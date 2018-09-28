/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.RX;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import modelos.EC.EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO;
import modelos.RX.*;
import modelos.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import vista.RX.*;
import static vista.RX.RX_EC_EXAMEN_CAB.DT;

/**
 *
 * @author PC-SISTEMA
 */
public class RX_EC_EXAMEN_CAB_RESULTADO extends javax.swing.JFrame implements Runnable{
Conexion conectar=new Conexion();
Connection conexion=conectar.conectar();
Conexion c=new Conexion();
Connection con;
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread h1;
CallableStatement cst;
ResultSet r;
byte tg=0;
DefaultTableModel m1, m2,msb1, msb3,m3,m4,m5,m6,m7, m8,m9, modelo1, modelo2;
static RX_EC_EXAMEN DT = new RX_EC_EXAMEN();

    /**
     * Creates new form RX_EC_EXAMEN_CAB
     */
    public RX_EC_EXAMEN_CAB_RESULTADO() {

        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        
        
        con=conectar.conectar();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        PERSONAL_ROL.setLocationRelativeTo(null);
        PERSONAL_ROL.getContentPane().setBackground(Color.white);
        PERSONAL_ROL_TODO.setLocationRelativeTo(null);
        PERSONAL_ROL_TODO.getContentPane().setBackground(Color.white);
        CIE10.setLocationRelativeTo(null);
        CIE10.getContentPane().setBackground(Color.white);
        mostrarArea();
//        CargarPersonalRol();
        CargarPersonalRol_todo();
        cargarDiagnostico();
        inicializar_tabla_Examenes_detalle();
        inicializar_tabla_cie10();
        VISIBLE_datos();
        txtHC.setVisible(false);
        txtDNI.setVisible(false);
        
        
        //FECHA Y HORA
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFechaReg.setText(fechaActual());
        
        RX_EC_RESULTADO_CAB num=new RX_EC_RESULTADO_CAB();
        txtNumExamen.setText(num.RX_EC_resultado_generarNum());
        if(txtNumExamen.getText().equalsIgnoreCase("")){
        txtNumExamen.setText("00000001");
                    } 
        lblNumExamen.setText(txtNumExamen.getText());
        lblPerB.setVisible(false);
        EP_Descripcion.setEnabled(false);
        EP_CONCLUSION.setEnabled(false);
        btnBuscarCIE10.setEnabled(false);
        BTN_QUITAR_CIE10.setEnabled(false);
        txtAM.setVisible(false);
        cargareliminar.setVisible(false);
        jPanel1.setVisible(false);
        cbxINCIDENCIA.setBackground(Color.WHITE);
        cbxMEDIDAS.setBackground(Color.WHITE);
        this.cbxMEDIDAS.setModel(tipo());
        this.cbxINCIDENCIA.setModel(tipo1());
        
//          tb_examen_det.setEnabled(false);
//          tb_examen_det.setBackground(Color.lightGray);
          tb_examen_det.getSelectionModel().setSelectionInterval(0, 0);
          tb_examen_det.requestFocus();
        
          btnGuardarCabeceraRes.setEnabled(false);
         
          

          
          //CERRAR DIALOGO CON ESCAPE
          addEscapeListenerWindowDialog(CIE10);
          addEscapeListenerWindowDialog(PERSONAL_ROL);
          addEscapeListenerWindowDialog(PERSONAL_ROL_TODO);
          
          //CERRAR CON ESCAPE
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                
                cerrar();
                                
            }
        });
          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PERSONAL_ROL = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblPerB = new javax.swing.JLabel();
        panelCPT1 = new javax.swing.JPanel();
        txtBuscarPersonal = new javax.swing.JTextField();
        btnBuscarCPT1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Personal_rol = new javax.swing.JTable();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        Frontal = new javax.swing.JMenuItem();
        PERSONAL_ROL_TODO = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtBuscarPersonal_TODO = new javax.swing.JTextField();
        btnBuscarCPT2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Personal_rol_todo = new javax.swing.JTable();
        CIE10 = new javax.swing.JDialog();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_CIE10 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txt_CIE10 = new javax.swing.JTextField();
        btnBuscarCPT = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblNomA = new javax.swing.JLabel();
        lblIDArea = new javax.swing.JLabel();
        lblCod_Personal_Sol = new javax.swing.JLabel();
        lblCod_Cab_Resultado = new javax.swing.JLabel();
        txtId_Documento_G = new javax.swing.JTextField();
        txtID_EXAMEN_CAB = new javax.swing.JTextField();
        lblUsu = new javax.swing.JLabel();
        btnGuardarCabeceraRes = new javax.swing.JButton();
        btnRegresarRes = new javax.swing.JButton();
        btnGuardarCabeceraRes1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblHospiServ = new javax.swing.JLabel();
        lblHospi = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHabitacion = new javax.swing.JTextField();
        txtCama = new javax.swing.JTextField();
        txtPersonalSolicita = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblNumExamen = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblFUA = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblFechaReg = new javax.swing.JLabel();
        lblHoraReg = new javax.swing.JLabel();
        txtAM = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        panelCPT2 = new javax.swing.JPanel();
        txtPersonalRegistraResultado = new javax.swing.JTextField();
        btnPersonalResultado = new javax.swing.JButton();
        panelCPT3 = new javax.swing.JPanel();
        txtPersonalRealizaRes = new javax.swing.JTextField();
        btnPersonalResRealiza = new javax.swing.JButton();
        lblCod_Per_Registra = new javax.swing.JLabel();
        lblCod_Per_realiza = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtID_COD_DOC = new javax.swing.JTextField();
        txtCOD_EXAMEN_DETALLE = new javax.swing.JTextField();
        txtNumExamen = new javax.swing.JTextField();
        txtAM1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_examen_det = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        panelCPT5 = new javax.swing.JPanel();
        txtPersonalRealizaRes1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        cbxMEDIDAS = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        cbxINCIDENCIA = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        txtCAB_RESULTADO = new javax.swing.JTextField();
        panelCPT6 = new javax.swing.JPanel();
        txtPersonalRealizaRes2 = new javax.swing.JTextField();
        btnBuscarCIE10 = new javax.swing.JButton();
        txtAMB = new javax.swing.JTextField();
        txtCOD_DET_RES = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        EP_CONCLUSION = new javax.swing.JEditorPane();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        EP_Descripcion = new javax.swing.JEditorPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCIE10 = new javax.swing.JTable();
        BTN_QUITAR_CIE10 = new javax.swing.JButton();
        lblId_Preventa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelFormaPago1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtNombreP = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JLabel();
        txtHC = new javax.swing.JTextField();
        txtHC2 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtHC3 = new javax.swing.JLabel();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();

        PERSONAL_ROL.setAlwaysOnTop(true);
        PERSONAL_ROL.setMinimumSize(new java.awt.Dimension(692, 360));
        PERSONAL_ROL.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(230, 230, 230));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Médico Solicitante");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Buscar por DNI. Cargo, Apellidos y Nombres");

        lblPerB.setForeground(new java.awt.Color(230, 230, 230));
        lblPerB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPerB.setText("B1");

        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarPersonal.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarPersonal.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPersonal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarPersonal.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtBuscarPersonal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPersonalCaretUpdate(evt);
            }
        });

        btnBuscarCPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarCPT1.setContentAreaFilled(false);
        btnBuscarCPT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCPT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCPT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
        panelCPT1.setLayout(panelCPT1Layout);
        panelCPT1Layout.setHorizontalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT1Layout.setVerticalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(450, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPerB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(lblPerB))
                .addGap(2, 2, 2)
                .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Personal_rol = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Personal_rol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tb_Personal_rol.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Personal_rol.setRowHeight(30);
        tb_Personal_rol.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Personal_rol.getTableHeader().setReorderingAllowed(false);
        tb_Personal_rol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Personal_rolKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Personal_rol);

        javax.swing.GroupLayout PERSONAL_ROLLayout = new javax.swing.GroupLayout(PERSONAL_ROL.getContentPane());
        PERSONAL_ROL.getContentPane().setLayout(PERSONAL_ROLLayout);
        PERSONAL_ROLLayout.setHorizontalGroup(
            PERSONAL_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        PERSONAL_ROLLayout.setVerticalGroup(
            PERSONAL_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PERSONAL_ROLLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
        );

        Frontal.setText("Frontal");
        jPopupMenu2.add(Frontal);

        PERSONAL_ROL_TODO.setAlwaysOnTop(true);
        PERSONAL_ROL_TODO.setMinimumSize(new java.awt.Dimension(692, 360));
        PERSONAL_ROL_TODO.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(230, 230, 230));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Responsable del Resultado");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Buscar por DNI. Cargo, Apellidos y Nombres");

        panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarPersonal_TODO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarPersonal_TODO.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPersonal_TODO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarPersonal_TODO.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtBuscarPersonal_TODO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPersonal_TODOCaretUpdate(evt);
            }
        });

        btnBuscarCPT2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarCPT2.setContentAreaFilled(false);
        btnBuscarCPT2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCPT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCPT2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
        panelCPT4.setLayout(panelCPT4Layout);
        panelCPT4Layout.setHorizontalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscarPersonal_TODO, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT4Layout.setVerticalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarPersonal_TODO, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel18)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Personal_rol_todo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Personal_rol_todo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tb_Personal_rol_todo.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Personal_rol_todo.setRowHeight(30);
        tb_Personal_rol_todo.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Personal_rol_todo.getTableHeader().setReorderingAllowed(false);
        tb_Personal_rol_todo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Personal_rol_todoKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tb_Personal_rol_todo);

        javax.swing.GroupLayout PERSONAL_ROL_TODOLayout = new javax.swing.GroupLayout(PERSONAL_ROL_TODO.getContentPane());
        PERSONAL_ROL_TODO.getContentPane().setLayout(PERSONAL_ROL_TODOLayout);
        PERSONAL_ROL_TODOLayout.setHorizontalGroup(
            PERSONAL_ROL_TODOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        PERSONAL_ROL_TODOLayout.setVerticalGroup(
            PERSONAL_ROL_TODOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PERSONAL_ROL_TODOLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
        );

        CIE10.setAlwaysOnTop(true);
        CIE10.setMinimumSize(new java.awt.Dimension(470, 500));
        CIE10.setResizable(false);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_CIE10 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_CIE10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tb_CIE10.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_CIE10.setRowHeight(30);
        tb_CIE10.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_CIE10.getTableHeader().setReorderingAllowed(false);
        tb_CIE10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_CIE10KeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tb_CIE10);

        jPanel8.setBackground(new java.awt.Color(230, 230, 230));

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("CIE10");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Descripción");

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txt_CIE10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_CIE10.setForeground(new java.awt.Color(51, 51, 51));
        txt_CIE10.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_CIE10.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txt_CIE10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_CIE10CaretUpdate(evt);
            }
        });

        btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarCPT.setContentAreaFilled(false);
        btnBuscarCPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
        panelCPT.setLayout(panelCPTLayout);
        panelCPTLayout.setHorizontalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_CIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_CIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel24.setBackground(new java.awt.Color(230, 230, 230));
        jLabel24.setForeground(new java.awt.Color(230, 230, 230));
        jLabel24.setText("         ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel25)
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(1, 1, 1)
                .addComponent(jLabel26)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CIE10Layout = new javax.swing.GroupLayout(CIE10.getContentPane());
        CIE10.getContentPane().setLayout(CIE10Layout);
        CIE10Layout.setHorizontalGroup(
            CIE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CIE10Layout.setVerticalGroup(
            CIE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CIE10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpanel.setBackground(new java.awt.Color(40, 40, 43));

        titulo5.setBackground(new java.awt.Color(0, 102, 102));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        titulo5.setForeground(new java.awt.Color(204, 204, 204));
        titulo5.setText("Resultados");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel5.setBackground(new java.awt.Color(40, 40, 43));

        lblNomA.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNomA.setForeground(new java.awt.Color(255, 255, 255));
        lblNomA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomA.setText("jLabel1");

        lblIDArea.setForeground(new java.awt.Color(255, 255, 255));
        lblIDArea.setText("jLabel1");

        lblCod_Personal_Sol.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Personal_Sol.setForeground(new java.awt.Color(255, 255, 255));
        lblCod_Personal_Sol.setText("codPersonal");

        lblCod_Cab_Resultado.setForeground(new java.awt.Color(255, 255, 255));
        lblCod_Cab_Resultado.setText("cod_cab_resultado");

        txtId_Documento_G.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtId_Documento_GCaretUpdate(evt);
            }
        });

        txtID_EXAMEN_CAB.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtID_EXAMEN_CAB.setText("jTextField1");
        txtID_EXAMEN_CAB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtID_EXAMEN_CABCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblIDArea)
                .addGap(79, 79, 79)
                .addComponent(lblCod_Personal_Sol)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNomA, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtID_EXAMEN_CAB, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtId_Documento_G, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCod_Cab_Resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblNomA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIDArea)
                    .addComponent(lblCod_Personal_Sol, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID_EXAMEN_CAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId_Documento_G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCod_Cab_Resultado))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblUsu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("Usuario");

        btnGuardarCabeceraRes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardarCabeceraRes.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCabeceraRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardarCabeceraRes.setText("Guardar");
        btnGuardarCabeceraRes.setContentAreaFilled(false);
        btnGuardarCabeceraRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCabeceraRes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGuardarCabeceraRes.setIconTextGap(30);
        btnGuardarCabeceraRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCabeceraResActionPerformed(evt);
            }
        });

        btnRegresarRes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegresarRes.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casa-32.png"))); // NOI18N
        btnRegresarRes.setText("Regresar");
        btnRegresarRes.setContentAreaFilled(false);
        btnRegresarRes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRegresarRes.setIconTextGap(30);
        btnRegresarRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarResActionPerformed(evt);
            }
        });

        btnGuardarCabeceraRes1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardarCabeceraRes1.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCabeceraRes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Print-32 (1).png"))); // NOI18N
        btnGuardarCabeceraRes1.setText("Imprimir FUA");
        btnGuardarCabeceraRes1.setContentAreaFilled(false);
        btnGuardarCabeceraRes1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCabeceraRes1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGuardarCabeceraRes1.setIconTextGap(30);
        btnGuardarCabeceraRes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCabeceraRes1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 265, Short.MAX_VALUE))
                            .addComponent(btnGuardarCabeceraRes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegresarRes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarCabeceraRes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(titulo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titulo5)
                .addGap(139, 139, 139)
                .addComponent(btnGuardarCabeceraRes)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarCabeceraRes1)
                .addGap(18, 18, 18)
                .addComponent(btnRegresarRes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                .addComponent(lblUsu)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        lblHospiServ.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblHospiServ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHospiServ.setText("- - -");

        lblHospi.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblHospi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHospi.setText("Hospitalización:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Habitación");

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Cama");

        txtHabitacion.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtHabitacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHabitacion.setText("- - -");
        txtHabitacion.setEnabled(false);
        txtHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHabitacionActionPerformed(evt);
            }
        });

        txtCama.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCama.setText("- - -");
        txtCama.setEnabled(false);
        txtCama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCamaActionPerformed(evt);
            }
        });

        txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPersonalSolicita.setEnabled(false);
        txtPersonalSolicita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonalSolicitaActionPerformed(evt);
            }
        });
        txtPersonalSolicita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPersonalSolicitaKeyPressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel27.setText("Médico Solicitante:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHospiServ, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel27)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHabitacion)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtPersonalSolicita)
                        .addComponent(lblHospi, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel27)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHospi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblHospiServ)
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("N° de Exámen");

        lblNumExamen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNumExamen.setForeground(new java.awt.Color(51, 51, 51));
        lblNumExamen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumExamen.setText("10000");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("N° FUA:");

        lblFUA.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        lblFUA.setForeground(new java.awt.Color(255, 255, 255));
        lblFUA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFUA.setText("----");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Forma de Pago");

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFP.setForeground(new java.awt.Color(51, 51, 51));
        lblFP.setText("forma PAGO");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Fecha y Hora Resultado");

        lblFechaReg.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFechaReg.setForeground(new java.awt.Color(51, 51, 51));
        lblFechaReg.setText("00/00/00");

        lblHoraReg.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHoraReg.setForeground(new java.awt.Color(51, 51, 51));
        lblHoraReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoraReg.setText("00:00:00");

        txtAM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAM.setEnabled(false);
        txtAM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAMCaretUpdate(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Acto Médico");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Solicitado");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Realizado");

        panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRegistraResultado.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRegistraResultado.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRegistraResultado.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRegistraResultado.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRegistraResultado.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRegistraResultadoCaretUpdate(evt);
            }
        });

        btnPersonalResultado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPersonalResultado.setContentAreaFilled(false);
        btnPersonalResultado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonalResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalResultadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
        panelCPT2.setLayout(panelCPT2Layout);
        panelCPT2Layout.setHorizontalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(txtPersonalRegistraResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT2Layout.setVerticalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPersonalRegistraResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnPersonalResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRealizaRes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRealizaRes.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRealizaRes.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRealizaRes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRealizaRes.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRealizaResCaretUpdate(evt);
            }
        });

        btnPersonalResRealiza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPersonalResRealiza.setContentAreaFilled(false);
        btnPersonalResRealiza.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonalResRealiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalResRealizaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
        panelCPT3.setLayout(panelCPT3Layout);
        panelCPT3Layout.setHorizontalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPersonalRealizaRes, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalResRealiza, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT3Layout.setVerticalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPersonalRealizaRes, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnPersonalResRealiza, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCod_Per_Registra.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Per_Registra.setText("jLabel13");

        lblCod_Per_realiza.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Per_realiza.setText("jLabel13");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Exámenes Realizados");

        txtID_COD_DOC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtID_COD_DOCCaretUpdate(evt);
            }
        });

        txtCOD_EXAMEN_DETALLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCOD_EXAMEN_DETALLEActionPerformed(evt);
            }
        });

        txtNumExamen.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        txtNumExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumExamenActionPerformed(evt);
            }
        });

        txtAM1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtAM1.setForeground(new java.awt.Color(51, 51, 51));
        txtAM1.setText("Acto Médico");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_examen_det = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_examen_det.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_examen_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5"
            }
        ));
        tb_examen_det.setRowHeight(35);
        tb_examen_det.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_examen_det.getTableHeader().setReorderingAllowed(false);
        tb_examen_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_examen_detMouseClicked(evt);
            }
        });
        tb_examen_det.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_examen_detKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_examen_det);

        panelCPT5.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRealizaRes1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRealizaRes1.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRealizaRes1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRealizaRes1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRealizaRes1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRealizaRes1CaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
        panelCPT5.setLayout(panelCPT5Layout);
        panelCPT5Layout.setHorizontalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT5Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txtPersonalRealizaRes1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        panelCPT5Layout.setVerticalGroup(
            panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtPersonalRealizaRes1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Nº de Placas");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Medidas");

        cbxMEDIDAS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxMEDIDAS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Incidencia");

        cbxINCIDENCIA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxINCIDENCIA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxMEDIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxINCIDENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxINCIDENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxMEDIDAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        panelCPT6.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRealizaRes2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRealizaRes2.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRealizaRes2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRealizaRes2.setText("CIE 10");
        txtPersonalRealizaRes2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRealizaRes2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRealizaRes2CaretUpdate(evt);
            }
        });

        btnBuscarCIE10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarCIE10.setContentAreaFilled(false);
        btnBuscarCIE10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCIE10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCIE10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
        panelCPT6.setLayout(panelCPT6Layout);
        panelCPT6Layout.setHorizontalGroup(
            panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPersonalRealizaRes2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT6Layout.setVerticalGroup(
            panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPersonalRealizaRes2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAMB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAMBCaretUpdate(evt);
            }
        });
        txtAMB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAMBActionPerformed(evt);
            }
        });

        txtCOD_DET_RES.setText("ID_DX");
        txtCOD_DET_RES.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCOD_DET_RESCaretUpdate(evt);
            }
        });

        EP_CONCLUSION.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EP_CONCLUSION.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                EP_CONCLUSIONCaretUpdate(evt);
            }
        });
        jScrollPane9.setViewportView(EP_CONCLUSION);

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Diagnósticos");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Relato");

        EP_Descripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EP_Descripcion.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                EP_DescripcionCaretUpdate(evt);
            }
        });
        jScrollPane3.setViewportView(EP_Descripcion);

        tbCIE10 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbCIE10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbCIE10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCIE10.setRowHeight(25);
        tbCIE10.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tbCIE10.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tbCIE10);

        BTN_QUITAR_CIE10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BTN_QUITAR_CIE10.setForeground(new java.awt.Color(51, 51, 51));
        BTN_QUITAR_CIE10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
        BTN_QUITAR_CIE10.setText("Quitar");
        BTN_QUITAR_CIE10.setContentAreaFilled(false);
        BTN_QUITAR_CIE10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_QUITAR_CIE10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTN_QUITAR_CIE10))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCOD_DET_RES, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCAB_RESULTADO, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAMB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblId_Preventa, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_QUITAR_CIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtCOD_DET_RES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCAB_RESULTADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lblId_Preventa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("jLabel1");

        panelFormaPago1.setBackground(new java.awt.Color(255, 255, 255));
        panelFormaPago1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jTextField1.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        jTextField1.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField1CaretUpdate(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormaPago1Layout = new javax.swing.GroupLayout(panelFormaPago1);
        panelFormaPago1.setLayout(panelFormaPago1Layout);
        panelFormaPago1Layout.setHorizontalGroup(
            panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormaPago1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelFormaPago1Layout.setVerticalGroup(
            panelFormaPago1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormaPago1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtID_COD_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCOD_EXAMEN_DETALLE, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCod_Per_Registra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCod_Per_realiza))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel29)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lblNumExamen)
                                        .addGap(101, 101, 101)
                                        .addComponent(jLabel1))
                                    .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(141, 141, 141)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel36)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel23)))
                                        .addGap(42, 42, 42))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFechaReg, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAM1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(lblHoraReg, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblFUA, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(panelFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel30)
                                        .addComponent(lblFechaReg)
                                        .addComponent(lblHoraReg)
                                        .addComponent(lblFUA)))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(lblNumExamen))))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel36)
                                    .addComponent(txtAM1))
                                .addComponent(txtAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel37)
                                .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelCPT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtID_COD_DOC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCOD_EXAMEN_DETALLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCod_Per_Registra)
                    .addComponent(lblCod_Per_realiza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(209, 52, 56));

        txtNombreP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreP.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreP.setText("jLabel8");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("DNI");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("N° HC");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Edad");

        txtEdad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(255, 255, 255));
        txtEdad.setText("DNI");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Género");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Fecha Nacimiento");

        txtGenero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGenero.setForeground(new java.awt.Color(255, 255, 255));
        txtGenero.setText("DNI");

        txtFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtFechaNac.setForeground(new java.awt.Color(255, 255, 255));
        txtFechaNac.setText("DNI");

        txtHC.setText("jTextField1");

        txtHC2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtHC2.setForeground(new java.awt.Color(255, 255, 255));
        txtHC2.setText("DNI");

        txtDNI.setText("jTextField1");

        txtHC3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtHC3.setForeground(new java.awt.Color(255, 255, 255));
        txtHC3.setText("DNI");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEdad)
                            .addComponent(txtHC2)
                            .addComponent(txtHC3))
                        .addGap(245, 245, 245)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaNac)
                            .addComponent(txtGenero)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txtNombreP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel34)
                    .addComponent(txtGenero)
                    .addComponent(txtHC3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel35)
                    .addComponent(txtFechaNac)
                    .addComponent(txtHC2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtEdad)
                    .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

        Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Mensaje.setForeground(new java.awt.Color(255, 255, 255));
        Mensaje.setText("Desea Actualizar el Registro ?");

        eli.setForeground(new java.awt.Color(240, 240, 240));
        eli.setText("Si");
        eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        eli.setContentAreaFilled(false);
        eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eli.setIconTextGap(30);
        eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliActionPerformed(evt);
            }
        });

        noeli.setForeground(new java.awt.Color(240, 240, 240));
        noeli.setText("No");
        noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        noeli.setContentAreaFilled(false);
        noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        noeli.setIconTextGap(30);
        noeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noeliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
        cargareliminar.setLayout(cargareliminarLayout);
        cargareliminarLayout.setHorizontalGroup(
            cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cargareliminarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cargareliminarLayout.setVerticalGroup(
            cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cargareliminarLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mensaje)
                    .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_Personal_rolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_rolKeyPressed
         char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol.getRowCount() == 0 &&
            this.tb_Personal_rol.getSelectedRow() == -1){

                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("Tabla Vacia");
                eli.setText("SI");
                noeli.setText("OK");
                eli.setVisible(false);
                noeli.setVisible(true);

                cargareliminar.setVisible(true);


        }else
        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol.getRowCount() != 0 &&
            this.tb_Personal_rol.getSelectedRow() != -1){
            int fila = tb_Personal_rol.getSelectedRow();
            //frm_SALA_OPERACION_TIPO_ANESTESIA T = new frm_SALA_OPERACION_TIPO_ANESTESIA();
            //T.setVisible(true);
            
            String apep, apem, nom;
            apep = tb_Personal_rol.getValueAt(fila, 2).toString();
            apem = tb_Personal_rol.getValueAt(fila, 3).toString();
            nom = tb_Personal_rol.getValueAt(fila, 4).toString();
            
            if(lblPerB.getText().equalsIgnoreCase("B1")){
                txtPersonalRealizaRes.setText(String.valueOf(apep + " " + apem + " " + nom));
                lblCod_Per_realiza.setText(String.valueOf(tb_Personal_rol.getValueAt(fila, 1)));
                    
                tb_examen_det.setEnabled(true);
                    tb_examen_det.setBackground(Color.white);
                    tb_examen_det.getSelectionModel().setSelectionInterval(0, 0);
                    tb_examen_det.requestFocus();
                
            }else{
                if(lblPerB.getText().equalsIgnoreCase("B2")){
                    txtPersonalRegistraResultado.setText(String.valueOf(apep + " " + apem + " " + nom));
                    lblCod_Per_Registra.setText(String.valueOf(tb_Personal_rol.getValueAt(fila, 1)));
                    btnPersonalResRealiza.requestFocus();
                    
                    
                }
            }
                                  
            PERSONAL_ROL.dispose();
            txtBuscarPersonal.setText("");
            
//            txtNumeroRegla.setEnabled(false);
//            txtDescripcionRegla.setEnabled(false);
//            btnmodificar.setEnabled(true);
//            btneliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tb_Personal_rolKeyPressed

    private void txtID_EXAMEN_CABCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtID_EXAMEN_CABCaretUpdate
               mostrar_VER_DETALLE_RESULTADO();    
               mostrar_FP_RESULTADO(txtID_EXAMEN_CAB.getText());
               mostrar_Personal_Solicita(txtID_EXAMEN_CAB.getText());
               RX_EC_VER_CODIGO_CAB(txtID_EXAMEN_CAB.getText());
    }//GEN-LAST:event_txtID_EXAMEN_CABCaretUpdate

    private void tb_examen_detKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_examen_detKeyPressed
        
    }//GEN-LAST:event_tb_examen_detKeyPressed

    private void txtPersonalSolicitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyPressed
        //        char tecla= evt.getKeyChar();
        //        if(tecla==KeyEvent.VK_ENTER){
            //            personal.setVisible(true);
            //            txtBuscar.setText("");
            //            Personal_cargar();
            //            Personal_formato();
            //        }
    }//GEN-LAST:event_txtPersonalSolicitaKeyPressed

    private void tb_Personal_rol_todoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_rol_todoKeyPressed
        char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_todo.getRowCount() == 0 &&
            this.tb_Personal_rol_todo.getSelectedRow() == -1){

            cargareliminar.setBackground(new Color(255,91,70));
            Mensaje.setText("Tabla Vacia");
            eli.setText("SI");
            noeli.setText("OK");
            eli.setVisible(false);
            noeli.setVisible(true);

            cargareliminar.setVisible(true);


        }else
        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_todo.getRowCount() != 0 &&
            this.tb_Personal_rol_todo.getSelectedRow() != -1){
            int fila = tb_Personal_rol_todo.getSelectedRow();
           
            String apep, apem, nom;
            apep = tb_Personal_rol_todo.getValueAt(fila, 2).toString();
            apem = tb_Personal_rol_todo.getValueAt(fila, 3).toString();
            nom = tb_Personal_rol_todo.getValueAt(fila, 4).toString();
                     
                txtPersonalSolicita.setText(String.valueOf(apep + " " + apem + " " + nom));
                lblCod_Personal_Sol.setText(String.valueOf(tb_Personal_rol_todo.getValueAt(fila, 1)));
                
                                  
            PERSONAL_ROL_TODO.dispose();
            txtBuscarPersonal_TODO.setText("");
            
            
            
//            txtNumeroRegla.setEnabled(false);
//            txtDescripcionRegla.setEnabled(false);
//            btnmodificar.setEnabled(true);
//            btneliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tb_Personal_rol_todoKeyPressed

    private void tb_examen_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_examen_detMouseClicked
        if(evt.getClickCount()==1){
         try{
                        if( tb_examen_det.getRowCount()>0){
                        int filaselec=tb_examen_det.getSelectedRow();
                            
                            txtID_COD_DOC.setText(tb_examen_det.getValueAt(filaselec, 0).toString());
                            jLabel1.setText(tb_examen_det.getValueAt(filaselec, 7).toString());
                            EP_Descripcion.setEnabled(true);
                            EP_CONCLUSION.setEnabled(true);
                            btnBuscarCIE10.setEnabled(true);
                            btnGuardarCabeceraRes.setEnabled(false);  
                            BTN_QUITAR_CIE10.setEnabled(true);
                            jPanel1.setVisible(true);
                        }
                        txtPersonalRealizaRes1.requestFocus();
        }catch(Exception e){
            
        }
       }
    }//GEN-LAST:event_tb_examen_detMouseClicked

    private void txtHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHabitacionActionPerformed

    private void txtCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaActionPerformed

    private void txtId_Documento_GCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtId_Documento_GCaretUpdate
