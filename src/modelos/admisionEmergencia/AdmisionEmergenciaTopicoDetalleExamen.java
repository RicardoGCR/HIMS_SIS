/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.admisionEmergencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author Yamila Rocca Ruiz
 */
public class AdmisionEmergenciaTopicoDetalleExamen {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String dettop_id;
    private String id_topico;
    private String cod_exa_ana;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String usu_cod;
    
    public boolean insertarDetalleExamen(int id_preventa)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CAJA_PREVENTA_DETALLE_INSERTAR_EXAMEN ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setString(2, getCod_exa_ana());
            cmd.setString(3, getUsu_cod());
            cmd.setInt(4,id_preventa);
            //cmd.setInt(4,id_preventa);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("insertarDetalleExamen: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarDetalleExamen()
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_TOPICO_DETALLE_EXAMEN_MODIFICAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setString(2, getCod_exa_ana());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: modificarDetalleExamen: " + ex.getMessage());
        }
        return resp;
    }
    
    public AdmisionEmergenciaTopicoDetalleExamen()
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
     * @return the dettop_id
     */
    public String getDettop_id() {
        return dettop_id;
    }

    /**
     * @param dettop_id the dettop_id to set
     */
    public void setDettop_id(String dettop_id) {
        this.dettop_id = dettop_id;
    }

    /**
     * @return the id_topico
     */
    public String getId_topico() {
        return id_topico;
    }

    /**
     * @param id_topico the id_topico to set
     */
    public void setId_topico(String id_topico) {
        this.id_topico = id_topico;
    }

    /**
     * @return the cod_exa_ana
     */
    public String getCod_exa_ana() {
        return cod_exa_ana;
    }

    /**
     * @param cod_exa_ana the cod_exa_ana to set
     */
    public void setCod_exa_ana(String cod_exa_ana) {
        this.cod_exa_ana = cod_exa_ana;
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
     * @return the usu_cod
     */
    public String getUsu_cod() {
        return usu_cod;
    }

    /**
     * @param usu_cod the usu_cod to set
     */
    public void setUsu_cod(String usu_cod) {
        this.usu_cod = usu_cod;
    }
}
