/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

public class ConsultorioExtCarnetPerinatalEf implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int efId;
    private String efEc;
    private String efMamas;
    private String efCueUte;
    private String efPelvis;
    private String efOdont;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public ConsultorioExtCarnetPerinatalEf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEf(int efId) {
        this.efId = efId;
    }

    public int getEfId() {
        return efId;
    }

    public void setEfId(int efId) {
        this.efId = efId;
    }

    public String getEfEc() {
        return efEc;
    }

    public void setEfEc(String efEc) {
        this.efEc = efEc;
    }

    public String getEfMamas() {
        return efMamas;
    }

    public void setEfMamas(String efMamas) {
        this.efMamas = efMamas;
    }

    public String getEfCueUte() {
        return efCueUte;
    }

    public void setEfCueUte(String efCueUte) {
        this.efCueUte = efCueUte;
    }

    public String getEfPelvis() {
        return efPelvis;
    }

    public void setEfPelvis(String efPelvis) {
        this.efPelvis = efPelvis;
    }

    public String getEfOdont() {
        return efOdont;
    }

    public void setEfOdont(String efOdont) {
        this.efOdont = efOdont;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
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

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEf[ efId=" + efId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }
    
}
