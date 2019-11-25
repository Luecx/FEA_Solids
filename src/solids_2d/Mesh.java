package solids_2d;

import core.Edge;
import core.Face;
import core.Volume;
import core.matrix.Matrix;
import core.matrix.sparse_matrix.HashMatrix;
import core.matrix.sparse_matrix.SparseMatrix;
import core.solver.direct.Solver;
import core.threads.Pool;
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
        Matrix matrix = this.build_reduced_stiffnes_matrix();
        DenseVector loads = this.build_reduced_load_vector();
        DenseVector displacements = Solver.precon_conjugate_gradient(matrix, loads, Pool.getAvailableProcessors());
        //DenseVector displacements = Solver.conjugate_gradient(matrix, loads, 1);


        this.apply_solution(displacements);
        this.calculate_stresses();

        return displacements;
    }

    public DenseVector solve(DenseVector x_0) {
        Matrix matrix = this.build_reduced_stiffnes_matrix();
        DenseVector loads = this.build_reduced_load_vector();
        DenseVector displacements = Solver.precon_conjugate_gradient(matrix, loads, x_0,Pool.getAvailableProcessors());

        this.apply_solution(displacements);
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
    public structs.Mesh<Node, Edge, FiniteElement2D, Volume> new_mesh() {
        return new Mesh();
    }

    @Override
    public Volume new_volume(FiniteElement2D... faces) {
        return new Volume(faces);
    }


}
