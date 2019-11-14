package beams_2d.arrays.vectors;

public class Vector2d extends Vector {

    public Vector2d(Vector2d v) {
        super(v);
    }
    public Vector2d(double x, double y){
        super(x,y);
    }
    public Vector2d() {
        super(2);
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
        data[1] = v;
    }

}
