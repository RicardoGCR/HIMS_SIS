/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Dimension;
import javax.swing.JComponent;

/**
 *
 * @author MYS1
 */
public class REGSEGSEGA extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
    /**
     * Creates new form REGSEGSEGA
     */
    public REGSEGSEGA() {
        initComponents();
        QuitarLaBarraTitulo();
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

        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtConsultorio1 = new javax.swing.JTextField();
        T5 = new javax.swing.JButton();
        Fecha1 = new com.toedter.calendar.JDateChooser();
        txtHematocrito3 = new javax.swing.JTextField();
        txtHematocrito4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_Detalle1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};

            setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setVisible(true);

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel12.setForeground(new java.awt.Color(51, 51, 51));
            jLabel12.setText("Fecha");

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(51, 51, 51));
            jLabel13.setText("Edad");

            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(51, 51, 51));
            jLabel14.setText("RES. Hb");

            jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel16.setForeground(new java.awt.Color(51, 51, 51));
            jLabel16.setText("Diagnóstico");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));
            jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtConsultorio1.setEditable(false);
            txtConsultorio1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtConsultorio1.setForeground(new java.awt.Color(51, 51, 51));
            txtConsultorio1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtConsultorio1.setBorder(null);
            txtConsultorio1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtConsultorio1CaretUpdate(evt);
                }
            });

            T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T5.setToolTipText("");
            T5.setContentAreaFilled(false);
            T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    T5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtConsultorio1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtConsultorio1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            Fecha1.setBackground(new java.awt.Color(255, 255, 255));
            Fecha1.setDateFormatString("dd/MM/yyyy");
            Fecha1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

            txtHematocrito3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            txtHematocrito4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(51, 51, 51));
            jLabel17.setText("Nuevo Registro___________________________________________________________________________________________________________");

            jScrollPane5.setBorder(null);
            jScrollPane5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

            tb_Detalle1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            tb_Detalle1.setForeground(new java.awt.Color(255, 255, 255));
            tb_Detalle1.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Detalle1.setGridColor(new java.awt.Color(255, 255, 255));
            tb_Detalle1.setRowHeight(25);
            tb_Detalle1.setSelectionBackground(new java.awt.Color(45, 204, 112));
            tb_Detalle1.setShowVerticalLines(false);
            tb_Detalle1.getTableHeader().setReorderingAllowed(false);
            tb_Detalle1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Detalle1MouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tb_Detalle1MousePressed(evt);
                }
            });
            tb_Detalle1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Detalle1KeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tb_Detalle1KeyReleased(evt);
                }
            });
            jScrollPane5.setViewportView(tb_Detalle1);

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(55, 55, 55)
                                    .addComponent(Fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel13)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtHematocrito3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtHematocrito4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(50, Short.MAX_VALUE))))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHematocrito3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(txtHematocrito4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtConsultorio1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsultorio1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultorio1CaretUpdate

    private void T5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T5ActionPerformed

    private void tb_Detalle1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Detalle1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Detalle1MouseClicked

    private void tb_Detalle1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Detalle1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Detalle1MousePressed

    private void tb_Detalle1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Detalle1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Detalle1KeyPressed

    private void tb_Detalle1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Detalle1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Detalle1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha1;
    private javax.swing.JButton T5;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tb_Detalle1;
    public static javax.swing.JTextField txtConsultorio1;
    public static javax.swing.JTextField txtHematocrito3;
    public static javax.swing.JTextField txtHematocrito4;
    // End of variables declaration//GEN-END:variables
}
