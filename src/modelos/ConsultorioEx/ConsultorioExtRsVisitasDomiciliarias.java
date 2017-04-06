/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_RS_VISITAS_DOMICILIARIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findAll", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVmId", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vmId = :vmId"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd1Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd1Fecha = :vd1Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd2Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd2Fecha = :vd2Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd3Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd3Fecha = :vd3Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd4Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd4Fecha = :vd4Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd5Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd5Fecha = :vd5Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd6Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd6Fecha = :vd6Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd7Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd7Fecha = :vd7Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd8Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd8Fecha = :vd8Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd9Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd9Fecha = :vd9Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd10Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd10Fecha = :vd10Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd11Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd11Fecha = :vd11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd12Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd12Fecha = :vd12Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd13Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd13Fecha = :vd13Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd14Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd14Fecha = :vd14Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd15Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd15Fecha = :vd15Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd16Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd16Fecha = :vd16Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd17Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd17Fecha = :vd17Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd18Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd18Fecha = :vd18Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd19Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd19Fecha = :vd19Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd20Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd20Fecha = :vd20Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd21Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd21Fecha = :vd21Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd22Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd22Fecha = :vd22Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd23Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd23Fecha = :vd23Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd24Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd24Fecha = :vd24Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd25Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd25Fecha = :vd25Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd26Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd26Fecha = :vd26Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd27Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd27Fecha = :vd27Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd28Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd28Fecha = :vd28Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd29Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd29Fecha = :vd29Fecha"),
    @NamedQuery(name = "ConsultorioExtRsVisitasDomiciliarias.findByVd30Fecha", query = "SELECT c FROM ConsultorioExtRsVisitasDomiciliarias c WHERE c.vd30Fecha = :vd30Fecha")})
public class ConsultorioExtRsVisitasDomiciliarias implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    @Id
    @Basic(optional = false)
    @Column(name = "VM_ID")
    private Long vmId;
    @Column(name = "VD1_FECHA")
    private String vd1Fecha;
    @Column(name = "VD2_FECHA")
    private String vd2Fecha;
    @Column(name = "VD3_FECHA")
    private String vd3Fecha;
    @Column(name = "VD4_FECHA")
    private String vd4Fecha;
    @Column(name = "VD5_FECHA")
    private String vd5Fecha;
    @Column(name = "VD6_FECHA")
    private String vd6Fecha;
    @Column(name = "VD7_FECHA")
    private String vd7Fecha;
    @Column(name = "VD8_FECHA")
    private String vd8Fecha;
    @Column(name = "VD9_FECHA")
    private String vd9Fecha;
    @Column(name = "VD10_FECHA")
    private String vd10Fecha;
    @Column(name = "VD11_FECHA")
    private String vd11Fecha;
    @Column(name = "VD12_FECHA")
    private String vd12Fecha;
    @Column(name = "VD13_FECHA")
    private String vd13Fecha;
    @Column(name = "VD14_FECHA")
    private String vd14Fecha;
    @Column(name = "VD15_FECHA")
    private String vd15Fecha;
    @Column(name = "VD16_FECHA")
    private String vd16Fecha;
    @Column(name = "VD17_FECHA")
    private String vd17Fecha;
    @Column(name = "VD18_FECHA")
    private String vd18Fecha;
    @Column(name = "VD19_FECHA")
    private String vd19Fecha;
    @Column(name = "VD20_FECHA")
    private String vd20Fecha;
    @Column(name = "VD21_FECHA")
    private String vd21Fecha;
    @Column(name = "VD22_FECHA")
    private String vd22Fecha;
    @Column(name = "VD23_FECHA")
    private String vd23Fecha;
    @Column(name = "VD24_FECHA")
    private String vd24Fecha;
    @Column(name = "VD25_FECHA")
    private String vd25Fecha;
    @Column(name = "VD26_FECHA")
    private String vd26Fecha;
    @Column(name = "VD27_FECHA")
    private String vd27Fecha;
    @Column(name = "VD28_FECHA")
    private String vd28Fecha;
    @Column(name = "VD29_FECHA")
    private String vd29Fecha;
    @Column(name = "VD30_FECHA")
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
