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
public class tipoUsuario {
    private Connection cn;
   private String tipoUsu_Codigo;
   private String tipoUsu_Tipo;
   private String tipoUsu_Descripcion;

   public boolean guardarTipoUsuario()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_TIPOUSUARIO_Insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTipoUsu_Codigo());
            cmd.setString(2, getTipoUsu_Tipo());
            cmd.setString(3, getTipoUsu_Descripcion());      
            
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
         
         
    public boolean modificarTipoUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_TIPOUSUARIO_Modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTipoUsu_Codigo());
            cmd.setString(2, getTipoUsu_Tipo());
            cmd.setString(3, getTipoUsu_Descripcion());      
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
    
    
    public boolean eliminarTipoUsuario()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SP_USUARIO_TIPOUSUARIO_Eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getTipoUsu_Codigo());
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
    
    
   
    
    public String codTipoUsuario()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SP_USUARIO_TIPOUSUARIO_generar_id";
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
   
    public int ver_tipousuario(String tipo)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM Tipo_Usuario where tipoUsu_Tipo=? AND tipoUsu_Estado='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, tipo);
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
   
    public String codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT tipoUsu_Codigo FROM tipo_Usuario where tipoUsu_tipo=?";
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
    
   public tipoUsuario()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
     * @return the tipoUsu_Tipo
     */
    public String getTipoUsu_Tipo() {
        return tipoUsu_Tipo;
    }

    /**
     * @param tipoUsu_Tipo the tipoUsu_Tipo to set
     */
    public void setTipoUsu_Tipo(String tipoUsu_Tipo) {
        this.tipoUsu_Tipo = tipoUsu_Tipo;
    }

    /**
     * @return the tipoUsu_Descripcion
     */
    public String getTipoUsu_Descripcion() {
        return tipoUsu_Descripcion;
    }

    /**
     * @param tipoUsu_Descripcion the tipoUsu_Descripcion to set
     */
    public void setTipoUsu_Descripcion(String tipoUsu_Descripcion) {
        this.tipoUsu_Descripcion = tipoUsu_Descripcion;
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
    
}
