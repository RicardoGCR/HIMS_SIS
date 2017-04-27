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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_VP")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalVp.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalVp c")})
public class ConsultorioExtCarnetPerinatalVp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VP_ID")
    private Long vpId;
    @Column(name = "VP_RUBEOLA")
    private Character vpRubeola;
    @Column(name = "VP_HEPATITIS")
    private Character vpHepatitis;
    @Column(name = "VP_PAPILOMA")
    private Character vpPapiloma;
    @Column(name = "VP_FIEBRE")
    private Character vpFiebre;
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

    public ConsultorioExtCarnetPerinatalVp() {
    }

    public ConsultorioExtCarnetPerinatalVp(Long vpId) {
        this.vpId = vpId;
    }

    public Long getVpId() {
        return vpId;
    }

    public void setVpId(Long vpId) {
        this.vpId = vpId;
    }

    public Character getVpRubeola() {
        return vpRubeola;
    }

    public void setVpRubeola(Character vpRubeola) {
        this.vpRubeola = vpRubeola;
    }

    public Character getVpHepatitis() {
        return vpHepatitis;
    }

    public void setVpHepatitis(Character vpHepatitis) {
        this.vpHepatitis = vpHepatitis;
    }

    public Character getVpPapiloma() {
        return vpPapiloma;
    }

    public void setVpPapiloma(Character vpPapiloma) {
        this.vpPapiloma = vpPapiloma;
    }

    public Character getVpFiebre() {
        return vpFiebre;
    }

    public void setVpFiebre(Character vpFiebre) {
        this.vpFiebre = vpFiebre;
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
        hash += (vpId != null ? vpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtCarnetPerinatalVp)) {
            return false;
        }
        ConsultorioExtCarnetPerinatalVp other = (ConsultorioExtCarnetPerinatalVp) object;
        if ((this.vpId == null && other.vpId != null) || (this.vpId != null && !this.vpId.equals(other.vpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVp[ vpId=" + vpId + " ]";
    }
    
}
