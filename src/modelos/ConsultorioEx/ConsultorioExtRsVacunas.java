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
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.ConsultorioEx.RSAIVacunas;

public class ConsultorioExtRsVacunas implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int vacId;
    private String elabFecha;
    private String elabFua;
    private String ejecFecha;
    private String ejecFua;
    private String rnbcgFecha;
    private String rnbcgFua;
    private String rnhvbFecha;
    private String rnhvbFua;
    private String ant1Fecha;
    private String ant1Fua;
    private String ant2Fecha;
    private String ant2Fua;
    private String ant3Fecha;
    private String ant3Fua;
    private String pent1Fecha;
    private String pent1Fua;
    private String pent2Fecha;
    private String pent2Fua;
    private String pent3Fecha;
    private String pent3Fua;
    private String neu1Fecha;
    private String neu1Fua;
    private String neu2Fecha;
    private String neu2Fua;
    private String neu3Fecha;
    private String neu3Fua;
    private String infl1Fecha;
    private String infl1Fua;
    private String infl2Fecha;
    private String infl2Fua2;
    private String rot1Fecha;
    private String rot1Fua;
    private String rot2Fecha;
    private String rot2Fua;
    private String spr1Fecha;
    private String spr1Fua;
    private String spr2Fecha;
    private String spr2Fua;
    private String ama1Fecha;
    private String ama1Fua;
    private String ama2Fecha;
    private String ama2Fua;
    private String dpt1Fecha;
    private String dpt1Fua;
    private String dpt2Fecha;
    private String dpt2Fua;
    private String inflr1Fecha;
    private String inflr1Fua;
    private String inflr2Fecha;
    private String inflr2Fua;
    private String apor1Fecha;
    private String apor1Fua;
    private String apor2Fecha;
    private String apor2Fua;

    public void ConsultoriosExtVacunasListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_VACUNAS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                String fechaSeleccionada = (String)(r.getString(3));
                DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = dfo.parse(fechaSeleccionada);
                RSAIVacunas.dtElab.setDate(fecha);
                RSAIVacunas.txtFuaElab.setText(r.getString(4));
