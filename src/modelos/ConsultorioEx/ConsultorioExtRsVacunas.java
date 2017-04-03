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
                
                String fechaSeleccionadaejec = (String)(r.getString(5));
                DateFormat dfoejec = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaejec = dfoejec.parse(fechaSeleccionadaejec);
                RSAIVacunas.dtEjec.setDate(fechaejec);
                RSAIVacunas.txtFuaEjec.setText(r.getString(6));
                
                String fechaSeleccionadarnbcg = (String)(r.getString(7));
                DateFormat dfornbcg = new SimpleDateFormat("dd/MM/yyyy");
                Date fecharnbcg = dfornbcg.parse(fechaSeleccionadarnbcg);
                RSAIVacunas.dtBcg.setDate(fecharnbcg);
                RSAIVacunas.txtFuaBcg.setText(r.getString(8));
                
                String fechaSeleccionadarnhvb = (String)(r.getString(9));
                DateFormat dfornhvb = new SimpleDateFormat("dd/MM/yyyy");
                Date fecharnhvb = dfornhvb.parse(fechaSeleccionadarnhvb);
                RSAIVacunas.dtHvb.setDate(fecharnhvb);
                RSAIVacunas.txtFuaHvb.setText(r.getString(10));
                
                String fechaSeleccionadapv1 = (String)(r.getString(11));
                DateFormat dfopv1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechapv1 = dfopv1.parse(fechaSeleccionadapv1);
                RSAIVacunas.dtIpv1.setDate(fechapv1);
                RSAIVacunas.txtFuaIpv1.setText(r.getString(12));
                
                String fechaSeleccionadapv2 = (String)(r.getString(13));
                DateFormat dfopv2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechapv2 = dfopv2.parse(fechaSeleccionadapv2);
                RSAIVacunas.dtIpv2.setDate(fechapv2);
                RSAIVacunas.txtFuaIpv2.setText(r.getString(14));
                
                String fechaSeleccionadapv3 = (String)(r.getString(15));
                DateFormat dfopv3 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechapv3 = dfopv3.parse(fechaSeleccionadapv3);
                RSAIVacunas.dtIpv3.setDate(fechapv3);
                RSAIVacunas.txtFuaIpv3.setText(r.getString(16));
                
                String fechaSeleccionadap1 = (String)(r.getString(17));
                DateFormat dfop1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechap1 = dfop1.parse(fechaSeleccionadap1);
                RSAIVacunas.dtPent1.setDate(fechap1);
                RSAIVacunas.txtFuaPent1.setText(r.getString(18));
                
                String fechaSeleccionadap2 = (String)(r.getString(19));
                DateFormat dfop2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechap2 = dfop2.parse(fechaSeleccionadap2);
                RSAIVacunas.dtPent2.setDate(fechap2);
                RSAIVacunas.txtFuaPent2.setText(r.getString(20));
                
                String fechaSeleccionadap3 = (String)(r.getString(21));
                DateFormat dfop3 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechap3 = dfop3.parse(fechaSeleccionadap3);
                RSAIVacunas.dtPent3.setDate(fechap3);
                RSAIVacunas.txtFuaPent3.setText(r.getString(22));
                
                String fechaSeleccionadan1 = (String)(r.getString(23));
                DateFormat dfon1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechan1 = dfon1.parse(fechaSeleccionadan1);
                RSAIVacunas.dtNeumo1.setDate(fechan1);
                RSAIVacunas.txtFuaNeumo1.setText(r.getString(24));
                
                String fechaSeleccionadan2 = (String)(r.getString(25));
                DateFormat dfon2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechan2 = dfon2.parse(fechaSeleccionadan2);
                RSAIVacunas.dtNeumo2.setDate(fechan2);
                RSAIVacunas.txtFuaNeumo2.setText(r.getString(26));
                
                String fechaSeleccionadan3 = (String)(r.getString(27));
                DateFormat dfon3 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechan3 = dfon3.parse(fechaSeleccionadan3);
                RSAIVacunas.dtNeumo3.setDate(fechan3);
                RSAIVacunas.txtFuaNeumo3.setText(r.getString(28));
                
                String fechaSeleccionadai1 = (String)(r.getString(29));
                DateFormat dfoi1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechai1 = dfoi1.parse(fechaSeleccionadai1);
                RSAIVacunas.dtInfl1.setDate(fechai1);
                RSAIVacunas.txtFuaInfl1.setText(r.getString(30));
                
                String fechaSeleccionadai2 = (String)(r.getString(31));
                DateFormat dfoi2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechai2 = dfoi2.parse(fechaSeleccionadai2);
                RSAIVacunas.dtInfl2.setDate(fechai2);
                RSAIVacunas.txtFuaInfl2.setText(r.getString(32));
                
                String fechaSeleccionadart1 = (String)(r.getString(33));
                DateFormat dfort1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechart1 = dfort1.parse(fechaSeleccionadart1);
                RSAIVacunas.dtRot1.setDate(fechart1);
                RSAIVacunas.txtFuaRot1.setText(r.getString(34));
                
                String fechaSeleccionadart2 = (String)(r.getString(35));
                DateFormat dfort2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechart2 = dfort2.parse(fechaSeleccionadart2);
                RSAIVacunas.dtRot2.setDate(fechart2);
                RSAIVacunas.txtFuaRot2.setText(r.getString(36));
                
                String fechaSeleccionadaspr = (String)(r.getString(37));
                DateFormat dfospr = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaspr = dfospr.parse(fechaSeleccionadaspr);
                RSAIVacunas.dtSpr1.setDate(fechaspr);
                RSAIVacunas.txtFuaSpr1.setText(r.getString(38));
                
                String fechaSeleccionadaspr2 = (String)(r.getString(39));
                DateFormat dfospr2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaspr2 = dfospr2.parse(fechaSeleccionadaspr2);
                RSAIVacunas.dtSpr2.setDate(fechaspr2);
                RSAIVacunas.txtFuaSpr2.setText(r.getString(40));
                
                String fechaSeleccionadaama = (String)(r.getString(41));
                DateFormat dfoama = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaama = dfoama.parse(fechaSeleccionadaama);
                RSAIVacunas.dtAmadu.setDate(fechaama);
                RSAIVacunas.txtFuaAmaDu.setText(r.getString(42));
                
                String fechaSeleccionadadpt1 = (String)(r.getString(43));
                DateFormat dfodpt1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechadpt1 = dfodpt1.parse(fechaSeleccionadadpt1);
                RSAIVacunas.dtDpt1.setDate(fechadpt1);
                RSAIVacunas.txtFuaDpt1.setText(r.getString(44));
                
                String fechaSeleccionadadpt2 = (String)(r.getString(45));
                DateFormat dfodpt2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechadpt2 = dfodpt2.parse(fechaSeleccionadadpt2);
                RSAIVacunas.dtDpt2.setDate(fechadpt2);
                RSAIVacunas.txtFuaDpt2.setText(r.getString(46));
                
                String fechaSeleccionadair1 = (String)(r.getString(47));
                DateFormat dfoir1 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechair1 = dfoir1.parse(fechaSeleccionadair1);
                RSAIVacunas.dtInflR1.setDate(fechair1);
                RSAIVacunas.txtInflR1.setText(r.getString(48));
                
                String fechaSeleccionadair2 = (String)(r.getString(49));
                DateFormat dfoir2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechair2 = dfoir2.parse(fechaSeleccionadair2);
                RSAIVacunas.dtInflR2.setDate(fechair2);
                RSAIVacunas.txtInflR2.setText(r.getString(50));
                
                String fechaSeleccionadaapo = (String)(r.getString(51));
                DateFormat dfoapo = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaapo = dfoapo.parse(fechaSeleccionadaapo);
                RSAIVacunas.dtApoR1.setDate(fechaapo);
                RSAIVacunas.txtFuaApoR1.setText(r.getString(52));
                
                String fechaSeleccionadaapo2 = (String)(r.getString(51));
                DateFormat dfoapo2 = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaapo2 = dfoapo2.parse(fechaSeleccionadaapo2);
                RSAIVacunas.dtApoR2.setDate(fechaapo2);
                RSAIVacunas.txtFuaApoR2.setText(r.getString(52));
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
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
