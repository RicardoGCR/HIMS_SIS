/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_NuevaVenta;
import servicios.Conexion;
import vista.admisionCentral.FrmNuevaHistoriaC;

/**
 *
 * @author MYS1
 */
public class Caja_Pagos extends javax.swing.JFrame {
DefaultTableModel m;
double totales;
byte tg;
ResultSet r;
Conexion c=new Conexion();
Connection conexion=c.conectar();
Caja_NuevaVenta cnn = new Caja_NuevaVenta();

    /**
     * Creates new form Caja_Pagos
     */
    public Caja_Pagos() {
        initComponents();
       
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         jTabbedPane1.setSelectedIndex(1);
         BHC.setLocationRelativeTo(null);//en el centro
         BHC.getContentPane().setBackground(Color.WHITE);
      
         Jerarquias.setLocationRelativeTo(null);//en el centro
         Jerarquias.getContentPane().setBackground(Color.WHITE);
         nomenclaturas.setLocationRelativeTo(null);//en el centro
         nomenclaturas.getContentPane().setBackground(Color.WHITE);
          
        
             
             quitar.setUndecorated(true);
             quitar.setLocationRelativeTo(null);//en el centro
         
         
         
         ///NO MOSTRAR
         //////////////////////////
          separar.setVisible(false);
          bu.setVisible(false);
          Nomen2.setVisible(false);
          b4.setVisible(false);
          jPanel20.setVisible(false);
          jScrollPane4.setVisible(false);
          tb_Grupo2.setVisible(false);
          b5.setVisible(false);
          //////////////////////////
          
               
         LISTAR();
         LISTARN();
         formatoj();
         if (txtBuscar.getText() == " "){
             jTabbedPane2.setSelectedIndex(0);
        }
   
       this.cbxtipo.setModel(tipo());
      

    }
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/iconos/Caja fuerte-96.png"));


