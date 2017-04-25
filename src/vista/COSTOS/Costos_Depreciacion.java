/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;

import static vista.COSTOS.BuscarTipoSustentacion.tableTipoSustentacion;
import static vista.COSTOS.TipoSustentacion.fechaActual;
import static vista.COSTOS.TipoSustentacion.lblFecha;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.COSTOS.C_Costos_Depreciacion;
import servicios.Conexion;

/**
 *
 * @author USUARIO
 */
public class Costos_Depreciacion extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion conectar=new Conexion();
    Connection con;    
    DefaultTableModel m;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    String total="";
   
    
    /**
     * Creates new form Costos_Depreciacion
     */
    public Costos_Depreciacion() {
        initComponents();
        txtCodigoDepreciacion.setVisible(false);
        txtCodigoEntradaDetalle.setVisible(false);
        txt_primer_registro.setVisible(false);
        //txtVidaUtilAñosN.setEnabled(false);
        
        setLocationRelativeTo(null);
        Buscar_herramientas_depreciacion.setLocationRelativeTo(null);
        Buscar_Drepreciaciones.setLocationRelativeTo(null);
        
        this.getContentPane().setBackground(Color.WHITE);
        Buscar_Drepreciaciones.getContentPane().setBackground(Color.WHITE);
        Buscar_herramientas_depreciacion.getContentPane().setBackground(Color.WHITE);
        
        con=conectar.conectar();
        cargarHerramientas();
        cargarDepreciacion();
        
        //fecha y hora
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());               
        deshabilitar();
        btnGrabarDepreciacion.setEnabled(false);
        btnModificarDepreciacion.setEnabled(false);
        btnEliminarDepreciacion.setEnabled(false);
           setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        
        //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
               close();

            }
        });
        
 //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();

            }
        });
    }

    
    public void nuevo(){
        String codigo;
        C_Costos_Depreciacion T = new C_Costos_Depreciacion();
       
        
        txtCodigoDepreciacion.setText(T.codDepreciacion());
        
        if(txtCodigoDepreciacion.getText().equalsIgnoreCase("")){
           txtCodigoDepreciacion.setText("CD000000000000000001");
           txtCodigoS.setText("00001");
        }else{
            codigo = txtCodigoDepreciacion.getText().substring(15, 20);
            txtCodigoS.setText(codigo);
        }
    }
    
    private void close(){
        java.awt.Toolkit.getDefaultToolkit().beep();
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir de la ventana?",
                "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){          
            dispose();
        }

    }
    
    
    public void cargarHerramientas(){
        
       DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Codigo Entrada Det.");
       tabla.addColumn("Codigo de Producto");
       tabla.addColumn("Codigo Patrimonial");
       tabla.addColumn("Descripcion de Producto");
       tabla.addColumn("Precio Compra");
       tabla.addColumn("Fecha Compra");
       
       cst=con.prepareCall("{call COSTOS_DEPRECIACION_HERRAMIENTA_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[6];
       for (int i=0; i<6; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tableHerramientas.setModel(tabla);
       formato();
       
       }catch (Exception e){}
      
    }   
    
    public void cargarDepreciacion(){
        
       DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Codigo de Depreciacion");
       tabla.addColumn("Codigo Entrada Detalle");
       tabla.addColumn("Codigo Patrimonial");
       tabla.addColumn("Descripcion de la Herramienta");
       tabla.addColumn("Precio Compra");
       tabla.addColumn("Fecha Compra");
       tabla.addColumn("Fecha fin a Depreciar");
       tabla.addColumn("Vida Util Años");
       tabla.addColumn("Vida Util Meses");
       tabla.addColumn("Depreciacion Mensual");
       tabla.addColumn("Depreciacion Diaria");
       tabla.addColumn("Total Mes a Depreciar");
       tabla.addColumn("Depreciacion Acumulada");
       tabla.addColumn("Valor Neto");
       
       
       cst=con.prepareCall("exec COSTOS_COSTOS_DEPRECIACION_listar");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[14];
       for (int i=0; i<14; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tableDepreciacion.setModel(tabla);
       formatoDepreciacion();
       
       }catch (Exception e){}
      
    }

  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Buscar_herramientas_depreciacion = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHerramientas = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cbxBuscar = new javax.swing.JComboBox();
        txtBuscarHerramienta = new javax.swing.JTextField();
        BTN_BUSCARHERRA = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        Buscar_Drepreciaciones = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDepreciacion = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        BTN_BUSCAR_PROD_DEPRE = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtBuscarDepreciacion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        btnNuevoDepreciacion = new javax.swing.JButton();
        btnGrabarDepreciacion = new javax.swing.JButton();
        btnModificarDepreciacion = new javax.swing.JButton();
        btnEliminarDepreciacion = new javax.swing.JButton();
        btnBuscarDepreciacion = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoPatrimonial = new javax.swing.JTextField();
        btnBuscarHerraminetaDepre = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtValorN = new javax.swing.JLabel();
        txtValorNeto = new javax.swing.JTextField();
        txtdpAcumulada = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotalMesesDepreciar = new javax.swing.JTextField();
        txtfechaFinDepreciar = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtCodigoDepreciacion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtValorCompra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtVidaUtilMeses = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDepreciacionDiaria = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtDepreciacionMensual = new javax.swing.JTextField();
        txtVidaUtilAñosN = new javax.swing.JTextField();
        txtCodigoS = new javax.swing.JTextField();
        txtCodigoEntradaDetalle = new javax.swing.JTextField();
        txt_primer_registro = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        Buscar_herramientas_depreciacion.setTitle("SISGESH .::. Buscar Productos");
        Buscar_herramientas_depreciacion.setAlwaysOnTop(true);
        Buscar_herramientas_depreciacion.setMinimumSize(new java.awt.Dimension(650, 550));

        tableHerramientas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tableHerramientas.setModel(new javax.swing.table.DefaultTableModel(
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
        tableHerramientas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableHerramientas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableHerramientasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableHerramientas);

        jLabel17.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel17.setText("Buscar por: ");

        cbxBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Codigo Patrimonial", "Nombre de Herramienta" }));
        cbxBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBuscarItemStateChanged(evt);
            }
        });

        txtBuscarHerramienta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarHerramientaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarHerramientaKeyTyped(evt);
            }
        });

        BTN_BUSCARHERRA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        BTN_BUSCARHERRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BUSCARHERRAActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
        jLabel18.setText("Buscar Productos");

        javax.swing.GroupLayout Buscar_herramientas_depreciacionLayout = new javax.swing.GroupLayout(Buscar_herramientas_depreciacion.getContentPane());
        Buscar_herramientas_depreciacion.getContentPane().setLayout(Buscar_herramientas_depreciacionLayout);
        Buscar_herramientas_depreciacionLayout.setHorizontalGroup(
            Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                .addGroup(Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                        .addGroup(Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(cbxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscarHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BTN_BUSCARHERRA, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 69, Short.MAX_VALUE))
                    .addGroup(Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        Buscar_herramientas_depreciacionLayout.setVerticalGroup(
            Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Buscar_herramientas_depreciacionLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Buscar_herramientas_depreciacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(cbxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarHerramienta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTN_BUSCARHERRA))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        Buscar_Drepreciaciones.setAlwaysOnTop(true);
        Buscar_Drepreciaciones.setMinimumSize(new java.awt.Dimension(640, 550));
        Buscar_Drepreciaciones.setResizable(false);

        tableDepreciacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tableDepreciacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDepreciacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableDepreciacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableDepreciacionKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableDepreciacion);

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 28)); // NOI18N
        jLabel19.setText("Buscar Depreciación");

        BTN_BUSCAR_PROD_DEPRE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        BTN_BUSCAR_PROD_DEPRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BUSCAR_PROD_DEPREActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel20.setText("Nombre Prod.:");

        txtBuscarDepreciacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarDepreciacionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout Buscar_DrepreciacionesLayout = new javax.swing.GroupLayout(Buscar_Drepreciaciones.getContentPane());
        Buscar_Drepreciaciones.getContentPane().setLayout(Buscar_DrepreciacionesLayout);
        Buscar_DrepreciacionesLayout.setHorizontalGroup(
            Buscar_DrepreciacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buscar_DrepreciacionesLayout.createSequentialGroup()
                .addGroup(Buscar_DrepreciacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Buscar_DrepreciacionesLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabel19))
                    .addGroup(Buscar_DrepreciacionesLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_BUSCAR_PROD_DEPRE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Buscar_DrepreciacionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        Buscar_DrepreciacionesLayout.setVerticalGroup(
            Buscar_DrepreciacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Buscar_DrepreciacionesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel19)
                .addGap(30, 30, 30)
                .addGroup(Buscar_DrepreciacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BTN_BUSCAR_PROD_DEPRE)
                    .addGroup(Buscar_DrepreciacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtBuscarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGESH .::. Depreciación");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Depreciación");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha:");

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("jLabel4");

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hora:");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("jLabel4");

        lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(255, 255, 255));
        lblUsu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
        lblUsu.setText("Usuario");

        btnNuevoDepreciacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        btnNuevoDepreciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevoDepreciacion.setMnemonic('N');
        btnNuevoDepreciacion.setToolTipText("Nuevo (Alt+N)");
        btnNuevoDepreciacion.setContentAreaFilled(false);
        btnNuevoDepreciacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoDepreciacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoDepreciacionActionPerformed(evt);
            }
        });

        btnGrabarDepreciacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        btnGrabarDepreciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGrabarDepreciacion.setMnemonic('G');
        btnGrabarDepreciacion.setToolTipText("Guardar (Alt+G)");
        btnGrabarDepreciacion.setContentAreaFilled(false);
        btnGrabarDepreciacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrabarDepreciacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarDepreciacionActionPerformed(evt);
            }
        });

        btnModificarDepreciacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        btnModificarDepreciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificarDepreciacion.setMnemonic('M');
        btnModificarDepreciacion.setToolTipText("Modificar (Alt+M)");
        btnModificarDepreciacion.setContentAreaFilled(false);
        btnModificarDepreciacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarDepreciacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarDepreciacionActionPerformed(evt);
            }
        });

        btnEliminarDepreciacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        btnEliminarDepreciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminarDepreciacion.setMnemonic('E');
        btnEliminarDepreciacion.setToolTipText("Eliminar (Alt+E)");
        btnEliminarDepreciacion.setContentAreaFilled(false);
        btnEliminarDepreciacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarDepreciacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDepreciacionActionPerformed(evt);
            }
        });

        btnBuscarDepreciacion.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        btnBuscarDepreciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscarDepreciacion.setMnemonic('B');
        btnBuscarDepreciacion.setToolTipText("Buscar (Alt+B)");
        btnBuscarDepreciacion.setContentAreaFilled(false);
        btnBuscarDepreciacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarDepreciacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDepreciacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnNuevoDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGrabarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnEliminarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHora)
                            .addComponent(lblFecha)))
                    .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblFecha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblHora)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoDepreciacion)
                    .addComponent(lblUsu)
                    .addComponent(btnModificarDepreciacion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarDepreciacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDepreciacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGrabarDepreciacion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0))
        );

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel5.setText("Nombre del Producto");

        txtNombreProducto.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel7.setText("Codigo Patrimonial:");

        txtCodigoPatrimonial.setEditable(false);

        btnBuscarHerraminetaDepre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscarHerraminetaDepre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHerraminetaDepreActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtValorN.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        txtValorN.setText("Valor Neto:");

        txtValorNeto.setEditable(false);

        txtdpAcumulada.setEditable(false);
        txtdpAcumulada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdpAcumuladaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel9.setText("Depreciacion Acumulada: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtdpAcumulada, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133)
                .addComponent(txtValorN)
                .addGap(18, 18, 18)
                .addComponent(txtValorNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdpAcumulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorN)
                    .addComponent(txtValorNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel10.setText("Fecha de Compra:");

        jLabel11.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel11.setText("Fecha Fin a Depreciar:");

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel15.setText("Total de Meses a Depreciar:");

        txtTotalMesesDepreciar.setEditable(false);
        txtTotalMesesDepreciar.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txtTotalMesesDepreciarComponentAdded(evt);
            }
        });
        txtTotalMesesDepreciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalMesesDepreciarActionPerformed(evt);
            }
        });

        txtfechaFinDepreciar.setDateFormatString("dd-MM-yyyy");
        txtfechaFinDepreciar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtfechaFinDepreciarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtfechaFinDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtTotalMesesDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfechaFinDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, 0)
                        .addComponent(txtTotalMesesDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        /*txtTotalMesesDepreciar.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                //Aquí agregaremos la funcionalidad que queremos
                //por ejemplo al seleccionar una fecha le mostrare un diálogo con la fecha de hoy
                dp_acumulada_neto();

                //txtTotalMesesDepreciar.setText(total);

            }
        });*/

        jLabel16.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel16.setText("Codigo Depreciacion:");

        txtCodigoDepreciacion.setEditable(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel4.setText("Valor de Compra:");

        txtValorCompra.setEditable(false);
        txtValorCompra.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel8.setText("Vida Util en Años:");

        jLabel12.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel12.setText("Vida Util en Meses:");

        txtVidaUtilMeses.setEditable(false);
        txtVidaUtilMeses.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel14.setText("Depreciacion Diaria:");

        txtDepreciacionDiaria.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel13.setText("Depreciacion Mensual:");

        txtDepreciacionMensual.setEditable(false);
        txtDepreciacionMensual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepreciacionMensualActionPerformed(evt);
            }
        });
        txtDepreciacionMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepreciacionMensualKeyReleased(evt);
            }
        });

        txtVidaUtilAñosN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVidaUtilAñosNKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVidaUtilAñosNKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtVidaUtilMeses)
                    .addComponent(txtValorCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtVidaUtilAñosN, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtDepreciacionMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepreciacionDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(txtVidaUtilMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(0, 0, 0)
                            .addComponent(txtDepreciacionDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(0, 0, 0)
                            .addComponent(txtVidaUtilAñosN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel13)
                            .addGap(0, 0, 0)
                            .addComponent(txtDepreciacionMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        txtCodigoS.setEditable(false);

        txtCodigoEntradaDetalle.setEditable(false);

        txt_primer_registro.setEditable(false);
        txt_primer_registro.setText("G");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
        jLabel21.setText("Salir (Esc)");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
        jLabel22.setText("Nuevo (Alt+N)");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
        jLabel23.setText("Guardar (Alt+G)");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/editar.png"))); // NOI18N
        jLabel24.setText("Modificar (Alt+M)");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
        jLabel25.setText("Eliminar (Alt+E)");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
        jLabel26.setText("Buscar (Alt+B)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(39, 39, 39)
                .addComponent(jLabel23)
                .addGap(33, 33, 33)
                .addComponent(jLabel24)
                .addGap(36, 36, 36)
                .addComponent(jLabel25)
                .addGap(36, 36, 36)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigoS, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(txtCodigoDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(txt_primer_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarHerraminetaDepre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCodigoPatrimonial, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(txtCodigoEntradaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel16)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_primer_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5)
                        .addGap(0, 0, 0)
                        .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoPatrimonial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoEntradaDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarHerraminetaDepre, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtdpAcumuladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdpAcumuladaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdpAcumuladaActionPerformed

    private void txtDepreciacionMensualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepreciacionMensualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepreciacionMensualActionPerformed

    private void btnNuevoDepreciacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoDepreciacionActionPerformed
        txtfechaFinDepreciar.setDate(null); 
        nuevo();
        habilitar();
        limpiar_Nuevo(); 
        txt_primer_registro.setText("G");
        btnGrabarDepreciacion.setEnabled(true);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoDepreciacionActionPerformed

    private void btnBuscarHerraminetaDepreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHerraminetaDepreActionPerformed
        cargarHerramientas();
        txtBuscarHerramienta.setEnabled(false);
        Buscar_herramientas_depreciacion.setVisible(true);
        cbxBuscar.setSelectedIndex(0);
        tableHerramientas.getSelectionModel().setSelectionInterval(0, 0);
        tableHerramientas.requestFocus();
    // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarHerraminetaDepreActionPerformed

    private void tableHerramientasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableHerramientasKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tableHerramientas.getSelectedRow();
              /*TipoSustentacion TS = new TipoSustentacion();
                TS.setVisible(true);
                dispose();*/
             
             txtCodigoEntradaDetalle.setText(String.valueOf(tableHerramientas.getValueAt(fila, 0)));
             txtNombreProducto.setText(String.valueOf(tableHerramientas.getValueAt(fila, 3)));  
             txtCodigoPatrimonial.setText(String.valueOf(tableHerramientas.getValueAt(fila, 2)));
             txtValorCompra.setText(String.valueOf(tableHerramientas.getValueAt(fila, 4)));
             txtFechaCompra.setText(String.valueOf(tableHerramientas.getValueAt(fila, 5)));
             Buscar_herramientas_depreciacion.setVisible(false);
             txtVidaUtilAñosN.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tableHerramientasKeyPressed

    private void txtTotalMesesDepreciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalMesesDepreciarActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalMesesDepreciarActionPerformed

    private void txtTotalMesesDepreciarComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txtTotalMesesDepreciarComponentAdded
      // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalMesesDepreciarComponentAdded

    private void btnGrabarDepreciacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarDepreciacionActionPerformed
        
    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png")); 
    C_Costos_Depreciacion u1=new C_Costos_Depreciacion();
    C_Costos_Depreciacion u2 = new C_Costos_Depreciacion();
    C_Costos_Depreciacion u3 = new C_Costos_Depreciacion();
                       
        try {
            if(txt_primer_registro.getText().equalsIgnoreCase("G")){
               if(txtCodigoDepreciacion.getText().equalsIgnoreCase("")|| txtVidaUtilAñosN.getText().equalsIgnoreCase("") ||
                       txtCodigoEntradaDetalle.getText().equalsIgnoreCase("")
                       || txtfechaFinDepreciar.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Verifique si ha ingresado todos los campos");
            }else if(u2.ver_depreciacion(txtCodigoEntradaDetalle.getText())>0){
              JOptionPane.showMessageDialog(rootPane, "El producto ingresado ya existe\nIntente nuevamente");
                 txtfechaFinDepreciar.setDate(null); 
              try {
                       
                txtNombreProducto.setText("");
                txtCodigoPatrimonial.setText("");
                txtValorCompra.setText("");      
                txtVidaUtilMeses.setText("");
                txtDepreciacionDiaria.setText("");
                txtDepreciacionMensual.setText("");
                txtFechaCompra.setText("");
                txtTotalMesesDepreciar.setText("");
                txtdpAcumulada.setText("");
                txtValorNeto.setText("");
                //cbxVidaUtil.setSelectedItem(0);
                txtVidaUtilAñosN.setText("");
                
      //txtCodigoEntradaDetalle.requestFocus();  
      } catch (Exception e) {
      }
              
            }else {
                   int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                   if(guardar==0){
                       C_Costos_Depreciacion TS = new C_Costos_Depreciacion();
                       TS.setCod_depreciacion(txtCodigoDepreciacion.getText());
                       TS.setCod_entrada_det(txtCodigoEntradaDetalle.getText());
                       TS.setVida_util_años(txtVidaUtilAñosN.getText());
                       //TS.setVida_util_años(cbxVidaUtil.getSelectedItem().toString());
                      
                       TS.setVida_util_meses(Integer.parseInt(txtVidaUtilMeses.getText()));
                       TS.setDepreciacion_mensual(Double.parseDouble(txtDepreciacionMensual.getText()));
                       TS.setDepreciacion_diaria(Double.parseDouble(txtDepreciacionDiaria.getText()));
                       
                       int dia = txtfechaFinDepreciar.getCalendar().get(Calendar.DAY_OF_MONTH);
                       int mes = txtfechaFinDepreciar.getCalendar().get(Calendar.MONTH) + 1;
                       int anio = txtfechaFinDepreciar.getCalendar().get(Calendar.YEAR);
                       
                       String fecha;
                       
                       if(mes<10 && dia<10){
                           fecha = "0" + dia + "/"+ "0" + mes + "/" + anio;
                       }else{                           
                           if(mes<10 && dia>=10){
                           fecha = dia + "/" +"0"+ mes + "/" + anio;
                           }else{
                               if(mes >=10 && dia<10){
                                 fecha = "0"+ dia + "/" + mes + "/" + anio;
                               }else{
                                   fecha = dia + "/" + mes + "/" + anio;
                               }
                           }
                       }

                       TS.setFecha_fin_depreciar(fecha);
                       TS.setTotal_mes_depreciar(Integer.parseInt(txtTotalMesesDepreciar.getText()));
                       TS.setDepreciacion_acumulada(Double.parseDouble(txtdpAcumulada.getText()));
                       TS.setValor_neto(Double.parseDouble(txtValorNeto.getText()));
                       TS.setNom_usu(lblUsu.getText());
                 
                   if(TS.grabarCostosDepreciacion()){
                      JOptionPane.showMessageDialog(this, "Datos Guardados"); 
                      txtfechaFinDepreciar.setDate(null);
                      deshabilitar();                  
                      limpiar();
                      btnGrabarDepreciacion.setEnabled(false);
                  } else{
                      JOptionPane.showMessageDialog(this, "El producto ya se encuentra registrado\nIntente nuevamente");
                      
                  }
                 
                   }
               }
            }else{
                
                if(txtCodigoDepreciacion.getText().equalsIgnoreCase("") ){
                JOptionPane.showMessageDialog(rootPane, "Verifique si ha ingresado todos los campos");
                } else{
                    int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                 if(modificar == 0 ){
                  C_Costos_Depreciacion t = new C_Costos_Depreciacion();                                       
                       t.setCod_depreciacion(txtCodigoDepreciacion.getText());
                       t.setCod_entrada_det(txtCodigoEntradaDetalle.getText());
                       t.setVida_util_años(txtVidaUtilAñosN.getText());
                       //t.setVida_util_años(cbxVidaUtil.getSelectedItem().toString());
                       
                       t.setVida_util_meses(Integer.parseInt(txtVidaUtilMeses.getText()));
                       t.setDepreciacion_mensual(Double.parseDouble(txtDepreciacionMensual.getText()));
                       t.setDepreciacion_diaria(Double.parseDouble(txtDepreciacionDiaria.getText()));
                       
                       int anio = txtfechaFinDepreciar.getCalendar().get(Calendar.YEAR);
                       int mes = txtfechaFinDepreciar.getCalendar().get(Calendar.MONTH) + 1;
                       int dia = txtfechaFinDepreciar.getCalendar().get(Calendar.DAY_OF_MONTH);
                       
                      String fecha;
                       
                       if(mes<10 && dia<10){
                           fecha = "0" + dia + "/"+ "0" + mes + "/" + anio;
                       }else{                           
                           if(mes<10 && dia>=10){
                           fecha = dia + "/" +"0"+ mes + "/" + anio;
                           }else{
                               if(mes >=10 && dia<10){
                                 fecha = "0"+ dia + "/" + mes + "/" + anio;
                               }else{
                                   fecha = dia + "/" + mes + "/" + anio;
                               }
                           }
                       }
                       
                       t.setFecha_fin_depreciar(fecha);
                       t.setTotal_mes_depreciar(Integer.parseInt(txtTotalMesesDepreciar.getText()));
                       t.setDepreciacion_acumulada(Double.parseDouble(txtdpAcumulada.getText()));
                       t.setValor_neto(Double.parseDouble(txtValorNeto.getText()));
                       t.setNom_usu(lblUsu.getText());
                  
                  if(t.modificarDepreciacion()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados");
                      txtfechaFinDepreciar.setDate(null);
                     // limpiar_combo();
                      limpiar();
                      deshabilitar();
                  }

                 }
                }
            }
        } catch (Exception e) {
        }   
        
        
        
        //String codigo = txtCodigoEntradaDetalle.getText();
        //validaCodigo(codigo);
        //TODO add your handling code here:
    }//GEN-LAST:event_btnGrabarDepreciacionActionPerformed

    public void guardar(){

    }
    
    public void validaCodigo(String codigo){
    try {
            C_Costos_Depreciacion C = new C_Costos_Depreciacion();
            
            PreparedStatement cmd = C.getCn().prepareStatement("SELECT cod_entrada_det FROM COSTOS_DEPRECIACION WHERE cod_entrada_det ='"+codigo+"'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){
                JOptionPane.showMessageDialog(null, "El producto con código " + codigo + " ya existe");
                txtfechaFinDepreciar.setDate(null);
                limpiar_Nuevo();
            }else {
                guardar();
                txtfechaFinDepreciar.setDate(null);
                res.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    private void btnBuscarDepreciacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDepreciacionActionPerformed
        cargarDepreciacion();
        Buscar_Drepreciaciones.setVisible(true);
        tableDepreciacion.getSelectionModel().setSelectionInterval(0, 0);
        tableDepreciacion.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarDepreciacionActionPerformed

    private void BTN_BUSCAR_PROD_DEPREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BUSCAR_PROD_DEPREActionPerformed
     String nombre =txtBuscarDepreciacion.getText().toString();              
        
         DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Codigo de Depreciacion");
       tabla.addColumn("Codigo de Producto");
       tabla.addColumn("Codigo Patrimonial");
       tabla.addColumn("Descripcion de la Herramienta");
       tabla.addColumn("Precio Compra");
       tabla.addColumn("Fecha Compra");
       tabla.addColumn("Fecha fin a Depreciar");
       tabla.addColumn("Vida Util Años");
       tabla.addColumn("Vida Util Meses");
       tabla.addColumn("Depreciacion Mensual");
       tabla.addColumn("Depreciacion Diaria");
       tabla.addColumn("Total Mes a Depreciar");
       tabla.addColumn("Depreciacion Acumulada");
       tabla.addColumn("Valor Neto");
       
       cst=con.prepareCall("{call COSTOS_COSTOS_DEPRECIACION_buscarNombreProd(?)}");     
            cst.setString(1, nombre);
            r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[14];
       for (int i=0; i<14; i++){
           dato[i]=r.getString(i+1);

       }
       tabla.addRow(dato);
       }
       this.tableDepreciacion.setModel(tabla);
       tableDepreciacion.getSelectionModel().setSelectionInterval(0, 0);
       tableDepreciacion.requestFocus();
       formatoDepreciacion();
       }catch (Exception e){}
       
       txtBuscarDepreciacion.setText("");
       

        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_BUSCAR_PROD_DEPREActionPerformed

    private void tableDepreciacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableDepreciacionKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tableDepreciacion.getSelectedRow();
              /*TipoSustentacion TS = new TipoSustentacion();
                TS.setVisible(true);
                dispose();*/
             String codigo;
            
             Costos_Depreciacion.txtCodigoDepreciacion.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 0)));
             
             codigo = txtCodigoDepreciacion.getText().substring(15, 20);
             txtCodigoS.setText(codigo);
             
             Costos_Depreciacion.txtCodigoEntradaDetalle.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 1)));
             Costos_Depreciacion.txtCodigoPatrimonial.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 2)));
             Costos_Depreciacion.txtNombreProducto.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 3)));
             Costos_Depreciacion.txtValorCompra.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 4)));
             Costos_Depreciacion.txtFechaCompra.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 5)));
             
             
             /*int filaSeleccionada = tableDepreciacion.getSelectedRow();
             int columnaSeleccionada = tableDepreciacion.getSelectedColumn();
*/
             
             String fechaSeleccionada = (String) tableDepreciacion.getModel().getValueAt(fila, 6);
             try {
       
             DateFormat dfo = new SimpleDateFormat("dd/MM/yyyy");
             Date fecha = dfo.parse(fechaSeleccionada);
             
             txtfechaFinDepreciar.setDate(fecha);
             
            } catch (Exception e) {
            }
             
           
            Costos_Depreciacion.txtVidaUtilAñosN.setText(String.valueOf(tableDepreciacion.getValueAt(fila, 7)));
            //Costos_Depreciacion.cbxVidaUtil.setSelectedItem(String.valueOf(tableDepreciacion.getValueAt(fila, 7)));   
            Costos_Depreciacion.txtVidaUtilMeses.setText(String.valueOf(tableDepreciacion.getValueAt(fila,8)));
            Costos_Depreciacion.txtDepreciacionMensual.setText(String.valueOf(tableDepreciacion.getValueAt(fila,9)));
            Costos_Depreciacion.txtDepreciacionDiaria.setText(String.valueOf(tableDepreciacion.getValueAt(fila,10)));
            Costos_Depreciacion.txtTotalMesesDepreciar.setText(String.valueOf(tableDepreciacion.getValueAt(fila,11)));
            Costos_Depreciacion.txtdpAcumulada.setText(String.valueOf(tableDepreciacion.getValueAt(fila,12)));
            Costos_Depreciacion.txtValorNeto.setText(String.valueOf(tableDepreciacion.getValueAt(fila,13)));
            Buscar_Drepreciaciones.setVisible(false);
             
             btnGrabarDepreciacion.setEnabled(false);
             btnModificarDepreciacion.setEnabled(true);
             btnEliminarDepreciacion.setEnabled(true);
             deshabilitar();
             

        }    
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDepreciacionKeyPressed

    private void btnModificarDepreciacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarDepreciacionActionPerformed
        
        btnGrabarDepreciacion.setEnabled(true);
        habilitar();   
        btnBuscarHerraminetaDepre.setEnabled(false);
        txt_primer_registro.setText("M");
        txtVidaUtilAñosN.requestFocus();

    }//GEN-LAST:event_btnModificarDepreciacionActionPerformed

    private void BTN_BUSCARHERRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BUSCARHERRAActionPerformed
     String nombre =txtBuscarHerramienta.getText().toString();              
        
       DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Codigo Entrada Det.");
       tabla.addColumn("Codigo de Producto");
       tabla.addColumn("Codigo Patrimonial");
       tabla.addColumn("Descripcion de Producto");
       tabla.addColumn("Precio Compra");
       tabla.addColumn("Fecha Compra");
       
       if(cbxBuscar.getSelectedIndex()==1){
            
            cst=con.prepareCall("{call COSTOS_DEPRECIACION_HERREAMIENTAS_buscarCodPatrimonial(?)}");     
            cst.setString(1, nombre);
            r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[6];
       for (int i=0; i<6; i++){
           dato[i]=r.getString(i+1);

       }
       tabla.addRow(dato);
       }
       this.tableHerramientas.setModel(tabla);
       tableHerramientas.getSelectionModel().setSelectionInterval(0, 0);
       tableHerramientas.requestFocus();
       txtBuscarHerramienta.setText("");
       formato(); 
       }
       else{
           if(cbxBuscar.getSelectedIndex()==2){
            
            cst=con.prepareCall("{call COSTOS_DEPRECIACION_HERREAMIENTAS_buscar(?)}");     
            cst.setString(1, nombre);
            r=cst.executeQuery();
            while (r.next()){
            Object dato[]=new  Object[6];
            for (int i=0; i<6; i++){
            dato[i]=r.getString(i+1);

            }
            tabla.addRow(dato);
             }
            this.tableHerramientas.setModel(tabla);
            tableHerramientas.getSelectionModel().setSelectionInterval(0, 0);
            tableHerramientas.requestFocus();
            txtBuscarHerramienta.setText("");
            formato();  
           }
       }
       

       }catch (Exception e){}
       
       //txtBuscarHerramienta.setText("");
       tableHerramientas.getSelectionModel().setSelectionInterval(0, 0);
        tableHerramientas.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_BUSCARHERRAActionPerformed

    private void btnEliminarDepreciacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDepreciacionActionPerformed

         ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 )
            {
                C_Costos_Depreciacion obj = new C_Costos_Depreciacion();
                obj.setCod_depreciacion(txtCodigoDepreciacion.getText());
                if(obj.eliminarDepreciacion()){
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                    txtfechaFinDepreciar.setDate(null);
                    limpiar();
                    deshabilitar();
                    btnGrabarDepreciacion.setVisible(true);
                    btnModificarDepreciacion.setVisible(true);
                    btnEliminarDepreciacion.setVisible(true);
                }
            }
            else{
               limpiar();
            }
        }catch(Exception e){
            
        }
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarDepreciacionActionPerformed

    private void cbxBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscarItemStateChanged
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxBuscar.getSelectedIndex()>0){
                    txtBuscarHerramienta.setEnabled(true);
                    BTN_BUSCARHERRA.setEnabled(true);
                }

            }
            else{
                txtBuscarHerramienta.setEnabled(false);
                BTN_BUSCARHERRA.setEnabled(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarItemStateChanged
    

    
    private void txtVidaUtilAñosNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVidaUtilAñosNKeyReleased
        if(txtNombreProducto.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Seleccione el Producto a Depreciar");
            txtVidaUtilAñosN.setText("");
            btnBuscarHerraminetaDepre.requestFocus();
        }else
        if(txtVidaUtilAñosN.getText().equalsIgnoreCase("")){
          txtVidaUtilMeses.setText("");
          txtDepreciacionDiaria.setText("");
          txtDepreciacionMensual.setText("");
          txtfechaFinDepreciar.setDate(null);
          txtTotalMesesDepreciar.setText("");
          txtdpAcumulada.setText("");
          txtValorNeto.setText("");
          

      }else{
        calcular_Depreciacion();   
        calcular_fecha();
         txtfechaFinDepreciar.setEnabled(true);
      }
       

      
    }//GEN-LAST:event_txtVidaUtilAñosNKeyReleased

    private void txtBuscarHerramientaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarHerramientaKeyTyped
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
           BTN_BUSCARHERRA.doClick();
       }
       
        if(cbxBuscar.getSelectedIndex()==1){
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=' '){
            evt.consume();            
        }
        if(tecla ==' ' && txtBuscarHerramienta.getText().contains(" ")){
            evt.consume();            
        }
       }
       else{
           if(cbxBuscar.getSelectedIndex()==2){
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
           }
       }
       
       
    }//GEN-LAST:event_txtBuscarHerramientaKeyTyped

    private void txtBuscarHerramientaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarHerramientaKeyReleased
      /* if(cbxBuscar.getSelectedIndex()==1){
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=' '){
            evt.consume();            
        }
        if(tecla ==' ' && txtBuscarHerramienta.getText().contains(" ")){
            evt.consume();            
        }
       }
       else{
           if(cbxBuscar.getSelectedIndex()==2){
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isLetter(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
           }
       }*/
    }//GEN-LAST:event_txtBuscarHerramientaKeyReleased

    private void txtDepreciacionMensualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepreciacionMensualKeyReleased

    }//GEN-LAST:event_txtDepreciacionMensualKeyReleased

    private void txtVidaUtilAñosNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVidaUtilAñosNKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtVidaUtilAñosNKeyTyped

    private void txtBuscarDepreciacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDepreciacionKeyTyped
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
           BTN_BUSCAR_PROD_DEPRE.doClick();
       }
    }//GEN-LAST:event_txtBuscarDepreciacionKeyTyped

    private void txtfechaFinDepreciarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtfechaFinDepreciarPropertyChange
               calcular_fecha(); 
        btnGrabarDepreciacion.requestFocus();
    }//GEN-LAST:event_txtfechaFinDepreciarPropertyChange

      public void calcular_Depreciacion(){       
        //en los parametros del DecimalFormat damos el formato
        int vida_util;
        double valor_compra;
        int vida_util_meses;
        double depreciacion_mensual;
        double depreciacion_diaria;

        String cadena="", depre_mensual="",depre_diaria="";
        
        //vida_util = Integer.parseInt(cbxVidaUtil.getSelectedItem().toString());
        vida_util = Integer.parseInt(txtVidaUtilAñosN.getText());
        valor_compra = Double.parseDouble(txtValorCompra.getText());

        
        vida_util_meses = vida_util * 12;
        depreciacion_mensual = (valor_compra/vida_util_meses);
        depreciacion_diaria = (depreciacion_mensual/30);

        cadena = String.valueOf(vida_util_meses);       
       /* depre_mensual = df.format(depreciacion_mensual);
        depre_diaria = df.format(depreciacion_diaria);
  */     //BigDecimal bd1 = new BigDecimal(vida_util_meses);
         BigDecimal bd2 = new BigDecimal(depreciacion_mensual);
         BigDecimal bd3 = new BigDecimal(depreciacion_diaria);
       
         bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
         bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        txtVidaUtilMeses.setText(cadena);
        txtDepreciacionMensual.setText(bd2.toString());
        txtDepreciacionDiaria.setText(bd3.toString());
}
      
      
    public void calcular_fecha(){
        int total_mes;
        String fecha_compra, mes,anio;
       
        String fin_depreciar;
        int mesF,anioF;
        
        try {
        DecimalFormat df = new DecimalFormat("0.00");
        fecha_compra = txtFechaCompra.getText();
        fin_depreciar = txtfechaFinDepreciar.getCalendar().toString();
        
        //guarda el mes del campo fecha_compra        
        mes = fecha_compra.substring(3,5);        
        //guarda el año del campo fecha_compra
        anio = fecha_compra.substring(6,10);
        
        int m = 0,a = 0;
        m = Integer.parseInt(mes);
        a = Integer.parseInt(anio);

        
        //guarda el mes del campo fecha_fin_depreciar
        mesF = txtfechaFinDepreciar.getCalendar().get(Calendar.MONTH) + 1;
 
        //guarda el año del campo fecha_fin_depreciar
        anioF = txtfechaFinDepreciar.getCalendar().get(Calendar.YEAR);

        total_mes = ((anioF - a - 1)*12)+(12 - m) + mesF;
        
        txtTotalMesesDepreciar.setText(String.valueOf(total_mes));  

        int vida_util;
        double valor_compra;
        double vida_util_meses;
        double depreciacion_mensual;
        double depreciacion_diaria;

        String cadena="", depre_mensual="",depre_diaria="";
        
        //vida_util = Integer.parseInt((String)cbxVidaUtil.getSelectedItem().toString());
        vida_util = Integer.parseInt(txtVidaUtilAñosN.getText());
        valor_compra = Double.parseDouble(txtValorCompra.getText());


        vida_util_meses = vida_util * 12;
        depreciacion_mensual = (valor_compra/vida_util_meses);
        depreciacion_diaria = (depreciacion_mensual/30);

        cadena = String.valueOf(vida_util_meses);       
  
        double depAcumulada=0,vneto=0;
        //String dm=df.format(depreciacion_mensual);
        depAcumulada=Math.rint(depreciacion_mensual*100)/100*total_mes;
        vneto=Double.parseDouble(txtValorCompra.getText())-depAcumulada;
               
         BigDecimal bd4 = new BigDecimal(depAcumulada);
         BigDecimal bd5 = new BigDecimal(vneto);
       
         bd4 = bd4.setScale(2, BigDecimal.ROUND_HALF_UP);
         bd5 = bd5.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        txtdpAcumulada.setText(bd4.toString());
        txtValorNeto.setText(bd5.toString());
        } catch (Exception e) {
        }
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
    
    public void deshabilitar(){
      txtCodigoS.setEnabled(false);
      txtNombreProducto.setEnabled(false);
      btnBuscarHerraminetaDepre.setEnabled(false);
      txtCodigoPatrimonial.setEnabled(false);
      txtValorCompra.setEnabled(false);
      //cbxVidaUtil.setEnabled(false);
      txtVidaUtilAñosN.setEnabled(false);
      txtVidaUtilMeses.setEnabled(false);
      txtDepreciacionDiaria.setEnabled(false);
      txtDepreciacionMensual.setEnabled(false);
      txtFechaCompra.setEnabled(false);
      txtfechaFinDepreciar.setEnabled(false);
      txtTotalMesesDepreciar.setEnabled(false);
      txtdpAcumulada.setEnabled(false);
      txtValorNeto.setEnabled(false);     
    }
    
    public void habilitar(){
      txtCodigoS.setEnabled(true);
      txtNombreProducto.setEnabled(true);
      btnBuscarHerraminetaDepre.setEnabled(true);
      txtCodigoPatrimonial.setEnabled(true);
      txtValorCompra.setEnabled(true);
      //cbxVidaUtil.setEnabled(true);
      txtVidaUtilAñosN.setEnabled(true);
      txtVidaUtilMeses.setEnabled(true);
      txtDepreciacionDiaria.setEnabled(true);
      txtDepreciacionMensual.setEnabled(true);
      txtFechaCompra.setEnabled(true);
      txtfechaFinDepreciar.setEnabled(true);
      txtTotalMesesDepreciar.setEnabled(true);
      txtdpAcumulada.setEnabled(true);
      txtValorNeto.setEnabled(true);
      btnBuscarHerraminetaDepre.requestFocus();
    }

        public void habilitar_nuevo(){
      txtCodigoS.setEnabled(true);
      txtNombreProducto.setEnabled(true);
      btnBuscarHerraminetaDepre.setEnabled(true);
      txtCodigoPatrimonial.setEnabled(true);
      txtValorCompra.setEnabled(true);
      //cbxVidaUtil.setEnabled(true);
      txtVidaUtilAñosN.setEnabled(true);
      txtVidaUtilMeses.setEnabled(true);
      txtDepreciacionDiaria.setEnabled(true);
      txtDepreciacionMensual.setEnabled(true);
      
    }
    
    public void limpiar(){
      try {
        
      txtCodigoS.setText("");
      txtNombreProducto.setText("");
      txtCodigoPatrimonial.setText("");
      txtValorCompra.setText("");      
      txtVidaUtilMeses.setText("");
      txtDepreciacionDiaria.setText("");
      txtDepreciacionMensual.setText("");
      txtFechaCompra.setText("");
      txtTotalMesesDepreciar.setText("");
      txtdpAcumulada.setText("");
      txtValorNeto.setText("");
      //cbxVidaUtil.setSelectedIndex(0);
      txtVidaUtilAñosN.setText("");
      btnModificarDepreciacion.setEnabled(false);
      btnEliminarDepreciacion.setEnabled(false);
//txtfechaFinDepreciar.setDate(null);
      //txtCodigoEntradaDetalle.requestFocus();  
      } catch (Exception e) {
      }
    }
    
    public void limpiar_Nuevo(){
      try {           
      txtNombreProducto.setText("");
      txtCodigoPatrimonial.setText("");
      txtValorCompra.setText("");     
      txtVidaUtilMeses.setText("");
      txtDepreciacionDiaria.setText("");
      txtDepreciacionMensual.setText("");
      txtFechaCompra.setText("");
      //fechaFinDepreciar.setEnabled(true);
      txtTotalMesesDepreciar.setText("");
      txtdpAcumulada.setText("");
      txtValorNeto.setText("");
      txtVidaUtilAñosN.setText("");
//cbxVidaUtil.setSelectedIndex(0);
      btnBuscarHerraminetaDepre.requestFocus();
      txtfechaFinDepreciar.setDate(null);
      btnModificarDepreciacion.setEnabled(false);
      btnEliminarDepreciacion.setEnabled(false);
      } catch (Exception e) {
      }
    }
    /*
    public void limpiar_combo(){
         try {

      txtTotalMesesDepreciar.setText("");
      txtdpAcumulada.setText("");
      txtValorNeto.setText("");
      txtfechaFinDepreciar.setDate(null);
      //txtCodigoEntradaDetalle.requestFocus();  
       } catch (Exception e) {
        }
    }*/
    
    public void formato(){
       tableHerramientas.getColumnModel().getColumn(0).setPreferredWidth(180);
       tableHerramientas.getColumnModel().getColumn(1).setPreferredWidth(120);
       tableHerramientas.getColumnModel().getColumn(2).setPreferredWidth(150);
       tableHerramientas.getColumnModel().getColumn(3).setPreferredWidth(250);
       tableHerramientas.getColumnModel().getColumn(4).setPreferredWidth(130);
       tableHerramientas.getColumnModel().getColumn(5).setPreferredWidth(130);
       //Ocultar
       tableHerramientas.getColumnModel().getColumn(0).setMinWidth(0);
       tableHerramientas.getColumnModel().getColumn(0).setMaxWidth(0);
       tableHerramientas.getColumnModel().getColumn(1).setMinWidth(0);
       tableHerramientas.getColumnModel().getColumn(1).setMaxWidth(0);
    }
    
    public void formatoDepreciacion(){
       tableDepreciacion.getColumnModel().getColumn(0).setPreferredWidth(180);
       tableDepreciacion.getColumnModel().getColumn(1).setPreferredWidth(180);
       tableDepreciacion.getColumnModel().getColumn(2).setPreferredWidth(150);
       tableDepreciacion.getColumnModel().getColumn(3).setPreferredWidth(250);
       tableDepreciacion.getColumnModel().getColumn(4).setPreferredWidth(100);
       tableDepreciacion.getColumnModel().getColumn(5).setPreferredWidth(100);
       tableDepreciacion.getColumnModel().getColumn(6).setPreferredWidth(180);
       tableDepreciacion.getColumnModel().getColumn(7).setPreferredWidth(100);       
       tableDepreciacion.getColumnModel().getColumn(8).setPreferredWidth(100);
       tableDepreciacion.getColumnModel().getColumn(9).setPreferredWidth(150);       
       tableDepreciacion.getColumnModel().getColumn(10).setPreferredWidth(150);
       tableDepreciacion.getColumnModel().getColumn(11).setPreferredWidth(150);
       tableDepreciacion.getColumnModel().getColumn(12).setPreferredWidth(180);
       tableDepreciacion.getColumnModel().getColumn(13).setPreferredWidth(100);
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
            java.util.logging.Logger.getLogger(Costos_Depreciacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Depreciacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Depreciacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Depreciacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Depreciacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_BUSCARHERRA;
    private javax.swing.JButton BTN_BUSCAR_PROD_DEPRE;
    private javax.swing.JDialog Buscar_Drepreciaciones;
    public static javax.swing.JDialog Buscar_herramientas_depreciacion;
    private javax.swing.JButton btnBuscarDepreciacion;
    private javax.swing.JButton btnBuscarHerraminetaDepre;
    private javax.swing.JButton btnEliminarDepreciacion;
    private javax.swing.JButton btnGrabarDepreciacion;
    private javax.swing.JButton btnModificarDepreciacion;
    private javax.swing.JButton btnNuevoDepreciacion;
    private javax.swing.JComboBox cbxBuscar;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tableDepreciacion;
    public static javax.swing.JTable tableHerramientas;
    private javax.swing.JTextField txtBuscarDepreciacion;
    private javax.swing.JTextField txtBuscarHerramienta;
    public static javax.swing.JTextField txtCodigoDepreciacion;
    public static javax.swing.JTextField txtCodigoEntradaDetalle;
    public static javax.swing.JTextField txtCodigoPatrimonial;
    private javax.swing.JTextField txtCodigoS;
    public static javax.swing.JTextField txtDepreciacionDiaria;
    public static javax.swing.JTextField txtDepreciacionMensual;
    public static javax.swing.JTextField txtFechaCompra;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtTotalMesesDepreciar;
    public static javax.swing.JTextField txtValorCompra;
    private javax.swing.JLabel txtValorN;
    public static javax.swing.JTextField txtValorNeto;
    public static javax.swing.JTextField txtVidaUtilAñosN;
    public static javax.swing.JTextField txtVidaUtilMeses;
    private javax.swing.JTextField txt_primer_registro;
    public static javax.swing.JTextField txtdpAcumulada;
    private com.toedter.calendar.JDateChooser txtfechaFinDepreciar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
