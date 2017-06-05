/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.ConsultorioEx.MensajeTv;

/**
 *
 * @author PC02
 */

public class ConsultorioExtMensaje implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    private int id;
    private String mensaje;

    public boolean mantenimientoConsultorioMensaje(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_MENSAJE ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId());
            cmd.setString(2, getMensaje());
            cmd.setString(3, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioMensaje : " + ex.getMessage());
        }
        return resp;
    }
    
    public void listarMensaje(JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"ID","Mensaje"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];
            consulta="EXEC CONSULTORIO_EXT_LISTAR_MENSAJE";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // 
                fila[1]=r.getString(2); // 
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: listarMensaje: " + e.getMessage());
        }
    }
    
    public void listarModMensaje(String idMensaje){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_MOD_MENSAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, idMensaje);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                MensajeTv.txtMensaje.setText(r.getString(2)); 
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: listarModMensajes  " + e.getMessage());
        }
    }
    
    public ConsultorioExtMensaje() {
          Conexion con = new Conexion();
          cn = con.conectar();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtMensaje)) {
            return false;
        }
        ConsultorioExtMensaje other = (ConsultorioExtMensaje) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtMensaje[ id=" + id + " ]";
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
