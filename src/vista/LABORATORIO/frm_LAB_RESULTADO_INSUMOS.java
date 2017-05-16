/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.LABORATORIO;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
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
import modelos.LABORATORIO.LAB_Resultado_Insumos;

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
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        personal.setLocationRelativeTo(null);
        personal.getContentPane().setBackground(Color.white);
        
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        
         jtab1.setBackground(Color.white); 
         lbltipo.setVisible(false);
         lblArea.setVisible(false);
         lblServicio.setVisible(false);
         lblCodPerVerifica.setVisible(false);
         lblCodPerConfirma.setVisible(false);
         
          
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
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(0).setPreferredWidth(30);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(3).setPreferredWidth(230);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(5).setPreferredWidth(80);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(7).setPreferredWidth(100);
    frm_LAB_RESULTADO_INSUMOS.tbResultadoInsumos.getColumnModel().getColumn(8).setPreferredWidth(110);
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
            jPanel2 = new javax.swing.JPanel();
            jPanel6 = new javax.swing.JPanel();
            tab = new javax.swing.JTabbedPane();
            jtab1 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            tbResultadoInsumos = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    if(colIndex==11){
                        return true;
                    }else{
                        return false; //Disallow the editing of any cell
                    }
                }};
                chTodos = new javax.swing.JCheckBox();
                jPanel9 = new javax.swing.JPanel();
                txtPersonalVerifica = new javax.swing.JTextField();
                jPanel10 = new javax.swing.JPanel();
                txtPersonalConfirma = new javax.swing.JTextField();
                jLabel9 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                lblArea = new javax.swing.JLabel();
                btnBuscarNino1 = new javax.swing.JButton();
                btnBuscarNino = new javax.swing.JButton();
                lblCodPerVerifica = new javax.swing.JLabel();
                lblCodPerConfirma = new javax.swing.JLabel();
                btnInsumosSusten = new javax.swing.JButton();
                lblServicio = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                txtCantidadPer = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                txtCantidadReasig = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                lblInsumo = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                lblUM = new javax.swing.JLabel();
                lblSaldo = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jLabel20 = new javax.swing.JLabel();
                jLabel21 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                jLabel18 = new javax.swing.JLabel();
                jLabel19 = new javax.swing.JLabel();
                jScrollPane4 = new javax.swing.JScrollPane();
                txtMotivoPerdida = new javax.swing.JEditorPane();
                btnAceptar = new javax.swing.JButton();
                btnCancelar = new javax.swing.JButton();
                lblCodKardex = new javax.swing.JLabel();
                jLabel22 = new javax.swing.JLabel();
                lblCantidad = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jpanel = new javax.swing.JPanel();
                titulo5 = new javax.swing.JLabel();
                jPanel7 = new javax.swing.JPanel();
                jPanel8 = new javax.swing.JPanel();
                tb_valores1 = new javax.swing.JScrollPane();
                tb_Insumos = new javax.swing.JTable();

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
                setAlwaysOnTop(true);
                setMinimumSize(new java.awt.Dimension(974, 477));
                getContentPane().setLayout(null);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setLayout(null);

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));
                jPanel6.setLayout(null);
                jPanel2.add(jPanel6);
                jPanel6.setBounds(0, 440, 880, 30);

                getContentPane().add(jPanel2);
                jPanel2.setBounds(0, 440, 960, 30);

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
                tbResultadoInsumos.setRowHeight(25);
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
                chTodos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPersonalConfirma, javax.swing.GroupLayout.Alignment.TRAILING)
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

                btnInsumosSusten.setBackground(new java.awt.Color(102, 102, 102));
                btnInsumosSusten.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                btnInsumosSusten.setForeground(new java.awt.Color(255, 255, 255));
                btnInsumosSusten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/insumos-verificados.png"))); // NOI18N
                btnInsumosSusten.setText("Insumos Sustentados");
                btnInsumosSusten.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                btnInsumosSusten.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnInsumosSustenActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jtab1Layout = new javax.swing.GroupLayout(jtab1);
                jtab1.setLayout(jtab1Layout);
                jtab1Layout.setHorizontalGroup(
                    jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jtab1Layout.createSequentialGroup()
                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jtab1Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblArea, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                    .addComponent(lblServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnInsumosSusten, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jtab1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(21, Short.MAX_VALUE))
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
                            .addComponent(btnBuscarNino, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodPerConfirma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jtab1Layout.createSequentialGroup()
                                .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jtab1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jtab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                            .addComponent(btnBuscarNino1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                    .addComponent(btnInsumosSusten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                );

                tab.addTab("tab1", jtab1);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel1.setText("Motivo de Pérdida");

                txtCantidadPer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtCantidadPer.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtCantidadPerKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtCantidadPerKeyTyped(evt);
                    }
                });

                jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel7.setText("Cantidad Reasignada");

                txtCantidadReasig.setEditable(false);
                txtCantidadReasig.setBackground(new java.awt.Color(153, 153, 153));
                txtCantidadReasig.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtCantidadReasig.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtCantidadReasigKeyTyped(evt);
                    }
                });

                jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel13.setText("Nombre del Insumo");

                jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel16.setText("Saldo Actual");

                lblInsumo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N

                jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel17.setText("Unidad de Medida");

                lblUM.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                lblSaldo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                jPanel5.setLayout(null);

                jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
                jLabel20.setText("Motivo de Pérdida");
                jPanel5.add(jLabel20);
                jLabel20.setBounds(2, 0, 250, 21);

                jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel21.setText("__________________________________________________________________________________________________________________________________________________");
                jPanel5.add(jLabel21);
                jLabel21.setBounds(0, 10, 910, 20);

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                jPanel4.setLayout(null);

                jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
                jLabel18.setText("Insumo");
                jPanel4.add(jLabel18);
                jLabel18.setBounds(2, 0, 250, 21);

                jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
                jLabel19.setText("__________________________________________________________________________________________________________________________________________________");
                jPanel4.add(jLabel19);
                jLabel19.setBounds(0, 10, 900, 20);

                txtMotivoPerdida.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtMotivoPerdida.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtMotivoPerdidaKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtMotivoPerdidaKeyTyped(evt);
                    }
                });
                jScrollPane4.setViewportView(txtMotivoPerdida);

                btnAceptar.setBackground(new java.awt.Color(100, 100, 100));
                btnAceptar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                btnAceptar.setForeground(new java.awt.Color(255, 255, 255));
                btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/verificar (2).png"))); // NOI18N
                btnAceptar.setText("Aceptar");
                btnAceptar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                btnAceptar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAceptarActionPerformed(evt);
                    }
                });

                btnCancelar.setBackground(new java.awt.Color(100, 100, 100));
                btnCancelar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
                btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Deshacer-30.png"))); // NOI18N
                btnCancelar.setText("Cancelar");
                btnCancelar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnCancelarActionPerformed(evt);
                    }
                });

                lblCodKardex.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel22.setText("Cantidad a Usar");

                lblCantidad.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel2.setText("Cantidad Perdida");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(35, 35, 35)
                                                .addComponent(txtCantidadReasig, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtCantidadPer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(lblInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(41, 41, 41)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCodKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblUM, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(81, 81, 81))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblInsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(lblCodKardex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUM, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCantidadPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCantidadReasig, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(41, Short.MAX_VALUE))
                );

                tab.addTab("tab2", jPanel3);

                getContentPane().add(tab);
                tab.setBounds(10, 111, 970, 360);

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Resultado - Insumos");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(titulo5)
                        .addContainerGap(656, Short.MAX_VALUE))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(titulo5)
                        .addGap(0, 45, Short.MAX_VALUE))
                );

                getContentPane().add(jpanel);
                jpanel.setBounds(0, 0, 990, 93);

                jPanel7.setBackground(new java.awt.Color(255, 255, 255));
                jPanel7.setLayout(null);

                jPanel8.setBackground(new java.awt.Color(255, 255, 255));
                jPanel8.setLayout(null);
                jPanel7.add(jPanel8);
                jPanel8.setBounds(0, 440, 880, 30);

                tb_Insumos.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "cod_kar", "motivo_perdida", "Cantidad_perdida", "cantidad_reasig"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tb_Insumos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_valores1.setViewportView(tb_Insumos);

                jPanel7.add(tb_valores1);
                tb_valores1.setBounds(10, 10, 770, 50);

                getContentPane().add(jPanel7);
                jPanel7.setBounds(0, 480, 950, 70);

                pack();
            }// </editor-fold>//GEN-END:initComponents

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
                    tbResultadoInsumos.setValueAt("Todo", i, 11);
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
                        if( tbResultadoInsumos.getRowCount()>0 ){
                            int filaselec=tbResultadoInsumos.getSelectedRow();
                            
                            if(tbResultadoInsumos.getValueAt(filaselec, 11).equals("Perdida")){
                            tab.setSelectedIndex(1);
                            
                            lblCodKardex.setText(String.valueOf(tbResultadoInsumos.getValueAt(filaselec,1)));
                            lblInsumo.setText(String.valueOf(tbResultadoInsumos.getValueAt(filaselec, 3)));
                            
                            lblCantidad.setText(String.valueOf(tbResultadoInsumos.getValueAt(filaselec, 5)));
                            
                            //saldo
                            LAB_Resultado_Insumos ri=new LAB_Resultado_Insumos();
                            String saldo=ri.LAB_Resultado_Insumo_Saldo(tbResultadoInsumos.getValueAt(filaselec, 2).toString());           
                            lblSaldo.setText(saldo);
                            
                            lblUM.setText(String.valueOf(tbResultadoInsumos.getValueAt(filaselec, 7)));
                            
                            txtMotivoPerdida.setText("");
                            txtCantidadPer.setText("");
                            txtCantidadReasig.setText("");
                        }
                        }           
                    }catch(Exception e){
           
       }
      }
        
    }//GEN-LAST:event_tbResultadoInsumosKeyPressed

    private void txtCantidadPerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadPerKeyTyped
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE &&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtCantidadPer.getText().length()>6){
        evt.consume();
    }
    }//GEN-LAST:event_txtCantidadPerKeyTyped

    private void txtCantidadReasigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadReasigKeyTyped
         char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtCantidadReasig.getText().contains(".")){
            evt.consume();            
        }
        if (txtCantidadReasig.getText().length()>6){
        evt.consume();
    }
    }//GEN-LAST:event_txtCantidadReasigKeyTyped

    private void txtMotivoPerdidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoPerdidaKeyTyped
      if (txtMotivoPerdida.getText().length()>280){
        evt.consume();
    }
    }//GEN-LAST:event_txtMotivoPerdidaKeyTyped

    private void txtMotivoPerdidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMotivoPerdidaKeyReleased
    
    }//GEN-LAST:event_txtMotivoPerdidaKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       tab.setSelectedIndex(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
           try {  
               
               BigDecimal bd = new BigDecimal(txtCantidadPer.getText());       
                bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
                BigDecimal bd1 = new BigDecimal(txtCantidadReasig.getText());       
                bd1 = bd1.setScale(0, BigDecimal.ROUND_HALF_UP);
                
                BigDecimal bdc = new BigDecimal(lblCantidad.getText());       
                bdc = bdc.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal bds = new BigDecimal(lblSaldo.getText());       
                bds = bds.setScale(2, BigDecimal.ROUND_HALF_UP);
                
                int cantidad=Integer.parseInt(lblCantidad.getText());
               int saldo=Integer.parseInt(lblSaldo.getText());
               
            if(txtMotivoPerdida.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Ingrese el Motivo de la Perdida del Insumo");
            }else if(txtCantidadPer.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Ingrese la Cantidad Perdida");
            }else if(Integer.parseInt(txtCantidadPer.getText())>cantidad){
                txtCantidadPer.setText("");
                txtCantidadReasig.setText("");
                JOptionPane.showMessageDialog(rootPane, "La Cantidad Perdida debe ser menor a "+lblCantidad.getText());
            }else if(txtCantidadReasig.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Ingrese la Cantidad Reasignada");
            }else if(Integer.parseInt(txtCantidadPer.getText())>saldo){
                JOptionPane.showMessageDialog(rootPane, "La Cantidad Reasignada no cubre el Stock del Producto.");
            }else{
        String cod_kardex,motivo_perdi,cant_perdida,cant_reasig;

            cod_kardex=lblCodKardex.getText();
            motivo_perdi=txtMotivoPerdida.getText();
            cant_perdida=txtCantidadPer.getText();
            cant_reasig=txtCantidadReasig.getText();
           
        if(tb_Insumos.getRowCount()>0){
              for (int i = 0; i < tb_Insumos.getRowCount(); i++){    
               if(tb_Insumos.getValueAt(i, 0).toString().equalsIgnoreCase(lblCodKardex.getText())){
                    DefaultTableModel modelo = (DefaultTableModel)tb_Insumos.getModel(); 
                   modelo.removeRow(i);
			}}  
            }       
            
           DefaultTableModel m;                         
           
              m=(DefaultTableModel)tb_Insumos.getModel();
           String filaelemento[]={cod_kardex,motivo_perdi,cant_perdida,cant_reasig};
               m.addRow(filaelemento);
                             
                tab.setSelectedIndex(0);
               
        } } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "La Cantidad Perdida debe ser menor a "+lblCantidad.getText());
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnInsumosSustenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsumosSustenActionPerformed
 int filtro,filtro2=0,per=0;

 
        if(lblCodPerVerifica.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Seleccione al Personal que Verificó los Insumos.");
                filtro=1;
            }else if(lblCodPerConfirma.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(rootPane, "Seleccione al Personal que Confirmó \nla verificación de los Insumos.");
                filtro=1;
            }
            else{
                //FILTRO PARA EL SELECCIONAR
                filtro=0;
                 for(int j=0;j<tbResultadoInsumos.getRowCount();j++){
              if(tbResultadoInsumos.getValueAt(j, 11).toString().equalsIgnoreCase("Seleccionar...")){
                  filtro=filtro+1;
              }}
              if(filtro>0){
              JOptionPane.showMessageDialog(rootPane, "Debe Seleccionar el Estado de Uso de cada Insumo.");  
              }
              else{
                  
              //PARA LA PERDIDA
                  for(int j=0;j<tbResultadoInsumos.getRowCount();j++){
                if(tbResultadoInsumos.getValueAt(j, 11).toString().equalsIgnoreCase("Perdida")){
                    per=per+1;
                    
              }}
              for(int j=0;j<tbResultadoInsumos.getRowCount();j++){
                if(tbResultadoInsumos.getValueAt(j, 11).toString().equalsIgnoreCase("Perdida")){
                   
                   for(int k=0;k<tb_Insumos.getRowCount();k++){
                    if(tb_Insumos.getValueAt(k, 0).toString().equalsIgnoreCase(tbResultadoInsumos.getValueAt(j, 1).toString())){
                       filtro2=filtro2+1;
                    }
                    }    
              }}
              if(filtro2>per || filtro2<per){
              JOptionPane.showMessageDialog(rootPane, "Debe Sustentar la Perdida del Insumo.");  
              }
              
              
              }
            }
        
        if(filtro==0 && filtro2==per){
        for (int i = 0; i < tbResultadoInsumos.getRowCount(); i++){    
               if(tbResultadoInsumos.getValueAt(i, 11).toString().equalsIgnoreCase("Todo")){
                    String cod_verif,per_veri,cod_conf,per_conf,cod_kardex,situacion,
                          motivo_perdi,cant_perdida,cant_reasig,cod_produc;
            cod_verif=lblCodPerVerifica.getText();
            per_veri=txtPersonalVerifica.getText();
            cod_conf=lblCodPerConfirma.getText();
            per_conf=txtPersonalConfirma.getText();
            cod_kardex=tbResultadoInsumos.getValueAt(i, 1).toString();
            situacion="Todo";
            motivo_perdi="";
            cant_perdida="0";
            cant_reasig="0";
            cod_produc=tbResultadoInsumos.getValueAt(i, 2).toString();
            
           DefaultTableModel m;         
              m=(DefaultTableModel)frm_LAB_RESULTADO_MUESTRA.tb_InsumosFinal.getModel();
           String filaelemento[]={cod_verif,per_veri,cod_conf,per_conf,cod_kardex,situacion,
                          motivo_perdi,cant_perdida,cant_reasig,cod_produc};
               m.addRow(filaelemento);
			}
               else if(tbResultadoInsumos.getValueAt(i, 11).toString().equalsIgnoreCase("Perdida")){
                    String cod_verif1,per_veri1,cod_conf1,per_conf1,cod_kardex1,situacion1,
                          motivo_perdi1="",cant_perdida1="",cant_reasig1="",cod_produc1;
                   cod_verif1=lblCodPerVerifica.getText();
                per_veri1=txtPersonalVerifica.getText();
                cod_conf1=lblCodPerConfirma.getText();
                per_conf1=txtPersonalConfirma.getText();
                cod_kardex1=tbResultadoInsumos.getValueAt(i, 1).toString();
                situacion1="Perdida";
                
                 for(int j=0;j<tb_Insumos.getRowCount();j++){
              if(tb_Insumos.getValueAt(j, 0).toString().equalsIgnoreCase(tbResultadoInsumos.getValueAt(i, 1).toString())){
                  motivo_perdi1=tb_Insumos.getValueAt(j, 1).toString();
                cant_perdida1=tb_Insumos.getValueAt(j, 2).toString();
                cant_reasig1=tb_Insumos.getValueAt(j, 3).toString();
              }}
                
                cod_produc1=tbResultadoInsumos.getValueAt(i, 2).toString();
            
                DefaultTableModel m;         
                m=(DefaultTableModel)frm_LAB_RESULTADO_MUESTRA.tb_InsumosFinal.getModel();
                String filaelemento[]={cod_verif1,per_veri1,cod_conf1,per_conf1,cod_kardex1,situacion1,
                          motivo_perdi1,cant_perdida1,cant_reasig1,cod_produc1};
               m.addRow(filaelemento);  
               }    }
        dispose();
            }
    }//GEN-LAST:event_btnInsumosSustenActionPerformed

    private void txtCantidadPerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadPerKeyReleased
       txtCantidadReasig.setText(txtCantidadPer.getText());
    }//GEN-LAST:event_txtCantidadPerKeyReleased
    public void enableDatos(){
   
}
       
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
          
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarNino;
    private javax.swing.JButton btnBuscarNino1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnInsumosSusten;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JCheckBox chTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jtab1;
    public static javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodKardex;
    private javax.swing.JLabel lblCodPerConfirma;
    private javax.swing.JLabel lblCodPerVerifica;
    private javax.swing.JLabel lblInsumo;
    private javax.swing.JLabel lblSaldo;
    public static javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblUM;
    private javax.swing.JLabel lbltipo;
    private javax.swing.JDialog personal;
    private javax.swing.JTabbedPane tab;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tbResultadoInsumos;
    public static javax.swing.JTable tb_Insumos;
    private javax.swing.JScrollPane tb_valores1;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo7;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidadPer;
    private javax.swing.JTextField txtCantidadReasig;
    private javax.swing.JEditorPane txtMotivoPerdida;
    public static javax.swing.JTextField txtPersonalConfirma;
    public static javax.swing.JTextField txtPersonalVerifica;
    // End of variables declaration//GEN-END:variables
}
