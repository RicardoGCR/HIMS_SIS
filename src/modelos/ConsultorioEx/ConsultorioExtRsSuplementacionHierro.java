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
@Table(name = "CONSULTORIO_EXT_RS_SUPLEMENTACION_HIERRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findAll", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByShId", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.shId = :shId"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM1Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m1Fecha = :m1Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM1Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m1Fua = :m1Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM2Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m2Fecha = :m2Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM2Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m2Fua = :m2Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM3Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m3Fecha = :m3Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM3Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m3Fua = :m3Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM4Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m4Fecha = :m4Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM4Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m4Fua = :m4Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM5Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m5Fecha = :m5Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM5Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m5Fua = :m5Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM6Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m6Fecha = :m6Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM6Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m6Fua = :m6Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM7Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m7Fecha = :m7Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM7Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m7Fua = :m7Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM8Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m8Fecha = :m8Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM8Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m8Fua = :m8Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM9Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m9Fecha = :m9Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM9Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m9Fua = :m9Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM10Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m10Fecha = :m10Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM10Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m10Fua = :m10Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM11Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m11Fecha = :m11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM11Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m11Fua = :m11Fua"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM12Fecha", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m12Fecha = :m12Fecha"),
    @NamedQuery(name = "ConsultorioExtRsSuplementacionHierro.findByM12Fua", query = "SELECT c FROM ConsultorioExtRsSuplementacionHierro c WHERE c.m12Fua = :m12Fua")})
public class ConsultorioExtRsSuplementacionHierro implements Serializable {
    private static final long serialVersionUID = 1L;
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    @Id
    @Basic(optional = false)
    @Column(name = "SH_ID")
    private Long shId;
    @Column(name = "M1_FECHA")
    private String m1Fecha;
    @Column(name = "M1_FUA")
    private String m1Fua;
    @Column(name = "M2_FECHA")
    private String m2Fecha;
    @Column(name = "M2_FUA")
    private String m2Fua;
    @Column(name = "M3_FECHA")
    private String m3Fecha;
    @Column(name = "M3_FUA")
    private String m3Fua;
    @Column(name = "M4_FECHA")
    private String m4Fecha;
    @Column(name = "M4_FUA")
    private String m4Fua;
    @Column(name = "M5_FECHA")
    private String m5Fecha;
    @Column(name = "M5_FUA")
    private String m5Fua;
    @Column(name = "M6_FECHA")
    private String m6Fecha;
    @Column(name = "M6_FUA")
    private String m6Fua;
    @Column(name = "M7_FECHA")
    private String m7Fecha;
    @Column(name = "M7_FUA")
    private String m7Fua;
    @Column(name = "M8_FECHA")
    private String m8Fecha;
    @Column(name = "M8_FUA")
    private String m8Fua;
    @Column(name = "M9_FECHA")
    private String m9Fecha;
    @Column(name = "M9_FUA")
    private String m9Fua;
    @Column(name = "M10_FECHA")
    private String m10Fecha;
    @Column(name = "M10_FUA")
    private String m10Fua;
    @Column(name = "M11_FECHA")
    private String m11Fecha;
    @Column(name = "M11_FUA")
    private String m11Fua;
    @Column(name = "M12_FECHA")
    private String m12Fecha;
    @Column(name = "M12_FUA")
    private String m12Fua;

    public ConsultorioExtRsSuplementacionHierro() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSuplementacionHierro(Long shId) {
        this.shId = shId;
    }

    public Long getShId() {
        return shId;
    }

    public void setShId(Long shId) {
        this.shId = shId;
    }

    public String getM1Fecha() {
        return m1Fecha;
    }

    public void setM1Fecha(String m1Fecha) {
        this.m1Fecha = m1Fecha;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shId != null ? shId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSuplementacionHierro)) {
            return false;
        }
        ConsultorioExtRsSuplementacionHierro other = (ConsultorioExtRsSuplementacionHierro) object;
        if ((this.shId == null && other.shId != null) || (this.shId != null && !this.shId.equals(other.shId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSuplementacionHierro[ shId=" + shId + " ]";
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
