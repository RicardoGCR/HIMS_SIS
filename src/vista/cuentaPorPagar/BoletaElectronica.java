/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.cuentaPorPagar;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import modelos.cuentaPorPagar.CuentasPorPagarBoletaElectronica;
import modelos.cuentaPorPagar.CuentasPorPagarFacturasCabecera;
import modelos.cuentaPorPagar.CuentasPorPagarFacturasDetalle;
import modelos.cuentaPorPagar.CuentasPorPagarVentasConsolidadoCabecera;

/**
 *
 * @author MYS3
 */
public class BoletaElectronica extends javax.swing.JFrame {

    CuentasPorPagarBoletaElectronica boleta = new CuentasPorPagarBoletaElectronica();
    String barra = File.separator;
    // SE DEFINE LA CARPETA DATA, DONDE SE ENVIAN LOS REGISTROS DE SUNAT
    String ubicacion = "W:\\sfs\\DATA\\";
    int cantidad = 0;
    //DECLARAR VARIABLES DE BOLETAS GENERADAS Y NO GENERADAS
    int cantidadAceptas = 0,cantidadRechazadas=0;
    public BoletaElectronica() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
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
        jPanel3.setVisible(false);
        //MUESTRA LA FECHA ACTUA
        lblFechaEmision.setText(fechaActual());
        //LISTA LAS PRIMERAS 500 BOLETAS DE VENTAS POR CONTADO
        boleta.ventasPorContado(tbBoletasCabecera, "", "", "");
        //CUENTA LOS REGISTROS DE LA TABLA
        cantidad = tbBoletasCabecera.getRowCount();
        //COMPLETA EL CAMPO CON LA CANTIDAD DE LA TABLA DE CABECERA DE BOLETAS
        txtTotalVentas.setText(String.valueOf(cantidad));
        //TRAE LOS DETALLES DE LA VENTA DE LA PRIMERA BOLETA
        boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 14)), "");
        //GENERA EL CORRELATIVO DE VENTA DE BOLETA SEGUN LA SERIE
        boleta.generarSerieCorrelativo("B");
        boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 15)), "");
        //MUESTRA LOS DATOS DE LA PERSONA PARA GUARDAR LOS CAMPOS EN LA TABLA DE BOLETAS Y AL FACTURADOR
        lblDNI.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 4)));
        lblApeNom.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 6)));
        //SELECCIONA EL PRIMER REGISTRO DE LA TABLA DE CABECERA
        tbBoletasCabecera.getSelectionModel().setSelectionInterval (0,0) ;
        tbBoletasCabecera.requestFocus();
        //CALCULA LOS VALORES DE VENTA DEL PRIMER REGISTRO
        calcularValores();
    }

    //TRAE LA FECHA ACTUAL
    public String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        return date.format(now);
    }
    
    //CREA LA CABECERA PARA LA BOLETA .CAB
    public boolean crearCabecera(){
        boolean retorna = false;
        String archivo = "20410275768" + "-" + 
                "03-" +
                lblSerie.getText() + "-" + 
                lblNroCorrelativo.getText() + ".CAB";
        File crea_archivo = new File(archivo);
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                    retorna = false;
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                        crea.format("01" + "|" +lblFechaEmision.getText()+"|"+
                        ""+"|"+
                        "1"+"|"+
                        lblDNI.getText()+"|"+
                        lblApeNom.getText() + "|" + 
                        "PEN" + "|" + 
                        txtDsctoGlobal.getText() + "|" + 
                        txtOtrosCargos.getText() + "|" + 
                        txtTotalDscto.getText() + "|" +
                        txtValorVentaGravada.getText() + "|" + 
                        txtValorVentaInafectada.getText() + "|" + 
                        txtVentaExonerada.getText() + "|" + 
                        txtMtoIGV.getText() + "|" +
                        txtMtoISC.getText()+ "|" + 
                        txtOtrosTributos.getText() + "|" + 
                        txtImporteTotalVenta.getText());
                    crea.close();
                    retorna = true;
                }   
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                    retorna = false;
            }
        return retorna;
    }   
    
    //CREA EL DETALLE DE LA BOLETA PARA EL FACTURADOR .DET
    public void crearDetalle(File crea_archivo, String archivo){
        archivo = "20410275768" + "-" + 
                "03-" +
                lblSerie.getText() + "-" + 
                lblNroCorrelativo.getText() + ".DET";
        crea_archivo = new File(archivo);
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                        crea.format("NIU" + "|" +
                        String.valueOf(tbBoletaDetalles.getValueAt(0, 5)) + "|" + String.valueOf(tbBoletaDetalles.getValueAt(0, 1))+  "|" + 
                         ""+ "|" + 
                        String.valueOf(tbBoletaDetalles.getValueAt(0, 2))+ "|" + 
                         String.valueOf(tbBoletaDetalles.getValueAt(0, 3)) + "|" + 
                         "0.00" + "|" + //DESCUENTO
                        "0.00" + "|" +  //IGV
                        "10" + "|" + //AFECTACION IGV
                        "0.00"+ "|" + //ISC
                        "01" + "|" + //AFECTACION ISC
                        String.valueOf(tbBoletaDetalles.getValueAt(0, 6)) + "|" + //PRECIO DE VENTA
                        String.valueOf(tbBoletaDetalles.getValueAt(0, 8)) //VALOR DE VENTA
                        );
                    crea.close();
                    JOptionPane.showMessageDialog(this, "Boleta Electrónica Generada");
                }   
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                    JOptionPane.showMessageDialog(this, "Error al generar Boleta electrónica");
            }
    }  
    
    //CREA EL DETALLE PARA EL FACTURADOR (ARCHIVO .DET) 
    //DE ACUERDO A LA CANTIDAD DE DETALLES QUE TENGA LA BOLETA DE VENTA
    public boolean crearDetalles(File crea_archivo, String archivo){
        boolean retorna = false;
        try {
            Formatter crea = new Formatter(ubicacion+archivo);
            if(crea_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                retorna = false;
            } else {
                String bloc1 = "";
                for (int c = 0; c < tbBoletaDetalles.getRowCount(); c++){    
                    bloc1 = bloc1 + "NIU" + "|" +
                        String.valueOf(tbBoletaDetalles.getValueAt(c, 5)) + "|" + String.valueOf(tbBoletaDetalles.getValueAt(c, 1))+  "|" + 
                         ""+ "|" + 
                        String.valueOf(tbBoletaDetalles.getValueAt(c, 2))+ "|" + 
                         String.valueOf(tbBoletaDetalles.getValueAt(c, 3)) + "|" + 
                         "0.00" + "|" + //DESCUENTO
                        "0.00" + "|" +  //IGV
                        "10" + "|" + //AFECTACION IGV
                        "0.00"+ "|" + //ISC
                        "01" + "|" + //AFECTACION ISC
                        String.valueOf(tbBoletaDetalles.getValueAt(c, 6)) + "|" + //PRECIO DE VENTA
                        String.valueOf(tbBoletaDetalles.getValueAt(c, 8))  + "\r\n" /*VALOR DE VENTA*/;
                }
                crea.format(bloc1);
                crea.close();
                retorna = true;
            }   
        } catch (Exception e) {
            System.out.println("Error crear detalle: " + e.getMessage());
                retorna = false;
        }
        return retorna;
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
    
    //CALCULA VALORES SEGUN EL REGISTRO SELECCIONADO DE LA BOLETA
    public void calcularValores(){
            int fila = tbBoletasCabecera.getSelectedRow();
            boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(fila, 15)), "");
            lblDNI.setText(String.valueOf(tbBoletasCabecera.getValueAt(fila, 4)));
            lblApeNom.setText(String.valueOf(tbBoletasCabecera.getValueAt(fila, 6)));
            double sumatoriaTotal = 0.00;
            double igv,importeTotalVenta;
            for (int i = 0; i < tbBoletaDetalles.getRowCount(); i++){          
                sumatoriaTotal = sumatoriaTotal + Double.parseDouble(tbBoletaDetalles.getValueAt(i,8).toString());         
            }
            igv = 0.00;
            BigDecimal bd2 = new BigDecimal(igv);
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
            //
            importeTotalVenta = sumatoriaTotal;
            BigDecimal bdImporte  = new BigDecimal(importeTotalVenta);
            bdImporte = bdImporte.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtMtoIGV.setText(String.valueOf(bd2));
            txtImporteTotalVenta.setText(String.valueOf(bdImporte));
            txtValorVentaInafectada.setText(String.valueOf(bdImporte));
            //VALORES POR DEFECTO ENVIADO CON 0.00 PORQUE NO AFECTAN 
            txtDsctoGlobal.setText("0.00");
            txtMtoISC.setText("0.00");
            txtOtrosCargos.setText("0.00");
            txtOtrosTributos.setText("0.00");
            txtTotalDscto.setText("0.00");
            txtValorVentaGravada.setText("0.00");
            txtVentaExonerada.setText("0.00");
    }
    
    //METODO PARA DETERMINAR LA FECHA SEGUN EL RANGO DE BUSQUEDA
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtDni = new javax.swing.JTextField();
        lbldetalle = new javax.swing.JLabel();
        btnIniciar1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtMtoIGV = new javax.swing.JLabel();
        txtValorVentaGravada = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        txtTotalDscto = new javax.swing.JLabel();
        txtDsctoGlobal = new javax.swing.JLabel();
        txtOtrosCargos = new javax.swing.JLabel();
        txtOtrosTributos = new javax.swing.JLabel();
        txtVentaExonerada = new javax.swing.JLabel();
        txtImporteTotalVenta = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        txtMtoISC = new javax.swing.JLabel();
        txtValorVentaInafectada = new javax.swing.JLabel();
        lblApeNom = new javax.swing.JLabel();
        lblFechaEmision2 = new javax.swing.JLabel();
        panelCPT3 = new javax.swing.JPanel();
        txtTotalVentas = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        tablaS = new javax.swing.JScrollPane();
        tbBoletasCabecera = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            tablaS1 = new javax.swing.JScrollPane();
            tbBoletaDetalles = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                dtFechaI = new com.toedter.calendar.JDateChooser();
                dtFechaF = new com.toedter.calendar.JDateChooser();
                btnIniciar = new javax.swing.JButton();
                lblSerie = new javax.swing.JLabel();
                lblNroCorrelativo = new javax.swing.JLabel();
                lblFechaEmision = new javax.swing.JLabel();
                lblSerie1 = new javax.swing.JLabel();
                lblFechaEmision1 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(41, 127, 184));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Consolidado de<span style=\"font-size:'15px'\"><br>Boletas Electrónicas</br></span></html>");

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

                lbldetalle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
                lbldetalle.setText("Ingrese el DNI del paciente");

                btnIniciar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnIniciar1.setForeground(new java.awt.Color(255, 255, 255));
                btnIniciar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
                btnIniciar1.setText("<html>Generar <br>Documento<br><span style=\"font-size:'9px'\">(Electrónico)</span></html>\n");
                btnIniciar1.setContentAreaFilled(false);
                btnIniciar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnIniciar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnIniciar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnIniciar1.setIconTextGap(30);
                btnIniciar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnIniciar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnIniciar1ActionPerformed(evt);
                    }
                });

                txtMtoIGV.setText("jLabel2");

                txtValorVentaGravada.setText("jLabel2");

                lblId.setText("jLabel2");

                lblusu.setText("jLabel2");

                txtTotalDscto.setText("jLabel2");

                txtDsctoGlobal.setText("jLabel2");

                txtOtrosCargos.setText("jLabel2");

                txtOtrosTributos.setText("jLabel2");

                txtVentaExonerada.setText("jLabel2");

                txtImporteTotalVenta.setText("jLabel2");

                lblDNI.setText("jLabel2");

                txtMtoISC.setText("jLabel2");

                txtValorVentaInafectada.setText("jLabel2");

                lblApeNom.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
                lblApeNom.setText("jLabel2");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorVentaInafectada)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtDsctoGlobal)
                                .addGap(18, 18, 18)
                                .addComponent(txtOtrosTributos))
                            .addComponent(txtValorVentaGravada)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTotalDscto)
                                .addGap(18, 18, 18)
                                .addComponent(lblDNI))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtOtrosCargos)
                                .addGap(18, 18, 18)
                                .addComponent(txtImporteTotalVenta))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblApeNom)
                                .addGap(18, 18, 18)
                                .addComponent(txtMtoISC, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMtoIGV)
                                    .addComponent(txtVentaExonerada))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblId)
                                    .addComponent(lblusu))))
                        .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApeNom)
                            .addComponent(txtMtoISC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDsctoGlobal)
                            .addComponent(txtOtrosTributos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOtrosCargos)
                            .addComponent(txtImporteTotalVenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalDscto)
                            .addComponent(lblDNI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorVentaGravada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorVentaInafectada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVentaExonerada)
                            .addComponent(lblId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMtoIGV)
                            .addComponent(lblusu))
                        .addContainerGap())
                );

                lblFechaEmision2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblFechaEmision2.setForeground(new java.awt.Color(255, 255, 255));
                lblFechaEmision2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblFechaEmision2.setText("<html>Ventas <br>(Cantidad)</html>");

                panelCPT3.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtTotalVentas.setEditable(false);
                txtTotalVentas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                txtTotalVentas.setForeground(new java.awt.Color(51, 51, 51));
                txtTotalVentas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTotalVentas.setBorder(null);
                txtTotalVentas.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTotalVentasCaretUpdate(evt);
                    }
                });
                txtTotalVentas.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtTotalVentasActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT3Layout = new javax.swing.GroupLayout(panelCPT3);
                panelCPT3.setLayout(panelCPT3Layout);
                panelCPT3Layout.setHorizontalGroup(
                    panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT3Layout.setVerticalGroup(
                    panelCPT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                );

                lblCantidad.setForeground(new java.awt.Color(41, 127, 184));
                lblCantidad.setText("jLabel2");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCantidad)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnIniciar1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblFechaEmision2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbldetalle)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldetalle)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaEmision2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIniciar1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                tablaS.setBackground(new java.awt.Color(255, 255, 255));
                tablaS.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbBoletasCabecera.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbBoletasCabecera.setForeground(new java.awt.Color(69, 69, 69));
                tbBoletasCabecera.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Título 1", "Título 2", "Título 3", "Título 4"
                    }
                ));
                tbBoletasCabecera.setGridColor(new java.awt.Color(255, 255, 255));
                tbBoletasCabecera.setRowHeight(25);
                tbBoletasCabecera.setSelectionBackground(new java.awt.Color(153, 153, 153));
                tbBoletasCabecera.getTableHeader().setReorderingAllowed(false);
                tbBoletasCabecera.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbBoletasCabeceraMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbBoletasCabeceraMousePressed(evt);
                    }
                });
                tbBoletasCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbBoletasCabeceraKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbBoletasCabeceraKeyReleased(evt);
                    }
                });
                tablaS.setViewportView(tbBoletasCabecera);

                tablaS1.setBackground(new java.awt.Color(255, 255, 255));
                tablaS1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbBoletaDetalles.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbBoletaDetalles.setForeground(new java.awt.Color(69, 69, 69));
                tbBoletaDetalles.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Título 1", "Título 2", "Título 3", "Título 4"
                    }
                ));
                tbBoletaDetalles.setGridColor(new java.awt.Color(255, 255, 255));
                tbBoletaDetalles.setRowHeight(25);
                tbBoletaDetalles.setSelectionBackground(new java.awt.Color(153, 153, 153));
                tbBoletaDetalles.getTableHeader().setReorderingAllowed(false);
                tbBoletaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbBoletaDetallesMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbBoletaDetallesMousePressed(evt);
                    }
                });
                tbBoletaDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbBoletaDetallesKeyPressed(evt);
                    }
                });
                tablaS1.setViewportView(tbBoletaDetalles);

                dtFechaI.setBackground(new java.awt.Color(255, 255, 255));
                dtFechaI.setForeground(new java.awt.Color(51, 51, 51));
                dtFechaI.setDateFormatString("dd/MM/yyyy");
                dtFechaI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                dtFechaF.setBackground(new java.awt.Color(255, 255, 255));
                dtFechaF.setForeground(new java.awt.Color(51, 51, 51));
                dtFechaF.setDateFormatString("dd/MM/yyyy");
                dtFechaF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                btnIniciar.setForeground(new java.awt.Color(102, 102, 102));
                btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Play Filled-32.png"))); // NOI18N
                btnIniciar.setText("Iniciar");
                btnIniciar.setContentAreaFilled(false);
                btnIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnIniciar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                btnIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btnIniciar.setIconTextGap(10);
                btnIniciar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                btnIniciar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnIniciarActionPerformed(evt);
                    }
                });

                lblSerie.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                lblSerie.setForeground(new java.awt.Color(51, 51, 51));
                lblSerie.setText("B001");

                lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                lblNroCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
                lblNroCorrelativo.setText("00000000");

                lblFechaEmision.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblFechaEmision.setForeground(new java.awt.Color(102, 102, 102));
                lblFechaEmision.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblFechaEmision.setText("2017-05-30");

                lblSerie1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                lblSerie1.setForeground(new java.awt.Color(51, 51, 51));
                lblSerie1.setText(" - ");

                lblFechaEmision1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblFechaEmision1.setForeground(new java.awt.Color(102, 102, 102));
                lblFechaEmision1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                lblFechaEmision1.setText("Fecha de Emisión");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tablaS)
                    .addComponent(tablaS1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblSerie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSerie1)
                                .addGap(4, 4, 4)
                                .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFechaEmision1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFechaEmision)))
                        .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblFechaEmision)
                                    .addComponent(lblFechaEmision1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSerie)
                                    .addComponent(lblNroCorrelativo)
                                    .addComponent(lblSerie1)))
                            .addComponent(btnIniciar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tablaS1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );

                jScrollPane1.setViewportView(jPanel2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtDniCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDniCaretUpdate

    }//GEN-LAST:event_txtDniCaretUpdate

    private void txtDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed

    }//GEN-LAST:event_txtDniKeyPressed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        //CARGAR IMAGEN DE INICIAR Y DETENER DE BOTONES PARA BUSQUEDA
        ImageIcon continuar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/icons8-Play Filled-32.png")); 
        ImageIcon detener=new ImageIcon(this.getClass().getResource("/imagenes/iconos/icons8-Detener Filled-32.png")); 
        try {
        boleta.ventasPorContado(tbBoletasCabecera, "", determinarFecha(dtFechaI), determinarFecha(dtFechaF));
         if(tbBoletasCabecera.getRowCount()!=0){ //IF VERIFICA SI LA TABLA TIENE DATOS
            if(btnIniciar.getText().equals("Iniciar")){ //INICIAR LA BUSQUEDA
                btnIniciar.setIcon(detener);
                btnIniciar.setText("Detener");
                dtFechaI.setVisible(false);
                dtFechaF.setVisible(false);
                    cantidad = tbBoletasCabecera.getRowCount();
                    txtTotalVentas.setText(String.valueOf(cantidad));
                    boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 14)), "");
                    boleta.generarSerieCorrelativo("B");
                    boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 15)), "");
                    lblDNI.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 4)));
                    lblApeNom.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 6)));
                    tbBoletasCabecera.getSelectionModel().setSelectionInterval (0,0) ;
                    tbBoletasCabecera.requestFocus();
                    calcularValores();
            } else { // DETIENE LA BUSQUEDA
                btnIniciar.setIcon(continuar); 
                btnIniciar.setText("Iniciar");
                boleta.ventasPorContado(tbBoletasCabecera, "","","");
                dtFechaI.setVisible(true);
                dtFechaF.setVisible(true);
            }
        } else {
                JOptionPane.showMessageDialog(this, "No hay registros para mostrar");
                btnIniciar.setIcon(continuar); 
                btnIniciar.setText("Iniciar");
                dtFechaI.setVisible(true);
                dtFechaF.setVisible(true);
        } // FIN IF VERIFICA SI LA TABLA TIENE DATOS
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void tbBoletasCabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraMouseClicked
        
        if(evt.getClickCount()==1){
            calcularValores();
            }
    }//GEN-LAST:event_tbBoletasCabeceraMouseClicked

    private void tbBoletasCabeceraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraMousePressed

    }//GEN-LAST:event_tbBoletasCabeceraMousePressed

    private void tbBoletasCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraKeyPressed
        
    }//GEN-LAST:event_tbBoletasCabeceraKeyPressed

    private void tbBoletaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletaDetallesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesMouseClicked

    private void tbBoletaDetallesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBoletaDetallesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesMousePressed

    private void tbBoletaDetallesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBoletaDetallesKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBoletaDetallesKeyPressed

    private void btnIniciar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciar1ActionPerformed
        try {
        lblCantidad.setText(txtTotalVentas.getText());
        boolean rpta = false;
            for(int c = 0;c<cantidad;c++){
            AdmisionEmergenciaCabecera cabecera = new AdmisionEmergenciaCabecera();
            CuentasPorPagarVentasConsolidadoCabecera cab = new CuentasPorPagarVentasConsolidadoCabecera();
            if(!lblDNI.getText().equals("")){
                    CuentasPorPagarFacturasCabecera facturaCabecera = new CuentasPorPagarFacturasCabecera();
                        facturaCabecera.setSerie(lblSerie.getText());
                        facturaCabecera.setCorrelativo(lblNroCorrelativo.getText());
                        facturaCabecera.setTipoOperacion("01");
                        facturaCabecera.setFechaEmision(lblFechaEmision.getText());
                        facturaCabecera.setTipoMoneda("PEN");
                        facturaCabecera.setDocumento("03 BOLETA");
                        facturaCabecera.setCod_usu(cabecera.codUsuario(lblusu.getText()));
                        facturaCabecera.setCodigoEmpresa("");
                        facturaCabecera.setDsctoGlobal(Double.parseDouble(txtDsctoGlobal.getText()));
                        facturaCabecera.setOtrosCargos(Double.parseDouble(txtOtrosCargos.getText()));
                        facturaCabecera.setTotalDscto(Double.parseDouble(txtTotalDscto.getText()));
                        facturaCabecera.setValorVGravada(Double.parseDouble(txtValorVentaGravada.getText()));
                        facturaCabecera.setValorVInafectada(Double.parseDouble(txtValorVentaInafectada.getText()));
                        facturaCabecera.setVentaExonerada(Double.parseDouble(txtVentaExonerada.getText()));
                        facturaCabecera.setMontoIgv(Double.parseDouble(txtMtoIGV.getText()));
                        facturaCabecera.setMontoIsc(Double.parseDouble(txtMtoISC.getText()));
                        facturaCabecera.setOtrosTributos(Double.parseDouble(txtOtrosTributos.getText()));
                        facturaCabecera.setImportaTotalVta(Double.parseDouble(txtImporteTotalVenta.getText()));
                        facturaCabecera.setPlaca("");
                        facturaCabecera.setPoliza("");
                        facturaCabecera.setContratante("");
                        facturaCabecera.setCartaGarantia("");
                        facturaCabecera.setDni(lblDNI.getText());
                if(facturaCabecera.mantenimientoCuentasPorPagarFacturasCabecera("I")){
                    cantidadAceptas=cantidadAceptas+1;
                    if(crearCabecera()){
                        CuentasPorPagarFacturasDetalle facturaDetalle1 = new CuentasPorPagarFacturasDetalle();
                        lblId.setText(facturaDetalle1.facturaCabeceraId());
                        String archivo = "20410275768" + "-" + 
                        "03" + "-" +
                        lblSerie.getText() + "-" + 
                        lblNroCorrelativo.getText() + ".DET";
                        File crea_archivo = new File(archivo);
                        for (int i = 0; i < tbBoletaDetalles.getRowCount(); i++){      
                            CuentasPorPagarFacturasDetalle facturaDetalle = new CuentasPorPagarFacturasDetalle();
                            facturaDetalle.setCpfId(Integer.parseInt(lblId.getText()));
                            facturaDetalle.setCpdGrav("GRAVADO");
                            facturaDetalle.setCpdCodUnidad("NIU UNIDAD");
                            facturaDetalle.setCpdCantidad(Integer.parseInt(tbBoletaDetalles.getValueAt(i,5).toString()));
                            facturaDetalle.setNomenclatura(facturaDetalle.codNomen(tbBoletaDetalles.getValueAt(i,1).toString()));
                            facturaDetalle.setCpdCodProdSunat("");
                            facturaDetalle.setCpdValorU(BigDecimal.valueOf(Double.parseDouble(tbBoletaDetalles.getValueAt(i,3).toString())));
                            facturaDetalle.setCpdDescPorcen(BigDecimal.valueOf(Double.parseDouble("0.00")));
                            facturaDetalle.setCpdDscto(BigDecimal.valueOf(Double.parseDouble(tbBoletaDetalles.getValueAt(i,7).toString())));
                            facturaDetalle.setCpdIgv(BigDecimal.valueOf(Double.parseDouble("0.00")));
                            facturaDetalle.setCpdAfecIgv("10 GRAVADO-OPERACIÓN ONEROSA"); 
                            facturaDetalle.setCpdIsc(BigDecimal.valueOf(Double.parseDouble("0.00")));
                            facturaDetalle.setCpdAfecIsc("01 SISTEMA AL VALOR"); 
                            facturaDetalle.setCpdPrecioVenta(BigDecimal.valueOf(Double.parseDouble(tbBoletaDetalles.getValueAt(i,3).toString())));
                            facturaDetalle.setCpdValorVenta(BigDecimal.valueOf(Double.parseDouble(tbBoletaDetalles.getValueAt(i,8).toString())));
                            facturaDetalle.setCpdDsctoGlobal(BigDecimal.valueOf(Double.parseDouble(txtDsctoGlobal.getText())));
                            facturaDetalle.setCpdSumOtrosCargos(BigDecimal.valueOf(Double.parseDouble(txtOtrosCargos.getText())));
                            facturaDetalle.setCpdSumIgv(BigDecimal.valueOf(Double.parseDouble("0.00")));
                            facturaDetalle.setCpdTVvInafec(BigDecimal.valueOf(Double.parseDouble(txtValorVentaInafectada.getText())));
                            facturaDetalle.setCpdTVvGrav(BigDecimal.valueOf(Double.parseDouble(txtValorVentaGravada.getText())));
                            facturaDetalle.setCpdTDsctos(BigDecimal.valueOf(Double.parseDouble(txtTotalDscto.getText())));
                            facturaDetalle.setCpdOtrosTribut(BigDecimal.valueOf(Double.parseDouble(txtOtrosTributos.getText())));
                            facturaDetalle.setCpdSumIsc(BigDecimal.valueOf(Double.parseDouble(txtMtoISC.getText())));
                            facturaDetalle.setCpdTVExonen(BigDecimal.valueOf(Double.parseDouble(txtVentaExonerada.getText())));
                            facturaDetalle.setCpdImpTotVtas(BigDecimal.valueOf(Double.parseDouble(tbBoletaDetalles.getValueAt(i,8).toString())));
                            facturaDetalle.setCodUsu(cabecera.codUsuario(lblusu.getText()));
                            facturaDetalle.setFormaPago("");
                            if(facturaDetalle.mantenimientoCuentasPorPagarFacturasDetalle("I")){
                                cab.actualizarEstadoFacturacion(tbBoletaDetalles.getValueAt(i,12).toString(), "L");
                                if(crearDetalles(crea_archivo, archivo)){
                                    rpta = true;
                                } else{
                                    rpta = false;
                                }
                            }
                    }
                        if(rpta==true){
                            if(Integer.parseInt(lblCantidad.getText())==1)
                                JOptionPane.showMessageDialog(this, "Boleta Electrónica Generada");
                        boleta.ventasPorContado(tbBoletasCabecera, "", "", "");
                            try {
                                txtTotalVentas.setText(String.valueOf(tbBoletasCabecera.getRowCount()));
                                boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 14)), "");
                                boleta.generarSerieCorrelativo("B");
                                boleta.ventasPorContadoDetalles(tbBoletaDetalles, String.valueOf(tbBoletasCabecera.getValueAt(0, 15)), "");
                                lblDNI.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 4)));
                                lblApeNom.setText(String.valueOf(tbBoletasCabecera.getValueAt(0, 6)));
                                tbBoletasCabecera.getSelectionModel().setSelectionInterval (0,0) ;
                                tbBoletasCabecera.requestFocus();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Error al crear la boleta electrónica");
                        }
                }//fin if crearCabecera    
                }//fin if mantenimiento cabecera
                else {
                    cantidadRechazadas = cantidadRechazadas + 1;
                }
            } else{
                JOptionPane.showMessageDialog(this, "El Paciente debe tener DNI");
            }
            } // fin de for cantidad
            //MOSTRAR BOLETAS GENERADAS Y NO GENERADAS
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
            JOptionPane.showMessageDialog(this, "Boletas electrónicas Generadas correctamente " + cantidadAceptas + 
                        "\nBoletas electrónicas Generadas incorrectamente " + cantidadRechazadas);        
    }//GEN-LAST:event_btnIniciar1ActionPerformed

    private void tbBoletasCabeceraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBoletasCabeceraKeyReleased

        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            calcularValores();
        }
    }//GEN-LAST:event_tbBoletasCabeceraKeyReleased

    private void txtTotalVentasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalVentasCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentasCaretUpdate

    private void txtTotalVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalVentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalVentasActionPerformed

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
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoletaElectronica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoletaElectronica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnIniciar;
    public static javax.swing.JButton btnIniciar1;
    private com.toedter.calendar.JDateChooser dtFechaF;
    private com.toedter.calendar.JDateChooser dtFechaI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApeNom;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblFechaEmision1;
    private javax.swing.JLabel lblFechaEmision2;
    private javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblNroCorrelativo;
    public static javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblSerie1;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT3;
    private javax.swing.JScrollPane tablaS;
    private javax.swing.JScrollPane tablaS1;
    public static javax.swing.JTable tbBoletaDetalles;
    public static javax.swing.JTable tbBoletasCabecera;
    public static javax.swing.JTextField txtDni;
    private javax.swing.JLabel txtDsctoGlobal;
    private javax.swing.JLabel txtImporteTotalVenta;
    private javax.swing.JLabel txtMtoIGV;
    private javax.swing.JLabel txtMtoISC;
    private javax.swing.JLabel txtOtrosCargos;
    private javax.swing.JLabel txtOtrosTributos;
    private javax.swing.JLabel txtTotalDscto;
    public static javax.swing.JTextField txtTotalVentas;
    private javax.swing.JLabel txtValorVentaGravada;
    private javax.swing.JLabel txtValorVentaInafectada;
    private javax.swing.JLabel txtVentaExonerada;
    // End of variables declaration//GEN-END:variables
}
