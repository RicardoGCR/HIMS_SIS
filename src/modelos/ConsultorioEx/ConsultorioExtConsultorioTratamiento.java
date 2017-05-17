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
import vista.ConsultorioEx.ConsultorioExtTratamiento;


/**
 *
 * @author MYS1
 */
public class ConsultorioExtConsultorioTratamiento implements Serializable {

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    
    private int idTrat;
    private int idConsultorioEx;
    private String indicaciones;
    private String otras;
    private String fechaActu;
    private String horaActu;
    private String estado;
    private String nomPc;
    private String codUsu;
    
    public boolean mantenimientoConsultorioExtTratamiento(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CONSULTORIO_TRATAMIENTO ?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getIdTrat());
            cmd.setInt(2, getIdConsultorioEx());
            cmd.setString(3, getIndicaciones());
            cmd.setString(4, getOtras());
            cmd.setString(5, getCodUsu());
            cmd.setString(6, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorio TRATAMIENTO: " + ex.getMessage());
        }
        return resp;
    }
    
    public void ConsultoriosExtTratamientoListar(String ap_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CONSULTORIO_TRATAMIENTO ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, ap_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                
                ConsultorioExtTratamiento.txtIndicaciones.setText(r.getString(3)); 
                ConsultorioExtTratamiento.txtOtras.setText(r.getString(4)); 


                ConsultorioExtTratamiento.lblIDT.setText(r.getString(1));

                if (!ConsultorioExtTratamiento.lblIDT.getText().equals("") ){
                    ConsultorioExtTratamiento.btnGuardar.setEnabled(false);
                    ConsultorioExtTratamiento.btneditar.setEnabled(true);
                    
                    ConsultorioExtTratamiento.txtIndicaciones.setEditable(true);
                    ConsultorioExtTratamiento.txtOtras.setEditable(true);
                    
                    ConsultorioExtTratamiento.var.setText("2");
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: LISTAR AP  " + e.getMessage());
        }
    }

    public ConsultorioExtConsultorioTratamiento() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtConsultorioTratamiento(int idTrat) {
        this.idTrat = idTrat;
    }

    public int getIdTrat() {
        return idTrat;
    }

    public void setIdTrat(int idTrat) {
        this.idTrat = idTrat;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getOtras() {
        return otras;
    }

    public void setOtras(String otras) {
        this.otras = otras;
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
