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
@Table(name = "CONSULTORIO_EXT_CARNET_PERINATAL_AP")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtCarnetPerinatalAp.findAll", query = "SELECT c FROM ConsultorioExtCarnetPerinatalAp c")})
public class ConsultorioExtCarnetPerinatalAp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AP_ID")
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int apId;
    @Column(name = "AP_1")
    private Character ap1;
    @Column(name = "AP_2")
    private Character ap2;
    @Column(name = "AP_3")
    private Character ap3;
    @Column(name = "AP_4")
    private Character ap4;
    @Column(name = "AP_5")
    private Character ap5;
    @Column(name = "AP_6")
    private Character ap6;
    @Column(name = "AP_7")
    private Character ap7;
    @Column(name = "AP_8")
    private Character ap8;
    @Column(name = "AP_9")
    private Character ap9;
    @Column(name = "AP_10")
    private Character ap10;
    @Column(name = "AP_11")
    private Character ap11;
    @Column(name = "AP_12")
    private Character ap12;
    @Column(name = "AP_13")
    private Character ap13;
    @Column(name = "AP_14")
    private Character ap14;
    @Column(name = "AP_15")
    private Character ap15;
    @Column(name = "AP_16")
    private Character ap16;
    @Column(name = "AP_17")
    private Character ap17;
    @Column(name = "AP_18")
    private Character ap18;
    @Column(name = "AP_19")
    private Character ap19;
    @Column(name = "AP_20")
    private Character ap20;
    @Column(name = "AP_21")
    private Character ap21;
    @Column(name = "AP_22")
    private Character ap22;
    @Column(name = "AP_23")
    private Character ap23;
    @Column(name = "AP_24")
    private Character ap24;
    @Column(name = "AP_25")
    private Character ap25;
    @Column(name = "AP_26")
    private Character ap26;
    @Column(name = "AP_27")
    private Character ap27;
    @Column(name = "AP_28")
    private Character ap28;
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

    public ConsultorioExtCarnetPerinatalAp() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAp(int apId) {
        this.apId = apId;
    }

    public int getApId() {
        return apId;
    }

    public void setApId(int apId) {
        this.apId = apId;
    }

    public Character getAp1() {
        return ap1;
    }

    public void setAp1(Character ap1) {
        this.ap1 = ap1;
    }

    public Character getAp2() {
        return ap2;
    }

    public void setAp2(Character ap2) {
        this.ap2 = ap2;
    }

    public Character getAp3() {
        return ap3;
    }

    public void setAp3(Character ap3) {
        this.ap3 = ap3;
    }

    public Character getAp4() {
        return ap4;
    }

    public void setAp4(Character ap4) {
        this.ap4 = ap4;
    }

    public Character getAp5() {
        return ap5;
    }

    public void setAp5(Character ap5) {
        this.ap5 = ap5;
    }

    public Character getAp6() {
        return ap6;
    }

    public void setAp6(Character ap6) {
        this.ap6 = ap6;
    }

    public Character getAp7() {
        return ap7;
    }

    public void setAp7(Character ap7) {
        this.ap7 = ap7;
    }

    public Character getAp8() {
        return ap8;
    }

    public void setAp8(Character ap8) {
        this.ap8 = ap8;
    }

    public Character getAp9() {
        return ap9;
    }

    public void setAp9(Character ap9) {
        this.ap9 = ap9;
    }

    public Character getAp10() {
        return ap10;
    }

    public void setAp10(Character ap10) {
        this.ap10 = ap10;
    }

    public Character getAp11() {
        return ap11;
    }

    public void setAp11(Character ap11) {
        this.ap11 = ap11;
    }

    public Character getAp12() {
        return ap12;
    }

    public void setAp12(Character ap12) {
        this.ap12 = ap12;
    }

    public Character getAp13() {
        return ap13;
    }

    public void setAp13(Character ap13) {
        this.ap13 = ap13;
    }

    public Character getAp14() {
        return ap14;
    }

    public void setAp14(Character ap14) {
        this.ap14 = ap14;
    }

    public Character getAp15() {
        return ap15;
    }

    public void setAp15(Character ap15) {
        this.ap15 = ap15;
    }

    public Character getAp16() {
        return ap16;
    }

    public void setAp16(Character ap16) {
        this.ap16 = ap16;
    }

    public Character getAp17() {
        return ap17;
    }

    public void setAp17(Character ap17) {
        this.ap17 = ap17;
    }

    public Character getAp18() {
        return ap18;
    }

    public void setAp18(Character ap18) {
        this.ap18 = ap18;
    }

    public Character getAp19() {
        return ap19;
    }

    public void setAp19(Character ap19) {
        this.ap19 = ap19;
    }

    public Character getAp20() {
        return ap20;
    }

    public void setAp20(Character ap20) {
        this.ap20 = ap20;
    }

    public Character getAp21() {
        return ap21;
    }

    public void setAp21(Character ap21) {
        this.ap21 = ap21;
    }

    public Character getAp22() {
        return ap22;
    }

    public void setAp22(Character ap22) {
        this.ap22 = ap22;
    }

    public Character getAp23() {
        return ap23;
    }

    public void setAp23(Character ap23) {
        this.ap23 = ap23;
    }

    public Character getAp24() {
        return ap24;
    }

    public void setAp24(Character ap24) {
        this.ap24 = ap24;
    }

    public Character getAp25() {
        return ap25;
    }

    public void setAp25(Character ap25) {
        this.ap25 = ap25;
    }

    public Character getAp26() {
        return ap26;
    }

    public void setAp26(Character ap26) {
        this.ap26 = ap26;
    }

    public Character getAp27() {
        return ap27;
    }

    public void setAp27(Character ap27) {
        this.ap27 = ap27;
    }

    public Character getAp28() {
        return ap28;
    }

    public void setAp28(Character ap28) {
        this.ap28 = ap28;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAp[ apId=" + apId + " ]";
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
