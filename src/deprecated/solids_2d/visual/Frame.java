package deprecated.solids_2d.visual;



import deprecated.solids_2d.Mesh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {

    FEM_Panel panel;

    public static final int STRESS = 0;
    public static final int E_MODUL = 1;
    public static final int DISPLACEMENT = 2;
    public static final int STRESS_X = 3;
    public static final int STRESS_Y = 4;
    public static final int STRESS_XY = 5;
    public static final int DISPLACEMENT_X = 6;
    public static final int DISPLACEMENT_Y = 7;


    public Frame(Mesh mesh) {
        super("FEM Graph");
        setDefaultCloseOperation(3);
        setSize(720,480);
        this.setLayout(new BorderLayout());
        this.panel = new FEM_Panel(mesh);
        this.panel.addKeyListener(this);
        this.addKeyListener(this);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public Frame enableWireframe(){
        panel.wireframe = true;
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
        switch (e.getExtendedKeyCode()){
            case 37: panel.move(-0.1,0);break;
            case 38: panel.move(0,+0.1);break;
            case 39: panel.move(+0.1,0);break;
            case 40: panel.move(0,-0.1);break;

            case 107: panel.zoom(0.8); break;
            case 109: panel.zoom(1.25); break;

            case 87: panel.wireframe = !panel.wireframe; break; //w
            case 66: panel.renderBCs = !panel.renderBCs; break; //b
            case 73: panel.renderIDs = !panel.renderIDs; break; //i

            case 49: panel.renderMode = Frame.STRESS; this.setTitle("Stress2D"); break;
            case 50: panel.renderMode = Frame.DISPLACEMENT;this.setTitle("Displacement"); break;
            case 51: panel.renderMode = Frame.E_MODUL;this.setTitle("E-Modul"); break;
            case 52: panel.renderMode = Frame.STRESS_X; this.setTitle("Stress2D (x)"); break;
            case 53: panel.renderMode = Frame.STRESS_Y;this.setTitle("Stress2D (y)"); break;
            case 54: panel.renderMode = Frame.STRESS_XY;this.setTitle("Stress2D (xy)"); break;
            case 55: panel.renderMode = Frame.DISPLACEMENT_X;this.setTitle("Displacement (x)"); break;
            case 56: panel.renderMode = Frame.DISPLACEMENT_Y;this.setTitle("Displacement (y)"); break;

            case 82: panel.lower_scaler *= 0.6; break; //r
            case 84: panel.lower_scaler *= 1/0.6; break; //t
            case 90: panel.upper_scalar *= 0.6; break; //z
            case 85: panel.upper_scalar *= 1/0.6; break; //u
        }
        System.out.println(e.getExtendedKeyCode());

        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
