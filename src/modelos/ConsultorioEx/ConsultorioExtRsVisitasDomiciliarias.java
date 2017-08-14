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
import vista.CRED.RSAIVD;
import static vista.CRED.RegistroSeguimiento.lblPorcentajeVD;
public class ConsultorioExtRsVisitasDomiciliarias implements Serializable {
    private static final long serialVersionUID = 1L;
    private Connection cn;
    private int rs_id;
    private Long vmId;
    private String vd1Fecha;
    private String vd2Fecha;
    private String vd3Fecha;
    private String vd4Fecha;
    private String vd5Fecha;
    private String vd6Fecha;
    private String vd7Fecha;
    private String vd8Fecha;
    private String vd9Fecha;
    private String vd10Fecha;
    private String vd11Fecha;
    private String vd12Fecha;
    private String vd13Fecha;
    private String vd14Fecha;
    private String vd15Fecha;
    private String vd16Fecha;
    private String vd17Fecha;
    private String vd18Fecha;
    private String vd19Fecha;
    private String vd20Fecha;
    private String vd21Fecha;
    private String vd22Fecha;
    private String vd23Fecha;
    private String vd24Fecha;
    private String vd25Fecha;
    private String vd26Fecha;
    private String vd27Fecha;
    private String vd28Fecha;
    private String vd29Fecha;
    private String vd30Fecha;
    private int rsId;
    
    public void ConsultoriosExtVDListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_VISITAS_DOMICILIARIAS_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
       
                /////////////////////////////////////////
                //1
                try {
                  if(r.getString(3).equals("")){
                    RSAIVD.DVF1.setDate(null);
                } else {
                    String fechaSeleccionada1 = (String)(r.getString(3));
                    DateFormat dfo1 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha1 = dfo1.parse(fechaSeleccionada1);
                    RSAIVD.DVF1.setDate(fecha1);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(4).equals("")){
                    RSAIVD.DVF12.setDate(null);
                } else {
                    String fechaSeleccionada12 = (String)(r.getString(4));
                    DateFormat dfo12 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha12 = dfo12.parse(fechaSeleccionada12);
                    RSAIVD.DVF12.setDate(fecha12);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(5).equals("")){
                    RSAIVD.DVF13.setDate(null);
                } else {
                    String fechaSeleccionada13 = (String)(r.getString(5));
                    DateFormat dfo13 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha13 = dfo13.parse(fechaSeleccionada13);
                    RSAIVD.DVF13.setDate(fecha13);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(6).equals("")){
                    RSAIVD.DVF14.setDate(null);
                } else {
                    String fechaSeleccionada14 = (String)(r.getString(6));
                    DateFormat dfo14 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha14 = dfo14.parse(fechaSeleccionada14);
                    RSAIVD.DVF14.setDate(fecha14);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(7).equals("")){
                    RSAIVD.DVF15.setDate(null);
                } else {
                    String fechaSeleccionada15 = (String)(r.getString(7));
                    DateFormat dfo15 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha15 = dfo15.parse(fechaSeleccionada15);
                    RSAIVD.DVF15.setDate(fecha15);
                }
                } catch (Exception e) {
                }
                
