package gui.painel.cadastro;

import cadastro.CadastroAgendamentos;
import gui.Alerta;
import gui.painel.tabela.TabelaAgendamentos;
import servicos.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cadastro.CadastroAgendamentos.agendarServico;
import static cadastro.CadastroAgendamentos.getServicosAgendados;
import static cadastro.CadastroClientes.pesquisarAnimal;
import static cadastro.CadastroFuncionarios.pesquisarVeterinario;
import static gui.painel.PainelAg.*;

public class inserirAgendamento extends JPanel {
    JPanel painelEntre = new JPanel(new BorderLayout());
    JPanel painelCampos = new JPanel(new GridBagLayout());
    JPanel painelBotao = new JPanel(new FlowLayout());
    ComboBoxServicos seletor = new ComboBoxServicos();

    JTextField txtNome = new JTextField(30);
    JTextField txtDataHoraI = new JTextField(30);
    JTextField txtDataHoraF = new JTextField(30);

    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public inserirAgendamento(){
        this.setLayout(new BorderLayout());

        JButton botaoCadastrar = new JButton("Agendar");

        painelCampos.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 100, 10, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(painelEntre, BorderLayout.CENTER);
        painelEntre.add(seletor, BorderLayout.NORTH);
        painelEntre.add(painelCampos, BorderLayout.CENTER);
        painelEntre.add(painelBotao, BorderLayout.SOUTH);

        //Label com o titulo
        JLabel titulo = new JLabel();
        titulo.setText("Agendamento de novo serviço");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titulo, BorderLayout.NORTH);

        //Botao
        botaoCadastrar.addActionListener(e -> {
            acaoCadastro();
        });
        painelBotao.add(botaoCadastrar);

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtNome.setEditable(true);
        painelCampos.add(txtNome, gbc);

        // DataHoraInicio
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Data e Hora do Início (dd/MM/yyyy HH:mm):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtDataHoraI.setEditable(true);
        painelCampos.add(txtDataHoraI, gbc);

        //DataHoraFinal
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        painelCampos.add(new JLabel("Data e Hora do Fim (dd/MM/yyyy HH:mm):"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtDataHoraF.setEditable(true);
        painelCampos.add(txtDataHoraF, gbc);
    }

    private void acaoCadastro(){
        try{
            String tipoServico = seletor.getServicoSelecionado();

            if (tipoServico == null) {
                JOptionPane.showMessageDialog(this, "Selecione um tipo de serviço!");
                return;
            }

            if (seletor.getAnimalSelecionado() == null) {
                JOptionPane.showMessageDialog(this, "Selecione um animal!");
                return;
            }

            if (seletor.getVeterinarioSelecionado() == null) {
                JOptionPane.showMessageDialog(this, "Selecione um veterinário!");
                return;
            }

            Servico novoServico = null;

            if(tipoServico.equals("Consulta")){
                novoServico = new Consulta(
                        LocalDateTime.parse(txtDataHoraI.getText(), formatador),
                        pesquisarAnimal(seletor.getAnimalSelecionado()),
                        pesquisarVeterinario(seletor.getVeterinarioSelecionado())
                );

            } else if(tipoServico.equals("Cirurgia")){
                if (txtNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o nome da cirurgia!");
                    return;
                }
                novoServico = new Cirurgia(
                        LocalDateTime.parse(txtDataHoraI.getText(), formatador),
                        LocalDateTime.parse(txtDataHoraF.getText(), formatador),
                        pesquisarAnimal(seletor.getAnimalSelecionado()),
                        pesquisarVeterinario(seletor.getVeterinarioSelecionado()),
                        txtNome.getText()
                );

            } else if(tipoServico.equals("Exame")){
                if (txtNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o nome do exame!");
                    return;
                }
                novoServico = new Exame(
                        LocalDateTime.parse(txtDataHoraI.getText(), formatador),
                        LocalDateTime.parse(txtDataHoraF.getText(), formatador),
                        pesquisarAnimal(seletor.getAnimalSelecionado()),
                        pesquisarVeterinario(seletor.getVeterinarioSelecionado()),
                        txtNome.getText()
                );

            } else if(tipoServico.equals("Vacina")){
                if (txtNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Digite o nome da vacina!");
                    return;
                }
                novoServico = new Vacinacao(
                        LocalDateTime.parse(txtDataHoraI.getText(), formatador),
                        pesquisarAnimal(seletor.getAnimalSelecionado()),
                        pesquisarVeterinario(seletor.getVeterinarioSelecionado()),
                        txtNome.getText()
                );
            }

            if (novoServico != null) {
                agendarServico(novoServico);
                CadastroAgendamentos.salvarAgendamentos();

                // Atualiza a tabelaT corretamente
                tabelaAG.atualizarTabelaAG(getServicosAgendados());

                // Limpa os campos
                txtNome.setText("");
                txtDataHoraI.setText("");
                txtDataHoraF.setText("");
                seletor.resetarSelecao();

                // Mostra alerta e volta para a tela da tabelaT
                new Alerta("Agendamento concluído com sucesso!");
                CartaAG.show(painelPrincipalAG, "Card 1");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao agendar serviço: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}