/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.xml.bind.annotation.XmlRootElement;

public class ConsultorioExtRsSeguimientoAnemia implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long saId;
    private String saFecha;
    private String saEdad;
    private String saRes;
    private Character estado;
    private String codUsu;
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

    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the rs_id
     */
    public int getRs_id() {
        return rs_id;
    }

    /**
     * @param rs_id the rs_id to set
     */
    public void setRs_id(int rs_id) {
        this.rs_id = rs_id;
    }
    
}
