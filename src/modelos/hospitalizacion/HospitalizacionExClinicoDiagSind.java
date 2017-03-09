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
public class HospitalizacionExClinicoDiagSind {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int id;
    private int hec_id;
    private int id_cie10;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String estado;
    private String cod_usu;
    
    public boolean insertarDiagSind(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_EXAMEN_CLINICO_DIAG_DEFI_SINDROMICO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getHec_id());
            cmd.setInt(2, getId_cie10());
            cmd.setString(3, getCod_usu());
            cmd.setString(4, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionExClinico: " + ex.getMessage());
        }
        return resp;
    }
    
    public HospitalizacionExClinicoDiagSind()
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the hec_id
     */
    public int getHec_id() {
        return hec_id;
    }

    /**
     * @param hec_id the hec_id to set
     */
    public void setHec_id(int hec_id) {
        this.hec_id = hec_id;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
