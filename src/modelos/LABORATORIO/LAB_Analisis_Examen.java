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
public class LAB_Analisis_Examen {
    private Connection cn;
    private String cod_exa_ana;
    private String cod_clasi_exa;
    private String cod_nomen_caja;
    private String nombre_ana_exa;
    private String abrev_ana_exa;
    private int tiempo_hora;
    private int tiempo_min;
    private String tipo_procesamiento;
    private String restriccion_analisis;
    private String explicacion_met_proce;
    private String estado_detalle;
    private String observacion_ana_exa;
    private String nom_usu;

     public LAB_Analisis_Examen()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
         public boolean LAB_Analisis_Examen_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_ANALISIS_EXAMEN_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_exa_ana());
            cmd.setString(2, getCod_clasi_exa());
            cmd.setString(3, getCod_nomen_caja());
            cmd.setString(4, getNombre_ana_exa());
            cmd.setString(5, getAbrev_ana_exa());
            cmd.setInt(6, getTiempo_hora());
            cmd.setInt(7, getTiempo_min());
            cmd.setString(8, getTipo_procesamiento());
            cmd.setString(9, getRestriccion_analisis());
            cmd.setString(10, getExplicacion_met_proce());
            cmd.setString(11, getEstado_detalle());
            cmd.setString(12, getObservacion_ana_exa());
            cmd.setString(13, getNom_usu());
 
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
         
         
    public boolean LAB_Analisis_Examen_modificar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ANALISIS_EXAMEN_modificar ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
           cmd.setString(1, getCod_exa_ana());
            cmd.setString(2, getCod_clasi_exa());
            cmd.setString(3, getCod_nomen_caja());
            cmd.setString(4, getNombre_ana_exa());
            cmd.setString(5, getAbrev_ana_exa());
            cmd.setInt(6, getTiempo_hora());
            cmd.setInt(7, getTiempo_min());
            cmd.setString(8, getTipo_procesamiento());
            cmd.setString(9, getRestriccion_analisis());
            cmd.setString(10, getExplicacion_met_proce());
            cmd.setString(11, getEstado_detalle());
            cmd.setString(12, getObservacion_ana_exa());
            cmd.setString(13, getNom_usu());
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
    

    public boolean LAB_Analisis_Examen_eliminar()
    {
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ANALISIS_EXAMEN_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_exa_ana());
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
    
    
    
    public String LAB_Analisis_Examen_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_ANALISIS_EXAMEN_generarid";
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
    
    
      public int LAB_Analisis_Examen_ver(String clasif, String nomen)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM LABORATORIO_ANALISIS_EXAMEN where cod_clasi_exa=? and cod_nomen_caja=? AND estado_analisis_exa='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, clasif);
            cmd.setString(2, nomen);
            ResultSet rs = cmd.executeQuery();
            for (int i=0; rs.next (); i++)
            {
               resultado++;
            }
            
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return resultado;
    }
    
 
    public String LAB_Analisis_Examen_codigo(String codigo)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_clasi_exa FROM LABORATORIO_CLASIFICACION_EXAMEN where cod_uni_organica_jerar=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod;
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
     * @return the cod_exa_ana
     */
    public String getCod_exa_ana() {
        return cod_exa_ana;
    }

    /**
     * @param cod_exa_ana the cod_exa_ana to set
     */
    public void setCod_exa_ana(String cod_exa_ana) {
        this.cod_exa_ana = cod_exa_ana;
    }

    /**
     * @return the cod_clasi_exa
     */
    public String getCod_clasi_exa() {
        return cod_clasi_exa;
    }

    /**
     * @param cod_clasi_exa the cod_clasi_exa to set
     */
    public void setCod_clasi_exa(String cod_clasi_exa) {
        this.cod_clasi_exa = cod_clasi_exa;
    }

    /**
     * @return the cod_nomen_caja
     */
    public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    /**
     * @param cod_nomen_caja the cod_nomen_caja to set
     */
    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    /**
     * @return the nombre_ana_exa
     */
    public String getNombre_ana_exa() {
        return nombre_ana_exa;
    }

    /**
     * @param nombre_ana_exa the nombre_ana_exa to set
     */
    public void setNombre_ana_exa(String nombre_ana_exa) {
        this.nombre_ana_exa = nombre_ana_exa;
    }

    /**
     * @return the abrev_ana_exa
     */
    public String getAbrev_ana_exa() {
        return abrev_ana_exa;
    }

    /**
     * @param abrev_ana_exa the abrev_ana_exa to set
     */
    public void setAbrev_ana_exa(String abrev_ana_exa) {
        this.abrev_ana_exa = abrev_ana_exa;
    }

    /**
     * @return the tiempo_hora
     */
    public int getTiempo_hora() {
        return tiempo_hora;
    }

    /**
     * @param tiempo_hora the tiempo_hora to set
     */
    public void setTiempo_hora(int tiempo_hora) {
        this.tiempo_hora = tiempo_hora;
    }

    /**
     * @return the tiempo_min
     */
    public int getTiempo_min() {
        return tiempo_min;
    }

    /**
     * @param tiempo_min the tiempo_min to set
     */
    public void setTiempo_min(int tiempo_min) {
        this.tiempo_min = tiempo_min;
    }

    /**
     * @return the tipo_procesamiento
     */
    public String getTipo_procesamiento() {
        return tipo_procesamiento;
    }

    /**
     * @param tipo_procesamiento the tipo_procesamiento to set
     */
    public void setTipo_procesamiento(String tipo_procesamiento) {
        this.tipo_procesamiento = tipo_procesamiento;
    }

    /**
     * @return the restriccion_analisis
     */
    public String getRestriccion_analisis() {
        return restriccion_analisis;
    }

    /**
     * @param restriccion_analisis the restriccion_analisis to set
     */
    public void setRestriccion_analisis(String restriccion_analisis) {
        this.restriccion_analisis = restriccion_analisis;
    }

    /**
     * @return the explicacion_met_proce
     */
    public String getExplicacion_met_proce() {
        return explicacion_met_proce;
    }

    /**
     * @param explicacion_met_proce the explicacion_met_proce to set
     */
    public void setExplicacion_met_proce(String explicacion_met_proce) {
        this.explicacion_met_proce = explicacion_met_proce;
    }

    /**
     * @return the estado_detalle
     */
    public String getEstado_detalle() {
        return estado_detalle;
    }

    /**
     * @param estado_detalle the estado_detalle to set
     */
    public void setEstado_detalle(String estado_detalle) {
        this.estado_detalle = estado_detalle;
    }

    /**
     * @return the observacion_ana_exa
     */
    public String getObservacion_ana_exa() {
        return observacion_ana_exa;
    }

    /**
     * @param observacion_ana_exa the observacion_ana_exa to set
     */
    public void setObservacion_ana_exa(String observacion_ana_exa) {
        this.observacion_ana_exa = observacion_ana_exa;
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
