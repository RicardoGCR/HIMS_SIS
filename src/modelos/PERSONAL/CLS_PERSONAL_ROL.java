/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.PERSONAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class CLS_PERSONAL_ROL {
    private Connection cn;
    private int COD_ROL;
    private String COD_TUR_UO;
    private int ID_PER_UNI_ORG;
    private String DIA_ROL;
    private String MES_ROL;
    private String ANIO_ROL;
    private int LIMITE_CONSULTA_DIA;
    private String TOTAL_HORAS;
    private double TOTAL_PAGO;
    private String NOM_USU;
    
    public CLS_PERSONAL_ROL()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean PERSONAL_ROL_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_PERSONAL_ROL_GUARDAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCOD_TUR_UO());
            cmd.setInt(2, getID_PER_UNI_ORG());
            cmd.setString(3, getDIA_ROL());
            cmd.setString(4, getMES_ROL());
            cmd.setString(5, getANIO_ROL());
            cmd.setInt(6, getLIMITE_CONSULTA_DIA());
            cmd.setString(7, getTOTAL_HORAS());
            cmd.setDouble(8, getTOTAL_PAGO());
            cmd.setString(9, getNOM_USU());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar personal rol cls: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean PERSONAL_ROL_MODIFICAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_PERSONAL_ROL_MODIFICAR ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setDouble(1, getCOD_ROL());
            cmd.setString(2, getCOD_TUR_UO());
            cmd.setString(3, getDIA_ROL());
            cmd.setString(4, getMES_ROL());
            cmd.setString(5, getANIO_ROL());
            cmd.setInt(6, getLIMITE_CONSULTA_DIA());
            cmd.setString(7, getTOTAL_HORAS());
            cmd.setDouble(8, getTOTAL_PAGO());
            cmd.setString(9, getNOM_USU());
            
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error modificar personal rol cls: " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean PERSONAL_ROL_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_PERSONAL_ROL_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_ROL());
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
    
    public String PERSONAL_COD_ROL()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="EXEC PERSONAL_PERSONAL_COD_ROL";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error COD ROL: " + ex.getMessage());
        }
        return cod;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCOD_TUR_UO() {
        return COD_TUR_UO;
    }

    public void setCOD_TUR_UO(String COD_TUR_UO) {
        this.COD_TUR_UO = COD_TUR_UO;
    }

    public int getID_PER_UNI_ORG() {
        return ID_PER_UNI_ORG;
    }

    public void setID_PER_UNI_ORG(int ID_PER_UNI_ORG) {
        this.ID_PER_UNI_ORG = ID_PER_UNI_ORG;
    }

    public String getDIA_ROL() {
        return DIA_ROL;
    }

    public void setDIA_ROL(String DIA_ROL) {
        this.DIA_ROL = DIA_ROL;
    }

    public String getMES_ROL() {
        return MES_ROL;
    }

    public void setMES_ROL(String MES_ROL) {
        this.MES_ROL = MES_ROL;
    }

    public String getANIO_ROL() {
        return ANIO_ROL;
    }

    public void setANIO_ROL(String ANIO_ROL) {
        this.ANIO_ROL = ANIO_ROL;
    }

    public int getLIMITE_CONSULTA_DIA() {
        return LIMITE_CONSULTA_DIA;
    }

    public void setLIMITE_CONSULTA_DIA(int LIMITE_CONSULTA_DIA) {
        this.LIMITE_CONSULTA_DIA = LIMITE_CONSULTA_DIA;
    }

    public String getTOTAL_HORAS() {
        return TOTAL_HORAS;
    }

    public void setTOTAL_HORAS(String TOTAL_HORAS) {
        this.TOTAL_HORAS = TOTAL_HORAS;
    }

    public Double getTOTAL_PAGO() {
        return TOTAL_PAGO;
    }

    public void setTOTAL_PAGO(Double TOTAL_PAGO) {
        this.TOTAL_PAGO = TOTAL_PAGO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public int getCOD_ROL() {
        return COD_ROL;
    }

    public void setCOD_ROL(int COD_ROL) {
        this.COD_ROL = COD_ROL;
    }
  
}
