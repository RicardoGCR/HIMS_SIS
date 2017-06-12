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
public class frm_Asig_Catalogo_Producto extends javax.swing.JFrame {
DefaultTableModel m2;
DefaultTableModel m3;
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion c=new Conexion();
    /**
     * Creates new form frm_Asig_Unidad_Medida
     */
    public frm_Asig_Catalogo_Producto() {
        initComponents();
         this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        cargarProducto();
        formatoProducto();
            tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
            tbProductoAlmacen.requestFocus();
        unidad_medida.setLocationRelativeTo(this);
        unidad_medida.getContentPane().setBackground(Color.WHITE);   
        insertar_um.setLocationRelativeTo(this);
        insertar_um.getContentPane().setBackground(Color.WHITE);   
        tbProductoAlmacen.setDefaultRenderer(Object.class,new FormatoTablaSIGA());
        
        //para no intercambiar columnas
        tbProductoAlmacen.getTableHeader().setReorderingAllowed(false);
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
    tbProductoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(50);
    tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(160);
    tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(360);
    tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(140);
    tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(140);
    tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
    tbProductoAlmacen.requestFocus();
}
    public void cargarUM(){
    try {
             String titulos[]={"Nº","Código","Unidad de Medida"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            String consulta="exec sp_unidad_medida_almacen ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,"");
            cmd.setString(2,"1");
            ResultSet r= cmd.executeQuery();
            int c=1;
            
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                m2.addRow(fila);
                c++;
            }
            tb_UM.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_UM.setRowSorter(elQueOrdena);
            this.tb_UM.setModel(m2);
    } catch (Exception e) {
    }
}
    public void formatoUM(){
    tb_UM.getColumnModel().getColumn(0).setPreferredWidth(35);
    tb_UM.getColumnModel().getColumn(1).setPreferredWidth(50);
    tb_UM.getColumnModel().getColumn(2).setPreferredWidth(160);
    
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        guardar = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        unidad_medida = new javax.swing.JDialog();
        jpanel1 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        lblCodProducto = new javax.swing.JLabel();
        txtBuscarUM = new javax.swing.JTextField();
        btnBuscarUM = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_UM = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){

                return false; //Disallow the editing of any cell

            }};
            jLabel2 = new javax.swing.JLabel();
            lblProducto = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            lblUm = new javax.swing.JLabel();
            jButton1 = new javax.swing.JButton();
            insertar_um = new javax.swing.JDialog();
            jpanel2 = new javax.swing.JPanel();
            titulo7 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            txtNuevoUM = new javax.swing.JTextField();
            jButton2 = new javax.swing.JButton();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbProductoAlmacen = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==3 ){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }
                }};
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();
                txtBuscar = new javax.swing.JTextField();
                btnBuscarProducto = new javax.swing.JButton();
                jTextField1 = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();

                jMenuItem1.setText("Guardar (Enter)");
                jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem1ActionPerformed(evt);
                    }
                });
                guardar.add(jMenuItem1);

                jMenuItem2.setText("Cambiar Unidad de Medida");
                jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem2ActionPerformed(evt);
                    }
                });
                guardar.add(jMenuItem2);

                unidad_medida.setMinimumSize(new java.awt.Dimension(406, 478));
                unidad_medida.setResizable(false);

                jpanel1.setBackground(new java.awt.Color(126, 5, 46));

                titulo6.setBackground(new java.awt.Color(0, 102, 102));
                titulo6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo6.setForeground(new java.awt.Color(255, 255, 255));
                titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo6.setText("Unidad de Medida");
                titulo6.setToolTipText("");
                titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                lblCodProducto.setForeground(new java.awt.Color(126, 5, 46));
                lblCodProducto.setText("jLabel2");

                txtBuscarUM.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtBuscarUM.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscarUM.setSelectionColor(new java.awt.Color(126, 5, 46));
                txtBuscarUM.addCaretListener(new javax.swing.event.CaretListener() {
                    public void caretUpdate(javax.swing.event.CaretEvent evt) {
                        txtBuscarUMCaretUpdate(evt);
                    }
                });
                txtBuscarUM.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarUMActionPerformed(evt);
                    }
                });
                txtBuscarUM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarUMKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBuscarUMKeyTyped(evt);
                    }
                });

                btnBuscarUM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                btnBuscarUM.setBorder(null);
                btnBuscarUM.setContentAreaFilled(false);
                btnBuscarUM.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarUM.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarUMActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                jpanel1.setLayout(jpanel1Layout);
                jpanel1Layout.setHorizontalGroup(
                    jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(txtBuscarUM, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarUM, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCodProducto)
                                .addGap(18, 18, 18))
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(titulo6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );
                jpanel1Layout.setVerticalGroup(
                    jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel1Layout.createSequentialGroup()
                        .addComponent(titulo6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarUM, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(lblCodProducto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnBuscarUM, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(11, 11, 11))
                );

                tb_UM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_UM.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_UM.setRowHeight(20);
                tb_UM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_UMKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(tb_UM);

                jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel2.setText("Producto:");

                lblProducto.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel4.setText("Unidad de medida:");

                lblUm.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/nuevo.png"))); // NOI18N
                jButton1.setContentAreaFilled(false);
                jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout unidad_medidaLayout = new javax.swing.GroupLayout(unidad_medida.getContentPane());
                unidad_medida.getContentPane().setLayout(unidad_medidaLayout);
                unidad_medidaLayout.setHorizontalGroup(
                    unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(unidad_medidaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(unidad_medidaLayout.createSequentialGroup()
                                .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(unidad_medidaLayout.createSequentialGroup()
                                        .addComponent(lblUm, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                unidad_medidaLayout.setVerticalGroup(
                    unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(unidad_medidaLayout.createSequentialGroup()
                        .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(unidad_medidaLayout.createSequentialGroup()
                                .addGroup(unidad_medidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                    .addComponent(lblUm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 34, Short.MAX_VALUE))
                );

                insertar_um.setMinimumSize(new java.awt.Dimension(424, 158));
                insertar_um.setResizable(false);

                jpanel2.setBackground(new java.awt.Color(126, 5, 46));

                titulo7.setBackground(new java.awt.Color(0, 102, 102));
                titulo7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 34)); // NOI18N
                titulo7.setForeground(new java.awt.Color(255, 255, 255));
                titulo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo7.setText("Unidad de Medida");
                titulo7.setToolTipText("");
                titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
                jpanel2.setLayout(jpanel2Layout);
                jpanel2Layout.setHorizontalGroup(
                    jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(titulo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                );
                jpanel2Layout.setVerticalGroup(
                    jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo7, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                );

                jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel3.setText("Unidad de Medida");

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                jButton2.setContentAreaFilled(false);
                jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout insertar_umLayout = new javax.swing.GroupLayout(insertar_um.getContentPane());
                insertar_um.getContentPane().setLayout(insertar_umLayout);
                insertar_umLayout.setHorizontalGroup(
                    insertar_umLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(insertar_umLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNuevoUM, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(65, Short.MAX_VALUE))
                );
                insertar_umLayout.setVerticalGroup(
                    insertar_umLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertar_umLayout.createSequentialGroup()
                        .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(insertar_umLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNuevoUM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(0, 51, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                tbProductoAlmacen.setComponentPopupMenu(guardar);
                tbProductoAlmacen.setRowHeight(25);
                tbProductoAlmacen.setSelectionBackground(new java.awt.Color(0, 153, 153));
                tbProductoAlmacen.setSelectionForeground(new java.awt.Color(0, 0, 0));
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

                jpanel.setBackground(new java.awt.Color(126, 5, 46));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Catálogo de Productos");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
                lblUsu.setForeground(new java.awt.Color(255, 255, 255));
                lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                lblUsu.setText("Usuario");

                txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscar.setSelectionColor(new java.awt.Color(126, 5, 46));
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
                        .addGap(16, 16, 16)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(titulo5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(titulo5)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 6, Short.MAX_VALUE))
                );

                jTextField1.setEditable(false);
                jTextField1.setBackground(new java.awt.Color(242, 136, 136));
                jTextField1.setForeground(new java.awt.Color(242, 136, 136));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                jLabel1.setText("Sin Cantidad de Unidad de Medida");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

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
        buscar_catalogo();
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    public void buscar_catalogo(){
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
    }
    private void tbProductoAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoAlmacenMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenMouseClicked

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
   
                 char tecla= evt.getKeyChar();
                        if(tecla==KeyEvent.VK_ENTER){
         if(tbProductoAlmacen.getRowCount()>0){    
         ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                   
        try {
          ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
          ImageIcon i1=new ImageIcon(this.getClass().getResource("/imagenes/iconos/aceptar24x24.png"));
          
          int filaselec=tbProductoAlmacen.getSelectedRow();
          if(filaselec<0){
           JOptionPane.showMessageDialog(rootPane, "Seleccione un Producto");
           }else if(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString())<0){
           JOptionPane.showMessageDialog(this, "La cantidad ingresada debe ser un valor entero positivo.",null, 1, alerta);
           tbProductoAlmacen.setValueAt("0",filaselec, 3);
           }
          else{
          int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ASIGNAR la "
              + "Cantidad de Unidad de Medida al Producto \n"+tbProductoAlmacen.getValueAt(filaselec, 2).toString()+" ?",
              "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  Producto_Insumos P= new  Producto_Insumos();
                  P.setCod_produc(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                  P.setCantidad_medida(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString()));
                  
                  if(P.ASIGNAR_UNIDAD_MEDIDA_MODIFICAR()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados",null, 1, i1);
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");
                      tbProductoAlmacen.setValueAt("0",filaselec, 3);
                  }}
           }}catch (Exception e) {
                     JOptionPane.showMessageDialog(this, "Debe Ingresar una cantidad en números",null, 1, alerta);
        }
                            }
                        }
    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void tbProductoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                   
        try {
            
        
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
          ImageIcon i1=new ImageIcon(this.getClass().getResource("/imagenes/iconos/aceptar24x24.png"));
          
           int filaselec=tbProductoAlmacen.getSelectedRow();
          if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Producto");
           }else if(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString())<0){
               JOptionPane.showMessageDialog(this, "La cantidad ingresada debe ser un valor entero positivo.",null, 1, alerta);
            tbProductoAlmacen.setValueAt("0",filaselec, 3);
           }
          else{
        int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ASIGNAR la "
                 + "Cantidad de Unidad de Medida al Producto \n"+tbProductoAlmacen.getValueAt(filaselec, 2).toString()+" ?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  Producto_Insumos P= new  Producto_Insumos();
                  P.setCod_produc(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                  P.setCantidad_medida(Integer.parseInt(tbProductoAlmacen.getValueAt(filaselec, 3).toString()));
                  
                  if(P.ASIGNAR_UNIDAD_MEDIDA_MODIFICAR()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados",null, 1, i1);
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");

                  }}
           }}catch (Exception e) {
                     JOptionPane.showMessageDialog(this, "Debe Ingresar una cantidad en números",null, 1, alerta);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       int filaselec=tbProductoAlmacen.getSelectedRow();
        unidad_medida.setVisible(true);
        lblCodProducto.setText(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
        lblProducto.setText(tbProductoAlmacen.getValueAt(filaselec, 2).toString());
        lblUm.setText(tbProductoAlmacen.getValueAt(filaselec, 4).toString());
        
        txtBuscarUM.setText("");
        cargarUM();
        formatoUM();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

        
    private void txtBuscarUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarUMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUMActionPerformed

    private void txtBuscarUMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUMKeyPressed
//        tb_UM.getSelectionModel().setSelectionInterval(0, 0);
//    tb_UM.requestFocus();
    }//GEN-LAST:event_txtBuscarUMKeyPressed
    public void buscar_unidad_medida(){
      try {
             String titulos[]={"Nº","Código","Unidad de Medida"};
            m2=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m2);
            String fila[]=new String[6];
            
            Usuario obj=new Usuario();
            String consulta="exec sp_unidad_medida_almacen ?,?";
        
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,txtBuscarUM.getText());
            cmd.setString(2,"2");
            ResultSet r= cmd.executeQuery();
            int c=1;
            
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                m2.addRow(fila);
                c++;
            }
            tb_UM.setModel(m2);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m2);
            tb_UM.setRowSorter(elQueOrdena);
            this.tb_UM.setModel(m2);
            formatoUM();
    } catch (Exception e) {
    }  
    }
            
    private void txtBuscarUMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUMKeyTyped

    private void tb_UMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_UMKeyPressed
        ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
                   
        try {
            
        
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
          ImageIcon i1=new ImageIcon(this.getClass().getResource("/imagenes/iconos/aceptar24x24.png"));
          
           int filaselec=tb_UM.getSelectedRow();
          if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione una Unidad de Medida");
           }
          else{
        int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR la Unidad de Medida ?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                  Producto_Insumos P= new  Producto_Insumos();
                  P.setCod_produc(lblCodProducto.getText());
                  P.setCod_unidad(Integer.parseInt(tb_UM.getValueAt(filaselec, 1).toString()));
                  
                  if(P.ASIGNAR_UNIDAD_MEDIDA()){
                      unidad_medida.setVisible(false);
                  cargarProducto();
                  formatoProducto();
                  buscar_catalogo();
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");

                  }}
           }}catch (Exception e) {
                     JOptionPane.showMessageDialog(this, e.getMessage(),null, 1, alerta);
        }
    }//GEN-LAST:event_tb_UMKeyPressed

    private void txtBuscarUMCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarUMCaretUpdate
        buscar_unidad_medida();
    }//GEN-LAST:event_txtBuscarUMCaretUpdate

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        Producto_Insumos me=new Producto_Insumos();
        try{
         
            if(txtNuevoUM.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Ingrese una Unidad de Medida");
          }  
          else if(me.Unidad_Medida_ver(txtNuevoUM.getText())>0){
              JOptionPane.showMessageDialog(rootPane, "La Unidad de Medida ingresada ya existe\nIntente nuevamente");
              txtNuevoUM.setText("");
              txtNuevoUM.requestFocus();
          }else{
              
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar == 0 ){
                  Producto_Insumos meGuardar = new Producto_Insumos();
                  meGuardar.setUnidad_medida(txtNuevoUM.getText());
  
                  if(meGuardar.Unidad_Medida_guardar()){
                      JOptionPane.showMessageDialog(null, "Datos Guardados");
                     insertar_um.setVisible(false);
                      
                  }
                  else{
                      JOptionPane.showMessageDialog(rootPane, "El Contenedor ingresado ya existe\nIntente nuevamente");
              
                  }}
          }}catch(Exception e) {
              JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
              
          }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       insertar_um.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarUMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarUMActionPerformed

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
            java.util.logging.Logger.getLogger(frm_Asig_Catalogo_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Catalogo_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Catalogo_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Asig_Catalogo_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Asig_Catalogo_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarUM;
    private javax.swing.JPopupMenu guardar;
    private javax.swing.JDialog insertar_um;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JLabel lblCodProducto;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblUm;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JTable tbProductoAlmacen;
    private javax.swing.JTable tb_UM;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarUM;
    private javax.swing.JTextField txtNuevoUM;
    private javax.swing.JDialog unidad_medida;
    // End of variables declaration//GEN-END:variables
}
