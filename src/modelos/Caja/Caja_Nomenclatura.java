/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.Caja;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import servicios.Conexion;

/**
 *
 * @author Ricardo
 */
public class Caja_Nomenclatura {
private Connection cn;
private String cod_nomen_caja;  
private String cod_grupo_nomen_aten;
private String cod_uni_organica_jerar;  
private String id_cuenta;  
private String nom_uni_organica_jerar;  
private String nomen_caja;  
private String descripcion_nomen_tipo;  
private String nom_usu;  

private String vis_admi;  
private String cod_farma;  
private String tipo_nomen;  
private String funcion_nomen;  
private String vis_aten;  
Conexion con = new Conexion(); 
public String idNomen(){//muestra el codigo
        String id = "";
        try {
            String consulta = "exec Caja_Nomenclatura_ID";
            ResultSet r;
            r=con.Listar(consulta);
        if(r.next()){
               id = r.getString(1);
        }
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }

public boolean nuevaNomenclatura(){
        boolean resp = false;
        try{
            String sql = "exec Caja_nomenclatura_NUEVO "
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            cmd.setString(2, getCod_grupo_nomen_aten());
            cmd.setString(3, getCod_uni_organica_jerar());
            cmd.setString(4, getId_cuenta());
            cmd.setString(5, getNom_uni_organica_jerar());
            cmd.setString(6, getNomen_caja());
            cmd.setString(7, getDescripcion_nomen_tipo());
            cmd.setString(8, getNom_usu());

            cmd.setString(9, getVis_admi());
            cmd.setString(10, getCod_farma());
            cmd.setString(11, getTipo_nomen());
            cmd.setString(12, getFuncion_nomen());
            cmd.setString(13, getVis_aten());

            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error  " + ex.getMessage());
        }
        return resp;
    }
     
     
    public boolean modificarNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "Caja_nomenclatura_MODIFICAR ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            cmd.setString(2, getCod_grupo_nomen_aten());
            cmd.setString(3, getCod_uni_organica_jerar());
            cmd.setString(4, getId_cuenta());
            cmd.setString(5, getNom_uni_organica_jerar());
            cmd.setString(6, getNomen_caja());
            cmd.setString(7, getDescripcion_nomen_tipo());
            cmd.setString(8, getNom_usu());

            cmd.setString(9, getVis_admi());
            cmd.setString(10, getCod_farma());
            cmd.setString(11, getTipo_nomen());
            cmd.setString(12, getFuncion_nomen());
            cmd.setString(13, getVis_aten());  
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
          System.out.println("Error " + ex.getMessage());
        }
        return resp;
    }
    
    public boolean eliminarCajaNomenclatura(){
        boolean resp = false;
        try
        {
            String sql = "EXEC Caja_nomenclatura_ELIMINAR ?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setString(1, getCod_nomen_caja());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
          
        }
        catch(Exception ex)
        {
            System.out.println("Error_eliminar: " + ex.getMessage());
        }
        return resp;
    }
     
         
      public Caja_Nomenclatura()
    {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
     public String getCod_nomen_caja() {
        return cod_nomen_caja;
    }

    public void setCod_nomen_caja(String cod_nomen_caja) {
        this.cod_nomen_caja = cod_nomen_caja;
    }

    public String getCod_grupo_nomen_aten() {
        return cod_grupo_nomen_aten;
    }

    public void setCod_grupo_nomen_aten(String cod_grupo_nomen_aten) {
        this.cod_grupo_nomen_aten = cod_grupo_nomen_aten;
    }

    public String getCod_uni_organica_jerar() {
        return cod_uni_organica_jerar;
    }

    public void setCod_uni_organica_jerar(String cod_uni_organica_jerar) {
        this.cod_uni_organica_jerar = cod_uni_organica_jerar;
    }

    public String getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(String id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNom_uni_organica_jerar() {
        return nom_uni_organica_jerar;
    }

    public void setNom_uni_organica_jerar(String nom_uni_organica_jerar) {
        this.nom_uni_organica_jerar = nom_uni_organica_jerar;
    }

    public String getNomen_caja() {
        return nomen_caja;
    }

    public void setNomen_caja(String nomen_caja) {
        this.nomen_caja = nomen_caja;
    }

    public String getDescripcion_nomen_tipo() {
        return descripcion_nomen_tipo;
    }

    public void setDescripcion_nomen_tipo(String descripcion_nomen_tipo) {
        this.descripcion_nomen_tipo = descripcion_nomen_tipo;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

 

    public String getVis_admi() {
        return vis_admi;
    }

    public void setVis_admi(String vis_admi) {
        this.vis_admi = vis_admi;
    }

    public String getCod_farma() {
        return cod_farma;
    }

    public void setCod_farma(String cod_farma) {
        this.cod_farma = cod_farma;
    }

    public String getTipo_nomen() {
        return tipo_nomen;
    }

    public void setTipo_nomen(String tipo_nomen) {
        this.tipo_nomen = tipo_nomen;
    }

    public String getFuncion_nomen() {
        return funcion_nomen;
    }

    public void setFuncion_nomen(String funcion_nomen) {
        this.funcion_nomen = funcion_nomen;
    }

    public String getVis_aten() {
        return vis_aten;
    }

    public void setVis_aten(String vis_aten) {
        this.vis_aten = vis_aten;
    }
}
