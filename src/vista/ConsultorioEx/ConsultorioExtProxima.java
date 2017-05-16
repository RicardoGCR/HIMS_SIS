/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import modelos.ConsultorioEx.ConsultorioExtConsultorioCita;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;
import org.codehaus.groovy.tools.shell.util.SimpleCompletor;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtProxima extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    ConsultorioExtConsultorioCita Consulta = new ConsultorioExtConsultorioCita();
    byte tg;
    byte tge;
    /**
     * Creates new form ConsultorioExtProxima
     */
    public ConsultorioExtProxima() {
        initComponents();
        QuitarLaBarraTitulo();
        mensaje.setVisible(false);
        lblFecha1.setVisible(false);
        jPanel9.setVisible(false);
        lblNumeros.setVisible(false);
        lblFecha.setVisible(false);
        
        lblturno.setVisible(false);
        lblFechaCompleta.setVisible(false);
                
        
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        this.getContentPane().setBackground(new Color(214,217,223)); 
        repaint();   
    }
    
    public void GuardarPreventa( ){
         try {
    ConsultorioExtConsultorioCita CXRsR= new ConsultorioExtConsultorioCita();
    ConsultorioExtConsultorioCita CXRsR2= new ConsultorioExtConsultorioCita();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("E"))
            CXRsR.setId_Preventa(Integer.parseInt(lblIDPREVENTA.getText()));
            CXRsR.setId_hc(lblid_hc.getText());
            CXRsR.setCod_nomen("CN00078         ");
            CXRsR.setCITA_NRO(txNRO.getText());
            CXRsR.setTURNO_CITA(lblturno.getText());
            CXRsR.setMEDICO_CITA(lblMedico.getText());
            
            
            CXRsR.setCodUsu(adEmerCab.codUsuario(ConsultorioExt.lblusu.getText()));
                if(CXRsR.mantenimientoConsultorioExtPREVENTA(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){
                        System.out.println("PREVENTA CITA AGREGADO");
                        
                        
                        CXRsR2.ConsultoriosExtPREVENTAListar(lblid_hc.getText()); 
//                        CXRsR2.cargarDatosPreventa(lblid_hc.getText(),tbPatologias1); 
                        
                    }
                    if (lblMant.getText().equals("E")){
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(39,174,97)); 
                        men.setText("Registro Actualizado de forma correcta");
                        b1.setText("OK");
                        b1.setVisible(true);
                        b.setVisible(false);
//                        CXRsR2.CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN_RECETADEAS(lblID.getText(),tbPatologias1); 
                    }
                }else {
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;    
                }  
              } catch (Exception e) {
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
         }
    }
    
    public void Guardar( ){
         try {

    ConsultorioExtConsultorioCita CXRsR= new ConsultorioExtConsultorioCita();
    ConsultorioExtConsultorioCita CXRsR2= new ConsultorioExtConsultorioCita();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant1.getText().equals("U"))
            CXRsR.setIdCita(Integer.parseInt(lblIDCITA.getText()));
            CXRsR.setIdConsultorioEx(Integer.parseInt(lblID.getText()));
            CXRsR.setFecha(lblFechaCompleta.getText());
            CXRsR.setTurno(lblturno.getText());
            CXRsR.setMedico(lblMedico.getText());
            CXRsR.setId_Preventa(Integer.parseInt(lblIDPREVENTA.getText()));
            CXRsR.setCodUsu(adEmerCab.codUsuario(ConsultorioExt.lblusu.getText()));

            
                if(CXRsR.mantenimientoConsultorioExtCita("I")==true){
                    if (lblMant1.getText().equals("I")){
                        mensaje.setVisible(true);
                    mensaje.setBackground(new Color(39,174,97)); 
                    men.setText("Cita Reservada");
                    b.setText("OK");
                    b.setVisible(true);
                    b1.setVisible(false);

                    btnGuardar.setEnabled(false);
                    btneditar.setEnabled(true);

                    tge=1;
//                    CXRsR2.ConsultoriosExtOrdenListar(lblID.getText());
                    }
                    if (lblMant.getText().equals("U")){
                   
                        System.out.println("Actualizado orden");
                    tge=9;
//                    CXRsR2.ConsultoriosExtOrdenListar(lblID.getText());
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
              } catch (Exception e) {
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btneditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblIDPREVENTA = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblid_hc = new javax.swing.JLabel();
        lblMant = new javax.swing.JLabel();
        lblMant1 = new javax.swing.JLabel();
        var = new javax.swing.JLabel();
        lblIDCITA = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        JD_Fecha = new com.toedter.calendar.JCalendar();
        mensaje = new javax.swing.JPanel();
        men = new javax.swing.JLabel();
        b = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTurnos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel4 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            lblFecha1 = new javax.swing.JLabel();
            lblNumeros = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            txNRO = new javax.swing.JTextField();
            lblturno = new javax.swing.JLabel();
            lblFechaCompleta = new javax.swing.JLabel();
            lblMedico = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();

            setBorder(javax.swing.BorderFactory.createCompoundBorder());
            setVisible(true);

            jPanel3.setBackground(new java.awt.Color(43, 43, 43));

            btnGuardar1.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda-32 (1).png"))); // NOI18N
            btnGuardar1.setContentAreaFilled(false);
            btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnGuardar1.setIconTextGap(30);
            btnGuardar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnGuardar1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnGuardar1MouseClicked(evt);
                }
            });
            btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardar1ActionPerformed(evt);
                }
            });

            btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
            btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Hoy-32 (2).png"))); // NOI18N
            btnGuardar.setContentAreaFilled(false);
            btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnGuardar.setIconTextGap(30);
            btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGuardarActionPerformed(evt);
                }
            });

            jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel30.setForeground(new java.awt.Color(255, 255, 255));
            jLabel30.setText("Próxima Cita");

            btneditar.setForeground(new java.awt.Color(240, 240, 240));
            btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
            btneditar.setContentAreaFilled(false);
            btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btneditar.setIconTextGap(30);
            btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btneditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btneditarActionPerformed(evt);
                }
            });

            btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
            btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
            btnEliminar.setContentAreaFilled(false);
            btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnEliminar.setEnabled(false);
            btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnEliminar.setIconTextGap(30);
            btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnEliminarActionPerformed(evt);
                }
            });

            lblIDPREVENTA.setText("jLabel13");

            lblID.setForeground(new java.awt.Color(255, 255, 255));
            lblID.setText("jLabel8");

            lblid_hc.setText("jLabel13");

            lblMant.setForeground(new java.awt.Color(255, 255, 255));
            lblMant.setText("I");

            lblMant1.setForeground(new java.awt.Color(255, 255, 255));
            lblMant1.setText("I");

            var.setForeground(new java.awt.Color(255, 255, 255));
            var.setText("1");

            lblIDCITA.setForeground(new java.awt.Color(255, 255, 255));

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIDPREVENTA)))
                    .addGap(18, 18, 18)
                    .addComponent(lblID)
                    .addGap(18, 18, 18)
                    .addComponent(lblid_hc)
                    .addGap(18, 18, 18)
                    .addComponent(lblMant)
                    .addGap(18, 18, 18)
                    .addComponent(lblMant1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblIDCITA, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGuardar)
                                .addComponent(btnGuardar1)
                                .addComponent(btneditar)
                                .addComponent(btnEliminar)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblIDCITA, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblIDPREVENTA)
                                    .addComponent(lblID)
                                    .addComponent(lblid_hc)
                                    .addComponent(lblMant)
                                    .addComponent(lblMant1)
                                    .addComponent(var)))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel1.setBackground(new java.awt.Color(214, 217, 223));

            JD_Fecha.setBackground(new java.awt.Color(242, 242, 242));
            JD_Fecha.setForeground(new java.awt.Color(102, 102, 102));
            JD_Fecha.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
            JD_Fecha.setDecorationBackgroundColor(new java.awt.Color(0, 102, 51));
            JD_Fecha.setDecorationBackgroundVisible(false);
            JD_Fecha.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
            JD_Fecha.setSundayForeground(new java.awt.Color(255, 153, 51));
            JD_Fecha.setWeekdayForeground(new java.awt.Color(51, 51, 51));
            JD_Fecha.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    JD_FechaMouseClicked(evt);
                }
            });
            JD_Fecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    JD_FechaPropertyChange(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JD_Fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(JD_Fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            mensaje.setBackground(new java.awt.Color(39, 174, 97));

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
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(51, 51, 51));
            jLabel3.setText("Turnos Disponibles");

            jScrollPane3.setBackground(new java.awt.Color(0, 153, 102));
            jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

            tbTurnos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            tbTurnos.setForeground(new java.awt.Color(102, 102, 102));
            tbTurnos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
            ));
            tbTurnos.setGridColor(new java.awt.Color(255, 255, 255));
            tbTurnos.setRowHeight(25);
            tbTurnos.setSelectionBackground(new java.awt.Color(39, 174, 97));
            tbTurnos.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tbTurnosMouseClicked(evt);
                }
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    tbTurnosMousePressed(evt);
                }
            });
            tbTurnos.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tbTurnosKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tbTurnosKeyReleased(evt);
                }
            });
            jScrollPane3.setViewportView(tbTurnos);

            jPanel4.setBackground(new java.awt.Color(255, 153, 51));

            jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 70)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Calendar.PNG"))); // NOI18N
            jLabel1.setText("00");
            jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

            jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("Reservar cita para:");

            lblFecha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblFecha.setForeground(new java.awt.Color(255, 255, 255));
            lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFecha.setText("jLabel3");

            lblFecha1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            lblFecha1.setForeground(new java.awt.Color(255, 255, 255));
            lblFecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFecha1.setText("Nº Atención");

            lblNumeros.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
            lblNumeros.setForeground(new java.awt.Color(255, 255, 255));
            lblNumeros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblNumeros.setText("00");

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));
            jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            txNRO.setBackground(new java.awt.Color(255, 153, 51));
            txNRO.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
            txNRO.setForeground(new java.awt.Color(255, 255, 255));
            txNRO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txNRO.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            txNRO.setSelectionColor(new java.awt.Color(255, 153, 51));
            txNRO.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txNROCaretUpdate(evt);
                }
            });
            txNRO.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txNROKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txNRO, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addComponent(txNRO)
                    .addGap(2, 2, 2))
            );

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                        .addComponent(lblNumeros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblFecha)
                    .addGap(37, 37, 37)
                    .addComponent(lblFecha1)
                    .addGap(18, 18, 18)
                    .addComponent(lblNumeros)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(36, Short.MAX_VALUE))
            );

            lblturno.setText("jLabel5");

            lblFechaCompleta.setText("jLabel6");

            lblMedico.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblMedico.setForeground(new java.awt.Color(51, 51, 51));
            lblMedico.setText("jLabel5");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(51, 51, 51));
            jLabel4.setText("Médico");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(lblturno)
                                .addComponent(lblFechaCompleta)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblMedico))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblturno)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblFechaCompleta)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed

    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        GuardarPreventa();
        Guardar();
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void JD_FechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JD_FechaMouseClicked
       
      
    }//GEN-LAST:event_JD_FechaMouseClicked

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
       mensaje.setVisible(false);
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void JD_FechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JD_FechaPropertyChange
        lblFecha.setVisible(false);
        lblFecha1.setVisible(false);
        jPanel9.setVisible(false);
        String FECHA= new SimpleDateFormat("dd").format(JD_Fecha.getDate());
        String FECHA1= new SimpleDateFormat("MM - yyy").format(JD_Fecha.getDate());
        jLabel1.setText("<html>"+FECHA+"<span style=\"font-size:'15px'\"><br>"+FECHA1+"<html>");
        
        String FECHA2= new SimpleDateFormat("dd/MM/yyy").format(JD_Fecha.getDate());
        lblFechaCompleta.setText(FECHA2);
        
        Consulta.ListarTurnos(tbTurnos,lblFechaCompleta.getText());
    }//GEN-LAST:event_JD_FechaPropertyChange

    private void btnGuardar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardar1MouseClicked
         ConsultorioExt.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_btnGuardar1MouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbTurnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTurnosMouseClicked
        int fila=tbTurnos.getSelectedRow();
        if(evt.getClickCount()==1){
            lblturno.setText(String.valueOf(tbTurnos.getValueAt(fila, 4)));
            lblFecha.setText("<html>Cantidad de citas <br><p style='text-align:center'>"+String.valueOf(tbTurnos.getValueAt(fila, 3))+"</p></html>");
            lblMedico.setText(String.valueOf(tbTurnos.getValueAt(fila, 6)));
            Consulta.ListarNumeros(lblFechaCompleta.getText(),lblturno.getText());
            
            lblFecha.setVisible(true);
            lblFecha1.setVisible(true);
            jPanel9.setVisible(true);
            txNRO.requestFocus();
        }
    }//GEN-LAST:event_tbTurnosMouseClicked

    private void tbTurnosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTurnosMousePressed

    }//GEN-LAST:event_tbTurnosMousePressed

    private void tbTurnosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTurnosKeyPressed

    }//GEN-LAST:event_tbTurnosKeyPressed

    private void tbTurnosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTurnosKeyReleased

    }//GEN-LAST:event_tbTurnosKeyReleased

    private void txNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txNROCaretUpdate
        

    }//GEN-LAST:event_txNROCaretUpdate

    private void txNROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNROKeyTyped
        if (txNRO.getText().length()== 2){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txNROKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar JD_Fecha;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    public static javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    public static javax.swing.JButton btneditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    private javax.swing.JLabel lblFechaCompleta;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblIDCITA;
    public static javax.swing.JLabel lblIDPREVENTA;
    public static javax.swing.JLabel lblMant;
    public static javax.swing.JLabel lblMant1;
    private javax.swing.JLabel lblMedico;
    public static javax.swing.JLabel lblNumeros;
    public static javax.swing.JLabel lblid_hc;
    private javax.swing.JLabel lblturno;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tbTurnos;
    public static javax.swing.JTextField txNRO;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
