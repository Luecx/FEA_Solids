package deprecated.solids_2d.constraint;


import core.vector.Vector2d;

public class Support extends Vector2d implements Constraint{

    private Force support_force = new Force(0,0);

    public Support(Boolean x, Boolean y) {
        super(x ? 1:0, y ? 1:0);
    }

    public Support(boolean x, boolean y) {
        super(x ? 1:0, y ? 1:0);
    }

    public Force getSupport_force() {
        return support_force;
    }

    public void setSupport_force(Force support_force) {
        this.support_force = support_force;
    }
}
