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
public class FP_Infraestructura {
    
private Connection cn;
private int cod_det_sus_infr; 
private String cod_sustento_costo;
private String cod_tipo_susten;
private String cod_servi_va ;
private int tiempo_vida_util;
private double area_total;
private double costo_total;
private double requerimiento_area;
private double valor_unitario_dep;
private double costo_construccion;
private double dep_infra_min;
private int tiempo_hora_proc;
private int tiempo_min_proc;
private double costo_estandar;
private String nom_usu;

 public FP_Infraestructura(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
  public boolean guardarFP_Infraestructura()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_INFRAESTRUCTURA_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_servi_va());
            cmd.setInt(4, getTiempo_vida_util());
            cmd.setDouble(5, getArea_total());
            cmd.setDouble(6, getCosto_total());
            cmd.setDouble(7, getRequerimiento_area());
            cmd.setDouble(8, getValor_unitario_dep());
            cmd.setDouble(9, getCosto_construccion());
            cmd.setDouble(10, getDep_infra_min());
            cmd.setInt(11, getTiempo_hora_proc());
            cmd.setInt(12, getTiempo_min_proc());
            cmd.setDouble(13, getCosto_estandar());
            cmd.setString(14, getNom_usu());
            
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
         

    public boolean eliminarFP_Infraestructura()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_DET_INFRAESTRUCTURA_eliminar ?";
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
     * @return the cod_det_sus_infr
     */
    public int getCod_det_sus_infr() {
        return cod_det_sus_infr;
    }

    /**
     * @param cod_det_sus_infr the cod_det_sus_infr to set
     */
    public void setCod_det_sus_infr(int cod_det_sus_infr) {
        this.cod_det_sus_infr = cod_det_sus_infr;
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
     * @return the tiempo_vida_util
     */
    public int getTiempo_vida_util() {
        return tiempo_vida_util;
    }

    /**
     * @param tiempo_vida_util the tiempo_vida_util to set
     */
    public void setTiempo_vida_util(int tiempo_vida_util) {
        this.tiempo_vida_util = tiempo_vida_util;
    }

    /**
     * @return the area_total
     */
    public double getArea_total() {
        return area_total;
    }

    /**
     * @param area_total the area_total to set
     */
    public void setArea_total(double area_total) {
        this.area_total = area_total;
    }

    /**
     * @return the costo_total
     */
    public double getCosto_total() {
        return costo_total;
    }

    /**
     * @param costo_total the costo_total to set
     */
    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    /**
     * @return the requerimiento_area
     */
    public double getRequerimiento_area() {
        return requerimiento_area;
    }

    /**
     * @param requerimiento_area the requerimiento_area to set
     */
    public void setRequerimiento_area(double requerimiento_area) {
        this.requerimiento_area = requerimiento_area;
    }

    /**
     * @return the valor_unitario_dep
     */
    public double getValor_unitario_dep() {
        return valor_unitario_dep;
    }

    /**
     * @param valor_unitario_dep the valor_unitario_dep to set
     */
    public void setValor_unitario_dep(double valor_unitario_dep) {
        this.valor_unitario_dep = valor_unitario_dep;
    }

    /**
     * @return the costo_construccion
     */
    public double getCosto_construccion() {
        return costo_construccion;
    }

    /**
     * @param costo_construccion the costo_construccion to set
     */
    public void setCosto_construccion(double costo_construccion) {
        this.costo_construccion = costo_construccion;
    }

    /**
     * @return the dep_infra_min
     */
    public double getDep_infra_min() {
        return dep_infra_min;
    }

    /**
     * @param dep_infra_min the dep_infra_min to set
     */
    public void setDep_infra_min(double dep_infra_min) {
        this.dep_infra_min = dep_infra_min;
    }

    /**
     * @return the tiempo_hora_proc
     */
    public int getTiempo_hora_proc() {
        return tiempo_hora_proc;
    }

    /**
     * @param tiempo_hora_proc the tiempo_hora_proc to set
     */
    public void setTiempo_hora_proc(int tiempo_hora_proc) {
        this.tiempo_hora_proc = tiempo_hora_proc;
    }

    /**
     * @return the tiempo_min_proc
     */
    public int getTiempo_min_proc() {
        return tiempo_min_proc;
    }

    /**
     * @param tiempo_min_proc the tiempo_min_proc to set
     */
    public void setTiempo_min_proc(int tiempo_min_proc) {
        this.tiempo_min_proc = tiempo_min_proc;
    }

    /**
     * @return the costo_estandar
     */
    public double getCosto_estandar() {
        return costo_estandar;
    }

    /**
     * @param costo_estandar the costo_estandar to set
     */
    public void setCosto_estandar(double costo_estandar) {
        this.costo_estandar = costo_estandar;
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
