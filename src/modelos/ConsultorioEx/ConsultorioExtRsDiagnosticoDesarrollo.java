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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.annotation.XmlRootElement;
import net.sf.jasperreports.components.table.Column;
import servicios.Conexion;
import vista.ConsultorioEx.RSAIDD;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeDD;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeDN;

/**
 *
 * @author PC02
 */

public class ConsultorioExtRsDiagnosticoDesarrollo implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int ddId;
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
    private String dn11Fecha;
    private String dn11Cie10;
    private String dn11Fua;
    private String dn12Fecha;
    private String dn12Cie10;
    private String dn12Fua;
    private int rsId;
    
    public void ConsultoriosExtDDListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_DIAGNOSTICO_DESARROLLO_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
 
                try {
                    if(r.getString(3).equals("")){
                        RSAIDD.FDD1.setDate(null);
                    } else {
                        String fechaSeleccionadaD1 = (String)(r.getString(3));
                        DateFormat dfoD1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD1 = dfoD1.parse(fechaSeleccionadaD1);
                        RSAIDD.FDD1.setDate(fechaD1);
                        RSAIDD.DXDD1.setText(r.getString(4));
                        RSAIDD.FUADD1.setText(r.getString(5));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(6).equals("")){
                        RSAIDD.FDD2.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(6));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RSAIDD.FDD2.setDate(fechaD2);
                        RSAIDD.DXDD2.setText(r.getString(7));
                        RSAIDD.FUADD2.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                        RSAIDD.FDD3.setDate(null);
                    } else {
                        String fechaSeleccionadaD3 = (String)(r.getString(9));
                        DateFormat dfoD3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD3 = dfoD3.parse(fechaSeleccionadaD3);
                        RSAIDD.FDD3.setDate(fechaD3);
                        RSAIDD.DXDD3.setText(r.getString(10));
                        RSAIDD.FUADD3.setText(r.getString(11));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(12).equals("")){
                        RSAIDD.FDD4.setDate(null);
                    } else {
                        String fechaSeleccionadaD4 = (String)(r.getString(12));
                        DateFormat dfoD4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD4 = dfoD4.parse(fechaSeleccionadaD4);
                        RSAIDD.FDD4.setDate(fechaD4);
                        RSAIDD.DXDD4.setText(r.getString(13));
                        RSAIDD.FUADD4.setText(r.getString(14));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(15).equals("")){
                        RSAIDD.FDD5.setDate(null);
                    } else {
                        String fechaSeleccionadaD5 = (String)(r.getString(15));
                        DateFormat dfoD5 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD5 = dfoD5.parse(fechaSeleccionadaD5);
                        RSAIDD.FDD5.setDate(fechaD5);
                        RSAIDD.DXDD5.setText(r.getString(16));
                        RSAIDD.FUADD5.setText(r.getString(17));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(18).equals("")){
                        RSAIDD.FDD6.setDate(null);
                    } else {
                        String fechaSeleccionadaD6 = (String)(r.getString(18));
                        DateFormat dfoD6 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD6 = dfoD6.parse(fechaSeleccionadaD6);
                        RSAIDD.FDD6.setDate(fechaD6);
                        RSAIDD.DXDD6.setText(r.getString(19));
                        RSAIDD.FUADD6.setText(r.getString(20));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(21).equals("")){
                        RSAIDD.FDD7.setDate(null);
                    } else {
                        String fechaSeleccionadaD7 = (String)(r.getString(21));
                        DateFormat dfoD7 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD7 = dfoD7.parse(fechaSeleccionadaD7);
                        RSAIDD.FDD7.setDate(fechaD7);
                        RSAIDD.DXDD7.setText(r.getString(22));
                        RSAIDD.FUADD7.setText(r.getString(23));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(24).equals("")){
                        RSAIDD.FDD8.setDate(null);
                    } else {
                        String fechaSeleccionadaD8 = (String)(r.getString(24));
                        DateFormat dfoD8 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD8 = dfoD8.parse(fechaSeleccionadaD8);
                        RSAIDD.FDD8.setDate(fechaD8);
                        RSAIDD.DXDD8.setText(r.getString(25));
                        RSAIDD.FUADD8.setText(r.getString(26));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(27).equals("")){
                        RSAIDD.FDD9.setDate(null);
                    } else {
                        String fechaSeleccionadaD9 = (String)(r.getString(27));
                        DateFormat dfoD9 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD9 = dfoD9.parse(fechaSeleccionadaD9);
                        RSAIDD.FDD9.setDate(fechaD9);
                        RSAIDD.DXDD9.setText(r.getString(28));
                        RSAIDD.FUADD9.setText(r.getString(29));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(30).equals("")){
                        RSAIDD.FDD10.setDate(null);
                    } else {
                        String fechaSeleccionadaD10 = (String)(r.getString(30));
                        DateFormat dfoD10 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD10 = dfoD10.parse(fechaSeleccionadaD10);
                        RSAIDD.FDD10.setDate(fechaD10);
                        RSAIDD.DXDD10.setText(r.getString(31));
                        RSAIDD.FUADD10.setText(r.getString(32));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(33).equals("")){
                        RSAIDD.FDD11.setDate(null);
                    } else {
                        String fechaSeleccionadaD11 = (String)(r.getString(33));
                        DateFormat dfoD11 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD11 = dfoD11.parse(fechaSeleccionadaD11);
                        RSAIDD.FDD11.setDate(fechaD11);
                        RSAIDD.DXDD11.setText(r.getString(34));
                        RSAIDD.FUADD11.setText(r.getString(35));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(36).equals("")){
                        RSAIDD.FDD12.setDate(null);
                    } else {
                        String fechaSeleccionadaD12 = (String)(r.getString(36));
                        DateFormat dfoD12 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD12 = dfoD12.parse(fechaSeleccionadaD12);
                        RSAIDD.FDD12.setDate(fechaD12);
                        RSAIDD.DXDD12.setText(r.getString(37));
                        RSAIDD.FUADD12.setText(r.getString(38));
                    }
                } catch (Exception e) {
                }
                }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
    
      public boolean mantenimientoRSAIDD(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_DIAGNOSTICO_DESARROLLO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            
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
            
            cmd.setString(32, getDn11Fecha());
            cmd.setString(33, getDn11Cie10());
            cmd.setString(34, getDn11Fua());
            
            cmd.setString(35, getDn12Fecha());
            cmd.setString(36, getDn12Cie10());
            cmd.setString(37, getDn12Fua());
            
            cmd.setString(38, tipo);
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
      
    public void porcentajeDD(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_DIAGNOSTICO_DESARROLLO_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeDD.setText(r.getString(1) + " %"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeDD " + e.getMessage());
        }
    }  
    
    public void cargarDatosCie10(String descripcion,JTable tabla){
    String consulta="";
        try {
            tabla.setModel(new DefaultTableModel());
            String titulos[]={"Nro","Código","Diagnóstico"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC CIE10_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, descripcion);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // clasificacion
                fila[1]=r.getString(2); //codigo
                fila[2]=r.getString(3); //codigo
                    m.addRow(fila);
                    c++;
            }
            tabla.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tabla.setRowSorter(elQueOrdena);
            tabla.setModel(m);
            formatoTablaCargarCie10(tabla);
        } catch (Exception e) {
            System.out.println("Error_cargarDatosCie10: " + e.getMessage());
        }
    }
     public void formatoTablaCargarCie10(JTable tabla){
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);//CODIGO
        tabla.getColumnModel().getColumn(2).setPreferredWidth(500);//CODIGO
        tabla.setRowHeight(30);
    }  
    public ConsultorioExtRsDiagnosticoDesarrollo() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsDiagnosticoDesarrollo(int ddId) {
        this.ddId = ddId;
    }

    public int getDdId() {
        return ddId;
    }

    public void setDdId(int ddId) {
        this.ddId = ddId;
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

    public String getDn11Fecha() {
        return dn11Fecha;
    }

    public void setDn11Fecha(String dn11Fecha) {
        this.dn11Fecha = dn11Fecha;
    }

    public String getDn11Cie10() {
        return dn11Cie10;
    }

    public void setDn11Cie10(String dn11Cie10) {
        this.dn11Cie10 = dn11Cie10;
    }

    public String getDn11Fua() {
        return dn11Fua;
    }

    public void setDn11Fua(String dn11Fua) {
        this.dn11Fua = dn11Fua;
    }

    public String getDn12Fecha() {
        return dn12Fecha;
    }

    public void setDn12Fecha(String dn12Fecha) {
        this.dn12Fecha = dn12Fecha;
    }

    public String getDn12Cie10() {
        return dn12Cie10;
    }

    public void setDn12Cie10(String dn12Cie10) {
        this.dn12Cie10 = dn12Cie10;
    }

    public String getDn12Fua() {
        return dn12Fua;
    }

    public void setDn12Fua(String dn12Fua) {
        this.dn12Fua = dn12Fua;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsDiagnosticoDesarrollo[ ddId=" + ddId + " ]";
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
