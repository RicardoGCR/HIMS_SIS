/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTopico;
import modelos.hospitalizacion.HospitalizacionExamenClinico;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import vista.LABORATORIO.frm_LAB_RESULTADO_MUESTRA;
import vista.inicioSesion;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionExClinico extends javax.swing.JFrame {
    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    HospitalizacionExamenClinico hospEx = new HospitalizacionExamenClinico();
    public FrmHospitalizacionExClinico() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        limpiar();
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
        hospEx.inicializarTabla(tbDiagDefinitivo);
        hospEx.inicializarTabla(tbDiagPresuntivo);
        hospEx.inicializarTabla(tbDiagPrincipal);
        hospEx.inicializarTabla(tbDiagSindromico);
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
       txtNHC.setText("");
       txtDNI.setText("");
       txtPaciente.setText("");
       txtNroCama.setText("");
       txtIdPreventa.setText("");
       txtPA.setText("");
       txtFC.setText("");
       txtTemp.setText("");
       txtFR.setText("");
       txtPeso.setText("");
       txtTalla.setText("");
       txtExamenGeneral.setText("");
       txtPiel.setText("");
       txtCabeza.setText("");
       txtOjos.setText("");
       txtDientes.setText("");
       txtTorax.setText("");
       txtMamas.setText("");
       txtPulmones.setText("");
       txtCorazon.setText("");
       txtLinfaticos.setText("");
       txtGenitales.setText("");
       txtTactoRec.setText("");
       txtExGineco.setText("");
       txtExtremidades.setText("");
       txtSistemaNeuro.setText("");
       for (int i = 0; i < tbDiagPresuntivo.getRowCount(); i++) {
           m= (DefaultTableModel) tbDiagPresuntivo.getModel(); 
           m.removeRow(i);
           i-=1;
       }
       for (int i = 0; i < tbDiagDefinitivo.getRowCount(); i++) {
           m= (DefaultTableModel) tbDiagDefinitivo.getModel(); 
           m.removeRow(i);
           i-=1;
       }
       for (int i = 0; i < tbDiagPrincipal.getRowCount(); i++) {
           m= (DefaultTableModel) tbDiagPrincipal.getModel(); 
           m.removeRow(i);
           i-=1;
       }
       for (int i = 0; i < tbDiagSindromico.getRowCount(); i++) {
           m= (DefaultTableModel) tbDiagSindromico.getModel(); 
           m.removeRow(i);
           i-=1;
       }  
    }
    
    public void habilitarCampos(boolean opcion){
       txtActoMedico.setEnabled(opcion);
       txtNHC.setEnabled(opcion);
       txtDNI.setEnabled(opcion);
       txtPaciente.setEnabled(opcion);
       txtNroCama.setEnabled(opcion);
       txtIdPreventa.setEnabled(opcion);
       txtPA.setEnabled(opcion);
       txtFC.setEnabled(opcion);
       txtTemp.setEnabled(opcion);
       txtFR.setEnabled(opcion);
       txtPeso.setEnabled(opcion);
       txtTalla.setEnabled(opcion);
       txtExamenGeneral.setEnabled(opcion);
       txtPiel.setEnabled(opcion);
       txtCabeza.setEnabled(opcion);
       txtOjos.setEnabled(opcion);
       txtDientes.setEnabled(opcion);
       txtTorax.setEnabled(opcion);
       txtMamas.setEnabled(opcion);
       txtPulmones.setEnabled(opcion);
       txtCorazon.setEnabled(opcion);
       txtLinfaticos.setEnabled(opcion);
       txtGenitales.setEnabled(opcion);
       txtTactoRec.setEnabled(opcion);
       txtExGineco.setEnabled(opcion);
       txtExtremidades.setEnabled(opcion);
       txtSistemaNeuro.setEnabled(opcion);
       btnAgregarDiagDefinitivo.setEnabled(opcion);
       btnAgregarDiagPresuntivo.setEnabled(opcion);
       btnAgregarDiagPrincipal.setEnabled(opcion);
       btnAgregarDiagSindromico.setEnabled(opcion);
       btnBuscarPac.setEnabled(true);
    }

    public void enviarDatosPac(){
        int fila = tbPacientesHosp.getSelectedRow();
        FrmHospitalizacionExClinico.txtIdPreventa.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 0)));
        FrmHospitalizacionExClinico.lblIdHC.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 1)));
        FrmHospitalizacionExClinico.txtActoMedico.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 2)));
        FrmHospitalizacionExClinico.txtNHC.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 3)));
        FrmHospitalizacionExClinico.txtDNI.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 4)));
        FrmHospitalizacionExClinico.txtPaciente.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 5)));
        FrmHospitalizacionExClinico.txtNroCama.setText(String.valueOf(tbPacientesHosp.getValueAt(fila, 7)));
    }
    
    public void formatotbDiagnosticos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(60);//clasificacion
        tabla.getColumnModel().getColumn(2).setPreferredWidth(550);//clasificacion
        tabla.setRowHeight(30);
    }
    
    public boolean repiteDiagImp(JTable tablaEnvia, JTable tablaRecibe){
        int filaselec=tablaEnvia.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tablaRecibe.getRowCount(); i++){    
            if(tablaRecibe.getValueAt(i, 0).toString().equalsIgnoreCase(tablaEnvia.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public void enviarDiagnosticos(JTable tablaEnvia, JTable tablaRecibe, JDialog dialogo){
        m = (DefaultTableModel) tablaRecibe.getModel();
        int fila = tablaEnvia.getSelectedRow();
        if(tablaRecibe.getColumnCount()==0){
            dialogo.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            //btnQuitarDiag.setEnabled(true);
        } else
        if(repiteDiagImp(tablaEnvia,tablaRecibe)==true)
            JOptionPane.showMessageDialog(dialogo, "Diagnóstico ya registrado");
        else {
            dialogo.dispose();
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            //btnQuitarDiag.setEnabled(true);
        }
    }
    
    public void quitarDiagnostico(JTable tabla){
        if(tabla.getSelectedRowCount()!=0){
            m= (DefaultTableModel) tabla.getModel(); 
            m.removeRow(tabla.getSelectedRow()); 
        } else 
            JOptionPane.showMessageDialog(this, "Seleccione un registro");
    }
    
    public boolean guardarDatosExClinico(){
        boolean retorna = false;
        int fila = tbDiagPrincipal.getSelectedRow();
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png")); 
        AdmisionEmergenciaCabecera adEmerCab5 = new AdmisionEmergenciaCabecera();
        int hec_id = Integer.parseInt(txtID.getText());
        int id_preventa = Integer.parseInt(txtIdPreventa.getText());
        String ex_gen = txtExamenGeneral.getText();
        String piel = txtPiel.getText();
        String cabeza = txtCabeza.getText();
        String ojo = txtOjos.getText();
        String dientes = txtDientes.getText();
        String torax = txtTorax.getText();
        String mam = txtMamas.getText();
        String pulmon = txtPulmones.getText();
        String cora = txtCorazon.getText();
        String linf = txtLinfaticos.getText();
        String genit = txtGenitales.getText();
        String tacto = txtTactoRec.getText();
        String ex_gine = txtExGineco.getText();
        String extrem = txtExtremidades.getText();
        String sist = txtSistemaNeuro.getText();
        String cod_per = txtPersonal.getText();
        int diag_prin = Integer.parseInt(String.valueOf(tbDiagPrincipal.getValueAt(fila, 0)));
        String cod_usu = adEmerCab5.codUsuario(lblUsuUsuario.getText());
        HospitalizacionExamenClinico hosp1 = new HospitalizacionExamenClinico();
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                       "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
        if(guardar == 0){
            hosp1.setHec_id(hec_id);
            hosp1.setId_preventa(id_preventa);
            hosp1.setHec_ex_gen(ex_gen);
            hosp1.setHec_piel_tej(piel);
            hosp1.setHec_cab(cabeza);
            hosp1.setHec_oj(ojo);
            hosp1.setHec_dientes(dientes);
            hosp1.setHec_torax(torax);
            hosp1.setHec_mam(mam);
            hosp1.setHec_pulmon(pulmon);
            hosp1.setHec_cora(cora);
            hosp1.setHec_linf(linf);
            hosp1.setHec_genit(genit);
            hosp1.setHec_tacto_rec(tacto);
            hosp1.setHec_ex_gine(ex_gine);
            hosp1.setHec_extrem(extrem);
            hosp1.setHec_sis_neuro(sist);
            hosp1.setCod_per(cod_per);
            hosp1.setDiag_prin(diag_prin);
            hosp1.setCod_usu(cod_usu);
            if(hosp1.mantenimientoHospitalizacionExClinico(lblMant.getText())==true){
                retorna = true;
            }
        }else{
                JOptionPane.showMessageDialog(this, "No se realizó ningun movimiento");
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
        FrmCie10Presun = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tbCiePresun = new javax.swing.JTable();
        txtBuscarCie10 = new javax.swing.JTextField();
        btnBuscarCie10 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        FrmCie10Defi = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        titulo8 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tbCieDefi = new javax.swing.JTable();
        txtBuscarCieDefi = new javax.swing.JTextField();
        btnBuscarCie11 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        FrmCie10Sind = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        titulo9 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        tbCieSind = new javax.swing.JTable();
        txtBuscarSind = new javax.swing.JTextField();
        btnBuscarCie12 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        FrmCie10Prin = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        titulo10 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tbCiePrin = new javax.swing.JTable();
        txtBuscarPrin = new javax.swing.JTextField();
        btnBuscarCie13 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPA = new javax.swing.JTextField();
        txtPeso = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFC = new javax.swing.JTextField();
        txtFR = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTemp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        txtActoMedico = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNHC = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtNroCama = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtExamenGeneral = new javax.swing.JEditorPane();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPiel = new javax.swing.JEditorPane();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCabeza = new javax.swing.JEditorPane();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtOjos = new javax.swing.JEditorPane();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDientes = new javax.swing.JEditorPane();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtTorax = new javax.swing.JEditorPane();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtMamas = new javax.swing.JEditorPane();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtPulmones = new javax.swing.JEditorPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtCorazon = new javax.swing.JEditorPane();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtLinfaticos = new javax.swing.JEditorPane();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtGenitales = new javax.swing.JEditorPane();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtTactoRec = new javax.swing.JEditorPane();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtExGineco = new javax.swing.JEditorPane();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtExtremidades = new javax.swing.JEditorPane();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtSistemaNeuro = new javax.swing.JEditorPane();
        txtIdPreventa = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtTalla = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbDiagPresuntivo = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbDiagDefinitivo = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbDiagSindromico = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tbDiagPrincipal = new javax.swing.JTable();
        btnAgregarDiagSindromico = new javax.swing.JButton();
        btnAgregarDiagPrincipal = new javax.swing.JButton();
        btnAgregarDiagPresuntivo = new javax.swing.JButton();
        btnAgregarDiagDefinitivo = new javax.swing.JButton();
        lblIdHC = new javax.swing.JLabel();
        txtIDTriaje = new javax.swing.JTextField();
        txtPersonal = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        lblMant = new javax.swing.JLabel();

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

        FrmCie10Presun.setMinimumSize(new java.awt.Dimension(600, 450));

        jPanel10.setBackground(new java.awt.Color(217, 176, 86));
        jPanel10.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel10.setLayout(null);

        titulo7.setBackground(new java.awt.Color(153, 0, 51));
        titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo7.setForeground(new java.awt.Color(255, 255, 255));
        titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo7.setText("CIE 10");
        titulo7.setToolTipText("");
        titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel10.add(titulo7);
        titulo7.setBounds(10, 10, 180, 41);

        jScrollPane24.setBorder(null);
        jScrollPane24.setOpaque(false);

        tbCiePresun = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCiePresun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCiePresun.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCiePresun.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbCiePresun.getTableHeader().setReorderingAllowed(false);
        tbCiePresun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCiePresunKeyPressed(evt);
            }
        });
        jScrollPane24.setViewportView(tbCiePresun);

        jPanel10.add(jScrollPane24);
        jScrollPane24.setBounds(0, 110, 595, 312);

        txtBuscarCie10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCie10CaretUpdate(evt);
            }
        });
        txtBuscarCie10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarCie10KeyPressed(evt);
            }
        });
        jPanel10.add(txtBuscarCie10);
        txtBuscarCie10.setBounds(10, 60, 230, 30);

        btnBuscarCie10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarCie10.setBorderPainted(false);
        btnBuscarCie10.setContentAreaFilled(false);
        btnBuscarCie10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.add(btnBuscarCie10);
        btnBuscarCie10.setBounds(240, 60, 30, 30);

        jLabel36.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Código CIE 10 / Diagnóstico Presuntivo");
        jPanel10.add(jLabel36);
        jLabel36.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmCie10PresunLayout = new javax.swing.GroupLayout(FrmCie10Presun.getContentPane());
        FrmCie10Presun.getContentPane().setLayout(FrmCie10PresunLayout);
        FrmCie10PresunLayout.setHorizontalGroup(
            FrmCie10PresunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        FrmCie10PresunLayout.setVerticalGroup(
            FrmCie10PresunLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        FrmCie10Defi.setMinimumSize(new java.awt.Dimension(600, 450));
        FrmCie10Defi.setPreferredSize(new java.awt.Dimension(600, 450));

        jPanel11.setBackground(new java.awt.Color(217, 176, 86));
        jPanel11.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel11.setLayout(null);

        titulo8.setBackground(new java.awt.Color(153, 0, 51));
        titulo8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo8.setForeground(new java.awt.Color(255, 255, 255));
        titulo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo8.setText("CIE 10");
        titulo8.setToolTipText("");
        titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel11.add(titulo8);
        titulo8.setBounds(10, 10, 180, 41);

        jScrollPane25.setBorder(null);
        jScrollPane25.setOpaque(false);

        tbCieDefi = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCieDefi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCieDefi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCieDefi.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbCieDefi.getTableHeader().setReorderingAllowed(false);
        tbCieDefi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCieDefiKeyPressed(evt);
            }
        });
        jScrollPane25.setViewportView(tbCieDefi);

        jPanel11.add(jScrollPane25);
        jScrollPane25.setBounds(0, 110, 595, 312);

        txtBuscarCieDefi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCieDefiCaretUpdate(evt);
            }
        });
        txtBuscarCieDefi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarCieDefiKeyPressed(evt);
            }
        });
        jPanel11.add(txtBuscarCieDefi);
        txtBuscarCieDefi.setBounds(10, 60, 230, 30);

        btnBuscarCie11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarCie11.setBorderPainted(false);
        btnBuscarCie11.setContentAreaFilled(false);
        btnBuscarCie11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.add(btnBuscarCie11);
        btnBuscarCie11.setBounds(240, 60, 30, 30);

        jLabel37.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Código CIE 10 / Diagnóstico Definitivo");
        jPanel11.add(jLabel37);
        jLabel37.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmCie10DefiLayout = new javax.swing.GroupLayout(FrmCie10Defi.getContentPane());
        FrmCie10Defi.getContentPane().setLayout(FrmCie10DefiLayout);
        FrmCie10DefiLayout.setHorizontalGroup(
            FrmCie10DefiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        FrmCie10DefiLayout.setVerticalGroup(
            FrmCie10DefiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        FrmCie10Sind.setMinimumSize(new java.awt.Dimension(600, 450));
        FrmCie10Sind.setPreferredSize(new java.awt.Dimension(600, 450));

        jPanel12.setBackground(new java.awt.Color(217, 176, 86));
        jPanel12.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel12.setLayout(null);

        titulo9.setBackground(new java.awt.Color(153, 0, 51));
        titulo9.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo9.setForeground(new java.awt.Color(255, 255, 255));
        titulo9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo9.setText("CIE 10");
        titulo9.setToolTipText("");
        titulo9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel12.add(titulo9);
        titulo9.setBounds(10, 10, 180, 41);

        jScrollPane26.setBorder(null);
        jScrollPane26.setOpaque(false);

        tbCieSind = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCieSind.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCieSind.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCieSind.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbCieSind.getTableHeader().setReorderingAllowed(false);
        tbCieSind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCieSindKeyPressed(evt);
            }
        });
        jScrollPane26.setViewportView(tbCieSind);

        jPanel12.add(jScrollPane26);
        jScrollPane26.setBounds(0, 110, 595, 312);

        txtBuscarSind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarSindCaretUpdate(evt);
            }
        });
        txtBuscarSind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarSindKeyPressed(evt);
            }
        });
        jPanel12.add(txtBuscarSind);
        txtBuscarSind.setBounds(10, 60, 230, 30);

        btnBuscarCie12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarCie12.setBorderPainted(false);
        btnBuscarCie12.setContentAreaFilled(false);
        btnBuscarCie12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.add(btnBuscarCie12);
        btnBuscarCie12.setBounds(240, 60, 30, 30);

        jLabel38.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Código CIE 10 / Diagnóstico Sindrómico");
        jPanel12.add(jLabel38);
        jLabel38.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmCie10SindLayout = new javax.swing.GroupLayout(FrmCie10Sind.getContentPane());
        FrmCie10Sind.getContentPane().setLayout(FrmCie10SindLayout);
        FrmCie10SindLayout.setHorizontalGroup(
            FrmCie10SindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        FrmCie10SindLayout.setVerticalGroup(
            FrmCie10SindLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        FrmCie10Prin.setMinimumSize(new java.awt.Dimension(600, 450));
        FrmCie10Prin.setResizable(false);

        jPanel13.setBackground(new java.awt.Color(217, 176, 86));
        jPanel13.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel13.setLayout(null);

        titulo10.setBackground(new java.awt.Color(153, 0, 51));
        titulo10.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo10.setForeground(new java.awt.Color(255, 255, 255));
        titulo10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo10.setText("CIE 10");
        titulo10.setToolTipText("");
        titulo10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel13.add(titulo10);
        titulo10.setBounds(10, 10, 180, 41);

        jScrollPane27.setBorder(null);
        jScrollPane27.setOpaque(false);

        tbCiePrin = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCiePrin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCiePrin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCiePrin.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbCiePrin.getTableHeader().setReorderingAllowed(false);
        tbCiePrin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCiePrinKeyPressed(evt);
            }
        });
        jScrollPane27.setViewportView(tbCiePrin);

        jPanel13.add(jScrollPane27);
        jScrollPane27.setBounds(0, 110, 595, 312);

        txtBuscarPrin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPrinCaretUpdate(evt);
            }
        });
        txtBuscarPrin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarPrinKeyPressed(evt);
            }
        });
        jPanel13.add(txtBuscarPrin);
        txtBuscarPrin.setBounds(10, 60, 230, 30);

        btnBuscarCie13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarCie13.setBorderPainted(false);
        btnBuscarCie13.setContentAreaFilled(false);
        btnBuscarCie13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.add(btnBuscarCie13);
        btnBuscarCie13.setBounds(240, 60, 30, 30);

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 0, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Código CIE 10 / Diagnóstico Principal");
        jPanel13.add(jLabel39);
        jLabel39.setBounds(10, 90, 220, 14);

        javax.swing.GroupLayout FrmCie10PrinLayout = new javax.swing.GroupLayout(FrmCie10Prin.getContentPane());
        FrmCie10Prin.getContentPane().setLayout(FrmCie10PrinLayout);
        FrmCie10PrinLayout.setHorizontalGroup(
            FrmCie10PrinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        FrmCie10PrinLayout.setVerticalGroup(
            FrmCie10PrinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBackground(new java.awt.Color(217, 176, 86));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));
        jPanel8.setLayout(null);

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo5.setText("Examen Clínico");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(titulo5);
        titulo5.setBounds(10, 10, 470, 41);

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Silvana");
        jPanel8.add(lblUsuUsuario);
        lblUsuUsuario.setBounds(1190, 20, 85, 20);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        jPanel8.add(jLabel19);
        jLabel19.setBounds(1150, 20, 32, 24);

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
        btnBuscarPac.setBounds(0, 120, 180, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Alt + B");
        jPanel8.add(jLabel7);
        jLabel7.setBounds(170, 130, 50, 20);

        jScrollPane1.setBorder(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel1.setText("Funciones Vitales");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel2.setText("Presión Arterial:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel3.setText("Peso:");

        txtPA.setEditable(false);
        txtPA.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPA.setEnabled(false);

        txtPeso.setEditable(false);
        txtPeso.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPeso.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("Frec. Respiratoria:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("Frec. Cardiáca:");

        txtFC.setEditable(false);
        txtFC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtFC.setEnabled(false);

        txtFR.setEditable(false);
        txtFR.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtFR.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel4.setText("Temperatura:");

        txtTemp.setEditable(false);
        txtTemp.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTemp.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel9.setText("Acto Médico");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel12.setText("Paciente:");

        txtPaciente.setEditable(false);
        txtPaciente.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPaciente.setEnabled(false);

        txtActoMedico.setEditable(false);
        txtActoMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtActoMedico.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel10.setText("Nº H.C.:");

        txtNHC.setEditable(false);
        txtNHC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNHC.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel11.setText("DNI:");

        txtDNI.setEditable(false);
        txtDNI.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDNI.setEnabled(false);

        txtNroCama.setEditable(false);
        txtNroCama.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNroCama.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel13.setText("Nº Cama:");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel8.setText("Examen General");

        txtExamenGeneral.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtExamenGeneral.setEnabled(false);
        jScrollPane3.setViewportView(txtExamenGeneral);

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel14.setText("Examen Regional");

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel15.setText("1.- Piel, Tejido celular subcutáneo");

        txtPiel.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPiel.setEnabled(false);
        jScrollPane4.setViewportView(txtPiel);

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel17.setText("2.- Cabeza, cuello");

        txtCabeza.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtCabeza.setEnabled(false);
        jScrollPane5.setViewportView(txtCabeza);

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel18.setText("3.- Ojos, oídos, nariz, garganta, boca");

        txtOjos.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtOjos.setEnabled(false);
        jScrollPane6.setViewportView(txtOjos);

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel20.setText("4.- Dientes");

        txtDientes.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDientes.setEnabled(false);
        jScrollPane7.setViewportView(txtDientes);

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel21.setText("5.- Tórax");

        txtTorax.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTorax.setEnabled(false);
        jScrollPane8.setViewportView(txtTorax);

        jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel22.setText("6.- Mamas");

        txtMamas.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtMamas.setEnabled(false);
        jScrollPane10.setViewportView(txtMamas);

        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel23.setText("7.- Pulmones");

        txtPulmones.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPulmones.setEnabled(false);
        jScrollPane11.setViewportView(txtPulmones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane8)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addComponent(jLabel21)))
                                .addComponent(jLabel22)))
                        .addComponent(jLabel23)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jScrollPane12.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel24.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel24.setText("8.- Corazón:");

        txtCorazon.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtCorazon.setEnabled(false);
        jScrollPane13.setViewportView(txtCorazon);

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel25.setText("9.- Linfáticos");

        txtLinfaticos.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtLinfaticos.setEnabled(false);
        jScrollPane14.setViewportView(txtLinfaticos);

        jLabel26.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel26.setText("10.- Genitales");

        txtGenitales.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtGenitales.setEnabled(false);
        jScrollPane15.setViewportView(txtGenitales);

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel27.setText("11.- Tacto rectal");

        txtTactoRec.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTactoRec.setEnabled(false);
        jScrollPane16.setViewportView(txtTactoRec);

        jLabel28.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel28.setText("12.- Examen Ginecológico");

        txtExGineco.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtExGineco.setEnabled(false);
        jScrollPane17.setViewportView(txtExGineco);

        jLabel29.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel29.setText("13.- Extremidades");

        txtExtremidades.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtExtremidades.setEnabled(false);
        jScrollPane18.setViewportView(txtExtremidades);

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel30.setText("14.- Sistema Neurológico");

        txtSistemaNeuro.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtSistemaNeuro.setEnabled(false);
        jScrollPane19.setViewportView(txtSistemaNeuro);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane12.setViewportView(jPanel4);

        txtIdPreventa.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtIdPreventa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIdPreventaCaretUpdate(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel31.setText("Talla:");

        txtTalla.setEditable(false);
        txtTalla.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtTalla.setEnabled(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel32.setText("Diagnósticos Presuntivos");

        jScrollPane20.setBorder(null);

        tbDiagPresuntivo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagPresuntivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagPresuntivo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagPresuntivo.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbDiagPresuntivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagPresuntivoKeyPressed(evt);
            }
        });
        jScrollPane20.setViewportView(tbDiagPresuntivo);

        jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel33.setText("Diagnósticos Definitivos");

        jScrollPane21.setBorder(null);

        tbDiagDefinitivo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagDefinitivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagDefinitivo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagDefinitivo.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbDiagDefinitivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagDefinitivoKeyPressed(evt);
            }
        });
        jScrollPane21.setViewportView(tbDiagDefinitivo);

        jLabel34.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel34.setText("Diagnósticos Sindrómicos");

        jScrollPane22.setBorder(null);

        tbDiagSindromico = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagSindromico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagSindromico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagSindromico.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbDiagSindromico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagSindromicoKeyPressed(evt);
            }
        });
        jScrollPane22.setViewportView(tbDiagSindromico);

        jLabel35.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel35.setText("Diagnóstico Principal");

        jScrollPane23.setBorder(null);

        tbDiagPrincipal = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbDiagPrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDiagPrincipal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbDiagPrincipal.setSelectionBackground(new java.awt.Color(217, 176, 86));
        tbDiagPrincipal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDiagPrincipalKeyPressed(evt);
            }
        });
        jScrollPane23.setViewportView(tbDiagPrincipal);

        btnAgregarDiagSindromico.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        btnAgregarDiagSindromico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAgregarDiagSindromico.setMnemonic('S');
        btnAgregarDiagSindromico.setText("Agregar");
        btnAgregarDiagSindromico.setToolTipText("Alt + S");
        btnAgregarDiagSindromico.setBorderPainted(false);
        btnAgregarDiagSindromico.setContentAreaFilled(false);
        btnAgregarDiagSindromico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDiagSindromico.setEnabled(false);
        btnAgregarDiagSindromico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiagSindromicoActionPerformed(evt);
            }
        });

        btnAgregarDiagPrincipal.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        btnAgregarDiagPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAgregarDiagPrincipal.setMnemonic('D');
        btnAgregarDiagPrincipal.setText("Agregar");
        btnAgregarDiagPrincipal.setToolTipText("Alt + D");
        btnAgregarDiagPrincipal.setBorderPainted(false);
        btnAgregarDiagPrincipal.setContentAreaFilled(false);
        btnAgregarDiagPrincipal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDiagPrincipal.setEnabled(false);
        btnAgregarDiagPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiagPrincipalActionPerformed(evt);
            }
        });

        btnAgregarDiagPresuntivo.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        btnAgregarDiagPresuntivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAgregarDiagPresuntivo.setMnemonic('P');
        btnAgregarDiagPresuntivo.setText("Agregar");
        btnAgregarDiagPresuntivo.setToolTipText("Alt + P");
        btnAgregarDiagPresuntivo.setBorderPainted(false);
        btnAgregarDiagPresuntivo.setContentAreaFilled(false);
        btnAgregarDiagPresuntivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDiagPresuntivo.setEnabled(false);
        btnAgregarDiagPresuntivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiagPresuntivoActionPerformed(evt);
            }
        });

        btnAgregarDiagDefinitivo.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        btnAgregarDiagDefinitivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
        btnAgregarDiagDefinitivo.setMnemonic('D');
        btnAgregarDiagDefinitivo.setText("Agregar");
        btnAgregarDiagDefinitivo.setToolTipText("Alt + D");
        btnAgregarDiagDefinitivo.setBorderPainted(false);
        btnAgregarDiagDefinitivo.setContentAreaFilled(false);
        btnAgregarDiagDefinitivo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDiagDefinitivo.setEnabled(false);
        btnAgregarDiagDefinitivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDiagDefinitivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarDiagDefinitivo))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarDiagPresuntivo))
                    .addComponent(jScrollPane21))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarDiagSindromico))
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addComponent(jScrollPane23)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarDiagPrincipal))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel34)
                    .addComponent(btnAgregarDiagSindromico)
                    .addComponent(btnAgregarDiagPresuntivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(btnAgregarDiagDefinitivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(btnAgregarDiagPrincipal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        lblIdHC.setText("jLabel36");

        txtIDTriaje.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtIDTriaje.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIDTriajeCaretUpdate(evt);
            }
        });

        lblMant.setText("jLabel40");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNHC, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                        .addComponent(txtNroCama)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22)
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(22, 22, 22)
                                            .addComponent(jLabel4)
                                            .addGap(20, 20, 20)
                                            .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtIdPreventa, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                    .addComponent(txtID))
                                .addGap(9, 9, 9)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdHC, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMant, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMant))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdHC)
                            .addComponent(txtPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtNHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtNroCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel2);

        jPanel8.add(jScrollPane1);
        jScrollPane1.setBounds(0, 160, 1365, 560);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1368, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            HospitalizacionExamenClinico hose = new HospitalizacionExamenClinico();
            if(hose.idHospitalizacionExamenClinico()!=0){
                txtID.setText(String.valueOf(hose.idHospitalizacionExamenClinico()));
            } else {
                txtID.setText("1");
            }
            limpiar();
            habilitarCampos(true);
            btnGuardar.setEnabled(true);
            lblMant.setText("I");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatosExClinico();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbPacientesHospKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesHospKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientesHosp.getSelectedRow()==0){
            tbPacientesHosp.getSelectionModel().setSelectionInterval(0,0);
            txtBuscarPaciente.requestFocus();
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            FrmBuscarPaciente.dispose();
            enviarDatosPac();
        }
    }//GEN-LAST:event_tbPacientesHospKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbPacientesHosp.getSelectionModel().setSelectionInterval(0,0);
            tbPacientesHosp.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPacienteKeyPressed

    private void btnBuscarPacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacActionPerformed
        FrmBuscarPaciente.setVisible(true);
        FrmBuscarPaciente.setLocationRelativeTo(null);//en el centro
        FrmBuscarPaciente.setResizable(false);
        FrmBuscarPaciente.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosp = new HospitalizacionPapeletas();
        hosp.listarPapeleta("C", txtBuscarPaciente.getText(), tbPacientesHosp,"F");
        txtBuscarPaciente.requestFocus();
    }//GEN-LAST:event_btnBuscarPacActionPerformed

    private void txtIdPreventaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdPreventaCaretUpdate
        HospitalizacionExamenClinico hospEx2 = new HospitalizacionExamenClinico();
        hospEx2.datosTriaje(Integer.parseInt(txtIdPreventa.getText()));
    }//GEN-LAST:event_txtIdPreventaCaretUpdate

    private void txtIDTriajeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDTriajeCaretUpdate
        HospitalizacionExamenClinico hospEx3 = new HospitalizacionExamenClinico();
        hospEx3.listarDiagPresun(txtIDTriaje.getText(),tbDiagPresuntivo);
        hospEx3.listarDiagDefinitivo(txtIDTriaje.getText(),tbDiagDefinitivo);
    }//GEN-LAST:event_txtIDTriajeCaretUpdate

    private void tbCiePresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresunKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCiePresun.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCie10.requestFocus();
            tbCiePresun.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCiePresun,tbDiagPresuntivo,FrmCie10Presun);
        }
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void btnAgregarDiagPresuntivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagPresuntivoActionPerformed
        FrmCie10Presun.setVisible(true);
        FrmCie10Presun.setLocationRelativeTo(null);//en el centro
        FrmCie10Presun.setResizable(false);
        FrmCie10Presun.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_btnAgregarDiagPresuntivoActionPerformed

    private void tbCieDefiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCieDefiKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCieDefi.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCieDefi.requestFocus();
            tbCieDefi.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCieDefi,tbDiagDefinitivo,FrmCie10Defi);
        }
    }//GEN-LAST:event_tbCieDefiKeyPressed

    private void txtBuscarCieDefiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCieDefiCaretUpdate
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarCieDefi.getText(), tbCieDefi);
    }//GEN-LAST:event_txtBuscarCieDefiCaretUpdate

    private void txtBuscarCieDefiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCieDefiKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCieDefi.getSelectionModel().setSelectionInterval(0, 0);
            tbCieDefi.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCieDefiKeyPressed

    private void tbCieSindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCieSindKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCieSind.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarSind.requestFocus();
            tbCieSind.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCieSind,tbDiagSindromico,FrmCie10Sind);
        }
    }//GEN-LAST:event_tbCieSindKeyPressed

    private void txtBuscarSindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarSindCaretUpdate
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarSind.getText(), tbCieSind);
    }//GEN-LAST:event_txtBuscarSindCaretUpdate

    private void txtBuscarSindKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSindKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCieSind.getSelectionModel().setSelectionInterval(0, 0);
            tbCieSind.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarSindKeyPressed

    private void tbCiePrinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePrinKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCiePrin.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarPrin.requestFocus();
            tbCiePrin.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCiePrin,tbDiagPrincipal,FrmCie10Prin);
        }
    }//GEN-LAST:event_tbCiePrinKeyPressed

    private void txtBuscarPrinCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPrinCaretUpdate
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarPrin.getText(), tbCiePrin);
    }//GEN-LAST:event_txtBuscarPrinCaretUpdate

    private void txtBuscarPrinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPrinKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePrin.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePrin.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarPrinKeyPressed

    private void btnAgregarDiagDefinitivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagDefinitivoActionPerformed
        FrmCie10Defi.setVisible(true);
        FrmCie10Defi.setLocationRelativeTo(null);//en el centro
        FrmCie10Defi.setResizable(false);
        FrmCie10Defi.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarCieDefi.getText(), tbCieDefi);
    }//GEN-LAST:event_btnAgregarDiagDefinitivoActionPerformed

    private void btnAgregarDiagSindromicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagSindromicoActionPerformed
        FrmCie10Sind.setVisible(true);
        FrmCie10Sind.setLocationRelativeTo(null);//en el centro
        FrmCie10Sind.setResizable(false);
        FrmCie10Sind.getContentPane().setBackground(Color.WHITE);
        AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
        adTopico.cargarDatosCie10(txtBuscarSind.getText(), tbCieSind);
    }//GEN-LAST:event_btnAgregarDiagSindromicoActionPerformed

    private void btnAgregarDiagPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDiagPrincipalActionPerformed
        if(tbDiagPrincipal.getRowCount()<1){
            FrmCie10Prin.setVisible(true);
            FrmCie10Prin.setLocationRelativeTo(null);//en el centro
            FrmCie10Prin.setResizable(false);
            FrmCie10Prin.getContentPane().setBackground(Color.WHITE);
            AdmisionEmergenciaTopico adTopico = new AdmisionEmergenciaTopico();
            adTopico.cargarDatosCie10(txtBuscarPrin.getText(), tbCiePrin);
        }else {
            JOptionPane.showMessageDialog(this, "Sólo se permite un \n diagnóstico principal", "Alerta", WIDTH);
        }
    }//GEN-LAST:event_btnAgregarDiagPrincipalActionPerformed

    private void tbDiagPresuntivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagPresuntivoKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE){
            quitarDiagnostico(tbDiagPresuntivo);
        }
    }//GEN-LAST:event_tbDiagPresuntivoKeyPressed

    private void tbDiagDefinitivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagDefinitivoKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE){
            quitarDiagnostico(tbDiagDefinitivo);
        }
    }//GEN-LAST:event_tbDiagDefinitivoKeyPressed

    private void tbDiagSindromicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagSindromicoKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE){
            quitarDiagnostico(tbDiagSindromico);
        }
    }//GEN-LAST:event_tbDiagSindromicoKeyPressed

    private void tbDiagPrincipalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagPrincipalKeyPressed
        char teclaPresionada = evt.getKeyChar(); 
        if(teclaPresionada==KeyEvent.VK_DELETE){
            quitarDiagnostico(tbDiagPrincipal);
        }
    }//GEN-LAST:event_tbDiagPrincipalKeyPressed

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionExClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionExClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionExClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionExClinico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionExClinico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrmBuscarPaciente;
    private javax.swing.JDialog FrmCie10Defi;
    private javax.swing.JDialog FrmCie10Presun;
    private javax.swing.JDialog FrmCie10Prin;
    private javax.swing.JDialog FrmCie10Sind;
    private javax.swing.JButton btnAgregarDiagDefinitivo;
    private javax.swing.JButton btnAgregarDiagPresuntivo;
    private javax.swing.JButton btnAgregarDiagPrincipal;
    private javax.swing.JButton btnAgregarDiagSindromico;
    private javax.swing.JButton btnBuscarCie10;
    private javax.swing.JButton btnBuscarCie11;
    private javax.swing.JButton btnBuscarCie12;
    private javax.swing.JButton btnBuscarCie13;
    private javax.swing.JButton btnBuscarPac;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    protected static javax.swing.JLabel lblIdHC;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JTable tbCieDefi;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JTable tbCiePrin;
    private javax.swing.JTable tbCieSind;
    private javax.swing.JTable tbDiagDefinitivo;
    private javax.swing.JTable tbDiagPresuntivo;
    private javax.swing.JTable tbDiagPrincipal;
    private javax.swing.JTable tbDiagSindromico;
    private javax.swing.JTable tbPacientesHosp;
    private javax.swing.JLabel titulo10;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    private javax.swing.JLabel titulo9;
    public static javax.swing.JTextField txtActoMedico;
    private javax.swing.JTextField txtBuscarCie10;
    private javax.swing.JTextField txtBuscarCieDefi;
    private javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtBuscarPrin;
    private javax.swing.JTextField txtBuscarSind;
    public static javax.swing.JEditorPane txtCabeza;
    public static javax.swing.JEditorPane txtCorazon;
    public static javax.swing.JTextField txtDNI;
    public static javax.swing.JEditorPane txtDientes;
    public static javax.swing.JEditorPane txtExGineco;
    public static javax.swing.JEditorPane txtExamenGeneral;
    public static javax.swing.JEditorPane txtExtremidades;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    public static javax.swing.JEditorPane txtGenitales;
    private javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtIDTriaje;
    public static javax.swing.JTextField txtIdPreventa;
    public static javax.swing.JEditorPane txtLinfaticos;
    public static javax.swing.JEditorPane txtMamas;
    public static javax.swing.JTextField txtNHC;
    public static javax.swing.JTextField txtNroCama;
    public static javax.swing.JEditorPane txtOjos;
    public static javax.swing.JTextField txtPA;
    public static javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtPersonal;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JEditorPane txtPiel;
    public static javax.swing.JEditorPane txtPulmones;
    public static javax.swing.JEditorPane txtSistemaNeuro;
    public static javax.swing.JEditorPane txtTactoRec;
    public static javax.swing.JTextField txtTalla;
    public static javax.swing.JTextField txtTemp;
    public static javax.swing.JEditorPane txtTorax;
    // End of variables declaration//GEN-END:variables
}
