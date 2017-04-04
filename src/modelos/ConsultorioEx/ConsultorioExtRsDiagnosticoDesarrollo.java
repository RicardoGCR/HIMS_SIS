/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import net.sf.jasperreports.components.table.Column;
import servicios.Conexion;

/**
 *
 * @author PC02
 */

public class ConsultorioExtRsDiagnosticoDesarrollo implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int ddId;
    private int rs_id;
    private String dn1Fecha;
    private String dn1Cie10;
    private String dn1Fua;
    private String dn2Fecha;
    private String dn2Cie10;
    private String dn2Fua;
    private String dn3Fecha;
    private String dn3Cie10;
    private String dn3Fua;
    private String dn4Fecha;
    private String dn4Cie10;
    private String dn4Fua;
    private String dn5Fecha;
    private String dn5Cie10;
    private String dn5Fua;
    private String dn6Fecha;
    private String dn6Cie10;
    private String dn6Fua;
    private String dn7Fecha;
    private String dn7Cie10;
    private String dn7Fua;
    private String dn8Fecha;
    private String dn8Cie10;
    private String dn8Fua;
    private String dn9Fecha;
    private String dn9Cie10;
    private String dn9Fua;
    private String dn10Fecha;
    private String dn10Cie10;
    private String dn10Fua;
    private String dn11Fecha;
    private String dn11Cie10;
    private String dn11Fua;
    private String dn12Fecha;
    private String dn12Cie10;
    private String dn12Fua;

    public ConsultorioExtRsDiagnosticoDesarrollo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsDiagnosticoDesarrollo(int ddId) {
        this.ddId = ddId;
    }

    public int getDdId() {
        return ddId;
    }

    public void setDdId(int ddId) {
        this.ddId = ddId;
    }

    public String getDn1Fecha() {
        return dn1Fecha;
    }

    public void setDn1Fecha(String dn1Fecha) {
        this.dn1Fecha = dn1Fecha;
    }

    public String getDn1Cie10() {
        return dn1Cie10;
    }

    public void setDn1Cie10(String dn1Cie10) {
        this.dn1Cie10 = dn1Cie10;
    }

    public String getDn1Fua() {
        return dn1Fua;
    }

    public void setDn1Fua(String dn1Fua) {
        this.dn1Fua = dn1Fua;
    }

    public String getDn2Fecha() {
        return dn2Fecha;
    }

    public void setDn2Fecha(String dn2Fecha) {
        this.dn2Fecha = dn2Fecha;
    }

    public String getDn2Cie10() {
        return dn2Cie10;
    }

    public void setDn2Cie10(String dn2Cie10) {
        this.dn2Cie10 = dn2Cie10;
    }

    public String getDn2Fua() {
        return dn2Fua;
    }

    public void setDn2Fua(String dn2Fua) {
        this.dn2Fua = dn2Fua;
    }

    public String getDn3Fecha() {
        return dn3Fecha;
    }

    public void setDn3Fecha(String dn3Fecha) {
        this.dn3Fecha = dn3Fecha;
    }

    public String getDn3Cie10() {
        return dn3Cie10;
    }

    public void setDn3Cie10(String dn3Cie10) {
        this.dn3Cie10 = dn3Cie10;
    }

    public String getDn3Fua() {
        return dn3Fua;
    }

    public void setDn3Fua(String dn3Fua) {
        this.dn3Fua = dn3Fua;
    }

    public String getDn4Fecha() {
        return dn4Fecha;
    }

    public void setDn4Fecha(String dn4Fecha) {
        this.dn4Fecha = dn4Fecha;
    }

    public String getDn4Cie10() {
        return dn4Cie10;
    }

    public void setDn4Cie10(String dn4Cie10) {
        this.dn4Cie10 = dn4Cie10;
    }

    public String getDn4Fua() {
        return dn4Fua;
    }

    public void setDn4Fua(String dn4Fua) {
        this.dn4Fua = dn4Fua;
    }

    public String getDn5Fecha() {
        return dn5Fecha;
    }

    public void setDn5Fecha(String dn5Fecha) {
        this.dn5Fecha = dn5Fecha;
    }

    public String getDn5Cie10() {
        return dn5Cie10;
    }

    public void setDn5Cie10(String dn5Cie10) {
        this.dn5Cie10 = dn5Cie10;
    }

    public String getDn5Fua() {
        return dn5Fua;
    }

    public void setDn5Fua(String dn5Fua) {
        this.dn5Fua = dn5Fua;
    }

    public String getDn6Fecha() {
        return dn6Fecha;
    }

    public void setDn6Fecha(String dn6Fecha) {
        this.dn6Fecha = dn6Fecha;
    }

    public String getDn6Cie10() {
        return dn6Cie10;
    }

    public void setDn6Cie10(String dn6Cie10) {
        this.dn6Cie10 = dn6Cie10;
    }

    public String getDn6Fua() {
        return dn6Fua;
    }

    public void setDn6Fua(String dn6Fua) {
        this.dn6Fua = dn6Fua;
    }

    public String getDn7Fecha() {
        return dn7Fecha;
    }

    public void setDn7Fecha(String dn7Fecha) {
        this.dn7Fecha = dn7Fecha;
    }

    public String getDn7Cie10() {
        return dn7Cie10;
    }

    public void setDn7Cie10(String dn7Cie10) {
        this.dn7Cie10 = dn7Cie10;
    }

    public String getDn7Fua() {
        return dn7Fua;
    }

    public void setDn7Fua(String dn7Fua) {
        this.dn7Fua = dn7Fua;
    }

    public String getDn8Fecha() {
        return dn8Fecha;
    }

    public void setDn8Fecha(String dn8Fecha) {
        this.dn8Fecha = dn8Fecha;
    }

    public String getDn8Cie10() {
        return dn8Cie10;
    }

    public void setDn8Cie10(String dn8Cie10) {
        this.dn8Cie10 = dn8Cie10;
    }

    public String getDn8Fua() {
        return dn8Fua;
    }

    public void setDn8Fua(String dn8Fua) {
        this.dn8Fua = dn8Fua;
    }

    public String getDn9Fecha() {
        return dn9Fecha;
    }

    public void setDn9Fecha(String dn9Fecha) {
        this.dn9Fecha = dn9Fecha;
    }

    public String getDn9Cie10() {
        return dn9Cie10;
    }

    public void setDn9Cie10(String dn9Cie10) {
        this.dn9Cie10 = dn9Cie10;
    }

    public String getDn9Fua() {
        return dn9Fua;
    }

    public void setDn9Fua(String dn9Fua) {
        this.dn9Fua = dn9Fua;
    }

    public String getDn10Fecha() {
        return dn10Fecha;
    }

    public void setDn10Fecha(String dn10Fecha) {
        this.dn10Fecha = dn10Fecha;
    }

    public String getDn10Cie10() {
        return dn10Cie10;
    }

    public void setDn10Cie10(String dn10Cie10) {
        this.dn10Cie10 = dn10Cie10;
    }

    public String getDn10Fua() {
        return dn10Fua;
    }

    public void setDn10Fua(String dn10Fua) {
        this.dn10Fua = dn10Fua;
    }

    public String getDn11Fecha() {
        return dn11Fecha;
    }

    public void setDn11Fecha(String dn11Fecha) {
        this.dn11Fecha = dn11Fecha;
    }

    public String getDn11Cie10() {
        return dn11Cie10;
    }

    public void setDn11Cie10(String dn11Cie10) {
        this.dn11Cie10 = dn11Cie10;
    }

    public String getDn11Fua() {
        return dn11Fua;
    }

    public void setDn11Fua(String dn11Fua) {
        this.dn11Fua = dn11Fua;
    }

    public String getDn12Fecha() {
        return dn12Fecha;
    }

    public void setDn12Fecha(String dn12Fecha) {
        this.dn12Fecha = dn12Fecha;
    }

    public String getDn12Cie10() {
        return dn12Cie10;
    }

    public void setDn12Cie10(String dn12Cie10) {
        this.dn12Cie10 = dn12Cie10;
    }

    public String getDn12Fua() {
        return dn12Fua;
    }

    public void setDn12Fua(String dn12Fua) {
        this.dn12Fua = dn12Fua;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoDesarrollo[ ddId=" + ddId + " ]";
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
