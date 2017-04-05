/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;

public class ConsultorioExtRsTamizajeNeonatal implements Serializable {
    private static final long serialVersionUID = 1L;

    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long tnId;
    private String tshFecha;
    private String tshFua;
    private String fcFecha;
    private String fcFua;
    private String foFecha;
    private String foFua;
    private String hsrFecha;
    private String hsrFua;

    public ConsultorioExtRsTamizajeNeonatal() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsTamizajeNeonatal(Long tnId) {
        this.tnId = tnId;
    }

    public Long getTnId() {
        return tnId;
    }

    public void setTnId(Long tnId) {
        this.tnId = tnId;
    }

    public String getTshFecha() {
        return tshFecha;
    }

    public void setTshFecha(String tshFecha) {
        this.tshFecha = tshFecha;
    }

    public String getTshFua() {
        return tshFua;
    }

    public void setTshFua(String tshFua) {
        this.tshFua = tshFua;
    }

    public String getFcFecha() {
        return fcFecha;
    }

    public void setFcFecha(String fcFecha) {
        this.fcFecha = fcFecha;
    }

    public String getFcFua() {
        return fcFua;
    }

    public void setFcFua(String fcFua) {
        this.fcFua = fcFua;
    }

    public String getFoFecha() {
        return foFecha;
    }

    public void setFoFecha(String foFecha) {
        this.foFecha = foFecha;
    }

    public String getFoFua() {
        return foFua;
    }

    public void setFoFua(String foFua) {
        this.foFua = foFua;
    }

    public String getHsrFecha() {
        return hsrFecha;
    }

    public void setHsrFecha(String hsrFecha) {
        this.hsrFecha = hsrFecha;
    }

    public String getHsrFua() {
        return hsrFua;
    }

    public void setHsrFua(String hsrFua) {
        this.hsrFua = hsrFua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tnId != null ? tnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsTamizajeNeonatal)) {
            return false;
        }
        ConsultorioExtRsTamizajeNeonatal other = (ConsultorioExtRsTamizajeNeonatal) object;
        if ((this.tnId == null && other.tnId != null) || (this.tnId != null && !this.tnId.equals(other.tnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTamizajeNeonatal[ tnId=" + tnId + " ]";
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
