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
public class LAB_Resultado_Muestra_Detalle {
    private Connection cn;
     private String cod_cab_resultado_mu_ana;
     private String num_resul_exa;
     private String cod_det_toma_mu_ana;
     private String cod_per_resultado;
     private String nombre_personal_resultado;
     private String cod_per_regis_resul;
     private String nombre_personal_regis_resul;
    private String nom_usu;
    
    public LAB_Resultado_Muestra_Detalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
     public boolean LAB_Resultado_Muestra_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_RESULTADO_MUESTRA_CAB_insertar ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_cab_resultado_mu_ana());
            cmd.setString(2, getNum_resul_exa());
            cmd.setString(3, getCod_det_toma_mu_ana());
            cmd.setString(4, getCod_per_resultado());
            cmd.setString(5, getNombre_personal_resultado());
            cmd.setString(6, getCod_per_regis_resul());
            cmd.setString(7, getNombre_personal_regis_resul());
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
     
    public String LAB_Resultado_Muestra_Cab_generarid(String tipo){
       String unidad="";
        try{
           String consulta="exec sp_LAB_RESULTADO_MUESTRA_CAB_generarid ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,tipo);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               unidad = r.getString(1);
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

    /**
     * @return the cod_cab_resultado_mu_ana
     */
    public String getCod_cab_resultado_mu_ana() {
        return cod_cab_resultado_mu_ana;
    }

    /**
     * @param cod_cab_resultado_mu_ana the cod_cab_resultado_mu_ana to set
     */
    public void setCod_cab_resultado_mu_ana(String cod_cab_resultado_mu_ana) {
        this.cod_cab_resultado_mu_ana = cod_cab_resultado_mu_ana;
    }

    /**
     * @return the num_resul_exa
     */
    public String getNum_resul_exa() {
        return num_resul_exa;
    }

    /**
     * @param num_resul_exa the num_resul_exa to set
     */
    public void setNum_resul_exa(String num_resul_exa) {
        this.num_resul_exa = num_resul_exa;
    }

    /**
     * @return the cod_det_toma_mu_ana
     */
    public String getCod_det_toma_mu_ana() {
        return cod_det_toma_mu_ana;
    }

    /**
     * @param cod_det_toma_mu_ana the cod_det_toma_mu_ana to set
     */
    public void setCod_det_toma_mu_ana(String cod_det_toma_mu_ana) {
        this.cod_det_toma_mu_ana = cod_det_toma_mu_ana;
    }

    /**
     * @return the cod_per_resultado
     */
    public String getCod_per_resultado() {
        return cod_per_resultado;
    }

    /**
     * @param cod_per_resultado the cod_per_resultado to set
     */
    public void setCod_per_resultado(String cod_per_resultado) {
        this.cod_per_resultado = cod_per_resultado;
    }

    /**
     * @return the nombre_personal_resultado
     */
    public String getNombre_personal_resultado() {
        return nombre_personal_resultado;
    }

    /**
     * @param nombre_personal_resultado the nombre_personal_resultado to set
     */
    public void setNombre_personal_resultado(String nombre_personal_resultado) {
        this.nombre_personal_resultado = nombre_personal_resultado;
    }

    /**
     * @return the cod_per_regis_resul
     */
    public String getCod_per_regis_resul() {
        return cod_per_regis_resul;
    }

    /**
     * @param cod_per_regis_resul the cod_per_regis_resul to set
     */
    public void setCod_per_regis_resul(String cod_per_regis_resul) {
        this.cod_per_regis_resul = cod_per_regis_resul;
    }

    /**
     * @return the nombre_personal_regis_resul
     */
    public String getNombre_personal_regis_resul() {
        return nombre_personal_regis_resul;
    }

    /**
     * @param nombre_personal_regis_resul the nombre_personal_regis_resul to set
     */
    public void setNombre_personal_regis_resul(String nombre_personal_regis_resul) {
        this.nombre_personal_regis_resul = nombre_personal_regis_resul;
    }
    
    
}
