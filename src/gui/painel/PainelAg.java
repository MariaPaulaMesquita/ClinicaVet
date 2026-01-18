package gui.painel;

import gui.painel.cadastro.inserirAgendamento;
import gui.painel.tabela.TabelaAgendamentos;

import javax.swing.*;
import java.awt.*;

import static cadastro.CadastroAgendamentos.getServicosAgendados;

public class PainelAg extends JPanel {
    public static CardLayout CartaAG = new CardLayout();
    public static JPanel painelPrincipalAG = new JPanel(CartaAG);
    public static TabelaAgendamentos tabelaAG; // Não inicializa aqui!

    public PainelAg(){
        this.setBackground(Color.blue);
        this.setLayout(new BorderLayout());

        this.add(painelPrincipalAG, BorderLayout.CENTER);

        JPanel card1 = criarCard1();
        card1.setLayout(new BorderLayout());

        JPanel card2 = criarCard2();
        card2.setLayout(new BorderLayout());

        painelPrincipalAG.add(card1, "Card 1");
        painelPrincipalAG.add(card2, "Card 2");

        JButton btnCard1 = new JButton("Voltar");
        JButton btnCard2 = new JButton("Cadastrar Novo");

        btnCard1.addActionListener(e -> CartaAG.show(painelPrincipalAG, "Card 1"));
        btnCard2.addActionListener(e -> CartaAG.show(painelPrincipalAG, "Card 2"));

        // Cria a tabela
        tabelaAG = new TabelaAgendamentos(getServicosAgendados());

        //titulo noov fi
        JLabel titulo = new JLabel("Listagem de Agendamentos");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        card1.add(titulo, BorderLayout.NORTH);
        card1.add(tabelaAG, BorderLayout.CENTER);
        card1.add(btnCard2, BorderLayout.SOUTH);

        card2.add(new JLabel("  "), BorderLayout.NORTH);
        card2.add(new inserirAgendamento(), BorderLayout.CENTER);
        card2.add(btnCard1, BorderLayout.SOUTH);
    }

    private JPanel criarCard1() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(179, 189, 255));
        return painel;
    }

    private JPanel criarCard2() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(135, 150, 255));
        return painel;
    }
}