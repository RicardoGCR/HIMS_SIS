/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.COSTOS;
import modelos.COSTOS.referencialDetalle;
import modelos.COSTOS.referencialCabecera;
import groovy.xml.Entity;
import modelos.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import servicios.Conexion;
import static vista.BUSCAR_USUARIO.tb_Usuario;
import static vista.Principal.fechaActual;

/**
 *
 * @author Profe
 */
public class COSTOS_PRODUCTO_REFERENCIAL extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
DefaultTableModel m;
DefaultTableModel m1;
DefaultTableModel m2;
DefaultTableModel m3;
DefaultTableModel pc;
Conexion c=new Conexion();
    /**
     * Creates new form Usuario
     */
    public COSTOS_PRODUCTO_REFERENCIAL() {
        initComponents();
        c.conectar();
        h1 = new Thread(this);
        h1.start();
        tb_productodetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_productodetalle.doLayout();
        inicializar_tabla();
        this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());

        referencialCabecera csv=new referencialCabecera();
        txtcodigo.setText(csv.codreferencialCabecera());
        if(txtcodigo.getText().equalsIgnoreCase("")){
        txtcodigo.setText("RC000000000000000001");
    }
        txtGuarModif.setVisible(false);
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
//        cargarProductoCabecera();
//        formatoProductoCabecera();
        PRODUCTO_REFERENCIAL.setLocationRelativeTo(this);
        PRODUCTO_REFERENCIAL.getContentPane().setBackground(Color.WHITE);
        
        addEscapeListenerWindowDialog(PRODUCTOS);
        addEscapeListenerWindowDialog(PRODUCTO_REFERENCIAL);
        
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    
        //para no intercambiar columnas
        tbProductoAlmacen.getTableHeader().setReorderingAllowed(false);
        tb_productodetalle.getTableHeader().setReorderingAllowed(false);
        //para limpiar el txt al darle click
