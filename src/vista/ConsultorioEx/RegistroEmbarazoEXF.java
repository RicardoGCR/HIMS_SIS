/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalEf;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import static vista.ConsultorioEx.RegistroEmbarazoPT_A_TS_F_D_FUM_H_E_V.lblIdActoMedico;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoEXF extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoEXF() {
        initComponents();
        QuitarLaBarraTitulo();
        this.getContentPane().setBackground(Color.WHITE);
        pnlMensaje.setVisible(false);
        lblCpId.setVisible(false);
        lblMant.setVisible(false);
        lblId.setVisible(false);
        lblIdActoMedico.setVisible(false);
    }
    public void QuitarLaBarraTitulo(){ 
           Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
           DimensionBarra = Barra.getPreferredSize(); 
           Barra.setSize(0,0); 
           Barra.setPreferredSize(new Dimension(0,0)); 
           repaint(); 
       }
    
    public boolean mantenimientoEF(){
        boolean retorna = false;
        try {
            ConsultorioExtCarnetPerinatalEf consultorio1 = new ConsultorioExtCarnetPerinatalEf();
            AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("U") || lblMant.getText().equals("E"))
                consultorio1.setEfId(Integer.parseInt(lblId.getText()));
            consultorio1.setCpId(Integer.parseInt(lblCpId.getText()));
            //CLINICO
            if(txtClinicoNormal.getText().equals("X"))
                consultorio1.setEfEc("Normal");
            if(txtClinicoPatologico.getText().equals("X"))
                consultorio1.setEfEc("Patologico");
            if(txtClinicoSinExamen.getText().equals("X"))
                consultorio1.setEfEc("Sin examen");
            //MAMAS
            if(txtMamasNormal.getText().equals("X"))
                consultorio1.setEfMamas("Normal");
            if(txtMamasPatologico.getText().equals("X"))
                consultorio1.setEfMamas("Patologico");
            if(txtMamasSinExamen.getText().equals("X"))
                consultorio1.setEfMamas("Sin examen");
            // CUELLO UTERINO
            if(txtCuNormal.getText().equals("X"))
                consultorio1.setEfCueUte("Normal");
            if(txtCuPatologico.getText().equals("X"))
                consultorio1.setEfCueUte("Patologico");
            if(txtCUSinExamen.getText().equals("X"))
                consultorio1.setEfCueUte("Sin examen");
            //PELVIS
            if(txtPelvisNormal.getText().equals("X"))
                consultorio1.setEfPelvis("Normal");
            if(txtPelvisPatologico.getText().equals("X"))
                consultorio1.setEfPelvis("Patologico");
            if(txtPelvisSinExamen.getText().equals("X"))
                consultorio1.setEfPelvis("Sin examen");
            //ODONTOLOGICO
            if(txtOdontNormal.getText().equals("X"))
                consultorio1.setEfOdont("Normal");
            if(txtOdontPatologico.getText().equals("X"))
                consultorio1.setEfOdont("Patologico");
            if(txtOdontSinExamen.getText().equals("X"))
                consultorio1.setEfOdont("Sin examen");
            consultorio1.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            consultorio1.setIdActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
            if(consultorio1.mantenimientoConsultorioExtCarnetPerinatalEf(lblMant.getText())==true){
                if(lblMant.getText().equals("I")){
                    lblId.setText(consultorio1.perinatalEfID());
                }
                lblMant.setText("");
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
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMadre = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtOdontSinExamen = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        txtCuPatologico = new javax.swing.JTextField();
        txtOdontNormal = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        txtMamasPatologico = new javax.swing.JTextField();
        txtMamasNormal = new javax.swing.JTextField();
        txtCuNormal = new javax.swing.JTextField();
        txtMamasSinExamen = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtClinicoPatologico = new javax.swing.JTextField();
        txtClinicoNormal = new javax.swing.JTextField();
        txtPelvisSinExamen = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPelvisNormal = new javax.swing.JTextField();
        txtClinicoSinExamen = new javax.swing.JTextField();
        txtPelvisPatologico = new javax.swing.JTextField();
        txtCUSinExamen = new javax.swing.JTextField();
        txtOdontPatologico = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnCaccnelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        ChkAnalf2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ChkEdad2 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        lblCpId = new javax.swing.JLabel();
        pnlMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        lblMant = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        lblMadre.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(12, 97, 81));
        lblMadre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtOdontSinExamen.setEditable(false);
        txtOdontSinExamen.setBackground(new java.awt.Color(255, 255, 255));
        txtOdontSinExamen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtOdontSinExamen.setForeground(new java.awt.Color(102, 102, 102));
        txtOdontSinExamen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOdontSinExamen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtOdontSinExamen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtOdontSinExamen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtOdontSinExamen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtOdontSinExamenCaretUpdate(evt);
            }
        });
        txtOdontSinExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOdontSinExamenMouseClicked(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Sin Examen");

        txtCuPatologico.setEditable(false);
        txtCuPatologico.setBackground(new java.awt.Color(255, 255, 255));
        txtCuPatologico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCuPatologico.setForeground(new java.awt.Color(102, 102, 102));
        txtCuPatologico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuPatologico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCuPatologico.setPreferredSize(new java.awt.Dimension(18, 18));
        txtCuPatologico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCuPatologicoCaretUpdate(evt);
            }
        });
        txtCuPatologico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCuPatologicoMouseClicked(evt);
            }
        });
        txtCuPatologico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuPatologicoKeyPressed(evt);
            }
        });

        txtOdontNormal.setEditable(false);
        txtOdontNormal.setBackground(new java.awt.Color(255, 204, 51));
        txtOdontNormal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtOdontNormal.setForeground(new java.awt.Color(102, 102, 102));
        txtOdontNormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOdontNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtOdontNormal.setPreferredSize(new java.awt.Dimension(18, 18));
        txtOdontNormal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtOdontNormalCaretUpdate(evt);
            }
        });
        txtOdontNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOdontNormalMouseClicked(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Pelvis");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Cuello Uterino");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Normal");

        txtMamasPatologico.setEditable(false);
        txtMamasPatologico.setBackground(new java.awt.Color(255, 255, 255));
        txtMamasPatologico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMamasPatologico.setForeground(new java.awt.Color(102, 102, 102));
        txtMamasPatologico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMamasPatologico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMamasPatologico.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMamasPatologico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMamasPatologicoCaretUpdate(evt);
            }
        });
        txtMamasPatologico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMamasPatologicoMouseClicked(evt);
            }
        });

        txtMamasNormal.setEditable(false);
        txtMamasNormal.setBackground(new java.awt.Color(255, 204, 51));
        txtMamasNormal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMamasNormal.setForeground(new java.awt.Color(102, 102, 102));
        txtMamasNormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMamasNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMamasNormal.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMamasNormal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMamasNormalCaretUpdate(evt);
            }
        });
        txtMamasNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMamasNormalMouseClicked(evt);
            }
        });

        txtCuNormal.setEditable(false);
        txtCuNormal.setBackground(new java.awt.Color(255, 204, 51));
        txtCuNormal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCuNormal.setForeground(new java.awt.Color(102, 102, 102));
        txtCuNormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCuNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCuNormal.setPreferredSize(new java.awt.Dimension(18, 18));
        txtCuNormal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCuNormalCaretUpdate(evt);
            }
        });
        txtCuNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCuNormalMouseClicked(evt);
            }
        });

        txtMamasSinExamen.setEditable(false);
        txtMamasSinExamen.setBackground(new java.awt.Color(255, 255, 255));
        txtMamasSinExamen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtMamasSinExamen.setForeground(new java.awt.Color(102, 102, 102));
        txtMamasSinExamen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMamasSinExamen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtMamasSinExamen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtMamasSinExamen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtMamasSinExamen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMamasSinExamenCaretUpdate(evt);
            }
        });
        txtMamasSinExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMamasSinExamenMouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Mamas");

        txtClinicoPatologico.setEditable(false);
        txtClinicoPatologico.setBackground(new java.awt.Color(255, 255, 255));
        txtClinicoPatologico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtClinicoPatologico.setForeground(new java.awt.Color(102, 102, 102));
        txtClinicoPatologico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClinicoPatologico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtClinicoPatologico.setPreferredSize(new java.awt.Dimension(18, 18));
        txtClinicoPatologico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtClinicoPatologicoCaretUpdate(evt);
            }
        });
        txtClinicoPatologico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClinicoPatologicoMouseClicked(evt);
            }
        });

        txtClinicoNormal.setEditable(false);
        txtClinicoNormal.setBackground(new java.awt.Color(255, 204, 51));
        txtClinicoNormal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtClinicoNormal.setForeground(new java.awt.Color(102, 102, 102));
        txtClinicoNormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClinicoNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtClinicoNormal.setPreferredSize(new java.awt.Dimension(18, 18));
        txtClinicoNormal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtClinicoNormalCaretUpdate(evt);
            }
        });
        txtClinicoNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClinicoNormalMouseClicked(evt);
            }
        });

        txtPelvisSinExamen.setEditable(false);
        txtPelvisSinExamen.setBackground(new java.awt.Color(255, 255, 255));
        txtPelvisSinExamen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPelvisSinExamen.setForeground(new java.awt.Color(102, 102, 102));
        txtPelvisSinExamen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPelvisSinExamen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPelvisSinExamen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPelvisSinExamen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtPelvisSinExamen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPelvisSinExamenCaretUpdate(evt);
            }
        });
        txtPelvisSinExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPelvisSinExamenMouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("Odontologia");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Clínico");

        txtPelvisNormal.setEditable(false);
        txtPelvisNormal.setBackground(new java.awt.Color(255, 204, 51));
        txtPelvisNormal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPelvisNormal.setForeground(new java.awt.Color(102, 102, 102));
        txtPelvisNormal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPelvisNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPelvisNormal.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPelvisNormal.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPelvisNormalCaretUpdate(evt);
            }
        });
        txtPelvisNormal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPelvisNormalMouseClicked(evt);
            }
        });

        txtClinicoSinExamen.setEditable(false);
        txtClinicoSinExamen.setBackground(new java.awt.Color(255, 255, 255));
        txtClinicoSinExamen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtClinicoSinExamen.setForeground(new java.awt.Color(102, 102, 102));
        txtClinicoSinExamen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClinicoSinExamen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtClinicoSinExamen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtClinicoSinExamen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtClinicoSinExamen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtClinicoSinExamenCaretUpdate(evt);
            }
        });
        txtClinicoSinExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtClinicoSinExamenMouseClicked(evt);
            }
        });

        txtPelvisPatologico.setEditable(false);
        txtPelvisPatologico.setBackground(new java.awt.Color(255, 255, 255));
        txtPelvisPatologico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtPelvisPatologico.setForeground(new java.awt.Color(102, 102, 102));
        txtPelvisPatologico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPelvisPatologico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtPelvisPatologico.setPreferredSize(new java.awt.Dimension(18, 18));
        txtPelvisPatologico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPelvisPatologicoCaretUpdate(evt);
            }
        });
        txtPelvisPatologico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPelvisPatologicoMouseClicked(evt);
            }
        });

        txtCUSinExamen.setEditable(false);
        txtCUSinExamen.setBackground(new java.awt.Color(255, 255, 255));
        txtCUSinExamen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtCUSinExamen.setForeground(new java.awt.Color(102, 102, 102));
        txtCUSinExamen.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCUSinExamen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtCUSinExamen.setPreferredSize(new java.awt.Dimension(18, 18));
        txtCUSinExamen.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtCUSinExamen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCUSinExamenCaretUpdate(evt);
            }
        });
        txtCUSinExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCUSinExamenMouseClicked(evt);
            }
        });

        txtOdontPatologico.setEditable(false);
        txtOdontPatologico.setBackground(new java.awt.Color(255, 255, 255));
        txtOdontPatologico.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtOdontPatologico.setForeground(new java.awt.Color(102, 102, 102));
        txtOdontPatologico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtOdontPatologico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtOdontPatologico.setPreferredSize(new java.awt.Dimension(18, 18));
        txtOdontPatologico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtOdontPatologicoCaretUpdate(evt);
            }
        });
        txtOdontPatologico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOdontPatologicoMouseClicked(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Patológico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(119, 119, 119)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCUSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMamasSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPelvisSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtOdontSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtClinicoSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMamasNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(txtMamasPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtClinicoNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtClinicoPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCuNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(txtCuPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPelvisNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(txtPelvisPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel74)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel75)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel76))
                    .addComponent(jLabel40)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(txtOdontNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(txtOdontPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jLabel76)
                    .addComponent(jLabel75))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtClinicoSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClinicoPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClinicoNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtMamasSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMamasNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMamasPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtCUSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtPelvisSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPelvisNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPelvisPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtOdontSinExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOdontNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOdontPatologico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jLabel25.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("<html>Examen Físico</html>");

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

        jPanel37.setBackground(new java.awt.Color(39, 174, 97));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(240, 240, 240));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('N');
        btnModificar.setText("Modificar");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setEnabled(false);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnCaccnelar)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel33))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel38))
                            .addComponent(lblCpId))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(lblCpId)
                .addGap(50, 50, 50)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(66, 66, 66))
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

        jPanel6.setBackground(new java.awt.Color(65, 65, 65));

        lblActoMedico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblActoMedico.setForeground(new java.awt.Color(255, 255, 255));

        lblFP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFP.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFP, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(738, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFP, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(lblActoMedico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(564, 564, 564)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1162, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblMadre)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(338, 338, 338)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(339, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtClinicoSinExamenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClinicoSinExamenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClinicoSinExamenCaretUpdate

    private void txtClinicoNormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClinicoNormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClinicoNormalCaretUpdate

    private void txtClinicoPatologicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtClinicoPatologicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClinicoPatologicoCaretUpdate

    private void txtMamasPatologicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMamasPatologicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMamasPatologicoCaretUpdate

    private void txtMamasNormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMamasNormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMamasNormalCaretUpdate

    private void txtMamasSinExamenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMamasSinExamenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMamasSinExamenCaretUpdate

    private void txtCuPatologicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCuPatologicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuPatologicoCaretUpdate

    private void txtCuNormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCuNormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuNormalCaretUpdate

    private void txtCUSinExamenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCUSinExamenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCUSinExamenCaretUpdate

    private void txtPelvisPatologicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPelvisPatologicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPelvisPatologicoCaretUpdate

    private void txtPelvisNormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPelvisNormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPelvisNormalCaretUpdate

    private void txtPelvisSinExamenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPelvisSinExamenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPelvisSinExamenCaretUpdate

    private void txtOdontPatologicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOdontPatologicoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdontPatologicoCaretUpdate

    private void txtOdontNormalCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOdontNormalCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdontNormalCaretUpdate

    private void txtOdontSinExamenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtOdontSinExamenCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOdontSinExamenCaretUpdate

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //Validar cajas 
        if(txtClinicoNormal.getText().equals("") && txtClinicoPatologico.getText().equals("") &&
           txtClinicoSinExamen.getText().equals("")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Seleccione una opcion de Examen Físico Clínico");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else 
        if(txtMamasNormal.getText().equals("") && txtMamasPatologico.getText().equals("") &&
           txtMamasSinExamen.getText().equals("")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Seleccione una opcion de Examen Físico de mamas");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else 
        if(txtCuNormal.getText().equals("") && txtCuPatologico.getText().equals("") &&
           txtCUSinExamen.getText().equals("")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Seleccione una opcion de Examen Físico patologico");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else 
        if(txtPelvisNormal.getText().equals("") && txtPelvisPatologico.getText().equals("") &&
           txtPelvisSinExamen.getText().equals("")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Seleccione una opcion de Examen Físico de pelvis");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else 
        if(txtOdontNormal.getText().equals("") && txtOdontPatologico.getText().equals("") &&
           txtOdontSinExamen.getText().equals("")){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("Seleccione una opcion de Examen Físico de odontología");
            btnSi.setVisible(false);
            btnNo.setVisible(false);
            pnlMensaje.setBackground(new Color(255,91,70));
        } else 
        if(lblMant.getText().equals("I")){
            lblMensaje.setText("¿Guardar los datos?");
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
        } else
        if(lblMant.getText().equals("U")){
            lblMensaje.setText("¿Modificar los datos?");
            pnlMensaje.setVisible(true);
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            pnlMensaje.setBackground(new Color(255,153,51));
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

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        btnGuardar.setEnabled(true);
        btnModificar.setEnabled(false);
        lblMant.setText("U");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            mantenimientoEF();
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void txtClinicoSinExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClinicoSinExamenMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtClinicoSinExamen.setText("X");
                txtClinicoNormal.setText("");
                txtClinicoPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtClinicoSinExamenMouseClicked

    private void txtClinicoNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClinicoNormalMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtClinicoSinExamen.setText("");
                txtClinicoNormal.setText("X");
                txtClinicoPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtClinicoNormalMouseClicked

    private void txtClinicoPatologicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClinicoPatologicoMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtClinicoSinExamen.setText("");
                txtClinicoNormal.setText("");
                txtClinicoPatologico.setText("X");
            }
        }
    }//GEN-LAST:event_txtClinicoPatologicoMouseClicked

    private void txtMamasSinExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMamasSinExamenMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtMamasSinExamen.setText("X");
                txtMamasNormal.setText("");
                txtMamasPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtMamasSinExamenMouseClicked

    private void txtMamasNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMamasNormalMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtMamasSinExamen.setText("");
                txtMamasNormal.setText("X");
                txtMamasPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtMamasNormalMouseClicked

    private void txtMamasPatologicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMamasPatologicoMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtMamasSinExamen.setText("");
                txtMamasNormal.setText("");
                txtMamasPatologico.setText("X");
            }
        }
    }//GEN-LAST:event_txtMamasPatologicoMouseClicked

    private void txtCUSinExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCUSinExamenMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtCUSinExamen.setText("X");
                txtCuNormal.setText("");
                txtCuPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtCUSinExamenMouseClicked

    private void txtCuNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCuNormalMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtCUSinExamen.setText("");
                txtCuNormal.setText("X");
                txtCuPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtCuNormalMouseClicked

    private void txtCuPatologicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuPatologicoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCuPatologicoKeyPressed

    private void txtCuPatologicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCuPatologicoMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtCUSinExamen.setText("");
                txtCuNormal.setText("");
                txtCuPatologico.setText("X");
            }
        }
    }//GEN-LAST:event_txtCuPatologicoMouseClicked

    private void txtPelvisSinExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPelvisSinExamenMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtPelvisSinExamen.setText("X");
                txtPelvisNormal.setText("");
                txtPelvisPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtPelvisSinExamenMouseClicked

    private void txtPelvisNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPelvisNormalMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtPelvisSinExamen.setText("");
                txtPelvisNormal.setText("X");
                txtPelvisPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtPelvisNormalMouseClicked

    private void txtPelvisPatologicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPelvisPatologicoMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtPelvisSinExamen.setText("");
                txtPelvisNormal.setText("");
                txtPelvisPatologico.setText("X");
            }
        }
    }//GEN-LAST:event_txtPelvisPatologicoMouseClicked

    private void txtOdontSinExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOdontSinExamenMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtOdontSinExamen.setText("X");
                txtOdontNormal.setText("");
                txtOdontPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtOdontSinExamenMouseClicked

    private void txtOdontNormalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOdontNormalMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtOdontSinExamen.setText("");
                txtOdontNormal.setText("X");
                txtOdontPatologico.setText("");
            }
        }
    }//GEN-LAST:event_txtOdontNormalMouseClicked

    private void txtOdontPatologicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOdontPatologicoMouseClicked
        if(lblId.getText().equals("") || lblMant.getText().equals("U")){
            if(evt.getClickCount()==1){
                txtOdontSinExamen.setText("");
                txtOdontNormal.setText("");
                txtOdontPatologico.setText("X");
            }
        }
    }//GEN-LAST:event_txtOdontPatologicoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf2;
    public static javax.swing.JTextField ChkEdad2;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel6;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblCpId;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblMadre;
    public static javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel pnlMensaje;
    public static javax.swing.JTextField txtCUSinExamen;
    public static javax.swing.JTextField txtClinicoNormal;
    public static javax.swing.JTextField txtClinicoPatologico;
    public static javax.swing.JTextField txtClinicoSinExamen;
    public static javax.swing.JTextField txtCuNormal;
    public static javax.swing.JTextField txtCuPatologico;
    public static javax.swing.JTextField txtMamasNormal;
    public static javax.swing.JTextField txtMamasPatologico;
    public static javax.swing.JTextField txtMamasSinExamen;
    public static javax.swing.JTextField txtOdontNormal;
    public static javax.swing.JTextField txtOdontPatologico;
    public static javax.swing.JTextField txtOdontSinExamen;
    public static javax.swing.JTextField txtPelvisNormal;
    public static javax.swing.JTextField txtPelvisPatologico;
    public static javax.swing.JTextField txtPelvisSinExamen;
    // End of variables declaration//GEN-END:variables
}
