/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_RS_CCD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsCcd.findAll", query = "SELECT c FROM ConsultorioExtRsCcd c"),
    @NamedQuery(name = "ConsultorioExtRsCcd.findByRsCcd", query = "SELECT c FROM ConsultorioExtRsCcd c WHERE c.rsCcd = :rsCcd"),
    @NamedQuery(name = "ConsultorioExtRsCcd.findByFecha", query = "SELECT c FROM ConsultorioExtRsCcd c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "ConsultorioExtRsCcd.findByFua", query = "SELECT c FROM ConsultorioExtRsCcd c WHERE c.fua = :fua"),
    @NamedQuery(name = "ConsultorioExtRsCcd.findByEstado", query = "SELECT c FROM ConsultorioExtRsCcd c WHERE c.estado = :estado")})
public class ConsultorioExtRsCcd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RS_CCD")
    private Long rsCcd;
    @Column(name = "Fecha")
    private String fecha;
    @Column(name = "Fua")
    private String fua;
    @Column(name = "Estado")
    private Character estado;
    @JoinColumn(name = "RS_ID", referencedColumnName = "RS_ID")
    @ManyToOne
    private ConsultorioExtRsCabecera rsId;

    public ConsultorioExtRsCcd() {
    }

    public ConsultorioExtRsCcd(Long rsCcd) {
        this.rsCcd = rsCcd;
    }

    public Long getRsCcd() {
        return rsCcd;
    }

    public void setRsCcd(Long rsCcd) {
        this.rsCcd = rsCcd;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFua() {
        return fua;
    }

    public void setFua(String fua) {
        this.fua = fua;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public ConsultorioExtRsCabecera getRsId() {
        return rsId;
    }

    public void setRsId(ConsultorioExtRsCabecera rsId) {
        this.rsId = rsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rsCcd != null ? rsCcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsCcd)) {
            return false;
        }
        ConsultorioExtRsCcd other = (ConsultorioExtRsCcd) object;
        if ((this.rsCcd == null && other.rsCcd != null) || (this.rsCcd != null && !this.rsCcd.equals(other.rsCcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsCcd[ rsCcd=" + rsCcd + " ]";
    }
    
}
