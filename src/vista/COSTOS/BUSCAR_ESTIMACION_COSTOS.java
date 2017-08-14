/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.COSTOS;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Reportes.Reportes;
import modelos.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import static vista.COSTOS.Costos_Sustentacion.*;
import static vista.COSTOS.Costos_Sustentacion.formatoInicializarTabla;


/**
 *
 * @author Pc03
 */
public class BUSCAR_ESTIMACION_COSTOS extends javax.swing.JFrame {
 Conexion conectar=new Conexion();
 Connection con;
 CallableStatement cst;
 ResultSet r;
 Statement st;
 DefaultTableModel m, m1,m2,m3,msa,msg,msi,msb,mi,cs,modelo4,modelo5;
    /**
     * Creates new form BUSCAR_ESTIMACION_COSTOS
     */
    public BUSCAR_ESTIMACION_COSTOS() {
        initComponents();
        
        con=conectar.conectar();
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        cargarEstimacionCostos();
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    }
    
    
    public void cargarEstimacionCostos(){
       
    try{
       DefaultTableModel tabla= new DefaultTableModel();
      
       tabla.addColumn("Codigo");
       tabla.addColumn("Codigo Precio");
       tabla.addColumn("Codigo CPT");
       tabla.addColumn("Servicio");
       tabla.addColumn("Area");
       tabla.addColumn("Forma de Pago");
       tabla.addColumn("Precio");
       tabla.addColumn("Tiempo(h)");
       tabla.addColumn("Tiempo(Min)");
       tabla.addColumn("Saldo");
       tabla.addColumn("Nomenclatura");
 
       
       cst=con.prepareCall("{call COSTOS_COSTOS_SUSTENTACION_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[11];
       for (int i=0; i<11; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tb_Buscar_Estimacion.setModel(tabla);
       formatoEstimacionCostos();
       
       }catch (Exception e){
       }
      
    }

    public void formatoEstimacionCostos(){
        
    tb_Buscar_Estimacion.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Buscar_Estimacion.getColumnModel().getColumn(2).setPreferredWidth(70);
    tb_Buscar_Estimacion.getColumnModel().getColumn(3).setPreferredWidth(120);
    tb_Buscar_Estimacion.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Buscar_Estimacion.getColumnModel().getColumn(5).setPreferredWidth(120);
    tb_Buscar_Estimacion.getColumnModel().getColumn(6).setPreferredWidth(60);
    tb_Buscar_Estimacion.getColumnModel().getColumn(7).setPreferredWidth(80);
    tb_Buscar_Estimacion.getColumnModel().getColumn(8).setPreferredWidth(90);
    tb_Buscar_Estimacion.getColumnModel().getColumn(9).setPreferredWidth(60);
    tb_Buscar_Estimacion.getColumnModel().getColumn(10).setPreferredWidth(200);
    //Ocultar    
    tb_Buscar_Estimacion.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Buscar_Estimacion.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Buscar_Estimacion.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Buscar_Estimacion.getColumnModel().getColumn(1).setMaxWidth(0);
    
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        REPORTES = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        TODO_REPORTE = new javax.swing.JMenuItem();
        TODO_PDF = new javax.swing.JMenuItem();
        TODO_EXCEL = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        DETALLE_REPORTE = new javax.swing.JMenuItem();
        DETALLE_PDF = new javax.swing.JMenuItem();
        DETALLE_EXCEL = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Buscar_Estimacion = new javax.swing.JTable();
        jpanel = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        cbxBuscarEstimacion = new javax.swing.JComboBox();
        txtBuscarEstimacion = new javax.swing.JTextField();
        lblUsu = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lbldia = new javax.swing.JLabel();

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/reporte16x16.png"))); // NOI18N
        jMenuItem5.setText("REPORTES");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        REPORTES.add(jMenuItem5);
        REPORTES.add(jSeparator1);

        jMenu1.setText("ESTIMACIÓN DE COSTOS");

        TODO_REPORTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Visualizar-16.png"))); // NOI18N
        TODO_REPORTE.setText("VISUALIZAR");
        TODO_REPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TODO_REPORTEActionPerformed(evt);
            }
        });
        jMenu1.add(TODO_REPORTE);