// txtBuscar.addFocusListener(new FocusListener() {
//    @Override
//    public void focusGained(FocusEvent e) {
//  txtBuscar.setText("");
//    }
//
//    @Override
//    public void focusLost(FocusEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//} );
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
    
    public static void addEscapeListenerWindowDialog( final JDialog windowDialog) {
       ActionListener escAction = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    windowDialog.dispose();
        }
    };
    windowDialog.getRootPane().registerKeyboardAction(escAction,
    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
    JComponent.WHEN_IN_FOCUSED_WINDOW);
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
     
     public void inicializar_tabla(){
     try{
    String titulos[]={"Codigo","Nombre del Producto","Clase producto","Descripcion de la clase producto", "Marca",
                "Unidad medida","Presentacion","Fabricante"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];
            tb_productodetalle.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_productodetalle.setRowSorter(elQueOrdena);
            this.tb_productodetalle.setModel(m);
            formatoInicializarTabla();

             } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
}
     public void formatoInicializarTabla(){
         tb_productodetalle.getColumnModel().getColumn(0).setPreferredWidth(140);
    tb_productodetalle.getColumnModel().getColumn(1).setPreferredWidth(250);
    tb_productodetalle.getColumnModel().getColumn(2).setPreferredWidth(150);
    tb_productodetalle.getColumnModel().getColumn(3).setPreferredWidth(190);
    tb_productodetalle.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_productodetalle.getColumnModel().getColumn(5).setPreferredWidth(120);
    tb_productodetalle.getColumnModel().getColumn(6).setPreferredWidth(150);
    tb_productodetalle.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_productodetalle.getColumnModel().getColumn(8).setPreferredWidth(130);

     }
     public void cargarProducto(){
    try {
             String titulos[]={"Nº","Codigo","Nombre del Producto","Clase producto","Descripcion de la clase producto", "Marca",
                "Unidad medida","Lote","Fabricante"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[16];
        String consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_ALMACEN_listar";
        ResultSet r;
        r=c.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
            fila[7]=r.getString(7);
            fila[8]=r.getString(8);
                m2.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m2);
    } catch (Exception e) {
    }
}
    public void formatoProducto(){
    tbProductoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(55);
    tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(190);
    tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(300);
    tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(120);
    tbProductoAlmacen.getColumnModel().getColumn(5).setPreferredWidth(100);
    tbProductoAlmacen.getColumnModel().getColumn(6).setPreferredWidth(100);
    tbProductoAlmacen.getColumnModel().getColumn(7).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(8).setPreferredWidth(150);
    
}
    
     public void cargarProductoCabecera(){
    try {
             String titulos[]={"Nº","Codigo","Nombre del Producto Referencial"};
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[4];
            referencialCabecera obj=new referencialCabecera();
        String consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_listar";
        ResultSet r;
        r=c.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                pc.addRow(fila);
                c++;
            }
            tbProductoReferencial.setModel(pc);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(pc);
            tbProductoReferencial.setRowSorter(elQueOrdena);
            this.tbProductoReferencial.setModel(pc);
    } catch (Exception e) {
    }
}
    public void formatoProductoCabecera(){
    tbProductoReferencial.getColumnModel().getColumn(0).setPreferredWidth(50);
    tbProductoReferencial.getColumnModel().getColumn(1).setPreferredWidth(200);
    tbProductoReferencial.getColumnModel().getColumn(2).setPreferredWidth(300);
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PRODUCTOS = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductoAlmacen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jpanel1 = new javax.swing.JPanel();
            titulo6 = new javax.swing.JLabel();
            txtBuscar = new javax.swing.JTextField();
            jLabel11 = new javax.swing.JLabel();
            PRODUCTO_REFERENCIAL = new javax.swing.JDialog();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbProductoReferencial = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jpanel3 = new javax.swing.JPanel();
                titulo8 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                txtBuscarProductoCabecera = new javax.swing.JTextField();
                btnBuscarProductoCabecera = new javax.swing.JButton();
                jPopupMenu1 = new javax.swing.JPopupMenu();
                jMenuItem1 = new javax.swing.JMenuItem();
                jSeparator1 = new javax.swing.JPopupMenu.Separator();
                jMenuItem2 = new javax.swing.JMenuItem();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                lblFecha = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                lblHora = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                btnmodificar = new javax.swing.JButton();
                btnBuscar = new javax.swing.JButton();
                jLabel18 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                txtProducto = new javax.swing.JTextField();
                txtcodigo = new javax.swing.JTextField();
                jScrollPane1 = new javax.swing.JScrollPane();
                tb_productodetalle = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    btnAgregar = new javax.swing.JButton();
                    btnQuitar = new javax.swing.JButton();
                    jLabel1 = new javax.swing.JLabel();
                    txtGuarModif = new javax.swing.JTextField();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel5 = new javax.swing.JLabel();
                    jLabel6 = new javax.swing.JLabel();
                    jLabel7 = new javax.swing.JLabel();
                    jLabel8 = new javax.swing.JLabel();
                    jLabel9 = new javax.swing.JLabel();
                    jLabel10 = new javax.swing.JLabel();

                    PRODUCTOS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    PRODUCTOS.setAlwaysOnTop(true);
                    PRODUCTOS.setAutoRequestFocus(false);
                    PRODUCTOS.setMinimumSize(new java.awt.Dimension(797, 385));
                    PRODUCTOS.setResizable(false);

                    jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tbProductoAlmacen.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                    tbProductoAlmacen.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "N°", "Código", "Nombre del Producto", "Clase Producto", "Descripción de la Clase Producto", "Marca", "Unidad de Medida", "Lote", "Fabricante"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false, false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tbProductoAlmacen.setComponentPopupMenu(jPopupMenu1);
                    tbProductoAlmacen.setRowHeight(25);
                    tbProductoAlmacen.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tbProductoAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbProductoAlmacenMouseClicked(evt);
                        }
                    });
                    tbProductoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbProductoAlmacenKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            tbProductoAlmacenKeyTyped(evt);
                        }
                    });
                    jScrollPane2.setViewportView(tbProductoAlmacen);

                    jpanel1.setBackground(new java.awt.Color(102, 102, 102));

                    titulo6.setBackground(new java.awt.Color(0, 102, 102));
                    titulo6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo6.setForeground(new java.awt.Color(255, 255, 255));
                    titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo6.setText("Catálogo de Productos");
                    titulo6.setToolTipText("");
                    titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                    txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarCaretUpdate(evt);
                        }
                    });
                    txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscarActionPerformed(evt);
                        }
                    });
                    txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyTyped(evt);
                        }
                    });

                    jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel11.setText("Búsqueda por Nombre del Producto");

                    javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                    jpanel1.setLayout(jpanel1Layout);
                    jpanel1Layout.setHorizontalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(titulo6)
                                .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jpanel1Layout.setVerticalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(titulo6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel11))
                    );

                    javax.swing.GroupLayout PRODUCTOSLayout = new javax.swing.GroupLayout(PRODUCTOS.getContentPane());
                    PRODUCTOS.getContentPane().setLayout(PRODUCTOSLayout);
                    PRODUCTOSLayout.setHorizontalGroup(
                        PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    );
                    PRODUCTOSLayout.setVerticalGroup(
                        PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PRODUCTOSLayout.createSequentialGroup()
                            .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
                    );

                    PRODUCTO_REFERENCIAL.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    PRODUCTO_REFERENCIAL.setAlwaysOnTop(true);
                    PRODUCTO_REFERENCIAL.setAutoRequestFocus(false);
                    PRODUCTO_REFERENCIAL.setMinimumSize(new java.awt.Dimension(558, 570));
                    PRODUCTO_REFERENCIAL.setResizable(false);

                    jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tbProductoReferencial.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "N°", "Codigo", "Nombre del Producto Referencial"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tbProductoReferencial.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbProductoReferencial.setRowHeight(25);
                    tbProductoReferencial.setSelectionBackground(new java.awt.Color(102, 102, 102));
                    tbProductoReferencial.getTableHeader().setReorderingAllowed(false);
                    tbProductoReferencial.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbProductoReferencialMouseClicked(evt);
                        }
                    });
                    tbProductoReferencial.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbProductoReferencialKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            tbProductoReferencialKeyTyped(evt);
                        }
                    });
                    jScrollPane3.setViewportView(tbProductoReferencial);
                    if (tbProductoReferencial.getColumnModel().getColumnCount() > 0) {
                        tbProductoReferencial.getColumnModel().getColumn(0).setResizable(false);
                    }

                    jpanel3.setBackground(new java.awt.Color(102, 102, 102));

                    titulo8.setBackground(new java.awt.Color(0, 102, 102));
                    titulo8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo8.setForeground(new java.awt.Color(255, 255, 255));
                    titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo8.setText("Producto Referencial");
                    titulo8.setToolTipText("");
                    titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
                    jLabel13.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel13.setText("Búsqueda por Nombre del Producto");

                    txtBuscarProductoCabecera.setForeground(new java.awt.Color(0, 51, 51));
                    txtBuscarProductoCabecera.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscarProductoCabeceraCaretUpdate(evt);
                        }
                    });
                    txtBuscarProductoCabecera.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscarProductoCabeceraActionPerformed(evt);
                        }
                    });
                    txtBuscarProductoCabecera.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarProductoCabeceraKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtBuscarProductoCabeceraKeyTyped(evt);
                        }
                    });

                    btnBuscarProductoCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
                    btnBuscarProductoCabecera.setBorder(null);
                    btnBuscarProductoCabecera.setContentAreaFilled(false);
                    btnBuscarProductoCabecera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarProductoCabecera.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarProductoCabeceraActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
                    jpanel3.setLayout(jpanel3Layout);
                    jpanel3Layout.setHorizontalGroup(
                        jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel3Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanel3Layout.createSequentialGroup()
                                    .addComponent(txtBuscarProductoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarProductoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(titulo8)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jpanel3Layout.setVerticalGroup(
                        jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel3Layout.createSequentialGroup()
                            .addComponent(titulo8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBuscarProductoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarProductoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(jLabel13)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout PRODUCTO_REFERENCIALLayout = new javax.swing.GroupLayout(PRODUCTO_REFERENCIAL.getContentPane());
                    PRODUCTO_REFERENCIAL.getContentPane().setLayout(PRODUCTO_REFERENCIALLayout);
                    PRODUCTO_REFERENCIALLayout.setHorizontalGroup(
                        PRODUCTO_REFERENCIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    );
                    PRODUCTO_REFERENCIALLayout.setVerticalGroup(
                        PRODUCTO_REFERENCIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PRODUCTO_REFERENCIALLayout.createSequentialGroup()
                            .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
                    );

                    jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    jMenuItem1.setText("Agregar");
                    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jMenuItem1ActionPerformed(evt);
                        }
                    });
                    jPopupMenu1.add(jMenuItem1);
                    jPopupMenu1.add(jSeparator1);

                    jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jMenuItem2.setText("Regresar");
                    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jMenuItem2ActionPerformed(evt);
                        }
                    });
                    jPopupMenu1.add(jMenuItem2);

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    setResizable(false);
                    addWindowListener(new java.awt.event.WindowAdapter() {
                        public void windowOpened(java.awt.event.WindowEvent evt) {
                            formWindowOpened(evt);
                        }
                    });

                    jpanel.setBackground(new java.awt.Color(102, 102, 102));

                    titulo5.setBackground(new java.awt.Color(0, 102, 102));
                    titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo5.setForeground(new java.awt.Color(255, 255, 255));
                    titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo5.setText("Producto Referencial");
                    titulo5.setToolTipText("");
                    titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                    jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel14.setText("Fecha:");

                    lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                    lblFecha.setForeground(new java.awt.Color(255, 255, 255));
                    lblFecha.setText("00/00/00");

                    jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                    jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel15.setText("Hora:");

                    lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
                    lblHora.setForeground(new java.awt.Color(255, 255, 255));
                    lblHora.setText("00:00:00");

                    lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
                    lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                    lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                    lblUsu.setText("Usuario");

                    btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
                    btnNuevo.setMnemonic('N');
                    btnNuevo.setToolTipText("Nuevo (Alt+N)");
                    btnNuevo.setContentAreaFilled(false);
                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoActionPerformed(evt);
                        }
                    });

                    btnguardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                    btnguardar.setMnemonic('G');
                    btnguardar.setToolTipText("Guardar (Alt-G)");
                    btnguardar.setContentAreaFilled(false);
                    btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnguardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnguardarActionPerformed(evt);
                        }
                    });

                    btneliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                    btneliminar.setMnemonic('E');
                    btneliminar.setToolTipText("Eliminar (Alt+E)");
                    btneliminar.setContentAreaFilled(false);
                    btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneliminarActionPerformed(evt);
                        }
                    });

                    btnmodificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                    btnmodificar.setMnemonic('M');
                    btnmodificar.setToolTipText("Modificar (Alt-M)");
                    btnmodificar.setContentAreaFilled(false);
                    btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnmodificar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnmodificarActionPerformed(evt);
                        }
                    });

                    btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscar.setMnemonic('B');
                    btnBuscar.setToolTipText("Buscar (Alt+B)");
                    btnBuscar.setContentAreaFilled(false);
                    btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                    jpanel.setLayout(jpanelLayout);
                    jpanelLayout.setHorizontalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelLayout.createSequentialGroup()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(titulo5))
                            .addGap(399, 399, 399)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblFecha)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jpanelLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblHora))
                                .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                    );
                    jpanelLayout.setVerticalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
                            .addGap(0, 0, 0)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(lblHora))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblUsu)
                            .addContainerGap())
                    );

                    jLabel18.setText("Código:");

                    jLabel3.setText("Nombre del Producto:");

                    txtcodigo.setEnabled(false);

                    tb_productodetalle.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_productodetalle.setRowHeight(20);
                    tb_productodetalle.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_productodetalleKeyPressed(evt);
                        }
                    });
                    jScrollPane1.setViewportView(tb_productodetalle);

                    btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
                    btnAgregar.setText("Agregar");
                    btnAgregar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAgregarActionPerformed(evt);
                        }
                    });

                    btnQuitar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menos.png"))); // NOI18N
                    btnQuitar.setText("Quitar");
                    btnQuitar.setPreferredSize(new java.awt.Dimension(109, 33));
                    btnQuitar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnQuitarActionPerformed(evt);
                        }
                    });

                    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    jLabel1.setText("Detalle del Producto");

                    txtGuarModif.setText("G");

                    jPanel1.setBackground(new java.awt.Color(204, 204, 204));

                    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jLabel5.setText("Salir (Esc)");

                    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
                    jLabel6.setText("Nuevo (Alt+N)");

                    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                    jLabel7.setText("Guardar (Alt+G)");

                    jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/editar.png"))); // NOI18N
                    jLabel8.setText("Modificar (Alt+M)");

                    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
                    jLabel9.setText("Eliminar (Alt+E)");

                    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    jLabel10.setText("Buscar (Alt+B)");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6)
                            .addGap(42, 42, 42)
                            .addComponent(jLabel7)
                            .addGap(36, 36, 36)
                            .addComponent(jLabel8)
                            .addGap(39, 39, 39)
                            .addComponent(jLabel9)
                            .addGap(39, 39, 39)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addContainerGap())
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(293, 293, 293)
                                            .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(205, 205, 205)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel18))
                                            .addGap(24, 24, 24)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtProducto)
                                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(147, 147, 147))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)))
                            .addGap(31, 31, 31)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    getAccessibleContext().setAccessibleName("SISGESH .::. PRODUCTO REFERENCIAL");

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        enableDatos();
        limpiar();
        btnguardar.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnQuitar.setEnabled(true);
        inicializar_tabla();
        
            
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png")); 
 referencialCabecera rc1=new referencialCabecera();
  referencialCabecera rc2=new referencialCabecera();
    referencialCabecera rc3=new referencialCabecera();
        referencialCabecera rc4=new referencialCabecera();
       
      try{
          
          if(txtGuarModif.getText().equalsIgnoreCase("G")){
            if(txtProducto.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Ingresar Nombre del Producto");
          }  
          else if((rc2.ver_referencialCabecera(txtProducto.getText()))>0){
              JOptionPane.showMessageDialog(rootPane, "El Producto ingresado ya existe\nIntente nuevamente");
              txtProducto.setText("");
              txtProducto.requestFocus();
          }else{
              
              
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar == 0 ){
                   referencialCabecera rcGUARDAR=new referencialCabecera();
                  rcGUARDAR.setCod_prod_refe(txtcodigo.getText());
                  rcGUARDAR.setNombre_producto_ref(txtProducto.getText());
                  rcGUARDAR.setNom_usu(lblUsu.getText());
  
                  if(rcGUARDAR.guardarReferencialCabecera()){
                      JOptionPane.showMessageDialog(this, "Datos Guardados");
                      
                      guardarDetalle();
                      limpiar();
                      enableDatos();
                      btnmodificar.setEnabled(false);
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Producto ya se encuentra registrado\nIntente nuevamente");   
                 }
                       
                
          }}}else{
              if(txtProducto.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Verifique si ha ingresado todos los campos");
          } 
              else if(rc3.codigo(txtProducto.getText()).equalsIgnoreCase(txtcodigo.getText())==false && rc4.ver_referencialCabecera(txtProducto.getText())>0 ){
                  JOptionPane.showMessageDialog(rootPane, "El Producto ingresado ya existe\nIntente nuevamente");
              txtProducto.setText("");
              txtProducto.requestFocus();
              }else{
              int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  referencialCabecera r=new referencialCabecera();
                  r.setCod_prod_refe(txtcodigo.getText());
                  r.setNombre_producto_ref(txtProducto.getText());
                  r.setNom_usu(lblUsu.getText());
                  
                  referencialDetalle rdeliminar=new referencialDetalle();
                  rdeliminar.setCod_prod_refe(txtcodigo.getText());
                  
                  if(r.modificarReferencialCabecera()&& rdeliminar.eliminarReferencialDetalle()){
                      guardarDetalle();
                      JOptionPane.showMessageDialog(this, "Datos Modificados");
                      limpiar();
                      enableDatos();
                      btnmodificar.setEnabled(false);
                  }
                  else{
                      JOptionPane.showMessageDialog(this, "El Servicio ya se encuentra registrado\nIntente nuevamente");

                      
                  }}
                  }}
          
            }catch(Exception e) {
              JOptionPane.showMessageDialog(this, e.getMessage());
              
          }
      

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

