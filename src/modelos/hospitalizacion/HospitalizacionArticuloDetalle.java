/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.hospitalizacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import static modelos.hospitalizacion.HospitalizacionPapeletas.getCn;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class HospitalizacionArticuloDetalle {
    static DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int art_id;
    private int id_preventa;
    private String clasificacion;
    private String descripcion;
    private String cod_usu;
    private String fecha_actu;
    private String hora_actu;
    private String nom_pc;
    
    public boolean insertarArticuloDetalle()
        {
        boolean resp = false;
        try{
            String sql = "HOSPITALIZACION_ART_DETALLE_INSERTAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getId_preventa());
            cmd.setString(2, getDescripcion());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoHospitalizacionPapeleta: " + ex.getMessage());
        }
        return resp;
    }
    
    public HospitalizacionArticuloDetalle()
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
     * @return the art_id
     */
    public int getArt_id() {
        return art_id;
    }

    /**
     * @param art_id the art_id to set
     */
    public void setArt_id(int art_id) {
        this.art_id = art_id;
    }

    /**
     * @return the clasificacion
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the id_preventa
     */
    public int getId_preventa() {
        return id_preventa;
    }

    /**
     * @param id_preventa the id_preventa to set
     */
    public void setId_preventa(int id_preventa) {
        this.id_preventa = id_preventa;
    }
}
