package beams_2d.arrays;


import java.util.Random;

public class Array {

    public static final int PRINT_PRECISION = 5;
    public static final long RANDOMIZE_SEED = (int) (100000 * Math.random());
    public static final Random RANDOM = new Random(RANDOMIZE_SEED);

    protected double[] data;

    public Array(int length) {
        this.data = new double[length];
    }

    public Array(double... data) {
        this.data = data;
    }

    public Array(Array ar) {
        this.data = new double[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            data[i] = ar.data[i];
        }
    }


    public int size() {
        return data.length;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        if (data != null && data.length == this.data.length) {
            this.data = data;
        }
    }

    public double getValue(int index) {
        return data[index];
    }

    public void setValue(int index, double value) {
        data[index] = value;
    }

    public void randomizeRegular(double lower, double upper) {

        for (int i = 0; i < this.size(); i++) {
            data[i] = RANDOM.nextDouble() * (upper - lower) + lower;
        }
    }

    public void randomizeGaussian(double lower, double upper) {
        for (int i = 0; i < this.size(); i++) {
            data[i] = RANDOM.nextGaussian() * (upper - lower) + lower;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int x = 0; x < data.length; x++) {
            s += ((Math.abs(getValue(x)) < 0.1 ? "~0":getValue(x)) + "           ").substring(0, Array.PRINT_PRECISION + 1) + " ";
        }


        return s;
    }
}
