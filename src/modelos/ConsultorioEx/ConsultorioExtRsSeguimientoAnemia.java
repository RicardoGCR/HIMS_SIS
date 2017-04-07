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
@Table(name = "CONSULTORIO_EXT_RS_SEGUIMIENTO_ANEMIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsSeguimientoAnemia.findAll", query = "SELECT c FROM ConsultorioExtRsSeguimientoAnemia c")})
public class ConsultorioExtRsSeguimientoAnemia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SA_ID")
    private Long saId;
    @Column(name = "SA_FECHA")
    private String saFecha;
    @Column(name = "SA_EDAD")
    private String saEdad;
    @Column(name = "SA_RES")
    private String saRes;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;
    @Column(name = "NOM_PC")
    private String nomPc;

    public ConsultorioExtRsSeguimientoAnemia() {
    }

    public ConsultorioExtRsSeguimientoAnemia(Long saId) {
        this.saId = saId;
    }

    public Long getSaId() {
        return saId;
    }

    public void setSaId(Long saId) {
        this.saId = saId;
    }

    public String getSaFecha() {
        return saFecha;
    }

    public void setSaFecha(String saFecha) {
        this.saFecha = saFecha;
    }

    public String getSaEdad() {
        return saEdad;
    }

    public void setSaEdad(String saEdad) {
        this.saEdad = saEdad;
    }

    public String getSaRes() {
        return saRes;
    }

    public void setSaRes(String saRes) {
        this.saRes = saRes;
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
        hash += (saId != null ? saId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSeguimientoAnemia)) {
            return false;
        }
        ConsultorioExtRsSeguimientoAnemia other = (ConsultorioExtRsSeguimientoAnemia) object;
        if ((this.saId == null && other.saId != null) || (this.saId != null && !this.saId.equals(other.saId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguimientoAnemia[ saId=" + saId + " ]";
    }
    
}
