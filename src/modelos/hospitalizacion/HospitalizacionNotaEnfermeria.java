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
public class HospitalizacionNotaEnfermeria {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private String nota_enfermeria;
    private String indicaciones;
    private int preventa;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;

    public HospitalizacionNotaEnfermeria()
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nota_enfermeria
     */
    public String getNota_enfermeria() {
        return nota_enfermeria;
    }

    /**
     * @param nota_enfermeria the nota_enfermeria to set
     */
    public void setNota_enfermeria(String nota_enfermeria) {
        this.nota_enfermeria = nota_enfermeria;
    }

    /**
     * @return the indicaciones
     */
    public String getIndicaciones() {
        return indicaciones;
    }

    /**
     * @param indicaciones the indicaciones to set
     */
    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    /**
     * @return the preventa
     */
    public int getPreventa() {
        return preventa;
    }

    /**
     * @param preventa the preventa to set
     */
    public void setPreventa(int preventa) {
        this.preventa = preventa;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
