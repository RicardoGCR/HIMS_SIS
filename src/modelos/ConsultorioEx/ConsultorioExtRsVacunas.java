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
@Table(name = "CONSULTORIO_EXT_RS_VACUNAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsVacunas.findAll", query = "SELECT c FROM ConsultorioExtRsVacunas c"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByVacId", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.vacId = :vacId"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByVacDesc", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.vacDesc = :vacDesc"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByVacFecha", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.vacFecha = :vacFecha"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByVacFua", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.vacFua = :vacFua"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByNomPc", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.nomPc = :nomPc"),
    @NamedQuery(name = "ConsultorioExtRsVacunas.findByUsuario", query = "SELECT c FROM ConsultorioExtRsVacunas c WHERE c.usuario = :usuario")})
public class ConsultorioExtRsVacunas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VAC_ID")
    private Long vacId;
    @Column(name = "VAC_DESC")
    private String vacDesc;
    @Column(name = "VAC_FECHA")
    private String vacFecha;
    @Column(name = "VAC_FUA")
    private String vacFua;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "USUARIO")
    private String usuario;
    @JoinColumn(name = "RS_ID", referencedColumnName = "RS_ID")
    @ManyToOne
    private ConsultorioExtRsCabecera rsId;

    public ConsultorioExtRsVacunas() {
    }

    public ConsultorioExtRsVacunas(Long vacId) {
        this.vacId = vacId;
    }

    public Long getVacId() {
        return vacId;
    }

    public void setVacId(Long vacId) {
        this.vacId = vacId;
    }

    public String getVacDesc() {
        return vacDesc;
    }

    public void setVacDesc(String vacDesc) {
        this.vacDesc = vacDesc;
    }

    public String getVacFecha() {
        return vacFecha;
    }

    public void setVacFecha(String vacFecha) {
        this.vacFecha = vacFecha;
    }

    public String getVacFua() {
        return vacFua;
    }

    public void setVacFua(String vacFua) {
        this.vacFua = vacFua;
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

    public ConsultorioExtRsCabecera getRsId() {
        return rsId;
    }

    public void setRsId(ConsultorioExtRsCabecera rsId) {
        this.rsId = rsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacId != null ? vacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsVacunas)) {
            return false;
        }
        ConsultorioExtRsVacunas other = (ConsultorioExtRsVacunas) object;
        if ((this.vacId == null && other.vacId != null) || (this.vacId != null && !this.vacId.equals(other.vacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsVacunas[ vacId=" + vacId + " ]";
    }
    
}
