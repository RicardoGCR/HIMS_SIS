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
public class Costos_Sustentacion_Detalle_Infraestructura extends javax.swing.JFrame implements Runnable{
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
    public Costos_Sustentacion_Detalle_Infraestructura() {
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
        
         txtHora.setText(Costos_Sustentacion.spHora.getValue().toString());
        txtMin.setText(Costos_Sustentacion.spMin.getValue().toString());
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblhPersonal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtAnios = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTiempodeVidaUtil = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMesesxAnio = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDiasLaV = new javax.swing.JTextField();
        txtDiasSabado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtHorasSabado = new javax.swing.JTextField();
        txtHorasLaV = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtMinxHora = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtDiasDomingo = new javax.swing.JTextField();
        txtHorasDomingo = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtCodTipoSust = new javax.swing.JTextField();
        txtCodServicio = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtValorUnitarioDepreciado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtCostoDeConstruccion = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtDepreciacionInfr = new javax.swing.JTextField();
        txtRequerimientodeArea = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtCostoTotal = new javax.swing.JTextField();
        txtAreaTotal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtMin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        txtDepreciacionInfr1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtCostoEstandar = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();

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
        setTitle("SISGESH .::. Infraestructura");
        setMinimumSize(new java.awt.Dimension(984, 680));
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
        jLabel1.setText("INFRAESTRUCTURA");

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
                .addGap(242, 242, 242)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
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

        txtTipo.setEditable(false);
        txtTipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTipo.setText("INFRAESTRUCTURA");
        txtTipo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel8.setText("Unidad Productora de Servicios(UPS):");

        txtServicio.setEditable(false);
        txtServicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

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
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel3KeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel9.setText("Años:");

        txtAnios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAniosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAniosKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel11.setText("Días por Mes");

        txtTiempodeVidaUtil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTiempodeVidaUtil.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel10.setText("Meses por Año:");

        txtMesesxAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesesxAnioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMesesxAnioKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel12.setText("Tiempo de vida útil y rendimiento de Infraestructura");

        jLabel13.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel13.setText("Lunes a Viernes:");

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel14.setText("Sábado:");

        txtDiasLaV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasLaVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasLaVKeyTyped(evt);
            }
        });

        txtDiasSabado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasSabadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasSabadoKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel16.setText("Horas por Día");

        txtHorasSabado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasSabadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHorasSabadoKeyTyped(evt);
            }
        });

        txtHorasLaV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasLaVKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHorasLaVKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel20.setText("Minutos por hora:");

        txtMinxHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinxHoraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinxHoraKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel21.setText("Tiempo de Vida Útil");

        jLabel34.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel34.setText("Domingo:");

        txtDiasDomingo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasDomingoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasDomingoKeyTyped(evt);
            }
        });

        txtHorasDomingo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHorasDomingoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHorasDomingoKeyTyped(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel36.setText("en minutos(h):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(284, 284, 284))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMesesxAnio, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(txtAnios))
                                .addGap(120, 120, 120)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)))
                            .addComponent(jLabel34))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDiasDomingo)
                            .addComponent(txtDiasSabado, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiasLaV, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtHorasLaV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtHorasDomingo)
                                .addComponent(txtHorasSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMinxHora, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTiempodeVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel11))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtMesesxAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiasLaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDiasSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiasDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtHorasLaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHorasSabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHorasDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtMinxHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTiempodeVidaUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)))))
                .addGap(0, 11, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel19.setText("Requerimiento mínimo de ");

        jLabel23.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel23.setText("Área de construcción");

        txtValorUnitarioDepreciado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValorUnitarioDepreciado.setEnabled(false);
        txtValorUnitarioDepreciado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorUnitarioDepreciadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorUnitarioDepreciadoKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel24.setText("Costo Promedio por minuto de Infraestructura");

        jLabel25.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel25.setText("por m ");

        jLabel22.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel22.setText("Valor Unitario Depreciado");

        jLabel26.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel26.setText("anual:");

        jLabel27.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
        jLabel27.setText("2");

        jLabel28.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel28.setText("Costo de construcción");

        txtCostoDeConstruccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCostoDeConstruccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoDeConstruccion.setEnabled(false);
        txtCostoDeConstruccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoDeConstruccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoDeConstruccionKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel29.setText("Depreciación de Infraestructura");

        txtDepreciacionInfr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDepreciacionInfr.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDepreciacionInfr.setEnabled(false);
        txtDepreciacionInfr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepreciacionInfrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepreciacionInfrKeyTyped(evt);
            }
        });

        txtRequerimientodeArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRequerimientodeAreaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRequerimientodeAreaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRequerimientodeAreaKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel30.setText("de la UPS expresada en minutos:");

        jLabel31.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel31.setText("de UPS (S/.):");

        jLabel32.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
        jLabel32.setText("2");

        jLabel33.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel33.setText(" m ");

        jLabel35.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel35.setText("Área de construcción");

        jLabel38.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel38.setText("total m ");

        jLabel39.setFont(new java.awt.Font("Palatino Linotype", 1, 10)); // NOI18N
        jLabel39.setText("2");

        jLabel40.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel40.setText("Costo de construcción");

        jLabel41.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel41.setText("total (S/.):");

        txtCostoTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCostoTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoTotalKeyTyped(evt);
            }
        });

        txtAreaTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAreaTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAreaTotalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jLabel24)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAreaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel35))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel38)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel39)))
                .addGap(73, 73, 73)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoDeConstruccion))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(txtRequerimientodeArea, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel33)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel32)))
                                .addGap(72, 72, 72))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(txtDepreciacionInfr, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26))
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtValorUnitarioDepreciado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel23)))
                            .addComponent(jLabel19))
                        .addGap(1, 1, 1)
                        .addComponent(txtRequerimientodeArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel35))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel39)))
                            .addComponent(jLabel41))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAreaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel31)
                        .addGap(0, 0, 0)
                        .addComponent(txtCostoDeConstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel30)
                        .addGap(0, 0, 0)
                        .addComponent(txtDepreciacionInfr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26))
                    .addComponent(jLabel27))
                .addGap(0, 0, 0)
                .addComponent(txtValorUnitarioDepreciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel49.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel49.setText("Costo Estándar por minuto de la Infraestructura de la UPS");

        jLabel50.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel50.setText("Tiempo");

        txtHora.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtHora.setEnabled(false);
        txtHora.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtHoraPropertyChange(evt);
            }
        });
        txtHora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHoraKeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setText("h");

        txtMin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMin.setEnabled(false);
        txtMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinActionPerformed(evt);
            }
        });
        txtMin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtMinPropertyChange(evt);
            }
        });
        txtMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMinKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMinKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("min");

        jLabel52.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel52.setText("de la UPS expresada en minutos:");

        jLabel53.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel53.setText("Depreciación de Infraestructura");

        txtDepreciacionInfr1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDepreciacionInfr1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDepreciacionInfr1.setEnabled(false);
        txtDepreciacionInfr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepreciacionInfr1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepreciacionInfr1KeyTyped(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Palatino Linotype", 1, 13)); // NOI18N
        jLabel54.setText("Costo estándar(S/.)");

        txtCostoEstandar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCostoEstandar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCostoEstandar.setEnabled(false);
        txtCostoEstandar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoEstandarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoEstandarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDepreciacionInfr1)
                            .addComponent(jLabel52))
                        .addGap(80, 80, 80)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addGap(141, 141, 141)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(89, 89, 89)
                                .addComponent(txtCostoEstandar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(150, 150, 150))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(275, 275, 275))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(jLabel54))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel51))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtCostoEstandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDepreciacionInfr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
        jLabel17.setText("Salir(Esc)");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
        jLabel18.setText("Aceptar(Alt+A)");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/cancelar16x16.png"))); // NOI18N
        jLabel37.setText("Cancelar(Alt+C)");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(21, 21, 21)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(txtCodTipoSust, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(txtCodServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(119, 119, 119)
                .addComponent(jButton6)
                .addGap(293, 293, 293))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(jButton6))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getAccessibleContext().setAccessibleName("INFRAESTRUCTURA");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void tbServiciosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbServiciosKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbServicios.getSelectedRow();
             Costos_Sustentacion_Detalle_Infraestructura.txtCodServicio.setText(String.valueOf(tbServicios.getValueAt(fila, 1))); 
             Costos_Sustentacion_Detalle_Infraestructura.txtServicio.setText(String.valueOf(tbServicios.getValueAt(fila, 2)));   
             BuscarTipoSustentacionServicios.setVisible(false);
        }  

