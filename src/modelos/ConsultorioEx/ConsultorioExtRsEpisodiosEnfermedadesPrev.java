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
public class ConsultorioExtRsEpisodiosEnfermedadesPrev implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long eeId;
    private String ee1Fecha;
    private String ee1Fua;
    private String ee2Fecha;
    private String ee2Fua;
    private String ee3Fecha;
    private String ee3Fua;
    private String ee4Fecha;
    private String ee4Fua;
    private String ee5Fecha;
    private String ee5Fua;
    private String ee6Fecha;
    private String ee6Fua;
    private String ee7Fecha;
    private String ee7Fua;
    private String ee8Fecha;
    private String ee8Fua;
    private String ee9Fecha;
    private String ee9Fua;
    private String ee10Fecha;
    private String ee10Fua;

    public ConsultorioExtRsEpisodiosEnfermedadesPrev() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsEpisodiosEnfermedadesPrev(Long eeId) {
        this.eeId = eeId;
    }

    public Long getEeId() {
        return eeId;
    }

    public void setEeId(Long eeId) {
        this.eeId = eeId;
    }

    public String getEe1Fecha() {
        return ee1Fecha;
    }

    public void setEe1Fecha(String ee1Fecha) {
        this.ee1Fecha = ee1Fecha;
    }

    public String getEe1Fua() {
        return ee1Fua;
    }

    public void setEe1Fua(String ee1Fua) {
        this.ee1Fua = ee1Fua;
    }

    public String getEe2Fecha() {
        return ee2Fecha;
    }

    public void setEe2Fecha(String ee2Fecha) {
        this.ee2Fecha = ee2Fecha;
    }

    public String getEe2Fua() {
        return ee2Fua;
    }

    public void setEe2Fua(String ee2Fua) {
        this.ee2Fua = ee2Fua;
    }

    public String getEe3Fecha() {
        return ee3Fecha;
    }

    public void setEe3Fecha(String ee3Fecha) {
        this.ee3Fecha = ee3Fecha;
    }

    public String getEe3Fua() {
        return ee3Fua;
    }

    public void setEe3Fua(String ee3Fua) {
        this.ee3Fua = ee3Fua;
    }

    public String getEe4Fecha() {
        return ee4Fecha;
    }

    public void setEe4Fecha(String ee4Fecha) {
        this.ee4Fecha = ee4Fecha;
    }

    public String getEe4Fua() {
        return ee4Fua;
    }

    public void setEe4Fua(String ee4Fua) {
        this.ee4Fua = ee4Fua;
    }

    public String getEe5Fecha() {
        return ee5Fecha;
    }

    public void setEe5Fecha(String ee5Fecha) {
        this.ee5Fecha = ee5Fecha;
    }

    public String getEe5Fua() {
        return ee5Fua;
    }

    public void setEe5Fua(String ee5Fua) {
        this.ee5Fua = ee5Fua;
    }

    public String getEe6Fecha() {
        return ee6Fecha;
    }

    public void setEe6Fecha(String ee6Fecha) {
        this.ee6Fecha = ee6Fecha;
    }

    public String getEe6Fua() {
        return ee6Fua;
    }

    public void setEe6Fua(String ee6Fua) {
        this.ee6Fua = ee6Fua;
    }

    public String getEe7Fecha() {
        return ee7Fecha;
    }

    public void setEe7Fecha(String ee7Fecha) {
        this.ee7Fecha = ee7Fecha;
    }

    public String getEe7Fua() {
        return ee7Fua;
    }

    public void setEe7Fua(String ee7Fua) {
        this.ee7Fua = ee7Fua;
    }

    public String getEe8Fecha() {
        return ee8Fecha;
    }

    public void setEe8Fecha(String ee8Fecha) {
        this.ee8Fecha = ee8Fecha;
    }

    public String getEe8Fua() {
        return ee8Fua;
    }

    public void setEe8Fua(String ee8Fua) {
        this.ee8Fua = ee8Fua;
    }

    public String getEe9Fecha() {
        return ee9Fecha;
    }

    public void setEe9Fecha(String ee9Fecha) {
        this.ee9Fecha = ee9Fecha;
    }

    public String getEe9Fua() {
        return ee9Fua;
    }

    public void setEe9Fua(String ee9Fua) {
        this.ee9Fua = ee9Fua;
    }

    public String getEe10Fecha() {
        return ee10Fecha;
    }

    public void setEe10Fecha(String ee10Fecha) {
        this.ee10Fecha = ee10Fecha;
    }

    public String getEe10Fua() {
        return ee10Fua;
    }

    public void setEe10Fua(String ee10Fua) {
        this.ee10Fua = ee10Fua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eeId != null ? eeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsEpisodiosEnfermedadesPrev)) {
            return false;
        }
        ConsultorioExtRsEpisodiosEnfermedadesPrev other = (ConsultorioExtRsEpisodiosEnfermedadesPrev) object;
        if ((this.eeId == null && other.eeId != null) || (this.eeId != null && !this.eeId.equals(other.eeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsEpisodiosEnfermedadesPrev[ eeId=" + eeId + " ]";
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
