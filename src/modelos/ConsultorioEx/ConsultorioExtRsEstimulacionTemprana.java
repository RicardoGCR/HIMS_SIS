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
import servicios.Conexion;
import vista.ConsultorioEx.RSAIET;
import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeDN;
//import static vista.ConsultorioEx.RegistroSeguimiento.lblPorcentajeET;

/**
 *
 * @author PC02
 */
public class ConsultorioExtRsEstimulacionTemprana implements Serializable {
    private static final long serialVersionUID = 1L;

    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int rs_id;
    private Long etId;
    private String m1Fecha;
    private String m1Cie10;
    private String m1Fua;
    private String m2Fecha;
    private String m2Cie10;
    private String m2Fua;
    private String m3Fecha;
    private String m3Cie10;
    private String m3Fua;
    private String m4Fecha;
    private String m4Cie10;
    private String m4Fua;
    private String m5Fecha;
    private String m5Cie10;
    private String m5Fua;
    private String m6Fecha;
    private String m6Cie10;
    private String m6Fua;
    private String m7Fecha;
    private String m7Cie10;
    private String m7Fua;
    private String m8Fecha;
    private String m8Cie10;
    private String m8Fua;
    private String m9Fecha;
    private String m9Cie10;
    private String m9Fua;
    private String m10Fecha;
    private String m10Cie10;
    private String m10Fua;
    private String m11Fecha;
    private String m11Cie10;
    private String m11Fua;
    private String m12Fecha;
    private String m12Cie10;
    private String m12Fua;
    private String m13Fecha;
    private String m13Cie10;
    private String m13Fua;
    private int rsId;

     public void ConsultoriosExtETListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_ESTIMULACION_TEMPRANA_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                //////////////////////////////////////////////////////////////
                //MENORES DE UN AÑO
                try {
                    if(r.getString(3).equals("")){
                        RSAIET.FETM1.setDate(null);
                    } else {
                        String fechaSeleccionadaD1 = (String)(r.getString(3));
                        DateFormat dfoD1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD1 = dfoD1.parse(fechaSeleccionadaD1);
                        RSAIET.FETM1.setDate(fechaD1);
                        RSAIET.DXETM1.setText(r.getString(4));
                        RSAIET.FUAETM1.setText(r.getString(5));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(6).equals("")){
                        RSAIET.FETM2.setDate(null);
                    } else {
                        String fechaSeleccionadaM2 = (String)(r.getString(6));
                        DateFormat dfoM2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM2 = dfoM2.parse(fechaSeleccionadaM2);
                        RSAIET.FETM2.setDate(fechaM2);
                        RSAIET.DXETM2.setText(r.getString(7));
                        RSAIET.FUAETM2.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                        RSAIET.FETM3.setDate(null);
                    } else {
                        String fechaSeleccionadaM3 = (String)(r.getString(9));
                        DateFormat dfoM3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM3 = dfoM3.parse(fechaSeleccionadaM3);
                        RSAIET.FETM3.setDate(fechaM3);
                        RSAIET.DXETM3.setText(r.getString(10));
                        RSAIET.FUAETM3.setText(r.getString(11));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(12).equals("")){
                        RSAIET.FETM4.setDate(null);
                    } else {
                        String fechaSeleccionadaM4 = (String)(r.getString(12));
                        DateFormat dfoM4 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM4 = dfoM4.parse(fechaSeleccionadaM4);
                        RSAIET.FETM4.setDate(fechaM4);
                        RSAIET.DXETM4.setText(r.getString(13));
                        RSAIET.FUAETM4.setText(r.getString(14));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(15).equals("")){
                        RSAIET.FETM5.setDate(null);
                    } else {
                        String fechaSeleccionadaM5 = (String)(r.getString(15));
                        DateFormat dfoM5 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM5 = dfoM5.parse(fechaSeleccionadaM5);
                        RSAIET.FETM5.setDate(fechaM5);
                        RSAIET.DXETM5.setText(r.getString(16));
                        RSAIET.FUAETM5.setText(r.getString(17));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(18).equals("")){
                        RSAIET.FETM6.setDate(null);
                    } else {
                        String fechaSeleccionadaM6 = (String)(r.getString(18));
                        DateFormat dfoM6 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM6 = dfoM6.parse(fechaSeleccionadaM6);
                        RSAIET.FETM6.setDate(fechaM6);
                        RSAIET.DXETM6.setText(r.getString(19));
                        RSAIET.FUAETM6.setText(r.getString(20));
                    }
                } catch (Exception e) {
                }
                //////////////////////////////////////
                //1 AÑO
                 try {
                    if(r.getString(21).equals("")){
                        RSAIET.FET11.setDate(null);
                    } else {
                        String fechaSeleccionadaM7 = (String)(r.getString(21));
                        DateFormat dfoM7 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM7 = dfoM7.parse(fechaSeleccionadaM7);
                        RSAIET.FET11.setDate(fechaM7);
                        RSAIET.DXET11.setText(r.getString(22));
                        RSAIET.FUAET11.setText(r.getString(23));
                    }
                } catch (Exception e) {
                }
                 
