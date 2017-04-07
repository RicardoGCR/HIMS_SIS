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
public class ConsultorioExtRsSuplementacionVitaminaA implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long svId;
    private String m61Fecha;
    private String m61Fua;
    private String m62Fecha;
    private String m62Fua;
    private String m11Fecha;
    private String m11Fua;
    private String m12Fecha;
    private String m12Fua;
    private String m21Fecha;
    private String m21Fua;
    private String m22Fecha;
    private String m22Fua;
    private String m31Fecha;
    private String m31Fua;
    private String m32Fecha;
    private String m32Fua;
    private String m41Fecha;
    private String m41Fua;
    private String m42Fecha;
    private String m42Fua;

    public ConsultorioExtRsSuplementacionVitaminaA() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSuplementacionVitaminaA(Long svId) {
        this.svId = svId;
    }

    public Long getSvId() {
        return svId;
    }

    public void setSvId(Long svId) {
        this.svId = svId;
    }

    public String getM61Fecha() {
        return m61Fecha;
    }

    public void setM61Fecha(String m61Fecha) {
        this.m61Fecha = m61Fecha;
    }

    public String getM61Fua() {
        return m61Fua;
    }

    public void setM61Fua(String m61Fua) {
        this.m61Fua = m61Fua;
    }

    public String getM62Fecha() {
        return m62Fecha;
    }

    public void setM62Fecha(String m62Fecha) {
        this.m62Fecha = m62Fecha;
    }

    public String getM62Fua() {
        return m62Fua;
    }

    public void setM62Fua(String m62Fua) {
        this.m62Fua = m62Fua;
    }

    public String getM11Fecha() {
        return m11Fecha;
    }

    public void setM11Fecha(String m11Fecha) {
        this.m11Fecha = m11Fecha;
    }

    public String getM11Fua() {
        return m11Fua;
    }

    public void setM11Fua(String m11Fua) {
        this.m11Fua = m11Fua;
    }

    public String getM12Fecha() {
        return m12Fecha;
    }

    public void setM12Fecha(String m12Fecha) {
        this.m12Fecha = m12Fecha;
    }

    public String getM12Fua() {
        return m12Fua;
    }

    public void setM12Fua(String m12Fua) {
        this.m12Fua = m12Fua;
    }

    public String getM21Fecha() {
        return m21Fecha;
    }

    public void setM21Fecha(String m21Fecha) {
        this.m21Fecha = m21Fecha;
    }

    public String getM21Fua() {
        return m21Fua;
    }

    public void setM21Fua(String m21Fua) {
        this.m21Fua = m21Fua;
    }

    public String getM22Fecha() {
        return m22Fecha;
    }

    public void setM22Fecha(String m22Fecha) {
        this.m22Fecha = m22Fecha;
    }

    public String getM22Fua() {
        return m22Fua;
    }

    public void setM22Fua(String m22Fua) {
        this.m22Fua = m22Fua;
    }

    public String getM31Fecha() {
        return m31Fecha;
    }

    public void setM31Fecha(String m31Fecha) {
        this.m31Fecha = m31Fecha;
    }

    public String getM31Fua() {
        return m31Fua;
    }

    public void setM31Fua(String m31Fua) {
        this.m31Fua = m31Fua;
    }

    public String getM32Fecha() {
        return m32Fecha;
    }

    public void setM32Fecha(String m32Fecha) {
        this.m32Fecha = m32Fecha;
    }

    public String getM32Fua() {
        return m32Fua;
    }

    public void setM32Fua(String m32Fua) {
        this.m32Fua = m32Fua;
    }

    public String getM41Fecha() {
        return m41Fecha;
    }

    public void setM41Fecha(String m41Fecha) {
        this.m41Fecha = m41Fecha;
    }

    public String getM41Fua() {
        return m41Fua;
    }

    public void setM41Fua(String m41Fua) {
        this.m41Fua = m41Fua;
    }

    public String getM42Fecha() {
        return m42Fecha;
    }

    public void setM42Fecha(String m42Fecha) {
        this.m42Fecha = m42Fecha;
    }

    public String getM42Fua() {
        return m42Fua;
    }

    public void setM42Fua(String m42Fua) {
        this.m42Fua = m42Fua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (svId != null ? svId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSuplementacionVitaminaA)) {
            return false;
        }
        ConsultorioExtRsSuplementacionVitaminaA other = (ConsultorioExtRsSuplementacionVitaminaA) object;
        if ((this.svId == null && other.svId != null) || (this.svId != null && !this.svId.equals(other.svId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSuplementacionVitaminaA[ svId=" + svId + " ]";
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
