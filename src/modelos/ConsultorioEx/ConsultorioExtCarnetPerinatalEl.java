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
import servicios.Conexion;
import vista.ConsultorioEx.RegistroEmbarazoEXL;
public class ConsultorioExtCarnetPerinatalEl implements Serializable {
    private static final long serialVersionUID = 1L;
    DefaultTableModel m;
    Conexion con = new Conexion();
    private Connection cn;
    private int cpId;
    private int elId;
    private String elHg1;
    private String elHg1Fecha;
    private String elHg2;
    private String elHg2Fecha;
    private String elHga;
    private String elHgaFecha;
    private String elGli1;
    private String elGli1Fecha;
    private String elGli2;
    private String elGli2Fecha;
    private String elGliTg;
    private String elGliTgFecha;
    private String elVdrl1;
    private String elVdrl1Fecha;
    private String elVdrl2;
    private String elVdrl2Fecha;
    private String elFta;
    private String elFtaFecha;
    private String elThpa;
    private String elThpaFecha;
    private String elPrs;
    private String elPrsFecha;
    private String elVpr;
    private String elVprFecha;
    private String elPr2;
    private String elPr2Fecha;
    private String elElisa;
    private String elElisaFecha;
    private String elFi;
    private String elFiFecha;
    private String elHtlvi;
    private String elHtlviFecha;
    private String elTorch;
    private String elTorchFecha;
    private String elGotaG;
    private String elGotaGFecha;
    private String elMpr;
    private String elMprFecha;
    private String elFm;
    private String elFmFecha;
    private String elEco;
    private String elEcoFecha;
    private String elLeuco;
    private String elLeucoFechar;
    private String elNitrit;
    private String elNitritFecha;
    private String elUrocultiv;
    private String elUrocultivFecha;
    private String elBk;
    private String elBkFecha;
    private String elLt;
    private String elLtFecha;
    private String elHb;
    private String elHbFecha;
    private String elPap;
    private String elPapFecha;
    private String elIvaa;
    private String elIvaaFecha;
    private String elColposcopia;
    private String elColposcopiaFecha;
    private String fechaActu;
    private String horaActu;
    private String nomPc;
    private Character estado;
    private String codUsu;
    private int idActoMedico;
    public boolean mantenimientoConsultorioExtCarnetPerinatalEl(String tipo)
        {
        boolean resp = false;
        try{
            String sql = "CONSULTORIO_EXT_MANTENIMIENTO_CARNET_PERINATAL_EL ?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            cmd.setInt(1, getElId());
            cmd.setInt(2, getCpId());
            cmd.setString(3, getElHg1());
            cmd.setString(4, getElHg1Fecha());
            cmd.setString(5, getElHg2());
            cmd.setString(6, getElHg2Fecha());
            cmd.setString(7, getElHga());
            cmd.setString(8, getElHgaFecha());
            cmd.setString(9, getElGli1());
            cmd.setString(10, getElGli1Fecha());
            cmd.setString(11, getElGli2());
            cmd.setString(12, getElGli2Fecha());
            cmd.setString(13, getElGliTg());
            cmd.setString(14, getElGliTgFecha());
            cmd.setString(15, getElVdrl1());
            cmd.setString(16, getElVdrl1Fecha());
            cmd.setString(17, getElVdrl2());
            cmd.setString(18, getElVdrl2Fecha());
            cmd.setString(19, getElFta());
            cmd.setString(20, getElFtaFecha());
            cmd.setString(21, getElThpa());
            cmd.setString(22, getElThpaFecha());
            cmd.setString(23, getElPrs());
            cmd.setString(24, getElPrsFecha());
            cmd.setString(25, getElVpr());
            cmd.setString(26, getElVprFecha());
            cmd.setString(27, getElPr2());
            cmd.setString(28, getElPr2Fecha());
            cmd.setString(29, getElElisa());
            cmd.setString(30, getElElisaFecha());
            cmd.setString(31, getElFi());
            cmd.setString(32, getElFiFecha());
            cmd.setString(33, getElHtlvi());
            cmd.setString(34, getElHtlviFecha());
            cmd.setString(35, getElTorch());
            cmd.setString(36, getElTorchFecha());
            cmd.setString(37, getElGotaG());
            cmd.setString(38, getElGotaGFecha());
            cmd.setString(39, getElMpr());
            cmd.setString(40, getElMprFecha());
            cmd.setString(41, getElFm());
            cmd.setString(42, getElFmFecha());
            cmd.setString(43, getElEco());
            cmd.setString(44, getElEcoFecha());
            cmd.setString(45, getElLeuco());
            cmd.setString(46, getElLeucoFechar());
            cmd.setString(47, getElNitrit());
            cmd.setString(48, getElNitritFecha());
            cmd.setString(49, getElUrocultiv());
            cmd.setString(50, getElUrocultivFecha());
            cmd.setString(51, getElBk());
            cmd.setString(52, getElBkFecha());
            cmd.setString(53, getElLt());
            cmd.setString(54, getElLtFecha());
            cmd.setString(55, getElHb());
            cmd.setString(56, getElHbFecha());
            cmd.setString(57, getElPap());
            cmd.setString(58, getElPapFecha());
            cmd.setString(59, getElIvaa());
            cmd.setString(60, getElIvaaFecha());
            cmd.setString(61, getElColposcopia());
            cmd.setString(62, getElColposcopiaFecha());
            cmd.setString(63, getCodUsu());
            cmd.setString(64, tipo);
            cmd.setInt(65, getIdActoMedico());
            if(!cmd.execute())
            {
                resp = true;
            }
            cmd.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error: mantenimientoConsultorioExtCarnetPerinatalEl: " + ex.getMessage());
        }
        return resp;
    }
    
    public String perinatalElID()
    {
        String cod="";
        try
        {
            String sql = "SELECT TOP 1 EL_ID\n" +
                        "FROM CONSULTORIO_EXT_CARNET_PERINATAL_EL \n" +
                        "WHERE NOM_PC = HOST_NAME()\n" +
                        "ORDER BY EL_ID DESC ";
            PreparedStatement cmd = getCn().prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
               cod = rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            System.out.println("perinatalElID: " + ex.getMessage());
        }
        return cod;
    }
    