        TODO_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/pdf.png"))); // NOI18N
        TODO_PDF.setText("EXPORTAR A PDF");
        TODO_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TODO_PDFActionPerformed(evt);
            }
        });
        jMenu1.add(TODO_PDF);

        TODO_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/excel16x16.png"))); // NOI18N
        TODO_EXCEL.setText("EXPORTAR A EXCEL");
        TODO_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TODO_EXCELActionPerformed(evt);
            }
        });
        jMenu1.add(TODO_EXCEL);

        REPORTES.add(jMenu1);

        jMenu2.setText("SUSTENTACIÓN DEL COSTO");

        DETALLE_REPORTE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Visualizar-16.png"))); // NOI18N
        DETALLE_REPORTE.setText("VISUALIZAR");
        DETALLE_REPORTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DETALLE_REPORTEActionPerformed(evt);
            }
        });
        jMenu2.add(DETALLE_REPORTE);

        DETALLE_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/pdf.png"))); // NOI18N
        DETALLE_PDF.setText("EXPORTAR A PDF");
        DETALLE_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DETALLE_PDFActionPerformed(evt);
            }
        });
        jMenu2.add(DETALLE_PDF);

        DETALLE_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/excel16x16.png"))); // NOI18N
        DETALLE_EXCEL.setText("EXPORTAR A EXCEL");
        DETALLE_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DETALLE_EXCELActionPerformed(evt);
            }
        });
        jMenu2.add(DETALLE_EXCEL);

        REPORTES.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(0, 0));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Buscar_Estimacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Buscar_Estimacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tb_Buscar_Estimacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Buscar_Estimacion.setComponentPopupMenu(REPORTES);
        tb_Buscar_Estimacion.setRowHeight(24);
        tb_Buscar_Estimacion.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Buscar_Estimacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Buscar_EstimacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Buscar_Estimacion);

        jpanel.setBackground(new java.awt.Color(102, 102, 102));

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText(" Búsqueda por:");

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("<html>Reporte Costos<span style=\"font-size:'14px'\"><br>CPT</br></span></html>");

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-32.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Buscar (Alt-B)");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar.setIconTextGap(30);
        btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cbxBuscarEstimacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxBuscarEstimacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Codigo CPT", "Servicio", "Nomenclatura " }));
        cbxBuscarEstimacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBuscarEstimacionItemStateChanged(evt);
            }
        });
        cbxBuscarEstimacion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxBuscarEstimacionPropertyChange(evt);
            }
        });

        txtBuscarEstimacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtBuscarEstimacion.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarEstimacionCaretUpdate(evt);
            }
        });
        txtBuscarEstimacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarEstimacionKeyPressed(evt);
            }
        });

        lblUsu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("Usuario");

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxBuscarEstimacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                .addComponent(txtBuscarEstimacion)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxBuscarEstimacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtBuscarEstimacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(43, 43, 43));
        jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

        lbldia.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        lbldia.setForeground(new java.awt.Color(255, 255, 255));
        lbldia.setText("Búsqueda y Reporte de CPT");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbldia, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lbldia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 819, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        getAccessibleContext().setAccessibleName("Búsqueda de CPT");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_Buscar_EstimacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Buscar_EstimacionKeyPressed
        try {
            modelo4 = (DefaultTableModel) tb_Buscar_Estimacion.getModel();
        char tecla= evt.getKeyChar();
        
        if(tecla==KeyEvent.VK_ENTER ){
            
            Costos_Sustentacion c=new Costos_Sustentacion();
            c.setVisible(true);
            
            mostrarCabecerayDetalle();
            dispose();
            btnBuscarCPT.setEnabled(false);
            btnGrabar.setEnabled(false);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);
            spHora.setEnabled(false);
            spMin.setEnabled(false);
            //Personal
            tbPersonal.setEnabled(false);
            tbPersonal.setBackground(Color.lightGray);
            btnAgregarPersonal.setEnabled(false);
            btnQuitarPersonal.setEnabled(false);
            //Insumos 
            tbInsumo.setEnabled(false);
            tbInsumo.setBackground(Color.lightGray);
            btnAgregarInsumo.setEnabled(false);
            btnQuitarInsumo.setEnabled(false);
            //Servicios Basicos
            tbServiciosBasicos.setEnabled(false);
            tbServiciosBasicos.setBackground(Color.lightGray);
            btnAgregarSBasicos.setEnabled(false);
            btnQuitarSBasicos.setEnabled(false);
            //Herramienta
            tbHerramienta.setEnabled(false);
            tbHerramienta.setBackground(Color.lightGray);
            btnAgregarHerramientas.setEnabled(false);
            btnQuitarHerramienta.setEnabled(false);
            //Infra
            tbInfraestructura.setEnabled(false);
            tbInfraestructura.setBackground(Color.lightGray);
            btnAgregarInfraestruc.setEnabled(false);
            btnQuitarInfraes.setEnabled(false);
            //S Administ
            tbServiciosAdministr.setEnabled(false);
            tbServiciosAdministr.setBackground(Color.lightGray);
            btnAgregarSAdminis.setEnabled(false);
            btnQuitarSAdminis.setEnabled(false);
            //S Generales
            tbServiciosGenerales.setEnabled(false);
            tbServiciosGenerales.setBackground(Color.lightGray);
            btnAgregarServiciosGenerales.setEnabled(false);
            btnQuitarServiciosGenerales.setEnabled(false);
            //S Intermedios
            tbServiciosIntermedios.setEnabled(false);
            tbServiciosIntermedios.setBackground(Color.lightGray);
            btnAgregarServiciosInterm.setEnabled(false);
            btnQuitarServiciosInterm.setEnabled(false);
            formatoInicializarTabla();
        }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tb_Buscar_EstimacionKeyPressed

    
    private void txtBuscarEstimacionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarEstimacionCaretUpdate
       
    }//GEN-LAST:event_txtBuscarEstimacionCaretUpdate

    private void txtBuscarEstimacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEstimacionKeyPressed
         char tecla= evt.getKeyChar();
         if(tecla==KeyEvent.VK_ENTER){
            tb_Buscar_Estimacion.getSelectionModel().setSelectionInterval(0, 0);
            tb_Buscar_Estimacion.requestFocus();
         }
    }//GEN-LAST:event_txtBuscarEstimacionKeyPressed

    private void cbxBuscarEstimacionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxBuscarEstimacionPropertyChange
       
    }//GEN-LAST:event_cbxBuscarEstimacionPropertyChange

    private void cbxBuscarEstimacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarEstimacionItemStateChanged

            txtBuscarEstimacion.requestFocus();
  
    }//GEN-LAST:event_cbxBuscarEstimacionItemStateChanged

    private void DETALLE_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DETALLE_PDFActionPerformed
         try {
            int filaselec=tb_Buscar_Estimacion.getSelectedRow();
            if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }else{
        Reportes re = new Reportes();
        String ruta = "/Reportes/COSTOS/SUSTENTACION/COSTOS_SUSTENTACION.jasper";
        
            String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
            
            
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
      
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    
                    printwriter.print(ruta);
                }
               JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta), parametros, conectar.conectar());
            JasperExportManager.exportReportToPdfFile(informe, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo
                }
                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } }catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
         Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_DETALLE_PDFActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void TODO_REPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TODO_REPORTEActionPerformed
        try {
           int filaselec=tb_Buscar_Estimacion.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
             String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
         
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
            
                JasperPrint informe=JasperFillManager.fillReport(getClass().
                    getResourceAsStream("/Reportes/COSTOS/ESTIMACION/COSTOS_SUSTENTACION.jasper"), parametros,conectar.conectar());

                JasperViewer ventana= new JasperViewer(informe,false);
                ventana.setTitle("ESTIMACIÓN DE COSTOS");
                ventana.setVisible(true);
           }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Cargar el reporte"+e.getMessage());
            }
    }//GEN-LAST:event_TODO_REPORTEActionPerformed

    private void TODO_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TODO_PDFActionPerformed
         try {
               int filaselec=tb_Buscar_Estimacion.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
        Reportes re = new Reportes();
        String ruta = "/Reportes/COSTOS/ESTIMACION/COSTOS_SUSTENTACION.jasper";
        
            String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
            
            
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.PDF", "pdf", "PDF"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
      
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    
                    printwriter.print(ruta);
                }
               JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta), parametros, conectar.conectar());
            JasperExportManager.exportReportToPdfFile(informe, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo
                }
               JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } }catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
         Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_TODO_PDFActionPerformed

    private void DETALLE_REPORTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DETALLE_REPORTEActionPerformed
         try {
               int filaselec=tb_Buscar_Estimacion.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
               String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
            
                JasperPrint informe=JasperFillManager.fillReport(getClass().
                    getResourceAsStream("/Reportes/COSTOS/SUSTENTACION/COSTOS_SUSTENTACION.jasper"), parametros,conectar.conectar());

                JasperViewer ventana= new JasperViewer(informe,false);
                ventana.setTitle("INFORME DE PERSONAL");
                ventana.setVisible(true);
           }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Cargar el reporte"+e.getMessage());
            }
    }//GEN-LAST:event_DETALLE_REPORTEActionPerformed

    private void DETALLE_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DETALLE_EXCELActionPerformed
       try {
               int filaselec=tb_Buscar_Estimacion.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
        Reportes re = new Reportes();
        String ruta = "/Reportes/COSTOS/SUSTENTACION/COSTOS_SUSTENTACION.jasper";
        
            String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
 //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.xls", "xls", "XLS"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
      
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    
                    printwriter.print(ruta);
                }
                
               JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta), parametros, conectar.conectar());
