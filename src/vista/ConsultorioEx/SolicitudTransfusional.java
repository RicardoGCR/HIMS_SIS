/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class SolicitudTransfusional extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    PreparedStatement pstm;
    AdmisionEmergenciaCabecera adEmerCab7 = new AdmisionEmergenciaCabecera();
    public SolicitudTransfusional() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
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
        conexion = c.conectar();
        cerrar();
        pnlMensaje.setVisible(false);
        habilitarCampos(false);
        
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
    
    public void habilitarCampos(boolean opcion){
        txtCrioprecitado.setEnabled(opcion);
        txtDiag.setEnabled(opcion);
        txtHb.setEnabled(opcion);
        txtHto.setEnabled(opcion);
        txtOtros.setEnabled(opcion);
        txtOtrosEsp.setEnabled(opcion);;
        txtPaciente.setEnabled(opcion);
        txtPaqGlobular.setEnabled(opcion);
        txtPlaquetas.setEnabled(opcion);
        txtPlaquetas2.setEnabled(opcion);
        txtPlasma.setEnabled(opcion);
        txtPlasmaFresco.setEnabled(opcion);
        txtSangreTotal.setEnabled(opcion);
        txtUnidPedi.setEnabled(opcion);
        chkTransADesc.setEnabled(opcion);
        chkTransANo.setEnabled(opcion);
        chkTransASi.setEnabled(opcion);
        chkTransDesc.setEnabled(opcion);
        chkTransSi.setEnabled(opcion);
        chkTransfNo.setEnabled(opcion);
        rbtMuyUrgente.setEnabled(opcion);
        rbtProgramada.setEnabled(opcion);
        rbtUrgente.setEnabled(opcion);
        cbxAbortos.setEnabled(opcion);
        cbxEmbPrevio.setEnabled(opcion);
        cbxIncomp.setEnabled(opcion);
        spFecha.setEnabled(opcion);
    }
    
    public void limpiar(){
        txtCrioprecitado.setText("");
        txtDiag.setText("");
        txtHb.setText("");
        txtHto.setText("");
        txtOtros.setText("");
        txtOtrosEsp.setText("");
        txtPaciente.setText("");
        txtPaqGlobular.setText("");
        txtPlaquetas.setText("");
        txtPlaquetas2.setText("");
        txtPlasma.setText("");
        txtPlasmaFresco.setText("");
        txtSangreTotal.setText("");
        txtUnidPedi.setText("");
        chkTransADesc.setSelected(false);
        chkTransANo.setSelected(false);
        chkTransASi.setSelected(false);
        chkTransDesc.setSelected(false);
        chkTransSi.setSelected(false);
        chkTransfNo.setSelected(false);
        rbtMuyUrgente.setSelected(true);
        rbtProgramada.setSelected(false);
        rbtUrgente.setSelected(false);
        cbxAbortos.setSelectedIndex(0);
        cbxEmbPrevio.setSelectedIndex(0);
        cbxIncomp.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarPacientes = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscarMadres = new javax.swing.JTextField();
        T7 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMadres = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            bgpRequisitos = new javax.swing.ButtonGroup();
            BGbgpTransAnteriores = new javax.swing.ButtonGroup();
            bgpTransPrevias = new javax.swing.ButtonGroup();
            jPanel1 = new javax.swing.JPanel();
            lblusu = new javax.swing.JLabel();
            btnbuscar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btnNuevo = new javax.swing.JButton();
            jLabel4 = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            lblPadres1 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            txtPaciente = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            jLabel3 = new javax.swing.JLabel();
            lblEdad = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            lblSexo = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            lblNHC = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            lblGrupo = new javax.swing.JLabel();
            chkTransSi = new javax.swing.JCheckBox();
            chkTransfNo = new javax.swing.JCheckBox();
            chkTransDesc = new javax.swing.JCheckBox();
            jLabel9 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            chkTransASi = new javax.swing.JCheckBox();
            chkTransANo = new javax.swing.JCheckBox();
            chkTransADesc = new javax.swing.JCheckBox();
            jPanel3 = new javax.swing.JPanel();
            jLabel11 = new javax.swing.JLabel();
            cbxEmbPrevio = new javax.swing.JComboBox();
            jLabel12 = new javax.swing.JLabel();
            cbxAbortos = new javax.swing.JComboBox();
            cbxIncomp = new javax.swing.JComboBox();
            jLabel13 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            jPanel11 = new javax.swing.JPanel();
            txtDiag = new javax.swing.JTextField();
            btnBuscarNino2 = new javax.swing.JButton();
            jLabel15 = new javax.swing.JLabel();
            txtHb = new javax.swing.JTextField();
            jLabel16 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            txtHto = new javax.swing.JTextField();
            jLabel18 = new javax.swing.JLabel();
            jLabel19 = new javax.swing.JLabel();
            txtPlaquetas = new javax.swing.JTextField();
            lblCubo = new javax.swing.JLabel();
            lblCubo1 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            jLabel22 = new javax.swing.JLabel();
            jLabel23 = new javax.swing.JLabel();
            jLabel24 = new javax.swing.JLabel();
            txtSangreTotal = new javax.swing.JTextField();
            txtPaqGlobular = new javax.swing.JTextField();
            txtPlasmaFresco = new javax.swing.JTextField();
            txtCrioprecitado = new javax.swing.JTextField();
            jLabel25 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            jLabel28 = new javax.swing.JLabel();
            jLabel29 = new javax.swing.JLabel();
            jLabel30 = new javax.swing.JLabel();
            jLabel31 = new javax.swing.JLabel();
            jLabel32 = new javax.swing.JLabel();
            txtPlasma = new javax.swing.JTextField();
            txtPlaquetas2 = new javax.swing.JTextField();
            txtUnidPedi = new javax.swing.JTextField();
            txtOtros = new javax.swing.JTextField();
            jLabel33 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            jLabel35 = new javax.swing.JLabel();
            jLabel36 = new javax.swing.JLabel();
            txtOtrosEsp = new javax.swing.JTextField();
            jLabel37 = new javax.swing.JLabel();
            spFecha = new javax.swing.JSpinner();
            jLabel38 = new javax.swing.JLabel();
            rbtMuyUrgente = new javax.swing.JRadioButton();
            rbtUrgente = new javax.swing.JRadioButton();
            rbtProgramada = new javax.swing.JRadioButton();
            pnlMensaje = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            btnSi = new javax.swing.JButton();
            btnNo = new javax.swing.JButton();

            BuscarPacientes.setAlwaysOnTop(true);
            BuscarPacientes.setMinimumSize(new java.awt.Dimension(615, 333));
            BuscarPacientes.setResizable(false);

            jPanel28.setBackground(new java.awt.Color(102, 102, 102));

            jLabel39.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel39.setForeground(new java.awt.Color(255, 255, 255));
            jLabel39.setText("Paciente");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));

            txtBuscarMadres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtBuscarMadres.setBorder(null);
            txtBuscarMadres.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarMadresCaretUpdate(evt);
                }
            });
            txtBuscarMadres.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtBuscarMadresMouseClicked(evt);
                }
            });
            txtBuscarMadres.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarMadresActionPerformed(evt);
                }
            });
            txtBuscarMadres.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarMadresKeyPressed(evt);
                }
            });

            T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    T7MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(T7)
                        .addComponent(txtBuscarMadres, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel30.setBackground(new java.awt.Color(0, 153, 102));

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 615, Short.MAX_VALUE)
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 21, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel39)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(549, 549, 549))
            );

            jScrollPane4.setBorder(null);

            tbMadres.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbMadres.setGridColor(new java.awt.Color(255, 255, 255));
            tbMadres.setRowHeight(25);
            tbMadres.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbMadres.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbMadresMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbMadresMousePressed(evt);
                }
            });
            tbMadres.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbMadresKeyPressed(evt);
                }
            });
            jScrollPane4.setViewportView(tbMadres);

            javax.swing.GroupLayout BuscarPacientesLayout = new javax.swing.GroupLayout(BuscarPacientes.getContentPane());
            BuscarPacientes.getContentPane().setLayout(BuscarPacientesLayout);
            BuscarPacientesLayout.setHorizontalGroup(
                BuscarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
            );
            BuscarPacientesLayout.setVerticalGroup(
                BuscarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BuscarPacientesLayout.createSequentialGroup()
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(0, 153, 102));

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblusu.setText("Silvana");

            btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
            btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
            btnbuscar.setMnemonic('N');
            btnbuscar.setContentAreaFilled(false);
            btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnbuscar.setIconTextGap(30);
            btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnbuscarActionPerformed(evt);
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

            jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            jLabel4.setText("Consentimiento Informado del Receptor");

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
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblusu))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminar)
                            .addComponent(btnbuscar))
                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(510, 510, 510))
            );

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(51, 51, 51));
            jLabel2.setText("Paciente ");

            lblPadres1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblPadres1.setForeground(new java.awt.Color(51, 51, 51));
            lblPadres1.setText("Datos Personales________________________________________________________________");

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPaciente.setEditable(false);
            txtPaciente.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtPaciente.setForeground(new java.awt.Color(51, 51, 51));
            txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPaciente.setToolTipText("");
            txtPaciente.setBorder(null);
            txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPacienteCaretUpdate(evt);
                }
            });

            btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarPaciente.setMnemonic('B');
            btnBuscarPaciente.setToolTipText("");
            btnBuscarPaciente.setBorderPainted(false);
            btnBuscarPaciente.setContentAreaFilled(false);
            btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarPacienteActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(51, 51, 51));
            jLabel3.setText("Edad");

            lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblEdad.setForeground(new java.awt.Color(51, 51, 51));
            lblEdad.setText("_______");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(51, 51, 51));
            jLabel6.setText("Sexo");

            lblSexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblSexo.setForeground(new java.awt.Color(51, 51, 51));
            lblSexo.setText("_______");

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(51, 51, 51));
            jLabel7.setText("Nº Historia Clínica");

            lblNHC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNHC.setForeground(new java.awt.Color(51, 51, 51));
            lblNHC.setText("_______________");

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(51, 51, 51));
            jLabel8.setText("Grupo");

            lblGrupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblGrupo.setForeground(new java.awt.Color(51, 51, 51));
            lblGrupo.setText("_______________");

            chkTransSi.setBackground(new java.awt.Color(255, 255, 255));
            bgpTransPrevias.add(chkTransSi);
            chkTransSi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransSi.setText("Si");
            chkTransSi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkTransSiActionPerformed(evt);
                }
            });

            chkTransfNo.setBackground(new java.awt.Color(255, 255, 255));
            bgpTransPrevias.add(chkTransfNo);
            chkTransfNo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransfNo.setText("No");

            chkTransDesc.setBackground(new java.awt.Color(255, 255, 255));
            bgpTransPrevias.add(chkTransDesc);
            chkTransDesc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransDesc.setText("Desconocido");

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel9.setForeground(new java.awt.Color(51, 51, 51));
            jLabel9.setText("Transfunsiones Previas");

            jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(51, 51, 51));
            jLabel10.setText("Reacciones Transfunsiones Anteriores");

            chkTransASi.setBackground(new java.awt.Color(255, 255, 255));
            BGbgpTransAnteriores.add(chkTransASi);
            chkTransASi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransASi.setText("Si");
            chkTransASi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkTransASiActionPerformed(evt);
                }
            });

            chkTransANo.setBackground(new java.awt.Color(255, 255, 255));
            BGbgpTransAnteriores.add(chkTransANo);
            chkTransANo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransANo.setText("No");

            chkTransADesc.setBackground(new java.awt.Color(255, 255, 255));
            BGbgpTransAnteriores.add(chkTransADesc);
            chkTransADesc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkTransADesc.setText("Desconocido");

            jPanel3.setBackground(new java.awt.Color(255, 255, 255));

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel11.setForeground(new java.awt.Color(51, 51, 51));
            jLabel11.setText("Embarazos Previos");

            cbxEmbPrevio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

            jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel12.setForeground(new java.awt.Color(51, 51, 51));
            jLabel12.setText("Abortos");

            cbxAbortos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

            cbxIncomp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(51, 51, 51));
            jLabel13.setText("Incompatibilidad Materno Fetal");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbxAbortos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxEmbPrevio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbxIncomp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 10, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cbxEmbPrevio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxAbortos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(cbxIncomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(51, 51, 51));
            jLabel14.setText("Diagnóstico de Enfermedad");

            jPanel11.setBackground(new java.awt.Color(255, 255, 255));
            jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtDiag.setEditable(false);
            txtDiag.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtDiag.setForeground(new java.awt.Color(51, 51, 51));
            txtDiag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDiag.setToolTipText("");
            txtDiag.setBorder(null);
            txtDiag.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtDiagCaretUpdate(evt);
                }
            });

            btnBuscarNino2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarNino2.setMnemonic('B');
            btnBuscarNino2.setToolTipText("");
            btnBuscarNino2.setBorderPainted(false);
            btnBuscarNino2.setContentAreaFilled(false);
            btnBuscarNino2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarNino2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarNino2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtDiag, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtDiag, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel15.setForeground(new java.awt.Color(51, 51, 51));
            jLabel15.setText("Hb");

            txtHb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel16.setForeground(new java.awt.Color(51, 51, 51));
            jLabel16.setText("g/ dl");

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(51, 51, 51));
            jLabel17.setText("Hto");

            txtHto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel18.setForeground(new java.awt.Color(51, 51, 51));
            jLabel18.setText("%");

            jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel19.setForeground(new java.awt.Color(51, 51, 51));
            jLabel19.setText("Plaquetas");

            txtPlaquetas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            lblCubo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblCubo.setForeground(new java.awt.Color(51, 51, 51));
            lblCubo.setText("/mm");

            lblCubo1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            lblCubo1.setForeground(new java.awt.Color(51, 51, 51));
            lblCubo1.setText("3");

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(51, 51, 51));
            jLabel20.setText("Requerimiento______________________________________________________________________________________________");

            jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel21.setForeground(new java.awt.Color(51, 51, 51));
            jLabel21.setText("Sangre Total");

            jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel22.setForeground(new java.awt.Color(51, 51, 51));
            jLabel22.setText("Paquete Globular");

            jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel23.setForeground(new java.awt.Color(51, 51, 51));
            jLabel23.setText("Plasma Fresco Congelado");

            jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel24.setForeground(new java.awt.Color(51, 51, 51));
            jLabel24.setText("Crioprecipitado");

            txtSangreTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtPaqGlobular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtPlasmaFresco.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtCrioprecitado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel25.setForeground(new java.awt.Color(51, 51, 51));
            jLabel25.setText("Unidades");

            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(51, 51, 51));
            jLabel26.setText("Unidades");

            jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel27.setForeground(new java.awt.Color(51, 51, 51));
            jLabel27.setText("Unidades");

            jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel28.setForeground(new java.awt.Color(51, 51, 51));
            jLabel28.setText("Unidades");

            jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel29.setForeground(new java.awt.Color(51, 51, 51));
            jLabel29.setText("Plasma");

            jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(51, 51, 51));
            jLabel30.setText("Plaquetas");

            jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel31.setForeground(new java.awt.Color(51, 51, 51));
            jLabel31.setText("Unidades Pediátricas");

            jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel32.setForeground(new java.awt.Color(51, 51, 51));
            jLabel32.setText("Otros (especifique)");

            txtPlasma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtPlaquetas2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtUnidPedi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtOtros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(51, 51, 51));
            jLabel33.setText("Unidades");

            jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel34.setForeground(new java.awt.Color(51, 51, 51));
            jLabel34.setText("Unidades");

            jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel35.setForeground(new java.awt.Color(51, 51, 51));
            jLabel35.setText("Unidades");

            jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel36.setForeground(new java.awt.Color(51, 51, 51));
            jLabel36.setText("Unidades");

            txtOtrosEsp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel37.setForeground(new java.awt.Color(51, 51, 51));
            jLabel37.setText("Fecha / Hora");

            spFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            spFecha.setModel(new javax.swing.SpinnerDateModel());

            jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel38.setForeground(new java.awt.Color(51, 51, 51));
            jLabel38.setText("Requisitos____________________________");

            bgpRequisitos.add(rbtMuyUrgente);
            rbtMuyUrgente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbtMuyUrgente.setText("MUY URGENTE (SIN PRUEBA CRUZADA)");
            rbtMuyUrgente.setContentAreaFilled(false);
            rbtMuyUrgente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            bgpRequisitos.add(rbtUrgente);
            rbtUrgente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbtUrgente.setText("URGENTE");
            rbtUrgente.setContentAreaFilled(false);
            rbtUrgente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            bgpRequisitos.add(rbtProgramada);
            rbtProgramada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            rbtProgramada.setText("PROGRAMADA");
            rbtProgramada.setContentAreaFilled(false);
            rbtProgramada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblPadres1, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel24))
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtCrioprecitado, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel28))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtPlasmaFresco, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel27))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtPaqGlobular, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel26))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtSangreTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel25)))
                                    .addGap(104, 104, 104)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel29)
                                                .addComponent(jLabel30))
                                            .addGap(165, 165, 165))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel32)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtOtrosEsp))
                                                .addComponent(jLabel31))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel33))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtUnidPedi, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel34))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtPlaquetas2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel35))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtPlasma, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel36)))))
                            .addGap(57, 57, 57)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel38)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel37)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(rbtMuyUrgente)
                                .addComponent(rbtUrgente)
                                .addComponent(rbtProgramada)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(40, 40, 40)
                                    .addComponent(chkTransSi)
                                    .addGap(18, 18, 18)
                                    .addComponent(chkTransfNo)
                                    .addGap(18, 18, 18)
                                    .addComponent(chkTransDesc)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(34, 34, 34)
                                    .addComponent(chkTransASi)
                                    .addGap(18, 18, 18)
                                    .addComponent(chkTransANo)
                                    .addGap(18, 18, 18)
                                    .addComponent(chkTransADesc))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNHC)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblEdad)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblSexo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblGrupo))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(70, 70, 70)
                                    .addComponent(txtHb, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel16)
                                    .addGap(60, 60, 60)
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtHto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel18)
                                    .addGap(58, 58, 58)
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPlaquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(lblCubo)
                                    .addGap(0, 0, 0)
                                    .addComponent(lblCubo1)))))
                    .addContainerGap(117, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblPadres1)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblNHC)
                            .addComponent(jLabel3)
                            .addComponent(lblEdad)
                            .addComponent(jLabel6)
                            .addComponent(lblSexo)
                            .addComponent(jLabel8)
                            .addComponent(lblGrupo)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkTransSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkTransfNo)
                        .addComponent(chkTransDesc)
                        .addComponent(jLabel9)
                        .addComponent(chkTransASi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkTransANo)
                        .addComponent(chkTransADesc)
                        .addComponent(jLabel10))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))
                            .addGap(8, 8, 8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel17)
                                            .addComponent(txtHto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel19)
                                            .addComponent(txtPlaquetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCubo)
                                            .addComponent(txtHb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblCubo1)))))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(jLabel38))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21)
                                        .addComponent(txtSangreTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel29))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel22)
                                        .addComponent(txtPaqGlobular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26)
                                        .addComponent(jLabel30))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel23)
                                        .addComponent(txtPlasmaFresco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel27))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel24)
                                        .addComponent(txtCrioprecitado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel28)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(rbtMuyUrgente)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbtUrgente)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rbtProgramada)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(spFecha)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addComponent(jLabel32)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPlasma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPlaquetas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtUnidPedi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34)
                                .addComponent(jLabel31))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtOtros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(txtOtrosEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(31, Short.MAX_VALUE))
            );

            pnlMensaje.setBackground(new java.awt.Color(255, 153, 51));

            lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
            lblMensaje.setText("Mensaje");

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
                    .addGap(46, 46, 46)
                    .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            pnlMensajeLayout.setVerticalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMensajeLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMensaje)
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            lblMant.setText("I");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate

    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void chkTransSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTransSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTransSiActionPerformed

    private void chkTransASiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTransASiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTransASiActionPerformed

    private void txtDiagCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDiagCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagCaretUpdate

    private void btnBuscarNino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino2ActionPerformed

    private void txtBuscarMadresCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMadresCaretUpdate
       
    }//GEN-LAST:event_txtBuscarMadresCaretUpdate

    private void txtBuscarMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMadresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMadresMouseClicked

    private void txtBuscarMadresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMadresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMadresActionPerformed

    private void txtBuscarMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMadresKeyPressed
       
    }//GEN-LAST:event_txtBuscarMadresKeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void tbMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMouseClicked
      
    }//GEN-LAST:event_tbMadresMouseClicked

    private void tbMadresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMadresMousePressed

    private void tbMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMadresKeyPressed
       
    }//GEN-LAST:event_tbMadresKeyPressed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
