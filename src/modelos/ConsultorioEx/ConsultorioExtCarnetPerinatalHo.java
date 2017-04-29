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

public class ConsultorioExtCarnetPerinatalHo implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int hoId;
    private int id_cie10;
    private String hoHosp;
    private String hoFecha;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;

    public boolean mantenimientoConsultorioExtCarnetPerinatalFu(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_HO ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHoId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getHoHosp());
            cmd.setString(4, getHoFecha());
            cmd.setInt(5, getId_cie10());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalHo: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalHoID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 HO_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_HO \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY HO_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalHoID: " + ex.getMessage());
        }
        return cod;
    }   
    
    public ConsultorioExtCarnetPerinatalHo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalHo(int hoId) {
        this.hoId = hoId;
    }

    public int getHoId() {
        return hoId;
    }

    public void setHoId(int hoId) {
        this.hoId = hoId;
    }

    public String getHoHosp() {
        return hoHosp;
    }

    public void setHoHosp(String hoHosp) {
        this.hoHosp = hoHosp;
    }

    public String getHoFecha() {
        return hoFecha;
    }

    public void setHoFecha(String hoFecha) {
        this.hoFecha = hoFecha;
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
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalHo[ hoId=" + hoId + " ]";
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
