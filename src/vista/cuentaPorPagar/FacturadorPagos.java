/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import campos.LimitadorDeDocumento;
import com.toedter.calendar.JDateChooser;
//import com.sun.org.apache.xml.internal.security.c14n.CanonicalizationException;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Caja.Caja_NuevaVenta;
import modelos.cuentaPorPagar.CuentasPorPagarSfsRpta;
import vista.COSTOS.Costos_Sustentacion;
import static vista.cuentaPorPagar.VentasConsolidado.Facturado;
/**
 *
 * @author PC02
 */
public class FacturadorPagos extends javax.swing.JFrame {

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
    CuentasPorPagarSfsRpta rpta = new CuentasPorPagarSfsRpta();
    public FacturadorPagos() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
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
        spFacturaDetalles.setVisible(false);
        pnlImportes.setVisible(false);
        cerrar();
        agregarFacturas();
        rpta.listarFacturasAceptadas(tbFacturas, "","F","","");
        tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
        tbFacturas.requestFocus();
        enviarDetallesFactura();
        txtBuscar.setEditable(false);
        pnlAtajos.setVisible(false);
    }

    public String determinarFecha(JDateChooser calendario){
         
        String fecha = "";
        try {
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        
            if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio); 
         } catch (Exception e) {
                           JOptionPane.showMessageDialog(this, "Error referente a la fecha: " + e.getMessage());
         }
        
        return fecha;
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                    Facturado = false;
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void agregarFacturas(){
        DefaultTableModel m;
        File ruta = new File("C:\\sunat_archivos\\sfs\\RPTA");
        //        System.out.println(ruta.getAbsolutePath());
        String[] nombres_archivos = ruta.list();
        m = (DefaultTableModel) tbFacturas.getModel();
        m.addColumn("Tipo",nombres_archivos);
        CuentasPorPagarSfsRpta rpta = new CuentasPorPagarSfsRpta();
        rpta.mantenimientoCuentasPorPagarSfsRpta("E");
        rpta.mantenimientoCuentasPorPagarSfsRpta("R");
        for (int i = 0; i < tbFacturas.getRowCount(); i++){
            rpta.setNombre(String.valueOf(tbFacturas.getValueAt(i, 0)));
            rpta.mantenimientoCuentasPorPagarSfsRpta("I");
        }
    }
   
    public void enviarDetallesFactura(){
        CuentasPorPagarSfsRpta rpta1 = new CuentasPorPagarSfsRpta();
        int fila = tbFacturas.getSelectedRow();
        txtSumatoriaIGV.setText("S/. " + String.valueOf(tbFacturas.getValueAt(fila, 4)));
        txtValorVInafectada.setText("S/. " + String.valueOf(tbFacturas.getValueAt(fila, 5)));
        txtValorVGravada.setText("S/. " + String.valueOf(tbFacturas.getValueAt(fila, 6)));
        txtImporteTotalVenta.setText("S/. " + String.valueOf(tbFacturas.getValueAt(fila, 7)));
        rpta1.listarFacturasDetalles(tbFacturaDetalles, String.valueOf(tbFacturas.getValueAt(fila, 3)));
        if(String.valueOf(tbFacturas.getValueAt(fila, 8)).equalsIgnoreCase("FACTURADO")){
            pnlPagar.setBackground(new Color(41,127,184));
            btnPagar.setVisible(true);
        } else {
            pnlPagar.setBackground(new Color(240,240,240));
            btnPagar.setVisible(false);
        }
        spFacturaDetalles.setVisible(true);
        pnlImportes.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Empresa = new javax.swing.JDialog();
        jPanel48 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        txtBuscarEmpresa = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        btnBuscarPaciente5 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tb_Empresa = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLocaleChooser1 = new com.toedter.components.JLocaleChooser();
            buttonGroup1 = new javax.swing.ButtonGroup();
            jPanel21 = new javax.swing.JPanel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            lblusu = new javax.swing.JLabel();
            pnlAtajos = new javax.swing.JPanel();
            btnGuardar = new javax.swing.JButton();
            lblId = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            lblEmpresa = new javax.swing.JLabel();
            tablaS = new javax.swing.JScrollPane();
            tbFacturas = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                panelCPT = new javax.swing.JPanel();
                txtBuscar = new javax.swing.JTextField();
                spFacturaDetalles = new javax.swing.JScrollPane();
                tbFacturaDetalles = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    btnBuscar = new javax.swing.JButton();
                    pnlImportes = new javax.swing.JPanel();
                    jPanel40 = new javax.swing.JPanel();
                    jLabel33 = new javax.swing.JLabel();
                    panelCPT19 = new javax.swing.JPanel();
                    txtValorVInafectada = new javax.swing.JTextField();
                    jPanel42 = new javax.swing.JPanel();
                    jLabel35 = new javax.swing.JLabel();
                    panelCPT21 = new javax.swing.JPanel();
                    txtImporteTotalVenta = new javax.swing.JTextField();
                    jPanel39 = new javax.swing.JPanel();
                    jLabel32 = new javax.swing.JLabel();
                    panelCPT18 = new javax.swing.JPanel();
                    txtSumatoriaIGV = new javax.swing.JTextField();
                    pnlPagar = new javax.swing.JPanel();
                    btnPagar = new javax.swing.JButton();
                    jPanel41 = new javax.swing.JPanel();
                    jLabel34 = new javax.swing.JLabel();
                    panelCPT20 = new javax.swing.JPanel();
                    txtValorVGravada = new javax.swing.JTextField();
                    dtFechaI = new com.toedter.calendar.JDateChooser();
                    dtFechaF = new com.toedter.calendar.JDateChooser();
                    rbtPorCancelar = new javax.swing.JRadioButton();
                    rbtCanceladas = new javax.swing.JRadioButton();
                    rbtTodas = new javax.swing.JRadioButton();
                    lblEmpresa1 = new javax.swing.JLabel();
                    lblEmpresa2 = new javax.swing.JLabel();

                    Empresa.setAlwaysOnTop(true);
                    Empresa.setMinimumSize(new java.awt.Dimension(754, 452));
                    Empresa.setResizable(false);

                    jPanel48.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel48.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel62.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel62.setText("<html>Empresa<span style=\"font-size:'14px'\"> Forma de pago</span></html>");

                    jPanel49.setBackground(new java.awt.Color(255, 255, 255));

                    txtBuscarEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtBuscarEmpresa.setBorder(null);
                    txtBuscarEmpresa.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarEmpresaCaretUpdate(evt);
                        }
                    });
                    txtBuscarEmpresa.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscarEmpresaActionPerformed(evt);
                        }
                    });
                    txtBuscarEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarEmpresaKeyPressed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
                    jPanel49.setLayout(jPanel49Layout);
                    jPanel49Layout.setHorizontalGroup(
                        jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel49Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 1, Short.MAX_VALUE))
                    );
                    jPanel49Layout.setVerticalGroup(
                        jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(txtBuscarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );

                    jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel56.setText("Representante o Razón social");

                    btnBuscarPaciente5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
                    btnBuscarPaciente5.setContentAreaFilled(false);
                    btnBuscarPaciente5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarPaciente5.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarPaciente5ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                    jPanel48.setLayout(jPanel48Layout);
                    jPanel48Layout.setHorizontalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel48Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel56)
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel48Layout.createSequentialGroup()
                                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(470, Short.MAX_VALUE))
                    );
                    jPanel48Layout.setVerticalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel56)
                            .addGap(326, 326, 326))
                    );

                    jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));
                    jScrollPane16.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Empresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    tb_Empresa.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Empresa.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Empresa.setRowHeight(25);
                    tb_Empresa.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tb_Empresa.getTableHeader().setReorderingAllowed(false);
                    tb_Empresa.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_EmpresaMouseClicked(evt);
                        }
                    });
                    tb_Empresa.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_EmpresaKeyPressed(evt);
                        }
                    });
                    jScrollPane16.setViewportView(tb_Empresa);

                    javax.swing.GroupLayout EmpresaLayout = new javax.swing.GroupLayout(Empresa.getContentPane());
                    Empresa.getContentPane().setLayout(EmpresaLayout);
                    EmpresaLayout.setHorizontalGroup(
                        EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmpresaLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    );
                    EmpresaLayout.setVerticalGroup(
                        EmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(EmpresaLayout.createSequentialGroup()
                            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                    );

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

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(41, 127, 184));
                    jLabel1.setText("<html><span style=\"font-size:'30px'\">Cuentas por Pagar - </span>Factura Electrónica</html>");

                    lblusu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                    lblusu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    lblusu.setText("Usuario: Silvana");

                    btnGuardar.setBackground(new java.awt.Color(102, 0, 102));
                    btnGuardar.setText("Guardar");
                    btnGuardar.setBorderPainted(false);
                    btnGuardar.setContentAreaFilled(false);
                    btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    btnGuardar.setDefaultCapable(false);
                    btnGuardar.setFocusPainted(false);
                    btnGuardar.setFocusable(false);
                    btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
                    btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGuardarActionPerformed(evt);
                        }
                    });

                    lblId.setText("jLabel3");

                    lblMant.setText("I");

                    lblEmpresa.setText("Empresa");

                    javax.swing.GroupLayout pnlAtajosLayout = new javax.swing.GroupLayout(pnlAtajos);
                    pnlAtajos.setLayout(pnlAtajosLayout);
                    pnlAtajosLayout.setHorizontalGroup(
                        pnlAtajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlAtajosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblMant)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblId)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblEmpresa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGuardar)
                            .addContainerGap(94, Short.MAX_VALUE))
                    );
                    pnlAtajosLayout.setVerticalGroup(
                        pnlAtajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlAtajosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(pnlAtajosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMant)
                                .addComponent(lblId)
                                .addComponent(lblEmpresa)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                    );

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(pnlAtajos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pnlAtajos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblusu))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    tablaS.setBackground(new java.awt.Color(255, 255, 255));
                    tablaS.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    tablaS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    tablaS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                    tablaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                    tbFacturas.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    tbFacturas.setForeground(new java.awt.Color(51, 51, 51));
                    tbFacturas.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {

                        }
                    ));
                    tbFacturas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbFacturas.setGridColor(new java.awt.Color(255, 255, 255));
                    tbFacturas.setRowHeight(25);
                    tbFacturas.setSelectionBackground(new java.awt.Color(41, 127, 184));
                    tbFacturas.getTableHeader().setReorderingAllowed(false);
                    tbFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbFacturasMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbFacturasMousePressed(evt);
                        }
                    });
                    tbFacturas.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbFacturasKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            tbFacturasKeyReleased(evt);
                        }
                    });
                    tablaS.setViewportView(tbFacturas);

                    panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    txtBuscar.setForeground(new java.awt.Color(51, 51, 51));
                    txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtBuscar.setBorder(null);
                    txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarCaretUpdate(evt);
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
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyTyped(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                    panelCPT.setLayout(panelCPTLayout);
                    panelCPTLayout.setHorizontalGroup(
                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    );
                    panelCPTLayout.setVerticalGroup(
                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    );

                    spFacturaDetalles.setBackground(new java.awt.Color(255, 255, 255));
                    spFacturaDetalles.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    spFacturaDetalles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                    spFacturaDetalles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                    spFacturaDetalles.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                    tbFacturaDetalles.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                    tbFacturaDetalles.setForeground(new java.awt.Color(51, 51, 51));
                    tbFacturaDetalles.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {

                        }
                    ));
                    tbFacturaDetalles.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbFacturaDetalles.setGridColor(new java.awt.Color(255, 255, 255));
                    tbFacturaDetalles.setRowHeight(25);
                    tbFacturaDetalles.setSelectionBackground(new java.awt.Color(153, 153, 153));
                    tbFacturaDetalles.getTableHeader().setReorderingAllowed(false);
                    tbFacturaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbFacturaDetallesMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbFacturaDetallesMousePressed(evt);
                        }
                    });
                    tbFacturaDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbFacturaDetallesKeyPressed(evt);
                        }
                    });
                    spFacturaDetalles.setViewportView(tbFacturaDetalles);

                    btnBuscar.setBackground(new java.awt.Color(102, 0, 102));
                    btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-30.png"))); // NOI18N
                    btnBuscar.setMnemonic('B');
                    btnBuscar.setToolTipText("Buscar Empresa");
                    btnBuscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                    btnBuscar.setBorderPainted(false);
                    btnBuscar.setContentAreaFilled(false);
                    btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscar.setDefaultCapable(false);
                    btnBuscar.setFocusPainted(false);
                    btnBuscar.setFocusable(false);
                    btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarActionPerformed(evt);
                        }
                    });

                    jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                    jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    jLabel33.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel33.setText("Total Valor de Ventas Inafectadas");

                    panelCPT19.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtValorVInafectada.setEditable(false);
                    txtValorVInafectada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                    txtValorVInafectada.setForeground(new java.awt.Color(41, 127, 184));
                    txtValorVInafectada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtValorVInafectada.setText("0.00");
                    txtValorVInafectada.setBorder(null);
                    txtValorVInafectada.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtValorVInafectadaCaretUpdate(evt);
                        }
                    });
                    txtValorVInafectada.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtValorVInafectadaActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPT19Layout = new javax.swing.GroupLayout(panelCPT19);
                    panelCPT19.setLayout(panelCPT19Layout);
                    panelCPT19Layout.setHorizontalGroup(
                        panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtValorVInafectada, javax.swing.GroupLayout.Alignment.TRAILING)
                    );
                    panelCPT19Layout.setVerticalGroup(
                        panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtValorVInafectada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
                    jPanel40.setLayout(jPanel40Layout);
                    jPanel40Layout.setHorizontalGroup(
                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel40Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelCPT19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                            .addContainerGap())
                    );
                    jPanel40Layout.setVerticalGroup(
                        jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel40Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelCPT19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                    jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel35.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel35.setText("Importe Total de Venta");

                    panelCPT21.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtImporteTotalVenta.setEditable(false);
                    txtImporteTotalVenta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                    txtImporteTotalVenta.setForeground(new java.awt.Color(41, 127, 184));
                    txtImporteTotalVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtImporteTotalVenta.setText("0.00");
                    txtImporteTotalVenta.setBorder(null);
                    txtImporteTotalVenta.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtImporteTotalVentaCaretUpdate(evt);
                        }
                    });
                    txtImporteTotalVenta.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtImporteTotalVentaActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPT21Layout = new javax.swing.GroupLayout(panelCPT21);
                    panelCPT21.setLayout(panelCPT21Layout);
                    panelCPT21Layout.setHorizontalGroup(
                        panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                    );
                    panelCPT21Layout.setVerticalGroup(
                        panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                    jPanel42.setLayout(jPanel42Layout);
                    jPanel42Layout.setHorizontalGroup(
                        jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel42Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                .addComponent(panelCPT21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel42Layout.setVerticalGroup(
                        jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel42Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelCPT21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                    jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel32.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel32.setText("Sumatoria IGV");

                    panelCPT18.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtSumatoriaIGV.setEditable(false);
                    txtSumatoriaIGV.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                    txtSumatoriaIGV.setForeground(new java.awt.Color(41, 127, 184));
                    txtSumatoriaIGV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtSumatoriaIGV.setText("0.00");
                    txtSumatoriaIGV.setBorder(null);
                    txtSumatoriaIGV.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtSumatoriaIGVCaretUpdate(evt);
                        }
                    });
                    txtSumatoriaIGV.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtSumatoriaIGVActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPT18Layout = new javax.swing.GroupLayout(panelCPT18);
                    panelCPT18.setLayout(panelCPT18Layout);
                    panelCPT18Layout.setHorizontalGroup(
                        panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSumatoriaIGV, javax.swing.GroupLayout.Alignment.TRAILING)
                    );
                    panelCPT18Layout.setVerticalGroup(
                        panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSumatoriaIGV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
                    jPanel39.setLayout(jPanel39Layout);
                    jPanel39Layout.setHorizontalGroup(
                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(panelCPT18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                    );
                    jPanel39Layout.setVerticalGroup(
                        jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel39Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelCPT18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    pnlPagar.setBackground(new java.awt.Color(41, 127, 184));
                    pnlPagar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(27, 77, 111), new java.awt.Color(33, 95, 136), new java.awt.Color(27, 77, 111), new java.awt.Color(33, 95, 136)));

                    btnPagar.setBackground(new java.awt.Color(102, 0, 102));
                    btnPagar.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                    btnPagar.setForeground(new java.awt.Color(255, 255, 255));
                    btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Caja fuerte-64.png"))); // NOI18N
                    btnPagar.setMnemonic('B');
                    btnPagar.setText("Pagar");
                    btnPagar.setToolTipText("Buscar Empresa");
                    btnPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 5));
                    btnPagar.setBorderPainted(false);
                    btnPagar.setContentAreaFilled(false);
                    btnPagar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    btnPagar.setDefaultCapable(false);
                    btnPagar.setFocusPainted(false);
                    btnPagar.setFocusable(false);
                    btnPagar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnPagarActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout pnlPagarLayout = new javax.swing.GroupLayout(pnlPagar);
                    pnlPagar.setLayout(pnlPagarLayout);
                    pnlPagarLayout.setHorizontalGroup(
                        pnlPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    );
                    pnlPagarLayout.setVerticalGroup(
                        pnlPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlPagarLayout.createSequentialGroup()
                            .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 1, Short.MAX_VALUE))
                    );

                    jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                    jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel34.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel34.setText("Total Valor de Ventas Gravadas");

                    panelCPT20.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtValorVGravada.setEditable(false);
                    txtValorVGravada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
                    txtValorVGravada.setForeground(new java.awt.Color(41, 127, 184));
                    txtValorVGravada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtValorVGravada.setText("0.00");
                    txtValorVGravada.setBorder(null);
                    txtValorVGravada.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtValorVGravadaCaretUpdate(evt);
                        }
                    });
                    txtValorVGravada.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtValorVGravadaActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPT20Layout = new javax.swing.GroupLayout(panelCPT20);
                    panelCPT20.setLayout(panelCPT20Layout);
                    panelCPT20Layout.setHorizontalGroup(
                        panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtValorVGravada, javax.swing.GroupLayout.Alignment.TRAILING)
                    );
                    panelCPT20Layout.setVerticalGroup(
                        panelCPT20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtValorVGravada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
                    jPanel41.setLayout(jPanel41Layout);
                    jPanel41Layout.setHorizontalGroup(
                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel41Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelCPT20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                    );
                    jPanel41Layout.setVerticalGroup(
                        jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel41Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelCPT20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    javax.swing.GroupLayout pnlImportesLayout = new javax.swing.GroupLayout(pnlImportes);
                    pnlImportes.setLayout(pnlImportesLayout);
                    pnlImportesLayout.setHorizontalGroup(
                        pnlImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlImportesLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    pnlImportesLayout.setVerticalGroup(
                        pnlImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlImportesLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(pnlImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(31, Short.MAX_VALUE))
                    );

                    dtFechaI.setBackground(new java.awt.Color(255, 255, 255));
                    dtFechaI.setForeground(new java.awt.Color(51, 51, 51));
                    dtFechaI.setDateFormatString("dd/MM/yyyy");
                    dtFechaI.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                    dtFechaI.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            dtFechaIMouseClicked(evt);
                        }
                    });

                    dtFechaF.setBackground(new java.awt.Color(255, 255, 255));
                    dtFechaF.setForeground(new java.awt.Color(51, 51, 51));
                    dtFechaF.setDateFormatString("dd/MM/yyyy");
                    dtFechaF.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                    dtFechaF.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            dtFechaFMouseClicked(evt);
                        }
                    });

                    buttonGroup1.add(rbtPorCancelar);
                    rbtPorCancelar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                    rbtPorCancelar.setForeground(new java.awt.Color(51, 51, 51));
                    rbtPorCancelar.setSelected(true);
                    rbtPorCancelar.setText("Por Cancelar");
                    rbtPorCancelar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            rbtPorCancelarActionPerformed(evt);
                        }
                    });

                    buttonGroup1.add(rbtCanceladas);
                    rbtCanceladas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                    rbtCanceladas.setForeground(new java.awt.Color(51, 51, 51));
                    rbtCanceladas.setText("Canceladas");
                    rbtCanceladas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            rbtCanceladasActionPerformed(evt);
                        }
                    });

                    buttonGroup1.add(rbtTodas);
                    rbtTodas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                    rbtTodas.setForeground(new java.awt.Color(51, 51, 51));
                    rbtTodas.setText("Todas");
                    rbtTodas.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            rbtTodasActionPerformed(evt);
                        }
                    });

                    lblEmpresa1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                    lblEmpresa1.setForeground(new java.awt.Color(51, 51, 51));
                    lblEmpresa1.setText("Rango de fechas");

                    lblEmpresa2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
                    lblEmpresa2.setForeground(new java.awt.Color(51, 51, 51));
                    lblEmpresa2.setText("Estado");

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spFacturaDetalles)
                                        .addComponent(pnlImportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEmpresa1)
                                        .addComponent(lblEmpresa2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rbtPorCancelar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbtCanceladas)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(rbtTodas))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtPorCancelar)
                                .addComponent(rbtCanceladas)
                                .addComponent(rbtTodas)
                                .addComponent(lblEmpresa2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(dtFechaF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblEmpresa1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(spFacturaDetalles, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                                    .addGap(0, 0, 0)
                                    .addComponent(pnlImportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(tablaS)))
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void tbFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturasMouseClicked
        if(evt.getClickCount()==1){
            enviarDetallesFactura();
        }
    }//GEN-LAST:event_tbFacturasMouseClicked

    private void tbFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturasMousePressed

    }//GEN-LAST:event_tbFacturasMousePressed

    private void tbFacturasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturasKeyPressed
        
    }//GEN-LAST:event_tbFacturasKeyPressed

    private void txtBuscarEmpresaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaCaretUpdate
        Caja_NuevaVenta CNVE = new Caja_NuevaVenta();
        //        CNV.listarMedicos(txtBuscarEmpresa.getText(),lblServicio.getText(),tb_Empresa);
        CNVE.listarEmpresa(txtBuscarEmpresa.getText(),tb_Empresa);

    }//GEN-LAST:event_txtBuscarEmpresaCaretUpdate

    private void txtBuscarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaActionPerformed

    private void txtBuscarEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpresaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpresaKeyPressed

    private void btnBuscarPaciente5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente5ActionPerformed

    }//GEN-LAST:event_btnBuscarPaciente5ActionPerformed

    private void tb_EmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EmpresaMouseClicked
        
    }//GEN-LAST:event_tb_EmpresaMouseClicked

    private void tb_EmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_EmpresaKeyPressed
      
    }//GEN-LAST:event_tb_EmpresaKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate

    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
     
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tbFacturaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturaDetallesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturaDetallesMouseClicked

    private void tbFacturaDetallesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturaDetallesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturaDetallesMousePressed

    private void tbFacturaDetallesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturaDetallesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturaDetallesKeyPressed

    private void txtSumatoriaIGVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSumatoriaIGVCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSumatoriaIGVCaretUpdate

    private void txtSumatoriaIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumatoriaIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSumatoriaIGVActionPerformed

    private void txtValorVInafectadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVInafectadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVInafectadaCaretUpdate

    private void txtValorVInafectadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVInafectadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVInafectadaActionPerformed

    private void txtValorVGravadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVGravadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVGravadaCaretUpdate

    private void txtValorVGravadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVGravadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVGravadaActionPerformed

    private void txtImporteTotalVentaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaCaretUpdate

    private void txtImporteTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        try {
            CuentasPorPagarSfsRpta rpta2 = new CuentasPorPagarSfsRpta();
            int fila = tbFacturas.getSelectedRow();
            if(rpta2.pagarFactura(String.valueOf(tbFacturas.getValueAt(fila, 3)))){
                JOptionPane.showMessageDialog(this, "Factura Cancelada en efectivo");
                rpta.listarFacturasAceptadas(tbFacturas, "","F","","");
                spFacturaDetalles.setVisible(false);
                pnlImportes.setVisible(false);
                tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
                tbFacturas.requestFocus();
                enviarDetallesFactura();
                rbtPorCancelar.setSelected(true);
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrió un error comuníquese con el administrador del sistema");
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error comuníquese con el administrador del sistema: " + e.getMessage() );
        }
    }//GEN-LAST:event_btnPagarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(dtFechaI.getDate()==null || dtFechaF.getDate()==null){
            JOptionPane.showMessageDialog(this, "Ingrese correctamente las fechas");
        } else {
            if(rbtPorCancelar.isSelected()){
                rpta.listarFacturasAceptadas(tbFacturas, "","F",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
                txtBuscar.setEditable(true);
            }else
            if(rbtCanceladas.isSelected()){
                rpta.listarFacturasAceptadas(tbFacturas, "","C",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
                txtBuscar.setEditable(true);
            }else
            if(rbtTodas.isSelected()){
                rpta.listarFacturasAceptadas(tbFacturas, "","T",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
                txtBuscar.setEditable(true);
            }
            tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
            tbFacturas.requestFocus();
            enviarDetallesFactura();
            txtBuscar.requestFocus();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbFacturasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturasKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            enviarDetallesFactura();
        }
    }//GEN-LAST:event_tbFacturasKeyReleased

    private void rbtCanceladasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtCanceladasActionPerformed
        if(dtFechaI.getDate()==null){
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"C","","");
        } else {
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"C",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
        }
        tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
        tbFacturas.requestFocus();
        enviarDetallesFactura();
    }//GEN-LAST:event_rbtCanceladasActionPerformed

    private void rbtTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTodasActionPerformed
        if(dtFechaI.getDate()==null){
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"T","","");
        } else {
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"T",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
        }
        tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
        tbFacturas.requestFocus();
        enviarDetallesFactura();
    }//GEN-LAST:event_rbtTodasActionPerformed

    private void rbtPorCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtPorCancelarActionPerformed
        if(dtFechaI.getDate()==null){
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"F","","");
        } else {
            CuentasPorPagarSfsRpta rpta3 = new CuentasPorPagarSfsRpta();
            rpta3.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"F",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
        }
        tbFacturas.getSelectionModel().setSelectionInterval (0,0) ;
        tbFacturas.requestFocus();
        enviarDetallesFactura();
    }//GEN-LAST:event_rbtPorCancelarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            if(dtFechaI.getDate()!=null || dtFechaF.getDate()!=null){
                if(rbtPorCancelar.isSelected())
                    rpta.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"F",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
                else
                if(rbtCanceladas.isSelected())
                    rpta.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"C",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
                else
                if(rbtTodas.isSelected())
                    rpta.listarFacturasAceptadas(tbFacturas, txtBuscar.getText(),"T",determinarFecha(dtFechaI),determinarFecha(dtFechaF));
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese correctamente las fechas");
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void dtFechaIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaIMouseClicked
   
    }//GEN-LAST:event_dtFechaIMouseClicked

    private void dtFechaFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaFMouseClicked
   
    }//GEN-LAST:event_dtFechaFMouseClicked

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
            java.util.logging.Logger.getLogger(FacturadorPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturadorPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturadorPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturadorPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturadorPagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Empresa;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarPaciente5;
    public static javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPagar;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dtFechaF;
    private com.toedter.calendar.JDateChooser dtFechaI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel62;
    private com.toedter.components.JLocaleChooser jLocaleChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEmpresa1;
    private javax.swing.JLabel lblEmpresa2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT18;
    private javax.swing.JPanel panelCPT19;
    private javax.swing.JPanel panelCPT20;
    private javax.swing.JPanel panelCPT21;
    private javax.swing.JPanel pnlAtajos;
    private javax.swing.JPanel pnlImportes;
    private javax.swing.JPanel pnlPagar;
    private javax.swing.JRadioButton rbtCanceladas;
    private javax.swing.JRadioButton rbtPorCancelar;
    private javax.swing.JRadioButton rbtTodas;
    private javax.swing.JScrollPane spFacturaDetalles;
    private javax.swing.JScrollPane tablaS;
    public static javax.swing.JTable tbFacturaDetalles;
    public static javax.swing.JTable tbFacturas;
    private javax.swing.JTable tb_Empresa;
    public static javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarEmpresa;
    public static javax.swing.JTextField txtImporteTotalVenta;
    public static javax.swing.JTextField txtSumatoriaIGV;
    public static javax.swing.JTextField txtValorVGravada;
    public static javax.swing.JTextField txtValorVInafectada;
    // End of variables declaration//GEN-END:variables
}
