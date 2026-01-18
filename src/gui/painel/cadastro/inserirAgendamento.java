package gui.painel.cadastro;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import cadastro.CadastroAgendamentos;
import excecoes.AnimalIndisponivelException;
import excecoes.ServicoInvalidoException;
import excecoes.VeterinarioIndisponivelException;
import gui.Alerta;
import pessoas.Veterinario;
import servicos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

import static cadastro.CadastroAgendamentos.agendarServico;
import static cadastro.CadastroAgendamentos.getServicosAgendados;
import static cadastro.CadastroClientes.getTodosAnimais;
import static cadastro.CadastroClientes.pesquisarAnimal;
import static cadastro.CadastroFuncionarios.getVeterinarios;
import static cadastro.CadastroFuncionarios.pesquisarVeterinario;
import static gui.painel.PainelAg.*;

public class inserirAgendamento extends JPanel {
    JPanel painelEntre = new JPanel(new BorderLayout());
    JPanel painelCampos = new JPanel(new GridBagLayout());
    JPanel painelBotao = new JPanel(new FlowLayout());
    JPanel painelzao = new JPanel(new BorderLayout());
    JPanel painelzinho = new JPanel(new FlowLayout());

    ComboBoxServicos seletor = new ComboBoxServicos();

    JComboBox<String> comboAnimais;
    JComboBox<String> comboVeterinarios;
    JComboBox<String> comboEspecifico; // Para cirurgia/exame/vacina

    JTextField txtDataHoraI = new JTextField(30);
    JTextField txtDataHoraF = new JTextField(30);

