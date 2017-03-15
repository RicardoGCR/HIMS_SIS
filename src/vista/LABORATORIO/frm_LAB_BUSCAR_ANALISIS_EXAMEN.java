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
import vista.PrincipalMDI;
import vista.frmlaboratorioClinico;

/**
 *
 * @author silvana
 */
public class frm_LAB_BUSCAR_ANALISIS_EXAMEN extends javax.swing.JFrame {
DefaultTableModel m,muestra;
Conexion c=new Conexion();
    /**
     * Creates new form listarUsuario
     */
    public frm_LAB_BUSCAR_ANALISIS_EXAMEN() {
        initComponents();
        c.conectar();
        tb_Analisis_Examen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Analisis_Examen.doLayout();
                setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        LAB_Analisis_Examen_cargar();
        LAB_Analisis_Examen_formato();
        txtBuscar.setVisible(false);
        btnBuscar.setVisible(false);
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
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
public void LAB_Analisis_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","CodigoClasif","CodNomen","Servicio","Clasificación","Código CPT","Nomenclatura","Nombre Examen",
                 "Abreviatura","Tiempo Hora","Tiempo Min","Tipo Procesamiento","Restriccion",
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
    tb_Analisis_Examen.getColumnModel().getColumn(0).setPreferredWidth(30);
    tb_Analisis_Examen.getColumnModel().getColumn(1).setPreferredWidth(120);
    tb_Analisis_Examen.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Analisis_Examen.getColumnModel().getColumn(5).setPreferredWidth(120);
    tb_Analisis_Examen.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Analisis_Examen.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_Analisis_Examen.getColumnModel().getColumn(8).setPreferredWidth(150);
    tb_Analisis_Examen.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Analisis_Examen.getColumnModel().getColumn(10).setPreferredWidth(90);
    tb_Analisis_Examen.getColumnModel().getColumn(11).setPreferredWidth(90);
    tb_Analisis_Examen.getColumnModel().getColumn(12).setPreferredWidth(100);
    tb_Analisis_Examen.getColumnModel().getColumn(13).setPreferredWidth(200);
    tb_Analisis_Examen.getColumnModel().getColumn(14).setPreferredWidth(200);
    tb_Analisis_Examen.getColumnModel().getColumn(15).setPreferredWidth(50);
    tb_Analisis_Examen.getColumnModel().getColumn(16).setPreferredWidth(200);
            //Ocultar    
    tb_Analisis_Examen.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Analisis_Examen.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Analisis_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Analisis_Examen.requestFocus();
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
            jLabel1 = new javax.swing.JLabel();
            txtBuscar = new javax.swing.JTextField();
            btnBuscar = new javax.swing.JButton();
            cbxAnalisis = new javax.swing.JComboBox();
            jLabel2 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("SISGESH .::. Búsqueda de Análisis");
            setAlwaysOnTop(true);

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
            tb_Analisis_Examen.setRowHeight(25);
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

            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
            jLabel1.setText("Búsqueda ");

            txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscar.setText("Buscar");
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

            cbxAnalisis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Clasificación", "Codigo CPT", "Nomenclatura", "Abreviatura" }));
            cbxAnalisis.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxAnalisisItemStateChanged(evt);
                }
            });
            cbxAnalisis.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxAnalisisActionPerformed(evt);
                }
            });

            jLabel2.setText("Búsqueda por:");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(93, 93, 93)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 300, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(294, 294, 294))
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane1)
                    .addGap(19, 19, 19))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
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
            cmd.setInt(2, cbxAnalisis.getSelectedIndex());
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

    private void tb_Analisis_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Analisis_ExamenKeyPressed
                char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        dispose();
                        int filaselec=tb_Analisis_Examen.getSelectedRow();
                        frm_LAB_ANALISIS_EXAMEN lme= new  frm_LAB_ANALISIS_EXAMEN();   
                        lme.setVisible(true);
                         frm_LAB_ANALISIS_EXAMEN.txtCodigo.setText(tb_Analisis_Examen.getValueAt(filaselec, 1).toString());
                         frm_LAB_ANALISIS_EXAMEN.txtCodClasificacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 2).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtCodNomen.setText(tb_Analisis_Examen.getValueAt(filaselec, 3).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtClasificacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 5).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtCodigoCPT.setText(tb_Analisis_Examen.getValueAt(filaselec, 6).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtNomen.setText(tb_Analisis_Examen.getValueAt(filaselec, 7).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtNombreExamen.setText(tb_Analisis_Examen.getValueAt(filaselec, 8).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtAbrev.setText(tb_Analisis_Examen.getValueAt(filaselec, 9).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtTiempoHora.setText(tb_Analisis_Examen.getValueAt(filaselec, 10).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtTiempoMin.setText(tb_Analisis_Examen.getValueAt(filaselec, 11).toString());
                        frm_LAB_ANALISIS_EXAMEN.cbxTipoProc.setSelectedItem(tb_Analisis_Examen.getValueAt(filaselec, 12).toString());
                        frm_LAB_ANALISIS_EXAMEN.cbxRestric.setSelectedItem(tb_Analisis_Examen.getValueAt(filaselec, 13).toString());
                        frm_LAB_ANALISIS_EXAMEN.txtExplicacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 14).toString());
                        if(tb_Analisis_Examen.getValueAt(filaselec, 15).toString().equalsIgnoreCase("A")){
                        frm_LAB_ANALISIS_EXAMEN.chActivo.setSelected(true);
                        }else{  
                            frm_LAB_ANALISIS_EXAMEN.chActivo.setSelected(false);
                        }
                        frm_LAB_ANALISIS_EXAMEN.txtObservacion.setText(tb_Analisis_Examen.getValueAt(filaselec, 16).toString());

                        frm_LAB_ANALISIS_EXAMEN.btnBuscarClasif.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.btnBuscarCPT.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.txtNombreExamen.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.txtAbrev.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.cbxTipoProc.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.cbxRestric.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.txtExplicacion.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.chActivo.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.txtObservacion.setEnabled(false);
                        frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setEnabled(false);
                         frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setBackground(Color.lightGray);
                         frm_LAB_ANALISIS_EXAMEN.btn_Agregar.setEnabled(false);
                         frm_LAB_ANALISIS_EXAMEN.btn_Quitar.setEnabled(false);
                        
                          frm_LAB_ANALISIS_EXAMEN.btnguardar.setEnabled(false);
                           frm_LAB_ANALISIS_EXAMEN.btnmodificar.setEnabled(true);
                           frm_LAB_ANALISIS_EXAMEN.btneliminar.setEnabled(true);
                         
            //tabla
            String consulta="";
            frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setModel(new DefaultTableModel());
            String titulos[]={"Código","Muestra"};
            muestra=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_ANALISIS_DETALLE_buscar ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Analisis_Examen.getValueAt(filaselec, 1).toString());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                muestra.addRow(fila);
                c++;
            }
            frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setModel(muestra);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(muestra);
            frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setRowSorter(elQueOrdena);
            frm_LAB_ANALISIS_EXAMEN.tb_Muestras.setModel(muestra);
            String u=PrincipalMDI.lblUsu.getText();
                             frm_LAB_ANALISIS_EXAMEN.lblUsu.setText(u);
                             
                    }
                    catch(Exception ex){
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                    // TODO add your handling code here:
    }//GEN-LAST:event_tb_Analisis_ExamenKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Analisis_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Analisis_Examen.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void cbxAnalisisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAnalisisItemStateChanged
        // TODO add your handling code here:
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxAnalisis.getSelectedIndex()>0){
                    txtBuscar.setVisible(true);
                    btnBuscar.setVisible(true);
                }

            }
            else{
                txtBuscar.setVisible(false);
                btnBuscar.setVisible(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxAnalisisItemStateChanged

    private void cbxAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAnalisisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAnalisisActionPerformed

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
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_BUSCAR_ANALISIS_EXAMEN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxAnalisis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tb_Analisis_Examen;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
