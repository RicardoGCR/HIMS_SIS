/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.admisionCentral;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.HeadlessException;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.admisionCentral.HistoriaClinica;
import modelos.admisionCentral.MovimientoHistoriaClinica;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import tablas.FormTablaMovHCSC;
import tablas.FormatoTMovSalida;
import tablas.FormatoTablaHC;
import tablas.FormatoTablaMovHC;
import static vista.Caja.Caja_Pagos.addEscapeListenerWindowDialog;
import static vista.admisionCentral.FrmNuevaHistoriaC.btnEliminar;
import static vista.admisionCentral.FrmNuevaHistoriaC.btnModificar;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDireccion;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxTipoDireccion;
import static vista.admisionCentral.FrmNuevaHistoriaC.rbtFemenino;
import static vista.admisionCentral.FrmNuevaHistoriaC.rbtMasculino;
import static vista.Principal.fechaActual;

/**
 *
 * @author Yamila
 */
public class FrmMovimientoHC extends javax.swing.JFrame implements Runnable {
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
    Conexion c=new Conexion();
    ResultSet r;
    MovimientoHistoriaClinica movHC = new MovimientoHistoriaClinica();
    DefaultTableModel m;
    PreparedStatement pstm;
    public FrmMovimientoHC() {
        initComponents();
        this.getContentPane().setBackground(Color.WHITE);//fondo blanco
        setLocationRelativeTo(null);//en el centro
        //Mostrar fecha
        h1 = new Thread(this);
        this.setExtendedState(MAXIMIZED_BOTH);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        //ASIGNAR COLOR A CADA FILA SEGUN ESTADO
        // mostrar datos en la tabla tbMvimientoHC
        txthc.requestFocus();
        btnAtajoVI.setMnemonic(KeyEvent.VK_F2);
        btnAtajoII.setMnemonic(KeyEvent.VK_F3);
        movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
        tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
         tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
        tbMovimientoHC.requestFocus();
        addEscapeListenerWindowDialog(jListascliente);
        addWindowListener( 
                new java.awt.event.WindowAdapter() { 
                    public void windowClosing(java.awt.event.WindowEvent e ) { 
                        setVisible(false); 
                    } 
                } 
        );
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
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
//        chkTodosArea.setVisible(false);
        lblD.setVisible(true);
        btnActualizarEstado.setEnabled(true);
        btnActualizarTabla.setEnabled(true);
        txthc.requestFocus();
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

    //Calcular hora
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
    
   
    
    public String determinarFecha(JDateChooser calendario){
        String fecha = "";
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio);
        return fecha;
    }
    
    
    public void actualizarEstadoMov(){
        int fila = tbMovimientoHC.getSelectedRow();
        String estadoAnt = String.valueOf(tbMovimientoHC.getValueAt(fila, 13));
        int id = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
//        int acto_medico = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 1)));
//        String fechai = String.valueOf(tbMovimientoHC.getValueAt(fila, 1));
        if(estadoAnt.equals("P"))
            estadoAnt = "Pendiente";
        else if(estadoAnt.equals("S"))
            estadoAnt = "Salida";
        else 
            estadoAnt = "Retorno";
        MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
        String estadoM = String.valueOf(tbMovimientoHC.getValueAt(fila, 12));
        String codigo = String.valueOf(tbMovimientoHC.getValueAt(fila, 4));
//        String codigo2 = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + 
//                String.valueOf(codigo.charAt(2)) + String.valueOf(codigo.charAt(3)) + 
//                String.valueOf(codigo.charAt(5)) + String.valueOf(codigo.charAt(6));
        String usuario = lblUsuUsuario.getText();
        int actualizarEst = JOptionPane.showConfirmDialog(this, "¿Desea realizar el cambio de estado?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(actualizarEst==0){
            if(movH.actualizarEstadoMovHC(estadoM,id,usuario)==true)
                JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " + estadoAnt + " --> " + estadoM);
            else 
                JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " + estadoAnt + " --> " + estadoM);
            tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC()); 
        }
        movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
        tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        tbMovimientoHC.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmBuscarNHC = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBuscarNHC = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        cbxTipoBusquedaNHC = new javax.swing.JComboBox();
        txtBusquedaNHC = new javax.swing.JTextField();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        btnVisualizarI = new javax.swing.JMenuItem();
        jListascliente = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        btnActualizarEstado1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        btnActualizarTabla = new javax.swing.JButton();
        btnActualizarEstado = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        lblUsuUsuario = new javax.swing.JLabel();
        btnAtajoVI = new javax.swing.JButton();
        btnAtajoII = new javax.swing.JButton();
        lblD = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txthc = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblDetener = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblContinuar = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMovimientoHC = new javax.swing.JTable();

