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
public class frm_LAB_BUSCAR_MUESTRA_EXAMEN extends javax.swing.JFrame {

    DefaultTableModel m;
    Conexion c = new Conexion();

    /**
     * Creates new form listarUsuario
     */
    public frm_LAB_BUSCAR_MUESTRA_EXAMEN() {
        initComponents();
        c.conectar();
        tb_Muestra_Examen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Muestra_Examen.doLayout();
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        LAB_Muestra_Examen_cargar();
        LAB_Muestra_Examen_formato();
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtBuscar.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });//para limpiar el txt al darle click
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtBuscar.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");

        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();

            }
        });
    }

    public void LAB_Muestra_Examen_cargar() {
        try {
            String titulos[] = {"Nº", "Código", "Muestra", "Usuario"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[4];

            Conexion obj = new Conexion();
            String consulta = "exec sp_LAB_MUESTRA_EXAMEN_listar";
            ResultSet r;
            r = obj.Listar(consulta);
            int c = 1;
            while (r.next()) {
                fila[0] = String.valueOf(c) + "º";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                fila[3] = r.getString(3);
                m.addRow(fila);
                c++;
            }
            tb_Muestra_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
        }
    }

    public void LAB_Muestra_Examen_formato() {
        tb_Muestra_Examen.getColumnModel().getColumn(0).setPreferredWidth(35);
        tb_Muestra_Examen.getColumnModel().getColumn(1).setPreferredWidth(70);
        tb_Muestra_Examen.getColumnModel().getColumn(2).setPreferredWidth(215);
        tb_Muestra_Examen.getColumnModel().getColumn(3).setPreferredWidth(100);
        tb_Muestra_Examen.getSelectionModel().setSelectionInterval(0, 0);
        tb_Muestra_Examen.requestFocus();
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
        tb_Muestra_Examen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtBuscar = new javax.swing.JTextField();
            btnBuscar = new javax.swing.JButton();
            jpanel4 = new javax.swing.JPanel();
            titulo9 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setTitle("SISGESH .::. Búsqueda de Muestra");
            setAlwaysOnTop(true);
            setMinimumSize(null);

            tb_Muestra_Examen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            tb_Muestra_Examen.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Muestra_Examen.setRowHeight(25);
            tb_Muestra_Examen.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Muestra_ExamenMouseClicked(evt);
                }
            });
            tb_Muestra_Examen.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Muestra_ExamenKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(tb_Muestra_Examen);

            txtBuscar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscar.setText("Ingresar Muestra");
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

            jpanel4.setBackground(new java.awt.Color(2, 67, 115));

            titulo9.setBackground(new java.awt.Color(0, 102, 102));
            titulo9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo9.setForeground(new java.awt.Color(255, 255, 255));
            titulo9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo9.setText("Muestras");
            titulo9.setToolTipText("");
            titulo9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            javax.swing.GroupLayout jpanel4Layout = new javax.swing.GroupLayout(jpanel4);
            jpanel4.setLayout(jpanel4Layout);
            jpanel4Layout.setHorizontalGroup(
                jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanel4Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(titulo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jpanel4Layout.setVerticalGroup(
                jpanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanel4Layout.createSequentialGroup()
                    .addComponent(titulo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(11, 11, 11))
                .addComponent(jpanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            btnBuscar.doClick();
        }

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String consulta = "";
        try {
            tb_Muestra_Examen.setModel(new DefaultTableModel());
            String titulos[] = {"Nº", "Código", "Muestra", "Usuario"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[4];

            serviciosVarios obj = new serviciosVarios();
            consulta = "exec sp_LAB_MUESTRA_EXAMEN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = String.valueOf(c) + "º";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                fila[3] = r.getString(3);
                m.addRow(fila);
                c++;
            }
            tb_Muestra_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(m);

            LAB_Muestra_Examen_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tb_Muestra_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenMouseClicked

    }//GEN-LAST:event_tb_Muestra_ExamenMouseClicked

    private void tb_Muestra_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenKeyPressed
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            try {
                dispose();
                int filaselec = tb_Muestra_Examen.getSelectedRow();
                dispose();
                frm_LAB_MUESTRA_EXAMEN lme = new frm_LAB_MUESTRA_EXAMEN();
                lme.setVisible(true);
                frm_LAB_MUESTRA_EXAMEN.txtCodigo.setText(tb_Muestra_Examen.getValueAt(filaselec, 1).toString());
                frm_LAB_MUESTRA_EXAMEN.txtMuestra.setText(tb_Muestra_Examen.getValueAt(filaselec, 2).toString());

                frm_LAB_MUESTRA_EXAMEN.txtCodigo.setEnabled(false);
                frm_LAB_MUESTRA_EXAMEN.txtMuestra.setEnabled(false);

                frm_LAB_MUESTRA_EXAMEN.btnguardar.setEnabled(false);
                frm_LAB_MUESTRA_EXAMEN.btnmodificar.setEnabled(true);
                frm_LAB_MUESTRA_EXAMEN.btneliminar.setEnabled(true);

                String u = PrincipalMDI.lblUsu.getText();
                frm_LAB_MUESTRA_EXAMEN.lblUsu.setText(u);

            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Muestra_ExamenKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
            tb_Muestra_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Muestra_Examen.requestFocus();
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
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_MUESTRA_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_MUESTRA_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_MUESTRA_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_BUSCAR_MUESTRA_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_BUSCAR_MUESTRA_EXAMEN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpanel4;
    public static javax.swing.JTable tb_Muestra_Examen;
    private javax.swing.JLabel titulo9;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
