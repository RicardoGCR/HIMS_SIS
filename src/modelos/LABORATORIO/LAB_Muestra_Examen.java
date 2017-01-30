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
public class LAB_Muestra_Examen {
        private Connection cn;
    private String cod_muestra_para_exa;
private String nombre_muestra_para_exa;
 private String nom_usu ;

 
     public LAB_Muestra_Examen()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
 public boolean LAB_Muestra_Examen_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_MUESTRA_EXAMEN_insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_muestra_para_exa());
            cmd.setString(2, getNombre_muestra_para_exa());
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
         
         
    public boolean LAB_Muestra_Examen_modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_MUESTRA_EXAMEN_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_muestra_para_exa());
            cmd.setString(2, getNombre_muestra_para_exa());
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
    
    
    public boolean LAB_Muestra_Examen_eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_MUESTRA_EXAMEN_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_muestra_para_exa());
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
    
   
    public String LAB_Muestra_Examen_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_MUESTRA_EXAMEN_generarid";
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
 
    public int LAB_Muestra_Examen_ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM LABORATORIO_MUESTRA_EXAMEN where nombre_muestra_para_exa=? AND estado_muestra_examen='A'";
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
    
 
    public String LAB_Muestra_Examen_codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_muestra_para_exa FROM LABORATORIO_MUESTRA_EXAMEN where nombre_muestra_para_exa=?";
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
     * @return the cod_muestra_para_exa
     */
    public String getCod_muestra_para_exa() {
        return cod_muestra_para_exa;
    }

    /**
     * @param cod_muestra_para_exa the cod_muestra_para_exa to set
     */
    public void setCod_muestra_para_exa(String cod_muestra_para_exa) {
        this.cod_muestra_para_exa = cod_muestra_para_exa;
    }

    /**
     * @return the nombre_muestra_para_exa
     */
    public String getNombre_muestra_para_exa() {
        return nombre_muestra_para_exa;
    }

    /**
     * @param nombre_muestra_para_exa the nombre_muestra_para_exa to set
     */
    public void setNombre_muestra_para_exa(String nombre_muestra_para_exa) {
        this.nombre_muestra_para_exa = nombre_muestra_para_exa;
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
