
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
import java.util.HashMap;
import java.util.Map;
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
import modelos.LABORATORIO.LAB_PC_AREA;
import modelos.LABORATORIO.Render_Checkbox;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;
import static vista.LABORATORIO.frm_LAB_TOMA_MUESTRA_CABECERA.lblDocumento;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_TOMA_MUESTRA_INGRESO extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_TOMA_MUESTRA_INGRESO() {
        initComponents();
        c.conectar();
     
        h1 = new Thread(this);
        h1.start();
        buscar_HC.getContentPane().setBackground(Color.white); 
        buscar_HC.setLocationRelativeTo(null);
        nomenclatura.getContentPane().setBackground(Color.white); 
        nomenclatura.setLocationRelativeTo(null);

        lblServicio.setVisible(false);
        lblArea.setVisible(false);
        this.getContentPane().setBackground(Color.white); 
      
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_HC.setResizable(false);
        dateDesde.setBackground(Color.white);
        dateHasta.setBackground(Color.white);
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
       

   formato();
    dateDesde.requestFocus();
  
 
    //Servicio-Area
        LAB_PC_AREA pa=new LAB_PC_AREA();
        lblServicio.setText(pa.LAB_PC_AREA_SERVICIO());
        lblArea.setText(pa.LAB_PC_AREA_AREA());
         LISTAR_examenes();
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
    tb_Pacientes.getColumnModel().getColumn(8).setMinWidth(0);
    tb_Pacientes.getColumnModel().getColumn(8).setMaxWidth(0);
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
    
//    public void LAB_Unidad_Organica_cargar(){
//    try {
//             String titulos[]={"Nº","Código","Código","Área"};
//            m=new DefaultTableModel(null,titulos);
//            JTable p=new JTable(m);
//            String fila[]=new String[6];
//
//            Conexion obj=new Conexion();
//        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_UNIDAD_ORGAN_listar";
//        ResultSet r;
//        r=obj.Listar(consulta);
//        int c=1;
//        while(r.next()){
//            fila[0]=String.valueOf(c)+"º";
//            fila[1]=r.getString(1);
//            fila[2]=r.getString(2);
//            fila[3]=r.getString(3);
//                m.addRow(fila);
//                c++;
//            }
//            tb_Unidad_Organica.setModel(m);
//            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
//            tb_Unidad_Organica.setRowSorter(elQueOrdena);
//            this.tb_Unidad_Organica.setModel(m);
//    } catch (Exception e) {
//        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
//    }
//}
//    public void LAB_Unidad_Organica_formato(){
//    tb_Unidad_Organica.getColumnModel().getColumn(0).setPreferredWidth(50);
//    tb_Unidad_Organica.getColumnModel().getColumn(2).setPreferredWidth(70);
//    tb_Unidad_Organica.getColumnModel().getColumn(3).setPreferredWidth(180);
//        tb_Unidad_Organica.getColumnModel().getColumn(1).setMinWidth(0);
//    tb_Unidad_Organica.getColumnModel().getColumn(1).setMaxWidth(0);
//    tb_Unidad_Organica.getSelectionModel().setSelectionInterval(0, 0);
//            tb_Unidad_Organica.requestFocus();
//}
    
     public void Nomenclatura_cargar(String cod){
    try {
        int filaselec=tb_Nomenclatura.getSelectedRow();
             String titulos[]={"N°","codigo venta detalle","Codigo caja","Código CPT","Nomenclatura","Servicio/Área Solicita","Nº de Documento","Fecha de Emitido"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
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
    tb_Nomenclatura.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Nomenclatura.getSelectionModel().setSelectionInterval(0, 0);
    tb_Nomenclatura.requestFocus();
}
    
    public void LISTAR_examenes(){
    
        String buscar="",tipo="6",servicioArea="";
        
        
        if(lblArea.getText().equalsIgnoreCase("")){
        tipo="6";
        servicioArea=lblServicio.getText();
        }
         else {
             tipo="7";
            servicioArea=lblArea.getText();        
        }
        
    String consulta="";
        try {
            tb_Pacientes.setModel(new DefaultTableModel());
            String titulos[]={"Código","N° de H.C.","Datos del Paciente","DNI","Fecha de Nacimiento","Edad","Sexo","Cantidad de Exámenes","Documento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_TOMA_MUESTRA_CAJA ?,?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,0);
            cmd.setInt(2, 0);
            cmd.setString(3, buscar);
            cmd.setString(4, servicioArea);
            cmd.setString(5, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                m.addRow(fila);
                c++;
            }
            tb_Pacientes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Pacientes.setRowSorter(elQueOrdena);
            this.tb_Pacientes.setModel(m);
            
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
            jPopupMenu1 = new javax.swing.JPopupMenu();
            ver = new javax.swing.JMenuItem();
            nomenclatura = new javax.swing.JDialog();
            jScrollPane2 = new javax.swing.JScrollPane();
            tb_Nomenclatura = new javax.swing.JTable();
            txthc = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            txtNombres = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
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
            dateDesde = new com.toedter.calendar.JDateChooser();
            dateHasta = new com.toedter.calendar.JDateChooser();
            jLabel19 = new javax.swing.JLabel();
            jLabel20 = new javax.swing.JLabel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tb_Pacientes = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==0){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }}};
                    btnGuardar = new javax.swing.JButton();
                    chPacientes = new javax.swing.JCheckBox();
                    txtPacientes = new javax.swing.JTextField();
                    btnPacientes = new javax.swing.JButton();
                    lblServicio = new javax.swing.JLabel();
                    lblArea = new javax.swing.JLabel();
                    lbldia = new javax.swing.JLabel();

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

                    ver.setText("Ver Exámenes");
                    ver.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            verActionPerformed(evt);
                        }
                    });
                    jPopupMenu1.add(ver);

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
                    jScrollPane2.setViewportView(tb_Nomenclatura);

                    txthc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    txthc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    txthc.setText("N° de H.C.");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    jLabel5.setText("N° de H.C.");

                    txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    txtNombres.setText("jLabel4");

                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    jLabel2.setText("Paciente:");

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
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(81, 81, 81)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txthc))
                                .addGroup(nomenclaturaLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(28, Short.MAX_VALUE))
                    );
                    nomenclaturaLayout.setVerticalGroup(
                        nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nomenclaturaLayout.createSequentialGroup()
                            .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtNombres)
                                .addComponent(jLabel5)
                                .addComponent(txthc))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46))
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    setTitle("SISGESH .::. Análisis Examen");

                    jpanel.setBackground(new java.awt.Color(2, 67, 115));

                    titulo5.setBackground(new java.awt.Color(0, 102, 102));
                    titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                    titulo5.setForeground(new java.awt.Color(255, 255, 255));
                    titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    titulo5.setText("Toma de Muestra");
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
                            .addGap(333, 333, 333)
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
                            .addContainerGap(26, Short.MAX_VALUE))
                    );
                    jpanelLayout.setVerticalGroup(
                        jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addComponent(titulo5)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jpanelLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(lblHora))
                            .addGap(3, 3, 3)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                    );

                    jPanel1.setBackground(new java.awt.Color(204, 204, 204));
                    jPanel1.setPreferredSize(new java.awt.Dimension(721, 25));

                    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                    jLabel3.setText("Salir (Esc)");

                    jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    jLabel10.setText("Buscar (Alt+B)");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(550, 550, 550)
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
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );

                    dateDesde.setDateFormatString("dd-MM-yyyy");

                    dateHasta.setDateFormatString("dd-MM-yyyy");

                    jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel19.setText("Desde");

                    jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    jLabel20.setText("Hasta");

                    tb_Pacientes.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Código", "N° de H.C", "Datos del Paciente", "DNI", "Fecha de Nacimiento", "Edad", "Sexo", "Cantidad de Exámenes", "Documento"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, true, false, false, false, true, true, true
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }
                    });
                    tb_Pacientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                    tb_Pacientes.setComponentPopupMenu(jPopupMenu1);
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
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_PacientesKeyPressed(evt);
                        }
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

                    chPacientes.setText("Todos los Pacientes");
                    chPacientes.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            chPacientesItemStateChanged(evt);
                        }
                    });

                    txtPacientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    txtPacientes.setForeground(new java.awt.Color(0, 51, 51));

                    btnPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    btnPacientes.setBorder(null);
                    btnPacientes.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnPacientesActionPerformed(evt);
                        }
                    });

                    lblServicio.setText("jLabel2");

                    lblArea.setText("area");

                    lbldia.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
                    lbldia.setText("Exámenes del día");

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dateDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(41, 41, 41)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addComponent(lblServicio)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(btnGuardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblArea)
                            .addGap(74, 74, 74))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(327, 327, 327)
                            .addComponent(lbldia)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
                            .addGap(13, 13, 13))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dateDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(dateHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(33, 33, 33)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(chPacientes)
                                            .addComponent(txtPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblServicio))
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 20, Short.MAX_VALUE)
                                            .addComponent(lblArea)))
                                    .addGap(9, 9, 9)
                                    .addComponent(lbldia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
        buscar_HC.setVisible(true);
        txtbuscarHC.setText("");
        LAB_HC_cargar();
        LAB_HC_formato();
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void chPacientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chPacientesItemStateChanged
         if(chPacientes.isSelected()){
             txtPacientes.setEnabled(false);
             btnPacientes.setEnabled(false);
             txtPacientes.setText("");
         }else{
            txtPacientes.setEnabled(true);
             btnPacientes.setEnabled(true); 
             txtPacientes.setText("");
             
         }
    }//GEN-LAST:event_chPacientesItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
 try{
          
            if(dateDesde.getDate()==null || dateHasta.getDate()==null){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un rango de Fechas.");
            }else if(txtPacientes.getText().isEmpty()&&chPacientes.isSelected()==false){
              JOptionPane.showMessageDialog(rootPane, "Seleccione y/o ingrese una Historia Clínica");
          }  else {
              buscar_examenes();
              lbldia.setVisible(false);
          }  
          }catch(Exception e) {
              JOptionPane.showMessageDialog(this, e.getMessage());
              
          }
    }//GEN-LAST:event_btnGuardarActionPerformed
