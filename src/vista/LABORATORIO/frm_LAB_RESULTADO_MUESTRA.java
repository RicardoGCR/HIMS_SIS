
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
        // this.setExtendedState(MAXIMIZED_BOTH);
                
//       LAB_Toma_Muestra_Cabecera u=new LAB_Toma_Muestra_Cabecera();
//        txtCodigo.setText(u.LAB_Toma_Muestra_Cab_generarid("1"));
//        if(txtCodigo.getText().equalsIgnoreCase("")){
//        txtCodigo.setText("TC000000000000000001");
//        }
//     LAB_Toma_Muestra_Cabecera num=new LAB_Toma_Muestra_Cabecera();
//        txtNum.setText(num.LAB_Toma_Muestra_Cab_generarid("2"));
//        if(txtNum.getText().equalsIgnoreCase("")){
//        txtNum.setText("000000000001");
//        }   
//        lblNum_toma_mu_exa.setText(txtNum.getText());
        h1 = new Thread(this);
        h1.start();
        panelPaciente.setBackground(Color.white); 
        panelCabecera.setBackground(Color.white); 
        
        panelResultado.setBackground(Color.white);
        this.getContentPane().setBackground(Color.white); 

        personal.getContentPane().setBackground(Color.white);
        personal.setLocationRelativeTo(null);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        //ocultar
