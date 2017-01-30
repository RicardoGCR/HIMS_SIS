/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.COSTOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author Profe
 */
public class FP_Herramientas {
    
    private Connection cn;
    private int cod_det_sus_maqui_herra;
    private String cod_sustento_costo;
    private String cod_tipo_susten;
    private String cod_depreciacion;
    private String nom_usu;
    
    public FP_Herramientas(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    public boolean guardarFP_Herramienta()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_insertar ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_tipo_susten());
            cmd.setString(3, getCod_depreciacion());
            cmd.setString(4, getNom_usu());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
         

    public boolean eliminarFP_Herramienta()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            getCn().close();  
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
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
     * @return the cod_det_sus_maqui_herra
     */
    public int getCod_det_sus_maqui_herra() {
        return cod_det_sus_maqui_herra;
    }

    /**
     * @param cod_det_sus_maqui_herra the cod_det_sus_maqui_herra to set
     */
    public void setCod_det_sus_maqui_herra(int cod_det_sus_maqui_herra) {
        this.cod_det_sus_maqui_herra = cod_det_sus_maqui_herra;
    }

    /**
     * @return the cod_sustento_costo
     */
    public String getCod_sustento_costo() {
        return cod_sustento_costo;
    }

    /**
     * @param cod_sustento_costo the cod_sustento_costo to set
     */
    public void setCod_sustento_costo(String cod_sustento_costo) {
        this.cod_sustento_costo = cod_sustento_costo;
    }

    /**
     * @return the cod_tipo_susten
     */
    public String getCod_tipo_susten() {
        return cod_tipo_susten;
    }

    /**
     * @param cod_tipo_susten the cod_tipo_susten to set
     */
    public void setCod_tipo_susten(String cod_tipo_susten) {
        this.cod_tipo_susten = cod_tipo_susten;
    }

    /**
     * @return the cod_depreciacion
     */
    public String getCod_depreciacion() {
        return cod_depreciacion;
    }

    /**
     * @param cod_depreciacion the cod_depreciacion to set
     */
    public void setCod_depreciacion(String cod_depreciacion) {
        this.cod_depreciacion = cod_depreciacion;
    }

    /**
     * @return the nom_usu
     */
    public String getNom_usu() {
        return nom_usu;
    }

    /**
     * @param nom_usu the nom_usu to set
     */
    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }
}
