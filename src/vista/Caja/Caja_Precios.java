/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;
import static groovy.xml.Entity.gt;
import static groovy.xml.Entity.lt;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import servicios.Conexion;
import modelos.Caja.Caja_Precio;
/**
 *
 * @author MYS1
 */
public class Caja_Precios extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tge;
Caja_Precio cnn = new Caja_Precio();
    /**
     * Creates new form Caja_Precios
     */
    public Caja_Precios() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         LISTAR();
         LISTARFP();
         LISTARNom();
         formato1();
         formato();
         
         FormaP.setLocationRelativeTo(null);//en el centro
         FormaP.getContentPane().setBackground(Color.WHITE);
         Nomenclatura.setLocationRelativeTo(null);//en el centro
         Nomenclatura.getContentPane().setBackground(Color.WHITE);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        cargareliminar.setVisible(false);
        buscartodo.setVisible(false);
    }
    
    public void LISTAR(){
    try {
             String titulos[]={"Codigo","Forma de Pago","Descripcion Forma de pago","Nomenclatura","Descripcion Nomenclatura","Precio","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Conexion obj = new Conexion();  
        String consulta="exec caja_Precios_listar";
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
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formato();
            
    } catch (Exception e) {
    }
}
    
    public void LISTARFP(){
    try {
             String titulos[]={"Forma de Pago","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec ListarJerarquiaas";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            formato1();
            
    } catch (Exception e) {
    }
}
    public void LISTARNom(){
    try {
             String titulos[]={"Nomenclatura","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec ListarNomenclaturas";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);
            formato1();
            
    } catch (Exception e) {
    }
}
    public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Descripcion Forma de pago","Nomenclatura","Descripcion Nomenclatura","Precio","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[8];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec Caja_Precios_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
                fila[4]=r.getString(5);
                fila[5]=r.getString(6);
                fila[6]=r.getString(7);
                fila[7]=r.getString(8);

                m.addRow(fila);
                c++;
            }
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);

            formato();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void formato(){
    tb_Grupo1.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(220);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(220);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(90);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(600);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(100);
    
    tb_Grupo1.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(6).setMaxWidth(0);
    
    tb_Grupo1.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(7).setMaxWidth(0);
    tb_Grupo1.setRowHeight(30);
    }
    
    public void BuscarFP(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Forma de Pago","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec BuscarJerarquias ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
             

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formato1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void BuscarNom(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo2.setModel(new DefaultTableModel());
             String titulos[]={"Nomenclatura","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Precio obj=new Caja_Precio();
                    consulta="exec BuscarNomenclaturas ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
             

                m.addRow(fila);
                c++;
            }
            tb_Grupo2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo2.setRowSorter(elQueOrdena);
            this.tb_Grupo2.setModel(m);

            formato1();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    
    public void formato1(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(2).setMaxWidth(0);

    tb_Grupo.setRowHeight(30);
    
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(80);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_Grupo2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo2.getColumnModel().getColumn(2).setMaxWidth(0);

    tb_Grupo2.setRowHeight(30);
    }
    public void GuardarPrecio(){

        if((precio.getText().equals(""))){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ingrese el precio");
                                eli.setVisible(false);
                                noeli.setVisible(false);
        } else {

                
                Caja_Precio cno1 = new Caja_Precio();
                //cno1.setCod_precio(txtcodigo.getText());//
                cno1.setCod_jerar_forma_pago(fp.getText());//
                cno1.setCod_nomen_caja(nom.getText());//
                cno1.setPrecio(precio.getText());//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevoPrecio()==true){
                           cargareliminar.setVisible(true);   
                           cargareliminar.setBackground(new Color(0,153,102)); 
                           Mensaje.setText("Datos Guardados de forma correcta");
                           tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(false);
                           btneliminar.setEnabled(false);
                           precio.setEnabled(false);
                           eli.setText("OK");
                           eli.setVisible(true);
                           noeli.setVisible(false);
                           b.setVisible(false);
                           b1.setVisible(false);
                        
                           forma.setEditable(false);
                           Nomen.setEditable(false);
                            LISTAR();
                           
                       } else {
                            cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                tge=1;
                       }}}

    public void Modificar(){
  
                        Caja_Precio cno = new Caja_Precio();
                        cno.setCod_precio(txtcodigo.getText());//
                        cno.setCod_jerar_forma_pago(fp.getText());//
                        cno.setCod_nomen_caja(nom.getText());//
                        cno.setPrecio(precio.getText());//
                        cno.setNom_usu(lblusu.getText());//
                        if(cno.modificarPrecio()==true){
                                   cargareliminar.setVisible(true);   
                           cargareliminar.setBackground(new Color(0,153,102)); 
                           Mensaje.setText("Datos Actualizados de forma correcta");
                           tge=1;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           precio.setEnabled(false);
                           noeli.setText("OK");
                           eli.setVisible(false);
                           b.setVisible(false);
                           b1.setVisible(false);
                           noeli.setVisible(true);
                                tge=3;
                                
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(false);
                             btneliminar.setEnabled(false);
                             precio.setEditable(false);
                             
                       
                           forma.setEditable(false);
                           Nomen.setEditable(false);
                        } else {
                           
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrió un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                                tge=2;
                        }
                       
    }
    public void eliminar(){
         try{
            if(tge==6 ){
                Caja_Precio hCEl = new Caja_Precio();
                hCEl.setCod_precio(txtcodigo.getText());
                if(hCEl.eliminar()){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(false);
                                noeli.setText("OK");
                                noeli.setVisible(true);
                                tge=7;
                }
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
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

        FormaP = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        T9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            Nomenclatura = new javax.swing.JDialog();
            jPanel8 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            jPanel47 = new javax.swing.JPanel();
            T8 = new javax.swing.JLabel();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane4 = new javax.swing.JScrollPane();
            tb_Grupo2 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jPanel1 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                btnNuevo = new javax.swing.JButton();
                btneditar = new javax.swing.JButton();
                btnguardar = new javax.swing.JButton();
                btneliminar = new javax.swing.JButton();
                btnbuscar = new javax.swing.JButton();
                lblusu = new javax.swing.JLabel();
                btneliminar1 = new javax.swing.JButton();
                cargareliminar = new javax.swing.JPanel();
                Mensaje = new javax.swing.JLabel();
                eli = new javax.swing.JButton();
                noeli = new javax.swing.JButton();
                jPanel28 = new javax.swing.JPanel();
                T4 = new javax.swing.JLabel();
                buscartodo = new javax.swing.JTextField();
                jPanel3 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                forma = new javax.swing.JTextField();
                Nomen = new javax.swing.JTextField();
                precio = new javax.swing.JTextField();
                fp = new javax.swing.JLabel();
                jLabel51 = new javax.swing.JLabel();
                nom = new javax.swing.JLabel();
                b = new javax.swing.JButton();
                b1 = new javax.swing.JButton();
                txtcodigo = new javax.swing.JTextField();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Grupo1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};

                    FormaP.setMinimumSize(new java.awt.Dimension(310, 441));
                    FormaP.setResizable(false);

                    jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel19.setText("Forma de Pago");

                    jPanel48.setBackground(new java.awt.Color(255, 255, 255));

                    T9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T9.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T9MouseClicked(evt);
                        }
                    });

                    txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    txtBuscar.setBorder(null);
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
                    txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtBuscarKeyReleased(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
                    jPanel48.setLayout(jPanel48Layout);
                    jPanel48Layout.setHorizontalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel48Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(T9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel48Layout.setVerticalGroup(
                        jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(T9)))
                    );

                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                    jPanel7.setLayout(jPanel7Layout);
                    jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(334, 334, 334))
                    );

                    jScrollPane2.setBorder(null);

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

                    javax.swing.GroupLayout FormaPLayout = new javax.swing.GroupLayout(FormaP.getContentPane());
                    FormaP.getContentPane().setLayout(FormaPLayout);
                    FormaPLayout.setHorizontalGroup(
                        FormaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    );
                    FormaPLayout.setVerticalGroup(
                        FormaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(FormaPLayout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    Nomenclatura.setMinimumSize(new java.awt.Dimension(612, 430));
                    Nomenclatura.setResizable(false);

                    jPanel8.setBackground(new java.awt.Color(0, 153, 153));
                    jPanel8.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel20.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel20.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel20.setText("Nomenclaturas");

                    jPanel47.setBackground(new java.awt.Color(255, 255, 255));

                    T8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T8.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T8MouseClicked(evt);
                        }
                    });

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
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtBuscar1KeyReleased(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
                    jPanel47.setLayout(jPanel47Layout);
                    jPanel47Layout.setHorizontalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(T8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );
                    jPanel47Layout.setVerticalGroup(
                        jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(T8))
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                    jPanel8.setLayout(jPanel8Layout);
                    jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(348, 348, 348))
                    );

                    jScrollPane4.setBorder(null);

                    tb_Grupo2.setModel(new javax.swing.table.DefaultTableModel(
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
                    tb_Grupo2.setGridColor(new java.awt.Color(255, 255, 255));
                    tb_Grupo2.setRowHeight(25);
                    tb_Grupo2.setSelectionBackground(new java.awt.Color(0, 153, 153));
                    tb_Grupo2.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tb_Grupo2MouseClicked(evt);
                        }
                    });
                    tb_Grupo2.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            tb_Grupo2KeyPressed(evt);
                        }
                    });
                    jScrollPane4.setViewportView(tb_Grupo2);

                    javax.swing.GroupLayout NomenclaturaLayout = new javax.swing.GroupLayout(Nomenclatura.getContentPane());
                    Nomenclatura.getContentPane().setLayout(NomenclaturaLayout);
                    NomenclaturaLayout.setHorizontalGroup(
                        NomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                    );
                    NomenclaturaLayout.setVerticalGroup(
                        NomenclaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(NomenclaturaLayout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("Precios");

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
                    btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Buscar-32.png"))); // NOI18N
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
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btneliminar)
                                    .addComponent(btnbuscar))
                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(552, 552, 552))
                    );

                    cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

                    Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                    Mensaje.setText("Mensaje");

                    eli.setForeground(new java.awt.Color(240, 240, 240));
                    eli.setText("Si");
                    eli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    eli.setContentAreaFilled(false);
                    eli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    eli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    eli.setIconTextGap(30);
                    eli.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            eliActionPerformed(evt);
                        }
                    });

                    noeli.setForeground(new java.awt.Color(240, 240, 240));
                    noeli.setText("No");
                    noeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                    noeli.setContentAreaFilled(false);
                    noeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    noeli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                    noeli.setIconTextGap(30);
                    noeli.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            noeliActionPerformed(evt);
                        }
                    });

                    jPanel28.setBackground(new java.awt.Color(255, 255, 255));

                    T4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    T4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    T4.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            T4MouseClicked(evt);
                        }
                    });

                    buscartodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    buscartodo.setBorder(null);
                    buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            buscartodoCaretUpdate(evt);
                        }
                    });
                    buscartodo.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            buscartodoActionPerformed(evt);
                        }
                    });
                    buscartodo.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            buscartodoKeyPressed(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            buscartodoKeyReleased(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
                    jPanel28.setLayout(jPanel28Layout);
                    jPanel28Layout.setHorizontalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0))
                    );
                    jPanel28Layout.setVerticalGroup(
                        jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(T4))
                            .addGap(0, 0, 0))
                    );

                    javax.swing.GroupLayout cargareliminarLayout = new javax.swing.GroupLayout(cargareliminar);
                    cargareliminar.setLayout(cargareliminarLayout);
                    cargareliminarLayout.setHorizontalGroup(
                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cargareliminarLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(Mensaje)
                            .addGap(46, 46, 46)
                            .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    cargareliminarLayout.setVerticalGroup(
                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cargareliminarLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Mensaje)
                                    .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel3.setText("Forma de Pago");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel4.setText("Nomenclatura");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel5.setText("Precio");

                    forma.setEditable(false);
                    forma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    forma.setForeground(new java.awt.Color(51, 51, 51));
                    forma.setBorder(null);
                    forma.setEnabled(false);

                    Nomen.setEditable(false);
                    Nomen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    Nomen.setForeground(new java.awt.Color(51, 51, 51));
                    Nomen.setBorder(null);
                    Nomen.setEnabled(false);

                    precio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    precio.setForeground(new java.awt.Color(51, 51, 51));
                    precio.setEnabled(false);
                    precio.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            precioKeyPressed(evt);
                        }
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            precioKeyTyped(evt);
                        }
                    });

                    fp.setBackground(new java.awt.Color(255, 255, 255));
                    fp.setForeground(new java.awt.Color(255, 255, 255));
                    fp.setText("jLabel8");

                    jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel51.setText("Edición_____________________________________________________________________________________________________________________");

                    nom.setBackground(new java.awt.Color(255, 255, 255));
                    nom.setForeground(new java.awt.Color(255, 255, 255));
                    nom.setText("jLabel8");

                    b.setForeground(new java.awt.Color(240, 240, 240));
                    b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    b.setMnemonic('N');
                    b.setContentAreaFilled(false);
                    b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    b.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    b.setIconTextGap(30);
                    b.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    b.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            bActionPerformed(evt);
                        }
                    });

                    b1.setForeground(new java.awt.Color(240, 240, 240));
                    b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    b1.setMnemonic('N');
                    b1.setContentAreaFilled(false);
                    b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                    b1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    b1.setIconTextGap(30);
                    b1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    b1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            b1ActionPerformed(evt);
                        }
                    });

                    txtcodigo.setEditable(false);
                    txtcodigo.setForeground(new java.awt.Color(255, 255, 255));
                    txtcodigo.setBorder(null);

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel51)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Nomen, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(40, 40, 40)
                                            .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(fp))
                                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(18, 18, 18)
                                            .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(338, 338, 338)
                                            .addComponent(nom))
                                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel51)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fp)
                                    .addComponent(nom))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(b)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(Nomen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(b1)
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jScrollPane3.setBorder(null);

                    tb_Grupo1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
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

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
//        txtcodigo.setText(cnn.idPrecio());
        tg=1;
        
       
      
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        b.setVisible(true);
        b1.setVisible(true);
        precio.setText("");
        Nomen.setText("");
        forma.setText("");
        txtcodigo.setEnabled(true);
        
        cargareliminar.setVisible(false);
        buscartodo.setVisible(false);
        jPanel28.setVisible(false);
        FormaP.setVisible(true);
     
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
   
        b.setVisible(true);
        b1.setVisible(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        precio.setEnabled(true);
        txtcodigo.setEnabled(true);
        forma.setEnabled(true);
        Nomen.setEnabled(true);
        precio.setEditable(true);
        tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            GuardarPrecio();
                 LISTAR();
        }else if(tg==2){
              cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,153,51)); 
              Mensaje.setText("Desea Actualizar el Registro ?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=2;
             
 
        }
      

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
              cargareliminar.setVisible(true);
              cargareliminar.setBackground(new Color(255,91,70)); 
              Mensaje.setText("Desea Eliminar este registro?");
              eli.setText("Si");
              eli.setVisible(true);
              noeli.setText("No");
              noeli.setVisible(true);
              tge=6;
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
  
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);  
        cargareliminar.setVisible(true);
        jPanel28.setVisible(true);
        eli.setVisible(false);
        noeli.setVisible(false);
        Mensaje.setText("Buscar");
        cargareliminar.setBackground(new Color(0,153,153));
        buscartodo.setVisible(true);
        buscartodo.requestFocus();
             
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
        //CUENTA2
        Caja_Precio cno1 = new Caja_Precio();
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==1){
       
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            Nomen.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            precio.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            
            nom.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            fp.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
        }
        tg=2;
        forma.setEnabled(false);
        precio.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
        Nomen.setEnabled(true);
        forma.setEnabled(true);
        txtcodigo.setEnabled(true);
        precio.setEnabled(true);
        precio.setEditable(false);
       
       
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

        Caja_Precio cno1 = new Caja_Precio();  
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
        
         
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            Nomen.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            precio.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            
            nom.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            fp.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
        }
        tg=2;
        forma.setEnabled(false);
        precio.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
        Nomen.setEnabled(true);
        forma.setEnabled(true);
        txtcodigo.setEnabled(true);
        precio.setEnabled(true);
        precio.setEditable(false);
     
        
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarFP();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
        //CUENTA2
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            FormaP.dispose();
            forma.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            fp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
                          forma.setEnabled(true);
                          Nomenclatura.setVisible(true);

        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            FormaP.dispose();
            forma.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            fp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
              forma.setEnabled(true);
              Nomenclatura.setVisible(true);
   
        }
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        BuscarNom();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked
        //CUENTA2
        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==2){
            Nomenclatura.dispose();
            Nomen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0)));
            nom.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
            Nomen.setEnabled(true);
             precio.setEnabled(true);
        precio.setEditable(true);
       precio.requestFocus();
        }
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo2.getSelectedRow();
            Nomenclatura.dispose();
            Nomen.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 0))) ;
            nom.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2))) ;
        Nomen.setEnabled(true);
         precio.setEnabled(true);
        precio.setEditable(true);
        precio.requestFocus();
        }
    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==1 || tge==2){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            Modificar();
            LISTAR();

        }
         if (tge==6){
            eliminar();
             LISTAR();

        }
      

    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        
       if (tge==1||tge==6||tge==7){
             cargareliminar.setVisible(false);  
               }
        if (tge==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51)); 
            Mensaje.setText("No se realizo ningun cambio");
            eli.setVisible(false);
            noeli.setVisible(false);

        }
           if (tge==3){
            cargareliminar.setVisible(false);

        }
                                
    }//GEN-LAST:event_noeliActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        FormaP.setVisible(true);
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
         Nomenclatura.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void T8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T8MouseClicked

    private void T9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_T9MouseClicked

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
            char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo2.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo2.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
           char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupo.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupo.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void T4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T4MouseClicked

    }//GEN-LAST:event_T4MouseClicked

    private void buscartodoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscartodoKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
            tb_Grupo1.getSelectionModel().setSelectionInterval (0,0) ;
            tb_Grupo1.requestFocus();
        }
    }//GEN-LAST:event_buscartodoKeyPressed

    private void buscartodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscartodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscartodoActionPerformed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Buscar();
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)&&tecla !=KeyEvent.VK_SPACE&&tecla!=KeyEvent.VK_BACK_SPACE){
            evt.consume();
            getToolkit().beep();            
        }    
    }//GEN-LAST:event_precioKeyTyped

    private void precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyPressed
      char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
             if(tg==1){
                 GuardarPrecio();
                 LISTAR();
                 eli.requestFocus();
        }
        if(tg==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
           noeli.setVisible(true); 
           tge=2;
        }       
          } 
    }//GEN-LAST:event_precioKeyPressed

    private void buscartodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscartodoKeyReleased
          buscartodo.setText(buscartodo.getText().toUpperCase());
    }//GEN-LAST:event_buscartodoKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        txtBuscar.setText(txtBuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyReleased
         txtBuscar1.setText(txtBuscar1.getText().toUpperCase());
    }//GEN-LAST:event_txtBuscar1KeyReleased

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
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Precios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Precios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog FormaP;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JTextField Nomen;
    private javax.swing.JDialog Nomenclatura;
    private javax.swing.JLabel T4;
    private javax.swing.JLabel T8;
    private javax.swing.JLabel T9;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JButton eli;
    private javax.swing.JTextField forma;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JLabel nom;
    private javax.swing.JTextField precio;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
