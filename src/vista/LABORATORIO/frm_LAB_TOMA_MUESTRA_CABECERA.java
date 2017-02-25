
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
import modelos.LABORATORIO.LAB_Valores_Referenciales;
import modelos.Usuario;
import servicios.Conexion;
import static vista.LABORATORIO.frm_LAB_TOMA_MUESTRA_DETALLE.tb_Muestras;

/**
 *
 * @author
 */
public class frm_LAB_TOMA_MUESTRA_CABECERA extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_TOMA_MUESTRA_CABECERA() {
        initComponents();
        c.conectar();
        // this.setExtendedState(MAXIMIZED_BOTH);
                
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
        lblNum_toma_mu_exa.setText(txtNum.getText());
        h1 = new Thread(this);
        h1.start();
        panelPaciente.setBackground(Color.white); 
        panelCabecera.setBackground(Color.white); 
        jPanel2.setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        nomenclatura.getContentPane().setBackground(Color.white);
        nomenclatura.setLocationRelativeTo(null);
        personal.getContentPane().setBackground(Color.white);
        personal.setLocationRelativeTo(null);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar

        //fecha
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        lblFecha1.setText(fechaActual());
        
        tb_Detalle.setEnabled(false);
        jPanel2.setEnabled(false);
        btnAgregar.setEnabled(false);
        btnQuitar.setEnabled(false);
//        formato();
        lbltipo.setVisible(false);
        
        
  
