package gui;

import javax.swing.*;
import java.awt.*;

public class Funcionarios extends JFrame {
    public Funcionarios(){
        this.setTitle("Gerenciamento");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(640,480));
        this.setLayout(new BorderLayout());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
