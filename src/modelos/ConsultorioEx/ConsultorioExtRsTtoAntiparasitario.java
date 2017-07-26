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
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.ConsultorioEx.RSAITTO;
//import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeTTO;

public class ConsultorioExtRsTtoAntiparasitario implements Serializable {
    private static final long serialVersionUID = 1L;

    Conexion con = new Conexion();
    private Connection cn;
    private Long taId;
    private String m11Fecha;
    private String m11Fua;
    private String m12Fecha;
    private String m12Fua;
    private String m21Fecha;
    private String m21Fua;
    private String m22Fecha;
    private String m22Fua;
    private String m31Fecha;
    private String m31Fua;
    private String m32Fecha;
    private String m32Fua;
    private String m41Fecha;
    private String m41Fua;
    private String m42Fecha;
    private String m42Fua;
    private String m51Fecha;
    private String m51Fua;
    private String m52Fecha;
    private String m52Fua;
    private String m61Fecha;
    private String m61Fua;
    private String m62Fecha;
    private String m62Fua;
    private String m71Fecha;
    private String m71Fua;
    private String m81Fecha;
    private String m81Fua;
    private String m91Fecha;
    private String m91Fua;
    private String m101Fecha;
    private String m101Fua;
    private String m111Fecha;
    private String m111Fua;
    private int rsId;
    
    public void ConsultoriosExtTTOListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_TTO_ANTIPARASITARIO_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                //1 AÑO
                try {
                  if(r.getString(3).equals("")){
                  RSAITTO.dtBcg.setDate(null);
                } else {
                
                String fechaSeleccionada11 = (String)(r.getString(3));
                DateFormat dfo11 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha11 = dfo11.parse(fechaSeleccionada11);
                RSAITTO.dtBcg.setDate(fecha11);
                RSAITTO.txtFuaBcg.setText(r.getString(4));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(5).equals("")){
                  RSAITTO.dtHvb.setDate(null);
                } else {
                
                String fechaSeleccionada12 = (String)(r.getString(5));
                DateFormat dfo12 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha12 = dfo12.parse(fechaSeleccionada12);
                RSAITTO.dtHvb.setDate(fecha12);
                RSAITTO.txtFuaHvb.setText(r.getString(6));
                }
                } catch (Exception e) {
                }
                //2 AÑOS
                try {
                  if(r.getString(7).equals("")){
                  RSAITTO.dtInfl1.setDate(null);
                } else {
                
                String fechaSeleccionada21 = (String)(r.getString(7));
                DateFormat dfo21 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha21 = dfo21.parse(fechaSeleccionada21);
                RSAITTO.dtInfl1.setDate(fecha21);
                RSAITTO.txtFuaInfl1.setText(r.getString(8));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(9).equals("")){
                  RSAITTO.dtInfl2.setDate(null);
                } else {
                
                String fechaSeleccionada22 = (String)(r.getString(9));
                DateFormat dfo22 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha22 = dfo22.parse(fechaSeleccionada22);
                RSAITTO.dtInfl2.setDate(fecha22);
                RSAITTO.txtFuaInfl2.setText(r.getString(10));
                }
                } catch (Exception e) {
                }
                //3 AÑOS
                try {
                  if(r.getString(11).equals("")){
                  RSAITTO.dtRot1.setDate(null);
                } else {
                
                String fechaSeleccionada31 = (String)(r.getString(11));
                DateFormat dfo31 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha31 = dfo31.parse(fechaSeleccionada31);
                RSAITTO.dtRot1.setDate(fecha31);
                RSAITTO.txtFuaRot1.setText(r.getString(12));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(13).equals("")){
                  RSAITTO.dtRot2.setDate(null);
                } else {
                
                String fechaSeleccionada32 = (String)(r.getString(13));
                DateFormat dfo32 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha32 = dfo32.parse(fechaSeleccionada32);
                RSAITTO.dtRot2.setDate(fecha32);
                RSAITTO.txtFuaRot2.setText(r.getString(14));
                }
                } catch (Exception e) {
                }
                
