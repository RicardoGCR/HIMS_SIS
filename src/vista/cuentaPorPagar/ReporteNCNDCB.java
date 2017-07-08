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
public class ReporteNCNDCB extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
     CuentasPorPagarNotaDeCreditoCabecera serie = new CuentasPorPagarNotaDeCreditoCabecera();
    public ReporteNCNDCB() {
        initComponents();
        c.conectar();
       this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
       
       
        
//        tbFacturacion.getTableHeader().setVisible(false);
//        tbFacturacion.setTableHeader(null);
     
        cbxBuscarDocumento.setBackground(Color.WHITE);
        
        BUSCAR_FACTURA_BOLETA.setLocationRelativeTo(null);
        BUSCAR_FACTURA_BOLETA.getContentPane().setBackground(Color.white);
        
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar("","1");
        CUENTAS_POR_PAGAR_NOTA_CREDITO_formato();
        
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
       
        //CREDITO
        lblIdFactura.setText("");
      
      
    }
    
     public void CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Serie/Correlativo NC","Fecha de Emision NC","CPF_ID","Serie/Correlativo"
                     , "Fecha de Emision","Representante","Correo","Tipo Documento","Nro Documento","Moneda","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_NOTA_CREDITO_listar ?,?";

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
    
    public void CUENTAS_POR_PAGAR_NOTA_CREDITO_formato(){
        tb_Factura_Boleta.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Factura_Boleta.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(5).setPreferredWidth(110);
    tb_Factura_Boleta.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Factura_Boleta.getColumnModel().getColumn(7).setPreferredWidth(160);
    tb_Factura_Boleta.getColumnModel().getColumn(8).setPreferredWidth(160);
    tb_Factura_Boleta.getColumnModel().getColumn(9).setPreferredWidth(160);
    //Ocultar    
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Factura_Boleta.getColumnModel().getColumn(10).setMaxWidth(0);
    
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
        lblUsu = new javax.swing.JLabel();
        lblIdFactura = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        btnGenerarDoc = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        cbxBuscarDocumento = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        panelCPT50 = new javax.swing.JPanel();
        txtBuscarDocumento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Factura_Boleta = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Factura_Boleta1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel6 = new javax.swing.JPanel();
                cbxBuscarDocumento1 = new javax.swing.JComboBox();
                jLabel70 = new javax.swing.JLabel();
                jLabel71 = new javax.swing.JLabel();
                panelCPT51 = new javax.swing.JPanel();
                txtBuscarDocumento1 = new javax.swing.JTextField();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_Factura_Boleta2 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Factura_Boleta3 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jPanel7 = new javax.swing.JPanel();
                        cbxBuscarDocumento2 = new javax.swing.JComboBox();
                        jLabel72 = new javax.swing.JLabel();
                        jLabel73 = new javax.swing.JLabel();
                        panelCPT52 = new javax.swing.JPanel();
                        txtBuscarDocumento2 = new javax.swing.JTextField();
                        jScrollPane6 = new javax.swing.JScrollPane();
                        tb_Factura_Boleta4 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jScrollPane7 = new javax.swing.JScrollPane();
                            tb_Factura_Boleta5 = new javax.swing.JTable(){
                                public boolean isCellEditable(int rowIndex, int colIndex){
                                    return false; //Disallow the editing of any cell
                                }};

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

                                lblUsu.setText("Silvana");
                                getContentPane().add(lblUsu);
                                lblUsu.setBounds(1290, 610, 40, 10);
                                getContentPane().add(lblIdFactura);
                                lblIdFactura.setBounds(480, 690, 0, 0);

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
                                    .addComponent(btnGenerarDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, Short.MAX_VALUE)
                                );
                                jPanel46Layout.setVerticalGroup(
                                    jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel46Layout.createSequentialGroup()
                                        .addComponent(btnGenerarDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                                        .addContainerGap())
                                );

                                getContentPane().add(jPanel46);
                                jPanel46.setBounds(1270, 530, 70, 60);

                                jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

                                jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel69.setText("Nro de Documento ");

                                jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel68.setText("Documento");

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

                                jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Factura_Boleta1.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Factura_Boleta1.setRowHeight(25);
                                tb_Factura_Boleta1.getTableHeader().setReorderingAllowed(false);
                                tb_Factura_Boleta1.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Factura_Boleta1MouseClicked(evt);
                                    }
                                });
                                tb_Factura_Boleta1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta1KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta1KeyReleased(evt);
                                    }
                                });
                                jScrollPane3.setViewportView(tb_Factura_Boleta1);

                                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                                jPanel5.setLayout(jPanel5Layout);
                                jPanel5Layout.setHorizontalGroup(
                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel69)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                                );
                                jPanel5Layout.setVerticalGroup(
                                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel69)
                                            .addComponent(jLabel68))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                jTabbedPane1.addTab("tab1", jPanel5);

                                jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                                cbxBuscarDocumento1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                cbxBuscarDocumento1.setForeground(new java.awt.Color(102, 102, 102));
                                cbxBuscarDocumento1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01 FACTURA", "03 BOLETA" }));
                                cbxBuscarDocumento1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                cbxBuscarDocumento1.addItemListener(new java.awt.event.ItemListener() {
                                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                        cbxBuscarDocumento1ItemStateChanged(evt);
                                    }
                                });
                                cbxBuscarDocumento1.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        cbxBuscarDocumento1ActionPerformed(evt);
                                    }
                                });

                                jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel70.setText("Nro de Documento ");

                                jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel71.setText("Documento");

                                panelCPT51.setBackground(new java.awt.Color(255, 255, 255));
                                panelCPT51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtBuscarDocumento1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtBuscarDocumento1.setForeground(new java.awt.Color(51, 51, 51));
                                txtBuscarDocumento1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtBuscarDocumento1.setBorder(null);
                                txtBuscarDocumento1.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarDocumento1CaretUpdate(evt);
                                    }
                                });
                                txtBuscarDocumento1.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        txtBuscarDocumento1ActionPerformed(evt);
                                    }
                                });
                                txtBuscarDocumento1.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtBuscarDocumento1KeyTyped(evt);
                                    }
                                });

                                javax.swing.GroupLayout panelCPT51Layout = new javax.swing.GroupLayout(panelCPT51);
                                panelCPT51.setLayout(panelCPT51Layout);
                                panelCPT51Layout.setHorizontalGroup(
                                    panelCPT51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarDocumento1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                );
                                panelCPT51Layout.setVerticalGroup(
                                    panelCPT51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarDocumento1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                );

                                jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Factura_Boleta2.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Factura_Boleta2.setRowHeight(25);
                                tb_Factura_Boleta2.getTableHeader().setReorderingAllowed(false);
                                tb_Factura_Boleta2.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Factura_Boleta2MouseClicked(evt);
                                    }
                                });
                                tb_Factura_Boleta2.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta2KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta2KeyReleased(evt);
                                    }
                                });
                                jScrollPane4.setViewportView(tb_Factura_Boleta2);

                                jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Factura_Boleta3.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Factura_Boleta3.setRowHeight(25);
                                tb_Factura_Boleta3.getTableHeader().setReorderingAllowed(false);
                                tb_Factura_Boleta3.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Factura_Boleta3MouseClicked(evt);
                                    }
                                });
                                tb_Factura_Boleta3.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta3KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta3KeyReleased(evt);
                                    }
                                });
                                jScrollPane5.setViewportView(tb_Factura_Boleta3);

                                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                                jPanel6.setLayout(jPanel6Layout);
                                jPanel6Layout.setHorizontalGroup(
                                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel70)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                                );
                                jPanel6Layout.setVerticalGroup(
                                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxBuscarDocumento1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel70)
                                            .addComponent(jLabel71))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                jTabbedPane1.addTab("tab1", jPanel6);

                                jPanel7.setBackground(new java.awt.Color(255, 255, 255));

                                cbxBuscarDocumento2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                cbxBuscarDocumento2.setForeground(new java.awt.Color(102, 102, 102));
                                cbxBuscarDocumento2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01 FACTURA", "03 BOLETA" }));
                                cbxBuscarDocumento2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                                cbxBuscarDocumento2.addItemListener(new java.awt.event.ItemListener() {
                                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                        cbxBuscarDocumento2ItemStateChanged(evt);
                                    }
                                });
                                cbxBuscarDocumento2.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        cbxBuscarDocumento2ActionPerformed(evt);
                                    }
                                });

                                jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel72.setText("Nro de Documento ");

                                jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                                jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                                jLabel73.setText("Documento");

                                panelCPT52.setBackground(new java.awt.Color(255, 255, 255));
                                panelCPT52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                                txtBuscarDocumento2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                                txtBuscarDocumento2.setForeground(new java.awt.Color(51, 51, 51));
                                txtBuscarDocumento2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                                txtBuscarDocumento2.setBorder(null);
                                txtBuscarDocumento2.addCaretListener(new javax.swing.event.CaretListener() {
                                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                        txtBuscarDocumento2CaretUpdate(evt);
                                    }
                                });
                                txtBuscarDocumento2.addActionListener(new java.awt.event.ActionListener() {
                                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                                        txtBuscarDocumento2ActionPerformed(evt);
                                    }
                                });
                                txtBuscarDocumento2.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyTyped(java.awt.event.KeyEvent evt) {
                                        txtBuscarDocumento2KeyTyped(evt);
                                    }
                                });

                                javax.swing.GroupLayout panelCPT52Layout = new javax.swing.GroupLayout(panelCPT52);
                                panelCPT52.setLayout(panelCPT52Layout);
                                panelCPT52Layout.setHorizontalGroup(
                                    panelCPT52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarDocumento2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                );
                                panelCPT52Layout.setVerticalGroup(
                                    panelCPT52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarDocumento2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                );

                                jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Factura_Boleta4.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Factura_Boleta4.setRowHeight(25);
                                tb_Factura_Boleta4.getTableHeader().setReorderingAllowed(false);
                                tb_Factura_Boleta4.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Factura_Boleta4MouseClicked(evt);
                                    }
                                });
                                tb_Factura_Boleta4.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta4KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta4KeyReleased(evt);
                                    }
                                });
                                jScrollPane6.setViewportView(tb_Factura_Boleta4);

                                jScrollPane7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                                tb_Factura_Boleta5.setModel(new javax.swing.table.DefaultTableModel(
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
                                tb_Factura_Boleta5.setRowHeight(25);
                                tb_Factura_Boleta5.getTableHeader().setReorderingAllowed(false);
                                tb_Factura_Boleta5.addMouseListener(new java.awt.event.MouseAdapter() {
                                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                                        tb_Factura_Boleta5MouseClicked(evt);
                                    }
                                });
                                tb_Factura_Boleta5.addKeyListener(new java.awt.event.KeyAdapter() {
                                    public void keyPressed(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta5KeyPressed(evt);
                                    }
                                    public void keyReleased(java.awt.event.KeyEvent evt) {
                                        tb_Factura_Boleta5KeyReleased(evt);
                                    }
                                });
                                jScrollPane7.setViewportView(tb_Factura_Boleta5);

                                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                                jPanel7.setLayout(jPanel7Layout);
                                jPanel7Layout.setHorizontalGroup(
                                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel72)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1350, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap())
                                );
                                jPanel7Layout.setVerticalGroup(
                                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxBuscarDocumento2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(panelCPT52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel72)
                                            .addComponent(jLabel73))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                jTabbedPane1.addTab("tab1", jPanel7);

                                getContentPane().add(jTabbedPane1);
                                jTabbedPane1.setBounds(30, 100, 1330, 630);

                                pack();
                            }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void cbxBuscarDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoItemStateChanged

    private void cbxBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoCaretUpdate
        if(cbxBuscarDocumento.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(txtBuscarDocumento.getText(), "2");
        }else if(cbxBuscarDocumento.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(txtBuscarDocumento.getText(), "3");
        }if(cbxBuscarDocumento.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(txtBuscarDocumento.getText(), "4");
        }
        CUENTAS_POR_PAGAR_NOTA_CREDITO_formato();
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
                 
        }
    }//GEN-LAST:event_tb_Factura_BoletaKeyReleased

    private void tb_Factura_BoletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_BoletaMouseClicked
            int filaselec=tb_Factura_Boleta.getSelectedRow();
            lblIdFactura.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                     
    }//GEN-LAST:event_tb_Factura_BoletaMouseClicked

    private void btnGenerarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDocActionPerformed
      
    }//GEN-LAST:event_btnGenerarDocActionPerformed

    private void tb_Factura_Boleta1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta1MouseClicked

    private void tb_Factura_Boleta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta1KeyPressed

    private void tb_Factura_Boleta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta1KeyReleased

    private void cbxBuscarDocumento1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumento1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumento1ItemStateChanged

    private void cbxBuscarDocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumento1ActionPerformed

    private void txtBuscarDocumento1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumento1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento1CaretUpdate

    private void txtBuscarDocumento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDocumento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento1ActionPerformed

    private void txtBuscarDocumento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocumento1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento1KeyTyped

    private void tb_Factura_Boleta2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta2MouseClicked

    private void tb_Factura_Boleta2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta2KeyPressed

    private void tb_Factura_Boleta2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta2KeyReleased

    private void tb_Factura_Boleta3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta3MouseClicked

    private void tb_Factura_Boleta3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta3KeyPressed

    private void tb_Factura_Boleta3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta3KeyReleased

    private void cbxBuscarDocumento2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumento2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumento2ItemStateChanged

    private void cbxBuscarDocumento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumento2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumento2ActionPerformed

    private void txtBuscarDocumento2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumento2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento2CaretUpdate

    private void txtBuscarDocumento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDocumento2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento2ActionPerformed

    private void txtBuscarDocumento2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocumento2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumento2KeyTyped

    private void tb_Factura_Boleta4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta4MouseClicked

    private void tb_Factura_Boleta4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta4KeyPressed

    private void tb_Factura_Boleta4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta4KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta4KeyReleased

    private void tb_Factura_Boleta5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta5MouseClicked

    private void tb_Factura_Boleta5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta5KeyPressed

    private void tb_Factura_Boleta5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Factura_Boleta5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Factura_Boleta5KeyReleased

    
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
            java.util.logging.Logger.getLogger(ReporteNCNDCB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteNCNDCB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteNCNDCB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteNCNDCB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReporteNCNDCB().setVisible(true);
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
    private javax.swing.JComboBox cbxBuscarDocumento1;
    private javax.swing.JComboBox cbxBuscarDocumento2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIdFactura;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JPanel panelCPT51;
    private javax.swing.JPanel panelCPT52;
    private javax.swing.JTable tb_Factura_Boleta;
    private javax.swing.JTable tb_Factura_Boleta1;
    private javax.swing.JTable tb_Factura_Boleta2;
    private javax.swing.JTable tb_Factura_Boleta3;
    private javax.swing.JTable tb_Factura_Boleta4;
    private javax.swing.JTable tb_Factura_Boleta5;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtBuscarDocumento1;
    public static javax.swing.JTextField txtBuscarDocumento2;
    // End of variables declaration//GEN-END:variables
}
