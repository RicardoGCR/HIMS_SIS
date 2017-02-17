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
public class frm_LAB_ESQUEMA_RESULTADO extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_ESQUEMA_RESULTADO() {
        initComponents();
        c.conectar();
        txtGuarModif.setVisible(false);
        h1 = new Thread(this);
        h1.start();
        this.getContentPane().setBackground(Color.white); 
        this.getContentPane().setBackground(Color.white); 
        panelAnalisis.setBackground(Color.white); 
        panelEsquema.setBackground(Color.white); 
        
        txtCodAnalisis.setVisible(false);
        txtCodClasificacion.setVisible(false);
        txtCodigo.setVisible(false);
        lblCodMedida.setVisible(false);
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
                frm_LAB_ESQUEMA_RESULTADO_INGRESO er=new frm_LAB_ESQUEMA_RESULTADO_INGRESO();
                er.setVisible(true);
                String u=lblUsu.getText();
                frm_LAB_ESQUEMA_RESULTADO_INGRESO.lblUsu.setText(u);

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
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(new DefaultTableModel());
            String titulos[]={"cod esquema","Nombre de Resultado","Resultado por Defecto" ,
"Tipo Esquema","cod Uni","Unidad de Medida","Valores","Resultados"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[16];

            Usuario obj=new Usuario();
            consulta="exec sp_LAB_ESQUEMA_RESULTADO_buscar ?,?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtCodAnalisis.getText());
             cmd.setInt(2, 1);
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
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setRowSorter(elQueOrdena);
            frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.setModel(m);
             frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(0).setMinWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(0).setMaxWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(4).setMinWidth(0);
    frm_LAB_ESQUEMA_RESULTADO.tb_Esquema.getColumnModel().getColumn(4).setMaxWidth(0);
    } catch (SQLException ex) {
                   Logger.getLogger(frm_LAB_ESQUEMA_RESULTADO.class.getName()).log(Level.SEVERE, null, ex);
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
            btnBuscar = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            jLabel10 = new javax.swing.JLabel();
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
                panelEsquema = new javax.swing.JPanel();
                jLabel21 = new javax.swing.JLabel();
                txtNombreResultado = new javax.swing.JTextField();
                jLabel22 = new javax.swing.JLabel();
                txtResultadoDefec = new javax.swing.JTextField();
                chTitulo = new javax.swing.JCheckBox();
                txtCodigo = new javax.swing.JTextField();
                txtGuarModif = new javax.swing.JTextField();
                txtCodClasificacion = new javax.swing.JTextField();
                txtCodAnalisis = new javax.swing.JTextField();
                txtUnidadMedida = new javax.swing.JTextField();
                jLabel20 = new javax.swing.JLabel();
                btnBuscarUnidad = new javax.swing.JButton();
                lblCodMedida = new javax.swing.JLabel();
                btnGuardar = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();

                buscar_unidad.setTitle("SISGESH .::. Búsqueda de Unidad de Medida");
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
                setTitle("SISGESH .::. Análisis Examen");
                setMinimumSize(new java.awt.Dimension(930, 719));

                jpanel.setBackground(new java.awt.Color(2, 67, 115));

                titulo5.setBackground(new java.awt.Color(0, 102, 102));
                titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                titulo5.setForeground(new java.awt.Color(255, 255, 255));
                titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                titulo5.setText(" Esquema Análisis-Resultado");
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

                btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
                btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
                btnBuscar.setMnemonic('B');
                btnBuscar.setToolTipText("Buscar (Alt+B)");
                btnBuscar.setContentAreaFilled(false);
                btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnBuscar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarActionPerformed(evt);
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
                                .addGap(29, 29, 29)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(titulo5))
                        .addGap(357, 357, 357)
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
                        .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
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

                jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                jLabel10.setText("Buscar (Alt+B)");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))
                );

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
                tb_Esquema.setSelectionBackground(new java.awt.Color(2, 67, 115));
                tb_Esquema.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tb_EsquemaMouseClicked(evt);
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

                panelAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Análisis", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11))); // NOI18N

                jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                jLabel23.setText("Análisis");

                txtAnalisis.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                txtAnalisis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAnalisis.setEnabled(false);

                jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                jLabel19.setText("Nombre de la Unidad");

                txtUnidadOrganica.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                txtUnidadOrganica.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtUnidadOrganica.setEnabled(false);

                txtClasificacion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                txtClasificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtClasificacion.setEnabled(false);

                jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                jLabel7.setText("Clasificación");

                jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                jLabel17.setText("Abrev. Análisis");

                txtAbreviatura.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
                txtAbreviatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                txtAbreviatura.setEnabled(false);

                javax.swing.GroupLayout panelAnalisisLayout = new javax.swing.GroupLayout(panelAnalisis);
                panelAnalisis.setLayout(panelAnalisisLayout);
                panelAnalisisLayout.setHorizontalGroup(
                    panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel19))
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(txtUnidadOrganica, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49)
                        .addGroup(panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(txtAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(txtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelAnalisisLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel7)
                                .addGap(170, 170, 170)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(90, 90, 90))))
                );
                panelAnalisisLayout.setVerticalGroup(
                    panelAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
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

                panelEsquema.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Esquema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 11))); // NOI18N

                jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel21.setText("Nombre del Resultado");

                txtNombreResultado.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

                jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel22.setText("Resultado por Defecto");

                txtResultadoDefec.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

                chTitulo.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                chTitulo.setText("Título");
                chTitulo.addItemListener(new java.awt.event.ItemListener() {
                    public void itemStateChanged(java.awt.event.ItemEvent evt) {
                        chTituloItemStateChanged(evt);
                    }
                });

                txtCodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
                txtCodigo.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtCodigoActionPerformed(evt);
                    }
                });

                txtGuarModif.setText("G");
                txtGuarModif.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        txtGuarModifActionPerformed(evt);
                    }
                });

                txtUnidadMedida.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                txtUnidadMedida.setEnabled(false);
                txtUnidadMedida.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        txtUnidadMedidaKeyPressed(evt);
                    }
                });

                jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
                jLabel20.setText("Unidad de Medida");

                btnBuscarUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                btnBuscarUnidad.setBorder(null);
                btnBuscarUnidad.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnBuscarUnidadActionPerformed(evt);
                    }
                });

                lblCodMedida.setText("jLabel1");

                javax.swing.GroupLayout panelEsquemaLayout = new javax.swing.GroupLayout(panelEsquema);
                panelEsquema.setLayout(panelEsquemaLayout);
                panelEsquemaLayout.setHorizontalGroup(
                    panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquemaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEsquemaLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel21))
                            .addComponent(txtNombreResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelEsquemaLayout.createSequentialGroup()
                                .addComponent(chTitulo)
                                .addGap(78, 78, 78)
                                .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(102, 102, 102)
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquemaLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(46, 46, 46))
                            .addComponent(txtResultadoDefec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEsquemaLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(txtCodAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCodMedida)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquemaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEsquemaLayout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel20))
                                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(btnBuscarUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38))))
                );
                panelEsquemaLayout.setVerticalGroup(
                    panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquemaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chTitulo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodMedida))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelEsquemaLayout.createSequentialGroup()
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNombreResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelEsquemaLayout.createSequentialGroup()
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtResultadoDefec, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquemaLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEsquemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscarUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                );

                btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/guardar16x16.png"))); // NOI18N
                btnGuardar.setMnemonic('G');
                btnGuardar.setText("Guardar");
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnGuardarActionPerformed(evt);
                    }
                });

                jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/eliminar16x16.png"))); // NOI18N
                jButton2.setMnemonic('E');
                jButton2.setText("Eliminar");
                jButton2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton2ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6)
                            .addComponent(panelAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelEsquema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2)
                        .addGap(36, 36, 36))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelEsquema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(btnGuardar))
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

       dispose();
       frm_LAB_ESQUEMA_RESULTADO_INGRESO er=new frm_LAB_ESQUEMA_RESULTADO_INGRESO();
       er.setVisible(true);
       String u=lblUsu.getText();
                             frm_LAB_ESQUEMA_RESULTADO_INGRESO.lblUsu.setText(u);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       dispose();
        frm_LAB_BUSCAR_ESQUEMA lu=new frm_LAB_BUSCAR_ESQUEMA();
        lu.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtGuarModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuarModifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuarModifActionPerformed

    private void btnBuscarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUnidadActionPerformed
       buscar_unidad.setVisible(true);
       txtBuscar.setText("");
       LAB_Unidad_Medida_cargar();
       LAB_Unidad_Medida_formato();
    }//GEN-LAST:event_btnBuscarUnidadActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void chTituloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chTituloItemStateChanged
      LAB_Esquema_Resultado erb=new LAB_Esquema_Resultado();
       LAB_Esquema_Resultado erb1=new LAB_Esquema_Resultado();
        if(chTitulo.isSelected()){
             txtResultadoDefec.setEnabled(false);
            btnBuscarUnidad.setEnabled(false);
            lblCodMedida.setText(erb.LAB_Unidad_Medida_buscar_cod("sin"));
            txtUnidadMedida.setText(erb1.LAB_Unidad_Medida_buscar_unidad("sin"));
            txtResultadoDefec.setText("");
      
        }else{
                 txtNombreResultado.setEnabled(true);
            txtResultadoDefec.setEnabled(true);
            btnBuscarUnidad.setEnabled(true);
            lblCodMedida.setText("");
            txtUnidadMedida.setText("");
            txtResultadoDefec.setText("");
            
        }
    }//GEN-LAST:event_chTituloItemStateChanged

    private void txtUnidadMedidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadMedidaKeyPressed
        char tecla= evt.getKeyChar();
                if(tecla==KeyEvent.VK_ENTER){
                    btnBuscarUnidad.doClick();
                }
    }//GEN-LAST:event_txtUnidadMedidaKeyPressed

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
                lblCodMedida.setText(tb_Unidad_Medida.getValueAt(filaselec, 1).toString());
                txtUnidadMedida.setText(tb_Unidad_Medida.getValueAt(filaselec, 2).toString());
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        LAB_Esquema_Resultado er=new LAB_Esquema_Resultado();
        LAB_Esquema_Resultado er1=new LAB_Esquema_Resultado();
        LAB_Esquema_Resultado er2=new LAB_Esquema_Resultado();
        try{
            if(txtGuarModif.getText().equalsIgnoreCase("G")){
                if(txtUnidadMedida.getText().equalsIgnoreCase("")||lblCodMedida.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una Unidad de Medida");
                }
                else  if(txtNombreResultado.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
                }
                else{
                    int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                    if(guardar == 0 ){
                        LAB_Esquema_Resultado meGuardar = new LAB_Esquema_Resultado();
                        meGuardar.setCod_esquema_resul(txtCodigo.getText());
                        meGuardar.setCod_exa_ana(txtCodAnalisis.getText());
                        meGuardar.setCod_uni_med_exa(lblCodMedida.getText());
                        meGuardar.setNombre_resultado_exa(txtNombreResultado.getText());
                        if(chTitulo.isSelected()){
                            meGuardar.setTipo_esquema("T");
                        }else{
                            meGuardar.setTipo_esquema("F");
                        }
                        meGuardar.setCantidad_valores(0);
                        meGuardar.setCantidad_resultados(0);
                        meGuardar.setResultado_defecto_esquema(txtResultadoDefec.getText());
                        meGuardar.setNom_usu(lblUsu.getText());

                        if(meGuardar.LAB_Esquema_Resultado_guardar()){
                           // JOptionPane.showMessageDialog(null, "Datos Guardados");
                            guardarTabla();
                            limpiar();
                            enableDatos();
                            txtGuarModif.setText("G");
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");

                        }}
                    }}else{
                       if(txtUnidadMedida.getText().equalsIgnoreCase("")||lblCodMedida.getText().equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una Unidad de Medida");
                }
                else  if(txtNombreResultado.getText().equalsIgnoreCase("")){

                    JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
                }
                        else{
                            int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                                "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                            if(modificar == 0 ){
                                LAB_Esquema_Resultado modif = new LAB_Esquema_Resultado();
                        modif.setCod_esquema_resul(txtCodigo.getText());
                        modif.setCod_exa_ana(txtCodAnalisis.getText());
                        modif.setCod_uni_med_exa(lblCodMedida.getText());
                        modif.setNombre_resultado_exa(txtNombreResultado.getText());
                        if(chTitulo.isSelected()){
                           modif.setTipo_esquema("T");
                        }else{
                            modif.setTipo_esquema("F");
                        }
                        modif.setResultado_defecto_esquema(txtResultadoDefec.getText());
                        modif.setNom_usu(lblUsu.getText());

                                if(modif.LAB_Esquema_Resultado_modificar()){
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
                                JOptionPane.showMessageDialog(this, "Ingrese todos los campos");

                            }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
           
            int filaselec=tb_Esquema.getSelectedRow();
            if( filaselec>=0){   
                ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
                    if(eliminar == 0 ){
        LAB_Esquema_Resultado obj=new LAB_Esquema_Resultado();
                obj.setCod_esquema_resul(tb_Esquema.getValueAt(filaselec, 0).toString());
                if(obj.LAB_Esquema_Resultado_eliminar())
                {
                    DefaultTableModel modelo = (DefaultTableModel)tb_Esquema.getModel(); 
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                    limpiar();
                    enableDatos();
                }
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Esquema a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tb_EsquemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_EsquemaMouseClicked
        // TODO add your handling code here:
        tb_Esquema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                    try{
                        enableDatos();
                        txtGuarModif.setText("M");
                        int filaselec=tb_Esquema.getSelectedRow();
                        txtCodigo.setText(tb_Esquema.getValueAt(filaselec, 0).toString());
                        txtNombreResultado.setText(tb_Esquema.getValueAt(filaselec, 1).toString());
                        txtResultadoDefec.setText(tb_Esquema.getValueAt(filaselec, 2).toString());
                        if(tb_Esquema.getValueAt(filaselec, 3).toString().equalsIgnoreCase("T")){
                            chTitulo.setSelected(true);
                            txtResultadoDefec.setEnabled(false);
                        }else{
                            chTitulo.setSelected(false);
                            txtResultadoDefec.setEnabled(true);
                        }
                        lblCodMedida.setText(tb_Esquema.getValueAt(filaselec, 4).toString());
                        txtUnidadMedida.setText(tb_Esquema.getValueAt(filaselec, 5).toString());
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
            }});
    }//GEN-LAST:event_tb_EsquemaMouseClicked
    public void enableDatos(){
    chTitulo.setEnabled(true);
    txtResultadoDefec.setEnabled(true);
    }
    public void limpiar(){
    //codigo
        LAB_Esquema_Resultado u=new LAB_Esquema_Resultado();
        txtCodigo.setText(u.LAB_Esquema_Resultado_generarid());
   if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("ER000000000000000001");
    }
   txtResultadoDefec.setText("");
   txtNombreResultado.setText("");
   lblCodMedida.setText("");
   txtUnidadMedida.setText("");
        txtGuarModif.setText("G");
        chTitulo.setSelected(false);
}
    
 public void guardarTabla(){
        try {
String cod_esquema_resul,nombre_resultado_exa,resultado_defecto_esquema,tipo_esquema,codmedi,medida,cantidad_valores,
cantidad_resultados;
            m=(DefaultTableModel) tb_Esquema.getModel();
            cod_esquema_resul=txtCodigo.getText();
            nombre_resultado_exa=txtNombreResultado.getText();
            resultado_defecto_esquema=txtResultadoDefec.getText();
            if(chTitulo.isSelected()){
                tipo_esquema="T";
            }
            else{tipo_esquema="F";
                    }
            codmedi=lblCodMedida.getText();
            medida=txtUnidadMedida.getText();
            cantidad_valores="0";
            cantidad_resultados="0";
            
           
              m=(DefaultTableModel) tb_Esquema.getModel();
           String filaelemento[]={cod_esquema_resul,nombre_resultado_exa,resultado_defecto_esquema,tipo_esquema,codmedi,medida,cantidad_valores,
cantidad_resultados};
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
            java.util.logging.Logger.getLogger(frm_LAB_ESQUEMA_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ESQUEMA_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ESQUEMA_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ESQUEMA_RESULTADO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_ESQUEMA_RESULTADO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    public static javax.swing.JButton btnBuscarUnidad;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JDialog buscar_unidad;
    public static javax.swing.JCheckBox chTitulo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpanel;
    public static javax.swing.JLabel lblCodMedida;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelAnalisis;
    private javax.swing.JPanel panelEsquema;
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
    private javax.swing.JTextField txtGuarModif;
    public static javax.swing.JTextField txtNombreResultado;
    public static javax.swing.JTextField txtResultadoDefec;
    public static javax.swing.JTextField txtUnidadMedida;
    public static javax.swing.JTextField txtUnidadOrganica;
    // End of variables declaration//GEN-END:variables
}
