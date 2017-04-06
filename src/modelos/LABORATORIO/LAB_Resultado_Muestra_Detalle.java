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
public class LAB_Resultado_Muestra_Detalle {
    private Connection cn;
     private String cod_cab_resultado_mu_ana;
     private String cod_exa_ana;
     private String cod_esquema_resul;
     private String nombre_resultado_exa;
     private String tipo_esquema_sub_ana;
     private String cod_uni_med_exa;
     private String cod_valores_refe_resul;
     private String valor_de_resultado_analisis;
     private String estado_todos_fabricante;
     private String cod_fabricante_producto;
     private String ini_anio_resul;
     private String ini_mes_resul;
     private String ini_dia_resul;
     private String fin_anio_resul;
     private String fin_mes_resul;
     private String fin_dia_resul;
     private String genero;
     private Double valor_minimo_resul;
     private Double valor_maximo_resul;
     private String valor_texto_referencia_resul;
     private String tipo_valor_referencia_resul;
     private String observaciones_resultado_exa;
     private String usa_valores_ref;
    private String nom_usu;
    
    public LAB_Resultado_Muestra_Detalle(){
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    
     public boolean LAB_Resultado_Muestra_Det_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_RESULTADO_MUESTRA_DET_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
//            cmd.setString(1, getCod_cab_resultado_mu_ana());
            cmd.setString(1, getCod_exa_ana());
            cmd.setString(2, getCod_esquema_resul() );
            cmd.setString(3, getNombre_resultado_exa());
            cmd.setString(4, getTipo_esquema_sub_ana());
            cmd.setString(5, getCod_uni_med_exa());
            cmd.setString(6, getCod_valores_refe_resul());
            cmd.setString(7, getValor_de_resultado_analisis());
            cmd.setString(8, getEstado_todos_fabricante());
            cmd.setString(9, getCod_fabricante_producto() );
            cmd.setString(10, getIni_anio_resul());
            cmd.setString(11, getIni_mes_resul());
            cmd.setString(12, getIni_dia_resul());
            cmd.setString(13, getFin_anio_resul());
            cmd.setString(14, getFin_mes_resul());
            cmd.setString(15, getFin_dia_resul());
            cmd.setString(16, getGenero());
            cmd.setDouble(17, getValor_minimo_resul());
            cmd.setDouble(18, getValor_maximo_resul());
            cmd.setString(19, getValor_texto_referencia_resul());
            cmd.setString(20, getTipo_valor_referencia_resul());
            cmd.setString(21, getObservaciones_resultado_exa());
            cmd.setString(22, getUsa_valores_ref());
            cmd.setString(23, getNom_usu());
 
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
     * @return the cod_esquema_resul
     */
    public String getCod_esquema_resul() {
        return cod_esquema_resul;
    }

    /**
     * @param cod_esquema_resul the cod_esquema_resul to set
     */
    public void setCod_esquema_resul(String cod_esquema_resul) {
        this.cod_esquema_resul = cod_esquema_resul;
    }

    /**
     * @return the nombre_resultado_exa
     */
    public String getNombre_resultado_exa() {
        return nombre_resultado_exa;
    }

    /**
     * @param nombre_resultado_exa the nombre_resultado_exa to set
     */
    public void setNombre_resultado_exa(String nombre_resultado_exa) {
        this.nombre_resultado_exa = nombre_resultado_exa;
    }

    /**
     * @return the tipo_esquema_sub_ana
     */
    public String getTipo_esquema_sub_ana() {
        return tipo_esquema_sub_ana;
    }

    /**
     * @param tipo_esquema_sub_ana the tipo_esquema_sub_ana to set
     */
    public void setTipo_esquema_sub_ana(String tipo_esquema_sub_ana) {
        this.tipo_esquema_sub_ana = tipo_esquema_sub_ana;
    }

    /**
     * @return the cod_uni_med_exa
     */
    public String getCod_uni_med_exa() {
        return cod_uni_med_exa;
    }

    /**
     * @param cod_uni_med_exa the cod_uni_med_exa to set
     */
    public void setCod_uni_med_exa(String cod_uni_med_exa) {
        this.cod_uni_med_exa = cod_uni_med_exa;
    }

    /**
     * @return the cod_valores_refe_resul
     */
    public String getCod_valores_refe_resul() {
        return cod_valores_refe_resul;
    }

    /**
     * @param cod_valores_refe_resul the cod_valores_refe_resul to set
     */
    public void setCod_valores_refe_resul(String cod_valores_refe_resul) {
        this.cod_valores_refe_resul = cod_valores_refe_resul;
    }

    /**
     * @return the valor_de_resultado_analisis
     */
    public String getValor_de_resultado_analisis() {
        return valor_de_resultado_analisis;
    }

    /**
     * @param valor_de_resultado_analisis the valor_de_resultado_analisis to set
     */
    public void setValor_de_resultado_analisis(String valor_de_resultado_analisis) {
        this.valor_de_resultado_analisis = valor_de_resultado_analisis;
    }

    /**
     * @return the estado_todos_fabricante
     */
    public String getEstado_todos_fabricante() {
        return estado_todos_fabricante;
    }

    /**
     * @param estado_todos_fabricante the estado_todos_fabricante to set
     */
    public void setEstado_todos_fabricante(String estado_todos_fabricante) {
        this.estado_todos_fabricante = estado_todos_fabricante;
    }

    /**
     * @return the cod_fabricante_producto
     */
    public String getCod_fabricante_producto() {
        return cod_fabricante_producto;
    }

    /**
     * @param cod_fabricante_producto the cod_fabricante_producto to set
     */
    public void setCod_fabricante_producto(String cod_fabricante_producto) {
        this.cod_fabricante_producto = cod_fabricante_producto;
    }

    /**
     * @return the ini_anio_resul
     */
    public String getIni_anio_resul() {
        return ini_anio_resul;
    }

    /**
     * @param ini_anio_resul the ini_anio_resul to set
     */
    public void setIni_anio_resul(String ini_anio_resul) {
        this.ini_anio_resul = ini_anio_resul;
    }

    /**
     * @return the ini_mes_resul
     */
    public String getIni_mes_resul() {
        return ini_mes_resul;
    }

    /**
     * @param ini_mes_resul the ini_mes_resul to set
     */
    public void setIni_mes_resul(String ini_mes_resul) {
        this.ini_mes_resul = ini_mes_resul;
    }

    /**
     * @return the ini_dia_resul
     */
    public String getIni_dia_resul() {
        return ini_dia_resul;
    }

    /**
     * @param ini_dia_resul the ini_dia_resul to set
     */
    public void setIni_dia_resul(String ini_dia_resul) {
        this.ini_dia_resul = ini_dia_resul;
    }

    /**
     * @return the fin_anio_resul
     */
    public String getFin_anio_resul() {
        return fin_anio_resul;
    }

    /**
     * @param fin_anio_resul the fin_anio_resul to set
     */
    public void setFin_anio_resul(String fin_anio_resul) {
        this.fin_anio_resul = fin_anio_resul;
    }

    /**
     * @return the fin_mes_resul
     */
    public String getFin_mes_resul() {
        return fin_mes_resul;
    }

    /**
     * @param fin_mes_resul the fin_mes_resul to set
     */
    public void setFin_mes_resul(String fin_mes_resul) {
        this.fin_mes_resul = fin_mes_resul;
    }

    /**
     * @return the fin_dia_resul
     */
    public String getFin_dia_resul() {
        return fin_dia_resul;
    }

    /**
     * @param fin_dia_resul the fin_dia_resul to set
     */
    public void setFin_dia_resul(String fin_dia_resul) {
        this.fin_dia_resul = fin_dia_resul;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the valor_minimo_resul
     */
    public Double getValor_minimo_resul() {
        return valor_minimo_resul;
    }

    /**
     * @param valor_minimo_resul the valor_minimo_resul to set
     */
    public void setValor_minimo_resul(Double valor_minimo_resul) {
        this.valor_minimo_resul = valor_minimo_resul;
    }

    /**
     * @return the valor_maximo_resul
     */
    public Double getValor_maximo_resul() {
        return valor_maximo_resul;
    }

    /**
     * @param valor_maximo_resul the valor_maximo_resul to set
     */
    public void setValor_maximo_resul(Double valor_maximo_resul) {
        this.valor_maximo_resul = valor_maximo_resul;
    }

    /**
     * @return the valor_texto_referencia_resul
     */
    public String getValor_texto_referencia_resul() {
        return valor_texto_referencia_resul;
    }

    /**
     * @param valor_texto_referencia_resul the valor_texto_referencia_resul to set
     */
    public void setValor_texto_referencia_resul(String valor_texto_referencia_resul) {
        this.valor_texto_referencia_resul = valor_texto_referencia_resul;
    }

    /**
     * @return the tipo_valor_referencia_resul
     */
    public String getTipo_valor_referencia_resul() {
        return tipo_valor_referencia_resul;
    }

    /**
     * @param tipo_valor_referencia_resul the tipo_valor_referencia_resul to set
     */
    public void setTipo_valor_referencia_resul(String tipo_valor_referencia_resul) {
        this.tipo_valor_referencia_resul = tipo_valor_referencia_resul;
    }

    /**
     * @return the observaciones_resultado_exa
     */
    public String getObservaciones_resultado_exa() {
        return observaciones_resultado_exa;
    }

    /**
     * @param observaciones_resultado_exa the observaciones_resultado_exa to set
     */
    public void setObservaciones_resultado_exa(String observaciones_resultado_exa) {
        this.observaciones_resultado_exa = observaciones_resultado_exa;
    }

    /**
     * @return the usa_valores_ref
     */
    public String getUsa_valores_ref() {
        return usa_valores_ref;
    }

    /**
     * @param usa_valores_ref the usa_valores_ref to set
     */
    public void setUsa_valores_ref(String usa_valores_ref) {
        this.usa_valores_ref = usa_valores_ref;
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
