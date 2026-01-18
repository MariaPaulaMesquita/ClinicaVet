package gui.painel.cadastro;

import gui.Alerta;
import pessoas.Tutor;
import pessoas.Tratamento;
import cadastro.CadastroClientes;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroClientes.*;
import static gui.painel.PainelT.CartaT;
import static gui.painel.PainelT.painelPrincipalT;
import static gui.painel.tabela.TabelaTutores.modeloT;
import static gui.painel.tabela.TabelaTutores.tabelaT;

public class InserirTutores extends JPanel {

    JPanel painelEntre = new JPanel(new BorderLayout());
    JPanel painelCampos = new JPanel(new GridBagLayout());
    JPanel painelBotao = new JPanel(new FlowLayout());
    JPanel painelzao = new JPanel(new BorderLayout());

    JTextField txtNome = new JTextField(30);
    JTextField txtCpf = new JTextField(30);
    JTextField txtTelefone = new JTextField(30);
    JTextField txtEndereco = new JTextField(30);

    JComboBox<Tratamento> comboTratamento;

    JButton botaoCadastrar = new JButton("Cadastrar");

    public InserirTutores() {
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Cadastro de novo tutor");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        add(painelzao, BorderLayout.CENTER);
        painelzao.add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(painelCampos, BorderLayout.CENTER);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        montarCampos();

        botaoCadastrar.addActionListener(e -> acaoCadastro());
        painelBotao.add(botaoCadastrar);
    }

    private void montarCampos() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtNome, gbc);

        // CPF
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        painelCampos.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtCpf, gbc);

        // Telefone
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        painelCampos.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtTelefone, gbc);

        // Endereço
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        painelCampos.add(new JLabel("Endereço:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        painelCampos.add(txtEndereco, gbc);

        // Tratamento
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        painelCampos.add(new JLabel("Tratamento:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;

        comboTratamento = new JComboBox<>(Tratamento.values());
        comboTratamento.insertItemAt(null, 0);
        comboTratamento.setSelectedIndex(0);

        painelCampos.add(comboTratamento, gbc);
    }

    private void acaoCadastro() {
        try {
            // Validações
            if (txtNome.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o nome do tutor!");
                return;
            }

            if (txtCpf.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o CPF!");
                return;
            }

            if (txtTelefone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o telefone!");
                return;
            }

            if (txtEndereco.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o endereço!");
                return;
            }

            Tratamento tratamento = (Tratamento) comboTratamento.getSelectedItem();

            Tutor novoTutor = new Tutor(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEndereco.getText(),
                    tratamento
            );

            cadastrarTutor(novoTutor);
            CadastroClientes.salvarTutores();

            // Atualiza tabela de tutores
            Object[] linha = {
                    novoTutor.getNome(),
                    novoTutor.getCpf(),
                    novoTutor.getTelefone(),
                    novoTutor.getEndereco(),
                    novoTutor.getTratamento() != null
                            ? novoTutor.getTratamento().toString()
                            : "Não definido",
                    0,
                    "Nenhum animal"
            };
            modeloT.addRow(linha);

            tabelaT.revalidate();
            tabelaT.repaint();

            // Limpa campos
            txtNome.setText("");
            txtCpf.setText("");
            txtTelefone.setText("");
            txtEndereco.setText("");
            comboTratamento.setSelectedIndex(0);

            // Alerta e volta para listagem
            new Alerta("Tutor cadastrado com sucesso!");
            CartaT.show(painelPrincipalT, "Card 1");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar tutor: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
