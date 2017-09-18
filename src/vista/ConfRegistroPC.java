/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import modelos.Usuario;
import modelos.tipoUsuario;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_Nomenclatura;
import modelos.Caja.Caja_PC_Registro;
import modelos.LABORATORIO.LAB_PC_AREA;
import modelos.RegistroPC;

import servicios.Conexion;
import static vista.Principal.fechaActual;
import static vista.RegistroUsuario.txtCodigo;

/**
 *
 * @author Profe
 */
public class ConfRegistroPC extends javax.swing.JFrame implements Runnable{
String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;
DefaultTableModel m;
Conexion cn=new Conexion();
byte est;
    /**
     * Creates new form Usuario
     */
    public ConfRegistroPC() {
        initComponents();
        cn.conectar();
         RegistroPC pc = new RegistroPC();
        pc.CajaPC_Listar();
//        pc.PERFIL_USUARIO(PrincipalMDI.lblUsu.getText());
        this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
      this.setExtendedState(MAXIMIZED_BOTH);
      tab.setSelectedIndex(2);
      Area.setLocationRelativeTo(null);
      
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());
    
        cargarPC();
        formatoPC();
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
    
    public void cargarPC(){
        String consulta="";
    try {
             String titulos[]={"Nº","Area","Módulo","Servicio","Area","Terminal","NRO PC","SE_ID","AR_ID"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];

            LAB_PC_AREA obj=new LAB_PC_AREA();
                    consulta="exec CONFIGURACION_PC_AREA_Listar ?,?";
                    
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            cmd.setString(2, "1");
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
                m.addRow(fila);
                c++;
            }
            TbPC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            TbPC.setRowSorter(elQueOrdena);
            this.TbPC.setModel(m);
    } catch (Exception e) {
    }
}
    public void formatoPC(){
    TbPC.getColumnModel().getColumn(0).setPreferredWidth(10);
    TbPC.getColumnModel().getColumn(2).setPreferredWidth(50);
    TbPC.getColumnModel().getColumn(3).setPreferredWidth(150);
    TbPC.getColumnModel().getColumn(4).setPreferredWidth(150);
    TbPC.getColumnModel().getColumn(4).setPreferredWidth(140);
    TbPC.getColumnModel().getColumn(4).setPreferredWidth(100);
    
    TbPC.getColumnModel().getColumn(1).setMinWidth(0);
    TbPC.getColumnModel().getColumn(1).setMaxWidth(0);
    
    TbPC.getColumnModel().getColumn(7).setMinWidth(0);
    TbPC.getColumnModel().getColumn(7).setMaxWidth(0);
    
    TbPC.getColumnModel().getColumn(8).setMinWidth(0);
    TbPC.getColumnModel().getColumn(8).setMaxWidth(0);
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
     
     
       public void BuscarUnidad(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupos1.setModel(new DefaultTableModel());
             String titulos[]={"Área","","Servicio",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec [buscarAreaServicio] ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);

                m.addRow(fila);
                c++;
            }
            tb_Grupos1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos1.setRowSorter(elQueOrdena);
            this.tb_Grupos1.setModel(m);

            formatoventanas1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void formatoventanas1(){
    tb_Grupos1.getColumnModel().getColumn(0).setPreferredWidth(200);
    tb_Grupos1.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupos1.getColumnModel().getColumn(1).setMaxWidth(0);
    tb_Grupos1.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Grupos1.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupos1.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupos1.setRowHeight(45);
}
    
    public void Guardar(){
        
                RegistroPC REG = new RegistroPC();
                REG.setModulo(cbxModulo.getSelectedItem().toString());
                if(lblSEID.getText().equalsIgnoreCase("")==false){
                REG.setSE_ID(Integer.parseInt(lblSEID.getText()));
                }
                if(lblARID.getText().equalsIgnoreCase("")==false){
                REG.setAR_ID(Integer.parseInt(lblARID.getText()) );//
                }
                REG.setNOM_PC(txtPC.getText());
                REG.setNOM_USU(lblUsuario.getText());//
                if(txtNRO.getText().equalsIgnoreCase("")==false){
                REG.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
                }
                    if(REG.NuevoTerminal()==true){
                        System.out.println("GUARDADO");
                        lblMensaje.setText("Todo Correcto");
                         JOptionPane.showMessageDialog(rootPane, "Datos Guardados");
                         btnguardar.setEnabled(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        
    }
                        
    }
    
    public void Modificar(){
        
                RegistroPC REG = new RegistroPC();
                REG.setModulo(cbxModulo.getSelectedItem().toString());
                if(lblSEID.getText().equalsIgnoreCase("")==false){
                REG.setSE_ID(Integer.parseInt(lblSEID.getText()));
                }
                if(lblARID.getText().equalsIgnoreCase("")==false){
                REG.setAR_ID(Integer.parseInt(lblARID.getText()) );//
                }
                REG.setNOM_PC(txtPC.getText());
                REG.setNOM_USU(lblUsuario.getText());//
                if(txtNRO.getText().equalsIgnoreCase("")==false){
                REG.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
                }
                REG.setPA_ID(Integer.parseInt(lblID.getText()));
                    if(REG.ModificarTerminal()==true){
                        System.out.println("MODIFICADO");
                        lblMensaje.setText("Todo Correcto");
                         JOptionPane.showMessageDialog(rootPane, "Datos Modificados");
                         btnguardar.setEnabled(false);
                    }else{
                        lblMensaje.setText("Algo salió mal");
                        
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

        Area = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        txtBuscar1 = new javax.swing.JTextField();
        btnBuscarPaciente4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_Grupos1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel3 = new javax.swing.JPanel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnguardar = new javax.swing.JButton();
            btneliminar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            jLabel61 = new javax.swing.JLabel();
            jPanel23 = new javax.swing.JPanel();
            buscartodo = new javax.swing.JTextField();
            btnBuscarPaciente = new javax.swing.JButton();
            lbldetalle = new javax.swing.JLabel();
            btnLista = new javax.swing.JButton();
            tab = new javax.swing.JTabbedPane();
            jPanel8 = new javax.swing.JPanel();
            jLabel58 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            panelCPT12 = new javax.swing.JPanel();
            txtArea = new javax.swing.JTextField();
            panelCPT13 = new javax.swing.JPanel();
            txtNRO = new javax.swing.JTextField();
            panelCPT14 = new javax.swing.JPanel();
            txtPC = new javax.swing.JTextField();
            jPanel67 = new javax.swing.JPanel();
            btnAlertConsulta2 = new javax.swing.JButton();
            jPanel68 = new javax.swing.JPanel();
            btnAlertConsulta3 = new javax.swing.JButton();
            lblARID = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            panelCPT10 = new javax.swing.JPanel();
            txtServicio = new javax.swing.JTextField();
            btnBuscarServicio = new javax.swing.JButton();
            jLabel23 = new javax.swing.JLabel();
            cbxServicio = new javax.swing.JCheckBox();
            lblSEID = new javax.swing.JLabel();
            cbxArea = new javax.swing.JCheckBox();
            cbxModulo = new javax.swing.JComboBox();
            lblID = new javax.swing.JLabel();
            jPanel5 = new javax.swing.JPanel();
            jPanel72 = new javax.swing.JPanel();
            btnAlertConsulta7 = new javax.swing.JButton();
            lblResumenMODULO = new javax.swing.JLabel();
            lblResumenPC = new javax.swing.JLabel();
            lblDes = new javax.swing.JLabel();
            lblMensaje = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            TbPC = new javax.swing.JTable();
            jPanel6 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();
            lblUsuario = new javax.swing.JLabel();

            Area.setAlwaysOnTop(true);
            Area.setMinimumSize(new java.awt.Dimension(591, 419));
            Area.setResizable(false);

            jPanel7.setBackground(new java.awt.Color(41, 127, 184));

            jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setText("Área");

            jPanel29.setBackground(new java.awt.Color(255, 255, 255));

            txtBuscar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtBuscar1.setBorder(null);
            txtBuscar1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtBuscar1CaretUpdate(evt);
                }
            });
            txtBuscar1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtBuscar1ActionPerformed(evt);
                }
            });
            txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    txtBuscar1KeyPressed(evt);
                }
            });

            javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
            jPanel29.setLayout(jPanel29Layout);
            jPanel29Layout.setHorizontalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 5, Short.MAX_VALUE))
            );
            jPanel29Layout.setVerticalGroup(
                jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
            );

            btnBuscarPaciente4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
            btnBuscarPaciente4.setContentAreaFilled(false);
            btnBuscarPaciente4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarPaciente4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarPaciente4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscarPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(305, Short.MAX_VALUE))
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPaciente4))
                    .addGap(412, 412, 412))
            );

            jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            tb_Grupos1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {},
                    {},
                    {},
                    {}
                },
                new String [] {

                }
            ));
            tb_Grupos1.setGridColor(new java.awt.Color(255, 255, 255));
            tb_Grupos1.setRowHeight(25);
            tb_Grupos1.setSelectionBackground(new java.awt.Color(102, 102, 102));
            tb_Grupos1.getTableHeader().setReorderingAllowed(false);
            tb_Grupos1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tb_Grupos1MouseClicked(evt);
                }
            });
            tb_Grupos1.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    tb_Grupos1KeyPressed(evt);
                }
            });
            jScrollPane4.setViewportView(tb_Grupos1);

            javax.swing.GroupLayout AreaLayout = new javax.swing.GroupLayout(Area.getContentPane());
            Area.getContentPane().setLayout(AreaLayout);
            AreaLayout.setHorizontalGroup(
                AreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
            );
            AreaLayout.setVerticalGroup(
                AreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AreaLayout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowOpened(java.awt.event.WindowEvent evt) {
                    formWindowOpened(evt);
                }
            });

            jPanel3.setBackground(new java.awt.Color(223, 0, 78));
            jPanel3.setPreferredSize(new java.awt.Dimension(292, 437));

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

            btneditar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btneditar.setForeground(new java.awt.Color(240, 240, 240));
            btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
            btneditar.setText("Editar");
            btneditar.setContentAreaFilled(false);
            btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btneditar.setEnabled(false);
            btneditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btneditar.setIconTextGap(30);
            btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btneditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btneditarActionPerformed(evt);
                }
            });

            btnguardar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnguardar.setForeground(new java.awt.Color(240, 240, 240));
            btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
            btnguardar.setText("Guardar");
            btnguardar.setContentAreaFilled(false);
            btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnguardar.setEnabled(false);
            btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnguardar.setIconTextGap(30);
            btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnguardar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnguardarActionPerformed(evt);
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

            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            lblusu.setForeground(new java.awt.Color(255, 255, 255));
            lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
            lblusu.setText("Silvana");
            lblusu.setFocusable(false);
            lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

            jLabel61.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
            jLabel61.setForeground(new java.awt.Color(255, 255, 255));
            jLabel61.setText("<html>Configuración PC<span style=\"font-size:'14px'\"><br>Terminal</br></span></html>");

            jPanel23.setBackground(new java.awt.Color(255, 255, 255));

            buscartodo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            buscartodo.setForeground(new java.awt.Color(51, 51, 51));
            buscartodo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            buscartodo.setBorder(null);
            buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    buscartodoCaretUpdate(evt);
                }
            });

            javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
            jPanel23.setLayout(jPanel23Layout);
            jPanel23Layout.setHorizontalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel23Layout.setVerticalGroup(
                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            btnBuscarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-27.png"))); // NOI18N
            btnBuscarPaciente.setContentAreaFilled(false);
            btnBuscarPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarPacienteActionPerformed(evt);
                }
            });

            lbldetalle.setForeground(new java.awt.Color(255, 255, 255));
            lbldetalle.setText("Módulo, PC");

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

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbldetalle)))
                            .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addComponent(lbldetalle)
                    .addGap(21, 21, 21)
                    .addComponent(btnNuevo)
                    .addGap(18, 18, 18)
                    .addComponent(btnguardar)
                    .addGap(18, 18, 18)
                    .addComponent(btneditar)
                    .addGap(18, 18, 18)
                    .addComponent(btneliminar)
                    .addGap(18, 18, 18)
                    .addComponent(btnLista)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            tab.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
            tab.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

            jPanel8.setBackground(new java.awt.Color(255, 255, 255));

            jLabel58.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel58.setForeground(new java.awt.Color(51, 51, 51));
            jLabel58.setText("Configuración del Terminal");

            jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel21.setForeground(new java.awt.Color(51, 51, 51));
            jLabel21.setText("Nombre de Terminal");

            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(51, 51, 51));
            jLabel5.setText("Numero de Terminal");

            jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel6.setForeground(new java.awt.Color(51, 51, 51));
            jLabel6.setText("Area");

            panelCPT12.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtArea.setEditable(false);
            txtArea.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtArea.setForeground(new java.awt.Color(51, 51, 51));
            txtArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtArea.setBorder(null);
            txtArea.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtAreaCaretUpdate(evt);
                }
            });

            javax.swing.GroupLayout panelCPT12Layout = new javax.swing.GroupLayout(panelCPT12);
            panelCPT12.setLayout(panelCPT12Layout);
            panelCPT12Layout.setHorizontalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
            );
            panelCPT12Layout.setVerticalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT12Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtNRO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtNRO.setForeground(new java.awt.Color(51, 51, 51));
            txtNRO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtNRO.setBorder(null);
            txtNRO.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtNROCaretUpdate(evt);
                }
            });
            txtNRO.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtNROKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT13Layout = new javax.swing.GroupLayout(panelCPT13);
            panelCPT13.setLayout(panelCPT13Layout);
            panelCPT13Layout.setHorizontalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addComponent(txtNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addComponent(txtNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            panelCPT14.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtPC.setForeground(new java.awt.Color(51, 51, 51));
            txtPC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtPC.setBorder(null);
            txtPC.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtPCCaretUpdate(evt);
                }
            });

            javax.swing.GroupLayout panelCPT14Layout = new javax.swing.GroupLayout(panelCPT14);
            panelCPT14.setLayout(panelCPT14Layout);
            panelCPT14Layout.setHorizontalGroup(
                panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtPC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
            );
            panelCPT14Layout.setVerticalGroup(
                panelCPT14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT14Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtPC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel67.setBackground(new java.awt.Color(43, 43, 43));

            btnAlertConsulta2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta2.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta2.setText("Siguiente");
            btnAlertConsulta2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta2.setContentAreaFilled(false);
            btnAlertConsulta2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta2.setIconTextGap(30);
            btnAlertConsulta2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
            jPanel67.setLayout(jPanel67Layout);
            jPanel67Layout.setHorizontalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel67Layout.setVerticalGroup(
                jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel67Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel68.setBackground(new java.awt.Color(223, 0, 78));

            btnAlertConsulta3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta3.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta3.setText("Cancelar");
            btnAlertConsulta3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta3.setContentAreaFilled(false);
            btnAlertConsulta3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta3.setIconTextGap(30);
            btnAlertConsulta3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
            jPanel68.setLayout(jPanel68Layout);
            jPanel68Layout.setHorizontalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel68Layout.setVerticalGroup(
                jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblARID.setForeground(new java.awt.Color(255, 255, 255));

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Este Nº de terminal ya se encuentra consignado.");

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel17.setText("Servicio");

            panelCPT10.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtServicio.setEditable(false);
            txtServicio.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtServicio.setForeground(new java.awt.Color(51, 51, 51));
            txtServicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            txtServicio.setBorder(null);
            txtServicio.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtServicioCaretUpdate(evt);
                }
            });

            btnBuscarServicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarServicio.setContentAreaFilled(false);
            btnBuscarServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarServicio.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarServicioActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT10Layout = new javax.swing.GroupLayout(panelCPT10);
            panelCPT10.setLayout(panelCPT10Layout);
            panelCPT10Layout.setHorizontalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT10Layout.createSequentialGroup()
                    .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            panelCPT10Layout.setVerticalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscarServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(panelCPT10Layout.createSequentialGroup()
                            .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );

            jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel23.setForeground(new java.awt.Color(51, 51, 51));
            jLabel23.setText("Módulo");

            cbxServicio.setText("SI");

            lblSEID.setForeground(new java.awt.Color(255, 255, 255));

            cbxArea.setText("SI");

            cbxModulo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADM", "CC", "CPP", "EME", "CEX", "LAB", "RX", "EC", "HOS" }));

            lblID.setForeground(new java.awt.Color(255, 255, 255));

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel68, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel21)
                                                .addComponent(jLabel23)
                                                .addComponent(jLabel17))
                                            .addGap(23, 23, 23)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(cbxServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cbxArea, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(panelCPT12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(panelCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(panelCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cbxModulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lblSEID))
                                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                                            .addGap(28, 28, 28)
                                                            .addComponent(lblID))))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addGap(92, 92, 92)
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(16, 16, 16)
                                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblARID)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addComponent(jLabel6))
                                    .addGap(0, 311, Short.MAX_VALUE)))))
                    .addGap(13, 13, 13))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(panelCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblID))
                    .addGap(27, 27, 27)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxModulo))
                    .addGap(41, 41, 41)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cbxServicio, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblSEID, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(34, 34, 34)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbxArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblARID, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(135, 135, 135)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            tab.addTab("tab1", jPanel8);

            jPanel5.setBackground(new java.awt.Color(255, 255, 255));

            jPanel72.setBackground(new java.awt.Color(223, 0, 78));

            btnAlertConsulta7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta7.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta7.setText("Anterior");
            btnAlertConsulta7.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta7.setContentAreaFilled(false);
            btnAlertConsulta7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta7.setIconTextGap(30);
            btnAlertConsulta7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta7ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
            jPanel72.setLayout(jPanel72Layout);
            jPanel72Layout.setHorizontalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel72Layout.setVerticalGroup(
                jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblResumenMODULO.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenMODULO.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenMODULO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Survey-70.png"))); // NOI18N
            lblResumenMODULO.setText("Nombre de Terminal");

            lblResumenPC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenPC.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Workstation-80.png"))); // NOI18N
            lblResumenPC.setText("Nombre de Terminal");

            lblDes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblDes.setForeground(new java.awt.Color(51, 51, 51));
            lblDes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblDes.setText("Detalle del Terminal");
            lblDes.setToolTipText("");

            lblMensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(51, 51, 51));
            lblMensaje.setText("Todo Correcto");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblResumenPC)
                        .addComponent(lblResumenMODULO))
                    .addGap(0, 889, Short.MAX_VALUE))
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDes, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(19, 19, 19))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)
                    .addComponent(lblDes)
                    .addGap(38, 38, 38)
                    .addComponent(lblResumenPC)
                    .addGap(40, 40, 40)
                    .addComponent(lblResumenMODULO)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            tab.addTab("tab1", jPanel5);

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));

            jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

            TbPC.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {},
                    {},
                    {},
                    {}
                },
                new String [] {

                }
            ));
            TbPC.setRowHeight(35);
            TbPC.setSelectionBackground(new java.awt.Color(102, 102, 102));
            TbPC.getTableHeader().setReorderingAllowed(false);
            TbPC.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    TbPCKeyPressed(evt);
                }
            });
            jScrollPane1.setViewportView(TbPC);

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
            );

            tab.addTab("tab7", jPanel9);

            jPanel6.setBackground(new java.awt.Color(43, 43, 43));
            jPanel6.setPreferredSize(new java.awt.Dimension(929, 115));

            jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(255, 255, 255));
            jLabel33.setText("Listado");

            lblUsuario.setForeground(new java.awt.Color(43, 43, 43));
            lblUsuario.setText("Silvana");

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(lblUsuario)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblUsuario)
                    .addContainerGap(35, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1141, Short.MAX_VALUE)
                        .addComponent(tab)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(tab))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //  txtcod.setText(cnn.idNomen());
        tab.setSelectedIndex(0);
      est=1;
      limpiar();
      enableDatos(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
//btnguardar.setEnabled(true);
enableDatos(true);
btneditar.setEnabled(false);
      est=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(est==1){
        Guardar();
        tab.setSelectedIndex(2);
        cargarPC();
        formatoPC();
        
        }else if(est==2){
            Modificar();
        tab.setSelectedIndex(2);
        cargarPC();
        formatoPC();
        est=1;
    }
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
try{
           
            int filaselec=TbPC.getSelectedRow();
            if( filaselec>=0){   
                ImageIcon ieli=new ImageIcon(this.getClass().getResource("/imagenes/iconos/eliminar16x16.png"));
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,ieli );
                    if(eliminar == 0 ){
        RegistroPC obj=new RegistroPC();
                obj.setPA_ID(Integer.parseInt(TbPC.getValueAt(filaselec, 1).toString()));
                if(obj.PC_eliminar())
                {
                    DefaultTableModel modelo = (DefaultTableModel)TbPC.getModel(); 
                    modelo.removeRow(filaselec);
                    JOptionPane.showMessageDialog(this, "Datos Eliminados");
                    limpiar();
                    enableDatos();
                    cargarPC();
                    formatoPC();
                }
                    
                }
            }else{
                JOptionPane.showMessageDialog(this, "Seleccione el Esquema a Eliminar");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Selecione el Detalle a eliminar");
        }

    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
           String consulta="";
    try {
             String titulos[]={"Nº","Area","Módulo","Servicio","Area","Terminal","NRO PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            LAB_PC_AREA obj=new LAB_PC_AREA();
                    consulta="exec CONFIGURACION_PC_AREA_Listar ?,?";
                    
        PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            cmd.setString(2, "2");
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
            TbPC.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            TbPC.setRowSorter(elQueOrdena);
            this.TbPC.setModel(m);
            formatoPC();
    } catch (Exception e) {
    }
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
            tab.setSelectedIndex(2);
             limpiar();
             enableDatos(true);
             cargarPC();
             formatoPC();
    }//GEN-LAST:event_btnListaActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        BuscarUnidad();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupos1.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupos1.requestFocus();
        }
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void btnBuscarPaciente4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPaciente4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarPaciente4ActionPerformed

    private void tb_Grupos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos1MouseClicked

        int fila=tb_Grupos1.getSelectedRow();
        if(evt.getClickCount()==2){
            Area.dispose();
            txtServicio.setText("");
            lblSEID.setText("");
            txtArea.setText("");
            lblARID.setText("");
            if(cbxServicio.isSelected()){
            txtServicio.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 2)));
            lblSEID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 3)));
            }
            if(cbxArea.isSelected()){
            txtArea.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            lblARID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            }
        }
    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos1.getSelectedRow();
            Area.dispose();
            txtServicio.setText("");
            lblSEID.setText("");
            txtArea.setText("");
            lblARID.setText("");
            if(cbxServicio.isSelected()){
            txtServicio.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 2)));
            lblSEID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 3)));
            }
            if(cbxArea.isSelected()){
            txtArea.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            lblARID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            }
        }
    }//GEN-LAST:event_tb_Grupos1KeyPressed

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        tab.setSelectedIndex(0);
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void txtServicioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtServicioCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtServicioCaretUpdate

    private void btnAlertConsulta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta3ActionPerformed
        tab.setSelectedIndex(2);
    }//GEN-LAST:event_btnAlertConsulta3ActionPerformed

    private void btnAlertConsulta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta2ActionPerformed
        RegistroPC cn = new RegistroPC();
        if(cbxModulo.getSelectedItem().toString().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Escriba el Módulo");
        }else if(cbxServicio.isSelected() &&lblSEID.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Seleccione el Servicio");
        }else  if(cbxArea.isSelected() &&lblARID.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(rootPane, "Seleccione el Área");
        }else{
        if(cn.VerificarNumero(cbxModulo.getSelectedItem().toString(),txtNRO.getText())>0){
            jLabel1.setForeground(new Color(41,127,184));

        }else{
            jLabel1.setForeground(new Color(255,255,255));
            
            if(txtNRO.getText().equalsIgnoreCase("")==false){
            lblResumenPC.setText("<HTML>"+"Terminal "+txtPC.getText()+"<br>"+"Nº "+txtNRO.getText()+"<BR>");
            }else{
                lblResumenPC.setText(txtPC.getText());
            }
            
            
            if(lblARID.getText().length()>0 &&lblSEID.getText().length()>0){
                lblResumenMODULO.setText("<HTML>"+cbxModulo.getSelectedItem().toString()+"<br>"+"Servicio  "+txtServicio.getText()
                        +"<BR>"+"Area  "+txtArea.getText());
            }else if(lblSEID.getText().length()>0){
                lblResumenMODULO.setText("<HTML>MODULO "+cbxModulo.getSelectedItem().toString()+"<br>"+"Servicio  "+txtServicio.getText());
            } else if(lblARID.getText().length()>0){
                lblResumenMODULO.setText("<HTML>"+cbxModulo.getSelectedItem().toString()+"<BR>"+"Area  "+txtArea.getText());
            } else{
            lblResumenMODULO.setText(cbxModulo.getSelectedItem().toString());
            }
            btnguardar.setEnabled(true);
                    tab.setSelectedIndex(1);
        }
        }
    }//GEN-LAST:event_btnAlertConsulta2ActionPerformed

    private void txtPCCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPCCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPCCaretUpdate

    private void txtNROKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNROKeyTyped
        if (txtNRO.getText().length()==1){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNROKeyTyped

    private void txtNROCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNROCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNROCaretUpdate

    private void btnBuscarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarServicioActionPerformed
        Area.setVisible(true);
    }//GEN-LAST:event_btnBuscarServicioActionPerformed

    private void txtAreaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAreaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaCaretUpdate

    private void TbPCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbPCKeyPressed
