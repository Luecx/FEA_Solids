package deprecated.solids_2d.elements;

import core.matrix.Matrix;
import core.matrix.dense.DenseMatrix;
import core.vector.DenseVector;
import deprecated.solids_2d.Node;
import deprecated.solids_2d.material.Material;
import deprecated.solids_2d.solution.Stress;

public class Quad extends FiniteElement2D {

    private Node a, b, c, d;
    private DenseMatrix B;
    private DenseMatrix D;
    private double det_J;
    private double Area;

    public Quad(Node a, Node b, Node c, Node d) {
        super(new Node[]{a, b, c, d});
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Quad(Node a, Node b, Node c, Node d,Material material) {
        super(new Node[]{a, b, c, d}, material);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Quad(Node a, Node b, Node c, Node d,double thickness) {
        super(new Node[]{a, b, c,d }, null, thickness);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Quad(Node a, Node b, Node c, Node d,Material material, double thickness) {
        super(new Node[]{a, b, c, d}, material, thickness);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void prepare() {

        this.det_J = (a.getPosition().getX() - c.getPosition().getX()) * (b.getPosition().getY() - c.getPosition().getY()) -
                (a.getPosition().getY() - c.getPosition().getY()) * (b.getPosition().getX() - c.getPosition().getX());

        this.Area = Math.abs(det_J) / 2;


        B = new DenseMatrix(3, 6);
        B.setValue(0, 0, b.getPosition().getY() - c.getPosition().getY());
        B.setValue(0, 2, c.getPosition().getY() - a.getPosition().getY());
        B.setValue(0, 4, a.getPosition().getY() - b.getPosition().getY());

        B.setValue(1, 1, c.getPosition().getX() - b.getPosition().getX());
        B.setValue(1, 3, a.getPosition().getX() - c.getPosition().getX());
        B.setValue(1, 5, b.getPosition().getX() - a.getPosition().getX());

        B.setValue(2, 0, c.getPosition().getX() - b.getPosition().getX());
        B.setValue(2, 1, b.getPosition().getY() - c.getPosition().getY());
        B.setValue(2, 2, a.getPosition().getX() - c.getPosition().getX());
        B.setValue(2, 3, c.getPosition().getY() - a.getPosition().getY());
        B.setValue(2, 4, b.getPosition().getX() - a.getPosition().getX());
        B.setValue(2, 5, a.getPosition().getY() - b.getPosition().getY());
        B.scale(1d / det_J);

        D = material.material_matrix();
    }

    @Override
    public Matrix generate_reduced_stiffness_matrix() {
        Matrix res = B.transpose().mul(D).mul(B);
        res.scale(Area * this.getThickness());
        return res;
    }

    @Override
    public void evaluate_stress() {
        DenseVector displ = new DenseVector(a.getDisplacement().getX(),
                a.getDisplacement().getY(),
                b.getDisplacement().getX(),
                b.getDisplacement().getY(),
                c.getDisplacement().getX(),
                c.getDisplacement().getY());

        DenseVector sol = D.mul(B).mul(displ);
        this.evaluated_stress = new Stress(
                sol.getValue(0),
                sol.getValue(1),
                sol.getValue(2));

    }




}


