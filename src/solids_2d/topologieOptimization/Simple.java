package solids_2d.topologieOptimization;

import core.Edge;
import solids_2d.Mesh;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;
import solids_2d.elements.FiniteElement2D;
import solids_2d.material.Material;
import solids_2d.meshgeneration.Generator;
import solids_2d.solution.Stress2D;
import solids_2d.visual.Frame;
import tools.Loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

public class Simple {


    Mesh mesh;

    public Simple(Mesh mesh) {
        this.mesh = mesh;
    }


    public double min_young_ratio = 1E-2;
    public double max_stress = 1E5;
    public double max_stress_interrupt_ratio = 0.99;
    public double cutoff_limit = 0.9;

    public double cutoff_ratio_start = 0.1;
    public double cutoff_ratio_end = 0.8;
    public double cutoff_ratio_step = 0.03;

    public double cutoff_ratio_strength = 0.2;

    public void run() {

        double min_young = max_young(mesh) * min_young_ratio;

        for(double cut = cutoff_ratio_start; cut <= cutoff_ratio_end; cut+= cutoff_ratio_step){

            mesh.solve();

            //highest stress
            ArrayList<FiniteElement2D> sorted = this.sorted();
            double max_stress = sorted.get(sorted.size() - 1).getStress2D().von_mises();


            System.out.println("cutoff ratio: " + cut);
            System.out.println("   max stress: " + max_stress);
            this.updateListener("cutoff ratio: " + cut, cut);

            if (max_stress > max_stress_interrupt_ratio * this.max_stress) {
                System.out.println("max stress exceeded: " + max_stress + " > " + max_stress_interrupt_ratio+ " * " + this.max_stress);
                return;
            }

            for (int i = 0; i < sorted.size() * cut; i++) {
                FiniteElement2D element = sorted.get(i);
                Material m = element.getMaterial();
                Stress2D s = element.getStress2D();
                if (s.von_mises() < max_stress * cutoff_limit)
                    m.setYoung(m.getYoung() * (1-cutoff_ratio_strength));
                if (m.getYoung() < min_young) {
                    m.setYoung(min_young);
                }
            }
        }


    }

    public static double avg_stress(FiniteElement2D element2D){
        double s1 = element2D.getStress2D().von_mises();
        int counter = 1;
        for(Edge e:element2D.getBoundaries()){
            if(e.hasLinkedBoundary()){
                s1 += ((FiniteElement2D)e.getLinkedBoundary().getCell()).getStress2D().von_mises();
                counter ++;
            }
        }
        return s1 / counter;
    }

    public static double max_young(Mesh mesh){
        double max = 0;
        for(FiniteElement2D e:mesh.getFaces()){
            max = Math.max(e.getMaterial().getYoung(), max);
        }
        return max;
    }

    public ArrayList<FiniteElement2D> sorted() {
        ArrayList<FiniteElement2D> shallow = new ArrayList<>(this.mesh.getFaces());
        shallow.sort(new Comparator<FiniteElement2D>() {
            @Override
            public int compare(FiniteElement2D o1, FiniteElement2D o2) {
                return Double.compare(avg_stress(o1), avg_stress(o2));
                //return Double.compare(o1.getStress2D().von_mises(), o2.getStress2D().von_mises());
            }
        });
        return shallow;
    }

    public void updateListener(String update, double p){

    }

    public static void main(String[] args) throws IOException {

        int n = 80, i = 20;
        Mesh m = Generator.rectangle_mesh(4, 1, n, i);
        m.getVertices().get(0).setSupport(new Support(true,true));
        m.getVertices().get(3).setSupport(new Support(true,true));
        m.getVertices().get(42).setSupport(new Support(true,true));

        m.getVertices().get(1638).setSupport(new Support(true,true));
        m.getVertices().get(1660).setSupport(new Support(true,true));
        m.getVertices().get(1680).setSupport(new Support(true,true));

        m.getVertices().get(840).setForce(new Force(0,1000));
        m.getVertices().get(862).setForce(new Force(0,1000));

//        m.getNodes().get(n / 3).setSupport(new Support(true,true));
//        m.getNodes().get(n / 3 * 2).setSupport(new Support(true,true));

//        m.getNodes().get((n+1) * (int)(i / 3)).setSupport(new Support(true,true));
//        m.getNodes().get((n+1) * (int)(i / 3) + n).setSupport(new Support(true,true));


//        for(int k = 10; k < n -10; k++){
//            m.getNodes().get((n+1) * (i+1) - k).setForce(new Force(0,-10));
//        }

//

//        m.getNodes().get(0).setSupport(new Support(true,true));
//        m.getNodes().get(80).setSupport(new Support(true,true));
//
//        m.getNodes().get((n + 1) * (i + 1) - 1 - 42).setForce(new Force(0, -100));
//        m.getNodes().get(81 * 21 - 1 - 41).setForce(new Force(0, -100));
//        m.getNodes().get(81 * 21 - 1 - 40).setForce(new Force(0, -100));
//        m.getNodes().get(81 * 21 - 1 - 39).setForce(new Force(0, -100));
//        m.getNodes().get(81 * 21 - 1 - 38).setForce(new Force(0, -100));


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
        //Simple s = new Simple(m);
        //s.run();


        //Loader.write("rect_optimized_load_bottom.mesh", m);

        new Frame(m).renderMode(0).renderBoundaryConditions();

    }

}
