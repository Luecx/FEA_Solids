package deprecated.solids_2d;

import core.vector.Vector2d;
import deprecated.solids_2d.constraint.Force;
import deprecated.solids_2d.constraint.Support;

public class Node {

    private int id_x = -1,id_y = -1;
    private Vector2d position;
    private Vector2d displacement = new Vector2d(0,0);


    private Force force = new Force(0,0);
    private Support support = new Support(false,false);

    public Node(Vector2d position) {
        this.position = position;
    }

    public int getId_x() {
        return id_x;
    }

    public void setId_x(int id_x) {
        this.id_x = id_x;
    }

    public int getId_y() {
        return id_y;
    }

    public void setId_y(int id_y) {
        this.id_y = id_y;
    }

    public Vector2d getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Vector2d displacement) {
        this.displacement = displacement;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public Force getForce() {
        return force;
    }

    public void setForce(Force force) {
        this.force = force;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}

