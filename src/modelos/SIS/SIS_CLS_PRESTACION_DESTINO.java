/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SIS_CLS_PRESTACION_DESTINO {
    private Connection cn;
    private String ID_DETALLE_PRESTACION_DESTINO;
    private String ID_PRESTACION;
    private String ID_DESTINO;
    private String NOM_USU;
    
    
    public SIS_CLS_PRESTACION_DESTINO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String SIS_PRESTACION_DESTINO_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SIS_DETALLE_PRESTACION_DESTINO_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    /*public boolean SIS_PRESTACIONES_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_PRESTACIONES_INSERTAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getNUM_PRESTACION());
            cmd.setString(3, getDESCRIP_PRESTACION());
            cmd.setString(4, getTIPO());
            cmd.setString(5, getETAPA_VIDA());
            cmd.setString(6, getEDAD_MINIMA());
            cmd.setString(7, getEDAD_MAXIMA());
            cmd.setString(8, getSEXO());
            cmd.setString(9, getHOSPITALIZACION());
            cmd.setString(10, getGESTANTE());
            cmd.setString(11, getPUERPERA());
            cmd.setString(12, getN_GESTANTE_N_PUERPERA());
            cmd.setString(13, getREGIMEN_COMPONENTE());
            cmd.setString(14, getNOM_USU());           
            
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
    }*/
}
