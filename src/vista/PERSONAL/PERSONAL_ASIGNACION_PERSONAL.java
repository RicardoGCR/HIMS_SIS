/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.PERSONAL;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
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
import java.util.Set;
import javax.mail.Message;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.PERSONAL.CLS_PERSONAL_ASIGNACION;
import modelos.Usuario;
import servicios.Conexion;
import vista.ConsultorioEx.Mensaje;

/**
 *
 * @author Admin
 */
public class PERSONAL_ASIGNACION_PERSONAL extends javax.swing.JFrame {
    
    DefaultTableModel m;
    Conexion conectar=new Conexion();
    Connection con; 
    Conexion c=new Conexion();
    Connection conexion=c.conectar();
    ResultSet r;
    public void formatoPersonal_UO(){        
        tb_Personal_UO.getColumnModel().getColumn(0).setPreferredWidth(50);
        tb_Personal_UO.getColumnModel().getColumn(1).setPreferredWidth(60); 
        tb_Personal_UO.getColumnModel().getColumn(2).setPreferredWidth(110);
        tb_Personal_UO.getColumnModel().getColumn(3).setPreferredWidth(130);
        tb_Personal_UO.getColumnModel().getColumn(4).setPreferredWidth(130);                
        tb_Personal_UO.getColumnModel().getColumn(5).setPreferredWidth(230); 
        tb_Personal_UO.getColumnModel().getColumn(6).setPreferredWidth(170); 
        tb_Personal_UO.getColumnModel().getColumn(7).setPreferredWidth(80);
        tb_Personal_UO.getColumnModel().getColumn(8).setPreferredWidth(150);
        tb_Personal_UO.getColumnModel().getColumn(9).setPreferredWidth(150);
        tb_Personal_UO.getColumnModel().getColumn(10).setPreferredWidth(60);
        tb_Personal_UO.getColumnModel().getColumn(11).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(11).setMaxWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(12).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(12).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(13).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(13).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(14).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(14).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(15).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(15).setMaxWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(16).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(16).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(17).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(17).setMaxWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(18).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(18).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(19).setMinWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(19).setMaxWidth(0);  
        tb_Personal_UO.getColumnModel().getColumn(20).setMinWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(20).setMaxWidth(0); 
        tb_Personal_UO.getColumnModel().getColumn(21).setMinWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(21).setMaxWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(22).setMinWidth(0);
        tb_Personal_UO.getColumnModel().getColumn(22).setMaxWidth(0);
        tb_Personal_UO.setRowHeight(38);
    }
     public void MostrarPersonal_UO(){
        try {
            String consulta="";
            tb_Personal_UO.setModel(new DefaultTableModel());
            String titulos[]={"Nº","ID UO","Cod. Personal","Apellido Paterno","Apellido Materno","Nombres","Cargo",
            "AR_ID", "Area", "Servicio", "SE_ID","","","","","","","","","","","",""};
            m=new DefaultTableModel(null,titulos);
               
            JTable p=new JTable(m){
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};
            String fila[]=new String[22];
            Usuario obj=new Usuario();
            consulta="exec [PERSONAL_LISTAR_PER_UO_ASIGNACION] ?";
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscarMedico_UO.getText());
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
                fila[9]=r.getString(9);
                fila[10]=r.getString(10);
                fila[11]=r.getString(11);
                fila[12]=r.getString(12);
                fila[13]=r.getString(13);
                fila[14]=r.getString(14);
                fila[15]=r.getString(15);
                fila[16]=r.getString(16); 
                fila[17]=r.getString(17);
                fila[18]=r.getString(18);    
                fila[19]=r.getString(19);
                fila[20]=r.getString(20);
                fila[21]=r.getString(21);
                m.addRow(fila);
                c++;
            }
            tb_Personal_UO.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Personal_UO.setRowSorter(elQueOrdena);
            tb_Personal_UO.setModel(m);
        } catch (Exception e) {
            System.out.println("Error mostrar personal UO: " + e.getMessage());
        }     
    }
