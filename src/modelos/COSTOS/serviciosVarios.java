/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.COSTOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author silvana
 */
public class serviciosVarios {
     private Connection cn;
    private String cod_servi_va;
private String nom_servi_va;
 private String nom_usu ;

 
 public boolean guardarServiciosVarios()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SERVICIOS_VARIOS_insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_servi_va());
            cmd.setString(2, getNom_servi_va());
            cmd.setString(3, getNom_usu());
            
            
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
         
         
    public boolean modificarServiciosVarios()
    {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SERVICIOS_VARIOS_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_servi_va());
            cmd.setString(2, getNom_servi_va());
            cmd.setString(3, getNom_usu());
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
    
    
    public boolean eliminarServiciosVarios()
    {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SERVICIOS_VARIOS_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_servi_va());
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
   
 
    public String codServiciosVarios()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec COSTOS_COSTOS_SERVICIOS_VARIOS_generarid";
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
 
    public int ver_ServiciosVarios(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM COSTOS_SERVICIO_VARIOS where nom_servi_va=? AND estado_servicio_va='A'";
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
    
 
    public String codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_servi_va FROM COSTOS_SERVICIO_VARIOS where nom_servi_va=?";
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
    
    
     public serviciosVarios()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
     
    /**
     * @return the cod_servi_va
     */
    public String getCod_servi_va() {
        return cod_servi_va;
    }

    /**
     * @param cod_servi_va the cod_servi_va to set
     */
    public void setCod_servi_va(String cod_servi_va) {
        this.cod_servi_va = cod_servi_va;
    }

    /**
     * @return the nom_servi_va
     */
    public String getNom_servi_va() {
        return nom_servi_va;
    }

    /**
     * @param nom_servi_va the nom_servi_va to set
     */
    public void setNom_servi_va(String nom_servi_va) {
        this.nom_servi_va = nom_servi_va;
    }

    /**
     * @return the nom_usu
     */
    public String getNom_usu() {
        return nom_usu;
    }

    /**
     * @param nom_usu the nom_usu to set
     */
    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
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
