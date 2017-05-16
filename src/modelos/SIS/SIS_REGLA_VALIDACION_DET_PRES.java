/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SIS_REGLA_VALIDACION_DET_PRES {
    private Connection cn;
    private int ID_DETALLE;
    private int ID_REGLA;
    private String ID_PRESTACION;
    private String NOMBRE_CAMPO;
    private String CANTIDAD1;
    private String CANTIDAD2;
    
    public SIS_REGLA_VALIDACION_DET_PRES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean SIS_REGLA_VALIDACION_DET_PRES_GUARDAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_CA_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_REGLA());
            cmd.setString(2, getID_PRESTACION());
            cmd.setString(3, getNOMBRE_CAMPO());  
            cmd.setString(4, getCANTIDAD1());
            cmd.setString(5, getCANTIDAD2());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean SIS_REGLA_VALIDACION_DET_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_CA_MODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_REGLA());
            cmd.setString(2, getID_PRESTACION());
            cmd.setString(3, getNOMBRE_CAMPO());  
            cmd.setString(4, getCANTIDAD1());
            cmd.setString(5, getCANTIDAD2());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(int ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

    public int getID_REGLA() {
        return ID_REGLA;
    }

    public void setID_REGLA(int ID_REGLA) {
        this.ID_REGLA = ID_REGLA;
    }

    public String getID_PRESTACION() {
        return ID_PRESTACION;
    }

    public void setID_PRESTACION(String ID_PRESTACION) {
        this.ID_PRESTACION = ID_PRESTACION;
    }

    public String getNOMBRE_CAMPO() {
        return NOMBRE_CAMPO;
    }

    public void setNOMBRE_CAMPO(String NOMBRE_CAMPO) {
        this.NOMBRE_CAMPO = NOMBRE_CAMPO;
    }

    public String getCANTIDAD1() {
        return CANTIDAD1;
    }

    public void setCANTIDAD1(String CANTIDAD1) {
        this.CANTIDAD1 = CANTIDAD1;
    }

    public String getCANTIDAD2() {
        return CANTIDAD2;
    }

    public void setCANTIDAD2(String CANTIDAD2) {
        this.CANTIDAD2 = CANTIDAD2;
    }
    
}
