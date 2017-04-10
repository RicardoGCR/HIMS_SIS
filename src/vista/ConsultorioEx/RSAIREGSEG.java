/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtRsCabecera;
import modelos.ConsultorioEx.ConsultorioExtRsMonitoreo;
import modelos.ConsultorioEx.ConsultorioExtRsSeguimientoAnemia;
import modelos.ConsultorioEx.ConsultorioExtRsSeguimientoDesarrollo;
import modelos.ConsultorioEx.ConsultorioExtRsSeguimientoIra;
import modelos.ConsultorioEx.ConsultorioExtRsSeguimientoParasitosis;
import static vista.ConsultorioEx.REGSEGMONITOREO.lblId;

/**
 *
 * @author MYS1
 */
public class RSAIREGSEG extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
    public RSAIREGSEG() {
        initComponents();
        QuitarLaBarraTitulo();
        lblId.setVisible(false);
        lblMensaje.setVisible(false);
        
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(1000);
        txtObservacion.setDocument(limitObservacion);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ContenedorDD = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JEditorPane();
        btnGuardar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel12.setBackground(new java.awt.Color(25, 188, 157));
        jPanel12.setPreferredSize(new java.awt.Dimension(83, 45));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("<html>MONITOREO / SUPERVISION DE<br>MICRONUTRIENTES</br></html>");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jLabel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(45, 204, 112));
        jPanel13.setPreferredSize(new java.awt.Dimension(298, 45));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SEGUIMIENTO DE ANEMIA");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(50, 151, 219));
        jPanel15.setPreferredSize(new java.awt.Dimension(205, 45));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SEGUIMIENTO DE PARASITOSIS");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(154, 89, 181));
        jPanel16.setPreferredSize(new java.awt.Dimension(222, 45));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SEGUIMIENTO DE DESARROLLO");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(232, 76, 61));
        jPanel17.setPreferredSize(new java.awt.Dimension(238, 45));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SEGUIMIENTO DE IRA - EDA");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblId.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Observaciones:");

        txtObservacion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(txtObservacion);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMensaje.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(558, 558, 558)
                        .addComponent(lblId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMensaje)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMensaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lblId)
                .addContainerGap())
        );

        javax.swing.GroupLayout ContenedorDDLayout = new javax.swing.GroupLayout(ContenedorDD);
        ContenedorDD.setLayout(ContenedorDDLayout);
        ContenedorDDLayout.setHorizontalGroup(
            ContenedorDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContenedorDDLayout.setVerticalGroup(
            ContenedorDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContenedorDD.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel26.setBackground(new java.awt.Color(126, 140, 141));
        jPanel26.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Reg. Seguimiento De La Atención Integral");

        jPanel27.setBackground(new java.awt.Color(93, 94, 94));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(93, 94, 94));
        jPanel2.setPreferredSize(new java.awt.Dimension(1, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblNina.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblNina.setForeground(new java.awt.Color(63, 64, 64));
        lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-50.png"))); // NOI18N
        lblNina.setText("NIÑOS");
        lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNino.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblNino.setForeground(new java.awt.Color(63, 64, 64));
        lblNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-50.png"))); // NOI18N
        lblNino.setText("NIÑOS");
        lblNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNina)
                    .addComponent(lblNino))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ContenedorDD)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1457, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ContenedorDD))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
      

    }//GEN-LAST:event_jPanel12MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        REGSEGSEGA ANEMIA =new REGSEGSEGA();
        ConsultorioExtRsSeguimientoAnemia seguimiento = new ConsultorioExtRsSeguimientoAnemia();
        seguimiento.listarDiagnostico(lblId.getText(), REGSEGSEGA.tbSeguimientoA);
        REGSEGSEGA.txtEdad.setText(RegistroSeguimiento.lblEdad.getText());
        REGSEGSEGA.lblId.setText(RSAIREGSEG.lblId.getText());
        ContenedorDD.add(ANEMIA);
        try {
            ANEMIA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked

    }//GEN-LAST:event_jPanel13MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        REGSEGSEGP PAR =new REGSEGSEGP();
        ConsultorioExtRsSeguimientoParasitosis seguimiento = new ConsultorioExtRsSeguimientoParasitosis();
        seguimiento.listarDiagnostico(lblId.getText(), REGSEGSEGP.tbSeguimientoP);
        REGSEGSEGP.txtEdad.setText(RegistroSeguimiento.lblEdad.getText());
        REGSEGSEGP.lblId.setText(RSAIREGSEG.lblId.getText());
        ContenedorDD.add(PAR);
        try {
            PAR.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        REGSEGSEGD sd =new REGSEGSEGD();
        ConsultorioExtRsSeguimientoDesarrollo seguimiento = new ConsultorioExtRsSeguimientoDesarrollo();
        seguimiento.listarDiagnostico(lblId.getText(), REGSEGSEGD.tbSeguimientoD);
        REGSEGSEGD.txtEdad.setText(RegistroSeguimiento.lblEdad.getText());
        REGSEGSEGD.lblId.setText(RSAIREGSEG.lblId.getText());
        ContenedorDD.add(sd);
        try {
            sd.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       REGSEGSEGIRA IRA =new REGSEGSEGIRA();
       ConsultorioExtRsSeguimientoIra seguimiento = new ConsultorioExtRsSeguimientoIra();
        seguimiento.listarDiagnostico(lblId.getText(), REGSEGSEGIRA.tbSeguimientoI);
        REGSEGSEGIRA.txtEdad.setText(RegistroSeguimiento.lblEdad.getText());
        REGSEGSEGIRA.lblId.setText(RSAIREGSEG.lblId.getText());
        ContenedorDD.add(IRA);
        try {
            IRA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel4KeyPressed
     
    }//GEN-LAST:event_jLabel4KeyPressed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        REGSEGMONITOREO MON =new REGSEGMONITOREO();
        ConsultorioExtRsMonitoreo monitoreo = new ConsultorioExtRsMonitoreo();
        monitoreo.listarDiagnostico(lblId.getText(), REGSEGMONITOREO.tbMonitoreo);
        ContenedorDD.add(MON);
        REGSEGMONITOREO.txtEdad.setText(RegistroSeguimiento.lblEdad.getText());
        REGSEGMONITOREO.lblId.setText(RSAIREGSEG.lblId.getText());
        try {
            MON.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if(!txtObservacion.getText().equals("")){
            ConsultorioExtRsCabecera consultorio1  = new ConsultorioExtRsCabecera();
                consultorio1.setRsId(Integer.parseInt(lblId.getText()));
                consultorio1.setRsObservacion(txtObservacion.getText());
                if(consultorio1.mantenimientoConsultorioRsCabecera("U")==true){
                    lblMensaje.setVisible(true);
                    lblMensaje.setText("Datos guardados de forma correcta");
                    lblMensaje.setBackground(new Color(33,115,70));
                } else {
                    lblMensaje.setVisible(true);
                    lblMensaje.setText("Ocurrió un error, verifique");
                    lblMensaje.setBackground(new Color(255,91,70));
                }
            } else {
                lblMensaje.setVisible(true);
                lblMensaje.setText("Verifique, que los datos estén correctos");
                lblMensaje.setBackground(new Color(255,91,70));
            }
        } catch (Exception e) {
            System.out.println("Error: btnGuardar" + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane ContenedorDD;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    public static javax.swing.JEditorPane txtObservacion;
    // End of variables declaration//GEN-END:variables
}