enableDatos();
                        txtGuarModif.setText("M");
                        btnguardar.setEnabled(true);
                         btneliminar.setEnabled(false);
                         tb_productodetalle.setEnabled(true);
                          btnAgregar.setEnabled(true);
                         btnQuitar.setEnabled(true);
                         tb_productodetalle.setBackground(Color.white);
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

         ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 )
            {
                referencialCabecera rceliminar=new referencialCabecera();
                  rceliminar.setCod_prod_refe(txtcodigo.getText());
                  
                  referencialDetalle rdeliminar=new referencialDetalle();
                  rdeliminar.setCod_prod_refe(txtcodigo.getText());
                  
                if(rceliminar.eliminarReferencialCabecera() && rdeliminar.eliminarReferencialDetalle())
                {
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                       btnguardar.setEnabled(true);
                       btnmodificar.setEnabled(false);
                       btneliminar.setEnabled(false);
                       
                          btnAgregar.setEnabled(true);
                         btnQuitar.setEnabled(true);
                    limpiar();
                    enableDatos();
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Personal a eliminar");
        }
        
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        PRODUCTO_REFERENCIAL.setVisible(true);
        tbProductoReferencial.getSelectionModel().setSelectionInterval(0, 0);
        tbProductoReferencial.requestFocus();
        cargarProductoCabecera();
        formatoProductoCabecera();
        txtBuscarProductoCabecera.requestFocus();
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here: 

        
        PRODUCTOS.setVisible(true);
        PRODUCTOS.getContentPane().setBackground(Color.WHITE);
        PRODUCTOS.setLocationRelativeTo(null);//en el centro
        
//        cargarProducto();
        formatoProducto();
        txtBuscar.requestFocus();
                     
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
   
        try{
            int filaselec=tb_productodetalle.getSelectedRow();
            if( filaselec>=0){   
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Producto?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(eliminar == 0 ){
       
                    DefaultTableModel modelo = (DefaultTableModel)tb_productodetalle.getModel(); 
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Producto a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tbProductoAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoAlmacenMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tbProductoAlmacenMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped
public void buscar_producto_almacen(){
     String consulta="";
        try {
            tbProductoAlmacen.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Producto","Clase producto","Descripcion de la clase producto", "Marca",
                "Unidad medida","Lote","Fabricante"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[15];

            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_ALMACEN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                fila[6]=r.getString(6);
                fila[7]=r.getString(7);
                fila[8]=r.getString(8);
                m3.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m3);
            formatoProducto();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}
    private void tbProductoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tbProductoAlmacenKeyTyped

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
        // TODO add your handling code here:
//         char tecla= evt.getKeyChar();
//                if(tecla==KeyEvent.VK_ENTER){
//                     PRODUCTOS.setVisible(false);
//                    mostrarDetalle();
//                }
                
    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void tb_productodetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_productodetalleKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_DELETE){
                    btnQuitar.doClick();
                }
    }//GEN-LAST:event_tb_productodetalleKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                   tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
            tbProductoAlmacen.requestFocus();
                }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarProductoCabeceraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoCabeceraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoCabeceraActionPerformed

    private void txtBuscarProductoCabeceraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoCabeceraKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    tbProductoReferencial.getSelectionModel().setSelectionInterval(0, 0);
                    tbProductoReferencial.requestFocus();
                }
    }//GEN-LAST:event_txtBuscarProductoCabeceraKeyPressed

    private void txtBuscarProductoCabeceraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoCabeceraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoCabeceraKeyTyped

    private void btnBuscarProductoCabeceraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoCabeceraActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbProductoReferencial.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Producto Referencial"};
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[4];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarProductoCabecera.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                pc.addRow(fila);
                c++;
            }
            tbProductoReferencial.setModel(pc);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(pc);
            tbProductoReferencial.setRowSorter(elQueOrdena);
            tbProductoReferencial.setModel(pc);
            formatoProductoCabecera();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        
    }//GEN-LAST:event_btnBuscarProductoCabeceraActionPerformed

    private void tbProductoReferencialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoReferencialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoReferencialMouseClicked

    private void tbProductoReferencialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoReferencialKeyPressed
