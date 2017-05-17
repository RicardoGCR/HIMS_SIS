/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import modelos.COSTOS.serviciosVarios;
import vista.LABORATORIO.frm_LAB_MUESTRA_EXAMEN;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelos.*;
import modelos.LABORATORIO.LAB_Analisis_Examen;
import modelos.LABORATORIO.LAB_Clasificacion_Examen;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author silvana
 */
public class frm_LAB_VALORES_REFERENCIALES_INGRESO extends javax.swing.JFrame implements Runnable{
DefaultTableModel m;
Conexion c=new Conexion();
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    /**
     * Creates new form listarUsuario
     */
    public frm_LAB_VALORES_REFERENCIALES_INGRESO() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
        
        h1 = new Thread(this);
        h1.start();
        tb_Analisis_Examen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Analisis_Examen.doLayout();
                setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        LAB_Clasificacion_Examen_cargar();
        LAB_Clasificacion_Examen_formato();
        LAB_Analisis_Examen_cargar();
        LAB_Analisis_Examen_formato();
         this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
//        lblFecha.setText(fechaActual());
        //para limpiar el txt al darle click
 txtBuscar.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscar.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} );
 //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();

            }
        });
    }
    
 public void LAB_Clasificacion_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","Servicio","Nombre de la Unidad","Cogd_uni_or","Clasificación","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(4);
            fila[3]=r.getString(5);
            fila[4]=r.getString(4);
            fila[5]=r.getString(2);
            fila[6]=r.getString(6);
                m.addRow(fila);
                c++;
            }
            tb_Clasificacion_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Clasificacion_Examen.setRowSorter(elQueOrdena);
            this.tb_Clasificacion_Examen.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Clasificacion_Examen_formato(){
        tb_Clasificacion_Examen.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_Clasificacion_Examen.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Clasificacion_Examen.getColumnModel().getColumn(5).setPreferredWidth(220);
    
            //Ocultar    
    tb_Clasificacion_Examen.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(1).setMaxWidth(0);
        tb_Clasificacion_Examen.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(4).setMaxWidth(0);
        tb_Clasificacion_Examen.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_Clasificacion_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Clasificacion_Examen.requestFocus();
}
public void LAB_Analisis_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","CodigoClasif","CodNomen","Servicio","Clasificación","Código CPT","Nomenclatura","Nombre Análisis",
                 "Abrev. Análisis","Tiempo Hora","Tiempo Min","Tipo Procesamiento","Restriccion",
                 "Explicación","Estado","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[17];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_ANALISIS_EXAMEN_listar";
        ResultSet r;
        r=obj.Listar(consulta);
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
                m.addRow(fila);
                c++;
            }
            tb_Analisis_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Analisis_Examen.setRowSorter(elQueOrdena);
            this.tb_Analisis_Examen.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Analisis_Examen_formato(){
    tb_Analisis_Examen.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_Analisis_Examen.getColumnModel().getColumn(5).setPreferredWidth(140);
    tb_Analisis_Examen.getColumnModel().getColumn(8).setPreferredWidth(215);
    tb_Analisis_Examen.getColumnModel().getColumn(9).setPreferredWidth(150);
            //Ocultar    
    tb_Analisis_Examen.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(10).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(11).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(11).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(12).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(12).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(14).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(15).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(15).setMaxWidth(0);
     tb_Analisis_Examen.getColumnModel().getColumn(16).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(16).setMaxWidth(0);   
    
    tb_Analisis_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Analisis_Examen.requestFocus();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Analisis_Examen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtBuscar = new javax.swing.JTextField();
            btnBuscar = new javax.swing.JButton();
            jScrollPane2 = new javax.swing.JScrollPane();
            tb_Clasificacion_Examen = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setAlwaysOnTop(true);

                tb_Analisis_Examen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_Analisis_Examen.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Analisis_Examen.setEditingColumn(0);
                tb_Analisis_Examen.setEditingRow(0);
                tb_Analisis_Examen.setFocusTraversalPolicyProvider(true);
                tb_Analisis_Examen.setGridColor(new java.awt.Color(102, 102, 102));
                tb_Analisis_Examen.setOpaque(false);
                tb_Analisis_Examen.setRequestFocusEnabled(false);
                tb_Analisis_Examen.setRowHeight(25);
                tb_Analisis_Examen.setSelectionBackground(new java.awt.Color(2, 67, 115));
                tb_Analisis_Examen.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Analisis_ExamenMouseClicked(evt);
                    }
                });
                tb_Analisis_Examen.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Analisis_ExamenKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(tb_Analisis_Examen);

                txtBuscar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscar.setText("Ingrese el Nombre del Análisis");
                txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarActionPerformed(evt);
                    }
                });
                txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBuscarKeyTyped(evt);
                    }
                });

                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                btnBuscar.setBorder(null);
                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarActionPerformed(evt);
                    }
                });

                tb_Clasificacion_Examen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_Clasificacion_Examen.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Clasificacion_Examen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_Clasificacion_Examen.setGridColor(new java.awt.Color(102, 102, 102));
                tb_Clasificacion_Examen.setRequestFocusEnabled(false);
                tb_Clasificacion_Examen.setRowHeight(25);
                tb_Clasificacion_Examen.setSelectionBackground(new java.awt.Color(2, 67, 115));
                tb_Clasificacion_Examen.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Clasificacion_ExamenMouseClicked(evt);
                    }
                });
                tb_Clasificacion_Examen.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Clasificacion_ExamenKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tb_Clasificacion_Examen);

                jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel2.setText("Clasificación");

                jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel3.setText("Análisis");

                jPanel1.setBackground(new java.awt.Color(204, 204, 204));

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/enviar.png"))); // NOI18N
                jLabel4.setText("Enviar Análisis(Enter)");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addGap(7, 7, 7))
                );

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Valores Referenciales");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                lblUsu.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                lblUsu.setText("Usuario");

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo5)
                        .addGap(521, 521, 521)
                        .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo5)
                            .addComponent(lblUsu))
                        .addGap(0, 29, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabel3)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addGap(22, 22, 22))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
 char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnBuscar.doClick();
                }
                
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         // TODO add your handling code here:
String consulta="";
        try {
            
            tb_Analisis_Examen.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","CodigoClasif","CodNomen","Servicio","Clasificación","Código CPT","Nomenclatura","Nombre Examen",
                 "Abreviatura","Tiempo Hora","Tiempo Min","Tipo Procesamiento","Restriccion",
                 "Explicación","Estado","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[17];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_ANALISIS_EXAMEN_buscar ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            cmd.setInt(2, 5);
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
                m.addRow(fila);
                c++;
            }
            tb_Analisis_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Analisis_Examen.setRowSorter(elQueOrdena);
            this.tb_Analisis_Examen.setModel(m);
            
            LAB_Analisis_Examen_formato();            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tb_Analisis_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Analisis_ExamenMouseClicked
 
    }//GEN-LAST:event_tb_Analisis_ExamenMouseClicked
