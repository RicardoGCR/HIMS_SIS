/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import servicios.Conexion;
/**

/**
 *
 * @author MYS1
 */
public class Caja_DetallePreventas {
private Connection cn;
private int IdDetalle_Preventa;  
private String estado;


	
Conexion con = new Conexion();  

public boolean eliminarDP(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Detalle_Preventa_Eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdDetalle_Preventa());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
  public Caja_DetallePreventas(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getIdDetalle_Preventa() {
        return IdDetalle_Preventa;
    }

    public void setIdDetalle_Preventa(int IdDetalle_Preventa) {
        this.IdDetalle_Preventa = IdDetalle_Preventa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
  
}