char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = TbPC.getSelectedRow();
            
            tab.setSelectedIndex(0);
            
            txtPC.setText(String.valueOf(TbPC.getValueAt(fila, 5)));
            lblID.setText(String.valueOf(TbPC.getValueAt(fila, 1)));
            cbxModulo.setSelectedItem(String.valueOf(TbPC.getValueAt(fila, 2)));
            if(String.valueOf(TbPC.getValueAt(fila, 6)).equalsIgnoreCase("0")){
            txtNRO.setText("");
            }else{
                txtNRO.setText(String.valueOf(TbPC.getValueAt(fila, 6)));
            }
            enableDatos(false);
            
            if(String.valueOf(TbPC.getValueAt(fila, 3)).equalsIgnoreCase("-")==false){
            txtServicio.setText(String.valueOf(TbPC.getValueAt(fila, 3)));
            lblSEID.setText(String.valueOf(TbPC.getValueAt(fila, 7)));
            cbxServicio.setSelected(true);
            }
            if(String.valueOf(TbPC.getValueAt(fila, 4)).equalsIgnoreCase("-")==false){
              txtArea.setText(String.valueOf(TbPC.getValueAt(fila, 4)));
            lblARID.setText(String.valueOf(TbPC.getValueAt(fila, 8)));
            cbxArea.setSelected(true);
            }
            btneditar.setEnabled(true);
        }
        
    }//GEN-LAST:event_TbPCKeyPressed
    public void guardarTipoUsuario(){
//         ImageIcon i=new ImageIcon(this.getClass().getResource("/imagenes/guardar.png")); 
//          tipoUsuario u2=new tipoUsuario();
//        if(txttipo.getText().equalsIgnoreCase("")||txtDescripcion.getText().equalsIgnoreCase("")){
//              JOptionPane.showMessageDialog(rootPane, "Verifique si ha ingresado todos los campos");
//          }  
//          else if(u2.ver_tipousuario(txttipo.getText())>0){
//              JOptionPane.showMessageDialog(rootPane, "El Tipo de Usuario ingresado ya existe\nIntente nuevamente");
//              txttipo.setText("");
//              txttipo.requestFocus();
//          }else{
//              
//              int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
//                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,i);
//              if(guardar == 0 ){
//                  tipoUsuario u = new tipoUsuario();
//                  u.setTipoUsu_Codigo(txtcodigo.getText());
//                  
//                  u.setTipoUsu_Tipo(txttipo.getText());
//                  u.setTipoUsu_Descripcion(txtDescripcion.getText());
//  
//                  if(u.guardarTipoUsuario()){
//                      JOptionPane.showMessageDialog(this, "Datos Guardados");
//                      limpiar();
//                      enableDatos();
//                      txtGuarModif.setText("G");
//                  }
//                  else{
//                      JOptionPane.showMessageDialog(this, "El Usuario ya se encuentra registrado\nIntente nuevamente");
//                      
//                  }}
//          }
    }
    
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
    public void limpiar()
    {
    lblID.setText("");
    txtNRO.setText("");
    RegistroPC pc = new RegistroPC();
        pc.CajaPC_Listar();
    cbxModulo.setSelectedIndex(0);
    txtArea.setText("");
    txtServicio.setText("");
    lblARID.setText("");
    lblSEID.setText("");
    btnguardar.setEnabled(false);
    btneditar.setEnabled(false);
    btneliminar.setEnabled(true);
    cbxServicio.setSelected(false);
    cbxArea.setSelected(false);
}

    public void enableDatos(boolean est){
        txtPC.setEnabled(est);
        txtNRO.setEnabled(est);
        cbxModulo.setEnabled(est);
        btnBuscarServicio.setEnabled(est);
        cbxServicio.setEnabled(est);
        cbxArea.setEnabled(est);
        
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
            java.util.logging.Logger.getLogger(ConfRegistroPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfRegistroPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfRegistroPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfRegistroPC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ConfRegistroPC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Area;
    private javax.swing.JTable TbPC;
    private javax.swing.JButton btnAlertConsulta2;
    private javax.swing.JButton btnAlertConsulta3;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnBuscarServicio;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JCheckBox cbxArea;
    private javax.swing.JComboBox cbxModulo;
    private javax.swing.JCheckBox cbxServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblARID;
    private javax.swing.JLabel lblDes;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMensaje;
    public static javax.swing.JLabel lblResumenMODULO;
    public static javax.swing.JLabel lblResumenPC;
    private javax.swing.JLabel lblSEID;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT12;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT14;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tb_Grupos1;
    public static javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtBuscar1;
    public static javax.swing.JTextField txtNRO;
    public static javax.swing.JTextField txtPC;
    public static javax.swing.JTextField txtServicio;
    // End of variables declaration//GEN-END:variables


}
