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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Caja.Caja_Nomenclatura;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionEvolucion;
import modelos.hospitalizacion.HospitalizacionExClinicoDiagPresun;
import modelos.hospitalizacion.HospitalizacionNotaEnfermeria;
import modelos.hospitalizacion.HospitalizacionNotaEnfermeriaProcedimiento;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import static vista.hospitalizacion.FrmHospitalizacionExClinico.lblUsuUsuario;
import static vista.hospitalizacion.FrmHospitalizacionExClinico.txtIDMod;

/**
 *
 * @author PC02
 */
public class FrmHospitalizacionNotaEnfermeria extends javax.swing.JFrame {

    
    
    Connection conexion=null;
    Conexion c=new Conexion();
    ResultSet r;
    HospitalizacionNotaEnfermeria notaEnfermeria = new HospitalizacionNotaEnfermeria();
    PreparedStatement pstm;
    DefaultTableModel m;
    AdmisionEmergenciaCabecera adEmer = new AdmisionEmergenciaCabecera();
    public FrmHospitalizacionNotaEnfermeria() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        cerrar();
        habilitarDatosCabecera(false);
        pnlMensaje.setVisible(false);
        LimitadorDeDocumento limitAnotaciones = new LimitadorDeDocumento(300);
        txtAnotacionesMedicas.setDocument(limitAnotaciones);
        LimitadorDeDocumento limitPA = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitFR = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitFC = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitT = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitPESO = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitTALLA = new LimitadorDeDocumento(10);
        LimitadorDeDocumento limitIDM = new LimitadorDeDocumento(10);
        txtPA.setDocument(limitPA);
        txtFR.setDocument(limitFR);
        txtFC.setDocument(limitFC);
        txtT.setDocument(limitT);
        txtPeso.setDocument(limitPESO);
        txtTalla.setDocument(limitTALLA);
        txtIDM.setDocument(limitIDM);
        txtIdPreventa.setVisible(false);
        txtNeId.setVisible(false);
        lblId.setVisible(false);
    }
    
    public void limpiar(){
        txtPA.setText("");
        txtFR.setText("");
        txtFC.setText("");
        txtT.setText("");
        txtPeso.setText("");
        txtTalla.setText("");
        txtIDM.setText("");
        txtAnotacionesMedicas.setText("");
        DefaultTableModel modelo4 = (DefaultTableModel)tbProced.getModel(); 
        int b4=tbProced.getRowCount();
        for(int j=0;j<b4;j++){
                    modelo4.removeRow(0);
        }
    }
    
    public void habilitarDatosCabecera(boolean opcion){
        lblActoMedico.setVisible(opcion);
        lblDNI.setVisible(opcion);
        lblHC.setVisible(opcion);
        lblServicio.setVisible(opcion);
        lblNroCama.setVisible(opcion);
    }
    
    public void habilitarCampos(boolean opcion){
        txtPA.setEditable(opcion);
        txtFR.setEditable(opcion);
        txtFC.setEditable(opcion);
        txtT.setEditable(opcion);
        txtTalla.setEditable(opcion);
        txtPeso.setEditable(opcion);
        txtIDM.setEditable(opcion);
        txtAnotacionesMedicas.setEditable(opcion);
        btnAgregarProcedimiento.setEnabled(opcion);
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
    
    public void enviarDatosPaciente(){
        int fila = tbPacientesHospitalizados.getSelectedRow();
        txtIdPreventa.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 0)));
        lblActoMedico.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 2)));
        lblHC.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 3)));
        lblDNI.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 4)));
        lblPaciente.setText("Paciente: " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 5)));
        lblNroCama.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 11)));
        lblServicio.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 8)));
        BuscarHospitalizacion.dispose();
        lblMant.setText("I");
        btnGuardar.setEnabled(true);
        habilitarDatosCabecera(true);
        habilitarCampos(true);
        btnNuevo.setEnabled(true);
    }
    
    public void enviarDatosNotasEnf(){
        int fila = tbNotas.getSelectedRow();
        txtNeId.setText(String.valueOf(tbNotas.getValueAt(fila, 0)));
        txtPA.setText(String.valueOf(tbNotas.getValueAt(fila, 3)));
        txtFR.setText(String.valueOf(tbNotas.getValueAt(fila, 4)));
        txtFC.setText(String.valueOf(tbNotas.getValueAt(fila, 5)));
        txtT.setText(String.valueOf(tbNotas.getValueAt(fila, 6)));
        txtPeso.setText(String.valueOf(tbNotas.getValueAt(fila, 7)));
        txtTalla.setText(String.valueOf(tbNotas.getValueAt(fila, 8)));
        txtIDM.setText(String.valueOf(tbNotas.getValueAt(fila, 9)));
        txtAnotacionesMedicas.setText(String.valueOf(tbNotas.getValueAt(fila, 10)));
        lblMant.setText("U");
        habilitarDatosCabecera(true);
        habilitarCampos(false);
        btnGuardar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }
    
    public void formatotbProcedimientos(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(550);//clasificacion
        tabla.getColumnModel().getColumn(2).setPreferredWidth(60);//clasificacion
        tabla.setRowHeight(40);
    }
    
    public boolean repiteProcedimientos(JTable tablaEnvia, JTable tablaRecibe){
        int filaselec=tablaEnvia.getSelectedRow();
        boolean c=false;
        for (int i = 0; i < tablaRecibe.getRowCount(); i++){    
            if(tablaRecibe.getValueAt(i, 0).toString().equalsIgnoreCase(tablaEnvia.getValueAt(filaselec, 0).toString())){
                    c=true;
            }
        }
        return c;
     }
    
    public void enviarProcedimientos(JTable tablaEnvia, JTable tablaRecibe){
        m = (DefaultTableModel) tablaRecibe.getModel();
        int fila = tablaEnvia.getSelectedRow();
        if(tablaRecibe.getColumnCount()==0){
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
//            formatotbProcedimientos(tablaRecibe);
            dlgBuscarCPT.dispose();
            //btnQuitarDiag.setEnabled(true);
        } //else
        if(repiteProcedimientos(tablaEnvia,tablaRecibe)==true)
            JOptionPane.showMessageDialog(dlgBuscarCPT, "Diagnóstico ya registrado");
        else {
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbProcedimientos(tablaRecibe);
            dlgBuscarCPT.dispose();
            //btnQuitarDiag.setEnabled(true);
        }
    }
    
    public boolean mantenimientoNotaEnfermeria(){
        boolean retorna = false;
        try {
            HospitalizacionNotaEnfermeria hospitalizacion = new HospitalizacionNotaEnfermeria();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("E"))
                hospitalizacion.setNeId(Integer.parseInt(txtNeId.getText()));
            hospitalizacion.setIdPreventa(Integer.parseInt(txtIdPreventa.getText()));
            hospitalizacion.setActoMedico(0);
            hospitalizacion.setNePa(txtPA.getText());
            hospitalizacion.setNeFr(txtFR.getText());
            hospitalizacion.setNeFc(txtFC.getText());
            hospitalizacion.setNeT(txtT.getText());
            hospitalizacion.setNePeso(txtPeso.getText());
            hospitalizacion.setNeTalla(txtTalla.getText());
            hospitalizacion.setNeIdm(txtIDM.getText());
            hospitalizacion.setNeAnotaciones(txtAnotacionesMedicas.getText());
            hospitalizacion.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(hospitalizacion.mantenimientoHospitalizacionNotaEnfermeria(lblMant.getText())==true){
                hospitalizacion.notaEnfermeriaID();
                if(lblMant.getText().equals("I")){
                    lblMant.setText("I");
                }
                if(tbProced.getRowCount()!=0){
                    if(guardarProcedimientos()){    
                        hospitalizacion.listarProcedimientos(txtIdPreventa.getText(), tbNotas);
                        if(lblMant.getText().equals("E"))
                            lblMensaje.setText("Registro eliminado");
                        else
                            lblMensaje.setText("Datos guardados de forma correcta");
                        pnlMensaje.setVisible(true);
                        habilitarCampos(true);
                        btnGuardar.setEnabled(false);
                        pnlMensaje.setBackground(new Color(33,115,70));
                        btnSi.setVisible(true);
                        btnSi.setText("OK");
                        btnNo.setVisible(false);
                        limpiar();
                    } else {
                        pnlMensaje.setVisible(true);
                        lblMensaje.setText("Ocurrió un error, al guardar el procedimiento");
                        pnlMensaje.setBackground(new Color(255,91,70));
                        btnSi.setVisible(false);
                        btnNo.setVisible(false);
                    }
                } else {
                    hospitalizacion.listarProcedimientos(txtIdPreventa.getText(), tbNotas);
                        pnlMensaje.setVisible(true);
                        if(lblMant.getText().equals("E"))
                            lblMensaje.setText("Registro eliminado");
                        else
                        lblMensaje.setText("Datos guardados de forma correcta");
                        habilitarCampos(true);
                        btnGuardar.setEnabled(false);
                        pnlMensaje.setBackground(new Color(33,115,70));
                        btnSi.setVisible(true);
                        btnSi.setText("OK");
                        btnNo.setVisible(false);
                        limpiar();
                }
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: mantenimientoNotaEnfermeria" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean guardarProcedimientos(){
        AdmisionEmergenciaCabecera adEmer2 = new AdmisionEmergenciaCabecera();
        boolean retorna = false;
        try {
        int id = Integer.parseInt(lblId.getText());
            for (int i = 0; i < tbProced.getRowCount(); i++){      
                HospitalizacionNotaEnfermeriaProcedimiento hospitalizacion = new HospitalizacionNotaEnfermeriaProcedimiento();
                hospitalizacion.setNeId(id);
                hospitalizacion.setCodNomenCaja(tbProced.getValueAt(i,2).toString());
                hospitalizacion.setCodUsu(adEmer2.codUsuario(lblusu.getText()));
                if(hospitalizacion.mantenimientoHospitalizacionNotaEnfermeriaProcedimiento(lblMant.getText())==true)
                    retorna = true;
            }
        } catch (Exception e) {
            System.out.println("Error: guardarProcedimientos " + e.getMessage());
        }
        return retorna;
    }
    
    public void validaTurno(String ne_id){
        try {
            String codUsu = adEmer.codUsuario(lblusu.getText());
            PreparedStatement cmd = notaEnfermeria.getCn().prepareStatement("SELECT NE_ID\n" +
                "FROM HOSPITALIZACION_NOTA_ENFERMERIA\n" +
                "WHERE COD_USU = '"+codUsu+"'\n" +
                "AND FECHA_ACTU = CONVERT(VARCHAR(10),GETDATE(),103) AND NE_ID ="+ne_id+" ");
            ResultSet res = cmd.executeQuery();
            if(res.next()){ //SI ESTA DE TURNO (DEL DIA Y EL USUARIO QUE LO INGRESO)
                btnEliminar.setEnabled(true);

            }else { //NO ESTA DE TURNO
                btnEliminar.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: validaTurno: " + e.toString());
        }
    }
    
    public void buscarPaciente(){
        BuscarHospitalizacion.setVisible(true);
        BuscarHospitalizacion.setLocationRelativeTo(null);//en el centro
        BuscarHospitalizacion.setResizable(false);
        BuscarHospitalizacion.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.listarPapeleta("H", txtPaciente.getText(), tbPacientesHospitalizados,"C");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgBuscarCPT = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        btnbuscar3 = new javax.swing.JButton();
        txtBuscar2 = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        tb_Grupo4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            BuscarHospitalizacion = new javax.swing.JDialog();
            jPanel7 = new javax.swing.JPanel();
            jLabel30 = new javax.swing.JLabel();
            jPanel27 = new javax.swing.JPanel();
            txtPaciente = new javax.swing.JTextField();
            btnBuscar = new javax.swing.JLabel();
            jPanel20 = new javax.swing.JPanel();
            jScrollPane7 = new javax.swing.JScrollPane();
            tbPacientesHospitalizados = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel8 = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                txtID = new javax.swing.JTextField();
                btnBuscarPaciente = new javax.swing.JButton();
                btnNuevo = new javax.swing.JButton();
                btnGuardar = new javax.swing.JButton();
                btnEliminar = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                lblMant = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jPanel4 = new javax.swing.JPanel();
                btnAgregarProcedimiento = new javax.swing.JButton();
                jScrollPane5 = new javax.swing.JScrollPane();
                txtAnotacionesMedicas = new javax.swing.JEditorPane();
                jPanel5 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                txtPA = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                txtFR = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                jPanel12 = new javax.swing.JPanel();
                txtFC = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                txtT = new javax.swing.JTextField();
                jLabel14 = new javax.swing.JLabel();
                jPanel14 = new javax.swing.JPanel();
                txtPeso = new javax.swing.JTextField();
                jLabel15 = new javax.swing.JLabel();
                jPanel15 = new javax.swing.JPanel();
                txtTalla = new javax.swing.JTextField();
                jLabel17 = new javax.swing.JLabel();
                jPanel16 = new javax.swing.JPanel();
                txtIDM = new javax.swing.JTextField();
                txtPaciente1 = new javax.swing.JLabel();
                lblProcedimientos = new javax.swing.JLabel();
                jScrollPane6 = new javax.swing.JScrollPane();
                tbNotas = new javax.swing.JTable();
                jScrollPane8 = new javax.swing.JScrollPane();
                tbProced = new javax.swing.JTable();
                pnlCabecera = new javax.swing.JPanel();
                lblPaciente = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                lblActoMedico = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                lblHC = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                lblNroCama = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                lblServicio = new javax.swing.JLabel();
                txtIdPreventa = new javax.swing.JTextField();
                txtNeId = new javax.swing.JTextField();
                lblId = new javax.swing.JLabel();
                pnlMensaje = new javax.swing.JPanel();
                lblMensaje = new javax.swing.JLabel();
                btnSi = new javax.swing.JButton();
                btnNo = new javax.swing.JButton();

                dlgBuscarCPT.setAlwaysOnTop(true);
                dlgBuscarCPT.setMinimumSize(new java.awt.Dimension(749, 338));
                dlgBuscarCPT.setResizable(false);
                dlgBuscarCPT.getContentPane().setLayout(null);

                jPanel17.setBackground(new java.awt.Color(255, 65, 26));
                jPanel17.setMinimumSize(new java.awt.Dimension(310, 441));

                jLabel74.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel74.setForeground(new java.awt.Color(255, 255, 255));
                jLabel74.setText("Procedimientos Terapéuticos");

                btnbuscar3.setForeground(new java.awt.Color(240, 240, 240));
                btnbuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                btnbuscar3.setMnemonic('N');
                btnbuscar3.setToolTipText("");
                btnbuscar3.setContentAreaFilled(false);
                btnbuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnbuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnbuscar3.setIconTextGap(30);
                btnbuscar3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnbuscar3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnbuscar3ActionPerformed(evt);
                    }
                });

                txtBuscar2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscar2CaretUpdate(evt);
                    }
                });
                txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscar2ActionPerformed(evt);
                    }
                });
                txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscar2KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(398, Short.MAX_VALUE))
                );
                jPanel17Layout.setVerticalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel74)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnbuscar3)))
                        .addGap(408, 408, 408))
                );

                dlgBuscarCPT.getContentPane().add(jPanel17);
                jPanel17.setBounds(0, 0, 770, 104);

                jScrollPane21.setBorder(null);

                tb_Grupo4.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Grupo4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_Grupo4.setGridColor(new java.awt.Color(255, 255, 255));
                tb_Grupo4.setRowHeight(25);
                tb_Grupo4.setSelectionBackground(new java.awt.Color(255, 65, 26));
                tb_Grupo4.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Grupo4MouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        tb_Grupo4MouseEntered(evt);
                    }
                });
                tb_Grupo4.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Grupo4KeyPressed(evt);
                    }
                });
                jScrollPane21.setViewportView(tb_Grupo4);

                dlgBuscarCPT.getContentPane().add(jScrollPane21);
                jScrollPane21.setBounds(0, 110, 743, 200);

                BuscarHospitalizacion.setAlwaysOnTop(true);
                BuscarHospitalizacion.setMinimumSize(new java.awt.Dimension(615, 333));
                BuscarHospitalizacion.setResizable(false);

                jPanel7.setBackground(new java.awt.Color(102, 102, 102));

                jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(255, 255, 255));
                jLabel30.setText("Pacientes Hospitalizados");

                jPanel27.setBackground(new java.awt.Color(255, 255, 255));

                txtPaciente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtPaciente.setBorder(null);
                txtPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPacienteCaretUpdate(evt);
                    }
                });
                txtPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtPacienteMouseClicked(evt);
                    }
                });
                txtPaciente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPacienteActionPerformed(evt);
                    }
                });
                txtPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPacienteKeyPressed(evt);
                    }
                });

                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarMouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                jPanel27.setLayout(jPanel27Layout);
                jPanel27Layout.setHorizontalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel27Layout.setVerticalGroup(
                    jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar)
                            .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel20.setBackground(new java.awt.Color(255, 73, 7));

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 615, Short.MAX_VALUE)
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 10, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                jScrollPane7.setBorder(null);

                tbPacientesHospitalizados.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbPacientesHospitalizados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbPacientesHospitalizados.setGridColor(new java.awt.Color(255, 255, 255));
                tbPacientesHospitalizados.setRowHeight(25);
                tbPacientesHospitalizados.setSelectionBackground(new java.awt.Color(102, 102, 102));
                tbPacientesHospitalizados.getTableHeader().setReorderingAllowed(false);
                tbPacientesHospitalizados.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbPacientesHospitalizadosMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbPacientesHospitalizadosMousePressed(evt);
                    }
                });
                tbPacientesHospitalizados.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbPacientesHospitalizadosKeyPressed(evt);
                    }
                });
                jScrollPane7.setViewportView(tbPacientesHospitalizados);

                javax.swing.GroupLayout BuscarHospitalizacionLayout = new javax.swing.GroupLayout(BuscarHospitalizacion.getContentPane());
                BuscarHospitalizacion.getContentPane().setLayout(BuscarHospitalizacionLayout);
                BuscarHospitalizacionLayout.setHorizontalGroup(
                    BuscarHospitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                );
                BuscarHospitalizacionLayout.setVerticalGroup(
                    BuscarHospitalizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarHospitalizacionLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel8.setBackground(new java.awt.Color(255, 73, 7));
                jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

                titulo5.setBackground(new java.awt.Color(153, 0, 51));
                titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                titulo5.setText("Nota de Enfermeria");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                btnBuscarPaciente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnBuscarPaciente.setForeground(new java.awt.Color(255, 255, 255));
                btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-32.png"))); // NOI18N
                btnBuscarPaciente.setText("Buscar");
                btnBuscarPaciente.setToolTipText("");
                btnBuscarPaciente.setContentAreaFilled(false);
                btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarPaciente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnBuscarPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnBuscarPaciente.setIconTextGap(30);
                btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarPacienteActionPerformed(evt);
                    }
                });

                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                btnNuevo.setText("Nuevo");
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setEnabled(false);
                btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnNuevo.setIconTextGap(30);
                btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnGuardar.setText("Guardar");
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setEnabled(false);
                btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnGuardar.setIconTextGap(30);
                btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                btnEliminar.setText("Eliminar");
                btnEliminar.setContentAreaFilled(false);
                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnEliminar.setEnabled(false);
                btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnEliminar.setIconTextGap(30);
                btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnEliminarActionPerformed(evt);
                    }
                });

                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                lblusu.setText("Silvana");
                lblusu.setFocusable(false);
                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                lblMant.setText("jLabel1");

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(lblMant)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblMant)
                        .addGap(52, 52, 52)
                        .addComponent(btnBuscarPaciente)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu))
                );

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jPanel4.setBackground(new java.awt.Color(255, 65, 26));

                btnAgregarProcedimiento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnAgregarProcedimiento.setForeground(new java.awt.Color(255, 255, 255));
                btnAgregarProcedimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-32.png"))); // NOI18N
                btnAgregarProcedimiento.setMnemonic('B');
                btnAgregarProcedimiento.setText("Agregar Procedimiento");
                btnAgregarProcedimiento.setToolTipText("Buscar Paciente (Alt + B)");
                btnAgregarProcedimiento.setBorderPainted(false);
                btnAgregarProcedimiento.setContentAreaFilled(false);
                btnAgregarProcedimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAgregarProcedimiento.setEnabled(false);
                btnAgregarProcedimiento.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                btnAgregarProcedimiento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAgregarProcedimientoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnAgregarProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarProcedimiento)
                );

                txtAnotacionesMedicas.setEditable(false);
                txtAnotacionesMedicas.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtAnotacionesMedicasKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtAnotacionesMedicasKeyTyped(evt);
                    }
                });
                jScrollPane5.setViewportView(txtAnotacionesMedicas);

                jPanel5.setBackground(new java.awt.Color(127, 121, 121));

                jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                jLabel3.setText("Signos Vitales");

                jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("PA:");

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPA.setEditable(false);
                txtPA.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtPA.setForeground(new java.awt.Color(51, 51, 51));
                txtPA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPA.setToolTipText("");
                txtPA.setBorder(null);
                txtPA.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPACaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
                jLabel5.setText("FR:");

                jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtFR.setEditable(false);
                txtFR.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtFR.setForeground(new java.awt.Color(51, 51, 51));
                txtFR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtFR.setToolTipText("");
                txtFR.setBorder(null);
                txtFR.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtFRCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("FC:");

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtFC.setEditable(false);
                txtFC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtFC.setForeground(new java.awt.Color(51, 51, 51));
                txtFC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtFC.setToolTipText("");
                txtFC.setBorder(null);
                txtFC.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtFCCaretUpdate(evt);
                    }
                });
                txtFC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtFCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                jLabel13.setText("Tº:");

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtT.setEditable(false);
                txtT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtT.setForeground(new java.awt.Color(51, 51, 51));
                txtT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtT.setToolTipText("");
                txtT.setBorder(null);
                txtT.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel14.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                jLabel14.setText("Peso:");

                jPanel14.setBackground(new java.awt.Color(255, 255, 255));
                jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPeso.setEditable(false);
                txtPeso.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtPeso.setForeground(new java.awt.Color(51, 51, 51));
                txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPeso.setToolTipText("");
                txtPeso.setBorder(null);
                txtPeso.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPesoCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setText("Talla:");

                jPanel15.setBackground(new java.awt.Color(255, 255, 255));
                jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtTalla.setEditable(false);
                txtTalla.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtTalla.setForeground(new java.awt.Color(51, 51, 51));
                txtTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTalla.setToolTipText("");
                txtTalla.setBorder(null);
                txtTalla.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTallaCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel17.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
                jLabel17.setForeground(new java.awt.Color(255, 255, 255));
                jLabel17.setText("IDM:");

                jPanel16.setBackground(new java.awt.Color(255, 255, 255));
                jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtIDM.setEditable(false);
                txtIDM.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtIDM.setForeground(new java.awt.Color(51, 51, 51));
                txtIDM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtIDM.setToolTipText("");
                txtIDM.setBorder(null);
                txtIDM.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIDMCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel6)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                txtPaciente1.setFont(new java.awt.Font("Segoe UI", 0, 19)); // NOI18N
                txtPaciente1.setForeground(new java.awt.Color(43, 43, 43));
                txtPaciente1.setText("Anotaciones de Enfermería");

                lblProcedimientos.setFont(new java.awt.Font("Segoe UI", 0, 19)); // NOI18N
                lblProcedimientos.setForeground(new java.awt.Color(43, 43, 43));
                lblProcedimientos.setText("Procedimientos Terapéuticos");

                jScrollPane6.setBorder(null);

                tbNotas = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tbNotas.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Fecha", "Hora", ""
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbNotas.setSelectionBackground(new java.awt.Color(218, 209, 195));
                tbNotas.setSelectionForeground(new java.awt.Color(0, 0, 0));
                tbNotas.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbNotasMouseClicked(evt);
                    }
                });
                tbNotas.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbNotasKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbNotasKeyReleased(evt);
                    }
                });
                jScrollPane6.setViewportView(tbNotas);

                jScrollPane8.setBorder(null);

                tbProced = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tbProced.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tbProced.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Código CPT", "Nomenclatura", "ID"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbProced.setSelectionBackground(new java.awt.Color(255, 73, 7));
                tbProced.setSelectionForeground(new java.awt.Color(204, 204, 204));
                tbProced.getTableHeader().setReorderingAllowed(false);
                tbProced.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbProcedMouseClicked(evt);
                    }
                });
                tbProced.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbProcedKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbProcedKeyReleased(evt);
                    }
                });
                jScrollPane8.setViewportView(tbProced);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPaciente1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblProcedimientos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(lblProcedimientos)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaciente1)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addComponent(jScrollPane6)
                );

                pnlCabecera.setBackground(new java.awt.Color(43, 43, 43));

                lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                lblPaciente.setForeground(new java.awt.Color(255, 255, 255));
                lblPaciente.setText("Paciente");

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(204, 204, 204));
                jLabel9.setText("Acto Médico");

                lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
                lblActoMedico.setText("Nº");

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(204, 204, 204));
                jLabel11.setText("DNI");

                lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                lblDNI.setText("DNI");

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(204, 204, 204));
                jLabel12.setText("Nº H.C.");

                lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lblHC.setForeground(new java.awt.Color(204, 204, 204));
                lblHC.setText("H.C.");

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(204, 204, 204));
                jLabel8.setText("Nº de Cama:");

                lblNroCama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lblNroCama.setForeground(new java.awt.Color(204, 204, 204));
                lblNroCama.setText("NroCama");

                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel18.setForeground(new java.awt.Color(204, 204, 204));
                jLabel18.setText("Servicio");

                lblServicio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lblServicio.setForeground(new java.awt.Color(204, 204, 204));
                lblServicio.setText("Servicio");

                txtIdPreventa.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIdPreventaCaretUpdate(evt);
                    }
                });

                txtNeId.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNeIdCaretUpdate(evt);
                    }
                });

                lblId.setText("jLabel1");

                javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(pnlCabecera);
                pnlCabecera.setLayout(pnlCabeceraLayout);
                pnlCabeceraLayout.setHorizontalGroup(
                    pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCabeceraLayout.createSequentialGroup()
                                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(67, 67, 67)
                                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNroCama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lblPaciente))
                        .addGap(43, 43, 43)
                        .addComponent(txtIdPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtNeId, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(lblId)
                        .addGap(135, 135, 135))
                );
                pnlCabeceraLayout.setVerticalGroup(
                    pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCabeceraLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPaciente)
                        .addGap(5, 5, 5)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblActoMedico)
                            .addComponent(jLabel8)
                            .addComponent(lblNroCama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(lblServicio))
                            .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblDNI)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHC)
                            .addComponent(jLabel12)
                            .addComponent(txtIdPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblId))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                pnlMensajeLayout.setVerticalGroup(
                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProcedimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProcedimientoActionPerformed
        dlgBuscarCPT.setLocationRelativeTo(null);//en el centro
        dlgBuscarCPT.setResizable(false);
        dlgBuscarCPT.getContentPane().setBackground(Color.WHITE);
        dlgBuscarCPT.setVisible(true);
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        cajaNomen.notaEnfermeriaProcedimientos(txtBuscar2.getText(), tb_Grupo4);
    }//GEN-LAST:event_btnAgregarProcedimientoActionPerformed

    private void txtPACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPACaretUpdate
        
    }//GEN-LAST:event_txtPACaretUpdate

    private void txtFRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFRCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFRCaretUpdate

    private void txtFCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFCCaretUpdate

    private void txtTCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTCaretUpdate

    private void txtPesoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPesoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCaretUpdate

    private void txtTallaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTallaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaCaretUpdate

    private void txtIDMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIDMCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMCaretUpdate

    private void txtFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFCActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        //        if(btnSi.getText().equals("Si")){ // Al guardar
            //            mantenimientoAtencionPrenatal();
            //        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        buscarPaciente();
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            lblMant.setText("I");
            btnBuscarPaciente.setEnabled(true);
            btnAgregarProcedimiento.setEnabled(true);
            habilitarCampos(true);
            limpiar();
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        if(txtPA.getText().equals("") && txtFC.getText().equals("") &&
           txtFR.getText().equals("") && txtIDM.getText().equals("") &&
           txtPeso.getText().equals("") && txtTalla.getText().equals("") &&
           txtT.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese los signos vitales");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
        } else
            mantenimientoNotaEnfermeria();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        mantenimientoNotaEnfermeria();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        Caja_Nomenclatura cajaNomen = new Caja_Nomenclatura();
        cajaNomen.notaEnfermeriaProcedimientos(txtBuscar2.getText(), tb_Grupo4);
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Grupo4.getSelectionModel().setSelectionInterval(0, 0);
            tb_Grupo4.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
        if(evt.getClickCount()==2)
            enviarProcedimientos(tb_Grupo4, tbProced);
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tb_Grupo4.getSelectedRow()==0){
            txtBuscar2.requestFocus();
            tb_Grupo4.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarProcedimientos(tb_Grupo4, tbProced);
        }
    }//GEN-LAST:event_tb_Grupo4KeyPressed

    private void txtPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPacienteCaretUpdate
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.listarPapeleta("H", txtPaciente.getText(), tbPacientesHospitalizados,"C");
    }//GEN-LAST:event_txtPacienteCaretUpdate

    private void txtPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPacienteMouseClicked

    }//GEN-LAST:event_txtPacienteMouseClicked

    private void txtPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPacienteActionPerformed

    private void txtPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPacienteKeyPressed

    }//GEN-LAST:event_txtPacienteKeyPressed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked

    }//GEN-LAST:event_btnBuscarMouseClicked

    private void tbPacientesHospitalizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesHospitalizadosMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesHospitalizadosMouseClicked

    private void tbPacientesHospitalizadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesHospitalizadosMousePressed

    }//GEN-LAST:event_tbPacientesHospitalizadosMousePressed

    private void tbPacientesHospitalizadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesHospitalizadosKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbPacientesHospitalizados.getSelectedRow()==0){
            lblPaciente.requestFocus();
            tbPacientesHospitalizados.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesHospitalizadosKeyPressed

    private void tbNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNotasMouseClicked
        if(evt.getClickCount()==1){
            enviarDatosNotasEnf();
            int fila = tbNotas.getSelectedRow();
            validaTurno(String.valueOf(tbNotas.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_tbNotasMouseClicked

    private void tbNotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNotasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbNotasKeyPressed

    private void tbNotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNotasKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tbNotas.getSelectedRow();
            enviarDatosNotasEnf();
            validaTurno(String.valueOf(tbNotas.getValueAt(fila, 0)));
        }
    }//GEN-LAST:event_tbNotasKeyReleased

    private void tb_Grupo4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo4MouseEntered

    private void tbProcedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedMouseClicked

    private void tbProcedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedKeyPressed

    private void tbProcedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedKeyReleased

    private void txtEstadia5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadia5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadia5ActionPerformed

    private void txtAnotacionesMedicasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnotacionesMedicasKeyTyped
        // TODO add your handling code here
    }//GEN-LAST:event_txtAnotacionesMedicasKeyTyped

    private void txtAnotacionesMedicasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnotacionesMedicasKeyReleased
        txtAnotacionesMedicas.setText(txtAnotacionesMedicas.getText().toUpperCase());
    }//GEN-LAST:event_txtAnotacionesMedicasKeyReleased

    private void txtIdPreventaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdPreventaCaretUpdate
        HospitalizacionNotaEnfermeria hospitalizacion = new HospitalizacionNotaEnfermeria();
        hospitalizacion.listarProcedimientos(txtIdPreventa.getText(), tbNotas);
    }//GEN-LAST:event_txtIdPreventaCaretUpdate

    private void txtNeIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNeIdCaretUpdate
        HospitalizacionNotaEnfermeriaProcedimiento hospitalizacion = new HospitalizacionNotaEnfermeriaProcedimiento();
        hospitalizacion.listarProcedimientos(txtNeId.getText(), tbProced);
    }//GEN-LAST:event_txtNeIdCaretUpdate

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
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionNotaEnfermeria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionNotaEnfermeria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarHospitalizacion;
    private javax.swing.JButton btnAgregarProcedimiento;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnBuscarPaciente;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JDialog dlgBuscarCPT;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblHC;
    public static javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNroCama;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblProcedimientos;
    private javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlCabecera;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbNotas;
    private javax.swing.JTable tbPacientesHospitalizados;
    private javax.swing.JTable tbProced;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JLabel titulo5;
    private javax.swing.JEditorPane txtAnotacionesMedicas;
    private javax.swing.JTextField txtBuscar2;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    private javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtIDM;
    private javax.swing.JTextField txtIdPreventa;
    public static javax.swing.JTextField txtNeId;
    public static javax.swing.JTextField txtPA;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JLabel txtPaciente1;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtT;
    public static javax.swing.JTextField txtTalla;
    // End of variables declaration//GEN-END:variables
}
