/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.PERSONAL;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import modelos.PERSONAL.CLS_FORMATO;
import modelos.PERSONAL.CLS_PERSONAL_ROL;
import modelos.PERSONAL.CLS_PERSONAL_ROL_ACTIVIDADES;
import modelos.Usuario;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class PERSONAL_ROL extends javax.swing.JFrame implements Runnable{
Conexion conectar=new Conexion();
Connection con;
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread h1;
ResultSet r;
CallableStatement cst;
DefaultTableModel m, m1, msb, msb1, m2, msb2, m3, m4, m5, 
                    modelo1, modelo2, msb3, m6, m7, msb4, m8, m9;

static CLS_PERSONAL_ROL PR = new CLS_PERSONAL_ROL();

    /**
     * Creates new form PERSONAL_ROL
     */
    public PERSONAL_ROL() {
        initComponents();
        con=conectar.conectar();
//        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MEDICOS_UO.setLocationRelativeTo(null);
        MEDICOS_UO.getContentPane().setBackground(Color.white);
        ROL_ACTIVIDAD.setLocationRelativeTo(null);
        ROL_ACTIVIDAD.getContentPane().setBackground(Color.white);
        ERROR_BUSCAR_MED.setLocationRelativeTo(null);
        ERROR_BUSCAR_MED.getContentPane().setBackground(new Color(122,77,135));
//        
//        this.cb_HORA_FIN.setModel(HORA());
//        this.cb_HORA_INICIO.setModel(HORA());
        
        jTabbedPane1.setSelectedIndex(1);
        
        inicializar_tabla_Medicos_UO();
        inicializar_tabla_Turnos_UO();
        inicializar_tabla_Personal_ROL();
        inicializar_tabla_Actividades();
        inicializar_tabla_ROL_ACTIVIDADES();
        
//        seleccion();
        Mostrar_PERSONAL_ROL();
        
        cargareliminar_Per.setVisible(false);
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        
        
        P_ROL.setVisible(false);
        P_FECHAS.setVisible(false);
       
//      btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        BTN_AGREGAR_ACTIVIDADES_FRM.setVisible(false);
//        P_ACTIVIDAD.setVisible(false);
//        TB_TURNOS_UO.setDefaultRenderer(Object.class,new CLS_FORMATO());
//        btn_GUARDAR_ROL.setVisible(false);
          
//        cerrar_dialogo();
        LBL_TOTAL_HORA.setText("00:00:00");
        btnguardar_ACTIVIDAD_ROL.setEnabled(false);
        TXT_MOSTRAR_HORA_GUARDAR_UO.setVisible(false);
        BTN_QUITAR.setEnabled(false);
    
        //FRM
        TXT_CODIGO_ROL_G.setVisible(false);
        TXT_NOMBRE_DIA.setVisible(false);
        TXT_DIA.setVisible(false);
        TXT_MES.setVisible(false);
        lbl_id_per_uni_org.setVisible(false);
        txtAR_ID.setVisible(false);
        txtNOMBRE.setVisible(false);
        LBL_UO_HORAS_ELIMINAR.setVisible(false);
        jPanel8.setVisible(false);
        txtCOD_ROL.setVisible(false);
        LBL_UNIDAD_ORGANICA_2.setVisible(false);
        txtUO_3.setVisible(false);
        lblCOD_UO_1.setVisible(false);
        LBL_PASAR_DIA.setVisible(false);
        lblGM.setVisible(false);
        BTN_AGREGAR_TURNOS_UO.setVisible(false);
        
        //DIALOGO
        txt_COD_UNI_ORG_ACTIVIDADES.setVisible(false);
        txt_COD_UNI_ORGANICA_JERAR.setVisible(false);
        LBL_HORA_FIN_1.setVisible(false);
        LBL_HORA_TOTAL_QUITAR.setVisible(false);
        TXT_AR_ID_ACTI.setVisible(false);
        LBL_CANTIDAD_HORAS_TOTAL.setVisible(false);
        LBL_HORAS_LIBRES_SUMA.setVisible(false);
        TXT_HORA_QUITAR_CBX.setVisible(false);
        TXT_HORA_QUITAR_CBX_FIN.setVisible(false);
        
        h1 = new Thread(this);
        h1.start();
    
        //ESCAPE EN DIALOGO
        addEscapeListenerWindowDialog(MEDICOS_UO);
        addEscapeListenerWindowDialog(ROL_ACTIVIDAD);
        
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

        MEDICOS_UO = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtBuscarMedico_UO = new javax.swing.JTextField();
        T5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Personal_UO = new javax.swing.JTable();
        ERROR_BUSCAR_MED = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jPanel63 = new javax.swing.JPanel();
        btnGuardarDetalle1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ROL_ACTIVIDAD = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btnguardar_ACTIVIDAD_ROL = new javax.swing.JButton();
        txt_COD_UNI_ORG_ACTIVIDADES = new javax.swing.JTextField();
        TXT_AR_ID_ACTI = new javax.swing.JTextField();
        txt_COD_UNI_ORGANICA_JERAR = new javax.swing.JTextField();
        LBL_CANTIDAD_HORAS_TOTAL = new javax.swing.JLabel();
        LBL_HORA_TOTAL_QUITAR = new javax.swing.JLabel();
        LBL_HORAS_LIBRES_SUMA = new javax.swing.JLabel();
        LBL_HORA_FIN_1 = new javax.swing.JTextField();
        TXT_HORA_QUITAR_CBX = new javax.swing.JTextField();
        TXT_HORA_QUITAR_CBX_FIN = new javax.swing.JTextField();
        TXT_HORA_INICIO = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        TXT_HORA_FIN = new javax.swing.JLabel();
        TXT_HORA_INICIO1 = new javax.swing.JLabel();
        LBL_AREA_ACT = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        P_ROL1 = new javax.swing.JPanel();
        btn_AGREGAR_ACTIVIDADES = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TB_ROL_ACTIVIDAD = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            BTN_QUITAR_ACTIVIDAD = new javax.swing.JButton();
            jLabel14 = new javax.swing.JLabel();
            jPanel12 = new javax.swing.JPanel();
            txt_ACTIVIDAD = new javax.swing.JTextField();
            btn_BUSCAR_ACTIVIDAD = new javax.swing.JButton();
            cb_HORA_FIN = new javax.swing.JComboBox();
            jLabel16 = new javax.swing.JLabel();
            cb_HORA_INICIO = new javax.swing.JComboBox();
            jLabel17 = new javax.swing.JLabel();
            jLabel18 = new javax.swing.JLabel();
            LBL_TOTAL_HORA = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            txtHoras_Libres = new javax.swing.JLabel();
            jScrollPane7 = new javax.swing.JScrollPane();
            TB_ACTIVIDADES_LISTA = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel13 = new javax.swing.JPanel();
                BTN_AGREGAR_ACTIVIDADES_FRM = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                btneditar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                btnLista = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                jPanel23 = new javax.swing.JPanel();
                txtBuscarMedico_ROL = new javax.swing.JTextField();
                cb_Rango_fecha = new javax.swing.JCheckBox();
                btnBuscarPersonal_rol1 = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                lblListado_Edicion = new javax.swing.JLabel();
                lbl_id_per_uni_org = new javax.swing.JLabel();
                txtAR_ID = new javax.swing.JTextField();
                P_FECHAS = new javax.swing.JPanel();
                jLabel12 = new javax.swing.JLabel();
                D_FECHA_INICIO = new com.toedter.calendar.JDateChooser();
                jLabel13 = new javax.swing.JLabel();
                D_FECHA_FIN = new com.toedter.calendar.JDateChooser();
                btnBuscarPersonal_rol = new javax.swing.JButton();
                txtNOMBRE = new javax.swing.JTextField();
                txtCOD_ROL = new javax.swing.JTextField();
                txtUO_3 = new javax.swing.JTextField();
                jPanel8 = new javax.swing.JPanel();
                TXT_COD_ROL_GUARDAR = new javax.swing.JTextField();
                lblGM = new javax.swing.JLabel();
                lblCOD_UO_1 = new javax.swing.JTextField();
                LBL_UNIDAD_ORGANICA_2 = new javax.swing.JLabel();
                LBL_PASAR_DIA = new javax.swing.JLabel();
                TXT_CODIGO_ROL_G = new javax.swing.JTextField();
                LBL_UO_HORAS_ELIMINAR = new javax.swing.JLabel();
                TXT_NOMBRE_DIA = new javax.swing.JTextField();
                TXT_MES = new javax.swing.JTextField();
                TXT_DIA = new javax.swing.JTextField();
                jButton1 = new javax.swing.JButton();
                cargareliminar_Per = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel4 = new javax.swing.JPanel();
                jPanel9 = new javax.swing.JPanel();
                txtMedico_UO = new javax.swing.JTextField();
                btnBuscarP = new javax.swing.JButton();
                jLabel2 = new javax.swing.JLabel();
                lbl_turnos_disponibles = new javax.swing.JLabel();
                jScrollPane3 = new javax.swing.JScrollPane();
                TB_TURNOS_UO = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    P_ROL = new javax.swing.JPanel();
                    jLabel5 = new javax.swing.JLabel();
                    D_FECHA = new com.toedter.calendar.JDateChooser();
                    jLabel8 = new javax.swing.JLabel();
                    txtLimite_Consultas_Per = new javax.swing.JTextField();
                    jLabel9 = new javax.swing.JLabel();
                    txtTotal_Horas = new javax.swing.JTextField();
                    jLabel10 = new javax.swing.JLabel();
                    txtTotal_Pago = new javax.swing.JTextField();
                    btnAgregar_ROL = new javax.swing.JButton();
                    jScrollPane4 = new javax.swing.JScrollPane();
                    TB_TURNOS_PERSONAL_ROL = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        BTN_QUITAR = new javax.swing.JButton();
                        lbl_nombre_S = new javax.swing.JLabel();
                        LBL_SERVICIO = new javax.swing.JLabel();
                        LBL_GUION = new javax.swing.JLabel();
                        LBL_AREA = new javax.swing.JLabel();
                        TXT_MOSTRAR_HORA_GUARDAR_UO = new javax.swing.JTextField();
                        BTN_AGREGAR_TURNOS_UO = new javax.swing.JButton();
                        jPanel5 = new javax.swing.JPanel();
                        jPanel6 = new javax.swing.JPanel();
                        jScrollPane2 = new javax.swing.JScrollPane();
                        TB_PERSONAL_ROL = new javax.swing.JTable();

                        MEDICOS_UO.setAlwaysOnTop(true);
                        MEDICOS_UO.setMinimumSize(new java.awt.Dimension(700, 380));
                        MEDICOS_UO.setResizable(false);

                        jPanel2.setBackground(new java.awt.Color(122, 77, 135));

                        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
                        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel3.setText("Personal Unidad Organica");

                        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txtBuscarMedico_UO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtBuscarMedico_UO.setForeground(new java.awt.Color(51, 51, 51));
                        txtBuscarMedico_UO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                        txtBuscarMedico_UO.setToolTipText("");
                        txtBuscarMedico_UO.setBorder(null);
                        txtBuscarMedico_UO.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBuscarMedico_UOCaretUpdate(evt);
                            }
                        });
                        txtBuscarMedico_UO.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarMedico_UOActionPerformed(evt);
                            }
                        });
                        txtBuscarMedico_UO.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_UOKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_UOKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_UOKeyTyped(evt);
                            }
                        });

                        T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        T5.setToolTipText("");
                        T5.setContentAreaFilled(false);
                        T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        T5.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                T5ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                        jPanel11.setLayout(jPanel11Layout);
                        jPanel11Layout.setHorizontalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtBuscarMedico_UO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );
                        jPanel11Layout.setVerticalGroup(
                            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBuscarMedico_UO, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(403, Short.MAX_VALUE))
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                        );

                        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tb_Personal_UO = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex) {
                                return false; //Disallow the editing of any cell
                            }
                        };
                        tb_Personal_UO.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Personal_UO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_Personal_UO.setRowHeight(30);
                        tb_Personal_UO.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        tb_Personal_UO.getTableHeader().setReorderingAllowed(false);
                        tb_Personal_UO.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Personal_UOKeyPressed(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tb_Personal_UO);

                        javax.swing.GroupLayout MEDICOS_UOLayout = new javax.swing.GroupLayout(MEDICOS_UO.getContentPane());
                        MEDICOS_UO.getContentPane().setLayout(MEDICOS_UOLayout);
                        MEDICOS_UOLayout.setHorizontalGroup(
                            MEDICOS_UOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                        );
                        MEDICOS_UOLayout.setVerticalGroup(
                            MEDICOS_UOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MEDICOS_UOLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        );

                        ERROR_BUSCAR_MED.setAlwaysOnTop(true);
                        ERROR_BUSCAR_MED.setMinimumSize(new java.awt.Dimension(365, 175));
                        ERROR_BUSCAR_MED.setResizable(false);

                        jPanel7.setBackground(new java.awt.Color(122, 77, 135));
                        jPanel7.setPreferredSize(new java.awt.Dimension(360, 160));

                        jPanel63.setBackground(new java.awt.Color(102, 102, 102));

                        btnGuardarDetalle1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnGuardarDetalle1.setForeground(new java.awt.Color(240, 240, 240));
                        btnGuardarDetalle1.setText("OK");
                        btnGuardarDetalle1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                        btnGuardarDetalle1.setContentAreaFilled(false);
                        btnGuardarDetalle1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnGuardarDetalle1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                        btnGuardarDetalle1.setIconTextGap(30);
                        btnGuardarDetalle1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGuardarDetalle1ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
                        jPanel63.setLayout(jPanel63Layout);
                        jPanel63Layout.setHorizontalGroup(
                            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnGuardarDetalle1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );
                        jPanel63Layout.setVerticalGroup(
                            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel63Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnGuardarDetalle1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
                        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel6.setText("Ingrese todos los campos");

                        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Error-100.png"))); // NOI18N
                        jLabel7.setText("jLabel7");

                        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel11.setText("Error");

                        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                        jPanel7.setLayout(jPanel7Layout);
                        jPanel7Layout.setHorizontalGroup(
                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 118, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        jPanel7Layout.setVerticalGroup(
                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10))))
                        );

                        javax.swing.GroupLayout ERROR_BUSCAR_MEDLayout = new javax.swing.GroupLayout(ERROR_BUSCAR_MED.getContentPane());
                        ERROR_BUSCAR_MED.getContentPane().setLayout(ERROR_BUSCAR_MEDLayout);
                        ERROR_BUSCAR_MEDLayout.setHorizontalGroup(
                            ERROR_BUSCAR_MEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        );
                        ERROR_BUSCAR_MEDLayout.setVerticalGroup(
                            ERROR_BUSCAR_MEDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ERROR_BUSCAR_MEDLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        ROL_ACTIVIDAD.setAlwaysOnTop(true);
                        ROL_ACTIVIDAD.setMinimumSize(new java.awt.Dimension(1000, 668));

                        jPanel10.setBackground(new java.awt.Color(122, 77, 135));

                        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel15.setText("<html>Personal Rol<span style=\"font-size:'14px'\"><br>Actividades</br></span></html>");

                        btnguardar_ACTIVIDAD_ROL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        btnguardar_ACTIVIDAD_ROL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                        btnguardar_ACTIVIDAD_ROL.setMnemonic('G');
                        btnguardar_ACTIVIDAD_ROL.setToolTipText("Guardar (Alt-G)");
                        btnguardar_ACTIVIDAD_ROL.setContentAreaFilled(false);
                        btnguardar_ACTIVIDAD_ROL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnguardar_ACTIVIDAD_ROL.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnguardar_ACTIVIDAD_ROLActionPerformed(evt);
                            }
                        });

                        txt_COD_UNI_ORG_ACTIVIDADES.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txt_COD_UNI_ORG_ACTIVIDADESActionPerformed(evt);
                            }
                        });

                        TXT_AR_ID_ACTI.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TXT_AR_ID_ACTIActionPerformed(evt);
                            }
                        });

                        txt_COD_UNI_ORGANICA_JERAR.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txt_COD_UNI_ORGANICA_JERARActionPerformed(evt);
                            }
                        });

                        LBL_CANTIDAD_HORAS_TOTAL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                        LBL_CANTIDAD_HORAS_TOTAL.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_CANTIDAD_HORAS_TOTAL.setText("jLabel21");

                        LBL_HORA_TOTAL_QUITAR.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_HORA_TOTAL_QUITAR.setText("jLabel23");

                        LBL_HORAS_LIBRES_SUMA.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_HORAS_LIBRES_SUMA.setText("jLabel21");

                        LBL_HORA_FIN_1.setText("jTextField1");
                        LBL_HORA_FIN_1.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                LBL_HORA_FIN_1CaretUpdate(evt);
                            }
                        });

                        TXT_HORA_QUITAR_CBX.setText("jTextField1");

                        TXT_HORA_QUITAR_CBX_FIN.setText("jTextField1");

                        TXT_HORA_INICIO.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
                        TXT_HORA_INICIO.setForeground(new java.awt.Color(255, 255, 255));
                        TXT_HORA_INICIO.setText("jLabel19");

                        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
                        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel19.setText("-");

                        TXT_HORA_FIN.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
                        TXT_HORA_FIN.setForeground(new java.awt.Color(255, 255, 255));
                        TXT_HORA_FIN.setText("jLabel19");

                        TXT_HORA_INICIO1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
                        TXT_HORA_INICIO1.setForeground(new java.awt.Color(255, 255, 255));
                        TXT_HORA_INICIO1.setText("Area de :");

                        LBL_AREA_ACT.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
                        LBL_AREA_ACT.setForeground(new java.awt.Color(255, 255, 255));

                        jLabel4.setForeground(new java.awt.Color(122, 77, 135));
                        jLabel4.setText("jLabel4");

                        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                        jPanel10.setLayout(jPanel10Layout);
                        jPanel10Layout.setHorizontalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LBL_HORA_FIN_1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_COD_UNI_ORG_ACTIVIDADES, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_COD_UNI_ORGANICA_JERAR, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TXT_AR_ID_ACTI, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(jLabel4)
                                        .addGap(34, 34, 34)
                                        .addComponent(LBL_CANTIDAD_HORAS_TOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXT_HORA_QUITAR_CBX, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXT_HORA_QUITAR_CBX_FIN, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(LBL_HORA_TOTAL_QUITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(94, 94, 94)
                                                .addComponent(LBL_HORAS_LIBRES_SUMA, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(TXT_HORA_INICIO1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(LBL_AREA_ACT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(TXT_HORA_INICIO)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TXT_HORA_FIN)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnguardar_ACTIVIDAD_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        );
                        jPanel10Layout.setVerticalGroup(
                            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txt_COD_UNI_ORG_ACTIVIDADES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXT_AR_ID_ACTI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_COD_UNI_ORGANICA_JERAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(TXT_HORA_QUITAR_CBX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TXT_HORA_QUITAR_CBX_FIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(LBL_CANTIDAD_HORAS_TOTAL)))
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TXT_HORA_INICIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TXT_HORA_FIN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(LBL_HORA_FIN_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnguardar_ACTIVIDAD_ROL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(LBL_HORA_TOTAL_QUITAR)
                                                    .addComponent(LBL_HORAS_LIBRES_SUMA)))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LBL_AREA_ACT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TXT_HORA_INICIO1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        );

                        P_ROL1.setBackground(new java.awt.Color(255, 255, 255));

                        btn_AGREGAR_ACTIVIDADES.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
                        btn_AGREGAR_ACTIVIDADES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btn_AGREGAR_ACTIVIDADES.setText("AGREGAR ACT.");
                        btn_AGREGAR_ACTIVIDADES.setContentAreaFilled(false);
                        btn_AGREGAR_ACTIVIDADES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btn_AGREGAR_ACTIVIDADES.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btn_AGREGAR_ACTIVIDADES.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                btn_AGREGAR_ACTIVIDADESItemStateChanged(evt);
                            }
                        });
                        btn_AGREGAR_ACTIVIDADES.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_AGREGAR_ACTIVIDADESActionPerformed(evt);
                            }
                        });
                        btn_AGREGAR_ACTIVIDADES.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                btn_AGREGAR_ACTIVIDADESKeyPressed(evt);
                            }
                        });

                        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
                        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        TB_ROL_ACTIVIDAD.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        TB_ROL_ACTIVIDAD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        TB_ROL_ACTIVIDAD.setForeground(new java.awt.Color(51, 51, 51));
                        TB_ROL_ACTIVIDAD.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Tile", "Title", "Title", "Title"
                            }
                        ));
                        TB_ROL_ACTIVIDAD.setGridColor(new java.awt.Color(255, 255, 255));
                        TB_ROL_ACTIVIDAD.setRowHeight(25);
                        TB_ROL_ACTIVIDAD.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        TB_ROL_ACTIVIDAD.getTableHeader().setReorderingAllowed(false);
                        TB_ROL_ACTIVIDAD.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                TB_ROL_ACTIVIDADMouseClicked(evt);
                            }
                        });
                        TB_ROL_ACTIVIDAD.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                TB_ROL_ACTIVIDADKeyPressed(evt);
                            }
                        });
                        jScrollPane5.setViewportView(TB_ROL_ACTIVIDAD);

                        BTN_QUITAR_ACTIVIDAD.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
                        BTN_QUITAR_ACTIVIDAD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Menos-16.png"))); // NOI18N
                        BTN_QUITAR_ACTIVIDAD.setText("QUITAR ACT.");
                        BTN_QUITAR_ACTIVIDAD.setContentAreaFilled(false);
                        BTN_QUITAR_ACTIVIDAD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        BTN_QUITAR_ACTIVIDAD.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BTN_QUITAR_ACTIVIDADActionPerformed(evt);
                            }
                        });

                        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel14.setText("Actividad:");

                        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txt_ACTIVIDAD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txt_ACTIVIDAD.setForeground(new java.awt.Color(51, 51, 51));
                        txt_ACTIVIDAD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txt_ACTIVIDAD.setToolTipText("");
                        txt_ACTIVIDAD.setBorder(null);
                        txt_ACTIVIDAD.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txt_ACTIVIDADCaretUpdate(evt);
                            }
                        });
                        txt_ACTIVIDAD.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txt_ACTIVIDADActionPerformed(evt);
                            }
                        });
                        txt_ACTIVIDAD.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txt_ACTIVIDADKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txt_ACTIVIDADKeyTyped(evt);
                            }
                        });

                        btn_BUSCAR_ACTIVIDAD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        btn_BUSCAR_ACTIVIDAD.setToolTipText("");
                        btn_BUSCAR_ACTIVIDAD.setContentAreaFilled(false);
                        btn_BUSCAR_ACTIVIDAD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btn_BUSCAR_ACTIVIDAD.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn_BUSCAR_ACTIVIDADActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                        jPanel12.setLayout(jPanel12Layout);
                        jPanel12Layout.setHorizontalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_ACTIVIDAD, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_BUSCAR_ACTIVIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );
                        jPanel12Layout.setVerticalGroup(
                            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_BUSCAR_ACTIVIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txt_ACTIVIDAD, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        cb_HORA_FIN.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                cb_HORA_FINMouseClicked(evt);
                            }
                        });
                        cb_HORA_FIN.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cb_HORA_FINItemStateChanged(evt);
                            }
                        });
                        cb_HORA_FIN.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cb_HORA_FINActionPerformed(evt);
                            }
                        });
                        cb_HORA_FIN.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                cb_HORA_FINKeyPressed(evt);
                            }
                        });

                        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel16.setText("Hora Fin:");

                        cb_HORA_INICIO.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                cb_HORA_INICIOKeyPressed(evt);
                            }
                        });

                        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel17.setText("Hora Inicio:");

                        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel18.setText("Total Horas:");

                        LBL_TOTAL_HORA.setFont(new java.awt.Font("Century Gothic", 0, 20)); // NOI18N
                        LBL_TOTAL_HORA.setText("5");

                        jLabel20.setBackground(new java.awt.Color(255, 51, 51));
                        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
                        jLabel20.setText("Cantidad de Horas disponibles :");

                        txtHoras_Libres.setBackground(new java.awt.Color(255, 51, 51));
                        txtHoras_Libres.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
                        txtHoras_Libres.setForeground(new java.awt.Color(255, 51, 51));
                        txtHoras_Libres.setText("jLabel21");

                        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
                        jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        TB_ACTIVIDADES_LISTA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        TB_ACTIVIDADES_LISTA.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                        TB_ACTIVIDADES_LISTA.setForeground(new java.awt.Color(51, 51, 51));
                        TB_ACTIVIDADES_LISTA.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Tile", "Title", "Title", "Title"
                            }
                        ));
                        TB_ACTIVIDADES_LISTA.setGridColor(new java.awt.Color(255, 255, 255));
                        TB_ACTIVIDADES_LISTA.setRowHeight(25);
                        TB_ACTIVIDADES_LISTA.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        TB_ACTIVIDADES_LISTA.getTableHeader().setReorderingAllowed(false);
                        TB_ACTIVIDADES_LISTA.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                TB_ACTIVIDADES_LISTAMouseClicked(evt);
                            }
                        });
                        TB_ACTIVIDADES_LISTA.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                TB_ACTIVIDADES_LISTAKeyPressed(evt);
                            }
                        });
                        jScrollPane7.setViewportView(TB_ACTIVIDADES_LISTA);

                        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                        jPanel13.setLayout(jPanel13Layout);
                        jPanel13Layout.setHorizontalGroup(
                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 51, Short.MAX_VALUE)
                        );
                        jPanel13Layout.setVerticalGroup(
                            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 35, Short.MAX_VALUE)
                        );

                        BTN_AGREGAR_ACTIVIDADES_FRM.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                        BTN_AGREGAR_ACTIVIDADES_FRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-30.png"))); // NOI18N
                        BTN_AGREGAR_ACTIVIDADES_FRM.setText("Agregar Actividades");
                        BTN_AGREGAR_ACTIVIDADES_FRM.setContentAreaFilled(false);
                        BTN_AGREGAR_ACTIVIDADES_FRM.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BTN_AGREGAR_ACTIVIDADES_FRMActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout P_ROL1Layout = new javax.swing.GroupLayout(P_ROL1);
                        P_ROL1.setLayout(P_ROL1Layout);
                        P_ROL1Layout.setHorizontalGroup(
                            P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(P_ROL1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17))
                                .addGap(26, 26, 26)
                                .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(P_ROL1Layout.createSequentialGroup()
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BTN_AGREGAR_ACTIVIDADES_FRM)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtHoras_Libres, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(P_ROL1Layout.createSequentialGroup()
                                        .addComponent(cb_HORA_INICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel16)
                                        .addGap(26, 26, 26)
                                        .addComponent(cb_HORA_FIN, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel18)
                                        .addGap(10, 10, 10)
                                        .addComponent(LBL_TOTAL_HORA, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(btn_AGREGAR_ACTIVIDADES, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BTN_QUITAR_ACTIVIDAD)
                                        .addGap(0, 112, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addComponent(jScrollPane7)
                            .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(P_ROL1Layout.createSequentialGroup()
                                    .addGap(514, 514, 514)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(515, Short.MAX_VALUE)))
                        );
                        P_ROL1Layout.setVerticalGroup(
                            P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_ROL1Layout.createSequentialGroup()
                                .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(P_ROL1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P_ROL1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHoras_Libres, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BTN_AGREGAR_ACTIVIDADES_FRM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cb_HORA_INICIO)
                                        .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel18)
                                            .addComponent(LBL_TOTAL_HORA)
                                            .addComponent(btn_AGREGAR_ACTIVIDADES)
                                            .addComponent(BTN_QUITAR_ACTIVIDAD)))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_HORA_FIN, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(P_ROL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(P_ROL1Layout.createSequentialGroup()
                                    .addGap(257, 257, 257)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(269, Short.MAX_VALUE)))
                        );

                        javax.swing.GroupLayout ROL_ACTIVIDADLayout = new javax.swing.GroupLayout(ROL_ACTIVIDAD.getContentPane());
                        ROL_ACTIVIDAD.getContentPane().setLayout(ROL_ACTIVIDADLayout);
                        ROL_ACTIVIDADLayout.setHorizontalGroup(
                            ROL_ACTIVIDADLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(P_ROL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        ROL_ACTIVIDADLayout.setVerticalGroup(
                            ROL_ACTIVIDADLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ROL_ACTIVIDADLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(P_ROL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                        jPanel1.setBackground(new java.awt.Color(122, 77, 135));
                        jPanel1.setPreferredSize(new java.awt.Dimension(300, 70));

                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel1.setText("Personal Rol");

                        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
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

                        btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btneditar.setForeground(new java.awt.Color(240, 240, 240));
                        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                        btneditar.setText("Editar");
                        btneditar.setContentAreaFilled(false);
                        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btneditar.setIconTextGap(30);
                        btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btneditar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btneditarActionPerformed(evt);
                            }
                        });

                        btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                        btneliminar.setText("Eliminar");
                        btneliminar.setContentAreaFilled(false);
                        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btneliminar.setIconTextGap(30);
                        btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btneliminar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btneliminarActionPerformed(evt);
                            }
                        });

                        btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                        btnLista.setForeground(new java.awt.Color(240, 240, 240));
                        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
                        btnLista.setText("Listado");
                        btnLista.setContentAreaFilled(false);
                        btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        btnLista.setIconTextGap(30);
                        btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        btnLista.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnListaActionPerformed(evt);
                            }
                        });

                        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                        lblusu.setForeground(new java.awt.Color(255, 255, 255));
                        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                        lblusu.setText("Usuario");
                        lblusu.setFocusable(false);
                        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

                        txtBuscarMedico_ROL.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtBuscarMedico_ROL.setForeground(new java.awt.Color(51, 51, 51));
                        txtBuscarMedico_ROL.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                        txtBuscarMedico_ROL.setBorder(null);
                        txtBuscarMedico_ROL.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBuscarMedico_ROLCaretUpdate(evt);
                            }
                        });
                        txtBuscarMedico_ROL.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarMedico_ROLActionPerformed(evt);
                            }
                        });
                        txtBuscarMedico_ROL.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_ROLKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_ROLKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarMedico_ROLKeyTyped(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                        jPanel23.setLayout(jPanel23Layout);
                        jPanel23Layout.setHorizontalGroup(
                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addComponent(txtBuscarMedico_ROL, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        jPanel23Layout.setVerticalGroup(
                            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(txtBuscarMedico_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        cb_Rango_fecha.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        cb_Rango_fecha.setForeground(new java.awt.Color(255, 255, 255));
                        cb_Rango_fecha.setText("Buscar Por Rango De Fechas");
                        cb_Rango_fecha.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cb_Rango_fechaActionPerformed(evt);
                            }
                        });

                        btnBuscarPersonal_rol1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPersonal_rol1.setContentAreaFilled(false);
                        btnBuscarPersonal_rol1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPersonal_rol1.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btnBuscarPersonal_rol1MouseClicked(evt);
                            }
                        });
                        btnBuscarPersonal_rol1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPersonal_rol1ActionPerformed(evt);
                            }
                        });

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
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                                            .addComponent(btnLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cb_Rango_fecha)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscarPersonal_rol1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarPersonal_rol1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addComponent(cb_Rango_fecha)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btneditar)
                                .addGap(18, 18, 18)
                                .addComponent(btneliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnLista)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        jPanel3.setBackground(new java.awt.Color(43, 43, 43));
                        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

                        lblListado_Edicion.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
                        lblListado_Edicion.setForeground(new java.awt.Color(255, 255, 255));
                        lblListado_Edicion.setText("Listado");

                        lbl_id_per_uni_org.setForeground(new java.awt.Color(255, 255, 255));
                        lbl_id_per_uni_org.setText("id_per_uni_org");

                        txtAR_ID.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtAR_IDCaretUpdate(evt);
                            }
                        });
                        txtAR_ID.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtAR_IDActionPerformed(evt);
                            }
                        });

                        P_FECHAS.setBackground(new java.awt.Color(43, 43, 43));

                        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel12.setText("INICIO");

                        D_FECHA_INICIO.setBackground(new java.awt.Color(43, 43, 43));
                        D_FECHA_INICIO.setForeground(new java.awt.Color(255, 255, 255));
                        D_FECHA_INICIO.setDateFormatString("dd-MM-yyyy");
                        D_FECHA_INICIO.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        D_FECHA_INICIO.addAncestorListener(new javax.swing.event.AncestorListener() {
                            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                            }
                            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                D_FECHA_INICIOAncestorAdded(evt);
                            }
                            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                            }
                        });

                        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel13.setText("FIN");

                        D_FECHA_FIN.setBackground(new java.awt.Color(43, 43, 43));
                        D_FECHA_FIN.setDateFormatString("dd-MM-yyyy");
                        D_FECHA_FIN.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        D_FECHA_FIN.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                D_FECHA_FINPropertyChange(evt);
                            }
                        });
                        D_FECHA_FIN.addAncestorListener(new javax.swing.event.AncestorListener() {
                            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                            }
                            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                D_FECHA_FINAncestorAdded(evt);
                            }
                            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                            }
                        });

                        btnBuscarPersonal_rol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                        btnBuscarPersonal_rol.setContentAreaFilled(false);
                        btnBuscarPersonal_rol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarPersonal_rol.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPersonal_rolActionPerformed(evt);
                            }
                        });
                        btnBuscarPersonal_rol.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                btnBuscarPersonal_rolKeyTyped(evt);
                            }
                        });

                        javax.swing.GroupLayout P_FECHASLayout = new javax.swing.GroupLayout(P_FECHAS);
                        P_FECHAS.setLayout(P_FECHASLayout);
                        P_FECHASLayout.setHorizontalGroup(
                            P_FECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_FECHASLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(D_FECHA_INICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(D_FECHA_FIN, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPersonal_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))
                        );
                        P_FECHASLayout.setVerticalGroup(
                            P_FECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_FECHASLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(P_FECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBuscarPersonal_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(P_FECHASLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(D_FECHA_FIN, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                                        .addComponent(D_FECHA_INICIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        );

                        txtNOMBRE.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtNOMBREActionPerformed(evt);
                            }
                        });

                        txtCOD_ROL.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtCOD_ROLActionPerformed(evt);
                            }
                        });

                        txtUO_3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtUO_3ActionPerformed(evt);
                            }
                        });

                        jPanel8.setBackground(new java.awt.Color(43, 43, 43));
                        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(43, 43, 43)));

                        TXT_COD_ROL_GUARDAR.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TXT_COD_ROL_GUARDARActionPerformed(evt);
                            }
                        });

                        lblGM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                        lblGM.setForeground(new java.awt.Color(255, 255, 255));
                        lblGM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblGM.setText("G");

                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                        jPanel8.setLayout(jPanel8Layout);
                        jPanel8Layout.setHorizontalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(TXT_COD_ROL_GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblGM, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        jPanel8Layout.setVerticalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TXT_COD_ROL_GUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblGM))
                        );

                        lblCOD_UO_1.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                lblCOD_UO_1CaretUpdate(evt);
                            }
                        });
                        lblCOD_UO_1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                lblCOD_UO_1ActionPerformed(evt);
                            }
                        });

                        LBL_UNIDAD_ORGANICA_2.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_UNIDAD_ORGANICA_2.setText("jLabel19");

                        LBL_PASAR_DIA.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_PASAR_DIA.setText("jLabel21");

                        TXT_CODIGO_ROL_G.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TXT_CODIGO_ROL_GActionPerformed(evt);
                            }
                        });

                        LBL_UO_HORAS_ELIMINAR.setForeground(new java.awt.Color(255, 255, 255));
                        LBL_UO_HORAS_ELIMINAR.setText("jLabel4");

                        TXT_MES.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                TXT_MESCaretUpdate(evt);
                            }
                        });

                        jButton1.setContentAreaFilled(false);

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(LBL_UO_HORAS_ELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(P_FECHAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(LBL_UNIDAD_ORGANICA_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtUO_3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(LBL_PASAR_DIA)
                                                        .addGap(43, 43, 43)))
                                                .addGap(4, 4, 4)))
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblListado_Edicion)
                                        .addGap(14, 14, 14)
                                        .addComponent(TXT_CODIGO_ROL_G, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(TXT_NOMBRE_DIA, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TXT_DIA, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TXT_MES, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(lbl_id_per_uni_org, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAR_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCOD_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCOD_UO_1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23))))
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap(19, Short.MAX_VALUE)
                                        .addComponent(lblListado_Edicion)
                                        .addGap(21, 21, 21)
                                        .addComponent(P_FECHAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCOD_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCOD_UO_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(LBL_UNIDAD_ORGANICA_2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtUO_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(LBL_PASAR_DIA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(LBL_UO_HORAS_ELIMINAR))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_id_per_uni_org)
                                    .addComponent(txtAR_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_CODIGO_ROL_G, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_NOMBRE_DIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_MES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TXT_DIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(19, 19, 19))
                        );

                        cargareliminar_Per.setBackground(new java.awt.Color(255, 153, 51));

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

                        javax.swing.GroupLayout cargareliminar_PerLayout = new javax.swing.GroupLayout(cargareliminar_Per);
                        cargareliminar_Per.setLayout(cargareliminar_PerLayout);
                        cargareliminar_PerLayout.setHorizontalGroup(
                            cargareliminar_PerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cargareliminar_PerLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(Mensaje)
                                .addGap(46, 46, 46)
                                .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        cargareliminar_PerLayout.setVerticalGroup(
                            cargareliminar_PerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cargareliminar_PerLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(cargareliminar_PerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Mensaje)
                                    .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
                        jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                        txtMedico_UO.setEditable(false);
                        txtMedico_UO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtMedico_UO.setForeground(new java.awt.Color(51, 51, 51));
                        txtMedico_UO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtMedico_UO.setToolTipText("");
                        txtMedico_UO.setBorder(null);
                        txtMedico_UO.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtMedico_UOCaretUpdate(evt);
                            }
                        });
                        txtMedico_UO.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtMedico_UOActionPerformed(evt);
                            }
                        });

                        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        btnBuscarP.setToolTipText("");
                        btnBuscarP.setContentAreaFilled(false);
                        btnBuscarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscarPActionPerformed(evt);
                            }
                        });
                        btnBuscarP.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                btnBuscarPKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                        jPanel9.setLayout(jPanel9Layout);
                        jPanel9Layout.setHorizontalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtMedico_UO, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );
                        jPanel9Layout.setVerticalGroup(
                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
                                    .addComponent(txtMedico_UO))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel2.setText("Médico:");

                        lbl_turnos_disponibles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lbl_turnos_disponibles.setText("Turnos Disponibles");

                        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
                        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        TB_TURNOS_UO.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        TB_TURNOS_UO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        TB_TURNOS_UO.setForeground(new java.awt.Color(51, 51, 51));
                        TB_TURNOS_UO.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null},
                                {null, null, null, null}
                            },
                            new String [] {
                                "Tile", "Title", "Title", "Title"
                            }
                        ));
                        TB_TURNOS_UO.setGridColor(new java.awt.Color(255, 255, 255));
                        TB_TURNOS_UO.setRowHeight(25);
                        TB_TURNOS_UO.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        TB_TURNOS_UO.getTableHeader().setReorderingAllowed(false);
                        TB_TURNOS_UO.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                TB_TURNOS_UOMouseClicked(evt);
                            }
                        });
                        TB_TURNOS_UO.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                TB_TURNOS_UOKeyPressed(evt);
                            }
                        });
                        jScrollPane3.setViewportView(TB_TURNOS_UO);

                        P_ROL.setBackground(new java.awt.Color(255, 255, 255));

                        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel5.setText("Fecha:");

                        D_FECHA.setBackground(new java.awt.Color(255, 255, 255));
                        D_FECHA.setDateFormatString("dd-MM-yyyy");
                        D_FECHA.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        D_FECHA.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                D_FECHAPropertyChange(evt);
                            }
                        });
                        D_FECHA.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                D_FECHAKeyPressed(evt);
                            }
                        });

                        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel8.setText("Límite Consultas Diarias:");

                        txtLimite_Consultas_Per.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtLimite_Consultas_Per.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtLimite_Consultas_Per.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtLimite_Consultas_PerCaretUpdate(evt);
                            }
                        });
                        txtLimite_Consultas_Per.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtLimite_Consultas_PerMouseClicked(evt);
                            }
                        });
                        txtLimite_Consultas_Per.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtLimite_Consultas_PerActionPerformed(evt);
                            }
                        });
                        txtLimite_Consultas_Per.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtLimite_Consultas_PerKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtLimite_Consultas_PerKeyReleased(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtLimite_Consultas_PerKeyTyped(evt);
                            }
                        });

                        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel9.setText("Total Horas:");

                        txtTotal_Horas.setEditable(false);
                        txtTotal_Horas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtTotal_Horas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtTotal_Horas.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtTotal_HorasActionPerformed(evt);
                            }
                        });

                        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        jLabel10.setText("Total Pago:");

                        txtTotal_Pago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        txtTotal_Pago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtTotal_Pago.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtTotal_PagoActionPerformed(evt);
                            }
                        });
                        txtTotal_Pago.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtTotal_PagoKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtTotal_PagoKeyTyped(evt);
                            }
                        });

                        btnAgregar_ROL.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
                        btnAgregar_ROL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btnAgregar_ROL.setText("AGREGAR ROL");
                        btnAgregar_ROL.setContentAreaFilled(false);
                        btnAgregar_ROL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAgregar_ROL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        btnAgregar_ROL.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregar_ROLActionPerformed(evt);
                            }
                        });
                        btnAgregar_ROL.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                btnAgregar_ROLKeyPressed(evt);
                            }
                        });

                        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
                        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        TB_TURNOS_PERSONAL_ROL.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        TB_TURNOS_PERSONAL_ROL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        TB_TURNOS_PERSONAL_ROL.setForeground(new java.awt.Color(51, 51, 51));
                        TB_TURNOS_PERSONAL_ROL.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Tile", "Title", "Title", "Title"
                            }
                        ));
                        TB_TURNOS_PERSONAL_ROL.setGridColor(new java.awt.Color(255, 255, 255));
                        TB_TURNOS_PERSONAL_ROL.setRowHeight(25);
                        TB_TURNOS_PERSONAL_ROL.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        TB_TURNOS_PERSONAL_ROL.getTableHeader().setReorderingAllowed(false);
                        TB_TURNOS_PERSONAL_ROL.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                TB_TURNOS_PERSONAL_ROLMouseClicked(evt);
                            }
                        });
                        TB_TURNOS_PERSONAL_ROL.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                TB_TURNOS_PERSONAL_ROLKeyPressed(evt);
                            }
                        });
                        jScrollPane4.setViewportView(TB_TURNOS_PERSONAL_ROL);

                        BTN_QUITAR.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
                        BTN_QUITAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Menos-16.png"))); // NOI18N
                        BTN_QUITAR.setText("QUITAR ROL");
                        BTN_QUITAR.setContentAreaFilled(false);
                        BTN_QUITAR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        BTN_QUITAR.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BTN_QUITARActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout P_ROLLayout = new javax.swing.GroupLayout(P_ROL);
                        P_ROL.setLayout(P_ROLLayout);
                        P_ROLLayout.setHorizontalGroup(
                            P_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_ROLLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(D_FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLimite_Consultas_Per, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal_Horas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregar_ROL, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(BTN_QUITAR))
                            .addComponent(jScrollPane4)
                        );
                        P_ROLLayout.setVerticalGroup(
                            P_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(P_ROLLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(P_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(D_FECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(P_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLimite_Consultas_Per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTotal_Horas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTotal_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAgregar_ROL)
                                        .addComponent(BTN_QUITAR)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                        );

                        lbl_nombre_S.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lbl_nombre_S.setText("Servicio:");

                        LBL_SERVICIO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        LBL_SERVICIO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                        LBL_GUION.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        LBL_GUION.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        LBL_GUION.setText("-");

                        LBL_AREA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        LBL_AREA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                        BTN_AGREGAR_TURNOS_UO.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                        BTN_AGREGAR_TURNOS_UO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-30.png"))); // NOI18N
                        BTN_AGREGAR_TURNOS_UO.setText("Agregar Turnos");
                        BTN_AGREGAR_TURNOS_UO.setContentAreaFilled(false);
                        BTN_AGREGAR_TURNOS_UO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        BTN_AGREGAR_TURNOS_UO.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                BTN_AGREGAR_TURNOS_UOActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                        jPanel4.setLayout(jPanel4Layout);
                        jPanel4Layout.setHorizontalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(31, 31, 31)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbl_turnos_disponibles)
                                        .addGap(132, 132, 132)
                                        .addComponent(TXT_MOSTRAR_HORA_GUARDAR_UO, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(175, 175, 175)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lbl_nombre_S)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LBL_SERVICIO, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LBL_GUION, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LBL_AREA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(BTN_AGREGAR_TURNOS_UO)))
                                .addContainerGap())
                            .addComponent(P_ROL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        jPanel4Layout.setVerticalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(LBL_GUION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LBL_SERVICIO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_nombre_S, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(LBL_AREA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_turnos_disponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TXT_MOSTRAR_HORA_GUARDAR_UO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BTN_AGREGAR_TURNOS_UO)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(P_ROL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("", jPanel4);

                        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        TB_PERSONAL_ROL = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex) {
                                return false; //Disallow the editing of any cell
                            }
                        };
                        TB_PERSONAL_ROL.setModel(new javax.swing.table.DefaultTableModel(
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
                        TB_PERSONAL_ROL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        TB_PERSONAL_ROL.setRowHeight(35);
                        TB_PERSONAL_ROL.setSelectionBackground(new java.awt.Color(102, 102, 102));
                        TB_PERSONAL_ROL.getTableHeader().setReorderingAllowed(false);
                        TB_PERSONAL_ROL.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                TB_PERSONAL_ROLMouseClicked(evt);
                            }
                        });
                        TB_PERSONAL_ROL.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                TB_PERSONAL_ROLKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                TB_PERSONAL_ROLKeyReleased(evt);
                            }
                        });
                        jScrollPane2.setViewportView(TB_PERSONAL_ROL);

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                        );

                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel5Layout.setHorizontalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        jPanel5Layout.setVerticalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );

                        jTabbedPane1.addTab("", jPanel5);

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cargareliminar_Per, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTabbedPane1)))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cargareliminar_Per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTabbedPane1))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void txtMedico_UOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMedico_UOCaretUpdate

    }//GEN-LAST:event_txtMedico_UOCaretUpdate

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        MEDICOS_UO.setVisible(true);
        txtMedico_UO.setText("");
