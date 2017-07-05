package modelos.cuentaPorPagar;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import servicios.Conexion;
import vista.cuentaPorPagar.Facturador;
import vista.cuentaPorPagar.VentasConsolidado;
import static vista.cuentaPorPagar.VentasConsolidado.txtDni;

public class CuentasPorPagarVentasConsolidadoCabecera {

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    public void formatoVentasConsolidadoCabecera(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(13).setMinWidth(0);
        tabla.getColumnModel().getColumn(13).setMaxWidth(0);
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0);
    }

    public void ventasConsolidadoCabecera(JTable tabla, String actoMedico) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Documento", "Nº Documento", "Forma de Pago", "DNI", "HC", "C", "Estado", "Dscto", "SubTotal", "IGV", "Total", "Fecha", "Hora", "Am", "ID"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[15];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_VENTAS_CONSOLIDADO_CABECERA ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, actoMedico);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = r.getString(1);
                fila[1] = r.getString(2);
                fila[2] = r.getString(3);
                fila[3] = r.getString(4);
                fila[4] = r.getString(5);
                fila[5] = r.getString(6);
                fila[6] = r.getString(7);
                fila[7] = r.getString(8);
                fila[8] = r.getString(9);
                fila[9] = r.getString(10);
                fila[10] = r.getString(11);
                fila[11] = r.getString(12);
                fila[12] = r.getString(13);
                fila[13] = r.getString(14);
                fila[14] = r.getString(15);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoVentasConsolidadoCabecera(tabla);
        } catch (Exception e) {
            System.out.println("Error: ventasConsolidadoCabecera" + e.getMessage());
        }
    }

    public void formatoVentasConsolidadoDetalles(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setMinWidth(0);
        tabla.getColumnModel().getColumn(11).setMaxWidth(0);
    }

    public void ventasConsolidadoDetalles(JTable tabla, String id,String tipo) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Código CPT", "Nomenclatura", "Precio", "Área", "Cantidad", "Precio", "Descuento", "Total", "Personal", "Num Atención", "Turno",  "ID"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[12];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_VENTAS_CONSOLIDADO_DETALLES ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            cmd.setString(2, tipo);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = r.getString(1);
                fila[1] = r.getString(2);
                fila[2] = r.getString(3);
                fila[3] = r.getString(4);
                fila[4] = r.getString(5);
                fila[5] = r.getString(6);
                fila[6] = r.getString(7);
                fila[7] = r.getString(8);
                fila[8] = r.getString(9);
                fila[9] = r.getString(10);
                fila[10] = r.getString(11);
                fila[11] = r.getString(12);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoVentasConsolidadoDetalles(tabla);
        } catch (Exception e) {
            System.out.println("Error: ventasConsolidadoDetalles" + e.getMessage());
        }
    }

    public void listarActoMedico(String dni) {
        try {
            Statement sta = cn.createStatement();
            ResultSet rs = sta.executeQuery("EXEC CUENTAS_POR_PAGAR_LISTAR_ACTO_MEDICO '" + dni + "'");
            VentasConsolidado.cbxActoMedico.removeAllItems();
            VentasConsolidado.cbxActoMedico.addItem("Acto Médico");
            while (rs.next()) {
                VentasConsolidado.cbxActoMedico.addItem(rs.getInt("NUM_ACTOMEDICO"));
            }

        } catch (SQLException e) {
            System.out.println("error:" + e.getMessage());
        }
    }
    
    public void datosHC(String dni) {
        try {
            Statement sta = cn.createStatement();
            ResultSet rs = sta.executeQuery("EXEC CUENTAS_POR_PAGAR_DATOS_HC '" + dni + "'");
            while (rs.next()) {
                VentasConsolidado.lblDNI.setText(rs.getString(2));
                VentasConsolidado.lblHC.setText(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("error:" + e.getMessage());
        }
    }

    public boolean actualizarEstadoFacturacion(String id,String estado)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_ACTUALIZAR_ESTADO_FACTURACION ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, id);
            cmd.setString(2, estado);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: actualizarEstadoFacturacion: " + ex.toString());
        }
        return resp;
    }
    
    public void formatoListarPorFacturar(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
    }

    public void listarPorFacturar(JTable tabla, String id) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Código CPT", "Nomenclatura", "Valor U.", "Cantidad", "Precio", "IGV", "Dscto","Total","ID","FP"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[10];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_LISTADO_POR_FACTURAR ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = r.getString(1);
                fila[1] = r.getString(2);
                fila[2] = r.getString(3);
                fila[3] = r.getString(4);
                fila[4] = r.getString(5);
                fila[5] = r.getString(6);
                fila[6] = r.getString(7);
                fila[7] = r.getString(8);
                fila[8] = r.getString(9);
                fila[9] = r.getString(10);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoListarPorFacturar(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarPorFacturar" + e.getMessage());
        }
    }
    
    public void calcularPrecioVenta(String actoMedico){
        String consulta="";
        try {
            consulta="CUENTAS_POR_PAGAR_PRECIO_VENTA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, actoMedico);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Facturador.txtImporteTotalVenta.setText(r.getString(1)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: calcularPrecioVenta:  " + e.getMessage());
        }
    } 
    
    public void calculoValorVenta(String dni,String tipo){
        String consulta="";
        try {
            consulta="CUENTAS_POR_PAGAR_TOTAL_VENTAS_GRAVADAS ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, dni);
            cmd.setString(2, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            double redondeo,ventaInafectada;
            while(r.next()){
            if(tipo.equalsIgnoreCase("5")){
                redondeo = Double.parseDouble(r.getString(1));
                BigDecimal bdVentaGravada  = new BigDecimal(redondeo);
                bdVentaGravada = bdVentaGravada.setScale(2, BigDecimal.ROUND_HALF_UP);
                Facturador.txtValorVentaGravada.setText(bdVentaGravada.toString()); 
            }
            if(tipo.equalsIgnoreCase("T")){
                redondeo = Double.parseDouble(r.getString(1));
                BigDecimal bdVentaGravada  = new BigDecimal(redondeo);
                bdVentaGravada = bdVentaGravada.setScale(2, BigDecimal.ROUND_HALF_UP);
                Facturador.txtValorVentaInafectada.setText(bdVentaGravada.toString()); 
            }
            }
            //
        } catch (Exception e) {
            System.out.println("Error: calculoValorVenta:  " + e.getMessage());
        }
    } 
    
    public CuentasPorPagarVentasConsolidadoCabecera() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

}