   //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();
            } });
    }
    
    public void formato(){
    tb_Detalle.getColumnModel().getColumn(0).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(1).setPreferredWidth(150);
    tb_Detalle.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Detalle.getColumnModel().getColumn(3).setPreferredWidth(200);
            //Ocultar    
//  tb_Detalle.getColumnModel().getColumn(0).setMinWidth(0);
//  tb_Detalle.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Detalle.getSelectionModel().setSelectionInterval(0, 0);
            tb_Detalle.requestFocus();
}

    public void Nomenclatura_cargar(String cod){
    try {
        int filaselec=tb_Nomenclatura.getSelectedRow();
             String titulos[]={"N°","codigo venta detalle","Codigo caja","Código CPT","Nomenclatura","Servicio/Área Solicita","Nº de Documento","Fecha de Emitido","Area"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
        String consulta="exec sp_LAB_TOMA_MUESTRA_NOMENCLATURA ?,?,?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, cod);
            if(lblArea.getText().isEmpty()){
                cmd.setString(2, lblServicio.getText());
                cmd.setString(3, "1");
            }
            else if(lblArea.getText().equalsIgnoreCase("")==false){
                cmd.setString(2, lblArea.getText());
                cmd.setString(3, "2");
            }
            
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
                m.addRow(fila);
                c++;
            }
            tb_Nomenclatura.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Nomenclatura.setRowSorter(elQueOrdena);
            this.tb_Nomenclatura.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void Nomenclatura_formato(){
    tb_Nomenclatura.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_Nomenclatura.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Nomenclatura.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_Nomenclatura.getColumnModel().getColumn(3).setPreferredWidth(100);
    tb_Nomenclatura.getColumnModel().getColumn(4).setPreferredWidth(240);
    tb_Nomenclatura.getColumnModel().getColumn(5).setPreferredWidth(240);
    tb_Nomenclatura.getColumnModel().getColumn(6).setPreferredWidth(120);
    tb_Nomenclatura.getColumnModel().getColumn(7).setPreferredWidth(120);
       //Ocultar    
    tb_Nomenclatura.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Nomenclatura.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Nomenclatura.getColumnModel().getColumn(2).setMinWidth(0);
//    tb_Nomenclatura.getColumnModel().getColumn(2).setMaxWidth(0);
//    tb_Nomenclatura.getColumnModel().getColumn(8).setMinWidth(0);
//    tb_Nomenclatura.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_Nomenclatura.getSelectionModel().setSelectionInterval(0, 0);
    tb_Nomenclatura.requestFocus();
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
            nomenclatura = new javax.swing.JDialog();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Nomenclatura = new javax.swing.JTable();
            txthc = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            txtNombres = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jpanel1 = new javax.swing.JPanel();
            titulo6 = new javax.swing.JLabel();
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            lblHora = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jPanel1 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tb_Detalle = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==0){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }}};
                    btnAgregar = new javax.swing.JButton();
                    panelCabecera = new javax.swing.JPanel();
                    jLabel25 = new javax.swing.JLabel();
                    jLabel26 = new javax.swing.JLabel();
                    txtPersonalTomaMuestra = new javax.swing.JTextField();
                    txtPersonalRegistraToma = new javax.swing.JTextField();
                    jLabel27 = new javax.swing.JLabel();
                    jLabel28 = new javax.swing.JLabel();
                    lblHora1 = new javax.swing.JLabel();
                    lblFecha1 = new javax.swing.JLabel();
                    lblHc1 = new javax.swing.JLabel();
                    lblCantidad = new javax.swing.JLabel();
                    btnGenerar = new javax.swing.JButton();
                    lblNum_toma_mu_exa = new javax.swing.JLabel();
                    jLabel29 = new javax.swing.JLabel();
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
                    btnQuitar = new javax.swing.JButton();
                    lblDocumento = new javax.swing.JLabel();
                    jPanel2 = new javax.swing.JPanel();
                    lblCantidad1 = new javax.swing.JLabel();
                    lblHc2 = new javax.swing.JLabel();
                    lblCantidad2 = new javax.swing.JLabel();
                    lblContador = new javax.swing.JLabel();
                    lblCodPerToma = new javax.swing.JLabel();
                    lblCodPerRegistra = new javax.swing.JLabel();
                    lblServicio = new javax.swing.JLabel();
                    lblArea = new javax.swing.JLabel();
                    txtCodigo = new javax.swing.JTextField();
                    txtNum = new javax.swing.JTextField();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    tb_Subdettalle = new javax.swing.JTable();

                    personal.setMinimumSize(new java.awt.Dimension(846, 504));

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
                            .addContainerGap(26, Short.MAX_VALUE))
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

                    nomenclatura.setMinimumSize(new java.awt.Dimension(710, 435));

                    tb_Nomenclatura.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Nomenclatura.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Nomenclatura.setRowHeight(21);
                    tb_Nomenclatura.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_NomenclaturaKeyPressed(evt);
                        }
                    });
                    jScrollPane3.setViewportView(tb_Nomenclatura);

                    txthc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    txthc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    txthc.setText("N° de H.C.");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    jLabel5.setText("N° de H.C.");

                    txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    txtNombres.setText("jLabel4");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    jLabel4.setText("Paciente:");

                    jpanel1.setBackground(new java.awt.Color(2, 67, 115));

                    titulo6.setBackground(new java.awt.Color(0, 102, 102));
                    titulo6.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
                    titulo6.setForeground(new java.awt.Color(255, 255, 255));
                    titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo6.setText("Exámenes Pendientes");
                    titulo6.setToolTipText("");
                    titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                    javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                    jpanel1.setLayout(jpanel1Layout);
                    jpanel1Layout.setHorizontalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(titulo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    jpanel1Layout.setVerticalGroup(
                        jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanel1Layout.createSequentialGroup()
                            .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout nomenclaturaLayout = new javax.swing.GroupLayout(nomenclatura.getContentPane());
                    nomenclatura.getContentPane().setLayout(nomenclaturaLayout);
                    nomenclaturaLayout.setHorizontalGroup(
                        nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(nomenclaturaLayout.createSequentialGroup()
                            .addGroup(nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(nomenclaturaLayout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txthc))
                                .addGroup(nomenclaturaLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(28, Short.MAX_VALUE))
                    );
                    nomenclaturaLayout.setVerticalGroup(
                        nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nomenclaturaLayout.createSequentialGroup()
                            .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtNombres)
                                .addComponent(jLabel5)
                                .addComponent(txthc))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46))
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    setTitle("SISGESH .::. Análisis Examen");

                    jpanel.setBackground(new java.awt.Color(2, 67, 115));

                    titulo5.setBackground(new java.awt.Color(0, 102, 102));
                    titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo5.setForeground(new java.awt.Color(255, 255, 255));
                    titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo5.setText("Exámenes por realizar");
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
                            .addGap(603, 603, 603)
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
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                    jPanel1.setBackground(new java.awt.Color(204, 204, 204));

                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jLabel3.setText("Salir (Esc)");

                    jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    jLabel10.setText("Generar (Alt+G)");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(910, 910, 910)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGap(8, 8, 8))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(5, 5, 5))
                    );

                    tb_Detalle.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Código CPT", "Nomenclatura", "Servicio/Área", "Fecha Emitido"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, true, false, true
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tb_Detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Detalle.setRowHeight(20);
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

                    btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                    btnAgregar.setText("Agregar Toma de Muestra");
                    btnAgregar.setContentAreaFilled(false);
                    btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnAgregar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnAgregarActionPerformed(evt);
                        }
                    });

                    panelCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra "));

                    jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel25.setText("Personal - Toma de Muestra");

                    jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel26.setText("Personal - Registra Toma de Muestra");

                    txtPersonalTomaMuestra.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPersonalTomaMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPersonalTomaMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPersonalTomaMuestraKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtPersonalTomaMuestraKeyReleased(evt);
                        }
                    });

                    txtPersonalRegistraToma.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                    txtPersonalRegistraToma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                    txtPersonalRegistraToma.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtPersonalRegistraTomaKeyPressed(evt);
                        }
                    });

                    jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel27.setText("Fecha Toma Muestra");

                    jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel28.setText("Hora Toma Muestra");

                    lblHora1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    lblHora1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblHora1.setText("00:00:00");

                    lblFecha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    lblFecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblFecha1.setText("00/00/00");

                    lblHc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblHc1.setText("Cantidad de Exámenes");

                    lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblCantidad.setText("Cantidad de Exámenes");

                    btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                    btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/modificar16x16.png"))); // NOI18N
                    btnGenerar.setMnemonic('G');
                    btnGenerar.setText("Generar");
                    btnGenerar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGenerarActionPerformed(evt);
                        }
                    });

                    lblNum_toma_mu_exa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblNum_toma_mu_exa.setText("Num_toma_mu_exa");

                    jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel29.setText("N° de Toma de Muestra");

                    javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
                    panelCabecera.setLayout(panelCabeceraLayout);
                    panelCabeceraLayout.setHorizontalGroup(
                        panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblNum_toma_mu_exa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHora1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPersonalTomaMuestra, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(56, 56, 56)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtPersonalRegistraToma)
                                .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblHc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                            .addGap(39, 39, 39)
                            .addComponent(btnGenerar)
                            .addGap(12, 12, 12))
                    );
                    panelCabeceraLayout.setVerticalGroup(
                        panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCabeceraLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panelCabeceraLayout.createSequentialGroup()
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNum_toma_mu_exa)
                                        .addComponent(txtPersonalTomaMuestra)
                                        .addComponent(txtPersonalRegistraToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(21, 21, 21)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(jLabel28)
                                        .addComponent(lblHc1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCantidad)
                                        .addComponent(lblHora1)
                                        .addComponent(lblFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(5, 5, 5))))
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
                            .addGap(22, 22, 22)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                .addComponent(txtDni))
                            .addGap(33, 33, 33)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(39, 39, 39)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFecha)
                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGap(39, 39, 39)
                            .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEdad)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                            .addGap(36, 36, 36)
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

                    btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                    btnQuitar.setText(" Quitar Toma de Muestra");
                    btnQuitar.setContentAreaFilled(false);
                    btnQuitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnQuitar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnQuitarActionPerformed(evt);
                        }
                    });

                    lblDocumento.setText("lblDocumento");

                    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Exámenes Pendientes"));

                    lblCantidad1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    lblCantidad1.setForeground(new java.awt.Color(204, 0, 0));
                    lblCantidad1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    lblCantidad1.setText("0");

                    lblHc2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    lblHc2.setText("Falta Agregar Toma de Muestra:");

                    lblCantidad2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                    lblCantidad2.setForeground(new java.awt.Color(204, 0, 0));
                    lblCantidad2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    lblCantidad2.setText("Exámenes");

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(lblCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblCantidad2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addContainerGap())
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblHc2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCantidad1)
                                .addComponent(lblCantidad2))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    lblContador.setText("0");

                    lblServicio.setText("Servicio");

                    lblArea.setText("area");

                    tb_Subdettalle.setModel(new javax.swing.table.DefaultTableModel(
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
                    jScrollPane1.setViewportView(tb_Subdettalle);

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelCabecera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(21, 21, 21))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(lblCodPerToma, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(82, 82, 82)
                                    .addComponent(lblCodPerRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(lblServicio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblArea)
                                    .addGap(99, 99, 99)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(lblHc)
                                    .addGap(43, 43, 43)
                                    .addComponent(lblContador)
                                    .addGap(53, 53, 53))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(21, 21, 21))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHc)
                                .addComponent(lblDocumento)
                                .addComponent(lblContador)
                                .addComponent(lblCodPerToma, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCodPerRegistra, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblServicio)
                                .addComponent(lblArea)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(1, 1, 1)
                            .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnAgregar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnQuitar))))
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        nomenclatura.setVisible(true);
        txtNombres.setText(txtPacientes.getText());
        txthc.setText(txtHc.getText());
        Nomenclatura_cargar(lblDocumento.getText());
        Nomenclatura_formato();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed

        if(lblContador.getText().equalsIgnoreCase("0")){
            tb_Detalle.setEnabled(true);
        jPanel2.setEnabled(true);
        btnAgregar.setEnabled(true);
        btnQuitar.setEnabled(true);
        
//       Lab_guardar_cabecera();
        
        }
        else{
            
        }
    }//GEN-LAST:event_btnGenerarActionPerformed
        
    public void Lab_guardar_cabecera(){
          ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        try{
            if(lblCodPerToma.getText().equalsIgnoreCase("")||txtPersonalTomaMuestra.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para la Toma de Muestra");
          }  else if(lblCodPerRegistra.getText().equalsIgnoreCase("")||
                    txtPersonalRegistraToma.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Personal para el Registro de Toma de Muestra");
          }  
         else{
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar == 0 ){
                  LAB_Toma_Muestra_Cabecera meGuardar = new LAB_Toma_Muestra_Cabecera();
                  meGuardar.setCod_cab_toma_mu_exa(txtCodigo.getText());
                  meGuardar.setId_documento(lblDocumento.getText());
                  meGuardar.setNum_toma_mu_exa(lblNum_toma_mu_exa.getText());
                  meGuardar.setCod_per_toma_muestra(lblCodPerToma.getText());
                  meGuardar.setNombre_per_toma_muestra(txtPersonalTomaMuestra.getText());
                  meGuardar.setCod_per_regis_toma_muestra(lblCodPerRegistra.getText());
                  meGuardar.setNombre_per_regis_toma_muestra(txtPersonalRegistraToma.getText());
                  meGuardar.setNom_usu(lblUsu.getText());
  
                  if(meGuardar.LAB_Toma_Muestra_Cab_guardar()){
                    JOptionPane.showMessageDialog(null, "Datos Guardados");
                    limpiar();
                    enableDatos();
                    lblContador.setText("1");
                    txtPersonalTomaMuestra.setEnabled(false);
                    txtPersonalRegistraToma.setEnabled(false);
                  }
                  else{
                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
                  }}
          }}catch(Exception e) {
              JOptionPane.showMessageDialog(this, "Ingrese todos los campos");
              
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
                if(lbltipo.getText().equalsIgnoreCase("1")){
                personal.setVisible(false);
                int filaselec=tbPersonal.getSelectedRow();
                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                tbPersonal.getValueAt(filaselec, 3).toString()
                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                txtPersonalTomaMuestra.setText(nombreCompleto);
                lblCodPerToma.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                }
                else if(lbltipo.getText().equalsIgnoreCase("2")){
                personal.setVisible(false);
                int filaselec=tbPersonal.getSelectedRow();
                String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                tbPersonal.getValueAt(filaselec, 3).toString()
                +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                txtPersonalRegistraToma.setText(nombreCompleto);
                lblCodPerRegistra.setText(tbPersonal.getValueAt(filaselec, 1).toString());
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
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("1");
                }
    }//GEN-LAST:event_txtPersonalTomaMuestraKeyPressed

    private void txtPersonalTomaMuestraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalTomaMuestraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalTomaMuestraKeyReleased

    private void tb_NomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NomenclaturaKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{

                int filaselec=tb_Nomenclatura.getSelectedRow();
                nomenclatura.setVisible(false);

                frm_LAB_TOMA_MUESTRA_DETALLE vr=new frm_LAB_TOMA_MUESTRA_DETALLE();
                vr.setVisible(true);
                frm_LAB_TOMA_MUESTRA_DETALLE.lblServicio.setText(lblServicio.getText());
                
                frm_LAB_TOMA_MUESTRA_DETALLE.txtidDocumen.setText(lblDocumento.getText());
                frm_LAB_TOMA_MUESTRA_DETALLE.lblCodNomen.setText(tb_Nomenclatura.getValueAt(filaselec, 2).toString());

                frm_LAB_TOMA_MUESTRA_DETALLE.lblId_cod_doc_det.setText(tb_Nomenclatura.getValueAt(filaselec, 1).toString());

                frm_LAB_TOMA_MUESTRA_DETALLE.txtNomenclatura.setText(tb_Nomenclatura.getValueAt(filaselec, 4).toString());
                frm_LAB_TOMA_MUESTRA_DETALLE.txtCodigoCPT.setText(tb_Nomenclatura.getValueAt(filaselec, 3).toString());

                frm_LAB_TOMA_MUESTRA_DETALLE.txtServicio.setText(tb_Nomenclatura.getValueAt(filaselec, 5).toString());
                
                LAB_Toma_Muestra_Detalle md=new LAB_Toma_Muestra_Detalle();
                String cod_exa="";
                 cod_exa=md.LAB_Toma_Muestra_Det_exa(tb_Nomenclatura.getValueAt(filaselec, 2).toString(),tb_Nomenclatura.getValueAt(filaselec, 8).toString(),"1");
                frm_LAB_TOMA_MUESTRA_DETALLE.lblExa.setText(cod_exa);
                
                LAB_Toma_Muestra_Detalle md1=new LAB_Toma_Muestra_Detalle();
                frm_LAB_TOMA_MUESTRA_DETALLE.lblCantidadMues.setText(md1.LAB_Toma_Muestra_Det_exa(tb_Nomenclatura.getValueAt(filaselec, 2).toString(),tb_Nomenclatura.getValueAt(filaselec, 8).toString(),"3"));
                
                Muestras_cargar(tb_Nomenclatura.getValueAt(filaselec, 2).toString(),tb_Nomenclatura.getValueAt(filaselec, 8).toString());
                 
                String u=lblUsu.getText();
                frm_LAB_TOMA_MUESTRA_DETALLE.lblUsu.setText(u);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }}
        
    }//GEN-LAST:event_tb_NomenclaturaKeyPressed
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
    private void txtPersonalRegistraTomaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalRegistraTomaKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("2");
                }
    }//GEN-LAST:event_txtPersonalRegistraTomaKeyPressed
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
        lblNum_toma_mu_exa.setText(txtNum.getText());
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
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_CABECERA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_CABECERA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_CABECERA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_CABECERA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_TOMA_MUESTRA_CABECERA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    public static javax.swing.JLabel lblArea;
    public static javax.swing.JLabel lblCantidad;
    public static javax.swing.JLabel lblCantidad1;
    public static javax.swing.JLabel lblCantidad2;
    private javax.swing.JLabel lblCodPerRegistra;
    private javax.swing.JLabel lblCodPerToma;
    private javax.swing.JLabel lblContador;
    public static javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFecha1;
    public static javax.swing.JLabel lblHc;
    public static javax.swing.JLabel lblHc1;
    public static javax.swing.JLabel lblHc2;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHora1;
    private javax.swing.JLabel lblNum_toma_mu_exa;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JDialog nomenclatura;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelPaciente;
    private javax.swing.JDialog personal;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tb_Detalle;
    private javax.swing.JTable tb_Nomenclatura;
    private javax.swing.JTable tb_Subdettalle;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtHc;
    private javax.swing.JLabel txtNombres;
    private javax.swing.JTextField txtNum;
    public static javax.swing.JTextField txtPacientes;
    private javax.swing.JTextField txtPersonalRegistraToma;
    private javax.swing.JTextField txtPersonalTomaMuestra;
    public static javax.swing.JTextField txtSexo;
    private javax.swing.JLabel txthc;
    // End of variables declaration//GEN-END:variables
}
