package gui.painel.cadastro;

import animais.Animal;
import pessoas.Veterinario;
import servicos.TiposCirurgia;
import servicos.TiposExames;
import servicos.TiposVacinas;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

import static cadastro.CadastroClientes.getTodosAnimais;
import static cadastro.CadastroFuncionarios.getVeterinarios;

public class ComboBoxServicos extends JPanel {
    private JComboBox<String> comboTipoServico;

    // Array com os tipos de serviços
    private static final String[] TIPOS_SERVICOS = {
            "Selecione um tipo...",
            "Consulta",
            "Cirurgia",
            "Exame",
            "Vacina"
    };

    public ComboBoxServicos() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Label Tipo de Serviço
        JLabel label = new JLabel("Tipo de Serviço:");
        add(label);

        // ComboBox Tipo de Serviço
        comboTipoServico = new JComboBox<>(TIPOS_SERVICOS);
        comboTipoServico.setPreferredSize(new Dimension(200, 25));
        add(comboTipoServico);
    }

    // Método para obter o tipo de serviço selecionado
    public String getTipoServicoSelecionado() {
        String selecionado = (String) comboTipoServico.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um tipo...")) {
            return selecionado;
        }
        return null;
    }

    // Método para resetar a seleção
    public void resetarSelecao() {
        comboTipoServico.setSelectedIndex(0);
    }

    // Método para obter o JComboBox
    public JComboBox<String> getComboTipoServico() {
        return comboTipoServico;
    }

    // Métodos estáticos para obter as opções de cada tipo de serviço
    public static String[] getOpcoesCirurgia(String especie) {
        Map<String, Double> cirurgias = TiposCirurgia.cirurgias.get(especie);
        if (cirurgias != null) {
            String[] opcoes = new String[cirurgias.size() + 1];
            opcoes[0] = "Selecione uma cirurgia...";
            int i = 1;
            for (String nome : cirurgias.keySet()) {
                opcoes[i++] = nome;
            }
            return opcoes;
        }
        return new String[]{"Selecione uma cirurgia..."};
    }

    public static String[] getOpcoesExame() {
        String[] opcoes = new String[TiposExames.exames.size() + 1];
        opcoes[0] = "Selecione um exame...";
        int i = 1;
        for (String nome : TiposExames.exames.keySet()) {
            opcoes[i++] = nome;
        }
        return opcoes;
    }

    public static String[] getOpcoesVacina() {
        String[] opcoes = new String[TiposVacinas.vacinas.size() + 1];
        opcoes[0] = "Selecione uma vacina...";
        int i = 1;
        for (String nome : TiposVacinas.vacinas.keySet()) {
            opcoes[i++] = nome;
        }
        return opcoes;
    }
}