                //////////////////////////////////////////////////////
                //2
                try {
                  if(r.getString(8).equals("")){
                    RSAIVD.DVF2.setDate(null);
                } else {
                    String fechaSeleccionada2 = (String)(r.getString(8));
                    DateFormat dfo2 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha2 = dfo2.parse(fechaSeleccionada2);
                    RSAIVD.DVF2.setDate(fecha2);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(9).equals("")){
                    RSAIVD.DVF22.setDate(null);
                } else {
                    String fechaSeleccionada21 = (String)(r.getString(9));
                    DateFormat dfo21 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha21 = dfo21.parse(fechaSeleccionada21);
                    RSAIVD.DVF22.setDate(fecha21);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(10).equals("")){
                    RSAIVD.DVF23.setDate(null);
                } else {
                    String fechaSeleccionada22 = (String)(r.getString(10));
                    DateFormat dfo22 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha22 = dfo22.parse(fechaSeleccionada22);
                    RSAIVD.DVF23.setDate(fecha22);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(11).equals("")){
                    RSAIVD.DVF24.setDate(null);
                } else {
                    String fechaSeleccionada23 = (String)(r.getString(11));
                    DateFormat dfo23 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha23 = dfo23.parse(fechaSeleccionada23);
                    RSAIVD.DVF24.setDate(fecha23);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(12).equals("")){
                    RSAIVD.DVF25.setDate(null);
                } else {
                    String fechaSeleccionada24 = (String)(r.getString(12));
                    DateFormat dfo24 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha24 = dfo24.parse(fechaSeleccionada24);
                    RSAIVD.DVF25.setDate(fecha24);
                }
                } catch (Exception e) {
                }
                //////////////////////////////////////////////////////////
                //3
                try {
                  if(r.getString(13).equals("")){
                    RSAIVD.DVF3.setDate(null);
                } else {
                    String fechaSeleccionada3 = (String)(r.getString(13));
                    DateFormat dfo3 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha3= dfo3.parse(fechaSeleccionada3);
                    RSAIVD.DVF3.setDate(fecha3);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(14).equals("")){
                    RSAIVD.DVF32.setDate(null);
                } else {
                    String fechaSeleccionada32 = (String)(r.getString(14));
                    DateFormat dfo32 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha32 = dfo32.parse(fechaSeleccionada32);
                    RSAIVD.DVF32.setDate(fecha32);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(15).equals("")){
                    RSAIVD.DVF33.setDate(null);
                } else {
                    String fechaSeleccionada33 = (String)(r.getString(15));
                    DateFormat dfo33 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha33 = dfo33.parse(fechaSeleccionada33);
                    RSAIVD.DVF33.setDate(fecha33);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(16).equals("")){
                    RSAIVD.DVF34.setDate(null);
                } else {
                    String fechaSeleccionada34 = (String)(r.getString(16));
                    DateFormat dfo34 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha34 = dfo34.parse(fechaSeleccionada34);
                    RSAIVD.DVF34.setDate(fecha34);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(17).equals("")){
                    RSAIVD.DVF35.setDate(null);
                } else {
                    String fechaSeleccionada35 = (String)(r.getString(17));
                    DateFormat dfo35 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha35 = dfo35.parse(fechaSeleccionada35);
                    RSAIVD.DVF35.setDate(fecha35);
                }
                } catch (Exception e) {
                }
                //////////////////////////////////////////////
                //4
                try {
                  if(r.getString(18).equals("")){
                    RSAIVD.DVF4.setDate(null);
                } else {
                    String fechaSeleccionada4 = (String)(r.getString(18));
                    DateFormat dfo4 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha4 = dfo4.parse(fechaSeleccionada4);
                    RSAIVD.DVF4.setDate(fecha4);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(19).equals("")){
                    RSAIVD.DVF42.setDate(null);
                } else {
                    String fechaSeleccionada42 = (String)(r.getString(19));
                    DateFormat dfo42 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha42 = dfo42.parse(fechaSeleccionada42);
                    RSAIVD.DVF42.setDate(fecha42);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(20).equals("")){
                    RSAIVD.DVF43.setDate(null);
                } else {
                    String fechaSeleccionada43 = (String)(r.getString(20));
                    DateFormat dfo43 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha43 = dfo43.parse(fechaSeleccionada43);
                    RSAIVD.DVF43.setDate(fecha43);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(21).equals("")){
                    RSAIVD.DVF44.setDate(null);
                } else {
                    String fechaSeleccionada44 = (String)(r.getString(21));
                    DateFormat dfo44 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha44 = dfo44.parse(fechaSeleccionada44);
                    RSAIVD.DVF44.setDate(fecha44);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(22).equals("")){
                    RSAIVD.DVF45.setDate(null);
                } else {
                    String fechaSeleccionada45 = (String)(r.getString(22));
                    DateFormat dfo45 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha45 = dfo45.parse(fechaSeleccionada45);
                    RSAIVD.DVF45.setDate(fecha45);
                }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////////////
                //5
                try {
                  if(r.getString(23).equals("")){
                    RSAIVD.DVF5.setDate(null);
                } else {
                    String fechaSeleccionada5 = (String)(r.getString(23));
                    DateFormat dfo5 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha5 = dfo5.parse(fechaSeleccionada5);
                    RSAIVD.DVF5.setDate(fecha5);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(24).equals("")){
                    RSAIVD.DVF52.setDate(null);
                } else {
                    String fechaSeleccionada52 = (String)(r.getString(24));
                    DateFormat dfo52 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha52 = dfo52.parse(fechaSeleccionada52);
                    RSAIVD.DVF52.setDate(fecha52);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(25).equals("")){
                    RSAIVD.DVF53.setDate(null);
                } else {
                    String fechaSeleccionada53 = (String)(r.getString(25));
                    DateFormat dfo53 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha53 = dfo53.parse(fechaSeleccionada53);
                    RSAIVD.DVF53.setDate(fecha53);
                }
                } catch (Exception e) {
                }
                try {
                  if(r.getString(26).equals("")){
                    RSAIVD.DVF54.setDate(null);
                } else {
                    String fechaSeleccionada54 = (String)(r.getString(26));
                    DateFormat dfo54 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha54 = dfo54.parse(fechaSeleccionada54);
                    RSAIVD.DVF54.setDate(fecha54);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(27).equals("")){
                    RSAIVD.DVF55.setDate(null);
                } else {
                    String fechaSeleccionada55 = (String)(r.getString(27));
                    DateFormat dfo55 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha55 = dfo55.parse(fechaSeleccionada55);
                    RSAIVD.DVF55.setDate(fecha55);
                }
                } catch (Exception e) {
                }
                ///////////////////////////////////////////////////////////
                //6
                try {
                  if(r.getString(28).equals("")){
                    RSAIVD.DVF6.setDate(null);
                } else {
                    String fechaSeleccionada6 = (String)(r.getString(28));
                    DateFormat dfo6 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha6 = dfo6.parse(fechaSeleccionada6);
                    RSAIVD.DVF6.setDate(fecha6);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(29).equals("")){
                    RSAIVD.DVF62.setDate(null);
                } else {
                    String fechaSeleccionada62 = (String)(r.getString(29));
                    DateFormat dfo62 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha62 = dfo62.parse(fechaSeleccionada62);
                    RSAIVD.DVF62.setDate(fecha62);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(30).equals("")){
                    RSAIVD.DVF63.setDate(null);
                } else {
                    String fechaSeleccionada63 = (String)(r.getString(30));
                    DateFormat dfo63= new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha63 = dfo63.parse(fechaSeleccionada63);
                    RSAIVD.DVF63.setDate(fecha63);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(31).equals("")){
                    RSAIVD.DVF64.setDate(null);
                } else {
                    String fechaSeleccionada64 = (String)(r.getString(31));
                    DateFormat dfo64 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha64 = dfo64.parse(fechaSeleccionada64);
                    RSAIVD.DVF64.setDate(fecha64);
                }
                } catch (Exception e) {
                }
                
                try {
                  if(r.getString(32).equals("")){
                    RSAIVD.DVF65.setDate(null);
                } else {
                    String fechaSeleccionada65 = (String)(r.getString(32));
                    DateFormat dfo65 = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha65 = dfo65.parse(fechaSeleccionada65);
                    RSAIVD.DVF65.setDate(fecha65);
                }
                } catch (Exception e) {
                }
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }  
    
