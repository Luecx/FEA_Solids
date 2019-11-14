package solids_2d.elements;

import core.Edge;
import core.matrix.Matrix;
import core.matrix.dense.DenseMatrix;
import core.vector.DenseVector;
import solids_2d.Node;
import solids_2d.material.Material;
import solids_2d.solution.Stress2D;

public class Triangle extends FiniteElement2D{

    private Node a, b, c;

    private DenseMatrix B;
    private DenseMatrix D;
    private double det_J;
    private double Area;

    public Triangle(Edge... edges) {
        super(edges);
        this.a = this.getNode(0);
        this.b = this.getNode(1);
        this.c = this.getNode(2);
    }

    public Triangle(Node a, Node b, Node c) {
        super(a,b,c);
        this.a = this.getNode(0);
        this.b = this.getNode(1);
        this.c = this.getNode(2);
    }

    public Triangle(Node a, Node b, Node c, Material material) {
        super(new Node[]{a, b, c});
        this.a = this.getNode(0);
        this.b = this.getNode(1);
        this.c = this.getNode(2);
        this.setMaterial(material);
    }

    public Triangle(Node a, Node b, Node c, double thickness) {
        super(new Node[]{a, b, c});
        this.a = this.getNode(0);
        this.b = this.getNode(1);
        this.c = this.getNode(2);
        this.setThickness(thickness);
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


        D = this.getMaterial().material_matrix();
    }

    @Override
    public Matrix generate_reduced_stiffness_matrix() {
        Matrix res = B.transpose().mul(D).mul(B);
        res.scale(Area * this.getThickness());
        return res;
    }

    @Override
    public void evaluate_stress() {
        DenseVector sol = D.mul(B).mul(getNodeDisplacement());
        this.setStress2D(new Stress2D(
                sol.getValue(0),
                sol.getValue(1),
                sol.getValue(2)));
        //this.setDisplacement();
    }


    public DenseMatrix getB() {
        return B;
    }

    public DenseMatrix getD() {
        return D;
    }
}


