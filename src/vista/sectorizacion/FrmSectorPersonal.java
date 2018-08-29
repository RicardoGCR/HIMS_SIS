/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.sectorizacion;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Usuario;
import modelos.sectorizacion.Sector;
import servicios.Conexion;

/**
 *
 * @author SITEMAS
 */
public class FrmSectorPersonal extends javax.swing.JFrame {
    Connection conexion=null;
    Conexion c=new Conexion();
    ResultSet r;
    Sector se = new Sector();
    DefaultTableModel m,m1;
    
    /**
     * Creates new form FrmSectorPersonal
     */
    public void mostrar_sector_personal(){
    String consulta="";
        try {
            tbSectorizacion.setModel(new DefaultTableModel());
            String titulos[]={"ID","N° Sector Ini.","N° Sector Term.","Departamento","Provincia","Distrito","Personal","Fecha Inicio","Fecha Termino","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];
            //int index = cbxTipoBusqueda.getSelectedIndex();
            consulta="EXEC [SP_SISTEMASECTOR_BUSQUEDA_PERSONAL]";
            PreparedStatement cmd = se.getCn().prepareStatement(consulta);
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de sector
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4); // dni
                fila[4]=r.getString(5); // apellido pat    
                fila[5]=r.getString(6); // apellido pat
                fila[6]=r.getString(7); // apellido pat
                fila[7]=r.getString(8); // apellido pat
                fila[8]=r.getString(9); // apellido pat        
                fila[9]=r.getString(10); // apellido pat
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
    public void formatoSector(){
            tbSectorizacion.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbSectorizacion.getColumnModel().getColumn(1).setPreferredWidth(100); 
            tbSectorizacion.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbSectorizacion.getColumnModel().getColumn(3).setPreferredWidth(150);
            tbSectorizacion.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbSectorizacion.getColumnModel().getColumn(5).setPreferredWidth(150);
            tbSectorizacion.getColumnModel().getColumn(6).setPreferredWidth(150);   
            tbSectorizacion.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbSectorizacion.getColumnModel().getColumn(8).setPreferredWidth(100);
            tbSectorizacion.getColumnModel().getColumn(9).setPreferredWidth(50);
    }
    public DefaultComboBoxModel departamento(){
        try {
             DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
            String   sql = null;
            ResultSet rs = null;
            Statement st=conexion.createStatement(); 
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
        } catch (Exception e) {
            System.out.println(e.toString());
        }
       return null;
    }
    public void BuscarPersonal_UO(){
        try {
                     
            String consulta="";
            
            tb_Personal_UO.setModel(new DefaultTableModel());
            String titulos[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID"};
            m1=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m1);
            String fila[]=new String[11];
            Usuario obj=new Usuario();
            consulta="exec PERSONAL_BUSCAR_PER_UO ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);            
            cmd.setString(1, txtBuscarMedico_UO.getText());
            
            ResultSet r= cmd.executeQuery();
            int c = 1;
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
                
                m1.addRow(fila);
                c++;
            }
            tb_Personal_UO.setModel(m1);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m1);
            tb_Personal_UO.setRowSorter(elQueOrdena);
            tb_Personal_UO.setModel(m1);
                       
            formatoPersonal_UO();
            
  
        }catch (Exception e) {
            System.out.println("Error mostrar paciente: " + e.getMessage());
        }
        
    }
    public void formatoPersonal_UO(){        
            tb_Personal_UO.getColumnModel().getColumn(0).setPreferredWidth(50);
            tb_Personal_UO.getColumnModel().getColumn(1).setPreferredWidth(50); 
            tb_Personal_UO.getColumnModel().getColumn(2).setPreferredWidth(110);
            tb_Personal_UO.getColumnModel().getColumn(3).setPreferredWidth(130);
            tb_Personal_UO.getColumnModel().getColumn(4).setPreferredWidth(130);                
            tb_Personal_UO.getColumnModel().getColumn(5).setPreferredWidth(230); 
            tb_Personal_UO.getColumnModel().getColumn(6).setPreferredWidth(170); 
            tb_Personal_UO.getColumnModel().getColumn(7).setPreferredWidth(80);
            tb_Personal_UO.getColumnModel().getColumn(8).setPreferredWidth(150);
            tb_Personal_UO.getColumnModel().getColumn(9).setPreferredWidth(150);
            tb_Personal_UO.getColumnModel().getColumn(10).setPreferredWidth(80);
            
    }
    public void Mostar(){
        try {
            int fila = tbSectorizacion.getSelectedRow();
            departamento();
            cbxDepartamentoS.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,3)));
            Provincia();
            cbxProvinciaS.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,4)));
            Distrito();
            cbxDistrito.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,5)));
            Sector();
            cbxSector.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,1))); 
            cbxSector1.setSelectedItem(String.valueOf(tbSectorizacion.getValueAt(fila,2)));
            txtBuscarMedico_UO1.setText(String.valueOf(tbSectorizacion.getValueAt(fila,6)));
            txtfi.setText(String.valueOf(tbSectorizacion.getValueAt(fila,7)));
            txtft.setText(String.valueOf(tbSectorizacion.getValueAt(fila,8)));
        } catch (Exception e) {
        }
    }
    public FrmSectorPersonal() {
        initComponents();
        conexion = c.conectar();
        mostrar_sector_personal();
        cbxDepartamentoS.setModel(departamento());
        tbSectorizacion.getSelectionModel().setSelectionInterval(0,0);
        Mostar();
        this.setExtendedState(MAXIMIZED_BOTH);
        //ICONO DE FORMULARIO
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
        //BOTON CERRAR
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        // BOTON ESCAPE (ESC)
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                if (opc==0){
                        dispose();
                    }else{
                         btnGuardar.setEnabled(false);    
                    btnNuevo.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                    tbSectorizacion.setEnabled(true);
                    cbxDepartamentoS.setEnabled(false);
                    cbxDistrito.setEnabled(false);
                    cbxProvinciaS.setEnabled(false);
                    cbxSector.setEnabled(false);
                     cbxSector1.setEnabled(false);
                    txtBuscarMedico_UO1.setEnabled(false);  
                    txtfi.setEnabled(false);
                    txtft.setEnabled(false); 
                    T6.setEnabled(false);
                    mostrar_sector_personal();
                    tbSectorizacion.getSelectionModel().setSelectionInterval(0,0);
                    opc=0;
                    Mostar();
                    }
            }
        });
        cerrar();
    }
    
    public void cerrar (){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    if (opc==0){
                        dispose();
                    }else{
                        btnGuardar.setEnabled(false);    
                        btnNuevo.setEnabled(true);
                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                        tbSectorizacion.setEnabled(true);
                        cbxDepartamentoS.setEnabled(false);
                        cbxDistrito.setEnabled(false);
                        cbxProvinciaS.setEnabled(false);
                        cbxSector.setEnabled(false);
                         cbxSector1.setEnabled(false);
                        txtBuscarMedico_UO1.setEnabled(false);  
                        txtfi.setEnabled(false);
                        txtft.setEnabled(false); 
                        T6.setEnabled(false);
                        mostrar_sector_personal();
                        tbSectorizacion.getSelectionModel().setSelectionInterval(0,0);
                        Mostar();
                        opc=0;
                    }
                }
        });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MEDICOS_UO = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtBuscarMedico_UO = new javax.swing.JTextField();
        T5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Personal_UO = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        titulo5 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblUsuUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnEliminar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSectorizacion = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cbxDepartamentoS = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbxProvinciaS = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxDistrito = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxSector = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        ppersonal = new javax.swing.JPanel();
        txtBuscarMedico_UO1 = new javax.swing.JTextField();
        T6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtfi = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        txtft = new javax.swing.JFormattedTextField();
        cbxSector1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        MEDICOS_UO.setAlwaysOnTop(true);
        MEDICOS_UO.setMinimumSize(new java.awt.Dimension(700, 380));
        MEDICOS_UO.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(122, 77, 135));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Personal Unidad Organica");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtBuscarMedico_UO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarMedico_UO.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarMedico_UO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarMedico_UO.setToolTipText("");
        txtBuscarMedico_UO.setBorder(null);
        txtBuscarMedico_UO.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarMedico_UOCaretUpdate(evt);
            }
        });
        txtBuscarMedico_UO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMedico_UOActionPerformed(evt);
            }
        });
        txtBuscarMedico_UO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UOKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UOKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UOKeyTyped(evt);
            }
        });

        T5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        T5.setToolTipText("");
        T5.setContentAreaFilled(false);
        T5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        T5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(txtBuscarMedico_UO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBuscarMedico_UO, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(T5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(403, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        tb_Personal_UO = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tb_Personal_UO.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_Personal_UO.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_Personal_UO.setRowHeight(30);
        tb_Personal_UO.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tb_Personal_UO.getTableHeader().setReorderingAllowed(false);
        tb_Personal_UO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tb_Personal_UOKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tb_Personal_UO);

        javax.swing.GroupLayout MEDICOS_UOLayout = new javax.swing.GroupLayout(MEDICOS_UO.getContentPane());
        MEDICOS_UO.getContentPane().setLayout(MEDICOS_UOLayout);
        MEDICOS_UOLayout.setHorizontalGroup(
            MEDICOS_UOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        MEDICOS_UOLayout.setVerticalGroup(
            MEDICOS_UOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MEDICOS_UOLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 102));

        titulo5.setBackground(new java.awt.Color(153, 0, 51));
        titulo5.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
        titulo5.setForeground(new java.awt.Color(255, 255, 255));
        titulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo5.setText("Sectorización - Personal");
        titulo5.setToolTipText("");
        titulo5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-32.png"))); // NOI18N
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo.setIconTextGap(30);
        btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
        btnGuardar.setMnemonic('G');
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setEnabled(false);
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.setIconTextGap(30);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(240, 240, 240));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Editar-32.png"))); // NOI18N
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.setContentAreaFilled(false);
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(30);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Basura-32.png"))); // NOI18N
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(30);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblUsuUsuario.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        lblUsuUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuUsuario.setText("Nombre");

        jLabel19.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N

        btnEliminar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar1.setForeground(new java.awt.Color(240, 240, 240));
        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imprimir-32.png"))); // NOI18N
        btnEliminar1.setMnemonic('E');
        btnEliminar1.setText("Imprimir");
        btnEliminar1.setContentAreaFilled(false);
        btnEliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar1.setIconTextGap(30);
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnEliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(13, 13, 13))
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
        tbSectorizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSectorizacionMouseClicked(evt);
            }
        });
        tbSectorizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSectorizacionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbSectorizacion);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Departamento");

        cbxDepartamentoS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDepartamentoS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDepartamentoS.setBorder(null);
        cbxDepartamentoS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxDepartamentoS.setEnabled(false);
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Provincia");

        cbxProvinciaS.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxProvinciaS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        cbxProvinciaS.setBorder(null);
        cbxProvinciaS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxProvinciaS.setEnabled(false);
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Distrito");

        cbxDistrito.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxDistrito.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        cbxDistrito.setBorder(null);
        cbxDistrito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxDistrito.setEnabled(false);
        cbxDistrito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxDistritoItemStateChanged(evt);
            }
        });
        cbxDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDistritoActionPerformed(evt);
            }
        });
        cbxDistrito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxDistritoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Sector Inicio");

        cbxSector.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        cbxSector.setBorder(null);
        cbxSector.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSector.setEnabled(false);
        cbxSector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSectorKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("Personal");

        ppersonal.setBackground(new java.awt.Color(255, 255, 255));
        ppersonal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        ppersonal.setEnabled(false);

        txtBuscarMedico_UO1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtBuscarMedico_UO1.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarMedico_UO1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtBuscarMedico_UO1.setToolTipText("");
        txtBuscarMedico_UO1.setBorder(null);
        txtBuscarMedico_UO1.setEnabled(false);
        txtBuscarMedico_UO1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtBuscarMedico_UO1CaretUpdate(evt);
            }
        });
        txtBuscarMedico_UO1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMedico_UO1ActionPerformed(evt);
            }
        });
        txtBuscarMedico_UO1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UO1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UO1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarMedico_UO1KeyTyped(evt);
            }
        });

        T6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
        T6.setToolTipText("");
        T6.setContentAreaFilled(false);
        T6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        T6.setEnabled(false);
        T6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ppersonalLayout = new javax.swing.GroupLayout(ppersonal);
        ppersonal.setLayout(ppersonalLayout);
        ppersonalLayout.setHorizontalGroup(
            ppersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ppersonalLayout.createSequentialGroup()
                .addComponent(txtBuscarMedico_UO1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        ppersonalLayout.setVerticalGroup(
            ppersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ppersonalLayout.createSequentialGroup()
                .addGroup(ppersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(T6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarMedico_UO1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Fecha Inicio");

        try {
            txtfi.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtfi.setToolTipText("");
        txtfi.setEnabled(false);
        txtfi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("Fecha Termino");

        try {
            txtft.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtft.setToolTipText("");
        txtft.setEnabled(false);
        txtft.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cbxSector1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cbxSector1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        cbxSector1.setBorder(null);
        cbxSector1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxSector1.setEnabled(false);
        cbxSector1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSector1KeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Sector Termino");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(38, 38, 38)))
                                    .addComponent(jLabel10))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxProvinciaS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxDepartamentoS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxDistrito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSector1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfi, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtft, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxDepartamentoS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addComponent(cbxProvinciaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSector1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ppersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int opc=0;
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        try {
            opc=1;
            btnGuardar.setEnabled(true);    
            btnNuevo.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            tbSectorizacion.setEnabled(false);
            cbxDepartamentoS.setEnabled(true);
            cbxDepartamentoS.setSelectedItem("Seleccionar...");
            cbxDistrito.setEnabled(true);
            cbxProvinciaS.setEnabled(true);
            cbxSector.setEnabled(true); 
            cbxSector1.setEnabled(true);
            txtBuscarMedico_UO1.setEnabled(true);  
            txtBuscarMedico_UO1.setText("");
            txtfi.setEnabled(true);
            txtfi.setText("");
            txtft.setEnabled(true); 
            txtft.setText("");
            T6.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (opc==1){
                System.out.println(opc);
                Sector se1 = new Sector();
                se1.setSe_cod(cbxSector.getSelectedItem().toString());   
                se1.setSe_id(cbxSector1.getSelectedItem().toString());
                se1.setSe_alias(txtBuscarMedico_UO1.getText()); 
                se1.setSe_fech(txtfi.getText());
                se1.setSe_hora(txtft.getText());
                //REGISTRAR HISTORIA CLINICA
                if(se1.nuevoSectorPersonal()==true){
                    JOptionPane.showMessageDialog(this, "Datos Guardados");
                    btnGuardar.setEnabled(false);    
                    btnNuevo.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                    tbSectorizacion.setEnabled(true);
                    cbxDepartamentoS.setEnabled(false);
                    cbxDistrito.setEnabled(false);
                    cbxProvinciaS.setEnabled(false);
                    cbxSector.setEnabled(false);   
                    cbxSector1.setEnabled(false);
                    txtBuscarMedico_UO1.setEnabled(false);  
                    txtfi.setEnabled(false);
                    txtft.setEnabled(false); 
                    T6.setEnabled(false);
                    mostrar_sector_personal();
                    tbSectorizacion.getSelectionModel().setSelectionInterval(0,0);
                    opc=0;
               } else {
                   JOptionPane.showMessageDialog(this, "Error al guardar");
               }
            }
            if(opc==2){
                System.out.println(opc);
                Sector se1 = new Sector();
                int fila=tbSectorizacion.getSelectedRow();
                se1.setSe_usu(String.valueOf(tbSectorizacion.getValueAt(fila,0)));
                se1.setSe_cod(cbxSector.getSelectedItem().toString());   
                se1.setSe_id(cbxSector1.getSelectedItem().toString());
                se1.setSe_alias(txtBuscarMedico_UO1.getText()); 
                se1.setSe_fech(txtfi.getText());
                se1.setSe_hora(txtft.getText());
                //REGISTRAR HISTORIA CLINICA
               System.out.println( se1.getSe_alias());
                if(se1.modificarSectorPersonal()==true){
                    JOptionPane.showMessageDialog(this, "Datos Actualizados");
                    btnGuardar.setEnabled(false);    
                    btnNuevo.setEnabled(true);
                    btnEliminar.setEnabled(true);
                    btnModificar.setEnabled(true);
                    tbSectorizacion.setEnabled(true);
                    cbxDepartamentoS.setEnabled(false);
                    cbxDistrito.setEnabled(false);
                    cbxProvinciaS.setEnabled(false);
                    cbxSector.setEnabled(false);
                    cbxSector1.setEnabled(false);
                    txtBuscarMedico_UO1.setEnabled(false);  
                    txtfi.setEnabled(false);
                    txtft.setEnabled(false); 
                    T6.setEnabled(false);
                    mostrar_sector_personal();
                    tbSectorizacion.getSelectionModel().setSelectionInterval(fila,fila);
                    opc=0;
               } else {
                   JOptionPane.showMessageDialog(this, "Error al actualizar");
               }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error "+e.getMessage());
        }
              
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            opc=2;
            btnGuardar.setEnabled(true);    
            btnNuevo.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            tbSectorizacion.setEnabled(false);
            txtBuscarMedico_UO1.setEnabled(true);  
            cbxSector.setEnabled(true);      
            cbxSector1.setEnabled(true);      
            txtfi.setEnabled(true);
            txtft.setEnabled(true);
            T6.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            Sector se1 = new Sector();
            int fila=tbSectorizacion.getSelectedRow();
            se1.setSe_cod(String.valueOf(tbSectorizacion.getValueAt(fila,0)));
            //REGISTRAR HISTORIA CLINICA
            System.out.println( se1.getSe_alias());
            if(se1.eliminarSectorPersonal()==true){
                JOptionPane.showMessageDialog(this, "Datos Eliminados");
                mostrar_sector_personal();
                tbSectorizacion.getSelectionModel().setSelectionInterval(0,0);
           } else {
               JOptionPane.showMessageDialog(this, "Error al eliminar");
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar"+e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbSectorizacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSectorizacionKeyPressed
        
    }//GEN-LAST:event_tbSectorizacionKeyPressed
    public void Provincia(){
        try{
               
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

                }
                catch(Exception ex)
                {
                    System.out.println("Error_cbxDepartamento: " + ex.getMessage());
                }
    }
    private void cbxDepartamentoSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDepartamentoSItemStateChanged
        if(evt.getStateChange()==ItemEvent.SELECTED){
            Provincia();
        }
    }//GEN-LAST:event_cbxDepartamentoSItemStateChanged

    private void cbxDepartamentoSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDepartamentoSKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxProvinciaS.requestFocus();
            cbxProvinciaS.showPopup();
        }
    }//GEN-LAST:event_cbxDepartamentoSKeyPressed
    public void Distrito() throws SQLException{
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
    }
    private void cbxProvinciaSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxProvinciaSItemStateChanged
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
              Distrito();
            }}
            catch(Exception ex)
            {
                System.out.println("Error_cbxProvincia: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxProvinciaSItemStateChanged

    private void cbxProvinciaSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxProvinciaSKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxDistrito.requestFocus();
            cbxDistrito.showPopup();
        }
    }//GEN-LAST:event_cbxProvinciaSKeyPressed
    public void Sector(){
        try {
            if(this.cbxDistrito.getSelectedIndex()>0){
            this.cbxSector.removeAllItems();  
            this.cbxSector1.removeAllItems();
            Statement sta=conexion.createStatement();
            String provincia=cbxProvinciaS.getSelectedItem().toString();
            String distrito=cbxDistrito.getSelectedItem().toString();
            System.out.println(distrito);
            ResultSet rs=sta.executeQuery("EXEC SP_SISTEMASECTOR_LISTAR '"+provincia+"','"+distrito+"'");
            this.cbxSector.addItem("Seleccionar...");    
            this.cbxSector1.addItem("Seleccionar...");
            while(rs.next()){
                this.cbxSector.addItem(rs.getString("SE_COD"));      
                this.cbxSector1.addItem(rs.getString("SE_COD"));
                //  this.cbxProvincia.setModel(null);
            }
                }else{
                    this.cbxSector.removeAllItems();   
                    this.cbxSector1.removeAllItems();
                    this.cbxSector.addItem("Seleccionar...");
                    this.cbxSector1.addItem("Seleccionar...");
            }
        } catch (Exception e) {
        }
    }
    private void cbxDistritoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxDistritoItemStateChanged
        try{
            if(evt.getStateChange()==ItemEvent.SELECTED){
                Sector();
            }}
            catch(Exception ex)
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_cbxDistritoItemStateChanged

    private void cbxDistritoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxDistritoKeyPressed
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
            cbxSector.requestFocus();
            cbxSector.showPopup();
        }
    }//GEN-LAST:event_cbxDistritoKeyPressed

    private void cbxSectorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSectorKeyPressed
      
    }//GEN-LAST:event_cbxSectorKeyPressed

    private void txtBuscarMedico_UOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOCaretUpdate
        BuscarPersonal_UO();
    }//GEN-LAST:event_txtBuscarMedico_UOCaretUpdate

    private void txtBuscarMedico_UOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOActionPerformed

    }//GEN-LAST:event_txtBuscarMedico_UOActionPerformed

    private void txtBuscarMedico_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMedico_UOKeyPressed

    private void txtBuscarMedico_UOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyReleased
        txtBuscarMedico_UO.setText(txtBuscarMedico_UO.getText().toUpperCase());

    }//GEN-LAST:event_txtBuscarMedico_UOKeyReleased

    private void txtBuscarMedico_UOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }

    }//GEN-LAST:event_txtBuscarMedico_UOKeyTyped

    private void T5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T5ActionPerformed

    private void tb_Personal_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Personal_UOKeyPressed

        int filaselec=tb_Personal_UO.getSelectedRow();
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            if(filaselec<0){
                JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }else{
                String apep, apem, nom;
                apep = tb_Personal_UO.getValueAt(filaselec, 3).toString();
                apem = tb_Personal_UO.getValueAt(filaselec, 4).toString();
                nom = tb_Personal_UO.getValueAt(filaselec, 5).toString();
                txtBuscarMedico_UO1.setText(apep+" "+apem+" "+nom);
                MEDICOS_UO.dispose();
            }
        }
    }//GEN-LAST:event_tb_Personal_UOKeyPressed

    private void txtBuscarMedico_UO1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UO1CaretUpdate
        BuscarPersonal_UO();
    }//GEN-LAST:event_txtBuscarMedico_UO1CaretUpdate

    private void txtBuscarMedico_UO1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UO1ActionPerformed

    }//GEN-LAST:event_txtBuscarMedico_UO1ActionPerformed

    private void txtBuscarMedico_UO1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UO1KeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMedico_UO1KeyPressed

    private void txtBuscarMedico_UO1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UO1KeyReleased
        txtBuscarMedico_UO.setText(txtBuscarMedico_UO.getText().toUpperCase());

    }//GEN-LAST:event_txtBuscarMedico_UO1KeyReleased

    private void txtBuscarMedico_UO1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UO1KeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            BuscarPersonal_UO();
            MEDICOS_UO.setVisible(true);
        }

    }//GEN-LAST:event_txtBuscarMedico_UO1KeyTyped

    private void T6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T6ActionPerformed
        BuscarPersonal_UO();
        MEDICOS_UO.setVisible(true);
    }//GEN-LAST:event_T6ActionPerformed

    private void tbSectorizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSectorizacionMouseClicked
        Mostar();
    }//GEN-LAST:event_tbSectorizacionMouseClicked

    private void cbxSector1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSector1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSector1KeyPressed

    private void cbxDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDistritoActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        Sector se= new Sector();
            try {
                se.Sector();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Error Reporte"+e.getMessage());
            }        
        
    }//GEN-LAST:event_btnEliminar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmSectorPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSectorPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSectorPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSectorPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSectorPersonal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog MEDICOS_UO;
    private javax.swing.JButton T5;
    private javax.swing.JButton T6;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnEliminar1;
    public static javax.swing.JButton btnGuardar;
    public static javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxDepartamentoS;
    private javax.swing.JComboBox<String> cbxDistrito;
    private javax.swing.JComboBox<String> cbxProvinciaS;
    private javax.swing.JComboBox<String> cbxSector;
    private javax.swing.JComboBox<String> cbxSector1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel lblUsuUsuario;
    private javax.swing.JPanel ppersonal;
    private javax.swing.JTable tbSectorizacion;
    private javax.swing.JTable tb_Personal_UO;
    private javax.swing.JLabel titulo5;
    public static javax.swing.JTextField txtBuscarMedico_UO;
    public static javax.swing.JTextField txtBuscarMedico_UO1;
    private javax.swing.JFormattedTextField txtfi;
    private javax.swing.JFormattedTextField txtft;
    // End of variables declaration//GEN-END:variables
}