//            JasperExportManager.exportReportToPdfFile(informe, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                 
               //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
               if (!(PATH.endsWith(".xls"))) {
                    File temp = new File(PATH + ".xls");
                    JFC.renameTo(temp);//renombramos el archivo
                }
               JRXlsExporter exporterXLS = new JRXlsExporter();                
// exporterXLS.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IGNORE_PAGE_MARGINS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.MAXIMUM_ROWS_PER_SHEET,5000);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN , Boolean.FALSE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_FONT_SIZE_FIX_ENABLED , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.JASPER_PRINT, informe);
//exporterXLS.setParameter(JExcelApiExporterParameter.OUTPUT_FILE, PATH);
//exporterXLS.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
//               exporterXLS.setParameter(JExcelApiExporterParameter.MAXIMUM_ROWS_PER_SHEET,5000);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN , Boolean.FALSE);
               exporterXLS.setParameter(JExcelApiExporterParameter.JASPER_PRINT, informe);
exporterXLS.setParameter(JExcelApiExporterParameter.OUTPUT_FILE,new File(PATH + ".xls"));
//exporterXLS.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
 exporterXLS.exportReport();

                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } }catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
         Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_DETALLE_EXCELActionPerformed

    private void TODO_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TODO_EXCELActionPerformed
         try {
               int filaselec=tb_Buscar_Estimacion.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
        Reportes re = new Reportes();
        String ruta = "/Reportes/COSTOS/ESTIMACION/COSTOS_SUSTENTACION.jasper";
        
            String cod=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            Map parametros=new HashMap();
            parametros.put("COD_SUSTENTO",cod);
 //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.xls", "xls", "XLS"));//filtro para ver solo archivos .pdf
        int seleccion = fileChooser.showSaveDialog(null);
      
            if (seleccion == JFileChooser.APPROVE_OPTION) {//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    
                    printwriter.print(ruta);
                }
                
               JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta), parametros, conectar.conectar());
