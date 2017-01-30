/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.COSTOS;

import servicios.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author USUARIO
 */
public class CTipoSustentacion {
    private String codigo;
    private String nombreTipoSustentacion;
    private String fechaActu;
    private String horaActu;
    private String nombreUsuario;
    private String estadoTipoSustentacion;
    private Connection cn;

    public CTipoSustentacion(String codigo, String nombreTipoSustentacion, String fechaActu, String horaActu, String nombreUsuario, String estadoTipoSustentacion) {
        this.codigo = codigo;
        this.nombreTipoSustentacion = nombreTipoSustentacion;
        this.fechaActu = fechaActu;
        this.horaActu = horaActu;
        this.nombreUsuario = nombreUsuario;
        this.estadoTipoSustentacion = estadoTipoSustentacion;
    }
    
    public CTipoSustentacion() {  
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    Conexion conex = new Conexion();
    
    public boolean grabarTipoSustentacion()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_TIPO_SUSTENTACION_insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCodigo());
            cmd.setString(2, getNombreTipoSustentacion());
            cmd.setString(3, getNombreUsuario());
            
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
    
    public boolean modificarTipoSustentacion()
    {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_TIPO_SUSTENTACION_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCodigo());
            cmd.setString(2, getNombreTipoSustentacion());
            cmd.setString(3, getNombreUsuario());
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
    
    
    public boolean eliminarTipoSustentacion()
    {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_TIPO_SUSTENTACION_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCodigo());
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
    
    public String codTipoSustentacion()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec COSTOS_COSTOS_TIPO_SUSTENTACION_GENERAR_CODIGO";
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

    public String obtenerCodTipo(String tipo)
    {
        String cod="";
        try{
        String sql="SELECT cod_tipo_susten FROM COSTOS_TIPO_SUSTENTACION where nombre_tipo_susten=?";
         PreparedStatement cmd = getCn().prepareStatement(sql);
        cmd.setString(1, tipo);
        ResultSet r = cmd.executeQuery();
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
    
    
    public int ver_Tipo_Sustentacion(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM COSTOS_TIPO_SUSTENTACION where nombre_tipo_susten=? AND estado_tipo_sustentacion='A'";
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
    }
    
   
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreTipoSustentacion() {
        return nombreTipoSustentacion;
    }

    public void setNombreTipoSustentacion(String nombreTipoSustentacion) {
        this.nombreTipoSustentacion = nombreTipoSustentacion;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEstadoTipoSustentacion() {
        return estadoTipoSustentacion;
    }

    public void setEstadoTipoSustentacion(String estadoTipoSustentacion) {
        this.estadoTipoSustentacion = estadoTipoSustentacion;
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
