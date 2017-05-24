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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComponent;
import modelos.ConsultorioEx.ConsultorioExtRsCcd;
import modelos.ConsultorioEx.ConsultorioExtRsSeguimientoAnemia;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class REGSEGSEGA extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
private Dimension DimensionBarra = null;
    /**
     * Creates new form REGSEGSEGA
     */
    public REGSEGSEGA() {
        initComponents();
        QuitarLaBarraTitulo();
        pnlMensaje.setVisible(false);
        FrmCie10.setLocationRelativeTo(null);//en el centro
        FrmCie10.getContentPane().setBackground(new Color(0,153,102));
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10("", tbCiePresun);
        lblMant.setText("I");
        ConsultorioExtRsSeguimientoAnemia seguimiento = new ConsultorioExtRsSeguimientoAnemia();
        seguimiento.inicializarTabla(tbSeguimientoA);
        seguimiento.listarDiagnostico(lblId.getText(), tbSeguimientoA);
        habilitarDatos(false);
    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        repaint(); 
    }
    
    public void habilitarDatos(boolean opcion){
        dtFecha.setEnabled(opcion);
        txtEdad.setEnabled(opcion);
        txtRes.setEnabled(opcion);
        btnBuscarCie10.setEnabled(opcion);
        lblMant.setText("I");
    }
    
    public void limpiar(){
        dtFecha.setDate(null);
        txtCie10.setText("");
        txtRes.setText("");
        lblIdCie10.setText("");
    }
    
    public void enviarDiagnosticos(){
        int fila = tbCiePresun.getSelectedRow();
        FrmCie10.dispose();
        txtCie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 1)));
        lblIdCie10.setText(String.valueOf(tbCiePresun.getValueAt(fila, 0)));
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
                           pnlMensaje.setVisible(true);
                           pnlMensaje.setBackground(new Color(255,91,70)); 
                           lblMensaje.setText("Ingrese una fecha correcta");
                          
         }
        
        return fecha;
    }
    
    public void enviarDatosSeguimientoA(){
        int fila = tbSeguimientoA.getSelectedRow();
        txtEdad.setText(String.valueOf(tbSeguimientoA.getValueAt(fila, 2)));
        txtRes.setText(String.valueOf(tbSeguimientoA.getValueAt(fila, 3)));
        lblIdCie10.setText(String.valueOf(tbSeguimientoA.getValueAt(fila, 6)));
        txtCie10.setText(String.valueOf(tbSeguimientoA.getValueAt(fila, 4)));
        lblIdSa.setText(String.valueOf(tbSeguimientoA.getValueAt(fila, 0)));
        lblMant.setText("U");
        btnGuardar.setEnabled(true);
        habilitarDatos(true);
        String fechaSeleccionada = (String) tbSeguimientoA.getModel().getValueAt(fila, 1);
         try {
         DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
         Date fecha = dfo.parse(fechaSeleccionada);
         dtFecha.setDate(fecha);
        } catch (Exception e) {
        }
    }
    
    public boolean guardarDatos(){
        boolean retorna = false;
        try {
            ConsultorioExtRsSeguimientoAnemia seguimiento1 = new ConsultorioExtRsSeguimientoAnemia();
            AdmisionEmergenciaCabecera ad1 = new AdmisionEmergenciaCabecera();
            seguimiento1.setRs_id(Integer.parseInt(lblId.getText()));
            seguimiento1.setSaEdad(txtEdad.getText());
            seguimiento1.setSaFecha(determinarFecha(dtFecha));
            seguimiento1.setSaRes(txtRes.getText());
            seguimiento1.setId_cie10(Integer.parseInt(lblIdCie10.getText()));
            seguimiento1.setCodUsu(ad1.codUsuario(RegistroSeguimiento.lblUsu.getText()));
            if(seguimiento1.mantenimientoConsultorioExtRsSeguimientoAnemia(lblMant.getText())==true){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos guardados de forma correcta");
                seguimiento1.listarDiagnostico(lblId.getText(), tbSeguimientoA);
                habilitarDatos(false);
                btnGuardar.setEnabled(false);
                limpiar();
                btnGuardar.setEnabled(false);
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
            System.out.println("Error: guardarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean modificarDatos(){
        boolean retorna = false;
        try {
            ConsultorioExtRsSeguimientoAnemia seguimiento1 = new ConsultorioExtRsSeguimientoAnemia();
            seguimiento1.setSaId(Integer.parseInt(lblIdSa.getText()));
            seguimiento1.setSaEdad(txtEdad.getText());
            seguimiento1.setSaFecha(determinarFecha(dtFecha));
            seguimiento1.setSaRes(txtRes.getText());
            seguimiento1.setId_cie10(Integer.parseInt(lblIdCie10.getText()));
            if(seguimiento1.mantenimientoConsultorioExtRsSeguimientoAnemia(lblMant.getText())==true){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos modificados de forma correcta");
                seguimiento1.listarDiagnostico(lblId.getText(), tbSeguimientoA);
                limpiar();
                habilitarDatos(false);
                btnGuardar.setEnabled(false);
                btnGuardar.setEnabled(false);
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
            System.out.println("Error: modificarDatos" + e.getMessage());
        }
        return retorna;
    }
    
    public boolean eliminarDatos(){
        boolean retorna = false;
        try {
            ConsultorioExtRsSeguimientoAnemia seguimiento1 = new ConsultorioExtRsSeguimientoAnemia();
            seguimiento1.setSaId(Integer.parseInt(lblIdSa.getText()));
            if(seguimiento1.mantenimientoConsultorioExtRsSeguimientoAnemia(lblMant.getText())==true){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Datos eliminados de forma correcta");
                seguimiento1.listarDiagnostico(lblId.getText(), tbSeguimientoA);
                limpiar();
                habilitarDatos(false);
                btnGuardar.setEnabled(false);
                btnGuardar.setEnabled(false);
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
            System.out.println("Error: eliminarDatos" + e.getMessage());
        }
        return retorna;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrmCie10 = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        titulo7 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        T7 = new javax.swing.JLabel();
        txtBuscarCie10 = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbCiePresun = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel2 = new javax.swing.JPanel();
            jLabel12 = new javax.swing.JLabel();
            jLabel13 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            jPanel29 = new javax.swing.JPanel();
            txtCie10 = new javax.swing.JTextField();
            btnBuscarCie10 = new javax.swing.JButton();
            dtFecha = new com.toedter.calendar.JDateChooser();
            txtEdad = new javax.swing.JTextField();
            txtRes = new javax.swing.JTextField();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbSeguimientoA = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                mensaje1 = new javax.swing.JPanel();
                jPanel32 = new javax.swing.JPanel();
                btnGuardar = new javax.swing.JButton();
                btnNuevo = new javax.swing.JButton();
                pnlMensaje = new javax.swing.JPanel();
                lblMensaje = new javax.swing.JLabel();
                btnSi = new javax.swing.JButton();
                btnNo = new javax.swing.JButton();
                lblMant = new javax.swing.JLabel();
                lblIdCie10 = new javax.swing.JLabel();
                lblIdSa = new javax.swing.JLabel();
                lblId = new javax.swing.JLabel();
                jPanel14 = new javax.swing.JPanel();
                jPanel12 = new javax.swing.JPanel();
                jPanel13 = new javax.swing.JPanel();
                jPanel15 = new javax.swing.JPanel();
                jPanel16 = new javax.swing.JPanel();
                jPanel17 = new javax.swing.JPanel();

                FrmCie10.setMinimumSize(new java.awt.Dimension(750, 400));
                FrmCie10.setResizable(false);

                jPanel10.setBackground(new java.awt.Color(102, 102, 102));
                jPanel10.setPreferredSize(new java.awt.Dimension(500, 65));

                titulo7.setBackground(new java.awt.Color(153, 0, 51));
                titulo7.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                titulo7.setForeground(new java.awt.Color(255, 255, 255));
                titulo7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                titulo7.setText("CIE 10");
                titulo7.setToolTipText("");
                titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel36.setForeground(new java.awt.Color(255, 255, 255));
                jLabel36.setText("Código CIE 10 / Diagnóstico Presuntivo");

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

                jPanel33.setBackground(new java.awt.Color(39, 174, 97));

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

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(449, 449, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(titulo7)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(184, Short.MAX_VALUE)))
                );

                jScrollPane6.setBorder(null);

                tbCiePresun.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbCiePresun.setGridColor(new java.awt.Color(255, 255, 255));
                tbCiePresun.setRowHeight(25);
                tbCiePresun.setSelectionBackground(new java.awt.Color(39, 174, 97));
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
                jScrollPane6.setViewportView(tbCiePresun);

                javax.swing.GroupLayout FrmCie10Layout = new javax.swing.GroupLayout(FrmCie10.getContentPane());
                FrmCie10.getContentPane().setLayout(FrmCie10Layout);
                FrmCie10Layout.setHorizontalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                );
                FrmCie10Layout.setVerticalGroup(
                    FrmCie10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FrmCie10Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                );

                setBorder(javax.swing.BorderFactory.createCompoundBorder());
                setVisible(true);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                jLabel12.setText("Fecha");

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                jLabel13.setText("Edad");

                jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                jLabel14.setText("RES. Hb");

                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel16.setForeground(new java.awt.Color(51, 51, 51));
                jLabel16.setText("Diagnóstico");

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));
                jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtCie10.setEditable(false);
                txtCie10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtCie10.setForeground(new java.awt.Color(51, 51, 51));
                txtCie10.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtCie10.setBorder(null);
                txtCie10.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtCie10CaretUpdate(evt);
                    }
                });

                btnBuscarCie10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarCie10.setToolTipText("");
                btnBuscarCie10.setContentAreaFilled(false);
                btnBuscarCie10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarCie10.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarCie10ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtCie10, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCie10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                );
                jPanel29Layout.setVerticalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCie10, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(btnBuscarCie10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                dtFecha.setBackground(new java.awt.Color(255, 255, 255));
                dtFecha.setDateFormatString("dd/MM/yyyy");
                dtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

                txtEdad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                txtRes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jScrollPane5.setBorder(null);
                jScrollPane5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

                tbSeguimientoA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                tbSeguimientoA.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbSeguimientoA.setGridColor(new java.awt.Color(255, 255, 255));
                tbSeguimientoA.setRowHeight(25);
                tbSeguimientoA.setSelectionBackground(new java.awt.Color(45, 204, 112));
                tbSeguimientoA.setShowVerticalLines(false);
                tbSeguimientoA.getTableHeader().setReorderingAllowed(false);
                tbSeguimientoA.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbSeguimientoAMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbSeguimientoAMousePressed(evt);
                    }
                });
                tbSeguimientoA.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbSeguimientoAKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbSeguimientoAKeyReleased(evt);
                    }
                });
                jScrollPane5.setViewportView(tbSeguimientoA);

                mensaje1.setBackground(new java.awt.Color(102, 102, 102));

                jPanel32.setBackground(new java.awt.Color(51, 51, 51));

                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('N');
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.setEnabled(false);
                btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnGuardar.setIconTextGap(30);
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                btnNuevo.setMnemonic('N');
                btnNuevo.setContentAreaFilled(false);
                btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                btnNuevo.setIconTextGap(30);
                btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnNuevoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
                jPanel32.setLayout(jPanel32Layout);
                jPanel32Layout.setHorizontalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE))
                );
                jPanel32Layout.setVerticalGroup(
                    jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
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
                        .addGap(14, 14, 14)
                        .addGroup(pnlMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMensaje)
                            .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout mensaje1Layout = new javax.swing.GroupLayout(mensaje1);
                mensaje1.setLayout(mensaje1Layout);
                mensaje1Layout.setHorizontalGroup(
                    mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mensaje1Layout.createSequentialGroup()
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                mensaje1Layout.setVerticalGroup(
                    mensaje1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                lblMant.setText("jLabel1");

                lblIdCie10.setText("jLabel1");

                lblIdSa.setText("jLabel1");

                lblId.setText("jLabel1");

                jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                jPanel12.setPreferredSize(new java.awt.Dimension(83, 45));
                jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel12MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 233, Short.MAX_VALUE)
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );

                jPanel13.setBackground(new java.awt.Color(45, 204, 112));
                jPanel13.setPreferredSize(new java.awt.Dimension(298, 45));
                jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel13MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 209, Short.MAX_VALUE)
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );

                jPanel15.setBackground(new java.awt.Color(255, 255, 255));
                jPanel15.setPreferredSize(new java.awt.Dimension(205, 45));

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 216, Short.MAX_VALUE)
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );

                jPanel16.setBackground(new java.awt.Color(255, 255, 255));
                jPanel16.setPreferredSize(new java.awt.Dimension(222, 45));

                javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                jPanel16.setLayout(jPanel16Layout);
                jPanel16Layout.setHorizontalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 225, Short.MAX_VALUE)
                );
                jPanel16Layout.setVerticalGroup(
                    jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );

                jPanel17.setBackground(new java.awt.Color(255, 255, 255));
                jPanel17.setPreferredSize(new java.awt.Dimension(238, 45));

                javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                jPanel17.setLayout(jPanel17Layout);
                jPanel17Layout.setHorizontalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 228, Short.MAX_VALUE)
                );
                jPanel17Layout.setVerticalGroup(
                    jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 47, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(55, 55, 55)
                                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMant)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblIdCie10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblIdSa)))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblId)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRes, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(433, Short.MAX_VALUE))))
                    .addComponent(mensaje1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(txtRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblMant)
                                .addComponent(lblIdCie10)
                                .addComponent(lblIdSa)
                                .addComponent(lblId)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCie10CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCie10CaretUpdate

    private void btnBuscarCie10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCie10ActionPerformed
        FrmCie10.setVisible(true);
    }//GEN-LAST:event_btnBuscarCie10ActionPerformed

    private void tbSeguimientoAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSeguimientoAMouseClicked
        if(evt.getClickCount()==1){
            enviarDatosSeguimientoA();
            lblMant.setText("U");
        }
    }//GEN-LAST:event_tbSeguimientoAMouseClicked

    private void tbSeguimientoAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSeguimientoAMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbSeguimientoAMousePressed

    private void tbSeguimientoAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSeguimientoAKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_DELETE){
            pnlMensaje.setVisible(true);
            lblMensaje.setText("¿Seguro que desea ELIMINAR los datos?");
            pnlMensaje.setBackground(new Color(33,115,70));
            btnSi.setText("Si");
            btnSi.setVisible(true);
            btnNo.setVisible(true);
            lblMant.setText("E");
        }
    }//GEN-LAST:event_tbSeguimientoAKeyPressed

    private void tbSeguimientoAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSeguimientoAKeyReleased
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            enviarDatosSeguimientoA();
            lblMant.setText("U");
        }
    }//GEN-LAST:event_tbSeguimientoAKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(lblMant.getText().equals("I")){
            if(dtFecha.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else if(txtRes.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingreso el el Res Hb");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else if(txtCie10.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el diagnóstico");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea GUARDAR los datos?");
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                lblMant.setText("I");
            }
        }else{
            if(dtFecha.getDate()==null){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingrese la fecha");
                btnSi.setVisible(false);
                btnNo.setVisible(false);
                pnlMensaje.setBackground(new Color(255,91,70));
            } else if(txtRes.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Ingreso el el Res Hb");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else if(txtCie10.getText().equals("")){
                pnlMensaje.setVisible(true);
                lblMensaje.setText("Seleccione el diagnóstico");
                pnlMensaje.setBackground(new Color(255,91,70));
                btnSi.setVisible(false);
                btnNo.setVisible(false);
            } else {
                pnlMensaje.setVisible(true);
                lblMensaje.setText("¿Seguro que desea MODIFICAR los datos?");
                pnlMensaje.setBackground(new Color(33,115,70));
                btnSi.setText("Si");
                btnSi.setVisible(true);
                btnNo.setVisible(true);
                lblMant.setText("U");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
        habilitarDatos(true);
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
        if(btnSi.getText().equals("Si")){ // Al guardar
            if(lblMant.getText().equals("I")){
                guardarDatos();
            }
            if(lblMant.getText().equals("U")){
                modificarDatos();
            }
            if(lblMant.getText().equals("E")){
                eliminarDatos();
            }
        } else
        if(btnSi.getText().equals("OK")){ // Al hacer OK hacerloinvisible
            pnlMensaje.setVisible(false);
        }
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
        pnlMensaje.setVisible(false);
    }//GEN-LAST:event_btnNoActionPerformed

    private void T7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T7MouseClicked

    private void txtBuscarCie10CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCie10CaretUpdate
        ConsultorioExtRsCcd CCDBUSCAR = new ConsultorioExtRsCcd();
        CCDBUSCAR.cargarDatosCie10(txtBuscarCie10.getText(), tbCiePresun);
    }//GEN-LAST:event_txtBuscarCie10CaretUpdate

    private void txtBuscarCie10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCie10KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tbCiePresun.getSelectionModel().setSelectionInterval(0, 0);
            tbCiePresun.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarCie10KeyPressed

    private void tbCiePresunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCiePresunMouseClicked
        // TODO add your handling code here:
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

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked

    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked

    }//GEN-LAST:event_jPanel12MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FrmCie10;
    private javax.swing.JLabel T7;
    private javax.swing.JButton btnBuscarCie10;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSi;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JLabel lblId;
    public static javax.swing.JLabel lblIdCie10;
    public static javax.swing.JLabel lblIdSa;
    public static javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JPanel mensaje1;
    private javax.swing.JPanel pnlMensaje;
    private javax.swing.JTable tbCiePresun;
    public static javax.swing.JTable tbSeguimientoA;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscarCie10;
    public static javax.swing.JTextField txtCie10;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JTextField txtRes;
    // End of variables declaration//GEN-END:variables
}
