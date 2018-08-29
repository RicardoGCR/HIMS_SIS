/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.EC;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelos.EC.EC_EXAMEN_CABECERA;
import servicios.Conexion;
import modelos.EC.*;
import modelos.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import static vista.RX.RX_PRINCIPAL.jLabel1;

/**
 *
 * @author MYS3
 */
public class EC_EXAMEN_CAB extends javax.swing.JFrame implements Runnable{
Conexion conectar=new Conexion();
Conexion c=new Conexion();
Connection con;
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread h1;
ResultSet r;
CallableStatement cst;
DefaultTableModel m1, msb, msb1, m2, m3, m4, m5, m6, m7;
byte tg;
static EC_EXAMEN_CABECERA EXC = new EC_EXAMEN_CABECERA();
    /**
     * Creates new form EC_EXAMEN_CAB
     */
    public EC_EXAMEN_CAB() {
        initComponents();
        con=conectar.conectar();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        
        
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        PERSONAL_ROL.setLocationRelativeTo(null);
        PERSONAL_ROL.getContentPane().setBackground(Color.white);
        PERSONAL_ROL_TODO.setLocationRelativeTo(null);
        PERSONAL_ROL_TODO.getContentPane().setBackground(Color.white);
        CIE10.setLocationRelativeTo(null);
        CIE10.getContentPane().setBackground(Color.white);
        
        mostrarArea_EC();
//        CargarPersonalRol_EC();
        CargarPersonalRol_todo_EC();
        cargarDiagnostico_EC();
        inicializar_tabla_cie10_EC();
        cargareliminar.setVisible(false);

        jPanel6.setVisible(false);

        deshabilitar_EC();
        
        //FECHA Y HORA
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFechaReg_EC.setText(fechaActual());
        
        ///////////////////////////////////////
        txtPersonalRealiza_EC.setEditable(false);
        txtPersonalRegistra_EC.setEditable(false);
        
        /////////////////////////////////////
        lblCod_Personal_Sol_EC.setVisible(false);
        txtCodigoDoc_EC.setVisible(false);
        txtCOD_CABECERA.setVisible(false);
        lblIDArea_EC.setVisible(false);
        
        lblCod_Per_Registra.setVisible(false);
        lblCod_Per_realiza.setVisible(false);
        txtCod_doc_det_EC.setVisible(false);
        txtId_Preventa.setVisible(false);
        txtCOD_CAB_RES.setVisible(false);
        txtCOD_DETALLE_RES.setVisible(false);
        lblPerB.setVisible(false);
        btnGuardarDetalleRes.setEnabled(false);
        txtNumExamen.setVisible(false);
        txtAM.setVisible(false);
        txtDNI.setVisible(false);
        txtHC.setVisible(false);
        //NUMERO DE EXAMEN
        EC_EXAMEN_RESULTADO_DETALLE num=new EC_EXAMEN_RESULTADO_DETALLE();
        txtNumExamen.setText(num.RX_EC_INFORME_generarNum_EC());
        if(txtNumExamen.getText().equalsIgnoreCase("")){
        txtNumExamen.setText("00000001");
        }    
        lblNumExamen.setText(txtNumExamen.getText());
        
        ///SALIR CON ESCAPE
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Personal_rol_EC = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblPerB1 = new javax.swing.JLabel();
        panelCPT1 = new javax.swing.JPanel();
        txtBuscarPersonal_EC = new javax.swing.JTextField();
        btnBuscarCPT1 = new javax.swing.JButton();
        lblPerB = new javax.swing.JLabel();
        PERSONAL_ROL_TODO = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Personal_rol_todo_EC = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtBuscarPersonal_TODO_EC = new javax.swing.JTextField();
        btnBuscarCPT2 = new javax.swing.JButton();
        CIE10 = new javax.swing.JDialog();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_CIE10_EC = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txt_CIE10_EC = new javax.swing.JTextField();
        btnBuscarCPT = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jpanel = new javax.swing.JPanel();
        txtNombreP = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JLabel();
        txtGenero = new javax.swing.JLabel();
        txtFechaNac = new javax.swing.JLabel();
        txtAM = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtHC = new javax.swing.JTextField();
        lblCod_Per_Registra = new javax.swing.JLabel();
        lblCod_Per_realiza = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblIDArea_EC = new javax.swing.JLabel();
        txtNumExamen = new javax.swing.JTextField();
        txtCOD_CABECERA = new javax.swing.JTextField();
        txtCodigoDoc_EC = new javax.swing.JTextField();
        lblCod_Personal_Sol_EC = new javax.swing.JLabel();
        lblNomA_EC = new javax.swing.JLabel();
        txtCod_doc_det_EC = new javax.swing.JTextField();
        txtCOD_DETALLE_RES = new javax.swing.JTextField();
        txtCOD_CAB_RES = new javax.swing.JTextField();
        txtId_Preventa = new javax.swing.JTextField();
        txtEdad1 = new javax.swing.JLabel();
        txtEdad2 = new javax.swing.JLabel();
        jpanel1 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        lblUsu_EC = new javax.swing.JLabel();
        btnGuardarDetalleRes = new javax.swing.JButton();
        btnRegresarRes1 = new javax.swing.JButton();
        btnGuardarCabeceraRes1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblHospiServ_EC = new javax.swing.JLabel();
        lblHospi = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtHabitacion_EC = new javax.swing.JTextField();
        txtCama_EC = new javax.swing.JTextField();
        txtPersonalSolicita_EC = new javax.swing.JTextField();
        btnPersonalSolicita = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cargareliminar = new javax.swing.JPanel();
        Mensaje = new javax.swing.JLabel();
        eli = new javax.swing.JButton();
        noeli = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        lblNumExamen = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblFP_EC = new javax.swing.JLabel();
        lblFUA_EC = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        panelCPT2 = new javax.swing.JPanel();
        txtPersonalRegistra_EC = new javax.swing.JTextField();
        btnPersonalRegistra_EC = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        panelCPT3 = new javax.swing.JPanel();
        txtPersonalRealiza_EC = new javax.swing.JTextField();
        btnPersonalRealiza_EC = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblFechaReg_EC = new javax.swing.JLabel();
        lblHoraReg_EC = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_examen_det_EC = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        EP_CONCLUSION_EC = new javax.swing.JEditorPane();
        panelCPT6 = new javax.swing.JPanel();
        txtPersonalRealizaRes2 = new javax.swing.JTextField();
        btnBuscarCIE10 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbCIE10_EC = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        EP_Descripcion_EC = new javax.swing.JEditorPane();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        PERSONAL_ROL.setAlwaysOnTop(true);
        PERSONAL_ROL.setMinimumSize(new java.awt.Dimension(692, 360));
        PERSONAL_ROL.setResizable(false);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Personal_rol_EC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Personal_rol_EC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_Personal_rol_EC.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Personal_rol_EC.setGridColor(new java.awt.Color(102, 102, 102));
        tb_Personal_rol_EC.setRowHeight(30);
        tb_Personal_rol_EC.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Personal_rol_EC.getTableHeader().setReorderingAllowed(false);
        tb_Personal_rol_EC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Personal_rol_ECKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Personal_rol_EC);

