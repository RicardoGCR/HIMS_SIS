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
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        
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
 
<<<<<<< HEAD
 
=======
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
            CXRsPM.setId_ActoMedico(Integer.parseInt(lblIdActoMedico.getText()));
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
  
    public void ELIMINARCIE10( ){
        
    ConsultorioExtCarnetPerinatalPmcie10 CXRsPE= new ConsultorioExtCarnetPerinatalPmcie10();
          
            CXRsPE.setIdPm(Integer.parseInt(lblIdDetalle.getText()));
                    if(CXRsPE.EliminarDetalle()){
                    jPanel4.setVisible(false);
                    jPanel5.setVisible(false);
                    mensaje.setVisible(true);
                    mensaje.setBackground(new Color(33,115,70)); 
                    men.setText("Registro Eliminado Correctamente");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);
                    tge=1;
                    CXRsPE.listarRegistro(RegistroEmbarazoPrincipal.lblId.getText(),tbPatologias);      

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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
<<<<<<< HEAD
            jLabel10 = new javax.swing.JLabel();
            jLabel25 = new javax.swing.JLabel();
            jLabel24 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            txtT5 = new javax.swing.JTextField();
            jLabel26 = new javax.swing.JLabel();
            jLabel19 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            txtLm4 = new javax.swing.JTextField();
            txtTa4 = new javax.swing.JTextField();
            jLabel22 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            txtTa1 = new javax.swing.JTextField();
            txtLm2 = new javax.swing.JTextField();
            jLabel11 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jLabel23 = new javax.swing.JLabel();
            txtLp1 = new javax.swing.JTextField();
            txtLm1 = new javax.swing.JTextField();
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
            chkSi = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbCiePresun1 = new javax.swing.JTable(){
=======
            var = new javax.swing.JLabel();
            chkSP = new javax.swing.JTextField();
            jLabel5 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbPatologias = new javax.swing.JTable(){
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jLabel29 = new javax.swing.JLabel();
                jPanel20 = new javax.swing.JPanel();
                txtCIE10 = new javax.swing.JTextField();
<<<<<<< HEAD
                btnBuscarNino2 = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                fechaGA = new com.toedter.calendar.JDateChooser();
                CARGAR = new javax.swing.JButton();
                txtLm5 = new javax.swing.JTextField();
                jLabel31 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jLabel13 = new javax.swing.JLabel();
                fechaGA2 = new com.toedter.calendar.JDateChooser();
                txtLm3 = new javax.swing.JTextField();
                txtT3 = new javax.swing.JTextField();
                txtLm6 = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jLabel44 = new javax.swing.JLabel();
                jLabel45 = new javax.swing.JLabel();
                txtDosis1 = new javax.swing.JTextField();
                jLabel46 = new javax.swing.JLabel();
                jLabel47 = new javax.swing.JLabel();
                jLabel48 = new javax.swing.JLabel();
=======
                btnBuscarCIE10 = new javax.swing.JButton();
                jLabel4 = new javax.swing.JLabel();
                fecha1 = new com.toedter.calendar.JDateChooser();
                CARGAR = new javax.swing.JButton();
                txtLm5 = new javax.swing.JTextField();
                jLabel31 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                btneliminar = new javax.swing.JButton();
                lblIdCIE10 = new javax.swing.JLabel();
                lblDescripcion = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                lblIdDetalle = new javax.swing.JLabel();
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
<<<<<<< HEAD
                lblIdGA = new javax.swing.JLabel();
                lblMant = new javax.swing.JLabel();
=======
                lblIdPM = new javax.swing.JLabel();
                lblMant = new javax.swing.JLabel();
                lblIdActoMedico = new javax.swing.JLabel();
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                mensaje = new javax.swing.JPanel();
                men = new javax.swing.JLabel();
                b = new javax.swing.JButton();
                b1 = new javax.swing.JButton();
<<<<<<< HEAD
                lblMadreGA = new javax.swing.JLabel();
=======
                lblMadre = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                lblActoMedico = new javax.swing.JLabel();
                lblFP = new javax.swing.JLabel();
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

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

<<<<<<< HEAD
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

                jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                jLabel15.setText("Incompleto ");

                jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel20.setForeground(new java.awt.Color(51, 51, 51));
                jLabel20.setText("No hubo");

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

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                jLabel26.setText("Domic.");

                jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel19.setForeground(new java.awt.Color(51, 51, 51));
                jLabel19.setText("No aplica");

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(102, 102, 102));
                jLabel14.setText("<html>Lactancia<br>Materna</br></span></html>");

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

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("6 meses a más");

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
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                    }
                });

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("Sin Patologías");

                jPanel3.setBackground(new java.awt.Color(39, 174, 97));
