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
import vista.ConsultorioEx.RegistroEmbarazoAO;
import vista.ConsultorioEx.RegistroEmbarazoGA;

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
    
    public void ConsultoriosExtGAListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_GA ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
               
                if ((r.getString(4)).equals("Parto Vaginal") ){
                RegistroEmbarazoGA.txtT1.setText("X");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                
                }
                if ((r.getString(3)).equals("Cesarea") ){
                RegistroEmbarazoGA.txtT2.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(3)).equals("Aborto") ){
                RegistroEmbarazoGA.txtT3.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                
                if ((r.getString(3)).equals("Ectopico") ){
                RegistroEmbarazoGA.txtT4.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(3)).equals("Aborto Molar") ){
                RegistroEmbarazoGA.txtT5.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT4.setText("");
                RegistroEmbarazoGA.txtT6.setText(""); 
                }
                if ((r.getString(3)).equals("No Aplica") ){
                RegistroEmbarazoGA.txtT6.setText("X");
                RegistroEmbarazoGA.txtT1.setText("");
                RegistroEmbarazoGA.txtT2.setText("");
                RegistroEmbarazoGA.txtT3.setText("");
                RegistroEmbarazoGA.txtT5.setText("");
                RegistroEmbarazoGA.txtT4.setText(""); 
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
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_GA ?,?,?,?,?,?,?,?,?,?,?,?";
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
                    if(!cmd.execute())
                    {
                        resp = true;
                    }
                    cmd.close();
        }
            catch(Exception ex){
                System.out.println("Error: mantenimientoConsultorioExtAG : " + ex.getMessage());
            }
        return resp;
    }

    public ConsultorioExtCarnetPerinatalGa() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalGa(int gaId) {
        this.gaId = gaId;
    }

    public int getGaId() {
        return gaId;
    }

    public void setGaId(int gaId) {
        this.gaId = gaId;
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
    
}
