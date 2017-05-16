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
import modelos.Usuario;
import servicios.Conexion;
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_VALORES_REFERENCIALES_ESQUEMA extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_VALORES_REFERENCIALES_ESQUEMA() {
        initComponents();
        c.conectar();
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/laboratorio.png")).getImage());
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        panelAnalisis.setBackground(Color.white); 
       
        btnAsig.setVisible(false);
        txtCodAnalisis.setVisible(false);
        txtCodClasificacion.setVisible(false);
        txtCodigo.setVisible(false);
        
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_unidad.setLocationRelativeTo(null);
        buscar_unidad.getContentPane().setBackground(Color.white);
        LAB_Unidad_Medida_cargar();
        LAB_Unidad_Medida_formato();
        formato_esquema();
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
          //codigo
        LAB_Esquema_Resultado u=new LAB_Esquema_Resultado();
        txtCodigo.setText(u.LAB_Esquema_Resultado_generarid());
   if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("ER000000000000000001");
    }
       //para limpiar el txt al darle click
 txtBuscar.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscar.setText("");
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
                frm_LAB_VALORES_REFERENCIALES_INGRESO vr=new frm_LAB_VALORES_REFERENCIALES_INGRESO();
                vr.setVisible(true);
                String u=lblUsu.getText();
                             frm_LAB_VALORES_REFERENCIALES_INGRESO.lblUsu.setText(u);
            }
        });
        
        
    }
   
    public void LAB_Unidad_Medida_cargar(){
    try {
             String titulos[]={"Nº","Código","Descripción de la UM"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_listar";
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
            tb_Unidad_Medida.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Unidad_Medida.setRowSorter(elQueOrdena);
            this.tb_Unidad_Medida.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Unidad_Medida_formato(){
    tb_Unidad_Medida.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Unidad_Medida.getColumnModel().getColumn(1).setPreferredWidth(180);
    tb_Unidad_Medida.getColumnModel().getColumn(2).setPreferredWidth(220);
    tb_Unidad_Medida.getSelectionModel().setSelectionInterval(0, 0);
            tb_Unidad_Medida.requestFocus();
}
    
    public void formato_esquema(){
            tb_Esquema.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Esquema.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Esquema.getColumnModel().getColumn(4).setMinWidth(0);
    tb_Esquema.getColumnModel().getColumn(4).setMaxWidth(0);
    }
    public void refrescartabla(){
        
           String consulta="";
           try {
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(new DefaultTableModel());
            String titulos[]={"cod esquema","Nombre de Resultado","Resultado por Defecto" ,
"Tipo Esquema","cod Uni","Unidad de Medida","Valores","Resultados"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_ESQUEMA_RESULTADO_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtCodAnalisis.getText());
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
               
                m.addRow(fila);
                c++;
            }
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setRowSorter(elQueOrdena);
            frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.setModel(m);
             frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(0).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(0).setMaxWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(4).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_ESQUEMA.tb_Esquema.getColumnModel().getColumn(4).setMaxWidth(0);
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

        buscar_unidad = new javax.swing.JDialog();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Unidad_Medida = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            btnBuscar1 = new javax.swing.JButton();
            jpanel = new javax.swing.JPanel();
            titulo5 = new javax.swing.JLabel();
            jLabel14 = new javax.swing.JLabel();
            lblFecha = new javax.swing.JLabel();
            jLabel15 = new javax.swing.JLabel();
            lblHora = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jScrollPane6 = new javax.swing.JScrollPane();
            tb_Esquema = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                panelAnalisis = new javax.swing.JPanel();
                jLabel23 = new javax.swing.JLabel();
                txtAnalisis = new javax.swing.JTextField();
                jLabel19 = new javax.swing.JLabel();
                txtUnidadOrganica = new javax.swing.JTextField();
                txtClasificacion = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                txtAbreviatura = new javax.swing.JTextField();
                txtCodigo = new javax.swing.JTextField();
                txtCodClasificacion = new javax.swing.JTextField();
                txtCodAnalisis = new javax.swing.JTextField();
                btnAsig = new javax.swing.JButton();
                jLabel24 = new javax.swing.JLabel();

                buscar_unidad.setMinimumSize(new java.awt.Dimension(380, 452));

                txtBuscar.setForeground(new java.awt.Color(0, 51, 51));
                txtBuscar.setText("Ingresar Unidad de Medida");
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

                jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                jLabel1.setText("Búsqueda ");

                tb_Unidad_Medida.setModel(new javax.swing.table.DefaultTableModel(
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
                tb_Unidad_Medida.setRowHeight(25);
                tb_Unidad_Medida.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_Unidad_MedidaMouseClicked(evt);
                    }
                });
                tb_Unidad_Medida.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_Unidad_MedidaKeyPressed(evt);
                    }
                });
                jScrollPane1.setViewportView(tb_Unidad_Medida);

                btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                btnBuscar1.setBorder(null);
                btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscar1ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout buscar_unidadLayout = new javax.swing.GroupLayout(buscar_unidad.getContentPane());
                buscar_unidad.getContentPane().setLayout(buscar_unidadLayout);
                buscar_unidadLayout.setHorizontalGroup(
                    buscar_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_unidadLayout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(buscar_unidadLayout.createSequentialGroup()
                        .addGroup(buscar_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscar_unidadLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(buscar_unidadLayout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(jLabel1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                buscar_unidadLayout.setVerticalGroup(
                    buscar_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_unidadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(buscar_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setMinimumSize(new java.awt.Dimension(930, 680));

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText("Esquema Análisis");
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

                javax.swing.GroupLayout jpanelLayout = new javax.swing.GroupLayout(jpanel);
                jpanel.setLayout(jpanelLayout);
                jpanelLayout.setHorizontalGroup(
                    jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titulo5))
                        .addGap(531, 531, 531)
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
                        .addGap(2, 2, 2)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/modificar16x16.png"))); // NOI18N
                jLabel5.setText("Asignar Valor Ref (Enter)");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(607, 607, 607)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
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
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                );

                tb_Esquema.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                tb_Esquema.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "", "Nombre del Resulltado", "Resultado por Defecto", "Tipo de Esquema", "codmed", "Descripción Medida", "Valores", "Resultados"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
                tb_Esquema.setRowHeight(20);
                tb_Esquema.setSelectionBackground(new java.awt.Color(2, 67, 115));
                tb_Esquema.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_EsquemaMouseClicked(evt);
                    }
                });
                tb_Esquema.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        tb_EsquemaKeyPressed(evt);
                    }
                });
                jScrollPane6.setViewportView(tb_Esquema);
                if (tb_Esquema.getColumnModel().getColumnCount() > 0) {
                    tb_Esquema.getColumnModel().getColumn(0).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(1).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(2).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(3).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(4).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(5).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(6).setResizable(false);
                    tb_Esquema.getColumnModel().getColumn(7).setResizable(false);
                }

                panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder("Análisis"));

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
                        .addGap(22, 22, 22)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUnidadOrganica, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGap(52, 52, 52)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAbreviatura, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE))
                );
                panelAnalisisLayout.setVerticalGroup(
                    panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbreviatura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUnidadOrganica, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                );

                txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                txtCodigo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodigoActionPerformed(evt);
                    }
                });

                btnAsig.setText("Asignar Valor Referencial");
                btnAsig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnAsig.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnAsigActionPerformed(evt);
                    }
                });

                jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel24.setText("   Seleccione un Esquema ");

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAsig, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6)
                            .addComponent(panelAnalisis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAsig)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

       dispose();
       frm_LAB_VALORES_REFERENCIALES_INGRESO er=new frm_LAB_VALORES_REFERENCIALES_INGRESO();
       er.setVisible(true);
       String u=lblUsu.getText();
                             frm_LAB_VALORES_REFERENCIALES_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
         char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
      

    }//GEN-LAST:event_txtBuscarKeyTyped

    private void tb_Unidad_MedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Unidad_MedidaMouseClicked

    }//GEN-LAST:event_tb_Unidad_MedidaMouseClicked

    private void tb_Unidad_MedidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Unidad_MedidaKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                buscar_unidad.setVisible(false);
                int filaselec=tb_Unidad_Medida.getSelectedRow();
               
            }
            catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Unidad_MedidaKeyPressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_Unidad_Medida.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Descripción de la UM"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            LAB_Esquema_Resultado obj=new LAB_Esquema_Resultado();
            consulta="exec sp_LAB_UNIDAD_MEDIDA_RESULTADO_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                m.addRow(fila);
                c++;
            }
            tb_Unidad_Medida.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Unidad_Medida.setRowSorter(elQueOrdena);
            this.tb_Unidad_Medida.setModel(m);

