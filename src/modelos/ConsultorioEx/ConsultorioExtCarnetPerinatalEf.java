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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_EF")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalEf.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalEf c")})
public class ConsultorioExtCarnetPerinatalEf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EF_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int efId;
    @Column(name = "EF_EC")
    private String efEc;
    @Column(name = "EF_MAMAS")
    private String efMamas;
    @Column(name = "EF_CUE_UTE")
    private String efCueUte;
    @Column(name = "EF_PELVIS")
    private String efPelvis;
    @Column(name = "EF_ODONT")
    private String efOdont;
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

    public ConsultorioExtCarnetPerinatalEf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEf(int efId) {
        this.efId = efId;
    }

    public int getEfId() {
        return efId;
    }

    public void setEfId(int efId) {
        this.efId = efId;
    }

    public String getEfEc() {
        return efEc;
    }

    public void setEfEc(String efEc) {
        this.efEc = efEc;
    }

    public String getEfMamas() {
        return efMamas;
    }

    public void setEfMamas(String efMamas) {
        this.efMamas = efMamas;
    }

    public String getEfCueUte() {
        return efCueUte;
    }

    public void setEfCueUte(String efCueUte) {
        this.efCueUte = efCueUte;
    }

    public String getEfPelvis() {
        return efPelvis;
    }

    public void setEfPelvis(String efPelvis) {
        this.efPelvis = efPelvis;
    }

    public String getEfOdont() {
        return efOdont;
    }

    public void setEfOdont(String efOdont) {
        this.efOdont = efOdont;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEf[ efId=" + efId + " ]";
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