                try {
                    if(r.getString(24).equals("")){
                        RSAIET.FET12.setDate(null);
                    } else {
                        String fechaSeleccionadaM8 = (String)(r.getString(24));
                        DateFormat dfoM8 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM8 = dfoM8.parse(fechaSeleccionadaM8);
                        RSAIET.FET12.setDate(fechaM8);
                        RSAIET.DXET12.setText(r.getString(25));
                        RSAIET.FUAET12.setText(r.getString(26));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(27).equals("")){
                        RSAIET.FET13.setDate(null);
                    } else {
                        String fechaSeleccionadaM9 = (String)(r.getString(27));
                        DateFormat dfoM9 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM9 = dfoM9.parse(fechaSeleccionadaM9);
                        RSAIET.FET13.setDate(fechaM9);
                        RSAIET.DXET13.setText(r.getString(22));
                        RSAIET.FUAET13.setText(r.getString(23));
                    }
                } catch (Exception e) {
                }
                
                
                try {
                    if(r.getString(24).equals("")){
                        RSAIET.FET14.setDate(null);
                    } else {
                        String fechaSeleccionadaM10 = (String)(r.getString(24));
                        DateFormat dfoM10 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM10 = dfoM10.parse(fechaSeleccionadaM10);
                        RSAIET.FET14.setDate(fechaM10);
                        RSAIET.DXET14.setText(r.getString(25));
                        RSAIET.FUAET14.setText(r.getString(26));
                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////
                //2 AÑOS
                try {
                    if(r.getString(27).equals("")){
                        RSAIET.FET21.setDate(null);
                    } else {
                        String fechaSeleccionadaM11 = (String)(r.getString(27));
                        DateFormat dfoM11 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM11 = dfoM11.parse(fechaSeleccionadaM11);
                        RSAIET.FET21.setDate(fechaM11);
                        RSAIET.DXET21.setText(r.getString(28));
                        RSAIET.FUAET21.setText(r.getString(29));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(30).equals("")){
                        RSAIET.FET22.setDate(null);
                    } else {
                        String fechaSeleccionadaM12 = (String)(r.getString(30));
                        DateFormat dfoM12 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM12 = dfoM12.parse(fechaSeleccionadaM12);
                        RSAIET.FET22.setDate(fechaM12);
                        RSAIET.DXET22.setText(r.getString(31));
                        RSAIET.FUAET22.setText(r.getString(32));
                    }
                } catch (Exception e) {
                }
                ///////////////////////////////////////////////////////
                //3 AÑOS
                try {
                    if(r.getString(33).equals("")){
                        RSAIET.FET23.setDate(null);
                    } else {
                        String fechaSeleccionadaM13 = (String)(r.getString(33));
                        DateFormat dfoM13 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaM13 = dfoM13.parse(fechaSeleccionadaM13);
                        RSAIET.FET23.setDate(fechaM13);
                        RSAIET.DXET23.setText(r.getString(34));
                        RSAIET.FUAET23.setText(r.getString(35));
                    }
                } catch (Exception e) {
                }
                
            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
     
    public boolean mantenimientoRSAIET(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_MANTENIMIENTO_RS_ESTIMULACION_TEMPRANA ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRs_id());
            
            //////////////////////////////////////////////
            //MENORES DE UN AÑO
           
            cmd.setString(2, getM1Fecha());
            cmd.setString(3, getM1Cie10());
            cmd.setString(4, getM1Fua());
            
            cmd.setString(5, getM2Fecha());
            cmd.setString(6, getM2Cie10());
            cmd.setString(7, getM2Fua());
            
            cmd.setString(8, getM3Fecha());
            cmd.setString(9, getM3Cie10());
            cmd.setString(10, getM3Fua());
            
            cmd.setString(11, getM4Fecha());
            cmd.setString(12, getM4Cie10());
            cmd.setString(13, getM4Fua());
            
            cmd.setString(14, getM5Fecha());
            cmd.setString(15, getM5Cie10());
            cmd.setString(16, getM5Fua());
            
            cmd.setString(17, getM6Fecha());
            cmd.setString(18, getM6Cie10());
            cmd.setString(19, getM6Fua());
            //////////////////////////////////////////////
            //1 AÑO
            cmd.setString(20, getM7Fecha());
            cmd.setString(21, getM7Cie10());
            cmd.setString(22, getM7Fua());
            
            cmd.setString(23, getM8Fecha());
            cmd.setString(24, getM8Cie10());
            cmd.setString(25, getM8Fua());
            
            cmd.setString(26, getM9Fecha());
            cmd.setString(27, getM9Cie10());
            cmd.setString(28, getM9Fua());
            
            cmd.setString(29, getM10Fecha());
            cmd.setString(30, getM10Cie10());
            cmd.setString(31, getM10Fua());
            /////////////////////////////////////////////////
            //2 AÑOS
            cmd.setString(32, getM11Fecha());
            cmd.setString(33, getM11Cie10());
            cmd.setString(34, getM11Fua());
            
            cmd.setString(35, getM12Fecha());
            cmd.setString(36, getM12Cie10());
            cmd.setString(37, getM12Fua());
            ///////////////////////////////////////////////
            //3 AÑOS
            cmd.setString(38, getM13Fecha());
            cmd.setString(39, getM13Cie10());
            cmd.setString(40, getM13Fua());
            
            cmd.setString(41, tipo);
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
    public void porcentajeET(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_ESTIMULACION_TEMPRANA_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
//                lblPorcentajeET.setText(r.getString(1) + " %"); 
                RSAIET.lblPorcentajeV.setText(r.getString(1) + " %"); 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: porcentajeET " + e.getMessage());
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
    public ConsultorioExtRsEstimulacionTemprana() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtRsEstimulacionTemprana(Long etId) {
        this.etId = etId;
    }

    public Long getEtId() {
        return etId;
    }

    public void setEtId(Long etId) {
        this.etId = etId;
    }

    public String getM1Fecha() {
        return m1Fecha;
    }

    public void setM1Fecha(String m1Fecha) {
        this.m1Fecha = m1Fecha;
    }

    public String getM1Cie10() {
        return m1Cie10;
    }

    public void setM1Cie10(String m1Cie10) {
        this.m1Cie10 = m1Cie10;
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

    public String getM2Cie10() {
        return m2Cie10;
    }

    public void setM2Cie10(String m2Cie10) {
        this.m2Cie10 = m2Cie10;
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

    public String getM3Cie10() {
        return m3Cie10;
    }

    public void setM3Cie10(String m3Cie10) {
        this.m3Cie10 = m3Cie10;
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

    public String getM4Cie10() {
        return m4Cie10;
    }

    public void setM4Cie10(String m4Cie10) {
        this.m4Cie10 = m4Cie10;
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

    public String getM5Cie10() {
        return m5Cie10;
    }

    public void setM5Cie10(String m5Cie10) {
        this.m5Cie10 = m5Cie10;
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

    public String getM6Cie10() {
        return m6Cie10;
    }

    public void setM6Cie10(String m6Cie10) {
        this.m6Cie10 = m6Cie10;
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

    public String getM7Cie10() {
        return m7Cie10;
    }

    public void setM7Cie10(String m7Cie10) {
        this.m7Cie10 = m7Cie10;
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

    public String getM8Cie10() {
        return m8Cie10;
    }

    public void setM8Cie10(String m8Cie10) {
        this.m8Cie10 = m8Cie10;
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

    public String getM9Cie10() {
        return m9Cie10;
    }

    public void setM9Cie10(String m9Cie10) {
        this.m9Cie10 = m9Cie10;
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

    public String getM10Cie10() {
        return m10Cie10;
    }

    public void setM10Cie10(String m10Cie10) {
        this.m10Cie10 = m10Cie10;
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

    public String getM11Cie10() {
        return m11Cie10;
    }

    public void setM11Cie10(String m11Cie10) {
        this.m11Cie10 = m11Cie10;
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

    public String getM12Cie10() {
        return m12Cie10;
    }

    public void setM12Cie10(String m12Cie10) {
        this.m12Cie10 = m12Cie10;
    }

    public String getM12Fua() {
        return m12Fua;
    }

    public void setM12Fua(String m12Fua) {
        this.m12Fua = m12Fua;
    }

    public String getM13Fecha() {
        return m13Fecha;
    }

    public void setM13Fecha(String m13Fecha) {
        this.m13Fecha = m13Fecha;
    }

    public String getM13Cie10() {
        return m13Cie10;
    }

    public void setM13Cie10(String m13Cie10) {
        this.m13Cie10 = m13Cie10;
    }

    public String getM13Fua() {
        return m13Fua;
    }

    public void setM13Fua(String m13Fua) {
        this.m13Fua = m13Fua;
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
        hash += (etId != null ? etId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultorioExtRsEstimulacionTemprana)) {
            return false;
        }
        ConsultorioExtRsEstimulacionTemprana other = (ConsultorioExtRsEstimulacionTemprana) object;
        if ((this.etId == null && other.etId != null) || (this.etId != null && !this.etId.equals(other.etId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsEstimulacionTemprana[ etId=" + etId + " ]";
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