    JButton botaoCadastrar = new JButton("Agendar");

    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public inserirAgendamento(){
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Agendamento de novo serviço");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        add(painelzao, BorderLayout.CENTER);
        painelzao.add(painelzinho, BorderLayout.NORTH);
        painelzao.add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(painelCampos, BorderLayout.CENTER);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        painelzinho.add(seletor);

        // ComboBox de animais
        comboAnimais = new JComboBox<>();
        comboAnimais.addItem("Selecione um animal...");
        preencherAnimais();
        comboAnimais.setPreferredSize(new Dimension(250, 25));

        // ComboBox de veterinários
        comboVeterinarios = new JComboBox<>();
        comboVeterinarios.addItem("Selecione um veterinário...");
        preencherVeterinarios();
        comboVeterinarios.setPreferredSize(new Dimension(250, 25));

        // ComboBox específico (vazio inicialmente)
        comboEspecifico = new JComboBox<>();
        comboEspecifico.setPreferredSize(new Dimension(250, 25));

        // Botão cadastrar
        botaoCadastrar.addActionListener(e -> acaoCadastro());
        painelBotao.add(botaoCadastrar);

        // Listener para atualizar campos quando mudar tipo de serviço
        seletor.getComboTipoServico().addActionListener(e -> atualizarCampos());

        // Listener para atualizar combo específico quando mudar animal (para cirurgias)
        comboAnimais.addActionListener(e -> atualizarComboEspecifico());

        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    private void preencherAnimais() {
        Set<Animal> animais = getTodosAnimais();
        for (Animal a : animais) {
            comboAnimais.addItem(a.getNome() + " | " + a.getTutor().getNome() + " | " + a.getID());
        }
    }

    private void preencherVeterinarios() {
        Set<Veterinario> veterinarios = getVeterinarios();
        for (Veterinario v : veterinarios) {
            comboVeterinarios.addItem(v.getNome() + " | " + v.getCrmv());
        }
    }

    private void atualizarComboEspecifico() {
        String tipoServico = seletor.getTipoServicoSelecionado();

        if ("Cirurgia".equals(tipoServico)) {
            String animalSelecionado = getAnimalSelecionado();
            if (animalSelecionado != null) {
                Animal animal = pesquisarAnimal(animalSelecionado);
                if (animal != null) {
                    String especie = animal instanceof Cachorro ? "Cachorro" : "Gato";
                    comboEspecifico.removeAllItems();
                    String[] opcoes = ComboBoxServicos.getOpcoesCirurgia(especie);
                    for (String opcao : opcoes) {
                        comboEspecifico.addItem(opcao);
                    }
                }
            }
        }
    }

    private void atualizarCampos() {
        String tipoServico = seletor.getTipoServicoSelecionado();

        painelCampos.removeAll();

        if (tipoServico == null) {
            painelCampos.revalidate();
            painelCampos.repaint();
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Animal
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelCampos.add(new JLabel("Animal:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(comboAnimais, gbc);

        // Veterinário
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(new JLabel("Veterinário:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(comboVeterinarios, gbc);

        int row = 2;

        // Combo específico para Cirurgia, Exame ou Vacina
        if ("Cirurgia".equals(tipoServico)) {
            gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
            painelCampos.add(new JLabel("Tipo de Cirurgia:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            comboEspecifico.removeAllItems();
            comboEspecifico.addItem("Selecione um animal primeiro...");
            painelCampos.add(comboEspecifico, gbc);
            row++;

        } else if ("Exame".equals(tipoServico)) {
            gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
            painelCampos.add(new JLabel("Tipo de Exame:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            comboEspecifico.removeAllItems();
            String[] opcoes = ComboBoxServicos.getOpcoesExame();
            for (String opcao : opcoes) {
                comboEspecifico.addItem(opcao);
            }
            painelCampos.add(comboEspecifico, gbc);
            row++;

        } else if ("Vacina".equals(tipoServico)) {
            gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
            painelCampos.add(new JLabel("Tipo de Vacina:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            comboEspecifico.removeAllItems();
            String[] opcoes = ComboBoxServicos.getOpcoesVacina();
            for (String opcao : opcoes) {
                comboEspecifico.addItem(opcao);
            }
            painelCampos.add(comboEspecifico, gbc);
            row++;
        }

        // Data e Hora Início
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        painelCampos.add(new JLabel("Data/Hora Início (dd/MM/yyyy HH:mm):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtDataHoraI, gbc);
        row++;

        // Data e Hora Final (apenas para Cirurgia e Exame)
        if ("Cirurgia".equals(tipoServico) || "Exame".equals(tipoServico)) {
            gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
            painelCampos.add(new JLabel("Data/Hora Fim (dd/MM/yyyy HH:mm):"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            painelCampos.add(txtDataHoraF, gbc);
        }

        painelCampos.revalidate();
        painelCampos.repaint();
    }

    public String getAnimalSelecionado() {
        String selecionado = (String) comboAnimais.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um animal...")) {
            return selecionado;
        }
        return null;
    }

    public String getVeterinarioSelecionado() {
        String selecionado = (String) comboVeterinarios.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um veterinário...")) {
            return selecionado;
        }
        return null;
    }

    public String getEspecificoSelecionado() {
        String selecionado = (String) comboEspecifico.getSelectedItem();
        if (selecionado != null &&
                !selecionado.startsWith("Selecione")) {
            return selecionado;
        }
        return null;
    }

    private void acaoCadastro(){
        try{
            String tipoServico = seletor.getTipoServicoSelecionado();

            if (tipoServico == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecione um tipo de serviço!",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (getAnimalSelecionado() == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecione um animal!",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (getVeterinarioSelecionado() == null) {
                JOptionPane.showMessageDialog(this,
                        "Selecione um veterinário!",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (txtDataHoraI.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Digite a data e hora de início!",
                        "Campo obrigatório",
                        JOptionPane.WARNING_MESSAGE);
                txtDataHoraI.requestFocus();
                return;
            }

            Animal animal = pesquisarAnimal(getAnimalSelecionado());
            Veterinario veterinario = pesquisarVeterinario(getVeterinarioSelecionado());

            Servico novoServico = null;

            if(tipoServico.equals("Consulta")){
                novoServico = new Consulta(
                        LocalDateTime.parse(txtDataHoraI.getText().trim(), formatador),
                        animal,
                        veterinario
                );

            } else if(tipoServico.equals("Cirurgia")){
                String nomeCirurgia = getEspecificoSelecionado();
                if (nomeCirurgia == null) {
                    JOptionPane.showMessageDialog(this,
                            "Selecione o tipo de cirurgia!",
                            "Campo obrigatório",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtDataHoraF.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Digite a data e hora de término!",
                            "Campo obrigatório",
                            JOptionPane.WARNING_MESSAGE);
                    txtDataHoraF.requestFocus();
                    return;
                }

                novoServico = new Cirurgia(
                        LocalDateTime.parse(txtDataHoraI.getText().trim(), formatador),
                        LocalDateTime.parse(txtDataHoraF.getText().trim(), formatador),
                        animal,
                        veterinario,
                        nomeCirurgia
                );

            } else if(tipoServico.equals("Exame")){
                String nomeExame = getEspecificoSelecionado();
                if (nomeExame == null) {
                    JOptionPane.showMessageDialog(this,
                            "Selecione o tipo de exame!",
                            "Campo obrigatório",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (txtDataHoraF.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Digite a data e hora de término!",
                            "Campo obrigatório",
                            JOptionPane.WARNING_MESSAGE);
                    txtDataHoraF.requestFocus();
                    return;
                }

                novoServico = new Exame(
                        LocalDateTime.parse(txtDataHoraI.getText().trim(), formatador),
                        LocalDateTime.parse(txtDataHoraF.getText().trim(), formatador),
                        animal,
                        veterinario,
                        nomeExame
                );

            } else if(tipoServico.equals("Vacina")){
                String nomeVacina = getEspecificoSelecionado();
                if (nomeVacina == null) {
                    JOptionPane.showMessageDialog(this,
                            "Selecione o tipo de vacina!",
                            "Campo obrigatório",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                novoServico = new Vacinacao(
                        LocalDateTime.parse(txtDataHoraI.getText().trim(), formatador),
                        animal,
                        veterinario,
                        nomeVacina
                );
            }

            if (novoServico != null) {
                agendarServico(novoServico);
                CadastroAgendamentos.salvarAgendamentos();

                // Atualiza a tabela
                tabelaAG.atualizarTabelaAG(getServicosAgendados());

                // Limpa os campos
                txtDataHoraI.setText("");
                txtDataHoraF.setText("");
                seletor.resetarSelecao();
                comboAnimais.setSelectedIndex(0);
                comboVeterinarios.setSelectedIndex(0);

                // Limpa o painel de campos
                painelCampos.removeAll();
                painelCampos.revalidate();
                painelCampos.repaint();

                // Mostra alerta e volta para a tela da tabela
                new Alerta("Agendamento concluído com sucesso!");
                CartaAG.show(painelPrincipalAG, "Card 1");
            }

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Data/hora inválida! Use o formato dd/MM/yyyy HH:mm (ex: 26/01/2026 14:30)",
                    "Erro de formato",
                    JOptionPane.ERROR_MESSAGE);
            txtDataHoraI.requestFocus();

        } catch (VeterinarioIndisponivelException e) {
            JOptionPane.showMessageDialog(this,
                    "Veterinário indisponível: " + e.getMessage(),
                    "Conflito de horário",
                    JOptionPane.ERROR_MESSAGE);

        } catch (AnimalIndisponivelException e) {
            JOptionPane.showMessageDialog(this,
                    "Animal indisponível: " + e.getMessage(),
                    "Conflito de horário",
                    JOptionPane.ERROR_MESSAGE);

        } catch (ServicoInvalidoException e) {
            JOptionPane.showMessageDialog(this,
                    "Serviço inválido: " + e.getMessage(),
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro inesperado: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}