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
    /**
     * Creates new form Usuario
     */
    public ConfRegistroPC() {
        initComponents();
        cn.conectar();
         RegistroPC pc = new RegistroPC();
        pc.CajaPC_Listar();
        pc.PERFIL_USUARIO(PrincipalMDI.lblUsu.getText());
        this.getContentPane().setBackground(Color.white); 
        //setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal.png")).getImage());
        setLocationRelativeTo(null);//en el centro
      this.setExtendedState(MAXIMIZED_BOTH);
      tab.setSelectedIndex(5);
      Area.setLocationRelativeTo(null);
      
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/hospital32x32.png")).getImage());
       
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
    try {
             String titulos[]={"Nº","Area","Módulo","Servicio","Area","Terminal","NRO PC"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

        String consulta="exec CONFIGURACION_PC_AREA_Listar ";
        ResultSet r;
        r=cn.Listar(consulta);
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
    } catch (Exception e) {
    }
}
    public void formatoPC(){
    TbPC.getColumnModel().getColumn(0).setPreferredWidth(50);
    TbPC.getColumnModel().getColumn(1).setPreferredWidth(100);
    TbPC.getColumnModel().getColumn(2).setPreferredWidth(150);
    TbPC.getColumnModel().getColumn(3).setPreferredWidth(100);
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
             String titulos[]={"Área","","Servicio"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec buscarunidad ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);

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
    tb_Grupos1.setRowHeight(45);
}
    
    public void Guardar(){
        
//                Caja_PC_Registro cno1 = new Caja_PC_Registro();
//                cno1.setAR_ID(Integer.parseInt(lblARID.getText()) );//
//                cno1.setNOM_USU(lblUsuario.getText());//
//                cno1.setNRO_PC(Integer.parseInt(txtNRO.getText()));//
//                    if(cno1.NuevoTerminal()==true){
//                        System.out.println("GUARDADO ");
//                        lblMensaje.setText("Todo Correcto");
//                        lblDes.setText("La configuración del terminal se guardó de forma correcta.");
//                        panelMenu.setVisible(true);
//                        jPanel73.setVisible(false);
//                         
//                    }else{
//                        lblMensaje.setText("Algo salió mal");
//                        lblDes.setText("<HTML>"+"Verifique que la configuración sea la correcta,"+"<BR>"+"Puede que el Nº de PC este repetido"+"</HTML>");
//                        panelMenu.setVisible(false);
//                        jPanel73.setVisible(true);
//    }
                        
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
            jPanel1 = new javax.swing.JPanel();
            jLabel57 = new javax.swing.JLabel();
            lblUsu = new javax.swing.JLabel();
            jPanel66 = new javax.swing.JPanel();
            btnAlertConsulta1 = new javax.swing.JButton();
            lblUsuario = new javax.swing.JLabel();
            jPanel8 = new javax.swing.JPanel();
            jLabel58 = new javax.swing.JLabel();
            jLabel21 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            panelCPT12 = new javax.swing.JPanel();
            txtCPT3 = new javax.swing.JTextField();
            btnBuscarCPT4 = new javax.swing.JButton();
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
            lblServicio1 = new javax.swing.JTextField();
            btnBuscarCPT3 = new javax.swing.JButton();
            jLabel23 = new javax.swing.JLabel();
            panelCPT16 = new javax.swing.JPanel();
            txtModulo = new javax.swing.JTextField();
            jPanel4 = new javax.swing.JPanel();
            jLabel59 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            jLabel8 = new javax.swing.JLabel();
            panelCPT15 = new javax.swing.JPanel();
            txtCPT8 = new javax.swing.JTextField();
            jPanel69 = new javax.swing.JPanel();
            btnAlertConsulta4 = new javax.swing.JButton();
            jPanel70 = new javax.swing.JPanel();
            btnAlertConsulta5 = new javax.swing.JButton();
            btnImprimir = new javax.swing.JButton();
            cbxImpresoras = new javax.swing.JComboBox();
            jPanel5 = new javax.swing.JPanel();
            jLabel60 = new javax.swing.JLabel();
            jPanel71 = new javax.swing.JPanel();
            btnAlertConsulta6 = new javax.swing.JButton();
            jPanel72 = new javax.swing.JPanel();
            btnAlertConsulta7 = new javax.swing.JButton();
            lblResumenUsuario = new javax.swing.JLabel();
            lblResumenPC = new javax.swing.JLabel();
            jLabel22 = new javax.swing.JLabel();
            jPanel10 = new javax.swing.JPanel();
            lblMensaje = new javax.swing.JLabel();
            lblDes = new javax.swing.JLabel();
            panelMenu = new javax.swing.JPanel();
            lblUsu1 = new javax.swing.JLabel();
            jPanel73 = new javax.swing.JPanel();
            btnAlertConsulta8 = new javax.swing.JButton();
            jPanel9 = new javax.swing.JPanel();
            jScrollPane1 = new javax.swing.JScrollPane();
            TbPC = new javax.swing.JTable();
            jPanel6 = new javax.swing.JPanel();
            jLabel33 = new javax.swing.JLabel();

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

            jPanel3.setBackground(new java.awt.Color(41, 127, 184));
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
            lbldetalle.setText("CPT, Descripción");

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

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));

            jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel57.setForeground(new java.awt.Color(51, 51, 51));
            jLabel57.setText("Hola, Bienvenido a la configuración de la estación de trabajo");

            lblUsu.setBackground(new java.awt.Color(51, 51, 51));
            lblUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            lblUsu.setForeground(new java.awt.Color(51, 51, 51));
            lblUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Gerente-80.png"))); // NOI18N
            lblUsu.setText("Numero de Terminal");

            jPanel66.setBackground(new java.awt.Color(43, 43, 43));

            btnAlertConsulta1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta1.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta1.setText("Siguiente");
            btnAlertConsulta1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta1.setContentAreaFilled(false);
            btnAlertConsulta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta1.setIconTextGap(30);
            btnAlertConsulta1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
            jPanel66.setLayout(jPanel66Layout);
            jPanel66Layout.setHorizontalGroup(
                jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel66Layout.setVerticalGroup(
                jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            lblUsuario.setForeground(new java.awt.Color(41, 127, 184));
            lblUsuario.setText("jLabel1");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(980, Short.MAX_VALUE)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(lblUsuario))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(267, 267, 267)
                            .addComponent(lblUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                    .addComponent(lblUsu)
                    .addGap(82, 82, 82)
                    .addComponent(lblUsuario)
                    .addGap(85, 85, 85)
                    .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
            );

            tab.addTab("tab1", jPanel1);

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

            txtCPT3.setEditable(false);
            txtCPT3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT3.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCPT3.setBorder(null);
            txtCPT3.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT3CaretUpdate(evt);
                }
            });

            btnBuscarCPT4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarCPT4.setContentAreaFilled(false);
            btnBuscarCPT4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarCPT4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarCPT4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT12Layout = new javax.swing.GroupLayout(panelCPT12);
            panelCPT12.setLayout(panelCPT12Layout);
            panelCPT12Layout.setHorizontalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT12Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBuscarCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3))
            );
            panelCPT12Layout.setVerticalGroup(
                panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT12Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelCPT12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCPT3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(btnBuscarCPT4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelCPT13.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtNRO.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtNRO.setForeground(new java.awt.Color(51, 51, 51));
            txtNRO.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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
                    .addGap(5, 5, 5)
                    .addComponent(txtNRO, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
            );
            panelCPT13Layout.setVerticalGroup(
                panelCPT13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT13Layout.createSequentialGroup()
                    .addComponent(txtNRO, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            panelCPT14.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtPC.setEditable(false);
            txtPC.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtPC.setForeground(new java.awt.Color(51, 51, 51));
            txtPC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
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
                .addGroup(panelCPT14Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtPC, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
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

            jPanel68.setBackground(new java.awt.Color(0, 98, 155));

            btnAlertConsulta3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta3.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta3.setText("Anterior");
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
            lblARID.setText("jLabel1");

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Este Nº de terminal ya se encuentra consignado.");

            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            jLabel17.setText("Servicio");

            panelCPT10.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            lblServicio1.setEditable(false);
            lblServicio1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            lblServicio1.setForeground(new java.awt.Color(51, 51, 51));
            lblServicio1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            lblServicio1.setBorder(null);
            lblServicio1.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    lblServicio1CaretUpdate(evt);
                }
            });

            btnBuscarCPT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
            btnBuscarCPT3.setContentAreaFilled(false);
            btnBuscarCPT3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnBuscarCPT3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnBuscarCPT3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelCPT10Layout = new javax.swing.GroupLayout(panelCPT10);
            panelCPT10.setLayout(panelCPT10Layout);
            panelCPT10Layout.setHorizontalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT10Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(lblServicio1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBuscarCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(3, 3, 3))
            );
            panelCPT10Layout.setVerticalGroup(
                panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT10Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(panelCPT10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblServicio1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(btnBuscarCPT3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel23.setForeground(new java.awt.Color(51, 51, 51));
            jLabel23.setText("Módulo");

            panelCPT16.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtModulo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtModulo.setForeground(new java.awt.Color(51, 51, 51));
            txtModulo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtModulo.setBorder(null);
            txtModulo.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtModuloCaretUpdate(evt);
                }
            });
            txtModulo.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    txtModuloKeyTyped(evt);
                }
            });

            javax.swing.GroupLayout panelCPT16Layout = new javax.swing.GroupLayout(panelCPT16);
            panelCPT16.setLayout(panelCPT16Layout);
            panelCPT16Layout.setHorizontalGroup(
                panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT16Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtModulo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
            );
            panelCPT16Layout.setVerticalGroup(
                panelCPT16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCPT16Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txtModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

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
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel17))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(panelCPT14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(panelCPT10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(128, 128, 128)
                                            .addComponent(lblARID)
                                            .addGap(39, 475, Short.MAX_VALUE))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(panelCPT12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addComponent(panelCPT16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(31, 31, 31)
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(panelCPT13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(0, 0, Short.MAX_VALUE)))))
                    .addGap(13, 13, 13))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(55, 55, 55)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel21)
                        .addComponent(panelCPT14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(27, 27, 27)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panelCPT13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelCPT16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(34, 34, 34)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblARID)
                        .addComponent(panelCPT10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelCPT12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(141, 141, 141)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel68, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14))
            );

            tab.addTab("tab1", jPanel8);

            jPanel4.setBackground(new java.awt.Color(255, 255, 255));
            jPanel4.setForeground(new java.awt.Color(51, 51, 51));

            jLabel59.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel59.setText("Configuración de la Impresora");

            jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel7.setText("Nombre de la impresora");

            jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel8.setText("Puerto");

            panelCPT15.setBackground(new java.awt.Color(255, 255, 255));
            panelCPT15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            txtCPT8.setEditable(false);
            txtCPT8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
            txtCPT8.setForeground(new java.awt.Color(51, 51, 51));
            txtCPT8.setHorizontalAlignment(javax.swing.JTextField.LEFT);
            txtCPT8.setBorder(null);
            txtCPT8.addCaretListener(new javax.swing.event.CaretListener() {
                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                    txtCPT8CaretUpdate(evt);
                }
            });

            javax.swing.GroupLayout panelCPT15Layout = new javax.swing.GroupLayout(panelCPT15);
            panelCPT15.setLayout(panelCPT15Layout);
            panelCPT15Layout.setHorizontalGroup(
                panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT15Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(txtCPT8, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
            );
            panelCPT15Layout.setVerticalGroup(
                panelCPT15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCPT15Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(txtCPT8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel69.setBackground(new java.awt.Color(43, 43, 43));

            btnAlertConsulta4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta4.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta4.setText("Siguiente");
            btnAlertConsulta4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta4.setContentAreaFilled(false);
            btnAlertConsulta4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta4.setIconTextGap(30);
            btnAlertConsulta4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta4ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
            jPanel69.setLayout(jPanel69Layout);
            jPanel69Layout.setHorizontalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel69Layout.setVerticalGroup(
                jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel70.setBackground(new java.awt.Color(0, 98, 155));

            btnAlertConsulta5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta5.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta5.setText("Anterior");
            btnAlertConsulta5.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta5.setContentAreaFilled(false);
            btnAlertConsulta5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta5.setIconTextGap(30);
            btnAlertConsulta5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta5ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
            jPanel70.setLayout(jPanel70Layout);
            jPanel70Layout.setHorizontalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel70Layout.setVerticalGroup(
                jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel70Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            btnImprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Imprimir-32.png"))); // NOI18N
            btnImprimir.setText("Prueba de Impresión");
            btnImprimir.setContentAreaFilled(false);
            btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnImprimir.setEnabled(false);
            btnImprimir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            btnImprimir.setIconTextGap(30);
            btnImprimir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnImprimir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnImprimirActionPerformed(evt);
                }
            });

            cbxImpresoras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            cbxImpresoras.setForeground(new java.awt.Color(51, 51, 51));
            cbxImpresoras.setEnabled(false);

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(61, 61, 61)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(cbxImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(19, 19, 19)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(panelCPT15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addComponent(btnImprimir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                            .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(14, 14, 14))
            );

            tab.addTab("tab1", jPanel4);

            jPanel5.setBackground(new java.awt.Color(255, 255, 255));

            jLabel60.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            jLabel60.setForeground(new java.awt.Color(51, 51, 51));
            jLabel60.setText("Resumen");

            jPanel71.setBackground(new java.awt.Color(43, 43, 43));

            btnAlertConsulta6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta6.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta6.setText("Terminar");
            btnAlertConsulta6.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta6.setContentAreaFilled(false);
            btnAlertConsulta6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta6.setIconTextGap(30);
            btnAlertConsulta6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta6ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
            jPanel71.setLayout(jPanel71Layout);
            jPanel71Layout.setHorizontalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel71Layout.setVerticalGroup(
                jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel71Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            jPanel72.setBackground(new java.awt.Color(0, 98, 155));

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

            lblResumenUsuario.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenUsuario.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Gerente-80.png"))); // NOI18N
            lblResumenUsuario.setText("Nombre de Terminal");

            lblResumenPC.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            lblResumenPC.setForeground(new java.awt.Color(51, 51, 51));
            lblResumenPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Estación de trabajo-80.png"))); // NOI18N
            lblResumenPC.setText("Nombre de Terminal");

            jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
            jLabel22.setForeground(new java.awt.Color(51, 51, 51));
            jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Imprimir-80.png"))); // NOI18N
            jLabel22.setText("Nombre de Terminal");

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblResumenPC)
                                        .addComponent(lblResumenUsuario)
                                        .addComponent(jLabel22))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addContainerGap(837, Short.MAX_VALUE)
                            .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(lblResumenUsuario)
                            .addGap(18, 18, 18)
                            .addComponent(lblResumenPC)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                            .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel72, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(14, 14, 14))
            );

            tab.addTab("tab1", jPanel5);

            jPanel10.setBackground(new java.awt.Color(255, 255, 255));

            lblMensaje.setFont(new java.awt.Font("Segoe UI Light", 0, 28)); // NOI18N
            lblMensaje.setForeground(new java.awt.Color(51, 51, 51));
            lblMensaje.setText("Todo Correcto");

            lblDes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
            lblDes.setForeground(new java.awt.Color(51, 51, 51));
            lblDes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblDes.setText("La configuración del terminal se guardó de forma correcta.");
            lblDes.setToolTipText("");

            panelMenu.setBackground(new java.awt.Color(0, 98, 155));

            lblUsu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            lblUsu1.setForeground(new java.awt.Color(51, 51, 51));
            lblUsu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblUsu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Menú principal-80.png"))); // NOI18N
            lblUsu1.setText("Menú Principal");
            lblUsu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            lblUsu1.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    lblUsu1MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
            panelMenu.setLayout(panelMenuLayout);
            panelMenuLayout.setHorizontalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
            );
            panelMenuLayout.setVerticalGroup(
                panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelMenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblUsu1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel73.setBackground(new java.awt.Color(0, 98, 155));

            btnAlertConsulta8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            btnAlertConsulta8.setForeground(new java.awt.Color(240, 240, 240));
            btnAlertConsulta8.setText("Anterior");
            btnAlertConsulta8.setBorder(javax.swing.BorderFactory.createCompoundBorder());
            btnAlertConsulta8.setContentAreaFilled(false);
            btnAlertConsulta8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnAlertConsulta8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnAlertConsulta8.setIconTextGap(30);
            btnAlertConsulta8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAlertConsulta8ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
            jPanel73.setLayout(jPanel73Layout);
            jPanel73Layout.setHorizontalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnAlertConsulta8, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
            );
            jPanel73Layout.setVerticalGroup(
                jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnAlertConsulta8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 1092, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(lblDes, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(13, 13, 13))
                .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(lblDes)
                    .addGap(111, 111, 111)
                    .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                    .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
            );

            tab.addTab("tab1", jPanel10);

            jPanel9.setBackground(new java.awt.Color(255, 255, 255));

            TbPC.setModel(new javax.swing.table.DefaultTableModel(
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
            TbPC.setRowHeight(35);
            jScrollPane1.setViewportView(TbPC);

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            );

            tab.addTab("tab7", jPanel9);

            jPanel6.setBackground(new java.awt.Color(43, 43, 43));
            jPanel6.setPreferredSize(new java.awt.Dimension(929, 115));

            jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
            jLabel33.setForeground(new java.awt.Color(255, 255, 255));
            jLabel33.setText("Listado");

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE))
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
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
                        .addComponent(tab)))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
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
      
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
       
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed


    }//GEN-LAST:event_btneliminarActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
       
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
tab.setSelectedIndex(5);
             
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
            txtCPT3.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            lblARID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
        }
    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed

        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos1.getSelectedRow();
            Area.dispose();
            txtCPT3.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            lblARID.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));

        }
    }//GEN-LAST:event_tb_Grupos1KeyPressed

    private void btnAlertConsulta8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta8ActionPerformed
        tab.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta8ActionPerformed

    private void lblUsu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsu1MouseClicked

    }//GEN-LAST:event_lblUsu1MouseClicked

    private void btnAlertConsulta7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta7ActionPerformed
        tab.setSelectedIndex(2);
    }//GEN-LAST:event_btnAlertConsulta7ActionPerformed

    private void btnAlertConsulta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta6ActionPerformed
        Guardar();
        tab.setSelectedIndex(4);
    }//GEN-LAST:event_btnAlertConsulta6ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnAlertConsulta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta5ActionPerformed
        tab.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta5ActionPerformed

    private void btnAlertConsulta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta4ActionPerformed

        lblResumenPC.setText("<HTML>"+"Terminal "+txtPC.getText()+"<br>"+"Nº "+txtNRO.getText()+"<BR>"+"Área "+txtCPT3.getText());

        tab.setSelectedIndex(3);
    }//GEN-LAST:event_btnAlertConsulta4ActionPerformed

    private void txtCPT8CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT8CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT8CaretUpdate

    private void txtModuloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModuloKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModuloKeyTyped

    private void txtModuloCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtModuloCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModuloCaretUpdate

    private void btnBuscarCPT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarCPT3ActionPerformed

    private void lblServicio1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_lblServicio1CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_lblServicio1CaretUpdate

    private void btnAlertConsulta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta3ActionPerformed
        tab.setSelectedIndex(0);
    }//GEN-LAST:event_btnAlertConsulta3ActionPerformed

    private void btnAlertConsulta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta2ActionPerformed
        Caja_PC_Registro cn = new Caja_PC_Registro();
        if(cn.VerificarNumero(txtNRO.getText())>0){
            jLabel1.setForeground(new Color(255,255,255));

        }else{
            jLabel1.setForeground(new Color(41,127,184));
            tab.setSelectedIndex(2);
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

    private void btnBuscarCPT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPT4ActionPerformed
        Area.setVisible(true);
    }//GEN-LAST:event_btnBuscarCPT4ActionPerformed

    private void txtCPT3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCPT3CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPT3CaretUpdate

    private void btnAlertConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlertConsulta1ActionPerformed
        tab.setSelectedIndex(1);
    }//GEN-LAST:event_btnAlertConsulta1ActionPerformed
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
  
    btnguardar.setEnabled(true);
    btneditar.setEnabled(false);
    btneliminar.setEnabled(false);
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
    private javax.swing.JButton btnAlertConsulta1;
    private javax.swing.JButton btnAlertConsulta2;
    private javax.swing.JButton btnAlertConsulta3;
    private javax.swing.JButton btnAlertConsulta4;
    private javax.swing.JButton btnAlertConsulta5;
    private javax.swing.JButton btnAlertConsulta6;
    private javax.swing.JButton btnAlertConsulta7;
    private javax.swing.JButton btnAlertConsulta8;
    private javax.swing.JButton btnBuscarCPT3;
    private javax.swing.JButton btnBuscarCPT4;
    private javax.swing.JButton btnBuscarPaciente;
    private javax.swing.JButton btnBuscarPaciente4;
    private javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JComboBox cbxImpresoras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblARID;
    private javax.swing.JLabel lblDes;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblResumenPC;
    private javax.swing.JLabel lblResumenUsuario;
    private javax.swing.JTextField lblServicio1;
    public static javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblUsu1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lbldetalle;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JPanel panelCPT10;
    private javax.swing.JPanel panelCPT12;
    private javax.swing.JPanel panelCPT13;
    private javax.swing.JPanel panelCPT14;
    private javax.swing.JPanel panelCPT15;
    private javax.swing.JPanel panelCPT16;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tb_Grupos1;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCPT3;
    private javax.swing.JTextField txtCPT8;
    private javax.swing.JTextField txtModulo;
    private javax.swing.JTextField txtNRO;
    public static javax.swing.JTextField txtPC;
    // End of variables declaration//GEN-END:variables


}
