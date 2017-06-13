/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class EC_EXAMEN_CABECERA {
    private Connection cn;
    private String ID_DOCUMENTO;
    private String FECHA_REGISTRO;
    private String HORA_REGISTRO;
    private String NOM_USU;
    
    public EC_EXAMEN_CABECERA()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean RX_EC_INFORME_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_INFORME_RESULTADO_EC_INSERTAR ?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_DOCUMENTO());
            cmd.setString(2, getFECHA_REGISTRO());
            cmd.setString(3, getHORA_REGISTRO());
            cmd.setString(4, getNOM_USU());
                        
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
    
    public String RX_EC_ID_EC()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID_CAB_EC";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error CODIGO: " + ex.getMessage());
        }
        return cod;
    }
    
    public String getID_DOCUMENTO() {
        return ID_DOCUMENTO;
    }

    public void setID_DOCUMENTO(String ID_DOCUMENTO) {
        this.ID_DOCUMENTO = ID_DOCUMENTO;
    }

    public String getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(String FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getHORA_REGISTRO() {
        return HORA_REGISTRO;
    }

    public void setHORA_REGISTRO(String HORA_REGISTRO) {
        this.HORA_REGISTRO = HORA_REGISTRO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
}
