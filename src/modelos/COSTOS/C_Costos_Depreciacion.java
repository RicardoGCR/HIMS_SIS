/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelos.COSTOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class C_Costos_Depreciacion {
    private String cod_depreciacion;
    private String cod_entrada_det;
    private String vida_util_años;
    private int vida_util_meses;
    private Double depreciacion_mensual;
    private Double depreciacion_diaria;
    private String fecha_fin_depreciar;
    private int total_mes_depreciar;
    private Double depreciacion_acumulada;
    private Double valor_neto;
    private String estado_depreciacion;
    private String fecha_actu;
    private String hora_actu;
    private String nom_usu;
    private Connection cn;

    public C_Costos_Depreciacion() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
        Conexion conex = new Conexion();
    
    public boolean grabarCostosDepreciacion()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_DEPRECIACION_insertar ?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_depreciacion());
            cmd.setString(2, getCod_entrada_det());
            cmd.setString(3, getVida_util_años());
            cmd.setInt(4, getVida_util_meses());
            cmd.setDouble(5, getDepreciacion_mensual());
            cmd.setDouble(6,getDepreciacion_diaria());
            cmd.setString(7, getFecha_fin_depreciar());
            cmd.setDouble(8,getTotal_mes_depreciar());
            cmd.setDouble(9,getDepreciacion_acumulada());
            cmd.setDouble(10,getValor_neto());
            cmd.setString(11,getNom_usu());
            
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
    
    
    public boolean modificarDepreciacion()
    {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_DEPRECIACION_modificar ?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_depreciacion());
            cmd.setString(2, getCod_entrada_det());
            cmd.setString(3, getVida_util_años());
            cmd.setInt(4, getVida_util_meses());
            cmd.setDouble(5, getDepreciacion_mensual());
            cmd.setDouble(6,getDepreciacion_diaria());
            cmd.setString(7, getFecha_fin_depreciar());
            cmd.setDouble(8,getTotal_mes_depreciar());
            cmd.setDouble(9,getDepreciacion_acumulada());
            cmd.setDouble(10,getValor_neto());
            cmd.setString(11,getNom_usu());
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
    
    
   
    
    public String codDepreciacion(){
    
            Conexion cnn=new Conexion();
        String cod="";
        try{
        String consulta="exec COSTOS_COSTOS_DEPRECIACION_generarid";
        ResultSet r;
        r=cnn.Listar(consulta);
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
    
        public boolean eliminarDepreciacion()
    {
        boolean resp = false;
        try
        {
            String sql = "EXEC COSTOS_COSTOS_DEPRECIACION_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_depreciacion());
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
    
    public int ver_depreciacion(String codigo)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT cod_entrada_det FROM COSTOS_DEPRECIACION WHERE cod_entrada_det =? and estado_depreciacion='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codigo);
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
        
        public int ver_CodDepreciacion(String codD)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT cod_depreciacion FROM COSTOS_DEPRECIACION WHERE cod_depreciacion =? and estado_depreciacion='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, codD);
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

    public String getFecha_actu() {
        return fecha_actu;
    }

    public void setFecha_actu(String fecha_actu) {
        this.fecha_actu = fecha_actu;
    }

    public String getHora_actu() {
        return hora_actu;
    }

    public void setHora_actu(String hora_actu) {
        this.hora_actu = hora_actu;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public Conexion getConex() {
        return conex;
    }

    public void setConex(Conexion conex) {
        this.conex = conex;
    }
    
    public String getCod_depreciacion() {
        return cod_depreciacion;
    }

    public void setCod_depreciacion(String cod_depreciacion) {
        this.cod_depreciacion = cod_depreciacion;
    }

    public String getCod_entrada_det() {
        return cod_entrada_det;
    }

    public void setCod_entrada_det(String cod_entrada_det) {
        this.cod_entrada_det = cod_entrada_det;
    }


    public String getVida_util_años() {
        return vida_util_años;
    }

    public void setVida_util_años(String vida_util_años) {
        this.vida_util_años = vida_util_años;
    }

    public Integer getVida_util_meses() {
        return vida_util_meses;
    }

    public void setVida_util_meses(Integer vida_util_meses) {
        this.vida_util_meses = vida_util_meses;
    }

    public Double getDepreciacion_mensual() {
        return depreciacion_mensual;
    }

    public void setDepreciacion_mensual(Double depreciacion_mensual) {
        this.depreciacion_mensual = depreciacion_mensual;
    }

    public Double getDepreciacion_diaria() {
        return depreciacion_diaria;
    }

    public void setDepreciacion_diaria(Double depreciacion_diaria) {
        this.depreciacion_diaria = depreciacion_diaria;
    }

    public String getFecha_fin_depreciar() {
        return fecha_fin_depreciar;
    }

    public void setFecha_fin_depreciar(String fecha_fin_depreciar) {
        this.fecha_fin_depreciar = fecha_fin_depreciar;
    }

    public int getTotal_mes_depreciar() {
        return total_mes_depreciar;
    }

    public void setTotal_mes_depreciar(int total_mes_depreciar) {
        this.total_mes_depreciar = total_mes_depreciar;
    }

    public Double getDepreciacion_acumulada() {
        return depreciacion_acumulada;
    }

    public void setDepreciacion_acumulada(Double depreciacion_acumulada) {
        this.depreciacion_acumulada = depreciacion_acumulada;
    }

    public Double getValor_neto() {
        return valor_neto;
    }

    public void setValor_neto(Double valor_neto) {
        this.valor_neto = valor_neto;
    }

    public String getEstado_depreciacion() {
        return estado_depreciacion;
    }

    public void setEstado_depreciacion(String estado_depreciacion) {
        this.estado_depreciacion = estado_depreciacion;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }          
    
}
