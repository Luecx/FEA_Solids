package solids_2d.material;


import core.matrix.dense.DenseMatrix;

public class Material {



    private double young;                   //E-Modul in 1E6
    private double poisson;                 //Querkontraktionszahl



    private DenseMatrix matrix = new DenseMatrix(3, 3);
    private boolean matrix_deprecated = true;


    public Material(Materials template){
        this.young = template.young;
        this.poisson = template.poisson;
    }

    public Material(double young, double poisson) {
        this.young = young;
        this.poisson = poisson;
    }

    public double getYoung() {
        return young;
    }

    public void setYoung(double young) {
        this.young = young;
        this.matrix_deprecated = true;
    }

    public double getPoisson() {
        return poisson;
    }

    public void setPoisson(double poisson) {
        this.poisson = poisson;
        this.matrix_deprecated = true;
    }

    public DenseMatrix material_matrix() {
        if (matrix_deprecated) {
//            double LAME = v * E / ((1 + v) * (1 - 2 * v));
//            double MY = E / (2 * (1 + v));
//
//            matrix.setValue(0, 0, LAME + 2 * MY);
//            matrix.setValue(1, 1, LAME + 2 * MY);
//            matrix.setValue(1, 0, LAME);
//            matrix.setValue(0, 1, LAME);
//            matrix.setValue(2, 2, MY);
            double E = this.young;
            double v = this.poisson;

            matrix.setValue(0,0,1);
            matrix.setValue(1,0,v);
            matrix.setValue(0,1,v);
            matrix.setValue(1,1,1);
            matrix.setValue(2,2, (1-v) / 2);
            matrix.self_scale(E / (1-v*v ));
        }
        return matrix;

    }

}
