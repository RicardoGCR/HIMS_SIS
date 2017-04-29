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
public class LAB_Resultado_Insumos {
    private Connection cn;
    
    
    public LAB_Resultado_Insumos()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String LAB_Resultado_Insumo_Saldo(String cod_produc){
       String saldo="";
        try{
           String consulta="exec sp_LAB_INSUMO_SALDO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,cod_produc);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               saldo = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return saldo;
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
