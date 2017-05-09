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
import vista.ConsultorioEx.RegistroEmbarazoGA;

import javax.swing.table.DefaultTableModel;
import servicios.Conexion;

/**
 *
 * @author PC02
 */
public class ConsultorioExtCarnetPerinatalGa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gaId;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private String gaIntergenesico;
    private String gaTerminacion;
    private String gaTipoAborto;
    private String gaLactanciaMat;
    private String gaLugarParto;
    private String gaFechaGestacion;
    private String GA_CAPTADA;
    private String GA_REFERIDA;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;
    
    public void ConsultoriosExtGAListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_GA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
               
                if ((r.getString(3)).equals("Si") ){
                RegistroEmbarazoGA.chkSi.setText("X");
                RegistroEmbarazoGA.chkNo.setText("");
                
                }
                if ((r.getString(3)).equals("No") ){
                RegistroEmbarazoGA.chkNo.setText("X");
                RegistroEmbarazoGA.chkSi.setText("");

                
                }
                
                if ((r.getString(4)).equals("Parto Vaginal") ){
                RegistroEmbarazoGA.txtT1.setText("X");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                
                }
                if ((r.getString(4)).equals("Cesarea") ){
                RegistroEmbarazoGA.txtT2.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(4)).equals("Aborto") ){
                RegistroEmbarazoGA.txtT3.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                
                if ((r.getString(4)).equals("Ectopico") ){
                RegistroEmbarazoGA.txtT4.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(4)).equals("Aborto Molar") ){
                RegistroEmbarazoGA.txtT5.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(4)).equals("No Aplica") ){
                RegistroEmbarazoGA.txtT6.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT4.setText(""); 
                }
                
                ////////TIPO DE ABORTO
                if ((r.getString(5)).equals("Incompleto") ){
                RegistroEmbarazoGA.txtTa1.setText("X");
                RegistroEmbarazoGA.txtTa2.setText("");
                RegistroEmbarazoGA.txtTa3.setText("");
                RegistroEmbarazoGA.txtTa4.setText("");
                RegistroEmbarazoGA.txtTa5.setText("");
                }
                if ((r.getString(5)).equals("Completo") ){
                RegistroEmbarazoGA.txtTa2.setText("X");
                RegistroEmbarazoGA.txtTa1.setText("");
                RegistroEmbarazoGA.txtTa3.setText("");
                RegistroEmbarazoGA.txtTa4.setText("");
                RegistroEmbarazoGA.txtTa5.setText("");
                }
                if ((r.getString(5)).equals("Frusto/Retenido") ){
                RegistroEmbarazoGA.txtTa3.setText("X");
                RegistroEmbarazoGA.txtTa2.setText("");
                RegistroEmbarazoGA.txtTa1.setText("");
                RegistroEmbarazoGA.txtTa4.setText("");
                RegistroEmbarazoGA.txtTa5.setText("");
                }
                if ((r.getString(5)).equals("Septico") ){
                RegistroEmbarazoGA.txtTa4.setText("X");
                RegistroEmbarazoGA.txtTa2.setText("");
                RegistroEmbarazoGA.txtTa3.setText("");
                RegistroEmbarazoGA.txtTa1.setText("");
                RegistroEmbarazoGA.txtTa5.setText("");
                }
                if ((r.getString(5)).equals("No Aplica") ){
                RegistroEmbarazoGA.txtTa5.setText("X");
                RegistroEmbarazoGA.txtTa2.setText("");
                RegistroEmbarazoGA.txtTa3.setText("");
                RegistroEmbarazoGA.txtTa4.setText("");
                RegistroEmbarazoGA.txtTa1.setText("");
                }
                
                /////////LACTANCIA
                if ((r.getString(6)).equals("No Hubo") ){
                RegistroEmbarazoGA.txtLm1.setText("X");
                RegistroEmbarazoGA.txtLm2.setText("");
                RegistroEmbarazoGA.txtLm3.setText("");
                RegistroEmbarazoGA.txtLm4.setText("");
                }
                if ((r.getString(6)).equals("< 6 meses") ){
                RegistroEmbarazoGA.txtLm2.setText("X");
                RegistroEmbarazoGA.txtLm1.setText("");
                RegistroEmbarazoGA.txtLm3.setText("");
                RegistroEmbarazoGA.txtLm4.setText("");
                }
                if ((r.getString(6)).equals("6 meses a mas") ){
                RegistroEmbarazoGA.txtLm3.setText("X");
                RegistroEmbarazoGA.txtLm2.setText("");
                RegistroEmbarazoGA.txtLm1.setText("");
                RegistroEmbarazoGA.txtLm4.setText("");
                }
                if ((r.getString(6)).equals("No Aplica") ){
                RegistroEmbarazoGA.txtLm4.setText("X");
                RegistroEmbarazoGA.txtLm2.setText("");
                RegistroEmbarazoGA.txtLm3.setText("");
                RegistroEmbarazoGA.txtLm1.setText("");
                }
                /////////lugar de parto
                
                if ((r.getString(7)).equals("EESS") ){
                RegistroEmbarazoGA.txtLp1.setText("X");
                RegistroEmbarazoGA.txtLp2.setText("");

                }
                if ((r.getString(7)).equals("DOMIC") ){
                RegistroEmbarazoGA.txtLp2.setText("X");
                RegistroEmbarazoGA.txtLp1.setText("");
                }
                
                //////FECHA
                
                try {
                    if(r.getString(8).equals("")){
                        RegistroEmbarazoGA.fechaGA.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(8));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RegistroEmbarazoGA.fechaGA.setDate(fechaD2);

                    }
                } catch (Exception e) {
                }
                
                /////////CAPTADA
                
                if ((r.getString(9)).equals("S") ){
                RegistroEmbarazoGA.chkCsi.setText("X");
                RegistroEmbarazoGA.chkCno.setText("");

                }
                if ((r.getString(9)).equals("N") ){
                RegistroEmbarazoGA.chkCno.setText("X");
                RegistroEmbarazoGA.chkCsi.setText("");
                }
                
                /////////Referida
                
                if ((r.getString(10)).equals("S") ){
                RegistroEmbarazoGA.chkRsi.setText("X");
                RegistroEmbarazoGA.chkRno.setText("");

                }
                if ((r.getString(10)).equals("N") ){
                RegistroEmbarazoGA.chkRno.setText("X");
                RegistroEmbarazoGA.chkRsi.setText("");
                }
                RegistroEmbarazoGA.lblIdGA.setText(r.getString(1)); 
                if (!RegistroEmbarazoGA.lblIdGA.getText().equals("") ){
                    RegistroEmbarazoGA.btnGuardar.setEnabled(false);
                    RegistroEmbarazoGA.btneditar.setEnabled(true);
                    RegistroEmbarazoGA.var.setText("2");
                    RegistroEmbarazoGA.fechaGA.setEnabled(false);
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios AO  " + e.getMessage());
        }
    }  
    
    public boolean mantenimientoConsultorioExtGA(String tipo){
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_GA ?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getGaId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getGaIntergenesico());
            cmd.setString(4, getGaTerminacion());
            cmd.setString(5, getGaTipoAborto());
            cmd.setString(6, getGaLactanciaMat());
            cmd.setString(7, getGaLugarParto());
            cmd.setString(8, getGaFechaGestacion());
            cmd.setString(9, getGA_CAPTADA());
            cmd.setString(10, getGA_REFERIDA());
            cmd.setString(11, getCodUsu());
            cmd.setString(12, tipo);
            cmd.setInt(13, getIdActoMedico());
                    if(!cmd.execute())
                    {
                        resp = true;
                    }
                    cmd.close();
        }
            catch(Exception ex){
                System.out.println("Error: mantenimientoConsultorioExtGA : " + ex.getMessage());
            }
        return resp;
    }


    public ConsultorioExtCarnetPerinatalGa() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }


    public String getGaIntergenesico() {
        return gaIntergenesico;
    }

    public void setGaIntergenesico(String gaIntergenesico) {
        this.gaIntergenesico = gaIntergenesico;
    }

    public String getGaTerminacion() {
        return gaTerminacion;
    }

    public void setGaTerminacion(String gaTerminacion) {
        this.gaTerminacion = gaTerminacion;
    }

    public String getGaTipoAborto() {
        return gaTipoAborto;
    }

    public void setGaTipoAborto(String gaTipoAborto) {
        this.gaTipoAborto = gaTipoAborto;
    }

    public String getGaLactanciaMat() {
        return gaLactanciaMat;
    }

    public void setGaLactanciaMat(String gaLactanciaMat) {
        this.gaLactanciaMat = gaLactanciaMat;
    }

    public String getGaLugarParto() {
        return gaLugarParto;
    }

    public void setGaLugarParto(String gaLugarParto) {
        this.gaLugarParto = gaLugarParto;
    }

    public String getGaFechaGestacion() {
        return gaFechaGestacion;
    }

    public void setGaFechaGestacion(String gaFechaGestacion) {
        this.gaFechaGestacion = gaFechaGestacion;
    }

    public String getGA_CAPTADA() {
        return GA_CAPTADA;
    }

    public void setGA_CAPTADA(String GA_CAPTADA) {
        this.GA_CAPTADA = GA_CAPTADA;
    }

    public String getGA_REFERIDA() {
        return GA_REFERIDA;
    }

    public void setGA_REFERIDA(String GA_REFERIDA) {
        this.GA_REFERIDA = GA_REFERIDA;
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
    

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalGa[ gaId=" + getGaId() + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    /**
     * @return the gaId
     */
    public int getGaId() {
        return gaId;
    }

    /**
     * @param gaId the gaId to set
     */
    public void setGaId(int gaId) {
        this.gaId = gaId;
    }

    /**
     * @return the idActoMedico
     */
    public int getIdActoMedico() {
        return idActoMedico;
    }

    /**
     * @param idActoMedico the idActoMedico to set
     */
    public void setIdActoMedico(int idActoMedico) {
        this.idActoMedico = idActoMedico;
    }
    
}
