package gui.botao;

import gui.Agendamentos;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class verAgendamentos extends JButton {
    public verAgendamentos(){
        //ImageIcon pata = new ImageIcon("src/gui/img/paw.png");
        this.setText("Agendamentos");
//        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(0));
        this.setBackground(new Color(179, 189, 255));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Agendamentos = new Agendamentos();
            }
        });
    }
}
