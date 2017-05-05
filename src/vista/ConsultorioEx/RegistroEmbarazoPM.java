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
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        ConsultorioExtCarnetPerinatalPmcie10 CCDBUSCAR = new ConsultorioExtCarnetPerinatalPmcie10();
        CCDBUSCAR.cargarDatosCie10("", tbCiePresun);
        
    }
 public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
 public void enviarDiagnosticos(){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        txtCIE10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
        lblIdCIE10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 0)));
    }
 
 public void HabilitarDatos(){
     fecha1.setEnabled(true);
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

  public void GuardarCIE10( ){
        
    ConsultorioExtCarnetPerinatalPmcie10 CXRsPM= new ConsultorioExtCarnetPerinatalPmcie10();
    ConsultorioExtCarnetPerinatalPmcie10 CXRsPM2 = new ConsultorioExtCarnetPerinatalPmcie10();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    
           if(lblMant.getText().equals("U"))
            CXRsPM.setIdPm(Integer.parseInt(lblIdPM.getText()));
            CXRsPM.setIdcp(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));
            CXRsPM.setId_cie10(Integer.parseInt(lblIdCIE10.getText()));
            CXRsPM.setCie10fecha(determinarFecha(fecha1));
            CXRsPM.setDescripcion(lblDescripcion.getText());
            CXRsPM.setCodUsu(adEmerCab.codUsuario(lblusu.getText()));
            
                    if(CXRsPM.mantenimientoConsultorioExtCarnetPerinatalPM(lblMant.getText())==true){
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
                    CXRsPM2.listarRegistro(RegistroEmbarazoPrincipal.lblId.getText(),tbPatologias);     
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
 
                    CXRsPM2.listarRegistro(RegistroEmbarazoPrincipal.lblId.getText(),tbPatologias);        
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
  
  public void Guardar( ){
        
    ConsultorioExtCarnetPerinatalReferencias CXRsR= new ConsultorioExtCarnetPerinatalReferencias();
    ConsultorioExtCarnetPerinatalReferencias CXRsR2 = new ConsultorioExtCarnetPerinatalReferencias();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
    
           if(lblMant.getText().equals("U"))
            CXRsR.setIdRef(Integer.parseInt(lblIdGA.getText()));
            CXRsR.setCP_ID(Integer.parseInt(RegistroEmbarazoPrincipal.lblId.getText()));

            ////////////////////////////////////////////////////CONSULTA EXTERNA
            if(chkCEsi.getText().equals("X"))
                CXRsR.setCex("S");
            else
            if(chkCEno.getText().equals("X"))
                CXRsR.setCex("S");
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
                CXRsR.setEme("S");
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
                CXRsR.setApoyo("S");
            else
            if(chkADna.getText().equals("X"))
                CXRsR.setApoyo("A");
            else
                CXRsR.setApoyo("");

            CXRsR.setAPOYOFecha(determinarFecha(fechaE));
            CXRsR.setAPOYOEstable(txtEstablecimiento2.getText());
            
            
            
            

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
                btnBuscarCIE10 = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                fecha1 = new com.toedter.calendar.JDateChooser();
                CARGAR = new javax.swing.JButton();
                txtLm5 = new javax.swing.JTextField();
                jLabel31 = new javax.swing.JLabel();
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
                chkADsi = new javax.swing.JTextField();
                chkADno = new javax.swing.JTextField();
                chkADna = new javax.swing.JTextField();
                jLabel51 = new javax.swing.JLabel();
                fechaAD = new com.toedter.calendar.JDateChooser();
                jLabel52 = new javax.swing.JLabel();
                txtEstablecimiento3 = new javax.swing.JTextField();
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
                lblIdCIE10 = new javax.swing.JLabel();
                lblDescripcion = new javax.swing.JLabel();
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
                lblIdPM = new javax.swing.JLabel();
                lblMant = new javax.swing.JLabel();
                mensaje = new javax.swing.JPanel();
                men = new javax.swing.JLabel();
                b = new javax.swing.JButton();
                b1 = new javax.swing.JButton();
                lblMadre = new javax.swing.JLabel();

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
                chkSP.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        chkSPActionPerformed(evt);
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

                btnBuscarCIE10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarCIE10.setMnemonic('B');
                btnBuscarCIE10.setToolTipText("");
                btnBuscarCIE10.setBorderPainted(false);
                btnBuscarCIE10.setContentAreaFilled(false);
                btnBuscarCIE10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarCIE10.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        btnBuscarCIE10MouseClicked(evt);
                    }
                });
                btnBuscarCIE10.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarCIE10ActionPerformed(evt);
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
                        .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("Fecha");

                fecha1.setBackground(new java.awt.Color(39, 174, 97));
                fecha1.setDateFormatString("dd-MM-yyyy");

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
                txtLm5.setBorder(null);
                txtLm5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm5.setDisabledTextColor(new java.awt.Color(102, 102, 102));
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
                txtLm5.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtLm5ActionPerformed(evt);
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
                                            .addComponent(fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(chkADsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(chkADno, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(456, 456, 456)
                                .addComponent(txtEstablecimiento3))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(87, 87, 87)
                                        .addComponent(chkEsi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(chkEno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkCEno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(79, 79, 79)
                                                .addComponent(jLabel44)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel48))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEstablecimiento1))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(chkCEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkEna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chkADna, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel51)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaAD, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel52)
                                                .addGap(362, 362, 362))
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(jLabel49)
                                                .addGap(18, 18, 18)
                                                .addComponent(fechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel50)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtEstablecimiento2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))))))
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
                                .addComponent(txtEstablecimiento1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fechaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkEsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkEno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkEna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(jLabel49))
                            .addComponent(fechaE, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel50)
                                .addComponent(txtEstablecimiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(chkADsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkADno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkADna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34)
                                .addComponent(jLabel51))
                            .addComponent(fechaAD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52)
                                .addComponent(txtEstablecimiento3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(23, Short.MAX_VALUE))
                );

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

                lblIdCIE10.setText("jLabel10");

                lblDescripcion.setText("Patologías");

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
                                .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(lblIdCIE10)
                                .addGap(18, 18, 18)
                                .addComponent(lblDescripcion))
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
                            .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdCIE10)
                            .addComponent(lblDescripcion))
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
                                    .addComponent(lblIdPM, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMant))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addComponent(lblMant)
                        .addGap(31, 31, 31)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCaccnelar)
                        .addGap(47, 47, 47)
                        .addComponent(lblIdPM, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(lblMadre)
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
       if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
       if(chkSP.getText().equals("") && evt.getClickCount()==1){
           chkSP.setText("X");
           jPanel3.setEnabled(true);
           
  
        }else
        if(chkSP.getText().equals("X") && evt.getClickCount()==1){
           chkSP.setText(""); 
           jPanel3.setEnabled(false);

        }
          }
    }//GEN-LAST:event_chkSPMouseClicked

    private void chkCEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnoCaretUpdate

    private void chkHCMPapCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPapCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPapCaretUpdate

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
       if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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

    private void chkHCMPapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPapMouseClicked
     
    }//GEN-LAST:event_chkHCMPapMouseClicked

    private void chkCEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEsiMouseClicked
     if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtCarnetPerinatalPmcie10 ETBUSCAR = new ConsultorioExtCarnetPerinatalPmcie10();
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
            enviarDiagnosticos();
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
            enviarDiagnosticos();
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

    private void btnBuscarCIE10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCIE10MouseClicked
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_btnBuscarCIE10MouseClicked

    private void btnBuscarCIE10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCIE10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarCIE10ActionPerformed

    private void CARGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CARGARActionPerformed
            GuardarCIE10(); 
    }//GEN-LAST:event_CARGARActionPerformed

    private void txtLm5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5CaretUpdate

    private void txtLm5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm5MouseClicked
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
       if(txtLm5.getText().equals("") && evt.getClickCount()==1){
           txtLm5.setText("X");
           lblDescripcion.setText("Otras Patologías");
           
  
        }else
        if(txtLm5.getText().equals("X") && evt.getClickCount()==1){
           txtLm5.setText(""); 
           lblDescripcion.setText("Patologías");

        }
          }
    }//GEN-LAST:event_txtLm5MouseClicked

    private void chkCEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkCEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkCEnaCaretUpdate

    private void chkCEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkCEnaMouseClicked
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        if (lblIdPM.getText().equals("")|| lblMant.getText().equals("U")){
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
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPsiMouseClicked

    private void chkPPnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkPPnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnoCaretUpdate

    private void chkPPnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPPnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnoMouseClicked

    private void chkPPnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkPPnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnaCaretUpdate

    private void chkPPnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkPPnaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPPnaMouseClicked

    private void chkACEsiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEsiCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEsiCaretUpdate

    private void chkACEsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEsiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEsiMouseClicked

    private void chkACEnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEnoCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnoCaretUpdate

    private void chkACEnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnoMouseClicked

    private void chkACEnaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkACEnaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnaCaretUpdate

    private void chkACEnaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkACEnaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkACEnaMouseClicked

    private void chkHCMPpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPpMouseClicked
//        if (lblIdGA.getText().equals("")|| lblMant.getText().equals("U")){
//            if(chkHCMPp.getText().equals("") && evt.getClickCount()==1){
//                chkHCMPp.setText("X");
//                txtTa2.setText("");
//                txtTa3.setText("");
//                txtTa4.setText("");
//                chkHCMPap.setText("");
//
//            }else
//            if(chkHCMPp.getText().equals("X") && evt.getClickCount()==1){
//                chkHCMPp.setText("");
//                txtTa2.setText("");
//                txtTa3.setText("");
//                txtTa4.setText("");
//                chkHCMPap.setText("");
//            }
//        }
    }//GEN-LAST:event_chkHCMPpMouseClicked

    private void chkHCMPpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPpCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPpCaretUpdate

    private void chkHCMPaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHCMPaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPaCaretUpdate

    private void chkHCMPaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHCMPaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHCMPaMouseClicked

    private void chkHUCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkHUCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHUCaretUpdate

    private void chkHUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkHUMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHUMouseClicked

    private void chkEMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkEMCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_chkEMCaretUpdate

    private void chkEMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkEMMouseClicked
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_chkAMouseClicked

    private void txtLm5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLm5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5ActionPerformed

    private void chkSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CARGAR;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarCIE10;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkA;
    public static javax.swing.JTextField chkACEna;
    public static javax.swing.JTextField chkACEno;
    public static javax.swing.JTextField chkACEsi;
    public static javax.swing.JTextField chkADna;
    public static javax.swing.JTextField chkADno;
    public static javax.swing.JTextField chkADsi;
    public static javax.swing.JTextField chkCEna;
    public static javax.swing.JTextField chkCEno;
    public static javax.swing.JTextField chkCEsi;
    public static javax.swing.JTextField chkEM;
    public static javax.swing.JTextField chkEna;
    public static javax.swing.JTextField chkEno;
    public static javax.swing.JTextField chkEsi;
    public static javax.swing.JTextField chkHCMPa;
    public static javax.swing.JTextField chkHCMPap;
    public static javax.swing.JTextField chkHCMPp;
    public static javax.swing.JTextField chkHU;
    public static javax.swing.JTextField chkPPna;
    public static javax.swing.JTextField chkPPno;
    public static javax.swing.JTextField chkPPsi;
    public static javax.swing.JTextField chkSP;
    public static com.toedter.calendar.JDateChooser fecha1;
    public static com.toedter.calendar.JDateChooser fechaAD;
    public static com.toedter.calendar.JDateChooser fechaCE;
    public static com.toedter.calendar.JDateChooser fechaE;
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
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblIdCIE10;
    public static javax.swing.JLabel lblIdPM;
    public static javax.swing.JLabel lblMadre;
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JTable tbPatologias;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtEstablecimiento1;
    public static javax.swing.JTextField txtEstablecimiento2;
    public static javax.swing.JTextField txtEstablecimiento3;
    public static javax.swing.JTextField txtEstimulacion;
    public static javax.swing.JTextField txtLm5;
    public static javax.swing.JTextField txtOrden;
    public static javax.swing.JTextField txtPsico;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