        jPanel5.setBackground(new java.awt.Color(230, 230, 230));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Médico Solicitante");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Buscar por DNI. Cargo, Apellidos y Nombres");

        lblPerB1.setForeground(new java.awt.Color(230, 230, 230));
        lblPerB1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPerB1.setText("B1");

        panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarPersonal_EC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarPersonal_EC.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPersonal_EC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarPersonal_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtBuscarPersonal_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPersonal_ECCaretUpdate(evt);
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
                .addComponent(txtBuscarPersonal_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT1Layout.setVerticalGroup(
            panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarPersonal_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblPerB.setForeground(new java.awt.Color(230, 230, 230));
        lblPerB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPerB.setText("B1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addContainerGap(450, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPerB1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(lblPerB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(lblPerB))
                    .addComponent(lblPerB1))
                .addGap(2, 2, 2)
                .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PERSONAL_ROLLayout = new javax.swing.GroupLayout(PERSONAL_ROL.getContentPane());
        PERSONAL_ROL.getContentPane().setLayout(PERSONAL_ROLLayout);
        PERSONAL_ROLLayout.setHorizontalGroup(
            PERSONAL_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        PERSONAL_ROLLayout.setVerticalGroup(
            PERSONAL_ROLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PERSONAL_ROLLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
        );

        PERSONAL_ROL_TODO.setAlwaysOnTop(true);
        PERSONAL_ROL_TODO.setMinimumSize(new java.awt.Dimension(692, 360));
        PERSONAL_ROL_TODO.setResizable(false);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Personal_rol_todo_EC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Personal_rol_todo_EC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_Personal_rol_todo_EC.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Personal_rol_todo_EC.setRowHeight(30);
        tb_Personal_rol_todo_EC.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Personal_rol_todo_EC.getTableHeader().setReorderingAllowed(false);
        tb_Personal_rol_todo_EC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Personal_rol_todo_ECKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tb_Personal_rol_todo_EC);

        jPanel11.setBackground(new java.awt.Color(230, 230, 230));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Responsable del Resultado");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Buscar por DNI. Cargo, Apellidos y Nombres");

        panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarPersonal_TODO_EC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarPersonal_TODO_EC.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPersonal_TODO_EC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarPersonal_TODO_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtBuscarPersonal_TODO_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPersonal_TODO_ECCaretUpdate(evt);
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
                .addComponent(txtBuscarPersonal_TODO_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT4Layout.setVerticalGroup(
            panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarPersonal_TODO_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel27)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PERSONAL_ROL_TODOLayout = new javax.swing.GroupLayout(PERSONAL_ROL_TODO.getContentPane());
        PERSONAL_ROL_TODO.getContentPane().setLayout(PERSONAL_ROL_TODOLayout);
        PERSONAL_ROL_TODOLayout.setHorizontalGroup(
            PERSONAL_ROL_TODOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PERSONAL_ROL_TODOLayout.setVerticalGroup(
            PERSONAL_ROL_TODOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PERSONAL_ROL_TODOLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
        );

        CIE10.setAlwaysOnTop(true);
        CIE10.setMinimumSize(new java.awt.Dimension(470, 500));
        CIE10.setResizable(false);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_CIE10_EC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_CIE10_EC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_CIE10_EC.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_CIE10_EC.setRowHeight(30);
        tb_CIE10_EC.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_CIE10_EC.getTableHeader().setReorderingAllowed(false);
        tb_CIE10_EC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_CIE10_ECKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tb_CIE10_EC);

        jPanel8.setBackground(new java.awt.Color(230, 230, 230));

        jLabel40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("CIE10");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setText("Descripción");

        panelCPT.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txt_CIE10_EC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_CIE10_EC.setForeground(new java.awt.Color(51, 51, 51));
        txt_CIE10_EC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_CIE10_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txt_CIE10_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_CIE10_ECCaretUpdate(evt);
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
                .addComponent(txt_CIE10_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPTLayout.setVerticalGroup(
            panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPTLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_CIE10_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel42.setBackground(new java.awt.Color(230, 230, 230));
        jLabel42.setForeground(new java.awt.Color(230, 230, 230));
        jLabel42.setText("         ");

        jLabel24.setForeground(new java.awt.Color(230, 230, 230));
        jLabel24.setText("         ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel41)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel42)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel24))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(1, 1, 1)
                .addComponent(jLabel41)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CIE10Layout = new javax.swing.GroupLayout(CIE10.getContentPane());
        CIE10.getContentPane().setLayout(CIE10Layout);
        CIE10Layout.setHorizontalGroup(
            CIE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CIE10Layout.setVerticalGroup(
            CIE10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CIE10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1369, 775));

        jpanel.setBackground(new java.awt.Color(25, 188, 157));
        jpanel.setPreferredSize(new java.awt.Dimension(632, 131));

        txtNombreP.setBackground(new java.awt.Color(0, 102, 102));
        txtNombreP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombreP.setForeground(new java.awt.Color(255, 255, 255));
        txtNombreP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNombreP.setText("Exámenes EC");
        txtNombreP.setToolTipText("");
        txtNombreP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("DNI");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("N° HC");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Edad");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Género");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Fecha Nacimiento");

        txtEdad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEdad.setForeground(new java.awt.Color(255, 255, 255));
        txtEdad.setText("Edad");

        txtGenero.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtGenero.setForeground(new java.awt.Color(255, 255, 255));
        txtGenero.setText("Género");

        txtFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtFechaNac.setForeground(new java.awt.Color(255, 255, 255));
        txtFechaNac.setText("Fecha Nacimiento");

        txtAM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAM.setEnabled(false);
        txtAM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAMCaretUpdate(evt);
            }
        });

        txtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI.setEnabled(false);
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });

        txtHC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHC.setEnabled(false);
        txtHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHCActionPerformed(evt);
            }
        });

        lblCod_Per_Registra.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Per_Registra.setText("jLabel13");

        lblCod_Per_realiza.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Per_realiza.setText("jLabel13");

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jLabel28.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Fecha y Hora del Examen:");

        lblIDArea_EC.setForeground(new java.awt.Color(255, 255, 255));
        lblIDArea_EC.setText("jLabel1");

        txtNumExamen.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        txtNumExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumExamenActionPerformed(evt);
            }
        });

        txtCodigoDoc_EC.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        txtCodigoDoc_EC.setText("jTextField1");
        txtCodigoDoc_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCodigoDoc_ECCaretUpdate(evt);
            }
        });

        lblCod_Personal_Sol_EC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblCod_Personal_Sol_EC.setForeground(new java.awt.Color(255, 255, 255));
        lblCod_Personal_Sol_EC.setText("codPersonal");

        lblNomA_EC.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        lblNomA_EC.setForeground(new java.awt.Color(255, 255, 255));
        lblNomA_EC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomA_EC.setText("jLabel1");

        txtCod_doc_det_EC.setText("COD_DOC");

        txtCOD_DETALLE_RES.setText("COD_DET");

        txtCOD_CAB_RES.setText("COD_CAB");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblCod_Personal_Sol_EC)
                        .addGap(18, 18, 18)
                        .addComponent(txtCod_doc_det_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtId_Preventa, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(txtCOD_CAB_RES, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtCOD_DETALLE_RES, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNomA_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDArea_EC))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoDoc_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtNumExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCOD_CABECERA, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId_Preventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCOD_CAB_RES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCOD_DETALLE_RES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCod_doc_det_EC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCod_Personal_Sol_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNomA_EC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(lblIDArea_EC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCOD_CABECERA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigoDoc_EC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtEdad1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEdad1.setForeground(new java.awt.Color(255, 255, 255));
        txtEdad1.setText("dni");

        txtEdad2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtEdad2.setForeground(new java.awt.Color(255, 255, 255));
        txtEdad2.setText("hc");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreP)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(25, 25, 25)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEdad2)
                            .addComponent(txtEdad1)
                            .addComponent(txtEdad))
                        .addGap(248, 248, 248)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCod_Per_Registra)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCod_Per_realiza)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35))
                                .addGap(42, 42, 42)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaNac)
                                    .addComponent(txtGenero))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(txtNombreP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel34)
                            .addComponent(txtGenero)
                            .addComponent(txtEdad1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel35)
                            .addComponent(txtFechaNac)
                            .addComponent(txtEdad2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(txtEdad)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCod_Per_Registra)
                            .addComponent(lblCod_Per_realiza))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jpanel1.setBackground(new java.awt.Color(40, 40, 43));

        titulo6.setBackground(new java.awt.Color(0, 102, 102));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        titulo6.setForeground(new java.awt.Color(204, 204, 204));
        titulo6.setText("Resultados");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblUsu_EC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsu_EC.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu_EC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu_EC.setText("Usuario");

        btnGuardarDetalleRes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardarDetalleRes.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarDetalleRes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardarDetalleRes.setText("Guardar");
        btnGuardarDetalleRes.setContentAreaFilled(false);
        btnGuardarDetalleRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarDetalleRes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnGuardarDetalleRes.setIconTextGap(30);
        btnGuardarDetalleRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDetalleResActionPerformed(evt);
            }
        });

        btnRegresarRes1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegresarRes1.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarRes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casa-32.png"))); // NOI18N
        btnRegresarRes1.setText("Regresar");
        btnRegresarRes1.setContentAreaFilled(false);
        btnRegresarRes1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRegresarRes1.setIconTextGap(30);
        btnRegresarRes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarRes1ActionPerformed(evt);
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

        jPanel4.setBackground(new java.awt.Color(40, 40, 43));
        jPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        lblHospiServ_EC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblHospiServ_EC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHospiServ_EC.setText("- - -");

        lblHospi.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblHospi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHospi.setText("Hospitalización:");

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Habitación");

        jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Cama");

        txtHabitacion_EC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtHabitacion_EC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHabitacion_EC.setText("- - -");
        txtHabitacion_EC.setEnabled(false);
        txtHabitacion_EC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHabitacion_ECActionPerformed(evt);
            }
        });

        txtCama_EC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtCama_EC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCama_EC.setText("- - -");
        txtCama_EC.setEnabled(false);
        txtCama_EC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCama_ECActionPerformed(evt);
            }
        });

        txtPersonalSolicita_EC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtPersonalSolicita_EC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPersonalSolicita_EC.setEnabled(false);
        txtPersonalSolicita_EC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonalSolicita_ECActionPerformed(evt);
            }
        });
        txtPersonalSolicita_EC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPersonalSolicita_ECKeyPressed(evt);
            }
        });

        btnPersonalSolicita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPersonalSolicita.setContentAreaFilled(false);
        btnPersonalSolicita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonalSolicita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalSolicitaActionPerformed(evt);
            }
        });
        btnPersonalSolicita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPersonalSolicitaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel8.setText("Médico Solicitante:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblHospi, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtPersonalSolicita_EC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(443, 443, 443)))
                        .addComponent(btnPersonalSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHospiServ_EC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtHabitacion_EC)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCama_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(54, 54, 54))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPersonalSolicita))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(txtPersonalSolicita_EC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHospi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblHospiServ_EC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCama_EC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHabitacion_EC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
        jpanel1.setLayout(jpanel1Layout);
        jpanel1Layout.setHorizontalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(lblUsu_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 156, Short.MAX_VALUE))
                            .addComponent(btnGuardarDetalleRes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegresarRes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarCabeceraRes1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jpanel1Layout.setVerticalGroup(
            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titulo6)
                .addGap(62, 62, 62)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnGuardarDetalleRes)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarCabeceraRes1)
                .addGap(18, 18, 18)
                .addComponent(btnRegresarRes1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsu_EC)
                .addContainerGap())
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

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("N° de Examen");

        lblNumExamen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNumExamen.setForeground(new java.awt.Color(51, 51, 51));
        lblNumExamen.setText("10000");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Forma de Pago");

        lblFP_EC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFP_EC.setForeground(new java.awt.Color(51, 51, 51));
        lblFP_EC.setText("forma PAGO");

        lblFUA_EC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFUA_EC.setForeground(new java.awt.Color(255, 255, 255));
        lblFUA_EC.setText("----");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Solicitado");

        panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRegistra_EC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRegistra_EC.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRegistra_EC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRegistra_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRegistra_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRegistra_ECCaretUpdate(evt);
            }
        });

        btnPersonalRegistra_EC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPersonalRegistra_EC.setContentAreaFilled(false);
        btnPersonalRegistra_EC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonalRegistra_EC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalRegistra_ECActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
        panelCPT2.setLayout(panelCPT2Layout);
        panelCPT2Layout.setHorizontalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(txtPersonalRegistra_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalRegistra_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT2Layout.setVerticalGroup(
            panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPersonalRegistra_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnPersonalRegistra_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Realizado");

        panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
        panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPersonalRealiza_EC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtPersonalRealiza_EC.setForeground(new java.awt.Color(51, 51, 51));
        txtPersonalRealiza_EC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPersonalRealiza_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtPersonalRealiza_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPersonalRealiza_ECCaretUpdate(evt);
            }
        });

        btnPersonalRealiza_EC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnPersonalRealiza_EC.setContentAreaFilled(false);
        btnPersonalRealiza_EC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPersonalRealiza_EC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalRealiza_ECActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
        panelCPT3.setLayout(panelCPT3Layout);
        panelCPT3Layout.setHorizontalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPersonalRealiza_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPersonalRealiza_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        panelCPT3Layout.setVerticalGroup(
            panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCPT3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPersonalRealiza_EC, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(btnPersonalRealiza_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Acto Médico");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Fecha y Hora Resultado");

        lblFechaReg_EC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblFechaReg_EC.setForeground(new java.awt.Color(51, 51, 51));
        lblFechaReg_EC.setText("00/00/00");

        lblHoraReg_EC.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblHoraReg_EC.setForeground(new java.awt.Color(51, 51, 51));
        lblHoraReg_EC.setText("00:00:00");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Acto Médico");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Exámenes Realizados");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_examen_det_EC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_examen_det_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_examen_det_EC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tb_examen_det_EC.setForeground(new java.awt.Color(51, 51, 51));
        tb_examen_det_EC.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_examen_det_EC.setRowHeight(30);
        tb_examen_det_EC.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_examen_det_EC.getTableHeader().setReorderingAllowed(false);
        tb_examen_det_EC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_examen_det_ECMouseClicked(evt);
            }
        });
        tb_examen_det_EC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tb_examen_det_ECFocusLost(evt);
            }
        });
        tb_examen_det_EC.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tb_examen_det_ECCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        tb_examen_det_EC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tb_examen_det_ECPropertyChange(evt);
            }
        });
        tb_examen_det_EC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_examen_det_ECKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_examen_det_ECKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tb_examen_det_ECKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tb_examen_det_EC);

        jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Diagnósticos");

        EP_CONCLUSION_EC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane9.setViewportView(EP_CONCLUSION_EC);

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

        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tbCIE10_EC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbCIE10_EC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tbCIE10_EC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbCIE10_EC.setForeground(new java.awt.Color(51, 51, 51));
        tbCIE10_EC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCIE10_EC.setRowHeight(25);
        tbCIE10_EC.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tbCIE10_EC.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tbCIE10_EC);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
        jButton1.setText("Quitar");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        EP_Descripcion_EC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        EP_Descripcion_EC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                EP_Descripcion_ECCaretUpdate(evt);
            }
        });
        jScrollPane3.setViewportView(EP_Descripcion_EC);

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Relato");

        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel29))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(350, 350, 350)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel36))
                                                .addGap(80, 80, 80)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel39)
                                                    .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblFechaReg_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblFUA_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel1))
                                                    .addComponent(lblHoraReg_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNumExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFP_EC, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addComponent(jScrollPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(lblNumExamen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblFP_EC)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(lblFechaReg_EC)
                            .addComponent(lblHoraReg_EC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel36)
                                .addComponent(jLabel39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFUA_EC)
                                .addComponent(jLabel1)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(panelCPT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9)))
                .addGap(4, 4, 4)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoDoc_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodigoDoc_ECCaretUpdate
         mostrar_VER_DETALLE_EC();
         mostrar_FP_EC(txtCodigoDoc_EC.getText());
         RX_EC_VER_CODIGO_RES_INFORME(txtCodigoDoc_EC.getText());
    }//GEN-LAST:event_txtCodigoDoc_ECCaretUpdate

    private void txtHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHCActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtHabitacion_ECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabitacion_ECActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHabitacion_ECActionPerformed

    private void txtCama_ECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCama_ECActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCama_ECActionPerformed

    private void txtPersonalSolicita_ECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalSolicita_ECActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicita_ECActionPerformed

    private void txtPersonalSolicita_ECKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicita_ECKeyPressed
        //        char tecla= evt.getKeyChar();
        //        if(tecla==KeyEvent.VK_ENTER){
            //            personal.setVisible(true);
            //            txtBuscar.setText("");
            //            Personal_cargar();
            //            Personal_formato();
            //        }
    }//GEN-LAST:event_txtPersonalSolicita_ECKeyPressed

    private void btnPersonalSolicitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalSolicitaActionPerformed
        PERSONAL_ROL_TODO.setVisible(true);
        txtBuscarPersonal_EC.requestFocus();
        lblPerB.setText("B1");
    }//GEN-LAST:event_btnPersonalSolicitaActionPerformed

    private void tb_Personal_rol_ECKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_rol_ECKeyPressed
        char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_EC.getRowCount() == 0 &&
            this.tb_Personal_rol_EC.getSelectedRow() == -1){

            JOptionPane.showMessageDialog(rootPane, "La tabla esta vacia");

        }else
        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_EC.getRowCount() != 0 &&
            this.tb_Personal_rol_EC.getSelectedRow() != -1){
            int fila = tb_Personal_rol_EC.getSelectedRow();
            //frm_SALA_OPERACION_TIPO_ANESTESIA T = new frm_SALA_OPERACION_TIPO_ANESTESIA();
            //T.setVisible(true);

            String apep, apem, nom;
            apep = tb_Personal_rol_EC.getValueAt(fila, 2).toString();
            apem = tb_Personal_rol_EC.getValueAt(fila, 3).toString();
            nom = tb_Personal_rol_EC.getValueAt(fila, 4).toString();

            if(lblPerB.getText().equalsIgnoreCase("B1")){
                txtPersonalRealiza_EC.setText(String.valueOf(apep + " " + apem + " " + nom));
                lblCod_Per_realiza.setText(String.valueOf(tb_Personal_rol_EC.getValueAt(fila, 1)));
               
                tb_Personal_rol_EC.getSelectionModel().setSelectionInterval(0, 0);
                tb_Personal_rol_EC.requestFocus();
                
                btnPersonalSolicita.requestFocus();
                
                tb_examen_det_EC.setEnabled(true);
                tb_examen_det_EC.setBackground(Color.white);
                tb_examen_det_EC.getSelectionModel().setSelectionInterval(0, 0);
                tb_examen_det_EC.requestFocus();
                
            }else{
                if(lblPerB.getText().equalsIgnoreCase("B2")){
                    txtPersonalRegistra_EC.setText(String.valueOf(apep + " " + apem + " " + nom));
                    lblCod_Per_Registra.setText(String.valueOf(tb_Personal_rol_EC.getValueAt(fila, 1)));
                    
                    tb_Personal_rol_EC.getSelectionModel().setSelectionInterval(0, 0);
                    tb_Personal_rol_EC.requestFocus();
                    
                    btnPersonalRealiza_EC.requestFocus();
                }
            }

            PERSONAL_ROL.dispose();
            txtBuscarPersonal_EC.setText("");
            
            

            //            txtNumeroRegla.setEnabled(false);
            //            txtDescripcionRegla.setEnabled(false);
            //            btnmodificar.setEnabled(true);
            //            btneliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tb_Personal_rol_ECKeyPressed

    private void tb_Personal_rol_todo_ECKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_rol_todo_ECKeyPressed
        char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_todo_EC.getRowCount() == 0 &&
            this.tb_Personal_rol_todo_EC.getSelectedRow() == -1){

            JOptionPane.showMessageDialog(rootPane, "La tabla esta vacia");

        }else
        if(teclaPresionada==KeyEvent.VK_ENTER &&
            this.tb_Personal_rol_todo_EC.getRowCount() != 0 &&
            this.tb_Personal_rol_todo_EC.getSelectedRow() != -1){
            int fila = tb_Personal_rol_todo_EC.getSelectedRow();

            String apep, apem, nom;
            apep = tb_Personal_rol_todo_EC.getValueAt(fila, 2).toString();
            apem = tb_Personal_rol_todo_EC.getValueAt(fila, 3).toString();
            nom = tb_Personal_rol_todo_EC.getValueAt(fila, 4).toString();

            txtPersonalSolicita_EC.setText(String.valueOf(apep + " " + apem + " " + nom));
            lblCod_Personal_Sol_EC.setText(String.valueOf(tb_Personal_rol_todo_EC.getValueAt(fila, 1)));

            PERSONAL_ROL_TODO.dispose();
            txtBuscarPersonal_TODO_EC.setText("");
            
//            tb_examen_det_EC.setEnabled(true);
//            tb_examen_det_EC.setBackground(Color.white);
//            tb_examen_det_EC.getSelectionModel().setSelectionInterval(0, 0);
//            tb_examen_det_EC.requestFocus();

            //            txtNumeroRegla.setEnabled(false);
            //            txtDescripcionRegla.setEnabled(false);
            //            btnmodificar.setEnabled(true);
            //            btneliminar.setEnabled(true);
        }
    }//GEN-LAST:event_tb_Personal_rol_todo_ECKeyPressed

    private void tb_examen_det_ECMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_examen_det_ECMouseClicked
        if(evt.getClickCount()==1){
            try{
                if( tb_examen_det_EC.getRowCount()>0){
                    int filaselec=tb_examen_det_EC.getSelectedRow();

                    txtCod_doc_det_EC.setText(tb_examen_det_EC.getValueAt(filaselec, 0).toString());
                    jLabel1.setText(tb_examen_det_EC.getValueAt(filaselec, 0).toString());
                    EP_CONCLUSION_EC.requestFocus();
                    EP_CONCLUSION_EC.setEnabled(true);
                    EP_Descripcion_EC.setEnabled(true);
                    btnBuscarCIE10.setEnabled(true);
                    jButton1.setEnabled(true);

                }
                
            }catch(Exception e){
//                JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
            }
        }
    }//GEN-LAST:event_tb_examen_det_ECMouseClicked

    private void tb_examen_det_ECKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_examen_det_ECKeyPressed

    }//GEN-LAST:event_tb_examen_det_ECKeyPressed

    private void tb_CIE10_ECKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CIE10_ECKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            CIE10.setVisible(false);
            mostrarDiagnostico_EC();
        }
    }//GEN-LAST:event_tb_CIE10_ECKeyPressed

    private void txtAMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAMCaretUpdate
        mostrarHospitalizacion(Integer.parseInt(txtAM.getText()));
    }//GEN-LAST:event_txtAMCaretUpdate

    private void btnPersonalSolicitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPersonalSolicitaKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                   btnPersonalSolicita.doClick();
                    
        }
    }//GEN-LAST:event_btnPersonalSolicitaKeyPressed

    private void tb_examen_det_ECFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tb_examen_det_ECFocusLost
        
    }//GEN-LAST:event_tb_examen_det_ECFocusLost

    private void tb_examen_det_ECKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_examen_det_ECKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_examen_det_ECKeyTyped

    private void tb_examen_det_ECCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tb_examen_det_ECCaretPositionChanged
        
    }//GEN-LAST:event_tb_examen_det_ECCaretPositionChanged

    private void tb_examen_det_ECPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_examen_det_ECPropertyChange
       
    }//GEN-LAST:event_tb_examen_det_ECPropertyChange

    private void tb_examen_det_ECKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_examen_det_ECKeyReleased
