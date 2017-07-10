/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaPorPagar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import servicios.Conexion;
import vista.Caja.Caja_Pagos;
import vista.cuentaPorPagar.BoletaElectronica;

/**
 *
 * @author Yamila
 */
public class CuentasPorPagarBoletaElectronica {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    public void ventasPorContado(JTable tabla, String dni,String fechaI,String fechaF) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"N°","Documento", "Nº Documento", "Forma de Pago", "DNI", "HC", "C", "Estado", "Dscto", "SubTotal", "IGV", "Total", "Fecha", "Hora", "Am", "ID"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[16];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_VENTAS_BOLETA_ELECTRONICA ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, dni);
            cmd.setString(2, fechaI);
            cmd.setString(3, fechaF);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = c + "°";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                fila[3] = r.getString(3);
                fila[4] = r.getString(4);
                fila[5] = r.getString(5);
                fila[6] = r.getString(6);
                fila[7] = r.getString(7);
                fila[8] = r.getString(8);
                fila[9] = r.getString(9);
                fila[10] = r.getString(10);
                fila[11] = r.getString(11);
                fila[12] = r.getString(12);
                fila[13] = r.getString(13);
                fila[14] = r.getString(14);
                fila[15] = r.getString(15);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoVentasPorContado(tabla);
        } catch (Exception e) {
            System.out.println("Error: ventasConsolidadoCabecera" + e.getMessage());
        }
    }

    public void formatoVentasPorContado(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(80);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(13).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(14).setMinWidth(0);
        tabla.getColumnModel().getColumn(14).setMaxWidth(0);
        tabla.getColumnModel().getColumn(15).setMinWidth(0);
        tabla.getColumnModel().getColumn(15).setMaxWidth(0);
    }
    
    public void formatoVentasConsolidadoDetalles(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(800);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(12).setMinWidth(0);
        tabla.getColumnModel().getColumn(12).setMaxWidth(0);
    }

    public void ventasPorContadoDetalles(JTable tabla, String id,String tipo) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"N°","Código CPT", "Nomenclatura", "Valor U.", "Área", "Cantidad", "Precio", "Descuento", "Total", "Personal", "Num Atención", "Turno",  "ID"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[13];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_VENTAS_CONSOLIDADO_DETALLES ?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            cmd.setString(2, tipo);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = c + "°";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                fila[3] = r.getString(3);
                fila[4] = r.getString(4);
                fila[5] = r.getString(5);
                fila[6] = r.getString(6);
                fila[7] = r.getString(7);
                fila[8] = r.getString(8);
                fila[9] = r.getString(9);
                fila[10] = r.getString(10);
                fila[11] = r.getString(11);
                fila[12] = r.getString(12);
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
    
    public void generarSerieCorrelativo(String documento){
        try {
            String consulta = "exec CUENTAS_POR_PAGAR_GENERAR_SERIE_CORRELATIVO '"+documento+"'";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               BoletaElectronica.lblSerie.setText(r.getString(1));
               BoletaElectronica.lblNroCorrelativo.setText(r.getString(2));
        }
        }catch(Exception ex){
            System.out.println("Error: generarSerieCorrelativo - " + ex.getMessage());
        }
    }

    public CuentasPorPagarBoletaElectronica() {
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