        FrmBuscarNHC.setMinimumSize(new java.awt.Dimension(550, 650));

        tbBuscarNHC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbBuscarNHC.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBuscarNHC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbBuscarNHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBuscarNHCKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbBuscarNHC);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Citas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cbxTipoBusquedaNHC.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        cbxTipoBusquedaNHC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "N° Historia", "DNI", "Apellidos", "Nombres" }));
        cbxTipoBusquedaNHC.setFocusable(false);
        cbxTipoBusquedaNHC.setLightWeightPopupEnabled(false);
        cbxTipoBusquedaNHC.setOpaque(false);
        cbxTipoBusquedaNHC.setVerifyInputWhenFocusTarget(false);
        cbxTipoBusquedaNHC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoBusquedaNHCItemStateChanged(evt);
            }
        });

        txtBusquedaNHC.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        txtBusquedaNHC.setEnabled(false);
        txtBusquedaNHC.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBusquedaNHCCaretUpdate(evt);
            }
        });
        txtBusquedaNHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaNHCKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cbxTipoBusquedaNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(txtBusquedaNHC, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoBusquedaNHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaNHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout FrmBuscarNHCLayout = new javax.swing.GroupLayout(FrmBuscarNHC.getContentPane());
        FrmBuscarNHC.getContentPane().setLayout(FrmBuscarNHCLayout);
        FrmBuscarNHCLayout.setHorizontalGroup(
            FrmBuscarNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmBuscarNHCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FrmBuscarNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FrmBuscarNHCLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                .addContainerGap())
        );
        FrmBuscarNHCLayout.setVerticalGroup(
            FrmBuscarNHCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FrmBuscarNHCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
        jMenuItem1.setText("Opciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);
        jPopupMenu1.add(jSeparator2);

        btnVisualizarI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVisualizarI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
        btnVisualizarI.setText("Imprimir");
        btnVisualizarI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarIActionPerformed(evt);
            }
        });
        jPopupMenu1.add(btnVisualizarI);

        jListascliente.setMaximumSize(new java.awt.Dimension(875, 300));
        jListascliente.setMinimumSize(new java.awt.Dimension(875, 300));
        jListascliente.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jListasclienteWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                jListasclienteWindowClosed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo6.setBackground(new java.awt.Color(153, 0, 51));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo6.setForeground(new java.awt.Color(255, 255, 255));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo6.setText("Movimiento Historias Clínicas");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnActualizarEstado1.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizarEstado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnActualizarEstado1.setMnemonic('E');
        btnActualizarEstado1.setBorderPainted(false);
        btnActualizarEstado1.setContentAreaFilled(false);
        btnActualizarEstado1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEstado1.setFocusPainted(false);
        btnActualizarEstado1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizarEstado1.setMaximumSize(new java.awt.Dimension(49, 25));
        btnActualizarEstado1.setMinimumSize(new java.awt.Dimension(49, 25));
        btnActualizarEstado1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnActualizarEstado1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizarEstado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEstado1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnActualizarEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnActualizarEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jListasclienteLayout = new javax.swing.GroupLayout(jListascliente.getContentPane());
        jListascliente.getContentPane().setLayout(jListasclienteLayout);
        jListasclienteLayout.setHorizontalGroup(
            jListasclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        jListasclienteLayout.setVerticalGroup(
            jListasclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jListasclienteLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admisión .::. Movimiento Historia Clínica");
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(23, 160, 134));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Movimiento Historias Clínicas");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnActualizarTabla.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/actualizar.png"))); // NOI18N
        btnActualizarTabla.setMnemonic('A');
        btnActualizarTabla.setToolTipText("");
        btnActualizarTabla.setBorderPainted(false);
        btnActualizarTabla.setContentAreaFilled(false);
        btnActualizarTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarTabla.setEnabled(false);
        btnActualizarTabla.setFocusPainted(false);
        btnActualizarTabla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizarTabla.setMaximumSize(new java.awt.Dimension(49, 25));
        btnActualizarTabla.setMinimumSize(new java.awt.Dimension(49, 25));
        btnActualizarTabla.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnActualizarTabla.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaActionPerformed(evt);
            }
        });

        btnActualizarEstado.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizarEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnActualizarEstado.setMnemonic('E');
        btnActualizarEstado.setBorderPainted(false);
        btnActualizarEstado.setContentAreaFilled(false);
        btnActualizarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEstado.setEnabled(false);
        btnActualizarEstado.setFocusPainted(false);
        btnActualizarEstado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizarEstado.setMaximumSize(new java.awt.Dimension(49, 25));
        btnActualizarEstado.setMinimumSize(new java.awt.Dimension(49, 25));
        btnActualizarEstado.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnActualizarEstado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEstadoActionPerformed(evt);
            }
        });

        btnVisualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/PDF-32.png"))); // NOI18N
        btnVisualizar.setMnemonic('V');
        btnVisualizar.setBorderPainted(false);
        btnVisualizar.setContentAreaFilled(false);
        btnVisualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVisualizar.setFocusPainted(false);
        btnVisualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVisualizar.setMaximumSize(new java.awt.Dimension(49, 25));
        btnVisualizar.setMinimumSize(new java.awt.Dimension(49, 25));
        btnVisualizar.setPreferredSize(new java.awt.Dimension(49, 25));
        btnVisualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVisualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        lblUsuUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblUsuUsuario.setText("Usuario");

        btnAtajoVI.setBorderPainted(false);
        btnAtajoVI.setContentAreaFilled(false);
        btnAtajoVI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoVIActionPerformed(evt);
            }
        });

        btnAtajoII.setBorderPainted(false);
        btnAtajoII.setContentAreaFilled(false);
        btnAtajoII.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoIIActionPerformed(evt);
            }
        });

        lblD.setForeground(new java.awt.Color(255, 255, 255));
        lblD.setText("D");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("HC");

        txthc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txthc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txthcCaretUpdate(evt);
            }
        });
        txthc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txthcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txthcFocusLost(evt);
            }
        });
        txthc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthcActionPerformed(evt);
            }
        });
        txthc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txthcKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(btnAtajoII, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(lblD, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAtajoVI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizarEstado, btnActualizarTabla});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUsuUsuario))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAtajoII)
                                    .addComponent(btnAtajoVI))
                                .addGap(0, 0, 0)
                                .addComponent(lblD)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnActualizarTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addComponent(btnVisualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addComponent(btnActualizarEstado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(196, 196, 196));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel13.setText("Salir (ESC)");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/pdf.png"))); // NOI18N
        jLabel12.setText("Exportar a PDF (Alt+P)");

        lblDetener.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDetener.setForeground(new java.awt.Color(51, 51, 51));
        lblDetener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Stop-16.png"))); // NOI18N
        lblDetener.setText("Detener (Alt+D)");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
        jLabel11.setText("Actualizar (Alt+E)");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Actualizar-16.png"))); // NOI18N
        jLabel10.setText("Actualizar (Alt+A)");

        lblContinuar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblContinuar.setForeground(new java.awt.Color(51, 51, 51));
        lblContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Play-16.png"))); // NOI18N
        lblContinuar.setText("Continuar (Alt+C)");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Visualizar-16.png"))); // NOI18N
        jLabel16.setText("Visualizar (Alt+V)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblDetener)
                .addGap(3, 3, 3)
                .addComponent(lblContinuar)
                .addGap(3, 3, 3)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jLabel11)
                .addGap(3, 3, 3)
                .addComponent(jLabel16)
                .addGap(3, 3, 3)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDetener)
                    .addComponent(lblContinuar)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tbMovimientoHC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                if(colIndex==12)
                return true;
                else
                return false;
            }
        };
        tbMovimientoHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbMovimientoHC.setForeground(new java.awt.Color(51, 51, 51));
        tbMovimientoHC.setModel(new javax.swing.table.DefaultTableModel(
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
        tbMovimientoHC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbMovimientoHC.setGridColor(new java.awt.Color(153, 0, 153));
        tbMovimientoHC.setShowHorizontalLines(false);
        tbMovimientoHC.setShowVerticalLines(false);
        tbMovimientoHC.getTableHeader().setReorderingAllowed(false);
        tbMovimientoHC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMovimientoHCMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbMovimientoHCMouseReleased(evt);
            }
        });
        tbMovimientoHC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMovimientoHCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbMovimientoHCKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbMovimientoHC);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1308, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoBusquedaNHCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaNHCItemStateChanged
        //Seleccionar tipo de bbubsqueda
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxTipoBusquedaNHC.getSelectedIndex()>0){
                    txtBusquedaNHC.setEnabled(true);
                    txtBusquedaNHC.setText("");
                }
            }
            else{
                txtBusquedaNHC.setEnabled(false);
            }}
        catch(Exception ex){
            System.out.println("Error_tipoBusquedaNHC:" + ex.getMessage());
        }
    }//GEN-LAST:event_cbxTipoBusquedaNHCItemStateChanged

    private void txtBusquedaNHCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBusquedaNHCCaretUpdate
      
    }//GEN-LAST:event_txtBusquedaNHCCaretUpdate

    private void txtBusquedaNHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaNHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbBuscarNHC.getSelectionModel().setSelectionInterval(0, 0);
            tbBuscarNHC.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaNHCKeyPressed

    private void tbBuscarNHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBuscarNHCKeyPressed
       /*
       OBTENER HC  
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbBuscarNHC.getSelectedRow();
            FrmBuscarNHC.setVisible(false);
            FrmMovimientoHC.txtNumeroHC.setText(String.valueOf(tbBuscarNHC.getValueAt(fila, 0)));  
       }
        */
    }//GEN-LAST:event_tbBuscarNHCKeyPressed

    private void tbMovimientoHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMovimientoHCKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
        int fila = tbMovimientoHC.getSelectedRow();
            MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
            String codigo = String.valueOf(tbMovimientoHC.getValueAt(fila, 15)); 
            String barra = String.valueOf(tbMovimientoHC.getValueAt(fila, 16));
            System.out.println(barra.length());    
            int opc=0;
            if (barra.length()!=0){
                int actualizarEst = JOptionPane.showConfirmDialog(this, "El Historial clinico ya tiene codigo de barras",
                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(actualizarEst==1){
                    System.out.println(actualizarEst);      
                    System.out.println(actualizarEst);
                    opc=1;
                }
                else{
                    System.out.println(actualizarEst);
                    movH.reporteCODHIST(codigo);
                }
            }
            if (opc==0){
                System.out.println("12");
                movH.reporteCODHIST(codigo);
                }
        }
         movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
        tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
         tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        tbMovimientoHC.requestFocus();
    }//GEN-LAST:event_tbMovimientoHCKeyPressed

    private void tbMovimientoHCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMovimientoHCMouseClicked
        btnActualizarEstado.setEnabled(true);
    }//GEN-LAST:event_tbMovimientoHCMouseClicked

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        
    }//GEN-LAST:event_formMouseReleased

    private void tbMovimientoHCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMovimientoHCMouseReleased
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_tbMovimientoHCMouseReleased
        String txt="";
        int num = 0 ;
        int can=0;  
        int pos=0;
       
    private void txthcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthcKeyPressed
        
    }//GEN-LAST:event_txthcKeyPressed

    private void txthcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txthcFocusLost
