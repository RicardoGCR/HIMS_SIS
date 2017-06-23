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
 * @author USUARIO
 */
public class RX_EC_RESULTADO_CAB {
    private Connection cn;
    private int COD_EXAMEN_CAB;
    private String NUMERO_RESULTADO;
    private String COD_PERSONAL_RESULTADO;
    private String NOMBRE_PERSONAL_RESULTADO;
    private String COD_PERSONAL_RESULTADO_REG;
    private String NOMBRE_PERSONAL_RESULTADO_REG;
    private String NOM_USU;
    
    public RX_EC_RESULTADO_CAB()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String RX_EC_resultado_generarNum()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_RESULTADO_GENERAR_NUM";
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
    
    public boolean RX_EC_RESULTADO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_RESULTADO_CABECERA_GUARDAR ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_EXAMEN_CAB());
            cmd.setString(2, getNUMERO_RESULTADO());
            cmd.setString(3, getCOD_PERSONAL_RESULTADO());
            cmd.setString(4, getNOMBRE_PERSONAL_RESULTADO());
            cmd.setString(5, getCOD_PERSONAL_RESULTADO_REG());
            cmd.setString(6, getNOMBRE_PERSONAL_RESULTADO_REG());          
            cmd.setString(7, getNOM_USU());
            
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

    public String RX_EC_ID_CAB_RES()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID_CAB_RES_EX";
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
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCOD_EXAMEN_CAB() {
        return COD_EXAMEN_CAB;
    }

    public void setCOD_EXAMEN_CAB(int COD_EXAMEN_CAB) {
        this.COD_EXAMEN_CAB = COD_EXAMEN_CAB;
    }

    public String getNUMERO_RESULTADO() {
        return NUMERO_RESULTADO;
    }

    public void setNUMERO_RESULTADO(String NUMERO_RESULTADO) {
        this.NUMERO_RESULTADO = NUMERO_RESULTADO;
    }

    public String getCOD_PERSONAL_RESULTADO() {
        return COD_PERSONAL_RESULTADO;
    }

    public void setCOD_PERSONAL_RESULTADO(String COD_PERSONAL_RESULTADO) {
        this.COD_PERSONAL_RESULTADO = COD_PERSONAL_RESULTADO;
    }

    public String getNOMBRE_PERSONAL_RESULTADO() {
        return NOMBRE_PERSONAL_RESULTADO;
    }

    public void setNOMBRE_PERSONAL_RESULTADO(String NOMBRE_PERSONAL_RESULTADO) {
        this.NOMBRE_PERSONAL_RESULTADO = NOMBRE_PERSONAL_RESULTADO;
    }

    public String getCOD_PERSONAL_RESULTADO_REG() {
        return COD_PERSONAL_RESULTADO_REG;
    }

    public void setCOD_PERSONAL_RESULTADO_REG(String COD_PERSONAL_RESULTADO_REG) {
        this.COD_PERSONAL_RESULTADO_REG = COD_PERSONAL_RESULTADO_REG;
    }

    public String getNOMBRE_PERSONAL_RESULTADO_REG() {
        return NOMBRE_PERSONAL_RESULTADO_REG;
    }

    public void setNOMBRE_PERSONAL_RESULTADO_REG(String NOMBRE_PERSONAL_RESULTADO_REG) {
        this.NOMBRE_PERSONAL_RESULTADO_REG = NOMBRE_PERSONAL_RESULTADO_REG;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
}
