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
import modelos.hospitalizacion.HospitalizacionCamas;
import modelos.hospitalizacion.HospitalizacionHabitaciones;
import modelos.hospitalizacion.HospitalizacionPisos;
import servicios.Conexion;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDepartamento;
import static vista.hospitalizacion.FrmHospitalizacionHabitaciones.lblUsuUsuario;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionCamas extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    public FrmHospitalizacionCamas() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        conexion = c.conectar();
        cbxPiso.setBackground(Color.white);
        cbxHab.setBackground(Color.white);
        cbxServicio.setBackground(Color.white);
        cbxPiso.setModel(pisos());
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
        cbxHab.setSelectedIndex(0);
        cbxServicio.setSelectedIndex(0);
        rbtM.setSelected(true);
        txtAsignacion.setText("");
        txtDescripcion.setText("");
    }
    
    public void habilitarCajas(boolean opcion){
        cbxPiso.setEnabled(opcion);
        cbxHab.setEnabled(opcion);
        cbxServicio.setEnabled(opcion);
        rbtM.setEnabled(opcion);
        rbtF.setEnabled(opcion);
        rbtT.setEnabled(opcion);
        txtAsignacion.setEnabled(opcion);
        txtDescripcion.setEnabled(opcion);
    }
    
    public void seleccionRegistro(){
        tbHosC.setSelectedIndex(0);
        btnRegistro.setForeground(Color.red);
        btnListado.setForeground(Color.black);
    }
    
    public void btnBuscar(){
        tbHosC.setSelectedIndex(1);
        btnListado.setForeground(Color.red);
        btnRegistro.setForeground(Color.black);
        HospitalizacionCamas hospP = new HospitalizacionCamas();
        hospP.hospitalizacionCamListar(tbListar,txtBuscar.getText());
        tbListar.getSelectionModel().setSelectionInterval(0,0);
        tbListar.requestFocus();
        txtBuscar.requestFocus();
    }
    
    public void modificarHosCamas(){
        int fila = tbListar.getSelectedRow();
        lblID.setText(String.valueOf(tbListar.getValueAt(fila, 0)));
        cbxPiso.setSelectedItem(String.valueOf(tbListar.getValueAt(fila, 1)));
        cbxServicio.setSelectedItem(String.valueOf(tbListar.getValueAt(fila, 3)));
        cbxHab.setSelectedItem(String.valueOf(tbListar.getValueAt(fila, 2)));
        String genero = String.valueOf(tbListar.getValueAt(fila, 4));
        if(genero .equals("M"))
            rbtM.setSelected(true);
        else if(genero.equals("F"))
            rbtF.setSelected(true);
        else
            rbtT.setSelected(true);
        txtDescripcion.setText(String.valueOf(tbListar.getValueAt(fila, 5)));
        txtAsignacion.setText(String.valueOf(tbListar.getValueAt(fila, 6)));
        tbHosC.setSelectedIndex(0);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        tbHosC = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxPiso = new javax.swing.JComboBox();
        cbxHab = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        rbtM = new javax.swing.JRadioButton();
        rbtF = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtAsignacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox();
        lblMant = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        rbtT = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 420));
        getContentPane().setLayout(null);

        jPanel8.setBackground(new java.awt.Color(255, 119, 0));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Hospitalización - Camas");
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
                .addGap(34, 34, 34)
                .addComponent(btnRegistro)
                .addGap(18, 18, 18)
                .addComponent(btnListado)
                .addContainerGap(228, Short.MAX_VALUE))
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
        jLabel3.setText("Habitaciones:");

        cbxPiso.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxPiso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxPiso.setEnabled(false);
        cbxPiso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPisoItemStateChanged(evt);
            }
        });
        cbxPiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxPisoKeyPressed(evt);
            }
        });

        cbxHab.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxHab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxHab.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel4.setText("Género:");

        buttonGroup1.add(rbtM);
        rbtM.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        rbtM.setSelected(true);
        rbtM.setText("M");
        rbtM.setEnabled(false);

        buttonGroup1.add(rbtF);
        rbtF.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        rbtF.setText("F");
        rbtF.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("Asignación:");

        txtAsignacion.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtAsignacion.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("Servicio");

        cbxServicio.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxServicio.setEnabled(false);
        cbxServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicioItemStateChanged(evt);
            }
        });

        lblMant.setText("jLabel7");

        lblID.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setText("Descripción");

        txtDescripcion.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDescripcion.setEnabled(false);

        buttonGroup1.add(rbtT);
        rbtT.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        rbtT.setText("T");
        rbtT.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxHab, javax.swing.GroupLayout.Alignment.LEADING, 0, 180, Short.MAX_VALUE)
                            .addComponent(cbxServicio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescripcion)
                            .addComponent(txtAsignacion)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbxPiso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(rbtM)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtF)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtT)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMant, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblID, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rbtM)
                    .addComponent(rbtF)
                    .addComponent(lblMant)
                    .addComponent(rbtT))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblID)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 46, Short.MAX_VALUE))
        );

        tbHosC.addTab("Registro", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbListar = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbListar.setModel(new javax.swing.table.DefaultTableModel(
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
        tbListar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbListar.setGridColor(new java.awt.Color(255, 255, 255));
        tbListar.setSelectionBackground(new java.awt.Color(217, 100, 118));
        tbListar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbListarKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbListar);

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
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbHosC.addTab("Listado", jPanel3);

        getContentPane().add(tbHosC);
        tbHosC.setBounds(0, 140, 450, 270);

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
            String servicio = cbxServicio.getSelectedItem().toString();
            String habitacion = cbxHab.getSelectedItem().toString();
            String asignacion = txtAsignacion.getText();
            String descripcion = txtDescripcion.getText();
            if(lblMant.getText().equals("I")){//NUEVO REGISTRO
                if(cbxPiso.getSelectedIndex()==0 || cbxServicio.getSelectedIndex()==0
                        || cbxHab.getSelectedIndex()==0 || asignacion.equals("") || descripcion.equals("")){//VALIDAR
                    JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                    txtAsignacion.setBackground(new Color(235,167,176));
                    cbxPiso.setBackground(new Color(235,167,176));
                    cbxServicio.setBackground(new Color(235,167,176));
                    cbxHab.setBackground(new Color(235,167,176));
                    txtDescripcion.setBackground(new Color(235,167,176));
                } else { //GUARDAR
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0){
                        HospitalizacionCamas hospP = new HospitalizacionCamas();
                        HospitalizacionCamas hospP2 = new HospitalizacionCamas();
                        HospitalizacionPisos p = new HospitalizacionPisos();
                        AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
                        hospP.setHab_id(Integer.parseInt(hospP2.codHabitacion(habitacion)));
                        if(rbtM.isSelected())
                            hospP.setCa_gen("M");
                        else if (rbtF.isSelected())
                            hospP.setCa_gen("F");
                        else if(rbtT.isSelected())
                            hospP.setCa_gen("T");
                        hospP.setCa_asign(asignacion);
                        hospP.setCa_desc(descripcion);
                        hospP.setCod_usu(adEmerCab.codUsuario(lblUsuUsuario.getText()));
                        if(hospP.mantenimientoHospitalizacionCamas(lblMant.getText())){
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
                if(cbxPiso.getSelectedIndex()==0 || cbxServicio.getSelectedIndex()==0
                        || cbxHab.getSelectedIndex()==0 || asignacion.equals("") || descripcion.equals("")){//VALIDAR
                    JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
                    txtAsignacion.setBackground(new Color(235,167,176));
                    cbxPiso.setBackground(new Color(235,167,176));
                    cbxServicio.setBackground(new Color(235,167,176));
                    cbxHab.setBackground(new Color(235,167,176));
                    txtDescripcion.setBackground(new Color(235,167,176));
                } else { //GUARDAR
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
                    int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(modificar == 0){
                        HospitalizacionCamas hospP = new HospitalizacionCamas();
                        HospitalizacionCamas hospP2 = new HospitalizacionCamas();
                        HospitalizacionPisos p = new HospitalizacionPisos();
                        hospP.setHab_id(Integer.parseInt(hospP2.codHabitacion(habitacion)));
                        hospP.setCa_id(Integer.parseInt(lblID.getText()));
                        if(rbtM.isSelected())
                            hospP.setCa_gen("M");
                        else if (rbtF.isSelected())
                            hospP.setCa_gen("F");
                        else if(rbtT.isSelected())
                            hospP.setCa_gen("T");
                        hospP.setCa_asign(asignacion);
                        hospP.setCa_desc(descripcion);
                        if(hospP.mantenimientoHospitalizacionCamas(lblMant.getText())){
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
                HospitalizacionCamas hospP = new HospitalizacionCamas();
                HospitalizacionCamas hospP2 = new HospitalizacionCamas();
                hospP.setCa_id(Integer.parseInt(lblID.getText()));
                if(hospP.mantenimientoHospitalizacionCamas("E")){
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

    private void cbxPisoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxPisoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPisoKeyPressed

    private void cbxPisoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPisoItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxPiso.getSelectedIndex()>0){
                        this.cbxServicio.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    int piso=Integer.parseInt(cbxPiso.getSelectedItem().toString());
                    ResultSet rs=sta.executeQuery("EXEC HOSPITALIZACION_LISTAR_PISOS_HAB_PCAMAS '"+piso+"'");
                    this.cbxServicio.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxServicio.addItem(rs.getString("SE_DESC"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxServicio.removeAllItems();

                        this.cbxServicio.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxPisoItemStateChanged

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxServicio.getSelectedIndex()>0){
                        this.cbxHab.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String servicio = cbxServicio.getSelectedItem().toString();
                    int piso=Integer.parseInt(cbxPiso.getSelectedItem().toString());
                    ResultSet rs=sta.executeQuery("EXEC HOSPITALIZAION_LISTAR_HAB_PCAMAS '"+ servicio + "','" + piso+"'");
                    this.cbxHab.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxHab.addItem(rs.getString("HAB_NOM"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxHab.removeAllItems();

                        this.cbxHab.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxServicioItemStateChanged

    private void btnListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListadoActionPerformed
        btnBuscar();
    }//GEN-LAST:event_btnListadoActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        seleccionRegistro();
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbListar.getSelectionModel().setSelectionInterval(0, 0);
            tbListar.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tbListarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbListarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbListar.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscar.requestFocus();
            tbListar.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
            if(teclaPresionada==KeyEvent.VK_ENTER){
                modificarHosCamas();
            }
    }//GEN-LAST:event_tbListarKeyPressed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        HospitalizacionCamas hospP = new HospitalizacionCamas();
        hospP.hospitalizacionCamListar(tbListar,txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarCaretUpdate

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionCamas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCamas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCamas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionCamas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionCamas().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxHab;
    private javax.swing.JComboBox cbxPiso;
    private javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JRadioButton rbtF;
    private javax.swing.JRadioButton rbtM;
    private javax.swing.JRadioButton rbtT;
    private javax.swing.JTabbedPane tbHosC;
    private javax.swing.JTable tbListar;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtAsignacion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
