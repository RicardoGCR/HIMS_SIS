/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.RX_EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class RX_EC_EXAMEN_DETALLE {
    private Connection cn;
    
    public RX_EC_EXAMEN_DETALLE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    public int LAB_Toma_Muestra_Hospitalizacion_ver(String idhc){
        int resultado=0;
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    
}
