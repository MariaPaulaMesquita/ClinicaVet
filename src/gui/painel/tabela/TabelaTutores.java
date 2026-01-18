package gui.painel.tabela;
import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import pessoas.Tutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

import static gui.painel.tabela.TabelaFuncionarios.formatarCPF;

public class TabelaTutores extends JPanel {
    public static JTable tabelaT;
    public static DefaultTableModel modeloT;

    public TabelaTutores(Set<Tutor> tutores) {
        setLayout(new BorderLayout());

        // colunas
        String[] colunas = {"Nome", "CPF", "Telefone", "Endereço", "Tratamento", "Qtd. Animais", "Animais"};

        // modeloT da tabelaT
        modeloT = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenche a tabelaT
        for (Tutor t : tutores) {
            Object[] linha = {
                    t.getNome(),
                    formatarCPF(t.getCpf()),
                    t.getTelefone(),
                    t.getEndereco(),
                    t.getTratamento() != null ? t.getTratamento().toString() : "Não definido",
                    t.getAnimais().size(),
                    formatarAnimais(t.getAnimais())
            };
            modeloT.addRow(linha);
        }

        // Cria a tabelaT
        tabelaT = new JTable(modeloT);
        tabelaT.setFillsViewportHeight(true);
        tabelaT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajusta largura das colunas
        tabelaT.getColumnModel().getColumn(0).setPreferredWidth(150); // Nome
        tabelaT.getColumnModel().getColumn(1).setPreferredWidth(120); // CPF
        tabelaT.getColumnModel().getColumn(2).setPreferredWidth(110); // Telefone
        tabelaT.getColumnModel().getColumn(3).setPreferredWidth(200); // Endereço
        tabelaT.getColumnModel().getColumn(4).setPreferredWidth(100); // Tratamento
        tabelaT.getColumnModel().getColumn(5).setPreferredWidth(80);  // Qtd. Animais
        tabelaT.getColumnModel().getColumn(6).setPreferredWidth(250); // Animais

        // Permite quebra de linha nas células
        tabelaT.setRowHeight(40);

        // Adiciona scroll
        JScrollPane scrollPane = new JScrollPane(tabelaT);
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