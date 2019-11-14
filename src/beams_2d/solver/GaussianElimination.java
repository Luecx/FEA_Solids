package beams_2d.solver;

import beams_2d.arrays.Array2D;
import beams_2d.arrays.vectors.Vector;

public class GaussianElimination {
    public static double[] solve(double[][] A, double[] B) {
        int N = B.length;
        for (int k = 0; k < N; k++) {
            /** find pivot row **/

            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;
            /** swap row in A matrix **/
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;
            /** swap corresponding values in constants matrix **/
            double t = B[k];
            B[k] = B[max];
            B[max] = t;
            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }

        /** back substitution **/
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        return solution;
    }


    public static Vector lsolve(Array2D A, Vector b) {


        double[][] a = new double[A.getHeight()][A.getWidth()];
        for (int x = 0; x < A.getWidth(); x++) {
            for (int y = 0; y < A.getWidth(); y++) {
                a[y][x] = A.getValue(x, y);
            }
        }
        return new Vector(solve(a, b.getData()));
    }

    public static void lsolve(Array2D A, Vector x, Vector b) {
        Vector b_new = new Vector(b);
        Array2D A_new = transform_system(A, x, b_new);


        Vector sol = lsolve(A_new, b_new);

        for (int i = 0; i < sol.size(); i++) {
            if (Double.isNaN(x.getValue(i))) {
                x.setValue(i, sol.getValue(i));
            } else {

                b.setValue(i, sol.getValue(i));
            }
        }
    }

    public static Array2D transform_system(Array2D A, Vector x, Vector b) {
        Array2D matrix = new Array2D(A);
        for (int i = 0; i < A.getHeight(); i++) {
            if (Double.isNaN(b.getValue(i))) {
                if (Double.isNaN(x.getValue(i))) {
                    throw new RuntimeException("Cannot have 2 unknowns in row " + i);
                } else {

                    for (int k = 0; k < A.getHeight(); k++) {
                        if (k == i) continue;
                        b.setValue(k, b.getValue(k) - matrix.getValue(i, k) * x.getValue(i));
                        matrix.setValue(i, k, 0);
                    }

                    b.setValue(i, -matrix.getValue(i, i) * x.getValue(i));
                    matrix.setValue(i, i, -1);
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {

        Array2D ar = new Array2D(3, 3);
        ar.setValue(0, 0, 2);
        ar.setValue(0, 1, 1);
        ar.setValue(0, 2, 2);

        ar.setValue(1, 0, -2);
        ar.setValue(1, 1, 1);
        ar.setValue(1, 2, 1);

        ar.setValue(2, 0, 3);
        ar.setValue(2, 1, 1);
        ar.setValue(2, 2, 1);

        Vector x = new Vector(Double.NaN, Double.NaN, 2);
        Vector b = new Vector(1, 2, Double.NaN);


        System.out.println(ar);
        System.out.println("");
        System.out.println("");
        System.out.println(b);
        System.out.println("");
        System.out.println(lsolve(transform_system(ar, x, b), b));

//        double[][] m= new double[][]{
//                {2,-2,3},
//                {1,1,1},
//                {2,1,1}
//        };
//        double[] b_s= new double[]{1,2,2};
//
//        System.out.println(Arrays.toString(lsolve(m,b_s)));
    }

}