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

public class ConsultorioExtRsSeguimientoDesarrollo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long sdId;
    private String sdFecha;
    private String sdEdad;
    private String sdRes;
    private Character estado;
    private String codUsu;
    private String nomPc;

    public ConsultorioExtRsSeguimientoDesarrollo() {
        Conexion con = new Conexion();
        cn = con.conectar();
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
