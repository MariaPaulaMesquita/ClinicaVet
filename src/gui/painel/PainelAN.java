package gui.painel;

import gui.painel.cadastro.inserirAnimal;
import gui.painel.tabela.TabelaAnimais;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroClientes.getTodosAnimais;

public class PainelAN extends JPanel {
    public static TabelaAnimais tabelaAN; // NÃO INICIALIZA AQUI
    public static CardLayout CartaAN = new CardLayout();
    public static JPanel painelPrincipalAN = new JPanel(CartaAN);

    public PainelAN(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        this.add(painelPrincipalAN, BorderLayout.CENTER);

        JPanel card1 = criarCard1();
        card1.setLayout(new BorderLayout());

        JPanel card2 = criarCard2();
        card2.setLayout(new BorderLayout());

        painelPrincipalAN.add(card1, "Card 1");
        painelPrincipalAN.add(card2, "Card 2");

        JButton btnCard1 = new JButton("Voltar");
        JButton btnCard2 = new JButton("Cadastrar Novo");

        btnCard1.addActionListener(e -> CartaAN.show(painelPrincipalAN, "Card 1"));
        btnCard2.addActionListener(e -> CartaAN.show(painelPrincipalAN, "Card 2"));

        // CRIA A INSTÂNCIA
        tabelaAN = new TabelaAnimais(getTodosAnimais());

        //titulo novo
        JLabel titulo = new JLabel("Listagem de Animais");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        card1.add(titulo, BorderLayout.NORTH);
        card1.add(tabelaAN, BorderLayout.CENTER);
        card1.add(btnCard2, BorderLayout.SOUTH);
        card2.add(new JLabel("  "), BorderLayout.NORTH);
        card2.add(new inserirAnimal(), BorderLayout.CENTER);
        card2.add(btnCard1, BorderLayout.SOUTH);
    }

    private JPanel criarCard1() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(255, 179, 179));
        return painel;
    }

    private JPanel criarCard2() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(225, 145, 145));
        return painel;
    }
}