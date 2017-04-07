/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.ConsultorioEx;

import com.lowagie.text.Table;
import groovy.xml.Entity;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import servicios.Conexion;
import vista.ConsultorioEx.RSAITAPTG;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeTAP;
public class ConsultorioExtRsTamizajeAnemiaParasitosis implements Serializable {
    private static final long serialVersionUID = 1L;
    
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long taId;
    private String anemia6mFecha;
    private String anemia6mFua;
    private String anemia1Fecha;
    private String anemia1Fua;
    private String anemia2Fecha;
    private String anemia2Fua;
    private String anemia3Fecha;
    private String anemia3Fua;
    private String anemia4Fecha;
    private String anemia4Fua;
    private String anemia11Fecha;
    private String anemia11Fua;
    private String parasit1Fecha;
    private String parasit1Fua;
    private String parasit2Fecha;
    private String parasit2Fua;
    private String parasit3Fecha;
    private String parasit3Fua;
    private String parasit4Fecha;
    private String parasit4Fua;
    private String parasit11Fecha;
    private String parasit11Fua;
    private String test1Fecha;
    private String test1Fua;
    private String test2Fecha;
    private String test2Fua;
    private String test3Fecha;
    private String test3Fua;
    private String test4Fecha;
    private String test4Fua;
    private String test11Fecha;
    private String test11Fua;
    
