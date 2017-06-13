/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.admisionEmergencia;

import Atxy2k.CustomTextField.RestrictedTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelos.*;
import modelos.Caja.Caja_Preventa;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.admisionEmergencia.AdmisionEmergenciaTopico;
import modelos.admisionEmergencia.AdmisionEmergenciaTriaje;
import vista.admisionCentral.FrmAdmision;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtIDTriaje;
import tablas.*;
/**
 *
 * @author PC02
 */
public class FrmListFormatoEmergencia extends javax.swing.JFrame {

    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    AdmisionEmergenciaCabecera adEmerCab2 = new AdmisionEmergenciaCabecera();
    AdmisionEmergenciaTriaje adEmerTr1 = new AdmisionEmergenciaTriaje();
    AdmisionEmergenciaTopico adEmerTo = new AdmisionEmergenciaTopico();
    Caja_Preventa cp = new Caja_Preventa();
    DefaultTableModel m;
    public FrmListFormatoEmergencia() {
        initComponents();
        this.setLocationRelativeTo(null);//en el centro
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);
            cp.listarDatosEmergencia(txtBuscar.getText(), "", "", tbCabecera);
            tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
            tbCabecera.requestFocus();
            adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
            tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
            tbTriaje.requestFocus();
            adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
            tbTopico.getSelectionModel().setSelectionInterval(0, 0);
            tbTopico.requestFocus();
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
    
    public void imprimirTriaje(){
        int fila = tbTriaje.getSelectedRow();
        String ruta = "/reportes/admisionEmergencia/formatoEmergencia-Triaje.jasper";
        adEmerCab.reporteTriaje(ruta, String.valueOf(tbTriaje.getValueAt(fila, 0)));
    }
    
    public void imprimirTopico(){
        int fila = tbTopico.getSelectedRow();
        String ruta = "/reportes/admisionEmergencia/formatoEmergencia-Topico.jasper";
        adEmerTo.reporteTopico(ruta, String.valueOf(tbTopico.getValueAt(fila, 0)));
    }
    
    public void imprimirCabecera(){
        int fila = tbCabecera.getSelectedRow();
        String ruta = "/reportes/admisionEmergencia/formatoEmergencia-Cabecera.jasper";
        adEmerCab.reporteCabecera(ruta, Integer.parseInt(String.valueOf(tbCabecera.getValueAt(fila, 0))));
    }
    
