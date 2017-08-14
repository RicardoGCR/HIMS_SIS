/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAO;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAf;

import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalGa;

import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAn;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAp;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAtencionPrenatal;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalCabecera;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEm;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEf;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEl;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalExamenes;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFd;

import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFu;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalHo;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPmcie10;

import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPt;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalReferencias;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalTs;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVg;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVp;
import static vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId;
import static vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdPeso;



/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoPrincipal extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    public static String CpId = "";
    public RegistroEmbarazoPrincipal() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(new Color(126,140,141));
//        OP1.setVisible(true);  
//        OP2.setVisible(false);
//        OP3.setVisible(false);
//        OP4.setVisible(false);
        DBEA1.setVisible(false);
        DBEA2.setVisible(false);
        otros.setVisible(false);
        FA.setVisible(true);
        lblId.setVisible(false);
        lblIdActoMedico.setVisible(false);
        pnlConfirma.setVisible(false);
        lblFua.setVisible(false);
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
   
 
    public void antecedentesObtetricos(){
        RegistroEmbarazoAO AO =new RegistroEmbarazoAO();
        RegistroEmbarazo.ContenedorTablas.add(AO);
        RegistroEmbarazoAO.lblFP.setText(lblFP.getText());
        
        try {
            AO.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConsultorioExtCarnetPerinatalAO AO1 = new ConsultorioExtCarnetPerinatalAO();
        AO1.ConsultoriosExtAOListar(lblId.getText());  
        RegistroEmbarazoAO.lbMadreAO.setText(lblMadre.getText());
        if(RegistroEmbarazoAO.lblIdAO.getText().equals("")){
            RegistroEmbarazoAO.lblActoMedico.setText(RegistroEmbarazoPrincipal.lblActoMedico.getText());
            RegistroEmbarazoAO.lblIdActoMedico.setText(RegistroEmbarazoPrincipal.lblIdActoMedico.getText());
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void gestacionAnterior(){
        RegistroEmbarazoGA GA =new RegistroEmbarazoGA();
        RegistroEmbarazo.ContenedorTablas.add(GA);
        RegistroEmbarazoGA.lblFP.setText(lblFP.getText());
        try {
            GA.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConsultorioExtCarnetPerinatalGa GA1 = new ConsultorioExtCarnetPerinatalGa();
        GA1.ConsultoriosExtGAListar(lblId.getText()); 
        
        RegistroEmbarazoGA.lblMadreGA.setText(lblMadre.getText());
        if (RegistroEmbarazoGA.lblIdGA.getText().equals("") ){
            RegistroEmbarazoGA.btnGuardar.setEnabled(true);
            RegistroEmbarazoGA.btneditar.setEnabled(false);
            RegistroEmbarazoGA.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoGA.lblActoMedico.setText(lblActoMedico.getText());
        }

        RegistroEmbarazoGA.lblMadreGA.setText(lblMadre.getText());

        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void antecedentesFamiliares(){
        RegistroEmbarazoAF AF =new RegistroEmbarazoAF();
        RegistroEmbarazo.ContenedorTablas.add(AF);
        RegistroEmbarazoAF.lblFP.setText(lblFP.getText());
        try {
            AF.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConsultorioExtCarnetPerinatalAf AF1 = new ConsultorioExtCarnetPerinatalAf();
        AF1.ConsultoriosExtAFListar(lblId.getText()); 
        
        RegistroEmbarazoAF.lblMadreAf.setText(lblMadre.getText());
        if (RegistroEmbarazoAF.lblIdAF.getText().equals("") ){
            RegistroEmbarazoAF.btnGuardar.setEnabled(true);
            RegistroEmbarazoAF.btneditar.setEnabled(false);
            RegistroEmbarazoAF.lblActoMedico.setText(lblActoMedico.getText());
            RegistroEmbarazoAF.lblIdActoMedico.setText(lblIdActoMedico.getText());
        }
        
        
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void antecedentesPersonal(){
        RegistroEmbarazoAP AP =new RegistroEmbarazoAP();
        RegistroEmbarazo.ContenedorTablas.add(AP);
        RegistroEmbarazoAP.lblFP.setText(lblFP.getText());
        try {
            AP.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConsultorioExtCarnetPerinatalAp AP1 = new ConsultorioExtCarnetPerinatalAp();
        AP1.ConsultoriosExtAPListar(lblId.getText()); 
        
        RegistroEmbarazoAP.lblMadre.setText(lblMadre.getText());
        if (RegistroEmbarazoAP.lblIdAP.getText().equals("") ){
            RegistroEmbarazoAP.btnGuardar.setEnabled(true);
            RegistroEmbarazoAP.btneditar.setEnabled(false);
            RegistroEmbarazoAP.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoAP.lblActoMedico.setText(lblActoMedico.getText());
        }
        
        
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void vacunasPrevias(){
        RegistroEmbarazoVP VP =new RegistroEmbarazoVP();
        RegistroEmbarazo.ContenedorTablas.add(VP);
        RegistroEmbarazoVP.lblFP.setText(lblFP.getText());
        try {
            VP.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConsultorioExtCarnetPerinatalVp VP1 = new ConsultorioExtCarnetPerinatalVp();
        VP1.ConsultoriosExtVPListar(lblId.getText()); 
        
        RegistroEmbarazoVP.lblMadreVP.setText(lblMadre.getText());
        if (RegistroEmbarazoVP.lblIdVP.getText().equals("") ){
            RegistroEmbarazoVP.btnGuardar.setEnabled(true);
            RegistroEmbarazoVP.btneditar.setEnabled(false);
            RegistroEmbarazoVP.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoVP.lblActoMedico.setText(lblActoMedico.getText());
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void pesoTalla(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("<html>Peso y <br>talla</html>");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "peso";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalPt PT = new ConsultorioExtCarnetPerinatalPt();
        PT.ConsultoriosExtPTListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdPeso.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMant.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtPeso.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtTalla.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMant.setText("U");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtPeso.setEditable(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtTalla.setEditable(false);
        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void antitetanica(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Antitetánica");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "antitetanica";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalAn AN = new ConsultorioExtCarnetPerinatalAn();
        AN.ConsultoriosExtANListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdAn.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantAn.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNDosisPrevia.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis1.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis2.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNDosisPrevia.setEditable(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis1.setEditable(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtDosis2.setEditable(false);
        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void tipoSangre(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        try {
            RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Tipo de Sangre");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "tipoSangre";//
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
            ConsultorioExtCarnetPerinatalTs TS = new ConsultorioExtCarnetPerinatalTs();
            TS.ConsultoriosExtTsListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdGs.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantGs.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
        }
        } catch (Exception e) {
        }//
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void fumaDroga(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Fuma / Droga");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "fumaDroga";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalFd FD = new ConsultorioExtCarnetPerinatalFd();
        FD.ConsultoriosExtFdListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdFd.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantFd.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNCigarros.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtNCigarros.setEditable(false);
        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void fechaUM(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("<html>Fecha Ultima <br>de Menstruación</html>");
        
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "fum";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalFu FU = new ConsultorioExtCarnetPerinatalFu();
        FU.ConsultoriosExtFuListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdFum.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantFum.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtEcografia.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFUM.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaEco.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaProbableParto.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtEcografia.setEditable(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFUM.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaEco.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.dtFechaProbableParto.setEnabled(false);
        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void hospitalizacion(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Hospitalización");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "hos";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalHo HO = new ConsultorioExtCarnetPerinatalHo();
        HO.ConsultoriosExtHoListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdHos.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantHo.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtEcografia.setEditable(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaf3.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{

            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.txtEcografia.setEditable(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaf3.setEnabled(false);

        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void emergencia(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Emergencia");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "eme";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalEm EM = new ConsultorioExtCarnetPerinatalEm();
        EM.ConsultoriosExtEmListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdEme.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantEme.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());

            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaEmer.setEnabled(true);
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);

            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.fechaEmer.setEnabled(false);

        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void violenciaGenero(){
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V PT_A_TS_F_D_FUM_H_E_V =new RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V();
        RegistroEmbarazo.ContenedorTablas.add(PT_A_TS_F_D_FUM_H_E_V);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.LblTitulo.setText("Violencia / Género");
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P1.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P2.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P3.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P4.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P5.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P6.setVisible(true);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.PEmergencia.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P8.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.P9.setVisible(false);
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.opcionGuardar = "vg";
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblFP.setText(lblFP.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.setText(lblId.getText());
        RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalVg VG = new ConsultorioExtCarnetPerinatalVg();
        VG.ConsultoriosExtVgListar(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblCpId.getText());
        if(RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdVG.getText().equals("")){
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblMantVG.setText("I");
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.FechaVG.setEnabled(true);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnGuardar.setEnabled(false);
            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.btnModificar.setEnabled(true);

            RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.FechaVG.setEnabled(false);

        }
        try {
            PT_A_TS_F_D_FUM_H_E_V.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void examenFisico(){
        RegistroEmbarazoEXF EXF =new RegistroEmbarazoEXF();
        RegistroEmbarazo.ContenedorTablas.add(EXF);
        RegistroEmbarazoEXF.lblCpId.setText(lblId.getText());
        RegistroEmbarazoEXF.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalEf EF = new ConsultorioExtCarnetPerinatalEf();
        EF.ConsultoriosExtEfListar(RegistroEmbarazoEXF.lblCpId.getText());
        RegistroEmbarazoEXF.lblFP.setText(lblFP.getText());
        if(RegistroEmbarazoEXF.lblId.getText().equals("")){
            RegistroEmbarazoEXF.lblMant.setText("I");
            RegistroEmbarazoEXF.btnGuardar.setEnabled(true);
            RegistroEmbarazoEXF.btnModificar.setEnabled(false);
            RegistroEmbarazoEXF.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoEXF.lblActoMedico.setText(lblActoMedico.getText());
        }else{
            RegistroEmbarazoEXF.btnGuardar.setEnabled(false);
            RegistroEmbarazoEXF.btnModificar.setEnabled(true);
        }
        try {
            EXF.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    public void examenLaboratorio(){
        RegistroEmbarazoEXL EXL =new RegistroEmbarazoEXL();
        RegistroEmbarazo.ContenedorTablas.add(EXL);
        RegistroEmbarazoEXL.lblFP.setText(lblFP.getText());
        try {
            EXL.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConsultorioExtCarnetPerinatalEl EXL1 = new ConsultorioExtCarnetPerinatalEl();
        EXL1.ConsultoriosExtEXLListar(lblId.getText()); 
        
        RegistroEmbarazoEXL.lblMadreEXL.setText(lblMadre.getText());
        if (RegistroEmbarazoEXL.lblIdEx.getText().equals("") ){
            RegistroEmbarazoEXL.btnGuardar1.setEnabled(true);
            RegistroEmbarazoEXL.btneditar.setEnabled(false);
            RegistroEmbarazoEXL.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoEXL.lblActoMedico.setText(lblActoMedico.getText());
        }

        RegistroEmbarazoEXL.lblMadreEXL.setText(lblMadre.getText());
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    
    /////////////////////////////////
    public void PatologiasMaternas(){
        RegistroEmbarazoPM PM =new RegistroEmbarazoPM();
        RegistroEmbarazo.ContenedorTablas.add(PM);
        RegistroEmbarazoPM.lblFP.setText(lblFP.getText());
        
        try {
            PM.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConsultorioExtCarnetPerinatalPmcie10 listarPM = new ConsultorioExtCarnetPerinatalPmcie10();
        listarPM.listarRegistro(lblId.getText(),RegistroEmbarazoPM.tbPatologias);  
        RegistroEmbarazoPM.lblMadre.setText(lblMadre.getText());
        if(RegistroEmbarazoPM.lblIdPM.getText().equals("")){
            RegistroEmbarazoPM.lblActoMedico.setText(RegistroEmbarazoPrincipal.lblActoMedico.getText());
            RegistroEmbarazoPM.lblIdActoMedico.setText(RegistroEmbarazoPrincipal.lblIdActoMedico.getText());
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    //////////////////////////////////////
    public void Referencias(){
        RegistroEmbarazoPM1 REF =new RegistroEmbarazoPM1();
        RegistroEmbarazo.ContenedorTablas.add(REF);
        RegistroEmbarazoPM1.lblFP.setText(lblFP.getText());
        try {
            REF.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConsultorioExtCarnetPerinatalReferencias REF1 = new ConsultorioExtCarnetPerinatalReferencias();
        REF1.ConsultoriosExtREFListar(lblId.getText()); 
        
        RegistroEmbarazoPM1.lblMadre.setText(lblMadre.getText());
        if (RegistroEmbarazoPM1.lblIdPM1.getText().equals("") ){
            RegistroEmbarazoPM1.btnGuardar.setEnabled(true);
            RegistroEmbarazoPM1.btneditar.setEnabled(false);
            RegistroEmbarazoPM1.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoPM1.lblActoMedico.setText(lblActoMedico.getText());
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
     public void Psico(){
        RegistroEmbarazoPM2 psico =new RegistroEmbarazoPM2();
        RegistroEmbarazo.ContenedorTablas.add(psico);
        RegistroEmbarazoPM2.lblFP.setText(lblFP.getText());
        
        try {
            psico.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConsultorioExtCarnetPerinatalExamenes ex = new ConsultorioExtCarnetPerinatalExamenes();
        ex.ConsultoriosExtEXAMListar(lblId.getText());  
        RegistroEmbarazoPM2.lblMadre.setText(lblMadre.getText());
        if(RegistroEmbarazoPM2.lblIdPM2.getText().equals("")){
            RegistroEmbarazoPM2.lblActoMedico.setText(RegistroEmbarazoPrincipal.lblActoMedico.getText());
            RegistroEmbarazoPM2.lblIdActoMedico.setText(RegistroEmbarazoPrincipal.lblIdActoMedico.getText());
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnbuscar = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        btnbuscar2 = new javax.swing.JButton();
        lblId = new javax.swing.JTextField();
        btnTerminaConsulta = new javax.swing.JButton();
        btnbuscar3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        lblFua = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();
        lblActoMedico1 = new javax.swing.JLabel();
        lblActoMedico2 = new javax.swing.JLabel();
        lblMadre = new javax.swing.JLabel();
        pnlConfirma = new javax.swing.JPanel();
        btnSi1 = new javax.swing.JButton();
        btnNo1 = new javax.swing.JButton();
        lblTerminarConsulta = new javax.swing.JLabel();
        FA = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnGA = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btnNuevo2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAO = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnNuevo3 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btnNuevo8 = new javax.swing.JButton();
        DBEA1 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        btnAT = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        btnTS = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        btnPT = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        btnFD = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        btnFUM = new javax.swing.JButton();
        DBEA2 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        btnHO = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        btnEM = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        btnVG = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        btnEF = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        btnEL = new javax.swing.JButton();
        otros = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        btnAT1 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        btnTS1 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        btnPT1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnbuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Calificaciones-32.png"))); // NOI18N
        btnbuscar.setText("Filiación y antecedentes");
        btnbuscar.setContentAreaFilled(false);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnbuscar.setIconTextGap(30);
        btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnbuscar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
        btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-32.png"))); // NOI18N
        btnbuscar1.setText("<html>Datos Basales del <br>Embarazo Actual</html>");
        btnbuscar1.setContentAreaFilled(false);
        btnbuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnbuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnbuscar1.setIconTextGap(30);
        btnbuscar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1ActionPerformed(evt);
            }
        });

        btnbuscar2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnbuscar2.setForeground(new java.awt.Color(240, 240, 240));
        btnbuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bebé-32.png"))); // NOI18N
        btnbuscar2.setText("Atenciones Prenatales");
        btnbuscar2.setContentAreaFilled(false);
        btnbuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscar2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnbuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnbuscar2.setIconTextGap(30);
        btnbuscar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnbuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar2ActionPerformed(evt);
            }
        });

        lblId.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                lblIdCaretUpdate(evt);
            }
        });

        btnTerminaConsulta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTerminaConsulta.setForeground(new java.awt.Color(240, 240, 240));
        btnTerminaConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Apagar-32 (3).png"))); // NOI18N
        btnTerminaConsulta.setText("Terminar Consulta");
        btnTerminaConsulta.setContentAreaFilled(false);
        btnTerminaConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTerminaConsulta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTerminaConsulta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTerminaConsulta.setIconTextGap(30);
        btnTerminaConsulta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnTerminaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminaConsultaActionPerformed(evt);
            }
        });

        btnbuscar3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnbuscar3.setForeground(new java.awt.Color(240, 240, 240));
        btnbuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bacteria-32.png"))); // NOI18N
        btnbuscar3.setText("Patologías Maternas");
        btnbuscar3.setContentAreaFilled(false);
        btnbuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscar3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnbuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnbuscar3.setIconTextGap(30);
        btnbuscar3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnbuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html>Carnet de<br>Control Materno <br>Perinatal <span style=\"font-size:'15px'\"></span></html>");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        lblIdActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIdActoMedico.setForeground(new java.awt.Color(255, 255, 255));
        lblIdActoMedico.setText("ID AM");

        lblFua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFua.setForeground(new java.awt.Color(243, 156, 17));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFua, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTerminaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnbuscar2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnbuscar1)
                            .addComponent(btnbuscar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(btnbuscar)
                .addGap(36, 36, 36)
                .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnbuscar2)
                .addGap(36, 36, 36)
                .addComponent(btnbuscar3)
                .addGap(36, 36, 36)
                .addComponent(btnTerminaConsulta)
                .addGap(65, 65, 65)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1008, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(43, 43, 43));
        jPanel4.setPreferredSize(new java.awt.Dimension(1574, 113));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico.setText("jLabel1");

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(204, 204, 204));
        lblFP.setText("jLabel1");

        lblActoMedico1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico1.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico1.setText("Acto Médico");

        lblActoMedico2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico2.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico2.setText("Forma de Pago");

        lblMadre.setBackground(new java.awt.Color(0, 153, 0));
        lblMadre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(255, 255, 255));
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMadre.setIconTextGap(10);
        lblMadre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        pnlConfirma.setBackground(new java.awt.Color(232, 76, 61));

        btnSi1.setForeground(new java.awt.Color(240, 240, 240));
        btnSi1.setText("Si");
        btnSi1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSi1.setContentAreaFilled(false);
        btnSi1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSi1.setIconTextGap(30);
        btnSi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSi1ActionPerformed(evt);
            }
        });

        btnNo1.setForeground(new java.awt.Color(240, 240, 240));
        btnNo1.setText("No");
        btnNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnNo1.setContentAreaFilled(false);
        btnNo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNo1.setIconTextGap(30);
        btnNo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNo1ActionPerformed(evt);
            }
        });

        lblTerminarConsulta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTerminarConsulta.setForeground(new java.awt.Color(255, 255, 255));
        lblTerminarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Apagar-32.png"))); // NOI18N
        lblTerminarConsulta.setText("Terminar Consulta ?");
        lblTerminarConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTerminarConsultaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlConfirmaLayout = new javax.swing.GroupLayout(pnlConfirma);
        pnlConfirma.setLayout(pnlConfirmaLayout);
        pnlConfirmaLayout.setHorizontalGroup(
            pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfirmaLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTerminarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlConfirmaLayout.createSequentialGroup()
                        .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConfirmaLayout.setVerticalGroup(
            pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTerminarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblActoMedico1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblActoMedico2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblActoMedico)
                            .addComponent(lblFP))))
                .addGap(126, 126, 126)
                .addComponent(pnlConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblMadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActoMedico1)
                    .addComponent(lblActoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFP)
                    .addComponent(lblActoMedico2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        FA.setBackground(new java.awt.Color(248, 245, 245));

        jPanel6.setBackground(new java.awt.Color(45, 204, 112));
        jPanel6.setPreferredSize(new java.awt.Dimension(333, 111));

        btnGA.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnGA.setForeground(new java.awt.Color(240, 240, 240));
        btnGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Coche de niño-50.png"))); // NOI18N
        btnGA.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Gestación anterior</html>");
        btnGA.setContentAreaFilled(false);
        btnGA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGA.setIconTextGap(30);
        btnGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnGA)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGA, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(39, 174, 97));
        jPanel15.setPreferredSize(new java.awt.Dimension(78, 111));

        btnNuevo2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnNuevo2.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Familia hombre mujer-50.png"))); // NOI18N
        btnNuevo2.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Familiares</html>");
        btnNuevo2.setContentAreaFilled(false);
        btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo2.setIconTextGap(30);
        btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(btnNuevo2)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(25, 188, 157));
        jPanel5.setPreferredSize(new java.awt.Dimension(405, 111));

        btnAO.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnAO.setForeground(new java.awt.Color(240, 240, 240));
        btnAO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hojas-50.png"))); // NOI18N
        btnAO.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Obstétricos</html>");
        btnAO.setContentAreaFilled(false);
        btnAO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAO.setIconTextGap(30);
        btnAO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnAO)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAO, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(23, 160, 134));
        jPanel7.setPreferredSize(new java.awt.Dimension(399, 111));

        btnNuevo3.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnNuevo3.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Mujer de pie-50.png"))); // NOI18N
        btnNuevo3.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Personales</html>");
        btnNuevo3.setContentAreaFilled(false);
        btnNuevo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo3.setIconTextGap(30);
        btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnNuevo3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(50, 151, 219));
        jPanel8.setPreferredSize(new java.awt.Dimension(303, 111));

        btnNuevo8.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnNuevo8.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-50.png"))); // NOI18N
        btnNuevo8.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Vacunas Previas</html>");
        btnNuevo8.setToolTipText("");
        btnNuevo8.setContentAreaFilled(false);
        btnNuevo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo8.setIconTextGap(30);
        btnNuevo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnNuevo8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo8, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FALayout = new javax.swing.GroupLayout(FA);
        FA.setLayout(FALayout);
        FALayout.setHorizontalGroup(
            FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FALayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)))
        );
        FALayout.setVerticalGroup(
            FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FALayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        DBEA1.setBackground(new java.awt.Color(248, 245, 245));

        jPanel26.setBackground(new java.awt.Color(232, 76, 61));
        jPanel26.setPreferredSize(new java.awt.Dimension(269, 111));

        btnAT.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnAT.setForeground(new java.awt.Color(240, 240, 240));
        btnAT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-50.png"))); // NOI18N
        btnAT.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antitetánica</html>");
        btnAT.setContentAreaFilled(false);
        btnAT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAT.setIconTextGap(30);
        btnAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnATActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(btnAT)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAT, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(243, 156, 17));
        jPanel28.setPreferredSize(new java.awt.Dimension(78, 111));

        btnTS.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnTS.setForeground(new java.awt.Color(240, 240, 240));
        btnTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agua Filled-50.png"))); // NOI18N
        btnTS.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Tipo de Sangre");
        btnTS.setContentAreaFilled(false);
        btnTS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTS.setIconTextGap(30);
        btnTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addComponent(btnTS)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTS, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(193, 57, 45));
        jPanel29.setPreferredSize(new java.awt.Dimension(269, 111));

        btnPT.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnPT.setForeground(new java.awt.Color(240, 240, 240));
        btnPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Balanza-50.png"))); // NOI18N
        btnPT.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Peso y Talla</html>");
        btnPT.setContentAreaFilled(false);
        btnPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPT.setIconTextGap(30);
        btnPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(btnPT)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel30.setBackground(new java.awt.Color(241, 197, 14));
        jPanel30.setPreferredSize(new java.awt.Dimension(293, 111));

        btnFD.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnFD.setForeground(new java.awt.Color(51, 51, 51));
        btnFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Fumador-50.png"))); // NOI18N
        btnFD.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Fuma - Droga");
        btnFD.setContentAreaFilled(false);
        btnFD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFD.setIconTextGap(30);
        btnFD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFD, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFD, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel47.setBackground(new java.awt.Color(45, 204, 112));
        jPanel47.setPreferredSize(new java.awt.Dimension(10, 111));

        btnFUM.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnFUM.setForeground(new java.awt.Color(240, 240, 240));
        btnFUM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Calendario-50.png"))); // NOI18N
        btnFUM.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Fecha Ultima de Menstruación");
        btnFUM.setContentAreaFilled(false);
        btnFUM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFUM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFUM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFUM.setIconTextGap(30);
        btnFUM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFUMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFUM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFUM, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DBEA1Layout = new javax.swing.GroupLayout(DBEA1);
        DBEA1.setLayout(DBEA1Layout);
        DBEA1Layout.setHorizontalGroup(
            DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        DBEA1Layout.setVerticalGroup(
            DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        DBEA2.setBackground(new java.awt.Color(248, 245, 245));

        jPanel45.setBackground(new java.awt.Color(39, 174, 97));
        jPanel45.setPreferredSize(new java.awt.Dimension(301, 111));

        btnHO.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnHO.setForeground(new java.awt.Color(240, 240, 240));
        btnHO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50.png"))); // NOI18N
        btnHO.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Hospitalización");
        btnHO.setContentAreaFilled(false);
        btnHO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHO.setIconTextGap(30);
        btnHO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(btnHO)
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHO, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel46.setBackground(new java.awt.Color(23, 160, 134));
        jPanel46.setPreferredSize(new java.awt.Dimension(78, 111));

        btnEM.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnEM.setForeground(new java.awt.Color(240, 240, 240));
        btnEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Ambulancia-50.png"))); // NOI18N
        btnEM.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Emergencia");
        btnEM.setContentAreaFilled(false);
        btnEM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEM.setIconTextGap(30);
        btnEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(btnEM)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEM, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel48.setBackground(new java.awt.Color(25, 188, 157));
        jPanel48.setPreferredSize(new java.awt.Dimension(340, 111));

        btnVG.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnVG.setForeground(new java.awt.Color(240, 240, 240));
        btnVG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Género-50.png"))); // NOI18N
        btnVG.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Violencia / Género");
        btnVG.setContentAreaFilled(false);
        btnVG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnVG.setIconTextGap(30);
        btnVG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(btnVG)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVG, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel50.setBackground(new java.awt.Color(50, 151, 219));
        jPanel50.setPreferredSize(new java.awt.Dimension(291, 111));

        btnEF.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnEF.setForeground(new java.awt.Color(240, 240, 240));
        btnEF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Escaneo cuerpo-50.png"))); // NOI18N
        btnEF.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Examen Fisico");
        btnEF.setContentAreaFilled(false);
        btnEF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEF.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEF.setIconTextGap(30);
        btnEF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(btnEF)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEF, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel52.setBackground(new java.awt.Color(41, 127, 184));
        jPanel52.setPreferredSize(new java.awt.Dimension(10, 111));

        btnEL.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnEL.setForeground(new java.awt.Color(240, 240, 240));
        btnEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tubo de ensayo-50.png"))); // NOI18N
        btnEL.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Exámenes  de Laboratorio");
        btnEL.setContentAreaFilled(false);
        btnEL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEL.setIconTextGap(30);
        btnEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(btnEL, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEL, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DBEA2Layout = new javax.swing.GroupLayout(DBEA2);
        DBEA2.setLayout(DBEA2Layout);
        DBEA2Layout.setHorizontalGroup(
            DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        DBEA2Layout.setVerticalGroup(
            DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        otros.setBackground(new java.awt.Color(126, 140, 141));

        jPanel35.setBackground(new java.awt.Color(50, 151, 219));
        jPanel35.setPreferredSize(new java.awt.Dimension(265, 111));

        btnAT1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnAT1.setForeground(new java.awt.Color(240, 240, 240));
        btnAT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50 (1).png"))); // NOI18N
        btnAT1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Referencias</html>");
        btnAT1.setContentAreaFilled(false);
        btnAT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAT1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAT1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAT1.setIconTextGap(30);
        btnAT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(btnAT1)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAT1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel36.setBackground(new java.awt.Color(41, 127, 184));
        jPanel36.setPreferredSize(new java.awt.Dimension(78, 111));

        btnTS1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        btnTS1.setForeground(new java.awt.Color(240, 240, 240));
        btnTS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50 (2).png"))); // NOI18N
        btnTS1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Psicoprofilaxis, Estimulación pre natal<br>&nbsp;&nbsp;&nbsp;&nbsp;Plan de parto, Alojada en casa de espera<br>&nbsp;&nbsp;&nbsp;&nbsp;HCMP, Producto de la concepción</html>");
        btnTS1.setContentAreaFilled(false);
        btnTS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTS1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTS1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTS1.setIconTextGap(30);
        btnTS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTS1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(btnTS1)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTS1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        jPanel37.setBackground(new java.awt.Color(25, 188, 157));
        jPanel37.setPreferredSize(new java.awt.Dimension(371, 111));

        btnPT1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        btnPT1.setForeground(new java.awt.Color(240, 240, 240));
        btnPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bacteria-50.png"))); // NOI18N
        btnPT1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Patologías Maternas<br>&nbsp;&nbsp;&nbsp;&nbsp;CIE 10 Diagnosticadas</html>");
        btnPT1.setContentAreaFilled(false);
        btnPT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPT1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPT1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPT1.setIconTextGap(30);
        btnPT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPT1MouseClicked(evt);
            }
        });
        btnPT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(btnPT1)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout otrosLayout = new javax.swing.GroupLayout(otros);
        otros.setLayout(otrosLayout);
        otrosLayout.setHorizontalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        otrosLayout.setVerticalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
                    .addComponent(FA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DBEA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(DBEA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(otros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(FA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DBEA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DBEA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(otros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
//        OP1.setVisible(true);
//        OP2.setVisible(false);
//        OP3.setVisible(false);
//        OP4.setVisible(false);
        FA.setVisible(true);
        DBEA1.setVisible(false);
        DBEA2.setVisible(false);
        otros.setVisible(false);
        this.getContentPane().setBackground(new Color(126,140,141)); 
//        LblTitulo.setText("Filiación y antecedentes");
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
//        OP1.setVisible(false);
//        OP2.setVisible(true);
//        OP3.setVisible(false);
        DBEA1.setVisible(true);
        DBEA2.setVisible(true);
        FA.setVisible(false);
        otros.setVisible(false);
        this.getContentPane().setBackground(new Color(126,140,141)); 
//        LblTitulo.setText("Datos Basales del Embarazo Actual");
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void btnAOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAOActionPerformed
        antecedentesObtetricos();
    }//GEN-LAST:event_btnAOActionPerformed

    private void btnGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGAActionPerformed
        gestacionAnterior();
    }//GEN-LAST:event_btnGAActionPerformed

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        antecedentesFamiliares();
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
       antecedentesPersonal();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void btnATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnATActionPerformed
        antitetanica();
    }//GEN-LAST:event_btnATActionPerformed

    private void btnTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTSActionPerformed
        tipoSangre();
    }//GEN-LAST:event_btnTSActionPerformed

    private void btnPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPTActionPerformed
        pesoTalla();
    }//GEN-LAST:event_btnPTActionPerformed

    private void btnFDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFDActionPerformed
        fumaDroga();
    }//GEN-LAST:event_btnFDActionPerformed

    private void btnHOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHOActionPerformed
        hospitalizacion();
    }//GEN-LAST:event_btnHOActionPerformed

    private void btnEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEMActionPerformed
        emergencia();
    }//GEN-LAST:event_btnEMActionPerformed

    private void btnFUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFUMActionPerformed
        fechaUM();
    }//GEN-LAST:event_btnFUMActionPerformed

    private void btnVGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVGActionPerformed
        violenciaGenero();
    }//GEN-LAST:event_btnVGActionPerformed

    private void btnEFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEFActionPerformed
        examenFisico();
    }//GEN-LAST:event_btnEFActionPerformed

    private void btnELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELActionPerformed
       examenLaboratorio();
    }//GEN-LAST:event_btnELActionPerformed

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
//        OP1.setVisible(false);
//        OP2.setVisible(false);
//        OP3.setVisible(true);
//        OP4.setVisible(false);
        RegistroEmbarazoAtencionesP AP =new RegistroEmbarazoAtencionesP();
        RegistroEmbarazo.ContenedorTablas.add(AP);
        RegistroEmbarazoAtencionesP.lblIdCp.setText(lblId.getText());
        RegistroEmbarazoAtencionesP.lblMadre.setText(lblMadre.getText());
        ConsultorioExtCarnetPerinatalAtencionPrenatal consultorio1 = new ConsultorioExtCarnetPerinatalAtencionPrenatal();
        RegistroEmbarazoAtencionesP.validaAtencionPrenatal(RegistroEmbarazoAtencionesP.lblIdCp.getText(), "1");
        RegistroEmbarazoAtencionesP.lblFP.setText(lblFP.getText());
        if(RegistroEmbarazoAtencionesP.lblId.getText().equals("")){
            RegistroEmbarazoAtencionesP.lblActoMedico.setText(lblActoMedico.getText());
            RegistroEmbarazoAtencionesP.lblIdActoMedico.setText(lblIdActoMedico.getText());
            RegistroEmbarazoAtencionesP.txtFUA.setText(lblFua.getText());
        }
        try {
            AP.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(RegistroEmbarazo.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void btnNuevo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo8ActionPerformed
        vacunasPrevias();
    }//GEN-LAST:event_btnNuevo8ActionPerformed

    private void lblIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblIdCaretUpdate
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        consultorio1.mostrarDatosCabecera(lblId.getText());
    }//GEN-LAST:event_lblIdCaretUpdate

    private void btnTerminaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminaConsultaActionPerformed
        pnlConfirma.setVisible(true);
    }//GEN-LAST:event_btnTerminaConsultaActionPerformed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
