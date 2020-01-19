/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solids_2d.visual.application;

import solids_2d.Mesh;
import solids_2d.topologieOptimization.Simple;
import solids_2d.visual.panel.FEM_Panel;
import solids_2d.visual.panel.RenderMode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author finne
 */
public class RunSettings extends javax.swing.JDialog {


    private MeshEditor meshEditor;

    /**
     * Creates new form RunSettings
     */
    public RunSettings(MeshEditor parent) {
        super(parent, true);
        this.meshEditor = parent;
        initComponents();
    }

    private Mesh mesh;

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {


        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        min_young_ratio = new javax.swing.JTextField();
        max_stress = new javax.swing.JTextField();
        max_stress_interrupt = new javax.swing.JTextField();
        cutoff_start = new javax.swing.JTextField();
        cutoff_end = new javax.swing.JTextField();
        cutoff_step = new javax.swing.JTextField();
        cutoff_strength = new javax.swing.JTextField();
        cutoff_stress_limit = new javax.swing.JTextField();
        goButton = new javax.swing.JButton();
        debugButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("min young ratio");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("max stress");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("cutoff start");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("cutoff end");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("cutoff step");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("cutoff strength");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("max stress interrupt");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("cutoff stress limit");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        min_young_ratio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        min_young_ratio.setText("1E-2");

        max_stress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        max_stress.setText("1E5");

        max_stress_interrupt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        max_stress_interrupt.setText("0.95");

        cutoff_start.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cutoff_start.setText("0.1");

        cutoff_end.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cutoff_end.setText("0.8");

        cutoff_step.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cutoff_step.setText("0.03");

        cutoff_strength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cutoff_strength.setText("0.2");

        cutoff_stress_limit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cutoff_stress_limit.setText("0.9");

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        debugButton.setText("Debug");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(debugButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(goButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(min_young_ratio, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                                                        .addComponent(max_stress)
                                                                        .addComponent(max_stress_interrupt)
                                                                        .addComponent(cutoff_start)
                                                                        .addComponent(cutoff_end)
                                                                        .addComponent(cutoff_step)
                                                                        .addComponent(cutoff_strength)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(cutoff_stress_limit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 30, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(min_young_ratio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(max_stress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(max_stress_interrupt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cutoff_start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cutoff_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cutoff_step, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cutoff_strength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cutoff_stress_limit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(debugButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {

        if (mode_go) {
            goButton.setText("Cancel");
            mode_go = false;
        } else {
            mode_go = true;
            goButton.setText("Go");
            if (runningThread != null) {
                runningThread.interrupt();
                runningThread.stop();
                runningThread = null;
                this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                return;
            }
        }

        double v_cutoff_end = Double.parseDouble(cutoff_end.getText());
        double v_cutoff_start = Double.parseDouble(cutoff_start.getText());
        double v_cutoff_step = Double.parseDouble(cutoff_step.getText());
        double v_cutoff_strength = Double.parseDouble(cutoff_strength.getText());
        double v_cutoff_stress_limit = Double.parseDouble(cutoff_stress_limit.getText());
        double v_min_young_ratio = Double.parseDouble(min_young_ratio.getText());
        double v_max_stress = Double.parseDouble(max_stress.getText());
        double v_max_stress_interrupt = Double.parseDouble(max_stress_interrupt.getText());

        boolean debug = debugButton.isSelected();
        final String path=new File(".").getAbsolutePath() + "/debugOutput/"+System.currentTimeMillis()+"/";
        if(debug){
            new File(path).mkdirs();
            System.out.println("debug output: " + new File(path).getAbsolutePath());
        }

        final RunSettings runSettings = this;

        Simple s = new Simple(mesh) {
            @Override
            public void updateListener(String update, double v) {
                runSettings.setTitle(update);
                if(debug){

                    try {
                        Thread.sleep(10);
                        String num = "" + BigDecimal.valueOf(v*100).setScale(5, RoundingMode.HALF_UP).doubleValue();
                        meshEditor.getRenderPanel().renderMode = RenderMode.STRESS;
                        BufferedImage img =
                                meshEditor.getRenderPanel().renderImage(
                                        meshEditor.getRenderPanel().getWidth(),
                                        meshEditor.getRenderPanel().getHeight());
                        System.out.println(num);
                        if(debug)
                            ImageIO.write(img, "PNG", new File(path+"stress"+num+".png"));
                        Thread.sleep(10);
                        meshEditor.getRenderPanel().renderMode = RenderMode.E_MODUL;
                        img = meshEditor.getRenderPanel().renderImage(
                                        meshEditor.getRenderPanel().getWidth(),
                                        meshEditor.getRenderPanel().getHeight());
                        if(debug)
                            ImageIO.write(img, "PNG", new File(path+"young"+num+".png"));

                        Thread.sleep(10);
                    } catch (IOException e) {
                        System.out.println(e);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                meshEditor.updateMeshFromTO(mesh);
                meshEditor.redraw();
            }
        };
        s.cutoff_ratio_strength = v_cutoff_strength;
        s.cutoff_ratio_start = v_cutoff_start;
        s.cutoff_ratio_end = v_cutoff_end;
        s.cutoff_ratio_step = v_cutoff_step;
        s.cutoff_limit = v_cutoff_stress_limit;
        s.min_young_ratio = v_min_young_ratio;
        s.max_stress = v_max_stress;
        s.max_stress_interrupt_ratio = v_max_stress_interrupt;

        runningThread = new Thread(() -> {
            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            s.run();
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.dispose();

            runningThread.interrupt();
        });
        runningThread.start();
    }


    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        goButton.setEnabled(true);
        goButton.setText("Go");
    }


    boolean mode_go = true;
    Thread runningThread = null;

    // Variables declaration - do not modify                     
    private javax.swing.JToggleButton debugButton;
    private javax.swing.JButton goButton;
    private javax.swing.JTextField cutoff_end;
    private javax.swing.JTextField cutoff_start;
    private javax.swing.JTextField cutoff_step;
    private javax.swing.JTextField cutoff_strength;
    private javax.swing.JTextField cutoff_stress_limit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField max_stress;
    private javax.swing.JTextField max_stress_interrupt;
    private javax.swing.JTextField min_young_ratio;
    // End of variables declaration                   
}
