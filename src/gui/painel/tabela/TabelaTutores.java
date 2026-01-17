package gui.painel.tabela;
import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import pessoas.Tutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class TabelaTutores extends JPanel {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TabelaTutores(Set<Tutor> tutores) {
        setLayout(new BorderLayout());

        // colunas
        String[] colunas = {"Nome", "CPF", "Telefone", "Endereço", "Tratamento", "Qtd. Animais", "Animais"};

        // modelo da tabela
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenche a tabela
        for (Tutor t : tutores) {
            Object[] linha = {
                    t.getNome(),
                    t.getCpf(),
                    t.getTelefone(),
                    t.getEndereco(),
                    t.getTratamento() != null ? t.getTratamento().toString() : "Não definido",
                    t.getAnimais().size(),
                    formatarAnimais(t.getAnimais())
            };
            modelo.addRow(linha);
        }

        // Cria a tabela
        tabela = new JTable(modelo);
        tabela.setFillsViewportHeight(true);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajusta largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(150); // Nome
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120); // CPF
        tabela.getColumnModel().getColumn(2).setPreferredWidth(110); // Telefone
        tabela.getColumnModel().getColumn(3).setPreferredWidth(200); // Endereço
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100); // Tratamento
        tabela.getColumnModel().getColumn(5).setPreferredWidth(80);  // Qtd. Animais
        tabela.getColumnModel().getColumn(6).setPreferredWidth(250); // Animais

        // Permite quebra de linha nas células
        tabela.setRowHeight(40);

        // Adiciona scroll
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private String formatarAnimais(Set<Animal> animais) {
        if (animais.isEmpty()) {
            return "Nenhum animal";
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Animal a : animais) {
            if (count > 0) {
                sb.append(", ");
            }

            String especie = "";
            if (a instanceof Cachorro) {
                especie = "Cachorro";
            } else if (a instanceof Gato) {
                especie = "Gato";
            }

            sb.append(a.getNome()).append(" ").append(especie);
            count++;

            // Limita a 3 animais na exibição
            if (count >= 3 && animais.size() > 3) {
                sb.append("... (+").append(animais.size() - 3).append(")");
                break;
            }
        }

        return sb.toString();
    }
}