        return retValue;
    }
    
    public DefaultComboBoxModel tipo(){
       DefaultComboBoxModel  listmodel = new DefaultComboBoxModel ();        
       String   sql = null;
       ResultSet rs = null;
       Statement  st = null;   
        try {
              st = conexion.createStatement();
              r = st.executeQuery ("EXEC Caja_tipoDoc"); 
//              listmodel.addElement("Seleccionar...");
            while( r.next() ){
                listmodel.addElement( r.getString( "tipo_documento" ) );                
             }
            r.close();
        } catch (SQLException ex) {            
            System.err.println( "Error consulta :" + ex.getMessage() );
        }        
        return listmodel;
    }

    public void LISTAR(){
    try {
             String titulos[]={"Codigo","Forma de Pago","Descripcion","Relacion","Nivel","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Jerarquia_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo3.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo3.setRowSorter(elQueOrdena);
            this.tb_Grupo3.setModel(m);
            formatoj();
            
    } catch (Exception e) {
    }
}  
   
    public void LISTARN(){
    try {
             String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_NomenclaturaVenta";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);
         //   formatoj();
            
    } catch (Exception e) {
    }
}  
    
    public void BuscarHC(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Nº H.C.","Paciente","Direccion","DNI","Sexo","Fecha","Edad",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_BuscarHC ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
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
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formato();
         
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
    public void BuscarN(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo4.setModel(new DefaultTableModel());
             String titulos[]={"Nomeclatura","Descripcion","Precio","Forma de Pago","Decripcion Forma Pago",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Caja_NuevaVenta obj=new Caja_NuevaVenta();
                    consulta="exec Caja_NomenclaturaVentaBUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
            fila[4]=r.getString(5);
            fila[5]=r.getString(6);
       


                m.addRow(fila);
                c++;
            }
            tb_Grupo4.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo4.setRowSorter(elQueOrdena);
            this.tb_Grupo4.setModel(m);

            //formato();
         
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
    public void formato(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(220);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(480);
    tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(400);
    tb_Grupo.getColumnModel().getColumn(3).setPreferredWidth(150);
    tb_Grupo.getColumnModel().getColumn(4).setPreferredWidth(70);
    tb_Grupo.getColumnModel().getColumn(6).setPreferredWidth(70);

    tb_Grupo.getColumnModel().getColumn(5).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(5).setMaxWidth(0);
    tb_Grupo.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(7).setMaxWidth(0);
    }
    
    public void formatoj(){
    tb_Grupo3.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo3.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo3.getColumnModel().getColumn(2).setPreferredWidth(500);
    tb_Grupo3.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo3.getColumnModel().getColumn(4).setPreferredWidth(20);
    tb_Grupo3.getColumnModel().getColumn(5).setPreferredWidth(100);

    }
    
    public void Guardar(){

        if((txtfp.getText().equals("")) ||  txtcodigo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_NuevaVenta cno1 = new Caja_NuevaVenta();
                cno1.setCod_jerar_forma_pago(txtcodigo.getText());//
                cno1.setCod_tipo_documento(cno1.codTipo(cbxtipo.getSelectedItem().toString()));
        
//                cno1.setNom_forma_pago(forma.getText());//
//                cno1.setDescri_forma_pago(des.getText());//
//                cno1.setRelacion_forma_pago(codpago.getText());//
//                cno1.setNivel_forma_pago("2");//
//                cno1.setTipo_estado_pago("P(Pendiente)");//
//                cno1.setNom_usu(lblusu.getText());//
//                    if(cno1.NuevaJerarquia()==true){
//                           JOptionPane.showMessageDialog(this, "Datos Guardados");
//                           tg=2;
//                           btnguardar.setEnabled(false);
//                           btneditar.setEnabled(true);
//                           forma.setEnabled(false);
//                           des.setEnabled(false);
//                           
//                       } else {
//                           JOptionPane.showMessageDialog(this, "Error al guardar");
//                       }
            }
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

        BHC = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jPanel6 = new javax.swing.JPanel();
            jLabel16 = new javax.swing.JLabel();
            jLabel17 = new javax.swing.JLabel();
            jPanel9 = new javax.swing.JPanel();
            btnNuevo1 = new javax.swing.JButton();
            jLabel18 = new javax.swing.JLabel();
            Jerarquias = new javax.swing.JDialog();
            jPanel10 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            btnbuscar2 = new javax.swing.JButton();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane5 = new javax.swing.JScrollPane();
            tb_Grupo3 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                nomenclaturas = new javax.swing.JDialog();
                jPanel11 = new javax.swing.JPanel();
                jLabel21 = new javax.swing.JLabel();
                btnbuscar3 = new javax.swing.JButton();
                txtBuscar2 = new javax.swing.JTextField();
                jPanel12 = new javax.swing.JPanel();
                jTabbedPane3 = new javax.swing.JTabbedPane();
                jPanel13 = new javax.swing.JPanel();
                jLabel22 = new javax.swing.JLabel();
                jPanel14 = new javax.swing.JPanel();
                jScrollPane6 = new javax.swing.JScrollPane();
                tb_Grupo4 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel15 = new javax.swing.JPanel();
                    jLabel23 = new javax.swing.JLabel();
                    jLabel24 = new javax.swing.JLabel();
                    jPanel16 = new javax.swing.JPanel();
                    btnNuevo2 = new javax.swing.JButton();
                    jLabel25 = new javax.swing.JLabel();
                    quitar = new javax.swing.JDialog();
                    jPanel17 = new javax.swing.JPanel();
                    jLabel27 = new javax.swing.JLabel();
                    jLabel28 = new javax.swing.JLabel();
                    btnNuevo3 = new javax.swing.JButton();
                    btnNuevo4 = new javax.swing.JButton();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
                    btneditar = new javax.swing.JButton();
                    btnguardar = new javax.swing.JButton();
                    btneliminar = new javax.swing.JButton();
                    btnbuscar = new javax.swing.JButton();
                    lblusu = new javax.swing.JLabel();
                    btneliminar1 = new javax.swing.JButton();
                    btnbuscar4 = new javax.swing.JButton();
                    jTabbedPane1 = new javax.swing.JTabbedPane();
                    jPanel2 = new javax.swing.JPanel();
                    jScrollPane3 = new javax.swing.JScrollPane();
                    tb_Grupo1 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        buscartodo = new javax.swing.JTextField();
                        jLabel8 = new javax.swing.JLabel();
                        jPanel3 = new javax.swing.JPanel();
                        jLabel2 = new javax.swing.JLabel();
                        jLabel3 = new javax.swing.JLabel();
                        jLabel4 = new javax.swing.JLabel();
                        jLabel5 = new javax.swing.JLabel();
                        txtcodigo = new javax.swing.JTextField();
                        txthc = new javax.swing.JTextField();
                        txtfp = new javax.swing.JTextField();
                        b = new javax.swing.JButton();
                        fp = new javax.swing.JLabel();
                        jLabel51 = new javax.swing.JLabel();
                        nom = new javax.swing.JLabel();
                        b2 = new javax.swing.JButton();
                        jLabel6 = new javax.swing.JLabel();
                        jLabel7 = new javax.swing.JLabel();
                        txtape = new javax.swing.JTextField();
                        jLabel10 = new javax.swing.JLabel();
                        jLabel11 = new javax.swing.JLabel();
                        jLabel12 = new javax.swing.JLabel();
                        txtdir = new javax.swing.JTextField();
                        txtdni = new javax.swing.JTextField();
                        txtedad = new javax.swing.JTextField();
                        separar = new javax.swing.JLabel();
                        jScrollPane4 = new javax.swing.JScrollPane();
                        tb_Grupo2 = new javax.swing.JTable(){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false; //Disallow the editing of any cell
                            }};
                            jLabel14 = new javax.swing.JLabel();
                            sexo = new javax.swing.JTextField();
                            Nomen2 = new javax.swing.JTextField();
                            b4 = new javax.swing.JButton();
                            bu = new javax.swing.JLabel();
                            lblcodigo = new javax.swing.JLabel();
                            lblhc = new javax.swing.JLabel();
                            cbxtipo = new javax.swing.JComboBox();
                            b5 = new javax.swing.JButton();
                            jPanel20 = new javax.swing.JPanel();
                            jLabel26 = new javax.swing.JLabel();
                            total = new javax.swing.JTextField();

                            BHC.setAlwaysOnTop(true);
                            BHC.setMinimumSize(new java.awt.Dimension(749, 338));
                            BHC.setResizable(false);
                            BHC.getContentPane().setLayout(null);

                            jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                            jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                            jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel19.setText("Historia Clínica");

                            btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
                            btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                            btnbuscar1.setMnemonic('N');
                            btnbuscar1.setToolTipText("");
                            btnbuscar1.setContentAreaFilled(false);
                            btnbuscar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnbuscar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnbuscar1.setIconTextGap(30);
                            btnbuscar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnbuscar1ActionPerformed(evt);
                                }
                            });

                            txtBuscar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                            txtBuscar.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    txtBuscarCaretUpdate(evt);
                                }
                            });
                            txtBuscar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txtBuscarActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                            jPanel7.setLayout(jPanel7Layout);
                            jPanel7Layout.setHorizontalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(500, Short.MAX_VALUE))
                            );
                            jPanel7Layout.setVerticalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(btnbuscar1)))
                                    .addGap(408, 408, 408))
                            );

                            BHC.getContentPane().add(jPanel7);
                            jPanel7.setBounds(0, 0, 780, 104);

                            jPanel8.setBackground(new java.awt.Color(255, 255, 255));

                            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                            jPanel8.setLayout(jPanel8Layout);
                            jPanel8Layout.setHorizontalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 750, Short.MAX_VALUE)
                            );
                            jPanel8Layout.setVerticalGroup(
                                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 50, Short.MAX_VALUE)
                            );

                            BHC.getContentPane().add(jPanel8);
                            jPanel8.setBounds(0, 312, 750, 50);

                            jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                            jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel9.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Documento-100.png"))); // NOI18N
                            jLabel9.setText("Busqueda de Historias ");

                            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                            jPanel4.setLayout(jPanel4Layout);
                            jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(134, 134, 134)
                                    .addComponent(jLabel9)
                                    .addContainerGap(163, Short.MAX_VALUE))
                            );
                            jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addComponent(jLabel9)
                                    .addContainerGap(40, Short.MAX_VALUE))
                            );

                            jTabbedPane2.addTab("tab2", jPanel4);

                            jPanel5.setBackground(new java.awt.Color(255, 255, 255));

                            tb_Grupo.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo.setRowHeight(25);
                            tb_Grupo.setSelectionBackground(new java.awt.Color(0, 153, 153));
                            tb_Grupo.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_GrupoMouseClicked(evt);
                                }
                            });
                            tb_Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_GrupoKeyPressed(evt);
                                }
                            });
                            jScrollPane2.setViewportView(tb_Grupo);

                            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                            jPanel5.setLayout(jPanel5Layout);
                            jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 754, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                            );
                            jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 202, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                            );

                            jTabbedPane2.addTab("tab2", jPanel5);

                            jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel16.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel16.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel16.setText("No se hallaron coincidencias");

                            jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                            jLabel17.setForeground(new java.awt.Color(0, 153, 153));
                            jLabel17.setText(":(");

                            jPanel9.setBackground(new java.awt.Color(153, 153, 153));

                            btnNuevo1.setBackground(new java.awt.Color(204, 204, 204));
                            btnNuevo1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                            btnNuevo1.setForeground(new java.awt.Color(102, 102, 102));
                            btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-50.png"))); // NOI18N
                            btnNuevo1.setMnemonic('N');
                            btnNuevo1.setContentAreaFilled(false);
                            btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnNuevo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                            btnNuevo1.setIconTextGap(30);
                            btnNuevo1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNuevo1ActionPerformed(evt);
                                }
                            });

                            jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel18.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel18.setText("Nueva Historia Clinica");

                            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                            jPanel9.setLayout(jPanel9Layout);
                            jPanel9Layout.setHorizontalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(btnNuevo1)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addContainerGap(45, Short.MAX_VALUE)
                                    .addComponent(jLabel18)
                                    .addGap(44, 44, 44))
                            );
                            jPanel9Layout.setVerticalGroup(
                                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(btnNuevo1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(40, Short.MAX_VALUE))
                            );

                            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                            jPanel6.setLayout(jPanel6Layout);
                            jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            );
                            jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel17))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(77, 77, 77)
                                            .addComponent(jLabel16))
                                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jTabbedPane2.addTab("tab3", jPanel6);

                            BHC.getContentPane().add(jTabbedPane2);
                            jTabbedPane2.setBounds(0, 108, 749, 230);

                            Jerarquias.setAlwaysOnTop(true);
                            Jerarquias.setMinimumSize(new java.awt.Dimension(628, 300));
                            Jerarquias.setResizable(false);

                            jPanel10.setBackground(new java.awt.Color(0, 153, 153));
                            jPanel10.setMinimumSize(new java.awt.Dimension(310, 441));

                            jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel20.setText("Forma de Pago");

                            btnbuscar2.setForeground(new java.awt.Color(240, 240, 240));
                            btnbuscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                            btnbuscar2.setMnemonic('N');
                            btnbuscar2.setToolTipText("");
                            btnbuscar2.setContentAreaFilled(false);
                            btnbuscar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnbuscar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnbuscar2.setIconTextGap(30);
                            btnbuscar2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnbuscar2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnbuscar2ActionPerformed(evt);
                                }
                            });

                            txtBuscar1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
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

                            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                            jPanel10.setLayout(jPanel10Layout);
                            jPanel10Layout.setHorizontalGroup(
                                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel20)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            jPanel10Layout.setVerticalGroup(
                                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(btnbuscar2)))
                                    .addGap(408, 408, 408))
                            );

                            tb_Grupo3.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo3.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo3.setRowHeight(25);
                            tb_Grupo3.setSelectionBackground(new java.awt.Color(0, 153, 153));
                            tb_Grupo3.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo3MouseClicked(evt);
                                }
                            });
                            tb_Grupo3.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo3KeyPressed(evt);
                                }
                            });
                            jScrollPane5.setViewportView(tb_Grupo3);

                            javax.swing.GroupLayout JerarquiasLayout = new javax.swing.GroupLayout(Jerarquias.getContentPane());
                            Jerarquias.getContentPane().setLayout(JerarquiasLayout);
                            JerarquiasLayout.setHorizontalGroup(
                                JerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JerarquiasLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            );
                            JerarquiasLayout.setVerticalGroup(
                                JerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JerarquiasLayout.createSequentialGroup()
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addContainerGap())
                            );

                            nomenclaturas.setAlwaysOnTop(true);
                            nomenclaturas.setMinimumSize(new java.awt.Dimension(749, 338));
                            nomenclaturas.setResizable(false);
                            nomenclaturas.getContentPane().setLayout(null);

                            jPanel11.setBackground(new java.awt.Color(0, 153, 153));
                            jPanel11.setMinimumSize(new java.awt.Dimension(310, 441));

                            jLabel21.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel21.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel21.setText("Nomenclaturas");

                            btnbuscar3.setForeground(new java.awt.Color(240, 240, 240));
                            btnbuscar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
                            btnbuscar3.setMnemonic('N');
                            btnbuscar3.setToolTipText("");
                            btnbuscar3.setContentAreaFilled(false);
                            btnbuscar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnbuscar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnbuscar3.setIconTextGap(30);
                            btnbuscar3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnbuscar3.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnbuscar3ActionPerformed(evt);
                                }
                            });

                            txtBuscar2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
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

                            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                            jPanel11.setLayout(jPanel11Layout);
                            jPanel11Layout.setHorizontalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(490, Short.MAX_VALUE))
                            );
                            jPanel11Layout.setVerticalGroup(
                                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(btnbuscar3)))
                                    .addGap(408, 408, 408))
                            );

                            nomenclaturas.getContentPane().add(jPanel11);
                            jPanel11.setBounds(0, 0, 770, 104);

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
                            jLabel22.setText("Busqueda de Nomenclaturas ");

                            javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                            jPanel13.setLayout(jPanel13Layout);
                            jPanel13Layout.setHorizontalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(95, 95, 95)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(71, Short.MAX_VALUE))
                            );
                            jPanel13Layout.setVerticalGroup(
                                jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(jLabel22)
                                    .addContainerGap(74, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab2", jPanel13);

                            jPanel14.setBackground(new java.awt.Color(255, 255, 255));

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
                            tb_Grupo4.setSelectionBackground(new java.awt.Color(0, 153, 153));
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
                            jScrollPane6.setViewportView(tb_Grupo4);

                            javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                            jPanel14.setLayout(jPanel14Layout);
                            jPanel14Layout.setHorizontalGroup(
                                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 754, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                            );
                            jPanel14Layout.setVerticalGroup(
                                jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 229, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab2", jPanel14);

                            jPanel15.setBackground(new java.awt.Color(255, 255, 255));

                            jLabel23.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel23.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel23.setText("No se hallaron coincidencias");

                            jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 100)); // NOI18N
                            jLabel24.setForeground(new java.awt.Color(0, 153, 153));
                            jLabel24.setText(":(");

                            jPanel16.setBackground(new java.awt.Color(153, 153, 153));

                            btnNuevo2.setBackground(new java.awt.Color(204, 204, 204));
                            btnNuevo2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                            btnNuevo2.setForeground(new java.awt.Color(102, 102, 102));
                            btnNuevo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Agregar propiedad-50.png"))); // NOI18N
                            btnNuevo2.setMnemonic('N');
                            btnNuevo2.setContentAreaFilled(false);
                            btnNuevo2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnNuevo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                            btnNuevo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                            btnNuevo2.setIconTextGap(30);
                            btnNuevo2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnNuevo2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNuevo2ActionPerformed(evt);
                                }
                            });

                            jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            jLabel25.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel25.setText("Nueva Nomenclatura");

                            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
                            jPanel16.setLayout(jPanel16Layout);
                            jPanel16Layout.setHorizontalGroup(
                                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(btnNuevo2)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                    .addContainerGap(45, Short.MAX_VALUE)
                                    .addComponent(jLabel25)
                                    .addGap(44, 44, 44))
                            );
                            jPanel16Layout.setVerticalGroup(
                                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(btnNuevo2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(40, Short.MAX_VALUE))
                            );

                            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                            jPanel15.setLayout(jPanel15Layout);
                            jPanel15Layout.setHorizontalGroup(
                                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            );
                            jPanel15Layout.setVerticalGroup(
                                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel24))
                                        .addGroup(jPanel15Layout.createSequentialGroup()
                                            .addGap(77, 77, 77)
                                            .addComponent(jLabel23))
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            jTabbedPane3.addTab("tab3", jPanel15);

                            nomenclaturas.getContentPane().add(jTabbedPane3);
                            jTabbedPane3.setBounds(0, 108, 749, 230);

                            quitar.setAlwaysOnTop(true);
                            quitar.setIconImage(null);
                            quitar.setMinimumSize(new java.awt.Dimension(357, 156));
                            quitar.setModal(true);
                            quitar.setResizable(false);

                            jPanel17.setBackground(new java.awt.Color(255, 51, 51));

                            jLabel27.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
                            jLabel27.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Message-96.png"))); // NOI18N
                            jLabel27.setText("Eliminar ");

                            jLabel28.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                            jLabel28.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel28.setText("Desea eliminar este detalle?");

                            btnNuevo3.setForeground(new java.awt.Color(240, 240, 240));
                            btnNuevo3.setText("Si");
                            btnNuevo3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                            btnNuevo3.setContentAreaFilled(false);
                            btnNuevo3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnNuevo3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnNuevo3.setIconTextGap(30);
                            btnNuevo3.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNuevo3ActionPerformed(evt);
                                }
                            });

                            btnNuevo4.setBackground(new java.awt.Color(153, 153, 153));
                            btnNuevo4.setForeground(new java.awt.Color(240, 240, 240));
                            btnNuevo4.setText("No");
                            btnNuevo4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                            btnNuevo4.setContentAreaFilled(false);
                            btnNuevo4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnNuevo4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnNuevo4.setIconTextGap(30);
                            btnNuevo4.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnNuevo4ActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
                            jPanel17.setLayout(jPanel17Layout);
                            jPanel17Layout.setHorizontalGroup(
                                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                            .addGap(0, 48, Short.MAX_VALUE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(104, 104, 104))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                            .addComponent(jLabel28)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevo4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())))
                            );
                            jPanel17Layout.setVerticalGroup(
                                jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel17Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnNuevo3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnNuevo4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel17Layout.createSequentialGroup()
                                            .addComponent(jLabel27)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel28)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );

                            javax.swing.GroupLayout quitarLayout = new javax.swing.GroupLayout(quitar.getContentPane());
                            quitar.getContentPane().setLayout(quitarLayout);
                            quitarLayout.setHorizontalGroup(
                                quitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            );
                            quitarLayout.setVerticalGroup(
                                quitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            );

                            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                            setIconImage(getIconImage());

                            jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                            jLabel1.setText("Venta");

                            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                            btnNuevo.setMnemonic('N');
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

                            btneditar.setForeground(new java.awt.Color(240, 240, 240));
                            btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Editar-32.png"))); // NOI18N
                            btneditar.setMnemonic('N');
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

                            btnguardar.setForeground(new java.awt.Color(240, 240, 240));
                            btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Guardar-32.png"))); // NOI18N
                            btnguardar.setMnemonic('N');
                            btnguardar.setContentAreaFilled(false);
                            btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnguardar.setIconTextGap(30);
                            btnguardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnguardar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnguardarActionPerformed(evt);
                                }
                            });

                            btneliminar.setForeground(new java.awt.Color(240, 240, 240));
                            btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Basura-32.png"))); // NOI18N
                            btneliminar.setMnemonic('N');
                            btneliminar.setContentAreaFilled(false);
                            btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btneliminar.setIconTextGap(30);
                            btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btneliminar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btneliminarActionPerformed(evt);
                                }
                            });

                            btnbuscar.setForeground(new java.awt.Color(240, 240, 240));
                            btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-32.png"))); // NOI18N
                            btnbuscar.setMnemonic('N');
                            btnbuscar.setContentAreaFilled(false);
                            btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnbuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnbuscar.setIconTextGap(30);
                            btnbuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnbuscar.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnbuscarActionPerformed(evt);
                                }
                            });

                            lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                            lblusu.setForeground(new java.awt.Color(255, 255, 255));
                            lblusu.setText("Ricardo Chumpitaz");

                            btneliminar1.setForeground(new java.awt.Color(240, 240, 240));
                            btneliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/User-32.png"))); // NOI18N
                            btneliminar1.setMnemonic('N');
                            btneliminar1.setToolTipText("");
                            btneliminar1.setContentAreaFilled(false);
                            btneliminar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btneliminar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btneliminar1.setIconTextGap(30);
                            btneliminar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btneliminar1.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btneliminar1ActionPerformed(evt);
                                }
                            });

                            btnbuscar4.setForeground(new java.awt.Color(240, 240, 240));
                            btnbuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Print-32 (2).png"))); // NOI18N
                            btnbuscar4.setMnemonic('N');
                            btnbuscar4.setContentAreaFilled(false);
                            btnbuscar4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            btnbuscar4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            btnbuscar4.setIconTextGap(30);
                            btnbuscar4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            btnbuscar4.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    btnbuscar4ActionPerformed(evt);
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
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblusu)
                                            .addGap(34, 34, 34))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            );
                            jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(lblusu)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneliminar1)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btneliminar)
                                                .addComponent(btnbuscar))
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(btnbuscar4))
                                    .addGap(552, 552, 552))
                            );

                            jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                            jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                            tb_Grupo1.setModel(new javax.swing.table.DefaultTableModel(
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
                            tb_Grupo1.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo1.setRowHeight(25);
                            tb_Grupo1.setSelectionBackground(new java.awt.Color(0, 153, 153));
                            tb_Grupo1.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo1MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo1MousePressed(evt);
                                }
                            });
                            tb_Grupo1.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo1KeyPressed(evt);
                                }
                            });
                            jScrollPane3.setViewportView(tb_Grupo1);

                            buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    buscartodoCaretUpdate(evt);
                                }
                            });

                            jLabel8.setText("Buscar");

                            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                            jPanel2.setLayout(jPanel2Layout);
                            jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            );
                            jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                            );

                            jTabbedPane1.addTab("Ventas de Hoy", jPanel2);

                            jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                            jLabel2.setText("Forma de Pago");

                            jLabel3.setText("Tipo de Documento");

                            jLabel4.setText("Historia Clinica");

                            jLabel5.setText("Numero de Documento");

                            txtcodigo.setEnabled(false);

                            txthc.setEnabled(false);
                            txthc.addCaretListener(new javax.swing.event.CaretListener() {
                                public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                    txthcCaretUpdate(evt);
                                }
                            });
                            txthc.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    txthcMouseClicked(evt);
                                }
                            });
                            txthc.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    txthcActionPerformed(evt);
                                }
                            });

                            txtfp.setEnabled(false);
                            txtfp.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    txtfpMouseClicked(evt);
                                }
                            });

                            b.setBackground(new java.awt.Color(255, 255, 255));
                            b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                            b.setEnabled(false);
                            b.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    bActionPerformed(evt);
                                }
                            });

                            fp.setBackground(new java.awt.Color(255, 255, 255));
                            fp.setForeground(new java.awt.Color(255, 255, 255));
                            fp.setText("jLabel8");

                            jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                            jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                            jLabel51.setText("DATOS DE LA VENTA________________________________________________________________________________________________________________________________________");

                            nom.setBackground(new java.awt.Color(255, 255, 255));
                            nom.setForeground(new java.awt.Color(255, 255, 255));
                            nom.setText("jLabel8");

                            b2.setBackground(new java.awt.Color(255, 255, 255));
                            b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                            b2.setEnabled(false);
                            b2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    b2ActionPerformed(evt);
                                }
                            });

                            jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                            jLabel6.setForeground(new java.awt.Color(153, 153, 153));
                            jLabel6.setText("PACIENTE __________________________________________________________________________________________________________________________________________________");

                            jLabel7.setText("Apellidos y Nombres");

                            txtape.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            txtape.setForeground(new java.awt.Color(102, 102, 102));
                            txtape.setBorder(null);
                            txtape.setEnabled(false);

                            jLabel10.setText("Edad");

                            jLabel11.setText("DNI");

                            jLabel12.setText("Dirección ");

                            txtdir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            txtdir.setForeground(new java.awt.Color(102, 102, 102));
                            txtdir.setBorder(null);
                            txtdir.setEnabled(false);

                            txtdni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            txtdni.setForeground(new java.awt.Color(102, 102, 102));
                            txtdni.setBorder(null);
                            txtdni.setEnabled(false);

                            txtedad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            txtedad.setForeground(new java.awt.Color(102, 102, 102));
                            txtedad.setBorder(null);
                            txtedad.setEnabled(false);

                            separar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                            separar.setForeground(new java.awt.Color(153, 153, 153));
                            separar.setText("NOMENCLATURAS _________________________________________________________________________________________________________________________________________");

                            tb_Grupo2.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][] {

                                },
                                new String [] {
                                    "Nomenclatura", "Descripcion", "Precio", "Forma de Pago"
                                }
                            ));
                            tb_Grupo2.setGridColor(new java.awt.Color(255, 255, 255));
                            tb_Grupo2.setRowHeight(25);
                            tb_Grupo2.setSelectionBackground(new java.awt.Color(0, 153, 153));
                            tb_Grupo2.addMouseListener(new java.awt.event.MouseAdapter() {
                                public void mouseClicked(java.awt.event.MouseEvent evt) {
                                    tb_Grupo2MouseClicked(evt);
                                }
                                public void mousePressed(java.awt.event.MouseEvent evt) {
                                    tb_Grupo2MousePressed(evt);
                                }
                            });
                            tb_Grupo2.addKeyListener(new java.awt.event.KeyAdapter() {
                                public void keyPressed(java.awt.event.KeyEvent evt) {
                                    tb_Grupo2KeyPressed(evt);
                                }
                            });
                            jScrollPane4.setViewportView(tb_Grupo2);

                            jLabel14.setText("Sexo ");

                            sexo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                            sexo.setForeground(new java.awt.Color(102, 102, 102));
                            sexo.setBorder(null);
                            sexo.setEnabled(false);

                            Nomen2.setEnabled(false);

                            b4.setBackground(new java.awt.Color(255, 255, 255));
                            b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                            b4.setEnabled(false);
                            b4.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    b4ActionPerformed(evt);
                                }
                            });

                            bu.setText("Buscar");

                            lblcodigo.setText("Numero de Documento");

                            lblhc.setText("Numero de Documento");

                            cbxtipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                            cbxtipo.setEnabled(false);

                            b5.setForeground(new java.awt.Color(255, 51, 51));
                            b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Delete Property-32.png"))); // NOI18N
                            b5.setMnemonic('N');
                            b5.setText("Quitar");
                            b5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
                            b5.setContentAreaFilled(false);
                            b5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                            b5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                            b5.setIconTextGap(30);
                            b5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                            b5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                            b5.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    b5ActionPerformed(evt);
                                }
                            });

                            jPanel20.setBackground(new java.awt.Color(255, 255, 255));
                            jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

                            jLabel26.setText("  SubTotal  ");

                            total.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
                            total.setForeground(new java.awt.Color(51, 51, 51));
                            total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                            total.setBorder(null);
                            total.setEnabled(false);

                            javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                            jPanel20.setLayout(jPanel20Layout);
                            jPanel20Layout.setHorizontalGroup(
                                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel20Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel26)
                                    .addContainerGap())
                            );
                            jPanel20Layout.setVerticalGroup(
                                jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel20Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                            );

                            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                            jPanel3.setLayout(jPanel3Layout);
                            jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel51)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(bu))
                                                    .addGap(14, 14, 14))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(33, 33, 33)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel12)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(jLabel11)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(fp)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel10)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGap(18, 18, 18)
                                                            .addComponent(jLabel14)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(nom))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(Nomen2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cbxtipo, javax.swing.GroupLayout.Alignment.LEADING, 0, 160, Short.MAX_VALUE)
                                                        .addComponent(txtfp, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(72, 72, 72)
                                                    .addComponent(jLabel5)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblcodigo)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lblhc))))
                                        .addComponent(jLabel6)
                                        .addComponent(separar)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            );
                            jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel51)
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel2)
                                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtfp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)
                                            .addComponent(lblcodigo)
                                            .addComponent(lblhc))
                                        .addComponent(b))
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(txthc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(b2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel12)
                                            .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(fp)
                                                .addComponent(nom)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel11)
                                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10)
                                                .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14)
                                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addComponent(separar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(b4)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(Nomen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(bu)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(26, 26, 26)
                                                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addContainerGap())
                            );

                            jTabbedPane1.addTab("Nueva Venta", jPanel3);

                            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                            getContentPane().setLayout(layout);
                            layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
                                    .addContainerGap())
                            );
                            layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                                    .addGap(16, 16, 16))
                            );

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        lblcodigo.setText(cnn.id());

        tg=1;
    
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        cbxtipo.setEnabled(true);
        b2.setEnabled(true);
        b.setEnabled(true);
        b4.setEnabled(true);
        txthc.setEnabled(true);
        txthc.setEditable(false);
        Nomen2.setEnabled(true);
        Nomen2.setEditable(false);
        txtfp.setEnabled(true);
        txtfp.setEditable(false);
        total.setEnabled(true);
        total.setEditable(false);
    
     
        jTabbedPane1.setSelectedIndex(1);       
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
      
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        Guardar();    

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
    
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
     

    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
      
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        BHC.setVisible(true);
        txtBuscar.setText("");
    }//GEN-LAST:event_b2ActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        Jerarquias.setVisible(true);
        txtBuscar1.setText("");
    }//GEN-LAST:event_bActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked

        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==1){
            
            int filaseleccionada;

     try{
     
         filaseleccionada= tb_Grupo2.getSelectedRow();
            if (filaseleccionada!=-1){
         
            b5.setEnabled(true);

         }
           } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
//            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
 
        }
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MousePressed
    
    }//GEN-LAST:event_tb_Grupo2MousePressed

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
      

    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
          nomenclaturas.setVisible(true);
          txtBuscar2.setText("");
    }//GEN-LAST:event_b4ActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed

    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        jTabbedPane2.setSelectedIndex(1);
        BuscarHC();
        if (tb_Grupo.getRowCount() == 0){
            jTabbedPane2.setSelectedIndex(2);
            }

        
           
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
       int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            BHC.dispose();
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);   
            txtdni.setEnabled(true);
            sexo.setEnabled(true);
            txtedad.setEnabled(true);
            
            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);   
            txtdni.setEditable(false);
            sexo.setEditable(false);
            txtedad.setEditable(false);
            
            txthc.setText(" "+String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            txtdir.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));    
            txtdni.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));
            sexo.setText(String.valueOf(tb_Grupo.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tb_Grupo.getValueAt(fila, 6)));
            lblhc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 7))); 
            
          ////////////////////////
          separar.setVisible(true);
          bu.setVisible(true);
          Nomen2.setVisible(true);
          b4.setVisible(true);
          tb_Grupo2.setVisible(true);
          jScrollPane4.setVisible(true);
          tb_Grupo2.setVisible(true);
        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
         char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            BHC.dispose();
            txthc.setEnabled(true);
            txtape.setEnabled(true);
            txtdir.setEnabled(true);   
            txtdni.setEnabled(true);
            sexo.setEnabled(true);
            txtedad.setEnabled(true);
            
            txthc.setEditable(false);
            txtape.setEditable(false);
            txtdir.setEditable(false);   
            txtdni.setEditable(false);
            sexo.setEditable(false);
            txtedad.setEditable(false);
            
            txthc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtape.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            txtdir.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));    
            txtdni.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));
            sexo.setText(String.valueOf(tb_Grupo.getValueAt(fila, 4)));
            txtedad.setText(String.valueOf(tb_Grupo.getValueAt(fila, 6)));
            lblhc.setText(String.valueOf(tb_Grupo.getValueAt(fila, 7))); 
            
            
          ////////////////////////
          separar.setVisible(true);
          bu.setVisible(true);
          Nomen2.setVisible(true);
          b4.setVisible(true);
          jScrollPane4.setVisible(true);
          tb_Grupo2.setVisible(true);

        }
        
        
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
  BHC.dispose();
        FrmNuevaHistoriaC frmEmerList = new FrmNuevaHistoriaC();
        frmEmerList.setVisible(true);
        
