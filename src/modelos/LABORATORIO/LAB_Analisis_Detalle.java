/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Analisis_Detalle {
     private Connection cn;
     private String cod_exa_ana;
     private String cod_muestra_para_exa;
     
     public LAB_Analisis_Detalle()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
     public boolean LAB_Analisis_Detalle_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_ANALISIS_DETALLE_insertar ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_exa_ana());
            cmd.setString(2, getCod_muestra_para_exa());
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
         
         
    
    public boolean LAB_Analisis_Detalle_eliminar()
    {
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ANALISIS_DETALLE_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_exa_ana());
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
     * @return the cod_muestra_para_exa
     */
    public String getCod_muestra_para_exa() {
        return cod_muestra_para_exa;
    }

    /**
     * @param cod_muestra_para_exa the cod_muestra_para_exa to set
     */
    public void setCod_muestra_para_exa(String cod_muestra_para_exa) {
        this.cod_muestra_para_exa = cod_muestra_para_exa;
    }
}