//        OP1.setVisible(false);
//        OP2.setVisible(false);
//        OP3.setVisible(false);
//        OP4.setVisible(true);
        FA.setVisible(false);
        DBEA1.setVisible(false);
        DBEA2.setVisible(false);
        otros.setVisible(true);
//        LblTitulo.setText("Pienso y digo, que es esto??");
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void btnSi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSi1ActionPerformed
        if(btnSi1.getText().equals("Si")){ // Al guardar
            ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
            consultorio1.nombreEstablecimiento();
            consultorio1.mantenimientoConsultorioExtCarnetPerinatalCabecera("T",RegistroEmbarazo.lblID.getText());//para cambiar el estado de triaje de pendiente a ya atendido
            RegistroEmbarazo.pnlControl.setVisible(false);
            RegistroEmbarazo.btnInicio.setVisible(false);
            RegistroEmbarazo.jTabbedPane1.setSelectedIndex(0);
            RegistroEmbarazo.btnGuardar.setVisible(false);
            lblId.setText("");
            this.dispose();
        } 
    }//GEN-LAST:event_btnSi1ActionPerformed

    private void btnNo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNo1ActionPerformed
        pnlConfirma.setVisible(false);
    }//GEN-LAST:event_btnNo1ActionPerformed

    private void btnAT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAT1ActionPerformed
        Referencias();
    }//GEN-LAST:event_btnAT1ActionPerformed

    private void btnTS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTS1ActionPerformed
       Psico();
    }//GEN-LAST:event_btnTS1ActionPerformed

    private void btnPT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPT1ActionPerformed

    private void btnPT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPT1MouseClicked
        PatologiasMaternas();
    }//GEN-LAST:event_btnPT1MouseClicked

    private void lblTerminarConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTerminarConsultaMouseClicked

    }//GEN-LAST:event_lblTerminarConsultaMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
