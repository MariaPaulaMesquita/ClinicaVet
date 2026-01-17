package gui;

import gui.painel.PainelAg;

import javax.swing.*;
import java.awt.*;

public class Agendamentos extends JFrame {
    public Agendamentos(){
        this.setTitle("Gerenciamento");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(640, 480));
        this.setSize(1280, 720);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.add(new PainelAg());
        this.setVisible(true);
    }
}
