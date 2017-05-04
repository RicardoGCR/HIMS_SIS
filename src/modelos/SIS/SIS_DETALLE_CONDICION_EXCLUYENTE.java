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
public class SIS_DETALLE_CONDICION_EXCLUYENTE {
    private Connection cn;
    private String ID_DETALLE_PRES_ACTIVIDAD;
    private String ID_PRESTACION; 
    private String ID_ACTIVIDAD_RC;
    private Double RANGO_MINIMO;
    private String UNI_MIN;
    private Double RANGO_MAXIMO;
    private String UNI_MAX;
    private String NOM_USU;

    public SIS_DETALLE_CONDICION_EXCLUYENTE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean SIS_DETALLE_CONDICION_EXCLUYENTE_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_ACTIVIDAD_RC5_INSERTAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getID_ACTIVIDAD_RC());
            cmd.setDouble(3, getRANGO_MINIMO());
            cmd.setString(4, getUNI_MIN());
            cmd.setDouble(5, getRANGO_MAXIMO());
            cmd.setString(6, getUNI_MAX());
            cmd.setString(7, getNOM_USU());
            
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
    
    public boolean SIS_DETALLE_CONDICION_EXCLUYENTE_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_ACTIVIDAD_RC5_MODIFICAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DETALLE_PRES_ACTIVIDAD());
            cmd.setString(2, getID_PRESTACION());
            cmd.setString(3, getID_ACTIVIDAD_RC());
            cmd.setDouble(4, getRANGO_MINIMO());
            cmd.setString(5, getUNI_MIN());
            cmd.setDouble(6, getRANGO_MAXIMO());
            cmd.setString(7, getUNI_MAX());
            cmd.setString(8, getNOM_USU());
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

    public boolean SIS_DETALLE_CONDICION_EXCLUYENTE_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_ACTIVIDAD_RC5_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DETALLE_PRES_ACTIVIDAD());
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
    
    public int SIS_DETALLE_CONDICION_EXCLUYENTE_Ver_NUM(String nombreN)
    {
        int resultado=0;
        try
        {
            String sql = "EXEC SIS_CONDICION_EXCLUYENYE_VER_NUMERO ?";
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
    
    public Connection getCn() {
        return cn;
    }

    public String getID_DETALLE_PRES_ACTIVIDAD() {
        return ID_DETALLE_PRES_ACTIVIDAD;
    }

    public void setID_DETALLE_PRES_ACTIVIDAD(String ID_DETALLE_PRES_ACTIVIDAD) {
        this.ID_DETALLE_PRES_ACTIVIDAD = ID_DETALLE_PRES_ACTIVIDAD;
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

    public String getID_ACTIVIDAD_RC() {
        return ID_ACTIVIDAD_RC;
    }

    public void setID_ACTIVIDAD_RC(String ID_ACTIVIDAD_RC) {
        this.ID_ACTIVIDAD_RC = ID_ACTIVIDAD_RC;
    }

    public Double getRANGO_MINIMO() {
        return RANGO_MINIMO;
    }

    public void setRANGO_MINIMO(Double RANGO_MINIMO) {
        this.RANGO_MINIMO = RANGO_MINIMO;
    }

    public String getUNI_MIN() {
        return UNI_MIN;
    }

    public void setUNI_MIN(String UNI_MIN) {
        this.UNI_MIN = UNI_MIN;
    }

    public Double getRANGO_MAXIMO() {
        return RANGO_MAXIMO;
    }

    public void setRANGO_MAXIMO(Double RANGO_MAXIMO) {
        this.RANGO_MAXIMO = RANGO_MAXIMO;
    }

    public String getUNI_MAX() {
        return UNI_MAX;
    }

    public void setUNI_MAX(String UNI_MAX) {
        this.UNI_MAX = UNI_MAX;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
}
