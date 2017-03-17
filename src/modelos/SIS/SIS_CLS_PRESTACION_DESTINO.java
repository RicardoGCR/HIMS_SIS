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
public class SIS_CLS_PRESTACION_DESTINO {
    private Connection cn;
    private String ID_PRESTACION;
    private String ID_DESTINO;
    private String NOM_USU;
    
    
    public SIS_CLS_PRESTACION_DESTINO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
 /*   public String SIS_PRESTACION_DESTINO_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SIS_DETALLE_PRESTACION_DESTINO_GENERAR_ID";
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
    }*/
    
    public boolean SIS_PRESTACION_DESTINO_Guardar(){
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_DESTINO_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getID_DESTINO());
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
    
    public boolean SIS_PRESTACION_DESTINO_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_DESTINO_MODIFICAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_PRESTACION());
            cmd.setString(2, getID_DESTINO());
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
    
    public boolean SIS_PRESTACION_DESTINO_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SIS_DETALLE_PRESTACION_DESTINO_ELIMINAR ?";
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

    public String getID_DESTINO() {
        return ID_DESTINO;
    }

    public void setID_DESTINO(String ID_DESTINO) {
        this.ID_DESTINO = ID_DESTINO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
}
