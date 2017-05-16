/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAp;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoAP extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoAP() {
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
        
    ConsultorioExtCarnetPerinatalAp CXRsAP= new ConsultorioExtCarnetPerinatalAp();
    ConsultorioExtCarnetPerinatalAp CXRsAP2 = new ConsultorioExtCarnetPerinatalAp();
    AdmisionEmergenciaCabecera admi = new AdmisionEmergenciaCabecera();
           if(lblMant.getText().equals("U"))
            CXRsAP.setApId(Integer.parseInt(lblIdAP.getText()));
            CXRsAP.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            //////////////////////////////////
            if(txtAp1.getText().equals("X"))
                CXRsAP.setAp1("X");

            //////////////////////////////////
            if(txtAp2.getText().equals("X"))
                CXRsAP.setAp2("X");

            //////////////////////////////////
            if(txtAp3.getText().equals("X"))
                CXRsAP.setAp3("X");
  
             //////////////////////////////////
            if(txtAp4.getText().equals("X"))
                CXRsAP.setAp4("X");

            //////////////////////////////////
            if(txtAp5.getText().equals("X"))
                CXRsAP.setAp5("X");
  
            //////////////////////////////////
            if(txtAp6.getText().equals("X"))
                CXRsAP.setAp6("X");
           
            //////////////////////////////////
            if(txtAp7.getText().equals("X"))
                CXRsAP.setAp7("X");
        
            //////////////////////////////////
            if(txtAp8.getText().equals("X"))
                CXRsAP.setAp8("X");
       
            //////////////////////////////////
            if(txtAp9.getText().equals("X"))
                CXRsAP.setAp9("X");
       
            //////////////////////////////////
            if(txtAp10.getText().equals("X"))
                CXRsAP.setAp10("X");
          
            //////////////////////////////////
            if(txtAp11.getText().equals("X"))
                CXRsAP.setAp11("X");
         
            //////////////////////////////////
            if(txtAp12.getText().equals("X"))
                CXRsAP.setAp12("X");
          
            //////////////////////////////////
            if(txtAp13.getText().equals("X"))
                CXRsAP.setAp13("X");
          
            //////////////////////////////////
            if(txtAp14.getText().equals("X"))
                CXRsAP.setAp14("X");
          
            //////////////////////////////////
            if(txtAp15.getText().equals("X"))
                CXRsAP.setAp15("X");
          
            //////////////////////////////////
            if(txtAp16.getText().equals("X"))
                CXRsAP.setAp16("X");
           
            //////////////////////////////////
            if(txtAp17.getText().equals("X"))
                CXRsAP.setAp17("X");
          
            ////////////////////////////////// 
            if(txtAp18.getText().equals("X"))
                CXRsAP.setAp18("X");

            //////////////////////////////////
            if(txtAp19.getText().equals("X"))
                CXRsAP.setAp19("X");

            //////////////////////////////////
            if(txtAp20.getText().equals("X"))
                CXRsAP.setAp20("X");
 
            //////////////////////////////////
            if(txtAp21.getText().equals("X"))
                CXRsAP.setAp21("X");

            //////////////////////////////////
            if(txtAp22.getText().equals("X"))
                CXRsAP.setAp22("X");

            //////////////////////////////////
            if(txtAp23.getText().equals("X"))
                CXRsAP.setAp23("X");

            //////////////////////////////////
            if(txtAp24.getText().equals("X"))
                CXRsAP.setAp24("X");

            //////////////////////////////////
            if(txtAp25.getText().equals("X"))
                CXRsAP.setAp25("X");

            //////////////////////////////////
            if(txtAp26.getText().equals("X"))
                CXRsAP.setAp26("X");

            //////////////////////////////////
            if(txtAp27.getText().equals("X"))
                CXRsAP.setAp27("X");

            //////////////////////////////////
            if(txtAp28.getText().equals("X"))
                
                CXRsAP.setAp28(txtOtros.getText());

                CXRsAP.setCodUsu(admi.codUsuario(lblusu.getText()));//falta 
                CXRsAP.setIdActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
            
                if(CXRsAP.mantenimientoConsultorioExtAP(lblMant.getText())==true){
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
                    CXRsAP2.ConsultoriosExtAPListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
 
                    CXRsAP2.ConsultoriosExtAPListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtAp3 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtAp14 = new javax.swing.JTextField();
        txtAp11 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtAp13 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtAp28 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAp24 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtAp21 = new javax.swing.JTextField();
        txtAp22 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtAp8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAp18 = new javax.swing.JTextField();
        txtAp16 = new javax.swing.JTextField();
        txtAp17 = new javax.swing.JTextField();
        txtAp7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtAp1 = new javax.swing.JTextField();
        txtAp2 = new javax.swing.JTextField();
        txtAp26 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtAp10 = new javax.swing.JTextField();
        txtAp19 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtAp15 = new javax.swing.JTextField();
        txtAp23 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtAp5 = new javax.swing.JTextField();
        txtAp12 = new javax.swing.JTextField();
        txtAp4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAp27 = new javax.swing.JTextField();
        txtAp20 = new javax.swing.JTextField();
        txtAp6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtAp9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAp25 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        var = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOtros = new javax.swing.JEditorPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        ChkAnalf1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        ChkEdad1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblMant = new javax.swing.JLabel();
        lblIdAP = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblMadre = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
<<<<<<< HEAD
        jPanel3 = new javax.swing.JPanel();
=======
        jPanel7 = new javax.swing.JPanel();
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Otras Drogas");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Tabaco");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Enferm. Infecciosas");

        txtAp3.setEditable(false);
        txtAp3.setBackground(new java.awt.Color(255, 204, 51));
        txtAp3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp3.setForeground(new java.awt.Color(102, 102, 102));
        txtAp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp3.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp3.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp3.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp3CaretUpdate(evt);
            }
        });
        txtAp3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp3MouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Transtorn. mentales");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Prematuridad");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Diabétes");

        txtAp14.setEditable(false);
        txtAp14.setBackground(new java.awt.Color(255, 51, 51));
        txtAp14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp14.setForeground(new java.awt.Color(255, 255, 255));
        txtAp14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp14.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp14.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp14.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp14CaretUpdate(evt);
            }
        });
        txtAp14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp14MouseClicked(evt);
            }
        });

        txtAp11.setEditable(false);
        txtAp11.setBackground(new java.awt.Color(255, 204, 51));
        txtAp11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp11.setForeground(new java.awt.Color(102, 102, 102));
        txtAp11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp11.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp11.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp11.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp11.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp11CaretUpdate(evt);
            }
        });
        txtAp11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp11MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("TBC Pulmonar");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Neoplasia");

        txtAp13.setEditable(false);
        txtAp13.setBackground(new java.awt.Color(255, 204, 51));
        txtAp13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp13.setForeground(new java.awt.Color(102, 102, 102));
        txtAp13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp13.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp13.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp13.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp13.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp13CaretUpdate(evt);
            }
        });
        txtAp13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp13MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Infertilidad");

        txtAp28.setEditable(false);
        txtAp28.setBackground(new java.awt.Color(255, 204, 51));
        txtAp28.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp28.setForeground(new java.awt.Color(102, 102, 102));
        txtAp28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp28.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp28.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp28.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp28.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp28CaretUpdate(evt);
            }
        });
        txtAp28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp28MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Hipertensión Arterial");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Alcoholismo");

        txtAp24.setEditable(false);
        txtAp24.setBackground(new java.awt.Color(255, 204, 51));
        txtAp24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp24.setForeground(new java.awt.Color(102, 102, 102));
        txtAp24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp24.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp24.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp24.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp24.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp24CaretUpdate(evt);
            }
        });
        txtAp24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp24MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("VIH / SIDA");

        txtAp21.setEditable(false);
        txtAp21.setBackground(new java.awt.Color(255, 204, 51));
        txtAp21.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp21.setForeground(new java.awt.Color(102, 102, 102));
        txtAp21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp21.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp21.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp21.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp21.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp21CaretUpdate(evt);
            }
        });
        txtAp21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp21MouseClicked(evt);
            }
        });

        txtAp22.setEditable(false);
        txtAp22.setBackground(new java.awt.Color(255, 204, 51));
        txtAp22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp22.setForeground(new java.awt.Color(102, 102, 102));
        txtAp22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp22.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp22.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp22.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp22.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp22CaretUpdate(evt);
            }
        });
        txtAp22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp22MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Otros");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Coca");

        txtAp8.setEditable(false);
        txtAp8.setBackground(new java.awt.Color(255, 51, 51));
        txtAp8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp8.setForeground(new java.awt.Color(255, 255, 255));
        txtAp8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp8.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp8.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp8.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp8CaretUpdate(evt);
            }
        });
        txtAp8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp8MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Epilepsia");

        txtAp18.setEditable(false);
        txtAp18.setBackground(new java.awt.Color(255, 204, 51));
        txtAp18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp18.setForeground(new java.awt.Color(102, 102, 102));
        txtAp18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp18.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp18.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp18.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp18.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp18CaretUpdate(evt);
            }
        });
        txtAp18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp18MouseClicked(evt);
            }
        });

        txtAp16.setEditable(false);
        txtAp16.setBackground(new java.awt.Color(255, 204, 51));
        txtAp16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp16.setForeground(new java.awt.Color(102, 102, 102));
        txtAp16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp16.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp16.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp16.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp16.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp16CaretUpdate(evt);
            }
        });
        txtAp16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp16MouseClicked(evt);
            }
        });

        txtAp17.setEditable(false);
        txtAp17.setBackground(new java.awt.Color(255, 204, 51));
        txtAp17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp17.setForeground(new java.awt.Color(102, 102, 102));
        txtAp17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp17.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp17.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp17.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp17.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp17CaretUpdate(evt);
            }
        });
        txtAp17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp17MouseClicked(evt);
            }
        });

        txtAp7.setEditable(false);
        txtAp7.setBackground(new java.awt.Color(255, 51, 51));
        txtAp7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp7.setForeground(new java.awt.Color(255, 255, 255));
        txtAp7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp7.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp7.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp7.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp7CaretUpdate(evt);
            }
        });
        txtAp7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Aborto habitual / recurrente");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Hemorra. Postparto");

        txtAp1.setEditable(false);
        txtAp1.setBackground(new java.awt.Color(255, 255, 255));
        txtAp1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp1.setForeground(new java.awt.Color(102, 102, 102));
        txtAp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp1.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp1.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtAp1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp1CaretUpdate(evt);
            }
        });
        txtAp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp1MouseClicked(evt);
            }
        });
        txtAp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAp1ActionPerformed(evt);
            }
        });

        txtAp2.setEditable(false);
        txtAp2.setBackground(new java.awt.Color(255, 51, 51));
        txtAp2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp2.setForeground(new java.awt.Color(255, 255, 255));
        txtAp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp2.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp2CaretUpdate(evt);
            }
        });
        txtAp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp2MouseClicked(evt);
            }
        });

        txtAp26.setEditable(false);
        txtAp26.setBackground(new java.awt.Color(255, 204, 51));
        txtAp26.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp26.setForeground(new java.awt.Color(102, 102, 102));
        txtAp26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp26.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp26.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp26.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp26.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp26CaretUpdate(evt);
            }
        });
        txtAp26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp26MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Alergia medicamentos");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Reten. Placenta");

        txtAp10.setEditable(false);
        txtAp10.setBackground(new java.awt.Color(255, 51, 51));
        txtAp10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp10.setForeground(new java.awt.Color(255, 255, 255));
        txtAp10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp10.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp10.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp10.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp10CaretUpdate(evt);
            }
        });
        txtAp10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp10MouseClicked(evt);
            }
        });

        txtAp19.setEditable(false);
        txtAp19.setBackground(new java.awt.Color(255, 204, 51));
        txtAp19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp19.setForeground(new java.awt.Color(102, 102, 102));
        txtAp19.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp19.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp19.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp19.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp19.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp19CaretUpdate(evt);
            }
        });
        txtAp19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp19MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Violencia");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Ninguno");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Preeclampsia");

        txtAp15.setEditable(false);
        txtAp15.setBackground(new java.awt.Color(255, 51, 51));
        txtAp15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp15.setForeground(new java.awt.Color(255, 255, 255));
        txtAp15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp15.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp15.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp15.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp15.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp15CaretUpdate(evt);
            }
        });
        txtAp15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp15MouseClicked(evt);
            }
        });

        txtAp23.setEditable(false);
        txtAp23.setBackground(new java.awt.Color(255, 204, 51));
        txtAp23.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp23.setForeground(new java.awt.Color(102, 102, 102));
        txtAp23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp23.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp23.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp23.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp23.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp23.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp23CaretUpdate(evt);
            }
        });
        txtAp23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp23MouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Parto Prolong.");

        txtAp5.setEditable(false);
        txtAp5.setBackground(new java.awt.Color(255, 204, 51));
        txtAp5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp5.setForeground(new java.awt.Color(102, 102, 102));
        txtAp5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp5.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp5.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp5.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp5CaretUpdate(evt);
            }
        });
        txtAp5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp5MouseClicked(evt);
            }
        });

        txtAp12.setEditable(false);
        txtAp12.setBackground(new java.awt.Color(255, 204, 51));
        txtAp12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp12.setForeground(new java.awt.Color(102, 102, 102));
        txtAp12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp12.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp12.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp12.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp12.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp12CaretUpdate(evt);
            }
        });
        txtAp12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp12MouseClicked(evt);
            }
        });

        txtAp4.setEditable(false);
        txtAp4.setBackground(new java.awt.Color(255, 204, 51));
        txtAp4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp4.setForeground(new java.awt.Color(102, 102, 102));
        txtAp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp4.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp4.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp4.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp4CaretUpdate(evt);
            }
        });
        txtAp4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp4MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Enferm. congénitas");

        txtAp27.setEditable(false);
        txtAp27.setBackground(new java.awt.Color(255, 51, 51));
        txtAp27.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp27.setForeground(new java.awt.Color(255, 255, 255));
        txtAp27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp27.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp27.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp27.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp27CaretUpdate(evt);
            }
        });
        txtAp27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp27MouseClicked(evt);
            }
        });

        txtAp20.setEditable(false);
        txtAp20.setBackground(new java.awt.Color(255, 204, 51));
        txtAp20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp20.setForeground(new java.awt.Color(102, 102, 102));
        txtAp20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp20.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp20.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp20.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp20.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp20CaretUpdate(evt);
            }
        });
        txtAp20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp20MouseClicked(evt);
            }
        });

        txtAp6.setEditable(false);
        txtAp6.setBackground(new java.awt.Color(255, 204, 51));
        txtAp6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp6.setForeground(new java.awt.Color(102, 102, 102));
        txtAp6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp6.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp6.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp6.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp6CaretUpdate(evt);
            }
        });
        txtAp6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp6MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Asma Bronquial");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Cirugia Pelv. uterina");

        txtAp9.setEditable(false);
        txtAp9.setBackground(new java.awt.Color(255, 204, 51));
        txtAp9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp9.setForeground(new java.awt.Color(102, 102, 102));
        txtAp9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp9.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp9.setSelectedTextColor(new java.awt.Color(102, 102, 102));
        txtAp9.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAp9.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp9CaretUpdate(evt);
            }
        });
        txtAp9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp9MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Eclampsia");

        txtAp25.setEditable(false);
        txtAp25.setBackground(new java.awt.Color(255, 51, 51));
        txtAp25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAp25.setForeground(new java.awt.Color(255, 255, 255));
        txtAp25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAp25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAp25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtAp25.setPreferredSize(new java.awt.Dimension(18, 18));
        txtAp25.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtAp25.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAp25CaretUpdate(evt);
            }
        });
        txtAp25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAp25MouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Cardiopatia");

        var.setText("1");

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
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel29))
                                    .addGap(50, 50, 50)))
                            .addComponent(jLabel32))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAp7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel34)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel14)
                            .addComponent(jLabel35)
                            .addComponent(jLabel13))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAp16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAp17, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel36)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel25))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAp19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAp27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtAp19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtAp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtAp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtAp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtAp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtAp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel32)
                                .addComponent(txtAp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(txtAp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(txtAp9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtAp11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtAp12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtAp13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(txtAp14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(txtAp15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(txtAp16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(txtAp17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(txtAp18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtAp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAp10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(249, 249, 249)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(txtAp21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtAp22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(txtAp23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtAp24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtAp25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36)
                                    .addComponent(txtAp26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel38)
                                            .addComponent(txtAp27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAp28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtAp20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(249, 249, 249)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));

        jLabel45.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("<html>Antecedentes <br>Personales y<br>Vacunas</html>");

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

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(204, 204, 204));
        jLabel46.setText("Leyenda");

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

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel47MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnCaccnelar)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblMant)
                                .addGap(18, 18, 18)
                                .addComponent(lblIdAP, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)))
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMant)
                    .addComponent(lblIdAP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(79, 79, 79))
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Antecedentes Personales");

        lblMadre.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(12, 97, 81));
        lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

<<<<<<< HEAD
        jPanel3.setBackground(new java.awt.Color(65, 65, 65));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(204, 204, 204));
        lblActoMedico.setText("Acto Médico");

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(204, 204, 204));
        lblFP.setText("Forma de Pago");
