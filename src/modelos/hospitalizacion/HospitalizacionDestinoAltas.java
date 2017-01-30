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
public class HospitalizacionDestinoAltas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int da_id;
    private String da_descripcion;
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
     * @return the da_id
     */
    public int getDa_id() {
        return da_id;
    }

    /**
     * @param da_id the da_id to set
     */
    public void setDa_id(int da_id) {
        this.da_id = da_id;
    }

    /**
     * @return the da_descripcion
     */
    public String getDa_descripcion() {
        return da_descripcion;
    }

    /**
     * @param da_descripcion the da_descripcion to set
     */
    public void setDa_descripcion(String da_descripcion) {
        this.da_descripcion = da_descripcion;
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
