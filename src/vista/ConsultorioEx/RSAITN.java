/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

/**
 *
 * @author MYS1
 */
public class RSAITN extends javax.swing.JInternalFrame {

    /**
     * Creates new form RSAITN
     */
    public RSAITN() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel299 = new javax.swing.JPanel();
        jPanel300 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jPanel301 = new javax.swing.JPanel();
        jLabel185 = new javax.swing.JLabel();

        jPanel299.setBackground(new java.awt.Color(255, 255, 255));

        jPanel300.setBackground(new java.awt.Color(23, 160, 134));
        jPanel300.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel184.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel184.setForeground(new java.awt.Color(255, 255, 255));
        jLabel184.setText("TAMIZAJE NEONATAL");

        jPanel301.setBackground(new java.awt.Color(25, 188, 157));

        jLabel185.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel185.setForeground(new java.awt.Color(255, 255, 255));
        jLabel185.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel185.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel185MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel301Layout = new javax.swing.GroupLayout(jPanel301);
        jPanel301.setLayout(jPanel301Layout);
        jPanel301Layout.setHorizontalGroup(
            jPanel301Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel301Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel185)
                .addContainerGap())
        );
        jPanel301Layout.setVerticalGroup(
            jPanel301Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel301Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel185)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel300Layout = new javax.swing.GroupLayout(jPanel300);
        jPanel300.setLayout(jPanel300Layout);
        jPanel300Layout.setHorizontalGroup(
            jPanel300Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel300Layout.createSequentialGroup()
                .addComponent(jPanel301, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel184)
                .addContainerGap(1132, Short.MAX_VALUE))
        );
        jPanel300Layout.setVerticalGroup(
            jPanel300Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel301, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel300Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel184)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel299Layout = new javax.swing.GroupLayout(jPanel299);
        jPanel299.setLayout(jPanel299Layout);
        jPanel299Layout.setHorizontalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel300, javax.swing.GroupLayout.DEFAULT_SIZE, 1328, Short.MAX_VALUE)
        );
        jPanel299Layout.setVerticalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel299Layout.createSequentialGroup()
                .addComponent(jPanel300, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel299, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel299, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel185MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel185MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel185MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JPanel jPanel299;
    private javax.swing.JPanel jPanel300;
    private javax.swing.JPanel jPanel301;
    // End of variables declaration//GEN-END:variables
}
