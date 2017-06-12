/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;


import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import modelos.Usuario;
import servicios.Conexion;
import static vista.COSTOS.Costos_Sustentacion.lblGananciaPer;
import static vista.COSTOS.Costos_Sustentacion.tbPersonal;
import static vista.COSTOS.Costos_Sustentacion.tbResumenCostos;
import static vista.COSTOS.Costos_Sustentacion.txtGananciaPerdida;
import static vista.Principal.fechaActual;

/**
 *
 * @author USUARIO
 */
public class Costos_Sustentacion_Detalle_Herramientas extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion conectar=new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    DefaultTableModel m,m1,totalh;
    /**
     * Creates new form Costos_Sustentacion_Detalle
     */
    public Costos_Sustentacion_Detalle_Herramientas() {
        initComponents();
        con=conectar.conectar();
        setLocationRelativeTo(null);
        BUSCAR_PRODUCTO_REFERENCIA.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        BUSCAR_PRODUCTO_REFERENCIA.getContentPane().setBackground(Color.WHITE);
        //cargarCostosSustentacion();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        h1 = new Thread(this);
        h1.start();
        //Mostrar fecha
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());    
        editable();
        txtCodTipoSust.setVisible(false);
        txtCodigoDepreciacion.setVisible(false);
        cargarPR();
        
        CTipoSustentacion ts=new CTipoSustentacion();
        txtCodTipoSust.setText(ts.obtenerCodTipo(txtTipo.getText()));


                //para limpiar el txt al darle click
 txtBuscar.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscar.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} );
 
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
   
    public void cargarPR(){
        try {
            String titulos[]={"Cod_Depreciacion","Producto","Codigo Patrimonial","Fecha de Compra","Precio de Compra",
            "Fecha Fin a Depreciar","Vida Util en Meses","Depreciación Mensual","Depreciacion Diaria",
            "Total de Meses a Depreciar","Depreciacion Acumulada","Valor Neto"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];

            Usuario obj=new Usuario();
        String consulta="exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_ALMACEN_listar";
        ResultSet r;
        r=conectar.Listar(consulta);
        while(r.next()){
            for (int i=0; i<12; i++){
           fila[i]=r.getString(i+1);
       }
                m.addRow(fila);
            }
        
            tbProductoAlmacen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m);
            formatoPR();
    } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
    }
    }
       
     public void formatoPR(){
    tbProductoAlmacen.getColumnModel().getColumn(1).setPreferredWidth(150);  
    tbProductoAlmacen.getColumnModel().getColumn(2).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(3).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(4).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(5).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(6).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(7).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(8).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(9).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(10).setPreferredWidth(150);
    tbProductoAlmacen.getColumnModel().getColumn(11).setPreferredWidth(150);
    //Ocultar    
    tbProductoAlmacen.getColumnModel().getColumn(0).setMinWidth(0);
    tbProductoAlmacen.getColumnModel().getColumn(0).setMaxWidth(0);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BUSCAR_PRODUCTO_REFERENCIA = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductoAlmacen = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
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
        btnProducto = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCodPratrimonial = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDepreciacionMensual = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtVidaUtilMeses = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDepreciacionDiaria = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtDepreciacionAcumulada = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtValorNeto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtAnioCompra = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtValorCompra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTotalMesesDepreciar = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtFechaFin = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtCodTipoSust = new javax.swing.JTextField();
        txtCodigoDepreciacion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        BUSCAR_PRODUCTO_REFERENCIA.setAlwaysOnTop(true);
        BUSCAR_PRODUCTO_REFERENCIA.setMinimumSize(new java.awt.Dimension(831, 506));

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
        tbProductoAlmacen.setRowHeight(25);
        tbProductoAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProductoAlmacenKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductoAlmacen);

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel8.setText("PRODUCTO REFERENCIA");

        txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
        txtBuscar.setText("Ingresar Producto");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscar.setBorder(null);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BUSCAR_PRODUCTO_REFERENCIALayout = new javax.swing.GroupLayout(BUSCAR_PRODUCTO_REFERENCIA.getContentPane());
        BUSCAR_PRODUCTO_REFERENCIA.getContentPane().setLayout(BUSCAR_PRODUCTO_REFERENCIALayout);
        BUSCAR_PRODUCTO_REFERENCIALayout.setHorizontalGroup(
            BUSCAR_PRODUCTO_REFERENCIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                        .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jLabel8))
                            .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                                .addGap(304, 304, 304)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 334, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BUSCAR_PRODUCTO_REFERENCIALayout.setVerticalGroup(
            BUSCAR_PRODUCTO_REFERENCIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BUSCAR_PRODUCTO_REFERENCIALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BUSCAR_PRODUCTO_REFERENCIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(720, 571));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Equipamiento Básico");

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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblhPersonal)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(109, 109, 109)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
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
        txtTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTipo.setText("EQUIPAMIENTO BASICO");
        txtTipo.setEnabled(false);

        txtProducto.setEditable(false);

        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(txtProducto))
                .addGap(27, 27, 27)
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(14, 14, 14))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel12.setText("Cod. Patrimonial:");

        txtCodPratrimonial.setBackground(java.awt.SystemColor.controlHighlight);
        txtCodPratrimonial.setForeground(new java.awt.Color(51, 51, 51));

        jLabel17.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel17.setText("Depreciacion Mensual:");

        txtDepreciacionMensual.setBackground(java.awt.SystemColor.controlHighlight);
        txtDepreciacionMensual.setForeground(new java.awt.Color(51, 51, 51));

        jLabel18.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel18.setText("Vida Util en Meses:");

        txtVidaUtilMeses.setBackground(java.awt.SystemColor.controlHighlight);
        txtVidaUtilMeses.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel7.setText("Depreciacion Diaria:");

        txtDepreciacionDiaria.setBackground(java.awt.SystemColor.controlHighlight);
        txtDepreciacionDiaria.setForeground(new java.awt.Color(51, 51, 51));

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel14.setText("Depreciación Acumulada:");

        txtDepreciacionAcumulada.setBackground(java.awt.SystemColor.controlHighlight);
        txtDepreciacionAcumulada.setForeground(new java.awt.Color(51, 51, 51));

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel19.setText("Valor Neto:");

        txtValorNeto.setBackground(java.awt.SystemColor.controlHighlight);
        txtValorNeto.setForeground(new java.awt.Color(51, 51, 51));

        jLabel20.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel20.setText("Año de Compra:");

        txtAnioCompra.setBackground(java.awt.SystemColor.controlHighlight);
        txtAnioCompra.setForeground(new java.awt.Color(51, 51, 51));

        jLabel21.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel21.setText("Precio de Compra:");

        txtValorCompra.setBackground(java.awt.SystemColor.controlHighlight);
        txtValorCompra.setForeground(new java.awt.Color(51, 51, 51));

        jLabel13.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel13.setText("Total de Meses a Depreciar:");

        txtTotalMesesDepreciar.setBackground(java.awt.SystemColor.controlHighlight);
        txtTotalMesesDepreciar.setForeground(new java.awt.Color(51, 51, 51));

        jLabel22.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel22.setText("Fecha Fin a Depreciar");

        txtFechaFin.setBackground(java.awt.SystemColor.controlHighlight);
        txtFechaFin.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtValorNeto)
                            .addComponent(txtCodPratrimonial, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaFin, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepreciacionDiaria, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAnioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel18))
                                        .addGap(50, 50, 50))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtTotalMesesDepreciar, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtVidaUtilMeses))
                                        .addGap(39, 39, 39)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14)
                                    .addComponent(txtDepreciacionMensual, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(txtDepreciacionAcumulada))))
                        .addGap(41, 41, 41))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodPratrimonial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDepreciacionMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVidaUtilMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtDepreciacionAcumulada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTotalMesesDepreciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepreciacionDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtValorNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(31, 31, 31)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnAceptar.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dialog-ok-apply.png"))); // NOI18N
        btnAceptar.setMnemonic('A');
        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
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

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
        jLabel9.setText("Salir(Esc)");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
        jLabel11.setText("Aceptar(Alt+A)");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/cancelar16x16.png"))); // NOI18N
        jLabel16.setText("Cancelar(Alt+C)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(jLabel11)
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btnAceptar)
                        .addGap(134, 134, 134)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(txtCodigoDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoDepreciacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(jButton6))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("EQUIPAMIENTO BÁSICO");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        // TODO add your handling code here:
        BUSCAR_PRODUCTO_REFERENCIA.setVisible(true);
                tbProductoAlmacen.getSelectionModel().setSelectionInterval(0, 0);
                    tbProductoAlmacen.requestFocus();
        
    }//GEN-LAST:event_btnProductoActionPerformed

    private void tbProductoAlmacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductoAlmacenKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                try{
                    BUSCAR_PRODUCTO_REFERENCIA.setVisible(false);
                    int filaselec=tbProductoAlmacen.getSelectedRow();
                    txtProducto.setText(tbProductoAlmacen.getValueAt(filaselec, 1).toString());
                    txtCodigoDepreciacion.setText(tbProductoAlmacen.getValueAt(filaselec, 0).toString());
                    txtCodPratrimonial.setText(tbProductoAlmacen.getValueAt(filaselec, 2).toString());
                    txtAnioCompra.setText(tbProductoAlmacen.getValueAt(filaselec, 3).toString());
                    txtValorCompra.setText(tbProductoAlmacen.getValueAt(filaselec, 4).toString());
                    txtFechaFin.setText(tbProductoAlmacen.getValueAt(filaselec, 5).toString());
                    txtVidaUtilMeses.setText(tbProductoAlmacen.getValueAt(filaselec, 6).toString());
                    txtDepreciacionMensual.setText(tbProductoAlmacen.getValueAt(filaselec, 7).toString());
                    txtDepreciacionDiaria.setText(tbProductoAlmacen.getValueAt(filaselec, 8).toString());
                    txtTotalMesesDepreciar.setText(tbProductoAlmacen.getValueAt(filaselec, 9).toString());
                    txtDepreciacionAcumulada.setText(tbProductoAlmacen.getValueAt(filaselec, 10).toString());
                    txtValorNeto.setText(tbProductoAlmacen.getValueAt(filaselec, 11).toString());
                    
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
    }
    }//GEN-LAST:event_tbProductoAlmacenKeyPressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(txtProducto.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Seleccione un Producto");
        }
        else{
        dispose();
        
                    mostrarDetalleHerramienta();
                    cargarResumenCostoHerramienta();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbProductoAlmacen.setModel(new DefaultTableModel());
            String titulos[]={"Cod_Depreciacion","Producto","Codigo Patrimonial","Fecha de Compra","Precio de Compra",
            "Fecha Fin a Depreciar","Vida Util en Meses","Depreciación Mensual","Depreciacion Diaria",
            "Total de Meses a Depreciar","Depreciacion Acumulada","Valor Neto"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];

            Usuario obj=new Usuario();
            consulta="exec COSTOS_COSTOS_SUSTENTACION_MAQUI_HERRA_ALMACEN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            while(r.next()){
            for (int i=0; i<12; i++){
            fila[i]=r.getString(i+1);
            }
                m.addRow(fila);
            }
            tbProductoAlmacen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbProductoAlmacen.setRowSorter(elQueOrdena);
            this.tbProductoAlmacen.setModel(m);
            formatoPR();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    
    public void editable(){
        txtCodPratrimonial.setEditable(false);
        txtVidaUtilMeses.setEditable(false);
        txtTotalMesesDepreciar.setEditable(false);
        txtDepreciacionMensual.setEditable(false);
        txtDepreciacionDiaria.setEditable(false);
        txtDepreciacionAcumulada.setEditable(false);
        txtValorNeto.setEditable(false);
    }
    
    public void mostrarDetalleHerramienta(){
        
        try {
        String cod_tipo,cod_depre,tipo_Sust,nom_detalle,cod_patr,anio_compra,valor_compra,fecha_fin,vida_util,dep_mensual,total_meses,dep_acumulada,valor_neto,dep_diaria;

            cod_tipo=txtCodTipoSust.getText();
            cod_depre=txtCodigoDepreciacion.getText();
            tipo_Sust=txtTipo.getText();
            nom_detalle=txtProducto.getText();
            cod_patr=txtCodPratrimonial.getText();
            anio_compra=txtAnioCompra.getText();
            valor_compra=txtValorCompra.getText();
            fecha_fin=txtFechaFin.getText();
            vida_util=txtVidaUtilMeses.getText();
            dep_mensual=txtDepreciacionMensual.getText();
            total_meses=txtTotalMesesDepreciar.getText();
            dep_acumulada=txtDepreciacionAcumulada.getText();
            valor_neto=txtValorNeto.getText();
            dep_diaria=txtDepreciacionDiaria.getText();
           
          if(Costos_Sustentacion.tbHerramienta.getRowCount()==0){
            dispose();
              m1=(DefaultTableModel) Costos_Sustentacion.tbHerramienta.getModel();
           String filaelemento[]={cod_tipo,cod_depre,tipo_Sust,nom_detalle,cod_patr,anio_compra,valor_compra,fecha_fin,vida_util,dep_mensual,total_meses,dep_acumulada,valor_neto,dep_diaria};
               m1.addRow(filaelemento);
          }
          else{
           if(repiteDetalleHerramienta()==true){
               JOptionPane.showMessageDialog(rootPane,"El Producto ya ha sido ingresado.");   
          }
           else{
              m1=(DefaultTableModel) Costos_Sustentacion.tbHerramienta.getModel();
           String filaelemento[]={cod_tipo,cod_depre,tipo_Sust,nom_detalle,cod_patr,anio_compra,valor_compra,fecha_fin,vida_util,dep_mensual,total_meses,dep_acumulada,valor_neto,dep_diaria};
               m1.addRow(filaelemento); 
               
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean repiteDetalleHerramienta(){   
         boolean c=false;
         for (int i = 0; i < Costos_Sustentacion.tbHerramienta.getRowCount(); i++){    
               if(Costos_Sustentacion.tbHerramienta.getValueAt(i, 1).toString().equalsIgnoreCase(txtCodigoDepreciacion.getText())){
                    c=true;
			}}
               return c;
     }
        
     public void cargarResumenCostoHerramienta(){
        try{
        String tipo_Sust;
        double total=0;
        if(Costos_Sustentacion.tbHerramienta.getRowCount()>0){
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            totalh=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(totalh);
            
            
        for (int i = 0; i < Costos_Sustentacion.tbHerramienta.getRowCount(); i++){    
                    total=total+Double.parseDouble(Costos_Sustentacion.tbHerramienta.getValueAt(i, 13).toString());
                }
                BigDecimal totalhe = new BigDecimal(total);
                       totalhe = totalhe.setScale(2, BigDecimal.ROUND_HALF_UP);
                //Resumen
            String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=Costos_Sustentacion.txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=Costos_Sustentacion.txtTotalInsumos.getText();;
                } 
                else if(i==2){
                fila[0]="SERVICIOS BÁSICOS";
                fila[1]=Costos_Sustentacion.txtTotalSBasicos.getText();;
                } 
                else if(i==3){
                fila[0]="EQUIPAMIENTO BÁSICO";
                fila[1]=String.valueOf(totalhe);
                } 
                else if(i==4){
                fila[0]="INFRAESTRUCTURA";
                fila[1]=Costos_Sustentacion.txtTotalInfraes.getText();;
                } 
                else if(i==5){
                fila[0]="SERVICIOS ADMINISTRATIVOS";
                fila[1]=Costos_Sustentacion.txtTotalSAdminis.getText();;
                } 
                else if(i==6){
                fila[0]="SERVICIOS GENERALES";
                fila[1]=Costos_Sustentacion.txtTotalSGenerales.getText();;
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=Costos_Sustentacion.txtTotalSIntermedios.getText();;
                } 
                totalh.addRow(fila);
                }
                
                Costos_Sustentacion.tbResumenCostos.setModel(totalh);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>(totalh);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
            Costos_Sustentacion.txtTotalHerramienta.setText(String.valueOf(totalhe));
            //Total
            Double insumo,p,sb,inf,sg,sa,si;
            p=Double.parseDouble(Costos_Sustentacion.txtTotalPersonal.getText());
            insumo=Double.parseDouble(Costos_Sustentacion.txtTotalInsumos.getText());
            sb=Double.parseDouble(Costos_Sustentacion.txtTotalSBasicos.getText());
            inf=Double.parseDouble(Costos_Sustentacion.txtTotalInfraes.getText());
            sa=Double.parseDouble(Costos_Sustentacion.txtTotalSAdminis.getText());
            sg=Double.parseDouble(Costos_Sustentacion.txtTotalSGenerales.getText());
            si=Double.parseDouble(Costos_Sustentacion.txtTotalSIntermedios.getText());
            Double precioTotal=total+p+insumo+sb+inf+sa+sg+si;
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Herramientas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Herramientas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Herramientas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Herramientas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Sustentacion_Detalle_Herramientas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BUSCAR_PRODUCTO_REFERENCIA;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblhPersonal;
    private javax.swing.JTable tbProductoAlmacen;
    private javax.swing.JTextField txtAnioCompra;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodPratrimonial;
    private javax.swing.JTextField txtCodTipoSust;
    private javax.swing.JTextField txtCodigoDepreciacion;
    private javax.swing.JTextField txtDepreciacionAcumulada;
    private javax.swing.JTextField txtDepreciacionDiaria;
    private javax.swing.JTextField txtDepreciacionMensual;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtProducto;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotalMesesDepreciar;
    private javax.swing.JTextField txtValorCompra;
    private javax.swing.JTextField txtValorNeto;
    private javax.swing.JTextField txtVidaUtilMeses;
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
