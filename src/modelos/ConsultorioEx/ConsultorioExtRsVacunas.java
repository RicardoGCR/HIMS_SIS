/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

public class ConsultorioExtRsVacunas implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long vacId;
    private String vacDesc;
    private String vacFecha;
    private String vacFua;
    private String nomPc;
    private String usuario;
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