//        tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);     
        txtBuscarMedico_UO.requestFocus();
        txtBuscarMedico_UO.setText("");
        
        MostrarPersonal_UO();
        Clear_Tb_TURNOS_UO();
        
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void txtBuscarMedico_UOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOCaretUpdate
            BuscarPersonal_UO();

    }//GEN-LAST:event_txtBuscarMedico_UOCaretUpdate

    private void T5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T5ActionPerformed

    private void txtBuscarMedico_ROLCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedico_ROLCaretUpdate
//    jTabbedPane1.setSelectedIndex(1);
    if(cb_Rango_fecha.isSelected()== true && (D_FECHA_INICIO.getDate()==null || D_FECHA_FIN.getDate()==null)){
        JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un rango de fechas");
        txtBuscarMedico_ROL.setText("");
    }
        
    if(cb_Rango_fecha.isSelected()==true){
        BUSCAR_PERSONAL_ROL_FECHA();
    }else if(cb_Rango_fecha.isSelected()==false){
        BUSCAR_PERSONAL_ROL();
    }
        
//        BUSCAR_PERSONAL_ROL_FECHA();
    }//GEN-LAST:event_txtBuscarMedico_ROLCaretUpdate

    private void btnBuscarPersonal_rolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonal_rolActionPerformed
        BUSCAR_PERSONAL_ROL_FECHA();
        txtBuscarMedico_ROL.requestFocus();
        txtBuscarMedico_ROL.setEditable(true);
        
    }//GEN-LAST:event_btnBuscarPersonal_rolActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        lblListado_Edicion.setText("Edición");
        jTabbedPane1.setSelectedIndex(0);
        P_ROL.setVisible(false);
