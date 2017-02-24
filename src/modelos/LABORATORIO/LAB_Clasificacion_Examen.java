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
public class LAB_Clasificacion_Examen {
      private Connection cn;
    private String cod_clasi_exa;
private String nombre_clasi_exa;
private String cod_uni_organica_jerar;
private String observacion_clasi;
 private String nom_usu ;

 
     public LAB_Clasificacion_Examen()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
 public boolean LAB_Clasificacion_Examen_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_CLASIFICACION_EXAMEN_insertar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_clasi_exa());
            cmd.setString(2, getNombre_clasi_exa());
            cmd.setString(3, getCod_uni_organica_jerar());
            cmd.setString(4, getObservacion_clasi());
            cmd.setString(5, getNom_usu());
            
            
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
         
         
    public boolean LAB_Clasificacion_Examen_modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_CLASIFICACION_EXAMEN_modificar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_clasi_exa());
            cmd.setString(2, getNombre_clasi_exa());
            cmd.setString(3, getCod_uni_organica_jerar());
            cmd.setString(4, getObservacion_clasi());
            cmd.setString(5, getNom_usu());
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
    
    
    public boolean LAB_Clasificacion_Examen_eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_CLASIFICACION_EXAMEN_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_clasi_exa());
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
   
 
    public String LAB_Clasificacion_Examen_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_generarid";
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
 
    public int LAB_Clasificacion_Examen_ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM LABORATORIO_CLASIFICACION_EXAMEN where cod_uni_organica_jerar=? AND estado_clasi_exa='A'";
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
    
 
    public String LAB_Clasificacion_Examen_codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_clasi_exa FROM LABORATORIO_CLASIFICACION_EXAMEN where cod_uni_organica_jerar=?";
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

    public String LAB_Clasificacion_Examen_buscar(String clas)   {
        String unidad="";
        try{
           String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_buscar ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,clas);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               unidad = r.getString(5);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return unidad;
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
     * @return the cod_clasi_exa
     */
    public String getCod_clasi_exa() {
        return cod_clasi_exa;
    }

    /**
     * @param cod_clasi_exa the cod_clasi_exa to set
     */
    public void setCod_clasi_exa(String cod_clasi_exa) {
        this.cod_clasi_exa = cod_clasi_exa;
    }

    /**
     * @return the nombre_clasi_exa
     */
    public String getNombre_clasi_exa() {
        return nombre_clasi_exa;
    }

    /**
     * @param nombre_clasi_exa the nombre_clasi_exa to set
     */
    public void setNombre_clasi_exa(String nombre_clasi_exa) {
        this.nombre_clasi_exa = nombre_clasi_exa;
    }

    /**
     * @return the cod_uni_organica_jerar
     */
    public String getCod_uni_organica_jerar() {
        return cod_uni_organica_jerar;
    }

    /**
     * @param cod_uni_organica_jerar the cod_uni_organica_jerar to set
     */
    public void setCod_uni_organica_jerar(String cod_uni_organica_jerar) {
        this.cod_uni_organica_jerar = cod_uni_organica_jerar;
    }

    /**
     * @return the observacion_clasi
     */
    public String getObservacion_clasi() {
        return observacion_clasi;
    }

    /**
     * @param observacion_clasi the observacion_clasi to set
     */
    public void setObservacion_clasi(String observacion_clasi) {
        this.observacion_clasi = observacion_clasi;
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
