/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ALMACEN;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.ALMACEN.Producto_Insumos;
import modelos.FormatoTablaSIGA;
import modelos.LABORATORIO.LAB_Contenedor_Muestra;
import modelos.Usuario;
import servicios.Conexion;
import static vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL.tbProductoAlmacen;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_Asig_Unidad_Medida extends javax.swing.JFrame {
DefaultTableModel m2;
DefaultTableModel m3;
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion c=new Conexion();
    /**
     * Creates new form frm_Asig_Unidad_Medida
     */
    public frm_Asig_Unidad_Medida() {
        initComponents();
         this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        cargarProducto();
        formatoProducto();
                    tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
                    tbProductoAlmacen.requestFocus();
                    
        tbProductoAlmacen.setDefaultRenderer(Object.class,new FormatoTablaSIGA());
        
        //para no intercambiar columnas
        tbProductoAlmacen.getTableHeader().setReorderingAllowed(false);
    }

    
     public void cargarProducto(){
    try {
             String titulos[]={"Nº","Código","Nombre del Producto","Cantidad de Unidad de Medida","Unidad de Medida"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            String consulta="exec ASIGNAR_UNIDAD_MEDIDA_LISTAR ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"1");
            cmd.setString(2,"");
            ResultSet r= cmd.executeQuery();
            int c=1;
            
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
                m2.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m2);
    } catch (Exception e) {
    }
}
    public void formatoProducto(){
    tbProductoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(50);
    tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(160);
    tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(360);
    tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(140);
    tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(140);
    tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
    tbProductoAlmacen.requestFocus();
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardar = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductoAlmacen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                if(colIndex==3){
                    return true;
                }else{
                    return false; //Disallow the editing of any cell
                }
            }};
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            txtBuscar = new javax.swing.JTextField();
            btnBuscarProducto = new javax.swing.JButton();
            jTextField1 = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();

            jMenuItem1.setText("Guardar (Enter)");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            guardar.add(jMenuItem1);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            tbProductoAlmacen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
            tbProductoAlmacen.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, true, true, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tbProductoAlmacen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbProductoAlmacen.setComponentPopupMenu(guardar);
            tbProductoAlmacen.setRowHeight(25);
            tbProductoAlmacen.setSelectionBackground(new java.awt.Color(0, 153, 153));
            tbProductoAlmacen.setSelectionForeground(new java.awt.Color(0, 0, 0));
            tbProductoAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbProductoAlmacenMouseClicked(evt);
                }
            });
            tbProductoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbProductoAlmacenKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    tbProductoAlmacenKeyTyped(evt);
                }
            });
            jScrollPane2.setViewportView(tbProductoAlmacen);
            if (tbProductoAlmacen.getColumnModel().getColumnCount() > 0) {
                tbProductoAlmacen.getColumnModel().getColumn(0).setResizable(false);
                tbProductoAlmacen.getColumnModel().getColumn(1).setResizable(false);
                tbProductoAlmacen.getColumnModel().getColumn(2).setResizable(false);
                tbProductoAlmacen.getColumnModel().getColumn(3).setResizable(false);
            }

            jpanel.setBackground(new java.awt.Color(126, 5, 46));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo5.setText("Catálogo de Productos");
            titulo5.setToolTipText("");
            titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblUsu.setText("Usuario");

            txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscar.setSelectionColor(new java.awt.Color(126, 5, 46));
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

            btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
            btnBuscarProducto.setBorder(null);
            btnBuscarProducto.setContentAreaFilled(false);
            btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarProductoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(22, 22, 22))))
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo5)
                        .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 6, Short.MAX_VALUE))
            );

            jTextField1.setEditable(false);
            jTextField1.setBackground(new java.awt.Color(242, 136, 136));
            jTextField1.setForeground(new java.awt.Color(242, 136, 136));

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel1.setText("Sin Cantidad de Unidad de Medida");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGap(8, 8, 8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarProducto.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbProductoAlmacen.setModel(new DefaultTableModel());

            String titulos[]={"Nº","Código","Nombre del Producto","Cantidad de Unidad de Medida","Unidad de Medida"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            consulta="exec ASIGNAR_UNIDAD_MEDIDA_LISTAR ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"2");
            cmd.setString(2,txtBuscar.getText());
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                m3.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m3);
            formatoProducto();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void tbProductoAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoAlmacenMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenMouseClicked

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
   
                 char tecla= evt.getKeyChar();
                        if(tecla==KeyEvent.VK_ENTER){
         if(tbProductoAlmacen.getRowCount()>0){    
         ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                   
        try {
          ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
          ImageIcon i1=new ImageIcon(this.getClass().getResource("/imagenes/iconos/aceptar24x24.png"));
          
          int filaselec=tbProductoAlmacen.getSelectedRow();
          if(filaselec<0){
           JOptionPane.showMessageDialog(rootPane, "Seleccione un Producto");
           }else if(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString())<0){
           JOptionPane.showMessageDialog(this, "La cantidad ingresada debe ser un valor entero positivo.",null, 1, alerta);
           tbProductoAlmacen.setValueAt("0",filaselec, 3);
           }
          else{
          int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ASIGNAR la "
              + "Cantidad de Unidad de Medida al Producto \n"+tbProductoAlmacen.getValueAt(filaselec, 2).toString()+" ?",
              "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  Producto_Insumos P= new  Producto_Insumos();
                  P.setCod_produc(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                  P.setCantidad_medida(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString()));
                  
                  if(P.ASIGNAR_UNIDAD_MEDIDA_MODIFICAR()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados",null, 1, i1);
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");
                      tbProductoAlmacen.setValueAt("0",filaselec, 3);
                  }}
           }}catch (Exception e) {
                     JOptionPane.showMessageDialog(this, "Debe Ingresar una cantidad en números",null, 1, alerta);
        }
                            }
                        }
    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void tbProductoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                   
        try {
            
        
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
          ImageIcon i1=new ImageIcon(this.getClass().getResource("/imagenes/iconos/aceptar24x24.png"));
          
           int filaselec=tbProductoAlmacen.getSelectedRow();
          if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Producto");
           }else if(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString())<0){
               JOptionPane.showMessageDialog(this, "La cantidad ingresada debe ser un valor entero positivo.",null, 1, alerta);
            tbProductoAlmacen.setValueAt("0",filaselec, 3);
           }
          else{
        int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ASIGNAR la "
                 + "Cantidad de Unidad de Medida al Producto \n"+tbProductoAlmacen.getValueAt(filaselec, 2).toString()+" ?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  Producto_Insumos P= new  Producto_Insumos();
                  P.setCod_produc(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                  P.setCantidad_medida(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString()));
                  
                  if(P.ASIGNAR_UNIDAD_MEDIDA_MODIFICAR()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados",null, 1, i1);
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");

                  }}
           }}catch (Exception e) {
                     JOptionPane.showMessageDialog(this, "Debe Ingresar una cantidad en números",null, 1, alerta);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_Asig_Unidad_Medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Unidad_Medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Unidad_Medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Unidad_Medida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Asig_Unidad_Medida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JPopupMenu guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tbProductoAlmacen;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}