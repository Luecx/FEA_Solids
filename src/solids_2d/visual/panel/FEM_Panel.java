package solids_2d.visual.panel;


import core.Vertex;
import core.vector.Vector2d;
import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.elements.FiniteElement2D;
import solids_2d.elements.Triangle;
import solids_2d.solution.Stress2D;
import visuals.ColorPalette;
import visuals.Panel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public class FEM_Panel extends Panel implements KeyListener, MouseListener {



    public RenderMode renderMode = RenderMode.NONE;
    public boolean renderIDs;
    public boolean renderBCs;
    public boolean renderMohr;
    public boolean renderKeys;
    public boolean renderGrid;
    public boolean renderWireframe;
    public boolean renderColorPalette = true;

    private Mesh mesh;

    public Node highlightedNode;
    public double upper_scalar = 1, lower_scaler = 1;

    private boolean render_image = false;
    private int render_image_width = 2560;
    private int render_image_height = 1440;


    public FEM_Panel(Mesh mesh) {
        this.setMesh(mesh);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
        this.highlightedNode = null;

        if (this.mesh == null) return;

        double min_x, max_x, min_y, max_y;
        min_x = 100000000;
        max_x = -100000000;
        min_y = 100000000;
        max_y = -100000000;

        for (Node n : mesh.getVertices()) {
            if (n.getPosition().getX() < min_x) min_x = n.getPosition().getX();
            if (n.getPosition().getY() < min_y) min_y = n.getPosition().getY();

            if (n.getPosition().getX() > max_x) max_x = n.getPosition().getX();
            if (n.getPosition().getY() > max_y) max_y = n.getPosition().getY();
        }

        double center_x = (max_x + min_x) / 2;
        double center_y = (max_y + min_y) / 2;

        double zoom = 2;

        max_x = center_x - (center_x - max_x) * zoom;
        min_x = center_x - (center_x - min_x) * zoom;
        max_y = center_y - (center_y - max_y) * zoom;
        min_y = center_y - (center_y - min_y) * zoom;

        double max_scale = Math.max(max_x - min_x, max_y - min_y);

        this.setCenter(new Vector2d(center_x, center_y));
        this.setScale(new Vector2d(max_scale, max_scale));


    }

    public Vector2d moved_position(Node a) {
        return a.getPosition2D().add(a.getDisplacement());
    }

    public Node closestNode(Vector2d mousePosition) {
        Node closest = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Node n : mesh.getVertices()) {
            double k = moved_position(n).sub(mousePosition).length();
            if (k < dist) {
                dist = k;
                closest = n;
            }
        }
        return closest;
    }

    private double value_to_display_vertex(Node e) {

        try {
            switch (renderMode) {
                case DISPLACEMENT:
                    return e.getDisplacement().length();
                case DISPLACEMENT_X:
                    return e.getDisplacement().getX();
                case DISPLACEMENT_Y:
                    return e.getDisplacement().getY();
                case NONE:
                    return 0;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }


    private double value_to_display_face(FiniteElement2D e) {
        try {
            switch (renderMode) {
                case STRESS:
                    return e.getStress2D().comparable_stress();
                case E_MODUL:
                    return e.getMaterial().getYoung();
                case STRESS_X:
                    return e.getStress2D().getSigma_x();
                case STRESS_Y:
                    return e.getStress2D().getSigma_y();
                case STRESS_XY:
                    return e.getStress2D().getTau();
                case STRAIN:
                    return e.getStrain().length();
                case NONE:
                    return 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }


    @Override
    protected Vector2d toScreenSpace(Vector2d vector2d) {
        Vector2d rect = new Vector2d(scale);
        double aspectRatio = this.getWidth() / (double)this.getHeight();
        scale.setX(rect.getX() * aspectRatio);
        Vector2d retu =  super.toScreenSpace(vector2d);
        this.scale = rect;
        return retu;
    }

    @Override
    protected Vector2d toWorldSpace(Vector2d screenSpace) {
        Vector2d rect = new Vector2d(scale);
        double aspectRatio = this.getWidth() / (double)this.getHeight();
        scale.setX(rect.getX() * aspectRatio);
        Vector2d retu =  super.toWorldSpace(screenSpace);
        this.scale = rect;
        return retu;
    }


    @Override
    public int getWidth() {
        if (render_image == false)
            return super.getWidth();
        return render_image_width;
    }

    @Override
    public int getHeight() {
        if (render_image == false)
            return super.getHeight();
        return render_image_height;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.render((Graphics2D) g);
    }

    public BufferedImage renderImage(int width, int height) {
        this.render_image = true;
        this.render_image_height = height;
        this.render_image_width = width;

        BufferedImage bImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        render(cg);

        this.render_image = false;

        return bImg;
    }


    private void render(Graphics2D g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.white);
        g.fillRect(0,   0,this.getWidth(), this.getHeight());
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (renderGrid) {
            g.setColor(Color.lightGray);
            this.draw_grid(g);
        }


        if (mesh != null) {
            double min = min_val();
            double max = max_val();
            render_cells(g, min, max);

            if (renderMohr) {
                render_mohr(g);
            }

            if (renderIDs) {
                render_ids(g);
            }

            if (this.renderBCs) {
                render_bc(g);
            }
            if (this.renderColorPalette) {
                render_cp(g, min, max);
            }
            render_information(g);
        }

        if (this.renderKeys) {
            render_keys(g);
        }

        if (this.highlightedNode != null) {
            this.render_highlighted_node(g);
        }

    }

    private void render_cp(Graphics2D g, double min, double max) {
        ColorPalette.RAINBOW.drawColorPalette(g, min, max, 10, 300);
        String s = RenderMode.values()[renderMode.getIndex()+1].toString();
        g.drawString(s, 30, 340);
    }

    private double max_val() {
        double max_val = Double.NEGATIVE_INFINITY;

        if(renderMode.getContent() == RenderContent.FACE){
            for (FiniteElement2D e : mesh.getFaces()) {
                double k = value_to_display_face(e);
                max_val = Math.max(k, max_val);
            }
        }else{
            for (Node e : mesh.getVertices()) {
                double k = value_to_display_vertex(e);
                max_val = Math.max(k, max_val);
            }
        }

        return max_val * this.upper_scalar;
    }

    private double min_val() {
        double min_val = Double.POSITIVE_INFINITY;
        if(renderMode.getContent() == RenderContent.FACE){
            for (FiniteElement2D e : mesh.getFaces()) {
                double k = value_to_display_face(e);
                min_val = Math.min(k, min_val);
            }
        }else{
            for (Node e : mesh.getVertices()) {
                double k = value_to_display_vertex(e);
                min_val = Math.min(k, min_val);
            }
        }
        return min_val * this.lower_scaler;
    }

    private void render_highlighted_node(Graphics2D g) {
        Vector2d screen = toScreenSpace(moved_position(highlightedNode));
        g.setColor(Color.red);
        g.fillRect((int) screen.getX() - 5, (int) screen.getY() - 5, 11, 11);
    }

    private void render_cells(Graphics2D g, double min, double max) {

        g.setStroke(new BasicStroke(1));

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        for (int i = 0; i < mesh.getFaces().size(); i++) {
            FiniteElement2D e = mesh.getFaces().get(i);

            int[] x_points = new int[e.node_count() + 1];
            int[] y_points = new int[e.node_count() + 1];
            for (int n = 0; n < e.node_count() + 1; n++) {
                Vector2d screenSpace = toScreenSpace(moved_position(e.getNode(n % e.node_count())));

                x_points[n] = (int) screenSpace.getX();
                y_points[n] = (int) screenSpace.getY();
            }

            if (max != 0) {

                if(renderMode.getContent() == RenderContent.VERTEX){
                    double f1 = Math.max(0, Math.min(1, (value_to_display_vertex(e.getNode(0)) - min) / (max - min)));
                    double f2 = Math.max(0, Math.min(1, (value_to_display_vertex(e.getNode(1)) - min) / (max - min)));
                    double f3 = Math.max(0, Math.min(1, (value_to_display_vertex(e.getNode(2)) - min) / (max - min)));
                    draw_triangle_gradient(g,
                            new Polygon(x_points, y_points, 4),
                            ColorPalette.RAINBOW.apply(f1),
                            ColorPalette.RAINBOW.apply(f2),
                            ColorPalette.RAINBOW.apply(f3)
                    );
                }else{
                    double fac = 0;
                    if (max != min) {
                        fac = Math.max(0, Math.min(1, (value_to_display_face(e) - min) / (max - min)));
                    }
                    Color c = ColorPalette.RAINBOW.apply((float) fac);
                    g.setColor(c);
                    g.fillPolygon(x_points, y_points, 3);
                }
            }

        }
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if(renderWireframe){
            for (int i = 0; i < mesh.getFaces().size(); i++) {
                FiniteElement2D e = mesh.getFaces().get(i);
                int[] x_points = new int[e.node_count() + 1];
                int[] y_points = new int[e.node_count() + 1];
                for (int n = 0; n < e.node_count() + 1; n++) {
                    Vector2d screenSpace = toScreenSpace(moved_position(e.getNode(n % e.node_count())));
                    x_points[n] = (int) screenSpace.getX();
                    y_points[n] = (int) screenSpace.getY();
                }
                g.setColor(Color.black);
                g.setStroke(new BasicStroke(1));
                g.drawPolygon(x_points, y_points, 3);
            }
        }

    }

    private void render_ids(Graphics2D g) {
        for (int i = 0; i < mesh.getVertices().size(); i++) {
            Node node = mesh.getVertices().get(i);
            Vector2d a1 = toScreenSpace(moved_position(node));

            if (this.renderIDs) {
                g.setColor(Color.green);
                g.drawString(i + "", (int) (double) a1.getX(), (int) (double) a1.getY());
            }

        }
    }

    private void render_bc(Graphics2D g) {
        for (int i = 0; i < mesh.getVertices().size(); i++) {
            Node node = mesh.getVertices().get(i);
            Vector2d a1 = toScreenSpace(moved_position(node));

            g.setColor(Color.black);
            if (node.getSupport().getX() != 0) {
                draw_arrow(g, a1.sub(new Vector2d(60, 0)), a1, 10, 25, 25);
            }
            if (node.getSupport().getY() != 0) {
                draw_arrow(g, a1.add(new Vector2d(0, 60)), a1, 10, 25, 25);
            }
            if (node.getForce() != null) {
                Vector2d force = new Vector2d(node.getForce()).self_normalise().self_scale(60);
                force.setX(force.getX() * -1);
                draw_arrow(g, a1.add(force), a1, 10, 25, 25);
            }

        }
    }

    private void render_mohr(Graphics2D g) {
        for (int i = 0; i < mesh.getFaces().size(); i++) {
            FiniteElement2D e = mesh.getFaces().get(i);
            if (e instanceof Triangle) {


                Stress2D s = e.getStress2D();
                double a = s.mohr_angle();

                Vector2d cen = moved_position(e.getNode(0)).add(
                        moved_position(e.getNode(1)).add(
                                moved_position(e.getNode(2))
                        )
                ).self_scale(1/3d);

                Vector2d direction = new Vector2d(30 * Math.cos(a),- 30 * Math.sin(a));

                Vector2d center = toScreenSpace(new Vector2d(cen.getX(), cen.getY())).sub(direction);
                Vector2d target = center.add(direction.scale(2));

                g.setColor(Color.black);
                g.setStroke(new BasicStroke(1));
                g.drawLine((int)center.getX(), (int)center.getY(), (int)target.getX(), (int)target.getY());
                //draw_arrow(g, center,target, 3, 3, 10);
            }
        }
    }

    private void render_keys(Graphics2D g) {
        String[] strings = {
                "Keybinds: (ctrl + key)",
                " ",
                "zoom in: NUMPAD +",
                "zoom out: NUMPAD -",
                "move vertical: Arrow up/down",
                "move horizontal: Arrow left/right",
                "render renderWireframe: w",
                "render boundary conditions: b",
                "render node ids: i",
                "render mohr arrows: m",
                "render key_binds: k",
                "render grid: g",
                " ",
                "render nothing: 0",
                "render von_mises stress: 1",
                "render displacement: 2",
                "render E-Module: 3",
                "render sigma_x: 4",
                "render sigma_y: 5",
                "render tau: 6",
                "render displacement_x: 7",
                "render displacement_y: 8",
                "render strain: 98",
                " ",
                "decrease lower limit: r",
                "increase lower limit: t",
                "decrease upper limit: z",
                "increase upper limit: u",
        };


        Font font = new Font("Arial", 1, 16);
        g.setFont(font);
        g.setColor(Color.black);
        for (int i = 0; i < strings.length; i++) {
            g.drawString(strings[i], 20, 400 + 16 * i);
        }


    }

    private void render_information(Graphics2D g) {
        String[] strings = {
                "faces:" + mesh.getFaces().size(),
                "edges:" + mesh.getEdges().size() + " (internal edge count)",
                "vertices:" + mesh.getVertices().size(),
                "matrix size:" + (mesh.getVertices().size() * 2 - 3) + " (upper limit)"
        };

        Font font = new Font("Arial", 1, 16);
        g.setFont(font);
        g.setColor(Color.black);
        for (int i = 0; i < strings.length; i++) {
            g.drawString(strings[i], 140, 30 + 20 * i);
        }
    }

    public void process_key_event(int extendedKeyCode) {
        switch (extendedKeyCode) {
            case 37:
                move(-0.1, 0);
                break;
            case 38:
                move(0, +0.1);
                break;
            case 39:
                move(+0.1, 0);
                break;
            case 40:
                move(0, -0.1);
                break;

            case 107:
                zoom(0.8);
                break;
            case 109:
                zoom(1.25);
                break;

            case 87:
                renderWireframe = !renderWireframe;
                break; //w
            case 66:
                renderBCs = !renderBCs;
                break; //b
            case 73:
                renderIDs = !renderIDs;
                break; //i
            case 77:
                renderMohr = !renderMohr;
                break; //m
            case 75:
                renderKeys = !renderKeys;
                break; //k
            case 71:
                renderGrid = !renderGrid;
                break; //g
            case 49:
                renderMode = RenderMode.STRESS;
                break;
            case 50:
                renderMode = RenderMode.DISPLACEMENT;
                break;
            case 51:
                renderMode = RenderMode.E_MODUL;
                break;
            case 52:
                renderMode = RenderMode.STRESS_X;
                break;
            case 53:
                renderMode = RenderMode.STRESS_Y;
                break;
            case 54:
                renderMode = RenderMode.STRESS_XY;
                break;
            case 55:
                renderMode = RenderMode.DISPLACEMENT_X;
                break;
            case 56:
                renderMode = RenderMode.DISPLACEMENT_Y;
                break;
            case 48:
                renderMode = RenderMode.NONE;
                this.renderWireframe = true;
                break;

            case 82:
                lower_scaler *= 0.6;
                break; //r
            case 84:
                lower_scaler *= 1 / 0.6;
                break; //t
            case 90:
                upper_scalar *= 0.6;
                break; //z
            case 85:
                upper_scalar *= 1 / 0.6;
                break; //u
        }


        this.repaint();
    }

    public Node process_mouse_click(int screen_x, int screen_y) {
        //this.mousePosition = this.toWorldSpace(new Vector2d(screen_x-8, screen_y-28));
        Vector2d mousePos = this.toWorldSpace(new Vector2d(screen_x, screen_y));
        this.repaint();
        return closestNode(mousePos);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        process_key_event(e.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.grabFocus();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.grabFocus();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.grabFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.grabFocus();

    }
}