//        TB_TURNOS_UO.setVisible(false);
        jScrollPane3.setVisible(false);     
        lbl_turnos_disponibles.setVisible(false);
        lbl_nombre_S.setVisible(false);
        LBL_GUION.setVisible(false);
        LBL_SERVICIO.setText("");
        LBL_AREA.setText("");
        Clear_TB_TURNOS_PERSONAL_ROL();
        Clear_Tb_TURNOS_UO();
        txtMedico_UO.setText("");
        btnAgregar_ROL.setText("AGREGAR ROL");
        cb_Rango_fecha.setSelected(false);
        P_FECHAS.setVisible(false);
        btnBuscarP.setEnabled(true);
        D_FECHA.setDate(null);
        txtLimite_Consultas_Per.setText("");
        txtTotal_Horas.setText("");
        txtTotal_Pago.setText("");
        txtBuscarMedico_ROL.setEditable(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
//        btnBuscarP.requestFocus();
//        MEDICOS_UO.setVisible(true);
        btnBuscarP.doClick();
        lblGM.setText("G");
        BTN_AGREGAR_TURNOS_UO.setVisible(false);
               
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        TB_TURNOS_PERSONAL_ROL.setEnabled(true);
        TB_TURNOS_PERSONAL_ROL.setBackground(Color.white);
        btneliminar.setEnabled(true);
        
        if(lblGM.getText().equalsIgnoreCase("G")){
            
            lblGM.setText("E");
            TB_TURNOS_PERSONAL_ROL.setEnabled(true);
            TB_TURNOS_PERSONAL_ROL.setBackground(Color.white);
            
        }else{
        
//                if(lblGM.getText().equalsIgnoreCase("E")){
//
//                    lblGM.setText("E");
//                    cargareliminar_Per.setVisible(false);
//                            TB_TURNOS_UO.setEnabled(false);
//                            TB_TURNOS_UO.setBackground(Color.lightGray);
//                            btnBuscarP.setEnabled(false);
//                            D_FECHA.setEnabled(false);
//                            txtLimite_Consultas_Per.setEditable(false);
//                            txtTotal_Horas.setEditable(false);
//                            txtTotal_Pago.setEditable(false);
//                            btnAgregar_ROL.setEnabled(false);
//                }else{
//
//        //            if(lblGM.getText().equalsIgnoreCase("G")){
//        //                lblGM.setText("M");
//        //                btnAgregar_ROL.setText("MODIFICAR ROL");
//        //                btnBuscarP.setEnabled(false);
//        //                
//        ////                TB_TURNOS_UO.setEnabled(true);
//        ////                TB_TURNOS_UO.setBackground(Color.white);
//        ////                D_FECHA.setEnabled(true);
//        ////                txtLimite_Consultas_Per.setEditable(true);
//        ////                txtTotal_Horas.setEditable(true);
//        ////                txtTotal_Pago.setEditable(true);
//        ////                btnAgregar_ROL.setEnabled(true);
//        //            }
//                }
        }
            
        
        
//        TB_TURNOS_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
//        TB_TURNOS_PERSONAL_ROL.requestFocus();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        btneditar.setEnabled(false);
        
        if(lblGM.getText().equalsIgnoreCase("G")){
            
            lblGM.setText("E");
            TB_TURNOS_PERSONAL_ROL.setEnabled(true);
            TB_TURNOS_PERSONAL_ROL.setBackground(Color.white);
            
        }else{
            if(lblGM.getText().equalsIgnoreCase("E")){
                    ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
                    int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );

                    try{       
                    int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                    if( filaselec>=0){

                        if(eliminar == 0 )
                        {
                            PERSONAL_ROL_ELIMINAR_ACTIVIDAD();
                            JOptionPane.showMessageDialog(rootPane, "Datos Eliminados de \n Forma Correcta");
                            System.out.println("datos eliminados"); 
                            
                                //quitar la fila seleccionada de la tabla 
                                DefaultTableModel modelo = (DefaultTableModel)TB_TURNOS_PERSONAL_ROL.getModel();
                                modelo.removeRow(filaselec);

//                            lblGM.setText("G"); 
                        }else{
                            System.out.println("Error al eliminar rol y actividad");
                        }                        

                    }else{
        //              
                          JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
                    }
                    }catch(Exception e){
                            System.out.println("error eliminar rol_actividad: " + e.getMessage());
                            
                    }
            }else{
                JOptionPane.showMessageDialog(this, "Error eliminar");
            }
        }    
            
            
         
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jTabbedPane1.setSelectedIndex(1);
        lblGM.setText("G");
        Mostrar_PERSONAL_ROL();
        Clear_Tb_TURNOS_UO();
        Clear_TB_TURNOS_PERSONAL_ROL();
        txtBuscarMedico_ROL.setEditable(true);
        txtBuscarMedico_ROL.requestFocus();
//        btn_GUARDAR_ROL.setEnabled(false);
        LBL_SERVICIO.setText("");
        LBL_AREA.setText("");
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
    }//GEN-LAST:event_btnListaActionPerformed

    private void txtBuscarMedico_UOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOActionPerformed
        
    }//GEN-LAST:event_txtBuscarMedico_UOActionPerformed

    private void txtBuscarMedico_UOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyReleased
        txtBuscarMedico_UO.setText(txtBuscarMedico_UO.getText().toUpperCase());
        
    }//GEN-LAST:event_txtBuscarMedico_UOKeyReleased

    private void tb_Personal_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_UOKeyPressed
        
        int filaselec=tb_Personal_UO.getSelectedRow();
        char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            if(filaselec<0){
                   JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }else{

                    String apep, apem, nom;
                    apep = tb_Personal_UO.getValueAt(filaselec, 3).toString();
                    apem = tb_Personal_UO.getValueAt(filaselec, 4).toString();
                    nom = tb_Personal_UO.getValueAt(filaselec, 5).toString();

                    txtMedico_UO.setText(String.valueOf(apep + " " + apem + " " + nom));
                    lbl_id_per_uni_org.setText(String.valueOf(tb_Personal_UO.getValueAt(filaselec, 1)));
                    txtAR_ID.setText(String.valueOf(tb_Personal_UO.getValueAt(filaselec, 7)));
                    LBL_SERVICIO.setText(String.valueOf(tb_Personal_UO.getValueAt(filaselec, 9)));
                    LBL_AREA.setText(String.valueOf(tb_Personal_UO.getValueAt(filaselec, 8)));
                    TXT_AR_ID_ACTI.setText(String.valueOf(tb_Personal_UO.getValueAt(filaselec, 7)));
                    
                    
                    MEDICOS_UO.dispose();
                    
                    TB_TURNOS_UO.getSelectionModel().setSelectionInterval(0, 0);
                    TB_TURNOS_UO.requestFocus();
        
                    TB_TURNOS_UO.setEnabled(true);
                    TB_TURNOS_UO.setBackground(Color.white);
                    D_FECHA.setEnabled(true);
                    txtLimite_Consultas_Per.setEditable(true);
                    txtTotal_Pago.setEditable(true);
                    btnAgregar_ROL.setEnabled(true);
                    
                    jScrollPane3.setVisible(true);
                    lbl_nombre_S.setVisible(true);
                    LBL_GUION.setVisible(true);
                    btnBuscarP.setEnabled(false);
                    
                    lbl_turnos_disponibles.setVisible(true);
                    BTN_AGREGAR_TURNOS_UO.setVisible(true);
            }
        }
          
        if(TB_TURNOS_UO.getRowCount()==0){
            P_ROL.setVisible(false);
        }
       
    }//GEN-LAST:event_tb_Personal_UOKeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
//        jTabbedPane1.setSelectedIndex(1);
//        cargareliminar_Per.setVisible(false);
        Mostrar_PERSONAL_ROL();
        if(lblGM.getText().equalsIgnoreCase("E")){
            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
            if( filaselec>=0){
                
                    CLS_PERSONAL_ROL PE=new CLS_PERSONAL_ROL();
                    PE.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                    if(PE.PERSONAL_ROL_ELIMINAR())
                    {
                        cargareliminar_Per.setVisible(false);
//                        
                    }
            }
        }
        if(lblGM.getText().equalsIgnoreCase("G")){
            cargareliminar_Per.setVisible(false);
            jTabbedPane1.setSelectedIndex(1);
           
            
        }
        
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        if(lblGM.getText().equalsIgnoreCase("E")){
            cargareliminar_Per.setVisible(false);
        }

    }//GEN-LAST:event_noeliActionPerformed

    private void txtBuscarMedico_UOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }
    
    }//GEN-LAST:event_txtBuscarMedico_UOKeyTyped

    private void txtBuscarMedico_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMedico_UOKeyPressed

    private void btnGuardarDetalle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDetalle1ActionPerformed
        txtBuscarMedico_UO.setText("");
        ERROR_BUSCAR_MED.dispose();
    }//GEN-LAST:event_btnGuardarDetalle1ActionPerformed

    private void TB_TURNOS_UOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_TURNOS_UOMouseClicked
        if(evt.getClickCount()==1){
            
            P_ROL.setVisible(true);
            formatoPersonal_TURNOS_UO();
            int filasel=TB_TURNOS_UO.getSelectedRow();
            String total = TB_TURNOS_UO.getValueAt(filasel, 10).toString();
           
            String d = total.substring(0, 2);
            
            txtTotal_Horas.setText(d);
            TXT_HORA_INICIO.setText(TB_TURNOS_UO.getValueAt(filasel, 7).toString());
            TXT_HORA_FIN.setText(TB_TURNOS_UO.getValueAt(filasel, 8).toString());
            LBL_PASAR_DIA.setText(TB_TURNOS_UO.getValueAt(filasel, 11).toString());
            
            if(lblGM.getText().equalsIgnoreCase("M")){
                lblCOD_UO_1.setText(TB_TURNOS_UO.getValueAt(filasel, 1).toString());
                LBL_UNIDAD_ORGANICA_2.setText(TB_TURNOS_UO.getValueAt(filasel, 1).toString());
                
                //////segundo I
//                TXT_UO_TB_TURNOS_UO.setText(String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 1)));
                
            }
            
            
        }
     
    }//GEN-LAST:event_TB_TURNOS_UOMouseClicked

    private void TB_TURNOS_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_TURNOS_UOKeyPressed
//        char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
//            P_ROL.setVisible(true);
//            int filaselec=TB_TURNOS_UO.getSelectedRow();
//            String total = TB_TURNOS_UO.getValueAt(filaselec, 10).toString();
//           
//            String d = total.substring(0, 2);
//            
//            txtTotal_Horas.setText(d);
//            TXT_HORA_INICIO.setText(TB_TURNOS_UO.getValueAt(filaselec, 7).toString());
//            TXT_HORA_FIN.setText(TB_TURNOS_UO.getValueAt(filaselec, 8).toString());
//            LBL_PASAR_DIA.setText(TB_TURNOS_UO.getValueAt(filaselec, 11).toString());
//            
//            if(lblGM.getText().equalsIgnoreCase("M")){
//                lblCOD_UO.setText(TB_TURNOS_UO.getValueAt(filaselec, 1).toString());
//                LBL_UNIDAD_ORGANICA.setText(TB_TURNOS_UO.getValueAt(filaselec, 1).toString());
//            }
//            
//            TB_TURNOS_UO.getSelectionModel().setSelectionInterval(filaselec,filaselec);
//
//        }
        
    }//GEN-LAST:event_TB_TURNOS_UOKeyPressed

    private void txtAR_IDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAR_IDCaretUpdate
        mostrar_TURNOS_UO();
        mostrar_ACTIVIDADES();
        mostrar_SERVICIO_AREA(txtAR_ID.getText());
    }//GEN-LAST:event_txtAR_IDCaretUpdate

    private void TB_TURNOS_PERSONAL_ROLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_TURNOS_PERSONAL_ROLMouseClicked
       if(evt.getClickCount()==1){
           int filaselect = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
           if(!String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselect, 10)).equals("null")){
            
                TXT_COD_ROL_GUARDAR.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselect, 10)));
                BTN_QUITAR.setEnabled(false);
           }else{
                TXT_COD_ROL_GUARDAR.setText("");
                BTN_QUITAR.setEnabled(true);
                
           }
           
           if(lblGM.getText().equalsIgnoreCase("M")){
               if(LBL_UNIDAD_ORGANICA_2.getText().length()<10){
//               if(!LBL_UNIDAD_ORGANICA_2.getText().equalsIgnoreCase(lblCOD_UO_1.getText())){
                    cargar_tb_personal_rol_tb_UO();
                    TB_TURNOS_UO.setEnabled(true);
                    TB_TURNOS_UO.setBackground(Color.white);
                    D_FECHA.setEnabled(true);
                    txtLimite_Consultas_Per.setEditable(true);

                    txtTotal_Pago.setEditable(true);
                    ////////////////////////
                    btnAgregar_ROL.setEnabled(true);
                    
               }else{
                    TB_TURNOS_UO.setEnabled(true);
                    TB_TURNOS_UO.setBackground(Color.white);
                    D_FECHA.setEnabled(true);
                    txtLimite_Consultas_Per.setEditable(true);

                    txtTotal_Pago.setEditable(true);
                    //////////////////////////
                    btnAgregar_ROL.setEnabled(false);
               }
               
//               TXT_UO_TB_PERSONAL_ROL.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselect, 0)));
//               TXT_UO_TB_TURNOS_UO.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselect, 0)));
               


                //////////////////////////// prueba modificar
//               if(TXT_UO_TB_PERSONAL_ROL.getText().equalsIgnoreCase(TXT_UO_TB_TURNOS_UO.getText())){
//                    cargar_tb_personal_rol_tb_UO();
//                    TB_TURNOS_UO.setEnabled(true);
//                    TB_TURNOS_UO.setBackground(Color.white);
//                    D_FECHA.setEnabled(true);
//                    txtLimite_Consultas_Per.setEditable(true);
//
//                    txtTotal_Pago.setEditable(true);
//                    ////////////////////////
//                    btnAgregar_ROL.setEnabled(true);
//               }else{
//                    TB_TURNOS_UO.setEnabled(true);
//                    TB_TURNOS_UO.setBackground(Color.white);
//                    D_FECHA.setEnabled(true);
//                    txtLimite_Consultas_Per.setEditable(true);
//
//                    txtTotal_Pago.setEditable(true);
//                    //////////////////////////
//                    btnAgregar_ROL.setEnabled(false);
//               }
               //////////////////////////////////////////////////
               
           }else{
               if(lblGM.getText().equalsIgnoreCase("E")){
                   if( TB_TURNOS_PERSONAL_ROL.getRowCount()>0){
                            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                         
                            txtCOD_ROL.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString());
                            LBL_UO_HORAS_ELIMINAR.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0)));
                   }
                    
                    cargar_tb_personal_rol_tb_UO();
               }
//               else{
//                   if(lblGM.getText().equalsIgnoreCase("G")){
//                       int filaselec = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
//                       String fila = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString();
//                       if(fila.length()==0){
//                            txtCOD_ROL.setText("");
//                            
//                       }else{
//                            txtCOD_ROL.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString());
//                       }                    
//                   }
//               }
           }
           
       }
       if(evt.getClickCount()==2){
           
           LBL_AREA_ACT.setText(LBL_AREA.getText());
           
           if(lblGM.getText().equalsIgnoreCase("G")){
                
                int filaseleccionada = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                if(!String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaseleccionada, 10)).equals("null")){           
                    TXT_COD_ROL_GUARDAR.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaseleccionada, 10)));
                    TXT_MOSTRAR_HORA_GUARDAR_UO.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaseleccionada, 0)));
                    ROL_ACTIVIDAD.setVisible(true);
                    mostrar_ROL_ACTIVIDADES_GUARDAR();
                    txt_ACTIVIDAD.setEditable(false);
                    cb_HORA_INICIO.setEnabled(false);
                    cb_HORA_FIN.setEnabled(false);
                    btn_AGREGAR_ACTIVIDADES.setEnabled(false);
                    btnguardar_ACTIVIDAD_ROL.setEnabled(false);
                    BTN_QUITAR_ACTIVIDAD.setEnabled(false);
                    TB_ROL_ACTIVIDAD.setEnabled(false);
                    TB_ROL_ACTIVIDAD.setBackground(Color.lightGray);
                    TB_ACTIVIDADES_LISTA.setEnabled(false);
                    TB_ACTIVIDADES_LISTA.setBackground(Color.lightGray);
                    txt_ACTIVIDAD.setText("");
                    mostrar_HORAS_UO_ELIMINAR(TXT_MOSTRAR_HORA_GUARDAR_UO.getText());
                    
                    
