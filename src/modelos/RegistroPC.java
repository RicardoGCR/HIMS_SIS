/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;
import vista.ConfRegistroPC;

/**
 *
 * @author PC-SISTEMA
 */
public class RegistroPC {
    private Connection cn;
    private int PA_ID;
    private String NOM_PC;  
private String NOM_USU;  
private int NRO_PC;
private int AR_ID; 
private int SE_ID; 
private String modulo;

 public RegistroPC(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
 
 
     public void CajaPC_Listar(){
        String consulta="";
        try {
            consulta="[CAJA_PC_NOMBRE] ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                ConfRegistroPC.txtPC.setText(r.getString(1)); 
//                ConfRegistroPC.txtResumen.setText(r.getString(1)); 

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
     
      

       public boolean NuevoTerminal(){
        boolean resp = false;
        try{
            String sql = "exec CONFIGURACION_PC_AREA_insertar ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, getCod_nomen_caja());
            cmd.setString(1, getModulo());
            cmd.setInt(2, getSE_ID());
            cmd.setInt(3, getAR_ID());
            cmd.setString(4,getNOM_PC());
            cmd.setString(5, getNOM_USU());
            cmd.setInt(6, getNRO_PC());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
       public boolean ModificarTerminal(){
        boolean resp = false;
        try{
            String sql = "exec CONFIGURACION_PC_AREA_modificar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, getCod_nomen_caja());
            cmd.setString(1, getModulo());
            cmd.setInt(2, getSE_ID());
            cmd.setInt(3, getAR_ID());
            cmd.setString(4,getNOM_PC());
            cmd.setString(5, getNOM_USU());
            cmd.setInt(6, getNRO_PC());
            cmd.setInt(7, getPA_ID());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
       
       public int VerificarNumero(String modulo,String nombre){
        int resultado=0;
        try
        {
            String sql = "select * from SISTEMA_CONFIGURACION_PC_AREA where PA_MODULO =? AND NRO_PC=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, modulo);
            cmd.setString(2, nombre);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            //getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error verificacion repetidos: " + ex.getMessage());
        }
        return resultado;
    }
       public boolean PC_eliminar(){
           
        boolean resp = false;
        try{
            String sql = "exec CONFIGURACION_PC_AREA_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getPA_ID());
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
    /**
     * @return the cn
     */
    public Connection getCn() {
        return cn;
    }

    /**
     * @param cn the cn to set
     */
    public void setCn(Connection cn) {
        this.cn = cn;
    }

    /**
     * @return the NOM_USU
     */
    public String getNOM_USU() {
        return NOM_USU;
    }

    /**
     * @param NOM_USU the NOM_USU to set
     */
    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    /**
     * @return the NRO_PC
     */
    public int getNRO_PC() {
        return NRO_PC;
    }

    /**
     * @param NRO_PC the NRO_PC to set
     */
    public void setNRO_PC(int NRO_PC) {
        this.NRO_PC = NRO_PC;
    }

    /**
     * @return the AR_ID
     */
    public int getAR_ID() {
        return AR_ID;
    }

    /**
     * @param AR_ID the AR_ID to set
     */
    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }

    /**
     * @return the SE_ID
     */
    public int getSE_ID() {
        return SE_ID;
    }

    /**
     * @param SE_ID the SE_ID to set
     */
    public void setSE_ID(int SE_ID) {
        this.SE_ID = SE_ID;
    }

    /**
     * @return the modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the NOM_PC
     */
    public String getNOM_PC() {
        return NOM_PC;
    }

    /**
     * @param NOM_PC the NOM_PC to set
     */
    public void setNOM_PC(String NOM_PC) {
        this.NOM_PC = NOM_PC;
    }

    /**
     * @return the PA_ID
     */
    public int getPA_ID() {
        return PA_ID;
    }

    /**
     * @param PA_ID the PA_ID to set
     */
    public void setPA_ID(int PA_ID) {
        this.PA_ID = PA_ID;
    }
}
