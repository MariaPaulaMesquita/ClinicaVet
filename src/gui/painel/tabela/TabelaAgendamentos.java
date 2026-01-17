package gui.painel.tabela;


import animais.Animal;
import pessoas.Tutor;
import pessoas.Veterinario;
import servicos.Servico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public class TabelaAgendamentos extends JPanel {
        private JTable tabela;
        private DefaultTableModel modelo;
        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        public TabelaAgendamentos(Set<Servico> servicos) {
            setLayout(new BorderLayout());

            // Define as colunas
            String[] colunas = {"Tipo Serviço", "Data/Hora", "Animal", "Tutor", "Veterinário", "Valor", "Status"};

            // Cria o modelo
            modelo = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            // Preenche a tabela com os dados
            for (Servico s : servicos) {
                Object[] linha = {
                        s.tipoServico(),
                        s.getDataHoraInicio().format(formatter),
                        s.getAnimal().getNome(),
                        s.getAnimal().getTutor().getNome(),
                        s.getVeterinario().getNome(),
                        String.format("R$ %.2f", s.getValorBase()),
                        s.isCancelado() ? "Cancelado" : "Agendado"
                };
                modelo.addRow(linha);
            }

            // Cria a tabela
            tabela = new JTable(modelo);
            tabela.setFillsViewportHeight(true);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // Ajusta largura das colunas
            tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Tipo
            tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // Data/Hora
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100); // Animal
            tabela.getColumnModel().getColumn(3).setPreferredWidth(120); // Tutor
            tabela.getColumnModel().getColumn(4).setPreferredWidth(120); // Veterinário
            tabela.getColumnModel().getColumn(5).setPreferredWidth(80);  // Valor
            tabela.getColumnModel().getColumn(6).setPreferredWidth(80);  // Status

            // Adiciona scroll
            JScrollPane scrollPane = new JScrollPane(tabela);
            add(scrollPane, BorderLayout.CENTER);

            // Painel inferior com total
            JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            double total = calcularTotal(servicos);
            JLabel labelTotal = new JLabel(String.format("Valor Total: R$ %.2f", total));
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

        // Método para atualizar a tabela
        public void atualizarTabela(Set<Servico> servicos) {
            modelo.setRowCount(0);

            for (Servico s : servicos) {
                Object[] linha = {
                        s.tipoServico(),
                        s.getDataHoraInicio().format(formatter),
                        s.getAnimal().getNome(),
                        s.getAnimal().getTutor().getNome(),
                        s.getVeterinario().getNome(),
                        String.format("R$ %.2f", s.getValorBase()),
                        s.isCancelado() ? "Cancelado" : "Agendado"
                };
                modelo.addRow(linha);
            }

            // Atualiza o total
            removeAll();
            JScrollPane scrollPane = new JScrollPane(tabela);
            add(scrollPane, BorderLayout.CENTER);

            JPanel painelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            double total = calcularTotal(servicos);
            JLabel labelTotal = new JLabel(String.format("Valor Total: R$ %.2f", total));
            labelTotal.setFont(new Font("Arial", Font.BOLD, 14));
            painelTotal.add(labelTotal);
            add(painelTotal, BorderLayout.SOUTH);

            revalidate();
            repaint();
        }

        // Método para obter a tabela
        public JTable getTabela() {
            return tabela;
        }

        // Método para mostrar em janela separada
        public static void mostrarEmJanela(Set<Servico> servicos) {
            JFrame frame = new JFrame("Agendamentos");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);

            TabelaAgendamentos painelTabela = new TabelaAgendamentos(servicos);
            frame.add(painelTabela);

            frame.setVisible(true);
        }

        // Filtros específicos
        public static TabelaAgendamentos porTutor(Set<Servico> servicos, Tutor tutor) {
            Set<Servico> filtrados = new TreeSet<>();
            for (Servico s : servicos) {
                if (s.getAnimal().getTutor().equals(tutor)) {
                    filtrados.add(s);
                }
            }
            return new TabelaAgendamentos(filtrados);
        }

        public static TabelaAgendamentos porAnimal(Set<Servico> servicos, Animal animal) {
            Set<Servico> filtrados = new TreeSet<>();
            for (Servico s : servicos) {
                if (s.getAnimal().equals(animal)) {
                    filtrados.add(s);
                }
            }
            return new TabelaAgendamentos(filtrados);
        }

        public static TabelaAgendamentos porVeterinario(Set<Servico> servicos, Veterinario vet) {
            Set<Servico> filtrados = new TreeSet<>();
            for (Servico s : servicos) {
                if (s.getVeterinario().equals(vet)) {
                    filtrados.add(s);
                }
            }
            return new TabelaAgendamentos(filtrados);
        }
}
