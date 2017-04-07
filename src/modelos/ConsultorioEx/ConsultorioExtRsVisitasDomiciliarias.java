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
public class ConsultorioExtRsVisitasDomiciliarias implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long vmId;
    private String vd1Fecha;
    private String vd2Fecha;
    private String vd3Fecha;
    private String vd4Fecha;
    private String vd5Fecha;
    private String vd6Fecha;
    private String vd7Fecha;
    private String vd8Fecha;
    private String vd9Fecha;
    private String vd10Fecha;
    private String vd11Fecha;
    private String vd12Fecha;
    private String vd13Fecha;
    private String vd14Fecha;
    private String vd15Fecha;
    private String vd16Fecha;
    private String vd17Fecha;
    private String vd18Fecha;
    private String vd19Fecha;
    private String vd20Fecha;
    private String vd21Fecha;
    private String vd22Fecha;
    private String vd23Fecha;
    private String vd24Fecha;
    private String vd25Fecha;
    private String vd26Fecha;
    private String vd27Fecha;
    private String vd28Fecha;
    private String vd29Fecha;
    private String vd30Fecha;

    public ConsultorioExtRsVisitasDomiciliarias() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsVisitasDomiciliarias(Long vmId) {
        this.vmId = vmId;
    }

    public Long getVmId() {
        return vmId;
    }

    public void setVmId(Long vmId) {
        this.vmId = vmId;
    }

    public String getVd1Fecha() {
        return vd1Fecha;
    }

    public void setVd1Fecha(String vd1Fecha) {
        this.vd1Fecha = vd1Fecha;
    }

    public String getVd2Fecha() {
        return vd2Fecha;
    }

    public void setVd2Fecha(String vd2Fecha) {
        this.vd2Fecha = vd2Fecha;
    }

    public String getVd3Fecha() {
        return vd3Fecha;
    }

    public void setVd3Fecha(String vd3Fecha) {
        this.vd3Fecha = vd3Fecha;
    }

    public String getVd4Fecha() {
        return vd4Fecha;
    }

    public void setVd4Fecha(String vd4Fecha) {
        this.vd4Fecha = vd4Fecha;
    }

    public String getVd5Fecha() {
        return vd5Fecha;
    }

    public void setVd5Fecha(String vd5Fecha) {
        this.vd5Fecha = vd5Fecha;
    }

    public String getVd6Fecha() {
        return vd6Fecha;
    }

    public void setVd6Fecha(String vd6Fecha) {
        this.vd6Fecha = vd6Fecha;
    }

    public String getVd7Fecha() {
        return vd7Fecha;
    }

    public void setVd7Fecha(String vd7Fecha) {
        this.vd7Fecha = vd7Fecha;
    }

    public String getVd8Fecha() {
        return vd8Fecha;
    }

    public void setVd8Fecha(String vd8Fecha) {
        this.vd8Fecha = vd8Fecha;
    }

    public String getVd9Fecha() {
        return vd9Fecha;
    }

    public void setVd9Fecha(String vd9Fecha) {
        this.vd9Fecha = vd9Fecha;
    }

    public String getVd10Fecha() {
        return vd10Fecha;
    }

    public void setVd10Fecha(String vd10Fecha) {
        this.vd10Fecha = vd10Fecha;
    }

    public String getVd11Fecha() {
        return vd11Fecha;
    }

    public void setVd11Fecha(String vd11Fecha) {
        this.vd11Fecha = vd11Fecha;
    }

    public String getVd12Fecha() {
        return vd12Fecha;
    }

    public void setVd12Fecha(String vd12Fecha) {
        this.vd12Fecha = vd12Fecha;
    }

    public String getVd13Fecha() {
        return vd13Fecha;
    }

    public void setVd13Fecha(String vd13Fecha) {
        this.vd13Fecha = vd13Fecha;
    }

    public String getVd14Fecha() {
        return vd14Fecha;
    }

    public void setVd14Fecha(String vd14Fecha) {
        this.vd14Fecha = vd14Fecha;
    }

    public String getVd15Fecha() {
        return vd15Fecha;
    }

    public void setVd15Fecha(String vd15Fecha) {
        this.vd15Fecha = vd15Fecha;
    }

    public String getVd16Fecha() {
        return vd16Fecha;
    }

    public void setVd16Fecha(String vd16Fecha) {
        this.vd16Fecha = vd16Fecha;
    }

    public String getVd17Fecha() {
        return vd17Fecha;
    }

    public void setVd17Fecha(String vd17Fecha) {
        this.vd17Fecha = vd17Fecha;
    }

    public String getVd18Fecha() {
        return vd18Fecha;
    }

    public void setVd18Fecha(String vd18Fecha) {
        this.vd18Fecha = vd18Fecha;
    }

    public String getVd19Fecha() {
        return vd19Fecha;
    }

    public void setVd19Fecha(String vd19Fecha) {
        this.vd19Fecha = vd19Fecha;
    }

    public String getVd20Fecha() {
        return vd20Fecha;
    }

    public void setVd20Fecha(String vd20Fecha) {
        this.vd20Fecha = vd20Fecha;
    }

    public String getVd21Fecha() {
        return vd21Fecha;
    }

    public void setVd21Fecha(String vd21Fecha) {
        this.vd21Fecha = vd21Fecha;
    }

    public String getVd22Fecha() {
        return vd22Fecha;
    }

    public void setVd22Fecha(String vd22Fecha) {
        this.vd22Fecha = vd22Fecha;
    }

    public String getVd23Fecha() {
        return vd23Fecha;
    }

    public void setVd23Fecha(String vd23Fecha) {
        this.vd23Fecha = vd23Fecha;
    }

    public String getVd24Fecha() {
        return vd24Fecha;
    }

    public void setVd24Fecha(String vd24Fecha) {
        this.vd24Fecha = vd24Fecha;
    }

    public String getVd25Fecha() {
        return vd25Fecha;
    }

    public void setVd25Fecha(String vd25Fecha) {
        this.vd25Fecha = vd25Fecha;
    }

    public String getVd26Fecha() {
        return vd26Fecha;
    }

    public void setVd26Fecha(String vd26Fecha) {
        this.vd26Fecha = vd26Fecha;
    }

    public String getVd27Fecha() {
        return vd27Fecha;
    }

    public void setVd27Fecha(String vd27Fecha) {
        this.vd27Fecha = vd27Fecha;
    }

    public String getVd28Fecha() {
        return vd28Fecha;
    }

    public void setVd28Fecha(String vd28Fecha) {
        this.vd28Fecha = vd28Fecha;
    }

    public String getVd29Fecha() {
        return vd29Fecha;
    }

    public void setVd29Fecha(String vd29Fecha) {
        this.vd29Fecha = vd29Fecha;
    }

    public String getVd30Fecha() {
        return vd30Fecha;
    }

    public void setVd30Fecha(String vd30Fecha) {
        this.vd30Fecha = vd30Fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vmId != null ? vmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsVisitasDomiciliarias)) {
            return false;
        }
        ConsultorioExtRsVisitasDomiciliarias other = (ConsultorioExtRsVisitasDomiciliarias) object;
        if ((this.vmId == null && other.vmId != null) || (this.vmId != null && !this.vmId.equals(other.vmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsVisitasDomiciliarias[ vmId=" + vmId + " ]";
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
