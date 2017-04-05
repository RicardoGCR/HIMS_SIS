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
import vista.ConsultorioEx.RSAIDN;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeDN;
public class ConsultorioExtRsDiagnosticoNutricional implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dnId;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private String dn1Fecha;
    private String dn1Cie10;
    private String dn1Fua;
    private String dn2Fecha;
    private String dn2Cie10;
    private String dn2Fua;
    private String dn3Fecha;
    private String dn3Cie10;
    private String dn3Fua;
    private String dn4Fecha;
    private String dn4Cie10;
    private String dn4Fua;
    private String dn5Fecha;
    private String dn5Cie10;
    private String dn5Fua;
    private String dn6Fecha;
    private String dn6Cie10;
    private String dn6Fua;
    private String dn7Fecha;
    private String dn7Cie10;
    private String dn7Fua;
    private String dn8Fecha;
    private String dn8Cie10;
    private String dn8Fua;
    private String dn9Fecha;
    private String dn9Cie10;
    private String dn9Fua;
    private String dn10Fecha;
    private String dn10Cie10;
    private String dn10Fua;
    private int rsId;
    
     public void ConsultoriosExtDNListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_DIAGNOSTICO_NUTRICIONAL_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
 
                try {
                    if(r.getString(3).equals("")){
                        RSAIDN.FDN1.setDate(null);
                    } else {
                        String fechaSeleccionadaN1 = (String)(r.getString(3));
                        DateFormat dfoN1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN1 = dfoN1.parse(fechaSeleccionadaN1);
                        RSAIDN.FDN1.setDate(fechaN1);
                        RSAIDN.DXDN1.setText(r.getString(4));
                        RSAIDN.FUADN1.setText(r.getString(5));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(6).equals("")){
                        RSAIDN.FDN2.setDate(null);
                    } else {
                        String fechaSeleccionadaN2 = (String)(r.getString(6));
                        DateFormat dfoN2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN2 = dfoN2.parse(fechaSeleccionadaN2);
                        RSAIDN.FDN2.setDate(fechaN2);
                        RSAIDN.DXDN2.setText(r.getString(7));
                        RSAIDN.FUADN2.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                        RSAIDN.FDN3.setDate(null);
                    } else {
                        String fechaSeleccionadaN3 = (String)(r.getString(9));
                        DateFormat dfoN3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN3 = dfoN3.parse(fechaSeleccionadaN3);
                        RSAIDN.FDN3.setDate(fechaN3);
                        RSAIDN.DXDN3.setText(r.getString(10));
                        RSAIDN.FUADN3.setText(r.getString(11));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(12).equals("")){
                        RSAIDN.FDN4.setDate(null);
                    } else {
                        String fechaSeleccionadaN4 = (String)(r.getString(12));
                        DateFormat dfoN4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN4 = dfoN4.parse(fechaSeleccionadaN4);
                        RSAIDN.FDN4.setDate(fechaN4);
                        RSAIDN.DXDN4.setText(r.getString(13));
                        RSAIDN.FUADN4.setText(r.getString(14));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(13).equals("")){
                        RSAIDN.FDN5.setDate(null);
                    } else {
                        String fechaSeleccionadaN5 = (String)(r.getString(13));
                        DateFormat dfoN5 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN5 = dfoN5.parse(fechaSeleccionadaN5);
                        RSAIDN.FDN5.setDate(fechaN5);
                        RSAIDN.DXDN5.setText(r.getString(14));
                        RSAIDN.FUADN5.setText(r.getString(15));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(14).equals("")){
                        RSAIDN.FDN6.setDate(null);
                    } else {
                        String fechaSeleccionadaN6 = (String)(r.getString(14));
                        DateFormat dfoN6 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN6 = dfoN6.parse(fechaSeleccionadaN6);
                        RSAIDN.FDN6.setDate(fechaN6);
                        RSAIDN.DXDN6.setText(r.getString(15));
                        RSAIDN.FUADN6.setText(r.getString(16));
                    }
                } catch (Exception e) {
                }
                try {
                    if(r.getString(17).equals("")){
                        RSAIDN.FDN7.setDate(null);
                    } else {
                        String fechaSeleccionadaN7 = (String)(r.getString(17));
                        DateFormat dfoN7 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN7 = dfoN7.parse(fechaSeleccionadaN7);
                        RSAIDN.FDN7.setDate(fechaN7);
                        RSAIDN.DXDN7.setText(r.getString(18));
                        RSAIDN.FUADN7.setText(r.getString(19));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(20).equals("")){
                        RSAIDN.FDN8.setDate(null);
                    } else {
                        String fechaSeleccionadaN8 = (String)(r.getString(20));
                        DateFormat dfoN8 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN8 = dfoN8.parse(fechaSeleccionadaN8);
                        RSAIDN.FDN8.setDate(fechaN8);
                        RSAIDN.DXDN8.setText(r.getString(21));
                        RSAIDN.FUADN8.setText(r.getString(22));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(23).equals("")){
                        RSAIDN.FDN9.setDate(null);
                    } else {
                        String fechaSeleccionadaN9 = (String)(r.getString(23));
                        DateFormat dfoN9 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN9 = dfoN9.parse(fechaSeleccionadaN9);
                        RSAIDN.FDN9.setDate(fechaN9);
                        RSAIDN.DXDN9.setText(r.getString(24));
                        RSAIDN.FUADN9.setText(r.getString(25));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(26).equals("")){
                        RSAIDN.FDN10.setDate(null);
                    } else {
                        String fechaSeleccionadaN10 = (String)(r.getString(26));
                        DateFormat dfoN10 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaN10 = dfoN10.parse(fechaSeleccionadaN10);
                        RSAIDN.FDN10.setDate(fechaN10);
                        RSAIDN.DXDN10.setText(r.getString(27));
                        RSAIDN.FUADN10.setText(r.getString(28));
                    }
                } catch (Exception e) {
                }
                
                

            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
     
      public boolean mantenimientoRSAIDN(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_DIAGNOSTICO_NUTRICIONAL ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            cmd.setString(2, getDn1Fecha());
            cmd.setString(3, getDn1Cie10());
            cmd.setString(4, getDn1Fua());
            
            cmd.setString(5, getDn2Fecha());
            cmd.setString(6, getDn2Cie10());
            cmd.setString(7, getDn2Fua());
            
            cmd.setString(8, getDn3Fecha());
            cmd.setString(9, getDn3Cie10());
            cmd.setString(10, getDn3Fua());
            
            cmd.setString(11, getDn4Fecha());
            cmd.setString(12, getDn4Cie10());
            cmd.setString(13, getDn4Fua());
            
            cmd.setString(14, getDn5Fecha());
            cmd.setString(15, getDn5Cie10());
            cmd.setString(16, getDn5Fua());
            
            cmd.setString(17, getDn6Fecha());
            cmd.setString(18, getDn6Cie10());
            cmd.setString(19, getDn6Fua());
            
            cmd.setString(20, getDn7Fecha());
            cmd.setString(21, getDn7Cie10());
            cmd.setString(22, getDn7Fua());
            
            cmd.setString(23, getDn8Fecha());
            cmd.setString(24, getDn8Cie10());
            cmd.setString(25, getDn8Fua());
            
            cmd.setString(26, getDn9Fecha());
            cmd.setString(27, getDn9Cie10());
            cmd.setString(28, getDn9Fua());
            
            cmd.setString(29, getDn10Fecha());
            cmd.setString(30, getDn10Cie10());
            cmd.setString(31, getDn10Fua());
            
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

    public void porcentajeDN(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EX_RS_DIAGNOSTICO_NUTRICIONAL_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeDN.setText(r.getString(1) + " %"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeDN " + e.getMessage());
        }
    }  
      
    public ConsultorioExtRsDiagnosticoNutricional() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsDiagnosticoNutricional(int dnId) {
        this.dnId = dnId;
    }

    public int getDnId() {
        return dnId;
    }

    public void setDnId(int dnId) {
        this.dnId = dnId;
    }

    public String getDn1Fecha() {
        return dn1Fecha;
    }

    public void setDn1Fecha(String dn1Fecha) {
        this.dn1Fecha = dn1Fecha;
    }

    public String getDn1Cie10() {
        return dn1Cie10;
    }

    public void setDn1Cie10(String dn1Cie10) {
        this.dn1Cie10 = dn1Cie10;
    }

    public String getDn1Fua() {
        return dn1Fua;
    }

    public void setDn1Fua(String dn1Fua) {
        this.dn1Fua = dn1Fua;
    }

    public String getDn2Fecha() {
        return dn2Fecha;
    }

    public void setDn2Fecha(String dn2Fecha) {
        this.dn2Fecha = dn2Fecha;
    }

    public String getDn2Cie10() {
        return dn2Cie10;
    }

    public void setDn2Cie10(String dn2Cie10) {
        this.dn2Cie10 = dn2Cie10;
    }

    public String getDn2Fua() {
        return dn2Fua;
    }

    public void setDn2Fua(String dn2Fua) {
        this.dn2Fua = dn2Fua;
    }

    public String getDn3Fecha() {
        return dn3Fecha;
    }

    public void setDn3Fecha(String dn3Fecha) {
        this.dn3Fecha = dn3Fecha;
    }

    public String getDn3Cie10() {
        return dn3Cie10;
    }

    public void setDn3Cie10(String dn3Cie10) {
        this.dn3Cie10 = dn3Cie10;
    }

    public String getDn3Fua() {
        return dn3Fua;
    }

    public void setDn3Fua(String dn3Fua) {
        this.dn3Fua = dn3Fua;
    }

    public String getDn4Fecha() {
        return dn4Fecha;
    }

    public void setDn4Fecha(String dn4Fecha) {
        this.dn4Fecha = dn4Fecha;
    }

    public String getDn4Cie10() {
        return dn4Cie10;
    }

    public void setDn4Cie10(String dn4Cie10) {
        this.dn4Cie10 = dn4Cie10;
    }

    public String getDn4Fua() {
        return dn4Fua;
    }

    public void setDn4Fua(String dn4Fua) {
        this.dn4Fua = dn4Fua;
    }

    public String getDn5Fecha() {
        return dn5Fecha;
    }

    public void setDn5Fecha(String dn5Fecha) {
        this.dn5Fecha = dn5Fecha;
    }

    public String getDn5Cie10() {
        return dn5Cie10;
    }

    public void setDn5Cie10(String dn5Cie10) {
        this.dn5Cie10 = dn5Cie10;
    }

    public String getDn5Fua() {
        return dn5Fua;
    }

    public void setDn5Fua(String dn5Fua) {
        this.dn5Fua = dn5Fua;
    }

    public String getDn6Fecha() {
        return dn6Fecha;
    }

    public void setDn6Fecha(String dn6Fecha) {
        this.dn6Fecha = dn6Fecha;
    }

    public String getDn6Cie10() {
        return dn6Cie10;
    }

    public void setDn6Cie10(String dn6Cie10) {
        this.dn6Cie10 = dn6Cie10;
    }

    public String getDn6Fua() {
        return dn6Fua;
    }

    public void setDn6Fua(String dn6Fua) {
        this.dn6Fua = dn6Fua;
    }

    public String getDn7Fecha() {
        return dn7Fecha;
    }

    public void setDn7Fecha(String dn7Fecha) {
        this.dn7Fecha = dn7Fecha;
    }

    public String getDn7Cie10() {
        return dn7Cie10;
    }

    public void setDn7Cie10(String dn7Cie10) {
        this.dn7Cie10 = dn7Cie10;
    }

    public String getDn7Fua() {
        return dn7Fua;
    }

    public void setDn7Fua(String dn7Fua) {
        this.dn7Fua = dn7Fua;
    }

    public String getDn8Fecha() {
        return dn8Fecha;
    }

    public void setDn8Fecha(String dn8Fecha) {
        this.dn8Fecha = dn8Fecha;
    }

    public String getDn8Cie10() {
        return dn8Cie10;
    }

    public void setDn8Cie10(String dn8Cie10) {
        this.dn8Cie10 = dn8Cie10;
    }

    public String getDn8Fua() {
        return dn8Fua;
    }

    public void setDn8Fua(String dn8Fua) {
        this.dn8Fua = dn8Fua;
    }

    public String getDn9Fecha() {
        return dn9Fecha;
    }

    public void setDn9Fecha(String dn9Fecha) {
        this.dn9Fecha = dn9Fecha;
    }

    public String getDn9Cie10() {
        return dn9Cie10;
    }

    public void setDn9Cie10(String dn9Cie10) {
        this.dn9Cie10 = dn9Cie10;
    }

    public String getDn9Fua() {
        return dn9Fua;
    }

    public void setDn9Fua(String dn9Fua) {
        this.dn9Fua = dn9Fua;
    }

    public String getDn10Fecha() {
        return dn10Fecha;
    }

    public void setDn10Fecha(String dn10Fecha) {
        this.dn10Fecha = dn10Fecha;
    }

    public String getDn10Cie10() {
        return dn10Cie10;
    }

    public void setDn10Cie10(String dn10Cie10) {
        this.dn10Cie10 = dn10Cie10;
    }

    public String getDn10Fua() {
        return dn10Fua;
    }

    public void setDn10Fua(String dn10Fua) {
        this.dn10Fua = dn10Fua;
    }

    public int getRsId() {
        return rsId;
    }

    public void setRsId(int rsId) {
        this.rsId = rsId;
    }
    

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoNutricional[ dnId=" + dnId + " ]";
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
