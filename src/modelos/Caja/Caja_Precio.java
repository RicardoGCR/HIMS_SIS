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
public class Caja_Precio {
private Connection cn;
//CUENTA 2
private String cod_precio ;
private String cod_jerar_forma_pago ;
private String cod_nomen_caja ;
private String precio ;
private String nom_usu ;
Conexion con = new Conexion(); 

public boolean NuevoPrecio()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_Precios_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_precio());
            cmd.setString(2, getCod_jerar_forma_pago());
            cmd.setString(3, getCod_nomen_caja());
            cmd.setString(4, getPrecio());
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
    
    
public String idPrecio(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Precios_ID";
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

public boolean modificarPrecio(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Precios_ACTUALIZAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_precio());
            cmd.setString(2, getCod_jerar_forma_pago());
            cmd.setString(3, getCod_nomen_caja());
            cmd.setString(4, getPrecio());
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

 public Caja_Precio(){
        Conexion con = new Conexion();
        cn = con.conectar();
    } 

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCod_precio() {
        return cod_precio;
    }

    public void setCod_precio(String cod_precio) {
        this.cod_precio = cod_precio;
    }

    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
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
