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
 * @author Ricardo
 */
public class Caja_AperturaCierre {
private Connection cn;
private String cierreanterior;
private String Apertura ;
private String cierre ;
private String Cajero ;
private int idApertura;
        
Conexion con = new Conexion();

public boolean NUEVO()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_CierreNUEVO ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            
            cmd.setString(1, getCierreanterior());
            cmd.setString(2, getApertura());
            cmd.setString(3, getCierre());
            cmd.setString(4, getCajero());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
        return resp;
    }


public boolean modificar(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_CierreMODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdApertura());
            cmd.setString(2, getCierreanterior());
            cmd.setString(3, getApertura());
            cmd.setString(4, getCierre());
            cmd.setString(5, getCajero());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }

public String Apertura(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec CAJA_Apertura";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }
 public Caja_AperturaCierre(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCierreanterior() {
        return cierreanterior;
    }

    public void setCierreanterior(String cierreanterior) {
        this.cierreanterior = cierreanterior;
    }

    public String getApertura() {
        return Apertura;
    }

    public void setApertura(String Apertura) {
        this.Apertura = Apertura;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
    }

    public String getCajero() {
        return Cajero;
    }

    public void setCajero(String Cajero) {
        this.Cajero = Cajero;
    }

    public int getIdApertura() {
        return idApertura;
    }

    public void setIdApertura(int idApertura) {
        this.idApertura = idApertura;
    }
    

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
 
    
}
