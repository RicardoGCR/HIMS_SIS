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
import modelos.ConsultorioEx.ConsultorioExtCarnetPerinatalGa;
import static vista.ConsultorioEx.RegistroEmbarazoAO.chk1;

/**
 *
 * @author MYS1
 */
public class RegistroEmbarazoGA extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    

    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoGA() {
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
     fechaGA.setEnabled(true);
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
        
    ConsultorioExtCarnetPerinatalGa CXRsGA= new ConsultorioExtCarnetPerinatalGa();
    ConsultorioExtCarnetPerinatalGa CXRsGA2 = new ConsultorioExtCarnetPerinatalGa();
    
           if(lblMant.getText().equals("U"))
            CXRsGA.setGaId(Integer.parseInt(lblIdGA.getText()));
            CXRsGA.setCpId(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            ////////////////INTERGENESTICO
            if(chkSi.getText().equals("X"))
                CXRsGA.setGaIntergenesico("Si");
            else
            if(chkNo.getText().equals("X"))
                CXRsGA.setGaIntergenesico("No");
            else
                CXRsGA.setGaIntergenesico("");
                /////////// TERMINACION
            if(txtT1.getText().equals("X"))
                CXRsGA.setGaTerminacion("Parto Vaginal");
            else
            if(txtT2.getText().equals("X"))
                CXRsGA.setGaTerminacion("Cesarea");
            else
            if(txtT3.getText().equals("X"))
                CXRsGA.setGaTerminacion("Aborto");
            else
            if(txtT4.getText().equals("X"))
                CXRsGA.setGaTerminacion("Ectopico");
            else
            if(txtT5.getText().equals("X"))
                CXRsGA.setGaTerminacion("Aborto Molar");
            else
            if(txtT6.getText().equals("X"))
                CXRsGA.setGaTerminacion("No Aplica");
            else
                CXRsGA.setGaTerminacion("");
            
            //////////////////////TIPO DE ABORTO
            if(txtTa1.getText().equals("X"))
                CXRsGA.setGaTipoAborto("Incompleto");
            else
            if(txtTa2.getText().equals("X"))
                CXRsGA.setGaTipoAborto("Completo");
            else
            if(txtTa3.getText().equals("X"))
                CXRsGA.setGaTipoAborto("Frusto/Retenido");
            else
            if(txtTa4.getText().equals("X"))
                CXRsGA.setGaTipoAborto("Septico");
            else
            if(txtTa5.getText().equals("X"))
                CXRsGA.setGaTipoAborto("No Aplica");
           
            else
                CXRsGA.setGaTipoAborto("");
            
            
            //////////////////////LACTANCIA
            if(txtLm1.getText().equals("X"))
                CXRsGA.setGaLactanciaMat("No Hubo");
            else
            if(txtLm2.getText().equals("X"))
                CXRsGA.setGaLactanciaMat("< 6 meses");
            else
            if(txtLm3.getText().equals("X"))
                CXRsGA.setGaLactanciaMat("6 meses a mas");
            else
            if(txtLm4.getText().equals("X"))
                CXRsGA.setGaLactanciaMat("No Aplica");
            else
                CXRsGA.setGaLactanciaMat("");
            
            //////////////////////LUGAR DE PARTO
            if(txtLp1.getText().equals("X"))
                CXRsGA.setGaLugarParto("EESS");
            else
            if(txtLp2.getText().equals("X"))
                CXRsGA.setGaLugarParto("DOMIC");
            else
                CXRsGA.setGaLugarParto("");

            CXRsGA.setGaFechaGestacion(determinarFecha(fechaGA));
            //////////////////////CAPTADA
            if(chkCsi.getText().equals("X"))
                CXRsGA.setGA_CAPTADA("S");
            else
            if(chkCno.getText().equals("X"))
                CXRsGA.setGA_CAPTADA("N");
            else
                CXRsGA.setGA_CAPTADA("");
            //////////////////////REFERIDA
            if(chkRsi.getText().equals("X"))
                CXRsGA.setGA_REFERIDA("S");
            else
            if(chkRno.getText().equals("X"))
                CXRsGA.setGA_REFERIDA("N");
            else
                CXRsGA.setGA_REFERIDA("");

            CXRsGA.setCodUsu(lblusu.getText());//falta 

            
                if(CXRsGA.mantenimientoConsultorioExtGA(lblMant.getText())==true){
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
                    CXRsGA2.ConsultoriosExtGAListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
 
                    CXRsGA2.ConsultoriosExtGAListar(RegistroEmbarazoPrincipal.lblId.getText());     
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
        jLabel10 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtT3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtLm3 = new javax.swing.JTextField();
        txtT5 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        chkSi = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        chkNo = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fechaGA = new com.toedter.calendar.JDateChooser();
        txtT2 = new javax.swing.JTextField();
        txtLm4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTa4 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTa1 = new javax.swing.JTextField();
        txtLm2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtLp1 = new javax.swing.JTextField();
        txtLm1 = new javax.swing.JTextField();
        txtT1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtT6 = new javax.swing.JTextField();
        txtT4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTa3 = new javax.swing.JTextField();
        txtTa5 = new javax.swing.JTextField();
        txtLp2 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTa2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        chkCsi = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        chkRsi = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        chkRno = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        chkCno = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        var = new javax.swing.JLabel();
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
        lblIdGA = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        lblMadreGA = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createCompoundBorder());
        setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Ectópico");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("EESS");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Lugar del parto");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Completo");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Cesárea");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Per. Intergenésico Adecuado");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Incompleto ");

        txtT3.setEditable(false);
        txtT3.setBackground(new java.awt.Color(255, 204, 51));
        txtT3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT3.setForeground(new java.awt.Color(102, 102, 102));
        txtT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT3.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT3CaretUpdate(evt);
            }
        });
        txtT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT3MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("No hubo");

        txtLm3.setEditable(false);
        txtLm3.setBackground(new java.awt.Color(255, 255, 255));
        txtLm3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLm3.setForeground(new java.awt.Color(102, 102, 102));
        txtLm3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLm3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLm3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLm3.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLm3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLm3CaretUpdate(evt);
            }
        });
        txtLm3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLm3MouseClicked(evt);
            }
        });

        txtT5.setEditable(false);
        txtT5.setBackground(new java.awt.Color(255, 204, 51));
        txtT5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT5.setForeground(new java.awt.Color(102, 102, 102));
        txtT5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT5.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT5CaretUpdate(evt);
            }
        });
        txtT5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT5MouseClicked(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        chkSi.setEditable(false);
        chkSi.setBackground(new java.awt.Color(204, 204, 204));
        chkSi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkSi.setForeground(new java.awt.Color(102, 102, 102));
        chkSi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkSi.setBorder(null);
        chkSi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkSi.setPreferredSize(new java.awt.Dimension(28, 28));
        chkSi.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkSi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkSiCaretUpdate(evt);
            }
        });
        chkSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkSiMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("SI");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("NO");

        chkNo.setEditable(false);
        chkNo.setBackground(new java.awt.Color(204, 204, 204));
        chkNo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkNo.setForeground(new java.awt.Color(102, 102, 102));
        chkNo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkNo.setBorder(null);
        chkNo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkNo.setPreferredSize(new java.awt.Dimension(28, 28));
        chkNo.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkNo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkNoCaretUpdate(evt);
            }
        });
        chkNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkNoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(19, 19, 19)
                .addComponent(chkSi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(chkNo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(chkNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Domic.");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("No aplica");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("<html>Lactancia<br>Materna</br></span></html>");

        fechaGA.setBackground(new java.awt.Color(255, 255, 255));
        fechaGA.setDateFormatString("dd-MM-yyyy");

        txtT2.setEditable(false);
        txtT2.setBackground(new java.awt.Color(255, 204, 51));
        txtT2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT2.setForeground(new java.awt.Color(102, 102, 102));
        txtT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT2CaretUpdate(evt);
            }
        });
        txtT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT2MouseClicked(evt);
            }
        });

        txtLm4.setEditable(false);
        txtLm4.setBackground(new java.awt.Color(255, 255, 255));
        txtLm4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLm4.setForeground(new java.awt.Color(102, 102, 102));
        txtLm4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLm4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLm4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLm4.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLm4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLm4CaretUpdate(evt);
            }
        });
        txtLm4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLm4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Parto Vaginal");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Terminación");

        txtTa4.setEditable(false);
        txtTa4.setBackground(new java.awt.Color(255, 204, 51));
        txtTa4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTa4.setForeground(new java.awt.Color(102, 102, 102));
        txtTa4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTa4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTa4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTa4.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTa4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTa4CaretUpdate(evt);
            }
        });
        txtTa4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTa4MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("<html>Si fue aborto<br>Tipo de Aborto</br></span></html>");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("6 meses a más");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Fecha");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Aborto");

        txtTa1.setEditable(false);
        txtTa1.setBackground(new java.awt.Color(255, 204, 51));
        txtTa1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTa1.setForeground(new java.awt.Color(102, 102, 102));
        txtTa1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTa1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTa1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTa1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTa1CaretUpdate(evt);
            }
        });
        txtTa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTa1MouseClicked(evt);
            }
        });

        txtLm2.setEditable(false);
        txtLm2.setBackground(new java.awt.Color(255, 204, 51));
        txtLm2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLm2.setForeground(new java.awt.Color(102, 102, 102));
        txtLm2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLm2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLm2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLm2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLm2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLm2CaretUpdate(evt);
            }
        });
        txtLm2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLm2MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("No Aplica");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Frusto / Retenido");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("No Aplica");

        txtLp1.setEditable(false);
        txtLp1.setBackground(new java.awt.Color(255, 255, 255));
        txtLp1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLp1.setForeground(new java.awt.Color(102, 102, 102));
        txtLp1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLp1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLp1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLp1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLp1CaretUpdate(evt);
            }
        });
        txtLp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLp1MouseClicked(evt);
            }
        });

        txtLm1.setEditable(false);
        txtLm1.setBackground(new java.awt.Color(255, 51, 51));
        txtLm1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLm1.setForeground(new java.awt.Color(255, 255, 255));
        txtLm1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLm1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLm1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLm1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLm1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLm1CaretUpdate(evt);
            }
        });
        txtLm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLm1MouseClicked(evt);
            }
        });

        txtT1.setEditable(false);
        txtT1.setBackground(new java.awt.Color(255, 255, 255));
        txtT1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT1.setForeground(new java.awt.Color(102, 102, 102));
        txtT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT1.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT1.setSelectionColor(new java.awt.Color(255, 204, 51));
        txtT1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT1CaretUpdate(evt);
            }
        });
        txtT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT1MouseClicked(evt);
            }
        });
        txtT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtT1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Séptico");

        txtT6.setEditable(false);
        txtT6.setBackground(new java.awt.Color(255, 255, 255));
        txtT6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT6.setForeground(new java.awt.Color(102, 102, 102));
        txtT6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT6.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT6.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT6CaretUpdate(evt);
            }
        });
        txtT6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT6MouseClicked(evt);
            }
        });

        txtT4.setEditable(false);
        txtT4.setBackground(new java.awt.Color(255, 204, 51));
        txtT4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtT4.setForeground(new java.awt.Color(102, 102, 102));
        txtT4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtT4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtT4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtT4.setPreferredSize(new java.awt.Dimension(18, 18));
        txtT4.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtT4CaretUpdate(evt);
            }
        });
        txtT4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtT4MouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Aborto molar");

        txtTa3.setEditable(false);
        txtTa3.setBackground(new java.awt.Color(255, 204, 51));
        txtTa3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTa3.setForeground(new java.awt.Color(102, 102, 102));
        txtTa3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTa3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTa3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTa3.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTa3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTa3CaretUpdate(evt);
            }
        });
        txtTa3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTa3MouseClicked(evt);
            }
        });

        txtTa5.setEditable(false);
        txtTa5.setBackground(new java.awt.Color(255, 255, 255));
        txtTa5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTa5.setForeground(new java.awt.Color(102, 102, 102));
        txtTa5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTa5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTa5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTa5.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTa5.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTa5CaretUpdate(evt);
            }
        });
        txtTa5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTa5MouseClicked(evt);
            }
        });

        txtLp2.setEditable(false);
        txtLp2.setBackground(new java.awt.Color(255, 255, 255));
        txtLp2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtLp2.setForeground(new java.awt.Color(102, 102, 102));
        txtLp2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLp2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtLp2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtLp2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtLp2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtLp2CaretUpdate(evt);
            }
        });
        txtLp2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLp2MouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("< 6 meses");

        txtTa2.setEditable(false);
        txtTa2.setBackground(new java.awt.Color(255, 204, 51));
        txtTa2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTa2.setForeground(new java.awt.Color(102, 102, 102));
        txtTa2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTa2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        txtTa2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtTa2.setPreferredSize(new java.awt.Dimension(18, 18));
        txtTa2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTa2CaretUpdate(evt);
            }
        });
        txtTa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTa2MouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Captada");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Referida x Ag comuni.");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        chkCsi.setEditable(false);
        chkCsi.setBackground(new java.awt.Color(204, 204, 204));
        chkCsi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkCsi.setForeground(new java.awt.Color(102, 102, 102));
        chkCsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkCsi.setBorder(null);
        chkCsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCsi.setPreferredSize(new java.awt.Dimension(28, 28));
        chkCsi.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkCsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkCsiCaretUpdate(evt);
            }
        });
        chkCsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCsiMouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("SI");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCsi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        chkRsi.setEditable(false);
        chkRsi.setBackground(new java.awt.Color(204, 204, 204));
        chkRsi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkRsi.setForeground(new java.awt.Color(102, 102, 102));
        chkRsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkRsi.setBorder(null);
        chkRsi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkRsi.setPreferredSize(new java.awt.Dimension(28, 28));
        chkRsi.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkRsi.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkRsiCaretUpdate(evt);
            }
        });
        chkRsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkRsiMouseClicked(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("SI");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkRsi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkRsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        chkRno.setEditable(false);
        chkRno.setBackground(new java.awt.Color(204, 204, 204));
        chkRno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkRno.setForeground(new java.awt.Color(102, 102, 102));
        chkRno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkRno.setBorder(null);
        chkRno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkRno.setPreferredSize(new java.awt.Dimension(28, 28));
        chkRno.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkRno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkRnoCaretUpdate(evt);
            }
        });
        chkRno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkRnoMouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("NO");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkRno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkRno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        chkCno.setEditable(false);
        chkCno.setBackground(new java.awt.Color(204, 204, 204));
        chkCno.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chkCno.setForeground(new java.awt.Color(102, 102, 102));
        chkCno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chkCno.setBorder(null);
        chkCno.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkCno.setPreferredSize(new java.awt.Dimension(28, 28));
        chkCno.setSelectionColor(new java.awt.Color(204, 204, 204));
        chkCno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                chkCnoCaretUpdate(evt);
            }
        });
        chkCno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkCnoMouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("NO");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        var.setText("1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(58, 58, 58)
                        .addComponent(fechaGA, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtT6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtT5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                    .addGap(22, 22, 22)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtT4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtT2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtT3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTa5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTa4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTa3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtLm4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel22))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtLm3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLm1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLm2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(99, 99, 99)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaGA, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtT5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtLm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel20))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtLm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtLp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtLp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLm3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLm4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtT6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("<html>Gestación <br>Anterior</html>");

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
                            .addComponent(jLabel37)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkAnalf1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ChkEdad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel43))
                            .addComponent(lblIdGA, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(btnCaccnelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addComponent(lblIdGA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblusu)
                .addGap(28, 28, 28)
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

        lblMadreGA.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        lblMadreGA.setForeground(new java.awt.Color(12, 97, 81));
        lblMadreGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
        lblMadreGA.setText("Martha Arias Torres");
        lblMadreGA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMadreGA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMadreGA, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblMadreGA)
                        .addGap(23, 23, 23)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkNoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkNoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNoCaretUpdate

    private void chkNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNoMouseClicked
       
         if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkNo.getText().equals("") && evt.getClickCount()==1){
           chkNo.setText("X");
           chkSi.setText(""); 
        }else
        if(chkNo.getText().equals("X") && evt.getClickCount()==1){
           chkNo.setText("");
           chkSi.setText(""); 
        }
         }
    }//GEN-LAST:event_chkNoMouseClicked

    private void chkSiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkSiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSiCaretUpdate

    private void chkSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkSiMouseClicked
          if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
       if(chkSi.getText().equals("") && evt.getClickCount()==1){
           chkSi.setText("X");
           chkNo.setText(""); 
  
        }else
        if(chkSi.getText().equals("X") && evt.getClickCount()==1){
           chkSi.setText(""); 
           chkNo.setText(""); 

        }
          }
    }//GEN-LAST:event_chkSiMouseClicked

    private void txtT1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1CaretUpdate

    private void txtT2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT2CaretUpdate

    private void txtT3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT3CaretUpdate

    private void txtT4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT4CaretUpdate

    private void txtT5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT5CaretUpdate

    private void txtT6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT6CaretUpdate

    private void txtTa1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa1CaretUpdate

    private void txtTa2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa2CaretUpdate

    private void txtTa3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa3CaretUpdate

    private void txtTa4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa4CaretUpdate

    private void txtTa5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa5CaretUpdate

    private void txtLm1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm1CaretUpdate

    private void txtLm2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm2CaretUpdate

    private void txtLm3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm3CaretUpdate

    private void txtLm4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm4CaretUpdate

    private void txtLp1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp1CaretUpdate

    private void txtLp2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp2CaretUpdate

    private void chkCsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCsiCaretUpdate

    private void chkCsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCsiMouseClicked
  if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCsi.getText().equals("") && evt.getClickCount()==1){
           chkCsi.setText("X");
           chkCno.setText("");

        }else
        if(chkCsi.getText().equals("X") && evt.getClickCount()==1){
           chkCsi.setText(""); 
           chkCno.setText("");

        }
  }
    }//GEN-LAST:event_chkCsiMouseClicked

    private void chkCnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCnoCaretUpdate

    private void chkCnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCnoMouseClicked
         if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCno.getText().equals("") && evt.getClickCount()==1){
           chkCno.setText("X");
           chkCsi.setText("");
        }else
        if(chkCno.getText().equals("X") && evt.getClickCount()==1){
           chkCno.setText(""); 
           chkCsi.setText("");

        }
         }
    }//GEN-LAST:event_chkCnoMouseClicked

    private void chkRnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkRnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRnoCaretUpdate

    private void chkRnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRnoMouseClicked
  if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkRno.getText().equals("") && evt.getClickCount()==1){
           chkRno.setText("X");
           chkRsi.setText("");
        }else
        if(chkRno.getText().equals("X") && evt.getClickCount()==1){
           chkRno.setText(""); 
           chkRsi.setText("");
        }
  }
    }//GEN-LAST:event_chkRnoMouseClicked

    private void chkRsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkRsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRsiCaretUpdate

    private void chkRsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRsiMouseClicked
  if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkRsi.getText().equals("") && evt.getClickCount()==1){
           chkRsi.setText("X");
           chkRno.setText("");
        }else
        if(chkRsi.getText().equals("X") && evt.getClickCount()==1){
           chkRsi.setText(""); 
           chkRno.setText("");
        }
  }
    }//GEN-LAST:event_chkRsiMouseClicked

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

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        RegistroEmbarazo.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_jLabel32MouseClicked

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

    private void txtT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT1MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT1.getText().equals("") && evt.getClickCount()==1){
           txtT1.setText("X");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }else
        if(txtT1.getText().equals("X") && evt.getClickCount()==1){
           txtT1.setText(""); 
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }
        }
    }//GEN-LAST:event_txtT1MouseClicked

    private void txtT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT2MouseClicked
       if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT2.getText().equals("") && evt.getClickCount()==1){
           txtT2.setText("X");
           txtT1.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }else
        if(txtT2.getText().equals("X") && evt.getClickCount()==1){
           txtT2.setText(""); 
           txtT1.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }
       }
    }//GEN-LAST:event_txtT2MouseClicked

    private void txtT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT3MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT3.getText().equals("") && evt.getClickCount()==1){
           txtT3.setText("X");
           txtT1.setText("");
           txtT2.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }else
        if(txtT3.getText().equals("X") && evt.getClickCount()==1){
           txtT3.setText(""); 
           txtT1.setText("");
           txtT2.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }
        }
    }//GEN-LAST:event_txtT3MouseClicked

    private void txtT4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT4MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT4.getText().equals("") && evt.getClickCount()==1){
           txtT4.setText("X");
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }else
        if(txtT4.getText().equals("X") && evt.getClickCount()==1){
           txtT4.setText(""); 
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }
        }
    }//GEN-LAST:event_txtT4MouseClicked

    private void txtT5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT5MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT5.getText().equals("") && evt.getClickCount()==1){
           txtT5.setText("X");
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT6.setText("");
        }else
        if(txtT5.getText().equals("X") && evt.getClickCount()==1){
           txtT5.setText(""); 
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT6.setText("");
        }
        }
    }//GEN-LAST:event_txtT5MouseClicked

    private void txtT6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT6MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtT6.getText().equals("") && evt.getClickCount()==1){
           txtT6.setText("X");
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
        }else
        if(txtT6.getText().equals("X") && evt.getClickCount()==1){
           txtT6.setText(""); 
           txtT1.setText("");
           txtT2.setText("");
           txtT3.setText("");
           txtT4.setText("");
           txtT5.setText("");
        }
        }
    }//GEN-LAST:event_txtT6MouseClicked

    private void txtTa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa1MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTa1.getText().equals("") && evt.getClickCount()==1){
           txtTa1.setText("X");
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText(""); 
           
        }else
        if(txtTa1.getText().equals("X") && evt.getClickCount()==1){
           txtTa1.setText(""); 
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText(""); 
        }
        }
    }//GEN-LAST:event_txtTa1MouseClicked

    private void txtTa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa2MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTa2.getText().equals("") && evt.getClickCount()==1){
           txtTa2.setText("X");
           txtTa1.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText(""); 
           
        }else
        if(txtTa2.getText().equals("X") && evt.getClickCount()==1){
           txtTa2.setText(""); 
           txtTa1.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText("");
        }
        }
    }//GEN-LAST:event_txtTa2MouseClicked

    private void txtTa3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa3MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTa3.getText().equals("") && evt.getClickCount()==1){
           txtTa3.setText("X");
           txtTa2.setText(""); 
           txtTa1.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText(""); 
           
        }else
        if(txtTa3.getText().equals("X") && evt.getClickCount()==1){
           txtTa3.setText(""); 
           txtTa2.setText(""); 
           txtTa1.setText(""); 
           txtTa4.setText(""); 
           txtTa5.setText(""); 
        }
        }
    }//GEN-LAST:event_txtTa3MouseClicked

    private void txtTa4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa4MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTa4.getText().equals("") && evt.getClickCount()==1){
           txtTa4.setText("X");
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa1.setText(""); 
           txtTa5.setText("");
           
        }else
        if(txtTa4.getText().equals("X") && evt.getClickCount()==1){
           txtTa4.setText(""); 
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa1.setText(""); 
           txtTa5.setText(""); 
        }
        }
    }//GEN-LAST:event_txtTa4MouseClicked

    private void txtTa5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa5MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtTa5.getText().equals("") && evt.getClickCount()==1){
           txtTa5.setText("X");
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa1.setText(""); 
           
        }else
        if(txtTa5.getText().equals("X") && evt.getClickCount()==1){
           txtTa5.setText(""); 
           txtTa2.setText(""); 
           txtTa3.setText(""); 
           txtTa4.setText(""); 
           txtTa1.setText(""); 
        }
        }
    }//GEN-LAST:event_txtTa5MouseClicked

    private void txtLm1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm1MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLm1.getText().equals("") && evt.getClickCount()==1){
           txtLm1.setText("X");
           txtLm2.setText(""); 
           txtLm3.setText(""); 
           txtLm4.setText(""); 
        }else
        if(txtLm1.getText().equals("X") && evt.getClickCount()==1){
           txtLm1.setText(""); 
           txtLm2.setText(""); 
           txtLm3.setText(""); 
           txtLm4.setText("");
        }
        }
    }//GEN-LAST:event_txtLm1MouseClicked

    private void txtLm2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm2MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLm2.getText().equals("") && evt.getClickCount()==1){
           txtLm2.setText("X");
           txtLm1.setText(""); 
           txtLm3.setText(""); 
           txtLm4.setText(""); 
        }else
        if(txtLm2.getText().equals("X") && evt.getClickCount()==1){
           txtLm2.setText(""); 
           txtLm1.setText(""); 
           txtLm3.setText(""); 
           txtLm4.setText(""); 
        }
        }
    }//GEN-LAST:event_txtLm2MouseClicked

    private void txtLm3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm3MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLm3.getText().equals("") && evt.getClickCount()==1){
           txtLm3.setText("X");
           txtLm2.setText(""); 
           txtLm1.setText(""); 
           txtLm4.setText(""); 
        }else
        if(txtLm3.getText().equals("X") && evt.getClickCount()==1){
           txtLm3.setText(""); 
           txtLm2.setText(""); 
           txtLm1.setText(""); 
           txtLm4.setText(""); 
        }
        }
    }//GEN-LAST:event_txtLm3MouseClicked

    private void txtLm4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm4MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLm4.getText().equals("") && evt.getClickCount()==1){
           txtLm4.setText("X");
           txtLm2.setText(""); 
           txtLm3.setText(""); 
           txtLm1.setText(""); 
        }else
        if(txtLm4.getText().equals("X") && evt.getClickCount()==1){
           txtLm4.setText(""); 
           txtLm2.setText(""); 
           txtLm3.setText(""); 
           txtLm1.setText(""); 
        }
        }
    }//GEN-LAST:event_txtLm4MouseClicked

    private void txtLp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp1MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLp1.getText().equals("") && evt.getClickCount()==1){
           txtLp1.setText("X");
           txtLp2.setText(""); 
        }else
        if(txtLp1.getText().equals("X") && evt.getClickCount()==1){
           txtLp1.setText(""); 
           txtLp2.setText(""); 
        }
        }
    }//GEN-LAST:event_txtLp1MouseClicked

    private void txtLp2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp2MouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(txtLp2.getText().equals("") && evt.getClickCount()==1){
           txtLp2.setText("X");
           txtLp1.setText(""); 
        }else
        if(txtLp2.getText().equals("X") && evt.getClickCount()==1){
           txtLp2.setText(""); 
           txtLp1.setText(""); 
        }
        }
    }//GEN-LAST:event_txtLp2MouseClicked

    private void txtT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkCno;
    public static javax.swing.JTextField chkCsi;
    public static javax.swing.JTextField chkNo;
    public static javax.swing.JTextField chkRno;
    public static javax.swing.JTextField chkRsi;
    public static javax.swing.JTextField chkSi;
    public static com.toedter.calendar.JDateChooser fechaGA;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JLabel lblIdGA;
    public static javax.swing.JLabel lblMadreGA;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    public static javax.swing.JTextField txtLm1;
    public static javax.swing.JTextField txtLm2;
    public static javax.swing.JTextField txtLm3;
    public static javax.swing.JTextField txtLm4;
    public static javax.swing.JTextField txtLp1;
    public static javax.swing.JTextField txtLp2;
    public static javax.swing.JTextField txtT1;
    public static javax.swing.JTextField txtT2;
    public static javax.swing.JTextField txtT3;
    public static javax.swing.JTextField txtT4;
    public static javax.swing.JTextField txtT5;
    public static javax.swing.JTextField txtT6;
    public static javax.swing.JTextField txtTa1;
    public static javax.swing.JTextField txtTa2;
    public static javax.swing.JTextField txtTa3;
    public static javax.swing.JTextField txtTa4;
    public static javax.swing.JTextField txtTa5;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
