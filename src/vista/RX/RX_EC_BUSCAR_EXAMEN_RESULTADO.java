/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.RX;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import servicios.Conexion;
import modelos.RX.*;
import modelos.Usuario;
import static vista.EC.EC_EXAMEN_CAB.btnPersonalRegistra_EC;
import vista.RX.RX_EC_EXAMEN_CAB;
import static vista.RX.RX_EC_EXAMEN_CAB.tb_examen_det;
import static vista.RX.RX_EC_EXAMEN_CAB_RESULTADO.btnPersonalResRealiza;
import static vista.RX.RX_EC_EXAMEN_CAB_RESULTADO.btnPersonalResultado;
import static vista.RX.RX_EC_EXAMEN_CAB_RESULTADO.txtCAB_RESULTADO;
import static vista.RX.RX_EC_EXAMEN_CAB_RESULTADO.txtPersonalRealizaRes;
import static vista.RX.RX_EC_EXAMEN_CAB_RESULTADO.txtPersonalRegistraResultado;

/**
 *
 * @author MYS3
 */
public class RX_EC_BUSCAR_EXAMEN_RESULTADO extends javax.swing.JFrame implements Runnable{
Conexion conectar=new Conexion();
Connection ConexionS=conectar.conectar();
Connection con;
String hora, minutos, segundos, ampm;
Calendar calendario;
Thread h1;
ResultSet r;
CallableStatement cst;
DefaultTableModel m, msb,m2, m3, m4, m5, modelo1, modelo2;
static int contador =0;
int velocidad=1;
    
static RX_EC_BUSCAR_EXAMEN_CAJA DT = new RX_EC_BUSCAR_EXAMEN_CAJA();  
/**
     * Creates new form BUSCAR_EXAMEN_C
*/
    public RX_EC_BUSCAR_EXAMEN_RESULTADO() throws UnknownHostException {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        DETALLE.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        DETALLE.getContentPane().setBackground(Color.white);
        con=conectar.conectar();
        mostrarArea();
        inicializar_tabla_Examenes();
        seleccion();
        txtBuscarPaciente.requestFocus();
//        mostrarPacientes();
        timer.start();
        lblFecha.setVisible(false);
        lblHora.setVisible(false);
        lblNumeArea.setVisible(false);
        lblFechaNaci.setVisible(false);
        lblGenero.setVisible(false);
        lblEdad.setVisible(false);
        lblIDArea.setVisible(false);
        lblNomAD.setVisible(false);
        lblUsuD.setVisible(false);
        jPanel3.setVisible(false);
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
         
       
        //CERRAR CON ESCAPE
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                
                cerrar();
                                
            }
        });
        
        
    
        
     
    
        
        
