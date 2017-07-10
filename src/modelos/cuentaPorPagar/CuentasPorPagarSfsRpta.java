/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.cuentaPorPagar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import modelos.Caja.Caja_NuevaVenta;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
public class CuentasPorPagarSfsRpta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Conexion con = new Conexion();
    private Connection cn;
    private Long id;
    private String nombre;
    DefaultTableModel m;

    public boolean mantenimientoCuentasPorPagarSfsRpta(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_MANTENIMIENTO_SFS_RPTA  ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getNombre());
            cmd.setString(2, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarSfsRpta: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean pagarFactura(String id)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_FACTURA_CABECERA_PAGAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, id);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: pagarFactura: " + ex.getMessage());
        }
        return resp;
    }
    
    public void listarFacturasAceptadas(JTable tabla, String nombre,String estado,String fechaI,String fechaF) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Tipo de Documento", "Nº Documento","Emisión","ID","","","","","Estado"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[9];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_LISTAR_SFS_RPTA ?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, nombre);
            cmd.setString(2, estado);
            cmd.setString(3, fechaI);
            cmd.setString(4, fechaF);
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
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoListarFacturasAceptadas(tabla);
        } catch (Exception e) {
            System.out.println("Error: ventasConsolidadoCabecera" + e.getMessage());
        }
    }

    public void formatoListarFacturasAceptadas(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(5).setMinWidth(0);
        tabla.getColumnModel().getColumn(5).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(75);
    }
    
    public void listarFacturasDetalles(JTable tabla, String id) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Nro","Cantidad", "CPT","Nomenclatura","Valor U.","IGV","Precio","Valor Venta"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[9];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_FACTURADOR_PAGOS_DETALLES ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = c + "º";
                fila[1] = r.getString(1);
                fila[2] = r.getString(2);
                fila[3] = r.getString(3);
                fila[4] = r.getString(4);
                fila[5] = r.getString(5);
                fila[6] = r.getString(6);
                fila[7] = r.getString(7);
                m.addRow(fila);
                c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoListarFacturasDetalles(tabla);
        } catch (Exception e) {
            System.out.println("Error: listarFacturasDetalles" + e.getMessage());
        }
    }

    public void formatoListarFacturasDetalles(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(40);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(500);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(90);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(67);
        tabla.setRowHeight(35);
    }
    
    public void reporteFactura(String idFactura) {
        try {
            Map parametros = new HashMap();
            parametros.put("FACTURA", idFactura);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/cuentasPorPagar/reporteFactura.jasper"), parametros, con.conectar());          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Factura Electrónica .::. " + idFactura );
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "reporteFactura:"+e.getMessage());
        }
    }
    
    public void reporteFacturasPorFecha(String estado,String fechaI,String fechaF) {
        try {
            Map parametros = new HashMap();
            parametros.put("ESTADO", estado);
            parametros.put("FECHAI", fechaI);
            parametros.put("FECHAF", fechaF);
            JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream("/Reportes/cuentasPorPagar/facturas.jasper"), parametros, con.conectar());          
            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Factura Electrónica .::. " + fechaI + " - " + fechaF );
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "reporteFacturasPorFecha:"+e.getMessage());
        }
    }
    
    
    public boolean mantenimientoCuentasPorPagarSfsRptaNotas(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CUENTAS_POR_PAGAR_MANTENIMIENTO_NOTAS_SFS_RPTA  ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getNombre());
            cmd.setString(2, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoCuentasPorPagarSfsRptaNotas: " + ex.getMessage());
        }
        return resp;
    }
    
        
    
    
    public CuentasPorPagarSfsRpta() {
         Conexion con = new Conexion();
         cn = con.conectar();
    }

    public CuentasPorPagarSfsRpta(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasPorPagarSfsRpta)) {
            return false;
        }
        CuentasPorPagarSfsRpta other = (CuentasPorPagarSfsRpta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.cuentaPorPagar.CuentasPorPagarSfsRpta[ id=" + id + " ]";
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
