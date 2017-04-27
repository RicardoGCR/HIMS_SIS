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
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
@Entity
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_AF")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalAf.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalAf c")})
public class ConsultorioExtCarnetPerinatalAf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AF_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private Long afId;
    @Column(name = "AF_NINGUNO")
    private Character afNinguno;
    @Column(name = "AF_ALERGIAS")
    private Character afAlergias;
    @Column(name = "AF_HIPERTENS")
    private Character afHipertens;
    @Column(name = "AF_EPILEPSIA")
    private Character afEpilepsia;
    @Column(name = "AF_DIABETES")
    private Character afDiabetes;
    @Column(name = "AF_ENF_CONGENITAS")
    private Character afEnfCongenitas;
    @Column(name = "AF_EMB_MULTIPLE")
    private Character afEmbMultiple;
    @Column(name = "AF_MALARIA")
    private Character afMalaria;
    @Column(name = "AF_HIPER_ARTERIAL")
    private Character afHiperArterial;
    @Column(name = "AF_HIPOTIROIDISMO")
    private Character afHipotiroidismo;
    @Column(name = "AF_NEOPLASICA")
    private Character afNeoplasica;
    @Column(name = "AF_TBC")
    private Character afTbc;
    @Column(name = "AF_OTROS")
    private String afOtros;
    @Column(name = "FECHA_ACTU")
    private String fechaActu;
    @Column(name = "HORA_ACTU")
    private String horaActu;
    @Column(name = "NOM_PC")
    private String nomPc;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "COD_USU")
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
