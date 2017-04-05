/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_RS_TTO_ANTIPARASITARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findAll", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByTaId", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.taId = :taId"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM11Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m11Fecha = :m11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM11Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m11Fua = :m11Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM12Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m12Fecha = :m12Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM12Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m12Fua = :m12Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM21Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m21Fecha = :m21Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM21Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m21Fua = :m21Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM22Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m22Fecha = :m22Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM22Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m22Fua = :m22Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM31Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m31Fecha = :m31Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM31Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m31Fua = :m31Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM32Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m32Fecha = :m32Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM32Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m32Fua = :m32Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM41Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m41Fecha = :m41Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM41Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m41Fua = :m41Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM42Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m42Fecha = :m42Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM42Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m42Fua = :m42Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM51Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m51Fecha = :m51Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM51Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m51Fua = :m51Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM52Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m52Fecha = :m52Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM52Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m52Fua = :m52Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM61Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m61Fecha = :m61Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM61Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m61Fua = :m61Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM62Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m62Fecha = :m62Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM62Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m62Fua = :m62Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM71Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m71Fecha = :m71Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM71Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m71Fua = :m71Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM81Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m81Fecha = :m81Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM81Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m81Fua = :m81Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM91Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m91Fecha = :m91Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM91Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m91Fua = :m91Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM101Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m101Fecha = :m101Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM101Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m101Fua = :m101Fua"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM111Fecha", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m111Fecha = :m111Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTtoAntiparasitario.findByM111Fua", query = "SELECT c FROM ConsultorioExtRsTtoAntiparasitario c WHERE c.m111Fua = :m111Fua")})
public class ConsultorioExtRsTtoAntiparasitario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TA_ID")
    private Long taId;
    @Column(name = "M1_1_FECHA")
    private String m11Fecha;
    @Column(name = "M1_1_FUA")
    private String m11Fua;
    @Column(name = "M1_2_FECHA")
    private String m12Fecha;
    @Column(name = "M1_2_FUA")
    private String m12Fua;
    @Column(name = "M2_1_FECHA")
    private String m21Fecha;
    @Column(name = "M2_1_FUA")
    private String m21Fua;
    @Column(name = "M2_2_FECHA")
    private String m22Fecha;
    @Column(name = "M2_2_FUA")
    private String m22Fua;
    @Column(name = "M3_1_FECHA")
    private String m31Fecha;
    @Column(name = "M3_1_FUA")
    private String m31Fua;
    @Column(name = "M3_2_FECHA")
    private String m32Fecha;
    @Column(name = "M3_2_FUA")
    private String m32Fua;
    @Column(name = "M4_1_FECHA")
    private String m41Fecha;
    @Column(name = "M4_1_FUA")
    private String m41Fua;
    @Column(name = "M4_2_FECHA")
    private String m42Fecha;
    @Column(name = "M4_2_FUA")
    private String m42Fua;
    @Column(name = "M5_1_FECHA")
    private String m51Fecha;
    @Column(name = "M5_1_FUA")
    private String m51Fua;
    @Column(name = "M5_2_FECHA")
    private String m52Fecha;
    @Column(name = "M5_2_FUA")
    private String m52Fua;
    @Column(name = "M6_1_FECHA")
    private String m61Fecha;
    @Column(name = "M6_1_FUA")
    private String m61Fua;
    @Column(name = "M6_2_FECHA")
    private String m62Fecha;
    @Column(name = "M6_2_FUA")
    private String m62Fua;
    @Column(name = "M7_1_FECHA")
    private String m71Fecha;
    @Column(name = "M7_1_FUA")
    private String m71Fua;
    @Column(name = "M8_1_FECHA")
    private String m81Fecha;
    @Column(name = "M8_1_FUA")
    private String m81Fua;
    @Column(name = "M9_1_FECHA")
    private String m91Fecha;
    @Column(name = "M9_1_FUA")
    private String m91Fua;
    @Column(name = "M10_1_FECHA")
    private String m101Fecha;
    @Column(name = "M10_1_FUA")
    private String m101Fua;
    @Column(name = "M11_1_FECHA")
    private String m111Fecha;
    @Column(name = "M11_1_FUA")
    private String m111Fua;

    public ConsultorioExtRsTtoAntiparasitario() {
    }

    public ConsultorioExtRsTtoAntiparasitario(Long taId) {
        this.taId = taId;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
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

    public String getM51Fecha() {
        return m51Fecha;
    }

    public void setM51Fecha(String m51Fecha) {
        this.m51Fecha = m51Fecha;
    }

    public String getM51Fua() {
        return m51Fua;
    }

    public void setM51Fua(String m51Fua) {
        this.m51Fua = m51Fua;
    }

    public String getM52Fecha() {
        return m52Fecha;
    }

    public void setM52Fecha(String m52Fecha) {
        this.m52Fecha = m52Fecha;
    }

    public String getM52Fua() {
        return m52Fua;
    }

    public void setM52Fua(String m52Fua) {
        this.m52Fua = m52Fua;
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

    public String getM71Fecha() {
        return m71Fecha;
    }

    public void setM71Fecha(String m71Fecha) {
        this.m71Fecha = m71Fecha;
    }

    public String getM71Fua() {
        return m71Fua;
    }

    public void setM71Fua(String m71Fua) {
        this.m71Fua = m71Fua;
    }

    public String getM81Fecha() {
        return m81Fecha;
    }

    public void setM81Fecha(String m81Fecha) {
        this.m81Fecha = m81Fecha;
    }

    public String getM81Fua() {
        return m81Fua;
    }

    public void setM81Fua(String m81Fua) {
        this.m81Fua = m81Fua;
    }

    public String getM91Fecha() {
        return m91Fecha;
    }

    public void setM91Fecha(String m91Fecha) {
        this.m91Fecha = m91Fecha;
    }

    public String getM91Fua() {
        return m91Fua;
    }

    public void setM91Fua(String m91Fua) {
        this.m91Fua = m91Fua;
    }

    public String getM101Fecha() {
        return m101Fecha;
    }

    public void setM101Fecha(String m101Fecha) {
        this.m101Fecha = m101Fecha;
    }

    public String getM101Fua() {
        return m101Fua;
    }

    public void setM101Fua(String m101Fua) {
        this.m101Fua = m101Fua;
    }

    public String getM111Fecha() {
        return m111Fecha;
    }

    public void setM111Fecha(String m111Fecha) {
        this.m111Fecha = m111Fecha;
    }

    public String getM111Fua() {
        return m111Fua;
    }

    public void setM111Fua(String m111Fua) {
        this.m111Fua = m111Fua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taId != null ? taId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsTtoAntiparasitario)) {
            return false;
        }
        ConsultorioExtRsTtoAntiparasitario other = (ConsultorioExtRsTtoAntiparasitario) object;
        if ((this.taId == null && other.taId != null) || (this.taId != null && !this.taId.equals(other.taId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTtoAntiparasitario[ taId=" + taId + " ]";
    }
    
}
