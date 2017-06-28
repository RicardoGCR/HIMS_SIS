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
public class Caja_Cta4 {
private Connection cn;
//CUENTA 3
private String id_cuenta4 ;
private String id_cuenta3 ;
private String cuenta_4 ;
private String descripcion ;
private String nom_usu ;
Conexion con = new Conexion();   
public boolean NuevaCTA4()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Cta4_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta4());
            cmd.setString(2, getId_cuenta3());
            cmd.setString(3, getCuenta_4());
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
    
    
public String idCTA4(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Cta4_ID";
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

public boolean modificarCta4(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Cta4_Actualizar ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta4());
            cmd.setString(2, getId_cuenta3());
            cmd.setString(3, getCuenta_4());
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
public boolean eliminarCTA4(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Cta4_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta4());
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

 public Caja_Cta4(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getId_cuenta4() {
        return id_cuenta4;
    }

    public void setId_cuenta4(String id_cuenta4) {
        this.id_cuenta4 = id_cuenta4;
    }

    public String getId_cuenta3() {
        return id_cuenta3;
    }

    public void setId_cuenta3(String id_cuenta3) {
        this.id_cuenta3 = id_cuenta3;
    }

    public String getCuenta_4() {
        return cuenta_4;
    }

    public void setCuenta_4(String cuenta_4) {
        this.cuenta_4 = cuenta_4;
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
