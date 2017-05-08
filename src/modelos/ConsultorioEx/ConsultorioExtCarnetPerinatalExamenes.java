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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.table.DefaultTableModel;
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoPM2;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtCarnetPerinatalExamenes implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int idEx;
    private int cp_id;
    private String psico;
    private String et;
    private String planp;
    private String alojada;
    private String hcmp;
    private String producto;
    private String orden;
    private String aborto;
    private int ID_ACTOMEDICO;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    
    public void ConsultoriosExtEXAMListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_EXAMENES ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPM2.lblIdPM2.setText(r.getString(1)); 
                RegistroEmbarazoPM2.txtPsico.setText(r.getString(3));
                RegistroEmbarazoPM2.txtEstimulacion.setText(r.getString(4));
                
                ///////////////////////////////////////////////////PLAN DE PARTO
                if ((r.getString(5)).equals("S") ){
                RegistroEmbarazoPM2.chkPPsi.setText("X");
                RegistroEmbarazoPM2.chkPPno.setText("");
                RegistroEmbarazoPM2.chkPPna.setText("");

                }
                if ((r.getString(5)).equals("N") ){
                RegistroEmbarazoPM2.chkPPsi.setText("");
                RegistroEmbarazoPM2.chkPPno.setText("X");
                RegistroEmbarazoPM2.chkPPna.setText("");
                }
                if ((r.getString(5)).equals("A") ){
                RegistroEmbarazoPM2.chkPPsi.setText("");
                RegistroEmbarazoPM2.chkPPno.setText("");
                RegistroEmbarazoPM2.chkPPna.setText("X");
                }
                ///////////////////////////////////////ALOJADA EN CASA DE ESPERA
                if ((r.getString(6)).equals("S") ){
                RegistroEmbarazoPM2.chkACEsi.setText("X");
                RegistroEmbarazoPM2.chkACEno.setText("");
                RegistroEmbarazoPM2.chkACEna.setText("");

                }
                if ((r.getString(6)).equals("N") ){
                RegistroEmbarazoPM2.chkACEsi.setText("");
                RegistroEmbarazoPM2.chkACEno.setText("X");
                RegistroEmbarazoPM2.chkACEna.setText("");
                }
                if ((r.getString(6)).equals("A") ){
                RegistroEmbarazoPM2.chkACEsi.setText("");
                RegistroEmbarazoPM2.chkACEno.setText("");
                RegistroEmbarazoPM2.chkACEna.setText("X");
                }
                
                ////////////////////////////////////////////////////////////HCMP
                if ((r.getString(7)).equals("R") ){
                RegistroEmbarazoPM2.chkHCMPap.setText("X");
                RegistroEmbarazoPM2.chkHCMPa.setText("");
                RegistroEmbarazoPM2.chkHCMPp.setText("");

                }
                if ((r.getString(7)).equals("A") ){
                RegistroEmbarazoPM2.chkHCMPap.setText("");
                RegistroEmbarazoPM2.chkHCMPa.setText("X");
                RegistroEmbarazoPM2.chkHCMPp.setText("");
                }
                if ((r.getString(7)).equals("P") ){
                RegistroEmbarazoPM2.chkHCMPap.setText("");
                RegistroEmbarazoPM2.chkHCMPa.setText("");
                RegistroEmbarazoPM2.chkHCMPp.setText("X");
                }
                
                ///////////////////////////////////////PRODUCTO DE LA CONCEPCION
                if ((r.getString(8)).equals("U") ){
                RegistroEmbarazoPM2.chkHU.setText("X");
                RegistroEmbarazoPM2.chkEM.setText("");

                }
                if ((r.getString(8)).equals("M") ){
                RegistroEmbarazoPM2.chkHU.setText("");
                RegistroEmbarazoPM2.chkEM.setText("X");
                }
                RegistroEmbarazoPM2.txtOrden.setText(r.getString(9));
                
                if ((r.getString(10)).equals("X") ){
                RegistroEmbarazoPM2.chkA.setText("X");
                }
               
                
 
                if (!RegistroEmbarazoPM2.lblIdPM2.getText().equals("") ){
                    RegistroEmbarazoPM2.btnGuardar.setEnabled(false);
                    RegistroEmbarazoPM2.btneditar.setEnabled(true);
                    
                    RegistroEmbarazoPM2.lblIdActoMedico.setText(r.getString(11)); 
                    RegistroEmbarazoPM2.lblActoMedico.setText("Acto Médico de registro " + r.getString(17)); 
                    RegistroEmbarazoPM2.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios EXAMENES  " + e.getMessage());
        }
    }  
    
    public boolean mantenimientoConsultorioExtCarnetPerinatalExamenes(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_EXAMEN ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdEx());
            cmd.setInt(2, getCp_id());
            cmd.setString(3, getPsico());
            cmd.setString(4, getEt());
            cmd.setString(5, getPlanp());
            cmd.setString(6, getAlojada());
            cmd.setString(7, getHcmp());
            cmd.setString(8, getProducto());
            cmd.setString(9, getOrden());
            cmd.setString(10, getAborto());
            cmd.setInt(11, getID_ACTOMEDICO());
            cmd.setString(12, getCodUsu());
            cmd.setString(13, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatal EXAMENES: " + ex.getMessage());
        }
        return resp;
    }

    public ConsultorioExtCarnetPerinatalExamenes() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }


    public int getIdEx() {
        return idEx;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
    }

    public String getPsico() {
        return psico;
    }

    public void setPsico(String psico) {
        this.psico = psico;
    }

    public String getEt() {
        return et;
    }

    public void setEt(String et) {
        this.et = et;
    }

    public String getPlanp() {
        return planp;
    }

    public void setPlanp(String planp) {
        this.planp = planp;
    }

    public String getAlojada() {
        return alojada;
    }

    public void setAlojada(String alojada) {
        this.alojada = alojada;
    }

    public String getHcmp() {
        return hcmp;
    }

    public void setHcmp(String hcmp) {
        this.hcmp = hcmp;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getAborto() {
        return aborto;
    }

    public void setAborto(String aborto) {
        this.aborto = aborto;
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

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
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

    public int getCp_id() {
        return cp_id;
    }

    public void setCp_id(int cp_id) {
        this.cp_id = cp_id;
    }

    public int getID_ACTOMEDICO() {
        return ID_ACTOMEDICO;
    }

    public void setID_ACTOMEDICO(int ID_ACTOMEDICO) {
        this.ID_ACTOMEDICO = ID_ACTOMEDICO;
    }

    
    
}
