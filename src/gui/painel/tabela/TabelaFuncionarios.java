package gui.painel.tabela;

import animais.Animal;
import pessoas.Veterinario;
import servicos.Servico;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TabelaFuncionarios extends JPanel {
    private static JTable tabela;
    public static DefaultTableModel modelo;
    public static DefaultTableModel getModelo(){
        return modelo;
    }

    public TabelaFuncionarios(Set<Veterinario> veterinarios) {
        setLayout(new BorderLayout());

        // Colunas
        String[] colunas = {"Nome", "CPF", "Telefone", "CRMV", "Ano de Contrato", "Formação", "Ações"};

        //modeloT da tabelaT
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        //tabelaT com os dados
        for (Veterinario v : veterinarios) {
            Object[] linha = {
                    v.getNome(),
                    formatarCPF(v.getCpf()),
                    v.getTelefone(),
                    v.getCrmv(),
                    v.getAnoContrato(),
                    v.getAnoFormacao(),
                    "Detalhes"
            };
            modelo.addRow(linha);
        }

        // Cria a tabelaT
        tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajusta largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200); // Nome
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // CPF
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120); // Telefone
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // CRMV
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // DataContrato
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100); // DataFormacao
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100); // Botão

        //Gerenciar o botao
        tabela.getColumnModel().getColumn(6).setCellRenderer(new RenderBotao());
        tabela.getColumnModel().getColumn(6).setCellEditor(new EditorBotao(new JCheckBox(), veterinarios));

        // Adiciona scroll
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        //Total
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelTotal = new JLabel("Total de veterinários: " + veterinarios.size());
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        painelInfo.add(labelTotal);
        add(painelInfo, BorderLayout.SOUTH);
    }

    public static String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    // Método para obter a tabelaT
    public JTable getTabela() {
        return tabela;
    }

    // Método para obter o veterinário selecionado
    public Veterinario getVeterinarioSelecionado(Set<Veterinario> veterinarios) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            return null;
        }

        String nomeSelecionado = (String) modelo.getValueAt(linha, 0);
        for (Veterinario v : veterinarios) {
            if (v.getNome().equals(nomeSelecionado)) {
                return v;
            }
        }
        return null;
    }

    private void abrirDetalhesVeterinario(Veterinario vet) {
        JFrame frameDetalhes = new JFrame("Detalhes - " + vet.getNome());
        frameDetalhes.setSize(900, 700);
        frameDetalhes.setLocationRelativeTo(this);
        frameDetalhes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameDetalhes.setVisible(true);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frameDetalhes.add(painel);

        // Título
        JLabel titulo = new JLabel("Detalhes do Veterinário", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(titulo, BorderLayout.NORTH);

        // Painel central com os campos
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField txtNome = new JTextField(vet.getNome(), 30);
        txtNome.setEditable(false);
        painelCampos.add(txtNome, gbc);

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField txtCPF = new JTextField(formatarCPF(vet.getCpf()), 30);
        txtCPF.setEditable(false);
        painelCampos.add(txtCPF, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField txtTelefone = new JTextField(vet.getTelefone(), 30);
        txtTelefone.setEditable(false);
        painelCampos.add(txtTelefone, gbc);

        // CRMV
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("CRMV:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField txtCRMV = new JTextField(vet.getCrmv(), 30);
        txtCRMV.setEditable(false);
        painelCampos.add(txtCRMV, gbc);

        // Agendamentos
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Agendamentos:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        int numAgendamentos = vet.getAgendamentos() != null ? vet.getAgendamentos().size() : 0;
        JTextField txtAgendamentos = new JTextField(String.valueOf(numAgendamentos), 30);
        txtAgendamentos.setEditable(false);
        painelCampos.add(txtAgendamentos, gbc);
        painel.add(painelCampos, BorderLayout.NORTH);

        // Tabela de agendamentos
        JPanel painelAgendamentos = new JPanel(new BorderLayout());
        painelAgendamentos.setBorder(BorderFactory.createTitledBorder("Agendamentos"));

        // Buscar agendamentos do vet
        Set<Servico> agendamentosVet = buscarAgendamentosVeterinario(vet);

        if (agendamentosVet != null && !agendamentosVet.isEmpty()) {
            // Criar tabelaT de agendamentos
            String[] colunasAg = {"Tipo", "Data/Hora Inicial", "Data/Hora Final", "Animal", "Tutor", "Valor", "Status"};
            DefaultTableModel modeloAg = new DefaultTableModel(colunasAg, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            DateTimeFormatter formatterAg = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Servico s : agendamentosVet) {
                Object[] linha = {
                        s.tipoServico(),
                        s.getDataHoraInicio().format(formatterAg),
                        s.getDataHoraFinal().format(formatterAg),
                        s.getAnimal().getNome(),
                        s.getAnimal().getTutor().getNome(),
                        String.format("R$ %.2f", s.getValorBase()),
                        s.isCancelado() ? "Cancelado" : "Agendado"
                };
                modeloAg.addRow(linha);
            }

            JTable tabelaAg = new JTable(modeloAg);
            tabelaAg.setFillsViewportHeight(true);

            // Ajusta largura das colunas
            tabelaAg.getColumnModel().getColumn(0).setPreferredWidth(120);
            tabelaAg.getColumnModel().getColumn(1).setPreferredWidth(130);
            tabelaAg.getColumnModel().getColumn(2).setPreferredWidth(130);
            tabelaAg.getColumnModel().getColumn(3).setPreferredWidth(150);
            tabelaAg.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabelaAg.getColumnModel().getColumn(5).setPreferredWidth(80);

            JScrollPane scrollAg = new JScrollPane(tabelaAg);
            scrollAg.setPreferredSize(new Dimension(800, 200));
            painelAgendamentos.add(scrollAg, BorderLayout.CENTER);

            // Total de agendamentos
            JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            double total = calcularTotalAgendamentos(agendamentosVet);
            JLabel labelTotal = new JLabel(String.format("Total: R$ %.2f | Agendamentos: %d", total, agendamentosVet.size()));
            labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
            painelTotal.add(labelTotal);
            painelAgendamentos.add(painelTotal, BorderLayout.SOUTH);

        } else {
            JLabel labelSemAgendamentos = new JLabel("Nenhum agendamento encontrado", SwingConstants.CENTER);
            labelSemAgendamentos.setFont(new Font("Arial", Font.ITALIC, 14));
            painelAgendamentos.add(labelSemAgendamentos, BorderLayout.CENTER);
        }

        painel.add(painelAgendamentos, BorderLayout.CENTER);

        // Botão fechar
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> frameDetalhes.dispose());
        painelBotao.add(btnFechar);
        painel.add(painelBotao, BorderLayout.SOUTH);

        frameDetalhes.setVisible(true);
    }

    // Método auxiliar para buscar agendamentos
    private Set<Servico> buscarAgendamentosVeterinario(Veterinario vet) {
        Set<Servico> todosServicos = cadastro.CadastroAgendamentos.getServicosAgendados();
        Set<Servico> servicosVeterinario = new java.util.TreeSet<>();

        for (Servico s : todosServicos) {
            if (s.getVeterinario().equals(vet)) {
                servicosVeterinario.add(s);
            }
        }

        return servicosVeterinario;
    }

    // Método auxiliar para calcular total dos agendamentos
    private double calcularTotalAgendamentos(Set<Servico> servicos) {
        double total = 0.0;
        for (Servico s : servicos) {
            if (!s.isCancelado()) {
                total += s.getValorBase();
            }
        }
        return total;
    }

    class RenderBotao implements TableCellRenderer {
        private JButton button;

        public RenderBotao() {
            button = new JButton();
            button.setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            button.setText((value == null) ? "Detalhes" : value.toString());
            return button;
        }
    }
    class EditorBotao extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;
        private Set<Veterinario> veterinarios;

        public EditorBotao(JCheckBox checkBox, Set<Veterinario> vets) {
            super(checkBox);
            this.veterinarios = vets;
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "Detalhes" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (clicked) {
                // Obtém o veterinário da linha clicada
                String CPF = (String) TabelaFuncionarios.modelo.getValueAt(row, 1);
                Veterinario vetSelecionado = null;

                for (Veterinario v : veterinarios) {
                    if (formatarCPF(v.getCpf()).equals(CPF)) {
                        vetSelecionado = v;
                        break;
                    }
                }

                if (vetSelecionado != null) {
                    abrirDetalhesVeterinario(vetSelecionado);
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

}