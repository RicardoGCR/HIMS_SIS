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
public class LAB_Valores_Referenciales {
    private Connection cn;
    private String cod_valores_refe_resul;
    private String cod_esquema_resul;
    private String estado_todos_fabricantes;
    private String cod_fabricante_producto_mh;
    private String ini_anio;
    private String ini_mes;
    private String ini_dia;
     private String fin_anio;
    private String fin_mes;
    private String fin_dia;
    private String genero;
    private String estado_clinico_ref;
     private double valor_minimo;
    private double valor_maximo;
    private String valor_texto_referencia;
    private String tipo_valor_referencia;
    private String nom_usu;

     public LAB_Valores_Referenciales()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
         public boolean LAB_Valores_Ref_guardar(){
        boolean resp = false;
        try{
            String sql = "exec  sp_LAB_VALORES_REF_insertar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_valores_refe_resul());
            cmd.setString(2, getCod_esquema_resul());
            cmd.setString(3, getEstado_todos_fabricantes());
            cmd.setString(4, getCod_fabricante_producto_mh());
            cmd.setString(5, getIni_anio());
            cmd.setString(6, getIni_mes());
            cmd.setString(7, getIni_dia());
            cmd.setString(8, getFin_anio());
            cmd.setString(9, getFin_mes());
            cmd.setString(10, getFin_dia());
            cmd.setString(11, getGenero());
            cmd.setString(12, getEstado_clinico_ref());
            cmd.setDouble(13, getValor_minimo());
            cmd.setDouble(14, getValor_maximo());
            cmd.setString(15, getValor_texto_referencia());
            cmd.setString(16, getTipo_valor_referencia());
            cmd.setString(17, getNom_usu());
 
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
         
         
    public boolean LAB_Valores_Ref_modificar(){
        boolean resp = false;
        try{
            String sql = "exec  sp_LAB_VALORES_REF_modificar ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_valores_refe_resul());
            cmd.setString(2, getCod_esquema_resul());
            cmd.setString(3, getEstado_todos_fabricantes());
            cmd.setString(4, getCod_fabricante_producto_mh());
            cmd.setString(5, getIni_anio());
            cmd.setString(6, getIni_mes());
            cmd.setString(7, getIni_dia());
            cmd.setString(8, getFin_anio());
            cmd.setString(9, getFin_mes());
            cmd.setString(10, getFin_dia());
            cmd.setString(11, getGenero());
            cmd.setString(12, getEstado_clinico_ref());
            cmd.setDouble(13, getValor_minimo());
            cmd.setDouble(14, getValor_maximo());
            cmd.setString(15, getValor_texto_referencia());
            cmd.setString(16, getTipo_valor_referencia());
            cmd.setString(17, getNom_usu());
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
    

    public boolean LAB_Valores_Ref_eliminar()
    {
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_VALORES_REF_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_valores_refe_resul());
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
    //para todos-fabricante
     public String LAB_Fabricante_buscar_cod(String med)   {
        String unidad="";
        try{
           String consulta="exec sp_LAB_VALORES_Fabricante_buscar ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,med);
            ResultSet r= cmd.executeQuery();
            if(r.next())
            {
               unidad = r.getString(1);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return unidad;
    }
     
    public String LAB_Fabricante_buscar_fab(String med)   {
        String unidad="";
        try{
           String consulta="exec sp_LAB_VALORES_Fabricante_buscar ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1,med);
            ResultSet r= cmd.executeQuery();
            if(r.next())
            {
               unidad = r.getString(2);
            }
            cmd.close();
            getCn().close();
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }
        return unidad;
    }
    public String LAB_Valores_Ref_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_VALORES_REF_generarid";
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
     * @return the estado_todos_fabricantes
     */
    public String getEstado_todos_fabricantes() {
        return estado_todos_fabricantes;
    }

    /**
     * @param estado_todos_fabricantes the estado_todos_fabricantes to set
     */
    public void setEstado_todos_fabricantes(String estado_todos_fabricantes) {
        this.estado_todos_fabricantes = estado_todos_fabricantes;
    }

    /**
     * @return the cod_fabricante_producto_mh
     */
    public String getCod_fabricante_producto_mh() {
        return cod_fabricante_producto_mh;
    }

    /**
     * @param cod_fabricante_producto_mh the cod_fabricante_producto_mh to set
     */
    public void setCod_fabricante_producto_mh(String cod_fabricante_producto_mh) {
        this.cod_fabricante_producto_mh = cod_fabricante_producto_mh;
    }

    /**
     * @return the ini_anio
     */
    public String getIni_anio() {
        return ini_anio;
    }

    /**
     * @param ini_anio the ini_anio to set
     */
    public void setIni_anio(String ini_anio) {
        this.ini_anio = ini_anio;
    }

    /**
     * @return the ini_mes
     */
    public String getIni_mes() {
        return ini_mes;
    }

    /**
     * @param ini_mes the ini_mes to set
     */
    public void setIni_mes(String ini_mes) {
        this.ini_mes = ini_mes;
    }

    /**
     * @return the ini_dia
     */
    public String getIni_dia() {
        return ini_dia;
    }

    /**
     * @param ini_dia the ini_dia to set
     */
    public void setIni_dia(String ini_dia) {
        this.ini_dia = ini_dia;
    }

    /**
     * @return the fin_anio
     */
    public String getFin_anio() {
        return fin_anio;
    }

    /**
     * @param fin_anio the fin_anio to set
     */
    public void setFin_anio(String fin_anio) {
        this.fin_anio = fin_anio;
    }

    /**
     * @return the fin_mes
     */
    public String getFin_mes() {
        return fin_mes;
    }

    /**
     * @param fin_mes the fin_mes to set
     */
    public void setFin_mes(String fin_mes) {
        this.fin_mes = fin_mes;
    }

    /**
     * @return the fin_dia
     */
    public String getFin_dia() {
        return fin_dia;
    }

    /**
     * @param fin_dia the fin_dia to set
     */
    public void setFin_dia(String fin_dia) {
        this.fin_dia = fin_dia;
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
     * @return the estado_clinico_ref
     */
    public String getEstado_clinico_ref() {
        return estado_clinico_ref;
    }

    /**
     * @param estado_clinico_ref the estado_clinico_ref to set
     */
    public void setEstado_clinico_ref(String estado_clinico_ref) {
        this.estado_clinico_ref = estado_clinico_ref;
    }

    /**
     * @return the valor_minimo
     */
    public double getValor_minimo() {
        return valor_minimo;
    }

    /**
     * @param valor_minimo the valor_minimo to set
     */
    public void setValor_minimo(double valor_minimo) {
        this.valor_minimo = valor_minimo;
    }

    /**
     * @return the valor_maximo
     */
    public double getValor_maximo() {
        return valor_maximo;
    }

    /**
     * @param valor_maximo the valor_maximo to set
     */
    public void setValor_maximo(double valor_maximo) {
        this.valor_maximo = valor_maximo;
    }

    /**
     * @return the valor_texto_referencia
     */
    public String getValor_texto_referencia() {
        return valor_texto_referencia;
    }

    /**
     * @param valor_texto_referencia the valor_texto_referencia to set
     */
    public void setValor_texto_referencia(String valor_texto_referencia) {
        this.valor_texto_referencia = valor_texto_referencia;
    }

    /**
     * @return the tipo_valor_referencia
     */
    public String getTipo_valor_referencia() {
        return tipo_valor_referencia;
    }

    /**
     * @param tipo_valor_referencia the tipo_valor_referencia to set
     */
    public void setTipo_valor_referencia(String tipo_valor_referencia) {
        this.tipo_valor_referencia = tipo_valor_referencia;
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
