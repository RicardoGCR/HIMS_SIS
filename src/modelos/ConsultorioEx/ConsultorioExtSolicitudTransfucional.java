/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;


public class ConsultorioExtSolicitudTransfucional implements Serializable {
    private static final long serialVersionUID = 1L;
    private int stId;
    private String stTransPrevia;
    private String stTransPreviaReac;
    private String stEmbPrevio;
    private String stAbortos;
    private String stIncMf;
    private int idCie10;
    private String stHc;
    private String stHto;
    private String stPlaq;
    private String stSangt;
    private String stPaqGl;
    private String stPlasF;
    private String stCrioprec;
    private String stPlasma;
    private String stPlaquetas;
    private String stUnidPed;
    private String stOtros;
    private String stCantOtros;
    private String stFechaProg;
    private String stHoraprog;
    private String stRequisito;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String estado;
    private String id_hc;
    private String rh;
    private int ar_id;
    private String cod_usuario;
    private Connection cn;
    DefaultTableModel m;
    Conexion con = new Conexion();

    public boolean mantenimientoConsultorioSolicitudTransfucional(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_SOLICITUD_TRANSFUCIONAL ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getStId());
            cmd.setString(2, getId_hc());
            cmd.setInt(3, getAr_id());
            cmd.setString(4, getStTransPrevia());
            cmd.setString(5, getStTransPreviaReac());
            cmd.setString(6, getStEmbPrevio());
            cmd.setString(7, getStAbortos());
            cmd.setString(8, getStIncMf());
            cmd.setInt(9, getIdCie10());
            cmd.setString(10, getStHc());
            cmd.setString(11, getStHto());
            cmd.setString(12, getStPlaq());
            cmd.setString(13, getStSangt());
            cmd.setString(14, getStPaqGl());
            cmd.setString(15, getStPlasF());
            cmd.setString(16, getStCrioprec());
            cmd.setString(17, getStPlasma());
            cmd.setString(18, getStPlaquetas());
            cmd.setString(19, getStUnidPed());
            cmd.setString(20, getStOtros());
            cmd.setString(21, getStCantOtros());
            cmd.setString(22, getStFechaProg());
            cmd.setString(23, getStHoraprog());
            cmd.setString(24, getStRequisito());
            cmd.setString(25, getRh());
            cmd.setString(26, getCod_usuario());
            cmd.setString(27, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioSolicitudTransfucional: " + ex.getMessage());
        }
        return resp;
    }
    
    public ConsultorioExtSolicitudTransfucional() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtSolicitudTransfucional(int stId) {
        this.stId = stId;
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStTransPrevia() {
        return stTransPrevia;
    }

    public void setStTransPrevia(String stTransPrevia) {
        this.stTransPrevia = stTransPrevia;
    }

    public String getStTransPreviaReac() {
        return stTransPreviaReac;
    }

    public void setStTransPreviaReac(String stTransPreviaReac) {
        this.stTransPreviaReac = stTransPreviaReac;
    }

    public String getStEmbPrevio() {
        return stEmbPrevio;
    }

    public void setStEmbPrevio(String stEmbPrevio) {
        this.stEmbPrevio = stEmbPrevio;
    }

    public String getStAbortos() {
        return stAbortos;
    }

    public void setStAbortos(String stAbortos) {
        this.stAbortos = stAbortos;
    }

    public String getStIncMf() {
        return stIncMf;
    }

    public void setStIncMf(String stIncMf) {
        this.stIncMf = stIncMf;
    }

    public int getIdCie10() {
        return idCie10;
    }

    public void setIdCie10(int idCie10) {
        this.idCie10 = idCie10;
    }

    public String getStHc() {
        return stHc;
    }

    public void setStHc(String stHc) {
        this.stHc = stHc;
    }

    public String getStHto() {
        return stHto;
    }

    public void setStHto(String stHto) {
        this.stHto = stHto;
    }

    public String getStPlaq() {
        return stPlaq;
    }

    public void setStPlaq(String stPlaq) {
        this.stPlaq = stPlaq;
    }

    public String getStSangt() {
        return stSangt;
    }

    public void setStSangt(String stSangt) {
        this.stSangt = stSangt;
    }

    public String getStPaqGl() {
        return stPaqGl;
    }

    public void setStPaqGl(String stPaqGl) {
        this.stPaqGl = stPaqGl;
    }

    public String getStPlasF() {
        return stPlasF;
    }

    public void setStPlasF(String stPlasF) {
        this.stPlasF = stPlasF;
    }

    public String getStCrioprec() {
        return stCrioprec;
    }

    public void setStCrioprec(String stCrioprec) {
        this.stCrioprec = stCrioprec;
    }

    public String getStPlasma() {
        return stPlasma;
    }

    public void setStPlasma(String stPlasma) {
        this.stPlasma = stPlasma;
    }

    public String getStPlaquetas() {
        return stPlaquetas;
    }

    public void setStPlaquetas(String stPlaquetas) {
        this.stPlaquetas = stPlaquetas;
    }

    public String getStUnidPed() {
        return stUnidPed;
    }

    public void setStUnidPed(String stUnidPed) {
        this.stUnidPed = stUnidPed;
    }

    public String getStOtros() {
        return stOtros;
    }

    public void setStOtros(String stOtros) {
        this.stOtros = stOtros;
    }

    public String getStCantOtros() {
        return stCantOtros;
    }

    public void setStCantOtros(String stCantOtros) {
        this.stCantOtros = stCantOtros;
    }

    public String getStFechaProg() {
        return stFechaProg;
    }

    public void setStFechaProg(String stFechaProg) {
        this.stFechaProg = stFechaProg;
    }

    public String getStHoraprog() {
        return stHoraprog;
    }

    public void setStHoraprog(String stHoraprog) {
        this.stHoraprog = stHoraprog;
    }

    public String getStRequisito() {
        return stRequisito;
    }

    public void setStRequisito(String stRequisito) {
        this.stRequisito = stRequisito;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtSolicitudTransfucional[ stId=" + stId + " ]";
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the ar_id
     */
    public int getAr_id() {
        return ar_id;
    }

    /**
     * @param ar_id the ar_id to set
     */
    public void setAr_id(int ar_id) {
        this.ar_id = ar_id;
    }

    /**
     * @return the cod_usuario
     */
    public String getCod_usuario() {
        return cod_usuario;
    }

    /**
     * @param cod_usuario the cod_usuario to set
     */
    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
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
     * @return the rh
     */
    public String getRh() {
        return rh;
    }

    /**
     * @param rh the rh to set
     */
    public void setRh(String rh) {
        this.rh = rh;
    }
    
}
