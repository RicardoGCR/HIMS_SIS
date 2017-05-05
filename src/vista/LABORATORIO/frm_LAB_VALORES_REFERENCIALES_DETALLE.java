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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import modelos.LABORATORIO.LAB_Esquema_Resultado;
import modelos.LABORATORIO.LAB_Valores_Referenciales;
import modelos.Usuario;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import vista.PrincipalMDI;
import vista.frmlaboratorioClinico;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_VALORES_REFERENCIALES_DETALLE extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_VALORES_REFERENCIALES_DETALLE() {
        initComponents();
        c.conectar();
        txtGuarModif.setVisible(false);
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        panelAnalisis.setBackground(Color.white); 
        panelEsquema.setBackground(Color.white); 
        panelValores.setBackground(Color.white); 
        jPanel2.setBackground(Color.white); 
       lblCodFabr.setVisible(false);
        txtCodigoEsquema.setVisible(false);
         txtCodigoValorRef.setVisible(false);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_fabricante.setLocationRelativeTo(null);
        buscar_fabricante.getContentPane().setBackground(Color.white);
        LAB_Fabricante_cargar();
        LAB_Fabricante_formato();
        //color al text field
        jLabel41.setBackground(Color.BLACK);
       
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
          //codigo
        LAB_Valores_Referenciales u=new LAB_Valores_Referenciales();
        txtCodigoValorRef.setText(u.LAB_Valores_Ref_generarid());
   if(txtCodigoValorRef.getText().equalsIgnoreCase("")){
        txtCodigoValorRef.setText("VR00000000000000000000001");
    }
       //para limpiar el txt al darle click
 txtBuscarFab.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscarFab.setText("");
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
   
    public void LAB_Fabricante_cargar(){
    try {
             String titulos[]={"Nº","Código","Fabricante"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj=new Conexion();
        String consulta="EXEC sp_LAB_VALORES_Fabricante ";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Fabricante.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Fabricante.setRowSorter(elQueOrdena);
            this.tb_Fabricante.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Fabricante_formato(){
    tb_Fabricante.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Fabricante.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Fabricante.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Fabricante.getSelectionModel().setSelectionInterval(0, 0);
    tb_Fabricante.requestFocus();
}
    
    public void formato_esquema(){
    tb_ValoresRef.getColumnModel().getColumn(4).setPreferredWidth(200);
    tb_ValoresRef.getColumnModel().getColumn(5).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(6).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(7).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(8).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(9).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(10).setPreferredWidth(50);
    tb_ValoresRef.getColumnModel().getColumn(11).setPreferredWidth(60);
    tb_ValoresRef.getColumnModel().getColumn(12).setPreferredWidth(80);
    tb_ValoresRef.getColumnModel().getColumn(13).setPreferredWidth(80);
    tb_ValoresRef.getColumnModel().getColumn(14).setPreferredWidth(80);
    tb_ValoresRef.getColumnModel().getColumn(15).setPreferredWidth(168);
    
    
    tb_ValoresRef.getColumnModel().getColumn(0).setMinWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(1).setMinWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(2).setMinWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(2).setMaxWidth(0);
      tb_ValoresRef.getColumnModel().getColumn(3).setMinWidth(0);
    tb_ValoresRef.getColumnModel().getColumn(3).setMaxWidth(0);
    }
    public void refrescartabla(){
        
           //tabla
            String consultasql="";
            try {
            frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.setModel(new DefaultTableModel());
            String titulos[]={"cod_valores_refe_resul","cod_esquema_resul","estado_todos_fabricantes",
"cod_fabricante_producto_mh","Nombre del Fabricante","Año","Meses","Dias","Año","Meses","Dias","M / F","Embarazo",
"Mínimo","Máximo","Resultado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[17];

            LAB_Valores_Referenciales obj=new LAB_Valores_Referenciales();
            consultasql="exec sp_LAB_VALORES_REF_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consultasql);
            cmd.setString(1, txtCodigoEsquema.getText());
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
                fila[9]=r.getString(10);
                fila[10]=r.getString(11);
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14);
                fila[14]=r.getString(15);
                fila[15]=r.getString(16);
                m.addRow(fila);
                c++;
            }
            tb_ValoresRef.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_ValoresRef.setRowSorter(elQueOrdena);
            this.tb_ValoresRef.setModel(m);
            formato_esquema();
            } catch (SQLException ex) {
                   Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_ESQUEMA.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buscar_fabricante = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Fabricante = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jLabel28 = new javax.swing.JLabel();
            txtBuscarFab = new javax.swing.JTextField();
            btnBuscarFab = new javax.swing.JButton();
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            lblHora = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            panelAnalisis = new javax.swing.JPanel();
            jLabel23 = new javax.swing.JLabel();
            txtAnalisis = new javax.swing.JTextField();
            jLabel19 = new javax.swing.JLabel();
            txtUnidadOrganica = new javax.swing.JTextField();
            txtClasificacion = new javax.swing.JTextField();
            jLabel7 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            txtAbreviatura = new javax.swing.JTextField();
            panelEsquema = new javax.swing.JPanel();
            jLabel21 = new javax.swing.JLabel();
            txtNombreResultado = new javax.swing.JTextField();
            jLabel22 = new javax.swing.JLabel();
            txtResultadoDefec = new javax.swing.JTextField();
            txtUnidadMedida = new javax.swing.JTextField();
            jLabel20 = new javax.swing.JLabel();
            panelValores = new javax.swing.JPanel();
            jLabel24 = new javax.swing.JLabel();
            txtFabricante = new javax.swing.JTextField();
            jLabel2 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel9 = new javax.swing.JLabel();
            jLabel11 = new javax.swing.JLabel();
            jLabel12 = new javax.swing.JLabel();
            chTodos = new javax.swing.JCheckBox();
            btnFabricante = new javax.swing.JButton();
            lblCodFabr = new javax.swing.JLabel();
            txtDesdeM = new javax.swing.JTextField();
            txtHastaMeses = new javax.swing.JTextField();
            txtDesdeDias = new javax.swing.JTextField();
            txtDesdeAnio = new javax.swing.JTextField();
            txtHastaAnio = new javax.swing.JTextField();
            txtHastaDias = new javax.swing.JTextField();
            jLabel13 = new javax.swing.JLabel();
            jLabel16 = new javax.swing.JLabel();
            chEmbarazada = new javax.swing.JCheckBox();
            rbMasculino = new javax.swing.JRadioButton();
            rbFemenino = new javax.swing.JRadioButton();
            jLabel18 = new javax.swing.JLabel();
            txtMax = new javax.swing.JTextField();
            jLabel25 = new javax.swing.JLabel();
            jLabel26 = new javax.swing.JLabel();
            txtMin = new javax.swing.JTextField();
            txtResultado = new javax.swing.JTextField();
            jLabel27 = new javax.swing.JLabel();
            txtCodigoValorRef = new javax.swing.JTextField();
            txtCodigoEsquema = new javax.swing.JTextField();
            txtGuarModif = new javax.swing.JTextField();
            jPanel2 = new javax.swing.JPanel();
            jLabel35 = new javax.swing.JLabel();
            jLabel34 = new javax.swing.JLabel();
            jLabel39 = new javax.swing.JLabel();
            jLabel38 = new javax.swing.JLabel();
            jLabel37 = new javax.swing.JLabel();
            jLabel40 = new javax.swing.JLabel();
            jLabel41 = new javax.swing.JLabel();
            jScrollPane6 = new javax.swing.JScrollPane();
            tb_ValoresRef = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};

                buscar_fabricante.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                buscar_fabricante.setTitle("SISGESH .::. Búsqueda de Fabricante");
                buscar_fabricante.setMinimumSize(new java.awt.Dimension(365, 439));

                tb_Fabricante.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Fabricante.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_Fabricante.setRowHeight(25);
                tb_Fabricante.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_FabricanteMouseClicked(evt);
                    }
                });
                tb_Fabricante.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_FabricanteKeyPressed(evt);
                    }
                });
                jScrollPane2.setViewportView(tb_Fabricante);

                jLabel28.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                jLabel28.setText("Búsqueda ");

                txtBuscarFab.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscarFab.setText("Ingresar Fabricante");
                txtBuscarFab.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtBuscarFabActionPerformed(evt);
                    }
                });
                txtBuscarFab.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtBuscarFabKeyPressed(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtBuscarFabKeyTyped(evt);
                    }
                });

                btnBuscarFab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                btnBuscarFab.setBorder(null);
                btnBuscarFab.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarFabActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout buscar_fabricanteLayout = new javax.swing.GroupLayout(buscar_fabricante.getContentPane());
                buscar_fabricante.getContentPane().setLayout(buscar_fabricanteLayout);
                buscar_fabricanteLayout.setHorizontalGroup(
                    buscar_fabricanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_fabricanteLayout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(buscar_fabricanteLayout.createSequentialGroup()
                        .addGroup(buscar_fabricanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_fabricanteLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(txtBuscarFab, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarFab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(buscar_fabricanteLayout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel28)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                buscar_fabricanteLayout.setVerticalGroup(
                    buscar_fabricanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_fabricanteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addGroup(buscar_fabricanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscarFab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarFab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(41, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("SISGESH .::. Análisis Examen");
                setMinimumSize(new java.awt.Dimension(930, 719));

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Valores Referenciales");
                titulo5.setToolTipText("");
                titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel14.setForeground(new java.awt.Color(255, 255, 255));
                jLabel14.setText("Fecha:");

                lblFecha.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                lblFecha.setForeground(new java.awt.Color(255, 255, 255));
                lblFecha.setText("00/00/00");

                jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel15.setForeground(new java.awt.Color(255, 255, 255));
                jLabel15.setText("Hora:");

                lblHora.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                lblHora.setForeground(new java.awt.Color(255, 255, 255));
                lblHora.setText("00:00:00");

                lblUsu.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
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

                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                btnGuardar.setMnemonic('G');
                btnGuardar.setContentAreaFilled(false);
                btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
                jButton2.setMnemonic('E');
                jButton2.setContentAreaFilled(false);
                jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
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
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo5))
                        .addGap(13, 13, 13)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGap(699, 699, 699)
                                .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblHora))
                                    .addGroup(jpanelLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFecha)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 698, Short.MAX_VALUE)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))))
                );
                jpanelLayout.setVerticalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelLayout.createSequentialGroup()
                        .addComponent(titulo5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(btnGuardar)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
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

                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
                jLabel4.setText("Nuevo (Alt+N)");

                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                jLabel5.setText("Guardar (Alt+G)");

                jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
                jLabel8.setText("Eliminar (Alt+E)");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(784, 784, 784)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
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
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                );

                panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Análisis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 12))); // NOI18N

                jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel23.setText("Análisis");

                txtAnalisis.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtAnalisis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAnalisis.setEnabled(false);

                jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel19.setText("Nombre de la Unidad");

                txtUnidadOrganica.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtUnidadOrganica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtUnidadOrganica.setEnabled(false);

                txtClasificacion.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtClasificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtClasificacion.setEnabled(false);

                jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel7.setText("Clasificación");

                jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel17.setText("Abrev. Análisis");

                txtAbreviatura.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtAbreviatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAbreviatura.setEnabled(false);

                javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
                panelAnalisis.setLayout(panelAnalisisLayout);
                panelAnalisisLayout.setHorizontalGroup(
                    panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                .addComponent(txtClasificacion, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUnidadOrganica, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addComponent(txtAbreviatura, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelAnalisisLayout.setVerticalGroup(
                    panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidadOrganica, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                panelEsquema.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Esquema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 12))); // NOI18N

                jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel21.setText("Nombre del Resultado");

                txtNombreResultado.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtNombreResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtNombreResultado.setEnabled(false);
                txtNombreResultado.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtNombreResultadoActionPerformed(evt);
                    }
                });

                jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel22.setText("Resultado por Defecto");

                txtResultadoDefec.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtResultadoDefec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtResultadoDefec.setEnabled(false);
                txtResultadoDefec.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtResultadoDefecActionPerformed(evt);
                    }
                });

                txtUnidadMedida.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                txtUnidadMedida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtUnidadMedida.setEnabled(false);
                txtUnidadMedida.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtUnidadMedidaKeyPressed(evt);
                    }
                });

                jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel20.setText("Unidad de Medida");

                javax.swing.GroupLayout panelEsquemaLayout = new javax.swing.GroupLayout(panelEsquema);
                panelEsquema.setLayout(panelEsquemaLayout);
                panelEsquemaLayout.setHorizontalGroup(
                    panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquemaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtResultadoDefec)
                            .addComponent(txtUnidadMedida)
                            .addGroup(panelEsquemaLayout.createSequentialGroup()
                                .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addComponent(txtNombreResultado)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                panelEsquemaLayout.setVerticalGroup(
                    panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquemaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtResultadoDefec, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                );

                panelValores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Referencial", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 1, 12))); // NOI18N

                jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel24.setText("Fabricante");

                txtFabricante.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtFabricante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtFabricante.setEnabled(false);

                jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Años");

                jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("Meses");

                jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel9.setText("Días");

                jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel11.setText("Desde");

                jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel12.setText("Hasta");

                chTodos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                chTodos.setText("Todos");
                chTodos.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        chTodosItemStateChanged(evt);
                    }
                });

                btnFabricante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                btnFabricante.setBorder(null);
                btnFabricante.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnFabricanteActionPerformed(evt);
                    }
                });

                lblCodFabr.setText("jLabel13");

                txtDesdeM.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtDesdeMKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDesdeMKeyTyped(evt);
                    }
                });

                txtHastaMeses.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtHastaMesesKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtHastaMesesKeyTyped(evt);
                    }
                });

                txtDesdeDias.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtDesdeDiasKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDesdeDiasKeyTyped(evt);
                    }
                });

                txtDesdeAnio.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtDesdeAnioKeyTyped(evt);
                    }
                });

                txtHastaAnio.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtHastaAnioKeyTyped(evt);
                    }
                });

                txtHastaDias.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtHastaDiasKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtHastaDiasKeyTyped(evt);
                    }
                });

                jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel13.setText("Género");

                jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel16.setText("Situación");

                chEmbarazada.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                chEmbarazada.setText("Embarazada");
                chEmbarazada.setEnabled(false);

                buttonGroup1.add(rbMasculino);
                rbMasculino.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                rbMasculino.setSelected(true);
                rbMasculino.setText("Masculino");
                rbMasculino.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        rbMasculinoMouseClicked(evt);
                    }
                });
                rbMasculino.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        rbMasculinoPropertyChange(evt);
                    }
                });

                buttonGroup1.add(rbFemenino);
                rbFemenino.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                rbFemenino.setText("Femenino");
                rbFemenino.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        rbFemeninoMouseClicked(evt);
                    }
                });
                rbFemenino.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        rbFemeninoPropertyChange(evt);
                    }
                });

                jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
                jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel18.setText("Valores Referenciales");

                txtMax.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtMax.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtMaxKeyReleased(evt);
                    }
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        txtMaxKeyTyped(evt);
                    }
                });

                jLabel25.setText("Mínimo:");

                jLabel26.setText("Máximo:");

                txtMin.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
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

                txtResultado.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                txtResultado.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        txtResultadoKeyReleased(evt);
                    }
                });

                jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel27.setText("Resultado");
                jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
                jLabel27.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

                javax.swing.GroupLayout panelValoresLayout = new javax.swing.GroupLayout(panelValores);
                panelValores.setLayout(panelValoresLayout);
                panelValoresLayout.setHorizontalGroup(
                    panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelValoresLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelValoresLayout.createSequentialGroup()
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFabricante)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panelValoresLayout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtHastaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelValoresLayout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDesdeAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addComponent(chTodos)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblCodFabr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addComponent(txtDesdeM, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDesdeDias, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addComponent(txtHastaMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtHastaDias, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rbFemenino)
                                        .addComponent(rbMasculino)))
                                .addGap(84, 84, 84)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelValoresLayout.createSequentialGroup()
                                        .addComponent(chEmbarazada)
                                        .addGap(63, 63, 63))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelValoresLayout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79))))
                            .addGroup(panelValoresLayout.createSequentialGroup()
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMin)
                                    .addComponent(txtMax))
                                .addGap(112, 112, 112)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtResultado)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(414, 414, 414))))
                );
                panelValoresLayout.setVerticalGroup(
                    panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelValoresLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelValoresLayout.createSequentialGroup()
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDesdeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDesdeDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDesdeAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelValoresLayout.createSequentialGroup()
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCodFabr)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel11)
                                    .addComponent(chEmbarazada)
                                    .addComponent(rbMasculino))
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbFemenino))
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtHastaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHastaMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtHastaDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelValoresLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelValoresLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)))
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                );

                txtCodigoValorRef.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                txtCodigoValorRef.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodigoValorRefActionPerformed(evt);
                    }
                });

                txtCodigoEsquema.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                txtCodigoEsquema.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodigoEsquemaActionPerformed(evt);
                    }
                });

                txtGuarModif.setText("G");
                txtGuarModif.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtGuarModifActionPerformed(evt);
                    }
                });

                jPanel2.setLayout(null);

                jLabel35.setBackground(new java.awt.Color(102, 102, 102));
                jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel35.setText("Texto");
                jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel35);
                jLabel35.setBounds(802, 0, 156, 22);

                jLabel34.setBackground(new java.awt.Color(102, 102, 102));
                jLabel34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel34.setText("Valores Normales");
                jLabel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel34);
                jLabel34.setBounds(642, 0, 160, 22);

                jLabel39.setBackground(new java.awt.Color(102, 102, 102));
                jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel39.setText("Situación");
                jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel39);
                jLabel39.setBounds(562, 0, 80, 22);

                jLabel38.setBackground(new java.awt.Color(102, 102, 102));
                jLabel38.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel38.setText("Género");
                jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel38);
                jLabel38.setBounds(502, 0, 60, 22);

                jLabel37.setBackground(new java.awt.Color(102, 102, 102));
                jLabel37.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel37.setText("Desde");
                jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel37);
                jLabel37.setBounds(202, 0, 150, 22);

                jLabel40.setBackground(new java.awt.Color(102, 102, 102));
                jLabel40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel40.setText("Hasta");
                jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel40);
                jLabel40.setBounds(352, 0, 150, 22);

                jLabel41.setBackground(new java.awt.Color(102, 102, 102));
                jLabel41.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel41.setText("Fabricante");
                jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jPanel2.add(jLabel41);
                jLabel41.setBounds(2, 0, 200, 22);

                tb_ValoresRef.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_ValoresRef.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Fabricante", "Año", "Meses", "Días", "Año", "Meses", "Días", "M / F", "Embarazada", "Mínimo", "Máximo", "Resultado"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tb_ValoresRef.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                tb_ValoresRef.setRowHeight(20);
                tb_ValoresRef.setSelectionBackground(new java.awt.Color(2, 67, 115));
                tb_ValoresRef.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_ValoresRefMouseClicked(evt);
                    }
                });
                jScrollPane6.setViewportView(tb_ValoresRef);
                if (tb_ValoresRef.getColumnModel().getColumnCount() > 0) {
                    tb_ValoresRef.getColumnModel().getColumn(0).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(1).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(2).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(3).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(4).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(5).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(6).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(7).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(8).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(9).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(10).setResizable(false);
                    tb_ValoresRef.getColumnModel().getColumn(11).setResizable(false);
                }

                jPanel2.add(jScrollPane6);
                jScrollPane6.setBounds(0, 20, 960, 260);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelEsquema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigoValorRef, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 446, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(panelValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoValorRef, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigoEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

    limpiar();
    enableDatos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtGuarModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuarModifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuarModifActionPerformed

    private void txtCodigoEsquemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoEsquemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoEsquemaActionPerformed

    private void txtUnidadMedidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadMedidaKeyPressed

    }//GEN-LAST:event_txtUnidadMedidaKeyPressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));

        try{
            if(txtGuarModif.getText().equalsIgnoreCase("G")){
                 if(lblCodFabr.getText().equalsIgnoreCase("")||txtFabricante.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Fabricante");
                }
                else if(txtDesdeAnio.getText().equalsIgnoreCase("")||txtDesdeDias.getText().equalsIgnoreCase("")||
                        txtDesdeM.getText().equalsIgnoreCase("")||txtHastaAnio.getText().equalsIgnoreCase("")||
                        txtHastaMeses.getText().equalsIgnoreCase("")||txtHastaDias.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Ingrese un rango de Años-Meses-Días");
                }
                else{
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                        LAB_Valores_Referenciales meGuardar = new LAB_Valores_Referenciales();
                         meGuardar.setCod_valores_refe_resul(txtCodigoValorRef.getText());
                        meGuardar.setCod_esquema_resul(txtCodigoEsquema.getText());
                        if(chTodos.isSelected()){
                            meGuardar.setEstado_todos_fabricantes("S");
                        }else{
                          meGuardar.setEstado_todos_fabricantes("N");  
                        }
                        meGuardar.setCod_fabricante_producto_mh(lblCodFabr.getText());
                         DecimalFormat df = new DecimalFormat("00");
                        meGuardar.setIni_anio(df.format(Integer.parseInt(txtDesdeAnio.getText())));
                         meGuardar.setIni_mes(df.format(Integer.parseInt(txtDesdeM.getText())));
                          meGuardar.setIni_dia(df.format(Integer.parseInt(txtDesdeDias.getText())));
                           meGuardar.setFin_anio(df.format(Integer.parseInt(txtHastaAnio.getText())));
                            meGuardar.setFin_mes(df.format(Integer.parseInt(txtHastaMeses.getText())));
                             meGuardar.setFin_dia(df.format(Integer.parseInt(txtHastaDias.getText())));
                             if(rbMasculino.isSelected()){
                             meGuardar.setGenero("M");
                             }else{
                                 meGuardar.setGenero("F");
                             }
                             if(chEmbarazada.isSelected()){
                            meGuardar.setEstado_clinico_ref("S");
                        }else{
                          meGuardar.setEstado_clinico_ref("N");  
                        }
                        
                    if(txtMin.getText().length()>0){
                         meGuardar.setValor_minimo(Double.parseDouble(txtMin.getText().toString()));
                    }else{
                        meGuardar.setValor_minimo(0);
                    }
                    if(txtMax.getText().length()>0){
                         meGuardar.setValor_maximo(Double.parseDouble(txtMax.getText().toString()));
                    }else{
                        meGuardar.setValor_maximo(0);
                    }
                        meGuardar.setValor_texto_referencia(txtResultado.getText());
                        if(txtResultado.getText().length()>0){
                         meGuardar.setTipo_valor_referencia("S");
                    }else{
                        meGuardar.setTipo_valor_referencia("N");
                    }
                        meGuardar.setNom_usu(lblUsu.getText());

                        if(meGuardar.LAB_Valores_Ref_guardar()){
                            JOptionPane.showMessageDialog(null, "Datos Guardados");
                           guardarTabla();
                            limpiar();
                            enableDatos();
                            txtGuarModif.setText("G");
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");

                        }}
                    //Modificar
                    }}else{
                      if(txtNombreResultado.getText().equalsIgnoreCase("")){

                    JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
                }
                        else{
                            int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                            if(modificar == 0 ){
                                  LAB_Valores_Referenciales vrModif = new LAB_Valores_Referenciales();
                         vrModif.setCod_valores_refe_resul(txtCodigoValorRef.getText());
                        vrModif.setCod_esquema_resul(txtCodigoEsquema.getText());
                        if(chTodos.isSelected()){
                           vrModif.setEstado_todos_fabricantes("S");
                        }else{
                          vrModif.setEstado_todos_fabricantes("N");  
                        }
                        vrModif.setCod_fabricante_producto_mh(lblCodFabr.getText()); 
                        
                        DecimalFormat d = new DecimalFormat("00");
                        vrModif.setIni_anio(d.format(Integer.parseInt(txtDesdeAnio.getText())));
                        vrModif.setIni_mes(d.format(Integer.parseInt(txtDesdeM.getText())));
                        vrModif.setIni_dia(d.format(Integer.parseInt(txtDesdeDias.getText())));
                        vrModif.setFin_anio(d.format(Integer.parseInt(txtHastaAnio.getText())));
                        vrModif.setFin_mes(d.format(Integer.parseInt(txtHastaMeses.getText())));
                        vrModif.setFin_dia(d.format(Integer.parseInt(txtHastaDias.getText())));
                             
                             if(rbMasculino.isSelected()){
                          vrModif.setGenero("M");
                             }else{
                         vrModif.setGenero("F");
                             }
                             if(chEmbarazada.isSelected()){
                             vrModif.setEstado_clinico_ref("S");
                        }else{
                          vrModif.setEstado_clinico_ref("N");  
                        }
                        
                    if(txtMin.getText().length()>0){
                         vrModif.setValor_minimo(Double.parseDouble(txtMin.getText().toString()));
                    }else{
                        vrModif.setValor_minimo(0);
                    }
                    if(txtMax.getText().length()>0){
                        vrModif.setValor_maximo(Double.parseDouble(txtMax.getText().toString()));
                    }else{
                      vrModif.setValor_maximo(0);
                    }
                       vrModif.setValor_texto_referencia(txtResultado.getText());
                        if(txtResultado.getText().length()>0){
                        vrModif.setTipo_valor_referencia("S");
                    }else{
                       vrModif.setTipo_valor_referencia("N");
                    }
                        vrModif.setNom_usu(lblUsu.getText());

                                if( vrModif.LAB_Valores_Ref_modificar()){
                                    JOptionPane.showMessageDialog(this, "Datos Modificados");
                                    refrescartabla();
                                    limpiar();
                                    enableDatos();
                                    txtGuarModif.setText("G");
                                }
                                else{
                                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");

                                }}
                            }}}catch(Exception e) {
                                JOptionPane.showMessageDialog(this, e.getMessage());

                            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
           
            int filaselec=tb_ValoresRef.getSelectedRow();
            if( filaselec>=0){   
                ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
                    if(eliminar == 0 ){
        LAB_Valores_Referenciales obj=new LAB_Valores_Referenciales();
                obj.setCod_valores_refe_resul(tb_ValoresRef.getValueAt(filaselec, 0).toString());
                if(obj.LAB_Valores_Ref_eliminar())
                {
                    DefaultTableModel modelo = (DefaultTableModel)tb_ValoresRef.getModel(); 
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                    limpiar();
                    enableDatos();
                }
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione un registro a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione un registro eliminar");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tb_ValoresRefMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ValoresRefMouseClicked
        // TODO add your handling code here:
        tb_ValoresRef.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                    try{
                        enableDatos();
                        txtGuarModif.setText("M");
                        int filaselec=tb_ValoresRef.getSelectedRow();
                        txtCodigoValorRef.setText(tb_ValoresRef.getValueAt(filaselec, 0).toString());
                        txtCodigoEsquema.setText(tb_ValoresRef.getValueAt(filaselec, 1).toString());
                        if(tb_ValoresRef.getValueAt(filaselec, 2).toString().equalsIgnoreCase("S")){
                            chTodos.setSelected(true);
                            btnFabricante.setEnabled(false);
                        }else{
                            chTodos.setSelected(false);
                            btnFabricante.setEnabled(true);
                        }
                        lblCodFabr.setText(tb_ValoresRef.getValueAt(filaselec, 3).toString());
                        txtFabricante.setText(tb_ValoresRef.getValueAt(filaselec, 4).toString());
                        txtDesdeAnio.setText(tb_ValoresRef.getValueAt(filaselec, 5).toString());
                        txtDesdeM.setText(tb_ValoresRef.getValueAt(filaselec, 6).toString());
                        txtDesdeDias.setText(tb_ValoresRef.getValueAt(filaselec, 7).toString());
                        txtHastaAnio.setText(tb_ValoresRef.getValueAt(filaselec, 8).toString());
                        txtHastaMeses.setText(tb_ValoresRef.getValueAt(filaselec, 9).toString());
                        txtHastaDias.setText(tb_ValoresRef.getValueAt(filaselec, 10).toString());
                        if(tb_ValoresRef.getValueAt(filaselec, 11).toString().equalsIgnoreCase("M")){
                            rbMasculino.setSelected(true);
                            chEmbarazada.setEnabled(false);
                        }else{
                            rbFemenino.setSelected(true);
                            chEmbarazada.setEnabled(true);
                        }
                        if(tb_ValoresRef.getValueAt(filaselec, 12).toString().equalsIgnoreCase("S")){
                            chEmbarazada.setSelected(true);
                        }else{
                            chEmbarazada.setSelected(false);
                        }
                        if(tb_ValoresRef.getValueAt(filaselec, 15).toString().length()>0 ){
                            txtMin.setEnabled(false);
                            txtMax.setEnabled(false);
                            txtMin.setText("");
                            txtMax.setText("");
                            txtResultado.setEnabled(true);
                            txtResultado.setText(tb_ValoresRef.getValueAt(filaselec, 15).toString());
                        }else{
                            txtMin.setText(tb_ValoresRef.getValueAt(filaselec, 13).toString());
                            txtMax.setText(tb_ValoresRef.getValueAt(filaselec, 14).toString());
                            txtMin.setEnabled(true);
                            txtMin.setEnabled(true);
                            txtResultado.setText("");
                            txtResultado.setEnabled(false);
                            
                        }
                        
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }});
    }//GEN-LAST:event_tb_ValoresRefMouseClicked

    private void txtCodigoValorRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoValorRefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoValorRefActionPerformed

    private void txtNombreResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreResultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreResultadoActionPerformed

    private void btnFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFabricanteActionPerformed
