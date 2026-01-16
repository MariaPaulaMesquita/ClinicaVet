package gui.painel;

import gui.botao.verAgendamentos;
import gui.botao.verAnimais;
import gui.botao.verFuncionarios;
import gui.botao.verTutores;

import javax.swing.*;
import java.awt.*;

public class Painel1 extends JPanel {
    public Painel1(){
        this.setBackground(Color.gray);
        this.setLayout(new GridLayout(2,2,25,25));
        this.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        this.add(new verAnimais());
        this.add(new verAgendamentos());
        this.add(new verFuncionarios());
        this.add(new verTutores());

    }
}
