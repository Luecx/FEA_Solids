package beams_2d.arrays.vectors;

public class Vector3d extends Vector {

    public Vector3d(Vector3d v) {
        super(v);
    }

    public Vector3d(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3d() {
        super(3);
    }

    public double getX(){
        return data[0];
    }
    public double getY(){
        return data[1];
    }
    public void setX(double v){
        data[0] = v;
    }
    public void setY(double v){
        data[2] = v;
    }
    public double getZ(){
        return data[1];
    }
    public void setZ(double v){
        data[2] = v;
    }
}
