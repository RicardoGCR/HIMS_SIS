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
public class FP_SBasicos {
    private Connection cn;
    private int cod_det_sus_serv_bas;
    private String cod_sustento_costo;
    private String cod_tipo_susten;
    private String cod_servi_va;
    private double area;
    private double total_consultas;
    private double ponderacion_cons_energia;
    private double base_asig_cons_energia;
    private double sum_base_asig_cons_energia;
    private double coefic_cons_energia;
    private double consumo_energia;
    private double costo_estandar_energia;
    private double ponderacion_cons_agua;
    private double base_asig_cons_agua;
    private double sum_base_asig_cons_agua;
    private double coefic_cons_agua;
    private double consumo_agua;
    private double costo_estandar_agua;
    private double costo_estandar_total;
    private String fecha_actu;
    private String hora_actu;
    private String nom_usu;
    private String estado_sus_serv_bas;

    
    public FP_SBasicos(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean guardarCostosServiciosBasicos(){
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SERVICIOS_BASICOS_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_servi_va());
            cmd.setDouble(4, getArea());
            cmd.setDouble(5, getTotal_consultas());
            cmd.setDouble(6, getPonderacion_cons_energia());
            cmd.setDouble(7, getBase_asig_cons_energia());
            cmd.setDouble(8, getSum_base_asig_cons_energia());
            cmd.setDouble(9, getCoefic_cons_energia());
            cmd.setDouble(10, getConsumo_energia());
            cmd.setDouble(11, getCosto_estandar_energia());
            cmd.setDouble(12, getPonderacion_cons_agua());
            cmd.setDouble(13, getBase_asig_cons_agua());
            cmd.setDouble(14, getSum_base_asig_cons_agua());
            cmd.setDouble(15, getCoefic_cons_agua());
            cmd.setDouble(16, getConsumo_agua());
            cmd.setDouble(17, getCosto_estandar_agua());
            cmd.setDouble(18, getCosto_estandar_total());
            cmd.setString(19, getNom_usu());
            
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
    
    
    public boolean eliminarServiciosBasicos(){
        boolean resp = false;
        try
        {
            String sql = "EXEC COSTOS_COSTOS_SERVICIOS_BASICOS_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
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
     * @return the cod_det_sus_serv_bas
     */
    public int getCod_det_sus_serv_bas() {
        return cod_det_sus_serv_bas;
    }

    /**
     * @param cod_det_sus_serv_bas the cod_det_sus_serv_bas to set
     */
    public void setCod_det_sus_serv_bas(int cod_det_sus_serv_bas) {
        this.cod_det_sus_serv_bas = cod_det_sus_serv_bas;
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
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the total_consultas
     */
    public double getTotal_consultas() {
        return total_consultas;
    }

    /**
     * @param total_consultas the total_consultas to set
     */
    public void setTotal_consultas(double total_consultas) {
        this.total_consultas = total_consultas;
    }

    /**
     * @return the ponderacion_cons_energia
     */
    public double getPonderacion_cons_energia() {
        return ponderacion_cons_energia;
    }

    /**
     * @param ponderacion_cons_energia the ponderacion_cons_energia to set
     */
    public void setPonderacion_cons_energia(double ponderacion_cons_energia) {
        this.ponderacion_cons_energia = ponderacion_cons_energia;
    }

    /**
     * @return the base_asig_cons_energia
     */
    public double getBase_asig_cons_energia() {
        return base_asig_cons_energia;
    }

    /**
     * @param base_asig_cons_energia the base_asig_cons_energia to set
     */
    public void setBase_asig_cons_energia(double base_asig_cons_energia) {
        this.base_asig_cons_energia = base_asig_cons_energia;
    }

    /**
     * @return the sum_base_asig_cons_energia
     */
    public double getSum_base_asig_cons_energia() {
        return sum_base_asig_cons_energia;
    }

    /**
     * @param sum_base_asig_cons_energia the sum_base_asig_cons_energia to set
     */
    public void setSum_base_asig_cons_energia(double sum_base_asig_cons_energia) {
        this.sum_base_asig_cons_energia = sum_base_asig_cons_energia;
    }

    /**
     * @return the coefic_cons_energia
     */
    public double getCoefic_cons_energia() {
        return coefic_cons_energia;
    }

    /**
     * @param coefic_cons_energia the coefic_cons_energia to set
     */
    public void setCoefic_cons_energia(double coefic_cons_energia) {
        this.coefic_cons_energia = coefic_cons_energia;
    }

    /**
     * @return the consumo_energia
     */
    public double getConsumo_energia() {
        return consumo_energia;
    }

    /**
     * @param consumo_energia the consumo_energia to set
     */
    public void setConsumo_energia(double consumo_energia) {
        this.consumo_energia = consumo_energia;
    }

    /**
     * @return the costo_estandar_energia
     */
    public double getCosto_estandar_energia() {
        return costo_estandar_energia;
    }

    /**
     * @param costo_estandar_energia the costo_estandar_energia to set
     */
    public void setCosto_estandar_energia(double costo_estandar_energia) {
        this.costo_estandar_energia = costo_estandar_energia;
    }

    /**
     * @return the ponderacion_cons_agua
     */
    public double getPonderacion_cons_agua() {
        return ponderacion_cons_agua;
    }

    /**
     * @param ponderacion_cons_agua the ponderacion_cons_agua to set
     */
    public void setPonderacion_cons_agua(double ponderacion_cons_agua) {
        this.ponderacion_cons_agua = ponderacion_cons_agua;
    }

    /**
     * @return the base_asig_cons_agua
     */
    public double getBase_asig_cons_agua() {
        return base_asig_cons_agua;
    }

    /**
     * @param base_asig_cons_agua the base_asig_cons_agua to set
     */
    public void setBase_asig_cons_agua(double base_asig_cons_agua) {
        this.base_asig_cons_agua = base_asig_cons_agua;
    }

    /**
     * @return the sum_base_asig_cons_agua
     */
    public double getSum_base_asig_cons_agua() {
        return sum_base_asig_cons_agua;
    }

    /**
     * @param sum_base_asig_cons_agua the sum_base_asig_cons_agua to set
     */
    public void setSum_base_asig_cons_agua(double sum_base_asig_cons_agua) {
        this.sum_base_asig_cons_agua = sum_base_asig_cons_agua;
    }

    /**
     * @return the coefic_cons_agua
     */
    public double getCoefic_cons_agua() {
        return coefic_cons_agua;
    }

    /**
     * @param coefic_cons_agua the coefic_cons_agua to set
     */
    public void setCoefic_cons_agua(double coefic_cons_agua) {
        this.coefic_cons_agua = coefic_cons_agua;
    }

    /**
     * @return the consumo_agua
     */
    public double getConsumo_agua() {
        return consumo_agua;
    }

    /**
     * @param consumo_agua the consumo_agua to set
     */
    public void setConsumo_agua(double consumo_agua) {
        this.consumo_agua = consumo_agua;
    }

    /**
     * @return the costo_estandar_agua
     */
    public double getCosto_estandar_agua() {
        return costo_estandar_agua;
    }

    /**
     * @param costo_estandar_agua the costo_estandar_agua to set
     */
    public void setCosto_estandar_agua(double costo_estandar_agua) {
        this.costo_estandar_agua = costo_estandar_agua;
    }

    /**
     * @return the costo_estandar_total
     */
    public double getCosto_estandar_total() {
        return costo_estandar_total;
    }

    /**
     * @param costo_estandar_total the costo_estandar_total to set
     */
    public void setCosto_estandar_total(double costo_estandar_total) {
        this.costo_estandar_total = costo_estandar_total;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
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
     * @return the estado_sus_serv_bas
     */
    public String getEstado_sus_serv_bas() {
        return estado_sus_serv_bas;
    }

    /**
     * @param estado_sus_serv_bas the estado_sus_serv_bas to set
     */
    public void setEstado_sus_serv_bas(String estado_sus_serv_bas) {
        this.estado_sus_serv_bas = estado_sus_serv_bas;
    }
    
    

    
}
