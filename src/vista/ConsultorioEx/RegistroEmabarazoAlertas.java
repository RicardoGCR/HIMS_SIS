/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAtencionPrenatal;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalCabecera;
import tablas.FormatoTablaConsultorioExtAlertas;
import tablas.FormatoTablaMovCONEXT;

/**
 *
 * @author MYS1
 */
public class RegistroEmabarazoAlertas extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    ConsultorioExtCarnetPerinatalCabecera cabecera = new ConsultorioExtCarnetPerinatalCabecera();
    public RegistroEmabarazoAlertas() {
        initComponents();
        QuitarLaBarraTitulo();
        pnlDatosPaciente.setVisible(false);
        ConsultorioExtCarnetPerinatalAtencionPrenatal consultorio1 = new ConsultorioExtCarnetPerinatalAtencionPrenatal();
        consultorio1.calculoAlertas();
        consultorio1.consultorioExAtencionPrenatalAlertas(txtBuscarAlertas.getText(), tbAlertas);
        tbAlertas.setDefaultRenderer(Object.class,new FormatoTablaConsultorioExtAlertas());
    }
    
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
    public void enviarDatosAlertas(){   
        pnlDatosPaciente.setVisible(true);
        int fila = tbAlertas.getSelectedRow();
        lblTelefono.setText("Nº Telefónico " + String.valueOf(tbAlertas.getValueAt(fila, 8)));
        lblCelular.setText("Celular " + String.valueOf(tbAlertas.getValueAt(fila, 9)));
        lblDistrito.setText("Distrito " + String.valueOf(tbAlertas.getValueAt(fila, 10)));
        lblSector.setText("Sector " + String.valueOf(tbAlertas.getValueAt(fila, 11)));
        lblDireccion.setText("Dirección " + String.valueOf(tbAlertas.getValueAt(fila, 12)) + " " + String.valueOf(tbAlertas.getValueAt(fila, 13)));
      }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        txtBuscarAlertas = new javax.swing.JTextField();
        T3 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        pnlDatosPaciente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDistrito = new javax.swing.JLabel();
        lblSector = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbAlertas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};

            setBorder(javax.swing.BorderFactory.createCompoundBorder());
            setVisible(true);

            jPanel1.setBackground(new java.awt.Color(232, 76, 61));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html>Atenciones<br>Prenatales<span style=\"font-size:'15px'\"><br><b>ALERTAS</b></span></html>");

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
            lblusu.setText("Silvana");
            lblusu.setFocusable(false);
            lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

            btnActualizar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnActualizar.setForeground(new java.awt.Color(240, 240, 240));
            btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/actualizar.png"))); // NOI18N
            btnActualizar.setText("Actualizar");
            btnActualizar.setContentAreaFilled(false);
            btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnActualizar.setIconTextGap(30);
            btnActualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnActualizar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnActualizarActionPerformed(evt);
                }
            });

            jPanel9.setBackground(new java.awt.Color(232, 76, 61));

            txtBuscarAlertas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtBuscarAlertas.setForeground(new java.awt.Color(51, 51, 51));
            txtBuscarAlertas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtBuscarAlertas.setToolTipText("");
            txtBuscarAlertas.setBorder(null);
            txtBuscarAlertas.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarAlertasCaretUpdate(evt);
                }
            });

            T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
            T3.setToolTipText("");
            T3.setContentAreaFilled(false);
            T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    T3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtBuscarAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtBuscarAlertas, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel38.setBackground(new java.awt.Color(193, 57, 45));

            jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel34.setForeground(new java.awt.Color(255, 255, 255));
            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
            jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel34MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
            jPanel38.setLayout(jPanel38Layout);
            jPanel38Layout.setHorizontalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel34)
            );
            jPanel38Layout.setVerticalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnImprimir.setForeground(new java.awt.Color(240, 240, 240));
            btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Print-32 (2).png"))); // NOI18N
            btnImprimir.setText("Imprimir");
            btnImprimir.setContentAreaFilled(false);
            btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnImprimir.setIconTextGap(30);
            btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnImprimirActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)
                    .addComponent(btnActualizar)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnImprimir)
                    .addGap(26, 26, 26)
                    .addComponent(lblusu)
                    .addGap(130, 130, 130))
            );

            pnlDatosPaciente.setBackground(new java.awt.Color(51, 51, 51));

            jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("Contacto");

            lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblTelefono.setForeground(new java.awt.Color(255, 255, 255));
            lblTelefono.setText("Nº Telefonico");

            lblDistrito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblDistrito.setForeground(new java.awt.Color(255, 255, 255));
            lblDistrito.setText("Distrito");

            lblSector.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblSector.setForeground(new java.awt.Color(255, 255, 255));
            lblSector.setText("Sector");

            lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblDireccion.setForeground(new java.awt.Color(255, 255, 255));
            lblDireccion.setText("Direccion");

            lblCelular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblCelular.setForeground(new java.awt.Color(255, 255, 255));
            lblCelular.setText("Celular");

            javax.swing.GroupLayout pnlDatosPacienteLayout = new javax.swing.GroupLayout(pnlDatosPaciente);
            pnlDatosPaciente.setLayout(pnlDatosPacienteLayout);
            pnlDatosPacienteLayout.setHorizontalGroup(
                pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDatosPacienteLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(pnlDatosPacienteLayout.createSequentialGroup()
                            .addGroup(pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDistrito)
                                .addComponent(lblTelefono))
                            .addGap(49, 49, 49)
                            .addComponent(lblSector)
                            .addGap(86, 86, 86)
                            .addGroup(pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCelular)
                                .addComponent(lblDireccion))))
                    .addContainerGap(363, Short.MAX_VALUE))
            );
            pnlDatosPacienteLayout.setVerticalGroup(
                pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlDatosPacienteLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addGroup(pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTelefono)
                        .addComponent(lblCelular))
                    .addGap(18, 18, 18)
                    .addGroup(pnlDatosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDistrito)
                        .addComponent(lblSector)
                        .addComponent(lblDireccion))
                    .addContainerGap(40, Short.MAX_VALUE))
            );

            jScrollPane3.setBorder(null);

            tbAlertas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            tbAlertas.setForeground(new java.awt.Color(255, 255, 255));
            tbAlertas.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tbAlertas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbAlertas.setGridColor(new java.awt.Color(255, 255, 255));
            tbAlertas.setRowHeight(25);
            tbAlertas.setSelectionBackground(new java.awt.Color(232, 76, 61));
            tbAlertas.getTableHeader().setReorderingAllowed(false);
            tbAlertas.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbAlertasMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbAlertasMousePressed(evt);
                }
            });
            tbAlertas.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbAlertasKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbAlertasKeyReleased(evt);
                }
            });
            jScrollPane3.setViewportView(tbAlertas);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlDatosPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(pnlDatosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        ConsultorioExtCarnetPerinatalAtencionPrenatal consultorio1 = new ConsultorioExtCarnetPerinatalAtencionPrenatal();
        consultorio1.consultorioExAtencionPrenatalAlertas("", tbAlertas);
        txtBuscarAlertas.setText("");
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtBuscarAlertasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarAlertasCaretUpdate
        ConsultorioExtCarnetPerinatalAtencionPrenatal consultorio1 = new ConsultorioExtCarnetPerinatalAtencionPrenatal();
        consultorio1.consultorioExAtencionPrenatalAlertas(txtBuscarAlertas.getText(), tbAlertas);
    }//GEN-LAST:event_txtBuscarAlertasCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
        cabecera.conteoAlertas();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void tbAlertasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlertasMouseClicked
        if(evt.getClickCount()==1){
            enviarDatosAlertas();
        }
    }//GEN-LAST:event_tbAlertasMouseClicked

    private void tbAlertasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlertasMousePressed

    }//GEN-LAST:event_tbAlertasMousePressed

    private void tbAlertasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAlertasKeyPressed


    }//GEN-LAST:event_tbAlertasKeyPressed

    private void tbAlertasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAlertasKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP
           || evt.getExtendedKeyCode()==KeyEvent.VK_ENTER){
            enviarDatosAlertas();
        }
    }//GEN-LAST:event_tbAlertasKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        //VISUALIZAR EL REPORTE DE ALERTAS
        String ruta = "/reportes/consultoriosExternos/atencionPrenatalAlertas.jasper";
        cabecera.reporteAlertas(ruta);
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton T3;
    public static javax.swing.JButton btnActualizar;
    public static javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblSector;
    private javax.swing.JLabel lblTelefono;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlDatosPaciente;
    private javax.swing.JTable tbAlertas;
    public static javax.swing.JTextField txtBuscarAlertas;
    // End of variables declaration//GEN-END:variables
}
