
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.LABORATORIO.Celda_Checkox;
import modelos.LABORATORIO.FormatoTabla;
import modelos.LABORATORIO.FormatoTablaResultados;
import modelos.LABORATORIO.LAB_Resultado_Insumos;
import modelos.LABORATORIO.LAB_Resultado_Muestra_Cabecera;
import modelos.LABORATORIO.LAB_Resultado_Muestra_Detalle;
import modelos.LABORATORIO.LAB_Toma_Muestra_Cabecera;
import modelos.LABORATORIO.LAB_Toma_Muestra_Detalle;
import modelos.LABORATORIO.LAB_Toma_Muestra_Subdetalle;
import modelos.LABORATORIO.LAB_Valores_Referenciales;
import modelos.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import servicios.Conexion;
import static vista.LABORATORIO.frm_LAB_BUSCAR_RESULTADO.tb_TomasRealizadas;
import static vista.LABORATORIO.frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos;
import static vista.LABORATORIO.frm_LAB_TOMA_MUESTRA_DETALLE.tb_Muestras;

/**
 *
 * @author
 */
public class frm_LAB_RESULTADO_MUESTRA extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_RESULTADO_MUESTRA() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
//         this.setExtendedState(MAXIMIZED_BOTH);
   
        h1 = new Thread(this);
        h1.start();
        
        panelOcultar.setBackground(Color.white);
        this.getContentPane().setBackground(Color.white); 

        personal.getContentPane().setBackground(Color.white);
        personal.setLocationRelativeTo(null);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        formato();
        //ocultar
        panelOcultar.setVisible(false);
        //fecha
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        
        lblFecha1.setText(fechaActual());
        
        lbltipo.setVisible(false);
        
        tb_Detalle.setDefaultRenderer(Object.class,new FormatoTablaResultados());
  
   //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();
                frm_LAB_RESULTADOS_MUESTRA_INGRESO rm=new frm_LAB_RESULTADOS_MUESTRA_INGRESO ();
                rm.setVisible(true);
            } });
    }
    
    public void formato(){
    
    tb_Detalle.getColumnModel().getColumn(1).setPreferredWidth(160);
    tb_Detalle.getColumnModel().getColumn(5).setPreferredWidth(190);
    tb_Detalle.getColumnModel().getColumn(6).setPreferredWidth(160);
    
    tb_Detalle.getColumnModel().getColumn(16).setPreferredWidth(105);
    tb_Detalle.getColumnModel().getColumn(17).setPreferredWidth(105);
    tb_Detalle.getColumnModel().getColumn(18).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(20).setPreferredWidth(220);
            //Ocultar    
  tb_Detalle.getColumnModel().getColumn(0).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(0).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(2).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(2).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(3).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(3).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(4).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(4).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(7).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(7).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(8).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(8).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(9).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(9).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(10).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(10).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(11).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(11).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(12).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(12).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(13).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(13).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(14).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(14).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(15).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(15).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(19).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(19).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(21).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(21).setMaxWidth(0);
  
//    tb_Detalle.getSelectionModel().setSelectionInterval(0, 0);
//            tb_Detalle.requestFocus();
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
   public static String fechaActual(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        return date.format(now);
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personal = new javax.swing.JDialog();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPersonal = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            cbxBuscar2 = new javax.swing.JComboBox();
            jpanel2 = new javax.swing.JPanel();
            titulo7 = new javax.swing.JLabel();
            lbltipo = new javax.swing.JLabel();
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tb_Detalle = new javax.swing.JTable();
            panelPaciente = new javax.swing.JPanel();
            jLabel23 = new javax.swing.JLabel();
            txtDni = new javax.swing.JTextField();
            jLabel22 = new javax.swing.JLabel();
            txtHc = new javax.swing.JTextField();
            txtPacientes = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            txtFecha = new javax.swing.JTextField();
            jLabel18 = new javax.swing.JLabel();
            txtSexo = new javax.swing.JTextField();
            jLabel24 = new javax.swing.JLabel();
            txtEdad = new javax.swing.JTextField();
            btnGenerar = new javax.swing.JButton();
            jButton1 = new javax.swing.JButton();
            panelResultado = new javax.swing.JPanel();
            jLabel40 = new javax.swing.JLabel();
            txtPersonalResponR = new javax.swing.JTextField();
            txtPersonalRegistraR = new javax.swing.JTextField();
            jLabel43 = new javax.swing.JLabel();
            jLabel44 = new javax.swing.JLabel();
            jLabel45 = new javax.swing.JLabel();
            lblFecha1 = new javax.swing.JLabel();
            lblHora1 = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            txtResult_PN = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            panelOcultar = new javax.swing.JPanel();
            txtCodigo = new javax.swing.JTextField();
            txtNum = new javax.swing.JTextField();
            lblServicio = new javax.swing.JLabel();
            lblArea = new javax.swing.JLabel();
            lblcod_cab_toma = new javax.swing.JLabel();
            lblCodPerResponsaR = new javax.swing.JLabel();
            lblCodPerRegistraR = new javax.swing.JLabel();
            lblcod_det_toma = new javax.swing.JLabel();
            lblcod_exa_ana = new javax.swing.JLabel();
            lblHc = new javax.swing.JLabel();
            lblid_cod_doc_det = new javax.swing.JLabel();
            tb_valores = new javax.swing.JScrollPane();
            tb_Valores = new javax.swing.JTable();
            jScrollPane1 = new javax.swing.JScrollPane();
            tb_Esquema = new javax.swing.JTable();
            lblCantidadInsumos = new javax.swing.JLabel();
            tb_valores1 = new javax.swing.JScrollPane();
            tb_InsumosFinal = new javax.swing.JTable();
            panelTM = new javax.swing.JPanel();
            jLabel19 = new javax.swing.JLabel();
            txtActoMedico = new javax.swing.JTextField();
            txtFormaPago = new javax.swing.JTextField();
            jLabel30 = new javax.swing.JLabel();
            jLabel29 = new javax.swing.JLabel();
            txtNToma = new javax.swing.JTextField();
            jLabel25 = new javax.swing.JLabel();
            txtPersonalTomaMuestra = new javax.swing.JTextField();
            jLabel27 = new javax.swing.JLabel();
            txtFechaTM = new javax.swing.JTextField();
            jLabel28 = new javax.swing.JLabel();
            txtHoraTM = new javax.swing.JTextField();
            jLabel26 = new javax.swing.JLabel();
            txtPersonalSolicita = new javax.swing.JTextField();
            jLabel32 = new javax.swing.JLabel();
            txtPiso = new javax.swing.JTextField();
            jLabel33 = new javax.swing.JLabel();
            txtCama = new javax.swing.JTextField();
            jLabel31 = new javax.swing.JLabel();
            jLabel37 = new javax.swing.JLabel();
            txtFechaOrden = new javax.swing.JTextField();
            txtHoraOrden = new javax.swing.JTextField();
            txthospiServ = new javax.swing.JTextField();
            jLabel36 = new javax.swing.JLabel();
            panelAnalisis = new javax.swing.JPanel();
            txtNomenclatura = new javax.swing.JTextField();
            jLabel35 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            txtCPT = new javax.swing.JTextField();
            jLabel39 = new javax.swing.JLabel();
            txtServArea = new javax.swing.JTextField();
            btnInsumos = new javax.swing.JButton();

            personal.setAlwaysOnTop(true);
            personal.setMinimumSize(new java.awt.Dimension(852, 504));
            personal.setResizable(false);

            txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
            txtBuscar.setEnabled(false);
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

            jLabel2.setText("Búsqueda por:");

            btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
            btnBuscar.setBorder(null);
            btnBuscar.setEnabled(false);
            btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarActionPerformed(evt);
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
            jScrollPane2.setViewportView(tbPersonal);

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

            lbltipo.setText("jLabel1");

            javax.swing.GroupLayout personalLayout = new javax.swing.GroupLayout(personal.getContentPane());
            personal.getContentPane().setLayout(personalLayout);
            personalLayout.setHorizontalGroup(
                personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(personalLayout.createSequentialGroup()
                    .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(personalLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalLayout.createSequentialGroup()
                            .addGap(97, 97, 97)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbxBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(158, 158, 158)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addComponent(lbltipo))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setMinimumSize(null);

            jpanel.setBackground(new java.awt.Color(2, 67, 115));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            titulo5.setText("Resultado");
            titulo5.setToolTipText("");
            titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

            lblUsu.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblUsu.setText("Silvana");

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28))
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 23, Short.MAX_VALUE))
            );

            tb_Detalle.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            tb_Detalle.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "cod_esquema", "Análisis/Estudio", "tipo_esquema_sub_ana", "cod_uni_med_exa", "cod_valores_refe_resul", "Valor de Resultado", "Unidad de Medida", "estado_todos_fabricante", "cod_fabricante_producto", "ini_anio_resul", "ini_mes_resul", "ini_dia_resul", "fin_anio_resul", "fin_mes_resul", "fin_dia_resul", "genero", "Valor Mínimo", "Valor Máximo", "Valor Texto", "tipo_valor_refencia_resul", "Observación Resultado", "usu_valores_ref"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tb_Detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tb_Detalle.setRowHeight(26);
            tb_Detalle.setSelectionBackground(new java.awt.Color(2, 67, 115));
            tb_Detalle.getTableHeader().setReorderingAllowed(false);
            tb_Detalle.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_DetalleMouseClicked(evt);
                }
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    tb_DetalleMouseReleased(evt);
                }
            });
            tb_Detalle.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    tb_DetallePropertyChange(evt);
                }
            });
            tb_Detalle.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    tb_DetalleKeyTyped(evt);
                }
            });
            jScrollPane5.setViewportView(tb_Detalle);
            if (tb_Detalle.getColumnModel().getColumnCount() > 0) {
                tb_Detalle.getColumnModel().getColumn(0).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(2).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(3).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(7).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(8).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(9).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(10).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(11).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(12).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(13).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(14).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(15).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(19).setResizable(false);
                tb_Detalle.getColumnModel().getColumn(21).setResizable(false);
            }

            panelPaciente.setBackground(new java.awt.Color(204, 204, 204));
            panelPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Datos del Paciente ________________________________________", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 13), new java.awt.Color(51, 51, 51))); // NOI18N
            panelPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N

            jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel23.setText("DNI");

            txtDni.setEditable(false);
            txtDni.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel22.setText("Nº de H.C.");

            txtHc.setEditable(false);
            txtHc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtHc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPacientes.setEditable(false);
            txtPacientes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtPacientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPacientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Paciente");

            jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel17.setText("Fecha de Nac.     ");

            txtFecha.setEditable(false);
            txtFecha.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel18.setText("Sexo         ");

            txtSexo.setEditable(false);
            txtSexo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtSexo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSexo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel24.setText("Edad");

            txtEdad.setEditable(false);
            txtEdad.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtEdad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            javax.swing.GroupLayout panelPacienteLayout = new javax.swing.GroupLayout(panelPaciente);
            panelPaciente.setLayout(panelPacienteLayout);
            panelPacienteLayout.setHorizontalGroup(
                panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPacienteLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEdad)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelPacienteLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPacienteLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(103, 103, 103))))
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtDni)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addComponent(txtHc, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelPacienteLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPacientes)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPacienteLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            );
            panelPacienteLayout.setVerticalGroup(
                panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPacienteLayout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtHc, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(txtPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel23)
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(0, 0, 0)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            btnGenerar.setBackground(new java.awt.Color(100, 100, 100));
            btnGenerar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
            btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
            btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnGenerar.setMnemonic('G');
            btnGenerar.setText("Guardar");
            btnGenerar.setToolTipText("Guardar(Alt + G)");
            btnGenerar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGenerar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGenerarActionPerformed(evt);
                }
            });

            jButton1.setBackground(new java.awt.Color(100, 100, 100));
            jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
            jButton1.setForeground(new java.awt.Color(255, 255, 255));
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Deshacer-30.png"))); // NOI18N
            jButton1.setText("Regresar");
            jButton1.setToolTipText("Regresar(Esc)");
            jButton1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            panelResultado.setBackground(new java.awt.Color(255, 255, 255));
            panelResultado.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));

            jLabel40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel40.setText("Personal - Responsable Resultado");

            txtPersonalResponR.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
            txtPersonalResponR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalResponR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPersonalResponR.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPersonalResponRActionPerformed(evt);
                }
            });
            txtPersonalResponR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPersonalResponRKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtPersonalResponRKeyReleased(evt);
                }
            });

            txtPersonalRegistraR.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
            txtPersonalRegistraR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalRegistraR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPersonalRegistraR.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPersonalRegistraRActionPerformed(evt);
                }
            });
            txtPersonalRegistraR.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPersonalRegistraRKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtPersonalRegistraRKeyReleased(evt);
                }
            });

            jLabel43.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel43.setText("Personal - Registra Resultado");

            jLabel44.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel44.setText("Fecha Registro Resul.");

            jLabel45.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel45.setText("Hora Registro Resul.");

            lblFecha1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            lblFecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFecha1.setText("00/00/00");

            lblHora1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            lblHora1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblHora1.setText("00:00:00");

            jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("Resultado");

            txtResult_PN.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtResult_PN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtResult_PN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtResult_PN.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtResult_PNActionPerformed(evt);
                }
            });
            txtResult_PN.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtResult_PNKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtResult_PNKeyReleased(evt);
                }
            });

            jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 1, 13)); // NOI18N
            jLabel3.setText("Resultado__________________________________________________________________________________________________________________________");

            javax.swing.GroupLayout panelResultadoLayout = new javax.swing.GroupLayout(panelResultado);
            panelResultado.setLayout(panelResultadoLayout);
            panelResultadoLayout.setHorizontalGroup(
                panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultadoLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtResult_PN)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(lblHora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(84, 84, 84))
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPersonalResponR, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                            .addGap(43, 43, 43)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPersonalRegistraR)
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(panelResultadoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3)
                    .addGap(0, 20, Short.MAX_VALUE))
            );
            panelResultadoLayout.setVerticalGroup(
                panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultadoLayout.createSequentialGroup()
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultadoLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPersonalResponR, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPersonalRegistraR, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelResultadoLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel40))
                                .addComponent(jLabel3)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultadoLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel43)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel44)
                        .addComponent(jLabel1))
                    .addGap(0, 0, 0)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtResult_PN, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
            );

            lblServicio.setText("Servicio");

            lblArea.setText("area");

            lblcod_cab_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            lblcod_det_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblcod_det_toma.setText("cod_det_toma");

            lblcod_exa_ana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblcod_exa_ana.setText("cod_exa_ana");

            lblid_cod_doc_det.setText("lblid_cod_doc");

            tb_Valores.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "cod_caja", "codMuestra", "codContenedor", "codArea", "Muestra", "Contenedor", "Area", "Cantidad", "Codigo_Barras"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, true, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tb_Valores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tb_valores.setViewportView(tb_Valores);

            tb_Esquema.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "cod_caja", "codMuestra", "codContenedor", "codArea", "Muestra", "Contenedor", "Area", "Cantidad", "Codigo_Barras"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, true, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tb_Esquema.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            jScrollPane1.setViewportView(tb_Esquema);

            lblCantidadInsumos.setText("jLabel4");

            tb_InsumosFinal.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "cod_per_verifi", "nombre_verifica", "cod_per_confirma", "nombre_confirma", "cod_kar", "situacion", "motivo_perdida", "Cantidad_perdida", "cantidad_reasig", "cod_produc"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tb_InsumosFinal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            tb_valores1.setViewportView(tb_InsumosFinal);

            javax.swing.GroupLayout panelOcultarLayout = new javax.swing.GroupLayout(panelOcultar);
            panelOcultar.setLayout(panelOcultarLayout);
            panelOcultarLayout.setHorizontalGroup(
                panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOcultarLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(tb_valores, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblServicio)
                    .addGap(7, 7, 7)
                    .addComponent(lblArea)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblcod_exa_ana, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblCodPerResponsaR)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblCodPerRegistraR)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblcod_cab_toma)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblcod_det_toma, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblid_cod_doc_det, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblCantidadInsumos)
                    .addGap(111, 111, 111)
                    .addComponent(tb_valores1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(61, 61, 61))
            );
            panelOcultarLayout.setVerticalGroup(
                panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tb_valores, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcod_exa_ana)
                        .addComponent(lblcod_cab_toma)
                        .addComponent(lblHc)
                        .addComponent(lblServicio)
                        .addComponent(lblArea)
                        .addComponent(lblCodPerResponsaR)
                        .addComponent(lblCodPerRegistraR)
                        .addComponent(lblcod_det_toma)
                        .addComponent(lblid_cod_doc_det)
                        .addComponent(lblCantidadInsumos)))
                .addComponent(tb_valores1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            panelTM.setBackground(new java.awt.Color(204, 204, 204));
            panelTM.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Toma de Muestra_________________________________________________________________________________________________", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N
            panelTM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N

            jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel19.setText("Acto Médico");

            txtActoMedico.setEditable(false);
            txtActoMedico.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtActoMedico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtFormaPago.setEditable(false);
            txtFormaPago.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFormaPago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtFormaPago.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtFormaPagoActionPerformed(evt);
                }
            });
            txtFormaPago.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtFormaPagoKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtFormaPagoKeyReleased(evt);
                }
            });

            jLabel30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel30.setText("Forma de Pago");

            jLabel29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel29.setText("N° de Toma de Muestra");

            txtNToma.setEditable(false);
            txtNToma.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtNToma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNToma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtNToma.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNTomaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtNTomaKeyReleased(evt);
                }
            });

            jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel25.setText("Personal - Toma de Muestra");

            txtPersonalTomaMuestra.setEditable(false);
            txtPersonalTomaMuestra.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
            txtPersonalTomaMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalTomaMuestra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPersonalTomaMuestra.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPersonalTomaMuestraActionPerformed(evt);
                }
            });
            txtPersonalTomaMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPersonalTomaMuestraKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtPersonalTomaMuestraKeyReleased(evt);
                }
            });

            jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel27.setText("Fecha Toma Muestra");

            txtFechaTM.setEditable(false);
            txtFechaTM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtFechaTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFechaTM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtFechaTM.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtFechaTMActionPerformed(evt);
                }
            });
            txtFechaTM.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtFechaTMKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtFechaTMKeyReleased(evt);
                }
            });

            jLabel28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel28.setText("Hora Toma Muestra");

            txtHoraTM.setEditable(false);
            txtHoraTM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtHoraTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHoraTM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtHoraTM.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtHoraTMActionPerformed(evt);
                }
            });
            txtHoraTM.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtHoraTMKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtHoraTMKeyReleased(evt);
                }
            });

            jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setText("Personal - Solicita Muestra");

            txtPersonalSolicita.setEditable(false);
            txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
            txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalSolicita.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPersonalSolicita.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPersonalSolicitaActionPerformed(evt);
                }
            });
            txtPersonalSolicita.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPersonalSolicitaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtPersonalSolicitaKeyReleased(evt);
                }
            });

            jLabel32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel32.setText("Piso");

            txtPiso.setEditable(false);
            txtPiso.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtPiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPiso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtPiso.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtPisoActionPerformed(evt);
                }
            });
            txtPiso.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtPisoKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtPisoKeyReleased(evt);
                }
            });

            jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel33.setText("Cama");

            txtCama.setEditable(false);
            txtCama.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtCama.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCamaActionPerformed(evt);
                }
            });
            txtCama.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCamaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtCamaKeyReleased(evt);
                }
            });

            jLabel31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel31.setText("Fecha Orden");

            jLabel37.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel37.setText("Hora Orden");

            txtFechaOrden.setEditable(false);
            txtFechaOrden.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtFechaOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFechaOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtFechaOrden.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtFechaOrdenActionPerformed(evt);
                }
            });
            txtFechaOrden.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtFechaOrdenKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtFechaOrdenKeyReleased(evt);
                }
            });

            txtHoraOrden.setEditable(false);
            txtHoraOrden.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtHoraOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHoraOrden.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtHoraOrden.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtHoraOrdenActionPerformed(evt);
                }
            });
            txtHoraOrden.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtHoraOrdenKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtHoraOrdenKeyReleased(evt);
                }
            });

            txthospiServ.setEditable(false);
            txthospiServ.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txthospiServ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txthospiServ.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txthospiServ.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txthospiServActionPerformed(evt);
                }
            });
            txthospiServ.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txthospiServKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txthospiServKeyReleased(evt);
                }
            });

            jLabel36.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel36.setText("Hospitalización-Servicio");

            javax.swing.GroupLayout panelTMLayout = new javax.swing.GroupLayout(panelTM);
            panelTM.setLayout(panelTMLayout);
            panelTMLayout.setHorizontalGroup(
                panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTMLayout.createSequentialGroup()
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(19, 19, 19)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaTM, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtNToma, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(21, 21, 21)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtHoraOrden)
                        .addComponent(txtFormaPago, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtHoraTM))
                    .addGap(26, 26, 26)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPiso, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txthospiServ)
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(txtCama)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(13, 13, 13))
            );
            panelTMLayout.setVerticalGroup(
                panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTMLayout.createSequentialGroup()
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jLabel30)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNToma, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addComponent(jLabel36)
                            .addGap(0, 0, 0)
                            .addComponent(txthospiServ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28)
                                .addComponent(jLabel32))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtHoraTM, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                                .addComponent(txtFechaTM, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPiso))
                            .addGap(0, 0, 0)
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37)
                                .addComponent(jLabel33))
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHoraOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(3, 3, 3))
            );

            panelAnalisis.setBackground(new java.awt.Color(204, 204, 204));
            panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), "Análisis______________________________________________________", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 13), new java.awt.Color(51, 51, 51))); // NOI18N
            panelAnalisis.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N

            txtNomenclatura.setEditable(false);
            txtNomenclatura.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
            txtNomenclatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNomenclatura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtNomenclatura.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtNomenclaturaActionPerformed(evt);
                }
            });
            txtNomenclatura.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNomenclaturaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtNomenclaturaKeyReleased(evt);
                }
            });

            jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel35.setText("Análisis / Examen");

            jLabel34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel34.setText("Código CPT");

            txtCPT.setEditable(false);
            txtCPT.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            txtCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtCPT.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtCPTActionPerformed(evt);
                }
            });
            txtCPT.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtCPTKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtCPTKeyReleased(evt);
                }
            });

            jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
            jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel39.setText("Servicio/Área");

            txtServArea.setEditable(false);
            txtServArea.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
            txtServArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtServArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
            txtServArea.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtServAreaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtServAreaKeyReleased(evt);
                }
            });

            javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
            panelAnalisis.setLayout(panelAnalisisLayout);
            panelAnalisisLayout.setHorizontalGroup(
                panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAnalisisLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtServArea))
                    .addContainerGap(21, Short.MAX_VALUE))
            );
            panelAnalisisLayout.setVerticalGroup(
                panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAnalisisLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jLabel39)
                    .addGap(0, 0, 0)
                    .addComponent(txtServArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addGap(0, 0, 0)
                            .addComponent(txtCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAnalisisLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(21, 21, 21))))
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            btnInsumos.setBackground(new java.awt.Color(100, 100, 100));
            btnInsumos.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
            btnInsumos.setForeground(new java.awt.Color(255, 255, 255));
            btnInsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Tubo de ensayo-30.png"))); // NOI18N
            btnInsumos.setMnemonic('I');
            btnInsumos.setText("Insumos");
            btnInsumos.setToolTipText("Guardar(Alt + I)");
            btnInsumos.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnInsumos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnInsumos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnInsumosActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelOcultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnInsumos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                            .addGap(23, 23, 23))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 7, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnInsumos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 96, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void tb_DetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DetalleMouseClicked

    }//GEN-LAST:event_tb_DetalleMouseClicked

    private void tb_DetalleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DetalleMouseReleased
 
    }//GEN-LAST:event_tb_DetalleMouseReleased

    private void tb_DetallePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_DetallePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_DetallePropertyChange

    private void tb_DetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_DetalleKeyTyped

    }//GEN-LAST:event_tb_DetalleKeyTyped

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
ImageIcon alerta=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));
        
