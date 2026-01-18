package gui.painel.cadastro;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import gui.Alerta;
import pessoas.Tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static cadastro.CadastroClientes.*;
import static gui.painel.PainelAN.*;

public class inserirAnimal extends JPanel {
    JPanel painelEntre = new JPanel(new BorderLayout());
    JPanel painelCampos = new JPanel(new GridBagLayout());
    JPanel painelBotao = new JPanel(new FlowLayout());
    JPanel painelzao = new JPanel(new BorderLayout());
    JPanel painelzinho = new JPanel(new FlowLayout());

    JComboBox<String> comboTipos;
    JComboBox<String> comboTutores;

    // Radio Buttons para acesso à rua
    JRadioButton radioAcessoSim;
    JRadioButton radioAcessoNao;
    ButtonGroup grupoAcessoRua;
    JPanel painelRadio;

    private static final String[] TIPOS = {
            "Selecione um tipo...",
            "Cachorro",
            "Gato"
    };

    static DateTimeFormatter Data = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    JTextField txtNome = new JTextField(30);
    JTextField txtSexo = new JTextField(30);
    JTextField txtData = new JTextField(30);
    JTextField txtRaca = new JTextField(30);
    JTextField txtPelagem = new JTextField(30);
    JTextField txtPorte = new JTextField(30);

    JButton botaoCadastrar = new JButton("Cadastrar");

    public inserirAnimal(){
        setLayout(new BorderLayout());

        //Label com o titulo
        JLabel titulo = new JLabel();
        titulo.setText("Cadastro de novo animal");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titulo, BorderLayout.NORTH);

        add(painelzao, BorderLayout.CENTER);
        painelzao.add(painelzinho, BorderLayout.NORTH);
        painelzao.add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(painelCampos, BorderLayout.CENTER);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        // ComboBox de tipos
        JLabel label = new JLabel("Tipo de Animal:");
        painelzinho.add(label);

        comboTipos = new JComboBox<>(TIPOS);
        comboTipos.setPreferredSize(new Dimension(200, 25));
        painelzinho.add(comboTipos);

        // ComboBox de tutores
        comboTutores = new JComboBox<>();
        comboTutores.addItem("Selecione um tutor...");
        preencherTutores();
        comboTutores.setPreferredSize(new Dimension(200, 25));

        // Inicializa Radio Buttons para acesso à rua
        radioAcessoSim = new JRadioButton("Sim");
        radioAcessoNao = new JRadioButton("Não");
        radioAcessoNao.setSelected(true); // Padrão: Não

        grupoAcessoRua = new ButtonGroup();
        grupoAcessoRua.add(radioAcessoSim);
        grupoAcessoRua.add(radioAcessoNao);

        painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelRadio.add(radioAcessoSim);
        painelRadio.add(radioAcessoNao);

        // Botão cadastrar
        botaoCadastrar.addActionListener(e -> acaoCadastro());
        painelBotao.add(botaoCadastrar);