//        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
//        consultorio1.mostrarDatosCabecera(lblId.getText());
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DBEA1;
    private javax.swing.JPanel DBEA2;
    private javax.swing.JPanel FA;
    private javax.swing.JButton btnAO;
    private javax.swing.JButton btnAT;
    private javax.swing.JButton btnAT1;
    private javax.swing.JButton btnEF;
    private javax.swing.JButton btnEL;
    private javax.swing.JButton btnEM;
    private javax.swing.JButton btnFD;
    private javax.swing.JButton btnFUM;
    private javax.swing.JButton btnGA;
    private javax.swing.JButton btnHO;
    private javax.swing.JButton btnNo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnNuevo8;
    private javax.swing.JButton btnPT;
    private javax.swing.JButton btnPT1;
    private javax.swing.JButton btnSi1;
    private javax.swing.JButton btnTS;
    private javax.swing.JButton btnTS1;
    private javax.swing.JButton btnTerminaConsulta;
    private javax.swing.JButton btnVG;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblActoMedico1;
    public static javax.swing.JLabel lblActoMedico2;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblFua;
    public static javax.swing.JTextField lblId;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblMadre;
    public static javax.swing.JLabel lblTerminarConsulta;
    private javax.swing.JPanel otros;
    private javax.swing.JPanel pnlConfirma;
    // End of variables declaration//GEN-END:variables
}
