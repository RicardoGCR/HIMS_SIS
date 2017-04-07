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
@Table(name = "CONSULTORIO_EXT_RS_SEGUIMIENTO_DESARROLLO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsSeguimientoDesarrollo.findAll", query = "SELECT c FROM ConsultorioExtRsSeguimientoDesarrollo c")})
public class ConsultorioExtRsSeguimientoDesarrollo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SD_ID")
    private Long sdId;
    @Column(name = "SD_FECHA")
    private String sdFecha;
    @Column(name = "SD_EDAD")
    private String sdEdad;
    @Column(name = "SD_RES")
    private String sdRes;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;
    @Column(name = "NOM_PC")
    private String nomPc;

    public ConsultorioExtRsSeguimientoDesarrollo() {
    }

    public ConsultorioExtRsSeguimientoDesarrollo(Long sdId) {
        this.sdId = sdId;
    }

    public Long getSdId() {
        return sdId;
    }

    public void setSdId(Long sdId) {
        this.sdId = sdId;
    }

    public String getSdFecha() {
        return sdFecha;
    }

    public void setSdFecha(String sdFecha) {
        this.sdFecha = sdFecha;
    }

    public String getSdEdad() {
        return sdEdad;
    }

    public void setSdEdad(String sdEdad) {
        this.sdEdad = sdEdad;
    }

    public String getSdRes() {
        return sdRes;
    }

    public void setSdRes(String sdRes) {
        this.sdRes = sdRes;
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
        hash += (sdId != null ? sdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSeguimientoDesarrollo)) {
            return false;
        }
        ConsultorioExtRsSeguimientoDesarrollo other = (ConsultorioExtRsSeguimientoDesarrollo) object;
        if ((this.sdId == null && other.sdId != null) || (this.sdId != null && !this.sdId.equals(other.sdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguimientoDesarrollo[ sdId=" + sdId + " ]";
    }
    
}
