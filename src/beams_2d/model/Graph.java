package beams_2d.model;

import beams_2d.arrays.vectors.Vector;

import java.util.ArrayList;

public class Graph<T extends Vector> {

    private ArrayList<Node<T>> nodes;
    private ArrayList<Connection<T>> connections;

    public Graph() {
    }

    public Graph(ArrayList<Node<T>> nodes, ArrayList<Connection<T>> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public void addNode(Node n, Node... others){
        nodes.add(n);
        for(Node k:others){
            connections.add(new Connection(n, k));
        }
    }



    public ArrayList<Node<T>> getNodes() {
        return nodes;
    }

    public ArrayList<Connection<T>> getConnections() {
        return connections;
    }
}
