/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_EM")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalEm.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalEm c")})
public class ConsultorioExtCarnetPerinatalEm implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EM_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int emId;
    @Column(name = "EM_FECHA")
    private String emFecha;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;

    public ConsultorioExtCarnetPerinatalEm() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEm(int emId) {
        this.emId = emId;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public String getEmFecha() {
        return emFecha;
    }

    public void setEmFecha(String emFecha) {
        this.emFecha = emFecha;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEm[ emId=" + emId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }
    
}
