package gui.painel;

import gui.botao.verAgendamentos;
import gui.botao.verAnimais;
import gui.botao.verFuncionarios;
import gui.botao.verTutores;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Painel1 extends JPanel {
    public Painel1(){
        this.setBackground(Color.gray);
        this.setLayout(new GridLayout(2,2,25,25));
        EmptyBorder bordaVazia = new EmptyBorder(25, 25, 25, 25);
        LineBorder bordaCor = new LineBorder(Color.white, 25);
        EtchedBorder bordaEncravada = new EtchedBorder();
        CompoundBorder borda1 = new CompoundBorder(bordaCor,bordaEncravada);
        CompoundBorder borda2 = new CompoundBorder(borda1, bordaVazia);

        this.setBorder(borda2);
        this.add(new verAnimais());
        this.add(new verAgendamentos());
        this.add(new verFuncionarios());
        this.add(new verTutores());

    }
}
