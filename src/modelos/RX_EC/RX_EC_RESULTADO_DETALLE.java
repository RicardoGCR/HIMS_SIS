/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.RX_EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class RX_EC_RESULTADO_DETALLE {
    private Connection cn;
    private int COD_CAB_RESULTADO;
    private int COD_EXAMEN_DETALLE;
    private String DESCRIPCION_RESULTADO;
    private String CONCLUSION_DIAGNOSTICA;
    private String NOM_USU;
    
    public RX_EC_RESULTADO_DETALLE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    public boolean RX_EC_RESULTADO_DETALLE_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_RESULTADO_DETALLE_INSERTAR ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_CAB_RESULTADO());
            cmd.setInt(2, getCOD_EXAMEN_DETALLE());
            cmd.setString(3, getDESCRIPCION_RESULTADO());
            cmd.setString(4, getCONCLUSION_DIAGNOSTICA());        
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
    

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCOD_CAB_RESULTADO() {
        return COD_CAB_RESULTADO;
    }

    public void setCOD_CAB_RESULTADO(int COD_CAB_RESULTADO) {
        this.COD_CAB_RESULTADO = COD_CAB_RESULTADO;
    }

    public int getCOD_EXAMEN_DETALLE() {
        return COD_EXAMEN_DETALLE;
    }

    public void setCOD_EXAMEN_DETALLE(int COD_EXAMEN_DETALLE) {
        this.COD_EXAMEN_DETALLE = COD_EXAMEN_DETALLE;
    }

    public String getDESCRIPCION_RESULTADO() {
        return DESCRIPCION_RESULTADO;
    }

    public void setDESCRIPCION_RESULTADO(String DESCRIPCION_RESULTADO) {
        this.DESCRIPCION_RESULTADO = DESCRIPCION_RESULTADO;
    }

    public String getCONCLUSION_DIAGNOSTICA() {
        return CONCLUSION_DIAGNOSTICA;
    }

    public void setCONCLUSION_DIAGNOSTICA(String CONCLUSION_DIAGNOSTICA) {
        this.CONCLUSION_DIAGNOSTICA = CONCLUSION_DIAGNOSTICA;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
  
}
