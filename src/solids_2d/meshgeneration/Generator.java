package solids_2d.meshgeneration;

import core.Edge;
import core.vector.Vector2d;
import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;
import solids_2d.visual.Frame;

import java.util.ArrayList;

public class Generator {

    /**
     * first index = x
     * second index = y
     *
     * @param nodes
     * @return
     */
    public static Mesh connect_rectangular_nodes(Node[][] nodes, boolean loopX) {
        ArrayList<FiniteElement2D> triangles = new ArrayList<>();
        ArrayList<Node> nodes_list = new ArrayList<>();

        Edge[][][] edges = new Edge[nodes.length - 1 + (loopX ? 1:0)][nodes[0].length - 1][];

        //generating edges
        for (int i = 0; i < nodes.length - 1 + (loopX ? 1:0); i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                if ((n + i) % 2 == 0) {
                    edges[i][n] = new Edge[]{
                            new Edge(nodes[i][n], nodes[(i+1) % nodes.length][n+1]),
                            new Edge(nodes[(i+1) % nodes.length][n+1], nodes[i][n+1]),
                            new Edge(nodes[i][n+1], nodes[i][n]),
                            new Edge(nodes[i][n], nodes[(i+1) % nodes.length][n]),
                            new Edge(nodes[(i+1) % nodes.length][n], nodes[(i+1) % nodes.length][n+1]),
                            new Edge(nodes[(i+1) % nodes.length][n+1], nodes[i][n])};
                }else{
                    edges[i][n] = new Edge[]{
                            new Edge(nodes[(i+1) % nodes.length][n], nodes[i][n+1]),
                            new Edge(nodes[(i+1) % nodes.length][n+1], nodes[i][n+1]),
                            new Edge(nodes[i][n+1], nodes[i][n]),
                            new Edge(nodes[i][n], nodes[(i+1) % nodes.length][n]),
                            new Edge(nodes[(i+1) % nodes.length][n], nodes[(i+1) % nodes.length][n+1]),
                            new Edge(nodes[i][n+1], nodes[(i+1) % nodes.length][n])};
                }

            }
        }

        //linking edges
        for (int i = 0; i < nodes.length - 1+ (loopX ? 1:0); i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                edges[i][n][0].link(edges[i][n][5]);
                if(n > 0){
                    edges[i][n][3].link(edges[i][n-1][1]);
                }
                if(i > 0){
                    edges[i][n][2].link(edges[i-1][n][4]);
                }
            }
        }

        //generating faces
        for (int i = 0; i < nodes.length - 1 + (loopX ? 1:0); i++) {
            for (int n = 0; n < nodes[0].length - 1; n++) {
                if ((n + i) % 2 == 0) {
                    triangles.add(new Triangle(edges[i][n][0], edges[i][n][1], edges[i][n][2]));
                    triangles.add(new Triangle(edges[i][n][3], edges[i][n][4], edges[i][n][5]));
                }else{
                    triangles.add(new Triangle(edges[i][n][0], edges[i][n][2], edges[i][n][3]));
                    triangles.add(new Triangle(edges[i][n][1], edges[i][n][5], edges[i][n][4]));
                }
            }
        }

        Mesh m = new Mesh(triangles);
        return m;
    }

    public static Mesh arc_mesh(double lever_length, double r_min, double r_max, double degrees, int subd_l, int subd_w) {
        return Generator.connect_rectangular_nodes(arc_mesh_nodes(lever_length, r_min, r_max, degrees, subd_l, subd_w),false);
    }

    public static Mesh rectangle_mesh(double w, double h, int subd_w, int subd_h) {

        return Generator.connect_rectangular_nodes(rectangle_mesh_nodes(w,h,subd_w,subd_h),false);
    }

    public static Mesh rectangle_hole_mesh_connected(double w, double r, int subd_a, int subd_r){
        subd_a =  ((subd_a / 8)) * 8;
        Node[][] nodes = new Node[8 * subd_a][subd_r + 1];

        for(int k = 0; k < subd_a * 8 - 0.5; k++){
            double angle = Math.PI * 2 * k / (subd_a * 8);
            double inner = r;
            double outer = distanceInQuad(w, angle);
            for(int n = 0; n < subd_r + 0.5; n++){
                double rad = inner + ((double)n / subd_r) * (outer -inner);
                Node node = new Node(Math.cos(angle) * rad, Math.sin(angle) * rad);
                nodes[k][n] = node;
            }
        }
        return connect_rectangular_nodes(nodes, true);
    }

    public static Mesh rectangle_hole_mesh(double w, double r, int subd_a, int subd_r){
        subd_a =  ((subd_a / 8)) * 8;
        Node[][] nodes = new Node[8 * subd_a][subd_r + 1];

        for(int k = 0; k < subd_a * 8 - 0.5; k++){
            double angle = Math.PI * 2 * k / (subd_a * 8);
            double inner = r;
            double outer = distanceInQuad(w, angle);
            for(int n = 0; n < subd_r + 0.5; n++){
                double rad = inner + ((double)n / subd_r) * (outer -inner);
                Node node = new Node(Math.cos(angle) * rad, Math.sin(angle) * rad);
                nodes[k][n] = node;
            }
        }
        return connect_rectangular_nodes(nodes, false);
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

    /**
     * calculates the distance from the center of a quad with side length a given the angle
     *
     * ___y___
     * |  |  |
     * |  |__|x
     * |     |
     * |_____|
     * @param angle
     * @return
     */
    public static double distanceInQuad(double a, double angle) {
        angle %= (Math.PI / 2);
        if(angle > Math.PI / 4){
            angle = Math.PI / 2 - angle;
        }
        return a/2 / Math.cos(angle);
    }


    public static void main(String[] args) {
        Mesh mesh = rectangle_hole_mesh_connected(1,0.1,10,10);
        new Frame(mesh).enableWireframe();
    }


}
