
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
import static vista.Principal.fechaActual;
import vista.frmlaboratorioClinico;

/**
 *
 * @author PC-SISTEMA
 */
public class frm_LAB_ANALISIS_EXAMEN extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
Conexion c=new Conexion();
DefaultTableModel m,n,muestra;
    /**
     * Creates new form LAB_MUESTRA_EXAMEN
     */
    public frm_LAB_ANALISIS_EXAMEN() {
        initComponents();
        c.conectar();
        txtGuarModif.setVisible(false);
        h1 = new Thread(this);
        h1.start();
        buscar_clasificacion.getContentPane().setBackground(Color.white); 
        buscar_clasificacion.setLocationRelativeTo(null);
        buscar_nomenclatura.getContentPane().setBackground(Color.white); 
        buscar_nomenclatura.setLocationRelativeTo(null);
        buscar_muestra.getContentPane().setBackground(Color.white); 
        buscar_muestra.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white); 
        panelMuestras.setBackground(Color.white); 
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        txtCodClasificacion.setVisible(false);
        txtCodNomen.setVisible(false);
        setLocationRelativeTo(null);//en el centro
        setResizable(false);//para que no funcione el boton maximizar
        buscar_clasificacion.setResizable(false);
        buscar_nomenclatura.setResizable(false);
        buscar_muestra.setResizable(false);
        //fecha
       Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        //codigo
        LAB_Analisis_Examen u=new LAB_Analisis_Examen();
        txtCodigo.setText(u.LAB_Analisis_Examen_generarid());
   if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("AE0000000000001");
    }
   btnBuscarCPT.setEnabled(false);
   lblArea.setVisible(false);
   LAB_Clasificacion_Examen_cargar();
   LAB_Clasificacion_Examen_formato();
   LAB_Muestra_Examen_cargar();
   LAB_Muestra_formato();
   ///tb_Muestras.getColumnModel().getColumn(0).setCellEditor(new Celda_Checkox());
   //tb_Muestras.getColumnModel().getColumn(0).setCellRenderer(new Render_Checkbox());

   
   
   //para el text Area-salto de linea
        txtExplicacion.setLineWrap(true);
        txtExplicacion.setWrapStyleWord(true);
        txtObservacion.setLineWrap(true);
        txtObservacion.setWrapStyleWord(true);
   //para limpiar el txt al darle click
 txtbuscarClasifi.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtbuscarClasifi.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} );
 
 //nomen
 txtbuscarNomen.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtbuscarNomen.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
} );
 //nomen
 txtBuscarMuestra.addFocusListener(new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
  txtBuscarMuestra.setText("");
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
    
    
    public void LAB_Clasificacion_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","Nombre","Cogd_uni_or","Servicio","NomUni","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];

            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_CLASIFICACION_EXAMEN_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
            fila[0]=String.valueOf(c)+"º";
            fila[1]=r.getString(1);
            fila[2]=r.getString(2);
            fila[3]=r.getString(3);
            fila[4]=r.getString(4);
            fila[5]=r.getString(5);
            fila[6]=r.getString(6);
                m.addRow(fila);
                c++;
            }
            tb_Clasificacion.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Clasificacion.setRowSorter(elQueOrdena);
            this.tb_Clasificacion.setModel(m);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
}
    public void LAB_Clasificacion_Examen_formato(){
    tb_Clasificacion.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Clasificacion.getColumnModel().getColumn(1).setPreferredWidth(110);
    tb_Clasificacion.getColumnModel().getColumn(2).setPreferredWidth(170);
    tb_Clasificacion.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Clasificacion.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_Clasificacion.getColumnModel().getColumn(6).setPreferredWidth(220);
    
            //Ocultar    
    tb_Clasificacion.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Clasificacion.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Clasificacion.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Clasificacion.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Clasificacion.getSelectionModel().setSelectionInterval(0, 0);
            tb_Clasificacion.requestFocus();
}
    
        public void LAB_Nomenclatura_cargar(){
     String consulta="";
        try {
            tb_Nomenclatura.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Código CPT","Nomenclatura","Tiempo(hora)","Tiempo(min)"};
            n=new DefaultTableModel(null,titulos);
            JTable p=new JTable(n);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
                    consulta="exec sp_LAB_ANALISIS_NOMEN_listar ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1, Integer.parseInt(lblArea.getText()));
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                n.addRow(fila);
                c++;
            }
            tb_Nomenclatura.setModel(n);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(n);
            tb_Nomenclatura.setRowSorter(elQueOrdena);
            this.tb_Nomenclatura.setModel(n);
            
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
}
    public void LAB_Nomenclatura_formato(){
    tb_Nomenclatura.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Nomenclatura.getColumnModel().getColumn(1).setPreferredWidth(120);
    tb_Nomenclatura.getColumnModel().getColumn(2).setPreferredWidth(120);
    tb_Nomenclatura.getColumnModel().getColumn(3).setPreferredWidth(230);
    tb_Nomenclatura.getColumnModel().getColumn(4).setPreferredWidth(100);
    tb_Nomenclatura.getColumnModel().getColumn(5).setPreferredWidth(100);
    tb_Nomenclatura.getSelectionModel().setSelectionInterval(0, 0);
            tb_Nomenclatura.requestFocus();
}
    
    public void LAB_Muestra_Examen_cargar(){
    try {
             String titulos[]={"Nº","Código","Muestra",""};
            muestra=new DefaultTableModel(null,titulos);
            JTable p=new JTable(muestra);
//            Object filas[]=new Object[4];
String filas[]=new String[6];
            Conexion obj=new Conexion();
        String consulta="exec sp_LAB_MUESTRA_EXAMEN_listar";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
            filas[0]=String.valueOf(c)+"º";
            filas[1]=r.getString(1);
            filas[2]=r.getString(2);
            filas[3]=r.getString(3);
                muestra.addRow(filas);
                c++;
            }
        
            tb_Muestra_Examen.setModel(muestra);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(muestra);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(muestra);
            
     //   tb_Muestras.getColumnModel().getColumn(0).setCellRenderer((TableCellRenderer) new Render_Checkbox());
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(rootPane, "Error en la tabla");
    }
    }
    
    public void LAB_Muestra_formato(){
    tb_Muestra_Examen.getColumnModel().getColumn(0).setPreferredWidth(60);
    tb_Muestra_Examen.getColumnModel().getColumn(1).setPreferredWidth(100);
    tb_Muestra_Examen.getColumnModel().getColumn(2).setPreferredWidth(220);
    tb_Muestra_Examen.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Muestra_Examen.getColumnModel().getColumn(3).setMaxWidth(0);
      tb_Muestra_Examen.getSelectionModel().setSelectionInterval(0, 0);
            tb_Muestra_Examen.requestFocus();
    
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

        buscar_clasificacion = new javax.swing.JDialog();
        btnBuscar1 = new javax.swing.JButton();
        txtbuscarClasifi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Clasificacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            buscar_nomenclatura = new javax.swing.JDialog();
            jScrollPane4 = new javax.swing.JScrollPane();
            tb_Nomenclatura = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jLabel2 = new javax.swing.JLabel();
                txtbuscarNomen = new javax.swing.JTextField();
                btnBuscarNomen = new javax.swing.JButton();
                buscar_muestra = new javax.swing.JDialog();
                jScrollPane6 = new javax.swing.JScrollPane();
                tb_Muestra_Examen = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jLabel24 = new javax.swing.JLabel();
                    txtBuscarMuestra = new javax.swing.JTextField();
                    btnBuscarMuestra = new javax.swing.JButton();
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
                    btnBuscar = new javax.swing.JButton();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel3 = new javax.swing.JLabel();
                    jLabel4 = new javax.swing.JLabel();
                    jLabel5 = new javax.swing.JLabel();
                    jLabel6 = new javax.swing.JLabel();
                    jLabel8 = new javax.swing.JLabel();
                    jLabel10 = new javax.swing.JLabel();
                    txtCodigo = new javax.swing.JTextField();
                    jLabel7 = new javax.swing.JLabel();
                    txtClasificacion = new javax.swing.JTextField();
                    jLabel18 = new javax.swing.JLabel();
                    txtGuarModif = new javax.swing.JTextField();
                    jLabel9 = new javax.swing.JLabel();
                    txtCodClasificacion = new javax.swing.JTextField();
                    btnBuscarClasif = new javax.swing.JButton();
                    jLabel11 = new javax.swing.JLabel();
                    cbxTipoProc = new javax.swing.JComboBox();
                    jLabel12 = new javax.swing.JLabel();
                    cbxRestric = new javax.swing.JComboBox();
                    jLabel13 = new javax.swing.JLabel();
                    txtCodigoCPT = new javax.swing.JTextField();
                    btnBuscarCPT = new javax.swing.JButton();
                    jLabel16 = new javax.swing.JLabel();
                    txtNomen = new javax.swing.JTextField();
                    txtCodNomen = new javax.swing.JTextField();
                    jLabel17 = new javax.swing.JLabel();
                    txtAbrev = new javax.swing.JTextField();
                    jLabel19 = new javax.swing.JLabel();
                    txtTiempoHora = new javax.swing.JTextField();
                    txtTiempoMin = new javax.swing.JTextField();
                    jLabel20 = new javax.swing.JLabel();
                    chActivo = new javax.swing.JCheckBox();
                    jScrollPane2 = new javax.swing.JScrollPane();
                    txtExplicacion = new javax.swing.JTextArea();
                    jScrollPane3 = new javax.swing.JScrollPane();
                    txtObservacion = new javax.swing.JTextArea();
                    jLabel21 = new javax.swing.JLabel();
                    jLabel22 = new javax.swing.JLabel();
                    jLabel23 = new javax.swing.JLabel();
                    txtNombreExamen = new javax.swing.JTextField();
                    panelMuestras = new javax.swing.JPanel();
                    jScrollPane5 = new javax.swing.JScrollPane();
                    tb_Muestras = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            if(colIndex==0){
                                return true;
                            }else{
                                return false; //Disallow the editing of any cell
                            }}};
                            btn_Agregar = new javax.swing.JButton();
                            btn_Quitar = new javax.swing.JButton();
                            lblArea = new javax.swing.JLabel();

                            buscar_clasificacion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                            buscar_clasificacion.setTitle("SISGESH.::.Clasificación Examen");
                            buscar_clasificacion.setMinimumSize(new java.awt.Dimension(548, 444));

                            btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                            btnBuscar1.setBorder(null);
                            btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnBuscar1ActionPerformed(evt);
                                }
                            });

                            txtbuscarClasifi.setForeground(new java.awt.Color(0, 51, 51));
                            txtbuscarClasifi.setText("Ingresar Clasificación Examen");
                            txtbuscarClasifi.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtbuscarClasifiActionPerformed(evt);
                                }
                            });
                            txtbuscarClasifi.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtbuscarClasifiKeyPressed(evt);
                                }
                                public void keyTyped(java.awt.event.KeyEvent evt) {
                                    txtbuscarClasifiKeyTyped(evt);
                                }
                            });

                            jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            jLabel1.setText("Búsqueda ");

                            tb_Clasificacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            tb_Clasificacion.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Clasificacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tb_Clasificacion.setRowHeight(25);
                            tb_Clasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_ClasificacionMouseClicked(evt);
                                }
                            });
                            tb_Clasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_ClasificacionKeyPressed(evt);
                                }
                            });
                            jScrollPane1.setViewportView(tb_Clasificacion);

                            javax.swing.GroupLayout buscar_clasificacionLayout = new javax.swing.GroupLayout(buscar_clasificacion.getContentPane());
                            buscar_clasificacion.getContentPane().setLayout(buscar_clasificacionLayout);
                            buscar_clasificacionLayout.setHorizontalGroup(
                                buscar_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(buscar_clasificacionLayout.createSequentialGroup()
                                    .addGroup(buscar_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(buscar_clasificacionLayout.createSequentialGroup()
                                            .addGap(164, 164, 164)
                                            .addComponent(txtbuscarClasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(buscar_clasificacionLayout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(19, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_clasificacionLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addGap(207, 207, 207))
                            );
                            buscar_clasificacionLayout.setVerticalGroup(
                                buscar_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_clasificacionLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addGroup(buscar_clasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtbuscarClasifi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(39, Short.MAX_VALUE))
                            );

                            buscar_nomenclatura.setMinimumSize(new java.awt.Dimension(551, 461));

                            tb_Nomenclatura.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
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
                            tb_Nomenclatura.setRowHeight(25);
                            tb_Nomenclatura.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_NomenclaturaMouseClicked(evt);
                                }
                            });
                            tb_Nomenclatura.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_NomenclaturaKeyPressed(evt);
                                }
                            });
                            jScrollPane4.setViewportView(tb_Nomenclatura);

                            jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
                            jLabel2.setText("Búsqueda ");

                            txtbuscarNomen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            txtbuscarNomen.setForeground(new java.awt.Color(0, 51, 51));
                            txtbuscarNomen.setText("Ingresar Nomenclatura");
                            txtbuscarNomen.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtbuscarNomenActionPerformed(evt);
                                }
                            });
                            txtbuscarNomen.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtbuscarNomenKeyPressed(evt);
                                }
                                public void keyTyped(java.awt.event.KeyEvent evt) {
                                    txtbuscarNomenKeyTyped(evt);
                                }
                            });

                            btnBuscarNomen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                            btnBuscarNomen.setBorder(null);
                            btnBuscarNomen.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnBuscarNomenActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout buscar_nomenclaturaLayout = new javax.swing.GroupLayout(buscar_nomenclatura.getContentPane());
                            buscar_nomenclatura.getContentPane().setLayout(buscar_nomenclaturaLayout);
                            buscar_nomenclaturaLayout.setHorizontalGroup(
                                buscar_nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(buscar_nomenclaturaLayout.createSequentialGroup()
                                    .addGroup(buscar_nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(buscar_nomenclaturaLayout.createSequentialGroup()
                                            .addGap(164, 164, 164)
                                            .addComponent(txtbuscarNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnBuscarNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(buscar_nomenclaturaLayout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(19, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_nomenclaturaLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addGap(207, 207, 207))
                            );
                            buscar_nomenclaturaLayout.setVerticalGroup(
                                buscar_nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_nomenclaturaLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addGroup(buscar_nomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtbuscarNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscarNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                                    .addGap(29, 29, 29))
                            );

                            buscar_muestra.setMinimumSize(new java.awt.Dimension(415, 491));

                            tb_Muestra_Examen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            tb_Muestra_Examen.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Muestra_Examen.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
                            tb_Muestra_Examen.setRowHeight(25);
                            tb_Muestra_Examen.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Muestra_ExamenMouseClicked(evt);
                                }
                            });
                            tb_Muestra_Examen.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Muestra_ExamenKeyPressed(evt);
                                }
                            });
                            jScrollPane6.setViewportView(tb_Muestra_Examen);

                            jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 1, 24)); // NOI18N
                            jLabel24.setText("Búsqueda ");

                            txtBuscarMuestra.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            txtBuscarMuestra.setForeground(new java.awt.Color(0, 51, 51));
                            txtBuscarMuestra.setText("Ingresar Muestra");
                            txtBuscarMuestra.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtBuscarMuestraActionPerformed(evt);
                                }
                            });
                            txtBuscarMuestra.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    txtBuscarMuestraKeyPressed(evt);
                                }
                                public void keyTyped(java.awt.event.KeyEvent evt) {
                                    txtBuscarMuestraKeyTyped(evt);
                                }
                            });

                            btnBuscarMuestra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view.gif"))); // NOI18N
                            btnBuscarMuestra.setBorder(null);
                            btnBuscarMuestra.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnBuscarMuestraActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout buscar_muestraLayout = new javax.swing.GroupLayout(buscar_muestra.getContentPane());
                            buscar_muestra.getContentPane().setLayout(buscar_muestraLayout);
                            buscar_muestraLayout.setHorizontalGroup(
                                buscar_muestraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_muestraLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel24)
                                    .addGap(140, 140, 140))
                                .addGroup(buscar_muestraLayout.createSequentialGroup()
                                    .addGap(89, 89, 89)
                                    .addComponent(txtBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_muestraLayout.createSequentialGroup()
                                    .addContainerGap(16, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))
                            );
                            buscar_muestraLayout.setVerticalGroup(
                                buscar_muestraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscar_muestraLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel24)
                                    .addGap(18, 18, 18)
                                    .addGroup(buscar_muestraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBuscarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(40, Short.MAX_VALUE))
                            );

                            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                            setTitle("SISGESH .::. Análisis Examen");

                            jpanel.setBackground(new java.awt.Color(2, 67, 115));

                            titulo5.setBackground(new java.awt.Color(0, 102, 102));
                            titulo5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
                            titulo5.setForeground(new java.awt.Color(255, 255, 255));
                            titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                            titulo5.setText("Análisis Examen");
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
                            btnguardar.setToolTipText("Guardar (Alt-G)");
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
                            btnmodificar.setToolTipText("Modificar (Alt-M)");
                            btnmodificar.setContentAreaFilled(false);
                            btnmodificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnmodificar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnmodificarActionPerformed(evt);
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
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(titulo5))
                                    .addGap(433, 433, 433)
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
                                    .addGap(0, 0, 0)
                                    .addGroup(jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addGap(222, 222, 222)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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

                            txtCodigo.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtCodigo.setEnabled(false);

                            jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel7.setText("Clasificación del Examen:");

                            txtClasificacion.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtClasificacion.setEnabled(false);
                            txtClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyReleased(java.awt.event.KeyEvent evt) {
                                    txtClasificacionKeyReleased(evt);
                                }
                            });

                            jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel18.setText("Código:");

                            txtGuarModif.setText("G");
                            txtGuarModif.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtGuarModifActionPerformed(evt);
                                }
                            });

                            jLabel9.setText("Observación:");

                            btnBuscarClasif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                            btnBuscarClasif.setBorder(null);
                            btnBuscarClasif.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnBuscarClasifActionPerformed(evt);
                                }
                            });

                            jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel11.setText("Tipo de Procedimiento:");

                            cbxTipoProc.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            cbxTipoProc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Manual", "Automático" }));

                            jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel12.setText("Restricción:");

                            cbxRestric.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            cbxRestric.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Normal", "Restringido" }));

                            jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel13.setText("Código CPT:");

                            txtCodigoCPT.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtCodigoCPT.setEnabled(false);

                            btnBuscarCPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                            btnBuscarCPT.setBorder(null);
                            btnBuscarCPT.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnBuscarCPTActionPerformed(evt);
                                }
                            });

                            jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel16.setText("Nomenclatura:");

                            txtNomen.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtNomen.setEnabled(false);

                            jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel17.setText("Abreviatura:");

                            txtAbrev.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                            jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel19.setText("Tiempo:");

                            txtTiempoHora.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtTiempoHora.setEnabled(false);
                            txtTiempoHora.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtTiempoHoraActionPerformed(evt);
                                }
                            });

                            txtTiempoMin.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
                            txtTiempoMin.setEnabled(false);

                            jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel20.setText("Explicación:");

                            chActivo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            chActivo.setSelected(true);
                            chActivo.setText("Activo");

                            txtExplicacion.setColumns(20);
                            txtExplicacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            txtExplicacion.setRows(5);
                            jScrollPane2.setViewportView(txtExplicacion);

                            txtObservacion.setColumns(20);
                            txtObservacion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            txtObservacion.setRows(5);
                            jScrollPane3.setViewportView(txtObservacion);

                            jLabel21.setText("h");

                            jLabel22.setText("min");

                            jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            jLabel23.setText("Nombre del Examen:");

                            txtNombreExamen.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N

                            panelMuestras.setBorder(javax.swing.BorderFactory.createTitledBorder("Muestras"));

                            tb_Muestras.setFont(new java.awt.Font("Segoe UI Semilight", 0, 12)); // NOI18N
                            tb_Muestras.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {

                                },
                                new String [] {
                                    "Código", "Muestra"
                                }
                            ) {
                                boolean[] canEdit = new boolean [] {
                                    false, false
                                };

                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return canEdit [columnIndex];
                                }
                            });
                            tb_Muestras.setRowHeight(18);
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
                                public void keyTyped(java.awt.event.KeyEvent evt) {
                                    tb_MuestrasKeyTyped(evt);
                                }
                            });
                            jScrollPane5.setViewportView(tb_Muestras);

                            btn_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/mas.png"))); // NOI18N
                            btn_Agregar.setContentAreaFilled(false);
                            btn_Agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btn_AgregarActionPerformed(evt);
                                }
                            });

                            btn_Quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/menos16x16.png"))); // NOI18N
                            btn_Quitar.setContentAreaFilled(false);
                            btn_Quitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btn_Quitar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btn_QuitarActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout panelMuestrasLayout = new javax.swing.GroupLayout(panelMuestras);
                            panelMuestras.setLayout(panelMuestrasLayout);
                            panelMuestrasLayout.setHorizontalGroup(
                                panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelMuestrasLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btn_Quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap())
                            );
                            panelMuestrasLayout.setVerticalGroup(
                                panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelMuestrasLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addGroup(panelMuestrasLayout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_Quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            lblArea.setText("jLabel25");

                            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                            getContentPane().setLayout(layout);
                            layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel9))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(panelMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(cbxTipoProc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel12))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGap(18, 18, 18)
                                                            .addComponent(lblArea)))
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(btnBuscarClasif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtNombreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtTiempoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel21)
                                                    .addGap(12, 12, 12)
                                                    .addComponent(txtTiempoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel22))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(cbxRestric, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(chActivo))
                                                .addComponent(txtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtCodigoCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtCodNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(54, 54, 54))
                            );
                            layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCodClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGuarModif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblArea))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18))
                                    .addGap(21, 21, 21)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCodigoCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(btnBuscarClasif, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(26, 26, 26)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTiempoHora, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTiempoMin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(25, 25, 25)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(30, 30, 30)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cbxRestric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(chActivo)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cbxTipoProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtNomen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(24, 24, 24)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtNombreExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(18, 18, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(panelMuestras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(30, 30, 30)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnBuscarCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                            );

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        enableDatos();
        limpiar();
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/guardar16x16.png"));
        LAB_Analisis_Examen me=new  LAB_Analisis_Examen();
        LAB_Analisis_Examen me1=new LAB_Analisis_Examen();
        LAB_Analisis_Examen me2=new LAB_Analisis_Examen();
        try{
          if(txtGuarModif.getText().equalsIgnoreCase("G")){
            if(txtClasificacion.getText().equalsIgnoreCase("")||txtCodClasificacion.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione una Clasificación del Examen");
          }  else if(txtCodNomen.getText().equalsIgnoreCase("")||txtCodigoCPT.getText().equalsIgnoreCase("")
                  ||txtNomen.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Seleccione un Código CPT");
          }  
          else if(txtNombreExamen.getText().equalsIgnoreCase("")||txtAbrev.getText().equalsIgnoreCase("")||
                  cbxTipoProc.getSelectedIndex()==0 || cbxRestric.getSelectedIndex()==0 ){
              JOptionPane.showMessageDialog(rootPane, "Ingrese todos los campos");
          }  
          else if(me1.LAB_Analisis_Examen_ver(txtCodClasificacion.getText(),txtCodNomen.getText())>0){
              JOptionPane.showMessageDialog(rootPane, "El Registro ya ha sido ingresado\nIntente nuevamente");
              
              txtClasificacion.setText("");
              txtClasificacion.requestFocus();
          }
         else{
              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(guardar == 0 ){
                  LAB_Analisis_Examen meGuardar = new LAB_Analisis_Examen();
                  meGuardar.setCod_exa_ana(txtCodigo.getText());
                  meGuardar.setCod_clasi_exa(txtCodClasificacion.getText());
                  meGuardar.setCod_nomen_caja(txtCodNomen.getText());
                  meGuardar.setNombre_ana_exa(txtNombreExamen.getText());
                  meGuardar.setAbrev_ana_exa(txtAbrev.getText());
                  meGuardar.setTiempo_hora(Integer.parseInt(txtTiempoHora.getText()));
                  meGuardar.setTiempo_min(Integer.parseInt(txtTiempoMin.getText()));
                  meGuardar.setTipo_procesamiento(cbxTipoProc.getSelectedItem().toString());
                  meGuardar.setRestriccion_analisis(cbxRestric.getSelectedItem().toString());
                  meGuardar.setExplicacion_met_proce(txtExplicacion.getText());
                  if(chActivo.isSelected()){
                  meGuardar.setEstado_detalle("A");
                  }else{
                  meGuardar.setEstado_detalle("D");
                  }
                  meGuardar.setObservacion_ana_exa(txtObservacion.getText());
                  meGuardar.setNom_usu(lblUsu.getText());
  
                  if(meGuardar.LAB_Analisis_Examen_guardar()){
                    JOptionPane.showMessageDialog(null, "Datos Guardados");
                    guardarDetalle();
                    limpiar();
                    enableDatos();
                    txtGuarModif.setText("G");
                  }
                  else{
                    JOptionPane.showMessageDialog(this, "El Registro ya ha sido ingresado\nIntente nuevamente");
                  }}
          }}else{
             if(txtClasificacion.getText().equalsIgnoreCase("")||txtCodClasificacion.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Busque una Unidad Orgánica");
          }  else if(txtObservacion.getText().equalsIgnoreCase("")){
              JOptionPane.showMessageDialog(rootPane, "Ingrese la Observación");
          } 
             else{
              int modificar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea MODIFICAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
              if(modificar == 0 ){
                 LAB_Analisis_Examen meModif = new LAB_Analisis_Examen();
                  meModif.setCod_exa_ana(txtCodigo.getText());
                  meModif.setCod_clasi_exa(txtCodClasificacion.getText());
                  meModif.setCod_nomen_caja(txtCodNomen.getText());
                  meModif.setNombre_ana_exa(txtNombreExamen.getText());
                  meModif.setAbrev_ana_exa(txtAbrev.getText());
                  meModif.setTiempo_hora(Integer.parseInt(txtTiempoHora.getText()));
                  meModif.setTiempo_min(Integer.parseInt(txtTiempoMin.getText()));
                  meModif.setTipo_procesamiento(cbxTipoProc.getSelectedItem().toString());
                  meModif.setRestriccion_analisis(cbxRestric.getSelectedItem().toString());
                  meModif.setExplicacion_met_proce(txtExplicacion.getText());
                  if(chActivo.isSelected()){
                  meModif.setEstado_detalle("A");
                  }else{
                  meModif.setEstado_detalle("D");
                  }
                  meModif.setObservacion_ana_exa(txtObservacion.getText());
                  meModif.setNom_usu(lblUsu.getText());
                  
                  LAB_Analisis_Detalle rdeliminar=new LAB_Analisis_Detalle();
                  rdeliminar.setCod_exa_ana(txtCodigo.getText());
                  
                  if(meModif.LAB_Analisis_Examen_modificar()&& rdeliminar.LAB_Analisis_Detalle_eliminar()){
                      JOptionPane.showMessageDialog(this, "Datos Modificados");
                      guardarDetalle();
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

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 )
            {
                LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
                obj.setCod_exa_ana(txtCodigo.getText());
                LAB_Analisis_Detalle rdeliminar=new LAB_Analisis_Detalle();
                  rdeliminar.setCod_exa_ana(txtCodigo.getText());
                if(obj.LAB_Analisis_Examen_eliminar()&& rdeliminar.LAB_Analisis_Detalle_eliminar())
                {
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                    limpiar();
                    enableDatos();
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Personal a eliminar");
        }

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        enableDatos();
        btnBuscarClasif.setEnabled(false);
    btnBuscarCPT.setEnabled(false);
        txtGuarModif.setText("M");
        btnguardar.setEnabled(true);
        btneliminar.setEnabled(false);
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       dispose();
        frm_LAB_BUSCAR_ANALISIS_EXAMEN lu=new frm_LAB_BUSCAR_ANALISIS_EXAMEN();
        lu.setVisible(true);
        


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtGuarModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuarModifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuarModifActionPerformed

    private void btnBuscarClasifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClasifActionPerformed
        // TODO add your handling code here:
        buscar_clasificacion.setVisible(true);
        txtbuscarClasifi.setText("");
        LAB_Clasificacion_Examen_cargar();
        LAB_Clasificacion_Examen_formato();
    }//GEN-LAST:event_btnBuscarClasifActionPerformed

    private void btnBuscarCPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPTActionPerformed
      buscar_nomenclatura.setVisible(true);
      txtbuscarNomen.setText("");
      LAB_Nomenclatura_cargar();
      LAB_Nomenclatura_formato();
    }//GEN-LAST:event_btnBuscarCPTActionPerformed

    private void txtTiempoHoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoHoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoHoraActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
       String consulta="";
        try {
            tb_Clasificacion.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Nombre","Cod_uni_org","Servicio","UniOrg","Observacion"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[7];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
                    consulta="exec sp_LAB_CLASIFICACION_EXAMEN_buscar ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtbuscarClasifi.getText());
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
                m.addRow(fila);
                c++;
            }
            tb_Clasificacion.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Clasificacion.setRowSorter(elQueOrdena);
            this.tb_Clasificacion.setModel(m);
            
            LAB_Clasificacion_Examen_formato();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void txtbuscarClasifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarClasifiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarClasifiActionPerformed

    private void txtbuscarClasifiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarClasifiKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Clasificacion.getSelectionModel().setSelectionInterval(0, 0);
            tb_Clasificacion.requestFocus();
        }
    }//GEN-LAST:event_txtbuscarClasifiKeyPressed

    private void txtbuscarClasifiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarClasifiKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscar1.doClick();
        }

    }//GEN-LAST:event_txtbuscarClasifiKeyTyped

    private void tb_ClasificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClasificacionMouseClicked

    }//GEN-LAST:event_tb_ClasificacionMouseClicked

    private void tb_ClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_ClasificacionKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                buscar_clasificacion.setVisible(false);
                int filaselec=tb_Clasificacion.getSelectedRow();
                txtCodClasificacion.setText(tb_Clasificacion.getValueAt(filaselec, 1).toString());
                txtClasificacion.setText(tb_Clasificacion.getValueAt(filaselec, 2).toString());
                lblArea.setText(tb_Clasificacion.getValueAt(filaselec, 3).toString());
                btnBuscarCPT.setEnabled(true);
                 txtCodigoCPT.setText("");
            txtCodNomen.setText("");
            txtNomen.setText("");
            txtTiempoHora.setText("");
            txtTiempoMin.setText("");
            txtNombreExamen.setText("");
            txtAbrev.setText("");
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_ClasificacionKeyPressed

    private void tb_NomenclaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_NomenclaturaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_NomenclaturaMouseClicked

    private void tb_NomenclaturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_NomenclaturaKeyPressed
         char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            try{
                buscar_nomenclatura.setVisible(false);
                int filaselec=tb_Nomenclatura.getSelectedRow();
                txtCodNomen.setText(tb_Nomenclatura.getValueAt(filaselec, 1).toString());
                txtCodigoCPT.setText(tb_Nomenclatura.getValueAt(filaselec, 2).toString());
                txtNomen.setText(tb_Nomenclatura.getValueAt(filaselec, 3).toString());
                txtNombreExamen.setText(tb_Nomenclatura.getValueAt(filaselec, 3).toString());
                txtTiempoHora.setText(tb_Nomenclatura.getValueAt(filaselec, 4).toString());
                txtTiempoMin.setText(tb_Nomenclatura.getValueAt(filaselec, 5).toString());
            }
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_tb_NomenclaturaKeyPressed

    private void txtbuscarNomenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarNomenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarNomenActionPerformed

    private void txtbuscarNomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarNomenKeyPressed
