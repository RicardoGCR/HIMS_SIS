/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.Conexion;


public class ConsultorioExtRsCabecera implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private static final long serialVersionUID = 1L;
    private Long rsId;
    private String rsTipoRiesgo;
    private String rsPadre;
    private String rsAfilSis;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String usuario;
    private Character estado;
    private Collection<ConsultorioExtRsCcd> consultorioExtRsCcdCollection;

    private Collection<ConsultorioExtRsVacunas> consultorioExtRsVacunasCollection;

    public ConsultorioExtRsCabecera()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsCabecera(Long rsId) {
        this.rsId = rsId;
    }

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public String getRsTipoRiesgo() {
        return rsTipoRiesgo;
    }

    public void setRsTipoRiesgo(String rsTipoRiesgo) {
        this.rsTipoRiesgo = rsTipoRiesgo;
    }

    public String getRsPadre() {
        return rsPadre;
    }

    public void setRsPadre(String rsPadre) {
        this.rsPadre = rsPadre;
    }

    public String getRsAfilSis() {
        return rsAfilSis;
    }

    public void setRsAfilSis(String rsAfilSis) {
        this.rsAfilSis = rsAfilSis;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<ConsultorioExtRsCcd> getConsultorioExtRsCcdCollection() {
        return consultorioExtRsCcdCollection;
    }

    public void setConsultorioExtRsCcdCollection(Collection<ConsultorioExtRsCcd> consultorioExtRsCcdCollection) {
        this.consultorioExtRsCcdCollection = consultorioExtRsCcdCollection;
    }

    @XmlTransient
    public Collection<ConsultorioExtRsVacunas> getConsultorioExtRsVacunasCollection() {
        return consultorioExtRsVacunasCollection;
    }

    public void setConsultorioExtRsVacunasCollection(Collection<ConsultorioExtRsVacunas> consultorioExtRsVacunasCollection) {
        this.consultorioExtRsVacunasCollection = consultorioExtRsVacunasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rsId != null ? rsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsCabecera)) {
            return false;
        }
        ConsultorioExtRsCabecera other = (ConsultorioExtRsCabecera) object;
        if ((this.rsId == null && other.rsId != null) || (this.rsId != null && !this.rsId.equals(other.rsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsCabecera[ rsId=" + rsId + " ]";
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
    
}