//        String u=PrincipalMDI.lblUsu.getText();
//        frmEmerList.lblUsuUsuario.setText(u);
//        FrmListFormatoEmergencia.tbpReporteEmergencia.setSelectedIndex(1);       
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void txthcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthcMouseClicked
        BHC.setVisible(true);
        txtBuscar.setText("");
    }//GEN-LAST:event_txthcMouseClicked

    private void txthcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txthcCaretUpdate
       
    }//GEN-LAST:event_txthcCaretUpdate

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed

    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
//        BuscarRelacion();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo3MouseClicked
         int fila=tb_Grupo3.getSelectedRow();
        if(evt.getClickCount()==2){
            Jerarquias.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtfp.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
        }
    }//GEN-LAST:event_tb_Grupo3MouseClicked

    private void tb_Grupo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo3KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo3.getSelectedRow();
            Jerarquias.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            txtfp.setText(String.valueOf(tb_Grupo3.getValueAt(fila, 1)));
        }
    }//GEN-LAST:event_tb_Grupo3KeyPressed

    private void txthcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthcActionPerformed

    private void txtfpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfpMouseClicked
        Jerarquias.setVisible(true);
        txtBuscar1.setText("");
    }//GEN-LAST:event_txtfpMouseClicked

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
          jTabbedPane3.setSelectedIndex(1);
        BuscarN();
        if (tb_Grupo4.getRowCount() == 0){
            jTabbedPane3.setSelectedIndex(2);
    // Código a ejecutar cuando sea falso
}
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void tb_Grupo4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo4MouseClicked
        int fila=tb_Grupo4.getSelectedRow();
        if(evt.getClickCount()==2){
        nomenclaturas.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
//            codpago.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            
            
            
            //Paso al otro formulario
            int fsel=tb_Grupo4.getSelectedRow();
            try {
                String Nomeclatura,Descripcion,Precio,Forma_de_Pago;
//                double calcula=0.0,x=0.0,igv=0.0;
//                int cant;
                if(fsel==-1){
                  JOptionPane.showMessageDialog(null,"Seleccion");
                }else{
                    m=(DefaultTableModel)tb_Grupo4.getModel();
                    Nomeclatura=tb_Grupo4.getValueAt(fsel,0).toString();
                    Descripcion=tb_Grupo4.getValueAt(fsel,1).toString();
                    Precio=tb_Grupo4.getValueAt(fsel,2).toString();
                    Forma_de_Pago=tb_Grupo4.getValueAt(fsel,3).toString();
                
//                    cant=txtcant.getText();
//                    x=(Double.parseDouble(precio)*Integer.parseInt(cant));
//                    importe==String.valueOf(x);
                    
                    m=(DefaultTableModel) tb_Grupo2.getModel();
                    String filaelemento[]={Nomeclatura,Descripcion,Precio,Forma_de_Pago};
                    m.addRow(filaelemento);
                      suma();
                        jPanel20.setVisible(true);
                        b5.setVisible(true);
                      
                        b5.setEnabled(false);
                    
                }
                
            }
                
             catch (Exception e) {
            }
            }
    }//GEN-LAST:event_tb_Grupo4MouseClicked

    private void tb_Grupo4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo4KeyPressed
          char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo4.getSelectedRow();
            nomenclaturas.dispose();
