/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.sectorizacion;

import modelos.sectorizacion.Sector;
import vista.admisionCentral.FrmAdmision;
import vista.admisionCentral.FrmNuevaHistoriaC;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import servicios.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import static vista.Principal.fechaActual;
import modelos.*;
import static vista.admisionCentral.FrmNuevaHistoriaC.btnEliminar;
import static vista.admisionCentral.FrmNuevaHistoriaC.btnGuardar;
import static vista.admisionCentral.FrmNuevaHistoriaC.btnModificar;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDepartamento;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDepartamentoNac;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDistrito;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxDistritoNac;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxEstadoCivil;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxProvincia;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxProvinciaNac;
import static vista.admisionCentral.FrmNuevaHistoriaC.cbxTipoDireccion;
import static vista.admisionCentral.FrmNuevaHistoriaC.lblUsuUsuario;

public class FrmSector extends javax.swing.JFrame implements Runnable{
    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
    Connection conexion=null;
    Conexion c=new Conexion();
    ResultSet r;
    Sector se = new Sector();
    DefaultTableModel m;
    /**
     * Creates new form FrmSector
     */
    public FrmSector() {
        initComponents();
        cabecera();
        conexion = c.conectar();
        h1 = new Thread(this);
        h1.start();
        Calendar cal=Calendar.getInstance(); 
        String hora=cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
        lblFecha.setText(fechaActual());
        cbxDepartamentoS.setModel(departamento());
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);//en el centro
        habilitarBotones(true);
        habilitarOpciones(false);
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Edificio-24.png")).getImage());
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                dispose();
            }
        });
        cerrar();
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    dispose();
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public DefaultComboBoxModel departamento(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_DEPARTAMENTO"); 
              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "NOMBRE_DEPARTAMENTO" ) );              
            }   
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
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
    
    public void habilitarOpciones(boolean opcion){
        cbxDepartamentoS.setEnabled(opcion);
        cbxProvinciaS.setEnabled(opcion);
        cbxDistrito.setEnabled(opcion);
        cbxSector.setEnabled(opcion);
        cbxTipoDireccion.setEnabled(opcion);
        txtDireccion.setEnabled(opcion);
    }
    
    public void habilitarBotones(boolean opcion){
        btnNuevo.setEnabled(opcion);
        btnGuardar.setEnabled(!opcion);
        btnModificar.setEnabled(!opcion);
        btnEliminar.setEnabled(!opcion);
        btnBuscar.setEnabled(opcion);
    }
    
    public void limpiarDatos(){
        txtID.setText(se.idSector());
        cbxDepartamentoS.setSelectedIndex(0);
        cbxProvinciaS.setSelectedIndex(0);
        cbxDistrito.setSelectedIndex(0);
        cbxSector.setSelectedIndex(0);
        cbxTipoDireccion.setSelectedIndex(0);
        txtDireccion.setText("");
    }
    
    public void cabecera(){
        String titulos[]={"ID","Departamento","Provincia","Distrito","N° Sector","Tipo dirección","Dirección",
                              "PC de creación","Fecha de creación","Hora de creación","Estado","Usuario"};
                m=new DefaultTableModel(null,titulos);
                JTable p=new JTable(m);
                tbSectorizacion.setModel(m);
                TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
                formatoSector();
            tbSectorizacion.setRowSorter(elQueOrdena);
            this.tbSectorizacion.setModel(m);
    }
    
    public void formatoSector(){
            tbSectorizacion.getColumnModel().getColumn(0).setPreferredWidth(170);
            tbSectorizacion.getColumnModel().getColumn(1).setPreferredWidth(160);
            tbSectorizacion.getColumnModel().getColumn(2).setPreferredWidth(160);
            tbSectorizacion.getColumnModel().getColumn(3).setPreferredWidth(170);
            tbSectorizacion.getColumnModel().getColumn(4).setPreferredWidth(160);
            tbSectorizacion.getColumnModel().getColumn(5).setPreferredWidth(80);
            tbSectorizacion.getColumnModel().getColumn(6).setPreferredWidth(150);
            tbSectorizacion.getColumnModel().getColumn(7).setPreferredWidth(150);
            tbSectorizacion.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbSectorizacion.getColumnModel().getColumn(9).setPreferredWidth(100);
            tbSectorizacion.getColumnModel().getColumn(10).setPreferredWidth(50);
            tbSectorizacion.getColumnModel().getColumn(11).setPreferredWidth(100);
    }
    
    public void buscar_Sector(int index, String descripcion, String estado){
    String consulta="";
        try {
            tbSectorizacion.setModel(new DefaultTableModel());
            String titulos[]={"ID","Departamento","Provincia","Distrito","N° Sector","Tipo dirección","Dirección",
                              "PC de creación","Fecha de creación","Hora de creación","Estado","Usuario"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC SP_SISTEMASECTOR_BUSQUEDA ?,?,?";
            PreparedStatement cmd = se.getCn().prepareStatement(consulta);
            cmd.setInt(1, index);
            cmd.setString(2, descripcion);
            cmd.setString(3, estado);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de sector
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // dni
                fila[4]=r.getString(5); // apellido pat
                fila[5]=r.getString(6); // tipo direccion
                fila[6]=r.getString(7); // direccion
                fila[7]=r.getString(8); // 2do nombre
                fila[8]=r.getString(9); // 3er nombre
                fila[9]=r.getString(10); // fecha de nacimiento
                fila[10]=r.getString(11); // genero
                fila[11]=r.getString(12); // nombre departamento actual
                    m.addRow(fila);
                    c++;
            }
            tbSectorizacion.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tbSectorizacion.setRowSorter(elQueOrdena);
            this.tbSectorizacion.setModel(m);
            formatoSector();
        } catch (Exception e) {
            System.out.println("Error_buscar_Sector: " + e.getMessage());
        }
    }
    
    public void btnGuardar(){
    ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Guardar-32.png")); 
        Sector se2 = new Sector();
        if(cbxDepartamentoS.getSelectedIndex()==0 || cbxProvinciaS.getSelectedIndex() == 0 || 
           cbxDistrito.getSelectedIndex() ==  0 || cbxSector.getSelectedIndex() == 0 || 
           cbxTipoDireccion.getSelectedIndex() == 0 || txtDireccion.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
            if(guardar == 0 ){
                Sector se1 = new Sector();
                se1.setSe_id(txtID.getText());
                se1.setCod_dis(se1.codDistrito(cbxDistrito.getSelectedItem().toString()));
                se1.setSe_cod(cbxSector.getSelectedItem().toString());
                se1.setSe_tipo_dir(cbxTipoDireccion.getSelectedItem().toString());
                se1.setSe_dir(txtDireccion.getText());
                se1.setSe_usuario(lblUsuUsuario.getText());
                se1.setCod_prov(se1.codProvincia(String.valueOf(cbxProvinciaS.getSelectedItem().toString())));
                se1.setCod_dep(se1.codDepartamento(cbxDepartamentoS.getSelectedItem().toString()));
                    //REGISTRAR HISTORIA CLINICA
                    if(se1.nuevaDireccion()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                            limpiarDatos();
                            habilitarBotones(true);
                            habilitarOpciones(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }
                    limpiarDatos();
                    btnGuardar.setEnabled(false);
                    habilitarOpciones(false);
                    }
                }
    }
    
    public void verificaDireccion(String tipo, String direccion, String dpto, String prov, String distrito, String sector){
        try {
            PreparedStatement cmd = se.getCn().prepareStatement("SELECT * FROM SISTEMA_SECTOR SE, SISTEMA_DEPARTAMENTO SD, SISTEMA_PROVINCIA SP, SISTEMA_DISTRITO SDIS \n" +
"         WHERE SE_TIPO_DIR LIKE '"+tipo+"'\n" +
"         AND SE_DIR = '"+direccion+"'\n" +
"         AND SE.COD_DIS = SDIS.cod_dis\n" +
"         AND SE.COD_DEP = SD.cod_dep\n" +
"         AND SE.COD_PROV = SP.cod_prov\n" +
"         AND SD.nombre_departamento = '"+dpto+"'\n" +
"         AND SP.nombre_provincia = '"+prov+"'\n" +
"         AND SDIS.nombre_distrito = '"+distrito+"'\n" +
"		 AND SE.SE_COD = '"+sector+"'\n" +
"		 AND SE.SE_ESTADO = 'A'");
            ResultSet res = cmd.executeQuery();
            if(res.next()){
                JOptionPane.showMessageDialog(null, "Es posible que esta dirección ya se ecuentre \n \t                 en la Base de Datos");
            }else {btnGuardar();
                res.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error_verificaDireccion: " + e.toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarSector = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        titulo6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSectorizacion = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbxTipoBusqueda = new javax.swing.JComboBox<String>();
        txtBuscarSector = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        rbtActivos = new javax.swing.JRadioButton();
        rbtInactivos = new javax.swing.JRadioButton();
        rbtTodos = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxDepartamentoS = new javax.swing.JComboBox<String>();
        cbxProvinciaS = new javax.swing.JComboBox<String>();
        cbxDistrito = new javax.swing.JComboBox<String>();
        cbxSector = new javax.swing.JComboBox<String>();
        cbxTipoDireccion = new javax.swing.JComboBox<String>();
        txtDireccion = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();

        BuscarSector.setMinimumSize(new java.awt.Dimension(750, 450));
        BuscarSector.setResizable(false);

        jPanel9.setBackground(new java.awt.Color(153, 0, 51));
        jPanel9.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo6.setBackground(new java.awt.Color(153, 0, 51));
        titulo6.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        titulo6.setForeground(new java.awt.Color(255, 255, 255));
        titulo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo6.setText("Sectorización");
        titulo6.setToolTipText("");
        titulo6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(titulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        tbSectorizacion = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbSectorizacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSectorizacion.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbSectorizacion.getTableHeader().setReorderingAllowed(false);
        tbSectorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSectorizacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbSectorizacion);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Buscar por:");

        cbxTipoBusqueda.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cbxTipoBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "N° de Sector", "Distrito", "Dirección" }));
        cbxTipoBusqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoBusquedaItemStateChanged(evt);
            }
        });

        txtBuscarSector.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarSector.setEnabled(false);
        txtBuscarSector.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarSectorCaretUpdate(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Mostrar:");

        buttonGroup1.add(rbtActivos);
        rbtActivos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtActivos.setText("Activos (I)");
        rbtActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtActivosActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtInactivos);
        rbtInactivos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtInactivos.setText("Inactivos (D)");
        rbtInactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtInactivosActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtTodos);
        rbtTodos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtTodos.setText("Todos");
        rbtTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtTodos)
                    .addComponent(rbtInactivos)
                    .addComponent(jLabel9)
                    .addComponent(rbtActivos))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(rbtActivos)
                .addGap(18, 18, 18)
                .addComponent(rbtInactivos)
                .addGap(18, 18, 18)
                .addComponent(rbtTodos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxTipoBusqueda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscarSector)
                    .addComponent(jLabel8)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cbxTipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarSector, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BuscarSectorLayout = new javax.swing.GroupLayout(BuscarSector.getContentPane());
        BuscarSector.getContentPane().setLayout(BuscarSectorLayout);
        BuscarSectorLayout.setHorizontalGroup(
            BuscarSectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarSectorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        BuscarSectorLayout.setVerticalGroup(
            BuscarSectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarSectorLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BuscarSectorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sectorización");

        jPanel8.setBackground(new java.awt.Color(101, 166, 136));
        jPanel8.setPreferredSize(new java.awt.Dimension(500, 65));

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Sectorización");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel14.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fecha:");

        lblFecha.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("00/00/00");

        jLabel15.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hora:");

        lblHora.setFont(new java.awt.Font("Palatino Linotype", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("00:00:00");

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Nombre");

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 1)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("Nuevo (Alt + N)");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 1)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Grabar");
        btnGuardar.setToolTipText("Guardar (Alt + G)");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 1)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("Modificar (Alt + M)");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 1)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Eliminar (Alt + E)");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 1)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Buscar (Alt + B)");
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(3, 3, 3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHora, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHora)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblFecha)))
                    .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUsuUsuario, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("ID");

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtID.setForeground(new java.awt.Color(102, 102, 102));
        txtID.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtID.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Distrito");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Sector");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Tipo dirección");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Dirección");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Provincia");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Departamento");

        cbxDepartamentoS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDepartamentoS.setForeground(new java.awt.Color(102, 102, 102));
        cbxDepartamentoS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDepartamentoS.setBorder(null);
        cbxDepartamentoS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxDepartamentoS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDepartamentoSItemStateChanged(evt);
            }
        });
        cbxDepartamentoS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDepartamentoSKeyPressed(evt);
            }
        });

        cbxProvinciaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxProvinciaS.setForeground(new java.awt.Color(102, 102, 102));
        cbxProvinciaS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxProvinciaS.setBorder(null);
        cbxProvinciaS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxProvinciaS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxProvinciaSItemStateChanged(evt);
            }
        });
        cbxProvinciaS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxProvinciaSKeyPressed(evt);
            }
        });

        cbxDistrito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDistrito.setForeground(new java.awt.Color(102, 102, 102));
        cbxDistrito.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxDistrito.setBorder(null);
        cbxDistrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoItemStateChanged(evt);
            }
        });
        cbxDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDistritoKeyPressed(evt);
            }
        });

        cbxSector.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxSector.setForeground(new java.awt.Color(102, 102, 102));
        cbxSector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar..." }));
        cbxSector.setBorder(null);
        cbxSector.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSectorKeyPressed(evt);
            }
        });

        cbxTipoDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxTipoDireccion.setForeground(new java.awt.Color(102, 102, 102));
        cbxTipoDireccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar...", "Urb", "Calle", "Barrio", "Av", "Psje", "AA.HH.", "Prol" }));
        cbxTipoDireccion.setBorder(null);
        cbxTipoDireccion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxTipoDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxTipoDireccionKeyPressed(evt);
            }
        });

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(102, 102, 102));
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Edit-16.png"))); // NOI18N
        jLabel28.setText("Alt + M");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Document-16.png"))); // NOI18N
        jLabel41.setText("Alt + N");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Save-16.png"))); // NOI18N
        jLabel42.setText("Alt + G");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Eliminar-16.png"))); // NOI18N
        jLabel43.setText("Alt + E");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
        jLabel44.setText("Alt + B");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/salir.png"))); // NOI18N
        jLabel45.setText("Salir (ESC)");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel41)
                .addGap(6, 6, 6)
                .addComponent(jLabel42)
                .addGap(6, 6, 6)
                .addComponent(jLabel28)
                .addGap(6, 6, 6)
                .addComponent(jLabel43)
                .addGap(6, 6, 6)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel28)
                    .addComponent(jLabel42)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(38, 38, 38)))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxDepartamentoS, 0, 186, Short.MAX_VALUE)
                            .addComponent(cbxProvinciaS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxDistrito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxSector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTipoDireccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDireccion)))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addComponent(cbxDepartamentoS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addComponent(cbxProvinciaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxTipoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarOpciones(true);
        habilitarBotones(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
        limpiarDatos();
        cbxDepartamentoS.requestFocus();
        cbxDepartamentoS.showPopup();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String tipo = cbxTipoDireccion.getSelectedItem().toString();
        String direccion = txtDireccion.getText();
        String dpto = cbxDepartamentoS.getSelectedItem().toString();
        String prov = cbxProvinciaS.getSelectedItem().toString();
        String distrito = cbxDistrito.getSelectedItem().toString();
        String sector = cbxSector.getSelectedItem().toString();
        String provincia = cbxProvinciaS.getSelectedItem().toString();
        String departamento = cbxDepartamentoS.getSelectedItem().toString();
        verificaDireccion(tipo,direccion,dpto,prov,distrito,sector);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Guardar-32.png")); 
        if(btnModificar.getText().equals("Modificar")){
            habilitarOpciones(true);
            btnGuardar.setEnabled(true);
            btnEliminar.setEnabled(false);
            btnGuardar.setEnabled(false);
            btnNuevo.setEnabled(true);
            btnModificar.setText("Actualizar");
            cbxDepartamentoS.setEnabled(false);
            cbxProvinciaS.setEnabled(false);
            cbxDistrito.setEnabled(false);
        } else 
        if(btnModificar.getText().equals("Actualizar")){
            if(txtID.getText() != (se.idSector())){
                int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
                if(modificar == 0 ){
                    Sector se2 = new Sector();
                    se2.setSe_id(txtID.getText());
                    se2.setCod_dep(se2.codDepartamento(cbxDepartamentoS.getSelectedItem().toString()));
                    se2.setCod_prov(se2.codProvincia(cbxProvinciaS.getSelectedItem().toString()));
                    se2.setCod_dis(se2.codDistrito(cbxDistrito.getSelectedItem().toString()));
                    se2.setSe_cod(cbxSector.getSelectedItem().toString());
                    se2.setSe_tipo_dir(cbxTipoDireccion.getSelectedItem().toString());
                    se2.setSe_dir(txtDireccion.getText());
                        
                    if(se2.modificarDireccion()==true){
                        JOptionPane.showMessageDialog(this, "Datos Actualizados");
                        limpiarDatos();
                        habilitarBotones(true);
                        habilitarOpciones(false);
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al actualizar los datos");
                       }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }
        }
            btnModificar.setText("Modificar");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(btnEliminar.getText().equals("Eliminar")){
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Basura-32.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 ){
                Sector se2 = new Sector();
                se2.setSe_id(txtID.getText());
                if(se2.eliminarDireccion()){
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    limpiarDatos();
                }
                habilitarBotones(true);
                habilitarOpciones(false);
            } 
        }catch(Exception e){
            System.out.println("Error_btnElimminar: " + e.toString());
        }
        }
        if(btnEliminar.getText().equals("Restaurar")){
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Restaurar-32.png")); 
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea RESTAURAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{
            if(eliminar == 0 ){
                Sector se2 = new Sector();
                se2.setSe_id(txtID.getText());
                if(se2.restaurarDireccion()){
                    JOptionPane.showMessageDialog(this, "Datos resttaurados");
                    limpiarDatos();
                }
                habilitarBotones(true);
                habilitarOpciones(false);
            } 
        }catch(Exception e){
            System.out.println("Error_btnElimminar_restaurar: " + e.toString());
        }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarSector.setVisible(true);
        BuscarSector.getContentPane().setBackground(Color.WHITE);
        BuscarSector.setLocationRelativeTo(null);//en el centro
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxDepartamentoSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoSItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDepartamentoS.getSelectedIndex()>0){
                        this.cbxProvinciaS.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String dpto=cbxDepartamentoS.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_PROVINCIA '"+dpto+"'");
                    this.cbxProvinciaS.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxProvinciaS.addItem(rs.getString("NOMBRE_PROVINCIA"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxProvinciaS.removeAllItems();

                        this.cbxProvinciaS.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error_cbxDepartamento: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDepartamentoSItemStateChanged

    private void cbxProvinciaSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxProvinciaSKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDistrito.requestFocus();
            cbxDistrito.showPopup();
        }
    }//GEN-LAST:event_cbxProvinciaSKeyPressed

    private void cbxProvinciaSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaSItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxProvinciaS.getSelectedIndex()>0){
                        this.cbxDistrito.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String prov=cbxProvinciaS.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_ADMISION_HISTORIACLINICA_NOMBRE_DISTRITO '"+prov+ "'");
                    this.cbxDistrito.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxDistrito.addItem(rs.getString("NOMBRE_DISTRITO"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxDistrito.removeAllItems();

                        this.cbxDistrito.addItem("Seleccionar...");
                            }
            }}
            catch(Exception ex) 
            {
                System.out.println("Error_cbxProvincia: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxProvinciaSItemStateChanged

    private void cbxDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoItemStateChanged
        try{  
                if(evt.getStateChange()==ItemEvent.SELECTED){
                    if(this.cbxDistrito.getSelectedIndex()>0){
                        this.cbxSector.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String distrito=cbxDistrito.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC SP_SISTEMASECTOR_LISTAR '"+se.codDistrito(distrito)+"'");
                    this.cbxSector.addItem("Seleccionar...");
                    while(rs.next()){
                     this.cbxSector.addItem(rs.getString("SE_COD"));
                  //  this.cbxProvincia.setModel(null);
                    }
                     }else{
                            this.cbxSector.removeAllItems();

                        this.cbxSector.addItem("Seleccionar...");
                            }

            }}
            catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDistritoItemStateChanged

    private void txtBuscarSectorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarSectorCaretUpdate
        int index = cbxTipoBusqueda.getSelectedIndex();
        if(rbtActivos.isSelected()== false && rbtInactivos.isSelected() == false && rbtTodos.isSelected()==false && txtBuscarSector.isEnabled() == true){
            JOptionPane.showMessageDialog(BuscarSector, "Debe seleccionar una opción \n ");
        } else
        if(rbtActivos.isSelected()==true){
            buscar_Sector(index, txtBuscarSector.getText(), "A");
        } else 
        if(rbtInactivos.isSelected()==true){
            buscar_Sector(index, txtBuscarSector.getText(), "D");
        } else
        if(rbtTodos.isSelected()==true){
            buscar_Sector(index, txtBuscarSector.getText(), "");
        }
    }//GEN-LAST:event_txtBuscarSectorCaretUpdate

    private void rbtActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtActivosActionPerformed
        txtBuscarSector.setText("");
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscarSector.getText();
        String opcion = "";
        buscar_Sector(index, "", "A");
        if(index != 0){
        txtBuscarSector.setEnabled(true);
        }
    }//GEN-LAST:event_rbtActivosActionPerformed

    private void rbtInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtInactivosActionPerformed
        txtBuscarSector.setText("");
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscarSector.getText();
        String opcion = "";
        buscar_Sector(index, "", "D");
        if(index != 0){
        txtBuscarSector.setEnabled(true);
        }
    }//GEN-LAST:event_rbtInactivosActionPerformed

    private void rbtTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTodosActionPerformed
        txtBuscarSector.setText("");
        int index = cbxTipoBusqueda.getSelectedIndex();
        String busqueda = txtBuscarSector.getText();
        String opcion = "";
        buscar_Sector(index, "", "");
        if(index != 0){
        txtBuscarSector.setEnabled(true);
        }
    }//GEN-LAST:event_rbtTodosActionPerformed

    private void cbxTipoBusquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoBusquedaItemStateChanged
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.cbxTipoBusqueda.getSelectedIndex()>0){
                    txtBuscarSector.setEnabled(true);
                    txtBuscarSector.setText("");
                }
            }
            else{
                txtBuscarSector.setEnabled(false);
                }}
        catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxTipoBusquedaItemStateChanged

    private void tbSectorizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSectorizacionKeyPressed
       char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tbSectorizacion.getSelectedRow();
            FrmSector frmSec = new FrmSector();
            frmSec.setVisible(true);
            BuscarSector.setVisible(false);
            dispose();
            frmSec.txtID.setText(String.valueOf(tbSectorizacion.getValueAt(fila, 0)));  
            frmSec.cbxDepartamentoS.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,1)));
            frmSec.cbxProvinciaS.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,2)));
            frmSec.cbxDistrito.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,3)));
            frmSec.cbxSector.setSelectedItem(tbSectorizacion.getValueAt(fila, 4));
            frmSec.cbxTipoDireccion.setSelectedItem(tbSectorizacion.getValueAt(fila, 5));
            frmSec.txtDireccion.setText(String.valueOf(tbSectorizacion.getValueAt(fila, 6))); 
            frmSec.habilitarOpciones(false);
            if(String.valueOf(tbSectorizacion.getValueAt(fila, 10)).equals("D")){
                btnEliminar.setText("Restaurar");
                btnModificar.setEnabled(false);
                ImageIcon iRestaurar=new ImageIcon(this.getClass().getResource("/imagenes/iconos/Restaurar-32.png")); 
                btnEliminar.setIcon(iRestaurar);
            } else {
                btnModificar.setEnabled(true);
            }
            btnEliminar.setEnabled(true);
       }
    }//GEN-LAST:event_tbSectorizacionKeyPressed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        txtDireccion.setText(txtDireccion.getText().toUpperCase());
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void cbxDepartamentoSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentoSKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxProvinciaS.requestFocus();
            cbxProvinciaS.showPopup();
        }
    }//GEN-LAST:event_cbxDepartamentoSKeyPressed

    private void cbxDistritoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDistritoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxSector.requestFocus();
            cbxSector.showPopup();
        }
    }//GEN-LAST:event_cbxDistritoKeyPressed

    private void cbxSectorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSectorKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxTipoDireccion.requestFocus();
            cbxTipoDireccion.showPopup();
        }
    }//GEN-LAST:event_cbxSectorKeyPressed

    private void cbxTipoDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxTipoDireccionKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_cbxTipoDireccionKeyPressed

    //HORA
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
            java.util.logging.Logger.getLogger(FrmSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDialog BuscarSector;
    private javax.swing.JButton btnBuscar;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxDepartamentoS;
    private javax.swing.JComboBox<String> cbxDistrito;
    private javax.swing.JComboBox<String> cbxProvinciaS;
    private javax.swing.JComboBox<String> cbxSector;
    private javax.swing.JComboBox<String> cbxTipoBusqueda;
    private javax.swing.JComboBox<String> cbxTipoDireccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JRadioButton rbtActivos;
    private javax.swing.JRadioButton rbtInactivos;
    private javax.swing.JRadioButton rbtTodos;
    private javax.swing.JTable tbSectorizacion;
    private javax.swing.JLabel titulo5;
    private javax.swing.JLabel titulo6;
    private javax.swing.JTextField txtBuscarSector;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
