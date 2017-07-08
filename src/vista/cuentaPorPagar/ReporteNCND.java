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
import java.text.DecimalFormat;
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
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(0,0,"","1");
        CUENTAS_POR_PAGAR_NOTA_CREDITO_formato();
        //DEBITO
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(0,0,"","1");
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
    
     public void CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(int desde,int hasta,String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Serie/Correlativo NC","Fecha de Emision NC","CPF_ID","Serie/Correlativo"
                     , "Fecha de Emision","Representante","Correo","Tipo Documento","Nro Documento","Moneda","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_NOTA_CREDITO_listar ?,?,?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1, desde);
            cmd.setInt(2, hasta);
            cmd.setString(3, buscar);
            cmd.setString(4, tipo);
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
    
    public void CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(int desde,int hasta,String buscar,String tipo){
        try {
            String consulta="";
             String titulos[]={"ID","Serie/Correlativo ND","Fecha de Emision ND","CPF_ID","Serie/Correlativo"
                     , "Fecha de Emision","Representante","Correo","Tipo Documento","Nro Documento","Moneda","Descripción"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_CUENTAS_POR_PAGAR_NOTA_DEBITO_listar ?,?,?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1, desde);
            cmd.setInt(2, hasta);
            cmd.setString(3, buscar);
            cmd.setString(4, tipo);
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
        lblUsu = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel84 = new javax.swing.JPanel();
        lblLineCre = new javax.swing.JLabel();
        lblCredito = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
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
                lblEmpresa1 = new javax.swing.JLabel();
                dtFechaI = new com.toedter.calendar.JDateChooser();
                dtFechaF = new com.toedter.calendar.JDateChooser();
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
                        lblEmpresa2 = new javax.swing.JLabel();
                        dtFechaIND = new com.toedter.calendar.JDateChooser();
                        dtFechaFND = new com.toedter.calendar.JDateChooser();
                        jPanel85 = new javax.swing.JPanel();
                        lblLineDeb = new javax.swing.JLabel();
                        lblDebito = new javax.swing.JLabel();

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
                            .addGap(0, 730, Short.MAX_VALUE)
                        );

                        getContentPane().add(jPanel21);
                        jPanel21.setBounds(0, 0, 19, 730);

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
                        jLabel1.setText("<html><span style=\"font-size:'30px'\">Cuenta por Pagar - </span>Notas de Crédito y Débito</html>");

                        lblUsu.setText("Silvana");

                        jLabel2.setText("Usuario:");

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))))
                                .addContainerGap())
                        );

                        getContentPane().add(jPanel1);
                        jPanel1.setBounds(25, 9, 1334, 70);

                        jPanel84.setBackground(new java.awt.Color(255, 255, 255));

                        lblLineCre.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
                        lblLineCre.setForeground(new java.awt.Color(41, 127, 184));
                        lblLineCre.setText("__________________________________________________________");
                        lblLineCre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lblLineCre.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lblLineCreMouseClicked(evt);
                            }
                        });

                        lblCredito.setFont(new java.awt.Font("Segoe UI Semilight", 1, 15)); // NOI18N
                        lblCredito.setForeground(new java.awt.Color(102, 102, 102));
                        lblCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblCredito.setText("NOTA DE CRÉDITO");
                        lblCredito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                        javax.swing.GroupLayout jPanel84Layout = new javax.swing.GroupLayout(jPanel84);
                        jPanel84.setLayout(jPanel84Layout);
                        jPanel84Layout.setHorizontalGroup(
                            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 774, Short.MAX_VALUE)
                            .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel84Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(lblLineCre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap()))
                            .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel84Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                        );
                        jPanel84Layout.setVerticalGroup(
                            jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 30, Short.MAX_VALUE)
                            .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel84Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblLineCre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel84Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel84Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblCredito)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                        );

                        getContentPane().add(jPanel84);
                        jPanel84.setBounds(20, 90, 660, 30);

                        tab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

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
                        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
                        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel69.setText("Nro de Documento ");

                        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
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

                        tb_Nota_Credito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

                        tb_NotaCreditoDet.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

                        lblEmpresa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lblEmpresa1.setForeground(new java.awt.Color(51, 51, 51));
                        lblEmpresa1.setText("Rango de fechas");

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

                        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                        jPanel5.setLayout(jPanel5Layout);
                        jPanel5Layout.setHorizontalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(lblEmpresa1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel69)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1316, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane3)))
                                .addContainerGap(28, Short.MAX_VALUE))
                        );
                        jPanel5Layout.setVerticalGroup(
                            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelCPT50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dtFechaF, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                        .addComponent(lblEmpresa1)
                                        .addComponent(dtFechaI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel69)
                                    .addComponent(jLabel68))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(44, Short.MAX_VALUE))
                        );

                        tab.addTab("", jPanel5);

                        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

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

                        tb_NotaDebitoDet.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

                        tb_Nota_Debito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

                        lblEmpresa2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        lblEmpresa2.setForeground(new java.awt.Color(51, 51, 51));
                        lblEmpresa2.setText("Rango de fechas");

                        dtFechaIND.setBackground(new java.awt.Color(255, 255, 255));
                        dtFechaIND.setForeground(new java.awt.Color(51, 51, 51));
                        dtFechaIND.setDateFormatString("dd/MM/yyyy");
                        dtFechaIND.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                        dtFechaIND.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                dtFechaINDMouseClicked(evt);
                            }
                        });

                        dtFechaFND.setBackground(new java.awt.Color(255, 255, 255));
                        dtFechaFND.setForeground(new java.awt.Color(51, 51, 51));
                        dtFechaFND.setDateFormatString("dd/MM/yyyy");
                        dtFechaFND.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                        dtFechaFND.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                dtFechaFNDMouseClicked(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(lblEmpresa2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtFechaIND, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtFechaFND, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(cbxBuscarDocumentoD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel70)
                                                .addGap(65, 65, 65)
                                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 489, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane6))))
                                .addContainerGap())
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxBuscarDocumentoD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelCPT51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(dtFechaFND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblEmpresa2)
                                        .addComponent(dtFechaIND, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel71))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(61, Short.MAX_VALUE))
                        );

                        tab.addTab("", jPanel6);

                        getContentPane().add(tab);
                        tab.setBounds(30, 120, 1330, 620);

                        jPanel85.setBackground(new java.awt.Color(255, 255, 255));

                        lblLineDeb.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
                        lblLineDeb.setForeground(new java.awt.Color(255, 255, 255));
                        lblLineDeb.setText("___________________________________________________");
                        lblLineDeb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        lblLineDeb.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                lblLineDebMouseClicked(evt);
                            }
                        });

                        lblDebito.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                        lblDebito.setForeground(new java.awt.Color(102, 102, 102));
                        lblDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblDebito.setText("NOTA DE DÉBITO");
                        lblDebito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                        javax.swing.GroupLayout jPanel85Layout = new javax.swing.GroupLayout(jPanel85);
                        jPanel85.setLayout(jPanel85Layout);
                        jPanel85Layout.setHorizontalGroup(
                            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 684, Short.MAX_VALUE)
                            .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel85Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblLineDeb, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap()))
                            .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel85Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                        );
                        jPanel85Layout.setVerticalGroup(
                            jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 30, Short.MAX_VALUE)
                            .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel85Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblLineDeb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel85Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel85Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lblDebito)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                        );

                        getContentPane().add(jPanel85);
                        jPanel85.setBounds(680, 90, 680, 30);

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void cbxBuscarDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoItemStateChanged

    private void cbxBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarDocumentoActionPerformed

    private void txtBuscarDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarDocumentoCaretUpdate
        if(dtFechaI.getDate()==null || dtFechaF.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de Fechas.");
            }
        else{
        DecimalFormat df = new DecimalFormat("00");
        int dia,mes,anio,diah,mesh,anioh;
        dia = dtFechaI.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtFechaI.getCalendar().get(Calendar.MONTH) + 1;
        anio = dtFechaI.getCalendar().get(Calendar.YEAR);
        diah = dtFechaF.getCalendar().get(Calendar.DAY_OF_MONTH);
        mesh = dtFechaF.getCalendar().get(Calendar.MONTH) + 1;
        anioh = dtFechaF.getCalendar().get(Calendar.YEAR);
        
        int desde=Integer.parseInt(anio+df.format(mes)+df.format(dia));
        int hasta=Integer.parseInt(anioh+df.format(mesh)+df.format(diah));
        
        if(cbxBuscarDocumento.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(desde,hasta,txtBuscarDocumento.getText(), "2");
        }else if(cbxBuscarDocumento.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(desde,hasta,txtBuscarDocumento.getText(), "3");
        }if(cbxBuscarDocumento.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_NOTA_CREDITO_listar(desde,hasta,txtBuscarDocumento.getText(), "4");
        }
        CUENTAS_POR_PAGAR_NOTA_CREDITO_formato();
        }
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
     if(dtFechaIND.getDate()==null || dtFechaFND.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de Fechas.");
            }
        else{
        DecimalFormat df = new DecimalFormat("00");
        int dia,mes,anio,diah,mesh,anioh;
        dia = dtFechaIND.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dtFechaIND.getCalendar().get(Calendar.MONTH) + 1;
        anio = dtFechaIND.getCalendar().get(Calendar.YEAR);
        diah = dtFechaFND.getCalendar().get(Calendar.DAY_OF_MONTH);
        mesh = dtFechaFND.getCalendar().get(Calendar.MONTH) + 1;
        anioh = dtFechaFND.getCalendar().get(Calendar.YEAR);
        
        int desdeND=Integer.parseInt(anio+df.format(mes)+df.format(dia));
        int hastaND=Integer.parseInt(anioh+df.format(mesh)+df.format(diah));
        if(cbxBuscarDocumentoD.getSelectedIndex()==0){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(desdeND,hastaND,txtBuscarDocumentoND.getText(), "2");
        }else if(cbxBuscarDocumentoD.getSelectedIndex()==1){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(desdeND,hastaND,txtBuscarDocumentoND.getText(), "3");
        }if(cbxBuscarDocumentoD.getSelectedIndex()==2){
        CUENTAS_POR_PAGAR_NOTA_DEBITO_listar(desdeND,hastaND,txtBuscarDocumentoND.getText(), "4");
        }
        CUENTAS_POR_PAGAR_NOTA_DEBITO_formato();
     }
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

    private void dtFechaIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaIMouseClicked

    }//GEN-LAST:event_dtFechaIMouseClicked

    private void dtFechaFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaFMouseClicked

    }//GEN-LAST:event_dtFechaFMouseClicked

    private void dtFechaINDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaINDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dtFechaINDMouseClicked

    private void dtFechaFNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtFechaFNDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dtFechaFNDMouseClicked

    private void lblLineCreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLineCreMouseClicked
        tab.setSelectedIndex(0);
        lblLineDeb.setForeground(new Color(255,255,255));
        lblLineCre.setForeground(new Color(41,127,184));
        lblCredito.setFont(new Font("Segoe UI Semilight",1, 15));
        lblDebito.setFont(new Font("Segoe UI Semilight",0, 14));
    }//GEN-LAST:event_lblLineCreMouseClicked

    private void lblLineDebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLineDebMouseClicked
        tab.setSelectedIndex(1);
        lblLineCre.setForeground(new Color(255,255,255));
        lblLineDeb.setForeground(new Color(41,127,184));

        lblCredito.setFont(new Font("Segoe UI Semilight",0, 14));
        lblDebito.setFont(new Font("Segoe UI Semilight",1, 15));
    }//GEN-LAST:event_lblLineDebMouseClicked

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
    private com.toedter.calendar.JDateChooser dtFechaF;
    private com.toedter.calendar.JDateChooser dtFechaFND;
    private com.toedter.calendar.JDateChooser dtFechaI;
    private com.toedter.calendar.JDateChooser dtFechaIND;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblCredito;
    private javax.swing.JLabel lblDebito;
    private javax.swing.JLabel lblEmpresa1;
    private javax.swing.JLabel lblEmpresa2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLineCre;
    private javax.swing.JLabel lblLineDeb;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JPanel panelCPT51;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tb_NotaCreditoDet;
    private javax.swing.JTable tb_NotaDebitoDet;
    private javax.swing.JTable tb_Nota_Credito;
    private javax.swing.JTable tb_Nota_Debito;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtBuscarDocumentoND;
    // End of variables declaration//GEN-END:variables
}
