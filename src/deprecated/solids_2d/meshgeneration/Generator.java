package deprecated.solids_2d.meshgeneration;

import core.vector.Vector2d;
import deprecated.solids_2d.Mesh;
import deprecated.solids_2d.Node;
import deprecated.solids_2d.elements.FiniteElement2D;
import deprecated.solids_2d.elements.Triangle;

import java.util.ArrayList;

public class Generator {

    public static Mesh connect_rectangular_nodes(Node[][] nodes){
        ArrayList<FiniteElement2D> triangles = new ArrayList<>();
        ArrayList<Node> nodes_list = new ArrayList<>();
        for (int n = 0; n < nodes[0].length; n++) {
            for (int i = 0; i < nodes.length; i++) {
                Node node = nodes[i][n];
                nodes_list.add(node);
                if (n != 0) {
                    if (i > 0) {
                        if((n + i) % 2 == 0){
                            triangles.add(new Triangle(nodes[i][n], nodes[i - 1][n], nodes[i - 1][n - 1],1));
                            triangles.add(new Triangle(nodes[i][n], nodes[i - 1][n - 1], nodes[i][n - 1],1));
                        }else{
                            triangles.add(new Triangle(nodes[i][n], nodes[i - 1][n], nodes[i][n - 1],1));
                            triangles.add(new Triangle(nodes[i-1][n], nodes[i - 1][n - 1], nodes[i][n - 1],1));
                        }
                    }
                }
            }
        }
        return new Mesh(nodes_list, triangles);
    }


    public static Mesh arc_mesh(double lever_length, double r_min, double r_max, double degrees, int subd_l, int subd_w){
        Node[][] nodes = new Node[subd_l + 1][subd_w + 1];
        for(int i = 0; i < subd_l + 1; i++){
            for(int n = 0; n < subd_w + 1; n++){
                double radius = n / (double)subd_w * (r_max - r_min) + r_min;
                double x = i / (double)subd_l;
                nodes[i][n] = new Node(arc_function(lever_length, radius, r_max, degrees, x));
            }
        }
        return Generator.connect_rectangular_nodes(nodes);
    }

    public static Vector2d arc_function(double lever_length, double radius, double ref_radius, double degrees, double x){
        double arc_l = ref_radius * Math.PI * degrees / 180;
        double total_l = lever_length * 2 + arc_l;
        if(x < lever_length / total_l){
            return new Vector2d(-radius, -(lever_length - x * total_l));
        }else if (x < 1- lever_length / total_l){
            double angle = (x - lever_length / total_l) * total_l / arc_l * degrees;
            return new Vector2d(radius* -Math.cos(Math.toRadians(angle)), radius * Math.sin(Math.toRadians(angle)));
        }else{
            Vector2d start =  new Vector2d(radius*-Math.cos(Math.toRadians(degrees)),radius* Math.sin(Math.toRadians(degrees)));
            Vector2d direction =  new Vector2d(Math.sin(Math.toRadians(degrees)),Math.cos(Math.toRadians(degrees)));
            double part = (x - (1 - lever_length / total_l)) * total_l / lever_length;
            return new Vector2d(start.add(direction.self_scale(part * lever_length)));

        }
    }

    /**
     * (subd_w+1)(subd_h+1)-1
     * x---x---x-
     * |   |   |
     * |   |   |
     * x---x---x-
     * |   |   |
     * |   |   |
     * x---x---x-
     * 0   1   .. subd_w
     *
     * @param w
     * @param h
     * @param subd_w
     * @param subd_h
     * @return
     */
    public static Mesh rectangle_mesh(double w, double h, int subd_w, int subd_h) {

        Node[][] nodes = new Node[subd_w + 1][subd_h + 1];

        for (int n = 0; n < subd_h + 1; n++) {
            for (int i = 0; i < subd_w + 1; i++) {
                Node node = new Node(new Vector2d((double) w / (subd_w) * i, (double) h / (subd_h) * n));
                nodes[i][n] = node;

            }
        }

        return Generator.connect_rectangular_nodes(nodes);
    }



}
