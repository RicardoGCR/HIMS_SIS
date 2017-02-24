/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_PC_AREA {
    
    private Connection cn;
    
    
    public LAB_PC_AREA ()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    public String LAB_PC_AREA_SERVICIO()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sistema_pc_area";
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
    public String LAB_PC_AREA_AREA()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sistema_pc_area";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(2);
            }
        }catch(Exception ex)
        {
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
