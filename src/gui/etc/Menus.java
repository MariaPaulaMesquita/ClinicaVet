package gui.etc;

import javax.swing.*;
import java.awt.*;

public class Menus extends JMenuBar {
    public Menus(){
        JMenu testeMenu = new JMenu("teste");
        this.setLayout(new BorderLayout());
        this.add(testeMenu, BorderLayout.EAST);

        this.setVisible(true);
    }
}
