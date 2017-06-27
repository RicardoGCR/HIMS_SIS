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
 * @author PC02
 */
public class CLS_PERSONAL_ROL_ACTIVIDADES {
   private Connection cn;
   private int COD_ROL;
   private String COD_UNI_ORG_ACTI;
   private String HORA_INICIO_ACTI;
   private String HORA_FIN_ACTI;
   private String TOTAL_HORA_ACTIVIDAD;
   private String NOM_USU;
   private String COD_UNI_ORGANICA_JERAR;
   private int AR_ID;
   
   public CLS_PERSONAL_ROL_ACTIVIDADES()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

   public boolean PERSONAL_ROL_ACTIVIDADES_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_PERSONAL_ROL_ACTIVIDAD_GUARDAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_ROL());
            cmd.setString(2, getCOD_UNI_ORG_ACTI());
            cmd.setString(3, getHORA_INICIO_ACTI());
            cmd.setString(4, getHORA_FIN_ACTI());
            cmd.setString(5, getTOTAL_HORA_ACTIVIDAD());
            cmd.setString(6, getNOM_USU());         
            cmd.setInt(7, getAR_ID());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar personal rol actividades cls: " + ex.getMessage());
        }
        return resp;
    }
   
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCOD_ROL() {
        return COD_ROL;
    }

    public void setCOD_ROL(int COD_ROL) {
        this.COD_ROL = COD_ROL;
    }

    public String getCOD_UNI_ORG_ACTI() {
        return COD_UNI_ORG_ACTI;
    }

    public void setCOD_UNI_ORG_ACTI(String COD_UNI_ORG_ACTI) {
        this.COD_UNI_ORG_ACTI = COD_UNI_ORG_ACTI;
    }

    public String getHORA_INICIO_ACTI() {
        return HORA_INICIO_ACTI;
    }

    public void setHORA_INICIO_ACTI(String HORA_INICIO_ACTI) {
        this.HORA_INICIO_ACTI = HORA_INICIO_ACTI;
    }

    public String getHORA_FIN_ACTI() {
        return HORA_FIN_ACTI;
    }

    public void setHORA_FIN_ACTI(String HORA_FIN_ACTI) {
        this.HORA_FIN_ACTI = HORA_FIN_ACTI;
    }

    public String getTOTAL_HORA_ACTIVIDAD() {
        return TOTAL_HORA_ACTIVIDAD;
    }

    public void setTOTAL_HORA_ACTIVIDAD(String TOTAL_HORA_ACTIVIDAD) {
        this.TOTAL_HORA_ACTIVIDAD = TOTAL_HORA_ACTIVIDAD;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public String getCOD_UNI_ORGANICA_JERAR() {
        return COD_UNI_ORGANICA_JERAR;
    }

    public void setCOD_UNI_ORGANICA_JERAR(String COD_UNI_ORGANICA_JERAR) {
        this.COD_UNI_ORGANICA_JERAR = COD_UNI_ORGANICA_JERAR;
    }

    public int getAR_ID() {
        return AR_ID;
    }

    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }
 
}
