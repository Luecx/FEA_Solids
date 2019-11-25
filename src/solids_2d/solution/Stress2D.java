package solids_2d.solution;

public class Stress2D {

    private double sigma_x,sigma_y, tau;

    public Stress2D(double sigma_x, double sigma_y, double tau) {
        this.sigma_x = sigma_x;
        this.sigma_y = sigma_y;
        this.tau = tau;
    }

    public double comparable_stress(){
        return von_mises();
    }

    public double mohr_angle_2() {
        return Math.atan2(2 * tau, sigma_x-sigma_y);
    }

    public double mohr_angle() {
        return mohr_angle_2()/ 2;
    }

    public double von_mises() {
        return Math.sqrt(sigma_x* sigma_x + sigma_y * sigma_y - sigma_y * sigma_x+ 3 * tau * tau);
    }

    public double getSigma_x() {
        return sigma_x;
    }

    public void setSigma_x(double sigma_x) {
        this.sigma_x = sigma_x;
    }

    public double getSigma_y() {
        return sigma_y;
    }

    public void setSigma_y(double sigma_y) {
        this.sigma_y = sigma_y;
    }

    public double getTau() {
        return tau;
    }

    public void setTau(double tau) {
        this.tau = tau;
    }

    @Override
    public String toString() {
        return "Stress2D{" +
                "sigma_x=" + sigma_x +
                ", sigma_y=" + sigma_y +
                ", tau=" + tau +
                '}';
    }

}
