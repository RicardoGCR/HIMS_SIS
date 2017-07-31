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
 * @author USUARIO
 */
public class CLS_PERSONAL_HORARIOS {
    private Connection cn;
    private String COD_HORARIOS;
    private String HORA_INICIO;
    private String HORA_FIN;
    private String HORA_COMPLETO;
    private String HORA_TOTAL;
    private String PASAR_DIA;
    private String COD_USU;
    
    public CLS_PERSONAL_HORARIOS()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public String PERSONAL_HORARIOS_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec PERSONAL_HORARIOS_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error generar ID HORARIOS: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_HORARIO_COD_USUARIO(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT Usu_Codigo FROM USUARIO WHERE Usu_Usuario = ? ";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error cod USUARIO: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean PERSONAL_HORARIO_INSERTAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_HORARIOS_INSERTAR ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCOD_UNI_ORG_ACTI());
            cmd.setString(1, getHORA_INICIO());
            cmd.setString(2, getHORA_FIN());
            cmd.setString(3, getHORA_COMPLETO());
            cmd.setString(4, getHORA_TOTAL());
            cmd.setString(5, getPASAR_DIA());
            cmd.setString(6, getCOD_USU());
            
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error guardar HORARIOS: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCOD_HORARIOS() {
        return COD_HORARIOS;
    }

    public void setCOD_HORARIOS(String COD_HORARIOS) {
        this.COD_HORARIOS = COD_HORARIOS;
    }

    public String getHORA_INICIO() {
        return HORA_INICIO;
    }

    public void setHORA_INICIO(String HORA_INICIO) {
        this.HORA_INICIO = HORA_INICIO;
    }

    public String getHORA_FIN() {
        return HORA_FIN;
    }

    public void setHORA_FIN(String HORA_FIN) {
        this.HORA_FIN = HORA_FIN;
    }

    public String getHORA_COMPLETO() {
        return HORA_COMPLETO;
    }

    public void setHORA_COMPLETO(String HORA_COMPLETO) {
        this.HORA_COMPLETO = HORA_COMPLETO;
    }

    public String getHORA_TOTAL() {
        return HORA_TOTAL;
    }

    public void setHORA_TOTAL(String HORA_TOTAL) {
        this.HORA_TOTAL = HORA_TOTAL;
    }

    public String getPASAR_DIA() {
        return PASAR_DIA;
    }

    public void setPASAR_DIA(String PASAR_DIA) {
        this.PASAR_DIA = PASAR_DIA;
    }

    public String getCOD_USU() {
        return COD_USU;
    }

    public void setCOD_USU(String COD_USU) {
        this.COD_USU = COD_USU;
    }
    
    
    
}
