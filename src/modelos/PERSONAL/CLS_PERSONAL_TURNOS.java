/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.PERSONAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class CLS_PERSONAL_TURNOS {
    private Connection cn;
    
    
    public CLS_PERSONAL_TURNOS()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String PERSONAL_COD_TIPO_TURNOS(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT cod_tipo_turno FROM PERSONAL_TIPO_TURNOS WHERE  nom_tipo_turno = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod tipo turnos: " + ex.getMessage());
        }
        return cod;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
 
    
}
