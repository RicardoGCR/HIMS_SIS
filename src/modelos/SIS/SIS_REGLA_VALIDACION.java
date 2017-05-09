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
public class SIS_REGLA_VALIDACION {
    private Connection cn;
    private int ID_REGLA;
    private String NUM_REGLA;
    private String DESCRIPCION;
    
    public SIS_REGLA_VALIDACION()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean SIS_REGLA_VALIDACION_GUARDAR()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_INSERTAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getNUM_REGLA());
            cmd.setString(2, getDESCRIPCION());            
            
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
    
    public boolean SIS_REGLA_VALIDACION_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_MODIFICAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_REGLA());
            cmd.setString(2, getNUM_REGLA());
            cmd.setString(3, getDESCRIPCION());
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
    
    public boolean SIS_REGLA_VALIDACION_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_REGLA_VALIDACION_ELIMINAR ?";
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
    
    public int SIS_REGLA_VALIDACION_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SIS_REGLA_VALIDACION where DESCRIPCION=?";
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

    public String SIS_REGLA_VALIDACION_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT NUM_VALIDACION FROM SIS_REGLA_VALIDACION where DESCRIPCION=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getNUM_REGLA() {
        return NUM_REGLA;
    }

    public void setNUM_REGLA(String NUM_REGLA) {
        this.NUM_REGLA = NUM_REGLA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID_REGLA() {
        return ID_REGLA;
    }

    public void setID_REGLA(int ID_REGLA) {
        this.ID_REGLA = ID_REGLA;
    }
    
      
}
