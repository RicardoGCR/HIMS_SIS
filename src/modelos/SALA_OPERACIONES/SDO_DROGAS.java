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
 * @author USUARIO
 */
public class SDO_DROGAS {
    private Connection cn;
    private String ID_DROGAS;
    private String DESCRIP_DROGAS;
    private String UNIDAD_MEDIDA;
    private int STOCK;
    private String NOM_USU;
    
    
    public SDO_DROGAS()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String SDO_DROGAS_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SALA_OPERACION_DROGAS_GENERAR_ID";
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
    
    
    public boolean SDO_Droga_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_DROGAS_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DROGAS());
            cmd.setString(2, getDESCRIP_DROGAS());
            cmd.setString(3, getUNIDAD_MEDIDA());
            cmd.setInt(4, getSTOCK());
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
    
    
    public boolean SDO_Drogas_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_DROGAS_MODIFICAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DROGAS());
            cmd.setString(2, getDESCRIP_DROGAS());
            cmd.setString(3, getUNIDAD_MEDIDA());
            cmd.setInt(4, getSTOCK());
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
    
    public boolean SDO_Drogas_Eliminar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_DROGAS_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DROGAS());
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

    
    public int SDO_Drogas_Ver(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM SALA_OPERACION_DROGAS where DESCRIP_DROGAS=? AND ESTADO_DROGAS='A'";
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
    
    
    public String SDO_Drogas_Codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT ID_DROGAS FROM SALA_OPERACION_DROGAS where DESCRIP_DROGAS=?";
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

    public String getID_DROGAS() {
        return ID_DROGAS;
    }

    public void setID_DROGAS(String ID_DROGAS) {
        this.ID_DROGAS = ID_DROGAS;
    }

    public String getDESCRIP_DROGAS() {
        return DESCRIP_DROGAS;
    }

    public void setDESCRIP_DROGAS(String DESCRIP_DROGAS) {
        this.DESCRIP_DROGAS = DESCRIP_DROGAS;
    }

    public String getUNIDAD_MEDIDA() {
        return UNIDAD_MEDIDA;
    }

    public void setUNIDAD_MEDIDA(String UNIDAD_MEDIDA) {
        this.UNIDAD_MEDIDA = UNIDAD_MEDIDA;
    }

    public int getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(int STOCK) {
        this.STOCK = STOCK;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
}
