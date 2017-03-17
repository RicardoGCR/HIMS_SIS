/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import campos.LimitadorDeDocumento;
import java.awt.Color;
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
import modelos.hospitalizacion.HospitalizacionExamenClinico;
import modelos.hospitalizacion.HospitalizacionFormatoInterconsulta;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import static vista.PrincipalMDI.lblUsu;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionFormatoInterconsulta extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    ResultSet r;
    public FrmHospitalizacionFormatoInterconsulta() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);//deshabilita boton maximizar
        this.getContentPane().setBackground(Color.WHITE);//color blanco del formulario
        conexion = c.conectar();
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
        cbxServicio.setBackground(Color.white);
        cbxArea.setBackground(Color.white);
        cbxServicio.setModel(servicios());
        txtCodigoMedico.setVisible(false);
        HospitalizacionFormatoInterconsulta hosFor = new HospitalizacionFormatoInterconsulta();
        hosFor.inicializarTablaDiag(tbDiagnosticoPresun);
        LimitadorDeDocumento limitResumen = new LimitadorDeDocumento(1000);
        txtResumenHC.setDocument(limitResumen);
        LimitadorDeDocumento limitMotivo = new LimitadorDeDocumento(900);
        txtMotivoInterconsulta.setDocument(limitMotivo);
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
        txtActoMedico.setText("");
        txtCodigoMedico.setText("");
        txtMedico.setText("");
        txtDNI.setText("");
        txtNHC.setText("");
        txtNroCama.setText("");
        txtPaciente.setText("");
        cbxArea.setSelectedIndex(0);
        cbxServicio.setSelectedIndex(0);
        txtID.setText("");
        txtIdHe.setText("");
        txtIdPreventa.setText("");
        txtResumenHC.setText("");
        txtMotivoInterconsulta.setText("");
    }
    
    public void habilitarDatos(boolean opcion){
        txtActoMedico.setEnabled(opcion);
        txtCodigoMedico.setEnabled(opcion);
        txtMedico.setEnabled(opcion);
        txtDNI.setEnabled(opcion);
        txtNHC.setEnabled(opcion);
        txtNroCama.setEnabled(opcion);
        txtPaciente.setEnabled(opcion);
        cbxArea.setEnabled(opcion);
        cbxServicio.setEnabled(opcion);
        txtResumenHC.setEnabled(opcion);
        txtMotivoInterconsulta.setEnabled(opcion);
        btnBuscarMedico.setEnabled(opcion);
    }

    public DefaultComboBoxModel servicios(){
           DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
           String   sql = null;
           ResultSet rs = null;
           Statement  st = null;   
            try {
                  st = conexion.createStatement();
                  r = st.executeQuery ("EXEC HOSPITALIZACION_LISTAR_SERVICIO "+01+""); 
                  listmodel.addElement("Seleccione el servicio...");
                while( r.next() ){
                    listmodel.addElement( r.getString( "SE_DESC" ) );                
                 }
                r.close();
                
            } catch (SQLException ex) {            
                System.err.println( "FrmHospitalizacionFormatoInterconsulta Error: servicios: EXEC HOSPITALIZACION_LISTAR_SERVICIO :" + ex.getMessage() );
            }        
        return listmodel;
    }
    
    public void enviarDatosExClinico(){
        int fila = tbExClinico.getSelectedRow();
        FrmHospitalizacionFormatoInterconsulta.txtIdHe.setText(String.valueOf(tbExClinico.getValueAt(fila, 0)));
        FrmHospitalizacionFormatoInterconsulta.txtIdPreventa.setText(String.valueOf(tbExClinico.getValueAt(fila, 1)));
        FrmHospitalizacionFormatoInterconsulta.txtActoMedico.setText(String.valueOf(tbExClinico.getValueAt(fila, 2)));
        FrmHospitalizacionFormatoInterconsulta.txtDNI.setText(String.valueOf(tbExClinico.getValueAt(fila, 3)));
        FrmHospitalizacionFormatoInterconsulta.txtNHC.setText(String.valueOf(tbExClinico.getValueAt(fila, 4)));
        FrmHospitalizacionFormatoInterconsulta.txtPaciente.setText(String.valueOf(tbExClinico.getValueAt(fila, 5)));
        FrmHospitalizacionFormatoInterconsulta.txtNroCama.setText(String.valueOf(tbExClinico.getValueAt(fila, 7)));
        FrmHospitalizacionFormatoInterconsulta.txtMedico.setText(String.valueOf(tbExClinico.getValueAt(fila, 8)));
        FrmHospitalizacionFormatoInterconsulta.txtCodigoMedico.setText(String.valueOf(tbExClinico.getValueAt(fila, 9)));
        FrmListarExClinico.dispose();
        btnGuardar.setEnabled(true);
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
    }
    
    public boolean guardarDatosFormatoInterconsulta(){
        boolean retorna = false;
        HospitalizacionFormatoInterconsulta forInter = new HospitalizacionFormatoInterconsulta();
        try {
            ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
            AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
            if(txtActoMedico.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Seleccine un paciente");
            } else
            if(cbxArea.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this, "Seleccione un área");
            } else {
                int id_preventa = Integer.parseInt(txtIdPreventa.getText());
                int id_hec = Integer.parseInt(txtIdHe.getText());
                int area = Integer.parseInt(forInter.codArea(cbxArea.getSelectedItem().toString()));
                String resumen = txtResumenHC.getText();
                String motivo = txtMotivoInterconsulta.getText();
                String cod_per = txtCodigoMedico.getText();
                String cod_usu = adEmerCab5.codUsuario(lblUsuUsuario.getText());
                HospitalizacionFormatoInterconsulta hosp1 = new HospitalizacionFormatoInterconsulta();
                int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                               "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0){
                    hosp1.setId_hec(id_hec);
                    hosp1.setAr_id(area);
                    hosp1.setResumen_hc(resumen);
                    hosp1.setMotivo(motivo);
                    hosp1.setCod_per(cod_per);
                    hosp1.setId_preventa(id_preventa);
                    hosp1.setUsuario(cod_usu);
                    if(hosp1.mantenimientoHospitalizacionFormInterconsulta(lblMant.getText())==true){
                        String id = hosp1.hospitalizacionInterconsultaID();
                        txtIdHe.setText(id);
                        JOptionPane.showMessageDialog(this, "Interconsulta solicitada\n Acto Médico: " + id);
                        btnGuardar.setEnabled(false);
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                        limpiar();
                        habilitarDatos(false);
                        txtIdPreventa.setText("");
                        lblMant.setText("");
                        txtID.setText("");
                    }
                }else{
                        JOptionPane.showMessageDialog(this, "No se realizó ninguna solicitud");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDatosFormatoInterconsulta" + e.getMessage());
        }
        return retorna;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmBuscarPaciente = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbPacientesHosp = new javax.swing.JTable();
        txtBuscarPaciente = new javax.swing.JTextField();
        btnBuscarPaciente = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        FrmListarExClinico = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        titulo12 = new javax.swing.JLabel();
        txtBuscarExClinico = new javax.swing.JTextField();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        txtIdHec = new javax.swing.JTextField();
        jScrollPane29 = new javax.swing.JScrollPane();
        tbExClinico = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        tbDiagP = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane31 = new javax.swing.JScrollPane();
        tbDiagS = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        tbDiagD = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        jScrollPane33 = new javax.swing.JScrollPane();
        tbDiagPR = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscarPac = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtIdPreventa = new javax.swing.JTextField();
        txtIdHe = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtActoMedico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNHC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxServicio = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbxArea = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResumenHC = new javax.swing.JEditorPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotivoInterconsulta = new javax.swing.JEditorPane();
        txtMedico = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDiagnosticoPresun = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNroCama = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnInformesInt = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnFormatosInt = new javax.swing.JButton();
        txtCodigoMedico = new javax.swing.JTextField();
        btnBuscarMedico = new javax.swing.JButton();

        FrmBuscarPaciente.setAlwaysOnTop(true);
        FrmBuscarPaciente.setMinimumSize(new java.awt.Dimension(750, 450));

        jPanel9.setBackground(new java.awt.Color(217, 176, 86));
        jPanel9.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel9.setLayout(null);

        titulo6.setBackground(new java.awt.Color(153, 0, 51));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo6.setForeground(new java.awt.Color(255, 255, 255));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo6.setText("Pacientes Hospitalizados");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.add(titulo6);
        titulo6.setBounds(10, 10, 470, 41);

        jScrollPane9.setBorder(null);
        jScrollPane9.setOpaque(false);

        tbPacientesHosp = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbPacientesHosp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbPacientesHosp.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbPacientesHosp.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbPacientesHosp.getTableHeader().setReorderingAllowed(false);
        tbPacientesHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPacientesHospMouseClicked(evt);
            }
        });
        tbPacientesHosp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPacientesHospKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(tbPacientesHosp);

        jPanel9.add(jScrollPane9);
        jScrollPane9.setBounds(0, 110, 745, 315);

        txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPacienteCaretUpdate(evt);
            }
        });
        txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyPressed(evt);
            }
        });
        jPanel9.add(txtBuscarPaciente);
        txtBuscarPaciente.setBounds(10, 60, 230, 30);

        btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarPaciente.setBorderPainted(false);
        btnBuscarPaciente.setContentAreaFilled(false);
        btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.add(btnBuscarPaciente);
        btnBuscarPaciente.setBounds(240, 60, 30, 30);

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Acto Médico / Nº H.C. / Datos del Paciente");
        jPanel9.add(jLabel16);
        jLabel16.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmBuscarPacienteLayout = new javax.swing.GroupLayout(FrmBuscarPaciente.getContentPane());
        FrmBuscarPaciente.getContentPane().setLayout(FrmBuscarPacienteLayout);
        FrmBuscarPacienteLayout.setHorizontalGroup(
            FrmBuscarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        FrmBuscarPacienteLayout.setVerticalGroup(
            FrmBuscarPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        FrmListarExClinico.setAlwaysOnTop(true);
        FrmListarExClinico.setMinimumSize(new java.awt.Dimension(1368, 700));
        FrmListarExClinico.setResizable(false);
        FrmListarExClinico.getContentPane().setLayout(null);

        jPanel15.setBackground(new java.awt.Color(217, 176, 86));
        jPanel15.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel15.setLayout(null);

        titulo12.setBackground(new java.awt.Color(153, 0, 51));
        titulo12.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo12.setForeground(new java.awt.Color(255, 255, 255));
        titulo12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo12.setText("Exámenes Clínicos");
        titulo12.setToolTipText("");
        titulo12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel15.add(titulo12);
        titulo12.setBounds(10, 10, 470, 41);

        txtBuscarExClinico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarExClinicoCaretUpdate(evt);
            }
        });
        txtBuscarExClinico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarExClinicoKeyPressed(evt);
            }
        });
        jPanel15.add(txtBuscarExClinico);
        txtBuscarExClinico.setBounds(10, 60, 230, 30);

        btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarPaciente2.setBorderPainted(false);
        btnBuscarPaciente2.setContentAreaFilled(false);
        btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel15.add(btnBuscarPaciente2);
        btnBuscarPaciente2.setBounds(240, 60, 30, 30);

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Acto Médico / Nº H.C. / DNI / Datos del Paciente");
        jPanel15.add(jLabel41);
        jLabel41.setBounds(10, 90, 300, 14);

        txtIdHec.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdHecCaretUpdate(evt);
            }
        });
        txtIdHec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdHecKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdHecKeyReleased(evt);
            }
        });
        jPanel15.add(txtIdHec);
        txtIdHec.setBounds(410, 60, 180, 30);

        FrmListarExClinico.getContentPane().add(jPanel15);
        jPanel15.setBounds(0, 0, 1380, 110);

        jScrollPane29.setBorder(null);

        tbExClinico = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbExClinico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbExClinico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbExClinico.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbExClinico.getTableHeader().setReorderingAllowed(false);
        tbExClinico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbExClinicoMouseClicked(evt);
            }
        });
        tbExClinico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbExClinicoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbExClinicoKeyReleased(evt);
            }
        });
        jScrollPane29.setViewportView(tbExClinico);

        FrmListarExClinico.getContentPane().add(jScrollPane29);
        jScrollPane29.setBounds(0, 110, 950, 560);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane30.setBorder(null);

        tbDiagP = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagP.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagP.setSelectionBackground(new java.awt.Color(221, 194, 82));
        jScrollPane30.setViewportView(tbDiagP);

        jLabel42.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel42.setText("Diagnóstico Presuntivo");

        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel43.setText("Diagnóstico Sindrómico");

        jScrollPane31.setBorder(null);

        tbDiagS = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagS.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagS.setSelectionBackground(new java.awt.Color(221, 194, 82));
        jScrollPane31.setViewportView(tbDiagS);

        jLabel44.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel44.setText("Diagnóstico Definitivo");

        jScrollPane32.setBorder(null);

        tbDiagD = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagD.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagD.setSelectionBackground(new java.awt.Color(221, 194, 82));
        jScrollPane32.setViewportView(tbDiagD);

        jLabel45.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel45.setText("Diagnóstico Principal");

        jScrollPane33.setBorder(null);

        tbDiagPR = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagPR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagPR.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagPR.setGridColor(new java.awt.Color(255, 255, 255));
        tbDiagPR.setSelectionBackground(new java.awt.Color(2, 127, 42));
        jScrollPane33.setViewportView(tbDiagPR);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addGap(264, 264, 264))
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
            .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43)
                    .addComponent(jLabel45))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        FrmListarExClinico.getContentPane().add(jPanel3);
        jPanel3.setBounds(960, 110, 390, 560);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(883, 654));

        jPanel8.setBackground(new java.awt.Color(217, 176, 86));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Formato de Interconsulta");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(10, 10, 370, 41);

        lblUsuUsuario.setFont(new java.awt.Font("Segoe UI Light", 1, 22)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(710, 20, 180, 20);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel8.add(jLabel19);
        jLabel19.setBounds(680, 20, 32, 24);

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
        btnNuevo.setBounds(10, 60, 24, 30);

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
        btnGuardar.setBounds(50, 60, 28, 30);

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
        btnModificar.setBounds(90, 60, 28, 30);

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar (Delete)");
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
        btnEliminar.setBounds(130, 60, 28, 30);

        btnBuscarPac.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnBuscarPac.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarPac.setMnemonic('B');
        btnBuscarPac.setText("Buscar Paciente");
        btnBuscarPac.setToolTipText("Buscar Paciente (Alt + B)");
        btnBuscarPac.setBorderPainted(false);
        btnBuscarPac.setContentAreaFilled(false);
        btnBuscarPac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPac.setEnabled(false);
        btnBuscarPac.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscarPac.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscarPac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPacActionPerformed(evt);
            }
        });
        jPanel8.add(btnBuscarPac);
        btnBuscarPac.setBounds(0, 100, 180, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alt + B");
        jPanel8.add(jLabel7);
        jLabel7.setBounds(170, 110, 50, 20);

        lblMant.setText("jLabel9");
        jPanel8.add(lblMant);
        lblMant.setBounds(360, 60, 34, 14);

        txtID.setEditable(false);
        txtID.setEnabled(false);
        jPanel8.add(txtID);
        txtID.setBounds(360, 100, 110, 20);

        txtIdPreventa.setEditable(false);
        txtIdPreventa.setEnabled(false);
        jPanel8.add(txtIdPreventa);
        txtIdPreventa.setBounds(480, 100, 110, 20);

        txtIdHe.setEditable(false);
        txtIdHe.setEnabled(false);
        txtIdHe.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdHeCaretUpdate(evt);
            }
        });
        jPanel8.add(txtIdHe);
        txtIdHe.setBounds(600, 100, 130, 20);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel4.setText("Acto Médico:");

        txtActoMedico.setEditable(false);
        txtActoMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtActoMedico.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel3.setText("Nº H.C.");

        txtNHC.setEditable(false);
        txtNHC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNHC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNHC.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("DNI:");

        txtDNI.setEditable(false);
        txtDNI.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("Paciente:");

        txtPaciente.setEditable(false);
        txtPaciente.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPaciente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPaciente.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel1.setText("Servicio que solicita:");

        cbxServicio.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione el servicio..." }));
        cbxServicio.setEnabled(false);
        cbxServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxServicioItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Interconsulta a:");

        cbxArea.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        cbxArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar área..." }));
        cbxArea.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel9.setText("Resumen de Historia Clínica:");

        txtResumenHC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtResumenHC.setEnabled(false);
        jScrollPane1.setViewportView(txtResumenHC);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel10.setText("Motivo de Interconsulta:");

        txtMotivoInterconsulta.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtMotivoInterconsulta.setEnabled(false);
        jScrollPane2.setViewportView(txtMotivoInterconsulta);

        txtMedico.setEditable(false);
        txtMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMedico.setEnabled(false);

        jScrollPane3.setBorder(null);

        tbDiagnosticoPresun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagnosticoPresun.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagnosticoPresun.setSelectionBackground(new java.awt.Color(135, 159, 128));
        jScrollPane3.setViewportView(tbDiagnosticoPresun);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel11.setText("Diagnóstico Presuntivo");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel8.setText("Nº de Cama:");

        txtNroCama.setEditable(false);
        txtNroCama.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNroCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNroCama.setEnabled(false);

        jPanel4.setBackground(new java.awt.Color(215, 173, 113));

        btnInformesInt.setBackground(new java.awt.Color(76, 100, 77));
        btnInformesInt.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnInformesInt.setForeground(new java.awt.Color(255, 255, 255));
        btnInformesInt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Informe-40.png"))); // NOI18N
        btnInformesInt.setText("<html>Informes de<br> Interconsultas<html>");
        btnInformesInt.setBorderPainted(false);
        btnInformesInt.setContentAreaFilled(false);
        btnInformesInt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInformesInt.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnInformesInt.setIconTextGap(10);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnInformesInt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInformesInt, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(135, 159, 128));

        btnFormatosInt.setBackground(new java.awt.Color(76, 100, 77));
        btnFormatosInt.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnFormatosInt.setForeground(new java.awt.Color(255, 255, 255));
        btnFormatosInt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Formato40.png"))); // NOI18N
        btnFormatosInt.setText("<html>Formatos de <br>Interconsultas<br><p style=\"font-size:'8px';text-align:'center'\"><b>Pendientes de pago</b></p></html>");
        btnFormatosInt.setToolTipText("");
        btnFormatosInt.setBorderPainted(false);
        btnFormatosInt.setContentAreaFilled(false);
        btnFormatosInt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFormatosInt.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnFormatosInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormatosIntActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFormatosInt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFormatosInt, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        );

        txtCodigoMedico.setEditable(false);
        txtCodigoMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtCodigoMedico.setEnabled(false);

        btnBuscarMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        btnBuscarMedico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
        btnBuscarMedico.setText("Médico de Turno");
        btnBuscarMedico.setBorderPainted(false);
        btnBuscarMedico.setContentAreaFilled(false);
        btnBuscarMedico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarMedico.setEnabled(false);
        btnBuscarMedico.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxServicio, 0, 255, Short.MAX_VALUE)
                            .addComponent(cbxArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtMedico, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnBuscarMedico)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtCodigoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarMedico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaciente))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNHC)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDNI)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPaciente)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            limpiar();
            habilitarDatos(true);
            lblMant.setText("I");
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            btnBuscarPac.setEnabled(true);
        } catch (Exception e) {
            System.out.println("Error: btnNuevo" + e.getMessage());
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            guardarDatosFormatoInterconsulta();
        } /*else {
            modificarDatos();
        }*/
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
//        btnEliminar.setEnabled(false);
//        lblMant.setText("U");
//        int fila = tbNotEnf.getSelectedRow();
//        txtIndicaciones.setText(String.valueOf(tbNotEnf.getValueAt(fila, 3)));
//        txtEvolucion.setText(String.valueOf(tbNotEnf.getValueAt(fila, 4)));
//        spHora.setEnabled(false);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarPacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacActionPerformed
        //LISTAR PACIENTE QUE YA TENGAN UN EXAMEN CLINICO
