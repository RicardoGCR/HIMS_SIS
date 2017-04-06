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
public class ConsultorioExtRsSeguimientoParasitosis implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long spId;
    private String spFecha;
    private String spEdad;
    private String spRecomendaciones;
    private Character estado;
    private String codUsu;
    private String nomPc;

    public ConsultorioExtRsSeguimientoParasitosis() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSeguimientoParasitosis(Long spId) {
        this.spId = spId;
    }

    public Long getSpId() {
        return spId;
    }

    public void setSpId(Long spId) {
        this.spId = spId;
    }

    public String getSpFecha() {
        return spFecha;
    }

    public void setSpFecha(String spFecha) {
        this.spFecha = spFecha;
    }

    public String getSpEdad() {
        return spEdad;
    }

    public void setSpEdad(String spEdad) {
        this.spEdad = spEdad;
    }

    public String getSpRecomendaciones() {
        return spRecomendaciones;
    }

    public void setSpRecomendaciones(String spRecomendaciones) {
        this.spRecomendaciones = spRecomendaciones;
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
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSeguimientoParasitosis)) {
            return false;
        }
        ConsultorioExtRsSeguimientoParasitosis other = (ConsultorioExtRsSeguimientoParasitosis) object;
        if ((this.spId == null && other.spId != null) || (this.spId != null && !this.spId.equals(other.spId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSeguimientoParasitosis[ spId=" + spId + " ]";
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
