package gui.painel.tabela;

import pessoas.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class TabelaFuncionarios extends JPanel {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TabelaFuncionarios(Set<Veterinario> veterinarios) {
        setLayout(new BorderLayout());

        // Colunas
        String[] colunas = {"Nome", "CPF", "Telefone", "CRMV", "Agendamentos"};

        //modelo da tabela
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //tabela com os dados
        for (Veterinario v : veterinarios) {
            Object[] linha = {
                    v.getNome(),
                    formatarCPF(v.getCpf()),
                    v.getTelefone(),
                    v.getCrmv(),
                    v.getAgendamentos() != null ? v.getAgendamentos().size() : 0
            };
            modelo.addRow(linha);
        }

        // Cria a tabela
        tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajusta largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(200); // Nome
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // CPF
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120); // Telefone
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // CRMV
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Agendamentos

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

    private String formatarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    // Método para atualizar a tabela
    public void atualizarTabela(Set<Veterinario> veterinarios) {
        modelo.setRowCount(0);

        for (Veterinario v : veterinarios) {
            Object[] linha = {
                    v.getNome(),
                    formatarCPF(v.getCpf()),
                    v.getTelefone(),
                    v.getCrmv(),
                    v.getAgendamentos() != null ? v.getAgendamentos().size() : 0
            };
            modelo.addRow(linha);
        }

        // Atualiza o total
        removeAll();
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelTotal = new JLabel("Total de veterinários: " + veterinarios.size());
        labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
        painelInfo.add(labelTotal);
        add(painelInfo, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    // Método para obter a tabela
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

    // Método para mostrar em janela separada
    public static void mostrarEmJanela(Set<Veterinario> veterinarios) {
        JFrame frame = new JFrame("Veterinários Cadastrados");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        TabelaFuncionarios painelTabela = new TabelaFuncionarios(veterinarios);
        frame.add(painelTabela);

        frame.setVisible(true);
    }
}