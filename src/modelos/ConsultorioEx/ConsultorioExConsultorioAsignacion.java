/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class ConsultorioExConsultorioAsignacion {
   DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private int consultorio_id;
    private String nro_cita;
    private String turno;
    private int cod_rol;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String usuario;
    
    public ConsultorioExConsultorioAsignacion()
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
     * @return the consultorio_id
     */
    public int getConsultorio_id() {
        return consultorio_id;
    }

    /**
     * @param consultorio_id the consultorio_id to set
     */
    public void setConsultorio_id(int consultorio_id) {
        this.consultorio_id = consultorio_id;
    }

    /**
     * @return the nro_cita
     */
    public String getNro_cita() {
        return nro_cita;
    }

    /**
     * @param nro_cita the nro_cita to set
     */
    public void setNro_cita(String nro_cita) {
        this.nro_cita = nro_cita;
    }

    /**
     * @return the turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * @return the cod_rol
     */
    public int getCod_rol() {
        return cod_rol;
    }

    /**
     * @param cod_rol the cod_rol to set
     */
    public void setCod_rol(int cod_rol) {
        this.cod_rol = cod_rol;
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