=======
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
                .addContainerGap(738, Short.MAX_VALUE))
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
<<<<<<< HEAD
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
=======
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(586, 586, 586)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1144, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
<<<<<<< HEAD
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
=======
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                .addComponent(lblMadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
<<<<<<< HEAD
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 606, Short.MAX_VALUE)
=======
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 615, Short.MAX_VALUE)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(273, 273, 273)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                    .addContainerGap(312, Short.MAX_VALUE)))
=======
                    .addContainerGap(321, Short.MAX_VALUE)))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAp1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp1CaretUpdate

    private void txtAp2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp2CaretUpdate

    private void txtAp3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp3CaretUpdate

    private void txtAp4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp4CaretUpdate

    private void txtAp5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp5CaretUpdate

    private void txtAp6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp6CaretUpdate

    private void txtAp7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp7CaretUpdate

    private void txtAp8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp8CaretUpdate

    private void txtAp9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp9CaretUpdate

    private void txtAp15CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp15CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp15CaretUpdate

    private void txtAp12CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp12CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp12CaretUpdate

    private void txtAp11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp11CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp11CaretUpdate

    private void txtAp10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp10CaretUpdate

    private void txtAp14CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp14CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp14CaretUpdate

    private void txtAp13CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp13CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp13CaretUpdate

    private void txtAp17CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp17CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp17CaretUpdate

    private void txtAp18CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp18CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp18CaretUpdate

    private void txtAp16CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp16CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp16CaretUpdate

    private void txtAp20CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp20CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp20CaretUpdate

    private void txtAp21CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp21CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp21CaretUpdate

    private void txtAp22CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp22CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp22CaretUpdate

    private void txtAp23CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp23CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp23CaretUpdate

    private void txtAp24CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp24CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp24CaretUpdate

    private void txtAp25CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp25CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp25CaretUpdate

    private void txtAp26CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp26CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp26CaretUpdate

    private void txtAp27CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp27CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp27CaretUpdate

    private void txtAp28CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp28CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp28CaretUpdate

    private void txtAp19CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAp19CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp19CaretUpdate

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

    private void jLabel47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel47MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel47MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         btnGuardar.setEnabled(true);
         btneditar.setEnabled(false);

         lblMant.setText("U");
         
         if (txtAp28.getText().equals("X")){
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

    private void txtAp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAp1ActionPerformed

    private void txtAp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp1MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp1.getText().equals("") && evt.getClickCount()==1){
           txtAp1.setText("X");  
           txtAp2.setText("");
           txtAp3.setText("");
           txtAp4.setText("");
           txtAp5.setText("");
           txtAp6.setText("");
           txtAp7.setText("");
           txtAp8.setText("");
           txtAp9.setText("");
           txtAp10.setText("");
           txtAp11.setText("");
           txtAp12.setText("");
           txtAp13.setText("");
           txtAp14.setText("");
           txtAp15.setText("");
           txtAp16.setText("");
           txtAp17.setText("");
           txtAp18.setText("");
           txtAp19.setText("");
           txtAp20.setText("");
           txtAp21.setText("");
           txtAp22.setText("");
           txtAp23.setText("");
           txtAp24.setText("");
           txtAp25.setText("");
           txtAp26.setText("");
           txtAp27.setText("");
           txtAp28.setText("");
        }else
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp1.setText(""); 
           txtAp2.setText("");
           txtAp3.setText("");
           txtAp4.setText("");
           txtAp5.setText("");
           txtAp6.setText("");
           txtAp7.setText("");
           txtAp8.setText("");
           txtAp9.setText("");
           txtAp10.setText("");
           txtAp11.setText("");
           txtAp12.setText("");
           txtAp13.setText("");
           txtAp14.setText("");
           txtAp15.setText("");
           txtAp16.setText("");
           txtAp17.setText("");
           txtAp18.setText("");
           txtAp19.setText("");
           txtAp20.setText("");
           txtAp21.setText("");
           txtAp22.setText("");
           txtAp23.setText("");
           txtAp24.setText("");
           txtAp25.setText("");
           txtAp26.setText("");
           txtAp27.setText("");
           txtAp28.setText("");
        }
        if(!txtOtros.getText().equals("")){
           txtAp28.setText("X"); 
        }
        }
    }//GEN-LAST:event_txtAp1MouseClicked

    private void txtAp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp2MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp2.getText().equals("") && evt.getClickCount()==1){
           txtAp2.setText("X");
        }else
        if(txtAp2.getText().equals("X") && evt.getClickCount()==1){
           txtAp2.setText(""); 
        }
        
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp2.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp2MouseClicked

    private void txtAp3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp3MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
            
        if(txtAp3.getText().equals("X") && evt.getClickCount()==1){
           txtAp3.setText(""); 
        }
        if(txtAp3.getText().equals("") && evt.getClickCount()==1){
           txtAp3.setText("X");
        }else
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp3.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp3MouseClicked

    private void txtAp4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp4MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp4.getText().equals("") && evt.getClickCount()==1){
           txtAp4.setText("X");
        }else
        if(txtAp4.getText().equals("X") && evt.getClickCount()==1){
           txtAp4.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp4.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp4MouseClicked

    private void txtAp5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp5MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp5.getText().equals("") && evt.getClickCount()==1){
           txtAp5.setText("X");
        }else
        if(txtAp5.getText().equals("X") && evt.getClickCount()==1){
           txtAp5.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp5.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp5MouseClicked

    private void txtAp6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp6MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp6.getText().equals("") && evt.getClickCount()==1){
           txtAp6.setText("X");
        }else
        if(txtAp6.getText().equals("X") && evt.getClickCount()==1){
           txtAp6.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp6.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp6MouseClicked

    private void txtAp7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp7MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp7.getText().equals("") && evt.getClickCount()==1){
           txtAp7.setText("X");
        }else
        if(txtAp7.getText().equals("X") && evt.getClickCount()==1){
           txtAp7.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp7.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp7MouseClicked

    private void txtAp8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp8MouseClicked
       if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp8.getText().equals("") && evt.getClickCount()==1){
           txtAp8.setText("X");
 
        }else
        if(txtAp8.getText().equals("X") && evt.getClickCount()==1){
           txtAp8.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp8.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp8MouseClicked

    private void txtAp9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp9MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp9.getText().equals("") && evt.getClickCount()==1){
           txtAp9.setText("X");

        }else
        if(txtAp9.getText().equals("X") && evt.getClickCount()==1){
           txtAp9.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp9.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp9MouseClicked

    private void txtAp10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp10MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp10.getText().equals("") && evt.getClickCount()==1){
           txtAp10.setText("X");
 
        }else
        if(txtAp10.getText().equals("X") && evt.getClickCount()==1){
           txtAp10.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp10.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp10MouseClicked

    private void txtAp11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp11MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp11.getText().equals("") && evt.getClickCount()==1){
           txtAp11.setText("X");

        }else
        if(txtAp11.getText().equals("X") && evt.getClickCount()==1){
           txtAp11.setText(""); 
  
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp11.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp11MouseClicked

    private void txtAp12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp12MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp12.getText().equals("") && evt.getClickCount()==1){
           txtAp12.setText("X");
 
        }else
        if(txtAp12.getText().equals("X") && evt.getClickCount()==1){
           txtAp12.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp12.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp12MouseClicked

    private void txtAp13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp13MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp13.getText().equals("") && evt.getClickCount()==1){
           txtAp13.setText("X");

        }else
        if(txtAp13.getText().equals("X") && evt.getClickCount()==1){
           txtAp13.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp13.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp13MouseClicked

    private void txtAp14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp14MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp14.getText().equals("") && evt.getClickCount()==1){
           txtAp14.setText("X");
 
        }else
        if(txtAp14.getText().equals("X") && evt.getClickCount()==1){
           txtAp14.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp14.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp14MouseClicked

    private void txtAp15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp15MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp15.getText().equals("") && evt.getClickCount()==1){
           txtAp15.setText("X");
 
        }else
        if(txtAp15.getText().equals("X") && evt.getClickCount()==1){
           txtAp15.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp15.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp15MouseClicked

    private void txtAp16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp16MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp16.getText().equals("") && evt.getClickCount()==1){
           txtAp16.setText("X");

        }else
        if(txtAp16.getText().equals("X") && evt.getClickCount()==1){
           txtAp16.setText(""); 

        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp16.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp16MouseClicked

    private void txtAp17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp17MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp17.getText().equals("") && evt.getClickCount()==1){
           txtAp17.setText("X");
 
        }else
        if(txtAp17.getText().equals("X") && evt.getClickCount()==1){
           txtAp17.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp17.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp17MouseClicked

    private void txtAp18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp18MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp18.getText().equals("") && evt.getClickCount()==1){
           txtAp18.setText("X");

        }else
        if(txtAp18.getText().equals("X") && evt.getClickCount()==1){
           txtAp18.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp18.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp18MouseClicked

    private void txtAp19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp19MouseClicked
       if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp19.getText().equals("") && evt.getClickCount()==1){
           txtAp19.setText("X");
        }else
        if(txtAp19.getText().equals("X") && evt.getClickCount()==1){
           txtAp19.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp19.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp19MouseClicked

    private void txtAp20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp20MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp20.getText().equals("") && evt.getClickCount()==1){
           txtAp20.setText("X");
        }else
        if(txtAp20.getText().equals("X") && evt.getClickCount()==1){
           txtAp20.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp20.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp20MouseClicked

    private void txtAp21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp21MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp21.getText().equals("") && evt.getClickCount()==1){
           txtAp21.setText("X");
        }else
        if(txtAp21.getText().equals("X") && evt.getClickCount()==1){
           txtAp21.setText(""); 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp21.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp21MouseClicked

    private void txtAp22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp22MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp22.getText().equals("") && evt.getClickCount()==1){
           txtAp22.setText("X");
        }else
        if(txtAp22.getText().equals("X") && evt.getClickCount()==1){
           txtAp22.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp22.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp22MouseClicked

    private void txtAp23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp23MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp23.getText().equals("") && evt.getClickCount()==1){
           txtAp23.setText("X");
        }else
        if(txtAp23.getText().equals("X") && evt.getClickCount()==1){
           txtAp23.setText(""); 

        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp23.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp23MouseClicked

    private void txtAp24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp24MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp24.getText().equals("") && evt.getClickCount()==1){
           txtAp24.setText("X");
 
        }else
        if(txtAp24.getText().equals("X") && evt.getClickCount()==1){
           txtAp24.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp24.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp24MouseClicked

    private void txtAp25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp25MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp25.getText().equals("") && evt.getClickCount()==1){
           txtAp25.setText("X");

        }else
        if(txtAp25.getText().equals("X") && evt.getClickCount()==1){
           txtAp25.setText(""); 

        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp25.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp25MouseClicked

    private void txtAp26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp26MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp26.getText().equals("") && evt.getClickCount()==1){
           txtAp26.setText("X");
 
        }else
        if(txtAp26.getText().equals("X") && evt.getClickCount()==1){
           txtAp26.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp26.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp26MouseClicked

    private void txtAp27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp27MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp27.getText().equals("") && evt.getClickCount()==1){
           txtAp27.setText("X");
 
        }else
        if(txtAp27.getText().equals("X") && evt.getClickCount()==1){
           txtAp27.setText(""); 
 
        }
        if(txtAp1.getText().equals("X") && evt.getClickCount()==1){
           txtAp27.setText(""); 
        }
        }
    }//GEN-LAST:event_txtAp27MouseClicked

    private void txtAp28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAp28MouseClicked
        if (lblIdAP.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtAp28.getText().equals("") && evt.getClickCount()==1){
           txtAp28.setText("X");
           txtOtros.setEditable(true);
           txtOtros.requestFocus();
 
        }else
        if(txtAp28.getText().equals("X") && evt.getClickCount()==1){
           txtAp28.setText(""); 
           txtOtros.setEditable(false);
           txtOtros.setText("");
        }
        }
    }//GEN-LAST:event_txtAp28MouseClicked

    private void txtOtrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyReleased
        txtOtros.setText(txtOtros.getText().toUpperCase());

    }//GEN-LAST:event_txtOtrosKeyReleased

    private void txtOtrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtrosKeyTyped

    }//GEN-LAST:event_txtOtrosKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdAP;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblMadre;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtAp1;
    public static javax.swing.JTextField txtAp10;
    public static javax.swing.JTextField txtAp11;
    public static javax.swing.JTextField txtAp12;
    public static javax.swing.JTextField txtAp13;
    public static javax.swing.JTextField txtAp14;
    public static javax.swing.JTextField txtAp15;
    public static javax.swing.JTextField txtAp16;
    public static javax.swing.JTextField txtAp17;
    public static javax.swing.JTextField txtAp18;
    public static javax.swing.JTextField txtAp19;
    public static javax.swing.JTextField txtAp2;
    public static javax.swing.JTextField txtAp20;
    public static javax.swing.JTextField txtAp21;
    public static javax.swing.JTextField txtAp22;
    public static javax.swing.JTextField txtAp23;
    public static javax.swing.JTextField txtAp24;
    public static javax.swing.JTextField txtAp25;
    public static javax.swing.JTextField txtAp26;
    public static javax.swing.JTextField txtAp27;
    public static javax.swing.JTextField txtAp28;
    public static javax.swing.JTextField txtAp3;
    public static javax.swing.JTextField txtAp4;
    public static javax.swing.JTextField txtAp5;
    public static javax.swing.JTextField txtAp6;
    public static javax.swing.JTextField txtAp7;
    public static javax.swing.JTextField txtAp8;
    public static javax.swing.JTextField txtAp9;
    public static javax.swing.JEditorPane txtOtros;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