<<<<<<< HEAD

                jScrollPane5.setBorder(null);

                tbCiePresun1.setModel(new javax.swing.table.DefaultTableModel(
=======
                jPanel3.setMinimumSize(new java.awt.Dimension(922, 283));

                jScrollPane5.setBorder(null);

                tbPatologias.setModel(new javax.swing.table.DefaultTableModel(
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
<<<<<<< HEAD
                tbCiePresun1.setGridColor(new java.awt.Color(255, 255, 255));
                tbCiePresun1.setRowHeight(25);
                tbCiePresun1.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbCiePresun1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbCiePresun1MouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbCiePresun1MousePressed(evt);
                    }
                });
                tbCiePresun1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbCiePresun1KeyPressed(evt);
                    }
                });
                jScrollPane5.setViewportView(tbCiePresun1);
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

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

<<<<<<< HEAD
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
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
<<<<<<< HEAD
                        .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
=======
                        .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                );
                jPanel20Layout.setVerticalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
<<<<<<< HEAD
                            .addComponent(btnBuscarNino2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
=======
                            .addComponent(btnBuscarCIE10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                            .addComponent(txtCIE10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("Fecha");

<<<<<<< HEAD
                fechaGA.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA.setDateFormatString("dd-MM-yyyy");
=======
                fecha1.setBackground(new java.awt.Color(39, 174, 97));
                fecha1.setDateFormatString("dd-MM-yyyy");
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

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
<<<<<<< HEAD
                txtLm5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
=======
                txtLm5.setBorder(null);
                txtLm5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm5.setDisabledTextColor(new java.awt.Color(102, 102, 102));
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
<<<<<<< HEAD
=======
                txtLm5.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtLm5ActionPerformed(evt);
                    }
                });
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

                jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel31.setForeground(new java.awt.Color(255, 255, 255));
                jLabel31.setText("Otras Patologías");

<<<<<<< HEAD
=======
                jPanel5.setBackground(new java.awt.Color(255, 91, 70));

                btneliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                btneliminar.setText("Eliminar ");
                btneliminar.setContentAreaFilled(false);
                btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                btneliminar.setIconTextGap(30);
                btneliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneliminarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneliminar)
                        .addContainerGap())
                );

>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
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
=======
                            .addComponent(CARGAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLm5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
<<<<<<< HEAD
                            .addComponent(fechaGA, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
=======
                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLm5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
<<<<<<< HEAD
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(CARGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(102, 102, 102));
                jLabel13.setText("Referencias");

                fechaGA2.setBackground(new java.awt.Color(255, 255, 255));
                fechaGA2.setDateFormatString("dd-MM-yyyy");

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

                txtLm6.setEditable(false);
                txtLm6.setBackground(new java.awt.Color(255, 255, 255));
                txtLm6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtLm6.setForeground(new java.awt.Color(102, 102, 102));
                txtLm6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtLm6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtLm6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                txtLm6.setPreferredSize(new java.awt.Dimension(18, 18));
                txtLm6.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtLm6CaretUpdate(evt);
                    }
                });
                txtLm6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        txtLm6MouseClicked(evt);
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

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLm3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtLm6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaGA2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel45))
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDosis1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel48))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLm3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLm6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel44)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(fechaGA2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45)
                                .addComponent(txtDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(125, Short.MAX_VALUE))
                );

