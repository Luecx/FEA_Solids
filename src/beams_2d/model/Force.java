package beams_2d.model;

import beams_2d.arrays.vectors.Vector;

public class Force<T extends Vector> {

    private T vector;

    public Force(T vector) {
        this.vector = vector;
    }

    public T getVector() {
        return vector;
    }

    public void setVector(T vector) {
        this.vector = vector;
    }
}
