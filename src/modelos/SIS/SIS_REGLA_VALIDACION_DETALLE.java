/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SIS_REGLA_VALIDACION_DETALLE {
    private Connection cn;
    private int ID_DETALLE;
    private int ID_VALIDACION;
    private String NOMBRE_CAMPO;
    private String NOM_USU;
    
    public SIS_REGLA_VALIDACION_DETALLE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean SIS_REGLA_VALIDACION_DETALLE_GUARDAR()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_VALIDACION());
            cmd.setString(2, getNOMBRE_CAMPO());
            cmd.setString(3, getNOM_USU());
            
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
    
    public boolean SIS_REGLA_VALIDACION_DETALLE_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_MODIFICAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DETALLE());
            cmd.setInt(2, getID_VALIDACION());
            cmd.setString(3, getNOMBRE_CAMPO());
            cmd.setString(4, getNOM_USU());
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
    
    public boolean SIS_REGLA_VALIDACION_DETALLE_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_VALIDACION());
            if(!cmd.execute()){
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
    
    public int SIS_REGLA_VALIDACION_DETALLE_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SIS_REGLA_VALIDACION_DETALLE where NOMBRE_CAMPO = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
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

    public int getID_VALIDACION() {
        return ID_VALIDACION;
    }

    public void setID_VALIDACION(int ID_VALIDACION) {
        this.ID_VALIDACION = ID_VALIDACION;
    }

    public String getNOMBRE_CAMPO() {
        return NOMBRE_CAMPO;
    }

    public void setNOMBRE_CAMPO(String NOMBRE_CAMPO) {
        this.NOMBRE_CAMPO = NOMBRE_CAMPO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
     
}
