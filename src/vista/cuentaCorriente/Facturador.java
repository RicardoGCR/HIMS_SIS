/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaCorriente;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.io.File;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;

/**
 *
 * @author PC02
 */
public class Facturador extends javax.swing.JFrame {

    String barra = File.separator;
    String ubicacion = System.getProperty("user.dir") + barra+ "Registros" + barra;
    public Facturador() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        cbxTipoOperacion.setBackground(Color.WHITE);
        cbxCodUnidad.setBackground(Color.WHITE);
        cbxDocumento.setBackground(Color.WHITE);
        cbxTipoDocumento.setBackground(Color.WHITE);
        cbxGravado.setBackground(Color.WHITE);
        cbxSerie.setBackground(Color.WHITE);
        cbxTipoMoneda.setBackground(Color.WHITE);
        cbxIGV.setBackground(Color.WHITE);
        cbxISC.setBackground(Color.WHITE);
        tbFacturacion.getTableHeader().setVisible(false);
        tbFacturacion.setTableHeader(null);
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
        txtTipoDocumento.setDocument(limitObservacion);
    }

    public void crear(){
        String archivo = txtTipoDocumento.getText() + ".registros";
        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(ubicacion + archivo);
    }   
//    if(txt)
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        Serie = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel21 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxTipoOperacion = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        lblFechaEmision = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblFechaEmision1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxTipoMoneda = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        cbxDocumento = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        cbxSerie = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblNroCorrelativo = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txtTipoDocumento = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        panelCPT1 = new javax.swing.JPanel();
        txtCPT1 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        panelCPT2 = new javax.swing.JPanel();
        txtCPT2 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        panelCPT3 = new javax.swing.JPanel();
        txtActoMedico = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        cbxGravado = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        cbxCodUnidad = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtCPT3 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        panelCPT5 = new javax.swing.JPanel();
        txtCodProducto = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        panelCPT6 = new javax.swing.JPanel();
        txtCodProducto1 = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        panelCPT7 = new javax.swing.JPanel();
        txtCPT4 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        panelCPT8 = new javax.swing.JPanel();
        txtCPT5 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        panelCPT9 = new javax.swing.JPanel();
        txtCPT6 = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        panelCPT10 = new javax.swing.JPanel();
        txtCPT7 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        panelCPT11 = new javax.swing.JPanel();
        txtCPT8 = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbxIGV = new javax.swing.JComboBox();
        jPanel31 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        panelCPT12 = new javax.swing.JPanel();
        txtCPT9 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        cbxISC = new javax.swing.JComboBox();
        jPanel33 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        panelCPT13 = new javax.swing.JPanel();
        txtCPT10 = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        panelCPT14 = new javax.swing.JPanel();
        txtCPT11 = new javax.swing.JTextField();
        tablaS = new javax.swing.JScrollPane();
        tbFacturacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel35 = new javax.swing.JPanel();
            jPanel36 = new javax.swing.JPanel();
            jLabel29 = new javax.swing.JLabel();
            panelCPT15 = new javax.swing.JPanel();
            txtCPT12 = new javax.swing.JTextField();
            jPanel37 = new javax.swing.JPanel();
            jLabel30 = new javax.swing.JLabel();
            panelCPT16 = new javax.swing.JPanel();
            txtCPT13 = new javax.swing.JTextField();
            jPanel38 = new javax.swing.JPanel();
            jLabel31 = new javax.swing.JLabel();
            panelCPT17 = new javax.swing.JPanel();
            txtCPT14 = new javax.swing.JTextField();
            jPanel39 = new javax.swing.JPanel();
            jLabel32 = new javax.swing.JLabel();
            panelCPT18 = new javax.swing.JPanel();
            txtCPT15 = new javax.swing.JTextField();
            jPanel40 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            panelCPT19 = new javax.swing.JPanel();
            txtCPT16 = new javax.swing.JTextField();
            jPanel41 = new javax.swing.JPanel();
            jLabel34 = new javax.swing.JLabel();
            panelCPT20 = new javax.swing.JPanel();
            txtCPT17 = new javax.swing.JTextField();
            jPanel42 = new javax.swing.JPanel();
            jLabel35 = new javax.swing.JLabel();
            panelCPT21 = new javax.swing.JPanel();
            txtCPT18 = new javax.swing.JTextField();
            jPanel43 = new javax.swing.JPanel();
            jLabel36 = new javax.swing.JLabel();
            panelCPT22 = new javax.swing.JPanel();
            txtCPT19 = new javax.swing.JTextField();
            jPanel44 = new javax.swing.JPanel();
            jLabel37 = new javax.swing.JLabel();
            panelCPT23 = new javax.swing.JPanel();
            txtCPT20 = new javax.swing.JTextField();
            jPanel45 = new javax.swing.JPanel();
            jLabel38 = new javax.swing.JLabel();
            panelCPT24 = new javax.swing.JPanel();
            txtCPT21 = new javax.swing.JTextField();
            jPanel46 = new javax.swing.JPanel();
            btnGenerarDoc = new javax.swing.JButton();

            jPanel22.setBackground(new java.awt.Color(41, 127, 184));

            javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
            jPanel22.setLayout(jPanel22Layout);
            jPanel22Layout.setHorizontalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 19, Short.MAX_VALUE)
            );
            jPanel22Layout.setVerticalGroup(
                jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jMenuItem1.setText("jMenuItem1");
            Serie.add(jMenuItem1);

            jMenuItem2.setText("jMenuItem2");
            Serie.add(jMenuItem2);

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel21.setBackground(new java.awt.Color(41, 127, 184));

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 19, Short.MAX_VALUE)
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));
            jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

            jPanel2.setBackground(new java.awt.Color(41, 127, 184));

            btnGuardar.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnGuardar.setBorderPainted(false);
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            btnGuardar.setDefaultCapable(false);
            btnGuardar.setFocusPainted(false);
            btnGuardar.setFocusable(false);

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel3.setBackground(new java.awt.Color(41, 127, 184));

            btnGuardar1.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
            btnGuardar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
            btnGuardar1.setBorderPainted(false);
            btnGuardar1.setContentAreaFilled(false);
            btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            btnGuardar1.setDefaultCapable(false);
            btnGuardar1.setFocusPainted(false);
            btnGuardar1.setFocusable(false);

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel4.setBackground(new java.awt.Color(41, 127, 184));

            btnGuardar2.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imprimir-32.png"))); // NOI18N
            btnGuardar2.setBorderPainted(false);
            btnGuardar2.setContentAreaFilled(false);
            btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            btnGuardar2.setDefaultCapable(false);
            btnGuardar2.setFocusPainted(false);
            btnGuardar2.setFocusable(false);

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(41, 127, 184));
            jLabel1.setText("<html><span style=\"font-size:'30px'\">Cuenta Corriente - </span>Factura Electrónica</html>");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel5.setBackground(new java.awt.Color(255, 255, 255));
            jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

            jPanel6.setBackground(new java.awt.Color(255, 255, 255));
            jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            jPanel6.setDoubleBuffered(false);
            jPanel6.setFocusable(false);

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(102, 102, 102));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("Tipo de Operación:");

            cbxTipoOperacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxTipoOperacion.setForeground(new java.awt.Color(102, 102, 102));
            cbxTipoOperacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 VENTA INTERNA" }));
            cbxTipoOperacion.setBorder(null);
            cbxTipoOperacion.setLightWeightPopupEnabled(false);
            cbxTipoOperacion.setOpaque(false);
            cbxTipoOperacion.setRequestFocusEnabled(false);
            cbxTipoOperacion.setVerifyInputWhenFocusTarget(false);
            cbxTipoOperacion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipoOperacionActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(cbxTipoOperacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxTipoOperacion)
                    .addContainerGap())
            );

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
            jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            lblFechaEmision.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            lblFechaEmision.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFechaEmision.setText("2017-05-30");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 51, 51));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("Fecha de Emisión");

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaEmision)
                    .addContainerGap())
            );

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));
            jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            lblFechaEmision1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            lblFechaEmision1.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaEmision1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFechaEmision1.setText("30/05/2017");

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(102, 102, 102));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Cod. Domicilio Fiscal");

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(lblFechaEmision1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaEmision1)
                    .addContainerGap())
            );

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(255, 51, 51));
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Tipo Moneda");

            cbxTipoMoneda.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxTipoMoneda.setForeground(new java.awt.Color(102, 102, 102));
            cbxTipoMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN" }));
            cbxTipoMoneda.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxTipoMoneda.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipoMonedaActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxTipoMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));
            jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            cbxDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BOLETA", "FACTURA" }));
            cbxDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxDocumento.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxDocumentoItemStateChanged(evt);
                }
            });
            cbxDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxDocumentoActionPerformed(evt);
                }
            });

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(102, 102, 102));
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Documento");

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxDocumento, 0, 114, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel11.setBackground(new java.awt.Color(255, 255, 255));
            jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            cbxSerie.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxSerie.setForeground(new java.awt.Color(102, 102, 102));
            cbxSerie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "F001", "B001" }));
            cbxSerie.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxSerie.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxSerieActionPerformed(evt);
                }
            });

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(255, 51, 51));
            jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel8.setText("Serie");

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxSerie, 0, 76, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel12.setBackground(new java.awt.Color(255, 255, 255));
            jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel9.setForeground(new java.awt.Color(255, 51, 51));
            jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel9.setText("Nº Correlativo");

            lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            lblNroCorrelativo.setForeground(new java.awt.Color(102, 102, 102));
            lblNroCorrelativo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblNroCorrelativo.setText("00000001");

            javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
            jPanel12.setLayout(jPanel12Layout);
            jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                        .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNroCorrelativo)
                    .addContainerGap())
            );

            jPanel13.setBackground(new java.awt.Color(255, 255, 255));
            jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(255, 51, 51));
            jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel10.setText("Tipo Documento");

            cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxTipoDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "2 CARNET DE EXTRANJERIA", "3 RUC", "4 PASAPORTE", "5 CED.DIPLOMATICA DE IDENTIDAD" }));
            cbxTipoDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxTipoDocumento.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxTipoDocumentoItemStateChanged(evt);
                }
            });
            cbxTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxTipoDocumentoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
            jPanel13.setLayout(jPanel13Layout);
            jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxTipoDocumento)
                    .addContainerGap())
            );

            jPanel14.setBackground(new java.awt.Color(255, 255, 255));
            jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel11.setForeground(new java.awt.Color(255, 51, 51));
            jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel11.setText("Nro de Documento");

            panelCPT.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtTipoDocumento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtTipoDocumento.setForeground(new java.awt.Color(51, 51, 51));
            txtTipoDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtTipoDocumento.setBorder(null);
            txtTipoDocumento.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtTipoDocumentoCaretUpdate(evt);
                }
            });
            txtTipoDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtTipoDocumentoActionPerformed(evt);
                }
            });
            txtTipoDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtTipoDocumentoKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
            panelCPT.setLayout(panelCPTLayout);
            panelCPTLayout.setHorizontalGroup(
                panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtTipoDocumento, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPTLayout.setVerticalGroup(
                panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPTLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
            jPanel14.setLayout(jPanel14Layout);
            jPanel14Layout.setHorizontalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel14Layout.setVerticalGroup(
                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel15.setBackground(new java.awt.Color(255, 255, 255));
            jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel12.setForeground(new java.awt.Color(255, 51, 51));
            jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel12.setText("Apellidos y Nombres / Razón Social");

            panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT1.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT1.setBorder(null);
            txtCPT1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT1CaretUpdate(evt);
                }
            });
            txtCPT1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
            panelCPT1.setLayout(panelCPT1Layout);
            panelCPT1Layout.setHorizontalGroup(
                panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT1, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT1Layout.setVerticalGroup(
                panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT1Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel12)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel16.setBackground(new java.awt.Color(255, 255, 255));
            jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(255, 51, 51));
            jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel13.setText("Correo Electrónico");

            panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT2.setEditable(false);
            txtCPT2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT2.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT2.setBorder(null);
            txtCPT2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT2CaretUpdate(evt);
                }
            });
            txtCPT2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
            panelCPT2.setLayout(panelCPT2Layout);
            panelCPT2Layout.setHorizontalGroup(
                panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT2, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT2Layout.setVerticalGroup(
                panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel17.setBackground(new java.awt.Color(255, 255, 255));
            jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(102, 102, 102));
            jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel14.setText("Acto Médico");

            panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtActoMedico.setEditable(false);
            txtActoMedico.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtActoMedico.setForeground(new java.awt.Color(51, 51, 51));
            txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtActoMedico.setBorder(null);
            txtActoMedico.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtActoMedicoCaretUpdate(evt);
                }
            });
            txtActoMedico.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtActoMedicoActionPerformed(evt);
                }
            });
            txtActoMedico.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtActoMedicoKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
            panelCPT3.setLayout(panelCPT3Layout);
            panelCPT3Layout.setHorizontalGroup(
                panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtActoMedico)
            );
            panelCPT3Layout.setVerticalGroup(
                panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT3Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
            jPanel17.setLayout(jPanel17Layout);
            jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel14)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel18.setBackground(new java.awt.Color(255, 255, 255));
            jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            cbxGravado.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            cbxGravado.setForeground(new java.awt.Color(102, 102, 102));
            cbxGravado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GRAVADO", "EXONERADO", "INAFECTO", "EXPORTACION", "GRATUITAS" }));
            cbxGravado.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxGravado.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxGravadoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
            jPanel18.setLayout(jPanel18Layout);
            jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(cbxGravado, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(cbxGravado)
                    .addContainerGap())
            );

            jPanel19.setBackground(new java.awt.Color(255, 255, 255));
            jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            cbxCodUnidad.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            cbxCodUnidad.setForeground(new java.awt.Color(102, 102, 102));
            cbxCodUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NIU UNIDAD", "ZZ SERVICIO", "BX CAJA", "CT CAJA DE CARTON", "LTR LITRO", "MTR METRO", "CMT CENTIMETRO", "KGM KILOGRAMO" }));
            cbxCodUnidad.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxCodUnidad.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxCodUnidadActionPerformed(evt);
                }
            });

            jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel15.setForeground(new java.awt.Color(255, 51, 51));
            jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel15.setText("Cod. Unidad");

            javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
            jPanel19.setLayout(jPanel19Layout);
            jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxCodUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel15)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxCodUnidad)
                    .addContainerGap())
            );

            jPanel20.setBackground(new java.awt.Color(255, 255, 255));
            jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel16.setForeground(new java.awt.Color(255, 51, 51));
            jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel16.setText("Cantidad");

            panelCPT4.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT3.setEditable(false);
            txtCPT3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT3.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT3.setBorder(null);
            txtCPT3.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT3CaretUpdate(evt);
                }
            });
            txtCPT3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
            panelCPT4.setLayout(panelCPT4Layout);
            panelCPT4Layout.setHorizontalGroup(
                panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT3, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT4Layout.setVerticalGroup(
                panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
            jPanel20.setLayout(jPanel20Layout);
            jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel16)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel23.setBackground(new java.awt.Color(255, 255, 255));
            jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(102, 102, 102));
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel17.setText("Cod Producto");

            panelCPT5.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCodProducto.setEditable(false);
            txtCodProducto.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCodProducto.setForeground(new java.awt.Color(51, 51, 51));
            txtCodProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCodProducto.setBorder(null);
            txtCodProducto.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCodProductoCaretUpdate(evt);
                }
            });
            txtCodProducto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCodProductoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT5Layout = new javax.swing.GroupLayout(panelCPT5);
            panelCPT5.setLayout(panelCPT5Layout);
            panelCPT5Layout.setHorizontalGroup(
                panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCodProducto, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT5Layout.setVerticalGroup(
                panelCPT5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT5Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
            jPanel23.setLayout(jPanel23Layout);
            jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel17)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel24.setBackground(new java.awt.Color(255, 255, 255));
            jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
            jLabel18.setForeground(new java.awt.Color(102, 102, 102));
            jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel18.setText("<html><span style=\"font-size:'10px'\">Cod Producto</span> Sunat<html>");

            panelCPT6.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCodProducto1.setEditable(false);
            txtCodProducto1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCodProducto1.setForeground(new java.awt.Color(51, 51, 51));
            txtCodProducto1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCodProducto1.setBorder(null);
            txtCodProducto1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCodProducto1CaretUpdate(evt);
                }
            });
            txtCodProducto1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCodProducto1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
            panelCPT6.setLayout(panelCPT6Layout);
            panelCPT6Layout.setHorizontalGroup(
                panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCodProducto1, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT6Layout.setVerticalGroup(
                panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT6Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCodProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
            jPanel24.setLayout(jPanel24Layout);
            jPanel24Layout.setHorizontalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel24Layout.setVerticalGroup(
                jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel25.setBackground(new java.awt.Color(255, 255, 255));
            jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel19.setForeground(new java.awt.Color(255, 51, 51));
            jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel19.setText("Descripción");

            panelCPT7.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT4.setEditable(false);
            txtCPT4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT4.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT4.setBorder(null);
            txtCPT4.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT4CaretUpdate(evt);
                }
            });
            txtCPT4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT7Layout = new javax.swing.GroupLayout(panelCPT7);
            panelCPT7.setLayout(panelCPT7Layout);
            panelCPT7Layout.setHorizontalGroup(
                panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT4, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT7Layout.setVerticalGroup(
                panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT7Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
            jPanel25.setLayout(jPanel25Layout);
            jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel19)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel26.setBackground(new java.awt.Color(255, 255, 255));
            jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(255, 51, 51));
            jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel20.setText("Valor U.");

            panelCPT8.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT5.setEditable(false);
            txtCPT5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT5.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT5.setText("0.00");
            txtCPT5.setBorder(null);
            txtCPT5.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT5CaretUpdate(evt);
                }
            });
            txtCPT5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT8Layout = new javax.swing.GroupLayout(panelCPT8);
            panelCPT8.setLayout(panelCPT8Layout);
            panelCPT8Layout.setHorizontalGroup(
                panelCPT8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT5, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT8Layout.setVerticalGroup(
                panelCPT8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT8Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
            jPanel26.setLayout(jPanel26Layout);
            jPanel26Layout.setHorizontalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel20)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel27.setBackground(new java.awt.Color(255, 255, 255));
            jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel21.setForeground(new java.awt.Color(102, 102, 102));
            jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel21.setText("Desc. %");

            panelCPT9.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT6.setEditable(false);
            txtCPT6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT6.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT6.setText("0.00");
            txtCPT6.setBorder(null);
            txtCPT6.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT6CaretUpdate(evt);
                }
            });
            txtCPT6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT9Layout = new javax.swing.GroupLayout(panelCPT9);
            panelCPT9.setLayout(panelCPT9Layout);
            panelCPT9Layout.setHorizontalGroup(
                panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT6, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT9Layout.setVerticalGroup(
                panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT9Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
            jPanel27.setLayout(jPanel27Layout);
            jPanel27Layout.setHorizontalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelCPT9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel27Layout.setVerticalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel21)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel28.setBackground(new java.awt.Color(255, 255, 255));
            jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel22.setForeground(new java.awt.Color(102, 102, 102));
            jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel22.setText("Dscto");

            panelCPT10.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT7.setEditable(false);
            txtCPT7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT7.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT7.setText("0.00");
            txtCPT7.setBorder(null);
            txtCPT7.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT7CaretUpdate(evt);
                }
            });
            txtCPT7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT10Layout = new javax.swing.GroupLayout(panelCPT10);
            panelCPT10.setLayout(panelCPT10Layout);
            panelCPT10Layout.setHorizontalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            );
            panelCPT10Layout.setVerticalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel22)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));
            jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel23.setForeground(new java.awt.Color(255, 51, 51));
            jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel23.setText("IGV");

            panelCPT11.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT8.setEditable(false);
            txtCPT8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT8.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT8.setText("0.00");
            txtCPT8.setBorder(null);
            txtCPT8.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT8CaretUpdate(evt);
                }
            });
            txtCPT8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT11Layout = new javax.swing.GroupLayout(panelCPT11);
            panelCPT11.setLayout(panelCPT11Layout);
            panelCPT11Layout.setHorizontalGroup(
                panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            );
            panelCPT11Layout.setVerticalGroup(
                panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT11Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel23)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel30.setBackground(new java.awt.Color(255, 255, 255));
            jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel24.setForeground(new java.awt.Color(255, 51, 51));
            jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel24.setText("Afec. IGV");

            cbxIGV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            cbxIGV.setForeground(new java.awt.Color(102, 102, 102));
            cbxIGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20" }));
            cbxIGV.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxIGV.setLightWeightPopupEnabled(false);
            cbxIGV.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxIGVActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxIGV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel30Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel24)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxIGV)
                    .addContainerGap())
            );

            jPanel31.setBackground(new java.awt.Color(255, 255, 255));
            jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel25.setForeground(new java.awt.Color(102, 102, 102));
            jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel25.setText("ISC");

            panelCPT12.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT9.setEditable(false);
            txtCPT9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT9.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT9.setText("0.00");
            txtCPT9.setBorder(null);
            txtCPT9.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT9CaretUpdate(evt);
                }
            });
            txtCPT9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT9ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT12Layout = new javax.swing.GroupLayout(panelCPT12);
            panelCPT12.setLayout(panelCPT12Layout);
            panelCPT12Layout.setHorizontalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
            );
            panelCPT12Layout.setVerticalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT12Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
            jPanel31.setLayout(jPanel31Layout);
            jPanel31Layout.setHorizontalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel31Layout.setVerticalGroup(
                jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel31Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel25)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel32.setBackground(new java.awt.Color(255, 255, 255));
            jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(102, 102, 102));
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setText("Afec. ISC");

            cbxISC.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            cbxISC.setForeground(new java.awt.Color(102, 102, 102));
            cbxISC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10", "20" }));
            cbxISC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxISC.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxISCActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
            jPanel32.setLayout(jPanel32Layout);
            jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel32Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(0, 216, Short.MAX_VALUE))
                        .addComponent(cbxISC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbxISC)
                    .addContainerGap())
            );

            jPanel33.setBackground(new java.awt.Color(255, 255, 255));
            jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel27.setForeground(new java.awt.Color(255, 51, 51));
            jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel27.setText("Precio Venta");

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT10.setEditable(false);
            txtCPT10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT10.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT10.setText("0.00");
            txtCPT10.setBorder(null);
            txtCPT10.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT10CaretUpdate(evt);
                }
            });
            txtCPT10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT10ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
            panelCPT13.setLayout(panelCPT13Layout);
            panelCPT13Layout.setHorizontalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT10, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
            jPanel33.setLayout(jPanel33Layout);
            jPanel33Layout.setHorizontalGroup(
                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel33Layout.setVerticalGroup(
                jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel33Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel27)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            jPanel34.setBackground(new java.awt.Color(255, 255, 255));
            jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel28.setForeground(new java.awt.Color(255, 51, 51));
            jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel28.setText("Valor de Venta");

            panelCPT14.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT11.setEditable(false);
            txtCPT11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
            txtCPT11.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT11.setText("0.00");
            txtCPT11.setBorder(null);
            txtCPT11.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT11CaretUpdate(evt);
                }
            });
            txtCPT11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT11ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT14Layout = new javax.swing.GroupLayout(panelCPT14);
            panelCPT14.setLayout(panelCPT14Layout);
            panelCPT14Layout.setHorizontalGroup(
                panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT11, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT14Layout.setVerticalGroup(
                panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT14Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
            jPanel34.setLayout(jPanel34Layout);
            jPanel34Layout.setHorizontalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                        .addComponent(panelCPT14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel34Layout.setVerticalGroup(
                jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel28)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(6, 6, 6)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            tablaS.setBackground(new java.awt.Color(255, 255, 255));
            tablaS.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            tablaS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            tablaS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            tablaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

            tbFacturacion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            tbFacturacion.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
            ));
            tbFacturacion.setGridColor(new java.awt.Color(255, 255, 255));
            tbFacturacion.setRowHeight(25);
            tbFacturacion.setSelectionBackground(new java.awt.Color(41, 127, 184));
            tbFacturacion.getTableHeader().setReorderingAllowed(false);
            tbFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbFacturacionMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbFacturacionMousePressed(evt);
                }
            });
            tbFacturacion.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbFacturacionKeyPressed(evt);
                }
            });
            tablaS.setViewportView(tbFacturacion);

            jPanel35.setBackground(new java.awt.Color(255, 255, 255));
            jPanel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
            jPanel35.setLayout(jPanel35Layout);
            jPanel35Layout.setHorizontalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );
            jPanel35Layout.setVerticalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 29, Short.MAX_VALUE)
            );

            jPanel36.setBackground(new java.awt.Color(255, 255, 255));
            jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel29.setForeground(new java.awt.Color(102, 102, 102));
            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel29.setText("Descuento Global");

            panelCPT15.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT12.setEditable(false);
            txtCPT12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT12.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT12.setText("0.00");
            txtCPT12.setBorder(null);
            txtCPT12.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT12CaretUpdate(evt);
                }
            });
            txtCPT12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT12ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT15Layout = new javax.swing.GroupLayout(panelCPT15);
            panelCPT15.setLayout(panelCPT15Layout);
            panelCPT15Layout.setHorizontalGroup(
                panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT12, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT15Layout.setVerticalGroup(
                panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT15Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
            jPanel36.setLayout(jPanel36Layout);
            jPanel36Layout.setHorizontalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel36Layout.setVerticalGroup(
                jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel29)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel37.setBackground(new java.awt.Color(255, 255, 255));
            jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(102, 102, 102));
            jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel30.setText("Sum. otros Cargos");

            panelCPT16.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT13.setEditable(false);
            txtCPT13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT13.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT13.setText("0.00");
            txtCPT13.setBorder(null);
            txtCPT13.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT13CaretUpdate(evt);
                }
            });
            txtCPT13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT13ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT16Layout = new javax.swing.GroupLayout(panelCPT16);
            panelCPT16.setLayout(panelCPT16Layout);
            panelCPT16Layout.setHorizontalGroup(
                panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT13, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT16Layout.setVerticalGroup(
                panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT16Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
            jPanel37.setLayout(jPanel37Layout);
            jPanel37Layout.setHorizontalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel37Layout.setVerticalGroup(
                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel37Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel30)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel38.setBackground(new java.awt.Color(255, 255, 255));
            jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel31.setForeground(new java.awt.Color(102, 102, 102));
            jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel31.setText("Sumatoria IGV");

            panelCPT17.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT14.setEditable(false);
            txtCPT14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT14.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT14.setText("0.00");
            txtCPT14.setBorder(null);
            txtCPT14.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT14CaretUpdate(evt);
                }
            });
            txtCPT14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT14ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT17Layout = new javax.swing.GroupLayout(panelCPT17);
            panelCPT17.setLayout(panelCPT17Layout);
            panelCPT17Layout.setHorizontalGroup(
                panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT14, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT17Layout.setVerticalGroup(
                panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT17Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
            jPanel38.setLayout(jPanel38Layout);
            jPanel38Layout.setHorizontalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel38Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel38Layout.setVerticalGroup(
                jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel38Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel31)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel39.setBackground(new java.awt.Color(255, 255, 255));
            jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel32.setForeground(new java.awt.Color(255, 51, 51));
            jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel32.setText("Total Valor de Ventas Gravadas");

            panelCPT18.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT15.setEditable(false);
            txtCPT15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT15.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT15.setText("0.00");
            txtCPT15.setBorder(null);
            txtCPT15.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT15CaretUpdate(evt);
                }
            });
            txtCPT15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT15ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT18Layout = new javax.swing.GroupLayout(panelCPT18);
            panelCPT18.setLayout(panelCPT18Layout);
            panelCPT18Layout.setHorizontalGroup(
                panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT15, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT18Layout.setVerticalGroup(
                panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT18Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
            jPanel39.setLayout(jPanel39Layout);
            jPanel39Layout.setHorizontalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel39Layout.setVerticalGroup(
                jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel39Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel40.setBackground(new java.awt.Color(255, 255, 255));
            jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(255, 51, 51));
            jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel33.setText("Total Valor de Ventas Inafectadas");

            panelCPT19.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT16.setEditable(false);
            txtCPT16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT16.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT16.setText("0.00");
            txtCPT16.setBorder(null);
            txtCPT16.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT16CaretUpdate(evt);
                }
            });
            txtCPT16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT16ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT19Layout = new javax.swing.GroupLayout(panelCPT19);
            panelCPT19.setLayout(panelCPT19Layout);
            panelCPT19Layout.setHorizontalGroup(
                panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT16, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT19Layout.setVerticalGroup(
                panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT19Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
            jPanel40.setLayout(jPanel40Layout);
            jPanel40Layout.setHorizontalGroup(
                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel40Layout.setVerticalGroup(
                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel33)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel41.setBackground(new java.awt.Color(255, 255, 255));
            jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel34.setForeground(new java.awt.Color(102, 102, 102));
            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel34.setText("Total Descuentos");

            panelCPT20.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT17.setEditable(false);
            txtCPT17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT17.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT17.setText("0.00");
            txtCPT17.setBorder(null);
            txtCPT17.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT17CaretUpdate(evt);
                }
            });
            txtCPT17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT17ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT20Layout = new javax.swing.GroupLayout(panelCPT20);
            panelCPT20.setLayout(panelCPT20Layout);
            panelCPT20Layout.setHorizontalGroup(
                panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT17, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT20Layout.setVerticalGroup(
                panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT20Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
            jPanel41.setLayout(jPanel41Layout);
            jPanel41Layout.setHorizontalGroup(
                jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel41Layout.setVerticalGroup(
                jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel34)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel42.setBackground(new java.awt.Color(255, 255, 255));
            jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel35.setForeground(new java.awt.Color(102, 102, 102));
            jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel35.setText("Sum. otros Tributos");

            panelCPT21.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT18.setEditable(false);
            txtCPT18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT18.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT18.setText("0.00");
            txtCPT18.setBorder(null);
            txtCPT18.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT18CaretUpdate(evt);
                }
            });
            txtCPT18.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT18ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT21Layout = new javax.swing.GroupLayout(panelCPT21);
            panelCPT21.setLayout(panelCPT21Layout);
            panelCPT21Layout.setHorizontalGroup(
                panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT18, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT21Layout.setVerticalGroup(
                panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT21Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
            jPanel42.setLayout(jPanel42Layout);
            jPanel42Layout.setHorizontalGroup(
                jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel42Layout.setVerticalGroup(
                jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel35)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel43.setBackground(new java.awt.Color(255, 255, 255));
            jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel36.setForeground(new java.awt.Color(102, 102, 102));
            jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel36.setText("Sumatoria ISC");

            panelCPT22.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT19.setEditable(false);
            txtCPT19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT19.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT19.setText("0.00");
            txtCPT19.setBorder(null);
            txtCPT19.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT19CaretUpdate(evt);
                }
            });
            txtCPT19.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT19ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT22Layout = new javax.swing.GroupLayout(panelCPT22);
            panelCPT22.setLayout(panelCPT22Layout);
            panelCPT22Layout.setHorizontalGroup(
                panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT19, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT22Layout.setVerticalGroup(
                panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT22Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
            jPanel43.setLayout(jPanel43Layout);
            jPanel43Layout.setHorizontalGroup(
                jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addComponent(panelCPT22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel43Layout.setVerticalGroup(
                jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel43Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel36)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel44.setBackground(new java.awt.Color(255, 255, 255));
            jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel37.setForeground(new java.awt.Color(255, 51, 51));
            jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel37.setText("Total de Ventas Exoneradas");

            panelCPT23.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT20.setEditable(false);
            txtCPT20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT20.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT20.setText("0.00");
            txtCPT20.setBorder(null);
            txtCPT20.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT20CaretUpdate(evt);
                }
            });
            txtCPT20.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT20ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT23Layout = new javax.swing.GroupLayout(panelCPT23);
            panelCPT23.setLayout(panelCPT23Layout);
            panelCPT23Layout.setHorizontalGroup(
                panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT20, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT23Layout.setVerticalGroup(
                panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT23Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
            jPanel44.setLayout(jPanel44Layout);
            jPanel44Layout.setHorizontalGroup(
                jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel44Layout.setVerticalGroup(
                jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel44Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel37)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel45.setBackground(new java.awt.Color(255, 255, 255));
            jPanel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
            jLabel38.setForeground(new java.awt.Color(255, 51, 51));
            jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel38.setText("Importe Total de Venta");

            panelCPT24.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT21.setEditable(false);
            txtCPT21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            txtCPT21.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT21.setText("0.00");
            txtCPT21.setBorder(null);
            txtCPT21.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT21CaretUpdate(evt);
                }
            });
            txtCPT21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPT21ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT24Layout = new javax.swing.GroupLayout(panelCPT24);
            panelCPT24.setLayout(panelCPT24Layout);
            panelCPT24Layout.setHorizontalGroup(
                panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtCPT21, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT24Layout.setVerticalGroup(
                panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT24Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
            jPanel45.setLayout(jPanel45Layout);
            jPanel45Layout.setHorizontalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel45Layout.setVerticalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(jLabel38)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelCPT24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel46.setBackground(new java.awt.Color(41, 127, 184));
            jPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            btnGenerarDoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            btnGenerarDoc.setForeground(new java.awt.Color(255, 255, 255));
            btnGenerarDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
            btnGenerarDoc.setMnemonic('G');
            btnGenerarDoc.setText("<html>Generar Documento <br>Electrónico</html>");
            btnGenerarDoc.setBorderPainted(false);
            btnGenerarDoc.setContentAreaFilled(false);
            btnGenerarDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGenerarDoc.setIconTextGap(20);

            javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
            jPanel46.setLayout(jPanel46Layout);
            jPanel46Layout.setHorizontalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnGenerarDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            );
            jPanel46Layout.setVerticalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnGenerarDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tablaS, javax.swing.GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(1, 1, 1)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoOperacionActionPerformed

    private void cbxTipoMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMonedaActionPerformed

    private void cbxDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoActionPerformed

    private void cbxSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSerieActionPerformed

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void txtTipoDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTipoDocumentoCaretUpdate

    }//GEN-LAST:event_txtTipoDocumentoCaretUpdate

    private void txtTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoDocumentoActionPerformed

    private void txtCPT1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT1CaretUpdate

    private void txtCPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT1ActionPerformed

    private void txtCPT2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT2CaretUpdate

    private void txtCPT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT2ActionPerformed

    private void txtActoMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtActoMedicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActoMedicoCaretUpdate

    private void txtActoMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActoMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActoMedicoActionPerformed

    private void cbxGravadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGravadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGravadoActionPerformed

    private void cbxCodUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCodUnidadActionPerformed

    private void txtCPT3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT3CaretUpdate

    private void txtCPT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT3ActionPerformed

    private void txtCodProductoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProductoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoCaretUpdate

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProducto1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProducto1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProducto1CaretUpdate

    private void txtCodProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProducto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProducto1ActionPerformed

    private void txtCPT4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT4CaretUpdate

    private void txtCPT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT4ActionPerformed

    private void txtCPT5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT5CaretUpdate

    private void txtCPT5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT5ActionPerformed

    private void txtCPT6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT6CaretUpdate

    private void txtCPT6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT6ActionPerformed

    private void txtCPT7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT7CaretUpdate

    private void txtCPT7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT7ActionPerformed

    private void txtCPT8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT8CaretUpdate

    private void txtCPT8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT8ActionPerformed

    private void cbxIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxIGVActionPerformed

    private void txtCPT9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT9CaretUpdate

    private void txtCPT9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT9ActionPerformed

    private void cbxISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxISCActionPerformed

    private void txtCPT10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT10CaretUpdate

    private void txtCPT10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT10ActionPerformed

    private void txtCPT11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT11CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT11CaretUpdate

    private void txtCPT11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT11ActionPerformed

    private void tbFacturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMouseClicked

    }//GEN-LAST:event_tbFacturacionMouseClicked

    private void tbFacturacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMousePressed

    }//GEN-LAST:event_tbFacturacionMousePressed

    private void tbFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturacionKeyPressed

    }//GEN-LAST:event_tbFacturacionKeyPressed

    private void txtCPT12CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT12CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT12CaretUpdate

    private void txtCPT12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT12ActionPerformed

    private void txtCPT13CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT13CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT13CaretUpdate

    private void txtCPT13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT13ActionPerformed

    private void txtCPT14CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT14CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT14CaretUpdate

    private void txtCPT14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT14ActionPerformed

    private void txtCPT15CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT15CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT15CaretUpdate

    private void txtCPT15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT15ActionPerformed

    private void txtCPT16CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT16CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT16CaretUpdate

    private void txtCPT16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT16ActionPerformed

    private void txtCPT17CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT17CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT17CaretUpdate

    private void txtCPT17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT17ActionPerformed

    private void txtCPT18CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT18CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT18CaretUpdate

    private void txtCPT18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT18ActionPerformed

    private void txtCPT19CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT19CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT19CaretUpdate

    private void txtCPT19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT19ActionPerformed

    private void txtCPT20CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT20CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT20CaretUpdate

    private void txtCPT20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT20ActionPerformed

    private void txtCPT21CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT21CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT21CaretUpdate

    private void txtCPT21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPT21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT21ActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        if(cbxTipoDocumento.getSelectedIndex()==0){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
            txtTipoDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==1){ //DNI
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(8);
            txtTipoDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==2){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(12);
            txtTipoDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==3){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(11);
            txtTipoDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==4){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(12);
            txtTipoDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==5){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
            txtTipoDocumento.setDocument(limitObservacion);
        } 
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

    private void cbxDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDocumentoItemStateChanged
        if(cbxDocumento.getSelectedIndex()==0){
            cbxSerie.setSelectedItem("F001");
            lblNroCorrelativo.setText("00000001");
        } else
        if(cbxDocumento.getSelectedIndex()==1){
            cbxSerie.setSelectedItem("B001");
            lblNroCorrelativo.setText("00000002");
        }    
    }//GEN-LAST:event_cbxDocumentoItemStateChanged

    private void txtTipoDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoDocumentoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(cbxTipoDocumento.getSelectedIndex()==1 || cbxTipoDocumento.getSelectedIndex()==3){//DNI
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
        }
    }//GEN-LAST:event_txtTipoDocumentoKeyTyped

    private void txtActoMedicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActoMedicoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
    }//GEN-LAST:event_txtActoMedicoKeyTyped

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
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu Serie;
    private javax.swing.JButton btnGenerarDoc;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JComboBox cbxCodUnidad;
    private javax.swing.JComboBox cbxDocumento;
    private javax.swing.JComboBox cbxGravado;
    private javax.swing.JComboBox cbxIGV;
    private javax.swing.JComboBox cbxISC;
    private javax.swing.JComboBox cbxSerie;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JComboBox cbxTipoMoneda;
    private javax.swing.JComboBox cbxTipoOperacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblFechaEmision1;
    private javax.swing.JLabel lblNroCorrelativo;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT11;
    private javax.swing.JPanel panelCPT12;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT14;
    private javax.swing.JPanel panelCPT15;
    private javax.swing.JPanel panelCPT16;
    private javax.swing.JPanel panelCPT17;
    private javax.swing.JPanel panelCPT18;
    private javax.swing.JPanel panelCPT19;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT20;
    private javax.swing.JPanel panelCPT21;
    private javax.swing.JPanel panelCPT22;
    private javax.swing.JPanel panelCPT23;
    private javax.swing.JPanel panelCPT24;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelCPT7;
    private javax.swing.JPanel panelCPT8;
    private javax.swing.JPanel panelCPT9;
    private javax.swing.JScrollPane tablaS;
    private javax.swing.JTable tbFacturacion;
    public static javax.swing.JTextField txtActoMedico;
    public static javax.swing.JTextField txtCPT1;
    public static javax.swing.JTextField txtCPT10;
    public static javax.swing.JTextField txtCPT11;
    public static javax.swing.JTextField txtCPT12;
    public static javax.swing.JTextField txtCPT13;
    public static javax.swing.JTextField txtCPT14;
    public static javax.swing.JTextField txtCPT15;
    public static javax.swing.JTextField txtCPT16;
    public static javax.swing.JTextField txtCPT17;
    public static javax.swing.JTextField txtCPT18;
    public static javax.swing.JTextField txtCPT19;
    public static javax.swing.JTextField txtCPT2;
    public static javax.swing.JTextField txtCPT20;
    public static javax.swing.JTextField txtCPT21;
    public static javax.swing.JTextField txtCPT3;
    public static javax.swing.JTextField txtCPT4;
    public static javax.swing.JTextField txtCPT5;
    public static javax.swing.JTextField txtCPT6;
    public static javax.swing.JTextField txtCPT7;
    public static javax.swing.JTextField txtCPT8;
    public static javax.swing.JTextField txtCPT9;
    public static javax.swing.JTextField txtCodProducto;
    public static javax.swing.JTextField txtCodProducto1;
    public static javax.swing.JTextField txtTipoDocumento;
    // End of variables declaration//GEN-END:variables
}
