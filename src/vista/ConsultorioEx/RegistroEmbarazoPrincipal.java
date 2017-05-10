/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
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
        this.getContentPane().setBackground(new Color(248,245,245));
        OP1.setVisible(true);  
        OP2.setVisible(false);
        OP3.setVisible(false);
        OP4.setVisible(false);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
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
        OP1 = new javax.swing.JPanel();
        OP2 = new javax.swing.JPanel();
        lblMadre = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        btnbuscar2 = new javax.swing.JButton();
        OP3 = new javax.swing.JPanel();
        lblId = new javax.swing.JTextField();
        btnTerminaConsulta = new javax.swing.JButton();
        btnbuscar3 = new javax.swing.JButton();
        pnlConfirma = new javax.swing.JPanel();
        lblConfirma = new javax.swing.JLabel();
        btnSi1 = new javax.swing.JButton();
        btnNo1 = new javax.swing.JButton();
        OP4 = new javax.swing.JPanel();
        LblTitulo = new javax.swing.JLabel();
        FA = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblAF = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblAO = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblGA = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnGA = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        btnNuevo2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAO = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnNuevo3 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        lblAF1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnNuevo8 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        lblVP = new javax.swing.JLabel();
        DBEA1 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        lblTS = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        lblPT = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        lblAT = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        btnAT = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        btnTS = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        btnPT = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        btnFD = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        lblFD = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        btnFUM = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        lblFUM = new javax.swing.JLabel();
        DBEA2 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        lblEM = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        lblHO = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        btnHO = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        btnEM = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        btnVG = new javax.swing.JButton();
        jPanel49 = new javax.swing.JPanel();
        lblVG = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        btnEF = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        lblEF = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        btnEL = new javax.swing.JButton();
        jPanel53 = new javax.swing.JPanel();
        lblEL = new javax.swing.JLabel();
        lblFua = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        otros = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        lblTS1 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        lblPT1 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        lblAT1 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        btnAT1 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        btnTS1 = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        btnPT1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));

        OP1.setBackground(new java.awt.Color(39, 174, 97));

        javax.swing.GroupLayout OP1Layout = new javax.swing.GroupLayout(OP1);
        OP1.setLayout(OP1Layout);
        OP1Layout.setHorizontalGroup(
            OP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        OP1Layout.setVerticalGroup(
            OP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        OP2.setBackground(new java.awt.Color(39, 174, 97));

        javax.swing.GroupLayout OP2Layout = new javax.swing.GroupLayout(OP2);
        OP2.setLayout(OP2Layout);
        OP2Layout.setHorizontalGroup(
            OP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OP2Layout.setVerticalGroup(
            OP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lblMadre.setBackground(new java.awt.Color(0, 153, 0));
        lblMadre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(255, 255, 255));
        lblMadre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada-80.png"))); // NOI18N
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMadre.setIconTextGap(10);
        lblMadre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        OP3.setBackground(new java.awt.Color(39, 174, 97));

        javax.swing.GroupLayout OP3Layout = new javax.swing.GroupLayout(OP3);
        OP3.setLayout(OP3Layout);
        OP3Layout.setHorizontalGroup(
            OP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        OP3Layout.setVerticalGroup(
            OP3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        lblId.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                lblIdCaretUpdate(evt);
            }
        });

        btnTerminaConsulta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTerminaConsulta.setForeground(new java.awt.Color(240, 240, 240));
        btnTerminaConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Apagar-40.png"))); // NOI18N
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

        pnlConfirma.setBackground(new java.awt.Color(232, 76, 61));

        lblConfirma.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblConfirma.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfirma.setText("Desea cerrar la consulta?");

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

        javax.swing.GroupLayout pnlConfirmaLayout = new javax.swing.GroupLayout(pnlConfirma);
        pnlConfirma.setLayout(pnlConfirmaLayout);
        pnlConfirmaLayout.setHorizontalGroup(
            pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfirmaLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblConfirma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlConfirmaLayout.setVerticalGroup(
            pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmaLayout.createSequentialGroup()
                .addComponent(lblConfirma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlConfirmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSi1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OP4.setBackground(new java.awt.Color(39, 174, 97));

        javax.swing.GroupLayout OP4Layout = new javax.swing.GroupLayout(OP4);
        OP4.setLayout(OP4Layout);
        OP4Layout.setHorizontalGroup(
            OP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        OP4Layout.setVerticalGroup(
            OP4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMadre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(OP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnbuscar1)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnbuscar2)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(OP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(OP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnbuscar3)
                .addGap(26, 26, 26)
                .addComponent(OP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTerminaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(pnlConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMadre)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(OP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnbuscar)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(OP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnbuscar2)
                            .addComponent(OP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(btnbuscar3))
                    .addComponent(OP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnTerminaConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1004, Short.MAX_VALUE))
        );

        LblTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(51, 51, 51));
        LblTitulo.setText("Filiación y antecedentes");

        FA.setBackground(new java.awt.Color(248, 245, 245));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        lblAF.setBackground(new java.awt.Color(255, 255, 255));
        lblAF.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        lblAF.setForeground(new java.awt.Color(102, 102, 102));
        lblAF.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Familiares</html>");
        lblAF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAFMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(lblAF, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAF, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblAO.setBackground(new java.awt.Color(255, 255, 255));
        lblAO.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        lblAO.setForeground(new java.awt.Color(102, 102, 102));
        lblAO.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Obstétricos</html>");
        lblAO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAOMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblAO, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAO, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblGA.setBackground(new java.awt.Color(255, 255, 255));
        lblGA.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        lblGA.setForeground(new java.awt.Color(102, 102, 102));
        lblGA.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Gestación anterior</html>");
        lblGA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblGA)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGA, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(141, 68, 173));

        btnGA.setForeground(new java.awt.Color(240, 240, 240));
        btnGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Coche de niño-50.png"))); // NOI18N
        btnGA.setContentAreaFilled(false);
        btnGA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnGA, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(248, 26, 70));
        jPanel15.setPreferredSize(new java.awt.Dimension(78, 85));

        btnNuevo2.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Familia hombre mujer-50.png"))); // NOI18N
        btnNuevo2.setContentAreaFilled(false);
        btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnNuevo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(154, 89, 181));

        btnAO.setForeground(new java.awt.Color(240, 240, 240));
        btnAO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hojas-50.png"))); // NOI18N
        btnAO.setContentAreaFilled(false);
        btnAO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnAO, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(222, 84, 84));

        btnNuevo3.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Mujer de pie-50.png"))); // NOI18N
        btnNuevo3.setContentAreaFilled(false);
        btnNuevo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnNuevo3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setPreferredSize(new java.awt.Dimension(597, 85));

        lblAF1.setBackground(new java.awt.Color(255, 255, 255));
        lblAF1.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        lblAF1.setForeground(new java.awt.Color(102, 102, 102));
        lblAF1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antecedentes Personales</html>");
        lblAF1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAF1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(lblAF1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAF1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(250, 136, 79));

        btnNuevo8.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-50.png"))); // NOI18N
        btnNuevo8.setContentAreaFilled(false);
        btnNuevo8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnNuevo8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnNuevo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setPreferredSize(new java.awt.Dimension(597, 85));

        lblVP.setBackground(new java.awt.Color(255, 255, 255));
        lblVP.setFont(new java.awt.Font("Segoe UI Light", 0, 22)); // NOI18N
        lblVP.setForeground(new java.awt.Color(102, 102, 102));
        lblVP.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Vacunas Previas</html>");
        lblVP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(lblVP, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVP, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FALayout = new javax.swing.GroupLayout(FA);
        FA.setLayout(FALayout);
        FALayout.setHorizontalGroup(
            FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FALayout.createSequentialGroup()
                        .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(FALayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FALayout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FALayout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 18, Short.MAX_VALUE))
        );
        FALayout.setVerticalGroup(
            FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        DBEA1.setBackground(new java.awt.Color(248, 245, 245));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        lblTS.setBackground(new java.awt.Color(255, 255, 255));
        lblTS.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTS.setForeground(new java.awt.Color(102, 102, 102));
        lblTS.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Tipo de Sangre");
        lblTS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTSMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTS)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTS, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        lblPT.setBackground(new java.awt.Color(255, 255, 255));
        lblPT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPT.setForeground(new java.awt.Color(102, 102, 102));
        lblPT.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Peso y Talla</html>");
        lblPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPTMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPT)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPT, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        lblAT.setBackground(new java.awt.Color(255, 255, 255));
        lblAT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAT.setForeground(new java.awt.Color(102, 102, 102));
        lblAT.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Antitetánica</html>");
        lblAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblATMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAT)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAT, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel26.setBackground(new java.awt.Color(232, 76, 61));

        btnAT.setForeground(new java.awt.Color(240, 240, 240));
        btnAT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Jeringa-50.png"))); // NOI18N
        btnAT.setContentAreaFilled(false);
        btnAT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnAT, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(243, 156, 17));
        jPanel28.setPreferredSize(new java.awt.Dimension(78, 85));

        btnTS.setForeground(new java.awt.Color(240, 240, 240));
        btnTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agua Filled-50.png"))); // NOI18N
        btnTS.setContentAreaFilled(false);
        btnTS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnTS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(193, 57, 45));

        btnPT.setForeground(new java.awt.Color(240, 240, 240));
        btnPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Balanza-50.png"))); // NOI18N
        btnPT.setContentAreaFilled(false);
        btnPT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnPT, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel30.setBackground(new java.awt.Color(241, 197, 14));

        btnFD.setForeground(new java.awt.Color(240, 240, 240));
        btnFD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Fumador-50.png"))); // NOI18N
        btnFD.setContentAreaFilled(false);
        btnFD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnFD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(btnFD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        lblFD.setBackground(new java.awt.Color(255, 255, 255));
        lblFD.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFD.setForeground(new java.awt.Color(102, 102, 102));
        lblFD.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Fuma - Droga");
        lblFD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFDMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFD)
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFD, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel47.setBackground(new java.awt.Color(45, 204, 112));

        btnFUM.setForeground(new java.awt.Color(240, 240, 240));
        btnFUM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Calendario-50.png"))); // NOI18N
        btnFUM.setContentAreaFilled(false);
        btnFUM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFUM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnFUM, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnFUM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        lblFUM.setBackground(new java.awt.Color(255, 255, 255));
        lblFUM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblFUM.setForeground(new java.awt.Color(102, 102, 102));
        lblFUM.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Fecha Ultima de Menstruación");
        lblFUM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFUMMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFUM, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFUM, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DBEA1Layout = new javax.swing.GroupLayout(DBEA1);
        DBEA1.setLayout(DBEA1Layout);
        DBEA1Layout.setHorizontalGroup(
            DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DBEA1Layout.createSequentialGroup()
                        .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DBEA1Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DBEA1Layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DBEA1Layout.createSequentialGroup()
                        .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        DBEA1Layout.setVerticalGroup(
            DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        DBEA2.setBackground(new java.awt.Color(248, 245, 245));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));

        lblEM.setBackground(new java.awt.Color(255, 255, 255));
        lblEM.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEM.setForeground(new java.awt.Color(102, 102, 102));
        lblEM.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Emergencia");
        lblEM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEMMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEM)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEM, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        lblHO.setBackground(new java.awt.Color(255, 255, 255));
        lblHO.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHO.setForeground(new java.awt.Color(102, 102, 102));
        lblHO.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Hospitalización");
        lblHO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHOMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHO)
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHO, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel45.setBackground(new java.awt.Color(39, 174, 97));

        btnHO.setForeground(new java.awt.Color(240, 240, 240));
        btnHO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50.png"))); // NOI18N
        btnHO.setContentAreaFilled(false);
        btnHO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnHO, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel46.setBackground(new java.awt.Color(23, 160, 134));
        jPanel46.setPreferredSize(new java.awt.Dimension(78, 85));

        btnEM.setForeground(new java.awt.Color(240, 240, 240));
        btnEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Ambulancia-50.png"))); // NOI18N
        btnEM.setContentAreaFilled(false);
        btnEM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnEM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel48.setBackground(new java.awt.Color(25, 188, 157));

        btnVG.setForeground(new java.awt.Color(240, 240, 240));
        btnVG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Género-50.png"))); // NOI18N
        btnVG.setContentAreaFilled(false);
        btnVG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVG.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnVG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnVG, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));

        lblVG.setBackground(new java.awt.Color(255, 255, 255));
        lblVG.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblVG.setForeground(new java.awt.Color(102, 102, 102));
        lblVG.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Violencia / Género");
        lblVG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVGMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVG, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVG)
        );

        jPanel50.setBackground(new java.awt.Color(50, 151, 219));

        btnEF.setForeground(new java.awt.Color(240, 240, 240));
        btnEF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Escaneo cuerpo-50.png"))); // NOI18N
        btnEF.setContentAreaFilled(false);
        btnEF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnEF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEF, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));

        lblEF.setBackground(new java.awt.Color(255, 255, 255));
        lblEF.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEF.setForeground(new java.awt.Color(102, 102, 102));
        lblEF.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Examen Fisico");
        lblEF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEFMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEF)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEF)
        );

        jPanel52.setBackground(new java.awt.Color(41, 127, 184));

        btnEL.setForeground(new java.awt.Color(240, 240, 240));
        btnEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tubo de ensayo-50.png"))); // NOI18N
        btnEL.setContentAreaFilled(false);
        btnEL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnEL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnEL, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));

        lblEL.setBackground(new java.awt.Color(255, 255, 255));
        lblEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEL.setForeground(new java.awt.Color(102, 102, 102));
        lblEL.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Exámenes  de Laboratorio");
        lblEL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblELMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEL)
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEL)
        );

        javax.swing.GroupLayout DBEA2Layout = new javax.swing.GroupLayout(DBEA2);
        DBEA2.setLayout(DBEA2Layout);
        DBEA2Layout.setHorizontalGroup(
            DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DBEA2Layout.createSequentialGroup()
                        .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(DBEA2Layout.createSequentialGroup()
                                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(DBEA2Layout.createSequentialGroup()
                                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(DBEA2Layout.createSequentialGroup()
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(DBEA2Layout.createSequentialGroup()
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DBEA2Layout.setVerticalGroup(
            DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DBEA2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DBEA2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lblFua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFua.setForeground(new java.awt.Color(243, 156, 17));

        jPanel4.setBackground(new java.awt.Color(65, 65, 65));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(255, 255, 255));
        lblActoMedico.setText("jLabel1");

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(255, 255, 255));
        lblFP.setText("jLabel1");

        lblIdActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIdActoMedico.setForeground(new java.awt.Color(255, 255, 255));
        lblIdActoMedico.setText("ID AM");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblActoMedico)
                .addGap(230, 230, 230)
                .addComponent(lblFP)
                .addGap(213, 213, 213)
                .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(949, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActoMedico)
                    .addComponent(lblFP)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        otros.setBackground(new java.awt.Color(248, 245, 245));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        lblTS1.setBackground(new java.awt.Color(255, 255, 255));
        lblTS1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTS1.setForeground(new java.awt.Color(102, 102, 102));
        lblTS1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Psicoprofilaxis, Estimulación pre natal<br>&nbsp;&nbsp;&nbsp;&nbsp;Plan de parto, Alojada en casa de espera<br>&nbsp;&nbsp;&nbsp;&nbsp;HCMP, Producto de la concepción</html>");
        lblTS1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTS1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(lblTS1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 121, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTS1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        lblPT1.setBackground(new java.awt.Color(255, 255, 255));
        lblPT1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblPT1.setForeground(new java.awt.Color(102, 102, 102));
        lblPT1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Patologías Maternas<br>&nbsp;&nbsp;&nbsp;&nbsp;CIE 10 Diagnosticadas</html>");
        lblPT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPT1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPT1)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPT1)
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        lblAT1.setBackground(new java.awt.Color(255, 255, 255));
        lblAT1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAT1.setForeground(new java.awt.Color(102, 102, 102));
        lblAT1.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;Referencias</html>");
        lblAT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAT1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAT1)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAT1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        jPanel35.setBackground(new java.awt.Color(50, 151, 219));

        btnAT1.setForeground(new java.awt.Color(240, 240, 240));
        btnAT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50 (1).png"))); // NOI18N
        btnAT1.setContentAreaFilled(false);
        btnAT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAT1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnAT1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel36.setBackground(new java.awt.Color(41, 127, 184));
        jPanel36.setPreferredSize(new java.awt.Dimension(78, 85));

        btnTS1.setForeground(new java.awt.Color(240, 240, 240));
        btnTS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hospital-50 (2).png"))); // NOI18N
        btnTS1.setContentAreaFilled(false);
        btnTS1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTS1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnTS1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTS1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel37.setBackground(new java.awt.Color(25, 188, 157));

        btnPT1.setForeground(new java.awt.Color(240, 240, 240));
        btnPT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Bacteria-50.png"))); // NOI18N
        btnPT1.setContentAreaFilled(false);
        btnPT1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPT1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            .addComponent(btnPT1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout otrosLayout = new javax.swing.GroupLayout(otros);
        otros.setLayout(otrosLayout);
        otrosLayout.setHorizontalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(otrosLayout.createSequentialGroup()
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        otrosLayout.setVerticalGroup(
            otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(lblFua, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(DBEA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(120, 120, 120)
                                        .addComponent(DBEA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(otros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblTitulo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblFua, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(FA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DBEA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DBEA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAOMouseClicked
        antecedentesObtetricos();
    }//GEN-LAST:event_lblAOMouseClicked

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        OP1.setVisible(true);
        OP2.setVisible(false);
        OP3.setVisible(false);
        OP4.setVisible(false);
        FA.setVisible(true);
        DBEA1.setVisible(false);
        DBEA2.setVisible(false);
        otros.setVisible(false);
        LblTitulo.setText("Filiación y antecedentes");
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        OP1.setVisible(false);
        OP2.setVisible(true);
        OP3.setVisible(false);
        DBEA1.setVisible(true);
        DBEA2.setVisible(true);
        FA.setVisible(false);
        LblTitulo.setText("Datos Basales del Embarazo Actual");
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void btnAOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAOActionPerformed
        antecedentesObtetricos();
    }//GEN-LAST:event_btnAOActionPerformed

    private void btnGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGAActionPerformed
        gestacionAnterior();
    }//GEN-LAST:event_btnGAActionPerformed

    private void lblGAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGAMouseClicked
        gestacionAnterior();
    }//GEN-LAST:event_lblGAMouseClicked

    private void lblAFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAFMouseClicked
        antecedentesFamiliares();
    }//GEN-LAST:event_lblAFMouseClicked

    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        antecedentesFamiliares();
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
       antecedentesPersonal();
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void lblTSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTSMouseClicked
        tipoSangre();
    }//GEN-LAST:event_lblTSMouseClicked

    private void lblPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPTMouseClicked
        pesoTalla();
    }//GEN-LAST:event_lblPTMouseClicked

    private void lblATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblATMouseClicked
        antitetanica();
    }//GEN-LAST:event_lblATMouseClicked

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

    private void lblFDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFDMouseClicked
        fumaDroga();
    }//GEN-LAST:event_lblFDMouseClicked

    private void lblEMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEMMouseClicked
        emergencia();
    }//GEN-LAST:event_lblEMMouseClicked

    private void lblFUMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFUMMouseClicked
        fechaUM();
    }//GEN-LAST:event_lblFUMMouseClicked

    private void lblHOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHOMouseClicked
        hospitalizacion();
    }//GEN-LAST:event_lblHOMouseClicked

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

    private void lblVGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVGMouseClicked
        violenciaGenero();
    }//GEN-LAST:event_lblVGMouseClicked

    private void btnEFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEFActionPerformed
        examenFisico();
    }//GEN-LAST:event_btnEFActionPerformed

    private void lblEFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEFMouseClicked
        examenFisico();
    }//GEN-LAST:event_lblEFMouseClicked

    private void btnELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELActionPerformed
       examenLaboratorio();
    }//GEN-LAST:event_btnELActionPerformed

    private void lblELMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblELMouseClicked
        examenLaboratorio();
    }//GEN-LAST:event_lblELMouseClicked

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
        OP1.setVisible(false);
        OP2.setVisible(false);
        OP3.setVisible(true);
        OP4.setVisible(false);
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
            Logger.getLogger(RegistroSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void btnNuevo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo8ActionPerformed
        vacunasPrevias();
    }//GEN-LAST:event_btnNuevo8ActionPerformed

    private void lblVPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVPMouseClicked
        vacunasPrevias();
    }//GEN-LAST:event_lblVPMouseClicked

    private void lblIdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblIdCaretUpdate
        ConsultorioExtCarnetPerinatalCabecera consultorio1 = new ConsultorioExtCarnetPerinatalCabecera();
        consultorio1.mostrarDatosCabecera(lblId.getText());
    }//GEN-LAST:event_lblIdCaretUpdate

    private void btnTerminaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminaConsultaActionPerformed
        pnlConfirma.setVisible(true);
    }//GEN-LAST:event_btnTerminaConsultaActionPerformed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        OP1.setVisible(false);
        OP2.setVisible(false);
        OP3.setVisible(false);
        OP4.setVisible(true);
        FA.setVisible(false);
        DBEA1.setVisible(false);
        DBEA2.setVisible(false);
        otros.setVisible(true);
        LblTitulo.setText("Pienso y digo, que es esto??");
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void lblAF1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAF1MouseClicked
        antecedentesPersonal();
    }//GEN-LAST:event_lblAF1MouseClicked

    private void btnSi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSi1ActionPerformed
        if(btnSi1.getText().equals("Si")){ // Al guardar
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

    private void lblTS1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTS1MouseClicked
       Psico();
    }//GEN-LAST:event_lblTS1MouseClicked

    private void lblPT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPT1MouseClicked
        PatologiasMaternas();
    }//GEN-LAST:event_lblPT1MouseClicked

    private void lblAT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAT1MouseClicked
        Referencias();
    }//GEN-LAST:event_lblAT1MouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DBEA1;
    private javax.swing.JPanel DBEA2;
    private javax.swing.JPanel FA;
    private javax.swing.JLabel LblTitulo;
    public static javax.swing.JPanel OP1;
    public static javax.swing.JPanel OP2;
    public static javax.swing.JPanel OP3;
    public static javax.swing.JPanel OP4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAF;
    private javax.swing.JLabel lblAF1;
    private javax.swing.JLabel lblAO;
    private javax.swing.JLabel lblAT;
    private javax.swing.JLabel lblAT1;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblConfirma;
    private javax.swing.JLabel lblEF;
    private javax.swing.JLabel lblEL;
    private javax.swing.JLabel lblEM;
    private javax.swing.JLabel lblFD;
    public static javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFUM;
    public static javax.swing.JLabel lblFua;
    private javax.swing.JLabel lblGA;
    private javax.swing.JLabel lblHO;
    public static javax.swing.JTextField lblId;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblMadre;
    private javax.swing.JLabel lblPT;
    private javax.swing.JLabel lblPT1;
    private javax.swing.JLabel lblTS;
    private javax.swing.JLabel lblTS1;
    private javax.swing.JLabel lblVG;
    private javax.swing.JLabel lblVP;
    private javax.swing.JPanel otros;
    private javax.swing.JPanel pnlConfirma;
    // End of variables declaration//GEN-END:variables
}
