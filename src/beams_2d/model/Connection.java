package beams_2d.model;

import beams_2d.arrays.Array2D;
import beams_2d.arrays.vectors.Vector;
import beams_2d.arrays.vectors.Vector2d;

public class Connection<T extends Vector> {

    private Node<T> nodeA, nodeB;

    public Connection(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.nodeA.connections.remove(this);
        this.nodeB.connections.remove(this);
        this.nodeA.connections.add(this);
        this.nodeB.connections.add(this);
    }

    public Array2D get_local_stiffness() {
        if(this.nodeA.getPosition() instanceof Vector2d){
            Vector2d dir = new Vector2d(this.getNodeA().getPosition().getData()[0] - this.getNodeB().getPosition().getData()[0],
                    this.getNodeA().getPosition().getData()[1] - this.getNodeB().getPosition().getData()[1]);

            double angle = Math.atan2(dir.getY(), dir.getX());
            double length = dir.length();
            double A = 0.01;
            double E = 2 * 1E10;

            double f = E * A / length;

            Array2D ar = new Array2D(4, 4);
            ar.setValue(0, 0, f * Math.cos(angle) * Math.cos(angle));
            ar.setValue(1, 0, f * Math.sin(angle) * Math.cos(angle));
            ar.setValue(2, 0, f * -Math.cos(angle) * Math.cos(angle));
            ar.setValue(3, 0, f * -Math.sin(angle) * Math.cos(angle));

            ar.setValue(0, 1, f * Math.sin(angle) * Math.cos(angle));
            ar.setValue(1, 1, f * Math.sin(angle) * Math.sin(angle));
            ar.setValue(2, 1, f * -Math.cos(angle) * Math.sin(angle));
            ar.setValue(3, 1, f * -Math.sin(angle) * Math.sin(angle));

            ar.setValue(0, 2, f * -Math.cos(angle) * Math.cos(angle));
            ar.setValue(1, 2, f * -Math.sin(angle) * Math.cos(angle));
            ar.setValue(2, 2, f * Math.cos(angle) * Math.cos(angle));
            ar.setValue(3, 2, f * Math.sin(angle) * Math.cos(angle));

            ar.setValue(0, 3, f * -Math.cos(angle) * Math.sin(angle));
            ar.setValue(1, 3, f * -Math.sin(angle) * Math.sin(angle));
            ar.setValue(2, 3, f * Math.cos(angle) * Math.sin(angle));
            ar.setValue(3, 3, f * Math.sin(angle) * Math.sin(angle));
            return ar;
        }
        return null;
    }

    public void build_into_global_matrix(Array2D ar){
        if(this.nodeA.getPosition() instanceof Vector2d) {
            Array2D l = get_local_stiffness();


            int x1 = nodeA.index * 2;
            int x2 = nodeB.index * 2;

            ar.getData()[ar.getIndex(x1,x1)] += l.getValue(0,0);
            ar.getData()[ar.getIndex(x1+1,x1)] += l.getValue(1,0);
            ar.getData()[ar.getIndex(x1,x1+1)] += l.getValue(0,1);
            ar.getData()[ar.getIndex(x1+1,x1+1)] += l.getValue(1,1);

            ar.getData()[ar.getIndex(x2,x2)] += l.getValue(2,2);
            ar.getData()[ar.getIndex(x2+1,x2)] += l.getValue(3,2);
            ar.getData()[ar.getIndex(x2,x2+1)] += l.getValue(2,3);
            ar.getData()[ar.getIndex(x2+1,x2+1)] += l.getValue(3,3);

            ar.getData()[ar.getIndex(x1,x2)] += l.getValue(0,2);
            ar.getData()[ar.getIndex(x1+1,x2)] += l.getValue(1,2);
            ar.getData()[ar.getIndex(x1,x2+1)] += l.getValue(0,3);
            ar.getData()[ar.getIndex(x1+1,x2+1)] += l.getValue(1,3);

            ar.getData()[ar.getIndex(x2,x1)] += l.getValue(2,0);
            ar.getData()[ar.getIndex(x2+1,x1)] += l.getValue(3,0);
            ar.getData()[ar.getIndex(x2,x1+1)] += l.getValue(2,1);
            ar.getData()[ar.getIndex(x2+1,x1+1)] += l.getValue(3,1);
        }
    }

    public Node<T> getNodeA() {
        return nodeA;
    }

    public void setNodeA(Node<T> nodeA) {
        this.nodeA = nodeA;
    }

    public Node<T> getNodeB() {
        return nodeB;
    }

    public void setNodeB(Node<T> nodeB) {
        this.nodeB = nodeB;
    }
}
