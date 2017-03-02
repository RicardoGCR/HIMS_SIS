/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author Profe
 */
public class LAB_Toma_Muestra_Subdetalle {
     private Connection cn;
     private String cod_det_toma_mu_ana;
    private String cod_muestra_para_exa;
    private String cod_contenedor_exa;
    private String AR_ID;
    private String cantidad;
    private String codigo_barras;
    private String nom_usu;
    
     
     public LAB_Toma_Muestra_Subdetalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

     public boolean LAB_Toma_Muestra_SubDetalle_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_TOMA_MUESTRA_SUBDET_insertar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_det_toma_mu_ana());
            cmd.setString(2, getCod_muestra_para_exa());
            cmd.setString(3, getCod_contenedor_exa());
            cmd.setString(4, getAR_ID());
            cmd.setString(5, getCantidad());
            cmd.setString(6, getCodigo_barras());
            cmd.setString(7, getNom_usu());
 
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
     * @return the AR_ID
     */
    public String getAR_ID() {
        return AR_ID;
    }

    /**
     * @param AR_ID the AR_ID to set
     */
    public void setAR_ID(String AR_ID) {
        this.AR_ID = AR_ID;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the codigo_barras
     */
    public String getCodigo_barras() {
        return codigo_barras;
    }

    /**
     * @param codigo_barras the codigo_barras to set
     */
    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
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
