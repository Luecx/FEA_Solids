package solids_2d;

import core.Vertex;
import core.vector.Vector2d;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;

public class Node extends Vertex {

    private int id_x = -1,id_y = -1;

    private Vector2d displacement = new Vector2d(0,0);
    private Vector2d localDisplacement = new Vector2d(0,0);
    private Force force = new Force(0,0);
    private Support support = new Support(false,false);

    public Node(double x, double y) {
        super(x, y);
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

    public Vector2d getLocalDisplacement() {
        return localDisplacement;
    }

    public void setLocalDisplacement(Vector2d localDisplacement) {
        this.localDisplacement = localDisplacement;
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

    public Vector2d getPosition2D(){
        return new Vector2d(this.getX(), this.getY());
    }

    @Override
    public void prepare_data() {
        super.prepare_data();
        this.bindData(new double[]{
                displacement.getX(),
                displacement.getY(),
                force.getX(),
                force.getY(),
                support.getX(),
                support.getY(),
                support.getSupport_force().getX(),
                support.getSupport_force().getY()
        });
    }

    @Override
    public void process_data() {
        super.process_data();

        this.displacement = new Vector2d(this.data[0],this.data[1]);
        this.force = new Force(this.data[2],this.data[3]);
        this.support = new Support(this.data[4] == 1, this.data[5] == 1);
        this.support.setSupport_force(new Force(this.data[6], this.data[7]));

    }

    @Override
    public String toString() {
        return "Node " + this.getPosition2D();
    }
}

