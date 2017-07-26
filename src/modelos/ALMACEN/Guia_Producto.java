/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ALMACEN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class Guia_Producto {
     private Connection cn;
      private String cod_produc;
      private int cantidad;
      private int precio;
      private String nom_usu;
      private String upss;
      
      
     public Guia_Producto()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

     public boolean GUIA_PRODUCTO_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_Guia_Producto ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_produc());
            cmd.setInt(2, getCantidad());
            cmd.setInt(3, getPrecio());
            cmd.setString(4, getNom_usu());
            cmd.setString(5, getUpss());
            
            
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
     * @return the upss
     */
    public String getUpss() {
        return upss;
    }

    /**
     * @param upss the upss to set
     */
    public void setUpss(String upss) {
        this.upss = upss;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
