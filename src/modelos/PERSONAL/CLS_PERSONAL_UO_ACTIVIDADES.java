/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.PERSONAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class CLS_PERSONAL_UO_ACTIVIDADES {
    private Connection cn;
    private String COD_UNI_ORG_ACTI;
    private String COD_UNI_ORGANICA_JERAR;
    private String NOMBRE_ACTIVIDAD;
    private String NOM_USU;
    
    
    public CLS_PERSONAL_UO_ACTIVIDADES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean PERSONAL_UO_ACTIVIDAD_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_UNI_ORG_ACTIVIDADES_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCOD_UNI_ORG_ACTI());
            cmd.setString(1, getCOD_UNI_ORGANICA_JERAR());
            cmd.setString(2, getNOMBRE_ACTIVIDAD());
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
            System.out.println("Error guardar  UO ACTIVIDADES: " + ex.getMessage());
        }
        return resp;
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCOD_UNI_ORG_ACTI() {
        return COD_UNI_ORG_ACTI;
    }

    public void setCOD_UNI_ORG_ACTI(String COD_UNI_ORG_ACTI) {
        this.COD_UNI_ORG_ACTI = COD_UNI_ORG_ACTI;
    }

    public String getCOD_UNI_ORGANICA_JERAR() {
        return COD_UNI_ORGANICA_JERAR;
    }

    public void setCOD_UNI_ORGANICA_JERAR(String COD_UNI_ORGANICA_JERAR) {
        this.COD_UNI_ORGANICA_JERAR = COD_UNI_ORGANICA_JERAR;
    }

    public String getNOMBRE_ACTIVIDAD() {
        return NOMBRE_ACTIVIDAD;
    }

    public void setNOMBRE_ACTIVIDAD(String NOMBRE_ACTIVIDAD) {
        this.NOMBRE_ACTIVIDAD = NOMBRE_ACTIVIDAD;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
}
