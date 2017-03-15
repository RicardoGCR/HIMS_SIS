
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.LABORATORIO.Celda_Checkox;
import modelos.LABORATORIO.LAB_Resultado_Muestra_Cabecera;
import modelos.LABORATORIO.LAB_Resultado_Muestra_Detalle;
import modelos.LABORATORIO.LAB_Toma_Muestra_Cabecera;
import modelos.LABORATORIO.LAB_Toma_Muestra_Detalle;
import modelos.LABORATORIO.LAB_Toma_Muestra_Subdetalle;
import modelos.LABORATORIO.LAB_Valores_Referenciales;
import modelos.Usuario;
import servicios.Conexion;
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
//         this.setExtendedState(MAXIMIZED_BOTH);
                
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
        
        h1 = new Thread(this);
        h1.start();
        panelPaciente.setBackground(Color.white); 
        panelTM.setBackground(Color.white); 
         panelAnalisis.setBackground(Color.white); 
        panelResultado.setBackground(Color.white);
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
        lblFecha.setText(fechaActual());
        lblFecha1.setText(fechaActual());
        
        lbltipo.setVisible(false);
        
        
  
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
    
    tb_Detalle.getColumnModel().getColumn(1).setPreferredWidth(153);
    tb_Detalle.getColumnModel().getColumn(5).setPreferredWidth(180);
    tb_Detalle.getColumnModel().getColumn(6).setPreferredWidth(150);
    
    tb_Detalle.getColumnModel().getColumn(16).setPreferredWidth(100);
    tb_Detalle.getColumnModel().getColumn(17).setPreferredWidth(100);
    tb_Detalle.getColumnModel().getColumn(18).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(20).setPreferredWidth(180);
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
            jLabel14 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            lblHora = new javax.swing.JLabel();
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
            panelAnalisis = new javax.swing.JPanel();
            txtNomenclatura = new javax.swing.JTextField();
            jLabel35 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            txtCPT = new javax.swing.JTextField();
            jLabel39 = new javax.swing.JLabel();
            txtServArea = new javax.swing.JTextField();

            personal.setAlwaysOnTop(true);
            personal.setMinimumSize(new java.awt.Dimension(852, 504));

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
            setTitle("SISGESH .::. Toma de Muestra - Ingreso\n");
            setPreferredSize(new java.awt.Dimension(1300, 710));

            jpanel.setBackground(new java.awt.Color(2, 67, 115));

            titulo5.setBackground(new java.awt.Color(0, 102, 102));
            titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
            titulo5.setForeground(new java.awt.Color(255, 255, 255));
            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            titulo5.setText("Resultado");
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

            lblUsu.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(255, 255, 255));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
            lblUsu.setText("Usuario");

            javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
            jpanel.setLayout(jpanelLayout);
            jpanelLayout.setHorizontalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(586, 586, 586)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblHora))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblFecha))
                        .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(53, Short.MAX_VALUE))
            );
            jpanelLayout.setVerticalGroup(
                jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(2, 2, 2)
                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(lblHora))
                    .addGap(2, 2, 2)
                    .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(jpanelLayout.createSequentialGroup()
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

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

            panelPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Paciente"));

            jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel23.setText("DNI");

            txtDni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtDni.setEnabled(false);

            jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel22.setText("Nº de H.C.");

            txtHc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtHc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHc.setEnabled(false);

            txtPacientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtPacientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPacientes.setEnabled(false);

            jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel7.setText("Paciente");

            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel17.setText("Fecha de Nac.");

            txtFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFecha.setEnabled(false);

            jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel18.setText("Sexo");

            txtSexo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtSexo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtSexo.setEnabled(false);

            jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel24.setText("Edad");

            txtEdad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtEdad.setEnabled(false);

            javax.swing.GroupLayout panelPacienteLayout = new javax.swing.GroupLayout(panelPaciente);
            panelPaciente.setLayout(panelPacienteLayout);
            panelPacienteLayout.setHorizontalGroup(
                panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPacienteLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEdad)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelPacienteLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(22, Short.MAX_VALUE))
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
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel7))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel23)
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addGap(0, 0, 0)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSexo))
                    .addGap(3, 3, 3))
            );

            btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
            btnGenerar.setMnemonic('G');
            btnGenerar.setText("Guardar");
            btnGenerar.setToolTipText("Guardar(Alt + G)");
            btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnGenerar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnGenerarActionPerformed(evt);
                }
            });

            jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
            jButton1.setText("Regresar");
            jButton1.setToolTipText("Regresar(Esc)");
            jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            panelResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

            jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel40.setText("Personal - Responsable Resultado");

            txtPersonalResponR.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            txtPersonalResponR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

            txtPersonalRegistraR.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
            txtPersonalRegistraR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

            jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel43.setText("Personal - Registra Resultado");

            jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel44.setText("Fecha Registro Resul.");

            jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel45.setText("Hora Registro Resul.");

            lblFecha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            lblFecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblFecha1.setText("00/00/00");

            lblHora1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            lblHora1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblHora1.setText("00:00:00");

            javax.swing.GroupLayout panelResultadoLayout = new javax.swing.GroupLayout(panelResultado);
            panelResultado.setLayout(panelResultadoLayout);
            panelResultadoLayout.setHorizontalGroup(
                panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultadoLayout.createSequentialGroup()
                    .addGap(66, 66, 66)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPersonalResponR, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPersonalRegistraR))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(126, 126, 126))
            );
            panelResultadoLayout.setVerticalGroup(
                panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelResultadoLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultadoLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(txtPersonalResponR))
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(3, 3, 3)
                            .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0)
                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPersonalRegistraR)
                        .addComponent(lblHora1)))
            );

            lblServicio.setText("Servicio");

            lblArea.setText("area");

            lblcod_cab_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblcod_cab_toma.setText("cod_cab_toma");

            lblCodPerResponsaR.setText("respons");

            lblCodPerRegistraR.setText("registra");

            lblcod_det_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblcod_det_toma.setText("cod_det_toma");

            lblcod_exa_ana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblcod_exa_ana.setText("cod_exa_ana");

            lblHc.setText("lblHc");

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
                    .addComponent(lblcod_cab_toma, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblcod_det_toma, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblHc, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblid_cod_doc_det, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(145, 145, 145))
            );
            panelOcultarLayout.setVerticalGroup(
                panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelOcultarLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tb_valores, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(panelOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                                .addComponent(lblid_cod_doc_det))))
                    .addGap(0, 0, 0))
            );

            panelTM.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra"));

            jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel19.setText("Acto Médico");

            txtActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtActoMedico.setEnabled(false);

            txtFormaPago.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFormaPago.setEnabled(false);
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

            jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel30.setText("Forma de Pago");

            jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel29.setText("N° de Toma de Muestra");

            txtNToma.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtNToma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNToma.setEnabled(false);
            txtNToma.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtNTomaKeyPressed(evt);
                }
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    txtNTomaKeyReleased(evt);
                }
            });

            jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel25.setText("Personal - Toma de Muestra");

            txtPersonalTomaMuestra.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
            txtPersonalTomaMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalTomaMuestra.setEnabled(false);
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

            jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel27.setText("Fecha Toma Muestra");

            txtFechaTM.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtFechaTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFechaTM.setEnabled(false);
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

            jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel28.setText("Hora Toma Muestra");

            txtHoraTM.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtHoraTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHoraTM.setEnabled(false);
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

            jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel26.setText("Personal - Solicita Muestra");

            txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
            txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPersonalSolicita.setEnabled(false);
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

            jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel32.setText("Piso");

            txtPiso.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtPiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPiso.setEnabled(false);
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

            jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel33.setText("Cama");

            txtCama.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCama.setEnabled(false);
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

            jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel31.setText("Fecha Orden");

            jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel37.setText("Hora Orden");

            txtFechaOrden.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtFechaOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtFechaOrden.setEnabled(false);
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

            txtHoraOrden.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtHoraOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtHoraOrden.setEnabled(false);
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

            javax.swing.GroupLayout panelTMLayout = new javax.swing.GroupLayout(panelTM);
            panelTM.setLayout(panelTMLayout);
            panelTMLayout.setHorizontalGroup(
                panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTMLayout.createSequentialGroup()
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTMLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelTMLayout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addGap(24, 24, 24)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPiso, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCama))
                    .addGap(27, 27, 27))
            );
            panelTMLayout.setVerticalGroup(
                panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTMLayout.createSequentialGroup()
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jLabel30)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32))
                    .addGap(0, 0, 0)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoraTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37))
                    .addGroup(panelTMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPersonalSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHoraOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3))
            );

            panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder("Análisis"));

            txtNomenclatura.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtNomenclatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNomenclatura.setEnabled(false);
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

            jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel35.setText("Nomenclatura");

            jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel34.setText("Código CPT");

            txtCPT.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
            txtCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtCPT.setEnabled(false);
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

            jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel39.setText("Servicio/Área");

            txtServArea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
            txtServArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtServArea.setEnabled(false);
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
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelAnalisisLayout.setVerticalGroup(
                panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelAnalisisLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jLabel39)
                    .addGap(0, 0, 0)
                    .addComponent(txtServArea)
                    .addGap(0, 0, 0)
                    .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addGap(0, 0, 0)
                            .addComponent(txtCPT))
                        .addGroup(panelAnalisisLayout.createSequentialGroup()
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAnalisisLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelAnalisisLayout.createSequentialGroup()
                                    .addComponent(jLabel35)
                                    .addGap(21, 21, 21)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(0, 0, 0))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelOcultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(64, 64, 64)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelTM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(25, 25, 25))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addComponent(panelOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
int filtro=0;
        try{
            if(lblCodPerResponsaR.getText().equalsIgnoreCase("")||txtPersonalResponR.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal Responsable del Resultado");
          }  else if(lblCodPerRegistraR.getText().equalsIgnoreCase("")||
                    txtPersonalRegistraR.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para el Registro del Resultado");
          }else {
              for(int j=0;j<tb_Detalle.getRowCount();j++){
              if(tb_Detalle.getValueAt(j, 5).toString().equalsIgnoreCase("")){
//                      ||tb_Detalle.getValueAt(j, 20).toString().equalsIgnoreCase("")){
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
                  meGuardar.setCod_cab_resultado_mu_ana(txtCodigo.getText());
                  meGuardar.setNum_resul_exa(txtNum.getText());
                  meGuardar.setCod_det_toma_mu_ana(lblcod_det_toma.getText());
                  meGuardar.setCod_per_resultado(lblCodPerResponsaR.getText());
                  meGuardar.setNombre_personal_resultado(txtPersonalResponR.getText());
                  meGuardar.setCod_per_regis_resul(lblCodPerRegistraR.getText());
                  meGuardar.setNombre_personal_regis_resul(txtPersonalRegistraR.getText());
                  meGuardar.setNom_usu(lblUsu.getText());
  
                  if(meGuardar.LAB_Resultado_Muestra_Cab_guardar()){
                      Lab_guardar_detalleySub();
                      
                      LAB_Resultado_Muestra_Cabecera mc=new LAB_Resultado_Muestra_Cabecera();
                      mc.LAB_Resultado_Caja_Estado(Integer.parseInt( lblid_cod_doc_det.getText()));
                      
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
                   gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
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
               gDet.setCod_cab_resultado_mu_ana(txtCodigo.getText());
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
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("1");
                    txtBuscar.setText("");
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
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("2");
                    txtBuscar.setText("");
                }
    }//GEN-LAST:event_txtPersonalRegistraRKeyPressed

    private void txtPersonalRegistraRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalRegistraRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalRegistraRKeyReleased
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
            lblHora.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
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
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
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
    private javax.swing.JLabel lblCodPerRegistraR;
    private javax.swing.JLabel lblCodPerResponsaR;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblHora;
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
    public static javax.swing.JTable tb_Valores;
    private javax.swing.JScrollPane tb_valores;
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
    private javax.swing.JTextField txtPersonalResponR;
    public static javax.swing.JTextField txtPersonalSolicita;
    public static javax.swing.JTextField txtPersonalTomaMuestra;
    public static javax.swing.JTextField txtPiso;
    public static javax.swing.JTextField txtServArea;
    public static javax.swing.JTextField txtSexo;
    // End of variables declaration//GEN-END:variables
}