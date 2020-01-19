package solids_2d.visual;

import core.matrix.Matrix;
import core.vector.DenseVector;
import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;
import solids_2d.material.Material;
import solids_2d.visual.panel.FEM_Panel;
import solids_2d.visual.panel.Frame;
import solids_2d.visual.panel.RenderMode;
import tools.Generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Cases {

    public static Mesh arc_1() {

        double l = 0.1;
        Mesh m = tools.Generator.arc_mesh(new Mesh(), l, 0.07, 0.4, 90, 150, 150 );
        m.forEach_node(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if (node.getPosition().getY() == -l) {
                    node.setSupport(new Support(true, true));
                }
                if (node.getPosition().getX() > l - 1E-5) {
                    node.setForce(new Force(0, -0.01));
                }
            }
        });
        m.forEach_face(new Consumer<FiniteElement2D>() {
            @Override
            public void accept(FiniteElement2D finiteElement2D) {
                finiteElement2D.setMaterial(new Material(1E10, 0.25));
            }
        });
        return m;
    }

    public static void init_testing() {
        Node n1 = new Node(0, 0);
        Node n2 = new Node(0, 2);
        Node n3 = new Node(-3, 2);
        Node n4 = new Node(-3, 0);

        Triangle t1 = new Triangle(n4,n1,n2);
        Triangle t2 = new Triangle(n3,n2,n4);

        t1.setThickness(0.5);
        t2.setThickness(0.5);

        t1.setMaterial(new Material(30 * 1E6, 0.25));
        t2.setMaterial(new Material(30 * 1E6, 0.25));

        n1.setSupport(new Support(false, true));
        n3.setSupport(new Support(true, true));
        n4.setSupport(new Support(true, true));
        n2.setForce(new Force(0, -1000));

        ArrayList<Node> vertices = new ArrayList<>();
        vertices.add(n1);
        vertices.add(n2);
        vertices.add(n3);
        vertices.add(n4);
        Mesh mesh = new Mesh(t1, t2);
        mesh.setVertices(vertices);

        t1.prepare();
        t2.prepare();

        Matrix m = mesh.build_reduced_stiffnes_matrix();
        DenseVector v = mesh.build_reduced_load_vector();


//        System.out.println(t1.generate_reduced_stiffness_matrix());
//        System.out.println(t2.generate_reduced_stiffness_matrix());
        mesh.solve();


        System.out.println(t1.getStress2D());
        System.out.println(t2.getStress2D());
        //System.out.println(mesh.build_complete_stiffnes_matrix());
        //System.out.println(mesh.build_reduced_stiffnes_matrix());
        new Frame(mesh).renderMode(RenderMode.STRESS).renderBoundaryConditions().enableWireframe();
    }

    public static Mesh rectangle_1(double w, double h, int subd_w, int subd_h, double force) {
        Mesh mesh = Generator.rectangle_mesh(new Mesh(), w, h, subd_w, subd_h);

        for (int i = 0; i < subd_h + 1; i++) {
            mesh.getVertices().get(i * (subd_w + 1)).setSupport(new Support(true, true));
            mesh.getVertices().get(i * (subd_w + 1) + subd_w).setForce(new Force(force, 0));
        }

        mesh.forEach_face(finiteElement2D -> finiteElement2D.setMaterial(new Material(70E9, 0.5)));
        mesh.forEach_face(finiteElement2D -> finiteElement2D.setThickness(0.01));

        return mesh;
    }

    public static void main(String[] args) throws IOException {
//        Mesh m = arc_1();
//        m.solve();
//        new Simple(m).run();
//        Loader.write("TestEditor.java.mesh", m);


//        Mesh loaded = new Mesh();
//        Loader.load("TestEditor.java.mesh",loaded);
//        new Frame(loaded);


//        Node[][] nodes = Generator.rectangle_mesh_nodes(3,1,150,50);
//        Mesh mesh = Generator.connect_rectangular_nodes(nodes);
//        nodes[0][0].setSupport(new Support(true,true));
//        nodes[0][50].setSupport(new Support(true,true));
//        nodes[150][0].setForce(new Force(0,-100));
//
//
//        new Simple(mesh).run();
//        Loader.write("rect_2.mesh", mesh);

//        Mesh mesh = new Mesh();
//        Loader.load("C:\\Users\\finne\\Desktop\\rect_100_500.mesh", mesh);
//
//        new Simple(mesh){
//            @Override
//            public void updateListener(String update, double p) {
//                try {
//                    Loader.write("C:\\Users\\finne\\Desktop\\rect_100_500_"+p+".mesh", mesh);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.run();
        init_testing();

    }


}
