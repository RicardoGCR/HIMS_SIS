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
public class RSAIDN extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
    /**
     * Creates new form RSAIDN
     */
    public RSAIDN() {
        initComponents();
        QuitarLaBarraTitulo();
        habilitarCampos(false);
    }
    public void QuitarLaBarraTitulo() // ocultar borde de fomrulario interno
    { 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
    public void habilitarCampos(boolean opcion){
        FDN1.setEnabled(opcion);
        FDN2.setEnabled(opcion);
        FDN3.setEnabled(opcion);
        FDN4.setEnabled(opcion);
        FDN5.setEnabled(opcion);
        FDN6.setEnabled(opcion);
        FDN7.setEnabled(opcion);
        FDN8.setEnabled(opcion);
        FDN9.setEnabled(opcion);
        FDN10.setEnabled(opcion);
        DXDN1.setEnabled(opcion);
        DXDN2.setEnabled(opcion);
        DXDN3.setEnabled(opcion);
        DXDN4.setEnabled(opcion);
        DXDN5.setEnabled(opcion);
        DXDN6.setEnabled(opcion);
        DXDN7.setEnabled(opcion);
        DXDN8.setEnabled(opcion);
        DXDN9.setEnabled(opcion);
        DXDN10.setEnabled(opcion);
        FUADN1.setEnabled(opcion);
        FUADN2.setEnabled(opcion);
        FUADN3.setEnabled(opcion);
        FUADN4.setEnabled(opcion);
        FUADN5.setEnabled(opcion);
        FUADN6.setEnabled(opcion);
        FUADN7.setEnabled(opcion);
        FUADN8.setEnabled(opcion);
        FUADN9.setEnabled(opcion);
        FUADN10.setEnabled(opcion);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        DN = new javax.swing.JPanel();
        jPanel202 = new javax.swing.JPanel();
        jLabel126 = new javax.swing.JLabel();
        jPanel203 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        CCDM13 = new javax.swing.JPanel();
        jPanel133 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jPanel134 = new javax.swing.JPanel();
        FDN1 = new com.toedter.calendar.JDateChooser();
        rbt1 = new javax.swing.JRadioButton();
        jPanel135 = new javax.swing.JPanel();
        FDN2 = new com.toedter.calendar.JDateChooser();
        rbt2 = new javax.swing.JRadioButton();
        jPanel136 = new javax.swing.JPanel();
        FDN3 = new com.toedter.calendar.JDateChooser();
        rbt3 = new javax.swing.JRadioButton();
        FUADN3 = new javax.swing.JTextField();
        FUADN1 = new javax.swing.JTextField();
        FUADN2 = new javax.swing.JTextField();
        DXDN1 = new javax.swing.JLabel();
        DXDN2 = new javax.swing.JLabel();
        DXDN3 = new javax.swing.JLabel();
        jPanel137 = new javax.swing.JPanel();
        FDN4 = new com.toedter.calendar.JDateChooser();
        rbt4 = new javax.swing.JRadioButton();
        DXDN4 = new javax.swing.JLabel();
        FUADN4 = new javax.swing.JTextField();
        jPanel138 = new javax.swing.JPanel();
        FDN5 = new com.toedter.calendar.JDateChooser();
        rbt5 = new javax.swing.JRadioButton();
        DXDN5 = new javax.swing.JLabel();
        FUADN5 = new javax.swing.JTextField();
        jPanel139 = new javax.swing.JPanel();
        FDN6 = new com.toedter.calendar.JDateChooser();
        rbt6 = new javax.swing.JRadioButton();
        DXDN6 = new javax.swing.JLabel();
        FUADN6 = new javax.swing.JTextField();
        LEYENDA2 = new javax.swing.JPanel();
        jPanel141 = new javax.swing.JPanel();
        jPanel142 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jPanel143 = new javax.swing.JPanel();
        jLabel138 = new javax.swing.JLabel();
        jPanel144 = new javax.swing.JPanel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        CCDR3A2 = new javax.swing.JPanel();
        jPanel129 = new javax.swing.JPanel();
        jLabel125 = new javax.swing.JLabel();
        jPanel130 = new javax.swing.JPanel();
        FDN7 = new com.toedter.calendar.JDateChooser();
        rbt7 = new javax.swing.JRadioButton();
        jPanel131 = new javax.swing.JPanel();
        FDN8 = new com.toedter.calendar.JDateChooser();
        rbt8 = new javax.swing.JRadioButton();
        jPanel132 = new javax.swing.JPanel();
        FDN9 = new com.toedter.calendar.JDateChooser();
        rbt9 = new javax.swing.JRadioButton();
        FUADN9 = new javax.swing.JTextField();
        FUADN7 = new javax.swing.JTextField();
        FUADN8 = new javax.swing.JTextField();
        jPanel140 = new javax.swing.JPanel();
        FDN10 = new com.toedter.calendar.JDateChooser();
        rbt10 = new javax.swing.JRadioButton();
        FUADN10 = new javax.swing.JTextField();
        DXDN7 = new javax.swing.JLabel();
        DXDN8 = new javax.swing.JLabel();
        DXDN9 = new javax.swing.JLabel();
        DXDN10 = new javax.swing.JLabel();
        Opciones = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        btneditar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        lblNina = new javax.swing.JLabel();
        lblNino = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        DN.setBackground(new java.awt.Color(255, 255, 255));

        jPanel202.setBackground(new java.awt.Color(50, 151, 219));
        jPanel202.setPreferredSize(new java.awt.Dimension(83, 45));

        jLabel126.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(255, 255, 255));
        jLabel126.setText("DIAGNOSTICO NUTRICIONAL");

        jPanel203.setBackground(new java.awt.Color(41, 127, 184));

        jLabel127.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(255, 255, 255));
        jLabel127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel127.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel127MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel203Layout = new javax.swing.GroupLayout(jPanel203);
        jPanel203.setLayout(jPanel203Layout);
        jPanel203Layout.setHorizontalGroup(
            jPanel203Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel203Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel127)
                .addContainerGap())
        );
        jPanel203Layout.setVerticalGroup(
            jPanel203Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel203Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel127)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel202Layout = new javax.swing.GroupLayout(jPanel202);
        jPanel202.setLayout(jPanel202Layout);
        jPanel202Layout.setHorizontalGroup(
            jPanel202Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel202Layout.createSequentialGroup()
                .addComponent(jPanel203, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel126)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel202Layout.setVerticalGroup(
            jPanel202Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel202Layout.createSequentialGroup()
                .addComponent(jPanel203, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel202Layout.createSequentialGroup()
                .addComponent(jLabel126, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        CCDM13.setBackground(new java.awt.Color(204, 204, 204));

        jPanel133.setBackground(new java.awt.Color(50, 151, 219));

        jLabel129.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(255, 255, 255));
        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel129.setText("DIAGNOSTICO NUTRICIONAL");

        javax.swing.GroupLayout jPanel133Layout = new javax.swing.GroupLayout(jPanel133);
        jPanel133.setLayout(jPanel133Layout);
        jPanel133Layout.setHorizontalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel133Layout.createSequentialGroup()
                .addComponent(jLabel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel133Layout.setVerticalGroup(
            jPanel133Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel129)
        );

        jPanel134.setBackground(new java.awt.Color(153, 153, 153));

        FDN1.setBackground(new java.awt.Color(204, 204, 204));
        FDN1.setDateFormatString("dd/MM/yyyy");
        FDN1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt1.setForeground(new java.awt.Color(255, 255, 255));
        rbt1.setText("1º");
        rbt1.setContentAreaFilled(false);
        rbt1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel134Layout = new javax.swing.GroupLayout(jPanel134);
        jPanel134.setLayout(jPanel134Layout);
        jPanel134Layout.setHorizontalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel134Layout.setVerticalGroup(
            jPanel134Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel134Layout.createSequentialGroup()
                .addComponent(rbt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel135.setBackground(new java.awt.Color(153, 153, 153));

        FDN2.setBackground(new java.awt.Color(204, 204, 204));
        FDN2.setDateFormatString("dd/MM/yyyy");
        FDN2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt2.setForeground(new java.awt.Color(255, 255, 255));
        rbt2.setText("2º");
        rbt2.setContentAreaFilled(false);
        rbt2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel135Layout = new javax.swing.GroupLayout(jPanel135);
        jPanel135.setLayout(jPanel135Layout);
        jPanel135Layout.setHorizontalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel135Layout.setVerticalGroup(
            jPanel135Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel135Layout.createSequentialGroup()
                .addComponent(rbt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel136.setBackground(new java.awt.Color(153, 153, 153));

        FDN3.setBackground(new java.awt.Color(204, 204, 204));
        FDN3.setDateFormatString("dd/MM/yyyy");
        FDN3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt3.setForeground(new java.awt.Color(255, 255, 255));
        rbt3.setText("3º");
        rbt3.setContentAreaFilled(false);
        rbt3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel136Layout = new javax.swing.GroupLayout(jPanel136);
        jPanel136.setLayout(jPanel136Layout);
        jPanel136Layout.setHorizontalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel136Layout.setVerticalGroup(
            jPanel136Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel136Layout.createSequentialGroup()
                .addComponent(rbt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DXDN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN1.setText("                                ");
        DXDN1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DXDN2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN2.setText("                                ");
        DXDN2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DXDN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN3.setText("                                ");
        DXDN3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel137.setBackground(new java.awt.Color(153, 153, 153));

        FDN4.setBackground(new java.awt.Color(204, 204, 204));
        FDN4.setDateFormatString("dd/MM/yyyy");
        FDN4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt4.setForeground(new java.awt.Color(255, 255, 255));
        rbt4.setText("4º");
        rbt4.setContentAreaFilled(false);
        rbt4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel137Layout = new javax.swing.GroupLayout(jPanel137);
        jPanel137.setLayout(jPanel137Layout);
        jPanel137Layout.setHorizontalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel137Layout.setVerticalGroup(
            jPanel137Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel137Layout.createSequentialGroup()
                .addComponent(rbt4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DXDN4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN4.setText("                                ");
        DXDN4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel138.setBackground(new java.awt.Color(153, 153, 153));

        FDN5.setBackground(new java.awt.Color(204, 204, 204));
        FDN5.setDateFormatString("dd/MM/yyyy");
        FDN5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt5.setForeground(new java.awt.Color(255, 255, 255));
        rbt5.setText("5º");
        rbt5.setContentAreaFilled(false);
        rbt5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel138Layout = new javax.swing.GroupLayout(jPanel138);
        jPanel138.setLayout(jPanel138Layout);
        jPanel138Layout.setHorizontalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel138Layout.setVerticalGroup(
            jPanel138Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel138Layout.createSequentialGroup()
                .addComponent(rbt5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DXDN5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN5.setText("                                ");
        DXDN5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel139.setBackground(new java.awt.Color(153, 153, 153));

        FDN6.setBackground(new java.awt.Color(204, 204, 204));
        FDN6.setDateFormatString("dd/MM/yyyy");
        FDN6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt6.setForeground(new java.awt.Color(255, 255, 255));
        rbt6.setText("6º");
        rbt6.setContentAreaFilled(false);
        rbt6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel139Layout = new javax.swing.GroupLayout(jPanel139);
        jPanel139.setLayout(jPanel139Layout);
        jPanel139Layout.setHorizontalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel139Layout.setVerticalGroup(
            jPanel139Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel139Layout.createSequentialGroup()
                .addComponent(rbt6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DXDN6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN6.setText("                                ");
        DXDN6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout CCDM13Layout = new javax.swing.GroupLayout(CCDM13);
        CCDM13.setLayout(CCDM13Layout);
        CCDM13Layout.setHorizontalGroup(
            CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel133, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CCDM13Layout.createSequentialGroup()
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel134, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADN1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXDN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADN2)
                    .addComponent(DXDN2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FUADN3)
                    .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DXDN3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FUADN4)
                    .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DXDN4))
                .addGap(1, 1, 1)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FUADN5)
                    .addComponent(jPanel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DXDN5))
                .addGap(1, 1, 1)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FUADN6)
                    .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DXDN6)))
        );
        CCDM13Layout.setVerticalGroup(
            CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDM13Layout.createSequentialGroup()
                .addComponent(jPanel133, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CCDM13Layout.createSequentialGroup()
                        .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel134, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DXDN1)
                            .addComponent(DXDN2)
                            .addComponent(DXDN3))
                        .addGap(0, 0, 0)
                        .addGroup(CCDM13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FUADN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FUADN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CCDM13Layout.createSequentialGroup()
                        .addComponent(jPanel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DXDN5)
                        .addGap(0, 0, 0)
                        .addComponent(FUADN5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CCDM13Layout.createSequentialGroup()
                        .addComponent(jPanel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DXDN4)
                        .addGap(0, 0, 0)
                        .addComponent(FUADN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CCDM13Layout.createSequentialGroup()
                        .addComponent(jPanel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DXDN6)
                        .addGap(0, 0, 0)
                        .addComponent(FUADN6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        LEYENDA2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel141.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel141Layout = new javax.swing.GroupLayout(jPanel141);
        jPanel141.setLayout(jPanel141Layout);
        jPanel141Layout.setHorizontalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel141Layout.setVerticalGroup(
            jPanel141Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel142.setBackground(new java.awt.Color(153, 153, 153));

        jLabel137.setBackground(new java.awt.Color(153, 153, 153));
        jLabel137.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel137.setText("Nº CONRTROL");

        javax.swing.GroupLayout jPanel142Layout = new javax.swing.GroupLayout(jPanel142);
        jPanel142.setLayout(jPanel142Layout);
        jPanel142Layout.setHorizontalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel142Layout.createSequentialGroup()
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel142Layout.setVerticalGroup(
            jPanel142Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel142Layout.createSequentialGroup()
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel143.setBackground(new java.awt.Color(204, 204, 204));

        jLabel138.setForeground(new java.awt.Color(255, 255, 255));
        jLabel138.setText("FECHA");

        javax.swing.GroupLayout jPanel143Layout = new javax.swing.GroupLayout(jPanel143);
        jPanel143.setLayout(jPanel143Layout);
        jPanel143Layout.setHorizontalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel143Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel143Layout.setVerticalGroup(
            jPanel143Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel143Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel138))
        );

        jPanel144.setBackground(new java.awt.Color(153, 153, 153));

        jLabel139.setForeground(new java.awt.Color(255, 255, 255));
        jLabel139.setText("DX");

        javax.swing.GroupLayout jPanel144Layout = new javax.swing.GroupLayout(jPanel144);
        jPanel144.setLayout(jPanel144Layout);
        jPanel144Layout.setHorizontalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel144Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel144Layout.setVerticalGroup(
            jPanel144Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel139, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jLabel140.setForeground(new java.awt.Color(255, 255, 255));
        jLabel140.setText("FUA");

        javax.swing.GroupLayout LEYENDA2Layout = new javax.swing.GroupLayout(LEYENDA2);
        LEYENDA2.setLayout(LEYENDA2Layout);
        LEYENDA2Layout.setHorizontalGroup(
            LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LEYENDA2Layout.createSequentialGroup()
                .addGroup(LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel144, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(LEYENDA2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LEYENDA2Layout.setVerticalGroup(
            LEYENDA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LEYENDA2Layout.createSequentialGroup()
                .addComponent(jPanel141, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel142, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel143, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel140, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        CCDR3A2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel129.setBackground(new java.awt.Color(50, 151, 219));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(255, 255, 255));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel125.setText("DIAGNOSTICO NUTRICIONAL");

        javax.swing.GroupLayout jPanel129Layout = new javax.swing.GroupLayout(jPanel129);
        jPanel129.setLayout(jPanel129Layout);
        jPanel129Layout.setHorizontalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel129Layout.createSequentialGroup()
                .addComponent(jLabel125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel129Layout.setVerticalGroup(
            jPanel129Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel125)
        );

        jPanel130.setBackground(new java.awt.Color(153, 153, 153));

        FDN7.setBackground(new java.awt.Color(204, 204, 204));
        FDN7.setDateFormatString("dd/MM/yyyy");
        FDN7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt7.setForeground(new java.awt.Color(255, 255, 255));
        rbt7.setText("7º");
        rbt7.setContentAreaFilled(false);
        rbt7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel130Layout = new javax.swing.GroupLayout(jPanel130);
        jPanel130.setLayout(jPanel130Layout);
        jPanel130Layout.setHorizontalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel130Layout.setVerticalGroup(
            jPanel130Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel130Layout.createSequentialGroup()
                .addComponent(rbt7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel131.setBackground(new java.awt.Color(153, 153, 153));

        FDN8.setBackground(new java.awt.Color(204, 204, 204));
        FDN8.setDateFormatString("dd/MM/yyyy");
        FDN8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt8.setForeground(new java.awt.Color(255, 255, 255));
        rbt8.setText("8º");
        rbt8.setContentAreaFilled(false);
        rbt8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel131Layout = new javax.swing.GroupLayout(jPanel131);
        jPanel131.setLayout(jPanel131Layout);
        jPanel131Layout.setHorizontalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel131Layout.setVerticalGroup(
            jPanel131Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel131Layout.createSequentialGroup()
                .addComponent(rbt8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel132.setBackground(new java.awt.Color(153, 153, 153));

        FDN9.setBackground(new java.awt.Color(204, 204, 204));
        FDN9.setDateFormatString("dd/MM/yyyy");
        FDN9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt9.setForeground(new java.awt.Color(255, 255, 255));
        rbt9.setText("9º");
        rbt9.setContentAreaFilled(false);
        rbt9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel132Layout = new javax.swing.GroupLayout(jPanel132);
        jPanel132.setLayout(jPanel132Layout);
        jPanel132Layout.setHorizontalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel132Layout.setVerticalGroup(
            jPanel132Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel132Layout.createSequentialGroup()
                .addComponent(rbt9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel140.setBackground(new java.awt.Color(153, 153, 153));

        FDN10.setBackground(new java.awt.Color(204, 204, 204));
        FDN10.setDateFormatString("dd/MM/yyyy");
        FDN10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        rbt10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbt10.setForeground(new java.awt.Color(255, 255, 255));
        rbt10.setText("10º");
        rbt10.setContentAreaFilled(false);
        rbt10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbt10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel140Layout = new javax.swing.GroupLayout(jPanel140);
        jPanel140.setLayout(jPanel140Layout);
        jPanel140Layout.setHorizontalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FDN10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rbt10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel140Layout.setVerticalGroup(
            jPanel140Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel140Layout.createSequentialGroup()
                .addComponent(rbt10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FDN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DXDN7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN7.setText("                                ");
        DXDN7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DXDN8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN8.setText("                                ");
        DXDN8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DXDN9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN9.setText("                                ");
        DXDN9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DXDN10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-20.png"))); // NOI18N
        DXDN10.setText("                                ");
        DXDN10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DXDN10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout CCDR3A2Layout = new javax.swing.GroupLayout(CCDR3A2);
        CCDR3A2.setLayout(CCDR3A2Layout);
        CCDR3A2Layout.setHorizontalGroup(
            CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel129, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CCDR3A2Layout.createSequentialGroup()
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel130, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADN7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DXDN7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADN8)
                    .addComponent(DXDN8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FUADN9)
                    .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DXDN9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FUADN10)
                    .addComponent(DXDN10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        CCDR3A2Layout.setVerticalGroup(
            CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CCDR3A2Layout.createSequentialGroup()
                .addComponent(jPanel129, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel132, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel130, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DXDN7)
                    .addComponent(DXDN8)
                    .addComponent(DXDN9)
                    .addComponent(DXDN10))
                .addGap(0, 0, 0)
                .addGroup(CCDR3A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FUADN9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADN7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADN8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FUADN10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        Opciones.setBackground(new java.awt.Color(102, 102, 102));

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));

        btneditar.setForeground(new java.awt.Color(240, 240, 240));
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
        btneditar.setMnemonic('N');
        btneditar.setContentAreaFilled(false);
        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar.setIconTextGap(30);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnguardar.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
        btnguardar.setMnemonic('N');
        btnguardar.setText("Guardar");
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setIconTextGap(30);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(15, 15, 15)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout OpcionesLayout = new javax.swing.GroupLayout(Opciones);
        Opciones.setLayout(OpcionesLayout);
        OpcionesLayout.setHorizontalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpcionesLayout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        OpcionesLayout.setVerticalGroup(
            OpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        lblId.setText("jLabel1");

        javax.swing.GroupLayout DNLayout = new javax.swing.GroupLayout(DN);
        DN.setLayout(DNLayout);
        DNLayout.setHorizontalGroup(
            DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel202, javax.swing.GroupLayout.DEFAULT_SIZE, 1387, Short.MAX_VALUE)
            .addComponent(Opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DNLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DNLayout.createSequentialGroup()
                        .addComponent(LEYENDA2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(CCDM13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DNLayout.createSequentialGroup()
                        .addComponent(CCDR3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblId))
                    .addGroup(DNLayout.createSequentialGroup()
                        .addComponent(lblNina, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNino, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(532, Short.MAX_VALUE))
        );
        DNLayout.setVerticalGroup(
            DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DNLayout.createSequentialGroup()
                .addComponent(jPanel202, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNina)
                    .addComponent(lblNino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CCDM13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LEYENDA2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(DNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CCDR3A2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(Opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1387, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(DN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel127MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel127MouseClicked
        RegistroSeguimiento.jTabbedPane1.setSelectedIndex(0);
        this.dispose();
    }//GEN-LAST:event_jLabel127MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed

    }//GEN-LAST:event_btnguardarActionPerformed

    private void lblNinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinaMouseClicked

    private void lblNinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNinoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNinoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CCDM13;
    private javax.swing.JPanel CCDR3A2;
    private javax.swing.JPanel DN;
    private javax.swing.JLabel DXDN1;
    private javax.swing.JLabel DXDN10;
    private javax.swing.JLabel DXDN2;
    private javax.swing.JLabel DXDN3;
    private javax.swing.JLabel DXDN4;
    private javax.swing.JLabel DXDN5;
    private javax.swing.JLabel DXDN6;
    private javax.swing.JLabel DXDN7;
    private javax.swing.JLabel DXDN8;
    private javax.swing.JLabel DXDN9;
    private com.toedter.calendar.JDateChooser FDN1;
    private com.toedter.calendar.JDateChooser FDN10;
    private com.toedter.calendar.JDateChooser FDN2;
    private com.toedter.calendar.JDateChooser FDN3;
    private com.toedter.calendar.JDateChooser FDN4;
    private com.toedter.calendar.JDateChooser FDN5;
    private com.toedter.calendar.JDateChooser FDN6;
    private com.toedter.calendar.JDateChooser FDN7;
    private com.toedter.calendar.JDateChooser FDN8;
    private com.toedter.calendar.JDateChooser FDN9;
    private javax.swing.JTextField FUADN1;
    private javax.swing.JTextField FUADN10;
    private javax.swing.JTextField FUADN2;
    private javax.swing.JTextField FUADN3;
    private javax.swing.JTextField FUADN4;
    private javax.swing.JTextField FUADN5;
    private javax.swing.JTextField FUADN6;
    private javax.swing.JTextField FUADN7;
    private javax.swing.JTextField FUADN8;
    private javax.swing.JTextField FUADN9;
    private javax.swing.JPanel LEYENDA2;
    private javax.swing.JPanel Opciones;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JPanel jPanel129;
    private javax.swing.JPanel jPanel130;
    private javax.swing.JPanel jPanel131;
    private javax.swing.JPanel jPanel132;
    private javax.swing.JPanel jPanel133;
    private javax.swing.JPanel jPanel134;
    private javax.swing.JPanel jPanel135;
    private javax.swing.JPanel jPanel136;
    private javax.swing.JPanel jPanel137;
    private javax.swing.JPanel jPanel138;
    private javax.swing.JPanel jPanel139;
    private javax.swing.JPanel jPanel140;
    private javax.swing.JPanel jPanel141;
    private javax.swing.JPanel jPanel142;
    private javax.swing.JPanel jPanel143;
    private javax.swing.JPanel jPanel144;
    private javax.swing.JPanel jPanel202;
    private javax.swing.JPanel jPanel203;
    private javax.swing.JPanel jPanel28;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNina;
    public static javax.swing.JLabel lblNino;
    private javax.swing.JRadioButton rbt1;
    private javax.swing.JRadioButton rbt10;
    private javax.swing.JRadioButton rbt2;
    private javax.swing.JRadioButton rbt3;
    private javax.swing.JRadioButton rbt4;
    private javax.swing.JRadioButton rbt5;
    private javax.swing.JRadioButton rbt6;
    private javax.swing.JRadioButton rbt7;
    private javax.swing.JRadioButton rbt8;
    private javax.swing.JRadioButton rbt9;
    // End of variables declaration//GEN-END:variables
}
