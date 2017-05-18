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
public class LAB_Conversion {
        private Connection cn;
    
        private int cod_conversion_cab;
        private String  cod_produc;
        private int cantidad;
        private String  cod_per_verif;
        private String nombre_per_verif;
        private String cod_per_confirma ;
        private String nombre_per_confirma;
        private String nom_usu;
            
            private int cod_conversion_det ;
        private String cod_produc_conv;
        private int cantidad_conv ;
        private int cod_KardexLAB ;
    
    
    public LAB_Conversion()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean LAB_Conversion_Cab_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_CONVERSION_CAB ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_produc());
            cmd.setInt(2, getCantidad() );
            cmd.setString(3, getCod_per_verif());
            cmd.setString(4, getNombre_per_verif() );
            cmd.setString(5, getCod_per_confirma());
            cmd.setString(6, getNombre_per_confirma());
            cmd.setString(7, getNom_usu());
 
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error CAB: " + ex.getMessage());
        }
        return resp;
    }
    
     public boolean LAB_Conversion_Det_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_CONVERSION_DET ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_produc_conv());
            cmd.setInt(2, getCantidad_conv());
            cmd.setString(3, getNom_usu());
 
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error DET: " + ex.getMessage());
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
     * @return the cod_conversion_cab
     */
    public int getCod_conversion_cab() {
        return cod_conversion_cab;
    }

    /**
     * @param cod_conversion_cab the cod_conversion_cab to set
     */
    public void setCod_conversion_cab(int cod_conversion_cab) {
        this.cod_conversion_cab = cod_conversion_cab;
    }

    /**
     * @return the cod_produc
     */
    public String getCod_produc() {
        return cod_produc;
    }

    /**
     * @param cod_produc the cod_produc to set
     */
    public void setCod_produc(String cod_produc) {
        this.cod_produc = cod_produc;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
     * @return the cod_conversion_det
     */
    public int getCod_conversion_det() {
        return cod_conversion_det;
    }

    /**
     * @param cod_conversion_det the cod_conversion_det to set
     */
    public void setCod_conversion_det(int cod_conversion_det) {
        this.cod_conversion_det = cod_conversion_det;
    }

    /**
     * @return the cod_produc_conv
     */
    public String getCod_produc_conv() {
        return cod_produc_conv;
    }

    /**
     * @param cod_produc_conv the cod_produc_conv to set
     */
    public void setCod_produc_conv(String cod_produc_conv) {
        this.cod_produc_conv = cod_produc_conv;
    }

    /**
     * @return the cantidad_conv
     */
    public int getCantidad_conv() {
        return cantidad_conv;
    }

    /**
     * @param cantidad_conv the cantidad_conv to set
     */
    public void setCantidad_conv(int cantidad_conv) {
        this.cantidad_conv = cantidad_conv;
    }

    /**
     * @return the cod_KardexLAB
     */
    public int getCod_KardexLAB() {
        return cod_KardexLAB;
    }

    /**
     * @param cod_KardexLAB the cod_KardexLAB to set
     */
    public void setCod_KardexLAB(int cod_KardexLAB) {
        this.cod_KardexLAB = cod_KardexLAB;
    }

    /**
     * @return the cod_per_verif
     */
    public String getCod_per_verif() {
        return cod_per_verif;
    }

    /**
     * @param cod_per_verif the cod_per_verif to set
     */
    public void setCod_per_verif(String cod_per_verif) {
        this.cod_per_verif = cod_per_verif;
    }

    /**
     * @return the nombre_per_verif
     */
    public String getNombre_per_verif() {
        return nombre_per_verif;
    }

    /**
     * @param nombre_per_verif the nombre_per_verif to set
     */
    public void setNombre_per_verif(String nombre_per_verif) {
        this.nombre_per_verif = nombre_per_verif;
    }

    /**
     * @return the cod_per_confirma
     */
    public String getCod_per_confirma() {
        return cod_per_confirma;
    }

    /**
     * @param cod_per_confirma the cod_per_confirma to set
     */
    public void setCod_per_confirma(String cod_per_confirma) {
        this.cod_per_confirma = cod_per_confirma;
    }

    /**
     * @return the nombre_per_confirma
     */
    public String getNombre_per_confirma() {
        return nombre_per_confirma;
    }

    /**
     * @param nombre_per_confirma the nombre_per_confirma to set
     */
    public void setNombre_per_confirma(String nombre_per_confirma) {
        this.nombre_per_confirma = nombre_per_confirma;
    }

}