//////obtener el nombre de la pc
//        InetAddress localHost = InetAddress.getLocalHost();
//        txtNomPC.setText(localHost.getHostName());
        
        //FECHA Y HORA
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());
        
    }
    
    Timer timer = new Timer (1000, new ActionListener (){
        public void actionPerformed(ActionEvent e){
            Cambios();
            lblCONTADOR.setText(String.valueOf(contador));
    }
    });
    
    public void LimpiarTablas(){
        DefaultTableModel modelo1 = (DefaultTableModel)tb_DETALLE.getModel(); 
        int b=tb_DETALLE.getRowCount();
        for(int j =0;j<b;j++){
            modelo1.removeRow(0);
        }

        DefaultTableModel modelo2 = (DefaultTableModel)tb_CABECERA.getModel(); 
        int C=tb_CABECERA.getRowCount();
        for(int j=0;j<C;j++){
            modelo2.removeRow(0);
        }
        
        RX_EC_EXAMEN CB = new RX_EC_EXAMEN();
        CB.LISTA_CABECERAS(tb_CABECERA);
    }
    public void Cambios(){
        switch(contador){
        case 0:
        contador = 1;
        break;
        case 1:
        RX_EC_EXAMEN CB = new RX_EC_EXAMEN();
        CB.LISTA_CABECERAS(tb_CABECERA);
        
        
        int CT =tb_CABECERA.getRowCount();
        for(int j=0;j<CT;j++){
            try {
                tb_CABECERA.getSelectionModel().setSelectionInterval (0,0) ;
                CARAGAR_DETALLE();
                NUEVO_REGISTRO(ConexionS);  
                LimpiarTablas();
                

            } catch (Exception e) {
         
            }
            
        }
        
        if(tb_Examenes.getRowCount()!=0){
            jTabbedPane1.setSelectedIndex(1);
        }  
        contador = 0;
        break;     
        }
    }
    
    public void CARAGAR_DETALLE(){
        try {
            int fila=tb_CABECERA.getSelectedRow();
            jScrollPane5.setVisible(true);
            lblID_DOCUEMENTO.setText(String.valueOf(tb_CABECERA.getValueAt(fila, 0)));
            RX_EC_EXAMEN CNVRCDB = new  RX_EC_EXAMEN();
            CNVRCDB.LISTA_DETALLES(lblID_DOCUEMENTO.getText(),tb_DETALLE);
            tb_DETALLE.getSelectionModel().setSelectionInterval (0,0) ;
            System.out.println("COD_DET  "+tb_DETALLE.getValueAt(0, 0).toString());
//            CARGAR_ID();
        } catch (Exception e) {
        }
    }
    

    
    public void NUEVO_REGISTRO(java.sql.Connection con) {
        try {
            RX_EC_EXAMEN rxg = new RX_EC_EXAMEN();
            RX_EC_EXAMEN cno2 = new RX_EC_EXAMEN();
            CallableStatement cstmt = con.prepareCall("exec RX_EC_EXAMEN_CABECERA_GUARDAR ?,?,?,?,?,?,?,?");  
            cstmt.setString(1, lblID_DOCUEMENTO.getText());
            cstmt.setString(2, "1");
            cstmt.setString(3, "P0001");
            cstmt.setString(4, "P01");
            cstmt.setString(5, "P0002");
            cstmt.setString(6, "P02");
            cstmt.setString(7, "PC");
            // registramos el parametro de retorno (si fueran mas, repetimos la linea cambiando el nro de orden del parametro)
            cstmt.registerOutParameter(8, java.sql.Types.INTEGER);
            // ejecutamos
            cstmt.execute();
            // mostramos al usuario el codigo creado
            int ID;
            ID=cstmt.getInt(8);
            this.lblID_CAB.setText( String.valueOf(ID) );
            System.out.println("CABECERA GUARDADA");  

            RX_EC_ESTADO_MODIFICAR();
            guardarDetalleExamen();
                                    
        }
        catch (Exception e) {
            timer.stop();
            System.out.println("ERROR AL REGISTRAR CABECERA" + e);
            e.printStackTrace();
            timer.start();
        }
    }
    
    public void RX_EC_ESTADO_MODIFICAR(){
        for (int i = 0; i < tb_DETALLE.getRowCount(); i++){ 
        RX_EC_EXAMEN_DET m=new RX_EC_EXAMEN_DET();
        m.RX_EC_Estado_Caja(Integer.parseInt(tb_DETALLE.getValueAt(i, 0).toString()));  
        }
    }
    
    public void guardarDetalleExamen(){
//        int id = Integer.parseInt(txtId_Documento_G.getText());
        
         for (int i = 0; i < tb_DETALLE.getRowCount(); i++){      
               RX_EC_EXAMEN_DET dd=new RX_EC_EXAMEN_DET();
               dd.setID_EXAMEN_CAB(Integer.parseInt(lblID_CAB.getText()));
               dd.setID_COD_DOC_DET(Integer.parseInt(tb_DETALLE.getValueAt(i, 0).toString()));
//               
                    dd.setCOD_PER_SOL(String.valueOf("- - -"));
                    dd.setNOM_PER_SOL(String.valueOf("- - -"));                 

               dd.setINCIDENCIA(tb_DETALLE.getValueAt(i, 3).toString());
//               dd.setID_PREVENTA(lblId_Preventa.getText());
//               dd.setHAB_NOM(txtHabitacion.getText());
//               dd.setCA_DESC(txtCama.getText());
//               dd.setHOSP_SERVICIO(lblHospiServ.getText());             
               dd.setNOM_USU(lblUsu.getText());
               
               dd.RX_EC_EXAMEN_DETALLE_GUARDAR();               
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ver_detalle = new javax.swing.JMenuItem();
        DETALLE = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_Detalle = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblFechaNaci = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblIDArea = new javax.swing.JLabel();
        lblNomAD = new javax.swing.JLabel();
        lblUsuD = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtHC = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbFecha = new javax.swing.JCheckBox();
        fecha_fin = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblCONTADOR = new javax.swing.JLabel();
        lblID_DOCUEMENTO = new javax.swing.JLabel();
        lblID_CAB = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_DETALLE = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_CABECERA = new javax.swing.JTable();
        lblFP = new javax.swing.JLabel();
        lblREF = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        txtBuscarPaciente = new javax.swing.JTextField();
        btnBuscarP = new javax.swing.JButton();
        btnBuscarP1 = new javax.swing.JButton();
        fecha_inicio = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lblNomArea = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblNumeArea = new javax.swing.JLabel();
        lblfecha_I = new javax.swing.JLabel();
        lblG = new javax.swing.JLabel();
        lblfecha_F = new javax.swing.JLabel();
        lblfecha_F2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Examenes = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        ver_detalle.setText("Ver detalle");
        ver_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_detalleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ver_detalle);

        DETALLE.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DETALLE.setMinimumSize(new java.awt.Dimension(705, 400));

        tb_Detalle = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Detalle.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        tb_Detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_Detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_Detalle.setRowHeight(25);
        tb_Detalle.setSelectionBackground(new java.awt.Color(40, 112, 99));
        tb_Detalle.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tb_Detalle);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 20)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("DETALLE");

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("El paciente tiene ");

        lblNumero.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(255, 255, 255));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumero.setText("jLabel12");

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("examen(es) pendiente(es). ¿ Desea cargar ?");

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("OK");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton1.setContentAreaFilled(false);
        jButton1.setMaximumSize(new java.awt.Dimension(23, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(23, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(23, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12)
                .addGap(42, 42, 42)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblNumero)
                    .addComponent(jLabel12)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblFechaNaci.setText("jLabel13");

        lblGenero.setText("jLabel13");

        lblEdad.setText("jLabel13");

        lblIDArea.setText("jLabel13");

        lblNomAD.setText("jLabel14");

        lblUsuD.setText("jLabel14");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel5.setText("Cod. Exa.:");

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel6.setText("DNI:");

        txtDNI.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDNI.setEnabled(false);

        txtDocumento.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtDocumento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDocumento.setEnabled(false);
        txtDocumento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDocumentoCaretUpdate(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel9.setText("AM:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel8.setText("Paciente:");

        txtAM.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtAM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAM.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        jLabel7.setText("HC:");

        txtHC.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtHC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHC.setEnabled(false);

        txtNombres.setFont(new java.awt.Font("Segoe UI Light", 0, 13)); // NOI18N
        txtNombres.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDocumento)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout DETALLELayout = new javax.swing.GroupLayout(DETALLE.getContentPane());
        DETALLE.getContentPane().setLayout(DETALLELayout);
        DETALLELayout.setHorizontalGroup(
            DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DETALLELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DETALLELayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DETALLELayout.createSequentialGroup()
                                .addComponent(lblFechaNaci)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGenero))
                            .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DETALLELayout.createSequentialGroup()
                                .addComponent(lblNomAD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsuD))
                            .addComponent(lblIDArea, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(29, 29, 29))
                    .addGroup(DETALLELayout.createSequentialGroup()
                        .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        DETALLELayout.setVerticalGroup(
            DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DETALLELayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFechaNaci)
                        .addComponent(lblGenero))
                    .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNomAD)
                        .addComponent(lblUsuD))
                    .addGroup(DETALLELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(lblIDArea)
                        .addComponent(lblEdad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel13.setText("jLabel13");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Diagnóstico por Imágenes RX");

        jpanel.setBackground(new java.awt.Color(40, 40, 43));

        titulo5.setBackground(new java.awt.Color(0, 102, 102));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 35)); // NOI18N
        titulo5.setForeground(new java.awt.Color(204, 204, 204));
        titulo5.setText("Ordenes");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblUsu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("Usuario");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Desde");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hasta");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("AM, DNI, HC, Apellidos y Nombres");

        cbFecha.setBackground(new java.awt.Color(40, 40, 43));
        cbFecha.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbFecha.setForeground(new java.awt.Color(255, 255, 255));
        cbFecha.setText("Buscar por Rango de Fechas");
        cbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFechaActionPerformed(evt);
            }
        });
        cbFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbFechaPropertyChange(evt);
            }
        });

        fecha_fin.setBackground(new java.awt.Color(40, 40, 43));
        fecha_fin.setForeground(new java.awt.Color(255, 255, 255));
        fecha_fin.setDateFormatString("dd-MM-yyyy");
        fecha_fin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fecha_finKeyTyped(evt);
            }
        });

        jLabel14.setText("jLabel14");

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        lblCONTADOR.setForeground(new java.awt.Color(255, 255, 255));
        lblCONTADOR.setText("jLabel14");

        lblID_DOCUEMENTO.setText("jLabel14");

        lblID_CAB.setText("jLabel18");

        jScrollPane5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_CABECERA = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_DETALLE.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_DETALLE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5"
            }
        ));
        tb_DETALLE.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_DETALLE.setRowHeight(35);
        tb_DETALLE.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_DETALLE.getTableHeader().setReorderingAllowed(false);
        tb_DETALLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DETALLEMouseClicked(evt);
            }
        });
        tb_DETALLE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_DETALLEKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tb_DETALLE);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_CABECERA = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_CABECERA.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_CABECERA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Título 5"
            }
        ));
        tb_CABECERA.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_CABECERA.setRowHeight(35);
        tb_CABECERA.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_CABECERA.getTableHeader().setReorderingAllowed(false);
        tb_CABECERA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_CABECERAMouseClicked(evt);
            }
        });
        tb_CABECERA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_CABECERAKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tb_CABECERA);

        lblFP.setText("jLabel19");

        lblREF.setText("jLabel19");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCONTADOR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(lblFP)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblREF))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblID_DOCUEMENTO)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblID_CAB)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID_DOCUEMENTO)
                    .addComponent(lblID_CAB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFP)
                    .addComponent(lblREF)
                    .addComponent(lblCONTADOR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscarPaciente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarPaciente.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPaciente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarPaciente.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        txtBuscarPaciente.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarPacienteCaretUpdate(evt);
            }
        });
        txtBuscarPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarPacienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBuscarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
        btnBuscarP.setContentAreaFilled(false);
        btnBuscarP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        btnBuscarP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
        btnBuscarP1.setContentAreaFilled(false);
        btnBuscarP1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarP1ActionPerformed(evt);
            }
        });

        fecha_inicio.setBackground(new java.awt.Color(40, 40, 43));
        fecha_inicio.setForeground(new java.awt.Color(255, 255, 255));
        fecha_inicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
        jpanel.setLayout(jpanelLayout);
        jpanelLayout.setHorizontalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFecha)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarP1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fecha_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpanelLayout.setVerticalGroup(
            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titulo5)
                .addGap(114, 114, 114)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarP1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(24, 24, 24)
                .addComponent(cbFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsu)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(209, 52, 56));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 23)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Exámenes Pendientes");

        lblNomArea.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        lblNomArea.setForeground(new java.awt.Color(209, 52, 56));
        lblNomArea.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNomArea.setText("jLabel3");

        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("jLabel3");

        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("jLabel3");

        lblNumeArea.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeArea.setText("jLabel3");

        lblfecha_I.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblfecha_I.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha_I.setText("  ");

        lblG.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblG.setForeground(new java.awt.Color(255, 255, 255));
        lblG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblG.setText("-");

        lblfecha_F.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblfecha_F.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha_F.setText(" ");

        lblfecha_F2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        lblfecha_F2.setForeground(new java.awt.Color(255, 255, 255));
        lblfecha_F2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfecha_F2.setText(" ");

        jLabel3.setForeground(new java.awt.Color(209, 52, 56));
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(184, 184, 184)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel3)
                                .addGap(296, 296, 296))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblNumeArea)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNomArea, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblfecha_I, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblG)
                        .addGap(10, 10, 10)
                        .addComponent(lblfecha_F, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lblfecha_F2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel15))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(lblHora)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumeArea)
                            .addComponent(lblNomArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblfecha_I)
                    .addComponent(lblG)
                    .addComponent(lblfecha_F)
                    .addComponent(lblfecha_F2))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(230, 230, 230));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imagen/icons8-importar-96.png"))); // NOI18N
        jLabel18.setText("<html> Importando Exámenes Pendientes, Espere…\n<span style=\"font-size:'17px'\">\n<br>&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP&NBSP Esto Puede Tardar unos Segundos</br>\n<br>&NBSP</br>\n<br>&NBSP</br>\n</span></html>");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel5);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Examenes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Examenes.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tb_Examenes.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Examenes.setRowHeight(35);
        tb_Examenes.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Examenes.getTableHeader().setReorderingAllowed(false);
        tb_Examenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ExamenesMouseClicked(evt);
            }
        });
        tb_Examenes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_ExamenesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Examenes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("tab2", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE))
            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFechaActionPerformed
       seleccion();
    }//GEN-LAST:event_cbFechaActionPerformed

    private void tb_ExamenesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ExamenesKeyPressed
       char tecla= evt.getKeyChar();
       
        try {
            
        
                if(tecla==KeyEvent.VK_ENTER){
                    
                    int filaselec=tb_Examenes.getSelectedRow();
                    if( filaselec>=0){
                        cargar_tabla_cabecera_ENTER();

                        if(txtCAB_RESULTADO.getText().length()!=0){
                            txtPersonalRegistraResultado.setEnabled(false);
                            txtPersonalRealizaRes.setEnabled(false);
                            btnPersonalResultado.setEnabled(false);
                            btnPersonalResRealiza.setEnabled(false);
                            tb_CABECERA.setEnabled(true);
                            tb_CABECERA.setBackground(Color.white);
                        }else{
                            btnPersonalResultado.requestFocus();
                            
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(this, "Seleccione un Registro");
                    }
                    
                }
        } catch (Exception e) {
        }
       
                   
    }//GEN-LAST:event_tb_ExamenesKeyPressed

    private void fecha_finKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_finKeyTyped

    }//GEN-LAST:event_fecha_finKeyTyped

    private void ver_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_detalleActionPerformed
        DETALLE.setVisible(true);
        ver_detalle();
    }//GEN-LAST:event_ver_detalleActionPerformed

    private void txtDocumentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDocumentoCaretUpdate
        mostrar_VER_DETALLE();
    }//GEN-LAST:event_txtDocumentoCaretUpdate

    private void tb_ExamenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ExamenesMouseClicked
      if(evt.getClickCount()==2){
         try{
                        if( tb_Examenes.getRowCount()>0){
                        int filaselec=tb_Examenes.getSelectedRow();
                        
                           dispose();
                            RX_EC_EXAMEN_CAB_RESULTADO rx=new RX_EC_EXAMEN_CAB_RESULTADO();
                            rx.setVisible(true);
                            
                            RX_EC_EXAMEN_CAB_RESULTADO.txtID_EXAMEN_CAB.setText(tb_Examenes.getValueAt(filaselec, 10).toString());
                            
                            
                            RX_EC_EXAMEN cno1 = new RX_EC_EXAMEN();
                            cno1.setID_DOCUMENTO(tb_Examenes.getValueAt(filaselec, 10).toString());
                                if(cno1.RX_FUA()==true){
                                       System.out.println("FUA GENERADO");
                                } else {
                                        System.out.println("ERROR GENERAR FUA");
                                }
                            
                            
                            RX_EC_EXAMEN_CAB_RESULTADO.txtAM.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtAM1.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC2.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtDNI.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC3.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
                            
                            RX_EC_EXAMEN_CAB_RESULTADO.txtNombreP.setText(tb_Examenes.getValueAt(filaselec, 2).toString());
//                            RX_EC_EXAMEN_CAB.lblCantidad.setText(tb_Examenes.getValueAt(filaselec, 8).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtEdad.setText(tb_Examenes.getValueAt(filaselec, 5).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtFechaNac.setText(tb_Examenes.getValueAt(filaselec, 4).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtGenero.setText(tb_Examenes.getValueAt(filaselec, 6).toString());
//                            RX_EC_EXAMEN_CAB.lblCantidad.setText(tb_Examenes.getValueAt(filaselec, 8).toString());
//                            lblIDArea.setText(lblNumeArea.getText());
//                            lblNomAD.setText(lblNomArea.getText());
                                                    
                            RX_EC_EXAMEN_CAB_RESULTADO.lblIDArea.setText(lblNumeArea.getText());
                            RX_EC_EXAMEN_CAB_RESULTADO.lblNomA.setText(lblNomArea.getText());
                            

                                  String u=lblUsu.getText();
                            RX_EC_EXAMEN_CAB_RESULTADO.lblUsu.setText(u);
                        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
        }
       }
    }//GEN-LAST:event_tb_ExamenesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {   
            RX_EC_BUSCAR_EXAMEN_RESULTADO R = new RX_EC_BUSCAR_EXAMEN_RESULTADO();
            R.setVisible(false);
            DETALLE.dispose();
            cargar_tabla_cabecera_OK();
         
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbFechaPropertyChange
        
    }//GEN-LAST:event_cbFechaPropertyChange

    private void tb_CABECERAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_CABECERAMouseClicked
        //        if(evt.getClickCount()==1){
            //         try{
                //                        if( tb_examen_det.getRowCount()>0){
                    //                        int filaselec=tb_examen_det.getSelectedRow();
                    //
                    //                            lblId_cod_doc_det.setText(tb_examen_det.getValueAt(filaselec, 0).toString());
                    //                            txtCodigoCPT.setText(tb_examen_det.getValueAt(filaselec, 1).toString());
                    //                            txtNomenclatura.setText(tb_examen_det.getValueAt(filaselec, 2).toString());
                    //
                    //
                    //
                    //                        }
                //        }catch(Exception e){
                //            JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
                //        }
            //       }
    }//GEN-LAST:event_tb_CABECERAMouseClicked

    private void tb_CABECERAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_CABECERAKeyPressed

    }//GEN-LAST:event_tb_CABECERAKeyPressed

    private void tb_DETALLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DETALLEMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_DETALLEMouseClicked

    private void tb_DETALLEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_DETALLEKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_DETALLEKeyPressed

    private void txtBuscarPacienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarPacienteCaretUpdate
        if(cbFecha.isSelected()== true && (fecha_inicio.getDate()==null || fecha_fin.getDate()==null)){
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un rango de fechas");
        }

        if(cbFecha.isSelected()==true){
            buscar_examen();
        }else if(cbFecha.isSelected()==false){
            BuscarPacientesDIA();
        }
    }//GEN-LAST:event_txtBuscarPacienteCaretUpdate

    private void txtBuscarPacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyTyped
        char tecla= evt.getKeyChar();
       if(tecla==KeyEvent.VK_ENTER){
            tb_Examenes.getSelectionModel().setSelectionInterval(0, 0);
            tb_Examenes.requestFocus();
        }
       
       ///LIMITE DE DIGITOS
       if (txtBuscarPaciente.getText().length()>40)
       {
                evt.consume();
       }
    }//GEN-LAST:event_txtBuscarPacienteKeyTyped

    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        try{
          
         String fecha = lblFecha.getText();
         int diaAC = Integer.parseInt(fecha.substring(0, 2));
         int mesAC = Integer.parseInt(fecha.substring(3, 5));
         int anioAC = Integer.parseInt(fecha.substring(6, 10));
        
         String diaIN = new SimpleDateFormat("dd").format(fecha_inicio.getDate());
         String mesIN = new SimpleDateFormat("MM").format(fecha_inicio.getDate());
         String anioIN = new SimpleDateFormat("yyy").format(fecha_inicio.getDate());
         
         
//         int diaIN = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
//         int mesIN = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
//         int anioIN = fecha_inicio.getCalendar().get(Calendar.YEAR);
         
         String diaFN = new SimpleDateFormat("dd").format(fecha_fin.getDate());
         String mesFN = new SimpleDateFormat("MM").format(fecha_fin.getDate());
         String anioFN = new SimpleDateFormat("yyy").format(fecha_fin.getDate());
         
         
//         int diaFN = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
//         int mesFN = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
//         int anioFN = fecha_fin.getCalendar().get(Calendar.YEAR);
         

//            if(fecha_inicio.getDate()==null || fecha_fin.getDate()==null){
//              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de fechas");
//            }else{
//                 if(diaIN > diaAC || mesIN > mesAC || anioIN > anioAC){
//                        JOptionPane.showMessageDialog(rootPane, "Seleccione una fecha de inicio \n menor a la actual: " + diaAC + "-" +
//                        mesAC + "-" + anioAC);
//                        fecha_inicio.setDate(null);
//                 }else{
//                       if(diaFN > diaAC || mesFN > mesAC || anioFN > anioAC){
//                                JOptionPane.showMessageDialog(rootPane, "Seleccione una fecha de termino \n menor a la actual: " + diaAC + "-" +
//                                mesAC + "-" + anioAC);
//                                fecha_fin.setDate(null);
//                       }else{
                                buscar_examen();
                                lblfecha_I.setText(diaIN + "/" + mesIN + "/" + anioIN);
                                lblfecha_F.setText(diaFN + "/" + mesFN + "/" + anioFN);
                                lblG.setVisible(true);
                                txtBuscarPaciente.requestFocus();
//                       }  
//                }
//             
//            }
            
             if(tb_Examenes.getRowCount()==0){
//                lblRegistro.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "No se encontraron registros");
            }else{
//                lblRegistro.setVisible(false);
            }
         
          }catch(Exception e) {
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de fechas");
          }
    }//GEN-LAST:event_btnBuscarPActionPerformed

    private void txtBuscarPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPacienteKeyReleased
         txtBuscarPaciente.setText(txtBuscarPaciente.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarPacienteKeyReleased

    private void btnBuscarP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarP1ActionPerformed

    public void cargar_tabla_cabecera_OK(){
    try{
                        if( tb_Examenes.getRowCount()>0){
                        int filaselec=tb_Examenes.getSelectedRow();
                            
                            dispose();
                            RX_EC_EXAMEN_CAB rx=new RX_EC_EXAMEN_CAB();
                            rx.setVisible(true);
                            RX_EC_EXAMEN_CAB.txtHC.setText(txtHC.getText());
                            RX_EC_EXAMEN_CAB.txtNombreP.setText(txtNombres.getText());
                            RX_EC_EXAMEN_CAB.txtDNI.setText(txtDNI.getText());
                            RX_EC_EXAMEN_CAB.txtFechaNac.setText(lblFechaNaci.getText());
                            RX_EC_EXAMEN_CAB.txtEdad.setText(lblEdad.getText());
                            RX_EC_EXAMEN_CAB.txtGenero.setText(lblGenero.getText());
                            RX_EC_EXAMEN_CAB.txtAM.setText(txtAM.getText());
                            RX_EC_EXAMEN_CAB.txtCodigoDoc.setText(txtDocumento.getText());
//                            RX_EC_EXAMEN_CAB.lblCantidad.setText(lblNumero.getText());
                            
                            RX_EC_EXAMEN_CAB.lblIDArea.setText(lblIDArea.getText());
                            RX_EC_EXAMEN_CAB.lblNomA.setText(lblNomAD.getText());
                            

                                  String u=lblUsuD.getText();
                                  RX_EC_EXAMEN_CAB.lblUsu.setText(u);
                        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "cargar"+e.getMessage());
        }           
    }
    
    public void cargar_tabla_cabecera_ENTER(){
    try{
                        if( tb_Examenes.getRowCount()>0){
                        int filaselec=tb_Examenes.getSelectedRow();
                        
                            dispose();
                            RX_EC_EXAMEN_CAB_RESULTADO rx=new RX_EC_EXAMEN_CAB_RESULTADO();
                            rx.setVisible(true);
                            
                            RX_EC_EXAMEN_CAB_RESULTADO.txtID_EXAMEN_CAB.setText(tb_Examenes.getValueAt(filaselec, 10).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtAM.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtAM1.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC2.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtDNI.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtHC3.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
                            
                            RX_EC_EXAMEN_CAB_RESULTADO.txtNombreP.setText(tb_Examenes.getValueAt(filaselec, 2).toString());
//                            RX_EC_EXAMEN_CAB.lblCantidad.setText(tb_Examenes.getValueAt(filaselec, 8).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtEdad.setText(tb_Examenes.getValueAt(filaselec, 5).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtFechaNac.setText(tb_Examenes.getValueAt(filaselec, 4).toString());
                            RX_EC_EXAMEN_CAB_RESULTADO.txtGenero.setText(tb_Examenes.getValueAt(filaselec, 6).toString());
//                            RX_EC_EXAMEN_CAB.lblCantidad.setText(tb_Examenes.getValueAt(filaselec, 8).toString());
//                            lblIDArea.setText(lblNumeArea.getText());
//                            lblNomAD.setText(lblNomArea.getText());
                                                    
                            RX_EC_EXAMEN_CAB_RESULTADO.lblIDArea.setText(lblNumeArea.getText());
                            RX_EC_EXAMEN_CAB_RESULTADO.lblNomA.setText(lblNomArea.getText());
                            

                                  String u=lblUsu.getText();
                            RX_EC_EXAMEN_CAB_RESULTADO.lblUsu.setText(u);
                        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "cargar"+e);
        }           
    }
    
    public void mostrar_VER_DETALLE(){
        try {
            
            //detalle
            String consulta="";
            tb_Detalle.setModel(new DefaultTableModel());
            String titulos[]={"Cod. Documento Det.","Cod. Nomenclatura","Descripción Nomenclatura",
                           "Incidencia" ,"Placas Usadas", "Medida","Producto"};
            m5=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m5);
            String fila[]=new String[7];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_VER_DETALLE_EXAMEN ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtDocumento.getText());
            ResultSet r= cmd.executeQuery();
            int usado = 0;
            while(r.next()){
//            for (int i=0; i<7; i++){
                        fila[0]=r.getString(1);
                        fila[1]=r.getString(2); 
                        fila[2]=r.getString(3); 
                        fila[3]=r.getString(4);
                        
                        usado = (r.getInt(5));
                
                        BigDecimal bd2 = new BigDecimal(usado);

                        bd2 = bd2.setScale(0, BigDecimal.ROUND_HALF_UP);
                        
                        fila[4]="0" + bd2;
                        fila[5]=r.getString(6); 
                        fila[6]=r.getString(7);
//            }
                        m5.addRow(fila);
            }
            tb_Detalle.setModel(m5);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m5);
            tb_Detalle.setRowSorter(elQueOrdena);
            tb_Detalle.setModel(m5);
                       
            formatoVerDetalle();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public void ver_detalle(){
        int filaselec=tb_Examenes.getSelectedRow();
        txtDocumento.setText(tb_Examenes.getValueAt(filaselec, 10).toString());
        txtAM.setText(tb_Examenes.getValueAt(filaselec, 7).toString());
        txtHC.setText(tb_Examenes.getValueAt(filaselec, 1).toString());
        txtDNI.setText(tb_Examenes.getValueAt(filaselec, 3).toString());
        txtNombres.setText(tb_Examenes.getValueAt(filaselec, 2).toString());
        lblNumero.setText(tb_Examenes.getValueAt(filaselec, 8).toString());
        lblFechaNaci.setText(tb_Examenes.getValueAt(filaselec, 4).toString());
        lblEdad.setText(tb_Examenes.getValueAt(filaselec, 5).toString());
        lblGenero.setText(tb_Examenes.getValueAt(filaselec, 6).toString());
        lblIDArea.setText(lblNumeArea.getText());
        lblNomAD.setText(lblNomArea.getText());
        lblUsuD.setText(lblUsu.getText());
    }
    
    public void seleccion(){
        if(cbFecha.isSelected()==true){
            fecha_inicio.setEnabled(true);
            fecha_fin.setEnabled(true);
            lblfecha_I.setVisible(true);
            lblfecha_F.setVisible(true);
//            lblG.setVisible(true);
            btnBuscarP.setEnabled(true);
        }else{
            fecha_inicio.setEnabled(false);
            fecha_fin.setEnabled(false);
            fecha_inicio.setDate(null);
            fecha_fin.setDate(null);
            lblfecha_I.setVisible(false);
            lblfecha_F.setVisible(false);
            lblG.setVisible(false);
            btnBuscarP.setEnabled(false);
            txtBuscarPaciente.requestFocus();
        }
    }
    
    public void buscar_examen(){
    
           int dia = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
           int mes = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
           int anio = fecha_inicio.getCalendar().get(Calendar.YEAR);
                       
           int fechaI;
                       
                       if(mes<10 && dia<10){
                           fechaI = Integer.parseInt(anio + "0" + mes + "0" + dia);
                       }else{                           
                           if(mes<10 && dia>=10){
                           fechaI = Integer.parseInt(anio + "0"+ mes + dia);
                           }else{
                               if(mes >=10 && dia<10){
                                 fechaI = Integer.parseInt( anio + mes + "0"+ dia);
                               }else{
                                   fechaI = anio +  mes + dia ;
                               }
                           }
                       }

                       
           int dia1 = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
           int mes1 = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
           int anio1 = fecha_fin.getCalendar().get(Calendar.YEAR);
                       
           int fechaF;
                       
                       if(mes1<10 && dia1<10){
                           fechaF = Integer.parseInt(anio1 + "0" + mes1 + "0" + dia1);
                       }else{                           
                           if(mes1<10 && dia1>=10){
                           fechaF = Integer.parseInt(anio1 + "0"+ mes1 +  dia1);
                           }else{
                               if(mes1 >=10 && dia1<10){
                                 fechaF = Integer.parseInt(anio1  + mes1 + "0"+ dia1);
                               }else{
                                   fechaF = anio1 + mes1 + dia1 ;
                               }
                           }
                       }

                       
        
        String buscar="",servicioArea="";
        buscar = txtBuscarPaciente.getText();
        
        
        
        
    String consulta="";
        try {
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Exámen"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[11];

            RX_EC_BUSCAR_EXAMEN_CAJA obj=new RX_EC_BUSCAR_EXAMEN_CAJA();
            consulta="exec RX_EC_BUSCAR_RESULTADO_RX_2 ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,fechaI);
            cmd.setInt(2, fechaF);
            cmd.setString(3, buscar);          
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){               
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                fila[9]=r.getString(10);
                fila[10]=r.getString(11);
                
                m.addRow(fila);
                c++;
            }
            tb_Examenes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Examenes.setRowSorter(elQueOrdena);
            this.tb_Examenes.setModel(m);
            
            formatoExamen();
            
            
            
        } catch (Exception e) {
            System.out.println("Error buscar examen: " + e.getMessage());
        }
    }
    
    public void BuscarPacientesDIA(){
        try {
                     
            String consulta="";
            
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Exámen"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_BUSCAR_EXAMEN_RESULTADO_2 ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
            cmd.setString(1, txtBuscarPaciente.getText());
       
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                m2.addRow(fila);
            }
            tb_Examenes.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_Examenes.setRowSorter(elQueOrdena);
            tb_Examenes.setModel(m2);
                       
            formatoExamen();
            
            
            
        } catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
