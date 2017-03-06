/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Resultado_Muestra_Cabecera {
    private Connection cn;

    private String nom_usu;
    
    public LAB_Resultado_Muestra_Cabecera(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
//    
//     public boolean LAB_Toma_Muestra_Cab_guardar()
//        {
//        boolean resp = false;
//        try
//        {
//            String sql = "exec sp_LAB_TOMA_MUESTRA_CAB_insertar ?,?,?,?,?,?,?,?";
//            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCod_cab_toma_mu_exa());
//            cmd.setString(2, getId_documento());
//            cmd.setString(3, getNum_toma_mu_exa());
//            cmd.setString(4, getCod_per_toma_muestra());
//            cmd.setString(5, getNombre_per_toma_muestra());
//            cmd.setString(6, getCod_per_regis_toma_muestra());
//            cmd.setString(7, getNombre_per_regis_toma_muestra());
//            cmd.setString(8, getNom_usu());
// 
//            if(!cmd.execute())
//            {
//                resp = true;
//            }
//            cmd.close();
//            getCn().close();
//        }
//        catch(Exception ex)
//        {
//            System.out.println("Error: " + ex.getMessage());
//        }
//        return resp;
//    }
     
    public String LAB_Resultado_Muestra_Cab_generarid(String tipo){
       String unidad="";
        try{
           String consulta="exec sp_LAB_RESULTADO_MUESTRA_CAB_generarid ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,tipo);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               unidad = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return unidad;
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
