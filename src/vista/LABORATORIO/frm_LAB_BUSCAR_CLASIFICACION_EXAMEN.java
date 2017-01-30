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
public class frm_LAB_BUSCAR_CLASIFICACION_EXAMEN extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();
    /**
     * Creates new form listarUsuario
     */
    public frm_LAB_BUSCAR_CLASIFICACION_EXAMEN() {
        initComponents();
        c.conectar();
        tb_Clasificacion_Examen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Clasificacion_Examen.doLayout();
                setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        LAB_Clasificacion_Examen_cargar();
        LAB_Clasificacion_Examen_formato();
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
public void LAB_Clasificacion_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","Nombre","Cogd_uni_or","NomUni","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_listar";
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
    tb_Clasificacion_Examen.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Clasificacion_Examen.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Clasificacion_Examen.getColumnModel().getColumn(2).setPreferredWidth(170);
    tb_Clasificacion_Examen.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Clasificacion_Examen.getColumnModel().getColumn(4).setPreferredWidth(250);
    tb_Clasificacion_Examen.getColumnModel().getColumn(5).setPreferredWidth(250);
    
            //Ocultar    
    tb_Clasificacion_Examen.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Clasificacion_Examen.getColumnModel().getColumn(4).setMaxWidth(0);
    tb_Clasificacion_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Clasificacion_Examen.requestFocus();
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
        tb_Clasificacion_Examen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLabel1 = new javax.swing.JLabel();
            txtBuscar = new javax.swing.JTextField();
            btnBuscar = new javax.swing.JButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("SISGESH .::. Búsqueda de Clasificación Examen");
            setAlwaysOnTop(true);

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
            tb_Clasificacion_Examen.setRowHeight(25);
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
            jScrollPane1.setViewportView(tb_Clasificacion_Examen);

            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
            jLabel1.setText("Búsqueda ");

            txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscar.setText("Ingresar Clasificación Examen");
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

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(25, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(213, 213, 213))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
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
            tb_Clasificacion_Examen.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Nombre","Cod_uni_org","UniOrg","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
                    consulta="exec sp_LAB_CLASIFICACION_EXAMEN_buscar ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                m.addRow(fila);
                c++;
            }
            tb_Clasificacion_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Clasificacion_Examen.setRowSorter(elQueOrdena);
            this.tb_Clasificacion_Examen.setModel(m);
            
            LAB_Clasificacion_Examen_formato();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tb_Clasificacion_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Clasificacion_ExamenMouseClicked
 
    }//GEN-LAST:event_tb_Clasificacion_ExamenMouseClicked

    private void tb_Clasificacion_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Clasificacion_ExamenKeyPressed
                char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        dispose();
                        int filaselec=tb_Clasificacion_Examen.getSelectedRow();
                        frm_LAB_CLASIFICACION_EXAMEN lme= new  frm_LAB_CLASIFICACION_EXAMEN();   
                        lme.setVisible(true);
                         frm_LAB_CLASIFICACION_EXAMEN.txtCodigo.setText(tb_Clasificacion_Examen.getValueAt(filaselec, 1).toString());
                         frm_LAB_CLASIFICACION_EXAMEN.txtClasificacion.setText(tb_Clasificacion_Examen.getValueAt(filaselec, 2).toString());
                        frm_LAB_CLASIFICACION_EXAMEN.txtCodUni.setText(tb_Clasificacion_Examen.getValueAt(filaselec, 3).toString());
                         frm_LAB_CLASIFICACION_EXAMEN.txtObservacion.setText(tb_Clasificacion_Examen.getValueAt(filaselec, 5).toString());

                         frm_LAB_CLASIFICACION_EXAMEN.txtCodigo.setEnabled(false);
                         frm_LAB_CLASIFICACION_EXAMEN.btnBuscarUnidad.setEnabled(false);
                         frm_LAB_CLASIFICACION_EXAMEN.txtClasificacion.setEnabled(false);
                        frm_LAB_CLASIFICACION_EXAMEN.txtObservacion.setEnabled(false);
                        
                          frm_LAB_CLASIFICACION_EXAMEN.btnguardar.setEnabled(false);
                           frm_LAB_CLASIFICACION_EXAMEN.btnmodificar.setEnabled(true);
                           frm_LAB_CLASIFICACION_EXAMEN.btneliminar.setEnabled(true);
                          
                          String u=PrincipalMDI.lblUsu.getText();
                             frm_LAB_CLASIFICACION_EXAMEN.lblUsu.setText(u);
                                                 
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                    // TODO add your handling code here:
    }//GEN-LAST:event_tb_Clasificacion_ExamenKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Clasificacion_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Clasificacion_Examen.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

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
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_CLASIFICACION_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_CLASIFICACION_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_CLASIFICACION_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_CLASIFICACION_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_BUSCAR_CLASIFICACION_EXAMEN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tb_Clasificacion_Examen;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
