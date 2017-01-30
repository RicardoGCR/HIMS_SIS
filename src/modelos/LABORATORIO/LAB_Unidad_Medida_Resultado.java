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
public class LAB_Unidad_Medida_Resultado {
      private Connection cn;
    private String cod_uni_med_exa;
private String descripcion_unidad_medida;
 private String nom_usu ;

 
     public LAB_Unidad_Medida_Resultado()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
 public boolean LAB_Unidad_Medida_Resultado_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_uni_med_exa());
            cmd.setString(2, getDescripcion_unidad_medida());
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
         
         
    public boolean LAB_Unidad_Medida_Resultado_modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_uni_med_exa());
            cmd.setString(2, getDescripcion_unidad_medida());
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
    
    
    public boolean LAB_Unidad_Medida_Resultado_eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_uni_med_exa());
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
   
 
    public String LAB_Unidad_Medida_Resultado_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_generarid";
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
 
    public int LAB_Unidad_Medida_Resultado_ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM LABORATORIO_UNIDAD_MEDIDA_RESULTADO where descripcion_unidad_medida=? AND estado_uni_med_exa='A'";
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
    
 
    public String LAB_Unidad_Medida_Resultado_codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_uni_med_exa FROM LABORATORIO_UNIDAD_MEDIDA_RESULTADO where descripcion_unidad_medida=?";
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
     * @return the cod_uni_med_exa
     */
    public String getCod_uni_med_exa() {
        return cod_uni_med_exa;
    }

    /**
     * @param cod_uni_med_exa the cod_uni_med_exa to set
     */
    public void setCod_uni_med_exa(String cod_uni_med_exa) {
        this.cod_uni_med_exa = cod_uni_med_exa;
    }

    /**
     * @return the descripcion_unidad_medida
     */
    public String getDescripcion_unidad_medida() {
        return descripcion_unidad_medida;
    }

    /**
     * @param descripcion_unidad_medida the descripcion_unidad_medida to set
     */
    public void setDescripcion_unidad_medida(String descripcion_unidad_medida) {
        this.descripcion_unidad_medida = descripcion_unidad_medida;
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
