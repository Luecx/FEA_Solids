package solids_2d.constraint;


import core.vector.Vector2d;

public class Force extends Vector2d implements Constraint {
    public Force(Double x, Double y) {
        super(x, y);
    }

    public Force(double x, double y) {
        super(x, y);
    }
}
