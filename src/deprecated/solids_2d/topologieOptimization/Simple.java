package deprecated.solids_2d.topologieOptimization;

import deprecated.solids_2d.Mesh;
import deprecated.solids_2d.constraint.Force;
import deprecated.solids_2d.constraint.Support;
import deprecated.solids_2d.elements.FiniteElement2D;
import deprecated.solids_2d.material.Material;
import deprecated.solids_2d.meshgeneration.Generator;
import deprecated.solids_2d.solution.Stress;
import deprecated.solids_2d.visual.Frame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class Simple {


    Mesh mesh;

    public Simple(Mesh mesh) {
        this.mesh = mesh;
    }


    public void run() {

        double min_young = 1E4;

        double max_stress = 1E5;
        double interrupt = 0.99;

        double cutoff_limit = 0.9;  //will not cutoff if stress is above cutoff_limit * max_stress
        double cutoff_ratio = 0.1;  //amount of nodes to be cut of
        double cutoff_value = 0.5;  //change of young's-module per cutoff

        while (cutoff_ratio < 0.8) {
            mesh.solve();

            ArrayList<FiniteElement2D> sorted = this.sorted();
            double highest = sorted.get(sorted.size() - 1).getEvaluated_stress().von_mises();
            System.err.println(cutoff_ratio);

            if (highest > interrupt * max_stress) return;


            for (int i = 0; i < sorted.size() * cutoff_ratio; i++) {
                FiniteElement2D element = sorted.get(i);
                Material m = element.getMaterial();
                Stress s = element.getEvaluated_stress();
                if (s.von_mises() < highest * cutoff_limit)
                    m.setYoung(m.getYoung() * cutoff_value);
                if (m.getYoung() < min_young) {
                    m.setYoung(min_young);
                }
            }

            cutoff_ratio += 0.03;
        }
    }

    public ArrayList<FiniteElement2D> sorted() {
        ArrayList<FiniteElement2D> shallow = new ArrayList<>(this.mesh.getElements());
        shallow.sort(new Comparator<FiniteElement2D>() {
            @Override
            public int compare(FiniteElement2D o1, FiniteElement2D o2) {
                return Double.compare(o1.getEvaluated_stress().von_mises(), o2.getEvaluated_stress().von_mises());
            }
        });
        return shallow;
    }


    public static void main(String[] args) {

        int n = 80, i = 20;
        Mesh m = Generator.rectangle_mesh(4, 1, n, i);

//        m.getNodes().get(n / 3).setSupport(new Support(true,true));
//        m.getNodes().get(n / 3 * 2).setSupport(new Support(true,true));

//        m.getNodes().get((n+1) * (int)(i / 3)).setSupport(new Support(true,true));
//        m.getNodes().get((n+1) * (int)(i / 3) + n).setSupport(new Support(true,true));


//        for(int k = 10; k < n -10; k++){
//            m.getNodes().get((n+1) * (i+1) - k).setForce(new Force(0,-10));
//        }

//

        m.getNodes().get(0).setSupport(new Support(true,true));
        m.getNodes().get(80).setSupport(new Support(true,true));

        m.getNodes().get((n + 1) * (i + 1) - 1 - 42).setForce(new Force(0, -100));
        m.getNodes().get(81 * 21 - 1 - 41).setForce(new Force(0, -100));
        m.getNodes().get(81 * 21 - 1 - 40).setForce(new Force(0, -100));
        m.getNodes().get(81 * 21 - 1 - 39).setForce(new Force(0, -100));
        m.getNodes().get(81 * 21 - 1 - 38).setForce(new Force(0, -100));


//        for(int j = 0; j < (n+1)*(i+1); j+=(n+1)){
//            m.getNodes().get(j).setSupport(new Support(true,true));
//        }
//        m.getNodes().get(n).setForce(new Force(0,-10));


        m.forEach_face(new Consumer<FiniteElement2D>() {
            @Override
            public void accept(FiniteElement2D finiteElement2D) {
                finiteElement2D.setMaterial(new Material(1E10, 0.25));
            }
        });
        m.solve();
        Simple s = new Simple(m);
        s.run();

        new Frame(m);

    }

}
