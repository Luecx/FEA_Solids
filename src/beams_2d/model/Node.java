package beams_2d.model;

import beams_2d.arrays.vectors.Vector;

import java.util.ArrayList;

public class Node<T extends Vector> {


    int index;
    T position;
    ArrayList<Connection> connections = new ArrayList<Connection>();

    private Force<T> force;
    private Support<T> support;


    public Node(T position, int index, Force<T> force, Support<T> support) {
        this.index = index;
        this.position = position;
        this.force = force;
        this.support = support;
    }

    public Node(T position, int index) {
        this.position = position;
        this.index = index;
    }

    public T getPosition() {
        return position;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public int getIndex() {
        return index;
    }

    public Force<T> getForce() {
        return force;
    }

    public Support<T> getSupport() {
        return support;
    }

    public void setForce(Force<T> force) {
        this.force = force;
    }

    public void setSupport(Support<T> support) {
        this.support = support;
    }
}
