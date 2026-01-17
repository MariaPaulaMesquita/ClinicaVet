package gui;

import gui.painel.PainelT;

import javax.swing.*;
import java.awt.*;

public class Tutores extends JFrame {
    public Tutores(){
        this.setTitle("Gerenciamento");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(640, 480));
        this.setSize(1280, 720);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.add(new PainelT());
        this.setVisible(true);
    }
}
