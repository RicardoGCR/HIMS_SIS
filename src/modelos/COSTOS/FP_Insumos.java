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
public class FP_Insumos {
    private Connection cn;
    private int cod_det_sus_pro_insu;
    private String cod_sustento_costo;
    private String cod_tipo_susten;
    private String cod_prod_refe;
    private String consumible;
    private Double cantidad_susten;
    private Double rendimiento_um;
    private String um;
    private Double cantidad_um;
    private Double precio_susten;
    private Double total_susten;
    private String nom_usu;
    
    
    public boolean guardarFP_Insumo()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_PROD_INSU_insertar ?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_prod_refe());
            cmd.setString(4, getConsumible());
            cmd.setDouble(5, getCantidad_susten());
            cmd.setDouble(6, getRendimiento_um());
            cmd.setString(7, getUm());
            cmd.setDouble(8, getCantidad_um());
            cmd.setDouble(9, getPrecio_susten());
            cmd.setDouble(10, getTotal_susten());
            cmd.setString(11, getNom_usu());
 
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
         

    public boolean eliminarFP_Insumo()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_PROD_INSU_eliminar ?";
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
    
    
    public FP_Insumos()
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
     * @return the cod_det_sus_pro_insu
     */
    public int getCod_det_sus_pro_insu() {
        return cod_det_sus_pro_insu;
    }

    /**
     * @param cod_det_sus_pro_insu the cod_det_sus_pro_insu to set
     */
    public void setCod_det_sus_pro_insu(int cod_det_sus_pro_insu) {
        this.cod_det_sus_pro_insu = cod_det_sus_pro_insu;
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
     * @return the cod_prod_refe
     */
    public String getCod_prod_refe() {
        return cod_prod_refe;
    }

    /**
     * @param cod_prod_refe the cod_prod_refe to set
     */
    public void setCod_prod_refe(String cod_prod_refe) {
        this.cod_prod_refe = cod_prod_refe;
    }

    /**
     * @return the consumible
     */
    public String getConsumible() {
        return consumible;
    }

    /**
     * @param consumible the consumible to set
     */
    public void setConsumible(String consumible) {
        this.consumible = consumible;
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
     * @return the rendimiento_um
     */
    public Double getRendimiento_um() {
        return rendimiento_um;
    }

    /**
     * @param rendimiento_um the rendimiento_um to set
     */
    public void setRendimiento_um(Double rendimiento_um) {
        this.rendimiento_um = rendimiento_um;
    }

    /**
     * @return the um
     */
    public String getUm() {
        return um;
    }

    /**
     * @param um the um to set
     */
    public void setUm(String um) {
        this.um = um;
    }

    /**
     * @return the cantidad_um
     */
    public Double getCantidad_um() {
        return cantidad_um;
    }

    /**
     * @param cantidad_um the cantidad_um to set
     */
    public void setCantidad_um(Double cantidad_um) {
        this.cantidad_um = cantidad_um;
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
