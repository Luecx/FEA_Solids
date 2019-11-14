package beams_2d.arrays;

public class Array2D extends Array{

    private final int width;
    private final int height;

    public Array2D(int width,int height) {
        super(width * height);
        this.width = width;
        this.height = height;
    }

    public Array2D(Array2D ar) {
        super(ar);
        this.width = ar.width;
        this.height = ar.height;
    }

    public int getIndex(int x, int y){
        return x * height + y;
    }


    public double getValue(int x, int y){
        return this.data[x * height + y];
    }

    public void setValue(int x, int y, double value){
        this.data[x * height + y] = value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        String s = "";

        if(size() == height || size() == width){
            s+= "flipped: ";
            for(int h = 0; h < size(); h++){
                s += (data[h] + "           ").substring(0,Array.PRINT_PRECISION + 1) + " ";
            }
            return s;
        }

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
               s +=  ((Math.abs(getValue(x,y)) < 0.001 ? "~0":getValue(x,y)) + "           ").substring(0,Array.PRINT_PRECISION + 1) + " ";
            }
            if(y != height-1){
                s+="\n";
            }
        }
        return s;
    }
}