//            JasperExportManager.exportReportToPdfFile(informe, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                 
               //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
               if (!(PATH.endsWith(".xls"))) {
                    File temp = new File(PATH + ".xls");
                    JFC.renameTo(temp);//renombramos el archivo
                }
               JRXlsExporter exporterXLS = new JRXlsExporter();                
// exporterXLS.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.SHEET_NAMES, "Rpt");
//exporterXLS.setParameter(JExcelApiExporterParameter.IGNORE_PAGE_MARGINS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.MAXIMUM_ROWS_PER_SHEET,5000);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN , Boolean.FALSE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_FONT_SIZE_FIX_ENABLED , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.JASPER_PRINT, informe);
//exporterXLS.setParameter(JExcelApiExporterParameter.OUTPUT_FILE, PATH);
//exporterXLS.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
               
               exporterXLS.setParameter(JExcelApiExporterParameter.JASPER_PRINT, informe);
exporterXLS.setParameter(JExcelApiExporterParameter.OUTPUT_FILE,new File(PATH + ".xls"));
exporterXLS.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
 exporterXLS.exportReport();

                JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } }catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
         Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_TODO_EXCELActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       try{
    if(cbxBuscarEstimacion.getSelectedIndex()>0){
        
    String tipo_sust =txtBuscarEstimacion.getText().toString(); 
    int combo = cbxBuscarEstimacion.getSelectedIndex();
        
    DefaultTableModel tabla= new DefaultTableModel();
       
       tabla.addColumn("Codigo");
       tabla.addColumn("Codigo Precio");
       tabla.addColumn("Codigo CPT");
       tabla.addColumn("Servicio");
       tabla.addColumn("Area");
       tabla.addColumn("Forma de Pago");
       tabla.addColumn("Precio");
       tabla.addColumn("Tiempo(h)");
       tabla.addColumn("Tiempo(Min)");
       tabla.addColumn("Saldo");
       tabla.addColumn("Nomenclatura");
       cst=con.prepareCall("exec COSTOS_COSTOS_SUSTENTACION_ESTIMACION_buscar ?,?");     
            cst.setString(1, tipo_sust);
            cst.setInt(2, combo);
            r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[11];
       for (int i=0; i<11; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       
       this.tb_Buscar_Estimacion.setModel(tabla);
       
       formatoEstimacionCostos();
       tb_Buscar_Estimacion.getSelectionModel().setSelectionInterval(0, 0);
       tb_Buscar_Estimacion.requestFocus();
       txtBuscarEstimacion.requestFocus();
      
       } else {
        JOptionPane.showMessageDialog(rootPane, "Seleccione un tipo de busqueda");
        cbxBuscarEstimacion.requestFocus();
        txtBuscarEstimacion.setText("");
    }   

       }catch (Exception e){
       }
    }//GEN-LAST:event_btnBuscarActionPerformed
private void exportXls()
 {
     String ruta = "/reporteTotal/report1.jasper";
        String destino = "E:\\Rpt01.xls";
 try{   
     
 JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta),null, conectar.conectar());

 JRXlsExporter exporterXLS = new JRXlsExporter();                

