/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.admisionCentral;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.HeadlessException;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
        chkHoy.setSelected(false);
        //Mostrar fecha
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        //ASIGNAR COLOR A CADA FILA SEGUN ESTADO
        // mostrar datos en la tabla tbMvimientoHC
        movHC.mostrar_MovHC(2,tbMovimientoHC,"Retorno","","","","","","");
        //Combobox seleccionado en retorno
        cbxMovimiento.setSelectedItem("Retorno");
        btnAtajoVI.setMnemonic(KeyEvent.VK_F2);
        btnAtajoII.setMnemonic(KeyEvent.VK_F3);
        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
        tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
        tbMovimientoHC.requestFocus();
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
    
    public void habilitarOpciones(boolean opcion){
        cbxMovimiento.setEnabled(opcion);
        dtFechI.setEnabled(opcion);
        dtFechaFinal1.setEnabled(opcion);
        txtServicio.setEnabled(opcion);
        txtConsultorio.setEnabled(opcion);
        txtTurno.setEnabled(opcion);
        chkTodosArea.setEnabled(opcion);
        chkTodosConsultorio.setEnabled(opcion);
        chkTodosTurno.setEnabled(opcion);
        cbxEstadoM.setEnabled(opcion);
        chkTodosEstado.setEnabled(opcion);
    }
    
    public void limpiarDatos(){
        cbxMovimiento.setSelectedIndex(0);
        dtFechI.setDate(null);
        dtFechaFinal1.setDate(null);
        txtServicio.setText("");
        txtConsultorio.setText("");
        txtTurno.setText("");
        cbxEstadoM.setSelectedIndex(0);
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
        String estMovimiento = cbxMovimiento.getSelectedItem().toString();
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
            if(cbxMovimiento.getSelectedIndex()==4)
                movHC.mostrar_MovHC(1,tbMovimientoHC,estMovimiento,determinarFecha(dtFechI),determinarFecha(dtFechaFinal1),"","","","");
            else
                movHC.mostrar_MovHC(2,tbMovimientoHC,estMovimiento,determinarFecha(dtFechI),determinarFecha(dtFechaFinal1),"","","","");
            tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC()); 
        }
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
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        btnDetener = new javax.swing.JButton();
        btnActualizarTabla = new javax.swing.JButton();
        btnActualizarEstado = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxMovimiento = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtFechI = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        chkTodosArea = new javax.swing.JCheckBox();
        txtServicio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chkTodosConsultorio = new javax.swing.JCheckBox();
        txtConsultorio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        chkTodosTurno = new javax.swing.JCheckBox();
        lblD = new javax.swing.JLabel();
        cbxEstadoM = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        chkTodosEstado = new javax.swing.JCheckBox();
        dtFechaFinal1 = new com.toedter.calendar.JDateChooser();
        btnAtajoII = new javax.swing.JButton();
        btnAtajoVI = new javax.swing.JButton();
        chkHoy = new javax.swing.JCheckBox();

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

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("00:00:00");

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hora:");

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fecha:");

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("00/00/00");

        btnDetener.setBackground(new java.awt.Color(255, 255, 255));
        btnDetener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/detener.png"))); // NOI18N
        btnDetener.setMnemonic('D');
        btnDetener.setAlignmentY(0.0F);
        btnDetener.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDetener.setBorderPainted(false);
        btnDetener.setContentAreaFilled(false);
        btnDetener.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });

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

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Nombre");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnDetener, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 563, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHora, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnActualizarEstado, btnActualizarTabla});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHora)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFecha))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(lblUsuUsuario)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnDetener, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(btnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 44, 44))))
        );

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel13.setText("Salir (ESC)");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/pdf.png"))); // NOI18N
        jLabel12.setText("Exportar a PDF (Alt+P)");

        lblDetener.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Stop-16.png"))); // NOI18N
        lblDetener.setText("Detener (Alt+D)");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
        jLabel11.setText("Actualizar (Alt+E)");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Actualizar-16.png"))); // NOI18N
        jLabel10.setText("Actualizar (Alt+A)");

        lblContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Play-16.png"))); // NOI18N
        lblContinuar.setText("Continuar (Alt+C)");

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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDetener)
                    .addComponent(lblContinuar)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        });
        jScrollPane2.setViewportView(tbMovimientoHC);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Estado de Movi");

        cbxMovimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Pendiente", "Salida", "Retorno", "Todos" }));
        cbxMovimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxMovimiento.setEnabled(false);
        cbxMovimiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMovimientoItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Fecha Inicial");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Fecha Final");

        dtFechI.setBackground(new java.awt.Color(255, 255, 255));
        dtFechI.setDateFormatString("dd-MM-yyyy");
        dtFechI.setEnabled(false);
        dtFechI.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Area/Servicio/Dpto");

        chkTodosArea.setBackground(new java.awt.Color(255, 255, 255));
        chkTodosArea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkTodosArea.setSelected(true);
        chkTodosArea.setText("Todos");
        chkTodosArea.setEnabled(false);
        chkTodosArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTodosAreaItemStateChanged(evt);
            }
        });
        chkTodosArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTodosAreaActionPerformed(evt);
            }
        });

        txtServicio.setEnabled(false);
        txtServicio.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtServicioCaretUpdate(evt);
            }
        });
        txtServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtServicioActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Consultorio");

        chkTodosConsultorio.setBackground(new java.awt.Color(255, 255, 255));
        chkTodosConsultorio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkTodosConsultorio.setSelected(true);
        chkTodosConsultorio.setText("T");
        chkTodosConsultorio.setEnabled(false);
        chkTodosConsultorio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTodosConsultorioItemStateChanged(evt);
            }
        });
        chkTodosConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTodosConsultorioActionPerformed(evt);
            }
        });

        txtConsultorio.setEnabled(false);
        txtConsultorio.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConsultorioCaretUpdate(evt);
            }
        });
        txtConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsultorioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Turno");

        txtTurno.setEnabled(false);
        txtTurno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTurnoCaretUpdate(evt);
            }
        });

        chkTodosTurno.setBackground(new java.awt.Color(255, 255, 255));
        chkTodosTurno.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkTodosTurno.setSelected(true);
        chkTodosTurno.setText("T");
        chkTodosTurno.setEnabled(false);
        chkTodosTurno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTodosTurnoItemStateChanged(evt);
            }
        });
        chkTodosTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTodosTurnoActionPerformed(evt);
            }
        });

        lblD.setForeground(new java.awt.Color(255, 255, 255));
        lblD.setText("D");

        cbxEstadoM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));
        cbxEstadoM.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Estado");

        chkTodosEstado.setBackground(new java.awt.Color(255, 255, 255));
        chkTodosEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkTodosEstado.setSelected(true);
        chkTodosEstado.setText("T");
        chkTodosEstado.setEnabled(false);
        chkTodosEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTodosEstadoItemStateChanged(evt);
            }
        });

        dtFechaFinal1.setBackground(new java.awt.Color(255, 255, 255));
        dtFechaFinal1.setDateFormatString("dd-MM-yyyy");
        dtFechaFinal1.setEnabled(false);
        dtFechaFinal1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        btnAtajoII.setBorderPainted(false);
        btnAtajoII.setContentAreaFilled(false);
        btnAtajoII.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoIIActionPerformed(evt);
            }
        });

        btnAtajoVI.setBorderPainted(false);
        btnAtajoVI.setContentAreaFilled(false);
        btnAtajoVI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtajoVIActionPerformed(evt);
            }
        });

        chkHoy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chkHoy.setForeground(new java.awt.Color(51, 51, 51));
        chkHoy.setText("Pendientes de Hoy");
        chkHoy.setContentAreaFilled(false);
        chkHoy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkHoy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHoyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxMovimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dtFechI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(dtFechaFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTodosArea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTodosConsultorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(42, 42, 42)
                        .addComponent(chkTodosTurno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTodosEstado))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxEstadoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(64, 64, 64)
                        .addComponent(btnAtajoII)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtajoVI))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(chkHoy)))
                .addGap(18, 18, 18)
                .addComponent(lblD))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(chkTodosArea)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(chkTodosConsultorio)
                            .addComponent(jLabel7)
                            .addComponent(chkTodosTurno)
                            .addComponent(jLabel8)
                            .addComponent(chkTodosEstado))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxEstadoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTurno, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtConsultorio, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtServicio, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtFechaFinal1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtFechI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxMovimiento, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblD)
                        .addComponent(btnAtajoII)
                        .addComponent(btnAtajoVI))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(chkHoy)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void chkTodosAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTodosAreaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTodosAreaActionPerformed

    private void chkTodosTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTodosTurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTodosTurnoActionPerformed

    private void txtConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsultorioActionPerformed
        
    }//GEN-LAST:event_txtConsultorioActionPerformed

    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        ImageIcon continuar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/continuar.png")); 
        ImageIcon detener=new ImageIcon(this.getClass().getResource("/imagenes/iconos/detener.png")); 
        //Detener
        if(evt.getSource() == btnDetener && lblD.getText().equals("D")){
            lblD.setText("C");
            btnDetener.setIcon(continuar);
            btnVisualizar.setEnabled(false);
            habilitarOpciones(true);
            tbMovimientoHC.removeAll();
            movHC.cabecera(tbMovimientoHC);
            limpiarDatos();
            chkTodosEstado.setEnabled(true);
            cbxEstadoM.setEnabled(false);
            chkTodosEstado.setSelected(true);
            chkTodosArea.setSelected(false);
            chkTodosConsultorio.setSelected(false);
            chkTodosTurno.setSelected(false);
            btnActualizarEstado.setEnabled(false);
            btnActualizarTabla.setEnabled(false);
            btnDetener.setMnemonic(KeyEvent.VK_C);
            if(chkTodosArea.isSelected()==false)
                txtServicio.setEnabled(true);
            else
                txtServicio.setEnabled(false);
            if(chkTodosConsultorio.isSelected()==false)
                txtConsultorio.setEnabled(true);
            else
                txtConsultorio.setEnabled(false);
            if(chkTodosTurno.isSelected()==false)
                txtTurno.setEnabled(true);
            else
                txtTurno.setEnabled(false);
        } else 
            //Continuar
        if(lblD.getText().equals("C")){
            if(!chkHoy.isSelected()){
                if(cbxMovimiento.getSelectedIndex() == 0 || dtFechI.getDate() == null || dtFechaFinal1.getDate() == null){
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un estado de Movimiento \ne ingresar un rango de fechas");
                } else 
                if(cbxMovimiento.getSelectedIndex() > 0){
                    if(chkTodosArea.isSelected()==false && txtServicio.getText().equals("") || 
                       chkTodosConsultorio.isSelected()==false && txtConsultorio.getText().equals("") ||
                       chkTodosTurno.isSelected()==false && txtTurno.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Debe marcar las casillas o ingresar los datos");
                    } else {
                        String estadoM = cbxMovimiento.getSelectedItem().toString();
                        String servicio = txtServicio.getText();
                        String consultorio = txtConsultorio.getText();
                        String turno = txtTurno.getText();
                        String estado = "";
                        String fechI = "";
                        String fechF = "";
                        if(chkTodosEstado.isSelected()==false){
                            if(cbxEstadoM.getSelectedIndex()==0){
                                estado = "A";
                            } else {
                                estado = "D";
                            }
                        } else {
                            estado = "";
                        }
                        if(dtFechI.getDate() == null || dtFechaFinal1.getDate() == null){
                            fechI = "";
                            fechF = "";
                        } else {
                            fechI = determinarFecha(dtFechI);
                            fechF = determinarFecha(dtFechaFinal1);
                        }
                        if(chkTodosEstado.isSelected()){
                            estado = "";
                        }
                        if(cbxMovimiento.getSelectedIndex()==4){
                            movHC.mostrar_MovHC(1, tbMovimientoHC,"",fechI,fechF,servicio,estado,consultorio,turno);
                            //tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
                        } else {
                            movHC.mostrar_MovHC(2, tbMovimientoHC,estadoM,fechI,fechF,servicio,estado,consultorio,turno);
                        }
                        btnDetener.setMnemonic(KeyEvent.VK_D);
                        lblD.setText("D");
                        btnDetener.setIcon(detener);
                        btnVisualizar.setEnabled(true);
                        habilitarOpciones(false);
                        btnActualizarTabla.setEnabled(true);
                        tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
                        txtServicio.setEnabled(false);
                        txtConsultorio.setEnabled(false);
                        btnActualizarEstado.setEnabled(true);
                    }
                }
            // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
            tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
            tbMovimientoHC.requestFocus();
            }else{//SOLO MUESTRA LOS DE HOY
                if(cbxMovimiento.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un estado de Movimiento");
                }else{
                    String estadoM = cbxMovimiento.getSelectedItem().toString();
                    movHC.mostrar_MovHC(2, tbMovimientoHC,estadoM,"","","","","","");
                }
            }
        }
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void chkTodosAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTodosAreaItemStateChanged
        if(chkTodosArea.isSelected()){
            txtServicio.setEnabled(false);
        } else 
            txtServicio.setEnabled(true);
    }//GEN-LAST:event_chkTodosAreaItemStateChanged

    private void chkTodosConsultorioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTodosConsultorioItemStateChanged
        if(chkTodosConsultorio.isSelected()){
            txtConsultorio.setEnabled(false);
        } else 
            txtConsultorio.setEnabled(true);
    }//GEN-LAST:event_chkTodosConsultorioItemStateChanged

    private void chkTodosTurnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTodosTurnoItemStateChanged
        if(chkTodosTurno.isSelected()){
            txtTurno.setEnabled(false);
        } else 
            txtTurno.setEnabled(true);
    }//GEN-LAST:event_chkTodosTurnoItemStateChanged

    private void chkTodosEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTodosEstadoItemStateChanged
        if(chkTodosEstado.isSelected()){
            cbxEstadoM.setEnabled(false);
        } else {
            cbxEstadoM.setEnabled(true);
        }
    }//GEN-LAST:event_chkTodosEstadoItemStateChanged

    private void txtServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtServicioActionPerformed
        
    }//GEN-LAST:event_txtServicioActionPerformed

    private void txtServicioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtServicioCaretUpdate
        if(txtServicio.isEnabled()==true){
            if(txtServicio.getText().equals(""))
                chkTodosArea.setEnabled(true);
            else
                chkTodosArea.setEnabled(false);
        }
    }//GEN-LAST:event_txtServicioCaretUpdate

    private void tbMovimientoHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMovimientoHCKeyPressed
        /*String estMovimiento = cbxMovimiento.getSelectedItem().toString();
        
        MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
        String estadoM = String.valueOf(tbMovimientoHC.getValueAt(fila, 7));
        String codigo = String.valueOf(tbMovimientoHC.getValueAt(fila, 2));
        String codigo2 = String.valueOf(codigo.charAt(0)) + String.valueOf(codigo.charAt(1)) + 
                String.valueOf(codigo.charAt(2)) + String.valueOf(codigo.charAt(3)) + 
                String.valueOf(codigo.charAt(5)) + String.valueOf(codigo.charAt(6));
        if(movH.actualizarEstadoMovHC(estadoM,codigo2)==true)
            JOptionPane.showMessageDialog(this, estadoM + " " +codigo2);
        else 
            JOptionPane.showMessageDialog(this, "NO");
        movHC.mostrar_MovHC(2,tbMovimientoHC,estMovimiento,"","","","");*/
    }//GEN-LAST:event_tbMovimientoHCKeyPressed

    private void cbxMovimientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMovimientoItemStateChanged
        /*if(cbxMovimiento.getSelectedIndex()==2){
            tbMovimientoHC.setDefaultRenderer(Object.class,new FormatoTablaMovHC());
        } else {
            tbMovimientoHC.setDefaultRenderer(Object.class,new FormTablaMovHCSC());
        }*/
    }//GEN-LAST:event_cbxMovimientoItemStateChanged

    private void btnActualizarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEstadoActionPerformed
        //Cambio de estado mediante ENTER + COMBOBOX --------------------------------------
        actualizarEstadoMov();
        // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
        tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
        tbMovimientoHC.requestFocus();
        // --------------------------------------------------------------------------------
    }//GEN-LAST:event_btnActualizarEstadoActionPerformed

    private void tbMovimientoHCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMovimientoHCMouseClicked
        btnActualizarEstado.setEnabled(true);
    }//GEN-LAST:event_tbMovimientoHCMouseClicked

    private void btnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaActionPerformed
        if(chkHoy.isSelected()){
            movHC.mostrar_MovHC(3,tbMovimientoHC,"Pendiente","","","","","","");
        }else{
            String estadoM = cbxMovimiento.getSelectedItem().toString();
            String servicio = txtServicio.getText();
            String consultorio = txtConsultorio.getText();
            String turno = txtTurno.getText();
                        String estado = "";
                        String fechI = "";
                        String fechF = "";
                        if(chkTodosEstado.isSelected()==false){
                            if(cbxEstadoM.getSelectedIndex()==0){
                                estado = "A";
                            } else {
                                estado = "D";
                            }
                        } else {
                            estado = "";
                        }
                        if(dtFechI.getDate() == null || dtFechaFinal1.getDate() == null){
                            fechI = "";
                            fechF = "";
                        } else {
                            fechI = determinarFecha(dtFechI);
                            fechF = determinarFecha(dtFechaFinal1);
                        }
                        if(chkTodosEstado.isSelected()){
                            estado = "";
                        }
            if(cbxMovimiento.getSelectedIndex()==4)
                movHC.mostrar_MovHC(1,tbMovimientoHC,estadoM,fechI,fechF,servicio,estado,consultorio,turno);
            else
                movHC.mostrar_MovHC(2,tbMovimientoHC,estadoM,fechI,fechF,servicio,estado,consultorio,turno);
            // DESLIZAR CON LAS FLECHAS DENTRO DE LA TABLA
            tbMovimientoHC.getSelectionModel().setSelectionInterval(0,0);
            tbMovimientoHC.requestFocus();
        }
    }//GEN-LAST:event_btnActualizarTablaActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        
    }//GEN-LAST:event_formMouseReleased

    private void btnVisualizarIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarIActionPerformed
     int fila = tbMovimientoHC.getSelectedRow();
        try {
            int id = Integer.parseInt(String.valueOf(tbMovimientoHC.getValueAt(fila, 0)));
            String rutaInforme = "src\\Reportes\\admisionCentral\\report1.jasper";
            Map parametros = new HashMap();
//            parametros.put("id", id);
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
//            JasperViewer ventanavisor = new JasperViewer(informe, false);
//            ventanavisor.setTitle("Movimiento Historia Clínica a Detalle");
//            ventanavisor.setVisible(true);
//            PrintService impresora = "EPSON TM-T88V Receipt";
             
            //Archivo que se desea imprimir
    FileInputStream inputStream = new FileInputStream("src\\Reportes\\admisionCentral\\ticket.jasper");
 
    //Formato de Documento
    DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
    //Lectura de Documento
    Doc document = new SimpleDoc(inputStream, docFormat, null);
 
    //Nombre de la impresora
    String printerName = "EPSON TM-T88V Receipt";
 
    //Inclusion del nombre de impresora y sus atributos
//    AttributeSet attributeSet = new HashAttributeSet();
//    attributeSet.add(new PrinterName(printerName, null));
//    attributeSet = new HashAttributeSet();
//    //Soporte de color o no
//    attributeSet.add(ColorSupported.NOT_SUPPORTED);
 
    //Busqueda de la impresora por el nombre asignado en attributeSet
    PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
    for(int i=0; i < services.length; i++){

            if(services[i].getName().equals(printerName)){

            try {
            JOptionPane.showMessageDialog(null, "Imprimiendo orden de servicio en " + services[i].getName(), "IMPRESIÓN", JOptionPane.INFORMATION_MESSAGE);
            FileInputStream fis = new FileInputStream("src\\Reportes\\admisionCentral\\ticket.jasper");
            Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null); 
        
            DocPrintJob printJob = services[i].createPrintJob();
            printJob.print(pdfDoc, new HashPrintRequestAttributeSet()); 

            } catch (PrintException e) {
            JOptionPane.showMessageDialog(null, e);
            }
            catch (FileNotFoundException e) {
            e.printStackTrace();
            }
            }
    }


