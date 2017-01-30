/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Contenedor_Muestra {
        private Connection cn;
    private String cod_contenedor_exa;
private String nombre_contenedor;
private String material_contenedor;
 private String nom_usu ;

 
     public LAB_Contenedor_Muestra()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
 public boolean LAB_Contenedor_Muestra_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_CONTENEDOR_MUESTRA_insertar ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_contenedor_exa());
            cmd.setString(2, getNombre_contenedor());
            cmd.setString(3, getMaterial_contenedor());
            cmd.setString(4, getNom_usu());
            
            
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
         
         
    public boolean LAB_Contenedor_Muestra_modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec  sp_LAB_CONTENEDOR_MUESTRA_modificar ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_contenedor_exa());
            cmd.setString(2, getNombre_contenedor());
            cmd.setString(3, getMaterial_contenedor());
            cmd.setString(4, getNom_usu());
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
    
    
    public boolean LAB_Contenedor_Muestra_eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_CONTENEDOR_MUESTRA_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_contenedor_exa());
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
    
   
    public String LAB_Contenedor_Muestra_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_CONTENEDOR_MUESTRA_generarid";
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
 
    public int LAB_Contenedor_Muestra_ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM LABORATORIO_CONTENEDOR_MUESTRA where nombre_contenedor=? AND estado_contenedor='A'";
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
    
 
    public String LAB_Contenedor_Muestra_codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_contenedor_exa FROM LABORATORIO_CONTENEDOR_MUESTRA where nombre_contenedor=?";
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

    /**
     * @return the cod_contenedor_exa
     */
    public String getCod_contenedor_exa() {
        return cod_contenedor_exa;
    }

    /**
     * @param cod_contenedor_exa the cod_contenedor_exa to set
     */
    public void setCod_contenedor_exa(String cod_contenedor_exa) {
        this.cod_contenedor_exa = cod_contenedor_exa;
    }

    /**
     * @return the nombre_contenedor
     */
    public String getNombre_contenedor() {
        return nombre_contenedor;
    }

    /**
     * @param nombre_contenedor the nombre_contenedor to set
     */
    public void setNombre_contenedor(String nombre_contenedor) {
        this.nombre_contenedor = nombre_contenedor;
    }

    /**
     * @return the material_contenedor
     */
    public String getMaterial_contenedor() {
        return material_contenedor;
    }

    /**
     * @param material_contenedor the material_contenedor to set
     */
    public void setMaterial_contenedor(String material_contenedor) {
        this.material_contenedor = material_contenedor;
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


}