//    public void BuscarPacientes(){
//        try {
//                     
//            String consulta="";
//            
//            tb_Examenes.setModel(new DefaultTableModel());
//            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
//            "Acto Médico","Cant. Examenes","Fecha Examen","Codigo Documento"};
//            m2=new DefaultTableModel(null,titulos);
//            JTable p=new JTable(m2);
//            String fila[]=new String[11];
//            Usuario obj=new Usuario();
//            consulta="exec RX_EC_BUSCAR_CAJA_RX ?,?,?,?";
//            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
//            cmd.setString(1, txtBuscarPaciente.getText());
//            cmd.setString(2, lblNumeArea.getText());
//            
//            ResultSet r= cmd.executeQuery();
//            while(r.next()){
//            for (int i=0; i<11; i++){
//            fila[i]=r.getString(i+1);
//            }
//                m2.addRow(fila);
//            }
//            tb_Examenes.setModel(m2);
//            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
//            tb_Examenes.setRowSorter(elQueOrdena);
//            tb_Examenes.setModel(m2);
//                       
//            formatoExamen();
//            
//        } catch (Exception e) {
//            System.out.println("Error mostrar paciente: " + e.getMessage());
//        }
//        
//    }
    
    public void mostrarArea(){
        String consulta="";
        try {
            consulta="EXEC RX_EC_SERVICIO ";
            PreparedStatement cmd = DT.getCn().prepareStatement(consulta);        
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                lblNumeArea.setText(r.getString(1));
                lblNomArea.setText(r.getString(2));
            }
            
        } catch (Exception e) {
            System.out.println("Error carga area : " + e.getMessage());
        }
    }   
    
    public void inicializar_tabla_Examenes(){       
        try {
            
            String titulosb[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant. Examenes","Fecha Examen", "Codigo Exámen"};
            msb=new DefaultTableModel(null,titulosb);
            JTable psb=new JTable(msb);
            String filasb[]=new String[11];
            tb_Examenes.setModel(msb);
            TableRowSorter<TableModel> elQueOrdenasb=new TableRowSorter<TableModel>(msb);
            tb_Examenes.setRowSorter(elQueOrdenasb);
            this.tb_Examenes.setModel(msb);
            
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("error inicializar tabla_RV: " + e);
        }
            
    }
        
    public void formatoExamen(){        
              
            tb_Examenes.getColumnModel().getColumn(0).setPreferredWidth(80); 
            tb_Examenes.getColumnModel().getColumn(1).setPreferredWidth(100);
            tb_Examenes.getColumnModel().getColumn(2).setPreferredWidth(350);
            tb_Examenes.getColumnModel().getColumn(3).setPreferredWidth(100);                
            tb_Examenes.getColumnModel().getColumn(4).setPreferredWidth(60); 
            tb_Examenes.getColumnModel().getColumn(5).setPreferredWidth(60);
            tb_Examenes.getColumnModel().getColumn(6).setPreferredWidth(60);
            tb_Examenes.getColumnModel().getColumn(7).setPreferredWidth(80); 
            tb_Examenes.getColumnModel().getColumn(8).setPreferredWidth(100);
            tb_Examenes.getColumnModel().getColumn(9).setPreferredWidth(100);
            tb_Examenes.getColumnModel().getColumn(10).setPreferredWidth(150);
            //Ocultar
            tb_Examenes.getColumnModel().getColumn(0).setMinWidth(0);
            tb_Examenes.getColumnModel().getColumn(0).setMaxWidth(0);    
            tb_Examenes.getColumnModel().getColumn(4).setMinWidth(0);
            tb_Examenes.getColumnModel().getColumn(4).setMaxWidth(0);
    }
    
    public void formatoVerDetalle(){        
              
            tb_Detalle.getColumnModel().getColumn(0).setPreferredWidth(150); 
            tb_Detalle.getColumnModel().getColumn(1).setPreferredWidth(150);
            tb_Detalle.getColumnModel().getColumn(2).setPreferredWidth(400);                      
    }
    
    public void fecha_IF(){
        
         int diaIN = fecha_inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesIN = fecha_inicio.getCalendar().get(Calendar.MONTH) + 1;
         int anioIN = fecha_inicio.getCalendar().get(Calendar.YEAR);
         
         int diaFN = fecha_fin.getCalendar().get(Calendar.DAY_OF_MONTH);
         int mesFN = fecha_fin.getCalendar().get(Calendar.MONTH) + 1;
         int anioFN = fecha_fin.getCalendar().get(Calendar.YEAR);
         
        if(diaIN > diaFN || mesIN > mesFN || anioIN > anioFN){
                 JOptionPane.showMessageDialog(rootPane, "La fecha de inicio debe ser menor \n o igual a la fecha de termino ");
                 fecha_inicio.setDate(null);
                 fecha_fin.setDate(null);
             }
    }
    
    public void mostrarPacientes(){
        try {
                     
            String consulta="";
            
            tb_Examenes.setModel(new DefaultTableModel());
            String titulos[]={"ID_HC","N° HC","Nombre del Paciente","DNI","Fecha Nac.","Edad","Sexo",
            "Acto Médico","Cant Examenes","Fecha Examen","Codigo Exámen"};
            m4=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m4);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec RX_EC_LISTAR_EXAMEN_RESULTADO_2 ";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            //cmd.setString(1, lblNumeArea.getText());
 
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<11; i++){
            fila[i]=r.getString(i+1);
            }
                m4.addRow(fila);
            }
            tb_Examenes.setModel(m4);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m4);
            tb_Examenes.setRowSorter(elQueOrdena);
            tb_Examenes.setModel(m4);
                       
            formatoExamen();
            
        } catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    
    public void cargar_detalle_examen(){
        try {
            
            modelo1 = (DefaultTableModel) tb_Detalle.getModel();
            //limpiarTabla();
            
            if(tb_Detalle.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No hay registros que cargar");
            }else{
            
            //pasar datos de una tabla a otra
            for (int i=0;i<modelo1.getRowCount(); i++){
            String cod_doc_det, cod_nomenclatura, nomenclatura;
            
            cod_doc_det = tb_Detalle.getValueAt(i, 0).toString();
            cod_nomenclatura = tb_Detalle.getValueAt(i, 1).toString();           
            nomenclatura = tb_Detalle.getValueAt(i, 2).toString();
           

            //Cargar los datos a la otra tabla 
            modelo2 = (DefaultTableModel) tb_CABECERA.getModel();
            
            String filaelemento[] = {cod_doc_det, cod_nomenclatura, nomenclatura};
                                 
            modelo2.addRow(filaelemento);                   
            
            }
        }    
        } catch (Exception e) {
        }
    }
    
    public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        return date.format(now);
    }
    
    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechaHoraActual = new java.util.Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    public void cerrar(){
        int eleccion = JOptionPane.showConfirmDialog(rootPane,"¿Desea realmente salir del formulario?","Mensaje de Confirmación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);
        if (eleccion == JOptionPane.YES_OPTION)
        {
            dispose();   
//            PrincipalMDI MDI= new PrincipalMDI();
//            MDI.setVisible(true);  
        }else{
        }
    }
    
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
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RX_EC_BUSCAR_EXAMEN_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RX_EC_BUSCAR_EXAMEN_RESULTADO().setVisible(true);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(RX_EC_BUSCAR_EXAMEN_RESULTADO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DETALLE;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnBuscarP1;
    private javax.swing.JCheckBox cbFecha;
    private com.toedter.calendar.JDateChooser fecha_fin;
    private com.toedter.calendar.JDateChooser fecha_inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblCONTADOR;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblFP;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaNaci;
    private javax.swing.JLabel lblG;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblIDArea;
    private javax.swing.JLabel lblID_CAB;
    private javax.swing.JLabel lblID_DOCUEMENTO;
    private javax.swing.JLabel lblNomAD;
    private javax.swing.JLabel lblNomArea;
    private javax.swing.JLabel lblNumeArea;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblREF;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblUsuD;
    private javax.swing.JLabel lblfecha_F;
    private javax.swing.JLabel lblfecha_F2;
    private javax.swing.JLabel lblfecha_I;
    public static javax.swing.JTable tb_CABECERA;
    public static javax.swing.JTable tb_DETALLE;
    private javax.swing.JTable tb_Detalle;
    public javax.swing.JTable tb_Examenes;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtAM;
    public static javax.swing.JTextField txtBuscarPaciente;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtHC;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JMenuItem ver_detalle;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            if(cbFecha.isSelected()==false){
                mostrarPacientes();
            }
            
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
    }
}
