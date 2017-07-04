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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import modelos.Caja.Caja_NuevaVenta;
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
    
    public void listarFacturasAceptadas(JTable tabla, String nombre) {
        String consulta = "";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[] = {"Tipo de Documento", "Nº Documento"};
            m = new DefaultTableModel(null, titulos);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Caja_NuevaVenta obj = new Caja_NuevaVenta();
            consulta = "CUENTAS_POR_PAGAR_LISTAR_SFS_RPTA ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, nombre);
            ResultSet r = cmd.executeQuery();
            int c = 1;
            while (r.next()) {
                fila[0] = r.getString(1);
                fila[1] = r.getString(2);
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
        tabla.getColumnModel().getColumn(0).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(190);
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