public void buscar_examenes(){
    DecimalFormat df = new DecimalFormat("00");
        int dia,mes,anio,diah,mesh,anioh;
        dia = dateDesde.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = dateDesde.getCalendar().get(Calendar.MONTH) + 1;
        anio = dateDesde.getCalendar().get(Calendar.YEAR);
        diah = dateHasta.getCalendar().get(Calendar.DAY_OF_MONTH);
        mesh = dateHasta.getCalendar().get(Calendar.MONTH) + 1;
        anioh = dateHasta.getCalendar().get(Calendar.YEAR);
        
        int desde=Integer.parseInt(anio+df.format(mes)+df.format(dia));
        int hasta=Integer.parseInt(anioh+df.format(mesh)+df.format(diah));
        String buscar="",tipo="1",servicioArea="";
        
        
        if(chPacientes.isSelected()==true &&lblArea.getText().equalsIgnoreCase("")){
        tipo="1";
        servicioArea=lblServicio.getText();
        }
        else if(txtPacientes.getText().length()>0 &&lblArea.getText().equalsIgnoreCase("")){
        tipo="2";
         buscar=txtPacientes.getText();
        servicioArea=lblServicio.getText();
        }
        else if(chPacientes.isSelected()==true &&lblArea.getText().length()>0 ){
            tipo="3";
            servicioArea=lblArea.getText();        
        }
         else if(txtPacientes.getText().length()>0 &&lblArea.getText().length()>0 ){
             tipo="4";
              buscar=txtPacientes.getText();
            servicioArea=lblArea.getText();        
        }
        
    String consulta="";
        try {
            tb_Pacientes.setModel(new DefaultTableModel());
            String titulos[]={"Código","N° de H.C.","Datos del Paciente","DNI","Fecha de Nacimiento","Edad","Sexo","Cantidad de Exámenes","Documento"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                    consulta="exec sp_LAB_TOMA_MUESTRA_CAJA ?,?,?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1,desde);
            cmd.setInt(2, hasta);
            cmd.setString(3, buscar);
            cmd.setString(4, servicioArea);
            cmd.setString(5, tipo);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2);
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
                fila[8]=r.getString(9);
                m.addRow(fila);
                c++;
            }
            tb_Pacientes.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Pacientes.setRowSorter(elQueOrdena);
            this.tb_Pacientes.setModel(m);
            
            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}
    private void tb_PacientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_PacientesKeyPressed
        // TODO add your handling code here:
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    try{
                        dispose();
                        int filaselec=tb_Pacientes.getSelectedRow();
                        
              frm_LAB_TOMA_MUESTRA_CABECERA vr=new frm_LAB_TOMA_MUESTRA_CABECERA();
                    vr.setVisible(true);
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblHc.setText(tb_Pacientes.getValueAt(filaselec, 0).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtHc.setText(tb_Pacientes.getValueAt(filaselec, 1).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtPacientes.setText(tb_Pacientes.getValueAt(filaselec, 2).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtDni.setText(tb_Pacientes.getValueAt(filaselec, 3).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtFecha.setText(tb_Pacientes.getValueAt(filaselec, 4).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtEdad.setText(tb_Pacientes.getValueAt(filaselec, 5).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.txtSexo.setText(tb_Pacientes.getValueAt(filaselec, 6).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblCantidad.setText(tb_Pacientes.getValueAt(filaselec, 7).toString());
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblCantidad1.setText(tb_Pacientes.getValueAt(filaselec, 7).toString());
                    
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblServicio.setText(lblServicio.getText());
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblArea.setText(lblArea.getText());
                    
                    frm_LAB_TOMA_MUESTRA_CABECERA.lblDocumento.setText(tb_Pacientes.getValueAt(filaselec, 8).toString());
                    
                    String u=lblUsu.getText();
                             frm_LAB_TOMA_MUESTRA_CABECERA.lblUsu.setText(u);
                             
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "ingreso"+e.getMessage());
        }
        }
    }//GEN-LAST:event_tb_PacientesKeyPressed

    private void verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verActionPerformed
       try {
           int filaselec=tb_Pacientes.getSelectedRow();
           if(filaselec<0){
               JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
           }else{
            nomenclatura.setVisible(true);
            txthc.setText(tb_Pacientes.getValueAt(filaselec, 1).toString());
        txtNombres.setText(tb_Pacientes.getValueAt(filaselec, 2).toString());
        
        String cod=tb_Pacientes.getValueAt(filaselec, 8).toString();
        Nomenclatura_cargar(cod);
        Nomenclatura_formato();
        
           }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Cargar el reporte"+e.getMessage());
            }
    }//GEN-LAST:event_verActionPerformed

    private void tb_NomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NomenclaturaKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{

                int filaselec=tb_Nomenclatura.getSelectedRow();
                nomenclatura.setVisible(false);

                frm_LAB_TOMA_MUESTRA_DETALLE vr=new frm_LAB_TOMA_MUESTRA_DETALLE();
                vr.setVisible(true);
                frm_LAB_TOMA_MUESTRA_DETALLE.txtidDocumen.setText(lblDocumento.getText());
                frm_LAB_TOMA_MUESTRA_DETALLE.lblId_cod_doc_det.setText(tb_Nomenclatura.getValueAt(filaselec, 1).toString());

                frm_LAB_TOMA_MUESTRA_DETALLE.txtNomenclatura.setText(tb_Nomenclatura.getValueAt(filaselec, 3).toString());
                frm_LAB_TOMA_MUESTRA_DETALLE.txtCodigoCPT.setText(tb_Nomenclatura.getValueAt(filaselec, 4).toString());

                String u=lblUsu.getText();
                frm_LAB_TOMA_MUESTRA_DETALLE.lblUsu.setText(u);
            }catch(Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }}
    }//GEN-LAST:event_tb_NomenclaturaKeyPressed
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
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_INGRESO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_TOMA_MUESTRA_INGRESO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnPacientes;
    private javax.swing.JDialog buscar_HC;
    private javax.swing.JCheckBox chPacientes;
    private com.toedter.calendar.JDateChooser dateDesde;
    private com.toedter.calendar.JDateChooser dateHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbldia;
    private javax.swing.JDialog nomenclatura;
    public static javax.swing.JTable tb_HC;
    private javax.swing.JTable tb_Nomenclatura;
    public static javax.swing.JTable tb_Pacientes;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel txtNombres;
    public static javax.swing.JTextField txtPacientes;
    private javax.swing.JTextField txtbuscarHC;
    private javax.swing.JLabel txthc;
    private javax.swing.JMenuItem ver;
    // End of variables declaration//GEN-END:variables
}
