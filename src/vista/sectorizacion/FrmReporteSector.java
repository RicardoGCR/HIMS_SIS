/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.sectorizacion;

import app.sis.gob.pe.edi.sisws.Sisws;
import app.sis.gob.pe.edi.sisws.SiswsSoap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import modelos.Caja.Caja_NuevaVenta;
import modelos.sectorizacion.Sector;
import webservice.handler.MsgHandler;

/**
 *
 * @author SITEMAS
 */
public class FrmReporteSector extends javax.swing.JFrame {

    /**
     * Creates new form FrmReporteSector
     */
    public FrmReporteSector() {
        initComponents();
        jTextField2.setVisible(false);
        jFormattedTextField1.setVisible(false);
        jFormattedTextField2.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        btnEliminar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxopc1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxPACIENTE = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jComboBox3 = new javax.swing.JComboBox<>();

        jPanel8.setBackground(new java.awt.Color(101, 166, 136));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Sectorización - Paciente");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnEliminar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar1.setForeground(new java.awt.Color(240, 240, 240));
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imprimir-32.png"))); // NOI18N
        btnEliminar1.setMnemonic('E');
        btnEliminar1.setText("Imprimir");
        btnEliminar1.setContentAreaFilled(false);
        btnEliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar1.setIconTextGap(30);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnEliminar1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Edad");

        cbxopc1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MENOR A", "MAYOR A", "IGUAL A", "ENTRE" }));
        cbxopc1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxopc1ItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("PACIENTE");

        cbxPACIENTE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIÑOS", "GESTANTES" }));
        cbxPACIENTE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPACIENTEItemStateChanged(evt);
            }
        });

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AÑOS" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jFormattedTextField1))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2)
                            .addComponent(jFormattedTextField2)))
                    .addComponent(cbxopc1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxPACIENTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxPACIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxopc1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        Sector se= new Sector();
        try {
            String PACIENTE, opc1, txt1, txt2;
            PACIENTE=cbxPACIENTE.getSelectedItem().toString();
            opc1=cbxopc1.getSelectedItem().toString();
             if (PACIENTE.equals("NIÑOS")){
                 txt1=jTextField1.getText();
                 txt2=jTextField2.getText();
             }else{
                 txt1=jFormattedTextField1.getText();
                 txt2=jFormattedTextField2.getText();
             }
             System.out.println(PACIENTE);     
             System.out.println(opc1);
             System.out.println(txt1);
             System.out.println(txt2); 
            se.Sector_Paciente(PACIENTE, opc1, txt1, txt2);
        } catch (Exception e) {
           JOptionPane.showMessageDialog(rootPane, "Error Reporte : "+e.getMessage());
        }
        //BuscarHC();
    }//GEN-LAST:event_btnEliminar1ActionPerformed
   public void BuscarHC(){
        String consulta="";   
        String consulta1="";
        try {
            Caja_NuevaVenta obj=new Caja_NuevaVenta();
            consulta="exec CAJA_BUSCAR_HISTORIAS ?";      
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            ResultSet r= cmd.executeQuery();
            int c=1;
            String DNI;
           while(r.next()){
                DNI=r.getString(1);
                System.out.println(DNI);
                try {
                    String result = buscarAseguradosDocIdent("05195", "1", DNI);
                    //System.out.println(result);
                    if (!result.trim().equals("")){
                        String nombrePalabra =result;
                        char palabraBuscar = '|';
                        int cont =0;
                        int n5=0;int n6=0;
                        for(int i=0; i < nombrePalabra.length(); i++ ){
                            if( nombrePalabra.charAt(i)== palabraBuscar){
                                cont+=1;
                                if (cont==11){
                                    n5=i;//dir0
                                }
                                 if (cont==12){
                                    n6=i;//dir0
                                }
                            }
                        }
                        String dir;
                        dir=(nombrePalabra.substring( n5+1, n6)); 
                        consulta1="EXEC [SIS_PACIENTE] ?,?";      
                        PreparedStatement cmd1 = obj.getCn().prepareStatement(consulta1);
                        cmd1.setString(1, DNI);      
                        cmd1.setString(2, dir);
                        cmd1.execute();
                        //System.out.println(dir);
                    }//else{
//                        consulta1="EXEC [SIS_PACIENTE_NO] ?";      
//                        PreparedStatement cmd1 = obj.getCn().prepareStatement(consulta1);
//                        cmd1.setString(1, DNI);
//                        cmd1.execute();
//                        //System.out.println("No sis");
//                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                c++;
            }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    private static String buscarAseguradosDocIdent(java.lang.String idEess, java.lang.String tipoDoc, java.lang.String documento) {
        Sisws service = new Sisws();
        SiswsSoap port = service.getSiswsSoap();

        Binding binding = ((BindingProvider) port).getBinding();

        List<Handler> handlersList = new ArrayList<Handler>();
        handlersList.add(new MsgHandler());  //SOAPMessageContext context
        binding.setHandlerChain(handlersList);

        return port.buscarAseguradosDocIdent(idEess, tipoDoc, documento);
    }
    public void opc(){
        String opc=cbxPACIENTE.getSelectedItem().toString();
        String opc1=cbxopc1.getSelectedItem().toString();
        System.out.println(opc);
        System.out.println(opc1);
        if (opc.equals("NIÑOS")){
            jTextField1.setVisible(true);            
            jTextField1.setText("");            
            jTextField2.setText(""); 
            jFormattedTextField1.setVisible(false);
            if (opc1.equals("ENTRE")){
                jTextField2.setVisible(true);
                jFormattedTextField2.setVisible(false);
            }else{
                jTextField2.setVisible(false);
            }
            jLabel1.setText("EDAD");
            jComboBox3.setVisible(true);
        }else{
            jTextField1.setVisible(false);            
            jTextField1.setText("");            
            jTextField2.setText(""); 
            jFormattedTextField1.setVisible(true);
            if (opc1.equals("ENTRE")){
                jTextField2.setVisible(false);
                jFormattedTextField2.setVisible(true);
            }else{
                jFormattedTextField2.setVisible(false);
            }
            jLabel1.setText("Fecha prov. Parto");
            jComboBox3.setVisible(false);
        }
    }
    private void cbxPACIENTEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPACIENTEItemStateChanged
        opc();
    }//GEN-LAST:event_cbxPACIENTEItemStateChanged

    private void cbxopc1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxopc1ItemStateChanged
        opc();
    }//GEN-LAST:event_cbxopc1ItemStateChanged

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmReporteSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReporteSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReporteSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReporteSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReporteSector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnEliminar1;
    private javax.swing.JComboBox<String> cbxPACIENTE;
    private javax.swing.JComboBox<String> cbxopc1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel titulo5;
    // End of variables declaration//GEN-END:variables
}
