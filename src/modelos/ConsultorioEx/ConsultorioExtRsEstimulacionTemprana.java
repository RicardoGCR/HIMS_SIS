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

/**
 *
 * @author PC02
 */
public class ConsultorioExtRsEstimulacionTemprana implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long etId;
    private String m1Fecha;
    private String m1Cie10;
    private String m1Fua;
    private String m2Fecha;
    private String m2Cie10;
    private String m2Fua;
    private String m3Fecha;
    private String m3Cie10;
    private String m3Fua;
    private String m4Fecha;
    private String m4Cie10;
    private String m4Fua;
    private String m5Fecha;
    private String m5Cie10;
    private String m5Fua;
    private String m6Fecha;
    private String m6Cie10;
    private String m6Fua;
    private String m7Fecha;
    private String m7Cie10;
    private String m7Fua;
    private String m8Fecha;
    private String m8Cie10;
    private String m8Fua;
    private String m9Fecha;
    private String m9Cie10;
    private String m9Fua;
    private String m10Fecha;
    private String m10Cie10;
    private String m10Fua;
    private String m11Fecha;
    private String m11Cie10;
    private String m11Fua;
    private String m12Fecha;
    private String m12Cie10;
    private String m12Fua;
    private String m13Fecha;
    private String m13Cie10;
    private String m13Fua;

    public ConsultorioExtRsEstimulacionTemprana() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsEstimulacionTemprana(Long etId) {
        this.etId = etId;
    }

    public Long getEtId() {
        return etId;
    }

    public void setEtId(Long etId) {
        this.etId = etId;
    }

    public String getM1Fecha() {
        return m1Fecha;
    }

    public void setM1Fecha(String m1Fecha) {
        this.m1Fecha = m1Fecha;
    }

    public String getM1Cie10() {
        return m1Cie10;
    }

    public void setM1Cie10(String m1Cie10) {
        this.m1Cie10 = m1Cie10;
    }

    public String getM1Fua() {
        return m1Fua;
    }

    public void setM1Fua(String m1Fua) {
        this.m1Fua = m1Fua;
    }

    public String getM2Fecha() {
        return m2Fecha;
    }

    public void setM2Fecha(String m2Fecha) {
        this.m2Fecha = m2Fecha;
    }

    public String getM2Cie10() {
        return m2Cie10;
    }

    public void setM2Cie10(String m2Cie10) {
        this.m2Cie10 = m2Cie10;
    }

    public String getM2Fua() {
        return m2Fua;
    }

    public void setM2Fua(String m2Fua) {
        this.m2Fua = m2Fua;
    }

    public String getM3Fecha() {
        return m3Fecha;
    }

    public void setM3Fecha(String m3Fecha) {
        this.m3Fecha = m3Fecha;
    }

    public String getM3Cie10() {
        return m3Cie10;
    }

    public void setM3Cie10(String m3Cie10) {
        this.m3Cie10 = m3Cie10;
    }

    public String getM3Fua() {
        return m3Fua;
    }

    public void setM3Fua(String m3Fua) {
        this.m3Fua = m3Fua;
    }

    public String getM4Fecha() {
        return m4Fecha;
    }

    public void setM4Fecha(String m4Fecha) {
        this.m4Fecha = m4Fecha;
    }

    public String getM4Cie10() {
        return m4Cie10;
    }

    public void setM4Cie10(String m4Cie10) {
        this.m4Cie10 = m4Cie10;
    }

    public String getM4Fua() {
        return m4Fua;
    }

    public void setM4Fua(String m4Fua) {
        this.m4Fua = m4Fua;
    }

    public String getM5Fecha() {
        return m5Fecha;
    }

    public void setM5Fecha(String m5Fecha) {
        this.m5Fecha = m5Fecha;
    }

    public String getM5Cie10() {
        return m5Cie10;
    }

    public void setM5Cie10(String m5Cie10) {
        this.m5Cie10 = m5Cie10;
    }

    public String getM5Fua() {
        return m5Fua;
    }

    public void setM5Fua(String m5Fua) {
        this.m5Fua = m5Fua;
    }

    public String getM6Fecha() {
        return m6Fecha;
    }

    public void setM6Fecha(String m6Fecha) {
        this.m6Fecha = m6Fecha;
    }

    public String getM6Cie10() {
        return m6Cie10;
    }

    public void setM6Cie10(String m6Cie10) {
        this.m6Cie10 = m6Cie10;
    }

    public String getM6Fua() {
        return m6Fua;
    }

    public void setM6Fua(String m6Fua) {
        this.m6Fua = m6Fua;
    }

    public String getM7Fecha() {
        return m7Fecha;
    }

    public void setM7Fecha(String m7Fecha) {
        this.m7Fecha = m7Fecha;
    }

    public String getM7Cie10() {
        return m7Cie10;
    }

    public void setM7Cie10(String m7Cie10) {
        this.m7Cie10 = m7Cie10;
    }

    public String getM7Fua() {
        return m7Fua;
    }

    public void setM7Fua(String m7Fua) {
        this.m7Fua = m7Fua;
    }

    public String getM8Fecha() {
        return m8Fecha;
    }

    public void setM8Fecha(String m8Fecha) {
        this.m8Fecha = m8Fecha;
    }

    public String getM8Cie10() {
        return m8Cie10;
    }

    public void setM8Cie10(String m8Cie10) {
        this.m8Cie10 = m8Cie10;
    }

    public String getM8Fua() {
        return m8Fua;
    }

    public void setM8Fua(String m8Fua) {
        this.m8Fua = m8Fua;
    }

    public String getM9Fecha() {
        return m9Fecha;
    }

    public void setM9Fecha(String m9Fecha) {
        this.m9Fecha = m9Fecha;
    }

    public String getM9Cie10() {
        return m9Cie10;
    }

    public void setM9Cie10(String m9Cie10) {
        this.m9Cie10 = m9Cie10;
    }

    public String getM9Fua() {
        return m9Fua;
    }

    public void setM9Fua(String m9Fua) {
        this.m9Fua = m9Fua;
    }

    public String getM10Fecha() {
        return m10Fecha;
    }

    public void setM10Fecha(String m10Fecha) {
        this.m10Fecha = m10Fecha;
    }

    public String getM10Cie10() {
        return m10Cie10;
    }

    public void setM10Cie10(String m10Cie10) {
        this.m10Cie10 = m10Cie10;
    }

    public String getM10Fua() {
        return m10Fua;
    }

    public void setM10Fua(String m10Fua) {
        this.m10Fua = m10Fua;
    }

    public String getM11Fecha() {
        return m11Fecha;
    }

    public void setM11Fecha(String m11Fecha) {
        this.m11Fecha = m11Fecha;
    }

    public String getM11Cie10() {
        return m11Cie10;
    }

    public void setM11Cie10(String m11Cie10) {
        this.m11Cie10 = m11Cie10;
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

    public String getM12Cie10() {
        return m12Cie10;
    }

    public void setM12Cie10(String m12Cie10) {
        this.m12Cie10 = m12Cie10;
    }

    public String getM12Fua() {
        return m12Fua;
    }

    public void setM12Fua(String m12Fua) {
        this.m12Fua = m12Fua;
    }

    public String getM13Fecha() {
        return m13Fecha;
    }

    public void setM13Fecha(String m13Fecha) {
        this.m13Fecha = m13Fecha;
    }

    public String getM13Cie10() {
        return m13Cie10;
    }

    public void setM13Cie10(String m13Cie10) {
        this.m13Cie10 = m13Cie10;
    }

    public String getM13Fua() {
        return m13Fua;
    }

    public void setM13Fua(String m13Fua) {
        this.m13Fua = m13Fua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etId != null ? etId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsEstimulacionTemprana)) {
            return false;
        }
        ConsultorioExtRsEstimulacionTemprana other = (ConsultorioExtRsEstimulacionTemprana) object;
        if ((this.etId == null && other.etId != null) || (this.etId != null && !this.etId.equals(other.etId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsEstimulacionTemprana[ etId=" + etId + " ]";
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
