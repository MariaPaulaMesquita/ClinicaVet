package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alerta extends JFrame {
    public Alerta(String g){
        this.setSize(200,160);
        this.setResizable(false);
        this.setAutoRequestFocus(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(this);
        this.setVisible(true);

        JPanel painel = new JPanel(new BorderLayout());
        this.add(painel);
        JLabel label = new JLabel(g);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        painel.add(label,BorderLayout.CENTER);
        JButton botao = new JButton("Ok");
        botao.addActionListener(e -> {
            this.dispose();
        });;
        painel.add(botao,BorderLayout.SOUTH);

    }
}