//        mostrar_Cod_Cabecera_RESULTADO(txtId_Documento_G.getText());
    }//GEN-LAST:event_txtId_Documento_GCaretUpdate

    private void txtAMBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAMBCaretUpdate
        mostrarHospitalizacion(Integer.parseInt(txtAMB.getText()));
    }//GEN-LAST:event_txtAMBCaretUpdate

    private void txtAMBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAMBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAMBActionPerformed

    private void txtNumExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumExamenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumExamenActionPerformed

    private void txtPersonalSolicitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaActionPerformed

    private void txtCOD_EXAMEN_DETALLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCOD_EXAMEN_DETALLEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCOD_EXAMEN_DETALLEActionPerformed

    private void txtID_COD_DOCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtID_COD_DOCCaretUpdate
        mostrar_COD_EXAMEN_DETALLE(txtID_COD_DOC.getText());
    }//GEN-LAST:event_txtID_COD_DOCCaretUpdate

    private void tb_CIE10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CIE10KeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     CIE10.setVisible(false);
                    mostrarDiagnostico();
                }
    }//GEN-LAST:event_tb_CIE10KeyPressed

    private void txtCOD_DET_RESCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCOD_DET_RESCaretUpdate
           
    }//GEN-LAST:event_txtCOD_DET_RESCaretUpdate

    private void btnGuardarCabeceraResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCabeceraResActionPerformed
        if(txtCAB_RESULTADO.getText().equalsIgnoreCase("")){
            guardar_resultado_cabecera();   
            mostrar_VER_DETALLE_RESULTADO();  
//            mostrar_VER_DETALLE_EC();         
//            btnGuardarCabeceraRes.setEnabled(false); 
            if(lblFP.getText().equals("SIS  FUA ")){
                EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO cno1 = new EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO();
                cno1.setID_DETALLE(Integer.parseInt(txtID_COD_DOC.getText()));
                cno1.setFUA(Integer.parseInt(jTextField1.getText()));
                cno1.setSERVICIO("RX");
                                if(cno1.EC_FUA_LOCAL()==true){
                                       System.out.println("FUA LOCAL GENERADO");
                                } else {
                                        System.out.println("ERROR GENERAR FUA LOCAL");
                                }
            }
            
        }else{
            if(txtPersonalRealizaRes.getText().equalsIgnoreCase("") || txtPersonalRegistraResultado.getText().equalsIgnoreCase("")
                        || EP_Descripcion.getText().equalsIgnoreCase("") || EP_CONCLUSION.getText().equalsIgnoreCase("")
                        || tbCIE10.getRowCount()==0){
                
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("Verifique si ha seleccionado el personal o completado todos los campos");
                eli.setText("SI");
                noeli.setText("OK");
                eli.setVisible(false);
                noeli.setVisible(true);
                cargareliminar.setVisible(true);
            }
            else{
                cargareliminar.setBackground(new Color(255,153,51));
                Mensaje.setText("Guardar este Resultado?");
                eli.setText("SI");
                noeli.setText("NO");
                eli.setVisible(true);
                noeli.setVisible(true);
                cargareliminar.setVisible(true);
                tg=1;
            }
        }
    }//GEN-LAST:event_btnGuardarCabeceraResActionPerformed

    private void btnRegresarResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarResActionPerformed
         try {
            this.dispose();
            RX_EC_BUSCAR_EXAMEN_RESULTADO b = new RX_EC_BUSCAR_EXAMEN_RESULTADO();
            b.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnRegresarResActionPerformed

    private void BTN_QUITAR_CIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_QUITAR_CIE10ActionPerformed
        
        cargareliminar.setBackground(new Color(255,91,70));
        Mensaje.setText("¿Está seguro que desea QUITAR el Diagnóstico ?");
        eli.setText("SI");
        noeli.setText("NO");
        eli.setVisible(true);
        noeli.setVisible(true);
        cargareliminar.setVisible(true);
        tg=3;
    }//GEN-LAST:event_BTN_QUITAR_CIE10ActionPerformed

    private void EP_CONCLUSIONCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_EP_CONCLUSIONCaretUpdate
        btnGuardarCabeceraRes.setEnabled(true);
    }//GEN-LAST:event_EP_CONCLUSIONCaretUpdate

    private void txtAMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAMCaretUpdate
       txtAMB.setText(txtAM.getText());
    }//GEN-LAST:event_txtAMCaretUpdate

    private void EP_DescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_EP_DescripcionCaretUpdate
        btnGuardarCabeceraRes.setEnabled(true);
    }//GEN-LAST:event_EP_DescripcionCaretUpdate

    private void txtPersonalRegistraResultadoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRegistraResultadoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRegistraResultadoCaretUpdate

    private void btnPersonalResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalResultadoActionPerformed
         PERSONAL_ROL.setVisible(true);
         cargareliminar.setVisible(false);
