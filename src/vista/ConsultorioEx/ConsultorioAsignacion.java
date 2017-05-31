/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import modelos.ConsultorioEx.ConsultorioExConsultorioAsignacion;
import servicios.Conexion;
import tablas.FormatoTablaMovCONEXT;
import static vista.hospitalizacion.FrmHospitalizacionListarCajaPreventa.tbListarPapeleta;

/**
 *
 * @author MYS1
 */
public class ConsultorioAsignacion extends javax.swing.JFrame {
ConsultorioExConsultorioAsignacion ConsA = new ConsultorioExConsultorioAsignacion();
byte tg;
byte tge;
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();
    /**
     * Creates new form ConsultorioAsignacion
     */
    public ConsultorioAsignacion() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
        Medicos.setLocationRelativeTo(null);//en el centro
        Medicos.getContentPane().setBackground(new Color(0,153,102)); 
        Consultorios.setLocationRelativeTo(null);//en el centro
        Consultorios.getContentPane().setBackground(new Color(0,153,102)); 
        mensaje.setVisible(false);
        
        Nuevo.setVisible(false);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnsubir.setEnabled(false);
        btnbuscar.setEnabled(false);
        this.cbTurno.setModel(Turno());
        
       
        listar();
    
        tb_Detalle.setDefaultRenderer(Object.class,new FormatoTablaMovCONEXT());
        
