/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.LABORATORIO.LAB_Conversion;
import modelos.LABORATORIO.LAB_PC_AREA;
import modelos.LABORATORIO.LAB_Resultado_Insumos;
import modelos.LABORATORIO.LAB_Toma_Muestra_Cabecera;
import modelos.Usuario;
import servicios.Conexion;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_CONVERSION extends javax.swing.JFrame {
DefaultTableModel m;
Conexion c=new Conexion();
    /**
     * Creates new form frm_CONVERSION
     */
    public frm_LAB_CONVERSION() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
        this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
         PRODUCTOS.setLocationRelativeTo(this);
        PRODUCTOS.getContentPane().setBackground(Color.WHITE);
        personal.setLocationRelativeTo(null);
        personal.getContentPane().setBackground(Color.white);
        conversion.setLocationRelativeTo(this);
        conversion.getContentPane().setBackground(Color.WHITE);
        formatoConversion();
        
          LAB_PC_AREA pa=new LAB_PC_AREA();
        lblServicio.setText(pa.LAB_PC_AREA_SERVICIO());
        
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

         public void cargarProducto(){
    try {
            String consulta="";
             String titulos[]={"Nº","Codigo","Nombre del Producto","Subclase producto", "Marca",
                "Unidad medida","Lote"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_LAB_KARDEX_PRODUCTO_ALMACEN ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            cmd.setString(2, "1");
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
                m.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m);
    } catch (Exception e) {
    }
}
         
    public void formatoProducto(){
        tbProductoAlmacen.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(130);
        tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(120);
        tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbProductoAlmacen.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbProductoAlmacen.getColumnModel().getColumn(6).setPreferredWidth(150);
        tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
        tbProductoAlmacen.requestFocus();
    }
    
    public void formatoConversion(){
            tb_Conversion.getColumnModel().getColumn(0).setPreferredWidth(140);
            tb_Conversion.getColumnModel().getColumn(1).setPreferredWidth(258);
            tb_Conversion.getColumnModel().getColumn(2).setPreferredWidth(180);
            tb_Conversion.getColumnModel().getColumn(3).setPreferredWidth(100);
            tb_Conversion.getColumnModel().getColumn(4).setPreferredWidth(180);
            tb_Conversion.getColumnModel().getColumn(5).setPreferredWidth(120);

     }
    
     public void Personal_cargar(){
         String tipo="",serArea="";
         if(lblArea.getText().equalsIgnoreCase("")){
             tipo="1";
             serArea=lblServicio.getText();
         }else{
             tipo="2";
             serArea=lblArea.getText();
         }
    try {
             String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
            
        String consulta="exec sp_PERSONAL_ROL ?,?,?";
       PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
           cmd.setString(1, "");
            cmd.setString(2, serArea);
            cmd.setString(3, tipo);
        ResultSet r=cmd.executeQuery();
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
                m.addRow(fila);
                c++;
            }
            tbPersonal.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, e.getMessage());
    }
}
    public void Personal_formato(){
    tbPersonal.getColumnModel().getColumn(0).setPreferredWidth(40);
    tbPersonal.getColumnModel().getColumn(1).setPreferredWidth(100);
    tbPersonal.getColumnModel().getColumn(2).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(3).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(4).setPreferredWidth(200);
    tbPersonal.getColumnModel().getColumn(5).setPreferredWidth(240);
    tbPersonal.getColumnModel().getColumn(6).setPreferredWidth(120);
    tbPersonal.getColumnModel().getColumn(7).setPreferredWidth(120);
    tbPersonal.getSelectionModel().setSelectionInterval(0, 0);
            tbPersonal.requestFocus();
}
    
    public void conversiones(){
        try {
            String consulta="";
             String titulos[]={"Nº","cod_conv_cab","Codigo","Nombre del Producto","Unidad de Medida"
                     , "Cantidad","cod_per_verif","Personal Verifica","cod_per_CONF","Personal Confirma","Fecha de Conversión"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
           Usuario obj=new Usuario();
            consulta="exec sp_LAB_CONVERSION ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
            cmd.setString(2, "1");
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
            fila[9]=r.getString(9);
            fila[10]=r.getString(10);
                m.addRow(fila);
                c++;
            }
            tb_Conversiones.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Conversiones.setRowSorter(elQueOrdena);
            this.tb_Conversiones.setModel(m);
    } catch (Exception e) {
    }
    }
    
    public void conversiones_formato(){
        tb_Conversiones.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_Conversiones.getColumnModel().getColumn(2).setPreferredWidth(110);
    tb_Conversiones.getColumnModel().getColumn(3).setPreferredWidth(210);
    tb_Conversiones.getColumnModel().getColumn(4).setPreferredWidth(120);
    tb_Conversiones.getColumnModel().getColumn(5).setPreferredWidth(100);
    tb_Conversiones.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_Conversiones.getColumnModel().getColumn(9).setPreferredWidth(150);
    tb_Conversiones.getColumnModel().getColumn(10).setPreferredWidth(130);
    //Ocultar    
    tb_Conversiones.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Conversiones.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Conversiones.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Conversiones.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_Conversiones.getColumnModel().getColumn(8).setMinWidth(0);
    tb_Conversiones.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_Conversiones.getSelectionModel().setSelectionInterval(0, 0);
            tb_Conversiones.requestFocus();
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
        txtBuscar = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbProductoAlmacen = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jpanel1 = new javax.swing.JPanel();
            titulo6 = new javax.swing.JLabel();
            lblTipo = new javax.swing.JLabel();
            jLabel19 = new javax.swing.JLabel();
            jpPopupMenu = new javax.swing.JPopupMenu();
            jpagregar = new javax.swing.JMenuItem();
            jSeparator1 = new javax.swing.JPopupMenu.Separator();
            jpregresar = new javax.swing.JMenuItem();
            personal = new javax.swing.JDialog();
            txtBuscarPersonal = new javax.swing.JTextField();
            jLabel14 = new javax.swing.JLabel();
            btnBuscarPersonal = new javax.swing.JButton();
            jScrollPane3 = new javax.swing.JScrollPane();
            tbPersonal = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                cbxBuscar2 = new javax.swing.JComboBox();
                jpanel2 = new javax.swing.JPanel();
                titulo7 = new javax.swing.JLabel();
                lbltipo = new javax.swing.JLabel();
                conversion = new javax.swing.JDialog();
                txtConver = new javax.swing.JTextField();
                btnBuscarConversiones = new javax.swing.JButton();
                jScrollPane4 = new javax.swing.JScrollPane();
                tb_Conversiones = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jpanel3 = new javax.swing.JPanel();
                    titulo8 = new javax.swing.JLabel();
                    jLabel20 = new javax.swing.JLabel();
                    jpanel = new javax.swing.JPanel();
                    titulo5 = new javax.swing.JLabel();
                    lblUsu = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
                    btnguardar = new javax.swing.JButton();
                    btnBuscar1 = new javax.swing.JButton();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    tb_Conversion = new javax.swing.JTable();
                    jPanel9 = new javax.swing.JPanel();
                    txtProductoConversion = new javax.swing.JTextField();
                    btnBuscarProAlm = new javax.swing.JButton();
                    btnAgregar = new javax.swing.JButton();
                    btnQuitar = new javax.swing.JButton();
                    jPanel6 = new javax.swing.JPanel();
                    jLabel28 = new javax.swing.JLabel();
                    jLabel29 = new javax.swing.JLabel();
                    lblCodigo = new javax.swing.JLabel();
                    jLabel9 = new javax.swing.JLabel();
                    jLabel10 = new javax.swing.JLabel();
                    jPanel10 = new javax.swing.JPanel();
                    txtCantidad = new javax.swing.JTextField();
                    jLabel11 = new javax.swing.JLabel();
                    lblUnidadMedida = new javax.swing.JLabel();
                    jLabel12 = new javax.swing.JLabel();
                    jLabel13 = new javax.swing.JLabel();
                    jPanel11 = new javax.swing.JPanel();
                    txtPersonalConfirma = new javax.swing.JTextField();
                    btnBuscarPerVerif = new javax.swing.JButton();
                    btnBuscarPerConf = new javax.swing.JButton();
                    jPanel12 = new javax.swing.JPanel();
                    txtPersonalVerifica = new javax.swing.JTextField();
                    jPanel7 = new javax.swing.JPanel();
                    jLabel30 = new javax.swing.JLabel();
                    jLabel31 = new javax.swing.JLabel();
                    lblCodPerVerifica = new javax.swing.JLabel();
                    lblCodPerConfirma = new javax.swing.JLabel();
                    lblArea = new javax.swing.JLabel();
                    lblServicio = new javax.swing.JLabel();

                    PRODUCTOS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    PRODUCTOS.setAlwaysOnTop(true);
                    PRODUCTOS.setAutoRequestFocus(false);
                    PRODUCTOS.setMinimumSize(new java.awt.Dimension(917, 577));
                    PRODUCTOS.setResizable(false);

                    txtBuscar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                    txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
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

                    btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarProducto.setBorder(null);
                    btnBuscarProducto.setContentAreaFilled(false);
                    btnBuscarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarProductoActionPerformed(evt);
                        }
                    });

                    tbProductoAlmacen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
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
                    ));
                    tbProductoAlmacen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbProductoAlmacen.setComponentPopupMenu(jpPopupMenu);
                    tbProductoAlmacen.setRowHeight(25);
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

                    jpanel1.setBackground(new java.awt.Color(2, 67, 115));

                    titulo6.setBackground(new java.awt.Color(0, 102, 102));
                    titulo6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                    titulo6.setForeground(new java.awt.Color(255, 255, 255));
                    titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo6.setText("Productos");
                    titulo6.setToolTipText("");
                    titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                    jpanel1.setLayout(jpanel1Layout);
                    jpanel1Layout.setHorizontalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(titulo6)
                            .addContainerGap(744, Short.MAX_VALUE))
                    );
                    jpanel1Layout.setVerticalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(titulo6)
                            .addGap(0, 27, Short.MAX_VALUE))
                    );

                    lblTipo.setForeground(new java.awt.Color(255, 255, 255));
                    lblTipo.setText("jLabel1");

                    jLabel19.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel19.setText("Búsqueda por Producto");

                    javax.swing.GroupLayout PRODUCTOSLayout = new javax.swing.GroupLayout(PRODUCTOS.getContentPane());
                    PRODUCTOS.getContentPane().setLayout(PRODUCTOSLayout);
                    PRODUCTOSLayout.setHorizontalGroup(
                        PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PRODUCTOSLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PRODUCTOSLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(183, 183, 183)
                                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66))
                                .addGroup(PRODUCTOSLayout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    PRODUCTOSLayout.setVerticalGroup(
                        PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PRODUCTOSLayout.createSequentialGroup()
                            .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PRODUCTOSLayout.createSequentialGroup()
                                    .addGap(93, 93, 93)
                                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(14, 14, 14))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PRODUCTOSLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, 0)
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(42, Short.MAX_VALUE))
                        .addGroup(PRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PRODUCTOSLayout.createSequentialGroup()
                                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 502, Short.MAX_VALUE)))
                    );

                    jpagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    jpagregar.setText("Agregar");
                    jpagregar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jpagregarActionPerformed(evt);
                        }
                    });
                    jpPopupMenu.add(jpagregar);
                    jpPopupMenu.add(jSeparator1);

                    jpregresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jpregresar.setText("Regresar");
                    jpregresar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jpregresarActionPerformed(evt);
                        }
                    });
                    jpPopupMenu.add(jpregresar);

                    personal.setAlwaysOnTop(true);
                    personal.setMinimumSize(new java.awt.Dimension(852, 504));

                    txtBuscarPersonal.setForeground(new java.awt.Color(0, 51, 51));
                    txtBuscarPersonal.setEnabled(false);
                    txtBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscarPersonalActionPerformed(evt);
                        }
                    });
                    txtBuscarPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarPersonalKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtBuscarPersonalKeyTyped(evt);
                        }
                    });

                    jLabel14.setText("Búsqueda por:");

                    btnBuscarPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                    btnBuscarPersonal.setBorder(null);
                    btnBuscarPersonal.setEnabled(false);
                    btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarPersonalActionPerformed(evt);
                        }
                    });

                    tbPersonal.setModel(new javax.swing.table.DefaultTableModel(
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
                    tbPersonal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tbPersonal.setRowHeight(25);
                    tbPersonal.getTableHeader().setReorderingAllowed(false);
                    tbPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbPersonalMouseClicked(evt);
                        }
                    });
                    tbPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbPersonalKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            tbPersonalKeyTyped(evt);
                        }
                    });
                    jScrollPane3.setViewportView(tbPersonal);

                    cbxBuscar2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Cargo", "Apellidos y Nombres" }));
                    cbxBuscar2.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            cbxBuscar2ItemStateChanged(evt);
                        }
                    });

                    jpanel2.setBackground(new java.awt.Color(2, 67, 115));

                    titulo7.setBackground(new java.awt.Color(0, 102, 102));
                    titulo7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo7.setForeground(new java.awt.Color(255, 255, 255));
                    titulo7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo7.setText("Personal");
                    titulo7.setToolTipText("");
                    titulo7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    javax.swing.GroupLayout jpanel2Layout = new javax.swing.GroupLayout(jpanel2);
                    jpanel2.setLayout(jpanel2Layout);
                    jpanel2Layout.setHorizontalGroup(
                        jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    jpanel2Layout.setVerticalGroup(
                        jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    lbltipo.setForeground(new java.awt.Color(255, 255, 255));
                    lbltipo.setText("jLabel1");

                    javax.swing.GroupLayout personalLayout = new javax.swing.GroupLayout(personal.getContentPane());
                    personal.getContentPane().setLayout(personalLayout);
                    personalLayout.setHorizontalGroup(
                        personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(personalLayout.createSequentialGroup()
                            .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(personalLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(personalLayout.createSequentialGroup()
                                    .addGap(97, 97, 97)
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(158, 158, 158)
                                    .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbltipo)))
                            .addContainerGap(32, Short.MAX_VALUE))
                        .addComponent(jpanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    personalLayout.setVerticalGroup(
                        personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createSequentialGroup()
                            .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbxBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))
                                .addComponent(lbltipo))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(47, Short.MAX_VALUE))
                    );

                    conversion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    conversion.setAlwaysOnTop(true);
                    conversion.setAutoRequestFocus(false);
                    conversion.setMinimumSize(new java.awt.Dimension(917, 523));
                    conversion.setResizable(false);

                    txtConver.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                    txtConver.setForeground(new java.awt.Color(0, 51, 51));
                    txtConver.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtConverActionPerformed(evt);
                        }
                    });
                    txtConver.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtConverKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtConverKeyTyped(evt);
                        }
                    });

                    btnBuscarConversiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarConversiones.setBorder(null);
                    btnBuscarConversiones.setContentAreaFilled(false);
                    btnBuscarConversiones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarConversiones.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarConversionesActionPerformed(evt);
                        }
                    });

                    tb_Conversiones.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                    tb_Conversiones.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Conversiones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Conversiones.setComponentPopupMenu(jpPopupMenu);
                    tb_Conversiones.setRowHeight(25);
                    tb_Conversiones.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_ConversionesMouseClicked(evt);
                        }
                    });
                    tb_Conversiones.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_ConversionesKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            tb_ConversionesKeyTyped(evt);
                        }
                    });
                    jScrollPane4.setViewportView(tb_Conversiones);

                    jpanel3.setBackground(new java.awt.Color(2, 67, 115));

                    titulo8.setBackground(new java.awt.Color(0, 102, 102));
                    titulo8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                    titulo8.setForeground(new java.awt.Color(255, 255, 255));
                    titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo8.setText("Conversiones Realizadas");
                    titulo8.setToolTipText("");
                    titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
                    jpanel3.setLayout(jpanel3Layout);
                    jpanel3Layout.setHorizontalGroup(
                        jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel3Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(titulo8)
                            .addContainerGap(520, Short.MAX_VALUE))
                    );
                    jpanel3Layout.setVerticalGroup(
                        jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel3Layout.createSequentialGroup()
                            .addComponent(titulo8)
                            .addGap(0, 27, Short.MAX_VALUE))
                    );

                    jLabel20.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                    jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel20.setText("Búsqueda por Producto");

                    javax.swing.GroupLayout conversionLayout = new javax.swing.GroupLayout(conversion.getContentPane());
                    conversion.getContentPane().setLayout(conversionLayout);
                    conversionLayout.setHorizontalGroup(
                        conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(conversionLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(conversionLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtConver, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscarConversiones, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(312, 312, 312))
                                .addGroup(conversionLayout.createSequentialGroup()
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    conversionLayout.setVerticalGroup(
                        conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(conversionLayout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnBuscarConversiones, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtConver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(35, Short.MAX_VALUE))
                        .addGroup(conversionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(conversionLayout.createSequentialGroup()
                                .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 448, Short.MAX_VALUE)))
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                    jpanel.setBackground(new java.awt.Color(2, 67, 115));

                    titulo5.setBackground(new java.awt.Color(0, 102, 102));
                    titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                    titulo5.setForeground(new java.awt.Color(255, 255, 255));
                    titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo5.setText("Conversión");
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

                    btnBuscar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                    btnBuscar1.setMnemonic('B');
                    btnBuscar1.setToolTipText("Buscar (Alt+B)");
                    btnBuscar1.setContentAreaFilled(false);
                    btnBuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscar1ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                    jpanel.setLayout(jpanelLayout);
                    jpanelLayout.setHorizontalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jpanelLayout.createSequentialGroup()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(titulo5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 713, Short.MAX_VALUE)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                    );
                    jpanelLayout.setVerticalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(titulo5)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(2, 2, 2)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnBuscar1))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    tb_Conversion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                    tb_Conversion.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Codigo", "Nombre del Insumo", "Marca", "Cantidad", "Unidad de Medida", "Saldo Actual"
                        }
                    ) {
                        Class[] types = new Class [] {
                            java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
                        };
                        boolean[] canEdit = new boolean [] {
                            false, false, false, true, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                            return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tb_Conversion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Conversion.setRowHeight(24);
                    tb_Conversion.getTableHeader().setReorderingAllowed(false);
                    tb_Conversion.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_ConversionKeyPressed(evt);
                        }
                    });
                    jScrollPane1.setViewportView(tb_Conversion);

                    jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtProductoConversion.setEditable(false);
                    txtProductoConversion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
                    txtProductoConversion.setForeground(new java.awt.Color(102, 102, 102));
                    txtProductoConversion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtProductoConversion.setBorder(null);
                    txtProductoConversion.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtProductoConversionCaretUpdate(evt);
                        }
                    });
                    txtProductoConversion.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtProductoConversionActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                    jPanel9.setLayout(jPanel9Layout);
                    jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(txtProductoConversion, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addGap(0, 0, 0))
                    );
                    jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtProductoConversion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    btnBuscarProAlm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarProAlm.setMnemonic('B');
                    btnBuscarProAlm.setToolTipText("");
                    btnBuscarProAlm.setBorderPainted(false);
                    btnBuscarProAlm.setContentAreaFilled(false);
                    btnBuscarProAlm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarProAlm.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarProAlmActionPerformed(evt);
                        }
                    });

                    btnAgregar.setBackground(new java.awt.Color(102, 102, 102));
                    btnAgregar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                    btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    btnAgregar.setMnemonic('A');
                    btnAgregar.setText("Agregar");
                    btnAgregar.setToolTipText("(Alt + A)");
                    btnAgregar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    btnAgregar.setContentAreaFilled(false);
                    btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnAgregar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAgregarActionPerformed(evt);
                        }
                    });

                    btnQuitar.setBackground(new java.awt.Color(102, 102, 102));
                    btnQuitar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                    btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                    btnQuitar.setMnemonic('Q');
                    btnQuitar.setText("Quitar");
                    btnQuitar.setToolTipText("(Alt + Q)");
                    btnQuitar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    btnQuitar.setContentAreaFilled(false);
                    btnQuitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnQuitar.setPreferredSize(new java.awt.Dimension(109, 33));
                    btnQuitar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnQuitarActionPerformed(evt);
                        }
                    });

                    jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel6.setLayout(null);

                    jLabel28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                    jLabel28.setText("Insumo ");
                    jPanel6.add(jLabel28);
                    jLabel28.setBounds(2, 0, 250, 20);

                    jLabel29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                    jLabel29.setText("______________________________________________________________________________________________________________________________________________________________");
                    jPanel6.add(jLabel29);
                    jLabel29.setBounds(0, 0, 960, 30);

                    lblCodigo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                    lblCodigo.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel6.add(lblCodigo);
                    lblCodigo.setBounds(550, 0, 76, 20);

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel9.setText("Cantidad");

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel10.setText("Unidad de Medida");

                    jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    txtCantidad.setForeground(new java.awt.Color(102, 102, 102));
                    txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtCantidad.setBorder(null);
                    txtCantidad.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtCantidadCaretUpdate(evt);
                        }
                    });
                    txtCantidad.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtCantidadActionPerformed(evt);
                        }
                    });
                    txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtCantidadKeyTyped(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                    jPanel10.setLayout(jPanel10Layout);
                    jPanel10Layout.setHorizontalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    );
                    jPanel10Layout.setVerticalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel11.setText("Insumo");

                    lblUnidadMedida.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
                    lblUnidadMedida.setForeground(new java.awt.Color(51, 51, 51));
                    lblUnidadMedida.setText("___________________________");

                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel12.setText("Personal Verifica");

                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel13.setText("Personal Confirma");

                    jPanel11.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtPersonalConfirma.setEditable(false);
                    txtPersonalConfirma.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    txtPersonalConfirma.setForeground(new java.awt.Color(102, 102, 102));
                    txtPersonalConfirma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPersonalConfirma.setBorder(null);
                    txtPersonalConfirma.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtPersonalConfirmaCaretUpdate(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                    jPanel11.setLayout(jPanel11Layout);
                    jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );
                    jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.Alignment.TRAILING)
                    );

                    btnBuscarPerVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarPerVerif.setMnemonic('B');
                    btnBuscarPerVerif.setToolTipText("");
                    btnBuscarPerVerif.setBorderPainted(false);
                    btnBuscarPerVerif.setContentAreaFilled(false);
                    btnBuscarPerVerif.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarPerVerif.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarPerVerifActionPerformed(evt);
                        }
                    });

                    btnBuscarPerConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarPerConf.setMnemonic('B');
                    btnBuscarPerConf.setToolTipText("");
                    btnBuscarPerConf.setBorderPainted(false);
                    btnBuscarPerConf.setContentAreaFilled(false);
                    btnBuscarPerConf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarPerConf.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarPerConfActionPerformed(evt);
                        }
                    });

                    jPanel12.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtPersonalVerifica.setEditable(false);
                    txtPersonalVerifica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    txtPersonalVerifica.setForeground(new java.awt.Color(102, 102, 102));
                    txtPersonalVerifica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPersonalVerifica.setBorder(null);
                    txtPersonalVerifica.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtPersonalVerificaCaretUpdate(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                    jPanel12.setLayout(jPanel12Layout);
                    jPanel12Layout.setHorizontalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPersonalVerifica, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    );
                    jPanel12Layout.setVerticalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtPersonalVerifica, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel7.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel7.setLayout(null);

                    jLabel30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                    jLabel30.setText("Personal");
                    jPanel7.add(jLabel30);
                    jLabel30.setBounds(2, 0, 250, 20);

                    jLabel31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                    jLabel31.setText("______________________________________________________________________________________________________________________________________________________________");
                    jPanel7.add(jLabel31);
                    jLabel31.setBounds(0, 0, 960, 30);

                    lblCodPerVerifica.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel7.add(lblCodPerVerifica);
                    lblCodPerVerifica.setBounds(340, 0, 65, 18);

                    lblCodPerConfirma.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel7.add(lblCodPerConfirma);
                    lblCodPerConfirma.setBounds(490, 0, 65, 20);

                    lblArea.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel7.add(lblArea);
                    lblArea.setBounds(600, 0, 26, 25);

                    lblServicio.setForeground(new java.awt.Color(255, 255, 255));
                    jPanel7.add(lblServicio);
                    lblServicio.setBounds(690, 0, 26, 20);

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarPerVerif, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarPerConf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(177, 177, 177))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(121, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(btnBuscarProAlm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(58, 58, 58))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1))
                            .addGap(23, 23, 23))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarProAlm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnBuscarPerVerif, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarPerConf, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        limpiar();
        enableDatos(true);
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        int filtro=0,fsaldo=0;
        try{
            
                if(lblCodigo.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Insumo del Examen");
                     filtro=1;
                }  else if(txtCantidad.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Ingrese la Cantidad");
                    filtro=1;
                }else if(lblCodPerVerifica.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Seleccione al Personal que Verificó \nla Conversión de Insumos");
                filtro=1;
            }   else if(lblCodPerConfirma.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Seleccione al Personal que Confirmó \nla Conversión de Insumos.");
                filtro=1;
            }
                else if(tb_Conversion.getRowCount()==0 ){
                    JOptionPane.showMessageDialog(rootPane, "Agregue Insumos para realizar la Conversión");
                    filtro=1;
                }
                else{
                     filtro=0;
                     fsaldo=0;
                        for(int j=0;j<tb_Conversion.getRowCount();j++){
                        if(tb_Conversion.getValueAt(j, 3).toString().equalsIgnoreCase("")){
//                      ||tb_Detalle.getValueAt(j, 20).toString().equalsIgnoreCase("")){
                        filtro=filtro+1;
                        }
                        if(Integer.parseInt(tb_Conversion.getValueAt(j, 3).toString())>Integer.parseInt(tb_Conversion.getValueAt(j, 5).toString())){
//                      ||tb_Detalle.getValueAt(j, 20).toString().equalsIgnoreCase("")){
                        fsaldo=fsaldo+1;
                        }
                        }
                    if(filtro>0){
                    JOptionPane.showMessageDialog(rootPane, "Debe ingresar todas las cantidades");  
                    } 
                    if(fsaldo>0){
                    JOptionPane.showMessageDialog(rootPane, "No hay insumos suficientes para realizar la conversión.\nVerifique el Saldo Actual del Insumo.");  
                    } 
                }
                
                if(filtro==0&&fsaldo==0){
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                        LAB_Conversion convCab = new LAB_Conversion();
                        convCab.setCod_produc(lblCodigo.getText());
                        convCab.setCantidad(Integer.parseInt(txtCantidad.getText()));
                        convCab.setCod_per_verif(lblCodPerVerifica.getText());
                        convCab.setNombre_per_verif(txtPersonalVerifica.getText());
                        convCab.setCod_per_confirma(lblCodPerConfirma.getText());
                        convCab.setNombre_per_confirma(txtPersonalConfirma.getText());

                        convCab.setNom_usu(lblUsu.getText());

                        if(convCab.LAB_Conversion_Cab_guardar()){
                            JOptionPane.showMessageDialog(null, "Datos Guardados");
                            Lab_guardar_Insumos();
                            limpiar();
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
                        }}
                }
                        }catch(Exception e) {
                                JOptionPane.showMessageDialog(this, "Ingrese todos los campos");

                            }
        
    
    }//GEN-LAST:event_btnguardarActionPerformed
 public void Lab_guardar_Insumos(){
       
     for (int i = 0; i < tb_Conversion.getRowCount(); i++){      
               
               LAB_Conversion convDet=new LAB_Conversion();
               convDet.setCod_produc_conv(tb_Conversion.getValueAt(i, 0).toString());
               convDet.setCantidad_conv(Integer.parseInt(tb_Conversion.getValueAt(i, 3).toString()));
               convDet.setNom_usu(lblUsu.getText());
               
                convDet.LAB_Conversion_Det_guardar();
     }
         
    }
    private void txtProductoConversionCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtProductoConversionCaretUpdate

    }//GEN-LAST:event_txtProductoConversionCaretUpdate

    private void btnBuscarProAlmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProAlmActionPerformed
        PRODUCTOS.setVisible(true);
        PRODUCTOS.getContentPane().setBackground(Color.WHITE);
        PRODUCTOS.setLocationRelativeTo(null);//en el centro
        
        cargarProducto();
        formatoProducto();
        
        lblTipo.setText("1");
        jpagregar.setVisible(false);
        jSeparator1.setVisible(false);
        
    }//GEN-LAST:event_btnBuscarProAlmActionPerformed

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
             String titulos[]={"Nº","Codigo","Nombre del Producto","Subclase producto", "Marca",
                "Unidad medida","Lote"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[15];
           Usuario obj=new Usuario();
            consulta="exec sp_LAB_KARDEX_PRODUCTO_ALMACEN ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            cmd.setString(2, "2");
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
                m.addRow(fila);
                c++;
            }
            tbProductoAlmacen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m);
            
            formatoProducto();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void tbProductoAlmacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductoAlmacenMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenMouseClicked

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
      
                 char tecla= evt.getKeyChar();
                        if(tecla==KeyEvent.VK_ENTER){
                          if(lblTipo.getText().equalsIgnoreCase("1") && tbProductoAlmacen.getRowCount()>0){
                               PRODUCTOS.setVisible(false);
                               
                            int filaselec=tbProductoAlmacen.getSelectedRow();
                            lblCodigo.setText(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                            txtProductoConversion.setText(tbProductoAlmacen.getValueAt(filaselec, 2).toString());
                            lblUnidadMedida.setText(tbProductoAlmacen.getValueAt(filaselec, 5).toString());
                              
                           
                          }
                            }

    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void tbProductoAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tbProductoAlmacenKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

        PRODUCTOS.setVisible(true);
        PRODUCTOS.getContentPane().setBackground(Color.WHITE);
        PRODUCTOS.setLocationRelativeTo(null);//en el centro
        
        lblTipo.setText("2");
        jpagregar.setVisible(true);
        jSeparator1.setVisible(true);

        cargarProducto();
        formatoProducto();

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed

        try{
            int filaselec=tb_Conversion.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Producto?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){

                    DefaultTableModel modelo = (DefaultTableModel)tb_Conversion.getModel();
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Registro Eliminado");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Insumo a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void txtProductoConversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoConversionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoConversionActionPerformed

    private void jpagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpagregarActionPerformed
        // TODO add your handling code here:
        
        if(lblTipo.getText().equalsIgnoreCase("2")){
        mostrarDetalle();
        }
        
    }//GEN-LAST:event_jpagregarActionPerformed

    private void jpregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpregresarActionPerformed
        PRODUCTOS.setVisible(false);
    }//GEN-LAST:event_jpregresarActionPerformed

    private void tb_ConversionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ConversionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ConversionKeyPressed

    private void txtCantidadCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCantidadCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadCaretUpdate

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPersonalConfirmaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalConfirmaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalConfirmaCaretUpdate

    private void btnBuscarPerVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPerVerifActionPerformed
        personal.setVisible(true);
        Personal_cargar();
        Personal_formato();
        lbltipo.setText("1");
        txtBuscar.setText("");
        cbxBuscar2.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscarPerVerifActionPerformed

    private void btnBuscarPerConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPerConfActionPerformed
        personal.setVisible(true);
        Personal_cargar();
        Personal_formato();
        lbltipo.setText("2");
        txtBuscar.setText("");
        cbxBuscar2.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscarPerConfActionPerformed

    private void txtPersonalVerificaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalVerificaCaretUpdate

    }//GEN-LAST:event_txtPersonalVerificaCaretUpdate

    private void txtBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPersonalActionPerformed

    private void txtBuscarPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPersonalKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarPersonal.doClick();
        }
    }//GEN-LAST:event_txtBuscarPersonalKeyPressed

    private void txtBuscarPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPersonalKeyTyped

    }//GEN-LAST:event_txtBuscarPersonalKeyTyped

    private void btnBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonalActionPerformed
        // TODO add your handling code here:
        try {
            String tipo="",serArea="",buscar="";
            buscar=txtBuscarPersonal.getText();

            if(lblArea.getText().equalsIgnoreCase("") && cbxBuscar2.getSelectedIndex()==1){
                tipo="3";
                serArea=lblServicio.getText();
            }else if(lblArea.getText().equalsIgnoreCase("") && cbxBuscar2.getSelectedIndex()==2){
                tipo="4";
                serArea=lblServicio.getText();

            }else if(lblArea.getText().length()>0 && cbxBuscar2.getSelectedIndex()==1){
                tipo="5";
                serArea=lblArea.getText();
            }else if(lblArea.getText().length()>0 && cbxBuscar2.getSelectedIndex()==2){
                tipo="6";
                serArea=lblArea.getText();
            }
            String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();

            String consulta="exec sp_PERSONAL_ROL ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, serArea);
            cmd.setString(3, tipo);
            ResultSet r=cmd.executeQuery();
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
                m.addRow(fila);
                c++;
            }
            tbPersonal.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbPersonal.setRowSorter(elQueOrdena);
            this.tbPersonal.setModel(m);
            Personal_formato();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarPersonalActionPerformed

    private void tbPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPersonalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalMouseClicked

    private void tbPersonalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                if(lbltipo.getText().equalsIgnoreCase("1")){
                    personal.setVisible(false);
                    int filaselec=tbPersonal.getSelectedRow();
                    String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                    tbPersonal.getValueAt(filaselec, 3).toString()
                    +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                    txtPersonalVerifica.setText(nombreCompleto);
                    lblCodPerVerifica.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                }
                else if(lbltipo.getText().equalsIgnoreCase("2")){
                    personal.setVisible(false);
                    int filaselec=tbPersonal.getSelectedRow();
                    String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                    tbPersonal.getValueAt(filaselec, 3).toString()
                    +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                    txtPersonalConfirma.setText(nombreCompleto);
                    lblCodPerConfirma.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                }
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tbPersonalKeyPressed

    private void tbPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPersonalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPersonalKeyTyped

    private void cbxBuscar2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBuscar2ItemStateChanged
        // TODO add your handling code here:
        try
        {
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(cbxBuscar2.getSelectedIndex()>0){
                    txtBuscarPersonal.setEnabled(true);
                    btnBuscarPersonal.setEnabled(true);
                }

            }
            else{
                txtBuscarPersonal.setEnabled(false);
                btnBuscarPersonal.setEnabled(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxBuscar2ItemStateChanged

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed

        conversion.setVisible(true);
        txtConver.setText("");
        conversiones();
        conversiones_formato();
        
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtConverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConverActionPerformed

    private void txtConverKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConverKeyPressed
char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
        btnBuscarConversiones.doClick();
        }
    }//GEN-LAST:event_txtConverKeyPressed

    private void txtConverKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConverKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConverKeyTyped

    private void btnBuscarConversionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarConversionesActionPerformed
       try {
            String consulta="";
             String titulos[]={"Nº","cod_conv_cab","Codigo","Nombre del Producto","Unidad de Medida"
                     , "Cantidad","cod_per_verif","Personal Verifica","cod_per_CONF","Personal Confirma","Fecha de Conversión"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
           Usuario obj=new Usuario();
            consulta="exec sp_LAB_CONVERSION ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtConver.getText());
            cmd.setString(2, "2");
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
            fila[9]=r.getString(9);
            fila[10]=r.getString(10);
                m.addRow(fila);
                c++;
            }
            tb_Conversiones.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Conversiones.setRowSorter(elQueOrdena);
            this.tb_Conversiones.setModel(m);
            conversiones_formato();
    } catch (Exception e) {
    }
    }//GEN-LAST:event_btnBuscarConversionesActionPerformed

    private void tb_ConversionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ConversionesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ConversionesMouseClicked

    private void tb_ConversionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ConversionesKeyPressed
        char tecla= evt.getKeyChar();
                        if(tecla==KeyEvent.VK_ENTER){
                          if(tb_Conversiones.getRowCount()>0){
                                 conversion.setVisible(false);
                            int filaselec=tb_Conversiones.getSelectedRow();
                            txtProductoConversion.setText(tb_Conversiones.getValueAt(filaselec, 3).toString());
                            txtCantidad.setText(tb_Conversiones.getValueAt(filaselec, 5).toString());
                            lblUnidadMedida.setText(tb_Conversiones.getValueAt(filaselec, 4).toString());
                              txtPersonalVerifica.setText(tb_Conversiones.getValueAt(filaselec, 7).toString());
                            txtPersonalConfirma.setText(tb_Conversiones.getValueAt(filaselec, 9).toString());
                          
                         tb_Conversion.setBackground(Color.lightGray);
                              enableDatos(false);
                              
                              //DETALLE
                              conversion_detalle(tb_Conversiones.getValueAt(filaselec, 1).toString());
                          }
                            }
    }//GEN-LAST:event_tb_ConversionesKeyPressed
