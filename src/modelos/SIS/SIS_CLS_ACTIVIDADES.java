/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;

import java.sql.Connection;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SIS_CLS_ACTIVIDADES {
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public SIS_CLS_ACTIVIDADES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
}