        JTableHeader th; 
        th = tb_Detalle.getTableHeader(); 
        Font fuente = new Font("Segoe UI", Font.CENTER_BASELINE, 14); 
        th.setFont(fuente); 
        th.setForeground(new java.awt.Color(102,102,102));

        
        
        

       

    }
    public DefaultComboBoxModel Turno(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("Select DISTINCT  Nombre From CONSULTORIO_EXT_TURNO"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "Nombre" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    public void enviarDatosTbMedico(){
        int fila = tb_medicos.getSelectedRow();
            Medicos.dispose();
            codmed.setText(String.valueOf(tb_medicos.getValueAt(fila, 0)));
            txtMedico.setText(String.valueOf(tb_medicos.getValueAt(fila, 1)));
            btnguardar.setEnabled(true);
    }
    public void enviarDatosTbConsultorio(){
        int fila = tb_consultorios.getSelectedRow();
        Consultorios.dispose();
            codcons.setText(String.valueOf(tb_consultorios.getValueAt(fila, 0)));
            txtConsultorio.setText(String.valueOf(tb_consultorios.getValueAt(fila, 1))+"    "+String.valueOf(tb_consultorios.getValueAt(fila, 2)));
             jPanel28.setBorder(BorderFactory.createLineBorder(new Color(153,153,153), 1));
    }
    public void listar(){
        try {
            ConsA.listarDetalle(tb_Detalle); 
        } catch (Exception e) {
        }
       
    }
    public void Limpiar(){
        txtMedico.setText("");
        txtConsultorio.setText("");
        txtAtenciones.setText("");
        txtAdicionales.setText("");
        txtFuturas.setText("");
        txtFecha.setDate(null);
       
    }
    
     public static String fechaActual(){
        java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
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
     
     

     public void Guardar(){
        if(txtAtenciones.getText().equals("")|| txtFecha.equals("")){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Asegurese de completar todos los campos");
            b.setVisible(false);
            b1.setVisible(false);
            
            jPanel28.setBorder(BorderFactory.createLineBorder(new Color(255,91,70), 1));
            txtAtenciones.setBorder(BorderFactory.createLineBorder(new Color(255,91,70), 1));
        } else {
            ConsultorioExConsultorioAsignacion CA1 = new ConsultorioExConsultorioAsignacion();
            if(CA1.MismoMedicoMismoTurno(codmed.getText(),turid.getText(),determinarFecha(txtFecha))>0){
                 mensaje.setVisible(true);
                 mensaje.setBackground(new Color(255,91,70)); 
                 men.setText(txtMedico.getText()+" ya se encuantra asiganado en el turno de "+tur.getText()+" para el dia "+determinarFecha(txtFecha)+" ,Verique");
                 b.setVisible(false);
                 b1.setVisible(false);
          
                }else
          
                 if(CA1.MiscoConsultorioMismoTurno(codcons.getText(),turid.getText(),determinarFecha(txtFecha))>0){
                 mensaje.setVisible(true);
                 mensaje.setBackground(new Color(255,91,70)); 
                 men.setText("El consultorio de "+txtConsultorio.getText()+" ya se encuantra asiganado en el turno de "+tur.getText()+" para el dia "+determinarFecha(txtFecha)+" ,Verique");
                 b.setVisible(false);
                 b1.setVisible(false);
                  
          
                }else{      
                
                        ConsultorioExConsultorioAsignacion CA = new ConsultorioExConsultorioAsignacion();
                        String Hinicio = (String) HoraFN.getSelectedItem();
                        String Hfin = (String) HoraIN.getSelectedItem();
                            CA.setId(0);
                            CA.setConsultorio_id(Integer.parseInt(codcons.getText()));
                            CA.setNro_cita(Integer.parseInt(txtAtenciones.getText()));
                            CA.setCod_rol(Integer.parseInt(codmed.getText()));
                            CA.setFecha(determinarFecha(txtFecha));
                            CA.setId_Turno(Integer.parseInt(turid.getText()));/////
                            CA.setUsuario(CA.codUsuario(lblusu.getText()));
                            CA.setNro_cita_A(Integer.parseInt(txtAdicionales.getText()));
                            CA.setNro_cita_F(Integer.parseInt(txtFuturas.getText()));

                            if(CA.mantenimientoConsultorioExConsultorioAsignacion("I")==true){
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Datos Guardados de forma correcta");
                                b.setText("OK");
                                b.setVisible(true);
                                b1.setVisible(false);
                                Nuevo.setVisible(false);
                                btnguardar.setEnabled(false);
                                btnsubir.setEnabled(false);
                                tge=1;
                                listar();
                            }else {
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(255,91,70)); 
                                men.setText("Ocurrió un error, Verifique");
                                b.setVisible(false);
                                b1.setVisible(false);
                            }
                        }
                    }
    }
     
         public void Modificar(){
             try {
                 
             
                        ConsultorioExConsultorioAsignacion CAM = new ConsultorioExConsultorioAsignacion();
                        String Hinicio = (String) HoraFN.getSelectedItem();
                        String Hfin = (String) HoraIN.getSelectedItem();
                        CAM.setId(Integer.parseInt(id.getText()));
                        CAM.setConsultorio_id(Integer.parseInt(codcons.getText()));
                        CAM.setNro_cita(Integer.parseInt(txtAtenciones.getText()));
                        CAM.setCod_rol(Integer.parseInt(codmed.getText()));
                        CAM.setFecha(determinarFecha(txtFecha));
                        CAM.setId_Turno(Integer.parseInt(turid.getText()));/////
                        CAM.setNro_cita_A(Integer.parseInt(txtAdicionales.getText()));
                        CAM.setNro_cita_F(Integer.parseInt(txtFuturas.getText()));
                      
                        if(CAM.mantenimientoConsultorioExConsultorioAsignacion("U")==true){
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Datos Actualizados de forma correcta");
                                btnguardar.setEnabled(false);
                                b.setText("OK");
                                b.setVisible(true);
                                b1.setVisible(false);
                                tge=1;
                                
                                listar();
                
                            }else {
                               
                           
                                mensaje.setVisible(true);
                                mensaje.setBackground(new Color(255,91,70)); 
                                men.setText("Ocurrió un error, Verifique");
                                b.setVisible(false);
                                b1.setVisible(false);
                   
                    }   
                        } catch (Exception e) {
                            System.out.println("Error: btnGuardar" + e.getMessage());
             }
    }
    public void Eliminar(){
  
                ConsultorioExConsultorioAsignacion Tr = new ConsultorioExConsultorioAsignacion();
                Tr.setId(Integer.parseInt(id.getText()));
                if(Tr.mantenimientoConsultorioExConsultorioAsignacion("E")){
                                mensaje.setBackground(new Color(33,115,70)); 
                                men.setText("Registro Eliminado");
                                b.setText("OK");
                                b1.setVisible(true);
                                b1.setVisible(false);
                                tge=9;
                                listar();
                                
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

        Medicos = new javax.swing.JDialog();
        jPanel46 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        BMedicos = new javax.swing.JTextField();
        T8 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tb_medicos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel51 = new javax.swing.JPanel();
            jLabel65 = new javax.swing.JLabel();
            jLabel66 = new javax.swing.JLabel();
            Consultorios = new javax.swing.JDialog();
            jPanel48 = new javax.swing.JPanel();
            jLabel63 = new javax.swing.JLabel();
            jPanel49 = new javax.swing.JPanel();
            Bcons = new javax.swing.JTextField();
            T9 = new javax.swing.JLabel();
            jTabbedPane8 = new javax.swing.JTabbedPane();
            jPanel52 = new javax.swing.JPanel();
            jScrollPane17 = new javax.swing.JScrollPane();
            tb_consultorios = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel53 = new javax.swing.JPanel();
                jLabel67 = new javax.swing.JLabel();
                jLabel68 = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                btneditar = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                btneliminar1 = new javax.swing.JButton();
                btnsubir = new javax.swing.JButton();
                btnbuscar = new javax.swing.JButton();
                mensaje = new javax.swing.JPanel();
                men = new javax.swing.JLabel();
                b = new javax.swing.JButton();
                b1 = new javax.swing.JButton();
                Nuevo = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                cbTurno = new javax.swing.JComboBox();
                jLabel5 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                txtAtenciones = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                txtFecha = new com.toedter.calendar.JDateChooser();
                codmed = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel12 = new javax.swing.JLabel();
                id = new javax.swing.JLabel();
                tur = new javax.swing.JLabel();
                HoraFN = new javax.swing.JComboBox();
                HoraIN = new javax.swing.JComboBox();
                turid = new javax.swing.JLabel();
                jPanel9 = new javax.swing.JPanel();
                txtMedico = new javax.swing.JTextField();
                T3 = new javax.swing.JButton();
                jPanel28 = new javax.swing.JPanel();
                txtConsultorio = new javax.swing.JTextField();
                T4 = new javax.swing.JButton();
                jLabel9 = new javax.swing.JLabel();
                txtAdicionales = new javax.swing.JTextField();
                jLabel11 = new javax.swing.JLabel();
                txtFuturas = new javax.swing.JTextField();
                codcons = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_Detalle = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel29 = new javax.swing.JPanel();
                    fechai = new com.toedter.calendar.JDateChooser();
                    fechaf = new com.toedter.calendar.JDateChooser();
                    T5 = new javax.swing.JLabel();
                    txtBuscar = new javax.swing.JTextField();
                    jLabel13 = new javax.swing.JLabel();

                    Medicos.setAlwaysOnTop(true);
                    Medicos.setMinimumSize(new java.awt.Dimension(900, 398));
                    Medicos.setResizable(false);
                    Medicos.getContentPane().setLayout(null);

                    jPanel46.setBackground(new java.awt.Color(0, 153, 102));
                    jPanel46.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel62.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel62.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel62.setText("Médicos de turno");

                    jPanel47.setBackground(new java.awt.Color(255, 255, 255));

                    BMedicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    BMedicos.setBorder(null);
                    BMedicos.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            BMedicosCaretUpdate(evt);
                        }
                    });
                    BMedicos.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            BMedicosActionPerformed(evt);
                        }
                    });
                    BMedicos.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            BMedicosKeyPressed(evt);
                        }
                    });

                    T8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T8.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T8MouseClicked(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                    jPanel47.setLayout(jPanel47Layout);
                    jPanel47Layout.setHorizontalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(BMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(T8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel47Layout.setVerticalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(T8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );

                    jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    jLabel56.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel56.setText("Búsqueda por nombres y apellidos");

                    javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
                    jPanel46.setLayout(jPanel46Layout);
                    jPanel46Layout.setHorizontalGroup(
                        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel46Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel56)
                                .addComponent(jLabel62)
                                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(634, Short.MAX_VALUE))
                    );
                    jPanel46Layout.setVerticalGroup(
                        jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                            .addContainerGap(13, Short.MAX_VALUE)
                            .addComponent(jLabel62)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jLabel56)
                            .addGap(333, 333, 333))
                    );

                    Medicos.getContentPane().add(jPanel46);
                    jPanel46.setBounds(0, 0, 910, 110);

                    jTabbedPane7.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel50.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane16.setBackground(new java.awt.Color(255, 255, 255));
                    jScrollPane16.setBorder(null);

                    tb_medicos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    tb_medicos.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_medicos.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_medicos.setRowHeight(25);
                    tb_medicos.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tb_medicos.getTableHeader().setReorderingAllowed(false);
                    tb_medicos.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_medicosMouseClicked(evt);
                        }
                    });
                    tb_medicos.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_medicosKeyPressed(evt);
                        }
                    });
                    jScrollPane16.setViewportView(tb_medicos);

                    javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
                    jPanel50.setLayout(jPanel50Layout);
                    jPanel50Layout.setHorizontalGroup(
                        jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 905, Short.MAX_VALUE)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE))
                    );
                    jPanel50Layout.setVerticalGroup(
                        jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 282, Short.MAX_VALUE)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    );

                    jTabbedPane7.addTab("tab2", jPanel50);

                    jPanel51.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel65.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                    jLabel65.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel65.setText("No se halló ningún medico de turno");

                    jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                    jLabel66.setForeground(new java.awt.Color(0, 153, 51));
                    jLabel66.setText(":(");

                    javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
                    jPanel51.setLayout(jPanel51Layout);
                    jPanel51Layout.setHorizontalGroup(
                        jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jLabel66)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel65)
                            .addContainerGap(249, Short.MAX_VALUE))
                    );
                    jPanel51Layout.setVerticalGroup(
                        jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel51Layout.createSequentialGroup()
                            .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel51Layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addComponent(jLabel65))
                                .addGroup(jPanel51Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel66)))
                            .addContainerGap(91, Short.MAX_VALUE))
                    );

                    jTabbedPane7.addTab("tab3", jPanel51);

                    Medicos.getContentPane().add(jTabbedPane7);
                    jTabbedPane7.setBounds(0, 118, 910, 310);

                    Consultorios.setAlwaysOnTop(true);
                    Consultorios.setMinimumSize(new java.awt.Dimension(497, 398));
                    Consultorios.setResizable(false);
                    Consultorios.getContentPane().setLayout(null);

                    jPanel48.setBackground(new java.awt.Color(0, 153, 102));
                    jPanel48.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel63.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel63.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel63.setText("Consultorios");

                    jPanel49.setBackground(new java.awt.Color(255, 255, 255));

                    Bcons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    Bcons.setBorder(null);
                    Bcons.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            BconsCaretUpdate(evt);
                        }
                    });
                    Bcons.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            BconsActionPerformed(evt);
                        }
                    });
                    Bcons.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            BconsKeyPressed(evt);
                        }
                    });

                    T9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T9.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T9MouseClicked(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
                    jPanel49.setLayout(jPanel49Layout);
                    jPanel49Layout.setHorizontalGroup(
                        jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel49Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(Bcons, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(T9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel49Layout.setVerticalGroup(
                        jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(Bcons, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(T9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );

                    javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                    jPanel48.setLayout(jPanel48Layout);
                    jPanel48Layout.setHorizontalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel48Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel63)
                                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(634, Short.MAX_VALUE))
                    );
                    jPanel48Layout.setVerticalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel63)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(353, 353, 353))
                    );

                    Consultorios.getContentPane().add(jPanel48);
                    jPanel48.setBounds(0, 0, 910, 110);

                    jTabbedPane8.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel52.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane17.setBackground(new java.awt.Color(255, 255, 255));
                    jScrollPane17.setBorder(null);

                    tb_consultorios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    tb_consultorios.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_consultorios.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_consultorios.setRowHeight(25);
                    tb_consultorios.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tb_consultorios.getTableHeader().setReorderingAllowed(false);
                    tb_consultorios.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_consultoriosMouseClicked(evt);
                        }
                    });
                    tb_consultorios.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_consultoriosKeyPressed(evt);
                        }
                    });
                    jScrollPane17.setViewportView(tb_consultorios);

                    javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
                    jPanel52.setLayout(jPanel52Layout);
                    jPanel52Layout.setHorizontalGroup(
                        jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 905, Short.MAX_VALUE)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel52Layout.createSequentialGroup()
                                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 408, Short.MAX_VALUE)))
                    );
                    jPanel52Layout.setVerticalGroup(
                        jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 282, Short.MAX_VALUE)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    );

                    jTabbedPane8.addTab("tab2", jPanel52);

                    jPanel53.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel67.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                    jLabel67.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel67.setText("No se halló ningún medico de turno");

                    jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                    jLabel68.setForeground(new java.awt.Color(0, 153, 51));
                    jLabel68.setText(":(");

                    javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
                    jPanel53.setLayout(jPanel53Layout);
                    jPanel53Layout.setHorizontalGroup(
                        jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel53Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jLabel68)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel67)
                            .addContainerGap(249, Short.MAX_VALUE))
                    );
                    jPanel53Layout.setVerticalGroup(
                        jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel53Layout.createSequentialGroup()
                            .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel53Layout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addComponent(jLabel67))
                                .addGroup(jPanel53Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(jLabel68)))
                            .addContainerGap(91, Short.MAX_VALUE))
                    );

                    jTabbedPane8.addTab("tab3", jPanel53);

                    Consultorios.getContentPane().add(jTabbedPane8);
                    jTabbedPane8.setBounds(0, 118, 910, 310);

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("<html>Consultorios <span style=\"font-size:'15px'\">Asignación</span></html>");

                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                    btnNuevo.setMnemonic('N');
                    btnNuevo.setContentAreaFilled(false);
                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNuevo.setIconTextGap(30);
                    btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoActionPerformed(evt);
                        }
                    });

                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                    btneditar.setMnemonic('N');
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

                    btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                    btnguardar.setMnemonic('N');
                    btnguardar.setContentAreaFilled(false);
                    btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnguardar.setIconTextGap(30);
                    btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnguardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnguardarActionPerformed(evt);
                        }
                    });

                    btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                    btneliminar.setMnemonic('N');
                    btneliminar.setContentAreaFilled(false);
                    btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btneliminar.setIconTextGap(30);
                    btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btneliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneliminarActionPerformed(evt);
                        }
                    });

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setText("Silvana");

                    btneliminar1.setForeground(new java.awt.Color(240, 240, 240));
                    btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                    btneliminar1.setMnemonic('N');
                    btneliminar1.setToolTipText("");
                    btneliminar1.setContentAreaFilled(false);
                    btneliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btneliminar1.setIconTextGap(30);
                    btneliminar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btneliminar1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneliminar1ActionPerformed(evt);
                        }
                    });

                    btnsubir.setForeground(new java.awt.Color(240, 240, 240));
                    btnsubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Subir-30.png"))); // NOI18N
                    btnsubir.setMnemonic('N');
                    btnsubir.setContentAreaFilled(false);
                    btnsubir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnsubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnsubir.setIconTextGap(30);
                    btnsubir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnsubir.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnsubirActionPerformed(evt);
                        }
                    });

                    btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
                    btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-32.png"))); // NOI18N
                    btnbuscar.setMnemonic('N');
                    btnbuscar.setContentAreaFilled(false);
                    btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnbuscar.setIconTextGap(30);
                    btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnbuscarActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblusu)
                                    .addGap(16, 16, 16))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnsubir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneliminar1)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addComponent(lblusu)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btneliminar))
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(btnsubir)
                                .addComponent(btnbuscar))
                            .addGap(552, 552, 552))
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
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    Nuevo.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel2.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel2.setText("Médico");

                    cbTurno.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            cbTurnoItemStateChanged(evt);
                        }
                    });

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel5.setText("Nº de Atenciones");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel4.setText("Turno");

                    txtAtenciones.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
                    txtAtenciones.setForeground(new java.awt.Color(102, 102, 102));
                    txtAtenciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtAtenciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                    txtAtenciones.setSelectionColor(new java.awt.Color(0, 153, 0));
                    txtAtenciones.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtAtencionesKeyTyped(evt);
                        }
                    });

                    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel6.setText("Asignación de consultorios____________________________________________________________________________________________");

                    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel7.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel7.setText("Consultorio");

                    txtFecha.setBackground(new java.awt.Color(255, 255, 255));
                    txtFecha.setDateFormatString("dd/MM/yyyy");
                    txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

                    codmed.setForeground(new java.awt.Color(255, 255, 255));
                    codmed.setText("jLabel3");

                    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel8.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel8.setText("Inicio");

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel10.setText("Termino");

                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel12.setText("Fecha");

                    id.setForeground(new java.awt.Color(255, 255, 255));
                    id.setText("jLabel3");

                    tur.setForeground(new java.awt.Color(255, 255, 255));
                    tur.setText("jLabel3");

                    HoraFN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione.." }));

                    HoraIN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione.." }));
                    HoraIN.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            HoraINMouseClicked(evt);
                        }
                    });
                    HoraIN.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            HoraINItemStateChanged(evt);
                        }
                    });

                    turid.setForeground(new java.awt.Color(255, 255, 255));
                    turid.setText("jLabel3");

                    jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtMedico.setEditable(false);
                    txtMedico.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    txtMedico.setForeground(new java.awt.Color(51, 51, 51));
                    txtMedico.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    txtMedico.setToolTipText("");
                    txtMedico.setBorder(null);
                    txtMedico.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtMedicoCaretUpdate(evt);
                        }
                    });

                    T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T3.setToolTipText("");
                    T3.setContentAreaFilled(false);
                    T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            T3ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                    jPanel9.setLayout(jPanel9Layout);
                    jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtMedico)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMedico, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel28.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtConsultorio.setEditable(false);
                    txtConsultorio.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                    txtConsultorio.setForeground(new java.awt.Color(51, 51, 51));
                    txtConsultorio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    txtConsultorio.setBorder(null);
                    txtConsultorio.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtConsultorioCaretUpdate(evt);
                        }
                    });

                    T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T4.setToolTipText("");
                    T4.setContentAreaFilled(false);
                    T4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            T4ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                    jPanel28.setLayout(jPanel28Layout);
                    jPanel28Layout.setHorizontalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtConsultorio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    jPanel28Layout.setVerticalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtConsultorio, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel9.setText("Adicionales");

                    txtAdicionales.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
                    txtAdicionales.setForeground(new java.awt.Color(102, 102, 102));
                    txtAdicionales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtAdicionales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                    txtAdicionales.setSelectionColor(new java.awt.Color(0, 153, 0));
                    txtAdicionales.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtAdicionalesKeyTyped(evt);
                        }
                    });

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel11.setText("Futuras");

                    txtFuturas.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
                    txtFuturas.setForeground(new java.awt.Color(102, 102, 102));
                    txtFuturas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtFuturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                    txtFuturas.setSelectionColor(new java.awt.Color(0, 153, 0));
                    txtFuturas.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtFuturasKeyTyped(evt);
                        }
                    });

                    codcons.setForeground(new java.awt.Color(255, 255, 255));
                    codcons.setText("jLabel3");

                    javax.swing.GroupLayout NuevoLayout = new javax.swing.GroupLayout(Nuevo);
                    Nuevo.setLayout(NuevoLayout);
                    NuevoLayout.setHorizontalGroup(
                        NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NuevoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, NuevoLayout.createSequentialGroup()
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7))
                                    .addGap(68, 68, 68)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(NuevoLayout.createSequentialGroup()
                                            .addComponent(txtAtenciones, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtFuturas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(NuevoLayout.createSequentialGroup()
                                            .addGap(117, 117, 117)
                                            .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel12))
                                            .addGap(18, 18, 18)
                                            .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbTurno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(NuevoLayout.createSequentialGroup()
                                                    .addComponent(HoraIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                                    .addComponent(jLabel10)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(HoraFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(NuevoLayout.createSequentialGroup()
                                            .addGap(86, 86, 86)
                                            .addComponent(tur)
                                            .addGap(18, 18, 18)
                                            .addComponent(codcons)
                                            .addGap(31, 31, 31)
                                            .addComponent(id)
                                            .addGap(26, 26, 26)
                                            .addComponent(codmed)
                                            .addGap(37, 37, 37)
                                            .addComponent(turid))))
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    NuevoLayout.setVerticalGroup(
                        NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NuevoLayout.createSequentialGroup()
                            .addContainerGap(17, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addGap(23, 23, 23)
                            .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(NuevoLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtAtenciones, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAdicionales, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFuturas, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(20, 20, 20))
                                .addGroup(NuevoLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(HoraIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10)
                                        .addComponent(HoraFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11))
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(NuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(id)
                                        .addComponent(tur)
                                        .addComponent(codmed)
                                        .addComponent(turid)
                                        .addComponent(codcons)))))
                    );

                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    jScrollPane4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

                    tb_Detalle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    tb_Detalle.setForeground(new java.awt.Color(255, 255, 255));
                    tb_Detalle.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Detalle.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Detalle.setRowHeight(25);
                    tb_Detalle.setSelectionBackground(new java.awt.Color(33, 102, 78));
                    tb_Detalle.setShowVerticalLines(false);
                    tb_Detalle.getTableHeader().setReorderingAllowed(false);
                    tb_Detalle.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_DetalleMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tb_DetalleMousePressed(evt);
                        }
                    });
                    tb_Detalle.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_DetalleKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            tb_DetalleKeyReleased(evt);
                        }
                    });
                    jScrollPane4.setViewportView(tb_Detalle);

                    jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                    fechai.setBackground(new java.awt.Color(255, 255, 255));
                    fechai.setDateFormatString("dd-MM-yyyy");

                    fechaf.setBackground(new java.awt.Color(255, 255, 255));
                    fechaf.setDateFormatString("dd-MM-yyyy");

                    T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T5.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T5MouseClicked(evt);
                        }
                    });

                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel13.setText("Busqueda");

                    javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                    jPanel29.setLayout(jPanel29Layout);
                    jPanel29Layout.setHorizontalGroup(
                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 515, Short.MAX_VALUE))
                    );
                    jPanel29Layout.setVerticalGroup(
                        jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(T5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                .addComponent(fechaf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBuscar)))
                    );

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(Nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(38, 38, 38))
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Nuevo.setVisible(true);
        btnguardar.setEnabled(false);
        btnsubir.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        jPanel29.setVisible(false);
        btnbuscar.setEnabled(true);
        txtAtenciones.setEditable(true);
        txtAdicionales.setEditable(true);
        txtFuturas.setEditable(true);
        T3.setEnabled(true);
        T4.setEnabled(true);
        Limpiar();
        tg=1;
        try {
            ConsA.ListarMedicos(BMedicos.getText(),tb_medicos,"","","A");
            Medicos.setVisible(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
             tg=2;
             btnNuevo.setEnabled(true);
             btnguardar.setEnabled(true);
             btneditar.setEnabled(false);
             btneliminar.setEnabled(false);
             Nuevo.setVisible(true);
             jPanel29.setVisible(false);
//             jScrollPane4.setEnabled(false);
//             tb_Detalle.setEnabled(false);
             btnsubir.setEnabled(true);
             txtAtenciones.setEditable(true);
             txtAdicionales.setEditable(true);
             txtFuturas.setEditable(true);
             T3.setEnabled(true);
             T4.setEnabled(true);
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        ConsultorioExConsultorioAsignacion cno2 = new ConsultorioExConsultorioAsignacion();
        turid.setText((cno2.TurnosId(HoraIN.getSelectedItem().toString())));
        if(tg==1){
             Guardar();  
        }
        if(tg==2){
           mensaje.setVisible(true);
           mensaje.setBackground(new Color(255,153,51)); 
           men.setText("Desea Actualizar el Registro ?");
           b.setText("Si");
           b.setVisible(true);
           b1.setVisible(true); 
           tge=2;
        }       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        mensaje.setVisible(true);
        mensaje.setBackground(new Color(255,91,70)); 
        //men.setText("Desea Eliminar este registro?");
        men.setText("ELIMINAR esta asiganción de consultorio para, "+txtMedico.getText()+" , "+tur.getText()+" , "+determinarFecha(txtFecha)+" ?");

        b.setText("Si");
        b.setVisible(true);
        b1.setVisible(true);
        tge=8;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void tb_DetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DetalleMouseClicked
        
        int fila=tb_Detalle.getSelectedRow();
        if(evt.getClickCount()==1){
         
            String fechaSeleccionada1 = (String) tb_Detalle.getModel().getValueAt(fila, 13);
             try {
             DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
             Date fecha = dfo.parse(fechaSeleccionada1);
             txtFecha.setDate(fecha);
            } catch (Exception e) {
            }
            id.setText(String.valueOf(tb_Detalle.getValueAt(fila, 0)));  
            txtConsultorio.setText(String.valueOf(tb_Detalle.getValueAt(fila, 4)));
            txtAtenciones.setText(String.valueOf(tb_Detalle.getValueAt(fila, 5)));
            txtAdicionales.setText(String.valueOf(tb_Detalle.getValueAt(fila, 6)));
            txtFuturas.setText(String.valueOf(tb_Detalle.getValueAt(fila, 7)));
            cbTurno.setSelectedItem(String.valueOf(tb_Detalle.getValueAt(fila, 8)));
            txtMedico.setText(String.valueOf(tb_Detalle.getValueAt(fila, 9)));
            tur.setText(String.valueOf(tb_Detalle.getValueAt(fila, 8)));
            codcons.setText(String.valueOf(tb_Detalle.getValueAt(fila, 10)));
            codmed.setText(String.valueOf(tb_Detalle.getValueAt(fila, 11)));
            
            btneliminar.setEnabled(true);
            btneditar.setEnabled(true);
            btnguardar.setEnabled(false);
            
           

     
        }
        if(evt.getClickCount()==2){
            Nuevo.setVisible(true);
            jPanel29.setVisible(false);
            btnguardar.setEnabled(false);
            btneliminar.setEnabled(true);
            btneditar.setEnabled(true);
            btnsubir.setEnabled(true);
            T3.setEnabled(false);
            T4.setEnabled(false);
            txtAtenciones.setEditable(false);
            txtAdicionales.setEditable(false);
            txtFuturas.setEditable(false);
            
        
        }
    }//GEN-LAST:event_tb_DetalleMouseClicked

    private void tb_DetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DetalleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_DetalleMousePressed

    private void tb_DetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_DetalleKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_DetalleKeyPressed

    private void BMedicosCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BMedicosCaretUpdate

        ConsA.ListarMedicos(BMedicos.getText(),tb_medicos,"","","A");
        BMedicos.setEnabled(true);
        if (tb_medicos.getRowCount() == 0){
            jTabbedPane7.setSelectedIndex(1);
        }
        if (BMedicos.getText().length()==0){
            jTabbedPane7.setSelectedIndex(0);
        }
    }//GEN-LAST:event_BMedicosCaretUpdate

    private void BMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMedicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BMedicosActionPerformed

    private void BMedicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BMedicosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BMedicosKeyPressed

    private void T8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T8MouseClicked

    private void tb_medicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_medicosMouseClicked
        if(evt.getClickCount()==2){
            enviarDatosTbMedico();
        }
    }//GEN-LAST:event_tb_medicosMouseClicked

    private void tb_medicosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_medicosKeyPressed

       if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tb_medicos.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            BMedicos.requestFocus();
            tb_medicos.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbMedico();
        }

    }//GEN-LAST:event_tb_medicosKeyPressed

    private void BconsCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_BconsCaretUpdate
        ConsA.listarConsultorios(Bcons.getText(),tb_consultorios);
        Bcons.setEnabled(true);
        if (tb_consultorios.getRowCount() == 0){
            jTabbedPane7.setSelectedIndex(1);
        }
        if (Bcons.getText().length()==0){
            jTabbedPane7.setSelectedIndex(0);
        }
    }//GEN-LAST:event_BconsCaretUpdate

    private void BconsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BconsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BconsActionPerformed

    private void BconsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BconsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BconsKeyPressed

    private void T9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T9MouseClicked

    private void tb_consultoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_consultoriosMouseClicked
       
        if(evt.getClickCount()==2){
            enviarDatosTbConsultorio();
        }
    }//GEN-LAST:event_tb_consultoriosMouseClicked

    private void tb_consultoriosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_consultoriosKeyPressed
         if(evt.getExtendedKeyCode()==KeyEvent.VK_UP && tb_consultorios.getSelectedRow()==0){
            //tbPaciente.getSelectionModel().setSelectionInterval(0, 0);
            Bcons.requestFocus();
            tb_consultorios.getSelectionModel().setSelectionInterval(0,0);
        }
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            enviarDatosTbConsultorio();
        }
    }//GEN-LAST:event_tb_consultoriosKeyPressed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
 if (tge==3 || tge==1 || tge==9){
   mensaje.setVisible(false);  

    
   }
        
        if (tge==2){
        Modificar();

        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);

   }  
     if (tge==8){
        Eliminar();
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
   }  
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void cbTurnoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTurnoItemStateChanged

          
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbTurno.getSelectedIndex()>0){
                        this.HoraIN.removeAllItems(); 
                        this.HoraFN.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    Statement sta1=conexion.createStatement();
                    String dpto=cbTurno.getSelectedItem().toString();
                    String dpto1=cbTurno.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC CONSULTORIO_EXT_BUSCAR_HORAS '"+dpto+"'");
                    ResultSet rs1=sta1.executeQuery("EXEC CONSULTORIO_EXT_BUSCAR_HORAS1 '"+dpto1+"'");
                    
                    while(rs.next()){
                     this.HoraIN.addItem(rs.getString("HoraI"));
                  //  this.cbxProvincia.setModel(null);
                    }
                    while(rs1.next()){
                     this.HoraFN.addItem(rs1.getString("HoraF"));
                  //  this.cbxProvincia.setModel(null);
                    }
                    
                     }else{
                            this.HoraIN.removeAllItems();
                            this.HoraFN.removeAllItems();

                        this.HoraIN.addItem("Seleccione");
                        this.HoraFN.addItem("Seleccione");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
        
       
        
        tur.setText((cbTurno.getSelectedItem().toString()));
        txtAtenciones.setBorder(BorderFactory.createLineBorder(new Color(102,102,102), 1));
        
//        if(Turno.equals("Mañana")){
//           this.HoraI.removeAllItems(); 
//            HoraI.addItem("8:00");
//            HoraI.addItem("9:00");
//            HoraI.addItem("10:00");
//            HoraI.addItem("11:00");
//            this.HoraF.removeAllItems(); 
//            HoraF.addItem("9:00");
//            HoraF.addItem("10:00");
//            HoraF.addItem("11:00");
//            HoraF.addItem("12:00");
//
//       }
//       if(Turno.equals("Tarde")){
//            this.HoraI.removeAllItems(); 
//            HoraI.addItem("2:00");
//            HoraI.addItem("3:00");
//            HoraI.addItem("4:00");
//            this.HoraF.removeAllItems(); 
//            HoraF.addItem("3:00");
//            HoraF.addItem("4:00");
//            HoraF.addItem("5:00");
//
//       }
//        if(Turno.equals("Noche")){
//            this.HoraI.removeAllItems(); 
//            HoraI.addItem("6:00");
//            HoraI.addItem("7:00");
//            HoraI.addItem("8:00");
//            this.HoraF.removeAllItems(); 
//            HoraF.addItem("7:00");
//            HoraF.addItem("8:00");
//            HoraF.addItem("9:00");
//
//       }
           
            
      
    }//GEN-LAST:event_cbTurnoItemStateChanged

    private void btnsubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubirActionPerformed
        Nuevo.setVisible(false);
        btnsubir.setEnabled(false);
        btnbuscar.setEnabled(true);
        btnguardar.setEnabled(false);
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
        mensaje.setVisible(false);
    }//GEN-LAST:event_btnsubirActionPerformed

    private void tb_DetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_DetalleKeyReleased
         if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN || evt.getExtendedKeyCode()==KeyEvent.VK_UP){
            int fila = tb_Detalle.getSelectedRow();
            try {
             String fechaSeleccionada = (String) tb_Detalle.getModel().getValueAt(fila, 13);
             try {
             DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
             Date fecha = dfo.parse(fechaSeleccionada);
             txtFecha.setDate(fecha);
            } catch (Exception e) {
            }
            id.setText(String.valueOf(tb_Detalle.getValueAt(fila, 0)));  
            txtConsultorio.setText(String.valueOf(tb_Detalle.getValueAt(fila, 4)));
            txtAtenciones.setText(String.valueOf(tb_Detalle.getValueAt(fila, 5)));
            txtAdicionales.setText(String.valueOf(tb_Detalle.getValueAt(fila, 6)));
            txtFuturas.setText(String.valueOf(tb_Detalle.getValueAt(fila, 7)));
            cbTurno.setSelectedItem(String.valueOf(tb_Detalle.getValueAt(fila, 8)));
            txtMedico.setText(String.valueOf(tb_Detalle.getValueAt(fila, 9)));
            tur.setText(String.valueOf(tb_Detalle.getValueAt(fila, 8)));//mensajes
            codcons.setText(String.valueOf(tb_Detalle.getValueAt(fila, 10)));
            codmed.setText(String.valueOf(tb_Detalle.getValueAt(fila, 11)));
            
            btneliminar.setEnabled(true);
            btneditar.setEnabled(true);
            btnguardar.setEnabled(false);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tb_DetalleKeyReleased

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
//        adEmerTr.TriajeListarReporte(txtBuscar.getText(),tbTriaje,determinarFecha(fechai),determinarFecha(fechaf),"T");
//        txtBuscar.setEnabled(true);
    }//GEN-LAST:event_T5MouseClicked

    private void HoraINItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_HoraINItemStateChanged
       
      
    }//GEN-LAST:event_HoraINItemStateChanged

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        jPanel29.setVisible(true);
        txtBuscar.requestFocus();
        mensaje.setVisible(false);
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtAtencionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAtencionesKeyTyped

        if (txtAtenciones.getText().length()==2){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
//Como ves, implementé un beep para que suene si se trata de ingresar mas 😄
}
    }//GEN-LAST:event_txtAtencionesKeyTyped

    private void txtMedicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMedicoCaretUpdate

    }//GEN-LAST:event_txtMedicoCaretUpdate

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed
       Medicos.setVisible(true);
    }//GEN-LAST:event_T3ActionPerformed

    private void txtConsultorioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConsultorioCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsultorioCaretUpdate

    private void T4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T4ActionPerformed
        ConsA.listarConsultorios("",tb_consultorios);
        Consultorios.setVisible(true);
    }//GEN-LAST:event_T4ActionPerformed

    private void HoraINMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoraINMouseClicked
         
    }//GEN-LAST:event_HoraINMouseClicked

    private void txtAdicionalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdicionalesKeyTyped
        if (txtAdicionales.getText().length()==2){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtAdicionalesKeyTyped

    private void txtFuturasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuturasKeyTyped
        if (txtFuturas.getText().length()==2){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtFuturasKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultorioAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultorioAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultorioAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultorioAsignacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultorioAsignacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BMedicos;
    private javax.swing.JTextField Bcons;
    private javax.swing.JDialog Consultorios;
    private javax.swing.JComboBox HoraFN;
    private javax.swing.JComboBox HoraIN;
    private javax.swing.JDialog Medicos;
    private javax.swing.JPanel Nuevo;
    private javax.swing.JButton T3;
    private javax.swing.JButton T4;
    private javax.swing.JLabel T5;
    private javax.swing.JLabel T8;
    private javax.swing.JLabel T9;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsubir;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JLabel codcons;
    private javax.swing.JLabel codmed;
    private com.toedter.calendar.JDateChooser fechaf;
    private com.toedter.calendar.JDateChooser fechai;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JTable tb_Detalle;
    private javax.swing.JTable tb_consultorios;
    private javax.swing.JTable tb_medicos;
    private javax.swing.JLabel tur;
    private javax.swing.JLabel turid;
    private javax.swing.JTextField txtAdicionales;
    private javax.swing.JTextField txtAtenciones;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtConsultorio;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtFuturas;
    public static javax.swing.JTextField txtMedico;
    // End of variables declaration//GEN-END:variables
}