char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarNomen.doClick();
        }
    }//GEN-LAST:event_txtbuscarNomenKeyPressed

    private void txtbuscarNomenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarNomenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarNomenKeyTyped

    private void btnBuscarNomenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarNomenActionPerformed
       String consulta="";
        try {
            tb_Nomenclatura.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Código CPT","Nomenclatura","Tiempo(hora)","Tiempo(min)"};
            n=new DefaultTableModel(null,titulos);
            JTable p=new JTable(n);
            String fila[]=new String[6];

            LAB_Clasificacion_Examen obj=new LAB_Clasificacion_Examen();
                    consulta="exec sp_LAB_ANALISIS_NOMEN_buscar ?,?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setInt(1, Integer.parseInt(lblArea.getText()));
            cmd.setString(2, txtbuscarNomen.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=String.valueOf(c)+"º";
                fila[1]=r.getString(1);
                fila[2]=r.getString(2);
                fila[3]=r.getString(3);
                fila[4]=r.getString(4);
                fila[5]=r.getString(5);
                n.addRow(fila);
                c++;
            }
            tb_Nomenclatura.setModel(n);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(n);
            tb_Nomenclatura.setRowSorter(elQueOrdena);
            this.tb_Nomenclatura.setModel(n);
            
            LAB_Nomenclatura_formato();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarNomenActionPerformed

    private void tb_MuestrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MuestrasMouseClicked

    }//GEN-LAST:event_tb_MuestrasMouseClicked

    private void tb_MuestrasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_MuestrasMouseReleased
 
    }//GEN-LAST:event_tb_MuestrasMouseReleased

    private void tb_MuestrasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tb_MuestrasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_MuestrasPropertyChange

    private void tb_MuestrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_MuestrasKeyTyped

    }//GEN-LAST:event_tb_MuestrasKeyTyped

    private void tb_Muestra_ExamenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenMouseClicked

    }//GEN-LAST:event_tb_Muestra_ExamenMouseClicked

    private void tb_Muestra_ExamenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Muestra_ExamenKeyPressed
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            
                buscar_muestra.setVisible(false);
                
                String cod,muestra;
                int filaselec=tb_Muestra_Examen.getSelectedRow();
        
            m=(DefaultTableModel) tb_Muestras.getModel();
            cod=tb_Muestra_Examen.getValueAt(filaselec, 1).toString();
            muestra=tb_Muestra_Examen.getValueAt(filaselec, 2).toString();
         
                
            if(tb_Muestras.getRowCount()==0){
              m=(DefaultTableModel) tb_Muestras.getModel();
           String filaelemento[]={cod,muestra};
               m.addRow(filaelemento);
               
          }
          else{
           if(repiteDetalle()==true){
               JOptionPane.showMessageDialog(rootPane,"La Muestra ya ha sido ingresada.");   
          }
           else{
              m=(DefaultTableModel) tb_Muestras.getModel();
           String filaelemento[]={ cod,muestra};
               m.addRow(filaelemento);
           }}
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_Muestra_ExamenKeyPressed

    private void txtBuscarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMuestraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMuestraActionPerformed

    private void txtBuscarMuestraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMuestraKeyPressed
