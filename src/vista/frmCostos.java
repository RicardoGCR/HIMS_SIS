/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import vista.COSTOS.TipoSustentacion;
import vista.COSTOS.Costos_Sustentacion;
import vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL;
import vista.COSTOS.Costos_Depreciacion;
import vista.COSTOS.COSTOS_SERVICIOS_VARIOS;
import vista.COSTOS.BUSCAR_ESTIMACION_COSTOS;
import vista.COSTOS.BuscarTipoSustentacion;
import vista.COSTOS.BUSCAR_COSTOS_SERVICIOS_VARIOS;
import vista.COSTOS.BUSCAR_NOMENCLATURA;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import servicios.Conexion;
import modelos.*;
import static vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL.PRODUCTO_REFERENCIAL;
import static vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL.tbProductoReferencial;
import static vista.COSTOS.COSTOS_SERVICIOS_VARIOS.lblUsu;
import static vista.COSTOS.Costos_Sustentacion.tbCostoSusten;

/**
 *
 * @author USUARIO
 */
public class frmCostos extends javax.swing.JFrame {
Conexion cn1= new Conexion();
    Connection cnn1=cn1.conectar();
    /**
     * Creates new form Costos
     */
    public frmCostos() {
        initComponents();
        cerrartodo();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        //Para colocar Fondo
        ((JPanel)getContentPane()).setOpaque(false); 
        ImageIcon uno=new ImageIcon(this.getClass().getResource("/imagenes/MODULO08 1400.jpg")); 
        JLabel fondo= new JLabel(); fondo.setIcon(uno); 
        getLayeredPane().add(fondo,JLayeredPane.FRAME_CONTENT_LAYER); 
        fondo.setBounds(0,0,uno.getIconWidth(),u­no.getIconHeight());
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                cerrar();
                validarUsuarioBoton();
                
            }
        });
    }

    private void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    public void cerrar(){
        int eleccion = JOptionPane.showConfirmDialog(rootPane,"¿Desea realmente salir del módulo?","Mensaje de Confirmación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
        if (eleccion == JOptionPane.YES_OPTION)
        {
            dispose();   
            Principal pr= new Principal();
            pr.setVisible(true);  
        }else{
        }
    }
    
        //cerrar
    public void cerrartodo (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    
                    cerrar();
                    validarUsuarioBoton();
                    
                }
});
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void confirmarSalida(){
        int valor=JOptionPane.showConfirmDialog(this, "¿Esta seguro de cerrar la aplicación?",
                "Advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION){
            dispose();   
            Principal pr= new Principal();
            pr.setVisible(true);  
        }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Gestión Hospitalaria .::. Módulo de Costos");

        lblUsu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("jLabel1");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel3.setText("Salir (Esc)");

        jMenu1.setText("Mantenimiento");
        jMenu1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenu10.setText("Estimación de Costos");
        jMenu10.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem9.setText("Costos de Nomenclatura");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem10.setText("Buscar Estimación del Costo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem10);

        jMenu1.add(jMenu10);

        jMenu9.setText("Factores de Producción");
        jMenu9.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem7.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem7.setText("Crear Factor de Producción");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem8.setText("Buscar Factor de Producción");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem8);

        jMenu1.add(jMenu9);

        jMenu7.setText("Sub Servicios");
        jMenu7.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem3.setText("Crear Sub Servicio");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem4.setText("Buscar Sub Servicio");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem4);

        jMenu1.add(jMenu7);

        jMenu8.setText("Producto Referencial");
        jMenu8.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem5.setText("Crear Producto Referencial");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem6.setText("Buscar Producto Referencial");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem6);

        jMenu1.add(jMenu8);

        jMenu11.setText("Depreciación");
        jMenu11.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem11.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem11.setText("Depreciar un Producto");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem11);

        jMenu1.add(jMenu11);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem12.setText("Costos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Ayuda");
        jMenu5.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Salir");
        jMenu6.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem1.setText("Salir del Módulo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Palatino Linotype", 0, 14)); // NOI18N
        jMenuItem2.setText("Salir del Sistema");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1179, Short.MAX_VALUE)
                .addComponent(lblUsu)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(730, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsu)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      cerrar();
 validarUsuarioBoton();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        close();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        BUSCAR_COSTOS_SERVICIOS_VARIOS bcs=new BUSCAR_COSTOS_SERVICIOS_VARIOS();
        bcs.setVisible(true);
        String u=lblUsu.getText();
        COSTOS_SERVICIOS_VARIOS.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        COSTOS_SERVICIOS_VARIOS csv=new COSTOS_SERVICIOS_VARIOS();
        csv.setVisible(true);
        String u=lblUsu.getText();
        COSTOS_SERVICIOS_VARIOS.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        COSTOS_PRODUCTO_REFERENCIAL cpr=new COSTOS_PRODUCTO_REFERENCIAL();
        cpr.setVisible(true);
        String u=lblUsu.getText();
        COSTOS_PRODUCTO_REFERENCIAL.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        COSTOS_PRODUCTO_REFERENCIAL cpr=new COSTOS_PRODUCTO_REFERENCIAL();
        cpr.PRODUCTO_REFERENCIAL.setVisible(true);
        cpr.tbProductoReferencial.getSelectionModel().setSelectionInterval(0, 0);
        cpr.tbProductoReferencial.requestFocus();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        TipoSustentacion TS = new TipoSustentacion();
        TS.setVisible(true);

        TipoSustentacion.btnGrabar.setEnabled(false);
        TipoSustentacion.btnModificar.setEnabled(false);
        TipoSustentacion.btnEliminar.setEnabled(false);
        String u=lblUsu.getText();
        TipoSustentacion.lblUsu.setText(u);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        BuscarTipoSustentacion BTS = new BuscarTipoSustentacion();
        BTS.setVisible(true);
        BuscarTipoSustentacion.tableTipoSustentacion.getSelectionModel().setSelectionInterval(0, 0);
        BuscarTipoSustentacion.tableTipoSustentacion.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        BUSCAR_NOMENCLATURA cs=new BUSCAR_NOMENCLATURA();
        cs.setVisible(true);
        String u=lblUsu.getText();
        Costos_Sustentacion.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        Costos_Depreciacion cd=new Costos_Depreciacion();
        cd.setVisible(true);
        String u=lblUsu.getText();
        Costos_Depreciacion.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        BUSCAR_ESTIMACION_COSTOS BEC = new BUSCAR_ESTIMACION_COSTOS();
        BEC.setVisible(true);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.getSelectionModel().setSelectionInterval(0, 0);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.requestFocus();
        String u=lblUsu.getText();
        COSTOS_SERVICIOS_VARIOS.lblUsu.setText(u);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
      BUSCAR_ESTIMACION_COSTOS BEC = new BUSCAR_ESTIMACION_COSTOS();
        BEC.setVisible(true);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.getSelectionModel().setSelectionInterval(0, 0);
        BUSCAR_ESTIMACION_COSTOS.tb_Buscar_Estimacion.requestFocus();
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    public void validarUsuarioBoton(){
         String u=lblUsu.getText();
        Principal.lblUsu.setText(u);
        Usuario u1=new Usuario();
        Usuario u2=new Usuario();
        if(u1.codAdmin().equalsIgnoreCase(u2.codigoAdminVali(u))==false){
        Principal.btnSistema.setEnabled(false);
        }
    }
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
            java.util.logging.Logger.getLogger(frmCostos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCostos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCostos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCostos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCostos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public static javax.swing.JLabel lblUsu;
    // End of variables declaration//GEN-END:variables
}