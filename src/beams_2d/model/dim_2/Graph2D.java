package beams_2d.model.dim_2;

import beams_2d.arrays.Array2D;
import beams_2d.arrays.vectors.Vector;
import beams_2d.arrays.vectors.Vector2d;
import beams_2d.model.Connection;
import beams_2d.model.Force;
import beams_2d.model.Node;
import beams_2d.model.Support;

import java.util.ArrayList;

public class Graph2D extends beams_2d.model.Graph<Vector2d> {


    public Graph2D(Vector2d... boundary) {

    }

    public Graph2D(ArrayList<Node<Vector2d>> nodes, ArrayList<Connection<Vector2d>> connections) {
        super(nodes, connections);
    }

    public Graph2D() {
    }

    public Array2D generate_stiffness_matrix(){
        Array2D ar =  new Array2D(getNodes().size() * 2, getNodes().size() * 2);
        for(Connection<Vector2d> connection:this.getConnections()){
            connection.build_into_global_matrix(ar);
        }
        return ar;
    }

    public Vector generate_displacement_vector(){
        Vector v = new Vector(getNodes().size() * 2);
        for(int i = 0; i < getNodes().size(); i++){
            Node n = getNodes().get(i);

            if(n.getSupport() != null && n.getSupport().getSupport().getValue(0) != 0){
                v.setValue(i * 2, 0);
            }else{
                v.setValue(i * 2, Double.NaN);
            }

            if(n.getSupport() != null && n.getSupport().getSupport().getValue(1) != 0){
                v.setValue(i * 2 + 1, 0);
            }else{
                v.setValue(i * 2 + 1, Double.NaN);
            }
        }
        return v;
    }

    public Vector generate_force_vector(){
        Vector v = new Vector(getNodes().size() * 2);
        for(int i = 0; i < getNodes().size(); i++){
            Node n = getNodes().get(i);

            if(n.getForce() != null){
                v.setValue(i * 2, n.getForce().getVector().getValue(0));
                v.setValue(i * 2 + 1, n.getForce().getVector().getValue(1));
            }else{
                if(n.getSupport() != null){

                    if(n.getSupport().getSupport().getValue(0) != 0)
                        v.setValue(i * 2, Double.NaN);
                    if(n.getSupport().getSupport().getValue(1) != 0)
                        v.setValue(i * 2 + 1, Double.NaN);
                }else{

                    v.setValue(i * 2, 0);
                    v.setValue(i * 2 + 1, 0);
                }
            }
        }
        return v;
    }

    public static Graph2D rectangle(int w, int h, int subd_w, int subd_h){

        Node[][] nodes = new Node[subd_w+1][subd_h+1];
        ArrayList<Connection<Vector2d>> connections = new ArrayList<>();
        ArrayList<Node<Vector2d>> nodes_list = new ArrayList<>();

        int index = 0;

        for(int n = 0; n < subd_h +1; n++){
            for(int i = 0; i < subd_w +1; i++){
                Node node = new Node<>(new Vector2d((double) w / (subd_w) * i, (double) h / (subd_h) * n), index++);

                if(i > 0){
                    connections.add(new Connection(nodes[i-1][n], node));
                }
                if(n != 0){
                    connections.add(new Connection(nodes[i][n-1], node));
                    if(i != subd_w){
                        connections.add(new Connection(nodes[i+1][n-1], node));
                    }
                }

                nodes_list.add(node);
                nodes[i][n] = node;

            }
        }

        Graph2D gr = new Graph2D(nodes_list, connections);
        return gr;
    }


    public static void main(String[] args) {
        Graph2D graph = Graph2D.rectangle(1,1,300,300);
        graph.getNodes().get(0).setSupport(new Support<Vector2d>(new Vector2d(1,1)));
        graph.getNodes().get(3).setSupport(new Support<Vector2d>(new Vector2d(1,0)));
        graph.getNodes().get(2).setForce(new Force<Vector2d>(new Vector2d(30000000,300000000)));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        Vector displacement = graph.generate_displacement_vector();
        Vector force = graph.generate_force_vector();
        Array2D stiffness = graph.generate_stiffness_matrix();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        GaussianElimination.lsolve(stiffness, displacement, force);
//        System.out.println(force);
//        System.out.println(displacement);
//
//        System.out.println(Arrays.toString(displacement.getData()));



//        Vector2d target = new Vector2d(graph.getNodes().size() * 2);
//
//        System.out.println(Cholesky.cholesky(graph.generate_reduced_stiffness_matrix()));



       // System.out.println(GaussianElimination.lsolve(graph.generate_reduced_stiffness_matrix(), target));
    }
}
