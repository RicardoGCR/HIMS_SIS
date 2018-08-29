/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.EC;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;
import modelos.Caja.Caja_Preventa;
import tablas.FORMATO_RX;
import static vista.PrincipalMDI.lblUsu;

/**
 *
 * @author Administrator
 */
public class EC_PRINCIPAL extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null;
    static int contador =0;
    int velocidad=1;
    /**
     * Creates new form EC_PRINCIPAL
     */
    public EC_PRINCIPAL() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        QuitarLaBarraTitulo();
        tb_examen_det.getTableHeader().setVisible(false);
        tb_examen_det.setTableHeader(null);
        Caja_Preventa RX1 = new Caja_Preventa ();
        RX1.LISTAREC_HOY(tb_examen_det);
        tb_examen_det.setDefaultRenderer(Object.class,new FORMATO_RX());
        
        Caja_Preventa RX = new Caja_Preventa ();
        RX.CONSULTAR_CITAS_EC_HOY();
        
        Caja_Preventa RXe = new Caja_Preventa ();
        RXe.CONSULTAR_CITAS_EC_HOY_EME();
        
        jButton2.setVisible(false);
        jButton1.setVisible(false);
        lblCONTADOR.setVisible(false);
        timer.start();
    }
    
    public void Cambios(){
        switch(contador){
        case 0:
        contador = 1;
        break;
        case 1:
        jButton1.doClick();
        contador = 0;
        break;     
        }
    }
    
    Timer timer = new Timer (1000, new ActionListener (){
        public void actionPerformed(ActionEvent e){
            Cambios();
            lblCONTADOR.setText(String.valueOf(contador));
    }
    });
    
    
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

        jPanel17 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        btnCostos = new javax.swing.JButton();
        btnCostos1 = new javax.swing.JButton();
        btnCostos2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lblCONTADOR = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_examen_det = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCostos.setBackground(new java.awt.Color(102, 102, 102));
        btnCostos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCostos.setForeground(new java.awt.Color(102, 102, 102));
        btnCostos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-purchase-order-64.png"))); // NOI18N
        btnCostos.setText("Ordenes");
        btnCostos.setContentAreaFilled(false);
        btnCostos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCostos.setFocusPainted(false);
        btnCostos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCostos.setIconTextGap(30);
        btnCostos.setVerifyInputWhenFocusTarget(false);
        btnCostos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostosActionPerformed(evt);
            }
        });

        btnCostos1.setBackground(new java.awt.Color(102, 102, 102));
        btnCostos1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCostos1.setForeground(new java.awt.Color(102, 102, 102));
        btnCostos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-pass-fail-64.png"))); // NOI18N
        btnCostos1.setText("Resultados");
        btnCostos1.setContentAreaFilled(false);
        btnCostos1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCostos1.setFocusPainted(false);
        btnCostos1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCostos1.setIconTextGap(30);
        btnCostos1.setVerifyInputWhenFocusTarget(false);
        btnCostos1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCostos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostos1ActionPerformed(evt);
            }
        });

        btnCostos2.setBackground(new java.awt.Color(102, 102, 102));
        btnCostos2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCostos2.setForeground(new java.awt.Color(102, 102, 102));
        btnCostos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-calendar-1-64.png"))); // NOI18N
        btnCostos2.setText("Citas");
        btnCostos2.setContentAreaFilled(false);
        btnCostos2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCostos2.setFocusPainted(false);
        btnCostos2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCostos2.setIconTextGap(30);
        btnCostos2.setVerifyInputWhenFocusTarget(false);
        btnCostos2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCostos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCostos2ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblCONTADOR.setText("jLabel1");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCostos2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCostos1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton2)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblCONTADOR))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCONTADOR))
                    .addComponent(btnCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCostos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCostos2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(214, 217, 223));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Sin Resultados");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("SIS 0");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Contado 0");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Emergencia 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_examen_det = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_examen_det.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_examen_det.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5"
            }
        ));
        tb_examen_det.setRowHeight(35);
        tb_examen_det.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_examen_det.getTableHeader().setReorderingAllowed(false);
        tb_examen_det.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_examen_detMouseClicked(evt);
            }
        });
        tb_examen_det.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_examen_detKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tb_examen_det);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        System.out.println("CERRANDO EC");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tb_examen_detMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_examen_detMouseClicked

    }//GEN-LAST:event_tb_examen_detMouseClicked

    private void tb_examen_detKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_examen_detKeyPressed

    }//GEN-LAST:event_tb_examen_detKeyPressed

    private void btnCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostosActionPerformed
        EC_BUSCAR_EXAMEN_CAJA examenEC = new EC_BUSCAR_EXAMEN_CAJA();
        examenEC.setVisible(true);
        String u = lblUsu.getText();
        EC_BUSCAR_EXAMEN_CAJA.lblUsu.setText(u);
    }//GEN-LAST:event_btnCostosActionPerformed

    private void btnCostos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostos1ActionPerformed
        EC_RESULTADOS examenEC_res = new EC_RESULTADOS();
        examenEC_res.setVisible(true);
        String u = lblUsu.getText();
        EC_RESULTADOS.lblUsu.setText(u);
    }//GEN-LAST:event_btnCostos1ActionPerformed

    private void btnCostos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCostos2ActionPerformed
        EC_PROGRAMACION P = new EC_PROGRAMACION();
        P.setVisible(true);
        String u = lblUsu.getText();
        EC_PROGRAMACION.lblUsu.setText(u);
    }//GEN-LAST:event_btnCostos2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Caja_Preventa RX1 = new Caja_Preventa ();
            RX1.LISTAREC_HOY(tb_examen_det);

            Caja_Preventa RXe = new Caja_Preventa ();
            RXe.CONSULTAR_CITAS_EC_HOY_EME();

            Caja_Preventa RX = new Caja_Preventa ();
            RX.CONSULTAR_CITAS_EC_HOY();

//            Caja_Preventa RXC = new Caja_Preventa ();
//            RXC.RUTA_FUA_CANTIDAD();
//
//            Caja_Preventa RXD = new Caja_Preventa ();
//            RXD.RUTA_FUA_DIA();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
//            java.util.logging.Logger.getLogger(EC_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(EC_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(EC_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(EC_PRINCIPAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EC_PRINCIPAL().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnCostos;
    public static javax.swing.JButton btnCostos1;
    public static javax.swing.JButton btnCostos2;
    private javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCONTADOR;
    public static javax.swing.JTable tb_examen_det;
    // End of variables declaration//GEN-END:variables
}
