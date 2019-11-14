package beams_2d.model;

import beams_2d.arrays.vectors.Vector;

public class Support<T extends Vector> {

    private T support;

    public Support(T support) {
        this.support = support;
    }

    public T getSupport() {
        return support;
    }

    public void setSupport(T support) {
        this.support = support;
    }
}
