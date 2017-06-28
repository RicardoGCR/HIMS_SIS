/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import vista.Caja.Caja_Registro;

/**
 *
 * @author MYS1
 */
public class Caja_PC_Registro {
DefaultTableModel m;
private Connection cn;
private String NOM_USU;  
private int NRO_PC;
private int AR_ID;  

    public void CajaPC_Listar(){
        String consulta="";
        try {
            consulta="[CAJA_PC_NOMBRE] ";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
//            cmd.setString(1, cp_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                Caja_Registro.txtPC.setText(r.getString(1)); 

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
                Caja_Registro.lblUsu.setText("<html>"+r.getString(2)+"<span style=\"font-size:'14px'\"><br>"+"Usuario, "+r.getString(1)+"<html>");
                Caja_Registro.lblResumenUsuario.setText("<html>"+"Cajero "+r.getString(2)+"<html>");
       

                }
            //
        } catch (Exception e) {
            System.out.println("Error: PC: " + e.getMessage());
        }
    }
        public void VerificarExistencia(String Usuario,JTable tabla){
        String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[1];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="exec CAJA_VERIFICAR_EXISTENCIA_PC_CONFIGURACION ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, Usuario); ///bus1 esto se busca
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
        } catch (Exception e) {
            System.out.println("Error: CONSULTAR PC EXISTENTE " + e.getMessage());
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
    
    public int VerificarNumero(String nombre){
        int resultado=0;
        try
        {
            String sql = "select * from SISTEMA_CONFIGURACION_PC_AREA where PA_MODULO ='CC' AND NRO_PC=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
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
     
     public Caja_PC_Registro(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public String getNOM_USU() {
        return NOM_USU;
    }

    public void setNOM_USU(String NOM_USU) {
        this.NOM_USU = NOM_USU;
    }

    public int getNRO_PC() {
        return NRO_PC;
    }

    public void setNRO_PC(int NRO_PC) {
        this.NRO_PC = NRO_PC;
    }

    public int getAR_ID() {
        return AR_ID;
    }

    public void setAR_ID(int AR_ID) {
        this.AR_ID = AR_ID;
    }
    
    
    
}