public void conversion_detalle(String cod_cab){
      try {
            String consulta="";
             String titulos[]={"Codigo","Nombre del Insumo",
                 "Marca","Cantidad","Unidad de Medida","Saldo Actual"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
           Usuario obj=new Usuario();
            consulta="exec sp_LAB_CONVERSION ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1,cod_cab);
            cmd.setString(2, "3");
            ResultSet r= cmd.executeQuery();
            int c=1;
        while(r.next()){
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
            fila[4]=r.getString(5);
            //saldo
            LAB_Resultado_Insumos ri=new LAB_Resultado_Insumos();
            fila[5]=ri.LAB_Resultado_Insumo_Saldo(r.getString(1));
            
                m.addRow(fila);
                c++;
            }
            tb_Conversion.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Conversion.setRowSorter(elQueOrdena);
            this.tb_Conversion.setModel(m);
            formatoConversion();
    } catch (Exception e) {
    }
}
    private void tb_ConversionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ConversionesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ConversionesKeyTyped
 
      public void mostrarDetalle(){
        try {
            String Codigo,Nombre_del_Producto,Marca,cantidad,
                Unidad_medida,saldo;
            int filaselec=tbProductoAlmacen.getSelectedRow();
            
            m=(DefaultTableModel) tbProductoAlmacen.getModel();
            Codigo=tbProductoAlmacen.getValueAt(filaselec, 1).toString();
            Nombre_del_Producto=tbProductoAlmacen.getValueAt(filaselec, 2).toString();
            Marca=tbProductoAlmacen.getValueAt(filaselec, 4).toString();
            cantidad="";
            Unidad_medida=tbProductoAlmacen.getValueAt(filaselec, 5).toString();
            
             //saldo
            LAB_Resultado_Insumos ri=new LAB_Resultado_Insumos();
            saldo=ri.LAB_Resultado_Insumo_Saldo(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
          if(tb_Conversion.getRowCount()==0){
              m=(DefaultTableModel) tb_Conversion.getModel();
                String filaelemento[]={Codigo,Nombre_del_Producto,Marca,
                    cantidad,Unidad_medida,saldo};
               m.addRow(filaelemento);
               
          }
          else{
           if(repiteDetalle()==true){
               JOptionPane.showMessageDialog(PRODUCTOS,"El Producto ya ha sido ingresado.");   
          }
           else{
                 m=(DefaultTableModel) tb_Conversion.getModel();
                String filaelemento[]={Codigo,Nombre_del_Producto,Marca,
                    cantidad,Unidad_medida,saldo};
               m.addRow(filaelemento);   
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
     public boolean repiteDetalle(){
         int filaselec=tbProductoAlmacen.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tb_Conversion.getRowCount(); i++){    
               if(tb_Conversion.getValueAt(i, 0).toString().equalsIgnoreCase(tbProductoAlmacen.getValueAt(filaselec, 1).toString())){
                    c=true;
			}}
               return c;
     }
    
     public void limpiar(){
         lblCodigo.setText("");
         txtProductoConversion.setText("");
         txtCantidad.setText("");
         lblUnidadMedida.setText("__________________");
         lblCodPerVerifica.setText("");
         txtPersonalVerifica.setText("");
         lblCodPerConfirma.setText("");
         txtPersonalConfirma.setText("");
         
         DefaultTableModel modelo = (DefaultTableModel)tb_Conversion.getModel(); 
   int filas=tb_Conversion.getRowCount();
   for(int i=0;i<filas;i++){
                    modelo.removeRow(0);
   }
   tb_Conversion.setBackground(Color.white);
     }
     public void enableDatos(boolean tipo){
         txtCantidad.setEnabled(tipo);
         btnBuscarProAlm.setEnabled(tipo);
         btnBuscarPerVerif.setEnabled(tipo);
         btnBuscarPerConf.setEnabled(tipo);
         btnguardar.setEnabled(tipo);
         btnAgregar.setEnabled(tipo);
         btnQuitar.setEnabled(tipo);
         tb_Conversion.setEnabled(tipo);
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
            java.util.logging.Logger.getLogger(frm_LAB_CONVERSION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_CONVERSION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_CONVERSION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_CONVERSION.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_CONVERSION().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog PRODUCTOS;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarConversiones;
    private javax.swing.JButton btnBuscarPerConf;
    private javax.swing.JButton btnBuscarPerVerif;
    private javax.swing.JButton btnBuscarPersonal;
    private javax.swing.JButton btnBuscarProAlm;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitar;
    public static javax.swing.JButton btnguardar;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JDialog conversion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu jpPopupMenu;
    private javax.swing.JMenuItem jpagregar;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JMenuItem jpregresar;
    public static javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCodPerConfirma;
    private javax.swing.JLabel lblCodPerVerifica;
    private javax.swing.JLabel lblCodigo;
    public static javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblUnidadMedida;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JDialog personal;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tbProductoAlmacen;
    private javax.swing.JTable tb_Conversion;
    public static javax.swing.JTable tb_Conversiones;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarPersonal;
    public static javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtConver;
    public static javax.swing.JTextField txtPersonalConfirma;
    public static javax.swing.JTextField txtPersonalVerifica;
    public static javax.swing.JTextField txtProductoConversion;
    // End of variables declaration//GEN-END:variables
}