int filtro=0;
        try{
            if(lblCodPerResponsaR.getText().equalsIgnoreCase("")||txtPersonalResponR.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal Responsable del Resultado");
              filtro=1;
          }  else if(lblCodPerRegistraR.getText().equalsIgnoreCase("")||
                    txtPersonalRegistraR.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para el Registro del Resultado");
              filtro=1;
          }
          else if(tb_InsumosFinal.getRowCount()==0){
              JOptionPane.showMessageDialog(this, "Antes de Guardar el Resultado debe realizar\nla Sustentación de los Insumos",null, 1, alerta);
                filtro=1;
          }
           else {
              filtro=0;
              for(int j=0;j<tb_Detalle.getRowCount();j++){
              if(tb_Detalle.getValueAt(j, 5).toString().equalsIgnoreCase("")&&
                      tb_Detalle.getValueAt(j, 2).toString().equalsIgnoreCase("F")){

                  filtro=filtro+1;
              }}
              if(filtro>0){
              JOptionPane.showMessageDialog(rootPane, "Debe registrar todos los Resultados");  
              }
          }
            
              if(filtro==0){
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar ==0){
                  LAB_Resultado_Muestra_Cabecera meGuardar = new LAB_Resultado_Muestra_Cabecera();
                  
                LAB_Resultado_Muestra_Cabecera u=new LAB_Resultado_Muestra_Cabecera();
                txtCodigo.setText(u.LAB_Resultado_Muestra_Cab_generarid("1"));
                if(txtCodigo.getText().equalsIgnoreCase("")){
                txtCodigo.setText("RC000000000000000001");
                }
                LAB_Resultado_Muestra_Cabecera num=new LAB_Resultado_Muestra_Cabecera();
                txtNum.setText(num.LAB_Resultado_Muestra_Cab_generarid("2"));
                if(txtNum.getText().equalsIgnoreCase("")){
                txtNum.setText("00000000000000000001");
                }   
        
                  meGuardar.setCod_cab_resultado_mu_ana(txtCodigo.getText());
                  meGuardar.setNum_resul_exa(txtNum.getText());
                  meGuardar.setCod_det_toma_mu_ana(lblcod_det_toma.getText());
                  meGuardar.setCod_per_resultado(lblCodPerResponsaR.getText());
                  meGuardar.setNombre_personal_resultado(txtPersonalResponR.getText());
                  meGuardar.setCod_per_regis_resul(lblCodPerRegistraR.getText());
                  meGuardar.setNombre_personal_regis_resul(txtPersonalRegistraR.getText());
                  meGuardar.setResult_PN(txtResult_PN.getText());
                  meGuardar.setNom_usu(lblUsu.getText());
  
                  if(meGuardar.LAB_Resultado_Muestra_Cab_guardar()){
                      Lab_guardar_detalleySub();
                   Lab_guardar_Insumos();
                      JOptionPane.showMessageDialog(null, "Espere un momento, documento exportandose!", "Importante!", JOptionPane.INFORMATION_MESSAGE);

                      LAB_Resultado_Muestra_Cabecera mc=new LAB_Resultado_Muestra_Cabecera();
                      mc.LAB_Resultado_Caja_Estado(Integer.parseInt( lblid_cod_doc_det.getText()));
                      
                      //guardando automaticamente en la ruta D:\\LABORATORIO-RESULTADOS
                      exportar_pdf_automaticamente();
                      
                     JOptionPane.showMessageDialog(null, "Datos Guardados");
                     limpiar();
                     dispose();
                     frm_LAB_RESULTADOS_MUESTRA_INGRESO tmi=new  frm_LAB_RESULTADOS_MUESTRA_INGRESO();
                     tmi.setVisible(true);
                     }
                     else{
                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
                     }}}
                     }catch(Exception e) {
                     JOptionPane.showMessageDialog(this, e.getMessage());
                     }
    }//GEN-LAST:event_btnGenerarActionPerformed
        
    public void Lab_guardar_detalleySub(){
     for (int i = 0; i < tb_Detalle.getRowCount(); i++){      
               LAB_Resultado_Muestra_Detalle gDet=new LAB_Resultado_Muestra_Detalle();
               if(tb_Detalle.getValueAt(i,4).toString().equalsIgnoreCase("")){
//                   gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
               gDet.setCod_exa_ana(lblcod_exa_ana.getText());
               gDet.setCod_esquema_resul(tb_Detalle.getValueAt(i, 0).toString());
               gDet.setNombre_resultado_exa(tb_Detalle.getValueAt(i, 1).toString());
               gDet.setTipo_esquema_sub_ana(tb_Detalle.getValueAt(i, 2).toString());
               gDet.setCod_uni_med_exa(tb_Detalle.getValueAt(i, 3).toString());
               gDet.setCod_valores_refe_resul("");
               gDet.setValor_de_resultado_analisis(tb_Detalle.getValueAt(i, 5).toString());
               gDet.setEstado_todos_fabricante("");
               gDet.setCod_fabricante_producto("");
               gDet.setIni_anio_resul("");
               gDet.setIni_mes_resul("");
               gDet.setIni_dia_resul("");
               gDet.setFin_anio_resul("");
               gDet.setFin_mes_resul("");
               gDet.setFin_dia_resul("");
               gDet.setGenero("");
               gDet.setValor_minimo_resul(0.0);
               gDet.setValor_maximo_resul(0.0);
               gDet.setValor_texto_referencia_resul("");
               gDet.setTipo_valor_referencia_resul("");
               gDet.setObservaciones_resultado_exa(tb_Detalle.getValueAt(i, 20).toString());
               gDet.setUsa_valores_ref(tb_Detalle.getValueAt(i, 21).toString());
               gDet.setNom_usu(lblUsu.getText());
               
                gDet.LAB_Resultado_Muestra_Det_guardar();
               }else{
//               gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
               gDet.setCod_exa_ana(lblcod_exa_ana.getText());
               gDet.setCod_esquema_resul(tb_Detalle.getValueAt(i, 0).toString());
               gDet.setNombre_resultado_exa(tb_Detalle.getValueAt(i, 1).toString());
               gDet.setTipo_esquema_sub_ana(tb_Detalle.getValueAt(i, 2).toString());
               gDet.setCod_uni_med_exa(tb_Detalle.getValueAt(i, 3).toString());
               gDet.setCod_valores_refe_resul(tb_Detalle.getValueAt(i,4).toString());
               gDet.setValor_de_resultado_analisis(tb_Detalle.getValueAt(i, 5).toString());
               gDet.setEstado_todos_fabricante(tb_Detalle.getValueAt(i, 7).toString());
               gDet.setCod_fabricante_producto(tb_Detalle.getValueAt(i, 8).toString());
               gDet.setIni_anio_resul(tb_Detalle.getValueAt(i, 9).toString());
               gDet.setIni_mes_resul(tb_Detalle.getValueAt(i, 10).toString());
               gDet.setIni_dia_resul(tb_Detalle.getValueAt(i, 11).toString());
               gDet.setFin_anio_resul(tb_Detalle.getValueAt(i, 12).toString());
               gDet.setFin_mes_resul(tb_Detalle.getValueAt(i, 13).toString());
               gDet.setFin_dia_resul(tb_Detalle.getValueAt(i, 14).toString());
               gDet.setGenero(tb_Detalle.getValueAt(i, 15).toString());
               if(tb_Detalle.getValueAt(i,18).toString().equalsIgnoreCase("--")){
               gDet.setValor_minimo_resul(Double.parseDouble(tb_Detalle.getValueAt(i, 16).toString()));
               gDet.setValor_maximo_resul(Double.parseDouble(tb_Detalle.getValueAt(i,17).toString()));
               gDet.setValor_texto_referencia_resul("");
               }else{
               gDet.setValor_minimo_resul(0.0);
               gDet.setValor_maximo_resul(0.0);
               gDet.setValor_texto_referencia_resul(tb_Detalle.getValueAt(i,18).toString());    
               }
               gDet.setTipo_valor_referencia_resul(tb_Detalle.getValueAt(i, 19).toString());
               gDet.setObservaciones_resultado_exa(tb_Detalle.getValueAt(i, 20).toString());
               gDet.setUsa_valores_ref(tb_Detalle.getValueAt(i, 21).toString());
               gDet.setNom_usu(lblUsu.getText());
               
                gDet.LAB_Resultado_Muestra_Det_guardar();
			}
               
     }
         
    }
    public void Lab_guardar_Insumos(){
        LAB_Resultado_Insumos gCab=new LAB_Resultado_Insumos();
//                   gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
               gCab.setCod_per_verif(tb_InsumosFinal.getValueAt(0, 0).toString());
               gCab.setNombre_per_verif(tb_InsumosFinal.getValueAt(0, 1).toString());
               gCab.setCod_per_confirma(tb_InsumosFinal.getValueAt(0, 2).toString());
               gCab.setNombre_per_confirma(tb_InsumosFinal.getValueAt(0, 3).toString());
               gCab.setNom_usu(lblUsu.getText());
               
                gCab.LAB_Sustentacion_Insumos_Cab_guardar();
                
     for (int i = 0; i < tb_InsumosFinal.getRowCount(); i++){      
               
               LAB_Resultado_Insumos gDet=new LAB_Resultado_Insumos();
//                   gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
               gDet.setCod_KardexLAB(Integer.parseInt(tb_InsumosFinal.getValueAt(i, 4).toString()));
               gDet.setSituacion_consumo(tb_InsumosFinal.getValueAt(i, 5).toString());
               gDet.setMotivo_perdida(tb_InsumosFinal.getValueAt(i, 6).toString());
               gDet.setCantidad_perdida(Integer.parseInt(tb_InsumosFinal.getValueAt(i, 7).toString()));
               gDet.setCantidad_reasig(Integer.parseInt(tb_InsumosFinal.getValueAt(i,8).toString()));
               gDet.setCod_produc(tb_InsumosFinal.getValueAt(i, 9).toString());
               gDet.setId_cod_det(Integer.parseInt(lblid_cod_doc_det.getText()));
               gDet.setNom_usu(lblUsu.getText());
               
                gDet.LAB_Sustentacion_Insumos_Det_guardar();
     }
         
    }
    
    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    try {
                 String tipo="",serArea="",buscar="";
         buscar=txtBuscar.getText();
         
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
    }//GEN-LAST:event_btnBuscarActionPerformed

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
                txtPersonalResponR.setText(nombreCompleto);
                lblCodPerResponsaR.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                }
                else if(lbltipo.getText().equalsIgnoreCase("2")){
                personal.setVisible(false);
                int filaselec=tbPersonal.getSelectedRow();
                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                tbPersonal.getValueAt(filaselec, 3).toString()
                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                txtPersonalRegistraR.setText(nombreCompleto);
                lblCodPerRegistraR.setText(tbPersonal.getValueAt(filaselec, 1).toString());
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
                    txtBuscar.setEnabled(true);
                    btnBuscar.setEnabled(true);
                }

            }
            else{
                txtBuscar.setEnabled(false);
                btnBuscar.setEnabled(false);
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxBuscar2ItemStateChanged

    private void txtPersonalTomaMuestraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraKeyPressed
      
    }//GEN-LAST:event_txtPersonalTomaMuestraKeyPressed

    private void txtPersonalTomaMuestraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalTomaMuestraKeyReleased
