package solids_2d;

import core.Edge;
import core.Face;
import core.Volume;
import core.matrix.Matrix;
import core.matrix.sparse_matrix.HashMatrix;
import core.matrix.sparse_matrix.SparseMatrix;
import core.solver.direct.Solver;
import core.vector.DenseVector;
import core.vector.Vector;
import core.vector.Vector2d;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;
import solids_2d.material.Material;
import solids_2d.meshgeneration.Generator;
import solids_2d.visual.FEM_Panel;
import solids_2d.visual.Frame;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Mesh extends structs.Mesh<Node, Edge, FiniteElement2D, Volume> {

    public Mesh(ArrayList<FiniteElement2D> faces) {
        super(faces);
    }

    public Mesh(Face... faces) {
        super(faces);
    }



    public int numerate_reduced_nodes() {
        int index = 0;
        for (Node n : this.vertices) {
            n.setId_x(n.getSupport().getX() == 0 ? index++ : -1);
            n.setId_y(n.getSupport().getY() == 0 ? index++ : -1);
        }
        return index;
    }

    public int numerate_nodes() {
        int index = 0;
        for (Node n : this.vertices) {
            n.setId_x(index++);
            n.setId_y(index++);
        }
        return index;
    }


    public Matrix build_complete_stiffnes_matrix() {

        int size = numerate_nodes();
        HashMatrix hashMatrix = new HashMatrix(size, size);
        //DenseMatrix hashMatrix = new DenseMatrix(size,size);
        for (FiniteElement2D e : this.faces) {
            e.prepare();
            e.assemble_complete_stiffness(hashMatrix);
        }
        //return hashMatrix;
        return new SparseMatrix(hashMatrix);
    }

    public Matrix build_reduced_stiffnes_matrix() {

        int size = numerate_reduced_nodes();
        HashMatrix hashMatrix = new HashMatrix(size, size);
        //DenseMatrix hashMatrix = new DenseMatrix(size,size);
        for (FiniteElement2D e : getFaces()) {
            e.prepare();
            e.assemble_reduced_stiffness(hashMatrix);
        }
        //return hashMatrix;
        return new SparseMatrix(hashMatrix);
    }

    public DenseVector build_reduced_load_vector() {
        int size = numerate_reduced_nodes();
        DenseVector loads = new DenseVector(size);
        for (Node n : getVertices()) {
            if (n.getId_x() >= 0) {
                loads.setValue(n.getId_x(), n.getForce().getX());
            }
            if (n.getId_y() >= 0) {
                loads.setValue(n.getId_y(), n.getForce().getY());
            }
        }
        return loads;
    }


    public void apply_solution(Vector solution) {
        numerate_reduced_nodes();
        for (Node n : getVertices()) {
            Vector2d displ = new Vector2d(
                    n.getId_x() >= 0 ? solution.getValue(n.getId_x()) : 0,
                    n.getId_y() >= 0 ? solution.getValue(n.getId_y()) : 0
            );
            n.setDisplacement(displ);
        }
    }

    public void calculate_stresses() {
        for (FiniteElement2D e : getFaces()) {
            e.evaluate_stress();
        }
    }


    public DenseVector solve() {
        long millis = System.currentTimeMillis();

        Matrix matrix = this.build_reduced_stiffnes_matrix();
        DenseVector loads = this.build_reduced_load_vector();
        DenseVector displacements = Solver.conjugate_gradient(matrix, loads);

        this.apply_solution(displacements);

        //System.err.println(matrix.sub(matrix.transpose()).norm_1());


        this.calculate_stresses();

        System.out.println("Solving and processing took: " + (System.currentTimeMillis()- millis) / 1000d + "s");

        return displacements;
    }

    public DenseVector solve(DenseVector x_0) {
        Matrix matrix = this.build_reduced_stiffnes_matrix();
        DenseVector loads = this.build_reduced_load_vector();
        DenseVector displacements = Solver.conjugate_gradient(matrix, loads, x_0);

        this.apply_solution(displacements);

        //System.err.println(matrix.sub(matrix.transpose()).norm_1());


        this.calculate_stresses();
        return displacements;
    }

    public DenseVector solve_with_reactions() {
        this.solve();

        Matrix matrix = this.build_complete_stiffnes_matrix();
        DenseVector displ = new DenseVector(this.vertices.size());
        for(int i = 0; i < this.vertices.size(); i++){
            displ.setValue(2 * i, this.vertices.get(i).getDisplacement().getX());
            displ.setValue(2 * i + 1, this.vertices.get(i).getDisplacement().getY());
        }
        DenseVector sol = matrix.mul(displ);
        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getSupport() != null){
                this.vertices.get(i).getSupport().setSupport_force(new Force(sol.getValue(2*i), sol.getValue(2 * i + 1)));
            }
            //this.nodes.get(i).setForce(new Force(sol.getValue(2*i), sol.getValue(2 * i + 1)));
        }

        return displ;

    }


    public void forEach_face(Consumer<FiniteElement2D> consumer){
        for(FiniteElement2D n:this.getFaces()){
            consumer.accept(n);
        }
    }

    public void forEach_node(Consumer<Node> consumer){
        for(Node n:getVertices()){
            consumer.accept(n);
        }
    }


    public static void main(String[] args) {


////
//
//
        Mesh mesh = Generator.rectangle_mesh(0.1d, 0.01d, 10, 10);

        mesh.getVertices().get(1).setSupport(new Support(true,true));
        mesh.getVertices().get(2).setSupport(new Support(true,true));
        mesh.getVertices().get(3).setForce(new Force(1,0));

        for(FiniteElement2D f:mesh.getFaces()){
            f.setMaterial(new Material(1E6,0.2));
        }
        ArrayList<Face> faces = mesh.getFaces().get(45).getNeighbors();
        for(Face f:faces){
            System.out.println(f);
            ((FiniteElement2D)f).setMaterial(new Material(1E4,0.3));
        }
        mesh.solve();
        //System.out.println(mesh.build_complete_stiffnes_matrix());
        new Frame(mesh).renderMode(FEM_Panel.DISPLACEMENT).renderBoundaryConditions();

//
//        mesh.getNodes().get(0).setSupport(new Support(true,true));
//        mesh.getNodes().get(100).setSupport(new Support(true,true));
//        mesh.getNodes().get(5101).setForce(new Force(0,-10000));
//        mesh.forEach_face(finiteElement2D -> finiteElement2D.setMaterial(new Material(70E9, 0.33)));
//        mesh.forEach_face(finiteElement2D -> finiteElement2D.setThickness(0.01));
//
//        mesh.solve_with_reactions();
//        System.out.println(mesh.getNodes().get(0).getSupport().getSupport_force());
//        System.out.println(mesh.getNodes().get(100).getSupport().getSupport_force());
//
////        for (int i = 0; i < 0; i++) {
////            mesh.solve();
////
////            double min = 1E13;
////            double max = 0;
////            for (FiniteElement2D element2D : mesh.getElements()) {
////                min = Math.min(min, element2D.getEvaluated_stress().comparable_stress());
////                max = Math.max(max, element2D.getEvaluated_stress().comparable_stress());
////            }
////
////            new Frame(mesh);
////            for (FiniteElement2D element2D : mesh.getElements()) {
////                double stress = element2D.getEvaluated_stress().comparable_stress();
////                double relative_stress = (stress - min) / (max - min + 1);
////                element2D.getMaterial().setE(relative_stress < 0.5 ? element2D.getMaterial().getE() * 0.9d:element2D.getMaterial().getE());
////            }
////        }
//
//        new Frame(mesh).renderMode(Frame.STRESS).renderBoundaryConditions();


    }


    @Override
    public Node new_vertex(double v, double v1, double v2) {
        return new Node(v,v1);
    }

    @Override
    public Edge new_edge(Node node, Node v1) {
        return new Edge(node, v1);
    }

    @Override
    public FiniteElement2D new_face(Edge... edges) {
        if(edges.length == 3){
            return new Triangle(edges[0], edges[1],edges[2]);
        }
        return null;
    }

    @Override
    public Volume new_volume(Face... faces) {
        return new Volume(faces);
    }


}
