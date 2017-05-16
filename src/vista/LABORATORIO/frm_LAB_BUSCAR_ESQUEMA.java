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
public class frm_LAB_BUSCAR_ESQUEMA extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();
    /**
     * Creates new form listarUsuario
     */
    public frm_LAB_BUSCAR_ESQUEMA() {
        initComponents();
        c.conectar();
        tb_Esquema_Analisis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Esquema_Analisis.doLayout();
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
            tb_Esquema_Analisis.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Esquema_Analisis.setRowSorter(elQueOrdena);
            this.tb_Esquema_Analisis.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
   public void LAB_Analisis_Examen_formato(){
    tb_Esquema_Analisis.getColumnModel().getColumn(0).setPreferredWidth(30);
    tb_Esquema_Analisis.getColumnModel().getColumn(1).setPreferredWidth(120);
    tb_Esquema_Analisis.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Esquema_Analisis.getColumnModel().getColumn(5).setPreferredWidth(120);
    tb_Esquema_Analisis.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Esquema_Analisis.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_Esquema_Analisis.getColumnModel().getColumn(8).setPreferredWidth(150);
    tb_Esquema_Analisis.getColumnModel().getColumn(9).setPreferredWidth(100);
    tb_Esquema_Analisis.getColumnModel().getColumn(10).setPreferredWidth(90);
    tb_Esquema_Analisis.getColumnModel().getColumn(11).setPreferredWidth(90);
    tb_Esquema_Analisis.getColumnModel().getColumn(12).setPreferredWidth(100);
    tb_Esquema_Analisis.getColumnModel().getColumn(13).setPreferredWidth(200);
    tb_Esquema_Analisis.getColumnModel().getColumn(14).setPreferredWidth(200);
    tb_Esquema_Analisis.getColumnModel().getColumn(15).setPreferredWidth(50);
    tb_Esquema_Analisis.getColumnModel().getColumn(16).setPreferredWidth(200);
            //Ocultar    
    tb_Esquema_Analisis.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Esquema_Analisis.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Esquema_Analisis.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Esquema_Analisis.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Esquema_Analisis.getSelectionModel().setSelectionInterval(0, 0);
            tb_Esquema_Analisis.requestFocus();
}
    
    
   

    public void LAB_Esquema_formato(){
            //Ocultar    
        frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(0).setMinWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(0).setMaxWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(4).setMinWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(4).setMaxWidth(0);
    
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getSelectionModel().setSelectionInterval(0, 0);
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.requestFocus();
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
        tb_Esquema_Analisis = new javax.swing.JTable(){
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

            tb_Esquema_Analisis.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            tb_Esquema_Analisis.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Esquema_Analisis.setRowHeight(25);
            tb_Esquema_Analisis.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Esquema_AnalisisMouseClicked(evt);
                }
            });
            tb_Esquema_Analisis.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Esquema_AnalisisKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(tb_Esquema_Analisis);

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
                    .addGap(0, 312, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(282, 282, 282))
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
  String consulta="";
        try {
            
            tb_Esquema_Analisis.setModel(new DefaultTableModel());
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
            tb_Esquema_Analisis.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Esquema_Analisis.setRowSorter(elQueOrdena);
            this.tb_Esquema_Analisis.setModel(m);
            
            LAB_Analisis_Examen_formato();            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tb_Esquema_AnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Esquema_AnalisisMouseClicked
 
    }//GEN-LAST:event_tb_Esquema_AnalisisMouseClicked

    private void tb_Esquema_AnalisisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Esquema_AnalisisKeyPressed
            char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        dispose();
                        int filaselec=tb_Esquema_Analisis.getSelectedRow();
                        frm_LAB_ESQUEMA_RESULTADO lme= new  frm_LAB_ESQUEMA_RESULTADO();  
                        lme.setVisible(true);   
                        
                        LAB_Clasificacion_Examen ce=new LAB_Clasificacion_Examen();
                        frm_LAB_ESQUEMA_RESULTADO.txtUnidadOrganica.setText(ce.LAB_Clasificacion_Examen_buscar(tb_Esquema_Analisis.getValueAt(filaselec, 5).toString()));
                        frm_LAB_ESQUEMA_RESULTADO.txtCodClasificacion.setText(tb_Esquema_Analisis.getValueAt(filaselec, 2).toString());
                        frm_LAB_ESQUEMA_RESULTADO.txtClasificacion.setText(tb_Esquema_Analisis.getValueAt(filaselec, 5).toString());
                        frm_LAB_ESQUEMA_RESULTADO.txtCodAnalisis.setText(tb_Esquema_Analisis.getValueAt(filaselec, 1).toString());
                        frm_LAB_ESQUEMA_RESULTADO.txtAnalisis.setText(tb_Esquema_Analisis.getValueAt(filaselec, 8).toString());
                        frm_LAB_ESQUEMA_RESULTADO.txtAbreviatura.setText(tb_Esquema_Analisis.getValueAt(filaselec, 9).toString());            
                        
                        
                             String consulta="";
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(new DefaultTableModel());
            String titulos[]={"cod esquema","Nombre de Resultado","Resultado por Defecto" ,
"Tipo Esquema","cod Uni","Unidad de Medida","Valores","Resultados"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_ESQUEMA_RESULTADO_buscar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Esquema_Analisis.getValueAt(filaselec, 1).toString());
            cmd.setInt(2,1);
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
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setRowSorter(elQueOrdena);
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(m);
            LAB_Esquema_formato();
            String u=PrincipalMDI.lblUsu.getText();
                             frm_LAB_ESQUEMA_RESULTADO.lblUsu.setText(u);
                    }catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                    // TODO add your handling code here:
    }//GEN-LAST:event_tb_Esquema_AnalisisKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Esquema_Analisis.getSelectionModel().setSelectionInterval(0, 0);
            tb_Esquema_Analisis.requestFocus();
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
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_BUSCAR_ESQUEMA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox cbxAnalisis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tb_Esquema_Analisis;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
