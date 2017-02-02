/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SALA_OPERACIONES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SDO_TIPO_ANESTESIA {
    private Connection cn;
    private String ID_TIPO_ANESTESIA;
    private String DESCRIP_TIPO_ANESTESIA;
    private String NOM_USU;
    
    public SDO_TIPO_ANESTESIA()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String SDO_TIPO_ANESTESIA_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SALA_OPERACION_TIPO_ANESTESIA_GENERAR_ID";
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
    
    public boolean SDO_Tipo_Anestesia_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_TIPO_ANESTESIA_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_TIPO_ANESTESIA());
            cmd.setString(2, getDESCRIP_TIPO_ANESTESIA());
            cmd.setString(3, getNOM_USU());
            
            
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
    
    public boolean SDO_Tipo_Anestesia_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_TIPO_ANESTESIA_MODIFICAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_TIPO_ANESTESIA());
            cmd.setString(2, getDESCRIP_TIPO_ANESTESIA());
            cmd.setString(3, getNOM_USU());
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
    
    public boolean SDO_Tipo_Anestesia_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_TIPO_ANESTESIA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_TIPO_ANESTESIA());
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
    
    public int SDO_Tipo_Anestesia_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SALA_OPERACION_TIPO_ANESTESIA where DESCRIP_TIPO_ANESTESIA=? AND ESTADO_TIPO_ANESTESIA='A'";
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
    
    public String SDO_Tipo_Anestesia_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_TIPO_ANESTESIA FROM SALA_OPERACION_TIPO_ANESTESIA where DESCRIP_TIPO_ANESTESIA=?";
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
    
     
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_TIPO_ANESTESIA() {
        return ID_TIPO_ANESTESIA;
    }

    public void setID_TIPO_ANESTESIA(String ID_TIPO_ANESTESIA) {
        this.ID_TIPO_ANESTESIA = ID_TIPO_ANESTESIA;
    }

    public String getDESCRIP_TIPO_ANESTESIA() {
        return DESCRIP_TIPO_ANESTESIA;
    }

    public void setDESCRIP_TIPO_ANESTESIA(String DESCRIP_TIPO_ANESTESIA) {
        this.DESCRIP_TIPO_ANESTESIA = DESCRIP_TIPO_ANESTESIA;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
    
    
}
