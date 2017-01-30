/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.COSTOS;


import modelos.COSTOS.serviciosVarios;
import modelos.COSTOS.CTipoSustentacion;
import groovy.xml.Entity;
import static groovy.xml.Entity.gt;
import static groovy.xml.Entity.lt;
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
import modelos.*;
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
public class Costos_Sustentacion_Detalle_Servicios_Generales extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Conexion conectar=new Conexion();
    Connection con;
    CallableStatement cst;
    ResultSet r;
    Statement st;
    DefaultTableModel m,m1,m2,mresumen;
    
    /**
     * Creates new form Costos_Sustentacion_Detalle
     */
    public Costos_Sustentacion_Detalle_Servicios_Generales() {
        initComponents();
        con=conectar.conectar();
        setLocationRelativeTo(null);
        BuscarTipoSustentacion.setLocationRelativeTo(null);
        BuscarTipoSustentacionServicios.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        BuscarTipoSustentacion.getContentPane().setBackground(Color.WHITE);
        BuscarTipoSustentacionServicios.getContentPane().setBackground(Color.WHITE);

        h1 = new Thread(this);
        h1.start();
        //Mostrar fecha
        Calendar cal=Calendar.getInstance();          
        lblFecha.setText(fechaActual());  
        cargarTS();
        cargarServiciosVarios();
setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
        CTipoSustentacion ts=new CTipoSustentacion();
        txtCodTipoSust.setText(ts.obtenerCodTipo(txtTipo.getText()));
        txtCodTipoSust.setVisible(false);
        txtCodServicio.setVisible(false);
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
 
    //para limpiar el txt al darle click
    btnBuscarTipo.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    btnBuscarTipo.setText("");
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
   
    public void cargarTS(){
        
       DefaultTableModel tabla= new DefaultTableModel();
       try{
       tabla.addColumn("Código");
       tabla.addColumn("Tipo Sustentación");
       
       cst=con.prepareCall("{call COSTOS_COSTOS_TIPO_SUSTENTACION_listar}");
       r=cst.executeQuery();
       while (r.next()){
       Object dato[]=new  Object[3];
       for (int i=0; i<2; i++){
           dato[i]=r.getString(i+1);
       }
       tabla.addRow(dato);
       }
       this.tbTipoSuste.setModel(tabla);
       //Dar formato a la tabla
       tbTipoSuste.getColumnModel().getColumn(0).setPreferredWidth(130);
       tbTipoSuste.getColumnModel().getColumn(1).setPreferredWidth(260);

       }catch (Exception e){}
      
    }
      public void cargarServiciosVarios(){
    try {
             String titulos[]={"Nº","Codigo","Nombre del Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

        String consulta="exec COSTOS_COSTOS_SERVICIOS_VARIOS_listar";
        ResultSet r;
        r=conectar.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tbServicios.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbServicios.setRowSorter(elQueOrdena);
            this.tbServicios.setModel(m);
            formatoServiciosVarios();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void formatoServiciosVarios(){
    tbServicios.getColumnModel().getColumn(0).setPreferredWidth(30);
    tbServicios.getColumnModel().getColumn(1).setPreferredWidth(160);
    tbServicios.getColumnModel().getColumn(2).setPreferredWidth(220);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarTipoSustentacionServicios = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbServicios = new javax.swing.JTable();
        BuscarServicios = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarServicio = new javax.swing.JButton();
        BuscarTipoSustentacion = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTipoSuste = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtTipoSusten = new javax.swing.JTextField();
        btnBuscarTipo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblhPersonal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotalSustentacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtCodTipoSust = new javax.swing.JTextField();
        txtCodServicio = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        BuscarTipoSustentacionServicios.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        BuscarTipoSustentacionServicios.setAlwaysOnTop(true);
        BuscarTipoSustentacionServicios.setMinimumSize(new java.awt.Dimension(405, 500));
        BuscarTipoSustentacionServicios.setResizable(false);

        tbServicios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbServicios.setModel(new javax.swing.table.DefaultTableModel(
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
        tbServicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbServicios.setRowHeight(25);
        tbServicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbServiciosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbServicios);

        BuscarServicios.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
        BuscarServicios.setText("Servicios");

        txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
        txtBuscar.setText("Ingresar Servicio ");
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

        btnBuscarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscarServicio.setBorder(null);
        btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarServicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscarTipoSustentacionServiciosLayout = new javax.swing.GroupLayout(BuscarTipoSustentacionServicios.getContentPane());
        BuscarTipoSustentacionServicios.getContentPane().setLayout(BuscarTipoSustentacionServiciosLayout);
        BuscarTipoSustentacionServiciosLayout.setHorizontalGroup(
            BuscarTipoSustentacionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarTipoSustentacionServiciosLayout.createSequentialGroup()
                .addGroup(BuscarTipoSustentacionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarTipoSustentacionServiciosLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BuscarTipoSustentacionServiciosLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(BuscarServicios))
                    .addGroup(BuscarTipoSustentacionServiciosLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        BuscarTipoSustentacionServiciosLayout.setVerticalGroup(
            BuscarTipoSustentacionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarTipoSustentacionServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BuscarServicios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BuscarTipoSustentacionServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        BuscarTipoSustentacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        BuscarTipoSustentacion.setAlwaysOnTop(true);
        BuscarTipoSustentacion.setMinimumSize(new java.awt.Dimension(416, 485));
        BuscarTipoSustentacion.setResizable(false);

        tbTipoSuste = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbTipoSuste.setModel(new javax.swing.table.DefaultTableModel(
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
        tbTipoSuste.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbTipoSuste.setRowHeight(25);
        tbTipoSuste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTipoSusteKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbTipoSuste);

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 30)); // NOI18N
        jLabel15.setText("Tipo Sustentacion");

        btnBuscarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        btnBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscarTipoSustentacionLayout = new javax.swing.GroupLayout(BuscarTipoSustentacion.getContentPane());
        BuscarTipoSustentacion.getContentPane().setLayout(BuscarTipoSustentacionLayout);
        BuscarTipoSustentacionLayout.setHorizontalGroup(
            BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, BuscarTipoSustentacionLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(txtTipoSusten, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BuscarTipoSustentacionLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(68, 68, 68)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        BuscarTipoSustentacionLayout.setVerticalGroup(
            BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarTipoSustentacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(BuscarTipoSustentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTipoSusten))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISGESH .::. Servicios Generales");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

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

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SERVICIOS GENERALES");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblhPersonal)
                    .addComponent(lblFecha))
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(lblUsu)))))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel4.setText("Tipo Sustentacion:");

        txtTipo.setEditable(false);
        txtTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTipo.setText("SERVICIOS GENERALES");
        txtTipo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel8.setText("Servicios:");

        txtServicio.setEditable(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel9.setText("Cantidad:");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel11.setText("Total Sustentacion: ");

        txtTotalSustentacion.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel10.setText("Precio Sustentacion:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(txtPrecio))
                .addGap(54, 54, 54)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalSustentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotalSustentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
        jLabel16.setText("Salir(Esc)");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
        jLabel18.setText("Aceptar(Alt+A)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/cancelar16x16.png"))); // NOI18N
        jLabel19.setText("Cancelar(Alt+C)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(21, 21, 21)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton5)
                        .addGap(119, 119, 119)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("SERVICIOS GENERALES");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void tbServiciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbServiciosKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbServicios.getSelectedRow();
             Costos_Sustentacion_Detalle_Servicios_Generales.txtCodServicio.setText(String.valueOf(tbServicios.getValueAt(fila, 1))); 
             Costos_Sustentacion_Detalle_Servicios_Generales.txtServicio.setText(String.valueOf(tbServicios.getValueAt(fila, 2)));   
             BuscarTipoSustentacionServicios.setVisible(false);
        }  

// TODO add your handling code here:
    }//GEN-LAST:event_tbServiciosKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(txtServicio.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Seleccione un Servicio");
        }
        else if(txtCantidad.getText().equalsIgnoreCase("")||txtPrecio.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Ingrese Cantidad y/o Precio");
        }        else{
                dispose();
                mostrarServicioDetalle();
                cargarResumenCostoSGene();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        BuscarTipoSustentacionServicios.setVisible(true);
        tbServicios.getSelectionModel().setSelectionInterval(0, 0);
        tbServicios.requestFocus();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tbServicios.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Codigo","Nombre del Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            serviciosVarios obj=new serviciosVarios();
            consulta="exec COSTOS_COSTOS_SERVICIOS_VARIOS_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tbServicios.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbServicios.setRowSorter(elQueOrdena);
            this.tbServicios.setModel(m);
            formatoServiciosVarios();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void tbTipoSusteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTipoSusteKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbTipoSuste.getSelectedRow();
            Costos_Sustentacion_Detalle_Servicios_Generales.txtTipo.setText(String.valueOf(tbTipoSuste.getValueAt(fila, 1)));
            BuscarTipoSustentacion.setVisible(false);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tbTipoSusteKeyPressed

    private void btnBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarTipoActionPerformed

    private void txtPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyTyped
        // TODO add your handling code here:
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtPrecio.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtPrecioKeyTyped

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
        if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidad.getText().equalsIgnoreCase("")){
                        txtTotalSustentacion.setText("");
                        }
       else{
                        Double t=Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidad.getText());
                        BigDecimal total = new BigDecimal(t);
                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
                        txtTotalSustentacion.setText(String.valueOf(total));
       }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        // TODO add your handling code here:
        if(txtPrecio.getText().equalsIgnoreCase("") || txtCantidad.getText().equalsIgnoreCase("")){
                        txtTotalSustentacion.setText("");
                        }
       else{
                        Double t=Double.parseDouble(txtPrecio.getText())*Double.parseDouble(txtCantidad.getText());
                        BigDecimal total = new BigDecimal(t);
                        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
                        txtTotalSustentacion.setText(String.valueOf(total));
       }
    }//GEN-LAST:event_txtPrecioKeyReleased

    public void mostrarServicioDetalle(){
        
        try {
        String cod_tipo,cod_servicio,tipo_Sust,nom_servicio,cantidad,precio,total;
        
            cod_tipo=txtCodTipoSust.getText();
            cod_servicio=txtCodServicio.getText();
            tipo_Sust=txtTipo.getText();
            nom_servicio=txtServicio.getText();
            cantidad=txtCantidad.getText();
            precio=txtPrecio.getText();
            total=txtTotalSustentacion.getText();
         
            if(Costos_Sustentacion.tbServiciosGenerales.getRowCount()==0){
              m2=(DefaultTableModel) Costos_Sustentacion.tbServiciosGenerales.getModel();
           String filaelemento[]={cod_tipo,cod_servicio,tipo_Sust,nom_servicio,cantidad,precio,total};
               m2.addRow(filaelemento);
            }
            else{
           if(repiteDetalleSGene()==true){
               JOptionPane.showMessageDialog(rootPane,"El Servicio General ya ha sido ingresado.");   
          }
           else{
               m2=(DefaultTableModel) Costos_Sustentacion.tbServiciosGenerales.getModel();
           String filaelemento[]={cod_tipo,cod_servicio,tipo_Sust,nom_servicio,cantidad,precio,total};
               m2.addRow(filaelemento);
            }
           }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public boolean repiteDetalleSGene(){
         
         boolean c=false;
         for (int i = 0; i < Costos_Sustentacion.tbServiciosGenerales.getRowCount(); i++){    
               if(Costos_Sustentacion.tbServiciosGenerales.getValueAt(i, 1).toString().equalsIgnoreCase(txtCodServicio.getText())){
                    c=true;
			}}
               return c;
     }
    
    
    
    public void cargarResumenCostoSGene(){
        try{
        
        double total=0;
        if(Costos_Sustentacion.tbServiciosGenerales.getRowCount()>0){
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            mresumen=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(mresumen);

                for (int i = 0; i < Costos_Sustentacion.tbServiciosGenerales.getRowCount(); i++){    
                    total=total+Double.parseDouble(Costos_Sustentacion.tbServiciosGenerales.getValueAt(i, 6).toString());
                }
                BigDecimal totalg = new BigDecimal(total);
                       totalg = totalg.setScale(2, BigDecimal.ROUND_HALF_UP);
                String fila[]=new String[2];
            for (int i=0; i<8; i++){
                if(i==0){
                fila[0]="RECURSO HUMANO";
                fila[1]=Costos_Sustentacion.txtTotalPersonal.getText();
                }
                else if(i==1){
                fila[0]="INSUMOS";
                fila[1]=Costos_Sustentacion.txtTotalInsumos.getText();
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
                fila[1]=String.valueOf(totalg);
                } 
                else if(i==7){
                fila[0]="GASTOS INTERMEDIOS";
                fila[1]=Costos_Sustentacion.txtTotalSIntermedios.getText();
                } 
                 mresumen.addRow(fila);
                }
                
                Costos_Sustentacion.tbResumenCostos.setModel( mresumen);
            TableRowSorter<TableModel> elQueOrdena2=new TableRowSorter<TableModel>( mresumen);
            tbResumenCostos.setRowSorter(elQueOrdena2);
            tbResumenCostos.getColumnModel().getColumn(0).setPreferredWidth(200);
            tbResumenCostos.getColumnModel().getColumn(1).setPreferredWidth(120);
    
            Costos_Sustentacion.txtTotalSGenerales.setText(String.valueOf(totalg));
            //Total
            Double insumo,he,sb,inf,sa,pe,si;
            pe=Double.parseDouble(Costos_Sustentacion.txtTotalPersonal.getText());
            he=Double.parseDouble(Costos_Sustentacion.txtTotalHerramienta.getText());
            insumo=Double.parseDouble(Costos_Sustentacion.txtTotalInsumos.getText());
            sb=Double.parseDouble(Costos_Sustentacion.txtTotalSBasicos.getText());
            inf=Double.parseDouble(Costos_Sustentacion.txtTotalInfraes.getText());
            sa=Double.parseDouble(Costos_Sustentacion.txtTotalSAdminis.getText());
            si=Double.parseDouble(Costos_Sustentacion.txtTotalSIntermedios.getText());
            Double precioTotal=total+he+insumo+sb+inf+pe+sa+si;
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
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicios_Generales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicios_Generales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicios_Generales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Servicios_Generales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Costos_Sustentacion_Detalle_Servicios_Generales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BuscarServicios;
    private javax.swing.JDialog BuscarTipoSustentacion;
    private javax.swing.JDialog BuscarTipoSustentacionServicios;
    private javax.swing.JButton btnBuscarServicio;
    public static javax.swing.JButton btnBuscarTipo;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblhPersonal;
    private javax.swing.JTable tbServicios;
    private javax.swing.JTable tbTipoSuste;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtCodServicio;
    public static javax.swing.JTextField txtCodTipoSust;
    private javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtServicio;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTipoSusten;
    private javax.swing.JTextField txtTotalSustentacion;
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
