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
public class Producto_Insumos {
      private Connection cn;
      private String cod_produc;
      private int cantidad_medida;
      
     public Producto_Insumos()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
 public boolean ASIGNAR_UNIDAD_MEDIDA_MODIFICAR()
        {
        boolean resp = false;
        try
        {
            String sql = "exec ASIGNAR_UNIDAD_MEDIDA_MODIFICAR ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_produc());
            cmd.setInt(2, getCantidad_medida());
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
     * @return the cantidad_medida
     */
    public int getCantidad_medida() {
        return cantidad_medida;
    }

    /**
     * @param cantidad_medida the cantidad_medida to set
     */
    public void setCantidad_medida(int cantidad_medida) {
        this.cantidad_medida = cantidad_medida;
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
    
}
