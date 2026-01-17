package gui.painel.cadastro;

import gui.Alerta;
import gui.painel.tabela.TabelaFuncionarios;
import pessoas.Veterinario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public inserirFuncionario(){
        this.setLayout(new BorderLayout());

        JButton botaoCadastrar = new JButton("Cadastrar");

        painelCampos.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 100, 10, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(painelCampos, BorderLayout.NORTH);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        //Label com o titulo
        JLabel titulo = new JLabel();
        titulo.setText("Cadastro de novo funcionário");
        titulo.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));;
        this.add(titulo,BorderLayout.NORTH);

        //Botao
        botaoCadastrar.addActionListener(e -> {
            acaoCadastro();
        });
        painelBotao.add(botaoCadastrar);

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtNome.setEditable(true);
        painelCampos.add(txtNome, gbc);

        // CPF
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCPF.setEditable(true);
        painelCampos.add(txtCPF, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTelefone.setEditable(true);
        painelCampos.add(txtTelefone, gbc);

        // CRMV
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("CRMV:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCRMV.setEditable(true);
        painelCampos.add(txtCRMV, gbc);

        //DATACONTRATO
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Ano de Contrato:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCRMV.setEditable(true);
        painelCampos.add(txtDC, gbc);

        //DATACONTRATO
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Ano de Formação:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtCRMV.setEditable(true);
        painelCampos.add(txtDF, gbc);

        //TODO O BOTAO PRA CADASTRAR COM BASE NOS DADOS PREENCHIDOS E POPUP DE EXCECAO
    }

    // Método para atualizar a tabela
    public static void atualizarTabelaF(TabelaFuncionarios painelTabela, Set<Veterinario> veterinarios) {
        modelo.setRowCount(0);

        for (Veterinario v : veterinarios) {
            Object[] linha = {
                    v.getNome(),
                    formatarCPF(v.getCpf()),
                    v.getTelefone(),
                    v.getCrmv(),
                    v.getAnoContrato(),
                    v.getAnoFormacao(),
                    "Agendamentos",
                    "Detalhes"
            };
            modelo.addRow(linha);
        }

        // Atualiza apenas o painel inferior
        Component[] components = painelTabela.getComponents();
        for (Component c : components) {
            if (c instanceof JPanel) {
                JPanel p = (JPanel) c;
                if (p.getLayout() instanceof FlowLayout) {
                    p.removeAll();
                    JLabel labelTotal = new JLabel("Total de veterinários: " + veterinarios.size());
                    labelTotal.setFont(new Font("Arial", Font.BOLD, 12));
                    p.add(labelTotal);
                    p.revalidate();
                    p.repaint();
                    break;
                }
            }
        }
    }

    private void acaoCadastro(){
        DefaultTableModel dtmTabelaVet = getModelo();
        try{
            Veterinario vet = new Veterinario(txtNome.getText(),txtCPF.getText(),txtTelefone.getText(),txtCRMV.getText(),Integer.parseInt(txtDC.getText()),Integer.parseInt(txtDF.getText()));
            cadastrarVeterinario(vet);
            Alerta alerta = new Alerta("Cadastro concluido");
            Carta.show(painelPrincipal, "Card 1");
            listarVeterinarios();
            atualizarTabelaF(tabelaF,getVeterinarios());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
