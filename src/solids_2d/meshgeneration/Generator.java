package solids_2d.meshgeneration;

import core.Edge;
import core.vector.Vector2d;
import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;

import java.util.ArrayList;

public class Generator {

    /**
     * first index = x
     * second index = y
     *
     * @param nodes
     * @return
     */
    public static Mesh connect_rectangular_nodes(Node[][] nodes) {
        ArrayList<FiniteElement2D> triangles = new ArrayList<>();
        ArrayList<Node> nodes_list = new ArrayList<>();

        Edge[][][] edges = new Edge[nodes.length - 1][nodes[0].length - 1][5];

        //generating edges
        for (int i = 0; i < nodes.length - 1; i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                if ((n + i) % 2 == 0) {
                    edges[i][n] = new Edge[]{
                            new Edge(nodes[i][n], nodes[i][n+1]),
                            new Edge(nodes[i][n+1], nodes[i+1][n+1]),
                            new Edge(nodes[i+1][n+1], nodes[i+1][n]),
                            new Edge(nodes[i+1][n], nodes[i][n]),
                            new Edge(nodes[i][n], nodes[i+1][n+1]),
                            new Edge(nodes[i][n], nodes[i+1][n+1])};
                }else{
                    edges[i][n] = new Edge[]{
                            new Edge(nodes[i][n], nodes[i][n+1]),
                            new Edge(nodes[i][n+1], nodes[i+1][n+1]),
                            new Edge(nodes[i+1][n+1], nodes[i+1][n]),
                            new Edge(nodes[i+1][n], nodes[i][n]),
                            new Edge(nodes[i+1][n], nodes[i][n+1]),
                            new Edge(nodes[i+1][n], nodes[i][n+1])};
                }

            }
        }

        //linking edges
        for (int i = 0; i < nodes.length - 1; i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                edges[i][n][4].link(edges[i][n][5]);
                if(n > 0){
                    edges[i][n][3].link(edges[i][n-1][1]);
                }
                if(i > 0){
                    edges[i][n][0].link(edges[i-1][n][2]);
                }
            }
        }

        //generating faces
        for (int i = 0; i < nodes.length - 1; i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                if ((n + i) % 2 == 0) {
                    triangles.add(new Triangle(edges[i][n][0], edges[i][n][1], edges[i][n][5]));
                    triangles.add(new Triangle(edges[i][n][2], edges[i][n][3], edges[i][n][4]));
                }else{
                    triangles.add(new Triangle(edges[i][n][0], edges[i][n][3], edges[i][n][4]));
                    triangles.add(new Triangle(edges[i][n][1], edges[i][n][2], edges[i][n][5]));
                }
            }
        }

        Mesh m = new Mesh(triangles);
        return m;
    }

    public static Node[][] arc_mesh_nodes(double lever_length, double r_min, double r_max, double degrees, int subd_l, int subd_w) {
        Node[][] nodes = new Node[subd_l + 1][subd_w + 1];
        for (int i = 0; i < subd_l + 1; i++) {
            for (int n = 0; n < subd_w + 1; n++) {
                double radius = n / (double) subd_w * (r_max - r_min) + r_min;
                double x = i / (double) subd_l;
                Vector2d arc = arc_function(lever_length, radius, r_max, degrees, x);
                nodes[i][n] = new Node(arc.getX(), arc.getY());
            }
        }
        return nodes;
    }

    public static Mesh arc_mesh(double lever_length, double r_min, double r_max, double degrees, int subd_l, int subd_w) {
        return Generator.connect_rectangular_nodes(arc_mesh_nodes(lever_length, r_min, r_max, degrees, subd_l, subd_w));
    }

    public static Vector2d arc_function(double lever_length, double radius, double ref_radius, double degrees, double x) {
        double arc_l = ref_radius * Math.PI * degrees / 180;
        double total_l = lever_length * 2 + arc_l;
        if (x < lever_length / total_l) {
            return new Vector2d(-radius, -(lever_length - x * total_l));
        } else if (x < 1 - lever_length / total_l) {
            double angle = (x - lever_length / total_l) * total_l / arc_l * degrees;
            return new Vector2d(radius * -Math.cos(Math.toRadians(angle)), radius * Math.sin(Math.toRadians(angle)));
        } else {
            Vector2d start = new Vector2d(radius * -Math.cos(Math.toRadians(degrees)), radius * Math.sin(Math.toRadians(degrees)));
            Vector2d direction = new Vector2d(Math.sin(Math.toRadians(degrees)), Math.cos(Math.toRadians(degrees)));
            double part = (x - (1 - lever_length / total_l)) * total_l / lever_length;
            return new Vector2d(start.add(direction.self_scale(part * lever_length)));

        }
    }

    public static Node[][] rectangle_mesh_nodes(double w, double h, int subd_w, int subd_h) {

        Node[][] nodes = new Node[subd_w + 1][subd_h + 1];

        for (int n = 0; n < subd_h + 1; n++) {
            for (int i = 0; i < subd_w + 1; i++) {
                Node node = new Node((double) w / (subd_w) * i, (double) h / (subd_h) * n);
                nodes[i][n] = node;

            }
        }

        return nodes;
    }

    public static Mesh rectangle_mesh(double w, double h, int subd_w, int subd_h) {

        return Generator.connect_rectangular_nodes(rectangle_mesh_nodes(w,h,subd_w,subd_h));
    }


}
