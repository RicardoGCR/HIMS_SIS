/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

public class ConsultorioExtCarnetPerinatalVp implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int vpId;
    private String vpRubeola;
    private String vpHepatitis;
    private String vpPapiloma;
    private String vpFiebre;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalVp(String tipo,String triaje)
        {
        boolean resp = false;
        try{
            String sql = "[CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_VP] ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getVpId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getVpRubeola());
            cmd.setString(4, getVpHepatitis());
            cmd.setString(5, getVpPapiloma());
            cmd.setString(6, getVpFiebre());
            cmd.setString(7, getCodUsu());
            cmd.setString(8, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalVp: " + ex.getMessage());
        }
        return resp;
    }
    
    public ConsultorioExtCarnetPerinatalVp() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalVp(int vpId) {
        this.vpId = vpId;
    }

    public int getVpId() {
        return vpId;
    }

    public void setVpId(int vpId) {
        this.vpId = vpId;
    }

    public String getVpRubeola() {
        return vpRubeola;
    }

    public void setVpRubeola(String vpRubeola) {
        this.vpRubeola = vpRubeola;
    }

    public String getVpHepatitis() {
        return vpHepatitis;
    }

    public void setVpHepatitis(String vpHepatitis) {
        this.vpHepatitis = vpHepatitis;
    }

    public String getVpPapiloma() {
        return vpPapiloma;
    }

    public void setVpPapiloma(String vpPapiloma) {
        this.vpPapiloma = vpPapiloma;
    }

    public String getVpFiebre() {
        return vpFiebre;
    }

    public void setVpFiebre(String vpFiebre) {
        this.vpFiebre = vpFiebre;
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
