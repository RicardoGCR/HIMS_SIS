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
public class Caja_MotivoAnulacion {
private Connection cn;
private String cod_motiv_anu;  
private String descripcion_anulacion;
private String nom_usu;  
     
Conexion con = new Conexion();  

public boolean NuevoAnulacion(){
        boolean resp = false;
        try{
            String sql = "exec Caja_Motivo_Anulacion_Insertar "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_motiv_anu());
            cmd.setString(2, getDescripcion_anulacion());
            cmd.setString(3, getNom_usu());

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

public String idanu(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Motiv_Anu_Id";
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
 public String codUsuario(String nombreUsuario)
    {
        String cod="";
        try
        {
            String sql = "SELECT USU_CODIGO FROM USUARIO WHERE Usu_Usuario = ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreUsuario);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error_codUsuario: " + ex.getMessage());
        }
        return cod;
    }
public boolean modificarA(){
        boolean resp = false;
        try
        {
            String sql = "Caja_Motivo_Anulacion_Actualizar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_motiv_anu());
            cmd.setString(2, getDescripcion_anulacion());
            cmd.setString(3, getNom_usu());

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

 public boolean eliminarA(){
        boolean resp = false;
        try
        {
            String sql = "EXEC CAJA_ELIMINAR_ANULACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_motiv_anu());
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
  public Caja_MotivoAnulacion(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCod_motiv_anu() {
        return cod_motiv_anu;
    }

    public void setCod_motiv_anu(String cod_motiv_anu) {
        this.cod_motiv_anu = cod_motiv_anu;
    }

    public String getDescripcion_anulacion() {
        return descripcion_anulacion;
    }

    public void setDescripcion_anulacion(String descripcion_anulacion) {
        this.descripcion_anulacion = descripcion_anulacion;
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
