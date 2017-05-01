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

/**
 *
 * @author PC02
 */
public class ConsultorioExtCarnetPerinatalAtencionPrenatal implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int apId;
    private String apEdadSem;
    private String apPesoMadre;
    private String apTemp;
    private String apPa;
    private String apPm;
    private String apAu;
    private String apSit;
    private String apPres;
    private String apPosicion;
    private String apFcf;
    private String apMovFet;
    private String apProtei;
    private String apEdema;
    private String apRo;
    private String apExPezon;
    private String apIndicFierro;
    private String apIndicCalcio;
    private String apIndicAcFolico;
    private String apOrientConsej;
    private String apEgEco;
    private String apPerfilBio;
    private String apCita;
    private String apVisitDomic;
    private String apPlanParto;
    private String apEstabAtencion;
    private String apRespAtencion;
    private String apSis;
    private Character apAtencion;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public ConsultorioExtCarnetPerinatalAtencionPrenatal() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalAtencionPrenatal(int apId) {
        this.apId = apId;
    }

    public int getApId() {
        return apId;
    }

    public void setApId(int apId) {
        this.apId = apId;
    }

    public String getApEdadSem() {
        return apEdadSem;
    }

    public void setApEdadSem(String apEdadSem) {
        this.apEdadSem = apEdadSem;
    }

    public String getApPesoMadre() {
        return apPesoMadre;
    }

    public void setApPesoMadre(String apPesoMadre) {
        this.apPesoMadre = apPesoMadre;
    }

    public String getApTemp() {
        return apTemp;
    }

    public void setApTemp(String apTemp) {
        this.apTemp = apTemp;
    }

    public String getApPa() {
        return apPa;
    }

    public void setApPa(String apPa) {
        this.apPa = apPa;
    }

    public String getApPm() {
        return apPm;
    }

    public void setApPm(String apPm) {
        this.apPm = apPm;
    }

    public String getApAu() {
        return apAu;
    }

    public void setApAu(String apAu) {
        this.apAu = apAu;
    }

    public String getApSit() {
        return apSit;
    }

    public void setApSit(String apSit) {
        this.apSit = apSit;
    }

    public String getApPres() {
        return apPres;
    }

    public void setApPres(String apPres) {
        this.apPres = apPres;
    }

    public String getApPosicion() {
        return apPosicion;
    }

    public void setApPosicion(String apPosicion) {
        this.apPosicion = apPosicion;
    }

    public String getApFcf() {
        return apFcf;
    }

    public void setApFcf(String apFcf) {
        this.apFcf = apFcf;
    }

    public String getApMovFet() {
        return apMovFet;
    }

    public void setApMovFet(String apMovFet) {
        this.apMovFet = apMovFet;
    }

    public String getApProtei() {
        return apProtei;
    }

    public void setApProtei(String apProtei) {
        this.apProtei = apProtei;
    }

    public String getApEdema() {
        return apEdema;
    }

    public void setApEdema(String apEdema) {
        this.apEdema = apEdema;
    }

    public String getApRo() {
        return apRo;
    }

    public void setApRo(String apRo) {
        this.apRo = apRo;
    }

    public String getApExPezon() {
        return apExPezon;
    }

    public void setApExPezon(String apExPezon) {
        this.apExPezon = apExPezon;
    }

    public String getApIndicFierro() {
        return apIndicFierro;
    }

    public void setApIndicFierro(String apIndicFierro) {
        this.apIndicFierro = apIndicFierro;
    }

    public String getApIndicCalcio() {
        return apIndicCalcio;
    }

    public void setApIndicCalcio(String apIndicCalcio) {
        this.apIndicCalcio = apIndicCalcio;
    }

    public String getApIndicAcFolico() {
        return apIndicAcFolico;
    }

    public void setApIndicAcFolico(String apIndicAcFolico) {
        this.apIndicAcFolico = apIndicAcFolico;
    }

    public String getApOrientConsej() {
        return apOrientConsej;
    }

    public void setApOrientConsej(String apOrientConsej) {
        this.apOrientConsej = apOrientConsej;
    }

    public String getApEgEco() {
        return apEgEco;
    }

    public void setApEgEco(String apEgEco) {
        this.apEgEco = apEgEco;
    }

    public String getApPerfilBio() {
        return apPerfilBio;
    }

    public void setApPerfilBio(String apPerfilBio) {
        this.apPerfilBio = apPerfilBio;
    }

    public String getApCita() {
        return apCita;
    }

    public void setApCita(String apCita) {
        this.apCita = apCita;
    }

    public String getApVisitDomic() {
        return apVisitDomic;
    }

    public void setApVisitDomic(String apVisitDomic) {
        this.apVisitDomic = apVisitDomic;
    }

    public String getApPlanParto() {
        return apPlanParto;
    }

    public void setApPlanParto(String apPlanParto) {
        this.apPlanParto = apPlanParto;
    }

    public String getApEstabAtencion() {
        return apEstabAtencion;
    }

    public void setApEstabAtencion(String apEstabAtencion) {
        this.apEstabAtencion = apEstabAtencion;
    }

    public String getApRespAtencion() {
        return apRespAtencion;
    }

    public void setApRespAtencion(String apRespAtencion) {
        this.apRespAtencion = apRespAtencion;
    }

    public String getApSis() {
        return apSis;
    }

    public void setApSis(String apSis) {
        this.apSis = apSis;
    }

    public Character getApAtencion() {
        return apAtencion;
    }

    public void setApAtencion(Character apAtencion) {
        this.apAtencion = apAtencion;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAtencionPrenatal[ apId=" + apId + " ]";
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
