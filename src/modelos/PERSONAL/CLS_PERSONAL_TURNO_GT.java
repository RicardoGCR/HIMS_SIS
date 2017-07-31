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
public class CLS_PERSONAL_TURNO_GT {
    private Connection cn;
    private String COD_TUR;
    private String COD_HORARIO;
    private String COD_TIPO_TURNO;
    private String NOMENCLATURA;
    private String DESCRIPCION;
    private String HORA_TOTAL;
    private String COD_USU;
    
    public CLS_PERSONAL_TURNO_GT()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String PERSONAL_TURNOS_GT_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec PERSONAL_TURNOS_GENERAR_ID";
        ResultSet r;
        r=cn.Listar(consulta);
        if(r.next())
            {
               cod = r.getString(1);
            }
        }catch(Exception ex)
        {
            System.out.println("Error generar ID TURNOS GT: " + ex.getMessage());
        }
        return cod;
    }
    
    public String PERSONAL_TURNO_GT_COD_USUARIO(String descrip){
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
            System.out.println("Error cod USUARIO TURNO GT: " + ex.getMessage());
        }
        return cod;
    }
    
    public boolean PERSONAL_TURNO_GT_INSERTAR(){
        boolean resp = false;
        try
        {
            String sql = "exec PERSONAL_TURNOS_GT_INSERTAR ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCOD_UNI_ORG_ACTI());
            cmd.setString(1, getCOD_HORARIO());
            cmd.setString(2, getCOD_TIPO_TURNO());
            cmd.setString(3, getNOMENCLATURA());
            cmd.setString(4, getDESCRIPCION());
            cmd.setString(5, getHORA_TOTAL());
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
            System.out.println("Error guardar TURNOS GT: " + ex.getMessage());
        }
        return resp;
    }
    
    public String PERSONAL_COD_TIPO_TURNOS_GT(String descrip){
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
            System.out.println("Error cod tipo turnos GT: " + ex.getMessage());
        }
        return cod;
    }
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getCOD_TUR() {
        return COD_TUR;
    }

    public void setCOD_TUR(String COD_TUR) {
        this.COD_TUR = COD_TUR;
    }

    public String getCOD_HORARIO() {
        return COD_HORARIO;
    }

    public void setCOD_HORARIO(String COD_HORARIO) {
        this.COD_HORARIO = COD_HORARIO;
    }

    public String getCOD_TIPO_TURNO() {
        return COD_TIPO_TURNO;
    }

    public void setCOD_TIPO_TURNO(String COD_TIPO_TURNO) {
        this.COD_TIPO_TURNO = COD_TIPO_TURNO;
    }

    public String getNOMENCLATURA() {
        return NOMENCLATURA;
    }

    public void setNOMENCLATURA(String NOMENCLATURA) {
        this.NOMENCLATURA = NOMENCLATURA;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getHORA_TOTAL() {
        return HORA_TOTAL;
    }

    public void setHORA_TOTAL(String HORA_TOTAL) {
        this.HORA_TOTAL = HORA_TOTAL;
    }

    public String getCOD_USU() {
        return COD_USU;
    }

    public void setCOD_USU(String COD_USU) {
        this.COD_USU = COD_USU;
    }
    
    
}
