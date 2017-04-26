/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.LABORATORIO.LAB_Contenedor_Muestra;
import modelos.LABORATORIO.LAB_Toma_Muestra_Cabecera;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import modelos.LABORATORIO.FormatoTabla;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_RESULTADO_INSUMOS extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m;
JComboBox jcbx;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_RESULTADO_INSUMOS() {
        initComponents();
        c.conectar();
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        personal.setLocationRelativeTo(null);
        personal.setBackground(Color.white);
        
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
         jtab1.setBackground(Color.white); 
    
         
          
         //Combobox seleccionado en retorno
//        tbResultadoInsumos.setDefaultRenderer(Object.class,new FormatoTabla());
         //Combobox JTable
//    String [] datos = {"Pendiente","Salida","Retorno"};
//    jcbx = new JComboBox(datos);
//        
//        Insumos_cargar("3");
         
         
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
    
    
       public void Insumos_cargar(String cod){
      
    try {
        int filaselec=frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getSelectedRow();
             String titulos[]={"N°","codigoKardex","Codigo Produc","Descripcion del Producto",
                 "Entrada","Cantidad","Saldo","UM","Fecha de Vencimiento","Lote","Marca","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[13];

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
            fila[11]="";//combo
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
            
         
            Insumos_formato();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla"+ e.getMessage());
    }
}
    public void Insumos_formato(){
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(0).setPreferredWidth(35);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(3).setPreferredWidth(230);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(5).setPreferredWidth(80);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(7).setPreferredWidth(110);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(8).setPreferredWidth(120);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(9).setPreferredWidth(120);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(10).setPreferredWidth(150);
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
        jLabel12 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPersonal = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            cbxBuscar2 = new javax.swing.JComboBox();
            jpanel2 = new javax.swing.JPanel();
            titulo7 = new javax.swing.JLabel();
            lbltipo = new javax.swing.JLabel();
            tab = new javax.swing.JTabbedPane();
            jtab1 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbResultadoInsumos = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                chTodos = new javax.swing.JCheckBox();
                jPanel9 = new javax.swing.JPanel();
                txtPersonalVerifica = new javax.swing.JTextField();
                jPanel10 = new javax.swing.JPanel();
                jLabel9 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                lblArea = new javax.swing.JLabel();
                lblServicio = new javax.swing.JLabel();
                btnBuscarNino1 = new javax.swing.JButton();
                btnBuscarNino = new javax.swing.JButton();
                lblCodPerVerifica = new javax.swing.JLabel();
                lblCodPerConfirma = new javax.swing.JLabel();
                txtPersonalConfirma = new javax.swing.JTextField();
                jPanel3 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jTextField1 = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                txtCantidadPer = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                txtCantidadPer1 = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                jTextField3 = new javax.swing.JTextField();
                lblInsumo = new javax.swing.JLabel();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                lblFecha = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                lblHora = new javax.swing.JLabel();
                lblUsu = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                btnmodificar = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jPanel2 = new javax.swing.JPanel();

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

                jLabel12.setText("Búsqueda por:");

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
                                .addComponent(jLabel12)
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
                                    .addComponent(jLabel12)))
                            .addComponent(lbltipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("SISGESH .::. Muestra Examen");
                setMinimumSize(new java.awt.Dimension(921, 556));
                getContentPane().setLayout(null);

                tab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                jtab1.setBackground(new java.awt.Color(255, 255, 255));

                tbResultadoInsumos.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                tbResultadoInsumos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                    },
                    new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tbResultadoInsumos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tbResultadoInsumos.setRowHeight(22);
                tbResultadoInsumos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        tbResultadoInsumosPropertyChange(evt);
                    }
                });
                tbResultadoInsumos.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tbResultadoInsumosKeyPressed(evt);
                    }
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        tbResultadoInsumosKeyReleased(evt);
                    }
                });
                jScrollPane1.setViewportView(tbResultadoInsumos);

                chTodos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
                chTodos.setText("Todos (Productos)");
                chTodos.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        chTodosActionPerformed(evt);
                    }
                });

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

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

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPersonalVerifica)
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(txtPersonalVerifica, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel10.setBackground(new java.awt.Color(255, 255, 255));
                jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 305, Short.MAX_VALUE)
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 25, Short.MAX_VALUE)
                );

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel9.setText("Personal Verifica");

                jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel11.setText("Personal Confirma");

                btnBuscarNino1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino1.setMnemonic('B');
                btnBuscarNino1.setToolTipText("");
                btnBuscarNino1.setBorderPainted(false);
                btnBuscarNino1.setContentAreaFilled(false);
                btnBuscarNino1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNino1ActionPerformed(evt);
                    }
                });

                btnBuscarNino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                btnBuscarNino.setMnemonic('B');
                btnBuscarNino.setToolTipText("");
                btnBuscarNino.setBorderPainted(false);
                btnBuscarNino.setContentAreaFilled(false);
                btnBuscarNino.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscarNino.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarNinoActionPerformed(evt);
                    }
                });

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

                javax.swing.GroupLayout jtab1Layout = new javax.swing.GroupLayout(jtab1);
                jtab1.setLayout(jtab1Layout);
                jtab1Layout.setHorizontalGroup(
                    jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jtab1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jtab1Layout.createSequentialGroup()
                                .addGap(0, 115, Short.MAX_VALUE)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, 0)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jtab1Layout.createSequentialGroup()
                                        .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCodPerVerifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jtab1Layout.createSequentialGroup()
                                        .addComponent(btnBuscarNino1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCodPerConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(69, 69, 69)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(chTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jtab1Layout.createSequentialGroup()
                                        .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jtab1Layout.createSequentialGroup()
                            .addGap(295, 295, 295)
                            .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(295, Short.MAX_VALUE)))
                );
                jtab1Layout.setVerticalGroup(
                    jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jtab1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chTodos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCodPerVerifica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarNino1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(lblCodPerConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jtab1Layout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(144, Short.MAX_VALUE)))
                );

                tab.addTab("tab1", jtab1);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel1.setText("Motivo de Pérdida");

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel2.setText("Cantidad Perdida");

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel7.setText("Cantidad Reasignada");

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel13.setText("Insumo");

                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel16.setText("Lote");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtCantidadPer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblInsumo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCantidadPer1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(451, 451, 451))))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblInsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCantidadPer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))
                );

                tab.addTab("tab2", jPanel3);

                getContentPane().add(tab);
                tab.setBounds(10, 111, 900, 340);

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Resultado - Insumos");
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
                btnguardar.setToolTipText("Guardar (Alt+G)");
                btnguardar.setContentAreaFilled(false);
                btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnguardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnguardarActionPerformed(evt);
                    }
                });

                btneliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                btneliminar.setMnemonic('E');
                btneliminar.setToolTipText("Eliminar (Alt+E)");
                btneliminar.setContentAreaFilled(false);
                btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btneliminar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btneliminarActionPerformed(evt);
                    }
                });

                btnmodificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
                btnmodificar.setMnemonic('M');
                btnmodificar.setToolTipText("Modificar (Alt+M)");
                btnmodificar.setContentAreaFilled(false);
                btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnmodificar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnmodificarActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo5))
                        .addGap(322, 322, 322)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblFecha))
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblHora)))
                                .addGap(0, 140, Short.MAX_VALUE)))
                        .addContainerGap())
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(titulo5)
                        .addGap(0, 0, 0)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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

                getContentPane().add(jpanel);
                jpanel.setBounds(0, 0, 920, 93);

                jPanel1.setBackground(new java.awt.Color(204, 204, 204));

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                jLabel3.setText("Salir (Esc)");

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
                jLabel4.setText("Nuevo (Alt+N)");

                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                jLabel5.setText("Guardar (Alt+G)");

                jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/editar.png"))); // NOI18N
                jLabel6.setText("Modificar (Alt+M)");

                jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
                jLabel8.setText("Eliminar (Alt+E)");

                jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                jLabel10.setText("Buscar (Alt+B)");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(8, 8, 8))
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                );

                getContentPane().add(jPanel1);
                jPanel1.setBounds(0, 540, 820, 20);

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                jScrollPane2.setViewportView(jTable1);

                getContentPane().add(jScrollPane2);
                jScrollPane2.setBounds(0, 450, 860, 80);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setLayout(null);
                getContentPane().add(jPanel2);
                jPanel2.setBounds(0, 390, 880, 30);

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed

        enableDatos();
       
        btnguardar.setEnabled(true);
        btneliminar.setEnabled(false);
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
     
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        enableDatos();
        limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtPersonalVerificaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalVerificaCaretUpdate

    }//GEN-LAST:event_txtPersonalVerificaCaretUpdate

    private void btnBuscarNinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNinoActionPerformed
                    personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("1");
                    txtBuscar.setText("");
                    cbxBuscar2.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscarNinoActionPerformed

    private void txtPersonalConfirmaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPersonalConfirmaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonalConfirmaCaretUpdate

    private void btnBuscarNino1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNino1ActionPerformed
         personal.setVisible(true);
                    Personal_cargar();
                    Personal_formato();
                    lbltipo.setText("2");
                    txtBuscar.setText("");
                    cbxBuscar2.setSelectedIndex(0);
    }//GEN-LAST:event_btnBuscarNino1ActionPerformed

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

    private void chTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chTodosActionPerformed
        if(chTodos.isSelected()){
                DefaultTableModel modelo = (DefaultTableModel)tbResultadoInsumos.getModel(); 
         int a=tbResultadoInsumos.getRowCount();
                for(int i=0;i<a;i++){
//                    tbResultadoInsumos.getValueAt(i, 11).toString();
                    tbResultadoInsumos.setValueAt("Completo", i, 11);
                }         
           }else{
           }
    }//GEN-LAST:event_chTodosActionPerformed

    private void tbResultadoInsumosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResultadoInsumosKeyReleased
      int filaselec=tbResultadoInsumos.getSelectedRow();
      
        if(tbResultadoInsumos.getValueAt(filaselec, 11).equals("Perdida")){
            tab.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tbResultadoInsumosKeyReleased

    private void tbResultadoInsumosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbResultadoInsumosPropertyChange
    
    }//GEN-LAST:event_tbResultadoInsumosPropertyChange

    private void tbResultadoInsumosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResultadoInsumosKeyPressed
       char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){  
                    try{
                        if( tbResultadoInsumos.getRowCount()>0){
                            tab.setSelectedIndex(1);
                            int filaselec=tbResultadoInsumos.getSelectedRow();
                            lblInsumo.setText(String.valueOf(tbResultadoInsumos.getValueAt(filaselec, 3)));
                        }
           
                    }catch(Exception e){
           
       }
      }
        
    }//GEN-LAST:event_tbResultadoInsumosKeyPressed
    public void enableDatos(){
   
}
    public void limpiar()
{

   btnguardar.setEnabled(true);
   btnmodificar.setEnabled(false);
   btneliminar.setEnabled(false);
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
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_INSUMOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_INSUMOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_INSUMOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_RESULTADO_INSUMOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LAB_RESULTADO_INSUMOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarNino;
    private javax.swing.JButton btnBuscarNino1;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btneliminar;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnmodificar;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JCheckBox chTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jtab1;
    public static javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCodPerConfirma;
    private javax.swing.JLabel lblCodPerVerifica;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblInsumo;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JDialog personal;
    private javax.swing.JTabbedPane tab;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tbResultadoInsumos;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidadPer;
    private javax.swing.JTextField txtCantidadPer1;
    public static javax.swing.JTextField txtPersonalConfirma;
    public static javax.swing.JTextField txtPersonalVerifica;
    // End of variables declaration//GEN-END:variables
}
