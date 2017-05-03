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
import java.util.Locale;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalAO;
import static vista.ConsultorioEx.RSAIVacunas.txtFuaAmaDu;
import static vista.ConsultorioEx.RSAIVacunas.txtFuaApoR1;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoAO extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoAO() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        mensaje.setVisible(false);
        btneditar.setEnabled(false);

        LimitadorDeDocumento limitGestas = new LimitadorDeDocumento(2);
        txtGestas.setDocument(limitGestas);
        
        LimitadorDeDocumento limitAborto = new LimitadorDeDocumento(2);
        txtAborto.setDocument(limitAborto);
        
        LimitadorDeDocumento limitPartos = new LimitadorDeDocumento(2);
        txtPartos.setDocument(limitPartos);
        
        LimitadorDeDocumento limitV = new LimitadorDeDocumento(2);
        txtVaginales.setDocument(limitV);
        
        LimitadorDeDocumento limitCesa = new LimitadorDeDocumento(2);
        txtCesareas.setDocument(limitCesa);
        
        LimitadorDeDocumento limitNacidos = new LimitadorDeDocumento(2);
        txtNacidos.setDocument(limitNacidos);
        
        LimitadorDeDocumento limitRN = new LimitadorDeDocumento(2);
        txtRN.setDocument(limitRN);
        
        LimitadorDeDocumento limitViven = new LimitadorDeDocumento(2);
        txtViven.setDocument(limitViven);
        
        LimitadorDeDocumento limitMuerto = new LimitadorDeDocumento(2);
        txtMuerto1.setDocument(limitMuerto);
        
        LimitadorDeDocumento limitDespues = new LimitadorDeDocumento(2);
        txtDespues.setDocument(limitDespues);
        
        LimitadorDeDocumento limitMayor = new LimitadorDeDocumento(4);
        txtRNmayor.setDocument(limitMayor);
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
 

  public void habilitarDatos(boolean opcion){
        txtGestas.setEditable(opcion);
        txtAborto.setEditable(opcion);
        txtPartos.setEditable(opcion);
        txtVaginales.setEditable(opcion);
        txtCesareas.setEditable(opcion);
        txtRN.setEditable(opcion);
        txtNacidos.setEditable(opcion);
        txtViven.setEditable(opcion);
        txtMuerto1.setEditable(opcion);
        txtDespues.setEditable(opcion);
        txtRNmayor.setEditable(opcion);
  }
 public void Guardar(){
        
    ConsultorioExtCarnetPerinatalAO CXRsAO= new ConsultorioExtCarnetPerinatalAO();
    ConsultorioExtCarnetPerinatalAO CXRsAO2 = new ConsultorioExtCarnetPerinatalAO();
    try {
          if(lblMant.getText().equals("U"))
            CXRsAO.setAO_ID(0);

            CXRsAO.setCP_ID(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            CXRsAO.setAO_GESTAS(txtGestas.getText());
            CXRsAO.setAO_ABORTOS(txtAborto.getText());
            CXRsAO.setAO_VAGINALES(txtVaginales.getText());
            CXRsAO.setAO_NAC_VIVOS(txtRN.getText());
            CXRsAO.setAO_VIVEN(txtViven.getText());
            CXRsAO.setAO_PARTOS(txtPartos.getText());
            CXRsAO.setAO_CESAREAS(txtCesareas.getText());
            CXRsAO.setAO_NAC_MUERTOS(txtNacidos.getText());
            CXRsAO.setAO_MUERTO_P_SEM(txtMuerto1.getText());
            CXRsAO.setAO_MUERTO_D_PSEM(txtDespues.getText());
            CXRsAO.setAO_PARTO_0(chk1.getText());
            CXRsAO.setAO_PARTO_2500(chk2.getText());
            CXRsAO.setAO_PARTO_MULT(chk3.getText());
            CXRsAO.setAO_PARTO_37_SEM(chk4.getText());
            CXRsAO.setAO_RN_MAYOR_PESO(txtRNmayor.getText());
            CXRsAO.setCOD_USU(lblusu.getText());//falta 

            
                if(CXRsAO.mantenimientoConsultorioExtAO("I")==true){
                    mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Guardados de forma correcta");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
             
                    tge=1;
                    CXRsAO.ConsultoriosExtAOListar(RegistroEmbarazoPrincipal.lblId.getText());  

                    habilitarDatos(false);
                }else {

                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
                }  
             } catch (Exception e) {
                System.out.println("Error: guardar " + e.getMessage());
            }
  
    }
 
  public void Modificar(){
        
    ConsultorioExtCarnetPerinatalAO CXRsAO= new ConsultorioExtCarnetPerinatalAO();
    ConsultorioExtCarnetPerinatalAO CXRsAO2 = new ConsultorioExtCarnetPerinatalAO();
    try {
  
            CXRsAO.setAO_ID(Integer.parseInt(lblIdAO.getText()));

            CXRsAO.setCP_ID(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            CXRsAO.setAO_GESTAS(txtGestas.getText());
            CXRsAO.setAO_ABORTOS(txtAborto.getText());
            CXRsAO.setAO_VAGINALES(txtVaginales.getText());
            CXRsAO.setAO_NAC_VIVOS(txtRN.getText());
            CXRsAO.setAO_VIVEN(txtViven.getText());
            CXRsAO.setAO_PARTOS(txtPartos.getText());
            CXRsAO.setAO_CESAREAS(txtCesareas.getText());
            CXRsAO.setAO_NAC_MUERTOS(txtNacidos.getText());
            CXRsAO.setAO_MUERTO_P_SEM(txtMuerto1.getText());
            CXRsAO.setAO_MUERTO_D_PSEM(txtDespues.getText());
            CXRsAO.setAO_PARTO_0(chk1.getText());
            CXRsAO.setAO_PARTO_2500(chk2.getText());
            CXRsAO.setAO_PARTO_MULT(chk3.getText());
            CXRsAO.setAO_PARTO_37_SEM(chk4.getText());
            CXRsAO.setAO_RN_MAYOR_PESO(txtRNmayor.getText());
            CXRsAO.setCOD_USU(lblusu.getText());//falta 

            
                if(CXRsAO.mantenimientoConsultorioExtAO("U")==true){
                    mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Datos Actualizados de forma correcta");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
             
                    tge=1;
                    CXRsAO.ConsultoriosExtAOListar(RegistroEmbarazoPrincipal.lblId.getText());  

                    habilitarDatos(false);
                }else {

                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=5;
                }  
             } catch (Exception e) {
                System.out.println("Error: guardar " + e.getMessage());
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtGestas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtAborto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPartos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtCesareas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtVaginales = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtNacidos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        chk3 = new javax.swing.JTextField();
        chk2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chk4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        chk1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        txtRN = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtViven = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        txtDespues = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtMuerto1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtRNmayor = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        lblIdAO = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        ChkAnalf1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        ChkEdad1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblMant = new javax.swing.JLabel();
        lbMadreAO = new javax.swing.JLabel();
        var = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtGestas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtGestas.setForeground(new java.awt.Color(102, 102, 102));
        txtGestas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGestas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtGestas.setPreferredSize(new java.awt.Dimension(28, 28));
        txtGestas.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtGestas.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtGestas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGestasCaretUpdate(evt);
            }
        });
        txtGestas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGestasKeyReleased(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestas");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(txtGestas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtGestas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtAborto.setBackground(new java.awt.Color(255, 204, 51));
        txtAborto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtAborto.setForeground(new java.awt.Color(102, 102, 102));
        txtAborto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAborto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtAborto.setPreferredSize(new java.awt.Dimension(28, 28));
        txtAborto.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtAborto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtAbortoCaretUpdate(evt);
            }
        });
        txtAborto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbortoKeyReleased(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Abortos");

        txtPartos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtPartos.setForeground(new java.awt.Color(102, 102, 102));
        txtPartos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPartos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPartos.setPreferredSize(new java.awt.Dimension(28, 28));
        txtPartos.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtPartos.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtPartos.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPartosCaretUpdate(evt);
            }
        });
        txtPartos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPartosKeyReleased(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Partos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(txtAborto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPartos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtAborto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(110, 110, 110)
                .addComponent(txtPartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(23, 23, 23))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        txtCesareas.setBackground(new java.awt.Color(255, 51, 51));
        txtCesareas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCesareas.setForeground(new java.awt.Color(255, 255, 255));
        txtCesareas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCesareas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCesareas.setPreferredSize(new java.awt.Dimension(28, 28));
        txtCesareas.setSelectionColor(new java.awt.Color(255, 51, 51));
        txtCesareas.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCesareasCaretUpdate(evt);
            }
        });
        txtCesareas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCesareasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCesareasKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cesáreas");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCesareas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtCesareas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        txtVaginales.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtVaginales.setForeground(new java.awt.Color(102, 102, 102));
        txtVaginales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVaginales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtVaginales.setPreferredSize(new java.awt.Dimension(28, 28));
        txtVaginales.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtVaginales.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtVaginales.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVaginalesCaretUpdate(evt);
            }
        });
        txtVaginales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVaginalesActionPerformed(evt);
            }
        });
        txtVaginales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVaginalesKeyReleased(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Vaginales");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVaginales, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtVaginales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(56, 56));

        txtNacidos.setBackground(new java.awt.Color(255, 204, 51));
        txtNacidos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNacidos.setForeground(new java.awt.Color(102, 102, 102));
        txtNacidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNacidos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtNacidos.setPreferredSize(new java.awt.Dimension(28, 28));
        txtNacidos.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtNacidos.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNacidosCaretUpdate(evt);
            }
        });
        txtNacidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNacidosKeyReleased(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("<html>Nacidos<br>Muertos</br></span></html>");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtNacidos, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(txtNacidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/lineas.PNG"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/ele.PNG"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/corchete.png"))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setText("< 37 sem.");

        chk3.setEditable(false);
        chk3.setBackground(new java.awt.Color(255, 204, 51));
        chk3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chk3.setForeground(new java.awt.Color(102, 102, 102));
        chk3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chk3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chk3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chk3.setPreferredSize(new java.awt.Dimension(18, 18));
        chk3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chk3CaretUpdate(evt);
            }
        });
        chk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chk3MouseClicked(evt);
            }
        });

        chk2.setEditable(false);
        chk2.setBackground(new java.awt.Color(255, 204, 51));
        chk2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chk2.setForeground(new java.awt.Color(102, 102, 102));
        chk2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chk2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chk2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chk2.setPreferredSize(new java.awt.Dimension(18, 18));
        chk2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chk2CaretUpdate(evt);
            }
        });
        chk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chk2MouseClicked(evt);
            }
        });

        jLabel6.setText("0 ó + 3");

        chk4.setEditable(false);
        chk4.setBackground(new java.awt.Color(255, 204, 51));
        chk4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chk4.setForeground(new java.awt.Color(102, 102, 102));
        chk4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chk4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chk4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chk4.setPreferredSize(new java.awt.Dimension(18, 18));
        chk4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chk4CaretUpdate(evt);
            }
        });
        chk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chk4MouseClicked(evt);
            }
        });

        jLabel8.setText("< 2500 g");

        chk1.setEditable(false);
        chk1.setBackground(new java.awt.Color(255, 204, 51));
        chk1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chk1.setForeground(new java.awt.Color(102, 102, 102));
        chk1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chk1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chk1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chk1.setPreferredSize(new java.awt.Dimension(18, 18));
        chk1.setSelectionColor(new java.awt.Color(255, 204, 51));
        chk1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chk1CaretUpdate(evt);
            }
        });
        chk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chk1MouseClicked(evt);
            }
        });

        jLabel13.setText("Multiple");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(chk4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chk2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chk3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        txtRN.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRN.setForeground(new java.awt.Color(102, 102, 102));
        txtRN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRN.setPreferredSize(new java.awt.Dimension(28, 28));
        txtRN.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtRN.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtRN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRNCaretUpdate(evt);
            }
        });
        txtRN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRNKeyReleased(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Vivos");

        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Nacidos");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(txtRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addGap(0, 44, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        txtViven.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtViven.setForeground(new java.awt.Color(102, 102, 102));
        txtViven.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtViven.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtViven.setPreferredSize(new java.awt.Dimension(28, 28));
        txtViven.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtViven.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtViven.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtVivenCaretUpdate(evt);
            }
        });
        txtViven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVivenActionPerformed(evt);
            }
        });
        txtViven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVivenKeyReleased(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Viven");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtViven, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(txtViven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        txtDespues.setBackground(new java.awt.Color(255, 204, 51));
        txtDespues.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDespues.setForeground(new java.awt.Color(102, 102, 102));
        txtDespues.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDespues.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtDespues.setPreferredSize(new java.awt.Dimension(28, 28));
        txtDespues.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtDespues.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDespuesCaretUpdate(evt);
            }
        });
        txtDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDespuesActionPerformed(evt);
            }
        });
        txtDespues.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDespuesKeyReleased(evt);
            }
        });

        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("<html>Después - 1 ra.<br>semana</br></span></html>");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(txtDespues, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(txtDespues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        txtMuerto1.setBackground(new java.awt.Color(255, 204, 51));
        txtMuerto1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMuerto1.setForeground(new java.awt.Color(102, 102, 102));
        txtMuerto1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMuerto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMuerto1.setPreferredSize(new java.awt.Dimension(28, 28));
        txtMuerto1.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtMuerto1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMuerto1CaretUpdate(evt);
            }
        });
        txtMuerto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMuerto1ActionPerformed(evt);
            }
        });
        txtMuerto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMuerto1KeyReleased(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("<html>Muerto - 1ra.<br>semana</br></span></html>");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(txtMuerto1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(txtMuerto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/tres333.PNG"))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("RN de mayor peso:");

        txtRNmayor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtRNmayor.setForeground(new java.awt.Color(102, 102, 102));
        txtRNmayor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRNmayor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtRNmayor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtRNmayorCaretUpdate(evt);
            }
        });
        txtRNmayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRNmayorKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("g");

        lblIdAO.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel20)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(txtRNmayor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblIdAO, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel20)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(46, 46, 46)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addGap(16, 16, 16))
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(87, 87, 87)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtRNmayor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))))
                .addGap(18, 18, 18)
                .addComponent(lblIdAO, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addContainerGap(737, Short.MAX_VALUE))
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

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("<html>Antecedentes <br>Obstétricos</html>");

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

        lblMant.setForeground(new java.awt.Color(51, 51, 51));
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37))))
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblMant)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(lblMant)
                .addGap(51, 51, 51)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(90, 90, 90)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(249, 249, 249))
        );

        lbMadreAO.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lbMadreAO.setForeground(new java.awt.Color(12, 97, 81));
        lbMadreAO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lbMadreAO.setText("Martha Arias Torres");
        lbMadreAO.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMadreAO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        var.setText("1");

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbMadreAO, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbMadreAO)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtGestasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGestasCaretUpdate
       
    }//GEN-LAST:event_txtGestasCaretUpdate

    private void txtAbortoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAbortoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbortoCaretUpdate

    private void txtVaginalesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVaginalesCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVaginalesCaretUpdate

    private void txtRNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRNCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRNCaretUpdate

    private void txtCesareasCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCesareasCaretUpdate
   
       
    }//GEN-LAST:event_txtCesareasCaretUpdate

    private void txtPartosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPartosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPartosCaretUpdate

    private void chk4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chk4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chk4CaretUpdate

    private void chk1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chk1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chk1CaretUpdate

    private void chk2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chk2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chk2CaretUpdate

    private void chk3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chk3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chk3CaretUpdate

    private void txtNacidosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNacidosCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacidosCaretUpdate

    private void txtVaginalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVaginalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVaginalesActionPerformed

    private void txtVivenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtVivenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVivenCaretUpdate

    private void txtVivenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVivenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVivenActionPerformed

    private void txtMuerto1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMuerto1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMuerto1CaretUpdate

    private void txtMuerto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMuerto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMuerto1ActionPerformed

    private void txtDespuesCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDespuesCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDespuesCaretUpdate

    private void txtDespuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDespuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDespuesActionPerformed

    private void txtRNmayorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtRNmayorCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRNmayorCaretUpdate

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel32MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
     btnGuardar.setEnabled(true);
     habilitarDatos(true);
     btneditar.setEnabled(false);
     tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
         if (tge==3 || tge==1){
   mensaje.setVisible(false);  

   }
        
        if (tge==2){
        Modificar();

        btneditar.setEnabled(false);

   }  
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed
       ConsultorioExtCarnetPerinatalAO AO1 = new ConsultorioExtCarnetPerinatalAO();
        AO1.ConsultoriosExtAOListar("2");     
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

    private void chk1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chk1MouseClicked
        if (lblIdAO.getText().equals("")){
        if(chk1.getText().equals("") && evt.getClickCount()==1){
           chk1.setText("X");
        }else
        if(chk1.getText().equals("X") && evt.getClickCount()==1){
           chk1.setText(""); 
        }
        }
    }//GEN-LAST:event_chk1MouseClicked

    private void chk2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chk2MouseClicked
