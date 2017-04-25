/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import modelos.ConsultorioEx.ConsultorioExtEsnitss;


/**
 *
 * @author MYS1
 */
public class RegistroEmbarazo extends javax.swing.JFrame {

    /**
     * Creates new form RegistroEmbarazo
     */
    public RegistroEmbarazo() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        pnlControl.setVisible(false);
        pnlAp.setVisible(false);
        pnlEs.setVisible(false);
    }
    
    public void limpiar(){}
    
    public void habilitarDatos(boolean opcion){}

    public void enviarDatosMadres(){
        int fila = tbMadres.getSelectedRow();
//        txtDniMadre.setText(String.valueOf(tbMadres.getValueAt(fila, 0)));
//        txtMadre.setText(String.valueOf(tbMadres.getValueAt(fila, 2)));
//        lblTelefono.setText(String.valueOf(tbMadres.getValueAt(fila, 3)));
//        lblDireccion.setText(String.valueOf(tbMadres.getValueAt(fila, 4)));
//        lblHcMadre.setText(String.valueOf(tbMadres.getValueAt(fila, 5)));
//        lblSector.setText(String.valueOf(tbMadres.getValueAt(fila, 6)));
        BuscarMadres.dispose();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarMadres = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        T7 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbMadres = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jTabbedPane1 = new javax.swing.JTabbedPane();
            jPanel2 = new javax.swing.JPanel();
            pnlEs = new javax.swing.JPanel();
            pnlAp = new javax.swing.JPanel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblusu = new javax.swing.JLabel();
            btnCaccnelar = new javax.swing.JButton();
            btnbuscar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            jLabel2 = new javax.swing.JLabel();
            ChkAnalf1 = new javax.swing.JTextField();
            jLabel30 = new javax.swing.JLabel();
            ChkEdad1 = new javax.swing.JTextField();
            jLabel37 = new javax.swing.JLabel();
            btnCaccnelar1 = new javax.swing.JButton();
            pnlControl = new javax.swing.JPanel();
            jPanel8 = new javax.swing.JPanel();
            Chkprim = new javax.swing.JTextField();
            jLabel32 = new javax.swing.JLabel();
            jPanel18 = new javax.swing.JPanel();
            chkSoltera = new javax.swing.JTextField();
            jLabel42 = new javax.swing.JLabel();
            txtPadreRN = new javax.swing.JTextField();
            jLabel11 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            jCheckBox5 = new javax.swing.JCheckBox();
            jLabel8 = new javax.swing.JLabel();
            jPanel17 = new javax.swing.JPanel();
            chkConviviente = new javax.swing.JTextField();
            jLabel41 = new javax.swing.JLabel();
            jCheckBox4 = new javax.swing.JCheckBox();
            jPanel9 = new javax.swing.JPanel();
            ChkSec = new javax.swing.JTextField();
            jLabel33 = new javax.swing.JLabel();
            jLabel24 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            ChkAnalf = new javax.swing.JTextField();
            jLabel31 = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jPanel20 = new javax.swing.JPanel();
            chkNoApl = new javax.swing.JTextField();
            jLabel46 = new javax.swing.JLabel();
            jCheckBox3 = new javax.swing.JCheckBox();
            jLabel22 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            jLabel28 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            ChkEdad = new javax.swing.JTextField();
            jPanel16 = new javax.swing.JPanel();
            chkCasada = new javax.swing.JTextField();
            jLabel40 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jPanel21 = new javax.swing.JPanel();
            chkRef = new javax.swing.JTextField();
            jLabel47 = new javax.swing.JLabel();
            jPanel11 = new javax.swing.JPanel();
            ChkSup = new javax.swing.JTextField();
            jLabel34 = new javax.swing.JLabel();
            jLabel39 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jLabel13 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jPanel19 = new javax.swing.JPanel();
            chkOtro = new javax.swing.JTextField();
            jLabel43 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            jPanel15 = new javax.swing.JPanel();
            txtPaciente2 = new javax.swing.JTextField();
            btnBuscarNino2 = new javax.swing.JButton();
            jPanel13 = new javax.swing.JPanel();
            ChkAnAp = new javax.swing.JTextField();
            jLabel36 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            jLabel23 = new javax.swing.JLabel();
            jLabel29 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            txtPadre1 = new javax.swing.JTextField();
            jLabel38 = new javax.swing.JLabel();
            txtPadre = new javax.swing.JTextField();
            jPanel12 = new javax.swing.JPanel();
            ChkSupnU = new javax.swing.JTextField();
            jLabel35 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            txtPaciente1 = new javax.swing.JTextField();
            btnBuscarNino1 = new javax.swing.JButton();
            lblNhc = new javax.swing.JLabel();
            lblHc = new javax.swing.JLabel();
            Contenedor = new javax.swing.JDesktopPane();
            ContenedorTablas = new javax.swing.JDesktopPane();

            BuscarMadres.setAlwaysOnTop(true);
            BuscarMadres.setMinimumSize(new java.awt.Dimension(615, 333));
            BuscarMadres.setResizable(false);

            jPanel28.setBackground(new java.awt.Color(102, 102, 102));

            jLabel45.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel45.setForeground(new java.awt.Color(255, 255, 255));
            jLabel45.setText("<html>Madre<span style=\"font-size:'15px'\"><br>del menor</br></span></html>");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));

            txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtBuscar.setBorder(null);
            txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarCaretUpdate(evt);
                }
            });
            txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    txtBuscarMouseClicked(evt);
                }
            });
            txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarActionPerformed(evt);
                }
            });
            txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscarKeyPressed(evt);
                }
            });

            T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            T7.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    T7MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(T7)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            jPanel30.setBackground(new java.awt.Color(0, 153, 102));

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 615, Short.MAX_VALUE)
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 9, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
            jPanel28.setLayout(jPanel28Layout);
            jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel28Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                    .addContainerGap(17, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            jScrollPane4.setBorder(null);

            tbMadres.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tbMadres.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbMadres.setGridColor(new java.awt.Color(255, 255, 255));
            tbMadres.setRowHeight(25);
            tbMadres.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbMadres.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbMadresMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbMadresMousePressed(evt);
                }
            });
            tbMadres.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbMadresKeyPressed(evt);
                }
            });
            jScrollPane4.setViewportView(tbMadres);

            javax.swing.GroupLayout BuscarMadresLayout = new javax.swing.GroupLayout(BuscarMadres.getContentPane());
            BuscarMadres.getContentPane().setLayout(BuscarMadresLayout);
            BuscarMadresLayout.setHorizontalGroup(
                BuscarMadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
            );
            BuscarMadresLayout.setVerticalGroup(
                BuscarMadresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BuscarMadresLayout.createSequentialGroup()
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);

            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            pnlEs.setBackground(new java.awt.Color(39, 174, 97));
            pnlEs.setPreferredSize(new java.awt.Dimension(15, 34));

            javax.swing.GroupLayout pnlEsLayout = new javax.swing.GroupLayout(pnlEs);
            pnlEs.setLayout(pnlEsLayout);
            pnlEsLayout.setHorizontalGroup(
                pnlEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 14, Short.MAX_VALUE)
            );
            pnlEsLayout.setVerticalGroup(
                pnlEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 37, Short.MAX_VALUE)
            );

            pnlAp.setBackground(new java.awt.Color(39, 174, 97));
            pnlAp.setPreferredSize(new java.awt.Dimension(15, 34));

            javax.swing.GroupLayout pnlApLayout = new javax.swing.GroupLayout(pnlAp);
            pnlAp.setLayout(pnlApLayout);
            pnlApLayout.setHorizontalGroup(
                pnlApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 13, Short.MAX_VALUE)
            );
            pnlApLayout.setVerticalGroup(
                pnlApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 37, Short.MAX_VALUE)
            );

            jPanel1.setBackground(new java.awt.Color(51, 51, 51));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("<html>Carnet de<br>Control Materno <br>Perinatal <span style=\"font-size:'15px'\"></span></html>");

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
            lblusu.setText("Silvana");
            lblusu.setFocusable(false);
            lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

            btnCaccnelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnCaccnelar.setForeground(new java.awt.Color(255, 255, 255));
            btnCaccnelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casa-32.png"))); // NOI18N
            btnCaccnelar.setText("Detalles");
            btnCaccnelar.setToolTipText("");
            btnCaccnelar.setContentAreaFilled(false);
            btnCaccnelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCaccnelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnCaccnelar.setIconTextGap(30);
            btnCaccnelar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCaccnelarActionPerformed(evt);
                }
            });

            btnbuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
            btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
            btnbuscar.setText("Buscar");
            btnbuscar.setContentAreaFilled(false);
            btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnbuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnbuscar.setIconTextGap(30);
            btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnbuscarActionPerformed(evt);
                }
            });

            btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnGuardar.setText("Guardar");
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnGuardar.setIconTextGap(30);
            btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(204, 204, 204));
            jLabel2.setText("Leyenda");

            ChkAnalf1.setEditable(false);
            ChkAnalf1.setBackground(new java.awt.Color(255, 204, 51));
            ChkAnalf1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkAnalf1.setForeground(new java.awt.Color(102, 102, 102));
            ChkAnalf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkAnalf1.setBorder(null);
            ChkAnalf1.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkAnalf1.setSelectionColor(new java.awt.Color(255, 204, 51));
            ChkAnalf1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkAnalf1CaretUpdate(evt);
                }
            });
            ChkAnalf1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkAnalf1MouseClicked(evt);
                }
            });

            jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(255, 255, 255));
            jLabel30.setText("ALERTA");

            ChkEdad1.setEditable(false);
            ChkEdad1.setBackground(new java.awt.Color(255, 51, 51));
            ChkEdad1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkEdad1.setForeground(new java.awt.Color(255, 255, 255));
            ChkEdad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkEdad1.setBorder(null);
            ChkEdad1.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkEdad1.setSelectionColor(new java.awt.Color(255, 51, 51));
            ChkEdad1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkEdad1CaretUpdate(evt);
                }
            });
            ChkEdad1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkEdad1MouseClicked(evt);
                }
            });

            jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel37.setForeground(new java.awt.Color(255, 255, 255));
            jLabel37.setText("Requiere Seguimiento Continuo");

            btnCaccnelar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnCaccnelar1.setForeground(new java.awt.Color(255, 255, 255));
            btnCaccnelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
            btnCaccnelar1.setText("Nuevo");
            btnCaccnelar1.setToolTipText("");
            btnCaccnelar1.setContentAreaFilled(false);
            btnCaccnelar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnCaccnelar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnCaccnelar1.setIconTextGap(30);
            btnCaccnelar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCaccnelar1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel30))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel37)))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                                    .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(btnCaccnelar)
                                .addComponent(btnCaccnelar1))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnCaccnelar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(14, 14, 14)
                    .addComponent(btnGuardar)
                    .addGap(18, 18, 18)
                    .addComponent(btnbuscar)
                    .addGap(18, 18, 18)
                    .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(78, 78, 78)
                    .addComponent(lblusu)
                    .addGap(28, 28, 28)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37))
                    .addGap(249, 249, 249))
            );

            pnlControl.setBackground(new java.awt.Color(255, 255, 255));

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));

            Chkprim.setEditable(false);
            Chkprim.setBackground(new java.awt.Color(204, 204, 204));
            Chkprim.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            Chkprim.setForeground(new java.awt.Color(102, 102, 102));
            Chkprim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            Chkprim.setBorder(null);
            Chkprim.setPreferredSize(new java.awt.Dimension(28, 28));
            Chkprim.setSelectionColor(new java.awt.Color(204, 204, 204));
            Chkprim.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkprimCaretUpdate(evt);
                }
            });
            Chkprim.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkprimMouseClicked(evt);
                }
            });

            jLabel32.setForeground(new java.awt.Color(51, 51, 51));
            jLabel32.setText("Primaria");

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addComponent(jLabel32)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                    .addComponent(Chkprim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Chkprim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jPanel18.setBackground(new java.awt.Color(255, 255, 255));

            chkSoltera.setEditable(false);
            chkSoltera.setBackground(new java.awt.Color(255, 204, 51));
            chkSoltera.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkSoltera.setForeground(new java.awt.Color(102, 102, 102));
            chkSoltera.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkSoltera.setBorder(null);
            chkSoltera.setPreferredSize(new java.awt.Dimension(28, 28));
            chkSoltera.setSelectionColor(new java.awt.Color(255, 204, 51));
            chkSoltera.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkSolteraCaretUpdate(evt);
                }
            });
            chkSoltera.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkSolteraMouseClicked(evt);
                }
            });

            jLabel42.setForeground(new java.awt.Color(51, 51, 51));
            jLabel42.setText("Soltera");

            javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
            jPanel18.setLayout(jPanel18Layout);
            jPanel18Layout.setHorizontalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                    .addComponent(jLabel42)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                    .addComponent(chkSoltera, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel18Layout.setVerticalGroup(
                jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkSoltera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel11.setForeground(new java.awt.Color(51, 51, 51));
            jLabel11.setText("Cod. Sector");

            jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel25.setForeground(new java.awt.Color(51, 51, 51));
            jLabel25.setText("Edad");

            jCheckBox5.setBackground(new java.awt.Color(255, 255, 255));
            jCheckBox5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jCheckBox5.setText("PRIVADO");

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(51, 51, 51));
            jLabel8.setText("Teléfono");

            jPanel17.setBackground(new java.awt.Color(255, 255, 255));

            chkConviviente.setEditable(false);
            chkConviviente.setBackground(new java.awt.Color(204, 204, 204));
            chkConviviente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkConviviente.setForeground(new java.awt.Color(102, 102, 102));
            chkConviviente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkConviviente.setBorder(null);
            chkConviviente.setPreferredSize(new java.awt.Dimension(28, 28));
            chkConviviente.setSelectionColor(new java.awt.Color(204, 204, 204));
            chkConviviente.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkConvivienteCaretUpdate(evt);
                }
            });
            chkConviviente.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkConvivienteMouseClicked(evt);
                }
            });

            jLabel41.setForeground(new java.awt.Color(51, 51, 51));
            jLabel41.setText("Conviviente");

            javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
            jPanel17.setLayout(jPanel17Layout);
            jPanel17Layout.setHorizontalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                    .addComponent(jLabel41)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkConviviente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel17Layout.setVerticalGroup(
                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel17Layout.createSequentialGroup()
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkConviviente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jCheckBox4.setBackground(new java.awt.Color(255, 255, 255));
            jCheckBox4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jCheckBox4.setText("ESSALUD");

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));

            ChkSec.setEditable(false);
            ChkSec.setBackground(new java.awt.Color(204, 204, 204));
            ChkSec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkSec.setForeground(new java.awt.Color(102, 102, 102));
            ChkSec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkSec.setBorder(null);
            ChkSec.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkSec.setSelectionColor(new java.awt.Color(204, 204, 204));
            ChkSec.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkSecCaretUpdate(evt);
                }
            });
            ChkSec.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkSecMouseClicked(evt);
                }
            });

            jLabel33.setForeground(new java.awt.Color(51, 51, 51));
            jLabel33.setText("Secundaria");

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addComponent(jLabel33)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChkSec, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel24.setForeground(new java.awt.Color(51, 51, 51));
            jLabel24.setText("___________________________________");

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(51, 51, 51));
            jLabel7.setText("Distrito");

            jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel10.setForeground(new java.awt.Color(51, 51, 51));
            jLabel10.setText("___________________________________");

            jPanel5.setBackground(new java.awt.Color(255, 255, 255));

            ChkAnalf.setEditable(false);
            ChkAnalf.setBackground(new java.awt.Color(255, 204, 51));
            ChkAnalf.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkAnalf.setForeground(new java.awt.Color(102, 102, 102));
            ChkAnalf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkAnalf.setBorder(null);
            ChkAnalf.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkAnalf.setSelectionColor(new java.awt.Color(255, 204, 51));
            ChkAnalf.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkAnalfCaretUpdate(evt);
                }
            });
            ChkAnalf.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkAnalfMouseClicked(evt);
                }
            });

            jLabel31.setForeground(new java.awt.Color(51, 51, 51));
            jLabel31.setText("Analfabeta");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel31)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(ChkAnalf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkAnalf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel15.setForeground(new java.awt.Color(51, 51, 51));
            jLabel15.setText("________________________");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(51, 51, 51));
            jLabel4.setText("Dirección");

            jPanel20.setBackground(new java.awt.Color(255, 255, 255));

            chkNoApl.setEditable(false);
            chkNoApl.setBackground(new java.awt.Color(204, 204, 204));
            chkNoApl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkNoApl.setForeground(new java.awt.Color(102, 102, 102));
            chkNoApl.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkNoApl.setBorder(null);
            chkNoApl.setPreferredSize(new java.awt.Dimension(28, 28));
            chkNoApl.setSelectionColor(new java.awt.Color(204, 204, 204));
            chkNoApl.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkNoAplCaretUpdate(evt);
                }
            });
            chkNoApl.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkNoAplMouseClicked(evt);
                }
            });

            jLabel46.setForeground(new java.awt.Color(51, 51, 51));
            jLabel46.setText("No Aplica");

            javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
            jPanel20.setLayout(jPanel20Layout);
            jPanel20Layout.setHorizontalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(chkNoApl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel20Layout.setVerticalGroup(
                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel20Layout.createSequentialGroup()
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkNoApl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
            jCheckBox3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jCheckBox3.setText("SIS");

            jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel22.setForeground(new java.awt.Color(51, 51, 51));
            jLabel22.setText("Tipo de Seguro");

            jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel44.setForeground(new java.awt.Color(51, 51, 51));
            jLabel44.setText("Padre:RN:");

            jPanel4.setBackground(new java.awt.Color(255, 255, 255));

            jLabel28.setForeground(new java.awt.Color(51, 51, 51));
            jLabel28.setText(">35");

            jLabel27.setForeground(new java.awt.Color(51, 51, 51));
            jLabel27.setText("<15");

            ChkEdad.setEditable(false);
            ChkEdad.setBackground(new java.awt.Color(255, 51, 51));
            ChkEdad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkEdad.setForeground(new java.awt.Color(255, 255, 255));
            ChkEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkEdad.setBorder(null);
            ChkEdad.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkEdad.setSelectionColor(new java.awt.Color(255, 51, 51));
            ChkEdad.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkEdadCaretUpdate(evt);
                }
            });
            ChkEdad.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkEdadMouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel28)
                        .addComponent(jLabel27))
                    .addGap(0, 46, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel27)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel28))
                        .addComponent(ChkEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel16.setBackground(new java.awt.Color(255, 255, 255));

            chkCasada.setEditable(false);
            chkCasada.setBackground(new java.awt.Color(204, 204, 204));
            chkCasada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkCasada.setForeground(new java.awt.Color(102, 102, 102));
            chkCasada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkCasada.setBorder(null);
            chkCasada.setPreferredSize(new java.awt.Dimension(28, 28));
            chkCasada.setSelectionColor(new java.awt.Color(204, 204, 204));
            chkCasada.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkCasadaCaretUpdate(evt);
                }
            });
            chkCasada.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkCasadaMouseClicked(evt);
                }
            });

            jLabel40.setForeground(new java.awt.Color(51, 51, 51));
            jLabel40.setText("Casada");

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                    .addComponent(jLabel40)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addComponent(chkCasada, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkCasada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel12.setForeground(new java.awt.Color(51, 51, 51));
            jLabel12.setText("________________________");

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(51, 51, 51));
            jLabel5.setText("Localidad");

            jPanel21.setBackground(new java.awt.Color(255, 255, 255));

            chkRef.setEditable(false);
            chkRef.setBackground(new java.awt.Color(204, 204, 204));
            chkRef.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkRef.setForeground(new java.awt.Color(102, 102, 102));
            chkRef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkRef.setBorder(null);
            chkRef.setPreferredSize(new java.awt.Dimension(28, 28));
            chkRef.setSelectionColor(new java.awt.Color(204, 204, 204));
            chkRef.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkRefCaretUpdate(evt);
                }
            });
            chkRef.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkRefMouseClicked(evt);
                }
            });

            jLabel47.setForeground(new java.awt.Color(51, 51, 51));
            jLabel47.setText("Referencia");

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(chkRef, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jPanel11.setBackground(new java.awt.Color(255, 255, 255));

            ChkSup.setEditable(false);
            ChkSup.setBackground(new java.awt.Color(204, 204, 204));
            ChkSup.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkSup.setForeground(new java.awt.Color(102, 102, 102));
            ChkSup.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkSup.setBorder(null);
            ChkSup.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkSup.setSelectionColor(new java.awt.Color(204, 204, 204));
            ChkSup.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkSupCaretUpdate(evt);
                }
            });
            ChkSup.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkSupMouseClicked(evt);
                }
            });

            jLabel34.setForeground(new java.awt.Color(51, 51, 51));
            jLabel34.setText("Superior");

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel34)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(ChkSup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel39.setForeground(new java.awt.Color(51, 51, 51));
            jLabel39.setText("Estado Civil");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(51, 51, 51));
            jLabel6.setText("Departamento");

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel17.setForeground(new java.awt.Color(51, 51, 51));
            jLabel17.setText("___________________________________");

            jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel13.setForeground(new java.awt.Color(51, 51, 51));
            jLabel13.setText("___________________________________");

            jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel9.setForeground(new java.awt.Color(51, 51, 51));
            jLabel9.setText("__________________________________________________________________________");

            jPanel19.setBackground(new java.awt.Color(255, 255, 255));

            chkOtro.setEditable(false);
            chkOtro.setBackground(new java.awt.Color(255, 204, 51));
            chkOtro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            chkOtro.setForeground(new java.awt.Color(102, 102, 102));
            chkOtro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            chkOtro.setBorder(null);
            chkOtro.setPreferredSize(new java.awt.Dimension(28, 28));
            chkOtro.setSelectionColor(new java.awt.Color(255, 204, 51));
            chkOtro.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    chkOtroCaretUpdate(evt);
                }
            });
            chkOtro.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    chkOtroMouseClicked(evt);
                }
            });

            jLabel43.setForeground(new java.awt.Color(51, 51, 51));
            jLabel43.setText("Otro");

            javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
            jPanel19.setLayout(jPanel19Layout);
            jPanel19Layout.setHorizontalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                    .addComponent(jLabel43)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                    .addComponent(chkOtro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );
            jPanel19Layout.setVerticalGroup(
                jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel19Layout.createSequentialGroup()
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkOtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel14.setForeground(new java.awt.Color(51, 51, 51));
            jLabel14.setText("Provincia");

            jPanel15.setBackground(new java.awt.Color(255, 255, 255));
            jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPaciente2.setEditable(false);
            txtPaciente2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtPaciente2.setForeground(new java.awt.Color(102, 102, 102));
            txtPaciente2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPaciente2.setBorder(null);
            txtPaciente2.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPaciente2CaretUpdate(evt);
                }
            });

            btnBuscarNino2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarNino2.setMnemonic('B');
            btnBuscarNino2.setToolTipText("");
            btnBuscarNino2.setBorderPainted(false);
            btnBuscarNino2.setContentAreaFilled(false);
            btnBuscarNino2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarNino2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarNino2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel13.setBackground(new java.awt.Color(255, 255, 255));

            ChkAnAp.setBackground(new java.awt.Color(204, 204, 204));
            ChkAnAp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkAnAp.setForeground(new java.awt.Color(102, 102, 102));
            ChkAnAp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkAnAp.setBorder(null);
            ChkAnAp.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkAnAp.setSelectionColor(new java.awt.Color(204, 204, 204));
            ChkAnAp.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkAnApCaretUpdate(evt);
                }
            });
            ChkAnAp.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkAnApMouseClicked(evt);
                }
            });

            jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            jLabel36.setForeground(new java.awt.Color(51, 51, 51));
            jLabel36.setText("Años Aprobados");

            javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
            jPanel13.setLayout(jPanel13Layout);
            jPanel13Layout.setHorizontalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel36)
                    .addGap(4, 4, 4)
                    .addComponent(ChkAnAp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel13Layout.setVerticalGroup(
                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkAnAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel16.setForeground(new java.awt.Color(51, 51, 51));
            jLabel16.setText("__________________________________________________________________________");

            jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel23.setForeground(new java.awt.Color(51, 51, 51));
            jLabel23.setText("Código Afiliacion Seguro");

            jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel29.setForeground(new java.awt.Color(51, 51, 51));
            jLabel29.setText("Estudios");

            jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(51, 51, 51));
            jLabel20.setText("Ocupación");

            jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel21.setForeground(new java.awt.Color(51, 51, 51));
            jLabel21.setText("Establecimiento");

            txtPadre1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtPadre1.setForeground(new java.awt.Color(102, 102, 102));
            txtPadre1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtPadre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPadre1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPadre1CaretUpdate(evt);
                }
            });

            jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel38.setForeground(new java.awt.Color(51, 51, 51));
            jLabel38.setText("Establecimiento Origen");

            txtPadre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtPadre.setForeground(new java.awt.Color(102, 102, 102));
            txtPadre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtPadre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPadre.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPadreCaretUpdate(evt);
                }
            });

            jPanel12.setBackground(new java.awt.Color(255, 255, 255));

            ChkSupnU.setEditable(false);
            ChkSupnU.setBackground(new java.awt.Color(204, 204, 204));
            ChkSupnU.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            ChkSupnU.setForeground(new java.awt.Color(102, 102, 102));
            ChkSupnU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            ChkSupnU.setBorder(null);
            ChkSupnU.setPreferredSize(new java.awt.Dimension(28, 28));
            ChkSupnU.setSelectionColor(new java.awt.Color(204, 204, 204));
            ChkSupnU.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    ChkSupnUCaretUpdate(evt);
                }
            });
            ChkSupnU.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ChkSupnUMouseClicked(evt);
                }
            });

            jLabel35.setForeground(new java.awt.Color(51, 51, 51));
            jLabel35.setText("Superior No Univ.");

            javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
            jPanel12.setLayout(jPanel12Layout);
            jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jLabel35)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChkSupnU, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChkSupnU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(51, 51, 51));
            jLabel26.setText("________________________");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(51, 51, 51));
            jLabel3.setText("Apellidos y Nombres");

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));
            jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPaciente1.setEditable(false);
            txtPaciente1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
            txtPaciente1.setForeground(new java.awt.Color(102, 102, 102));
            txtPaciente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPaciente1.setBorder(null);
            txtPaciente1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPaciente1CaretUpdate(evt);
                }
            });

            btnBuscarNino1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarNino1.setMnemonic('B');
            btnBuscarNino1.setToolTipText("");
            btnBuscarNino1.setBorderPainted(false);
            btnBuscarNino1.setContentAreaFilled(false);
            btnBuscarNino1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarNino1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarNino1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnBuscarNino1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnBuscarNino1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(txtPaciente1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            lblNhc.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
            lblNhc.setForeground(new java.awt.Color(51, 51, 51));
            lblNhc.setText("Nº. HC");

            lblHc.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
            lblHc.setForeground(new java.awt.Color(51, 51, 51));
            lblHc.setText("___________________");

            javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
            pnlControl.setLayout(pnlControlLayout);
            pnlControlLayout.setHorizontalGroup(
                pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlControlLayout.createSequentialGroup()
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addGap(23, 23, 23)
                                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addGap(166, 166, 166)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlControlLayout.createSequentialGroup()
                                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlControlLayout.createSequentialGroup()
                                                        .addComponent(jLabel10)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel11))
                                                    .addGroup(pnlControlLayout.createSequentialGroup()
                                                        .addComponent(jLabel13)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel14)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel12))))
                                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtPadre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(pnlControlLayout.createSequentialGroup()
                                            .addComponent(jCheckBox3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCheckBox5)
                                            .addGap(85, 85, 85)
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel23)
                                                .addComponent(jLabel26))
                                            .addGap(18, 18, 18)
                                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addComponent(jLabel8)
                        .addComponent(jLabel21)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel22)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel25))
                        .addComponent(jLabel20)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addGap(109, 109, 109)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel39))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPadreRN))
                                .addGroup(pnlControlLayout.createSequentialGroup()
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(38, 38, 38)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblNhc)
                            .addGap(18, 18, 18)
                            .addComponent(lblHc)))
                    .addContainerGap())
            );
            pnlControlLayout.setVerticalGroup(
                pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlControlLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNhc)
                                .addComponent(lblHc)))
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel16))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel17))
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(txtPadre1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlControlLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jCheckBox4)
                        .addComponent(jCheckBox5)
                        .addComponent(jCheckBox3)
                        .addComponent(jLabel23)
                        .addComponent(txtPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel39)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(txtPadreRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
            );

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlEs, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlAp, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 239, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(pnlAp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(213, 213, 213)
                            .addComponent(pnlEs, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );

            jTabbedPane1.addTab("Datos Generales", jPanel2);

            javax.swing.GroupLayout ContenedorLayout = new javax.swing.GroupLayout(Contenedor);
            Contenedor.setLayout(ContenedorLayout);
            ContenedorLayout.setHorizontalGroup(
                ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1528, Short.MAX_VALUE)
            );
            ContenedorLayout.setVerticalGroup(
                ContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 712, Short.MAX_VALUE)
            );

            jTabbedPane1.addTab("Registros", Contenedor);

            javax.swing.GroupLayout ContenedorTablasLayout = new javax.swing.GroupLayout(ContenedorTablas);
            ContenedorTablas.setLayout(ContenedorTablasLayout);
            ContenedorTablasLayout.setHorizontalGroup(
                ContenedorTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1528, Short.MAX_VALUE)
            );
            ContenedorTablasLayout.setVerticalGroup(
                ContenedorTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 712, Short.MAX_VALUE)
            );

            jTabbedPane1.addTab("Pestañas", ContenedorTablas);

            getContentPane().add(jTabbedPane1);
            jTabbedPane1.setBounds(0, 0, 1533, 740);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtPaciente1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaciente1CaretUpdate

    }//GEN-LAST:event_txtPaciente1CaretUpdate

    private void btnBuscarNino1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino1ActionPerformed
       
    }//GEN-LAST:event_btnBuscarNino1ActionPerformed

    private void txtPadreCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadreCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadreCaretUpdate

    private void txtPadre1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre1CaretUpdate

    private void ChkAnalfCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalfCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalfCaretUpdate

    private void ChkAnalfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalfMouseClicked
        if(ChkAnalf.getText().equals("") && evt.getClickCount()==1){
           ChkAnalf.setText("X");
        }else
        if(ChkAnalf.getText().equals("X") && evt.getClickCount()==1){
           ChkAnalf.setText(""); 
        }
    }//GEN-LAST:event_ChkAnalfMouseClicked

    private void ChkEdadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdadCaretUpdate

    private void ChkEdadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdadMouseClicked
        
        if(ChkEdad.getText().equals("") && evt.getClickCount()==1){
           ChkEdad.setText("X");
        }else
        if(ChkEdad.getText().equals("X") && evt.getClickCount()==1){
           ChkEdad.setText(""); 
        }
    }//GEN-LAST:event_ChkEdadMouseClicked

    private void ChkprimCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkprimCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkprimCaretUpdate

    private void ChkprimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkprimMouseClicked
        if(Chkprim.getText().equals("") && evt.getClickCount()==1){
           Chkprim.setText("X");
        }else
        if(Chkprim.getText().equals("X") && evt.getClickCount()==1){
           Chkprim.setText(""); 
        }
    }//GEN-LAST:event_ChkprimMouseClicked

    private void ChkSecCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSecCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSecCaretUpdate

    private void ChkSecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSecMouseClicked
        if(ChkSec.getText().equals("") && evt.getClickCount()==1){
           ChkSec.setText("X");
        }else
        if(ChkSec.getText().equals("X") && evt.getClickCount()==1){
           ChkSec.setText(""); 
        }
    }//GEN-LAST:event_ChkSecMouseClicked

    private void ChkSupCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSupCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSupCaretUpdate

    private void ChkSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSupMouseClicked
        if(ChkSup.getText().equals("") && evt.getClickCount()==1){
           ChkSup.setText("X");
        }else
        if(ChkSup.getText().equals("X") && evt.getClickCount()==1){
           ChkSup.setText(""); 
        }
    }//GEN-LAST:event_ChkSupMouseClicked

    private void ChkSupnUCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkSupnUCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSupnUCaretUpdate

    private void ChkSupnUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkSupnUMouseClicked
        if(ChkSupnU.getText().equals("") && evt.getClickCount()==1){
           ChkSupnU.setText("X");
        }else
        if(ChkSupnU.getText().equals("X") && evt.getClickCount()==1){
           ChkSupnU.setText(""); 
        }
    }//GEN-LAST:event_ChkSupnUMouseClicked

    private void ChkAnApCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnApCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnApCaretUpdate

    private void ChkAnApMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnApMouseClicked
        if(ChkAnAp.getText().equals("") && evt.getClickCount()==1){
           ChkAnAp.setText("X");
        }else
        if(ChkAnAp.getText().equals("X") && evt.getClickCount()==1){
           ChkAnAp.setText(""); 
        }
    }//GEN-LAST:event_ChkAnApMouseClicked

    private void ChkEdad1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1CaretUpdate

    private void ChkEdad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1MouseClicked

    private void ChkAnalf1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1CaretUpdate

    private void ChkAnalf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1MouseClicked

    private void txtPaciente2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaciente2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaciente2CaretUpdate

    private void btnBuscarNino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino2ActionPerformed

    private void chkCasadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCasadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCasadaCaretUpdate

    private void chkCasadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCasadaMouseClicked
        if(chkCasada.getText().equals("") && evt.getClickCount()==1){
           chkCasada.setText("X");
        }else
        if(chkCasada.getText().equals("X") && evt.getClickCount()==1){
           chkCasada.setText(""); 
        }
    }//GEN-LAST:event_chkCasadaMouseClicked

    private void chkConvivienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkConvivienteCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkConvivienteCaretUpdate

    private void chkConvivienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkConvivienteMouseClicked
        if(chkConviviente.getText().equals("") && evt.getClickCount()==1){
           chkConviviente.setText("X");
        }else
        if(chkConviviente.getText().equals("X") && evt.getClickCount()==1){
           chkConviviente.setText(""); 
        }
    }//GEN-LAST:event_chkConvivienteMouseClicked

    private void chkSolteraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkSolteraCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSolteraCaretUpdate

    private void chkSolteraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkSolteraMouseClicked
        if(chkSoltera.getText().equals("") && evt.getClickCount()==1){
           chkSoltera.setText("X");
        }else
        if(chkSoltera.getText().equals("X") && evt.getClickCount()==1){
           chkSoltera.setText(""); 
        }
    }//GEN-LAST:event_chkSolteraMouseClicked

    private void chkOtroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkOtroCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkOtroCaretUpdate

    private void chkOtroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkOtroMouseClicked
        if(chkOtro.getText().equals("") && evt.getClickCount()==1){
           chkOtro.setText("X");
        }else
        if(chkOtro.getText().equals("X") && evt.getClickCount()==1){
           chkOtro.setText(""); 
        }
    }//GEN-LAST:event_chkOtroMouseClicked

    private void chkNoAplCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkNoAplCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoAplCaretUpdate

    private void chkNoAplMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNoAplMouseClicked
        if(chkNoApl.getText().equals("") && evt.getClickCount()==1){
           chkNoApl.setText("X");
        }else
        if(chkNoApl.getText().equals("X") && evt.getClickCount()==1){
           chkNoApl.setText(""); 
        }
    }//GEN-LAST:event_chkNoAplMouseClicked

    private void chkRefCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkRefCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRefCaretUpdate

    private void chkRefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRefMouseClicked
        if(chkRef.getText().equals("") && evt.getClickCount()==1){
           chkRef.setText("X");
        }else
        if(chkRef.getText().equals("X") && evt.getClickCount()==1){
           chkRef.setText(""); 
        }
    }//GEN-LAST:event_chkRefMouseClicked

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed
         RegistroEmbarazoPrincipal GA =new RegistroEmbarazoPrincipal();
        Contenedor.add(GA);
        try {
            GA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCaccnelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelar1ActionPerformed
        BuscarMadres.setVisible(true);
        BuscarMadres.setLocationRelativeTo(null);//en el centro
        BuscarMadres.setResizable(false);
        BuscarMadres.getContentPane().setBackground(Color.WHITE);
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscar.getText(), "Q", tbMadres);
//        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnCaccnelar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        ConsultorioExtEsnitss consultorio1 = new ConsultorioExtEsnitss();
        consultorio1.consultorioExListarC(txtBuscar.getText(), "Q", tbMadres);
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbMadres.getSelectionModel().setSelectionInterval(0, 0);
            tbMadres.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void tbMadresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMouseClicked
//        if(evt.getClickCount()==2){
//            enviarDatosMadres();
//        }
    }//GEN-LAST:event_tbMadresMouseClicked

    private void tbMadresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMadresMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbMadresMousePressed

    private void tbMadresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMadresKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbMadres.getSelectedRow()==0){
            txtBuscar.requestFocus();
            tbMadres.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
//        if(teclaPresionada==KeyEvent.VK_ENTER){
//            enviarDatosMadres();
//        }
    }//GEN-LAST:event_tbMadresKeyPressed

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
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroEmbarazo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroEmbarazo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarMadres;
    public static javax.swing.JTextField ChkAnAp;
    public static javax.swing.JTextField ChkAnalf;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad;
    public static javax.swing.JTextField ChkEdad1;
    public static javax.swing.JTextField ChkSec;
    public static javax.swing.JTextField ChkSup;
    public static javax.swing.JTextField ChkSupnU;
    public static javax.swing.JTextField Chkprim;
    private javax.swing.JDesktopPane Contenedor;
    public static javax.swing.JDesktopPane ContenedorTablas;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscarNino1;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnCaccnelar;
    private javax.swing.JButton btnCaccnelar1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnbuscar;
    public static javax.swing.JTextField chkCasada;
    public static javax.swing.JTextField chkConviviente;
    public static javax.swing.JTextField chkNoApl;
    public static javax.swing.JTextField chkOtro;
    public static javax.swing.JTextField chkRef;
    public static javax.swing.JTextField chkSoltera;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblNhc;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlAp;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlEs;
    private javax.swing.JTable tbMadres;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtPaciente1;
    public static javax.swing.JTextField txtPaciente2;
    public static javax.swing.JTextField txtPadre;
    public static javax.swing.JTextField txtPadre1;
    private javax.swing.JTextField txtPadreRN;
    // End of variables declaration//GEN-END:variables
}
