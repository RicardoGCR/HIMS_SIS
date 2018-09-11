/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Dimension;
import javax.swing.JComponent;
import vista.CRED.RegistroSeguimiento;
import vista.PrincipalMDI;
import static vista.PrincipalMDI.lblUsu;

/**
 *
 * @author Administrator
 */
public class CEX_PRINCIPAL extends javax.swing.JInternalFrame  {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null;
    /**
     * Creates new form CEX_PRINCIPAL
     */
    public CEX_PRINCIPAL() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        QuitarLaBarraTitulo();
        jButton2.setVisible(false);
    }
    
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel86 = new javax.swing.JPanel();
        btnTriaje = new javax.swing.JButton();
        btnConsultorio = new javax.swing.JButton();
        btnAdmEme2 = new javax.swing.JButton();
        btnAdmEme1 = new javax.swing.JButton();
        btnConsultorio1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        jPanel86.setBackground(new java.awt.Color(255, 255, 255));

        btnTriaje.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnTriaje.setForeground(new java.awt.Color(51, 51, 51));
        btnTriaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-book-64.png"))); // NOI18N
        btnTriaje.setText("Triaje");
        btnTriaje.setToolTipText("");
        btnTriaje.setContentAreaFilled(false);
        btnTriaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTriaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTriaje.setIconTextGap(30);
        btnTriaje.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTriaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTriajeActionPerformed(evt);
            }
        });

        btnConsultorio.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnConsultorio.setForeground(new java.awt.Color(51, 51, 51));
        btnConsultorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-check-file-64.png"))); // NOI18N
        btnConsultorio.setText("Asignación");
        btnConsultorio.setToolTipText("");
        btnConsultorio.setContentAreaFilled(false);
        btnConsultorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultorio.setIconTextGap(30);
        btnConsultorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultorioActionPerformed(evt);
            }
        });

        btnAdmEme2.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmEme2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnAdmEme2.setForeground(new java.awt.Color(51, 51, 51));
        btnAdmEme2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-pregnant-64.png"))); // NOI18N
        btnAdmEme2.setText("<HTML>Carnet <BR>perinatal<HTML>");
        btnAdmEme2.setContentAreaFilled(false);
        btnAdmEme2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmEme2.setFocusPainted(false);
        btnAdmEme2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmEme2.setIconTextGap(30);
        btnAdmEme2.setVerifyInputWhenFocusTarget(false);
        btnAdmEme2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmEme2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmEme2MouseEntered(evt);
            }
        });
        btnAdmEme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmEme2ActionPerformed(evt);
            }
        });

        btnAdmEme1.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmEme1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAdmEme1.setForeground(new java.awt.Color(102, 102, 102));
        btnAdmEme1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-children-64.png"))); // NOI18N
        btnAdmEme1.setText("CRED");
        btnAdmEme1.setContentAreaFilled(false);
        btnAdmEme1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmEme1.setFocusPainted(false);
        btnAdmEme1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmEme1.setIconTextGap(30);
        btnAdmEme1.setVerifyInputWhenFocusTarget(false);
        btnAdmEme1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmEme1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmEme1MouseEntered(evt);
            }
        });
        btnAdmEme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmEme1ActionPerformed(evt);
            }
        });

        btnConsultorio1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        btnConsultorio1.setForeground(new java.awt.Color(51, 51, 51));
        btnConsultorio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-check-file-64.png"))); // NOI18N
        btnConsultorio1.setText("Asignación");
        btnConsultorio1.setToolTipText("");
        btnConsultorio1.setContentAreaFilled(false);
        btnConsultorio1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultorio1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConsultorio1.setIconTextGap(30);
        btnConsultorio1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConsultorio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultorio1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel86Layout = new javax.swing.GroupLayout(jPanel86);
        jPanel86.setLayout(jPanel86Layout);
        jPanel86Layout.setHorizontalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addGroup(jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel86Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConsultorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdmEme1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdmEme2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel86Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel86Layout.setVerticalGroup(
            jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel86Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel86Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnConsultorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmEme2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addComponent(btnConsultorio1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmEme1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTriajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTriajeActionPerformed
        Triaje triaje = new Triaje();
        triaje.setVisible(true);
        String u=lblUsu.getText();
        Triaje.lblUsu.setText(u);
    }//GEN-LAST:event_btnTriajeActionPerformed

    private void btnConsultorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultorioActionPerformed
        ConsultorioAsignacion consultorio = new ConsultorioAsignacion();
        consultorio.setVisible(true);
        String u=lblUsu.getText();
        ConsultorioAsignacion.lblUsu.setText(u);
    }//GEN-LAST:event_btnConsultorioActionPerformed

    private void btnAdmEme2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEme2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEme2MouseEntered

    private void btnAdmEme2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEme2ActionPerformed
        RegistroEmbarazo REM = new RegistroEmbarazo();
        REM.setVisible(true);
        String u=lblUsu.getText();
        RegistroEmbarazo.lblUsu.setText(u);
    }//GEN-LAST:event_btnAdmEme2ActionPerformed

    private void btnAdmEme1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmEme1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdmEme1MouseEntered

    private void btnAdmEme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmEme1ActionPerformed
        RegistroSeguimiento frmCRED = new RegistroSeguimiento();
        frmCRED.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        frmCRED.lblUsu.setText(u);
    }//GEN-LAST:event_btnAdmEme1ActionPerformed

    private void btnConsultorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultorio1ActionPerformed
        Consultorio CFR = new Consultorio();
        CFR.setVisible(true);
        String u=PrincipalMDI.lblUsu.getText();
        //CFR.lblusu.setText(u);
    }//GEN-LAST:event_btnConsultorio1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        System.out.println("CERRANDO EC");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CEX_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CEX_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CEX_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CEX_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CEX_PRINCIPAL().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAdmEme1;
    public static javax.swing.JButton btnAdmEme2;
    private javax.swing.JButton btnConsultorio;
    private javax.swing.JButton btnConsultorio1;
    private javax.swing.JButton btnTriaje;
    public static javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel86;
    // End of variables declaration//GEN-END:variables
}
