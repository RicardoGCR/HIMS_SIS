
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
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
import modelos.LABORATORIO.LAB_Toma_Muestra_Cabecera;
import modelos.LABORATORIO.LAB_Toma_Muestra_Detalle;
import modelos.LABORATORIO.Render_Checkbox;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author
 */
public class frm_LAB_TOMA_MUESTRA_DETALLE extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_TOMA_MUESTRA_DETALLE() {
        initComponents();
        c.conectar();
//        this.setExtendedState(MAXIMIZED_BOTH);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
                
        h1 = new Thread(this);
        h1.start();
        panelPaciente.setBackground(Color.white); 
        panelCabecera.setBackground(Color.white); 
        personal.getContentPane().setBackground(Color.white);
        personal.setLocationRelativeTo(null);
        contenedor.getContentPane().setBackground(Color.white);
        contenedor.setLocationRelativeTo(null);
        muestras.getContentPane().setBackground(Color.white);
        muestras.setLocationRelativeTo(null);
        Unidad_Organica.getContentPane().setBackground(Color.white);
        Unidad_Organica.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white); 
        panelMuestras.setBackground(Color.white);
        jPanel3.setBackground(Color.white);
       
      date.setBackground(Color.white);
      spHora.setBackground(Color.white);
   
        setLocationRelativeTo(null);//en el centro
      setResizable(false);//para que no funcione el boton maximizar
       //OCULTAR
      lblServicio.setVisible(false);
      lblCodNomen.setVisible(false);
      lblExa.setVisible(false);
      lblId_cod_doc_det.setVisible(false);
      lblDni.setVisible(false);
      lblid_preventa.setVisible(false);
      lblCodMuestra.setVisible(false);
      lblCodContenedor.setVisible(false);
      lblArId.setVisible(false);
      lblCodPerSolicita.setVisible(false);
        panelbotones.setBackground(Color.white);
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
       
        lblFechaEmi.setText(fechaActual());
       
   Muestras_formato();
       LAB_subdetalle_formato();
 
