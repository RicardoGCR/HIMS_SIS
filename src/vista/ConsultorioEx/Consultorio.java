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
        pnlMensaje.setVisible(false);
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
            
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                String cod_usu = adEmerCab5.codUsuario(lblusu.getText());
//                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                if(guardar == 0){
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    consultorio1.setUsuario(cod_usu);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        lblMensaje.setText("Consultorio guardado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
//                }else{
//                        JOptionPane.showMessageDialog(this, "No se realizó ninguú registro");
//                }
        } catch (Exception e) {
            System.out.println("Error: guardarConsultorio" + e.getMessage());
        }
    }
    
    public void modificarConsultorio(){
        try {
            ConsultorioExConsultorio consultorio1 = new ConsultorioExConsultorio();
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                String numero = txtNumero.getText();
                String descripcion = txtDescripcion.getText();
                    consultorio1.setId(Integer.parseInt(txtID.getText()));
                    consultorio1.setNumero(numero);
                    consultorio1.setDescripcion(descripcion);
                    if(consultorio1.mantenimientoConsultorioExConsultorio(lblMant.getText())==true){
                        String id = consultorio1.consultorioID();
                        System.out.println("ID: " + id);
                        lblMensaje.setText("Consultorio modificado con éxito");
                        consultorio1.listarConsultorios("", tbConsultorios);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarCampos(false);
                        lblMant.setText("I");
                        txtID.setText("");
                    }
        } catch (Exception e) {
            System.out.println("Error: modificarConsultorio" + e.getMessage());
        }
    }
    
    public void eliminarConsultorio(){
        ConsultorioExConsultorio consultorio3 = new ConsultorioExConsultorio();
            consultorio3.setId(Integer.parseInt(txtID.getText()));
            if(consultorio3.mantenimientoConsultorioExConsultorio("E")){
                lblMensaje.setText("Consultorio eliminado");
                consultorio3.listarConsultorios("", tbConsultorios);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                limpiar();
                habilitarCampos(false);
            } else{
                lblMensaje.setText("Ocurrió un error al eliminar");
                consultorio3.listarConsultorios("", tbConsultorios);
                btnEliminar.setEnabled(false);
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                limpiar();
                habilitarCampos(false);
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
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbConsultorios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtNumero = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();

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

            pnlMensaje.setBackground(new java.awt.Color(255, 153, 51));

            lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
            lblMensaje.setText("Desea Actualizar el Registro ?");

            btnSi.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
            btnSi.setForeground(new java.awt.Color(240, 240, 240));
            btnSi.setText("Si");
            btnSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
            btnSi.setContentAreaFilled(false);
            btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnSi.setIconTextGap(30);
            btnSi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnSiActionPerformed(evt);
                }
            });

            btnNo.setForeground(new java.awt.Color(240, 240, 240));
            btnNo.setText("No");
            btnNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
            btnNo.setContentAreaFilled(false);
            btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnNo.setIconTextGap(30);
            btnNo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
            pnlMensaje.setLayout(pnlMensajeLayout);
            pnlMensajeLayout.setHorizontalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMensajeLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(lblMensaje)
                    .addGap(54, 54, 54)
                    .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pnlMensajeLayout.setVerticalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMensajeLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMensaje)
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(102, 102, 102));
            jLabel4.setText("Numero");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(102, 102, 102));
            jLabel3.setText("Consultorios Registrados");

            jScrollPane1.setViewportView(txtDescripcion);

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

            txtNumero.setEnabled(false);
            txtNumero.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtNumeroCaretUpdate(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(102, 102, 102));
            jLabel2.setText("Nuevo Registro_________________________________________");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(102, 102, 102));
            jLabel6.setText("Descripcion");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(210, 210, 210)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(38, 38, 38)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, 0))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addComponent(jLabel6)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            pnlMensaje.setVisible(false);
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
            if(txtNumero.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un número de consultorio");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea GUARDAR los datos?");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
            }
        }else{
            if(txtNumero.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese un número de consultorio");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea MODIFICAR los datos?");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        pnlMensaje.setVisible(true);
        lblMensaje.setText("Desea ELIMINAR el registro?");
        btnSi.setVisible(true);
        btnNo.setVisible(true);
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

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(lblMant.getText().equals("I")){
            guardarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("U")){
            modificarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("E")){
            eliminarConsultorio();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        if(lblMant.getText().equals("I")){
            lblMensaje.setText("No se realizó ningún registro");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("U")){
            lblMensaje.setText("No se realizó ninguna modificación");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("E")){
            lblMensaje.setText("No se eliminó ningún registro");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }    
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtNumeroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNumeroCaretUpdate

    }//GEN-LAST:event_txtNumeroCaretUpdate

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
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbConsultorios;
    private javax.swing.JEditorPane txtDescripcion;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
