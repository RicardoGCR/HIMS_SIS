/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.PERSONAL;

import java.sql.Connection;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class CLS_PERSONAL_ROL {
    private Connection cn;
    
    public CLS_PERSONAL_ROL()
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
