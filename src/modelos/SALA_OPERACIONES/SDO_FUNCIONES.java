/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.SALA_OPERACIONES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class SDO_FUNCIONES {
    private Connection cn;
    private String ID_FUNCIONES;
    private String DESCRIP_FUNCIONES;
    private String NOM_USU;
    
    public SDO_FUNCIONES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    public String SDO_FUNCIONES_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SALA_OPERACION_FUNCIONES_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    
    public boolean SDO_Funciones_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_FUNCIONES_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_FUNCIONES());
            cmd.setString(2, getDESCRIP_FUNCIONES());
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
    
    public boolean SDO_Funciones_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_FUNCIONES_MODIFICAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_FUNCIONES());
            cmd.setString(2, getDESCRIP_FUNCIONES());
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
    
    public boolean SDO_Funciones_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_FUNCIONES_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_FUNCIONES());
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

    public int SDO_Funciones_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SALA_OPERACION_FUNCIONES where DESCRIPCION_FUNCIONES = ? AND ESTADO_FUNCIONES='A'";
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
    
    
    public String SDO_Funciones_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_FUNCIONES FROM SALA_OPERACION_FUNCIONES where DESCRIPCION_FUNCIONES=?";
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

    public String getID_FUNCIONES() {
        return ID_FUNCIONES;
    }

    public void setID_FUNCIONES(String ID_FUNCIONES) {
        this.ID_FUNCIONES = ID_FUNCIONES;
    }

    public String getDESCRIP_FUNCIONES() {
        return DESCRIP_FUNCIONES;
    }

    public void setDESCRIP_FUNCIONES(String DESCRIP_FUNCIONES) {
        this.DESCRIP_FUNCIONES = DESCRIP_FUNCIONES;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
      
}