//                lblDNI.setText(r.getString(3)); 
//                lblPaciente.setText(r.getString(4)); 
//                lblGenero.setText(r.getString(5)); 
//                lblFecNac.setText(r.getString(6)); 
//                lblEdad.setText(r.getString(7)); 
//                lblDireccion.setText(r.getString(8)); 
//                lblSector.setText(r.getString(9)); 
//                lblServicio.setText(r.getString(10)); 
//                lblConsultorio.setText(r.getString(11)); 
//                lblTurno.setText(r.getString(12)); 
//                lblNAtencion.setText(r.getString(13)); 
//                //lblTipoEdad.setText(r.getString(14)); 
//                lblMedico.setText(r.getString(14)); 
//                lblFechaPen.setText(r.getString(15)); 
//                lblHoraPen.setText(r.getString(16)); 
//                lblUsuarioPen.setText(r.getString(17)); 
//                lblPcPen.setText(r.getString(18)); 
//                lblFechaSal.setText(r.getString(19)); 
//                lblHoraSal.setText(r.getString(20)); 
//                lblUsuarioSal.setText(r.getString(21)); 
//                lblPcSal.setText(r.getString(22)); 
//                lblFechaRet.setText(r.getString(23)); 
//                lblHorraRet.setText(r.getString(24)); 
//                lblUsuarioRet.setText(r.getString(25)); 
//                lblPcRet.setText(r.getString(26)); 
//                lblEstado.setText(r.getString(27));
//                lblFechaSG.setText(r.getString(28)); 
//                lblHoraSG.setText(r.getString(29));
            }
            //
        } catch (Exception e) {
            System.out.println("Error_mostrar_MovHC: " + e.getMessage());
        }
    }
    
    public ConsultorioExtRsVacunas() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsVacunas(int vacId) {
        this.vacId = vacId;
    }

    public int getVacId() {
        return vacId;
    }

    public void setVacId(int vacId) {
        this.vacId = vacId;
    }

    public String getElabFecha() {
        return elabFecha;
    }

    public void setElabFecha(String elabFecha) {
        this.elabFecha = elabFecha;
    }

    public String getElabFua() {
        return elabFua;
    }

    public void setElabFua(String elabFua) {
        this.elabFua = elabFua;
    }

    public String getEjecFecha() {
        return ejecFecha;
    }

    public void setEjecFecha(String ejecFecha) {
        this.ejecFecha = ejecFecha;
    }

    public String getEjecFua() {
        return ejecFua;
    }

    public void setEjecFua(String ejecFua) {
        this.ejecFua = ejecFua;
    }

    public String getRnbcgFecha() {
        return rnbcgFecha;
    }

    public void setRnbcgFecha(String rnbcgFecha) {
        this.rnbcgFecha = rnbcgFecha;
    }

    public String getRnbcgFua() {
        return rnbcgFua;
    }

    public void setRnbcgFua(String rnbcgFua) {
        this.rnbcgFua = rnbcgFua;
    }

    public String getRnhvbFecha() {
        return rnhvbFecha;
    }

    public void setRnhvbFecha(String rnhvbFecha) {
        this.rnhvbFecha = rnhvbFecha;
    }

    public String getRnhvbFua() {
        return rnhvbFua;
    }

    public void setRnhvbFua(String rnhvbFua) {
        this.rnhvbFua = rnhvbFua;
    }

    public String getAnt1Fecha() {
        return ant1Fecha;
    }

    public void setAnt1Fecha(String ant1Fecha) {
        this.ant1Fecha = ant1Fecha;
    }

    public String getAnt1Fua() {
        return ant1Fua;
    }

    public void setAnt1Fua(String ant1Fua) {
        this.ant1Fua = ant1Fua;
    }

    public String getAnt2Fecha() {
        return ant2Fecha;
    }

    public void setAnt2Fecha(String ant2Fecha) {
        this.ant2Fecha = ant2Fecha;
    }

    public String getAnt2Fua() {
        return ant2Fua;
    }

    public void setAnt2Fua(String ant2Fua) {
        this.ant2Fua = ant2Fua;
    }

    public String getAnt3Fecha() {
        return ant3Fecha;
    }

    public void setAnt3Fecha(String ant3Fecha) {
        this.ant3Fecha = ant3Fecha;
    }

    public String getAnt3Fua() {
        return ant3Fua;
    }

    public void setAnt3Fua(String ant3Fua) {
        this.ant3Fua = ant3Fua;
    }

    public String getPent1Fecha() {
        return pent1Fecha;
    }

    public void setPent1Fecha(String pent1Fecha) {
        this.pent1Fecha = pent1Fecha;
    }

    public String getPent1Fua() {
        return pent1Fua;
    }

    public void setPent1Fua(String pent1Fua) {
        this.pent1Fua = pent1Fua;
    }

    public String getPent2Fecha() {
        return pent2Fecha;
    }

    public void setPent2Fecha(String pent2Fecha) {
        this.pent2Fecha = pent2Fecha;
    }

    public String getPent2Fua() {
        return pent2Fua;
    }

    public void setPent2Fua(String pent2Fua) {
        this.pent2Fua = pent2Fua;
    }

    public String getPent3Fecha() {
        return pent3Fecha;
    }

    public void setPent3Fecha(String pent3Fecha) {
        this.pent3Fecha = pent3Fecha;
    }

    public String getPent3Fua() {
        return pent3Fua;
    }

    public void setPent3Fua(String pent3Fua) {
        this.pent3Fua = pent3Fua;
    }

    public String getNeu1Fecha() {
        return neu1Fecha;
    }

    public void setNeu1Fecha(String neu1Fecha) {
        this.neu1Fecha = neu1Fecha;
    }

    public String getNeu1Fua() {
        return neu1Fua;
    }

    public void setNeu1Fua(String neu1Fua) {
        this.neu1Fua = neu1Fua;
    }

    public String getNeu2Fecha() {
        return neu2Fecha;
    }

    public void setNeu2Fecha(String neu2Fecha) {
        this.neu2Fecha = neu2Fecha;
    }

    public String getNeu2Fua() {
        return neu2Fua;
    }

    public void setNeu2Fua(String neu2Fua) {
        this.neu2Fua = neu2Fua;
    }

    public String getNeu3Fecha() {
        return neu3Fecha;
    }

    public void setNeu3Fecha(String neu3Fecha) {
        this.neu3Fecha = neu3Fecha;
    }

    public String getNeu3Fua() {
        return neu3Fua;
    }

    public void setNeu3Fua(String neu3Fua) {
        this.neu3Fua = neu3Fua;
    }

    public String getInfl1Fecha() {
        return infl1Fecha;
    }

    public void setInfl1Fecha(String infl1Fecha) {
        this.infl1Fecha = infl1Fecha;
    }

    public String getInfl1Fua() {
        return infl1Fua;
    }

    public void setInfl1Fua(String infl1Fua) {
        this.infl1Fua = infl1Fua;
    }

    public String getInfl2Fecha() {
        return infl2Fecha;
    }

    public void setInfl2Fecha(String infl2Fecha) {
        this.infl2Fecha = infl2Fecha;
    }

    public String getInfl2Fua2() {
        return infl2Fua2;
    }

    public void setInfl2Fua2(String infl2Fua2) {
        this.infl2Fua2 = infl2Fua2;
    }

    public String getRot1Fecha() {
        return rot1Fecha;
    }

    public void setRot1Fecha(String rot1Fecha) {
        this.rot1Fecha = rot1Fecha;
    }

    public String getRot1Fua() {
        return rot1Fua;
    }

    public void setRot1Fua(String rot1Fua) {
        this.rot1Fua = rot1Fua;
    }

    public String getRot2Fecha() {
        return rot2Fecha;
    }

    public void setRot2Fecha(String rot2Fecha) {
        this.rot2Fecha = rot2Fecha;
    }

    public String getRot2Fua() {
        return rot2Fua;
    }

    public void setRot2Fua(String rot2Fua) {
        this.rot2Fua = rot2Fua;
    }

    public String getSpr1Fecha() {
        return spr1Fecha;
    }

    public void setSpr1Fecha(String spr1Fecha) {
        this.spr1Fecha = spr1Fecha;
    }

    public String getSpr1Fua() {
        return spr1Fua;
    }

    public void setSpr1Fua(String spr1Fua) {
        this.spr1Fua = spr1Fua;
    }

    public String getSpr2Fecha() {
        return spr2Fecha;
    }

    public void setSpr2Fecha(String spr2Fecha) {
        this.spr2Fecha = spr2Fecha;
    }

    public String getSpr2Fua() {
        return spr2Fua;
    }

    public void setSpr2Fua(String spr2Fua) {
        this.spr2Fua = spr2Fua;
    }

    public String getAma1Fecha() {
        return ama1Fecha;
    }

    public void setAma1Fecha(String ama1Fecha) {
        this.ama1Fecha = ama1Fecha;
    }

    public String getAma1Fua() {
        return ama1Fua;
    }

    public void setAma1Fua(String ama1Fua) {
        this.ama1Fua = ama1Fua;
    }

    public String getAma2Fecha() {
        return ama2Fecha;
    }

    public void setAma2Fecha(String ama2Fecha) {
        this.ama2Fecha = ama2Fecha;
    }

    public String getAma2Fua() {
        return ama2Fua;
    }

    public void setAma2Fua(String ama2Fua) {
        this.ama2Fua = ama2Fua;
    }

    public String getDpt1Fecha() {
        return dpt1Fecha;
    }

    public void setDpt1Fecha(String dpt1Fecha) {
        this.dpt1Fecha = dpt1Fecha;
    }

    public String getDpt1Fua() {
        return dpt1Fua;
    }

    public void setDpt1Fua(String dpt1Fua) {
        this.dpt1Fua = dpt1Fua;
    }

    public String getDpt2Fecha() {
        return dpt2Fecha;
    }

    public void setDpt2Fecha(String dpt2Fecha) {
        this.dpt2Fecha = dpt2Fecha;
    }

    public String getDpt2Fua() {
        return dpt2Fua;
    }

    public void setDpt2Fua(String dpt2Fua) {
        this.dpt2Fua = dpt2Fua;
    }

    public String getInflr1Fecha() {
        return inflr1Fecha;
    }

    public void setInflr1Fecha(String inflr1Fecha) {
        this.inflr1Fecha = inflr1Fecha;
    }

    public String getInflr1Fua() {
        return inflr1Fua;
    }

    public void setInflr1Fua(String inflr1Fua) {
        this.inflr1Fua = inflr1Fua;
    }

    public String getInflr2Fecha() {
        return inflr2Fecha;
    }

    public void setInflr2Fecha(String inflr2Fecha) {
        this.inflr2Fecha = inflr2Fecha;
    }

    public String getInflr2Fua() {
        return inflr2Fua;
    }

    public void setInflr2Fua(String inflr2Fua) {
        this.inflr2Fua = inflr2Fua;
    }

    public String getApor1Fecha() {
        return apor1Fecha;
    }

    public void setApor1Fecha(String apor1Fecha) {
        this.apor1Fecha = apor1Fecha;
    }

    public String getApor1Fua() {
        return apor1Fua;
    }

    public void setApor1Fua(String apor1Fua) {
        this.apor1Fua = apor1Fua;
    }

    public String getApor2Fecha() {
        return apor2Fecha;
    }

    public void setApor2Fecha(String apor2Fecha) {
        this.apor2Fecha = apor2Fecha;
    }

    public String getApor2Fua() {
        return apor2Fua;
    }

    public void setApor2Fua(String apor2Fua) {
        this.apor2Fua = apor2Fua;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsVacunas[ vacId=" + vacId + " ]";
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
    
}
