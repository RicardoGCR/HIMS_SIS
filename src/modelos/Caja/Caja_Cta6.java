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
 *
 * @author MYS1
 */
public class Caja_Cta6 {
private Connection cn;
//CUENTA 6
private String id_cuenta6 ;
private String id_cuenta5 ;
private String cuenta_6 ;
private String descripcion ;
private String nom_usu ;
Conexion con = new Conexion();  
public boolean NuevaCTA6()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Cta6_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta6());
            cmd.setString(2, getId_cuenta5());
            cmd.setString(3, getCuenta_6());
            cmd.setString(4, getDescripcion());
            cmd.setString(5, getNom_usu());

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
    
    
public String idCTA6(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Cta6_ID";
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

public boolean modificarCta6(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Cta6_Actualizar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta6());
            cmd.setString(2, getId_cuenta5());
            cmd.setString(3, getCuenta_6());
            cmd.setString(4, getDescripcion());
            cmd.setString(5, getNom_usu());

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

 public Caja_Cta6(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }  

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getId_cuenta6() {
        return id_cuenta6;
    }

    public void setId_cuenta6(String id_cuenta6) {
        this.id_cuenta6 = id_cuenta6;
    }

    public String getId_cuenta5() {
        return id_cuenta5;
    }

    public void setId_cuenta5(String id_cuenta5) {
        this.id_cuenta5 = id_cuenta5;
    }

    public String getCuenta_6() {
        return cuenta_6;
    }

    public void setCuenta_6(String cuenta_6) {
        this.cuenta_6 = cuenta_6;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }
 
}
