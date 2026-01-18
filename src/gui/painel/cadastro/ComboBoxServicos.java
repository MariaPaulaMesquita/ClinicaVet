package gui.painel.cadastro;
import animais.Animal;
import pessoas.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import static cadastro.CadastroClientes.getTodosAnimais;
import static cadastro.CadastroFuncionarios.getVeterinarios;

public class ComboBoxServicos extends JPanel {
    private JComboBox<String> comboServicos;
    private JComboBox<String> comboAnimais;
    private JComboBox<String> comboVeterinarios;
    private JLabel labelSelecionado;

    // Array com os tipos de serviços
    private static final String[] TIPOS_SERVICOS = {
            "Selecione um serviço...",
            "Consulta",
            "Cirurgia",
            "Exame",
            "Vacina"
    };

    public ComboBoxServicos() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Label Serviço
        JLabel label = new JLabel("Tipo de Serviço:");
        add(label);

        // ComboBox Serviço
        comboServicos = new JComboBox<>(TIPOS_SERVICOS);
        comboServicos.setPreferredSize(new Dimension(200, 25));
        add(comboServicos);

        // Label Animal
        JLabel labelAnimal = new JLabel("Animal:");
        add(labelAnimal);

        // ComboBox Animal (preenchido dinamicamente)
        comboAnimais = new JComboBox<>();
        comboAnimais.addItem("Selecione um animal...");
        preencherAnimais();
        comboAnimais.setPreferredSize(new Dimension(200, 25));
        add(comboAnimais);

        // Label Veterinário
        JLabel labelVeterinario = new JLabel("Veterinário:");
        add(labelVeterinario);

        // ComboBox Veterinário (preenchido dinamicamente)
        comboVeterinarios = new JComboBox<>();
        comboVeterinarios.addItem("Selecione um veterinário...");
        preencherVeterinarios();
        comboVeterinarios.setPreferredSize(new Dimension(200, 25));
        add(comboVeterinarios);

        // Label para mostrar seleção (opcional)
        labelSelecionado = new JLabel("");
        add(labelSelecionado);

        // Listener para quando selecionar serviço
        comboServicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLabelSelecionado();
            }
        });

        // Listener para quando selecionar animal
        comboAnimais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLabelSelecionado();
            }
        });

        // Listener para quando selecionar veterinário
        comboVeterinarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarLabelSelecionado();
            }
        });
    }

    private void preencherAnimais() {
        // Obtém todos os animais cadastrados
        Set<Animal> animais = getTodosAnimais();

        for (Animal a : animais) {
            comboAnimais.addItem(a.getNome()+" | "+a.getTutor().getNome()+" | "+a.getID());
        }
    }

    private void preencherVeterinarios() {
        // Obtém todos os veterinários cadastrados
        Set<Veterinario> veterinarios = getVeterinarios();

        for (Veterinario v : veterinarios) {
            comboVeterinarios.addItem(v.getNome()+" | "+v.getCrmv());
        }
    }

    private void atualizarLabelSelecionado() {
        String servico = getServicoSelecionado();
        String animal = getAnimalSelecionado();
        String veterinario = getVeterinarioSelecionado();

        StringBuilder texto = new StringBuilder();
        if (servico != null) texto.append("Serviço: ").append(servico);
        if (animal != null) {
            if (!texto.isEmpty()) texto.append(" | ");
            texto.append("Animal: ").append(animal);
        }
        if (veterinario != null) {
            if (!texto.isEmpty()) texto.append(" | ");
            texto.append("Vet: ").append(veterinario);
        }

        labelSelecionado.setText(texto.toString());
    }

    // Método para obter o serviço selecionado
    public String getServicoSelecionado() {
        String selecionado = (String) comboServicos.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um serviço...")) {
            return selecionado;
        }
        return null;
    }

    // Método para obter o animal selecionado
    public String getAnimalSelecionado() {
        String selecionado = (String) comboAnimais.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um animal...")) {
            return selecionado;
        }
        return null;
    }

    // Método para obter o veterinário selecionado
    public String getVeterinarioSelecionado() {
        String selecionado = (String) comboVeterinarios.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um veterinário...")) {
            return selecionado;
        }
        return null;
    }

    // Método para obter o objeto Animal selecionado
    public Animal getAnimalObjetoSelecionado() {
        String nomeAnimal = getAnimalSelecionado();
        if (nomeAnimal == null) return null;

        Set<Animal> animais = getTodosAnimais();
        for (Animal a : animais) {
            if (a.getNome().equals(nomeAnimal)) {
                return a;
            }
        }
        return null;
    }

    // Método para obter o objeto Veterinario selecionado
    public Veterinario getVeterinarioObjetoSelecionado() {
        String nomeVet = getVeterinarioSelecionado();
        if (nomeVet == null) return null;

        Set<Veterinario> veterinarios = getVeterinarios();
        for (Veterinario v : veterinarios) {
            if (v.getNome().equals(nomeVet)) {
                return v;
            }
        }
        return null;
    }

    // Método para atualizar a lista de animais
    public void atualizarListaAnimais() {
        comboAnimais.removeAllItems();
        comboAnimais.addItem("Selecione um animal...");
        preencherAnimais();
    }

    // Método para atualizar a lista de veterinários
    public void atualizarListaVeterinarios() {
        comboVeterinarios.removeAllItems();
        comboVeterinarios.addItem("Selecione um veterinário...");
        preencherVeterinarios();
    }

    // Método para atualizar todas as listas
    public void atualizarTodasListas() {
        atualizarListaAnimais();
        atualizarListaVeterinarios();
    }

    // Método para definir o serviço selecionado
    public void setServicoSelecionado(String servico) {
        comboServicos.setSelectedItem(servico);
    }

    // Método para resetar a seleção
    public void resetarSelecao() {
        comboServicos.setSelectedIndex(0);
        comboAnimais.setSelectedIndex(0);
        comboVeterinarios.setSelectedIndex(0);
        labelSelecionado.setText("");
    }

    // Método para obter o JComboBox de serviços
    public JComboBox<String> getComboServicos() {
        return comboServicos;
    }

    // Método para obter o JComboBox de animais
    public JComboBox<String> getComboAnimais() {
        return comboAnimais;
    }

    // Método para obter o JComboBox de veterinários
    public JComboBox<String> getComboVeterinarios() {
        return comboVeterinarios;
    }

    // Método para adicionar um listener customizado
    public void addSelecaoListener(ActionListener listener) {
        comboServicos.addActionListener(listener);
        comboAnimais.addActionListener(listener);
        comboVeterinarios.addActionListener(listener);
    }
}