// TODO add your handling code here:
    }//GEN-LAST:event_tbServiciosKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if(txtServicio.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Seleccione un Servicio");
        }
       
        else if(txtCostoEstandar.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane,"Debe llenar todos los campos");
        }
             else{
                dispose();
                mostrarInfraestructuraDetalle();
                cargarResumenCostoInfr();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            Costos_Sustentacion_Detalle_Infraestructura.txtTipo.setText(String.valueOf(tbTipoSuste.getValueAt(fila, 1)));
            BuscarTipoSustentacion.setVisible(false);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tbTipoSusteKeyPressed

    private void btnBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarTipoActionPerformed

    private void txtMesesxAnioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesesxAnioKeyTyped
      char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtMesesxAnioKeyTyped

    private void txtAniosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAniosKeyTyped
        // TODO add your handling code here:
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtAniosKeyTyped

    private void txtAniosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAniosKeyReleased
     tiempo();
     costo_estandar();
    }//GEN-LAST:event_txtAniosKeyReleased

    private void txtMesesxAnioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesesxAnioKeyReleased
        tiempo();
          costo_estandar();
    }//GEN-LAST:event_txtMesesxAnioKeyReleased

    private void txtDiasLaVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasLaVKeyReleased
           tiempo();
             costo_estandar();
    }//GEN-LAST:event_txtDiasLaVKeyReleased

    private void txtDiasLaVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasLaVKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDiasLaVKeyTyped

    private void txtDiasSabadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasSabadoKeyReleased
              tiempo();
                costo_estandar();
    }//GEN-LAST:event_txtDiasSabadoKeyReleased

    private void txtDiasSabadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasSabadoKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDiasSabadoKeyTyped

    private void txtHorasSabadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasSabadoKeyReleased
     tiempo();
       costo_estandar();
    }//GEN-LAST:event_txtHorasSabadoKeyReleased

    private void txtHorasSabadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasSabadoKeyTyped
     char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtHorasSabadoKeyTyped

    private void txtHorasLaVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasLaVKeyReleased
  tiempo();
    costo_estandar();
    }//GEN-LAST:event_txtHorasLaVKeyReleased

    private void txtHorasLaVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasLaVKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtHorasLaVKeyTyped

    private void txtMinxHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinxHoraKeyReleased
 tiempo();
   costo_estandar();
    }//GEN-LAST:event_txtMinxHoraKeyReleased

    private void txtMinxHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinxHoraKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtMinxHoraKeyTyped

    private void txtValorUnitarioDepreciadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioDepreciadoKeyReleased
   
    }//GEN-LAST:event_txtValorUnitarioDepreciadoKeyReleased

    private void txtValorUnitarioDepreciadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorUnitarioDepreciadoKeyTyped
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtValorUnitarioDepreciado.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtValorUnitarioDepreciadoKeyTyped

    private void txtCostoDeConstruccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoDeConstruccionKeyReleased
      costo_estandar();
    }//GEN-LAST:event_txtCostoDeConstruccionKeyReleased

    private void txtCostoDeConstruccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoDeConstruccionKeyTyped
                char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtCostoDeConstruccion.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtCostoDeConstruccionKeyTyped

    private void txtDepreciacionInfrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepreciacionInfrKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepreciacionInfrKeyReleased

    private void txtDepreciacionInfrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepreciacionInfrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepreciacionInfrKeyTyped

    private void txtRequerimientodeAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRequerimientodeAreaKeyReleased
  costo_estandar();
    }//GEN-LAST:event_txtRequerimientodeAreaKeyReleased

    private void txtRequerimientodeAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRequerimientodeAreaKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtRequerimientodeAreaKeyTyped

    private void txtHoraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtHoraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraPropertyChange

    private void txtHoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraKeyReleased

    private void txtHoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraKeyTyped

    private void txtMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinActionPerformed

    private void txtMinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtMinPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinPropertyChange

    private void txtMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinKeyReleased

    private void txtMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinKeyTyped

    private void txtDepreciacionInfr1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepreciacionInfr1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepreciacionInfr1KeyReleased

    private void txtDepreciacionInfr1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepreciacionInfr1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepreciacionInfr1KeyTyped

    private void txtCostoEstandarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoEstandarKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoEstandarKeyReleased

    private void txtCostoEstandarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoEstandarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoEstandarKeyTyped

    private void txtDiasDomingoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasDomingoKeyReleased
      tiempo();
        costo_estandar();
    }//GEN-LAST:event_txtDiasDomingoKeyReleased

    private void txtDiasDomingoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasDomingoKeyTyped
             char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtDiasDomingoKeyTyped

    private void txtHorasDomingoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasDomingoKeyReleased
      tiempo();
      costo_estandar();
    }//GEN-LAST:event_txtHorasDomingoKeyReleased

    private void txtHorasDomingoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHorasDomingoKeyTyped
             char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtHorasDomingoKeyTyped

    private void jPanel3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3KeyReleased

    private void txtRequerimientodeAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRequerimientodeAreaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRequerimientodeAreaKeyPressed

    private void txtCostoTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTotalKeyReleased
        costo_estandar();
    }//GEN-LAST:event_txtCostoTotalKeyReleased

    private void txtCostoTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoTotalKeyTyped
                char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtCostoDeConstruccion.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtCostoTotalKeyTyped

    private void txtAreaTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaTotalKeyPressed

    private void txtAreaTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaTotalKeyReleased
        costo_estandar();
    }//GEN-LAST:event_txtAreaTotalKeyReleased

    private void txtAreaTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaTotalKeyTyped
              char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtCostoDeConstruccion.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtAreaTotalKeyTyped

    public void mostrarInfraestructuraDetalle(){
        
        try {
        String cod_tipo,cod_servicio,tipo_Sust,nom_servicio,area_total,costo_total,requ,
                valor_uni,costo_cons,tiempo,depreciacion,tiempoH,tiempoM,costo_estandar;
        
            cod_tipo=txtCodTipoSust.getText();
            cod_servicio=txtCodServicio.getText();
            tipo_Sust=txtTipo.getText();
            nom_servicio=txtServicio.getText();
            area_total=txtAreaTotal.getText();
            costo_total=txtCostoTotal.getText();
            requ=txtRequerimientodeArea.getText();
            valor_uni=txtValorUnitarioDepreciado.getText();
            costo_cons=txtCostoDeConstruccion.getText();
            tiempo=txtTiempodeVidaUtil.getText();
            depreciacion=txtDepreciacionInfr.getText();
            tiempoH=txtHora.getText();
            tiempoM=txtMin.getText();
            costo_estandar=txtCostoEstandar.getText();
         
            if(Costos_Sustentacion.tbInfraestructura.getRowCount()==0){
              m2=(DefaultTableModel) Costos_Sustentacion.tbInfraestructura.getModel();
           String filaelemento[]={cod_tipo,cod_servicio,tipo_Sust,nom_servicio,area_total,costo_total,
               requ,valor_uni,costo_cons,tiempo,depreciacion,tiempoH,tiempoM,costo_estandar};
               m2.addRow(filaelemento);
            }
            else{
           if(repiteDetalleInf()==true){
               JOptionPane.showMessageDialog(rootPane,"La Infraestructura ya ha sido ingresada.");   
          }
           else{
               m2=(DefaultTableModel) Costos_Sustentacion.tbInfraestructura.getModel();
           String filaelemento[]={cod_tipo,cod_servicio,tipo_Sust,nom_servicio,area_total,costo_total,
               requ,valor_uni,costo_cons,tiempo,depreciacion,tiempoH,tiempoM,costo_estandar};
           m2.addRow(filaelemento);
            }
           }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public boolean repiteDetalleInf(){
         
         boolean c=false;
         for (int i = 0; i < Costos_Sustentacion.tbInfraestructura.getRowCount(); i++){    
               if(Costos_Sustentacion.tbInfraestructura.getValueAt(i, 1).toString().equalsIgnoreCase(txtCodServicio.getText())){
                    c=true;
			}}
               return c;
     }
    
    
    
    public void cargarResumenCostoInfr(){
        try{
        
        double total=0;
        if(Costos_Sustentacion.tbInfraestructura.getRowCount()>0){
            tbResumenCostos.setModel(new DefaultTableModel());
            String titulos2[]={"Tipo Sustentación","Valor Total"};
            mresumen=new DefaultTableModel(null,titulos2);
            JTable p2=new JTable(mresumen);

                for (int i = 0; i < Costos_Sustentacion.tbInfraestructura.getRowCount(); i++){    
                    total=total+Double.parseDouble(Costos_Sustentacion.tbInfraestructura.getValueAt(i, 13).toString());
                }
                BigDecimal totalinf = new BigDecimal(total);
                       totalinf = totalinf.setScale(2, BigDecimal.ROUND_HALF_UP);
                        Costos_Sustentacion.txtTotalInfraes.setText(String.valueOf(totalinf));
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
                fila[1]=Costos_Sustentacion.txtTotalSGenerales.getText();
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
    
           
            //Total
            Double insumo,he,sb,sa,sg,pe,si;
            pe=Double.parseDouble(Costos_Sustentacion.txtTotalPersonal.getText());
            he=Double.parseDouble(Costos_Sustentacion.txtTotalHerramienta.getText());
            insumo=Double.parseDouble(Costos_Sustentacion.txtTotalInsumos.getText());
            sb=Double.parseDouble(Costos_Sustentacion.txtTotalSBasicos.getText());
            sa=Double.parseDouble(Costos_Sustentacion.txtTotalSAdminis.getText());
            sg=Double.parseDouble(Costos_Sustentacion.txtTotalSGenerales.getText());
            si=Double.parseDouble(Costos_Sustentacion.txtTotalSIntermedios.getText());
            Double precioTotal=total+he+insumo+sb+sa+pe+sg+si;
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
    public void tiempo(){
        if(txtAnios.getText().equalsIgnoreCase("") || txtMesesxAnio.getText().equalsIgnoreCase("") ||
                txtDiasLaV.getText().equalsIgnoreCase("") ||  txtDiasSabado.getText().equalsIgnoreCase("")||
                txtDiasDomingo.getText().equalsIgnoreCase("") || txtHorasLaV.getText().equalsIgnoreCase("") ||  
                txtHorasSabado.getText().equalsIgnoreCase("")|| txtHorasDomingo.getText().equalsIgnoreCase("")||
                txtMinxHora.getText().equalsIgnoreCase("")){
                        txtTiempodeVidaUtil.setText("");
                        }
        else{
                    Double t=Double.parseDouble(txtAnios.getText())*Double.parseDouble(txtMesesxAnio.getText())*
                    (Double.parseDouble(txtDiasLaV.getText())*Double.parseDouble(txtHorasLaV.getText())+
                    Double.parseDouble(txtDiasSabado.getText())*Double.parseDouble(txtHorasSabado.getText())+
                    Double.parseDouble(txtDiasDomingo.getText())*Double.parseDouble(txtHorasDomingo.getText()))*
                    Double.parseDouble(txtMinxHora.getText());
                        BigDecimal total = new BigDecimal(t);
            total = total.setScale(0, BigDecimal.ROUND_HALF_UP);
                        txtTiempodeVidaUtil.setText(String.valueOf(total));
       }
    }
    public void costo_estandar(){
         if(txtAreaTotal.getText().equalsIgnoreCase("") ||txtCostoTotal.getText().equalsIgnoreCase("") 
           ||txtRequerimientodeArea.getText().equalsIgnoreCase("")     ||txtTiempodeVidaUtil.getText().equalsIgnoreCase("") ){
              txtCostoDeConstruccion.setText("");
             txtValorUnitarioDepreciado.setText("");
        txtDepreciacionInfr.setText("");
           txtDepreciacionInfr1.setText("");
           txtCostoEstandar.setText("");
                        }
       else{
             Double t=Double.parseDouble(txtCostoTotal.getText())/Double.parseDouble(txtAreaTotal.getText());
            BigDecimal total = new BigDecimal(t);
            total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtValorUnitarioDepreciado.setText(String.valueOf(total));
            
             Double t0=Double.parseDouble(txtCostoTotal.getText())/Double.parseDouble(txtAreaTotal.getText())
                     *Double.parseDouble(txtRequerimientodeArea.getText());
            BigDecimal total0 = new BigDecimal(t0);
            total0 = total0.setScale(2, BigDecimal.ROUND_HALF_UP);
            txtCostoDeConstruccion.setText(String.valueOf(total0));
            
            
             Double t1=Double.parseDouble(txtCostoDeConstruccion.getText())/Double.parseDouble(txtTiempodeVidaUtil.getText());
            BigDecimal total1 = new BigDecimal(t1);
            total1 = total1.setScale(4, BigDecimal.ROUND_HALF_UP);
             txtDepreciacionInfr.setText(String.valueOf(total1));
              txtDepreciacionInfr1.setText(String.valueOf(total1));
              
              int tiempo=Integer.parseInt(txtHora.getText())*60+Integer.parseInt(txtMin.getText());
             Double c=Double.parseDouble(txtDepreciacionInfr.getText())*tiempo;
            BigDecimal costo1 = new BigDecimal(c);
            costo1 = costo1.setScale(4, BigDecimal.ROUND_HALF_UP);
             txtCostoEstandar.setText(String.valueOf(costo1));
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
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Infraestructura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Infraestructura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Infraestructura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Costos_Sustentacion_Detalle_Infraestructura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Costos_Sustentacion_Detalle_Infraestructura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BuscarServicios;
    private javax.swing.JDialog BuscarTipoSustentacion;
    private javax.swing.JDialog BuscarTipoSustentacionServicios;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscarServicio;
    public static javax.swing.JButton btnBuscarTipo;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblhPersonal;
    private javax.swing.JTable tbServicios;
    private javax.swing.JTable tbTipoSuste;
    private javax.swing.JTextField txtAnios;
    private javax.swing.JTextField txtAreaTotal;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCodServicio;
    public static javax.swing.JTextField txtCodTipoSust;
    private javax.swing.JTextField txtCostoDeConstruccion;
    private javax.swing.JTextField txtCostoEstandar;
    private javax.swing.JTextField txtCostoTotal;
    private javax.swing.JTextField txtDepreciacionInfr;
    private javax.swing.JTextField txtDepreciacionInfr1;
    private javax.swing.JTextField txtDiasDomingo;
    private javax.swing.JTextField txtDiasLaV;
    private javax.swing.JTextField txtDiasSabado;
    public static javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtHorasDomingo;
    private javax.swing.JTextField txtHorasLaV;
    private javax.swing.JTextField txtHorasSabado;
    private javax.swing.JTextField txtMesesxAnio;
    public static javax.swing.JTextField txtMin;
    private javax.swing.JTextField txtMinxHora;
    private javax.swing.JTextField txtRequerimientodeArea;
    public static javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTiempodeVidaUtil;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTipoSusten;
    private javax.swing.JTextField txtValorUnitarioDepreciado;
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