char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     PRODUCTO_REFERENCIAL.setVisible(false);
                    mostrarCabecerayDetalle();
                    txtProducto.setEnabled(false);
                    btnguardar.setEnabled(false);
                         btnmodificar.setEnabled(true);
                         btneliminar.setEnabled(true);
                         tb_productodetalle.setEnabled(false);
                         tb_productodetalle.setBackground(Color.lightGray);
                         btnAgregar.setEnabled(false);
                         btnQuitar.setEnabled(false);
                }       
        
    }//GEN-LAST:event_tbProductoReferencialKeyPressed

    private void tbProductoReferencialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoReferencialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoReferencialKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        mostrarDetalle();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
PRODUCTOS.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
       buscar_producto_almacen();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarProductoCabeceraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarProductoCabeceraCaretUpdate
       ProductoReferencial_buscar();
    }//GEN-LAST:event_txtBuscarProductoCabeceraCaretUpdate
    public void ProductoReferencial_buscar(){
        String consulta="";
        try {
            tbProductoReferencial.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Producto Referencial"};
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[4];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarProductoCabecera.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                pc.addRow(fila);
                c++;
            }
            tbProductoReferencial.setModel(pc);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(pc);
            tbProductoReferencial.setRowSorter(elQueOrdena);
            tbProductoReferencial.setModel(pc);
            
            formatoProductoCabecera();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void enableDatos(){                    
                       txtProducto.setEnabled(true);
}
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
    public void limpiar()
{
    referencialCabecera v=new referencialCabecera();
        txtcodigo.setText(v.codreferencialCabecera());
        if(txtcodigo.getText().equalsIgnoreCase("")){
        txtcodigo.setText("RC000000000000000001");
    }
        txtGuarModif.setText("G");
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
    txtProducto.setText("");
    tb_productodetalle.setModel(new DefaultTableModel());
    inicializar_tabla();
   tb_productodetalle.setEnabled(true);
        tb_productodetalle.setBackground(Color.white);
}

     public void mostrarDetalle(){
        
        try {
String Codigo,Nombre_del_Producto,Clase_producto,Descripcion_de_la_clase_producto,Marca,
                Unidad_medida,Presentacion,Fabricante;
            int filaselec=tbProductoAlmacen.getSelectedRow();
            
            m1=(DefaultTableModel) tbProductoAlmacen.getModel();
            Codigo=tbProductoAlmacen.getValueAt(filaselec, 1).toString();
            Nombre_del_Producto=tbProductoAlmacen.getValueAt(filaselec, 2).toString();
            Clase_producto=tbProductoAlmacen.getValueAt(filaselec, 3).toString();
            Descripcion_de_la_clase_producto=tbProductoAlmacen.getValueAt(filaselec, 4).toString();
            Marca=tbProductoAlmacen.getValueAt(filaselec, 5).toString();
            Unidad_medida=tbProductoAlmacen.getValueAt(filaselec, 6).toString();
            Presentacion=tbProductoAlmacen.getValueAt(filaselec, 7).toString();
            Fabricante=tbProductoAlmacen.getValueAt(filaselec, 8).toString();
           
          if(tb_productodetalle.getRowCount()==0){
              m1=(DefaultTableModel) tb_productodetalle.getModel();
           String filaelemento[]={Codigo,Nombre_del_Producto,Clase_producto,Descripcion_de_la_clase_producto,
           Marca,Unidad_medida,Presentacion,Fabricante};
               m1.addRow(filaelemento);
               
          }
          else{
           if(repiteDetalle()==true){
               JOptionPane.showMessageDialog(PRODUCTOS,"El Producto ya ha sido ingresado.");   
          } else if(repiteUM()==true){
               JOptionPane.showMessageDialog(PRODUCTOS,"Los Productos ingresados deben tener igual Unidad de Medida.");   
          }
           else{
                m1=(DefaultTableModel) tb_productodetalle.getModel();
           String filaelemento[]={Codigo,Nombre_del_Producto,Clase_producto,Descripcion_de_la_clase_producto,
           Marca,Unidad_medida,Presentacion,Fabricante};
               m1.addRow(filaelemento);   
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
     public boolean repiteDetalle(){
         int filaselec=tbProductoAlmacen.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tb_productodetalle.getRowCount(); i++){    
               if(tb_productodetalle.getValueAt(i, 0).toString().equalsIgnoreCase(tbProductoAlmacen.getValueAt(filaselec, 1).toString())){
                    c=true;
			}}
               return c;
     }
     public boolean repiteUM(){
         int filaselec=tbProductoAlmacen.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tb_productodetalle.getRowCount(); i++){    
               if(tb_productodetalle.getValueAt(i, 5).toString().equalsIgnoreCase(tbProductoAlmacen.getValueAt(filaselec, 6).toString())==false){
                    c=true;
			}}
               return c;
     }
     
     public void guardarDetalle(){
         for (int i = 0; i < tb_productodetalle.getRowCount(); i++){      
               referencialDetalle rd=new referencialDetalle();
               rd.setCod_prod_refe(txtcodigo.getText());
               rd.setCod_produc(tb_productodetalle.getValueAt(i, 0).toString());
                rd.guardarReferencialDetalle();
			}}
     
     public void mostrarCabecerayDetalle(){
        
        try {
            
            int filaselec=tbProductoReferencial.getSelectedRow();

            txtcodigo.setText(tbProductoReferencial.getValueAt(filaselec, 1).toString());
            txtProducto.setText(tbProductoReferencial.getValueAt(filaselec, 2).toString());
            
            //m1=(DefaultTableModel) tbProductoReferencial.getModel();
          
          // m1=(DefaultTableModel) tb_productodetalle.getModel();
         //  String filaelemento[]={Codigo,Nombre_del_Producto};
          // m1.addRow(filaelemento);
          String consulta="";
            tb_productodetalle.setModel(new DefaultTableModel());
            String titulos[]={"Codigo","Nombre del Producto","Clase producto","Descripcion de la clase producto", "Marca",
                "Unidad medida","Presentacion","Fabricante"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[15];

            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_DETALLE_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tbProductoReferencial.getValueAt(filaselec, 1).toString());
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
                m1.addRow(fila);
                c++;
            }
            tb_productodetalle.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            tb_productodetalle.setRowSorter(elQueOrdena);
            this.tb_productodetalle.setModel(m1);
            formatoInicializarTabla();
            
            //inicializar_tabla();
        
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(COSTOS_PRODUCTO_REFERENCIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(COSTOS_PRODUCTO_REFERENCIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(COSTOS_PRODUCTO_REFERENCIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(COSTOS_PRODUCTO_REFERENCIAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new COSTOS_PRODUCTO_REFERENCIAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog PRODUCTOS;
    public static javax.swing.JDialog PRODUCTO_REFERENCIAL;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProductoCabecera;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitar;
    public static javax.swing.JButton btneliminar;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnmodificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tbProductoAlmacen;
    public static javax.swing.JTable tbProductoReferencial;
    public static javax.swing.JTable tb_productodetalle;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarProductoCabecera;
    private javax.swing.JTextField txtGuarModif;
    public static javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables


}