//        FrmBuscarPaciente.setVisible(true);
//        FrmBuscarPaciente.setLocationRelativeTo(null);//en el centro
//        FrmBuscarPaciente.setResizable(false);
//        FrmBuscarPaciente.getContentPane().setBackground(Color.WHITE);
//        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
//        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
//        txtBuscarPaciente.requestFocus();
        FrmListarExClinico.setVisible(true);
        FrmListarExClinico.setLocationRelativeTo(null);//en el centro
        FrmListarExClinico.setResizable(false);
        FrmListarExClinico.getContentPane().setBackground(Color.WHITE);
        txtBuscarExClinico.requestFocus();
        HospitalizacionExamenClinico hosp = new HospitalizacionExamenClinico();
        hosp.listarExClinico(txtBuscarExClinico.getText(), tbExClinico);
        txtBuscarExClinico.requestFocus();
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnBuscarPacActionPerformed

    private void btnFormatosIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormatosIntActionPerformed
        FrmHospitalizacionFormatoInterconsultaPendiente frmP =new  FrmHospitalizacionFormatoInterconsultaPendiente();
        frmP.setVisible(true);
        String u=lblUsu.getText();
        FrmHospitalizacionFormatoInterconsultaPendiente.lblUsuUsuario.setText(u);
    }//GEN-LAST:event_btnFormatosIntActionPerformed

    private void tbPacientesHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesHospMouseClicked
