/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import vista.ConsultorioEx.ConsultorioExt;
import vista.ConsultorioEx.ConsultoriosExtAntecedentes;
import vista.ConsultorioEx.HistoriaClinica;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioAntecedentesExf implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    
    private int idAnexf;
    private int idConsultorioEx;
    private String quirurgicas;
    private String patologicas;
    private String obstetricos;
    private String alergicas;
    private String familiares;
    private String otros;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    
    
    public boolean mantenimientoConsultorioExtAntecedentes(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_ANTECEDENTES_EXF ?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdAnexf());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setString(3, getQuirurgicas());
            cmd.setString(4, getPatologicas());
            cmd.setString(5, getObstetricos());
            cmd.setString(6, getAlergicas());
            cmd.setString(7, getFamiliares());
            cmd.setString(8, getOtros());
            cmd.setString(9, getCodUsu());
            cmd.setString(10, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio ANTECEDENTES : " + ex.getMessage());
        }
        return resp;
    }
    
    public void ConsultoriosExtAntecedentesListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_ANTECEDENTES_EXF ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                ConsultoriosExtAntecedentes.txtQuirurgico.setText(r.getString(3)); 
                ConsultoriosExtAntecedentes.txtPatologico.setText(r.getString(4));
                ConsultoriosExtAntecedentes.txtObstetrico.setText(r.getString(5));
                ConsultoriosExtAntecedentes.txtAlergico.setText(r.getString(6));
                ConsultoriosExtAntecedentes.txtFamiliares.setText(r.getString(7));
                ConsultoriosExtAntecedentes.txtOtros.setText(r.getString(8)); 
                
                ConsultoriosExtAntecedentes.lblIDAE.setText(r.getString(1));

                if (!ConsultoriosExtAntecedentes.lblIDAE.getText().equals("") ){
                    ConsultoriosExtAntecedentes.btnGuardar.setEnabled(false);
                    ConsultoriosExtAntecedentes.btneditar.setEnabled(true);
                    
                    ConsultoriosExtAntecedentes.txtQuirurgico.setEnabled(true);
                    ConsultoriosExtAntecedentes.txtPatologico.setEnabled(true);
                    ConsultoriosExtAntecedentes.txtObstetrico.setEnabled(true);
                    ConsultoriosExtAntecedentes.txtAlergico.setEnabled(true);
                    ConsultoriosExtAntecedentes.txtFamiliares.setEnabled(true);
                    ConsultoriosExtAntecedentes.txtOtros.setEnabled(true);
                    
                    ConsultoriosExtAntecedentes.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }

    public void historiaClinicaAntecedentes(String id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_HISTORIAL_CONSULTORIO_ANTECEDENTES_EXF ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                HistoriaClinica.txtAntecedentes.setText(r.getString(1)); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios CABECERA LISTAR  " + e.getMessage());
        }
    } 
    
    public ConsultorioExtConsultorioAntecedentesExf() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtConsultorioAntecedentesExf(int idAnexf) {
        this.idAnexf = idAnexf;
    }

    public int getIdAnexf() {
        return idAnexf;
    }

    public void setIdAnexf(int idAnexf) {
        this.idAnexf = idAnexf;
    }

    public String getQuirurgicas() {
        return quirurgicas;
    }

    public void setQuirurgicas(String quirurgicas) {
        this.quirurgicas = quirurgicas;
    }

    public String getPatologicas() {
        return patologicas;
    }

    public void setPatologicas(String patologicas) {
        this.patologicas = patologicas;
    }

    public String getObstetricos() {
        return obstetricos;
    }

    public void setObstetricos(String obstetricos) {
        this.obstetricos = obstetricos;
    }

    public String getAlergicas() {
        return alergicas;
    }

    public void setAlergicas(String alergicas) {
        this.alergicas = alergicas;
    }

    public String getFamiliares() {
        return familiares;
    }

    public void setFamiliares(String familiares) {
        this.familiares = familiares;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public int getIdConsultorioEx() {
        return idConsultorioEx;
    }

    public void setIdConsultorioEx(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }

    public DefaultTableModel getM() {
        return m;
    }

    public void setM(DefaultTableModel m) {
        this.m = m;
    }

    public Conexion getCon() {
        return con;
    }

    public void setCon(Conexion con) {
        this.con = con;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    

   
    
}
