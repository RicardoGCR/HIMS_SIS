
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultCellEditor;
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
import modelos.LABORATORIO.LAB_Analisis_Detalle;
import modelos.LABORATORIO.LAB_Analisis_Examen;
import modelos.LABORATORIO.LAB_Clasificacion_Examen;
import modelos.LABORATORIO.LAB_Muestra_Examen;
import modelos.LABORATORIO.Render_Checkbox;
import servicios.Conexion;
import sun.security.util.Length;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_TOMA_MUESTRA_ extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_TOMA_MUESTRA_() {
        initComponents();
        c.conectar();
     
        h1 = new Thread(this);
        h1.start();
        buscar_HC.getContentPane().setBackground(Color.white); 
        buscar_HC.setLocationRelativeTo(null);
        Buscar_Unidad_Organica.getContentPane().setBackground(Color.white); 
        Buscar_Unidad_Organica.setLocationRelativeTo(null);
        panelPaciente.setBackground(Color.white); 
        panelCabecera.setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
       
        txtPacientes.setVisible(false);
            btnPacientes.setVisible(false);
            
            btnAreas.setVisible(false);
      
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_HC.setResizable(false);
       
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        
   LAB_HC_cargar();
   LAB_HC_formato();
   formato();
  
  
 
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
    tb_Pacientes.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_Pacientes.getColumnModel().getColumn(1).setPreferredWidth(90);
    tb_Pacientes.getColumnModel().getColumn(2).setPreferredWidth(260);
    tb_Pacientes.getColumnModel().getColumn(3).setPreferredWidth(85);
    tb_Pacientes.getColumnModel().getColumn(4).setPreferredWidth(140);
    tb_Pacientes.getColumnModel().getColumn(5).setPreferredWidth(50);
    tb_Pacientes.getColumnModel().getColumn(6).setPreferredWidth(50);
    tb_Pacientes.getColumnModel().getColumn(7).setPreferredWidth(145);
            //Ocultar    
    tb_Pacientes.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Pacientes.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Pacientes.getSelectionModel().setSelectionInterval(0, 0);
            tb_Pacientes.requestFocus();
}
    public void LAB_HC_cargar(){
    try {
             String titulos[]={"N°","N° H.C.","Paciente","Dirección","DNI","Sexo","Fecha de Nac.","Edad","COdigo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
        String consulta="exec Caja_BuscarHC ?";
        
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, "");
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
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_HC_formato(){
    tb_HC.getColumnModel().getColumn(0).setPreferredWidth(40);
    tb_HC.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(2).setPreferredWidth(230);
    tb_HC.getColumnModel().getColumn(3).setPreferredWidth(180);
    tb_HC.getColumnModel().getColumn(4).setPreferredWidth(100);
    tb_HC.getColumnModel().getColumn(5).setPreferredWidth(80);
    tb_HC.getColumnModel().getColumn(6).setPreferredWidth(150);
    tb_HC.getColumnModel().getColumn(7).setPreferredWidth(100);
    
            //Ocultar    
    tb_HC.getColumnModel().getColumn(8).setMinWidth(0);
    tb_HC.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
}
    public void LAB_Unidad_Organica_cargar(){
    try {
             String titulos[]={"Nº","Código","Código","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_UNIDAD_ORGAN_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
                m.addRow(fila);
                c++;
            }
            tb_Unidad_Organica.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Unidad_Organica.setRowSorter(elQueOrdena);
            this.tb_Unidad_Organica.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Unidad_Organica_formato(){
    tb_Unidad_Organica.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Unidad_Organica.getColumnModel().getColumn(2).setPreferredWidth(70);
    tb_Unidad_Organica.getColumnModel().getColumn(3).setPreferredWidth(180);
        tb_Unidad_Organica.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Unidad_Organica.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Unidad_Organica.getSelectionModel().setSelectionInterval(0, 0);
            tb_Unidad_Organica.requestFocus();
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

        buscar_HC = new javax.swing.JDialog();
        btnBuscar1 = new javax.swing.JButton();
        txtbuscarHC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_HC = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            Buscar_Unidad_Organica = new javax.swing.JDialog();
            jScrollPane2 = new javax.swing.JScrollPane();
            tb_Unidad_Organica = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jLabel2 = new javax.swing.JLabel();
                txtBuscarUni = new javax.swing.JTextField();
                btnBuscar2 = new javax.swing.JButton();
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
                jLabel19 = new javax.swing.JLabel();
                jLabel20 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                jScrollPane5 = new javax.swing.JScrollPane();
                tb_Pacientes = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        if(colIndex==0){
                            return true;
                        }else{
                            return false; //Disallow the editing of any cell
                        }}};
                        btnGuardar = new javax.swing.JButton();
                        panelCabecera = new javax.swing.JPanel();
                        btnPacientes = new javax.swing.JButton();
                        btnAreas = new javax.swing.JButton();
                        jLabel4 = new javax.swing.JLabel();
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

                        buscar_HC.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                        buscar_HC.setTitle("SISGESH.::.Clasificación Examen");
                        buscar_HC.setMinimumSize(new java.awt.Dimension(876, 692));

                        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscar1.setBorder(null);
                        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscar1ActionPerformed(evt);
                            }
                        });

                        txtbuscarHC.setForeground(new java.awt.Color(0, 51, 51));
                        txtbuscarHC.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtbuscarHCActionPerformed(evt);
                            }
                        });
                        txtbuscarHC.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtbuscarHCKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtbuscarHCKeyTyped(evt);
                            }
                        });

                        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                        jLabel1.setText("Búsqueda de H.C");

                        tb_HC.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_HC.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_HC.setRowHeight(25);
                        tb_HC.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_HCMouseClicked(evt);
                            }
                        });
                        tb_HC.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_HCKeyPressed(evt);
                            }
                        });
                        jScrollPane1.setViewportView(tb_HC);

                        javax.swing.GroupLayout buscar_HCLayout = new javax.swing.GroupLayout(buscar_HC.getContentPane());
                        buscar_HC.getContentPane().setLayout(buscar_HCLayout);
                        buscar_HCLayout.setHorizontalGroup(
                            buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_HCLayout.createSequentialGroup()
                                .addGap(300, 300, 300)
                                .addComponent(txtbuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_HCLayout.createSequentialGroup()
                                .addGap(0, 345, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(330, 330, 330))
                            .addGroup(buscar_HCLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1)
                                .addGap(19, 19, 19))
                        );
                        buscar_HCLayout.setVerticalGroup(
                            buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_HCLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(buscar_HCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbuscarHC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(38, Short.MAX_VALUE))
                        );

                        Buscar_Unidad_Organica.setTitle("SISGESH .::. BÚSQUEDA DE UNIDAD ORGÁNICA");
                        Buscar_Unidad_Organica.setAlwaysOnTop(true);
                        Buscar_Unidad_Organica.setFocusCycleRoot(false);
                        Buscar_Unidad_Organica.setMinimumSize(new java.awt.Dimension(381, 494));

                        tb_Unidad_Organica.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Unidad_Organica.setRowHeight(25);
                        tb_Unidad_Organica.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_Unidad_OrganicaMouseClicked(evt);
                            }
                        });
                        tb_Unidad_Organica.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Unidad_OrganicaKeyPressed(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tb_Unidad_Organica);

                        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                        jLabel2.setText("Búsqueda ");

                        txtBuscarUni.setForeground(new java.awt.Color(0, 51, 51));
                        txtBuscarUni.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscarUniActionPerformed(evt);
                            }
                        });
                        txtBuscarUni.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarUniKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscarUniKeyTyped(evt);
                            }
                        });

                        btnBuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscar2.setBorder(null);
                        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscar2ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout Buscar_Unidad_OrganicaLayout = new javax.swing.GroupLayout(Buscar_Unidad_Organica.getContentPane());
                        Buscar_Unidad_Organica.getContentPane().setLayout(Buscar_Unidad_OrganicaLayout);
                        Buscar_Unidad_OrganicaLayout.setHorizontalGroup(
                            Buscar_Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Buscar_Unidad_OrganicaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(125, 125, 125))
                            .addGroup(Buscar_Unidad_OrganicaLayout.createSequentialGroup()
                                .addGroup(Buscar_Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Buscar_Unidad_OrganicaLayout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(txtBuscarUni, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Buscar_Unidad_OrganicaLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(22, Short.MAX_VALUE))
                        );
                        Buscar_Unidad_OrganicaLayout.setVerticalGroup(
                            Buscar_Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Buscar_Unidad_OrganicaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(Buscar_Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarUni, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(42, Short.MAX_VALUE))
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
                                .addGap(240, 240, 240)
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
                        jLabel10.setText("Buscar (Alt+B)");

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(559, 559, 559)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

                        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel19.setText("Desde");

                        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel20.setText("Hasta");

                        jLabel21.setText("Búsqueda por:");

                        tb_Pacientes.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "Código", "N° de H.C", "Datos del Paciente", "DNI", "Fecha de Nac.", "Edad", "Sexo", "Cantidad de Exámenes"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, true, false, false, false, true, true
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_Pacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_Pacientes.setRowHeight(20);
                        tb_Pacientes.setSelectionBackground(new java.awt.Color(2, 67, 115));
                        tb_Pacientes.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_PacientesMouseClicked(evt);
                            }
                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                tb_PacientesMouseReleased(evt);
                            }
                        });
                        tb_Pacientes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                tb_PacientesPropertyChange(evt);
                            }
                        });
                        tb_Pacientes.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                tb_PacientesKeyTyped(evt);
                            }
                        });
                        jScrollPane5.setViewportView(tb_Pacientes);
                        if (tb_Pacientes.getColumnModel().getColumnCount() > 0) {
                            tb_Pacientes.getColumnModel().getColumn(5).setResizable(false);
                            tb_Pacientes.getColumnModel().getColumn(6).setResizable(false);
                        }

                        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                        btnGuardar.setMnemonic('B');
                        btnGuardar.setText("Buscar");
                        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGuardarActionPerformed(evt);
                            }
                        });

                        btnPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        btnPacientes.setBorder(null);
                        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnPacientesActionPerformed(evt);
                            }
                        });

                        btnAreas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        btnAreas.setBorder(null);
                        btnAreas.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAreasActionPerformed(evt);
                            }
                        });

                        jLabel4.setText("jLabel4");

                        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
                        panelCabecera.setLayout(panelCabeceraLayout);
                        panelCabeceraLayout.setHorizontalGroup(
                            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGap(17, 574, Short.MAX_VALUE)
                                        .addComponent(btnAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGap(183, 183, 183)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        );
                        panelCabeceraLayout.setVerticalGroup(
                            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                        );

                        panelPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Análisis"));

                        jLabel23.setText("DNI");

                        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtDni.setEnabled(false);

                        jLabel22.setText("Nº de H.C.");

                        txtHc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        txtHc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtHc.setEnabled(false);

                        txtPacientes.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                        txtPacientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPacientes.setEnabled(false);

                        jLabel7.setText("Paciente");

                        jLabel17.setText("Fecha de Nac.");

                        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtFecha.setEnabled(false);

                        jLabel18.setText("Sexo");

                        txtSexo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtSexo.setEnabled(false);

                        jLabel24.setText("Edad");

                        txtEdad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtEdad.setEnabled(false);

                        javax.swing.GroupLayout panelPacienteLayout = new javax.swing.GroupLayout(panelPaciente);
                        panelPaciente.setLayout(panelPacienteLayout);
                        panelPacienteLayout.setHorizontalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jLabel22))
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel17)))
                                .addGap(49, 49, 49)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jLabel7)
                                        .addGap(170, 170, 170)
                                        .addComponent(jLabel23))
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(jLabel24))
                                            .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(jLabel18))
                                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(72, 214, Short.MAX_VALUE))
                        );
                        panelPacienteLayout.setVerticalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHc, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPacienteLayout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(31, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnGuardar)
                                        .addGap(67, 67, 67))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(265, 265, 265)
                                        .addComponent(panelCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jScrollPane5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(panelPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnGuardar)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_HC.setModel(new DefaultTableModel());
            String titulos[]={"N°","N° H.C.","Paciente","Dirección","DNI","Sexo","Fecha de Nac.","Edad","COdigo"};
           m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
            consulta="exec Caja_BuscarHC ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtbuscarHC.getText());
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
                m.addRow(fila);
                c++;
            }
            tb_HC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_HC.setRowSorter(elQueOrdena);
            this.tb_HC.setModel(m);

            LAB_HC_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtbuscarHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarHCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarHCActionPerformed

    private void txtbuscarHCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_HC.getSelectionModel().setSelectionInterval(0, 0);
            tb_HC.requestFocus();
        }
    }//GEN-LAST:event_txtbuscarHCKeyPressed

    private void txtbuscarHCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarHCKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }

    }//GEN-LAST:event_txtbuscarHCKeyTyped

    private void tb_HCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_HCMouseClicked

    }//GEN-LAST:event_tb_HCMouseClicked

    private void tb_HCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_HCKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                buscar_HC.setVisible(false);
                int filaselec=tb_HC.getSelectedRow();
             
                
                txtPacientes.setText(tb_HC.getValueAt(filaselec, 1).toString());
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_HCKeyPressed

    private void tb_PacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_PacientesMouseClicked

    }//GEN-LAST:event_tb_PacientesMouseClicked

    private void tb_PacientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_PacientesMouseReleased
 
    }//GEN-LAST:event_tb_PacientesMouseReleased

    private void tb_PacientesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_PacientesPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_PacientesPropertyChange

    private void tb_PacientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_PacientesKeyTyped

    }//GEN-LAST:event_tb_PacientesKeyTyped

    private void btnAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAreasActionPerformed
Buscar_Unidad_Organica.setVisible(true);
    LAB_Unidad_Organica_cargar();
    LAB_Unidad_Organica_formato();
    }//GEN-LAST:event_btnAreasActionPerformed

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
          buscar_HC.setVisible(true);
        LAB_HC_cargar();
        LAB_HC_formato();
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
 
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tb_Unidad_OrganicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Unidad_OrganicaMouseClicked

    }//GEN-LAST:event_tb_Unidad_OrganicaMouseClicked

    private void tb_Unidad_OrganicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Unidad_OrganicaKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                Buscar_Unidad_Organica.setVisible(false);
                int filaselec=tb_Unidad_Organica.getSelectedRow();
              
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Unidad_OrganicaKeyPressed

    private void txtBuscarUniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarUniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUniActionPerformed

    private void txtBuscarUniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUniKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Unidad_Organica.getSelectionModel().setSelectionInterval(0, 0);
            tb_Unidad_Organica.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarUniKeyPressed

    private void txtBuscarUniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUniKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar2.doClick();
        }
    }//GEN-LAST:event_txtBuscarUniKeyTyped

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_Unidad_Organica.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Código","Unidad Orgánica"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
            consulta="exec sp_LAB_CLASIFICACION_EXAMEN_UNIDAD_ORGAN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarUni.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                m.addRow(fila);
                c++;
            }
            tb_Unidad_Organica.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Unidad_Organica.setRowSorter(elQueOrdena);
            this.tb_Unidad_Organica.setModel(m);
            LAB_Unidad_Organica_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar2ActionPerformed
    public void enableDatos(){
    tb_Pacientes.setEnabled(true);
    tb_Pacientes.setBackground(Color.white);
}
    public void limpiar(){
    
  
   DefaultTableModel modelo = (DefaultTableModel)tb_Pacientes.getModel(); 
   int filas=tb_Pacientes.getRowCount();
   for(int i=0;i<filas;i++){
                    modelo.removeRow(0);
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
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_TOMA_MUESTRA_().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Buscar_Unidad_Organica;
    public static javax.swing.JButton btnAreas;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnPacientes;
    private javax.swing.JDialog buscar_HC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelPaciente;
    public static javax.swing.JTable tb_HC;
    public static javax.swing.JTable tb_Pacientes;
    public static javax.swing.JTable tb_Unidad_Organica;
    private javax.swing.JLabel titulo5;
    private javax.swing.JTextField txtBuscarUni;
    public static javax.swing.JTextField txtDni;
    public static javax.swing.JTextField txtEdad;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtHc;
    public static javax.swing.JTextField txtPacientes;
    public static javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtbuscarHC;
    // End of variables declaration//GEN-END:variables
}