//       if(tb_examen_det_EC.isFocusable()){
//            try{
//                if( tb_examen_det_EC.getRowCount()>0){
//                    int filaselec=tb_examen_det_EC.getSelectedRow();
//
//                    txtCod_doc_det_EC.setText(tb_examen_det_EC.getValueAt(filaselec, 0).toString());
//
//                }
//                EP_Descripcion_EC.requestFocus();
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
//            }
//        }
    }//GEN-LAST:event_tb_examen_det_ECKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargareliminar.setBackground(new Color(255,91,70));
        Mensaje.setText("¿Está seguro que desea QUITAR el Diagnóstico ?");
        eli.setText("SI");
        noeli.setText("NO");
        eli.setVisible(true);
        noeli.setVisible(true);
        cargareliminar.setVisible(true);
        tg=3;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EP_Descripcion_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_EP_Descripcion_ECCaretUpdate
        btnGuardarDetalleRes.setEnabled(true);
    }//GEN-LAST:event_EP_Descripcion_ECCaretUpdate

    private void txtNumExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumExamenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumExamenActionPerformed

    private void btnGuardarDetalleResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDetalleResActionPerformed
        if(txtCOD_CABECERA.getText().equalsIgnoreCase("")){
            GUARDAR_RESULTADO_CABECERA_EC();   
            mostrar_VER_DETALLE_EC();
            
        }else{
           
           if(txtCodigoDoc_EC.getText().equalsIgnoreCase("") || txtPersonalRegistra_EC.getText().equalsIgnoreCase("")
                        || txtPersonalRealiza_EC.getText().equalsIgnoreCase("") || EP_CONCLUSION_EC.getText().equalsIgnoreCase("")
                        || EP_Descripcion_EC.getText().equalsIgnoreCase("") || tbCIE10_EC.getRowCount()==0){
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
    }//GEN-LAST:event_btnGuardarDetalleResActionPerformed

    private void btnRegresarRes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarRes1ActionPerformed
        try {
            this.dispose();
            EC_BUSCAR_EXAMEN_CAJA b = new EC_BUSCAR_EXAMEN_CAJA();
            b.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnRegresarRes1ActionPerformed

    private void btnGuardarCabeceraRes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCabeceraRes1ActionPerformed
        Caja_NuevaVenta F = new Caja_NuevaVenta();
        try {
            System.out.println(txtAM.getText());//actomedic
            F.reporteFUA(txtAM.getText());
            System.out.println("Imprimiendo FUA");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGuardarCabeceraRes1ActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        cargareliminar.setVisible(false);
        if(tg==1){
            RX_EC_ESTADO_RESULTADO_MODIFICAR_CAJA_EC();
            GUARDAR_RESULTADO_DETALLE_EC_CAB();
            mostrar_VER_DETALLE_EC();
            btnGuardarDetalleRes.setEnabled(false);
            EP_CONCLUSION_EC.setEnabled(false);
            EP_Descripcion_EC.setEnabled(false);
            btnBuscarCIE10.setEnabled(false);
            jButton1.setEnabled(false);
        }else if (tg==2){
                        EC_EXAMEN_CABECERA ecc = new EC_EXAMEN_CABECERA();

                        ecc.setID_DOCUMENTO(txtCodigoDoc_EC.getText());
                        ecc.setFECHA_REGISTRO(lblFechaReg_EC.getText());
                        ecc.setHORA_REGISTRO(lblHoraReg_EC.getText());
                        ecc.setNOM_USU(lblUsu_EC.getText());                                        

                        if(ecc.RX_EC_INFORME_GUARDAR()){
                            cargareliminar.setBackground(new Color(0,153,102));
                            Mensaje.setText("Resultado Guardado");
                            eli.setText("SI");
                            noeli.setText("OK");
                            eli.setVisible(false);
                            noeli.setVisible(true);
                            cargareliminar.setVisible(true);
                                txtCOD_CAB_RES.setText(ecc.RX_EC_ID_EC());
                                RX_EC_ESTADO_RESULTADO_MODIFICAR_CAJA_EC();
                                if(txtPersonalRegistra_EC.getText().length()!= 0|| 
                                        txtPersonalRealiza_EC.getText().length()!= 0 ||
                                        EP_CONCLUSION_EC.getText().length()!= 0 ||
                                        EP_Descripcion_EC.getText().length()!= 0 ){
                                try {
                                    GUARDAR_RESULTADO_DETALLE_EC();
                                   
                                } catch (JRException ex) {
                                    
                                }
 
                                } 
                                
                                btnGuardarDetalleRes.setEnabled(false);
                                EP_CONCLUSION_EC.setEnabled(false);
                                EP_Descripcion_EC.setEnabled(false);
                                btnBuscarCIE10.setEnabled(false);
                                jButton1.setEnabled(false);
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
            int filaselec=tbCIE10_EC.getSelectedRow();
            if( filaselec>=0){
                    DefaultTableModel modelo = (DefaultTableModel)tbCIE10_EC.getModel();
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

    }//GEN-LAST:event_noeliActionPerformed

    private void txtPersonalRegistra_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRegistra_ECCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRegistra_ECCaretUpdate

    private void btnPersonalRegistra_ECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalRegistra_ECActionPerformed
        PERSONAL_ROL.setVisible(true);
//        tb_Personal_rol_EC.getSelectionModel().setSelectionInterval(0, 0);
//        tb_Personal_rol_EC.requestFocus();
        txtBuscarPersonal_EC.requestFocus();
        lblPerB.setText("B2");
        try {
            buscarPersonal_EC();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPersonalRegistra_ECActionPerformed

    private void txtPersonalRealiza_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRealiza_ECCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRealiza_ECCaretUpdate

    private void btnPersonalRealiza_ECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalRealiza_ECActionPerformed
        PERSONAL_ROL.setVisible(true);
        txtBuscarPersonal_EC.requestFocus();
        lblPerB.setText("B1");
        try {
            buscarPersonal_todo_EC();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnPersonalRealiza_ECActionPerformed

    private void txtPersonalRealizaRes2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalRealizaRes2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRealizaRes2CaretUpdate

    private void btnBuscarCIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCIE10ActionPerformed
        CIE10.setVisible(true);
        tb_CIE10_EC.getSelectionModel().setSelectionInterval(0, 0);
        txt_CIE10_EC.requestFocus();
        txt_CIE10_EC.setText("");
    }//GEN-LAST:event_btnBuscarCIE10ActionPerformed

    private void txtBuscarPersonal_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPersonal_ECCaretUpdate
       buscarPersonal_EC();
    }//GEN-LAST:event_txtBuscarPersonal_ECCaretUpdate

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        buscarPersonal_EC();
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void txtBuscarPersonal_TODO_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPersonal_TODO_ECCaretUpdate
        buscarPersonal_todo_EC();
    }//GEN-LAST:event_txtBuscarPersonal_TODO_ECCaretUpdate

    private void btnBuscarCPT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT2ActionPerformed
        buscarPersonal_todo_EC();
    }//GEN-LAST:event_btnBuscarCPT2ActionPerformed

    private void txt_CIE10_ECCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_CIE10_ECCaretUpdate
       buscarDiagnostico();
    }//GEN-LAST:event_txt_CIE10_ECCaretUpdate

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed

    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    public void GUARDAR_RESULTADO_CABECERA_EC(){
        try{          
                if(txtCodigoDoc_EC.getText().equalsIgnoreCase("") || txtPersonalRegistra_EC.getText().equalsIgnoreCase("")
                        || txtPersonalRealiza_EC.getText().equalsIgnoreCase("") || EP_CONCLUSION_EC.getText().equalsIgnoreCase("")
                        || EP_Descripcion_EC.getText().equalsIgnoreCase("") || tbCIE10_EC.getRowCount()==0){
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
    
    public void GUARDAR_RESULTADO_DETALLE_EC() throws JRException{  

            int id = Integer.parseInt(txtCOD_CAB_RES.getText());
                
               EC_EXAMEN_RESULTADO_DETALLE RD = new EC_EXAMEN_RESULTADO_DETALLE();
               RD.setID_CABECERA_RESULTADO_EC(id);             
               RD.setID_COD_DOC_DET(Integer.parseInt(txtCod_doc_det_EC.getText()));
               RD.setCOD_PERSONAL_REALIZA(lblCod_Per_realiza.getText());
               RD.setNOMBRE_PERSONAL_REALIZA(txtPersonalRealiza_EC.getText());
               RD.setCOD_PERSONAL_REGISTRA(lblCod_Per_Registra.getText());
               RD.setNOMBRE_PERSONAL_REGISTRA(txtPersonalRegistra_EC.getText());
               RD.setCOD_PERSONAL_SOLICITA(lblCod_Personal_Sol_EC.getText());
               RD.setNOMBRE_PERSONAL_SOLICITA(txtPersonalSolicita_EC.getText());
               RD.setID_PREVENTA(txtId_Preventa.getText());
               RD.setHAB_NOM(txtHabitacion_EC.getText());
               RD.setCA_DESC(txtCama_EC.getText());
               RD.setHOSP_SERVICIO(lblHospiServ_EC.getText());
               RD.setDESCRIPCION_RESULTADO(EP_Descripcion_EC.getText());
               RD.setDIAGNOSTICO_RESULTADO(EP_CONCLUSION_EC.getText());
               RD.setFECHA_RESULTADO(lblFechaReg_EC.getText());
               RD.setHORA_RESULTADO(lblHoraReg_EC.getText());
               RD.setNOM_USU(lblUsu_EC.getText());
               RD.setNUMERO_EXAMEN(lblNumExamen.getText());
               
               RD.RX_EC_INFORME_DETALLE_GUARDAR();
               
               txtCOD_DETALLE_RES.setText(RD.RX_EC_ID_DETALLE_EC());
               if(tbCIE10_EC.getRowCount()!=0){
                    GUARDAR_DETALLE_DIAGNOSTICO_EC();
                    EP_Descripcion_EC.setText("");
                    EP_CONCLUSION_EC.setText("");
                    Clear_Tb_GuardarDetalle_EC();
                    mostrar_VER_DETALLE_EC();

                    Map parametros=new HashMap();
                    parametros.put("COD_DET_COD",jLabel1.getText());
                    JasperPrint informe=JasperFillManager.fillReport(getClass().
                    getResourceAsStream("/Reportes/EC/EC_RESULTADOG.jasper"), parametros,c.conectar());
                    JasperViewer ventana= new JasperViewer(informe,false);
                    ventana.setTitle("RESULTADO");
                    ventana.setVisible(true);

                    if(tb_examen_det_EC.getRowCount()==0){
                        btnRegresarRes1.doClick();
                    }
                     
               }else{
                   System.out.println("error al guardar detalle diagnostico ");
               }  
           
               EC_EXAMEN_RESULTADO_DETALLE num=new EC_EXAMEN_RESULTADO_DETALLE();
               txtNumExamen.setText(num.RX_EC_INFORME_generarNum_EC());
                  
                lblNumExamen.setText(txtNumExamen.getText());
      
                
    }
    
    public void GUARDAR_RESULTADO_DETALLE_EC_CAB(){  
        
        //int id = Integer.parseInt(txtCOD_CAB_RES.getText());
                
               EC_EXAMEN_RESULTADO_DETALLE RD = new EC_EXAMEN_RESULTADO_DETALLE();
               RD.setID_CABECERA_RESULTADO_EC(Integer.parseInt(txtCOD_CABECERA.getText()));             
               RD.setID_COD_DOC_DET(Integer.parseInt(txtCod_doc_det_EC.getText()));
               RD.setCOD_PERSONAL_REALIZA(lblCod_Per_realiza.getText());
               RD.setNOMBRE_PERSONAL_REALIZA(txtPersonalRealiza_EC.getText());
               RD.setCOD_PERSONAL_REGISTRA(lblCod_Per_Registra.getText());
               RD.setNOMBRE_PERSONAL_REGISTRA(txtPersonalRegistra_EC.getText());
               RD.setCOD_PERSONAL_SOLICITA(lblCod_Personal_Sol_EC.getText());
               RD.setNOMBRE_PERSONAL_SOLICITA(txtPersonalSolicita_EC.getText());
               RD.setID_PREVENTA(txtId_Preventa.getText());
               RD.setHAB_NOM(txtHabitacion_EC.getText());
               RD.setCA_DESC(txtCama_EC.getText());
               RD.setHOSP_SERVICIO(lblHospiServ_EC.getText());
               RD.setDESCRIPCION_RESULTADO(EP_Descripcion_EC.getText());
               RD.setDIAGNOSTICO_RESULTADO(EP_CONCLUSION_EC.getText());
               RD.setFECHA_RESULTADO(lblFechaReg_EC.getText());
               RD.setHORA_RESULTADO(lblHoraReg_EC.getText());
               RD.setNOM_USU(lblUsu_EC.getText());
               RD.setNUMERO_EXAMEN(lblNumExamen.getText());
               
               RD.RX_EC_INFORME_DETALLE_GUARDAR();
               
               txtCOD_DETALLE_RES.setText(RD.RX_EC_ID_DETALLE_EC());
               if(tbCIE10_EC.getRowCount()!=0){
                   GUARDAR_DETALLE_DIAGNOSTICO_EC();
                   EP_Descripcion_EC.setText("");
                   EP_CONCLUSION_EC.setText("");
                   Clear_Tb_GuardarDetalle_EC();
               }else{
                   System.out.println("error al guardar detalle diagnostico ");
               }  
               
               EC_EXAMEN_RESULTADO_DETALLE num=new EC_EXAMEN_RESULTADO_DETALLE();
               txtNumExamen.setText(num.RX_EC_INFORME_generarNum_EC());
                  
               lblNumExamen.setText(txtNumExamen.getText());
           
    }
    
    public void GUARDAR_DETALLE_DIAGNOSTICO_EC(){    
        
        int idD = Integer.parseInt(txtCOD_DETALLE_RES.getText());

        for (int i = 0; i < tbCIE10_EC.getRowCount(); i++){      
              EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO DD = new EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO();
               DD.setID_DETALLE_RESULTADO_EC(idD);             
               DD.setID_CIE10(Integer.parseInt(tbCIE10_EC.getValueAt(i, 0).toString()));
               DD.setNOM_USU(lblUsu_EC.getText());
               DD.RX_EC_INFORME_DETALLE_DIAGNOSTICO_GUARDAR();  
               
               mostrar_COD_CABECERA(txtCodigoDoc_EC.getText());
        }
    }
    
    public void RX_EC_ESTADO_RESULTADO_MODIFICAR_CAJA_EC(){
        
        EC_EXAMEN_RESULTADO_DETALLE ER = new EC_EXAMEN_RESULTADO_DETALLE();
        ER.EC_Estado_RESULTADO_Caja_EC(Integer.parseInt(txtCod_doc_det_EC.getText()));    
        
    }
    
    private void Clear_Tb_GuardarDetalle_EC(){
        DefaultTableModel modelo1 = (DefaultTableModel)tbCIE10_EC.getModel(); 
        int b=tbCIE10_EC.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
        }
    }
    
    public void mostrar_COD_CABECERA(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_BUSCAR_CABECERA_EC ?";
            PreparedStatement cmd = EXC.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtCOD_CABECERA.setText(r.getString(1));
                
            }
            
        } catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }
    

    public void mostrarArea_EC(){
        String consulta="";
        try {
            consulta="EXEC RX_EC_SERVICIO_EC ";
            PreparedStatement cmd = EXC.getCn().prepareStatement(consulta);        
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblIDArea_EC.setText(r.getString(1));
                lblNomA_EC.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga area : " + e.getMessage());
        }
    }
    
    
    public void CargarPersonalRol_EC(){
        try {
             String titulos[]={"Nº","Cod. Per","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio",
                 "Cod. Servicio"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[8];

        String consulta="exec RX_EC_PERSONAL_LISTAR_EC";
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
            tb_Personal_rol_EC.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            tb_Personal_rol_EC.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_EC.setModel(m1);
            formato_Personal_Rol_EC();
        } catch (Exception e) {
            cargareliminar.setBackground(new Color(255,91,70));
            Mensaje.setText("Algo salió mal");
            eli.setText("SI");
            noeli.setText("OK");
            eli.setVisible(false);
            noeli.setVisible(true);
            cargareliminar.setVisible(true);
        }
    }
    
    public void formato_Personal_Rol_EC(){
        tb_Personal_rol_EC.getColumnModel().getColumn(0).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(1).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(1).setMaxWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(2).setPreferredWidth(400);
        tb_Personal_rol_EC.getColumnModel().getColumn(3).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(3).setMaxWidth(0);  
        tb_Personal_rol_EC.getColumnModel().getColumn(4).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(5).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(5).setMaxWidth(0); 
        tb_Personal_rol_EC.getColumnModel().getColumn(6).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(6).setMaxWidth(0); 
        tb_Personal_rol_EC.getColumnModel().getColumn(7).setMinWidth(0);
        tb_Personal_rol_EC.getColumnModel().getColumn(7).setMaxWidth(0); 
    }
    
    public void buscarPersonal_EC(){
        String consulta="";
        try {
            tb_Personal_rol_EC.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Per","Médico Solicitante","Apellido Materno","Nombres","Cargo","Servicio",
                 "Cod. Servicio"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[8];

            EC_EXAMEN_CABECERA obj=new EC_EXAMEN_CABECERA();
                    consulta="exec RX_EC_PERSONAL_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarPersonal_EC.getText());
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
            tb_Personal_rol_EC.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_Personal_rol_EC.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_EC.setModel(m2);
            
            formato_Personal_Rol_EC();
            
        } catch (Exception e) {
            System.out.println("Error BUSCAR PER: " + e.getMessage());
        }
    }
    
    public void CargarPersonalRol_todo_EC(){
        try {
             String titulos[]={"Nº","Cod. Per","Apellido Paterno","Apellido Materno","Nombres","Cargo"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
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
            
                m3.addRow(fila);
                c++;
            }
            tb_Personal_rol_todo_EC.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tb_Personal_rol_todo_EC.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_todo_EC.setModel(m3);
            formato_Personal_Rol_todo_EC();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
        }
    }
    
    public void formato_Personal_Rol_todo_EC(){
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(0).setMinWidth(0);
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(0).setMaxWidth(0);
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(1).setPreferredWidth(80);
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(2).setPreferredWidth(150);
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(3).setPreferredWidth(150);  
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(4).setPreferredWidth(200); 
        tb_Personal_rol_todo_EC.getColumnModel().getColumn(5).setPreferredWidth(150); 
   }
    
    public void buscarPersonal_todo_EC(){
        String consulta="";
        try {
            tb_Personal_rol_todo_EC.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Cod. Per","Apellido Paterno","Apellido Materno","Nombres","Cargo"};
            m4=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m4);
            String fila[]=new String[6];

            EC_EXAMEN_CABECERA obj=new EC_EXAMEN_CABECERA();
                    consulta="exec RX_EC_PERSONAL_BUSCAR_TODO ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarPersonal_TODO_EC.getText());
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
            tb_Personal_rol_todo_EC.setModel(m4);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m4);
            tb_Personal_rol_todo_EC.setRowSorter(elQueOrdena);
            this.tb_Personal_rol_todo_EC.setModel(m4);
            
            formato_Personal_Rol_todo_EC();
            
        } catch (Exception e) {
            System.out.println("Error BUSCAR PER TODO: " + e.getMessage());
        }
    }
    
    public void cargarDiagnostico_EC(){
       
    try{
        DefaultTableModel tabla= new DefaultTableModel();

        tabla.addColumn("ID CIE10");
        tabla.addColumn("COD ENF");
        tabla.addColumn("DESCRIPCION");


        cst=con.prepareCall("{call RX_EC_CIE10_LISTAR}");
        r=cst.executeQuery();
        while (r.next()){
        Object dato[]=new  Object[3];
        for (int i=0; i<3; i++){
            dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tb_CIE10_EC.setModel(tabla);
       formatoCIE10_EC();
       
       }catch (Exception e){
       }
    }
    
    public void formatoCIE10_EC(){
       tb_CIE10_EC.getColumnModel().getColumn(0).setPreferredWidth(80);
       tb_CIE10_EC.getColumnModel().getColumn(1).setPreferredWidth(80);
       tb_CIE10_EC.getColumnModel().getColumn(2).setPreferredWidth(600);
       //OCULTAR
       tb_CIE10_EC.getColumnModel().getColumn(0).setMinWidth(0);
       tb_CIE10_EC.getColumnModel().getColumn(0).setMaxWidth(0);    
    }
    
    public void buscarDiagnostico(){
        String consulta="";
        try {
            tb_CIE10_EC.setModel(new DefaultTableModel());
            String titulos[]={"ID CIE10","COD ENF","DESCRIPCION"};
            m5=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m5);
            String fila[]=new String[3];

            EC_EXAMEN_CABECERA obj=new EC_EXAMEN_CABECERA();
                    consulta="exec RX_EC_CIE10_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txt_CIE10_EC.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){               
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                
                m5.addRow(fila);
                c++;
            }
            tb_CIE10_EC.setModel(m5);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m5);
            tb_CIE10_EC.setRowSorter(elQueOrdena);
            this.tb_CIE10_EC.setModel(m5);
            
            formatoCIE10_EC();
            
          
            
        } catch (Exception e) {
            System.out.println("Error buscar cie10: " + e.getMessage());
        }
    }
    
    public void mostrarDiagnostico_EC(){
        
        try {
            String ID_CIE10,COD_ENF,DESCRIPCION;
            int filaselec=tb_CIE10_EC.getSelectedRow();
            
            m6=(DefaultTableModel) tb_CIE10_EC.getModel();
            ID_CIE10=tb_CIE10_EC.getValueAt(filaselec, 0).toString();
            COD_ENF=tb_CIE10_EC.getValueAt(filaselec, 1).toString();
            DESCRIPCION=tb_CIE10_EC.getValueAt(filaselec, 2).toString();
                    
          if(tbCIE10_EC.getRowCount()==0){
              m6=(DefaultTableModel) tbCIE10_EC.getModel();
           String filaelemento[]={ID_CIE10,COD_ENF,DESCRIPCION};
              m6.addRow(filaelemento);
               formatoCIE10_O_EC();
          }
          
          else{
           if(repiteDiagnostico()==true){
               JOptionPane.showMessageDialog(rootPane,"El diagnostico ya ha sido ingresado.");   
          }
           else{
                m6=(DefaultTableModel) tbCIE10_EC.getModel();
           String filaelemento[]={ID_CIE10,COD_ENF,DESCRIPCION};
               m6.addRow(filaelemento);
               formatoCIE10_O_EC();
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error cargar: " + e);
        }
    }
    
        public boolean repiteDiagnostico(){
         int filaselec=tb_CIE10_EC.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tbCIE10_EC.getRowCount(); i++){    
               if(tbCIE10_EC.getValueAt(i, 0).toString().equalsIgnoreCase(tb_CIE10_EC.getValueAt(filaselec, 0).toString())){
                    c=true;
			}}
               return c;
     }
        
    public void formatoCIE10_O_EC(){
       tbCIE10_EC.getColumnModel().getColumn(0).setPreferredWidth(20);
       tbCIE10_EC.getColumnModel().getColumn(1).setPreferredWidth(70);
       tbCIE10_EC.getColumnModel().getColumn(2).setPreferredWidth(600);
       //OCULTAR
       tbCIE10_EC.getColumnModel().getColumn(0).setMinWidth(0);
       tbCIE10_EC.getColumnModel().getColumn(0).setMaxWidth(0);    
    }
    
    public void inicializar_tabla_cie10_EC(){       
        try {
            
            String titulosb[]={"ID CIE10","COD ENF","DESCRIPCION"};
            msb1=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb1);
            String filasb[]=new String[3];
            tbCIE10_EC.setModel(msb1);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb1);
            tbCIE10_EC.setRowSorter(elQueOrdenasb);
            tbCIE10_EC.setModel(msb1);
            
            formatoCIE10_O_EC();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }
            
    }
    
    public void mostrar_VER_DETALLE_EC(){
        try {
                    
                        //detalle
                        String consulta="";
                        tb_examen_det_EC.setModel(new DefaultTableModel());
                        String titulos[]={"Cod. Det.","Cod. Nomenclatura","Descripción Nomenclatura"};
                        m7=new DefaultTableModel(null,titulos);
                        JTable p=new JTable(m7);
                        String fila[]=new String[3];
                        Usuario obj=new Usuario();
                        consulta="exec RX_EC_VER_DETALLE_EXAMEN_EC ?";
                        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
                        cmd.setString(1, txtCodigoDoc_EC.getText());
                        ResultSet r= cmd.executeQuery();
                        int i=0, usado=0;
                        while(r.next()){
//                        for (int i=0; i<5; i++){
                        fila[0]=r.getString(1);
                        fila[1]=r.getString(2); 
                        fila[2]=r.getString(3); 
                        
                          m7.addRow(fila);
                        }
                        tb_examen_det_EC.setModel(m7);
                        TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m7);
                        tb_examen_det_EC.setRowSorter(elQueOrdena);
                        tb_examen_det_EC.setModel(m7);

                        formatoExamen_detalle_EC();           
                      
        } catch (Exception e) {
            System.out.println("Error MOSTRAR VER DETALLE ec: " + e.getMessage());
        }
        
    }
    
    public void formatoExamen_detalle_EC(){     
        tb_examen_det_EC.getColumnModel().getColumn(0).setMinWidth(0);
        tb_examen_det_EC.getColumnModel().getColumn(0).setMaxWidth(0); 
        tb_examen_det_EC.getColumnModel().getColumn(1).setMinWidth(0);
        tb_examen_det_EC.getColumnModel().getColumn(1).setMaxWidth(0); 
        tb_examen_det_EC.getColumnModel().getColumn(2).setPreferredWidth(650);   
    }
    
    public void mostrarHospitalizacion(int AM){
        String consulta="";
        try {
            consulta="EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = EXC.getCn().prepareStatement(consulta);
            cmd.setInt(1, AM);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblHospiServ_EC.setText(r.getString(5));
                txtHabitacion_EC.setText(r.getString(3));
                txtCama_EC.setText(r.getString(4));
                txtId_Preventa.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga HOSPITALIZADO: " + e.getMessage());
        }
    }
    
    public void mostrar_FP_EC(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_FP ?";
            PreparedStatement cmd = EXC.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblFP_EC.setText(r.getString(1));
                lblFUA_EC.setText(r.getString(2));
                String a = "";
                  String b = "";
                  a=String.valueOf(lblFP_EC.getText().charAt(0));
                  b=String.valueOf(lblFP_EC.getText().charAt(1));
                  a=(String.valueOf(a)+String.valueOf(b));
                  System.out.println("aa"+a);

                  if(a.equals("SI")){
                      btnGuardarCabeceraRes1.setEnabled(true);
                  }else{
                      btnGuardarCabeceraRes1.setEnabled(false);
                  }
                
            }
            
        }catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }
    
    public void RX_EC_VER_CODIGO_RES_INFORME(String cod){
        String consulta="";
        try {
            consulta="EXEC RX_EC_VER_CODIGO_RES_INFORME ?";
            PreparedStatement cmd = EXC.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                txtCOD_CABECERA.setText(r.getString(1));
                txtPersonalRealiza_EC.setText(r.getString(2));
                txtPersonalRegistra_EC.setText(r.getString(3));
                txtPersonalSolicita_EC.setText(r.getString(4));
                
            }
            
        }catch (Exception e) {
            System.out.println("Error carga cod cabecera: " + e.getMessage());
        }
    }
    
    public void deshabilitar_EC(){
        EP_CONCLUSION_EC.setEnabled(false);
        EP_Descripcion_EC.setEnabled(false);
        tb_examen_det_EC.setEnabled(false);
        tb_examen_det_EC.setBackground(Color.lightGray);
//        tbCIE10_EC.setEnabled(false);
//        tbCIE10_EC.setBackground(Color.lightGray);
        btnBuscarCIE10.setEnabled(false);
        
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
            java.util.logging.Logger.getLogger(EC_EXAMEN_CAB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EC_EXAMEN_CAB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EC_EXAMEN_CAB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EC_EXAMEN_CAB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EC_EXAMEN_CAB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog CIE10;
    private javax.swing.JEditorPane EP_CONCLUSION_EC;
    private javax.swing.JEditorPane EP_Descripcion_EC;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog PERSONAL_ROL;
    private javax.swing.JDialog PERSONAL_ROL_TODO;
    public static javax.swing.JButton btnBuscarCIE10;
    private javax.swing.JButton btnBuscarCPT;
    private javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarCPT2;
    public static javax.swing.JButton btnGuardarCabeceraRes1;
    public static javax.swing.JButton btnGuardarDetalleRes;
    public static javax.swing.JButton btnPersonalRealiza_EC;
    public static javax.swing.JButton btnPersonalRegistra_EC;
    public static javax.swing.JButton btnPersonalSolicita;
    private javax.swing.JButton btnRegresarRes1;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JButton eli;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    public static javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JLabel lblCod_Per_Registra;
    private javax.swing.JLabel lblCod_Per_realiza;
    private javax.swing.JLabel lblCod_Personal_Sol_EC;
    private javax.swing.JLabel lblFP_EC;
    private javax.swing.JLabel lblFUA_EC;
    private javax.swing.JLabel lblFechaReg_EC;
    private javax.swing.JLabel lblHoraReg_EC;
    public static javax.swing.JLabel lblHospi;
    public static javax.swing.JLabel lblHospiServ_EC;
    public static javax.swing.JLabel lblIDArea_EC;
    public static javax.swing.JLabel lblNomA_EC;
    private javax.swing.JLabel lblNumExamen;
    private javax.swing.JLabel lblPerB;
    private javax.swing.JLabel lblPerB1;
    public static javax.swing.JLabel lblUsu_EC;
    private javax.swing.JButton noeli;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JTable tbCIE10_EC;
    private javax.swing.JTable tb_CIE10_EC;
    private javax.swing.JTable tb_Personal_rol_EC;
    private javax.swing.JTable tb_Personal_rol_todo_EC;
    public static javax.swing.JTable tb_examen_det_EC;
    private javax.swing.JLabel titulo6;
    public static javax.swing.JTextField txtAM;
    public static javax.swing.JTextField txtBuscarPersonal_EC;
    public static javax.swing.JTextField txtBuscarPersonal_TODO_EC;
    public static javax.swing.JTextField txtCOD_CABECERA;
    private javax.swing.JTextField txtCOD_CAB_RES;
    private javax.swing.JTextField txtCOD_DETALLE_RES;
    public static javax.swing.JTextField txtCama_EC;
    private javax.swing.JTextField txtCod_doc_det_EC;
    public static javax.swing.JTextField txtCodigoDoc_EC;
    public static javax.swing.JTextField txtDNI;
    public static javax.swing.JLabel txtEdad;
    public static javax.swing.JLabel txtEdad1;
    public static javax.swing.JLabel txtEdad2;
    public static javax.swing.JLabel txtFechaNac;
    public static javax.swing.JLabel txtGenero;
    public static javax.swing.JTextField txtHC;
    public static javax.swing.JTextField txtHabitacion_EC;
    private javax.swing.JTextField txtId_Preventa;
    public static javax.swing.JLabel txtNombreP;
    private javax.swing.JTextField txtNumExamen;
    public static javax.swing.JTextField txtPersonalRealizaRes2;
    public static javax.swing.JTextField txtPersonalRealiza_EC;
    public static javax.swing.JTextField txtPersonalRegistra_EC;
    private javax.swing.JTextField txtPersonalSolicita_EC;
    public static javax.swing.JTextField txt_CIE10_EC;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
//            CargarPersonalRol_EC();
            
            lblHoraReg_EC.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
            }
        }
    
    }
}
