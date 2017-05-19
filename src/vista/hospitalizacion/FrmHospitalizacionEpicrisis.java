/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtConsultorioDiagnostico;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.hospitalizacion.HospitalizacionEpicrisis;
import modelos.hospitalizacion.HospitalizacionEpicrisisDiagnosticosEgreso;
import modelos.hospitalizacion.HospitalizacionExamenClinico;
import modelos.hospitalizacion.HospitalizacionNotaEnfermeria;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroSeguimiento;
import static vista.Principal.fechaActual;
import vista.SALA_OPERACIONES.frm_SALA_OPERACION_CONDICION;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.tbListarPapeleta;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.txtBuscarr;
import static vista.hospitalizacion.FrmHospitalizacionNotaEnfermeria.txtPA;

/**
 *
 * @author MYS1
 */
public class FrmHospitalizacionEpicrisis extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    Thread h1;
    public FrmHospitalizacionEpicrisis() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        pnlMensaje.setVisible(false);
        h1 = new Thread(this);
        h1.start();Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFechaE.setText(fechaActual());
        lblMant.setVisible(false);
        lblId.setVisible(false);
        txtId.setVisible(false);
        txtIdPreventa.setVisible(false);
        LimitadorDeDocumento limitInfo = new LimitadorDeDocumento(300);
        txtInfo.setDocument(limitInfo);
        LimitadorDeDocumento limitComplicaciones = new LimitadorDeDocumento(300);
        txtComplicaciones.setDocument(limitComplicaciones);
        LimitadorDeDocumento limitPronostico = new LimitadorDeDocumento(40);
        txtPronosticoAlta.setDocument(limitPronostico);
        LimitadorDeDocumento limitCondicion = new LimitadorDeDocumento(40);
        txtCondicionEgreso.setDocument(limitCondicion);
    }

    public void habilitarCampos(boolean opcion){
        btnDiagEgreso.setEnabled(opcion);
        txtComplicaciones.setEditable(opcion);
        cbxTipoAlta.setEnabled(opcion);
        txtCondicionEgreso.setEditable(opcion);
        txtPronosticoAlta.setEditable(opcion);
    }
    
    public void enviarDatosPaciente(){
        int fila = tbPacientesHospitalizados.getSelectedRow();
        txtIdPreventa.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 0)));
        lblActoMedico.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 2)));
        lblHC.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 3)));
        lblDNI.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 4)));
        lblPaciente.setText("Paciente: " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 5)));
        lblFecha.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 17)) + " " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 18)));
        lblServicio.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 8)) + " Nº de Cama " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 11)));
        BuscarHospitalizacion.dispose();
        lblMant.setText("I");
        btnGuardar.setEnabled(true);
        habilitarCampos(true);
    }
    
    public void enviarDatosEpicrisis(){
        int fila = tbEpicrisis.getSelectedRow();
        txtIdPreventa.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 0)));
        lblActoMedico.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 2)));
        lblDNI.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 3)));
        lblHC.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 4)));
        lblPaciente.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 5)));
        lblFecha.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 9)) + " " + String.valueOf(tbEpicrisis.getValueAt(fila, 10)));
        lblServicio.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 14)) + " Nº de Cama " + String.valueOf(tbEpicrisis.getValueAt(fila, 6)));
        lblFechaE.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 12)));
        lblHoraE.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 13)));
        txtId.setText(String.valueOf(tbEpicrisis.getValueAt(fila, 11)));
        FrmReporteEpicrisis.dispose();
        lblMant.setText("U");
        //EVALUAR SI PERTENECE AL DI Y AL MEDICO QUE LO REGISTRO
        //
        habilitarCampos(true);
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
    
    public void enviarDiagnosticos(JTable tablaEnvia, JTable tablaRecibe){
        m = (DefaultTableModel) tablaRecibe.getModel();
        int fila = tablaEnvia.getSelectedRow();
        if(tablaRecibe.getColumnCount()==0){
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            FrmCie10.dispose();
            //btnQuitarDiag.setEnabled(true);
        } else
        if(repiteDiagImp(tablaEnvia,tablaRecibe)==true)
            JOptionPane.showMessageDialog(FrmCie10, "Diagnóstico ya registrado");
        else {
            //PASAR DATOS A TABLA
            String diagnosticos[] = {
            String.valueOf(tablaEnvia.getValueAt(fila, 0)),
            String.valueOf(tablaEnvia.getValueAt(fila, 1)),
            String.valueOf(tablaEnvia.getValueAt(fila, 2))};
            m.addRow(diagnosticos);
            formatotbDiagnosticos(tablaRecibe);
            FrmCie10.dispose();
            //btnQuitarDiag.setEnabled(true);
        }
    }
    
    public boolean guardarDiagnosticosEgreso(){
        AdmisionEmergenciaCabecera adEmer2 = new AdmisionEmergenciaCabecera();
        boolean retorna = false;
        try {
        int id = Integer.parseInt(lblId.getText());
            for (int i = 0; i < tbDiagnosticoEgreso.getRowCount(); i++){      
                HospitalizacionEpicrisisDiagnosticosEgreso hospitalizacion = new HospitalizacionEpicrisisDiagnosticosEgreso();
                hospitalizacion.setHhId(id);
                hospitalizacion.setIdCie10(Integer.parseInt(tbDiagnosticoEgreso.getValueAt(i,0).toString()));
                hospitalizacion.setCodUsu(adEmer2.codUsuario(lblusu.getText()));
                if(hospitalizacion.mantenimientoHospitalizacionEpicrisisDiagnosticosEgreso(lblMant.getText())==true)
                    retorna = true;
            }
        } catch (Exception e) {
            System.out.println("Error: guardarDiagnosticosEgreso " + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoHospitalizacionEpicrisis(){
        boolean retorna = false;
        try {
            HospitalizacionEpicrisis hospitalizacion = new HospitalizacionEpicrisis();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("E"))
                hospitalizacion.setHhId(Integer.parseInt(txtId.getText()));
            hospitalizacion.setIdPreventa(Integer.parseInt(txtIdPreventa.getText()));
            hospitalizacion.setHhComplicaciones(txtComplicaciones.getText());
            hospitalizacion.setHhTipoAlta(cbxTipoAlta.getSelectedItem().toString().toUpperCase());
            hospitalizacion.setHhCondicionEgreso(txtCondicionEgreso.getText());
            hospitalizacion.setHhPronosticoAlta(txtPronosticoAlta.getText());
            hospitalizacion.setHhInfMortalidad(txtInfo.getText());
            hospitalizacion.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(hospitalizacion.mantenimientoHospitalizacionEpicrisis(lblMant.getText())==true){
                hospitalizacion.epicrisisID();
                if(lblMant.getText().equals("I")){
                    lblMant.setText("I");
                }
                if(tbDiagnosticoEgreso.getRowCount()!=0){
                    if(guardarDiagnosticosEgreso()){    
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
//                        limpiar();
                    } else {
                        pnlMensaje.setVisible(true);
                        lblMensaje.setText("Ocurrió un error, al guardar el procedimiento");
                        pnlMensaje.setBackground(new Color(255,91,70));
                        btnSi.setVisible(false);
                        btnNo.setVisible(false);
                    }
                } else {
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
//                        limpiar();
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarHospitalizacion = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        txtPaciente = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbPacientesHospitalizados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            FrmCie10 = new javax.swing.JDialog();
            jPanel14 = new javax.swing.JPanel();
            titulo7 = new javax.swing.JLabel();
            jLabel36 = new javax.swing.JLabel();
            jPanel30 = new javax.swing.JPanel();
            T7 = new javax.swing.JLabel();
            txtBuscarCie10 = new javax.swing.JTextField();
            jPanel32 = new javax.swing.JPanel();
            jScrollPane8 = new javax.swing.JScrollPane();
            tbCie10 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                FrmReporteEpicrisis = new javax.swing.JDialog();
                jPanel5 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                lblusu1 = new javax.swing.JLabel();
                btnActualizar = new javax.swing.JButton();
                jPanel9 = new javax.swing.JPanel();
                txtBuscarEpicrisis = new javax.swing.JTextField();
                T3 = new javax.swing.JButton();
                jPanel38 = new javax.swing.JPanel();
                jLabel34 = new javax.swing.JLabel();
                btnImprimir = new javax.swing.JButton();
                jScrollPane12 = new javax.swing.JScrollPane();
                tbEpicrisis = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel11 = new javax.swing.JPanel();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    lblusu = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
                    btnGuardar = new javax.swing.JButton();
                    btnBuscarPaciente = new javax.swing.JButton();
                    btnEliminar = new javax.swing.JButton();
                    btnEditar = new javax.swing.JButton();
                    lblMant = new javax.swing.JLabel();
                    jPanel2 = new javax.swing.JPanel();
                    lblPaciente = new javax.swing.JLabel();
                    jLabel3 = new javax.swing.JLabel();
                    lblActoMedico = new javax.swing.JLabel();
                    jLabel5 = new javax.swing.JLabel();
                    lblDNI = new javax.swing.JLabel();
                    jLabel4 = new javax.swing.JLabel();
                    lblHC = new javax.swing.JLabel();
                    txtIdPreventa = new javax.swing.JTextField();
                    txtId = new javax.swing.JTextField();
                    jLabel9 = new javax.swing.JLabel();
                    lblServicio = new javax.swing.JLabel();
                    lblId = new javax.swing.JLabel();
                    jScrollPane9 = new javax.swing.JScrollPane();
                    jPanel4 = new javax.swing.JPanel();
                    jLabel2 = new javax.swing.JLabel();
                    jLabel21 = new javax.swing.JLabel();
                    jScrollPane10 = new javax.swing.JScrollPane();
                    tbDiagRealizados = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jScrollPane11 = new javax.swing.JScrollPane();
                        txtProcedTerapeuticos = new javax.swing.JEditorPane();
                        jScrollPane4 = new javax.swing.JScrollPane();
                        jPanel3 = new javax.swing.JPanel();
                        jLabel10 = new javax.swing.JLabel();
                        jLabel13 = new javax.swing.JLabel();
                        jPanel13 = new javax.swing.JPanel();
                        txtPronosticoAlta = new javax.swing.JTextField();
                        jLabel8 = new javax.swing.JLabel();
                        jPanel10 = new javax.swing.JPanel();
                        txtEstadia = new javax.swing.JTextField();
                        jLabel19 = new javax.swing.JLabel();
                        jLabel18 = new javax.swing.JLabel();
                        jScrollPane2 = new javax.swing.JScrollPane();
                        txtComplicaciones = new javax.swing.JEditorPane();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        txtInfo = new javax.swing.JEditorPane();
                        jLabel7 = new javax.swing.JLabel();
                        jLabel17 = new javax.swing.JLabel();
                        jScrollPane5 = new javax.swing.JScrollPane();
                        tbDiagnosticoEgreso = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jLabel16 = new javax.swing.JLabel();
                            jScrollPane3 = new javax.swing.JScrollPane();
                            tbDiagIngreso = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                lblFecha = new javax.swing.JLabel();
                                jScrollPane1 = new javax.swing.JScrollPane();
                                txtEnfActual = new javax.swing.JEditorPane();
                                jLabel12 = new javax.swing.JLabel();
                                jPanel12 = new javax.swing.JPanel();
                                txtCondicionEgreso = new javax.swing.JTextField();
                                jLabel14 = new javax.swing.JLabel();
                                jLabel15 = new javax.swing.JLabel();
                                lblFechaE = new javax.swing.JLabel();
                                btnDiagEgreso = new javax.swing.JButton();
                                cbxTipoAlta = new javax.swing.JComboBox();
                                lblHoraE = new javax.swing.JLabel();
                                pnlMensaje = new javax.swing.JPanel();
                                lblMensaje = new javax.swing.JLabel();
                                btnSi = new javax.swing.JButton();
                                btnNo = new javax.swing.JButton();

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

                                jPanel8.setBackground(new java.awt.Color(255, 73, 7));

                                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                                jPanel8.setLayout(jPanel8Layout);
                                jPanel8Layout.setHorizontalGroup(
                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 615, Short.MAX_VALUE)
                                );
                                jPanel8Layout.setVerticalGroup(
                                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                );
                                jPanel7Layout.setVerticalGroup(
                                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                        .addContainerGap(15, Short.MAX_VALUE)
                                        .addComponent(jLabel30)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                                FrmCie10.setMinimumSize(new java.awt.Dimension(750, 400));
                                FrmCie10.setResizable(false);

                                jPanel14.setBackground(new java.awt.Color(102, 102, 102));
                                jPanel14.setPreferredSize(new java.awt.Dimension(500, 65));

                                titulo7.setBackground(new java.awt.Color(153, 0, 51));
                                titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                titulo7.setForeground(new java.awt.Color(255, 255, 255));
                                titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                titulo7.setText("CIE 10");
                                titulo7.setToolTipText("");
                                titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel36.setText("Código CIE 10 / Diagnóstico de Egreso");

                                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                                T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                                T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                T7.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        T7MouseClicked(evt);
                                    }
                                });

                                txtBuscarCie10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                txtBuscarCie10.setBorder(null);
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

                                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                                jPanel30.setLayout(jPanel30Layout);
                                jPanel30Layout.setHorizontalGroup(
                                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBuscarCie10, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0))
                                );
                                jPanel30Layout.setVerticalGroup(
                                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel30Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                                .addComponent(T7)
                                                .addGap(4, 4, 4))
                                            .addComponent(txtBuscarCie10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                );

                                jPanel32.setBackground(new java.awt.Color(255, 73, 7));

                                javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                                jPanel32.setLayout(jPanel32Layout);
                                jPanel32Layout.setHorizontalGroup(
                                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 750, Short.MAX_VALUE)
                                );
                                jPanel32Layout.setVerticalGroup(
                                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 16, Short.MAX_VALUE)
                                );

                                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                                jPanel14.setLayout(jPanel14Layout);
                                jPanel14Layout.setHorizontalGroup(
                                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(449, 449, Short.MAX_VALUE))
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel14Layout.setVerticalGroup(
                                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(titulo7)
                                        .addGap(9, 9, 9)
                                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel14Layout.createSequentialGroup()
                                            .addGap(127, 127, 127)
                                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(184, Short.MAX_VALUE)))
                                );

                                jScrollPane8.setBorder(null);

                                tbCie10.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbCie10.setGridColor(new java.awt.Color(255, 255, 255));
                                tbCie10.setRowHeight(25);
                                tbCie10.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                tbCie10.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbCie10MouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbCie10MousePressed(evt);
                                    }
                                });
                                tbCie10.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbCie10KeyPressed(evt);
                                    }
                                });
                                jScrollPane8.setViewportView(tbCie10);

                                javax.swing.GroupLayout FrmCie10Layout = new javax.swing.GroupLayout(FrmCie10.getContentPane());
                                FrmCie10.getContentPane().setLayout(FrmCie10Layout);
                                FrmCie10Layout.setHorizontalGroup(
                                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                                    .addComponent(jScrollPane8)
                                );
                                FrmCie10Layout.setVerticalGroup(
                                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FrmCie10Layout.createSequentialGroup()
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                                );

                                FrmReporteEpicrisis.setMinimumSize(new java.awt.Dimension(1300, 650));
                                FrmReporteEpicrisis.setPreferredSize(new java.awt.Dimension(1300, 650));
                                FrmReporteEpicrisis.setResizable(false);

                                jPanel5.setBackground(new java.awt.Color(255, 73, 7));

                                jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel6.setText("<html>Reporte<br>de Altas</html>");

                                lblusu1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                lblusu1.setForeground(new java.awt.Color(255, 255, 255));
                                lblusu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                lblusu1.setText("Silvana");
                                lblusu1.setFocusable(false);
                                lblusu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                btnActualizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnActualizar.setForeground(new java.awt.Color(240, 240, 240));
                                btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/actualizar.png"))); // NOI18N
                                btnActualizar.setText("Actualizar");
                                btnActualizar.setContentAreaFilled(false);
                                btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnActualizar.setIconTextGap(30);
                                btnActualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btnActualizar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnActualizarActionPerformed(evt);
                                    }
                                });

                                jPanel9.setBackground(new java.awt.Color(255, 73, 7));

                                txtBuscarEpicrisis.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtBuscarEpicrisis.setForeground(new java.awt.Color(51, 51, 51));
                                txtBuscarEpicrisis.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtBuscarEpicrisis.setToolTipText("");
                                txtBuscarEpicrisis.setBorder(null);
                                txtBuscarEpicrisis.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarEpicrisisCaretUpdate(evt);
                                    }
                                });

                                T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                T3.setToolTipText("");
                                T3.setContentAreaFilled(false);
                                T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                T3.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        T3ActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                jPanel9.setLayout(jPanel9Layout);
                                jPanel9Layout.setHorizontalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtBuscarEpicrisis, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                );
                                jPanel9Layout.setVerticalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtBuscarEpicrisis, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                            .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jPanel38.setBackground(new java.awt.Color(228, 94, 37));

                                jLabel34.setBackground(new java.awt.Color(255, 73, 7));
                                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel34.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
                                jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jLabel34MouseClicked(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                                jPanel38.setLayout(jPanel38Layout);
                                jPanel38Layout.setHorizontalGroup(
                                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                );
                                jPanel38Layout.setVerticalGroup(
                                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel38Layout.createSequentialGroup()
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                );

                                btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnImprimir.setForeground(new java.awt.Color(240, 240, 240));
                                btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Print-32 (2).png"))); // NOI18N
                                btnImprimir.setText("Imprimir");
                                btnImprimir.setContentAreaFilled(false);
                                btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnImprimir.setIconTextGap(30);
                                btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnImprimirActionPerformed(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                jPanel5.setLayout(jPanel5Layout);
                                jPanel5Layout.setHorizontalGroup(
                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblusu1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel5Layout.setVerticalGroup(
                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(btnActualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnImprimir)
                                        .addGap(26, 26, 26)
                                        .addComponent(lblusu1)
                                        .addGap(130, 130, 130))
                                );

                                jScrollPane12.setBorder(null);

                                tbEpicrisis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbEpicrisis.setForeground(new java.awt.Color(102, 102, 102));
                                tbEpicrisis.setModel(new javax.swing.table.DefaultTableModel(
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
                                tbEpicrisis.setGridColor(new java.awt.Color(255, 255, 255));
                                tbEpicrisis.setRowHeight(25);
                                tbEpicrisis.setSelectionBackground(new java.awt.Color(255, 73, 7));
                                tbEpicrisis.getTableHeader().setReorderingAllowed(false);
                                tbEpicrisis.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbEpicrisisMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbEpicrisisMousePressed(evt);
                                    }
                                });
                                tbEpicrisis.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbEpicrisisKeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tbEpicrisisKeyReleased(evt);
                                    }
                                });
                                jScrollPane12.setViewportView(tbEpicrisis);

                                jPanel11.setBackground(new java.awt.Color(43, 43, 43));

                                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                                jPanel11.setLayout(jPanel11Layout);
                                jPanel11Layout.setHorizontalGroup(
                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 0, Short.MAX_VALUE)
                                );
                                jPanel11Layout.setVerticalGroup(
                                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGap(0, 107, Short.MAX_VALUE)
                                );

                                javax.swing.GroupLayout FrmReporteEpicrisisLayout = new javax.swing.GroupLayout(FrmReporteEpicrisis.getContentPane());
                                FrmReporteEpicrisis.getContentPane().setLayout(FrmReporteEpicrisisLayout);
                                FrmReporteEpicrisisLayout.setHorizontalGroup(
                                    FrmReporteEpicrisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FrmReporteEpicrisisLayout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(FrmReporteEpicrisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(FrmReporteEpicrisisLayout.createSequentialGroup()
                                                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                                                .addContainerGap())
                                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                );
                                FrmReporteEpicrisisLayout.setVerticalGroup(
                                    FrmReporteEpicrisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(FrmReporteEpicrisisLayout.createSequentialGroup()
                                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane12)
                                        .addContainerGap())
                                );

                                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                                jPanel1.setBackground(new java.awt.Color(255, 73, 7));

                                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                jLabel1.setText("EPICRISIS");

                                lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                lblusu.setText("Silvana");
                                lblusu.setFocusable(false);
                                lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                                btnNuevo.setText("<html>Reportes<br><p style=\"font-size:'14px'\">de Alta</p></html>");
                                btnNuevo.setContentAreaFilled(false);
                                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

                                btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnEditar.setForeground(new java.awt.Color(240, 240, 240));
                                btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                                btnEditar.setText("Editar");
                                btnEditar.setContentAreaFilled(false);
                                btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnEditar.setEnabled(false);
                                btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnEditar.setIconTextGap(30);
                                btnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btnEditar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnEditarActionPerformed(evt);
                                    }
                                });

                                lblMant.setText("jLabel6");

                                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                jPanel1.setLayout(jPanel1Layout);
                                jPanel1Layout.setHorizontalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(lblMant))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnEliminar)
                                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel1Layout.setVerticalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblMant)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarPaciente)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGuardar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblusu))
                                );

                                jPanel2.setBackground(new java.awt.Color(43, 43, 43));

                                lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                                lblPaciente.setForeground(new java.awt.Color(255, 255, 255));
                                lblPaciente.setText("Paciente");

                                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel3.setText("Acto Médico");

                                lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));

                                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel5.setText("DNI");

                                lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblDNI.setForeground(new java.awt.Color(204, 204, 204));

                                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel4.setText("Nº H.C.");

                                lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblHC.setForeground(new java.awt.Color(204, 204, 204));

                                txtIdPreventa.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtIdPreventaCaretUpdate(evt);
                                    }
                                });

                                txtId.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtIdCaretUpdate(evt);
                                    }
                                });

                                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel9.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel9.setText("Servicio y Numero de Cama");

                                lblServicio.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblServicio.setForeground(new java.awt.Color(204, 204, 204));

                                lblId.setForeground(new java.awt.Color(255, 255, 255));
                                lblId.setText("jLabel6");

                                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                jPanel2.setLayout(jPanel2Layout);
                                jPanel2Layout.setHorizontalGroup(
                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel4))
                                                .addGap(67, 67, 67)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel9)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(456, 456, 456)
                                                        .addComponent(lblId)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblPaciente)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtIdPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(189, 189, 189))))
                                );
                                jPanel2Layout.setVerticalGroup(
                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblPaciente)
                                            .addComponent(txtIdPreventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel9))
                                            .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDNI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5)
                                                .addComponent(lblId)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addContainerGap())
                                );

                                jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
                                jScrollPane9.setBorder(null);
                                jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel2.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                jLabel2.setText("Procedimientos Terapéuticos - Notas de Enfermería");
                                jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jLabel2MouseClicked(evt);
                                    }
                                });

                                jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel21.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                jLabel21.setText("Diagnósticos Realizados");
                                jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        jLabel21MouseClicked(evt);
                                    }
                                });

                                jScrollPane10.setBorder(null);
                                jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

                                tbDiagRealizados.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbDiagRealizados.setForeground(new java.awt.Color(102, 102, 102));
                                tbDiagRealizados.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbDiagRealizados.setGridColor(new java.awt.Color(255, 255, 255));
                                tbDiagRealizados.setRowHeight(25);
                                tbDiagRealizados.setSelectionBackground(new java.awt.Color(222, 75, 22));
                                tbDiagRealizados.getTableHeader().setReorderingAllowed(false);
                                tbDiagRealizados.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDiagRealizadosMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbDiagRealizadosMousePressed(evt);
                                    }
                                });
                                tbDiagRealizados.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagRealizadosKeyPressed(evt);
                                    }
                                });
                                jScrollPane10.setViewportView(tbDiagRealizados);

                                txtProcedTerapeuticos.setEditable(false);
                                txtProcedTerapeuticos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtProcedTerapeuticos.setForeground(new java.awt.Color(102, 102, 102));
                                jScrollPane11.setViewportView(txtProcedTerapeuticos);

                                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                jPanel4.setLayout(jPanel4Layout);
                                jPanel4Layout.setHorizontalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                    .addContainerGap()
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap(36, Short.MAX_VALUE))
                                );
                                jPanel4Layout.setVerticalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0))
                                );

                                jScrollPane9.setViewportView(jPanel4);

                                jScrollPane4.setBorder(null);
                                jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                                jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

                                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel10.setText("Resumen de la Enfermedad Actual");

                                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel13.setText("Fecha y Hora de Egreso");

                                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtPronosticoAlta.setEditable(false);
                                txtPronosticoAlta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtPronosticoAlta.setForeground(new java.awt.Color(102, 102, 102));
                                txtPronosticoAlta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtPronosticoAlta.setToolTipText("");
                                txtPronosticoAlta.setBorder(null);
                                txtPronosticoAlta.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtPronosticoAltaCaretUpdate(evt);
                                    }
                                });
                                txtPronosticoAlta.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtPronosticoAltaKeyReleased(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                jPanel13.setLayout(jPanel13Layout);
                                jPanel13Layout.setHorizontalGroup(
                                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtPronosticoAlta)
                                        .addGap(1, 1, 1))
                                );
                                jPanel13Layout.setVerticalGroup(
                                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtPronosticoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel8.setText("Diagnóstico de Ingreso");

                                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtEstadia.setEditable(false);
                                txtEstadia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtEstadia.setForeground(new java.awt.Color(102, 102, 102));
                                txtEstadia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtEstadia.setToolTipText("");
                                txtEstadia.setBorder(null);
                                txtEstadia.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtEstadiaCaretUpdate(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                                jPanel10.setLayout(jPanel10Layout);
                                jPanel10Layout.setHorizontalGroup(
                                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel10Layout.setVerticalGroup(
                                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel19.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel19.setText("<html>Informacion sobre Mortalidad <p style=\"font-size:10px\">(Si fuera el caso)</p></html>");

                                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel18.setText("Diagnóstico  de Egreso Principal y Secundario ( CIE 10 )");

                                txtComplicaciones.setEditable(false);
                                txtComplicaciones.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtComplicaciones.setForeground(new java.awt.Color(102, 102, 102));
                                txtComplicaciones.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtComplicacionesKeyReleased(evt);
                                    }
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtComplicacionesKeyTyped(evt);
                                    }
                                });
                                jScrollPane2.setViewportView(txtComplicaciones);

                                txtInfo.setEditable(false);
                                txtInfo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtInfo.setForeground(new java.awt.Color(102, 102, 102));
                                txtInfo.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtInfoKeyReleased(evt);
                                    }
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtInfoKeyTyped(evt);
                                    }
                                });
                                jScrollPane6.setViewportView(txtInfo);

                                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel7.setText("Fecha y Hora de Ingreso");

                                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel17.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel17.setText("Pronostico de Alta");

                                jScrollPane5.setBorder(null);

                                tbDiagnosticoEgreso.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbDiagnosticoEgreso.setForeground(new java.awt.Color(102, 102, 102));
                                tbDiagnosticoEgreso.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbDiagnosticoEgreso.setGridColor(new java.awt.Color(255, 255, 255));
                                tbDiagnosticoEgreso.setRowHeight(25);
                                tbDiagnosticoEgreso.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                tbDiagnosticoEgreso.getTableHeader().setReorderingAllowed(false);
                                tbDiagnosticoEgreso.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDiagnosticoEgresoMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbDiagnosticoEgresoMousePressed(evt);
                                    }
                                });
                                tbDiagnosticoEgreso.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagnosticoEgresoKeyPressed(evt);
                                    }
                                });
                                jScrollPane5.setViewportView(tbDiagnosticoEgreso);

                                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel16.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel16.setText("Condición del Egreso");

                                jScrollPane3.setBorder(null);

                                tbDiagIngreso.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                tbDiagIngreso.setForeground(new java.awt.Color(102, 102, 102));
                                tbDiagIngreso.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbDiagIngreso.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                                tbDiagIngreso.setGridColor(new java.awt.Color(255, 255, 255));
                                tbDiagIngreso.setRowHeight(25);
                                tbDiagIngreso.setSelectionBackground(new java.awt.Color(255, 73, 7));
                                tbDiagIngreso.getTableHeader().setReorderingAllowed(false);
                                tbDiagIngreso.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbDiagIngresoMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbDiagIngresoMousePressed(evt);
                                    }
                                });
                                tbDiagIngreso.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbDiagIngresoKeyPressed(evt);
                                    }
                                });
                                jScrollPane3.setViewportView(tbDiagIngreso);

                                lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFecha.setForeground(new java.awt.Color(51, 51, 51));
                                lblFecha.setText("Fecha y Hora de Ingreso");

                                txtEnfActual.setEditable(false);
                                txtEnfActual.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtEnfActual.setForeground(new java.awt.Color(102, 102, 102));
                                txtEnfActual.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtEnfActualKeyReleased(evt);
                                    }
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtEnfActualKeyTyped(evt);
                                    }
                                });
                                jScrollPane1.setViewportView(txtEnfActual);

                                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel12.setText("Complicaciónes");

                                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtCondicionEgreso.setEditable(false);
                                txtCondicionEgreso.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                txtCondicionEgreso.setForeground(new java.awt.Color(102, 102, 102));
                                txtCondicionEgreso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtCondicionEgreso.setToolTipText("");
                                txtCondicionEgreso.setBorder(null);
                                txtCondicionEgreso.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtCondicionEgresoCaretUpdate(evt);
                                    }
                                });
                                txtCondicionEgreso.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtCondicionEgresoKeyReleased(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                jPanel12.setLayout(jPanel12Layout);
                                jPanel12Layout.setHorizontalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtCondicionEgreso)
                                        .addGap(1, 1, 1))
                                );
                                jPanel12Layout.setVerticalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtCondicionEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel14.setText("Estadia Total");

                                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel15.setText("Tipo de Alta");

                                lblFechaE.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblFechaE.setForeground(new java.awt.Color(102, 102, 102));
                                lblFechaE.setText("Fecha y Hora");

                                btnDiagEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                                btnDiagEgreso.setBorderPainted(false);
                                btnDiagEgreso.setContentAreaFilled(false);
                                btnDiagEgreso.setEnabled(false);
                                btnDiagEgreso.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnDiagEgresoActionPerformed(evt);
                                    }
                                });

                                cbxTipoAlta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                cbxTipoAlta.setForeground(new java.awt.Color(102, 102, 102));
                                cbxTipoAlta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Terapéutica", "Común", "Voluntaria", "Deceso" }));
                                cbxTipoAlta.addItemListener(new java.awt.event.ItemListener() {
                                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                        cbxTipoAltaItemStateChanged(evt);
                                    }
                                });

                                lblHoraE.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblHoraE.setForeground(new java.awt.Color(102, 102, 102));
                                lblHoraE.setText("Fecha y Hora");

                                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                jPanel3.setLayout(jPanel3Layout);
                                jPanel3Layout.setHorizontalGroup(
                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel18)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnDiagEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGap(290, 290, 290)
                                                        .addComponent(jLabel7)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblFecha))))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel13)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(lblFechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(lblHoraE, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel15)
                                                                    .addComponent(jLabel14)
                                                                    .addComponent(jLabel16)
                                                                    .addComponent(jLabel17))
                                                                .addGap(34, 34, 34)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(cbxTipoAlta, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)))))
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel3Layout.setVerticalGroup(
                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(lblFecha))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel18)
                                                            .addComponent(btnDiagEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel13)
                                                    .addComponent(lblFechaE)
                                                    .addComponent(lblHoraE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(cbxTipoAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel17)
                                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addContainerGap(36, Short.MAX_VALUE))
                                );

                                jScrollPane4.setViewportView(jPanel3);

                                pnlMensaje.setBackground(new java.awt.Color(33, 115, 70));

                                lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
                                lblMensaje.setText("Desea Actualizar el Registro ?");

                                btnSi.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
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

                                btnNo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
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
                                        .addContainerGap()
                                        .addComponent(lblMensaje)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(298, Short.MAX_VALUE))
                                );
                                pnlMensajeLayout.setVerticalGroup(
                                    pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMensajeLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblMensaje))
                                        .addContainerGap())
                                );

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(301, Short.MAX_VALUE))
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                );
                                layout.setVerticalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(0, 0, 0))
                                );

                                pack();
                            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        FrmReporteEpicrisis.setVisible(true);
        FrmReporteEpicrisis.setLocationRelativeTo(null);//en el centro
        FrmReporteEpicrisis.setResizable(false);
        FrmReporteEpicrisis.getContentPane().setBackground(Color.WHITE);
        HospitalizacionEpicrisis epicrisis = new HospitalizacionEpicrisis();
        epicrisis.listarEpicrisis(txtBuscarEpicrisis.getText(), tbEpicrisis);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(cbxTipoAlta.getSelectedIndex()==0){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el tipo de alta");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
        } else
            mantenimientoHospitalizacionEpicrisis();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed
        BuscarHospitalizacion.setVisible(true);
        BuscarHospitalizacion.setLocationRelativeTo(null);//en el centro
        BuscarHospitalizacion.setResizable(false);
        BuscarHospitalizacion.getContentPane().setBackground(Color.WHITE);
        HospitalizacionPapeletas hosP = new HospitalizacionPapeletas();
        hosP.listarPapeleta("H", txtPaciente.getText(), tbPacientesHospitalizados,"C");
    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void tbDiagIngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagIngresoMouseClicked

       
    }//GEN-LAST:event_tbDiagIngresoMouseClicked

    private void tbDiagIngresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagIngresoMousePressed

    }//GEN-LAST:event_tbDiagIngresoMousePressed

    private void tbDiagIngresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagIngresoKeyPressed

    }//GEN-LAST:event_tbDiagIngresoKeyPressed

    private void txtEnfActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfActualKeyReleased
        txtEnfActual.setText(txtEnfActual.getText().toUpperCase());
    }//GEN-LAST:event_txtEnfActualKeyReleased

    private void txtEnfActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnfActualKeyTyped

    }//GEN-LAST:event_txtEnfActualKeyTyped

    private void txtComplicacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplicacionesKeyReleased
        txtComplicaciones.setText(txtComplicaciones.getText().toUpperCase());
    }//GEN-LAST:event_txtComplicacionesKeyReleased

    private void txtComplicacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplicacionesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplicacionesKeyTyped

    private void txtEstadiaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstadiaCaretUpdate
        //        if(lblNewMod.getText().equals("N")){
            //            if(txtNHCTri.getText().length()==7){
                //                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
                //                pnlTriaje.setVisible(false);
                //                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
                //                AdmisionEmergenciaTriaje ad = new AdmisionEmergenciaTriaje();
                //                if(tbFormatosEmer.getRowCount()!=0){
                    //                    if(lblPestana.getText().equals("TR") || tbPaneles.getSelectedIndex()==1)
                    //                    lblPestanaMod.setText("TR");
                    //                    dlgModemergencia.setVisible(true);
                    //                    dlgModemergencia.setLocationRelativeTo(null);//en el centro
                    //                    dlgModemergencia.setResizable(false);
                    //                    dlgModemergencia.getContentPane().setBackground(Color.WHITE);
                    //                } else {
                    //                    JOptionPane.showMessageDialog(this,"No hay registros");
                    //                }
                //            }
            //            else{
                //                lblIDHCTr.setText("");
                //                lblPaciente.setText("");
                //                txtIDTriaje.setText("");
                //                pnlTriaje.setVisible(false);
                //            }
            //        } else { //MODIFICAR
            //            if(txtNHCTri.getText().length()==7){
                //                adEmerCab.mostrarHCTriaje(formatoNHC(txtNHCTri.getText()));
                //                //pnlTriaje.setVisible(true);
                //                adEmerCab.cargarFormatEmer(lblIDHCTr.getText(),"",tbFormatosEmer);
                //                adEmerTr.admisionEmergenciaTriajeListar(lblIDHCTr.getText(),"" , tbModifTriaje,"A");
                //                if(tbModifTriaje.getRowCount()!=0){
                    //                    dlgModTriaje.setVisible(true);
                    //                    dlgModTriaje.setLocationRelativeTo(null);//en el centro
                    //                    dlgModTriaje.setResizable(false);
                    //                    dlgModTriaje.getContentPane().setBackground(Color.WHITE);
                    //                } else {
                    //                    dlgModTriaje.setVisible(false);
                    //                    JOptionPane.showMessageDialog(this,"No hay registros");
                    //                    txtNHCTri.setText("");
                    //                }
                //            }
            //            else{
                //                lblIDHCTr.setText("");
                //                lblPaciente.setText("");
                //                txtIDTriaje.setText("");
                //                pnlTriaje.setVisible(false);
                //            }
            //        }
    }//GEN-LAST:event_txtEstadiaCaretUpdate

    private void txtCondicionEgresoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCondicionEgresoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCondicionEgresoCaretUpdate

    private void txtPronosticoAltaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPronosticoAltaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPronosticoAltaCaretUpdate

    private void tbDiagnosticoEgresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoMouseClicked

    private void tbDiagnosticoEgresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoMousePressed

    private void tbDiagnosticoEgresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoKeyPressed

    private void txtInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInfoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInfoKeyReleased

    private void txtInfoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInfoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInfoKeyTyped

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
            txtPaciente.requestFocus();
            tbPacientesHospitalizados.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosPaciente();
        }
    }//GEN-LAST:event_tbPacientesHospitalizadosKeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCie10);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCie10.getSelectionModel().setSelectionInterval(0, 0);
            tbCie10.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tbCie10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCie10MouseClicked
        if(evt.getClickCount()==2){
            enviarDiagnosticos(tbCie10,tbDiagnosticoEgreso);
        }
    }//GEN-LAST:event_tbCie10MouseClicked

    private void tbCie10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCie10MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCie10MousePressed

    private void tbCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCie10.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCie10.requestFocus();
            tbCie10.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(tbCie10,tbDiagnosticoEgreso);
        }
    }//GEN-LAST:event_tbCie10KeyPressed

    private void btnDiagEgresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagEgresoActionPerformed
        FrmCie10.setVisible(true);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.setResizable(false);
        FrmCie10.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCie10);
    }//GEN-LAST:event_btnDiagEgresoActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
       
    }//GEN-LAST:event_jLabel21MouseClicked

    private void tbDiagRealizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagRealizadosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagRealizadosMouseClicked

    private void tbDiagRealizadosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagRealizadosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagRealizadosMousePressed

    private void tbDiagRealizadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagRealizadosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagRealizadosKeyPressed

    private void txtIdPreventaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdPreventaCaretUpdate
        ConsultorioExtConsultorioDiagnostico diagIngreso = new ConsultorioExtConsultorioDiagnostico();
        diagIngreso.hospitalizacionDiagnosticoIngreso(tbDiagIngreso,txtIdPreventa.getText());
        HospitalizacionExamenClinico ec = new HospitalizacionExamenClinico();
        ec.listarDiagnosticosEpicrisis(txtIdPreventa.getText(), tbDiagRealizados);
        HospitalizacionNotaEnfermeria notaEnfermeria = new HospitalizacionNotaEnfermeria();
        notaEnfermeria.notaEnfermeriaEpicrisis(txtIdPreventa.getText());
        HospitalizacionEpicrisis epicrisis = new HospitalizacionEpicrisis();
        txtEstadia.setText(epicrisis.diasHospitalizados(txtIdPreventa.getText()));
    }//GEN-LAST:event_txtIdPreventaCaretUpdate

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

    private void cbxTipoAltaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoAltaItemStateChanged
        if(cbxTipoAlta.getSelectedIndex()==4)
            txtInfo.setEditable(true);
        else
            txtInfo.setEditable(false);
    }//GEN-LAST:event_cbxTipoAltaItemStateChanged

    private void txtCondicionEgresoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCondicionEgresoKeyReleased
        txtCondicionEgreso.setText(txtCondicionEgreso.getText().toUpperCase());
    }//GEN-LAST:event_txtCondicionEgresoKeyReleased

    private void txtPronosticoAltaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPronosticoAltaKeyReleased
        txtPronosticoAlta.setText(txtPronosticoAlta.getText().toUpperCase());
    }//GEN-LAST:event_txtPronosticoAltaKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        HospitalizacionEpicrisis epicrisis = new HospitalizacionEpicrisis();
        epicrisis.listarEpicrisis(txtBuscarEpicrisis.getText(), tbEpicrisis);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarEpicrisisCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarEpicrisisCaretUpdate
        HospitalizacionEpicrisis epicrisis = new HospitalizacionEpicrisis();
        epicrisis.listarEpicrisis(txtBuscarEpicrisis.getText(), tbEpicrisis);
    }//GEN-LAST:event_txtBuscarEpicrisisCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked

    }//GEN-LAST:event_jLabel34MouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
