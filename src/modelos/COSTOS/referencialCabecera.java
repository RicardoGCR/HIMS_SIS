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
 * @author silvana
 */
    public class referencialCabecera {
        private Connection cn;
    private String cod_prod_refe;
    private String nombre_producto_ref;
    private String nom_usu;
        
    public boolean guardarReferencialCabecera()
        {
        boolean resp = false;
        try
        {
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_insertar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_prod_refe());
            cmd.setString(2, getNombre_producto_ref());
            cmd.setString(3, getNom_usu());
 
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
         
         
    public boolean modificarReferencialCabecera(){
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_modificar ?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_prod_refe());
            cmd.setString(2, getNombre_producto_ref());
            cmd.setString(3, getNom_usu());
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
    

    public boolean eliminarReferencialCabecera()
    {
        boolean resp = false;
        try{
            String sql = "exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_eliminar ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_prod_refe());
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
    
    
    
    public String codreferencialCabecera()
    {
        Conexion cn=new Conexion();
        String cod="";
        try{
        String consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_generarid";
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
    
    public int ver_referencialCabecera(String nombre)
    {
        int resultado=0;
        try
        {
            String sql = "SELECT * FROM COSTOS_PRODUCTO_REFERENCIAL_CABECERA where nombre_producto_ref=? and estado_prod_refe_cab='A'";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
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
    
    public String codigo(String nombre)
    {
        String cod="";
        try
        {
            String sql = "SELECT cod_prod_refe FROM COSTOS_PRODUCTO_REFERENCIAL_CABECERA where nombre_producto_ref=?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, nombre);
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
    
     public referencialCabecera()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }
    /**
     * @return the cod_prod_refe
     */
    public String getCod_prod_refe() {
        return cod_prod_refe;
    }

    /**
     * @param cod_prod_refe the cod_prod_refe to set
     */
    public void setCod_prod_refe(String cod_prod_refe) {
        this.cod_prod_refe = cod_prod_refe;
    }

    /**
     * @return the nombre_producto_ref
     */
    public String getNombre_producto_ref() {
        return nombre_producto_ref;
    }

    /**
     * @param nombre_producto_ref the nombre_producto_ref to set
     */
    public void setNombre_producto_ref(String nombre_producto_ref) {
        this.nombre_producto_ref = nombre_producto_ref;
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
