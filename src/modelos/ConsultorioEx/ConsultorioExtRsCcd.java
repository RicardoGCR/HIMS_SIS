/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.Conexion;

/**
 *
 * @author PC02
 */

public class ConsultorioExtRsCcd implements Serializable {
    private static final long serialVersionUID = 1L;
     static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rsCcd;
    private String fecha;
    private String fua;
    private int ID_CIE10;
    private int RS_ID;
    private String estado;

    
    public boolean mantenimientoCXTriaje(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CX_TRIAJE_MANTANIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsCcd());
            cmd.setInt(2, getRS_ID());
            cmd.setString(3, getFecha());
            cmd.setInt(4, getID_CIE10());
            cmd.setString(5, getFua());
 
            cmd.setString(12, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoAdmisionemergenciaTriaje: " + ex.getMessage());
        }
        return resp;
    }
    
    public ConsultorioExtRsCcd() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getRsCcd() {
        return rsCcd;
    }

    public void setRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFua() {
        return fua;
    }

    public void setFua(String fua) {
        this.fua = fua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }
    

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getRS_ID() {
        return RS_ID;
    }

    public void setRS_ID(int RS_ID) {
        this.RS_ID = RS_ID;
    }
    
    


}
