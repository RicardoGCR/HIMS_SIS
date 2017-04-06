/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
public class ConsultorioExtRsSeguiientoIra implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long siId;
    private String siFecha;
    private String siEdad;
    private String siRes;
    private Character estado;
    private String codUsu;
    private String nomPc;

    public ConsultorioExtRsSeguiientoIra() {
        Conexion con = new Conexion();
        cn = con.conectar();
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
