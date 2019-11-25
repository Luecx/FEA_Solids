/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solids_2d.visual.application;

import solids_2d.Mesh;
import solids_2d.Node;
import solids_2d.constraint.Force;
import solids_2d.constraint.Support;
import solids_2d.elements.FiniteElement2D;
import solids_2d.material.Material;
import solids_2d.material.Materials;
import solids_2d.meshgeneration.Generator;
import solids_2d.visual.FEM_Panel;
import tools.Loader;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author finne
 */
public class MeshEditor extends javax.swing.JFrame implements MouseListener {

    /**
     * Creates new form MeshEditor
     */
    public MeshEditor(Mesh mesh) {
        meshGen = new MeshGenerator(this);
        toSimple = new RunSettings(this);
        console = new Console();
        console.setVisible(true);
        renderToPng = new RenderToPng(this);
        jPanel1 = new FEM_Panel(mesh);
        //jPanel1.addKeyListener(jPanel1);
        jPanel1.addMouseListener(jPanel1);
        jPanel1.setFocusable(true);
        jPanel1.renderColorPalette = true;
        this.addMouseListener(jPanel1);
        jPanel1.addMouseListener(this);
        initComponents();
        this.renderGrid(true);
        this.renderWireframe(true);
        this.renderLoads(true);
        this.renderKeys(false);
        this.renderMohr(false);
        this.radio_nothingActionPerformed(null);
        this.setVisible(true);
        this.popup_missing_resources();
    }

