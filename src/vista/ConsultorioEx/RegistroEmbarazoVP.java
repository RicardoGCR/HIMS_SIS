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
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalVp;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static vista.ConsultorioEx.RegistroEmbarazoAO.chk1;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoVP extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    

    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoVP() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        mensaje.setVisible(false);
        lblMant.setVisible(false);
        var.setVisible(false);
        
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
 


  public void Guardar( ){
        
    ConsultorioExtCarnetPerinatalVp CXRsVP= new ConsultorioExtCarnetPerinatalVp();
    ConsultorioExtCarnetPerinatalVp CXRsVP2 = new ConsultorioExtCarnetPerinatalVp();
    AdmisionEmergenciaCabecera admi = new AdmisionEmergenciaCabecera();
           if(lblMant.getText().equals("U"))
            CXRsVP.setVpId(Integer.parseInt(lblIdVP.getText()));
            CXRsVP.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            ////////////////RUBEOLA
            if(txtRS.getText().equals("X"))
                CXRsVP.setVpRubeola("S");
            else
            if(txtRN.getText().equals("X"))
                CXRsVP.setVpRubeola("N");
           
            /////////// HEPATITIS
            if(txtHS.getText().equals("X"))
                CXRsVP.setVpHepatitis("S");
            else
            if(txtHN.getText().equals("X"))
                CXRsVP.setVpHepatitis("N");
            
            /////////// PAPILOMA
            if(txtPS.getText().equals("X"))
                CXRsVP.setVpPapiloma("S");
            else
            if(txtPN.getText().equals("X"))
                CXRsVP.setVpPapiloma("N");
            /////////// FIEBRE
            if(txtFS.getText().equals("X"))
                CXRsVP.setVpFiebre("S");
            else
            if(txtFN.getText().equals("X"))
                CXRsVP.setVpFiebre("N");

            CXRsVP.setCodUsu(admi.codUsuario(lblusu.getText()));//falta 
            CXRsVP.setIdActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
            
                if(CXRsVP.mantenimientoConsultorioExtCarnetPerinatalVp(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Guardados de forma correcta");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=1;
                    CXRsVP2.ConsultoriosExtVPListar(RegistroEmbarazoPrincipal.lblId.getText());     
                    }
                    if (lblMant.getText().equals("U")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Actualizados de forma correcta");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=9;
 
                    CXRsVP2.ConsultoriosExtVPListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
  
 

//  public void Guardar(JTextField Inter ,JTextField Terminacion ,JTextField TipoAborto ,JTextField Lactancia,JTextField LugarParto,JTextField Captada,JTextField Referida ){
//        
//    ConsultorioExtCarnetPerinatalGa CXRsGA= new ConsultorioExtCarnetPerinatalGa();
//    ConsultorioExtCarnetPerinatalGa CXRsGA2 = new ConsultorioExtCarnetPerinatalGa();
//    
//          
//            CXRsGA.setGaId(0);
//            CXRsGA.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
//            CXRsGA.setGaIntergenesico(Inter.getText());
//            CXRsGA.setGaTerminacion(Terminacion.getText());
//            CXRsGA.setGaTipoAborto(TipoAborto.getText());
//            CXRsGA.setGaLactanciaMat(Lactancia.getText());
//            CXRsGA.setGaLugarParto(LugarParto.getText());
//            CXRsGA.setGaFechaGestacion(determinarFecha(fechaGA));
//            CXRsGA.setGA_CAPTADA(Captada.getText());
//            CXRsGA.setGA_REFERIDA(Referida.getText());
//            CXRsGA.setCodUsu(lblusu.getText());//falta 
//
//            
//                if(CXRsGA.mantenimientoConsultorioExtGA("I")==true){
//                    mensaje.setVisible(true);
//                    mensaje.setBackground(new Color(33,115,70)); 
//                    men.setText("Datos Guardados de forma correcta");
//                    b.setText("OK");
//                    b.setVisible(true);
//                    b1.setVisible(false);
//
//                    btnGuardar.setEnabled(false);
//                    btneditar.setEnabled(true);
//             
//                    tge=1;
//                    CXRsGA2.ConsultoriosExtGAListar(RegistroEmbarazoPrincipal.lblId.getText());  
//
////                    habilitarDatos(false);
//                }else {
//
//                        mensaje.setVisible(true);
//                        mensaje.setBackground(new Color(255,91,70)); 
//                        men.setText("Ocurrio un error, Verifique");
//                        b.setVisible(false);
//                        b1.setVisible(false);
//                        tge=7;
//                }  
//             
//  
//    }
//  
//  public void Modificar(JTextField Inter ,JTextField Terminacion ,JTextField TipoAborto ,JTextField Lactancia,JTextField LugarParto,JTextField Captada,JTextField Referida ){
//        
//    ConsultorioExtCarnetPerinatalGa CXRsGA= new ConsultorioExtCarnetPerinatalGa();
//    ConsultorioExtCarnetPerinatalGa CXRsGA2 = new ConsultorioExtCarnetPerinatalGa();
//    try {
//          
//            CXRsGA.setGaId(0);
//            CXRsGA.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
//            CXRsGA.setGaIntergenesico(Inter.getText());
//            CXRsGA.setGaTerminacion(Terminacion.getText());
//            CXRsGA.setGaTipoAborto(TipoAborto.getText());
//            CXRsGA.setGaLactanciaMat(Lactancia.getText());
//            CXRsGA.setGaLugarParto(LugarParto.getText());
//            CXRsGA.setGaFechaGestacion(determinarFecha(fechaGA));
//            CXRsGA.setGA_CAPTADA(Captada.getText());
//            CXRsGA.setGA_REFERIDA(Referida.getText());
//            CXRsGA.setCodUsu(lblusu.getText());//falta 
//
//            
//                if(CXRsGA.mantenimientoConsultorioExtGA("I")==true){
//                    mensaje.setVisible(true);
//                    mensaje.setBackground(new Color(33,115,70)); 
//                    men.setText("Datos Guardados de forma correcta");
//                    b.setText("OK");
//                    b.setVisible(true);
//                    b1.setVisible(false);
//
//                    btnGuardar.setEnabled(false);
//                    btneditar.setEnabled(true);
//             
//                    tge=1;
//                    CXRsGA2.ConsultoriosExtGAListar(RegistroEmbarazoPrincipal.lblId.getText());  
//
////                    habilitarDatos(false);
//                }else {
//
//                        mensaje.setVisible(true);
//                        mensaje.setBackground(new Color(255,91,70)); 
//                        men.setText("Ocurrio un error, Verifique");
//                        b.setVisible(false);
//                        b1.setVisible(false);
//                        tge=7;
//                }  
//             } catch (Exception e) {
//                System.out.println("Error: guardar " + e.getMessage());
//            }
//  
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        var = new javax.swing.JLabel();
        panelVacunas = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtHS = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtHN = new javax.swing.JTextField();
        txtRN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRS = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtPS = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtPN = new javax.swing.JTextField();
        txtFS = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtFN = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel37 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblIdVP = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblMadreVP = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        var.setText("1");

        panelVacunas.setBackground(new java.awt.Color(255, 255, 255));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("<html>Papiloma<br>  Virus</br></html>");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Hepatitis B");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("<html>Fiebre<br>Amarilla</br></html>");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Rubeola");

        txtHS.setEditable(false);
        txtHS.setBackground(new java.awt.Color(255, 255, 255));
        txtHS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHS.setForeground(new java.awt.Color(102, 102, 102));
        txtHS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtHS.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHS.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHS.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHSCaretUpdate(evt);
            }
        });
        txtHS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHSMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("SI");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("NO");

        txtHN.setEditable(false);
        txtHN.setBackground(new java.awt.Color(255, 255, 255));
        txtHN.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtHN.setForeground(new java.awt.Color(102, 102, 102));
        txtHN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtHN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtHN.setPreferredSize(new java.awt.Dimension(18, 18));
        txtHN.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtHN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtHNCaretUpdate(evt);
            }
        });
        txtHN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHNMouseClicked(evt);
            }
        });

        txtRN.setEditable(false);
        txtRN.setBackground(new java.awt.Color(255, 255, 255));
        txtRN.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtRN.setForeground(new java.awt.Color(102, 102, 102));
        txtRN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtRN.setPreferredSize(new java.awt.Dimension(18, 18));
        txtRN.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtRN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRNCaretUpdate(evt);
            }
        });
        txtRN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRNMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("NO");

        txtRS.setEditable(false);
        txtRS.setBackground(new java.awt.Color(255, 255, 255));
        txtRS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtRS.setForeground(new java.awt.Color(102, 102, 102));
        txtRS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtRS.setPreferredSize(new java.awt.Dimension(18, 18));
        txtRS.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtRS.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRSCaretUpdate(evt);
            }
        });
        txtRS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRSMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("SI");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("SI");

        txtPS.setEditable(false);
        txtPS.setBackground(new java.awt.Color(255, 255, 255));
        txtPS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPS.setForeground(new java.awt.Color(102, 102, 102));
        txtPS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtPS.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPS.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPS.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPSCaretUpdate(evt);
            }
        });
        txtPS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPSMouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("NO");

        txtPN.setEditable(false);
        txtPN.setBackground(new java.awt.Color(255, 255, 255));
        txtPN.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPN.setForeground(new java.awt.Color(102, 102, 102));
        txtPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtPN.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPN.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPNCaretUpdate(evt);
            }
        });
        txtPN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPNMouseClicked(evt);
            }
        });

        txtFS.setEditable(false);
        txtFS.setBackground(new java.awt.Color(255, 255, 255));
        txtFS.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFS.setForeground(new java.awt.Color(102, 102, 102));
        txtFS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtFS.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFS.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFS.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFSCaretUpdate(evt);
            }
        });
        txtFS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFSMouseClicked(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("SI");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("NO");

        txtFN.setEditable(false);
        txtFN.setBackground(new java.awt.Color(255, 255, 255));
        txtFN.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtFN.setForeground(new java.awt.Color(102, 102, 102));
        txtFN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtFN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtFN.setPreferredSize(new java.awt.Dimension(18, 18));
        txtFN.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtFN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFNCaretUpdate(evt);
            }
        });
        txtFN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelVacunasLayout = new javax.swing.GroupLayout(panelVacunas);
        panelVacunas.setLayout(panelVacunasLayout);
        panelVacunasLayout.setHorizontalGroup(
            panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVacunasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVacunasLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVacunasLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVacunasLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVacunasLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(675, 675, 675))
        );
        panelVacunasLayout.setVerticalGroup(
            panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVacunasLayout.createSequentialGroup()
                .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVacunasLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVacunasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)))))
                .addGap(35, 35, 35)
                .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtHN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(36, 36, 36)
                .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(txtPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(panelVacunasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(txtFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(panelVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("<html>Vacunas <br>Previas</html>");

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

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
        );

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

        lblMant.setForeground(new java.awt.Color(255, 255, 255));
        lblMant.setText("I");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnCaccnelar)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdVP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMant))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(lblMant)
                .addGap(31, 31, 31)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar)
                .addGap(47, 47, 47)
                .addComponent(lblIdVP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblusu)
                .addGap(70, 70, 70))
        );

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

        lblMadreVP.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadreVP.setForeground(new java.awt.Color(12, 97, 81));
        lblMadreVP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadreVP.setText("Martha Arias Torres");
        lblMadreVP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadreVP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jPanel7.setBackground(new java.awt.Color(65, 65, 65));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(255, 255, 255));

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMadreVP, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(53, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(374, 374, 374)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1000, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMadreVP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(273, 273, 273)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(300, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

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

        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         btnGuardar.setEnabled(true);
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
//        if (tge==9){
//   mensaje.setVisible(false);  
//   btnGuardar.setEnabled(false);
//                    btneditar.setEnabled(true);
//   
//
//   }
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void txtPSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPSCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPSCaretUpdate

    private void txtPNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPNCaretUpdate

    private void txtHSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHSCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHSCaretUpdate

    private void txtHNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtHNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHNCaretUpdate

    private void txtFSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFSCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFSCaretUpdate

    private void txtFNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFNCaretUpdate

    private void txtRSCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRSCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRSCaretUpdate

    private void txtRNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRNCaretUpdate

    private void txtRSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRSMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
       if(txtRS.getText().equals("") && evt.getClickCount()==1){
           txtRS.setText("X");
           txtRN.setText(""); 
  
        }else
        if(txtRS.getText().equals("X") && evt.getClickCount()==1){
           txtRS.setText(""); 
           txtRN.setText(""); 

        }
          }
    }//GEN-LAST:event_txtRSMouseClicked

    private void txtRNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRNMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtRN.getText().equals("") && evt.getClickCount()==1){
           txtRN.setText("X");
           txtRS.setText(""); 
  
        }else
        if(txtRN.getText().equals("X") && evt.getClickCount()==1){
           txtRN.setText(""); 
           txtRS.setText(""); 

        }
          }
    }//GEN-LAST:event_txtRNMouseClicked

    private void txtHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHSMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHS.getText().equals("") && evt.getClickCount()==1){
           txtHS.setText("X");
           txtHN.setText(""); 
  
        }else
        if(txtHS.getText().equals("X") && evt.getClickCount()==1){
           txtHS.setText(""); 
           txtHN.setText(""); 

        }
          }
    }//GEN-LAST:event_txtHSMouseClicked

    private void txtHNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHNMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtHN.getText().equals("") && evt.getClickCount()==1){
           txtHN.setText("X");
           txtHS.setText(""); 
  
        }else
        if(txtHN.getText().equals("X") && evt.getClickCount()==1){
           txtHN.setText(""); 
           txtHS.setText(""); 

        }
          }
    }//GEN-LAST:event_txtHNMouseClicked

    private void txtPSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPSMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPS.getText().equals("") && evt.getClickCount()==1){
           txtPS.setText("X");
           txtPN.setText(""); 
  
        }else
        if(txtPS.getText().equals("X") && evt.getClickCount()==1){
           txtPS.setText(""); 
           txtPN.setText(""); 

        }
          }
    }//GEN-LAST:event_txtPSMouseClicked

    private void txtFSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFSMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFS.getText().equals("") && evt.getClickCount()==1){
           txtFS.setText("X");
           txtFN.setText(""); 
  
        }else
        if(txtFS.getText().equals("X") && evt.getClickCount()==1){
           txtFS.setText(""); 
           txtFN.setText(""); 

        }
          }
    }//GEN-LAST:event_txtFSMouseClicked

    private void txtPNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPNMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtPN.getText().equals("") && evt.getClickCount()==1){
           txtPN.setText("X");
           txtPS.setText(""); 
  
        }else
        if(txtPN.getText().equals("X") && evt.getClickCount()==1){
           txtPN.setText(""); 
           txtPS.setText(""); 

        }
          }
    }//GEN-LAST:event_txtPNMouseClicked

    private void txtFNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFNMouseClicked
        if (lblIdVP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtFN.getText().equals("") && evt.getClickCount()==1){
           txtFN.setText("X");
           txtFS.setText(""); 
  
        }else
        if(txtFN.getText().equals("X") && evt.getClickCount()==1){
           txtFN.setText(""); 
           txtFS.setText(""); 

        }
          }
    }//GEN-LAST:event_txtFNMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel7;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblIdVP;
    public static javax.swing.JLabel lblMadreVP;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel panelVacunas;
    public static javax.swing.JTextField txtFN;
    public static javax.swing.JTextField txtFS;
    public static javax.swing.JTextField txtHN;
    public static javax.swing.JTextField txtHS;
    public static javax.swing.JTextField txtPN;
    public static javax.swing.JTextField txtPS;
    public static javax.swing.JTextField txtRN;
    public static javax.swing.JTextField txtRS;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
