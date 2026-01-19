package gui.botao;

import gui.Funcionarios;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static cadastro.CadastroFuncionarios.getVeterinarios;
import static gui.painel.PainelF.tabelaF;
import static gui.painel.cadastro.inserirFuncionario.atualizarTabelaF;

public class verFuncionarios extends JButton {
    public verFuncionarios(){
        //ImageIcon pata = new ImageIcon("src/gui/img/paw.png");
        this.setText("Funcionários");
//        this.setFocusable(false);
        this.setBorder(new SoftBevelBorder(0));
        this.setBackground(new Color(179, 255, 183));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame Funcionarios = new Funcionarios();
                atualizarTabelaF(tabelaF,getVeterinarios());
            }
        });
    }
}