public void Muestras_cargar(String nomen,String area){
    try {
        
             String titulos[]={"N°","codigoMuestra","Muestra"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
        String consulta="exec sp_LAB_TOMA_MUESTRA_DET_EXAMEN ?,?,?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, nomen);
             cmd.setString(2, area);
                cmd.setString(3, "2");
            
        ResultSet r=cmd.executeQuery();
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Muestras.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Muestras.setRowSorter(elQueOrdena);
            Muestras_formato();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
 public void Muestras_formato(){
    tb_Muestras.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_Muestras.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Muestras.getColumnModel().getColumn(2).setPreferredWidth(100);
    //Ocultar    
    tb_Muestras.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Muestras.getColumnModel().getColumn(1).setMaxWidth(0);
         
    tb_Muestras.getSelectionModel().setSelectionInterval(0, 0);
    tb_Muestras.requestFocus();
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        frm_LAB_RESULTADOS_MUESTRA_INGRESO tmi=new frm_LAB_RESULTADOS_MUESTRA_INGRESO();
        tmi.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtFormaPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaPagoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoKeyPressed

    private void txtFormaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFormaPagoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoKeyReleased

    private void txtNTomaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTomaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTomaKeyPressed

    private void txtNTomaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNTomaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNTomaKeyReleased

    private void txtPersonalTomaMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalTomaMuestraActionPerformed

    private void txtPersonalSolicitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaActionPerformed

    private void txtPersonalSolicitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaKeyPressed

    private void txtPersonalSolicitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicitaKeyReleased

    private void txtPisoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoKeyPressed

    private void txtPisoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPisoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoKeyReleased

    private void txtCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaActionPerformed

    private void txtCamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaKeyPressed

    private void txtCamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCamaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaKeyReleased

    private void txtCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTActionPerformed

    private void txtCPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTKeyPressed

    private void txtCPTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPTKeyReleased

    private void txtServAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServAreaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServAreaKeyPressed

    private void txtServAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServAreaKeyReleased

    private void txtPersonalResponRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalResponRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalResponRKeyReleased

    private void txtPersonalResponRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalResponRKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    if(lblCodPerResponsaR.getText().equalsIgnoreCase("")){
                        personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("1");
                    txtBuscar.setText("");
                    cbxBuscar2.setSelectedIndex(0);
                    }else{
                        txtPersonalRegistraR.requestFocus();
                    }
                    
                }
    }//GEN-LAST:event_txtPersonalResponRKeyPressed

    private void txtPersonalResponRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalResponRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalResponRActionPerformed

    private void txtFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFormaPagoActionPerformed

    private void txtNomenclaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomenclaturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaActionPerformed

    private void txtNomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomenclaturaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaKeyPressed

    private void txtNomenclaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomenclaturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomenclaturaKeyReleased

    private void txtFechaTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMActionPerformed

    private void txtFechaTMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaTMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMKeyPressed

    private void txtFechaTMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaTMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaTMKeyReleased

    private void txtFechaOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenActionPerformed

    private void txtFechaOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaOrdenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenKeyPressed

    private void txtFechaOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaOrdenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaOrdenKeyReleased

    private void txtHoraTMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraTMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMActionPerformed

    private void txtHoraTMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraTMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMKeyPressed

    private void txtHoraTMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraTMKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraTMKeyReleased

    private void txtHoraOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoraOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenActionPerformed

    private void txtHoraOrdenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraOrdenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenKeyPressed

    private void txtHoraOrdenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoraOrdenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoraOrdenKeyReleased

    private void txtPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPisoActionPerformed

    private void txtPersonalRegistraRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalRegistraRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRegistraRActionPerformed

    private void txtPersonalRegistraRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalRegistraRKeyPressed
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    if(lblCodPerRegistraR.getText().equalsIgnoreCase("")){
                        personal.setVisible(true);
                        Personal_cargar();
                        Personal_formato();
                        lbltipo.setText("2");
                        txtBuscar.setText("");
                        cbxBuscar2.setSelectedIndex(0);
                    }else{
                        txtResult_PN.requestFocus();
                    }
                    
                }
    }//GEN-LAST:event_txtPersonalRegistraRKeyPressed

    private void txtPersonalRegistraRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalRegistraRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRegistraRKeyReleased

    private void txtResult_PNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResult_PNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResult_PNActionPerformed

    private void txtResult_PNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResult_PNKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    tb_Detalle.getSelectionModel().setSelectionInterval(0, 0);
                    tb_Detalle.requestFocus();
                }
    }//GEN-LAST:event_txtResult_PNKeyPressed

    private void txtResult_PNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResult_PNKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResult_PNKeyReleased

    private void txthospiServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthospiServActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServActionPerformed

    private void txthospiServKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthospiServKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServKeyPressed

    private void txthospiServKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthospiServKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthospiServKeyReleased

    private void btnInsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsumosActionPerformed
ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/alerta32x32.png"));

        if(tb_InsumosFinal.getRowCount()>0){
           int limpiar = JOptionPane.showConfirmDialog(this, "¿Desea Nuevamente realizar la SUSTENTACIÓN de los Insumos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(limpiar ==0){
                  limpiarTabla();
                     frm_LAB_RESULTADO_INSUMOS RI=new frm_LAB_RESULTADO_INSUMOS();
        RI.setVisible(true);
        Insumos_cargar(lblid_cod_doc_det.getText());
        frm_LAB_RESULTADO_INSUMOS.lblArea.setText(lblArea.getText());
        frm_LAB_RESULTADO_INSUMOS.lblServicio.setText(lblServicio.getText());
              }
         
        }else{
            frm_LAB_RESULTADO_INSUMOS RI=new frm_LAB_RESULTADO_INSUMOS();
        RI.setVisible(true);
        Insumos_cargar(lblid_cod_doc_det.getText());
        frm_LAB_RESULTADO_INSUMOS.lblArea.setText(lblArea.getText());
        frm_LAB_RESULTADO_INSUMOS.lblServicio.setText(lblServicio.getText());
           
}
    }//GEN-LAST:event_btnInsumosActionPerformed
    
    
    public void exportar_pdf_automaticamente(){
         //NUMERO DE DIAS
            int sDias=0;
            Calendar fecha = new GregorianCalendar();
            int dia=fecha.get(Calendar.DAY_OF_MONTH);
            int mes=fecha.get(Calendar.MONTH)+1;
            int anio=fecha.get(Calendar.YEAR);
           
            if(mes>1){
            for(int i=1;i<mes;i++)    {
            switch (i){
                case 1:case 3:case 5:case 7:case 8:case 10:case 12: 
                sDias = sDias+ 31;
                break;
                case 2:	
                    if(anio %4==0){
                        sDias = sDias+ 29;
                    }else{
                        sDias = sDias+ 28;
                    }
                break;			
                case 4:case 6:case 9:case 11:			
                sDias = sDias+ 30;
                break;
                default:
                sDias = 0;
            }}}
            int ndias=sDias+dia;
            DecimalFormat df = new DecimalFormat("000");
            
        try {
               
               
        //para crear directorio
        File directorio =new File("D:\\LABORATORIO-RESULTADOS\\");
        directorio.mkdir();
       
         //para crear archivo
//         File archivo =new File(directorio,"archivo8.txt");
//         archivo.createNewFile();
         
         
//        Reportes re = new Reportes();
        String ruta = "/Reportes/LAB/RESULTADOS.jasper";
        
            String cod=lblid_cod_doc_det.getText();
            Map parametros=new HashMap();
            parametros.put("ID_COD_DET",cod);
            
            
        //ABRIR CUADRO DE DIALOGO PARA GUARDAR EL ARCHIVO         
        //dni + numero de dias + cod resultado 
           File JFC =new File("D:\\LABORATORIO-RESULTADOS\\"+
                   txtDni.getText()+
                   df.format(ndias)+
                   txtCodigo.getText());
                String PATH = JFC.getAbsolutePath();//obtenemos la direccion del archivo + el nombre a guardar
                try (PrintWriter printwriter = new PrintWriter(JFC)) {
                    
                    printwriter.print(ruta);
                }
               JasperPrint informe = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta), parametros, c.conectar());
            JasperExportManager.exportReportToPdfFile(informe, PATH);//mandamos como parametros la ruta del archivo a compilar y el nombre y ruta donde se guardaran    
                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if (!(PATH.endsWith(".pdf"))) {
                    File temp = new File(PATH + ".pdf");
                    JFC.renameTo(temp);//renombramos el archivo
                }
               
               //Para que lo abra una vez guardado
