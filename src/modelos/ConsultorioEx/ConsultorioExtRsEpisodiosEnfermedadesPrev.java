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
import vista.ConsultorioEx.RSAIEEP;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeEEP;
public class ConsultorioExtRsEpisodiosEnfermedadesPrev implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long eeId;
    private String ee1Fecha;
    private String ee1Fua;
    private String ee2Fecha;
    private String ee2Fua;
    private String ee3Fecha;
    private String ee3Fua;
    private String ee4Fecha;
    private String ee4Fua;
    private String ee5Fecha;
    private String ee5Fua;
    private String ee6Fecha;
    private String ee6Fua;
    private String ee7Fecha;
    private String ee7Fua;
    private String ee8Fecha;
    private String ee8Fua;
    private String ee9Fecha;
    private String ee9Fua;
    private String ee10Fecha;
    private String ee10Fua;
    private int rsId;
    
    public void ConsultoriosExtEEPListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_EPISODIOS_ENFERMEDADES_PREV_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
    
                try {
                  if(r.getString(3).equals("")){
                    RSAIEEP.FDN1.setDate(null);
                } else {
                
                    String fechaSeleccionada1 = (String)(r.getString(3));
                    DateFormat dfo1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha1 = dfo1.parse(fechaSeleccionada1);
                    RSAIEEP.FDN1.setDate(fecha1);
                    RSAIEEP.FUADN1.setText(r.getString(4));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(5).equals("")){
                    RSAIEEP.FDN2.setDate(null);
                } else {
                
                    String fechaSeleccionada2 = (String)(r.getString(5));
                    DateFormat dfo2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha2 = dfo2.parse(fechaSeleccionada2);
                    RSAIEEP.FDN2.setDate(fecha2);
                    RSAIEEP.FUADN2.setText(r.getString(6));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(7).equals("")){
                    RSAIEEP.FDN3.setDate(null);
                } else {
                
                    String fechaSeleccionada3 = (String)(r.getString(7));
                    DateFormat dfo3 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha3 = dfo3.parse(fechaSeleccionada3);
                    RSAIEEP.FDN3.setDate(fecha3);
                    RSAIEEP.FUADN3.setText(r.getString(8));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(9).equals("")){
                    RSAIEEP.FDN4.setDate(null);
                } else {
                
                    String fechaSeleccionada4 = (String)(r.getString(9));
                    DateFormat dfo4 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha4 = dfo4.parse(fechaSeleccionada4);
                    RSAIEEP.FDN4.setDate(fecha4);
                    RSAIEEP.FUADN4.setText(r.getString(10));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(11).equals("")){
                    RSAIEEP.FDN5.setDate(null);
                } else {
                
                    String fechaSeleccionada5 = (String)(r.getString(11));
                    DateFormat dfo5 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha5 = dfo5.parse(fechaSeleccionada5);
                    RSAIEEP.FDN5.setDate(fecha5);
                    RSAIEEP.FUADN5.setText(r.getString(12));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(13).equals("")){
                    RSAIEEP.FDN7.setDate(null);
                } else {
                
                    String fechaSeleccionada6 = (String)(r.getString(13));
                    DateFormat dfo6 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha6 = dfo6.parse(fechaSeleccionada6);
                    RSAIEEP.FDN7.setDate(fecha6);
                    RSAIEEP.FUADN7.setText(r.getString(14));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(15).equals("")){
                    RSAIEEP.FDN8.setDate(null);
                } else {
                
                    String fechaSeleccionada7 = (String)(r.getString(15));
                    DateFormat dfo7 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha7 = dfo7.parse(fechaSeleccionada7);
                    RSAIEEP.FDN8.setDate(fecha7);
                    RSAIEEP.FUADN8.setText(r.getString(16));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(17).equals("")){
                    RSAIEEP.FDN9.setDate(null);
                } else {
                
                    String fechaSeleccionada8 = (String)(r.getString(17));
                    DateFormat dfo8 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha8 = dfo8.parse(fechaSeleccionada8);
                    RSAIEEP.FDN9.setDate(fecha8);
                    RSAIEEP.FUADN9.setText(r.getString(18));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(19).equals("")){
                    RSAIEEP.FDN10.setDate(null);
                } else {
                
                    String fechaSeleccionada9 = (String)(r.getString(19));
                    DateFormat dfo9 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha9 = dfo9.parse(fechaSeleccionada9);
                    RSAIEEP.FDN10.setDate(fecha9);
                    RSAIEEP.FUADN10.setText(r.getString(20));
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(21).equals("")){
                    RSAIEEP.FDN11.setDate(null);
                } else {
                
                    String fechaSeleccionada10 = (String)(r.getString(21));
                    DateFormat dfo10 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha10 = dfo10.parse(fechaSeleccionada10);
                    RSAIEEP.FDN11.setDate(fecha10);
                    RSAIEEP.FUADN11.setText(r.getString(22));
                }
                } catch (Exception e) {
                }
                
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
    
     public boolean mantenimientoRSAIEEP(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_EPISODIOS_ENFERMEDADES_PREV ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            cmd.setString(2, getEe1Fecha());
            cmd.setString(3, getEe1Fua());
            
            cmd.setString(4, getEe2Fecha());
            cmd.setString(5, getEe2Fua());
            
            cmd.setString(6, getEe3Fecha());
            cmd.setString(7, getEe3Fua());
            
            cmd.setString(8, getEe4Fecha());
            cmd.setString(9, getEe4Fua());
            
            cmd.setString(10, getEe5Fecha());
            cmd.setString(11, getEe5Fua());
            
            cmd.setString(12, getEe6Fecha());
            cmd.setString(13, getEe6Fua());
            
            cmd.setString(14, getEe7Fecha());
            cmd.setString(15, getEe7Fua());
            
            cmd.setString(16, getEe8Fecha());
            cmd.setString(17, getEe8Fua());
            
            cmd.setString(18, getEe9Fecha());
            cmd.setString(19, getEe9Fua());
            
            cmd.setString(20, getEe10Fecha());
            cmd.setString(21, getEe10Fua());
            
            cmd.setString(22, tipo);
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

     public void porcentajeEET(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_EPISODIOS_ENFERMEDADES_PREV_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeEEP.setText(r.getString(1) + " %"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeVacunas " + e.getMessage());
        }
    }
    public ConsultorioExtRsEpisodiosEnfermedadesPrev() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsEpisodiosEnfermedadesPrev(Long eeId) {
        this.eeId = eeId;
    }

    public Long getEeId() {
        return eeId;
    }

    public void setEeId(Long eeId) {
        this.eeId = eeId;
    }

    public String getEe1Fecha() {
        return ee1Fecha;
    }

    public void setEe1Fecha(String ee1Fecha) {
        this.ee1Fecha = ee1Fecha;
    }

    public String getEe1Fua() {
        return ee1Fua;
    }

    public void setEe1Fua(String ee1Fua) {
        this.ee1Fua = ee1Fua;
    }

    public String getEe2Fecha() {
        return ee2Fecha;
    }

    public void setEe2Fecha(String ee2Fecha) {
        this.ee2Fecha = ee2Fecha;
    }

    public String getEe2Fua() {
        return ee2Fua;
    }

    public void setEe2Fua(String ee2Fua) {
        this.ee2Fua = ee2Fua;
    }

    public String getEe3Fecha() {
        return ee3Fecha;
    }

    public void setEe3Fecha(String ee3Fecha) {
        this.ee3Fecha = ee3Fecha;
    }

    public String getEe3Fua() {
        return ee3Fua;
    }

    public void setEe3Fua(String ee3Fua) {
        this.ee3Fua = ee3Fua;
    }

    public String getEe4Fecha() {
        return ee4Fecha;
    }

    public void setEe4Fecha(String ee4Fecha) {
        this.ee4Fecha = ee4Fecha;
    }

    public String getEe4Fua() {
        return ee4Fua;
    }

    public void setEe4Fua(String ee4Fua) {
        this.ee4Fua = ee4Fua;
    }

    public String getEe5Fecha() {
        return ee5Fecha;
    }

    public void setEe5Fecha(String ee5Fecha) {
        this.ee5Fecha = ee5Fecha;
    }

    public String getEe5Fua() {
        return ee5Fua;
    }

    public void setEe5Fua(String ee5Fua) {
        this.ee5Fua = ee5Fua;
    }

    public String getEe6Fecha() {
        return ee6Fecha;
    }

    public void setEe6Fecha(String ee6Fecha) {
        this.ee6Fecha = ee6Fecha;
    }

    public String getEe6Fua() {
        return ee6Fua;
    }

    public void setEe6Fua(String ee6Fua) {
        this.ee6Fua = ee6Fua;
    }

    public String getEe7Fecha() {
        return ee7Fecha;
    }

    public void setEe7Fecha(String ee7Fecha) {
        this.ee7Fecha = ee7Fecha;
    }

    public String getEe7Fua() {
        return ee7Fua;
    }

    public void setEe7Fua(String ee7Fua) {
        this.ee7Fua = ee7Fua;
    }

    public String getEe8Fecha() {
        return ee8Fecha;
    }

    public void setEe8Fecha(String ee8Fecha) {
        this.ee8Fecha = ee8Fecha;
    }

    public String getEe8Fua() {
        return ee8Fua;
    }

    public void setEe8Fua(String ee8Fua) {
        this.ee8Fua = ee8Fua;
    }

    public String getEe9Fecha() {
        return ee9Fecha;
    }

    public void setEe9Fecha(String ee9Fecha) {
        this.ee9Fecha = ee9Fecha;
    }

    public String getEe9Fua() {
        return ee9Fua;
    }

    public void setEe9Fua(String ee9Fua) {
        this.ee9Fua = ee9Fua;
    }

    public String getEe10Fecha() {
        return ee10Fecha;
    }

    public void setEe10Fecha(String ee10Fecha) {
        this.ee10Fecha = ee10Fecha;
    }

    public String getEe10Fua() {
        return ee10Fua;
    }

    public void setEe10Fua(String ee10Fua) {
        this.ee10Fua = ee10Fua;
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
        hash += (eeId != null ? eeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsEpisodiosEnfermedadesPrev)) {
            return false;
        }
        ConsultorioExtRsEpisodiosEnfermedadesPrev other = (ConsultorioExtRsEpisodiosEnfermedadesPrev) object;
        if ((this.eeId == null && other.eeId != null) || (this.eeId != null && !this.eeId.equals(other.eeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsEpisodiosEnfermedadesPrev[ eeId=" + eeId + " ]";
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
