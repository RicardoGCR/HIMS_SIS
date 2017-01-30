/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.COSTOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author silvana
 */
public class referencialDetalle {
    private Connection cn;
    private int cod_prod_refe_deta;
    private String cod_prod_refe;
    private String cod_produc;

    public boolean guardarReferencialDetalle()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_insertar ?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_prod_refe());
            cmd.setString(2, getCod_produc());
            
            
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
         
         
    public boolean modificarReferencialDetalle(){
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCod_prod_refe_deta());
            cmd.setString(2, getCod_prod_refe());
            cmd.setString(3, getCod_produc());
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
    

    public boolean eliminarReferencialDetalle()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_prod_refe());
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
    
    
    
    
    public referencialDetalle()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the cod_prod_refe_deta
     */
    public int getCod_prod_refe_deta() {
        return cod_prod_refe_deta;
    }

    /**
     * @param cod_prod_refe_deta the cod_prod_refe_deta to set
     */
    public void setCod_prod_refe_deta(int cod_prod_refe_deta) {
        this.cod_prod_refe_deta = cod_prod_refe_deta;
    }

    /**
     * @return the cod_prod_refe
     */
    public String getCod_prod_refe() {
        return cod_prod_refe;
    }

    /**
     * @param cod_prod_refe the cod_prod_refe to set
     */
    public void setCod_prod_refe(String cod_prod_refe) {
        this.cod_prod_refe = cod_prod_refe;
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
