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
public class CostosSustentacion {
    private String cod_sustento_costo;
    private String cod_precio;
    private int tiempo_hora;
    private int tiempo_min;
    private double saldo_ganancia_perdida_total;
    private String fecha_actu;
    private String hora_actu;
    private String nom_usu;
    private String estado_sustentacion;
    private Connection cn;

    public CostosSustentacion(String cod_sustento_costo, String cod_precio, double saldo_ganancia_perdida_total, String fecha_actu, String hora_actu, String nom_usu, String estado_sustentacion, Connection cn) {
        this.cod_sustento_costo = cod_sustento_costo;
        this.cod_precio = cod_precio;
        this.saldo_ganancia_perdida_total = saldo_ganancia_perdida_total;
        this.fecha_actu = fecha_actu;
        this.hora_actu = hora_actu;
        this.nom_usu = nom_usu;
        this.estado_sustentacion = estado_sustentacion;
        this.cn = cn;
    }

    public CostosSustentacion() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    
    public boolean guardarCostosSustentacion()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_insertar ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_precio());
            cmd.setInt(3, getTiempo_hora());
            cmd.setInt(4, getTiempo_min());
            cmd.setDouble(5, getSaldo_ganancia_perdida_total());
            cmd.setString(6, getNom_usu());
 
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
    
    public boolean modificarCostosSustentacion()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_modificar ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
            cmd.setString(2, getCod_precio());
            cmd.setInt(3, getTiempo_hora());
            cmd.setInt(4, getTiempo_min());
            cmd.setDouble(5, getSaldo_ganancia_perdida_total());
            cmd.setString(6, getNom_usu());
 
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
    
        public boolean eliminarCostosSustentacion(){
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_SUSTENTACION_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_sustento_costo());
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
    
        
        
    public String codCostosSustentacion()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec COSTOS_COSTOS_SUSTENTACION_generarid";
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
    

    public String getCod_sustento_costo() {
        return cod_sustento_costo;
    }

    public void setCod_sustento_costo(String cod_sustento_costo) {
        this.cod_sustento_costo = cod_sustento_costo;
    }

    public String getCod_precio() {
        return cod_precio;
    }

    public void setCod_precio(String cod_precio) {
        this.cod_precio = cod_precio;
    }

    public double getSaldo_ganancia_perdida_total() {
        return saldo_ganancia_perdida_total;
    }

    public void setSaldo_ganancia_perdida_total(double saldo_ganancia_perdida_total) {
        this.saldo_ganancia_perdida_total = saldo_ganancia_perdida_total;
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

    public String getEstado_sustentacion() {
        return estado_sustentacion;
    }

    public void setEstado_sustentacion(String estado_sustentacion) {
        this.estado_sustentacion = estado_sustentacion;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
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
    
    
   
   
    
}
