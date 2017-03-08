/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SIS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SIS_CLS_PRESTACIONES {
    private Connection cn;
    private String ID_PRESTACION;
    private String NUM_PRESTACION;
    private String DESCRIP_PRESTACION;
    private String TIPO;
    private String NOM_USU;

    public SIS_CLS_PRESTACIONES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String SIS_PRESTACIONES_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SIS_PRESTACIONES_GENERAR_ID";
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
    
    public boolean SIS_PRESTACIONES_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_PRESTACIONES_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getNUM_PRESTACION());
            cmd.setString(3, getDESCRIP_PRESTACION());
            cmd.setString(4, getTIPO());
            cmd.setString(5, getNOM_USU());
            
            
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
    
    public boolean SIS_PRESTACIONES_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_PRESTACIONES_MODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getNUM_PRESTACION());
            cmd.setString(3, getDESCRIP_PRESTACION());
            cmd.setString(4, getTIPO());
            cmd.setString(5, getNOM_USU());
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
    
    public boolean SIS_PRESTACIONES_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_PRESTACIONES_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            if(!cmd.execute()){
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
    
    public int SIS_PRESTACIONES_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SIS_PRESTACIONES where DESCRIP_PRESTACION=? AND ESTADO_PRESTACION='A'";
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
    
    
    public int SIS_PRESTACIONES_Ver_NUM(String nombreN)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SIS_PRESTACIONES where NUM_PRESTACION=? AND ESTADO_PRESTACION='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombreN);
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
    
    
    
    
    public String SIS_PRESTACION_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_PRESTACION FROM SIS_PRESTACIONES where DESCRIP_PRESTACION=?";
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
    
    public String SIS_PRESTACION_numero(String numero)
    {
        String cod="";
        try
        {
            String sql = "SELECT NUM_PRESTACION FROM SIS_PRESTACIONES where DESCRIP_PRESTACION=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, numero);
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
    
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_PRESTACION() {
        return ID_PRESTACION;
    }

    public void setID_PRESTACION(String ID_PRESTACION) {
        this.ID_PRESTACION = ID_PRESTACION;
    }

    public String getDESCRIP_PRESTACION() {
        return DESCRIP_PRESTACION;
    }

    public void setDESCRIP_PRESTACION(String DESCRIP_PRESTACION) {
        this.DESCRIP_PRESTACION = DESCRIP_PRESTACION;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public String getNUM_PRESTACION() {
        return NUM_PRESTACION;
    }

    public void setNUM_PRESTACION(String NUM_PRESTACION) {
        this.NUM_PRESTACION = NUM_PRESTACION;
    }
    
    
}
