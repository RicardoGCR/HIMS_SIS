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
public class LAB_Toma_Muestra_Detalle {
    private Connection cn;
    private String cod_det_toma_mu_ana;
    private String cod_cab_toma_mu_exa;
    private String id_cod_det;
    private String cod_exa_ana;
    private String cod_per_solicita;
    private String nom_per_solicita;
    private String fecha_probable_entre;
    private String Id_Preventa;
    private String hab_nom;
    private String ca_desc;
    private String hora_probable_entre;
    private String nom_usu;
    
    public LAB_Toma_Muestra_Detalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
     public boolean LAB_Toma_Muestra_Det_guardar()
        {
        boolean resp = false;
        try
        {
            String sql = "exec sp_LAB_TOMA_MUESTRA_DET_insertar ?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_det_toma_mu_ana());
            cmd.setString(2, getCod_cab_toma_mu_exa());
            cmd.setString(3, getId_cod_det());
            cmd.setString(4, getCod_exa_ana());
            cmd.setString(5, getCod_per_solicita());
            cmd.setString(6, getNom_per_solicita());
            cmd.setString(7, getFecha_probable_entre());
            cmd.setString(8, getHora_probable_entre());
            cmd.setString(9, getId_Preventa());
            cmd.setString(10, getHab_nom());
            cmd.setString(11, getCa_desc());
            cmd.setString(12, getNom_usu());
 
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
     
      public String LAB_Toma_Muestra_Det_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_TOMA_MUESTRA_DET_generarid";
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
      
      
    public String LAB_Toma_Muestra_Det_exa(String nomen,String area,String tipo){
       String cod_exa="";
        try{
           String consulta="exec sp_LAB_TOMA_MUESTRA_DET_EXAMEN ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,nomen);
            cmd.setString(2,area);
            cmd.setString(3,tipo);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               cod_exa = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return cod_exa;
    }

    
    
     public int LAB_Toma_Muestra_Hospitalizacion_ver(String idhc){
        int resultado=0;
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
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
    
 
    public String LAB_Toma_Muestra_Hospi_idPreventa(String idhc)
    {
        String cod="";
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
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
    public String LAB_Toma_Muestra_Hospi_habitacion(String idhc)
    {
        String cod="";
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(2);
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
    
    public String LAB_Toma_Muestra_Hospi_cama(String idhc)
    {
        String cod="";
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(3);
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
    public String LAB_Toma_Muestra_Hospi_Servicio(String idhc)
    {
        String cod="";
        try
        {
            String sql = "sp_TOMA_MUESTRA_HOSPITALIZACION ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, idhc);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(4);
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
     * @return the cod_det_toma_mu_ana
     */
    public String getCod_det_toma_mu_ana() {
        return cod_det_toma_mu_ana;
    }

    /**
     * @param cod_det_toma_mu_ana the cod_det_toma_mu_ana to set
     */
    public void setCod_det_toma_mu_ana(String cod_det_toma_mu_ana) {
        this.cod_det_toma_mu_ana = cod_det_toma_mu_ana;
    }

    /**
     * @return the cod_cab_toma_mu_exa
     */
    public String getCod_cab_toma_mu_exa() {
        return cod_cab_toma_mu_exa;
    }

    /**
     * @param cod_cab_toma_mu_exa the cod_cab_toma_mu_exa to set
     */
    public void setCod_cab_toma_mu_exa(String cod_cab_toma_mu_exa) {
        this.cod_cab_toma_mu_exa = cod_cab_toma_mu_exa;
    }

    /**
     * @return the id_cod_det
     */
    public String getId_cod_det() {
        return id_cod_det;
    }

    /**
     * @param id_cod_det the id_cod_det to set
     */
    public void setId_cod_det(String id_cod_det) {
        this.id_cod_det = id_cod_det;
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
     * @return the cod_per_solicita
     */
    public String getCod_per_solicita() {
        return cod_per_solicita;
    }

    /**
     * @param cod_per_solicita the cod_per_solicita to set
     */
    public void setCod_per_solicita(String cod_per_solicita) {
        this.cod_per_solicita = cod_per_solicita;
    }

    /**
     * @return the nom_per_solicita
     */
    public String getNom_per_solicita() {
        return nom_per_solicita;
    }

    /**
     * @param nom_per_solicita the nom_per_solicita to set
     */
    public void setNom_per_solicita(String nom_per_solicita) {
        this.nom_per_solicita = nom_per_solicita;
    }

    /**
     * @return the fecha_probable_entre
     */
    public String getFecha_probable_entre() {
        return fecha_probable_entre;
    }

    /**
     * @param fecha_probable_entre the fecha_probable_entre to set
     */
    public void setFecha_probable_entre(String fecha_probable_entre) {
        this.fecha_probable_entre = fecha_probable_entre;
    }

    /**
     * @return the Id_Preventa
     */
    public String getId_Preventa() {
        return Id_Preventa;
    }

    /**
     * @param Id_Preventa the Id_Preventa to set
     */
    public void setId_Preventa(String Id_Preventa) {
        this.Id_Preventa = Id_Preventa;
    }

    /**
     * @return the hora_probable_entre
     */
    public String getHora_probable_entre() {
        return hora_probable_entre;
    }

    /**
     * @param hora_probable_entre the hora_probable_entre to set
     */
    public void setHora_probable_entre(String hora_probable_entre) {
        this.hora_probable_entre = hora_probable_entre;
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

    /**
     * @return the hab_nom
     */
    public String getHab_nom() {
        return hab_nom;
    }

    /**
     * @param hab_nom the hab_nom to set
     */
    public void setHab_nom(String hab_nom) {
        this.hab_nom = hab_nom;
    }

    /**
     * @return the ca_desc
     */
    public String getCa_desc() {
        return ca_desc;
    }

    /**
     * @param ca_desc the ca_desc to set
     */
    public void setCa_desc(String ca_desc) {
        this.ca_desc = ca_desc;
    }
  
}
