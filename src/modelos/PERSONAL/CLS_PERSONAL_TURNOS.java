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
public class CLS_PERSONAL_TURNOS {
    private Connection cn;
    private String COD_TUR_UO;
    private String COD_TURNO;
    private String COD_UNI_ORGANICA_JERAR;
    private String NOM_USU;
    
    
    public CLS_PERSONAL_TURNOS()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String PERSONAL_COD_TIPO_TURNOS(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT cod_tipo_turno FROM PERSONAL_TIPO_TURNOS WHERE  nom_tipo_turno = ?";
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
            System.out.println("Error cod tipo turnos: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean PERSONAL_TURNOS_UO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_TURNOS_UNIDAD_ORGANICA_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCOD_UNI_ORG_ACTI());
            cmd.setString(1, getCOD_TURNO());
            cmd.setString(2, getCOD_UNI_ORGANICA_JERAR());
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
            System.out.println("Error guardar TURNOS UO: " + ex.getMessage());
        }
        return resp;
    }

    public String PERSONAL_TURNOS_UO_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec PERSONAL_TURNOS_UNIDAD_ORGANICA_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error generar ID TURNOS UO: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean PERSONAL_TURNOS_UNIDAD_ORGANICA_ELIMINAR()
    {
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_TURNOS_UNIDAD_ORGANICA_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCOD_TUR_UO());
            if(!cmd.execute()){
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error ELIMINAR TURNOS UNIDAD ORGANICA: " + ex.getMessage());
        }
        return resp;
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

    public String getCOD_TURNO() {
        return COD_TURNO;
    }

    public void setCOD_TURNO(String COD_TURNO) {
        this.COD_TURNO = COD_TURNO;
    }

    public String getCOD_UNI_ORGANICA_JERAR() {
        return COD_UNI_ORGANICA_JERAR;
    }

    public void setCOD_UNI_ORGANICA_JERAR(String COD_UNI_ORGANICA_JERAR) {
        this.COD_UNI_ORGANICA_JERAR = COD_UNI_ORGANICA_JERAR;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
 
    
    
}
