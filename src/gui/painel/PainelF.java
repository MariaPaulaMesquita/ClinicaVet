package gui.painel;

import gui.painel.cadastro.inserirFuncionario;
import gui.painel.tabela.TabelaFuncionarios;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroFuncionarios.getVeterinarios;

public class PainelF extends JPanel{
    public static CardLayout CartaF = new CardLayout();
    public static JPanel painelPrincipalF = new JPanel(CartaF);
    public static TabelaFuncionarios tabelaF; // NÃO INICIALIZA AQUI

    public PainelF(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        this.add(painelPrincipalF, BorderLayout.CENTER);

        JPanel card1 = criarCard1();
        card1.setLayout(new BorderLayout());

        JPanel card2 = criarCard2();
        card2.setLayout(new BorderLayout());

        painelPrincipalF.add(card1, "Card 1");
        painelPrincipalF.add(card2, "Card 2");

        JButton btnCard1 = new JButton("Voltar");
        JButton btnCard2 = new JButton("Cadastrar Novo");

        btnCard1.addActionListener(e -> CartaF.show(painelPrincipalF, "Card 1"));
        btnCard2.addActionListener(e -> CartaF.show(painelPrincipalF, "Card 2"));

        // CRIA A INSTÂNCIA
        tabelaF = new TabelaFuncionarios(getVeterinarios());

        //titulo novo
        JLabel titulo = new JLabel("Listagem de Funcionários");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        card1.add(titulo, BorderLayout.NORTH);
        card1.add(tabelaF, BorderLayout.CENTER);
        card1.add(btnCard2, BorderLayout.SOUTH);
        card2.add(new JLabel("  "), BorderLayout.NORTH);
        card2.add(new inserirFuncionario(), BorderLayout.CENTER);
        card2.add(btnCard1, BorderLayout.SOUTH);
    }

    private JPanel criarCard1() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(179, 255, 183));
        return painel;
    }

    private JPanel criarCard2() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(129, 216, 132));
        return painel;
    }
}