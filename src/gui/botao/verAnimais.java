package gui.botao;

import gui.Animais;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verAnimais extends JButton {
    public verAnimais(){
        //ImageIcon pata = new ImageIcon("src/gui/img/paw.png");
        this.setText("Animais");
//        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(0));
        this.setBackground(new Color(255, 179, 179));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Animais = new Animais();
            }
        });

    }

};

