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

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_AN")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalAn.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalAn c")})
public class ConsultorioExtCarnetPerinatalAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AN_ID")
    private Long anId;
    @Column(name = "AN_DOSIS_PREVIA")
    private String anDosisPrevia;
    @Column(name = "AN_1RA_DOSIS")
    private String an1raDosis;
    @Column(name = "AN_1RA_D_APLICACION")
    private String an1raDAplicacion;
    @Column(name = "AN_2RA_DOSIS")
    private String an2raDosis;
    @Column(name = "AN_2RA_D_APLICACION")
    private String an2raDAplicacion;
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

    public ConsultorioExtCarnetPerinatalAn() {
    }

    public ConsultorioExtCarnetPerinatalAn(Long anId) {
        this.anId = anId;
    }

    public Long getAnId() {
        return anId;
    }

    public void setAnId(Long anId) {
        this.anId = anId;
    }

    public String getAnDosisPrevia() {
        return anDosisPrevia;
    }

    public void setAnDosisPrevia(String anDosisPrevia) {
        this.anDosisPrevia = anDosisPrevia;
    }

    public String getAn1raDosis() {
        return an1raDosis;
    }

    public void setAn1raDosis(String an1raDosis) {
        this.an1raDosis = an1raDosis;
    }

    public String getAn1raDAplicacion() {
        return an1raDAplicacion;
    }

    public void setAn1raDAplicacion(String an1raDAplicacion) {
        this.an1raDAplicacion = an1raDAplicacion;
    }

    public String getAn2raDosis() {
        return an2raDosis;
    }

    public void setAn2raDosis(String an2raDosis) {
        this.an2raDosis = an2raDosis;
    }

    public String getAn2raDAplicacion() {
        return an2raDAplicacion;
    }

    public void setAn2raDAplicacion(String an2raDAplicacion) {
        this.an2raDAplicacion = an2raDAplicacion;
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
        hash += (anId != null ? anId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtCarnetPerinatalAn)) {
            return false;
        }
        ConsultorioExtCarnetPerinatalAn other = (ConsultorioExtCarnetPerinatalAn) object;
        if ((this.anId == null && other.anId != null) || (this.anId != null && !this.anId.equals(other.anId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAn[ anId=" + anId + " ]";
    }
    
}
