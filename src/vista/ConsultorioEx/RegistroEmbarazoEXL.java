/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEl;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static vista.ConsultorioEx.RegistroEmbarazoGA.btneditar;
import static vista.ConsultorioEx.RegistroEmbarazoPrincipal.lblId;
import static vista.ConsultorioEx.RegistroEmbarazoPrincipal.lblMadre;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoEXL extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoEXL() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        mensaje.setVisible(false);
        lblHepa.setVisible(false);
        txtHepatitisn.setVisible(false);
        txtHepatitisp.setVisible(false);
        txtHepatitisnh.setVisible(false);
        txtHepatitisna.setVisible(false);
        fechaHepatitis.setVisible(false);
        
        
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
 public String determinarFecha(JDateChooser calendario){
         
        String fecha = "";
        try {
        int dia = calendario.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes = calendario.getCalendar().get(Calendar.MONTH)+1;
        int anio = calendario.getCalendar().get(Calendar.YEAR); 
        
            if(dia < 10 && mes < 10){
            fecha = String.valueOf("0" + dia + "/" + "0" + mes + "/" + anio);
        }else 
            if(dia < 10 || mes < 10){
                if(dia < 10 && mes >=10){
                    fecha = String.valueOf("0" + dia + "/" + mes + "/" + anio);
                } else 
                    if(dia >= 10 && mes < 10){
                        fecha = String.valueOf(dia + "/" + "0" + mes + "/" + anio);
                    } 
            } else 
                fecha = String.valueOf(dia + "/" + mes + "/" + anio); 
         } catch (Exception e) {
                           mensaje.setVisible(true);
                           mensaje.setBackground(new Color(255,91,70)); 
                           men.setText("Ingrese una fecha correcta");
                           b.setVisible(false);
                           b1.setVisible(false); 
         }
        
        return fecha;
    }
 public void Guardar( ){
        
    ConsultorioExtCarnetPerinatalEl CXRsEXL= new ConsultorioExtCarnetPerinatalEl();
    ConsultorioExtCarnetPerinatalEl CXRsEXL1 = new ConsultorioExtCarnetPerinatalEl();
    
           if(lblMant.getText().equals("U"))
            CXRsEXL.setElId(Integer.parseInt(lblIdEx.getText()));
            CXRsEXL.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            if(chkH1.getText().equals("")){
            CXRsEXL.setElHg1(txtH1.getText());
            CXRsEXL.setElHg1Fecha(determinarFecha(dFechaH1));
            }
            if(chkH1.getText().equals("X")){
            CXRsEXL.setElHg1("");
            CXRsEXL.setElHg1Fecha("");
            }
            
            if(chkH2.getText().equals("")){
            CXRsEXL.setElHg2(txtH2.getText());
            CXRsEXL.setElHg2Fecha(determinarFecha(dFechaH2));
            }
            if(chkH2.getText().equals("X")){
            CXRsEXL.setElHg2("");
            CXRsEXL.setElHg2Fecha("");
            }
            
            if(chkH3.getText().equals("")){
            CXRsEXL.setElHga(txtH3.getText());
            CXRsEXL.setElHgaFecha(determinarFecha(dFechaH3));
            }
            if(chkH3.getText().equals("X")){
            CXRsEXL.setElHga("");
            CXRsEXL.setElHgaFecha("");
            }
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////GLICEMIA 1
            if(txtG1N.getText().equals("X")){
                CXRsEXL.setElGli1("N");
                CXRsEXL.setElGli1Fecha(determinarFecha(fechaG1));
            }else
            if(txtG1A.getText().equals("X")){
                CXRsEXL.setElGli1("A");
                CXRsEXL.setElGli1Fecha(determinarFecha(fechaG1));
            }else
         
            if(txtG1NA.getText().equals("X")){
                CXRsEXL.setElGli1("H");
                CXRsEXL.setElGli1Fecha("");
            }else
                CXRsEXL.setElGli1("");
                CXRsEXL.setElGli1Fecha("");
            //////////////////////////////////////////////////////////GLICEMIA 2
            if(txtG2N.getText().equals("X")){
                CXRsEXL.setElGli2("N");
                CXRsEXL.setElGli2Fecha(determinarFecha(fechaG2));
            }else
            if(txtG2A.getText().equals("X")){
                CXRsEXL.setElGli2("A");
                CXRsEXL.setElGli2Fecha(determinarFecha(fechaG2));
            }else
            if(txtG2NA.getText().equals("X")){
                CXRsEXL.setElGli2("H");
                CXRsEXL.setElGli2Fecha("");
            }else
                CXRsEXL.setElGli2("");
                CXRsEXL.setElGli2Fecha("");
            //////////////////////////////////////////////////////////GLICEMIA 2
            if(txtTGN.getText().equals("X")){
                CXRsEXL.setElGliTg("N");
                CXRsEXL.setElGliTgFecha(determinarFecha(fechaTG));
            }else
            if(txtTGA.getText().equals("X")){
                CXRsEXL.setElGliTg("A");
                CXRsEXL.setElGliTgFecha(determinarFecha(fechaTG));
            }else
            if(txtTGNH.getText().equals("X")){
                CXRsEXL.setElGliTg("H");
                CXRsEXL.setElGliTgFecha("");
            }else
            if(txtTGNA.getText().equals("X")){
                CXRsEXL.setElGliTg("S");
                CXRsEXL.setElGliTgFecha("");
            }
            else
                CXRsEXL.setElGliTg("");
                CXRsEXL.setElGliTgFecha("");  
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////VDRL 1
            if(txtVDRL1nr.getText().equals("X")){
                CXRsEXL.setElVdrl1("N");
                CXRsEXL.setElVdrl1Fecha(determinarFecha(fechaVDRL1));
            }else
            if(txtVDRL1a.getText().equals("X")){
                CXRsEXL.setElVdrl1("R");
                CXRsEXL.setElVdrl1Fecha(determinarFecha(fechaVDRL1));
            }else
            if(txtVDRL1nh.getText().equals("X")){
                CXRsEXL.setElVdrl1("H");
                CXRsEXL.setElVdrl1Fecha("");
            }else
                CXRsEXL.setElVdrl1("");
                CXRsEXL.setElVdrl1Fecha("");
            //////////////////////////////////////////////////////////////VDRL 2
            if(txtVDRL2nr.getText().equals("X")){
                CXRsEXL.setElVdrl2("N");
                CXRsEXL.setElVdrl2Fecha(determinarFecha(fechaVDRL2));
            }else
            if(txtVDRL2a.getText().equals("X")){
                CXRsEXL.setElVdrl2("R");
                CXRsEXL.setElVdrl2Fecha(determinarFecha(fechaVDRL2));
            }else
            if(txtVDRL2nh.getText().equals("X")){
                CXRsEXL.setElVdrl2("H");
                CXRsEXL.setElVdrl2Fecha("");
            }else
            if(txtVDRL2nh.getText().equals("X")){
                CXRsEXL.setElVdrl2("S");
                CXRsEXL.setElVdrl2Fecha("");
            }else
                CXRsEXL.setElVdrl2("");
                CXRsEXL.setElVdrl2Fecha("");
            
            /////////////////////////////////////////////////////////////////FTA
            if(txtFTAnr.getText().equals("X")){
                CXRsEXL.setElFta("N");
                CXRsEXL.setElFtaFecha(determinarFecha(fechaFTA));
            }else
            if(txtFTAr.getText().equals("X")){
                CXRsEXL.setElFta("R");
                CXRsEXL.setElFtaFecha(determinarFecha(fechaFTA));
            }else
            if(txtFTAnh.getText().equals("X")){
                CXRsEXL.setElFta("H");
                CXRsEXL.setElFtaFecha("");
            }else
            if(txtFTAna.getText().equals("X")){
                CXRsEXL.setElFta("S");
                CXRsEXL.setElFtaFecha("");
            }else
                CXRsEXL.setElFta("");
                CXRsEXL.setElFtaFecha("");
            ////////////////////////////////////////////////////////////////THPA
            if(txtTHPAnr.getText().equals("X")){
                CXRsEXL.setElThpa("N");
                CXRsEXL.setElThpaFecha(determinarFecha(fechaTHPA));
            }else
            if(txtTHPAr.getText().equals("X")){
                CXRsEXL.setElThpa("R");
                CXRsEXL.setElThpaFecha(determinarFecha(fechaTHPA));
            }else
            if(txtTHPAnh.getText().equals("X")){
                CXRsEXL.setElThpa("H");
                CXRsEXL.setElThpaFecha("");
            }else
            if(txtTHPAna.getText().equals("X")){
                CXRsEXL.setElThpa("S");
                CXRsEXL.setElThpaFecha("");
            }else
                CXRsEXL.setElThpa("");
                CXRsEXL.setElThpaFecha("");
            ////////////////////////////////////////////PRUEBA RAPIDA DE SIFILIS
            if(txtPruebanr.getText().equals("X")){
                CXRsEXL.setElPrs("N");
                CXRsEXL.setElPrsFecha(determinarFecha(fechaPrueba));
            }else
            if(txtPruebar.getText().equals("X")){
                CXRsEXL.setElPrs("R");
                CXRsEXL.setElPrsFecha(determinarFecha(fechaPrueba));
            }else
            if(txtPruebanh.getText().equals("X")){
                CXRsEXL.setElPrs("H");
                CXRsEXL.setElPrsFecha("");
            }else
                CXRsEXL.setElPrs("");
                CXRsEXL.setElPrsFecha("");
            ////////////////////////////////////////////////////////VIH PRUEBA 1
            if(txtVIH1nr.getText().equals("X")){
                CXRsEXL.setElVpr("N");
                CXRsEXL.setElVprFecha(determinarFecha(fechaVIH1));
            }else
            if(txtVIHr.getText().equals("X")){
                CXRsEXL.setElVpr("R");
                CXRsEXL.setElVprFecha(determinarFecha(fechaVIH1));
            }else
            if(txtVIHnh.getText().equals("X")){
                CXRsEXL.setElVpr("H");
                CXRsEXL.setElVprFecha("");
            }else
                CXRsEXL.setElVpr("");
                CXRsEXL.setElVprFecha("");
            ////////////////////////////////////////////////////////VIH PRUEBA 2
            if(txtVIH2nr.getText().equals("X")){
                CXRsEXL.setElPr2("N");
                CXRsEXL.setElPr2Fecha(determinarFecha(fechaVIH2));
            }else
            if(txtVIH2r.getText().equals("X")){
                CXRsEXL.setElPr2("R");
                CXRsEXL.setElPr2Fecha(determinarFecha(fechaVIH2));
            }else
            if(txtVIH2nh.getText().equals("X")){
                CXRsEXL.setElPr2("H");
                CXRsEXL.setElPr2Fecha("");
            }else
            if(txtVIH2na.getText().equals("X")){
                CXRsEXL.setElPr2("S");
                CXRsEXL.setElPr2Fecha("");
            }else
                CXRsEXL.setElPr2("");
                CXRsEXL.setElPr2Fecha("");
            ///////////////////////////////////////////////////////////////ELISA
            if(txtElisanr.getText().equals("X")){
                CXRsEXL.setElElisa("N");
                CXRsEXL.setElElisaFecha(determinarFecha(fechaELisa));
            }else
            if(txtElisar.getText().equals("X")){
                CXRsEXL.setElElisa("R");
                CXRsEXL.setElElisaFecha(determinarFecha(fechaELisa));
            }else
            if(txtElisanh.getText().equals("X")){
                CXRsEXL.setElElisa("H");
                CXRsEXL.setElElisaFecha("");
            }else
            if(txtElisana.getText().equals("X")){
                CXRsEXL.setElElisa("S");
                CXRsEXL.setElElisaFecha("");
            }else
                CXRsEXL.setElElisa("");
                CXRsEXL.setElElisaFecha("");
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////IFI
            if(txtIFIn.getText().equals("X")){
                CXRsEXL.setElFi("N");
                CXRsEXL.setElFiFecha(determinarFecha(fechaIFI));
            }else
            if(txtIFIp.getText().equals("X")){
                CXRsEXL.setElFi("P");
                CXRsEXL.setElFiFecha(determinarFecha(fechaIFI));
            }else
            if(txtIFInh.getText().equals("X")){
                CXRsEXL.setElFi("H");
                CXRsEXL.setElFiFecha("");
            }else
            if(txtIFIna.getText().equals("X")){
                CXRsEXL.setElFi("A");
                CXRsEXL.setElFiFecha("");
            }else
                CXRsEXL.setElFi("");
                CXRsEXL.setElFiFecha("");
            ///////////////////////////////////////////////////////////////HTLVI
            if(txtHTLVLn.getText().equals("X")){
                CXRsEXL.setElHtlvi("N");
                CXRsEXL.setElHtlviFecha(determinarFecha(fechaHTLVI));
            }else
            if(txtHTLVLp.getText().equals("X")){
                CXRsEXL.setElHtlvi("P");
                CXRsEXL.setElHtlviFecha(determinarFecha(fechaHTLVI));
            }else
            if(txtHTLVLnh.getText().equals("X")){
                CXRsEXL.setElHtlvi("H");
                CXRsEXL.setElHtlviFecha("");
            }else
            if(txtHTLVLna.getText().equals("X")){
                CXRsEXL.setElHtlvi("A");
                CXRsEXL.setElHtlviFecha("");
            }else
                CXRsEXL.setElHtlvi("");
                CXRsEXL.setElHtlviFecha("");
            ///////////////////////////////////////////////////////////////TORCH
            if(txtTORCHn.getText().equals("X")){
                CXRsEXL.setElTorch("N");
                CXRsEXL.setElTorchFecha(determinarFecha(fechaTORCH));
            }else
            if(txtTORCHp.getText().equals("X")){
                CXRsEXL.setElTorch("P");
                CXRsEXL.setElTorchFecha(determinarFecha(fechaTORCH));
            }else
            if(txtTORCHnh.getText().equals("X")){
                CXRsEXL.setElTorch("H");
                CXRsEXL.setElTorchFecha("");
            }else
            if(txtTORCHna.getText().equals("X")){
                CXRsEXL.setElTorch("A");
                CXRsEXL.setElTorchFecha("");
            }else
                CXRsEXL.setElTorch("");
                CXRsEXL.setElTorchFecha("");
            ////////////////////////////////////////////////////////////////GOTA
            if(txtGotan.getText().equals("X")){
                CXRsEXL.setElGotaG("N");
                CXRsEXL.setElGotaGFecha(determinarFecha(fechaGota));
            }else
            if(txtGotap.getText().equals("X")){
                CXRsEXL.setElGotaG("P");
                CXRsEXL.setElGotaGFecha(determinarFecha(fechaGota));
            }else
            if(txtGotanh.getText().equals("X")){
                CXRsEXL.setElGotaG("H");
                CXRsEXL.setElGotaGFecha("");
            }else
            if(txtGotna.getText().equals("X")){
                CXRsEXL.setElGotaG("A");
                CXRsEXL.setElGotaGFecha("");
            }else
                CXRsEXL.setElGotaG("");
                CXRsEXL.setElGotaGFecha("");
            /////////////////////////////////////////////////////////////MALARIA
            if(txtMalarian.getText().equals("X")){
                CXRsEXL.setElMpr("N");
                CXRsEXL.setElMprFecha(determinarFecha(fechaMalaria));
            }else
            if(txtMalariap.getText().equals("X")){
                CXRsEXL.setElMpr("P");
                CXRsEXL.setElMprFecha(determinarFecha(fechaMalaria));
            }else
            if(txtMalarianh.getText().equals("X")){
                CXRsEXL.setElMpr("H");
                CXRsEXL.setElMprFecha("");
            }else
            if(txtMalariana.getText().equals("X")){
                CXRsEXL.setElMpr("A");
                CXRsEXL.setElMprFecha("");
            }else
                CXRsEXL.setElMpr("");
                CXRsEXL.setElMprFecha("");
            ////////////////////////////////////////////////////Fluorec. Malaria
            if(txtFluorn.getText().equals("X")){
                CXRsEXL.setElFm("N");
                CXRsEXL.setElFmFecha(determinarFecha(fechaFluor));
            }else
            if(txtFluorp.getText().equals("X")){
                CXRsEXL.setElFm("P");
                CXRsEXL.setElFmFecha(determinarFecha(fechaFluor));
            }else
            if(txtFluornh.getText().equals("X")){
                CXRsEXL.setElFm("H");
                CXRsEXL.setElFmFecha("");
            }else
            if(txtFluorna.getText().equals("X")){
                CXRsEXL.setElFm("A");
                CXRsEXL.setElFmFecha("");
            }else
                CXRsEXL.setElFm("");
                CXRsEXL.setElFmFecha("");
            ////////////////////////////////////////////////////Ex - Comp. Orina
            if(txtEXn.getText().equals("X")){
                CXRsEXL.setElEco("N");
                CXRsEXL.setElEcoFecha(determinarFecha(fechaEX));
            }else
            if(txtEXp.getText().equals("X")){
                CXRsEXL.setElEco("P");
                CXRsEXL.setElEcoFecha(determinarFecha(fechaEX));
            }else
            if(txtEXnh.getText().equals("X")){
                CXRsEXL.setElEco("H");
                CXRsEXL.setElEcoFecha("");
            }else
                CXRsEXL.setElEco("");
                CXRsEXL.setElEcoFecha(""); 
            ////////////////////////////////////////////////////Ex - Comp. Orina
            if(txtEXn.getText().equals("X")){
                CXRsEXL.setElEco("N");
                CXRsEXL.setElEcoFecha(determinarFecha(fechaEX));
            }else
            if(txtEXp.getText().equals("X")){
                CXRsEXL.setElEco("P");
                CXRsEXL.setElEcoFecha(determinarFecha(fechaEX));
            }else
            if(txtEXnh.getText().equals("X")){
                CXRsEXL.setElEco("H");
                CXRsEXL.setElEcoFecha("");
            }else
                CXRsEXL.setElEco("");
                CXRsEXL.setElEcoFecha("");
            ////////////////////////////////////////////////////////Leucocituria
            if(txtLEUn.getText().equals("X")){
                CXRsEXL.setElLeuco("N");
                CXRsEXL.setElLeucoFechar(determinarFecha(fechaLEU));
            }else
            if(txtLEUp.getText().equals("X")){
                CXRsEXL.setElLeuco("P");
                CXRsEXL.setElLeucoFechar(determinarFecha(fechaLEU));
            }else
            if(txtLEUnh.getText().equals("X")){
                CXRsEXL.setElLeuco("H");
                CXRsEXL.setElLeucoFechar("");
            }else
                CXRsEXL.setElLeuco("");
                CXRsEXL.setElLeucoFechar("");
            ////////////////////////////////////////////////////////////Nitritos
            if(txtNitritosn.getText().equals("X")){
                CXRsEXL.setElNitrit("N");
                CXRsEXL.setElNitritFecha(determinarFecha(fechaNitritos));
            }else
            if(txtNitritosp.getText().equals("X")){
                CXRsEXL.setElNitrit("P");
                CXRsEXL.setElNitritFecha(determinarFecha(fechaNitritos));
            }else
            if(txtNitritosnh.getText().equals("X")){
                CXRsEXL.setElNitrit("H");
                CXRsEXL.setElNitritFecha("");
            }else
                CXRsEXL.setElNitrit("");
                CXRsEXL.setElNitritFecha("");
            //////////////////////////////////////////////////////////Urocultivo
            if(txtUrocultivon.getText().equals("X")){
                CXRsEXL.setElUrocultiv("N");
                CXRsEXL.setElUrocultivFecha(determinarFecha(fechaUrocultivo));
            }else
            if(txtUrocultivop.getText().equals("X")){
                CXRsEXL.setElUrocultiv("P");
                CXRsEXL.setElUrocultivFecha(determinarFecha(fechaUrocultivo));
            }else
            if(txtUrocultivonh.getText().equals("X")){
                CXRsEXL.setElUrocultiv("H");
                CXRsEXL.setElUrocultivFecha("");
            }else
            if(txtUrocultivona.getText().equals("X")){
                CXRsEXL.setElUrocultiv("S");
                CXRsEXL.setElUrocultivFecha("");
            }else
                CXRsEXL.setElUrocultiv("");
                CXRsEXL.setElUrocultivFecha("");
            ////////////////////////////////////////////////////////BK en Esputo
            if(txtBKn.getText().equals("X")){
                CXRsEXL.setElBk("N");
                CXRsEXL.setElBkFecha(determinarFecha(fechaBK));
            }else
            if(txtBKp.getText().equals("X")){
                CXRsEXL.setElBk("P");
                CXRsEXL.setElBkFecha(determinarFecha(fechaBK));
            }else
            if(txtBKnh.getText().equals("X")){
                CXRsEXL.setElBk("H");
                CXRsEXL.setElBkFecha("");
            }else
            if(txtBKna.getText().equals("X")){
                CXRsEXL.setElBk("S");
                CXRsEXL.setElBkFecha("");
            }else
                CXRsEXL.setElBk("");
                CXRsEXL.setElBkFecha("");
            ////////////////////////////////////////////////////////////Listeria
            if(txtListerian.getText().equals("X")){
                CXRsEXL.setElLt("N");
                CXRsEXL.setElLtFecha(determinarFecha(fechaListeria));
            }else
            if(txtListeriap.getText().equals("X")){
                CXRsEXL.setElLt("P");
                CXRsEXL.setElLtFecha(determinarFecha(fechaListeria));
            }else
            if(txtListerianh.getText().equals("X")){
                CXRsEXL.setElLt("H");
                CXRsEXL.setElLtFecha("");
            }else
            if(txtListeriana.getText().equals("X")){
                CXRsEXL.setElLt("S");
                CXRsEXL.setElLtFecha("");
            }else
                CXRsEXL.setElLt("");
                CXRsEXL.setElLtFecha("");
            ////////////////////////////////////////////////Tamizaje Hepatitis B
            if(txtTamizajen.getText().equals("X")){
                CXRsEXL.setElHb("N");
                CXRsEXL.setElHbFecha(determinarFecha(fechaTamizaje));
            }else
            if(txtTamizajep.getText().equals("X")){
                CXRsEXL.setElHb("P");
                CXRsEXL.setElHbFecha(determinarFecha(fechaTamizaje));
            }else
            if(txtTamizajenh.getText().equals("X")){
                CXRsEXL.setElHb("H");
                CXRsEXL.setElHbFecha("");
            }else
            if(txtTamizajena.getText().equals("X")){
                CXRsEXL.setElHb("S");
                CXRsEXL.setElHbFecha("");
            }else
                CXRsEXL.setElHb("");
                CXRsEXL.setElHbFecha("");
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////PAP
            if(txtPAPn.getText().equals("X")){
                CXRsEXL.setElPap("N");
                CXRsEXL.setElPapFecha(determinarFecha(fechaPAP));
            }else
            if(txtPAPa.getText().equals("X")){
                CXRsEXL.setElPap("P");
                CXRsEXL.setElPapFecha(determinarFecha(fechaPAP));
            }else
            if(txtPAPnh.getText().equals("X")){
                CXRsEXL.setElPap("H");
                CXRsEXL.setElPapFecha("");
            }else
            if(txtPAPna.getText().equals("X")){
                CXRsEXL.setElPap("S");
                CXRsEXL.setElPapFecha("");
            }else
                CXRsEXL.setElPap("");
                CXRsEXL.setElPapFecha("");
            ////////////////////////////////////////////////////////////////IVAA
            if(txtIVAAn.getText().equals("X")){
                CXRsEXL.setElIvaa("N");
                CXRsEXL.setElIvaaFecha(determinarFecha(fechaIVAA));
            }else
            if(txtIVAAa.getText().equals("X")){
                CXRsEXL.setElIvaa("P");
                CXRsEXL.setElIvaaFecha(determinarFecha(fechaIVAA));
            }else
            if(txtIVAAnh.getText().equals("X")){
                CXRsEXL.setElIvaa("H");
                CXRsEXL.setElIvaaFecha("");
            }else
            if(txtIVAAna.getText().equals("X")){
                CXRsEXL.setElIvaa("S");
                CXRsEXL.setElIvaaFecha("");
            }else
                CXRsEXL.setElIvaa("");
                CXRsEXL.setElIvaaFecha("");
            /////////////////////////////////////////////////////////COLPOSCOPIA
            if(txtColn.getText().equals("X")){
                CXRsEXL.setElColposcopia("N");
                CXRsEXL.setElColposcopiaFecha(determinarFecha(fechaCol));
            }else
            if(txtCola.getText().equals("X")){
                CXRsEXL.setElColposcopia("P");
                CXRsEXL.setElColposcopiaFecha(determinarFecha(fechaCol));
            }else
            if(txtColnh.getText().equals("X")){
                CXRsEXL.setElColposcopia("H");
                CXRsEXL.setElColposcopiaFecha("");
            }else
            if(txtColna.getText().equals("X")){
                CXRsEXL.setElColposcopia("S");
                CXRsEXL.setElColposcopiaFecha("");
            }else
                CXRsEXL.setElColposcopia("");
                CXRsEXL.setElColposcopiaFecha("");
                
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            CXRsEXL.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            CXRsEXL.setIdActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
            
                if(CXRsEXL.mantenimientoConsultorioExtCarnetPerinatalEl(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Guardados de forma correcta");
                    b1.setText("OK");
                    b1.setVisible(true);
                    b.setVisible(false);

                    btnGuardar1.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=1;
                    CXRsEXL.ConsultoriosExtEXLListar(RegistroEmbarazoPrincipal.lblId.getText());     
                    }
                    if (lblMant.getText().equals("U")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Actualizados de forma correcta");
                    b1.setText("OK");
                    b.setVisible(true);
                    b.setVisible(false);

                    btnGuardar1.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=9;
 
                    CXRsEXL.ConsultoriosExtEXLListar(RegistroEmbarazoPrincipal.lblId.getText());     
                    }
                    

//                    habilitarDatos(false);
                }else {

                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
                }  
             
  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        ChkAnalf2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ChkEdad2 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblMant = new javax.swing.JLabel();
        lblIdEx = new javax.swing.JLabel();
        var = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtH1 = new javax.swing.JTextField();
        chkH1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtH2 = new javax.swing.JTextField();
        chkH2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtH3 = new javax.swing.JTextField();
        chkH3 = new javax.swing.JTextField();
        dFechaH1 = new com.toedter.calendar.JDateChooser();
        dFechaH2 = new com.toedter.calendar.JDateChooser();
        dFechaH3 = new com.toedter.calendar.JDateChooser();
        jLabel55 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTGN = new javax.swing.JTextField();
        txtG1NA = new javax.swing.JTextField();
        txtG1A = new javax.swing.JTextField();
        txtG2NA = new javax.swing.JTextField();
        txtG2A = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTGA = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtTGNH = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtG1N = new javax.swing.JTextField();
        txtG2N = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fechaTG = new com.toedter.calendar.JDateChooser();
        fechaG2 = new com.toedter.calendar.JDateChooser();
        fechaG1 = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        txtTGNA = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        txtVDRL1a = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtVDRL1nr = new javax.swing.JTextField();
        txtVDRL1nh = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtVDRL2nr = new javax.swing.JTextField();
        txtVDRL2a = new javax.swing.JTextField();
        txtVDRL2nh = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtFTAnr = new javax.swing.JTextField();
        txtFTAr = new javax.swing.JTextField();
        txtFTAnh = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtTHPAnr = new javax.swing.JTextField();
        txtTHPAr = new javax.swing.JTextField();
        txtTHPAnh = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtPruebanr = new javax.swing.JTextField();
        txtPruebar = new javax.swing.JTextField();
        txtPruebanh = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtVIH1nr = new javax.swing.JTextField();
        txtVIHr = new javax.swing.JTextField();
        txtVIHnh = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtVIH2nr = new javax.swing.JTextField();
        txtVIH2r = new javax.swing.JTextField();
        txtVIH2nh = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtElisanr = new javax.swing.JTextField();
        txtElisar = new javax.swing.JTextField();
        txtElisanh = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtVDRL2na = new javax.swing.JTextField();
        txtFTAna = new javax.swing.JTextField();
        txtTHPAna = new javax.swing.JTextField();
        txtElisana = new javax.swing.JTextField();
        txtVIH2na = new javax.swing.JTextField();
        fechaVDRL1 = new com.toedter.calendar.JDateChooser();
        fechaVDRL2 = new com.toedter.calendar.JDateChooser();
        fechaFTA = new com.toedter.calendar.JDateChooser();
        fechaTHPA = new com.toedter.calendar.JDateChooser();
        fechaPrueba = new com.toedter.calendar.JDateChooser();
        fechaVIH1 = new com.toedter.calendar.JDateChooser();
        fechaVIH2 = new com.toedter.calendar.JDateChooser();
        fechaELisa = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        txtIFIp = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtIFIn = new javax.swing.JTextField();
        txtIFInh = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtHTLVLn = new javax.swing.JTextField();
        txtHTLVLp = new javax.swing.JTextField();
        txtHTLVLnh = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtTORCHn = new javax.swing.JTextField();
        txtTORCHp = new javax.swing.JTextField();
        txtTORCHnh = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        txtGotan = new javax.swing.JTextField();
        txtGotap = new javax.swing.JTextField();
        txtGotanh = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtMalarian = new javax.swing.JTextField();
        txtMalariap = new javax.swing.JTextField();
        txtMalarianh = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        txtFluorn = new javax.swing.JTextField();
        txtFluorp = new javax.swing.JTextField();
        txtFluornh = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtEXn = new javax.swing.JTextField();
        txtEXp = new javax.swing.JTextField();
        txtEXnh = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        txtLEUn = new javax.swing.JTextField();
        txtLEUp = new javax.swing.JTextField();
        txtLEUnh = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtHTLVLna = new javax.swing.JTextField();
        txtTORCHna = new javax.swing.JTextField();
        txtGotna = new javax.swing.JTextField();
        txtHepatitisna = new javax.swing.JTextField();
        txtBKna = new javax.swing.JTextField();
        fechaIFI = new com.toedter.calendar.JDateChooser();
        fechaHTLVI = new com.toedter.calendar.JDateChooser();
        fechaTORCH = new com.toedter.calendar.JDateChooser();
        fechaGota = new com.toedter.calendar.JDateChooser();
        fechaMalaria = new com.toedter.calendar.JDateChooser();
        fechaFluor = new com.toedter.calendar.JDateChooser();
        fechaEX = new com.toedter.calendar.JDateChooser();
        fechaLEU = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        lblHepa = new javax.swing.JLabel();
        txtNitritosn = new javax.swing.JTextField();
        txtUrocultivon = new javax.swing.JTextField();
        txtBKn = new javax.swing.JTextField();
        txtListerian = new javax.swing.JTextField();
        txtTamizajen = new javax.swing.JTextField();
        txtHepatitisn = new javax.swing.JTextField();
        txtNitritosp = new javax.swing.JTextField();
        txtUrocultivop = new javax.swing.JTextField();
        txtBKp = new javax.swing.JTextField();
        txtListeriap = new javax.swing.JTextField();
        txtTamizajep = new javax.swing.JTextField();
        txtHepatitisp = new javax.swing.JTextField();
        txtNitritosnh = new javax.swing.JTextField();
        txtUrocultivonh = new javax.swing.JTextField();
        txtBKnh = new javax.swing.JTextField();
        txtListerianh = new javax.swing.JTextField();
        txtTamizajenh = new javax.swing.JTextField();
        txtHepatitisnh = new javax.swing.JTextField();
        txtIFIna = new javax.swing.JTextField();
        txtMalariana = new javax.swing.JTextField();
        txtFluorna = new javax.swing.JTextField();
        txtTamizajena = new javax.swing.JTextField();
        txtListeriana = new javax.swing.JTextField();
        fechaHepatitis = new com.toedter.calendar.JDateChooser();
        fechaTamizaje = new com.toedter.calendar.JDateChooser();
        fechaListeria = new com.toedter.calendar.JDateChooser();
        fechaBK = new com.toedter.calendar.JDateChooser();
        fechaUrocultivo = new com.toedter.calendar.JDateChooser();
        fechaNitritos = new com.toedter.calendar.JDateChooser();
        txtUrocultivona = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        txtPAPa = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtPAPn = new javax.swing.JTextField();
        txtPAPnh = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        txtIVAAn = new javax.swing.JTextField();
        txtIVAAa = new javax.swing.JTextField();
        txtIVAAnh = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        txtColn = new javax.swing.JTextField();
        txtCola = new javax.swing.JTextField();
        txtColnh = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txtIVAAna = new javax.swing.JTextField();
        txtColna = new javax.swing.JTextField();
        fechaCol = new com.toedter.calendar.JDateChooser();
        fechaPAP = new com.toedter.calendar.JDateChooser();
        fechaIVAA = new com.toedter.calendar.JDateChooser();
        jLabel85 = new javax.swing.JLabel();
        txtPAPna = new javax.swing.JTextField();
        lblIdActoMedico = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();
        lblActoMedico2 = new javax.swing.JLabel();
        lblActoMedico3 = new javax.swing.JLabel();
        lblMadreEXL = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setMinimumSize(new java.awt.Dimension(900, 724));
        setPreferredSize(new java.awt.Dimension(1355, 744));
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        mensaje.setBackground(new java.awt.Color(33, 115, 70));

        men.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        men.setForeground(new java.awt.Color(255, 255, 255));
        men.setText("Desea Actualizar el Registro ?");

        b.setForeground(new java.awt.Color(240, 240, 240));
        b.setText("Si");
        b.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b.setContentAreaFilled(false);
        b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b.setIconTextGap(30);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });

        b1.setForeground(new java.awt.Color(240, 240, 240));
        b1.setText("No");
        b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        b1.setContentAreaFilled(false);
        b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b1.setIconTextGap(30);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
        mensaje.setLayout(mensajeLayout);
        mensajeLayout.setHorizontalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(men)
                .addGap(46, 46, 46)
                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mensajeLayout.setVerticalGroup(
            mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mensajeLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(men)
                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("<html>Exámenes de <br>Laboratorio</html>");

        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblusu.setText("Silvana");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnGuardar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar1.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setContentAreaFilled(false);
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar1.setIconTextGap(30);
        btnGuardar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 204, 204));
        jLabel28.setText("Leyenda");

        ChkAnalf2.setEditable(false);
        ChkAnalf2.setBackground(new java.awt.Color(255, 204, 51));
        ChkAnalf2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ChkAnalf2.setForeground(new java.awt.Color(102, 102, 102));
        ChkAnalf2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ChkAnalf2.setBorder(null);
        ChkAnalf2.setPreferredSize(new java.awt.Dimension(28, 28));
        ChkAnalf2.setSelectionColor(new java.awt.Color(255, 204, 51));
        ChkAnalf2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ChkAnalf2CaretUpdate(evt);
            }
        });
        ChkAnalf2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChkAnalf2MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("ALERTA");

        ChkEdad2.setEditable(false);
        ChkEdad2.setBackground(new java.awt.Color(255, 51, 51));
        ChkEdad2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ChkEdad2.setForeground(new java.awt.Color(255, 255, 255));
        ChkEdad2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ChkEdad2.setBorder(null);
        ChkEdad2.setPreferredSize(new java.awt.Dimension(28, 28));
        ChkEdad2.setSelectionColor(new java.awt.Color(255, 51, 51));
        ChkEdad2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ChkEdad2CaretUpdate(evt);
            }
        });
        ChkEdad2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChkEdad2MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Requiere Seguimiento Continuo");

        btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btneditar.setForeground(new java.awt.Color(240, 240, 240));
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btneditar.setMnemonic('N');
        btneditar.setText("Modificar");
        btneditar.setContentAreaFilled(false);
        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btneditar.setIconTextGap(30);
        btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        lblMant.setForeground(new java.awt.Color(102, 102, 102));
        lblMant.setText("I");

        var.setForeground(new java.awt.Color(102, 102, 102));
        var.setText("1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblusu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel33))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel38))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addComponent(lblMant)
                            .addGap(26, 26, 26)
                            .addComponent(lblIdEx, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(139, 139, 139)
                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(140, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMant)
                    .addComponent(lblIdEx, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnGuardar1)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(217, 217, 217)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(180, 180, 180)
                .addComponent(lblusu)
                .addGap(29, 29, 29))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(418, 418, 418)
                    .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(419, 419, 419)))
        );

        jScrollPane1.setBorder(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Hemoglobina 1");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Hg (%)");

        txtH1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtH1.setText(" ");

        chkH1.setEditable(false);
        chkH1.setBackground(new java.awt.Color(255, 204, 51));
        chkH1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkH1.setForeground(new java.awt.Color(102, 102, 102));
        chkH1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkH1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkH1.setPreferredSize(new java.awt.Dimension(18, 18));
        chkH1.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        chkH1.setSelectionColor(new java.awt.Color(255, 204, 51));
        chkH1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkH1CaretUpdate(evt);
            }
        });
        chkH1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkH1MouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("<html>No se<br>Hizo</br></html>");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Hemoglobina 2");

        txtH2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtH2.setText(" ");

        chkH2.setEditable(false);
        chkH2.setBackground(new java.awt.Color(255, 204, 51));
        chkH2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkH2.setForeground(new java.awt.Color(102, 102, 102));
        chkH2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkH2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkH2.setPreferredSize(new java.awt.Dimension(18, 18));
        chkH2.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        chkH2.setSelectionColor(new java.awt.Color(255, 204, 51));
        chkH2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkH2CaretUpdate(evt);
            }
        });
        chkH2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkH2MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Hemoglobina al Alta");

        txtH3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtH3.setText(" ");

        chkH3.setEditable(false);
        chkH3.setBackground(new java.awt.Color(255, 204, 51));
        chkH3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkH3.setForeground(new java.awt.Color(102, 102, 102));
        chkH3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkH3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkH3.setPreferredSize(new java.awt.Dimension(18, 18));
        chkH3.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        chkH3.setSelectionColor(new java.awt.Color(255, 204, 51));
        chkH3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkH3CaretUpdate(evt);
            }
        });
        chkH3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkH3MouseClicked(evt);
            }
        });

        dFechaH1.setBackground(new java.awt.Color(245, 245, 245));
        dFechaH1.setDateFormatString("dd-MM-yyyy");
        dFechaH1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        dFechaH2.setBackground(new java.awt.Color(245, 245, 245));
        dFechaH2.setDateFormatString("dd-MM-yyyy");
        dFechaH2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        dFechaH3.setBackground(new java.awt.Color(245, 245, 245));
        dFechaH3.setDateFormatString("dd-MM-yyyy");
        dFechaH3.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("Fecha");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(67, 67, 67)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtH1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtH2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtH3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(chkH3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dFechaH3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkH2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkH1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dFechaH1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dFechaH2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dFechaH1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkH2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dFechaH2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dFechaH3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtTGN.setEditable(false);
        txtTGN.setBackground(new java.awt.Color(255, 255, 255));
        txtTGN.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTGN.setForeground(new java.awt.Color(102, 102, 102));
        txtTGN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTGN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTGN.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTGN.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTGN.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTGN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTGNCaretUpdate(evt);
            }
        });
        txtTGN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTGNMouseClicked(evt);
            }
        });

        txtG1NA.setEditable(false);
        txtG1NA.setBackground(new java.awt.Color(255, 204, 51));
        txtG1NA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG1NA.setForeground(new java.awt.Color(102, 102, 102));
        txtG1NA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG1NA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG1NA.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG1NA.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG1NA.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtG1NA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG1NACaretUpdate(evt);
            }
        });
        txtG1NA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG1NAMouseClicked(evt);
            }
        });

        txtG1A.setEditable(false);
        txtG1A.setBackground(new java.awt.Color(255, 204, 51));
        txtG1A.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG1A.setForeground(new java.awt.Color(102, 102, 102));
        txtG1A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG1A.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG1A.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG1A.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG1A.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtG1A.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG1ACaretUpdate(evt);
            }
        });
        txtG1A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG1AMouseClicked(evt);
            }
        });

        txtG2NA.setEditable(false);
        txtG2NA.setBackground(new java.awt.Color(255, 204, 51));
        txtG2NA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG2NA.setForeground(new java.awt.Color(102, 102, 102));
        txtG2NA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG2NA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG2NA.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG2NA.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG2NA.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtG2NA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG2NACaretUpdate(evt);
            }
        });
        txtG2NA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG2NAMouseClicked(evt);
            }
        });

        txtG2A.setEditable(false);
        txtG2A.setBackground(new java.awt.Color(255, 204, 51));
        txtG2A.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG2A.setForeground(new java.awt.Color(102, 102, 102));
        txtG2A.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG2A.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG2A.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG2A.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG2A.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtG2A.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG2ACaretUpdate(evt);
            }
        });
        txtG2A.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG2AMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText(" Anormal");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Glicemia 1");

        txtTGA.setEditable(false);
        txtTGA.setBackground(new java.awt.Color(255, 204, 51));
        txtTGA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTGA.setForeground(new java.awt.Color(102, 102, 102));
        txtTGA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTGA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTGA.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTGA.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTGA.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTGA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTGACaretUpdate(evt);
            }
        });
        txtTGA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTGAMouseClicked(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("<html>No se<br>Hizo</br></html>");

        txtTGNH.setEditable(false);
        txtTGNH.setBackground(new java.awt.Color(255, 204, 51));
        txtTGNH.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTGNH.setForeground(new java.awt.Color(102, 102, 102));
        txtTGNH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTGNH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTGNH.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTGNH.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTGNH.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTGNH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTGNHCaretUpdate(evt);
            }
        });
        txtTGNH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTGNHMouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Normal");

        txtG1N.setEditable(false);
        txtG1N.setBackground(new java.awt.Color(255, 255, 255));
        txtG1N.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG1N.setForeground(new java.awt.Color(102, 102, 102));
        txtG1N.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG1N.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG1N.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG1N.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG1N.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtG1N.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG1NCaretUpdate(evt);
            }
        });
        txtG1N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG1NMouseClicked(evt);
            }
        });

        txtG2N.setEditable(false);
        txtG2N.setBackground(new java.awt.Color(255, 255, 255));
        txtG2N.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtG2N.setForeground(new java.awt.Color(102, 102, 102));
        txtG2N.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtG2N.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtG2N.setPreferredSize(new java.awt.Dimension(18, 18));
        txtG2N.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtG2N.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtG2N.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtG2NCaretUpdate(evt);
            }
        });
        txtG2N.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtG2NMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Glicemia 2");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Tolerancia Glucosa");

        fechaTG.setBackground(new java.awt.Color(245, 245, 245));
        fechaTG.setDateFormatString("dd-MM-yyyy");
        fechaTG.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaG2.setBackground(new java.awt.Color(245, 245, 245));
        fechaG2.setDateFormatString("dd-MM-yyyy");
        fechaG2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaG1.setBackground(new java.awt.Color(245, 245, 245));
        fechaG1.setDateFormatString("dd-MM-yyyy");
        fechaG1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("<html>No se<br>Aplica</br></html>");

        txtTGNA.setEditable(false);
        txtTGNA.setBackground(new java.awt.Color(255, 255, 255));
        txtTGNA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTGNA.setForeground(new java.awt.Color(102, 102, 102));
        txtTGNA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTGNA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTGNA.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTGNA.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTGNA.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTGNA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTGNACaretUpdate(evt);
            }
        });
        txtTGNA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTGNAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtG1N, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTGN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtG2N, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtG1A, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTGA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtG2A, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel48)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel49)))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtG1NA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTGNH, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtG2NA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaG2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaG1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTGNA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaTG, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtG1A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(fechaG2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaTG, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtG1N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtG2A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtG2N, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)
                                        .addComponent(txtG2NA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTGA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTGN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtTGNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTGNA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaG1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtG1NA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("<html>No <br>Reactivo</br></html>");

        txtVDRL1a.setEditable(false);
        txtVDRL1a.setBackground(new java.awt.Color(255, 204, 51));
        txtVDRL1a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL1a.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL1a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL1a.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL1a.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL1a.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL1a.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVDRL1a.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL1aCaretUpdate(evt);
            }
        });
        txtVDRL1a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL1aMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Reactivo");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("VDRL / RPR 1");

        txtVDRL1nr.setEditable(false);
        txtVDRL1nr.setBackground(new java.awt.Color(255, 255, 255));
        txtVDRL1nr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL1nr.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL1nr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL1nr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL1nr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL1nr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL1nr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVDRL1nr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL1nrCaretUpdate(evt);
            }
        });
        txtVDRL1nr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL1nrMouseClicked(evt);
            }
        });

        txtVDRL1nh.setEditable(false);
        txtVDRL1nh.setBackground(new java.awt.Color(255, 204, 51));
        txtVDRL1nh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL1nh.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL1nh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL1nh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL1nh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL1nh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL1nh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVDRL1nh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL1nhCaretUpdate(evt);
            }
        });
        txtVDRL1nh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL1nhMouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("<html>No se<br>Hizo</br></html>");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("VDRL / RPR 2");

        txtVDRL2nr.setEditable(false);
        txtVDRL2nr.setBackground(new java.awt.Color(255, 255, 255));
        txtVDRL2nr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL2nr.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL2nr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL2nr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL2nr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL2nr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL2nr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVDRL2nr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL2nrCaretUpdate(evt);
            }
        });
        txtVDRL2nr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL2nrMouseClicked(evt);
            }
        });

        txtVDRL2a.setEditable(false);
        txtVDRL2a.setBackground(new java.awt.Color(255, 204, 51));
        txtVDRL2a.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL2a.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL2a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL2a.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL2a.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL2a.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL2a.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVDRL2a.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL2aCaretUpdate(evt);
            }
        });
        txtVDRL2a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL2aMouseClicked(evt);
            }
        });

        txtVDRL2nh.setEditable(false);
        txtVDRL2nh.setBackground(new java.awt.Color(255, 204, 51));
        txtVDRL2nh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL2nh.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL2nh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL2nh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL2nh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL2nh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL2nh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVDRL2nh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL2nhCaretUpdate(evt);
            }
        });
        txtVDRL2nh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL2nhMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("FTA Abs");

        txtFTAnr.setEditable(false);
        txtFTAnr.setBackground(new java.awt.Color(255, 255, 255));
        txtFTAnr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFTAnr.setForeground(new java.awt.Color(102, 102, 102));
        txtFTAnr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFTAnr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFTAnr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFTAnr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFTAnr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtFTAnr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFTAnrCaretUpdate(evt);
            }
        });
        txtFTAnr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFTAnrMouseClicked(evt);
            }
        });

        txtFTAr.setEditable(false);
        txtFTAr.setBackground(new java.awt.Color(255, 204, 51));
        txtFTAr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFTAr.setForeground(new java.awt.Color(102, 102, 102));
        txtFTAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFTAr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFTAr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFTAr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFTAr.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFTAr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFTArCaretUpdate(evt);
            }
        });
        txtFTAr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFTArMouseClicked(evt);
            }
        });

        txtFTAnh.setEditable(false);
        txtFTAnh.setBackground(new java.awt.Color(255, 204, 51));
        txtFTAnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFTAnh.setForeground(new java.awt.Color(102, 102, 102));
        txtFTAnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFTAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFTAnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFTAnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFTAnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFTAnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFTAnhCaretUpdate(evt);
            }
        });
        txtFTAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFTAnhMouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("THPA");

        txtTHPAnr.setEditable(false);
        txtTHPAnr.setBackground(new java.awt.Color(255, 255, 255));
        txtTHPAnr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTHPAnr.setForeground(new java.awt.Color(102, 102, 102));
        txtTHPAnr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTHPAnr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTHPAnr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTHPAnr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTHPAnr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTHPAnr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTHPAnrCaretUpdate(evt);
            }
        });
        txtTHPAnr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTHPAnrMouseClicked(evt);
            }
        });

        txtTHPAr.setEditable(false);
        txtTHPAr.setBackground(new java.awt.Color(255, 204, 51));
        txtTHPAr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTHPAr.setForeground(new java.awt.Color(102, 102, 102));
        txtTHPAr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTHPAr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTHPAr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTHPAr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTHPAr.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTHPAr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTHPArCaretUpdate(evt);
            }
        });
        txtTHPAr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTHPArMouseClicked(evt);
            }
        });

        txtTHPAnh.setEditable(false);
        txtTHPAnh.setBackground(new java.awt.Color(255, 204, 51));
        txtTHPAnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTHPAnh.setForeground(new java.awt.Color(102, 102, 102));
        txtTHPAnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTHPAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTHPAnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTHPAnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTHPAnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTHPAnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTHPAnhCaretUpdate(evt);
            }
        });
        txtTHPAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTHPAnhMouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Prueba Rap. Sifilis");

        txtPruebanr.setEditable(false);
        txtPruebanr.setBackground(new java.awt.Color(255, 255, 255));
        txtPruebanr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPruebanr.setForeground(new java.awt.Color(102, 102, 102));
        txtPruebanr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPruebanr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPruebanr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPruebanr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPruebanr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtPruebanr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPruebanrCaretUpdate(evt);
            }
        });
        txtPruebanr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPruebanrMouseClicked(evt);
            }
        });

        txtPruebar.setEditable(false);
        txtPruebar.setBackground(new java.awt.Color(255, 204, 51));
        txtPruebar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPruebar.setForeground(new java.awt.Color(102, 102, 102));
        txtPruebar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPruebar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPruebar.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPruebar.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPruebar.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPruebar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPruebarCaretUpdate(evt);
            }
        });
        txtPruebar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPruebarMouseClicked(evt);
            }
        });

        txtPruebanh.setEditable(false);
        txtPruebanh.setBackground(new java.awt.Color(255, 204, 51));
        txtPruebanh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPruebanh.setForeground(new java.awt.Color(102, 102, 102));
        txtPruebanh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPruebanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPruebanh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPruebanh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPruebanh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPruebanh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPruebanhCaretUpdate(evt);
            }
        });
        txtPruebanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPruebanhMouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("VIH Prueba Ráp 1");

        txtVIH1nr.setEditable(false);
        txtVIH1nr.setBackground(new java.awt.Color(255, 255, 255));
        txtVIH1nr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIH1nr.setForeground(new java.awt.Color(102, 102, 102));
        txtVIH1nr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIH1nr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIH1nr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIH1nr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIH1nr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVIH1nr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIH1nrCaretUpdate(evt);
            }
        });
        txtVIH1nr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIH1nrMouseClicked(evt);
            }
        });

        txtVIHr.setEditable(false);
        txtVIHr.setBackground(new java.awt.Color(255, 204, 51));
        txtVIHr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIHr.setForeground(new java.awt.Color(102, 102, 102));
        txtVIHr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIHr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIHr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIHr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIHr.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVIHr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIHrCaretUpdate(evt);
            }
        });
        txtVIHr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIHrMouseClicked(evt);
            }
        });

        txtVIHnh.setEditable(false);
        txtVIHnh.setBackground(new java.awt.Color(255, 204, 51));
        txtVIHnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIHnh.setForeground(new java.awt.Color(102, 102, 102));
        txtVIHnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIHnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIHnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIHnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIHnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVIHnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIHnhCaretUpdate(evt);
            }
        });
        txtVIHnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIHnhMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Prueba Ráp 2");

        txtVIH2nr.setEditable(false);
        txtVIH2nr.setBackground(new java.awt.Color(255, 255, 255));
        txtVIH2nr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIH2nr.setForeground(new java.awt.Color(102, 102, 102));
        txtVIH2nr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIH2nr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIH2nr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIH2nr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIH2nr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVIH2nr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIH2nrCaretUpdate(evt);
            }
        });
        txtVIH2nr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIH2nrMouseClicked(evt);
            }
        });

        txtVIH2r.setEditable(false);
        txtVIH2r.setBackground(new java.awt.Color(255, 204, 51));
        txtVIH2r.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIH2r.setForeground(new java.awt.Color(102, 102, 102));
        txtVIH2r.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIH2r.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIH2r.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIH2r.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIH2r.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVIH2r.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIH2rCaretUpdate(evt);
            }
        });
        txtVIH2r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIH2rMouseClicked(evt);
            }
        });

        txtVIH2nh.setEditable(false);
        txtVIH2nh.setBackground(new java.awt.Color(255, 204, 51));
        txtVIH2nh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIH2nh.setForeground(new java.awt.Color(102, 102, 102));
        txtVIH2nh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIH2nh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIH2nh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIH2nh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIH2nh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtVIH2nh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIH2nhCaretUpdate(evt);
            }
        });
        txtVIH2nh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIH2nhMouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("ELISA");

        txtElisanr.setEditable(false);
        txtElisanr.setBackground(new java.awt.Color(255, 255, 255));
        txtElisanr.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtElisanr.setForeground(new java.awt.Color(102, 102, 102));
        txtElisanr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtElisanr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtElisanr.setPreferredSize(new java.awt.Dimension(18, 18));
        txtElisanr.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtElisanr.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtElisanr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtElisanrCaretUpdate(evt);
            }
        });
        txtElisanr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtElisanrMouseClicked(evt);
            }
        });

        txtElisar.setEditable(false);
        txtElisar.setBackground(new java.awt.Color(255, 204, 51));
        txtElisar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtElisar.setForeground(new java.awt.Color(102, 102, 102));
        txtElisar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtElisar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtElisar.setPreferredSize(new java.awt.Dimension(18, 18));
        txtElisar.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtElisar.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtElisar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtElisarCaretUpdate(evt);
            }
        });
        txtElisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtElisarMouseClicked(evt);
            }
        });
        txtElisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtElisarActionPerformed(evt);
            }
        });

        txtElisanh.setEditable(false);
        txtElisanh.setBackground(new java.awt.Color(255, 204, 51));
        txtElisanh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtElisanh.setForeground(new java.awt.Color(102, 102, 102));
        txtElisanh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtElisanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtElisanh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtElisanh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtElisanh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtElisanh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtElisanhCaretUpdate(evt);
            }
        });
        txtElisanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtElisanhMouseClicked(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("<html>No se<br>Aplica</br></html>");

        txtVDRL2na.setEditable(false);
        txtVDRL2na.setBackground(new java.awt.Color(255, 255, 255));
        txtVDRL2na.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVDRL2na.setForeground(new java.awt.Color(102, 102, 102));
        txtVDRL2na.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVDRL2na.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVDRL2na.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVDRL2na.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVDRL2na.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVDRL2na.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVDRL2naCaretUpdate(evt);
            }
        });
        txtVDRL2na.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVDRL2naMouseClicked(evt);
            }
        });

        txtFTAna.setEditable(false);
        txtFTAna.setBackground(new java.awt.Color(255, 255, 255));
        txtFTAna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFTAna.setForeground(new java.awt.Color(102, 102, 102));
        txtFTAna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFTAna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFTAna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFTAna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFTAna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtFTAna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFTAnaCaretUpdate(evt);
            }
        });
        txtFTAna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFTAnaMouseClicked(evt);
            }
        });

        txtTHPAna.setEditable(false);
        txtTHPAna.setBackground(new java.awt.Color(255, 255, 255));
        txtTHPAna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTHPAna.setForeground(new java.awt.Color(102, 102, 102));
        txtTHPAna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTHPAna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTHPAna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTHPAna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTHPAna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTHPAna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTHPAnaCaretUpdate(evt);
            }
        });
        txtTHPAna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTHPAnaMouseClicked(evt);
            }
        });

        txtElisana.setEditable(false);
        txtElisana.setBackground(new java.awt.Color(255, 255, 255));
        txtElisana.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtElisana.setForeground(new java.awt.Color(102, 102, 102));
        txtElisana.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtElisana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtElisana.setPreferredSize(new java.awt.Dimension(18, 18));
        txtElisana.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtElisana.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtElisana.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtElisanaCaretUpdate(evt);
            }
        });
        txtElisana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtElisanaMouseClicked(evt);
            }
        });

        txtVIH2na.setEditable(false);
        txtVIH2na.setBackground(new java.awt.Color(255, 255, 255));
        txtVIH2na.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtVIH2na.setForeground(new java.awt.Color(102, 102, 102));
        txtVIH2na.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVIH2na.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVIH2na.setPreferredSize(new java.awt.Dimension(18, 18));
        txtVIH2na.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtVIH2na.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVIH2na.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVIH2naCaretUpdate(evt);
            }
        });
        txtVIH2na.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVIH2naMouseClicked(evt);
            }
        });

        fechaVDRL1.setBackground(new java.awt.Color(245, 245, 245));
        fechaVDRL1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaVDRL1.setDateFormatString("dd-MM-yyyy");
        fechaVDRL1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaVDRL2.setBackground(new java.awt.Color(245, 245, 245));
        fechaVDRL2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaVDRL2.setDateFormatString("dd-MM-yyyy");
        fechaVDRL2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaFTA.setBackground(new java.awt.Color(245, 245, 245));
        fechaFTA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaFTA.setDateFormatString("dd-MM-yyyy");
        fechaFTA.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaTHPA.setBackground(new java.awt.Color(245, 245, 245));
        fechaTHPA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaTHPA.setDateFormatString("dd-MM-yyyy");
        fechaTHPA.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaPrueba.setBackground(new java.awt.Color(245, 245, 245));
        fechaPrueba.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaPrueba.setDateFormatString("dd-MM-yyyy");
        fechaPrueba.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaVIH1.setBackground(new java.awt.Color(245, 245, 245));
        fechaVIH1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaVIH1.setDateFormatString("dd-MM-yyyy");
        fechaVIH1.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaVIH2.setBackground(new java.awt.Color(245, 245, 245));
        fechaVIH2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaVIH2.setDateFormatString("dd-MM-yyyy");
        fechaVIH2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaELisa.setBackground(new java.awt.Color(245, 245, 245));
        fechaELisa.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaELisa.setDateFormatString("dd-MM-yyyy");
        fechaELisa.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(jLabel29)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVDRL1nr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL2nr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPruebanr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIH2nr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtVIH2r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPruebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVDRL2a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtVDRL1a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFTAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTHPAr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVIHr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtElisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtFTAnr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTHPAnr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVIH1nr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtElisanr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPruebanh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIHnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaVIH1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaVIH2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaELisa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaTHPA, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaVDRL2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaFTA, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL1nh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFTAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL2nh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTHPAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtElisanh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIH2nh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFTAna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVDRL2na, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTHPAna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtElisana, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVIH2na, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 141, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaVDRL1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVDRL1nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(txtVDRL1a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL1nh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(fechaVDRL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtVDRL2nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL2a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL2nh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVDRL2na, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtFTAnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFTAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFTAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFTAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(txtTHPAnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTHPAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTHPAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTHPAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(txtPruebanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPruebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPruebanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtVIH1nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIHr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIHnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(txtVIH2nr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIH2r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIH2nh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIH2na, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(txtElisanr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtElisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtElisanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtElisana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(fechaVDRL2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaFTA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaTHPA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaVIH1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaVIH2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaELisa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("Negativo");

        txtIFIp.setEditable(false);
        txtIFIp.setBackground(new java.awt.Color(255, 204, 51));
        txtIFIp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIFIp.setForeground(new java.awt.Color(102, 102, 102));
        txtIFIp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIFIp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIFIp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIFIp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIFIp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtIFIp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIFIpCaretUpdate(evt);
            }
        });
        txtIFIp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIFIpMouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("Positivo");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("IFI / Westerm Blot");

        txtIFIn.setEditable(false);
        txtIFIn.setBackground(new java.awt.Color(255, 255, 255));
        txtIFIn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIFIn.setForeground(new java.awt.Color(102, 102, 102));
        txtIFIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIFIn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIFIn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIFIn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIFIn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtIFIn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIFInCaretUpdate(evt);
            }
        });
        txtIFIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIFInMouseClicked(evt);
            }
        });

        txtIFInh.setEditable(false);
        txtIFInh.setBackground(new java.awt.Color(255, 204, 51));
        txtIFInh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIFInh.setForeground(new java.awt.Color(102, 102, 102));
        txtIFInh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIFInh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIFInh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIFInh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIFInh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtIFInh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIFInhCaretUpdate(evt);
            }
        });
        txtIFInh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIFInhMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("<html>No se<br>Hizo</br></html>");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("HTLVI");

        txtHTLVLn.setEditable(false);
        txtHTLVLn.setBackground(new java.awt.Color(255, 255, 255));
        txtHTLVLn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHTLVLn.setForeground(new java.awt.Color(102, 102, 102));
        txtHTLVLn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHTLVLn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHTLVLn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHTLVLn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHTLVLn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtHTLVLn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHTLVLnCaretUpdate(evt);
            }
        });
        txtHTLVLn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHTLVLnMouseClicked(evt);
            }
        });

        txtHTLVLp.setEditable(false);
        txtHTLVLp.setBackground(new java.awt.Color(255, 204, 51));
        txtHTLVLp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHTLVLp.setForeground(new java.awt.Color(102, 102, 102));
        txtHTLVLp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHTLVLp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHTLVLp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHTLVLp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHTLVLp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHTLVLp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHTLVLpCaretUpdate(evt);
            }
        });
        txtHTLVLp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHTLVLpMouseClicked(evt);
            }
        });

        txtHTLVLnh.setEditable(false);
        txtHTLVLnh.setBackground(new java.awt.Color(255, 204, 51));
        txtHTLVLnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHTLVLnh.setForeground(new java.awt.Color(102, 102, 102));
        txtHTLVLnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHTLVLnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHTLVLnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHTLVLnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHTLVLnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHTLVLnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHTLVLnhCaretUpdate(evt);
            }
        });
        txtHTLVLnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHTLVLnhMouseClicked(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("TORCH");

        txtTORCHn.setEditable(false);
        txtTORCHn.setBackground(new java.awt.Color(255, 255, 255));
        txtTORCHn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTORCHn.setForeground(new java.awt.Color(102, 102, 102));
        txtTORCHn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTORCHn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTORCHn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTORCHn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTORCHn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTORCHn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTORCHnCaretUpdate(evt);
            }
        });
        txtTORCHn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTORCHnMouseClicked(evt);
            }
        });

        txtTORCHp.setEditable(false);
        txtTORCHp.setBackground(new java.awt.Color(255, 204, 51));
        txtTORCHp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTORCHp.setForeground(new java.awt.Color(102, 102, 102));
        txtTORCHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTORCHp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTORCHp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTORCHp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTORCHp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTORCHp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTORCHpCaretUpdate(evt);
            }
        });
        txtTORCHp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTORCHpMouseClicked(evt);
            }
        });

        txtTORCHnh.setEditable(false);
        txtTORCHnh.setBackground(new java.awt.Color(255, 204, 51));
        txtTORCHnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTORCHnh.setForeground(new java.awt.Color(102, 102, 102));
        txtTORCHnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTORCHnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTORCHnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTORCHnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTORCHnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTORCHnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTORCHnhCaretUpdate(evt);
            }
        });
        txtTORCHnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTORCHnhMouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Gota Gruesa");

        txtGotan.setEditable(false);
        txtGotan.setBackground(new java.awt.Color(255, 255, 255));
        txtGotan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGotan.setForeground(new java.awt.Color(102, 102, 102));
        txtGotan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGotan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtGotan.setPreferredSize(new java.awt.Dimension(18, 18));
        txtGotan.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtGotan.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtGotan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGotanCaretUpdate(evt);
            }
        });
        txtGotan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGotanMouseClicked(evt);
            }
        });

        txtGotap.setEditable(false);
        txtGotap.setBackground(new java.awt.Color(255, 204, 51));
        txtGotap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGotap.setForeground(new java.awt.Color(102, 102, 102));
        txtGotap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGotap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtGotap.setPreferredSize(new java.awt.Dimension(18, 18));
        txtGotap.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtGotap.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtGotap.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGotapCaretUpdate(evt);
            }
        });
        txtGotap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGotapMouseClicked(evt);
            }
        });

        txtGotanh.setEditable(false);
        txtGotanh.setBackground(new java.awt.Color(255, 204, 51));
        txtGotanh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGotanh.setForeground(new java.awt.Color(102, 102, 102));
        txtGotanh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGotanh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtGotanh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtGotanh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtGotanh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtGotanh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGotanhCaretUpdate(evt);
            }
        });
        txtGotanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGotanhMouseClicked(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Malaria Prueba Ráp.");

        txtMalarian.setEditable(false);
        txtMalarian.setBackground(new java.awt.Color(255, 255, 255));
        txtMalarian.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMalarian.setForeground(new java.awt.Color(102, 102, 102));
        txtMalarian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMalarian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMalarian.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMalarian.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtMalarian.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtMalarian.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMalarianCaretUpdate(evt);
            }
        });
        txtMalarian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMalarianMouseClicked(evt);
            }
        });

        txtMalariap.setEditable(false);
        txtMalariap.setBackground(new java.awt.Color(255, 204, 51));
        txtMalariap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMalariap.setForeground(new java.awt.Color(102, 102, 102));
        txtMalariap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMalariap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMalariap.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMalariap.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtMalariap.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtMalariap.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMalariapCaretUpdate(evt);
            }
        });
        txtMalariap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMalariapMouseClicked(evt);
            }
        });

        txtMalarianh.setEditable(false);
        txtMalarianh.setBackground(new java.awt.Color(255, 204, 51));
        txtMalarianh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMalarianh.setForeground(new java.awt.Color(102, 102, 102));
        txtMalarianh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMalarianh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMalarianh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMalarianh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtMalarianh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtMalarianh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMalarianhCaretUpdate(evt);
            }
        });
        txtMalarianh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMalarianhMouseClicked(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Fluorec. Malaria");

        txtFluorn.setEditable(false);
        txtFluorn.setBackground(new java.awt.Color(255, 255, 255));
        txtFluorn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFluorn.setForeground(new java.awt.Color(102, 102, 102));
        txtFluorn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFluorn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFluorn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFluorn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFluorn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtFluorn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFluornCaretUpdate(evt);
            }
        });
        txtFluorn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFluornMouseClicked(evt);
            }
        });

        txtFluorp.setEditable(false);
        txtFluorp.setBackground(new java.awt.Color(255, 204, 51));
        txtFluorp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFluorp.setForeground(new java.awt.Color(102, 102, 102));
        txtFluorp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFluorp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFluorp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFluorp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFluorp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFluorp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFluorpCaretUpdate(evt);
            }
        });
        txtFluorp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFluorpMouseClicked(evt);
            }
        });

        txtFluornh.setEditable(false);
        txtFluornh.setBackground(new java.awt.Color(255, 204, 51));
        txtFluornh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFluornh.setForeground(new java.awt.Color(102, 102, 102));
        txtFluornh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFluornh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFluornh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFluornh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFluornh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFluornh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFluornhCaretUpdate(evt);
            }
        });
        txtFluornh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFluornhMouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setText("Ex - Comp. Orina");

        txtEXn.setEditable(false);
        txtEXn.setBackground(new java.awt.Color(255, 255, 255));
        txtEXn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEXn.setForeground(new java.awt.Color(102, 102, 102));
        txtEXn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEXn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEXn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtEXn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtEXn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtEXn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEXnCaretUpdate(evt);
            }
        });
        txtEXn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEXnMouseClicked(evt);
            }
        });

        txtEXp.setEditable(false);
        txtEXp.setBackground(new java.awt.Color(255, 204, 51));
        txtEXp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEXp.setForeground(new java.awt.Color(102, 102, 102));
        txtEXp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEXp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEXp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtEXp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtEXp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtEXp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEXpCaretUpdate(evt);
            }
        });
        txtEXp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEXpMouseClicked(evt);
            }
        });

        txtEXnh.setEditable(false);
        txtEXnh.setBackground(new java.awt.Color(255, 204, 51));
        txtEXnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEXnh.setForeground(new java.awt.Color(102, 102, 102));
        txtEXnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEXnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEXnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtEXnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtEXnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtEXnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEXnhCaretUpdate(evt);
            }
        });
        txtEXnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEXnhMouseClicked(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Leucocituria");

        txtLEUn.setEditable(false);
        txtLEUn.setBackground(new java.awt.Color(255, 255, 255));
        txtLEUn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLEUn.setForeground(new java.awt.Color(102, 102, 102));
        txtLEUn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLEUn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLEUn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLEUn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtLEUn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtLEUn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLEUnCaretUpdate(evt);
            }
        });
        txtLEUn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLEUnMouseClicked(evt);
            }
        });

        txtLEUp.setEditable(false);
        txtLEUp.setBackground(new java.awt.Color(255, 204, 51));
        txtLEUp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLEUp.setForeground(new java.awt.Color(102, 102, 102));
        txtLEUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLEUp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLEUp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLEUp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtLEUp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtLEUp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLEUpCaretUpdate(evt);
            }
        });
        txtLEUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLEUpMouseClicked(evt);
            }
        });

        txtLEUnh.setEditable(false);
        txtLEUnh.setBackground(new java.awt.Color(255, 204, 51));
        txtLEUnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLEUnh.setForeground(new java.awt.Color(102, 102, 102));
        txtLEUnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLEUnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLEUnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLEUnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtLEUnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtLEUnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLEUnhCaretUpdate(evt);
            }
        });
        txtLEUnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLEUnhMouseClicked(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("<html>No se<br>Aplica</br></html>");

        txtHTLVLna.setEditable(false);
        txtHTLVLna.setBackground(new java.awt.Color(255, 255, 255));
        txtHTLVLna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHTLVLna.setForeground(new java.awt.Color(102, 102, 102));
        txtHTLVLna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHTLVLna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHTLVLna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHTLVLna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHTLVLna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtHTLVLna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHTLVLnaCaretUpdate(evt);
            }
        });
        txtHTLVLna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHTLVLnaMouseClicked(evt);
            }
        });

        txtTORCHna.setEditable(false);
        txtTORCHna.setBackground(new java.awt.Color(255, 255, 255));
        txtTORCHna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTORCHna.setForeground(new java.awt.Color(102, 102, 102));
        txtTORCHna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTORCHna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTORCHna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTORCHna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTORCHna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTORCHna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTORCHnaCaretUpdate(evt);
            }
        });
        txtTORCHna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTORCHnaMouseClicked(evt);
            }
        });

        txtGotna.setEditable(false);
        txtGotna.setBackground(new java.awt.Color(255, 255, 255));
        txtGotna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGotna.setForeground(new java.awt.Color(102, 102, 102));
        txtGotna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGotna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtGotna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtGotna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtGotna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtGotna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGotnaCaretUpdate(evt);
            }
        });
        txtGotna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGotnaMouseClicked(evt);
            }
        });

        txtHepatitisna.setEditable(false);
        txtHepatitisna.setBackground(new java.awt.Color(255, 255, 255));
        txtHepatitisna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHepatitisna.setForeground(new java.awt.Color(102, 102, 102));
        txtHepatitisna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHepatitisna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHepatitisna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHepatitisna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHepatitisna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtHepatitisna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHepatitisnaCaretUpdate(evt);
            }
        });

        txtBKna.setEditable(false);
        txtBKna.setBackground(new java.awt.Color(255, 255, 255));
        txtBKna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBKna.setForeground(new java.awt.Color(102, 102, 102));
        txtBKna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBKna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtBKna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtBKna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtBKna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtBKna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBKnaCaretUpdate(evt);
            }
        });
        txtBKna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBKnaMouseClicked(evt);
            }
        });

        fechaIFI.setBackground(new java.awt.Color(245, 245, 245));
        fechaIFI.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaIFI.setDateFormatString("dd-MM-yyyy");
        fechaIFI.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaHTLVI.setBackground(new java.awt.Color(245, 245, 245));
        fechaHTLVI.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaHTLVI.setDateFormatString("dd-MM-yyyy");
        fechaHTLVI.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaTORCH.setBackground(new java.awt.Color(245, 245, 245));
        fechaTORCH.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaTORCH.setDateFormatString("dd-MM-yyyy");
        fechaTORCH.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaGota.setBackground(new java.awt.Color(245, 245, 245));
        fechaGota.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaGota.setDateFormatString("dd-MM-yyyy");
        fechaGota.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaMalaria.setBackground(new java.awt.Color(245, 245, 245));
        fechaMalaria.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaMalaria.setDateFormatString("dd-MM-yyyy");
        fechaMalaria.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaFluor.setBackground(new java.awt.Color(245, 245, 245));
        fechaFluor.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaFluor.setDateFormatString("dd-MM-yyyy");
        fechaFluor.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaEX.setBackground(new java.awt.Color(245, 245, 245));
        fechaEX.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaEX.setDateFormatString("dd-MM-yyyy");
        fechaEX.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaLEU.setBackground(new java.awt.Color(245, 245, 245));
        fechaLEU.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaLEU.setDateFormatString("dd-MM-yyyy");
        fechaLEU.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("Fecha");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("Nitritos");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jLabel69.setText("Urocultivo");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("BK en Esputo");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Listeria");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Tamizaje Hepatitis B");

        lblHepa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblHepa.setForeground(new java.awt.Color(51, 51, 51));
        lblHepa.setText("Hepatitis B");

        txtNitritosn.setEditable(false);
        txtNitritosn.setBackground(new java.awt.Color(255, 255, 255));
        txtNitritosn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNitritosn.setForeground(new java.awt.Color(102, 102, 102));
        txtNitritosn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNitritosn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNitritosn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNitritosn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtNitritosn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtNitritosn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNitritosnCaretUpdate(evt);
            }
        });
        txtNitritosn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNitritosnMouseClicked(evt);
            }
        });

        txtUrocultivon.setEditable(false);
        txtUrocultivon.setBackground(new java.awt.Color(255, 255, 255));
        txtUrocultivon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtUrocultivon.setForeground(new java.awt.Color(102, 102, 102));
        txtUrocultivon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUrocultivon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtUrocultivon.setPreferredSize(new java.awt.Dimension(18, 18));
        txtUrocultivon.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtUrocultivon.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtUrocultivon.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUrocultivonCaretUpdate(evt);
            }
        });
        txtUrocultivon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUrocultivonMouseClicked(evt);
            }
        });

        txtBKn.setEditable(false);
        txtBKn.setBackground(new java.awt.Color(255, 255, 255));
        txtBKn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBKn.setForeground(new java.awt.Color(102, 102, 102));
        txtBKn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBKn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtBKn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtBKn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtBKn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtBKn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBKnCaretUpdate(evt);
            }
        });
        txtBKn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBKnMouseClicked(evt);
            }
        });

        txtListerian.setEditable(false);
        txtListerian.setBackground(new java.awt.Color(255, 255, 255));
        txtListerian.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtListerian.setForeground(new java.awt.Color(102, 102, 102));
        txtListerian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtListerian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtListerian.setPreferredSize(new java.awt.Dimension(18, 18));
        txtListerian.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtListerian.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtListerian.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtListerianCaretUpdate(evt);
            }
        });
        txtListerian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtListerianMouseClicked(evt);
            }
        });

        txtTamizajen.setEditable(false);
        txtTamizajen.setBackground(new java.awt.Color(255, 255, 255));
        txtTamizajen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTamizajen.setForeground(new java.awt.Color(102, 102, 102));
        txtTamizajen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTamizajen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTamizajen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTamizajen.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTamizajen.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTamizajen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTamizajenCaretUpdate(evt);
            }
        });
        txtTamizajen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTamizajenMouseClicked(evt);
            }
        });

        txtHepatitisn.setEditable(false);
        txtHepatitisn.setBackground(new java.awt.Color(255, 255, 255));
        txtHepatitisn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHepatitisn.setForeground(new java.awt.Color(102, 102, 102));
        txtHepatitisn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHepatitisn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHepatitisn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHepatitisn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHepatitisn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtHepatitisn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHepatitisnCaretUpdate(evt);
            }
        });

        txtNitritosp.setEditable(false);
        txtNitritosp.setBackground(new java.awt.Color(255, 204, 51));
        txtNitritosp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNitritosp.setForeground(new java.awt.Color(102, 102, 102));
        txtNitritosp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNitritosp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNitritosp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNitritosp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtNitritosp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNitritosp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNitritospCaretUpdate(evt);
            }
        });
        txtNitritosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNitritospMouseClicked(evt);
            }
        });

        txtUrocultivop.setEditable(false);
        txtUrocultivop.setBackground(new java.awt.Color(255, 204, 51));
        txtUrocultivop.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtUrocultivop.setForeground(new java.awt.Color(102, 102, 102));
        txtUrocultivop.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUrocultivop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtUrocultivop.setPreferredSize(new java.awt.Dimension(18, 18));
        txtUrocultivop.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtUrocultivop.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtUrocultivop.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUrocultivopCaretUpdate(evt);
            }
        });
        txtUrocultivop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUrocultivopMouseClicked(evt);
            }
        });

        txtBKp.setEditable(false);
        txtBKp.setBackground(new java.awt.Color(255, 204, 51));
        txtBKp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBKp.setForeground(new java.awt.Color(102, 102, 102));
        txtBKp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBKp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtBKp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtBKp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtBKp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtBKp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBKpCaretUpdate(evt);
            }
        });
        txtBKp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBKpMouseClicked(evt);
            }
        });

        txtListeriap.setEditable(false);
        txtListeriap.setBackground(new java.awt.Color(255, 204, 51));
        txtListeriap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtListeriap.setForeground(new java.awt.Color(102, 102, 102));
        txtListeriap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtListeriap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtListeriap.setPreferredSize(new java.awt.Dimension(18, 18));
        txtListeriap.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtListeriap.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtListeriap.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtListeriapCaretUpdate(evt);
            }
        });
        txtListeriap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtListeriapMouseClicked(evt);
            }
        });

        txtTamizajep.setEditable(false);
        txtTamizajep.setBackground(new java.awt.Color(255, 204, 51));
        txtTamizajep.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTamizajep.setForeground(new java.awt.Color(102, 102, 102));
        txtTamizajep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTamizajep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTamizajep.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTamizajep.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTamizajep.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTamizajep.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTamizajepCaretUpdate(evt);
            }
        });
        txtTamizajep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTamizajepMouseClicked(evt);
            }
        });

        txtHepatitisp.setEditable(false);
        txtHepatitisp.setBackground(new java.awt.Color(255, 204, 51));
        txtHepatitisp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHepatitisp.setForeground(new java.awt.Color(102, 102, 102));
        txtHepatitisp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHepatitisp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHepatitisp.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHepatitisp.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHepatitisp.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHepatitisp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHepatitispCaretUpdate(evt);
            }
        });

        txtNitritosnh.setEditable(false);
        txtNitritosnh.setBackground(new java.awt.Color(255, 204, 51));
        txtNitritosnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNitritosnh.setForeground(new java.awt.Color(102, 102, 102));
        txtNitritosnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNitritosnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNitritosnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNitritosnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtNitritosnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNitritosnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNitritosnhCaretUpdate(evt);
            }
        });
        txtNitritosnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNitritosnhMouseClicked(evt);
            }
        });

        txtUrocultivonh.setEditable(false);
        txtUrocultivonh.setBackground(new java.awt.Color(255, 204, 51));
        txtUrocultivonh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtUrocultivonh.setForeground(new java.awt.Color(102, 102, 102));
        txtUrocultivonh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUrocultivonh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtUrocultivonh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtUrocultivonh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtUrocultivonh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtUrocultivonh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUrocultivonhCaretUpdate(evt);
            }
        });
        txtUrocultivonh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUrocultivonhMouseClicked(evt);
            }
        });

        txtBKnh.setEditable(false);
        txtBKnh.setBackground(new java.awt.Color(255, 204, 51));
        txtBKnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtBKnh.setForeground(new java.awt.Color(102, 102, 102));
        txtBKnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBKnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtBKnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtBKnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtBKnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtBKnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBKnhCaretUpdate(evt);
            }
        });
        txtBKnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBKnhMouseClicked(evt);
            }
        });

        txtListerianh.setEditable(false);
        txtListerianh.setBackground(new java.awt.Color(255, 204, 51));
        txtListerianh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtListerianh.setForeground(new java.awt.Color(102, 102, 102));
        txtListerianh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtListerianh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtListerianh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtListerianh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtListerianh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtListerianh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtListerianhCaretUpdate(evt);
            }
        });
        txtListerianh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtListerianhMouseClicked(evt);
            }
        });

        txtTamizajenh.setEditable(false);
        txtTamizajenh.setBackground(new java.awt.Color(255, 204, 51));
        txtTamizajenh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTamizajenh.setForeground(new java.awt.Color(102, 102, 102));
        txtTamizajenh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTamizajenh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTamizajenh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTamizajenh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTamizajenh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtTamizajenh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTamizajenhCaretUpdate(evt);
            }
        });
        txtTamizajenh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTamizajenhMouseClicked(evt);
            }
        });

        txtHepatitisnh.setEditable(false);
        txtHepatitisnh.setBackground(new java.awt.Color(255, 204, 51));
        txtHepatitisnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHepatitisnh.setForeground(new java.awt.Color(102, 102, 102));
        txtHepatitisnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHepatitisnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHepatitisnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHepatitisnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtHepatitisnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHepatitisnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHepatitisnhCaretUpdate(evt);
            }
        });

        txtIFIna.setEditable(false);
        txtIFIna.setBackground(new java.awt.Color(255, 255, 255));
        txtIFIna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIFIna.setForeground(new java.awt.Color(102, 102, 102));
        txtIFIna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIFIna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIFIna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIFIna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIFIna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtIFIna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIFInaCaretUpdate(evt);
            }
        });
        txtIFIna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIFInaMouseClicked(evt);
            }
        });

        txtMalariana.setEditable(false);
        txtMalariana.setBackground(new java.awt.Color(255, 255, 255));
        txtMalariana.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMalariana.setForeground(new java.awt.Color(102, 102, 102));
        txtMalariana.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMalariana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMalariana.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMalariana.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtMalariana.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtMalariana.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMalarianaCaretUpdate(evt);
            }
        });
        txtMalariana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMalarianaMouseClicked(evt);
            }
        });

        txtFluorna.setEditable(false);
        txtFluorna.setBackground(new java.awt.Color(255, 255, 255));
        txtFluorna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFluorna.setForeground(new java.awt.Color(102, 102, 102));
        txtFluorna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFluorna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFluorna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFluorna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtFluorna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtFluorna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFluornaCaretUpdate(evt);
            }
        });
        txtFluorna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFluornaMouseClicked(evt);
            }
        });

        txtTamizajena.setEditable(false);
        txtTamizajena.setBackground(new java.awt.Color(255, 255, 255));
        txtTamizajena.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTamizajena.setForeground(new java.awt.Color(102, 102, 102));
        txtTamizajena.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTamizajena.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTamizajena.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTamizajena.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtTamizajena.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtTamizajena.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTamizajenaCaretUpdate(evt);
            }
        });
        txtTamizajena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTamizajenaMouseClicked(evt);
            }
        });

        txtListeriana.setEditable(false);
        txtListeriana.setBackground(new java.awt.Color(255, 255, 255));
        txtListeriana.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtListeriana.setForeground(new java.awt.Color(102, 102, 102));
        txtListeriana.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtListeriana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtListeriana.setPreferredSize(new java.awt.Dimension(18, 18));
        txtListeriana.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtListeriana.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtListeriana.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtListerianaCaretUpdate(evt);
            }
        });
        txtListeriana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtListerianaMouseClicked(evt);
            }
        });

        fechaHepatitis.setBackground(new java.awt.Color(245, 245, 245));
        fechaHepatitis.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaHepatitis.setDateFormatString("dd-MM-yyyy");
        fechaHepatitis.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaTamizaje.setBackground(new java.awt.Color(245, 245, 245));
        fechaTamizaje.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaTamizaje.setDateFormatString("dd-MM-yyyy");
        fechaTamizaje.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaListeria.setBackground(new java.awt.Color(245, 245, 245));
        fechaListeria.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaListeria.setDateFormatString("dd-MM-yyyy");
        fechaListeria.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaBK.setBackground(new java.awt.Color(245, 245, 245));
        fechaBK.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaBK.setDateFormatString("dd-MM-yyyy");
        fechaBK.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaUrocultivo.setBackground(new java.awt.Color(245, 245, 245));
        fechaUrocultivo.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaUrocultivo.setDateFormatString("dd-MM-yyyy");
        fechaUrocultivo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaNitritos.setBackground(new java.awt.Color(245, 245, 245));
        fechaNitritos.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaNitritos.setDateFormatString("dd-MM-yyyy");
        fechaNitritos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        txtUrocultivona.setEditable(false);
        txtUrocultivona.setBackground(new java.awt.Color(255, 255, 255));
        txtUrocultivona.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtUrocultivona.setForeground(new java.awt.Color(102, 102, 102));
        txtUrocultivona.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUrocultivona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtUrocultivona.setPreferredSize(new java.awt.Dimension(18, 18));
        txtUrocultivona.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtUrocultivona.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtUrocultivona.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtUrocultivonaCaretUpdate(evt);
            }
        });
        txtUrocultivona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUrocultivonaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(jLabel59)
                            .addComponent(jLabel60)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTORCHn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGotan, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFluorn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLEUn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIFIn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHTLVLn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMalarian, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEXn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNitritosn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUrocultivon, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBKn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtListerian, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTamizajen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHepatitisn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHepatitisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTamizajep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtListeriap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBKp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUrocultivop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNitritosp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEXp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMalariap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHTLVLp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtIFIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTORCHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGotap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFluorp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtLEUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72)
                            .addComponent(lblHepa)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel56)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57)))
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIFInh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTORCHnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHTLVLnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGotanh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLEUnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEXnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMalarianh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFluornh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitritosnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUrocultivonh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBKnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtListerianh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTamizajenh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHepatitisnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHepatitisna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTamizajena, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtListeriana, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBKna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUrocultivona, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFluorna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMalariana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGotna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTORCHna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHTLVLna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIFIna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaTamizaje, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaHepatitis, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaListeria, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaBK, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaUrocultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaNitritos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaLEU, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaEX, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaFluor, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaMalaria, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaGota, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaTORCH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaHTLVI, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaIFI, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel67)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(jLabel57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaIFI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIFIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44)
                        .addComponent(txtIFIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIFInh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIFIna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(txtHTLVLn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHTLVLp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHTLVLnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHTLVLna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaHTLVI, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel60)
                        .addComponent(txtTORCHn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTORCHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTORCHnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTORCHna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaTORCH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel61)
                        .addComponent(txtGotan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGotap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGotanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGotna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaGota, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(txtMalarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMalariap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMalarianh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMalariana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaMalaria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(txtFluorn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFluorp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFluornh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFluorna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaFluor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(txtEXn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEXp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEXnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaEX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65)
                        .addComponent(txtLEUn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLEUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLEUnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaLEU, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel68)
                        .addComponent(txtNitritosn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNitritosp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNitritosnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaNitritos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel69)
                        .addComponent(txtUrocultivon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUrocultivop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUrocultivonh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUrocultivona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaUrocultivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel70)
                                .addComponent(txtBKn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBKp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBKnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBKna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(txtListerian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtListeriap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtListerianh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtListeriana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(txtTamizajen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTamizajep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTamizajenh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTamizajena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHepa)
                            .addComponent(txtHepatitisn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHepatitisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHepatitisnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHepatitisna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(fechaBK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaListeria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaTamizaje, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaHepatitis, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Negativo");

        txtPAPa.setEditable(false);
        txtPAPa.setBackground(new java.awt.Color(255, 204, 51));
        txtPAPa.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPAPa.setForeground(new java.awt.Color(102, 102, 102));
        txtPAPa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPAPa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPAPa.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPAPa.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPAPa.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPAPa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPAPaCaretUpdate(evt);
            }
        });
        txtPAPa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPAPaMouseClicked(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Anormal");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("PAP");

        txtPAPn.setEditable(false);
        txtPAPn.setBackground(new java.awt.Color(255, 255, 255));
        txtPAPn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPAPn.setForeground(new java.awt.Color(102, 102, 102));
        txtPAPn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPAPn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPAPn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPAPn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPAPn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtPAPn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPAPnCaretUpdate(evt);
            }
        });
        txtPAPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPAPnMouseClicked(evt);
            }
        });

        txtPAPnh.setEditable(false);
        txtPAPnh.setBackground(new java.awt.Color(255, 204, 51));
        txtPAPnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPAPnh.setForeground(new java.awt.Color(102, 102, 102));
        txtPAPnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPAPnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPAPnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPAPnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPAPnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPAPnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPAPnhCaretUpdate(evt);
            }
        });
        txtPAPnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPAPnhMouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("<html>No se<br>Hizo</br></html>");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
        jLabel77.setText("IVAA");

        txtIVAAn.setEditable(false);
        txtIVAAn.setBackground(new java.awt.Color(255, 255, 255));
        txtIVAAn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIVAAn.setForeground(new java.awt.Color(102, 102, 102));
        txtIVAAn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVAAn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIVAAn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIVAAn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIVAAn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtIVAAn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIVAAnCaretUpdate(evt);
            }
        });
        txtIVAAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIVAAnMouseClicked(evt);
            }
        });

        txtIVAAa.setEditable(false);
        txtIVAAa.setBackground(new java.awt.Color(255, 204, 51));
        txtIVAAa.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIVAAa.setForeground(new java.awt.Color(102, 102, 102));
        txtIVAAa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVAAa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIVAAa.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIVAAa.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIVAAa.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtIVAAa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIVAAaCaretUpdate(evt);
            }
        });
        txtIVAAa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIVAAaMouseClicked(evt);
            }
        });

        txtIVAAnh.setEditable(false);
        txtIVAAnh.setBackground(new java.awt.Color(255, 204, 51));
        txtIVAAnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIVAAnh.setForeground(new java.awt.Color(102, 102, 102));
        txtIVAAnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVAAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIVAAnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIVAAnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIVAAnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtIVAAnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIVAAnhCaretUpdate(evt);
            }
        });
        txtIVAAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIVAAnhMouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 51));
        jLabel78.setText("Colposcopia");

        txtColn.setEditable(false);
        txtColn.setBackground(new java.awt.Color(255, 255, 255));
        txtColn.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtColn.setForeground(new java.awt.Color(102, 102, 102));
        txtColn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtColn.setPreferredSize(new java.awt.Dimension(18, 18));
        txtColn.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtColn.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtColn.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtColnCaretUpdate(evt);
            }
        });
        txtColn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtColnMouseClicked(evt);
            }
        });

        txtCola.setEditable(false);
        txtCola.setBackground(new java.awt.Color(255, 204, 51));
        txtCola.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCola.setForeground(new java.awt.Color(102, 102, 102));
        txtCola.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCola.setPreferredSize(new java.awt.Dimension(18, 18));
        txtCola.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtCola.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtCola.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtColaCaretUpdate(evt);
            }
        });
        txtCola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtColaMouseClicked(evt);
            }
        });

        txtColnh.setEditable(false);
        txtColnh.setBackground(new java.awt.Color(255, 204, 51));
        txtColnh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtColnh.setForeground(new java.awt.Color(102, 102, 102));
        txtColnh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtColnh.setPreferredSize(new java.awt.Dimension(18, 18));
        txtColnh.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtColnh.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtColnh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtColnhCaretUpdate(evt);
            }
        });
        txtColnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtColnhMouseClicked(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("<html>No se<br>Aplica</br></html>");

        txtIVAAna.setEditable(false);
        txtIVAAna.setBackground(new java.awt.Color(255, 255, 255));
        txtIVAAna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtIVAAna.setForeground(new java.awt.Color(102, 102, 102));
        txtIVAAna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIVAAna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtIVAAna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtIVAAna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtIVAAna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtIVAAna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIVAAnaCaretUpdate(evt);
            }
        });
        txtIVAAna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIVAAnaMouseClicked(evt);
            }
        });

        txtColna.setEditable(false);
        txtColna.setBackground(new java.awt.Color(255, 255, 255));
        txtColna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtColna.setForeground(new java.awt.Color(102, 102, 102));
        txtColna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtColna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtColna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtColna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtColna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtColnaCaretUpdate(evt);
            }
        });
        txtColna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtColnaMouseClicked(evt);
            }
        });

        fechaCol.setBackground(new java.awt.Color(245, 245, 245));
        fechaCol.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaCol.setDateFormatString("dd-MM-yyyy");
        fechaCol.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaPAP.setBackground(new java.awt.Color(245, 245, 245));
        fechaPAP.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaPAP.setDateFormatString("dd-MM-yyyy");
        fechaPAP.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        fechaIVAA.setBackground(new java.awt.Color(245, 245, 245));
        fechaIVAA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        fechaIVAA.setDateFormatString("dd-MM-yyyy");
        fechaIVAA.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("Fecha");

        txtPAPna.setEditable(false);
        txtPAPna.setBackground(new java.awt.Color(255, 255, 255));
        txtPAPna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPAPna.setForeground(new java.awt.Color(102, 102, 102));
        txtPAPna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPAPna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPAPna.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPAPna.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtPAPna.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtPAPna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPAPnaCaretUpdate(evt);
            }
        });
        txtPAPna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPAPnaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel77)
                            .addComponent(jLabel78))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtColn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPAPn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIVAAn, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIVAAa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPAPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel75)))
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPAPnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtColnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIVAAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtColna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIVAAna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPAPna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fechaCol, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel85))
                    .addComponent(fechaIVAA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaPAP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechaPAP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaIVAA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaCol, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel74)
                                .addComponent(jLabel75))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPAPn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53)
                            .addComponent(txtPAPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPAPnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPAPna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(txtIVAAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIVAAa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIVAAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIVAAna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(txtColn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(505, 505, 505)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(910, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(267, 267, 267)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel5);

        jPanel11.setBackground(new java.awt.Color(43, 43, 43));
        jPanel11.setPreferredSize(new java.awt.Dimension(1574, 113));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico.setText("jLabel1");

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(204, 204, 204));
        lblFP.setText("jLabel1");

        lblActoMedico2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico2.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico2.setText("Acto Médico");

        lblActoMedico3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico3.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico3.setText("Forma de Pago");

        lblMadreEXL.setBackground(new java.awt.Color(0, 153, 0));
        lblMadreEXL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMadreEXL.setForeground(new java.awt.Color(255, 255, 255));
        lblMadreEXL.setText("Martha Arias Torres");
        lblMadreEXL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadreEXL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMadreEXL.setIconTextGap(10);
        lblMadreEXL.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMadreEXL, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblActoMedico2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblActoMedico3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblActoMedico)
                            .addComponent(lblFP))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblMadreEXL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActoMedico2)
                    .addComponent(lblActoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFP)
                    .addComponent(lblActoMedico3))
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        String variable;
        variable=var.getText();

       if(variable=="1"){
             Guardar();  
           
        }
        if(variable=="2"){
           mensaje.setVisible(true);
           mensaje.setBackground(new Color(255,153,51)); 
           men.setText("Desea Actualizar el Registro ?");
           b.setText("Si");
           b.setVisible(true);
           b1.setVisible(true); 
           tge=2;
        }       
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void ChkAnalf2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf2CaretUpdate

    private void ChkAnalf2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf2MouseClicked

    private void ChkEdad2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2CaretUpdate

    private void ChkEdad2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad2MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         btnGuardar1.setEnabled(true);
         btneditar.setEnabled(false);
         lblMant.setText("U");
    }//GEN-LAST:event_btneditarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1|| tge==9){
           mensaje.setVisible(false);  

           }

                if (lblMant.getText().equals("U")){
                Guardar();

                btneditar.setEnabled(false);
                tge=9;

           }  
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void txtPAPnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPAPnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAPnaCaretUpdate

    private void txtColnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtColnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColnaCaretUpdate

    private void txtIVAAnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIVAAnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAAnaCaretUpdate

    private void txtColnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtColnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColnhCaretUpdate

    private void txtColaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtColaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColaCaretUpdate

    private void txtColnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtColnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColnCaretUpdate

    private void txtIVAAnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIVAAnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAAnhCaretUpdate

    private void txtIVAAaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIVAAaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAAaCaretUpdate

    private void txtIVAAnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIVAAnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAAnCaretUpdate

    private void txtPAPnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPAPnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAPnhCaretUpdate

    private void txtPAPnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPAPnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAPnCaretUpdate

    private void txtPAPaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPAPaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAPaCaretUpdate

    private void txtListerianaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtListerianaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtListerianaCaretUpdate

    private void txtTamizajenaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTamizajenaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamizajenaCaretUpdate

    private void txtFluornaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFluornaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFluornaCaretUpdate

    private void txtMalarianaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMalarianaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMalarianaCaretUpdate

    private void txtIFInaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIFInaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIFInaCaretUpdate

    private void txtHepatitisnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHepatitisnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHepatitisnhCaretUpdate

    private void txtTamizajenhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTamizajenhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamizajenhCaretUpdate

    private void txtListerianhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtListerianhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtListerianhCaretUpdate

    private void txtBKnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBKnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBKnhCaretUpdate

    private void txtUrocultivonhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUrocultivonhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrocultivonhCaretUpdate

    private void txtNitritosnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNitritosnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitritosnhCaretUpdate

    private void txtHepatitispCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHepatitispCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHepatitispCaretUpdate

    private void txtTamizajepCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTamizajepCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamizajepCaretUpdate

    private void txtListeriapCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtListeriapCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtListeriapCaretUpdate

    private void txtBKpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBKpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBKpCaretUpdate

    private void txtUrocultivopCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUrocultivopCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrocultivopCaretUpdate

    private void txtNitritospCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNitritospCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitritospCaretUpdate

    private void txtHepatitisnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHepatitisnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHepatitisnCaretUpdate

    private void txtTamizajenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTamizajenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTamizajenCaretUpdate

    private void txtListerianCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtListerianCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtListerianCaretUpdate

    private void txtBKnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBKnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBKnCaretUpdate

    private void txtUrocultivonCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUrocultivonCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrocultivonCaretUpdate

    private void txtNitritosnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNitritosnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitritosnCaretUpdate

    private void txtBKnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBKnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBKnaCaretUpdate

    private void txtHepatitisnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHepatitisnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHepatitisnaCaretUpdate

    private void txtGotnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGotnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGotnaCaretUpdate

    private void txtTORCHnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTORCHnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTORCHnaCaretUpdate

    private void txtHTLVLnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHTLVLnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHTLVLnaCaretUpdate

    private void txtLEUnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLEUnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLEUnhCaretUpdate

    private void txtLEUpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLEUpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLEUpCaretUpdate

    private void txtLEUnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLEUnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLEUnCaretUpdate

    private void txtEXnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEXnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEXnhCaretUpdate

    private void txtEXpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEXpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEXpCaretUpdate

    private void txtEXnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEXnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEXnCaretUpdate

    private void txtFluornhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFluornhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFluornhCaretUpdate

    private void txtFluorpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFluorpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFluorpCaretUpdate

    private void txtFluornCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFluornCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFluornCaretUpdate

    private void txtMalarianhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMalarianhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMalarianhCaretUpdate

    private void txtMalariapCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMalariapCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMalariapCaretUpdate

    private void txtMalarianCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMalarianCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMalarianCaretUpdate

    private void txtGotanhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGotanhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGotanhCaretUpdate

    private void txtGotapCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGotapCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGotapCaretUpdate

    private void txtGotanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGotanCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGotanCaretUpdate

    private void txtTORCHnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTORCHnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTORCHnhCaretUpdate

    private void txtTORCHpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTORCHpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTORCHpCaretUpdate

    private void txtTORCHnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTORCHnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTORCHnCaretUpdate

    private void txtHTLVLnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHTLVLnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHTLVLnhCaretUpdate

    private void txtHTLVLpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHTLVLpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHTLVLpCaretUpdate

    private void txtHTLVLnCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHTLVLnCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHTLVLnCaretUpdate

    private void txtIFInhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIFInhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIFInhCaretUpdate

    private void txtIFInCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIFInCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIFInCaretUpdate

    private void txtIFIpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIFIpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIFIpCaretUpdate

    private void txtVIH2naCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIH2naCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIH2naCaretUpdate

    private void txtElisanaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtElisanaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElisanaCaretUpdate

    private void txtTHPAnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTHPAnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTHPAnaCaretUpdate

    private void txtFTAnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFTAnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFTAnaCaretUpdate

    private void txtVDRL2naCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL2naCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL2naCaretUpdate

    private void txtElisanhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtElisanhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElisanhCaretUpdate

    private void txtElisarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtElisarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElisarCaretUpdate

    private void txtElisanrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtElisanrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElisanrCaretUpdate

    private void txtVIH2nhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIH2nhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIH2nhCaretUpdate

    private void txtVIH2rCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIH2rCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIH2rCaretUpdate

    private void txtVIH2nrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIH2nrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIH2nrCaretUpdate

    private void txtVIHnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIHnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIHnhCaretUpdate

    private void txtVIHrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIHrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIHrCaretUpdate

    private void txtVIH1nrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVIH1nrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVIH1nrCaretUpdate

    private void txtPruebanhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPruebanhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPruebanhCaretUpdate

    private void txtPruebarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPruebarCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPruebarCaretUpdate

    private void txtPruebanrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPruebanrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPruebanrCaretUpdate

    private void txtTHPAnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTHPAnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTHPAnhCaretUpdate

    private void txtTHPArCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTHPArCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTHPArCaretUpdate

    private void txtTHPAnrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTHPAnrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTHPAnrCaretUpdate

    private void txtFTAnhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFTAnhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFTAnhCaretUpdate

    private void txtFTArCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFTArCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFTArCaretUpdate

    private void txtFTAnrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFTAnrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFTAnrCaretUpdate

    private void txtVDRL2nhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL2nhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL2nhCaretUpdate

    private void txtVDRL2aCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL2aCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL2aCaretUpdate

    private void txtVDRL2nrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL2nrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL2nrCaretUpdate

    private void txtVDRL1nhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL1nhCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL1nhCaretUpdate

    private void txtVDRL1nrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL1nrCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL1nrCaretUpdate

    private void txtVDRL1aCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVDRL1aCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVDRL1aCaretUpdate

    private void txtTGNACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTGNACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTGNACaretUpdate

    private void txtG2NCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG2NCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG2NCaretUpdate

    private void txtG1NCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG1NCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG1NCaretUpdate

    private void txtTGNHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTGNHCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTGNHCaretUpdate

    private void txtTGACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTGACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTGACaretUpdate

    private void txtG2ACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG2ACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG2ACaretUpdate

    private void txtG2NACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG2NACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG2NACaretUpdate

    private void txtG1ACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG1ACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG1ACaretUpdate

    private void txtG1NACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtG1NACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtG1NACaretUpdate

    private void txtTGNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTGNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTGNCaretUpdate

    private void chkH3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkH3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkH3CaretUpdate

    private void chkH2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkH2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkH2CaretUpdate

    private void chkH1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkH1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkH1CaretUpdate

    private void chkH3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkH3MouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
            
        if(chkH3.getText().equals("X") && evt.getClickCount()==1){
           chkH3.setText(""); 
           txtH3.setEnabled(true);
           dFechaH3.setEnabled(true);
        }else
        if(chkH3.getText().equals("") && evt.getClickCount()==1){
           chkH3.setText("X");
           txtH3.setEnabled(false);
           dFechaH3.setEnabled(false);
        }
        }
    }//GEN-LAST:event_chkH3MouseClicked

    private void chkH1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkH1MouseClicked
       if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
            
        if(chkH1.getText().equals("X") && evt.getClickCount()==1){
           chkH1.setText(""); 
           txtH1.setEnabled(true);
           dFechaH1.setEnabled(true);
        }else
        if(chkH1.getText().equals("") && evt.getClickCount()==1){
           chkH1.setText("X");
           txtH1.setEnabled(false);
           dFechaH1.setEnabled(false);
        }
        }
    }//GEN-LAST:event_chkH1MouseClicked

    private void chkH2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkH2MouseClicked
       if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
            
        if(chkH2.getText().equals("X") && evt.getClickCount()==1){
           chkH2.setText(""); 
           txtH2.setEnabled(true);
           dFechaH2.setEnabled(true);
        }else
        if(chkH2.getText().equals("") && evt.getClickCount()==1){
           chkH2.setText("X");
           txtH2.setEnabled(false);
           dFechaH2.setEnabled(false);
        }
        }
    }//GEN-LAST:event_chkH2MouseClicked

    private void txtG1NMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG1NMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG1N.getText().equals("") && evt.getClickCount()==1){
           txtG1N.setText("X");
           txtG1A.setText("");
           txtG1NA.setText("");
           fechaG1.setEnabled(true);

        }else
        if(txtG1N.getText().equals("X") && evt.getClickCount()==1){
           txtG1N.setText(""); 
           txtG1A.setText("");
           txtG1NA.setText("");
           fechaG1.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtG1NMouseClicked

    private void txtG1AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG1AMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG1A.getText().equals("") && evt.getClickCount()==1){
           txtG1A.setText("X");
           txtG1N.setText("");
           txtG1NA.setText("");
           fechaG1.setEnabled(true);

        }else
        if(txtG1A.getText().equals("X") && evt.getClickCount()==1){
           txtG1A.setText(""); 
           txtG1N.setText("");
           txtG1NA.setText("");
           fechaG1.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtG1AMouseClicked

    private void txtG1NAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG1NAMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG1NA.getText().equals("") && evt.getClickCount()==1){
           txtG1NA.setText("X");
           txtG1N.setText("");
           txtG1A.setText("");
           fechaG1.setEnabled(false);

        }else
        if(txtG1NA.getText().equals("X") && evt.getClickCount()==1){
           txtG1NA.setText(""); 
           txtG1N.setText("");
           txtG1A.setText("");
           fechaG1.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtG1NAMouseClicked

    private void txtG2NMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG2NMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG2N.getText().equals("") && evt.getClickCount()==1){
           txtG2N.setText("X");
           txtG2A.setText("");
           txtG2NA.setText("");
           fechaG2.setEnabled(true);

        }else
        if(txtG2N.getText().equals("X") && evt.getClickCount()==1){
           txtG2N.setText(""); 
           txtG2A.setText("");
           txtG2NA.setText("");
           fechaG2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtG2NMouseClicked

    private void txtG2AMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG2AMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG2A.getText().equals("") && evt.getClickCount()==1){
           txtG2A.setText("X");
           txtG2N.setText("");
           txtG2NA.setText("");
           fechaG2.setEnabled(true);

        }else
        if(txtG2A.getText().equals("X") && evt.getClickCount()==1){
           txtG2A.setText(""); 
           txtG2N.setText("");
           txtG2NA.setText("");
           fechaG2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtG2AMouseClicked

    private void txtG2NAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtG2NAMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtG2NA.getText().equals("") && evt.getClickCount()==1){
           txtG2NA.setText("X");
           txtG2N.setText("");
           txtG2A.setText("");
           fechaG2.setEnabled(false);

        }else
        if(txtG2NA.getText().equals("X") && evt.getClickCount()==1){
           txtG2NA.setText(""); 
           txtG2N.setText("");
           txtG2A.setText("");
           fechaG2.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtG2NAMouseClicked

    private void txtTGNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTGNMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTGN.getText().equals("") && evt.getClickCount()==1){
           txtTGN.setText("X");
           txtTGA.setText("");
           txtTGNH.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(true);

        }else
        if(txtTGN.getText().equals("X") && evt.getClickCount()==1){
           txtTGN.setText("");
           txtTGA.setText("");
           txtTGNH.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTGNMouseClicked

    private void txtTGAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTGAMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTGA.getText().equals("") && evt.getClickCount()==1){
           txtTGA.setText("X");
           txtTGN.setText("");
           txtTGNH.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(true);

        }else
        if(txtTGA.getText().equals("X") && evt.getClickCount()==1){
           txtTGA.setText("");
           txtTGN.setText("");
           txtTGNH.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTGAMouseClicked

    private void txtTGNHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTGNHMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTGNH.getText().equals("") && evt.getClickCount()==1){
           txtTGNH.setText("X");
           txtTGN.setText("");
           txtTGA.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(false);

        }else
        if(txtTGNH.getText().equals("X") && evt.getClickCount()==1){
           txtTGNH.setText("");
           txtTGN.setText("");
           txtTGA.setText("");
           txtTGNA.setText("");
           fechaTG.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTGNHMouseClicked

    private void txtTGNAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTGNAMouseClicked
          if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTGNA.getText().equals("") && evt.getClickCount()==1){
           txtTGNA.setText("X");
           txtTGN.setText("");
           txtTGNH.setText("");
           txtTGA.setText("");
           fechaTG.setEnabled(false);

        }else
        if(txtTGNA.getText().equals("X") && evt.getClickCount()==1){
           txtTGNA.setText("");
           txtTGN.setText("");
           txtTGNH.setText("");
           txtTGA.setText("");
           fechaTG.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTGNAMouseClicked

    private void txtVDRL1nrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL1nrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL1nr.getText().equals("") && evt.getClickCount()==1){
           txtVDRL1nr.setText("X");
           txtVDRL1a.setText("");
           txtVDRL1nh.setText("");
           fechaVDRL1.setEnabled(true);

        }else
        if(txtVDRL1nr.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL1nr.setText("");
           txtVDRL1a.setText("");
           txtVDRL1nh.setText("");
           fechaVDRL1.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtVDRL1nrMouseClicked

    private void txtVDRL1aMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL1aMouseClicked
       if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL1a.getText().equals("") && evt.getClickCount()==1){
           txtVDRL1a.setText("X");
           txtVDRL1nr.setText("");
           txtVDRL1nh.setText("");
           fechaVDRL1.setEnabled(true);

        }else
        if(txtVDRL1a.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL1a.setText("");
           txtVDRL1nr.setText("");
           txtVDRL1nh.setText("");
           fechaVDRL1.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtVDRL1aMouseClicked

    private void txtVDRL1nhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL1nhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL1nh.getText().equals("") && evt.getClickCount()==1){
           txtVDRL1nh.setText("X");
           txtVDRL1a.setText("");
           txtVDRL1nr.setText("");
           fechaVDRL1.setEnabled(false);

        }else
        if(txtVDRL1nh.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL1nh.setText("");
           txtVDRL1a.setText("");
           txtVDRL1nr.setText("");
           fechaVDRL1.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtVDRL1nhMouseClicked

    private void txtVDRL2nrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL2nrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL2nr.getText().equals("") && evt.getClickCount()==1){
           txtVDRL2nr.setText("X");
           txtVDRL2a.setText("");
           txtVDRL2nh.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(true);

        }else
        if(txtVDRL2nr.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL2nr.setText("");
           txtVDRL2a.setText("");
           txtVDRL2nh.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtVDRL2nrMouseClicked

    private void txtVDRL2aMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL2aMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL2a.getText().equals("") && evt.getClickCount()==1){
           txtVDRL2a.setText("X");
           txtVDRL2nr.setText("");
           txtVDRL2nh.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(true);

        }else
        if(txtVDRL2a.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL2a.setText("");
           txtVDRL2nr.setText("");
           txtVDRL2nh.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtVDRL2aMouseClicked

    private void txtVDRL2nhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL2nhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL2nh.getText().equals("") && evt.getClickCount()==1){
           txtVDRL2nh.setText("X");
           txtVDRL2a.setText("");
           txtVDRL2nr.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(false);

        }else
        if(txtVDRL2nh.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL2nh.setText("");
           txtVDRL2a.setText("");
           txtVDRL2nr.setText("");
           txtVDRL2na.setText("");
           fechaVDRL2.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtVDRL2nhMouseClicked

    private void txtVDRL2naMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVDRL2naMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVDRL2na.getText().equals("") && evt.getClickCount()==1){
           txtVDRL2na.setText("X");
           txtVDRL2a.setText("");
           txtVDRL2nr.setText("");
           txtVDRL2nh.setText("");
           fechaVDRL2.setEnabled(false);

        }else
        if(txtVDRL2na.getText().equals("X") && evt.getClickCount()==1){
           txtVDRL2na.setText("");
           txtVDRL2a.setText("");
           txtVDRL2nr.setText("");
           txtVDRL2nh.setText("");
           fechaVDRL2.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtVDRL2naMouseClicked

    private void txtFTAnrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFTAnrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFTAnr.getText().equals("") && evt.getClickCount()==1){
           txtFTAnr.setText("X");
           txtFTAr.setText("");
           txtFTAnh.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(true);

        }else
        if(txtFTAnr.getText().equals("X") && evt.getClickCount()==1){
           txtFTAnr.setText("");
           txtFTAr.setText("");
           txtFTAnh.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtFTAnrMouseClicked

    private void txtFTArMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFTArMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFTAr.getText().equals("") && evt.getClickCount()==1){
           txtFTAr.setText("X");
           txtFTAnr.setText("");
           txtFTAnh.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(true);

        }else
        if(txtFTAr.getText().equals("X") && evt.getClickCount()==1){
           txtFTAr.setText("");
           txtFTAnr.setText("");
           txtFTAnh.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtFTArMouseClicked

    private void txtFTAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFTAnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFTAnh.getText().equals("") && evt.getClickCount()==1){
           txtFTAnh.setText("X");
           txtFTAr.setText("");
           txtFTAnr.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(false);

        }else
        if(txtFTAnh.getText().equals("X") && evt.getClickCount()==1){
           txtFTAnh.setText("");
           txtFTAr.setText("");
           txtFTAnr.setText("");
           txtFTAna.setText("");
           fechaFTA.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtFTAnhMouseClicked

    private void txtFTAnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFTAnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFTAna.getText().equals("") && evt.getClickCount()==1){
           txtFTAna.setText("X");
           txtFTAr.setText("");
           txtFTAnh.setText("");
           txtFTAnr.setText("");
           fechaFTA.setEnabled(false);

        }else
        if(txtFTAna.getText().equals("X") && evt.getClickCount()==1){
           txtFTAna.setText("");
           txtFTAr.setText("");
           txtFTAnh.setText("");
           txtFTAnr.setText("");
           fechaFTA.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtFTAnaMouseClicked

    private void txtTHPAnrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTHPAnrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTHPAnr.getText().equals("") && evt.getClickCount()==1){
           txtTHPAnr.setText("X");
           txtTHPAr.setText("");
           txtTHPAnh.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(true);

        }else
        if(txtTHPAnr.getText().equals("X") && evt.getClickCount()==1){
           txtTHPAnr.setText("");
           txtTHPAr.setText("");
           txtTHPAnh.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtTHPAnrMouseClicked

    private void txtTHPArMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTHPArMouseClicked
       if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTHPAr.getText().equals("") && evt.getClickCount()==1){
           txtTHPAr.setText("X");
           txtTHPAnr.setText("");
           txtTHPAnh.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(true);

        }else
        if(txtTHPAr.getText().equals("X") && evt.getClickCount()==1){
           txtTHPAr.setText("");
           txtTHPAnr.setText("");
           txtTHPAnh.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(false);
           
        }
       }
    }//GEN-LAST:event_txtTHPArMouseClicked

    private void txtTHPAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTHPAnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTHPAnh.getText().equals("") && evt.getClickCount()==1){
           txtTHPAnh.setText("X");
           txtTHPAr.setText("");
           txtTHPAnr.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(false);

        }else
        if(txtTHPAnh.getText().equals("X") && evt.getClickCount()==1){
           txtTHPAnh.setText("");
           txtTHPAr.setText("");
           txtTHPAnr.setText("");
           txtTHPAna.setText("");
           fechaTHPA.setEnabled(true);
           
        }
       }
    }//GEN-LAST:event_txtTHPAnhMouseClicked

    private void txtTHPAnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTHPAnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTHPAna.getText().equals("") && evt.getClickCount()==1){
           txtTHPAna.setText("X");
           txtTHPAr.setText("");
           txtTHPAnh.setText("");
           txtTHPAnr.setText("");
           fechaTHPA.setEnabled(false);

        }else
        if(txtTHPAna.getText().equals("X") && evt.getClickCount()==1){
           txtTHPAna.setText("");
           txtTHPAr.setText("");
           txtTHPAnh.setText("");
           txtTHPAnr.setText("");
           fechaTHPA.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTHPAnaMouseClicked

    private void txtPruebanrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPruebanrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPruebanr.getText().equals("") && evt.getClickCount()==1){
           txtPruebanr.setText("X");
           txtPruebar.setText("");
           txtPruebanh.setText("");
           fechaPrueba.setEnabled(true);

        }else
        if(txtPruebanr.getText().equals("X") && evt.getClickCount()==1){
           txtPruebanr.setText("");
           txtPruebar.setText("");
           txtPruebanh.setText("");
           fechaPrueba.setEnabled(false); 
        }
       }
    }//GEN-LAST:event_txtPruebanrMouseClicked

    private void txtPruebarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPruebarMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPruebar.getText().equals("") && evt.getClickCount()==1){
           txtPruebar.setText("X");
           txtPruebanr.setText("");
           txtPruebanh.setText("");
           fechaPrueba.setEnabled(true);

        }else
        if(txtPruebar.getText().equals("X") && evt.getClickCount()==1){
           txtPruebar.setText("");
           txtPruebanr.setText("");
           txtPruebanh.setText("");
           fechaPrueba.setEnabled(false); 
        }
       }
    }//GEN-LAST:event_txtPruebarMouseClicked

    private void txtPruebanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPruebanhMouseClicked
         if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPruebanh.getText().equals("") && evt.getClickCount()==1){
           txtPruebanh.setText("X");
           txtPruebar.setText("");
           txtPruebanr.setText("");
           fechaPrueba.setEnabled(false);

        }else
        if(txtPruebanh.getText().equals("X") && evt.getClickCount()==1){
           txtPruebanh.setText("");
           txtPruebar.setText("");
           txtPruebanr.setText("");
           fechaPrueba.setEnabled(true); 
        }
       }
    }//GEN-LAST:event_txtPruebanhMouseClicked

    private void txtUrocultivonaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUrocultivonaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUrocultivonaCaretUpdate

    private void txtVIH1nrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIH1nrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIH1nr.getText().equals("") && evt.getClickCount()==1){
           txtVIH1nr.setText("X");
           txtVIHr.setText("");
           txtVIHnh.setText("");
           fechaVIH1.setEnabled(true);

        }else
        if(txtVIH1nr.getText().equals("X") && evt.getClickCount()==1){
           txtVIH1nr.setText("");
           txtVIHr.setText("");
           txtVIHnh.setText("");
           fechaVIH1.setEnabled(false); 
        }
       }
    }//GEN-LAST:event_txtVIH1nrMouseClicked

    private void txtVIHrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIHrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIHr.getText().equals("") && evt.getClickCount()==1){
           txtVIHr.setText("X");
           txtVIH1nr.setText("");
           txtVIHnh.setText("");
           fechaVIH1.setEnabled(true);

        }else
        if(txtVIHr.getText().equals("X") && evt.getClickCount()==1){
           txtVIHr.setText("");
           txtVIH1nr.setText("");
           txtVIHnh.setText("");
           fechaVIH1.setEnabled(false); 
        }
       }
    }//GEN-LAST:event_txtVIHrMouseClicked

    private void txtVIHnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIHnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIHnh.getText().equals("") && evt.getClickCount()==1){
           txtVIHnh.setText("X");
           txtVIH1nr.setText("");
           txtVIHr.setText("");
           fechaVIH1.setEnabled(false);

        }else
        if(txtVIHnh.getText().equals("X") && evt.getClickCount()==1){
           txtVIHnh.setText("");
           txtVIH1nr.setText("");
           txtVIHr.setText("");
           fechaVIH1.setEnabled(true); 
        }
       }
    }//GEN-LAST:event_txtVIHnhMouseClicked

    private void txtVIH2nrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIH2nrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIH2nr.getText().equals("") && evt.getClickCount()==1){
           txtVIH2nr.setText("X");
           txtVIH2r.setText("");
           txtVIH2nh.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(true);

        }else
        if(txtVIH2nr.getText().equals("X") && evt.getClickCount()==1){
           txtVIH2nr.setText("");
           txtVIH2r.setText("");
           txtVIH2nh.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtVIH2nrMouseClicked

    private void txtVIH2rMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIH2rMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIH2r.getText().equals("") && evt.getClickCount()==1){
           txtVIH2r.setText("X");
           txtVIH2nr.setText("");
           txtVIH2nh.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(true);

        }else
        if(txtVIH2r.getText().equals("X") && evt.getClickCount()==1){
           txtVIH2r.setText("");
           txtVIH2nr.setText("");
           txtVIH2nh.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtVIH2rMouseClicked

    private void txtVIH2nhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIH2nhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIH2nh.getText().equals("") && evt.getClickCount()==1){
           txtVIH2nh.setText("X");
           txtVIH2nr.setText("");
           txtVIH2r.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(false);

        }else
        if(txtVIH2nh.getText().equals("X") && evt.getClickCount()==1){
           txtVIH2nh.setText("");
           txtVIH2nr.setText("");
           txtVIH2r.setText("");
           txtVIH2na.setText("");
           fechaVIH2.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtVIH2nhMouseClicked

    private void txtVIH2naMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVIH2naMouseClicked
         if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtVIH2na.getText().equals("") && evt.getClickCount()==1){
           txtVIH2na.setText("X");
           txtVIH2nr.setText("");
           txtVIH2r.setText("");
           txtVIH2nh.setText("");
           fechaVIH2.setEnabled(false);

        }else
        if(txtVIH2na.getText().equals("X") && evt.getClickCount()==1){
           txtVIH2na.setText("");
           txtVIH2nr.setText("");
           txtVIH2r.setText("");
           txtVIH2nh.setText("");
           fechaVIH2.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtVIH2naMouseClicked

    private void txtElisanrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtElisanrMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtElisanr.getText().equals("") && evt.getClickCount()==1){
           txtElisanr.setText("X");
           txtElisar.setText("");
           txtElisanh.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(true);

        }else
        if(txtElisanr.getText().equals("X") && evt.getClickCount()==1){
           txtElisanr.setText("");
           txtElisar.setText("");
           txtElisanh.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtElisanrMouseClicked

    private void txtElisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtElisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtElisarActionPerformed

    private void txtElisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtElisarMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtElisar.getText().equals("") && evt.getClickCount()==1){
           txtElisar.setText("X");
           txtElisanr.setText("");
           txtElisanh.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(true);

        }else
        if(txtElisar.getText().equals("X") && evt.getClickCount()==1){
           txtElisar.setText("");
           txtElisanr.setText("");
           txtElisanh.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtElisarMouseClicked

    private void txtElisanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtElisanhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtElisanh.getText().equals("") && evt.getClickCount()==1){
           txtElisanh.setText("X");
           txtElisanr.setText("");
           txtElisar.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(false);

        }else
        if(txtElisanh.getText().equals("X") && evt.getClickCount()==1){
           txtElisanh.setText("");
           txtElisanr.setText("");
           txtElisar.setText("");
           txtElisana.setText("");
           fechaELisa.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtElisanhMouseClicked

    private void txtElisanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtElisanaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtElisana.getText().equals("") && evt.getClickCount()==1){
           txtElisana.setText("X");
           txtElisanr.setText("");
           txtElisar.setText("");
           txtElisanh.setText("");
           fechaELisa.setEnabled(false);

        }else
        if(txtElisana.getText().equals("X") && evt.getClickCount()==1){
           txtElisana.setText("");
           txtElisanr.setText("");
           txtElisar.setText("");
           txtElisanh.setText("");
           fechaELisa.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtElisanaMouseClicked

    private void txtIFInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIFInMouseClicked
         if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIFIn.getText().equals("") && evt.getClickCount()==1){
           txtIFIn.setText("X");
           txtIFIp.setText("");
           txtIFInh.setText("");
           txtIFIna.setText("");
           fechaIFI.setEnabled(true);

        }else
        if(txtIFIn.getText().equals("X") && evt.getClickCount()==1){
           txtIFIn.setText("");
           txtIFIp.setText("");
           txtIFInh.setText("");
           txtIFIna.setText("");
           fechaELisa.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtIFInMouseClicked

    private void txtIFIpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIFIpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIFIp.getText().equals("") && evt.getClickCount()==1){
           txtIFIp.setText("X");
           txtIFIn.setText("");
           txtIFInh.setText("");
           txtIFIna.setText("");
           fechaIFI.setEnabled(true);

        }else
        if(txtIFIp.getText().equals("X") && evt.getClickCount()==1){
           txtIFIp.setText("");
           txtIFIn.setText("");
           txtIFInh.setText("");
           txtIFIna.setText("");
           fechaELisa.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtIFIpMouseClicked

    private void txtIFInhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIFInhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIFInh.getText().equals("") && evt.getClickCount()==1){
           txtIFInh.setText("X");
           txtIFIn.setText("");
           txtIFIp.setText("");
           txtIFIna.setText("");
           fechaIFI.setEnabled(false);

        }else
        if(txtIFInh.getText().equals("X") && evt.getClickCount()==1){
           txtIFInh.setText("");
           txtIFIn.setText("");
           txtIFIp.setText("");
           txtIFIna.setText("");
           fechaELisa.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtIFInhMouseClicked

    private void txtIFInaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIFInaMouseClicked
         if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIFIna.getText().equals("") && evt.getClickCount()==1){
           txtIFIna.setText("X");
           txtIFIn.setText("");
           txtIFIp.setText("");
           txtIFInh.setText("");
           fechaIFI.setEnabled(false);

        }else
        if(txtIFIna.getText().equals("X") && evt.getClickCount()==1){
           txtIFIna.setText("");
           txtIFIn.setText("");
           txtIFIp.setText("");
           txtIFInh.setText("");
           fechaELisa.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtIFInaMouseClicked

    private void txtHTLVLnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHTLVLnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHTLVLn.getText().equals("") && evt.getClickCount()==1){
           txtHTLVLn.setText("X");
           txtHTLVLp.setText("");
           txtHTLVLnh.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(true);

        }else
        if(txtHTLVLn.getText().equals("X") && evt.getClickCount()==1){
           txtHTLVLn.setText("");
           txtHTLVLp.setText("");
           txtHTLVLnh.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtHTLVLnMouseClicked

    private void txtHTLVLpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHTLVLpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHTLVLp.getText().equals("") && evt.getClickCount()==1){
           txtHTLVLp.setText("X");
           txtHTLVLn.setText("");
           txtHTLVLnh.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(true);

        }else
        if(txtHTLVLp.getText().equals("X") && evt.getClickCount()==1){
           txtHTLVLp.setText("");
           txtHTLVLn.setText("");
           txtHTLVLnh.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtHTLVLpMouseClicked

    private void txtHTLVLnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHTLVLnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHTLVLnh.getText().equals("") && evt.getClickCount()==1){
           txtHTLVLnh.setText("X");
           txtHTLVLn.setText("");
           txtHTLVLp.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(false);

        }else
        if(txtHTLVLnh.getText().equals("X") && evt.getClickCount()==1){
           txtHTLVLnh.setText("");
           txtHTLVLn.setText("");
           txtHTLVLp.setText("");
           txtHTLVLna.setText("");
           fechaHTLVI.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtHTLVLnhMouseClicked

    private void txtHTLVLnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHTLVLnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHTLVLna.getText().equals("") && evt.getClickCount()==1){
           txtHTLVLna.setText("X");
           txtHTLVLn.setText("");
           txtHTLVLp.setText("");
           txtHTLVLnh.setText("");
           fechaHTLVI.setEnabled(false);

        }else
        if(txtHTLVLna.getText().equals("X") && evt.getClickCount()==1){
           txtHTLVLna.setText("");
           txtHTLVLn.setText("");
           txtHTLVLp.setText("");
           txtHTLVLnh.setText("");
           fechaHTLVI.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtHTLVLnaMouseClicked

    private void txtTORCHnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTORCHnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTORCHn.getText().equals("") && evt.getClickCount()==1){
           txtTORCHn.setText("X");
           txtTORCHp.setText("");
           txtTORCHnh.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(true);

        }else
        if(txtTORCHn.getText().equals("X") && evt.getClickCount()==1){
           txtTORCHn.setText("");
           txtTORCHp.setText("");
           txtTORCHnh.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTORCHnMouseClicked

    private void txtTORCHpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTORCHpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTORCHp.getText().equals("") && evt.getClickCount()==1){
           txtTORCHp.setText("X");
           txtTORCHn.setText("");
           txtTORCHnh.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(true);

        }else
        if(txtTORCHp.getText().equals("X") && evt.getClickCount()==1){
           txtTORCHp.setText("");
           txtTORCHn.setText("");
           txtTORCHnh.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTORCHpMouseClicked

    private void txtTORCHnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTORCHnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTORCHnh.getText().equals("") && evt.getClickCount()==1){
           txtTORCHnh.setText("X");
           txtTORCHn.setText("");
           txtTORCHp.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(false);

        }else
        if(txtTORCHnh.getText().equals("X") && evt.getClickCount()==1){
           txtTORCHnh.setText("");
           txtTORCHn.setText("");
           txtTORCHp.setText("");
           txtTORCHna.setText("");
           fechaTORCH.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTORCHnhMouseClicked

    private void txtTORCHnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTORCHnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTORCHna.getText().equals("") && evt.getClickCount()==1){
           txtTORCHna.setText("X");
           txtTORCHn.setText("");
           txtTORCHp.setText("");
           txtTORCHnh.setText("");
           fechaTORCH.setEnabled(false);

        }else
        if(txtTORCHna.getText().equals("X") && evt.getClickCount()==1){
           txtTORCHna.setText("");
           txtTORCHn.setText("");
           txtTORCHp.setText("");
           txtTORCHnh.setText("");
           fechaTORCH.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTORCHnaMouseClicked

    private void txtGotanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGotanMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtGotan.getText().equals("") && evt.getClickCount()==1){
           txtGotan.setText("X");
           txtGotap.setText("");
           txtGotanh.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(true);

        }else
        if(txtGotan.getText().equals("X") && evt.getClickCount()==1){
           txtGotan.setText("");
           txtGotap.setText("");
           txtGotanh.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtGotanMouseClicked

    private void txtGotapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGotapMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtGotap.getText().equals("") && evt.getClickCount()==1){
           txtGotap.setText("X");
           txtGotan.setText("");
           txtGotanh.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(true);

        }else
        if(txtGotap.getText().equals("X") && evt.getClickCount()==1){
           txtGotap.setText("");
           txtGotan.setText("");
           txtGotanh.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtGotapMouseClicked

    private void txtGotanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGotanhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtGotanh.getText().equals("") && evt.getClickCount()==1){
           txtGotanh.setText("X");
           txtGotan.setText("");
           txtGotap.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(false);

        }else
        if(txtGotanh.getText().equals("X") && evt.getClickCount()==1){
           txtGotanh.setText("");
           txtGotan.setText("");
           txtGotap.setText("");
           txtGotna.setText("");
           fechaGota.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtGotanhMouseClicked

    private void txtGotnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGotnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtGotna.getText().equals("") && evt.getClickCount()==1){
           txtGotna.setText("X");
           txtGotan.setText("");
           txtGotap.setText("");
           txtGotanh.setText("");
           fechaGota.setEnabled(false);

        }else
        if(txtGotna.getText().equals("X") && evt.getClickCount()==1){
           txtGotna.setText("");
           txtGotan.setText("");
           txtGotap.setText("");
           txtGotanh.setText("");
           fechaGota.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtGotnaMouseClicked

    private void txtMalarianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMalarianMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtMalarian.getText().equals("") && evt.getClickCount()==1){
           txtMalarian.setText("X");
           txtMalariap.setText("");
           txtMalarianh.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(true);

        }else
        if(txtMalarian.getText().equals("X") && evt.getClickCount()==1){
           txtMalarian.setText("");
           txtMalariap.setText("");
           txtMalarianh.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtMalarianMouseClicked

    private void txtMalariapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMalariapMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtMalariap.getText().equals("") && evt.getClickCount()==1){
           txtMalariap.setText("X");
           txtMalarian.setText("");
           txtMalarianh.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(true);

        }else
        if(txtMalariap.getText().equals("X") && evt.getClickCount()==1){
           txtMalariap.setText("");
           txtMalarian.setText("");
           txtMalarianh.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtMalariapMouseClicked

    private void txtMalarianhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMalarianhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtMalarianh.getText().equals("") && evt.getClickCount()==1){
           txtMalarianh.setText("X");
           txtMalarian.setText("");
           txtMalariap.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(false);

        }else
        if(txtMalarianh.getText().equals("X") && evt.getClickCount()==1){
           txtMalarianh.setText("");
           txtMalarian.setText("");
           txtMalariap.setText("");
           txtMalariana.setText("");
           fechaMalaria.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtMalarianhMouseClicked

    private void txtMalarianaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMalarianaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtMalariana.getText().equals("") && evt.getClickCount()==1){
           txtMalariana.setText("X");
           txtMalarian.setText("");
           txtMalariap.setText("");
           txtMalarianh.setText("");
           fechaMalaria.setEnabled(false);

        }else
        if(txtMalariana.getText().equals("X") && evt.getClickCount()==1){
           txtMalariana.setText("");
           txtMalarian.setText("");
           txtMalariap.setText("");
           txtMalarianh.setText("");
           fechaMalaria.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtMalarianaMouseClicked

    private void txtFluornMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFluornMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFluorn.getText().equals("") && evt.getClickCount()==1){
           txtFluorn.setText("X");
           txtFluorp.setText("");
           txtFluornh.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(true);

        }else
        if(txtFluorn.getText().equals("X") && evt.getClickCount()==1){
           txtFluorn.setText("");
           txtFluorp.setText("");
           txtFluornh.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtFluornMouseClicked

    private void txtFluorpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFluorpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFluorp.getText().equals("") && evt.getClickCount()==1){
           txtFluorp.setText("X");
           txtFluorn.setText("");
           txtFluornh.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(true);

        }else
        if(txtFluorp.getText().equals("X") && evt.getClickCount()==1){
           txtFluorp.setText("");
           txtFluorn.setText("");
           txtFluornh.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtFluorpMouseClicked

    private void txtFluornhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFluornhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFluornh.getText().equals("") && evt.getClickCount()==1){
           txtFluornh.setText("X");
           txtFluorn.setText("");
           txtFluorp.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(false);

        }else
        if(txtFluornh.getText().equals("X") && evt.getClickCount()==1){
           txtFluornh.setText("");
           txtFluorn.setText("");
           txtFluorp.setText("");
           txtFluorna.setText("");
           fechaFluor.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtFluornhMouseClicked

    private void txtFluornaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFluornaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFluorna.getText().equals("") && evt.getClickCount()==1){
           txtFluorna.setText("X");
           txtFluorn.setText("");
           txtFluorp.setText("");
           txtFluornh.setText("");
           fechaFluor.setEnabled(false);

        }else
        if(txtFluorna.getText().equals("X") && evt.getClickCount()==1){
           txtFluorna.setText("");
           txtFluorn.setText("");
           txtFluorp.setText("");
           txtFluornh.setText("");
           fechaFluor.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtFluornaMouseClicked

    private void txtEXnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEXnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtEXn.getText().equals("") && evt.getClickCount()==1){
           txtEXn.setText("X");
           txtEXp.setText("");
           txtEXnh.setText("");
           fechaEX.setEnabled(true);

        }else
        if(txtEXn.getText().equals("X") && evt.getClickCount()==1){
           txtEXn.setText("");
           txtEXp.setText("");
           txtEXnh.setText("");
           fechaEX.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtEXnMouseClicked

    private void txtEXpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEXpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtEXp.getText().equals("") && evt.getClickCount()==1){
           txtEXp.setText("X");
           txtEXn.setText("");
           txtEXnh.setText("");
           fechaEX.setEnabled(true);

        }else
        if(txtEXp.getText().equals("X") && evt.getClickCount()==1){
           txtEXp.setText("");
           txtEXn.setText("");
           txtEXnh.setText("");
           fechaEX.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtEXpMouseClicked

    private void txtEXnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEXnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtEXnh.getText().equals("") && evt.getClickCount()==1){
           txtEXnh.setText("X");
           txtEXp.setText("");
           txtEXn.setText("");
           fechaEX.setEnabled(false);

        }else
        if(txtEXnh.getText().equals("X") && evt.getClickCount()==1){
           txtEXnh.setText("");
           txtEXp.setText("");
           txtEXn.setText("");
           fechaEX.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtEXnhMouseClicked

    private void txtLEUnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLEUnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLEUn.getText().equals("") && evt.getClickCount()==1){
           txtLEUn.setText("X");
           txtLEUp.setText("");
           txtLEUnh.setText("");
           fechaLEU.setEnabled(true);

        }else
        if(txtLEUn.getText().equals("X") && evt.getClickCount()==1){
           txtLEUn.setText("");
           txtLEUp.setText("");
           txtLEUnh.setText("");
           fechaLEU.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtLEUnMouseClicked

    private void txtLEUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLEUpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLEUp.getText().equals("") && evt.getClickCount()==1){
           txtLEUp.setText("X");
           txtLEUn.setText("");
           txtLEUnh.setText("");
           fechaLEU.setEnabled(true);

        }else
        if(txtLEUp.getText().equals("X") && evt.getClickCount()==1){
           txtLEUp.setText("");
           txtLEUn.setText("");
           txtLEUnh.setText("");
           fechaLEU.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtLEUpMouseClicked

    private void txtLEUnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLEUnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLEUnh.getText().equals("") && evt.getClickCount()==1){
           txtLEUnh.setText("X");
           txtLEUn.setText("");
           txtLEUp.setText("");
           fechaLEU.setEnabled(false);

        }else
        if(txtLEUnh.getText().equals("X") && evt.getClickCount()==1){
           txtLEUnh.setText("");
           txtLEUn.setText("");
           txtLEUp.setText("");
           fechaLEU.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtLEUnhMouseClicked

    private void txtNitritosnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNitritosnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtNitritosn.getText().equals("") && evt.getClickCount()==1){
           txtNitritosn.setText("X");
           txtNitritosp.setText("");
           txtNitritosnh.setText("");
           fechaNitritos.setEnabled(true);

        }else
        if(txtNitritosn.getText().equals("X") && evt.getClickCount()==1){
           txtNitritosn.setText("");
           txtNitritosp.setText("");
           txtNitritosnh.setText("");
           fechaNitritos.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtNitritosnMouseClicked

    private void txtNitritospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNitritospMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtNitritosp.getText().equals("") && evt.getClickCount()==1){
           txtNitritosp.setText("X");
           txtNitritosn.setText("");
           txtNitritosnh.setText("");
           fechaNitritos.setEnabled(true);

        }else
        if(txtNitritosp.getText().equals("X") && evt.getClickCount()==1){
           txtNitritosp.setText("");
           txtNitritosn.setText("");
           txtNitritosnh.setText("");
           fechaNitritos.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtNitritospMouseClicked

    private void txtNitritosnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNitritosnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtNitritosnh.getText().equals("") && evt.getClickCount()==1){
           txtNitritosnh.setText("X");
           txtNitritosn.setText("");
           txtNitritosp.setText("");
           fechaNitritos.setEnabled(false);

        }else
        if(txtNitritosnh.getText().equals("X") && evt.getClickCount()==1){
           txtNitritosnh.setText("");
           txtNitritosn.setText("");
           txtNitritosp.setText("");
           fechaNitritos.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtNitritosnhMouseClicked

    private void txtUrocultivonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUrocultivonMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtUrocultivon.getText().equals("") && evt.getClickCount()==1){
           txtUrocultivon.setText("X");
           txtUrocultivop.setText("");
           txtUrocultivonh.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(true);

        }else
        if(txtUrocultivon.getText().equals("X") && evt.getClickCount()==1){
           txtUrocultivon.setText("");
           txtUrocultivop.setText("");
           txtUrocultivonh.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtUrocultivonMouseClicked

    private void txtUrocultivopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUrocultivopMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtUrocultivop.getText().equals("") && evt.getClickCount()==1){
           txtUrocultivop.setText("X");
           txtUrocultivon.setText("");
           txtUrocultivonh.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(true);

        }else
        if(txtUrocultivop.getText().equals("X") && evt.getClickCount()==1){
           txtUrocultivop.setText("");
           txtUrocultivon.setText("");
           txtUrocultivonh.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtUrocultivopMouseClicked

    private void txtUrocultivonhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUrocultivonhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtUrocultivonh.getText().equals("") && evt.getClickCount()==1){
           txtUrocultivonh.setText("X");
           txtUrocultivon.setText("");
           txtUrocultivop.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(false);

        }else
        if(txtUrocultivonh.getText().equals("X") && evt.getClickCount()==1){
           txtUrocultivonh.setText("");
           txtUrocultivon.setText("");
           txtUrocultivop.setText("");
           txtUrocultivona.setText("");
           fechaUrocultivo.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtUrocultivonhMouseClicked

    private void txtUrocultivonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUrocultivonaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtUrocultivona.getText().equals("") && evt.getClickCount()==1){
           txtUrocultivona.setText("X");
           txtUrocultivon.setText("");
           txtUrocultivop.setText("");
           txtUrocultivonh.setText("");
           fechaUrocultivo.setEnabled(false);

        }else
        if(txtUrocultivona.getText().equals("X") && evt.getClickCount()==1){
           txtUrocultivona.setText("");
           txtUrocultivon.setText("");
           txtUrocultivop.setText("");
           txtUrocultivonh.setText("");
           fechaUrocultivo.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtUrocultivonaMouseClicked

    private void txtBKnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBKnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtBKn.getText().equals("") && evt.getClickCount()==1){
           txtBKn.setText("X");
           txtBKp.setText("");
           txtBKnh.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(true);

        }else
        if(txtBKn.getText().equals("X") && evt.getClickCount()==1){
           txtBKn.setText("");
           txtBKp.setText("");
           txtBKnh.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtBKnMouseClicked

    private void txtBKpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBKpMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtBKp.getText().equals("") && evt.getClickCount()==1){
           txtBKp.setText("X");
           txtBKn.setText("");
           txtBKnh.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(true);

        }else
        if(txtBKp.getText().equals("X") && evt.getClickCount()==1){
           txtBKp.setText("");
           txtBKn.setText("");
           txtBKnh.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtBKpMouseClicked

    private void txtBKnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBKnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtBKnh.getText().equals("") && evt.getClickCount()==1){
           txtBKnh.setText("X");
           txtBKn.setText("");
           txtBKp.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(false);

        }else
        if(txtBKnh.getText().equals("X") && evt.getClickCount()==1){
           txtBKnh.setText("");
           txtBKn.setText("");
           txtBKp.setText("");
           txtBKna.setText("");
           fechaBK.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtBKnhMouseClicked

    private void txtBKnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBKnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtBKna.getText().equals("") && evt.getClickCount()==1){
           txtBKna.setText("X");
           txtBKn.setText("");
           txtBKp.setText("");
           txtBKnh.setText("");
           fechaBK.setEnabled(false);

        }else
        if(txtBKna.getText().equals("X") && evt.getClickCount()==1){
           txtBKna.setText("");
           txtBKn.setText("");
           txtBKp.setText("");
           txtBKnh.setText("");
           fechaBK.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtBKnaMouseClicked

    private void txtListerianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtListerianMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtListerian.getText().equals("") && evt.getClickCount()==1){
           txtListerian.setText("X");
           txtListeriap.setText("");
           txtListerianh.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(true);

        }else
        if(txtListerian.getText().equals("X") && evt.getClickCount()==1){
           txtListerian.setText("");
           txtListeriap.setText("");
           txtListerianh.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtListerianMouseClicked

    private void txtListeriapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtListeriapMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtListeriap.getText().equals("") && evt.getClickCount()==1){
           txtListeriap.setText("X");
           txtListerian.setText("");
           txtListerianh.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(true);

        }else
        if(txtListeriap.getText().equals("X") && evt.getClickCount()==1){
           txtListeriap.setText("");
           txtListerian.setText("");
           txtListerianh.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtListeriapMouseClicked

    private void txtListerianhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtListerianhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtListerianh.getText().equals("") && evt.getClickCount()==1){
           txtListerianh.setText("X");
           txtListerian.setText("");
           txtListeriap.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(false);

        }else
        if(txtListerianh.getText().equals("X") && evt.getClickCount()==1){
           txtListerianh.setText("");
           txtListerian.setText("");
           txtListeriap.setText("");
           txtListeriana.setText("");
           fechaListeria.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtListerianhMouseClicked

    private void txtListerianaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtListerianaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtListeriana.getText().equals("") && evt.getClickCount()==1){
           txtListeriana.setText("X");
           txtListerian.setText("");
           txtListeriap.setText("");
           txtListerianh.setText("");
           fechaListeria.setEnabled(false);

        }else
        if(txtListeriana.getText().equals("X") && evt.getClickCount()==1){
           txtListeriana.setText("");
           txtListerian.setText("");
           txtListeriap.setText("");
           txtListerianh.setText("");
           fechaListeria.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtListerianaMouseClicked

    private void txtTamizajenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTamizajenMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTamizajen.getText().equals("") && evt.getClickCount()==1){
           txtTamizajen.setText("X");
           txtTamizajep.setText("");
           txtTamizajenh.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(true);

        }else
        if(txtTamizajen.getText().equals("X") && evt.getClickCount()==1){
           txtTamizajen.setText("");
           txtTamizajep.setText("");
           txtTamizajenh.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTamizajenMouseClicked

    private void txtTamizajepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTamizajepMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTamizajep.getText().equals("") && evt.getClickCount()==1){
           txtTamizajep.setText("X");
           txtTamizajen.setText("");
           txtTamizajenh.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(true);

        }else
        if(txtTamizajep.getText().equals("X") && evt.getClickCount()==1){
           txtTamizajep.setText("");
           txtTamizajen.setText("");
           txtTamizajenh.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtTamizajepMouseClicked

    private void txtTamizajenhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTamizajenhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTamizajenh.getText().equals("") && evt.getClickCount()==1){
           txtTamizajenh.setText("X");
           txtTamizajen.setText("");
           txtTamizajep.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(false);

        }else
        if(txtTamizajenh.getText().equals("X") && evt.getClickCount()==1){
           txtTamizajenh.setText("");
           txtTamizajen.setText("");
           txtTamizajep.setText("");
           txtTamizajena.setText("");
           fechaTamizaje.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTamizajenhMouseClicked

    private void txtTamizajenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTamizajenaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTamizajena.getText().equals("") && evt.getClickCount()==1){
           txtTamizajena.setText("X");
           txtTamizajen.setText("");
           txtTamizajep.setText("");
           txtTamizajenh.setText("");
           fechaTamizaje.setEnabled(false);

        }else
        if(txtTamizajena.getText().equals("X") && evt.getClickCount()==1){
           txtTamizajena.setText("");
           txtTamizajen.setText("");
           txtTamizajep.setText("");
           txtTamizajenh.setText("");
           fechaTamizaje.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtTamizajenaMouseClicked

    private void txtPAPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPAPnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPAPn.getText().equals("") && evt.getClickCount()==1){
           txtPAPn.setText("X");
           txtPAPa.setText("");
           txtPAPnh.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(true);

        }else
        if(txtPAPn.getText().equals("X") && evt.getClickCount()==1){
           txtPAPn.setText("");
           txtPAPa.setText("");
           txtPAPnh.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtPAPnMouseClicked

    private void txtPAPaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPAPaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPAPa.getText().equals("") && evt.getClickCount()==1){
           txtPAPa.setText("X");
           txtPAPn.setText("");
           txtPAPnh.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(true);

        }else
        if(txtPAPa.getText().equals("X") && evt.getClickCount()==1){
           txtPAPa.setText("");
           txtPAPn.setText("");
           txtPAPnh.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtPAPaMouseClicked

    private void txtPAPnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPAPnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPAPnh.getText().equals("") && evt.getClickCount()==1){
           txtPAPnh.setText("X");
           txtPAPn.setText("");
           txtPAPa.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(false);

        }else
        if(txtPAPnh.getText().equals("X") && evt.getClickCount()==1){
           txtPAPnh.setText("");
           txtPAPn.setText("");
           txtPAPa.setText("");
           txtPAPna.setText("");
           fechaPAP.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtPAPnhMouseClicked

    private void txtPAPnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPAPnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPAPna.getText().equals("") && evt.getClickCount()==1){
           txtPAPna.setText("X");
           txtPAPn.setText("");
           txtPAPa.setText("");
           txtPAPnh.setText("");
           fechaPAP.setEnabled(false);

        }else
        if(txtPAPna.getText().equals("X") && evt.getClickCount()==1){
           txtPAPna.setText("");
           txtPAPn.setText("");
           txtPAPa.setText("");
           txtPAPnh.setText("");
           fechaPAP.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtPAPnaMouseClicked

    private void txtIVAAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIVAAnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIVAAn.getText().equals("") && evt.getClickCount()==1){
           txtIVAAn.setText("X");
           txtIVAAa.setText("");
           txtIVAAnh.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(true);

        }else
        if(txtIVAAn.getText().equals("X") && evt.getClickCount()==1){
           txtIVAAn.setText("");
           txtIVAAa.setText("");
           txtIVAAnh.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtIVAAnMouseClicked

    private void txtIVAAaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIVAAaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIVAAa.getText().equals("") && evt.getClickCount()==1){
           txtIVAAa.setText("X");
           txtIVAAn.setText("");
           txtIVAAnh.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(true);

        }else
        if(txtIVAAa.getText().equals("X") && evt.getClickCount()==1){
           txtIVAAa.setText("");
           txtIVAAn.setText("");
           txtIVAAnh.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtIVAAaMouseClicked

    private void txtIVAAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIVAAnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIVAAnh.getText().equals("") && evt.getClickCount()==1){
           txtIVAAnh.setText("X");
           txtIVAAa.setText("");
           txtIVAAn.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(false);

        }else
        if(txtIVAAnh.getText().equals("X") && evt.getClickCount()==1){
           txtIVAAnh.setText("");
           txtIVAAa.setText("");
           txtIVAAn.setText("");
           txtIVAAna.setText("");
           fechaIVAA.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtIVAAnhMouseClicked

    private void txtIVAAnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIVAAnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtIVAAna.getText().equals("") && evt.getClickCount()==1){
           txtIVAAna.setText("X");
           txtIVAAa.setText("");
           txtIVAAnh.setText("");
           txtIVAAn.setText("");
           fechaIVAA.setEnabled(false);

        }else
        if(txtIVAAna.getText().equals("X") && evt.getClickCount()==1){
           txtIVAAna.setText("");
           txtIVAAa.setText("");
           txtIVAAnh.setText("");
           txtIVAAn.setText("");
           fechaIVAA.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtIVAAnaMouseClicked

    private void txtColnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtColnMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtColn.getText().equals("") && evt.getClickCount()==1){
           txtColn.setText("X");
           txtCola.setText("");
           txtColnh.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(true);

        }else
        if(txtColn.getText().equals("X") && evt.getClickCount()==1){
           txtColn.setText("");
           txtCola.setText("");
           txtColnh.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtColnMouseClicked

    private void txtColaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtColaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtCola.getText().equals("") && evt.getClickCount()==1){
           txtCola.setText("X");
           txtColn.setText("");
           txtColnh.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(true);

        }else
        if(txtCola.getText().equals("X") && evt.getClickCount()==1){
           txtCola.setText("");
           txtColn.setText("");
           txtColnh.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(false);
        }
       }
    }//GEN-LAST:event_txtColaMouseClicked

    private void txtColnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtColnhMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtColnh.getText().equals("") && evt.getClickCount()==1){
           txtColnh.setText("X");
           txtCola.setText("");
           txtColn.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(false);

        }else
        if(txtColnh.getText().equals("X") && evt.getClickCount()==1){
           txtColnh.setText("");
           txtCola.setText("");
           txtColn.setText("");
           txtColna.setText("");
           fechaCol.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtColnhMouseClicked

    private void txtColnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtColnaMouseClicked
        if (lblIdEx.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtColna.getText().equals("") && evt.getClickCount()==1){
           txtColna.setText("X");
           txtCola.setText("");
           txtColnh.setText("");
           txtColn.setText("");
           fechaCol.setEnabled(false);

        }else
        if(txtColna.getText().equals("X") && evt.getClickCount()==1){
           txtColna.setText("");
           txtCola.setText("");
           txtColnh.setText("");
           txtColn.setText("");
           fechaCol.setEnabled(true);
        }
       }
    }//GEN-LAST:event_txtColnaMouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel35MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf2;
    public static javax.swing.JTextField ChkEdad2;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    public static javax.swing.JButton btnGuardar1;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkH1;
    public static javax.swing.JTextField chkH2;
    public static javax.swing.JTextField chkH3;
    public static com.toedter.calendar.JDateChooser dFechaH1;
    public static com.toedter.calendar.JDateChooser dFechaH2;
    public static com.toedter.calendar.JDateChooser dFechaH3;
    public static com.toedter.calendar.JDateChooser fechaBK;
    public static com.toedter.calendar.JDateChooser fechaCol;
    public static com.toedter.calendar.JDateChooser fechaELisa;
    public static com.toedter.calendar.JDateChooser fechaEX;
    public static com.toedter.calendar.JDateChooser fechaFTA;
    public static com.toedter.calendar.JDateChooser fechaFluor;
    public static com.toedter.calendar.JDateChooser fechaG1;
    public static com.toedter.calendar.JDateChooser fechaG2;
    public static com.toedter.calendar.JDateChooser fechaGota;
    public static com.toedter.calendar.JDateChooser fechaHTLVI;
    public static com.toedter.calendar.JDateChooser fechaHepatitis;
    public static com.toedter.calendar.JDateChooser fechaIFI;
    public static com.toedter.calendar.JDateChooser fechaIVAA;
    public static com.toedter.calendar.JDateChooser fechaLEU;
    public static com.toedter.calendar.JDateChooser fechaListeria;
    public static com.toedter.calendar.JDateChooser fechaMalaria;
    public static com.toedter.calendar.JDateChooser fechaNitritos;
    public static com.toedter.calendar.JDateChooser fechaPAP;
    public static com.toedter.calendar.JDateChooser fechaPrueba;
    public static com.toedter.calendar.JDateChooser fechaTG;
    public static com.toedter.calendar.JDateChooser fechaTHPA;
    public static com.toedter.calendar.JDateChooser fechaTORCH;
    public static com.toedter.calendar.JDateChooser fechaTamizaje;
    public static com.toedter.calendar.JDateChooser fechaUrocultivo;
    public static com.toedter.calendar.JDateChooser fechaVDRL1;
    public static com.toedter.calendar.JDateChooser fechaVDRL2;
    public static com.toedter.calendar.JDateChooser fechaVIH1;
    public static com.toedter.calendar.JDateChooser fechaVIH2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblActoMedico2;
    public static javax.swing.JLabel lblActoMedico3;
    public static javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblHepa;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblIdEx;
    public static javax.swing.JLabel lblMadreEXL;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtBKn;
    public static javax.swing.JTextField txtBKna;
    public static javax.swing.JTextField txtBKnh;
    public static javax.swing.JTextField txtBKp;
    public static javax.swing.JTextField txtCola;
    public static javax.swing.JTextField txtColn;
    public static javax.swing.JTextField txtColna;
    public static javax.swing.JTextField txtColnh;
    public static javax.swing.JTextField txtEXn;
    public static javax.swing.JTextField txtEXnh;
    public static javax.swing.JTextField txtEXp;
    public static javax.swing.JTextField txtElisana;
    public static javax.swing.JTextField txtElisanh;
    public static javax.swing.JTextField txtElisanr;
    public static javax.swing.JTextField txtElisar;
    public static javax.swing.JTextField txtFTAna;
    public static javax.swing.JTextField txtFTAnh;
    public static javax.swing.JTextField txtFTAnr;
    public static javax.swing.JTextField txtFTAr;
    public static javax.swing.JTextField txtFluorn;
    public static javax.swing.JTextField txtFluorna;
    public static javax.swing.JTextField txtFluornh;
    public static javax.swing.JTextField txtFluorp;
    public static javax.swing.JTextField txtG1A;
    public static javax.swing.JTextField txtG1N;
    public static javax.swing.JTextField txtG1NA;
    public static javax.swing.JTextField txtG2A;
    public static javax.swing.JTextField txtG2N;
    public static javax.swing.JTextField txtG2NA;
    public static javax.swing.JTextField txtGotan;
    public static javax.swing.JTextField txtGotanh;
    public static javax.swing.JTextField txtGotap;
    public static javax.swing.JTextField txtGotna;
    public static javax.swing.JTextField txtH1;
    public static javax.swing.JTextField txtH2;
    public static javax.swing.JTextField txtH3;
    public static javax.swing.JTextField txtHTLVLn;
    public static javax.swing.JTextField txtHTLVLna;
    public static javax.swing.JTextField txtHTLVLnh;
    public static javax.swing.JTextField txtHTLVLp;
    public static javax.swing.JTextField txtHepatitisn;
    public static javax.swing.JTextField txtHepatitisna;
    public static javax.swing.JTextField txtHepatitisnh;
    public static javax.swing.JTextField txtHepatitisp;
    public static javax.swing.JTextField txtIFIn;
    public static javax.swing.JTextField txtIFIna;
    public static javax.swing.JTextField txtIFInh;
    public static javax.swing.JTextField txtIFIp;
    public static javax.swing.JTextField txtIVAAa;
    public static javax.swing.JTextField txtIVAAn;
    public static javax.swing.JTextField txtIVAAna;
    public static javax.swing.JTextField txtIVAAnh;
    public static javax.swing.JTextField txtLEUn;
    public static javax.swing.JTextField txtLEUnh;
    public static javax.swing.JTextField txtLEUp;
    public static javax.swing.JTextField txtListerian;
    public static javax.swing.JTextField txtListeriana;
    public static javax.swing.JTextField txtListerianh;
    public static javax.swing.JTextField txtListeriap;
    public static javax.swing.JTextField txtMalarian;
    public static javax.swing.JTextField txtMalariana;
    public static javax.swing.JTextField txtMalarianh;
    public static javax.swing.JTextField txtMalariap;
    public static javax.swing.JTextField txtNitritosn;
    public static javax.swing.JTextField txtNitritosnh;
    public static javax.swing.JTextField txtNitritosp;
    public static javax.swing.JTextField txtPAPa;
    public static javax.swing.JTextField txtPAPn;
    public static javax.swing.JTextField txtPAPna;
    public static javax.swing.JTextField txtPAPnh;
    public static javax.swing.JTextField txtPruebanh;
    public static javax.swing.JTextField txtPruebanr;
    public static javax.swing.JTextField txtPruebar;
    public static javax.swing.JTextField txtTGA;
    public static javax.swing.JTextField txtTGN;
    public static javax.swing.JTextField txtTGNA;
    public static javax.swing.JTextField txtTGNH;
    public static javax.swing.JTextField txtTHPAna;
    public static javax.swing.JTextField txtTHPAnh;
    public static javax.swing.JTextField txtTHPAnr;
    public static javax.swing.JTextField txtTHPAr;
    public static javax.swing.JTextField txtTORCHn;
    public static javax.swing.JTextField txtTORCHna;
    public static javax.swing.JTextField txtTORCHnh;
    public static javax.swing.JTextField txtTORCHp;
    public static javax.swing.JTextField txtTamizajen;
    public static javax.swing.JTextField txtTamizajena;
    public static javax.swing.JTextField txtTamizajenh;
    public static javax.swing.JTextField txtTamizajep;
    public static javax.swing.JTextField txtUrocultivon;
    public static javax.swing.JTextField txtUrocultivona;
    public static javax.swing.JTextField txtUrocultivonh;
    public static javax.swing.JTextField txtUrocultivop;
    public static javax.swing.JTextField txtVDRL1a;
    public static javax.swing.JTextField txtVDRL1nh;
    public static javax.swing.JTextField txtVDRL1nr;
    public static javax.swing.JTextField txtVDRL2a;
    public static javax.swing.JTextField txtVDRL2na;
    public static javax.swing.JTextField txtVDRL2nh;
    public static javax.swing.JTextField txtVDRL2nr;
    public static javax.swing.JTextField txtVIH1nr;
    public static javax.swing.JTextField txtVIH2na;
    public static javax.swing.JTextField txtVIH2nh;
    public static javax.swing.JTextField txtVIH2nr;
    public static javax.swing.JTextField txtVIH2r;
    public static javax.swing.JTextField txtVIHnh;
    public static javax.swing.JTextField txtVIHr;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
