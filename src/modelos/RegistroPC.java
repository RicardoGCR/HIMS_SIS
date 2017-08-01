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
private String NOM_USU;  
private int NRO_PC;
private int AR_ID; 

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

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
     
      public void PERFIL_USUARIO(String cp_id){
        String consulta="";
        try {
            consulta="[CAJA_PERSONAL_USUARIO] ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                ConfRegistroPC.lblUsu.setText("<html>"+r.getString(2)+"<span style=\"font-size:'14px'\"><br>"+"Usuario, "+r.getString(1)+"<html>");
//                ConfRegistroPC.lblResumenUsuario.setText("<html>"+"Cajero "+r.getString(2)+"<html>");
       

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
      

       public boolean NuevoTerminal(){
        boolean resp = false;
        try{
            String sql = "exec CAJA_CONFIGURAR_TERMINAL "
                        + "?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            //cmd.setString(1, getCod_nomen_caja());
            cmd.setInt(1, getAR_ID());
            cmd.setString(2, getNOM_USU());
            cmd.setInt(3, getNRO_PC());

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
}
