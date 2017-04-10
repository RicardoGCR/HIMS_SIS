/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@Table(name = "CONSULTORIO_EXT_ORDENPCR")
@NamedQueries({
    @NamedQuery(name = "ConsultorioExtOrdenpcr.findAll", query = "SELECT c FROM ConsultorioExtOrdenpcr c")})
public class ConsultorioExtOrdenpcr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    DefaultTableModel m;
    private Conexion con = new Conexion();
    private Connection cn;
    private int ceoId;
    private String ceoDiresa;
    private String ceoEess;
    private String ceoFechSolicitud;
    private String ceoMadre;
    private String ceoDniMadre;
    private String ceoTipSangt;
    private String ceoTipTpf;
    private String ceoPcr1;
    private String ceoPcr2;
    private String ceoPcr3;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String hcNino;
    private String codUsu;

    public boolean mantenimientoConsultorioOrdenpcr(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_ORDENPCR ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCeoId());
            cmd.setString(2, getCeoDiresa());
            cmd.setString(3, getCeoEess());
            cmd.setString(4, getCeoFechSolicitud());
            cmd.setString(5, getCeoMadre());
            cmd.setString(6, getCeoDniMadre());
            cmd.setString(7, getHcNino());
            cmd.setString(8, getCeoTipSangt());
            cmd.setString(9, getCeoTipTpf());
            cmd.setString(10, getCeoPcr1());
            cmd.setString(11, getCeoPcr2());
            cmd.setString(12, getCeoPcr3());
            cmd.setString(13, getCodUsu());
            cmd.setString(14, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioOrdenpcr: " + ex.getMessage());
        }
        return resp;
    }
    
    public String ordenpcrID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 CEO_ID\n" +
                        "FROM CONSULTORIO_EXT_ORDENPCR \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY CEO_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("ordenpcrID: " + ex.getMessage());
        }
        return cod;
    }
    
    public ConsultorioExtOrdenpcr() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtOrdenpcr(int ceoId) {
        this.ceoId = ceoId;
    }

    public int getCeoId() {
        return ceoId;
    }

    public void setCeoId(int ceoId) {
        this.ceoId = ceoId;
    }

    public String getCeoDiresa() {
        return ceoDiresa;
    }

    public void setCeoDiresa(String ceoDiresa) {
        this.ceoDiresa = ceoDiresa;
    }

    public String getCeoEess() {
        return ceoEess;
    }

    public void setCeoEess(String ceoEess) {
        this.ceoEess = ceoEess;
    }

    public String getCeoFechSolicitud() {
        return ceoFechSolicitud;
    }

    public void setCeoFechSolicitud(String ceoFechSolicitud) {
        this.ceoFechSolicitud = ceoFechSolicitud;
    }

    public String getCeoMadre() {
        return ceoMadre;
    }

    public void setCeoMadre(String ceoMadre) {
        this.ceoMadre = ceoMadre;
    }

    public String getCeoDniMadre() {
        return ceoDniMadre;
    }

    public void setCeoDniMadre(String ceoDniMadre) {
        this.ceoDniMadre = ceoDniMadre;
    }

    public String getCeoTipSangt() {
        return ceoTipSangt;
    }

    public void setCeoTipSangt(String ceoTipSangt) {
        this.ceoTipSangt = ceoTipSangt;
    }

    public String getCeoTipTpf() {
        return ceoTipTpf;
    }

    public void setCeoTipTpf(String ceoTipTpf) {
        this.ceoTipTpf = ceoTipTpf;
    }

    public String getCeoPcr1() {
        return ceoPcr1;
    }

    public void setCeoPcr1(String ceoPcr1) {
        this.ceoPcr1 = ceoPcr1;
    }

    public String getCeoPcr2() {
        return ceoPcr2;
    }

    public void setCeoPcr2(String ceoPcr2) {
        this.ceoPcr2 = ceoPcr2;
    }

    public String getCeoPcr3() {
        return ceoPcr3;
    }

    public void setCeoPcr3(String ceoPcr3) {
        this.ceoPcr3 = ceoPcr3;
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

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtOrdenpcr[ ceoId=" + ceoId + " ]";
    }

    /**
     * @return the hcNino
     */
    public String getHcNino() {
        return hcNino;
    }

    /**
     * @param hcNino the hcNino to set
     */
    public void setHcNino(String hcNino) {
        this.hcNino = hcNino;
    }

    /**
     * @return the codUsu
     */
    public String getCodUsu() {
        return codUsu;
    }

    /**
     * @param codUsu the codUsu to set
     */
    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    /**
     * @return the con
     */
    public Conexion getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Conexion con) {
        this.con = con;
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
    
}