        public void ConsultoriosExtEXLListar(String rs_id){
        String consulta="";
        try {
            consulta="CONSULTORIO_EXT_LISTAR_CARNET_PERINATAL_EL ?";
            PreparedStatement cmd = getCn().prepareStatement(consulta);
            cmd.setString(1, rs_id);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                 RegistroEmbarazoEXL.lblIdEx.setText(r.getString(1)); 
                 RegistroEmbarazoEXL.lblIdActoMedico.setText(r.getString(68)); 
                 RegistroEmbarazoEXL.lblActoMedico.setText("Acto Médico de registro " + r.getString(69));
                 if (!RegistroEmbarazoEXL.lblIdEx.getText().equals("") ){
                    RegistroEmbarazoEXL.btnGuardar1.setEnabled(false);
                    RegistroEmbarazoEXL.btneditar.setEnabled(true);
                    RegistroEmbarazoEXL.var.setText("2");
                }
                ///////////////////////////////////////////////////HEMOGLOBINA 1
                if (!(r.getString(3)).equals("N") ){
                RegistroEmbarazoEXL.txtH1.setText(r.getString(3));
                }
                if ((r.getString(3)).equals("N") ){
                RegistroEmbarazoEXL.txtH1.setText("");
                RegistroEmbarazoEXL.txtH1.setEnabled(false);
                RegistroEmbarazoEXL.chkH1.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(4).equals("")){
                        RegistroEmbarazoEXL.dFechaH1.setDate(null);
                    } else {
                        String fechaSeleccionadaD2 = (String)(r.getString(4));
                        DateFormat dfoD2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaD2 = dfoD2.parse(fechaSeleccionadaD2);
                        RegistroEmbarazoEXL.dFechaH1.setDate(fechaD2);

                    }
                } catch (Exception e) {
                }
                ///////////////////////////////////////////////////HEMOGLOBINA 2
                if (!(r.getString(5)).equals("N") ){
                RegistroEmbarazoEXL.txtH2.setText(r.getString(5));
                }
                if ((r.getString(5)).equals("N") ){
                RegistroEmbarazoEXL.txtH2.setText("");
                RegistroEmbarazoEXL.txtH2.setEnabled(false);
                RegistroEmbarazoEXL.chkH2.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(6).equals("")){
                        RegistroEmbarazoEXL.dFechaH2.setDate(null);
                    } else {
                        String fechaSeleccionadaH2 = (String)(r.getString(6));
                        DateFormat dfoH2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaH2 = dfoH2.parse(fechaSeleccionadaH2);
                        RegistroEmbarazoEXL.dFechaH2.setDate(fechaH2);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////HEMOGLOBINA ALTA
                if (!(r.getString(7)).equals("N") ){
                RegistroEmbarazoEXL.txtH3.setText(r.getString(7));
                }
                if ((r.getString(7)).equals("N") ){
                RegistroEmbarazoEXL.txtH3.setText("");
                RegistroEmbarazoEXL.txtH3.setEnabled(false);
                RegistroEmbarazoEXL.chkH3.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(8).equals("")){
                        RegistroEmbarazoEXL.dFechaH3.setDate(null);
                    } else {
                        String fechaSeleccionadaH3 = (String)(r.getString(8));
                        DateFormat dfoH3 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaH3 = dfoH3.parse(fechaSeleccionadaH3);
                        RegistroEmbarazoEXL.dFechaH3.setDate(fechaH3);

                    }
                } catch (Exception e) {
                }  
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////GLICEMIA 1
                if ((r.getString(9)).equals("N") ){
                RegistroEmbarazoEXL.txtG1N.setText("X");
                RegistroEmbarazoEXL.txtG1A.setText("");
                RegistroEmbarazoEXL.txtG1NA.setText("");
                }
                if ((r.getString(9)).equals("A") ){
                RegistroEmbarazoEXL.txtG1N.setText("");
                RegistroEmbarazoEXL.txtG1A.setText("X");
                RegistroEmbarazoEXL.txtG1NA.setText("");
                }
                if ((r.getString(9)).equals("H") ){
                RegistroEmbarazoEXL.txtG1N.setText("");
                RegistroEmbarazoEXL.txtG1A.setText("");
                RegistroEmbarazoEXL.txtG1NA.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(10).equals("")){
                        RegistroEmbarazoEXL.fechaG1.setDate(null);
                    } else {
                        String fechaSeleccionadaG1 = (String)(r.getString(10));
                        DateFormat dfoG1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaG1 = dfoG1.parse(fechaSeleccionadaG1);
                        RegistroEmbarazoEXL.fechaG1.setDate(fechaG1);

                    }
                } catch (Exception e) {
                } 
                //////////////////////////////////////////////////////GLICEMIA 2
                if ((r.getString(11)).equals("N") ){
                RegistroEmbarazoEXL.txtG2N.setText("X");
                RegistroEmbarazoEXL.txtG2A.setText("");
                RegistroEmbarazoEXL.txtG2NA.setText("");
                }
                if ((r.getString(11)).equals("A") ){
                RegistroEmbarazoEXL.txtG2N.setText("");
                RegistroEmbarazoEXL.txtG2A.setText("X");
                RegistroEmbarazoEXL.txtG2NA.setText("");
                }
                if ((r.getString(11)).equals("H") ){
                RegistroEmbarazoEXL.txtG2N.setText("");
                RegistroEmbarazoEXL.txtG2A.setText("");
                RegistroEmbarazoEXL.txtG2NA.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(12).equals("")){
                        RegistroEmbarazoEXL.fechaG2.setDate(null);
                    } else {
                        String fechaSeleccionadaG1 = (String)(r.getString(12));
                        DateFormat dfoG1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaG1 = dfoG1.parse(fechaSeleccionadaG1);
                        RegistroEmbarazoEXL.fechaG2.setDate(fechaG1);

                    }
                } catch (Exception e) {
                } 
                
                /////////////////////////////////////////TOLERANCIA A LA GLUCOSA
                if ((r.getString(13)).equals("N") ){
                RegistroEmbarazoEXL.txtTGN.setText("X");
                RegistroEmbarazoEXL.txtTGA.setText("");
                RegistroEmbarazoEXL.txtTGNH.setText("");
                RegistroEmbarazoEXL.txtTGNA.setText("");
                }
                if ((r.getString(13)).equals("A") ){
                RegistroEmbarazoEXL.txtTGN.setText("");
                RegistroEmbarazoEXL.txtTGA.setText("X");
                RegistroEmbarazoEXL.txtTGNH.setText("");
                RegistroEmbarazoEXL.txtTGNA.setText("");
                }
                if ((r.getString(13)).equals("H") ){
                RegistroEmbarazoEXL.txtTGN.setText("");
                RegistroEmbarazoEXL.txtTGA.setText("");
                RegistroEmbarazoEXL.txtTGNH.setText("X");
                RegistroEmbarazoEXL.txtTGNA.setText("");
                }
                if ((r.getString(13)).equals("S") ){
                RegistroEmbarazoEXL.txtTGN.setText("");
                RegistroEmbarazoEXL.txtTGA.setText("");
                RegistroEmbarazoEXL.txtTGNH.setText("");
                RegistroEmbarazoEXL.txtTGNA.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(14).equals("")){
                        RegistroEmbarazoEXL.fechaTG.setDate(null);
                    } else {
                        String fechaSeleccionadaTG = (String)(r.getString(14));
                        DateFormat dfoTG = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaTG = dfoTG.parse(fechaSeleccionadaTG);
                        RegistroEmbarazoEXL.fechaTG.setDate(fechaTG);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////VDRL 1
                if ((r.getString(15)).equals("N") ){
                RegistroEmbarazoEXL.txtVDRL1nr.setText("X");
                RegistroEmbarazoEXL.txtVDRL1a.setText("");
                RegistroEmbarazoEXL.txtVDRL1nh.setText("");
                }
                if ((r.getString(15)).equals("R") ){
                RegistroEmbarazoEXL.txtVDRL1nr.setText("");
                RegistroEmbarazoEXL.txtVDRL1a.setText("X");
                RegistroEmbarazoEXL.txtVDRL1nh.setText("");
                }
                if ((r.getString(15)).equals("H") ){
                RegistroEmbarazoEXL.txtVDRL1nr.setText("");
                RegistroEmbarazoEXL.txtVDRL1a.setText("");
                RegistroEmbarazoEXL.txtVDRL1nh.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(16).equals("")){
                        RegistroEmbarazoEXL.fechaVDRL1.setDate(null);
                    } else {
                        String fechaSeleccionadaTG = (String)(r.getString(16));
                        DateFormat dfoTG = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaTG = dfoTG.parse(fechaSeleccionadaTG);
                        RegistroEmbarazoEXL.fechaVDRL1.setDate(fechaTG);

                    }
                } catch (Exception e) {
                }
                //////////////////////////////////////////////////////////VDRL 2
                if ((r.getString(17)).equals("N") ){
                RegistroEmbarazoEXL.txtVDRL2nr.setText("X");
                RegistroEmbarazoEXL.txtVDRL2a.setText("");
                RegistroEmbarazoEXL.txtVDRL2nh.setText("");
                RegistroEmbarazoEXL.txtVDRL2na.setText("");
                }
                if ((r.getString(17)).equals("R") ){
                RegistroEmbarazoEXL.txtVDRL2nr.setText("");
                RegistroEmbarazoEXL.txtVDRL2a.setText("X");
                RegistroEmbarazoEXL.txtVDRL2nh.setText("");
                RegistroEmbarazoEXL.txtVDRL2na.setText("");
                }
                if ((r.getString(17)).equals("H") ){
                RegistroEmbarazoEXL.txtVDRL2nr.setText("");
                RegistroEmbarazoEXL.txtVDRL2a.setText("");
                RegistroEmbarazoEXL.txtVDRL2nh.setText("X");
                RegistroEmbarazoEXL.txtVDRL2na.setText("");
                }
                if ((r.getString(17)).equals("A") ){
                RegistroEmbarazoEXL.txtVDRL2nr.setText("");
                RegistroEmbarazoEXL.txtVDRL2a.setText("");
                RegistroEmbarazoEXL.txtVDRL2nh.setText("");
                RegistroEmbarazoEXL.txtVDRL2na.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(18).equals("")){
                        RegistroEmbarazoEXL.fechaVDRL2.setDate(null);
                    } else {
                        String fechaSeleccionadaTG = (String)(r.getString(18));
                        DateFormat dfoTG = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaTG = dfoTG.parse(fechaSeleccionadaTG);
                        RegistroEmbarazoEXL.fechaVDRL2.setDate(fechaTG);

                    }
                } catch (Exception e) {
                }
                /////////////////////////////////////////////////////////FTA Abs
                if ((r.getString(19)).equals("N") ){
                RegistroEmbarazoEXL.txtFTAnr.setText("X");
                RegistroEmbarazoEXL.txtFTAr.setText("");
                RegistroEmbarazoEXL.txtFTAnh.setText("");
                RegistroEmbarazoEXL.txtFTAna.setText("");
                }
                if ((r.getString(19)).equals("R") ){
                RegistroEmbarazoEXL.txtFTAnr.setText("");
                RegistroEmbarazoEXL.txtFTAr.setText("X");
                RegistroEmbarazoEXL.txtFTAnh.setText("");
                RegistroEmbarazoEXL.txtFTAna.setText("");
                }
                if ((r.getString(19)).equals("H") ){
                RegistroEmbarazoEXL.txtFTAnr.setText("");
                RegistroEmbarazoEXL.txtFTAr.setText("");
                RegistroEmbarazoEXL.txtFTAnh.setText("X");
                RegistroEmbarazoEXL.txtFTAna.setText("");
                }
                if ((r.getString(19)).equals("A") ){
                RegistroEmbarazoEXL.txtFTAnr.setText("");
                RegistroEmbarazoEXL.txtFTAr.setText("");
                RegistroEmbarazoEXL.txtFTAnh.setText("");
                RegistroEmbarazoEXL.txtFTAna.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(20).equals("")){
                        RegistroEmbarazoEXL.fechaFTA.setDate(null);
                    } else {
                        String fechaSeleccionadaFTA = (String)(r.getString(20));
                        DateFormat dfoFTA= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaFTA = dfoFTA.parse(fechaSeleccionadaFTA);
                        RegistroEmbarazoEXL.fechaFTA.setDate(fechaFTA);

                    }
                } catch (Exception e) {
                }
                
                
                ////////////////////////////////////////////////////////////THPA
                if ((r.getString(21)).equals("N") ){
                RegistroEmbarazoEXL.txtTHPAnr.setText("X");
                RegistroEmbarazoEXL.txtTHPAr.setText("");
                RegistroEmbarazoEXL.txtTHPAnh.setText("");
                RegistroEmbarazoEXL.txtTHPAna.setText("");
                }
                if ((r.getString(21)).equals("R") ){
                RegistroEmbarazoEXL.txtTHPAnr.setText("");
                RegistroEmbarazoEXL.txtTHPAr.setText("X");
                RegistroEmbarazoEXL.txtTHPAnh.setText("");
                RegistroEmbarazoEXL.txtTHPAna.setText("");
                }
                if ((r.getString(21)).equals("H") ){
                RegistroEmbarazoEXL.txtTHPAnr.setText("");
                RegistroEmbarazoEXL.txtTHPAr.setText("");
                RegistroEmbarazoEXL.txtTHPAnh.setText("X");
                RegistroEmbarazoEXL.txtTHPAna.setText("");
                }
                if ((r.getString(21)).equals("A") ){
                RegistroEmbarazoEXL.txtTHPAnr.setText("");
                RegistroEmbarazoEXL.txtTHPAr.setText("");
                RegistroEmbarazoEXL.txtTHPAnh.setText("");
                RegistroEmbarazoEXL.txtTHPAna.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(22).equals("")){
                        RegistroEmbarazoEXL.fechaTHPA.setDate(null);
                    } else {
                        String fechaSeleccionadaTHPA = (String)(r.getString(22));
                        DateFormat dfoTHPA= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaFTA = dfoTHPA.parse(fechaSeleccionadaTHPA);
                        RegistroEmbarazoEXL.fechaTHPA.setDate(fechaFTA);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////PRUEBA RAPIDA DE SIFILIS
                if ((r.getString(23)).equals("N") ){
                RegistroEmbarazoEXL.txtPruebanr.setText("X");
                RegistroEmbarazoEXL.txtPruebar.setText("");
                RegistroEmbarazoEXL.txtPruebanh.setText("");
                }
                if ((r.getString(23)).equals("R") ){
                RegistroEmbarazoEXL.txtPruebanr.setText("");
                RegistroEmbarazoEXL.txtPruebar.setText("X");
                RegistroEmbarazoEXL.txtPruebanh.setText("");
                }
                if ((r.getString(23)).equals("H") ){
                RegistroEmbarazoEXL.txtPruebanr.setText("");
                RegistroEmbarazoEXL.txtPruebar.setText("");
                RegistroEmbarazoEXL.txtPruebanh.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(24).equals("")){
                        RegistroEmbarazoEXL.fechaPrueba.setDate(null);
                    } else {
                        String fechaSeleccionadaP = (String)(r.getString(24));
                        DateFormat dfoP= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaP = dfoP.parse(fechaSeleccionadaP);
                        RegistroEmbarazoEXL.fechaPrueba.setDate(fechaP);

                    }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////VIH PRUEBA RAPIDA 1
                if ((r.getString(25)).equals("N") ){
                RegistroEmbarazoEXL.txtVIH1nr.setText("X");
                RegistroEmbarazoEXL.txtVIHr.setText("");
                RegistroEmbarazoEXL.txtVIHnh.setText("");
                }
                if ((r.getString(25)).equals("R") ){
                RegistroEmbarazoEXL.txtVIH1nr.setText("");
                RegistroEmbarazoEXL.txtVIHr.setText("X");
                RegistroEmbarazoEXL.txtVIHnh.setText("");
                }
                if ((r.getString(25)).equals("H") ){
                RegistroEmbarazoEXL.txtVIH1nr.setText("");
                RegistroEmbarazoEXL.txtVIHr.setText("");
                RegistroEmbarazoEXL.txtVIHnh.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(26).equals("")){
                        RegistroEmbarazoEXL.fechaVIH1.setDate(null);
                    } else {
                        String fechaSeleccionadaVIH1 = (String)(r.getString(26));
                        DateFormat dfoVIH1= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaVIH1 = dfoVIH1.parse(fechaSeleccionadaVIH1);
                        RegistroEmbarazoEXL.fechaVIH1.setDate(fechaVIH1);

                    }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////VIH PRUEBA RAPIDA 2
                if ((r.getString(27)).equals("N") ){
                RegistroEmbarazoEXL.txtVIH2nr.setText("X");
                RegistroEmbarazoEXL.txtVIH2r.setText("");
                RegistroEmbarazoEXL.txtVIH2nh.setText("");
                RegistroEmbarazoEXL.txtVIH2na.setText("");
                }
                if ((r.getString(27)).equals("R") ){
                RegistroEmbarazoEXL.txtVIH2nr.setText("");
                RegistroEmbarazoEXL.txtVIH2r.setText("X");
                RegistroEmbarazoEXL.txtVIH2nh.setText("");
                RegistroEmbarazoEXL.txtVIH2na.setText("");
                }
                if ((r.getString(27)).equals("H") ){
                RegistroEmbarazoEXL.txtVIH2nr.setText("");
                RegistroEmbarazoEXL.txtVIH2r.setText("");
                RegistroEmbarazoEXL.txtVIH2nh.setText("X");
                RegistroEmbarazoEXL.txtVIH2na.setText("");
                }
                if ((r.getString(27)).equals("A") ){
                RegistroEmbarazoEXL.txtVIH2nr.setText("");
                RegistroEmbarazoEXL.txtVIH2r.setText("");
                RegistroEmbarazoEXL.txtVIH2nh.setText("");
                RegistroEmbarazoEXL.txtVIH2na.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(28).equals("")){
                        RegistroEmbarazoEXL.fechaVIH2.setDate(null);
                    } else {
                        String fechaSeleccionadaVIH2 = (String)(r.getString(28));
                        DateFormat dfoVIH2= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaVIH2 = dfoVIH2.parse(fechaSeleccionadaVIH2);
                        RegistroEmbarazoEXL.fechaVIH2.setDate(fechaVIH2);

                    }
                } catch (Exception e) {
                }
                ///////////////////////////////////////////////////////////ELISA
                if ((r.getString(29)).equals("N") ){
                RegistroEmbarazoEXL.txtElisanr.setText("X");
                RegistroEmbarazoEXL.txtElisar.setText("");
                RegistroEmbarazoEXL.txtElisanh.setText("");
                RegistroEmbarazoEXL.txtElisana.setText("");
                }
                if ((r.getString(29)).equals("R") ){
                RegistroEmbarazoEXL.txtElisanr.setText("");
                RegistroEmbarazoEXL.txtElisar.setText("X");
                RegistroEmbarazoEXL.txtElisanh.setText("");
                RegistroEmbarazoEXL.txtElisana.setText("");
                }
                if ((r.getString(29)).equals("H") ){
                RegistroEmbarazoEXL.txtElisanr.setText("");
                RegistroEmbarazoEXL.txtElisar.setText("");
                RegistroEmbarazoEXL.txtElisanh.setText("X");
                RegistroEmbarazoEXL.txtElisana.setText("");
                }
                if ((r.getString(29)).equals("A") ){
                RegistroEmbarazoEXL.txtElisanr.setText("");
                RegistroEmbarazoEXL.txtElisar.setText("");
                RegistroEmbarazoEXL.txtElisanh.setText("");
                RegistroEmbarazoEXL.txtElisana.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(30).equals("")){
                        RegistroEmbarazoEXL.fechaELisa.setDate(null);
                    } else {
                        String fechaSeleccionadaELISA = (String)(r.getString(30));
                        DateFormat dfoELISA= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaELISA = dfoELISA.parse(fechaSeleccionadaELISA);
                        RegistroEmbarazoEXL.fechaELisa.setDate(fechaELISA);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////IFI / Westerm Blot
                if ((r.getString(31)).equals("N") ){
                RegistroEmbarazoEXL.txtIFIn.setText("X");
                RegistroEmbarazoEXL.txtIFIp.setText("");
                RegistroEmbarazoEXL.txtIFInh.setText("");
                RegistroEmbarazoEXL.txtIFIna.setText("");
                }
                if ((r.getString(31)).equals("P") ){
                RegistroEmbarazoEXL.txtIFIn.setText("");
                RegistroEmbarazoEXL.txtIFIp.setText("X");
                RegistroEmbarazoEXL.txtIFInh.setText("");
                RegistroEmbarazoEXL.txtIFIna.setText("");
                }
                if ((r.getString(31)).equals("H") ){
                RegistroEmbarazoEXL.txtIFIn.setText("");
                RegistroEmbarazoEXL.txtIFIp.setText("");
                RegistroEmbarazoEXL.txtIFInh.setText("X");
                RegistroEmbarazoEXL.txtIFIna.setText("");
                }
                if ((r.getString(31)).equals("A") ){
                RegistroEmbarazoEXL.txtIFIn.setText("");
                RegistroEmbarazoEXL.txtIFIp.setText("");
                RegistroEmbarazoEXL.txtIFInh.setText("");
                RegistroEmbarazoEXL.txtIFIna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(32).equals("")){
                        RegistroEmbarazoEXL.fechaIFI.setDate(null);
                    } else {
                        String fechaSeleccionadaIFI = (String)(r.getString(32));
                        DateFormat dfoIFI= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaIFI = dfoIFI.parse(fechaSeleccionadaIFI);
                        RegistroEmbarazoEXL.fechaIFI.setDate(fechaIFI);

                    }
                } catch (Exception e) {
                }
                
                ///////////////////////////////////////////////////////////HTLVI
                if ((r.getString(33)).equals("N") ){
                RegistroEmbarazoEXL.txtHTLVLn.setText("X");
                RegistroEmbarazoEXL.txtHTLVLp.setText("");
                RegistroEmbarazoEXL.txtHTLVLnh.setText("");
                RegistroEmbarazoEXL.txtHTLVLna.setText("");
                }
                if ((r.getString(33)).equals("P") ){
                RegistroEmbarazoEXL.txtHTLVLn.setText("");
                RegistroEmbarazoEXL.txtHTLVLp.setText("X");
                RegistroEmbarazoEXL.txtHTLVLnh.setText("");
                RegistroEmbarazoEXL.txtHTLVLna.setText("");
                }
                if ((r.getString(33)).equals("H") ){
                RegistroEmbarazoEXL.txtHTLVLn.setText("");
                RegistroEmbarazoEXL.txtHTLVLp.setText("");
                RegistroEmbarazoEXL.txtHTLVLnh.setText("X");
                RegistroEmbarazoEXL.txtHTLVLna.setText("");
                }
                if ((r.getString(33)).equals("A") ){
                RegistroEmbarazoEXL.txtHTLVLn.setText("");
                RegistroEmbarazoEXL.txtHTLVLp.setText("");
                RegistroEmbarazoEXL.txtHTLVLnh.setText("");
                RegistroEmbarazoEXL.txtHTLVLna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(34).equals("")){
                        RegistroEmbarazoEXL.fechaHTLVI.setDate(null);
                    } else {
                        String fechaSeleccionadaHT = (String)(r.getString(34));
                        DateFormat dfoHT= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaHT = dfoHT.parse(fechaSeleccionadaHT);
                        RegistroEmbarazoEXL.fechaHTLVI.setDate(fechaHT);

                    }
                } catch (Exception e) {
                }
                ///////////////////////////////////////////////////////////TORCH
                if ((r.getString(35)).equals("N") ){
                RegistroEmbarazoEXL.txtTORCHn.setText("X");
                RegistroEmbarazoEXL.txtTORCHp.setText("");
                RegistroEmbarazoEXL.txtTORCHnh.setText("");
                RegistroEmbarazoEXL.txtTORCHna.setText("");
                }
                if ((r.getString(35)).equals("P") ){
                RegistroEmbarazoEXL.txtTORCHn.setText("");
                RegistroEmbarazoEXL.txtTORCHp.setText("X");
                RegistroEmbarazoEXL.txtTORCHnh.setText("");
                RegistroEmbarazoEXL.txtTORCHna.setText("");
                }
                if ((r.getString(35)).equals("H") ){
                RegistroEmbarazoEXL.txtTORCHn.setText("");
                RegistroEmbarazoEXL.txtTORCHp.setText("");
                RegistroEmbarazoEXL.txtTORCHnh.setText("X");
                RegistroEmbarazoEXL.txtTORCHna.setText("");
                }
                if ((r.getString(35)).equals("A") ){
                RegistroEmbarazoEXL.txtTORCHn.setText("");
                RegistroEmbarazoEXL.txtTORCHp.setText("");
                RegistroEmbarazoEXL.txtTORCHnh.setText("");
                RegistroEmbarazoEXL.txtTORCHna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(36).equals("")){
                        RegistroEmbarazoEXL.fechaTORCH.setDate(null);
                    } else {
                        String fechaSeleccionadaTORCH = (String)(r.getString(36));
                        DateFormat dfoTORCH= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaTORCH = dfoTORCH.parse(fechaSeleccionadaTORCH);
                        RegistroEmbarazoEXL.fechaTORCH.setDate(fechaTORCH);

                    }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////////////Gota Gruesa
                if ((r.getString(35)).equals("N") ){
                RegistroEmbarazoEXL.txtGotan.setText("X");
                RegistroEmbarazoEXL.txtGotap.setText("");
                RegistroEmbarazoEXL.txtGotanh.setText("");
                RegistroEmbarazoEXL.txtGotna.setText("");
                }
                if ((r.getString(35)).equals("P") ){
                RegistroEmbarazoEXL.txtGotan.setText("");
                RegistroEmbarazoEXL.txtGotap.setText("X");
                RegistroEmbarazoEXL.txtGotanh.setText("");
                RegistroEmbarazoEXL.txtGotna.setText("");
                }
                if ((r.getString(35)).equals("H") ){
                RegistroEmbarazoEXL.txtGotan.setText("");
                RegistroEmbarazoEXL.txtGotap.setText("");
                RegistroEmbarazoEXL.txtGotanh.setText("X");
                RegistroEmbarazoEXL.txtGotna.setText("");
                }
                if ((r.getString(35)).equals("A") ){
                RegistroEmbarazoEXL.txtGotan.setText("");
                RegistroEmbarazoEXL.txtGotap.setText("");
                RegistroEmbarazoEXL.txtGotanh.setText("");
                RegistroEmbarazoEXL.txtGotna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(36).equals("")){
                        RegistroEmbarazoEXL.fechaGota.setDate(null);
                    } else {
                        String fechaSeleccionadaGOTA = (String)(r.getString(36));
                        DateFormat dfoGOTA= new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaGOTA = dfoGOTA.parse(fechaSeleccionadaGOTA);
                        RegistroEmbarazoEXL.fechaGota.setDate(fechaGOTA);

                    }
                } catch (Exception e) {
                }
                
                //////////////////////////////////////////////////MALARIA PRUEBA
                if ((r.getString(37)).equals("N") ){
                RegistroEmbarazoEXL.txtMalarian.setText("X");
                RegistroEmbarazoEXL.txtMalariap.setText("");
                RegistroEmbarazoEXL.txtMalarianh.setText("");
                RegistroEmbarazoEXL.txtMalariana.setText("");
                }
                if ((r.getString(37)).equals("P") ){
                RegistroEmbarazoEXL.txtMalarian.setText("");
                RegistroEmbarazoEXL.txtMalariap.setText("X");
                RegistroEmbarazoEXL.txtMalarianh.setText("");
                RegistroEmbarazoEXL.txtMalariana.setText("");
                }
                if ((r.getString(37)).equals("H") ){
                RegistroEmbarazoEXL.txtMalarian.setText("");
                RegistroEmbarazoEXL.txtMalariap.setText("");
                RegistroEmbarazoEXL.txtMalarianh.setText("X");
                RegistroEmbarazoEXL.txtMalariana.setText("");
                }
                if ((r.getString(37)).equals("A") ){
                RegistroEmbarazoEXL.txtMalarian.setText("");
                RegistroEmbarazoEXL.txtMalariap.setText("");
                RegistroEmbarazoEXL.txtMalarianh.setText("");
                RegistroEmbarazoEXL.txtMalariana.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(38).equals("")){
                        RegistroEmbarazoEXL.fechaMalaria.setDate(null);
                    } else {
                        String fechaSeleccionadaMAL = (String)(r.getString(38));
                        DateFormat dfoMAL = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaMAL  = dfoMAL .parse(fechaSeleccionadaMAL );
                        RegistroEmbarazoEXL.fechaMalaria.setDate(fechaMAL );

                    }
                } catch (Exception e) {
                }
                
                ////////////////////////////////////////////////////FLUORECENCIA
                if ((r.getString(39)).equals("N") ){
                RegistroEmbarazoEXL.txtFluorn.setText("X");
                RegistroEmbarazoEXL.txtFluorp.setText("");
                RegistroEmbarazoEXL.txtFluornh.setText("");
                RegistroEmbarazoEXL.txtFluorna.setText("");
                }
                if ((r.getString(39)).equals("P") ){
                RegistroEmbarazoEXL.txtFluorn.setText("");
                RegistroEmbarazoEXL.txtFluorp.setText("X");
                RegistroEmbarazoEXL.txtFluornh.setText("");
                RegistroEmbarazoEXL.txtFluorna.setText("");
                }
                if ((r.getString(39)).equals("H") ){
                RegistroEmbarazoEXL.txtFluorn.setText("");
                RegistroEmbarazoEXL.txtFluorp.setText("");
                RegistroEmbarazoEXL.txtFluornh.setText("X");
                RegistroEmbarazoEXL.txtFluorna.setText("");
                }
                if ((r.getString(39)).equals("A") ){
                RegistroEmbarazoEXL.txtFluorn.setText("");
                RegistroEmbarazoEXL.txtFluorp.setText("");
                RegistroEmbarazoEXL.txtFluornh.setText("");
                RegistroEmbarazoEXL.txtFluorna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(40).equals("")){
                        RegistroEmbarazoEXL.fechaFluor.setDate(null);
                    } else {
                        String fechaSeleccionadaFL = (String)(r.getString(40));
                        DateFormat dfoFL = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaFL  = dfoFL .parse(fechaSeleccionadaFL );
                        RegistroEmbarazoEXL.fechaFluor.setDate(fechaFL );

                    }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////////////////EX-COMP
                if ((r.getString(41)).equals("N") ){
                RegistroEmbarazoEXL.txtEXn.setText("X");
                RegistroEmbarazoEXL.txtEXp.setText("");
                RegistroEmbarazoEXL.txtEXnh.setText("");
                }
                if ((r.getString(41)).equals("P") ){
                RegistroEmbarazoEXL.txtEXn.setText("");
                RegistroEmbarazoEXL.txtEXp.setText("X");
                RegistroEmbarazoEXL.txtEXnh.setText("");
                }
                if ((r.getString(41)).equals("H") ){
                RegistroEmbarazoEXL.txtEXn.setText("");
                RegistroEmbarazoEXL.txtEXp.setText("");
                RegistroEmbarazoEXL.txtEXnh.setText("X");
                }
                    //////FECHA
                try {
                    if(r.getString(42).equals("")){
                        RegistroEmbarazoEXL.fechaEX.setDate(null);
                    } else {
                        String fechaSeleccionadaEX = (String)(r.getString(42));
                        DateFormat dfoEX = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaEX  = dfoEX .parse(fechaSeleccionadaEX);
                        RegistroEmbarazoEXL.fechaEX.setDate(fechaEX);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////LEUCOCITURIA
                if ((r.getString(43)).equals("N") ){
                RegistroEmbarazoEXL.txtLEUn.setText("X");
                RegistroEmbarazoEXL.txtLEUp.setText("");
                RegistroEmbarazoEXL.txtLEUnh.setText("");
                }
                if ((r.getString(43)).equals("P") ){
                RegistroEmbarazoEXL.txtLEUn.setText("");
                RegistroEmbarazoEXL.txtLEUp.setText("X");
                RegistroEmbarazoEXL.txtLEUnh.setText("");
                }
                if ((r.getString(43)).equals("H") ){
                RegistroEmbarazoEXL.txtLEUn.setText("");
                RegistroEmbarazoEXL.txtLEUp.setText("");
                RegistroEmbarazoEXL.txtLEUnh.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(44).equals("")){
                        RegistroEmbarazoEXL.fechaLEU.setDate(null);
                    } else {
                        String fechaSeleccionadaLEU = (String)(r.getString(44));
                        DateFormat dfoLEU = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaLEU  = dfoLEU .parse(fechaSeleccionadaLEU);
                        RegistroEmbarazoEXL.fechaLEU.setDate(fechaLEU);

                    }
                } catch (Exception e) {
                }
                
                ////////////////////////////////////////////////////////NITRITOS
                if ((r.getString(45)).equals("N") ){
                RegistroEmbarazoEXL.txtNitritosn.setText("X");
                RegistroEmbarazoEXL.txtNitritosp.setText("");
                RegistroEmbarazoEXL.txtNitritosnh.setText("");
                }
                if ((r.getString(45)).equals("P") ){
                RegistroEmbarazoEXL.txtNitritosn.setText("");
                RegistroEmbarazoEXL.txtNitritosp.setText("X");
                RegistroEmbarazoEXL.txtNitritosnh.setText("");
                }
                if ((r.getString(45)).equals("H") ){
                RegistroEmbarazoEXL.txtNitritosn.setText("");
                RegistroEmbarazoEXL.txtNitritosp.setText("");
                RegistroEmbarazoEXL.txtNitritosnh.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(46).equals("")){
                        RegistroEmbarazoEXL.fechaNitritos.setDate(null);
                    } else {
                        String fechaSeleccionadaNIT = (String)(r.getString(46));
                        DateFormat dfoNIT = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaNIT   = dfoNIT  .parse(fechaSeleccionadaNIT);
                        RegistroEmbarazoEXL.fechaNitritos.setDate(fechaNIT);

                    }
                } catch (Exception e) {
                }
                
                //////////////////////////////////////////////////////UROCULTIVO
                if ((r.getString(47)).equals("N") ){
                RegistroEmbarazoEXL.txtUrocultivon.setText("X");
                RegistroEmbarazoEXL.txtUrocultivop.setText("");
                RegistroEmbarazoEXL.txtUrocultivonh.setText("");
                }
                if ((r.getString(47)).equals("P") ){
                RegistroEmbarazoEXL.txtUrocultivon.setText("");
                RegistroEmbarazoEXL.txtUrocultivop.setText("X");
                RegistroEmbarazoEXL.txtUrocultivonh.setText("");
                }
                if ((r.getString(47)).equals("H") ){
                RegistroEmbarazoEXL.txtUrocultivon.setText("");
                RegistroEmbarazoEXL.txtUrocultivop.setText("");
                RegistroEmbarazoEXL.txtUrocultivonh.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(48).equals("")){
                        RegistroEmbarazoEXL.fechaUrocultivo.setDate(null);
                    } else {
                        String fechaSeleccionadaURO = (String)(r.getString(48));
                        DateFormat dfoURO = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaURO   = dfoURO  .parse(fechaSeleccionadaURO);
                        RegistroEmbarazoEXL.fechaUrocultivo.setDate(fechaURO);

                    }
                } catch (Exception e) {
                }
                
                ////////////////////////////////////////////////////BK EN ESPUTO
                if ((r.getString(49)).equals("N") ){
                RegistroEmbarazoEXL.txtBKn.setText("X");
                RegistroEmbarazoEXL.txtBKp.setText("");
                RegistroEmbarazoEXL.txtBKnh.setText("");
                RegistroEmbarazoEXL.txtBKna.setText("");
                }
                if ((r.getString(49)).equals("P") ){
                RegistroEmbarazoEXL.txtBKn.setText("");
                RegistroEmbarazoEXL.txtBKp.setText("X");
                RegistroEmbarazoEXL.txtBKnh.setText("");
                RegistroEmbarazoEXL.txtBKna.setText("");
                }
                if ((r.getString(49)).equals("H") ){
                RegistroEmbarazoEXL.txtBKn.setText("");
                RegistroEmbarazoEXL.txtBKp.setText("");
                RegistroEmbarazoEXL.txtBKnh.setText("X");
                RegistroEmbarazoEXL.txtBKna.setText("");
                }
                if ((r.getString(49)).equals("A") ){
                RegistroEmbarazoEXL.txtBKn.setText("");
                RegistroEmbarazoEXL.txtBKp.setText("");
                RegistroEmbarazoEXL.txtBKnh.setText("");
                RegistroEmbarazoEXL.txtBKna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(50).equals("")){
                        RegistroEmbarazoEXL.fechaBK.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(50));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaBK.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////////LISTERIA
                if ((r.getString(51)).equals("N") ){
                RegistroEmbarazoEXL.txtListerian.setText("X");
                RegistroEmbarazoEXL.txtListeriap.setText("");
                RegistroEmbarazoEXL.txtListerianh.setText("");
                RegistroEmbarazoEXL.txtListeriana.setText("");
                }
                if ((r.getString(51)).equals("P") ){
                RegistroEmbarazoEXL.txtListerian.setText("");
                RegistroEmbarazoEXL.txtListeriap.setText("X");
                RegistroEmbarazoEXL.txtListerianh.setText("");
                RegistroEmbarazoEXL.txtListeriana.setText("");
                }
                if ((r.getString(51)).equals("H") ){
                RegistroEmbarazoEXL.txtListerian.setText("");
                RegistroEmbarazoEXL.txtListeriap.setText("");
                RegistroEmbarazoEXL.txtListerianh.setText("X");
                RegistroEmbarazoEXL.txtListeriana.setText("");
                }
                if ((r.getString(51)).equals("A") ){
                RegistroEmbarazoEXL.txtListerian.setText("");
                RegistroEmbarazoEXL.txtListeriap.setText("");
                RegistroEmbarazoEXL.txtListerianh.setText("");
                RegistroEmbarazoEXL.txtListeriana.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(52).equals("")){
                        RegistroEmbarazoEXL.fechaListeria.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(52));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaListeria.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                
                ////////////////////////////////////////////Tamizaje Hepatitis B
                if ((r.getString(53)).equals("N") ){
                RegistroEmbarazoEXL.txtTamizajen.setText("X");
                RegistroEmbarazoEXL.txtTamizajep.setText("");
                RegistroEmbarazoEXL.txtTamizajenh.setText("");
                RegistroEmbarazoEXL.txtTamizajena.setText("");
                }
                if ((r.getString(53)).equals("P") ){
                RegistroEmbarazoEXL.txtTamizajen.setText("");
                RegistroEmbarazoEXL.txtTamizajep.setText("X");
                RegistroEmbarazoEXL.txtTamizajenh.setText("");
                RegistroEmbarazoEXL.txtTamizajena.setText("");
                }
                if ((r.getString(53)).equals("H") ){
                RegistroEmbarazoEXL.txtTamizajen.setText("");
                RegistroEmbarazoEXL.txtTamizajep.setText("");
                RegistroEmbarazoEXL.txtTamizajenh.setText("X");
                RegistroEmbarazoEXL.txtTamizajena.setText("");
                }
                if ((r.getString(53)).equals("A") ){
                RegistroEmbarazoEXL.txtTamizajen.setText("");
                RegistroEmbarazoEXL.txtTamizajep.setText("");
                RegistroEmbarazoEXL.txtTamizajenh.setText("");
                RegistroEmbarazoEXL.txtTamizajena.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(54).equals("")){
                        RegistroEmbarazoEXL.fechaTamizaje.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(54));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaTamizaje.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////PAP
                if ((r.getString(55)).equals("N") ){
                RegistroEmbarazoEXL.txtPAPn.setText("X");
                RegistroEmbarazoEXL.txtPAPa.setText("");
                RegistroEmbarazoEXL.txtPAPnh.setText("");
                RegistroEmbarazoEXL.txtPAPna.setText("");
                }
                if ((r.getString(55)).equals("A") ){
                RegistroEmbarazoEXL.txtPAPn.setText("");
                RegistroEmbarazoEXL.txtPAPa.setText("X");
                RegistroEmbarazoEXL.txtPAPnh.setText("");
                RegistroEmbarazoEXL.txtPAPna.setText("");
                }
                if ((r.getString(55)).equals("H") ){
                RegistroEmbarazoEXL.txtPAPn.setText("");
                RegistroEmbarazoEXL.txtPAPa.setText("");
                RegistroEmbarazoEXL.txtPAPnh.setText("X");
                RegistroEmbarazoEXL.txtPAPna.setText("");
                }
                if ((r.getString(55)).equals("S") ){
                RegistroEmbarazoEXL.txtPAPn.setText("");
                RegistroEmbarazoEXL.txtPAPa.setText("");
                RegistroEmbarazoEXL.txtPAPnh.setText("");
                RegistroEmbarazoEXL.txtPAPna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(56).equals("")){
                        RegistroEmbarazoEXL.fechaPAP.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(56));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaPAP.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                ////////////////////////////////////////////////////////////IVAA
                if ((r.getString(57)).equals("N") ){
                RegistroEmbarazoEXL.txtIVAAn.setText("X");
                RegistroEmbarazoEXL.txtIVAAa.setText("");
                RegistroEmbarazoEXL.txtIVAAnh.setText("");
                RegistroEmbarazoEXL.txtIVAAna.setText("");
                }
                if ((r.getString(57)).equals("A") ){
                RegistroEmbarazoEXL.txtIVAAn.setText("");
                RegistroEmbarazoEXL.txtIVAAa.setText("X");
                RegistroEmbarazoEXL.txtIVAAnh.setText("");
                RegistroEmbarazoEXL.txtIVAAna.setText("");
                }
                if ((r.getString(57)).equals("H") ){
                RegistroEmbarazoEXL.txtIVAAn.setText("");
                RegistroEmbarazoEXL.txtIVAAa.setText("");
                RegistroEmbarazoEXL.txtIVAAnh.setText("X");
                RegistroEmbarazoEXL.txtIVAAna.setText("");
                }
                if ((r.getString(57)).equals("S") ){
                RegistroEmbarazoEXL.txtIVAAn.setText("");
                RegistroEmbarazoEXL.txtIVAAa.setText("");
                RegistroEmbarazoEXL.txtIVAAnh.setText("");
                RegistroEmbarazoEXL.txtIVAAna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(58).equals("")){
                        RegistroEmbarazoEXL.fechaIVAA.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(58));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaIVAA.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                
                /////////////////////////////////////////////////////Colposcopia
                if ((r.getString(59)).equals("N") ){
                RegistroEmbarazoEXL.txtColn.setText("X");
                RegistroEmbarazoEXL.txtCola.setText("");
                RegistroEmbarazoEXL.txtColnh.setText("");
                RegistroEmbarazoEXL.txtColna.setText("");
                }
                if ((r.getString(59)).equals("A") ){
                RegistroEmbarazoEXL.txtColn.setText("");
                RegistroEmbarazoEXL.txtCola.setText("X");
                RegistroEmbarazoEXL.txtColnh.setText("");
                RegistroEmbarazoEXL.txtColna.setText("");
                }
                if ((r.getString(59)).equals("H") ){
                RegistroEmbarazoEXL.txtColn.setText("");
                RegistroEmbarazoEXL.txtCola.setText("");
                RegistroEmbarazoEXL.txtColnh.setText("X");
                RegistroEmbarazoEXL.txtColna.setText("");
                }
                if ((r.getString(59)).equals("S") ){
                RegistroEmbarazoEXL.txtColn.setText("");
                RegistroEmbarazoEXL.txtCola.setText("");
                RegistroEmbarazoEXL.txtColnh.setText("");
                RegistroEmbarazoEXL.txtColna.setText("X");
                }

                    //////FECHA
                try {
                    if(r.getString(60).equals("")){
                        RegistroEmbarazoEXL.fechaCol.setDate(null);
                    } else {
                        String fechaSeleccionadaBK = (String)(r.getString(60));
                        DateFormat dfoBK = new SimpleDateFormat("dd/MM/yyyy");
                        Date fechaBK   = dfoBK  .parse(fechaSeleccionadaBK);
                        RegistroEmbarazoEXL.fechaCol.setDate(fechaBK);

                    }
                } catch (Exception e) {
                }
                 
            }
            //
        } catch (Exception e) {
            System.out.println("Error: Consultorios EL  " + e.getMessage());
        }
    } 
    
    public ConsultorioExtCarnetPerinatalEl() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public ConsultorioExtCarnetPerinatalEl(int elId) {
        this.elId = elId;
    }

    public int getElId() {
        return elId;
    }

    public void setElId(int elId) {
        this.elId = elId;
    }

    public String getElHg1() {
        return elHg1;
    }

    public void setElHg1(String elHg1) {
        this.elHg1 = elHg1;
    }

    public String getElHg1Fecha() {
        return elHg1Fecha;
    }

    public void setElHg1Fecha(String elHg1Fecha) {
        this.elHg1Fecha = elHg1Fecha;
    }

    public String getElHg2() {
        return elHg2;
    }

    public void setElHg2(String elHg2) {
        this.elHg2 = elHg2;
    }

    public String getElHg2Fecha() {
        return elHg2Fecha;
    }

    public void setElHg2Fecha(String elHg2Fecha) {
        this.elHg2Fecha = elHg2Fecha;
    }

    public String getElHga() {
        return elHga;
    }

    public void setElHga(String elHga) {
        this.elHga = elHga;
    }

    public String getElHgaFecha() {
        return elHgaFecha;
    }

    public void setElHgaFecha(String elHgaFecha) {
        this.elHgaFecha = elHgaFecha;
    }

    public String getElGli1() {
        return elGli1;
    }

    public void setElGli1(String elGli1) {
        this.elGli1 = elGli1;
    }

    public String getElGli1Fecha() {
        return elGli1Fecha;
    }

    public void setElGli1Fecha(String elGli1Fecha) {
        this.elGli1Fecha = elGli1Fecha;
    }

    public String getElGli2() {
        return elGli2;
    }

    public void setElGli2(String elGli2) {
        this.elGli2 = elGli2;
    }

    public String getElGli2Fecha() {
        return elGli2Fecha;
    }

    public void setElGli2Fecha(String elGli2Fecha) {
        this.elGli2Fecha = elGli2Fecha;
    }

    public String getElGliTg() {
        return elGliTg;
    }

    public void setElGliTg(String elGliTg) {
        this.elGliTg = elGliTg;
    }

    public String getElGliTgFecha() {
        return elGliTgFecha;
    }

    public void setElGliTgFecha(String elGliTgFecha) {
        this.elGliTgFecha = elGliTgFecha;
    }

    public String getElVdrl1() {
        return elVdrl1;
    }

    public void setElVdrl1(String elVdrl1) {
        this.elVdrl1 = elVdrl1;
    }

    public String getElVdrl1Fecha() {
        return elVdrl1Fecha;
    }

    public void setElVdrl1Fecha(String elVdrl1Fecha) {
        this.elVdrl1Fecha = elVdrl1Fecha;
    }

    public String getElVdrl2() {
        return elVdrl2;
    }

    public void setElVdrl2(String elVdrl2) {
        this.elVdrl2 = elVdrl2;
    }

    public String getElVdrl2Fecha() {
        return elVdrl2Fecha;
    }

    public void setElVdrl2Fecha(String elVdrl2Fecha) {
        this.elVdrl2Fecha = elVdrl2Fecha;
    }

    public String getElFta() {
        return elFta;
    }

    public void setElFta(String elFta) {
        this.elFta = elFta;
    }

    public String getElFtaFecha() {
        return elFtaFecha;
    }

    public void setElFtaFecha(String elFtaFecha) {
        this.elFtaFecha = elFtaFecha;
    }

    public String getElThpa() {
        return elThpa;
    }

    public void setElThpa(String elThpa) {
        this.elThpa = elThpa;
    }

    public String getElThpaFecha() {
        return elThpaFecha;
    }

    public void setElThpaFecha(String elThpaFecha) {
        this.elThpaFecha = elThpaFecha;
    }

    public String getElPrs() {
        return elPrs;
    }

    public void setElPrs(String elPrs) {
        this.elPrs = elPrs;
    }

    public String getElPrsFecha() {
        return elPrsFecha;
    }

    public void setElPrsFecha(String elPrsFecha) {
        this.elPrsFecha = elPrsFecha;
    }

    public String getElVpr() {
        return elVpr;
    }

    public void setElVpr(String elVpr) {
        this.elVpr = elVpr;
    }

    public String getElVprFecha() {
        return elVprFecha;
    }

    public void setElVprFecha(String elVprFecha) {
        this.elVprFecha = elVprFecha;
    }

    public String getElPr2() {
        return elPr2;
    }

    public void setElPr2(String elPr2) {
        this.elPr2 = elPr2;
    }

    public String getElPr2Fecha() {
        return elPr2Fecha;
    }

    public void setElPr2Fecha(String elPr2Fecha) {
        this.elPr2Fecha = elPr2Fecha;
    }

    public String getElElisa() {
        return elElisa;
    }

    public void setElElisa(String elElisa) {
        this.elElisa = elElisa;
    }

    public String getElElisaFecha() {
        return elElisaFecha;
    }

    public void setElElisaFecha(String elElisaFecha) {
        this.elElisaFecha = elElisaFecha;
    }

    public String getElFi() {
        return elFi;
    }

    public void setElFi(String elFi) {
        this.elFi = elFi;
    }

    public String getElFiFecha() {
        return elFiFecha;
    }

    public void setElFiFecha(String elFiFecha) {
        this.elFiFecha = elFiFecha;
    }

    public String getElHtlvi() {
        return elHtlvi;
    }

    public void setElHtlvi(String elHtlvi) {
        this.elHtlvi = elHtlvi;
    }

    public String getElHtlviFecha() {
        return elHtlviFecha;
    }

    public void setElHtlviFecha(String elHtlviFecha) {
        this.elHtlviFecha = elHtlviFecha;
    }

    public String getElTorch() {
        return elTorch;
    }

    public void setElTorch(String elTorch) {
        this.elTorch = elTorch;
    }

    public String getElTorchFecha() {
        return elTorchFecha;
    }

    public void setElTorchFecha(String elTorchFecha) {
        this.elTorchFecha = elTorchFecha;
    }

    public String getElGotaG() {
        return elGotaG;
    }

    public void setElGotaG(String elGotaG) {
        this.elGotaG = elGotaG;
    }

    public String getElGotaGFecha() {
        return elGotaGFecha;
    }

    public void setElGotaGFecha(String elGotaGFecha) {
        this.elGotaGFecha = elGotaGFecha;
    }

    public String getElMpr() {
        return elMpr;
    }

    public void setElMpr(String elMpr) {
        this.elMpr = elMpr;
    }

    public String getElMprFecha() {
        return elMprFecha;
    }

    public void setElMprFecha(String elMprFecha) {
        this.elMprFecha = elMprFecha;
    }

    public String getElFm() {
        return elFm;
    }

    public void setElFm(String elFm) {
        this.elFm = elFm;
    }

    public String getElFmFecha() {
        return elFmFecha;
    }

    public void setElFmFecha(String elFmFecha) {
        this.elFmFecha = elFmFecha;
    }

    public String getElEco() {
        return elEco;
    }

    public void setElEco(String elEco) {
        this.elEco = elEco;
    }

    public String getElEcoFecha() {
        return elEcoFecha;
    }

    public void setElEcoFecha(String elEcoFecha) {
        this.elEcoFecha = elEcoFecha;
    }

    public String getElLeuco() {
        return elLeuco;
    }

    public void setElLeuco(String elLeuco) {
        this.elLeuco = elLeuco;
    }

    public String getElLeucoFechar() {
        return elLeucoFechar;
    }

    public void setElLeucoFechar(String elLeucoFechar) {
        this.elLeucoFechar = elLeucoFechar;
    }

    public String getElNitrit() {
        return elNitrit;
    }

    public void setElNitrit(String elNitrit) {
        this.elNitrit = elNitrit;
    }

    public String getElNitritFecha() {
        return elNitritFecha;
    }

    public void setElNitritFecha(String elNitritFecha) {
        this.elNitritFecha = elNitritFecha;
    }

    public String getElUrocultiv() {
        return elUrocultiv;
    }

    public void setElUrocultiv(String elUrocultiv) {
        this.elUrocultiv = elUrocultiv;
    }

    public String getElUrocultivFecha() {
        return elUrocultivFecha;
    }

    public void setElUrocultivFecha(String elUrocultivFecha) {
        this.elUrocultivFecha = elUrocultivFecha;
    }

    public String getElBk() {
        return elBk;
    }

    public void setElBk(String elBk) {
        this.elBk = elBk;
    }

    public String getElBkFecha() {
        return elBkFecha;
    }

    public void setElBkFecha(String elBkFecha) {
        this.elBkFecha = elBkFecha;
    }

    public String getElLt() {
        return elLt;
    }

    public void setElLt(String elLt) {
        this.elLt = elLt;
    }

    public String getElLtFecha() {
        return elLtFecha;
    }

    public void setElLtFecha(String elLtFecha) {
        this.elLtFecha = elLtFecha;
    }

    public String getElHb() {
        return elHb;
    }

    public void setElHb(String elHb) {
        this.elHb = elHb;
    }

    public String getElHbFecha() {
        return elHbFecha;
    }

    public void setElHbFecha(String elHbFecha) {
        this.elHbFecha = elHbFecha;
    }

    public String getElPap() {
        return elPap;
    }

    public void setElPap(String elPap) {
        this.elPap = elPap;
    }

    public String getElPapFecha() {
        return elPapFecha;
    }

    public void setElPapFecha(String elPapFecha) {
        this.elPapFecha = elPapFecha;
    }

    public String getElIvaa() {
        return elIvaa;
    }

    public void setElIvaa(String elIvaa) {
        this.elIvaa = elIvaa;
    }

    public String getElIvaaFecha() {
        return elIvaaFecha;
    }

    public void setElIvaaFecha(String elIvaaFecha) {
        this.elIvaaFecha = elIvaaFecha;
    }

    public String getElColposcopia() {
        return elColposcopia;
    }

    public void setElColposcopia(String elColposcopia) {
        this.elColposcopia = elColposcopia;
    }

    public String getElColposcopiaFecha() {
        return elColposcopiaFecha;
    }

    public void setElColposcopiaFecha(String elColposcopiaFecha) {
        this.elColposcopiaFecha = elColposcopiaFecha;
    }

    public String getFechaActu() {
        return fechaActu;
    }

    public void setFechaActu(String fechaActu) {
        this.fechaActu = fechaActu;
    }

    public String getHoraActu() {
        return horaActu;
    }

    public void setHoraActu(String horaActu) {
        this.horaActu = horaActu;
    }

    public String getNomPc() {
        return nomPc;
    }

    public void setNomPc(String nomPc) {
        this.nomPc = nomPc;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    @Override
    public String toString() {
        return "modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEl[ elId=" + elId + " ]";
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
     * @return the cpId
     */
    public int getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    /**
     * @return the idActoMedico
     */
    public int getIdActoMedico() {
        return idActoMedico;
    }

    /**
     * @param idActoMedico the idActoMedico to set
     */
    public void setIdActoMedico(int idActoMedico) {
        this.idActoMedico = idActoMedico;
    }
    
}