//       //para limpiar el txt al darle click
// txtBuscarUni.addFocusListener(new FocusListener() {
//    @Override
//    public void focusGained(FocusEvent e) {
//  txtBuscarUni.setText("");
//    }
//
//    @Override
//    public void focusLost(FocusEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//} );
// //para limpiar el txt al darle click
// txtBuscar1.addFocusListener(new FocusListener() {
//    @Override
//    public void focusGained(FocusEvent e) {
//  txtBuscar1.setText("");
//    }
//
//    @Override
//    public void focusLost(FocusEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//} );
   //salir presionando escape
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){
                dispose();
            } });
    }
    
    
 public void LAB_subdetalle_formato(){
//    tb_subdetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
//    tb_subdetalle.getColumnModel().getColumn(1).setPreferredWidth(100);
//    tb_subdetalle.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb_MuestrasRegis.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_MuestrasRegis.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_MuestrasRegis.getColumnModel().getColumn(5).setPreferredWidth(150);
    tb_MuestrasRegis.getColumnModel().getColumn(6).setPreferredWidth(80);
    tb_MuestrasRegis.getColumnModel().getColumn(7).setPreferredWidth(150);
    //Ocultar
         tb_MuestrasRegis.getColumnModel().getColumn(0).setMinWidth(0);
    tb_MuestrasRegis.getColumnModel().getColumn(0).setMaxWidth(0);
     tb_MuestrasRegis.getColumnModel().getColumn(1).setMinWidth(0);
    tb_MuestrasRegis.getColumnModel().getColumn(1).setMaxWidth(0);
         tb_MuestrasRegis.getColumnModel().getColumn(2).setMinWidth(0);
    tb_MuestrasRegis.getColumnModel().getColumn(2).setMaxWidth(0);
}
   public void LAB_Contenedor_cargar(){
    try {
             String titulos[]={"Nº","Código","Contenedor","Material"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_CONTENEDOR_MUESTRA_listar";
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
            tb_Contenedor.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Contenedor.setRowSorter(elQueOrdena);
            this.tb_Contenedor.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Contenedor_formato(){
    tb_Contenedor.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Contenedor.getColumnModel().getColumn(1).setPreferredWidth(70);
    tb_Contenedor.getColumnModel().getColumn(2).setPreferredWidth(175);
    tb_Contenedor.getColumnModel().getColumn(3).setPreferredWidth(175);
    tb_Contenedor.getSelectionModel().setSelectionInterval(0, 0);
            tb_Contenedor.requestFocus();
}
    
    public void LAB_Unidad_Organica_cargar(){
     String consulta="";
        try {
            tb_Unidad_Organica.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Código","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
            consulta="exec sp_LAB_AREA ?,?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblServicio.getText());
            cmd.setString(2, "");
            cmd.setString(3, "1");
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
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
}
    public void LAB_Unidad_Organica_formato(){
    tb_Unidad_Organica.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Unidad_Organica.getColumnModel().getColumn(2).setPreferredWidth(60);
    tb_Unidad_Organica.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Unidad_Organica.getColumnModel().getColumn(4).setPreferredWidth(180);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        muestras = new javax.swing.JDialog();
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
            contenedor = new javax.swing.JDialog();
            jScrollPane3 = new javax.swing.JScrollPane();
            tb_Contenedor = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                btnBuscar1 = new javax.swing.JButton();
                txtBuscar1 = new javax.swing.JTextField();
                jpanel1 = new javax.swing.JPanel();
                titulo6 = new javax.swing.JLabel();
                jLabel18 = new javax.swing.JLabel();
                Unidad_Organica = new javax.swing.JDialog();
                jScrollPane7 = new javax.swing.JScrollPane();
                tb_Unidad_Organica = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    txtBuscarUni = new javax.swing.JTextField();
                    btnBuscar2 = new javax.swing.JButton();
                    jpanel3 = new javax.swing.JPanel();
                    titulo8 = new javax.swing.JLabel();
                    jLabel19 = new javax.swing.JLabel();
                    jpanel = new javax.swing.JPanel();
                    titulo5 = new javax.swing.JLabel();
                    lblUsu = new javax.swing.JLabel();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel3 = new javax.swing.JLabel();
                    jLabel10 = new javax.swing.JLabel();
                    panelCabecera = new javax.swing.JPanel();
                    jLabel25 = new javax.swing.JLabel();
                    txtPersonalSolicita = new javax.swing.JTextField();
                    jLabel27 = new javax.swing.JLabel();
                    jLabel28 = new javax.swing.JLabel();
                    date = new com.toedter.calendar.JDateChooser();
                    jLabel11 = new javax.swing.JLabel();
                    spHora = new javax.swing.JSpinner();
                    spMin = new javax.swing.JSpinner();
                    jLabel12 = new javax.swing.JLabel();
                    jLabel4 = new javax.swing.JLabel();
                    jLabel5 = new javax.swing.JLabel();
                    txtHabitacion = new javax.swing.JTextField();
                    txtCama = new javax.swing.JTextField();
                    lblHospi = new javax.swing.JLabel();
                    lblHospiServ = new javax.swing.JLabel();
                    panelPaciente = new javax.swing.JPanel();
                    jLabel23 = new javax.swing.JLabel();
                    txtCodigoCPT = new javax.swing.JTextField();
                    jLabel22 = new javax.swing.JLabel();
                    txtidDocumen = new javax.swing.JTextField();
                    txtNomenclatura = new javax.swing.JTextField();
                    jLabel7 = new javax.swing.JLabel();
                    jLabel17 = new javax.swing.JLabel();
                    txtServicio = new javax.swing.JTextField();
                    lblId_cod_doc_det = new javax.swing.JLabel();
                    lblCodNomen = new javax.swing.JLabel();
                    lblCodPerSolicita = new javax.swing.JLabel();
                    lblServicio = new javax.swing.JLabel();
                    lblExa = new javax.swing.JLabel();
                    jPanel3 = new javax.swing.JPanel();
                    jScrollPane1 = new javax.swing.JScrollPane();
                    tb_MuestrasRegis = new javax.swing.JTable();
                    panelMuestras = new javax.swing.JPanel();
                    lblCantidadMues = new javax.swing.JLabel();
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Muestras = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jLabel13 = new javax.swing.JLabel();
                        lblCantidadMues1 = new javax.swing.JLabel();
                        txtContenedor = new javax.swing.JTextField();
                        jLabel6 = new javax.swing.JLabel();
                        txtArea = new javax.swing.JTextField();
                        jLabel8 = new javax.swing.JLabel();
                        lblFechaEmi = new javax.swing.JLabel();
                        lblHoraEmitido = new javax.swing.JLabel();
                        lblH = new javax.swing.JLabel();
                        lblf = new javax.swing.JLabel();
                        jLabel9 = new javax.swing.JLabel();
                        lblCodMuestra = new javax.swing.JLabel();
                        lblCodContenedor = new javax.swing.JLabel();
                        lblArId = new javax.swing.JLabel();
                        jLabel16 = new javax.swing.JLabel();
                        txtCantidadRegis = new javax.swing.JTextField();
                        lblMuestra = new javax.swing.JTextField();
                        panelbotones = new javax.swing.JPanel();
                        btnAgregar = new javax.swing.JButton();
                        btnQuitar = new javax.swing.JButton();
                        jButton2 = new javax.swing.JButton();
                        lblDni = new javax.swing.JLabel();
                        lblid_preventa = new javax.swing.JLabel();
                        jButton3 = new javax.swing.JButton();

                        muestras.setMinimumSize(new java.awt.Dimension(385, 213));

                        javax.swing.GroupLayout muestrasLayout = new javax.swing.GroupLayout(muestras.getContentPane());
                        muestras.getContentPane().setLayout(muestrasLayout);
                        muestrasLayout.setHorizontalGroup(
                            muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 385, Short.MAX_VALUE)
                        );
                        muestrasLayout.setVerticalGroup(
                            muestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGap(0, 213, Short.MAX_VALUE)
                        );

                        personal.setAlwaysOnTop(true);
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
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(26, Short.MAX_VALUE))
                            .addComponent(jpanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        );
                        personalLayout.setVerticalGroup(
                            personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createSequentialGroup()
                                .addComponent(jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbxBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(47, Short.MAX_VALUE))
                        );

                        contenedor.setAlwaysOnTop(true);
                        contenedor.setMinimumSize(new java.awt.Dimension(434, 467));

                        tb_Contenedor.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Contenedor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_Contenedor.setRowHeight(25);
                        tb_Contenedor.getTableHeader().setReorderingAllowed(false);
                        tb_Contenedor.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_ContenedorMouseClicked(evt);
                            }
                        });
                        tb_Contenedor.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_ContenedorKeyPressed(evt);
                            }
                        });
                        jScrollPane3.setViewportView(tb_Contenedor);

                        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                        btnBuscar1.setBorder(null);
                        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnBuscar1ActionPerformed(evt);
                            }
                        });

                        txtBuscar1.setForeground(new java.awt.Color(0, 51, 51));
                        txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBuscar1ActionPerformed(evt);
                            }
                        });
                        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscar1KeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtBuscar1KeyTyped(evt);
                            }
                        });

                        jpanel1.setBackground(new java.awt.Color(2, 67, 115));

                        titulo6.setBackground(new java.awt.Color(0, 102, 102));
                        titulo6.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
                        titulo6.setForeground(new java.awt.Color(255, 255, 255));
                        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo6.setText("Búsqueda");
                        titulo6.setToolTipText("");
                        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel1Layout = new javax.swing.GroupLayout(jpanel1);
                        jpanel1.setLayout(jpanel1Layout);
                        jpanel1Layout.setHorizontalGroup(
                            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel1Layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(titulo6)
                                .addContainerGap(147, Short.MAX_VALUE))
                        );
                        jpanel1Layout.setVerticalGroup(
                            jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanel1Layout.createSequentialGroup()
                                .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        jLabel18.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel18.setText("Búsqueda por Contenedor");

                        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor.getContentPane());
                        contenedor.getContentPane().setLayout(contenedorLayout);
                        contenedorLayout.setHorizontalGroup(
                            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(contenedorLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(contenedorLayout.createSequentialGroup()
                                        .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)))
                                .addContainerGap(32, Short.MAX_VALUE))
                        );
                        contenedorLayout.setVerticalGroup(
                            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                                .addComponent(jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(47, Short.MAX_VALUE))
                        );

                        Unidad_Organica.setTitle("SISGESH .::. BÚSQUEDA DE UNIDAD ORGÁNICA");
                        Unidad_Organica.setAlwaysOnTop(true);
                        Unidad_Organica.setFocusCycleRoot(false);
                        Unidad_Organica.setMinimumSize(new java.awt.Dimension(422, 509));

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
                        tb_Unidad_Organica.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_Unidad_Organica.setRowHeight(25);
                        tb_Unidad_Organica.getTableHeader().setReorderingAllowed(false);
                        tb_Unidad_Organica.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_Unidad_OrganicaMouseClicked(evt);
                            }
                        });
                        tb_Unidad_Organica.addAncestorListener(new javax.swing.event.AncestorListener() {
                            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                            }
                            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                                tb_Unidad_OrganicaAncestorAdded(evt);
                            }
                            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                            }
                        });
                        tb_Unidad_Organica.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Unidad_OrganicaKeyPressed(evt);
                            }
                        });
                        jScrollPane7.setViewportView(tb_Unidad_Organica);

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

                        jpanel3.setBackground(new java.awt.Color(2, 67, 115));

                        titulo8.setBackground(new java.awt.Color(0, 102, 102));
                        titulo8.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
                        titulo8.setForeground(new java.awt.Color(255, 255, 255));
                        titulo8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo8.setText("Búsqueda");
                        titulo8.setToolTipText("");
                        titulo8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

                        javax.swing.GroupLayout jpanel3Layout = new javax.swing.GroupLayout(jpanel3);
                        jpanel3.setLayout(jpanel3Layout);
                        jpanel3Layout.setHorizontalGroup(
                            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo8, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                        );
                        jpanel3Layout.setVerticalGroup(
                            jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        );

                        jLabel19.setFont(new java.awt.Font("Tahoma", 2, 10)); // NOI18N
                        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel19.setText("Búsqueda por Área");

                        javax.swing.GroupLayout Unidad_OrganicaLayout = new javax.swing.GroupLayout(Unidad_Organica.getContentPane());
                        Unidad_Organica.getContentPane().setLayout(Unidad_OrganicaLayout);
                        Unidad_OrganicaLayout.setHorizontalGroup(
                            Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Unidad_OrganicaLayout.createSequentialGroup()
                                .addGroup(Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Unidad_OrganicaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Unidad_OrganicaLayout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addGroup(Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtBuscarUni, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(31, Short.MAX_VALUE))
                            .addGroup(Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        Unidad_OrganicaLayout.setVerticalGroup(
                            Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Unidad_OrganicaLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBuscarUni, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jLabel19)
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(54, Short.MAX_VALUE))
                            .addGroup(Unidad_OrganicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Unidad_OrganicaLayout.createSequentialGroup()
                                    .addComponent(jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 459, Short.MAX_VALUE)))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                        jpanel.setBackground(new java.awt.Color(2, 67, 115));

                        titulo5.setBackground(new java.awt.Color(0, 102, 102));
                        titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                        titulo5.setForeground(new java.awt.Color(255, 255, 255));
                        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        titulo5.setText("Toma de Muestra");
                        titulo5.setToolTipText("");
                        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                                .addGap(483, 483, 483)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(26, Short.MAX_VALUE))
                        );
                        jpanelLayout.setVerticalGroup(
                            jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addComponent(titulo5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpanelLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        );

                        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

                        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                        jLabel3.setText("Salir (Esc)");

                        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
                        jLabel10.setText("Aceptar (Alt+A)");

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(709, 709, 709)
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

                        panelCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder("Toma de Muestra"));

                        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel25.setText("Personal - Solicita Muestra");

                        txtPersonalSolicita.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                        txtPersonalSolicita.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtPersonalSolicita.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtPersonalSolicitaKeyPressed(evt);
                            }
                        });

                        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel27.setText("Fecha de Entrega");

                        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel28.setText("Hora de Entrega");

                        date.setDateFormatString("dd-MM-yyyy");

                        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                        jLabel11.setText("h");

                        spHora.setModel(new javax.swing.SpinnerNumberModel(0, 0, 24, 1));
                        spHora.addChangeListener(new javax.swing.event.ChangeListener() {
                            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                spHoraStateChanged(evt);
                            }
                        });

                        spMin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
                        spMin.addChangeListener(new javax.swing.event.ChangeListener() {
                            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                                spMinStateChanged(evt);
                            }
                        });
                        spMin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                spMinPropertyChange(evt);
                            }
                        });

                        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                        jLabel12.setText("min");

                        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel4.setText("Habitación");

                        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel5.setText("Cama   ");

                        txtHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                        txtHabitacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtHabitacion.setEnabled(false);
                        txtHabitacion.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtHabitacionActionPerformed(evt);
                            }
                        });

                        txtCama.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                        txtCama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCama.setEnabled(false);
                        txtCama.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtCamaActionPerformed(evt);
                            }
                        });

                        lblHospi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblHospi.setText("Hospitalización");

                        lblHospiServ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblHospiServ.setText("jLabel1");

                        javax.swing.GroupLayout panelCabeceraLayout = new javax.swing.GroupLayout(panelCabecera);
                        panelCabecera.setLayout(panelCabeceraLayout);
                        panelCabeceraLayout.setHorizontalGroup(
                            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPersonalSolicita)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                .addGap(26, 26, 26)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addComponent(spHora, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spMin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(22, 22, 22)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHospi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblHospiServ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        );
                        panelCabeceraLayout.setVerticalGroup(
                            panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCabeceraLayout.createSequentialGroup()
                                .addComponent(lblHospi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblHospiServ)
                                .addGap(0, 0, 0)
                                .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel27)
                                                .addComponent(jLabel25))
                                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, 0)
                                        .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPersonalSolicita)
                                            .addComponent(date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panelCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel11)
                                                .addComponent(spHora)
                                                .addComponent(spMin)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelCabeceraLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                        );

                        panelPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Paciente"));

                        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel23.setText("Código CPT");

                        txtCodigoCPT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtCodigoCPT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCodigoCPT.setEnabled(false);

                        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel22.setText("N° de Documento");

                        txtidDocumen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtidDocumen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
                        txtidDocumen.setEnabled(false);

                        txtNomenclatura.setEditable(false);
                        txtNomenclatura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtNomenclatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtNomenclatura.setEnabled(false);

                        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel7.setText("Nomenclatura");

                        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel17.setText("Servicio/Área");

                        txtServicio.setEditable(false);
                        txtServicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                        txtServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtServicio.setEnabled(false);

                        javax.swing.GroupLayout panelPacienteLayout = new javax.swing.GroupLayout(panelPaciente);
                        panelPaciente.setLayout(panelPacienteLayout);
                        panelPacienteLayout.setHorizontalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtidDocumen)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodigoCPT)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(43, Short.MAX_VALUE))
                        );
                        panelPacienteLayout.setVerticalGroup(
                            panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPacienteLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodigoCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtidDocumen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        lblId_cod_doc_det.setText("id_cod_doc_det");

                        lblCodNomen.setText("cod_nomen");

                        lblServicio.setText("Servicio");

                        lblExa.setText("Exa");

                        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Muestras"));

                        tb_MuestrasRegis.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "codMuestra", "codContenedor", "codArea", "Muestra", "Contenedor", "Area", "Cantidad", "Codigo_Barras"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                true, false, false, false, false, false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_MuestrasRegis.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                        tb_MuestrasRegis.getTableHeader().setReorderingAllowed(false);
                        jScrollPane1.setViewportView(tb_MuestrasRegis);

                        panelMuestras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                        lblCantidadMues.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lblCantidadMues.setForeground(new java.awt.Color(204, 0, 0));
                        lblCantidadMues.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        lblCantidadMues.setText("0");

                        tb_Muestras.setModel(new javax.swing.table.DefaultTableModel(
                            new Object [][] {

                            },
                            new String [] {
                                "", "Código", "Muestra"
                            }
                        ) {
                            boolean[] canEdit = new boolean [] {
                                false, false, false
                            };

                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                            }
                        });
                        tb_Muestras.setRowHeight(24);
                        tb_Muestras.setSelectionBackground(new java.awt.Color(2, 67, 115));
                        tb_Muestras.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_MuestrasMouseClicked(evt);
                            }
                            public void mouseReleased(java.awt.event.MouseEvent evt) {
                                tb_MuestrasMouseReleased(evt);
                            }
                        });
                        tb_Muestras.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                                tb_MuestrasPropertyChange(evt);
                            }
                        });
                        tb_Muestras.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_MuestrasKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                tb_MuestrasKeyTyped(evt);
                            }
                        });
                        jScrollPane5.setViewportView(tb_Muestras);

                        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel13.setText("Cantidad");

                        lblCantidadMues1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                        lblCantidadMues1.setForeground(new java.awt.Color(204, 0, 0));
                        lblCantidadMues1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                        lblCantidadMues1.setText("Muestras");

                        javax.swing.GroupLayout panelMuestrasLayout = new javax.swing.GroupLayout(panelMuestras);
                        panelMuestras.setLayout(panelMuestrasLayout);
                        panelMuestrasLayout.setHorizontalGroup(
                            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMuestrasLayout.createSequentialGroup()
                                .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelMuestrasLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelMuestrasLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestrasLayout.createSequentialGroup()
                                                .addComponent(lblCantidadMues)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblCantidadMues1)
                                                .addGap(24, 24, 24))
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                        panelMuestrasLayout.setVerticalGroup(
                            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMuestrasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCantidadMues)
                                    .addComponent(lblCantidadMues1))
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addGap(14, 14, 14))
                        );

                        txtContenedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtContenedor.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtContenedorKeyPressed(evt);
                            }
                        });

                        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel6.setText("Contenedor");

                        txtArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtArea.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtAreaKeyPressed(evt);
                            }
                            public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtAreaKeyReleased(evt);
                            }
                        });

                        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel8.setText("Área");

                        lblFechaEmi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblFechaEmi.setText("jLabel13");

                        lblHoraEmitido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblHoraEmitido.setText("jLabel13");

                        lblH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblH.setText("Hora Emitido");

                        lblf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        lblf.setText("Fecha emitido");

                        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel9.setText("Muestra");

                        lblCodMuestra.setText("Mues");

                        lblCodContenedor.setText("Conte");

                        lblArId.setText("Area");

                        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        jLabel16.setText("Cantidad");

                        txtCantidadRegis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        txtCantidadRegis.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtCantidadRegisKeyPressed(evt);
                            }
                            public void keyTyped(java.awt.event.KeyEvent evt) {
                                txtCantidadRegisKeyTyped(evt);
                            }
                        });

                        lblMuestra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                        lblMuestra.setEnabled(false);

                        panelbotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

                        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                        btnAgregar.setContentAreaFilled(false);
                        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAgregarActionPerformed(evt);
                            }
                        });

                        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                        btnQuitar.setContentAreaFilled(false);
                        btnQuitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnQuitarActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout panelbotonesLayout = new javax.swing.GroupLayout(panelbotones);
                        panelbotones.setLayout(panelbotonesLayout);
                        panelbotonesLayout.setHorizontalGroup(
                            panelbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelbotonesLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(panelbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4))
                        );
                        panelbotonesLayout.setVerticalGroup(
                            panelbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelbotonesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnAgregar)
                                .addGap(18, 18, 18)
                                .addComponent(btnQuitar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                        jPanel3.setLayout(jPanel3Layout);
                        jPanel3Layout.setHorizontalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblCodMuestra)
                                        .addGap(30, 30, 30)
                                        .addComponent(lblCodContenedor)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblArId)))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtContenedor)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                            .addComponent(lblMuestra))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lblf, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                                    .addComponent(lblFechaEmi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(54, 54, 54)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(lblHoraEmitido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblH, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                                .addGap(45, 45, 45))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtCantidadRegis, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panelbotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addContainerGap(33, Short.MAX_VALUE))
                        );
                        jPanel3Layout.setVerticalGroup(
                            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(panelMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblCodMuestra)
                                            .addComponent(lblCodContenedor)
                                            .addComponent(lblArId)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(lblH, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lblf, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(3, 3, 3)
                                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(lblHoraEmitido)
                                                            .addComponent(lblFechaEmi, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lblMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCantidadRegis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(panelbotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3))
                        );

                        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/aceptar16x16.png"))); // NOI18N
                        jButton2.setMnemonic('A');
                        jButton2.setText("Aceptar");
                        jButton2.setToolTipText("Aceptar(Alt+A)");
                        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        jButton2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton2ActionPerformed(evt);
                            }
                        });

                        lblDni.setText("dni");

                        lblid_preventa.setText("preventa");

                        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir16x16.png"))); // NOI18N
                        jButton3.setText("Salir");
                        jButton3.setToolTipText("Salir(Esc)");
                        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        jButton3.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton3ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(lblServicio)
                                        .addGap(61, 61, 61)
                                        .addComponent(lblCodPerSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)
                                        .addComponent(lblCodNomen)
                                        .addGap(105, 105, 105)
                                        .addComponent(lblExa)
                                        .addGap(106, 106, 106)
                                        .addComponent(lblId_cod_doc_det)
                                        .addGap(33, 33, 33)
                                        .addComponent(lblDni)
                                        .addGap(88, 88, 88)
                                        .addComponent(lblid_preventa)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panelPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panelCabecera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(16, 16, 16))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(326, 326, 326)
                                .addComponent(jButton2)
                                .addGap(81, 81, 81)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblId_cod_doc_det)
                                            .addComponent(lblCodNomen)
                                            .addComponent(lblCodPerSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblExa)
                                            .addComponent(lblDni)
                                            .addComponent(lblid_preventa)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblServicio)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

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
            String tipo="",buscar="";

            if(cbxBuscar2.getSelectedIndex()==1){
                tipo="2";
            }else if(cbxBuscar2.getSelectedIndex()==2){
                tipo="3";
            }
            buscar=txtBuscar.getText();
            String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();

            String consulta="exec sp_LAB_PERSONAL ?,?,?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscar);
            cmd.setString(2, "");
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
               
                    personal.setVisible(false);
                    int filaselec=tbPersonal.getSelectedRow();
                    String nombreCompleto=tbPersonal.getValueAt(filaselec, 2).toString()+" "+
                    tbPersonal.getValueAt(filaselec, 3).toString()
                    +" "+tbPersonal.getValueAt(filaselec, 4).toString();
                    txtPersonalSolicita.setText(nombreCompleto);
                    lblCodPerSolicita.setText(tbPersonal.getValueAt(filaselec, 1).toString());
                
            }catch(Exception ex)
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

    private void txtPersonalSolicitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPersonalSolicitaKeyPressed
      char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    personal.setVisible(true);
                    txtBuscar.setText("");
                    Personal_cargar();
                    Personal_formato();
                }
    }//GEN-LAST:event_txtPersonalSolicitaKeyPressed

    private void spHoraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spHoraStateChanged
 
    }//GEN-LAST:event_spHoraStateChanged

    private void spMinStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spMinStateChanged
    }//GEN-LAST:event_spMinStateChanged

    private void spMinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spMinPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_spMinPropertyChange

    private void tb_MuestrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MuestrasMouseClicked
 tb_Muestras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                   int filaselec=tb_Muestras.getSelectedRow();
        
        lblCodMuestra.setText(tb_Muestras.getValueAt(filaselec, 1).toString());
        lblMuestra.setText(tb_Muestras.getValueAt(filaselec, 2).toString());   
                }}});
    }//GEN-LAST:event_tb_MuestrasMouseClicked

    private void tb_MuestrasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MuestrasMouseReleased

    }//GEN-LAST:event_tb_MuestrasMouseReleased

    private void tb_MuestrasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_MuestrasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_MuestrasPropertyChange

    private void tb_MuestrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_MuestrasKeyTyped

    }//GEN-LAST:event_tb_MuestrasKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if(lblMuestra.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this, "Seleccione una Muestra");
            }else if(lblCodContenedor.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this, "Seleccione un Contenedor");
            }else if(lblArId.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(this, "Seleccione un Área");
            }
            else if(txtCantidadRegis.getText().equalsIgnoreCase("")|| Integer.parseInt(txtCantidadRegis.getText())==0){
                JOptionPane.showMessageDialog(this, "Ingrese una Cantidad mayor a 0");
            }else{
                mostrarMuestra();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage()); 
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
 public void mostrarMuestra(){
        
        try {
        String cod_muestra,cod_conten,ar_id,muestram,contenedorm,area,cantidad,cod_barras;

            cod_muestra=lblCodMuestra.getText();
            cod_conten=lblCodContenedor.getText();
            ar_id=lblArId.getText();
            muestram=lblMuestra.getText();
            contenedorm=txtContenedor.getText();
            area=txtArea.getText();
            cantidad=txtCantidadRegis.getText();
            String cod_ba=lblDni.getText();
            
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
            //dni invertido + muestra + examen
            cod_barras=cod_ba.substring(0, 2)+cod_ba.substring(6, 8)+cod_ba.substring(2, 6)+
                    df.format(ndias)
                    +lblCodMuestra.getText().substring(2, 5)
                    +lblCodNomen.getText().substring(2,7);
           
          if(tb_MuestrasRegis.getRowCount()==0){
              muestra=(DefaultTableModel)tb_MuestrasRegis.getModel();
           String filaelemento[]={cod_muestra,cod_conten,ar_id,muestram,contenedorm,area,cantidad,cod_barras};
               muestra.addRow(filaelemento);
               limpiarMuestra();
          }
          else{
           if(repiteMuestra()==true){
               JOptionPane.showMessageDialog(this,"La Muestra ya ha sido ingresada.");   
          } 
           else{
              muestra=(DefaultTableModel) tb_MuestrasRegis.getModel();
           String filaelemento[]={cod_muestra,cod_conten,ar_id,muestram,contenedorm,area,cantidad,cod_barras};
               muestra.addRow(filaelemento); 
               limpiarMuestra();
           }
          }
           
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean repiteMuestra(){
         
         boolean c=false;
         for (int i = 0; i < tb_MuestrasRegis.getRowCount(); i++){    
               if(tb_MuestrasRegis.getValueAt(i, 0).toString().equalsIgnoreCase(lblCodMuestra.getText())){
                    c=true;
			}}
               return c;
     }
    public void limpiarMuestra(){
         lblCodMuestra.setText("");
         lblCodContenedor.setText("");
         lblArId.setText("");
         lblMuestra.setText("");
         txtContenedor.setText("");
         txtArea.setText("");
         txtCantidadRegis.setText("");
        
     }
    
    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
          try{
            int filaselec=tb_MuestrasRegis.getSelectedRow();
            if( filaselec>=0){
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR el Registro?",
                    "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(eliminar == 0 ){
                    DefaultTableModel modelo = (DefaultTableModel)tb_MuestrasRegis.getModel();
                    modelo.removeRow(filaselec);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Registro a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Registro a eliminar");
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void tb_MuestrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_MuestrasKeyPressed
   
    }//GEN-LAST:event_tb_MuestrasKeyPressed

    private void tb_ContenedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ContenedorMouseClicked

    }//GEN-LAST:event_tb_ContenedorMouseClicked

    private void tb_ContenedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ContenedorKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                contenedor.setVisible(false);
                int filaselec=tb_Contenedor.getSelectedRow();
                
                lblCodContenedor.setText(tb_Contenedor.getValueAt(filaselec, 1).toString());
                txtContenedor.setText(tb_Contenedor.getValueAt(filaselec, 2).toString());
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ContenedorKeyPressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_Contenedor.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Contenedor","Material"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
            consulta="exec sp_LAB_CONTENEDOR_MUESTRA_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
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
            tb_Contenedor.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Contenedor.setRowSorter(elQueOrdena);
            this.tb_Contenedor.setModel(m);

            LAB_Contenedor_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Contenedor.getSelectionModel().setSelectionInterval(0, 0);
            tb_Contenedor.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }

    }//GEN-LAST:event_txtBuscar1KeyTyped

    private void txtContenedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContenedorKeyPressed
