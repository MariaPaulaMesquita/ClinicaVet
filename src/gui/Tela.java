package gui;

import gui.etc.Menus;
import gui.painel.Painel1;

import javax.swing.*;
import java.awt.*;

public class Tela extends JFrame {
    public Tela(){
        this.setTitle("Gerenciamento");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(640,480));
        this.setLayout(new BorderLayout());
        this.add(new Painel1(),BorderLayout.CENTER);
        this.add(new Menus(),BorderLayout.NORTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
