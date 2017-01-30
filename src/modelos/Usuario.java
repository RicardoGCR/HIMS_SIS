/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author silvana
 */
public class Usuario {
    private Connection cn;
private String Usu_Codigo;
private String tipoUsu_Codigo;
private String cod_per;
private String Usu_Usuario;
private String Usu_Contrasena;
private String Usu_Pregunta;
private String Usu_Respuesta;
private String Usu_FechaCre ;
private String Usu_HoraCre;
private String Usu_Estado;


public boolean guardarUsuario()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_Insertar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getUsu_Codigo());
            cmd.setString(2, getTipoUsu_Codigo());
            cmd.setString(3, getCod_per());
            cmd.setString(4, getUsu_Usuario());
            cmd.setString(5, getUsu_Contrasena());
            cmd.setString(6, getUsu_Pregunta());
            cmd.setString(7, getUsu_Respuesta());
            
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
         
         
    public boolean modificarUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_Modificar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getUsu_Codigo());
            cmd.setString(2, getTipoUsu_Codigo());
            cmd.setString(3, getCod_per());
            cmd.setString(4, getUsu_Usuario());
            cmd.setString(5, getUsu_Contrasena());
            cmd.setString(6, getUsu_Pregunta());
            cmd.setString(7, getUsu_Respuesta());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public boolean eliminarUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_Eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getUsu_Codigo());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
   
        
    public String codPersonal(String nombreCompleto)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_per FROM  personal_personal where nombres_per +' '+ ape_pat_per +' '+ ape_mat_per=? ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreCompleto);
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
    
    public String codTipo(String tipo)
    {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM tipo_Usuario where tipoUsu_tipo=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
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
    
    public String codUsuario()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SP_Usuario_generar_id";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    /*
    public int ver_usuarioRegistrado(String nombre)
    {
        
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM  personal_personal where nombres_per +' '+ ape_pat_per +' '+ ape_mat_per=? AND Usu_Estado='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
            ResultSet rs = cmd.executeQuery();
             for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
    }*/

    public int ver_usuario(String usu)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM Usuario where usu_usuario=? ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
    }
    
    public String codigoValidacion(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT Usu_Codigo FROM Usuario where usu_usuario=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
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
    
    
    public String Pregunta(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT usu_pregunta FROM Usuario where usu_usuario=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
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
    public String Respuesta(String usu,String pregunta)
    {
        String con="";
        try
        {
            String sql = "SELECT usu_respuesta FROM Usuario where usu_usuario=? and usu_pregunta=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, pregunta);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               con = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
    public String Contrasena(String usu,String pregunta)
    {
        String con="";
        try
        {
            String sql = "SELECT dbo.fnLeeClave(Usu_Contrasena) FROM Usuario where usu_usuario=? and usu_pregunta=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
            cmd.setString(2, pregunta);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               con = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return con;
    }
    
    //validar lblUsuario y Boton
    public String codAdmin()
        {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM tipo_Usuario where tipoUsu_tipo='Administrador'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    public String codigoAdminVali(String usu)
    {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM Usuario where usu_usuario=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, usu);
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
    
    
    public Usuario()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the Usu_Codigo
     */
    public String getUsu_Codigo() {
        return Usu_Codigo;
    }

    /**
     * @param Usu_Codigo the Usu_Codigo to set
     */
    public void setUsu_Codigo(String Usu_Codigo) {
        this.Usu_Codigo = Usu_Codigo;
    }

    /**
     * @return the tipoUsu_Codigo
     */
    public String getTipoUsu_Codigo() {
        return tipoUsu_Codigo;
    }

    /**
     * @param tipoUsu_Codigo the tipoUsu_Codigo to set
     */
    public void setTipoUsu_Codigo(String tipoUsu_Codigo) {
        this.tipoUsu_Codigo = tipoUsu_Codigo;
    }

    /**
     * @return the Usu_Nombre
     */

    /**
     * @return the Usu_FechaCre
     */
    public String getUsu_FechaCre() {
        return Usu_FechaCre;
    }

    /**
     * @param Usu_FechaCre the Usu_FechaCre to set
     */
    public void setUsu_FechaCre(String Usu_FechaCre) {
        this.Usu_FechaCre = Usu_FechaCre;
    }

    /**
     * @return the Usu_HoraCre
     */
    public String getUsu_HoraCre() {
        return Usu_HoraCre;
    }

    /**
     * @param Usu_HoraCre the Usu_HoraCre to set
     */
    public void setUsu_HoraCre(String Usu_HoraCre) {
        this.Usu_HoraCre = Usu_HoraCre;
    }

    /**
     * @return the Usu_Usuario
     */
    public String getUsu_Usuario() {
        return Usu_Usuario;
    }

    /**
     * @param Usu_Usuario the Usu_Usuario to set
     */
    public void setUsu_Usuario(String Usu_Usuario) {
        this.Usu_Usuario = Usu_Usuario;
    }

    /**
     * @return the Usu_Contrasena
     */
    public String getUsu_Contrasena() {
        return Usu_Contrasena;
    }

    /**
     * @param Usu_Contrasena the Usu_Contrasena to set
     */
    public void setUsu_Contrasena(String Usu_Contrasena) {
        this.Usu_Contrasena = Usu_Contrasena;
    }

    /**
     * @return the Usu_Pregunta
     */
    public String getUsu_Pregunta() {
        return Usu_Pregunta;
    }

    /**
     * @param Usu_Pregunta the Usu_Pregunta to set
     */
    public void setUsu_Pregunta(String Usu_Pregunta) {
        this.Usu_Pregunta = Usu_Pregunta;
    }

    /**
     * @return the Usu_Estado
     */
    public String getUsu_Estado() {
        return Usu_Estado;
    }

    /**
     * @param Usu_Estado the Usu_Estado to set
     */
    public void setUsu_Estado(String Usu_Estado) {
        this.Usu_Estado = Usu_Estado;
    }

    /**
     * @return the Usu_Respuesta
     */
    public String getUsu_Respuesta() {
        return Usu_Respuesta;
    }

    /**
     * @param Usu_Respuesta the Usu_Respuesta to set
     */
    public void setUsu_Respuesta(String Usu_Respuesta) {
        this.Usu_Respuesta = Usu_Respuesta;
    }

    /**
     * @return the cod_per
     */
    public String getCod_per() {
        return cod_per;
    }

    /**
     * @param cod_per the cod_per to set
     */
    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }
}
