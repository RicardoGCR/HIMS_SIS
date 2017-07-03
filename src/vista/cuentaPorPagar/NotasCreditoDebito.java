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
import modelos.cuentaPorPagar.CuentasPorPagarFacturasDetalle;
import modelos.cuentaPorPagar.CuentasPorPagarNotaDeCreditoCabecera;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import static vista.admisionEmergencia.FrmFormatoEmergencia.pnlEObservación;

/**
 *
 * @author PC02
 */
public class NotasCreditoDebito extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();

    String barra = File.separator;
    String ubicacion = "C:\\sunat_archivos\\sfs\\DATA\\";
     CuentasPorPagarNotaDeCreditoCabecera serie = new CuentasPorPagarNotaDeCreditoCabecera();
    public NotasCreditoDebito() {
        initComponents();
        c.conectar();
       this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//en el centro
        cbxTipoNotaCredito.setBackground(Color.WHITE);
        cbxCodUnidad.setBackground(Color.WHITE);
        cbxDocumento.setBackground(Color.WHITE);
        cbxTipoDocumento.setBackground(Color.WHITE);
        cbxGravado.setBackground(Color.WHITE);
        cbxTipoMoneda.setBackground(Color.WHITE);
        cbxAfecIGV.setBackground(Color.WHITE);
        cbxAfecISC.setBackground(Color.WHITE);
        
        //Debito
        cbxTipoNotaDebito.setBackground(Color.WHITE);
        cbxCodUnidadDebito.setBackground(Color.WHITE);
        cbxDocumentoDebito.setBackground(Color.WHITE);
        cbxTipoDocumentoDebito.setBackground(Color.WHITE);
        cbxGravadoDebito.setBackground(Color.WHITE);
        cbxTipoMonedaDebito.setBackground(Color.WHITE);
        cbxAfecIGVDebito.setBackground(Color.WHITE);
        cbxAfecISCDebito.setBackground(Color.WHITE);
        
//        tbFacturacion.getTableHeader().setVisible(false);
//        tbFacturacion.setTableHeader(null);
        LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
        txtNroDocumento.setDocument(limitObservacion);
        lblFechaEmision.setText(fechaActual());
        lblFechaEmisionDebito.setText(fechaActual());
        lblCorrelativoCreditoF.setText("");
        lblCorrelativoDebitoF.setText("");
        cbxBuscarDocumento.setBackground(Color.WHITE);
        
        BUSCAR_FACTURA_BOLETA.setLocationRelativeTo(null);
        BUSCAR_FACTURA_BOLETA.getContentPane().setBackground(Color.white);
        
     
        
        
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
        cbxAfecIGV.setSelectedIndex(0);
         cbxAfecISC.setSelectedIndex(0);
        cbxCodUnidad.setSelectedIndex(0);
        cbxDocumento.setSelectedIndex(0);
        cbxGravado.setSelectedIndex(0);
        cbxTipoDocumento.setSelectedIndex(0);
        cbxTipoMoneda.setSelectedIndex(0);
        cbxTipoNotaCredito.setSelectedIndex(0);
        
        cbxAfecIGVDebito.setSelectedIndex(0);
         cbxAfecISCDebito.setSelectedIndex(0);
        cbxCodUnidadDebito.setSelectedIndex(0);
        cbxDocumentoDebito.setSelectedIndex(0);
        cbxGravadoDebito.setSelectedIndex(0);
        cbxTipoDocumentoDebito.setSelectedIndex(0);
        cbxTipoMonedaDebito.setSelectedIndex(0);
        cbxTipoNotaDebito.setSelectedIndex(0);
     
        //CREDITO
        lblIdCredito.setText("");
        txtSerie.setText("");
        lblNroCorrelativo.setText("");
        lblCorrelativoCreditoF.setText("");
        txtApeNom.setText("");
        txtCantidad.setText("");
        txtCodProducto.setText("");
        txtCodProductoSunat.setText("");
        txtDescripcionSustento.setText("");
        txtDscto.setText("");
//        txtDsctoGlobal.setText("");
        txtIGV.setText("");
        txtImporteTotalVenta.setText("");
        txtMtoIGVCredito.setText("");
        txtMtoISC.setText("");
        txtOtrosCargosCredito.setText("");
        txtOtrosTributosCredito.setText("");
        txtPorcenDscto.setText("");
        txtNroDocumento.setText("");
//        txtTotalDscto.setText("");
        txtValorU.setText("");
        txtValorVentaGravada.setText("");
        txtValorVentaInafectada.setText("");
        txtVentaExonerada.setText("");
        
        
        //DEBITO
        lblIdDebito.setText("");
        txtSerieDebito.setText("");
        lblNroCorrelativoDebito.setText("");
        lblCorrelativoDebitoF.setText("");
        txtApeNomDebito.setText("");
        txtCantidadDebito.setText("");
        txtCodProducto.setText("");
        txtCodProductoSunat1.setText("");
        txtDescripcionDebito.setText("");
        txtDsctoDebito.setText("");
//        txtDsctoGlobal.setText("");
        txtIGVDebito.setText("");
        txtImporteTotalVentaDebito.setText("");
        txtMtoIGVDebito.setText("");
        txtMtoISCDebito.setText("");
        txtOtrosCargosDebito.setText("");
        txtOtrosTributosDebito.setText("");
        txtPorcenDsctoDebito.setText("");
        txtNroDocumentoDebito.setText("");
//        txtTotalDscto.setText("");
        txtValorUDebito.setText("");
        txtValorVentaGravadaDebito.setText("");
        txtValorVentaInafectadaDebito.setText("");
        txtVentaExoneradaDebito.setText("");
        
        
          DefaultTableModel modelo = (DefaultTableModel)tbFacturacion.getModel(); 
         int a=tbFacturacion.getRowCount();
        for(int i=0;i<a;i++){
                    modelo.removeRow(0);
        }       
   
        DefaultTableModel modeloD = (DefaultTableModel)tbFacturacionDebito.getModel(); 
         int aD=tbFacturacionDebito.getRowCount();
        for(int i=0;i<aD;i++){
                    modeloD.removeRow(0);
        }       
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
            consulta="exec sp_CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar ?,?";

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
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        return date.format(now);
    }
    
    public void crearCabeceraCredito(){
        
       String archivo = "20410275768" + "-" + 
               "07" + "-" +
               txtSerie.getText() + "-" + 
                lblCorrelativoCreditoF.getText() + ".NOT";
//        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(archivo);
        if(txtNroDocumento.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No hay ID");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    crea.format(lblFechaEmision.getText()+"|"+
                    String.valueOf(cbxTipoNotaCredito.getSelectedItem().toString().charAt(0)) + 
                    String.valueOf(cbxTipoNotaCredito.getSelectedItem().toString().charAt(1))+"|"+
                    txtDescripcionSustento.getText()+ "|" + 
                    String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(0)) + 
                    String.valueOf(cbxDocumento.getSelectedItem().toString().charAt(1))+"|"+
                    txtSerie.getText() + "-" + 
                lblNroCorrelativo.getText() +"|"+
                     String.valueOf(cbxTipoDocumento.getSelectedItem().toString().charAt(0)) + "|" + 
                     txtNroDocumento.getText()+ "|" + 
                   txtApeNom.getText()+ "|" + 
                    cbxTipoMoneda.getSelectedItem().toString() + "|" + 
                    txtOtrosCargosCredito.getText() + "|" + 
                    
                    txtValorVentaGravada.getText() + "|" + 
                    txtValorVentaInafectada.getText() + "|" + 
                    txtVentaExonerada.getText() + "|" + 
                    txtMtoIGVCredito.getText() + "|" +
                    txtMtoISC.getText()+ "|" + 
                    txtOtrosTributosCredito.getText() + "|" + 
                    txtImporteTotalVenta.getText());
                    crea.close();
                }   
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "No se pudo"+e.getMessage());
            }
        }
    }   
    
    public void crearCabeceraDebito(){
        
       String archivo = txtNroDocumentoDebito .getText() + "-" + 
               "08" + "-" +
               txtSerieDebito.getText() + "-" + 
                lblCorrelativoDebitoF.getText() + ".NOT";
//        File crea_ubicacion = new File(ubicacion);
        File crea_archivo = new File(archivo);
        if(txtNroDocumentoDebito.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No hay ID");
        } else {
            try {
                if(crea_archivo.exists()){
                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                } else {
                    Formatter crea = new Formatter(ubicacion+archivo);
                    crea.format(lblFechaEmisionDebito.getText()+"|"+
                    String.valueOf(cbxTipoNotaDebito.getSelectedItem().toString().charAt(0)) + 
                    String.valueOf(cbxTipoNotaDebito.getSelectedItem().toString().charAt(1))+"|"+
                    txtDescripcionDebito.getText()+ "|" + 
                    String.valueOf(cbxDocumentoDebito.getSelectedItem().toString().charAt(0)) + 
                    String.valueOf(cbxDocumentoDebito.getSelectedItem().toString().charAt(1))+"|"+
                    txtSerieDebito.getText() + "-" + 
                lblNroCorrelativoDebito.getText() +"|"+
                     String.valueOf(cbxTipoDocumentoDebito.getSelectedItem().toString().charAt(0)) + "|" + 
                     txtNroDocumentoDebito.getText()+ "|" + 
                   txtApeNomDebito.getText()+ "|" + 
                    cbxTipoMonedaDebito.getSelectedItem().toString() + "|" + 
                    txtOtrosCargosDebito.getText() + "|" + 
                    
                    txtValorVentaGravadaDebito.getText() + "|" + 
                    txtValorVentaInafectadaDebito.getText() + "|" + 
                    txtVentaExoneradaDebito.getText() + "|" + 
                    txtMtoIGVDebito.getText() + "|" +
                    txtMtoISCDebito.getText()+ "|" + 
                    txtOtrosTributosDebito.getText() + "|" + 
                    txtImporteTotalVentaDebito.getText());
                    crea.close();
                }   
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "No se pudo");
            }
        }
    }   
    public void crearDetalle(){
//        String archivo = txtTipoDocumento.getText() + "-" + 
//                cbxDocumento.getSelectedItem().toString().charAt(0) + 
//                cbxDocumento.getSelectedItem().toString().charAt(1) + "-" +
//                cbxSerie.getSelectedItem().toString() + "-" + 
//                lblNroCorrelativo.getText() + ".DET";
//        File crea_archivo = new File(archivo);
//        if(txtTipoDocumento.getText().equals("")){
//            JOptionPane.showMessageDialog(this,"No hay ID");
//        } else {
//            try {
//                if(crea_archivo.exists()){
//                    JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
//                } else {
//                    Formatter crea = new Formatter(ubicacion+archivo);
//                    if(cbxCodUnidad.getSelectedIndex()==0 || cbxCodUnidad.getSelectedIndex()==4 ||
//                               cbxCodUnidad.getSelectedIndex()==5 || cbxCodUnidad.getSelectedIndex()==6 ||
//                               cbxCodUnidad.getSelectedIndex()==7){
//                        crea.format(String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
//                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) +
//                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(2)) + "|" +
//                        txtCantidad.getText() + "|" + txtCodProducto.getText() + "|" + 
//                        txtDescripcion.getText() + "|" + txtValorU.getText() + "|" + txtDscto.getText() + "|" +
//                        txtIGV.getText() + "|" + String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
//                        txtISC.getText() + "|" + String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
//                        txtPrecioVenta.getText() + "|" + txtValorVenta.getText()
//                        );
//                    } else {
//                        crea.format(String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
//                        String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) + "|" +
//                        txtCantidad.getText() + "|" + txtCodProducto.getText() + "|" + 
//                        txtDescripcion.getText() + "|" + txtValorU.getText() + "|" + txtDscto.getText() + "|" +
//                        txtIGV.getText() + "|" + String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
//                        txtISC.getText() + "|" + String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
//                        String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
//                        txtPrecioVenta.getText() + "|" + txtValorVenta.getText()
//                        );
//                    }
//                    crea.close();
//                    JOptionPane.showMessageDialog(this, "Factura Electrónica Generada");
//                }   
//            } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, "No se pudo");
//            }
//        }
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
        cbxBuscarDocumento = new javax.swing.JComboBox();
        panelCPT50 = new javax.swing.JPanel();
        txtBuscarDocumento = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Factura_Boleta = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel84 = new javax.swing.JPanel();
        lblLineCre = new javax.swing.JLabel();
        lblCredito = new javax.swing.JLabel();
        jPanel85 = new javax.swing.JPanel();
        lblLineDeb = new javax.swing.JLabel();
        lblDebito = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxTipoNotaCredito = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        lblFechaEmision = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbxTipoMoneda = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        cbxDocumento = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbxTipoDocumento = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panelCPT = new javax.swing.JPanel();
        txtNroDocumento = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        panelCPT1 = new javax.swing.JPanel();
        txtApeNom = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        cbxGravado = new javax.swing.JComboBox();
        jPanel19 = new javax.swing.JPanel();
        cbxCodUnidad = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        panelCPT4 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        panelCPT5 = new javax.swing.JPanel();
        txtCodProducto = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        panelCPT6 = new javax.swing.JPanel();
        txtCodProductoSunat = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        panelCPT7 = new javax.swing.JPanel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        panelCPT8 = new javax.swing.JPanel();
        txtValorU = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        panelCPT9 = new javax.swing.JPanel();
        txtPorcenDscto = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        panelCPT10 = new javax.swing.JPanel();
        txtDscto = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        panelCPT11 = new javax.swing.JPanel();
        txtIGV = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        cbxAfecIGV = new javax.swing.JComboBox();
        tablaS = new javax.swing.JScrollPane();
        tbFacturacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel39 = new javax.swing.JPanel();
            jLabel32 = new javax.swing.JLabel();
            panelCPT18 = new javax.swing.JPanel();
            txtValorVentaGravada = new javax.swing.JTextField();
            jPanel45 = new javax.swing.JPanel();
            jLabel38 = new javax.swing.JLabel();
            panelCPT24 = new javax.swing.JPanel();
            txtImporteTotalVenta = new javax.swing.JTextField();
            jPanel40 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            panelCPT19 = new javax.swing.JPanel();
            txtValorVentaInafectada = new javax.swing.JTextField();
            jPanel44 = new javax.swing.JPanel();
            jLabel37 = new javax.swing.JLabel();
            panelCPT23 = new javax.swing.JPanel();
            txtVentaExonerada = new javax.swing.JTextField();
            jPanel43 = new javax.swing.JPanel();
            jLabel36 = new javax.swing.JLabel();
            panelCPT22 = new javax.swing.JPanel();
            txtMtoISC = new javax.swing.JTextField();
            jPanel38 = new javax.swing.JPanel();
            jLabel31 = new javax.swing.JLabel();
            panelCPT17 = new javax.swing.JPanel();
            txtMtoIGVCredito = new javax.swing.JTextField();
            jPanel37 = new javax.swing.JPanel();
            jLabel30 = new javax.swing.JLabel();
            panelCPT16 = new javax.swing.JPanel();
            txtOtrosCargosCredito = new javax.swing.JTextField();
            jPanel42 = new javax.swing.JPanel();
            jLabel35 = new javax.swing.JLabel();
            panelCPT21 = new javax.swing.JPanel();
            txtOtrosTributosCredito = new javax.swing.JTextField();
            jPanel46 = new javax.swing.JPanel();
            btnGenerarDoc = new javax.swing.JButton();
            jPanel31 = new javax.swing.JPanel();
            jLabel25 = new javax.swing.JLabel();
            txtSerie = new javax.swing.JLabel();
            txtSerie1 = new javax.swing.JLabel();
            lblNroCorrelativo = new javax.swing.JLabel();
            jPanel32 = new javax.swing.JPanel();
            jLabel26 = new javax.swing.JLabel();
            panelCPT13 = new javax.swing.JPanel();
            txtDescripcionSustento = new javax.swing.JTextField();
            jPanel47 = new javax.swing.JPanel();
            btnAgregarFactura = new javax.swing.JButton();
            jPanel33 = new javax.swing.JPanel();
            jLabel27 = new javax.swing.JLabel();
            panelCPT14 = new javax.swing.JPanel();
            txtISC = new javax.swing.JTextField();
            jPanel34 = new javax.swing.JPanel();
            jLabel28 = new javax.swing.JLabel();
            cbxAfecISC = new javax.swing.JComboBox();
            jPanel48 = new javax.swing.JPanel();
            jLabel39 = new javax.swing.JLabel();
            panelCPT25 = new javax.swing.JPanel();
            txtPrecioVenta = new javax.swing.JTextField();
            jPanel49 = new javax.swing.JPanel();
            jLabel40 = new javax.swing.JLabel();
            panelCPT26 = new javax.swing.JPanel();
            txtValorVenta = new javax.swing.JTextField();
            jPanel51 = new javax.swing.JPanel();
            jLabel41 = new javax.swing.JLabel();
            panelCPT27 = new javax.swing.JPanel();
            lblCorrelativoCreditoF = new javax.swing.JTextField();
            lblIdCredito = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            jPanel11 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            cbxTipoNotaDebito = new javax.swing.JComboBox();
            jPanel12 = new javax.swing.JPanel();
            lblFechaEmisionDebito = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jPanel16 = new javax.swing.JPanel();
            jLabel8 = new javax.swing.JLabel();
            cbxTipoMonedaDebito = new javax.swing.JComboBox();
            jPanel36 = new javax.swing.JPanel();
            cbxDocumentoDebito = new javax.swing.JComboBox();
            jLabel9 = new javax.swing.JLabel();
            jPanel41 = new javax.swing.JPanel();
            jLabel13 = new javax.swing.JLabel();
            cbxTipoDocumentoDebito = new javax.swing.JComboBox();
            jPanel52 = new javax.swing.JPanel();
            jLabel29 = new javax.swing.JLabel();
            panelCPT2 = new javax.swing.JPanel();
            txtNroDocumentoDebito = new javax.swing.JTextField();
            jPanel53 = new javax.swing.JPanel();
            jLabel34 = new javax.swing.JLabel();
            panelCPT15 = new javax.swing.JPanel();
            txtApeNomDebito = new javax.swing.JTextField();
            jPanel55 = new javax.swing.JPanel();
            cbxGravadoDebito = new javax.swing.JComboBox();
            jPanel56 = new javax.swing.JPanel();
            cbxCodUnidadDebito = new javax.swing.JComboBox();
            jLabel43 = new javax.swing.JLabel();
            jPanel57 = new javax.swing.JPanel();
            jLabel44 = new javax.swing.JLabel();
            panelCPT28 = new javax.swing.JPanel();
            txtCantidadDebito = new javax.swing.JTextField();
            jPanel58 = new javax.swing.JPanel();
            jLabel45 = new javax.swing.JLabel();
            panelCPT29 = new javax.swing.JPanel();
            txtCodProducto1 = new javax.swing.JTextField();
            jPanel59 = new javax.swing.JPanel();
            jLabel46 = new javax.swing.JLabel();
            panelCPT30 = new javax.swing.JPanel();
            txtCodProductoSunat1 = new javax.swing.JTextField();
            jPanel60 = new javax.swing.JPanel();
            jLabel47 = new javax.swing.JLabel();
            panelCPT31 = new javax.swing.JPanel();
            txtDescripcion1 = new javax.swing.JTextField();
            jPanel61 = new javax.swing.JPanel();
            jLabel48 = new javax.swing.JLabel();
            panelCPT32 = new javax.swing.JPanel();
            txtValorUDebito = new javax.swing.JTextField();
            jPanel62 = new javax.swing.JPanel();
            jLabel49 = new javax.swing.JLabel();
            panelCPT33 = new javax.swing.JPanel();
            txtPorcenDsctoDebito = new javax.swing.JTextField();
            jPanel63 = new javax.swing.JPanel();
            jLabel50 = new javax.swing.JLabel();
            panelCPT34 = new javax.swing.JPanel();
            txtDsctoDebito = new javax.swing.JTextField();
            jPanel64 = new javax.swing.JPanel();
            jLabel51 = new javax.swing.JLabel();
            panelCPT35 = new javax.swing.JPanel();
            txtIGVDebito = new javax.swing.JTextField();
            jPanel65 = new javax.swing.JPanel();
            jLabel52 = new javax.swing.JLabel();
            cbxAfecIGVDebito = new javax.swing.JComboBox();
            tablaS1 = new javax.swing.JScrollPane();
            tbFacturacionDebito = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel67 = new javax.swing.JPanel();
                jLabel53 = new javax.swing.JLabel();
                panelCPT36 = new javax.swing.JPanel();
                txtValorVentaGravadaDebito = new javax.swing.JTextField();
                jPanel68 = new javax.swing.JPanel();
                jLabel54 = new javax.swing.JLabel();
                panelCPT37 = new javax.swing.JPanel();
                txtImporteTotalVentaDebito = new javax.swing.JTextField();
                jPanel69 = new javax.swing.JPanel();
                jLabel55 = new javax.swing.JLabel();
                panelCPT38 = new javax.swing.JPanel();
                txtValorVentaInafectadaDebito = new javax.swing.JTextField();
                jPanel70 = new javax.swing.JPanel();
                jLabel56 = new javax.swing.JLabel();
                panelCPT39 = new javax.swing.JPanel();
                txtVentaExoneradaDebito = new javax.swing.JTextField();
                jPanel71 = new javax.swing.JPanel();
                jLabel57 = new javax.swing.JLabel();
                panelCPT40 = new javax.swing.JPanel();
                txtMtoISCDebito = new javax.swing.JTextField();
                jPanel72 = new javax.swing.JPanel();
                jLabel58 = new javax.swing.JLabel();
                panelCPT41 = new javax.swing.JPanel();
                txtMtoIGVDebito = new javax.swing.JTextField();
                jPanel73 = new javax.swing.JPanel();
                jLabel59 = new javax.swing.JLabel();
                panelCPT42 = new javax.swing.JPanel();
                txtOtrosCargosDebito = new javax.swing.JTextField();
                jPanel74 = new javax.swing.JPanel();
                jLabel60 = new javax.swing.JLabel();
                panelCPT43 = new javax.swing.JPanel();
                txtOtrosTributosDebito = new javax.swing.JTextField();
                jPanel75 = new javax.swing.JPanel();
                btnGenerarDoc2 = new javax.swing.JButton();
                jPanel76 = new javax.swing.JPanel();
                jLabel61 = new javax.swing.JLabel();
                txtSerieDebito = new javax.swing.JLabel();
                txtSerie3 = new javax.swing.JLabel();
                lblNroCorrelativoDebito = new javax.swing.JLabel();
                jPanel77 = new javax.swing.JPanel();
                jLabel62 = new javax.swing.JLabel();
                panelCPT45 = new javax.swing.JPanel();
                txtDescripcionDebito = new javax.swing.JTextField();
                jPanel78 = new javax.swing.JPanel();
                btnGenerarDoc3 = new javax.swing.JButton();
                jPanel79 = new javax.swing.JPanel();
                jLabel63 = new javax.swing.JLabel();
                panelCPT46 = new javax.swing.JPanel();
                txtISCDebito = new javax.swing.JTextField();
                jPanel80 = new javax.swing.JPanel();
                jLabel64 = new javax.swing.JLabel();
                cbxAfecISCDebito = new javax.swing.JComboBox();
                jPanel81 = new javax.swing.JPanel();
                jLabel65 = new javax.swing.JLabel();
                panelCPT47 = new javax.swing.JPanel();
                txtPrecioVenta1 = new javax.swing.JTextField();
                jPanel82 = new javax.swing.JPanel();
                jLabel66 = new javax.swing.JLabel();
                panelCPT48 = new javax.swing.JPanel();
                txtValorVentaDebito = new javax.swing.JTextField();
                jPanel83 = new javax.swing.JPanel();
                jLabel67 = new javax.swing.JLabel();
                panelCPT49 = new javax.swing.JPanel();
                lblCorrelativoDebitoF = new javax.swing.JTextField();
                lblIdDebito = new javax.swing.JLabel();

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
                    .addComponent(txtBuscarDocumento)
                );
                panelCPT50Layout.setVerticalGroup(
                    panelCPT50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT50Layout.createSequentialGroup()
                        .addComponent(txtBuscarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel69.setForeground(new java.awt.Color(255, 255, 255));
                jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel69.setText("Nro de Documento ");

                jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel68.setForeground(new java.awt.Color(255, 255, 255));
                jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel68.setText("Documento");

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
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxBuscarDocumento, 0, 111, Short.MAX_VALUE)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(panelCPT50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(351, 351, 351)
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
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(panelCPT50, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                tb_Factura_Boleta.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Factura_BoletaKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tb_Factura_Boleta);

                javax.swing.GroupLayout BUSCAR_FACTURA_BOLETALayout = new javax.swing.GroupLayout(BUSCAR_FACTURA_BOLETA.getContentPane());
                BUSCAR_FACTURA_BOLETA.getContentPane().setLayout(BUSCAR_FACTURA_BOLETALayout);
                BUSCAR_FACTURA_BOLETALayout.setHorizontalGroup(
                    BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                );
                BUSCAR_FACTURA_BOLETALayout.setVerticalGroup(
                    BUSCAR_FACTURA_BOLETALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BUSCAR_FACTURA_BOLETALayout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
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
                jPanel84.setBounds(20, 80, 660, 30);

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
                jPanel85.setBounds(680, 80, 680, 30);

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                jPanel6.setDoubleBuffered(false);
                jPanel6.setFocusable(false);

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 51, 51));
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Tipo de Nota:");

                cbxTipoNotaCredito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoNotaCredito.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoNotaCredito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 ANULACIÓN DE LA OPERACIÓN", "02 ANULACIÓN POR ERROR EN EL RUC ", "03 CORRECCIÓN POR ERROR EN LA DESCRIPCIÓN", "04 DESCUENTO GLOBAL", "05 DESCUENTO POR ITEM", "06 DEVOLUCIÓN TOTAL", "07 DEVOLUCIÓN POR ITEM", "08 BONIFICACIÓN ", "09 DISMINUCIÓN EN EL VALOR", "10 OTROS CONCEPTOS" }));
                cbxTipoNotaCredito.setBorder(null);
                cbxTipoNotaCredito.setLightWeightPopupEnabled(false);
                cbxTipoNotaCredito.setOpaque(false);
                cbxTipoNotaCredito.setRequestFocusEnabled(false);
                cbxTipoNotaCredito.setVerifyInputWhenFocusTarget(false);
                cbxTipoNotaCredito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoNotaCreditoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoNotaCredito, 0, 255, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoNotaCredito)
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                cbxTipoMoneda.setEnabled(false);
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
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                jLabel7.setText("Documento que modifica");

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

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));
                jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel10.setForeground(new java.awt.Color(255, 51, 51));
                jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel10.setText("Tipo Documento");

                cbxTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoDocumento.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                cbxTipoDocumento.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxTipoDocumento.setEnabled(false);
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
                            .addComponent(cbxTipoDocumento, 0, 0, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoDocumento)
                        .addGap(6, 6, 6))
                );

                jPanel14.setBackground(new java.awt.Color(255, 255, 255));
                jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel11.setForeground(new java.awt.Color(255, 51, 51));
                jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel11.setText("Nro de Documento Cliente");

                panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNroDocumento.setEditable(false);
                txtNroDocumento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtNroDocumento.setForeground(new java.awt.Color(51, 51, 51));
                txtNroDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNroDocumento.setBorder(null);
                txtNroDocumento.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNroDocumentoCaretUpdate(evt);
                    }
                });
                txtNroDocumento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtNroDocumentoActionPerformed(evt);
                    }
                });
                txtNroDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNroDocumentoKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                panelCPT.setLayout(panelCPTLayout);
                panelCPTLayout.setHorizontalGroup(
                    panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPTLayout.setVerticalGroup(
                    panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPTLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel11)
                        .addGap(3, 3, 3)
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

                txtApeNom.setEditable(false);
                txtApeNom.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtApeNom.setForeground(new java.awt.Color(51, 51, 51));
                txtApeNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtApeNom.setBorder(null);
                txtApeNom.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtApeNomCaretUpdate(evt);
                    }
                });
                txtApeNom.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtApeNomActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                panelCPT1.setLayout(panelCPT1Layout);
                panelCPT1Layout.setHorizontalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApeNom, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT1Layout.setVerticalGroup(
                    panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtApeNom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(7, 7, 7)
                        .addComponent(jLabel12)
                        .addGap(3, 3, 3)
                        .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
                txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCantidad.setBorder(null);
                txtCantidad.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCantidadCaretUpdate(evt);
                    }
                });
                txtCantidad.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCantidadActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT4Layout = new javax.swing.GroupLayout(panelCPT4);
                panelCPT4.setLayout(panelCPT4Layout);
                panelCPT4Layout.setHorizontalGroup(
                    panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT4Layout.setVerticalGroup(
                    panelCPT4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(txtCodProducto)
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
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
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

                txtCodProductoSunat.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtCodProductoSunat.setForeground(new java.awt.Color(51, 51, 51));
                txtCodProductoSunat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCodProductoSunat.setBorder(null);
                txtCodProductoSunat.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCodProductoSunatCaretUpdate(evt);
                    }
                });
                txtCodProductoSunat.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodProductoSunatActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT6Layout = new javax.swing.GroupLayout(panelCPT6);
                panelCPT6.setLayout(panelCPT6Layout);
                panelCPT6Layout.setHorizontalGroup(
                    panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT6Layout.createSequentialGroup()
                        .addComponent(txtCodProductoSunat)
                        .addContainerGap())
                );
                panelCPT6Layout.setVerticalGroup(
                    panelCPT6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT6Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCodProductoSunat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
                jPanel24.setLayout(jPanel24Layout);
                jPanel24Layout.setHorizontalGroup(
                    jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addComponent(panelCPT6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                javax.swing.GroupLayout panelCPT7Layout = new javax.swing.GroupLayout(panelCPT7);
                panelCPT7.setLayout(panelCPT7Layout);
                panelCPT7Layout.setHorizontalGroup(
                    panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT7Layout.setVerticalGroup(
                    panelCPT7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT7Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                jPanel25.setLayout(jPanel25Layout);
                jPanel25Layout.setHorizontalGroup(
                    jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
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

                txtValorU.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtValorU.setForeground(new java.awt.Color(51, 51, 51));
                txtValorU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorU.setText("0.00");
                txtValorU.setBorder(null);
                txtValorU.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorUCaretUpdate(evt);
                    }
                });
                txtValorU.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorUActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT8Layout = new javax.swing.GroupLayout(panelCPT8);
                panelCPT8.setLayout(panelCPT8Layout);
                panelCPT8Layout.setHorizontalGroup(
                    panelCPT8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT8Layout.setVerticalGroup(
                    panelCPT8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT8Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorU, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                txtPorcenDscto.setEditable(false);
                txtPorcenDscto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPorcenDscto.setForeground(new java.awt.Color(51, 51, 51));
                txtPorcenDscto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPorcenDscto.setText("0.00");
                txtPorcenDscto.setBorder(null);
                txtPorcenDscto.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPorcenDsctoCaretUpdate(evt);
                    }
                });
                txtPorcenDscto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPorcenDsctoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT9Layout = new javax.swing.GroupLayout(panelCPT9);
                panelCPT9.setLayout(panelCPT9Layout);
                panelCPT9Layout.setHorizontalGroup(
                    panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPorcenDscto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT9Layout.setVerticalGroup(
                    panelCPT9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPorcenDscto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                txtDscto.setEditable(false);
                txtDscto.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDscto.setForeground(new java.awt.Color(51, 51, 51));
                txtDscto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDscto.setText("0.00");
                txtDscto.setBorder(null);
                txtDscto.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDsctoCaretUpdate(evt);
                    }
                });
                txtDscto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDsctoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT10Layout = new javax.swing.GroupLayout(panelCPT10);
                panelCPT10.setLayout(panelCPT10Layout);
                panelCPT10Layout.setHorizontalGroup(
                    panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDscto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT10Layout.setVerticalGroup(
                    panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT10Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDscto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                txtIGV.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtIGV.setForeground(new java.awt.Color(51, 51, 51));
                txtIGV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtIGV.setText("0.00");
                txtIGV.setBorder(null);
                txtIGV.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIGVCaretUpdate(evt);
                    }
                });
                txtIGV.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtIGVActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT11Layout = new javax.swing.GroupLayout(panelCPT11);
                panelCPT11.setLayout(panelCPT11Layout);
                panelCPT11Layout.setHorizontalGroup(
                    panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIGV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT11Layout.setVerticalGroup(
                    panelCPT11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT11Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                cbxAfecIGV.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxAfecIGV.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecIGV.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10 GRAVADO-OPERACIÓN ONEROSA", "11 GRAVADO-RETIRO POR PREMIO", "12 GRAVADO-RETIRO POR DONACIÓN", "13 GRAVADO-RETIRO", "14 GRAVADO-RETIRO POR PUBLICIDAD", "15 GRAVADO-BONIFICACIONES", "16 GRAVADO-RETIRO POR ENTREGA A TRABAJADORES", "20 EXONERADO-OPERACIÓN ONEROSA", "30 INAFECTO-OPERACIÓN ONEROSA", "31 INAFECTO-RETIRO POR BONIFICACIÓN", "32 INAFECTO-RETIRO", "33 INAFECTO-RETIRO POR MUESTRAS MÉDICAS", "34 INAFECTO-RETIRO POR CONVENIO COLECTIVO", "35 INAFECTO-RETIRO POR PREMIO", "36 INAFECTO-RETIRO POR PUBLICIDAD", "40 EXPORTACIÓN" }));
                cbxAfecIGV.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecIGV.setLightWeightPopupEnabled(false);
                cbxAfecIGV.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecIGVActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAfecIGV, 0, 362, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecIGV)
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

                jPanel39.setBackground(new java.awt.Color(255, 255, 255));
                jPanel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel32.setForeground(new java.awt.Color(255, 51, 51));
                jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel32.setText("Total Valor de Ventas Gravadas");

                panelCPT18.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaGravada.setEditable(false);
                txtValorVentaGravada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaGravada.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaGravada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaGravada.setText("0.00");
                txtValorVentaGravada.setBorder(null);
                txtValorVentaGravada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaGravadaCaretUpdate(evt);
                    }
                });
                txtValorVentaGravada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaGravadaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT18Layout = new javax.swing.GroupLayout(panelCPT18);
                panelCPT18.setLayout(panelCPT18Layout);
                panelCPT18Layout.setHorizontalGroup(
                    panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaGravada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT18Layout.setVerticalGroup(
                    panelCPT18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT18Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaGravada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(panelCPT18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel45.setBackground(new java.awt.Color(255, 255, 255));
                jPanel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel38.setForeground(new java.awt.Color(255, 51, 51));
                jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel38.setText("Importe Total de Venta");

                panelCPT24.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtImporteTotalVenta.setEditable(false);
                txtImporteTotalVenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtImporteTotalVenta.setForeground(new java.awt.Color(51, 51, 51));
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

                javax.swing.GroupLayout panelCPT24Layout = new javax.swing.GroupLayout(panelCPT24);
                panelCPT24.setLayout(panelCPT24Layout);
                panelCPT24Layout.setHorizontalGroup(
                    panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT24Layout.setVerticalGroup(
                    panelCPT24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT24Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtImporteTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
                jPanel45.setLayout(jPanel45Layout);
                jPanel45Layout.setHorizontalGroup(
                    jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(panelCPT24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel45Layout.setVerticalGroup(
                    jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel40.setBackground(new java.awt.Color(255, 255, 255));
                jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel33.setForeground(new java.awt.Color(255, 51, 51));
                jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel33.setText("Total Valor de Ventas Inafectadas");

                panelCPT19.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaInafectada.setEditable(false);
                txtValorVentaInafectada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaInafectada.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaInafectada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaInafectada.setText("0.00");
                txtValorVentaInafectada.setBorder(null);
                txtValorVentaInafectada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaInafectadaCaretUpdate(evt);
                    }
                });
                txtValorVentaInafectada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaInafectadaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT19Layout = new javax.swing.GroupLayout(panelCPT19);
                panelCPT19.setLayout(panelCPT19Layout);
                panelCPT19Layout.setHorizontalGroup(
                    panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaInafectada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT19Layout.setVerticalGroup(
                    panelCPT19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT19Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaInafectada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(panelCPT19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel44.setBackground(new java.awt.Color(255, 255, 255));
                jPanel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel37.setForeground(new java.awt.Color(255, 51, 51));
                jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel37.setText("Total de Ventas Exoneradas");

                panelCPT23.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtVentaExonerada.setEditable(false);
                txtVentaExonerada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtVentaExonerada.setForeground(new java.awt.Color(51, 51, 51));
                txtVentaExonerada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtVentaExonerada.setText("0.00");
                txtVentaExonerada.setBorder(null);
                txtVentaExonerada.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtVentaExoneradaCaretUpdate(evt);
                    }
                });
                txtVentaExonerada.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtVentaExoneradaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT23Layout = new javax.swing.GroupLayout(panelCPT23);
                panelCPT23.setLayout(panelCPT23Layout);
                panelCPT23Layout.setHorizontalGroup(
                    panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVentaExonerada, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT23Layout.setVerticalGroup(
                    panelCPT23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT23Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtVentaExonerada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
                jPanel44.setLayout(jPanel44Layout);
                jPanel44Layout.setHorizontalGroup(
                    jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(panelCPT23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel44Layout.setVerticalGroup(
                    jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel43.setBackground(new java.awt.Color(255, 255, 255));
                jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(102, 102, 102));
                jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel36.setText("Sumatoria ISC");

                panelCPT22.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoISC.setEditable(false);
                txtMtoISC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoISC.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoISC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoISC.setText("0.00");
                txtMtoISC.setBorder(null);
                txtMtoISC.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoISCCaretUpdate(evt);
                    }
                });
                txtMtoISC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoISCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT22Layout = new javax.swing.GroupLayout(panelCPT22);
                panelCPT22.setLayout(panelCPT22Layout);
                panelCPT22Layout.setHorizontalGroup(
                    panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoISC, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT22Layout.setVerticalGroup(
                    panelCPT22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT22Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoISC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
                jPanel43.setLayout(jPanel43Layout);
                jPanel43Layout.setHorizontalGroup(
                    jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel43Layout.setVerticalGroup(
                    jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel38.setBackground(new java.awt.Color(255, 255, 255));
                jPanel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(102, 102, 102));
                jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel31.setText("Sumatoria IGV");

                panelCPT17.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoIGVCredito.setEditable(false);
                txtMtoIGVCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoIGVCredito.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoIGVCredito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoIGVCredito.setText("0.00");
                txtMtoIGVCredito.setBorder(null);
                txtMtoIGVCredito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoIGVCreditoCaretUpdate(evt);
                    }
                });
                txtMtoIGVCredito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoIGVCreditoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT17Layout = new javax.swing.GroupLayout(panelCPT17);
                panelCPT17.setLayout(panelCPT17Layout);
                panelCPT17Layout.setHorizontalGroup(
                    panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoIGVCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT17Layout.setVerticalGroup(
                    panelCPT17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT17Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoIGVCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                jPanel38.setLayout(jPanel38Layout);
                jPanel38Layout.setHorizontalGroup(
                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel38Layout.setVerticalGroup(
                    jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel37.setBackground(new java.awt.Color(255, 255, 255));
                jPanel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(102, 102, 102));
                jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel30.setText("Sum. otros Cargos");

                panelCPT16.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosCargosCredito.setEditable(false);
                txtOtrosCargosCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosCargosCredito.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosCargosCredito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosCargosCredito.setText("0.00");
                txtOtrosCargosCredito.setBorder(null);
                txtOtrosCargosCredito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosCargosCreditoCaretUpdate(evt);
                    }
                });
                txtOtrosCargosCredito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosCargosCreditoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT16Layout = new javax.swing.GroupLayout(panelCPT16);
                panelCPT16.setLayout(panelCPT16Layout);
                panelCPT16Layout.setHorizontalGroup(
                    panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosCargosCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT16Layout.setVerticalGroup(
                    panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT16Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosCargosCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                jPanel37.setLayout(jPanel37Layout);
                jPanel37Layout.setHorizontalGroup(
                    jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                );
                jPanel37Layout.setVerticalGroup(
                    jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel42.setBackground(new java.awt.Color(255, 255, 255));
                jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel35.setForeground(new java.awt.Color(102, 102, 102));
                jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel35.setText("Sum. otros Tributos");

                panelCPT21.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosTributosCredito.setEditable(false);
                txtOtrosTributosCredito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosTributosCredito.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosTributosCredito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosTributosCredito.setText("0.00");
                txtOtrosTributosCredito.setBorder(null);
                txtOtrosTributosCredito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosTributosCreditoCaretUpdate(evt);
                    }
                });
                txtOtrosTributosCredito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosTributosCreditoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT21Layout = new javax.swing.GroupLayout(panelCPT21);
                panelCPT21.setLayout(panelCPT21Layout);
                panelCPT21Layout.setHorizontalGroup(
                    panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosTributosCredito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT21Layout.setVerticalGroup(
                    panelCPT21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT21Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosTributosCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
                jPanel42.setLayout(jPanel42Layout);
                jPanel42Layout.setHorizontalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel42Layout.setVerticalGroup(
                    jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel46.setBackground(new java.awt.Color(41, 127, 184));
                jPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnGenerarDoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnGenerarDoc.setForeground(new java.awt.Color(255, 255, 255));
                btnGenerarDoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
                btnGenerarDoc.setMnemonic('G');
                btnGenerarDoc.setText("<html>Generar Nota <br>de Crédito</html>");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGenerarDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel46Layout.setVerticalGroup(
                    jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                );

                jPanel31.setBackground(new java.awt.Color(255, 255, 255));
                jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel25.setForeground(new java.awt.Color(255, 51, 51));
                jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel25.setText("Serie-Nro de Documento que modifica");

                txtSerie.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerie.setForeground(new java.awt.Color(51, 51, 51));
                txtSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                txtSerie1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerie1.setForeground(new java.awt.Color(51, 51, 51));
                txtSerie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                txtSerie1.setText("-");

                lblNroCorrelativo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                lblNroCorrelativo.setForeground(new java.awt.Color(51, 51, 51));
                lblNroCorrelativo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
                jPanel31.setLayout(jPanel31Layout);
                jPanel31Layout.setHorizontalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSerie1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel31Layout.setVerticalGroup(
                    jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSerie)
                                    .addComponent(txtSerie1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblNroCorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );

                jPanel32.setBackground(new java.awt.Color(255, 255, 255));
                jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(255, 51, 51));
                jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel26.setText("Descripción de motivo o sustento");

                panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDescripcionSustento.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtDescripcionSustento.setForeground(new java.awt.Color(51, 51, 51));
                txtDescripcionSustento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDescripcionSustento.setBorder(null);
                txtDescripcionSustento.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDescripcionSustentoCaretUpdate(evt);
                    }
                });
                txtDescripcionSustento.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDescripcionSustentoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
                panelCPT13.setLayout(panelCPT13Layout);
                panelCPT13Layout.setHorizontalGroup(
                    panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionSustento, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT13Layout.setVerticalGroup(
                    panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT13Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDescripcionSustento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                jPanel32.setLayout(jPanel32Layout);
                jPanel32Layout.setHorizontalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

                jPanel47.setBackground(new java.awt.Color(41, 127, 184));
                jPanel47.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnAgregarFactura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnAgregarFactura.setForeground(new java.awt.Color(255, 255, 255));
                btnAgregarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
                btnAgregarFactura.setMnemonic('G');
                btnAgregarFactura.setText("<html>Buscar la Factura o <br>Boleta Electrónica</html>");
                btnAgregarFactura.setBorderPainted(false);
                btnAgregarFactura.setContentAreaFilled(false);
                btnAgregarFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAgregarFactura.setIconTextGap(20);
                btnAgregarFactura.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAgregarFacturaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                jPanel47.setLayout(jPanel47Layout);
                jPanel47Layout.setHorizontalGroup(
                    jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarFactura, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                jPanel47Layout.setVerticalGroup(
                    jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(btnAgregarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                jPanel33.setBackground(new java.awt.Color(255, 255, 255));
                jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(102, 102, 102));
                jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel27.setText("ISC");

                panelCPT14.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtISC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtISC.setForeground(new java.awt.Color(51, 51, 51));
                txtISC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtISC.setText("0.00");
                txtISC.setBorder(null);
                txtISC.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtISCCaretUpdate(evt);
                    }
                });
                txtISC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtISCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT14Layout = new javax.swing.GroupLayout(panelCPT14);
                panelCPT14.setLayout(panelCPT14Layout);
                panelCPT14Layout.setHorizontalGroup(
                    panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT14Layout.createSequentialGroup()
                        .addComponent(txtISC)
                        .addContainerGap())
                );
                panelCPT14Layout.setVerticalGroup(
                    panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT14Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtISC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                jPanel33.setLayout(jPanel33Layout);
                jPanel33Layout.setHorizontalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel33Layout.setVerticalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel27)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel34.setBackground(new java.awt.Color(255, 255, 255));
                jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(102, 102, 102));
                jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel28.setText("Afec. ISC");

                cbxAfecISC.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxAfecISC.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecISC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 SISTEMA AL VALOR", "02 APLICACIÓN DEL MONTO FIJO", "03 SISTEMA DE PRECIOS DE VENTA AL PÚBLICO" }));
                cbxAfecISC.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecISC.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecISCActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
                jPanel34.setLayout(jPanel34Layout);
                jPanel34Layout.setHorizontalGroup(
                    jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAfecISC, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel34Layout.setVerticalGroup(
                    jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecISC)
                        .addContainerGap())
                );

                jPanel48.setBackground(new java.awt.Color(255, 255, 255));
                jPanel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel39.setForeground(new java.awt.Color(255, 51, 51));
                jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel39.setText("Precio Venta");

                panelCPT25.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPrecioVenta.setForeground(new java.awt.Color(51, 51, 51));
                txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPrecioVenta.setText("0.00");
                txtPrecioVenta.setBorder(null);
                txtPrecioVenta.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPrecioVentaCaretUpdate(evt);
                    }
                });
                txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPrecioVentaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT25Layout = new javax.swing.GroupLayout(panelCPT25);
                panelCPT25.setLayout(panelCPT25Layout);
                panelCPT25Layout.setHorizontalGroup(
                    panelCPT25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT25Layout.setVerticalGroup(
                    panelCPT25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT25Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                jPanel48.setLayout(jPanel48Layout);
                jPanel48Layout.setHorizontalGroup(
                    jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(panelCPT25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                );
                jPanel48Layout.setVerticalGroup(
                    jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel39)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel49.setBackground(new java.awt.Color(255, 255, 255));
                jPanel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel40.setForeground(new java.awt.Color(255, 51, 51));
                jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel40.setText("Valor de Venta");

                panelCPT26.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVenta.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtValorVenta.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVenta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVenta.setText("0.00");
                txtValorVenta.setBorder(null);
                txtValorVenta.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaCaretUpdate(evt);
                    }
                });
                txtValorVenta.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT26Layout = new javax.swing.GroupLayout(panelCPT26);
                panelCPT26.setLayout(panelCPT26Layout);
                panelCPT26Layout.setHorizontalGroup(
                    panelCPT26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVenta, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT26Layout.setVerticalGroup(
                    panelCPT26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT26Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
                jPanel49.setLayout(jPanel49Layout);
                jPanel49Layout.setHorizontalGroup(
                    jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel49Layout.setVerticalGroup(
                    jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel40)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel51.setBackground(new java.awt.Color(255, 255, 255));
                jPanel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel41.setForeground(new java.awt.Color(255, 51, 51));
                jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel41.setText("Correlativo de la Nota");

                panelCPT27.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                lblCorrelativoCreditoF.setEditable(false);
                lblCorrelativoCreditoF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lblCorrelativoCreditoF.setForeground(new java.awt.Color(51, 51, 51));
                lblCorrelativoCreditoF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                lblCorrelativoCreditoF.setText("0.00");
                lblCorrelativoCreditoF.setBorder(null);
                lblCorrelativoCreditoF.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        lblCorrelativoCreditoFCaretUpdate(evt);
                    }
                });
                lblCorrelativoCreditoF.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        lblCorrelativoCreditoFActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT27Layout = new javax.swing.GroupLayout(panelCPT27);
                panelCPT27.setLayout(panelCPT27Layout);
                panelCPT27Layout.setHorizontalGroup(
                    panelCPT27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCorrelativoCreditoF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT27Layout.setVerticalGroup(
                    panelCPT27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT27Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblCorrelativoCreditoF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
                jPanel51.setLayout(jPanel51Layout);
                jPanel51Layout.setHorizontalGroup(
                    jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel51Layout.setVerticalGroup(
                    jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                lblIdCredito.setText("jLabel70");

                lblUsu.setText("Silvana");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tablaS, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(lblUsu)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblIdCredito)
                                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(25, 25, 25))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblIdCredito)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablaS, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblUsu)
                                .addGap(53, 53, 53))))
                );

                tab.addTab("NOTA DE CRÉDITO", jPanel5);

                jPanel8.setBackground(new java.awt.Color(255, 255, 255));
                jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

                jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                jPanel11.setDoubleBuffered(false);
                jPanel11.setFocusable(false);

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(255, 51, 51));
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText("Tipo de Nota:");

                cbxTipoNotaDebito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoNotaDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoNotaDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 INTERESES POR MORA", "02 AUMENTO EN EL VALOR", "03 PENALIDADES" }));
                cbxTipoNotaDebito.setBorder(null);
                cbxTipoNotaDebito.setLightWeightPopupEnabled(false);
                cbxTipoNotaDebito.setOpaque(false);
                cbxTipoNotaDebito.setRequestFocusEnabled(false);
                cbxTipoNotaDebito.setVerifyInputWhenFocusTarget(false);
                cbxTipoNotaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoNotaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTipoNotaDebito, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoNotaDebito)
                        .addContainerGap())
                );

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                lblFechaEmisionDebito.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                lblFechaEmisionDebito.setForeground(new java.awt.Color(102, 102, 102));
                lblFechaEmisionDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblFechaEmisionDebito.setText("2017-05-30");

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(255, 51, 51));
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Fecha de Emisión");

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFechaEmisionDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaEmisionDebito)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel16.setBackground(new java.awt.Color(255, 255, 255));
                jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(255, 51, 51));
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("Tipo Moneda");

                cbxTipoMonedaDebito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoMonedaDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoMonedaDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN" }));
                cbxTipoMonedaDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxTipoMonedaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoMonedaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoMonedaDebito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxTipoMonedaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel36.setBackground(new java.awt.Color(255, 255, 255));
                jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxDocumentoDebito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxDocumentoDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxDocumentoDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 FACTURA", "03 BOLETA" }));
                cbxDocumentoDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxDocumentoDebito.setEnabled(false);
                cbxDocumentoDebito.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxDocumentoDebitoItemStateChanged(evt);
                    }
                });
                cbxDocumentoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxDocumentoDebitoActionPerformed(evt);
                    }
                });

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel9.setText("Documento que modifica");

                javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
                jPanel36.setLayout(jPanel36Layout);
                jPanel36Layout.setHorizontalGroup(
                    jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxDocumentoDebito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel36Layout.setVerticalGroup(
                    jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel41.setBackground(new java.awt.Color(255, 255, 255));
                jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(255, 51, 51));
                jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel13.setText("Tipo Documento");

                cbxTipoDocumentoDebito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                cbxTipoDocumentoDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxTipoDocumentoDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                cbxTipoDocumentoDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxTipoDocumentoDebito.setEnabled(false);
                cbxTipoDocumentoDebito.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        cbxTipoDocumentoDebitoItemStateChanged(evt);
                    }
                });
                cbxTipoDocumentoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxTipoDocumentoDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
                jPanel41.setLayout(jPanel41Layout);
                jPanel41Layout.setHorizontalGroup(
                    jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoDocumentoDebito, 0, 0, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel41Layout.setVerticalGroup(
                    jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(1, 1, 1)
                        .addComponent(cbxTipoDocumentoDebito)
                        .addContainerGap())
                );

                jPanel52.setBackground(new java.awt.Color(255, 255, 255));
                jPanel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(255, 51, 51));
                jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel29.setText("Nro de Documento Cliente");

                panelCPT2.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                panelCPT2.setEnabled(false);

                txtNroDocumentoDebito.setEditable(false);
                txtNroDocumentoDebito.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtNroDocumentoDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtNroDocumentoDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNroDocumentoDebito.setBorder(null);
                txtNroDocumentoDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNroDocumentoDebitoCaretUpdate(evt);
                    }
                });
                txtNroDocumentoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtNroDocumentoDebitoActionPerformed(evt);
                    }
                });
                txtNroDocumentoDebito.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtNroDocumentoDebitoKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT2Layout = new javax.swing.GroupLayout(panelCPT2);
                panelCPT2.setLayout(panelCPT2Layout);
                panelCPT2Layout.setHorizontalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNroDocumentoDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT2Layout.setVerticalGroup(
                    panelCPT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtNroDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
                jPanel52.setLayout(jPanel52Layout);
                jPanel52Layout.setHorizontalGroup(
                    jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel52Layout.setVerticalGroup(
                    jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel29)
                        .addGap(3, 3, 3)
                        .addComponent(panelCPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel53.setBackground(new java.awt.Color(255, 255, 255));
                jPanel53.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(255, 51, 51));
                jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel34.setText("Apellidos y Nombres / Razón Social");

                panelCPT15.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                panelCPT15.setEnabled(false);

                txtApeNomDebito.setEditable(false);
                txtApeNomDebito.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtApeNomDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtApeNomDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtApeNomDebito.setBorder(null);
                txtApeNomDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtApeNomDebitoCaretUpdate(evt);
                    }
                });
                txtApeNomDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtApeNomDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT15Layout = new javax.swing.GroupLayout(panelCPT15);
                panelCPT15.setLayout(panelCPT15Layout);
                panelCPT15Layout.setHorizontalGroup(
                    panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApeNomDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT15Layout.setVerticalGroup(
                    panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT15Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtApeNomDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
                jPanel53.setLayout(jPanel53Layout);
                jPanel53Layout.setHorizontalGroup(
                    jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel53Layout.setVerticalGroup(
                    jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel34)
                        .addGap(3, 3, 3)
                        .addComponent(panelCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel55.setBackground(new java.awt.Color(255, 255, 255));
                jPanel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxGravadoDebito.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxGravadoDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxGravadoDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GRAVADO", "EXONERADO", "INAFECTO", "EXPORTACION", "GRATUITAS" }));
                cbxGravadoDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxGravadoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxGravadoDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
                jPanel55.setLayout(jPanel55Layout);
                jPanel55Layout.setHorizontalGroup(
                    jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxGravadoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel55Layout.setVerticalGroup(
                    jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(cbxGravadoDebito)
                        .addContainerGap())
                );

                jPanel56.setBackground(new java.awt.Color(255, 255, 255));
                jPanel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                cbxCodUnidadDebito.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                cbxCodUnidadDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxCodUnidadDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NIU UNIDAD", "ZZ SERVICIO", "BX CAJA", "CT CAJA DE CARTON", "LTR LITRO", "MTR METRO", "CMT CENTIMETRO", "KGM KILOGRAMO" }));
                cbxCodUnidadDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxCodUnidadDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxCodUnidadDebitoActionPerformed(evt);
                    }
                });

                jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel43.setForeground(new java.awt.Color(255, 51, 51));
                jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel43.setText("Cod. Unidad");

                javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
                jPanel56.setLayout(jPanel56Layout);
                jPanel56Layout.setHorizontalGroup(
                    jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxCodUnidadDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel56Layout.setVerticalGroup(
                    jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxCodUnidadDebito)
                        .addContainerGap())
                );

                jPanel57.setBackground(new java.awt.Color(255, 255, 255));
                jPanel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel44.setForeground(new java.awt.Color(255, 51, 51));
                jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel44.setText("Cantidad");

                panelCPT28.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCantidadDebito.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtCantidadDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtCantidadDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCantidadDebito.setBorder(null);
                txtCantidadDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCantidadDebitoCaretUpdate(evt);
                    }
                });
                txtCantidadDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCantidadDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT28Layout = new javax.swing.GroupLayout(panelCPT28);
                panelCPT28.setLayout(panelCPT28Layout);
                panelCPT28Layout.setHorizontalGroup(
                    panelCPT28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT28Layout.setVerticalGroup(
                    panelCPT28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT28Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCantidadDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
                jPanel57.setLayout(jPanel57Layout);
                jPanel57Layout.setHorizontalGroup(
                    jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel57Layout.setVerticalGroup(
                    jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel58.setBackground(new java.awt.Color(255, 255, 255));
                jPanel58.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel45.setForeground(new java.awt.Color(102, 102, 102));
                jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel45.setText("Cod Producto");

                panelCPT29.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

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

                javax.swing.GroupLayout panelCPT29Layout = new javax.swing.GroupLayout(panelCPT29);
                panelCPT29.setLayout(panelCPT29Layout);
                panelCPT29Layout.setHorizontalGroup(
                    panelCPT29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodProducto1, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT29Layout.setVerticalGroup(
                    panelCPT29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT29Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCodProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
                jPanel58.setLayout(jPanel58Layout);
                jPanel58Layout.setHorizontalGroup(
                    jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel58Layout.setVerticalGroup(
                    jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel59.setBackground(new java.awt.Color(255, 255, 255));
                jPanel59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
                jLabel46.setForeground(new java.awt.Color(102, 102, 102));
                jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel46.setText("<html><span style=\"font-size:'10px'\">Cod Producto</span> Sunat<html>");

                panelCPT30.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCodProductoSunat1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtCodProductoSunat1.setForeground(new java.awt.Color(51, 51, 51));
                txtCodProductoSunat1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCodProductoSunat1.setBorder(null);
                txtCodProductoSunat1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCodProductoSunat1CaretUpdate(evt);
                    }
                });
                txtCodProductoSunat1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodProductoSunat1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT30Layout = new javax.swing.GroupLayout(panelCPT30);
                panelCPT30.setLayout(panelCPT30Layout);
                panelCPT30Layout.setHorizontalGroup(
                    panelCPT30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodProductoSunat1, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT30Layout.setVerticalGroup(
                    panelCPT30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT30Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtCodProductoSunat1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
                jPanel59.setLayout(jPanel59Layout);
                jPanel59Layout.setHorizontalGroup(
                    jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel59Layout.setVerticalGroup(
                    jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel60.setBackground(new java.awt.Color(255, 255, 255));
                jPanel60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel47.setForeground(new java.awt.Color(255, 51, 51));
                jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel47.setText("Descripción");

                panelCPT31.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDescripcion1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtDescripcion1.setForeground(new java.awt.Color(51, 51, 51));
                txtDescripcion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDescripcion1.setBorder(null);
                txtDescripcion1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDescripcion1CaretUpdate(evt);
                    }
                });
                txtDescripcion1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDescripcion1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT31Layout = new javax.swing.GroupLayout(panelCPT31);
                panelCPT31.setLayout(panelCPT31Layout);
                panelCPT31Layout.setHorizontalGroup(
                    panelCPT31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcion1, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT31Layout.setVerticalGroup(
                    panelCPT31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT31Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDescripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
                jPanel60.setLayout(jPanel60Layout);
                jPanel60Layout.setHorizontalGroup(
                    jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel60Layout.setVerticalGroup(
                    jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel60Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel61.setBackground(new java.awt.Color(255, 255, 255));
                jPanel61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel48.setForeground(new java.awt.Color(255, 51, 51));
                jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel48.setText("Valor U.");

                panelCPT32.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorUDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtValorUDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtValorUDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorUDebito.setText("0.00");
                txtValorUDebito.setBorder(null);
                txtValorUDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorUDebitoCaretUpdate(evt);
                    }
                });
                txtValorUDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorUDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT32Layout = new javax.swing.GroupLayout(panelCPT32);
                panelCPT32.setLayout(panelCPT32Layout);
                panelCPT32Layout.setHorizontalGroup(
                    panelCPT32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorUDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT32Layout.setVerticalGroup(
                    panelCPT32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT32Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorUDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
                jPanel61.setLayout(jPanel61Layout);
                jPanel61Layout.setHorizontalGroup(
                    jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel61Layout.setVerticalGroup(
                    jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel62.setBackground(new java.awt.Color(255, 255, 255));
                jPanel62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel49.setForeground(new java.awt.Color(102, 102, 102));
                jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel49.setText("Desc. %");

                panelCPT33.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPorcenDsctoDebito.setEditable(false);
                txtPorcenDsctoDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPorcenDsctoDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtPorcenDsctoDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPorcenDsctoDebito.setText("0.00");
                txtPorcenDsctoDebito.setBorder(null);
                txtPorcenDsctoDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPorcenDsctoDebitoCaretUpdate(evt);
                    }
                });
                txtPorcenDsctoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPorcenDsctoDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT33Layout = new javax.swing.GroupLayout(panelCPT33);
                panelCPT33.setLayout(panelCPT33Layout);
                panelCPT33Layout.setHorizontalGroup(
                    panelCPT33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPorcenDsctoDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT33Layout.setVerticalGroup(
                    panelCPT33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT33Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPorcenDsctoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
                jPanel62.setLayout(jPanel62Layout);
                jPanel62Layout.setHorizontalGroup(
                    jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCPT33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel62Layout.setVerticalGroup(
                    jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel63.setBackground(new java.awt.Color(255, 255, 255));
                jPanel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel50.setForeground(new java.awt.Color(102, 102, 102));
                jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel50.setText("Dscto");

                panelCPT34.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDsctoDebito.setEditable(false);
                txtDsctoDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtDsctoDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtDsctoDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDsctoDebito.setText("0.00");
                txtDsctoDebito.setBorder(null);
                txtDsctoDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDsctoDebitoCaretUpdate(evt);
                    }
                });
                txtDsctoDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDsctoDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT34Layout = new javax.swing.GroupLayout(panelCPT34);
                panelCPT34.setLayout(panelCPT34Layout);
                panelCPT34Layout.setHorizontalGroup(
                    panelCPT34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDsctoDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT34Layout.setVerticalGroup(
                    panelCPT34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT34Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDsctoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
                jPanel63.setLayout(jPanel63Layout);
                jPanel63Layout.setHorizontalGroup(
                    jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel63Layout.setVerticalGroup(
                    jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel63Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel64.setBackground(new java.awt.Color(255, 255, 255));
                jPanel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel51.setForeground(new java.awt.Color(255, 51, 51));
                jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel51.setText("IGV");

                panelCPT35.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtIGVDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtIGVDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtIGVDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtIGVDebito.setText("0.00");
                txtIGVDebito.setBorder(null);
                txtIGVDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtIGVDebitoCaretUpdate(evt);
                    }
                });
                txtIGVDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtIGVDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT35Layout = new javax.swing.GroupLayout(panelCPT35);
                panelCPT35.setLayout(panelCPT35Layout);
                panelCPT35Layout.setHorizontalGroup(
                    panelCPT35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIGVDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT35Layout.setVerticalGroup(
                    panelCPT35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT35Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtIGVDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
                jPanel64.setLayout(jPanel64Layout);
                jPanel64Layout.setHorizontalGroup(
                    jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel64Layout.setVerticalGroup(
                    jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel64Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel65.setBackground(new java.awt.Color(255, 255, 255));
                jPanel65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel52.setForeground(new java.awt.Color(255, 51, 51));
                jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel52.setText("Afec. IGV");

                cbxAfecIGVDebito.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxAfecIGVDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecIGVDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10 GRAVADO-OPERACIÓN ONEROSA", "11 GRAVADO-RETIRO POR PREMIO", "12 GRAVADO-RETIRO POR DONACIÓN", "13 GRAVADO-RETIRO", "14 GRAVADO-RETIRO POR PUBLICIDAD", "15 GRAVADO-BONIFICACIONES", "16 GRAVADO-RETIRO POR ENTREGA A TRABAJADORES", "20 EXONERADO-OPERACIÓN ONEROSA", "30 INAFECTO-OPERACIÓN ONEROSA", "31 INAFECTO-RETIRO POR BONIFICACIÓN", "32 INAFECTO-RETIRO", "33 INAFECTO-RETIRO POR MUESTRAS MÉDICAS", "34 INAFECTO-RETIRO POR CONVENIO COLECTIVO", "35 INAFECTO-RETIRO POR PREMIO", "36 INAFECTO-RETIRO POR PUBLICIDAD", "40 EXPORTACIÓN" }));
                cbxAfecIGVDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecIGVDebito.setLightWeightPopupEnabled(false);
                cbxAfecIGVDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecIGVDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
                jPanel65.setLayout(jPanel65Layout);
                jPanel65Layout.setHorizontalGroup(
                    jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAfecIGVDebito, 0, 377, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel65Layout.setVerticalGroup(
                    jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecIGVDebito)
                        .addContainerGap())
                );

                tablaS1.setBackground(new java.awt.Color(255, 255, 255));
                tablaS1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                tablaS1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                tablaS1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                tablaS1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

                tbFacturacionDebito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                tbFacturacionDebito.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {

                    }
                ));
                tbFacturacionDebito.setGridColor(new java.awt.Color(255, 255, 255));
                tbFacturacionDebito.setRowHeight(25);
                tbFacturacionDebito.setSelectionBackground(new java.awt.Color(41, 127, 184));
                tbFacturacionDebito.getTableHeader().setReorderingAllowed(false);
                tbFacturacionDebito.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbFacturacionDebitoMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbFacturacionDebitoMousePressed(evt);
                    }
                });
                tbFacturacionDebito.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbFacturacionDebitoKeyPressed(evt);
                    }
                });
                tablaS1.setViewportView(tbFacturacionDebito);

                jPanel67.setBackground(new java.awt.Color(255, 255, 255));
                jPanel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel53.setForeground(new java.awt.Color(255, 51, 51));
                jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel53.setText("Total Valor de Ventas Gravadas");

                panelCPT36.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaGravadaDebito.setEditable(false);
                txtValorVentaGravadaDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaGravadaDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaGravadaDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaGravadaDebito.setText("0.00");
                txtValorVentaGravadaDebito.setBorder(null);
                txtValorVentaGravadaDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaGravadaDebitoCaretUpdate(evt);
                    }
                });
                txtValorVentaGravadaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaGravadaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT36Layout = new javax.swing.GroupLayout(panelCPT36);
                panelCPT36.setLayout(panelCPT36Layout);
                panelCPT36Layout.setHorizontalGroup(
                    panelCPT36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaGravadaDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT36Layout.setVerticalGroup(
                    panelCPT36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT36Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaGravadaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
                jPanel67.setLayout(jPanel67Layout);
                jPanel67Layout.setHorizontalGroup(
                    jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel67Layout.setVerticalGroup(
                    jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel68.setBackground(new java.awt.Color(255, 255, 255));
                jPanel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel54.setForeground(new java.awt.Color(255, 51, 51));
                jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel54.setText("Importe Total de Venta");

                panelCPT37.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtImporteTotalVentaDebito.setEditable(false);
                txtImporteTotalVentaDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtImporteTotalVentaDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtImporteTotalVentaDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtImporteTotalVentaDebito.setText("0.00");
                txtImporteTotalVentaDebito.setBorder(null);
                txtImporteTotalVentaDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtImporteTotalVentaDebitoCaretUpdate(evt);
                    }
                });
                txtImporteTotalVentaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtImporteTotalVentaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT37Layout = new javax.swing.GroupLayout(panelCPT37);
                panelCPT37.setLayout(panelCPT37Layout);
                panelCPT37Layout.setHorizontalGroup(
                    panelCPT37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImporteTotalVentaDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT37Layout.setVerticalGroup(
                    panelCPT37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT37Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtImporteTotalVentaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
                jPanel68.setLayout(jPanel68Layout);
                jPanel68Layout.setHorizontalGroup(
                    jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel68Layout.setVerticalGroup(
                    jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel68Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel69.setBackground(new java.awt.Color(255, 255, 255));
                jPanel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel55.setForeground(new java.awt.Color(255, 51, 51));
                jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel55.setText("Total Valor de Ventas Inafectadas");

                panelCPT38.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaInafectadaDebito.setEditable(false);
                txtValorVentaInafectadaDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtValorVentaInafectadaDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaInafectadaDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaInafectadaDebito.setText("0.00");
                txtValorVentaInafectadaDebito.setBorder(null);
                txtValorVentaInafectadaDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaInafectadaDebitoCaretUpdate(evt);
                    }
                });
                txtValorVentaInafectadaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaInafectadaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT38Layout = new javax.swing.GroupLayout(panelCPT38);
                panelCPT38.setLayout(panelCPT38Layout);
                panelCPT38Layout.setHorizontalGroup(
                    panelCPT38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaInafectadaDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT38Layout.setVerticalGroup(
                    panelCPT38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT38Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaInafectadaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
                jPanel69.setLayout(jPanel69Layout);
                jPanel69Layout.setHorizontalGroup(
                    jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel69Layout.setVerticalGroup(
                    jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel70.setBackground(new java.awt.Color(255, 255, 255));
                jPanel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel56.setForeground(new java.awt.Color(255, 51, 51));
                jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel56.setText("Total de Ventas Exoneradas");

                panelCPT39.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtVentaExoneradaDebito.setEditable(false);
                txtVentaExoneradaDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtVentaExoneradaDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtVentaExoneradaDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtVentaExoneradaDebito.setText("0.00");
                txtVentaExoneradaDebito.setBorder(null);
                txtVentaExoneradaDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtVentaExoneradaDebitoCaretUpdate(evt);
                    }
                });
                txtVentaExoneradaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtVentaExoneradaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT39Layout = new javax.swing.GroupLayout(panelCPT39);
                panelCPT39.setLayout(panelCPT39Layout);
                panelCPT39Layout.setHorizontalGroup(
                    panelCPT39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVentaExoneradaDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT39Layout.setVerticalGroup(
                    panelCPT39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT39Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtVentaExoneradaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
                jPanel70.setLayout(jPanel70Layout);
                jPanel70Layout.setHorizontalGroup(
                    jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel70Layout.setVerticalGroup(
                    jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel71.setBackground(new java.awt.Color(255, 255, 255));
                jPanel71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(102, 102, 102));
                jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel57.setText("Sumatoria ISC");

                panelCPT40.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoISCDebito.setEditable(false);
                txtMtoISCDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoISCDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoISCDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoISCDebito.setText("0.00");
                txtMtoISCDebito.setBorder(null);
                txtMtoISCDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoISCDebitoCaretUpdate(evt);
                    }
                });
                txtMtoISCDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoISCDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT40Layout = new javax.swing.GroupLayout(panelCPT40);
                panelCPT40.setLayout(panelCPT40Layout);
                panelCPT40Layout.setHorizontalGroup(
                    panelCPT40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoISCDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT40Layout.setVerticalGroup(
                    panelCPT40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT40Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoISCDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
                jPanel71.setLayout(jPanel71Layout);
                jPanel71Layout.setHorizontalGroup(
                    jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel71Layout.setVerticalGroup(
                    jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel72.setBackground(new java.awt.Color(255, 255, 255));
                jPanel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel58.setForeground(new java.awt.Color(102, 102, 102));
                jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel58.setText("Sumatoria IGV");

                panelCPT41.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtMtoIGVDebito.setEditable(false);
                txtMtoIGVDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtMtoIGVDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtMtoIGVDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtMtoIGVDebito.setText("0.00");
                txtMtoIGVDebito.setBorder(null);
                txtMtoIGVDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtMtoIGVDebitoCaretUpdate(evt);
                    }
                });
                txtMtoIGVDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtMtoIGVDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT41Layout = new javax.swing.GroupLayout(panelCPT41);
                panelCPT41.setLayout(panelCPT41Layout);
                panelCPT41Layout.setHorizontalGroup(
                    panelCPT41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMtoIGVDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT41Layout.setVerticalGroup(
                    panelCPT41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT41Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtMtoIGVDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
                jPanel72.setLayout(jPanel72Layout);
                jPanel72Layout.setHorizontalGroup(
                    jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                );
                jPanel72Layout.setVerticalGroup(
                    jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel73.setBackground(new java.awt.Color(255, 255, 255));
                jPanel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel59.setForeground(new java.awt.Color(102, 102, 102));
                jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel59.setText("Sum. otros Cargos");

                panelCPT42.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosCargosDebito.setEditable(false);
                txtOtrosCargosDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosCargosDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosCargosDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosCargosDebito.setText("0.00");
                txtOtrosCargosDebito.setBorder(null);
                txtOtrosCargosDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosCargosDebitoCaretUpdate(evt);
                    }
                });
                txtOtrosCargosDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosCargosDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT42Layout = new javax.swing.GroupLayout(panelCPT42);
                panelCPT42.setLayout(panelCPT42Layout);
                panelCPT42Layout.setHorizontalGroup(
                    panelCPT42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosCargosDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT42Layout.setVerticalGroup(
                    panelCPT42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT42Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosCargosDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
                jPanel73.setLayout(jPanel73Layout);
                jPanel73Layout.setHorizontalGroup(
                    jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel73Layout.setVerticalGroup(
                    jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel74.setBackground(new java.awt.Color(255, 255, 255));
                jPanel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel60.setForeground(new java.awt.Color(102, 102, 102));
                jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel60.setText("Sum. otros Tributos");

                panelCPT43.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtOtrosTributosDebito.setEditable(false);
                txtOtrosTributosDebito.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                txtOtrosTributosDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtOtrosTributosDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtOtrosTributosDebito.setText("0.00");
                txtOtrosTributosDebito.setBorder(null);
                txtOtrosTributosDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtOtrosTributosDebitoCaretUpdate(evt);
                    }
                });
                txtOtrosTributosDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtOtrosTributosDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT43Layout = new javax.swing.GroupLayout(panelCPT43);
                panelCPT43.setLayout(panelCPT43Layout);
                panelCPT43Layout.setHorizontalGroup(
                    panelCPT43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOtrosTributosDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT43Layout.setVerticalGroup(
                    panelCPT43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT43Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtOtrosTributosDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
                jPanel74.setLayout(jPanel74Layout);
                jPanel74Layout.setHorizontalGroup(
                    jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel74Layout.setVerticalGroup(
                    jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                jPanel75.setBackground(new java.awt.Color(41, 127, 184));
                jPanel75.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnGenerarDoc2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnGenerarDoc2.setForeground(new java.awt.Color(255, 255, 255));
                btnGenerarDoc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
                btnGenerarDoc2.setMnemonic('G');
                btnGenerarDoc2.setText("<html>Generar Nota  <br>de Débito</html>");
                btnGenerarDoc2.setBorderPainted(false);
                btnGenerarDoc2.setContentAreaFilled(false);
                btnGenerarDoc2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGenerarDoc2.setIconTextGap(20);
                btnGenerarDoc2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenerarDoc2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
                jPanel75.setLayout(jPanel75Layout);
                jPanel75Layout.setHorizontalGroup(
                    jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                );
                jPanel75Layout.setVerticalGroup(
                    jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                );

                jPanel76.setBackground(new java.awt.Color(255, 255, 255));
                jPanel76.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel61.setForeground(new java.awt.Color(255, 51, 51));
                jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel61.setText("Serie-Nro de Documento que modifica");

                txtSerieDebito.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerieDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtSerieDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                txtSerie3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                txtSerie3.setForeground(new java.awt.Color(51, 51, 51));
                txtSerie3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                txtSerie3.setText("-");

                lblNroCorrelativoDebito.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
                lblNroCorrelativoDebito.setForeground(new java.awt.Color(51, 51, 51));
                lblNroCorrelativoDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

                javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
                jPanel76.setLayout(jPanel76Layout);
                jPanel76Layout.setHorizontalGroup(
                    jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel76Layout.createSequentialGroup()
                                .addComponent(txtSerieDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSerie3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNroCorrelativoDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel76Layout.setVerticalGroup(
                    jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSerieDebito)
                                .addComponent(txtSerie3))
                            .addComponent(lblNroCorrelativoDebito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                );

                jPanel77.setBackground(new java.awt.Color(255, 255, 255));
                jPanel77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel62.setForeground(new java.awt.Color(255, 51, 51));
                jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel62.setText("Descripción de motivo o sustento");

                panelCPT45.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtDescripcionDebito.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                txtDescripcionDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtDescripcionDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDescripcionDebito.setBorder(null);
                txtDescripcionDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDescripcionDebitoCaretUpdate(evt);
                    }
                });
                txtDescripcionDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtDescripcionDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT45Layout = new javax.swing.GroupLayout(panelCPT45);
                panelCPT45.setLayout(panelCPT45Layout);
                panelCPT45Layout.setHorizontalGroup(
                    panelCPT45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT45Layout.setVerticalGroup(
                    panelCPT45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT45Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtDescripcionDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
                jPanel77.setLayout(jPanel77Layout);
                jPanel77Layout.setHorizontalGroup(
                    jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel77Layout.setVerticalGroup(
                    jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel77Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel62)
                        .addGap(3, 3, 3)
                        .addComponent(panelCPT45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel78.setBackground(new java.awt.Color(41, 127, 184));
                jPanel78.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                btnGenerarDoc3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                btnGenerarDoc3.setForeground(new java.awt.Color(255, 255, 255));
                btnGenerarDoc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-50.png"))); // NOI18N
                btnGenerarDoc3.setMnemonic('G');
                btnGenerarDoc3.setText("<html>Buscar la Factura o <br>Boleta Electrónica</html>");
                btnGenerarDoc3.setBorderPainted(false);
                btnGenerarDoc3.setContentAreaFilled(false);
                btnGenerarDoc3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGenerarDoc3.setIconTextGap(20);
                btnGenerarDoc3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGenerarDoc3ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
                jPanel78.setLayout(jPanel78Layout);
                jPanel78Layout.setHorizontalGroup(
                    jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerarDoc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                );
                jPanel78Layout.setVerticalGroup(
                    jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel78Layout.createSequentialGroup()
                        .addComponent(btnGenerarDoc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );

                jPanel79.setBackground(new java.awt.Color(255, 255, 255));
                jPanel79.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel63.setForeground(new java.awt.Color(102, 102, 102));
                jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel63.setText("ISC");

                panelCPT46.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtISCDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtISCDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtISCDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtISCDebito.setText("0.00");
                txtISCDebito.setBorder(null);
                txtISCDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtISCDebitoCaretUpdate(evt);
                    }
                });
                txtISCDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtISCDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT46Layout = new javax.swing.GroupLayout(panelCPT46);
                panelCPT46.setLayout(panelCPT46Layout);
                panelCPT46Layout.setHorizontalGroup(
                    panelCPT46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtISCDebito, javax.swing.GroupLayout.Alignment.TRAILING)
                );
                panelCPT46Layout.setVerticalGroup(
                    panelCPT46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT46Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtISCDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
                jPanel79.setLayout(jPanel79Layout);
                jPanel79Layout.setHorizontalGroup(
                    jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCPT46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel79Layout.setVerticalGroup(
                    jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel79Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel80.setBackground(new java.awt.Color(255, 255, 255));
                jPanel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel64.setForeground(new java.awt.Color(102, 102, 102));
                jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel64.setText("Afec. ISC");

                cbxAfecISCDebito.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                cbxAfecISCDebito.setForeground(new java.awt.Color(102, 102, 102));
                cbxAfecISCDebito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01 SISTEMA AL VALOR", "02 APLICACIÓN DEL MONTO FIJO", "03 SISTEMA DE PRECIOS DE VENTA AL PÚBLICO" }));
                cbxAfecISCDebito.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                cbxAfecISCDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        cbxAfecISCDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel80Layout = new javax.swing.GroupLayout(jPanel80);
                jPanel80.setLayout(jPanel80Layout);
                jPanel80Layout.setHorizontalGroup(
                    jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAfecISCDebito, 0, 0, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel80Layout.setVerticalGroup(
                    jPanel80Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel80Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxAfecISCDebito)
                        .addContainerGap())
                );

                jPanel81.setBackground(new java.awt.Color(255, 255, 255));
                jPanel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel65.setForeground(new java.awt.Color(255, 51, 51));
                jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel65.setText("Precio Venta");

                panelCPT47.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtPrecioVenta1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtPrecioVenta1.setForeground(new java.awt.Color(51, 51, 51));
                txtPrecioVenta1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtPrecioVenta1.setText("0.00");
                txtPrecioVenta1.setBorder(null);
                txtPrecioVenta1.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtPrecioVenta1CaretUpdate(evt);
                    }
                });
                txtPrecioVenta1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPrecioVenta1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT47Layout = new javax.swing.GroupLayout(panelCPT47);
                panelCPT47.setLayout(panelCPT47Layout);
                panelCPT47Layout.setHorizontalGroup(
                    panelCPT47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioVenta1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT47Layout.setVerticalGroup(
                    panelCPT47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT47Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPrecioVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
                jPanel81.setLayout(jPanel81Layout);
                jPanel81Layout.setHorizontalGroup(
                    jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel81Layout.setVerticalGroup(
                    jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel65)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel82.setBackground(new java.awt.Color(255, 255, 255));
                jPanel82.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
                jLabel66.setForeground(new java.awt.Color(255, 51, 51));
                jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel66.setText("Valor de Venta");

                panelCPT48.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtValorVentaDebito.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
                txtValorVentaDebito.setForeground(new java.awt.Color(51, 51, 51));
                txtValorVentaDebito.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtValorVentaDebito.setText("0.00");
                txtValorVentaDebito.setBorder(null);
                txtValorVentaDebito.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtValorVentaDebitoCaretUpdate(evt);
                    }
                });
                txtValorVentaDebito.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtValorVentaDebitoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT48Layout = new javax.swing.GroupLayout(panelCPT48);
                panelCPT48.setLayout(panelCPT48Layout);
                panelCPT48Layout.setHorizontalGroup(
                    panelCPT48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVentaDebito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT48Layout.setVerticalGroup(
                    panelCPT48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT48Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtValorVentaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
                jPanel82.setLayout(jPanel82Layout);
                jPanel82Layout.setHorizontalGroup(
                    jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel82Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel82Layout.setVerticalGroup(
                    jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel82Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel66)
                        .addGap(0, 0, 0)
                        .addComponent(panelCPT48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel83.setBackground(new java.awt.Color(255, 255, 255));
                jPanel83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel67.setForeground(new java.awt.Color(255, 51, 51));
                jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel67.setText("Correlativo de la Nota");

                panelCPT49.setBackground(new java.awt.Color(255, 255, 255));
                panelCPT49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                lblCorrelativoDebitoF.setEditable(false);
                lblCorrelativoDebitoF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                lblCorrelativoDebitoF.setForeground(new java.awt.Color(51, 51, 51));
                lblCorrelativoDebitoF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                lblCorrelativoDebitoF.setText("0.00");
                lblCorrelativoDebitoF.setBorder(null);
                lblCorrelativoDebitoF.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        lblCorrelativoDebitoFCaretUpdate(evt);
                    }
                });
                lblCorrelativoDebitoF.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        lblCorrelativoDebitoFActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelCPT49Layout = new javax.swing.GroupLayout(panelCPT49);
                panelCPT49.setLayout(panelCPT49Layout);
                panelCPT49Layout.setHorizontalGroup(
                    panelCPT49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCorrelativoDebitoF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                );
                panelCPT49Layout.setVerticalGroup(
                    panelCPT49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCPT49Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(lblCorrelativoDebitoF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel83Layout = new javax.swing.GroupLayout(jPanel83);
                jPanel83.setLayout(jPanel83Layout);
                jPanel83Layout.setHorizontalGroup(
                    jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCPT49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel83Layout.setVerticalGroup(
                    jPanel83Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel83Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCPT49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                lblIdDebito.setText("jLabel70");

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tablaS1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jPanel81, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIdDebito)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel78, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)))))
                        .addGap(25, 25, 25))
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel76, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel82, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jPanel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblIdDebito)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tablaS1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPanel83, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel74, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel75, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );

                tab.addTab(" NOTA DE DEBITO", jPanel8);

                getContentPane().add(tab);
                tab.setBounds(26, 80, 1330, 650);

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void cbxTipoNotaCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoNotaCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoNotaCreditoActionPerformed

    private void cbxTipoMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMonedaActionPerformed

    private void cbxDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoActionPerformed

    private void cbxTipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoActionPerformed

    private void txtNroDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNroDocumentoCaretUpdate

    }//GEN-LAST:event_txtNroDocumentoCaretUpdate

    private void txtNroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocumentoActionPerformed

    private void txtApeNomCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtApeNomCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomCaretUpdate

    private void txtApeNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApeNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomActionPerformed

    private void cbxGravadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGravadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGravadoActionPerformed

    private void cbxCodUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCodUnidadActionPerformed

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCodProductoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProductoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoCaretUpdate

    private void txtCodProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoActionPerformed

    private void txtCodProductoSunatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProductoSunatCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoSunatCaretUpdate

    private void txtCodProductoSunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoSunatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoSunatActionPerformed

    private void txtDescripcionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionCaretUpdate

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtValorUCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorUCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUCaretUpdate

    private void txtValorUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUActionPerformed

    private void txtPorcenDsctoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPorcenDsctoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcenDsctoCaretUpdate

    private void txtPorcenDsctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcenDsctoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcenDsctoActionPerformed

    private void txtDsctoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDsctoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsctoCaretUpdate

    private void txtDsctoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsctoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsctoActionPerformed

    private void txtIGVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIGVCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIGVCaretUpdate

    private void txtIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIGVActionPerformed

    private void cbxAfecIGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecIGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecIGVActionPerformed

    private void txtOtrosCargosCreditoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosCargosCreditoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosCreditoCaretUpdate

    private void txtOtrosCargosCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosCargosCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosCreditoActionPerformed

    private void txtMtoIGVCreditoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoIGVCreditoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVCreditoCaretUpdate

    private void txtMtoIGVCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoIGVCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVCreditoActionPerformed

    private void txtValorVentaGravadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaCaretUpdate

    private void txtValorVentaGravadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaActionPerformed

    private void txtValorVentaInafectadaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaInafectadaCaretUpdate

    private void txtValorVentaInafectadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaInafectadaActionPerformed

    private void txtOtrosTributosCreditoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosTributosCreditoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosCreditoCaretUpdate

    private void txtOtrosTributosCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosTributosCreditoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosCreditoActionPerformed

    private void txtMtoISCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoISCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCCaretUpdate

    private void txtMtoISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCActionPerformed

    private void txtVentaExoneradaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVentaExoneradaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaCaretUpdate

    private void txtVentaExoneradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentaExoneradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaActionPerformed

    private void txtImporteTotalVentaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaCaretUpdate

    private void txtImporteTotalVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaActionPerformed

    private void cbxTipoDocumentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoItemStateChanged
        if(cbxTipoDocumento.getSelectedIndex()==0){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
            txtNroDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==1){ //DNI
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(8);
            txtNroDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==2){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(12);
            txtNroDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==3){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(11);
            txtNroDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==4){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(12);
            txtNroDocumento.setDocument(limitObservacion);
        } else
        if(cbxTipoDocumento.getSelectedIndex()==5){
            LimitadorDeDocumento limitObservacion = new LimitadorDeDocumento(15);
            txtNroDocumento.setDocument(limitObservacion);
        } 
    }//GEN-LAST:event_cbxTipoDocumentoItemStateChanged

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

    private void txtNroDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumentoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(cbxTipoDocumento.getSelectedIndex()==1 || cbxTipoDocumento.getSelectedIndex()==3){//DNI
            if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
                evt.consume();
                getToolkit().beep();            
            }
        }
    }//GEN-LAST:event_txtNroDocumentoKeyTyped

    private void btnGenerarDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDocActionPerformed
        int estado=0; 
        String correlativo="";
        AdmisionEmergenciaCabecera cabecera = new AdmisionEmergenciaCabecera();
         if(txtDescripcionSustento.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Describa el Motivo o Sustento de la Nota de Crédito");
         }
         else if(!txtNroDocumento.getText().equals("")){
                CuentasPorPagarNotaDeCreditoCabecera facturaCabecera = new CuentasPorPagarNotaDeCreditoCabecera();
                    facturaCabecera.setIdFactura(Integer.parseInt(lblIdCredito.getText()));
                    facturaCabecera.setNota_credito(cbxTipoNotaCredito.getSelectedItem().toString());
                    facturaCabecera.setDescripcion(txtDescripcionSustento.getText());
                   facturaCabecera.setSerie(txtSerie.getText());
                   CuentasPorPagarNotaDeCreditoCabecera serief=new CuentasPorPagarNotaDeCreditoCabecera();
                    lblCorrelativoCreditoF.setText(serief.generarSerieCorrelativo(txtSerie.getText()));
                    facturaCabecera.setCorrelativo(lblCorrelativoCreditoF.getText());
                    facturaCabecera.setFechaEmision(lblFechaEmision.getText());
                    
                    facturaCabecera.setCod_usu(cabecera.codUsuario(lblUsu.getText()));
            if(facturaCabecera.mantenimientoCuentasPorPagarNotaCredito()){
                crearCabeceraCredito();
//                CuentasPorPagarFacturasDetalle facturaDetalle1 = new CuentasPorPagarFacturasDetalle();
//                lblId.setText(facturaDetalle1.facturaCabeceraId());
////                crearDetalle();
                String archivo = "20410275768" + "-" + 
                "07"+ "-" +
                txtSerie.getText() + "-" + 
                lblCorrelativoCreditoF.getText() + ".DET";
                File crea_archivo = new File(archivo);
                for (int i = 0; i < tbFacturacion.getRowCount(); i++){      
//                    CuentasPorPagarFacturasDetalle facturaDetalle = new CuentasPorPagarFacturasDetalle();
//                    facturaDetalle.setCpfId(Integer.parseInt(lblId.getText()));
//                    facturaDetalle.setCpdGrav(cbxGravado.getSelectedItem().toString());
//                    facturaDetalle.setCpdCodUnidad(cbxCodUnidad.getSelectedItem().toString());
//                    facturaDetalle.setCpdCantidad(Integer.parseInt(tbFacturacion.getValueAt(i,3).toString()));
//                    facturaDetalle.setNomenclatura(facturaDetalle.codNomen(tbFacturacion.getValueAt(i,0).toString()));
//                    facturaDetalle.setCpdCodProdSunat("");
//                    facturaDetalle.setCpdValorU(BigDecimal.valueOf(Double.parseDouble(tbFacturacion.getValueAt(i,2).toString())));
//                    facturaDetalle.setCpdDescPorcen(BigDecimal.valueOf(Double.parseDouble(txtPorcenDscto.getText())));
//                    facturaDetalle.setCpdDscto(BigDecimal.valueOf(Double.parseDouble(txtDscto.getText())));
//                    facturaDetalle.setCpdIgv(BigDecimal.valueOf(Double.parseDouble(txtIGV.getText())));
//                    facturaDetalle.setCpdAfecIgv(cbxAfecIGV.getSelectedItem().toString()); 
//                    facturaDetalle.setCpdIsc(BigDecimal.valueOf(Double.parseDouble(txtISC.getText())));
//                    facturaDetalle.setCpdAfecIsc(cbxAfecISC.getSelectedItem().toString()); 
//                    facturaDetalle.setCpdPrecioVenta(BigDecimal.valueOf(Double.parseDouble(txtPrecioVenta.getText())));
//                    facturaDetalle.setCpdValorVenta(BigDecimal.valueOf(Double.parseDouble(txtValorVenta.getText())));
//                    facturaDetalle.setCpdDsctoGlobal(BigDecimal.valueOf(Double.parseDouble(txtDsctoGlobal.getText())));
//                    facturaDetalle.setCpdSumOtrosCargos(BigDecimal.valueOf(Double.parseDouble(txtOtrosCargos.getText())));
//                    facturaDetalle.setCpdSumIgv(BigDecimal.valueOf(Double.parseDouble(txtMtoIGV.getText())));
//                    facturaDetalle.setCpdTVvInafec(BigDecimal.valueOf(Double.parseDouble(txtValorVentaInafectada.getText())));
//                    facturaDetalle.setCpdTVvGrav(BigDecimal.valueOf(Double.parseDouble(txtValorVentaGravada.getText())));
//                    facturaDetalle.setCpdTDsctos(BigDecimal.valueOf(Double.parseDouble(txtTotalDscto.getText())));
//                    facturaDetalle.setCpdOtrosTribut(BigDecimal.valueOf(Double.parseDouble(txtOtrosTributos.getText())));
//                    facturaDetalle.setCpdSumIsc(BigDecimal.valueOf(Double.parseDouble(txtMtoISC.getText())));
//                    facturaDetalle.setCpdTVExonen(BigDecimal.valueOf(Double.parseDouble(txtVentaExonerada.getText())));
//                    facturaDetalle.setCpdImpTotVtas(BigDecimal.valueOf(Double.parseDouble(txtImporteTotalVenta.getText())));
//                    facturaDetalle.setCodUsu(cabecera.codUsuario(lblusu.getText()));
//                    if(facturaDetalle.mantenimientoCuentasPorPagarFacturasDetalle(lblMant.getText())){
//                        //
//                        //
//                        
                    try {
                        Formatter crea = new Formatter(ubicacion+archivo);
                        if(crea_archivo.exists()){
                            JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                        } else {
                            String bloc1 = "";
                            for (int c = 0; c < tbFacturacion.getRowCount(); c++){    
                                bloc1 = bloc1 + String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                                String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) +
                                String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(2)) + "|" +
                                String.valueOf(tbFacturacion.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(c, 0))+  "|" + 
                                 ""+ "|" + 
                                String.valueOf(tbFacturacion.getValueAt(c, 1))+ "|" + 
                                 String.valueOf(tbFacturacion.getValueAt(c, 2)) + "|" + 
                                 String.valueOf(tbFacturacion.getValueAt(c, 6))+ "|" +
                               String.valueOf(tbFacturacion.getValueAt(c, 5))+ "|" + String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                                txtISC.getText() + "|" + String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
                                txtPrecioVenta.getText() + "|" + txtValorVenta.getText() + "\r\n";
                            }
                            String bloc2 = "";
                            for (int c = 0; c < tbFacturacion.getRowCount(); c++){    
                                bloc2 = bloc2 + String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(0))+
                                String.valueOf(cbxCodUnidad.getSelectedItem().toString().charAt(1)) + "|" +
                                String.valueOf(tbFacturacion.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacion.getValueAt(c, 0))+  "|" + 
                                 ""+ "|" + 
                                String.valueOf(tbFacturacion.getValueAt(c, 1))+ "|" + 
                                 String.valueOf(tbFacturacion.getValueAt(c, 2)) + "|"  + 
                                 String.valueOf(tbFacturacion.getValueAt(c, 6))+ "|" +
                               String.valueOf(tbFacturacion.getValueAt(c, 5))+ "|" + String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecIGV.getSelectedItem().toString().charAt(1)) + "|" + 
                                txtISC.getText() + "|" + String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecISC.getSelectedItem().toString().charAt(1)) + "|" +
                                txtPrecioVenta.getText() + "|" + txtValorVenta.getText() + "\r\n";
                            }
                            if(cbxCodUnidad.getSelectedIndex()==0 || cbxCodUnidad.getSelectedIndex()==4 ||
                                       cbxCodUnidad.getSelectedIndex()==5 || cbxCodUnidad.getSelectedIndex()==6 ||
                                       cbxCodUnidad.getSelectedIndex()==7){
                                crea.format(bloc1);
                            } else {
                                crea.format(bloc2);
                            }
                            crea.close();
                            estado=1;
                            
                        }   
                    } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "No se pudo"+e.getMessage());
                    }
