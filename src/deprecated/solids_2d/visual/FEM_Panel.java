package deprecated.solids_2d.visual;


import core.vector.Vector2d;
import deprecated.solids_2d.Mesh;
import deprecated.solids_2d.Node;
import deprecated.solids_2d.elements.FiniteElement2D;
import deprecated.solids_2d.elements.Triangle;
import visuals.ColorPalette;
import visuals.Panel;

import java.awt.*;

public class FEM_Panel extends Panel {

    public int renderMode;
    public boolean renderIDs;
    public boolean renderBCs;
    private Mesh mesh;

    boolean wireframe;
    double upper_scalar = 1, lower_scaler = 1;


    public FEM_Panel(Mesh mesh) {
        this.setMesh(mesh);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
        double min_x, max_x, min_y, max_y;
        min_x = 100000000;
        max_x = -100000000;
        min_y = 100000000;
        max_y = -100000000;

        for (Node n : mesh.getNodes()) {
            if (n.getPosition().getX() < min_x) min_x = n.getPosition().getX();
            if (n.getPosition().getY() < min_y) min_y = n.getPosition().getY();

            if (n.getPosition().getX() > max_x) max_x = n.getPosition().getX();
            if (n.getPosition().getY() > max_y) max_y = n.getPosition().getY();
        }

        double center_x = (max_x - min_x) / 2;
        double center_y = (max_y - min_y) / 2;

        double zoom = 1.3;

        max_x = center_x - (center_x - max_x) * zoom;
        min_x = center_x - (center_x - min_x) * zoom;
        max_y = center_y - (center_y - max_y) * zoom;
        min_y = center_y - (center_y - min_y) * zoom;

        this.setCenter(new Vector2d(center_x, center_y));
        this.setScale(new Vector2d(max_x - min_x, max_y - min_y));


    }


    public Vector2d moved_position(Node a) {
        return a.getPosition().add(a.getDisplacement());
        //return a.getPosition();
    }

    private double value_to_display(FiniteElement2D e) {

        Vector2d avg = new Vector2d();
        for (Node n : e.getNodes()) {
            avg.self_add(n.getDisplacement());
        }
        avg.self_scale(e.getNodes().length);

        try {
            switch (renderMode) {
                case deprecated.solids_2d.visual.Frame.STRESS:
                    return e.getEvaluated_stress().comparable_stress();
                case deprecated.solids_2d.visual.Frame.E_MODUL:
                    return e.getMaterial().getYoung();
                case deprecated.solids_2d.visual.Frame.DISPLACEMENT: {
                    return avg.length();
                }
                case deprecated.solids_2d.visual.Frame.STRESS_X: return e.getEvaluated_stress().getSigma_x();
                case deprecated.solids_2d.visual.Frame.STRESS_Y: return e.getEvaluated_stress().getSigma_y();
                case deprecated.solids_2d.visual.Frame.STRESS_XY: return e.getEvaluated_stress().getTau();
                case deprecated.solids_2d.visual.Frame.DISPLACEMENT_X: return avg.getX();
                case Frame.DISPLACEMENT_Y: return avg.getY();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    @Override
    public void paintComponent(Graphics g) {

        g.clearRect(0, 0, this.getWidth(), this.getHeight());


        double max_val = Double.NEGATIVE_INFINITY;
        double min_val = Double.POSITIVE_INFINITY;
        for (FiniteElement2D e : mesh.getElements()) {
            double k = value_to_display(e);
            min_val = Math.min(k,min_val);
            max_val = Math.max(k, max_val);
        }

        max_val = max_val * this.upper_scalar;
        min_val = min_val * this.lower_scaler;

        ColorPalette.RAINBOW.drawColorPalette((Graphics2D)g, min_val, max_val, 10,300);

        ((Graphics2D) g).setStroke(new BasicStroke(1));
        for (int i = 0; i < mesh.getElements().size(); i++) {
            FiniteElement2D e = mesh.getElements().get(i);
            if (e instanceof Triangle) {


                Vector2d a1 = toScreenSpace(moved_position(e.getNodes()[0]));
                Vector2d a2 = toScreenSpace(moved_position(e.getNodes()[1]));
                Vector2d a3 = toScreenSpace(moved_position(e.getNodes()[2]));

                Vector2d center = a1.add(a2).add(a3).self_scale(1/3d);

                int[] x_points = new int[]{(int) (double) a1.getX(), (int) (double) a2.getX(), (int) (double) a3.getX()};
                int[] y_points = new int[]{(int) (double) a1.getY(), (int) (double) a2.getY(), (int) (double) a3.getY()};



                if (max_val != 0) {
                    double fac = 0;
                    if(max_val != min_val){
                        fac = Math.max(0,Math.min(1, (value_to_display(e) - min_val) / (max_val - min_val)));

                    }
                    Color c = ColorPalette.RAINBOW.apply((float) fac);
                    g.setColor(c);
                    g.fillPolygon(x_points, y_points, 3);
                }

                if (wireframe) {
                    g.setColor(Color.black);
                    g.drawPolygon(x_points, y_points, 3);
                }



            }
        }

//        for (int i = 0; i < mesh.getElements().size(); i++) {
//            FiniteElement2D e = mesh.getElements().get(i);
//            if (e instanceof Triangle) {
//
//
//                Vector2d a1 = toScreenSpace(moved_position(e.getNodes()[0]));
//                Vector2d a2 = toScreenSpace(moved_position(e.getNodes()[1]));
//                Vector2d a3 = toScreenSpace(moved_position(e.getNodes()[2]));
//
//                Vector2d center = a1.add(a2).add(a3).self_scale(1 / 3d);
//
//                Stress2D s = e.getEvaluated_stress();
//                double a = s.mohr_angle();
//                g.setColor(Color.black);
//                draw_arrow((Graphics2D)g, center, center.add(new Vector2d(30 * Math.sin(a), 30 * Math.cos(a))),3,10,10);
//            }
//        }

        for (int i = 0; i < mesh.getNodes().size(); i++) {
            Node node = mesh.getNodes().get(i);
            Vector2d a1 = toScreenSpace(moved_position(node));

            if (this.renderIDs) {

                g.setColor(Color.green);
                g.drawString(i + "", (int) (double) a1.getX(), (int) (double) a1.getY());
            }
            if (this.renderBCs) {

                g.setColor(Color.black);
                if (node.getSupport().getX() != 0) {
                    draw_arrow((Graphics2D) g, a1.sub(new Vector2d(60, 0)), a1, 10, 25, 25);
                }
                if (node.getSupport().getY() != 0) {
                    draw_arrow((Graphics2D) g, a1.sub(new Vector2d(0, 60)), a1, 10, 25, 25);
                }
                if (node.getForce() != null) {
                    draw_arrow((Graphics2D) g, a1.add(new Vector2d(node.getForce().self_normalise().self_scale(60))), a1, 10, 25, 25);
                }
            }
        }

    }


}
