/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SALA_OPERACIONES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SDO_SOLICITUD {
     private Connection cn;
     private String ID_SOLICITUD;
     private String ID_HC;
     private String FECHA_SOLICITUD;
     private String HORA_SOLICITUD;
     private int AUTOGENERADO_SEG;
     private int SE_ID;
     private String COD_NOMEN_CAJA;
     private String ID_TIPO_ANESTESIA;
     private String COD_PER_SOLICITUD_OPERA;
     private String NOMBRE_PER_SOLICITUD_OPERA;
     private String COD_PER_SOLICITUD_OPERA1;
     private String NOMBRE_PER_SOLICITUD_OPERA1;
     private String COD_PER__SOLICITUD_JEFE_SERVICIO;
     private String NOMBRE_PER_SOLICITUD_JEFE_SERVICIO;
     private String NOM_USU;
     
    public SDO_SOLICITUD()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public String SDO_SOLICITUD_GENERAR_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec SALA_OPERACION_SOLICITUD_GENERAR_ID";
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
    
    public boolean guardarSolicitud()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_SOLICITUD_INSERTAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_SOLICITUD());
            cmd.setString(2, getID_HC());
            cmd.setString(3, getFECHA_SOLICITUD());
            cmd.setString(4, getHORA_SOLICITUD());
            cmd.setInt(5, getAUTOGENERADO_SEG());
            cmd.setInt(6, getSE_ID());
            cmd.setString(7, getCOD_NOMEN_CAJA());
            cmd.setString(8, getID_TIPO_ANESTESIA());
            cmd.setString(9, getCOD_PER_SOLICITUD_OPERA());
            cmd.setString(10, getNOMBRE_PER_SOLICITUD_OPERA());
            cmd.setString(11, getCOD_PER_SOLICITUD_OPERA1());
            cmd.setString(12, getNOMBRE_PER_SOLICITUD_OPERA1());
            cmd.setString(13, getCOD_PER__SOLICITUD_JEFE_SERVICIO());
            cmd.setString(14, getNOMBRE_PER_SOLICITUD_JEFE_SERVICIO());
            cmd.setString(15, getNOM_USU());
 
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
    
        public boolean SDO_Solicitud_Modificar()
    {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_SOLICITUD_MODIFICAR ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_SOLICITUD());
            cmd.setString(2, getID_HC());
            cmd.setString(3, getFECHA_SOLICITUD());
            cmd.setString(4, getHORA_SOLICITUD());
            cmd.setInt(5, getAUTOGENERADO_SEG());
            cmd.setInt(6, getSE_ID());
            cmd.setString(7, getCOD_NOMEN_CAJA());
            cmd.setString(8, getID_TIPO_ANESTESIA());
            cmd.setString(9, getCOD_PER_SOLICITUD_OPERA());
            cmd.setString(10, getNOMBRE_PER_SOLICITUD_OPERA());
            cmd.setString(11, getCOD_PER_SOLICITUD_OPERA1());
            cmd.setString(12, getNOMBRE_PER_SOLICITUD_OPERA1());
            cmd.setString(13, getCOD_PER__SOLICITUD_JEFE_SERVICIO());
            cmd.setString(14, getNOMBRE_PER_SOLICITUD_JEFE_SERVICIO());
            cmd.setString(15, getNOM_USU());
            
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
    
        
    public int codServicio(String descrip){
        int cod=0;
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT SE_ID FROM SISTEMA_SERVICIO where SE_DESC = ?";
            PreparedStatement cmd = con.prepareStatement(sql);
            cmd.setString(1, descrip);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getInt(1);
            }
            cmd.close();
            con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    
    
        public String codAnestesia(String descrip){
        String cod="";
        try
        {
            Connection con;
            con = getCn(); 
            String sql = "SELECT id_tipo_anestesia FROM SALA_OPERACION_TIPO_ANESTESIA where DESCRIP_TIPO_ANESTESIA = ?";
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
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
    }
    

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_SOLICITUD() {
        return ID_SOLICITUD;
    }

    public void setID_SOLICITUD(String ID_SOLICITUD) {
        this.ID_SOLICITUD = ID_SOLICITUD;
    }

    public String getID_HC() {
        return ID_HC;
    }

    public void setID_HC(String ID_HC) {
        this.ID_HC = ID_HC;
    }

    public String getFECHA_SOLICITUD() {
        return FECHA_SOLICITUD;
    }

    public void setFECHA_SOLICITUD(String FECHA_SOLICITUD) {
        this.FECHA_SOLICITUD = FECHA_SOLICITUD;
    }

    public String getHORA_SOLICITUD() {
        return HORA_SOLICITUD;
    }

    public void setHORA_SOLICITUD(String HORA_SOLICITUD) {
        this.HORA_SOLICITUD = HORA_SOLICITUD;
    }

    public int getAUTOGENERADO_SEG() {
        return AUTOGENERADO_SEG;
    }

    public void setAUTOGENERADO_SEG(int AUTOGENERADO_SEG) {
        this.AUTOGENERADO_SEG = AUTOGENERADO_SEG;
    }

    public int getSE_ID() {
        return SE_ID;
    }

    public void setSE_ID(int SE_ID) {
        this.SE_ID = SE_ID;
    }

    public String getCOD_NOMEN_CAJA() {
        return COD_NOMEN_CAJA;
    }

    public void setCOD_NOMEN_CAJA(String COD_NOMEN_CAJA) {
        this.COD_NOMEN_CAJA = COD_NOMEN_CAJA;
    }

    public String getID_TIPO_ANESTESIA() {
        return ID_TIPO_ANESTESIA;
    }

    public void setID_TIPO_ANESTESIA(String ID_TIPO_ANESTESIA) {
        this.ID_TIPO_ANESTESIA = ID_TIPO_ANESTESIA;
    }

    public String getCOD_PER_SOLICITUD_OPERA() {
        return COD_PER_SOLICITUD_OPERA;
    }

    public void setCOD_PER_SOLICITUD_OPERA(String COD_PER_SOLICITUD_OPERA) {
        this.COD_PER_SOLICITUD_OPERA = COD_PER_SOLICITUD_OPERA;
    }

    public String getNOMBRE_PER_SOLICITUD_OPERA() {
        return NOMBRE_PER_SOLICITUD_OPERA;
    }

    public void setNOMBRE_PER_SOLICITUD_OPERA(String NOMBRE_PER_SOLICITUD_OPERA) {
        this.NOMBRE_PER_SOLICITUD_OPERA = NOMBRE_PER_SOLICITUD_OPERA;
    }

    public String getCOD_PER_SOLICITUD_OPERA1() {
        return COD_PER_SOLICITUD_OPERA1;
    }

    public void setCOD_PER_SOLICITUD_OPERA1(String COD_PER_SOLICITUD_OPERA1) {
        this.COD_PER_SOLICITUD_OPERA1 = COD_PER_SOLICITUD_OPERA1;
    }

    public String getNOMBRE_PER_SOLICITUD_OPERA1() {
        return NOMBRE_PER_SOLICITUD_OPERA1;
    }

    public void setNOMBRE_PER_SOLICITUD_OPERA1(String NOMBRE_PER_SOLICITUD_OPERA1) {
        this.NOMBRE_PER_SOLICITUD_OPERA1 = NOMBRE_PER_SOLICITUD_OPERA1;
    }

    public String getCOD_PER__SOLICITUD_JEFE_SERVICIO() {
        return COD_PER__SOLICITUD_JEFE_SERVICIO;
    }

    public void setCOD_PER__SOLICITUD_JEFE_SERVICIO(String COD_PER__SOLICITUD_JEFE_SERVICIO) {
        this.COD_PER__SOLICITUD_JEFE_SERVICIO = COD_PER__SOLICITUD_JEFE_SERVICIO;
    }

    public String getNOMBRE_PER_SOLICITUD_JEFE_SERVICIO() {
        return NOMBRE_PER_SOLICITUD_JEFE_SERVICIO;
    }

    public void setNOMBRE_PER_SOLICITUD_JEFE_SERVICIO(String NOMBRE_PER_SOLICITUD_JEFE_SERVICIO) {
        this.NOMBRE_PER_SOLICITUD_JEFE_SERVICIO = NOMBRE_PER_SOLICITUD_JEFE_SERVICIO;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    


    
    
}
