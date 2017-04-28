/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import campos.LimitadorDeDocumento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAn;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalFd;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPt;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalTs;
import modelos.ConsultorioEx.ConsultorioExtEsnitss;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    public static String opcionGuardar = "";
    public RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        pnlMensaje.setVisible(false);
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
        //ANTITETANICA
        LimitadorDeDocumento limitDosisPrevia = new LimitadorDeDocumento(5);
        txtNDosisPrevia.setDocument(limitDosisPrevia);
        LimitadorDeDocumento limitDosis1 = new LimitadorDeDocumento(1);
        txtDosis1.setDocument(limitDosis1);
        LimitadorDeDocumento limitDosis2 = new LimitadorDeDocumento(1);
        txtDosis2.setDocument(limitDosis2);
    }
    
    public boolean mantenimientoPT(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalPt consultorio1 = new ConsultorioExtCarnetPerinatalPt();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
                consultorio1.setPtId(Integer.parseInt(lblIdPeso.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setPtPeso(txtPeso.getText());
            consultorio1.setPtTalla(txtTalla.getText());
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalPt(lblMant.getText())==true){
                if(lblMant.getText().equals("I")){
                    lblIdPeso.setText(consultorio1.perinatalPtID());
                    lblMant.setText("U");
                }
                txtPeso.setEditable(false);
                txtTalla.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: mantenimientoPT" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoAN(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalAn consultorio1 = new ConsultorioExtCarnetPerinatalAn();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantAn.getText().equals("U") || lblMantAn.getText().equals("E"))
                consultorio1.setAnId(Integer.parseInt(lblIdAn.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            consultorio1.setAnDosisPrevia(txtNDosisPrevia.getText());
            consultorio1.setAn1raDosis(txtDosis1.getText());
            consultorio1.setAn2raDosis(txtDosis2.getText());
            if(txtSinDosis1.getText().equals("X"))
                consultorio1.setAn1raDAplicacion("Sin dosis");
            else
<<<<<<< HEAD
         
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalAn(lblMant.getText())==true){
                if(lblMant.getText().equals("I")){
//                    lblIdPeso.setText(consultorio1.perinatalPtID());
                    lblMant.setText("U");
=======
                consultorio1.setAn1raDAplicacion("No aplica");
            if(txtSinDosis2.getText().equals("X"))
                consultorio1.setAn2raDAplicacion("Sin dosis");
            else
                consultorio1.setAn2raDAplicacion("No aplica");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalAn(lblMantAn.getText())==true){
                if(lblMantAn.getText().equals("I")){
                    lblIdAn.setText(consultorio1.perinatalAnID());
                    lblMantAn.setText("U");
>>>>>>> ecb6f28039c35ef6accf4728b36a23b34167e9c9
                }
                txtNDosisPrevia.setEditable(false);
                txtDosis1.setEditable(false);
                txtDosis2.setEditable(false);
                txtSinDosis1.setEditable(false);
                txtSinDosis2.setEditable(false);
                txtNoAplica1.setEditable(false);
                txtNoAplica1.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: mantenimientoAN" + e.getMessage());
        }
        return retorna;
    }
 
    public boolean mantenimientoTS(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalTs consultorio1 = new ConsultorioExtCarnetPerinatalTs();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMantGs.getText().equals("U") || lblMantGs.getText().equals("E"))
                consultorio1.setTsId(Integer.parseInt(lblIdGs.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            if(txtA.getText().equals("X"))
                consultorio1.setTsGrupo("A");
            if(txtB.getText().equals("X"))
                consultorio1.setTsGrupo("B");
            if(txtAB.getText().equals("X"))
                consultorio1.setTsGrupo("AB");
            if(txtO.getText().equals("X"))
                consultorio1.setTsGrupo("O");
            if(txtRhPositivo.getText().equals("X"))
                consultorio1.setTsRh("RH+");
            if(txtRhSen.getText().equals("X"))
                consultorio1.setTsRh("RH+ SEN DESC");
            if(txtNoDesc.getText().equals("X"))
                consultorio1.setTsRh("RH- NO DESC");
            if(txtRhNegativo.getText().equals("X"))
                consultorio1.setTsRh("RH- SEN");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalTs(lblMantGs.getText())==true){
                if(lblMantGs.getText().equals("I")){
                    lblIdGs.setText(consultorio1.perinatalTsID());
//                    lblMantGs.setText("U");
                }
                lblMantGs.setText("");
                txtRhNegativo.setEditable(false);
                txtNoDesc.setEditable(false);
                txtRhSen.setEditable(false);
                txtRhPositivo.setEditable(false);
                txtO.setEditable(false);
                txtAB.setEditable(false);
                txtB.setEditable(false);
                txtA.setEditable(false);
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: mantenimientoTS" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean mantenimientoFD(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalFd consultorio1 = new ConsultorioExtCarnetPerinatalFd();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            consultorio1.setFdNCigarros(txtNCigarros.getText());
            if(chkFdSi.getText().equals(""))
                consultorio1.setFdDroga("SI");
            if(chkFdNo.getText().equals(""))
                consultorio1.setFdDroga("NO");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalFd(lblMantFd.getText())==true){
                if(lblMantFd.getText().equals("I")){
                    lblIdFd.setText(consultorio1.perinatalFdID());
//                    lblMantGs.setText("U");
                }
                lblMantFd.setText("");
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setVisible(true);
                btnSi.setText("OK");
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ocurrió un error, verifique");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println("Error: mantenimientoFD" + e.getMessage());
        }
        return retorna;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        P1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtTalla = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblIdPeso = new javax.swing.JLabel();
        P3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtNDosisPrevia = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtDosis1 = new javax.swing.JTextField();
        txtDosis2 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtSinDosis1 = new javax.swing.JTextField();
        txtNoAplica1 = new javax.swing.JTextField();
        txtSinDosis2 = new javax.swing.JTextField();
        txtNoAplica2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblIdAn = new javax.swing.JLabel();
        lblMantAn = new javax.swing.JLabel();
        P5 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        txtPadre31 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtPadre30 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        fechaf3 = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        txtPaciente2 = new javax.swing.JTextField();
        btnBuscarNino2 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdes1 = new javax.swing.JEditorPane();
        P4 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        fechaf = new com.toedter.calendar.JDateChooser();
        txtPadre27 = new javax.swing.JTextField();
        txtPadre28 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        txtPadre5 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        txtPadre29 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        fechaf1 = new com.toedter.calendar.JDateChooser();
        jLabel61 = new javax.swing.JLabel();
        fechaf2 = new com.toedter.calendar.JDateChooser();
        jLabel84 = new javax.swing.JLabel();
        P6 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        txtPadre32 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtPadre33 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        txtPadre34 = new javax.swing.JTextField();
        txtPadre35 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        fechaf5 = new com.toedter.calendar.JDateChooser();
        P9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        txtNCigarros = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        lblIdFd = new javax.swing.JLabel();
        lblMantFd = new javax.swing.JLabel();
        P2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtA = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtB = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtAB = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtO = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtRhPositivo = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txtRhSen = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtNoDesc = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtRhNegativo = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        lblIdGs = new javax.swing.JLabel();
        lblMantGs = new javax.swing.JLabel();
        PEmergencia = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        fechaf4 = new com.toedter.calendar.JDateChooser();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        txtPaciente3 = new javax.swing.JTextField();
        btnBuscarNino3 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes = new javax.swing.JEditorPane();
        P8 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        chkFdSi = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        chkFdNo = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        LblTitulo = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        ChkAnalf1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        ChkEdad1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        lblCpId = new javax.swing.JLabel();
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        lblMadre = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        P1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Kg");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Peso Habitual");

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(102, 102, 102));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPeso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPeso.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPesoCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        txtTalla.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTalla.setForeground(new java.awt.Color(102, 102, 102));
        txtTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTalla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTalla.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTallaCaretUpdate(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("cm");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Talla");

        javax.swing.GroupLayout P1Layout = new javax.swing.GroupLayout(P1);
        P1.setLayout(P1Layout);
        P1Layout.setHorizontalGroup(
            P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P1Layout.createSequentialGroup()
                .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40))
                    .addGroup(P1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblIdPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        P1Layout.setVerticalGroup(
            P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P1Layout.createSequentialGroup()
                .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel40)))
                    .addGroup(P1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        P3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtNDosisPrevia.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNDosisPrevia.setForeground(new java.awt.Color(102, 102, 102));
        txtNDosisPrevia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNDosisPrevia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNDosisPrevia.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNDosisPreviaCaretUpdate(evt);
            }
        });
        txtNDosisPrevia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNDosisPreviaKeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("<html>Nº Dosis<br>Previa</br></span></html>");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("1ª");

        txtDosis1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDosis1.setForeground(new java.awt.Color(102, 102, 102));
        txtDosis1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDosis1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDosis1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDosis1CaretUpdate(evt);
            }
        });
        txtDosis1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDosis1KeyTyped(evt);
            }
        });

        txtDosis2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtDosis2.setForeground(new java.awt.Color(102, 102, 102));
        txtDosis2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDosis2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDosis2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDosis2CaretUpdate(evt);
            }
        });
        txtDosis2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDosis2KeyTyped(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("2ª");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Dosis");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("mes de gestación");

        txtSinDosis1.setEditable(false);
        txtSinDosis1.setBackground(new java.awt.Color(255, 204, 51));
        txtSinDosis1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSinDosis1.setForeground(new java.awt.Color(102, 102, 102));
        txtSinDosis1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSinDosis1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtSinDosis1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtSinDosis1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSinDosis1CaretUpdate(evt);
            }
        });
        txtSinDosis1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSinDosis1MouseClicked(evt);
            }
        });

        txtNoAplica1.setEditable(false);
        txtNoAplica1.setBackground(new java.awt.Color(255, 255, 255));
        txtNoAplica1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNoAplica1.setForeground(new java.awt.Color(102, 102, 102));
        txtNoAplica1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoAplica1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNoAplica1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNoAplica1.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNoAplica1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNoAplica1CaretUpdate(evt);
            }
        });
        txtNoAplica1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNoAplica1MouseClicked(evt);
            }
        });

        txtSinDosis2.setEditable(false);
        txtSinDosis2.setBackground(new java.awt.Color(255, 204, 51));
        txtSinDosis2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtSinDosis2.setForeground(new java.awt.Color(102, 102, 102));
        txtSinDosis2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSinDosis2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtSinDosis2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtSinDosis2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSinDosis2CaretUpdate(evt);
            }
        });
        txtSinDosis2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSinDosis2MouseClicked(evt);
            }
        });

        txtNoAplica2.setEditable(false);
        txtNoAplica2.setBackground(new java.awt.Color(255, 255, 255));
        txtNoAplica2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNoAplica2.setForeground(new java.awt.Color(102, 102, 102));
        txtNoAplica2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoAplica2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNoAplica2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNoAplica2.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNoAplica2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNoAplica2CaretUpdate(evt);
            }
        });
        txtNoAplica2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNoAplica2MouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("<html>Sin<br>dosis</br></span></html>");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("<html>No<br>aplica</br></span></html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNDosisPrevia, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIdAn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMantAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDosis2)
                            .addComponent(txtDosis1)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel47)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(txtSinDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(txtNoAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(txtSinDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(txtNoAplica2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel47))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSinDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)))
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoAplica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSinDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(txtNDosisPrevia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblIdAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMantAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout P3Layout = new javax.swing.GroupLayout(P3);
        P3.setLayout(P3Layout);
        P3Layout.setHorizontalGroup(
            P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        P3Layout.setVerticalGroup(
            P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("Si");

        txtPadre31.setEditable(false);
        txtPadre31.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre31.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre31.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre31.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre31.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre31CaretUpdate(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Hospitalización");

        txtPadre30.setEditable(false);
        txtPadre30.setBackground(new java.awt.Color(255, 204, 51));
        txtPadre30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre30.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre30.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre30.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre30CaretUpdate(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("No");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Fecha");

        fechaf3.setBackground(new java.awt.Color(255, 255, 255));
        fechaf3.setDateFormatString("dd-MM-yyyy");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Diagnóstico");

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPaciente2.setEditable(false);
        txtPaciente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPaciente2.setForeground(new java.awt.Color(102, 102, 102));
        txtPaciente2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPaciente2.setBorder(null);
        txtPaciente2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPaciente2CaretUpdate(evt);
            }
        });

        btnBuscarNino2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarNino2.setMnemonic('B');
        btnBuscarNino2.setToolTipText("");
        btnBuscarNino2.setBorderPainted(false);
        btnBuscarNino2.setContentAreaFilled(false);
        btnBuscarNino2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarNino2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNino2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPaciente2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("CIE 10");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(51, 51, 51));
        jLabel68.setText("________________");

        txtdes1.setForeground(new java.awt.Color(51, 51, 51));
        txtdes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdes1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdes1KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtdes1);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel62)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(txtPadre30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPadre31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fechaf3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel68)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPadre31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPadre30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel65)
                    .addComponent(fechaf3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout P5Layout = new javax.swing.GroupLayout(P5);
        P5.setLayout(P5Layout);
        P5Layout.setHorizontalGroup(
            P5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        P5Layout.setVerticalGroup(
            P5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("FUM");

        fechaf.setBackground(new java.awt.Color(255, 255, 255));
        fechaf.setDateFormatString("dd-MM-yyyy");

        txtPadre27.setEditable(false);
        txtPadre27.setBackground(new java.awt.Color(255, 204, 51));
        txtPadre27.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre27.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre27.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre27.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre27CaretUpdate(evt);
            }
        });

        txtPadre28.setEditable(false);
        txtPadre28.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre28.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre28.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre28.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre28.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre28.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre28CaretUpdate(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("Duda");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Si");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("No");

        txtPadre5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre5.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre5.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPadre5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre5CaretUpdate(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("EG. (Ecografía)");

        txtPadre29.setEditable(false);
        txtPadre29.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre29.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre29.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre29.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre29.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre29.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre29CaretUpdate(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("No Aplica");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("semana");

        fechaf1.setBackground(new java.awt.Color(255, 255, 255));
        fechaf1.setDateFormatString("dd-MM-yyyy");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(102, 102, 102));
        jLabel61.setText("Fecha Probable de Parto");

        fechaf2.setBackground(new java.awt.Color(255, 255, 255));
        fechaf2.setDateFormatString("dd-MM-yyyy");

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("Fecha");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPadre27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPadre28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtPadre29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txtPadre5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel17Layout.createSequentialGroup()
                                        .addComponent(jLabel60)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addComponent(jLabel84)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaf1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fechaf2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(txtPadre27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPadre28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel57))
                        .addGap(35, 35, 35)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPadre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)
                        .addComponent(jLabel60)
                        .addComponent(jLabel84)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPadre29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jLabel61))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fechaf2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout P4Layout = new javax.swing.GroupLayout(P4);
        P4.setLayout(P4Layout);
        P4Layout.setHorizontalGroup(
            P4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P4Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        P4Layout.setVerticalGroup(
            P4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        P6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("Ficha Tamizaje");

        txtPadre32.setEditable(false);
        txtPadre32.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre32.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre32.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre32.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre32.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre32.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre32CaretUpdate(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Si");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("No");

        txtPadre33.setEditable(false);
        txtPadre33.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre33.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre33.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre33.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre33.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre33.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre33CaretUpdate(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Violencia");

        txtPadre34.setEditable(false);
        txtPadre34.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre34.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre34.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre34.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre34.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre34.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre34CaretUpdate(evt);
            }
        });

        txtPadre35.setEditable(false);
        txtPadre35.setBackground(new java.awt.Color(255, 255, 255));
        txtPadre35.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPadre35.setForeground(new java.awt.Color(102, 102, 102));
        txtPadre35.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPadre35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPadre35.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPadre35.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPadre35.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPadre35CaretUpdate(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Si");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 51));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("No");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("Fecha");

        fechaf5.setBackground(new java.awt.Color(255, 255, 255));
        fechaf5.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel73))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPadre32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPadre34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPadre33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPadre35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fechaf5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPadre32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73)
                    .addComponent(jLabel74)
                    .addComponent(txtPadre33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75))
                .addGap(21, 21, 21)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPadre34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76)
                    .addComponent(txtPadre35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78)
                    .addComponent(jLabel77))
                .addGap(21, 21, 21)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaf5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout P6Layout = new javax.swing.GroupLayout(P6);
        P6.setLayout(P6Layout);
        P6Layout.setHorizontalGroup(
            P6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        P6Layout.setVerticalGroup(
            P6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        P9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Fuma");

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Nº de Cigarrillos");

        txtNCigarros.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNCigarros.setForeground(new java.awt.Color(102, 102, 102));
        txtNCigarros.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNCigarros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNCigarros.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNCigarrosCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNCigarros, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80)
                    .addComponent(txtNCigarros, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(39, 174, 97));
        jPanel7.setPreferredSize(new java.awt.Dimension(15, 34));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout P9Layout = new javax.swing.GroupLayout(P9);
        P9.setLayout(P9Layout);
        P9Layout.setHorizontalGroup(
            P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P9Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(217, 217, 217)
                .addComponent(lblIdFd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblMantFd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(P9Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        P9Layout.setVerticalGroup(
            P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P9Layout.createSequentialGroup()
                .addGroup(P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(P9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIdFd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMantFd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Grupo:");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtA.setEditable(false);
        txtA.setBackground(new java.awt.Color(255, 255, 255));
        txtA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtA.setForeground(new java.awt.Color(102, 102, 102));
        txtA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtA.setPreferredSize(new java.awt.Dimension(18, 18));
        txtA.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtACaretUpdate(evt);
            }
        });
        txtA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAMouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("A");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel34))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtB.setEditable(false);
        txtB.setBackground(new java.awt.Color(255, 255, 255));
        txtB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtB.setForeground(new java.awt.Color(102, 102, 102));
        txtB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtB.setPreferredSize(new java.awt.Dimension(18, 18));
        txtB.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBCaretUpdate(evt);
            }
        });
        txtB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBMouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("B");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel35))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        txtAB.setEditable(false);
        txtAB.setBackground(new java.awt.Color(255, 255, 255));
        txtAB.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAB.setForeground(new java.awt.Color(102, 102, 102));
        txtAB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAB.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAB.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAB.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtABCaretUpdate(evt);
            }
        });
        txtAB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtABMouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("AB");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAB, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtAB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel36))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtO.setEditable(false);
        txtO.setBackground(new java.awt.Color(255, 255, 255));
        txtO.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtO.setForeground(new java.awt.Color(102, 102, 102));
        txtO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtO.setPreferredSize(new java.awt.Dimension(18, 18));
        txtO.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtOCaretUpdate(evt);
            }
        });
        txtO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOMouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("O");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtO, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel38))
        );

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Rh:");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtRhPositivo.setEditable(false);
        txtRhPositivo.setBackground(new java.awt.Color(255, 255, 255));
        txtRhPositivo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtRhPositivo.setForeground(new java.awt.Color(102, 102, 102));
        txtRhPositivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRhPositivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRhPositivo.setPreferredSize(new java.awt.Dimension(18, 18));
        txtRhPositivo.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtRhPositivo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRhPositivoCaretUpdate(evt);
            }
        });
        txtRhPositivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRhPositivoMouseClicked(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Rh (+)");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRhPositivo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRhPositivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel50))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        txtRhSen.setEditable(false);
        txtRhSen.setBackground(new java.awt.Color(255, 204, 51));
        txtRhSen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtRhSen.setForeground(new java.awt.Color(102, 102, 102));
        txtRhSen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRhSen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRhSen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtRhSen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtRhSen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRhSenCaretUpdate(evt);
            }
        });
        txtRhSen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRhSenMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Rh (+) Sen Desc");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRhSen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRhSen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel51))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        txtNoDesc.setEditable(false);
        txtNoDesc.setBackground(new java.awt.Color(255, 204, 51));
        txtNoDesc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtNoDesc.setForeground(new java.awt.Color(102, 102, 102));
        txtNoDesc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNoDesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNoDesc.setPreferredSize(new java.awt.Dimension(18, 18));
        txtNoDesc.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNoDesc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNoDescCaretUpdate(evt);
            }
        });
        txtNoDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNoDescMouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Rh (-) No Desc");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtNoDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel52))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        txtRhNegativo.setEditable(false);
        txtRhNegativo.setBackground(new java.awt.Color(255, 51, 51));
        txtRhNegativo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtRhNegativo.setForeground(new java.awt.Color(255, 255, 255));
        txtRhNegativo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRhNegativo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRhNegativo.setPreferredSize(new java.awt.Dimension(18, 18));
        txtRhNegativo.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtRhNegativo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRhNegativoCaretUpdate(evt);
            }
        });
        txtRhNegativo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRhNegativoMouseClicked(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("Rh (-) Sen");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRhNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtRhNegativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel53))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel39))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(lblIdGs, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMantGs, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdGs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMantGs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout P2Layout = new javax.swing.GroupLayout(P2);
        P2.setLayout(P2Layout);
        P2Layout.setHorizontalGroup(
            P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
        );
        P2Layout.setVerticalGroup(
            P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PEmergencia.setBackground(new java.awt.Color(255, 255, 255));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        fechaf4.setBackground(new java.awt.Color(255, 255, 255));
        fechaf4.setDateFormatString("dd-MM-yyyy");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(51, 51, 51));
        jLabel69.setText("Fecha");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Diagnóstico");

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtPaciente3.setEditable(false);
        txtPaciente3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPaciente3.setForeground(new java.awt.Color(102, 102, 102));
        txtPaciente3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPaciente3.setBorder(null);
        txtPaciente3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPaciente3CaretUpdate(evt);
            }
        });

        btnBuscarNino3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        btnBuscarNino3.setMnemonic('B');
        btnBuscarNino3.setToolTipText("");
        btnBuscarNino3.setBorderPainted(false);
        btnBuscarNino3.setContentAreaFilled(false);
        btnBuscarNino3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarNino3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarNino3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtPaciente3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarNino3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarNino3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtPaciente3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("CIE 10");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("________________");

        txtdes.setForeground(new java.awt.Color(51, 51, 51));
        txtdes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdesKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtdes);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel72))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel69))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fechaf4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaf4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel70)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jLabel72))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PEmergenciaLayout = new javax.swing.GroupLayout(PEmergencia);
        PEmergencia.setLayout(PEmergenciaLayout);
        PEmergenciaLayout.setHorizontalGroup(
            PEmergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PEmergenciaLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PEmergenciaLayout.setVerticalGroup(
            PEmergenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PEmergenciaLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        P8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        chkFdSi.setEditable(false);
        chkFdSi.setBackground(new java.awt.Color(255, 204, 51));
        chkFdSi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkFdSi.setForeground(new java.awt.Color(102, 102, 102));
        chkFdSi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkFdSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkFdSi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkFdSi.setSelectionColor(new java.awt.Color(255, 204, 51));
        chkFdSi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkFdSiCaretUpdate(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setText("Si");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkFdSi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(chkFdSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel81))
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        chkFdNo.setEditable(false);
        chkFdNo.setBackground(new java.awt.Color(255, 255, 255));
        chkFdNo.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkFdNo.setForeground(new java.awt.Color(102, 102, 102));
        chkFdNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkFdNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkFdNo.setPreferredSize(new java.awt.Dimension(18, 18));
        chkFdNo.setSelectionColor(new java.awt.Color(255, 204, 51));
        chkFdNo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkFdNoCaretUpdate(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(51, 51, 51));
        jLabel82.setText("No");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkFdNo, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(chkFdNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel82))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel29Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Droga");

        jPanel6.setBackground(new java.awt.Color(39, 174, 97));
        jPanel6.setPreferredSize(new java.awt.Dimension(15, 34));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout P8Layout = new javax.swing.GroupLayout(P8);
        P8.setLayout(P8Layout);
        P8Layout.setHorizontalGroup(
            P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P8Layout.createSequentialGroup()
                .addGroup(P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(P8Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(P8Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P8Layout.setVerticalGroup(
            P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(P8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel28.setBackground(new java.awt.Color(51, 51, 51));

        LblTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        LblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        LblTitulo.setText("<html>Antecedentes <br>Obstétricos</html>");

        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblusu.setText("Silvana");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnCaccnelar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCaccnelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCaccnelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Casa-32.png"))); // NOI18N
        btnCaccnelar.setText("Detalles");
        btnCaccnelar.setToolTipText("");
        btnCaccnelar.setContentAreaFilled(false);
        btnCaccnelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCaccnelar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCaccnelar.setIconTextGap(30);
        btnCaccnelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaccnelarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setIconTextGap(30);
        btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 204, 204));
        jLabel26.setText("Leyenda");

        ChkAnalf1.setEditable(false);
        ChkAnalf1.setBackground(new java.awt.Color(255, 204, 51));
        ChkAnalf1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ChkAnalf1.setForeground(new java.awt.Color(102, 102, 102));
        ChkAnalf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ChkAnalf1.setBorder(null);
        ChkAnalf1.setPreferredSize(new java.awt.Dimension(28, 28));
        ChkAnalf1.setSelectionColor(new java.awt.Color(255, 204, 51));
        ChkAnalf1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ChkAnalf1CaretUpdate(evt);
            }
        });
        ChkAnalf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChkAnalf1MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("ALERTA");

        ChkEdad1.setEditable(false);
        ChkEdad1.setBackground(new java.awt.Color(255, 51, 51));
        ChkEdad1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ChkEdad1.setForeground(new java.awt.Color(255, 255, 255));
        ChkEdad1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ChkEdad1.setBorder(null);
        ChkEdad1.setPreferredSize(new java.awt.Dimension(28, 28));
        ChkEdad1.setSelectionColor(new java.awt.Color(255, 51, 51));
        ChkEdad1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                ChkEdad1CaretUpdate(evt);
            }
        });
        ChkEdad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChkEdad1MouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Requiere Seguimiento Continuo");

        jPanel42.setBackground(new java.awt.Color(39, 174, 97));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel83)
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(240, 240, 240));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('N');
        btnModificar.setText("Modificar");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(30);
        btnModificar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblCpId.setText("jLabel1");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(btnCaccnelar)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37))
                            .addComponent(lblCpId))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCpId)
                .addGap(65, 65, 65)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(249, 249, 249))
        );

        pnlMensaje.setBackground(new java.awt.Color(33, 115, 70));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 255, 255));
        lblMensaje.setText("Desea Actualizar el Registro ?");

        btnSi.setForeground(new java.awt.Color(240, 240, 240));
        btnSi.setText("Si");
        btnSi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnSi.setContentAreaFilled(false);
        btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSi.setIconTextGap(30);
        btnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiActionPerformed(evt);
            }
        });

        btnNo.setForeground(new java.awt.Color(240, 240, 240));
        btnNo.setText("No");
        btnNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnNo.setContentAreaFilled(false);
        btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNo.setIconTextGap(30);
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMensajeLayout = new javax.swing.GroupLayout(pnlMensaje);
        pnlMensaje.setLayout(pnlMensajeLayout);
        pnlMensajeLayout.setHorizontalGroup(
            pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensajeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblMensaje)
                .addGap(46, 46, 46)
                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMensajeLayout.setVerticalGroup(
            pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensajeLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensaje)
                    .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblMadre.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(12, 97, 81));
        lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(P6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(P4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(P8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(P9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMadre)
                            .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(P5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(P3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(P4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(P8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(P9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(P6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPesoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCaretUpdate

    private void txtTallaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTallaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTallaCaretUpdate

    private void txtNDosisPreviaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNDosisPreviaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNDosisPreviaCaretUpdate

    private void txtDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis1CaretUpdate

    private void txtDosis2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis2CaretUpdate

    private void txtSinDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSinDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSinDosis1CaretUpdate

    private void txtNoAplica1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoAplica1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAplica1CaretUpdate

    private void txtSinDosis2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSinDosis2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSinDosis2CaretUpdate

    private void txtNoAplica2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoAplica2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoAplica2CaretUpdate

    private void txtACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtACaretUpdate

    private void txtBCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBCaretUpdate

    private void txtABCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtABCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtABCaretUpdate

    private void txtOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOCaretUpdate

    private void txtRhPositivoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhPositivoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhPositivoCaretUpdate

    private void txtRhSenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhSenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhSenCaretUpdate

    private void txtNoDescCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNoDescCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoDescCaretUpdate

    private void txtRhNegativoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRhNegativoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRhNegativoCaretUpdate

    private void txtPadre27CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre27CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre27CaretUpdate

    private void txtPadre28CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre28CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre28CaretUpdate

    private void txtPadre5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre5CaretUpdate

    private void txtPadre29CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre29CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre29CaretUpdate

    private void txtPadre30CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre30CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre30CaretUpdate

    private void txtPadre31CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre31CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre31CaretUpdate

    private void txtPaciente2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaciente2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaciente2CaretUpdate

    private void btnBuscarNino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino2ActionPerformed

    private void txtPaciente3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPaciente3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaciente3CaretUpdate

    private void btnBuscarNino3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino3ActionPerformed

    private void txtNCigarrosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNCigarrosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNCigarrosCaretUpdate

    private void chkFdSiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkFdSiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFdSiCaretUpdate

    private void chkFdNoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkFdNoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkFdNoCaretUpdate

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(opcionGuardar.equals("peso")){ //PARA EL FORMULARIO DE PESO Y TALLA
            if(txtPeso.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese el peso");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtTalla.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la talla");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            }
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMant.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMant.getText().equals("U"))
                lblMensaje.setText("¿Modificar los datos?");
        } // FIN DE FORMULARIO PESO Y TALLA
        
        if(opcionGuardar.equals("antitetanica")){ //PARA EL FORMULARIO DE ANTITETANICA
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
            if(lblMantAn.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
            } else
            if(lblMantAn.getText().equals("U"))
                lblMensaje.setText("¿Modificar los datos?");
        } // FIN DE FORMULARIO DE ANTITETANICA
        
        if(opcionGuardar.equals("tipoSangre")){ //PARA EL FORMULARIO DE TIPO DE SANGRE
            if(txtA.getText().equals("") && txtB.getText().equals("") &&
               txtAB.getText().equals("") && txtO.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione un grupo de sangre");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else 
            if(txtRhNegativo.getText().equals("") && txtRhPositivo.getText().equals("") &&
               txtRhSen.getText().equals("") && txtNoDesc.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el Rh");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else
            if(lblMantGs.getText().equals("I")){
                lblMensaje.setText("¿Guardar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            } else
            if(lblMantGs.getText().equals("U")){
                lblMensaje.setText("¿Modificar los datos?");
                pnlMensaje.setVisible(true);
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                pnlMensaje.setBackground(new Color(255,153,51));
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void ChkAnalf1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkAnalf1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1CaretUpdate

    private void ChkAnalf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkAnalf1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkAnalf1MouseClicked

    private void ChkEdad1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_ChkEdad1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1CaretUpdate

    private void ChkEdad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkEdad1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkEdad1MouseClicked

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel83MouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(opcionGuardar.equals("peso")){ // PARA EL FORMULARIO DE PESO
            txtPeso.setEditable(true);
            txtTalla.setEditable(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
        } else
        if(opcionGuardar.equals("antitetanica")){ // PARA EL FORMULARIO DE ANTITETANICA
            txtNDosisPrevia.setEditable(true);
            txtDosis1.setEditable(true);
            txtDosis2.setEditable(true);
            txtSinDosis1.setEnabled(true);
            txtSinDosis2.setEnabled(true);
            txtNoAplica1.setEnabled(true);
            txtNoAplica2.setEnabled(true);
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantAn.setText("U");
        } else
        if(opcionGuardar.equals("tipoSangre")){ // PARA EL FORMULARIO DE ANTITETANICA
            btnGuardar.setEnabled(true);
            btnModificar.setEnabled(false);
            lblMantGs.setText("U");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(opcionGuardar.equals("peso")){ // formulario de peso y talla
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoPT();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("antitetanica")){ // formulario de antitetanica
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoAN();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
        
        if(opcionGuardar.equals("tipoSangre")){ // formulario de Tipo Sangre
            if(btnSi.getText().equals("Si")){ // Al guardar
                mantenimientoTS();
            } else
            if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
                pnlMensaje.setVisible(false);
            }
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtPadre35CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre35CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre35CaretUpdate

    private void txtPadre34CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre34CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre34CaretUpdate

    private void txtPadre33CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre33CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre33CaretUpdate

    private void txtPadre32CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPadre32CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPadre32CaretUpdate

    private void txtdesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyReleased
        txtdes.setText(txtdes.getText().toUpperCase());

    }//GEN-LAST:event_txtdesKeyReleased

    private void txtdesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdesKeyTyped

    }//GEN-LAST:event_txtdesKeyTyped

    private void txtdes1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdes1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdes1KeyReleased

    private void txtdes1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdes1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdes1KeyTyped

    private void txtSinDosis1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSinDosis1MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis1.setText("X");
                txtNoAplica1.setText("");
            }
        }
    }//GEN-LAST:event_txtSinDosis1MouseClicked

    private void txtSinDosis2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSinDosis2MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis2.setText("X");
                txtNoAplica2.setText("");
            }
        }
    }//GEN-LAST:event_txtSinDosis2MouseClicked

    private void txtNoAplica1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoAplica1MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis1.setText("");
                txtNoAplica1.setText("X");
            }
        }
    }//GEN-LAST:event_txtNoAplica1MouseClicked

    private void txtNoAplica2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoAplica2MouseClicked
        if(lblIdAn.getText().equals("") || lblMantAn.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtSinDosis2.setText("");
                txtNoAplica2.setText("X");
            }
        }
    }//GEN-LAST:event_txtNoAplica2MouseClicked

    private void txtNDosisPreviaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNDosisPreviaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtNDosisPreviaKeyTyped

    private void txtDosis1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis1KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDosis1KeyTyped

    private void txtDosis2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis2KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDosis2KeyTyped

    private void txtAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("X");
                txtB.setText("");
                txtAB.setText("");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtAMouseClicked

    private void txtBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("X");
                txtAB.setText("");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtBMouseClicked

    private void txtABMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtABMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("");
                txtAB.setText("X");
                txtO.setText("");
            }
        }
    }//GEN-LAST:event_txtABMouseClicked

    private void txtOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtA.setText("");
                txtB.setText("");
                txtAB.setText("");
                txtO.setText("X");
            }
        }
    }//GEN-LAST:event_txtOMouseClicked

    private void txtRhPositivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhPositivoMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("X");
                txtRhSen.setText("");
                txtNoDesc.setText("");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtRhPositivoMouseClicked

    private void txtRhSenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhSenMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("X");
                txtNoDesc.setText("");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtRhSenMouseClicked

    private void txtNoDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNoDescMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("");
                txtNoDesc.setText("X");
                txtRhNegativo.setText("");
            }
        }
    }//GEN-LAST:event_txtNoDescMouseClicked

    private void txtRhNegativoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRhNegativoMouseClicked
        if(lblIdGs.getText().equals("") || lblMantGs.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtRhPositivo.setText("");
                txtRhSen.setText("");
                txtNoDesc.setText("");
                txtRhNegativo.setText("X");
            }
        }
    }//GEN-LAST:event_txtRhNegativoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    public static javax.swing.JLabel LblTitulo;
    public static javax.swing.JPanel P1;
    public static javax.swing.JPanel P2;
    public static javax.swing.JPanel P3;
    public static javax.swing.JPanel P4;
    public static javax.swing.JPanel P5;
    public static javax.swing.JPanel P6;
    public static javax.swing.JPanel P8;
    public static javax.swing.JPanel P9;
    public static javax.swing.JPanel PEmergencia;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnBuscarNino3;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    public static javax.swing.JTextField chkFdNo;
    public static javax.swing.JTextField chkFdSi;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechaf1;
    private com.toedter.calendar.JDateChooser fechaf2;
    private com.toedter.calendar.JDateChooser fechaf3;
    private com.toedter.calendar.JDateChooser fechaf4;
    private com.toedter.calendar.JDateChooser fechaf5;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
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
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblCpId;
    public static javax.swing.JLabel lblIdAn;
    public static javax.swing.JLabel lblIdFd;
    public static javax.swing.JLabel lblIdGs;
    public static javax.swing.JLabel lblIdPeso;
    public static javax.swing.JLabel lblMadre;
    public static javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblMantAn;
    public static javax.swing.JLabel lblMantFd;
    public static javax.swing.JLabel lblMantGs;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    public static javax.swing.JTextField txtA;
    public static javax.swing.JTextField txtAB;
    public static javax.swing.JTextField txtB;
    public static javax.swing.JTextField txtDosis1;
    public static javax.swing.JTextField txtDosis2;
    public static javax.swing.JTextField txtNCigarros;
    public static javax.swing.JTextField txtNDosisPrevia;
    public static javax.swing.JTextField txtNoAplica1;
    public static javax.swing.JTextField txtNoAplica2;
    public static javax.swing.JTextField txtNoDesc;
    public static javax.swing.JTextField txtO;
    public static javax.swing.JTextField txtPaciente2;
    public static javax.swing.JTextField txtPaciente3;
    public static javax.swing.JTextField txtPadre27;
    public static javax.swing.JTextField txtPadre28;
    public static javax.swing.JTextField txtPadre29;
    public static javax.swing.JTextField txtPadre30;
    public static javax.swing.JTextField txtPadre31;
    public static javax.swing.JTextField txtPadre32;
    public static javax.swing.JTextField txtPadre33;
    public static javax.swing.JTextField txtPadre34;
    public static javax.swing.JTextField txtPadre35;
    public static javax.swing.JTextField txtPadre5;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtRhNegativo;
    public static javax.swing.JTextField txtRhPositivo;
    public static javax.swing.JTextField txtRhSen;
    public static javax.swing.JTextField txtSinDosis1;
    public static javax.swing.JTextField txtSinDosis2;
    public static javax.swing.JTextField txtTalla;
    private javax.swing.JEditorPane txtdes;
    private javax.swing.JEditorPane txtdes1;
    // End of variables declaration//GEN-END:variables
}