//        lblServicio.setVisible(false);
//        lblArea.setVisible(false);
//        txtCodigo.setVisible(false);
//        txtNum.setVisible(false);
//      
//        lblDocumento.setVisible(false);
//        lblHc.setVisible(false);
//        lblCodPerToma.setVisible(false);
//        panelSubdetalle.setVisible(false);
   
        //fecha
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
 
        
  
     
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
    
    tb_Detalle.getColumnModel().getColumn(3).setPreferredWidth(120);
    tb_Detalle.getColumnModel().getColumn(4).setPreferredWidth(180);
    tb_Detalle.getColumnModel().getColumn(5).setPreferredWidth(180);
    
    tb_Detalle.getColumnModel().getColumn(6).setPreferredWidth(100);
    tb_Detalle.getColumnModel().getColumn(7).setPreferredWidth(180);
    tb_Detalle.getColumnModel().getColumn(8).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(9).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(10).setPreferredWidth(100);
            //Ocultar    
  tb_Detalle.getColumnModel().getColumn(0).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(0).setMaxWidth(0);
  tb_Detalle.getColumnModel().getColumn(1).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Detalle.getColumnModel().getColumn(6).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_Detalle.getColumnModel().getColumn(10).setMinWidth(0);
  tb_Detalle.getColumnModel().getColumn(10).setMaxWidth(0);
    tb_Detalle.getSelectionModel().setSelectionInterval(0, 0);
            tb_Detalle.requestFocus();
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
            
        String consulta="exec sp_LAB_TOMA_MUESTRA_CAB_ROL ?,?,?";
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
            tb_Detalle = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==0){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }}};
                    panelCabecera = new javax.swing.JPanel();
                    jLabel25 = new javax.swing.JLabel();
                    txtPersonalTomaMuestra = new javax.swing.JTextField();
                    jLabel27 = new javax.swing.JLabel();
                    jLabel28 = new javax.swing.JLabel();
                    jLabel29 = new javax.swing.JLabel();
                    jLabel30 = new javax.swing.JLabel();
                    txtFormaPago = new javax.swing.JTextField();
                    txtNToma = new javax.swing.JTextField();
                    jLabel26 = new javax.swing.JLabel();
                    txtPersonalSolicita = new javax.swing.JTextField();
                    jLabel32 = new javax.swing.JLabel();
                    txtPiso = new javax.swing.JTextField();
                    txtCama = new javax.swing.JTextField();
                    jLabel33 = new javax.swing.JLabel();
                    txtActoMedico = new javax.swing.JTextField();
                    jLabel19 = new javax.swing.JLabel();
                    jLabel31 = new javax.swing.JLabel();
                    jLabel37 = new javax.swing.JLabel();
                    txtFechaTM = new javax.swing.JTextField();
                    txtFechaOrden = new javax.swing.JTextField();
                    txtHoraTM = new javax.swing.JTextField();
                    txtHoraOrden = new javax.swing.JTextField();
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
                    lblHc = new javax.swing.JLabel();
                    lblDocumento = new javax.swing.JLabel();
                    lblServicio = new javax.swing.JLabel();
                    lblArea = new javax.swing.JLabel();
                    txtCodigo = new javax.swing.JTextField();
                    txtNum = new javax.swing.JTextField();
                    panelSubdetalle = new javax.swing.JPanel();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    tb_Subdetalle = new javax.swing.JTable();
                    btnGenerar = new javax.swing.JButton();
                    jButton1 = new javax.swing.JButton();
                    panelResultado = new javax.swing.JPanel();
                    jLabel34 = new javax.swing.JLabel();
                    txtCPT = new javax.swing.JTextField();
                    txtServArea = new javax.swing.JTextField();
                    jLabel39 = new javax.swing.JLabel();
                    jLabel40 = new javax.swing.JLabel();
                    txtPersonalSolicita1 = new javax.swing.JTextField();
                    jLabel41 = new javax.swing.JLabel();
                    txtPiso1 = new javax.swing.JTextField();
                    txtCama1 = new javax.swing.JTextField();
                    jLabel42 = new javax.swing.JLabel();
                    txtNomenclatura = new javax.swing.JTextField();
                    jLabel35 = new javax.swing.JLabel();
                    lblcod_cab_toma = new javax.swing.JLabel();
                    lblcod_det_toma = new javax.swing.JLabel();
                    lblcod_exa_ana = new javax.swing.JLabel();

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

                    jpanel.setBackground(new java.awt.Color(2, 67, 115));

                    titulo5.setBackground(new java.awt.Color(0, 102, 102));
                    titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo5.setForeground(new java.awt.Color(255, 255, 255));
                    titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
                            .addComponent(titulo5)
                            .addGap(703, 703, 703)
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
                            .addContainerGap(190, Short.MAX_VALUE))
                    );
                    jpanelLayout.setVerticalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
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
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    tb_Detalle.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "idcoddet", "cod_exa_ana", "codigo caja", "Código CPT", "Nomenclatura", "Servicio/Área", "cod_per_solicita", "Personal Solicita", "Fecha de Entrega", "Hora de Entrega", "id_preventa", "Habitacion", "Cama"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false, false, false, false, false, false, false, false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tb_Detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Detalle.setRowHeight(25);
                    tb_Detalle.setSelectionBackground(new java.awt.Color(2, 67, 115));
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

                    panelCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra "));

                    jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel25.setText("Personal - Toma de Muestra");

                    txtPersonalTomaMuestra.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPersonalTomaMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel28.setText("Hora Toma Muestra");

                    jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel29.setText("N° de Toma de Muestra");

                    jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel30.setText("Forma de Pago");

                    txtFormaPago.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtFormaPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtNToma.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtNToma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtNToma.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtNTomaKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtNTomaKeyReleased(evt);
                        }
                    });

                    jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel26.setText("Personal - Solicita Muestra");

                    txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtPiso.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPiso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPiso.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPisoKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtPisoKeyReleased(evt);
                        }
                    });

                    txtCama.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel33.setText("Cama");

                    txtActoMedico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                    txtActoMedico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtActoMedico.setEnabled(false);

                    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel19.setText("Acto Médico");

                    jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel31.setText("Fecha Orden");

                    jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel37.setText("Hora Orden");

                    txtFechaTM.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtFechaTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtFechaOrden.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtFechaOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtHoraTM.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtHoraTM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtHoraOrden.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtHoraOrden.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
                    panelCabecera.setLayout(panelCabeceraLayout);
                    panelCabeceraLayout.setHorizontalGroup(
                        panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtActoMedico)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFormaPago)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPersonalSolicita))
                            .addGap(51, 51, 51)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(txtPiso))
                                    .addGap(6, 6, 6)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                            .addGap(63, 63, 63)
                                            .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(87, 87, 87))
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                            .addGap(11, 11, 11)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(43, 43, 43)))
                                    .addGap(40, 40, 40)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtFechaOrden))
                                    .addGap(36, 36, 36)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtHoraOrden)))
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(txtNToma))
                                    .addGap(49, 49, 49)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(60, 60, 60)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFechaTM)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                                    .addGap(33, 33, 33)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addComponent(txtHoraTM))))
                            .addGap(17, 17, 17))
                    );
                    panelCabeceraLayout.setVerticalGroup(
                        panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel29))
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28))
                                        .addComponent(jLabel30))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPersonalTomaMuestra)
                                        .addComponent(txtFormaPago)
                                        .addComponent(txtNToma)
                                        .addComponent(txtFechaTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHoraTM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtActoMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                            .addComponent(txtPersonalSolicita)
                                            .addGap(100, 100, 100))
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtCama)
                                                .addComponent(txtFechaOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtHoraOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(101, 101, 101))
                                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                                            .addComponent(txtPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel37))
                                    .addContainerGap())))
                    );

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
                            .addGap(34, 34, 34)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(36, 36, 36)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addComponent(txtDni))
                            .addGap(36, 36, 36)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(45, 45, 45)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFecha)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGap(36, 36, 36)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEdad)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                            .addGap(33, 33, 33)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSexo)
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panelPacienteLayout.setVerticalGroup(
                        panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPacienteLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22)
                                .addComponent(jLabel7)
                                .addComponent(jLabel23)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18)
                                .addComponent(jLabel24))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    lblHc.setText("lblHc");

                    lblDocumento.setText("lblDocumento");

                    lblServicio.setText("Servicio");

                    lblArea.setText("area");

                    tb_Subdetalle.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Subdetalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    jScrollPane1.setViewportView(tb_Subdetalle);

                    javax.swing.GroupLayout panelSubdetalleLayout = new javax.swing.GroupLayout(panelSubdetalle);
                    panelSubdetalle.setLayout(panelSubdetalleLayout);
                    panelSubdetalleLayout.setHorizontalGroup(
                        panelSubdetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelSubdetalleLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addContainerGap())
                    );
                    panelSubdetalleLayout.setVerticalGroup(
                        panelSubdetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                    );

                    btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                    btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                    btnGenerar.setMnemonic('G');
                    btnGenerar.setText("Guardar");
                    btnGenerar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGenerarActionPerformed(evt);
                        }
                    });

                    jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jButton1.setText("Regresar");
                    jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton1ActionPerformed(evt);
                        }
                    });

                    panelResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra "));

                    jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel34.setText("Código CPT");

                    txtCPT.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    txtServArea.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtServArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtServArea.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtServAreaKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtServAreaKeyReleased(evt);
                        }
                    });

                    jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel39.setText("Servicio/Área");

                    jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel40.setText("Personal - Solicita Muestra");

                    txtPersonalSolicita1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPersonalSolicita1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPersonalSolicita1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtPersonalSolicita1ActionPerformed(evt);
                        }
                    });
                    txtPersonalSolicita1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPersonalSolicita1KeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtPersonalSolicita1KeyReleased(evt);
                        }
                    });

                    jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel41.setText("Piso");

                    txtPiso1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPiso1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPiso1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPiso1KeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtPiso1KeyReleased(evt);
                        }
                    });

                    txtCama1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtCama1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtCama1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtCama1ActionPerformed(evt);
                        }
                    });
                    txtCama1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtCama1KeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtCama1KeyReleased(evt);
                        }
                    });

                    jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel42.setText("Cama");

                    txtNomenclatura.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtNomenclatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

                    javax.swing.GroupLayout panelResultadoLayout = new javax.swing.GroupLayout(panelResultado);
                    panelResultado.setLayout(panelResultadoLayout);
                    panelResultadoLayout.setHorizontalGroup(
                        panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelResultadoLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtServArea)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(45, 45, 45)
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCPT)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                    .addGap(51, 51, 51)
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(448, 448, 448))
                                .addGroup(panelResultadoLayout.createSequentialGroup()
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                        .addComponent(txtPersonalSolicita1))
                                    .addGap(24, 24, 24)
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPiso1)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtCama1)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                    .addGap(612, 612, 612))))
                    );
                    panelResultadoLayout.setVerticalGroup(
                        panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelResultadoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(jLabel39)
                                .addComponent(jLabel35))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCPT)
                                .addComponent(txtServArea)
                                .addComponent(txtNomenclatura))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel41))
                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPersonalSolicita1)
                                .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCama1)
                                    .addComponent(txtPiso1)))
                            .addGap(83, 83, 83))
                    );

                    lblcod_cab_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblcod_cab_toma.setText("cod_cab_toma");

                    lblcod_det_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblcod_det_toma.setText("cod_det_toma");

                    lblcod_exa_ana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblcod_exa_ana.setText("cod_exa_ana");

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(btnGenerar)))
                                    .addGap(85, 85, 85))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelResultado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(18, 18, 18))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(panelSubdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(lblServicio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblArea)
                            .addGap(18, 18, 18)
                            .addComponent(lblcod_cab_toma, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblcod_det_toma, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblcod_exa_ana, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                            .addComponent(lblHc, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblHc)
                                    .addComponent(lblDocumento)
                                    .addComponent(lblServicio)
                                    .addComponent(lblArea)
                                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblcod_cab_toma)
                                    .addComponent(lblcod_det_toma)
                                    .addComponent(lblcod_exa_ana))
                                .addComponent(panelSubdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(15, Short.MAX_VALUE))
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
//ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
//        try{
//            if(lblCodPerToma.getText().equalsIgnoreCase("")||txtPersonalTomaMuestra.getText().equalsIgnoreCase("")){
//              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para la Toma de Muestra");
//          }  else if(lblCodPerRegistra.getText().equalsIgnoreCase("")||
//                    txtPersonalRegistraToma.getText().equalsIgnoreCase("")){
//              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para el Registro de Toma de Muestra");
//          }  else if(Integer.parseInt(lblCantidad1.getText())>0){
//              JOptionPane.showMessageDialog(rootPane, "Agregue Toma de Muestra a todos los Exámenes");
//          }  
//         else{
//              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//              if(guardar ==0){
//                  LAB_Toma_Muestra_Cabecera meGuardar = new LAB_Toma_Muestra_Cabecera();
//                  meGuardar.setCod_cab_toma_mu_exa(txtCodigo.getText());
//                  meGuardar.setId_documento(lblDocumento.getText());
//                  meGuardar.setNum_toma_mu_exa(lblNum_toma_mu_exa.getText());
//                  meGuardar.setCod_per_toma_muestra(lblCodPerToma.getText());
//                  meGuardar.setNombre_per_toma_muestra(txtPersonalTomaMuestra.getText());
//                  meGuardar.setCod_per_regis_toma_muestra(lblCodPerRegistra.getText());
//                  meGuardar.setNombre_per_regis_toma_muestra(txtPersonalRegistraToma.getText());
//                  meGuardar.setNom_usu(lblUsu.getText());
//  
//                  if(meGuardar.LAB_Toma_Muestra_Cab_guardar()){
//                      Lab_guardar_detalleySub();
//                      LAB_Toma_Muestra_Cabecera mc=new LAB_Toma_Muestra_Cabecera();
//                      mc.LAB_Toma_Muestra_Caja_Estado(lblDocumento.getText());
//                      
//                    JOptionPane.showMessageDialog(null, "Datos Guardados");
//                    limpiar();
//                      dispose();
//                     frm_LAB_TOMA_MUESTRA_INGRESO tmi=new frm_LAB_TOMA_MUESTRA_INGRESO();
//                    tmi.setVisible(true);
//                  }
//                  else{
//                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
//                  }}
//          }}catch(Exception e) {
//              JOptionPane.showMessageDialog(this, e.getMessage());
//          }
    }//GEN-LAST:event_btnGenerarActionPerformed
        
    public void Lab_guardar_detalleySub(){
     
        
         
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
            
        String consulta="exec sp_LAB_TOMA_MUESTRA_CAB_ROL ?,?,?";
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
//                if(lbltipo.getText().equalsIgnoreCase("1")){
//                personal.setVisible(false);
//                int filaselec=tbPersonal.getSelectedRow();
//                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
//                tbPersonal.getValueAt(filaselec, 3).toString()
//                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
//                txtPersonalTomaMuestra.setText(nombreCompleto);
//                lblCodPerToma.setText(tbPersonal.getValueAt(filaselec, 1).toString());
//                }
//                else if(lbltipo.getText().equalsIgnoreCase("2")){
//                personal.setVisible(false);
//                int filaselec=tbPersonal.getSelectedRow();
//                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
//                tbPersonal.getValueAt(filaselec, 3).toString()
//                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
//                txtPersonalRegistraToma.setText(nombreCompleto);
//                lblCodPerRegistra.setText(tbPersonal.getValueAt(filaselec, 1).toString());
//                }
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

    private void txtCama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCama1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCama1ActionPerformed

    private void txtCama1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCama1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCama1KeyPressed

    private void txtCama1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCama1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCama1KeyReleased

    private void txtPiso1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiso1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiso1KeyReleased

    private void txtPiso1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiso1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiso1KeyPressed

    private void txtPersonalSolicita1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicita1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicita1KeyReleased

    private void txtPersonalSolicita1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicita1KeyPressed
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("1");
                }
    }//GEN-LAST:event_txtPersonalSolicita1KeyPressed

    private void txtPersonalSolicita1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonalSolicita1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalSolicita1ActionPerformed

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
    public void enableDatos(){
    tb_Detalle.setEnabled(true);
    tb_Detalle.setBackground(Color.white);
}
    public void limpiar(){
   LAB_Toma_Muestra_Cabecera u=new LAB_Toma_Muestra_Cabecera();
        txtCodigo.setText(u.LAB_Toma_Muestra_Cab_generarid("1"));
        if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("TC000000000000000001");
        }
     LAB_Toma_Muestra_Cabecera num=new LAB_Toma_Muestra_Cabecera();
        txtNum.setText(num.LAB_Toma_Muestra_Cab_generarid("2"));
        if(txtNum.getText().equalsIgnoreCase("")){
        txtNum.setText("000000000001");
        }   
        
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
        DefaultTableModel modelo1 = (DefaultTableModel)tb_Subdetalle.getModel(); 
        int b=tb_Subdetalle.getRowCount();
        for(int j=0;j<b;j++){
                    modelo1.removeRow(0);
   }
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel2;
    public static javax.swing.JLabel lblArea;
    public static javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblHc;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    public static javax.swing.JLabel lblcod_cab_toma;
    public static javax.swing.JLabel lblcod_det_toma;
    public static javax.swing.JLabel lblcod_exa_ana;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelPaciente;
    private javax.swing.JPanel panelResultado;
    private javax.swing.JPanel panelSubdetalle;
    private javax.swing.JDialog personal;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tb_Detalle;
    public static javax.swing.JTable tb_Subdetalle;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo7;
    public static javax.swing.JTextField txtActoMedico;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCPT;
    public static javax.swing.JTextField txtCama;
    private javax.swing.JTextField txtCama1;
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
    public static javax.swing.JTextField txtPersonalSolicita;
    private javax.swing.JTextField txtPersonalSolicita1;
    public static javax.swing.JTextField txtPersonalTomaMuestra;
    public static javax.swing.JTextField txtPiso;
    public static javax.swing.JTextField txtPiso1;
    public static javax.swing.JTextField txtServArea;
    public static javax.swing.JTextField txtSexo;
    // End of variables declaration//GEN-END:variables
}