//               Runtime.getRuntime().exec("cmd /c start "+PATH + ".pdf");
               
         }catch (FileNotFoundException | HeadlessException e) {//por alguna excepcion salta un mensaje de error
            JOptionPane.showMessageDialog(null, "Error al Exportar el archivo!"+e.getMessage(), "Oops! Error", JOptionPane.ERROR_MESSAGE);
        } catch (JRException ex) {
         Logger.getLogger(frm_LAB_BUSCAR_RESULTADO.class.getName()).log(Level.SEVERE, null, ex);
     } /*catch (IOException ex) {
        Logger.getLogger(frm_LAB_BUSCAR_RESULTADO.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    }
    
    public void Insumos_cargar(String cod){
        //Combobox JTable
    String [] datos = {"Todo","Perdida"};
    JComboBox jcbx = new JComboBox(datos);
    try {
        int filaselec=frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getSelectedRow();
             String titulos[]={"N°","codigoKardex","Codigo Produc","Descripcion del Producto",
                 "Entrada","Cantidad","Saldo","UM","Fecha de Vencimiento","Lote","Marca","Estado de Uso"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];

            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
        String consulta="exec sp_LAB_BUSQUEDA_RESULTADO_INSUMOS ?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
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
            fila[8]=r.getString(8);
            fila[9]=r.getString(9);
            fila[10]=r.getString(10);
            fila[11]="Seleccionar...";//combo
                m.addRow(fila);
                c++;
            }
            frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.setRowSorter(elQueOrdena);
           //combobox en jTable
            TableColumn tc = frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(11);
            TableCellEditor tce = new DefaultCellEditor(jcbx);
            tc.setCellEditor(tce);
            
            //Combobox seleccionado en retorno
        frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.setDefaultRenderer(Object.class,new FormatoTabla());
            Insumos_formato();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void Insumos_formato(){
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(0).setPreferredWidth(30);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(3).setPreferredWidth(230);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(5).setPreferredWidth(80);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(7).setPreferredWidth(110);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(8).setPreferredWidth(110);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(9).setPreferredWidth(100);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(10).setPreferredWidth(150);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(11).setPreferredWidth(150);
       //Ocultar    
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(1).setMinWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(1).setMaxWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(2).setMinWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(2).setMaxWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(4).setMinWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(4).setMaxWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(6).setMinWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(6).setMaxWidth(0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getSelectionModel().setSelectionInterval(0, 0);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.requestFocus();
}
    public void limpiarTabla(){
   DefaultTableModel modelo = (DefaultTableModel)tb_InsumosFinal.getModel(); 
   int filas=tb_InsumosFinal.getRowCount();
   for(int i=0;i<filas;i++){
                    modelo.removeRow(0);
   }
   }
    
    public void enableDatos(){
    tb_Detalle.setEnabled(true);
    tb_Detalle.setBackground(Color.white);
}
    public void limpiar(){

        
//        lblNum_toma_mu_exa.setText(txtNum.getText());
//        lblCodPerToma.setText("");
//        txtPersonalTomaMuestra.setText("");
//        lblCodPerRegistra.setText("");
//        txtPersonalRegistraToma.setText("");
         DefaultTableModel modelo = (DefaultTableModel)tb_Detalle.getModel(); 
         int a=tb_Detalle.getRowCount();
   for(int i=0;i<a;i++){
                    modelo.removeRow(0);
   }          
      
}
   
  
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            
            lblHora1.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
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
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_MUESTRA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_RESULTADO_MUESTRA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnInsumos;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel2;
    public static javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCantidadInsumos;
    private javax.swing.JLabel lblCodPerRegistraR;
    private javax.swing.JLabel lblCodPerResponsaR;
    private javax.swing.JLabel lblFecha1;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblHora1;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblcod_cab_toma;
    public static javax.swing.JLabel lblcod_det_toma;
    public static javax.swing.JLabel lblcod_exa_ana;
    public static javax.swing.JLabel lblid_cod_doc_det;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelOcultar;
    private javax.swing.JPanel panelPaciente;
    private javax.swing.JPanel panelResultado;
    private javax.swing.JPanel panelTM;
    private javax.swing.JDialog personal;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tb_Detalle;
    public static javax.swing.JTable tb_Esquema;
    public static javax.swing.JTable tb_InsumosFinal;
    public static javax.swing.JTable tb_Valores;
    private javax.swing.JScrollPane tb_valores;
    private javax.swing.JScrollPane tb_valores1;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo7;
    public static javax.swing.JTextField txtActoMedico;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtCama;
    private javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtFechaOrden;
    public static javax.swing.JTextField txtFechaTM;
    public static javax.swing.JTextField txtFormaPago;
    public static javax.swing.JTextField txtHc;
    public static javax.swing.JTextField txtHoraOrden;
    public static javax.swing.JTextField txtHoraTM;
    public static javax.swing.JTextField txtNToma;
    public static javax.swing.JTextField txtNomenclatura;
    private javax.swing.JTextField txtNum;
    public static javax.swing.JTextField txtPacientes;
    private javax.swing.JTextField txtPersonalRegistraR;
    public static javax.swing.JTextField txtPersonalResponR;
    public static javax.swing.JTextField txtPersonalSolicita;
    public static javax.swing.JTextField txtPersonalTomaMuestra;
    public static javax.swing.JTextField txtPiso;
    private javax.swing.JTextField txtResult_PN;
    public static javax.swing.JTextField txtServArea;
    public static javax.swing.JTextField txtSexo;
    public static javax.swing.JTextField txthospiServ;
    // End of variables declaration//GEN-END:variables
}
