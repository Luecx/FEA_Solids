package beams_2d.visual;

import beams_2d.arrays.Array2D;
import beams_2d.arrays.vectors.Vector;
import beams_2d.arrays.vectors.Vector2d;
import beams_2d.model.Connection;
import beams_2d.model.Node;
import beams_2d.model.dim_2.Graph2D;
import beams_2d.solver.GaussianElimination;

import javax.swing.*;
import java.awt.*;

public class FEM_Panel extends JPanel {

    private Graph2D graph2D;

    double min_x = 100000000,  max_x = -100000000,  min_y = 100000000,  max_y = -100000000;

    Vector displacement;

    public FEM_Panel(Graph2D graph2D) {
        this.setGraph2D(graph2D);

        Vector displacement = graph2D.generate_displacement_vector();
        Vector force = graph2D.generate_force_vector();
        Array2D stiffness = graph2D.generate_stiffness_matrix();
        GaussianElimination.lsolve(stiffness, displacement, force);

        GaussianElimination.lsolve(stiffness, displacement, force);
        System.out.println(force);
        System.out.println(displacement);

        this.displacement = displacement;
    }

    public Graph2D getGraph2D() {
        return graph2D;
    }

    public void setGraph2D(Graph2D graph2D) {
        this.graph2D = graph2D;
        min_x = 100000000;
        max_x = -100000000;
        min_y = 100000000;
        max_y = -100000000;
        for(Node<Vector2d> n:graph2D.getNodes()){

            if(n.getPosition().getX() < min_x) min_x = n.getPosition().getX();
            if(n.getPosition().getY() < min_y) min_y = n.getPosition().getY();

            if(n.getPosition().getX() > max_x) max_x = n.getPosition().getX();
            if(n.getPosition().getY() > max_y) max_y = n.getPosition().getY();
        }
        min_x--;
        min_y--;
        max_x++;
        max_y++;
    }

    private Vector2d toScreenSpace(Vector2d vector2d){
        return new Vector2d(
                this.getWidth() * ((double) (vector2d.getX() - min_x) / (double)(max_x-min_x)),
                this.getHeight() * (1 - (double) (vector2d.getY() - min_y) / (double)(max_y-min_y))
        ) ;
    }

    public Vector2d moved_position(Node a){

        double d_x = displacement.getValue(a.getIndex() * 2);
        double d_y = displacement.getValue(a.getIndex() * 2 + 1);

//        d_x = Math.abs(d_x) > 1 ? 0:d_x;
//        d_y = Math.abs(d_y) > 1 ? 0:d_y;

        return new Vector2d(
                a.getPosition().getValue(0) + d_x ,
                a.getPosition().getValue(1) + d_y) ;
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("...");
        g.clearRect(0,0,this.getWidth(), this.getHeight());


        for(Connection c:graph2D.getConnections()){
            g.setColor(Color.black);
            Vector2d a = toScreenSpace((Vector2d) c.getNodeA().getPosition());
            Vector2d b = toScreenSpace((Vector2d) c.getNodeB().getPosition());
            g.drawLine((int)a.getX(), (int)a.getY(), (int)b.getX(), (int)b.getY());

//            g.drawString(c.getNodeA().getIndex()+"", (int)a.getX(), (int)a.getY());
//            g.drawString(c.getNodeB().getIndex()+"", (int)b.getX(), (int)b.getY());

            g.setColor(Color.red);
            Vector2d a_moved = toScreenSpace(moved_position(c.getNodeA()));
            Vector2d b_moved = toScreenSpace(moved_position(c.getNodeB()));
            g.drawLine((int)a_moved.getX(), (int)a_moved.getY(), (int)b_moved.getX(), (int)b_moved.getY());
        }
    }
}
