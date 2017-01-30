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
public class SDO_CONDICION {
    private Connection cn;
    private String ID_CONDICION;
    private String DESCRIP_CONDICION;
    private String NOM_USU;
    
    public SDO_CONDICION()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
        public String SDO_CONDICION_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SALA_OPERACION_CONDICION_GENERAR_ID";
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
    
    public boolean SDO_CONDICION_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_CONDICION_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_CONDICION());
            cmd.setString(2, getDESCRIP_CONDICION());
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
    
    public boolean SDO_CONDICION_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_CONDICION_MODIFICAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_CONDICION());
            cmd.setString(2, getDESCRIP_CONDICION());
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
    
    public boolean SDO_CONDICION_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_CONDICION_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_CONDICION());
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
    
    public int SDO_CONDICION_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SALA_OPERACION_CONDICION where DESCRIP_CONDICION=? AND ESTADO_CONDICION='A'";
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
    
    public String SDO_CONDICION_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_CONDICION FROM SALA_OPERACION_CONDICION where DESCRIP_CONDICION=?";
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

    public String getID_CONDICION() {
        return ID_CONDICION;
    }

    public void setID_CONDICION(String ID_CONDICION) {
        this.ID_CONDICION = ID_CONDICION;
    }

    public String getDESCRIP_CONDICION() {
        return DESCRIP_CONDICION;
    }

    public void setDESCRIP_CONDICION(String DESCRIP_CONDICION) {
        this.DESCRIP_CONDICION = DESCRIP_CONDICION;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

 
    
}
