package solids_2d.topologieOptimization;

import core.Edge;
import core.tensor.Tensor;
import core.tensor.Tensor2D;
import core.tensor.Tensor3D;
import core.vector.Vector2d;
import neuralnetwork.builder.Builder;
import neuralnetwork.builder.Network;
import neuralnetwork.data.TrainSet;
import neuralnetwork.functions.ReLU;
import neuralnetwork.functions.Sigmoid;
import neuralnetwork.network.*;
import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;
import solids_2d.material.Material;
import solids_2d.meshgeneration.Generator;
import solids_2d.visual.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class NetworkOptimiser {

    private FiniteElement2D[][][] grid;
    private Node[][] nodes;

    private Mesh mesh;
    private ArrayList<Node> boundaryVertices;
    private TrainSet trainSet;

    int targetBoundaryConditions = 3;
    int targetForces = 1;

    public NetworkOptimiser(int subdw, int subdh) {
        this.mesh = Generator.rectangle_mesh(1, 1, subdw, subdh);
        this.findBoundaryVertices();
        this.trainSet = new TrainSet(4, subdw+1, subdh+1,
                1, subdw, subdh);
        this.grid = new FiniteElement2D[subdw][subdh][2];
        for (FiniteElement2D t : mesh.getFaces()) {
            int index_x = (int) (t.center().getX() * subdw);
            int index_y = (int) (t.center().getY() * subdh);
            if (this.grid[index_x][index_y][0] == null) {
                this.grid[index_x][index_y][0] = t;
            } else {
                if (this.grid[index_x][index_y][1] != null) {
                    throw new RuntimeException();
                }
                this.grid[index_x][index_y][1] = t;
            }
        }
        nodes = new Node[subdw+1][subdh+1];
        for (Node t : mesh.getVertices()) {
            int index_x = (int) (t.getPosition2D().getX() * (subdw) + 0.4);
            int index_y = (int) (t.getPosition2D().getY() * (subdh) + 0.4);
            this.nodes[index_x][index_y] = t;
        }
    }

    public void genNewEntry() {
        resetMeshBC();
        resetMeshMaterial();

        Tensor3D in = newInput();
        this.setMeshBC(in);
        mesh.solve();
        new Simple(mesh).run();

        trainSet.addData(in, getMeshOutput());
    }

    public Vector2d indexOfNode(Node node){
        int index_x = (int) (node.getPosition2D().getX() * (nodes.length-1) + 0.4);
        int index_y = (int) (node.getPosition2D().getY() * (nodes[0].length-1) + 0.4);
        return new Vector2d(index_x, index_y);
    }

    public Tensor3D newInput() {
        Tensor3D current = getMeshBC();
        resetMeshBC();
        for (int i = 0; i < targetBoundaryConditions; i++) {
            Node node = boundaryVertices.get((int) (boundaryVertices.size() * Math.random()));
            while(node.getSupport().length() != 0){
                node = boundaryVertices.get((int) (boundaryVertices.size() * Math.random()));
            }
            node.setSupport(new Support(true,true));
        }
        for (int i = 0; i < targetForces; i++) {
            Node node = boundaryVertices.get((int) (boundaryVertices.size() * Math.random()));
            while(node.getSupport().length() != 0){
                node = boundaryVertices.get((int) (boundaryVertices.size() * Math.random()));
            }
            double f_x = Math.random() * 1;
            double f_y = Math.random() * 1;
            node.setForce(new Force(f_x, f_y));
        }
        Tensor3D input = getMeshBC();
        setMeshBC(current);
        return input;
    }

    public void resetMeshBC() {
        mesh.forEach_node(node -> {
            node.setForce(new Force(0, 0));
            node.setSupport(new Support(false, false));
        });
    }

    public void resetMeshMaterial() {
        mesh.forEach_face(new Consumer<FiniteElement2D>() {
            @Override
            public void accept(FiniteElement2D finiteElement2D) {
                finiteElement2D.setMaterial(new Material(1E9, 0.25));
            }
        });
    }

    public void setMeshBC(Tensor in) {
        for (int x = 0; x < grid.length+1; x++) {
            for (int y = 0; y < grid[0].length+1; y++) {
                nodes[x][y].setSupport(new Support(in.get(0,x,y) > 0, in.get(1,x,y) > 0));
                nodes[x][y].setForce(new Force(in.get(2,x,y), in.get(3,x,y)));
            }
        }
    }

    public Tensor3D getMeshBC() {
        Tensor3D out = new Tensor3D(4, this.grid.length + 1, this.grid.length + 1);

        for (int x = 0; x < grid.length+1; x++) {
            for (int y = 0; y < grid[0].length+1; y++) {
                out.set(nodes[x][y].getSupport().getX(), 0, x, y);
                out.set(nodes[x][y].getSupport().getY(), 1, x, y);
                out.set(nodes[x][y].getForce().getX(), 2, x, y);
                out.set(nodes[x][y].getForce().getY(), 3, x, y);
            }
        }
        return out;
    }

    public void setMeshYoung(Tensor out) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                grid[x][y][0].getMaterial().setYoung(1E9 * out.get(0,x,y));
                grid[x][y][1].getMaterial().setYoung(1E9 * out.get(0,x,y));
            }
        }
    }

    public double getMeshYoung(int x, int y) {
        return (grid[x][y][0].getMaterial().getYoung() + grid[x][y][1].getMaterial().getYoung()) / 2;
    }

    public double maxYoung() {
        double max = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                max = Math.max(max, getMeshYoung(x, y));
            }
        }
        return max;
    }

    public Tensor3D getMeshOutput() {
        Tensor3D out = new Tensor3D(1, grid.length, grid[0].length);
        double maxYoung = maxYoung();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                out.set(getMeshYoung(x, y) / maxYoung, 0, x, y);
            }
        }
        return out;
    }

    private void findBoundaryVertices() {
        this.boundaryVertices = new ArrayList<>();
        for (FiniteElement2D t : mesh.getFaces()) {
            for (Edge e : t.getBoundaries()) {
                if (e.getLinkedBoundary() == null) {
                    if (!boundaryVertices.contains(e.getV1())) {
                        boundaryVertices.add((Node) e.getV1());
                    }
                    if (!boundaryVertices.contains(e.getV2())) {
                        boundaryVertices.add((Node) e.getV2());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NetworkOptimiser opt = new NetworkOptimiser(10, 10);


        System.out.print("Loading Trainset... ");
        opt.trainSet = TrainSet.load("resources/network/to3.trst");
        System.out.println("Finished!");

//        System.out.println(opt.trainSet.getInput(0));

//        for(int i = 0; i < 1000; i++){
//            opt.genNewEntry();
//            System.out.println("######################################### " + i + " ##########################################");
//            System.out.println("######################################### " + i + " ##########################################");
//            System.out.println("######################################### " + i + " ##########################################");
//        }
//        opt.trainSet.write("resources/network/to3.trst");



//        System.out.print("Building Network... ");
//        Builder builder = new Builder(4,11,11);
//        builder.addNode("conv1", new ConvolutionNode(1, 3, 1, 0));
//        builder.addNode("conv2", new ConvolutionNode(16, 3, 1, 0));
//        builder.addNode("conv3", new ConvolutionNode(16, 3, 1, 0));
//        builder.addNode("flatten", new FlattenNode());
//        builder.addNode("fc2", new DenseNode(16 * 4 * 4));
//        builder.addNode("shapen", new ShapeNode(16,4,4));
//        builder.addNode("deconv1", new DeconvNode(16, 3, 1, 0));
//        builder.addNode("deconv2", new DeconvNode(16, 3, 1, 0));
//        builder.addNode("deconv3", new DeconvNode(1, 3, 1, 0));
//        Network network = builder.build_network();
//        System.out.println("Finished!");
//        network.print_overview();
//        double e = 0.01;
//        while(e > 1E-4){
//            double avg = 0;
//            for(int i = 0; i < 200; i++){
//                avg += network.train(opt.trainSet.getInput(i), opt.trainSet.getOutput(i), 0.01);
//            }
//            avg /= opt.trainSet.size();
//            System.out.println("error: " + avg);
//            e = avg;
//        }
//        network.write("resources/network/10_conv_deconv.net");




        Network network = Network.load("resources/network/10_conv_deconv.net");
        Tensor3D in = opt.newInput();
        System.out.println(in.getDimension(0) + " " + in.getDimension(1) + " " + in.getDimension(2));
        network.print_overview();
        Tensor3D out = network.calculate(in)[0];
        System.out.println(out.getDimension(0) + " " + out.getDimension(1) + " " + out.getDimension(2));
        opt.setMeshYoung(out);
        opt.setMeshBC(in);

        Frame f = new Frame(opt.mesh).renderMode(1).enableWireframe().renderBoundaryConditions();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    for(int i = 0; i < 50; i++){
                        Thread.sleep(100);
                        Tensor3D in = opt.trainSet.getInput(i);
                        Tensor3D out = network.calculate(in)[0];
                        opt.setMeshYoung(out);
                        opt.setMeshBC(in);
                        f.repaint();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
