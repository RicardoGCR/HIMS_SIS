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
public class Caja_EmpresaJerarquia {
private Connection cn;
private String cod_empre_jerar;  
private String cod_jerar_forma_pago;
private String cod_dis;  
private String repre_empre_jerar;
private String ruc;
private String direccion_empre_jerar;
private String telefono;
private String cod_empre_jerar_farma;      
private String nom_usu;  

        
Conexion con = new Conexion();  

public boolean Nuevo(){
        boolean resp = false;
        try{
            String sql = "exec Caja_EmpresaJerarquia_INSERTAR "
                        + "?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_empre_jerar());
            cmd.setString(2, getCod_jerar_forma_pago());
            cmd.setString(3, getCod_dis());
            cmd.setString(4, getRepre_empre_jerar());
            cmd.setString(5, getRuc());
            cmd.setString(6, getDireccion_empre_jerar());
            cmd.setString(7, getTelefono());
            cmd.setString(8, getCod_empre_jerar_farma());
            cmd.setString(9, getNom_usu());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }

public String id(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_EmpresaJerarquia_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return id;
    }

public boolean modificar(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Empresajerarquia_MODIFICAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_empre_jerar());
            cmd.setString(2, getCod_jerar_forma_pago());
            cmd.setString(3, getCod_dis());
            cmd.setString(4, getRepre_empre_jerar());
            cmd.setString(5, getRuc());
            cmd.setString(6, getDireccion_empre_jerar());
            cmd.setString(7, getTelefono());
            cmd.setString(8, getCod_empre_jerar_farma());
            cmd.setString(9, getNom_usu());

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

public boolean eliminar(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_EmpresaJerarquia_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_empre_jerar());
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
  
    public Caja_EmpresaJerarquia(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCod_empre_jerar() {
        return cod_empre_jerar;
    }

    public void setCod_empre_jerar(String cod_empre_jerar) {
        this.cod_empre_jerar = cod_empre_jerar;
    }

    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    public String getCod_dis() {
        return cod_dis;
    }

    public void setCod_dis(String cod_dis) {
        this.cod_dis = cod_dis;
    }

    public String getRepre_empre_jerar() {
        return repre_empre_jerar;
    }

    public void setRepre_empre_jerar(String repre_empre_jerar) {
        this.repre_empre_jerar = repre_empre_jerar;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion_empre_jerar() {
        return direccion_empre_jerar;
    }

    public void setDireccion_empre_jerar(String direccion_empre_jerar) {
        this.direccion_empre_jerar = direccion_empre_jerar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCod_empre_jerar_farma() {
        return cod_empre_jerar_farma;
    }

    public void setCod_empre_jerar_farma(String cod_empre_jerar_farma) {
        this.cod_empre_jerar_farma = cod_empre_jerar_farma;
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
