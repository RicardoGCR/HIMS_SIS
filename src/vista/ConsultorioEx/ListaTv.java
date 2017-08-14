/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import vista.CRED.RegistroSeguimiento;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.ConsultorioEx.ConsultorioExtConsultorioCabecera;
import static vista.ConsultorioEx.MensajeTv.tbMensajes;
import static vista.Principal.fechaActual;

/**
 *
 * @author PC02
 */
public class ListaTv extends javax.swing.JFrame implements Runnable{

    ConsultorioExtConsultorioCabecera consultorio = new ConsultorioExtConsultorioCabecera();
    Thread h1;
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    public ListaTv() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);//en el centro
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
//        lblFecha.setText(fechaActual());
//        consultorio.listarConsultorioTv(tbConsultorio);
        Mensaje msj =new Mensaje();
        ListaTv.pnlMensaje.add(msj);
        try {
            msj.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnEnviar.requestFocus();
        tbPacientes.setVisible(false);
        jScrollPane5.setVisible(false);
        btnEnviar.setVisible(false);
        consultorio.listarConsultorioTv(tbPacientes);
        btnEnviar.doClick();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbPacientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            pnlMensaje = new javax.swing.JDesktopPane();
            jPanel24 = new javax.swing.JPanel();
            lblNumero4 = new javax.swing.JLabel();
            jPanel21 = new javax.swing.JPanel();
            lblNumero3 = new javax.swing.JLabel();
            jPanel18 = new javax.swing.JPanel();
            lblNumero2 = new javax.swing.JLabel();
            jPanel16 = new javax.swing.JPanel();
            lblConsultorio1 = new javax.swing.JLabel();
            lblMedico = new javax.swing.JLabel();
            jPanel15 = new javax.swing.JPanel();
            lblNumero1 = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jPanel1.setBackground(new java.awt.Color(51, 51, 51));
            jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/a.gif"))); // NOI18N
            jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, Short.MAX_VALUE)
            );

            btnEnviar.setText("Enviar");
            btnEnviar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnEnviarActionPerformed(evt);
                }
            });

            jScrollPane5.setBorder(null);

            tbPacientes.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            tbPacientes.setForeground(new java.awt.Color(102, 102, 102));
            tbPacientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbPacientes.setGridColor(new java.awt.Color(255, 255, 255));
            tbPacientes.setRowHeight(25);
            tbPacientes.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbPacientes.getTableHeader().setReorderingAllowed(false);
            tbPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbPacientesMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbPacientesMousePressed(evt);
                }
            });
            tbPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbPacientesKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbPacientesKeyReleased(evt);
                }
            });
            jScrollPane5.setViewportView(tbPacientes);

            javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
            pnlMensaje.setLayout(pnlMensajeLayout);
            pnlMensajeLayout.setHorizontalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            pnlMensajeLayout.setVerticalGroup(
                pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 117, Short.MAX_VALUE)
            );

            jPanel24.setBackground(new java.awt.Color(153, 153, 153));
            jPanel24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel24.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero4.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero4.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero4.setText("Nº");

            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
            jPanel24.setLayout(jPanel24Layout);
            jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNumero4, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
            );
            jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel21.setBackground(new java.awt.Color(153, 153, 153));
            jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel21.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero3.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero3.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero3.setText("Nº");

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            jPanel18.setBackground(new java.awt.Color(153, 153, 153));
            jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel18.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero2.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero2.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero2.setText("Nº");

            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
            jPanel18.setLayout(jPanel18Layout);
            jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(lblNumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblNumero2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
            );

            jPanel16.setBackground(new java.awt.Color(0, 204, 204));
            jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 1, true));

            lblConsultorio1.setFont(new java.awt.Font("Segoe UI Light", 0, 38)); // NOI18N
            lblConsultorio1.setForeground(new java.awt.Color(255, 255, 255));
            lblConsultorio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblConsultorio1.setText("Consultorio");

            lblMedico.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            lblMedico.setForeground(new java.awt.Color(255, 255, 255));
            lblMedico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblMedico.setText("Médico");

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblConsultorio1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addComponent(lblConsultorio1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE))
            );

            jPanel15.setBackground(new java.awt.Color(153, 153, 153));
            jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
            jPanel15.setForeground(new java.awt.Color(102, 102, 102));

            lblNumero1.setFont(new java.awt.Font("Segoe UI Light", 0, 50)); // NOI18N
            lblNumero1.setForeground(new java.awt.Color(255, 255, 255));
            lblNumero1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumero1.setText("Nº");

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 128, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNumero1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnEnviar)
                    .addGap(448, 448, 448))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tbPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMouseClicked
        
    }//GEN-LAST:event_tbPacientesMouseClicked

    private void tbPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPacientesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPacientesMousePressed

    private void tbPacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyPressed

    }//GEN-LAST:event_tbPacientesKeyPressed

    private void tbPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPacientesKeyReleased
        
    }//GEN-LAST:event_tbPacientesKeyReleased

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
    

            lblNumero1.setText("");
            lblNumero2.setText("");
            lblNumero3.setText("");
            lblNumero4.setText("");
            lblConsultorio1.setText("");

            try {
                if(tbPacientes.getRowCount()==1){
//                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==2){
//                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
//                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
//                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==3){
//                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
//                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
//                lblPaciente3.setText(String.valueOf(tbPacientes.getValueAt(2, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblNumero3.setText(String.valueOf(tbPacientes.getValueAt(2, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
//                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
//                lblConsultorio3.setText(String.valueOf(tbPacientes.getValueAt(2, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }else
                if(tbPacientes.getRowCount()==4){
//                lblPaciente1.setText(String.valueOf(tbPacientes.getValueAt(0, 0)));
//                lblPaciente2.setText(String.valueOf(tbPacientes.getValueAt(1, 0)));
//                lblPaciente3.setText(String.valueOf(tbPacientes.getValueAt(2, 0)));
//                lblPaciente4.setText(String.valueOf(tbPacientes.getValueAt(3, 0)));
                lblNumero1.setText(String.valueOf(tbPacientes.getValueAt(0, 1)));
                lblNumero2.setText(String.valueOf(tbPacientes.getValueAt(1, 1)));
                lblNumero3.setText(String.valueOf(tbPacientes.getValueAt(2, 1)));
                lblNumero4.setText(String.valueOf(tbPacientes.getValueAt(3, 1)));
                lblConsultorio1.setText(String.valueOf(tbPacientes.getValueAt(0, 2)));
//                lblConsultorio2.setText(String.valueOf(tbPacientes.getValueAt(1, 2)));
//                lblConsultorio3.setText(String.valueOf(tbPacientes.getValueAt(2, 2)));
//                lblConsultorio4.setText(String.valueOf(tbPacientes.getValueAt(3, 2)));
                lblMedico.setText(String.valueOf(tbPacientes.getValueAt(0, 3)));
                }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            try {
                Thread.sleep(7000);
                consultorio.listarConsultorioTv(tbPacientes);
                btnEnviar.doClick();
            } catch (InterruptedException e) {
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaTv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaTv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblConsultorio1;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JLabel lblNumero1;
    private javax.swing.JLabel lblNumero2;
    private javax.swing.JLabel lblNumero3;
    private javax.swing.JLabel lblNumero4;
    public static javax.swing.JDesktopPane pnlMensaje;
    public static javax.swing.JTable tbPacientes;
    // End of variables declaration//GEN-END:variables
}
