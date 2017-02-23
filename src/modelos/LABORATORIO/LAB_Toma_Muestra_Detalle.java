/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Toma_Muestra_Detalle {
    private Connection cn;
    private String cod_cab_toma_mu_exa;
    private String id_documento;
    private String num_toma_mu_exa;
    private String cod_per_toma_muestra;
    private String nombre_per_toma_muestra;
    private String cod_per_regis_toma_muestra;
    private String nombre_per_regis_toma_muestra;
    private String nom_usu;
    
    public LAB_Toma_Muestra_Detalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
     public boolean LAB_Toma_Muestra_Det_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_TOMA_MUESTRA_CAB_insertar ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_cab_toma_mu_exa());
            cmd.setString(2, getId_documento());
            cmd.setString(3, getNum_toma_mu_exa());
            cmd.setString(4, getCod_per_toma_muestra());
            cmd.setString(5, getNombre_per_toma_muestra());
            cmd.setString(6, getCod_per_regis_toma_muestra());
            cmd.setString(7, getNombre_per_regis_toma_muestra());
            cmd.setString(8, getNom_usu());
 
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
     
    public String LAB_Toma_Muestra_Det_exa(String exa,String tipo){
       String cod_exa="";
        try{
           String consulta="exec sp_LAB_TOMA_MUESTRA_DET_EXAMEN ?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,exa);
            cmd.setString(2,tipo);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               cod_exa = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod_exa;
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
     * @return the cod_cab_toma_mu_exa
     */
    public String getCod_cab_toma_mu_exa() {
        return cod_cab_toma_mu_exa;
    }

    /**
     * @param cod_cab_toma_mu_exa the cod_cab_toma_mu_exa to set
     */
    public void setCod_cab_toma_mu_exa(String cod_cab_toma_mu_exa) {
        this.cod_cab_toma_mu_exa = cod_cab_toma_mu_exa;
    }

    /**
     * @return the id_documento
     */
    public String getId_documento() {
        return id_documento;
    }

    /**
     * @param id_documento the id_documento to set
     */
    public void setId_documento(String id_documento) {
        this.id_documento = id_documento;
    }

    /**
     * @return the num_toma_mu_exa
     */
    public String getNum_toma_mu_exa() {
        return num_toma_mu_exa;
    }

    /**
     * @param num_toma_mu_exa the num_toma_mu_exa to set
     */
    public void setNum_toma_mu_exa(String num_toma_mu_exa) {
        this.num_toma_mu_exa = num_toma_mu_exa;
    }

    /**
     * @return the cod_per_toma_muestra
     */
    public String getCod_per_toma_muestra() {
        return cod_per_toma_muestra;
    }

    /**
     * @param cod_per_toma_muestra the cod_per_toma_muestra to set
     */
    public void setCod_per_toma_muestra(String cod_per_toma_muestra) {
        this.cod_per_toma_muestra = cod_per_toma_muestra;
    }

    /**
     * @return the nombre_per_toma_muestra
     */
    public String getNombre_per_toma_muestra() {
        return nombre_per_toma_muestra;
    }

    /**
     * @param nombre_per_toma_muestra the nombre_per_toma_muestra to set
     */
    public void setNombre_per_toma_muestra(String nombre_per_toma_muestra) {
        this.nombre_per_toma_muestra = nombre_per_toma_muestra;
    }

    /**
     * @return the cod_per_regis_toma_muestra
     */
    public String getCod_per_regis_toma_muestra() {
        return cod_per_regis_toma_muestra;
    }

    /**
     * @param cod_per_regis_toma_muestra the cod_per_regis_toma_muestra to set
     */
    public void setCod_per_regis_toma_muestra(String cod_per_regis_toma_muestra) {
        this.cod_per_regis_toma_muestra = cod_per_regis_toma_muestra;
    }

    /**
     * @return the nombre_per_regis_toma_muestra
     */
    public String getNombre_per_regis_toma_muestra() {
        return nombre_per_regis_toma_muestra;
    }

    /**
     * @param nombre_per_regis_toma_muestra the nombre_per_regis_toma_muestra to set
     */
    public void setNombre_per_regis_toma_muestra(String nombre_per_regis_toma_muestra) {
        this.nombre_per_regis_toma_muestra = nombre_per_regis_toma_muestra;
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
