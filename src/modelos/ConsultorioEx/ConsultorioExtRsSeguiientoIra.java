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
@Table(name = "CONSULTORIO_EXT_RS_SEGUIIENTO_IRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsSeguiientoIra.findAll", query = "SELECT c FROM ConsultorioExtRsSeguiientoIra c")})
public class ConsultorioExtRsSeguiientoIra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SI_ID")
    private Long siId;
    @Column(name = "SI_FECHA")
    private String siFecha;
    @Column(name = "SI_EDAD")
    private String siEdad;
    @Column(name = "SI_RES")
    private String siRes;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;
    @Column(name = "NOM_PC")
    private String nomPc;

    public ConsultorioExtRsSeguiientoIra() {
    }

    public ConsultorioExtRsSeguiientoIra(Long siId) {
        this.siId = siId;
    }

    public Long getSiId() {
        return siId;
    }

    public void setSiId(Long siId) {
        this.siId = siId;
    }

    public String getSiFecha() {
        return siFecha;
    }

    public void setSiFecha(String siFecha) {
        this.siFecha = siFecha;
    }

    public String getSiEdad() {
        return siEdad;
    }

    public void setSiEdad(String siEdad) {
        this.siEdad = siEdad;
    }

    public String getSiRes() {
        return siRes;
    }

    public void setSiRes(String siRes) {
        this.siRes = siRes;
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
        hash += (siId != null ? siId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSeguiientoIra)) {
            return false;
        }
        ConsultorioExtRsSeguiientoIra other = (ConsultorioExtRsSeguiientoIra) object;
        if ((this.siId == null && other.siId != null) || (this.siId != null && !this.siId.equals(other.siId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguiientoIra[ siId=" + siId + " ]";
    }
    
}
