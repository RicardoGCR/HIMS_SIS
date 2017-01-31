/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionMotivoAltas {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int ma_id;
    private String ma_descripcion;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String cod_usu;

    public boolean mantenimientoHospitalizacionMotivoAltas(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_MOTIVO_ALTAs_MANTENIMIENTO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getMa_descripcion());
            cmd.setString(2, getCod_usu());
            cmd.setInt(3, getMa_id());
            cmd.setString(4, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionMotivoAltas: " + ex.getMessage());
        }
        return resp;
    }
    
    public HospitalizacionMotivoAltas()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
     * @return the ma_id
     */
    public int getMa_id() {
        return ma_id;
    }

    /**
     * @param ma_id the ma_id to set
     */
    public void setMa_id(int ma_id) {
        this.ma_id = ma_id;
    }

    /**
     * @return the ma_descripcion
     */
    public String getMa_descripcion() {
        return ma_descripcion;
    }

    /**
     * @param ma_descripcion the ma_descripcion to set
     */
    public void setMa_descripcion(String ma_descripcion) {
        this.ma_descripcion = ma_descripcion;
    }

    /**
     * @return the fecha_actu
     */
    public String getFecha_actu() {
        return fecha_actu;
    }

    /**
     * @param fecha_actu the fecha_actu to set
     */
    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    /**
     * @return the hora_actu
     */
    public String getHora_actu() {
        return hora_actu;
    }

    /**
     * @param hora_actu the hora_actu to set
     */
    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    /**
     * @return the nom_pc
     */
    public String getNom_pc() {
        return nom_pc;
    }

    /**
     * @param nom_pc the nom_pc to set
     */
    public void setNom_pc(String nom_pc) {
        this.nom_pc = nom_pc;
    }

    /**
     * @return the cod_usu
     */
    public String getCod_usu() {
        return cod_usu;
    }

    /**
     * @param cod_usu the cod_usu to set
     */
    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }
}
