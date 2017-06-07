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
public class EC_EXAMEN_RESULTADO_DETALLE {
    private Connection cn;
    private int ID_CABECERA_RESULTADO_EC;
    private int ID_COD_DOC_DET;
    private String COD_PERSONAL_REALIZA;
    private String NOMBRE_PERSONAL_REALIZA;
    private String COD_PERSONAL_REGISTRA;
    private String NOMBRE_PERSONAL_REGISTRA;
    private String COD_PERSONAL_SOLICITA;
    private String NOMBRE_PERSONAL_SOLICITA;
    private String ID_PREVENTA;
    private String HAB_NOM;
    private String CA_DESC;
    private String HOSP_SERVICIO;
    private String DESCRIPCION_RESULTADO;
    private String DIAGNOSTICO_RESULTADO;
    private String FECHA_RESULTADO;
    private String HORA_RESULTADO;
    private String NOM_USU;
    
    public EC_EXAMEN_RESULTADO_DETALLE()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public boolean RX_EC_INFORME_DETALLE_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_INFORME_RESULTADO_DETALLE_INSERTAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getID_CABECERA_RESULTADO_EC());
            cmd.setInt(2, getID_COD_DOC_DET());
            cmd.setString(3, getCOD_PERSONAL_REALIZA());
            cmd.setString(4, getNOMBRE_PERSONAL_REALIZA());
            cmd.setString(5, getCOD_PERSONAL_REGISTRA());
            cmd.setString(6, getNOMBRE_PERSONAL_REGISTRA());
            cmd.setString(7, getCOD_PERSONAL_SOLICITA());
            cmd.setString(8, getNOMBRE_PERSONAL_SOLICITA());
            cmd.setString(9, getID_PREVENTA());
            cmd.setString(10, getHAB_NOM());
            cmd.setString(11, getCA_DESC());
            cmd.setString(12, getHOSP_SERVICIO());
            cmd.setString(13, getDESCRIPCION_RESULTADO());
            cmd.setString(14, getDIAGNOSTICO_RESULTADO());
            cmd.setString(15, getFECHA_RESULTADO());
            cmd.setString(16, getHORA_RESULTADO());
            cmd.setString(17, getNOM_USU());
                        
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
    
    public String RX_EC_ID_DETALLE_EC()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID_DET_EC";
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
    
    public boolean EC_Estado_RESULTADO_Caja_EC(int id_cod_doc_det){
         boolean resp = false;
        try{
            String sql = "RX_EC_RESULTADO_ESTADO_CAJA_EC ?";
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
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getID_CABECERA_RESULTADO_EC() {
        return ID_CABECERA_RESULTADO_EC;
    }

    public void setID_CABECERA_RESULTADO_EC(int ID_CABECERA_RESULTADO_EC) {
        this.ID_CABECERA_RESULTADO_EC = ID_CABECERA_RESULTADO_EC;
    }

    public int getID_COD_DOC_DET() {
        return ID_COD_DOC_DET;
    }

    public void setID_COD_DOC_DET(int ID_COD_DOC_DET) {
        this.ID_COD_DOC_DET = ID_COD_DOC_DET;
    }

    public String getCOD_PERSONAL_REALIZA() {
        return COD_PERSONAL_REALIZA;
    }

    public void setCOD_PERSONAL_REALIZA(String COD_PERSONAL_REALIZA) {
        this.COD_PERSONAL_REALIZA = COD_PERSONAL_REALIZA;
    }

    public String getNOMBRE_PERSONAL_REALIZA() {
        return NOMBRE_PERSONAL_REALIZA;
    }

    public void setNOMBRE_PERSONAL_REALIZA(String NOMBRE_PERSONAL_REALIZA) {
        this.NOMBRE_PERSONAL_REALIZA = NOMBRE_PERSONAL_REALIZA;
    }

    public String getCOD_PERSONAL_REGISTRA() {
        return COD_PERSONAL_REGISTRA;
    }

    public void setCOD_PERSONAL_REGISTRA(String COD_PERSONAL_REGISTRA) {
        this.COD_PERSONAL_REGISTRA = COD_PERSONAL_REGISTRA;
    }

    public String getNOMBRE_PERSONAL_REGISTRA() {
        return NOMBRE_PERSONAL_REGISTRA;
    }

    public void setNOMBRE_PERSONAL_REGISTRA(String NOMBRE_PERSONAL_REGISTRA) {
        this.NOMBRE_PERSONAL_REGISTRA = NOMBRE_PERSONAL_REGISTRA;
    }

    public String getCOD_PERSONAL_SOLICITA() {
        return COD_PERSONAL_SOLICITA;
    }

    public void setCOD_PERSONAL_SOLICITA(String COD_PERSONAL_SOLICITA) {
        this.COD_PERSONAL_SOLICITA = COD_PERSONAL_SOLICITA;
    }

    public String getNOMBRE_PERSONAL_SOLICITA() {
        return NOMBRE_PERSONAL_SOLICITA;
    }

    public void setNOMBRE_PERSONAL_SOLICITA(String NOMBRE_PERSONAL_SOLICITA) {
        this.NOMBRE_PERSONAL_SOLICITA = NOMBRE_PERSONAL_SOLICITA;
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

    public String getDESCRIPCION_RESULTADO() {
        return DESCRIPCION_RESULTADO;
    }

    public void setDESCRIPCION_RESULTADO(String DESCRIPCION_RESULTADO) {
        this.DESCRIPCION_RESULTADO = DESCRIPCION_RESULTADO;
    }

    public String getDIAGNOSTICO_RESULTADO() {
        return DIAGNOSTICO_RESULTADO;
    }

    public void setDIAGNOSTICO_RESULTADO(String DIAGNOSTICO_RESULTADO) {
        this.DIAGNOSTICO_RESULTADO = DIAGNOSTICO_RESULTADO;
    }

    public String getFECHA_RESULTADO() {
        return FECHA_RESULTADO;
    }

    public void setFECHA_RESULTADO(String FECHA_RESULTADO) {
        this.FECHA_RESULTADO = FECHA_RESULTADO;
    }

    public String getHORA_RESULTADO() {
        return HORA_RESULTADO;
    }

    public void setHORA_RESULTADO(String HORA_RESULTADO) {
        this.HORA_RESULTADO = HORA_RESULTADO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
}
