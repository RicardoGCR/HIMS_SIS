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
public class RegistroEmbarazoPM extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    byte tg;
    byte tge;
    

    /**
     * Creates new form RegistroEmbarazoAO
     */
    public RegistroEmbarazoPM() {
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
            if(chkSP.getText().equals("X"))
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
            if(chkCEno.getText().equals("X"))
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
            if(chkCEsi.getText().equals("X"))
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

        FrmCie10 = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        T7 = new javax.swing.JLabel();
        txtBuscarCie10 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCiePresun = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel2 = new javax.swing.JPanel();
            var = new javax.swing.JLabel();
            chkSP = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbPatologias = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jLabel29 = new javax.swing.JLabel();
                jPanel20 = new javax.swing.JPanel();
                txtCIE10 = new javax.swing.JTextField();
                btnBuscarNino2 = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                fechaGA = new com.toedter.calendar.JDateChooser();
                CARGAR = new javax.swing.JButton();
                txtLm5 = new javax.swing.JTextField();
                jLabel31 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jLabel13 = new javax.swing.JLabel();
                fechaGA2 = new com.toedter.calendar.JDateChooser();
                chkCEsi = new javax.swing.JTextField();
                chkCEno = new javax.swing.JTextField();
                chkCEna = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jLabel44 = new javax.swing.JLabel();
                jLabel45 = new javax.swing.JLabel();
                txtDosis1 = new javax.swing.JTextField();
                jLabel46 = new javax.swing.JLabel();
                jLabel47 = new javax.swing.JLabel();
                jLabel48 = new javax.swing.JLabel();
                jLabel33 = new javax.swing.JLabel();
                txtLm7 = new javax.swing.JTextField();
                txtT7 = new javax.swing.JTextField();
                txtLm8 = new javax.swing.JTextField();
                jLabel49 = new javax.swing.JLabel();
                fechaGA3 = new com.toedter.calendar.JDateChooser();
                jLabel50 = new javax.swing.JLabel();
                txtDosis2 = new javax.swing.JTextField();
                jLabel34 = new javax.swing.JLabel();
                txtLm9 = new javax.swing.JTextField();
                txtT8 = new javax.swing.JTextField();
                txtLm10 = new javax.swing.JTextField();
                jLabel51 = new javax.swing.JLabel();
                fechaGA4 = new com.toedter.calendar.JDateChooser();
                jLabel52 = new javax.swing.JLabel();
                txtDosis3 = new javax.swing.JTextField();
                jPanel4 = new javax.swing.JPanel();
                txtDosis4 = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                txtDosis5 = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                txtLp3 = new javax.swing.JTextField();
                jLabel53 = new javax.swing.JLabel();
                jLabel54 = new javax.swing.JLabel();
                txtLp4 = new javax.swing.JTextField();
                jLabel55 = new javax.swing.JLabel();
                txtLp5 = new javax.swing.JTextField();
                jLabel56 = new javax.swing.JLabel();
                txtLp6 = new javax.swing.JTextField();
                jLabel57 = new javax.swing.JLabel();
                txtLp7 = new javax.swing.JTextField();
                jLabel58 = new javax.swing.JLabel();
                txtLp8 = new javax.swing.JTextField();
                jPanel5 = new javax.swing.JPanel();
                txtTa1 = new javax.swing.JTextField();
                txtTa5 = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel59 = new javax.swing.JLabel();
                jLabel60 = new javax.swing.JLabel();
                txtTa6 = new javax.swing.JTextField();
                jLabel61 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel62 = new javax.swing.JLabel();
                txtTa7 = new javax.swing.JTextField();
                jLabel63 = new javax.swing.JLabel();
                txtTa8 = new javax.swing.JTextField();
                jLabel64 = new javax.swing.JLabel();
                txtDosis6 = new javax.swing.JTextField();
                jLabel65 = new javax.swing.JLabel();
                txtTa9 = new javax.swing.JTextField();
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

                FrmCie10.setMinimumSize(new java.awt.Dimension(750, 400));
                FrmCie10.setResizable(false);

                jPanel15.setBackground(new java.awt.Color(102, 102, 102));
                jPanel15.setPreferredSize(new java.awt.Dimension(500, 65));

                titulo7.setBackground(new java.awt.Color(153, 0, 51));
                titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                titulo7.setForeground(new java.awt.Color(255, 255, 255));
                titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                titulo7.setText("CIE 10");
                titulo7.setToolTipText("");
                titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel41.setForeground(new java.awt.Color(255, 255, 255));
                jLabel41.setText("Código CIE 10 / Diagnóstico Presuntivo");

                jPanel30.setBackground(new java.awt.Color(255, 255, 255));

                T7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T7MouseClicked(evt);
                    }
                });

                txtBuscarCie10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                txtBuscarCie10.setBorder(null);
                txtBuscarCie10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCie10CaretUpdate(evt);
                    }
                });
                txtBuscarCie10.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarCie10KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
                jPanel30.setLayout(jPanel30Layout);
                jPanel30Layout.setHorizontalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtBuscarCie10, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(T7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel30Layout.setVerticalGroup(
                    jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                                .addComponent(T7)
                                .addGap(4, 4, 4))
                            .addComponent(txtBuscarCie10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                );

                jPanel33.setBackground(new java.awt.Color(41, 127, 184));

                javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                jPanel33.setLayout(jPanel33Layout);
                jPanel33Layout.setHorizontalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 750, Short.MAX_VALUE)
                );
                jPanel33Layout.setVerticalGroup(
                    jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 16, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(449, 449, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titulo7)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );

                jScrollPane4.setBorder(null);

                tbCiePresun.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbCiePresun.setGridColor(new java.awt.Color(255, 255, 255));
                tbCiePresun.setRowHeight(25);
                tbCiePresun.setSelectionBackground(new java.awt.Color(50, 151, 219));
                tbCiePresun.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbCiePresunMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbCiePresunMousePressed(evt);
                    }
                });
                tbCiePresun.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbCiePresunKeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(tbCiePresun);

                javax.swing.GroupLayout FrmCie10Layout = new javax.swing.GroupLayout(FrmCie10.getContentPane());
                FrmCie10.getContentPane().setLayout(FrmCie10Layout);
                FrmCie10Layout.setHorizontalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                );
                FrmCie10Layout.setVerticalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrmCie10Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                );

                setBackground(new java.awt.Color(255, 255, 255));
                setBorder(javax.swing.BorderFactory.createCompoundBorder());
                setVisible(true);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                var.setText("1");

                chkSP.setEditable(false);
                chkSP.setBackground(new java.awt.Color(204, 204, 204));
                chkSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                chkSP.setForeground(new java.awt.Color(102, 102, 102));
                chkSP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                chkSP.setBorder(null);
                chkSP.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                chkSP.setPreferredSize(new java.awt.Dimension(28, 28));
                chkSP.setSelectionColor(new java.awt.Color(204, 204, 204));
                chkSP.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        chkSPCaretUpdate(evt);
                    }
                });
                chkSP.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        chkSPMouseClicked(evt);
                    }
                });

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Sin Patologías");

                jPanel3.setBackground(new java.awt.Color(39, 174, 97));

                jScrollPane5.setBorder(null);

                tbPatologias.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbPatologias.setGridColor(new java.awt.Color(255, 255, 255));
                tbPatologias.setRowHeight(25);
                tbPatologias.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbPatologias.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbPatologiasMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbPatologiasMousePressed(evt);
                    }
                });
                tbPatologias.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbPatologiasKeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tbPatologias);

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(255, 255, 255));
                jLabel29.setText("CIE 10");

                jPanel20.setBackground(new java.awt.Color(255, 255, 255));
                jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCIE10.setEditable(false);
                txtCIE10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                txtCIE10.setForeground(new java.awt.Color(102, 102, 102));
                txtCIE10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtCIE10.setBorder(null);
                txtCIE10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCIE10CaretUpdate(evt);
                    }
                });

                btnBuscarNino2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino2.setMnemonic('B');
                btnBuscarNino2.setToolTipText("");
                btnBuscarNino2.setBorderPainted(false);
                btnBuscarNino2.setContentAreaFilled(false);
                btnBuscarNino2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino2.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarNino2MouseClicked(evt);
                    }
                });
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
                        .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("Fecha");

                fechaGA.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA.setDateFormatString("dd-MM-yyyy");

                CARGAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                CARGAR.setForeground(new java.awt.Color(255, 255, 255));
                CARGAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar archivo-32.png"))); // NOI18N
                CARGAR.setText("Agregar");
                CARGAR.setBorder(null);
                CARGAR.setContentAreaFilled(false);
                CARGAR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                CARGAR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                CARGAR.setIconTextGap(30);
                CARGAR.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CARGARActionPerformed(evt);
                    }
                });

                txtLm5.setEditable(false);
                txtLm5.setBackground(new java.awt.Color(255, 255, 255));
                txtLm5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm5.setForeground(new java.awt.Color(102, 102, 102));
                txtLm5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm5.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm5.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm5CaretUpdate(evt);
                    }
                });
                txtLm5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm5MouseClicked(evt);
                    }
                });

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(255, 255, 255));
                jLabel31.setText("Otras Patologías");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel4))
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fechaGA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(CARGAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(txtLm5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(fechaGA, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLm5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(CARGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(102, 102, 102));
                jLabel13.setText("Referencias");

                fechaGA2.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA2.setDateFormatString("dd-MM-yyyy");

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

                txtLm7.setEditable(false);
                txtLm7.setBackground(new java.awt.Color(255, 255, 255));
                txtLm7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm7.setForeground(new java.awt.Color(102, 102, 102));
                txtLm7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm7.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm7.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm7CaretUpdate(evt);
                    }
                });
                txtLm7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm7MouseClicked(evt);
                    }
                });

                txtT7.setEditable(false);
                txtT7.setBackground(new java.awt.Color(255, 204, 51));
                txtT7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtT7.setForeground(new java.awt.Color(102, 102, 102));
                txtT7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtT7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtT7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtT7.setPreferredSize(new java.awt.Dimension(18, 18));
                txtT7.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtT7CaretUpdate(evt);
                    }
                });
                txtT7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtT7MouseClicked(evt);
                    }
                });

                txtLm8.setEditable(false);
                txtLm8.setBackground(new java.awt.Color(255, 255, 255));
                txtLm8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm8.setForeground(new java.awt.Color(102, 102, 102));
                txtLm8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm8.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm8.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm8CaretUpdate(evt);
                    }
                });
                txtLm8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm8MouseClicked(evt);
                    }
                });

                jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel49.setForeground(new java.awt.Color(51, 51, 51));
                jLabel49.setText("Fecha");

                fechaGA3.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA3.setDateFormatString("dd-MM-yyyy");

                jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel50.setForeground(new java.awt.Color(51, 51, 51));
                jLabel50.setText("Establecimiento de traslado");

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

                jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel34.setForeground(new java.awt.Color(51, 51, 51));
                jLabel34.setText("Apoyo al diagnostíco");

                txtLm9.setEditable(false);
                txtLm9.setBackground(new java.awt.Color(255, 255, 255));
                txtLm9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm9.setForeground(new java.awt.Color(102, 102, 102));
                txtLm9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm9.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm9.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm9CaretUpdate(evt);
                    }
                });
                txtLm9.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm9MouseClicked(evt);
                    }
                });

                txtT8.setEditable(false);
                txtT8.setBackground(new java.awt.Color(255, 204, 51));
                txtT8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtT8.setForeground(new java.awt.Color(102, 102, 102));
                txtT8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtT8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtT8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtT8.setPreferredSize(new java.awt.Dimension(18, 18));
                txtT8.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtT8CaretUpdate(evt);
                    }
                });
                txtT8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtT8MouseClicked(evt);
                    }
                });

                txtLm10.setEditable(false);
                txtLm10.setBackground(new java.awt.Color(255, 255, 255));
                txtLm10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm10.setForeground(new java.awt.Color(102, 102, 102));
                txtLm10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm10.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm10CaretUpdate(evt);
                    }
                });
                txtLm10.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm10MouseClicked(evt);
                    }
                });

                jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel51.setForeground(new java.awt.Color(51, 51, 51));
                jLabel51.setText("Fecha");

                fechaGA4.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA4.setDateFormatString("dd-MM-yyyy");

                jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel52.setForeground(new java.awt.Color(51, 51, 51));
                jLabel52.setText("Establecimiento de traslado");

                txtDosis3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis3.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis3.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis3CaretUpdate(evt);
                    }
                });
                txtDosis3.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis3KeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(29, 29, 29)
                                .addComponent(txtLm9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(txtT8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(456, 456, 456)
                                .addComponent(txtDosis3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(87, 87, 87)
                                        .addComponent(txtLm7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel46)
                                            .addComponent(chkCEsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtT7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkCEno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(79, 79, 79)
                                                .addComponent(jLabel44)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaGA2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel48))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDosis1))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(chkCEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLm8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLm10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel51)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaGA4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel52)
                                                .addGap(362, 362, 362))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jLabel49)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaGA3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel50)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDosis2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
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
                                .addComponent(txtDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fechaGA2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLm7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtT7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLm8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(jLabel49))
                            .addComponent(fechaGA3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel50)
                                .addComponent(txtDosis2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLm9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtT8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLm10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34)
                                .addComponent(jLabel51))
                            .addComponent(fechaGA4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52)
                                .addComponent(txtDosis3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(23, Short.MAX_VALUE))
                );

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                txtDosis4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis4.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis4.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis4CaretUpdate(evt);
                    }
                });
                txtDosis4.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis4KeyTyped(evt);
                    }
                });

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel1.setText("PSICOPROFILIAXIS");

                txtDosis5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis5.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis5.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis5CaretUpdate(evt);
                    }
                });
                txtDosis5.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis5KeyTyped(evt);
                    }
                });

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel2.setText("ESTIMULACIÓN PRE NATAL");

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setText("PLAN DE PARTO");

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setText("<html>ALOJADA EN<BR>CASA DE ESPERA<HTML>");

                txtLp3.setEditable(false);
                txtLp3.setBackground(new java.awt.Color(255, 255, 255));
                txtLp3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp3.setForeground(new java.awt.Color(102, 102, 102));
                txtLp3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp3.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp3.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp3CaretUpdate(evt);
                    }
                });
                txtLp3.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp3MouseClicked(evt);
                    }
                });

                jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel53.setForeground(new java.awt.Color(51, 51, 51));
                jLabel53.setText("SI");

                jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel54.setForeground(new java.awt.Color(51, 51, 51));
                jLabel54.setText("No");

                txtLp4.setEditable(false);
                txtLp4.setBackground(new java.awt.Color(255, 255, 255));
                txtLp4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp4.setForeground(new java.awt.Color(102, 102, 102));
                txtLp4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp4.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp4.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp4CaretUpdate(evt);
                    }
                });
                txtLp4.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp4MouseClicked(evt);
                    }
                });

                jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel55.setForeground(new java.awt.Color(51, 51, 51));
                jLabel55.setText("No Aplica");

                txtLp5.setEditable(false);
                txtLp5.setBackground(new java.awt.Color(255, 255, 255));
                txtLp5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp5.setForeground(new java.awt.Color(102, 102, 102));
                txtLp5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp5.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp5.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp5CaretUpdate(evt);
                    }
                });
                txtLp5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp5MouseClicked(evt);
                    }
                });

                jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel56.setForeground(new java.awt.Color(51, 51, 51));
                jLabel56.setText("SI");

                txtLp6.setEditable(false);
                txtLp6.setBackground(new java.awt.Color(255, 255, 255));
                txtLp6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp6.setForeground(new java.awt.Color(102, 102, 102));
                txtLp6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp6.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp6CaretUpdate(evt);
                    }
                });
                txtLp6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp6MouseClicked(evt);
                    }
                });

                jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel57.setForeground(new java.awt.Color(51, 51, 51));
                jLabel57.setText("No");

                txtLp7.setEditable(false);
                txtLp7.setBackground(new java.awt.Color(255, 255, 255));
                txtLp7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp7.setForeground(new java.awt.Color(102, 102, 102));
                txtLp7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp7.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp7.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp7CaretUpdate(evt);
                    }
                });
                txtLp7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp7MouseClicked(evt);
                    }
                });

                jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel58.setForeground(new java.awt.Color(51, 51, 51));
                jLabel58.setText("No Aplica");

                txtLp8.setEditable(false);
                txtLp8.setBackground(new java.awt.Color(255, 255, 255));
                txtLp8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLp8.setForeground(new java.awt.Color(102, 102, 102));
                txtLp8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLp8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLp8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLp8.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLp8.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLp8CaretUpdate(evt);
                    }
                });
                txtLp8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLp8MouseClicked(evt);
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
                                            .addComponent(txtDosis4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDosis5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel53)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtLp3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel54)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtLp4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLp5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLp6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLp7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLp8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(235, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtDosis4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtDosis5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53)
                            .addComponent(txtLp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54)
                            .addComponent(txtLp5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtLp6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56)
                                .addComponent(txtLp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel57)
                                .addComponent(txtLp8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel58)))
                        .addContainerGap(16, Short.MAX_VALUE))
                );

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel7.setText("HCMP");

                jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel59.setForeground(new java.awt.Color(51, 51, 51));
                jLabel59.setText("Atención Prenatal");

                jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel60.setForeground(new java.awt.Color(51, 51, 51));
                jLabel60.setText("Aborto");

                txtTa6.setEditable(false);
                txtTa6.setBackground(new java.awt.Color(255, 255, 255));
                txtTa6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtTa6.setForeground(new java.awt.Color(102, 102, 102));
                txtTa6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTa6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTa6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtTa6.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTa6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTa6CaretUpdate(evt);
                    }
                });
                txtTa6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTa6MouseClicked(evt);
                    }
                });

                jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel61.setForeground(new java.awt.Color(51, 51, 51));
                jLabel61.setText("Aborto");

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setText("PRODUCTO DE LA CONCEPCIÓN");

                jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel62.setForeground(new java.awt.Color(51, 51, 51));
                jLabel62.setText("Hijo Unico");

                txtTa7.setEditable(false);
                txtTa7.setBackground(new java.awt.Color(255, 255, 255));
                txtTa7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtTa7.setForeground(new java.awt.Color(102, 102, 102));
                txtTa7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTa7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTa7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtTa7.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTa7.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTa7CaretUpdate(evt);
                    }
                });
                txtTa7.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTa7MouseClicked(evt);
                    }
                });

                jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel63.setForeground(new java.awt.Color(51, 51, 51));
                jLabel63.setText("Embarazo Multiple");

                txtTa8.setEditable(false);
                txtTa8.setBackground(new java.awt.Color(255, 204, 51));
                txtTa8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtTa8.setForeground(new java.awt.Color(102, 102, 102));
                txtTa8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTa8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTa8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtTa8.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTa8.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTa8CaretUpdate(evt);
                    }
                });
                txtTa8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTa8MouseClicked(evt);
                    }
                });

                jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel64.setForeground(new java.awt.Color(51, 51, 51));
                jLabel64.setText("Orden");

                txtDosis6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtDosis6.setForeground(new java.awt.Color(102, 102, 102));
                txtDosis6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtDosis6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtDosis6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtDosis6CaretUpdate(evt);
                    }
                });
                txtDosis6.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDosis6KeyTyped(evt);
                    }
                });

                jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel65.setForeground(new java.awt.Color(51, 51, 51));
                jLabel65.setText("Aborto");

                txtTa9.setEditable(false);
                txtTa9.setBackground(new java.awt.Color(255, 204, 51));
                txtTa9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtTa9.setForeground(new java.awt.Color(102, 102, 102));
                txtTa9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtTa9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtTa9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtTa9.setPreferredSize(new java.awt.Dimension(18, 18));
                txtTa9.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtTa9CaretUpdate(evt);
                    }
                });
                txtTa9.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtTa9MouseClicked(evt);
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
                                .addComponent(txtTa5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTa6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTa1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtTa7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTa8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDosis6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTa9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(50, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel59)
                            .addComponent(txtTa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60)
                            .addComponent(txtTa6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61)
                            .addComponent(txtTa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel65)
                                .addComponent(txtTa9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel62)
                                .addComponent(txtTa7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel63)
                                .addComponent(txtTa8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel64)
                                .addComponent(txtDosis6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(19, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(30, 30, 30)
                                .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(642, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
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
                        .addComponent(btnCaccnelar)
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
                                .addGap(0, 0, Short.MAX_VALUE))
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

    private void chkSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkSPCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPCaretUpdate

    private void chkSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkSPMouseClicked
          if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
       if(chkSP.getText().equals("") && evt.getClickCount()==1){
           chkSP.setText("X");
           chkNo.setText(""); 
  
        }else
        if(chkSP.getText().equals("X") && evt.getClickCount()==1){
           chkSP.setText(""); 
           chkNo.setText(""); 

        }
          }
    }//GEN-LAST:event_chkSPMouseClicked

    private void chkCEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnoCaretUpdate

    private void txtTa5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa5CaretUpdate

    private void chkCEsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEsiCaretUpdate

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

    private void chkCEnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEnoMouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCEno.getText().equals("") && evt.getClickCount()==1){
           chkCEno.setText("X");
           txtT1.setText("");
           txtT2.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }else
        if(chkCEno.getText().equals("X") && evt.getClickCount()==1){
           chkCEno.setText(""); 
           txtT1.setText("");
           txtT2.setText("");
           txtT4.setText("");
           txtT5.setText("");
           txtT6.setText("");
        }
        }
    }//GEN-LAST:event_chkCEnoMouseClicked

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

    private void chkCEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEsiMouseClicked
        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
        if(chkCEsi.getText().equals("") && evt.getClickCount()==1){
           chkCEsi.setText("X");
           txtLm2.setText(""); 
           txtLm1.setText(""); 
           txtLm4.setText(""); 
        }else
        if(chkCEsi.getText().equals("X") && evt.getClickCount()==1){
           chkCEsi.setText(""); 
           txtLm2.setText(""); 
           txtLm1.setText(""); 
           txtLm4.setText(""); 
        }
        }
    }//GEN-LAST:event_chkCEsiMouseClicked

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtCarnetPerinatalHo ETBUSCAR = new ConsultorioExtCarnetPerinatalHo();
        ETBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tbCiePresunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMouseClicked
        if(evt.getClickCount()==2){
            enviarDiagnosticos(cie10);
        }
    }//GEN-LAST:event_tbCiePresunMouseClicked

    private void tbCiePresunMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresunMousePressed

    private void tbCiePresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresunKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tbCiePresun.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            txtBuscarCie10.requestFocus();
            tbCiePresun.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDiagnosticos(cie10);
        }

    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void tbPatologiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologiasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologiasMouseClicked

    private void tbPatologiasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologiasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologiasMousePressed

    private void tbPatologiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPatologiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologiasKeyPressed

    private void txtCIE10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCIE10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCIE10CaretUpdate

    private void btnBuscarNino2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarNino2MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_btnBuscarNino2MouseClicked

    private void btnBuscarNino2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarNino2ActionPerformed

    private void CARGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CARGARActionPerformed

        if(tgp==1){
            Caja_Preventa CSE = new Caja_Preventa();
            SE.setText(CSE.CodSE(NCP.getText()));

            //        Medicos.setVisible(true);
            //        listarMedicos1();
            //////////////////

            preventas.dispose();
        }

    }//GEN-LAST:event_CARGARActionPerformed

    private void txtLm5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5CaretUpdate

    private void txtLm5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5MouseClicked

    private void chkCEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnaCaretUpdate

    private void chkCEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEnaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnaMouseClicked

    private void txtDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis1CaretUpdate

    private void txtDosis1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis1KeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtDosis1KeyTyped

    private void txtLm7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm7CaretUpdate

    private void txtLm7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm7MouseClicked

    private void txtT7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT7CaretUpdate

    private void txtT7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT7MouseClicked

    private void txtLm8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm8CaretUpdate

    private void txtLm8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm8MouseClicked

    private void txtDosis2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis2CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis2CaretUpdate

    private void txtDosis2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis2KeyTyped

    private void txtLm9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm9CaretUpdate

    private void txtLm9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm9MouseClicked

    private void txtT8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtT8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT8CaretUpdate

    private void txtT8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtT8MouseClicked

    private void txtLm10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm10CaretUpdate

    private void txtLm10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm10MouseClicked

    private void txtDosis3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis3CaretUpdate

    private void txtDosis3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis3KeyTyped

    private void txtDosis4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis4CaretUpdate

    private void txtDosis4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis4KeyTyped

    private void txtDosis5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis5CaretUpdate

    private void txtDosis5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis5KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis5KeyTyped

    private void txtLp3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp3CaretUpdate

    private void txtLp3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp3MouseClicked

    private void txtLp4CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp4CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp4CaretUpdate

    private void txtLp4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp4MouseClicked

    private void txtLp5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp5CaretUpdate

    private void txtLp5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp5MouseClicked

    private void txtLp6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp6CaretUpdate

    private void txtLp6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp6MouseClicked

    private void txtLp7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp7CaretUpdate

    private void txtLp7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp7MouseClicked

    private void txtLp8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLp8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp8CaretUpdate

    private void txtLp8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLp8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLp8MouseClicked

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

    private void txtTa1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa1CaretUpdate

    private void txtTa6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa6CaretUpdate

    private void txtTa6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa6MouseClicked

    private void txtTa7CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa7CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa7CaretUpdate

    private void txtTa7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa7MouseClicked

    private void txtTa8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa8CaretUpdate

    private void txtTa8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa8MouseClicked

    private void txtDosis6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis6CaretUpdate

    private void txtDosis6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis6KeyTyped

    private void txtTa9CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTa9CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa9CaretUpdate

    private void txtTa9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTa9MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CARGAR;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkCEna;
    public static javax.swing.JTextField chkCEno;
    public static javax.swing.JTextField chkCEsi;
    public static javax.swing.JTextField chkSP;
    public static com.toedter.calendar.JDateChooser fechaGA;
    public static com.toedter.calendar.JDateChooser fechaGA2;
    public static com.toedter.calendar.JDateChooser fechaGA3;
    public static com.toedter.calendar.JDateChooser fechaGA4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JLabel lblIdGA;
    public static javax.swing.JLabel lblMadreGA;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JTable tbPatologias;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtDosis1;
    public static javax.swing.JTextField txtDosis2;
    public static javax.swing.JTextField txtDosis3;
    public static javax.swing.JTextField txtDosis4;
    public static javax.swing.JTextField txtDosis5;
    public static javax.swing.JTextField txtDosis6;
    public static javax.swing.JTextField txtLm10;
    public static javax.swing.JTextField txtLm5;
    public static javax.swing.JTextField txtLm7;
    public static javax.swing.JTextField txtLm8;
    public static javax.swing.JTextField txtLm9;
    public static javax.swing.JTextField txtLp3;
    public static javax.swing.JTextField txtLp4;
    public static javax.swing.JTextField txtLp5;
    public static javax.swing.JTextField txtLp6;
    public static javax.swing.JTextField txtLp7;
    public static javax.swing.JTextField txtLp8;
    public static javax.swing.JTextField txtT7;
    public static javax.swing.JTextField txtT8;
    public static javax.swing.JTextField txtTa1;
    public static javax.swing.JTextField txtTa5;
    public static javax.swing.JTextField txtTa6;
    public static javax.swing.JTextField txtTa7;
    public static javax.swing.JTextField txtTa8;
    public static javax.swing.JTextField txtTa9;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
