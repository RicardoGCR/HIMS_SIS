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
import vista.ConsultorioEx.ConsultorioExtMotivo;


/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioMotivo implements Serializable {
    
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    private int idMc;
    private int idConsultorioEx;
    private String motivo;
    private String resultado;
    private String antecedentes;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    
    public boolean mantenimientoConsultorioExtMotivo(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_MOTIVO ?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdMc());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setString(3, getMotivo());
            cmd.setString(4, getResultado());
            cmd.setString(5, getAntecedentes());
            cmd.setString(6, getCodUsu());
            cmd.setString(7, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio MOTIVO: " + ex.getMessage());
        }
        return resp;
    }
    
    public void ConsultoriosExtMotivoListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_MOTIVO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                ConsultorioExtMotivo.txtMotivo.setText(r.getString(3)); 
                ConsultorioExtMotivo.txtResultado.setText(r.getString(4));
                ConsultorioExtMotivo.txtAntecedentes.setText(r.getString(5));

                
                ConsultorioExtMotivo.lblIDM.setText(r.getString(1));

                if (!ConsultorioExtMotivo.lblIDM.getText().equals("") ){
                    ConsultorioExtMotivo.btnGuardar.setEnabled(false);
                    ConsultorioExtMotivo.btneditar.setEnabled(true);
                    
                    ConsultorioExtMotivo.txtMotivo.setEnabled(true);
                    ConsultorioExtMotivo.txtResultado.setEnabled(true);
                    ConsultorioExtMotivo.txtAntecedentes.setEnabled(true);
                    
                    ConsultorioExtMotivo.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }

    public ConsultorioExtConsultorioMotivo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtConsultorioMotivo(int idMc) {
        this.idMc = idMc;
    }

    public int getIdMc() {
        return idMc;
    }

    public void setIdMc(int idMc) {
        this.idMc = idMc;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
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

    public int getIdConsultorioEx() {
        return idConsultorioEx;
    }

    public void setIdConsultorioEx(int idConsultorioEx) {
        this.idConsultorioEx = idConsultorioEx;
    }
    
    

   
    
}
