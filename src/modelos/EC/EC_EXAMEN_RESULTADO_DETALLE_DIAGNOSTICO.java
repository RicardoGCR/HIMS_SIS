/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO {
    private Connection cn;
    private int ID_DETALLE_RESULTADO_EC;
    private int ID_CIE10;
    private String NOM_USU;
    
    
    public EC_EXAMEN_RESULTADO_DETALLE_DIAGNOSTICO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean RX_EC_INFORME_DETALLE_DIAGNOSTICO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_INFORME_RESULTADO_DETALLE_DIAGNOSTICO_EC ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_DETALLE_RESULTADO_EC());
            cmd.setInt(2, getID_CIE10());
            cmd.setString(3, getNOM_USU());
                        
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error detalle diagnostico: " + ex.getMessage());
        }
        return resp;
    }
    
    public int getID_DETALLE_RESULTADO_EC() {
        return ID_DETALLE_RESULTADO_EC;
    }

    public void setID_DETALLE_RESULTADO_EC(int ID_DETALLE_RESULTADO_EC) {
        this.ID_DETALLE_RESULTADO_EC = ID_DETALLE_RESULTADO_EC;
    }

    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
   
}
