/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAf;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoAF extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoAF() {
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
        
    ConsultorioExtCarnetPerinatalAf CXRsAf= new ConsultorioExtCarnetPerinatalAf();
    ConsultorioExtCarnetPerinatalAf CXRsAf2 = new ConsultorioExtCarnetPerinatalAf();
     AdmisionEmergenciaCabecera admi = new AdmisionEmergenciaCabecera();
           if(lblMant.getText().equals("U"))
            CXRsAf.setAfId(Integer.parseInt(lblIdAF.getText()));
            CXRsAf.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            //////////////////////////////////
            if(txtAf1.getText().equals("X"))
                CXRsAf.setAfNinguno("X");
            //////////////////////////////////
            if(txtAf2.getText().equals("X"))
                CXRsAf.setAfAlergias("X");
            //////////////////////////////////
            if(txtAf3.getText().equals("X"))
                CXRsAf.setAfHipertens("X");
            //////////////////////////////////
            if(txtAf4.getText().equals("X"))
                CXRsAf.setAfEpilepsia("X");
            //////////////////////////////////
            if(txtAf5.getText().equals("X"))
                CXRsAf.setAfDiabetes("X");
            //////////////////////////////////
            if(txtAf6.getText().equals("X"))
                CXRsAf.setAfEnfCongenitas("X");
            //////////////////////////////////
            if(txtAf7.getText().equals("X"))
                CXRsAf.setAfEmbMultiple("X");
            //////////////////////////////////
            if(txtAf8.getText().equals("X"))
                CXRsAf.setAfMalaria("X");
            //////////////////////////////////
            if(txtAf9.getText().equals("X"))
                CXRsAf.setAfHiperArterial("X");
            //////////////////////////////////
            if(txtAf10.getText().equals("X"))
                CXRsAf.setAfHipotiroidismo("X");
            //////////////////////////////////
            if(txtAf11.getText().equals("X"))
                CXRsAf.setAfNeoplasica("X");
            //////////////////////////////////
            if(txtAf12.getText().equals("X"))
                CXRsAf.setAfTbc("X");
            //////////////////////////////////
            
            
            if(txtAf13.getText().equals("X"))
                
                CXRsAf.setAfOtros(txtOtros.getText());

                CXRsAf.setCodUsu(admi.codUsuario(lblusu.getText()));//falta 
                CXRsAf.setIdActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
            
                if(CXRsAf.mantenimientoConsultorioExtAF(lblMant.getText())==true){
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
                    CXRsAf2.ConsultoriosExtAFListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
 
                    CXRsAf2.ConsultoriosExtAFListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
        txtAf3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAf7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAf1 = new javax.swing.JTextField();
        txtAf2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAf5 = new javax.swing.JTextField();
        txtAf4 = new javax.swing.JTextField();
        txtAf6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtAf8 = new javax.swing.JTextField();
        txtAf9 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAf10 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAf11 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtAf12 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtAf13 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOtros = new javax.swing.JEditorPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        ChkAnalf2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        ChkEdad2 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblMant = new javax.swing.JLabel();
        lblIdAF = new javax.swing.JLabel();
        lblMadreAf = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        var = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtAf3.setEditable(false);
        txtAf3.setBackground(new java.awt.Color(255, 204, 51));
        txtAf3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf3.setForeground(new java.awt.Color(102, 102, 102));
        txtAf3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf3.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf3.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf3.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf3CaretUpdate(evt);
            }
        });
        txtAf3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf3MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Enf. Hipertens. Emb.");

        txtAf7.setEditable(false);
        txtAf7.setBackground(new java.awt.Color(255, 204, 51));
        txtAf7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf7.setForeground(new java.awt.Color(102, 102, 102));
        txtAf7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf7.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf7.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf7.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf7CaretUpdate(evt);
            }
        });
        txtAf7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Alergias");

        txtAf1.setEditable(false);
        txtAf1.setBackground(new java.awt.Color(255, 255, 255));
        txtAf1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf1.setForeground(new java.awt.Color(102, 102, 102));
        txtAf1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf1.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf1.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtAf1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf1CaretUpdate(evt);
            }
        });
        txtAf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf1MouseClicked(evt);
            }
        });

        txtAf2.setEditable(false);
        txtAf2.setBackground(new java.awt.Color(255, 204, 51));
        txtAf2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf2.setForeground(new java.awt.Color(102, 102, 102));
        txtAf2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf2.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf2.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf2CaretUpdate(evt);
            }
        });
        txtAf2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf2MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Epilepsia");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Diabétes");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Ninguno");

        txtAf5.setEditable(false);
        txtAf5.setBackground(new java.awt.Color(255, 204, 51));
        txtAf5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf5.setForeground(new java.awt.Color(102, 102, 102));
        txtAf5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf5.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf5.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf5.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf5CaretUpdate(evt);
            }
        });
        txtAf5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf5MouseClicked(evt);
            }
        });

        txtAf4.setEditable(false);
        txtAf4.setBackground(new java.awt.Color(255, 204, 51));
        txtAf4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf4.setForeground(new java.awt.Color(102, 102, 102));
        txtAf4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf4.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf4.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf4.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf4CaretUpdate(evt);
            }
        });
        txtAf4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf4MouseClicked(evt);
            }
        });

        txtAf6.setEditable(false);
        txtAf6.setBackground(new java.awt.Color(255, 204, 51));
        txtAf6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf6.setForeground(new java.awt.Color(102, 102, 102));
        txtAf6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf6.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf6.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf6.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf6CaretUpdate(evt);
            }
        });
        txtAf6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf6MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Enferm. Congénitas");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Emb. Multiple");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Malaria");

        txtAf8.setEditable(false);
        txtAf8.setBackground(new java.awt.Color(255, 204, 51));
        txtAf8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf8.setForeground(new java.awt.Color(102, 102, 102));
        txtAf8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf8.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf8.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf8.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf8.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf8CaretUpdate(evt);
            }
        });
        txtAf8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf8MouseClicked(evt);
            }
        });

        txtAf9.setEditable(false);
        txtAf9.setBackground(new java.awt.Color(255, 204, 51));
        txtAf9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf9.setForeground(new java.awt.Color(102, 102, 102));
        txtAf9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf9.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf9.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf9.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf9.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf9CaretUpdate(evt);
            }
        });
        txtAf9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf9MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Hipertensión Arterial");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Hipotirodismo");

        txtAf10.setEditable(false);
        txtAf10.setBackground(new java.awt.Color(255, 204, 51));
        txtAf10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf10.setForeground(new java.awt.Color(102, 102, 102));
        txtAf10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf10.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf10.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf10.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf10CaretUpdate(evt);
            }
        });
        txtAf10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf10MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Neoplástica");

        txtAf11.setEditable(false);
        txtAf11.setBackground(new java.awt.Color(255, 204, 51));
        txtAf11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf11.setForeground(new java.awt.Color(102, 102, 102));
        txtAf11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf11.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf11.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf11.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf11CaretUpdate(evt);
            }
        });
        txtAf11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf11MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("TBC Pulmonar");

        txtAf12.setEditable(false);
        txtAf12.setBackground(new java.awt.Color(255, 204, 51));
        txtAf12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf12.setForeground(new java.awt.Color(102, 102, 102));
        txtAf12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf12.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf12.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf12.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf12.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf12CaretUpdate(evt);
            }
        });
        txtAf12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf12MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Otros");

        txtAf13.setEditable(false);
        txtAf13.setBackground(new java.awt.Color(255, 204, 51));
        txtAf13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAf13.setForeground(new java.awt.Color(102, 102, 102));
        txtAf13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAf13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAf13.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAf13.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAf13.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAf13.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAf13CaretUpdate(evt);
            }
        });
        txtAf13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAf13MouseClicked(evt);
            }
        });

        txtOtros.setEditable(false);
        txtOtros.setForeground(new java.awt.Color(51, 51, 51));
        txtOtros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtrosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtrosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtOtros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel32)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAf7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAf5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAf11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAf8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAf9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAf10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAf13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAf12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtAf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtAf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtAf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtAf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtAf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtAf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(txtAf8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtAf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtAf10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtAf11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtAf12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(txtAf13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(txtAf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("<html>Antecedentes <br>Familiares</html>");

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

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("ALERTA");

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

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
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
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jLabel26)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel38))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblMant)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdAF, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMant)
                    .addComponent(lblIdAF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(249, 249, 249))
        );

        lblMadreAf.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadreAf.setForeground(new java.awt.Color(12, 97, 81));
        lblMadreAf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadreAf.setText("Martha Arias Torres");
        lblMadreAf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadreAf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

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

        var.setText("1");

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
                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(844, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMadreAf, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 92, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(561, 561, 561)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1251, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMadreAf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(182, 182, 182)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(330, 330, 330)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(366, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAf1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf1CaretUpdate

    private void txtAf2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf2CaretUpdate

    private void txtAf3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf3CaretUpdate

    private void txtAf4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf4CaretUpdate

    private void txtAf5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf5CaretUpdate

    private void txtAf6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf6CaretUpdate

    private void txtAf7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf7CaretUpdate

    private void txtAf8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf8CaretUpdate

    private void txtAf9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf9CaretUpdate

    private void txtAf10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf10CaretUpdate

    private void txtAf11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf11CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf11CaretUpdate

    private void txtAf12CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf12CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf12CaretUpdate

    private void txtAf13CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAf13CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAf13CaretUpdate

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

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel33MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
btnGuardar.setEnabled(true);
         btneditar.setEnabled(false);

         lblMant.setText("U");
         
         if (txtAf13.getText().equals("X")){
             txtOtros.setEditable(true);
         }
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

    private void txtOtrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyReleased
        txtOtros.setText(txtOtros.getText().toUpperCase());
    }//GEN-LAST:event_txtOtrosKeyReleased

    private void txtOtrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyTyped

    }//GEN-LAST:event_txtOtrosKeyTyped

    private void txtAf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf1MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf1.getText().equals("") && evt.getClickCount()==1){
           txtAf1.setText("X");  
           
           txtAf2.setText("");
           txtAf3.setText("");
           txtAf4.setText("");
           txtAf5.setText("");
           txtAf6.setText("");
           txtAf7.setText("");
           txtAf8.setText("");
           txtAf9.setText("");
           txtAf10.setText("");
           txtAf11.setText("");
           txtAf12.setText("");
           txtAf13.setText("");
          
        }else
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf1.setText(""); 
           txtAf2.setText("");
           txtAf3.setText("");
           txtAf4.setText("");
           txtAf5.setText("");
           txtAf6.setText("");
           txtAf7.setText("");
           txtAf8.setText("");
           txtAf9.setText("");
           txtAf10.setText("");
           txtAf11.setText("");
           txtAf12.setText("");
           txtAf13.setText("");
        }
        if(!txtOtros.getText().equals("")){
           txtAf13.setText("X"); 
        }
        }
    }//GEN-LAST:event_txtAf1MouseClicked

    private void txtAf13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf13MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf13.getText().equals("") && evt.getClickCount()==1){
           txtAf13.setText("X");
           txtOtros.setEditable(true);
           txtOtros.requestFocus();
 
        }else
        if(txtAf13.getText().equals("X") && evt.getClickCount()==1){
           txtAf13.setText(""); 
           txtOtros.setEditable(false);
           txtOtros.setText("");
        }
        }
    }//GEN-LAST:event_txtAf13MouseClicked

    private void txtAf2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf2MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf2.getText().equals("") && evt.getClickCount()==1){
           txtAf2.setText("X");
        }else
        if(txtAf2.getText().equals("X") && evt.getClickCount()==1){
           txtAf2.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf2.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf2MouseClicked

    private void txtAf3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf3MouseClicked
       if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf3.getText().equals("") && evt.getClickCount()==1){
           txtAf3.setText("X");
        }else
        if(txtAf3.getText().equals("X") && evt.getClickCount()==1){
           txtAf3.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf3.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf3MouseClicked

    private void txtAf4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf4MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf4.getText().equals("") && evt.getClickCount()==1){
           txtAf4.setText("X");
        }else
        if(txtAf4.getText().equals("X") && evt.getClickCount()==1){
           txtAf4.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf4.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf4MouseClicked

    private void txtAf5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf5MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf5.getText().equals("") && evt.getClickCount()==1){
           txtAf5.setText("X");
        }else
        if(txtAf5.getText().equals("X") && evt.getClickCount()==1){
           txtAf5.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf5.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf5MouseClicked

    private void txtAf6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf6MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf6.getText().equals("") && evt.getClickCount()==1){
           txtAf6.setText("X");
        }else
        if(txtAf6.getText().equals("X") && evt.getClickCount()==1){
           txtAf6.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf6.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf6MouseClicked

    private void txtAf7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf7MouseClicked
        if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf7.getText().equals("") && evt.getClickCount()==1){
           txtAf7.setText("X");
        }else
        if(txtAf7.getText().equals("X") && evt.getClickCount()==1){
           txtAf7.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf7.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf7MouseClicked

    private void txtAf8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf8MouseClicked
         if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf8.getText().equals("") && evt.getClickCount()==1){
           txtAf8.setText("X");
        }else
        if(txtAf8.getText().equals("X") && evt.getClickCount()==1){
           txtAf8.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf8.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf8MouseClicked

    private void txtAf9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf9MouseClicked
         if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf9.getText().equals("") && evt.getClickCount()==1){
           txtAf9.setText("X");
        }else
        if(txtAf9.getText().equals("X") && evt.getClickCount()==1){
           txtAf9.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf9.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf9MouseClicked

    private void txtAf10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf10MouseClicked
         if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf10.getText().equals("") && evt.getClickCount()==1){
           txtAf10.setText("X");
        }else
        if(txtAf10.getText().equals("X") && evt.getClickCount()==1){
           txtAf10.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf10.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf10MouseClicked

    private void txtAf11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf11MouseClicked
         if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf11.getText().equals("") && evt.getClickCount()==1){
           txtAf11.setText("X");
        }else
        if(txtAf11.getText().equals("X") && evt.getClickCount()==1){
           txtAf11.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf11.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf11MouseClicked

    private void txtAf12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAf12MouseClicked
         if (lblIdAF.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAf12.getText().equals("") && evt.getClickCount()==1){
           txtAf12.setText("X");
        }else
        if(txtAf12.getText().equals("X") && evt.getClickCount()==1){
           txtAf12.setText(""); 
        }
        
        if(txtAf1.getText().equals("X") && evt.getClickCount()==1){
           txtAf12.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAf12MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf2;
    public static javax.swing.JTextField ChkEdad2;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdAF;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblMadreAf;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtAf1;
    public static javax.swing.JTextField txtAf10;
    public static javax.swing.JTextField txtAf11;
    public static javax.swing.JTextField txtAf12;
    public static javax.swing.JTextField txtAf13;
    public static javax.swing.JTextField txtAf2;
    public static javax.swing.JTextField txtAf3;
    public static javax.swing.JTextField txtAf4;
    public static javax.swing.JTextField txtAf5;
    public static javax.swing.JTextField txtAf6;
    public static javax.swing.JTextField txtAf7;
    public static javax.swing.JTextField txtAf8;
    public static javax.swing.JTextField txtAf9;
    public static javax.swing.JEditorPane txtOtros;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