//        tb_Personal_rol.getSelectionModel().setSelectionInterval(0, 0);
//        tb_Personal_rol.requestFocus();
        txtBuscarPersonal.requestFocus();
        lblPerB.setText("B2");
        try {
            buscarPersonal();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPersonalResultadoActionPerformed

    private void txtPersonalRealizaResCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRealizaResCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRealizaResCaretUpdate

    private void btnPersonalResRealizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalResRealizaActionPerformed
        PERSONAL_ROL.setVisible(true);
        cargareliminar.setVisible(false);
        txtBuscarPersonal.requestFocus();
        lblPerB.setText("B1");
        try {
            buscarPersonal_todo();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPersonalResRealizaActionPerformed

    private void txt_CIE10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_CIE10CaretUpdate
       buscarDiagnostico();

    }//GEN-LAST:event_txt_CIE10CaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
       
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void txtBuscarPersonalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPersonalCaretUpdate
        buscarPersonal();

    }//GEN-LAST:event_txtBuscarPersonalCaretUpdate

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        buscarPersonal();
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void txtBuscarPersonal_TODOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPersonal_TODOCaretUpdate
        buscarPersonal_todo();

    }//GEN-LAST:event_txtBuscarPersonal_TODOCaretUpdate

    private void btnBuscarCPT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT2ActionPerformed
        buscarPersonal_todo();
    }//GEN-LAST:event_btnBuscarCPT2ActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        
        cargareliminar.setVisible(false);
        if(tg==1){
            RX_EC_ESTADO_RESULTADO_MODIFICAR();
            GUARDAR_RESULTADO_DETALLE_RX_CONC();
            mostrar_VER_DETALLE_RESULTADO();
            btnGuardarCabeceraRes.setEnabled(false);

            EP_Descripcion.setEnabled(false);
            EP_CONCLUSION.setEnabled(false);
            btnBuscarCIE10.setEnabled(false);
        }else if(tg==2){
            RX_EC_RESULTADO_CAB rxg = new RX_EC_RESULTADO_CAB();
                        rxg.setCOD_EXAMEN_CAB(Integer.parseInt(txtID_EXAMEN_CAB.getText()));
                        rxg.setNUMERO_RESULTADO(lblNumExamen.getText());
                        rxg.setCOD_PERSONAL_RESULTADO(lblCod_Per_realiza.getText());
                        rxg.setNOMBRE_PERSONAL_RESULTADO(txtPersonalRealizaRes.getText());
                        rxg.setCOD_PERSONAL_RESULTADO_REG(lblCod_Per_Registra.getText());
                        rxg.setNOMBRE_PERSONAL_RESULTADO_REG(txtPersonalRegistraResultado.getText());                       
                        rxg.setNOM_USU(lblUsu.getText());
                        
                        
                        if(rxg.RX_EC_RESULTADO_GUARDAR()){
                            cargareliminar.setBackground(new Color(0,153,102));
                            Mensaje.setText("Resultado Guardado");
                            eli.setText("SI");
                            noeli.setText("OK");
                            eli.setVisible(false);
                            noeli.setVisible(true);
                            cargareliminar.setVisible(true);
                            txtCAB_RESULTADO.setText(rxg.RX_EC_ID_CAB_RES());
                            RX_EC_ESTADO_RESULTADO_MODIFICAR();
                             EP_Descripcion.setEnabled(false);
                                EP_CONCLUSION.setEnabled(false);
                                btnBuscarCIE10.setEnabled(false);
                            
                            if(EP_Descripcion.getText().length()!=0 && EP_CONCLUSION.getText().length()!=0){
                                GUARDAR_RESULTADO_DETALLE_RX();
                                
//                                HABILITAR();
                            }
                            
                            //deshabilitar();
                            //btnguardar.setEnabled(false);
                        }
                        else{
                            cargareliminar.setBackground(new Color(255,91,70));
                            Mensaje.setText("El registro ya existe Intente nuevamente");
                            eli.setText("SI");
                            noeli.setText("OK");
                            eli.setVisible(false);
                            noeli.setVisible(true);
                            cargareliminar.setVisible(true);
                        }
        }else if(tg==3){
            try{
            int filaselec=tbCIE10.getSelectedRow();
            if( filaselec>=0){  
                    DefaultTableModel modelo = (DefaultTableModel)tbCIE10.getModel();
                    modelo.removeRow(filaselec);
            }else{
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("Seleccione el registro a Eliminar");
                eli.setText("SI");
                noeli.setText("OK");
                eli.setVisible(false);
                noeli.setVisible(true);
                cargareliminar.setVisible(true);

            }
        }catch(Exception e){
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("Seleccione el registro a Eliminar");
                eli.setText("SI");
                noeli.setText("OK");
                eli.setVisible(false);
                noeli.setVisible(true);
                cargareliminar.setVisible(true);
        }
        }
        
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed

        btnGuardarCabeceraRes.setEnabled(true);
        cargareliminar.setVisible(false);

      
            
          
    }//GEN-LAST:event_noeliActionPerformed

    private void txtPersonalRealizaRes1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRealizaRes1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRealizaRes1CaretUpdate

    private void txtPersonalRealizaRes2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRealizaRes2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRealizaRes2CaretUpdate

    private void btnBuscarCIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCIE10ActionPerformed
        CIE10.setVisible(true);
        tb_CIE10.getSelectionModel().setSelectionInterval(0, 0);
        txt_CIE10.requestFocus();
        txt_CIE10.setText("");
    }//GEN-LAST:event_btnBuscarCIE10ActionPerformed

    private void btnGuardarCabeceraRes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCabeceraRes1ActionPerformed
        Caja_NuevaVenta F = new Caja_NuevaVenta();
                try {
                    System.out.println(txtAM1.getText());//actomedic
                    F.reporteFUA(txtAM1.getText());
                    System.out.println("Imprimiendo FUA");
                } catch (Exception e) {
                }
    }//GEN-LAST:event_btnGuardarCabeceraRes1ActionPerformed

    private void jTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1CaretUpdate

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

      
    public void mostrar_Cod_Cabecera_RESULTADO(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_RESULTADO_CAB_COD_VER ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblCod_Cab_Resultado.setText(r.getString(1));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }
    
    public void mostrar_COD_EXAMEN_DETALLE(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_VER_COD_EXAMEN_DETALLE ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtCOD_EXAMEN_DETALLE.setText(r.getString(1));               
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga cod EXAMEN DETALLE: " + e.getMessage());
        }
    }
    
    public void guardarDetalleExamen(){
         for (int i = 0; i < tb_examen_det.getRowCount(); i++){      
               RX_EC_EXAMEN_DET dd=new RX_EC_EXAMEN_DET();
               dd.setID_EXAMEN_CAB(Integer.parseInt(lblCod_Cab_Resultado.getText()));
               dd.setID_COD_DOC_DET(Integer.parseInt(tb_examen_det.getValueAt(i, 0).toString()));
               dd.setCOD_PER_SOL(lblCod_Personal_Sol.getText());
               dd.setNOM_PER_SOL(txtPersonalSolicita.getText());
               dd.setINCIDENCIA(tb_examen_det.getValueAt(i, 3).toString());
               dd.setID_PREVENTA(lblId_Preventa.getText());
               dd.setHAB_NOM(txtHabitacion.getText());
               dd.setCA_DESC(txtCama.getText());
               dd.setHOSP_SERVICIO(lblHospiServ.getText());             
               dd.setNOM_USU(lblUsu.getText());
               dd.RX_EC_EXAMEN_DETALLE_GUARDAR();               
            }     
    }
    
    public void mostrarHospitalizacion(int AM){
        String consulta="";
        try {
            consulta="EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setInt(1, AM);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblHospiServ.setText(r.getString(5));
                txtHabitacion.setText(r.getString(3));
                txtCama.setText(r.getString(4));
                lblId_Preventa.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga prestacion_B: " + e.getMessage());
        }
    }
    
    public DefaultComboBoxModel tipo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("select * from RX_EC_MEDIDAS"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "MEDIDA" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public DefaultComboBoxModel tipo1(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("select DISTINCT * from RX_EC_INCIDENCIAS"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "INCIDENCIA" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void buscarPersonal(){
        String consulta="";
        try {
            tb_Personal_rol.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Per","Médico Solicitante","Apellido Materno","Nombres","Cargo","Servicio",
                 "Cod. Servicio"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[8];

            RX_EC_EXAMEN obj=new RX_EC_EXAMEN();
                    consulta="exec RX_EC_PERSONAL_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarPersonal.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                m2.addRow(fila);
                c++;
            }
            tb_Personal_rol.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_Personal_rol.setRowSorter(elQueOrdena);
            this.tb_Personal_rol.setModel(m2);
            
            formato_Personal_Rol();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void mostrarArea(){
        String consulta="";
        try {
            consulta="EXEC RX_EC_SERVICIO ";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);        
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblIDArea.setText(r.getString(1));
                lblNomA.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga area : " + e.getMessage());
        }
    }
    
    public void CargarPersonalRol(){
        try {
             String titulos[]={"Nº","Cod. Per","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio",
                 "Cod. Servicio"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[8];

        String consulta="exec RX_EC_PERSONAL_LISTAR";
        ResultSet r;
        
        r=conectar.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
            fila[7]=r.getString(7);
                m1.addRow(fila);
                c++;
            }
            tb_Personal_rol.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            tb_Personal_rol.setRowSorter(elQueOrdena);
            this.tb_Personal_rol.setModel(m1);
            formato_Personal_Rol();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
        }
    }
    
    public void formato_Personal_Rol(){
        tb_Personal_rol.getColumnModel().getColumn(0).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(1).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(1).setMaxWidth(0);

        tb_Personal_rol.getColumnModel().getColumn(2).setPreferredWidth(400);
        tb_Personal_rol.getColumnModel().getColumn(3).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(4).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(5).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(5).setMaxWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(6).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(6).setMaxWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(7).setMinWidth(0);
        tb_Personal_rol.getColumnModel().getColumn(7).setMaxWidth(0);
    }
    
    public void formato_Personal_Rol_todo(){
        tb_Personal_rol_todo.getColumnModel().getColumn(0).setMinWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(1).setMinWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(1).setMaxWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(2).setPreferredWidth(400);
        tb_Personal_rol_todo.getColumnModel().getColumn(3).setMinWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(3).setMaxWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(4).setMinWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(5).setMinWidth(0);
        tb_Personal_rol_todo.getColumnModel().getColumn(5).setMaxWidth(0); 
        
    }
    
    public void guardar_resultado_cabecera(){
        RX_EC_EXAMEN C=new RX_EC_EXAMEN();
        RX_EC_EXAMEN C1=new RX_EC_EXAMEN();
        RX_EC_EXAMEN C2=new RX_EC_EXAMEN();
        try{          
                if(txtPersonalRealizaRes.getText().equalsIgnoreCase("") || txtPersonalRegistraResultado.getText().equalsIgnoreCase("")
                        || EP_Descripcion.getText().equalsIgnoreCase("") || EP_CONCLUSION.getText().equalsIgnoreCase("")
                        || tbCIE10.getRowCount()==0){
                    cargareliminar.setBackground(new Color(255,91,70));
                    Mensaje.setText("Verifique si ha seleccionado el personal o completado todos los campos");
                    eli.setText("SI");
                    noeli.setText("OK");
                    eli.setVisible(false);
                    noeli.setVisible(true);
                    cargareliminar.setVisible(true);
                }
                else{
                    tg=2;
                    cargareliminar.setBackground(new Color(255,153,51));
                    Mensaje.setText("¿Guardar este Resultado?");
                    eli.setText("SI");
                    noeli.setText("NO");
                    eli.setVisible(true);
                    noeli.setVisible(true);
                    cargareliminar.setVisible(true);
                    }
            
        }catch(Exception e){
                    cargareliminar.setBackground(new Color(255,91,70));
                    Mensaje.setText("Verifique si ha ingresado todos los campos");
                    eli.setText("SI");
                    noeli.setText("OK");
                    eli.setVisible(false);
                    noeli.setVisible(true);
                    cargareliminar.setVisible(true);
        }

    }
    
    public void HABILITAR(){
        EP_Descripcion.setEnabled(true);
        EP_CONCLUSION.setEnabled(true);
        btnBuscarCIE10.setEnabled(true);
    }
    
    public void RX_EC_VER_CODIGO_CAB(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_CODIGO_CAB_GUARDAR_CONC ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtCAB_RESULTADO.setText(r.getString(1));
                txtPersonalRegistraResultado.setText(r.getString(2));
                txtPersonalRealizaRes.setText(r.getString(3));
          
            }
            
        }catch (Exception e) {
            System.out.println("Error carga cod cabecera RX: " + e.getMessage());
        }
    }
    
    public void inicializar_tabla_Examenes_detalle(){       
        try {
            
            String titulosb[]={"Cod. Documento Det.","Cod. Nomenclatura","Descripción Nomenclatura",
            "Incidencia","Placas Usadas","Medidas","Producto"};
            msb1=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb1);
            String filasb[]=new String[7];
            tb_examen_det.setModel(msb1);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb1);
            tb_examen_det.setRowSorter(elQueOrdenasb);
            tb_examen_det.setModel(msb1);
            
            formatoExamen_detalle();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }
            
    }
    
    public void inicializar_tabla_cie10(){       
        try {
            
            String titulosb[]={"ID CIE10","Cod. Enf.","Descripción"};
            msb3=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb3);
            String filasb[]=new String[3];
            tbCIE10.setModel(msb3);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb3);
            tbCIE10.setRowSorter(elQueOrdenasb);
            tbCIE10.setModel(msb3);
            
            formatoCIE10_O();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }
            
    }
    
    public void formatoExamen_detalle(){                    
            tb_examen_det.getColumnModel().getColumn(0).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(0).setMaxWidth(0);  
            tb_examen_det.getColumnModel().getColumn(1).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(1).setMaxWidth(0); 
            tb_examen_det.getColumnModel().getColumn(2).setPreferredWidth(350);
            tb_examen_det.getColumnModel().getColumn(3).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(3).setMaxWidth(0); 
            tb_examen_det.getColumnModel().getColumn(4).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(4).setMaxWidth(0); 
            tb_examen_det.getColumnModel().getColumn(5).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(5).setMaxWidth(0); 
            tb_examen_det.getColumnModel().getColumn(6).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(6).setMaxWidth(0); 
            tb_examen_det.getColumnModel().getColumn(7).setMinWidth(0);
            tb_examen_det.getColumnModel().getColumn(7).setMaxWidth(0); 
            //Ocultar
   
            
    }
    
    
    
    public void mostrar_VER_DETALLE_RESULTADO(){
        try {
                    
                        //detalle
                        String consulta="";
                        tb_examen_det.setModel(new DefaultTableModel());
                        String titulos[]={"Cod. Documento Det.","Cod. Nomenclatura","Descripción Nomenclatura",
                        "Incidencia","Placas Usadas","Medida","Producto","DET"};
                        m3=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m3);
                        String fila[]=new String[8];
                        Usuario obj=new Usuario();
                        consulta="exec RX_EC_VER_DETALLE_RESULTADO ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, txtID_EXAMEN_CAB.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, usado=0;
                        while(r.next()){
//                        for (int i=0; i<5; i++){
                        fila[0]=r.getString(1);
                        fila[1]=r.getString(2); 
                        fila[2]=r.getString(3); 
                        fila[3]=r.getString(4); 
                        usado = (r.getInt(5));
                
                        BigDecimal bd2 = new BigDecimal(usado);

                        bd2 = bd2.setScale(0, BigDecimal.ROUND_HALF_UP);
                        
                        fila[4]="0" + bd2;
                        fila[5]=r.getString(6); 
                        fila[6]=r.getString(7);
                        fila[7]=r.getString(8);

//                        }
//                        String a = "sql";
//                        String texto = "lenguaje sql";
//                        int intIndex = texto.indexOf(a);
//                      
//                        String medida="";
//                        String med1 ="";
//                        String cadenaDondeBuscar = r.getString(5);
//                        String loQueQuieroBuscar = "TAMAÑO";
//                        med1 = loQueQuieroBuscar.substring(0,loQueQuieroBuscar.length());
//                       
//                        int lqb = loQueQuieroBuscar.length();
//                        int a;
//                        a=Integer.parseInt(med1);
//                        String[] palabras = loQueQuieroBuscar.split("\\s+");
//                        for (String palabra : palabras) {
//                            if (cadenaDondeBuscar.contains(palabra)) {
//                                System.out.println("Encontrado");
//                                medida = cadenaDondeBuscar.substring(a,15);
//                                
//                                System.out.println("valor" + medida);
//                                //aquí tu lógica en caso que se haya encontrado...
//                            }
//                        }
                        
                          m3.addRow(fila);
                        }
                        tb_examen_det.setModel(m3);
                        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
                        tb_examen_det.setRowSorter(elQueOrdena);
                        tb_examen_det.setModel(m3);

                        formatoExamen_detalle();           
                      
        } catch (Exception e) {
            System.out.println("Error ver detalle resultado: " + e.getMessage());
        }
        
    }

    
    public void buscarPersonal_todo(){
        String consulta="";
        try {
            tb_Personal_rol_todo.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Per","Responsable del Resultado","Apellido Materno","Nombres","Cargo"};
            m4=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m4);
            String fila[]=new String[6];

            RX_EC_EXAMEN obj=new RX_EC_EXAMEN();
                    consulta="exec RX_EC_PERSONAL_BUSCAR_TODO ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarPersonal_TODO.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                
                m4.addRow(fila);
                c++;
            }
            tb_Personal_rol_todo.setModel(m4);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m4);
            tb_Personal_rol_todo.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_todo.setModel(m4);
            
            formato_Personal_Rol_todo();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void CargarPersonalRol_todo(){
        try {
             String titulos[]={"Nº","Cod. Per","Apellido Paterno","Apellido Materno","Nombres","Cargo"};
            m5=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m5);
            String fila[]=new String[6];

        String consulta="exec RX_EC_PERSONAL_LISTAR_TODO";
        ResultSet r;
        
        r=conectar.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            
                m5.addRow(fila);
                c++;
            }
            tb_Personal_rol_todo.setModel(m5);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m5);
            tb_Personal_rol_todo.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_todo.setModel(m5);
            formato_Personal_Rol_todo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la tabla personal T");
        }
    }
   