=======
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CARGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblIdCIE10.setText("jLabel10");

                lblDescripcion.setText("Patologías");

                jPanel4.setBackground(new java.awt.Color(255, 91, 70));

                Mensaje.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                Mensaje.setText("Desea Eliminar el Registro ?");

                eli.setForeground(new java.awt.Color(240, 240, 240));
                eli.setText("Si");
                eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                eli.setContentAreaFilled(false);
                eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                eli.setIconTextGap(30);
                eli.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        eliActionPerformed(evt);
                    }
                });

                noeli.setForeground(new java.awt.Color(240, 240, 240));
                noeli.setText("No");
                noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                noeli.setContentAreaFilled(false);
                noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                noeli.setIconTextGap(30);
                noeli.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        noeliActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Mensaje)
                        .addGap(82, 82, 82)
                        .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Mensaje)
                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                lblIdDetalle.setText("jLabel1");

>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtT6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtT5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtT4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                            .addComponent(txtTa2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(30, 30, 30)
                                        .addComponent(chkSi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(99, 99, 99)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 689, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(chkSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel9)
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
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtLm4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
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
                                            .addComponent(jLabel18))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
=======
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIdDetalle)
                                .addGap(84, 84, 84)
                                .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(30, 30, 30)
                                    .addComponent(chkSP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(52, 52, 52)
                                    .addComponent(lblIdCIE10)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblDescripcion))
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(795, Short.MAX_VALUE))
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
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdDetalle)
                            .addComponent(var))
                        .addContainerGap(19, Short.MAX_VALUE))
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
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblMant)
                                        .addGap(81, 81, 81)
                                        .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
<<<<<<< HEAD
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addComponent(lblMant)
=======
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMant)
                            .addComponent(lblIdActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                        .addGap(31, 31, 31)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCaccnelar)
                        .addGap(47, 47, 47)
<<<<<<< HEAD
                        .addComponent(lblIdGA, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
=======
                        .addComponent(lblIdPM, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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

<<<<<<< HEAD
                lblMadreGA.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
                lblMadreGA.setForeground(new java.awt.Color(12, 97, 81));
                lblMadreGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Embarazada Filled-60.png"))); // NOI18N
                lblMadreGA.setText("Martha Arias Torres");
                lblMadreGA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                lblMadreGA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMadreGA, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
=======
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
<<<<<<< HEAD
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblMadreGA)
                                .addGap(23, 23, 23)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
=======
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMadre)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
                                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD
    private void chkSiCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkSiCaretUpdate
=======
    private void chkSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_chkSPCaretUpdate
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPCaretUpdate

<<<<<<< HEAD
    private void chkSiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkSiMouseClicked
       
    }//GEN-LAST:event_chkSiMouseClicked

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
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

<<<<<<< HEAD
=======
        String variable;
        variable=var.getText();

       if(variable=="1"){
           
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

        
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
<<<<<<< HEAD
 
=======
 if (tge==3 || tge==1|| tge==9){
   mensaje.setVisible(false);  

   }
        
        if (lblMant.getText().equals("U")){
       

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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

<<<<<<< HEAD
    private void txtT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT3MouseClicked
      
    }//GEN-LAST:event_txtT3MouseClicked

    private void txtT4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT4MouseClicked
      
    }//GEN-LAST:event_txtT4MouseClicked

    private void txtT5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT5MouseClicked
     
    }//GEN-LAST:event_txtT5MouseClicked

    private void txtT6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtT6MouseClicked
     
    }//GEN-LAST:event_txtT6MouseClicked

    private void txtTa1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa1MouseClicked
        
    }//GEN-LAST:event_txtTa1MouseClicked

    private void txtTa2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTa2MouseClicked

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
=======
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
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
         int fila=tbPatologias.getSelectedRow();
             if(evt.getClickCount()==1){

               lblIdDetalle.setText(String.valueOf(tbPatologias.getValueAt(fila, 0)));  
               
               jPanel5.setVisible(true);
               jPanel4.setVisible(false);

           }
        
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

    private void txtLm5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLm5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5ActionPerformed

