package solids_2d.elements;

import core.Edge;
import core.Face;
import core.matrix.Matrix;
import core.matrix.sparse_matrix.HashMatrix;
import core.vector.DenseVector;
import solids_2d.Node;
import solids_2d.material.Material;
import solids_2d.solution.Stress2D;

public abstract class FiniteElement2D extends Face {

    private double thickness = 1;
    private Material material = new Material(1E6, 0.25);

    private Stress2D stress2D = new Stress2D(0,0,0);

    public FiniteElement2D(Node... nodes){
        super(gen_edges(nodes));
    }

    public FiniteElement2D(Edge... edges) {
        super(edges);
    }

    public int node_count(){
        return this.vertices.length;
    }

    public Node getNode(int index){
        return (Node)this.vertices[index];
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getThickness() {
        return thickness;
    }

    public Material getMaterial() {
        return material;
    }

    public Stress2D getStress2D() {
        return stress2D;
    }

    public void setStress2D(Stress2D stress2D) {
        this.stress2D = stress2D;
    }

    @Override
    public void prepare_data() {
        super.prepare_data();
        this.bindData(new double[]{
                stress2D.getSigma_x(),
                stress2D.getSigma_y(),
                stress2D.getTau(),
                material.getYoung(),
                material.getPoisson()
        });
    }

    @Override
    public void process_data() {
        super.process_data();
        if(this.dataSize() >= 5){
            this.stress2D = new Stress2D(this.data[0],this.data[1], this.data[2]);
            this.material = new Material(this.data[3], this.data[4]);
        }
    }

    public abstract void prepare();

    public abstract Matrix generate_reduced_stiffness_matrix();

    public abstract void evaluate_stress();

    public DenseVector getNodeDisplacement(){
        DenseVector res = new DenseVector(this.node_count() * 2);
        for(int i = 0;i < this.node_count();i ++){
            res.setValue(i * 2, getNode(i).getDisplacement().getX());
            res.setValue(i * 2+1, getNode(i).getDisplacement().getY());
        }
        return res;
    }

    private static Edge[] gen_edges(Node... nodes){
        Edge[] e = new Edge[nodes.length];
        for(int i = 0; i < e.length; i++){
            e[i] = new Edge(nodes[i], nodes[(i+1) % nodes.length]);
        }
        return e;
    }

    public void assemble_reduced_stiffness(Matrix global) {
        this.prepare();
        Matrix stiffness = this.generate_reduced_stiffness_matrix();
        for (int i = 0; i < this.node_count() * 2; i++) {
            for (int n = 0; n < this.node_count() * 2; n++) {
                if (this.getNode(i / 2).getSupport().getValue(i % 2) == 0 &&
                        this.getNode(n / 2).getSupport().getValue(n % 2) == 0) {
                    int j = i % 2 == 0 ? this.getNode(i / 2).getId_x() : this.getNode(i / 2).getId_y();
                    int k = n % 2 == 0 ? this.getNode(n / 2).getId_x() : this.getNode(n / 2).getId_y();
                    global.setValue(j, k, stiffness.getValue(i, n) + global.getValue(j, k));
                }
            }
        }
    }

    public void assemble_complete_stiffness(HashMatrix global) {
        Matrix stiffness = this.generate_reduced_stiffness_matrix();

        for (int i = 0; i < this.node_count() * 2; i++) {
            for (int n = 0; n < this.node_count() * 2; n++) {
                int j = i % 2 == 0 ? this.getNode(i / 2).getId_x() : this.getNode(i / 2).getId_y();
                int k = n % 2 == 0 ? this.getNode(n / 2).getId_x() : this.getNode(n / 2).getId_y();
                global.setValue(j, k, stiffness.getValue(i, n) + global.getValue(j, k));
            }
        }
    }


}
