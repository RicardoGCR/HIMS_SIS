/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ConsultorioEx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.ConsultorioEx.CONSULTORIOEXTCONSULTORIOOrden;
import modelos.admisionEmergencia.AdmisionEmergenciaCabecera;

/**
 *
 * @author MYS1
 */
public class ConsultorioExtOrdenes extends javax.swing.JInternalFrame {
private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension DimensionBarra = null; 
    DefaultTableModel m;
    byte tg;
    byte tge;
    /**
     * Creates new form ConsultorioExtOrdenes
     */
    public ConsultorioExtOrdenes() {
        initComponents();
        QuitarLaBarraTitulo();
        mensaje.setVisible(false);
        nomenclaturas.setLocationRelativeTo(null);//en el centro
        nomenclaturas.getContentPane().setBackground(Color.WHITE);

    }
    public void QuitarLaBarraTitulo(){ 
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane(); 
        DimensionBarra = Barra.getPreferredSize(); 
        Barra.setSize(0,0); 
        Barra.setPreferredSize(new Dimension(0,0)); 
        this.getContentPane().setBackground(new Color(242,242,242)); 
        repaint();   
    }
    
    public void GuardarPreventa( ){
         try {
    CONSULTORIOEXTCONSULTORIOOrden CXRsR= new CONSULTORIOEXTCONSULTORIOOrden();
    CONSULTORIOEXTCONSULTORIOOrden CXRsR2= new CONSULTORIOEXTCONSULTORIOOrden();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant.getText().equals("E"))
            CXRsR.setId_Preventa(Integer.parseInt(lblIDPREVENTA.getText()));
            CXRsR.setId_hc(lblid_hc.getText());
            CXRsR.setCod_nomen(lblCPT.getText());
            CXRsR.setCodUsu(adEmerCab.codUsuario(ConsultorioExt.lblusu.getText()));
                if(CXRsR.mantenimientoConsultorioExtPREVENTA(lblMant.getText())==true){
                    if (lblMant.getText().equals("I")){
                        System.out.println("CPT AGREGADO");
                        
                        
                        CXRsR2.ConsultoriosExtPREVENTAListar(lblid_hc.getText()); 
                        CXRsR2.cargarDatosPreventa(lblid_hc.getText(),tbPatologias1); 
                        
                    }
                    if (lblMant.getText().equals("E")){
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(39,174,97)); 
                        men.setText("Registro Actualizado de forma correcta");
                        b1.setText("OK");
                        b1.setVisible(true);
                        b.setVisible(false);
                        CXRsR2.CONSULTORIO_EXT_LISTAR_CONSULTORIO_ORDEN_RECETADEAS(lblID.getText(),tbPatologias1); 
                    }
                }else {
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(39,174,97)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;    
                }  
              } catch (Exception e) {
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
         }
    }
    
    public void Guardar( ){
         try {

    CONSULTORIOEXTCONSULTORIOOrden CXRsR= new CONSULTORIOEXTCONSULTORIOOrden();
    CONSULTORIOEXTCONSULTORIOOrden CXRsR2= new CONSULTORIOEXTCONSULTORIOOrden();
    AdmisionEmergenciaCabecera adEmerCab = new AdmisionEmergenciaCabecera();
            if(lblMant1.getText().equals("U"))
            CXRsR.setIdOrden(Integer.parseInt(lblIDO.getText()));
            CXRsR.setIdConsultorioEx(Integer.parseInt(lblID.getText()));
            CXRsR.setId_Preventa(Integer.parseInt(lblIDPREVENTA.getText()));
            CXRsR.setCodUsu(adEmerCab.codUsuario(ConsultorioExt.lblusu.getText()));

            
                if(CXRsR.mantenimientoConsultorioExtOrden("I")==true){
                    if (lblMant1.getText().equals("I")){
                        System.out.println("Guardo ORDEN");

                    tge=1;
                   
                    CXRsR2.ConsultoriosExtOrdenListar(lblID.getText());
                    
                    }
                    if (lblMant.getText().equals("U")){
                   
                        System.out.println("Actualizado orden");

             
                    tge=9;
                    
//                    CXRsR2.ConsultoriosExtOrdenListar(lblID.getText());
                    }        

//                    habilitarDatos(false);
                }else {

                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
                       
                }  
              } catch (Exception e) {
                        mensaje.setVisible(true);
                        mensaje.setBackground(new Color(255,91,70)); 
                        men.setText("Ocurrio un error, Verifique");
                        b.setVisible(false);
                        b1.setVisible(false);
                        tge=7;
         }
  
    }
    
    public void BuscarN(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo4.setModel(new DefaultTableModel());
             String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[9];

            CONSULTORIOEXTCONSULTORIOOrden obj=new CONSULTORIOEXTCONSULTORIOOrden();
                    consulta="exec Caja_NomenclaturaVentaBUSCAR ?,?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            cmd.setString(2, "");
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
       


                m.addRow(fila);
                c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);

           formatocpt();
         
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
      public void formatocpt(){
    tb_Grupo4.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo4.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_Grupo4.getColumnModel().getColumn(2).setPreferredWidth(80);
    tb_Grupo4.getColumnModel().getColumn(3).setPreferredWidth(100);
    tb_Grupo4.getColumnModel().getColumn(4).setPreferredWidth(100);

    
    tb_Grupo4.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Grupo4.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(6).setMaxWidth(0);
    tb_Grupo4.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_Grupo4.getColumnModel().getColumn(8).setMinWidth(0);
    tb_Grupo4.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_Grupo4.setRowHeight(30);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomenclaturas = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        T4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_Grupo4 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel15 = new javax.swing.JPanel();
            jLabel23 = new javax.swing.JLabel();
            jLabel24 = new javax.swing.JLabel();
            jPanel3 = new javax.swing.JPanel();
            btnNuevo = new javax.swing.JButton();
            btnGuardar = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            jLabel30 = new javax.swing.JLabel();
            btnEliminar = new javax.swing.JButton();
            lblID = new javax.swing.JLabel();
            lblMant = new javax.swing.JLabel();
            lblIDO = new javax.swing.JLabel();
            var = new javax.swing.JLabel();
            lblid_hc = new javax.swing.JLabel();
            lblMant1 = new javax.swing.JLabel();
            jButton1 = new javax.swing.JButton();
            jPanel1 = new javax.swing.JPanel();
            jScrollPane5 = new javax.swing.JScrollPane();
            tbPatologias = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel2 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                jScrollPane6 = new javax.swing.JScrollPane();
                tbPatologias1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel21 = new javax.swing.JPanel();
                    txtCIE11 = new javax.swing.JTextField();
                    btnBuscarCIE11 = new javax.swing.JButton();
                    jLabel12 = new javax.swing.JLabel();
                    lblCPT = new javax.swing.JLabel();
                    mensaje = new javax.swing.JPanel();
                    men = new javax.swing.JLabel();
                    b = new javax.swing.JButton();
                    b1 = new javax.swing.JButton();
                    lblIDPREVENTA = new javax.swing.JLabel();

                    nomenclaturas.setAlwaysOnTop(true);
                    nomenclaturas.setMinimumSize(new java.awt.Dimension(749, 338));
                    nomenclaturas.setResizable(false);
                    nomenclaturas.getContentPane().setLayout(null);

                    jPanel11.setBackground(new java.awt.Color(43, 43, 43));
                    jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel21.setText("CPT");

                    jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                    txtBuscar2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtBuscar2.setBorder(null);
                    txtBuscar2.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtBuscar2CaretUpdate(evt);
                        }
                    });
                    txtBuscar2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtBuscar2ActionPerformed(evt);
                        }
                    });
                    txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscar2KeyPressed(evt);
                        }
                    });

                    T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T4MouseClicked(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                    jPanel28.setLayout(jPanel28Layout);
                    jPanel28Layout.setHorizontalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel28Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel28Layout.setVerticalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtBuscar2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(T4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );

                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                    jPanel11.setLayout(jPanel11Layout);
                    jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(494, Short.MAX_VALUE))
                    );
                    jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(353, 353, 353))
                    );

                    nomenclaturas.getContentPane().add(jPanel11);
                    jPanel11.setBounds(0, 0, 770, 100);

                    jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                    jPanel12.setLayout(jPanel12Layout);
                    jPanel12Layout.setHorizontalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 750, Short.MAX_VALUE)
                    );
                    jPanel12Layout.setVerticalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 50, Short.MAX_VALUE)
                    );

                    nomenclaturas.getContentPane().add(jPanel12);
                    jPanel12.setBounds(0, 312, 750, 50);

                    jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel22.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                    jLabel22.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Nueva ventana-100.png"))); // NOI18N
                    jLabel22.setText("Busqueda de CPT ");

                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                    jPanel13.setLayout(jPanel13Layout);
                    jPanel13Layout.setHorizontalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(160, 160, 160)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(198, Short.MAX_VALUE))
                    );
                    jPanel13Layout.setVerticalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(jLabel22)
                            .addContainerGap(53, Short.MAX_VALUE))
                    );

                    jTabbedPane3.addTab("tab2", jPanel13);

                    jPanel14.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
                    jScrollPane7.setBorder(null);

                    tb_Grupo4.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Grupo4.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo4.setRowHeight(25);
                    tb_Grupo4.setSelectionBackground(new java.awt.Color(39, 174, 97));
                    tb_Grupo4.getTableHeader().setReorderingAllowed(false);
                    tb_Grupo4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Grupo4MouseClicked(evt);
                        }
                    });
                    tb_Grupo4.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_Grupo4KeyPressed(evt);
                        }
                    });
                    jScrollPane7.setViewportView(tb_Grupo4);

                    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                    jPanel14.setLayout(jPanel14Layout);
                    jPanel14Layout.setHorizontalGroup(
                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 754, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                    );
                    jPanel14Layout.setVerticalGroup(
                        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 212, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                    );

                    jTabbedPane3.addTab("tab2", jPanel14);

                    jPanel15.setBackground(new java.awt.Color(255, 255, 255));

                    jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                    jLabel23.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel23.setText("No se hallaron coincidencias");

                    jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                    jLabel24.setForeground(new java.awt.Color(0, 153, 153));
                    jLabel24.setText(":(");

                    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                    jPanel15.setLayout(jPanel15Layout);
                    jPanel15Layout.setHorizontalGroup(
                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addComponent(jLabel24)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel23)
                            .addContainerGap(145, Short.MAX_VALUE))
                    );
                    jPanel15Layout.setVerticalGroup(
                        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGap(87, 87, 87)
                                    .addComponent(jLabel23))
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(jLabel24)))
                            .addContainerGap(41, Short.MAX_VALUE))
                    );

                    jTabbedPane3.addTab("tab3", jPanel15);

                    nomenclaturas.getContentPane().add(jTabbedPane3);
                    jTabbedPane3.setBounds(0, 98, 749, 240);

                    setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    setVisible(true);

                    jPanel3.setBackground(new java.awt.Color(43, 43, 43));

                    btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                    btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Izquierda-32 (1).png"))); // NOI18N
                    btnNuevo.setContentAreaFilled(false);
                    btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnNuevo.setIconTextGap(30);
                    btnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnNuevo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnNuevoActionPerformed(evt);
                        }
                    });

                    btnGuardar.setForeground(new java.awt.Color(240, 240, 240));
                    btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Guardar-32.png"))); // NOI18N
                    btnGuardar.setContentAreaFilled(false);
                    btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnGuardar.setIconTextGap(30);
                    btnGuardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnGuardarActionPerformed(evt);
                        }
                    });

                    btneditar.setForeground(new java.awt.Color(240, 240, 240));
                    btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                    btneditar.setContentAreaFilled(false);
                    btneditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btneditar.setIconTextGap(30);
                    btneditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btneditar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btneditarActionPerformed(evt);
                        }
                    });

                    jLabel30.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                    jLabel30.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel30.setText("Ordenes Médicas");

                    btnEliminar.setForeground(new java.awt.Color(240, 240, 240));
                    btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                    btnEliminar.setContentAreaFilled(false);
                    btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnEliminar.setEnabled(false);
                    btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    btnEliminar.setIconTextGap(30);
                    btnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    btnEliminar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnEliminarActionPerformed(evt);
                        }
                    });

                    lblID.setForeground(new java.awt.Color(255, 255, 255));
                    lblID.setText("jLabel8");

                    lblMant.setForeground(new java.awt.Color(255, 255, 255));
                    lblMant.setText("I");

                    lblIDO.setForeground(new java.awt.Color(255, 255, 255));

                    var.setForeground(new java.awt.Color(255, 255, 255));
                    var.setText("1");

                    lblid_hc.setText("jLabel13");

                    lblMant1.setForeground(new java.awt.Color(255, 255, 255));
                    lblMant1.setText("jLabel13");

                    jButton1.setText("jButton1");
                    jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton1ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1)
                                    .addGap(0, 8, Short.MAX_VALUE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(lblID)
                                            .addGap(32, 32, 32)
                                            .addComponent(lblMant)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(var, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(lblMant1)
                                            .addGap(0, 0, Short.MAX_VALUE)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblid_hc)
                            .addGap(285, 285, 285))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblID)
                                        .addComponent(lblMant)
                                        .addComponent(lblIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(var, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblid_hc))
                                    .addGap(19, 19, 19))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMant1)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addContainerGap())))
                    );

                    jPanel1.setBackground(new java.awt.Color(242, 242, 242));

                    jScrollPane5.setBorder(null);

                    tbPatologias.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbPatologias.setGridColor(new java.awt.Color(255, 255, 255));
                    tbPatologias.setRowHeight(25);
                    tbPatologias.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbPatologias.getTableHeader().setReorderingAllowed(false);
                    tbPatologias.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbPatologiasMouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbPatologiasMousePressed(evt);
                        }
                    });
                    tbPatologias.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbPatologiasKeyPressed(evt);
                        }
                    });
                    jScrollPane5.setViewportView(tbPatologias);

                    jPanel2.setBackground(new java.awt.Color(242, 242, 242));

                    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel2.setForeground(new java.awt.Color(255, 137, 19));
                    jLabel2.setText("Medicamento");

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel3.setForeground(new java.awt.Color(255, 137, 19));
                    jLabel3.setText("Dosis");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(255, 137, 19));
                    jLabel4.setText("Cantidad");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel5.setForeground(new java.awt.Color(255, 137, 19));
                    jLabel5.setText("Via");

                    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    jLabel6.setForeground(new java.awt.Color(255, 137, 19));
                    jLabel6.setText("Frecuencia");

                    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel7.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel7.setText("Medicamento");

                    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel8.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel8.setText("Cantidad");

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel9.setText("Dosis");

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel10.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel10.setText("Via");

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel11.setText("Frecuencia");

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7))
                            .addGap(57, 57, 57)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8))
                            .addGap(57, 57, 57)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel9))
                            .addGap(57, 57, 57)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel10))
                            .addGap(57, 57, 57)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel6))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(jLabel8)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11))
                            .addContainerGap(24, Short.MAX_VALUE))
                    );

                    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel1.setText("CIE 10");

                    jScrollPane6.setBorder(null);

                    tbPatologias1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    tbPatologias1.setForeground(new java.awt.Color(51, 51, 51));
                    tbPatologias1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "Title 1", "Title 2", "Title 3", "Title 4"
                        }
                    ));
                    tbPatologias1.setGridColor(new java.awt.Color(255, 255, 255));
                    tbPatologias1.setRowHeight(25);
                    tbPatologias1.setSelectionBackground(new java.awt.Color(0, 153, 102));
                    tbPatologias1.getTableHeader().setReorderingAllowed(false);
                    tbPatologias1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tbPatologias1MouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            tbPatologias1MousePressed(evt);
                        }
                    });
                    tbPatologias1.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tbPatologias1KeyPressed(evt);
                        }
                    });
                    jScrollPane6.setViewportView(tbPatologias1);

                    jPanel21.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtCIE11.setEditable(false);
                    txtCIE11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
                    txtCIE11.setForeground(new java.awt.Color(102, 102, 102));
                    txtCIE11.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    txtCIE11.setBorder(null);
                    txtCIE11.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtCIE11CaretUpdate(evt);
                        }
                    });

                    btnBuscarCIE11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    btnBuscarCIE11.setMnemonic('B');
                    btnBuscarCIE11.setToolTipText("");
                    btnBuscarCIE11.setBorderPainted(false);
                    btnBuscarCIE11.setContentAreaFilled(false);
                    btnBuscarCIE11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btnBuscarCIE11.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            btnBuscarCIE11MouseClicked(evt);
                        }
                    });
                    btnBuscarCIE11.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            btnBuscarCIE11ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                    jPanel21.setLayout(jPanel21Layout);
                    jPanel21Layout.setHorizontalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(txtCIE11, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscarCIE11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );
                    jPanel21Layout.setVerticalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnBuscarCIE11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(txtCIE11, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel12.setText("Ordenar CPT");

                    lblCPT.setText("jLabel13");

                    mensaje.setBackground(new java.awt.Color(33, 115, 70));

                    men.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    men.setForeground(new java.awt.Color(255, 255, 255));
                    men.setText("Desea Actualizar el Registro ?");

                    b.setForeground(new java.awt.Color(240, 240, 240));
                    b.setText("Si");
                    b.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    b.setContentAreaFilled(false);
                    b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    b.setIconTextGap(30);
                    b.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            bActionPerformed(evt);
                        }
                    });

                    b1.setForeground(new java.awt.Color(240, 240, 240));
                    b1.setText("No");
                    b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    b1.setContentAreaFilled(false);
                    b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    b1.setIconTextGap(30);
                    b1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            b1ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout mensajeLayout = new javax.swing.GroupLayout(mensaje);
                    mensaje.setLayout(mensajeLayout);
                    mensajeLayout.setHorizontalGroup(
                        mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mensajeLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(men)
                            .addGap(46, 46, 46)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    mensajeLayout.setVerticalGroup(
                        mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mensajeLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(mensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(men)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(17, Short.MAX_VALUE))
                    );

                    lblIDPREVENTA.setText("jLabel13");

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6)
                        .addComponent(mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(26, 26, 26)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80)
                                    .addComponent(lblCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblIDPREVENTA))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblIDPREVENTA)
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel12)))
                                .addComponent(lblCPT))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 2, Short.MAX_VALUE)
                            .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ConsultorioExt.jTabbedPane1.setSelectedIndex(1);
        this.dispose();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblMant.setText("E");
        mensaje.setVisible(true);
        mensaje.setBackground(new Color(255,91,70)); 
        men.setText("Desea Eliminar este registro?");
        b.setText("Si");
        b.setVisible(true);
        b1.setVisible(true);
        tge=8;
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbPatologiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologiasMouseClicked

    }//GEN-LAST:event_tbPatologiasMouseClicked

    private void tbPatologiasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologiasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologiasMousePressed

    private void tbPatologiasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPatologiasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologiasKeyPressed

    private void tbPatologias1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologias1MouseClicked
    
        int fila=tbPatologias1.getSelectedRow();
        if(evt.getClickCount()==1){
            lblIDPREVENTA.setText(String.valueOf(tbPatologias1.getValueAt(fila, 0)));////////////
            btnEliminar.setEnabled(true);
        }

    }//GEN-LAST:event_tbPatologias1MouseClicked

    private void tbPatologias1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPatologias1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologias1MousePressed

    private void tbPatologias1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPatologias1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPatologias1KeyPressed

    private void txtCIE11CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCIE11CaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCIE11CaretUpdate

    private void btnBuscarCIE11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCIE11MouseClicked

    }//GEN-LAST:event_btnBuscarCIE11MouseClicked

    private void btnBuscarCIE11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCIE11ActionPerformed
        lblMant.setText("I");
        nomenclaturas.setVisible(true);
    }//GEN-LAST:event_btnBuscarCIE11ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        jTabbedPane3.setSelectedIndex(1);
        BuscarN();

        if (tb_Grupo4.getRowCount() == 0){
            jTabbedPane3.setSelectedIndex(2);
        }
        if (txtBuscar2.getText().length()==0){
            jTabbedPane3.setSelectedIndex(0);
        }

    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();

        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupo4.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupo4.requestFocus();

        }
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T4MouseClicked

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
        int fila=tb_Grupo4.getSelectedRow();
        if(evt.getClickCount()==2){
            nomenclaturas.dispose();
            lblCPT.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 5)));////////////
            GuardarPreventa();
            Guardar();
        }
        
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo4.getSelectedRow();
            nomenclaturas.dispose();
            lblCPT.setText(String.valueOf(tb_Grupo4.getValueAt(fila, 5)));////////////
  
        }
    }//GEN-LAST:event_tb_Grupo4KeyPressed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        if (tge==3 || tge==1|| tge==9){
            mensaje.setVisible(false);
        }

//      if (lblMant.getText().equals("U")){
//            Guardar();
//
//            btneditar.setEnabled(false);
//            tge=9;
//
//        }
        if (lblMant.getText().equals("E")){
            GuardarPreventa();

        }
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        mensaje.setVisible(false);
    }//GEN-LAST:event_b1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel T4;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    public static javax.swing.JButton btnBuscarCIE11;
    public static javax.swing.JButton btnEliminar;
    public static javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    public static javax.swing.JButton btneditar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblCPT;
    public static javax.swing.JLabel lblID;
    public static javax.swing.JLabel lblIDO;
    public static javax.swing.JLabel lblIDPREVENTA;
    public static javax.swing.JLabel lblMant;
    private javax.swing.JLabel lblMant1;
    public static javax.swing.JLabel lblid_hc;
    private javax.swing.JLabel men;
    private javax.swing.JPanel mensaje;
    private javax.swing.JDialog nomenclaturas;
    public static javax.swing.JTable tbPatologias;
    public static javax.swing.JTable tbPatologias1;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTextField txtBuscar2;
    public static javax.swing.JTextField txtCIE11;
    public static javax.swing.JLabel var;
    // End of variables declaration//GEN-END:variables
}
