package gui.painel.tabela;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import servicos.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TabelaAnimais extends JPanel {
    private static JTable tabelaAN;
    private static DefaultTableModel modeloAN;
    private static DateTimeFormatter formatterAN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TabelaAnimais(Set<Animal> animais) {
        setLayout(new BorderLayout());

        // Define as colunas
        String[] colunas = {"Nome", "Sexo", "Data Nascimento", "Idade", "Raça", "Pelagem", "Porte", "Acesso à Rua", "Tutor", "Faixa Etária", "ID","Ações"};

        // Cria o modeloT da tabelaT
        modeloAN = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 11; // Apenas a coluna de ações é editável
            }
        };

        // Preenche a tabelaT com os dados do vetor
        for (Animal a : animais) {
            Object[] linha = {
                    a.getNome(),
                    a.getSexo(),
                    a.getDataNascimento().format(formatterAN),
                    formatarIdade(a.getIdade()),
                    a.getRaca(),
                    a.getPelagem(),
                    a instanceof Cachorro ? ((Cachorro) a).getPorte() : "N/A",
                    a instanceof Gato ? (((Gato) a).isAcessoARua() ? "Sim" : "Não") : "N/A",
                    a.getTutor() != null ? a.getTutor().getNome() : "Sem tutor",
                    a.calcularFaixaEtaria(),
                    a.getID(),
                    "Detalhes"
            };
            modeloAN.addRow(linha);
        }

        // Cria a tabela
        tabelaAN = new JTable(modeloAN);
        tabelaAN.setFillsViewportHeight(true);
        tabelaAN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaAN.setRowHeight(30);

        // Ajusta largura das colunas
        tabelaAN.getColumnModel().getColumn(0).setPreferredWidth(120); // Nome
        tabelaAN.getColumnModel().getColumn(1).setPreferredWidth(60);  // Sexo
        tabelaAN.getColumnModel().getColumn(2).setPreferredWidth(110); // Data
        tabelaAN.getColumnModel().getColumn(3).setPreferredWidth(100); // Idade
        tabelaAN.getColumnModel().getColumn(4).setPreferredWidth(100); // Raça
        tabelaAN.getColumnModel().getColumn(5).setPreferredWidth(100); // Pelagem
        tabelaAN.getColumnModel().getColumn(6).setPreferredWidth(80);  // Porte
        tabelaAN.getColumnModel().getColumn(7).setPreferredWidth(100); // Acesso à Rua
        tabelaAN.getColumnModel().getColumn(8).setPreferredWidth(120); // Tutor
        tabelaAN.getColumnModel().getColumn(9).setPreferredWidth(100); // Faixa Etária
        tabelaAN.getColumnModel().getColumn(10).setPreferredWidth(40); // Botão
        tabelaAN.getColumnModel().getColumn(11).setPreferredWidth(100); // Botão

        // Gerenciar o botão
        tabelaAN.getColumnModel().getColumn(11).setCellRenderer(new RenderBotao());
        tabelaAN.getColumnModel().getColumn(11).setCellEditor(new EditorBotao(new JCheckBox(), animais));

        // Adiciona scroll
        JScrollPane scrollPane = new JScrollPane(tabelaAN);
        add(scrollPane, BorderLayout.CENTER);

        // Painel inferior com total
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelTotal = new JLabel("Total de animais: " + animais.size());
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        painelInfo.add(labelTotal);
        add(painelInfo, BorderLayout.SOUTH);
    }

    private static String formatarIdade(Period idade) {
        if (idade.getYears() > 0) {
            return idade.getYears() + " anos e " + idade.getMonths() + " meses";
        } else {
            return idade.getMonths() + " meses";
        }
    }

    private void abrirDetalhesAnimal(Animal animal) {
        JFrame frameDetalhes = new JFrame("Detalhes - " + animal.getNome());
        frameDetalhes.setSize(900, 700);
        frameDetalhes.setLocationRelativeTo(this);
        frameDetalhes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frameDetalhes.add(painel);

        // Título
        JLabel titulo = new JLabel("Detalhes do Animal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(titulo, BorderLayout.NORTH);

        // Painel principal com scroll
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));

        // Painel de dados do animal
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0; gbc.gridy = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtNome = new JTextField(animal.getNome(), 30);
        txtNome.setEditable(false);
        painelCampos.add(txtNome, gbc);

        // Sexo
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(new JLabel("Sexo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtSexo = new JTextField(animal.getSexo(), 30);
        txtSexo.setEditable(false);
        painelCampos.add(txtSexo, gbc);

        // Data de Nascimento
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painelCampos.add(new JLabel("Data de Nascimento:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtData = new JTextField(animal.getDataNascimento().format(formatterAN), 30);
        txtData.setEditable(false);
        painelCampos.add(txtData, gbc);

        // Idade
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        painelCampos.add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtIdade = new JTextField(formatarIdade(animal.getIdade()), 30);
        txtIdade.setEditable(false);
        painelCampos.add(txtIdade, gbc);

        // Raça
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painelCampos.add(new JLabel("Raça:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtRaca = new JTextField(animal.getRaca(), 30);
        txtRaca.setEditable(false);
        painelCampos.add(txtRaca, gbc);

        // Pelagem
        gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
        painelCampos.add(new JLabel("Pelagem:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtPelagem = new JTextField(animal.getPelagem(), 30);
        txtPelagem.setEditable(false);
        painelCampos.add(txtPelagem, gbc);

        // Campos específicos por tipo
        if (animal instanceof Cachorro) {
            Cachorro cachorro = (Cachorro) animal;
            gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
            painelCampos.add(new JLabel("Porte:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            JTextField txtPorte = new JTextField(cachorro.getPorte(), 30);
            txtPorte.setEditable(false);
            painelCampos.add(txtPorte, gbc);
        } else if (animal instanceof Gato) {
            Gato gato = (Gato) animal;
            gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
            painelCampos.add(new JLabel("Acesso à Rua:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            JTextField txtAcesso = new JTextField(gato.isAcessoARua() ? "Sim" : "Não", 30);
            txtAcesso.setEditable(false);
            painelCampos.add(txtAcesso, gbc);
        }

        // Tutor
        gbc.gridx = 0; gbc.gridy = 7; gbc.weightx = 0;
        painelCampos.add(new JLabel("Tutor:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        String nomeTutor = animal.getTutor() != null ? animal.getTutor().getNome() : "Sem tutor";
        JTextField txtTutor = new JTextField(nomeTutor, 30);
        txtTutor.setEditable(false);
        painelCampos.add(txtTutor, gbc);

        // Faixa Etária
        gbc.gridx = 0; gbc.gridy = 8; gbc.weightx = 0;
        painelCampos.add(new JLabel("Faixa Etária:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        JTextField txtFaixa = new JTextField(animal.calcularFaixaEtaria(), 30);
        txtFaixa.setEditable(false);
        painelCampos.add(txtFaixa, gbc);

        painelPrincipal.add(painelCampos, BorderLayout.NORTH);

        // Tabela de agendamentos
        JPanel painelAgendamentos = new JPanel(new BorderLayout());
        painelAgendamentos.setBorder(BorderFactory.createTitledBorder("Agendamentos"));

        // Buscar agendamentos do animal
        Set<Servico> agendamentosAnimal = buscarAgendamentosAnimal(animal);

        if (agendamentosAnimal != null && !agendamentosAnimal.isEmpty()) {
            // Criar tabelaT de agendamentos
            String[] colunasAg = {"Tipo", "Data/Hora Inicial", "Data/Hora Final", "Veterinário", "Valor", "Status"};
            DefaultTableModel modeloAg = new DefaultTableModel(colunasAg, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            DateTimeFormatter formatterAg = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Servico s : agendamentosAnimal) {
                Object[] linha = {
                        s.tipoServico(),
                        s.getDataHoraInicio().format(formatterAg),
                        s.getDataHoraFinal().format(formatterAg),
                        s.getVeterinario().getNome(),
                        String.format("R$ %.2f", s.calcularPreco(s.getAnimal().getTutor())),
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
            double total = calcularTotalAgendamentos(agendamentosAnimal);
            JLabel labelTotal = new JLabel(String.format("Total: R$ %.2f | Agendamentos: %d", total, agendamentosAnimal.size()));
            labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
            painelTotal.add(labelTotal);
            painelAgendamentos.add(painelTotal, BorderLayout.SOUTH);

        } else {
            JLabel labelSemAgendamentos = new JLabel("Nenhum agendamento encontrado", SwingConstants.CENTER);
            labelSemAgendamentos.setFont(new Font("Arial", Font.ITALIC, 14));
            painelAgendamentos.add(labelSemAgendamentos, BorderLayout.CENTER);
        }

        painelPrincipal.add(painelAgendamentos, BorderLayout.CENTER);

        painel.add(painelPrincipal, BorderLayout.CENTER);

        // Botão fechar
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> frameDetalhes.dispose());
        painelBotao.add(btnFechar);
        painel.add(painelBotao, BorderLayout.SOUTH);

        frameDetalhes.setVisible(true);
    }

    // Método auxiliar para buscar agendamentos do animal
    private Set<Servico> buscarAgendamentosAnimal(Animal animal) {
        Set<Servico> todosServicos = cadastro.CadastroAgendamentos.getServicosAgendados();
        Set<Servico> servicosAnimal = new java.util.TreeSet<>();

        for (Servico s : todosServicos) {
            if (s.getAnimal().equals(animal)) {
                servicosAnimal.add(s);
            }
        }

        return servicosAnimal;
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
    // Renderer para o botão
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

    // Editor para o botão
    class EditorBotao extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean clicked;
        private int row;
        private Set<Animal> animais;

        public EditorBotao(JCheckBox checkBox, Set<Animal> animaisSet) {
            super(checkBox);
            this.animais = animaisSet;
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
                // Obtém o animal da linha clicada
                String nome = (String) modeloAN.getValueAt(row, 0);
                Animal animalSelecionado = null;

                for (Animal a : animais) {
                    if (a.getNome().equals(nome)) {
                        animalSelecionado = a;
                        break;
                    }
                }

                if (animalSelecionado != null) {
                    abrirDetalhesAnimal(animalSelecionado);
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

    // Método para atualizar a tabelaT com novos dados
    public static void atualizarTabelaAN(Set<Animal> animais) {
        modeloAN.setRowCount(0);

        for (Animal a : animais) {
            Object[] linha = {
                    a.getNome(),
                    a.getSexo(),
                    a.getDataNascimento().format(formatterAN),
                    formatarIdade(a.getIdade()),
                    a.getRaca(),
                    a.getPelagem(),
                    a instanceof Cachorro ? ((Cachorro) a).getPorte() : "N/A",
                    a instanceof Gato ? (((Gato) a).isAcessoARua() ? "Sim" : "Não") : "N/A",
                    a.getTutor() != null ? a.getTutor().getNome() : "Sem tutor",
                    a.calcularFaixaEtaria(),
                    a.getID(),
                    "Detalhes"
            };
            modeloAN.addRow(linha);
        }
    }

    // Método para obter a tabela
    public JTable getTabela() {
        return tabelaAN;
    }

    // Método para mostrar em uma janela separada
    public static void mostrarEmJanela(Set<Animal> animais) {
        JFrame frame = new JFrame("Tabela de Animais");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        TabelaAnimais painelTabela = new TabelaAnimais(animais);
        frame.add(painelTabela);

        frame.setVisible(true);
    }
}