//        if(btnSi.getText().equals("Si")){ // Al guardar
//            if(lblMant.getText().equals("I")){
//                if(guardarDatos()==true)
//                JOptionPane.showMessageDialog(this, "ok");
//            }
//        } else
//        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
//            pnlMensaje.setVisible(false);
//        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed

    }//GEN-LAST:event_btnNoActionPerformed

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
            java.util.logging.Logger.getLogger(SolicitudTransfusional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SolicitudTransfusional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SolicitudTransfusional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SolicitudTransfusional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolicitudTransfusional().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BGbgpTransAnteriores;
    private javax.swing.JDialog BuscarPacientes;
    private javax.swing.JLabel T7;
    private javax.swing.ButtonGroup bgpRequisitos;
    private javax.swing.ButtonGroup bgpTransPrevias;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox cbxAbortos;
    private javax.swing.JComboBox cbxEmbPrevio;
    private javax.swing.JComboBox cbxIncomp;
    public static javax.swing.JCheckBox chkTransADesc;
    public static javax.swing.JCheckBox chkTransANo;
    public static javax.swing.JCheckBox chkTransASi;
    public static javax.swing.JCheckBox chkTransDesc;
    public static javax.swing.JCheckBox chkTransSi;
    public static javax.swing.JCheckBox chkTransfNo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCubo;
    private javax.swing.JLabel lblCubo1;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNHC;
    private javax.swing.JLabel lblPadres1;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JRadioButton rbtMuyUrgente;
    private javax.swing.JRadioButton rbtProgramada;
    private javax.swing.JRadioButton rbtUrgente;
    private javax.swing.JSpinner spFecha;
    private javax.swing.JTable tbMadres;
    private javax.swing.JTextField txtBuscarMadres;
    public static javax.swing.JTextField txtCrioprecitado;
    public static javax.swing.JTextField txtDiag;
    public static javax.swing.JTextField txtHb;
    public static javax.swing.JTextField txtHto;
    public static javax.swing.JTextField txtOtros;
    public static javax.swing.JTextField txtOtrosEsp;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JTextField txtPaqGlobular;
    public static javax.swing.JTextField txtPlaquetas;
    public static javax.swing.JTextField txtPlaquetas2;
    public static javax.swing.JTextField txtPlasma;
    public static javax.swing.JTextField txtPlasmaFresco;
    public static javax.swing.JTextField txtSangreTotal;
    public static javax.swing.JTextField txtUnidPedi;
    // End of variables declaration//GEN-END:variables
}
