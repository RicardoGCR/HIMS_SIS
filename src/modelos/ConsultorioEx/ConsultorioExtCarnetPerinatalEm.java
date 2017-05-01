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
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

public class ConsultorioExtCarnetPerinatalEm implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int emId;
    private String emFecha;
    private int id_cie10;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalEm(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_EM ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getEmId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getEmFecha());
            cmd.setInt(4, getId_cie10());
            cmd.setString(5, getCodUsu());
            cmd.setString(6, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalEm: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalEmID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 EM_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_EM \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY EM_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalEmID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public ConsultorioExtCarnetPerinatalEm() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEm(int emId) {
        this.emId = emId;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public String getEmFecha() {
        return emFecha;
    }

    public void setEmFecha(String emFecha) {
        this.emFecha = emFecha;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEm[ emId=" + emId + " ]";
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

    /**
     * @return the id_cie10
     */
    public int getId_cie10() {
        return id_cie10;
    }

    /**
     * @param id_cie10 the id_cie10 to set
     */
    public void setId_cie10(int id_cie10) {
        this.id_cie10 = id_cie10;
    }
    
}
