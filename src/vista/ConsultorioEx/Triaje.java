/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
//import jdk.nashorn.internal.objects.NativeString;
import modelos.Caja.Caja_NuevaVenta;
import modelos.ConsultorioEx.ConsultorioEXTriaje;
import modelos.admisionCentral.MovimientoHistoriaClinica;
import vista.admisionCentral.FrmNuevaHistoriaC;
import static vista.admisionEmergencia.FrmFormatoEmergencia.buscar_HC;
import static vista.admisionEmergencia.FrmFormatoEmergencia.txtNHCTri;

/**
 *
 * @author MYS1
 */
public class Triaje extends javax.swing.JFrame {
ConsultorioEXTriaje adEmerTr = new ConsultorioEXTriaje();
static ConsultorioEXTriaje movHC = new ConsultorioEXTriaje();
static DefaultTableModel m;
byte tg;
byte tge;
    /**
     * Creates new form Triaje
     */
    public Triaje() {
        initComponents();
        mensaje.setVisible(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
       
        tbTriaje.requestFocus();
        listar();
        btnGuardar.setEnabled(false);
        btneditar.setEnabled(false);
        pnlTriaje.setVisible(false);
        jPanel29.setVisible(true);
         btnsubir.setEnabled(false);
        
    
        
         getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
    }
    
        public void listar(){
            adEmerTr.TriajeListarReporte(txtBuscar.getText().toString(), tbTriaje);
            tbTriaje.getColumnModel().getColumn(0).setPreferredWidth(150);//nhc   
            tbTriaje.getColumnModel().getColumn(4).setPreferredWidth(150);//nhc
            tbTriaje.getColumnModel().getColumn(7).setPreferredWidth(50);//nhc
            tbTriaje.setRowHeight(25);
        }
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
});
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void limpiarDatosTriaje(){
        txtNHCTri.setText("");
        txtFC.setText("");
        txtFR.setText("");
        txtPA.setText("");
        txtT.setText("");
        txtPeso.setText("");
        txtTalla.setText("");
        txtIDM.setText("");
        
        txtFC.setEditable(true);
        txtFR.setEditable(true);
        txtPA.setEditable(true);
        txtT.setEditable(true);
        txtPeso.setEditable(true);
        txtTalla.setEditable(true);
        txtIDM.setEditable(true);
    }
     public void habilitarDatosTriaje(){
        txtNHCTri.requestFocus();
        txtNHCTri.setEnabled(true);
        txtNHCTri.setText("");
    }
    
    public String determinarFecha(JDateChooser calendario){
        String fecha = "";
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
        return fecha;
    }
    
    public void Guardar(){
        if(txtPA.getText().equals("")|| txtFR.getText().equals("")){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Asegurese de completar todos los campos");
            b.setVisible(false);
            b1.setVisible(false);
        } else {
            ConsultorioEXTriaje adEmerTr1 = new ConsultorioEXTriaje();
            adEmerTr1.setTriaje_fv_pa(txtPA.getText());
            adEmerTr1.setTriaje_fv_fc(txtFC.getText());
            adEmerTr1.setTriaje_fv_fr(txtFR.getText());
            adEmerTr1.setTriaje_fv_t(txtT.getText());
            adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
            adEmerTr1.setTriaje_talla(txtTalla.getText());
            adEmerTr1.setTRIAJE_IDM(txtIDM.getText());    
            adEmerTr1.setPRESTA(txtIDM1.getText());
            System.out.println(adEmerTr1.getPRESTA());
            adEmerTr1.setCod_usu(adEmerTr1.codUsuario(lblUsu.getText()));
            adEmerTr1.setModulo("CEX");
            adEmerTr1.setId_doc(HC.getText());
            if(adEmerTr1.mantenimientoCXTriaje("I")==true){
                
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(33,115,70)); 
                men.setText("Datos Guardados de forma correcta");
                b.setText("OK");
                b.setVisible(true);
                b1.setVisible(false);
                pnlTriaje.setVisible(false);
                txtNHCTri.setEnabled(false);
                txtNHCTri.setText("");
                limpiarDatosTriaje();
                btnGuardar.setEnabled(false);
                btnsubir.setEnabled(false);
                
                tge=1;
                listar();
            }else {
                mensaje.setVisible(true);
                mensaje.setBackground(new Color(255,91,70)); 
                men.setText("Ocurrio un error, Verifique");
                b.setVisible(false);
                b1.setVisible(false);
                tge=7;
            }
        }
    }
    public void Modificar(){
        ConsultorioEXTriaje adEmerTr1 = new ConsultorioEXTriaje();
        adEmerTr1.setTriaje_id(id.getText());
        adEmerTr1.setTriaje_fv_pa(txtPA.getText());
        adEmerTr1.setTriaje_fv_fc(txtFC.getText());
        adEmerTr1.setTriaje_fv_t(txtT.getText());
        adEmerTr1.setTriaje_fv_peso(txtPeso.getText());
        adEmerTr1.setTriaje_fv_fr(txtFR.getText());
        adEmerTr1.setTriaje_talla(txtTalla.getText());
        adEmerTr1.setTRIAJE_IDM(txtIDM.getText());      
        adEmerTr1.setId_doc(id.getText());    
        adEmerTr1.setCod_usu(adEmerTr1.codUsuario(lblUsu.getText()));
        adEmerTr1.setModulo("CEX");
        adEmerTr1.setPRESTA(txtIDM1.getText()); 
        adEmerTr1.setFECPROPAR(txtPA1.getText());
        System.out.println(adEmerTr1.getFECPROPAR());
        System.out.println(adEmerTr1.getPRESTA());
        System.out.println(adEmerTr1.getId_doc()); 
        System.out.println(adEmerTr1.getCod_usu());
        if(adEmerTr1.mantenimientoCXTriaje("U")==true){
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(33,115,70)); 
            men.setText("Datos Guardados de forma correcta");
            MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
                if(movH.actualizarEstadoMovHC("Salio T",Integer.valueOf(id.getText()),lblUsu.getText())==true)
                        System.out.println("Estado actualizado Recepcion");
                    listar();
            b.setText("OK");
            b.setVisible(true);
            b1.setVisible(false);
            pnlTriaje.setVisible(false);
            txtNHCTri.setEnabled(false);
            txtNHCTri.setText("");
            limpiarDatosTriaje();
            btnGuardar.setEnabled(false);
            btnsubir.setEnabled(false);
            
            tge=1;
            Caja_NuevaVenta F = new Caja_NuevaVenta();
                try { 
                    F.reporteFUA(id.getText());
                } catch (Exception e) {
                }
            listar();
        }else {
            mensaje.setVisible(true);
            mensaje.setBackground(new Color(255,91,70)); 
            men.setText("Ocurrio un error, Verifique");
            b.setVisible(false);
            b1.setVisible(false);
            tge=5;
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

        jDialog1 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTriaje1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btneditar = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            lblUsu = new javax.swing.JLabel();
            btneliminar1 = new javax.swing.JButton();
            btnsubir = new javax.swing.JButton();
            mensaje = new javax.swing.JPanel();
            men = new javax.swing.JLabel();
            b = new javax.swing.JButton();
            b1 = new javax.swing.JButton();
            pnlTriaje = new javax.swing.JPanel();
            jLabel22 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            txtNHCTri = new javax.swing.JTextField();
            jLabel25 = new javax.swing.JLabel();
            txtFR = new javax.swing.JTextField();
            jLabel28 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            jLabel27 = new javax.swing.JLabel();
            txtFC = new javax.swing.JTextField();
            jLabel29 = new javax.swing.JLabel();
            txtT = new javax.swing.JTextField();
            jLabel30 = new javax.swing.JLabel();
            txtPeso = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            txtTalla = new javax.swing.JTextField();
            HC = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            txtIDM = new javax.swing.JTextField();
            txtPA = new javax.swing.JFormattedTextField();
            jLabel5 = new javax.swing.JLabel();
            txtIDM1 = new javax.swing.JTextField();
            jLabel6 = new javax.swing.JLabel();
            txtPA1 = new javax.swing.JFormattedTextField();
            id = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbTriaje = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel29 = new javax.swing.JPanel();
                T5 = new javax.swing.JLabel();
                txtBuscar = new javax.swing.JTextField();
                jLabel23 = new javax.swing.JLabel();

                jDialog1.setAlwaysOnTop(true);
                jDialog1.setMinimumSize(new java.awt.Dimension(897, 335));
                jDialog1.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowActivated(java.awt.event.WindowEvent evt) {
                        jDialog1WindowActivated(evt);
                    }
                    public void windowClosed(java.awt.event.WindowEvent evt) {
                        jDialog1WindowClosed(evt);
                    }
                });

                jPanel3.setBackground(new java.awt.Color(0, 153, 102));

                jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setText("<html>Triaje <span style=\"font-size:'15px'\">Consultorios Externos</span></html>");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(632, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jScrollPane4.setBorder(null);

                tbTriaje1.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbTriaje1.setGridColor(new java.awt.Color(255, 255, 255));
                tbTriaje1.setRowHeight(25);
                tbTriaje1.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbTriaje1.getTableHeader().setReorderingAllowed(false);
                tbTriaje1.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbTriaje1MouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbTriaje1MousePressed(evt);
                    }
                });
                tbTriaje1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbTriaje1KeyPressed(evt);
                    }
                });
                jScrollPane4.setViewportView(tbTriaje1);

                javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
                jDialog1.getContentPane().setLayout(jDialog1Layout);
                jDialog1Layout.setHorizontalGroup(
                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                );
                jDialog1Layout.setVerticalGroup(
                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowActivated(java.awt.event.WindowEvent evt) {
                        formWindowActivated(evt);
                    }
                });

                jPanel1.setBackground(new java.awt.Color(0, 153, 102));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("<html>Triaje <span style=\"font-size:'15px'\">Consultorios Externos</span></html>");

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

                btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('N');
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

                lblUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                lblUsu.setText("Usuario");

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
                                .addComponent(lblUsu)
                                .addGap(16, 16, 16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsubir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(lblUsu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(btnsubir, javax.swing.GroupLayout.Alignment.TRAILING))
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
                        .addContainerGap()
                        .addGroup(mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(men)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pnlTriaje.setBackground(new java.awt.Color(255, 255, 255));

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel22.setForeground(new java.awt.Color(51, 51, 51));
                jLabel22.setText("Paciente");

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                txtNHCTri.setEditable(false);
                txtNHCTri.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
                txtNHCTri.setForeground(new java.awt.Color(51, 51, 51));
                txtNHCTri.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                txtNHCTri.setBorder(null);
                txtNHCTri.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtNHCTriCaretUpdate(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtNHCTri, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtNHCTri, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                jLabel25.setForeground(new java.awt.Color(51, 51, 51));
                jLabel25.setText("Funciones Vitales_____________________________________________________________________________________");

                txtFR.setEditable(false);
                txtFR.setText("0");
                txtFR.setMaximumSize(new java.awt.Dimension(50, 20));
                txtFR.setMinimumSize(new java.awt.Dimension(50, 20));
                txtFR.setSelectionEnd(50);
                txtFR.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtFRKeyPressed(evt);
                    }
                });

                jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel28.setForeground(new java.awt.Color(51, 51, 51));
                jLabel28.setText("FR");

                jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel26.setForeground(new java.awt.Color(51, 51, 51));
                jLabel26.setText("PA");

                jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel27.setForeground(new java.awt.Color(51, 51, 51));
                jLabel27.setText("FC");

                txtFC.setEditable(false);
                txtFC.setMaximumSize(new java.awt.Dimension(50, 20));
                txtFC.setMinimumSize(new java.awt.Dimension(50, 20));
                txtFC.setSelectionEnd(50);
                txtFC.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtFCKeyPressed(evt);
                    }
                });

                jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel29.setForeground(new java.awt.Color(51, 51, 51));
                jLabel29.setText("Tº");

                txtT.setEditable(false);
                txtT.setMaximumSize(new java.awt.Dimension(50, 20));
                txtT.setMinimumSize(new java.awt.Dimension(50, 20));
                txtT.setSelectionEnd(50);
                txtT.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtTKeyPressed(evt);
                    }
                });

                jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel30.setForeground(new java.awt.Color(51, 51, 51));
                jLabel30.setText("Peso");

                txtPeso.setEditable(false);
                txtPeso.setMaximumSize(new java.awt.Dimension(50, 20));
                txtPeso.setMinimumSize(new java.awt.Dimension(50, 20));
                txtPeso.setSelectionEnd(50);
                txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPesoKeyPressed(evt);
                    }
                });

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                jLabel3.setText("Talla");

                txtTalla.setEditable(false);
                txtTalla.setMaximumSize(new java.awt.Dimension(50, 20));
                txtTalla.setMinimumSize(new java.awt.Dimension(50, 20));
                txtTalla.setSelectionEnd(50);
                txtTalla.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtTallaKeyPressed(evt);
                    }
                });

                HC.setForeground(new java.awt.Color(255, 255, 255));
                HC.setText("jLabel2");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                jLabel4.setText("IDMC");

                txtIDM.setEditable(false);
                txtIDM.setMaximumSize(new java.awt.Dimension(50, 20));
                txtIDM.setMinimumSize(new java.awt.Dimension(50, 20));
                txtIDM.setSelectionEnd(50);
                txtIDM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtIDMKeyPressed(evt);
                    }
                });

                txtPA.setEditable(false);
                try {
                    txtPA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##")));
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                txtPA.setText("00/00");
                txtPA.setMaximumSize(new java.awt.Dimension(50, 20));
                txtPA.setMinimumSize(new java.awt.Dimension(50, 20));
                txtPA.setPreferredSize(new java.awt.Dimension(6, 20));
                txtPA.setSelectionEnd(50);
                txtPA.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        txtPAFocusGained(evt);
                    }
                });
                txtPA.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPAActionPerformed(evt);
                    }
                });
                txtPA.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPAKeyPressed(evt);
                    }
                });

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                jLabel5.setText("PRESTACION");

                txtIDM1.setEditable(false);
                txtIDM1.setMaximumSize(new java.awt.Dimension(50, 20));
                txtIDM1.setMinimumSize(new java.awt.Dimension(50, 20));
                txtIDM1.setSelectionEnd(50);
                txtIDM1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtIDM1KeyPressed(evt);
                    }
                });

                jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(51, 51, 51));
                jLabel6.setText("FECHA PROBABLE DE PARTO");

                txtPA1.setEditable(false);
                try {
                    txtPA1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                    ex.printStackTrace();
                }
                txtPA1.setToolTipText("");
                txtPA1.setMaximumSize(new java.awt.Dimension(50, 20));
                txtPA1.setMinimumSize(new java.awt.Dimension(50, 20));
                txtPA1.setPreferredSize(new java.awt.Dimension(6, 20));
                txtPA1.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        txtPA1FocusGained(evt);
                    }
                });
                txtPA1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtPA1ActionPerformed(evt);
                    }
                });
                txtPA1.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtPA1KeyPressed(evt);
                    }
                });

                id.setForeground(new java.awt.Color(255, 255, 255));
                id.setText("jLabel2");

                javax.swing.GroupLayout pnlTriajeLayout = new javax.swing.GroupLayout(pnlTriaje);
                pnlTriaje.setLayout(pnlTriajeLayout);
                pnlTriajeLayout.setHorizontalGroup(
                    pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPA, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel28)
                                .addGap(36, 36, 36)
                                .addComponent(txtFR, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27)
                                .addGap(31, 31, 31)
                                .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtTalla, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDM, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDM1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtPA1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 43, Short.MAX_VALUE))
                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(HC)
                                .addGap(165, 165, 165)))
                        .addContainerGap())
                );
                pnlTriajeLayout.setVerticalGroup(
                    pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTriajeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlTriajeLayout.createSequentialGroup()
                                .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25))
                            .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(HC)
                                .addComponent(id)))
                        .addGap(12, 12, 12)
                        .addGroup(pnlTriajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(txtFR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTalla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIDM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPA1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(txtIDM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPeso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                );

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jScrollPane3.setBorder(null);

                tbTriaje.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ));
                tbTriaje.setGridColor(new java.awt.Color(255, 255, 255));
                tbTriaje.setRowHeight(25);
                tbTriaje.setSelectionBackground(new java.awt.Color(0, 153, 102));
                tbTriaje.getTableHeader().setReorderingAllowed(false);
                tbTriaje.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbTriajeMouseClicked(evt);
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        tbTriajeMousePressed(evt);
                    }
                });
                tbTriaje.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbTriajeKeyPressed(evt);
                    }
                });
                jScrollPane3.setViewportView(tbTriaje);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel29.setBackground(new java.awt.Color(255, 255, 255));

                T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                T5.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        T5MouseClicked(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        T5MouseEntered(evt);
                    }
                });

                txtBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
                txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarCaretUpdate(evt);
                    }
                });

                jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel23.setForeground(new java.awt.Color(51, 51, 51));
                jLabel23.setText(" Busqueda");

                javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
                jPanel29.setLayout(jPanel29Layout);
                jPanel29Layout.setHorizontalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addGap(5, 5, 5)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 199, Short.MAX_VALUE))
                );
                jPanel29Layout.setVerticalGroup(
                    jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(T5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTriaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTriaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        tg=2;
         btnGuardar.setEnabled(true);
         pnlTriaje.setVisible(true);
          jPanel29.setVisible(false);
        btneditar.setEnabled(false);
        txtFC.setEditable(true);
        txtFR.setEditable(true);
        txtPA.setEditable(true);
        txtPeso.setEditable(true);
        txtT.setEditable(true);
        txtTalla.setEditable(true);
        txtIDM.setEditable(true);   
        txtIDM1.setEditable(true);    
        txtPA1.setEditable(true);

        


    }//GEN-LAST:event_btneditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
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
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void tbTriajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMouseClicked
        int fila=tbTriaje.getSelectedRow();
        if(evt.getClickCount()==1){
            txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
            txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila,10)));
            txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
            txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
            txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
            txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
            txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 15)));  
            txtIDM1.setText(String.valueOf(tbTriaje.getValueAt(fila, 16)));
            txtPA1.setText(String.valueOf(tbTriaje.getValueAt(fila, 17)));
            id.setText(String.valueOf(tbTriaje.getValueAt(fila, 1)));
            txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
            System.out.println(String.valueOf(tbTriaje.getValueAt(fila, 17)));
            if ((String.valueOf(tbTriaje.getValueAt(fila, 17))).equals("  /  /    ") || (String.valueOf(tbTriaje.getValueAt(fila, 17))).equals("") )
                txtPA1.setText(String.valueOf(""));
            else
                txtPA1.setText(String.valueOf(tbTriaje.getValueAt(fila, 17)));
            txtFC.setEditable(false);
            txtFR.setEditable(false);
            txtPA.setEditable(false);
            txtT.setEditable(false);
            txtPeso.setEditable(false);
            txtTalla.setEditable(false);
            txtIDM.setEditable(false);       
            txtIDM1.setEditable(false);
            txtPA1.setEditable(false);
        }
        if(evt.getClickCount()==2){
            btnGuardar.setEnabled(false);
            btneditar.setEnabled(true);
            pnlTriaje.setVisible(true);
            jPanel29.setVisible(false);
            btnsubir.setEnabled(true);
            txtFC.setEditable(false);
            txtFR.setEditable(false);
            txtPA.setEditable(false);
            txtPeso.setEditable(false);
            txtT.setEditable(false);
            txtTalla.setEditable(false);
            txtIDM.setEditable(false); 
            txtIDM1.setEditable(false);
            txtPA1.setEditable(false);
        }
    }//GEN-LAST:event_tbTriajeMouseClicked

    private void tbTriajeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriajeMousePressed

    }//GEN-LAST:event_tbTriajeMousePressed
    int fila;
    private void tbTriajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriajeKeyPressed
        int teclaPresionada =  evt.getKeyCode();
        System.out.println(teclaPresionada);
        fila=0;
        if(teclaPresionada==10){
            System.out.println(teclaPresionada+1);
             fila = tbTriaje.getSelectedRow();
            txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
            txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila,10)));
            txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
            txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
            txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
            txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
            txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 15)));  
            txtIDM1.setText(String.valueOf(tbTriaje.getValueAt(fila, 16)));
            
            if ((String.valueOf(tbTriaje.getValueAt(fila, 17))).equals("  /  /    ") || (String.valueOf(tbTriaje.getValueAt(fila, 17))).equals("") )
                txtPA1.setText(String.valueOf("  /  /    "));
            else
                txtPA1.setText(String.valueOf(tbTriaje.getValueAt(fila, 17)));
            id.setText(String.valueOf(tbTriaje.getValueAt(fila, 1)));
            txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
            tg=2;
            btnGuardar.setEnabled(true);
            pnlTriaje.setVisible(true);
            jPanel29.setVisible(false);
            btneditar.setEnabled(false);
            txtFC.setEditable(true);
            txtFR.setEditable(true);
            txtPA.setEditable(true);
            txtPeso.setEditable(true);
            txtT.setEditable(true);
            txtTalla.setEditable(true);
            txtIDM.setEditable(true);
            txtIDM1.setEditable(true);
            txtPA1.setEditable(true);  
            txtPA.requestFocus();
            txtPA.setText("");
            tbTriaje.getSelectionModel().setSelectionInterval(pos, pos);
        }
    }//GEN-LAST:event_tbTriajeKeyPressed

    private void T5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseClicked
       listar();
    }//GEN-LAST:event_T5MouseClicked

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
  if (tge==3 || tge==1 || tge==9){
   mensaje.setVisible(false);  
   }
        if (tge==2){
        Modificar();
        btneditar.setEnabled(false);
   }  
     
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void btnsubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsubirActionPerformed
        pnlTriaje.setVisible(false);
        btnsubir.setEnabled(false);
        
        btnGuardar.setEnabled(false);
        btneditar.setEnabled(false);
        jPanel29.setVisible(true);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnsubirActionPerformed

    private void T5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_T5MouseEntered

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       txtBuscar.requestFocus();
    }//GEN-LAST:event_formWindowActivated
    String txt="";
    int num = 0 ;
    int can=0;  
    int pos=0;
    String dni="";   
    private void tbTriaje1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje1MouseClicked

    private void tbTriaje1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaje1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbTriaje1MousePressed

    private void tbTriaje1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaje1KeyPressed
        int teclaPresionada =  evt.getKeyCode();
        System.out.println(teclaPresionada);
        fila=0;
        if(teclaPresionada==10){
             int fila1 = tbTriaje1.getSelectedRow();
            int pos1=0;
            String codigo = String.valueOf(tbTriaje1.getValueAt(fila1, 1));  
            for (int i = 0; i < tbTriaje.getRowCount(); i++) {
            String codigo1 = String.valueOf(tbTriaje.getValueAt(i,1));
                if(codigo.equals(codigo1)){
                    pos1=i;
                }
            }
            tbTriaje.getSelectionModel().setSelectionInterval(pos1, pos1);
            txtFC.setText(String.valueOf(tbTriaje.getValueAt(pos1, 9)));
            txtFR.setText(String.valueOf(tbTriaje.getValueAt(pos1,10)));
            txtPA.setText(String.valueOf(tbTriaje.getValueAt(pos1, 11)));
            txtPeso.setText(String.valueOf(tbTriaje.getValueAt(pos1, 12)));
            txtT.setText(String.valueOf(tbTriaje.getValueAt(pos1, 13)));
            txtTalla.setText(String.valueOf(tbTriaje.getValueAt(pos1, 14)));
            txtIDM.setText(String.valueOf(tbTriaje.getValueAt(pos1, 15)));
            txtIDM1.setText(String.valueOf(tbTriaje.getValueAt(pos1, 15)));
            id.setText(String.valueOf(tbTriaje.getValueAt(pos1, 1)));
            txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(pos1, 4)));
            tg=2;
            btnGuardar.setEnabled(true);
            pnlTriaje.setVisible(true);
            jPanel29.setVisible(false);
            btneditar.setEnabled(false);
            txtFC.setEditable(true);
            txtFR.setEditable(true);
            txtPA.setEditable(true);
            txtPeso.setEditable(true);
            txtT.setEditable(true);
            txtTalla.setEditable(true);
            txtIDM.setEditable(true); 
            txtIDM1.setEditable(true); 
            txtPA1.setEditable(true); 
            txtPA.setText("");
            jDialog1.setVisible(false);
            txtPA.requestFocus();   
        }
    }//GEN-LAST:event_tbTriaje1KeyPressed

    private void txtPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPAActionPerformed

    private void txtIDMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDMKeyPressed
         char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtIDM1.setText("");
            txtIDM1.requestFocus();

        }
    }//GEN-LAST:event_txtIDMKeyPressed

    private void txtTallaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTallaKeyPressed
        double idm,peso,talla ;
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            peso=Double.parseDouble(txtPeso.getText());       
            talla=Double.parseDouble(txtTalla.getText());
            idm=peso/(talla*talla);
            txtIDM.setText(String.valueOf(idm).substring(0, 5));
            txtIDM1.requestFocus();
        } 
    }//GEN-LAST:event_txtTallaKeyPressed

    private void txtPesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtTalla.setText("");
            txtTalla.requestFocus();

        }
    }//GEN-LAST:event_txtPesoKeyPressed

    private void txtTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtPeso.setText("");
            txtPeso.requestFocus();
        }
    }//GEN-LAST:event_txtTKeyPressed

    private void txtFCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFCKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtT.setText("");
            txtT.requestFocus();
        }
    }//GEN-LAST:event_txtFCKeyPressed

    private void txtFRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFRKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtFC.setText("");
            txtFC.requestFocus();
        }
    }//GEN-LAST:event_txtFRKeyPressed

    private void txtNHCTriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNHCTriCaretUpdate
        //  
    }//GEN-LAST:event_txtNHCTriCaretUpdate

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        txt=txtBuscar.getText().trim();
        num=txt.length();
        System.out.println(txt);
        System.out.println(num);
        System.out.println(txtBuscar.getText().trim());
        if (num>7){
            for (int i = 0; i < tbTriaje.getRowCount(); i++) {
                String codigo = String.valueOf(tbTriaje.getValueAt(i, 2));
                if(codigo.equals(txt)){
                    can+=1;
                    pos=i;
                }
            }
            System.out.println(can);
            fila=0;
            if (can==(1)){
                tbTriaje.getSelectionModel().setSelectionInterval(pos, pos);
                fila = tbTriaje.getSelectedRow();
                txtFC.setText(String.valueOf(tbTriaje.getValueAt(fila, 9)));
                txtFR.setText(String.valueOf(tbTriaje.getValueAt(fila,10)));
                txtPA.setText(String.valueOf(tbTriaje.getValueAt(fila, 11)));
                txtPeso.setText(String.valueOf(tbTriaje.getValueAt(fila, 12)));
                txtT.setText(String.valueOf(tbTriaje.getValueAt(fila, 13)));
                txtTalla.setText(String.valueOf(tbTriaje.getValueAt(fila, 14)));
                txtIDM.setText(String.valueOf(tbTriaje.getValueAt(fila, 15)));
                txtIDM1.setText(String.valueOf(tbTriaje.getValueAt(fila, 15)));
                id.setText(String.valueOf(tbTriaje.getValueAt(fila, 1)));
                txtNHCTri.setText(String.valueOf(tbTriaje.getValueAt(fila, 4)));
                MovimientoHistoriaClinica movH = new MovimientoHistoriaClinica();
                System.out.println(Integer.valueOf(id.getText()));
                if (tbTriaje.getValueAt(fila,5).equals("Salio de Admisión")){
                    if(movH.actualizarEstadoMovHC("Recepc T",Integer.valueOf(id.getText()),lblUsu.getText())==true)
                        System.out.println("Estado actualizado Recepcion");
                    listar();
                }else{
                    tg=2;
                    btnsubir.setEnabled(true);
                    btnGuardar.setEnabled(true);
                    pnlTriaje.setVisible(true);
                    jPanel29.setVisible(false);
                    btneditar.setEnabled(false);
                    txtFC.setEditable(true);
                    txtFR.setEditable(true);
                    txtPA.setEditable(true);
                    txtPeso.setEditable(true);
                    txtT.setEditable(true);
                    txtTalla.setEditable(true);
                    txtIDM.setEditable(true);         
                    txtIDM1.setEditable(true); 
                    txtPA1.setEditable(true); 
                    txtPA.requestFocus();   
                    txtPA.setText("");
                }
            }else  if (can>(1)){
                jDialog1.setVisible(true);
                adEmerTr.TriajeListarReporte(txtBuscar.getText().toString(), tbTriaje1);
                tbTriaje1.getColumnModel().getColumn(0).setPreferredWidth(150);//nhc   
                tbTriaje1.getColumnModel().getColumn(4).setPreferredWidth(150);//nhc
                tbTriaje1.getColumnModel().getColumn(7).setPreferredWidth(50);//nhc   
                tbTriaje1.getColumnModel().getColumn(8).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(8).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(9).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(9).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(10).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(10).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(11).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(11).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(12).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(12).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(13).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(13).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(14).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(14).setMaxWidth(0);
                tbTriaje1.getColumnModel().getColumn(15).setMinWidth(0);
                tbTriaje1.getColumnModel().getColumn(15).setMaxWidth(0);
                tbTriaje1.setRowHeight(25);
                dni=txt;
            }
            num=0;
            can=0;
        }
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void jDialog1WindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowActivated
       txtBuscar.setText("");
       tbTriaje1.getSelectionModel().setSelectionInterval(0, 0);
    }//GEN-LAST:event_jDialog1WindowActivated

    private void txtPAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPAFocusGained
       tbTriaje.getSelectionModel().setSelectionInterval(fila, fila);
       txtBuscar.setText("");
    }//GEN-LAST:event_txtPAFocusGained

    private void jDialog1WindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog1WindowClosed
       txtPA.requestFocus();
    }//GEN-LAST:event_jDialog1WindowClosed

    private void txtPAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPAKeyPressed
       char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtFR.setText("");
            txtFR.requestFocus();
        }  
    }//GEN-LAST:event_txtPAKeyPressed

    private void txtIDM1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDM1KeyPressed
         char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtPA1.setText("");
            txtPA1.requestFocus();
        }  
    }//GEN-LAST:event_txtIDM1KeyPressed

    private void txtPA1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPA1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPA1FocusGained

    private void txtPA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPA1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPA1ActionPerformed

    private void txtPA1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPA1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPA1KeyPressed

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
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Triaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Triaje().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HC;
    private javax.swing.JLabel T5;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnsubir;
    private javax.swing.JLabel id;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JPanel pnlTriaje;
    private javax.swing.JTable tbTriaje;
    private javax.swing.JTable tbTriaje1;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtFC;
    public static javax.swing.JTextField txtFR;
    public static javax.swing.JTextField txtIDM;
    public static javax.swing.JTextField txtIDM1;
    public static javax.swing.JTextField txtNHCTri;
    private javax.swing.JFormattedTextField txtPA;
    private javax.swing.JFormattedTextField txtPA1;
    public static javax.swing.JTextField txtPeso;
    public static javax.swing.JTextField txtT;
    public static javax.swing.JTextField txtTalla;
    // End of variables declaration//GEN-END:variables
}
