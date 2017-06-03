/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.RX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;
/**
 *
 * @author USUARIO
 */
public class RX_EC_BUSCAR_EXAMEN_CAJA {
    private Connection cn;
    
    public RX_EC_BUSCAR_EXAMEN_CAJA()
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