//        if(evt.getClickCount()==2){
//            FrmBuscarPaciente.dispose();
//            enviarDatosPac();
//        }
    }//GEN-LAST:event_tbPacientesHospMouseClicked

    private void tbPacientesHospKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesHospKeyPressed
//        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientesHosp.getSelectedRow()==0){
//            tbPacientesHosp.getSelectionModel().setSelectionInterval(0,0);
//            txtBuscarPaciente.requestFocus();
//        }
//        char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
//            FrmBuscarPaciente.dispose();
//            enviarDatosPac();
//        }
    }//GEN-LAST:event_tbPacientesHospKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed

    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void cbxServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxServicioItemStateChanged
        try{
            HospitalizacionFormatoInterconsulta hosFor = new HospitalizacionFormatoInterconsulta();
            if(this.cbxServicio.getSelectedIndex()>0){
                this.cbxArea.removeAllItems();
                Statement sta=conexion.createStatement();
                String sServicio = hosFor.codServicio(cbxServicio.getSelectedItem().toString());
                ResultSet rs=sta.executeQuery("EXEC HOSPITALIZACION_LISTAR_AREAS '"+sServicio+"'");
                this.cbxArea.addItem("Seleccionar área...");
                while(rs.next()){
                    this.cbxArea.addItem(rs.getString("AR_DESC"));
                    //  this.cbxProvincia.setModel(null);
                }
            }else{
                this.cbxArea.removeAllItems();

                this.cbxArea.addItem("Seleccionar área...");
            }

        }
        catch(Exception ex)
        {
            System.out.println("Error: EXEC HOSPITALIZACION_LISTAR_AREAS" + ex.getMessage());
        }
    }//GEN-LAST:event_cbxServicioItemStateChanged

    private void txtBuscarExClinicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarExClinicoCaretUpdate
        HospitalizacionExamenClinico hosp = new HospitalizacionExamenClinico();
        hosp.listarExClinico(txtBuscarExClinico.getText(), tbExClinico);
    }//GEN-LAST:event_txtBuscarExClinicoCaretUpdate

    private void txtBuscarExClinicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarExClinicoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbExClinico.getSelectionModel().setSelectionInterval(0,0);
            tbExClinico.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarExClinicoKeyPressed

    private void txtIdHecCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdHecCaretUpdate
        HospitalizacionExamenClinico hosDefi = new HospitalizacionExamenClinico();
        hosDefi.listarDiagnosticos(txtIdHec.getText(), "D", tbDiagD,"V");
        hosDefi.listarDiagnosticos(txtIdHec.getText(), "P", tbDiagP,"V");
        hosDefi.listarDiagnosticos(txtIdHec.getText(), "S", tbDiagS,"V");
        hosDefi.listarDiagnosticos(txtIdHec.getText(), "R", tbDiagPR,"V");

    }//GEN-LAST:event_txtIdHecCaretUpdate

    private void txtIdHecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdHecKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            int fila = tbExClinico.getSelectedRow();
            txtIdHe.setText(String.valueOf(tbExClinico.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_txtIdHecKeyPressed

    private void txtIdHecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdHecKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            int fila = tbExClinico.getSelectedRow();
            txtIdHec.setText(String.valueOf(tbExClinico.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_txtIdHecKeyReleased

    private void tbExClinicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbExClinicoMouseClicked
        if(evt.getClickCount()==1){
            int fila = tbExClinico.getSelectedRow();
            txtIdHec.setText(String.valueOf(tbExClinico.getValueAt(fila, 0)));
        }
        if(evt.getClickCount()==2){
            enviarDatosExClinico();
        }
    }//GEN-LAST:event_tbExClinicoMouseClicked

    private void tbExClinicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExClinicoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosExClinico();
        }

        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbExClinico.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarExClinico.requestFocus();
        }
    }//GEN-LAST:event_tbExClinicoKeyPressed

    private void tbExClinicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExClinicoKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbExClinico.getSelectedRow();
            txtIdHec.setText(String.valueOf(tbExClinico.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_tbExClinicoKeyReleased

    private void txtIdHeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdHeCaretUpdate
        HospitalizacionExamenClinico hosDefi = new HospitalizacionExamenClinico();
        HospitalizacionFormatoInterconsulta hosFor = new HospitalizacionFormatoInterconsulta();
        hosDefi.listarDiagnosticos(txtIdHe.getText(), "P", tbDiagnosticoPresun,"V");
        hosFor.formatoTablaDiag(tbDiagnosticoPresun);
    }//GEN-LAST:event_txtIdHeCaretUpdate

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoInterconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoInterconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoInterconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionFormatoInterconsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionFormatoInterconsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrmBuscarPaciente;
    private javax.swing.JDialog FrmListarExClinico;
    private javax.swing.JButton btnBuscarMedico;
    private javax.swing.JButton btnBuscarPac;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente2;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFormatosInt;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInformesInt;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JComboBox cbxArea;
    public static javax.swing.JComboBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JTable tbDiagD;
    private javax.swing.JTable tbDiagP;
    private javax.swing.JTable tbDiagPR;
    private javax.swing.JTable tbDiagS;
    private javax.swing.JTable tbDiagnosticoPresun;
    private javax.swing.JTable tbExClinico;
    private javax.swing.JTable tbPacientesHosp;
    private javax.swing.JLabel titulo12;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    public static javax.swing.JTextField txtActoMedico;
    private javax.swing.JTextField txtBuscarExClinico;
    private javax.swing.JTextField txtBuscarPaciente;
    public static javax.swing.JTextField txtCodigoMedico;
    public static javax.swing.JTextField txtDNI;
    public static javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtIdHe;
    private javax.swing.JTextField txtIdHec;
    public static javax.swing.JTextField txtIdPreventa;
    public static javax.swing.JTextField txtMedico;
    public static javax.swing.JEditorPane txtMotivoInterconsulta;
    public static javax.swing.JTextField txtNHC;
    public static javax.swing.JTextField txtNroCama;
    public static javax.swing.JTextField txtPaciente;
    public static javax.swing.JEditorPane txtResumenHC;
    // End of variables declaration//GEN-END:variables
}
