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
                jPanel5 = new javax.swing.JPanel();
                btneliminar = new javax.swing.JButton();
                lblIdCIE10 = new javax.swing.JLabel();
                lblDescripcion = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                lblIdDetalle = new javax.swing.JLabel();
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
                lblIdActoMedico = new javax.swing.JLabel();
                mensaje = new javax.swing.JPanel();
                men = new javax.swing.JLabel();
                b = new javax.swing.JButton();
                b1 = new javax.swing.JButton();
                lblMadre = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                lblActoMedico = new javax.swing.JLabel();
                lblFP = new javax.swing.JLabel();

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
                jPanel3.setMinimumSize(new java.awt.Dimension(922, 283));

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

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMadre, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMadre)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnCaccnelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaccnelarActionPerformed

    }//GEN-LAST:event_btnCaccnelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CARGAR;
    public static javax.swing.JTextField ChkAnalf1;
    public static javax.swing.JTextField ChkEdad1;
    private javax.swing.JDialog FrmCie10;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel37;
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
    private javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JButton noeli;
    private javax.swing.JTable tbCiePresun;
    public static javax.swing.JTable tbPatologias;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCIE10;
    public static javax.swing.JTextField txtLm5;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
