/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import campos.LimitadorDeDocumento;
import com.sun.corba.se.pept.transport.Connection;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import modelos.cuentaPorPagar.CuentasPorPagarFacturasCabecera;
import modelos.cuentaPorPagar.CuentasPorPagarVentasConsolidadoCabecera;
import servicios.Conexion;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtaMotivo;
/**
 *
 * @author MYS1
 */
public class VentasConsolidado extends javax.swing.JFrame {
    DefaultTableModel m;
    Conexion cnn = new Conexion();
    java.sql.Connection conexion=null;
    Conexion c=new Conexion();
    public static boolean Facturado= false;
    public VentasConsolidado() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
<<<<<<< HEAD
        
       if(tbCabecera.getRowCount()==0){
                habilitarDatos(false);
                
            }
    }
    
    public void habilitarDatos(boolean opcion){
        jScrollPane1.setVisible(opcion);
        spCabecera.setVisible(opcion);
=======
        txtDni.requestFocus();
        pnlVentas.setVisible(false);
        cbxActoMedico.setBackground(Color.white);
        cbxActoMedico.setBackground(Color.white);
        conexion = c.conectar();
        LimitadorDeDocumento limitDNI = new LimitadorDeDocumento(8);
        txtDni.setDocument(limitDNI);
        cbxActoMedico.setVisible(false);
        lblMensajeActoMedico.setVisible(false);
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
>>>>>>> 8ab130461fee646e9653f9ee3f176d97ca8775a8
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void buscarVentas(){
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        if (!txtDni.getText().equals("")){
            cabecera1.ventasConsolidadoCabecera(tbCabecera,txtActoMedico.getText());
            if(tbCabecera.getRowCount()!=0){
                pnlVentas.setVisible(true);
                int fila = tbCabecera.getSelectedRow();
                tbCabecera.getSelectionModel().setSelectionInterval (0,0) ;
                tbCabecera.requestFocus();
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(0, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(0, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(0, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(0, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(0, 14)),"RX");
                lblIdCabecera.setText(String.valueOf(tbCabecera.getValueAt(0, 14)));
                btnFacturarEcografias.setEnabled(false);
                btnFacturarFarmacia.setEnabled(false);
                btnFacturarLaboratorio.setEnabled(false);
                btnFacturarProcedimientos.setEnabled(false);
                btnFacturarRayos.setEnabled(false);
            } else {
                pnlVentas.setVisible(false);
            }
        }
        if (txtDni.getText().length()==0){
            pnlVentas.setVisible(false);
        }     
    }
    
    public void listarActoMedico(String dni){
        try {
            Statement sta=conexion.createStatement();
            ResultSet rs=sta.executeQuery("EXEC CUENTAS_POR_PAGAR_LISTAR_ACTO_MEDICO '"+dni+"'");
            VentasConsolidado.cbxActoMedico.removeAllItems();
            while(rs.next()){
                VentasConsolidado.cbxActoMedico.addItem(rs.getInt("NUM_ACTOMEDICO"));
            }
        } catch (SQLException e) {
                System.out.println("Error: listarActoMedico:" + e.getMessage());
        }
        }
    
        public void actualizarEstadoFacturacion(JTable tabla){
            CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
            int fila = tabla.getSelectedRow();
            if(cabecera1.actualizarEstadoFacturacion(String.valueOf(tabla.getValueAt(fila,11)))){
               cabecera1.listarPorFacturar(Facturador.tbFacturacion,txtDni.getText());
               cabecera1.ventasConsolidadoDetalles(tbProcedimientos,lblIdCabecera.getText(),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,lblIdCabecera.getText(),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,lblIdCabecera.getText(),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,lblIdCabecera.getText(),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,lblIdCabecera.getText(),"RX");
                cabecera1.calcularPrecioVenta(txtDni.getText());
            } else {
               JOptionPane.showMessageDialog(null,"No se puedo generar esta factura");
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
                jPanel9 = new javax.swing.JPanel();
                txtDni = new javax.swing.JTextField();
                T3 = new javax.swing.JButton();
                lbldetalle = new javax.swing.JLabel();
                cbxActoMedico = new javax.swing.JComboBox();
                lblMensajeActoMedico = new javax.swing.JLabel();
                pnlDatos = new javax.swing.JPanel();
                lblApellidos = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                lblDNI = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                lblHC = new javax.swing.JLabel();
                txtActoMedico = new javax.swing.JTextField();
                lblIdCabecera = new javax.swing.JLabel();
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
                                    btnFacturarLaboratorio = new javax.swing.JButton();
                                    btnFacturarProcedimientos = new javax.swing.JButton();
                                    btnFacturarRayos = new javax.swing.JButton();
                                    btnFacturarEcografias = new javax.swing.JButton();
                                    btnFacturarFarmacia = new javax.swing.JButton();
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

                                        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

                                        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                                        txtDni.setForeground(new java.awt.Color(51, 51, 51));
                                        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                        txtDni.setBorder(null);
                                        txtDni.addCaretListener(new javax.swing.event.CaretListener() {
                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                txtDniCaretUpdate(evt);
                                            }
                                        });
                                        txtDni.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                txtDniActionPerformed(evt);
                                            }
                                        });
                                        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                txtDniKeyPressed(evt);
                                            }
                                        });

                                        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                                        jPanel9.setLayout(jPanel9Layout);
                                        jPanel9Layout.setHorizontalGroup(
                                            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
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

                                        lbldetalle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                                        lbldetalle.setText("Ingrese el DNI del paciente");

                                        cbxActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        cbxActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                        cbxActoMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acto Médico" }));
                                        cbxActoMedico.addItemListener(new java.awt.event.ItemListener() {
                                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                                cbxActoMedicoItemStateChanged(evt);
                                            }
                                        });

                                        lblMensajeActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        lblMensajeActoMedico.setForeground(new java.awt.Color(255, 255, 255));
                                        lblMensajeActoMedico.setText("Seleccione el Acto Médico");

                                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                                        jPanel1.setLayout(jPanel1Layout);
                                        jPanel1Layout.setHorizontalGroup(
                                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                                .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(lblMensajeActoMedico))))
                                                .addContainerGap(19, Short.MAX_VALUE))
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
                                                .addGap(58, 58, 58)
                                                .addComponent(lblMensajeActoMedico)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbxActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        pnlDatos.setBackground(new java.awt.Color(43, 43, 43));

                                        lblApellidos.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
                                        lblApellidos.setForeground(new java.awt.Color(255, 255, 255));
                                        lblApellidos.setText("Búsqueda por DNI");

                                        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                                        jLabel3.setText("Acto Médico");

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

                                        txtActoMedico.setEditable(false);
                                        txtActoMedico.setBackground(new java.awt.Color(43, 43, 43));
                                        txtActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                        txtActoMedico.setForeground(new java.awt.Color(51, 51, 51));
                                        txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                                        txtActoMedico.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                        txtActoMedico.addCaretListener(new javax.swing.event.CaretListener() {
                                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                                txtActoMedicoCaretUpdate(evt);
                                            }
                                        });
                                        txtActoMedico.addKeyListener(new java.awt.event.KeyAdapter() {
                                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                                txtActoMedicoKeyPressed(evt);
                                            }
                                        });

                                        lblIdCabecera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        lblIdCabecera.setForeground(new java.awt.Color(255, 255, 255));
                                        lblIdCabecera.setText("DOC CABECERA");

                                        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
                                        pnlDatos.setLayout(pnlDatosLayout);
                                        pnlDatosLayout.setHorizontalGroup(
                                            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlDatosLayout.createSequentialGroup()
                                                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jLabel5)
                                                                    .addComponent(jLabel4))
                                                                .addGap(105, 105, 105)
                                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(lblDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                                                    .addComponent(lblHC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(77, 77, 77)
                                                                .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addContainerGap(451, Short.MAX_VALUE))
                                                    .addGroup(pnlDatosLayout.createSequentialGroup()
                                                        .addComponent(lblApellidos)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblIdCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(32, 32, 32))))
                                        );
                                        pnlDatosLayout.setVerticalGroup(
                                            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDatosLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblApellidos)
                                                    .addComponent(lblIdCabecera))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtActoMedico)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel5)
                                                    .addComponent(lblDNI))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lblHC)
                                                    .addComponent(jLabel4))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

