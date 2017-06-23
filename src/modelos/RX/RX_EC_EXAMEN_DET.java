/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.RX;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class RX_EC_EXAMEN_DET {
    private Connection cn;
    private int ID_EXAMEN_CAB;
    private int ID_COD_DOC_DET;
    private String COD_PER_SOL;
    private String NOM_PER_SOL;
    private String INCIDENCIA;
    private String FECHA_ORDEN;
    private String HORA_ORDEN;
    private String ID_PREVENTA;
    private String HAB_NOM;
    private String CA_DESC;
    private String HOSP_SERVICIO;
    private String NOM_USU;
    
    public RX_EC_EXAMEN_DET()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
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
            cmd.setString(5, getINCIDENCIA());
            cmd.setString(6, getID_PREVENTA());          
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
    
     public int RX_EC_Hospitalizacion_ver(String idhc){
        int resultado=0;
        try
        {
            String sql = "EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
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
    
 
    public String RX_EC_Hospi_idPreventa(String idhc)
    {
        String cod="";
        try
        {
            String sql = "EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
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
    
    public String RX_EC_Hospi_habitacion(String idhc)
    {
        String cod="";
        try
        {
            String sql = "EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(2);
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
    
    public String RX_EC_Hospi_cama(String idhc)
    {
        String cod="";
        try
        {
            String sql = "EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(3);
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
    
    public String RX_EC_Hospi_Servicio(String idhc)
    {
        String cod="";
        try
        {
            String sql = "EXEC RX_EC_EXAMEN_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(4);
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

    public boolean RX_EC_Estado_Caja(int id_cod_doc_det){
         boolean resp = false;
        try{
            String sql = "RX_EC_EXAMEN_ESTADO_CAJA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, id_cod_doc_det);

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
    
    public boolean RX_EC_ESTADO_CAJA_MODIFICAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_EXAMEN_ESTADO_CAJA ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_COD_DOC_DET());
            
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

    public String getINCIDENCIA() {
        return INCIDENCIA;
    }

    public void setINCIDENCIA(String INCIDENCIA) {
        this.INCIDENCIA = INCIDENCIA;
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

    public String getID_PREVENTA() {
        return ID_PREVENTA;
    }

    public void setID_PREVENTA(String ID_PREVENTA) {
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
