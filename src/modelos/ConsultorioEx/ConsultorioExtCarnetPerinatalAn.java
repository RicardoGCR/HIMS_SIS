/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;

public class ConsultorioExtCarnetPerinatalAn implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long anId;
    private String anDosisPrevia;
    private String an1raDosis;
    private String an1raDAplicacion;
    private String an2raDosis;
    private String an2raDAplicacion;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
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
