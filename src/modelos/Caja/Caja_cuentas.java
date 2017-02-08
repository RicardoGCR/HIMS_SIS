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
 * @author Ricardo
 */
public class Caja_cuentas {
private Connection cn;
//CUENTA 1
private String id_cuenta1;  
private String cuenta_1;
private String descripcion_1; 
private String nom_usu;    
Conexion con = new Conexion();  

    public boolean NuevaCTA1()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Cta1_INSERTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta1());
            cmd.setString(2, getCuenta_1());
            cmd.setString(3, getDescripcion_1());
            cmd.setString(4, getNom_usu());

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
    
    
public String idCTA1(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Cta1_ID";
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
public boolean modificarCta1(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Cta1_Actualizar ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta1());
            cmd.setString(2, getCuenta_1());
            cmd.setString(3, getDescripcion_1());
            cmd.setString(4, getNom_usu());

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
public boolean eliminarCTA1(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Cta1_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getId_cuenta1());
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

 public Caja_cuentas(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getId_cuenta1() {
        return id_cuenta1;
    }

    public void setId_cuenta1(String id_cuenta1) {
        this.id_cuenta1 = id_cuenta1;
    }

    public String getCuenta_1() {
        return cuenta_1;
    }

    public void setCuenta_1(String cuenta_1) {
        this.cuenta_1 = cuenta_1;
    }

    public String getDescripcion_1() {
        return descripcion_1;
    }

    public void setDescripcion_1(String descripcion_1) {
        this.descripcion_1 = descripcion_1;
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
