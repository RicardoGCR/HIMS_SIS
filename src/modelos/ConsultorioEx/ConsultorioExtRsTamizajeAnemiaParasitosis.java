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
@Table(name = "CONSULTORIO_EXT_RS_TAMIZAJE_ANEMIA_PARASITOSIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findAll", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTaId", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.taId = :taId"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia6mFecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia6mFecha = :anemia6mFecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia6mFua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia6mFua = :anemia6mFua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia1Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia1Fecha = :anemia1Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia1Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia1Fua = :anemia1Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia2Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia2Fecha = :anemia2Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia2Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia2Fua = :anemia2Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia3Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia3Fecha = :anemia3Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia3Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia3Fua = :anemia3Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia4Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia4Fecha = :anemia4Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia4Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia4Fua = :anemia4Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia11Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia11Fecha = :anemia11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByAnemia11Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.anemia11Fua = :anemia11Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit1Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit1Fecha = :parasit1Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit1Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit1Fua = :parasit1Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit2Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit2Fecha = :parasit2Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit2Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit2Fua = :parasit2Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit3Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit3Fecha = :parasit3Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit3Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit3Fua = :parasit3Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit4Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit4Fecha = :parasit4Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit4Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit4Fua = :parasit4Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit11Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit11Fecha = :parasit11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByParasit11Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.parasit11Fua = :parasit11Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest1Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test1Fecha = :test1Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest1Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test1Fua = :test1Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest2Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test2Fecha = :test2Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest2Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test2Fua = :test2Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest3Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test3Fecha = :test3Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest3Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test3Fua = :test3Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest4Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test4Fecha = :test4Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest4Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test4Fua = :test4Fua"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest11Fecha", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test11Fecha = :test11Fecha"),
    @NamedQuery(name = "ConsultorioExtRsTamizajeAnemiaParasitosis.findByTest11Fua", query = "SELECT c FROM ConsultorioExtRsTamizajeAnemiaParasitosis c WHERE c.test11Fua = :test11Fua")})
