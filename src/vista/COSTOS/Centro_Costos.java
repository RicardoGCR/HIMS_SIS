/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.COSTOS;

import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SITEMAS
 */
public class Centro_Costos extends javax.swing.JFrame {

    /**
     * Creates new form Centro_Costos
     */
    public Centro_Costos() {
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

        Costos_Sustentacion = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        txtBuscarCosto = new javax.swing.JTextField();
        btnBuscarCosto = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbCostoSusten = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnGrabar = new javax.swing.JButton();
            btnEliminar = new javax.swing.JButton();
            btnModificar = new javax.swing.JButton();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTable1 = new javax.swing.JTable();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            txtCostoSIGA = new javax.swing.JTextField();
            txtCostoHIMS = new javax.swing.JTextField();
            btnBuscarCPT = new javax.swing.JButton();
            btnBuscarCPT1 = new javax.swing.JButton();

            Costos_Sustentacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            Costos_Sustentacion.setTitle("HIMS .::. Búsqueda por Nomenclatura");
            Costos_Sustentacion.setAlwaysOnTop(true);
            Costos_Sustentacion.setAutoRequestFocus(false);
            Costos_Sustentacion.setMinimumSize(new java.awt.Dimension(726, 570));

            jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
            jLabel5.setText("Busqueda Costos");

            txtBuscarCosto.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscarCosto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarCostoActionPerformed(evt);
                }
            });
            txtBuscarCosto.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarCostoKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtBuscarCostoKeyTyped(evt);
                }
            });

            btnBuscarCosto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            btnBuscarCosto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarCostoActionPerformed(evt);
                }
            });

            tbCostoSusten.setModel(new javax.swing.table.DefaultTableModel(
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
            tbCostoSusten.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbCostoSusten.setRowHeight(25);
            tbCostoSusten.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbCostoSustenMouseClicked(evt);
                }
            });
            tbCostoSusten.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbCostoSustenKeyPressed(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    tbCostoSustenKeyTyped(evt);
                }
            });
            jScrollPane20.setViewportView(tbCostoSusten);

            javax.swing.GroupLayout Costos_SustentacionLayout = new javax.swing.GroupLayout(Costos_Sustentacion.getContentPane());
            Costos_Sustentacion.getContentPane().setLayout(Costos_SustentacionLayout);
            Costos_SustentacionLayout.setHorizontalGroup(
                Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Costos_SustentacionLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(txtBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(225, 225, 225))))
            );
            Costos_SustentacionLayout.setVerticalGroup(
                Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Costos_SustentacionLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(Costos_SustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(49, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(102, 102, 102));

            jLabel1.setBackground(new java.awt.Color(255, 255, 255));
            jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Centro de Costos");

            lblUsu.setBackground(new java.awt.Color(255, 255, 255));
            lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblUsu.setText("Usuario");

            btnNuevo.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
            btnNuevo.setMnemonic('N');
            btnNuevo.setToolTipText("Nuevo (Alt+N)");
            btnNuevo.setContentAreaFilled(false);
            btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNuevoActionPerformed(evt);
                }
            });

            btnGrabar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
            btnGrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnGrabar.setMnemonic('G');
            btnGrabar.setToolTipText("Guardar (Alt+G)");
            btnGrabar.setContentAreaFilled(false);
            btnGrabar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGrabar.setEnabled(false);
            btnGrabar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGrabarActionPerformed(evt);
                }
            });

            btnEliminar.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
            btnEliminar.setMnemonic('E');
            btnEliminar.setToolTipText("Eliminar (Alt+E)");
            btnEliminar.setContentAreaFilled(false);
            btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnEliminarActionPerformed(evt);
                }
            });

            btnModificar.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
            btnModificar.setMnemonic('M');
            btnModificar.setToolTipText("Modificar (Alt+M)");
            btnModificar.setContentAreaFilled(false);
            btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnModificar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnModificarActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                    .addComponent(lblUsu)
                    .addGap(17, 17, 17))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            );

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
            jScrollPane1.setViewportView(jTable1);

            jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel2.setText("Costos SIGA");

            jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel3.setText("Costos HIMS");

            txtCostoSIGA.setEnabled(false);

            txtCostoHIMS.setText(" ");
            txtCostoHIMS.setEnabled(false);

            btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarCPTActionPerformed(evt);
                }
            });

            btnBuscarCPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            btnBuscarCPT1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarCPT1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCostoSIGA, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCostoHIMS, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(218, 218, 218)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(txtCostoSIGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtCostoHIMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 92, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtCostoSIGA.setText("");
        txtCostoHIMS.setText("");
        btnModificar.setEnabled(false);      
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGrabar.setEnabled(true);
        txtCostoSIGA.setEnabled(true );
        txtCostoHIMS.setEnabled(true);  
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        try{
            if (txtCostoHIMS.getText().trim().equals("") || txtCostoSIGA.getText().trim().equals("")){
                 JOptionPane.showMessageDialog(this,"Seleccion los costos");
            }else{
                btnModificar.setEnabled(true);      
                btnNuevo.setEnabled(true);
                btnEliminar.setEnabled(true );
                btnGrabar.setEnabled(false);
                txtCostoSIGA.setEnabled(false );
                txtCostoHIMS.setEnabled(false);   
            }
            }catch(Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        btnModificar.setEnabled(false);      
        btnNuevo.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGrabar.setEnabled(true);
        txtCostoSIGA.setEnabled(true );
        txtCostoHIMS.setEnabled(true);   
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 )
            {
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Personal a eliminar");
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT1ActionPerformed
        jLabel5.setText("Busqueda Costos - HIMS");
        Costos_Sustentacion.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPT1ActionPerformed

    private void txtBuscarCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCostoActionPerformed

    private void txtBuscarCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCostoKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarCosto.doClick();
        }
    }//GEN-LAST:event_txtBuscarCostoKeyPressed

    private void txtBuscarCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCostoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCostoKeyTyped
    private void btnBuscarCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCostoActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            DefaultTableModel cs;
            tbCostoSusten.setModel(new DefaultTableModel());
            String titulos[]={"Codigo","Cod Precio","Codigo CPT","Servicio","Área",
                "Forma de Pago","Precio","Tiempo(h)","Tiempo(min)","Saldo","Nomenclatura"};
            cs=new DefaultTableModel(null,titulos);
            JTable p=new JTable(cs);
            String fila[]=new String[11];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarCosto.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                for (int i=0; i<11; i++){
                    fila[i]=r.getString(i+1);
                }
                cs.addRow(fila);
            }
            tbCostoSusten.setModel(cs);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(cs);
            tbCostoSusten.setRowSorter(elQueOrdena);
            tbCostoSusten.setModel(cs);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarCostoActionPerformed

    private void tbCostoSustenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCostoSustenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCostoSustenMouseClicked

    private void tbCostoSustenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCostoSustenKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Costos_Sustentacion.setVisible(false);
           
        }
    }//GEN-LAST:event_tbCostoSustenKeyPressed

    private void tbCostoSustenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCostoSustenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCostoSustenKeyTyped

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
      jLabel5.setText("Busqueda Costos - SIGA");
      Costos_Sustentacion.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

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
            java.util.logging.Logger.getLogger(Centro_Costos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Centro_Costos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Centro_Costos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Centro_Costos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Centro_Costos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDialog Costos_Sustentacion;
    public static javax.swing.JButton btnBuscarCPT;
    public static javax.swing.JButton btnBuscarCPT1;
    private javax.swing.JButton btnBuscarCosto;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGrabar;
    public static javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tbCostoSusten;
    private javax.swing.JTextField txtBuscarCosto;
    private javax.swing.JTextField txtCostoHIMS;
    private javax.swing.JTextField txtCostoSIGA;
    // End of variables declaration//GEN-END:variables
}
