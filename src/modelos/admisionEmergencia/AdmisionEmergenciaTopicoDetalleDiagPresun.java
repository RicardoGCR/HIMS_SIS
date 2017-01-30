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
public class AdmisionEmergenciaTopicoDetalleDiagPresun {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private String dettopdiag_id;
    private String id_topico;
    private int id_cie10;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    private String usu_cod;
    
    public boolean insertarDetalleDiagPresuntivo()
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_TOPICO_DETALLE_DIAGPRESUN_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setInt(2, getId_cie10());
            cmd.setString(3, getUsu_cod());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: insertarDetalleDiagPresuntivo: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean modificarDetalleDiagPresun()
        {
        boolean resp = false;
        try{
            String sql = "EXEC ADMISION_EMERGENCIA_TOPICO_DIAGNOSTICOS_MODIFICAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_topico());
            cmd.setInt(2, getId_cie10());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: modificarDetalleDiagPresun: " + ex.getMessage());
        }
        return resp;
    }
    
    public AdmisionEmergenciaTopicoDetalleDiagPresun()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the dettopdiag_id
     */
    public String getDettopdiag_id() {
        return dettopdiag_id;
    }

    /**
     * @param dettopdiag_id the dettopdiag_id to set
     */
    public void setDettopdiag_id(String dettopdiag_id) {
        this.dettopdiag_id = dettopdiag_id;
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
