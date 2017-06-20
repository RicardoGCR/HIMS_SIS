/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import modelos.cuentaPorPagar.CuentasPorPagarFacturasCabecera;
import modelos.cuentaPorPagar.CuentasPorPagarVentasConsolidadoCabecera;

/**
 *
 * @author MYS1
 */
public class VentasConsolidado extends javax.swing.JFrame {
DefaultTableModel m;
    /**
     * Creates new form Caja_Consolidacion
     */
    public VentasConsolidado() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        txtDni.requestFocus();
        pnlVentas.setVisible(false);
        cbxActoMedico.setBackground(Color.white);
        cbxActoMedico.setBackground(Color.white);
    }
    
    public void buscarVentas(){
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        if (!txtDni.getText().equals("")){
            cabecera1.ventasConsolidadoCabecera(tbCabecera);
            if(tbCabecera.getRowCount()!=0){
                pnlVentas.setVisible(true);
                int fila = tbCabecera.getSelectedRow();
                tbCabecera.getSelectionModel().setSelectionInterval (0,0) ;
            } else {
                pnlVentas.setVisible(false);
            }
        }
        if (txtDni.getText().length()==0){
            pnlVentas.setVisible(false);
        }     
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane9 = new javax.swing.JScrollPane();
        tb_Grupo8 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            tb_Grupo9 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                lblusu = new javax.swing.JLabel();
                btnRefrescar = new javax.swing.JButton();
                jPanel9 = new javax.swing.JPanel();
                txtDni = new javax.swing.JTextField();
                T3 = new javax.swing.JButton();
                lbldetalle = new javax.swing.JLabel();
                cbxActoMedico = new javax.swing.JComboBox();
                jPanel2 = new javax.swing.JPanel();
                lblApellidos = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                lblActoMedico = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                lblHC = new javax.swing.JLabel();
                pnlVentas = new javax.swing.JPanel();
                spDetalle = new javax.swing.JScrollPane();
                pnlDetalle = new javax.swing.JPanel();
                spProcedimientos = new javax.swing.JScrollPane();
                tbProcedimientos = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    spLaboratorio = new javax.swing.JScrollPane();
                    tbLaboratorio = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        spRayos = new javax.swing.JScrollPane();
                        tbRayos = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            spEcografias = new javax.swing.JScrollPane();
                            tbEcografias = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};
                                spFarmacia = new javax.swing.JScrollPane();
                                tbFarmacia = new javax.swing.JTable(){
                                    public boolean isCellEditable(int rowIndex, int colIndex){
                                        return false; //Disallow the editing of any cell
                                    }};
                                    lblProcedimientos = new javax.swing.JLabel();
                                    lblLaboratorio = new javax.swing.JLabel();
                                    lblRayos = new javax.swing.JLabel();
                                    lblEcografias = new javax.swing.JLabel();
                                    lblFarmacia = new javax.swing.JLabel();
                                    spCabecera = new javax.swing.JScrollPane();
                                    tbCabecera = new javax.swing.JTable(){
                                        public boolean isCellEditable(int rowIndex, int colIndex){
                                            return false; //Disallow the editing of any cell
                                        }};

                                        jScrollPane9.setBorder(null);

                                        tb_Grupo8.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Grupo8.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Grupo8.setRowHeight(25);
                                        tb_Grupo8.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tb_Grupo8.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tb_Grupo8MouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tb_Grupo8MousePressed(evt);
                                            }
                                        });
                                        tb_Grupo8.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tb_Grupo8KeyPressed(evt);
                                            }
                                        });
                                        jScrollPane9.setViewportView(tb_Grupo8);

                                        tb_Grupo9.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tb_Grupo9.setGridColor(new java.awt.Color(255, 255, 255));
                                        tb_Grupo9.setRowHeight(25);
                                        tb_Grupo9.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tb_Grupo9.getTableHeader().setReorderingAllowed(false);

                                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                                        jPanel1.setBackground(new java.awt.Color(41, 127, 184));

                                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                                        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                                        jLabel1.setText("<html>Consolidado<span style=\"font-size:'15px'\"><br> Cuenta Corriente</br></span></html>");

                                        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                                        lblusu.setForeground(new java.awt.Color(255, 255, 255));
                                        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                                        lblusu.setText("Silvana");
                                        lblusu.setFocusable(false);
                                        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                                        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                                        btnRefrescar.setForeground(new java.awt.Color(240, 240, 240));
                                        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Actualizar-32 (1).png"))); // NOI18N
                                        btnRefrescar.setText("Refrescar");
                                        btnRefrescar.setContentAreaFilled(false);
                                        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnRefrescar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                                        btnRefrescar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                                        btnRefrescar.setIconTextGap(30);
                                        btnRefrescar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                                        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnRefrescarActionPerformed(evt);
                                            }
                                        });

                                        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                                        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        txtDni.setForeground(new java.awt.Color(51, 51, 51));
                                        txtDni.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                        txtDni.setBorder(null);
                                        txtDni.addCaretListener(new javax.swing.event.CaretListener() {
                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                txtDniCaretUpdate(evt);
                                            }
                                        });

                                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                        jPanel9.setLayout(jPanel9Layout);
                                        jPanel9Layout.setHorizontalGroup(
                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel9Layout.setVerticalGroup(
                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                                        T3.setToolTipText("");
                                        T3.setContentAreaFilled(false);
                                        T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        T3.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                T3ActionPerformed(evt);
                                            }
                                        });

                                        lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                        lbldetalle.setText("Acto Médico, DNI y Apellidos");

                                        cbxActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        cbxActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                        cbxActoMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

                                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                        jPanel1.setLayout(jPanel1Layout);
                                        jPanel1Layout.setHorizontalGroup(
                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(237, 237, 237)
                                                                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(24, 24, 24)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lbldetalle)
                                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(cbxActoMedico, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                        .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel1Layout.setVerticalGroup(
                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbldetalle)
                                                .addGap(80, 80, 80)
                                                .addComponent(cbxActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80)
                                                .addComponent(btnRefrescar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lblusu))
                                        );

                                        jPanel2.setBackground(new java.awt.Color(43, 43, 43));

                                        lblApellidos.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                                        lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
                                        lblApellidos.setText("Nombres y Apellidos");

                                        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                                        jLabel3.setText("Acto Médico");

                                        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
                                        lblActoMedico.setText("  ");

                                        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                                        jLabel5.setText("DNI");

                                        lblDNI.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        lblDNI.setForeground(new java.awt.Color(204, 204, 204));
                                        lblDNI.setText("  ");

                                        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                                        jLabel4.setText("Nº H.C.");

                                        lblHC.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        lblHC.setForeground(new java.awt.Color(204, 204, 204));
                                        lblHC.setText("  ");

                                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                                        jPanel2.setLayout(jPanel2Layout);
                                        jPanel2Layout.setHorizontalGroup(
                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(jLabel5)
                                                            .addComponent(jLabel4))
                                                        .addGap(67, 67, 67)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lblHC)
                                                            .addComponent(lblDNI)
                                                            .addComponent(lblActoMedico)))
                                                    .addComponent(lblApellidos))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );
                                        jPanel2Layout.setVerticalGroup(
                                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(lblApellidos)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel3)
                                                    .addComponent(lblActoMedico))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel5)
                                                    .addComponent(lblDNI))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblHC)
                                                    .addComponent(jLabel4))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        spDetalle.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                        spProcedimientos.setBorder(null);

                                        tbProcedimientos.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbProcedimientos.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbProcedimientos.setRowHeight(25);
                                        tbProcedimientos.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tbProcedimientos.getTableHeader().setReorderingAllowed(false);
                                        tbProcedimientos.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbProcedimientosMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbProcedimientosMousePressed(evt);
                                            }
                                        });
                                        tbProcedimientos.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbProcedimientosKeyPressed(evt);
                                            }
                                        });
                                        spProcedimientos.setViewportView(tbProcedimientos);

                                        spLaboratorio.setBorder(null);

                                        tbLaboratorio.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbLaboratorio.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbLaboratorio.setRowHeight(25);
                                        tbLaboratorio.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tbLaboratorio.getTableHeader().setReorderingAllowed(false);
                                        tbLaboratorio.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbLaboratorioMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbLaboratorioMousePressed(evt);
                                            }
                                        });
                                        tbLaboratorio.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbLaboratorioKeyPressed(evt);
                                            }
                                        });
                                        spLaboratorio.setViewportView(tbLaboratorio);

                                        spRayos.setBorder(null);

                                        tbRayos.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbRayos.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbRayos.setRowHeight(25);
                                        tbRayos.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tbRayos.getTableHeader().setReorderingAllowed(false);
                                        tbRayos.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbRayosMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbRayosMousePressed(evt);
                                            }
                                        });
                                        tbRayos.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbRayosKeyPressed(evt);
                                            }
                                        });
                                        spRayos.setViewportView(tbRayos);

                                        spEcografias.setBorder(null);

                                        tbEcografias.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbEcografias.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbEcografias.setRowHeight(25);
                                        tbEcografias.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tbEcografias.getTableHeader().setReorderingAllowed(false);
                                        tbEcografias.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbEcografiasMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbEcografiasMousePressed(evt);
                                            }
                                        });
                                        tbEcografias.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbEcografiasKeyPressed(evt);
                                            }
                                        });
                                        spEcografias.setViewportView(tbEcografias);

                                        spFarmacia.setBorder(null);

                                        tbFarmacia.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbFarmacia.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbFarmacia.setRowHeight(25);
                                        tbFarmacia.setSelectionBackground(new java.awt.Color(50, 151, 219));
                                        tbFarmacia.getTableHeader().setReorderingAllowed(false);
                                        tbFarmacia.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbFarmaciaMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbFarmaciaMousePressed(evt);
                                            }
                                        });
                                        tbFarmacia.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbFarmaciaKeyPressed(evt);
                                            }
                                        });
                                        spFarmacia.setViewportView(tbFarmacia);

                                        lblProcedimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/proc.png"))); // NOI18N

                                        lblLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/lab.png"))); // NOI18N

                                        lblRayos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/ray.png"))); // NOI18N

                                        lblEcografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eco.png"))); // NOI18N

                                        lblFarmacia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/farm.png"))); // NOI18N

                                        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
                                        pnlDetalle.setLayout(pnlDetalleLayout);
                                        pnlDetalleLayout.setHorizontalGroup(
                                            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDetalleLayout.createSequentialGroup()
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblEcografias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(lblRayos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblLaboratorio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lblProcedimientos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addComponent(lblFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spProcedimientos, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                                                    .addComponent(spLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(spRayos, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(spEcografias)
                                                    .addComponent(spFarmacia))
                                                .addGap(0, 0, 0))
                                        );
                                        pnlDetalleLayout.setVerticalGroup(
                                            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDetalleLayout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(spRayos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(lblRayos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblEcografias, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spEcografias, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        );

                                        spDetalle.setViewportView(pnlDetalle);

                                        spCabecera.setBorder(null);

                                        tbCabecera.setModel(new javax.swing.table.DefaultTableModel(
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
                                        tbCabecera.setGridColor(new java.awt.Color(255, 255, 255));
                                        tbCabecera.setRowHeight(25);
                                        tbCabecera.setSelectionBackground(new java.awt.Color(41, 127, 184));
                                        tbCabecera.getTableHeader().setReorderingAllowed(false);
                                        tbCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                                tbCabeceraMouseClicked(evt);
                                            }
                                            public void mousePressed(java.awt.event.MouseEvent evt) {
                                                tbCabeceraMousePressed(evt);
                                            }
                                        });
                                        tbCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                tbCabeceraKeyPressed(evt);
                                            }
                                        });
                                        spCabecera.setViewportView(tbCabecera);

                                        javax.swing.GroupLayout pnlVentasLayout = new javax.swing.GroupLayout(pnlVentas);
                                        pnlVentas.setLayout(pnlVentasLayout);
                                        pnlVentasLayout.setHorizontalGroup(
                                            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spCabecera)
                                                    .addComponent(spDetalle))
                                                .addGap(0, 0, 0))
                                        );
                                        pnlVentasLayout.setVerticalGroup(
                                            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                                .addGap(0, 0, 0)
                                                .addComponent(spCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(spDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                                                .addContainerGap())
                                        );

                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                        getContentPane().setLayout(layout);
                                        layout.setHorizontalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        );
                                        layout.setVerticalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        pack();
                                    }// </editor-fold>//GEN-END:initComponents

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed

    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void txtDniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniCaretUpdate
        buscarVentas();
    }//GEN-LAST:event_txtDniCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void tbCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseClicked
