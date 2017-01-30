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
public class LAB_Esquema_Resultado {
    private Connection cn;
    private String cod_esquema_resul;
    private String cod_exa_ana;
    private String cod_uni_med_exa;
    private String nombre_resultado_exa;
    private String tipo_esquema;
    private int cantidad_valores;
    private int cantidad_resultados;
    private String resultado_defecto_esquema;
    private String nom_usu;

     public LAB_Esquema_Resultado()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
     
         public boolean LAB_Esquema_Resultado_guardar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ESQUEMA_RESULTADO_insertar ?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_esquema_resul());
            cmd.setString(2, getCod_exa_ana());
            cmd.setString(3, getCod_uni_med_exa());
            cmd.setString(4, getNombre_resultado_exa());
            cmd.setString(5, getTipo_esquema());
            cmd.setInt(6, getCantidad_valores());
            cmd.setInt(7, getCantidad_resultados());
            cmd.setString(8, getResultado_defecto_esquema());
            cmd.setString(9, getNom_usu());
 
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
         
         
    public boolean LAB_Esquema_Resultado_modificar(){
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ESQUEMA_RESULTADO_modificar ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_esquema_resul());
            cmd.setString(2, getCod_exa_ana());
            cmd.setString(3, getCod_uni_med_exa());
            cmd.setString(4, getNombre_resultado_exa());
            cmd.setString(5, getTipo_esquema());
            cmd.setString(6, getResultado_defecto_esquema());
            cmd.setString(7, getNom_usu());
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
    

    public boolean LAB_Esquema_Resultado_eliminar()
    {
        boolean resp = false;
        try{
            String sql = "exec sp_LAB_ESQUEMA_RESULTADO_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_esquema_resul());
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
    
    //Para el titulo-sin unidad de medida
     public String LAB_Unidad_Medida_buscar_cod(String med)   {
        String unidad="";
        try{
           String consulta="exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_buscar ?";
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
     
    public String LAB_Unidad_Medida_buscar_unidad(String med)   {
        String unidad="";
        try{
           String consulta="exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_buscar ?";
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
    public String LAB_Esquema_Resultado_generarid()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec sp_LAB_ESQUEMA_RESULTADO_generarid";
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
     * @return the tipo_esquema
     */
    public String getTipo_esquema() {
        return tipo_esquema;
    }

    /**
     * @param tipo_esquema the tipo_esquema to set
     */
    public void setTipo_esquema(String tipo_esquema) {
        this.tipo_esquema = tipo_esquema;
    }

    /**
     * @return the cantidad_valores
     */
    public int getCantidad_valores() {
        return cantidad_valores;
    }

    /**
     * @param cantidad_valores the cantidad_valores to set
     */
    public void setCantidad_valores(int cantidad_valores) {
        this.cantidad_valores = cantidad_valores;
    }

    /**
     * @return the cantidad_resultados
     */
    public int getCantidad_resultados() {
        return cantidad_resultados;
    }

    /**
     * @param cantidad_resultados the cantidad_resultados to set
     */
    public void setCantidad_resultados(int cantidad_resultados) {
        this.cantidad_resultados = cantidad_resultados;
    }

    /**
     * @return the resultado_defecto_esquema
     */
    public String getResultado_defecto_esquema() {
        return resultado_defecto_esquema;
    }

    /**
     * @param resultado_defecto_esquema the resultado_defecto_esquema to set
     */
    public void setResultado_defecto_esquema(String resultado_defecto_esquema) {
        this.resultado_defecto_esquema = resultado_defecto_esquema;
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
