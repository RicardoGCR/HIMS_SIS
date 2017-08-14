/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;


import java.awt.Color;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.COSTOS.CTipoSustentacion;
import modelos.COSTOS.referencialCabecera;
import servicios.Conexion;
import static vista.COSTOS.Costos_Sustentacion.lblGananciaPer;
import static vista.COSTOS.Costos_Sustentacion.txtGananciaPerdida;
import static vista.COSTOS.Costos_Sustentacion_Detalle_Herramientas.txtTipo;
import static vista.Principal.fechaActual;

/**
 *
 * @author USUARIO
 */
public class Costos_Sustentacion_Detalle_Insumo extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion conectar=new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    DefaultTableModel pc,m1,mresumen;
    
    /**
     * Creates new form Costos_Sustentacion_Detalle
     */
    public Costos_Sustentacion_Detalle_Insumo() {
        initComponents();
        con=conectar.conectar();
        setLocationRelativeTo(null);
        BuscarTipoSustentacion.setLocationRelativeTo(null);
        PRODUCTO_REFERENCIAL.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        BuscarTipoSustentacion.getContentPane().setBackground(Color.WHITE);
        PRODUCTO_REFERENCIAL.getContentPane().setBackground(Color.WHITE);
        //cargarCostosSustentacion();
//        cargarTS();
        h1 = new Thread(this);
        h1.start();
        //Mostrar fecha
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());  
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        cargarProductoCabecera();
        formatoProductoCabecera();
        
        CTipoSustentacion ts=new CTipoSustentacion();
        txtCodTipoSust.setText(ts.obtenerCodTipo(txtTipo.getText()));
        txtCodTipoSust.setVisible(false);
        txtCodProducto.setVisible(false);
        
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
       
       
       public void cargarTS(){ 
       DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Código");
       tabla.addColumn("Tipo Sustentación");
       tabla.addColumn("Usuario");  
       
       cst=con.prepareCall("{call COSTOS_COSTOS_TIPO_SUSTENTACION_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[3];
       for (int i=0; i<3; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tableCostosSustentacionDet.setModel(tabla);
       //Dar formato a la tabla
       tableCostosSustentacionDet.getColumnModel().getColumn(0).setPreferredWidth(180);
       tableCostosSustentacionDet.getColumnModel().getColumn(1).setPreferredWidth(200);
       tableCostosSustentacionDet.getColumnModel().getColumn(2).setPreferredWidth(180);       
       }catch (Exception e){}
 
       }
    
    public void cargarProductoCabecera(){
        try {
             String titulos[]={"Nº","Codigo","Nombre del Producto Referencial","Precio","Cantidad de Medida","Unidad de Medida"};
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[6];

            String consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_INSUMO_listar";
            ResultSet r;
            r=conectar.Listar(consulta);
            int c=1;
            while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
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
    tbProductoReferencial.getColumnModel().getColumn(0).setPreferredWidth(35);
    tbProductoReferencial.getColumnModel().getColumn(2).setPreferredWidth(200);
    tbProductoReferencial.getColumnModel().getColumn(3).setPreferredWidth(80);
    tbProductoReferencial.getColumnModel().getColumn(4).setPreferredWidth(150);
    tbProductoReferencial.getColumnModel().getColumn(5).setPreferredWidth(150);
    
    tbProductoReferencial.getColumnModel().getColumn(1).setMinWidth(0);
    tbProductoReferencial.getColumnModel().getColumn(1).setMaxWidth(0);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarTipoSustentacion = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCostosSustentacionDet = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTipoSusten = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        PRODUCTO_REFERENCIAL = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductoReferencial = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jpanel3 = new javax.swing.JPanel();
            titulo8 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            txtBuscarProductoCabecera = new javax.swing.JTextField();
            btnBuscarProductoCabecera1 = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            lblhPersonal = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jPanel2 = new javax.swing.JPanel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            txtTipo = new javax.swing.JTextField();
            txtProducto = new javax.swing.JTextField();
            jButton2 = new javax.swing.JButton();
            jPanel3 = new javax.swing.JPanel();
            chConsumible = new javax.swing.JCheckBox();
            jLabel9 = new javax.swing.JLabel();
            txtPrecio = new javax.swing.JTextField();
            jLabel11 = new javax.swing.JLabel();
            txtCantidadAUsar = new javax.swing.JTextField();
            jLabel10 = new javax.swing.JLabel();
            txtCantidad = new javax.swing.JTextField();
            jLabel12 = new javax.swing.JLabel();
            txtCantidadMedida = new javax.swing.JTextField();
            txtUM = new javax.swing.JTextField();
            jLabel13 = new javax.swing.JLabel();
            txtTotal = new javax.swing.JTextField();
            jLabel14 = new javax.swing.JLabel();
            jButton5 = new javax.swing.JButton();
            jButton6 = new javax.swing.JButton();
            txtCodProducto = new javax.swing.JTextField();
            txtCodTipoSust = new javax.swing.JTextField();
            jPanel4 = new javax.swing.JPanel();
            jLabel17 = new javax.swing.JLabel();
            jLabel18 = new javax.swing.JLabel();
            jLabel19 = new javax.swing.JLabel();

            BuscarTipoSustentacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            BuscarTipoSustentacion.setAlwaysOnTop(true);
            BuscarTipoSustentacion.setMinimumSize(new java.awt.Dimension(620, 550));
            BuscarTipoSustentacion.setResizable(false);

            tableCostosSustentacionDet = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false; //Disallow the editing of any cell
                }
            };
            tableCostosSustentacionDet.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null}
                },
                new String [] {
                    "Codigo", "Tipo Sustentacion", "Usuario"
                }
            ));
            tableCostosSustentacionDet.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tableCostosSustentacionDet.setRowHeight(25);
            tableCostosSustentacionDet.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tableCostosSustentacionDetKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(tableCostosSustentacionDet);

            jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
            jLabel15.setText("Tipo Sustentacion");

            jLabel16.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel16.setText("Nombre:");

            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout BuscarTipoSustentacionLayout = new javax.swing.GroupLayout(BuscarTipoSustentacion.getContentPane());
            BuscarTipoSustentacion.getContentPane().setLayout(BuscarTipoSustentacionLayout);
            BuscarTipoSustentacionLayout.setHorizontalGroup(
                BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                    .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                            .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                                    .addGap(138, 138, 138)
                                    .addComponent(jLabel16)
                                    .addGap(28, 28, 28)
                                    .addComponent(txtTipoSusten, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BuscarTipoSustentacionLayout.createSequentialGroup()
                                    .addGap(174, 174, 174)
                                    .addComponent(jLabel15)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            BuscarTipoSustentacionLayout.setVerticalGroup(
                BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarTipoSustentacionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel15)
                    .addGap(26, 26, 26)
                    .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtTipoSusten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            PRODUCTO_REFERENCIAL.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            PRODUCTO_REFERENCIAL.setAlwaysOnTop(true);
            PRODUCTO_REFERENCIAL.setAutoRequestFocus(false);
            PRODUCTO_REFERENCIAL.setMinimumSize(new java.awt.Dimension(558, 570));

            jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tbProductoReferencial.setModel(new javax.swing.table.DefaultTableModel(
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
            tbProductoReferencial.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tbProductoReferencial.setRowHeight(25);
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

            jpanel3.setBackground(new java.awt.Color(102, 102, 102));

            titulo8.setBackground(new java.awt.Color(0, 102, 102));
            titulo8.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            titulo8.setForeground(new java.awt.Color(255, 255, 255));
            titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            titulo8.setText("Producto Referencial");
            titulo8.setToolTipText("");
            titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
            jLabel20.setForeground(new java.awt.Color(255, 255, 255));
            jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel20.setText("Búsqueda por Nombre del Producto");

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

            btnBuscarProductoCabecera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
            btnBuscarProductoCabecera1.setBorder(null);
            btnBuscarProductoCabecera1.setContentAreaFilled(false);
            btnBuscarProductoCabecera1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarProductoCabecera1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarProductoCabecera1ActionPerformed(evt);
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
                            .addComponent(btnBuscarProductoCabecera1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(titulo8)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(199, Short.MAX_VALUE))
            );
            jpanel3Layout.setVerticalGroup(
                jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanel3Layout.createSequentialGroup()
                    .addComponent(titulo8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBuscarProductoCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarProductoCabecera1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addComponent(jLabel20)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout PRODUCTO_REFERENCIALLayout = new javax.swing.GroupLayout(PRODUCTO_REFERENCIAL.getContentPane());
            PRODUCTO_REFERENCIAL.getContentPane().setLayout(PRODUCTO_REFERENCIALLayout);
            PRODUCTO_REFERENCIALLayout.setHorizontalGroup(
                PRODUCTO_REFERENCIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
            );
            PRODUCTO_REFERENCIALLayout.setVerticalGroup(
                PRODUCTO_REFERENCIALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PRODUCTO_REFERENCIALLayout.createSequentialGroup()
                    .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setResizable(false);
            addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    formKeyPressed(evt);
                }
            });

            jPanel1.setBackground(new java.awt.Color(102, 102, 102));

            jLabel1.setBackground(new java.awt.Color(255, 255, 255));
            jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 28)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("INSUMOS");

            jLabel2.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("FECHA:");

            lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            lblFecha.setForeground(new java.awt.Color(255, 255, 255));
            lblFecha.setText("jLabel4");

            jLabel3.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("HORA:");

            lblhPersonal.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            lblhPersonal.setForeground(new java.awt.Color(255, 255, 255));
            lblhPersonal.setText("jLabel4");

            jLabel6.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(255, 255, 255));
            jLabel6.setText("USUARIO");

            lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu.setText("Usuario");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblhPersonal)
                        .addComponent(lblFecha))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(219, 219, 219)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lblFecha))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(lblhPersonal)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
            );

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));
            jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

            jLabel4.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel4.setText("Tipo Sustentacion:");

            jLabel5.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel5.setText("Producto Referencia:");

            txtTipo.setEditable(false);
            txtTipo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            txtTipo.setText("INSUMOS");
            txtTipo.setEnabled(false);

            txtProducto.setEditable(false);

            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addGap(23, 23, 23)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addComponent(txtProducto, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGap(18, 18, 18)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(126, 126, 126))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addContainerGap(23, Short.MAX_VALUE))
            );

            jPanel3.setBackground(new java.awt.Color(255, 255, 255));
            jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

            chConsumible.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            chConsumible.setText("Consumible");

            jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel9.setText("Cantidad:");

            txtPrecio.setEditable(false);

            jLabel11.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel11.setText("Cantidad a Usar:");

            txtCantidadAUsar.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtCantidadAUsarKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtCantidadAUsarKeyTyped(evt);
                }
            });

            jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel10.setText("Precio:");

            txtCantidad.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCantidadActionPerformed(evt);
                }
            });
            txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtCantidadKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtCantidadKeyTyped(evt);
                }
            });

            jLabel12.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel12.setText("Rendimiento:");

            txtCantidadMedida.setEditable(false);

            txtUM.setEditable(false);

            jLabel13.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel13.setText("Total Sustentacion: ");

            txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            txtTotal.setEnabled(false);

            jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jLabel14.setText("Unidad de Medida:");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12)
                        .addComponent(chConsumible)
                        .addComponent(jLabel14))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtUM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCantidadMedida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 18, Short.MAX_VALUE)))
                    .addGap(63, 63, 63)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(40, 40, 40)
                                .addComponent(txtCantidadAUsar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(32, 32, 32))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(chConsumible)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGap(21, 21, 21)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtCantidadAUsar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(txtUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(24, Short.MAX_VALUE))
            );

            jButton5.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog-ok-apply.png"))); // NOI18N
            jButton5.setMnemonic('A');
            jButton5.setText("ACEPTAR");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });

            jButton6.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
            jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog-close.png"))); // NOI18N
            jButton6.setMnemonic('C');
            jButton6.setText("CANCELAR");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });

            jPanel4.setBackground(new java.awt.Color(204, 204, 204));

            jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
            jLabel17.setText("Salir(Esc)");

            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
            jLabel18.setText("Aceptar(Alt+A)");

            jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/cancelar16x16.png"))); // NOI18N
            jLabel19.setText("Cancelar(Alt+C)");

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(431, 431, 431)
                    .addComponent(jLabel18)
                    .addGap(21, 21, 21)
                    .addComponent(jLabel19)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(5, 5, 5))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(206, 206, 206)
                            .addComponent(jButton5)
                            .addGap(134, 134, 134)
                            .addComponent(jButton6))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton6))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            getAccessibleContext().setAccessibleName("INSUMOS");

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void tableCostosSustentacionDetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCostosSustentacionDetKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tableCostosSustentacionDet.getSelectedRow();
              /*TipoSustentacion TS = new TipoSustentacion();
                TS.setVisible(true);
                dispose();*/
            
             Costos_Sustentacion_Detalle_Insumo.txtTipo.setText(String.valueOf(tableCostosSustentacionDet.getValueAt(fila, 1)));     
             BuscarTipoSustentacion.setVisible(false);
        }  
        if(txtTipo.getText().equalsIgnoreCase("MEDICINA - MATERIAL - INSUMO")){
            chConsumible.setEnabled(true);
        }else {
            chConsumible.setEnabled(false);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_tableCostosSustentacionDetKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(txtProducto.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Seleccione un Producto");
        }
        else if(txtPrecio.getText().equalsIgnoreCase("")||txtCantidad.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese Cantidad y/o Precio");
        }
        else if(txtCantidadAUsar.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese Cantidad a Usar(U.M.)");
        }else{
                dispose();
                mostrarDetalleInsumo();
                cargarResumenInsumos();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PRODUCTO_REFERENCIAL.setVisible(true);
        tbProductoReferencial.getSelectionModel().setSelectionInterval(0, 0);
        tbProductoReferencial.requestFocus();
         cargarProductoCabecera();
        formatoProductoCabecera();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbProductoReferencialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoReferencialMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoReferencialMouseClicked

    private void tbProductoReferencialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoReferencialKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            int filaselec=tbProductoReferencial.getSelectedRow();
            PRODUCTO_REFERENCIAL.setVisible(false);
            txtCodProducto.setText(tbProductoReferencial.getValueAt(filaselec, 1).toString());
            txtProducto.setText(tbProductoReferencial.getValueAt(filaselec, 2).toString());
            txtPrecio.setText(tbProductoReferencial.getValueAt(filaselec, 3).toString());
            txtCantidadMedida.setText(tbProductoReferencial.getValueAt(filaselec, 4).toString());
            txtUM.setText(tbProductoReferencial.getValueAt(filaselec, 5).toString());
            if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidadAUsar.getText().equalsIgnoreCase("")){
                        txtTotal.setText("");
          }
                else if( Integer.parseInt(txtCantidadMedida.getText())< Integer.parseInt(txtCantidadAUsar.getText())){
                        txtCantidadAUsar.setText("");
                        txtTotal.setText("");
                        JOptionPane.showMessageDialog(rootPane, "La cantidad a usar tiene que ser menor al Rendimiento.");
                        
          }
                else if(!txtPrecio.getText().equalsIgnoreCase("") && !txtCantidad.getText().equalsIgnoreCase("")&& !txtCantidadAUsar.getText().equalsIgnoreCase("")){
                        double t=Double.parseDouble(txtCantidad.getText())*(Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidadAUsar.getText())/Double.parseDouble(txtCantidadMedida.getText()));
                        BigDecimal total = new BigDecimal(t);
                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
                        txtTotal.setText(String.valueOf(total));
                        }
        }

    }//GEN-LAST:event_tbProductoReferencialKeyPressed

    private void tbProductoReferencialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoReferencialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoReferencialKeyTyped

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
        if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidad.getText().equalsIgnoreCase("")|| txtCantidadAUsar.getText().equalsIgnoreCase("")){
                        txtTotal.setText("");
          }
                        else{
                        double t=Double.parseDouble(txtCantidad.getText())*(Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidadAUsar.getText())/Double.parseDouble(txtCantidadMedida.getText()));
                        BigDecimal total = new BigDecimal(t);
                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
                        txtTotal.setText(String.valueOf(total));
                        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadAUsarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadAUsarKeyReleased
        // TODO add your handling code here:
                if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidadAUsar.getText().equalsIgnoreCase("")){
                        txtTotal.setText("");
          }
                else if( Integer.parseInt(txtCantidadMedida.getText())< Integer.parseInt(txtCantidadAUsar.getText())){
                        JOptionPane.showMessageDialog(rootPane, "La cantidad a usar tiene que ser menor al Rendimiento.");
                        txtCantidadAUsar.setText("");
                        txtTotal.setText("");
          }
                      if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidad.getText().equalsIgnoreCase("")|| txtCantidadAUsar.getText().equalsIgnoreCase("")){
                        txtTotal.setText("");
          }
                        else{
                        double t=Double.parseDouble(txtCantidad.getText())*((Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidadAUsar.getText()))/Double.parseDouble(txtCantidadMedida.getText()));
                        BigDecimal total = new BigDecimal(t);
                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
                        txtTotal.setText(String.valueOf(total));
                        }
