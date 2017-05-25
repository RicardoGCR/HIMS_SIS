/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.RX_EC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class RX_EC_EXAMEN_DETALLE {
    private Connection cn;
    int ID_EXAMEN_CAB;
    int ID_COD_DOC_DET;
    String COD_PER_SOL;
    String NOM_PER_SOL;
    int ID_EXAMEN_INCIDENCIA;
    String FECHA_ORDEN;
    String HORA_ORDEN;
    int ID_PREVENTA;
    String HAB_NOM;
    String CA_DESC;
    String HOSP_SERVICIO;
    String NOM_USU;
    
    public RX_EC_EXAMEN_DETALLE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
    public int LAB_Toma_Muestra_Hospitalizacion_ver(String idhc){
        int resultado=0;
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
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

    public boolean RX_EC_EXAMEN_DETALLE_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_EXAMEN_DETALLE_GUARDAR ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_EXAMEN_CAB());
            cmd.setInt(2, getID_COD_DOC_DET());
            cmd.setString(3, getCOD_PER_SOL());
            cmd.setString(4, getNOM_PER_SOL());
            cmd.setInt(5, getID_EXAMEN_INCIDENCIA());
            cmd.setInt(6, getID_PREVENTA());          
            cmd.setString(7, getHAB_NOM());
            cmd.setString(8, getCA_DESC());
            cmd.setString(9, getHOSP_SERVICIO());
            cmd.setString(10, getNOM_USU());
      
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

    public int getID_EXAMEN_CAB() {
        return ID_EXAMEN_CAB;
    }

    public void setID_EXAMEN_CAB(int ID_EXAMEN_CAB) {
        this.ID_EXAMEN_CAB = ID_EXAMEN_CAB;
    }

    public int getID_COD_DOC_DET() {
        return ID_COD_DOC_DET;
    }

    public void setID_COD_DOC_DET(int ID_COD_DOC_DET) {
        this.ID_COD_DOC_DET = ID_COD_DOC_DET;
    }

    public String getCOD_PER_SOL() {
        return COD_PER_SOL;
    }

    public void setCOD_PER_SOL(String COD_PER_SOL) {
        this.COD_PER_SOL = COD_PER_SOL;
    }

    public String getNOM_PER_SOL() {
        return NOM_PER_SOL;
    }

    public void setNOM_PER_SOL(String NOM_PER_SOL) {
        this.NOM_PER_SOL = NOM_PER_SOL;
    }

    public int getID_EXAMEN_INCIDENCIA() {
        return ID_EXAMEN_INCIDENCIA;
    }

    public void setID_EXAMEN_INCIDENCIA(int ID_EXAMEN_INCIDENCIA) {
        this.ID_EXAMEN_INCIDENCIA = ID_EXAMEN_INCIDENCIA;
    }

    public String getFECHA_ORDEN() {
        return FECHA_ORDEN;
    }

    public void setFECHA_ORDEN(String FECHA_ORDEN) {
        this.FECHA_ORDEN = FECHA_ORDEN;
    }

    public String getHORA_ORDEN() {
        return HORA_ORDEN;
    }

    public void setHORA_ORDEN(String HORA_ORDEN) {
        this.HORA_ORDEN = HORA_ORDEN;
    }

    public int getID_PREVENTA() {
        return ID_PREVENTA;
    }

    public void setID_PREVENTA(int ID_PREVENTA) {
        this.ID_PREVENTA = ID_PREVENTA;
    }

    public String getHAB_NOM() {
        return HAB_NOM;
    }

    public void setHAB_NOM(String HAB_NOM) {
        this.HAB_NOM = HAB_NOM;
    }

    public String getCA_DESC() {
        return CA_DESC;
    }

    public void setCA_DESC(String CA_DESC) {
        this.CA_DESC = CA_DESC;
    }

    public String getHOSP_SERVICIO() {
        return HOSP_SERVICIO;
    }

    public void setHOSP_SERVICIO(String HOSP_SERVICIO) {
        this.HOSP_SERVICIO = HOSP_SERVICIO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
}
