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
 * @author USUARIO
 */
public class SIS_REGLA_VALIDACION_DET {
    private Connection cn;
    private int ID_DETALLE;
    private int ID_REGLA;
    private String NOM_CAMPO;
    private String NOM_USU;
    
    public SIS_REGLA_VALIDACION_DET()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean SIS_REGLA_VALIDACION_DET_GUARDAR()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_REGLA());
            cmd.setString(2, getNOM_CAMPO());  
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
    
    public boolean SIS_REGLA_VALIDACION_DET_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_MODIFICAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DETALLE());
            cmd.setInt(2, getID_REGLA());
            cmd.setString(3, getNOM_CAMPO());
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
    
    public boolean SIS_REGLA_VALIDACION_DET_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_DETALLE_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_REGLA());
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
    
    public int SIS_REGLA_VALIDACION_DET_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SIS_REGLA_VALIDACION_DETALLE where NOMBRE_CAMPO=?";
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

    public int getID_REGLA() {
        return ID_REGLA;
    }

    public void setID_REGLA(int ID_REGLA) {
        this.ID_REGLA = ID_REGLA;
    }

    public String getNOM_CAMPO() {
        return NOM_CAMPO;
    }

    public void setNOM_CAMPO(String NOM_CAMPO) {
        this.NOM_CAMPO = NOM_CAMPO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
        
}
