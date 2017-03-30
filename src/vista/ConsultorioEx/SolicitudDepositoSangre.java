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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
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
        txtMedico.setVisible(false);
        cbxMedico.setVisible(true);
        habilitarCampos(false);
        pnlMensaje.setVisible(false);
        cbxMedico.setModel(medicos());
        
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
        txtMedico.setText("");
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
        txtMedico.setEnabled(opcion);
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
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
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
            preventa1.setCod_usu(adEmerCab5.codUsuario(lblusu.getText()));
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
//    
//    public boolean modificarDatos(){
//        boolean retorna = false;
//        try {
//            ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
//            consultorio3.setId(Integer.parseInt(txtId.getText()));
//            consultorio3.setAr_id(Integer.parseInt(lblArea.getText()));
//            consultorio3.setProcedencia(Integer.parseInt(consultorio3.areaID(cbxProcedencia.getSelectedItem().toString())));
//            consultorio3.setEx_fisico(txtExamenFisico.getText());
//            consultorio3.setQx(txtQxAnteriores.getText());
//            consultorio3.setOtros(txtOtros.getText());
//            consultorio3.setSint_otros(txtOtrosSintomas.getText());
//            consultorio3.setDesc(txtRq.getText());
//            consultorio3.setSugerencia(txtSugerencia.getText());
//            consultorio3.setPreventa(Integer.parseInt(lblPreventa.getText()));
//            //
//            if(chkHta.isSelected())
//                consultorio3.setHta("X");
//            else
//                consultorio3.setHta("");
//            //
//            if(chkDm.isSelected())
//                consultorio3.setDm("X");
//            else
//                consultorio3.setDm("");
//            //
//            if(chkEnfRenal.isSelected())
//                consultorio3.setRenal("X");
//            else
//                consultorio3.setRenal("");
//            //
//            if(chkDisnea.isSelected())
//                consultorio3.setDisnea("X");
//            else
//                consultorio3.setDisnea("");
//            //
//            if(chkPalpitaciones.isSelected())
//                consultorio3.setPalpit("X");
//            else
//                consultorio3.setPalpit("");
//            //
//            if(chkTos.isSelected())
//                consultorio3.setTos("X");
//            else
//                consultorio3.setTos("");
//            //
//            if(cbxAlergia.getSelectedItem().equals("SI"))
//                consultorio3.setAlergia("X");
//            else
//                consultorio3.setAlergia("");
//            //
//                if(consultorio3.mantenimientoConsultorioExRQ(lblMant.getText())==true){
//                    pnlMensaje.setVisible(true);
//                    lblMensaje.setText("Datos guardados de forma correcta");
//                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()!=0){
//                        if(guardarDiagnostico(Integer.parseInt(txtId.getText()))&& guardarNomenclatura(Integer.parseInt(lblPreventa.getText()))){
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Datos guardados de forma correcta");
//                            limpiar();
//                            habilitarCampos(false);
//                            btnGuardar.setEnabled(false);
//                            pnlMensaje.setBackground(new Color(33,115,70));
//                            btnSi.setVisible(true);
//                            btnSi.setText("OK");
//                            btnNo.setVisible(false);
//                            txtMedico.setVisible(false);
//                            cbxMedico.setVisible(true);
//                        } else {
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Ocurrió un error, verifique");
//                            pnlMensaje.setBackground(new Color(255,91,70));
//                            btnSi.setVisible(false);
//                            btnNo.setVisible(false);
//                        }
//                    } else
//                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()!=0){
//                        if(guardarNomenclatura(Integer.parseInt(lblPreventa.getText()))){
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Datos guardados de forma correcta");
//                            limpiar();
//                            habilitarCampos(false);
//                            btnGuardar.setEnabled(false);
//                            pnlMensaje.setBackground(new Color(33,115,70));
//                            btnSi.setVisible(true);
//                            btnSi.setText("OK");
//                            btnNo.setVisible(false);
//                        } else {
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Ocurrió un error, verifique");
//                            pnlMensaje.setBackground(new Color(255,91,70));
//                            btnSi.setVisible(false);
//                            btnNo.setVisible(false);
//                        }
//                    } else
//                    if(tbDiagnostico.getRowCount()!=0 && tbCpt.getRowCount()==0){
//                        if(guardarDiagnostico(Integer.parseInt(txtId.getText()))){
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Datos guardados de forma correcta");
//                            limpiar();
//                            habilitarCampos(false);
//                            btnGuardar.setEnabled(false);
//                            pnlMensaje.setBackground(new Color(33,115,70));
//                            btnSi.setVisible(true);
//                            btnSi.setText("OK");
//                            btnNo.setVisible(false);
//                        } else {
//                            pnlMensaje.setVisible(true);
//                            lblMensaje.setText("Ocurrió un error, verifique");
//                            pnlMensaje.setBackground(new Color(255,91,70));
//                            btnSi.setVisible(false);
//                            btnNo.setVisible(false);
//                        }
//                    } else
//                    if(tbDiagnostico.getRowCount()==0 && tbCpt.getRowCount()==0){
//                        pnlMensaje.setVisible(true);
//                        lblMensaje.setText("Datos guardados de forma correcta");
//                        limpiar();
//                        habilitarCampos(false);
//                        btnGuardar.setEnabled(false);
//                        pnlMensaje.setBackground(new Color(33,115,70));
//                        btnSi.setVisible(true);
//                        btnSi.setText("OK");
//                        btnNo.setVisible(false);
//                    } 
//                } else {
//                    pnlMensaje.setVisible(true);
//                     lblMensaje.setText("error");
//                }
//        } catch (Exception e) {
//            System.out.println("Error: modificarDatos" + e.getMessage());
//        }
//        return retorna;
//    }
//    
//    public void eliminarDatos(){
//        ConsultorioExRiesgoQuirurgico consultorio3 = new ConsultorioExRiesgoQuirurgico();
//            consultorio3.setId(Integer.parseInt(txtId.getText()));
//            if(consultorio3.mantenimientoConsultorioExRQ("E")){
//                pnlMensaje.setVisible(true);
//                lblMensaje.setText("Datos guardados de forma correcta");
//                limpiar();
//                habilitarCampos(false);
//                btnGuardar.setEnabled(false);
//                pnlMensaje.setBackground(new Color(33,115,70));
//                btnSi.setVisible(true);
//                btnSi.setText("OK");
//                btnNo.setVisible(false);
//            } else{
//                pnlMensaje.setVisible(true);
//                lblMensaje.setText("Ocurrió un error, verifique");
//                pnlMensaje.setBackground(new Color(255,91,70));
//                btnSi.setVisible(false);
//                btnNo.setVisible(false);
//            }
//    }
    
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
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
        txtMedico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        javax.swing.GroupLayout FrmSolicitudDepositoLayout = new javax.swing.GroupLayout(FrmSolicitudDeposito.getContentPane());
        FrmSolicitudDeposito.getContentPane().setLayout(FrmSolicitudDepositoLayout);
        FrmSolicitudDepositoLayout.setHorizontalGroup(
            FrmSolicitudDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        FrmSolicitudDepositoLayout.setVerticalGroup(
            FrmSolicitudDepositoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
        lblusu.setText("Usuario");

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
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnEliminar)
                                        .addComponent(btnBuscar))
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
        cbxGS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "1" }));

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

        txtMedico.setEditable(false);
        txtMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
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
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(chkPlaquetas)))))
                        .addContainerGap(63, Short.MAX_VALUE))))
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
                        .addComponent(txtHematocrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtHemoglobina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            lblMant.setText("I");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        pnlMensaje.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            if(lblMant.getText().equals("I")){
                guardarDatos();
            } else 
            if(lblMant.getText().equals("U")){
//                modificarDatos();
            }
            if(lblMant.getText().equals("E")){
//                eliminarDatos();
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
        triaje1.consultorioExListar(txtBuscarPaciente.getText(), "Q", tbPacientes);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel btnPaciente;
    private javax.swing.JButton btnSi;
    public static javax.swing.JComboBox cbxGS;
    public static javax.swing.JComboBox cbxMedico;
    public static javax.swing.JCheckBox chkPaqGlobular;
    public static javax.swing.JCheckBox chkPlaquetas;
    public static javax.swing.JCheckBox chkPlasma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblApeNom;
    private javax.swing.JLabel lblArea;
    public static javax.swing.JLabel lblEdad;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbPacientes;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDonantes;
    public static javax.swing.JTextField txtHematocrito;
    public static javax.swing.JTextField txtHemoglobina;
    public static javax.swing.JTextField txtMedico;
    public static javax.swing.JTextField txtNhc;
    public static javax.swing.JTextField txtRH;
    // End of variables declaration//GEN-END:variables
}