    public void ConsultoriosExtTAListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_TAMIZAJE_ANEMIA_PARASITOSIS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                
                //////////////////////////////////////////////////
                //ANEMIA/////////////////////////////////////////////////////////
                //6 MESES 
                try {
                    if(r.getString(3).equals("")){
                        RSAITAPTG.TNFA1.setDate(null);
                    } else {
                        String fechaSeleccionadaD1 = (String)(r.getString(3));
                        DateFormat dfoD1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD1 = dfoD1.parse(fechaSeleccionadaD1);
                        RSAITAPTG.TNFA1.setDate(fechaD1);
                        RSAITAPTG.TNFUAA1.setText(r.getString(4));
                    }
                } catch (Exception e) {
                }
                //1 AÑO
                try {
                    if(r.getString(5).equals("")){
                        RSAITAPTG.TNFA2.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(5));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RSAITAPTG.TNFA2.setDate(fechaD2);
                        RSAITAPTG.TNFUAA2.setText(r.getString(6));
                    }
                } catch (Exception e) {
                }
                //2 AÑOS
                try {
                    if(r.getString(7).equals("")){
                        RSAITAPTG.TNFA3.setDate(null);
                    } else {
                        String fechaSeleccionadaD3 = (String)(r.getString(7));
                        DateFormat dfoD3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD3 = dfoD3.parse(fechaSeleccionadaD3);
                        RSAITAPTG.TNFA3.setDate(fechaD3);
                        RSAITAPTG.TNFUAA3.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                //3 AÑOS
                try {
                    if(r.getString(9).equals("")){
                        RSAITAPTG.TNFA4.setDate(null);
                    } else {
                        String fechaSeleccionadaD4 = (String)(r.getString(9));
                        DateFormat dfoD4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD4 = dfoD4.parse(fechaSeleccionadaD4);
                        RSAITAPTG.TNFA4.setDate(fechaD4);
                        RSAITAPTG.TNFUAA4.setText(r.getString(10));
                    }
                } catch (Exception e) {
                }
                
                //4 AÑOS
                try {
                    if(r.getString(11).equals("")){
                        RSAITAPTG.TNFA5.setDate(null);
                    } else {
                        String fechaSeleccionadaD5 = (String)(r.getString(11));
                        DateFormat dfoD5 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD5 = dfoD5.parse(fechaSeleccionadaD5);
                        RSAITAPTG.TNFA5.setDate(fechaD5);
                        RSAITAPTG.TNFUAA5.setText(r.getString(12));
                    }
                } catch (Exception e) {
                }
                
                //1 - 11 AÑOS
                try {
                    if(r.getString(13).equals("")){
                        RSAITAPTG.TNFA6.setDate(null);
                    } else {
                        String fechaSeleccionadaD6 = (String)(r.getString(13));
                        DateFormat dfoD6 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD6 = dfoD6.parse(fechaSeleccionadaD6);
                        RSAITAPTG.TNFA6.setDate(fechaD6);
                        RSAITAPTG.TNFUAA6.setText(r.getString(14));
                    }
                } catch (Exception e) {
                }
                
                //PARASITOSIS/////////////////////////////////////////////////////////
                //1 AÑO 
                try {
                    if(r.getString(15).equals("")){
                        RSAITAPTG.TNFP1.setDate(null);
                    } else {
                        String fechaSeleccionadaD7 = (String)(r.getString(15));
                        DateFormat dfoD7 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD7 = dfoD7.parse(fechaSeleccionadaD7);
                        RSAITAPTG.TNFP1.setDate(fechaD7);
                        RSAITAPTG.TNFUAP1.setText(r.getString(16));
                    }
                } catch (Exception e) {
                }
                
                //2 AÑOS 
                try {
                    if(r.getString(17).equals("")){
                        RSAITAPTG.TNFP2.setDate(null);
                    } else {
                        String fechaSeleccionadaD8 = (String)(r.getString(17));
                        DateFormat dfoD8 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD8 = dfoD8.parse(fechaSeleccionadaD8);
                        RSAITAPTG.TNFP2.setDate(fechaD8);
                        RSAITAPTG.TNFUAP2.setText(r.getString(18));
                    }
                } catch (Exception e) {
                }
                
                //3 AÑOS 
                try {
                    if(r.getString(19).equals("")){
                        RSAITAPTG.TNFP3.setDate(null);
                    } else {
                        String fechaSeleccionadaD9 = (String)(r.getString(19));
                        DateFormat dfoD9 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD9 = dfoD9.parse(fechaSeleccionadaD9);
                        RSAITAPTG.TNFP3.setDate(fechaD9);
                        RSAITAPTG.TNFUAP3.setText(r.getString(20));
                    }
                } catch (Exception e) {
                }
                
                //4 AÑOS 
                try {
                    if(r.getString(21).equals("")){
                        RSAITAPTG.TNFP4.setDate(null);
                    } else {
                        String fechaSeleccionadaD10 = (String)(r.getString(21));
                        DateFormat dfoD10 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD10 = dfoD10.parse(fechaSeleccionadaD10);
                        RSAITAPTG.TNFP4.setDate(fechaD10);
                        RSAITAPTG.TNFUAP4.setText(r.getString(22));
                    }
                } catch (Exception e) {
                }
                
                //1 - 11 AÑOS 
                try {
                    if(r.getString(23).equals("")){
                        RSAITAPTG.TNFP5.setDate(null);
                    } else {
                        String fechaSeleccionadaD11 = (String)(r.getString(23));
                        DateFormat dfoD11 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD11 = dfoD11.parse(fechaSeleccionadaD11);
                        RSAITAPTG.TNFP5.setDate(fechaD11);
                        RSAITAPTG.TNFUAP5.setText(r.getString(24));
                    }
                } catch (Exception e) {
                }
                //TEST DE GRHAM/////////////////////////////////////////////////////////
                //1 AÑO 
                try {
                    if(r.getString(25).equals("")){
                        RSAITAPTG.TNFT1.setDate(null);
                    } else {
                        String fechaSeleccionadaD12 = (String)(r.getString(25));
                        DateFormat dfoD12 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD12 = dfoD12.parse(fechaSeleccionadaD12);
                        RSAITAPTG.TNFT1.setDate(fechaD12);
                        RSAITAPTG.TNFUAT1.setText(r.getString(26));
                    }
                } catch (Exception e) {
                }
                //2 AÑOS 
                try {
                    if(r.getString(27).equals("")){
                        RSAITAPTG.TNFT2.setDate(null);
                    } else {
                        String fechaSeleccionadaD13 = (String)(r.getString(27));
                        DateFormat dfoD13 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD13 = dfoD13.parse(fechaSeleccionadaD13);
                        RSAITAPTG.TNFT2.setDate(fechaD13);
                        RSAITAPTG.TNFUAT2.setText(r.getString(28));
                    }
                } catch (Exception e) {
                }
                //3 AÑOS 
                try {
                    if(r.getString(29).equals("")){
                        RSAITAPTG.TNFT3.setDate(null);
                    } else {
                        String fechaSeleccionadaD14 = (String)(r.getString(29));
                        DateFormat dfoD14 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD14 = dfoD14.parse(fechaSeleccionadaD14);
                        RSAITAPTG.TNFT3.setDate(fechaD14);
                        RSAITAPTG.TNFUAT3.setText(r.getString(30));
                    }
                } catch (Exception e) {
                }
                
                //4 AÑOS 
                try {
                    if(r.getString(31).equals("")){
                        RSAITAPTG.TNFT4.setDate(null);
                    } else {
                        String fechaSeleccionadaD15 = (String)(r.getString(31));
                        DateFormat dfoD15 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD15 = dfoD15.parse(fechaSeleccionadaD15);
                        RSAITAPTG.TNFT4.setDate(fechaD15);
                        RSAITAPTG.TNFUAT4.setText(r.getString(32));
                    }
                } catch (Exception e) {
                }
                
                //1- 11 AÑOS 
                try {
                    if(r.getString(33).equals("")){
                        RSAITAPTG.TNFT5.setDate(null);
                    } else {
                        String fechaSeleccionadaD16 = (String)(r.getString(33));
                        DateFormat dfoD16 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD16 = dfoD16.parse(fechaSeleccionadaD16);
                        RSAITAPTG.TNFT5.setDate(fechaD16);
                        RSAITAPTG.TNFUAT5.setText(r.getString(34));
                    }
                } catch (Exception e) {
                }
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios tam neo " + e.getMessage());
        }
    }
    
     public boolean mantenimientoRSAITA(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_TAMIZAJE_ANEMIA_PARASITOSIS ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            //////////////////////////////////////////////////
            //ANEMIA//////////////////////////////////////////
            cmd.setString(2, getAnemia6mFecha());
            cmd.setString(3, getAnemia6mFua());
           
            cmd.setString(4, getAnemia1Fecha());
            cmd.setString(5, getAnemia1Fua());
            
            cmd.setString(6, getAnemia2Fecha());
            cmd.setString(7, getAnemia2Fua());
            
            cmd.setString(8, getAnemia3Fecha());
            cmd.setString(9, getAnemia3Fua());
            
            cmd.setString(10, getAnemia4Fecha());
            cmd.setString(11, getAnemia4Fua());
            
            cmd.setString(12, getAnemia11Fecha());
            cmd.setString(13, getAnemia11Fua());
            
            //PARASITOSIS//////////////////////////////////////////
            
            cmd.setString(14, getParasit1Fecha());
            cmd.setString(15, getParasit1Fua());
            
            cmd.setString(16, getParasit2Fecha());
            cmd.setString(17, getParasit2Fua());
            
            cmd.setString(18, getParasit3Fecha());
            cmd.setString(19, getParasit3Fua());
            
            cmd.setString(20, getParasit4Fecha());
            cmd.setString(21, getParasit4Fua());
            
            cmd.setString(22, getParasit11Fecha());
            cmd.setString(23, getParasit11Fua());
            
            //TEST DE GRAHAM//////////////////////////////////
            
            cmd.setString(24, getTest1Fecha());
            cmd.setString(25, getTest1Fua());
            
            cmd.setString(26, getTest2Fecha());
            cmd.setString(27, getTest2Fua());
            
            cmd.setString(28, getTest3Fecha());
            cmd.setString(29, getTest3Fua());
            
            cmd.setString(30, getTest4Fecha());
            cmd.setString(31, getTest4Fua());
            
            cmd.setString(32, getTest11Fecha());
            cmd.setString(33, getTest11Fua());
    
            cmd.setString(34, tipo);
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
    
    public void porcentajeTAP(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_TAMIZAJE_ANEMIA_PARASITOSIS_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeTAP.setText(r.getString(1) + " %"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeDN " + e.getMessage());
        }
    }  

    public ConsultorioExtRsTamizajeAnemiaParasitosis() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsTamizajeAnemiaParasitosis(Long taId) {
        this.taId = taId;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
    }

    public String getAnemia6mFecha() {
        return anemia6mFecha;
    }

    public void setAnemia6mFecha(String anemia6mFecha) {
        this.anemia6mFecha = anemia6mFecha;
    }

    public String getAnemia6mFua() {
        return anemia6mFua;
    }

    public void setAnemia6mFua(String anemia6mFua) {
        this.anemia6mFua = anemia6mFua;
    }

    public String getAnemia1Fecha() {
        return anemia1Fecha;
    }

    public void setAnemia1Fecha(String anemia1Fecha) {
        this.anemia1Fecha = anemia1Fecha;
    }

    public String getAnemia1Fua() {
        return anemia1Fua;
    }

    public void setAnemia1Fua(String anemia1Fua) {
        this.anemia1Fua = anemia1Fua;
    }

    public String getAnemia2Fecha() {
        return anemia2Fecha;
    }

    public void setAnemia2Fecha(String anemia2Fecha) {
        this.anemia2Fecha = anemia2Fecha;
    }

    public String getAnemia2Fua() {
        return anemia2Fua;
    }

    public void setAnemia2Fua(String anemia2Fua) {
        this.anemia2Fua = anemia2Fua;
    }

    public String getAnemia3Fecha() {
        return anemia3Fecha;
    }

    public void setAnemia3Fecha(String anemia3Fecha) {
        this.anemia3Fecha = anemia3Fecha;
    }

    public String getAnemia3Fua() {
        return anemia3Fua;
    }

    public void setAnemia3Fua(String anemia3Fua) {
        this.anemia3Fua = anemia3Fua;
    }

    public String getAnemia4Fecha() {
        return anemia4Fecha;
    }

    public void setAnemia4Fecha(String anemia4Fecha) {
        this.anemia4Fecha = anemia4Fecha;
    }

    public String getAnemia4Fua() {
        return anemia4Fua;
    }

    public void setAnemia4Fua(String anemia4Fua) {
        this.anemia4Fua = anemia4Fua;
    }

    public String getAnemia11Fecha() {
        return anemia11Fecha;
    }

    public void setAnemia11Fecha(String anemia11Fecha) {
        this.anemia11Fecha = anemia11Fecha;
    }

    public String getAnemia11Fua() {
        return anemia11Fua;
    }

    public void setAnemia11Fua(String anemia11Fua) {
        this.anemia11Fua = anemia11Fua;
    }

    public String getParasit1Fecha() {
        return parasit1Fecha;
    }

    public void setParasit1Fecha(String parasit1Fecha) {
        this.parasit1Fecha = parasit1Fecha;
    }

    public String getParasit1Fua() {
        return parasit1Fua;
    }

    public void setParasit1Fua(String parasit1Fua) {
        this.parasit1Fua = parasit1Fua;
    }

    public String getParasit2Fecha() {
        return parasit2Fecha;
    }

    public void setParasit2Fecha(String parasit2Fecha) {
        this.parasit2Fecha = parasit2Fecha;
    }

    public String getParasit2Fua() {
        return parasit2Fua;
    }

    public void setParasit2Fua(String parasit2Fua) {
        this.parasit2Fua = parasit2Fua;
    }

    public String getParasit3Fecha() {
        return parasit3Fecha;
    }

    public void setParasit3Fecha(String parasit3Fecha) {
        this.parasit3Fecha = parasit3Fecha;
    }

    public String getParasit3Fua() {
        return parasit3Fua;
    }

    public void setParasit3Fua(String parasit3Fua) {
        this.parasit3Fua = parasit3Fua;
    }

    public String getParasit4Fecha() {
        return parasit4Fecha;
    }

    public void setParasit4Fecha(String parasit4Fecha) {
        this.parasit4Fecha = parasit4Fecha;
    }

    public String getParasit4Fua() {
        return parasit4Fua;
    }

    public void setParasit4Fua(String parasit4Fua) {
        this.parasit4Fua = parasit4Fua;
    }

    public String getParasit11Fecha() {
        return parasit11Fecha;
    }

    public void setParasit11Fecha(String parasit11Fecha) {
        this.parasit11Fecha = parasit11Fecha;
    }

    public String getParasit11Fua() {
        return parasit11Fua;
    }

    public void setParasit11Fua(String parasit11Fua) {
        this.parasit11Fua = parasit11Fua;
    }

    public String getTest1Fecha() {
        return test1Fecha;
    }

    public void setTest1Fecha(String test1Fecha) {
        this.test1Fecha = test1Fecha;
    }

    public String getTest1Fua() {
        return test1Fua;
    }

    public void setTest1Fua(String test1Fua) {
        this.test1Fua = test1Fua;
    }

    public String getTest2Fecha() {
        return test2Fecha;
    }

    public void setTest2Fecha(String test2Fecha) {
        this.test2Fecha = test2Fecha;
    }

    public String getTest2Fua() {
        return test2Fua;
    }

    public void setTest2Fua(String test2Fua) {
        this.test2Fua = test2Fua;
    }

    public String getTest3Fecha() {
        return test3Fecha;
    }

    public void setTest3Fecha(String test3Fecha) {
        this.test3Fecha = test3Fecha;
    }

    public String getTest3Fua() {
        return test3Fua;
    }

    public void setTest3Fua(String test3Fua) {
        this.test3Fua = test3Fua;
    }

    public String getTest4Fecha() {
        return test4Fecha;
    }

    public void setTest4Fecha(String test4Fecha) {
        this.test4Fecha = test4Fecha;
    }

    public String getTest4Fua() {
        return test4Fua;
    }

    public void setTest4Fua(String test4Fua) {
        this.test4Fua = test4Fua;
    }

    public String getTest11Fecha() {
        return test11Fecha;
    }

    public void setTest11Fecha(String test11Fecha) {
        this.test11Fecha = test11Fecha;
    }

    public String getTest11Fua() {
        return test11Fua;
    }

    public void setTest11Fua(String test11Fua) {
        this.test11Fua = test11Fua;
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
        if (!(object instanceof ConsultorioExtRsTamizajeAnemiaParasitosis)) {
            return false;
        }
        ConsultorioExtRsTamizajeAnemiaParasitosis other = (ConsultorioExtRsTamizajeAnemiaParasitosis) object;
        if ((this.taId == null && other.taId != null) || (this.taId != null && !this.taId.equals(other.taId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsTamizajeAnemiaParasitosis[ taId=" + taId + " ]";
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