                //4 AÑOS
                try {
                  if(r.getString(15).equals("")){
                  RSAITTO.dtSpr1.setDate(null);
                } else {
                
                String fechaSeleccionada41 = (String)(r.getString(15));
                DateFormat dfo41 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha41 = dfo41.parse(fechaSeleccionada41);
                RSAITTO.dtSpr1.setDate(fecha41);
                RSAITTO.txtFuaSpr1.setText(r.getString(16));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(17).equals("")){
                  RSAITTO.dtSpr2.setDate(null);
                } else {
                
                String fechaSeleccionada42 = (String)(r.getString(17));
                DateFormat dfo42 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha42 = dfo42.parse(fechaSeleccionada42);
                RSAITTO.dtSpr2.setDate(fecha42);
                RSAITTO.txtFuaSpr2.setText(r.getString(18));
                }
                } catch (Exception e) {
                }
                //5 AÑOS
                try {
                  if(r.getString(19).equals("")){
                  RSAITTO.dtDpt1.setDate(null);
                } else {
                
                String fechaSeleccionada51 = (String)(r.getString(19));
                DateFormat dfo51 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha51 = dfo51.parse(fechaSeleccionada51);
                RSAITTO.dtDpt1.setDate(fecha51);
                RSAITTO.txtFuaDpt1.setText(r.getString(20));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(21).equals("")){
                  RSAITTO.dtDpt2.setDate(null);
                } else {
                
                String fechaSeleccionada52 = (String)(r.getString(21));
                DateFormat dfo52 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha52 = dfo52.parse(fechaSeleccionada52);
                RSAITTO.dtDpt2.setDate(fecha52);
                RSAITTO.txtFuaDpt2.setText(r.getString(22));
                }
                } catch (Exception e) {
                }
                
                //6 AÑOS
                try {
                  if(r.getString(23).equals("")){
                  RSAITTO.dtInflR1.setDate(null);
                } else {
                
                String fechaSeleccionada61 = (String)(r.getString(23));
                DateFormat dfo61 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha61 = dfo61.parse(fechaSeleccionada61);
                RSAITTO.dtInflR1.setDate(fecha61);
                RSAITTO.txtInflR1.setText(r.getString(24));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(25).equals("")){
                  RSAITTO.dtInflR2.setDate(null);
                } else {
                
                String fechaSeleccionada62 = (String)(r.getString(25));
                DateFormat dfo62 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha62 = dfo62.parse(fechaSeleccionada62);
                RSAITTO.dtInflR2.setDate(fecha62);
                RSAITTO.txtInflR2.setText(r.getString(26));
                }
                } catch (Exception e) {
                }
                
                //7 AÑOS
                try {
                  if(r.getString(27).equals("")){
                  RSAITTO.dtAmadu.setDate(null);
                } else {
                
                String fechaSeleccionada7 = (String)(r.getString(27));
                DateFormat dfo7 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha7 = dfo7.parse(fechaSeleccionada7);
                RSAITTO.dtAmadu.setDate(fecha7);
                RSAITTO.txtFuaAmaDu.setText(r.getString(28));
                }
                } catch (Exception e) {
                }
                
                //8 AÑOS
                try {
                  if(r.getString(29).equals("")){
                  RSAITTO.dtAmadu1.setDate(null);
                } else {
                
                String fechaSeleccionada8 = (String)(r.getString(29));
                DateFormat dfo8 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha8 = dfo8.parse(fechaSeleccionada8);
                RSAITTO.dtAmadu1.setDate(fecha8);
                RSAITTO.txtFuaAmaDu1.setText(r.getString(30));
                }
                } catch (Exception e) {
                }
                
                //9 AÑOS
                try {
                  if(r.getString(31).equals("")){
                  RSAITTO.dtAmadu2.setDate(null);
                } else {
                
                String fechaSeleccionada9 = (String)(r.getString(31));
                DateFormat dfo9 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha9 = dfo9.parse(fechaSeleccionada9);
                RSAITTO.dtAmadu2.setDate(fecha9);
                RSAITTO.txtFuaAmaDu2.setText(r.getString(32));
                }
                } catch (Exception e) {
                }
                
                //10 AÑOS
                try {
                  if(r.getString(33).equals("")){
                  RSAITTO.dtAmadu3.setDate(null);
                } else {
                
                String fechaSeleccionada10 = (String)(r.getString(33));
                DateFormat dfo10 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha10 = dfo10.parse(fechaSeleccionada10);
                RSAITTO.dtAmadu3.setDate(fecha10);
                RSAITTO.txtFuaAmaDu3.setText(r.getString(34));
                }
                } catch (Exception e) {
                }
                
