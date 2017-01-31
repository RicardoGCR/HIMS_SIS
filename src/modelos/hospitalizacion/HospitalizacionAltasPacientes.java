/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionAltasPacientes {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String ap_id;
    private String ac_id;
    private String da_id;
    private String ma_id;
    private String cod_per;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String cod_usu;

    
    
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
     * @return the ap_id
     */
    public String getAp_id() {
        return ap_id;
    }

    /**
     * @param ap_id the ap_id to set
     */
    public void setAp_id(String ap_id) {
        this.ap_id = ap_id;
    }

    /**
     * @return the ac_id
     */
    public String getAc_id() {
        return ac_id;
    }

    /**
     * @param ac_id the ac_id to set
     */
    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    /**
     * @return the da_id
     */
    public String getDa_id() {
        return da_id;
    }

    /**
     * @param da_id the da_id to set
     */
    public void setDa_id(String da_id) {
        this.da_id = da_id;
    }

    /**
     * @return the ma_id
     */
    public String getMa_id() {
        return ma_id;
    }

    /**
     * @param ma_id the ma_id to set
     */
    public void setMa_id(String ma_id) {
        this.ma_id = ma_id;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
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
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }
}
