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
import javax.swing.JFrame;
import modelos.ConsultorioEx.ConsultorioExConsultorio;
import modelos.ConsultorioEx.ConsultorioExTurno;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author PC02
 */
public class Turno extends javax.swing.JFrame {

    ConsultorioExTurno turno1 = new ConsultorioExTurno();
    public Turno() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        turno1.inicializarTablaTurnos(tbTurno);
        turno1.listarTurnos(tbTurno);
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
        txtHorai.setText("");
        txtHoraF.setText("");
        txtHorai2.setText("");
        txtHoraF2.setText("");
        cbxTurno.setSelectedIndex(0);
    }
    
    public void habilitarCampos(boolean opcion){
        txtHorai.setEnabled(opcion);
        txtHoraF.setEnabled(opcion);
        txtHorai2.setEnabled(opcion);
        txtHoraF2.setEnabled(opcion);
        cbxTurno.setEnabled(opcion);
    }
    
    public void guardarTurno(){
        try {
            ConsultorioExTurno consultorio1 = new ConsultorioExTurno();
            consultorio1.setNombre(cbxTurno.getSelectedItem().toString());
            consultorio1.setHorai(txtHorai.getText()+":"+txtHorai2.getText());
            consultorio1.setHoraf(txtHoraF.getText()+":"+txtHoraF.getText());
            if(consultorio1.mantenimientoConsultorioExTurno(lblMant.getText())==true){
//                String id = consultorio1.consultorioID();
//                System.out.println("ID: " + id);
                lblMensaje.setText("Turno guardado con éxito");
                consultorio1.listarTurnos(tbTurno);
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
    
    public void modificarTurno(){
        try {
            ConsultorioExTurno consultorio1 = new ConsultorioExTurno();
            int id = Integer.parseInt(txtID.getText());
            consultorio1.setId(id);
            consultorio1.setNombre(cbxTurno.getSelectedItem().toString());
            consultorio1.setHorai(txtHorai.getText());
            consultorio1.setHoraf(txtHoraF.getText());
            if(consultorio1.mantenimientoConsultorioExTurno(lblMant.getText())==true){
                lblMensaje.setText("Turno modificado con éxito");
                consultorio1.inicializarTablaTurnos(tbTurno);
                btnGuardar.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnModificar.setEnabled(false);
                limpiar();
                habilitarCampos(false);
                lblMant.setText("I");
                txtID.setText("");
            }
        } catch (Exception e) {
            System.out.println("Error: modificarTurno" + e.getMessage());
        }
    }
    
    public void eliminarTurno(){
        
    }
    
    public void enviarDatosMod(){
        int fila = tbTurno.getSelectedRow();
            String horai =  String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 2)).charAt(0)) + 
                            String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 2)).charAt(1));
            String horai2 = String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 2)).charAt(3)) +
                            String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 2)).charAt(4));
            String horaf =  String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 3)).charAt(0)) + 
                            String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 3)).charAt(1));
            String horaf2 = String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 3)).charAt(3)) + 
                            String.valueOf(String.valueOf(tbTurno.getValueAt(fila, 3)).charAt(4));
            txtID.setText(String.valueOf(tbTurno.getValueAt(fila, 0)));
            cbxTurno.setSelectedItem(String.valueOf(tbTurno.getValueAt(fila, 1)));
            txtHorai.setText(horai);
            txtHorai2.setText(horai2);
            txtHoraF.setText(horaf);
            txtHoraF2.setText(horaf2);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnGuardar.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTurno = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtHorai = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            cbxTurno = new javax.swing.JComboBox();
            jLabel5 = new javax.swing.JLabel();
            txtHoraF = new javax.swing.JTextField();
            txtHoraF2 = new javax.swing.JTextField();
            txtHorai2 = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();

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
                            .addGap(0, 160, Short.MAX_VALUE))
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
            jLabel4.setText("Hora de Inicio");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(102, 102, 102));
            jLabel3.setText("Turnos Registrados");

            tbTurno.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbTurno.setGridColor(new java.awt.Color(255, 255, 255));
            tbTurno.setRowHeight(25);
            tbTurno.setSelectionBackground(new java.awt.Color(0, 153, 102));
            tbTurno.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbTurnoMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbTurnoMousePressed(evt);
                }
            });
            tbTurno.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbTurnoKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbTurnoKeyReleased(evt);
                }
            });
            jScrollPane3.setViewportView(tbTurno);

            txtHorai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtHorai.setEnabled(false);
            txtHorai.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtHoraiCaretUpdate(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(102, 102, 102));
            jLabel2.setText("Nuevo Registro_________________________________________");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(102, 102, 102));
            jLabel6.setText("Turno");

            cbxTurno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            cbxTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Mañana", "Tarde" }));
            cbxTurno.setEnabled(false);

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(102, 102, 102));
            jLabel5.setText("Hora de Término");

            txtHoraF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtHoraF.setEnabled(false);
            txtHoraF.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtHoraFCaretUpdate(evt);
                }
            });

            txtHoraF2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtHoraF2.setEnabled(false);
            txtHoraF2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtHoraF2CaretUpdate(evt);
                }
            });

            txtHorai2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtHorai2.setEnabled(false);
            txtHorai2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtHorai2CaretUpdate(evt);
                }
            });

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel7.setText(":");

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel8.setText(":");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtHorai, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                        .addComponent(txtHoraF))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHoraF2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtHorai2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel2)))
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
                            .addGap(21, 21, 21)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(cbxTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtHorai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtHorai2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtHoraF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtHoraF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(11, 11, 11))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            if(cbxTurno.getSelectedIndex()==0 || txtHorai.getText().equals("") || txtHoraF.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Complete todos los campos");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea GUARDAR los datos?");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
            }
        }else
        if(lblMant.getText().equals("U")){
            if(cbxTurno.getSelectedIndex()==0 || txtHorai.getText().equals("") || txtHoraF.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Complete todos los campos");
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

    private void txtIDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDCaretUpdate

    }//GEN-LAST:event_txtIDCaretUpdate

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(lblMant.getText().equals("I")){
            guardarTurno();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("U")){
            modificarTurno();
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        } else
        if(lblMant.getText().equals("E")){
            eliminarTurno();
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

    private void tbTurnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTurnoMouseClicked
        if(evt.getClickCount()==1){
            enviarDatosMod();
        }
    }//GEN-LAST:event_tbTurnoMouseClicked

    private void tbTurnoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTurnoMousePressed

    }//GEN-LAST:event_tbTurnoMousePressed

    private void tbTurnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTurnoKeyPressed

    }//GEN-LAST:event_tbTurnoKeyPressed

    private void tbTurnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTurnoKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            enviarDatosMod();
        }
    }//GEN-LAST:event_tbTurnoKeyReleased

    private void txtHoraiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHoraiCaretUpdate

    }//GEN-LAST:event_txtHoraiCaretUpdate

    private void txtHoraFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHoraFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraFCaretUpdate

    private void txtHoraF2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHoraF2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraF2CaretUpdate

    private void txtHorai2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHorai2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHorai2CaretUpdate

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
            java.util.logging.Logger.getLogger(Turno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Turno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Turno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Turno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Turno().setVisible(true);
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
    private javax.swing.JComboBox cbxTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbTurno;
    private javax.swing.JTextField txtHoraF;
    private javax.swing.JTextField txtHoraF2;
    private javax.swing.JTextField txtHorai;
    private javax.swing.JTextField txtHorai2;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