    public Mesh getMesh() {
        return this.jPanel1.getMesh();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        selective_combo = new javax.swing.JComboBox<>();
        selective_combo_panel = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        selective_supp_x = new javax.swing.JRadioButton();
        selective_supp_y = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        selective_force_x = new javax.swing.JTextField();
        selective_force_y = new javax.swing.JTextField();
        selective_gen = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        selective_x_pos = new javax.swing.JLabel();
        selective_y_pos = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        auto_max_x = new javax.swing.JTextField();
        auto_min_x = new javax.swing.JTextField();
        auto_min_y = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        auto_max_y = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        auto_combo = new javax.swing.JComboBox<>();
        auto_combo_panel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        auto_supp_x = new javax.swing.JRadioButton();
        auto_supp_y = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auto_force_x = new javax.swing.JTextField();
        auto_force_y = new javax.swing.JTextField();
        auto_gen = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        item_save_mesh = new javax.swing.JMenuItem();
        item_open_mesh = new javax.swing.JMenuItem();
        menu_mesh = new javax.swing.JMenu();
        item_export_png = new javax.swing.JMenuItem();
        menu_export = new javax.swing.JMenu();
        file_sep_1 = new javax.swing.JPopupMenu.Separator();
        item_new_mesh = new javax.swing.JMenuItem();
        mesh_sep_1 = new javax.swing.JPopupMenu.Separator();
        menu_material = new javax.swing.JMenu();
        item_new_material = new javax.swing.JMenuItem();
        mat_sep_1 = new javax.swing.JPopupMenu.Separator();
        menu_visual = new javax.swing.JMenu();
        item_wireframe = new javax.swing.JCheckBoxMenuItem();
        item_grid = new javax.swing.JCheckBoxMenuItem();
        item_loads = new javax.swing.JCheckBoxMenuItem();
        item_mohr = new javax.swing.JCheckBoxMenuItem();
        item_keys = new javax.swing.JCheckBoxMenuItem();
        visual_sep_1 = new javax.swing.JPopupMenu.Separator();
        radio_nothing = new javax.swing.JRadioButtonMenuItem();
        radio_stress = new javax.swing.JRadioButtonMenuItem();
        radio_displacement = new javax.swing.JRadioButtonMenuItem();
        radio_young = new javax.swing.JRadioButtonMenuItem();
        radio_stress_x = new javax.swing.JRadioButtonMenuItem();
        radio_stress_y = new javax.swing.JRadioButtonMenuItem();
        radio_stress_xy = new javax.swing.JRadioButtonMenuItem();
        radio_displacement_x = new javax.swing.JRadioButtonMenuItem();
        radio_displacement_y = new javax.swing.JRadioButtonMenuItem();
        visual_sep_2 = new javax.swing.JPopupMenu.Separator();
        item_move_up = new javax.swing.JMenuItem();
        item_move_down = new javax.swing.JMenuItem();
        item_move_right = new javax.swing.JMenuItem();
        item_move_left = new javax.swing.JMenuItem();
        visual_sep_3 = new javax.swing.JPopupMenu.Separator();
        item_zoom_in = new javax.swing.JMenuItem();
        item_move_out = new javax.swing.JMenuItem();
        visual_sep_4 = new javax.swing.JPopupMenu.Separator();
        item_increase_upper_limit = new javax.swing.JMenuItem();
        item_decrease_upper_limit = new javax.swing.JMenuItem();
        item_increase_lower_limit = new javax.swing.JMenuItem();
        item_decrease_lower_limit = new javax.swing.JMenuItem();
        menu_solver = new javax.swing.JMenu();
        item_solve = new javax.swing.JMenuItem();
        solver_sep_1 = new javax.swing.JPopupMenu.Separator();
        solver_menu_topology_optimization = new javax.swing.JMenu();
        item_to_simple = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jSplitPane1.setDividerLocation(360);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 773, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 723, Short.MAX_VALUE)
        );
        jSplitPane1.setRightComponent(jPanel1);
        jPanel2.setLayout(new java.awt.GridLayout(2, 1));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("selective generation"));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        selective_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Support", "Force"}));
        selective_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selective_comboActionPerformed(evt);
            }
        });
        selective_combo_panel.setLayout(new java.awt.CardLayout());
        jLabel9.setText("support in x direction");
        jLabel10.setText("support in y direction");
        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selective_supp_x, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selective_supp_y, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selective_supp_x, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(selective_supp_y)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        selective_combo_panel.add(jPanel11, "Support");
        jLabel11.setText("force in x direction");
        jLabel12.setText("force in y direction");
        selective_force_x.setText("0");
        selective_force_y.setText("0");
        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(selective_force_y, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                        .addComponent(selective_force_x))
                                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(selective_force_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selective_force_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        selective_combo_panel.add(jPanel12, "Force");
        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(selective_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selective_combo_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(selective_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(selective_combo_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
        );
        selective_gen.setText("generate");
        selective_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selective_genActionPerformed(evt);
            }
        });
        jLabel13.setText("X Position:");
        jLabel14.setText("Y Position:");
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(selective_gen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(selective_y_pos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selective_x_pos, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(selective_y_pos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(23, 23, 23))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(selective_x_pos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(selective_gen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
        );
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel2.add(jPanel3);
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("automatic generation"));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<= x <=");
        auto_max_x.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        auto_max_x.setText("2");
        auto_min_x.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        auto_min_x.setText("0");
        auto_min_y.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        auto_min_y.setText("0");
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<= y <=");
        auto_max_y.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        auto_max_y.setText("2");
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        auto_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Support", "Force"}));
        auto_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_comboActionPerformed(evt);
            }
        });
        auto_combo_panel.setLayout(new java.awt.CardLayout());
        jLabel5.setText("support in x direction");
        jLabel6.setText("support in y direction");
        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(auto_supp_x, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(auto_supp_y, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(auto_supp_x, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(auto_supp_y)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        auto_combo_panel.add(jPanel8, "Support");
        jLabel3.setText("force in x direction");
        jLabel4.setText("force in y direction");
        auto_force_x.setText("0");
        auto_force_y.setText("0");
        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(auto_force_y, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                        .addComponent(auto_force_x))
                                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(auto_force_x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(auto_force_y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        auto_combo_panel.add(jPanel9, "Force");
        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(auto_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(auto_combo_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(auto_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(auto_combo_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))
        );
        auto_gen.setText("generate");
        auto_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                auto_genActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(auto_min_y, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(auto_max_y, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(auto_min_x, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(auto_max_x, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(auto_gen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(auto_min_x, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                                        .addComponent(auto_max_x))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(auto_min_y, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(auto_max_y, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(auto_gen, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel2.add(jPanel4);
        jSplitPane1.setLeftComponent(jPanel2);
        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        menu_file.setText("File");
        item_save_mesh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        item_save_mesh.setText("save mesh");
        item_save_mesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_save_meshActionPerformed(evt);
            }
        });
        menu_file.add(item_save_mesh);
        item_open_mesh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        item_open_mesh.setText("open mesh");
        item_open_mesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_open_meshActionPerformed(evt);
            }
        });
        menu_file.add(item_open_mesh);
        menu_file.add(file_sep_1);
        menu_export.setText("export");
        item_export_png.setText("PNG");
        item_export_png.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_export_pngActionPerformed(evt);
            }
        });
        menu_export.add(item_export_png);
        menu_file.add(menu_export);
        jMenuBar1.add(menu_file);
        menu_mesh.setText("Mesh");
        item_new_mesh.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        item_new_mesh.setText("new mesh");
        item_new_mesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_new_meshActionPerformed(evt);
            }
        });
        menu_mesh.add(item_new_mesh);
        menu_mesh.add(mesh_sep_1);
        menu_material.setText("materials");
        item_new_material.setText("new material");
        item_new_material.addActionListener(new java.awt.event.ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                item_new_materialActionPerformed(e);
            }
        });
        menu_material.add(item_new_material);
        menu_material.add(mat_sep_1);
        add_material_items(menu_material);
        menu_mesh.add(menu_material);
        jMenuBar1.add(menu_mesh);
        menu_visual.setText("Visual");
        item_wireframe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        item_wireframe.setSelected(true);
        item_wireframe.setText("render wireframe");
        item_wireframe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_wireframeActionPerformed(evt);
            }
        });
        menu_visual.add(item_wireframe);
        item_grid.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        item_grid.setSelected(true);
        item_grid.setText("render grid");
        item_grid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_gridActionPerformed(evt);
            }
        });
        menu_visual.add(item_grid);
        item_loads.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        item_loads.setSelected(true);
        item_loads.setText("render loads");
        item_loads.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_loadsActionPerformed(evt);
            }
        });
        menu_visual.add(item_loads);
        item_mohr.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        item_mohr.setSelected(true);
        item_mohr.setText("render mohr");
        item_mohr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_mohrActionPerformed(evt);
            }
        });
        menu_visual.add(item_mohr);
        item_keys.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        item_keys.setSelected(true);
        item_keys.setText("render keybinds");
        item_keys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_keysActionPerformed(evt);
            }
        });
        menu_visual.add(item_keys);
        menu_visual.add(visual_sep_1);
        radio_nothing.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_nothing);
        radio_nothing.setSelected(true);
        radio_nothing.setText("render nothing");
        radio_nothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_nothingActionPerformed(evt);
            }
        });
        menu_visual.add(radio_nothing);
        radio_stress.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_stress);
        radio_stress.setText("render stress");
        radio_stress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_stressActionPerformed(evt);
            }
        });
        menu_visual.add(radio_stress);
        radio_displacement.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_displacement);
        radio_displacement.setText("render displacement");
        radio_displacement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_displacementActionPerformed(evt);
            }
        });
        menu_visual.add(radio_displacement);
        radio_young.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_young);
        radio_young.setText("render E-Module");
        radio_young.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_youngActionPerformed(evt);
            }
        });
        menu_visual.add(radio_young);
        radio_stress_x.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_stress_x);
        radio_stress_x.setText("render stress (x)");
        radio_stress_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_stress_xActionPerformed(evt);
            }
        });
        menu_visual.add(radio_stress_x);
        radio_stress_y.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_stress_y);
        radio_stress_y.setText("render stress (y)");
        radio_stress_y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_stress_yActionPerformed(evt);
            }
        });
        menu_visual.add(radio_stress_y);
        radio_stress_xy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_stress_xy);
        radio_stress_xy.setText("render stress (xy)");
        radio_stress_xy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_stress_xyActionPerformed(evt);
            }
        });
        menu_visual.add(radio_stress_xy);
        radio_displacement_x.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_displacement_x);
        radio_displacement_x.setText("render displacement (x)");
        radio_displacement_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_displacement_xActionPerformed(evt);
            }
        });
        menu_visual.add(radio_displacement_x);
        radio_displacement_y.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, java.awt.event.InputEvent.CTRL_MASK));
        buttonGroup1.add(radio_displacement_y);
        radio_displacement_y.setText("render displacement (y)");
        radio_displacement_y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_displacement_yActionPerformed(evt);
            }
        });
        menu_visual.add(radio_displacement_y);
        menu_visual.add(visual_sep_2);
        item_move_up.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_UP, java.awt.event.InputEvent.CTRL_MASK));
        item_move_up.setText("move up");
        item_move_up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_move_upActionPerformed(evt);
            }
        });
        menu_visual.add(item_move_up);
        item_move_down.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.CTRL_MASK));
        item_move_down.setText("move down");
        item_move_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_move_downActionPerformed(evt);
            }
        });
        menu_visual.add(item_move_down);
        item_move_right.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.CTRL_MASK));
        item_move_right.setText("move right");
        item_move_right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_move_rightActionPerformed(evt);
            }
        });
        menu_visual.add(item_move_right);
        item_move_left.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.CTRL_MASK));
        item_move_left.setText("move left");
        item_move_left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_move_leftActionPerformed(evt);
            }
        });
        menu_visual.add(item_move_left);
        menu_visual.add(visual_sep_3);
        item_zoom_in.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ADD, java.awt.event.InputEvent.CTRL_MASK));
        item_zoom_in.setText("zoom in");
        item_zoom_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_zoom_inActionPerformed(evt);
            }
        });
        menu_visual.add(item_zoom_in);
        item_move_out.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SUBTRACT, java.awt.event.InputEvent.CTRL_MASK));
        item_move_out.setText("zoom out");
        item_move_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_move_outActionPerformed(evt);
            }
        });
        menu_visual.add(item_move_out);
        menu_visual.add(visual_sep_4);
        item_increase_upper_limit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        item_increase_upper_limit.setText("increase upper limit");
        item_increase_upper_limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_increase_upper_limitActionPerformed(evt);
            }
        });
        menu_visual.add(item_increase_upper_limit);
        item_decrease_upper_limit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        item_decrease_upper_limit.setText("decrease upper limit");
        item_decrease_upper_limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_decrease_upper_limitActionPerformed(evt);
            }
        });
        menu_visual.add(item_decrease_upper_limit);
        item_increase_lower_limit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        item_increase_lower_limit.setText("increase lower limit");
        item_increase_lower_limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_increase_lower_limitActionPerformed(evt);
            }
        });
        menu_visual.add(item_increase_lower_limit);
        item_decrease_lower_limit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        item_decrease_lower_limit.setText("decrease lower limit");
        item_decrease_lower_limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_decrease_lower_limitActionPerformed(evt);
            }
        });
        menu_visual.add(item_decrease_lower_limit);
        jMenuBar1.add(menu_visual);
        menu_solver.setText("Solver");
        item_solve.setText("solve");
        item_solve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_solveActionPerformed(evt);
            }
        });
        menu_solver.add(item_solve);
        menu_solver.add(solver_sep_1);
        solver_menu_topology_optimization.setText("topology optimization");
        item_to_simple.setText("topology optimization");
        item_to_simple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_to_simpleActionPerformed(evt);
            }
        });
        solver_menu_topology_optimization.add(item_to_simple);
        menu_solver.add(solver_menu_topology_optimization);
        jMenuBar1.add(menu_solver);
        setJMenuBar(jMenuBar1);
        pack();
    }// </editor-fold>

    private void popup_missing_resources() {
        String[] direct = {"images", "material"};
        //boolean[] exists = new boolean[direct.length];
        String message = "";
        int missings = 0;
        String path = new File("").getAbsolutePath();
        for (int i = 0; i < direct.length; i++) {
            boolean ex = new File(path + "/resources/" + direct[i]).exists();
            if (!ex) {
                message += "/resources/" + direct[i] + " \n";
                missings++;
            }
        }
        if (missings == 0) return;
        JOptionPane.showMessageDialog(this,
                message,
                "Missing resources",
                JOptionPane.WARNING_MESSAGE);
    }

    private void item_new_materialActionPerformed(ActionEvent e) {

    }

    private void add_material_file_to_node(File file, JMenu jMenu) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim().replaceAll(" {2}", " ");
                String[] split = line.split(" ");
                JMenuItem item = new JMenuItem();
                item.setText(split[0]);
                item.addActionListener(e -> set_material_for_all(Double.parseDouble(split[1]) * 1E9, Double.parseDouble(split[2])));
                jMenu.add(item);
            }
        } catch (Exception ignored) {
        }
    }


    private void add_material_items(JMenu menu_material) {
        String path = new File("").getAbsolutePath();
        File[] files;
        try {
            files = new File(path + "/resources/material").listFiles();
        } catch (Exception ignored) {
            return;
        }
        if (files == null) return;
        for (File file : files) {
            String name = file.getName();
            if (!name.equals("default")) {
                System.out.println("Loading materials: " + name);
                JMenu menu = new JMenu();
                menu.setText(name);
                add_material_file_to_node(file, menu);
                menu_material.add(menu);
            }
        }
        for (File file : files) {
            String name = file.getName();
            if (name.equals("default")) {
                System.out.println("Loading materials: " + name);
                add_material_file_to_node(file, menu_material);
            }
        }
    }

    private void set_material_for_all(double E, double v) {
        System.out.println(E + "  " + v);
        if (getMesh() == null) return;
        for (FiniteElement2D elements : getMesh().getFaces()) {
            elements.setMaterial(new Material(E, v));
        }
        this.redraw();
    }

    private void renderWireframe(boolean val) {
        jPanel1.renderWireframe = val;
        item_wireframe.setSelected(val);
        redraw();
    }

    private void renderGrid(boolean val) {
        jPanel1.renderGrid = val;
        item_grid.setSelected(val);
        redraw();
    }

    private void renderLoads(boolean val) {
        jPanel1.renderBCs = val;
        item_loads.setSelected(val);
        redraw();
    }

    private void renderMohr(boolean val) {
        jPanel1.renderMohr = val;
        item_mohr.setSelected(val);
        redraw();
    }

    private void renderKeys(boolean val) {
        jPanel1.renderKeys = val;
        item_keys.setSelected(val);
        redraw();
    }

    private void item_wireframeActionPerformed(java.awt.event.ActionEvent evt) {
        renderWireframe(item_wireframe.isSelected());
    }

    private void item_gridActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderGrid = item_grid.isSelected();
        redraw();
    }

    private void item_loadsActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderBCs = item_loads.isSelected();
        redraw();
    }

    private void item_mohrActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMohr = item_mohr.isSelected();
        redraw();
    }

    private void item_keysActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderKeys = item_keys.isSelected();
        redraw();
    }


    private void radio_nothingActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = -1;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_nothing.setSelected(true);
        redraw();
    }

    private void radio_stressActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 0;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_stress.setSelected(true);
        redraw();
    }

    private void radio_displacementActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 2;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_displacement.setSelected(true);
        redraw();
    }

    private void radio_youngActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 1;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_young.setSelected(true);
        redraw();
    }

    private void radio_stress_xActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 3;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_stress_x.setSelected(true);
        redraw();
    }

    private void radio_stress_yActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 4;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_stress_y.setSelected(true);
        redraw();
    }

    private void radio_stress_xyActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 5;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_stress_xy.setSelected(true);
        redraw();
    }

    private void radio_displacement_xActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 6;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_displacement_x.setSelected(true);
        redraw();
    }

    private void radio_displacement_yActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.renderMode = 7;
        jPanel1.lower_scaler = 1;
        jPanel1.upper_scalar = 1;
        radio_displacement_x.setSelected(true);
        redraw();
    }

    private void item_move_upActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.move(0, 0.1);
        redraw();
    }

    private void item_move_downActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.move(0, -0.1);
        redraw();
    }

    private void item_move_rightActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.move(0.1, 0);
        redraw();
    }

    private void item_move_leftActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.move(-0.1, 0);
        redraw();
    }

    private void item_zoom_inActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.zoom(0.8);
        redraw();
    }

    private void item_move_outActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.zoom(1.25);
        redraw();
    }

    private void item_increase_upper_limitActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.upper_scalar *= 1 / 0.6;
        redraw();
    }

    private void item_decrease_upper_limitActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.upper_scalar *= 0.6;
        redraw();
    }

    private void item_increase_lower_limitActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.lower_scaler *= 1 / 0.6;
        redraw();
    }

    private void item_decrease_lower_limitActionPerformed(java.awt.event.ActionEvent evt) {
        jPanel1.lower_scaler *= 0.6;
        redraw();
    }

    public void redraw() {
        this.invalidate();
        this.revalidate();
        this.repaint();
    }

    private void item_open_meshActionPerformed(java.awt.event.ActionEvent evt) {
        fileChooser.showOpenDialog(this);
        try {
            File file = fileChooser.getSelectedFile();
            Mesh mesh = new Mesh();
            if (file != null)
                Loader.load(file.getAbsolutePath(), mesh);
            jPanel1.setMesh(mesh);
            this.radio_nothingActionPerformed(null);
            this.renderWireframe(true);
            this.redraw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void item_solveActionPerformed(java.awt.event.ActionEvent evt) {
        Mesh mesh = jPanel1.getMesh();
        if (mesh != null) {
            console.setVisible(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mesh.solve();
                    redraw();
                }
            }).start();
        }
        this.radio_stressActionPerformed(null);
    }

    private void item_new_meshActionPerformed(java.awt.event.ActionEvent evt) {
        meshGen.setVisible(true);
        if (meshGen.getMesh() == null) return;
        this.jPanel1.setMesh(meshGen.getMesh());
        this.redraw();
    }

    private void item_save_meshActionPerformed(java.awt.event.ActionEvent evt) {
        fileChooser.showOpenDialog(this);
        try {
            File file = fileChooser.getSelectedFile();
            if (file != null)
                Loader.write(file.getAbsolutePath(), jPanel1.getMesh());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void item_to_simpleActionPerformed(java.awt.event.ActionEvent evt) {
        if (getMesh() == null) return;
        console.setVisible(true);
        toSimple.setMesh(getMesh());
        toSimple.setVisible(true);

        radio_youngActionPerformed(null);
        redraw();
    }

    private void auto_comboActionPerformed(java.awt.event.ActionEvent evt) {
        String name = auto_combo.getSelectedItem().toString();
        ((CardLayout) this.auto_combo_panel.getLayout()).show(this.auto_combo_panel, name);
    }

    private void selective_comboActionPerformed(java.awt.event.ActionEvent evt) {
        String name = selective_combo.getSelectedItem().toString();
        ((CardLayout) this.selective_combo_panel.getLayout()).show(this.selective_combo_panel, name);
    }

    private void selective_genActionPerformed(java.awt.event.ActionEvent evt) {
        if (jPanel1.highlightedNode == null) return;
        Node f = jPanel1.highlightedNode;
        if (this.selective_combo.getSelectedItem().toString().equals("Support")) {
            f.setSupport(new Support(selective_supp_x.isSelected(), selective_supp_y.isSelected()));
        } else if (this.selective_combo.getSelectedItem().toString().equals("Force")) {
            f.setForce(new Force(
                    Double.parseDouble(selective_force_x.getText()),
                    Double.parseDouble(selective_force_y.getText())
            ));
        }
        jPanel1.repaint();
    }

    private void auto_genActionPerformed(java.awt.event.ActionEvent evt) {
        double min_x = Double.parseDouble(auto_min_x.getText());
        double min_y = Double.parseDouble(auto_min_y.getText());
        double max_x = Double.parseDouble(auto_max_x.getText());
        double max_y = Double.parseDouble(auto_max_y.getText());
        for (Node f : jPanel1.getMesh().getVertices()) {
            double x = f.getPosition2D().getX();
            double y = f.getPosition2D().getY();
            if (x >= min_x && y >= min_y && x <= max_x && y <= max_y) {
                if (this.auto_combo.getSelectedItem().toString().equals("Support")) {
                    f.setSupport(new Support(auto_supp_x.isSelected(), auto_supp_y.isSelected()));
                } else if (this.auto_combo.getSelectedItem().toString().equals("Force")) {
                    f.setForce(new Force(
                            Double.parseDouble(auto_force_x.getText()),
                            Double.parseDouble(auto_force_y.getText())
                    ));
                }
            }
        }
        jPanel1.repaint();
    }

    private void item_export_pngActionPerformed(java.awt.event.ActionEvent evt) {
        renderToPng.setVisible(true);
    }


    public FEM_Panel getRenderPanel() {
        return jPanel1;
    }


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MeshEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeshEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeshEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeshEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MeshEditor meshEditor = new MeshEditor(null);
                meshEditor.jPanel1.setMesh(Generator.rectangle_hole_mesh_connected(1,0.2,10,10));
            }
        });
    }
    // Variables declaration - do not modify

    private MeshGenerator meshGen;
    private RunSettings toSimple;
    private FEM_Panel jPanel1;
    private Console console;
    private RenderToPng renderToPng;

    // Variables declaration - do not modify
    private javax.swing.JMenuItem[] material_menu;


    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> auto_combo;
    private javax.swing.JPanel auto_combo_panel;
    private javax.swing.JTextField auto_force_x;
    private javax.swing.JTextField auto_force_y;
    private javax.swing.JButton auto_gen;
    private javax.swing.JTextField auto_max_x;
    private javax.swing.JTextField auto_max_y;
    private javax.swing.JTextField auto_min_x;
    private javax.swing.JTextField auto_min_y;
    private javax.swing.JRadioButton auto_supp_x;
    private javax.swing.JRadioButton auto_supp_y;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenuItem item_decrease_lower_limit;
    private javax.swing.JMenuItem item_decrease_upper_limit;
    private javax.swing.JCheckBoxMenuItem item_grid;
    private javax.swing.JMenuItem item_increase_lower_limit;
    private javax.swing.JMenuItem item_increase_upper_limit;
    private javax.swing.JCheckBoxMenuItem item_keys;
    private javax.swing.JCheckBoxMenuItem item_loads;
    private javax.swing.JCheckBoxMenuItem item_mohr;
    private javax.swing.JMenuItem item_move_down;
    private javax.swing.JMenuItem item_move_left;
    private javax.swing.JMenuItem item_move_out;
    private javax.swing.JMenuItem item_move_right;
    private javax.swing.JMenuItem item_move_up;
    private javax.swing.JMenuItem item_new_material;
    private javax.swing.JMenuItem item_new_mesh;
    private javax.swing.JMenuItem item_open_mesh;
    private javax.swing.JMenuItem item_save_mesh;
    private javax.swing.JMenuItem item_solve;
    private javax.swing.JMenuItem item_to_simple;
    private javax.swing.JCheckBoxMenuItem item_wireframe;
    private javax.swing.JMenuItem item_zoom_in;
    private javax.swing.JMenuItem item_export_png;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPopupMenu.Separator mat_sep_1;
    private javax.swing.JPopupMenu.Separator file_sep_1;
    private javax.swing.JMenu menu_export;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenu menu_material;
    private javax.swing.JMenu menu_mesh;
    private javax.swing.JMenu menu_solver;
    private javax.swing.JMenu menu_visual;
    private javax.swing.JPopupMenu.Separator mesh_sep_1;
    private javax.swing.JRadioButtonMenuItem radio_displacement;
    private javax.swing.JRadioButtonMenuItem radio_displacement_x;
    private javax.swing.JRadioButtonMenuItem radio_displacement_y;
    private javax.swing.JRadioButtonMenuItem radio_nothing;
    private javax.swing.JRadioButtonMenuItem radio_stress;
    private javax.swing.JRadioButtonMenuItem radio_stress_x;
    private javax.swing.JRadioButtonMenuItem radio_stress_xy;
    private javax.swing.JRadioButtonMenuItem radio_stress_y;
    private javax.swing.JRadioButtonMenuItem radio_young;
    private javax.swing.JComboBox<String> selective_combo;
    private javax.swing.JPanel selective_combo_panel;
    private javax.swing.JTextField selective_force_x;
    private javax.swing.JTextField selective_force_y;
    private javax.swing.JButton selective_gen;
    private javax.swing.JRadioButton selective_supp_x;
    private javax.swing.JRadioButton selective_supp_y;
    private javax.swing.JLabel selective_x_pos;
    private javax.swing.JLabel selective_y_pos;
    private javax.swing.JMenu solver_menu_topology_optimization;
    private javax.swing.JPopupMenu.Separator solver_sep_1;
    private javax.swing.JPopupMenu.Separator visual_sep_1;
    private javax.swing.JPopupMenu.Separator visual_sep_2;
    private javax.swing.JPopupMenu.Separator visual_sep_3;
    private javax.swing.JPopupMenu.Separator visual_sep_4;


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Node closest = jPanel1.process_mouse_click(e.getX(), e.getY());
        selective_x_pos.setText(
                "" + BigDecimal.valueOf(closest.getPosition2D().getX())
                        .setScale(4, RoundingMode.HALF_UP).doubleValue());
        selective_y_pos.setText(
                "" + BigDecimal.valueOf(closest.getPosition2D().getY())
                        .setScale(4, RoundingMode.HALF_UP).doubleValue());
        jPanel1.highlightedNode = closest;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    // End of variables declaration                   
}