public void LAB_Esquema_formato(){
            //Ocultar    
        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(0).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(0).setMaxWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(4).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(4).setMaxWidth(0);
    
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getSelectionModel().setSelectionInterval(0, 0);
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.requestFocus();
}
    private void tb_Analisis_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Analisis_ExamenKeyPressed
                char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        dispose();
                        int filaselec=tb_Analisis_Examen.getSelectedRow();
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA lme= new frm_LAB_VALORES_REFERENCIALES_ESQUEMA();   
                        LAB_Clasificacion_Examen ce=new LAB_Clasificacion_Examen();
                        lme.setVisible(true);
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtCodClasificacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 2).toString());
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtUnidadOrganica.setText(ce.LAB_Clasificacion_Examen_buscar(tb_Analisis_Examen.getValueAt(filaselec, 5).toString()));
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtClasificacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 5).toString());
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtCodAnalisis.setText(tb_Analisis_Examen.getValueAt(filaselec, 1).toString());
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtAnalisis.setText(tb_Analisis_Examen.getValueAt(filaselec, 8).toString());
                        frm_LAB_VALORES_REFERENCIALES_ESQUEMA.txtAbreviatura.setText(tb_Analisis_Examen.getValueAt(filaselec, 9).toString());
                         String consulta="";
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(new DefaultTableModel());
            String titulos[]={"cod esquema","Nombre de Resultado","Resultado por Defecto" ,
            "Tipo Esquema","cod Uni","Unidad de Medida","Valores","Resultados"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_ESQUEMA_RESULTADO_buscar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Analisis_Examen.getValueAt(filaselec, 1).toString());
            cmd.setInt(2, 2);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                m.addRow(fila);
                c++;
            }
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setRowSorter(elQueOrdena);
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(m);
            LAB_Esquema_formato();
            String u=frm_LAB_VALORES_REFERENCIALES_INGRESO.lblUsu.getText();
                             frm_LAB_VALORES_REFERENCIALES_ESQUEMA.lblUsu.setText(u);
                                                 
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                    // TODO add your handling code here:
    }//GEN-LAST:event_tb_Analisis_ExamenKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void tb_Clasificacion_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Clasificacion_ExamenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Clasificacion_ExamenMouseClicked

    private void tb_Clasificacion_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Clasificacion_ExamenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Clasificacion_ExamenKeyPressed
 public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
//            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
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
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_VALORES_REFERENCIALES_INGRESO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tb_Analisis_Examen;
    public static javax.swing.JTable tb_Clasificacion_Examen;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
