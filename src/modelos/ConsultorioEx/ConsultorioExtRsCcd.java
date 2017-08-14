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
import vista.CRED.RSAICCD;
import static vista.CRED.RegistroSeguimiento.lblPorcentajeCCD;

public class ConsultorioExtRsCcd implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int RsId;
    private int rsCcd;
    //RECIEN NACIDO
    private String rn1Fecha;
    private String rn1Cie10;
    private String rn1Fua;
    private String rn2Fecha;
    private String rn2Cie10;
    private String rn2Fua;
    private String rn3Fecha;
    private String rn3Cie10;
    private String rn3Fua;
    private String rn4Fecha;
    private String rn4Cie10;
    //MENORES DE UN AÑO 
    private String rn4Fua;
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
    //1AÑO
    private String m11Fecha1;
    private String m11Cie101;
    private String m11Fua1;
    private String m12Fecha;
    private String m12Cie10;
    private String m12Fua;
    private String m13Fecha;
    private String m13Cie10;
    private String m13Fua;
    private String m14Fecha;
    private String m14Cie10;
    private String m14Fua;
    private String m15Fecha;
    private String m15Cie10;
    private String m15Fua;
    private String m16Fecha;
    private String m16Cie10;
    private String m16Fua;
    //2 AÑOS
    private String m21Fecha;
    private String m21Cie10;
    private String m21Fua;
    private String m22Fecha;
    private String m22Cie10;
    private String m22Fua;
    private String m23Fecha;
    private String m23Cie10;
    private String m23Fua;
    private String m24Fecha;
    private String m24Cie10;
    private String m24Fua;
    //3 AÑOS
    private String m31Fecha;
    private String m31Cie10;
    private String m31Fua;
    private String m32Fecha;
    private String m32Cie10;
    private String m32Fua;
    private String m33Fecha;
    private String m33Cie10;
    private String m33Fua;
    private String m34Fecha;
    private String m34Cie10;
    private String m34Fua;
    //4 AÑOS
    private String m41Fecha;
    private String m41Cie10;
    private String m41Fua;
    private String m42Fecha;
    private String m42Cie10;
    private String m42Fua;
    private String m43Fecha;
    private String m43Cie10;
    private String m43Fua;
    private String m44Fecha;
    private String m44Cie10;
    private String m44Fua;
    // 5 AÑOS
    private String m5Fecha1;
    private String m5Cie101;
    private String m5Fua1;
    
    private String m6Fecha1;
    private String m6Cie101;
    private String m6Fua1;
    private String m7Fecha1;
    private String m7Cie101;
    private String m7Fua1;
    private String m8Fecha1;
    private String m8Cie101;
    private String m8Fua1;
    private String m9Fecha1;
    private String m9Cie101;
    private String m9Fua1;
    private String m10Fecha1;
    private String m10Cie101;
    private String m10Fua1;
    private String m11Fecha2;
    private String m11Cie102;
    private String m11Fua2;
    
    public void ConsultoriosExtCCDListar(int rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_RS_CCD_LISTAR ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){  
                //RECIEN NACIDO
                try {
                    if(r.getString(3).equals("")){
                            RSAICCD.FCCDRN1.setDate(null);
                    } else {
                
                            String fechaSeleccionada = (String)(r.getString(3));
                            DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha = dfo.parse(fechaSeleccionada);
                            RSAICCD.FCCDRN1.setDate(fecha);
                            RSAICCD.DXCCDRN1.setText(r.getString(4));
                            RSAICCD.FUACCDRN1.setText(r.getString(5));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(6).equals("")){
                            RSAICCD.FCCDRN2.setDate(null);
                    } else {
                
                            String fechaSeleccionada2 = (String)(r.getString(6));
                            DateFormat dfo2 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha2 = dfo2.parse(fechaSeleccionada2);
                            RSAICCD.FCCDRN2.setDate(fecha2);
                            RSAICCD.DXCCDRN2.setText(r.getString(7));
                            RSAICCD.FUACCDRN2.setText(r.getString(8));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(9).equals("")){
                            RSAICCD.FCCDRN3.setDate(null);
                    } else {
                
                            String fechaSeleccionada3 = (String)(r.getString(9));
                            DateFormat dfo3 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha3 = dfo3.parse(fechaSeleccionada3);
                            RSAICCD.FCCDRN3.setDate(fecha3);
                            RSAICCD.DXCCDRN3.setText(r.getString(10));
                            RSAICCD.FUACCDRN3.setText(r.getString(11));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(12).equals("")){
                            RSAICCD.FCCDRN4.setDate(null);
                    } else {
                
                            String fechaSeleccionada4 = (String)(r.getString(12));
                            DateFormat dfo4 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fecha4 = dfo4.parse(fechaSeleccionada4);
                            RSAICCD.FCCDRN4.setDate(fecha4);
                            RSAICCD.DXCCDRN4.setText(r.getString(13));
                            RSAICCD.FUACCDRN4.setText(r.getString(14));
                    }
                } catch (Exception e) {
                }
                
                //MENORES DE UN AÑO
                
                try {
                    if(r.getString(15).equals("")){
                            RSAICCD.FCCDM1.setDate(null);
                    } else {
                
                            String fechaSellecionadaM1 = (String)(r.getString(15));
                            DateFormat dfoM1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM1 = dfoM1.parse(fechaSellecionadaM1);
                            RSAICCD.FCCDM1.setDate(fechaM1);
                            RSAICCD.DXCCDM1.setText(r.getString(16));
                            RSAICCD.FUACCDM1.setText(r.getString(17));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(18).equals("")){
                            RSAICCD.FCCDM2.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM2 = (String)(r.getString(18));
                            DateFormat dfoM2 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM2 = dfoM2.parse(fechaSeleccionadaM2);
                            RSAICCD.FCCDM2.setDate(fechaM2);
                            RSAICCD.DXCCDM2.setText(r.getString(19));
                            RSAICCD.FUACCDM2.setText(r.getString(20));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(21).equals("")){
                            RSAICCD.FCCDM3.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM3 = (String)(r.getString(21));
                            DateFormat dfoM3 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM3 = dfoM3.parse(fechaSeleccionadaM3);
                            RSAICCD.FCCDM3.setDate(fechaM3);
                            RSAICCD.DXCCDM3.setText(r.getString(22));
                            RSAICCD.FUACCDM3.setText(r.getString(23));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(24).equals("")){
                            RSAICCD.FCCDM4.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM4 = (String)(r.getString(24));
                            DateFormat dfoM4 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM4 = dfoM4.parse(fechaSeleccionadaM4);
                            RSAICCD.FCCDM4.setDate(fechaM4);
                            RSAICCD.DXCCDM4.setText(r.getString(25));
                            RSAICCD.FUACCDM4.setText(r.getString(26));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(27).equals("")){
                            RSAICCD.FCCDM5.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM5 = (String)(r.getString(27));
                            DateFormat dfoM5 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM5 = dfoM5.parse(fechaSeleccionadaM5);
                            RSAICCD.FCCDM5.setDate(fechaM5);
                            RSAICCD.DXCCDM5.setText(r.getString(28));
                            RSAICCD.FUACCDM5.setText(r.getString(29));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(30).equals("")){
                            RSAICCD.FCCDM6.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM6 = (String)(r.getString(30));
                            DateFormat dfoM6 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM6 = dfoM6.parse(fechaSeleccionadaM6);
                            RSAICCD.FCCDM6.setDate(fechaM6);
                            RSAICCD.DXCCDM6.setText(r.getString(31));
                            RSAICCD.FUACCDM6.setText(r.getString(32));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(33).equals("")){
                            RSAICCD.FCCDM7.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM7 = (String)(r.getString(33));
                            DateFormat dfoM7 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM7 = dfoM7.parse(fechaSeleccionadaM7);
                            RSAICCD.FCCDM7.setDate(fechaM7);
                            RSAICCD.DXCCDM7.setText(r.getString(34));
                            RSAICCD.FUACCDM7.setText(r.getString(35));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(36).equals("")){
                            RSAICCD.FCCDM8.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM8 = (String)(r.getString(36));
                            DateFormat dfoM8 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM8 = dfoM8.parse(fechaSeleccionadaM8);
                            RSAICCD.FCCDM8.setDate(fechaM8);
                            RSAICCD.DXCCDM8.setText(r.getString(37));
                            RSAICCD.FUACCDM8.setText(r.getString(38));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(39).equals("")){
                            RSAICCD.FCCDM9.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM9 = (String)(r.getString(39));
                            DateFormat dfoM9 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM9 = dfoM9.parse(fechaSeleccionadaM9);
                            RSAICCD.FCCDM9.setDate(fechaM9);
                            RSAICCD.DXCCDM9.setText(r.getString(40));
                            RSAICCD.FUACCDM9.setText(r.getString(41));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(42).equals("")){
                            RSAICCD.FCCDM10.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM10 = (String)(r.getString(42));
                            DateFormat dfoM10 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM10 = dfoM10.parse(fechaSeleccionadaM10);
                            RSAICCD.FCCDM10.setDate(fechaM10);
                            RSAICCD.DXCCDM10.setText(r.getString(43));
                            RSAICCD.FUACCDM10.setText(r.getString(44));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(45).equals("")){
                            RSAICCD.FCCDM11.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM11 = (String)(r.getString(45));
                            DateFormat dfoM11 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM11 = dfoM11.parse(fechaSeleccionadaM11);
                            RSAICCD.FCCDM11.setDate(fechaM11);
                            RSAICCD.DXCCDM11.setText(r.getString(46));
                            RSAICCD.FUACCDM11.setText(r.getString(47));
                    }
                } catch (Exception e) {
                }
                
                //1 AÑO
                try {
                    if(r.getString(48).equals("")){
                            RSAICCD.FCCD11.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM1A = (String)(r.getString(48));
                            DateFormat dfoM1A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM1A = dfoM1A.parse(fechaSeleccionadaM1A);
                            RSAICCD.FCCD11.setDate(fechaM1A);
                            RSAICCD.DXCCD11.setText(r.getString(49));
                            RSAICCD.FUACCD11.setText(r.getString(50));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(51).equals("")){
                            RSAICCD.FCCD12.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM12A = (String)(r.getString(51));
                            DateFormat dfoM12A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM12A = dfoM12A.parse(fechaSeleccionadaM12A);
                            RSAICCD.FCCD12.setDate(fechaM12A);
                            RSAICCD.DXCCD12.setText(r.getString(52));
                            RSAICCD.FUACCD12.setText(r.getString(53));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(54).equals("")){
                            RSAICCD.FCCD13.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM13A = (String)(r.getString(54));
                            DateFormat dfoM13A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM13A = dfoM13A.parse(fechaSeleccionadaM13A);
                            RSAICCD.FCCD13.setDate(fechaM13A);
                            RSAICCD.DXCCD13.setText(r.getString(55));
                            RSAICCD.FUACCD13.setText(r.getString(56));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(57).equals("")){
                            RSAICCD.FCCD14.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM14A = (String)(r.getString(57));
                            DateFormat dfoM14A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM14A = dfoM14A.parse(fechaSeleccionadaM14A);
                            RSAICCD.FCCD14.setDate(fechaM14A);
                            RSAICCD.DXCCD14.setText(r.getString(58));
                            RSAICCD.FUACCD14.setText(r.getString(59));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(60).equals("")){
                            RSAICCD.FCCD15.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM15A = (String)(r.getString(60));
                            DateFormat dfoM15A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM15A = dfoM15A.parse(fechaSeleccionadaM15A);
                            RSAICCD.FCCD15.setDate(fechaM15A);
                            RSAICCD.DXCCD15.setText(r.getString(61));
                            RSAICCD.FUACCD15.setText(r.getString(62));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(63).equals("")){
                            RSAICCD.FCCD16.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM16A = (String)(r.getString(63));
                            DateFormat dfoM16A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM16A = dfoM16A.parse(fechaSeleccionadaM16A);
                            RSAICCD.FCCD16.setDate(fechaM16A);
                            RSAICCD.DXCCD16.setText(r.getString(64));
                            RSAICCD.FUACCD16.setText(r.getString(65));
                    }
                } catch (Exception e) {
                }
                
                //2 AÑOS
                try {
                    if(r.getString(66).equals("")){
                            RSAICCD.FCCD21.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM21A = (String)(r.getString(66));
                            DateFormat dfoM21A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM21A = dfoM21A.parse(fechaSeleccionadaM21A);
                            RSAICCD.FCCD21.setDate(fechaM21A);
                            RSAICCD.DXCCD21.setText(r.getString(67));
                            RSAICCD.FUACCD21.setText(r.getString(68));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(69).equals("")){
                            RSAICCD.FCCD22.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM22A = (String)(r.getString(69));
                            DateFormat dfoM22A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM22A = dfoM22A.parse(fechaSeleccionadaM22A);
                            RSAICCD.FCCD22.setDate(fechaM22A);
                            RSAICCD.DXCCD22.setText(r.getString(70));
                            RSAICCD.FUACCD22.setText(r.getString(71));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(72).equals("")){
                            RSAICCD.FCCD23.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM23A = (String)(r.getString(72));
                            DateFormat dfoM23A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM23A = dfoM23A.parse(fechaSeleccionadaM23A);
                            RSAICCD.FCCD23.setDate(fechaM23A);
                            RSAICCD.DXCCD23.setText(r.getString(73));
                            RSAICCD.FUACCD23.setText(r.getString(74));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(75).equals("")){
                            RSAICCD.FCCD24.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM24A = (String)(r.getString(75));
                            DateFormat dfoM24A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM24A = dfoM24A.parse(fechaSeleccionadaM24A);
                            RSAICCD.FCCD24.setDate(fechaM24A);
                            RSAICCD.DXCCD24.setText(r.getString(76));
                            RSAICCD.FUACCD24.setText(r.getString(77));
                    }
                } catch (Exception e) {
                }
                
                // 3 AÑOS
                try {
                    if(r.getString(78).equals("")){
                            RSAICCD.FCCD3A1.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM3A1 = (String)(r.getString(78));
                            DateFormat dfoM3A1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM3A1 = dfoM3A1.parse(fechaSeleccionadaM3A1);
                            RSAICCD.FCCD3A1.setDate(fechaM3A1);
                            RSAICCD.DXCCD3A1.setText(r.getString(79));
                            RSAICCD.FUACCD3A1.setText(r.getString(80));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(81).equals("")){
                            RSAICCD.FCCD3A2.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM3A2 = (String)(r.getString(81));
                            DateFormat dfoM3A2 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM3A2 = dfoM3A2.parse(fechaSeleccionadaM3A2);
                            RSAICCD.FCCD3A2.setDate(fechaM3A2);
                            RSAICCD.DXCCD3A2.setText(r.getString(82));
                            RSAICCD.FUACCD3A2.setText(r.getString(83));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(84).equals("")){
                            RSAICCD.FCCD3A3.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM3A3 = (String)(r.getString(84));
                            DateFormat dfoM3A3 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM3A3 = dfoM3A3.parse(fechaSeleccionadaM3A3);
                            RSAICCD.FCCD3A3.setDate(fechaM3A3);
                            RSAICCD.DXCCD3A3.setText(r.getString(85));
                            RSAICCD.FUACCD3A3.setText(r.getString(86));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(87).equals("")){
                            RSAICCD.FCCD3A4.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM3A4 = (String)(r.getString(87));
                            DateFormat dfoM3A4 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM3A4 = dfoM3A4.parse(fechaSeleccionadaM3A4);
                            RSAICCD.FCCD3A4.setDate(fechaM3A4);
                            RSAICCD.DXCCD3A4.setText(r.getString(88));
                            RSAICCD.FUACCD3A4.setText(r.getString(89));
                    }
                } catch (Exception e) {
                }
                
                //4 AÑOS
                
                try {
                    if(r.getString(90).equals("")){
                            RSAICCD.FCCD4A1.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM4A1 = (String)(r.getString(90));
                            DateFormat dfoM34A1 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM4A1 = dfoM34A1.parse(fechaSeleccionadaM4A1);
                            RSAICCD.FCCD4A1.setDate(fechaM4A1);
                            RSAICCD.DXCCD4A1.setText(r.getString(91));
                            RSAICCD.FUACCD4A1.setText(r.getString(92));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(93).equals("")){
                            RSAICCD.FCCD4A2.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM4A2 = (String)(r.getString(93));
                            DateFormat dfoM4A2 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM4A2 = dfoM4A2.parse(fechaSeleccionadaM4A2);
                            RSAICCD.FCCD4A2.setDate(fechaM4A2);
                            RSAICCD.DXCCD4A2.setText(r.getString(94));
                            RSAICCD.FUACCD4A2.setText(r.getString(95));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(96).equals("")){
                            RSAICCD.FCCD3A3.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM4A3 = (String)(r.getString(96));
                            DateFormat dfoM4A3 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM4A3 = dfoM4A3.parse(fechaSeleccionadaM4A3);
                            RSAICCD.FCCD4A3.setDate(fechaM4A3);
                            RSAICCD.DXCCD4A3.setText(r.getString(97));
                            RSAICCD.FUACCD4A3.setText(r.getString(98));
                    }
                } catch (Exception e) {
                }
                
                try {
                    if(r.getString(99).equals("")){
                            RSAICCD.FCCD4A4.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM4A4 = (String)(r.getString(99));
                            DateFormat dfoM4A4 = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM4A4 = dfoM4A4.parse(fechaSeleccionadaM4A4);
                            RSAICCD.FCCD4A4.setDate(fechaM4A4);
                            RSAICCD.DXCCD4A4.setText(r.getString(100));
                            RSAICCD.FUACCD4A4.setText(r.getString(101));
                    }
                } catch (Exception e) {
                }
                
                //5 AÑOS
                try {
                    if(r.getString(102).equals("")){
                            RSAICCD.FCCD5A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM5A = (String)(r.getString(102));
                            DateFormat dfoM5A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM5A = dfoM5A.parse(fechaSeleccionadaM5A);
                            RSAICCD.FCCD5A.setDate(fechaM5A);
                            RSAICCD.DXCCD5A.setText(r.getString(103));
                            RSAICCD.FUACCD5A.setText(r.getString(104));
                    }
                } catch (Exception e) {
                }
                
                //6 AÑOS
                try {
                    if(r.getString(105).equals("")){
                            RSAICCD.FCCD6A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM6A = (String)(r.getString(105));
                            DateFormat dfoM6A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM6A = dfoM6A.parse(fechaSeleccionadaM6A);
                            RSAICCD.FCCD6A.setDate(fechaM6A);
                            RSAICCD.DXCCD6A.setText(r.getString(106));
                            RSAICCD.FUACCD6A.setText(r.getString(107));
                    }
                } catch (Exception e) {
                }
                
                //7 AÑOS
                try {
                    if(r.getString(108).equals("")){
                            RSAICCD.FCCD7A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM7A = (String)(r.getString(108));
                            DateFormat dfoM7A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM7A = dfoM7A.parse(fechaSeleccionadaM7A);
                            RSAICCD.FCCD7A.setDate(fechaM7A);
                            RSAICCD.DXCCD7A.setText(r.getString(109));
                            RSAICCD.FUACCD7A.setText(r.getString(110));
                    }
                } catch (Exception e) {
                }
                
                //8 AÑOS
                try {
                    if(r.getString(111).equals("")){
                            RSAICCD.FCCD8A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM8A = (String)(r.getString(111));
                            DateFormat dfoM8A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM8A = dfoM8A.parse(fechaSeleccionadaM8A);
                            RSAICCD.FCCD8A.setDate(fechaM8A);
                            RSAICCD.DXCCD8A.setText(r.getString(112));
                            RSAICCD.FUACCD8A.setText(r.getString(113));
                    }
                } catch (Exception e) {
                }
                
                //9 AÑOS
                try {
                    if(r.getString(114).equals("")){
                            RSAICCD.FCCD9A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM9A = (String)(r.getString(114));
                            DateFormat dfoM9A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM9A = dfoM9A.parse(fechaSeleccionadaM9A);
                            RSAICCD.FCCD9A.setDate(fechaM9A);
                            RSAICCD.DXCCD9A.setText(r.getString(115));
                            RSAICCD.FUACCD9A.setText(r.getString(116));
                    }
                } catch (Exception e) {
                }
                
                //10 AÑOS
                try {
                    if(r.getString(117).equals("")){
                            RSAICCD.FCCD10A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM10A = (String)(r.getString(117));
                            DateFormat dfoM10A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM10A = dfoM10A.parse(fechaSeleccionadaM10A);
                            RSAICCD.FCCD10A.setDate(fechaM10A);
                            RSAICCD.DXCCD10A.setText(r.getString(115));
                            RSAICCD.FUACCD10A.setText(r.getString(116));
                    }
                } catch (Exception e) {
                }
                
                //10 AÑOS
                try {
                    if(r.getString(118).equals("")){
                            RSAICCD.FCCD11A.setDate(null);
                    } else {
                
                            String fechaSeleccionadaM11A = (String)(r.getString(118));
                            DateFormat dfoM11A = new SimpleDateFormat("dd/MM/yyyy");
                            Date fechaM11A = dfoM11A.parse(fechaSeleccionadaM11A);
                            RSAICCD.FCCD11A.setDate(fechaM11A);
                            RSAICCD.DXCCD11A.setText(r.getString(119));
                            RSAICCD.FUACCD11A.setText(r.getString(120));
                    }
                } catch (Exception e) {
                }
              
                

            }
            //
        } catch (Exception e) {
            System.out.println("Error: ConsultoriosExtVacunasListar " + e.getMessage());
        }
    }
    
    public boolean mantenimientoRSAICCD(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "EXEC CONSULTORIO_EXT_RS_CCD_MANTENIMIENTO ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getRsId());
            //RECIEN NACIDO
            cmd.setString(2, getRn1Fecha());
            cmd.setString(3, getRn1Cie10());            
            cmd.setString(4, getRn1Fua());
            cmd.setString(5, getRn2Fecha()); 
            cmd.setString(6, getRn2Cie10());
            cmd.setString(7, getRn2Fua());    
            cmd.setString(8, getRn3Fecha());
            cmd.setString(9, getRn3Cie10());    
            cmd.setString(10, getRn3Fua());
            cmd.setString(11, getRn4Fecha());     
            cmd.setString(12, getRn4Cie10());
            cmd.setString(13, getRn4Fua());
            
            //MENORES DE UN AÑO
            cmd.setString(14, getM1Fecha());
            cmd.setString(15, getM1Cie10());
            cmd.setString(16, getM1Fua());
            
            cmd.setString(17, getM2Fecha());          
            cmd.setString(18, getM2Cie10());
            cmd.setString(19, getM2Fua());
            
            cmd.setString(20, getM3Fecha());
            cmd.setString(21, getM3Cie10());           
            cmd.setString(22, getM3Fua());
            
            cmd.setString(23, getM4Fecha());           
            cmd.setString(24, getM4Cie10());
            cmd.setString(25, getM4Fua());
            
            cmd.setString(26, getM5Fecha());
            cmd.setString(27, getM5Cie10());          
            cmd.setString(28, getM5Fua());
            
            cmd.setString(29, getM6Fecha());           
            cmd.setString(30, getM6Cie10());
            cmd.setString(31, getM6Fua());
            
            cmd.setString(32, getM7Fecha());           
            cmd.setString(33, getM7Cie10());           
            cmd.setString(34, getM7Fua());
            
            cmd.setString(35, getM8Fecha());           
            cmd.setString(36, getM8Cie10());
            cmd.setString(37, getM8Fua());
            
            cmd.setString(38, getM9Fecha());
            cmd.setString(39, getM9Cie10());           
            cmd.setString(40, getM9Fua());
            
            cmd.setString(41, getM10Fecha());           
            cmd.setString(42, getM10Cie10()); 
            cmd.setString(43, getM10Fua()); 
            
            cmd.setString(44, getM11Fecha());
            cmd.setString(45, getM11Cie10());
            cmd.setString(46, getM11Fua());
            
            //1 AÑO
            cmd.setString(47, getM11Fecha1());
            cmd.setString(48, getM11Cie101());
            cmd.setString(49, getM11Fua1());
            
            cmd.setString(50, getM12Fecha());
            cmd.setString(51, getM12Cie10());
            cmd.setString(52, getM12Fua());
            
            cmd.setString(53, getM13Fecha()); 
            cmd.setString(54, getM13Cie10());
            cmd.setString(55, getM13Fua());
            
            cmd.setString(56, getM14Fecha());
            cmd.setString(57, getM14Cie10());
            cmd.setString(58, getM14Fua());
            
            cmd.setString(59, getM15Fecha());
            cmd.setString(60, getM15Cie10());
            cmd.setString(61, getM15Fua());
            
            cmd.setString(62, getM16Fecha()); 
            cmd.setString(63, getM16Cie10());
            cmd.setString(64, getM16Fua());
            
            //2 AÑOS
            cmd.setString(65, getM21Fecha());
            cmd.setString(66, getM21Cie10());
            cmd.setString(67, getM21Fua());
            
            cmd.setString(68, getM22Fecha());
            cmd.setString(69, getM22Cie10());
            cmd.setString(70, getM22Fua());
            
            cmd.setString(71, getM23Fecha()); 
            cmd.setString(72, getM23Cie10());
            cmd.setString(73, getM23Fua());
            
            cmd.setString(74, getM24Fecha()); 
            cmd.setString(75, getM24Cie10());
            cmd.setString(76, getM24Fua());
            
           //3 AÑOS
            cmd.setString(77, getM31Fecha());
            cmd.setString(78, getM31Cie10());
            cmd.setString(79, getM31Fua());
            
            cmd.setString(80, getM32Fecha());
            cmd.setString(81, getM32Cie10());
            cmd.setString(82, getM32Fua());
            
            cmd.setString(83, getM33Fecha()); 
            cmd.setString(84, getM33Cie10());
            cmd.setString(85, getM33Fua());
            
            cmd.setString(86, getM34Fecha()); 
            cmd.setString(87, getM34Cie10());
            cmd.setString(88, getM34Fua());
            
            //4 AÑOS
            cmd.setString(89, getM41Fecha());
            cmd.setString(90, getM41Cie10());
            cmd.setString(91, getM41Fua());
            
            cmd.setString(92, getM42Fecha());
            cmd.setString(93, getM42Cie10());
            cmd.setString(94, getM42Fua());
            
            cmd.setString(95, getM43Fecha()); 
            cmd.setString(96, getM43Cie10());
            cmd.setString(97, getM43Fua());
            
            cmd.setString(98, getM44Fecha()); 
            cmd.setString(99, getM44Cie10());
            cmd.setString(100,getM44Fua());
            
            //5 AÑOS
            cmd.setString(101, getM5Fecha1());
            cmd.setString(102, getM5Cie101());
            cmd.setString(103, getM5Fua1());
            
            //6 AÑOS
            cmd.setString(104, getM6Fecha1());
            cmd.setString(105, getM6Cie101());
            cmd.setString(106, getM6Fua1());
            
            //7 AÑOS
            cmd.setString(107, getM7Fecha1());
            cmd.setString(108, getM7Cie101());
            cmd.setString(109, getM7Fua1());
            
            //8 AÑOS
            cmd.setString(110, getM8Fecha1());
            cmd.setString(111, getM8Cie101());
            cmd.setString(112, getM8Fua1());
            
            //9 AÑOS
            cmd.setString(113, getM9Fecha1());
            cmd.setString(114, getM9Cie101());
            cmd.setString(115, getM9Fua1());
            
            //10 AÑOS
            cmd.setString(116, getM10Fecha1());
            cmd.setString(117, getM10Cie101());
            cmd.setString(118, getM10Fua1());
            
            //11 AÑOS
            cmd.setString(119, getM11Fecha2());
            cmd.setString(120, getM11Cie102());
            cmd.setString(121, getM11Fua2());
            
            cmd.setString(122, tipo);
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
            getCn().close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimiento CCD " + ex.getMessage());
        }
        return resp;
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
        tabla.setRowHeight(45);
    }

    public void porcentajeCCD(int rs_id){
        String consulta="";
        try {
            consulta="EXEC CONSULTORIO_EXT_RS_CCD_PORCENTAJE ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setInt(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblPorcentajeCCD.setText(r.getString(1) + " %"); 
                RSAICCD.lblPorcentajeV.setText(r.getString(1) + " % Completado"); 
                RSAICCD.lblPorcentajeV1.setText(r.getString(1) + " % Completado"); 
                RSAICCD.lblPorcentajeV2.setText(r.getString(1) + " % Completado"); 
            }
            //
        } catch (Exception e) {
        }
    }
     
    public ConsultorioExtRsCcd() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public int getRsId() {
        return RsId;
    }

    public void setRsId(int RsId) {
        this.RsId = RsId;
    }

    public ConsultorioExtRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public int getRsCcd() {
        return rsCcd;
    }

    public void setRsCcd(int rsCcd) {
        this.rsCcd = rsCcd;
    }

    public String getRn1Fecha() {
        return rn1Fecha;
    }

    public void setRn1Fecha(String rn1Fecha) {
        this.rn1Fecha = rn1Fecha;
    }

    public String getRn1Cie10() {
        return rn1Cie10;
    }

    public void setRn1Cie10(String rn1Cie10) {
        this.rn1Cie10 = rn1Cie10;
    }

    public String getRn1Fua() {
        return rn1Fua;
    }

    public void setRn1Fua(String rn1Fua) {
        this.rn1Fua = rn1Fua;
    }

    public String getRn2Fecha() {
        return rn2Fecha;
    }

    public void setRn2Fecha(String rn2Fecha) {
        this.rn2Fecha = rn2Fecha;
    }

    public String getRn2Cie10() {
        return rn2Cie10;
    }

    public void setRn2Cie10(String rn2Cie10) {
        this.rn2Cie10 = rn2Cie10;
    }

    public String getRn2Fua() {
        return rn2Fua;
    }

    public void setRn2Fua(String rn2Fua) {
        this.rn2Fua = rn2Fua;
    }

    public String getRn3Fecha() {
        return rn3Fecha;
    }

    public void setRn3Fecha(String rn3Fecha) {
        this.rn3Fecha = rn3Fecha;
    }

    public String getRn3Cie10() {
        return rn3Cie10;
    }

    public void setRn3Cie10(String rn3Cie10) {
        this.rn3Cie10 = rn3Cie10;
    }

    public String getRn3Fua() {
        return rn3Fua;
    }

    public void setRn3Fua(String rn3Fua) {
        this.rn3Fua = rn3Fua;
    }

    public String getRn4Fecha() {
        return rn4Fecha;
    }

    public void setRn4Fecha(String rn4Fecha) {
        this.rn4Fecha = rn4Fecha;
    }

    public String getRn4Cie10() {
        return rn4Cie10;
    }

    public void setRn4Cie10(String rn4Cie10) {
        this.rn4Cie10 = rn4Cie10;
    }

    public String getRn4Fua() {
        return rn4Fua;
    }

    public void setRn4Fua(String rn4Fua) {
        this.rn4Fua = rn4Fua;
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

    public String getM11Fecha1() {
        return m11Fecha1;
    }

    public void setM11Fecha1(String m11Fecha1) {
        this.m11Fecha1 = m11Fecha1;
    }

    public String getM11Cie101() {
        return m11Cie101;
    }

    public void setM11Cie101(String m11Cie101) {
        this.m11Cie101 = m11Cie101;
    }

    public String getM11Fua1() {
        return m11Fua1;
    }

    public void setM11Fua1(String m11Fua1) {
        this.m11Fua1 = m11Fua1;
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

    public String getM14Fecha() {
        return m14Fecha;
    }

    public void setM14Fecha(String m14Fecha) {
        this.m14Fecha = m14Fecha;
    }

    public String getM14Cie10() {
        return m14Cie10;
    }

    public void setM14Cie10(String m14Cie10) {
        this.m14Cie10 = m14Cie10;
    }

    public String getM14Fua() {
        return m14Fua;
    }

    public void setM14Fua(String m14Fua) {
        this.m14Fua = m14Fua;
    }

    public String getM15Fecha() {
        return m15Fecha;
    }

    public void setM15Fecha(String m15Fecha) {
        this.m15Fecha = m15Fecha;
    }

    public String getM15Cie10() {
        return m15Cie10;
    }

    public void setM15Cie10(String m15Cie10) {
        this.m15Cie10 = m15Cie10;
    }

    public String getM15Fua() {
        return m15Fua;
    }

    public void setM15Fua(String m15Fua) {
        this.m15Fua = m15Fua;
    }

    public String getM16Fecha() {
        return m16Fecha;
    }

    public void setM16Fecha(String m16Fecha) {
        this.m16Fecha = m16Fecha;
    }

    public String getM16Cie10() {
        return m16Cie10;
    }

    public void setM16Cie10(String m16Cie10) {
        this.m16Cie10 = m16Cie10;
    }

    public String getM16Fua() {
        return m16Fua;
    }

    public void setM16Fua(String m16Fua) {
        this.m16Fua = m16Fua;
    }

    public String getM21Fecha() {
        return m21Fecha;
    }

    public void setM21Fecha(String m21Fecha) {
        this.m21Fecha = m21Fecha;
    }

    public String getM21Cie10() {
        return m21Cie10;
    }

    public void setM21Cie10(String m21Cie10) {
        this.m21Cie10 = m21Cie10;
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

    public String getM22Cie10() {
        return m22Cie10;
    }

    public void setM22Cie10(String m22Cie10) {
        this.m22Cie10 = m22Cie10;
    }

    public String getM22Fua() {
        return m22Fua;
    }

    public void setM22Fua(String m22Fua) {
        this.m22Fua = m22Fua;
    }

    public String getM23Fecha() {
        return m23Fecha;
    }

    public void setM23Fecha(String m23Fecha) {
        this.m23Fecha = m23Fecha;
    }

    public String getM23Cie10() {
        return m23Cie10;
    }

    public void setM23Cie10(String m23Cie10) {
        this.m23Cie10 = m23Cie10;
    }

    public String getM23Fua() {
        return m23Fua;
    }

    public void setM23Fua(String m23Fua) {
        this.m23Fua = m23Fua;
    }

    public String getM24Fecha() {
        return m24Fecha;
    }

    public void setM24Fecha(String m24Fecha) {
        this.m24Fecha = m24Fecha;
    }

    public String getM24Cie10() {
        return m24Cie10;
    }

    public void setM24Cie10(String m24Cie10) {
        this.m24Cie10 = m24Cie10;
    }

    public String getM24Fua() {
        return m24Fua;
    }

    public void setM24Fua(String m24Fua) {
        this.m24Fua = m24Fua;
    }

    public String getM31Fecha() {
        return m31Fecha;
    }

    public void setM31Fecha(String m31Fecha) {
        this.m31Fecha = m31Fecha;
    }

    public String getM31Cie10() {
        return m31Cie10;
    }

    public void setM31Cie10(String m31Cie10) {
        this.m31Cie10 = m31Cie10;
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

    public String getM32Cie10() {
        return m32Cie10;
    }

    public void setM32Cie10(String m32Cie10) {
        this.m32Cie10 = m32Cie10;
    }

    public String getM32Fua() {
        return m32Fua;
    }

    public void setM32Fua(String m32Fua) {
        this.m32Fua = m32Fua;
    }

    public String getM33Fecha() {
        return m33Fecha;
    }

    public void setM33Fecha(String m33Fecha) {
        this.m33Fecha = m33Fecha;
    }

    public String getM33Cie10() {
        return m33Cie10;
    }

    public void setM33Cie10(String m33Cie10) {
        this.m33Cie10 = m33Cie10;
    }

    public String getM33Fua() {
        return m33Fua;
    }

    public void setM33Fua(String m33Fua) {
        this.m33Fua = m33Fua;
    }

    public String getM34Fecha() {
        return m34Fecha;
    }

    public void setM34Fecha(String m34Fecha) {
        this.m34Fecha = m34Fecha;
    }

    public String getM34Cie10() {
        return m34Cie10;
    }

    public void setM34Cie10(String m34Cie10) {
        this.m34Cie10 = m34Cie10;
    }

    public String getM34Fua() {
        return m34Fua;
    }

    public void setM34Fua(String m34Fua) {
        this.m34Fua = m34Fua;
    }

    public String getM41Fecha() {
        return m41Fecha;
    }

    public void setM41Fecha(String m41Fecha) {
        this.m41Fecha = m41Fecha;
    }

    public String getM41Cie10() {
        return m41Cie10;
    }

    public void setM41Cie10(String m41Cie10) {
        this.m41Cie10 = m41Cie10;
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

    public String getM42Cie10() {
        return m42Cie10;
    }

    public void setM42Cie10(String m42Cie10) {
        this.m42Cie10 = m42Cie10;
    }

    public String getM42Fua() {
        return m42Fua;
    }

    public void setM42Fua(String m42Fua) {
        this.m42Fua = m42Fua;
    }

    public String getM43Fecha() {
        return m43Fecha;
    }

    public void setM43Fecha(String m43Fecha) {
        this.m43Fecha = m43Fecha;
    }

    public String getM43Cie10() {
        return m43Cie10;
    }

    public void setM43Cie10(String m43Cie10) {
        this.m43Cie10 = m43Cie10;
    }

    public String getM43Fua() {
        return m43Fua;
    }

    public void setM43Fua(String m43Fua) {
        this.m43Fua = m43Fua;
    }

    public String getM44Fecha() {
        return m44Fecha;
    }

    public void setM44Fecha(String m44Fecha) {
        this.m44Fecha = m44Fecha;
    }

    public String getM44Cie10() {
        return m44Cie10;
    }

    public void setM44Cie10(String m44Cie10) {
        this.m44Cie10 = m44Cie10;
    }

    public String getM44Fua() {
        return m44Fua;
    }

    public void setM44Fua(String m44Fua) {
        this.m44Fua = m44Fua;
    }

    public String getM5Fecha1() {
        return m5Fecha1;
    }

    public void setM5Fecha1(String m5Fecha1) {
        this.m5Fecha1 = m5Fecha1;
    }

    public String getM5Cie101() {
        return m5Cie101;
    }

    public void setM5Cie101(String m5Cie101) {
        this.m5Cie101 = m5Cie101;
    }

    public String getM5Fua1() {
        return m5Fua1;
    }

    public void setM5Fua1(String m5Fua1) {
        this.m5Fua1 = m5Fua1;
    }

    public String getM6Fecha1() {
        return m6Fecha1;
    }

    public void setM6Fecha1(String m6Fecha1) {
        this.m6Fecha1 = m6Fecha1;
    }

    public String getM6Cie101() {
        return m6Cie101;
    }

    public void setM6Cie101(String m6Cie101) {
        this.m6Cie101 = m6Cie101;
    }

    public String getM6Fua1() {
        return m6Fua1;
    }

    public void setM6Fua1(String m6Fua1) {
        this.m6Fua1 = m6Fua1;
    }

    public String getM7Fecha1() {
        return m7Fecha1;
    }

    public void setM7Fecha1(String m7Fecha1) {
        this.m7Fecha1 = m7Fecha1;
    }

    public String getM7Cie101() {
        return m7Cie101;
    }

    public void setM7Cie101(String m7Cie101) {
        this.m7Cie101 = m7Cie101;
    }

    public String getM7Fua1() {
        return m7Fua1;
    }

    public void setM7Fua1(String m7Fua1) {
        this.m7Fua1 = m7Fua1;
    }

    public String getM8Fecha1() {
        return m8Fecha1;
    }

    public void setM8Fecha1(String m8Fecha1) {
        this.m8Fecha1 = m8Fecha1;
    }

    public String getM8Cie101() {
        return m8Cie101;
    }

    public void setM8Cie101(String m8Cie101) {
        this.m8Cie101 = m8Cie101;
    }

    public String getM8Fua1() {
        return m8Fua1;
    }

    public void setM8Fua1(String m8Fua1) {
        this.m8Fua1 = m8Fua1;
    }

    public String getM9Fecha1() {
        return m9Fecha1;
    }

    public void setM9Fecha1(String m9Fecha1) {
        this.m9Fecha1 = m9Fecha1;
    }

    public String getM9Cie101() {
        return m9Cie101;
    }

    public void setM9Cie101(String m9Cie101) {
        this.m9Cie101 = m9Cie101;
    }

    public String getM9Fua1() {
        return m9Fua1;
    }

    public void setM9Fua1(String m9Fua1) {
        this.m9Fua1 = m9Fua1;
    }

    public String getM10Fecha1() {
        return m10Fecha1;
    }

    public void setM10Fecha1(String m10Fecha1) {
        this.m10Fecha1 = m10Fecha1;
    }

    public String getM10Cie101() {
        return m10Cie101;
    }

    public void setM10Cie101(String m10Cie101) {
        this.m10Cie101 = m10Cie101;
    }

    public String getM10Fua1() {
        return m10Fua1;
    }

    public void setM10Fua1(String m10Fua1) {
        this.m10Fua1 = m10Fua1;
    }

    public String getM11Fecha2() {
        return m11Fecha2;
    }

    public void setM11Fecha2(String m11Fecha2) {
        this.m11Fecha2 = m11Fecha2;
    }

    public String getM11Cie102() {
        return m11Cie102;
    }

    public void setM11Cie102(String m11Cie102) {
        this.m11Cie102 = m11Cie102;
    }

    public String getM11Fua2() {
        return m11Fua2;
    }

    public void setM11Fua2(String m11Fua2) {
        this.m11Fua2 = m11Fua2;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtRsCcd[ rsCcd=" + rsCcd + " ]";
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
