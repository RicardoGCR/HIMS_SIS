/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalExamenes;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalGa;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalHo;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalPmcie10;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalReferencias;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static vista.ConsultorioEx.RegistroEmbarazoGA.lblIdGA;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoPM2 extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    

    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoPM2() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        mensaje.setVisible(false);
 
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }

 

  public void Guardar( ){
        
    ConsultorioExtCarnetPerinatalExamenes CXRsR= new ConsultorioExtCarnetPerinatalExamenes();
    ConsultorioExtCarnetPerinatalExamenes CXRsR2 = new ConsultorioExtCarnetPerinatalExamenes();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    
           if(lblMant.getText().equals("U"))
            CXRsR.setIdEx(Integer.parseInt(lblIdPM2.getText()));
            CXRsR.setCp_id(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            
            CXRsR.setPsico(txtPsico.getText());
            CXRsR.setEt(txtEstimulacion.getText());

            ///////////////////////////////////////////////////////PLAN DE PARTO
            if(chkPPsi.getText().equals("X"))
                CXRsR.setPlanp("S");
            else
            if(chkPPno.getText().equals("X"))
                CXRsR.setPlanp("N");
            else
            if(chkPPna.getText().equals("X"))
                CXRsR.setPlanp("A");
            else
                CXRsR.setPlanp("");
            
            ///////////////////////////////////////////ALOJADA EN CASA DE ESPERA
            if(chkACEsi.getText().equals("X"))
                CXRsR.setAlojada("S");
            else
            if(chkACEno.getText().equals("X"))
                CXRsR.setAlojada("N");
            else
            if(chkACEna.getText().equals("X"))
                CXRsR.setAlojada("A");
            else
                CXRsR.setAlojada("");

            
            ////////////////////////////////////////////////////////////////HCMP
            if(chkHCMPap.getText().equals("X"))
                CXRsR.setHcmp("R");
            else
            if(chkHCMPa.getText().equals("X"))
                CXRsR.setHcmp("A");
            else
            if(chkHCMPp.getText().equals("X"))
                CXRsR.setHcmp("P");
            else
                CXRsR.setHcmp("");

            ////////////////////////////////////////// PRODUCTO DE LA CONCEPCION
            if(chkHU.getText().equals("X"))
                CXRsR.setProducto("U");
            else
            if(chkEM.getText().equals("X"))
                CXRsR.setProducto("M");
            else
                CXRsR.setHcmp("");
            
            CXRsR.setOrden(txtOrden.getText());
            
            if(chkA.getText().equals("X"))
                CXRsR.setAborto("X");
            else
                CXRsR.setHcmp("");
            

            CXRsR.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));

            
                if(CXRsR.mantenimientoConsultorioExtCarnetPerinatalExamenes(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Guardados de forma correcta");
                    b1.setText("OK");
                    b1.setVisible(true);
                    b.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=1;
                    CXRsR2.ConsultoriosExtEXAMListar(RegistroEmbarazoPrincipal.lblId.getText());     
                    }
                    if (lblMant.getText().equals("U")){
                     mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Actualizados de forma correcta");
                    b1.setText("OK");
                    b1.setVisible(true);
                    b.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=9;
 
                    CXRsR2.ConsultoriosExtEXAMListar(RegistroEmbarazoPrincipal.lblId.getText());     
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

        jPanel2 = new javax.swing.JPanel();
        var = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtPsico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtEstimulacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        chkPPsi = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        chkPPno = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        chkPPna = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        chkACEsi = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        chkACEno = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        chkACEna = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        chkHCMPp = new javax.swing.JTextField();
        chkHCMPap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        chkHCMPa = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        chkHU = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        chkEM = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtOrden = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        chkA = new javax.swing.JTextField();
        lblIdCp = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        ChkAnalf1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        ChkEdad1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblIdPM2 = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblMadre = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        var.setText("1");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtPsico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPsico.setForeground(new java.awt.Color(102, 102, 102));
        txtPsico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPsico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPsico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPsicoCaretUpdate(evt);
            }
        });
        txtPsico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPsicoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("PSICOPROFILIAXIS");

        txtEstimulacion.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEstimulacion.setForeground(new java.awt.Color(102, 102, 102));
        txtEstimulacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstimulacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEstimulacion.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEstimulacionCaretUpdate(evt);
            }
        });
        txtEstimulacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstimulacionKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ESTIMULACIÓN PRE NATAL");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("PLAN DE PARTO");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("<html>ALOJADA EN<BR>CASA DE ESPERA<HTML>");

        chkPPsi.setEditable(false);
        chkPPsi.setBackground(new java.awt.Color(255, 255, 255));
        chkPPsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkPPsi.setForeground(new java.awt.Color(102, 102, 102));
        chkPPsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkPPsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkPPsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkPPsi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkPPsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkPPsiCaretUpdate(evt);
            }
        });
        chkPPsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkPPsiMouseClicked(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("SI");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("No");

        chkPPno.setEditable(false);
        chkPPno.setBackground(new java.awt.Color(255, 255, 255));
        chkPPno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkPPno.setForeground(new java.awt.Color(102, 102, 102));
        chkPPno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkPPno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkPPno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkPPno.setPreferredSize(new java.awt.Dimension(18, 18));
        chkPPno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkPPnoCaretUpdate(evt);
            }
        });
        chkPPno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkPPnoMouseClicked(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("No Aplica");

        chkPPna.setEditable(false);
        chkPPna.setBackground(new java.awt.Color(255, 255, 255));
        chkPPna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkPPna.setForeground(new java.awt.Color(102, 102, 102));
        chkPPna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkPPna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkPPna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkPPna.setPreferredSize(new java.awt.Dimension(18, 18));
        chkPPna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkPPnaCaretUpdate(evt);
            }
        });
        chkPPna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkPPnaMouseClicked(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("SI");

        chkACEsi.setEditable(false);
        chkACEsi.setBackground(new java.awt.Color(255, 255, 255));
        chkACEsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkACEsi.setForeground(new java.awt.Color(102, 102, 102));
        chkACEsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkACEsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkACEsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkACEsi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkACEsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkACEsiCaretUpdate(evt);
            }
        });
        chkACEsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkACEsiMouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("No");

        chkACEno.setEditable(false);
        chkACEno.setBackground(new java.awt.Color(255, 255, 255));
        chkACEno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkACEno.setForeground(new java.awt.Color(102, 102, 102));
        chkACEno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkACEno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkACEno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkACEno.setPreferredSize(new java.awt.Dimension(18, 18));
        chkACEno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkACEnoCaretUpdate(evt);
            }
        });
        chkACEno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkACEnoMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("No Aplica");

        chkACEna.setEditable(false);
        chkACEna.setBackground(new java.awt.Color(255, 255, 255));
        chkACEna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkACEna.setForeground(new java.awt.Color(102, 102, 102));
        chkACEna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkACEna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkACEna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkACEna.setPreferredSize(new java.awt.Dimension(18, 18));
        chkACEna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkACEnaCaretUpdate(evt);
            }
        });
        chkACEna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkACEnaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPsico, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEstimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel53)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkPPsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel54)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(chkPPno, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkPPna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkACEsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkACEno, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel58)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkACEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtPsico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtEstimulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(chkPPsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(chkPPno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(chkPPna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkACEsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56)
                        .addComponent(chkACEno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57)
                        .addComponent(chkACEna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        chkHCMPp.setEditable(false);
        chkHCMPp.setBackground(new java.awt.Color(255, 204, 51));
        chkHCMPp.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkHCMPp.setForeground(new java.awt.Color(102, 102, 102));
        chkHCMPp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkHCMPp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkHCMPp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkHCMPp.setPreferredSize(new java.awt.Dimension(18, 18));
        chkHCMPp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkHCMPpCaretUpdate(evt);
            }
        });
        chkHCMPp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkHCMPpMouseClicked(evt);
            }
        });

        chkHCMPap.setEditable(false);
        chkHCMPap.setBackground(new java.awt.Color(255, 255, 255));
        chkHCMPap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkHCMPap.setForeground(new java.awt.Color(102, 102, 102));
        chkHCMPap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkHCMPap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkHCMPap.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkHCMPap.setPreferredSize(new java.awt.Dimension(18, 18));
        chkHCMPap.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkHCMPapCaretUpdate(evt);
            }
        });
        chkHCMPap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkHCMPapMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("HCMP");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 51, 51));
        jLabel59.setText("Atención Prenatal");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Aborto");

        chkHCMPa.setEditable(false);
        chkHCMPa.setBackground(new java.awt.Color(255, 255, 255));
        chkHCMPa.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkHCMPa.setForeground(new java.awt.Color(102, 102, 102));
        chkHCMPa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkHCMPa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkHCMPa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkHCMPa.setPreferredSize(new java.awt.Dimension(18, 18));
        chkHCMPa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkHCMPaCaretUpdate(evt);
            }
        });
        chkHCMPa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkHCMPaMouseClicked(evt);
            }
        });

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Parto");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("PRODUCTO DE LA CONCEPCIÓN");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Hijo Unico");

        chkHU.setEditable(false);
        chkHU.setBackground(new java.awt.Color(255, 255, 255));
        chkHU.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkHU.setForeground(new java.awt.Color(102, 102, 102));
        chkHU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkHU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkHU.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkHU.setPreferredSize(new java.awt.Dimension(18, 18));
        chkHU.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkHUCaretUpdate(evt);
            }
        });
        chkHU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkHUMouseClicked(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Embarazo Multiple");

        chkEM.setEditable(false);
        chkEM.setBackground(new java.awt.Color(255, 204, 51));
        chkEM.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkEM.setForeground(new java.awt.Color(102, 102, 102));
        chkEM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkEM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkEM.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkEM.setPreferredSize(new java.awt.Dimension(18, 18));
        chkEM.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkEMCaretUpdate(evt);
            }
        });
        chkEM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkEMMouseClicked(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setText("Orden");

        txtOrden.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtOrden.setForeground(new java.awt.Color(102, 102, 102));
        txtOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtOrden.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtOrdenCaretUpdate(evt);
            }
        });
        txtOrden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrdenKeyTyped(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Aborto");

        chkA.setEditable(false);
        chkA.setBackground(new java.awt.Color(255, 204, 51));
        chkA.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkA.setForeground(new java.awt.Color(102, 102, 102));
        chkA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkA.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkA.setPreferredSize(new java.awt.Dimension(18, 18));
        chkA.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkACaretUpdate(evt);
            }
        });
        chkA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(70, 70, 70)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chkHCMPap, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHCMPa, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkHCMPp, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chkHU, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkEM, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel59)
                    .addComponent(chkHCMPap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(chkHCMPa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61)
                    .addComponent(chkHCMPp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65)
                        .addComponent(chkA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel62)
                        .addComponent(chkHU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63)
                        .addComponent(chkEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel64)
                        .addComponent(txtOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdCp, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(881, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(lblIdCp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("<html>Patologías<br>Maternas<span style=\"font-size:'15px'\"><br>(CIE 10) Diagnosticadas</br></html>");

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

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("Leyenda");

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

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("ALERTA");

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

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Requiere Seguimiento Continuo");

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
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
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
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
                            .addComponent(jLabel37)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel43))
                            .addComponent(lblIdPM2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMant)
                                .addGap(109, 109, 109)
                                .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMant)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar)
                .addGap(47, 47, 47)
                .addComponent(lblIdPM2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblusu)
                .addGap(37, 37, 37)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(45, 45, 45))
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

        lblMadre.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(12, 97, 81));
        lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jPanel11.setBackground(new java.awt.Color(65, 65, 65));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(255, 255, 255));

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(779, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMadre)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkHCMPapCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPapCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPapCaretUpdate

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        String variable;
        variable=var.getText();

       if(variable=="1"){
             Guardar();  
//           
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

    private void chkHCMPapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPapMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkHCMPap.getText().equals("") && evt.getClickCount()==1){
           chkHCMPap.setText("X");
           chkHCMPa.setText("");
           chkHCMPp.setText("");
        }else
        if(chkHCMPap.getText().equals("X") && evt.getClickCount()==1){
           chkHCMPap.setText(""); 
           chkHCMPa.setText("");
           chkHCMPp.setText("");
        }
       }
    }//GEN-LAST:event_chkHCMPapMouseClicked

    private void txtPsicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPsicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsicoCaretUpdate

    private void txtPsicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPsicoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPsicoKeyTyped

    private void txtEstimulacionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstimulacionCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstimulacionCaretUpdate

    private void txtEstimulacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstimulacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstimulacionKeyTyped

    private void chkPPsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkPPsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPsiCaretUpdate

    private void chkPPsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPPsiMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkPPsi.getText().equals("") && evt.getClickCount()==1){
           chkPPsi.setText("X");
           chkPPno.setText("");
           chkPPna.setText("");
        }else
        if(chkPPsi.getText().equals("X") && evt.getClickCount()==1){
           chkPPsi.setText(""); 
           chkPPno.setText("");
           chkPPna.setText("");
        }
       }
    }//GEN-LAST:event_chkPPsiMouseClicked

    private void chkPPnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkPPnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnoCaretUpdate

    private void chkPPnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPPnoMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkPPno.getText().equals("") && evt.getClickCount()==1){
           chkPPno.setText("X");
           chkPPsi.setText("");
           chkPPna.setText("");
        }else
        if(chkPPno.getText().equals("X") && evt.getClickCount()==1){
           chkPPno.setText(""); 
           chkPPsi.setText("");
           chkPPna.setText("");
        }
       }
    }//GEN-LAST:event_chkPPnoMouseClicked

    private void chkPPnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkPPnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnaCaretUpdate

    private void chkPPnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPPnaMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkPPna.getText().equals("") && evt.getClickCount()==1){
           chkPPna.setText("X");
           chkPPsi.setText("");
           chkPPno.setText("");
        }else
        if(chkPPna.getText().equals("X") && evt.getClickCount()==1){
           chkPPna.setText(""); 
           chkPPsi.setText("");
           chkPPno.setText("");
        }
       }
    }//GEN-LAST:event_chkPPnaMouseClicked

    private void chkACEsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEsiCaretUpdate

    private void chkACEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEsiMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkACEsi.getText().equals("") && evt.getClickCount()==1){
           chkACEsi.setText("X");
           chkACEno.setText("");
           chkACEna.setText("");
        }else
        if(chkACEsi.getText().equals("X") && evt.getClickCount()==1){
           chkACEsi.setText(""); 
           chkACEno.setText("");
           chkACEna.setText("");
        }
       }
    }//GEN-LAST:event_chkACEsiMouseClicked

    private void chkACEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnoCaretUpdate

    private void chkACEnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEnoMouseClicked
         if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkACEno.getText().equals("") && evt.getClickCount()==1){
           chkACEno.setText("X");
           chkACEsi.setText("");
           chkACEna.setText("");
        }else
        if(chkACEno.getText().equals("X") && evt.getClickCount()==1){
           chkACEno.setText(""); 
           chkACEsi.setText("");
           chkACEna.setText("");
        }
       }
    }//GEN-LAST:event_chkACEnoMouseClicked

    private void chkACEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnaCaretUpdate

    private void chkACEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEnaMouseClicked
         if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkACEna.getText().equals("") && evt.getClickCount()==1){
           chkACEna.setText("X");
           chkACEno.setText("");
           chkACEsi.setText("");
        }else
        if(chkACEna.getText().equals("X") && evt.getClickCount()==1){
           chkACEna.setText(""); 
           chkACEno.setText("");
           chkACEsi.setText("");
        }
       }
    }//GEN-LAST:event_chkACEnaMouseClicked

    private void chkHCMPpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPpMouseClicked
       if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkHCMPp.getText().equals("") && evt.getClickCount()==1){
           chkHCMPp.setText("X");
           chkHCMPa.setText("");
           chkHCMPap.setText("");
        }else
        if(chkHCMPp.getText().equals("X") && evt.getClickCount()==1){
           chkHCMPp.setText(""); 
           chkHCMPa.setText("");
           chkHCMPap.setText("");
        }
       }
    }//GEN-LAST:event_chkHCMPpMouseClicked

    private void chkHCMPpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPpCaretUpdate

    private void chkHCMPaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPaCaretUpdate

    private void chkHCMPaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPaMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkHCMPa.getText().equals("") && evt.getClickCount()==1){
           chkHCMPa.setText("X");
           chkHCMPap.setText("");
           chkHCMPp.setText("");
        }else
        if(chkHCMPa.getText().equals("X") && evt.getClickCount()==1){
           chkHCMPa.setText(""); 
           chkHCMPap.setText("");
           chkHCMPp.setText("");
        }
       }
    }//GEN-LAST:event_chkHCMPaMouseClicked

    private void chkHUCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHUCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHUCaretUpdate

    private void chkHUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHUMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkHU.getText().equals("") && evt.getClickCount()==1){
           chkHU.setText("X");
           chkEM.setText("");
        }else
        if(chkHU.getText().equals("X") && evt.getClickCount()==1){
           chkHU.setText(""); 
           chkEM.setText("");
        }
       }
    }//GEN-LAST:event_chkHUMouseClicked

    private void chkEMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkEMCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEMCaretUpdate

    private void chkEMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEMMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkEM.getText().equals("") && evt.getClickCount()==1){
           chkEM.setText("X");
           chkHU.setText("");
        }else
        if(chkEM.getText().equals("X") && evt.getClickCount()==1){
           chkEM.setText(""); 
           chkHU.setText("");
        }
       }
    }//GEN-LAST:event_chkEMMouseClicked

    private void txtOrdenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOrdenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrdenCaretUpdate

    private void txtOrdenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrdenKeyTyped

    private void chkACaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACaretUpdate

    private void chkAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkAMouseClicked
        if (lblIdPM2.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkA.getText().equals("") && evt.getClickCount()==1){
           chkA.setText("X");
        }else
        if(chkA.getText().equals("X") && evt.getClickCount()==1){
           chkA.setText(""); 
        }
       }
    }//GEN-LAST:event_chkAMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkA;
    public static javax.swing.JTextField chkACEna;
    public static javax.swing.JTextField chkACEno;
    public static javax.swing.JTextField chkACEsi;
    public static javax.swing.JTextField chkEM;
    public static javax.swing.JTextField chkHCMPa;
    public static javax.swing.JTextField chkHCMPap;
    public static javax.swing.JTextField chkHCMPp;
    public static javax.swing.JTextField chkHU;
    public static javax.swing.JTextField chkPPna;
    public static javax.swing.JTextField chkPPno;
    public static javax.swing.JTextField chkPPsi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblIdCp;
    public static javax.swing.JLabel lblIdPM2;
    public static javax.swing.JLabel lblMadre;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtEstimulacion;
    public static javax.swing.JTextField txtOrden;
    public static javax.swing.JTextField txtPsico;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
