/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.SALA_OPERACIONES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import servicios.Conexion;

/**
 *
 * @author MYS3
 */
public class SDO_SOLICITUD_DETALLE_DIAGNOSTICO {
    private Connection cn;
    private String ID_SOLICITUD_DETALLE_DIAGNOSTICO;
    private String ID_SOLICITUD;
    private String ID_CIE10;
    private String NOM_USU;
    
    public SDO_SOLICITUD_DETALLE_DIAGNOSTICO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
        public boolean SDO_detalleDiagnostico_Guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec SALA_OPERACION_SOLICITUD_DETALLE_DIAGNOSTICO_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_SOLICITUD());
            cmd.setString(2, getID_CIE10());
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
            System.out.println("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    
    public boolean eliminarDetalleDiagnostico()
    {
        boolean resp = false;
        try{
            String sql = "exec SALA_OPERACION_SOLICITUD_DETALLE_DIAGNOSTICO_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getID_SOLICITUD());
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
        

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getID_SOLICITUD_DETALLE_DIAGNOSTICO() {
        return ID_SOLICITUD_DETALLE_DIAGNOSTICO;
    }

    public void setID_SOLICITUD_DETALLE_DIAGNOSTICO(String ID_SOLICITUD_DETALLE_DIAGNOSTICO) {
        this.ID_SOLICITUD_DETALLE_DIAGNOSTICO = ID_SOLICITUD_DETALLE_DIAGNOSTICO;
    }

    public String getID_SOLICITUD() {
        return ID_SOLICITUD;
    }

    public void setID_SOLICITUD(String ID_SOLICITUD) {
        this.ID_SOLICITUD = ID_SOLICITUD;
    }

    public String getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(String ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
    
    
}
