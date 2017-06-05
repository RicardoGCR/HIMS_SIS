/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.EC;

import java.sql.Connection;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class EC_EXAMEN_CABECERA {
    private Connection cn;

    public EC_EXAMEN_CABECERA()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
}