    public boolean mantenimientoRSAIVD(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_VISITAS_DOMICILIARIAS ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            cmd.setString(2, getVd1Fecha());
            cmd.setString(3, getVd2Fecha());
            cmd.setString(4, getVd3Fecha());
            cmd.setString(5, getVd4Fecha());
            cmd.setString(6, getVd5Fecha());
            cmd.setString(7, getVd6Fecha());
            cmd.setString(8, getVd7Fecha());
            cmd.setString(9, getVd8Fecha());
            cmd.setString(10, getVd9Fecha());
            cmd.setString(11, getVd10Fecha());
            cmd.setString(12, getVd11Fecha());
            cmd.setString(13, getVd12Fecha());
            cmd.setString(14, getVd13Fecha());
            cmd.setString(15, getVd14Fecha());
            cmd.setString(16, getVd15Fecha());
            cmd.setString(17, getVd16Fecha());
            cmd.setString(18, getVd17Fecha());
            cmd.setString(19, getVd18Fecha());
            cmd.setString(20, getVd19Fecha());
            cmd.setString(21, getVd20Fecha());
            cmd.setString(22, getVd21Fecha());
            cmd.setString(23, getVd22Fecha());
            cmd.setString(24, getVd23Fecha());
            cmd.setString(25, getVd24Fecha());
            cmd.setString(26, getVd25Fecha());
            cmd.setString(27, getVd26Fecha());
            cmd.setString(28, getVd27Fecha());
            cmd.setString(29, getVd28Fecha());
            cmd.setString(30, getVd29Fecha());
            cmd.setString(31, getVd30Fecha());

            cmd.setString(32, tipo);
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
    public void porcentajeVD(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_VISITAS_DOMICILIARIAS_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeVD.setText(r.getString(1) + " %"); 
                RSAIVD.lblPorcentajeVisitas.setText(r.getString(1) + " % Completado");
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeVD " + e.getMessage());
        }
    }