//        int fila = tbCabecera.getSelectedRow();
//        lblActoMedico.setText(String.valueOf(tbCabecera.getValueAt(fila, 13)));
//        lblApellidos.setText(String.valueOf(tbCabecera.getValueAt(fila, 5)));
//        lblDNI.setText(String.valueOf(tbCabecera.getValueAt(fila, 3)));
//        lblHC.setText(String.valueOf(tbCabecera.getValueAt(fila, 4)));
//        bus1.setText(String.valueOf(tbCabecera.getValueAt(fila, 14)));
//        
//        BusquedaDet();
//        BusquedaDetLA();
//        BusquedaDetRX();
//        spProcedimientos.setVisible(true);
//        spLaboratorio.setVisible(true);
//        spRayos.setVisible(true);
//        lblProcedimientos.setVisible(true);
//        lblLaboratorio.setVisible(true);
//        lblRayos.setVisible(true);
//        sumaAbono();
        
        /////////ACTUALIZAR RX
        
    }//GEN-LAST:event_tbCabeceraMouseClicked

    private void tbCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMousePressed

    }//GEN-LAST:event_tbCabeceraMousePressed

    private void tbCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyPressed

    }//GEN-LAST:event_tbCabeceraKeyPressed

    private void tbProcedimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimientosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosMouseClicked

    private void tbProcedimientosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimientosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosMousePressed

    private void tbProcedimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedimientosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosKeyPressed

    private void tbLaboratorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLaboratorioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLaboratorioMouseClicked

    private void tbLaboratorioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLaboratorioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLaboratorioMousePressed

    private void tbLaboratorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLaboratorioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLaboratorioKeyPressed

    private void tb_Grupo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MouseClicked

    private void tb_Grupo8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MousePressed

    private void tb_Grupo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8KeyPressed

    private void tbRayosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRayosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRayosMouseClicked

    private void tbRayosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRayosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRayosMousePressed

    private void tbRayosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRayosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRayosKeyPressed

    private void tbEcografiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEcografiasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEcografiasMouseClicked

    private void tbEcografiasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEcografiasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEcografiasMousePressed

    private void tbEcografiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEcografiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEcografiasKeyPressed

    private void tbFarmaciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmaciaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFarmaciaMouseClicked

    private void tbFarmaciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmaciaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFarmaciaMousePressed

    private void tbFarmaciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFarmaciaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFarmaciaKeyPressed

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
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasConsolidado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentasConsolidado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton T3;
    public static javax.swing.JButton btnRefrescar;
    public static javax.swing.JComboBox cbxActoMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEcografias;
    private javax.swing.JLabel lblFarmacia;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblLaboratorio;
    private javax.swing.JLabel lblProcedimientos;
    private javax.swing.JLabel lblRayos;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlVentas;
    private javax.swing.JScrollPane spCabecera;
    private javax.swing.JScrollPane spDetalle;
    private javax.swing.JScrollPane spEcografias;
    private javax.swing.JScrollPane spFarmacia;
    private javax.swing.JScrollPane spLaboratorio;
    private javax.swing.JScrollPane spProcedimientos;
    private javax.swing.JScrollPane spRayos;
    private javax.swing.JTable tbCabecera;
    private javax.swing.JTable tbEcografias;
    private javax.swing.JTable tbFarmacia;
    private javax.swing.JTable tbLaboratorio;
    private javax.swing.JTable tbProcedimientos;
    private javax.swing.JTable tbRayos;
    private javax.swing.JTable tb_Grupo8;
    private javax.swing.JTable tb_Grupo9;
    public static javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
