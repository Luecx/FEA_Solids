package solids_2d.visual;



import solids_2d.Mesh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener{

    public FEM_Panel panel;


    public Frame(Mesh mesh) {
        super("FEM Graph");
        setDefaultCloseOperation(3);
        setSize(1000,1000);
        this.setLayout(new BorderLayout());
        this.panel = new FEM_Panel(mesh);
        //this.setUndecorated(true);
        this.addKeyListener(panel);
        this.addMouseListener(panel);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Frame enableWireframe(){
        panel.renderWireframe = true;
        return this;
    }


    public Frame renderBoundaryConditions() {
        panel.renderBCs = true;
        return this;
    }

    public Frame renderIDs() {
        panel.renderIDs = true;
        return this;
    }

    public Frame renderMode(int mode){
        panel.renderMode = mode;
        return this;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        panel.process_key_event(e.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