    public ConsultorioExtRsVisitasDomiciliarias() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsVisitasDomiciliarias(Long vmId) {
        this.vmId = vmId;
    }

    public Long getVmId() {
        return vmId;
    }

    public void setVmId(Long vmId) {
        this.vmId = vmId;
    }

    public String getVd1Fecha() {
        return vd1Fecha;
    }

    public void setVd1Fecha(String vd1Fecha) {
        this.vd1Fecha = vd1Fecha;
    }

    public String getVd2Fecha() {
        return vd2Fecha;
    }

    public void setVd2Fecha(String vd2Fecha) {
        this.vd2Fecha = vd2Fecha;
    }

    public String getVd3Fecha() {
        return vd3Fecha;
    }

    public void setVd3Fecha(String vd3Fecha) {
        this.vd3Fecha = vd3Fecha;
    }

    public String getVd4Fecha() {
        return vd4Fecha;
    }

    public void setVd4Fecha(String vd4Fecha) {
        this.vd4Fecha = vd4Fecha;
    }

    public String getVd5Fecha() {
        return vd5Fecha;
    }

    public void setVd5Fecha(String vd5Fecha) {
        this.vd5Fecha = vd5Fecha;
    }

    public String getVd6Fecha() {
        return vd6Fecha;
    }

    public void setVd6Fecha(String vd6Fecha) {
        this.vd6Fecha = vd6Fecha;
    }

    public String getVd7Fecha() {
        return vd7Fecha;
    }

    public void setVd7Fecha(String vd7Fecha) {
        this.vd7Fecha = vd7Fecha;
    }

    public String getVd8Fecha() {
        return vd8Fecha;
    }

    public void setVd8Fecha(String vd8Fecha) {
        this.vd8Fecha = vd8Fecha;
    }

    public String getVd9Fecha() {
        return vd9Fecha;
    }

    public void setVd9Fecha(String vd9Fecha) {
        this.vd9Fecha = vd9Fecha;
    }

    public String getVd10Fecha() {
        return vd10Fecha;
    }

    public void setVd10Fecha(String vd10Fecha) {
        this.vd10Fecha = vd10Fecha;
    }

    public String getVd11Fecha() {
        return vd11Fecha;
    }

    public void setVd11Fecha(String vd11Fecha) {
        this.vd11Fecha = vd11Fecha;
    }

