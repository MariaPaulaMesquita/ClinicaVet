package gui.painel;

import gui.painel.cadastro.InserirTutores;
import gui.painel.tabela.TabelaTutores;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroClientes.getTutores;

public class PainelT extends JPanel {

    // CardLayout público para ser usado pelo inserirTutores
    public static CardLayout CartaT;
    public static JPanel painelPrincipalT;

    public PainelT() {
        this.setLayout(new BorderLayout());

        CartaT = new CardLayout();
        painelPrincipalT = new JPanel(CartaT);
        this.add(painelPrincipalT, BorderLayout.CENTER);

        // Cards
        JPanel card1 = criarCard1(); // listagem
        JPanel card2 = criarCard2(); // cadastro

        painelPrincipalT.add(card1, "Card 1");
        painelPrincipalT.add(card2, "Card 2");

        CartaT.show(painelPrincipalT, "Card 1");
    }

    private JPanel criarCard1() {
        JPanel painel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Listagem de Tutores");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCadastrar = new JButton("Cadastrar Novo");
        btnCadastrar.addActionListener(e ->
                CartaT.show(painelPrincipalT, "Card 2")
        );

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(new TabelaTutores(getTutores()), BorderLayout.CENTER);
        painel.add(btnCadastrar, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarCard2() {
        JPanel painel = new JPanel(new BorderLayout());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e ->
                CartaT.show(painelPrincipalT, "Card 1")
        );

        painel.add(new InserirTutores(), BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        return painel;
    }
}
