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
public class LAB_Resultado_Insumos {
    private Connection cn;
    
     private String cod_cab_resultado_mu_ana;
            private String  cod_per_verif;
            private String nombre_per_verif;
            private String cod_per_confirma ;
            private String nombre_per_confirma;
            
            private int cod_KardexLAB ;
        private String situacion_consumo ;
        private String motivo_perdida;
        private int cantidad_perdida ;
        private int cod_KardexLAB_Reasig ;
        private int cantidad_reasig;
        private String cod_produc;
        private int id_cod_det;
        private String nom_usu;
    
    
    public LAB_Resultado_Insumos()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean LAB_Sustentacion_Insumos_Cab_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_SUSTENTACION_INSUMOS_CAB ?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCod_cab_resultado_mu_ana());
            cmd.setString(1, getCod_per_verif());
            cmd.setString(2, getNombre_per_verif() );
            cmd.setString(3, getCod_per_confirma());
            cmd.setString(4, getNombre_per_confirma());
            cmd.setString(5, getNom_usu());
 
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error CAB: " + ex.getMessage());
        }
        return resp;
    }
    
     public boolean LAB_Sustentacion_Insumos_Det_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_SUSTENTACION_INSUMOS_DET ?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCod_cab_resultado_mu_ana());
            cmd.setInt(1, getCod_KardexLAB());
            cmd.setString(2, getSituacion_consumo() );
            cmd.setString(3, getMotivo_perdida());
            cmd.setInt(4, getCantidad_perdida());
            cmd.setInt(5, getCantidad_reasig());
            cmd.setString(6, getCod_produc());
            cmd.setInt(7, getId_cod_det());
            cmd.setString(8, getNom_usu());
 
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error DET: " + ex.getMessage());
        }
        return resp;
    }
    
    public String LAB_Resultado_Insumo_Saldo(String cod_produc){
       String saldo="";
        try{
           String consulta="exec sp_LAB_INSUMO_SALDO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,cod_produc);
            ResultSet r= cmd.executeQuery();
            if(r.next()){
               saldo = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return saldo;
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
     * @return the cod_per_verif
     */
    public String getCod_per_verif() {
        return cod_per_verif;
    }

    /**
     * @param cod_per_verif the cod_per_verif to set
     */
    public void setCod_per_verif(String cod_per_verif) {
        this.cod_per_verif = cod_per_verif;
    }

    /**
     * @return the nombre_per_verif
     */
    public String getNombre_per_verif() {
        return nombre_per_verif;
    }

    /**
     * @param nombre_per_verif the nombre_per_verif to set
     */
    public void setNombre_per_verif(String nombre_per_verif) {
        this.nombre_per_verif = nombre_per_verif;
    }

    /**
     * @return the cod_per_confirma
     */
    public String getCod_per_confirma() {
        return cod_per_confirma;
    }

    /**
     * @param cod_per_confirma the cod_per_confirma to set
     */
    public void setCod_per_confirma(String cod_per_confirma) {
        this.cod_per_confirma = cod_per_confirma;
    }

    /**
     * @return the nombre_per_confirma
     */
    public String getNombre_per_confirma() {
        return nombre_per_confirma;
    }

    /**
     * @param nombre_per_confirma the nombre_per_confirma to set
     */
    public void setNombre_per_confirma(String nombre_per_confirma) {
        this.nombre_per_confirma = nombre_per_confirma;
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
     * @return the cod_KardexLAB
     */
    public int getCod_KardexLAB() {
        return cod_KardexLAB;
    }

    /**
     * @param cod_KardexLAB the cod_KardexLAB to set
     */
    public void setCod_KardexLAB(int cod_KardexLAB) {
        this.cod_KardexLAB = cod_KardexLAB;
    }

    /**
     * @return the situacion_consumo
     */
    public String getSituacion_consumo() {
        return situacion_consumo;
    }

    /**
     * @param situacion_consumo the situacion_consumo to set
     */
    public void setSituacion_consumo(String situacion_consumo) {
        this.situacion_consumo = situacion_consumo;
    }

    /**
     * @return the motivo_perdida
     */
    public String getMotivo_perdida() {
        return motivo_perdida;
    }

    /**
     * @param motivo_perdida the motivo_perdida to set
     */
    public void setMotivo_perdida(String motivo_perdida) {
        this.motivo_perdida = motivo_perdida;
    }

    /**
     * @return the cantidad_perdida
     */
    public int getCantidad_perdida() {
        return cantidad_perdida;
    }

    /**
     * @param cantidad_perdida the cantidad_perdida to set
     */
    public void setCantidad_perdida(int cantidad_perdida) {
        this.cantidad_perdida = cantidad_perdida;
    }

    /**
     * @return the cod_KardexLAB_Reasig
     */
    public int getCod_KardexLAB_Reasig() {
        return cod_KardexLAB_Reasig;
    }

    /**
     * @param cod_KardexLAB_Reasig the cod_KardexLAB_Reasig to set
     */
    public void setCod_KardexLAB_Reasig(int cod_KardexLAB_Reasig) {
        this.cod_KardexLAB_Reasig = cod_KardexLAB_Reasig;
    }

    /**
     * @return the cantidad_reasig
     */
    public int getCantidad_reasig() {
        return cantidad_reasig;
    }

    /**
     * @param cantidad_reasig the cantidad_reasig to set
     */
    public void setCantidad_reasig(int cantidad_reasig) {
        this.cantidad_reasig = cantidad_reasig;
    }

    /**
     * @return the cod_produc
     */
    public String getCod_produc() {
        return cod_produc;
    }

    /**
     * @param cod_produc the cod_produc to set
     */
    public void setCod_produc(String cod_produc) {
        this.cod_produc = cod_produc;
    }

    /**
     * @return the id_cod_det
     */
    public int getId_cod_det() {
        return id_cod_det;
    }

    /**
     * @param id_cod_det the id_cod_det to set
     */
    public void setId_cod_det(int id_cod_det) {
        this.id_cod_det = id_cod_det;
    }
}