//                else if(!txtPrecio.getText().equalsIgnoreCase("") && !txtCantidad.getText().equalsIgnoreCase("")&& !txtCantidadAUsar.getText().equalsIgnoreCase("")){
//                        double t=Double.parseDouble(txtCantidad.getText())*(Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidadAUsar.getText())/Double.parseDouble(txtCantidadMedida.getText()));
//                        BigDecimal total = new BigDecimal(t);
//                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
//                        txtTotal.setText(String.valueOf(total));
//                        }
    }//GEN-LAST:event_txtCantidadAUsarKeyReleased

    private void txtCantidadAUsarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadAUsarKeyTyped
        // TODO add your handling code here:
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtCantidadAUsar.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtCantidadAUsarKeyTyped

    private void txtBuscarProductoCabeceraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarProductoCabeceraCaretUpdate
        ProductoReferencial_buscar();
    }//GEN-LAST:event_txtBuscarProductoCabeceraCaretUpdate
    public void ProductoReferencial_buscar(){
        String consulta="";
        try {
            tbProductoReferencial.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Producto Referencial","Precio","Cantidad de Medida","Unidad de Medida"};  
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[7];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_INSUMO_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarProductoCabecera.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
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

    private void btnBuscarProductoCabecera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoCabecera1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbProductoReferencial.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Producto Referencial","Precio","Cantidad de Medida","Unidad de Medida"};  
            pc=new DefaultTableModel(null,titulos);
            JTable p=new JTable(pc);
            String fila[]=new String[7];
            referencialCabecera obj=new referencialCabecera();
            consulta="exec COSTOS_COSTOS_PRODUCTO_REFERENCIAL_CABECERA_INSUMO_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarProductoCabecera.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
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

    }//GEN-LAST:event_btnBuscarProductoCabecera1ActionPerformed

    public void mostrarDetalleInsumo(){
        
        try {
        String cod_tipo,cod_depre,consumible,tipo_Sust,nom_detalle,cantidad,rendimiento,UM,cantidadUM,precio,total;

            cod_tipo=txtCodTipoSust.getText();
            cod_depre=txtCodProducto.getText();
            if(chConsumible.isSelected()){
            consumible="S";
            }else{
              consumible="N";  
            }
            tipo_Sust=txtTipo.getText();
            nom_detalle=txtProducto.getText();
            cantidad=txtCantidad.getText();
            rendimiento=txtCantidadMedida.getText();
            UM=txtUM.getText();
            cantidadUM=txtCantidadAUsar.getText();
            precio=txtPrecio.getText();
            total=txtTotal.getText();
           
          if(Costos_Sustentacion.tbInsumo.getRowCount()==0){
            dispose();
              m1=(DefaultTableModel) Costos_Sustentacion.tbInsumo.getModel();
           String filaelemento[]={cod_tipo,cod_depre,consumible,tipo_Sust,nom_detalle,cantidad,rendimiento,UM,cantidadUM,precio,total};
               m1.addRow(filaelemento);
          }
          else{
           if(repiteDetalleInsumo()==true){
               JOptionPane.showMessageDialog(rootPane,"El Insumo ya ha sido ingresado.");   
          } 
           else{
              m1=(DefaultTableModel) Costos_Sustentacion.tbInsumo.getModel();
           String filaelemento[]={cod_tipo,cod_depre,consumible,tipo_Sust,nom_detalle,cantidad,rendimiento,UM,cantidadUM,precio,total};
               m1.addRow(filaelemento); 
               
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean repiteDetalleInsumo(){
         
         boolean c=false;
         for (int i = 0; i < Costos_Sustentacion.tbInsumo.getRowCount(); i++){    
               if(Costos_Sustentacion.tbInsumo.getValueAt(i, 1).toString().equalsIgnoreCase(txtCodProducto.getText())){
                    c=true;
			}}
               return c;
     }
    
    public void cargarResumenInsumos(){
        try{
        
        double total=0;
        if(Costos_Sustentacion.tbInsumo.getRowCount()>0){
            Costos_Sustentacion.tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            mresumen=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(mresumen);

                for (int i = 0; i < Costos_Sustentacion.tbInsumo.getRowCount(); i++){    
                    total=total+Double.parseDouble(Costos_Sustentacion.tbInsumo.getValueAt(i, 10).toString());
                }
                BigDecimal totali = new BigDecimal(total);
                       totali = totali.setScale(2, BigDecimal.ROUND_HALF_UP);
                String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=Costos_Sustentacion.txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=String.valueOf(totali);
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=Costos_Sustentacion.txtTotalSBasicos.getText();
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=Costos_Sustentacion.txtTotalHerramienta.getText();
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=Costos_Sustentacion.txtTotalInfraes.getText();
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=Costos_Sustentacion.txtTotalSAdminis.getText();
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=Costos_Sustentacion.txtTotalSGenerales.getText();
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=Costos_Sustentacion.txtTotalSIntermedios.getText();
                } 
                 mresumen.addRow(fila);
                }
                
                Costos_Sustentacion.tbResumenCostos.setModel( mresumen);
            TableRowSorter<TableModel> elQueOrdenai=new TableRowSorter<TableModel>( mresumen);
            Costos_Sustentacion.tbResumenCostos.setRowSorter(elQueOrdenai);
            Costos_Sustentacion.tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            Costos_Sustentacion.tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            Costos_Sustentacion.txtTotalInsumos.setText(String.valueOf(totali));
            //Total
            Double sg,he,sb,inf,sa,pe,si;
            pe=Double.parseDouble(Costos_Sustentacion.txtTotalPersonal.getText());
            he=Double.parseDouble(Costos_Sustentacion.txtTotalHerramienta.getText());
            sb=Double.parseDouble(Costos_Sustentacion.txtTotalSBasicos.getText());
            inf=Double.parseDouble(Costos_Sustentacion.txtTotalInfraes.getText());
            sa=Double.parseDouble(Costos_Sustentacion.txtTotalSAdminis.getText());
            sg=Double.parseDouble(Costos_Sustentacion.txtTotalSGenerales.getText());
            si=Double.parseDouble(Costos_Sustentacion.txtTotalSIntermedios.getText());
            Double precioTotal=total+he+sg+sb+inf+pe+sa+si;
            BigDecimal ptotal = new BigDecimal(precioTotal);
                        ptotal = ptotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            Costos_Sustentacion.txtPrecioTotal.setText(String.valueOf(ptotal));
            //Ganancia
            double gan=Double.parseDouble(Costos_Sustentacion.txtPrecio.getText())-precioTotal;
             BigDecimal gtotal = new BigDecimal(gan);
                       gtotal = gtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
            Costos_Sustentacion.txtGanancia.setText(String.valueOf(gtotal));
            if(gan>0){
                  lblGananciaPer.setForeground(Color.black);
                txtGananciaPerdida.setForeground(Color.black);
                       Costos_Sustentacion.lblGananciaPer.setText("Ganancia Total");
            Costos_Sustentacion.txtGananciaPerdida.setText(String.valueOf(gtotal));
            }else{
                double g=-gan;
                BigDecimal gt = new BigDecimal(g);
                       gt = gt.setScale(2, BigDecimal.ROUND_HALF_UP);
                         lblGananciaPer.setForeground(Color.red);
                txtGananciaPerdida.setForeground(Color.red);
                 Costos_Sustentacion.lblGananciaPer.setText("Pérdida Total");
            Costos_Sustentacion.txtGananciaPerdida.setText(String.valueOf(gt));
            }
                }}
        catch(Exception e){
            
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Insumo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Sustentacion_Detalle_Insumo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BuscarTipoSustentacion;
    public static javax.swing.JDialog PRODUCTO_REFERENCIAL;
    private javax.swing.JButton btnBuscarProductoCabecera1;
    private javax.swing.JCheckBox chConsumible;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanel3;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblhPersonal;
    private javax.swing.JTable tableCostosSustentacionDet;
    public static javax.swing.JTable tbProductoReferencial;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscarProductoCabecera;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadAUsar;
    private javax.swing.JTextField txtCantidadMedida;
    private javax.swing.JTextField txtCodProducto;
    public static javax.swing.JTextField txtCodTipoSust;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTipoSusten;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUM;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            lblhPersonal.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
          } 
    }
}