//       
//    public void guardar_examen_detalle(){
//        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
//        RX_EC_EXAMEN_DET Cd=new RX_EC_EXAMEN_DET();
//        RX_EC_EXAMEN_DET Cd1=new RX_EC_EXAMEN_DET();
//        RX_EC_EXAMEN_DET Cd2=new RX_EC_EXAMEN_DET();
//        try{          
//                if(txtPersonalSolicita.getText().equalsIgnoreCase("")){
//                    JOptionPane.showMessageDialog(rootPane, "Verifique si ha seleccionado el personal");
//                }
//                else{
//
//                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                    if(guardar == 0 ){
//                        RX_EC_EXAMEN_DET g = new RX_EC_EXAMEN_DET();
//                        JOptionPane.showMessageDialog(null, "Datos Guardados");
//                        guardarDetalleExamen();
//                        RX_EC_ESTADO_MODIFICAR();
//                            //limpiar();
//                            //deshabilitar();
//                            //btnguardar.setEnabled(false);                       
//                        }
//                    }
//            
//        }catch(Exception e){
//                System.out.println("error guardar detalle" + e);
//        }
//
//    }
    
public void guardar_resultado_detalle(){
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        
        try{          
                if(EP_Descripcion.getText().equalsIgnoreCase("") || EP_CONCLUSION.getText().equalsIgnoreCase("")
                        || txtID_COD_DOC.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Verifique si ha seleccionado un examen \n o ha ingresado todos los campos");
                }
                else{

                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                        RX_EC_RESULTADO_DETALLE rxg1 = new RX_EC_RESULTADO_DETALLE();
//                        RX_EC_RESULTADO_DETALLE_DIAGNOSTICO r = new RX_EC_RESULTADO_DETALLE_DIAGNOSTICO();
                        rxg1.setCOD_CAB_RESULTADO(Integer.parseInt(lblCod_Cab_Resultado.getText()));
                        rxg1.setCOD_EXAMEN_DETALLE(Integer.parseInt(txtCOD_EXAMEN_DETALLE.getText()));
                        rxg1.setDESCRIPCION_RESULTADO(EP_Descripcion.getText());
                        rxg1.setCONCLUSION_DIAGNOSTICA(EP_CONCLUSION.getText());                   
                        rxg1.setNOM_USU(lblUsu.getText());
                        rxg1.setCANTIDAD(Integer.parseInt(txtPersonalRealizaRes1.getText()));
                        rxg1.setMEDIDA(cbxMEDIDAS.getSelectedItem().toString());
                        rxg1.setINCIDENCIA(cbxINCIDENCIA.getSelectedItem().toString());
                        if(rxg1.RX_EC_RESULTADO_DETALLE_GUARDAR()){
                            JOptionPane.showMessageDialog(null, "Datos Guardados");
                            RX_EC_ESTADO_RESULTADO_MODIFICAR();
                            txtCOD_DET_RES.setText(rxg1.RX_EC_ID());
                            if(tbCIE10.getRowCount()!=0){
                            guardarDetalleDiagnostico();
                            }

//                            RX_EC_RESULTADO_DETALLE_DIAGNOSTICO Dd = new RX_EC_RESULTADO_DETALLE_DIAGNOSTICO();
//                            int a = Integer.parseInt(Dd.RX_EC_ID_DX());
//                            lblId_DX.setText(a + "");
//                            guardarDetalleDiagnostico();
                            //limpiar();
                            //deshabilitar();
                            //btnguardar.setEnabled(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "El registro ya existe\nIntente nuevamente");

                        }}
                    }
            
        }catch(Exception e){
                 JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
        }

    }

    

    public void GUARDAR_RESULTADO_DETALLE_RX(){  
        try {
        int id = Integer.parseInt(txtCAB_RESULTADO.getText());
            if(EP_CONCLUSION.getText().equalsIgnoreCase("") || EP_Descripcion.getText().equalsIgnoreCase("")){
                cargareliminar.setBackground(new Color(255,91,70));
                Mensaje.setText("Asegurese de Ingresar Todos los Campos");
                eli.setText("SI");
                noeli.setText("OK");
                eli.setVisible(false);
                noeli.setVisible(true);
                cargareliminar.setVisible(true);
            }else{
                RX_EC_RESULTADO_DETALLE rxg1 = new RX_EC_RESULTADO_DETALLE(); 
                rxg1.setCOD_CAB_RESULTADO(id);
                rxg1.setCOD_EXAMEN_DETALLE(Integer.parseInt(txtCOD_EXAMEN_DETALLE.getText()));
                rxg1.setDESCRIPCION_RESULTADO(EP_Descripcion.getText());
                rxg1.setCONCLUSION_DIAGNOSTICA(EP_CONCLUSION.getText());                   
                rxg1.setNOM_USU(lblUsu.getText());
                rxg1.setCANTIDAD(Integer.parseInt(txtPersonalRealizaRes1.getText()));
                rxg1.setMEDIDA(cbxMEDIDAS.getSelectedItem().toString());
                rxg1.setINCIDENCIA(cbxINCIDENCIA.getSelectedItem().toString());

                rxg1.RX_EC_RESULTADO_DETALLE_GUARDAR();

                txtCOD_DET_RES.setText(rxg1.RX_EC_ID());
                if(tbCIE10.getRowCount()!=0){
                    guardarDetalleDiagnostico();
                    Clear_Tb_GuardarDetalle_RESULTADO();
                    EP_Descripcion.setText("");
                    EP_CONCLUSION.setText("");
 //                   Clear_Tb_GuardarDetalle();
                    txtCOD_DET_RES.setText(rxg1.RX_EC_ID());
                    System.out.println("COD DET ANTES    "+jLabel1.getText());
                    Map parametros=new HashMap();
                    parametros.put("COD_DET",jLabel1.getText());
                    JasperPrint informe=JasperFillManager.fillReport(getClass().
                    getResourceAsStream("/Reportes/RX/RX_RESULTADO.jasper"), parametros,c.conectar());
                    JasperViewer ventana= new JasperViewer(informe,false);
                    ventana.setTitle("RESULTADO");
                    ventana.setVisible(true);
                    System.out.println("COD DET DESPUES    "+jLabel1.getText());
                    jPanel1.setVisible(false);
                    txtPersonalRealizaRes1.setText("");
                    mostrar_VER_DETALLE_RESULTADO();
                    if(tb_examen_det.getRowCount()==0){
                        btnRegresarRes.doClick();
                    }
                }else{
                    System.out.println("error al guardar detalle diagnostico ");
                } 
            }    
        } catch (Exception e) {
        }        
           
    }

    public void GUARDAR_RESULTADO_DETALLE_RX_CONC(){  
        
//        int id = Integer.parseInt(txtCAB_RESULTADO.getText());
                
               RX_EC_RESULTADO_DETALLE rxg1 = new RX_EC_RESULTADO_DETALLE(); 
               rxg1.setCOD_CAB_RESULTADO(Integer.parseInt(txtCAB_RESULTADO.getText()));
               rxg1.setCOD_EXAMEN_DETALLE(Integer.parseInt(txtCOD_EXAMEN_DETALLE.getText()));
               rxg1.setDESCRIPCION_RESULTADO(EP_Descripcion.getText());
               rxg1.setCONCLUSION_DIAGNOSTICA(EP_CONCLUSION.getText());                   
               rxg1.setNOM_USU(lblUsu.getText());
               rxg1.setCANTIDAD(Integer.parseInt(txtPersonalRealizaRes1.getText()));
               rxg1.setMEDIDA(cbxMEDIDAS.getSelectedItem().toString());
               rxg1.setINCIDENCIA(cbxINCIDENCIA.getSelectedItem().toString());
               rxg1.RX_EC_RESULTADO_DETALLE_GUARDAR();
               
               txtCOD_DET_RES.setText(rxg1.RX_EC_ID());
               if(tbCIE10.getRowCount()!=0){
                   guardarDetalleDiagnostico();
                   Clear_Tb_GuardarDetalle_RESULTADO();
                   EP_Descripcion.setText("");
                   EP_CONCLUSION.setText("");
//                   Clear_Tb_GuardarDetalle();
               }else{
                   System.out.println("error al guardar detalle diagnostico ");
               }  
           
    }
    
    public void RX_EC_ESTADO_RESULTADO_MODIFICAR(){
        
        RX_EC_RESULTADO_DETALLE ER=new RX_EC_RESULTADO_DETALLE();
        ER.RX_EC_Estado_RESULTADO_Caja(Integer.parseInt(txtID_COD_DOC.getText()));    
        
    }
    
    public void mostrar_FP_RESULTADO(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_FP_EX ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblFP.setText(r.getString(1));
                lblFUA.setText("FUA"+r.getString(2));
                
                  String a = "";
                  String b = "";
                  a=String.valueOf(lblFP.getText().charAt(0));
                  b=String.valueOf(lblFP.getText().charAt(1));
                  a=(String.valueOf(a)+String.valueOf(b));
                  System.out.println("aa"+a);

                  if(a.equals("SI")){
                      btnGuardarCabeceraRes1.setEnabled(true);
                      
                  }else{
                      btnGuardarCabeceraRes1.setEnabled(false);
                      
                  }
                  
                  if(r.getString(2).equals("             ")){
                      panelFormaPago1.setVisible(true);
                  }else{
                      panelFormaPago1.setVisible(false);
                  }
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }
    
    public void mostrar_Personal_Solicita(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_RESULTADO_PERSONAL_SOLICITA ?";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                txtPersonalSolicita.setText(r.getString(1));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga personal sol.: " + e.getMessage());
        }
    }
    