//                    txtHoras_Libres.setText("00:00:00");
//                    
//                    int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
//           String COD_TUR_UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString();
//           mostrar_HORAS(COD_TUR_UO);
//           CANTIDAD_HORAS_TOTAL();

                    txtHoras_Libres.setText("00:00:00");
                    
                }else {
                    
                    /////pasar dia, TOTAL DE HORAS
                if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
                    int filaa = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                                        
                    String horas = String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaa, 7));
                    if(Integer.parseInt(horas) < 10){
                        txtHoras_Libres.setText("0" + horas + ":00:00");
                    }else{
                        if(Integer.parseInt(horas) >= 10){
                            txtHoras_Libres.setText(horas + ":00:00");
                        }
                        
                    }
                    
                }
                /////////
                    
                        if(LBL_PASAR_DIA.getText().equalsIgnoreCase("N")){
                            
                            TXT_COD_ROL_GUARDAR.setText("");
                     
                            ROL_ACTIVIDAD.setVisible(true);
                            txt_ACTIVIDAD.setEditable(true);
                            txt_ACTIVIDAD.requestFocus();
                            mostrar_ROL_ACTIVIDADES_GUARDAR();
                            cb_HORA_INICIO.setEnabled(true);
                            cb_HORA_FIN.setEnabled(true);
                            btn_AGREGAR_ACTIVIDADES.setEnabled(true);
                            btnguardar_ACTIVIDAD_ROL.setEnabled(false);
                            BTN_QUITAR_ACTIVIDAD.setEnabled(true);
                            TB_ROL_ACTIVIDAD.setEnabled(true);
                            TB_ROL_ACTIVIDAD.setBackground(Color.white);
                            TB_ACTIVIDADES_LISTA.setEnabled(true);
                            TB_ACTIVIDADES_LISTA.setBackground(Color.white);

                            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                            String COD_TUR_UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString();
                            mostrar_HORAS(COD_TUR_UO);
                            CANTIDAD_HORAS_TOTAL();

                            txtHoras_Libres.setText(LBL_CANTIDAD_HORAS_TOTAL.getText());
                            txt_ACTIVIDAD.setText("");
                            
                        }else{
                            if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
                                TXT_COD_ROL_GUARDAR.setText("");
                     
                            ROL_ACTIVIDAD.setVisible(true);
                            txt_ACTIVIDAD.setEditable(true);
                            txt_ACTIVIDAD.requestFocus();
                            mostrar_ROL_ACTIVIDADES_GUARDAR();
                            cb_HORA_INICIO.setEnabled(true);
                            cb_HORA_FIN.setEnabled(true);
                            btn_AGREGAR_ACTIVIDADES.setEnabled(true);
                            btnguardar_ACTIVIDAD_ROL.setEnabled(false);
                            BTN_QUITAR_ACTIVIDAD.setEnabled(true);
                            TB_ROL_ACTIVIDAD.setEnabled(true);
                            TB_ROL_ACTIVIDAD.setBackground(Color.white);
                            TB_ACTIVIDADES_LISTA.setEnabled(true);
                            TB_ACTIVIDADES_LISTA.setBackground(Color.white);

                            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                            String COD_TUR_UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString();
                            mostrar_HORAS(COD_TUR_UO);
//                            CANTIDAD_HORAS_TOTAL();

                            LBL_CANTIDAD_HORAS_TOTAL.setText(txtHoras_Libres.getText());
                            txt_ACTIVIDAD.setText("");
                            }
                            
                        }
                        
                        
                       
                }
                
                
                
                
           }else{
               if(lblGM.getText().equalsIgnoreCase("M")){
                    
                    int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        
                    int MODIFICAR_A = JOptionPane.showConfirmDialog(this, "¿Desea Quitar las Actividades y \n Agregar nuevas nuevas?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    
                    if(MODIFICAR_A == 0 ){
                        
                        ROL_ACTIVIDAD.setVisible(true);
                        txt_ACTIVIDAD.setEditable(true);
                        txt_ACTIVIDAD.requestFocus();
                        
                        int filaselec1=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                        String COD_TUR_UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec1, 0).toString();
                        mostrar_HORAS(COD_TUR_UO);
                        CANTIDAD_HORAS_TOTAL();

                        txtHoras_Libres.setText(LBL_CANTIDAD_HORAS_TOTAL.getText());
                        txt_ACTIVIDAD.setText("");
//                        mostrar_ROL_ACTIVIDADES();
//                        txtHoras_Libres.setText("00:00:00");
                    } 
                   
               }else{
                   if(lblGM.getText().equalsIgnoreCase("E")){
                        int filaselect = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                        mostrar_HORAS_UO_ELIMINAR(LBL_UO_HORAS_ELIMINAR.getText());
                       
                        TXT_COD_ROL_GUARDAR.setText(String.valueOf(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselect, 10)));
                        ROL_ACTIVIDAD.setVisible(true);
                        mostrar_ROL_ACTIVIDADES_GUARDAR();
                        txt_ACTIVIDAD.setEditable(false);
                        cb_HORA_INICIO.setEnabled(false);
                        cb_HORA_FIN.setEnabled(false);
                        btn_AGREGAR_ACTIVIDADES.setEnabled(false);
                        btnguardar_ACTIVIDAD_ROL.setEnabled(false);
                        BTN_QUITAR_ACTIVIDAD.setEnabled(false);
                        TB_ROL_ACTIVIDAD.setEnabled(false);
                        TB_ROL_ACTIVIDAD.setBackground(Color.lightGray);
                        TB_ACTIVIDADES_LISTA.setEnabled(false);
                        TB_ACTIVIDADES_LISTA.setBackground(Color.lightGray);
                        txt_ACTIVIDAD.setText("");
                        txtHoras_Libres.setText("00:00:00");
                   }
               }
           }
//           int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
//           String COD_TUR_UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString();
//           mostrar_HORAS(COD_TUR_UO);
//           CANTIDAD_HORAS_TOTAL();
//
//           txtHoras_Libres.setText(LBL_CANTIDAD_HORAS_TOTAL.getText());
//           
                try {
               
                    this.cb_HORA_INICIO.removeAllItems(); 
                    this.cb_HORA_FIN.removeAllItems(); 
                    Statement sta=con.createStatement();
                    String h1=TXT_HORA_INICIO.getText();
                    String h2=TXT_HORA_FIN.getText();
                    
                    if(LBL_PASAR_DIA.getText().equalsIgnoreCase("N")){
                        ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa BETWEEN '"+h1+"' AND '"+h2+"'");
//                        this.cb_HORA_INICIO.addItem("Seleccionar...");
//                        this.cb_HORA_FIN.addItem("Seleccionar...");
                        while(rs.next()){
                        this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                        this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));
                  
                    }
                    }else{
                        if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
                            ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa >= '"+h1+"' or hora_completa <='"+h2+"'");
//                            this.cb_HORA_INICIO.addItem("Seleccionar...");
//                            this.cb_HORA_FIN.addItem("Seleccionar...");
                            while(rs.next()){
                            this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                            this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));
                  
                    }
                        }
                    }
   
                } catch (Exception e) {
                    System.out.println("error cargar horas cbx: " + e.getMessage());
                    
                }
//                     }else{
//                            this.cbxDistritoNac.removeAllItems();
//
//                        this.cbxDistritoNac.addItem("Seleccionar...");
//                            }
                
                if(TB_ACTIVIDADES_LISTA.getRowCount()==0){
                     BTN_AGREGAR_ACTIVIDADES_FRM.setVisible(true);
                }else{
                     BTN_AGREGAR_ACTIVIDADES_FRM.setVisible(false);
                }
            
        }
    }//GEN-LAST:event_TB_TURNOS_PERSONAL_ROLMouseClicked

    private void TB_TURNOS_PERSONAL_ROLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_TURNOS_PERSONAL_ROLKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_TB_TURNOS_PERSONAL_ROLKeyPressed

    private void btnAgregar_ROLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar_ROLActionPerformed
//          ROL_ACTIVIDAD.setVisible(true);
        if(lblGM.getText().equalsIgnoreCase("G")){
            Agregar_Personal_ROL();
//            btnBuscarP.setEnabled(false);
        }else{
            if(lblGM.getText().equalsIgnoreCase("M")){
                try {
                    int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        
                    int MODIFICAR = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(MODIFICAR == 0 ){
                        
//                        if(CB_Envio_ROL.isSelected()){
//                            PR.setENVIO_ROL("S"); 
//                        }else{
//                            PR.setENVIO_ROL("N"); 
//                        }
                        
                            modificar_TB_PERSONAL_ROL();
                                                                   
                            //quitar la fila seleccionada de la tabla 
                            DefaultTableModel modelo = (DefaultTableModel)TB_TURNOS_PERSONAL_ROL.getModel();
                            modelo.removeRow(filaselec);

                             Agregar_Personal_ROL();
                            
                            JOptionPane.showMessageDialog(null, "Datos Modificados");                                                         
                       
                        }
                                
                } catch (Exception e) {
                    System.out.println("error al modificar" + e.getMessage());
                }
            }
        }
        
        
//        btnguardar.setEnabled(true);
    }//GEN-LAST:event_btnAgregar_ROLActionPerformed

    private void cb_Rango_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_Rango_fechaActionPerformed
        seleccion();
    }//GEN-LAST:event_cb_Rango_fechaActionPerformed

    private void TB_PERSONAL_ROLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_PERSONAL_ROLKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    cargar_personal_rol();
                    P_ROL.setVisible(true);
                    P_FECHAS.setVisible(false);
                    txtBuscarMedico_ROL.setText("");
                    cb_Rango_fecha.setSelected(false);
                    lblListado_Edicion.setText("Edición");
                    TB_TURNOS_PERSONAL_ROL.setEnabled(false);
                    TB_TURNOS_PERSONAL_ROL.setBackground(Color.lightGray);
                    TB_TURNOS_UO.setEnabled(false);
                    TB_TURNOS_UO.setBackground(Color.lightGray);
                    btnBuscarP.setEnabled(false);
                    D_FECHA.setEnabled(false);
                    txtLimite_Consultas_Per.setEditable(false);
//                    txtTotal_Horas.setEditable(false);
                    txtTotal_Pago.setEditable(false);
                    btnAgregar_ROL.setEnabled(false);
//                    TB_TURNOS_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
//                    TB_TURNOS_PERSONAL_ROL.requestFocus();
//                  btnPersonalRegistra.requestFocus();
                    btneditar.setEnabled(true);
                    btneliminar.setEnabled(false);
                    txtBuscarMedico_ROL.setEditable(false);
//                    
//                    jScrollPane3.setVisible(true);
        }
    }//GEN-LAST:event_TB_PERSONAL_ROLKeyPressed

    private void TB_PERSONAL_ROLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_PERSONAL_ROLKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            
                int filaselec=TB_PERSONAL_ROL.getSelectedRow();
                            
                            txtNOMBRE.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString());
        }
    }//GEN-LAST:event_TB_PERSONAL_ROLKeyReleased

    private void txtBuscarMedico_ROLKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_ROLKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            TB_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
            TB_PERSONAL_ROL.requestFocus();
            int filaselec=TB_PERSONAL_ROL.getSelectedRow();
                            
                            txtNOMBRE.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString());
        }
    }//GEN-LAST:event_txtBuscarMedico_ROLKeyTyped

    private void txtBuscarMedico_ROLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_ROLKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            TB_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
            TB_PERSONAL_ROL.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMedico_ROLKeyPressed

    private void TB_PERSONAL_ROLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_PERSONAL_ROLMouseClicked
        if(evt.getClickCount()==1){
            int filaselec=TB_PERSONAL_ROL.getSelectedRow();
                            
                            txtNOMBRE.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString());
        }
    }//GEN-LAST:event_TB_PERSONAL_ROLMouseClicked

    private void txtBuscarMedico_ROLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_ROLKeyReleased
        txtBuscarMedico_ROL.setText(txtBuscarMedico_ROL.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarMedico_ROLKeyReleased

    private void btnBuscarPersonal_rol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonal_rol1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPersonal_rol1ActionPerformed

    private void D_FECHA_FINPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_D_FECHA_FINPropertyChange
        btnBuscarPersonal_rol.requestFocus();
    }//GEN-LAST:event_D_FECHA_FINPropertyChange

    private void btnBuscarPersonal_rolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarPersonal_rolKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarPersonal_rol.doClick();
        }
        
    }//GEN-LAST:event_btnBuscarPersonal_rolKeyTyped

    private void btn_AGREGAR_ACTIVIDADESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AGREGAR_ACTIVIDADESActionPerformed

        if(repiteDetalleActividad()==true){
               JOptionPane.showMessageDialog(ROL_ACTIVIDAD,"La Actividad ya ha Sido Ingresado.");   
               txt_ACTIVIDAD.setText("");
               txt_ACTIVIDAD.requestFocus();
               cb_HORA_INICIO.setSelectedIndex(0);
               cb_HORA_FIN.setSelectedIndex(0);
               LBL_TOTAL_HORA.setText("00:00:00");
               
        }else{
                hora_mayor();
                Agregar_ROL_ACTIVIDAD();
                txt_ACTIVIDAD.setText("");
                cb_HORA_INICIO.setSelectedIndex(0);
                cb_HORA_FIN.setSelectedIndex(0);
                txt_ACTIVIDAD.requestFocus();

                LBL_CANTIDAD_HORAS_TOTAL.setText(txtHoras_Libres.getText());

                LBL_TOTAL_HORA.setText("00:00:00");
                btn_guardar_actividad_boton();
                LBL_HORAS_LIBRES_SUMA.setText(txtHoras_Libres.getText());
                
                
                ////////cargar combos
                try {
               
                    this.cb_HORA_INICIO.removeAllItems(); 
                    this.cb_HORA_FIN.removeAllItems(); 
                    Statement sta=con.createStatement();
                    String h1=LBL_HORA_FIN_1.getText();
                    String h2=TXT_HORA_FIN.getText();
                    
                    if(LBL_PASAR_DIA.getText().equalsIgnoreCase("N")){
                        ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa BETWEEN '"+h1+"' AND '"+h2+"'");
//                        this.cb_HORA_INICIO.addItem("Seleccionar...");
//                        this.cb_HORA_FIN.addItem("Seleccionar...");
                        while(rs.next()){
                        this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                        this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));
                  
                    }
                    }else{
                        if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
                            ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa >= '"+h1+"' OR hora_completa <='"+h2+"'");
//                            this.cb_HORA_INICIO.addItem("Seleccionar...");
//                            this.cb_HORA_FIN.addItem("Seleccionar...");
                            while(rs.next()){
                            this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                            this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));
                  
                    }
                        }
                    }
   
                } catch (Exception e) {
                    System.out.println("error cargar horas cbx: " + e.getMessage());
                    
                }
                /////////
        }
  
        
    }//GEN-LAST:event_btn_AGREGAR_ACTIVIDADESActionPerformed

    private void TB_ROL_ACTIVIDADMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_ROL_ACTIVIDADMouseClicked
        if(evt.getClickCount()==1){
            int filaselect = TB_ROL_ACTIVIDAD.getSelectedRow();
            LBL_HORA_TOTAL_QUITAR.setText(TB_ROL_ACTIVIDAD.getValueAt(filaselect, 4).toString());
            
            
            
            txt_ACTIVIDAD.setText("");
            cb_HORA_INICIO.setSelectedIndex(0);
            cb_HORA_FIN.setSelectedIndex(0);
            LBL_TOTAL_HORA.setText("00:00:00");
                        
        }
                
    }//GEN-LAST:event_TB_ROL_ACTIVIDADMouseClicked

    private void TB_ROL_ACTIVIDADKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_ROL_ACTIVIDADKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TB_ROL_ACTIVIDADKeyPressed

    private void txt_ACTIVIDADCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_ACTIVIDADCaretUpdate
//        P_ACTIVIDAD.setVisible(true);
        Buscar_ACTIVIDADES();
        
        if(TB_ACTIVIDADES_LISTA.getRowCount()==0){
                BTN_AGREGAR_ACTIVIDADES_FRM.setVisible(true);
            }else{
                if(TB_ACTIVIDADES_LISTA.getRowCount() > 0){
                    BTN_AGREGAR_ACTIVIDADES_FRM.setVisible(false);
                }
                
        }
    }//GEN-LAST:event_txt_ACTIVIDADCaretUpdate

    private void btn_BUSCAR_ACTIVIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BUSCAR_ACTIVIDADActionPerformed
        
    }//GEN-LAST:event_btn_BUSCAR_ACTIVIDADActionPerformed

    private void btnguardar_ACTIVIDAD_ROLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_ACTIVIDAD_ROLActionPerformed
        
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        if(lblGM.getText().equalsIgnoreCase("G")){
            if(txtMedico_UO.getText().equalsIgnoreCase("") || TB_TURNOS_UO.getRowCount()==0 || 
                   D_FECHA.getDate()==null || txtLimite_Consultas_Per.getText().equalsIgnoreCase("") ||
                   txtTotal_Horas.getText().equalsIgnoreCase("") || txtTotal_Pago.getText().equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Ingrese todos los campos");
            }else{
                int guardar = JOptionPane.showConfirmDialog(ROL_ACTIVIDAD, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(guardar == 0 ){
                    guardar_TB_PERSONAL_ROL();
                    JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Datos Guardados");
                    ROL_ACTIVIDAD.dispose();
                    
                    int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                    DefaultTableModel modelo = (DefaultTableModel)TB_TURNOS_PERSONAL_ROL.getModel();

                                            modelo.removeRow(filaselec);
                    
                    agregar_rol_act_1();
                                            
                    
//                    Clear_TB_ACTIVIDADES();
                    Clear_TB_ROL_ACTIVIDAD();
                    txtCOD_ROL.setText("");
//                    TXT_COD_ROL_GUARDAR.setText("");
                    
                    btnBuscarP.setEnabled(false);
                    TB_TURNOS_UO.setEnabled(true);
                    TB_TURNOS_UO.setBackground(Color.white);
                    TB_TURNOS_UO.getSelectionModel().setSelectionInterval(0, 0);
                    TB_TURNOS_UO.requestFocus();
                    D_FECHA.setDate(null);
                    txtLimite_Consultas_Per.setText("");
                    txtTotal_Horas.setText("");
                    txtTotal_Pago.setText("");
                    TB_TURNOS_PERSONAL_ROL.setEnabled(true);
                    TB_TURNOS_PERSONAL_ROL.setBackground(Color.white);
                    BTN_QUITAR.setEnabled(false);
                    
                }
                
            }
        }else{
            if(lblGM.getText().equalsIgnoreCase("M")){
                if(txtMedico_UO.getText().equalsIgnoreCase("") || TB_TURNOS_UO.getRowCount()==0 || 
                   D_FECHA.getDate()==null || txtLimite_Consultas_Per.getText().equalsIgnoreCase("") ||
                   txtTotal_Horas.getText().equalsIgnoreCase("") || txtTotal_Pago.getText().equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
                }else{
                        int MODIFICAR = JOptionPane.showConfirmDialog(ROL_ACTIVIDAD, "¿Está seguro que desea MODIFICAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                        if(MODIFICAR == 0 ){
                            guardar_TB_PERSONAL_ROL_modificar();
                            JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Datos Modificados");
                            ROL_ACTIVIDAD.dispose();
        //                    Clear_TB_ACTIVIDADES();
                            Clear_TB_ROL_ACTIVIDAD();

                            btnBuscarP.setEnabled(false);
                            TB_TURNOS_UO.getSelectionModel().setSelectionInterval(0, 0);
                            TB_TURNOS_UO.requestFocus();
                            D_FECHA.setDate(null);
                            txtLimite_Consultas_Per.setText("");
                            txtTotal_Horas.setText("");
                            txtTotal_Pago.setText("");
                        }
                        
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, "error al guardar");
            }
        }        
         lblGM.setText("G");
         
    }//GEN-LAST:event_btnguardar_ACTIVIDAD_ROLActionPerformed

    private void txt_ACTIVIDADKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ACTIVIDADKeyTyped
        
    }//GEN-LAST:event_txt_ACTIVIDADKeyTyped

    private void cb_HORA_INICIOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_HORA_INICIOKeyPressed
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
           if(cb_HORA_FIN.getSelectedIndex()==0){
                cb_HORA_FIN.setVisible(true);    
                cb_HORA_FIN.showPopup();
                cb_HORA_FIN.requestFocus(true);
           }else{
                restar_horas();
           }

       }
       
    }//GEN-LAST:event_cb_HORA_INICIOKeyPressed

    private void txtTotal_PagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotal_PagoKeyTyped
        //permite escribir solo numeros y un punto decimal
        try {
            
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtTotal_Pago.getText().contains(".")){
            evt.consume();            
        }
        
        if(txtTotal_Pago.getText().length()==4){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    
    } catch (Exception e) {
            System.out.println("error" + e.getMessage());
    }
        
    }//GEN-LAST:event_txtTotal_PagoKeyTyped

    private void txtLimite_Consultas_PerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerKeyTyped
        //permite escribir solo numeros
        try {        

            char tecla;
            tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
            
            if (txtLimite_Consultas_Per.getText().length()>1)
            {
                evt.consume();
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }//GEN-LAST:event_txtLimite_Consultas_PerKeyTyped

    private void lblCOD_UO_1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblCOD_UO_1CaretUpdate
       if(txtUO_3.getText().equalsIgnoreCase(lblCOD_UO_1.getText())){
                        btnAgregar_ROL.setEnabled(true);
                    }else{
                        btnAgregar_ROL.setEnabled(false);
                    }
    }//GEN-LAST:event_lblCOD_UO_1CaretUpdate

    private void txt_ACTIVIDADKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ACTIVIDADKeyPressed
        char tecla= evt.getKeyChar();       
        if(tecla==KeyEvent.VK_ENTER){
            TB_ACTIVIDADES_LISTA.getSelectionModel().setSelectionInterval(0, 0);
            TB_ACTIVIDADES_LISTA.requestFocus();
        }
        
