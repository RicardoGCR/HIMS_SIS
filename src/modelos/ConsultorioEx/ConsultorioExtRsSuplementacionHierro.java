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
import vista.ConsultorioEx.RSAISHM;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeSHM;

/**
 *
 * @author PC02
 */
public class ConsultorioExtRsSuplementacionHierro implements Serializable {
    private static final long serialVersionUID = 1L;
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long shId;
    private String m1Fecha;
    private String m1Fua;
    private String m2Fecha;
    private String m2Fua;
    private String m3Fecha;
    private String m3Fua;
    private String m4Fecha;
    private String m4Fua;
    private String m5Fecha;
    private String m5Fua;
    private String m6Fecha;
    private String m6Fua;
    private String m7Fecha;
    private String m7Fua;
    private String m8Fecha;
    private String m8Fua;
    private String m9Fecha;
    private String m9Fua;
    private String m10Fecha;
    private String m10Fua;
    private String m11Fecha;
    private String m11Fua;
    private String m12Fecha;
    private String m12Fua;
    private int rsId;
    
    public void ConsultoriosExtSHMListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_SUPLEMENTACION_HIERRO_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
    
                try {
                  if(r.getString(3).equals("")){
                  RSAISHM.FDN1.setDate(null);
                } else {
                
                    String fechaSeleccionada1 = (String)(r.getString(3));
                    DateFormat dfo1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha1 = dfo1.parse(fechaSeleccionada1);
                    RSAISHM.FDN1.setDate(fecha1);
                    RSAISHM.FUADN1.setText(r.getString(4));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(5).equals("")){
                  RSAISHM.FDN2.setDate(null);
                } else {
                
                    String fechaSeleccionada2 = (String)(r.getString(5));
                    DateFormat dfo2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha2 = dfo2.parse(fechaSeleccionada2);
                    RSAISHM.FDN2.setDate(fecha2);
                    RSAISHM.FUADN2.setText(r.getString(6));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(7).equals("")){
                  RSAISHM.FDN3.setDate(null);
                } else {
                
                    String fechaSeleccionada3 = (String)(r.getString(7));
                    DateFormat dfo3 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha3 = dfo3.parse(fechaSeleccionada3);
                    RSAISHM.FDN3.setDate(fecha3);
                    RSAISHM.FUADN3.setText(r.getString(8));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(9).equals("")){
                  RSAISHM.FDN4.setDate(null);
                } else {
                
                    String fechaSeleccionada4 = (String)(r.getString(9));
                    DateFormat dfo4 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha4 = dfo4.parse(fechaSeleccionada4);
                    RSAISHM.FDN4.setDate(fecha4);
                    RSAISHM.FUADN4.setText(r.getString(10));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(11).equals("")){
                  RSAISHM.FDN5.setDate(null);
                } else {
                
                    String fechaSeleccionada5 = (String)(r.getString(11));
                    DateFormat dfo5 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha5 = dfo5.parse(fechaSeleccionada5);
                    RSAISHM.FDN5.setDate(fecha5);
                    RSAISHM.FUADN5.setText(r.getString(12));
                }
                } catch (Exception e) {
                }
                try {
                  if(r.getString(13).equals("")){
                  RSAISHM.FDN6.setDate(null);
                } else {
                
                    String fechaSeleccionada6 = (String)(r.getString(13));
                    DateFormat dfo6 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha6 = dfo6.parse(fechaSeleccionada6);
                    RSAISHM.FDN6.setDate(fecha6);
                    RSAISHM.FUADN6.setText(r.getString(14));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(15).equals("")){
                  RSAISHM.FDN7.setDate(null);
                } else {
                
                    String fechaSeleccionada7 = (String)(r.getString(15));
                    DateFormat dfo7 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha7 = dfo7.parse(fechaSeleccionada7);
                    RSAISHM.FDN7.setDate(fecha7);
                    RSAISHM.FUADN7.setText(r.getString(16));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(17).equals("")){
                  RSAISHM.FDN8.setDate(null);
                } else {
                
                    String fechaSeleccionada8 = (String)(r.getString(17));
                    DateFormat dfo8 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha8 = dfo8.parse(fechaSeleccionada8);
                    RSAISHM.FDN8.setDate(fecha8);
                    RSAISHM.FUADN8.setText(r.getString(18));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(19).equals("")){
                  RSAISHM.FDN9.setDate(null);
                } else {
                
                    String fechaSeleccionada9 = (String)(r.getString(19));
                    DateFormat dfo9 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha9 = dfo9.parse(fechaSeleccionada9);
                    RSAISHM.FDN9.setDate(fecha9);
                    RSAISHM.FUADN9.setText(r.getString(20));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(21).equals("")){
                  RSAISHM.FDN10.setDate(null);
                } else {
                
                    String fechaSeleccionada10 = (String)(r.getString(21));
                    DateFormat dfo10 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha10 = dfo10.parse(fechaSeleccionada10);
                    RSAISHM.FDN10.setDate(fecha10);
                    RSAISHM.FUADN10.setText(r.getString(22));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(23).equals("")){
                  RSAISHM.FDN11.setDate(null);
                } else {
                
                    String fechaSeleccionada11 = (String)(r.getString(23));
                    DateFormat dfo11 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha11 = dfo11.parse(fechaSeleccionada11);
                    RSAISHM.FDN11.setDate(fecha11);
                    RSAISHM.FUADN11.setText(r.getString(24));
                }
                } catch (Exception e) {
                }
                try {
                  if(r.getString(25).equals("")){
                  RSAISHM.FDN12.setDate(null);
                } else {
                
                    String fechaSeleccionada12 = (String)(r.getString(25));
                    DateFormat dfo12 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha12 = dfo12.parse(fechaSeleccionada12);
                    RSAISHM.FDN12.setDate(fecha12);
                    RSAISHM.FUADN12.setText(r.getString(26));
                }
                } catch (Exception e) {
                }

            }
 
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
    
    public boolean mantenimientoRSAISHM(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_SUPLEMENTACION_HIERRO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            cmd.setString(2, getM1Fecha());
            cmd.setString(3, getM1Fua());
            
            cmd.setString(4, getM2Fecha());
            cmd.setString(5, getM2Fua());
            
            cmd.setString(6, getM3Fecha());
            cmd.setString(7, getM3Fua());
            
            cmd.setString(8, getM4Fecha());
            cmd.setString(9, getM4Fua());
            
            cmd.setString(10, getM5Fecha());
            cmd.setString(11, getM5Fua());
            
            cmd.setString(12, getM6Fecha());
            cmd.setString(13, getM6Fua());
            
            cmd.setString(14, getM7Fecha());
            cmd.setString(15, getM7Fua());
            
            cmd.setString(16, getM8Fecha());
            cmd.setString(17, getM8Fua());
            
            cmd.setString(18, getM9Fecha());
            cmd.setString(19, getM9Fua());
            
            cmd.setString(20, getM10Fecha());
            cmd.setString(21, getM10Fua());
            
            cmd.setString(22, getM11Fecha());
            cmd.setString(23, getM11Fua());
            
            cmd.setString(24, getM12Fecha());
            cmd.setString(25, getM12Fua());

            cmd.setString(26, tipo);
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
    
    public void porcentajeSHM(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_SUPLEMENTACION_HIERRO_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeSHM.setText(r.getString(1) + " %"); 
                RSAISHM.lblPorcentajeV.setText(r.getString(1) + " % Completado"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeSHM " + e.getMessage());
        }
    }

    public ConsultorioExtRsSuplementacionHierro() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSuplementacionHierro(Long shId) {
        this.shId = shId;
    }

    public Long getShId() {
        return shId;
    }

    public void setShId(Long shId) {
        this.shId = shId;
    }

    public String getM1Fecha() {
        return m1Fecha;
    }

    public void setM1Fecha(String m1Fecha) {
        this.m1Fecha = m1Fecha;
    }

    public String getM1Fua() {
        return m1Fua;
    }

    public void setM1Fua(String m1Fua) {
        this.m1Fua = m1Fua;
    }

    public String getM2Fecha() {
        return m2Fecha;
    }

    public void setM2Fecha(String m2Fecha) {
        this.m2Fecha = m2Fecha;
    }

    public String getM2Fua() {
        return m2Fua;
    }

    public void setM2Fua(String m2Fua) {
        this.m2Fua = m2Fua;
    }

    public String getM3Fecha() {
        return m3Fecha;
    }

    public void setM3Fecha(String m3Fecha) {
        this.m3Fecha = m3Fecha;
    }

    public String getM3Fua() {
        return m3Fua;
    }

    public void setM3Fua(String m3Fua) {
        this.m3Fua = m3Fua;
    }

    public String getM4Fecha() {
        return m4Fecha;
    }

    public void setM4Fecha(String m4Fecha) {
        this.m4Fecha = m4Fecha;
    }

    public String getM4Fua() {
        return m4Fua;
    }

    public void setM4Fua(String m4Fua) {
        this.m4Fua = m4Fua;
    }

    public String getM5Fecha() {
        return m5Fecha;
    }

    public void setM5Fecha(String m5Fecha) {
        this.m5Fecha = m5Fecha;
    }

    public String getM5Fua() {
        return m5Fua;
    }

    public void setM5Fua(String m5Fua) {
        this.m5Fua = m5Fua;
    }

    public String getM6Fecha() {
        return m6Fecha;
    }

    public void setM6Fecha(String m6Fecha) {
        this.m6Fecha = m6Fecha;
    }

    public String getM6Fua() {
        return m6Fua;
    }

    public void setM6Fua(String m6Fua) {
        this.m6Fua = m6Fua;
    }

    public String getM7Fecha() {
        return m7Fecha;
    }

    public void setM7Fecha(String m7Fecha) {
        this.m7Fecha = m7Fecha;
    }

    public String getM7Fua() {
        return m7Fua;
    }

    public void setM7Fua(String m7Fua) {
        this.m7Fua = m7Fua;
    }

    public String getM8Fecha() {
        return m8Fecha;
    }

    public void setM8Fecha(String m8Fecha) {
        this.m8Fecha = m8Fecha;
    }

    public String getM8Fua() {
        return m8Fua;
    }

    public void setM8Fua(String m8Fua) {
        this.m8Fua = m8Fua;
    }

    public String getM9Fecha() {
        return m9Fecha;
    }

    public void setM9Fecha(String m9Fecha) {
        this.m9Fecha = m9Fecha;
    }

    public String getM9Fua() {
        return m9Fua;
    }

    public void setM9Fua(String m9Fua) {
        this.m9Fua = m9Fua;
    }

    public String getM10Fecha() {
        return m10Fecha;
    }

    public void setM10Fecha(String m10Fecha) {
        this.m10Fecha = m10Fecha;
    }

    public String getM10Fua() {
        return m10Fua;
    }

    public void setM10Fua(String m10Fua) {
        this.m10Fua = m10Fua;
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

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shId != null ? shId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSuplementacionHierro)) {
            return false;
        }
        ConsultorioExtRsSuplementacionHierro other = (ConsultorioExtRsSuplementacionHierro) object;
        if ((this.shId == null && other.shId != null) || (this.shId != null && !this.shId.equals(other.shId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSuplementacionHierro[ shId=" + shId + " ]";
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
     * @return the rs_id
     */
    public int getRs_id() {
        return rs_id;
    }

    /**
     * @param rs_id the rs_id to set
     */
    public void setRs_id(int rs_id) {
        this.rs_id = rs_id;
    }
    
}
