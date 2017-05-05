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
import vista.ConsultorioEx.RegistroEmbarazoPM1;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtCarnetPerinatalReferencias implements Serializable {
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;

    private int idRef;
    private int CP_ID;
    private String cex;
    private String cEXFecha;
    private String cEXEstable;
    private String eme;
    private String eMEFecha;
    private String eMEEstable;
    private String apoyo;
    private String aPOYOFecha;
    private String aPOYOEstable;
    private int ACTOMEDICO;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private String estado;
    private String codUsu;
    
     public void ConsultoriosExtREFListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_REFERENCIAS ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                RegistroEmbarazoPM1.lblIdPM1.setText(r.getString(1)); 
                
                ////////////////////////////////////////////////CONSULTA EXTERNA
                if ((r.getString(3)).equals("S") ){
                RegistroEmbarazoPM1.chkCEsi.setText("X");
                RegistroEmbarazoPM1.chkCEno.setText("");
                RegistroEmbarazoPM1.chkCEna.setText("");

                }
                if ((r.getString(3)).equals("N") ){
                RegistroEmbarazoPM1.chkCEsi.setText("");
                RegistroEmbarazoPM1.chkCEno.setText("X");
                RegistroEmbarazoPM1.chkCEna.setText("");
                }
                if ((r.getString(3)).equals("A") ){
                RegistroEmbarazoPM1.chkCEsi.setText("");
                RegistroEmbarazoPM1.chkCEno.setText("");
                RegistroEmbarazoPM1.chkCEna.setText("X");
                }
                
                try {
                    if(r.getString(4).equals("")){
                        RegistroEmbarazoPM1.fechaCE.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(4));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RegistroEmbarazoPM1.fechaCE.setDate(fechaD2);

                    }
                } catch (Exception e) {
                }
                RegistroEmbarazoPM1.txtEstablecimiento1.setText(r.getString(5)); 
                
                //////////////////////////////////////////////////////EMERGENCIA
                if ((r.getString(6)).equals("S") ){
                RegistroEmbarazoPM1.chkEsi.setText("X");
                RegistroEmbarazoPM1.chkEno.setText("");
                RegistroEmbarazoPM1.chkEna.setText("");

                }
                if ((r.getString(6)).equals("N") ){
                RegistroEmbarazoPM1.chkEsi.setText("");
                RegistroEmbarazoPM1.chkEno.setText("X");
                RegistroEmbarazoPM1.chkEna.setText("");
                }
                if ((r.getString(6)).equals("A") ){
                RegistroEmbarazoPM1.chkEsi.setText("");
                RegistroEmbarazoPM1.chkEno.setText("");
                RegistroEmbarazoPM1.chkEna.setText("X");
                }
                
                try {
                    if(r.getString(7).equals("")){
                        RegistroEmbarazoPM1.fechaE.setDate(null);
                    } else {
                        String fechaSeleccionadaD3 = (String)(r.getString(7));
                        DateFormat dfoD3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD3 = dfoD3.parse(fechaSeleccionadaD3);
                        RegistroEmbarazoPM1.fechaE.setDate(fechaD3);

                    }
                } catch (Exception e) {
                }
                RegistroEmbarazoPM1.txtEstablecimiento2.setText(r.getString(8)); 
                ////////////////////////////////////////////APOYO AL DIAGNOSTICO
                if ((r.getString(9)).equals("S") ){
                RegistroEmbarazoPM1.chkADsi.setText("X");
                RegistroEmbarazoPM1.chkADno.setText("");
                RegistroEmbarazoPM1.chkADna.setText("");

                }
                if ((r.getString(9)).equals("N") ){
                RegistroEmbarazoPM1.chkADsi.setText("");
                RegistroEmbarazoPM1.chkADno.setText("X");
                RegistroEmbarazoPM1.chkADna.setText("");
                }
                if ((r.getString(9)).equals("A") ){
                RegistroEmbarazoPM1.chkADsi.setText("");
                RegistroEmbarazoPM1.chkADno.setText("");
                RegistroEmbarazoPM1.chkADna.setText("X");
                }
                
                try {
                    if(r.getString(10).equals("")){
                        RegistroEmbarazoPM1.fechaAD.setDate(null);
                    } else {
                        String fechaSeleccionadaD4 = (String)(r.getString(10));
                        DateFormat dfoD4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD4 = dfoD4.parse(fechaSeleccionadaD4);
                        RegistroEmbarazoPM1.fechaAD.setDate(fechaD4);

                    }
                } catch (Exception e) {
                }
                RegistroEmbarazoPM1.txtEstablecimiento3.setText(r.getString(11));
                
                
                
 
 
                if (!RegistroEmbarazoPM1.lblIdPM1.getText().equals("") ){
                    RegistroEmbarazoPM1.btnGuardar.setEnabled(false);
                    RegistroEmbarazoPM1.btneditar.setEnabled(true);
                    RegistroEmbarazoPM1.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios REFERENCIAS  " + e.getMessage());
        }
    }  
                
    
    public boolean mantenimientoConsultorioExtCarnetPerinatalREFERENCIAS(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_REFERENCIAS ?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdRef());
            cmd.setInt(2, getCP_ID());
            cmd.setString(3, getCex());
            cmd.setString(4, getCEXFecha());
            cmd.setString(5, getCEXEstable());
            
            cmd.setString(6, getEme());
            cmd.setString(7, getEMEFecha());
            cmd.setString(8, getEMEEstable());
            
            cmd.setString(9, getApoyo());
            cmd.setString(10, getAPOYOFecha());
            cmd.setString(11, getAPOYOEstable());
            cmd.setInt(12, getACTOMEDICO());
            
            cmd.setString(13, getCodUsu());
            cmd.setString(14, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalREFERENCIAS: " + ex.getMessage());
        }
        return resp;
    }
    

    public ConsultorioExtCarnetPerinatalReferencias() {
        Conexion con = new Conexion();
        cn = con.conectar();
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

    public String getcEXFecha() {
        return cEXFecha;
    }

    public void setcEXFecha(String cEXFecha) {
        this.cEXFecha = cEXFecha;
    }

    public String getcEXEstable() {
        return cEXEstable;
    }

    public void setcEXEstable(String cEXEstable) {
        this.cEXEstable = cEXEstable;
    }

    public String geteMEFecha() {
        return eMEFecha;
    }

    public void seteMEFecha(String eMEFecha) {
        this.eMEFecha = eMEFecha;
    }

    public String geteMEEstable() {
        return eMEEstable;
    }

    public void seteMEEstable(String eMEEstable) {
        this.eMEEstable = eMEEstable;
    }

    public String getaPOYOFecha() {
        return aPOYOFecha;
    }

    public void setaPOYOFecha(String aPOYOFecha) {
        this.aPOYOFecha = aPOYOFecha;
    }

    public String getaPOYOEstable() {
        return aPOYOEstable;
    }

    public void setaPOYOEstable(String aPOYOEstable) {
        this.aPOYOEstable = aPOYOEstable;
    }



    public int getIdRef() {
        return idRef;
    }

    public void setIdRef(int idRef) {
        this.idRef = idRef;
    }

    public int getCP_ID() {
        return CP_ID;
    }

    public void setCP_ID(int CP_ID) {
        this.CP_ID = CP_ID;
    }


    
    public String getCex() {
        return cex;
    }

    public void setCex(String cex) {
        this.cex = cex;
    }

    public String getCEXFecha() {
        return cEXFecha;
    }

    public void setCEXFecha(String cEXFecha) {
        this.cEXFecha = cEXFecha;
    }

    public String getCEXEstable() {
        return cEXEstable;
    }

    public void setCEXEstable(String cEXEstable) {
        this.cEXEstable = cEXEstable;
    }

    public String getEme() {
        return eme;
    }

    public void setEme(String eme) {
        this.eme = eme;
    }

    public String getEMEFecha() {
        return eMEFecha;
    }

    public void setEMEFecha(String eMEFecha) {
        this.eMEFecha = eMEFecha;
    }

    public String getEMEEstable() {
        return eMEEstable;
    }

    public void setEMEEstable(String eMEEstable) {
        this.eMEEstable = eMEEstable;
    }

    public String getApoyo() {
        return apoyo;
    }

    public void setApoyo(String apoyo) {
        this.apoyo = apoyo;
    }

    public String getAPOYOFecha() {
        return aPOYOFecha;
    }

    public void setAPOYOFecha(String aPOYOFecha) {
        this.aPOYOFecha = aPOYOFecha;
    }

    public String getAPOYOEstable() {
        return aPOYOEstable;
    }

    public void setAPOYOEstable(String aPOYOEstable) {
        this.aPOYOEstable = aPOYOEstable;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public int getACTOMEDICO() {
        return ACTOMEDICO;
    }

    public void setACTOMEDICO(int ACTOMEDICO) {
        this.ACTOMEDICO = ACTOMEDICO;
    }
    
    

  
    
}
