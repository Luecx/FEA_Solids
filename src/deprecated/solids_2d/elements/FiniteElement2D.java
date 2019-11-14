package deprecated.solids_2d.elements;

import core.matrix.Matrix;
import core.matrix.sparse_matrix.HashMatrix;
import core.vector.DenseVector;
import deprecated.solids_2d.Node;
import deprecated.solids_2d.solution.Stress;
import deprecated.solids_2d.material.Material;

public abstract class FiniteElement2D {

    protected Node[] nodes;
    protected Material material;
    private double thickness;

    protected Stress evaluated_stress;

    public FiniteElement2D(Node[] nodes) {
        this.nodes = nodes;
        //this.thickness = 0.5;
    }

    public FiniteElement2D(Node[] nodes, Material material) {
        this.nodes = nodes;
        this.material = material;
        //this.thickness = 0.5;
    }

    public FiniteElement2D(Node[] nodes, double thickness) {
        this.nodes = nodes;
        this.thickness = thickness;
    }

    public FiniteElement2D(Node[] nodes, Material material, double thickness) {
        this.nodes = nodes;
        this.material = material;
        this.thickness = thickness;
    }

    public abstract void prepare();

    public abstract Matrix generate_reduced_stiffness_matrix();

    public abstract void evaluate_stress();

    public void assemble_reduced_stiffness(Matrix global) {
        this.prepare();
        Matrix stiffness = this.generate_reduced_stiffness_matrix();
        for (int i = 0; i < this.nodes.length * 2; i++) {
            for (int n = 0; n < this.nodes.length * 2; n++) {
                if (nodes[i / 2].getSupport().getValue(i % 2) == 0 &&
                        nodes[n / 2].getSupport().getValue(n % 2) == 0) {
                    int j = i % 2 == 0 ? nodes[i / 2].getId_x() : nodes[i / 2].getId_y();
                    int k = n % 2 == 0 ? nodes[n / 2].getId_x() : nodes[n / 2].getId_y();
                    global.setValue(j, k, stiffness.getValue(i, n) + global.getValue(j, k));
                }
            }
        }
    }

    public void assemble_complete_stiffness(HashMatrix global) {
        Matrix stiffness = this.generate_reduced_stiffness_matrix();
        System.out.println(stiffness);
        for (int i = 0; i < this.nodes.length * 2; i++) {
            for (int n = 0; n < this.nodes.length * 2; n++) {
                int j = i % 2 == 0 ? nodes[i / 2].getId_x() : nodes[i / 2].getId_y();
                int k = n % 2 == 0 ? nodes[n / 2].getId_x() : nodes[n / 2].getId_y();
                global.setValue(j, k, stiffness.getValue(i, n) + global.getValue(j, k));
            }
        }
    }

    public DenseVector getDisplacement(){
        DenseVector res = new DenseVector(this.nodes.length * 2);
        for(int i = 0;i < this.nodes.length;i ++){
            res.setValue(i * 2, nodes[i].getDisplacement().getX());
            res.setValue(i * 2+1, nodes[i].getDisplacement().getY());
        }
        return res;
    }


    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public Node[] getNodes() {
        return nodes;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Stress getEvaluated_stress() {
        return evaluated_stress;
    }

}