//        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
//            TB_ACTIVIDADES.getSelectionModel().setSelectionInterval(0, 0);
//            TB_ACTIVIDADES.requestFocus();
//        }
    }//GEN-LAST:event_txt_ACTIVIDADKeyPressed

    private void TB_ACTIVIDADES_LISTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TB_ACTIVIDADES_LISTAMouseClicked

    }//GEN-LAST:event_TB_ACTIVIDADES_LISTAMouseClicked

    private void TB_ACTIVIDADES_LISTAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TB_ACTIVIDADES_LISTAKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = TB_ACTIVIDADES_LISTA.getSelectedRow();
                txt_COD_UNI_ORG_ACTIVIDADES.setText(String.valueOf(TB_ACTIVIDADES_LISTA.getValueAt(fila, 1)));
                txt_COD_UNI_ORGANICA_JERAR.setText(String.valueOf(TB_ACTIVIDADES_LISTA.getValueAt(fila, 2)));
                txt_ACTIVIDAD.setText(String.valueOf(TB_ACTIVIDADES_LISTA.getValueAt(fila, 3)));
//                TXT_AR_ID_ACTI.setText(String.valueOf(TB_ACTIVIDADES_LISTA.getValueAt(fila, 4)));
                
                mostrar_ACTIVIDADES();
                
                cb_HORA_INICIO.setVisible(true);    
                cb_HORA_INICIO.showPopup();
                cb_HORA_INICIO.requestFocus(true);
        }
        
    }//GEN-LAST:event_TB_ACTIVIDADES_LISTAKeyPressed

    private void BTN_QUITAR_ACTIVIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_QUITAR_ACTIVIDADActionPerformed
        try{
//            int filaselec=TB_ROL_ACTIVIDAD.getSelectedRow();
//            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(ROL_ACTIVIDAD, "¿Está Seguro que Desea \n QUITAR TODAS las Actividad Asignadas  ?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    
                    Clear_TB_ACTIVIDAD_QUITAR();
                    CANTIDAD_HORAS_TOTAL_QUITAR_ACT();
                    
                    txt_ACTIVIDAD.requestFocus();
                    cb_HORA_INICIO.setEnabled(true);
                    cb_HORA_FIN.setEnabled(true);
                    btn_AGREGAR_ACTIVIDADES.setEnabled(true);
                    TB_ACTIVIDADES_LISTA.setEnabled(true);
                    TB_ACTIVIDADES_LISTA.setBackground(Color.white);
                    
                    if(TB_ROL_ACTIVIDAD.getRowCount() == 0){
                        LBL_HORA_FIN_1.setText(TXT_HORA_INICIO.getText());
                        
                        ////////cargar combos
                        try {

                            this.cb_HORA_INICIO.removeAllItems(); 
                            this.cb_HORA_FIN.removeAllItems(); 
                            Statement sta=con.createStatement();
                            String h1=LBL_HORA_FIN_1.getText();
                            String h2=TXT_HORA_FIN.getText();

                            if(LBL_PASAR_DIA.getText().equalsIgnoreCase("N")){
                                ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa BETWEEN '"+h1+"' AND '"+h2+"'");
        //                        this.cb_HORA_INICIO.addItem("Seleccionar...");
        //                        this.cb_HORA_FIN.addItem("Seleccionar...");
                                while(rs.next()){
                                this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                                this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));

                            }
                            }else{
                                if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
                                    ResultSet rs=sta.executeQuery("SELECT hora_completa FROM PERSONAL_HORA WHERE hora_completa >= '"+h1+"' OR hora_completa <='"+h2+"'");
        //                            this.cb_HORA_INICIO.addItem("Seleccionar...");
        //                            this.cb_HORA_FIN.addItem("Seleccionar...");
                                    while(rs.next()){
                                    this.cb_HORA_INICIO.addItem(rs.getString("hora_completa"));
                                    this.cb_HORA_FIN.addItem(rs.getString("hora_completa"));

                            }
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("error cargar horas cbx: " + e.getMessage());

                        }
                        
                        
                    }
                    
                }
//            }else{
//                JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Seleccione el registro a Eliminar");
//            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "error al eliminar rol");
        }
//        SUMAR_HORAS();
        
        btn_guardar_actividad_boton();
        txt_ACTIVIDAD.setEditable(true);
        txt_ACTIVIDAD.requestFocus();
    }//GEN-LAST:event_BTN_QUITAR_ACTIVIDADActionPerformed

    private void cb_HORA_FINKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cb_HORA_FINKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            LBL_HORA_FIN_1.setText(String.valueOf(cb_HORA_FIN.getSelectedItem()));
            restar_horas();
            btn_AGREGAR_ACTIVIDADES.requestFocus();
             
        }
        
    }//GEN-LAST:event_cb_HORA_FINKeyPressed

    private void cb_HORA_FINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_HORA_FINActionPerformed

    }//GEN-LAST:event_cb_HORA_FINActionPerformed

    private void cb_HORA_FINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_HORA_FINItemStateChanged

    }//GEN-LAST:event_cb_HORA_FINItemStateChanged

    private void cb_HORA_FINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_HORA_FINMouseClicked
        //        restar_horas();
    }//GEN-LAST:event_cb_HORA_FINMouseClicked

    private void btnBuscarPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBuscarPKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    btnBuscarP.doClick();
                    
        }
    }//GEN-LAST:event_btnBuscarPKeyPressed

    private void D_FECHAPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_D_FECHAPropertyChange
//        if(lblGM.getText().equalsIgnoreCase("G")){
//            txtLimite_Consultas_Per.requestFocus();
//        }
  
            
    }//GEN-LAST:event_D_FECHAPropertyChange

    private void txtLimite_Consultas_PerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    btnAgregar_ROL.requestFocus();
        }
                
    }//GEN-LAST:event_txtLimite_Consultas_PerKeyPressed

    private void txtTotal_PagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotal_PagoKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    if(txtTotal_Pago.getText().equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(rootPane, "Ingrese el Total de Pago");
                    }else{
                        btnAgregar_ROL.requestFocus();
                    }
      
        }
                
    }//GEN-LAST:event_txtTotal_PagoKeyPressed

    private void btnAgregar_ROLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregar_ROLKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    btnAgregar_ROL.doClick();
                    TB_TURNOS_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
                    TB_TURNOS_PERSONAL_ROL.requestFocus();
        }
                
    }//GEN-LAST:event_btnAgregar_ROLKeyPressed

    private void btn_AGREGAR_ACTIVIDADESKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_AGREGAR_ACTIVIDADESKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    btn_AGREGAR_ACTIVIDADES.doClick();
//                    TB_TURNOS_PERSONAL_ROL.getSelectionModel().setSelectionInterval(0, 0);
//                    TB_TURNOS_PERSONAL_ROL.requestFocus();
        }
                
    }//GEN-LAST:event_btn_AGREGAR_ACTIVIDADESKeyPressed

    private void txtAR_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAR_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAR_IDActionPerformed

    private void txtNOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNOMBREActionPerformed

    private void D_FECHA_INICIOAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_D_FECHA_INICIOAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_D_FECHA_INICIOAncestorAdded

    private void D_FECHA_FINAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_D_FECHA_FINAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_D_FECHA_FINAncestorAdded

    private void BTN_QUITARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_QUITARActionPerformed
       QUITAR_PERSONAL_ROL();
                
    }//GEN-LAST:event_BTN_QUITARActionPerformed

    private void txtBuscarMedico_ROLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedico_ROLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedico_ROLActionPerformed

    private void txtLimite_Consultas_PerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLimite_Consultas_PerActionPerformed

    private void txtTotal_HorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotal_HorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotal_HorasActionPerformed

    private void txtTotal_PagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotal_PagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotal_PagoActionPerformed

    private void txtCOD_ROLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCOD_ROLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCOD_ROLActionPerformed

    private void lblCOD_UO_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCOD_UO_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCOD_UO_1ActionPerformed

    private void TXT_COD_ROL_GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_COD_ROL_GUARDARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_COD_ROL_GUARDARActionPerformed

    private void txtUO_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUO_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUO_3ActionPerformed

    private void TXT_CODIGO_ROL_GActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_CODIGO_ROL_GActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_CODIGO_ROL_GActionPerformed

    private void txt_COD_UNI_ORG_ACTIVIDADESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_COD_UNI_ORG_ACTIVIDADESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_COD_UNI_ORG_ACTIVIDADESActionPerformed

    private void txt_COD_UNI_ORGANICA_JERARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_COD_UNI_ORGANICA_JERARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_COD_UNI_ORGANICA_JERARActionPerformed

    private void TXT_AR_ID_ACTIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_AR_ID_ACTIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_AR_ID_ACTIActionPerformed

    private void txt_ACTIVIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ACTIVIDADActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ACTIVIDADActionPerformed

    private void btn_AGREGAR_ACTIVIDADESItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btn_AGREGAR_ACTIVIDADESItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_AGREGAR_ACTIVIDADESItemStateChanged

    private void LBL_HORA_FIN_1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_LBL_HORA_FIN_1CaretUpdate
                
    }//GEN-LAST:event_LBL_HORA_FIN_1CaretUpdate

    private void D_FECHAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D_FECHAKeyPressed

    }//GEN-LAST:event_D_FECHAKeyPressed

    private void txtMedico_UOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedico_UOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedico_UOActionPerformed

    private void txtLimite_Consultas_PerCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerCaretUpdate
        FECHA_PAGOS();
    }//GEN-LAST:event_txtLimite_Consultas_PerCaretUpdate

    private void TXT_MESCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TXT_MESCaretUpdate
       PAGOS_HORAS_DIA();
    }//GEN-LAST:event_TXT_MESCaretUpdate

    private void txtLimite_Consultas_PerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLimite_Consultas_PerKeyReleased

    private void txtLimite_Consultas_PerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLimite_Consultas_PerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLimite_Consultas_PerMouseClicked

    private void btnBuscarPersonal_rol1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarPersonal_rol1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPersonal_rol1MouseClicked

    private void BTN_AGREGAR_ACTIVIDADES_FRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGAR_ACTIVIDADES_FRMActionPerformed
        ROL_ACTIVIDAD.dispose();
        PERSONAL_ACTIVIDADES PA = new PERSONAL_ACTIVIDADES();
        PA.setVisible(true);
    }//GEN-LAST:event_BTN_AGREGAR_ACTIVIDADES_FRMActionPerformed

    private void BTN_AGREGAR_TURNOS_UOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGAR_TURNOS_UOActionPerformed
        PERSONAL_TURNOS PT = new PERSONAL_TURNOS();
        PT.setVisible(true);

    }//GEN-LAST:event_BTN_AGREGAR_TURNOS_UOActionPerformed

    public void CANTIDAD_HORAS_LIBRES_RESTA(){
        String L = LBL_TOTAL_HORA.getText();
        String L1 = LBL_CANTIDAD_HORAS_TOTAL.getText();
        
        int a = Integer.parseInt(L.substring(0, 2));
        int b = Integer.parseInt(L.substring(3, 5));
        int c = Integer.parseInt(L.substring(6, 8));
        
        int a1 = Integer.parseInt(L1.substring(0, 2));
        int b1 = Integer.parseInt(L1.substring(3, 5));
        int c1 = Integer.parseInt(L1.substring(6, 8));
        
        int hora =0, minuto=0, segundo =0;
        
        if(a < a1 && b < b1){
            hora = a1 - a;
            minuto = b1 - b;
        }
        
        if(a == a1 && b < b1){
            hora = 00;
            minuto = b1 - b;
        }
        
        if(a < a1 && b == b1){
            hora = a1 - a;
            minuto = 00;
        }
        
        if(a < a1 && b > b1){
            hora = (a1 - a) - 1;
            int m=0;
            m = 60 - b;
            minuto = m + b1;
        }

        String horaf = "", minutof ="";
        
        if(hora < 10){
            horaf = ("0" + hora);
        }else{
            horaf = String.valueOf(hora);
        }
        
        if(minuto < 10){
            minutof = ("0" + minuto);
        }else{
            minutof = String.valueOf(minuto);
        }
        
        txtHoras_Libres.setText(horaf + ":" + minutof + ":" + segundo + "0");
//        System.out.println("hora: " + hora + " " + minuto + " " + segundo);
    }
    
    public void seleccion(){
        if(cb_Rango_fecha.isSelected()==true){
            P_FECHAS.setVisible(true);
            P_ROL.setVisible(false);
            jTabbedPane1.setSelectedIndex(1);
            txtBuscarMedico_ROL.setEditable(false);
            lblListado_Edicion.setText("Listado");
//            lblfecha_I.setVisible(true);
//            lblfecha_F.setVisible(true);
//            lblG.setVisible(true);
        }else{
            P_FECHAS.setVisible(false);
            D_FECHA_INICIO.setDate(null);
            jTabbedPane1.setSelectedIndex(1);
            Mostrar_PERSONAL_ROL();
            txtBuscarMedico_ROL.setEditable(true);
            txtBuscarMedico_ROL.requestFocus();
//            lblListado_Edicion.setText("Edición");
//            D_FECHA_FIN.setDate(null);
//            txtBuscarMedico_ROL.setText("");
//            lblfecha_I.setVisible(false);
//            lblfecha_F.setVisible(false);
//            lblG.setVisible(false);
        }
    }
    
    public void MostrarPersonal_UO(){
        try {
                     
            String consulta="";
            
            tb_Personal_UO.setModel(new DefaultTableModel());
            String titulos[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec PERSONAL_LISTAR_PER_UO";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            
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
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                
                m.addRow(fila);
                c++;
 
            }
            tb_Personal_UO.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Personal_UO.setRowSorter(elQueOrdena);
            tb_Personal_UO.setModel(m);
                       
            formatoPersonal_UO();
            
        } catch (Exception e) {
            System.out.println("Error mostrar personal UO: " + e.getMessage());
        }     
    }
    
    public void BuscarPersonal_UO(){
        try {
                     
            String consulta="";
            
            tb_Personal_UO.setModel(new DefaultTableModel());
            String titulos[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec PERSONAL_BUSCAR_PER_UO ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
            cmd.setString(1, txtBuscarMedico_UO.getText());
            
            ResultSet r= cmd.executeQuery();
            int c = 1;
            while(r.next()){
                          
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                
                m1.addRow(fila);
                c++;
            }
            tb_Personal_UO.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            tb_Personal_UO.setRowSorter(elQueOrdena);
            tb_Personal_UO.setModel(m1);
                       
            formatoPersonal_UO();
            
  
        }catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
    public void formatoPersonal_UO(){        
            tb_Personal_UO.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_Personal_UO.getColumnModel().getColumn(1).setPreferredWidth(50); 
            tb_Personal_UO.getColumnModel().getColumn(2).setPreferredWidth(110);
            tb_Personal_UO.getColumnModel().getColumn(3).setPreferredWidth(130);
            tb_Personal_UO.getColumnModel().getColumn(4).setPreferredWidth(130);                
            tb_Personal_UO.getColumnModel().getColumn(5).setPreferredWidth(230); 
            tb_Personal_UO.getColumnModel().getColumn(6).setPreferredWidth(170); 
            tb_Personal_UO.getColumnModel().getColumn(7).setPreferredWidth(80);
            tb_Personal_UO.getColumnModel().getColumn(8).setPreferredWidth(150);
            tb_Personal_UO.getColumnModel().getColumn(9).setPreferredWidth(150);
            tb_Personal_UO.getColumnModel().getColumn(10).setPreferredWidth(80);
            
    }
    
    public void inicializar_tabla_Medicos_UO(){       
        try {
            
            String titulosb[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[11];
            tb_Personal_UO.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tb_Personal_UO.setRowSorter(elQueOrdenasb);
            tb_Personal_UO.setModel(msb);
            
            formatoPersonal_UO();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }      
    }
    
    public void inicializar_tabla_Turnos_UO(){       
        try {
            
            String titulosb[]={"Nº","Cod. Turno UO","Cod. Turno","Cod. Horarios","Cod. T Turno",
                "Turno","Nomenclatura","Hora Inicio", "Hora Fin", "AR_ID","Hora Total","Pasar Día",
                "Precio Normal", "Precio Sabado", "Precio Domingo","Precio Feriado"};
            msb1=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb1);
            String filasb[]=new String[16];
            TB_TURNOS_UO.setModel(msb1);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb1);
            TB_TURNOS_UO.setRowSorter(elQueOrdenasb);
            TB_TURNOS_UO.setModel(msb1);
            
            formatoPersonal_TURNOS_UO();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }      
    }
    
    public void inicializar_tabla_Personal_ROL(){       
        try {
            
            String titulosb[]={"Cod. Turno UO","ID_per_uni_org","Nomenclatura","Día",
                "Mes","Año","Lim. Consulta Diaria", "Total Hora", "Total Pago","Nombres","cod_rol"};
            msb2=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb2);
            String filasb[]=new String[11];
            TB_TURNOS_PERSONAL_ROL.setModel(msb2);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb2);
            TB_TURNOS_PERSONAL_ROL.setRowSorter(elQueOrdenasb);
            TB_TURNOS_PERSONAL_ROL.setModel(msb2);
            
            formatoPersonal_ROL();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla personal rol: " + e);
        }      
    }
    
    public void inicializar_tabla_Actividades(){       
        try {
            
            String titulosb[]={"Nº","Cod_uni_org_acti","cod_uni_organica_jerar","Nombre de la Actividad","AR_ID"};
            msb3=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb3);
            String filasb[]=new String[5];
            TB_ACTIVIDADES_LISTA.setModel(msb3);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb3);
            TB_ACTIVIDADES_LISTA.setRowSorter(elQueOrdenasb);
            TB_ACTIVIDADES_LISTA.setModel(msb3);
            
            formato_ACTIVIDADES();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla ACTIVIDADES: " + e);
        }      
    }
    
    public void inicializar_tabla_ROL_ACTIVIDADES(){       
        try {
            
            String titulosb[]={"Cod_uni_org_acti","Actividad","Hora de Inicio","Hora de Fin","Total de Horas",
                "Cod_uni_organica_jerar","AR_ID","COD_ROL_ACT"};
            msb4=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb4);
            String filasb[]=new String[8];
            TB_ROL_ACTIVIDAD.setModel(msb4);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb4);
            TB_ROL_ACTIVIDAD.setRowSorter(elQueOrdenasb);
            TB_ROL_ACTIVIDAD.setModel(msb4);
            
            formato_ROL_ACTIVIDADES();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla ROL_ACTIVIDAD: " + e);
        }      
    }
    
    public void formatoPersonal_TURNOS_UO(){        
            TB_TURNOS_UO.getColumnModel().getColumn(0).setPreferredWidth(50);
            TB_TURNOS_UO.getColumnModel().getColumn(1).setPreferredWidth(144); 
            TB_TURNOS_UO.getColumnModel().getColumn(2).setPreferredWidth(110);
            TB_TURNOS_UO.getColumnModel().getColumn(3).setPreferredWidth(130);
            TB_TURNOS_UO.getColumnModel().getColumn(4).setPreferredWidth(110);                
            TB_TURNOS_UO.getColumnModel().getColumn(5).setPreferredWidth(110); 
            TB_TURNOS_UO.getColumnModel().getColumn(6).setPreferredWidth(150);
            TB_TURNOS_UO.getColumnModel().getColumn(7).setPreferredWidth(111); 
            TB_TURNOS_UO.getColumnModel().getColumn(8).setPreferredWidth(111);
            TB_TURNOS_UO.getColumnModel().getColumn(9).setPreferredWidth(150);
            TB_TURNOS_UO.getColumnModel().getColumn(10).setPreferredWidth(100);
            TB_TURNOS_UO.getColumnModel().getColumn(11).setPreferredWidth(100);
            TB_TURNOS_UO.getColumnModel().getColumn(12).setPreferredWidth(100);
            TB_TURNOS_UO.getColumnModel().getColumn(13).setPreferredWidth(100);
            TB_TURNOS_UO.getColumnModel().getColumn(14).setPreferredWidth(100);
            TB_TURNOS_UO.getColumnModel().getColumn(15).setPreferredWidth(100);
            //Ocultar
            TB_TURNOS_UO.getColumnModel().getColumn(2).setMinWidth(0);
            TB_TURNOS_UO.getColumnModel().getColumn(2).setMaxWidth(0);    
            TB_TURNOS_UO.getColumnModel().getColumn(3).setMinWidth(0);
            TB_TURNOS_UO.getColumnModel().getColumn(3).setMaxWidth(0);
            TB_TURNOS_UO.getColumnModel().getColumn(4).setMinWidth(0);
            TB_TURNOS_UO.getColumnModel().getColumn(4).setMaxWidth(0); 
            TB_TURNOS_UO.getColumnModel().getColumn(9).setMinWidth(0);
            TB_TURNOS_UO.getColumnModel().getColumn(9).setMaxWidth(0);
//            TB_TURNOS_UO.getColumnModel().getColumn(10).setMinWidth(0);
//            TB_TURNOS_UO.getColumnModel().getColumn(10).setMaxWidth(0);
            
    }
    
    public void formatoPersonal_ROL(){        
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(0).setPreferredWidth(50);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(1).setPreferredWidth(144); 
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(2).setPreferredWidth(110);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(3).setPreferredWidth(130);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(4).setPreferredWidth(110);                
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(5).setPreferredWidth(110); 
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(6).setPreferredWidth(150);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(7).setPreferredWidth(111); 
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(8).setPreferredWidth(111);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(9).setPreferredWidth(200);
            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(10).setPreferredWidth(100);
              
//            //Ocultar
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(0).setMinWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(0).setMaxWidth(0);    
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(1).setMinWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(1).setMaxWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(9).setMinWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(9).setMaxWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(10).setMinWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(10).setMaxWidth(0);
    
    }
    
    public void formato_ACTIVIDADES(){        
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(0).setPreferredWidth(50);
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(1).setPreferredWidth(160); 
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(2).setPreferredWidth(160);
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(3).setPreferredWidth(505);
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(4).setPreferredWidth(94);                

            //Ocultar
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(1).setMinWidth(0);
            TB_ACTIVIDADES_LISTA.getColumnModel().getColumn(1).setMaxWidth(0);    
    
    }
    
    public void formato_ROL_ACTIVIDADES(){        
            
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(0).setPreferredWidth(160); 
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(1).setPreferredWidth(470);
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(2).setPreferredWidth(120);
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(3).setPreferredWidth(120);    
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(4).setPreferredWidth(120); 
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(5).setPreferredWidth(100); 
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(6).setPreferredWidth(80); 
            TB_ROL_ACTIVIDAD.getColumnModel().getColumn(7).setPreferredWidth(80); 
 
            //Ocultar
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(0).setMinWidth(0);
//            TB_TURNOS_PERSONAL_ROL.getColumnModel().getColumn(0).setMaxWidth(0);    
    
    }
        
    public void mostrar_TURNOS_UO(){
        try {                   
                        //detalle
                        String consulta="";
                        TB_TURNOS_UO.setModel(new DefaultTableModel());
                        String titulos[]={"Nº","Cod. Turno UO","Cod. Turno","Cod. Horarios","Cod. T Turno",
                        "Turno","Nomenclatura","Hora Inicio", "Hora Fin", "AR_ID","Total Hora","Pasar Día",
                        "Precio Normal", "Precio Sabado", "Precio Domingo","Precio Feriado"};
                        m2=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m2);
                        String fila[]=new String[16];
                        Usuario obj=new Usuario();
                        consulta="exec PERSONAL_TURNOS_UO_AR ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, txtAR_ID.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, c=1;
                        while(r.next()){
                            fila[0]=String.valueOf(c)+"º";
                            fila[1]=r.getString(1);
                            fila[2]=r.getString(2);
                            fila[3]=r.getString(3);
                            fila[4]=r.getString(4);
                            fila[5]=r.getString(5);
                            fila[6]=r.getString(6);
                            fila[7]=r.getString(7);
                            fila[8]=r.getString(8);
                            fila[9]=r.getString(9);
                            fila[10]=r.getString(10);
                            fila[11]=r.getString(11);
                            fila[12]=r.getString(12);
                            fila[13]=r.getString(13);
                            fila[14]=r.getString(14);
                            fila[15]=r.getString(15);
                            
                            m2.addRow(fila);
                            c++;
                        }
                            TB_TURNOS_UO.setModel(m2);
                            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
                            TB_TURNOS_UO.setRowSorter(elQueOrdena);
                            TB_TURNOS_UO.setModel(m2);

                            formatoPersonal_TURNOS_UO();
                      
        } catch (Exception e) {
            System.out.println("Error MOSTRAR VER DETALLE: " + e.getMessage());
        }  
    }
   
    public void GUARDAR_PERSONAL_ROL(){
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        
        try{   
            if(lblGM.getText().equalsIgnoreCase("G")){
                if(txtMedico_UO.getText().equalsIgnoreCase("") || TB_TURNOS_UO.getRowCount()==0 || 
                   D_FECHA.getDate()==null || txtLimite_Consultas_Per.getText().equalsIgnoreCase("") ||
                   txtTotal_Horas.getText().equalsIgnoreCase("") || txtTotal_Pago.getText().equalsIgnoreCase("")){
                    cargareliminar_Per.setVisible(true);
                           cargareliminar_Per.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Asegurese de completar todos los campos");
                           eli.setVisible(false);
                           noeli.setVisible(false);
//                    JOptionPane.showMessageDialog(rootPane, "Verifique que ha ingresado todos los campos");
                }
                else{

//                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//                    if(guardar == 0 ){
//                        
//                        if(CB_Envio_ROL.isSelected()){
//                            PR.setENVIO_ROL("S"); 
//                        }else{
//                            PR.setENVIO_ROL("N"); 
//                        }
                        
//                            guardar_TB_PERSONAL_ROL();
                            cargareliminar_Per.setVisible(true);
                                cargareliminar_Per.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
//                              JOptionPane.showMessageDialog(null, "Datos Guardados");    
                                Clear_TB_TURNOS_PERSONAL_ROL();
                            
//                        }
                    }
            }else{
                
                if(txtMedico_UO.getText().equalsIgnoreCase("") || TB_TURNOS_UO.getRowCount()==0 || 
                        D_FECHA.getDate()==null || txtLimite_Consultas_Per.getText().equalsIgnoreCase("") ||
                        txtTotal_Horas.getText().equalsIgnoreCase("") || txtTotal_Pago.getText().equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(rootPane, "Verifique que ha ingresado todos los campos");
                }
                else{

                    int MODIFICAR = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(MODIFICAR == 0 ){
                        
//                        if(CB_Envio_ROL.isSelected()){
//                            PR.setENVIO_ROL("S"); 
//                        }else{
//                            PR.setENVIO_ROL("N"); 
//                        }
                        
                            modificar_TB_PERSONAL_ROL();
                            
                            JOptionPane.showMessageDialog(null, "Datos Modificados");                                                         
                       
                        }
                    }
                
            }
            
        }catch(Exception e){
                 System.out.println("error guardar" + e.getMessage());
        }

    }
    
    public void guardar_TB_PERSONAL_ROL(){
     
        try {

            int filaselec=TB_TURNOS_UO.getSelectedRow();
            
            CLS_PERSONAL_ROL PR = new CLS_PERSONAL_ROL(); 
            
            if(lblGM.getText().equalsIgnoreCase("G")){
                PR.setCOD_TUR_UO(TB_TURNOS_UO.getValueAt(filaselec, 1).toString());
            }else{
                if(lblGM.getText().equalsIgnoreCase("M")){
                    PR.setCOD_TUR_UO(LBL_UNIDAD_ORGANICA_2.getText());
                }
            }
            
            PR.setID_PER_UNI_ORG(Integer.parseInt(lbl_id_per_uni_org.getText()));

                String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
                String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
                String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            PR.setDIA_ROL(DIA);
            PR.setMES_ROL(MES);
            PR.setANIO_ROL(ANIO);

            PR.setLIMITE_CONSULTA_DIA(Integer.parseInt(txtLimite_Consultas_Per.getText()));
            PR.setTOTAL_HORAS(txtTotal_Horas.getText());
            PR.setTOTAL_PAGO(Double.parseDouble(txtTotal_Pago.getText()));
            PR.setNOM_USU(lblusu.getText());
          
                PR.PERSONAL_ROL_GUARDAR();

                TXT_COD_ROL_GUARDAR.setText(PR.PERSONAL_COD_ROL());

                if(TB_ROL_ACTIVIDAD.getRowCount()!=0){
                    if(lblGM.getText().equalsIgnoreCase("G")){
                        GUARDAR_TB_ROL_ACTIVIDADES();
                    }else{
                        if(lblGM.getText().equalsIgnoreCase("M")){
                            CLS_PERSONAL_ROL_ACTIVIDADES q = new CLS_PERSONAL_ROL_ACTIVIDADES();
                            q.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                            q.eliminarActividad_modif();
                            GUARDAR_TB_ROL_ACTIVIDADES();
                        }
                    }
                

                }else{
                    JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Ingrese Alguna Actividad");
                }

        } catch (Exception e) {
            System.out.println("error guardar personal rol y actividades" + e.getMessage());
        }
    }
    
   public void guardar_TB_PERSONAL_ROL_modificar(){
     
        try {

            int filaselec=TB_TURNOS_UO.getSelectedRow();
            
            CLS_PERSONAL_ROL PRmm = new CLS_PERSONAL_ROL(); 
            
            PRmm.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
            PRmm.setCOD_TUR_UO(LBL_UNIDAD_ORGANICA_2.getText());
            
            PRmm.setID_PER_UNI_ORG(Integer.parseInt(lbl_id_per_uni_org.getText()));

                String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
                String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
                String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            PRmm.setDIA_ROL(DIA);
            PRmm.setMES_ROL(MES);
            PRmm.setANIO_ROL(ANIO);

            PRmm.setLIMITE_CONSULTA_DIA(Integer.parseInt(txtLimite_Consultas_Per.getText()));
            PRmm.setTOTAL_HORAS(txtTotal_Horas.getText());
            PRmm.setTOTAL_PAGO(Double.parseDouble(txtTotal_Pago.getText()));
            PRmm.setNOM_USU(lblusu.getText());

            PRmm.PERSONAL_ROL_MODIFICAR();

//            TXT_COD_ROL_GUARDAR.setText(PRmm.PERSONAL_COD_ROL());

                if(TB_ROL_ACTIVIDAD.getRowCount()!=0){
//                    if(lblGM.getText().equalsIgnoreCase("G")){
//                        GUARDAR_TB_ROL_ACTIVIDADES();
////                    }else{
//                        if(lblGM.getText().equalsIgnoreCase("M")){
                            CLS_PERSONAL_ROL_ACTIVIDADES q = new CLS_PERSONAL_ROL_ACTIVIDADES();
                            q.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                            q.eliminarActividad_modif();
//                            GUARDAR_TB_ROL_ACTIVIDADES();
//                            int id = Integer.parseInt(TXT_COD_ROL_GUARDAR.getText());
        
                            for(int i = 0; i < TB_ROL_ACTIVIDAD.getRowCount(); i++){      
                                  CLS_PERSONAL_ROL_ACTIVIDADES Rm=new CLS_PERSONAL_ROL_ACTIVIDADES();
//
//                                  if(lblGM.getText().equalsIgnoreCase("G")){
//                                      RA.setCOD_ROL(id);
//                                  }else{
//                                      if(lblGM.getText().equalsIgnoreCase("M")){
                                  Rm.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
//                                      }
//                                  }

                                  Rm.setCOD_UNI_ORG_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 0).toString());
                                  Rm.setHORA_INICIO_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 2).toString());
                                  Rm.setHORA_FIN_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 3).toString());
                                  Rm.setTOTAL_HORA_ACTIVIDAD(TB_ROL_ACTIVIDAD.getValueAt(i, 4).toString());
                                  Rm.setNOM_USU(lblusu.getText());
                                  Rm.setAR_ID(Integer.parseInt(TB_ROL_ACTIVIDAD.getValueAt(i, 6).toString()));

                                  Rm.PERSONAL_ROL_ACTIVIDADES_GUARDAR();
                               } 
