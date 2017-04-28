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
public class ConsultorioExtCarnetPerinatalAf implements Serializable {
    private static final long serialVersionUID = 1L;
    
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private Long afId;
    private Character afNinguno;
    private Character afAlergias;
    private Character afHipertens;
    private Character afEpilepsia;
    private Character afDiabetes;
    private Character afEnfCongenitas;
    private Character afEmbMultiple;
    private Character afMalaria;
    private Character afHiperArterial;
    private Character afHipotiroidismo;
    private Character afNeoplasica;
    private Character afTbc;
    private String afOtros;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public ConsultorioExtCarnetPerinatalAf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAf(Long afId) {
        this.afId = afId;
    }

    public Long getAfId() {
        return afId;
    }

    public void setAfId(Long afId) {
        this.afId = afId;
    }

    public Character getAfNinguno() {
        return afNinguno;
    }

    public void setAfNinguno(Character afNinguno) {
        this.afNinguno = afNinguno;
    }

    public Character getAfAlergias() {
        return afAlergias;
    }

    public void setAfAlergias(Character afAlergias) {
        this.afAlergias = afAlergias;
    }

    public Character getAfHipertens() {
        return afHipertens;
    }

    public void setAfHipertens(Character afHipertens) {
        this.afHipertens = afHipertens;
    }

    public Character getAfEpilepsia() {
        return afEpilepsia;
    }

    public void setAfEpilepsia(Character afEpilepsia) {
        this.afEpilepsia = afEpilepsia;
    }

    public Character getAfDiabetes() {
        return afDiabetes;
    }

    public void setAfDiabetes(Character afDiabetes) {
        this.afDiabetes = afDiabetes;
    }

    public Character getAfEnfCongenitas() {
        return afEnfCongenitas;
    }

    public void setAfEnfCongenitas(Character afEnfCongenitas) {
        this.afEnfCongenitas = afEnfCongenitas;
    }

    public Character getAfEmbMultiple() {
        return afEmbMultiple;
    }

    public void setAfEmbMultiple(Character afEmbMultiple) {
        this.afEmbMultiple = afEmbMultiple;
    }

    public Character getAfMalaria() {
        return afMalaria;
    }

    public void setAfMalaria(Character afMalaria) {
        this.afMalaria = afMalaria;
    }

    public Character getAfHiperArterial() {
        return afHiperArterial;
    }

    public void setAfHiperArterial(Character afHiperArterial) {
        this.afHiperArterial = afHiperArterial;
    }

    public Character getAfHipotiroidismo() {
        return afHipotiroidismo;
    }

    public void setAfHipotiroidismo(Character afHipotiroidismo) {
        this.afHipotiroidismo = afHipotiroidismo;
    }

    public Character getAfNeoplasica() {
        return afNeoplasica;
    }

    public void setAfNeoplasica(Character afNeoplasica) {
        this.afNeoplasica = afNeoplasica;
    }

    public Character getAfTbc() {
        return afTbc;
    }

    public void setAfTbc(Character afTbc) {
        this.afTbc = afTbc;
    }

    public String getAfOtros() {
        return afOtros;
    }

    public void setAfOtros(String afOtros) {
        this.afOtros = afOtros;
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
    public int hashCode() {
        int hash = 0;
        hash += (afId != null ? afId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtCarnetPerinatalAf)) {
            return false;
        }
        ConsultorioExtCarnetPerinatalAf other = (ConsultorioExtCarnetPerinatalAf) object;
        if ((this.afId == null && other.afId != null) || (this.afId != null && !this.afId.equals(other.afId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAf[ afId=" + afId + " ]";
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
