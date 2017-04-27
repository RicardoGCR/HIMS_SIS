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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_GA")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalGa.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalGa c")})
public class ConsultorioExtCarnetPerinatalGa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GA_ID")
    private Long gaId;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    @Column(name = "GA_INTERGENESICO")
    private String gaIntergenesico;
    @Column(name = "GA_TERMINACION")
    private String gaTerminacion;
    @Column(name = "GA_TIPO_ABORTO")
    private String gaTipoAborto;
    @Column(name = "GA_LACTANCIA_MAT")
    private String gaLactanciaMat;
    @Column(name = "GA_LUGAR_PARTO")
    private String gaLugarParto;
    @Column(name = "GA_FECHA_GESTACION")
    private String gaFechaGestacion;
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

    public ConsultorioExtCarnetPerinatalGa() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalGa(Long gaId) {
        this.gaId = gaId;
    }

    public Long getGaId() {
        return gaId;
    }

    public void setGaId(Long gaId) {
        this.gaId = gaId;
    }

    public String getGaIntergenesico() {
        return gaIntergenesico;
    }

    public void setGaIntergenesico(String gaIntergenesico) {
        this.gaIntergenesico = gaIntergenesico;
    }

    public String getGaTerminacion() {
        return gaTerminacion;
    }

    public void setGaTerminacion(String gaTerminacion) {
        this.gaTerminacion = gaTerminacion;
    }

    public String getGaTipoAborto() {
        return gaTipoAborto;
    }

    public void setGaTipoAborto(String gaTipoAborto) {
        this.gaTipoAborto = gaTipoAborto;
    }

    public String getGaLactanciaMat() {
        return gaLactanciaMat;
    }

    public void setGaLactanciaMat(String gaLactanciaMat) {
        this.gaLactanciaMat = gaLactanciaMat;
    }

    public String getGaLugarParto() {
        return gaLugarParto;
    }

    public void setGaLugarParto(String gaLugarParto) {
        this.gaLugarParto = gaLugarParto;
    }

    public String getGaFechaGestacion() {
        return gaFechaGestacion;
    }

    public void setGaFechaGestacion(String gaFechaGestacion) {
        this.gaFechaGestacion = gaFechaGestacion;
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
    public int hashCode() {
        int hash = 0;
        hash += (gaId != null ? gaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtCarnetPerinatalGa)) {
            return false;
        }
        ConsultorioExtCarnetPerinatalGa other = (ConsultorioExtCarnetPerinatalGa) object;
        if ((this.gaId == null && other.gaId != null) || (this.gaId != null && !this.gaId.equals(other.gaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalGa[ gaId=" + gaId + " ]";
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
