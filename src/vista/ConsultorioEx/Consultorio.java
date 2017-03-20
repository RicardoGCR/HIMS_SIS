/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.ConsultorioEx.ConsultorioExConsultorio;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class Consultorio extends javax.swing.JFrame {

    public Consultorio() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        ConsultorioExConsultorio consultorio2 = new ConsultorioExConsultorio();
        consultorio2.inicializarTablaConsultorios(tbConsultorios);
        consultorio2.listarConsultorios("", tbConsultorios);
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
    });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiar(){
        lblMant.setText("I");
        txtID.setText("");
        txtNumero.setText("");
        txtDescripcion.setText("");
    }
    
    public void habilitarCampos(boolean opcion){
        txtNumero.setEnabled(opcion);
        txtDescripcion.setEnabled(opcion);
    }
    
    public void guardarConsultorio(){
        try {
            ConsultorioExConsultorio consultorio1 = new ConsultorioExConsultorio();
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            if(txtNumero.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese un número de consultorio");
            } else {
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                String cod_usu = adEmerCab5.codUsuario(lblusu.getText());
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    consultorio1.setUsuario(cod_usu);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        JOptionPane.showMessageDialog(this, "Consultorio guardado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
                }else{
                        JOptionPane.showMessageDialog(this, "No se realizó ninguú registro");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: guardarConsultorio" + e.getMessage());
        }
    }
    
    public void modificarConsultorio(){
        try {
            ConsultorioExConsultorio consultorio1 = new ConsultorioExConsultorio();
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
            if(txtNumero.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese un número de consultorio");
            } else {
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(modificar == 0){
                    consultorio1.setId(Integer.parseInt(txtID.getText()));
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        JOptionPane.showMessageDialog(this, "Consultorio modificado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
                }else{
                        JOptionPane.showMessageDialog(this, "No se realizó ninguú registro");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: modificarConsultorio" + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbConsultorios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLabel4 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            txtNumero = new javax.swing.JTextField();
            jPanel14 = new javax.swing.JPanel();
            jLabel29 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            jLabel45 = new javax.swing.JLabel();
            jLabel46 = new javax.swing.JLabel();
            jLabel48 = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            txtDescripcion = new javax.swing.JEditorPane();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(0, 153, 102));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Consultorios");

            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
            btnNuevo.setMnemonic('N');
            btnNuevo.setContentAreaFilled(false);
            btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnNuevo.setIconTextGap(30);
            btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNuevoActionPerformed(evt);
                }
            });

            btnModificar.setForeground(new java.awt.Color(240, 240, 240));
            btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
            btnModificar.setMnemonic('N');
            btnModificar.setContentAreaFilled(false);
            btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnModificar.setEnabled(false);
            btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnModificar.setIconTextGap(30);
            btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnModificarActionPerformed(evt);
                }
            });

            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
            btnGuardar.setMnemonic('N');
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar.setEnabled(false);
            btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnGuardar.setIconTextGap(30);
            btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
                }
            });

            btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
            btnEliminar.setMnemonic('N');
            btnEliminar.setContentAreaFilled(false);
            btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnEliminar.setEnabled(false);
            btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnEliminar.setIconTextGap(30);
            btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnEliminarActionPerformed(evt);
                }
            });

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblusu.setText("Silvana");

            lblMant.setText("Mantenimiento");

            txtID.setEnabled(false);
            txtID.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtIDCaretUpdate(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(152, 152, 152)
                            .addComponent(lblMant)
                            .addGap(18, 18, 18)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(lblusu))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnEliminar))
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMant)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(552, 552, 552))
            );

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(102, 102, 102));
            jLabel2.setText("Nuevo Registro_________________________________________");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(102, 102, 102));
            jLabel3.setText("Consultorios Registrados");

            jScrollPane3.setBorder(null);

            tbConsultorios.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbConsultorios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbConsultorios.setGridColor(new java.awt.Color(255, 255, 255));
            tbConsultorios.setRowHeight(25);
            tbConsultorios.setSelectionBackground(new java.awt.Color(0, 153, 102));
            tbConsultorios.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbConsultoriosMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbConsultoriosMousePressed(evt);
                }
            });
            tbConsultorios.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbConsultoriosKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbConsultoriosKeyReleased(evt);
                }
            });
            jScrollPane3.setViewportView(tbConsultorios);

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(102, 102, 102));
            jLabel4.setText("Numero");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(102, 102, 102));
            jLabel6.setText("Descripcion");

            txtNumero.setEnabled(false);

            jLabel29.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
            jLabel29.setText("Modificar (Alt + M)");

            jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
            jLabel44.setText("Nuevo (Alt + N)");

            jLabel45.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Save-16.png"))); // NOI18N
            jLabel45.setText("Guardar (Alt + G)");

            jLabel46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar-16.png"))); // NOI18N
            jLabel46.setText("Eliminar (Alt + E)");

            jLabel48.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
            jLabel48.setText("Salir (ESC)");

            javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
            jPanel14.setLayout(jPanel14Layout);
            jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jLabel44)
                    .addGap(6, 6, 6)
                    .addComponent(jLabel45)
                    .addGap(6, 6, 6)
                    .addComponent(jLabel29)
                    .addGap(6, 6, 6)
                    .addComponent(jLabel46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48)
                    .addContainerGap())
            );
            jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(jLabel29)
                        .addComponent(jLabel45)
                        .addComponent(jLabel46)
                        .addComponent(jLabel48)))
            );

            jScrollPane1.setViewportView(txtDescripcion);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(38, 38, 38)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6)))
                            .addGap(0, 22, Short.MAX_VALUE))
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
        } catch (Exception e) {
            System.out.println("Error: btnNuevo" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
        habilitarCampos(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            guardarConsultorio();
        }else{
            modificarConsultorio();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbConsultoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbConsultoriosMouseClicked
        if(evt.getClickCount()==1){
            int fila = tbConsultorios.getSelectedRow();
            txtID.setText(String.valueOf(tbConsultorios.getValueAt(fila, 0)));
            txtNumero.setText(String.valueOf(tbConsultorios.getValueAt(fila, 1)));
            txtDescripcion.setText(String.valueOf(tbConsultorios.getValueAt(fila, 2)));
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_tbConsultoriosMouseClicked

    private void tbConsultoriosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbConsultoriosMousePressed

    }//GEN-LAST:event_tbConsultoriosMousePressed

    private void tbConsultoriosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbConsultoriosKeyPressed
        
    }//GEN-LAST:event_tbConsultoriosKeyPressed

    private void tbConsultoriosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbConsultoriosKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbConsultorios.getSelectedRow();
            txtID.setText(String.valueOf(tbConsultorios.getValueAt(fila, 0)));
            txtNumero.setText(String.valueOf(tbConsultorios.getValueAt(fila, 1)));
            txtDescripcion.setText(String.valueOf(tbConsultorios.getValueAt(fila, 2)));
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_tbConsultoriosKeyReleased

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate
        
    }//GEN-LAST:event_txtIDCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblusu;
    private javax.swing.JTable tbConsultorios;
    private javax.swing.JEditorPane txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