//                        //
//                        //
                    }
            }
//                
//            }
                if(estado==1){
                    JOptionPane.showMessageDialog(this, "Factura Electrónica Generada");
                    CuentasPorPagarNotaDeCreditoCabecera est=new CuentasPorPagarNotaDeCreditoCabecera();
                    est.CuentasPorPagarFacturaEstado(Integer.parseInt(lblIdCredito.getText()),"1");
                    limpiar();
                }
        }
        else{
            btnAgregarFactura.doClick();
        }
    }//GEN-LAST:event_btnGenerarDocActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void txtDescripcionSustentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionSustentoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionSustentoCaretUpdate

    private void txtDescripcionSustentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionSustentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionSustentoActionPerformed

    private void btnAgregarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFacturaActionPerformed
        BUSCAR_FACTURA_BOLETA.setVisible(true);
       lblEstado.setText("1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
        
    tb_Factura_Boleta.getSelectionModel().setSelectionInterval(0, 0);
            tb_Factura_Boleta.requestFocus();
       
    }//GEN-LAST:event_btnAgregarFacturaActionPerformed

    private void txtISCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtISCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtISCCaretUpdate

    private void txtISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtISCActionPerformed

    private void cbxAfecISCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecISCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecISCActionPerformed

    private void txtPrecioVentaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioVentaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaCaretUpdate

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void txtValorVentaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaCaretUpdate

    private void txtValorVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaActionPerformed

    private void lblCorrelativoCreditoFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblCorrelativoCreditoFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCorrelativoCreditoFCaretUpdate

    private void lblCorrelativoCreditoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCorrelativoCreditoFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCorrelativoCreditoFActionPerformed

    private void cbxTipoNotaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoNotaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoNotaDebitoActionPerformed

    private void cbxTipoMonedaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoMonedaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMonedaDebitoActionPerformed

    private void cbxDocumentoDebitoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDocumentoDebitoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoDebitoItemStateChanged

    private void cbxDocumentoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocumentoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocumentoDebitoActionPerformed

    private void cbxTipoDocumentoDebitoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoDebitoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoDebitoItemStateChanged

    private void cbxTipoDocumentoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoDocumentoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoDocumentoDebitoActionPerformed

    private void txtNroDocumentoDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNroDocumentoDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocumentoDebitoCaretUpdate

    private void txtNroDocumentoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroDocumentoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocumentoDebitoActionPerformed

    private void txtNroDocumentoDebitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumentoDebitoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocumentoDebitoKeyTyped

    private void txtApeNomDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtApeNomDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomDebitoCaretUpdate

    private void txtApeNomDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApeNomDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApeNomDebitoActionPerformed

    private void cbxGravadoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGravadoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGravadoDebitoActionPerformed

    private void cbxCodUnidadDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCodUnidadDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCodUnidadDebitoActionPerformed

    private void txtCantidadDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDebitoCaretUpdate

    private void txtCantidadDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadDebitoActionPerformed

    private void txtCodProducto1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProducto1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProducto1CaretUpdate

    private void txtCodProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProducto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProducto1ActionPerformed

    private void txtCodProductoSunat1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodProductoSunat1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoSunat1CaretUpdate

    private void txtCodProductoSunat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodProductoSunat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProductoSunat1ActionPerformed

    private void txtDescripcion1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcion1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion1CaretUpdate

    private void txtDescripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcion1ActionPerformed

    private void txtValorUDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorUDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUDebitoCaretUpdate

    private void txtValorUDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorUDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorUDebitoActionPerformed

    private void txtPorcenDsctoDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPorcenDsctoDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcenDsctoDebitoCaretUpdate

    private void txtPorcenDsctoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcenDsctoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcenDsctoDebitoActionPerformed

    private void txtDsctoDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDsctoDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsctoDebitoCaretUpdate

    private void txtDsctoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsctoDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsctoDebitoActionPerformed

    private void txtIGVDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIGVDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIGVDebitoCaretUpdate

    private void txtIGVDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIGVDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIGVDebitoActionPerformed

    private void cbxAfecIGVDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecIGVDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecIGVDebitoActionPerformed

    private void tbFacturacionDebitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionDebitoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturacionDebitoMouseClicked

    private void tbFacturacionDebitoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionDebitoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturacionDebitoMousePressed

    private void tbFacturacionDebitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturacionDebitoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbFacturacionDebitoKeyPressed

    private void txtValorVentaGravadaDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaDebitoCaretUpdate

    private void txtValorVentaGravadaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaGravadaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaGravadaDebitoActionPerformed

    private void txtImporteTotalVentaDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaDebitoCaretUpdate

    private void txtImporteTotalVentaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImporteTotalVentaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImporteTotalVentaDebitoActionPerformed

    private void txtValorVentaInafectadaDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaInafectadaDebitoCaretUpdate

    private void txtValorVentaInafectadaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaInafectadaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaInafectadaDebitoActionPerformed

    private void txtVentaExoneradaDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVentaExoneradaDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaDebitoCaretUpdate

    private void txtVentaExoneradaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVentaExoneradaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVentaExoneradaDebitoActionPerformed

    private void txtMtoISCDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoISCDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCDebitoCaretUpdate

    private void txtMtoISCDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoISCDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoISCDebitoActionPerformed

    private void txtMtoIGVDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMtoIGVDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVDebitoCaretUpdate

    private void txtMtoIGVDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMtoIGVDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMtoIGVDebitoActionPerformed

    private void txtOtrosCargosDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosCargosDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosDebitoCaretUpdate

    private void txtOtrosCargosDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosCargosDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosCargosDebitoActionPerformed

    private void txtOtrosTributosDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOtrosTributosDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosDebitoCaretUpdate

    private void txtOtrosTributosDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtrosTributosDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOtrosTributosDebitoActionPerformed

    private void btnGenerarDoc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDoc2ActionPerformed
          int estado=0; 
        AdmisionEmergenciaCabecera cabecera = new AdmisionEmergenciaCabecera();
         if(txtDescripcionDebito.getText().equals("")){
             JOptionPane.showMessageDialog(this, "Describa el Motivo o Sustento de la Nota de Débito");
         }
             
         else if(!txtNroDocumentoDebito.getText().equals("")){
                CuentasPorPagarNotaDeCreditoCabecera facturaCabecera = new CuentasPorPagarNotaDeCreditoCabecera();
                    facturaCabecera.setIdFactura(Integer.parseInt(lblIdDebito.getText()));
                    facturaCabecera.setNota_credito(cbxTipoNotaDebito.getSelectedItem().toString());
                    facturaCabecera.setDescripcion(txtDescripcionDebito.getText());
                   facturaCabecera.setSerie(txtSerieDebito.getText());
                   CuentasPorPagarNotaDeCreditoCabecera serief=new CuentasPorPagarNotaDeCreditoCabecera();
                    lblCorrelativoDebitoF.setText(serief.generarSerieCorrelativoDebito(txtSerieDebito.getText()));
                    facturaCabecera.setCorrelativo(lblCorrelativoDebitoF.getText());
                    facturaCabecera.setFechaEmision(lblFechaEmisionDebito.getText());
                    
                    facturaCabecera.setCod_usu(cabecera.codUsuario(lblUsu.getText()));
            if(facturaCabecera.mantenimientoCuentasPorPagarNotaDebito()){
                crearCabeceraDebito();
//                CuentasPorPagarFacturasDetalle facturaDetalle1 = new CuentasPorPagarFacturasDetalle();
//                lblId.setText(facturaDetalle1.facturaCabeceraId());
////                crearDetalle();
                String archivo = txtNroDocumentoDebito.getText() + "-" + 
                "08"+ "-" +
                txtSerieDebito.getText() + "-" + 
                lblCorrelativoDebitoF.getText() + ".DET";
                File crea_archivo = new File(archivo);
                for (int i = 0; i < tbFacturacionDebito.getRowCount(); i++){      
                    try {
                        Formatter crea = new Formatter(ubicacion+archivo);
                        if(crea_archivo.exists()){
                            JOptionPane.showMessageDialog(rootPane, "El registro ya existe");
                        } else {
                            String bloc1 = "";
                            for (int c = 0; c < tbFacturacionDebito.getRowCount(); c++){    
                                bloc1 = bloc1 + String.valueOf(cbxCodUnidadDebito.getSelectedItem().toString().charAt(0))+
                                String.valueOf(cbxCodUnidadDebito.getSelectedItem().toString().charAt(1)) +
                                String.valueOf(cbxCodUnidadDebito.getSelectedItem().toString().charAt(2)) + "|" +
                                String.valueOf(tbFacturacionDebito.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacionDebito.getValueAt(c, 0))+  "|" + 
                                 ""+ "|" + 
                                String.valueOf(tbFacturacionDebito.getValueAt(c, 1))+ "|" + 
                                 String.valueOf(tbFacturacionDebito.getValueAt(c, 2)) + "|" + 
                                  String.valueOf(tbFacturacion.getValueAt(c, 6)) + "|" +
                                 String.valueOf(tbFacturacion.getValueAt(c, 5))+ "|" + String.valueOf(cbxAfecIGVDebito.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecIGVDebito.getSelectedItem().toString().charAt(1)) + "|" + 
                                txtISCDebito.getText() + "|" + String.valueOf(cbxAfecISCDebito.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecISCDebito.getSelectedItem().toString().charAt(1)) + "|" +
                                txtPrecioVenta1.getText() + "|" + txtValorVentaDebito.getText() + "\r\n";
                            }
                            String bloc2 = "";
                            for (int c = 0; c < tbFacturacionDebito.getRowCount(); c++){    
                                bloc2 = bloc2 + String.valueOf(cbxCodUnidadDebito.getSelectedItem().toString().charAt(0))+
                                String.valueOf(cbxCodUnidadDebito.getSelectedItem().toString().charAt(1)) + "|" +
                                String.valueOf(tbFacturacionDebito.getValueAt(c, 3)) + "|" + String.valueOf(tbFacturacionDebito.getValueAt(c, 0))+  "|" + 
                                 ""+ "|" + 
                                String.valueOf(tbFacturacionDebito.getValueAt(c, 1))+ "|" + 
                                 String.valueOf(tbFacturacionDebito.getValueAt(c, 2)) + "|"  + 
                                  String.valueOf(tbFacturacion.getValueAt(c, 6)) + "|" +
                                 String.valueOf(tbFacturacion.getValueAt(c, 5))+ "|" + String.valueOf(cbxAfecIGVDebito.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecIGVDebito.getSelectedItem().toString().charAt(1)) + "|" + 
                                txtISCDebito.getText() + "|" + String.valueOf(cbxAfecISCDebito.getSelectedItem().toString().charAt(0)) +
                                String.valueOf(cbxAfecISCDebito.getSelectedItem().toString().charAt(1)) + "|" +
                                txtPrecioVenta1.getText() + "|" + txtValorVentaDebito.getText() + "\r\n";
                            }
                            if(cbxCodUnidadDebito.getSelectedIndex()==0 || cbxCodUnidadDebito.getSelectedIndex()==4 ||
                                       cbxCodUnidadDebito.getSelectedIndex()==5 || cbxCodUnidadDebito.getSelectedIndex()==6 ||
                                       cbxCodUnidadDebito.getSelectedIndex()==7){
                                crea.format(bloc1);
                            } else {
                                crea.format(bloc2);
                            }
                            crea.close();
                            estado=1;
                            
                        }   
                    } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "No se pudo"+e.getMessage());
                    }
//                        //
//                        //
                    }
            }