        // Listener para atualizar campos
        comboTipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampos();
            }
        });

        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
    }

    private void atualizarCampos() {
        String tipo = getTipoSelecionado();

        painelCampos.removeAll();

        if (tipo == null) {
            painelCampos.revalidate();
            painelCampos.repaint();
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtNome, gbc);

        // Sexo
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(new JLabel("Sexo (M/F):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtSexo, gbc);

        // Data de Nascimento
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painelCampos.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtData, gbc);

        // Raça
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        painelCampos.add(new JLabel("Raça:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtRaca, gbc);

        // Pelagem
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painelCampos.add(new JLabel("Pelagem:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtPelagem, gbc);

        // Campo específico por tipo
        if (tipo.equals("Cachorro")) {
            gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
            painelCampos.add(new JLabel("Porte:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            painelCampos.add(txtPorte, gbc);

        } else if (tipo.equals("Gato")) {
            gbc.gridx = 0; gbc.gridy = 5; gbc.weightx = 0;
            painelCampos.add(new JLabel("Acesso à rua:"), gbc);
            gbc.gridx = 1; gbc.weightx = 1.0;
            painelCampos.add(painelRadio, gbc);
        }

        // Tutor
        gbc.gridx = 0; gbc.gridy = 6; gbc.weightx = 0;
        painelCampos.add(new JLabel("Tutor:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(comboTutores, gbc);

        painelCampos.revalidate();
        painelCampos.repaint();
    }

    public String getTipoSelecionado() {
        String selecionado = (String) comboTipos.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um tipo...")) {
            return selecionado;
        }
        return null;
    }

    public String getTutorSelecionado() {
        String selecionado = (String) comboTutores.getSelectedItem();
        if (selecionado != null && !selecionado.equals("Selecione um tutor...")) {
            return selecionado;
        }
        return null;
    }

    // Método para obter o valor do acesso à rua
    public boolean getAcessoARua() {
        return radioAcessoSim.isSelected();
    }

    private void preencherTutores() {
        Set<Tutor> tutores = getTutores();
        for (Tutor t : tutores) {
            comboTutores.addItem(t.getNome() + " | " + t.getCpf());
        }
    }

    private void acaoCadastro(){
        try{
            String tipo = getTipoSelecionado();

            if (tipo == null) {
                JOptionPane.showMessageDialog(this, "Selecione um tipo de animal!");
                return;
            }

            if (getTutorSelecionado() == null) {
                JOptionPane.showMessageDialog(this, "Selecione um tutor!");
                return;
            }

            // Validações dos campos
            if (txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o nome do animal!");
                return;
            }

            if (txtSexo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o sexo do animal (M/F)!");
                return;
            }

            if (txtData.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite a data de nascimento!");
                return;
            }

            if (txtRaca.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite a raça!");
                return;
            }

            if (txtPelagem.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite a pelagem!");
                return;
            }

            // Parse da data
            LocalDate dataNasc = LocalDate.parse(txtData.getText(), Data);
            System.out.println("Data parsed: " + dataNasc); // DEBUG

            Animal novoAnimal = null;

            if(tipo.equals("Cachorro")){
                if (txtPorte.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o porte do cachorro!");
                    return;
                }

                novoAnimal = new Cachorro(
                        txtNome.getText(),
                        txtSexo.getText(),
                        dataNasc.getDayOfMonth(),
                        dataNasc.getMonthValue(),
                        dataNasc.getYear(),
                        txtRaca.getText(),
                        txtPelagem.getText(),
                        txtPorte.getText()
                );
                System.out.println("Cachorro criado: " + novoAnimal.getNome()); // DEBUG

            } else if(tipo.equals("Gato")){
                boolean acessoRua = getAcessoARua();
                novoAnimal = new Gato(
                        txtNome.getText(),
                        txtSexo.getText(),
                        dataNasc.getDayOfMonth(),
                        dataNasc.getMonthValue(),
                        dataNasc.getYear(),
                        txtRaca.getText(),
                        txtPelagem.getText(),
                        acessoRua
                );
                System.out.println("Gato criado: " + novoAnimal.getNome()); // DEBUG
            }

            Tutor tutor = pesquisarTutor(getTutorSelecionado());
            System.out.println("Tutor encontrado: " + (tutor != null ? tutor.getNome() : "NULL")); // DEBUG

            if (novoAnimal != null && tutor != null) {
                cadastrarAnimal(novoAnimal, tutor);

                System.out.println("Animal cadastrado com sucesso!"); // DEBUG

                // Atualiza a tabela
                tabelaAN.atualizarTabelaAN(getTodosAnimais());

                // Limpa os campos
                txtNome.setText("");
                txtSexo.setText("");
                txtData.setText("");
                txtRaca.setText("");
                txtPelagem.setText("");
                txtPorte.setText("");
                comboTipos.setSelectedIndex(0);
                comboTutores.setSelectedIndex(0);
                radioAcessoNao.setSelected(true);

                // Limpa o painel de campos
                painelCampos.removeAll();
                painelCampos.revalidate();
                painelCampos.repaint();

                // Mostra alerta e volta para a tela da tabela
                new Alerta("Animal cadastrado com sucesso!");
                CartaAN.show(painelPrincipalAN, "Card 1");

            } else {
                // DEBUG DETALHADO
                if (novoAnimal == null) {
                    JOptionPane.showMessageDialog(this, "Erro: Animal não foi criado!");
                    System.out.println("ERRO: novoAnimal é NULL");
                }
                if (tutor == null) {
                    JOptionPane.showMessageDialog(this, "Erro: Tutor não encontrado! Verifique se o CPF está correto.");
                    System.out.println("ERRO: tutor é NULL");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar animal: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}