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
import vista.RX_EC.RX_EC_EXAMEN_CAB_RESULTADO;

/**
 *
 * @author MYS3
 */
public class RX_EC_RESULTADO_DETALLE_DIAGNOSTICO {
    private Connection cn;
    private int COD_DETALLE_RESULTADO;
    private int ID_CIE10; 
    private String NOM_USU;
    
    public RX_EC_RESULTADO_DETALLE_DIAGNOSTICO()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean RX_EC_RESULTADO_DETALLE_DIAGNOSTICO_GUARDAR(){
        boolean resp = false;
        try
        {
            String sql = "exec RX_EC_RESULTADO_DETALLE_DIAGNOSTICO_INSERTAR ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getCOD_DETALLE_RESULTADO());
            cmd.setInt(2, getID_CIE10());
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
    
//    public String RX_EC_ID_DX()
//    {
//        String cod="";
//        try
//        {
//            String sql = "RX_EC_ID";
//            PreparedStatement cmd = getCn().prepareStatement(sql);
//            ResultSet rs = cmd.executeQuery();
//            if(rs.next())
//            {
//               RX_EC_EXAMEN_CAB_RESULTADO.lblId_DX.setText(rs.getString(1));
//            }
//        }
//        catch(Exception ex)
//        {
//            System.out.println("RESULTADO_DETALLE_ID: " + ex.getMessage());
//        }
//        return cod;
//    }
    
    public String RX_EC_ID()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec RX_EC_ID";
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

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public int getCOD_DETALLE_RESULTADO() {
        return COD_DETALLE_RESULTADO;
    }

    public void setCOD_DETALLE_RESULTADO(int COD_DETALLE_RESULTADO) {
        this.COD_DETALLE_RESULTADO = COD_DETALLE_RESULTADO;
    }

    public int getID_CIE10() {
        return ID_CIE10;
    }

    public void setID_CIE10(int ID_CIE10) {
        this.ID_CIE10 = ID_CIE10;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }
    
    
    
}
