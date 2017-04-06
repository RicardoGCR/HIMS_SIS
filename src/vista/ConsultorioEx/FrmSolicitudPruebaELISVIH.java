/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Caja.Caja_Preventa;
import modelos.ConsultorioEx.ConsultorioExRiesgoQuirurgico;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTriaje;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class FrmSolicitudPruebaELISVIH extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    PreparedStatement pstm;
    Caja_Preventa preventa = new Caja_Preventa();
    public FrmSolicitudPruebaELISVIH() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
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
        cbxConsejero.setModel(medicos());
        if(tbElisa.getSelectedRow()==0)
            preventa.inicializarTablaElisa(tbElisa);
        else
            preventa.listarElisa(txtBuscarVIH.getText(),"T", tbElisa);
        
        habilitarCampos(false);
        limpiar();
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
        txtNC.setText("");
        lblApeNom.setText("");
        lblCodigo.setText("");
        lblEdad.setText("");
        lblFN.setText("");
        lblActoMedico.setText("");
        chkElisaVIH.setSelected(false);
        chkPruebaC.setSelected(false);
        chkPruebaR.setSelected(false);
        cbxConsejero.setSelectedIndex(0);
        lblHc.setText("");
    }
    
    public void habilitarCampos(boolean opcion){
        txtNC.setEnabled(opcion);
        lblApeNom.setEnabled(opcion);
        lblCodigo.setEnabled(opcion);
        lblEdad.setEnabled(opcion);
        lblFN.setEnabled(opcion);
        lblActoMedico.setEnabled(opcion);
        chkElisaVIH.setEnabled(opcion);
        chkPruebaC.setEnabled(opcion);;
        chkPruebaR.setEnabled(opcion);
        cbxConsejero.setEnabled(opcion);
        lblHc.setEnabled(opcion);
    }
    
    public void enviarDatosPaciente(){
        int fila = tbPacientes.getSelectedRow();
        FrmSolicitudPruebaELISVIH.lblActoMedico.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
        FrmSolicitudPruebaELISVIH.txtNC.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
        FrmSolicitudPruebaELISVIH.lblApeNom.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
        FrmSolicitudPruebaELISVIH.lblEdad.setText(String.valueOf(tbPacientes.getValueAt(fila, 5)));
        FrmSolicitudPruebaELISVIH.lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 13)));
        FrmSolicitudPruebaELISVIH.lblCodigo.setText(String.valueOf(tbPacientes.getValueAt(fila, 14)));
        FrmPacientes.dispose();
    }
    
    public DefaultComboBoxModel medicos(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC CONSULTORIO_EXT_MEDICO_TURNO_LISTAR "+ Integer.parseInt(lblArea.getText()) +""); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "MEDICO" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error: medicos " + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        try {
            Caja_Preventa preventa1 = new Caja_Preventa();
            ConsultorioExRiesgoQuirurgico consultorio1  = new ConsultorioExRiesgoQuirurgico();
            AdmisionEmergenciaCabecera adEmerCab1 = new AdmisionEmergenciaCabecera();
            preventa1.setId_hc(lblHc.getText());
            preventa1.setModulo("CEX");
            preventa1.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));
            preventa1.setElisa_consejero(consultorio1.medicoID(cbxConsejero.getSelectedItem().toString()));
            preventa1.setCod_usu(adEmerCab1.codUsuario(lblusu.getText()));
            if(chkElisaVIH.isSelected()==true)
                preventa1.setElisa_vih("X");
            else
                preventa1.setPaq_globular("");
            if(chkPruebaC.isSelected()==true)
                preventa1.setElisa_prueba_config("X");
            else
                preventa1.setPlaquetas("");
            if(chkPruebaR.isSelected()==true)
                preventa1.setElisa_prueba_ra("X");
            else
                preventa1.setPlasma("");
            preventa1.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));
            if(preventa1.mantanimientoCajaPreventaPruebaElisa("I")==true){
                System.out.println("ID Preventa: " + Integer.parseInt(consultorio1.preventaID()));
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                preventa.listarElisa(txtBuscarVIH.getText(),"T", tbElisa);
                chkHoy.setSelected(false);
                limpiar();
                habilitarCampos(false);
                btnGuardar.setEnabled(false);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public void eliminarDatos(){
        int fila = tbElisa.getSelectedRow();
        Caja_Preventa cajaE = new Caja_Preventa();
        cajaE.setId_preventa(Integer.parseInt(String.valueOf(tbElisa.getValueAt(fila, 0))));
        if(cajaE.mantanimientoCajaPreventaPruebaElisa("E")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Datos eliminados de forma correcta");
            chkHoy.setSelected(false);
            pnlMensaje.setBackground(new Color(33,115,70));
            btnSi.setVisible(true);
            Caja_Preventa c1 = new Caja_Preventa();
            c1.listarElisa(txtBuscarVIH.getText(), "T", tbElisa);
            btnSi.setText("OK");
            btnNo.setVisible(false);
        } else{
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Ocurrió un error, verifique");
            pnlMensaje.setBackground(new Color(255,91,70));
            btnSi.setVisible(false);
            btnNo.setVisible(false);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmPacientes = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        titulo8 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable();
        txtBuscarPaciente = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        txtNC = new javax.swing.JTextField();
        T5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblApeNom = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblFN = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        chkElisaVIH = new javax.swing.JCheckBox();
        chkPruebaR = new javax.swing.JCheckBox();
        chkPruebaC = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        lblActoMedico = new javax.swing.JLabel();
        lblHc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxConsejero = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbElisa = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel31 = new javax.swing.JPanel();
            T7 = new javax.swing.JLabel();
            txtBuscarVIH = new javax.swing.JTextField();
            chkHoy = new javax.swing.JCheckBox();

            FrmPacientes.setAlwaysOnTop(true);
            FrmPacientes.setMinimumSize(new java.awt.Dimension(739, 450));

            jPanel17.setBackground(new java.awt.Color(0, 153, 102));
            jPanel17.setPreferredSize(new java.awt.Dimension(500, 65));
            jPanel17.setLayout(null);

            titulo8.setBackground(new java.awt.Color(153, 0, 51));
            titulo8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            titulo8.setForeground(new java.awt.Color(255, 255, 255));
            titulo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            titulo8.setText("Pacientes");
            titulo8.setToolTipText("");
            titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jPanel17.add(titulo8);
            titulo8.setBounds(10, 10, 180, 41);

            jScrollPane25.setBorder(null);
            jScrollPane25.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            jScrollPane25.setToolTipText("");
            jScrollPane25.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane25.setOpaque(false);

            tbPacientes = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false;
                }
            };
            tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbPacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbPacientes.setSelectionBackground(new java.awt.Color(0, 153, 102));
            tbPacientes.getTableHeader().setReorderingAllowed(false);
            tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbPacientesMouseClicked(evt);
                }
            });
            tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbPacientesKeyPressed(evt);
                }
            });
            jScrollPane25.setViewportView(tbPacientes);

            jPanel17.add(jScrollPane25);
            jScrollPane25.setBounds(0, 110, 735, 313);

            txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarPacienteCaretUpdate(evt);
                }
            });
            txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarPacienteKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtBuscarPacienteKeyReleased(evt);
                }
            });
            jPanel17.add(txtBuscarPaciente);
            txtBuscarPaciente.setBounds(10, 60, 230, 30);

            btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
            btnBuscarPaciente.setBorderPainted(false);
            btnBuscarPaciente.setContentAreaFilled(false);
            btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jPanel17.add(btnBuscarPaciente);
            btnBuscarPaciente.setBounds(240, 60, 30, 30);

            jLabel37.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
            jLabel37.setForeground(new java.awt.Color(255, 255, 255));
            jLabel37.setText("Acto Médico/Nº H.C. / DNI / Apellidos y Nombres");
            jPanel17.add(jLabel37);
            jLabel37.setBounds(10, 90, 230, 14);

            javax.swing.GroupLayout FrmPacientesLayout = new javax.swing.GroupLayout(FrmPacientes.getContentPane());
            FrmPacientes.getContentPane().setLayout(FrmPacientesLayout);
            FrmPacientesLayout.setHorizontalGroup(
                FrmPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)
            );
            FrmPacientesLayout.setVerticalGroup(
                FrmPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(0, 153, 102));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html>Solicitud de prueba de ELISA o prueba rápida para VIH  <span style=\"font-size:'15px'\"><br>(Para uso de laboratorio local)</br></span></html>");

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

            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
            btnGuardar.setMnemonic('N');
            btnGuardar.setBorderPainted(false);
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

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblusu.setText("Silvana");

            lblArea.setText("60");

            lblMant.setText("jLabel6");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(433, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMant)
                            .addGap(121, 121, 121)
                            .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(234, 234, 234)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblusu)
                            .addComponent(lblArea))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(lblMant)))))
                    .addGap(552, 552, 552))
            );

            pnlMensaje.setBackground(new java.awt.Color(33, 115, 70));

            lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
            lblMensaje.setText("Desea Actualizar el Registro ?");

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
                    .addContainerGap(805, Short.MAX_VALUE))
            );
            pnlMensajeLayout.setVerticalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlMensajeLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMensaje)
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jPanel29.setBackground(new java.awt.Color(204, 204, 204));

            txtNC.setEditable(false);
            txtNC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            txtNC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNC.setBorder(null);
            txtNC.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtNCCaretUpdate(evt);
                }
            });
            txtNC.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtNCMouseClicked(evt);
                }
            });
            txtNC.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtNCActionPerformed(evt);
                }
            });
            txtNC.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNCKeyPressed(evt);
                }
            });

            T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T5.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    T5MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(T5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNC))
                    .addGap(0, 0, 0))
            );

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(51, 51, 51));
            jLabel2.setText("Paciente");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(51, 51, 51));
            jLabel3.setText("Apellidos y Nombres");

            lblApeNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblApeNom.setForeground(new java.awt.Color(51, 51, 51));
            lblApeNom.setText("ABC");

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(51, 51, 51));
            jLabel5.setText("Edad");

            lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblEdad.setForeground(new java.awt.Color(51, 51, 51));
            lblEdad.setText("00");

            jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(153, 153, 153));
            jLabel7.setText("CÓDIGO");

            lblCodigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            lblCodigo.setForeground(new java.awt.Color(153, 153, 153));
            lblCodigo.setText("APAMN1N2");

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel9.setForeground(new java.awt.Color(51, 51, 51));
            jLabel9.setText("F.N.");

            lblFN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFN.setForeground(new java.awt.Color(51, 51, 51));
            lblFN.setText("FN");

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(102, 102, 102));
            jLabel13.setText("Prueba Solicitada_______________________________________________________________");

            chkElisaVIH.setBackground(new java.awt.Color(255, 255, 255));
            chkElisaVIH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkElisaVIH.setText("ELISA VIH");
            chkElisaVIH.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            chkElisaVIH.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkElisaVIHActionPerformed(evt);
                }
            });

            chkPruebaR.setBackground(new java.awt.Color(255, 255, 255));
            chkPruebaR.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkPruebaR.setText("PRUEBA RÁPIDA VIH");
            chkPruebaR.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            chkPruebaR.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkPruebaRActionPerformed(evt);
                }
            });

            chkPruebaC.setBackground(new java.awt.Color(255, 255, 255));
            chkPruebaC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            chkPruebaC.setText("PRUEBA CONFIRMATORIA VIH");
            chkPruebaC.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
            chkPruebaC.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkPruebaCActionPerformed(evt);
                }
            });

            jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(0, 153, 102));
            jLabel14.setText("Acto Medico");

            lblActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            lblActoMedico.setForeground(new java.awt.Color(0, 153, 102));
            lblActoMedico.setText("AM");

            lblHc.setText("jLabel4");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setText("Consejero");

            cbxConsejero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            cbxConsejero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(28, 28, 28)
                                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(57, 57, 57)
                                    .addComponent(lblHc, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblApeNom))
                                .addComponent(jLabel13)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(89, 89, 89)
                                    .addComponent(lblCodigo))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel14))
                                    .addGap(59, 59, 59)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFN)
                                        .addComponent(lblEdad)
                                        .addComponent(lblActoMedico)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(chkElisaVIH)
                                    .addGap(52, 52, 52)
                                    .addComponent(chkPruebaR)
                                    .addGap(38, 38, 38)
                                    .addComponent(chkPruebaC))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbxConsejero, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblHc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(27, 27, 27)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblApeNom))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lblCodigo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblEdad))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(lblFN))
                    .addGap(20, 20, 20)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(lblActoMedico))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel13)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkElisaVIH)
                        .addComponent(chkPruebaR)
                        .addComponent(chkPruebaC))
                    .addGap(50, 50, 50)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbxConsejero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jScrollPane3.setBorder(null);
            jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            tbElisa.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbElisa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbElisa.setGridColor(new java.awt.Color(255, 255, 255));
            tbElisa.setRowHeight(25);
            tbElisa.setSelectionBackground(new java.awt.Color(0, 153, 102));
            tbElisa.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbElisaMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbElisaMousePressed(evt);
                }
            });
            tbElisa.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbElisaKeyPressed(evt);
                }
            });
            jScrollPane3.setViewportView(tbElisa);

            jPanel31.setBackground(new java.awt.Color(204, 204, 204));

            T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    T7MouseClicked(evt);
                }
            });

            chkHoy.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            chkHoy.setText("Hoy");
            chkHoy.setContentAreaFilled(false);
            chkHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            chkHoy.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    chkHoyActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                    .addComponent(txtBuscarVIH, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(chkHoy)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                    .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(T7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addComponent(txtBuscarVIH)
                .addComponent(chkHoy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(63, Short.MAX_VALUE))
                        .addComponent(jScrollPane3)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtNC.requestFocus();
        FrmPacientes.setVisible(true);
        FrmPacientes.setLocationRelativeTo(null);//en el centro
        FrmPacientes.setResizable(false);
        FrmPacientes.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListarC("", "Q", tbPacientes);
        btnGuardar.setEnabled(true);
        habilitarCampos(true);
        lblMant.setText("I");
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtNC.getText().equals("") || chkElisaVIH.isSelected()==false
                && chkPruebaC.isSelected()==false && chkPruebaR.isSelected()==false){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Verifique, que los datos estén correctos");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else {
            pnlMensaje.setVisible(true);
            lblMensaje.setText("¿Guardar los datos?");
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCCaretUpdate

    private void txtNCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNCMouseClicked
        if(evt.getClickCount()==1){
            FrmPacientes.setVisible(true);
            FrmPacientes.setLocationRelativeTo(null);//en el centro
            FrmPacientes.setResizable(false);
            FrmPacientes.getContentPane().setBackground(Color.WHITE);
            AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
            triaje1.consultorioExListarC("", "Q", tbPacientes);
        }
    }//GEN-LAST:event_txtNCMouseClicked

    private void txtNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCActionPerformed

    private void txtNCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNCKeyPressed
        FrmPacientes.setVisible(true);
        FrmPacientes.setLocationRelativeTo(null);//en el centro
        FrmPacientes.setResizable(false);
        FrmPacientes.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListarC("", "Q", tbPacientes);
    }//GEN-LAST:event_txtNCKeyPressed

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
        FrmPacientes.setVisible(true);
        FrmPacientes.setLocationRelativeTo(null);//en el centro
        FrmPacientes.setResizable(false);
        FrmPacientes.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListarC("", "Q", tbPacientes);
    }//GEN-LAST:event_T5MouseClicked

    private void chkElisaVIHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkElisaVIHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkElisaVIHActionPerformed

    private void chkPruebaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPruebaRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPruebaRActionPerformed

    private void chkPruebaCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPruebaCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPruebaCActionPerformed

    private void tbElisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbElisaMouseClicked
        if(evt.getClickCount()==1){
            limpiar();
            habilitarCampos(false);
        }
    }//GEN-LAST:event_tbElisaMouseClicked

    private void tbElisaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbElisaMousePressed

    }//GEN-LAST:event_tbElisaMousePressed

    private void tbElisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbElisaKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_DELETE){
            if(tbElisa.getSelectedRowCount()!=0){
                int fila = tbElisa.getSelectedRow();
                if(String.valueOf(tbElisa.getValueAt(fila, 5)).equals("CANCELADO")){
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("No se puede eliminar un registro CANCELADO");
                    pnlMensaje.setBackground(new Color(255,91,70));
                    btnSi.setVisible(true);
                    btnNo.setVisible(false);
                    btnSi.setText("OK");
                } else {
                    pnlMensaje.setVisible(true);
                    lblMensaje.setText("Desea ELIMINAR el registro?");
                    btnSi.setVisible(true);
                    btnNo.setVisible(true);
                    btnSi.setText("Si");
                    pnlMensaje.setBackground(new Color(255,153,51));
                    lblMant.setText("E");
                }
            } else {
                pnlMensaje.setVisible(true);
                pnlMensaje.setBackground(new Color(255,91,70));
                lblMensaje.setText("Seleccione un registro");
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbElisaKeyPressed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ 
            if(lblMant.getText().equals("I"))
                guardarDatos();
            else
                eliminarDatos();
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
            Caja_Preventa c1 = new Caja_Preventa();
            c1.listarElisa(txtBuscarVIH.getText(), "T", tbElisa);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        
    }//GEN-LAST:event_T7MouseClicked

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientes.getSelectedRow()==0){
            txtBuscarPaciente.requestFocus();
            tbPacientes.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListar(txtBuscarPaciente.getText(), "Q", tbPacientes);
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbPacientes.getSelectionModel().setSelectionInterval(0, 0);
            tbPacientes.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void txtBuscarPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPacienteKeyReleased

    private void chkHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHoyActionPerformed
        if(chkHoy.isSelected())
            preventa.listarElisa(txtBuscarVIH.getText(),"H", tbElisa);
        else
            preventa.listarElisa(txtBuscarVIH.getText(),"T", tbElisa);
    }//GEN-LAST:event_chkHoyActionPerformed

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
            java.util.logging.Logger.getLogger(FrmSolicitudPruebaELISVIH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSolicitudPruebaELISVIH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSolicitudPruebaELISVIH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSolicitudPruebaELISVIH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSolicitudPruebaELISVIH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FrmPacientes;
    private javax.swing.JLabel T5;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JComboBox cbxConsejero;
    private javax.swing.JCheckBox chkElisaVIH;
    private javax.swing.JCheckBox chkHoy;
    private javax.swing.JCheckBox chkPruebaC;
    private javax.swing.JCheckBox chkPruebaR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblApeNom;
    private javax.swing.JLabel lblArea;
    public static javax.swing.JLabel lblCodigo;
    public static javax.swing.JLabel lblEdad;
    public static javax.swing.JLabel lblFN;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbElisa;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBuscarVIH;
    public static javax.swing.JTextField txtNC;
    // End of variables declaration//GEN-END:variables
}