// 
////    System.out.println("Imprimiendo en : " + services[0].getName());
// 
//    DocPrintJob printJob = services[0].createPrintJob();
//    //Envio a la impresora
//            try {
//                printJob.print(document,null);
//            } catch (Exception e) {
//            }
// 
//    inputStream.close();
//            
//            
  
            
//            JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
//                    jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, informe );
//                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, services[0].createPrintJob());
//                    jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
//                    jrprintServiceExporter.exportReport();
            JasperPrintManager.printReport(informe, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: _btnVisualizarDetalle" + e.toString());
            }
    }//GEN-LAST:event_btnVisualizarIActionPerformed

    private void tbMovimientoHCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMovimientoHCMouseReleased
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(),evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_tbMovimientoHCMouseReleased

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        try {
        String estadoM = cbxMovimiento.getSelectedItem().toString();
        String servicio = txtServicio.getText();
        String consultorio = txtConsultorio.getText();
        String turno = txtTurno.getText();
        String estado = "";
        String fechI = "";
        String fechF = "";
        //
        int tipo=0;
        if(chkTodosEstado.isSelected()==false){
            if(cbxEstadoM.getSelectedIndex()==0){
                estado = "A";
            } else {
                estado = "D";
            }
        } else {
            estado = "";
        }
        if(dtFechI.getDate() == null || dtFechaFinal1.getDate() == null){
            fechI = "";
            fechF = "";
        } else {
            fechI = determinarFecha(dtFechI);
            fechF = determinarFecha(dtFechaFinal1);
        }
        if(chkTodosEstado.isSelected()){
            estado = "";
        }
        if(cbxMovimiento.getSelectedIndex()==4)
            tipo = 1;
        else
            tipo = 2;
            String rutaInforme = "src\\reportes\\admisionCentral\\mov.jasper";
            Map parametros = new HashMap();
            parametros.put("tipo", tipo);
            parametros.put("estadoM", estadoM);
            parametros.put("fechaI", fechI);
            parametros.put("fechaF", fechF);
            parametros.put("area", servicio);
            parametros.put("estado", estado);
            parametros.put("turno",turno);
            parametros.put("consultorio",consultorio);
            if(tipo == 1)
                parametros.put("estadot", "Todos");
            else
                parametros.put("estadot", "");
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, parametros, c.conectar());
            // VER EN EL JASPERREPORT
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Movimiento Historia Clínica");
            ventanavisor.setVisible(true);
                       
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: _btnVisualizar" + e.toString());
        }
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void txtConsultorioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsultorioCaretUpdate
        if(txtConsultorio.isEnabled()==true){
            if(txtConsultorio.getText().equals(""))
                chkTodosConsultorio.setEnabled(true);
            else
                chkTodosConsultorio.setEnabled(false);
        }
    }//GEN-LAST:event_txtConsultorioCaretUpdate

    private void txtTurnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTurnoCaretUpdate
        if(txtTurno.isEnabled()==true){
            if(txtTurno.getText().equals(""))
                chkTodosTurno.setEnabled(true);
            else
                chkTodosTurno.setEnabled(false);
        }
    }//GEN-LAST:event_txtTurnoCaretUpdate

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

    private void chkTodosConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTodosConsultorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTodosConsultorioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void chkHoyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHoyActionPerformed
        if(chkHoy.isSelected()){//SE ACTIVA BUSQUEDA DE HOY 'PENDIENTES'
            btnDetener.setEnabled(false);
            cbxMovimiento.setEnabled(false);
            dtFechI.setEnabled(false);
            dtFechaFinal1.setEnabled(false);
            txtConsultorio.setEnabled(false);
            txtServicio.setEnabled(false);
            txtTurno.setEnabled(false);
            chkTodosArea.setEnabled(false);
            chkTodosConsultorio.setEnabled(false);
            chkTodosEstado.setEnabled(false);
            chkTodosTurno.setEnabled(false);
            cbxEstadoM.setEnabled(false);
            btnActualizarTabla.setEnabled(true);
            btnVisualizar.setEnabled(false);
        } else {
            btnVisualizar.setEnabled(true);
            btnDetener.setEnabled(true);
            if(lblD.getText().equals("C")){
                cbxMovimiento.setEnabled(true);
                dtFechI.setEnabled(true);
                dtFechaFinal1.setEnabled(true);
                txtConsultorio.setEnabled(true);
                txtServicio.setEnabled(true);
                txtTurno.setEnabled(true);
                chkTodosArea.setEnabled(true);
                chkTodosConsultorio.setEnabled(true);
                chkTodosEstado.setEnabled(true);
                chkTodosTurno.setEnabled(true);
                cbxEstadoM.setEnabled(true);
                btnActualizarTabla.setEnabled(true);
            } else {
                movHC.mostrar_MovHC(2,tbMovimientoHC,"Retorno","","","","","","");
                cbxMovimiento.setEnabled(false);
                dtFechI.setEnabled(false);
                dtFechaFinal1.setEnabled(false);
                txtConsultorio.setEnabled(false);
                txtServicio.setEnabled(false);
                txtTurno.setEnabled(false);
                chkTodosArea.setEnabled(false);
                chkTodosConsultorio.setEnabled(false);
                chkTodosEstado.setEnabled(false);
                chkTodosTurno.setEnabled(false);
                cbxEstadoM.setEnabled(false);
                btnActualizarTabla.setEnabled(false);
            }
        }
    }//GEN-LAST:event_chkHoyActionPerformed
    
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHora.setText(hora + ":" + minutos + " " + ampm);
            try {
                Thread.sleep(10000);
                // mostrar datos en la tabla tbMvimientoHC
                if(chkHoy.isSelected()){
                    movHC.mostrar_MovHC(3,tbMovimientoHC,"Pendiente","","","","","","");
                }
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
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnAtajoII;
    private javax.swing.JButton btnAtajoVI;
    private javax.swing.JButton btnDetener;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JMenuItem btnVisualizarI;
    private javax.swing.JComboBox cbxEstadoM;
    private javax.swing.JComboBox cbxMovimiento;
    private javax.swing.JComboBox cbxTipoBusquedaNHC;
    private javax.swing.JCheckBox chkHoy;
    private javax.swing.JCheckBox chkTodosArea;
    private javax.swing.JCheckBox chkTodosConsultorio;
    private javax.swing.JCheckBox chkTodosEstado;
    private javax.swing.JCheckBox chkTodosTurno;
    private com.toedter.calendar.JDateChooser dtFechI;
    private com.toedter.calendar.JDateChooser dtFechaFinal1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lblContinuar;
    private javax.swing.JLabel lblD;
    private javax.swing.JLabel lblDetener;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JTable tbBuscarNHC;
    private javax.swing.JTable tbMovimientoHC;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBusquedaNHC;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables

}
