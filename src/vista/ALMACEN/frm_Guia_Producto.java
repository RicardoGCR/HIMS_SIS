/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ALMACEN;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.ALMACEN.Guia_Producto;
import modelos.ALMACEN.Producto_Insumos;
import modelos.FormatoTablaSIGA;
import modelos.LABORATORIO.LAB_Contenedor_Muestra;
import modelos.Usuario;
import servicios.Conexion;
import static vista.COSTOS.COSTOS_PRODUCTO_REFERENCIAL.tbProductoAlmacen;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_Guia_Producto extends javax.swing.JFrame {
DefaultTableModel m2;
DefaultTableModel m3;
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion c=new Conexion();
    /**
     * Creates new form frm_Asig_Unidad_Medida
     */
    public frm_Guia_Producto() {
        initComponents();
         this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        cargarProducto();
        formatoProducto();
        upss.getContentPane().setBackground(Color.white); 
        upss.setLocationRelativeTo(null);     
        
        //para no intercambiar columnas
        tbProductoAlmacen.getTableHeader().setReorderingAllowed(false);
        tbUpss.getTableHeader().setReorderingAllowed(false);
    }

    
     public void cargarProducto(){
    try {
             String titulos[]={"Nº","Código","Nombre del Producto","Cantidad de Unidad de Medida","Unidad de Medida"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            String consulta="exec ASIGNAR_UNIDAD_MEDIDA_LISTAR ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"1");
            cmd.setString(2,"");
            ResultSet r= cmd.executeQuery();
            int c=1;
            
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
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
    tbProductoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(35);
    tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(125);
    tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(360);
    tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(140);
    tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(127);
    
    tbProductoAlmacen.getColumnModel().getColumn(3).setMinWidth(0);
    tbProductoAlmacen.getColumnModel().getColumn(3).setMaxWidth(0);
    tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
    tbProductoAlmacen.requestFocus();
}
    public void cargar_upss(){
         String consulta="";
        try {
            tbUpss.setModel(new DefaultTableModel());

            String titulos[]={"Nº","Código","UPSS"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[4];
            
            Usuario obj=new Usuario();
            consulta="exec sp_UPSS ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"");
            cmd.setString(2,"1");
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                m3.addRow(fila);
                c++;
            }
            tbUpss.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbUpss.setRowSorter(elQueOrdena);
            this.tbUpss.setModel(m3);
            formatoProducto();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
    
    public void formato_upss(){
     tbUpss.getColumnModel().getColumn(0).setPreferredWidth(35);
    tbUpss.getColumnModel().getColumn(1).setPreferredWidth(50);   
    tbUpss.getColumnModel().getColumn(2).setPreferredWidth(140);   
    tbUpss.getSelectionModel().setSelectionInterval(0, 0);
    tbUpss.requestFocus();
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upss = new javax.swing.JDialog();
        jpanel1 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        txtBuscarUpss = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUpss = new javax.swing.JTable();
        jpanel = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        lblCodProducto = new javax.swing.JLabel();
        lblCodUpss = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductoAlmacen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                if(colIndex==3){
                    return true;
                }else{
                    return false; //Disallow the editing of any cell
                }
            }};
            jLabel3 = new javax.swing.JLabel();
            txtUpss = new javax.swing.JTextField();
            jPanel2 = new javax.swing.JPanel();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbGuia = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==3){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }
                }};

                upss.setMinimumSize(new java.awt.Dimension(369, 466));

                jpanel1.setBackground(new java.awt.Color(126, 5, 46));

                titulo6.setBackground(new java.awt.Color(0, 102, 102));
                titulo6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo6.setForeground(new java.awt.Color(255, 255, 255));
                titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo6.setText("UPSS");
                titulo6.setToolTipText("");
                titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                jpanel1.setLayout(jpanel1Layout);
                jpanel1Layout.setHorizontalGroup(
                    jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jpanel1Layout.setVerticalGroup(
                    jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo6)
                );

                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                jButton1.setContentAreaFilled(false);
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                tbUpss.setModel(new javax.swing.table.DefaultTableModel(
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
                tbUpss.setRowHeight(22);
                tbUpss.setSelectionBackground(new java.awt.Color(153, 151, 153));
                tbUpss.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbUpssKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(tbUpss);

                javax.swing.GroupLayout upssLayout = new javax.swing.GroupLayout(upss.getContentPane());
                upss.getContentPane().setLayout(upssLayout);
                upssLayout.setHorizontalGroup(
                    upssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(upssLayout.createSequentialGroup()
                        .addGroup(upssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upssLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(txtBuscarUpss, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upssLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(15, Short.MAX_VALUE))
                );
                upssLayout.setVerticalGroup(
                    upssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upssLayout.createSequentialGroup()
                        .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(upssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarUpss, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                        .addContainerGap())
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(732, 566));
                setPreferredSize(new java.awt.Dimension(732, 566));
                getContentPane().setLayout(null);

                jpanel.setBackground(new java.awt.Color(126, 5, 46));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Guía de Productos");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
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

                txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscar.setSelectionColor(new java.awt.Color(126, 5, 46));
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

                btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
                btnBuscarProducto.setBorder(null);
                btnBuscarProducto.setContentAreaFilled(false);
                btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarProductoActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(titulo5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCodUpss, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(lblCodProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProducto)))
                        .addGap(35, 35, 35))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpanelLayout.createSequentialGroup()
                                        .addComponent(lblCodProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtBuscar)
                                    .addComponent(lblCodUpss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())))
                );

                getContentPane().add(jpanel);
                jpanel.setBounds(0, 0, 732, 94);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                jPanel3.setLayout(null);

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                jPanel6.setLayout(null);
                jPanel3.add(jPanel6);
                jPanel6.setBounds(0, 440, 880, 30);

                getContentPane().add(jPanel3);
                jPanel3.setBounds(0, 530, 730, 40);

                tab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtCantidad.setForeground(new java.awt.Color(0, 51, 51));
                txtCantidad.setSelectionColor(new java.awt.Color(126, 5, 46));
                txtCantidad.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCantidadActionPerformed(evt);
                    }
                });
                txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtCantidadKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtCantidadKeyTyped(evt);
                    }
                });

                jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("Cantidad");

                txtProducto.setEditable(false);
                txtProducto.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtProducto.setForeground(new java.awt.Color(0, 51, 51));
                txtProducto.setSelectionColor(new java.awt.Color(126, 5, 46));
                txtProducto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtProductoActionPerformed(evt);
                    }
                });
                txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtProductoKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtProductoKeyTyped(evt);
                    }
                });

                jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Producto");

                tbProductoAlmacen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                tbProductoAlmacen.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbProductoAlmacen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbProductoAlmacen.setRowHeight(25);
                tbProductoAlmacen.setSelectionBackground(new java.awt.Color(148, 146, 148));
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
                if (tbProductoAlmacen.getColumnModel().getColumnCount() > 0) {
                    tbProductoAlmacen.getColumnModel().getColumn(0).setResizable(false);
                    tbProductoAlmacen.getColumnModel().getColumn(1).setResizable(false);
                    tbProductoAlmacen.getColumnModel().getColumn(2).setResizable(false);
                    tbProductoAlmacen.getColumnModel().getColumn(3).setResizable(false);
                }

                jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText("UPSS");

                txtUpss.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtUpss.setForeground(new java.awt.Color(0, 51, 51));
                txtUpss.setSelectionColor(new java.awt.Color(126, 5, 46));
                txtUpss.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtUpssActionPerformed(evt);
                    }
                });
                txtUpss.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtUpssKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtUpssKeyTyped(evt);
                    }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUpss)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(19, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUpss, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                tab.addTab("tab1", jPanel1);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                tbGuia.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                tbGuia.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, true, true, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbGuia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbGuia.setRowHeight(25);
                tbGuia.setSelectionBackground(new java.awt.Color(126, 5, 46));
                tbGuia.setSelectionForeground(new java.awt.Color(0, 0, 0));
                tbGuia.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tbGuiaMouseClicked(evt);
                    }
                });
                tbGuia.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbGuiaKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        tbGuiaKeyTyped(evt);
                    }
                });
                jScrollPane3.setViewportView(tbGuia);
                if (tbGuia.getColumnModel().getColumnCount() > 0) {
                    tbGuia.getColumnModel().getColumn(0).setResizable(false);
                    tbGuia.getColumnModel().getColumn(1).setResizable(false);
                    tbGuia.getColumnModel().getColumn(2).setResizable(false);
                    tbGuia.getColumnModel().getColumn(3).setResizable(false);
                }

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(29, Short.MAX_VALUE))
                );

                tab.addTab("tab2", jPanel2);

                getContentPane().add(tab);
                tab.setBounds(10, 105, 710, 460);

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarProducto.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbProductoAlmacen.setModel(new DefaultTableModel());

            String titulos[]={"Nº","Código","Nombre del Producto","Cantidad de Unidad de Medida","Unidad de Medida"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            consulta="exec ASIGNAR_UNIDAD_MEDIDA_LISTAR ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"2");
            cmd.setString(2,txtBuscar.getText());
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
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
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void tbProductoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoAlmacenKeyTyped

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
   char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        int filaselec=tbProductoAlmacen.getSelectedRow();
                        
                        lblCodProducto.setText(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                        txtProducto.setText(tbProductoAlmacen.getValueAt(filaselec, 2).toString());
                     
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void tbProductoAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoAlmacenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbProductoAlmacenMouseClicked

    private void txtProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoActionPerformed

    private void txtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoKeyPressed

    private void txtProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoKeyTyped

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
            char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
tab.setSelectedIndex(0);
        
        limpiar();

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        
        
        try{
            
                if(txtProducto.getText().equalsIgnoreCase("")||lblCodProducto.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Producto");
                }  else if(lblCodUpss.getText().equalsIgnoreCase("")||txtUpss.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una UPSS");
                }
                else if(txtCantidad.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Ingrese una Cantidad");
                }  
                else{
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                        Guia_Producto g=new  Guia_Producto();
                        g.setCod_produc(lblCodProducto.getText());
                        g.setCantidad(Integer.parseInt(txtCantidad.getText()));
                        g.setNom_usu(lblUsu.getText());
                        g.setUpss(lblCodUpss.getText());

                        if(g.GUIA_PRODUCTO_guardar()){
                            JOptionPane.showMessageDialog(null, "Datos Guardados");
                            
                            limpiar();
                            
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
                        }}
                    }}catch(Exception e) {
                                JOptionPane.showMessageDialog(this, "Ingrese todos los campos");

                            }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

tab.setSelectedIndex(1);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tbGuiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGuiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbGuiaMouseClicked

    private void tbGuiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGuiaKeyPressed
      tab.setSelectedIndex(1);
    }//GEN-LAST:event_tbGuiaKeyPressed

    private void tbGuiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGuiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbGuiaKeyTyped

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
    /*  String consulta="";
        try {
            tbProductoAlmacen.setModel(new DefaultTableModel());

            String titulos[]={"Nº","Código","Nombre del Producto","Cantidad de Unidad de Medida","Unidad de Medida"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            consulta="exec ASIGNAR_UNIDAD_MEDIDA_LISTAR ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"2");
            cmd.setString(2,txtBuscar.getText());
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
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
        }*/
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtUpssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUpssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUpssActionPerformed

    private void txtUpssKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUpssKeyPressed
        upss.setVisible(true);
        txtBuscarUpss.setText("");
        cargar_upss();
        formato_upss();
    }//GEN-LAST:event_txtUpssKeyPressed

    private void txtUpssKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUpssKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUpssKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String consulta="";
        try {
            tbUpss.setModel(new DefaultTableModel());

            String titulos[]={"Nº","Código","UPSS"};
            m3=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m3);
            String fila[]=new String[4];
            
            Usuario obj=new Usuario();
            consulta="exec sp_UPSS ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,txtBuscarUpss.getText());
            cmd.setString(2,"2");
            
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                m3.addRow(fila);
                c++;
            }
            tbUpss.setModel(m3);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m3);
            tbUpss.setRowSorter(elQueOrdena);
            this.tbUpss.setModel(m3);
            formato_upss();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tbUpssKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUpssKeyPressed
   char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                         if(tbUpss.getRowCount()>0){
                        int filaselec=tbUpss.getSelectedRow();
                        
                        lblCodUpss.setText(tbUpss.getValueAt(filaselec, 1).toString());
                        txtUpss.setText(tbUpss.getValueAt(filaselec, 2).toString());
                        upss.setVisible(false);
                         }
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
    }//GEN-LAST:event_tbUpssKeyPressed
 public void enableDatos(boolean op){
    btnNuevo.setVisible(op);
    txtProducto.setVisible(op);
    txtCantidad.setVisible(op);
}
    public void limpiar(){
    lblCodProducto.setText("");
    txtProducto.setText("");
    txtCantidad.setText("");
    lblCodUpss.setText("");
    txtUpss.setText("");
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
            java.util.logging.Logger.getLogger(frm_Guia_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Guia_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Guia_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Guia_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Guia_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btnguardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JLabel lblCodProducto;
    private javax.swing.JLabel lblCodUpss;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JTabbedPane tab;
    public static javax.swing.JTable tbGuia;
    public static javax.swing.JTable tbProductoAlmacen;
    private javax.swing.JTable tbUpss;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarUpss;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtUpss;
    private javax.swing.JDialog upss;
    // End of variables declaration//GEN-END:variables
}
