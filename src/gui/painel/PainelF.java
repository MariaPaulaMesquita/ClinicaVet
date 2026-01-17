package gui.painel;

import gui.painel.tabela.TabelaFuncionarios;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroFuncionarios.getVeterinarios;


public class PainelF extends JPanel{
    public PainelF(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        CardLayout Carta = new CardLayout();
        JPanel painelPrincipal = new JPanel(Carta);
        this.add(painelPrincipal, BorderLayout.CENTER);

        //JPanel y = new JPanel();
        JPanel z = new JPanel();

        JPanel card1 = criarCard1();
        card1.setLayout(new BorderLayout());

        JPanel card2 = criarCard2();
        card2.setLayout(new BorderLayout());

        painelPrincipal.add(card1, "Card 1");
        painelPrincipal.add(card2, "Card 2");

        JButton btnCard1 = new JButton("Voltar");
        JButton btnCard2 = new JButton("Cadastrar Novo");

        btnCard1.addActionListener(e -> Carta.show(painelPrincipal, "Card 1"));
        btnCard2.addActionListener(e -> Carta.show(painelPrincipal, "Card 2"));

        card1.add(new JLabel("Listagem"),BorderLayout.NORTH);
        card1.add(new TabelaFuncionarios(getVeterinarios()), BorderLayout.CENTER);
        card1.add(btnCard2,BorderLayout.SOUTH);
        card2.add(new JLabel("Cadastro"),BorderLayout.NORTH);
        card2.add(z, BorderLayout.CENTER);
        card2.add(btnCard1,BorderLayout.SOUTH);
        //TODO a tela de cadastro e implementar a tabela
    }

    private JPanel criarCard1() {
        JPanel painel = new JPanel();
        painel.setBackground(Color.PINK);
        return painel;
    }

    private JPanel criarCard2() {
        JPanel painel = new JPanel();
        painel.setBackground(Color.CYAN);
        return painel;
    }


}
