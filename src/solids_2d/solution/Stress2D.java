package solids_2d.solution;

import core.vector.Vector;
import core.vector.Vector3d;

public class Stress2D extends Vector3d {


    public Stress2D() {
    }

    public Stress2D(double sigma_x, double sigma_y, double tau) {
       super(sigma_x, sigma_y, tau);
    }

    public Stress2D(Vector<?> other) {
        super(other);
    }

    public double comparable_stress(){
        return von_mises();
    }

    public double mohr_angle_2() {
        return Math.atan2(2 * getTau(), getSigma_x()-getSigma_y());
    }

    public double mohr_angle() {
        return mohr_angle_2()/ 2;
    }

    public double von_mises() {
        return Math.sqrt(
                + getSigma_x()* getSigma_x()
                + getSigma_y() * getSigma_y()
                - getSigma_y() * getSigma_x()
                + 3 * getTau() * getTau());
    }

    public double getSigma_x() {
        return getX();
    }

    public void setSigma_x(double sigma_x) {
        this.setX(sigma_x);
    }

    public double getSigma_y() {
        return getY();
    }

    public void setSigma_y(double sigma_y) {
        this.setY(sigma_y);
    }

    public double getTau() {
        return getZ();
    }

    public void setTau(double tau) {
        this.setZ(tau);
    }

    @Override
    public String toString() {
        return "Stress2D{" +
                "sigma_x=" + getSigma_x() +
                ", sigma_y=" + getSigma_y() +
                ", tau=" + getTau() +
                '}';
    }

}