//                
//            }
                if(estado==1){
                    JOptionPane.showMessageDialog(this, "Factura Electrónica Generada");
                    CuentasPorPagarNotaDeCreditoCabecera est=new CuentasPorPagarNotaDeCreditoCabecera();
                    est.CuentasPorPagarFacturaEstado(Integer.parseInt(lblIdDebito.getText()),"2");
                    limpiar();
                }
        }
        else{
            btnAgregarFactura.doClick();
        }
    }//GEN-LAST:event_btnGenerarDoc2ActionPerformed

    private void txtDescripcionDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDescripcionDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionDebitoCaretUpdate

    private void txtDescripcionDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionDebitoActionPerformed

    private void btnGenerarDoc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarDoc3ActionPerformed
      BUSCAR_FACTURA_BOLETA.setVisible(true);
        lblEstado.setText("2");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_listar("","1");
        CUENTAS_POR_PAGAR_FACTURA_BOLETA_formato();
        
    tb_Factura_Boleta.getSelectionModel().setSelectionInterval(0, 0);
            tb_Factura_Boleta.requestFocus();
    }//GEN-LAST:event_btnGenerarDoc3ActionPerformed

    private void txtISCDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtISCDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtISCDebitoCaretUpdate

    private void txtISCDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtISCDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtISCDebitoActionPerformed

    private void cbxAfecISCDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAfecISCDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAfecISCDebitoActionPerformed

    private void txtPrecioVenta1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPrecioVenta1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVenta1CaretUpdate

    private void txtPrecioVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVenta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVenta1ActionPerformed

    private void txtValorVentaDebitoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtValorVentaDebitoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaDebitoCaretUpdate

    private void txtValorVentaDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorVentaDebitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaDebitoActionPerformed

    private void lblCorrelativoDebitoFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblCorrelativoDebitoFCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCorrelativoDebitoFCaretUpdate

    private void lblCorrelativoDebitoFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblCorrelativoDebitoFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblCorrelativoDebitoFActionPerformed

    private void lblLineDebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLineDebMouseClicked
     tab.setSelectedIndex(1);
     lblLineCre.setForeground(new Color(255,255,255));
     lblLineDeb.setForeground(new Color(41,127,184));
     
     lblCredito.setFont(new Font("Segoe UI Semilight",0, 14));
     lblDebito.setFont(new Font("Segoe UI Semilight",1, 15));
    }//GEN-LAST:event_lblLineDebMouseClicked

    private void lblLineCreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLineCreMouseClicked
        tab.setSelectedIndex(0);
        lblLineDeb.setForeground(new Color(255,255,255));
        lblLineCre.setForeground(new Color(41,127,184));
        lblCredito.setFont(new Font("Segoe UI Semilight",1, 15));
        lblDebito.setFont(new Font("Segoe UI Semilight",0, 14));
    }//GEN-LAST:event_lblLineCreMouseClicked

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
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            try{
                if(tb_Factura_Boleta.getRowCount()>0){
                BUSCAR_FACTURA_BOLETA.setVisible(false);
                int filaselec=tb_Factura_Boleta.getSelectedRow();

                if(lblEstado.getText().equalsIgnoreCase("1")){
                lblIdCredito.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                lblFechaEmision.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                cbxDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());
                txtSerie.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                lblNroCorrelativo.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                cbxTipoDocumento.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 5).toString());
                txtNroDocumento.setText(tb_Factura_Boleta.getValueAt(filaselec, 6).toString());
                txtApeNom.setText(tb_Factura_Boleta.getValueAt(filaselec, 7).toString());
                
                //Totales
                txtOtrosCargosCredito.setText(tb_Factura_Boleta.getValueAt(filaselec, 11).toString());
                txtValorVentaGravada.setText(tb_Factura_Boleta.getValueAt(filaselec, 12).toString());
                txtValorVentaInafectada.setText(tb_Factura_Boleta.getValueAt(filaselec, 13).toString());
                txtVentaExonerada.setText(tb_Factura_Boleta.getValueAt(filaselec, 14).toString());
                txtMtoIGVCredito.setText(tb_Factura_Boleta.getValueAt(filaselec, 15).toString());
                txtMtoISC.setText(tb_Factura_Boleta.getValueAt(filaselec, 16).toString());
                txtOtrosTributosCredito.setText(tb_Factura_Boleta.getValueAt(filaselec, 17).toString());
                txtImporteTotalVenta.setText(tb_Factura_Boleta.getValueAt(filaselec, 18).toString());
                
                CuentasPorPagarNotaDeCreditoCabecera cor=new CuentasPorPagarNotaDeCreditoCabecera();
                lblCorrelativoCreditoF.setText(cor.generarSerieCorrelativo(tb_Factura_Boleta.getValueAt(filaselec, 3).toString()));
                mostrarFacturacionDetalle(tbFacturacion);
                formatoFacturacionDetalle(tbFacturacion);
                }
                else if(lblEstado.getText().equalsIgnoreCase("2")){
                lblIdDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 0).toString());
                lblFechaEmisionDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 1).toString());
                cbxDocumentoDebito.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 2).toString());
                txtSerieDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 3).toString());
                lblNroCorrelativoDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 4).toString());
                cbxTipoDocumentoDebito.setSelectedItem(tb_Factura_Boleta.getValueAt(filaselec, 5).toString());
                txtNroDocumentoDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 6).toString());
                txtApeNomDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 7).toString());
                
                //Totales
                txtOtrosCargosDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 11).toString());
                txtValorVentaGravadaDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 12).toString());
                txtValorVentaInafectadaDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 13).toString());
                txtVentaExoneradaDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 14).toString());
                txtMtoIGVDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 15).toString());
                txtMtoISCDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 16).toString());
                txtOtrosTributosDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 17).toString());
                txtImporteTotalVentaDebito.setText(tb_Factura_Boleta.getValueAt(filaselec, 18).toString());
                
                CuentasPorPagarNotaDeCreditoCabecera cord=new CuentasPorPagarNotaDeCreditoCabecera();
                lblCorrelativoDebitoF.setText(cord.generarSerieCorrelativoDebito(tb_Factura_Boleta.getValueAt(filaselec, 3).toString()));
                
                mostrarFacturacionDetalle(tbFacturacionDebito);
                formatoFacturacionDetalle(tbFacturacionDebito);
                }
                
                
            }} catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tb_Factura_BoletaKeyPressed

    private void tbFacturacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFacturacionKeyPressed

    }//GEN-LAST:event_tbFacturacionKeyPressed

    private void tbFacturacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMousePressed

    }//GEN-LAST:event_tbFacturacionMousePressed

    private void tbFacturacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFacturacionMouseClicked

    }//GEN-LAST:event_tbFacturacionMouseClicked

    
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
            java.util.logging.Logger.getLogger(NotasCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotasCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotasCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotasCreditoDebito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotasCreditoDebito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_FACTURA_BOLETA;
    private javax.swing.JPopupMenu Serie;
    private javax.swing.JButton btnAgregarFactura;
    private javax.swing.JButton btnGenerarDoc;
    private javax.swing.JButton btnGenerarDoc2;
    private javax.swing.JButton btnGenerarDoc3;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JComboBox cbxAfecIGV;
    private javax.swing.JComboBox cbxAfecIGVDebito;
    private javax.swing.JComboBox cbxAfecISC;
    private javax.swing.JComboBox cbxAfecISCDebito;
    private javax.swing.JComboBox cbxBuscarDocumento;
    private javax.swing.JComboBox cbxCodUnidad;
    private javax.swing.JComboBox cbxCodUnidadDebito;
    private javax.swing.JComboBox cbxDocumento;
    private javax.swing.JComboBox cbxDocumentoDebito;
    private javax.swing.JComboBox cbxGravado;
    private javax.swing.JComboBox cbxGravadoDebito;
    private javax.swing.JComboBox cbxTipoDocumento;
    private javax.swing.JComboBox cbxTipoDocumentoDebito;
    private javax.swing.JComboBox cbxTipoMoneda;
    private javax.swing.JComboBox cbxTipoMonedaDebito;
    private javax.swing.JComboBox cbxTipoNotaCredito;
    private javax.swing.JComboBox cbxTipoNotaDebito;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
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
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPanel jPanel83;
    private javax.swing.JPanel jPanel84;
    private javax.swing.JPanel jPanel85;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JTextField lblCorrelativoCreditoF;
    public static javax.swing.JTextField lblCorrelativoDebitoF;
    private javax.swing.JLabel lblCredito;
    private javax.swing.JLabel lblDebito;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblFechaEmisionDebito;
    private javax.swing.JLabel lblIdCredito;
    private javax.swing.JLabel lblIdDebito;
    private javax.swing.JLabel lblLineCre;
    private javax.swing.JLabel lblLineDeb;
    public static javax.swing.JLabel lblNroCorrelativo;
    public static javax.swing.JLabel lblNroCorrelativoDebito;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT11;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT14;
    private javax.swing.JPanel panelCPT15;
    private javax.swing.JPanel panelCPT16;
    private javax.swing.JPanel panelCPT17;
    private javax.swing.JPanel panelCPT18;
    private javax.swing.JPanel panelCPT19;
    private javax.swing.JPanel panelCPT2;
    private javax.swing.JPanel panelCPT21;
    private javax.swing.JPanel panelCPT22;
    private javax.swing.JPanel panelCPT23;
    private javax.swing.JPanel panelCPT24;
    private javax.swing.JPanel panelCPT25;
    private javax.swing.JPanel panelCPT26;
    private javax.swing.JPanel panelCPT27;
    private javax.swing.JPanel panelCPT28;
    private javax.swing.JPanel panelCPT29;
    private javax.swing.JPanel panelCPT30;
    private javax.swing.JPanel panelCPT31;
    private javax.swing.JPanel panelCPT32;
    private javax.swing.JPanel panelCPT33;
    private javax.swing.JPanel panelCPT34;
    private javax.swing.JPanel panelCPT35;
    private javax.swing.JPanel panelCPT36;
    private javax.swing.JPanel panelCPT37;
    private javax.swing.JPanel panelCPT38;
    private javax.swing.JPanel panelCPT39;
    private javax.swing.JPanel panelCPT4;
    private javax.swing.JPanel panelCPT40;
    private javax.swing.JPanel panelCPT41;
    private javax.swing.JPanel panelCPT42;
    private javax.swing.JPanel panelCPT43;
    private javax.swing.JPanel panelCPT45;
    private javax.swing.JPanel panelCPT46;
    private javax.swing.JPanel panelCPT47;
    private javax.swing.JPanel panelCPT48;
    private javax.swing.JPanel panelCPT49;
    private javax.swing.JPanel panelCPT5;
    private javax.swing.JPanel panelCPT50;
    private javax.swing.JPanel panelCPT6;
    private javax.swing.JPanel panelCPT7;
    private javax.swing.JPanel panelCPT8;
    private javax.swing.JPanel panelCPT9;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JScrollPane tablaS;
    private javax.swing.JScrollPane tablaS1;
    private javax.swing.JTable tbFacturacion;
    private javax.swing.JTable tbFacturacionDebito;
    private javax.swing.JTable tb_Factura_Boleta;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtApeNom;
    public static javax.swing.JTextField txtApeNomDebito;
    public static javax.swing.JTextField txtBuscarDocumento;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCantidadDebito;
    public static javax.swing.JTextField txtCodProducto;
    public static javax.swing.JTextField txtCodProducto1;
    public static javax.swing.JTextField txtCodProductoSunat;
    public static javax.swing.JTextField txtCodProductoSunat1;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextField txtDescripcion1;
    public static javax.swing.JTextField txtDescripcionDebito;
    public static javax.swing.JTextField txtDescripcionSustento;
    public static javax.swing.JTextField txtDscto;
    public static javax.swing.JTextField txtDsctoDebito;
    public static javax.swing.JTextField txtIGV;
    public static javax.swing.JTextField txtIGVDebito;
    public static javax.swing.JTextField txtISC;
    public static javax.swing.JTextField txtISCDebito;
    public static javax.swing.JTextField txtImporteTotalVenta;
    public static javax.swing.JTextField txtImporteTotalVentaDebito;
    public static javax.swing.JTextField txtMtoIGVCredito;
    public static javax.swing.JTextField txtMtoIGVDebito;
    public static javax.swing.JTextField txtMtoISC;
    public static javax.swing.JTextField txtMtoISCDebito;
    public static javax.swing.JTextField txtNroDocumento;
    public static javax.swing.JTextField txtNroDocumentoDebito;
    public static javax.swing.JTextField txtOtrosCargosCredito;
    public static javax.swing.JTextField txtOtrosCargosDebito;
    public static javax.swing.JTextField txtOtrosTributosCredito;
    public static javax.swing.JTextField txtOtrosTributosDebito;
    public static javax.swing.JTextField txtPorcenDscto;
    public static javax.swing.JTextField txtPorcenDsctoDebito;
    public static javax.swing.JTextField txtPrecioVenta;
    public static javax.swing.JTextField txtPrecioVenta1;
    public static javax.swing.JLabel txtSerie;
    public static javax.swing.JLabel txtSerie1;
    public static javax.swing.JLabel txtSerie3;
    public static javax.swing.JLabel txtSerieDebito;
    public static javax.swing.JTextField txtValorU;
    public static javax.swing.JTextField txtValorUDebito;
    public static javax.swing.JTextField txtValorVenta;
    public static javax.swing.JTextField txtValorVentaDebito;
    public static javax.swing.JTextField txtValorVentaGravada;
    public static javax.swing.JTextField txtValorVentaGravadaDebito;
    public static javax.swing.JTextField txtValorVentaInafectada;
    public static javax.swing.JTextField txtValorVentaInafectadaDebito;
    public static javax.swing.JTextField txtVentaExonerada;
    public static javax.swing.JTextField txtVentaExoneradaDebito;
    // End of variables declaration//GEN-END:variables
}