//                        }
//                    }
                
                }

        } catch (Exception e) {
            System.out.println("error guardar personal rol y actividades modif " + e.getMessage());
        }
    }
    
    public void GUARDAR_TB_ROL_ACTIVIDADES(){
         int id = Integer.parseInt(TXT_COD_ROL_GUARDAR.getText());
        
         for(int i = 0; i < TB_ROL_ACTIVIDAD.getRowCount(); i++){      
               CLS_PERSONAL_ROL_ACTIVIDADES RA=new CLS_PERSONAL_ROL_ACTIVIDADES();
               
               if(lblGM.getText().equalsIgnoreCase("G")){
                   RA.setCOD_ROL(id);
               }else{
                   if(lblGM.getText().equalsIgnoreCase("M")){
                       RA.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                   }
               }
               
               RA.setCOD_UNI_ORG_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 0).toString());
               RA.setHORA_INICIO_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 2).toString());
               RA.setHORA_FIN_ACTI(TB_ROL_ACTIVIDAD.getValueAt(i, 3).toString());
               RA.setTOTAL_HORA_ACTIVIDAD(TB_ROL_ACTIVIDAD.getValueAt(i, 4).toString());
               RA.setNOM_USU(lblusu.getText());
               RA.setAR_ID(Integer.parseInt(TB_ROL_ACTIVIDAD.getValueAt(i, 6).toString()));
                              
               RA.PERSONAL_ROL_ACTIVIDADES_GUARDAR();
               
               btnAgregar_ROL.setEnabled(true);
            } 
    }
    
    public void modificar_TB_PERSONAL_ROL(){
        
            int filaselec=TB_TURNOS_UO.getSelectedRow();
            
            CLS_PERSONAL_ROL PRM=new CLS_PERSONAL_ROL();
            PRM.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
            PRM.setCOD_TUR_UO(TB_TURNOS_UO.getValueAt(filaselec, 1).toString());
            
            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());
            
            PRM.setDIA_ROL(DIA);
            PRM.setMES_ROL(MES);
            PRM.setANIO_ROL(ANIO);
            PRM.setLIMITE_CONSULTA_DIA(Integer.parseInt(txtLimite_Consultas_Per.getText()));
            PRM.setTOTAL_HORAS(txtTotal_Horas.getText());
            PRM.setTOTAL_PAGO(Double.parseDouble(txtTotal_Pago.getText()));
            PRM.setNOM_USU(lblusu.getText());
            
            PRM.PERSONAL_ROL_MODIFICAR();
           
    }
    
    private void Clear_Tb_TURNOS_UO(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_TURNOS_UO.getModel(); 
        int b=TB_TURNOS_UO.getRowCount();
        for(int j=0;j<b;j++){
                modelo1.removeRow(0);
        }
   }
    
   private void Clear_TB_TURNOS_PERSONAL_ROL(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_TURNOS_PERSONAL_ROL.getModel(); 
        int b=TB_TURNOS_PERSONAL_ROL.getRowCount();
        for(int j=0;j<b;j++){
                modelo1.removeRow(0);
        }
   } 
   
   private void Clear_TB_ROL_ACTIVIDAD(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_ROL_ACTIVIDAD.getModel(); 
        int b=TB_ROL_ACTIVIDAD.getRowCount();
        for(int j=0;j<b;j++){
                modelo1.removeRow(0);
        }
   } 
   
   private void Clear_TB_ACTIVIDADES(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_ACTIVIDADES_LISTA.getModel(); 
        int b=TB_ACTIVIDADES_LISTA.getRowCount();
        for(int j=0;j<b;j++){
                modelo1.removeRow(0);
        }
   } 
   
   public void Agregar_Personal_ROL(){
     
        try {

        DefaultTableModel modelo=(DefaultTableModel) TB_TURNOS_PERSONAL_ROL.getModel(); 
 
        Object [] fila = new Object[11]; 

        if(lblGM.getText().equalsIgnoreCase("G")){
            
        if(D_FECHA.getDate()==null || txtLimite_Consultas_Per.getText().equalsIgnoreCase("") || 
                txtTotal_Horas.getText().equalsIgnoreCase("") || txtTotal_Pago.getText().equalsIgnoreCase("")){
            
//            JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
//            ERROR_BUSCAR_MED.setVisible(true);
                           cargareliminar_Per.setVisible(true);
                           cargareliminar_Per.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Asegurese de completar todos los campos");
                           eli.setVisible(false);
                           noeli.setVisible(false);
            
        }else{
            cargareliminar_Per.setVisible(false);
            int filaselec=TB_TURNOS_UO.getSelectedRow();
            int c =1;
            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            
            fila[0]=TB_TURNOS_UO.getValueAt(filaselec, 1).toString();
            fila[1]=lbl_id_per_uni_org.getText();
            fila[2]=TB_TURNOS_UO.getValueAt(filaselec, 6).toString();
            fila[3]=DIA; 
            fila[4]=MES; 
            fila[5]=ANIO; 
            fila[6]=txtLimite_Consultas_Per.getText(); 
            fila[7]=txtTotal_Horas.getText(); 
            fila[8]=txtTotal_Pago.getText();
            fila[9]=txtMedico_UO.getText();
            
            modelo.addRow(fila); 
            TB_TURNOS_PERSONAL_ROL.setModel(modelo);
            
            btnAgregar_ROL.setEnabled(false);
            
            TB_TURNOS_UO.setEnabled(false);
            TB_TURNOS_UO.setBackground(Color.lightGray);
//            D_FECHA.setDate(null);
//            txtLimite_Consultas_Per.setText("");
//            txtTotal_Horas.setText("");
//            txtTotal_Pago.setText("");
        }
        } 
        if(lblGM.getText().equalsIgnoreCase("M")){
           
            int filaselec=TB_TURNOS_UO.getSelectedRow();
            int c =1;
            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            
            fila[0]=TB_TURNOS_UO.getValueAt(filaselec, 1).toString();
            fila[1]=lbl_id_per_uni_org.getText();
            fila[2]=TB_TURNOS_UO.getValueAt(filaselec, 6).toString();
            fila[3]=DIA; 
            fila[4]=MES; 
            fila[5]=ANIO; 
            fila[6]=txtLimite_Consultas_Per.getText(); 
            fila[7]=txtTotal_Horas.getText(); 
            fila[8]=txtTotal_Pago.getText();
            fila[9]=txtMedico_UO.getText();
            fila[10]=txtCOD_ROL.getText();
            
            modelo.addRow(fila); 
            TB_TURNOS_PERSONAL_ROL.setModel(modelo);
         
        }

        } catch (Exception e) {
            System.out.println("error cargar PR catch" + e.getMessage());
        }
 
    }
   

   
//   public boolean repiteDetalleServicioB(){
//         
//         boolean c=false;
//         for (int i = 0; i < TB_TURNOS_PERSONAL_ROL.getRowCount(); i++){    
//               if(txtServiciosVariosB.getText().equalsIgnoreCase(tablaServiciosBasicosEA.getValueAt(i, 3).toString())){
//                    c=true;
//	}}
//               return c;
//    }
    
   public void Mostrar_PERSONAL_ROL(){
        try {
                     
            String consulta="";
            
            TB_PERSONAL_ROL.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Rol","ID_PER_UNI_ORG","Cod. Turno UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Turno",
            "Día", "Mes", "Año", "Lim. Consulta", "Total Hora", "Total Pago", "Cargo", "Servicio", "Area","AR_ID"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[19];
            Usuario obj=new Usuario();
            consulta="exec PERSONAL_PERSONAL_LISTAR_ROL";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            
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
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16);
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);
                
                m3.addRow(fila);
                c++;
 
            }
            TB_PERSONAL_ROL.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            TB_PERSONAL_ROL.setRowSorter(elQueOrdena);
            TB_PERSONAL_ROL.setModel(m3);
                       
            formatoPersonal_ROL_LISTA();
            
        } catch (Exception e) {
            System.out.println("Error mostrar personal_rol: " + e.getMessage());
        }     
    }
   
    public void formatoPersonal_ROL_LISTA(){        
            TB_PERSONAL_ROL.getColumnModel().getColumn(0).setPreferredWidth(40);
            TB_PERSONAL_ROL.getColumnModel().getColumn(1).setPreferredWidth(150); 
            TB_PERSONAL_ROL.getColumnModel().getColumn(2).setPreferredWidth(150);
            TB_PERSONAL_ROL.getColumnModel().getColumn(3).setPreferredWidth(150);
            TB_PERSONAL_ROL.getColumnModel().getColumn(4).setPreferredWidth(100);                
            TB_PERSONAL_ROL.getColumnModel().getColumn(5).setPreferredWidth(150); 
            TB_PERSONAL_ROL.getColumnModel().getColumn(6).setPreferredWidth(150);
            TB_PERSONAL_ROL.getColumnModel().getColumn(7).setPreferredWidth(220); 
            TB_PERSONAL_ROL.getColumnModel().getColumn(8).setPreferredWidth(60);
            TB_PERSONAL_ROL.getColumnModel().getColumn(9).setPreferredWidth(60);
            TB_PERSONAL_ROL.getColumnModel().getColumn(10).setPreferredWidth(60);
            TB_PERSONAL_ROL.getColumnModel().getColumn(11).setPreferredWidth(60);
            TB_PERSONAL_ROL.getColumnModel().getColumn(12).setPreferredWidth(90);
            TB_PERSONAL_ROL.getColumnModel().getColumn(13).setPreferredWidth(90);
            TB_PERSONAL_ROL.getColumnModel().getColumn(14).setPreferredWidth(90);
            TB_PERSONAL_ROL.getColumnModel().getColumn(15).setPreferredWidth(180);
            TB_PERSONAL_ROL.getColumnModel().getColumn(16).setPreferredWidth(180);
            TB_PERSONAL_ROL.getColumnModel().getColumn(17).setPreferredWidth(180);
            TB_PERSONAL_ROL.getColumnModel().getColumn(18).setPreferredWidth(100);
//          TB_PERSONAL_ROL.getColumnModel().getColumn(19).setPreferredWidth(100);
            //Ocultar
            TB_PERSONAL_ROL.getColumnModel().getColumn(1).setMinWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(1).setMaxWidth(0);    
            TB_PERSONAL_ROL.getColumnModel().getColumn(2).setMinWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(2).setMaxWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(3).setMinWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(3).setMaxWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(18).setMinWidth(0);
            TB_PERSONAL_ROL.getColumnModel().getColumn(18).setMaxWidth(0);
    
    }
   
    public void BUSCAR_PERSONAL_ROL_FECHA(){
    
            String DIAI = new SimpleDateFormat("dd").format(D_FECHA_INICIO.getDate());
            String MESI = new SimpleDateFormat("MM").format(D_FECHA_INICIO.getDate());
            String ANIOI = new SimpleDateFormat("yyy").format(D_FECHA_INICIO.getDate());
                                 
            
            String DIAF = new SimpleDateFormat("dd").format(D_FECHA_FIN.getDate());
            String MESF = new SimpleDateFormat("MM").format(D_FECHA_FIN.getDate());
            String ANIOF = new SimpleDateFormat("yyy").format(D_FECHA_FIN.getDate());
              
            String fechaI = ANIOI + MESI + DIAI;
            String fechaF = ANIOF + MESF + DIAF;
           
            String buscar="";
            buscar = txtBuscarMedico_ROL.getText();

        
        String consulta="";
        
        try {
       
            TB_PERSONAL_ROL.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Rol","ID_PER_UNI_ORG","Cod. Turno UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Turno",
            "Día", "Mes", "Año", "Lim. Consulta", "Total Hora", "Total Pago", "Cargo", "Servicio", "Area","AR_ID"};
            m4=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m4);
            String fila[]=new String[19];

            CLS_PERSONAL_ROL obj=new CLS_PERSONAL_ROL();
            consulta="exec PERSONAL_PERSONAL_BUSCAR_FECHA_LISTA_ROL ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,fechaI);
            cmd.setString(2, fechaF);
            cmd.setString(3, buscar);
