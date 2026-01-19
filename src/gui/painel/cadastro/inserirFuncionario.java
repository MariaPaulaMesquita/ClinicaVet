package gui.painel.cadastro;

import cadastro.CadastroFuncionarios;
import gui.Alerta;
import pessoas.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static cadastro.CadastroFuncionarios.*;
import static gui.painel.PainelF.*;
import static gui.painel.tabela.TabelaFuncionarios.*;

public class inserirFuncionario extends JPanel {

    JPanel painelEntre = new JPanel(new BorderLayout());
    JPanel painelCampos = new JPanel(new GridBagLayout());
    JPanel painelBotao = new JPanel(new FlowLayout());

    JTextField txtNome = new JTextField(30);
    JTextField txtCPF = new JTextField(30);
    JTextField txtTelefone = new JTextField(30);
    JTextField txtCRMV = new JTextField(30);
    JTextField txtDC = new JTextField(30);
    JTextField txtDF = new JTextField(30);

    public inserirFuncionario() {
        setLayout(new BorderLayout());

        JButton botaoCadastrar = new JButton("Cadastrar");

        painelCampos.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 100, 10, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(painelCampos, BorderLayout.NORTH);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        JLabel titulo = new JLabel("Cadastro de novo funcionário");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        botaoCadastrar.addActionListener(e -> acaoCadastro());
        painelBotao.add(botaoCadastrar);

        // Campos
        addCampo("Nome:", txtNome, gbc, 0);
        addCampo("CPF:", txtCPF, gbc, 1);
        addCampo("Telefone:", txtTelefone, gbc, 2);
        addCampo("CRMV:", txtCRMV, gbc, 3);
        addCampo("Ano de Contrato:", txtDC, gbc, 4);
        addCampo("Ano de Formação:", txtDF, gbc, 5);
    }

    private void addCampo(String label, JTextField campo, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        painelCampos.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        painelCampos.add(campo, gbc);
    }

    private void acaoCadastro() {
        try {
            // Validação manual simples (UX)
            if (txtNome.getText().isBlank()) {
                throw new IllegalArgumentException("Digite o nome do veterinário!");
            }
            if (txtCPF.getText().isBlank()) {
                throw new IllegalArgumentException("Digite o CPF!");
            }
            if (txtCRMV.getText().isBlank()) {
                throw new IllegalArgumentException("Digite o CRMV!");
            }
            if (txtDC.getText().isBlank()) {
                throw new IllegalArgumentException("Digite o ano de contrato!");
            }
            if (txtDF.getText().isBlank()) {
                throw new IllegalArgumentException("Digite o ano de formação!");
            }

            Veterinario vet = new Veterinario(
                    txtNome.getText(),
                    txtCPF.getText(),
                    txtTelefone.getText(),
                    txtCRMV.getText(),
                    Integer.parseInt(txtDC.getText()),
                    Integer.parseInt(txtDF.getText())
            );

            cadastrarVeterinario(vet);
            CadastroFuncionarios.salvarVeterinarios();

            tabelaF.atualizarTabelaF();

            limparCampos();

            new Alerta("Veterinário cadastrado com sucesso!");
            CartaF.show(painelPrincipalF, "Card 1");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ano deve ser numérico!");
            txtDC.requestFocus();

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

            if (e.getMessage().contains("nome")) txtNome.requestFocus();
            else if (e.getMessage().contains("CPF")) txtCPF.requestFocus();
            else if (e.getMessage().contains("CRMV")) txtCRMV.requestFocus();
            else if (e.getMessage().contains("contrato")) txtDC.requestFocus();
            else if (e.getMessage().contains("formação")) txtDF.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar veterinário: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /*private void atualizarTabelaF() {
        Set<Veterinario> vets = getVeterinarios();
        modelo.setRowCount(0);

        for (Veterinario v : vets) {
            Object[] linha = {
                    v.getNome(),
                    formatarCPF(v.getCpf()),
                    v.getTelefone(),
                    v.getCrmv(),
                    v.getAnoContrato(),
                    v.getAnoFormacao(),
                    "Detalhes"
            };
            modelo.addRow(linha);
        }
    }
    */

    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtTelefone.setText("");
        txtCRMV.setText("");
        txtDC.setText("");
        txtDF.setText("");
    }
}
