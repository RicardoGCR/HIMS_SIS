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
import vista.ConsultorioEx.RSAISCVA;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeSVA;
public class ConsultorioExtRsSuplementacionVitaminaA implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long svId;
    private String m61Fecha;
    private String m61Fua;
    private String m62Fecha;
    private String m62Fua;
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
    private int rsId;
    
    
    public void ConsultoriosExtSVAListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_SUPLEMENTACION_VITAMINA_A_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
       
                    
                try {
                    if(r.getString(3).equals("")){
                    RSAISCVA.TNFA2.setDate(null);
                } else {
                
                    String fechaSeleccionada = (String)(r.getString(3));
                    DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = dfo.parse(fechaSeleccionada);
                    RSAISCVA.TNFA2.setDate(fecha);
                    RSAISCVA.TNFUAA2.setText(r.getString(4));
                }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(5).equals("")){
                    RSAISCVA.TNFA3.setDate(null);
                } else {
                
                    String fechaSeleccionada2 = (String)(r.getString(5));
                    DateFormat dfo2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha2 = dfo2.parse(fechaSeleccionada2);
                    RSAISCVA.TNFA3.setDate(fecha2);
                    RSAISCVA.TNFUAA3.setText(r.getString(6));
                }
                } catch (Exception e) {
                }
                //1A
                try {
                    if(r.getString(7).equals("")){
                    RSAISCVA.TNFP1.setDate(null);
                } else {
                
                    String fechaSeleccionada3 = (String)(r.getString(7));
                    DateFormat dfo3 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha3 = dfo3.parse(fechaSeleccionada3);
                    RSAISCVA.TNFP1.setDate(fecha3);
                    RSAISCVA.TNFUAP1.setText(r.getString(8));
                }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                    RSAISCVA.TNFP2.setDate(null);
                } else {
                
                    String fechaSeleccionada4 = (String)(r.getString(9));
                    DateFormat dfo4 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha4 = dfo4.parse(fechaSeleccionada4);
                    RSAISCVA.TNFP2.setDate(fecha4);
                    RSAISCVA.TNFUAP2.setText(r.getString(10));
                }
                } catch (Exception e) {
                }
                
                //2A
                try {
                    if(r.getString(11).equals("")){
                    RSAISCVA.TNFT1.setDate(null);
                } else {
                
                    String fechaSeleccionada5 = (String)(r.getString(11));
                    DateFormat dfo5 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha5 = dfo5.parse(fechaSeleccionada5);
                    RSAISCVA.TNFT1.setDate(fecha5);
                    RSAISCVA.TNFUAT1.setText(r.getString(12));
                }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(13).equals("")){
                    RSAISCVA.TNFT2.setDate(null);
                } else {
                
                    String fechaSeleccionada6 = (String)(r.getString(13));
                    DateFormat dfo6 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha6 = dfo6.parse(fechaSeleccionada6);
                    RSAISCVA.TNFT2.setDate(fecha6);
                    RSAISCVA.TNFUAT2.setText(r.getString(14));
                }
                } catch (Exception e) {
                }
                
                // 3A 
                
                try {
                    if(r.getString(15).equals("")){
                    RSAISCVA.TNFT4.setDate(null);
                } else {
                
                    String fechaSeleccionada7 = (String)(r.getString(15));
                    DateFormat dfo7 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha7 = dfo7.parse(fechaSeleccionada7);
                    RSAISCVA.TNFT4.setDate(fecha7);
                    RSAISCVA.TNFUAT4.setText(r.getString(16));
                }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(17).equals("")){
                    RSAISCVA.TNFT3.setDate(null);
                } else {
                
                    String fechaSeleccionada8 = (String)(r.getString(17));
                    DateFormat dfo8 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha8 = dfo8.parse(fechaSeleccionada8);
                    RSAISCVA.TNFT3.setDate(fecha8);
                    RSAISCVA.TNFUAT3.setText(r.getString(18));
                }
                } catch (Exception e) {
                }
                //4 A
                try {
                    if(r.getString(19).equals("")){
                    RSAISCVA.TNFT5.setDate(null);
                } else {
                
                    String fechaSeleccionada9 = (String)(r.getString(19));
                    DateFormat dfo9 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha9 = dfo9.parse(fechaSeleccionada9);
                    RSAISCVA.TNFT5.setDate(fecha9);
                    RSAISCVA.TNFUAT6.setText(r.getString(20));
                }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(21).equals("")){
                    RSAISCVA.TNFT6.setDate(null);
                } else {
                
                    String fechaSeleccionada10 = (String)(r.getString(21));
                    DateFormat dfo10 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha10 = dfo10.parse(fechaSeleccionada10);
                    RSAISCVA.TNFT6.setDate(fecha10);
                    RSAISCVA.TNFUAT5.setText(r.getString(22));
                }
                } catch (Exception e) {
                }
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
    
    public boolean mantenimientoRSAISVA(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_SUPLEMENTACION_VITAMINA_A ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            cmd.setString(2, getM61Fecha());
            cmd.setString(3, getM61Fua());
            cmd.setString(4, getM62Fecha());
            cmd.setString(5, getM62Fua());
            
            cmd.setString(6, getM11Fecha());
            cmd.setString(7, getM11Fua());
            cmd.setString(8, getM12Fecha());
            cmd.setString(9, getM12Fua());
            
            cmd.setString(10, getM21Fecha());
            cmd.setString(11, getM21Fua());
            cmd.setString(12, getM22Fecha());
            cmd.setString(13, getM22Fua());
            
            cmd.setString(14, getM31Fecha());
            cmd.setString(15, getM31Fua());
            cmd.setString(16, getM32Fecha());
            cmd.setString(17, getM32Fua());
            
            cmd.setString(18, getM41Fecha());
            cmd.setString(19, getM41Fua());
            cmd.setString(20, getM42Fecha());
            cmd.setString(21, getM42Fua());
            
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
    
    public void porcentajeSVA(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_SUPLEMENTACION_VITAMINA_A_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeSVA.setText(r.getString(1) + " %"); 
                RSAISCVA.lblPorcentajeV.setText(r.getString(1) + " % Completado"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeSVA " + e.getMessage());
        }
    }

    public ConsultorioExtRsSuplementacionVitaminaA() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsSuplementacionVitaminaA(Long svId) {
        this.svId = svId;
    }

    public Long getSvId() {
        return svId;
    }

    public void setSvId(Long svId) {
        this.svId = svId;
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

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (svId != null ? svId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsSuplementacionVitaminaA)) {
            return false;
        }
        ConsultorioExtRsSuplementacionVitaminaA other = (ConsultorioExtRsSuplementacionVitaminaA) object;
        if ((this.svId == null && other.svId != null) || (this.svId != null && !this.svId.equals(other.svId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsSuplementacionVitaminaA[ svId=" + svId + " ]";
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