<<<<<<< HEAD
    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate

    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
      
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tbCiePresunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMouseClicked
       
    }//GEN-LAST:event_tbCiePresunMouseClicked

    private void tbCiePresunMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresunMousePressed

    private void tbCiePresunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresunKeyPressed
   
    }//GEN-LAST:event_tbCiePresunKeyPressed

    private void tbCiePresun1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresun1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresun1MouseClicked

    private void tbCiePresun1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresun1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresun1MousePressed

    private void tbCiePresun1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCiePresun1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCiePresun1KeyPressed

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

    }//GEN-LAST:event_CARGARActionPerformed

    private void txtLm5CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm5CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5CaretUpdate

    private void txtLm5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm5MouseClicked

    private void txtLm6CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtLm6CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm6CaretUpdate

    private void txtLm6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLm6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLm6MouseClicked

    private void txtDosis1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDosis1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDosis1CaretUpdate

    private void txtDosis1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDosis1KeyTyped
     
    }//GEN-LAST:event_txtDosis1KeyTyped
=======
    private void chkSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSPActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        ELIMINARCIE10();
       
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        jPanel5.setVisible(false);
        jPanel4.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        jPanel4.setVisible(true);
//        cargareliminar.setVisible(true);
//        cargareliminar.setBackground(new Color(255,91,70));
//        Mensaje.setText("Desea Eliminar este registro?");
//        eli.setText("Si");
//        eli.setVisible(true);
//        noeli.setText("No");
//        noeli.setVisible(true);
//        tge=6;

    }//GEN-LAST:event_btneliminarActionPerformed
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CARGAR;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JDialog FrmCie10;
<<<<<<< HEAD
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarNino2;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    public static javax.swing.JTextField chkCno;
    public static javax.swing.JTextField chkCsi;
    public static javax.swing.JTextField chkRno;
    public static javax.swing.JTextField chkRsi;
    public static javax.swing.JTextField chkSi;
    public static com.toedter.calendar.JDateChooser fechaGA;
    public static com.toedter.calendar.JDateChooser fechaGA2;
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
=======
    private javax.swing.JLabel Mensaje;
    private javax.swing.JLabel T7;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarCIE10;
    private javax.swing.JButton btnCaccnelar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    public static javax.swing.JTextField chkSP;
    private javax.swing.JButton eli;
    public static com.toedter.calendar.JDateChooser fecha1;
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
=======
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
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
    private javax.swing.JLabel jLabel5;
<<<<<<< HEAD
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
=======
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel37;
<<<<<<< HEAD
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JLabel lblIdGA;
    public static javax.swing.JLabel lblMadreGA;
=======
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JLabel lblActoMedico;
    private javax.swing.JLabel lblDescripcion;
    public static javax.swing.JLabel lblFP;
    public static javax.swing.JLabel lblIdActoMedico;
    private javax.swing.JLabel lblIdCIE10;
    private javax.swing.JLabel lblIdDetalle;
    public static javax.swing.JLabel lblIdPM;
    public static javax.swing.JLabel lblMadre;
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
<<<<<<< HEAD
    private javax.swing.JTable tbCiePresun;
    private javax.swing.JTable tbCiePresun1;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtDosis1;
    public static javax.swing.JTextField txtLm1;
    public static javax.swing.JTextField txtLm2;
    public static javax.swing.JTextField txtLm3;
    public static javax.swing.JTextField txtLm4;
    public static javax.swing.JTextField txtLm5;
    public static javax.swing.JTextField txtLm6;
    public static javax.swing.JTextField txtLp1;
    public static javax.swing.JTextField txtLp2;
    public static javax.swing.JTextField txtT3;
    public static javax.swing.JTextField txtT4;
    public static javax.swing.JTextField txtT5;
    public static javax.swing.JTextField txtT6;
    public static javax.swing.JTextField txtTa1;
    public static javax.swing.JTextField txtTa2;
    public static javax.swing.JTextField txtTa3;
    public static javax.swing.JTextField txtTa4;
    public static javax.swing.JTextField txtTa5;
=======
    private javax.swing.JButton noeli;
    private javax.swing.JTable tbCiePresun;
    public static javax.swing.JTable tbPatologias;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtLm5;
>>>>>>> 0fe3f63251de54eaaffe28f48ce8fa1bb9ba14e4
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