//            cmd.setString(4, servicioArea);
            
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
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16);
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);
                
                m4.addRow(fila);
                c++;
            }
            TB_PERSONAL_ROL.setModel(m4);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m4);
            TB_PERSONAL_ROL.setRowSorter(elQueOrdena);
            this.TB_PERSONAL_ROL.setModel(m4);
            
            formatoPersonal_ROL_LISTA();
            
        } catch (Exception e) {
            System.out.println("Error buscar personal rol: " + e.getMessage());
        }
    }
    
    public void BUSCAR_PERSONAL_ROL(){
           
            String buscar="";
            buscar = txtBuscarMedico_ROL.getText();

        String consulta="";
        
        try {
       
            TB_PERSONAL_ROL.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Rol","ID_PER_UNI_ORG","Cod. Turno UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Turno",
            "Día", "Mes", "Año", "Lim. Consulta", "Total Hora", "Total Pago", "Cargo", "Servicio", "Area","AR_ID"};
            m5=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m5);
            String fila[]=new String[19];

            CLS_PERSONAL_ROL obj=new CLS_PERSONAL_ROL();
            consulta="exec PERSONAL_PERSONAL_BUSCAR_LISTA_ROL ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
//            cmd.setString(4, servicioArea);
            
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
                fila[8]=r.getString(8);
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16);
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);
                
                m5.addRow(fila);
                c++;
            }
            TB_PERSONAL_ROL.setModel(m5);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m5);
            TB_PERSONAL_ROL.setRowSorter(elQueOrdena);
            this.TB_PERSONAL_ROL.setModel(m5);
            
            formatoPersonal_ROL_LISTA();
            
        } catch (Exception e) {
            System.out.println("Error buscar personal rol: " + e.getMessage());
        }
    }
    
    public void cargar_personal_rol(){
    try {
            ImageIcon im=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
            
            modelo1 = (DefaultTableModel) TB_PERSONAL_ROL.getModel();
//            limpiarTabla();
            
            if(TB_PERSONAL_ROL.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No hay registros que cargar");
            }else{
            
            //pasar datos de una tabla a otra
            int filaselec=TB_PERSONAL_ROL.getSelectedRow();
            int cargar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea CARGAR el rol del Médico \n " +
                    TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
                                 TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString(),
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,im);
            
//            String nom = TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
//                                 TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
//                                 TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString();
            if(cargar == 0 ){
                
                    
                
                for (int i=0;i<modelo1.getRowCount(); i++){
                    String nom2 = TB_PERSONAL_ROL.getValueAt(i, 5).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(i, 6).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(i, 7).toString();
                    if(txtNOMBRE.getText().equalsIgnoreCase(nom2)){
                        
                        String  cod_tur_uo, id_per_uni_org ,nomenclatura,Dia, Mes, Anio, Lim_Consulta, Total_Hora,
                                 Nombres, cod_rol;
                        double Total_Pago;

                        cod_tur_uo = TB_PERSONAL_ROL.getValueAt(i, 3).toString();
                        id_per_uni_org = TB_PERSONAL_ROL.getValueAt(i, 2).toString();
                        nomenclatura = TB_PERSONAL_ROL.getValueAt(i, 8).toString();
                        Dia = TB_PERSONAL_ROL.getValueAt(i, 9).toString();           
                        Mes = TB_PERSONAL_ROL.getValueAt(i, 10).toString();
                        Anio = TB_PERSONAL_ROL.getValueAt(i, 11).toString();
                        Lim_Consulta = TB_PERSONAL_ROL.getValueAt(i, 12).toString();
                        Total_Hora = TB_PERSONAL_ROL.getValueAt(i, 13).toString();
                        Total_Pago = Double.parseDouble(TB_PERSONAL_ROL.getValueAt(i, 14).toString());
                        
                        double pago = (Total_Pago);
                        String pagot = "";
                
                        BigDecimal bd2 = new BigDecimal(pago);

                        bd2 = bd2.setScale(0, BigDecimal.ROUND_HALF_UP);
                        
                        pagot = bd2.toString();
                        
                        Nombres = TB_PERSONAL_ROL.getValueAt(i, 5).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(i, 6).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(i, 7).toString();
                        cod_rol = TB_PERSONAL_ROL.getValueAt(i, 1).toString();

                        //Cargar los datos a la otra tabla 
                        modelo2 = (DefaultTableModel) TB_TURNOS_PERSONAL_ROL.getModel();

                        String filaelemento[] = {cod_tur_uo,id_per_uni_org, nomenclatura,Dia, Mes, Anio, Lim_Consulta, 
                                                 Total_Hora, pagot, Nombres,cod_rol};

                        modelo2.addRow(filaelemento);


                        txtMedico_UO.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 5).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(filaselec, 6).toString() + " " + 
                                             TB_PERSONAL_ROL.getValueAt(filaselec, 7).toString());
                        lbl_id_per_uni_org.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 2).toString());
                        txtAR_ID.setText(TB_PERSONAL_ROL.getValueAt(filaselec, 18).toString());


                        lblListado_Edicion.setText("Edición");
                        jTabbedPane1.setSelectedIndex(0);
                }
                }
            }
            
            jScrollPane3.setVisible(false);
            lbl_turnos_disponibles.setVisible(false);
            
        }    
        } catch (Exception e) {
            System.out.println("error cargar personal_rol - personal" + e.getMessage());
        }
    }
    
    public void cargar_tb_personal_rol_tb_UO(){
         try{
                        if( TB_TURNOS_PERSONAL_ROL.getRowCount()>0){
                            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
                            
                            String UO = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString();
                            txtUO_3.setText(UO);
                            
                            for (int i = 0; i < TB_TURNOS_UO.getRowCount(); i++){    

                                if(txtUO_3.getText().equalsIgnoreCase(TB_TURNOS_UO.getValueAt(i, 1).toString())){
                                
                                    TB_TURNOS_UO.getSelectionModel().setSelectionInterval(i,i);
                                    lblCOD_UO_1.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 0).toString());
                                    
//                                    TXT_UO_TB_TURNOS_UO.setText(String.valueOf(TB_TURNOS_UO.getValueAt(i, 1)));
                                  
                                }
                            }
                            
                            String dia = (String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 3)+
                                    "-"+(String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 4) +
                                    "-"+(String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 5);
//                                                      
                            DateFormat dfo = new SimpleDateFormat("dd-MM-yyyy");
                            
                            Date d = dfo.parse(dia);
                            
                            D_FECHA.setDate(d);
                            txtLimite_Consultas_Per.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 6).toString());
                            
                            String total1 = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 7).toString();
           
                            String d1 = total1.substring(0, 2);
            
                            txtTotal_Horas.setText(d1);
                            
                            txtTotal_Pago.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 8).toString());
                            txtCOD_ROL.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString());
                            
                            
                        }
                        
        }catch(Exception e){
             System.out.println("cargar " + e.getMessage());
        }
    }
    
