package beams_2d.arrays.vectors;

import beams_2d.arrays.Array;

public class Vector extends Array {

    public Vector(Vector v) {
        super(v);
    }

    public Vector(double... data) {
        super(data);
    }

    public Vector(Array ar) {
        super(ar);
    }

    public Vector(int length) {
        super(length);
    }

    public double length(){
        double v = 0;
        for(double d:data){
            v+= d*d;
        }
        return Math.sqrt(v);
    }
}