//    public void mostrar_Insumo_Cantidad(String cod){
//        String consulta="";
//        try {
//            consulta="EXEC RX_EC_EXAMEN_ALMACEN_CARGAR_ALMACEN ?";
//            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);
//            cmd.setString(1, cod);
//            ResultSet r= cmd.executeQuery();
//            int c=1, usado=0;
//            while(r.next()){
//                
//                txtInsumo.setText(r.getString(1));
//                usado = (r.getInt(2));
//                
//                BigDecimal bd2 = new BigDecimal(usado);
//         
//       
//                bd2 = bd2.setScale(0, BigDecimal.ROUND_HALF_UP);
//
//                txtPlacasUsadas.setText(bd2.toString());
//               
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Error carga PLACAS USADAS, INSUMO: " + e.getMessage());
//        }
//    }
//    
    public void ENABLED_CAMPOS(){
        txtPersonalRegistraResultado.setEnabled(false);
        btnPersonalResultado.setEnabled(false);
        txtPersonalRealizaRes.setEnabled(false);
        btnPersonalResRealiza.setEnabled(false);
//        btnGuardarDetalleRes.setEnabled(true);
        btnGuardarCabeceraRes.setEnabled(false);
        btnRegresarRes.setEnabled(false);  
    }
        
    public void buscarDiagnostico(){
        String consulta="";
        try {
            tb_CIE10.setModel(new DefaultTableModel());
            String titulos[]={"ID CIE10","Cod. Enf.","Descripción"};
            m8=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m8);
            String fila[]=new String[3];

            RX_EC_EXAMEN obj=new RX_EC_EXAMEN();
                    consulta="exec RX_EC_CIE10_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txt_CIE10.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){               
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                
                m8.addRow(fila);
                c++;
            }
            tb_CIE10.setModel(m8);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m8);
            tb_CIE10.setRowSorter(elQueOrdena);
            this.tb_CIE10.setModel(m8);
            
            formatoCIE10();

            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void cargarDiagnostico(){
       
    try{
        DefaultTableModel tabla= new DefaultTableModel();

        tabla.addColumn("ID CIE10");
        tabla.addColumn("Cod Enf.");
        tabla.addColumn("Descripción");


        cst=con.prepareCall("{call RX_EC_CIE10_LISTAR}");
        r=cst.executeQuery();
        while (r.next()){
        Object dato[]=new  Object[3];
        for (int i=0; i<3; i++){
            dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tb_CIE10.setModel(tabla);
       formatoCIE10();
       
       }catch (Exception e){
       }
    }
    
    public void formatoCIE10(){
       tb_CIE10.getColumnModel().getColumn(0).setPreferredWidth(80);
       tb_CIE10.getColumnModel().getColumn(1).setPreferredWidth(80);
       tb_CIE10.getColumnModel().getColumn(2).setPreferredWidth(600);
       //OCULTAR
       tb_CIE10.getColumnModel().getColumn(0).setMinWidth(0);
       tb_CIE10.getColumnModel().getColumn(0).setMaxWidth(0);    
    }
    
    public void formatoCIE10_O(){
       tbCIE10.getColumnModel().getColumn(0).setPreferredWidth(80);
       tbCIE10.getColumnModel().getColumn(1).setPreferredWidth(80);
       tbCIE10.getColumnModel().getColumn(2).setPreferredWidth(600);
       //OCULTAR
       tbCIE10.getColumnModel().getColumn(0).setMinWidth(0);
       tbCIE10.getColumnModel().getColumn(0).setMaxWidth(0);    
    }
    
    public void mostrarDiagnostico(){
        
        try {
            String ID_CIE10,COD_ENF,DESCRIPCION;
            int filaselec=tb_CIE10.getSelectedRow();
            
            m9=(DefaultTableModel) tb_CIE10.getModel();
            ID_CIE10=tb_CIE10.getValueAt(filaselec, 0).toString();
            COD_ENF=tb_CIE10.getValueAt(filaselec, 1).toString();
            DESCRIPCION=tb_CIE10.getValueAt(filaselec, 2).toString();
                    
          if(tbCIE10.getRowCount()==0){
              m9=(DefaultTableModel) tbCIE10.getModel();
           String filaelemento[]={ID_CIE10,COD_ENF,DESCRIPCION};
              m9.addRow(filaelemento);
               formatoCIE10_O();
          }
          
          else{
           if(repiteDiagnostico()==true){
               JOptionPane.showMessageDialog(rootPane,"El diagnostico ya ha sido ingresado.");   
          }
           else{
                m9=(DefaultTableModel) tbCIE10.getModel();
           String filaelemento[]={ID_CIE10,COD_ENF,DESCRIPCION};
               m9.addRow(filaelemento);
               formatoCIE10_O();
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error cargar: " + e);
        }
    }
    
        public boolean repiteDiagnostico(){
         int filaselec=tb_CIE10.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tbCIE10.getRowCount(); i++){    
               if(tbCIE10.getValueAt(i, 0).toString().equalsIgnoreCase(tb_CIE10.getValueAt(filaselec, 0).toString())){
                    c=true;
			}}
               return c;
     }
    
    public void guardarDetalleDiagnostico(){
        
        int id = Integer.parseInt(txtCOD_DET_RES.getText());
//        lblId_DX.setText(D.RX_EC_ID_DX());
       
         for (int i = 0; i < tbCIE10.getRowCount(); i++){      
              RX_EC_RESULTADO_DETALLE_DIAGNOSTICO D = new RX_EC_RESULTADO_DETALLE_DIAGNOSTICO();
               D.setCOD_DETALLE_RESULTADO(id);             
               D.setID_CIE10(Integer.parseInt(tbCIE10.getValueAt(i, 0).toString()));
               D.setNOM_USU(lblUsu.getText());
               D.RX_EC_RESULTADO_DETALLE_DIAGNOSTICO_GUARDAR();
                
    }
    }
    
    private void Clear_Tb_GuardarDetalle_RESULTADO(){
        DefaultTableModel modelo1 = (DefaultTableModel)tbCIE10.getModel(); 
        int b=tbCIE10.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
        }
    }
    
    public void VISIBLE_datos(){
        
        txtID_EXAMEN_CAB.setVisible(false);
        txtId_Documento_G.setVisible(false);
        lblCod_Cab_Resultado.setVisible(false);
        lblIDArea.setVisible(false);
        lblCod_Personal_Sol.setVisible(false);
        lblCod_Per_Registra.setVisible(false);
        lblCod_Per_realiza.setVisible(false);
        txtCAB_RESULTADO.setVisible(false);
        txtCOD_DET_RES.setVisible(false);
        txtID_COD_DOC.setVisible(false);
        txtCOD_EXAMEN_DETALLE.setVisible(false);
        txtNumExamen.setVisible(false);
        txtAMB.setVisible(false);
    }
        
    public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
    }
    
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

    public static void addEscapeListenerWindowDialog( final JDialog windowDialog) {
       ActionListener escAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        windowDialog.dispose();
        }
        };
        windowDialog.getRootPane().registerKeyboardAction(escAction,
        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_IN_FOCUSED_WINDOW);
   }

    public void cerrar(){
        int eleccion = JOptionPane.showConfirmDialog(rootPane,"¿Desea realmente salir del formulario?","Mensaje de Confirmación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
        if (eleccion == JOptionPane.YES_OPTION)
        {
            dispose();   
//            PrincipalMDI MDI= new PrincipalMDI();
//            MDI.setVisible(true);  
        }else{
        }
    }
    
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
            java.util.logging.Logger.getLogger(RX_EC_EXAMEN_CAB_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RX_EC_EXAMEN_CAB_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RX_EC_EXAMEN_CAB_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RX_EC_EXAMEN_CAB_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RX_EC_EXAMEN_CAB_RESULTADO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_QUITAR_CIE10;
    private javax.swing.JDialog CIE10;
    private javax.swing.JEditorPane EP_CONCLUSION;
    private javax.swing.JEditorPane EP_Descripcion;
    private javax.swing.JMenuItem Frontal;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog PERSONAL_ROL;
    private javax.swing.JDialog PERSONAL_ROL_TODO;
    public static javax.swing.JButton btnBuscarCIE10;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarCPT2;
    public static javax.swing.JButton btnGuardarCabeceraRes;
    public static javax.swing.JButton btnGuardarCabeceraRes1;
    public static javax.swing.JButton btnPersonalResRealiza;
    public static javax.swing.JButton btnPersonalResultado;
    private javax.swing.JButton btnRegresarRes;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxINCIDENCIA;
    private javax.swing.JComboBox cbxMEDIDAS;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblCod_Cab_Resultado;
    private javax.swing.JLabel lblCod_Per_Registra;
    private javax.swing.JLabel lblCod_Per_realiza;
    private javax.swing.JLabel lblCod_Personal_Sol;
    private javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFUA;
    private javax.swing.JLabel lblFechaReg;
    private javax.swing.JLabel lblHoraReg;
    public static javax.swing.JLabel lblHospi;
    public static javax.swing.JLabel lblHospiServ;
    public static javax.swing.JLabel lblIDArea;
    private javax.swing.JLabel lblId_Preventa;
    public static javax.swing.JLabel lblNomA;
    private javax.swing.JLabel lblNumExamen;
    private javax.swing.JLabel lblPerB;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    public static javax.swing.JPanel panelFormaPago1;
    private javax.swing.JTable tbCIE10;
    private javax.swing.JTable tb_CIE10;
    private javax.swing.JTable tb_Personal_rol;
    private javax.swing.JTable tb_Personal_rol_todo;
    public static javax.swing.JTable tb_examen_det;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtAM;
    public static javax.swing.JLabel txtAM1;
    private javax.swing.JTextField txtAMB;
    public static javax.swing.JTextField txtBuscarPersonal;
    public static javax.swing.JTextField txtBuscarPersonal_TODO;
    public static javax.swing.JTextField txtCAB_RESULTADO;
    public static javax.swing.JTextField txtCOD_DET_RES;
    private javax.swing.JTextField txtCOD_EXAMEN_DETALLE;
    public static javax.swing.JTextField txtCama;
    public static javax.swing.JTextField txtDNI;
    public static javax.swing.JLabel txtEdad;
    public static javax.swing.JLabel txtFechaNac;
    public static javax.swing.JLabel txtGenero;
    public static javax.swing.JTextField txtHC;
    public static javax.swing.JLabel txtHC2;
    public static javax.swing.JLabel txtHC3;
    public static javax.swing.JTextField txtHabitacion;
    private javax.swing.JTextField txtID_COD_DOC;
    public static javax.swing.JTextField txtID_EXAMEN_CAB;
    private javax.swing.JTextField txtId_Documento_G;
    public static javax.swing.JLabel txtNombreP;
    private javax.swing.JTextField txtNumExamen;
    public static javax.swing.JTextField txtPersonalRealizaRes;
    public static javax.swing.JTextField txtPersonalRealizaRes1;
    public static javax.swing.JTextField txtPersonalRealizaRes2;
    public static javax.swing.JTextField txtPersonalRegistraResultado;
    private javax.swing.JTextField txtPersonalSolicita;
    public static javax.swing.JTextField txt_CIE10;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
          
            
            lblHoraReg.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
            }
        }
    }
}