if (lblIdAO.getText().equals("")){
        if(chk2.getText().equals("") && evt.getClickCount()==1){
           chk2.setText("X");
        }else
        if(chk2.getText().equals("X") && evt.getClickCount()==1){
           chk2.setText(""); 
        }
}
    }//GEN-LAST:event_chk2MouseClicked

    private void chk3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chk3MouseClicked
        if (lblIdAO.getText().equals("")){
        if(chk3.getText().equals("") && evt.getClickCount()==1){
           chk3.setText("X");
        }else
        if(chk3.getText().equals("X") && evt.getClickCount()==1){
           chk3.setText(""); 
        }
        }
    }//GEN-LAST:event_chk3MouseClicked

    private void chk4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chk4MouseClicked
        if (lblIdAO.getText().equals("")){
        if(chk4.getText().equals("") && evt.getClickCount()==1){
           chk4.setText("X");
        }else
        if(chk4.getText().equals("X") && evt.getClickCount()==1){
           chk4.setText(""); 
        }
        }
    }//GEN-LAST:event_chk4MouseClicked

    private void txtCesareasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCesareasKeyReleased
      txtCesareas.setText(txtCesareas.getText().toUpperCase());
    }//GEN-LAST:event_txtCesareasKeyReleased

    private void txtCesareasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCesareasKeyTyped
      
    }//GEN-LAST:event_txtCesareasKeyTyped

    private void txtGestasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGestasKeyReleased
        txtGestas.setText(txtGestas.getText().toUpperCase());
    }//GEN-LAST:event_txtGestasKeyReleased

    private void txtAbortoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbortoKeyReleased
        txtAborto.setText(txtAborto.getText().toUpperCase());
    }//GEN-LAST:event_txtAbortoKeyReleased

    private void txtPartosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPartosKeyReleased
        txtPartos.setText(txtPartos.getText().toUpperCase());
    }//GEN-LAST:event_txtPartosKeyReleased

    private void txtVaginalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVaginalesKeyReleased
        txtVaginales.setText(txtVaginales.getText().toUpperCase());
    }//GEN-LAST:event_txtVaginalesKeyReleased

    private void txtRNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRNKeyReleased
        txtRN.setText(txtRN.getText().toUpperCase());
    }//GEN-LAST:event_txtRNKeyReleased

    private void txtNacidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacidosKeyReleased
        txtNacidos.setText(txtNacidos.getText().toUpperCase());
    }//GEN-LAST:event_txtNacidosKeyReleased

    private void txtVivenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVivenKeyReleased
        txtViven.setText(txtViven.getText().toUpperCase());
    }//GEN-LAST:event_txtVivenKeyReleased

    private void txtMuerto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMuerto1KeyReleased
        txtMuerto1.setText(txtMuerto1.getText().toUpperCase());
    }//GEN-LAST:event_txtMuerto1KeyReleased

    private void txtDespuesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDespuesKeyReleased
        txtDespues.setText(txtDespues.getText().toUpperCase());
    }//GEN-LAST:event_txtDespuesKeyReleased

    private void txtRNmayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRNmayorKeyTyped
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_txtRNmayorKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chk1;
    public static javax.swing.JTextField chk2;
    public static javax.swing.JTextField chk3;
    public static javax.swing.JTextField chk4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JLabel lbMadreAO;
    public static javax.swing.JLabel lblIdAO;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtAborto;
    public static javax.swing.JTextField txtCesareas;
    public static javax.swing.JTextField txtDespues;
    public static javax.swing.JTextField txtGestas;
    public static javax.swing.JTextField txtMuerto1;
    public static javax.swing.JTextField txtNacidos;
    public static javax.swing.JTextField txtPartos;
    public static javax.swing.JTextField txtRN;
    public static javax.swing.JTextField txtRNmayor;
    public static javax.swing.JTextField txtVaginales;
    public static javax.swing.JTextField txtViven;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
