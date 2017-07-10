/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import modelos.cuentaPorPagar.CuentasPorPagarBoletaElectronica;
import modelos.cuentaPorPagar.CuentasPorPagarVentasConsolidadoCabecera;

/**
 *
 * @author MYS3
 */
public class BoletaElectronica extends javax.swing.JFrame {

    CuentasPorPagarBoletaElectronica boleta = new CuentasPorPagarBoletaElectronica();
    public BoletaElectronica() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
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
        boleta.ventasPorContado(tbBoletasCabecera, "", "", "");
        boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 14)), "");
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtDni = new javax.swing.JTextField();
        lbldetalle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        tablaS = new javax.swing.JScrollPane();
        tbBoletasCabecera = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            tablaS1 = new javax.swing.JScrollPane();
            tbBoletaDetalles = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                dtFechaI = new com.toedter.calendar.JDateChooser();
                dtFechaF = new com.toedter.calendar.JDateChooser();
                btnNuevo = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(41, 127, 184));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Consolidado de<span style=\"font-size:'15px'\"><br>Boletas Electrónicas</br></span></html>");

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                txtDni.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                txtDni.setForeground(new java.awt.Color(51, 51, 51));
                txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDni.setBorder(null);
                txtDni.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDniCaretUpdate(evt);
                    }
                });
                txtDni.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDniActionPerformed(evt);
                    }
                });
                txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtDniKeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lbldetalle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                lbldetalle.setText("Ingrese el DNI del paciente");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbldetalle)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldetalle)
                        .addContainerGap(277, Short.MAX_VALUE))
                );

                jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                tablaS.setBackground(new java.awt.Color(255, 255, 255));
                tablaS.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbBoletasCabecera.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbBoletasCabecera.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Título 1", "Título 2", "Título 3", "Título 4"
                    }
                ));
                tbBoletasCabecera.setGridColor(new java.awt.Color(255, 255, 255));
                tbBoletasCabecera.setRowHeight(25);
                tbBoletasCabecera.setSelectionBackground(new java.awt.Color(153, 153, 153));
                tbBoletasCabecera.getTableHeader().setReorderingAllowed(false);
                tbBoletasCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbBoletasCabeceraMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbBoletasCabeceraMousePressed(evt);
                    }
                });
                tbBoletasCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbBoletasCabeceraKeyPressed(evt);
                    }
                });
                tablaS.setViewportView(tbBoletasCabecera);

                tablaS1.setBackground(new java.awt.Color(255, 255, 255));
                tablaS1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbBoletaDetalles.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbBoletaDetalles.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Título 1", "Título 2", "Título 3", "Título 4"
                    }
                ));
                tbBoletaDetalles.setGridColor(new java.awt.Color(255, 255, 255));
                tbBoletaDetalles.setRowHeight(25);
                tbBoletaDetalles.setSelectionBackground(new java.awt.Color(153, 153, 153));
                tbBoletaDetalles.getTableHeader().setReorderingAllowed(false);
                tbBoletaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbBoletaDetallesMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbBoletaDetallesMousePressed(evt);
                    }
                });
                tbBoletaDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbBoletaDetallesKeyPressed(evt);
                    }
                });
                tablaS1.setViewportView(tbBoletaDetalles);

                dtFechaI.setForeground(new java.awt.Color(51, 51, 51));
                dtFechaI.setDateFormatString("dd/MM/yyyy");
                dtFechaI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                dtFechaF.setForeground(new java.awt.Color(51, 51, 51));
                dtFechaF.setDateFormatString("dd/MM/yyyy");
                dtFechaF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                btnNuevo.setText("Iniciar");
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnNuevo.setIconTextGap(30);
                btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablaS)
                    .addComponent(tablaS1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addGap(401, 401, 401))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tablaS1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                jScrollPane1.setViewportView(jPanel2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtDniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniCaretUpdate

    }//GEN-LAST:event_txtDniCaretUpdate

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed

    }//GEN-LAST:event_txtDniKeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tbBoletasCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraMouseClicked
        if(evt.getClickCount()==1){
            boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 14)), "");
        }
    }//GEN-LAST:event_tbBoletasCabeceraMouseClicked

    private void tbBoletasCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraMousePressed

    }//GEN-LAST:event_tbBoletasCabeceraMousePressed

    private void tbBoletasCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraKeyPressed
        
    }//GEN-LAST:event_tbBoletasCabeceraKeyPressed

    private void tbBoletaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletaDetallesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesMouseClicked

    private void tbBoletaDetallesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletaDetallesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesMousePressed

    private void tbBoletaDetallesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBoletaDetallesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesKeyPressed

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
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoletaElectronica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnNuevo;
    private com.toedter.calendar.JDateChooser dtFechaF;
    private com.toedter.calendar.JDateChooser dtFechaI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JScrollPane tablaS;
    private javax.swing.JScrollPane tablaS1;
    public static javax.swing.JTable tbBoletaDetalles;
    public static javax.swing.JTable tbBoletasCabecera;
    public static javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
