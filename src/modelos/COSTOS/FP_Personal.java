/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.COSTOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author Profe
 */
public class FP_Personal {
    private Connection cn;
    private int cod_sust_det_per;
    private String cod_sustento_costo;
    private String cod_tipo_susten;
    private String cod_sueldo_bru;
    private int tiempo_hora_susten;
    private int tiempo_min_susten;
    private int hora_mes_susten;
    private double sueldo_susten;
    private double total_susten;
    private String nom_usu;


    public boolean guardarFP_Personal()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_PERSONAL_insertar ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_sueldo_bru());
            cmd.setInt(4, getTiempo_hora_susten());
            cmd.setInt(5, getTiempo_min_susten());
            cmd.setInt(6, getHora_mes_susten());
            cmd.setDouble(7, getSueldo_susten());
            cmd.setDouble(8, getTotal_susten());
            cmd.setString(9, getNom_usu());
            if(!cmd.execute()) {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
         

    public boolean eliminarFP_Personal()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_PERSONAL_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
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
    
    
     public FP_Personal()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
     * @return the cod_sust_det_per
     */
    public int getCod_sust_det_per() {
        return cod_sust_det_per;
    }

    /**
     * @param cod_sust_det_per the cod_sust_det_per to set
     */
    public void setCod_sust_det_per(int cod_sust_det_per) {
        this.cod_sust_det_per = cod_sust_det_per;
    }

    /**
     * @return the cod_sustento_costo
     */
    public String getCod_sustento_costo() {
        return cod_sustento_costo;
    }

    /**
     * @param cod_sustento_costo the cod_sustento_costo to set
     */
    public void setCod_sustento_costo(String cod_sustento_costo) {
        this.cod_sustento_costo = cod_sustento_costo;
    }

    /**
     * @return the cod_tipo_susten
     */
    public String getCod_tipo_susten() {
        return cod_tipo_susten;
    }

    /**
     * @param cod_tipo_susten the cod_tipo_susten to set
     */
    public void setCod_tipo_susten(String cod_tipo_susten) {
        this.cod_tipo_susten = cod_tipo_susten;
    }

    /**
     * @return the cod_sueldo_bru
     */
    public String getCod_sueldo_bru() {
        return cod_sueldo_bru;
    }

    /**
     * @param cod_sueldo_bru the cod_sueldo_bru to set
     */
    public void setCod_sueldo_bru(String cod_sueldo_bru) {
        this.cod_sueldo_bru = cod_sueldo_bru;
    }

    /**
     * @return the tiempo_hora_susten
     */
    public int getTiempo_hora_susten() {
        return tiempo_hora_susten;
    }

    /**
     * @param tiempo_hora_susten the tiempo_hora_susten to set
     */
    public void setTiempo_hora_susten(int tiempo_hora_susten) {
        this.tiempo_hora_susten = tiempo_hora_susten;
    }

    /**
     * @return the tiempo_min_susten
     */
    public int getTiempo_min_susten() {
        return tiempo_min_susten;
    }

    /**
     * @param tiempo_min_susten the tiempo_min_susten to set
     */
    public void setTiempo_min_susten(int tiempo_min_susten) {
        this.tiempo_min_susten = tiempo_min_susten;
    }

    /**
     * @return the hora_mes_susten
     */
    public int getHora_mes_susten() {
        return hora_mes_susten;
    }

    /**
     * @param hora_mes_susten the hora_mes_susten to set
     */
    public void setHora_mes_susten(int hora_mes_susten) {
        this.hora_mes_susten = hora_mes_susten;
    }

    /**
     * @return the sueldo_susten
     */
    public double getSueldo_susten() {
        return sueldo_susten;
    }

    /**
     * @param sueldo_susten the sueldo_susten to set
     */
    public void setSueldo_susten(double sueldo_susten) {
        this.sueldo_susten = sueldo_susten;
    }

    /**
     * @return the total_susten
     */
    public double getTotal_susten() {
        return total_susten;
    }

    /**
     * @param total_susten the total_susten to set
     */
    public void setTotal_susten(double total_susten) {
        this.total_susten = total_susten;
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
