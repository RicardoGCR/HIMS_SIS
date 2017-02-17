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
public class Caja_Jerarquias {
private Connection cn;
//CUENTA 2
private String cod_jerar_forma_pago ;
private String nom_forma_pago ;
private String descri_forma_pago;
private String relacion_forma_pago ;
private String nivel_forma_pago ;
private String tipo_estado_pago ;
private String nom_usu;
Conexion con = new Conexion(); 

public boolean NuevaJerarquia()
        {
        boolean resp = false;
        try{
            String sql = "EXEC Caja_jerarquia_INSERTAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_jerar_forma_pago());
            cmd.setString(2, getNom_forma_pago());
            cmd.setString(3, getDescri_forma_pago());
            cmd.setString(4, getRelacion_forma_pago());
            cmd.setString(5, getNivel_forma_pago());
            cmd.setString(6, getTipo_estado_pago());
            cmd.setString(7, getNom_usu());

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
    
    
public String idCJ(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_jerarquia_ID";
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

public boolean Caja_Jerarquia_MODIFICAR(){
        boolean resp = false;
        try
        {
            String sql = "Exec Caja_Jerarquia_MODIFICAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_jerar_forma_pago());
            cmd.setString(2, getNom_forma_pago());
            cmd.setString(3, getDescri_forma_pago());
            cmd.setString(4, getRelacion_forma_pago());
            cmd.setString(5, getNivel_forma_pago());
            cmd.setString(6, getTipo_estado_pago());
            cmd.setString(7, getNom_usu());

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

public String BuscarC(String codigo)
    {
        String cod="";
        try
        {
            String sql = "Exec Caja_Jerarquia_Codigo ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }

public boolean eliminarjerarquia(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_Jerarquia_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_jerar_forma_pago());
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

public String codTipo(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_jerar_forma_pago \n" +
                        "FROM CAJA_JERARQUIA_FORMA_PAGO\n" +
                        "WHERE nom_forma_pago = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codDistrito: " + ex.getMessage());
        }
        return cod;
    }



 public Caja_Jerarquias(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCod_jerar_forma_pago() {
        return cod_jerar_forma_pago;
    }

    public void setCod_jerar_forma_pago(String cod_jerar_forma_pago) {
        this.cod_jerar_forma_pago = cod_jerar_forma_pago;
    }

    public String getNom_forma_pago() {
        return nom_forma_pago;
    }

    public void setNom_forma_pago(String nom_forma_pago) {
        this.nom_forma_pago = nom_forma_pago;
    }

    public String getDescri_forma_pago() {
        return descri_forma_pago;
    }

    public void setDescri_forma_pago(String descri_forma_pago) {
        this.descri_forma_pago = descri_forma_pago;
    }

    public String getRelacion_forma_pago() {
        return relacion_forma_pago;
    }

    public void setRelacion_forma_pago(String relacion_forma_pago) {
        this.relacion_forma_pago = relacion_forma_pago;
    }

    public String getNivel_forma_pago() {
        return nivel_forma_pago;
    }

    public void setNivel_forma_pago(String nivel_forma_pago) {
        this.nivel_forma_pago = nivel_forma_pago;
    }

    public String getTipo_estado_pago() {
        return tipo_estado_pago;
    }

    public void setTipo_estado_pago(String tipo_estado_pago) {
        this.tipo_estado_pago = tipo_estado_pago;
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