char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    txtBuscar1.setText("");
        contenedor.setVisible(true);
      LAB_Contenedor_cargar();
                    LAB_Contenedor_formato();
              }
    }//GEN-LAST:event_txtContenedorKeyPressed

    private void tb_Unidad_OrganicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Unidad_OrganicaMouseClicked

    }//GEN-LAST:event_tb_Unidad_OrganicaMouseClicked

    private void tb_Unidad_OrganicaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Unidad_OrganicaKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                Unidad_Organica.setVisible(false);
                int filaselec=tb_Unidad_Organica.getSelectedRow();
                lblArId.setText(tb_Unidad_Organica.getValueAt(filaselec, 1).toString());
                txtArea.setText(tb_Unidad_Organica.getValueAt(filaselec, 4).toString());
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
       String consulta="";
        try {
            tb_Unidad_Organica.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Código","Servicio","Área"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
            consulta="exec sp_LAB_AREA ?,?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, lblServicio.getText());
            cmd.setString(2, txtBuscarUni.getText());
            cmd.setString(3, "2");
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
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

    private void txtAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    txtBuscarUni.setText("");
            Unidad_Organica.setVisible(true);
             LAB_Unidad_Organica_cargar();
                    LAB_Unidad_Organica_formato();
              }
    }//GEN-LAST:event_txtAreaKeyPressed

    private void tb_Unidad_OrganicaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tb_Unidad_OrganicaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Unidad_OrganicaAncestorAdded

    private void txtHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHabitacionActionPerformed

    private void txtCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCamaActionPerformed

    private void txtCantidadRegisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRegisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadRegisKeyPressed

    private void txtCantidadRegisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadRegisKeyTyped
          char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }
    }//GEN-LAST:event_txtCantidadRegisKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(date.getDate()==null){
        JOptionPane.showMessageDialog(rootPane, "Seleccione la Fecha de Entrega del Examen");
    }else if(spHora.getValue().equals(0) && spMin.getValue().equals(0)){
      JOptionPane.showMessageDialog(rootPane, "Ingrese la Hora de Entrega del Examen");
    }else  if(Integer.parseInt(lblCantidadMues.getText())!=tb_MuestrasRegis.getRowCount()){
      JOptionPane.showMessageDialog(rootPane, "Registre las Muestras del Examen");
    }else if(Integer.parseInt(lblCantidadMues.getText())==tb_MuestrasRegis.getRowCount()){
        cargar_detalle();
        cargar_subdetalle();
        int cantidad=0;
                cantidad=(Integer.parseInt(frm_LAB_TOMA_MUESTRA_CABECERA.lblCantidad.getText())
                    -frm_LAB_TOMA_MUESTRA_CABECERA.tb_Detalle.getRowCount());
            frm_LAB_TOMA_MUESTRA_CABECERA.lblCantidad1.setText(String.valueOf(cantidad));
       
        if(cantidad==0){
            frm_LAB_TOMA_MUESTRA_CABECERA.btnAgregar.setEnabled(false);
        }
        else if(cantidad>0){
             frm_LAB_TOMA_MUESTRA_CABECERA.btnAgregar.setEnabled(true);
        }
        frm_LAB_TOMA_MUESTRA_CABECERA.tb_Detalle.getSelectionModel().setSelectionInterval(0, 0);
        frm_LAB_TOMA_MUESTRA_CABECERA.tb_Detalle.requestFocus();

         dispose();
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaKeyReleased
    
     public void cargar_detalle(){
         try {
//            modelo1 = (DefaultTableModel) tablaServiciosBasicosEA.getModel();
       
            //pasar datos a una tabla

            String id_cod_det,cod_exa_ana,cod_caja,cod_cpt,nomenclatura,servicio,cod_per_solicita,nom_per_solicita,
                    fecha_probable_entre,hora_probable_entre,cod_asig_cama_pac,habitacion_hospi,cama_hospi,hospi_serv;

           
            id_cod_det=lblId_cod_doc_det.getText();
            cod_exa_ana=lblExa.getText();
            cod_caja=lblCodNomen.getText();
            cod_cpt=txtCodigoCPT.getText();
            nomenclatura=txtNomenclatura.getText();
            servicio=txtServicio.getText();
            if(lblCodPerSolicita.getText().equalsIgnoreCase("")){
            cod_per_solicita="";
            nom_per_solicita="";
            }else{
            cod_per_solicita=lblCodPerSolicita.getText();
            nom_per_solicita=txtPersonalSolicita.getText();
            }
            DecimalFormat df = new DecimalFormat("00");
                
        int dia,mes,anio;
        dia = date.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes = date.getCalendar().get(Calendar.MONTH) + 1;
        anio = date.getCalendar().get(Calendar.YEAR);
        
        String fecha=df.format(dia)+"/"+df.format(mes)+"/"+anio;
        
            fecha_probable_entre=fecha;
            hora_probable_entre=df.format(spHora.getValue())+":"+df.format(spMin.getValue())+":00";
            
            cod_asig_cama_pac=lblid_preventa.getText();
            habitacion_hospi=txtHabitacion.getText();
            cama_hospi=txtCama.getText();
            hospi_serv=lblHospiServ.getText();

            //Cargar los datos a la otra tabla 
            muestra = (DefaultTableModel) frm_LAB_TOMA_MUESTRA_CABECERA.tb_Detalle.getModel();
           String filaelemento[]={id_cod_det,cod_exa_ana,cod_caja,cod_cpt,nomenclatura,servicio,cod_per_solicita,nom_per_solicita,
                    fecha_probable_entre,hora_probable_entre,cod_asig_cama_pac,habitacion_hospi,cama_hospi,hospi_serv};
               muestra.addRow(filaelemento);

        } catch (Exception e) {
        }
    }
    public void cargar_subdetalle(){
         try {
            
//            modelo1 = (DefaultTableModel) tablaServiciosBasicosEA.getModel();
      
            //pasar datos de una tabla a otra
            for (int i=0;i<tb_MuestrasRegis.getRowCount(); i++){
            String cod_caja,cod_muestra,cod_conten,ar_id,muestram,contenedorm,area,cantidad,cod_barras;

            cod_caja=lblCodNomen.getText();
            cod_muestra=tb_MuestrasRegis.getValueAt(i, 0).toString();
            cod_conten=tb_MuestrasRegis.getValueAt(i, 1).toString();
            ar_id=tb_MuestrasRegis.getValueAt(i, 2).toString();
            muestram=tb_MuestrasRegis.getValueAt(i, 3).toString();
            contenedorm=tb_MuestrasRegis.getValueAt(i, 4).toString();
            area=tb_MuestrasRegis.getValueAt(i, 5).toString();
            cantidad=tb_MuestrasRegis.getValueAt(i, 6).toString();
            cod_barras=tb_MuestrasRegis.getValueAt(i, 7).toString();
            

            //Cargar los datos a la otra tabla 
            muestra = (DefaultTableModel) frm_LAB_TOMA_MUESTRA_CABECERA.tb_Subdetalle.getModel();
           String filaelemento[]={cod_caja,cod_muestra,cod_conten,ar_id,muestram,contenedorm,area,cantidad,cod_barras};
               muestra.addRow(filaelemento);

            }
        } catch (Exception e) {
        }
    }
    public void Personal_cargar(){

    try {
             String titulos[]={"N°","Código","Apellido Paterno","Apellido Materno","Nombres","Cargo"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];
            LAB_Toma_Muestra_Cabecera obj=new LAB_Toma_Muestra_Cabecera();
            
        String consulta="exec sp_LAB_PERSONAL ?,?,?";
       PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
           cmd.setString(1, "");
            cmd.setString(2, "");
            cmd.setString(3, "1");
        ResultSet r=cmd.executeQuery();
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
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
    tbPersonal.getSelectionModel().setSelectionInterval(0, 0);
            tbPersonal.requestFocus();
}
  

   
  
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
           
            lblHoraEmitido.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
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
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_TOMA_MUESTRA_DETALLE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_TOMA_MUESTRA_DETALLE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Unidad_Organica;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cbxBuscar2;
    private javax.swing.JDialog contenedor;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPanel jpanel;
    private javax.swing.JPanel jpanel1;
    private javax.swing.JPanel jpanel2;
    private javax.swing.JPanel jpanel3;
    private javax.swing.JLabel lblArId;
    public static javax.swing.JLabel lblCantidadMues;
    public static javax.swing.JLabel lblCantidadMues1;
    private javax.swing.JLabel lblCodContenedor;
    private javax.swing.JLabel lblCodMuestra;
    public static javax.swing.JLabel lblCodNomen;
    public static javax.swing.JLabel lblCodPerSolicita;
    public static javax.swing.JLabel lblDni;
    public static javax.swing.JLabel lblExa;
    private javax.swing.JLabel lblFechaEmi;
    private javax.swing.JLabel lblH;
    private javax.swing.JLabel lblHoraEmitido;
    public static javax.swing.JLabel lblHospi;
    public static javax.swing.JLabel lblHospiServ;
    public static javax.swing.JLabel lblId_cod_doc_det;
    private javax.swing.JTextField lblMuestra;
    public static javax.swing.JLabel lblServicio;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblf;
    public static javax.swing.JLabel lblid_preventa;
    private javax.swing.JDialog muestras;
    private javax.swing.JPanel panelCabecera;
    private javax.swing.JPanel panelMuestras;
    private javax.swing.JPanel panelPaciente;
    private javax.swing.JPanel panelbotones;
    private javax.swing.JDialog personal;
    public static javax.swing.JSpinner spHora;
    public static javax.swing.JSpinner spMin;
    public static javax.swing.JTable tbPersonal;
    public static javax.swing.JTable tb_Contenedor;
    public static javax.swing.JTable tb_Muestras;
    private javax.swing.JTable tb_MuestrasRegis;
    public static javax.swing.JTable tb_Unidad_Organica;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JLabel titulo7;
    private javax.swing.JLabel titulo8;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscarUni;
    public static javax.swing.JTextField txtCama;
    private javax.swing.JTextField txtCantidadRegis;
    public static javax.swing.JTextField txtCodigoCPT;
    private javax.swing.JTextField txtContenedor;
    public static javax.swing.JTextField txtHabitacion;
    public static javax.swing.JTextField txtNomenclatura;
    private javax.swing.JTextField txtPersonalSolicita;
    public static javax.swing.JTextField txtServicio;
    public static javax.swing.JTextField txtidDocumen;
    // End of variables declaration//GEN-END:variables
}
