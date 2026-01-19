package gui.painel.tabela;

import cadastro.CadastroAgendamentos;
import excecoes.AnimalIndisponivelException;
import excecoes.VeterinarioIndisponivelException;
import servicos.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class TabelaAgendamentos extends JPanel {
    private JTable tabela;
    private DefaultTableModel modelo;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private JLabel labelTotal;
    private Set<Servico> servicosAtuais;

    public TabelaAgendamentos(Set<Servico> servicos) {
        setLayout(new BorderLayout());
        this.servicosAtuais = servicos;

        // Define as colunas
        String[] colunas = {"Tipo Serviço", "Data/Hora Inicial", "Data/Hora Final", "Animal", "Tutor", "Veterinário", "Valor", "Status", "Editar", "Cancelar"};

        // Cria o modelo
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8 || column == 9; // Apenas colunas de ações são editáveis
            }
        };

        // Preenche a tabela com os dados
        for (Servico s : servicos) {
            Object[] linha = {
                    s.tipoServico(),
                    s.getDataHoraInicio().format(formatter),
                    s.getDataHoraFinal().format(formatter),
                    s.getAnimal().getNome(),
                    s.getAnimal().getTutor().getNome(),
                    s.getVeterinario().getNome(),
                    String.format("R$ %.2f", s.getValorBase()),
                    s.isCancelado() ? "Cancelado" : "Agendado",
                    "Editar Data",
                    s.isCancelado() ? "-" : "Cancelar"
            };
            modelo.addRow(linha);
        }

        // Cria a tabela
        tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(30);

        // Ajusta largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Tipo
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // Data/Hora I
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120); // Data/Hora F
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // Animal
        tabela.getColumnModel().getColumn(4).setPreferredWidth(120); // Tutor
        tabela.getColumnModel().getColumn(5).setPreferredWidth(120); // Veterinário
        tabela.getColumnModel().getColumn(6).setPreferredWidth(80);  // Valor
        tabela.getColumnModel().getColumn(7).setPreferredWidth(80);  // Status
        tabela.getColumnModel().getColumn(8).setPreferredWidth(100); // Editar
        tabela.getColumnModel().getColumn(9).setPreferredWidth(100); // Cancelar

        // Adiciona botões nas colunas de ações
        tabela.getColumnModel().getColumn(8).setCellRenderer(new RenderBotao());
        tabela.getColumnModel().getColumn(8).setCellEditor(new EditorBotaoEditar(new JCheckBox(), servicos));

        tabela.getColumnModel().getColumn(9).setCellRenderer(new RenderBotao());
        tabela.getColumnModel().getColumn(9).setCellEditor(new EditorBotaoCancelar(new JCheckBox(), servicos));

        // Adiciona scroll
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com total
        JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        double total = calcularTotal(servicos);
        labelTotal = new JLabel(String.format("Valor Total: R$ %.2f", total));
        labelTotal.setFont(new Font("Arial", Font.BOLD, 14));
        painelTotal.add(labelTotal);
        add(painelTotal, BorderLayout.SOUTH);
    }

    private double calcularTotal(Set<Servico> servicos) {
        double total = 0.0;
        for (Servico s : servicos) {
            if (!s.isCancelado()) {
                total += s.getValorBase();
            }
        }
        return total;
    }

    private Servico encontrarServico(int row) {
        String tipoServico = (String) modelo.getValueAt(row, 0);
        String dataHoraI = (String) modelo.getValueAt(row, 1);
        String nomeAnimal = (String) modelo.getValueAt(row, 3);

        for (Servico s : servicosAtuais) {
            if (s.tipoServico().equals(tipoServico) &&
                    s.getDataHoraInicio().format(formatter).equals(dataHoraI) &&
                    s.getAnimal().getNome().equals(nomeAnimal)) {
                return s;
            }
        }
        return null;
    }

    private void abrirDialogoEditarData(Servico servico) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Editar Data/Hora", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(this);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Data/Hora Início
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nova Data/Hora Início:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtInicio = new JTextField(servico.getDataHoraInicio().format(formatter), 20);
        painel.add(txtInicio, gbc);

        // Data/Hora Final (apenas se não for Consulta ou Vacina)
        JTextField txtFim = null;
        String tipo = servico.tipoServico();
        boolean precisaFim = tipo.contains("Cirurgia") || tipo.contains("Exame");

        if (precisaFim) {
            gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
            painel.add(new JLabel("Nova Data/Hora Fim:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            txtFim = new JTextField(servico.getDataHoraFinal().format(formatter), 20);
            painel.add(txtFim, gbc);
        }

        // Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        JTextField finalTxtFim = txtFim;
        btnSalvar.addActionListener(e -> {
            try {
                LocalDateTime novoInicio = LocalDateTime.parse(txtInicio.getText().trim(), formatter);
                LocalDateTime novoFim;

                if (precisaFim && finalTxtFim != null) {
                    novoFim = LocalDateTime.parse(finalTxtFim.getText().trim(), formatter);
                } else {
                    // Para Consulta e Vacina, mantém a duração original
                    novoFim = novoInicio.plusMinutes(
                            java.time.Duration.between(servico.getDataHoraInicio(), servico.getDataHoraFinal()).toMinutes()
                    );
                }

                CadastroAgendamentos cadastro = new CadastroAgendamentos();
                cadastro.alterarDataServico(servico, novoInicio, novoFim);

                // Atualiza a tabela
                atualizarTabelaAG(servicosAtuais);

                JOptionPane.showMessageDialog(dialog, "Data alterada com sucesso!");
                dialog.dispose();

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Formato de data inválido! Use dd/MM/yyyy HH:mm",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } catch (VeterinarioIndisponivelException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Veterinário indisponível nesse horário!",
                        "Conflito",
                        JOptionPane.ERROR_MESSAGE);
            } catch (AnimalIndisponivelException ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Animal já possui serviço nesse horário!",
                        "Conflito",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog,
                        "Erro: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = precisaFim ? 2 : 1;
        gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);

        dialog.add(painel);
        dialog.setVisible(true);
    }

    private void abrirDialogoCancelar(Servico servico, int row) {
        if (servico.isCancelado()) {
            JOptionPane.showMessageDialog(this,
                    "Este serviço já está cancelado!",
                    "Informação",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente cancelar este agendamento?\n\n" +
                        "Tipo: " + servico.tipoServico() + "\n" +
                        "Data: " + servico.getDataHoraInicio().format(formatter) + "\n" +
                        "Animal: " + servico.getAnimal().getNome(),
                "Confirmar Cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcao == JOptionPane.YES_OPTION) {
            CadastroAgendamentos cadastro = new CadastroAgendamentos();
            cadastro.cancelarServico(servico);

            // Atualiza a linha na tabela
            modelo.setValueAt("Cancelado", row, 7);
            modelo.setValueAt("-", row, 9);

            // Atualiza o total
            double total = calcularTotal(servicosAtuais);
            labelTotal.setText(String.format("Valor Total: R$ %.2f", total));

            JOptionPane.showMessageDialog(this,
                    "Serviço cancelado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Renderer para os botões
    class RenderBotao implements TableCellRenderer {
        private JButton button;

        public RenderBotao() {
            button = new JButton();
            button.setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            button.setEnabled(!value.toString().equals("-"));
            return button;
        }
    }

    // Editor para o botão Editar
    class EditorBotaoEditar extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;
        private Set<Servico> servicos;

        public EditorBotaoEditar(JCheckBox checkBox, Set<Servico> servicos) {
            super(checkBox);
            this.servicos = servicos;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "Editar Data" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                Servico servico = encontrarServico(row);
                if (servico != null) {
                    abrirDialogoEditarData(servico);
                }
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }

    // Editor para o botão Cancelar
    class EditorBotaoCancelar extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;
        private Set<Servico> servicos;

        public EditorBotaoCancelar(JCheckBox checkBox, Set<Servico> servicos) {
            super(checkBox);
            this.servicos = servicos;
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "Cancelar" : value.toString();
            button.setText(label);
            button.setEnabled(!label.equals("-"));
            clicked = !label.equals("-");
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                Servico servico = encontrarServico(row);
                if (servico != null && !servico.isCancelado()) {
                    abrirDialogoCancelar(servico, row);
                }
            }
            clicked = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }

    // Método para atualizar a tabela
    public void atualizarTabelaAG(Set<Servico> servicos) {
        this.servicosAtuais = servicos;
        modelo.setRowCount(0);

        for (Servico s : servicos) {
            Object[] linha = {
                    s.tipoServico(),
                    s.getDataHoraInicio().format(formatter),
                    s.getDataHoraFinal().format(formatter),
                    s.getAnimal().getNome(),
                    s.getAnimal().getTutor().getNome(),
                    s.getVeterinario().getNome(),
                    String.format("R$ %.2f", s.getValorBase()),
                    s.isCancelado() ? "Cancelado" : "Agendado",
                    "Editar Data",
                    s.isCancelado() ? "-" : "Cancelar"
            };
            modelo.addRow(linha);
        }

        // Atualiza apenas o label do total
        double total = calcularTotal(servicos);
        labelTotal.setText(String.format("Valor Total: R$ %.2f", total));

        // Força atualização visual
        tabela.revalidate();
        tabela.repaint();
        revalidate();
        repaint();
    }

    // Método para obter a tabela
    public JTable getTabela() {
        return tabela;
    }
}