<<<<<<< HEAD
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
                                        if (tbCabecera.getColumnModel().getColumnCount() > 0) {
                                            tbCabecera.getColumnModel().getColumn(0).setHeaderValue("Title 1");
                                            tbCabecera.getColumnModel().getColumn(1).setHeaderValue("Title 2");
                                            tbCabecera.getColumnModel().getColumn(2).setResizable(false);
                                            tbCabecera.getColumnModel().getColumn(2).setHeaderValue("Title 3");
                                            tbCabecera.getColumnModel().getColumn(3).setResizable(false);
                                            tbCabecera.getColumnModel().getColumn(3).setHeaderValue("Title 4");
                                        }

                                        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
=======
                                        spDetalle.setBorder(javax.swing.BorderFactory.createCompoundBorder());
>>>>>>> 8ab130461fee646e9653f9ee3f176d97ca8775a8

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

                                        btnFacturarLaboratorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        btnFacturarLaboratorio.setForeground(new java.awt.Color(51, 51, 51));
                                        btnFacturarLaboratorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                        btnFacturarLaboratorio.setText("<html>Agregar a <br>Factura</html>");
                                        btnFacturarLaboratorio.setContentAreaFilled(false);
                                        btnFacturarLaboratorio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnFacturarLaboratorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnFacturarLaboratorio.setIconTextGap(15);
                                        btnFacturarLaboratorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                        btnFacturarLaboratorio.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnFacturarLaboratorioActionPerformed(evt);
                                            }
                                        });

                                        btnFacturarProcedimientos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        btnFacturarProcedimientos.setForeground(new java.awt.Color(51, 51, 51));
                                        btnFacturarProcedimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                        btnFacturarProcedimientos.setText("<html>Agregar a <br>Factura</html>");
                                        btnFacturarProcedimientos.setContentAreaFilled(false);
                                        btnFacturarProcedimientos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnFacturarProcedimientos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnFacturarProcedimientos.setIconTextGap(15);
                                        btnFacturarProcedimientos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                        btnFacturarProcedimientos.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnFacturarProcedimientosActionPerformed(evt);
                                            }
                                        });

                                        btnFacturarRayos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        btnFacturarRayos.setForeground(new java.awt.Color(51, 51, 51));
                                        btnFacturarRayos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                        btnFacturarRayos.setText("<html>Agregar a <br>Factura</html>");
                                        btnFacturarRayos.setContentAreaFilled(false);
                                        btnFacturarRayos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnFacturarRayos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnFacturarRayos.setIconTextGap(15);
                                        btnFacturarRayos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                        btnFacturarRayos.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnFacturarRayosActionPerformed(evt);
                                            }
                                        });

                                        btnFacturarEcografias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        btnFacturarEcografias.setForeground(new java.awt.Color(51, 51, 51));
                                        btnFacturarEcografias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                        btnFacturarEcografias.setText("<html>Agregar a <br>Factura</html>");
                                        btnFacturarEcografias.setContentAreaFilled(false);
                                        btnFacturarEcografias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnFacturarEcografias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnFacturarEcografias.setIconTextGap(15);
                                        btnFacturarEcografias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                        btnFacturarEcografias.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnFacturarEcografiasActionPerformed(evt);
                                            }
                                        });

                                        btnFacturarFarmacia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                                        btnFacturarFarmacia.setForeground(new java.awt.Color(51, 51, 51));
                                        btnFacturarFarmacia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casilla de verificación marcada 2-32.png"))); // NOI18N
                                        btnFacturarFarmacia.setText("<html>Agregar a <br>Factura</html>");
                                        btnFacturarFarmacia.setContentAreaFilled(false);
                                        btnFacturarFarmacia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                                        btnFacturarFarmacia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                                        btnFacturarFarmacia.setIconTextGap(15);
                                        btnFacturarFarmacia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                                        btnFacturarFarmacia.addActionListener(new java.awt.event.ActionListener() {
                                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                                btnFacturarFarmaciaActionPerformed(evt);
                                            }
                                        });

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
                                                    .addComponent(spLaboratorio, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                                                    .addComponent(spProcedimientos)
                                                    .addComponent(spRayos)
                                                    .addComponent(spEcografias)
                                                    .addComponent(spFarmacia))
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnFacturarLaboratorio)
                                                        .addComponent(btnFacturarRayos, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnFacturarEcografias)
                                                        .addComponent(btnFacturarProcedimientos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(btnFacturarFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0))
                                        );
                                        pnlDetalleLayout.setVerticalGroup(
                                            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDetalleLayout.createSequentialGroup()
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnFacturarProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spProcedimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spLaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(btnFacturarLaboratorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(spRayos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(lblRayos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnFacturarRayos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblEcografias, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(btnFacturarEcografias, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spEcografias, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(spFarmacia, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                    .addComponent(btnFacturarFarmacia))
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
                                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                                tbCabeceraKeyReleased(evt);
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
                                                    .addComponent(spDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                                                    .addComponent(spCabecera)))
                                        );
                                        pnlVentasLayout.setVerticalGroup(
                                            pnlVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlVentasLayout.createSequentialGroup()
                                                .addComponent(spCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(spDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                                                .addGap(0, 0, 0))
                                        );

                                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                                        getContentPane().setLayout(layout);
                                        layout.setHorizontalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        );
                                        layout.setVerticalGroup(
                                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(pnlVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        );

                                        pack();
                                    }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
     
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txPacienteCaretUpdate
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        if (!txPaciente.getText().equals("")){
//            habilitarDatos(true);
            cabecera1.ventasConsolidadoCabecera(tbCabecera);
//            
            
//            jScrollPane7.setVisible(true);
//            jScrollPane8.setVisible(true);
//            jScrollPane10.setVisible(true);
            lblProcedimientos.setVisible(true);
            lblLaboratorio.setVisible(true);
            lblRayos.setVisible(true);

            int fila = tbCabecera.getSelectedRow();
            tbCabecera.getSelectionModel().setSelectionInterval (0,0) ;
   
//            lblActoMedico.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
//            lblApellidos.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
//            lblDNI.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
//            lblHC.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
//            
//            bus1.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 14)));
//            BusquedaDet();
            ////////////////////////////////////////
            if(tbCabecera.getRowCount()==0){
                habilitarDatos(false);
                
            }else if(tbCabecera.getRowCount()>0){
             habilitarDatos(true);

//            spFarmacia.setVisible(true);
//            lblProcedimientos.setVisible(true);
//            lblLaboratorio.setVisible(true);
//            lblRayos.setVisible(true);
                
            }

        }if (txPaciente.getText().length()==0){
            habilitarDatos(false);
            spFarmacia.setVisible(false);
            lblProcedimientos.setVisible(false);
            lblLaboratorio.setVisible(false);
            lblRayos.setVisible(false);
        }      
    }//GEN-LAST:event_txPacienteCaretUpdate
=======
    private void txtDniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniCaretUpdate
        listarActoMedico(txtDni.getText());
        if(cbxActoMedico.getItemCount()!=0){
            cbxActoMedico.setVisible(true);
            lblApellidos.setText("Nombres y Apellidos");
            lblMensajeActoMedico.setVisible(true);
            jLabel3.setForeground(new Color(204,204,204));
            jLabel4.setForeground(new Color(204,204,204));
            jLabel5.setForeground(new Color(204,204,204));
            lblDNI.setForeground(new Color(204,204,204));
            lblHC.setForeground(new Color(204,204,204));
            txtActoMedico.setForeground(new Color(204,204,204));
            txtActoMedico.setVisible(true);
        } else {
            cbxActoMedico.setVisible(false);
            lblMensajeActoMedico.setVisible(false);
            lblApellidos.setText("El paciente no tiene registros");
            jLabel3.setForeground(Color.BLACK);
            jLabel4.setForeground(Color.BLACK);
            jLabel5.setForeground(Color.BLACK);
            lblDNI.setForeground(Color.BLACK);
            lblHC.setForeground(Color.BLACK);
            txtActoMedico.setForeground(Color.BLACK);
            txtActoMedico.setVisible(false);
            pnlVentas.setVisible(false);
        }
    }//GEN-LAST:event_txtDniCaretUpdate
>>>>>>> 8ab130461fee646e9653f9ee3f176d97ca8775a8

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed

    }//GEN-LAST:event_T3ActionPerformed

    private void tbCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMouseClicked
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        int fila = tbCabecera.getSelectedRow();
        if(evt.getClickCount()==1){
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(fila, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(fila, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(fila, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"RX");
                lblIdCabecera.setText(String.valueOf(tbCabecera.getValueAt(fila, 14)));
                btnFacturarEcografias.setEnabled(false);
                btnFacturarFarmacia.setEnabled(false);
                btnFacturarLaboratorio.setEnabled(false);
                btnFacturarProcedimientos.setEnabled(false);
                btnFacturarRayos.setEnabled(false);
        }
    }//GEN-LAST:event_tbCabeceraMouseClicked

    private void tbCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCabeceraMousePressed

    }//GEN-LAST:event_tbCabeceraMousePressed

    private void tbCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyPressed

    }//GEN-LAST:event_tbCabeceraKeyPressed

    private void tbProcedimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimientosMouseClicked
        if(evt.getClickCount()==1){
            tbLaboratorio.setCellSelectionEnabled(false);
            tbProcedimientos.setCellSelectionEnabled(true);
            tbEcografias.setCellSelectionEnabled(false);
            tbFarmacia.setCellSelectionEnabled(false);
            tbRayos.setCellSelectionEnabled(false);
            btnFacturarProcedimientos.setEnabled(true);
            btnFacturarEcografias.setEnabled(false);
            btnFacturarFarmacia.setEnabled(false);
            btnFacturarLaboratorio.setEnabled(false);
            btnFacturarRayos.setEnabled(false);
        }
        if(evt.getClickCount()==2){
            btnFacturarProcedimientos.doClick();
        }
    }//GEN-LAST:event_tbProcedimientosMouseClicked

    private void tbProcedimientosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProcedimientosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosMousePressed

    private void tbProcedimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProcedimientosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProcedimientosKeyPressed

    private void tbLaboratorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLaboratorioMouseClicked
        if(evt.getClickCount()==1){
            tbLaboratorio.setCellSelectionEnabled(true);
            tbProcedimientos.setCellSelectionEnabled(false);
            tbEcografias.setCellSelectionEnabled(false);
            tbFarmacia.setCellSelectionEnabled(false);
            tbRayos.setCellSelectionEnabled(false);
            btnFacturarProcedimientos.setEnabled(false);
            btnFacturarEcografias.setEnabled(false);
            btnFacturarFarmacia.setEnabled(false);
            btnFacturarLaboratorio.setEnabled(true);
            btnFacturarRayos.setEnabled(false);
        }
        if(evt.getClickCount()==2){
            btnFacturarLaboratorio.doClick();
        }
    }//GEN-LAST:event_tbLaboratorioMouseClicked

    private void tbLaboratorioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLaboratorioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLaboratorioMousePressed

    private void tbLaboratorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbLaboratorioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbLaboratorioKeyPressed

    private void tbRayosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRayosMouseClicked
        if(evt.getClickCount()==1){
            tbLaboratorio.setCellSelectionEnabled(false);
            tbProcedimientos.setCellSelectionEnabled(false);
            tbEcografias.setCellSelectionEnabled(false);
            tbFarmacia.setCellSelectionEnabled(false);
            tbRayos.setCellSelectionEnabled(true);
            btnFacturarProcedimientos.setEnabled(false);
            btnFacturarEcografias.setEnabled(false);
            btnFacturarFarmacia.setEnabled(false);
            btnFacturarLaboratorio.setEnabled(false);
            btnFacturarRayos.setEnabled(true);
        }
        
        if(evt.getClickCount()==2){
            btnFacturarRayos.doClick();
        }
    }//GEN-LAST:event_tbRayosMouseClicked

    private void tbRayosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRayosMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRayosMousePressed

    private void tbRayosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRayosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbRayosKeyPressed

    private void tbEcografiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEcografiasMouseClicked
        if(evt.getClickCount()==1){
            tbLaboratorio.setCellSelectionEnabled(false);
            tbProcedimientos.setCellSelectionEnabled(false);
            tbEcografias.setCellSelectionEnabled(true);
            tbFarmacia.setCellSelectionEnabled(false);
            tbRayos.setCellSelectionEnabled(false);
            btnFacturarProcedimientos.setEnabled(false);
            btnFacturarEcografias.setEnabled(true);
            btnFacturarFarmacia.setEnabled(false);
            btnFacturarLaboratorio.setEnabled(false);
            btnFacturarRayos.setEnabled(false);
        }
        if(evt.getClickCount()==2){
            btnFacturarEcografias.doClick();
        }
    }//GEN-LAST:event_tbEcografiasMouseClicked

    private void tbEcografiasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEcografiasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEcografiasMousePressed

    private void tbEcografiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbEcografiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbEcografiasKeyPressed

    private void tbFarmaciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmaciaMouseClicked
        if(evt.getClickCount()==1){
            tbLaboratorio.setCellSelectionEnabled(false);
            tbProcedimientos.setCellSelectionEnabled(false);
            tbEcografias.setCellSelectionEnabled(false);
            tbFarmacia.setCellSelectionEnabled(true);
            tbRayos.setCellSelectionEnabled(false);
            btnFacturarProcedimientos.setEnabled(false);
            btnFacturarEcografias.setEnabled(false);
            btnFacturarFarmacia.setEnabled(true);
            btnFacturarLaboratorio.setEnabled(false);
            btnFacturarRayos.setEnabled(false);
        }
        if(evt.getClickCount()==2){
            btnFacturarFarmacia.doClick();
        }
    }//GEN-LAST:event_tbFarmaciaMouseClicked

    private void tbFarmaciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFarmaciaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFarmaciaMousePressed

    private void tbFarmaciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFarmaciaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFarmaciaKeyPressed

<<<<<<< HEAD
    private void tb_Grupo8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo8KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8KeyPressed

    private void tb_Grupo8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MousePressed

    private void tb_Grupo8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Grupo8MouseClicked
=======
    private void txtActoMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtActoMedicoCaretUpdate
        buscarVentas();
    }//GEN-LAST:event_txtActoMedicoCaretUpdate

    private void cbxActoMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxActoMedicoItemStateChanged
        try {
            txtActoMedico.setText(cbxActoMedico.getSelectedItem().toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbxActoMedicoItemStateChanged

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if(cbxActoMedico.isVisible()){
            if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                cbxActoMedico.requestFocus();
                cbxActoMedico.showPopup();
            }
        }
    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtActoMedicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActoMedicoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
                buscarVentas();
        }
    }//GEN-LAST:event_txtActoMedicoKeyPressed

    private void tbCabeceraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCabeceraKeyReleased
        CuentasPorPagarVentasConsolidadoCabecera cabecera1 = new CuentasPorPagarVentasConsolidadoCabecera();
        int fila = tbCabecera.getSelectedRow();
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
                cabecera1.ventasConsolidadoDetalles(tbProcedimientos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"CJ");
                cabecera1.ventasConsolidadoDetalles(tbEcografias,String.valueOf(tbCabecera.getValueAt(fila, 14)),"EC");
                cabecera1.ventasConsolidadoDetalles(tbFarmacia,String.valueOf(tbCabecera.getValueAt(fila, 14)),"FR");
                cabecera1.ventasConsolidadoDetalles(tbLaboratorio,String.valueOf(tbCabecera.getValueAt(fila, 14)),"LA");
                cabecera1.ventasConsolidadoDetalles(tbRayos,String.valueOf(tbCabecera.getValueAt(fila, 14)),"RX");
        }
    }//GEN-LAST:event_tbCabeceraKeyReleased

    private void btnFacturarLaboratorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarLaboratorioActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
        }
        actualizarEstadoFacturacion(tbLaboratorio);
    }//GEN-LAST:event_btnFacturarLaboratorioActionPerformed

    private void btnFacturarProcedimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarProcedimientosActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
        }
        actualizarEstadoFacturacion(tbProcedimientos);
    }//GEN-LAST:event_btnFacturarProcedimientosActionPerformed

    private void btnFacturarRayosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarRayosActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
        }
        actualizarEstadoFacturacion(tbRayos);
    }//GEN-LAST:event_btnFacturarRayosActionPerformed

    private void btnFacturarEcografiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarEcografiasActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
        }
        actualizarEstadoFacturacion(tbEcografias);
    }//GEN-LAST:event_btnFacturarEcografiasActionPerformed

    private void btnFacturarFarmaciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarFarmaciaActionPerformed
        if(Facturado==false){
             Facturador fac=  new Facturador();
            fac.setVisible(true);
            Facturado= true;
        } else {
            Facturador.tbFacturacion.requestFocus();
        }
        actualizarEstadoFacturacion(tbFarmacia);
    }//GEN-LAST:event_btnFacturarFarmaciaActionPerformed
>>>>>>> 8ab130461fee646e9653f9ee3f176d97ca8775a8

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
    public static javax.swing.JButton btnFacturarEcografias;
    public static javax.swing.JButton btnFacturarFarmacia;
    public static javax.swing.JButton btnFacturarLaboratorio;
    public static javax.swing.JButton btnFacturarProcedimientos;
    public static javax.swing.JButton btnFacturarRayos;
    public static javax.swing.JComboBox cbxActoMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblEcografias;
    private javax.swing.JLabel lblFarmacia;
    private javax.swing.JLabel lblHC;
    private javax.swing.JLabel lblIdCabecera;
    private javax.swing.JLabel lblLaboratorio;
    private javax.swing.JLabel lblMensajeActoMedico;
    private javax.swing.JLabel lblProcedimientos;
    private javax.swing.JLabel lblRayos;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JPanel pnlDatos;
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
    public static javax.swing.JTextField txtActoMedico;
    public static javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