// exporterXLS.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.SHEET_NAMES, "Rpt");
//exporterXLS.setParameter(JExcelApiExporterParameter.IGNORE_PAGE_MARGINS , Boolean.TRUE);
//exporterXLS.setParameter(JExcelApiExporterParameter.MAXIMUM_ROWS_PER_SHEET,5000);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN , Boolean.FALSE);
//exporterXLS.setParameter(JExcelApiExporterParameter.IS_FONT_SIZE_FIX_ENABLED , Boolean.TRUE);
exporterXLS.setParameter(JExcelApiExporterParameter.JASPER_PRINT, informe);
exporterXLS.setParameter(JExcelApiExporterParameter.OUTPUT_FILE, new File(destino));
exporterXLS.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
exporterXLS.exportReport();
JOptionPane.showMessageDialog(null, "Documento Exportado Exitosamente!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
            
 }catch(JRException e)
 {
 JOptionPane.showMessageDialog(null,e);;
 }

 }


        public void mostrarCabecerayDetalle(){
        try {
            int filaselec=tb_Buscar_Estimacion.getSelectedRow();
            //PERSONAL
            String consulta="";
            tbPersonal.setModel(new DefaultTableModel());
            String titulos[]={"Cod_tipoSust","Cod_Sueldo","Tipo Sustento","Nombre del Detalle","Hora","Min","Horas al Mes","Sueldo","Total Sustento"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[9];
            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_DET_PERSONAL_BuscarTodo ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<9; i++){
           fila[i]=r.getString(i+1);
       }
                m3.addRow(fila);
            }
            tbPersonal.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbPersonal.setRowSorter(elQueOrdena);
            tbPersonal.setModel(m3);
            
            //INSUMOS
            String consultai="";
            tbInsumo.setModel(new DefaultTableModel());
            String titulosi[]={"Cod_tipoSust","Cod_Prod_Refe","Consumible","Tipo Sustento","Nombre del Producto","Cantidad Sustento","Rendimiento","UM","Cantidad UM","Precio Sustento","Total Sustento"};
            m=new DefaultTableModel(null,titulosi);
            JTable pi=new JTable(m);
            String filai[]=new String[11];
            consultai="exec COSTOS_COSTOS_SUSTENTACION_DET_PROD_INSU_buscar ?";
            PreparedStatement cmdi = obj.getCn().prepareStatement(consultai);
            cmdi.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet ri= cmdi.executeQuery();
            while(ri.next()){
            for (int i=0; i<11; i++){
           filai[i]=ri.getString(i+1);
            }
                m.addRow(filai);
            }
            tbInsumo.setModel(m);
            TableRowSorter<TableModel> elQueOrdenai=new TableRowSorter<TableModel>(m);
            tbInsumo.setRowSorter(elQueOrdenai);
            tbInsumo.setModel(m);
            
            //SERVICIOS BASICOS
            String consultasb="";
            tbServiciosBasicos.setModel(new DefaultTableModel());
            String titulosb[]={"Código_Factor de Prod.","Cod_Servicio","Factores de Producción","Servicio","Área",
            "Ponderación de Energía","Base de Asignacion Energía","Sumatoria Base Asig. Energía",
            "Coeficiente de Consumo Energía","Consumo de Energía","Importe Energía Electrica","Importe por Consulta - Energía",
            "Ponderación de Consumo Agua","Base de Asig. Consumo Agua","Sumatoria Base Asig. Agua",
            "Coeficiente de Consumo Agua","Consumo de Agua","Importe Consumo de Agua","Importe por Consulta - Agua",
            "Total de Consultas","Costo Estandar"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[21];
            consultasb="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIOS_BASICOS_Buscar ?";
            PreparedStatement cmdsb = obj.getCn().prepareStatement(consultasb);
            cmdsb.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rsb= cmdsb.executeQuery();
            while(rsb.next()){
            for (int i=0; i<21; i++){
           filasb[i]=rsb.getString(i+1);
       }
                msb.addRow(filasb);
            }
            tbServiciosBasicos.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tbServiciosBasicos.setRowSorter(elQueOrdenasb);
            tbServiciosBasicos.setModel(msb);
            
            //HERRAMIENTAS
            String consultah="";
            tbHerramienta.setModel(new DefaultTableModel());
            String titulosh[]={"Cod_tipoSust","Cod_Depre","Tipo Sustento","Nombre del Detalle","Codigo Patrimonial","Año de Compra","Valor de Compra",
            "Fecha Fin a Depreciar","Vida Util(meses)","Depreciacion Mensual","Total de Meses a Depreciar","Depreciacion Acumulada","Valor Neto","Depreciacion Diaria"};
            m1=new DefaultTableModel(null,titulosh);
            JTable ph=new JTable(m1);
            String filah[]=new String[14];
            consultah="exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_buscar ?";
            PreparedStatement cmdh = obj.getCn().prepareStatement(consultah);
            cmdh.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rh= cmdh.executeQuery();
            while(rh.next()){
            for (int i=0; i<14; i++){
           filah[i]=rh.getString(i+1);
       }
                m1.addRow(filah);
            }
            tbHerramienta.setModel(m1);
            TableRowSorter<TableModel> elQueOrdenah=new TableRowSorter<TableModel>(m1);
            tbHerramienta.setRowSorter(elQueOrdenah);
            tbHerramienta.setModel(m1);
            
            //INFRAESTRUCTURA
            String consultaii="";
            tbInfraestructura.setModel(new DefaultTableModel());
            String titulosii[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Area Total","Costo Total","Requerimiento mínimo de Area","Valor Unitario Depr.",
                "Costo de Construccion","Tiempo de Rendimiento(min)","Depreciación de Infraestructura","Tiempo(Hora)","Tiempo(min)","Costo Estándar"};
            mi=new DefaultTableModel(null,titulosii);
            JTable pii=new JTable(mi);
            String filaii[]=new String[14];
            consultaii="exec COSTOS_COSTOS_SUSTENTACION_DET_INFRAESTRUCTURA_buscar ?";
            PreparedStatement cmdii = obj.getCn().prepareStatement(consultaii);
            cmdii.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rii= cmdii.executeQuery();
            while(rii.next()){
            for (int i=0; i<14; i++){
           filaii[i]=rii.getString(i+1);
       }
                mi.addRow(filaii);
            }
            tbInfraestructura.setModel(mi);
            TableRowSorter<TableModel> elQueOrdenaii=new TableRowSorter<TableModel>(mi);
            tbInfraestructura.setRowSorter(elQueOrdenaii);
            tbInfraestructura.setModel(mi);
            
            //SERVICIOS ADMINISTRATIVOS
            String consultasa="";
            tbServiciosAdministr.setModel(new DefaultTableModel());
            String titulossa[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msa=new DefaultTableModel(null,titulossa);
            JTable psa=new JTable(msa);
            String filasa[]=new String[7];
            consultasa="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSAdmin ?";
            PreparedStatement cmdsa = obj.getCn().prepareStatement(consultasa);
            cmdsa.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rsa= cmdsa.executeQuery();
            while(rsa.next()){
            for (int i=0; i<7; i++){
           filasa[i]=rsa.getString(i+1);
       }
                msa.addRow(filasa);
            }
            tbServiciosAdministr.setModel(msa);
            TableRowSorter<TableModel> elQueOrdenasa=new TableRowSorter<TableModel>(msa);
            tbServiciosAdministr.setRowSorter(elQueOrdenasa);
            tbServiciosAdministr.setModel(msa);
            
            
            //SERVICIOS GENERALES
            String consultasg="";
            tbServiciosGenerales.setModel(new DefaultTableModel());
            String titulossg[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msg=new DefaultTableModel(null,titulossg);
            JTable psg=new JTable(msg);
            String filasg[]=new String[7];
            consultasg="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSGen ?";
            PreparedStatement cmdsg = obj.getCn().prepareStatement(consultasg);
            cmdsg.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rsg= cmdsg.executeQuery();
            while(rsg.next()){
            for (int i=0; i<7; i++){
           filasg[i]=rsg.getString(i+1);
       }
                msg.addRow(filasg);
            }
            tbServiciosGenerales.setModel(msg);
            TableRowSorter<TableModel> elQueOrdenasg=new TableRowSorter<TableModel>(msg);
            tbServiciosGenerales.setRowSorter(elQueOrdenasg);
            tbServiciosGenerales.setModel(msg);
            
            //SERVICIOS INTERMEDIOS
            String consultasi="";
            tbServiciosIntermedios.setModel(new DefaultTableModel());
            String titulossi[]={"Cod_tipoSust","Cod_Servicio","Tipo Sustento","Nombre del Detalle","Cantidad Sustento","Precio Sustento","Total Sustento"};
            msi=new DefaultTableModel(null,titulossi);
            JTable psi=new JTable(msi);
            String filasi[]=new String[7];
            consultasi="exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_buscarSInter ?";
            PreparedStatement cmdsi = obj.getCn().prepareStatement(consultasi);
            cmdsi.setString(1, tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString());
            ResultSet rsi= cmdsi.executeQuery();
            while(rsi.next()){
            for (int i=0; i<7; i++){
           filasi[i]=rsi.getString(i+1);
       }
                msi.addRow(filasi);
            }
            tbServiciosIntermedios.setModel(msi);
            TableRowSorter<TableModel> elQueOrdenasi=new TableRowSorter<TableModel>(msi);
            tbServiciosIntermedios.setRowSorter(elQueOrdenasi);
            tbServiciosIntermedios.setModel(msi);
            
            //formatoInicializarTabla();
            
            
            
            //PERSONAL Sumar Subtotales
            double totalper=0;
            for (int i = 0; i < tbPersonal.getRowCount(); i++){    
                    totalper=totalper+Double.parseDouble(tbPersonal.getValueAt(i, 8).toString());
                }
                BigDecimal totalperson = new BigDecimal(totalper);
                       totalperson = totalperson.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalPersonal.setText(String.valueOf(totalperson));

            //INSUMOS Sumar Subtotales
            double totalin=0;
            for (int i = 0; i < tbInsumo.getRowCount(); i++){    
                    totalin=totalin+Double.parseDouble(tbInsumo.getValueAt(i, 10).toString());
                }
                BigDecimal totalinsumo = new BigDecimal(totalin);
                       totalinsumo = totalinsumo.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalInsumos.setText(String.valueOf(totalinsumo));
            
            //SERVICIOS BASICOS Sumar Subtotales
            double totalsB=0,SE=0,SA=0;
            for (int i = 0; i < tbServiciosBasicos.getRowCount(); i++){    
                    totalsB=totalsB+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 20).toString());
                    SE=SE+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 7).toString());
                    SA=SA+Double.parseDouble(tbServiciosBasicos.getValueAt(i, 14).toString());
                }
                BigDecimal totalsbas = new BigDecimal(totalsB);
                       totalsbas = totalsbas.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtTotalSBasicos.setText(String.valueOf(totalsbas));
            BigDecimal BSE = new BigDecimal(SE);
                       BSE = BSE.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtSumatoriaEnergiaBA.setText(String.valueOf(BSE));
            BigDecimal BSA = new BigDecimal(SA);
                       BSA = BSA.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtSumatoriaAguaBA.setText(String.valueOf(BSA));
            
            //HERRAMIENTAS Sumar Subtotales
            double totalher=0;
            for (int i = 0; i < tbHerramienta.getRowCount(); i++){    
                    totalher=totalher+Double.parseDouble(tbHerramienta.getValueAt(i, 13).toString());
                }
                BigDecimal totalherra = new BigDecimal(totalher);
                       totalherra = totalherra.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalHerramienta.setText(String.valueOf(totalherra));
               
            //INFRAESTRUCTURA Sumar Subtotales
            double totalinf=0;
            for (int i = 0; i < tbInfraestructura.getRowCount(); i++){    
                    totalinf=totalinf+Double.parseDouble(tbInfraestructura.getValueAt(i, 13).toString());
                }
                BigDecimal totalinfra = new BigDecimal(totalinf);
                       totalinfra = totalinfra.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalInfraes.setText(String.valueOf(totalinfra));
            
            //SERVICIOS ADMINISTRATIVOS Sumar Subtotales
            double totalsa=0;
            for (int i = 0; i < tbServiciosAdministr.getRowCount(); i++){    
                    totalsa=totalsa+Double.parseDouble(tbServiciosAdministr.getValueAt(i, 6).toString());
                }
                BigDecimal totalsadmin = new BigDecimal(totalsa);
                       totalsadmin = totalsadmin.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSAdminis.setText(String.valueOf(totalsadmin));
            //SERVICIOS GENERALES Sumar Subtotales
            double totalsg=0;
            for (int i = 0; i < tbServiciosGenerales.getRowCount(); i++){    
                    totalsg=totalsg+Double.parseDouble(tbServiciosGenerales.getValueAt(i, 6).toString());
                }
                BigDecimal totalsgen = new BigDecimal(totalsg);
                       totalsgen = totalsgen.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSGenerales.setText(String.valueOf(totalsgen));
            
            //SERVICIOS INTERMEDIOS Sumar Subtotales
            double totalsi=0;
            for (int i = 0; i < tbServiciosIntermedios.getRowCount(); i++){    
                    totalsi=totalsi+Double.parseDouble(tbServiciosIntermedios.getValueAt(i, 6).toString());
                }
                BigDecimal totalsinter = new BigDecimal(totalsi);
                       totalsinter = totalsinter.setScale(2, BigDecimal.ROUND_HALF_UP);
            
            txtTotalSIntermedios.setText(String.valueOf(totalsinter));
            
             //Resumen
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            m2=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(m2);
            String filar[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                filar[0]="RECURSO HUMANO";
                filar[1]=txtTotalPersonal.getText();
                }
                else if(i==1){
                filar[0]="INSUMOS";
                filar[1]=txtTotalInsumos.getText();
                } 
                else if(i==2){
                filar[0]="SERVICIOS BÁSICOS";
                filar[1]=txtTotalSBasicos.getText();
                } 
                else if(i==3){
                filar[0]="EQUIPAMIENTO BÁSICO";
                filar[1]=txtTotalHerramienta.getText();
                } 
                else if(i==4){
                filar[0]="INFRAESTRUCTURA";
                filar[1]=txtTotalInfraes.getText();
                } 
                else if(i==5){
                filar[0]="SERVICIOS ADMINISTRATIVOS";
                filar[1]=txtTotalSAdminis.getText();
                } 
                else if(i==6){
                filar[0]="SERVICIOS GENERALES";
                filar[1]=txtTotalSGenerales.getText();
                } 
                else if(i==7){
                filar[0]="GASTOS INTERMEDIOS";
                filar[1]=txtTotalSIntermedios.getText();
                } 
                m2.addRow(filar);
                }
                
                tbResumenCostos.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(m2);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
            //Total
            Double insumo,pe,he,sb,inf,sg,sa,si;
            pe=Double.parseDouble(txtTotalPersonal.getText());
            he=Double.parseDouble(txtTotalHerramienta.getText());
            insumo=Double.parseDouble(txtTotalInsumos.getText());
            sb=Double.parseDouble(txtTotalSBasicos.getText());
            inf=Double.parseDouble(txtTotalInfraes.getText());
            sa=Double.parseDouble(txtTotalSAdminis.getText());
            sg=Double.parseDouble(txtTotalSGenerales.getText());
            si=Double.parseDouble(txtTotalSIntermedios.getText());
            Double precioTotal=pe+he+insumo+sb+inf+sa+sg+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtPrecioTotal.setText(String.valueOf(ptotal));
            /*Ganancia
            double gan=Double.parseDouble(txtPrecio.getText())-precioTotal;
            BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtGanancia.setText(String.valueOf(gtotal));
            */
            //Todo
            String codigo=tb_Buscar_Estimacion.getValueAt(filaselec, 0).toString();
            txtCodigoSustento.setText(codigo);
            txtCodigo6.setText(codigo.substring(14, 20));
            txtCodigoPrecio.setText(tb_Buscar_Estimacion.getValueAt(filaselec, 1).toString());
            txtCodigoCPT.setText(tb_Buscar_Estimacion.getValueAt(filaselec, 2).toString());
            txtServicio.setText(tb_Buscar_Estimacion.getValueAt(filaselec, 3).toString());
            txtSubServicio.setText(tb_Buscar_Estimacion.getValueAt(filaselec,4).toString());
            txtFormadePago.setText(tb_Buscar_Estimacion.getValueAt(filaselec,5).toString());
            txtPrecio.setText(tb_Buscar_Estimacion.getValueAt(filaselec,6).toString());
            txtPrecio1.setText(tb_Buscar_Estimacion.getValueAt(filaselec,6).toString());
            spHora.setValue(Integer.parseInt(tb_Buscar_Estimacion.getValueAt(filaselec,7).toString()));
            spMin.setValue(Integer.parseInt(tb_Buscar_Estimacion.getValueAt(filaselec,8).toString()));
            txtGanancia.setText(tb_Buscar_Estimacion.getValueAt(filaselec,9).toString());
            if(Double.parseDouble(tb_Buscar_Estimacion.getValueAt(filaselec,9).toString())>0){
                lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                lblGananciaPer.setText("Ganancia Total");
            txtGananciaPerdida.setText(tbCostoSusten.getValueAt(filaselec,9).toString());
            }else{
                lblGananciaPer.setText("Pérdida Total");
                double ganper=-Double.parseDouble(tb_Buscar_Estimacion.getValueAt(filaselec,9).toString());
                lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
            txtGananciaPerdida.setText(String.valueOf(ganper));
            }
            txtNomenclatura.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+tb_Buscar_Estimacion.getValueAt(filaselec,10).toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BUSCAR_ESTIMACION_COSTOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BUSCAR_ESTIMACION_COSTOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DETALLE_EXCEL;
    private javax.swing.JMenuItem DETALLE_PDF;
    private javax.swing.JMenuItem DETALLE_REPORTE;
    private javax.swing.JPopupMenu REPORTES;
    private javax.swing.JMenuItem TODO_EXCEL;
    private javax.swing.JMenuItem TODO_PDF;
    private javax.swing.JMenuItem TODO_REPORTE;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxBuscarEstimacion;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem5;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbldia;
    public static javax.swing.JTable tb_Buscar_Estimacion;
    private javax.swing.JTextField txtBuscarEstimacion;
    // End of variables declaration//GEN-END:variables
}
