/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Usuario;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.cuentaPorPagar.CuentasPorPagarComunicacionDeBaja;
import modelos.cuentaPorPagar.CuentasPorPagarFacturasDetalle;
import modelos.cuentaPorPagar.CuentasPorPagarNotaDeCreditoCabecera;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;

/**
 *
 * @author PC02
 */
public class ComunicacionBaja extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
     CuentasPorPagarNotaDeCreditoCabecera serie = new CuentasPorPagarNotaDeCreditoCabecera();
    public ComunicacionBaja() {
        initComponents();
        c.conectar();
       this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        
        cbxDocumento.setBackground(Color.WHITE);
       
        
       
        
//        tbFacturacion.getTableHeader().setVisible(false);
//        tbFacturacion.setTableHeader(null);
     
        lblFechaEmision.setText(fechaActual());
       
        cbxBuscarDocumento.setBackground(Color.WHITE);
        
        BUSCAR_FACTURA_BOLETA.setLocationRelativeTo(null);
        BUSCAR_FACTURA_BOLETA.getContentPane().setBackground(Color.white);
        
     CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
        
        CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
        
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();

            }
        });
    }

    public void limpiar(){
       
        CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
                    
        cbxDocumento.setSelectedIndex(0);
        //CREDITO
        lblIdFactura.setText("");
        txtDescripcion.setText("");
        lblFechaDocumento.setText("");
        txtSerie.setText("");
        lblNroCorrelativo.setText("");
      
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
    }
    
     public void CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Fecha","DOCUMENTO","Serie","Número","Tipo Doc"
                     , "N° Documento","Apellidos y Nombres","Correo Electrónico","Desc. GLobal",
                "Sum Otros Cargos","Total Descuentos","Valor VFravada","Valor VInafectas","Valor Exoneradas",
             "Sumatoria IGV","Sumatoria ISC","Sum Otros Atributos","Importe Total"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[20];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_FACTURA_BOLETA_72h_listar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
        while(r.next()){
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
            fila[4]=r.getString(5);
            fila[5]=r.getString(6);
            fila[6]=r.getString(7);
            fila[7]=r.getString(8);
            fila[8]=r.getString(9);
            fila[9]=r.getString(10);
            fila[10]=r.getString(11);
            fila[11]=r.getString(12);
            fila[12]=r.getString(13);
            fila[13]=r.getString(14);
            fila[14]=r.getString(15);
            fila[15]=r.getString(16);
            fila[16]=r.getString(17);
            fila[17]=r.getString(18);
            fila[18]=r.getString(19);
                m.addRow(fila);
                c++;
            }
            tb_Factura_Boleta.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Factura_Boleta.setRowSorter(elQueOrdena);
            this.tb_Factura_Boleta.setModel(m);
    } catch (Exception e) {
    }
    }
    
    public void CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato(){
        tb_Factura_Boleta.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Factura_Boleta.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(3).setPreferredWidth(100);
    tb_Factura_Boleta.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(5).setPreferredWidth(110);
    tb_Factura_Boleta.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(7).setPreferredWidth(160);
    tb_Factura_Boleta.getColumnModel().getColumn(8).setPreferredWidth(160);
    //Ocultar    
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(9).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(9).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(11).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(11).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(12).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(12).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(13).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(13).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(14).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(14).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(15).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(15).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(16).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(16).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(17).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(17).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(18).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(18).setMaxWidth(0);
    
    }
    
    public String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(now);
    }
    
    
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel22 = new javax.swing.JPanel();
        Serie = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        BUSCAR_FACTURA_BOLETA = new javax.swing.JDialog();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Factura_Boleta = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            cbxBuscarDocumento = new javax.swing.JComboBox();
            jLabel68 = new javax.swing.JLabel();
            jLabel69 = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jLabel5 = new javax.swing.JLabel();
            lblFechaDocumento = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            cbxDocumento = new javax.swing.JComboBox();
            jLabel7 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            lblIdFactura = new javax.swing.JLabel();
            jPanel7 = new javax.swing.JPanel();
            lblFechaEmision = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jPanel46 = new javax.swing.JPanel();
            btnGenerarDoc = new javax.swing.JButton();
            jPanel32 = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            panelCPT13 = new javax.swing.JPanel();
            txtDescripcion = new javax.swing.JTextField();
            panelCPT50 = new javax.swing.JPanel();
            txtBuscarDocumento = new javax.swing.JTextField();
            jPanel8 = new javax.swing.JPanel();
            jLabel6 = new javax.swing.JLabel();
            lblNroBaja = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            txtSerie = new javax.swing.JLabel();
            txtSerie1 = new javax.swing.JLabel();
            lblNroCorrelativo = new javax.swing.JLabel();

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
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            Serie.add(jMenuItem1);

            jMenuItem2.setText("jMenuItem2");
            Serie.add(jMenuItem2);

            BUSCAR_FACTURA_BOLETA.setMinimumSize(new java.awt.Dimension(784, 561));

            jpanel.setBackground(new java.awt.Color(41, 127, 184));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo5.setText("Factura - Boleta");
            titulo5.setToolTipText("");
            titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            lblEstado.setText("jLabel70");

            jButton1.setBackground(new java.awt.Color(204, 0, 51));
            jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jButton1.setForeground(new java.awt.Color(255, 255, 255));
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salida 24 white.png"))); // NOI18N
            jButton1.setText("Salir");
            jButton1.setContentAreaFilled(false);
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
                            .addComponent(lblEstado)
                            .addGap(94, 94, 94))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addGap(0, 665, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addContainerGap())))
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo5)
                        .addComponent(lblEstado))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout BUSCAR_FACTURA_BOLETALayout = new javax.swing.GroupLayout(BUSCAR_FACTURA_BOLETA.getContentPane());
            BUSCAR_FACTURA_BOLETA.getContentPane().setLayout(BUSCAR_FACTURA_BOLETALayout);
            BUSCAR_FACTURA_BOLETALayout.setHorizontalGroup(
                BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            BUSCAR_FACTURA_BOLETALayout.setVerticalGroup(
                BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BUSCAR_FACTURA_BOLETALayout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(450, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);

            jPanel21.setBackground(new java.awt.Color(41, 127, 184));

            javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
            jPanel21.setLayout(jPanel21Layout);
            jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 19, Short.MAX_VALUE)
            );
            jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE)
            );

            getContentPane().add(jPanel21);
            jPanel21.setBounds(0, 0, 19, 400);

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));
            jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

            jPanel2.setBackground(new java.awt.Color(41, 127, 184));

            btnGuardar.setBackground(new java.awt.Color(102, 0, 102));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnGuardar.setBorderPainted(false);
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar1.setDefaultCapable(false);
            btnGuardar1.setFocusPainted(false);
            btnGuardar1.setFocusable(false);
            btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardar1ActionPerformed(evt);
                }
            });

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
            btnGuardar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
            jLabel1.setText("<html><span style=\"font-size:'30px'\">Cuenta por Pagar - </span>Factura Electrónica</html>");

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
                    .addGap(7, 7, 7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(7, 7, 7))
            );

            getContentPane().add(jPanel1);
            jPanel1.setBounds(25, 9, 1334, 70);

            jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_Factura_Boleta.setModel(new javax.swing.table.DefaultTableModel(
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
            tb_Factura_Boleta.setRowHeight(25);
            tb_Factura_Boleta.getTableHeader().setReorderingAllowed(false);
            tb_Factura_Boleta.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Factura_BoletaMouseClicked(evt);
                }
            });
            tb_Factura_Boleta.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Factura_BoletaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tb_Factura_BoletaKeyReleased(evt);
                }
            });
            jScrollPane2.setViewportView(tb_Factura_Boleta);

            getContentPane().add(jScrollPane2);
            jScrollPane2.setBounds(20, 160, 1350, 360);

            cbxBuscarDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            cbxBuscarDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxBuscarDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01 FACTURA", "03 BOLETA" }));
            cbxBuscarDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxBuscarDocumento.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    cbxBuscarDocumentoItemStateChanged(evt);
                }
            });
            cbxBuscarDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    cbxBuscarDocumentoActionPerformed(evt);
                }
            });
            getContentPane().add(cbxBuscarDocumento);
            cbxBuscarDocumento.setBounds(60, 100, 140, 30);

            jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel68.setText("Documento");
            getContentPane().add(jLabel68);
            jLabel68.setBounds(250, 130, 220, 16);

            jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel69.setText("Nro de Documento ");
            getContentPane().add(jLabel69);
            jLabel69.setBounds(80, 130, 105, 16);

            jPanel6.setBackground(new java.awt.Color(255, 255, 255));
            jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            jPanel6.setDoubleBuffered(false);
            jPanel6.setFocusable(false);

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(255, 51, 51));
            jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel5.setText("Fecha Generado el Documento");

            lblFechaDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFechaDocumento.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaDocumento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(lblFechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addContainerGap())
            );

            getContentPane().add(jPanel6);
            jPanel6.setBounds(170, 530, 240, 68);

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));
            jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            cbxDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            cbxDocumento.setForeground(new java.awt.Color(102, 102, 102));
            cbxDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 FACTURA", "03 BOLETA" }));
            cbxDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            cbxDocumento.setEnabled(false);
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
            jLabel7.setText("Tipo de Documento");

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

            getContentPane().add(jPanel10);
            jPanel10.setBounds(650, 530, 200, 68);

            lblUsu.setText("Silvana");
            getContentPane().add(lblUsu);
            lblUsu.setBounds(1160, 570, 60, 10);
            getContentPane().add(lblIdFactura);
            lblIdFactura.setBounds(480, 690, 0, 0);

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
            jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            lblFechaEmision.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblFechaEmision.setForeground(new java.awt.Color(102, 102, 102));
            lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFechaEmision.setText("2017-05-30");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 51, 51));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("Fecha de Comunicacion de Baja");

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFechaEmision)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            getContentPane().add(jPanel7);
            jPanel7.setBounds(420, 530, 220, 68);

            jPanel46.setBackground(new java.awt.Color(41, 127, 184));
            jPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            btnGenerarDoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            btnGenerarDoc.setForeground(new java.awt.Color(255, 255, 255));
            btnGenerarDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
            btnGenerarDoc.setMnemonic('G');
            btnGenerarDoc.setText("<html>Dar de Baja  <br>al Documento</html>");
            btnGenerarDoc.setBorderPainted(false);
            btnGenerarDoc.setContentAreaFilled(false);
            btnGenerarDoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGenerarDoc.setIconTextGap(20);
            btnGenerarDoc.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGenerarDocActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
            jPanel46.setLayout(jPanel46Layout);
            jPanel46Layout.setHorizontalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnGenerarDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
            );
            jPanel46Layout.setVerticalGroup(
                jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnGenerarDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 56, Short.MAX_VALUE)
            );

            getContentPane().add(jPanel46);
            jPanel46.setBounds(1080, 614, 230, 60);

            jPanel32.setBackground(new java.awt.Color(255, 255, 255));
            jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

            jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel26.setForeground(new java.awt.Color(255, 51, 51));
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setText("Descripción de motivo o sustento");

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
            txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDescripcion.setBorder(null);
            txtDescripcion.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtDescripcionCaretUpdate(evt);
                }
            });
            txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtDescripcionActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
            panelCPT13.setLayout(panelCPT13Layout);
            panelCPT13Layout.setHorizontalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
            jPanel32.setLayout(jPanel32Layout);
            jPanel32Layout.setHorizontalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel32Layout.setVerticalGroup(
                jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel32Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel26)
                    .addGap(3, 3, 3)
                    .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            getContentPane().add(jPanel32);
            jPanel32.setBounds(20, 610, 1050, 68);

            panelCPT50.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtBuscarDocumento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtBuscarDocumento.setForeground(new java.awt.Color(51, 51, 51));
            txtBuscarDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtBuscarDocumento.setBorder(null);
            txtBuscarDocumento.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscarDocumentoCaretUpdate(evt);
                }
            });
            txtBuscarDocumento.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscarDocumentoActionPerformed(evt);
                }
            });
            txtBuscarDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtBuscarDocumentoKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT50Layout = new javax.swing.GroupLayout(panelCPT50);
            panelCPT50.setLayout(panelCPT50Layout);
            panelCPT50Layout.setHorizontalGroup(
                panelCPT50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            );
            panelCPT50Layout.setVerticalGroup(
                panelCPT50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
            );

            getContentPane().add(panelCPT50);
            panelCPT50.setBounds(250, 100, 220, 30);

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));
            jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            jPanel8.setDoubleBuffered(false);
            jPanel8.setFocusable(false);

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(255, 51, 51));
            jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel6.setText("Nro Baja");

            lblNroBaja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNroBaja.setForeground(new java.awt.Color(102, 102, 102));
            lblNroBaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addComponent(lblNroBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblNroBaja)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            getContentPane().add(jPanel8);
            jPanel8.setBounds(20, 530, 140, 68);

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
            jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
            jPanel9.setDoubleBuffered(false);
            jPanel9.setFocusable(false);

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(255, 51, 51));
            jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel8.setText("Nro Documento Baja");

            txtSerie.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtSerie.setForeground(new java.awt.Color(51, 51, 51));
            txtSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            txtSerie1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
            txtSerie1.setForeground(new java.awt.Color(51, 51, 51));
            txtSerie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            txtSerie1.setText("-");

            lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            lblNroCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
            lblNroCorrelativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSerie1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSerie1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSerie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            getContentPane().add(jPanel9);
            jPanel9.setBounds(860, 530, 210, 70);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void cbxDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoActionPerformed

    private void cbxDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDocumentoItemStateChanged
