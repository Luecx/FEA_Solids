package beams_2d.visual;

import beams_2d.arrays.vectors.Vector2d;
import beams_2d.model.Force;
import beams_2d.model.Support;
import beams_2d.model.dim_2.Graph2D;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(Graph2D graph2D) {
        super("FEM Graph");
        setDefaultCloseOperation(3);
        setSize(720,480);
        this.setLayout(new BorderLayout());
        FEM_Panel panel = new FEM_Panel(graph2D);
        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Graph2D graph = Graph2D.rectangle(1,1,4,4);
        graph.getNodes().get(0).setSupport(new Support<Vector2d>(new Vector2d(1,1)));
        graph.getNodes().get(5 * 5 - 1).setSupport(new Support<Vector2d>(new Vector2d(1,1)));
        graph.getNodes().get(4).setForce(new Force<Vector2d>(new Vector2d(30000000,30000000)));
        new Frame(graph);
    }
}
