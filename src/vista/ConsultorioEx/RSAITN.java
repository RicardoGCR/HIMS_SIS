/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtRsTamizajeNeonatal;


/**
 *
 * @author MYS1
 */
public class RSAITN extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null; 
byte tg;
    byte tge;
    JDateChooser fecha;
    JTextField fua;
    ConsultorioExtRsTamizajeNeonatal TN01 = new ConsultorioExtRsTamizajeNeonatal();
    /**
     * Creates new form RSAITN
     */
    public RSAITN() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarDatos(false);
        this.Habilitar();
        mensaje.setVisible(false);
    }
    public void QuitarLaBarraTitulo()
    { 
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

        jPanel299 = new javax.swing.JPanel();
        jPanel300 = new javax.swing.JPanel();
        jLabel184 = new javax.swing.JLabel();
        jPanel301 = new javax.swing.JPanel();
        jLabel185 = new javax.swing.JLabel();
        CCDM15 = new javax.swing.JPanel();
        jPanel157 = new javax.swing.JPanel();
        FDD7 = new com.toedter.calendar.JDateChooser();
        RDD1 = new javax.swing.JRadioButton();
        jPanel158 = new javax.swing.JPanel();
        FDD8 = new com.toedter.calendar.JDateChooser();
        RDD2 = new javax.swing.JRadioButton();
        jPanel159 = new javax.swing.JPanel();
        FDD9 = new com.toedter.calendar.JDateChooser();
        RDD3 = new javax.swing.JRadioButton();
        FUADD9 = new javax.swing.JTextField();
        FUADD7 = new javax.swing.JTextField();
        FUADD8 = new javax.swing.JTextField();
        jPanel160 = new javax.swing.JPanel();
        FDD10 = new com.toedter.calendar.JDateChooser();
        RDD4 = new javax.swing.JRadioButton();
        FUADD10 = new javax.swing.JTextField();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        mensaje1 = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

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
                .addContainerGap(1196, Short.MAX_VALUE))
        );
        jPanel300Layout.setVerticalGroup(
            jPanel300Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel301, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel300Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel184)
                .addContainerGap())
        );

        CCDM15.setBackground(new java.awt.Color(204, 204, 204));

        jPanel157.setBackground(new java.awt.Color(153, 153, 153));

        FDD7.setBackground(new java.awt.Color(204, 204, 204));
        FDD7.setDateFormatString("dd/MM/yyyy");
        FDD7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD1.setBackground(new java.awt.Color(153, 153, 153));
        RDD1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD1.setForeground(new java.awt.Color(255, 255, 255));
        RDD1.setText("TSH");
        RDD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel157Layout = new javax.swing.GroupLayout(jPanel157);
        jPanel157.setLayout(jPanel157Layout);
        jPanel157Layout.setHorizontalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(RDD1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel157Layout.setVerticalGroup(
            jPanel157Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel157Layout.createSequentialGroup()
                .addComponent(RDD1)
                .addGap(3, 3, 3)
                .addComponent(FDD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel158.setBackground(new java.awt.Color(153, 153, 153));

        FDD8.setBackground(new java.awt.Color(204, 204, 204));
        FDD8.setDateFormatString("dd/MM/yyyy");
        FDD8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD2.setBackground(new java.awt.Color(153, 153, 153));
        RDD2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD2.setForeground(new java.awt.Color(255, 255, 255));
        RDD2.setText("FC");
        RDD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel158Layout = new javax.swing.GroupLayout(jPanel158);
        jPanel158.setLayout(jPanel158Layout);
        jPanel158Layout.setHorizontalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel158Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(RDD2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel158Layout.setVerticalGroup(
            jPanel158Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel158Layout.createSequentialGroup()
                .addComponent(RDD2)
                .addGap(3, 3, 3)
                .addComponent(FDD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel159.setBackground(new java.awt.Color(153, 153, 153));

        FDD9.setBackground(new java.awt.Color(204, 204, 204));
        FDD9.setDateFormatString("dd/MM/yyyy");
        FDD9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD3.setBackground(new java.awt.Color(153, 153, 153));
        RDD3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD3.setForeground(new java.awt.Color(255, 255, 255));
        RDD3.setText("FO");
        RDD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel159Layout = new javax.swing.GroupLayout(jPanel159);
        jPanel159.setLayout(jPanel159Layout);
        jPanel159Layout.setHorizontalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel159Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RDD3)
                .addGap(39, 39, 39))
        );
        jPanel159Layout.setVerticalGroup(
            jPanel159Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel159Layout.createSequentialGroup()
                .addComponent(RDD3)
                .addGap(3, 3, 3)
                .addComponent(FDD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel160.setBackground(new java.awt.Color(153, 153, 153));

        FDD10.setBackground(new java.awt.Color(204, 204, 204));
        FDD10.setDateFormatString("dd/MM/yyyy");
        FDD10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        RDD4.setBackground(new java.awt.Color(153, 153, 153));
        RDD4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RDD4.setForeground(new java.awt.Color(255, 255, 255));
        RDD4.setText("HSR");
        RDD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RDD4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel160Layout = new javax.swing.GroupLayout(jPanel160);
        jPanel160.setLayout(jPanel160Layout);
        jPanel160Layout.setHorizontalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDD10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
            .addGroup(jPanel160Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(RDD4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel160Layout.setVerticalGroup(
            jPanel160Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel160Layout.createSequentialGroup()
                .addComponent(RDD4)
                .addGap(3, 3, 3)
                .addComponent(FDD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout CCDM15Layout = new javax.swing.GroupLayout(CCDM15);
        CCDM15.setLayout(CCDM15Layout);
        CCDM15Layout.setHorizontalGroup(
            CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM15Layout.createSequentialGroup()
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel158, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel159, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADD9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADD10)))
        );
        CCDM15Layout.setVerticalGroup(
            CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CCDM15Layout.createSequentialGroup()
                        .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel159, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel158, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel157, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(CCDM15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FUADD9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADD7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADD8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CCDM15Layout.createSequentialGroup()
                        .addComponent(jPanel160, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(FUADD10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        lblNina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNina.setForeground(new java.awt.Color(102, 102, 102));
        lblNina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niña-48.png"))); // NOI18N
        lblNina.setText("NIÑOS");
        lblNina.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNina.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNinaMouseClicked(evt);
            }
        });

        lblNino.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNino.setForeground(new java.awt.Color(102, 102, 102));
        lblNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Niño-48.png"))); // NOI18N
        lblNino.setText("NIÑOS");
        lblNino.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNino.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblNino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNinoMouseClicked(evt);
            }
        });

        mensaje.setBackground(new java.awt.Color(102, 102, 102));

        jPanel31.setBackground(new java.awt.Color(51, 51, 51));

        btnEditar.setForeground(new java.awt.Color(240, 240, 240));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
        btnEditar.setMnemonic('N');
        btnEditar.setContentAreaFilled(false);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setIconTextGap(30);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('N');
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(30);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setForeground(new java.awt.Color(240, 240, 240));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Deshacer-30.png"))); // NOI18N
        btnCancelar.setMnemonic('N');
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIconTextGap(30);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        mensaje1.setBackground(new java.awt.Color(33, 115, 70));

        men.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        men.setForeground(new java.awt.Color(255, 255, 255));
        men.setText("Desea Actualizar el Registro ?");

        b.setForeground(new java.awt.Color(240, 240, 240));
        b.setText("Si");
        b.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b.setContentAreaFilled(false);
        b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b.setIconTextGap(30);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });

        b1.setForeground(new java.awt.Color(240, 240, 240));
        b1.setText("No");
        b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b1.setContentAreaFilled(false);
        b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b1.setIconTextGap(30);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mensaje1Layout = new javax.swing.GroupLayout(mensaje1);
        mensaje1.setLayout(mensaje1Layout);
        mensaje1Layout.setHorizontalGroup(
            mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensaje1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(men)
                .addGap(46, 46, 46)
                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(841, Short.MAX_VALUE))
        );
        mensaje1Layout.setVerticalGroup(
            mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensaje1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(men)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
        mensaje.setLayout(mensajeLayout);
        mensajeLayout.setHorizontalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeLayout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        mensajeLayout.setVerticalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblId.setText("jLabel1");

        javax.swing.GroupLayout jPanel299Layout = new javax.swing.GroupLayout(jPanel299);
        jPanel299.setLayout(jPanel299Layout);
        jPanel299Layout.setHorizontalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel300, javax.swing.GroupLayout.DEFAULT_SIZE, 1392, Short.MAX_VALUE)
            .addGroup(jPanel299Layout.createSequentialGroup()
                .addGroup(jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel299Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel299Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel299Layout.setVerticalGroup(
            jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel299Layout.createSequentialGroup()
                .addComponent(jPanel300, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel299Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNina)
                    .addComponent(lblNino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CCDM15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
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
         RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel185MouseClicked

    private void lblNinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinaMouseClicked

    private void lblNinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinoMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        validaRegistro(Integer.parseInt(lblId.getText()));
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarRadio(true);
        habilitarCampos(false);
        Botones(false);
        fua.setText("");
        cie10.setText("");
        fecha.setDate(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1 || tge==9){
            mensaje1.setVisible(false);

        }

        if (tge==2){
            //            Modificar();

            btnEditar.setEnabled(false);
            ;

        }
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje1.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void RDD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD1ActionPerformed
        if(FDD1.getDate()==null){
            if(RDD1.isSelected()){
                habilitarCampos(false);
                habilitarRadio(false);
                Botones(true);
                DXDD1.setEnabled(true);
                FDD1.setEnabled(true);
                fecha=FDD1;
                fua = FUADD1;
                cie10=DXDD1;
            }
        } else {
            RDD1.setEnabled(false);
        }

    }//GEN-LAST:event_RDD1ActionPerformed

    private void RDD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RDD2ActionPerformed

    private void RDD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RDD3ActionPerformed

    private void RDD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RDD4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RDD4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM15;
    public static com.toedter.calendar.JDateChooser FDD10;
    public static com.toedter.calendar.JDateChooser FDD7;
    public static com.toedter.calendar.JDateChooser FDD8;
    public static com.toedter.calendar.JDateChooser FDD9;
    public static javax.swing.JTextField FUADD10;
    public static javax.swing.JTextField FUADD7;
    public static javax.swing.JTextField FUADD8;
    public static javax.swing.JTextField FUADD9;
    private javax.swing.JRadioButton RDD1;
    private javax.swing.JRadioButton RDD2;
    private javax.swing.JRadioButton RDD3;
    private javax.swing.JRadioButton RDD4;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JPanel jPanel157;
    private javax.swing.JPanel jPanel158;
    private javax.swing.JPanel jPanel159;
    private javax.swing.JPanel jPanel160;
    private javax.swing.JPanel jPanel299;
    private javax.swing.JPanel jPanel300;
    private javax.swing.JPanel jPanel301;
    private javax.swing.JPanel jPanel31;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel mensaje1;
    // End of variables declaration//GEN-END:variables
}
