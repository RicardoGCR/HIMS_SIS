/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.hospitalizacion;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import modelos.hospitalizacion.HospitalizacionPapeletas;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroSeguimiento;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.tbListarPapeleta;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.txtBuscarr;

/**
 *
 * @author MYS1
 */
public class FrmHospitalizacionEpicrisis extends javax.swing.JFrame {

    DefaultTableModel m;
    Connection conexion=null;
    Conexion c = new Conexion();
    public FrmHospitalizacionEpicrisis() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
    }

    public void enviarDatosPaciente(){
        int fila = tbPacientesHospitalizados.getSelectedRow();
        lblActoMedico.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 2)));
        lblHC.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 3)));
        lblDNI.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 4)));
        lblPaciente.setText("Paciente: " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 5)));
        lblFecha.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 17)) + " " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 18)));
        lblServicio.setText(String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 8)) + " Nº de Cama " + String.valueOf(tbPacientesHospitalizados.getValueAt(fila, 11)));
        BuscarHospitalizacion.dispose();
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
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                lblusu = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                jPanel9 = new javax.swing.JPanel();
                txPaciente = new javax.swing.JTextField();
                btnGuardar = new javax.swing.JButton();
                btnBuscarPaciente = new javax.swing.JButton();
                btnEliminar = new javax.swing.JButton();
                btnEditar = new javax.swing.JButton();
                lbldetalle = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                lblPaciente = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                lblActoMedico = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                lblHC = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel10 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                jPanel13 = new javax.swing.JPanel();
                txtEstadia3 = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                txtEstadia = new javax.swing.JTextField();
                lblServicio = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                txtComplicaciones = new javax.swing.JEditorPane();
                jScrollPane6 = new javax.swing.JScrollPane();
                txtComplicaciones1 = new javax.swing.JEditorPane();
                jLabel7 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                jScrollPane5 = new javax.swing.JScrollPane();
                tbDiagnosticoEgreso = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jLabel16 = new javax.swing.JLabel();
                    jScrollPane3 = new javax.swing.JScrollPane();
                    tbTriaje = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        lblFecha = new javax.swing.JLabel();
                        jScrollPane1 = new javax.swing.JScrollPane();
                        txtIndicaciones = new javax.swing.JEditorPane();
                        jLabel12 = new javax.swing.JLabel();
                        jLabel9 = new javax.swing.JLabel();
                        jPanel12 = new javax.swing.JPanel();
                        txtEstadia2 = new javax.swing.JTextField();
                        jLabel14 = new javax.swing.JLabel();
                        jLabel15 = new javax.swing.JLabel();
                        lblFechaEgreso = new javax.swing.JLabel();
                        btnDiagEgreso = new javax.swing.JButton();
                        jComboBox1 = new javax.swing.JComboBox();
                        jScrollPane9 = new javax.swing.JScrollPane();
                        jPanel4 = new javax.swing.JPanel();
                        jLabel2 = new javax.swing.JLabel();
                        jLabel21 = new javax.swing.JLabel();
                        jScrollPane4 = new javax.swing.JScrollPane();
                        tbTriaje1 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jScrollPane10 = new javax.swing.JScrollPane();
                            tbTriaje2 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};

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
                                jLabel36.setText("Código CIE 10 / Diagnóstico Presuntivo");

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

                                jPanel32.setBackground(new java.awt.Color(39, 174, 97));

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
                                btnNuevo.setText("Nuevo");
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

                                jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                                txPaciente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txPaciente.setForeground(new java.awt.Color(51, 51, 51));
                                txPaciente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txPaciente.setToolTipText("");
                                txPaciente.setBorder(null);
                                txPaciente.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txPacienteCaretUpdate(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                jPanel9.setLayout(jPanel9Layout);
                                jPanel9Layout.setHorizontalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel9Layout.setVerticalGroup(
                                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                                btnGuardar.setText("Guardar");
                                btnGuardar.setContentAreaFilled(false);
                                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnGuardar.setIconTextGap(30);
                                btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnGuardarActionPerformed(evt);
                                    }
                                });

                                btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                btnBuscarPaciente.setToolTipText("");
                                btnBuscarPaciente.setContentAreaFilled(false);
                                btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                                btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                btnEditar.setIconTextGap(30);
                                btnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                btnEditar.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnEditarActionPerformed(evt);
                                    }
                                });

                                lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                lbldetalle.setText("DNI, Apellidos, Nombres");

                                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                jPanel1.setLayout(jPanel1Layout);
                                jPanel1Layout.setHorizontalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                .addGap(227, 227, 227)
                                                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(24, 24, 24)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lbldetalle)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addContainerGap())
                                );
                                jPanel1Layout.setVerticalGroup(
                                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbldetalle)
                                        .addGap(21, 21, 21)
                                        .addComponent(btnNuevo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGuardar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEditar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblusu))
                                );

                                jPanel2.setBackground(new java.awt.Color(43, 43, 43));

                                lblPaciente.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                                lblPaciente.setForeground(new java.awt.Color(255, 255, 255));
                                lblPaciente.setText("Nombres y Apellidos");

                                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel3.setText("Acto Médico");

                                lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
                                lblActoMedico.setText("Nº");

                                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel5.setText("DNI");

                                lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                                lblDNI.setText("DNI");

                                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                                jLabel4.setText("Nº H.C.");

                                lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                lblHC.setForeground(new java.awt.Color(204, 204, 204));
                                lblHC.setText("H.C.");

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
                                                    .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))
                                            .addComponent(lblPaciente))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel2Layout.setVerticalGroup(
                                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblPaciente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(lblActoMedico))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(lblDNI))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblHC)
                                            .addComponent(jLabel4))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel10.setText("Resumen de la Enfermedad Actual");

                                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel13.setText("Fecha y Hora de Egreso");

                                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtEstadia3.setEditable(false);
                                txtEstadia3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtEstadia3.setForeground(new java.awt.Color(51, 51, 51));
                                txtEstadia3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtEstadia3.setToolTipText("");
                                txtEstadia3.setBorder(null);
                                txtEstadia3.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtEstadia3CaretUpdate(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                                jPanel13.setLayout(jPanel13Layout);
                                jPanel13Layout.setHorizontalGroup(
                                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtEstadia3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                );
                                jPanel13Layout.setVerticalGroup(
                                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtEstadia3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel8.setText("Diagnóstico de Ingreso");

                                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtEstadia.setEditable(false);
                                txtEstadia.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtEstadia.setForeground(new java.awt.Color(51, 51, 51));
                                txtEstadia.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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
                                        .addComponent(txtEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );
                                jPanel10Layout.setVerticalGroup(
                                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtEstadia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                lblServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblServicio.setForeground(new java.awt.Color(51, 51, 51));
                                lblServicio.setText("Fecha y Hora de Ingreso");

                                jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel19.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel19.setText("Informacion sobre Mortalidad (Si fuera el caso)");

                                jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel18.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel18.setText("Diagnóstico  de Egreso Principal y Secundario ( CIE 10 )");

                                txtComplicaciones.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
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

                                txtComplicaciones1.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
                                txtComplicaciones1.setForeground(new java.awt.Color(102, 102, 102));
                                txtComplicaciones1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtComplicaciones1KeyReleased(evt);
                                    }
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtComplicaciones1KeyTyped(evt);
                                    }
                                });
                                jScrollPane6.setViewportView(txtComplicaciones1);

                                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel7.setText("Fecha y Hora de Ingreso");

                                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel17.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel17.setText("Pronostico de Alta");

                                jScrollPane5.setBorder(null);

                                tbDiagnosticoEgreso.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                tbDiagnosticoEgreso.setForeground(new java.awt.Color(51, 51, 51));
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

                                tbTriaje.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                tbTriaje.setForeground(new java.awt.Color(51, 51, 51));
                                tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbTriaje.setGridColor(new java.awt.Color(255, 255, 255));
                                tbTriaje.setRowHeight(25);
                                tbTriaje.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                tbTriaje.getTableHeader().setReorderingAllowed(false);
                                tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbTriajeMouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbTriajeMousePressed(evt);
                                    }
                                });
                                tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbTriajeKeyPressed(evt);
                                    }
                                });
                                jScrollPane3.setViewportView(tbTriaje);

                                lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFecha.setForeground(new java.awt.Color(51, 51, 51));
                                lblFecha.setText("Fecha y Hora de Ingreso");

                                txtIndicaciones.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
                                txtIndicaciones.setForeground(new java.awt.Color(102, 102, 102));
                                txtIndicaciones.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        txtIndicacionesKeyReleased(evt);
                                    }
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtIndicacionesKeyTyped(evt);
                                    }
                                });
                                jScrollPane1.setViewportView(txtIndicaciones);

                                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel12.setText("Complicaciónes");

                                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel9.setText("Servicio y Numero de Cama");

                                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtEstadia2.setEditable(false);
                                txtEstadia2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtEstadia2.setForeground(new java.awt.Color(51, 51, 51));
                                txtEstadia2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                txtEstadia2.setToolTipText("");
                                txtEstadia2.setBorder(null);
                                txtEstadia2.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtEstadia2CaretUpdate(evt);
                                    }
                                });

                                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                                jPanel12.setLayout(jPanel12Layout);
                                jPanel12Layout.setHorizontalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtEstadia2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                );
                                jPanel12Layout.setVerticalGroup(
                                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(0, 0, 0)
                                        .addComponent(txtEstadia2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                );

                                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel14.setText("Estadia Total");

                                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                                jLabel15.setText("Tipo de Alta");

                                lblFechaEgreso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                lblFechaEgreso.setForeground(new java.awt.Color(51, 51, 51));
                                lblFechaEgreso.setText("Fecha y Hora");

                                btnDiagEgreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                                btnDiagEgreso.setBorderPainted(false);
                                btnDiagEgreso.setContentAreaFilled(false);
                                btnDiagEgreso.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        btnDiagEgresoActionPerformed(evt);
                                    }
                                });

                                jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terapéutica", "Común", "Voluntaria", "Deceso" }));

                                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                                jPanel3.setLayout(jPanel3Layout);
                                jPanel3Layout.setHorizontalGroup(
                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addComponent(jLabel19)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE))
                                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel9)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lblServicio)
                                                    .addGap(42, 42, 42)
                                                    .addComponent(jLabel7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(lblFecha))
                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(jLabel13)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(lblFechaEgreso))
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel17)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                .addComponent(jLabel16)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel14)
                                                                    .addComponent(jLabel15))
                                                                .addGap(85, 85, 85)
                                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addContainerGap())
                                );
                                jPanel3Layout.setVerticalGroup(
                                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(lblServicio)
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
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(lblFechaEgreso))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel14)
                                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel17)
                                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(btnDiagEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addContainerGap())
                                );

                                jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
                                jScrollPane9.setBorder(null);

                                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                jLabel2.setForeground(new java.awt.Color(102, 102, 102));
                                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                jLabel2.setText("Procedimientos Terapéuticos");
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

                                jScrollPane4.setBorder(null);

                                tbTriaje1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                tbTriaje1.setForeground(new java.awt.Color(51, 51, 51));
                                tbTriaje1.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbTriaje1.setGridColor(new java.awt.Color(255, 255, 255));
                                tbTriaje1.setRowHeight(25);
                                tbTriaje1.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                tbTriaje1.getTableHeader().setReorderingAllowed(false);
                                tbTriaje1.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbTriaje1MouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbTriaje1MousePressed(evt);
                                    }
                                });
                                tbTriaje1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbTriaje1KeyPressed(evt);
                                    }
                                });
                                jScrollPane4.setViewportView(tbTriaje1);

                                jScrollPane10.setBorder(null);

                                tbTriaje2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                                tbTriaje2.setForeground(new java.awt.Color(51, 51, 51));
                                tbTriaje2.setModel(new javax.swing.table.DefaultTableModel(
                                    new Object [][] {

                                    },
                                    new String [] {
                                        "Title 1", "Title 2", "Title 3", "Title 4"
                                    }
                                ));
                                tbTriaje2.setGridColor(new java.awt.Color(255, 255, 255));
                                tbTriaje2.setRowHeight(25);
                                tbTriaje2.setSelectionBackground(new java.awt.Color(39, 174, 97));
                                tbTriaje2.getTableHeader().setReorderingAllowed(false);
                                tbTriaje2.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tbTriaje2MouseClicked(evt);
                                    }
                                    public void mousePressed(java.awt.event.MouseEvent evt) {
                                        tbTriaje2MousePressed(evt);
                                    }
                                });
                                tbTriaje2.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tbTriaje2KeyPressed(evt);
                                    }
                                });
                                jScrollPane10.setViewportView(tbTriaje2);

                                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                                jPanel4.setLayout(jPanel4Layout);
                                jPanel4Layout.setHorizontalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                            .addComponent(jScrollPane10)))
                                );
                                jPanel4Layout.setVerticalGroup(
                                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                        .addContainerGap())
                                );

                                jScrollPane9.setViewportView(jPanel4);

                                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                getContentPane().setLayout(layout);
                                layout.setHorizontalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))))
                                );
                                layout.setVerticalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addContainerGap())
                                );

                                pack();
                            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txPacienteCaretUpdate
        if(!txPaciente.getText().equals("")){
            lbldetalle.setText("Buscador de consultas anteriores");
        }else if(txPaciente.getText().equals("")){
            lbldetalle.setText(" ");
        }

    }//GEN-LAST:event_txPacienteCaretUpdate

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

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

    private void tbTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseClicked

       
    }//GEN-LAST:event_tbTriajeMouseClicked

    private void tbTriajeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMousePressed

    }//GEN-LAST:event_tbTriajeMousePressed

    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed

    }//GEN-LAST:event_tbTriajeKeyPressed

    private void txtIndicacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIndicacionesKeyReleased
        txtIndicaciones.setText(txtIndicaciones.getText().toUpperCase());
    }//GEN-LAST:event_txtIndicacionesKeyReleased

    private void txtIndicacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIndicacionesKeyTyped

    }//GEN-LAST:event_txtIndicacionesKeyTyped

    private void tbTriaje1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje1MouseClicked

    private void tbTriaje1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje1MousePressed

    private void tbTriaje1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaje1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje1KeyPressed

    private void txtComplicacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplicacionesKeyReleased
        // TODO add your handling code here:
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

    private void txtEstadia2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstadia2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadia2CaretUpdate

    private void txtEstadia3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstadia3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadia3CaretUpdate

    private void tbDiagnosticoEgresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoMouseClicked

    private void tbDiagnosticoEgresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoMousePressed

    private void tbDiagnosticoEgresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDiagnosticoEgresoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDiagnosticoEgresoKeyPressed

    private void txtComplicaciones1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplicaciones1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplicaciones1KeyReleased

    private void txtComplicaciones1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtComplicaciones1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComplicaciones1KeyTyped

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

    private void tbTriaje2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje2MouseClicked

    private void tbTriaje2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje2MousePressed

    private void tbTriaje2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaje2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje2KeyPressed

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
    private javax.swing.JLabel T7;
    private javax.swing.JLabel btnBuscar;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnDiagEgreso;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaEgreso;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JTable tbCie10;
    public static javax.swing.JTable tbDiagnosticoEgreso;
    private javax.swing.JTable tbPacientesHospitalizados;
    public static javax.swing.JTable tbTriaje;
    public static javax.swing.JTable tbTriaje1;
    public static javax.swing.JTable tbTriaje2;
    private javax.swing.JLabel titulo7;
    public static javax.swing.JTextField txPaciente;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JEditorPane txtComplicaciones;
    public static javax.swing.JEditorPane txtComplicaciones1;
    public static javax.swing.JTextField txtEstadia;
    public static javax.swing.JTextField txtEstadia2;
    public static javax.swing.JTextField txtEstadia3;
    public static javax.swing.JEditorPane txtIndicaciones;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
