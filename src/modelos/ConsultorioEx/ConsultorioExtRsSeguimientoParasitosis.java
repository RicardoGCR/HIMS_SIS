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
@Table(name = "CONSULTORIO_EXT_RS_SEGUIMIENTO_PARASITOSIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsSeguimientoParasitosis.findAll", query = "SELECT c FROM ConsultorioExtRsSeguimientoParasitosis c")})
public class ConsultorioExtRsSeguimientoParasitosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SP_ID")
    private Long spId;
    @Column(name = "SP_FECHA")
    private String spFecha;
    @Column(name = "SP_EDAD")
    private String spEdad;
    @Column(name = "SP_RECOMENDACIONES")
    private String spRecomendaciones;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
    private String codUsu;
    @Column(name = "NOM_PC")
    private String nomPc;

    public ConsultorioExtRsSeguimientoParasitosis() {
    }

    public ConsultorioExtRsSeguimientoParasitosis(Long spId) {
        this.spId = spId;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public String getSpFecha() {
        return spFecha;
    }

    public void setSpFecha(String spFecha) {
        this.spFecha = spFecha;
    }

    public String getSpEdad() {
        return spEdad;
    }

    public void setSpEdad(String spEdad) {
        this.spEdad = spEdad;
    }

    public String getSpRecomendaciones() {
        return spRecomendaciones;
    }

    public void setSpRecomendaciones(String spRecomendaciones) {
        this.spRecomendaciones = spRecomendaciones;
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
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSeguimientoParasitosis)) {
            return false;
        }
        ConsultorioExtRsSeguimientoParasitosis other = (ConsultorioExtRsSeguimientoParasitosis) object;
        if ((this.spId == null && other.spId != null) || (this.spId != null && !this.spId.equals(other.spId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguimientoParasitosis[ spId=" + spId + " ]";
    }
    
}