    public String getVd12Fecha() {
        return vd12Fecha;
    }

    public void setVd12Fecha(String vd12Fecha) {
        this.vd12Fecha = vd12Fecha;
    }

    public String getVd13Fecha() {
        return vd13Fecha;
    }

    public void setVd13Fecha(String vd13Fecha) {
        this.vd13Fecha = vd13Fecha;
    }

    public String getVd14Fecha() {
        return vd14Fecha;
    }

    public void setVd14Fecha(String vd14Fecha) {
        this.vd14Fecha = vd14Fecha;
    }

    public String getVd15Fecha() {
        return vd15Fecha;
    }

    public void setVd15Fecha(String vd15Fecha) {
        this.vd15Fecha = vd15Fecha;
    }

    public String getVd16Fecha() {
        return vd16Fecha;
    }

    public void setVd16Fecha(String vd16Fecha) {
        this.vd16Fecha = vd16Fecha;
    }

    public String getVd17Fecha() {
        return vd17Fecha;
    }

    public void setVd17Fecha(String vd17Fecha) {
        this.vd17Fecha = vd17Fecha;
    }

    public String getVd18Fecha() {
        return vd18Fecha;
    }

    public void setVd18Fecha(String vd18Fecha) {
        this.vd18Fecha = vd18Fecha;
    }

    public String getVd19Fecha() {
        return vd19Fecha;
    }

    public void setVd19Fecha(String vd19Fecha) {
        this.vd19Fecha = vd19Fecha;
    }

    public String getVd20Fecha() {
        return vd20Fecha;
    }

    public void setVd20Fecha(String vd20Fecha) {
        this.vd20Fecha = vd20Fecha;
    }

    public String getVd21Fecha() {
        return vd21Fecha;
    }

    public void setVd21Fecha(String vd21Fecha) {
        this.vd21Fecha = vd21Fecha;
    }

    public String getVd22Fecha() {
        return vd22Fecha;
    }

    public void setVd22Fecha(String vd22Fecha) {
        this.vd22Fecha = vd22Fecha;
    }

    public String getVd23Fecha() {
        return vd23Fecha;
    }

    public void setVd23Fecha(String vd23Fecha) {
        this.vd23Fecha = vd23Fecha;
    }

    public String getVd24Fecha() {
        return vd24Fecha;
    }

    public void setVd24Fecha(String vd24Fecha) {
        this.vd24Fecha = vd24Fecha;
    }

    public String getVd25Fecha() {
        return vd25Fecha;
    }

    public void setVd25Fecha(String vd25Fecha) {
        this.vd25Fecha = vd25Fecha;
    }

    public String getVd26Fecha() {
        return vd26Fecha;
    }

    public void setVd26Fecha(String vd26Fecha) {
        this.vd26Fecha = vd26Fecha;
    }

    public String getVd27Fecha() {
        return vd27Fecha;
    }

    public void setVd27Fecha(String vd27Fecha) {
        this.vd27Fecha = vd27Fecha;
    }

    public String getVd28Fecha() {
        return vd28Fecha;
    }

    public void setVd28Fecha(String vd28Fecha) {
        this.vd28Fecha = vd28Fecha;
    }

    public String getVd29Fecha() {
        return vd29Fecha;
    }

    public void setVd29Fecha(String vd29Fecha) {
        this.vd29Fecha = vd29Fecha;
    }

    public String getVd30Fecha() {
        return vd30Fecha;
    }

    public void setVd30Fecha(String vd30Fecha) {
        this.vd30Fecha = vd30Fecha;
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
        hash += (vmId != null ? vmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsVisitasDomiciliarias)) {
            return false;
        }
        ConsultorioExtRsVisitasDomiciliarias other = (ConsultorioExtRsVisitasDomiciliarias) object;
        if ((this.vmId == null && other.vmId != null) || (this.vmId != null && !this.vmId.equals(other.vmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsVisitasDomiciliarias[ vmId=" + vmId + " ]";
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
