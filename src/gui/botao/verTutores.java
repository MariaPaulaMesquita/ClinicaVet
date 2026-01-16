package gui.botao;

import gui.Tutores;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verTutores extends JButton {
    public verTutores(){
        //ImageIcon pata = new ImageIcon("src/gui/img/paw.png");
        this.setText("Tutores");
//        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(0));
        this.setBackground(new Color(255, 239, 179));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Tutores = new Tutores();
            }
        });

    }
}