    public void imprimirFormatoCompleto(){
        int fila = tbTopico.getSelectedRow();
        String ruta = "/reportes/admisionEmergencia/formatoEmergencia.jasper";
        adEmerTo.reporteTopico(ruta, String.valueOf(tbTopico.getValueAt(fila, 0)));
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jpmTriaje = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuVisualizar = new javax.swing.JMenuItem();
        jpmTopico = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jpmCabecera = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        fechai = new com.toedter.calendar.JDateChooser();
        fechaf = new com.toedter.calendar.JDateChooser();
        tbpReporteEmergencia = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCabecera = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTriaje = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTopico = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
        jMenuItem1.setText("Opciones");
        jpmTriaje.add(jMenuItem1);
        jpmTriaje.add(jSeparator1);

        mnuVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
        mnuVisualizar.setText("Imprimir");
        mnuVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVisualizarActionPerformed(evt);
            }
        });
        mnuVisualizar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mnuVisualizarKeyPressed(evt);
            }
        });
        jpmTriaje.add(mnuVisualizar);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
        jMenuItem2.setText("Opciones");
        jpmTopico.add(jMenuItem2);
        jpmTopico.add(jSeparator2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
        jMenuItem3.setText("Imprimir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jpmTopico.add(jMenuItem3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/formatoEmer16x16.png"))); // NOI18N
        jMenuItem6.setText("Imprimir Formato Completo");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jpmTopico.add(jMenuItem6);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones.png"))); // NOI18N
        jMenuItem4.setText("Opciones");
        jpmCabecera.add(jMenuItem4);
        jpmCabecera.add(jSeparator3);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
        jMenuItem5.setMnemonic('P');
        jMenuItem5.setText("Imprimir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jpmCabecera.add(jMenuItem5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admisión Emergencia .::. Lista Formato de Emergencia");

        jPanel8.setBackground(new java.awt.Color(0, 118, 168));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Admision Emergencia");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Usuario");

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setEnabled(false);
        txtBuscar.setSelectionColor(new java.awt.Color(204, 204, 204));
        txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarCaretUpdate(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Iniciar");
        btnBuscar.setToolTipText("Buscar (Alt + B)");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        fechai.setBackground(new java.awt.Color(0, 118, 168));
        fechai.setForeground(new java.awt.Color(102, 102, 102));
        fechai.setDateFormatString("dd/MM/yyyy");
        fechai.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        fechaf.setBackground(new java.awt.Color(0, 118, 168));
        fechaf.setForeground(new java.awt.Color(102, 102, 102));
        fechaf.setDateFormatString("dd/MM/yyyy");
        fechaf.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar))
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(titulo5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUsuUsuario))
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpReporteEmergencia.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        tbCabecera = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbCabecera.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbCabecera.setForeground(new java.awt.Color(102, 102, 102));
        tbCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCabecera.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbCabecera.setSelectionBackground(new java.awt.Color(0, 118, 168));
        tbCabecera.getTableHeader().setReorderingAllowed(false);
        tbCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbCabeceraMouseReleased(evt);
            }
        });
        tbCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCabeceraKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCabecera);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpReporteEmergencia.addTab("Cabecera", jPanel1);

        tbTriaje = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbTriaje.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbTriaje.setForeground(new java.awt.Color(102, 102, 102));
        tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTriaje.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbTriaje.setSelectionBackground(new java.awt.Color(0, 118, 168));
        tbTriaje.getTableHeader().setReorderingAllowed(false);
        tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbTriajeMouseReleased(evt);
            }
        });
        tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTriajeKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbTriaje);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        tbpReporteEmergencia.addTab("Triaje", jPanel2);

        tbTopico = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbTopico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbTopico.setForeground(new java.awt.Color(102, 102, 102));
        tbTopico.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTopico.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbTopico.setSelectionBackground(new java.awt.Color(0, 118, 168));
        tbTopico.getTableHeader().setReorderingAllowed(false);
        tbTopico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbTopicoMouseReleased(evt);
            }
        });
        tbTopico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTopicoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbTopico);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1175, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        tbpReporteEmergencia.addTab("Tópico", jPanel3);

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel36.setText("Inactivo (Alt + D)");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
        jLabel42.setText("Buscar (Alt + B)");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel43.setText("Activo (Alt + A)");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel44.setText("Todos (Alt + D)");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel46.setText("Salir (ESC)");

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 5)); // NOI18N
        btnImprimir.setMnemonic('P');
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        btnImprimir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnImprimirKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/imprimir16x16.png"))); // NOI18N
        jLabel1.setText("Imprimir (Alt + P)");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel42)
                .addGap(15, 15, 15)
                .addComponent(jLabel43)
                .addGap(15, 15, 15)
                .addComponent(jLabel36)
                .addGap(15, 15, 15)
                .addComponent(jLabel44)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel36)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel1))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(btnImprimir))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbpReporteEmergencia)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpReporteEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
   
        if(tbpReporteEmergencia.getSelectedIndex()==0){
            cp.listarDatosEmergencia(txtBuscar.getText(), determinarFecha(fechai),determinarFecha(fechaf), tbCabecera);
        }else
        if(tbpReporteEmergencia.getSelectedIndex()==1){
            adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, determinarFecha(fechai),determinarFecha(fechaf));
        }else
        if(tbpReporteEmergencia.getSelectedIndex()==2){
            adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, determinarFecha(fechai),determinarFecha(fechaf));
        }
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(tbpReporteEmergencia.getSelectedIndex()==0){
            if(btnBuscar.getText().equals("Iniciar")){
                if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                cp.listarDatosEmergencia(txtBuscar.getText(), determinarFecha(fechai),determinarFecha(fechaf), tbCabecera);
                btnBuscar.setText("Detener");
                txtBuscar.setEnabled(true);
                tbpReporteEmergencia.setEnabledAt(0, true);
                tbpReporteEmergencia.setEnabledAt(1, false);
                tbpReporteEmergencia.setEnabledAt(2, false);
            } else {
                cp.listarDatosEmergencia(txtBuscar.getText(), "","", tbCabecera);
                btnBuscar.setText("Iniciar");
                txtBuscar.setEnabled(false);
                tbpReporteEmergencia.setEnabledAt(0, true);
                tbpReporteEmergencia.setEnabledAt(1, true);
                tbpReporteEmergencia.setEnabledAt(2, true);
            }
        }else
        if(tbpReporteEmergencia.getSelectedIndex()==1){
            if(btnBuscar.getText().equals("Iniciar")){
            if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, determinarFecha(fechai),determinarFecha(fechaf));
                btnBuscar.setText("Detener");
                txtBuscar.setEnabled(true);
                tbpReporteEmergencia.setEnabledAt(0, false);
                tbpReporteEmergencia.setEnabledAt(1, true);
                tbpReporteEmergencia.setEnabledAt(2, false);
            } else {
                adEmerTr1.admisionEmergenciaTriajeListarReporte(txtBuscar.getText(), tbTriaje, "","");
                btnBuscar.setText("Iniciar");
                txtBuscar.setEnabled(false);
                tbpReporteEmergencia.setEnabledAt(0, true);
                tbpReporteEmergencia.setEnabledAt(1, true);
                tbpReporteEmergencia.setEnabledAt(2, true);
            }
        }else
        if(tbpReporteEmergencia.getSelectedIndex()==2){
            if(btnBuscar.getText().equals("Iniciar")){
            if(fechai.getDate()==null || fechai.getDate()==null){
                    JOptionPane.showMessageDialog(this, "Ingrese un rango de fechas");
                }
                adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, determinarFecha(fechai),determinarFecha(fechaf));
                btnBuscar.setText("Detener");
                txtBuscar.setEnabled(true);
                tbpReporteEmergencia.setEnabledAt(0, false);
                tbpReporteEmergencia.setEnabledAt(1, false);
                tbpReporteEmergencia.setEnabledAt(2, true);
            } else {
                adEmerTo.admisionEmergenciaTopicoReporteFinal(txtBuscar.getText(), tbTopico, "","");
                btnBuscar.setText("Iniciar");
                txtBuscar.setEnabled(false);
                tbpReporteEmergencia.setEnabledAt(0, true);
                tbpReporteEmergencia.setEnabledAt(1, true);
                tbpReporteEmergencia.setEnabledAt(2, true);
            }
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(tbpReporteEmergencia.getSelectedIndex()==0){
            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbCabecera.getSelectionModel().setSelectionInterval(0, 0);
                tbCabecera.requestFocus();
            }
        }
        if(tbpReporteEmergencia.getSelectedIndex()==1){
            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbTriaje.getSelectionModel().setSelectionInterval(0, 0);
                tbTriaje.requestFocus();
            }
        }
        if(tbpReporteEmergencia.getSelectedIndex()==2){
            if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                tbTopico.getSelectionModel().setSelectionInterval(0, 0);
                tbTopico.requestFocus();
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbTriaje.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_tbTriajeKeyPressed

    private void tbTopicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTopicoKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbTopico.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_tbTopicoKeyPressed

    private void tbTriajeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseReleased
        int fila = tbTriaje.getSelectedRow();
        if(fila == -1)
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        else{
            if(evt.isPopupTrigger()){
                jpmTriaje.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbTriajeMouseReleased

    private void mnuVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVisualizarActionPerformed
        //VISUALIZAR REPORTE DE TRIAJE!!!!!
        imprimirTriaje();
    }//GEN-LAST:event_mnuVisualizarActionPerformed

    private void mnuVisualizarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mnuVisualizarKeyPressed

    }//GEN-LAST:event_mnuVisualizarKeyPressed

    private void btnImprimirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnImprimirKeyPressed
        
    }//GEN-LAST:event_btnImprimirKeyPressed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if(tbpReporteEmergencia.getSelectedIndex()==0){
            int fila = tbCabecera.getSelectedRow();
            if(fila == -1)
                JOptionPane.showMessageDialog(this, "Seleccione una fila");
            else
                imprimirCabecera();
        }else
        if(tbpReporteEmergencia.getSelectedIndex()==1){
            int fila = tbTriaje.getSelectedRow();
            if(fila == -1)
                JOptionPane.showMessageDialog(this, "Seleccione una fila");
            else
                imprimirTriaje();
        }
        else
        if(tbpReporteEmergencia.getSelectedIndex()==2){
            int fila = tbTopico.getSelectedRow();
            if(fila == -1)
                JOptionPane.showMessageDialog(this, "Seleccione una fila");
            else
                imprimirTopico();
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void tbTopicoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTopicoMouseReleased
        int fila = tbTopico.getSelectedRow();
        if(fila == -1)
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        else{
            if(evt.isPopupTrigger()){
                jpmTopico.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbTopicoMouseReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        imprimirTopico();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        imprimirCabecera();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        imprimirFormatoCompleto();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void tbCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCabecera.getSelectedRow()==0){
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_tbCabeceraKeyPressed

    private void tbCabeceraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseReleased
        int fila = tbCabecera.getSelectedRow();
        if(fila == -1)
        JOptionPane.showMessageDialog(this, "Seleccione una fila");
        else{
            if(evt.isPopupTrigger()){
                jpmCabecera.show(evt.getComponent(),evt.getX(),evt.getY());
            }
        }
    }//GEN-LAST:event_tbCabeceraMouseReleased

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
            java.util.logging.Logger.getLogger(FrmListFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmListFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmListFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmListFormatoEmergencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmListFormatoEmergencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu jpmCabecera;
    private javax.swing.JPopupMenu jpmTopico;
    private javax.swing.JPopupMenu jpmTriaje;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JMenuItem mnuVisualizar;
    private javax.swing.JTable tbCabecera;
    public static javax.swing.JTable tbTopico;
    public static javax.swing.JTable tbTriaje;
    public static javax.swing.JTabbedPane tbpReporteEmergencia;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