                //11 AÑOS
                try {
                  if(r.getString(35).equals("")){
                  RSAITTO.dtAmadu4.setDate(null);
                } else {
                
                String fechaSeleccionada11 = (String)(r.getString(35));
                DateFormat dfo11 = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha11 = dfo11.parse(fechaSeleccionada11);
                RSAITTO.dtAmadu4.setDate(fecha11);
                RSAITTO.txtFuaAmaDu4.setText(r.getString(36));
                }
                } catch (Exception e) {
                }
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }

    public boolean mantenimientoRSAITTO(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_TTO_ANTIPARASITARIO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            //1 AÑO
            cmd.setString(2, getM11Fecha());
            cmd.setString(3, getM11Fua());
            cmd.setString(4, getM12Fecha());
            cmd.setString(5, getM12Fua());
             //2 AÑOS
            cmd.setString(6, getM21Fecha());
            cmd.setString(7, getM21Fua());
            cmd.setString(8, getM22Fecha());
            cmd.setString(9, getM22Fua());
             //3 AÑOS
            cmd.setString(10, getM31Fecha());
            cmd.setString(11, getM31Fua());
            cmd.setString(12, getM32Fecha());
            cmd.setString(13, getM32Fua());
             //4 AÑOS
            cmd.setString(14, getM41Fecha());
            cmd.setString(15, getM41Fua());
            cmd.setString(16, getM42Fecha());
            cmd.setString(17, getM42Fua());
            //5 AÑOS
            cmd.setString(18, getM51Fecha());
            cmd.setString(19, getM51Fua());
            cmd.setString(20, getM52Fecha());
            cmd.setString(21, getM52Fua());
            //6 AÑOS
            cmd.setString(22, getM61Fecha());
            cmd.setString(23, getM61Fua());
            cmd.setString(24, getM62Fecha());
            cmd.setString(25, getM62Fua());
             //7 AÑOS
            cmd.setString(26, getM71Fecha());
            cmd.setString(27, getM71Fua());
             //8 AÑOS
            cmd.setString(28, getM81Fecha());
            cmd.setString(29, getM81Fua());
             //9 AÑOS
            cmd.setString(30, getM91Fecha());
            cmd.setString(31, getM91Fua());
            //10 AÑOS
            cmd.setString(32, getM101Fecha());
            cmd.setString(33, getM101Fua());
            //11 AÑOS
            cmd.setString(34, getM111Fecha());
            cmd.setString(35, getM111Fua());
            
            cmd.setString(36, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimiento Vacunas " + ex.getMessage());
        }
        return resp;
    }
    public void porcentajeTTO(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_TTO_ANTIPARASITARIO_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
//                lblPorcentajeTTO.setText(r.getString(1) + " %"); 
                RSAITTO.lblPorcentajeTto.setText(r.getString(1) + " % Completado"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeTTO " + e.getMessage());
        }
    }

    public ConsultorioExtRsTtoAntiparasitario() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsTtoAntiparasitario(Long taId) {
        this.taId = taId;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
    }

    public String getM11Fecha() {
        return m11Fecha;
    }

    public void setM11Fecha(String m11Fecha) {
        this.m11Fecha = m11Fecha;
    }

    public String getM11Fua() {
        return m11Fua;
    }

    public void setM11Fua(String m11Fua) {
        this.m11Fua = m11Fua;
    }

    public String getM12Fecha() {
        return m12Fecha;
    }

    public void setM12Fecha(String m12Fecha) {
        this.m12Fecha = m12Fecha;
    }

    public String getM12Fua() {
        return m12Fua;
    }

    public void setM12Fua(String m12Fua) {
        this.m12Fua = m12Fua;
    }

    public String getM21Fecha() {
        return m21Fecha;
    }

    public void setM21Fecha(String m21Fecha) {
        this.m21Fecha = m21Fecha;
    }

    public String getM21Fua() {
        return m21Fua;
    }

    public void setM21Fua(String m21Fua) {
        this.m21Fua = m21Fua;
    }

    public String getM22Fecha() {
        return m22Fecha;
    }

    public void setM22Fecha(String m22Fecha) {
        this.m22Fecha = m22Fecha;
    }

    public String getM22Fua() {
        return m22Fua;
    }

    public void setM22Fua(String m22Fua) {
        this.m22Fua = m22Fua;
    }

    public String getM31Fecha() {
        return m31Fecha;
    }

    public void setM31Fecha(String m31Fecha) {
        this.m31Fecha = m31Fecha;
    }

    public String getM31Fua() {
        return m31Fua;
    }

    public void setM31Fua(String m31Fua) {
        this.m31Fua = m31Fua;
    }

    public String getM32Fecha() {
        return m32Fecha;
    }

    public void setM32Fecha(String m32Fecha) {
        this.m32Fecha = m32Fecha;
    }

    public String getM32Fua() {
        return m32Fua;
    }

    public void setM32Fua(String m32Fua) {
        this.m32Fua = m32Fua;
    }

    public String getM41Fecha() {
        return m41Fecha;
    }

    public void setM41Fecha(String m41Fecha) {
        this.m41Fecha = m41Fecha;
    }

    public String getM41Fua() {
        return m41Fua;
    }

    public void setM41Fua(String m41Fua) {
        this.m41Fua = m41Fua;
    }

    public String getM42Fecha() {
        return m42Fecha;
    }

    public void setM42Fecha(String m42Fecha) {
        this.m42Fecha = m42Fecha;
    }

    public String getM42Fua() {
        return m42Fua;
    }

    public void setM42Fua(String m42Fua) {
        this.m42Fua = m42Fua;
    }

    public String getM51Fecha() {
        return m51Fecha;
    }

    public void setM51Fecha(String m51Fecha) {
        this.m51Fecha = m51Fecha;
    }

    public String getM51Fua() {
        return m51Fua;
    }

    public void setM51Fua(String m51Fua) {
        this.m51Fua = m51Fua;
    }

    public String getM52Fecha() {
        return m52Fecha;
    }

    public void setM52Fecha(String m52Fecha) {
        this.m52Fecha = m52Fecha;
    }

    public String getM52Fua() {
        return m52Fua;
    }

    public void setM52Fua(String m52Fua) {
        this.m52Fua = m52Fua;
    }

    public String getM61Fecha() {
        return m61Fecha;
    }

    public void setM61Fecha(String m61Fecha) {
        this.m61Fecha = m61Fecha;
    }

    public String getM61Fua() {
        return m61Fua;
    }

    public void setM61Fua(String m61Fua) {
        this.m61Fua = m61Fua;
    }

    public String getM62Fecha() {
        return m62Fecha;
    }

    public void setM62Fecha(String m62Fecha) {
        this.m62Fecha = m62Fecha;
    }

    public String getM62Fua() {
        return m62Fua;
    }

    public void setM62Fua(String m62Fua) {
        this.m62Fua = m62Fua;
    }

    public String getM71Fecha() {
        return m71Fecha;
    }

    public void setM71Fecha(String m71Fecha) {
        this.m71Fecha = m71Fecha;
    }

    public String getM71Fua() {
        return m71Fua;
    }

    public void setM71Fua(String m71Fua) {
        this.m71Fua = m71Fua;
    }

    public String getM81Fecha() {
        return m81Fecha;
    }

    public void setM81Fecha(String m81Fecha) {
        this.m81Fecha = m81Fecha;
    }

    public String getM81Fua() {
        return m81Fua;
    }

    public void setM81Fua(String m81Fua) {
        this.m81Fua = m81Fua;
    }

    public String getM91Fecha() {
        return m91Fecha;
    }

    public void setM91Fecha(String m91Fecha) {
        this.m91Fecha = m91Fecha;
    }

    public String getM91Fua() {
        return m91Fua;
    }

    public void setM91Fua(String m91Fua) {
        this.m91Fua = m91Fua;
    }

    public String getM101Fecha() {
        return m101Fecha;
    }

    public void setM101Fecha(String m101Fecha) {
        this.m101Fecha = m101Fecha;
    }

    public String getM101Fua() {
        return m101Fua;
    }

    public void setM101Fua(String m101Fua) {
        this.m101Fua = m101Fua;
    }

    public String getM111Fecha() {
        return m111Fecha;
    }

    public void setM111Fecha(String m111Fecha) {
        this.m111Fecha = m111Fecha;
    }

    public String getM111Fua() {
        return m111Fua;
    }

    public void setM111Fua(String m111Fua) {
        this.m111Fua = m111Fua;
    }

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
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
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taId != null ? taId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsTtoAntiparasitario)) {
            return false;
        }
        ConsultorioExtRsTtoAntiparasitario other = (ConsultorioExtRsTtoAntiparasitario) object;
        if ((this.taId == null && other.taId != null) || (this.taId != null && !this.taId.equals(other.taId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTtoAntiparasitario[ taId=" + taId + " ]";
    }
    
}
