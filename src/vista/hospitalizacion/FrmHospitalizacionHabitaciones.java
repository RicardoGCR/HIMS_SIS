/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionHabitaciones;
import modelos.hospitalizacion.HospitalizacionPisos;
import servicios.Conexion;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDepartamento;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionHabitaciones extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    public FrmHospitalizacionHabitaciones() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        conexion = c.conectar();
        cbxPiso.setBackground(Color.white);
        cbxServicio.setBackground(Color.white);
        cbxPiso.setModel(pisos());
        cbxServicio.setModel(servicios());
        seleccionRegistro();
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
    
    public DefaultComboBoxModel servicios(){
           DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC HOSPITALIZACION_MOSTRAR_SERVICIO"); 
                  listmodel.addElement("Seleccionar...");
                while( r.next() ){
                    listmodel.addElement( r.getString( "SE_DESC" ) );                
                 }
                r.close();
            } catch (SQLException ex) {            
                System.err.println( "Error: servicios: EXEC HOSPITALIZACION_MOSTRAR_SERVICIO :" + ex.getMessage() );
            }        
        return listmodel;
    }
    
    public DefaultComboBoxModel pisos(){
           DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC HOSPITALIZACION_PISOS_LISTAR_PARAHAB"); 
                  listmodel.addElement("Seleccionar...");
                while( r.next() ){
                    listmodel.addElement( r.getString( "PISO_NUMERO" ));                
                 }
                r.close();
            } catch (SQLException ex) {            
                System.err.println( "Error: servicios: EXEC HOSPITALIZACION_PISOS_LISTAR :" + ex.getMessage() );
            }        
        return listmodel;
    }
    
    public void limpiarCajas(){
        cbxPiso.setSelectedIndex(0);
        txtNombreHab.setText("");
        cbxServicio.setSelectedIndex(0);
    }
    
    public void habilitarCajas(boolean opcion){
        cbxPiso.setEnabled(opcion);
        txtNombreHab.setEnabled(opcion);
        cbxServicio.setEnabled(opcion);
    }
    
    public void btnBuscar(){
        tbHospHab.setSelectedIndex(1);
        btnListado.setForeground(Color.red);
        btnRegistro.setForeground(Color.black);
        HospitalizacionHabitaciones hospP = new HospitalizacionHabitaciones();
        hospP.setHab_nom(txtBuscar.getText());
        hospP.hospitalizacionHabListar(tbHospListar);
        tbHospListar.getSelectionModel().setSelectionInterval(0,0);
        tbHospListar.requestFocus();
        txtBuscar.requestFocus();
    }
    
    public void seleccionRegistro(){
        tbHospHab.setSelectedIndex(0);
        btnRegistro.setForeground(Color.red);
        btnListado.setForeground(Color.black);
    }
    
    public void modificarHosHab(){
        int fila = tbHospListar.getSelectedRow();
        lblID.setText(String.valueOf(tbHospListar.getValueAt(fila, 0)));
        cbxPiso.setSelectedItem(String.valueOf(tbHospListar.getValueAt(fila, 1)));
        txtNombreHab.setText(String.valueOf(tbHospListar.getValueAt(fila, 3)));
        tbHospHab.setSelectedIndex(0);
        seleccionRegistro();
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnGuardar.setEnabled(false);
        habilitarCajas(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRegistro = new javax.swing.JButton();
        btnListado = new javax.swing.JButton();
        tbHospHab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxPiso = new javax.swing.JComboBox();
        txtNombreHab = new javax.swing.JTextField();
        lblMant = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHospListar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 400));
        getContentPane().setLayout(null);

        jPanel8.setBackground(new java.awt.Color(255, 119, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Hospitalización - Habitación");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(0, 11, 275, 30);

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(350, 20, 85, 20);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel8.add(jLabel19);
        jLabel19.setBounds(310, 20, 32, 24);

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setToolTipText("Nuevo (Alt + N)");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setFocusable(false);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel8.add(btnNuevo);
        btnNuevo.setBounds(10, 60, 24, 49);

        btnModificar.setBackground(new java.awt.Color(204, 204, 204));
        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setToolTipText("Modificar (Alt + M)");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setFocusable(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel8.add(btnModificar);
        btnModificar.setBounds(74, 60, 28, 49);

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setToolTipText("Guardar (Alt + G)");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel8.add(btnGuardar);
        btnGuardar.setBounds(40, 60, 28, 49);

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setToolTipText("Eliminar (Alt + E)");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setEnabled(false);
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel8.add(btnEliminar);
        btnEliminar.setBounds(108, 60, 28, 49);

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar (Alt + F3)");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel8.add(btnBuscar);
        btnBuscar.setBounds(140, 70, 38, 32);

        getContentPane().add(jPanel8);
        jPanel8.setBounds(0, 0, 450, 120);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnRegistro.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnRegistro.setText("Registro");
        btnRegistro.setContentAreaFilled(false);
        btnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        btnListado.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        btnListado.setText("Listado");
        btnListado.setContentAreaFilled(false);
        btnListado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnRegistro)
                .addGap(18, 18, 18)
                .addComponent(btnListado)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistro)
                    .addComponent(btnListado))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 120, 450, 50);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Piso:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel3.setText("Nombre de habitación:");

        cbxPiso.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxPiso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxPiso.setEnabled(false);
        cbxPiso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPisoItemStateChanged(evt);
            }
        });

        txtNombreHab.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNombreHab.setEnabled(false);

        lblMant.setText("jLabel5");

        lblID.setText("jLabel5");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("Servicio:");

        cbxServicio.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxServicio.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMant)
                        .addGap(37, 37, 37)
                        .addComponent(lblID))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbxServicio, 0, 180, Short.MAX_VALUE)
                                .addComponent(txtNombreHab)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMant)
                    .addComponent(lblID))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tbHospHab.addTab("Registro", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbHospListar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbHospListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbHospListar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbHospListar.setGridColor(new java.awt.Color(255, 255, 255));
        tbHospListar.setSelectionBackground(new java.awt.Color(217, 100, 118));
        tbHospListar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHospListarMouseClicked(evt);
            }
        });
        tbHospListar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHospListarKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbHospListar);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        jLabel1.setText("Buscar:");

        txtBuscar.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCaretUpdate(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        tbHospHab.addTab("Listado", jPanel3);

        getContentPane().add(tbHospHab);
        tbHospHab.setBounds(0, 140, 450, 260);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        lblMant.setText("I");
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        limpiarCajas();
        habilitarCajas(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        habilitarCajas(true);
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            int piso = Integer.parseInt(cbxPiso.getSelectedItem().toString());
            String hab_nom = txtNombreHab.getText();
            String servicio = cbxServicio.getSelectedItem().toString();
            if(lblMant.getText().equals("I")){//NUEVO REGISTRO
                if(cbxPiso.getSelectedIndex()==0 || txtNombreHab.getText().equals("") 
                        || 
                        cbxServicio.getSelectedIndex()==0){//VALIDAR
                    JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                    txtNombreHab.setBackground(new Color(235,167,176));
                    cbxPiso.setBackground(new Color(235,167,176));
                    cbxServicio.setBackground(new Color(235,167,176));
                } else { //GUARDAR
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0){
                        HospitalizacionHabitaciones hospP = new HospitalizacionHabitaciones();
                        HospitalizacionHabitaciones hospP2 = new HospitalizacionHabitaciones();
                        HospitalizacionPisos p = new HospitalizacionPisos();
                        AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
                        hospP.setPiso_id(Integer.parseInt(hospP2.codPiso(piso)));
                        hospP.setHab_nom(hab_nom);
                        hospP.setCod_usu(adEmerCab.codUsuario(lblUsuUsuario.getText()));
                        hospP.setSe_id(Integer.parseInt(p.codServicio(servicio)));
                        if(hospP.mantenimientoHospitalizacionHabitacion(lblMant.getText())){
                            JOptionPane.showMessageDialog(this, "Datos guardados");
                            habilitarCajas(false);
                            limpiarCajas();
                            btnModificar.setEnabled(false);
                            btnEliminar.setEnabled(false);
                            btnGuardar.setEnabled(false);
                        }else{
                            JOptionPane.showMessageDialog(this, "Error al guardar datos");}
                    }
                }
            } else
            if(lblMant.getText().equals("U")){//MODIFICAR 
                if(cbxPiso.getSelectedIndex()==0 || txtNombreHab.getText().equals("") 
                        || 
                        cbxServicio.getSelectedIndex()==0){//VALIDAR
                    JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                    txtNombreHab.setBackground(new Color(235,167,176));
                    cbxPiso.setBackground(new Color(235,167,176));
                    cbxServicio.setBackground(new Color(235,167,176));
                } else { //GUARDAR
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(modificar == 0){
                        HospitalizacionHabitaciones hospP = new HospitalizacionHabitaciones();
                        HospitalizacionHabitaciones hospP2 = new HospitalizacionHabitaciones();
                        HospitalizacionPisos p = new HospitalizacionPisos();
                        AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
                        hospP.setPiso_id(Integer.parseInt(hospP2.codPiso(piso)));
                        hospP.setHab_nom(hab_nom);
                        hospP.setHab_id(Integer.parseInt(lblID.getText()));
                        hospP.setSe_id(Integer.parseInt(p.codServicio(servicio)));
                        if(hospP.mantenimientoHospitalizacionHabitacion(lblMant.getText())){
                            JOptionPane.showMessageDialog(this, "Datos modificados");
                            habilitarCajas(false);
                            limpiarCajas();
                            btnModificar.setEnabled(false);
                            btnEliminar.setEnabled(false);
                            btnGuardar.setEnabled(false);
                        }else{
                            JOptionPane.showMessageDialog(this, "Error al modificar datos");}
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: btnGuardarActionPermormed" + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR los datos?",
                                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
            if(eliminar == 0){
                HospitalizacionHabitaciones hospP = new HospitalizacionHabitaciones();
                HospitalizacionHabitaciones hospP2 = new HospitalizacionHabitaciones();
                hospP.setHab_id(Integer.parseInt(lblID.getText()));
                if(hospP.mantenimientoHospitalizacionHabitacion("E")){
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    habilitarCajas(false);
                    limpiarCajas();
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    btnGuardar.setEnabled(false);
                }else
                    JOptionPane.showMessageDialog(this, "Error al eliminar datos");
            }
        } catch (Exception e) {
            System.out.println("Error: btnEliminarActionPerformed" + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        btnBuscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxPisoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPisoItemStateChanged
        
    }//GEN-LAST:event_cbxPisoItemStateChanged

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        btnBuscar();
    }//GEN-LAST:event_btnListadoActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        HospitalizacionHabitaciones hospP = new HospitalizacionHabitaciones();
        hospP.setHab_nom(txtBuscar.getText());
        hospP.hospitalizacionHabListar(tbHospListar);
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        seleccionRegistro();        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbHospListar.getSelectionModel().setSelectionInterval(0, 0);
            tbHospListar.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tbHospListarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHospListarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbHospListar.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscar.requestFocus();
            tbHospListar.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                modificarHosHab();
        }
    }//GEN-LAST:event_tbHospListarKeyPressed

    private void tbHospListarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHospListarMouseClicked
        if(evt.getClickCount()==2)
            modificarHosHab();
    }//GEN-LAST:event_tbHospListarMouseClicked

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionHabitaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionHabitaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListado;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JComboBox cbxPiso;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JTabbedPane tbHospHab;
    private javax.swing.JTable tbHospListar;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombreHab;
    // End of variables declaration//GEN-END:variables
}
