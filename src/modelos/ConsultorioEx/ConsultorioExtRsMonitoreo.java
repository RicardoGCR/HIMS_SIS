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

/**
 *
 * @author PC02
 */
public class ConsultorioExtRsMonitoreo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long moId;
    private String moFecha;
    private String moEdad;
    private String moPeso;
    private String moTalla;
    private Character estado;
    private String codUsu;
    private String nomPc;

    public ConsultorioExtRsMonitoreo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsMonitoreo(Long moId) {
        this.moId = moId;
    }

    public Long getMoId() {
        return moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    public String getMoFecha() {
        return moFecha;
    }

    public void setMoFecha(String moFecha) {
        this.moFecha = moFecha;
    }

    public String getMoEdad() {
        return moEdad;
    }

    public void setMoEdad(String moEdad) {
        this.moEdad = moEdad;
    }

    public String getMoPeso() {
        return moPeso;
    }

    public void setMoPeso(String moPeso) {
        this.moPeso = moPeso;
    }

    public String getMoTalla() {
        return moTalla;
    }

    public void setMoTalla(String moTalla) {
        this.moTalla = moTalla;
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
        hash += (moId != null ? moId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsMonitoreo)) {
            return false;
        }
        ConsultorioExtRsMonitoreo other = (ConsultorioExtRsMonitoreo) object;
        if ((this.moId == null && other.moId != null) || (this.moId != null && !this.moId.equals(other.moId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsMonitoreo[ moId=" + moId + " ]";
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