//    public void cargar_tb_ROL_ACTIVIDAD_tb_ACTIVIDAD(){ FALTAAAAAAA
//         try{
//                        if( TB_ROL_ACTIVIDAD.getRowCount()>0){
//                            int filaselec=TB_ROL_ACTIVIDAD.getSelectedRow();
//                            
//                            String ACT = TB_ROL_ACTIVIDAD.getValueAt(filaselec, 0).toString();
//                            txtUO.setText(ACT);J
//                            
//                            for (int i = 0; i < TB_TURNOS_UO.getRowCount(); i++){    
//
//                                if(txtUO.getText().equalsIgnoreCase(TB_TURNOS_UO.getValueAt(i, 1).toString())){
//                                
//                                    TB_TURNOS_UO.getSelectionModel().setSelectionInterval(i,i);
//                                  
//                                }
//                            }
//                            
//                            String dia = (String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 3)+
//                                    "-"+(String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 4) +
//                                    "-"+(String) TB_TURNOS_PERSONAL_ROL.getModel().getValueAt(filaselec, 5);
////                                                      
//                            DateFormat dfo = new SimpleDateFormat("dd-MM-yyyy");
//                            
//                            Date d = dfo.parse(dia);
//                            
//                            D_FECHA.setDate(d);
//                            txtLimite_Consultas_Per.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 6).toString());
//                            txtTotal_Horas.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 7).toString());
//                            txtTotal_Pago.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 8).toString());
//                            txtCOD_ROL.setText(TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString());
//                            
//                            
//                        }
//                        
//        }catch(Exception e){
//             System.out.println("cargar " + e.getMessage());
//        }
//    }
    
    
    public DefaultComboBoxModel HORA(){
        DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
        
        String consulta="";
        
        String   sql = null;
        ResultSet rs = null;
        Statement  st = null;   
        try {
            
//            
//            Usuario obj=new Usuario();
//            consulta="PERSONAL_PERSONAL_HORA ?,?";
//            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
//            cmd.setString(1, TXT_HORA_INICIO.getText());
//            cmd.setString(2, TXT_HORA_FIN.getText());
//            ResultSet r= cmd.executeQuery();
//            
//            
              st = con.createStatement();
              r = st.executeQuery ("select * from PERSONAL_HORA");       
                    listmodel.addElement( "Seleccionar..." );     
            while( r.next() ){
                listmodel.addElement(r.getString("hora_completa"));                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta HOra:" + ex.getMessage() );
        }        
        return listmodel;
    }
    
    public void mostrar_ACTIVIDADES(){
        try {                   
                        //detalle
                        String consulta="";
                        TB_ACTIVIDADES_LISTA.setModel(new DefaultTableModel());
                        String titulos[]={"Nº","Cod_uni_org_acti","cod_uni_organica_jerar","Nombre de la Actividad","AR_ID"};
                        m6=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m6);
                        String fila[]=new String[5];
                        Usuario obj=new Usuario();
                        consulta="exec PERSONAL_PERSONAL_ACTIVIDADES_LISTAR ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, txtAR_ID.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, c=1;
                        while(r.next()){
                            fila[0]=String.valueOf(c)+"º";
                            fila[1]=r.getString(1);
                            fila[2]=r.getString(2);
                            fila[3]=r.getString(3);
                            fila[4]=r.getString(4);
                            
                            
                            m6.addRow(fila);
                            c++;
                        }
                            TB_ACTIVIDADES_LISTA.setModel(m6);
                            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m6);
                            TB_ACTIVIDADES_LISTA.setRowSorter(elQueOrdena);
                            TB_ACTIVIDADES_LISTA.setModel(m6);

                            formato_ACTIVIDADES();
                      
        } catch (Exception e) {
            System.out.println("Error MOSTRAR ACTIVIDADES: " + e.getMessage());
        }  
    }
    
    public void Buscar_ACTIVIDADES(){
        try {
                     
            String consulta="";
            
            TB_ACTIVIDADES_LISTA.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod_uni_org_acti","cod_uni_organica_jerar","Nombre de la Actividad","AR_ID"};
            m7=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m7);
            String fila[]=new String[5];
            Usuario obj=new Usuario();
            consulta="exec PERSONAL_PERSONAL_ACTIVIDADES_LISTAR_BUSCAR ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);  
            cmd.setInt(1, Integer.parseInt(txtAR_ID.getText()));
            cmd.setString(2, txt_ACTIVIDAD.getText());
            
            ResultSet r= cmd.executeQuery();
            int c = 1;
            while(r.next()){
                          
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                
                m7.addRow(fila);
                c++;
            }
            TB_ACTIVIDADES_LISTA.setModel(m7);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m7);
            TB_ACTIVIDADES_LISTA.setRowSorter(elQueOrdena);
            TB_ACTIVIDADES_LISTA.setModel(m7);
                       
            formato_ACTIVIDADES();
        
            
        }catch (Exception e) {
            System.out.println("Error mostrar actividades: " + e.getMessage());
        }
        
    }
    
    public void Agregar_ROL_ACTIVIDAD(){
     
        try {

        DefaultTableModel modelo=(DefaultTableModel) TB_ROL_ACTIVIDAD.getModel(); 
 
        Object [] fila=new Object[11]; 

        
        if(cb_HORA_FIN.getSelectedIndex()==0 || 
                txt_ACTIVIDAD.getText().equalsIgnoreCase("") ){
            
                JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Seleccione Hora de Fin \n de la Actividad");
  
        }else{
            
                String hm = LBL_TOTAL_HORA.getText();
                String hm2 = txtHoras_Libres.getText();

                int a = Integer.parseInt(hm.substring(0, 2));
                int b = Integer.parseInt(hm.substring(3, 5));
                int c = Integer.parseInt(hm.substring(6, 8));

                int a1 = Integer.parseInt(hm2.substring(0, 2));
                int b1 = Integer.parseInt(hm2.substring(3, 5));
                int c1 = Integer.parseInt(hm2.substring(6, 8));

                int hora =0, minuto=0, segundo =0;
        
                if((a > a1 && b > b1) || (a > a1 && b <= b1) || (a == a1 && b > b1)){
                        JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "La Cantidad de Horas es Mayor a \n "
                                + "la Cantidad de Horas Disponibles");
                        
                }else{
                    
//            cargareliminar_Per.setVisible(false);
//            int filaselec=TB_TURNOS_UO.getSelectedRow();
//            int c =1;
//            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
//            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
//            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            
                    fila[0]=txt_COD_UNI_ORG_ACTIVIDADES.getText();
                    fila[1]=txt_ACTIVIDAD.getText();
                    fila[2]=this.cb_HORA_INICIO.getSelectedItem().toString();
                    fila[3]=this.cb_HORA_FIN.getSelectedItem().toString();
                    fila[4]=LBL_TOTAL_HORA.getText();
                    fila[5]=txt_COD_UNI_ORGANICA_JERAR.getText();
                    fila[6]=TXT_AR_ID_ACTI.getText();

                    modelo.addRow(fila); 
                    TB_ROL_ACTIVIDAD.setModel(modelo);
                    
                    CANTIDAD_HORAS_LIBRES_RESTA();
                }
            
        }
        } catch (Exception e) {
            System.out.println("error cargar ROL ACTIVIDAD" + e.getMessage());
        }
 
    }
    
    public void mostrar_ROL_ACTIVIDADES(){
        try {                   
                        //detalle
                        String consulta="";
                        TB_ROL_ACTIVIDAD.setModel(new DefaultTableModel());
                        String titulos[]={"Cod_uni_org_acti","Actividad","Hora de Inicio","Hora de Fin","Total de Horas",
                        "Cod_uni_organica_jerar","AR_ID","COD_ROL_ACT"};
                        m8=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m8);
                        String fila[]=new String[8];
                        Usuario obj=new Usuario();
                        consulta="exec PERSONAL_LISTAR_ACTIVIDAD ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, txtCOD_ROL.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, c=1;
                        while(r.next()){
                            fila[0]=r.getString(1);
                            fila[1]=r.getString(2);
                            fila[2]=r.getString(3);
                            fila[3]=r.getString(4);
                            fila[4]=r.getString(5);
                            fila[5]=r.getString(6);
                            fila[6]=r.getString(7);
                            fila[7]=r.getString(8);
                            
                            m8.addRow(fila);
                            c++;
                        }
                            TB_ROL_ACTIVIDAD.setModel(m8);
                            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m8);
                            TB_ROL_ACTIVIDAD.setRowSorter(elQueOrdena);
                            TB_ROL_ACTIVIDAD.setModel(m8);

                            formato_ROL_ACTIVIDADES();
                      
        } catch (Exception e) {
            System.out.println("Error MOSTRAR ROL ACTIVIDADES: " + e.getMessage());
        }  
    }
    
    public void mostrar_ROL_ACTIVIDADES_GUARDAR(){
        try {                   
                        //detalle
                        String consulta="";
//                        int filaselec = TB_TURNOS_PERSONAL_ROL.getSelectedRow();
//                        String cod = TB_TURNOS_PERSONAL_ROL.getValueAt(filaselec, 10).toString();
//                        String cod_rol;
//                        if(cod.length()<= 0){
//                            cod_rol = "";
//                        }else{
//                            cod_rol = cod;
//                        }
//                        
                        TB_ROL_ACTIVIDAD.setModel(new DefaultTableModel());
                        String titulos[]={"Cod_uni_org_acti","Actividad","Hora de Inicio","Hora de Fin","Total de Horas",
                        "Cod_uni_organica_jerar","AR_ID","COD_ROL_ACT"};
                        m9=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m9);
                        String fila[]=new String[8];
                        Usuario obj=new Usuario();
                        consulta="exec PERSONAL_LISTAR_ACTIVIDAD ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, TXT_COD_ROL_GUARDAR.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, c=1;
                        while(r.next()){
                            fila[0]=r.getString(1);
                            fila[1]=r.getString(2);
                            fila[2]=r.getString(3);
                            fila[3]=r.getString(4);
                            fila[4]=r.getString(5);
                            fila[5]=r.getString(6);
                            fila[6]=r.getString(7);
                            fila[7]=r.getString(8);
                            
                            m9.addRow(fila);
                            c++;
                        }
                            TB_ROL_ACTIVIDAD.setModel(m9);
                            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m9);
                            TB_ROL_ACTIVIDAD.setRowSorter(elQueOrdena);
                            TB_ROL_ACTIVIDAD.setModel(m9);

                            formato_ROL_ACTIVIDADES();
                      
        } catch (Exception e) {
            System.out.println("Error MOSTRAR ROL ACTIVIDADES guardar: " + e.getMessage());
        }  
    }
    
    public void mostrar_HORAS(String COD_TUR_UO){
        String consulta="";
        try {
            consulta="EXEC PERSONAL_TURNOS_UO_AR_HORA ?";
            PreparedStatement cmd = PR.getCn().prepareStatement(consulta);
            cmd.setString(1, COD_TUR_UO);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                TXT_HORA_INICIO.setText(r.getString(1));
                TXT_HORA_FIN.setText(r.getString(2));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga HORA INICIO Y FIN: " + e.getMessage());
        }
    }
    
    public void agregar_rol_act_1(){
           Object [] fila=new Object[11]; 
           DefaultTableModel modelo=(DefaultTableModel) TB_TURNOS_PERSONAL_ROL.getModel();
           int filaselec=TB_TURNOS_UO.getSelectedRow();
            int c =1;
            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());

            
            fila[0]=TB_TURNOS_UO.getValueAt(filaselec, 1).toString();
            fila[1]=lbl_id_per_uni_org.getText();
            fila[2]=TB_TURNOS_UO.getValueAt(filaselec, 6).toString();
            fila[3]=DIA; 
            fila[4]=MES; 
            fila[5]=ANIO; 
            fila[6]=txtLimite_Consultas_Per.getText(); 
            fila[7]=txtTotal_Horas.getText(); 
            fila[8]=txtTotal_Pago.getText();
            fila[9]=txtMedico_UO.getText();
            fila[10]=TXT_COD_ROL_GUARDAR.getText();
            
            modelo.addRow(fila); 
            TB_TURNOS_PERSONAL_ROL.setModel(modelo);
                                            
                                            
                                            
    }
    
    public void PERSONAL_ROL_ELIMINAR_ACTIVIDAD(){
            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
            if( filaselec>=0){
                
                    CLS_PERSONAL_ROL PE=new CLS_PERSONAL_ROL();
                    CLS_PERSONAL_ROL_ACTIVIDADES PR1 = new CLS_PERSONAL_ROL_ACTIVIDADES();
                    PE.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                    PR1.setCOD_ROL(Integer.parseInt(txtCOD_ROL.getText()));
                    
                    PE.PERSONAL_ROL_ELIMINAR();
                    PR1.PERSONAL_ROL_ACTIVIDADES_ELIMINAR();
                                           
            }
    }
    
    //cerrar
    public void cerrar_dialogo(){
        try {
            ROL_ACTIVIDAD.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    Clear_TB_ROL_ACTIVIDAD();
                }
            });
            ROL_ACTIVIDAD.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void restar_horas(){
        
        String h = cb_HORA_INICIO.getSelectedItem().toString();
        String h2 = cb_HORA_FIN.getSelectedItem().toString();
        
        int a = Integer.parseInt(h.substring(0, 2));
        int b = Integer.parseInt(h.substring(3, 5));
        int c = Integer.parseInt(h.substring(6, 8));
        
        int a1 = Integer.parseInt(h2.substring(0, 2));
        int b1 = Integer.parseInt(h2.substring(3, 5));
        int c1 = Integer.parseInt(h2.substring(6, 8));
        
        int hora =0, minuto=0, segundo =0;
        
        if(a < a1 && b < b1){
            hora = a1 - a;
            minuto = b1 - b;
        }
        
        if(a == a1 && b < b1){
            hora = 00;
            minuto = b1 - b;
        }
        
        if(a < a1 && b == b1){
            hora = a1 - a;
            minuto = 00;
        }
        
        if(a < a1 && b > b1){
            hora = (a1 - a) - 1;
            int m=0;
            m = 60 - b;
            minuto = m + b1;
        }
        
        
        
        if(a1==00 && b1==00 && a!=00 && b!=00){
            hora = (24 - a) - 1;
            minuto = 60 - b;
        }
        
        if(a1==00 && b1==00 && a!=00 && b==00){
            hora = (24 - a);
            minuto = 00;
        }
        
        if(a > a1 && b!=00){
            int q=0,w=0,e=0,r=0;
            q = (24 - a) - 1;
            w =  60 - b;
            
            e = a1;
            r = b1;
            
            hora = q + e;
            minuto = w + r;
            
            if(minuto >= 60){
                hora = hora + 1;
                minuto = minuto - 60;
            }            
        }
        
        if(a > a1 && b==00 ){
            int d=0, f=0, g=0, j=0;
            d = (24 - a);
            f = 00;
            
            g = a1;
            j = b1;
            
            hora = d + g;
            minuto = f + j;
            
            if(minuto >= 60){
                hora = hora + 1;
                minuto = minuto - 60;
            }  
        }
        
        String horaf = "", minutof ="";
        
        if(hora < 10){
            horaf = ("0" + hora);
        }else{
            horaf = String.valueOf(hora);
        }
        
        if(minuto < 10){
            minutof = ("0" + minuto);
        }else{
            minutof = String.valueOf(minuto);
        }
                
        LBL_TOTAL_HORA.setText(horaf + ":" + minutof + ":" + segundo + "0");
//        System.out.println("hora: " + hora + " " + minuto + " " + segundo);
    }
    
    public void CANTIDAD_HORAS_TOTAL(){
//        if(LBL_TOTAL_HORA.getText().equalsIgnoreCase("N")){
            String C = TXT_HORA_INICIO.getText();
            String C1 = TXT_HORA_FIN.getText();

            int a = Integer.parseInt(C.substring(0, 2));
            int b = Integer.parseInt(C.substring(3, 5));
            int c = Integer.parseInt(C.substring(6, 8));

            int a1 = Integer.parseInt(C1.substring(0, 2));
            int b1 = Integer.parseInt(C1.substring(3, 5));
            int c1 = Integer.parseInt(C1.substring(6, 8));

            int hora =0, minuto=0, segundo =0;

            if(a < a1 && b < b1){
                hora = a1 - a;
                minuto = b1 - b;
            }

            if(a == a1 && b < b1){
                hora = 00;
                minuto = b1 - b;
            }

            if(a < a1 && b == b1){
                hora = a1 - a;
                minuto = 00;
            }

            if(a < a1 && b > b1){
                hora = (a1 - a) - 1;
                int m=0;
                m = 60 - b;
                minuto = m + b1;
            }

            String horaf = "", minutof ="";

            if(hora < 10){
                horaf = ("0" + hora);
            }else{
                horaf = String.valueOf(hora);
            }

            if(minuto < 10){
                minutof = ("0" + minuto);
            }else{
                minutof = String.valueOf(minuto);
            }

            LBL_CANTIDAD_HORAS_TOTAL.setText(horaf + ":" + minutof + ":" + segundo + "0");
//        }else{
//            if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
//                
//            }
//        }
        
//        System.out.println("hora: " + hora + " " + minuto + " " + segundo);
    }

        public void CANTIDAD_HORAS_TOTAL_QUITAR_ACT(){
//        if(LBL_TOTAL_HORA.getText().equalsIgnoreCase("N")){
            String C = TXT_HORA_INICIO.getText();
            String C1 = TXT_HORA_FIN.getText();

            int a = Integer.parseInt(C.substring(0, 2));
            int b = Integer.parseInt(C.substring(3, 5));
            int c = Integer.parseInt(C.substring(6, 8));

            int a1 = Integer.parseInt(C1.substring(0, 2));
            int b1 = Integer.parseInt(C1.substring(3, 5));
            int c1 = Integer.parseInt(C1.substring(6, 8));

            int hora =0, minuto=0, segundo =0;

            if(a < a1 && b < b1){
                hora = a1 - a;
                minuto = b1 - b;
            }

            if(a == a1 && b < b1){
                hora = 00;
                minuto = b1 - b;
            }

            if(a < a1 && b == b1){
                hora = a1 - a;
                minuto = 00;
            }

            if(a < a1 && b > b1){
                hora = (a1 - a) - 1;
                int m=0;
                m = 60 - b;
                minuto = m + b1;
            }

            String horaf = "", minutof ="";

            if(hora < 10){
                horaf = ("0" + hora);
            }else{
                horaf = String.valueOf(hora);
            }

            if(minuto < 10){
                minutof = ("0" + minuto);
            }else{
                minutof = String.valueOf(minuto);
            }

            txtHoras_Libres.setText(horaf + ":" + minutof + ":" + segundo + "0");
            LBL_CANTIDAD_HORAS_TOTAL.setText(horaf + ":" + minutof + ":" + segundo + "0");
//        }else{
//            if(LBL_PASAR_DIA.getText().equalsIgnoreCase("S")){
//                
//            }
//        }
        
//        System.out.println("hora: " + hora + " " + minuto + " " + segundo);
    }
    
    
    public void btn_guardar_actividad_boton(){
        if(txtHoras_Libres.getText().equalsIgnoreCase("00:00:00")){
            btnguardar_ACTIVIDAD_ROL.setEnabled(true);
            txt_ACTIVIDAD.setEditable(false);
            cb_HORA_INICIO.setEnabled(false);
            cb_HORA_FIN.setEnabled(false);
            LBL_TOTAL_HORA.setText("00:00:00");
            btn_AGREGAR_ACTIVIDADES.setEnabled(false);
            TB_ACTIVIDADES_LISTA.setEnabled(false);
            TB_ACTIVIDADES_LISTA.setBackground(Color.lightGray);
        }else{
            btnguardar_ACTIVIDAD_ROL.setEnabled(false);
        }
    }
    
    public void SUMAR_HORAS(){
        String h = LBL_HORA_TOTAL_QUITAR.getText();
        String h2 = LBL_HORAS_LIBRES_SUMA.getText();
        
        int a = Integer.parseInt(h.substring(0, 2));
        int b = Integer.parseInt(h.substring(3, 5));
        int c = Integer.parseInt(h.substring(6, 8));
        
        int a1 = Integer.parseInt(h2.substring(0, 2));
        int b1 = Integer.parseInt(h2.substring(3, 5));
        int c1 = Integer.parseInt(h2.substring(6, 8));
        
        int hora =0, minuto=0, segundo =0;
        
        hora= a + a1;
        minuto = b + b1;
        
        if(minuto > 60){
            hora = hora + 1;
            minuto = minuto - 60;
        }
        if(minuto == 60){
            hora = hora + 1;
            minuto = 00;
        }
       
        String horaf = "", minutof ="";
        if(hora < 10){
            horaf = ("0" + hora);
        }else{
            horaf = String.valueOf(hora);
        }
        if(minuto < 10){
            minutof = ("0" + minuto);
        }else{
            minutof = String.valueOf(minuto);
        }

        txtHoras_Libres.setText(horaf + ":" + minutof + ":" + segundo+"0");
        LBL_CANTIDAD_HORAS_TOTAL.setText(txtHoras_Libres.getText());
        LBL_HORAS_LIBRES_SUMA.setText(txtHoras_Libres.getText());
    }
    
    public void hora_mayor(){
        String hm = LBL_TOTAL_HORA.getText();
        String hm2 = txtHoras_Libres.getText();
        
        int a = Integer.parseInt(hm.substring(0, 2));
        int b = Integer.parseInt(hm.substring(3, 5));
        int c = Integer.parseInt(hm.substring(6, 8));
        
        int a1 = Integer.parseInt(hm2.substring(0, 2));
        int b1 = Integer.parseInt(hm2.substring(3, 5));
        int c1 = Integer.parseInt(hm2.substring(6, 8));
        
        int hora =0, minuto=0, segundo =0;
        
        if(a > a1 && b > b1){
            
        }
    }
    
    public boolean repiteDetalleActividad(){
         
         boolean c=false;
         for (int i = 0; i < TB_ROL_ACTIVIDAD.getRowCount(); i++){    
                if(txt_ACTIVIDAD.getText().equalsIgnoreCase(TB_ROL_ACTIVIDAD.getValueAt(i, 1).toString())){
                    c=true;
                }
        }
               return c;
    }
    
    public void mostrar_SERVICIO_AREA(String AR_ID){
        String consulta="";
        try {
            consulta="EXEC PERSONAL_SERVICIO_AREA ?";
            PreparedStatement cmd = PR.getCn().prepareStatement(consulta);
            cmd.setString(1, AR_ID);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                LBL_SERVICIO.setText(r.getString(1));
                LBL_AREA.setText(r.getString(2));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga SERVICIO - AREA: " + e.getMessage());
        }
    }
    
     public void mostrar_HORAS_UO_ELIMINAR(String COD_TUR_UO){
        String consulta="";
        try {
            consulta="EXEC PERSONAL_TURNO_UO_CARGAR_HORA_EL ?";
            PreparedStatement cmd = PR.getCn().prepareStatement(consulta);
            cmd.setString(1, COD_TUR_UO);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                TXT_HORA_INICIO.setText(r.getString(1));
                TXT_HORA_FIN.setText(r.getString(2));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga HORA INICIO Y FIN ELIMINAR: " + e.getMessage());
        }
    }
    
    public void QUITAR_PERSONAL_ROL(){
        try{
            int filaselec=TB_TURNOS_PERSONAL_ROL.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(ROL_ACTIVIDAD, "¿Está Seguro que Desea QUITAR el Registro ?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    
                    DefaultTableModel modelo = (DefaultTableModel)TB_TURNOS_PERSONAL_ROL.getModel();
                    
                    modelo.removeRow(filaselec);
                    
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
   
                }
                
                    TB_TURNOS_UO.setEnabled(true);
                    TB_TURNOS_UO.setBackground(Color.white);
                    
                    D_FECHA.setDate(null);
                    txtLimite_Consultas_Per.setText("");
                    txtTotal_Horas.setText("");
                    txtTotal_Pago.setText("");
                    btnAgregar_ROL.setEnabled(true);
                    
            }else{
                JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "Seleccione el registro a Eliminar");
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(ROL_ACTIVIDAD, "error al eliminar rol");
        }
    }
     
    public void FECHA_PAGOS(){

          try {
              
            Calendar calendar = Calendar.getInstance();  
            
            String DIA = new SimpleDateFormat("dd").format(D_FECHA.getDate());
            String MES = new SimpleDateFormat("MM").format(D_FECHA.getDate());
            String ANIO = new SimpleDateFormat("yyy").format(D_FECHA.getDate());
              
            String fechaSeleccionada = DIA + "/" + MES + "/" + ANIO;
            
             DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
             Date fecha = dfo.parse(fechaSeleccionada);
          
                calendar.setTime(fecha);
                              
                String[] days = new String[] { "VACIO", "DOMINGO", "LUNES", "MARTES", "MIERCOLES", "JUEVES","VIERNES","SABADO"};

                String day = days[calendar.get(Calendar.DAY_OF_WEEK)];
                
                TXT_NOMBRE_DIA.setText(day);
                TXT_DIA.setText(DIA);
                TXT_MES.setText(MES);
                
            } catch (Exception e) {
                System.out.println("error fecha: " + e.getMessage());
            }
   
    }
    
    public void PAGOS_HORAS_DIA(){
        
        int filaselec = TB_TURNOS_UO.getSelectedRow();
        
        if(TXT_NOMBRE_DIA.getText().equalsIgnoreCase("LUNES") || TXT_NOMBRE_DIA.getText().equalsIgnoreCase("MARTES")
           || TXT_NOMBRE_DIA.getText().equalsIgnoreCase("MIERCOLES") || TXT_NOMBRE_DIA.getText().equalsIgnoreCase("JUEVES")
           || TXT_NOMBRE_DIA.getText().equalsIgnoreCase("VIERNES")){
            if((TXT_MES.getText().equalsIgnoreCase("01") && TXT_DIA.getText().equalsIgnoreCase("01")) 
                    || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("13"))
                    || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("14")) 
                    || (TXT_MES.getText().equalsIgnoreCase("05") && TXT_DIA.getText().equalsIgnoreCase("01"))
                    || (TXT_MES.getText().equalsIgnoreCase("06") && TXT_DIA.getText().equalsIgnoreCase("29")) 
                    || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("28"))
                    || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("29"))
                    || (TXT_MES.getText().equalsIgnoreCase("08") && TXT_DIA.getText().equalsIgnoreCase("30"))
                    || (TXT_MES.getText().equalsIgnoreCase("10") && TXT_DIA.getText().equalsIgnoreCase("08")) 
                    || (TXT_MES.getText().equalsIgnoreCase("11") && TXT_DIA.getText().equalsIgnoreCase("01"))
                    || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("08"))
                    || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("25")))
            {
                ////pasar el pago por feriados
//                  String precio_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));      
                  String precio_R_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));;
                
                  BigDecimal bd2_f = new BigDecimal(precio_R_feriado);

                  bd2_f = bd2_f.setScale(0, BigDecimal.ROUND_HALF_UP);
                
                  txtTotal_Pago.setText(String.valueOf(bd2_f));
                
            }else{
//                  String precio_normal = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 12));
                
                  String precio_R_normal = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 12));
                
                  BigDecimal bd2 = new BigDecimal(precio_R_normal);

                  bd2 = bd2.setScale(0, BigDecimal.ROUND_HALF_UP);
                
                  txtTotal_Pago.setText(String.valueOf(bd2));
            }
        }else{
            if(TXT_NOMBRE_DIA.getText().equalsIgnoreCase("SABADO")){
                    if((TXT_MES.getText().equalsIgnoreCase("01") && TXT_DIA.getText().equalsIgnoreCase("01")) 
                        || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("13"))
                        || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("14")) 
                        || (TXT_MES.getText().equalsIgnoreCase("05") && TXT_DIA.getText().equalsIgnoreCase("01"))
                        || (TXT_MES.getText().equalsIgnoreCase("06") && TXT_DIA.getText().equalsIgnoreCase("29")) 
                        || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("28"))
                        || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("29"))
                        || (TXT_MES.getText().equalsIgnoreCase("08") && TXT_DIA.getText().equalsIgnoreCase("30"))
                        || (TXT_MES.getText().equalsIgnoreCase("10") && TXT_DIA.getText().equalsIgnoreCase("08")) 
                        || (TXT_MES.getText().equalsIgnoreCase("11") && TXT_DIA.getText().equalsIgnoreCase("01"))
                        || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("08"))
                        || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("25")))
                    {  
                        ////pasar el pago por feriados
        //                  String precio_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));      
                          String precio_R_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));;

                          BigDecimal bd2_f = new BigDecimal(precio_R_feriado);

                          bd2_f = bd2_f.setScale(0, BigDecimal.ROUND_HALF_UP);

                          txtTotal_Pago.setText(String.valueOf(bd2_f));
                        
                    }else{
                        
//                          String precio_sabado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 12));
                
                          String precio_R_sabado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 13));

                          BigDecimal bd2_s = new BigDecimal(precio_R_sabado);

                          bd2_s = bd2_s.setScale(0, BigDecimal.ROUND_HALF_UP);

                          txtTotal_Pago.setText(String.valueOf(bd2_s));
                        
                    }
                
            }else{
                if(TXT_NOMBRE_DIA.getText().equalsIgnoreCase("DOMINGO")){
                    if((TXT_MES.getText().equalsIgnoreCase("01") && TXT_DIA.getText().equalsIgnoreCase("01")) 
                        || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("13"))
                        || (TXT_MES.getText().equalsIgnoreCase("04") && TXT_DIA.getText().equalsIgnoreCase("14")) 
                        || (TXT_MES.getText().equalsIgnoreCase("05") && TXT_DIA.getText().equalsIgnoreCase("01"))
                        || (TXT_MES.getText().equalsIgnoreCase("06") && TXT_DIA.getText().equalsIgnoreCase("29")) 
                        || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("28"))
                        || (TXT_MES.getText().equalsIgnoreCase("07") && TXT_DIA.getText().equalsIgnoreCase("29"))
                        || (TXT_MES.getText().equalsIgnoreCase("08") && TXT_DIA.getText().equalsIgnoreCase("30"))
                        || (TXT_MES.getText().equalsIgnoreCase("10") && TXT_DIA.getText().equalsIgnoreCase("08")) 
                        || (TXT_MES.getText().equalsIgnoreCase("11") && TXT_DIA.getText().equalsIgnoreCase("01"))
                        || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("08"))
                        || (TXT_MES.getText().equalsIgnoreCase("12") && TXT_DIA.getText().equalsIgnoreCase("25")))
                    {
                        ////pasar el pago por feriados
        //                  String precio_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));      
                          String precio_R_feriado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 15));;

                          BigDecimal bd2_f = new BigDecimal(precio_R_feriado);

                          bd2_f = bd2_f.setScale(0, BigDecimal.ROUND_HALF_UP);

                          txtTotal_Pago.setText(String.valueOf(bd2_f));
                    }else{
//                          String precio_sabado = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 12));
                
                          String precio_R_domingo = String.valueOf(TB_TURNOS_UO.getValueAt(filaselec, 14));

                          BigDecimal bd2_d = new BigDecimal(precio_R_domingo);

                          bd2_d = bd2_d.setScale(0, BigDecimal.ROUND_HALF_UP);

                          txtTotal_Pago.setText(String.valueOf(bd2_d));
                    }
                }
            }           
        }
    }
    
    private void Clear_TB_ACTIVIDAD_QUITAR(){
        DefaultTableModel modelo1 = (DefaultTableModel)TB_ROL_ACTIVIDAD.getModel(); 
        int b=TB_ROL_ACTIVIDAD.getRowCount();
        for(int j=0;j<b;j++){
            modelo1.removeRow(0);
        }
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
            java.util.logging.Logger.getLogger(PERSONAL_ROL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ROL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ROL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ROL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PERSONAL_ROL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_AGREGAR_ACTIVIDADES_FRM;
    private javax.swing.JButton BTN_AGREGAR_TURNOS_UO;
    private javax.swing.JButton BTN_QUITAR;
    private javax.swing.JButton BTN_QUITAR_ACTIVIDAD;
    private com.toedter.calendar.JDateChooser D_FECHA;
    private com.toedter.calendar.JDateChooser D_FECHA_FIN;
    private com.toedter.calendar.JDateChooser D_FECHA_INICIO;
    private javax.swing.JDialog ERROR_BUSCAR_MED;
    private javax.swing.JLabel LBL_AREA;
    private javax.swing.JLabel LBL_AREA_ACT;
    private javax.swing.JLabel LBL_CANTIDAD_HORAS_TOTAL;
    private javax.swing.JLabel LBL_GUION;
    private javax.swing.JLabel LBL_HORAS_LIBRES_SUMA;
    private javax.swing.JTextField LBL_HORA_FIN_1;
    private javax.swing.JLabel LBL_HORA_TOTAL_QUITAR;
    private javax.swing.JLabel LBL_PASAR_DIA;
    private javax.swing.JLabel LBL_SERVICIO;
    private javax.swing.JLabel LBL_TOTAL_HORA;
    private javax.swing.JLabel LBL_UNIDAD_ORGANICA_2;
    private javax.swing.JLabel LBL_UO_HORAS_ELIMINAR;
    private javax.swing.JDialog MEDICOS_UO;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JPanel P_FECHAS;
    private javax.swing.JPanel P_ROL;
    private javax.swing.JPanel P_ROL1;
    public static javax.swing.JDialog ROL_ACTIVIDAD;
    private javax.swing.JButton T5;
    public static javax.swing.JTable TB_ACTIVIDADES_LISTA;
    private javax.swing.JTable TB_PERSONAL_ROL;
    private javax.swing.JTable TB_ROL_ACTIVIDAD;
    private javax.swing.JTable TB_TURNOS_PERSONAL_ROL;
    public static javax.swing.JTable TB_TURNOS_UO;
    private javax.swing.JTextField TXT_AR_ID_ACTI;
    private javax.swing.JTextField TXT_CODIGO_ROL_G;
    private javax.swing.JTextField TXT_COD_ROL_GUARDAR;
    private javax.swing.JTextField TXT_DIA;
    private javax.swing.JLabel TXT_HORA_FIN;
    private javax.swing.JLabel TXT_HORA_INICIO;
    private javax.swing.JLabel TXT_HORA_INICIO1;
    private javax.swing.JTextField TXT_HORA_QUITAR_CBX;
    private javax.swing.JTextField TXT_HORA_QUITAR_CBX_FIN;
    private javax.swing.JTextField TXT_MES;
    private javax.swing.JTextField TXT_MOSTRAR_HORA_GUARDAR_UO;
    private javax.swing.JTextField TXT_NOMBRE_DIA;
    private javax.swing.JButton btnAgregar_ROL;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnBuscarPersonal_rol;
    private javax.swing.JButton btnBuscarPersonal_rol1;
    private javax.swing.JButton btnGuardarDetalle1;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btn_AGREGAR_ACTIVIDADES;
    private javax.swing.JButton btn_BUSCAR_ACTIVIDAD;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    public static javax.swing.JButton btnguardar_ACTIVIDAD_ROL;
    private javax.swing.JPanel cargareliminar_Per;
    private javax.swing.JComboBox cb_HORA_FIN;
    private javax.swing.JComboBox cb_HORA_INICIO;
    private javax.swing.JCheckBox cb_Rango_fecha;
    private javax.swing.JButton eli;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lblCOD_UO_1;
    private javax.swing.JLabel lblGM;
    private javax.swing.JLabel lblListado_Edicion;
    private javax.swing.JLabel lbl_id_per_uni_org;
    private javax.swing.JLabel lbl_nombre_S;
    private javax.swing.JLabel lbl_turnos_disponibles;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JTable tb_Personal_UO;
    public static javax.swing.JTextField txtAR_ID;
    public static javax.swing.JTextField txtBuscarMedico_ROL;
    public static javax.swing.JTextField txtBuscarMedico_UO;
    private javax.swing.JTextField txtCOD_ROL;
    private javax.swing.JLabel txtHoras_Libres;
    private javax.swing.JTextField txtLimite_Consultas_Per;
    public static javax.swing.JTextField txtMedico_UO;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtTotal_Horas;
    private javax.swing.JTextField txtTotal_Pago;
    private javax.swing.JTextField txtUO_3;
    public static javax.swing.JTextField txt_ACTIVIDAD;
    private javax.swing.JTextField txt_COD_UNI_ORGANICA_JERAR;
    private javax.swing.JTextField txt_COD_UNI_ORG_ACTIVIDADES;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Thread ct = Thread.currentThread();
        while (ct == h1) {
           
//            mostrar_ACTIVIDADES();
//            mostrar_TURNOS_UO();
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
    }
}