//        if(cbxDocumento.getSelectedIndex()==0){
//            cbxSerie.setSelectedItem("F001");
//            lblNroCorrelativo.setText("00000001");
//        } else
//        if(cbxDocumento.getSelectedIndex()==1){
//            cbxSerie.setSelectedItem("B001");
//            lblNroCorrelativo.setText("00000002");
//        }    
    }//GEN-LAST:event_cbxDocumentoItemStateChanged

    private void btnGenerarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDocActionPerformed
        int estado=0; 
        String correlativo="";
        AdmisionEmergenciaCabecera cabecera = new AdmisionEmergenciaCabecera();
         if(txtDescripcion.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Describa el Motivo o Sustento de la Comunicación de Baja");
         }
         else if(!lblIdFactura.getText().equals("")){
                CuentasPorPagarComunicacionDeBaja comunicacion = new CuentasPorPagarComunicacionDeBaja();
                    comunicacion.setIdFactura(Integer.parseInt(lblIdFactura.getText()));
                    comunicacion.setDescripcion(txtDescripcion.getText());
                   CuentasPorPagarComunicacionDeBaja serie=new CuentasPorPagarComunicacionDeBaja();
                    lblNroBaja.setText(serie.generarNroBaja());
                    comunicacion.setNumero(lblNroBaja.getText());
                    
                    comunicacion.setCod_usu(cabecera.codUsuario(lblUsu.getText()));
            if(comunicacion.mantenimientoCuentasPorPagarComunicacionBaja()){
                  
       String archivo = "20410275768" + "-" + 
               "RA" + "-" +
               lblFechaEmision.getText().toString().substring(0, 4)+ 
               lblFechaEmision.getText().toString().substring(5, 7)+
               lblFechaEmision.getText().toString().substring(8, 10)+"-" + 
                lblNroBaja.getText() + ".CBA";
//        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(archivo);
        if(lblIdFactura.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No hay ID");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    crea.format(lblFechaDocumento.getText()+"|"+
                            lblFechaEmision.getText()+"|"+
                            String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(0)) + 
                    String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(1))+"|"+
                    txtSerie.getText() + "-" + 
                    lblNroCorrelativo.getText() +"|"+
                    txtDescripcion.getText()); 
                    crea.close();
                     estado=1;
                }   
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "No se pudo"+e.getMessage());
            }
        }
               
            }
                if(estado==1){
                    JOptionPane.showMessageDialog(this, "La Comunicación de Baja fue generada");
                    CuentasPorPagarComunicacionDeBaja est=new CuentasPorPagarComunicacionDeBaja();
                    est.CuentasPorPagarFacturaEstado(Integer.parseInt(lblIdFactura.getText()),"3");
                    limpiar();
                }
        }
        else{
                                JOptionPane.showMessageDialog(this, "ERROR");

        }
    }//GEN-LAST:event_btnGenerarDocActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtDescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionCaretUpdate

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void cbxBuscarDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoItemStateChanged

    private void cbxBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoCaretUpdate
        if(cbxBuscarDocumento.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "2");
        }else if(cbxBuscarDocumento.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "3");
        }if(cbxBuscarDocumento.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar(txtBuscarDocumento.getText(), "4");
        }
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
    }//GEN-LAST:event_txtBuscarDocumentoCaretUpdate

    private void txtBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BUSCAR_FACTURA_BOLETA.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_Factura_BoletaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaKeyPressed

    }//GEN-LAST:event_tb_Factura_BoletaKeyPressed

    private void tb_Factura_BoletaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaKeyReleased
      if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            
                int filaselec=tb_Factura_Boleta.getSelectedRow();
                lblIdFactura.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                    lblFechaDocumento.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                    cbxDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());     
                    txtSerie.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                    lblNroCorrelativo.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                             
        }
    }//GEN-LAST:event_tb_Factura_BoletaKeyReleased

    private void tb_Factura_BoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaMouseClicked
            int filaselec=tb_Factura_Boleta.getSelectedRow();
            lblIdFactura.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                    lblFechaDocumento.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                    cbxDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());     
                    txtSerie.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                    lblNroCorrelativo.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                      
    }//GEN-LAST:event_tb_Factura_BoletaMouseClicked

    
     public void mostrarFacturacionDetalle( JTable table){
        
        try {
            int filaselec=tb_Factura_Boleta.getSelectedRow();

        
          String consulta="";
            table.setModel(new DefaultTableModel());
            String titulos[]={"Codigo CPT","Descripción","Valor U", "Cantidad",
                "Precio Venta","IGV","Descuento Total","Total","Codigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];

            Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_FACTURA_DETALLE_listar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                m.addRow(fila);
                c++;
            }
            table.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            table.setRowSorter(elQueOrdena);
        
            
            
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
     
     public void formatoFacturacionDetalle( JTable table){
         table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(700);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(60);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(60);
        table.getColumnModel().getColumn(7).setPreferredWidth(60);
         table.getColumnModel().getColumn(8).setMinWidth(0);
            table.getColumnModel().getColumn(8).setMaxWidth(0);
     }
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
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComunicacionBaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComunicacionBaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_FACTURA_BOLETA;
    private javax.swing.JPopupMenu Serie;
    private javax.swing.JButton btnGenerarDoc;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JComboBox cbxBuscarDocumento;
    private javax.swing.JComboBox cbxDocumento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaDocumento;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblIdFactura;
    private javax.swing.JLabel lblNroBaja;
    public static javax.swing.JLabel lblNroCorrelativo;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JTable tb_Factura_Boleta;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JLabel txtSerie;
    public static javax.swing.JLabel txtSerie1;
    // End of variables declaration//GEN-END:variables
}