//        //VISUALIZAR EL REPORTE DE ALERTAS
//        String ruta = "/reportes/consultoriosExternos/atencionPrenatalAlertas.jasper";
//        cabecera.reporteAlertas(ruta);
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tbEpicrisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEpicrisisMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosEpicrisis();
        }
    }//GEN-LAST:event_tbEpicrisisMouseClicked

    private void tbEpicrisisMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEpicrisisMousePressed

    }//GEN-LAST:event_tbEpicrisisMousePressed

    private void tbEpicrisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEpicrisisKeyPressed

    }//GEN-LAST:event_tbEpicrisisKeyPressed

    private void tbEpicrisisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEpicrisisKeyReleased
//        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP
//            || evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
//            enviarDatosAlertas();
//        }
    }//GEN-LAST:event_tbEpicrisisKeyReleased

    private void txtIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIdCaretUpdate
        HospitalizacionEpicrisis epicrisis = new HospitalizacionEpicrisis();
        epicrisis.hospitalizacionListarEpicrisis(txtId.getText());
        HospitalizacionEpicrisisDiagnosticosEgreso diagEgreso = new HospitalizacionEpicrisisDiagnosticosEgreso();
        diagEgreso.listarDiagnosticosEgreso(txtId.getText(), tbDiagnosticoEgreso);
        tbDiagnosticoEgreso.setAutoResizeMode(0);
    }//GEN-LAST:event_txtIdCaretUpdate
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    
    //HORA
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHoraE.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(FrmHospitalizacionEpicrisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionEpicrisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionEpicrisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHospitalizacionEpicrisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHospitalizacionEpicrisis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarHospitalizacion;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JDialog FrmReporteEpicrisis;
    private javax.swing.JButton T3;
    private javax.swing.JLabel T7;
    public static javax.swing.JButton btnActualizar;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnDiagEgreso;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNo;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    public static javax.swing.JComboBox cbxTipoAlta;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblActoMedico1;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDNI1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaE;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblHC1;
    private javax.swing.JLabel lblHoraE;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPaciente1;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblServicio1;
    public static javax.swing.JLabel lblusu;
    public static javax.swing.JLabel lblusu1;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbCie10;
    public static javax.swing.JTable tbDiagIngreso;
    public static javax.swing.JTable tbDiagRealizados;
    public static javax.swing.JTable tbDiagnosticoEgreso;
    private javax.swing.JTable tbEpicrisis;
    private javax.swing.JTable tbPacientesHospitalizados;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtBuscarEpicrisis;
    public static javax.swing.JEditorPane txtComplicaciones;
    public static javax.swing.JTextField txtCondicionEgreso;
    public static javax.swing.JEditorPane txtEnfActual;
    public static javax.swing.JTextField txtEstadia;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtIdPreventa;
    private javax.swing.JTextField txtIdPreventa1;
    public static javax.swing.JEditorPane txtInfo;
    private javax.swing.JTextField txtPaciente;
    public static javax.swing.JEditorPane txtProcedTerapeuticos;
    public static javax.swing.JTextField txtPronosticoAlta;
    // End of variables declaration//GEN-END:variables
}
