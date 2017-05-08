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
public class LAB_Solicitud_Inv_Bact {
      private Connection cn;
      
       public LAB_Solicitud_Inv_Bact(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
       
       
        public String LAB_DISA_SALUD(int cont){
        String cod="";
        try
        {
            String sql = " SELECT TOP 1 I.DESC_IPRESS,UE.UE_DESC FROM SISTEMA_IPRESS I, SISTEMA_UNIDAD_EJECUTORA UE "
                    + "WHERE I.ID_IPRESS=UE.ID_IPRESS ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(cont);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
        
        public String LAB_Cod_Muestra(){
        String cod="";
        try
        {
            String sql = " exec sp_LAB_MUESTRA_EXAMEN_buscar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, "Esputo");
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
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
