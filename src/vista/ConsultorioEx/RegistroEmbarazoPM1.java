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
public class RegistroEmbarazoPM1 extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    

    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoPM1() {
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

 public void HabilitarDatos(){

//     //////////////////////////INTERGENESTICO
//     chkSi.setEnabled(true);
//     chkNo.setEnabled(true);
//     //////////////////////////TERMINACION
//     txtT1.setEnabled(true);
//     txtT2.setEnabled(true);
//     txtT3.setEnabled(true);
//     txtT4.setEnabled(true);
//     txtT5.setEnabled(true);
//     txtT6.setEnabled(true);
//     //////////////////////////TIpo de aborto
//     txtTa1.setEnabled(true);
//     txtTa2.setEnabled(true);
//     txtTa3.setEnabled(true);
//     txtTa4.setEnabled(true);
//     txtTa5.setEnabled(true);
//     //////////////////////////Lactancia
//     txtLm1.setEnabled(true);
//     txtLm2.setEnabled(true);
//     txtLm3.setEnabled(true);
//     txtLm4.setEnabled(true);
//     //////////////////////////LUGAR DE PARTO
//     txtLp1.setEnabled(true);
//     txtLp2.setEnabled(true);
//     /////////////////////////captada
//     chkCsi.setEnabled(true);
//     chkCno.setEnabled(true);
//     ////////////////////////Referida
//     chkRsi.setEnabled(true);
//     chkRno.setEditable(true);
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
        
    ConsultorioExtCarnetPerinatalReferencias CXRsR= new ConsultorioExtCarnetPerinatalReferencias();
    ConsultorioExtCarnetPerinatalReferencias CXRsR2 = new ConsultorioExtCarnetPerinatalReferencias();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    
           if(lblMant.getText().equals("U"))
            CXRsR.setIdRef(Integer.parseInt(lblIdPM1.getText()));
            CXRsR.setCP_ID(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));

            ////////////////////////////////////////////////////CONSULTA EXTERNA
            if(chkCEsi.getText().equals("X"))
                CXRsR.setCex("S");
            else
            if(chkCEno.getText().equals("X"))
                CXRsR.setCex("N");
            else
            if(chkCEna.getText().equals("X"))
                CXRsR.setCex("A");
            else
                CXRsR.setCex("");

            CXRsR.setCEXFecha(determinarFecha(fechaCE));
            CXRsR.setCEXEstable(txtEstablecimiento1.getText());
            
            //////////////////////////////////////////////////////////EMERGENCIA
            if(chkEsi.getText().equals("X"))
                CXRsR.setEme("S");
            else
            if(chkEno.getText().equals("X"))
                CXRsR.setEme("N");
            else
            if(chkEna.getText().equals("X"))
                CXRsR.setEme("A");
            else
                CXRsR.setEme("");

            CXRsR.setEMEFecha(determinarFecha(fechaE));
            CXRsR.setEMEEstable(txtEstablecimiento2.getText());
            
            ////////////////////////////////////////////////APOYO AL DIAGNOSTICO
            if(chkADsi.getText().equals("X"))
                CXRsR.setApoyo("S");
            else
            if(chkADno.getText().equals("X"))
                CXRsR.setApoyo("N");
            else
            if(chkADna.getText().equals("X"))
                CXRsR.setApoyo("A");
            else
                CXRsR.setApoyo("");

            CXRsR.setAPOYOFecha(determinarFecha(fechaAD));
            CXRsR.setAPOYOEstable(txtEstablecimiento3.getText());
            CXRsR.setACTOMEDICO(Integer.parseInt(lblIdActoMedico.getText()));
            
            
            
            

            CXRsR.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));

            
                if(CXRsR.mantenimientoConsultorioExtCarnetPerinatalREFERENCIAS(lblMant.getText())==true){
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
                    CXRsR2.ConsultoriosExtREFListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
 
                    CXRsR2.ConsultoriosExtREFListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        fechaCE = new com.toedter.calendar.JDateChooser();
        chkCEsi = new javax.swing.JTextField();
        chkCEno = new javax.swing.JTextField();
        chkCEna = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtEstablecimiento1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        chkEsi = new javax.swing.JTextField();
        chkEno = new javax.swing.JTextField();
        chkEna = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        fechaE = new com.toedter.calendar.JDateChooser();
        jLabel50 = new javax.swing.JLabel();
        txtEstablecimiento2 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtEstablecimiento3 = new javax.swing.JTextField();
        chkADsi = new javax.swing.JTextField();
        chkADno = new javax.swing.JTextField();
        chkADna = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        fechaAD = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        lblIdCp = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        ChkAnalf1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        ChkEdad1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        lblIdPM1 = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        lblIdActoMedico = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        lblActoMedico = new javax.swing.JLabel();
        lblFP = new javax.swing.JLabel();
        lblActoMedico2 = new javax.swing.JLabel();
        lblActoMedico3 = new javax.swing.JLabel();
        lblMadre = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        var.setForeground(new java.awt.Color(255, 255, 255));
        var.setText("1");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Referencias");

        fechaCE.setBackground(new java.awt.Color(255, 255, 255));
        fechaCE.setDateFormatString("dd-MM-yyyy");

        chkCEsi.setEditable(false);
        chkCEsi.setBackground(new java.awt.Color(255, 255, 255));
        chkCEsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkCEsi.setForeground(new java.awt.Color(102, 102, 102));
        chkCEsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkCEsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkCEsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCEsi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkCEsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkCEsiCaretUpdate(evt);
            }
        });
        chkCEsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCEsiMouseClicked(evt);
            }
        });

        chkCEno.setEditable(false);
        chkCEno.setBackground(new java.awt.Color(255, 204, 51));
        chkCEno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkCEno.setForeground(new java.awt.Color(102, 102, 102));
        chkCEno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkCEno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkCEno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCEno.setPreferredSize(new java.awt.Dimension(18, 18));
        chkCEno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkCEnoCaretUpdate(evt);
            }
        });
        chkCEno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCEnoMouseClicked(evt);
            }
        });

        chkCEna.setEditable(false);
        chkCEna.setBackground(new java.awt.Color(255, 255, 255));
        chkCEna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkCEna.setForeground(new java.awt.Color(102, 102, 102));
        chkCEna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkCEna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkCEna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCEna.setPreferredSize(new java.awt.Dimension(18, 18));
        chkCEna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkCEnaCaretUpdate(evt);
            }
        });
        chkCEna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCEnaMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Consulta Externa");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Fecha");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Establecimiento de traslado");

        txtEstablecimiento1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEstablecimiento1.setForeground(new java.awt.Color(102, 102, 102));
        txtEstablecimiento1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEstablecimiento1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEstablecimiento1CaretUpdate(evt);
            }
        });
        txtEstablecimiento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstablecimiento1KeyTyped(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Si");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("No");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("No Aplica");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Emergencia");

        chkEsi.setEditable(false);
        chkEsi.setBackground(new java.awt.Color(255, 255, 255));
        chkEsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkEsi.setForeground(new java.awt.Color(102, 102, 102));
        chkEsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkEsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkEsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkEsi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkEsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkEsiCaretUpdate(evt);
            }
        });
        chkEsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkEsiMouseClicked(evt);
            }
        });

        chkEno.setEditable(false);
        chkEno.setBackground(new java.awt.Color(255, 204, 51));
        chkEno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkEno.setForeground(new java.awt.Color(102, 102, 102));
        chkEno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkEno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkEno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkEno.setPreferredSize(new java.awt.Dimension(18, 18));
        chkEno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkEnoCaretUpdate(evt);
            }
        });
        chkEno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkEnoMouseClicked(evt);
            }
        });

        chkEna.setEditable(false);
        chkEna.setBackground(new java.awt.Color(255, 255, 255));
        chkEna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkEna.setForeground(new java.awt.Color(102, 102, 102));
        chkEna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkEna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkEna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkEna.setPreferredSize(new java.awt.Dimension(18, 18));
        chkEna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkEnaCaretUpdate(evt);
            }
        });
        chkEna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkEnaMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Fecha");

        fechaE.setBackground(new java.awt.Color(255, 255, 255));
        fechaE.setDateFormatString("dd-MM-yyyy");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Establecimiento de traslado");

        txtEstablecimiento2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEstablecimiento2.setForeground(new java.awt.Color(102, 102, 102));
        txtEstablecimiento2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEstablecimiento2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEstablecimiento2CaretUpdate(evt);
            }
        });
        txtEstablecimiento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstablecimiento2KeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Apoyo al diagnostíco");

        txtEstablecimiento3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtEstablecimiento3.setForeground(new java.awt.Color(102, 102, 102));
        txtEstablecimiento3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEstablecimiento3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtEstablecimiento3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtEstablecimiento3CaretUpdate(evt);
            }
        });
        txtEstablecimiento3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstablecimiento3KeyTyped(evt);
            }
        });

        chkADsi.setEditable(false);
        chkADsi.setBackground(new java.awt.Color(255, 255, 255));
        chkADsi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkADsi.setForeground(new java.awt.Color(102, 102, 102));
        chkADsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkADsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkADsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkADsi.setPreferredSize(new java.awt.Dimension(18, 18));
        chkADsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkADsiCaretUpdate(evt);
            }
        });
        chkADsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkADsiMouseClicked(evt);
            }
        });

        chkADno.setEditable(false);
        chkADno.setBackground(new java.awt.Color(255, 204, 51));
        chkADno.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkADno.setForeground(new java.awt.Color(102, 102, 102));
        chkADno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkADno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkADno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkADno.setPreferredSize(new java.awt.Dimension(18, 18));
        chkADno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkADnoCaretUpdate(evt);
            }
        });
        chkADno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkADnoMouseClicked(evt);
            }
        });

        chkADna.setEditable(false);
        chkADna.setBackground(new java.awt.Color(255, 255, 255));
        chkADna.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        chkADna.setForeground(new java.awt.Color(102, 102, 102));
        chkADna.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkADna.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chkADna.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkADna.setPreferredSize(new java.awt.Dimension(18, 18));
        chkADna.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkADnaCaretUpdate(evt);
            }
        });
        chkADna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkADnaMouseClicked(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Fecha");

        fechaAD.setBackground(new java.awt.Color(255, 255, 255));
        fechaAD.setDateFormatString("dd-MM-yyyy");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Establecimiento de traslado");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(chkCEsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(87, 87, 87)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkADsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkEsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(chkEno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkCEno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(chkCEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkADna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel49)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel51)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaAD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addComponent(chkADno, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addGap(18, 18, 18)
                                .addComponent(txtEstablecimiento3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(txtEstablecimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(18, 18, 18)
                                .addComponent(txtEstablecimiento2))))
                    .addComponent(jLabel34)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkCEsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCEno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCEna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel44)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtEstablecimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fechaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(chkADsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkADno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkADna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(txtEstablecimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(fechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9)
                            .addComponent(fechaAD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkEsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkEno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkEna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel49))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(txtEstablecimiento3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        lblIdCp.setForeground(new java.awt.Color(255, 255, 255));
        lblIdCp.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblIdCp)))
                .addContainerGap(538, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIdCp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(var)
                .addGap(128, 128, 128))
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("<html>Patologías<br>Maternas<span style=\"font-size:'15px'\"><br>(CIE 10) Diagnosticadas</br></html>");

        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblusu.setText("Silvana");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

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

        lblIdPM1.setForeground(new java.awt.Color(255, 255, 255));

        lblMant.setForeground(new java.awt.Color(102, 102, 102));
        lblMant.setText("I");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblusu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel42))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel43))
                    .addComponent(lblIdPM1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMant)
                        .addGap(54, 54, 54)
                        .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMant)
                    .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btneditar)
                .addGap(85, 85, 85)
                .addComponent(lblIdPM1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblusu)
                .addContainerGap())
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

        jPanel7.setBackground(new java.awt.Color(43, 43, 43));
        jPanel7.setPreferredSize(new java.awt.Dimension(1574, 113));

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

        lblMadre.setBackground(new java.awt.Color(0, 153, 0));
        lblMadre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblMadre.setForeground(new java.awt.Color(255, 255, 255));
        lblMadre.setText("Martha Arias Torres");
        lblMadre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMadre.setIconTextGap(10);
        lblMadre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel38.setBackground(new java.awt.Color(39, 174, 97));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda Filled-30.png"))); // NOI18N
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35)
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblActoMedico2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblActoMedico3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblActoMedico)
                            .addComponent(lblFP))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(lblMadre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActoMedico2)
                    .addComponent(lblActoMedico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFP)
                    .addComponent(lblActoMedico3))
                .addContainerGap(23, Short.MAX_VALUE))
            .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1625, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkCEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnoCaretUpdate

    private void chkCEsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEsiCaretUpdate

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

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         btnGuardar.setEnabled(true);
         btneditar.setEnabled(false);
         HabilitarDatos();
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

    private void chkCEnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEnoMouseClicked
       if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCEno.getText().equals("") && evt.getClickCount()==1){
           chkCEno.setText("X");
           chkCEsi.setText("");
           chkCEna.setText("");
        }else
        if(chkCEno.getText().equals("X") && evt.getClickCount()==1){
           chkCEno.setText(""); 
           chkCEsi.setText("");
           chkCEna.setText("");
        }
       }
    }//GEN-LAST:event_chkCEnoMouseClicked

    private void chkCEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEsiMouseClicked
     if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCEsi.getText().equals("") && evt.getClickCount()==1){
           chkCEsi.setText("X");
           chkCEno.setText("");
           chkCEna.setText("");
        }else
        if(chkCEsi.getText().equals("X") && evt.getClickCount()==1){
           chkCEsi.setText(""); 
            chkCEno.setText("");
           chkCEna.setText("");
        }
       }
    }//GEN-LAST:event_chkCEsiMouseClicked

    private void chkCEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnaCaretUpdate

    private void chkCEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEnaMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCEna.getText().equals("") && evt.getClickCount()==1){
           chkCEna.setText("X");
           chkCEno.setText("");
           chkCEsi.setText("");
        }else
        if(chkCEna.getText().equals("X") && evt.getClickCount()==1){
           chkCEna.setText(""); 
           chkCEno.setText("");
           chkCEsi.setText("");
        }
       }
    }//GEN-LAST:event_chkCEnaMouseClicked

    private void txtEstablecimiento1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstablecimiento1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento1CaretUpdate

    private void txtEstablecimiento1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstablecimiento1KeyTyped
       
    }//GEN-LAST:event_txtEstablecimiento1KeyTyped

    private void chkEsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkEsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEsiCaretUpdate

    private void chkEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEsiMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkEsi.getText().equals("") && evt.getClickCount()==1){
           chkEsi.setText("X");
           chkEno.setText("");
           chkEna.setText("");
        }else
        if(chkEsi.getText().equals("X") && evt.getClickCount()==1){
           chkEsi.setText(""); 
           chkEno.setText("");
           chkEna.setText("");
        }
       }
    }//GEN-LAST:event_chkEsiMouseClicked

    private void chkEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEnoCaretUpdate

    private void chkEnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEnoMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkEno.getText().equals("") && evt.getClickCount()==1){
           chkEno.setText("X");
           chkEsi.setText("");
           chkEna.setText("");
        }else
        if(chkEno.getText().equals("X") && evt.getClickCount()==1){
           chkEno.setText(""); 
           chkEsi.setText("");
           chkEna.setText("");
        }
       }
    }//GEN-LAST:event_chkEnoMouseClicked

    private void chkEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEnaCaretUpdate

    private void chkEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEnaMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkEna.getText().equals("") && evt.getClickCount()==1){
           chkEna.setText("X");
           chkEno.setText("");
           chkEsi.setText("");
        }else
        if(chkEna.getText().equals("X") && evt.getClickCount()==1){
           chkEna.setText(""); 
           chkEno.setText("");
           chkEsi.setText("");
        }
       }
    }//GEN-LAST:event_chkEnaMouseClicked

    private void txtEstablecimiento2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstablecimiento2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento2CaretUpdate

    private void txtEstablecimiento2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstablecimiento2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento2KeyTyped

    private void chkADsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkADsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkADsiCaretUpdate

    private void chkADsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkADsiMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkADsi.getText().equals("") && evt.getClickCount()==1){
           chkADsi.setText("X");
           chkADno.setText("");
           chkADna.setText("");
        }else
        if(chkADsi.getText().equals("X") && evt.getClickCount()==1){
           chkADsi.setText(""); 
           chkADno.setText("");
           chkADna.setText("");
        }
       }
    }//GEN-LAST:event_chkADsiMouseClicked

    private void chkADnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkADnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkADnoCaretUpdate

    private void chkADnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkADnoMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkADno.getText().equals("") && evt.getClickCount()==1){
           chkADno.setText("X");
           chkADsi.setText("");
           chkADna.setText("");
        }else
        if(chkADno.getText().equals("X") && evt.getClickCount()==1){
           chkADno.setText(""); 
           chkADsi.setText("");
           chkADna.setText("");
        }
       }
    }//GEN-LAST:event_chkADnoMouseClicked

    private void chkADnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkADnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkADnaCaretUpdate

    private void chkADnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkADnaMouseClicked
        if (lblIdPM1.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkADna.getText().equals("") && evt.getClickCount()==1){
           chkADna.setText("X");
           chkADsi.setText("");
           chkADno.setText("");
        }else
        if(chkADna.getText().equals("X") && evt.getClickCount()==1){
           chkADna.setText(""); 
           chkADsi.setText("");
           chkADno.setText("");
        }
       }
    }//GEN-LAST:event_chkADnaMouseClicked

    private void txtEstablecimiento3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEstablecimiento3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento3CaretUpdate

    private void txtEstablecimiento3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstablecimiento3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstablecimiento3KeyTyped

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel35MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkADna;
    public static javax.swing.JTextField chkADno;
    public static javax.swing.JTextField chkADsi;
    public static javax.swing.JTextField chkCEna;
    public static javax.swing.JTextField chkCEno;
    public static javax.swing.JTextField chkCEsi;
    public static javax.swing.JTextField chkEna;
    public static javax.swing.JTextField chkEno;
    public static javax.swing.JTextField chkEsi;
    public static com.toedter.calendar.JDateChooser fechaAD;
    public static com.toedter.calendar.JDateChooser fechaCE;
    public static com.toedter.calendar.JDateChooser fechaE;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public static javax.swing.JLabel lblActoMedico;
    public static javax.swing.JLabel lblActoMedico2;
    public static javax.swing.JLabel lblActoMedico3;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdActoMedico;
    public static javax.swing.JLabel lblIdCp;
    public static javax.swing.JLabel lblIdPM1;
    public static javax.swing.JLabel lblMadre;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtEstablecimiento1;
    public static javax.swing.JTextField txtEstablecimiento2;
    public static javax.swing.JTextField txtEstablecimiento3;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