public class ConsultorioExtRsTamizajeAnemiaParasitosis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TA_ID")
    private Long taId;
    @Column(name = "ANEMIA6M_FECHA")
    private String anemia6mFecha;
    @Column(name = "ANEMIA6M_FUA")
    private String anemia6mFua;
    @Column(name = "ANEMIA1_FECHA")
    private String anemia1Fecha;
    @Column(name = "ANEMIA1_FUA")
    private String anemia1Fua;
    @Column(name = "ANEMIA2_FECHA")
    private String anemia2Fecha;
    @Column(name = "ANEMIA2_FUA")
    private String anemia2Fua;
    @Column(name = "ANEMIA3_FECHA")
    private String anemia3Fecha;
    @Column(name = "ANEMIA3_FUA")
    private String anemia3Fua;
    @Column(name = "ANEMIA4_FECHA")
    private String anemia4Fecha;
    @Column(name = "ANEMIA4_FUA")
    private String anemia4Fua;
    @Column(name = "ANEMIA11_FECHA")
    private String anemia11Fecha;
    @Column(name = "ANEMIA11_FUA")
    private String anemia11Fua;
    @Column(name = "PARASIT1_FECHA")
    private String parasit1Fecha;
    @Column(name = "PARASIT1_FUA")
    private String parasit1Fua;
    @Column(name = "PARASIT2_FECHA")
    private String parasit2Fecha;
    @Column(name = "PARASIT2_FUA")
    private String parasit2Fua;
    @Column(name = "PARASIT3_FECHA")
    private String parasit3Fecha;
    @Column(name = "PARASIT3_FUA")
    private String parasit3Fua;
    @Column(name = "PARASIT4_FECHA")
    private String parasit4Fecha;
    @Column(name = "PARASIT4_FUA")
    private String parasit4Fua;
    @Column(name = "PARASIT11_FECHA")
    private String parasit11Fecha;
    @Column(name = "PARASIT11_FUA")
    private String parasit11Fua;
    @Column(name = "TEST1_FECHA")
    private String test1Fecha;
    @Column(name = "TEST1_FUA")
    private String test1Fua;
    @Column(name = "TEST2_FECHA")
    private String test2Fecha;
    @Column(name = "TEST2_FUA")
    private String test2Fua;
    @Column(name = "TEST3_FECHA")
    private String test3Fecha;
    @Column(name = "TEST3_FUA")
    private String test3Fua;
    @Column(name = "TEST4_FECHA")
    private String test4Fecha;
    @Column(name = "TEST4_FUA")
    private String test4Fua;
    @Column(name = "TEST11_FECHA")
    private String test11Fecha;
    @Column(name = "TEST11_FUA")
    private String test11Fua;

    public ConsultorioExtRsTamizajeAnemiaParasitosis() {
    }

    public ConsultorioExtRsTamizajeAnemiaParasitosis(Long taId) {
        this.taId = taId;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
    }

    public String getAnemia6mFecha() {
        return anemia6mFecha;
    }

    public void setAnemia6mFecha(String anemia6mFecha) {
        this.anemia6mFecha = anemia6mFecha;
    }

    public String getAnemia6mFua() {
        return anemia6mFua;
    }

    public void setAnemia6mFua(String anemia6mFua) {
        this.anemia6mFua = anemia6mFua;
    }

    public String getAnemia1Fecha() {
        return anemia1Fecha;
    }

    public void setAnemia1Fecha(String anemia1Fecha) {
        this.anemia1Fecha = anemia1Fecha;
    }

    public String getAnemia1Fua() {
        return anemia1Fua;
    }

    public void setAnemia1Fua(String anemia1Fua) {
        this.anemia1Fua = anemia1Fua;
    }

    public String getAnemia2Fecha() {
        return anemia2Fecha;
    }

    public void setAnemia2Fecha(String anemia2Fecha) {
        this.anemia2Fecha = anemia2Fecha;
    }

    public String getAnemia2Fua() {
        return anemia2Fua;
    }

    public void setAnemia2Fua(String anemia2Fua) {
        this.anemia2Fua = anemia2Fua;
    }

    public String getAnemia3Fecha() {
        return anemia3Fecha;
    }

    public void setAnemia3Fecha(String anemia3Fecha) {
        this.anemia3Fecha = anemia3Fecha;
    }

    public String getAnemia3Fua() {
        return anemia3Fua;
    }

    public void setAnemia3Fua(String anemia3Fua) {
        this.anemia3Fua = anemia3Fua;
    }

    public String getAnemia4Fecha() {
        return anemia4Fecha;
    }

    public void setAnemia4Fecha(String anemia4Fecha) {
        this.anemia4Fecha = anemia4Fecha;
    }

    public String getAnemia4Fua() {
        return anemia4Fua;
    }

    public void setAnemia4Fua(String anemia4Fua) {
        this.anemia4Fua = anemia4Fua;
    }

    public String getAnemia11Fecha() {
        return anemia11Fecha;
    }

    public void setAnemia11Fecha(String anemia11Fecha) {
        this.anemia11Fecha = anemia11Fecha;
    }

    public String getAnemia11Fua() {
        return anemia11Fua;
    }

    public void setAnemia11Fua(String anemia11Fua) {
        this.anemia11Fua = anemia11Fua;
    }

    public String getParasit1Fecha() {
        return parasit1Fecha;
    }

    public void setParasit1Fecha(String parasit1Fecha) {
        this.parasit1Fecha = parasit1Fecha;
    }

    public String getParasit1Fua() {
        return parasit1Fua;
    }

    public void setParasit1Fua(String parasit1Fua) {
        this.parasit1Fua = parasit1Fua;
    }

    public String getParasit2Fecha() {
        return parasit2Fecha;
    }

    public void setParasit2Fecha(String parasit2Fecha) {
        this.parasit2Fecha = parasit2Fecha;
    }

    public String getParasit2Fua() {
        return parasit2Fua;
    }

    public void setParasit2Fua(String parasit2Fua) {
        this.parasit2Fua = parasit2Fua;
    }

    public String getParasit3Fecha() {
        return parasit3Fecha;
    }

    public void setParasit3Fecha(String parasit3Fecha) {
        this.parasit3Fecha = parasit3Fecha;
    }

    public String getParasit3Fua() {
        return parasit3Fua;
    }

    public void setParasit3Fua(String parasit3Fua) {
        this.parasit3Fua = parasit3Fua;
    }

    public String getParasit4Fecha() {
        return parasit4Fecha;
    }

    public void setParasit4Fecha(String parasit4Fecha) {
        this.parasit4Fecha = parasit4Fecha;
    }

    public String getParasit4Fua() {
        return parasit4Fua;
    }

    public void setParasit4Fua(String parasit4Fua) {
        this.parasit4Fua = parasit4Fua;
    }

    public String getParasit11Fecha() {
        return parasit11Fecha;
    }

    public void setParasit11Fecha(String parasit11Fecha) {
        this.parasit11Fecha = parasit11Fecha;
    }

    public String getParasit11Fua() {
        return parasit11Fua;
    }

    public void setParasit11Fua(String parasit11Fua) {
        this.parasit11Fua = parasit11Fua;
    }

    public String getTest1Fecha() {
        return test1Fecha;
    }

    public void setTest1Fecha(String test1Fecha) {
        this.test1Fecha = test1Fecha;
    }

    public String getTest1Fua() {
        return test1Fua;
    }

    public void setTest1Fua(String test1Fua) {
        this.test1Fua = test1Fua;
    }

    public String getTest2Fecha() {
        return test2Fecha;
    }

    public void setTest2Fecha(String test2Fecha) {
        this.test2Fecha = test2Fecha;
    }

    public String getTest2Fua() {
        return test2Fua;
    }

    public void setTest2Fua(String test2Fua) {
        this.test2Fua = test2Fua;
    }

    public String getTest3Fecha() {
        return test3Fecha;
    }

    public void setTest3Fecha(String test3Fecha) {
        this.test3Fecha = test3Fecha;
    }

    public String getTest3Fua() {
        return test3Fua;
    }

    public void setTest3Fua(String test3Fua) {
        this.test3Fua = test3Fua;
    }

    public String getTest4Fecha() {
        return test4Fecha;
    }

    public void setTest4Fecha(String test4Fecha) {
        this.test4Fecha = test4Fecha;
    }

    public String getTest4Fua() {
        return test4Fua;
    }

    public void setTest4Fua(String test4Fua) {
        this.test4Fua = test4Fua;
    }

    public String getTest11Fecha() {
        return test11Fecha;
    }

    public void setTest11Fecha(String test11Fecha) {
        this.test11Fecha = test11Fecha;
    }

    public String getTest11Fua() {
        return test11Fua;
    }

    public void setTest11Fua(String test11Fua) {
        this.test11Fua = test11Fua;
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
        if (!(object instanceof ConsultorioExtRsTamizajeAnemiaParasitosis)) {
            return false;
        }
        ConsultorioExtRsTamizajeAnemiaParasitosis other = (ConsultorioExtRsTamizajeAnemiaParasitosis) object;
        if ((this.taId == null && other.taId != null) || (this.taId != null && !this.taId.equals(other.taId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTamizajeAnemiaParasitosis[ taId=" + taId + " ]";
    }
    
}
