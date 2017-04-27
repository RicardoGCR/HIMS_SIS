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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_PT")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalPt.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalPt c")})
public class ConsultorioExtCarnetPerinatalPt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PT_ID")
    private Long ptId;
    @Column(name = "PT_PESO")
    private String ptPeso;
    @Column(name = "PT_TALLA")
    private String ptTalla;
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

    public ConsultorioExtCarnetPerinatalPt() {
    }

    public ConsultorioExtCarnetPerinatalPt(Long ptId) {
        this.ptId = ptId;
    }

    public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public String getPtPeso() {
        return ptPeso;
    }

    public void setPtPeso(String ptPeso) {
        this.ptPeso = ptPeso;
    }

    public String getPtTalla() {
        return ptTalla;
    }

    public void setPtTalla(String ptTalla) {
        this.ptTalla = ptTalla;
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
        hash += (ptId != null ? ptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtCarnetPerinatalPt)) {
            return false;
        }
        ConsultorioExtCarnetPerinatalPt other = (ConsultorioExtCarnetPerinatalPt) object;
        if ((this.ptId == null && other.ptId != null) || (this.ptId != null && !this.ptId.equals(other.ptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPt[ ptId=" + ptId + " ]";
    }
    
}