public DefaultComboBoxModel Servicio(){
        DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
        String   sql = null;
        ResultSet rs = null;
        Statement  st = null;   
        try {
            st = conexion.createStatement();
            r = st.executeQuery ("EXEC SP_CARGAR_AREA"); 
            while( r.next() ){
                listmodel.addElement( r.getString( "SE_DESC" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
public DefaultComboBoxModel Cargo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
            st = conexion.createStatement();
            r = st.executeQuery ("EXEC SP_CARGAR_AREA_CARGO"); 
            while( r.next() ){
                listmodel.addElement( r.getString( "descripcion_cargo" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
public DefaultComboBoxModel Trabajador(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
            st = conexion.createStatement();
            r = st.executeQuery ("EXEC [SP_CARGAR_TIPO_TRABAJADOR] "); 
            while( r.next() ){
                listmodel.addElement( r.getString( "nombre_tipo_trabajador" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
public DefaultComboBoxModel Regimen(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
            st = conexion.createStatement();
            r = st.executeQuery ("EXEC [SP_CARGAR_REGIMEN LABORAL]"); 
            while( r.next() ){
                listmodel.addElement( r.getString( "nomen_regimen_lab" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
public DefaultComboBoxModel Nivel(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
            st = conexion.createStatement();
            String cargo;
            cargo=jcmbCargo.getSelectedItem().toString();
            r = st.executeQuery ("EXEC [SP_CARGAR_nivel] '"+
                            cargo+"' ");
            while( r.next() ){
                listmodel.addElement( r.getString( "descripcion_nivel" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
public DefaultComboBoxModel Area(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
            st = conexion.createStatement();
            String servicio;
            servicio=jcmbServicio.getSelectedItem().toString();
            r = st.executeQuery ("EXEC [SP_CARGAR_AREA_SERVICIO] '"+
                            servicio+"'");
            while( r.next() ){
                listmodel.addElement( r.getString( "AR_DESC" ) );     
            }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }
    public PERSONAL_ASIGNACION_PERSONAL() {
        initComponents();
        con=conectar.conectar();
        this.getContentPane().setBackground(Color.white);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        MostrarPersonal_UO();
        formatoPersonal_UO();
        this.jcmbServicio.removeAllItems();     
        this.jcmbCargo.removeAllItems();
        this.jcmbTip_Trab.removeAllItems();
        this.jcmbRegLab.removeAllItems();
        this.jcmbNivel.removeAllItems();     
        this.jcmbArea.removeAllItems();
        this.jcmbServicio.setModel(Servicio());
        this.jcmbCargo.setModel(Cargo());         
        this.jcmbTip_Trab.setModel(Trabajador());  
        this.jcmbRegLab.setModel(Regimen());  
        this.jcmbNivel.setModel(Nivel());      
        this.jcmbArea.setModel(Area());
        jtxtApeMat.setVisible(false);
        jtxtApePat.setVisible(false);
        jtxtCelular.setVisible(false);
        jtxtCorrElec.setVisible(false);
        jtxtDNI.setVisible(false);
        jtxtNombres.setVisible(false);
        jtxtfecha.setVisible(false);
        jPanel3.setVisible(true);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true); 
        btneliminar.setEnabled(true);
        btnLista.setEnabled(true);
        jLabel1.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jcmbTip_Trab.setVisible(false);
        jcmbRegLab.setVisible(false);
        jcmbCargo.setVisible(false);
        jcmbServicio.setVisible(false);
        jcmbNivel.setVisible(false);
        jcmbSexo.setVisible(false);
        jcmbArea.setVisible(false);
        cerrar();
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");
        getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e)
            {
                SalirFormulario();
            }
        });
        OPC="0";
        
    }
    public void cerrar (){
        try {
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    SalirFormulario();
                }
            });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void SalirFormulario(){
        if (OPC=="0"){
            dispose();
        } else {
            this.jcmbServicio.removeAllItems();     
            this.jcmbCargo.removeAllItems();
            this.jcmbTip_Trab.removeAllItems();
            this.jcmbRegLab.removeAllItems();
            this.jcmbNivel.removeAllItems();     
            this.jcmbArea.removeAllItems();
            this.jcmbServicio.setModel(Servicio());
            this.jcmbCargo.setModel(Cargo());         
            this.jcmbTip_Trab.setModel(Trabajador());  
            this.jcmbRegLab.setModel(Regimen());  
            this.jcmbNivel.setModel(Nivel());      
            this.jcmbArea.setModel(Area());
            jtxtfecha.setVisible(false);
            jtxtApeMat.setVisible(false);
            jtxtApePat.setVisible(false);
            jtxtCelular.setVisible(false);
            jtxtCorrElec.setVisible(false);
            jtxtDNI.setVisible(false);
            jtxtNombres.setVisible(false);
            jtxtDNI.setEnabled(false);
            jPanel3.setVisible(true);
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true); 
            btneliminar.setEnabled(true);
            btnLista.setEnabled(true);
            jLabel1.setVisible(false);
            jLabel10.setVisible(false);
            jLabel11.setVisible(false);
            jLabel12.setVisible(false);
            jLabel13.setVisible(false);
            jLabel2.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            jLabel14.setVisible(false);
            jLabel15.setVisible(false);
            jcmbTip_Trab.setVisible(false);
            jcmbRegLab.setVisible(false);
            jcmbCargo.setVisible(false);
            jcmbServicio.setVisible(false);
            jcmbNivel.setVisible(false);
            jcmbSexo.setVisible(false);
            jcmbArea.setVisible(false);
            OPC="0";
        }
    }
    public void ELIMINAR_PERSONAL(){
        int filaselec=tb_Personal_UO.getSelectedRow();
        if( filaselec>=0){
            CLS_PERSONAL_ASIGNACION PE=new CLS_PERSONAL_ASIGNACION();
            PE.setDNI_per(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),2)));
            PE.ELIMINAR_PERSONAL();
        }
    }
    public void GuardarPersonalAsignacion(){
        CLS_PERSONAL_ASIGNACION cno1 = new CLS_PERSONAL_ASIGNACION();
        cno1.setDNI_per(jtxtDNI.getText());//
        cno1.setApe_par_per(jtxtApePat.getText());//
        cno1.setApe_mat_per(jtxtApeMat.getText());//
        cno1.setNombres_per(jtxtNombres.getText());// 
        cno1.setFec_nac_per(jtxtfecha.getText());//
        cno1.setSexo(jcmbSexo.getSelectedItem().toString());//
        cno1.setCod_nivel(jcmbNivel.getSelectedItem().toString());// 
        cno1.setCargo(jcmbCargo.getSelectedItem().toString());//
        cno1.setServicio(jcmbServicio.getSelectedItem().toString());//     
        cno1.setCod_tipo_trabajador(jcmbTip_Trab.getSelectedItem().toString());//  
        cno1.setCod_regimen_lab(jcmbRegLab.getSelectedItem().toString());//      
        cno1.setAR_ID(jcmbArea.getSelectedItem().toString());//      
        cno1.setCorreo(jtxtCorrElec.getText());//      
        cno1.setCel(jtxtCelular.getText());//      
        cno1.setCod_uso(lblusu.getText());//     
        if(cno1.INSERTAR_PERSONAL_ASIGNACION(OPC)==true){
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
            System.out.println("GUARDADO");
        } else {
            System.out.println("NO GUARDADO");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtBuscarMedico_UO = new javax.swing.JTextField();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
        lblusu = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnBuscarPaciente2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jtxtApePat = new javax.swing.JTextField();
        jtxtApeMat = new javax.swing.JTextField();
        jtxtNombres = new javax.swing.JTextField();
        jcmbCargo = new javax.swing.JComboBox<String>();
        jcmbSexo = new javax.swing.JComboBox<String>();
        jtxtCorrElec = new javax.swing.JTextField();
        jtxtDNI = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jcmbNivel = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        jcmbServicio = new javax.swing.JComboBox<String>();
        jLabel13 = new javax.swing.JLabel();
        jcmbArea = new javax.swing.JComboBox<String>();
        jLabel14 = new javax.swing.JLabel();
        jcmbTip_Trab = new javax.swing.JComboBox<String>();
        jLabel15 = new javax.swing.JLabel();
        jcmbRegLab = new javax.swing.JComboBox<String>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Personal_UO = new javax.swing.JTable();
        jtxtfecha = new javax.swing.JFormattedTextField();
        jtxtCelular = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 0, 153));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Personal Asignación UO");

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

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarMedico_UO, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBuscarMedico_UO, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btneditar.setForeground(new java.awt.Color(240, 240, 240));
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
        btneditar.setText("Editar");
        btneditar.setContentAreaFilled(false);
        btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btneditar.setIconTextGap(30);
        btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(240, 240, 240));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.setContentAreaFilled(false);
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btneliminar.setIconTextGap(30);
        btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnLista.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLista.setForeground(new java.awt.Color(240, 240, 240));
        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Orden de compra-32.png"))); // NOI18N
        btnLista.setText("Listado");
        btnLista.setContentAreaFilled(false);
        btnLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLista.setIconTextGap(30);
        btnLista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });

        lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        lblusu.setForeground(new java.awt.Color(255, 255, 255));
        lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
        lblusu.setText("Usuario");
        lblusu.setFocusable(false);
        lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(240, 240, 240));
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.setContentAreaFilled(false);
        btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnguardar.setIconTextGap(30);
        btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnBuscarPaciente2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
        btnBuscarPaciente2.setContentAreaFilled(false);
        btnBuscarPaciente2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarPaciente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPaciente2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(109, 109, 109))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarPaciente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardar)
                .addGap(4, 4, 4)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(btneliminar)
                .addGap(18, 18, 18)
                .addComponent(btnLista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Apellido Paterno");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Cargo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("DNI");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Sexo");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nombres");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Apellido Materno");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Fecha Nacimiento");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Correo Electronico");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Celular");

        jtxtApePat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jtxtApeMat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jtxtNombres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jcmbCargo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbCargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbCargoItemStateChanged(evt);
            }
        });

        jcmbSexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));

        jtxtCorrElec.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jtxtDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtDNI.setName(""); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Nivel");

        jcmbNivel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Servicio");

        jcmbServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbServicioItemStateChanged(evt);
            }
        });
        jcmbServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jcmbServicioMouseReleased(evt);
            }
        });
        jcmbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbServicioActionPerformed(evt);
            }
        });
        jcmbServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jcmbServicioKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Area");

        jcmbArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tipo Trabajador");

        jcmbTip_Trab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbTip_Trab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Regimen Laboral");

        jcmbRegLab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcmbRegLab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        tb_Personal_UO.setRowSelectionAllowed(false);
        tb_Personal_UO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_Personal_UOMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Personal_UO);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        jtxtfecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        jtxtfecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jtxtCelular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(jtxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtCorrElec, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jcmbTip_Trab, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcmbRegLab, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtxtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel12))
                                    .addGap(22, 22, 22)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jtxtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel7))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(28, 28, 28)
                                                    .addComponent(jLabel5))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jcmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(25, 25, 25)
                                                    .addComponent(jLabel13)))
                                            .addGap(21, 21, 21)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jcmbSexo, 0, 243, Short.MAX_VALUE)
                                                .addComponent(jtxtApeMat)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jcmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(25, 25, 25)
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                            .addComponent(jcmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(jcmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtxtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtApeMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jcmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jcmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcmbRegLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jcmbTip_Trab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jtxtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtCorrElec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jtxtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        MostrarPersonal_UO();
        formatoPersonal_UO();
        this.jcmbServicio.removeAllItems();     
        this.jcmbCargo.removeAllItems();
        this.jcmbTip_Trab.removeAllItems();
        this.jcmbRegLab.removeAllItems();
        this.jcmbNivel.removeAllItems();     
        this.jcmbArea.removeAllItems();
        this.jcmbServicio.setModel(Servicio());
        this.jcmbCargo.setModel(Cargo());         
        this.jcmbTip_Trab.setModel(Trabajador());  
        this.jcmbRegLab.setModel(Regimen());  
        this.jcmbNivel.setModel(Nivel());      
        this.jcmbArea.setModel(Area());
        jtxtApeMat.setVisible(false);
        jtxtApePat.setVisible(false);
        jtxtCelular.setVisible(false);
        jtxtCorrElec.setVisible(false);
        jtxtDNI.setVisible(false);
        jtxtNombres.setVisible(false);
        jPanel3.setVisible(true);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true); 
        btneliminar.setEnabled(true);
        btnLista.setEnabled(true);
        jLabel1.setVisible(false);
        jLabel10.setVisible(false);
        jLabel11.setVisible(false);
        jLabel12.setVisible(false);
        jLabel13.setVisible(false);
        jLabel2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        jLabel14.setVisible(false);
        jLabel15.setVisible(false);
        jcmbTip_Trab.setVisible(false);
        jcmbRegLab.setVisible(false);
        jcmbCargo.setVisible(false);
        jcmbServicio.setVisible(false);
        jcmbNivel.setVisible(false);
        jcmbSexo.setVisible(false);
        jcmbArea.setVisible(false);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR "+String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),2))+"  ?",
        "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
        try{       
            int filaselec=tb_Personal_UO.getSelectedRow();
            if( filaselec>=0){
                if(eliminar == 0 )
                {
                    ELIMINAR_PERSONAL();
                    JOptionPane.showMessageDialog(rootPane, "Datos Eliminados de \n Forma Correcta");
                    System.out.println("datos eliminados"); 
                    DefaultTableModel modelo = (DefaultTableModel)tb_Personal_UO.getModel();
                    modelo.removeRow(filaselec);
                    MostrarPersonal_UO();
                    formatoPersonal_UO();
                }else{
                    System.out.println("Error al eliminar rol y actividad");
                }                        
            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }
        }catch(Exception e){
            System.out.println("error eliminar rol_actividad: " + e.getMessage());
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        try{       
            int filaselec=tb_Personal_UO.getSelectedRow();
            if( filaselec>=0){
                OPC="2";
                jtxtDNI.setEnabled(false);
                jtxtApeMat.setVisible(true);
                jtxtApePat.setVisible(true);
                jtxtfecha.setVisible(true);
                jtxtCelular.setVisible(true );
                jtxtCorrElec.setVisible(true);
                jtxtDNI.setVisible(true);
                jtxtNombres.setVisible(true);
                jcmbCargo.setVisible(true);
                jcmbServicio.setVisible(true);
                jcmbNivel.setVisible(true);
                jcmbSexo.setVisible(true);       
                jcmbTip_Trab.setVisible(true);
                jcmbRegLab.setVisible(true);
                jcmbArea.setVisible(true);
                jPanel3.setVisible(false);
                btnNuevo.setEnabled(false);
                btnguardar.setEnabled(true);
                btneditar.setEnabled(false);
                btneliminar.setEnabled(false);
                btnLista.setEnabled(false);
                jLabel1.setVisible(true);
                jLabel10.setVisible(true);
                jLabel11.setVisible(true);
                jLabel12.setVisible(true);  
                jLabel14.setVisible(true);
                jLabel15.setVisible(true);
                jLabel13.setVisible(true);
                jLabel2.setVisible(true);
                jLabel4.setVisible(true);
                jLabel5.setVisible(true);
                jLabel6.setVisible(true);
                jLabel7.setVisible(true);
                jLabel8.setVisible(true);
                jLabel9.setVisible(true);
                jtxtApeMat.setText("");
                jtxtApePat.setText("");
                jtxtCelular.setText("");
                jtxtCorrElec.setText("");
                jtxtDNI.setText("");
                jtxtDNI.setText("");
                jtxtNombres.setText("");
                jtxtfecha.setText("");
                jtxtDNI.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),11)));
                jtxtApePat.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),3)));
                jtxtApeMat.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),4)));
                jtxtNombres.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),5)));
                jtxtCorrElec.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),18)));
                jtxtfecha.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),15)));
                jtxtCelular.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),19)));
                this.jcmbServicio.removeAllItems();     
                this.jcmbCargo.removeAllItems();
                this.jcmbTip_Trab.removeAllItems();
                this.jcmbRegLab.removeAllItems();
                this.jcmbNivel.removeAllItems();     
                this.jcmbArea.removeAllItems();    
                this.jcmbCargo.setModel(Cargo());         
                jcmbCargo.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),6)));
                this.jcmbNivel.setModel(Nivel());      
                jcmbNivel.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),20)));
                this.jcmbServicio.setModel(Servicio());
                jcmbServicio.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),9)));
                this.jcmbArea.setModel(Area());      
                jcmbArea.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),8)));
                this.jcmbTip_Trab.setModel(Trabajador());  
                jcmbTip_Trab.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),21)));
                this.jcmbRegLab.setModel(Regimen());   
                jcmbRegLab.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),22)));
            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }
        }catch(Exception e){
            System.out.println("Error al editar " + e.getMessage());
      }
    }//GEN-LAST:event_btneditarActionPerformed

    private void txtBuscarMedico_UOKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyTyped
        char tecla= evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            tb_Personal_UO.getSelectionModel().setSelectionInterval(0, 0);
            tb_Personal_UO.requestFocus();
        }
    }//GEN-LAST:event_txtBuscarMedico_UOKeyTyped

    private void txtBuscarMedico_UOKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyReleased
        txtBuscarMedico_UO.setText(txtBuscarMedico_UO.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarMedico_UOKeyReleased

    private void txtBuscarMedico_UOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOKeyPressed
        if(evt.getExtendedKeyCode()==KeyEvent.VK_DOWN){
            
        }
    }//GEN-LAST:event_txtBuscarMedico_UOKeyPressed

    private void txtBuscarMedico_UOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOActionPerformed

    }//GEN-LAST:event_txtBuscarMedico_UOActionPerformed

    private void txtBuscarMedico_UOCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarMedico_UOCaretUpdate
        MostrarPersonal_UO();
        formatoPersonal_UO();
    }//GEN-LAST:event_txtBuscarMedico_UOCaretUpdate
    String OPC;
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        OPC="1";
        jtxtApeMat.setVisible(true    );   
        jtxtApePat.setVisible(true);
        jtxtCelular.setVisible(true );
        jtxtCorrElec.setVisible(true);
        jtxtDNI.setVisible(true);    
        jtxtfecha.setVisible(true);
        jtxtNombres.setVisible(true);
        jcmbCargo.setVisible(true);
        jcmbServicio.setVisible(true);
        jcmbNivel.setVisible(true);
        jcmbSexo.setVisible(true);       
        jcmbTip_Trab.setVisible(true);
        jcmbRegLab.setVisible(true);
        jcmbArea.setVisible(true);
        jPanel3.setVisible(false);
        btnNuevo.setEnabled(false);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnLista.setEnabled(false);
        jLabel1.setVisible(true);
        jLabel10.setVisible(true);
        jLabel11.setVisible(true);
        jLabel12.setVisible(true);  
        jLabel14.setVisible(true);
        jLabel15.setVisible(true);
        jLabel13.setVisible(true);
        jLabel2.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jLabel7.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        jtxtApeMat.setText("");
        jtxtApePat.setText("");
        jtxtCelular.setText("");
        jtxtCorrElec.setText("");
        jtxtDNI.setText("");
        jtxtDNI.setEnabled(true);
        jtxtNombres.setText("");
        jtxtfecha.setText("");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if (jtxtDNI.getText().equals("") || jtxtApeMat.getText().equals("")
                || jtxtApePat.getText().equals("")
                || jtxtNombres.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Faltan Ingresar Datos Principales");
            }
        else{
            GuardarPersonalAsignacion();
            OPC="0";
            MostrarPersonal_UO();
            formatoPersonal_UO();
            this.jcmbServicio.removeAllItems();     
            this.jcmbCargo.removeAllItems();
            this.jcmbTip_Trab.removeAllItems();
            this.jcmbRegLab.removeAllItems();
            this.jcmbNivel.removeAllItems();     
            this.jcmbArea.removeAllItems();
            this.jcmbServicio.setModel(Servicio());
            this.jcmbCargo.setModel(Cargo());         
            this.jcmbTip_Trab.setModel(Trabajador());  
            this.jcmbRegLab.setModel(Regimen());  
            this.jcmbNivel.setModel(Nivel());      
            this.jcmbArea.setModel(Area());
            jtxtfecha.setVisible(false);
            jtxtApeMat.setVisible(false);
            jtxtApePat.setVisible(false);
            jtxtCelular.setVisible(false);
            jtxtCorrElec.setVisible(false);
            jtxtDNI.setVisible(false);
            jtxtNombres.setVisible(false);
            jtxtDNI.setEnabled(false);
            jPanel3.setVisible(true);
            btnNuevo.setEnabled(true);
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true); 
            btneliminar.setEnabled(true);
            btnLista.setEnabled(true);
            jLabel1.setVisible(false);
            jLabel10.setVisible(false);
            jLabel11.setVisible(false);
            jLabel12.setVisible(false);
            jLabel13.setVisible(false);
            jLabel2.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            jLabel9.setVisible(false);
            jLabel14.setVisible(false);
            jLabel15.setVisible(false);
            jcmbTip_Trab.setVisible(false);
            jcmbRegLab.setVisible(false);
            jcmbCargo.setVisible(false);
            jcmbServicio.setVisible(false);
            jcmbNivel.setVisible(false);
            jcmbSexo.setVisible(false);
            jcmbArea.setVisible(false);
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void jcmbServicioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcmbServicioMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbServicioMouseReleased

    private void jcmbServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcmbServicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbServicioKeyTyped

    private void jcmbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbServicioActionPerformed

    private void jcmbCargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbCargoItemStateChanged
        try{  
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.jcmbCargo.getSelectedIndex()>0){
                    this.jcmbNivel.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String  cargo=jcmbCargo.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC [SP_CARGAR_nivel] '"+
                    cargo+"' "); 
                    while(rs.next()){
                        this.jcmbNivel.addItem(rs.getString("descripcion_nivel"));
                    }
                }else{
                    this.jcmbNivel.removeAllItems();
                }
            }
        }
        catch(Exception ex) 
            {
                System.out.println("Error: " + ex.getMessage());
            }
    }//GEN-LAST:event_jcmbCargoItemStateChanged

    private void jcmbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbServicioItemStateChanged
        try{  
            if(evt.getStateChange()==ItemEvent.SELECTED){
                if(this.jcmbServicio.getSelectedIndex()>0){
                    this.jcmbArea.removeAllItems(); 
                    Statement sta=conexion.createStatement();
                    String  cargo=jcmbServicio.getSelectedItem().toString();
                    ResultSet rs=sta.executeQuery("EXEC [SP_CARGAR_AREA_SERVICIO] '"+
                    cargo+"' "); 
                    while(rs.next()){
                        this.jcmbArea.addItem(rs.getString("AR_DESC"));
                    }
                }else{
                    this.jcmbArea.removeAllItems();
                }
            }
        }
        catch(Exception ex) 
        {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jcmbServicioItemStateChanged

    private void btnBuscarPaciente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente2ActionPerformed
        MostrarPersonal_UO();
        formatoPersonal_UO();
    }//GEN-LAST:event_btnBuscarPaciente2ActionPerformed

    private void tb_Personal_UOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Personal_UOMouseClicked
         if(evt.getClickCount()==1){
             try{       
            int filaselec=tb_Personal_UO.getSelectedRow();
            if( filaselec>=0){
                OPC="2";
                jtxtDNI.setEnabled(false);
                jtxtApeMat.setVisible(true);
                jtxtApePat.setVisible(true);
                jtxtfecha.setVisible(true);
                jtxtCelular.setVisible(true );
                jtxtCorrElec.setVisible(true);
                jtxtDNI.setVisible(true);
                jtxtNombres.setVisible(true);
                jcmbCargo.setVisible(true);
                jcmbServicio.setVisible(true);
                jcmbNivel.setVisible(true);
                jcmbSexo.setVisible(true);       
                jcmbTip_Trab.setVisible(true);
                jcmbRegLab.setVisible(true);
                jcmbArea.setVisible(true);
                jPanel3.setVisible(false);
                btnNuevo.setEnabled(false);
                btnguardar.setEnabled(true);
                btneditar.setEnabled(false);
                btneliminar.setEnabled(false);
                btnLista.setEnabled(false);
                jLabel1.setVisible(true);
                jLabel10.setVisible(true);
                jLabel11.setVisible(true);
                jLabel12.setVisible(true);  
                jLabel14.setVisible(true);
                jLabel15.setVisible(true);
                jLabel13.setVisible(true);
                jLabel2.setVisible(true);
                jLabel4.setVisible(true);
                jLabel5.setVisible(true);
                jLabel6.setVisible(true);
                jLabel7.setVisible(true);
                jLabel8.setVisible(true);
                jLabel9.setVisible(true);
                jtxtApeMat.setText("");
                jtxtApePat.setText("");
                jtxtCelular.setText("");
                jtxtCorrElec.setText("");
                jtxtDNI.setText("");
                jtxtDNI.setText("");
                jtxtNombres.setText("");
                jtxtfecha.setText("");
                jtxtDNI.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),11)));
                jtxtApePat.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),3)));
                jtxtApeMat.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),4)));
                jtxtNombres.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),5)));
                jtxtCorrElec.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),18)));
                jtxtfecha.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),15)));
                jtxtCelular.setText(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),19)));
                this.jcmbServicio.removeAllItems();     
                this.jcmbCargo.removeAllItems();
                this.jcmbTip_Trab.removeAllItems();
                this.jcmbRegLab.removeAllItems();
                this.jcmbNivel.removeAllItems();     
                this.jcmbArea.removeAllItems();    
                this.jcmbCargo.setModel(Cargo());         
                jcmbCargo.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),6)));
                this.jcmbNivel.setModel(Nivel());      
                jcmbNivel.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),20)));
                this.jcmbServicio.setModel(Servicio());
                jcmbServicio.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),9)));
                this.jcmbArea.setModel(Area());      
                jcmbArea.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),8)));
                this.jcmbTip_Trab.setModel(Trabajador());  
                jcmbTip_Trab.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),21)));
                this.jcmbRegLab.setModel(Regimen());   
                jcmbRegLab.setSelectedItem(String.valueOf(tb_Personal_UO.getValueAt(tb_Personal_UO.getSelectedRow(),22)));
            }else{
                JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
            }
        }catch(Exception e){
            System.out.println("Error al editar " + e.getMessage());
      }
         }
    }//GEN-LAST:event_tb_Personal_UOMouseClicked

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
            java.util.logging.Logger.getLogger(PERSONAL_ASIGNACION_PERSONAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ASIGNACION_PERSONAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ASIGNACION_PERSONAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PERSONAL_ASIGNACION_PERSONAL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PERSONAL_ASIGNACION_PERSONAL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarPaciente2;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcmbArea;
    private javax.swing.JComboBox<String> jcmbCargo;
    private javax.swing.JComboBox<String> jcmbNivel;
    private javax.swing.JComboBox<String> jcmbRegLab;
    private javax.swing.JComboBox<String> jcmbServicio;
    private javax.swing.JComboBox<String> jcmbSexo;
    private javax.swing.JComboBox<String> jcmbTip_Trab;
    private javax.swing.JTextField jtxtApeMat;
    private javax.swing.JTextField jtxtApePat;
    private javax.swing.JTextField jtxtCelular;
    private javax.swing.JTextField jtxtCorrElec;
    private javax.swing.JTextField jtxtDNI;
    private javax.swing.JTextField jtxtNombres;
    private javax.swing.JFormattedTextField jtxtfecha;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JTable tb_Personal_UO;
    public static javax.swing.JTextField txtBuscarMedico_UO;
    // End of variables declaration//GEN-END:variables
}
