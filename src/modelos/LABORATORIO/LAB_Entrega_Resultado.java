/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.LABORATORIO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class LAB_Entrega_Resultado {
    private Connection cn;
     private String cod_entrega_resul_exa;
             private String cod_cab_resultado_mu_ana;
             private String cod_per_entrega;
             private String nombre_per_entrega;
             private String tipo_per_recepcion;
             private String cod_per_recepcion;
             private String nombre_per_recepcion;
             private String ar_id;
             private String ar_desc;
             private String id_hc;
             private String paciente_hc;
             private String fecha_entrega_exa;
             private String hora_entrega_exa;
             private String nom_usu;
    
    public LAB_Entrega_Resultado(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
     public boolean LAB_Entrega_Resultado_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ENTREGA_RESULTADO_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_entrega_resul_exa());
            cmd.setString(2, getCod_cab_resultado_mu_ana());
            cmd.setString(3, getCod_per_entrega());
            cmd.setString(4, getNombre_per_entrega());
            cmd.setString(5, getTipo_per_recepcion());
            cmd.setString(6, getCod_per_recepcion());
            cmd.setString(7, getNombre_per_recepcion());
            cmd.setString(8, getAr_id());
            cmd.setString(9, getAr_desc());
            cmd.setString(10, getId_hc());
            cmd.setString(11, getPaciente_hc());
            cmd.setString(12, getFecha_entrega_exa());
            cmd.setString(13, getHora_entrega_exa());
            cmd.setString(14, getNom_usu());
 
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
     
    public String LAB_Entrega_Resultado_generarid(){
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_ENTREGA_RESULTADO_generarid";
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

     public boolean LAB_Entrega_Resultado_Estado(String cod_cab_resul){
         boolean resp = false;
        try{
            String sql = "sp_LAB_ENTREGA_RESULTADO_ESTADO ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, cod_cab_resul);
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
     * @return the cod_entrega_resul_exa
     */
    public String getCod_entrega_resul_exa() {
        return cod_entrega_resul_exa;
    }

    /**
     * @param cod_entrega_resul_exa the cod_entrega_resul_exa to set
     */
    public void setCod_entrega_resul_exa(String cod_entrega_resul_exa) {
        this.cod_entrega_resul_exa = cod_entrega_resul_exa;
    }

    /**
     * @return the cod_cab_resultado_mu_ana
     */
    public String getCod_cab_resultado_mu_ana() {
        return cod_cab_resultado_mu_ana;
    }

    /**
     * @param cod_cab_resultado_mu_ana the cod_cab_resultado_mu_ana to set
     */
    public void setCod_cab_resultado_mu_ana(String cod_cab_resultado_mu_ana) {
        this.cod_cab_resultado_mu_ana = cod_cab_resultado_mu_ana;
    }

    /**
     * @return the cod_per_entrega
     */
    public String getCod_per_entrega() {
        return cod_per_entrega;
    }

    /**
     * @param cod_per_entrega the cod_per_entrega to set
     */
    public void setCod_per_entrega(String cod_per_entrega) {
        this.cod_per_entrega = cod_per_entrega;
    }

    /**
     * @return the nombre_per_entrega
     */
    public String getNombre_per_entrega() {
        return nombre_per_entrega;
    }

    /**
     * @param nombre_per_entrega the nombre_per_entrega to set
     */
    public void setNombre_per_entrega(String nombre_per_entrega) {
        this.nombre_per_entrega = nombre_per_entrega;
    }

    /**
     * @return the tipo_per_recepcion
     */
    public String getTipo_per_recepcion() {
        return tipo_per_recepcion;
    }

    /**
     * @param tipo_per_recepcion the tipo_per_recepcion to set
     */
    public void setTipo_per_recepcion(String tipo_per_recepcion) {
        this.tipo_per_recepcion = tipo_per_recepcion;
    }

    /**
     * @return the cod_per_recepcion
     */
    public String getCod_per_recepcion() {
        return cod_per_recepcion;
    }

    /**
     * @param cod_per_recepcion the cod_per_recepcion to set
     */
    public void setCod_per_recepcion(String cod_per_recepcion) {
        this.cod_per_recepcion = cod_per_recepcion;
    }

    /**
     * @return the nombre_per_recepcion
     */
    public String getNombre_per_recepcion() {
        return nombre_per_recepcion;
    }

    /**
     * @param nombre_per_recepcion the nombre_per_recepcion to set
     */
    public void setNombre_per_recepcion(String nombre_per_recepcion) {
        this.nombre_per_recepcion = nombre_per_recepcion;
    }

    /**
     * @return the ar_id
     */
    public String getAr_id() {
        return ar_id;
    }

    /**
     * @param ar_id the ar_id to set
     */
    public void setAr_id(String ar_id) {
        this.ar_id = ar_id;
    }

    /**
     * @return the ar_desc
     */
    public String getAr_desc() {
        return ar_desc;
    }

    /**
     * @param ar_desc the ar_desc to set
     */
    public void setAr_desc(String ar_desc) {
        this.ar_desc = ar_desc;
    }

    /**
     * @return the id_hc
     */
    public String getId_hc() {
        return id_hc;
    }

    /**
     * @param id_hc the id_hc to set
     */
    public void setId_hc(String id_hc) {
        this.id_hc = id_hc;
    }

    /**
     * @return the paciente_hc
     */
    public String getPaciente_hc() {
        return paciente_hc;
    }

    /**
     * @param paciente_hc the paciente_hc to set
     */
    public void setPaciente_hc(String paciente_hc) {
        this.paciente_hc = paciente_hc;
    }

    /**
     * @return the fecha_entrega_exa
     */
    public String getFecha_entrega_exa() {
        return fecha_entrega_exa;
    }

    /**
     * @param fecha_entrega_exa the fecha_entrega_exa to set
     */
    public void setFecha_entrega_exa(String fecha_entrega_exa) {
        this.fecha_entrega_exa = fecha_entrega_exa;
    }

    /**
     * @return the hora_entrega_exa
     */
    public String getHora_entrega_exa() {
        return hora_entrega_exa;
    }

    /**
     * @param hora_entrega_exa the hora_entrega_exa to set
     */
    public void setHora_entrega_exa(String hora_entrega_exa) {
        this.hora_entrega_exa = hora_entrega_exa;
    }

    /**
     * @return the nom_usu
     */
    public String getNom_usu() {
        return nom_usu;
    }

    /**
     * @param nom_usu the nom_usu to set
     */
    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }
    
  
}