buscar_fabricante.setVisible(true);
LAB_Fabricante_cargar();
LAB_Fabricante_formato();
    }//GEN-LAST:event_btnFabricanteActionPerformed

    private void chTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chTodosItemStateChanged

        if(chTodos.isSelected()){
             
            btnFabricante.setEnabled(false);
            LAB_Valores_Referenciales vr=new LAB_Valores_Referenciales();
            lblCodFabr.setText(vr.LAB_Fabricante_buscar_cod("todo"));
            LAB_Valores_Referenciales vr1=new LAB_Valores_Referenciales();
            txtFabricante.setText(vr1.LAB_Fabricante_buscar_fab("todo"));
      
        }else{
            btnFabricante.setEnabled(true);
            lblCodFabr.setText("");
            txtFabricante.setText("");
            
        }
    }//GEN-LAST:event_chTodosItemStateChanged

    private void txtResultadoDefecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResultadoDefecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtResultadoDefecActionPerformed

    private void tb_FabricanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_FabricanteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_FabricanteMouseClicked

    private void tb_FabricanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_FabricanteKeyPressed
         char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                     try{
                        buscar_fabricante.setVisible(false);
                        int filaselec=tb_Fabricante.getSelectedRow();
                       
                        lblCodFabr.setText(tb_Fabricante.getValueAt(filaselec, 1).toString());
                        txtFabricante.setText(tb_Fabricante.getValueAt(filaselec, 2).toString());
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
    }//GEN-LAST:event_tb_FabricanteKeyPressed

    private void txtBuscarFabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarFabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFabActionPerformed

    private void txtBuscarFabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFabKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnBuscarFab.doClick();
                }
    }//GEN-LAST:event_txtBuscarFabKeyPressed

    private void txtBuscarFabKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFabKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarFabKeyTyped

    private void btnBuscarFabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFabActionPerformed
     String consulta="";
        try {
            tb_Fabricante.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","fabricante"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            LAB_Valores_Referenciales obj=new LAB_Valores_Referenciales();
                    consulta="exec sp_LAB_VALORES_Fabricante_buscar ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarFab.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Fabricante.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Fabricante.setRowSorter(elQueOrdena);
            this.tb_Fabricante.setModel(m);
            
            LAB_Fabricante_formato();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarFabActionPerformed

    private void rbFemeninoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rbFemeninoPropertyChange

    }//GEN-LAST:event_rbFemeninoPropertyChange

    private void rbMasculinoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_rbMasculinoPropertyChange

    }//GEN-LAST:event_rbMasculinoPropertyChange

    private void rbFemeninoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbFemeninoMouseClicked
        chEmbarazada.setEnabled(true);
    }//GEN-LAST:event_rbFemeninoMouseClicked

    private void rbMasculinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMasculinoMouseClicked
              chEmbarazada.setSelected(false);
        chEmbarazada.setEnabled(false);
    }//GEN-LAST:event_rbMasculinoMouseClicked

    private void txtMinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtMinPropertyChange

    }//GEN-LAST:event_txtMinPropertyChange

    private void txtMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinKeyTyped
      char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtMin.getText().contains(".")){
            evt.consume();            
        }
        
    }//GEN-LAST:event_txtMinKeyTyped

    private void txtMaxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaxKeyTyped
    char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !='.'){
            evt.consume();            
        }
        if(tecla =='.' && txtMax.getText().contains(".")){
            evt.consume();            
        }
    }//GEN-LAST:event_txtMaxKeyTyped

    private void txtMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMinKeyReleased
            if (txtMin.getText().length() > 0 || txtMax.getText().length() > 0) {
            txtResultado.setEnabled(false);
            txtResultado.setText("");
                    }
       else{
          txtResultado.setEnabled(true);
          txtResultado.setText("");
    }
    }//GEN-LAST:event_txtMinKeyReleased

    private void txtMaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaxKeyReleased
           if (txtMin.getText().length() > 0 || txtMax.getText().length() > 0) {
            txtResultado.setEnabled(false);
            txtResultado.setText("");
                    }
       else{
          txtResultado.setEnabled(true);
          txtResultado.setText("");
    }
    }//GEN-LAST:event_txtMaxKeyReleased

    private void txtDesdeAnioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeAnioKeyTyped
             char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtDesdeAnio.getText().length()>1)
    {
evt.consume();
}
    }//GEN-LAST:event_txtDesdeAnioKeyTyped

    private void txtDesdeMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeMKeyTyped
              char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtDesdeM.getText().length()>1 )
    {
evt.consume();
}
    }//GEN-LAST:event_txtDesdeMKeyTyped

    private void txtDesdeDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeDiasKeyTyped
                  char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtDesdeDias.getText().length()>1 )
    {
evt.consume();
}
    }//GEN-LAST:event_txtDesdeDiasKeyTyped

    private void txtHastaAnioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaAnioKeyTyped
                   char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtHastaAnio.getText().length()>1 )
    {
evt.consume();
}
    }//GEN-LAST:event_txtHastaAnioKeyTyped

    private void txtHastaMesesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaMesesKeyTyped
                 char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtHastaMeses.getText().length()>1 )
    {
evt.consume();
}
    }//GEN-LAST:event_txtHastaMesesKeyTyped

    private void txtHastaDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaDiasKeyTyped
                  char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
        if (txtHastaDias.getText().length()>1 )
    {
evt.consume();
}
    }//GEN-LAST:event_txtHastaDiasKeyTyped

    private void txtResultadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtResultadoKeyReleased
      if (txtResultado.getText().length() > 0) {
            txtMin.setEnabled(false);
            txtMax.setEnabled(false);
            txtMin.setText("");
            txtMax.setText("");
                    }
       else{
         txtMin.setEnabled(true);
            txtMax.setEnabled(true);
            txtMin.setText("");
            txtMax.setText("");
    }
    }//GEN-LAST:event_txtResultadoKeyReleased

    private void txtDesdeMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeMKeyReleased
        if(Integer.parseInt(txtDesdeM.getText())>12){
            txtDesdeM.setText("");
        }
    }//GEN-LAST:event_txtDesdeMKeyReleased

    private void txtHastaMesesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaMesesKeyReleased
      if(Integer.parseInt(txtHastaMeses.getText())>12){
            txtHastaMeses.setText("");
        }
    }//GEN-LAST:event_txtHastaMesesKeyReleased

    private void txtDesdeDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesdeDiasKeyReleased
        if(Integer.parseInt(txtDesdeDias.getText())>31){
            txtDesdeDias.setText("");
        }
    }//GEN-LAST:event_txtDesdeDiasKeyReleased

    private void txtHastaDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHastaDiasKeyReleased
        if(Integer.parseInt(txtHastaDias.getText())>31){
            txtHastaDias.setText("");
        }
    }//GEN-LAST:event_txtHastaDiasKeyReleased
    public void enableDatos(){
   txtMin.setEnabled(true);
   txtMax.setEnabled(true);
   txtResultado.setEnabled(true);
}
    public void limpiar(){
      //codigo
        LAB_Valores_Referenciales u=new LAB_Valores_Referenciales();
        txtCodigoValorRef.setText(u.LAB_Valores_Ref_generarid());
   if(txtCodigoValorRef.getText().equalsIgnoreCase("")){
        txtCodigoValorRef.setText("VR00000000000000000000001");
    }
   lblCodFabr.setText("");
   txtFabricante.setText("");
   txtDesdeAnio.setText("");
   txtDesdeM.setText("");
   txtDesdeDias.setText("");
   txtHastaAnio.setText("");
   txtHastaMeses.setText("");
   txtHastaDias.setText("");
   chTodos.setSelected(false);
   rbMasculino.setSelected(true);
   chEmbarazada.setEnabled(false);
   chEmbarazada.setSelected(false);
   txtMin.setText("");
   txtMax.setText("");
   txtResultado.setText("");
        txtGuarModif.setText("G");
}
    public void guardarTabla(){
        try {
String cod_valores_refe_resul,cod_esquema_resul,estado_todos_fabricantes,
cod_fabricante_producto_mh,fabr,ini_anio,ini_mes,ini_dia,fin_anio,fin_mes,fin_dia,genero,estado_clinico_ref,valor_minimo,valor_maximo,
valor_texto_referencia;

            m=(DefaultTableModel) tb_ValoresRef.getModel();
            cod_valores_refe_resul=txtCodigoValorRef.getText();
            cod_esquema_resul=txtCodigoEsquema.getText();
            if(chTodos.isSelected()){
                            estado_todos_fabricantes="S";
                        }else{
                          estado_todos_fabricantes="N";  
                        }
            cod_fabricante_producto_mh=lblCodFabr.getText();
            DecimalFormat df=new DecimalFormat("00");
            fabr=txtFabricante.getText();
            ini_anio=df.format(Integer.parseInt(txtDesdeAnio.getText().toString()));
            ini_mes=df.format(Integer.parseInt(txtDesdeM.getText().toString()));
            ini_dia=df.format(Integer.parseInt(txtDesdeDias.getText().toString()));
            fin_anio=df.format(Integer.parseInt(txtHastaAnio.getText().toString()));
            fin_mes=df.format(Integer.parseInt(txtHastaMeses.getText().toString()));
            fin_dia=df.format(Integer.parseInt(txtHastaDias.getText().toString()));
           if(rbMasculino.isSelected()){
                  genero="M";
            }else{
             genero="F";}
             if(chEmbarazada.isSelected()){
            estado_clinico_ref="S";
                        }else{
            estado_clinico_ref="N";  
                        }
             valor_minimo=txtMin.getText();
             valor_maximo=txtMax.getText();
             valor_texto_referencia=txtResultado.getText();
             
              m=(DefaultTableModel) tb_ValoresRef.getModel();
           String filaelemento[]={cod_valores_refe_resul,cod_esquema_resul,estado_todos_fabricantes,
cod_fabricante_producto_mh,fabr,ini_anio,ini_mes,ini_dia,fin_anio,fin_mes,fin_dia,genero,estado_clinico_ref,
valor_minimo,valor_maximo,valor_texto_referencia};
               m.addRow(filaelemento);
               formato_esquema();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_VALORES_REFERENCIALES_DETALLE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarFab;
    public static javax.swing.JButton btnFabricante;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JDialog buscar_fabricante;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chEmbarazada;
    private javax.swing.JCheckBox chTodos;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblCodFabr;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelEsquema;
    private javax.swing.JPanel panelValores;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    public static javax.swing.JTable tb_Fabricante;
    public static javax.swing.JTable tb_ValoresRef;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtAbreviatura;
    public static javax.swing.JTextField txtAnalisis;
    private javax.swing.JTextField txtBuscarFab;
    public static javax.swing.JTextField txtClasificacion;
    public static javax.swing.JTextField txtCodigoEsquema;
    private javax.swing.JTextField txtCodigoValorRef;
    private javax.swing.JTextField txtDesdeAnio;
    private javax.swing.JTextField txtDesdeDias;
    private javax.swing.JTextField txtDesdeM;
    public static javax.swing.JTextField txtFabricante;
    private javax.swing.JTextField txtGuarModif;
    private javax.swing.JTextField txtHastaAnio;
    private javax.swing.JTextField txtHastaDias;
    private javax.swing.JTextField txtHastaMeses;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    public static javax.swing.JTextField txtNombreResultado;
    private javax.swing.JTextField txtResultado;
    public static javax.swing.JTextField txtResultadoDefec;
    public static javax.swing.JTextField txtUnidadMedida;
    public static javax.swing.JTextField txtUnidadOrganica;
    // End of variables declaration//GEN-END:variables
}
