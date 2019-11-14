package deprecated.solids_2d.visual;

import core.vector.Vector2d;
import deprecated.solids_2d.Mesh;
import deprecated.solids_2d.Node;
import deprecated.solids_2d.constraint.Force;
import deprecated.solids_2d.constraint.Support;
import deprecated.solids_2d.elements.FiniteElement2D;
import deprecated.solids_2d.elements.Triangle;
import deprecated.solids_2d.material.Material;
import deprecated.solids_2d.meshgeneration.Generator;

import java.util.function.Consumer;

public class Cases {

    public static Mesh arc_1(){

        double l = 0.1;
        Mesh m = Generator.arc_mesh(l,0.07,0.4,90,50,50);
        m.forEach_node(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                if(node.getPosition().getY() == -l){
                    node.setSupport(new Support(true,true));
                }
                if(node.getPosition().getX() > l - 1E-5){
                    node.setForce(new Force(0,-0.01));
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
        Node n1 = new Node(new Vector2d(0, 0));
        Node n2 = new Node(new Vector2d(0, 2));
        Node n3 = new Node(new Vector2d(-3, 2));
        Node n4 = new Node(new Vector2d(-3, 0));

        Triangle t1 = new Triangle(n1, n2, n4);
        Triangle t2 = new Triangle(n2, n3, n4);

        t1.setThickness(0.5);
        t2.setThickness(0.5);

        t1.setMaterial(new Material(30 * 1E2, 0.25));
        t2.setMaterial(new Material(30 * 1E2, 0.25));

        n1.setSupport(new Support(false, true));
        n3.setSupport(new Support(true, true));
        n4.setSupport(new Support(true, true));
        n2.setForce(new Force(0, -1));

        Mesh mesh = new Mesh();
        mesh.getElements().add(t1);
        mesh.getElements().add(t2);
        mesh.getNodes().add(n1);
        mesh.getNodes().add(n2);
        mesh.getNodes().add(n3);
        mesh.getNodes().add(n4);

        //mesh.solve();

        //System.out.println(mesh.build_complete_stiffnes_matrix());
        System.out.println(mesh.build_reduced_stiffnes_matrix());
        //new Frame(mesh);
    }

    public static Mesh rectangle_1(double w, double h, int subd_w, int subd_h, double force) {
        Mesh mesh = Generator.rectangle_mesh(w,h,subd_w,subd_h);

        for(int i = 0; i < subd_h+1; i++){
            mesh.getNodes().get(i * (subd_w+1)).setSupport(new Support(true,true));
            mesh.getNodes().get(i * (subd_w+1) + subd_w).setForce(new Force(force, 0));
        }

        mesh.forEach_face(finiteElement2D -> finiteElement2D.setMaterial(new Material(70E9, 0.5)));
        mesh.forEach_face(finiteElement2D -> finiteElement2D.setThickness(1));

        return mesh;
    }

    public static void main(String[] args) {
//        //arc_1();
////        //rectangle_1(10,1,100,10,100000);
////        Mesh m = arc_1();
////        m.solve();
////        new Simple(m).run();
////        new Frame(m);
        init_testing();
    }


}