//            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
//            codpago.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
            
            
            
            //Paso al otro formulario
            int fsel=tb_Grupo4.getSelectedRow();
            try {
                String Nomeclatura,Descripcion,Precio,Forma_de_Pago;
//                double calcula=0.0,x=0.0,igv=0.0;
                double tp;
//                int cant;
                if(fsel==-1){
                  JOptionPane.showMessageDialog(null,"Seleccion");
                }else{
                    m=(DefaultTableModel)tb_Grupo4.getModel();
                    Nomeclatura=tb_Grupo4.getValueAt(fsel,0).toString();
                    Descripcion=tb_Grupo4.getValueAt(fsel,1).toString();
                    Precio=tb_Grupo4.getValueAt(fsel,2).toString();
                    Forma_de_Pago=tb_Grupo4.getValueAt(fsel,3).toString();
        
//                    cant=txtcant.getText();
//                    x=(Double.parseDouble(precio)*Integer.parseInt(cant));
//                    importe==String.valueOf(x);
                    
                    m=(DefaultTableModel) tb_Grupo2.getModel();
                    String filaelemento[]={Nomeclatura,Descripcion,Precio,Forma_de_Pago};
                    m.addColumn(filaelemento);
//                    tp=(Double.parseDouble(Precio));
//                    totales=totales+tp;
//                    total.setText(""+totales);
                    suma();
                         jPanel20.setVisible(true);
                    
                        b5.setVisible(true);
                        b5.setEnabled(false);
                    
                }
                
            }
                
             catch (Exception e) {
            }
            }
            
    }//GEN-LAST:event_tb_Grupo4KeyPressed
  private void suma()
    {
        double total = 0;
        //recorrer todas las filas de la segunda columna y va sumando las cantidades
        for( int i=0 ; i<tb_Grupo2.getRowCount() ; i++)
        {
            double numero =0,st=0;
            try{
                //capturamos valor de celda
                numero = Double.parseDouble(tb_Grupo2.getValueAt(i, 2).toString() );
                
  
            }
            catch (NumberFormatException nfe){ //si existe un error se coloca 0 a la celda
                numero = 0;
                tb_Grupo2.setValueAt(0, i, 2);
                System.out.println("error" + nfe.getMessage());
            }
            //se suma al total
          total += numero;
        }
        //muestra en el componente
        this.total.setText( String.valueOf(total) );
    }
    private void btnNuevo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo2ActionPerformed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
      quitar.setVisible(true);
    }//GEN-LAST:event_b5ActionPerformed

    private void btnNuevo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo3ActionPerformed
             quitar.dispose();
             tg=0;
    }//GEN-LAST:event_btnNuevo3ActionPerformed

    private void btnNuevo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo4ActionPerformed
           quitar.dispose();
    }//GEN-LAST:event_btnNuevo4ActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog BHC;
    private javax.swing.JDialog Jerarquias;
    private javax.swing.JTextField Nomen2;
    private javax.swing.JButton b;
    private javax.swing.JButton b2;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnNuevo2;
    private javax.swing.JButton btnNuevo3;
    private javax.swing.JButton btnNuevo4;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JLabel bu;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JComboBox cbxtipo;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lblhc;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel nom;
    private javax.swing.JDialog nomenclaturas;
    private javax.swing.JDialog quitar;
    private javax.swing.JLabel separar;
    private javax.swing.JTextField sexo;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTable tb_Grupo3;
    private javax.swing.JTable tb_Grupo4;
    private javax.swing.JTextField total;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdir;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtfp;
    private javax.swing.JTextField txthc;
    // End of variables declaration//GEN-END:variables
}