//      
    }//GEN-LAST:event_txthcFocusLost

    private void btnAtajoIIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtajoIIActionPerformed
        int fila = tbMovimientoHC.getSelectedRow();
        int acto_medico = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
        String ruta = "/Reportes-HistoriaClinica/movimientoDetalle.jasper";
        String file = "";
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {

                    printwriter.print(ruta);
                }
                String rutaInforme = ruta;
                movHC.reportesPDFDetalle(ruta, PATH, acto_medico);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo

                }
                JOptionPane.showMessageDialog(null, "Esto puede tardar unos segundos,espere porfavor", "Estamos Generando el Reporte", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);

                Runtime.getRuntime().exec("cmd /c start "+PATH + ".pdf");

            }
        } catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(MovimientoHistoriaClinica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAtajoIIActionPerformed

    private void btnAtajoVIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtajoVIActionPerformed
        int fila = tbMovimientoHC.getSelectedRow();
        try {
            int acto_medico = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
            String rutaInforme = "src\\Reportes-HistoriaClinica\\movimientoDetalle.jasper";
            Map parametros = new HashMap();
            parametros.put("acto_medico", acto_medico);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Movimiento Historia Clínica a Detalle");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: _btnVisualizarDetalle" + e.toString());
        }
    }//GEN-LAST:event_btnAtajoVIActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        try {
            String estado = "";
            String fechI = "";
            String fechF = "";
            //
            Map parametros = new HashMap();
            parametros.put("estadot", "Todos");
            JasperPrint informe=JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/admisionCentral/mov.jasper"), parametros,c.conectar());
            // VER EN EL JASPERREPORT
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Movimiento Historia Clínica");
            ventanavisor.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: _btnVisualizar" + e.toString());
        }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnActualizarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEstadoActionPerformed
        //Cambio de estado mediante ENTER + COMBOBOX --------------------------------------
        actualizarEstadoMov();
        // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
        tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
        tbMovimientoHC.requestFocus();
        // --------------------------------------------------------------------------------
    }//GEN-LAST:event_btnActualizarEstadoActionPerformed

    private void btnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaActionPerformed
        movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
        tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
         tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        tbMovimientoHC.requestFocus();
    }//GEN-LAST:event_btnActualizarTablaActionPerformed

    private void tbMovimientoHCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMovimientoHCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMovimientoHCKeyReleased
    public void edit(){
         int fila = jTable1.getSelectedRow();
        String estadoAnt = String.valueOf(jTable1.getValueAt(fila, 13));
        int id = Integer.parseInt(String.valueOf(jTable1.getValueAt(fila, 0)));
//        int acto_medico = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 1)));
//        String fechai = String.valueOf(tbMovimientoHC.getValueAt(fila, 1));
        if(estadoAnt.equals("P"))
            estadoAnt = "Pendiente";
        else if(estadoAnt.equals("S"))
            estadoAnt = "Salida";
        else 
            estadoAnt = "Retorno";
        MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
        String estadoM = String.valueOf(jTable1.getValueAt(fila, 12));
        String codigo = String.valueOf(jTable1.getValueAt(fila, 4));
//        String codigo2 = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + 
//                String.valueOf(codigo.charAt(2)) + String.valueOf(codigo.charAt(3)) + 
//                String.valueOf(codigo.charAt(5)) + String.valueOf(codigo.charAt(6));
        String usuario = lblUsuUsuario.getText();
        int actualizarEst = JOptionPane.showConfirmDialog(this, "¿Desea realizar el cambio de estado?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(actualizarEst==0){
            if(movH.actualizarEstadoMovHC(estadoM,id,usuario)==true)
                JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " + estadoAnt + " --> " + estadoM);
            else 
                JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " + estadoAnt + " --> " + estadoM);
            jTable1.setDefaultRenderer(Object.class,new FormatoTablaMovHC()); 
        }
        // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
         movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
        tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
         tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
        tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
        System.out.println("dni"+dni);
        movHC.mostrar_MovHC(3,jTable1,"Todos","","","","","",dni);
       jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(10).setMinWidth(0);
        jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(11).setMinWidth(0);
        jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(14).setMinWidth(0);
        jTable1.getColumnModel().getColumn(14).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(15).setMinWidth(0);
        jTable1.getColumnModel().getColumn(15).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(16).setMinWidth(0);
        jTable1.getColumnModel().getColumn(16).setMaxWidth(0);
       jTable1.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
    }
    private void btnActualizarEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEstado1ActionPerformed
     edit();
    }//GEN-LAST:event_btnActualizarEstado1ActionPerformed

    private void txthcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthcActionPerformed
        String dni="";
        int h=0;
    private void txthcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txthcCaretUpdate
        txt=txthc.getText().trim();
        num=txt.length();
        h=1;
        System.out.println(txt); 
        System.out.println(num);    
        System.out.println(txthc.getText().trim());
        if (num>7){
            for (int i = 0; i < tbMovimientoHC.getRowCount(); i++) {
                 String codigo = String.valueOf(tbMovimientoHC.getValueAt(i, 15)); 
                if(codigo.equals(txt)){
                   can+=1;
                   pos=i;
                }else{
                    System.out.println("El número no existe");
                }
            }
             System.out.println(can);
             if (can==(1)){
                  int fila = pos;
                    String estadoAnt = String.valueOf(tbMovimientoHC.getValueAt(fila, 13));
                    int id = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
                    if(estadoAnt.equals("P"))
                        estadoAnt = "Salida";
                    else if(estadoAnt.equals("S"))
                        estadoAnt = "Retorno";
                    else 
                        estadoAnt = "Retorno";
                    MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
                    String estadoM = String.valueOf(tbMovimientoHC.getValueAt(fila, 12));
                    String codigo = String.valueOf(tbMovimientoHC.getValueAt(fila, 4));
                    String usuario = lblUsuUsuario.getText();
                    int actualizarEst = JOptionPane.showConfirmDialog(this, "¿Desea realizar el cambio de estado de la H.C. Nº " + codigo + " ? \n De " +  estadoM  + " --> " +estadoAnt,
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(actualizarEst==0){
                        if(movH.actualizarEstadoMovHC(estadoAnt,id,usuario)==true)
                            JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " +  estadoM  + " --> " +estadoAnt);
                        else 
                            JOptionPane.showMessageDialog(this, "N° H.C. " + codigo + "\n" + "Estado: " + estadoM  + " --> " +estadoAnt);
                        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC()); 
                    }
                    movHC.mostrar_MovHC(3,tbMovimientoHC,"Todos","","","","","","");
                    tbMovimientoHC.getColumnModel().getColumn(15).setMinWidth(0);
                    tbMovimientoHC.getColumnModel().getColumn(15).setMaxWidth(0);
                    tbMovimientoHC.getColumnModel().getColumn(16).setMinWidth(0);
                    tbMovimientoHC.getColumnModel().getColumn(16).setMaxWidth(0);
                    tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
                    txthc.requestFocus();
                    txt="";
                    h=0;
                    can=0;
             }else if (can>(1)){
                    jListascliente.setVisible(true);
                    movHC.mostrar_MovHC(3,jTable1,"Todos","","","","","",txt);
                    jTable1.getColumnModel().getColumn(0).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
                    jTable1.getColumnModel().getColumn(1).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(1).setMaxWidth(0);
                    jTable1.getColumnModel().getColumn(10).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
                     jTable1.getColumnModel().getColumn(11).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
                     jTable1.getColumnModel().getColumn(14).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(14).setMaxWidth(0);
                    jTable1.getColumnModel().getColumn(15).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(15).setMaxWidth(0);
                    jTable1.getColumnModel().getColumn(16).setMinWidth(0);
                    jTable1.getColumnModel().getColumn(16).setMaxWidth(0);
                    jTable1.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
                    jTable1.requestFocus();
                     dni=txt;
             }
            num=0;
        }
    }//GEN-LAST:event_txthcCaretUpdate

    private void jListasclienteWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jListasclienteWindowActivated
       txthc.setText("");  
       
    }//GEN-LAST:event_jListasclienteWindowActivated

    private void jListasclienteWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jListasclienteWindowClosed
        txthc.requestFocus();
        txt="";
    }//GEN-LAST:event_jListasclienteWindowClosed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        int fila = 0;
        if(teclaPresionada==KeyEvent.VK_ENTER){
            edit();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void btnVisualizarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarIActionPerformed
        int fila = tbMovimientoHC.getSelectedRow();
        try {
            int id = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
            Map parametros = new HashMap();
            parametros.put("id", id);
            JasperPrint informe=JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/admisionCentral/movimientoDetalle.jasper"), parametros,c.conectar());
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Movimiento Historia Clínica a Detalle");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: _btnVisualizarDetalle" + e.toString());
        }
    }//GEN-LAST:event_btnVisualizarIActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txthcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txthcFocusGained
        if (h==0){
            txthc.setText("");
        }
    }//GEN-LAST:event_txthcFocusGained
    
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            try {
                Thread.sleep(10000);
                // mostrar datos en la tabla tbMvimientoHC
//                if(chkHoy.isSelected()){
//                    movHC.mostrar_MovHC(3,tbMovimientoHC,"Pendiente","","","","","","");
//                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
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
            java.util.logging.Logger.getLogger(FrmMovimientoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMovimientoHC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMovimientoHC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FrmBuscarNHC;
    private javax.swing.JButton btnActualizarEstado;
    private javax.swing.JButton btnActualizarEstado1;
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnAtajoII;
    private javax.swing.JButton btnAtajoVI;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JMenuItem btnVisualizarI;
    private javax.swing.JComboBox cbxTipoBusquedaNHC;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JDialog jListascliente;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblContinuar;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblDetener;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JTable tbBuscarNHC;
    private javax.swing.JTable tbMovimientoHC;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JTextField txtBusquedaNHC;
    private javax.swing.JTextField txthc;
    // End of variables declaration//GEN-END:variables

}
