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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_FU")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalFu.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalFu c")})
public class ConsultorioExtCarnetPerinatalFu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FU_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int fuId;
    @Column(name = "FU_FECHA_ULT_MENS")
    private String fuFechaUltMens;
    @Column(name = "FU_DUDA_FECHA")
    private String fuDudaFecha;
    @Column(name = "FU_ECO")
    private String fuEco;
    @Column(name = "FU_FECHA_ECO")
    private String fuFechaEco;
    @Column(name = "FU_FECHA_P_PARTO")
    private String fuFechaPParto;
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

    public ConsultorioExtCarnetPerinatalFu() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalFu(int fuId) {
        this.fuId = fuId;
    }

    public int getFuId() {
        return fuId;
    }

    public void setFuId(int fuId) {
        this.fuId = fuId;
    }

    public String getFuFechaUltMens() {
        return fuFechaUltMens;
    }

    public void setFuFechaUltMens(String fuFechaUltMens) {
        this.fuFechaUltMens = fuFechaUltMens;
    }

    public String getFuDudaFecha() {
        return fuDudaFecha;
    }

    public void setFuDudaFecha(String fuDudaFecha) {
        this.fuDudaFecha = fuDudaFecha;
    }

    public String getFuEco() {
        return fuEco;
    }

    public void setFuEco(String fuEco) {
        this.fuEco = fuEco;
    }

    public String getFuFechaEco() {
        return fuFechaEco;
    }

    public void setFuFechaEco(String fuFechaEco) {
        this.fuFechaEco = fuFechaEco;
    }

    public String getFuFechaPParto() {
        return fuFechaPParto;
    }

    public void setFuFechaPParto(String fuFechaPParto) {
        this.fuFechaPParto = fuFechaPParto;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFu[ fuId=" + fuId + " ]";
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
