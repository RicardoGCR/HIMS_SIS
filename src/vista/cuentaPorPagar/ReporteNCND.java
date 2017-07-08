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
public class ReporteNCND extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
     CuentasPorPagarNotaDeCreditoCabecera serie = new CuentasPorPagarNotaDeCreditoCabecera();
    public ReporteNCND() {
        initComponents();
        c.conectar();
       this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
       
       
        
//        tbFacturacion.getTableHeader().setVisible(false);
//        tbFacturacion.setTableHeader(null);
     
        cbxBuscarDocumento.setBackground(Color.WHITE);
        cbxBuscarDocumentoD.setBackground(Color.WHITE);
        
        BUSCAR_FACTURA_BOLETA.setLocationRelativeTo(null);
        BUSCAR_FACTURA_BOLETA.getContentPane().setBackground(Color.white);
        
        //CREDITO
        CUENTAS_POR_PAGAR_NOTA_CREDITO_formato();
        //DEBITO
        
        CUENTAS_POR_PAGAR_NOTA_DEBITO_formato();
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
            tb_Nota_Credito.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Nota_Credito.setRowSorter(elQueOrdena);
            this.tb_Nota_Credito.setModel(m);
    } catch (Exception e) {
    }
    }
    
    public void CUENTAS_POR_PAGAR_NOTA_CREDITO_formato(){
        tb_Nota_Credito.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Nota_Credito.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Nota_Credito.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Nota_Credito.getColumnModel().getColumn(5).setPreferredWidth(110);
    tb_Nota_Credito.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Nota_Credito.getColumnModel().getColumn(7).setPreferredWidth(160);
    tb_Nota_Credito.getColumnModel().getColumn(8).setPreferredWidth(120);
    tb_Nota_Credito.getColumnModel().getColumn(9).setPreferredWidth(120);
    tb_Nota_Credito.getColumnModel().getColumn(11).setPreferredWidth(160);
    //Ocultar    
    tb_Nota_Credito.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Nota_Credito.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Nota_Credito.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Nota_Credito.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Nota_Credito.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Nota_Credito.getColumnModel().getColumn(10).setMaxWidth(0);
    
    }
    
    public void CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Serie/Correlativo ND","Fecha de Emision ND","CPF_ID","Serie/Correlativo"
                     , "Fecha de Emision","Representante","Correo","Tipo Documento","Nro Documento","Moneda","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_NOTA_DEBITO_listar ?,?";

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
            tb_Nota_Debito.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Nota_Debito.setRowSorter(elQueOrdena);
            this.tb_Nota_Debito.setModel(m);
    } catch (Exception e) {
    }
    }
    
    public void CUENTAS_POR_PAGAR_NOTA_DEBITO_formato(){
    tb_Nota_Debito.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Nota_Debito.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Nota_Debito.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Nota_Debito.getColumnModel().getColumn(5).setPreferredWidth(110);
    tb_Nota_Debito.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Nota_Debito.getColumnModel().getColumn(7).setPreferredWidth(160);
    tb_Nota_Debito.getColumnModel().getColumn(8).setPreferredWidth(120);
    tb_Nota_Debito.getColumnModel().getColumn(9).setPreferredWidth(120);
     tb_Nota_Debito.getColumnModel().getColumn(11).setPreferredWidth(120);
    //Ocultar    
    tb_Nota_Debito.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Nota_Debito.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Nota_Debito.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Nota_Debito.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Nota_Debito.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Nota_Debito.getColumnModel().getColumn(10).setMaxWidth(0);
    
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
        jPanel4 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        cbxBuscarDocumento = new javax.swing.JComboBox();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        panelCPT50 = new javax.swing.JPanel();
        txtBuscarDocumento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Nota_Credito = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_NotaCreditoDet = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel6 = new javax.swing.JPanel();
                cbxBuscarDocumentoD = new javax.swing.JComboBox();
                jLabel70 = new javax.swing.JLabel();
                jLabel71 = new javax.swing.JLabel();
                panelCPT51 = new javax.swing.JPanel();
                txtBuscarDocumentoND = new javax.swing.JTextField();
                jScrollPane5 = new javax.swing.JScrollPane();
                tb_NotaDebitoDet = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jScrollPane6 = new javax.swing.JScrollPane();
                    tb_Nota_Debito = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        lblUsu = new javax.swing.JLabel();

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 721, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7))
                        );

                        getContentPane().add(jPanel1);
                        jPanel1.setBounds(25, 9, 1334, 70);

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

                        tb_Nota_Credito.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "ID", "Serie/Correlativo NC", "Fecha de Emision NC", "CPF_ID", "Serie/Correlativo", "Fecha de Emision", "Representante", "Correo", "Tipo Documento", "Nro Documento", "Moneda", "Descripción"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, true, true, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_Nota_Credito.setRowHeight(25);
                        tb_Nota_Credito.getTableHeader().setReorderingAllowed(false);
                        tb_Nota_Credito.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_Nota_CreditoMouseClicked(evt);
                            }
                        });
                        tb_Nota_Credito.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Nota_CreditoKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                tb_Nota_CreditoKeyReleased(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tb_Nota_Credito);

                        jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tb_NotaCreditoDet.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {
                                {},
                                {},
                                {},
                                {}
                            },
                            new String [] {

                            }
                        ));
                        tb_NotaCreditoDet.setRowHeight(25);
                        tb_NotaCreditoDet.getTableHeader().setReorderingAllowed(false);
                        tb_NotaCreditoDet.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_NotaCreditoDetMouseClicked(evt);
                            }
                        });
                        tb_NotaCreditoDet.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_NotaCreditoDetKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                tb_NotaCreditoDetKeyReleased(evt);
                            }
                        });
                        jScrollPane3.setViewportView(tb_NotaCreditoDet);

                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel5Layout.setHorizontalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel69)
                                        .addGap(65, 65, 65)
                                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1329, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))
                                .addGap(31, 31, 31))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("tab1", jPanel5);

                        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                        cbxBuscarDocumentoD.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        cbxBuscarDocumentoD.setForeground(new java.awt.Color(102, 102, 102));
                        cbxBuscarDocumentoD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "01 FACTURA", "03 BOLETA" }));
                        cbxBuscarDocumentoD.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                        cbxBuscarDocumentoD.addItemListener(new java.awt.event.ItemListener() {
                            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                cbxBuscarDocumentoDItemStateChanged(evt);
                            }
                        });
                        cbxBuscarDocumentoD.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cbxBuscarDocumentoDActionPerformed(evt);
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

                        txtBuscarDocumentoND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                        txtBuscarDocumentoND.setForeground(new java.awt.Color(51, 51, 51));
                        txtBuscarDocumentoND.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtBuscarDocumentoND.setBorder(null);
                        txtBuscarDocumentoND.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                txtBuscarDocumentoNDCaretUpdate(evt);
                            }
                        });
                        txtBuscarDocumentoND.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarDocumentoNDActionPerformed(evt);
                            }
                        });
                        txtBuscarDocumentoND.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarDocumentoNDKeyTyped(evt);
                            }
                        });

                        javax.swing.GroupLayout panelCPT51Layout = new javax.swing.GroupLayout(panelCPT51);
                        panelCPT51.setLayout(panelCPT51Layout);
                        panelCPT51Layout.setHorizontalGroup(
                            panelCPT51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarDocumentoND, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        );
                        panelCPT51Layout.setVerticalGroup(
                            panelCPT51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarDocumentoND, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        );

                        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tb_NotaDebitoDet.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {
                                {},
                                {},
                                {},
                                {}
                            },
                            new String [] {

                            }
                        ));
                        tb_NotaDebitoDet.setRowHeight(25);
                        tb_NotaDebitoDet.getTableHeader().setReorderingAllowed(false);
                        tb_NotaDebitoDet.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_NotaDebitoDetMouseClicked(evt);
                            }
                        });
                        tb_NotaDebitoDet.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_NotaDebitoDetKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                tb_NotaDebitoDetKeyReleased(evt);
                            }
                        });
                        jScrollPane5.setViewportView(tb_NotaDebitoDet);

                        jScrollPane6.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                        tb_Nota_Debito.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "ID", "Serie/Correlativo ND", "Fecha de Emision ND", "CPF_ID", "Serie/Correlativo", "Fecha de Emision", "Representante", "Correo", "Tipo Documento", "Nro Documento", "Moneda", "Descripción"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_Nota_Debito.setRowHeight(25);
                        tb_Nota_Debito.getTableHeader().setReorderingAllowed(false);
                        tb_Nota_Debito.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_Nota_DebitoMouseClicked(evt);
                            }
                        });
                        tb_Nota_Debito.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Nota_DebitoKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                tb_Nota_DebitoKeyReleased(evt);
                            }
                        });
                        jScrollPane6.setViewportView(tb_Nota_Debito);

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumentoD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel70)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxBuscarDocumentoD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel71))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        jTabbedPane1.addTab("tab1", jPanel6);

                        getContentPane().add(jTabbedPane1);
                        jTabbedPane1.setBounds(30, 100, 1330, 630);

                        lblUsu.setText("Silvana");
                        getContentPane().add(lblUsu);
                        lblUsu.setBounds(1300, 90, 40, 10);

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

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

    private void tb_Nota_CreditoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Nota_CreditoKeyPressed

    }//GEN-LAST:event_tb_Nota_CreditoKeyPressed

    private void tb_Nota_CreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Nota_CreditoKeyReleased
 if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            if( tb_Nota_Credito.getRowCount()>0){
                int filaselec=tb_Nota_Credito.getSelectedRow();
                String cpf_id=tb_Nota_Credito.getValueAt(filaselec, 3).toString();
                CUENTAS_POR_PAGAR_NOTAS_CREDITO_DEBITO_DETALLE(tb_NotaCreditoDet, cpf_id);
            }
        }     
    }//GEN-LAST:event_tb_Nota_CreditoKeyReleased

    private void tb_Nota_CreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Nota_CreditoMouseClicked
            if( tb_Nota_Credito.getRowCount()>0){
                int filaselec=tb_Nota_Credito.getSelectedRow();
                String cpf_id=tb_Nota_Credito.getValueAt(filaselec, 3).toString();
                CUENTAS_POR_PAGAR_NOTAS_CREDITO_DEBITO_DETALLE(tb_NotaCreditoDet, cpf_id);
            }
                  
    }//GEN-LAST:event_tb_Nota_CreditoMouseClicked

    private void tb_NotaCreditoDetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NotaCreditoDetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaCreditoDetMouseClicked

    private void tb_NotaCreditoDetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NotaCreditoDetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaCreditoDetKeyPressed

    private void tb_NotaCreditoDetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NotaCreditoDetKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaCreditoDetKeyReleased

    private void cbxBuscarDocumentoDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoDItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoDItemStateChanged

    private void cbxBuscarDocumentoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoDActionPerformed

    private void txtBuscarDocumentoNDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoNDCaretUpdate
      if(cbxBuscarDocumentoD.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(txtBuscarDocumentoND.getText(), "2");
        }else if(cbxBuscarDocumentoD.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(txtBuscarDocumentoND.getText(), "3");
        }if(cbxBuscarDocumentoD.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(txtBuscarDocumentoND.getText(), "4");
        }
        CUENTAS_POR_PAGAR_NOTA_DEBITO_formato();
    }//GEN-LAST:event_txtBuscarDocumentoNDCaretUpdate

    private void txtBuscarDocumentoNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoNDActionPerformed

    private void txtBuscarDocumentoNDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoNDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarDocumentoNDKeyTyped

    private void tb_NotaDebitoDetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NotaDebitoDetMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaDebitoDetMouseClicked

    private void tb_NotaDebitoDetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NotaDebitoDetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaDebitoDetKeyPressed

    private void tb_NotaDebitoDetKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NotaDebitoDetKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NotaDebitoDetKeyReleased

    private void tb_Nota_DebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Nota_DebitoMouseClicked
         if( tb_Nota_Debito.getRowCount()>0){
                int filaselec=tb_Nota_Debito.getSelectedRow();
                String cpf_id=tb_Nota_Debito.getValueAt(filaselec, 3).toString();
                CUENTAS_POR_PAGAR_NOTAS_CREDITO_DEBITO_DETALLE(tb_NotaDebitoDet, cpf_id);
            }
    }//GEN-LAST:event_tb_Nota_DebitoMouseClicked

    private void tb_Nota_DebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Nota_DebitoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Nota_DebitoKeyPressed

    private void tb_Nota_DebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Nota_DebitoKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            if( tb_Nota_Debito.getRowCount()>0){
                int filaselec=tb_Nota_Debito.getSelectedRow();
                String cpf_id=tb_Nota_Debito.getValueAt(filaselec, 3).toString();
                CUENTAS_POR_PAGAR_NOTAS_CREDITO_DEBITO_DETALLE(tb_NotaDebitoDet, cpf_id);
            }
        }   
    }//GEN-LAST:event_tb_Nota_DebitoKeyReleased

    public void CUENTAS_POR_PAGAR_NOTAS_CREDITO_DEBITO_DETALLE(JTable table,String cpf_id ){
          try {
            String consulta="";
             String titulos[]={"Unidad Medida","Código","Descripcion","Valor Unitario",
                 "Cantidad","Precio Venta","IGV","Descuento","Total Venta"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_NOTA_CREDITO_DEBITO_detalle ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, cpf_id);
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
            }
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
            java.util.logging.Logger.getLogger(ReporteNCND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteNCND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteNCND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteNCND.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new ReporteNCND().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_FACTURA_BOLETA;
    private javax.swing.JPopupMenu Serie;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JComboBox cbxBuscarDocumento;
    private javax.swing.JComboBox cbxBuscarDocumentoD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JPanel panelCPT51;
    private javax.swing.JTable tb_NotaCreditoDet;
    private javax.swing.JTable tb_NotaDebitoDet;
    private javax.swing.JTable tb_Nota_Credito;
    private javax.swing.JTable tb_Nota_Debito;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtBuscarDocumentoND;
    // End of variables declaration//GEN-END:variables
}
