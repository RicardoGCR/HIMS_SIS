/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import campos.LimitadorDeDocumento;
import java.awt.Color;
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
 * @author PC02
 */
public class SolicitudDepositoSangre extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    PreparedStatement pstm;
    AdmisionEmergenciaCabecera adEmerCab7 = new AdmisionEmergenciaCabecera();
    public SolicitudDepositoSangre() {
        initComponents();
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
        cbxMedico.setVisible(true);
        habilitarCampos(false);
        pnlMensaje.setVisible(false);
        cbxMedico.setModel(medicos());//Validar cajas de texto
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(5);
        txtCantidad.setDocument(limitObservacion);
        txtDonantes.setDocument(limitObservacion);
        LimitadorDeDocumento limite = new LimitadorDeDocumento(10);
        txtRH.setDocument(limite);
        txtHematocrito.setDocument(limite);
        txtHemoglobina.setDocument(limite);
        
        
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
        txtNhc.setText("");
        lblActoMedico.setText("");
        lblApeNom.setText("");
        lblEdad.setText("");
        txtCantidad.setText("");
        txtDonantes.setText("");
        txtHematocrito.setText("");
        cbxGS.setSelectedIndex(0);
        txtRH.setText("");
        txtHemoglobina.setText("");
        cbxMedico.setSelectedIndex(0);
        chkPaqGlobular.setSelected(false);
        chkPlaquetas.setSelected(false);
        chkPlasma.setSelected(false);
    }
    
    public void habilitarCampos(boolean opcion){
        txtNhc.setEnabled(opcion);
        lblActoMedico.setEnabled(opcion);
        lblApeNom.setEnabled(opcion);
        lblEdad.setEnabled(opcion);
        txtCantidad.setEnabled(opcion);
        txtDonantes.setEnabled(opcion);
        txtHematocrito.setEnabled(opcion);
        cbxGS.setEnabled(opcion);
        txtRH.setEnabled(opcion);
        txtHemoglobina.setEnabled(opcion);
        cbxMedico.setEnabled(opcion);
        chkPaqGlobular.setEnabled(opcion);
        chkPlaquetas.setEnabled(opcion);
        chkPlasma.setEnabled(opcion);
        btnPaciente.setEnabled(opcion);
    }
    
    public void enviarDatosPaciente(){
        int fila = tbPacientes.getSelectedRow();
        SolicitudDepositoSangre.lblActoMedico.setText(String.valueOf(tbPacientes.getValueAt(fila, 1)));
        SolicitudDepositoSangre.txtNhc.setText(String.valueOf(tbPacientes.getValueAt(fila, 3)));
        SolicitudDepositoSangre.lblApeNom.setText(String.valueOf(tbPacientes.getValueAt(fila, 4)));
        SolicitudDepositoSangre.lblEdad.setText(String.valueOf(tbPacientes.getValueAt(fila, 5)));
        SolicitudDepositoSangre.lblHc.setText(String.valueOf(tbPacientes.getValueAt(fila, 13)));
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
            preventa1.setId_hc(lblHc.getText());
            preventa1.setAR_ID(Integer.parseInt(lblArea.getText()));
            preventa1.setCantidad(Integer.parseInt(txtCantidad.getText()));
            preventa1.setDonantes(Integer.parseInt(txtDonantes.getText()));
            preventa1.setHematocrito(txtHematocrito.getText());
            preventa1.setGrupo_sang(cbxGS.getSelectedItem().toString());
            preventa1.setRh(txtRH.getText());
            preventa1.setHemoglobina(txtHemoglobina.getText());
            preventa1.setCod_usu(adEmerCab7.codUsuario(lblusu.getText()));
            preventa1.setCod_medico(consultorio1.medicoID(cbxMedico.getSelectedItem().toString()));
            if(chkPaqGlobular.isSelected()==true)
                preventa1.setPaq_globular("X");
            else
                preventa1.setPaq_globular("");
            if(chkPlaquetas.isSelected()==true)
                preventa1.setPlaquetas("X");
            else
                preventa1.setPlaquetas("");
            if(chkPlasma.isSelected()==true)
                preventa1.setPlasma("X");
            else
                preventa1.setPlasma("");
            preventa1.setACTO_MEDICO(Integer.parseInt(lblActoMedico.getText()));
            if(preventa1.mantanimientoCajaPreventaCExDepSangre(lblMant.getText())==true){
                System.out.println("ID Preventa: " + Integer.parseInt(consultorio1.preventaID()));
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
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
        int fila = tbPendientes.getSelectedRow();
        Caja_Preventa cajaE = new Caja_Preventa();
        cajaE.setId_preventa(Integer.parseInt(String.valueOf(tbPendientes.getValueAt(fila, 0))));
        if(cajaE.mantanimientoCajaPreventaCExDepSangre("E")){
            pnlMensaje1.setVisible(true);
            lblMensaje1.setText("Datos eliminados de forma correcta");
            pnlMensaje1.setBackground(new Color(33,115,70));
            btnSi1.setVisible(true);
            btnSi1.setText("OK");
            btnNo1.setVisible(false);
        } else{
            pnlMensaje1.setVisible(true);
            lblMensaje1.setText("Ocurrió un error, verifique");
            pnlMensaje1.setBackground(new Color(255,91,70));
            btnSi1.setVisible(false);
            btnNo1.setVisible(false);
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
        FrmSolicitudDeposito = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnBuscarDep = new javax.swing.JButton();
        lblusu1 = new javax.swing.JLabel();
        lblMant1 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblArea1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        chkHoy = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCancelados = new javax.swing.JTable();
        pnlMensaje1 = new javax.swing.JPanel();
        lblMensaje1 = new javax.swing.JLabel();
        btnSi1 = new javax.swing.JButton();
        btnNo1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPendientes = new javax.swing.JTable();
        b5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        lblHc = new javax.swing.JLabel();
        lblArea = new javax.swing.JLabel();
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        txtNhc = new javax.swing.JTextField();
        btnPaciente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblActoMedico = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblApeNom = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDonantes = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHematocrito = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtRH = new javax.swing.JTextField();
        txtHemoglobina = new javax.swing.JTextField();
        cbxGS = new javax.swing.JComboBox();
        chkPaqGlobular = new javax.swing.JCheckBox();
        chkPlasma = new javax.swing.JCheckBox();
        chkPlaquetas = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        cbxMedico = new javax.swing.JComboBox();

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

        FrmSolicitudDeposito.setAlwaysOnTop(true);
        FrmSolicitudDeposito.setMinimumSize(new java.awt.Dimension(845, 655));

        jPanel3.setBackground(new java.awt.Color(0, 153, 102));
        jPanel3.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Solicitud de Deposito de Sangre y/o Hemocomponentes");

        btnBuscarDep.setForeground(new java.awt.Color(240, 240, 240));
        btnBuscarDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
        btnBuscarDep.setMnemonic('N');
        btnBuscarDep.setContentAreaFilled(false);
        btnBuscarDep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarDep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscarDep.setIconTextGap(30);
        btnBuscarDep.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDepActionPerformed(evt);
            }
        });

        lblusu1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblusu1.setForeground(new java.awt.Color(255, 255, 255));
        lblusu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblusu1.setText("Silvana");

        lblMant1.setText("Mant");

        lblID.setText("id");

        lblArea1.setText("60");

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCaretUpdate(evt);
            }
        });

        chkHoy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkHoy.setForeground(new java.awt.Color(255, 255, 255));
        chkHoy.setText("Hoy");
        chkHoy.setContentAreaFilled(false);
        chkHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHoyActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Acto Médico/Nº H.C./DNI o Apellidos y Nombres del paciente");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblMant1)
                        .addGap(45, 45, 45)
                        .addComponent(lblArea1)
                        .addGap(60, 60, 60)
                        .addComponent(lblusu1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnBuscarDep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkHoy))
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblID))
                            .addComponent(jLabel14))
                        .addContainerGap(215, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblusu1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblID)
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMant1)
                                    .addComponent(lblArea1)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarDep)
                                    .addComponent(chkHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)))))
                .addGap(552, 552, 552))
        );

        jScrollPane2.setBorder(null);

        tbCancelados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCancelados.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCancelados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCancelados.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jScrollPane2.setViewportView(tbCancelados);

        pnlMensaje1.setBackground(new java.awt.Color(255, 153, 51));

        lblMensaje1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMensaje1.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje1.setText("Mensaje");

        btnSi1.setForeground(new java.awt.Color(240, 240, 240));
        btnSi1.setText("Si");
        btnSi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSi1.setContentAreaFilled(false);
        btnSi1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSi1.setIconTextGap(30);
        btnSi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSi1ActionPerformed(evt);
            }
        });

        btnNo1.setForeground(new java.awt.Color(240, 240, 240));
        btnNo1.setText("No");
        btnNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnNo1.setContentAreaFilled(false);
        btnNo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNo1.setIconTextGap(30);
        btnNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMensaje1Layout = new javax.swing.GroupLayout(pnlMensaje1);
        pnlMensaje1.setLayout(pnlMensaje1Layout);
        pnlMensaje1Layout.setHorizontalGroup(
            pnlMensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensaje1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblMensaje1)
                .addGap(46, 46, 46)
                .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMensaje1Layout.setVerticalGroup(
            pnlMensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensaje1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensaje1)
                    .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(null);

        tbPendientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbPendientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPendientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPendientes.setSelectionBackground(new java.awt.Color(0, 153, 102));
        tbPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPendientesKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbPendientes);

        b5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        b5.setForeground(new java.awt.Color(0, 153, 102));
        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar archivo-40.png"))); // NOI18N
        b5.setMnemonic('N');
        b5.setText("Quitar");
        b5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        b5.setContentAreaFilled(false);
        b5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b5.setIconTextGap(20);
        b5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        b5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FrmSolicitudDepositoLayout = new javax.swing.GroupLayout(FrmSolicitudDeposito.getContentPane());
        FrmSolicitudDeposito.getContentPane().setLayout(FrmSolicitudDepositoLayout);
        FrmSolicitudDepositoLayout.setHorizontalGroup(
            FrmSolicitudDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FrmSolicitudDepositoLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        FrmSolicitudDepositoLayout.setVerticalGroup(
            FrmSolicitudDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrmSolicitudDepositoLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(FrmSolicitudDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));
        jPanel1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Solicitud de Deposito de Sangre y/o Hemocomponentes");

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

        btnBuscar.setForeground(new java.awt.Color(240, 240, 240));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
        btnBuscar.setMnemonic('N');
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setIconTextGap(30);
        btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblusu.setText("Silvana");

        lblMant.setText("Mant");

        lblHc.setText("Hc");

        lblArea.setText("60");

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
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHc)
                        .addGap(57, 57, 57)
                        .addComponent(lblMant)
                        .addGap(45, 45, 45)
                        .addComponent(lblArea)
                        .addGap(60, 60, 60)
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 367, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblusu)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMant)
                                    .addComponent(lblHc)
                                    .addComponent(lblArea))))))
                .addGap(552, 552, 552))
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(204, 204, 204));

        txtNhc.setEditable(false);
        txtNhc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNhc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNhc.setBorder(null);
        txtNhc.setEnabled(false);
        txtNhc.setSelectionColor(new java.awt.Color(0, 153, 102));
        txtNhc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNhcCaretUpdate(evt);
            }
        });
        txtNhc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNhcMouseClicked(evt);
            }
        });
        txtNhc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhcActionPerformed(evt);
            }
        });
        txtNhc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNhcKeyPressed(evt);
            }
        });

        btnPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPaciente.setEnabled(false);
        btnPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPacienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtNhc, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhc))
                .addGap(0, 0, 0))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Paciente");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos y Nombres");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Edad");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 102));
        jLabel5.setText("Acto Médico");

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(0, 153, 102));
        lblActoMedico.setText("Acto");

        lblEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEdad.setText("jLabel7");

        lblApeNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblApeNom.setText("jLabel8");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad");

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Donantes");

        txtDonantes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Hematocrito");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Donar_____________________________________________________________________________________________________________________");

        txtHematocrito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Grupo Sanguíneo");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("RH");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Hemoglobina");

        txtRH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHemoglobina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbxGS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxGS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "A", "B", "O", "AB" }));

        chkPaqGlobular.setBackground(new java.awt.Color(255, 255, 255));
        chkPaqGlobular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkPaqGlobular.setText("Paquete Globular");

        chkPlasma.setBackground(new java.awt.Color(255, 255, 255));
        chkPlasma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkPlasma.setText("Plasma");

        chkPlaquetas.setBackground(new java.awt.Color(255, 255, 255));
        chkPlaquetas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkPlaquetas.setText("Plaquetas");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Médico");

        cbxMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblApeNom, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEdad))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantidad)
                            .addComponent(txtDonantes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHematocrito)
                            .addComponent(txtHemoglobina, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRH)
                            .addComponent(cbxGS, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPaqGlobular)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(chkPlasma)
                                .addGap(27, 27, 27)
                                .addComponent(chkPlaquetas)))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lblActoMedico)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblEdad)
                    .addComponent(lblApeNom))
                .addGap(7, 7, 7)
                .addComponent(jLabel6)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtHematocrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHemoglobina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDonantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxGS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(chkPaqGlobular, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkPlasma)
                            .addComponent(chkPlaquetas))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            lblMant.setText("I");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(txtNhc.getText().equals("") || txtCantidad.getText().equals("") || txtDonantes.getText().equals("")
               || txtHematocrito.getText().equals("") || cbxMedico.getSelectedIndex()==0 || cbxGS.getSelectedIndex()==0
               || txtRH.getText().equals("") || txtHemoglobina.getText().equals("") || chkPaqGlobular.isSelected()==false
                    && chkPlaquetas.isSelected()==false && chkPlasma.isSelected()==false){
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
        } else 
        if(lblMant.getText().equals("U")){
            if(txtNhc.getText().equals("") || txtCantidad.getText().equals("") || txtDonantes.getText().equals("")
               || txtHematocrito.getText().equals("") || cbxMedico.getSelectedIndex()==0 || cbxGS.getSelectedIndex()==0
               || txtRH.getText().equals("") || txtHemoglobina.getText().equals("") || chkPaqGlobular.isSelected()==false
                    && chkPlaquetas.isSelected()==false && chkPlasma.isSelected()==false){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Verifique, que los datos estén correctos");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Modificar los datos?");
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        FrmSolicitudDeposito.setVisible(true);
        FrmSolicitudDeposito.setLocationRelativeTo(null);//en el centro
        FrmSolicitudDeposito.setResizable(false);
        FrmSolicitudDeposito.getContentPane().setBackground(Color.WHITE);
        pnlMensaje1.setVisible(false);
        Caja_Preventa cp = new Caja_Preventa();
        cp.listarDepositoSangre("", "C", "T", tbCancelados);
        cp.listarDepositoSangre("", "P", "T", tbPendientes);
//        if(tbCancelados.getSelectedRowCount()==0)
//            cp.inicializarTablaDepositoSangre(tbPendientes);
//        if(tbPendientes.getSelectedRowCount()==0)
//            cp.inicializarTablaDepositoSangre(tbPendientes);
//        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
//        triaje1.consultorioExListar("", "Q", tbPacientes);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            if(lblMant.getText().equals("I")){
                if(guardarDatos()==true)
                    JOptionPane.showMessageDialog(this, "ok");
            }
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed

    }//GEN-LAST:event_btnNoActionPerformed

    private void btnPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPacienteMouseClicked
        FrmPacientes.setVisible(true);
        FrmPacientes.setLocationRelativeTo(null);//en el centro
        FrmPacientes.setResizable(false);
        FrmPacientes.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTriaje triaje1 = new AdmisionEmergenciaTriaje();
        triaje1.consultorioExListar("", "Q", tbPacientes);
    }//GEN-LAST:event_btnPacienteMouseClicked

    private void txtNhcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNhcKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcKeyPressed

    private void txtNhcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcActionPerformed

    private void txtNhcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNhcMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcMouseClicked

    private void txtNhcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNhcCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNhcCaretUpdate

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

    private void btnBuscarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarDepActionPerformed

    private void btnSi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSi1ActionPerformed
        if(btnSi1.getText().equals("Si"))
            eliminarDatos();
        else
            pnlMensaje1.setVisible(false);
    }//GEN-LAST:event_btnSi1ActionPerformed

    private void btnNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNo1ActionPerformed
        pnlMensaje1.setVisible(false);
    }//GEN-LAST:event_btnNo1ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        if(tbPendientes.getSelectedRowCount()!=0){
                pnlMensaje1.setVisible(true);
                lblMensaje1.setText("Desea ELIMINAR el registro?");
                btnSi1.setVisible(true);
                btnNo1.setVisible(true);
        } else {
            pnlMensaje1.setVisible(true);
            pnlMensaje1.setBackground(new Color(255,91,70));
            lblMensaje1.setText("Seleccione un registro");
            btnSi1.setVisible(true);
            btnSi1.setText("OK");
            btnNo1.setVisible(false);
        }
    }//GEN-LAST:event_b5ActionPerformed

    private void chkHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHoyActionPerformed
        Caja_Preventa cp = new Caja_Preventa();
        if(chkHoy.isSelected()){
            cp.listarDepositoSangre("", "C", "H", tbCancelados);
            cp.listarDepositoSangre("", "P", "H", tbPendientes);
        } else
        if(chkHoy.isSelected()==false){
            cp.listarDepositoSangre("", "C", "T", tbCancelados);
            cp.listarDepositoSangre("", "P", "T", tbPendientes);
        }
    }//GEN-LAST:event_chkHoyActionPerformed

    private void tbPendientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPendientesKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_DELETE){
            if(tbPendientes.getSelectedRowCount()!=0){
                pnlMensaje1.setVisible(true);
                lblMensaje1.setText("Desea ELIMINAR el registro?");
                btnSi1.setVisible(true);
                btnNo1.setVisible(true);
            } else {
                pnlMensaje1.setVisible(true);
                pnlMensaje1.setBackground(new Color(255,91,70));
                lblMensaje1.setText("Seleccione un registro");
                btnSi1.setVisible(true);
                btnSi1.setText("OK");
                btnNo1.setVisible(false);
            }
        }
    }//GEN-LAST:event_tbPendientesKeyPressed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        Caja_Preventa cp = new Caja_Preventa();
        cp.listarDepositoSangre(txtBuscar.getText(), "C", "T", tbCancelados);
        cp.listarDepositoSangre(txtBuscar.getText(), "P", "T", tbPendientes);
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
            java.util.logging.Logger.getLogger(SolicitudDepositoSangre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SolicitudDepositoSangre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SolicitudDepositoSangre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SolicitudDepositoSangre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SolicitudDepositoSangre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FrmPacientes;
    private javax.swing.JDialog FrmSolicitudDeposito;
    private javax.swing.JButton b5;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarDep;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNo1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel btnPaciente;
    private javax.swing.JButton btnSi;
    private javax.swing.JButton btnSi1;
    public static javax.swing.JComboBox cbxGS;
    public static javax.swing.JComboBox cbxMedico;
    private javax.swing.JCheckBox chkHoy;
    public static javax.swing.JCheckBox chkPaqGlobular;
    public static javax.swing.JCheckBox chkPlaquetas;
    public static javax.swing.JCheckBox chkPlasma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblApeNom;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblArea1;
    public static javax.swing.JLabel lblEdad;
    public static javax.swing.JLabel lblHc;
    public static javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMant1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMensaje1;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel lblusu1;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JPanel pnlMensaje1;
    private javax.swing.JTable tbCancelados;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JTable tbPendientes;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDonantes;
    public static javax.swing.JTextField txtHematocrito;
    public static javax.swing.JTextField txtHemoglobina;
    public static javax.swing.JTextField txtNhc;
    public static javax.swing.JTextField txtRH;
    // End of variables declaration//GEN-END:variables
}