char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnBuscarMuestra.doClick();
        }
    }//GEN-LAST:event_txtBuscarMuestraKeyPressed

    private void txtBuscarMuestraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMuestraKeyTyped
        

    }//GEN-LAST:event_txtBuscarMuestraKeyTyped

    private void btnBuscarMuestraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMuestraActionPerformed
        // TODO add your handling code here:
        String consulta="";
        try {
            tb_Muestra_Examen.setModel(new DefaultTableModel());
            String titulos[]={"Nº","Código","Muestra","Usuario"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            LAB_Analisis_Examen obj=new LAB_Analisis_Examen();
            consulta="exec sp_LAB_MUESTRA_EXAMEN_buscar ?";

            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarMuestra.getText());
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
            tb_Muestra_Examen.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Muestra_Examen.setRowSorter(elQueOrdena);
            this.tb_Muestra_Examen.setModel(m);

            LAB_Muestra_formato();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnBuscarMuestraActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
       buscar_muestra.setVisible(true);
       LAB_Muestra_Examen_cargar();
       LAB_Muestra_formato();
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void btn_QuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_QuitarActionPerformed
        try{
            int filaselec=tb_Muestras.getSelectedRow();
            if( filaselec>=0){   
                int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea QUITAR la Muestra?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(eliminar == 0 ){
       
                    DefaultTableModel modelo = (DefaultTableModel)tb_Muestras.getModel(); 
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione la Muestra a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }
    }//GEN-LAST:event_btn_QuitarActionPerformed

    private void txtClasificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClasificacionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClasificacionKeyReleased
    public void enableDatos(){
    btnBuscarClasif.setEnabled(true);
    btnBuscarCPT.setEnabled(true);
    txtNombreExamen.setEnabled(true);
    txtAbrev.setEnabled(true);
    cbxTipoProc.setEnabled(true);
    cbxRestric.setEnabled(true);
    txtExplicacion.setEnabled(true);
    chActivo.setEnabled(true);
    txtObservacion.setEnabled(true);
    tb_Muestras.setEnabled(true);
    btn_Agregar.setEnabled(true);
    btn_Quitar.setEnabled(true);
    tb_Muestras.setBackground(Color.white);
}
    public void limpiar(){
    //codigo
        LAB_Analisis_Examen u=new LAB_Analisis_Examen();
        txtCodigo.setText(u.LAB_Analisis_Examen_generarid());
   if(txtCodigo.getText().equalsIgnoreCase("")){
        txtCodigo.setText("AE0000000000001");
    }
   btnBuscarCPT.setEnabled(false);
    txtCodClasificacion.setText("");
    txtClasificacion.setText("");
    txtCodigoCPT.setText("");
    txtCodNomen.setText("");
    txtNomen.setText("");
    txtTiempoHora.setText("");
    txtTiempoMin.setText("");
    txtNombreExamen.setText("");
    txtAbrev.setText("");
    cbxTipoProc.setSelectedIndex(0);
    cbxRestric.setSelectedIndex(0);
    chActivo.setSelected(true);
    txtExplicacion.setText("");
    txtObservacion.setText("");
        txtGuarModif.setText("G");
   btnguardar.setEnabled(true);
   btnmodificar.setEnabled(false);
   btneliminar.setEnabled(false);
   DefaultTableModel modelo = (DefaultTableModel)tb_Muestras.getModel(); 
   int filas=tb_Muestras.getRowCount();
   for(int i=0;i<filas;i++){
                    modelo.removeRow(0);
   }
}
    public void guardarDetalle(){
         for (int i = 0; i < tb_Muestras.getRowCount(); i++){      
               LAB_Analisis_Detalle rd=new LAB_Analisis_Detalle();
               rd.setCod_exa_ana(txtCodigo.getText());
               rd.setCod_muestra_para_exa(tb_Muestras.getValueAt(i, 0).toString());
                rd.LAB_Analisis_Detalle_guardar();
        
			}
        }
    public boolean repiteDetalle(){
         int filaselec=tb_Muestra_Examen.getSelectedRow();
         boolean c=false;
         for (int i = 0; i < tb_Muestras.getRowCount(); i++){    
               if(tb_Muestras.getValueAt(i, 0).toString().equalsIgnoreCase(tb_Muestra_Examen.getValueAt(filaselec, 1).toString())){
                    c=true;
			}}
               return c;
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
            java.util.logging.Logger.getLogger(frm_LAB_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LAB_ANALISIS_EXAMEN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new frm_LAB_ANALISIS_EXAMEN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    public static javax.swing.JButton btnBuscarCPT;
    public static javax.swing.JButton btnBuscarClasif;
    private javax.swing.JButton btnBuscarMuestra;
    private javax.swing.JButton btnBuscarNomen;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btn_Agregar;
    public static javax.swing.JButton btn_Quitar;
    public static javax.swing.JButton btneliminar;
    public static javax.swing.JButton btnguardar;
    public static javax.swing.JButton btnmodificar;
    private javax.swing.JDialog buscar_clasificacion;
    private javax.swing.JDialog buscar_muestra;
    private javax.swing.JDialog buscar_nomenclatura;
    public static javax.swing.JComboBox cbxRestric;
    public static javax.swing.JComboBox cbxTipoProc;
    public static javax.swing.JCheckBox chActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel jpanel;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JPanel panelMuestras;
    public static javax.swing.JTable tb_Clasificacion;
    public static javax.swing.JTable tb_Muestra_Examen;
    public static javax.swing.JTable tb_Muestras;
    public static javax.swing.JTable tb_Nomenclatura;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtAbrev;
    private javax.swing.JTextField txtBuscarMuestra;
    public static javax.swing.JTextField txtClasificacion;
    public static javax.swing.JTextField txtCodClasificacion;
    public static javax.swing.JTextField txtCodNomen;
    public static javax.swing.JTextField txtCodigo;
    public static javax.swing.JTextField txtCodigoCPT;
    public static javax.swing.JTextArea txtExplicacion;
    private javax.swing.JTextField txtGuarModif;
    public static javax.swing.JTextField txtNombreExamen;
    public static javax.swing.JTextField txtNomen;
    public static javax.swing.JTextArea txtObservacion;
    public static javax.swing.JTextField txtTiempoHora;
    public static javax.swing.JTextField txtTiempoMin;
    private javax.swing.JTextField txtbuscarClasifi;
    private javax.swing.JTextField txtbuscarNomen;
    // End of variables declaration//GEN-END:variables
}
