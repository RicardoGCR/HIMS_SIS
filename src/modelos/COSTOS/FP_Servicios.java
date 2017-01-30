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
public class FP_Servicios {
    private Connection cn;
    private int cod_det_sus_servi;
    private String cod_sustento_costo;
    private String cod_tipo_susten;
    private String cod_servi_va;
    private Double cantidad_susten;
    private Double precio_susten;
    private Double total_susten;
    private String nom_usu;
    
    public FP_Servicios(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    public boolean guardarFP_Servicios()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_insertar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_servi_va());
            cmd.setDouble(4, getCantidad_susten());
            cmd.setDouble(5, getPrecio_susten());
            cmd.setDouble(6, getTotal_susten());
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
         

    public boolean eliminarFP_Servicios()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_SERVICIO_eliminar ?";
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
     * @return the cod_det_sus_servi
     */
    public int getCod_det_sus_servi() {
        return cod_det_sus_servi;
    }

    /**
     * @param cod_det_sus_servi the cod_det_sus_servi to set
     */
    public void setCod_det_sus_servi(int cod_det_sus_servi) {
        this.cod_det_sus_servi = cod_det_sus_servi;
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
     * @return the cantidad_susten
     */
    public Double getCantidad_susten() {
        return cantidad_susten;
    }

    /**
     * @param cantidad_susten the cantidad_susten to set
     */
    public void setCantidad_susten(Double cantidad_susten) {
        this.cantidad_susten = cantidad_susten;
    }

    /**
     * @return the precio_susten
     */
    public Double getPrecio_susten() {
        return precio_susten;
    }

    /**
     * @param precio_susten the precio_susten to set
     */
    public void setPrecio_susten(Double precio_susten) {
        this.precio_susten = precio_susten;
    }

    /**
     * @return the total_susten
     */
    public Double getTotal_susten() {
        return total_susten;
    }

    /**
     * @param total_susten the total_susten to set
     */
    public void setTotal_susten(Double total_susten) {
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
