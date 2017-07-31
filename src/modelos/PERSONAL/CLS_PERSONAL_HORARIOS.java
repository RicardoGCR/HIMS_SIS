/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.PERSONAL;

import java.sql.Connection;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class CLS_PERSONAL_HORARIOS {
    private Connection cn;
    
    public CLS_PERSONAL_HORARIOS()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String PERSONAL_HORARIOS_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec PERSONAL_HORARIOS_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error generar ID HORARIOS: " + ex.getMessage());
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