LAB_Unidad_Medida_formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void tb_EsquemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EsquemaMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tb_EsquemaMouseClicked

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed
  public void formato_valorref(){
     frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(4).setPreferredWidth(200);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(5).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(6).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(7).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(8).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(9).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(10).setPreferredWidth(50);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(11).setPreferredWidth(60);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(12).setPreferredWidth(80);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(13).setPreferredWidth(80);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(14).setPreferredWidth(80);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(15).setPreferredWidth(154);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(0).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(0).setMaxWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(1).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(1).setMaxWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(2).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(2).setMaxWidth(0);
     frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(3).setMinWidth(0);
    frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.getColumnModel().getColumn(3).setMaxWidth(0);
    }
    private void btnAsigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsigActionPerformed
        try{
           
            int filaselec=tb_Esquema.getSelectedRow();
            if( filaselec>=0){  
             
              frm_LAB_VALORES_REFERENCIALES_DETALLE vr=new frm_LAB_VALORES_REFERENCIALES_DETALLE();
                    vr.setVisible(true);
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtAnalisis.setText(txtAnalisis.getText());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtAbreviatura.setText(txtAbreviatura.getText());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtUnidadOrganica.setText(txtUnidadOrganica.getText());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtClasificacion.setText(txtClasificacion.getText());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtCodigoEsquema.setText(tb_Esquema.getValueAt(filaselec, 0).toString());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtNombreResultado.setText(tb_Esquema.getValueAt(filaselec, 1).toString());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtResultadoDefec.setText(tb_Esquema.getValueAt(filaselec, 2).toString());
                    frm_LAB_VALORES_REFERENCIALES_DETALLE.txtUnidadMedida.setText(tb_Esquema.getValueAt(filaselec, 5).toString());
                     String u=frm_LAB_VALORES_REFERENCIALES_ESQUEMA.lblUsu.getText();
                             frm_LAB_VALORES_REFERENCIALES_DETALLE.lblUsu.setText(u);
                    //tabla
                           String consulta="";
            frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.setModel(new DefaultTableModel());
            String titulos[]={"cod_valores_refe_resul","cod_esquema_resul","estado_todos_fabricantes",
"cod_fabricante_producto_mh","Nombre del Fabricante","Año","Meses","Dias","Año","Meses","Dias","M / F","Embarazo",
"Mínimo","Máximo","Resultado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[17];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_VALORES_REF_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, tb_Esquema.getValueAt(filaselec, 0).toString());
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
            frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.setRowSorter(elQueOrdena);
            frm_LAB_VALORES_REFERENCIALES_DETALLE.tb_ValoresRef.setModel(m);
            formato_valorref();
           
                    
                    
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione un Esquema");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAsigActionPerformed

    private void tb_EsquemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_EsquemaKeyPressed
          char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnAsig.doClick();
                }
    }//GEN-LAST:event_tb_EsquemaKeyPressed
    public void enableDatos(){

}
    public void limpiar(){
    //codigo
        LAB_Esquema_Resultado u=new LAB_Esquema_Resultado();
        txtCodigo.setText(u.LAB_Esquema_Resultado_generarid());
   if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("ER000000000000000001");
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
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_VALORES_REFERENCIALES_ESQUEMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_VALORES_REFERENCIALES_ESQUEMA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsig;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JDialog buscar_unidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelAnalisis;
    public static javax.swing.JTable tb_Esquema;
    public static javax.swing.JTable tb_Unidad_Medida;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtAbreviatura;
    public static javax.swing.JTextField txtAnalisis;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtClasificacion;
    public static javax.swing.JTextField txtCodAnalisis;
    public static javax.swing.JTextField txtCodClasificacion;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtUnidadOrganica;
    // End of variables declaration//GEN-END:variables
}
