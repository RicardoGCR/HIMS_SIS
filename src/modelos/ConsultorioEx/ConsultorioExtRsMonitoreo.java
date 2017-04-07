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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_RS_MONITOREO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsMonitoreo.findAll", query = "SELECT c FROM ConsultorioExtRsMonitoreo c")})
public class ConsultorioExtRsMonitoreo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MO_ID")
    private Long moId;
    @Column(name = "MO_FECHA")
    private String moFecha;
    @Column(name = "MO_EDAD")
    private String moEdad;
    @Column(name = "MO_PESO")
    private String moPeso;
    @Column(name = "MO_TALLA")
    private String moTalla;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;
    @Column(name = "NOM_PC")
    private String nomPc;

    public ConsultorioExtRsMonitoreo() {
    }

    public ConsultorioExtRsMonitoreo(Long moId) {
        this.moId = moId;
    }

    public Long getMoId() {
        return moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    public String getMoFecha() {
        return moFecha;
    }

    public void setMoFecha(String moFecha) {
        this.moFecha = moFecha;
    }

    public String getMoEdad() {
        return moEdad;
    }

    public void setMoEdad(String moEdad) {
        this.moEdad = moEdad;
    }

    public String getMoPeso() {
        return moPeso;
    }

    public void setMoPeso(String moPeso) {
        this.moPeso = moPeso;
    }

    public String getMoTalla() {
        return moTalla;
    }

    public void setMoTalla(String moTalla) {
        this.moTalla = moTalla;
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

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moId != null ? moId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsMonitoreo)) {
            return false;
        }
        ConsultorioExtRsMonitoreo other = (ConsultorioExtRsMonitoreo) object;
        if ((this.moId == null && other.moId != null) || (this.moId != null && !this.moId.equals(other.moId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsMonitoreo[ moId=" + moId + " ]";
    }
    
}
