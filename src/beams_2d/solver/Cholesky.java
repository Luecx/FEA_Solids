package beams_2d.solver;

import beams_2d.arrays.Array2D;

public class Cholesky {
    private static final double EPSILON = 1e-10;

    // is symmetric
    public static boolean isSymmetric(Array2D A) {
        int N = A.getWidth();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A.getValue(i,j) != A.getValue(j,i)) return false;
            }
        }
        return true;
    }

    // is symmetric
    public static boolean isSquare(Array2D A) {
        return A.getWidth() == A.getHeight();
    }


    // return Cholesky factor L of psd matrix A = L L^T
    public static Array2D cholesky(Array2D A) {
        if (!isSquare(A)) {
            throw new RuntimeException("Matrix is not square");
        }
        if (!isSymmetric(A)) {
            throw new RuntimeException("Matrix is not symmetric");
        }

        int N  = A.getHeight();
        Array2D L = new Array2D(N,N);

        for (int i = 0; i < N; i++)  {
            for (int j = 0; j <= i; j++) {
                double sum = 0.0;
                for (int k = 0; k < j; k++) {
                    sum += L.getValue(k,i) * L.getValue(k,j);
                }
                if (i == j) L.setValue(i,i,Math.sqrt(A.getValue(i,i) - sum));
                else        L.setValue(j,i,1.0 / L.getValue(j,j) * (A.getValue(j,i) - sum));
            }
            if (L.getValue(i,i) <= 0) {
                throw new RuntimeException("Matrix not positive definite");
            }
        }
        return L;
    }


    // sample client
    public static void main(String[] args) {
        int N = 3;
        double[][] A = { { 4, 1,  1 },
                         { 1, 5,  3 },
                         { 1, 3, 15 }
                       };


        Array2D ar =new Array2D(3,3);
        ar.setValue(0,0,4);
        ar.setValue(1,0,1);
        ar.setValue(2,0,1);
        ar.setValue(0,1,1);
        ar.setValue(1,1,5);
        ar.setValue(2,1,3);
        ar.setValue(0,2,1);
        ar.setValue(1,2,3);
        ar.setValue(2,2,15);

        Array2D L = cholesky(ar);
       System.out.println(L);

    }

}