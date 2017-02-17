/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;
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
import modelos.Caja.Caja_Jerarquias;
/**
 *
 * @author MYS1
 */
public class Caja_Jerarquia extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
Caja_Jerarquias cnn = new Caja_Jerarquias();
    /**
     * Creates new form Caja_Jerarquia
     */
     public Caja_Jerarquia() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         setLocationRelativeTo(null);//en el centro
         LISTAR_Relacion();
         formatoRelacion();
         LISTAR();
         formato();
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);

         RelacionBuscar.setLocationRelativeTo(null);//en el centro
         RelacionBuscar.getContentPane().setBackground(Color.WHITE);


    }
     public void BuscarRelacion(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Forma de pago",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_RELACIONB ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
        

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);

            formatoRelacion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
  
     public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Descripcion","Relacion","Nivel","Estado"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[6];

            Caja_Jerarquias obj=new Caja_Jerarquias();
                    consulta="exec Caja_Jerarquia_BUSCAR ?";
                    
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
     public void LISTAR_Relacion(){
    try {
             String titulos[]={"Forma de pago",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Jerarquia_RELACION";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
      
                    m.addRow(fila);
                    c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            formatoRelacion();
            
    } catch (Exception e) {
    }
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
            tb_Grupo1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo1.setRowSorter(elQueOrdena);
            this.tb_Grupo1.setModel(m);
            formato();
            
    } catch (Exception e) {
    }
}
 
     public void formato(){
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(500);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(20);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(100);
    }
     public void formatoRelacion(){
      tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(400);
      tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(1);
     }
    
     public void GuardarJerarquia(){

        if((forma.getText().equals("")) ||  des.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Jerarquias cno1 = new Caja_Jerarquias();
                cno1.setCod_jerar_forma_pago(txtcodigo.getText());//
                cno1.setNom_forma_pago(forma.getText());//
                cno1.setDescri_forma_pago(des.getText());//
                cno1.setRelacion_forma_pago(codpago.getText());//
                cno1.setNivel_forma_pago("2");//
                cno1.setTipo_estado_pago("P(Pendiente)");//
                cno1.setNom_usu(lblusu.getText());//
                    if(cno1.NuevaJerarquia()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=2;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           forma.setEnabled(false);
                           des.setEnabled(false);
                           
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
     
     public void ModificarJerarquia(){
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_Jerarquias cno = new Caja_Jerarquias();
                        cno.setCod_jerar_forma_pago(txtcodigo.getText());//
                        cno.setNom_forma_pago(forma.getText());//
                        cno.setDescri_forma_pago(des.getText());//
                        cno.setRelacion_forma_pago(codpago.getText());//
                        cno.setNivel_forma_pago("2");//
                        cno.setTipo_estado_pago("P(Pendiente)");//
                        cno.setNom_usu(lblusu.getText());//
                        if(cno.Caja_Jerarquia_MODIFICAR()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             forma.setEnabled(false);
                             des.setEnabled(false);
                        } else {
                           
                             JOptionPane.showMessageDialog(this, "Error al guardar");
                        }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
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

        RelacionBuscar = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jDialog1 = new javax.swing.JDialog();
            jPanel4 = new javax.swing.JPanel();
            jPanel1 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            btnNuevo = new javax.swing.JButton();
            btneditar = new javax.swing.JButton();
            btnguardar = new javax.swing.JButton();
            btneliminar = new javax.swing.JButton();
            btnbuscar = new javax.swing.JButton();
            lblusu = new javax.swing.JLabel();
            jLabel6 = new javax.swing.JLabel();
            jLabel7 = new javax.swing.JLabel();
            btneliminar1 = new javax.swing.JButton();
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
                forma = new javax.swing.JTextField();
                des = new javax.swing.JTextField();
                relacion = new javax.swing.JTextField();
                b = new javax.swing.JButton();
                codpago = new javax.swing.JLabel();
                jLabel51 = new javax.swing.JLabel();

                RelacionBuscar.setAlwaysOnTop(true);
                RelacionBuscar.setMinimumSize(new java.awt.Dimension(310, 441));

                jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                jLabel19.setText("Forma de Pago");

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
                        .addContainerGap(30, Short.MAX_VALUE))
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

                javax.swing.GroupLayout RelacionBuscarLayout = new javax.swing.GroupLayout(RelacionBuscar.getContentPane());
                RelacionBuscar.getContentPane().setLayout(RelacionBuscarLayout);
                RelacionBuscarLayout.setHorizontalGroup(
                    RelacionBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(RelacionBuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                        .addContainerGap())
                );
                RelacionBuscarLayout.setVerticalGroup(
                    RelacionBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RelacionBuscarLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addContainerGap())
                );

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 586, Short.MAX_VALUE)
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 339, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
                jDialog1.getContentPane().setLayout(jDialog1Layout);
                jDialog1Layout.setHorizontalGroup(
                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jDialog1Layout.setVerticalGroup(
                    jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("Jerarquias");

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

                jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("Fecha");

                jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setText("Hora");

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
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(412, 412, 412))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblusu)
                            .addComponent(jLabel7))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblusu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                );

                jTabbedPane1.addTab("Listado", jPanel2);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                jLabel2.setText("Codigo");

                jLabel3.setText("Forma de Pago");

                jLabel4.setText("Descripcion");

                jLabel5.setText("Relacion");

                txtcodigo.setEnabled(false);

                forma.setEnabled(false);

                des.setEnabled(false);

                relacion.setEnabled(false);

                b.setBackground(new java.awt.Color(255, 255, 255));
                b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                b.setEnabled(false);
                b.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        bActionPerformed(evt);
                    }
                });

                codpago.setBackground(new java.awt.Color(255, 255, 255));
                codpago.setForeground(new java.awt.Color(255, 255, 255));
                codpago.setText("jLabel8");

                jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                jLabel51.setText("Edicion");

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
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2))
                                .addGap(41, 41, 41)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(relacion, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(codpago))
                                    .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(194, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3)
                                            .addComponent(forma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(des, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(relacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(b)))
                            .addComponent(codpago))
                        .addContainerGap(39, Short.MAX_VALUE))
                );

                jTabbedPane1.addTab("Edicion", jPanel3);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtcodigo.setText(cnn.idCJ());
        tg=1;
        forma.setEnabled(true);
        des.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        b.setEnabled(true);
        forma.setText("");
        des.setText("");
        relacion.setText("");
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         forma.setEnabled(true);
         des.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setEnabled(true);
         tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            GuardarJerarquia();
        }else if(tg==2){
            ModificarJerarquia();
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
     int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        try{
            if(eliminar == 0 ){
                Caja_Jerarquias hCEl = new Caja_Jerarquias();
                hCEl.setCod_jerar_forma_pago(txtcodigo.getText());
                if(hCEl.eliminarjerarquia()){
                    JOptionPane.showMessageDialog(this, "Jerarquia  \n\t\t\tEliminada");
             
                }
           
            } 
        }catch(Exception e){
            System.out.println("Error: " + e.toString());
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
    jTabbedPane1.setSelectedIndex(0);
    btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        RelacionBuscar.setVisible(true);
    }//GEN-LAST:event_bActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed

    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarRelacion();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
        //CUENTA2
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            RelacionBuscar.dispose();
            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            codpago.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            RelacionBuscar.dispose();
            relacion.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            codpago.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1)));
        }

    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
         //CUENTA2
        Caja_Jerarquias cno1 = new Caja_Jerarquias();
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
            jTabbedPane1.setSelectedIndex(1);
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            des.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            codpago.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            tg=2;
            btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
     
        relacion.setText(cno1.BuscarC(String.valueOf(tb_Grupo1.getValueAt(fila, 3))));

        }
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed
            // CUENTA 2
        Caja_Jerarquias cno1 = new Caja_Jerarquias();  
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
            jTabbedPane1.setSelectedIndex(1);
         
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            des.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            codpago.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            relacion.setText(cno1.BuscarC(String.valueOf(tb_Grupo1.getValueAt(fila, 3))));
        }
        tg=2;
        forma.setEnabled(true);
        des.setEnabled(true);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
         b.setEnabled(false);
        forma.setEnabled(false);
        des.setEnabled(false);
     
        
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Buscar();
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed
           //CUENTA2
        Caja_Jerarquias cno1 = new Caja_Jerarquias();
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
            jTabbedPane1.setSelectedIndex(1);
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            forma.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            des.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            codpago.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            tg=2;
            btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
     
        relacion.setText(cno1.BuscarC(String.valueOf(tb_Grupo1.getValueAt(fila, 3))));
b.setEnabled(false);
        forma.setEnabled(false);
        des.setEnabled(false);
     
        }
    }//GEN-LAST:event_tb_Grupo1MousePressed

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
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Jerarquia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog RelacionBuscar;
    private javax.swing.JButton b;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JLabel codpago;
    private javax.swing.JTextField des;
    private javax.swing.JTextField forma;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblusu;
    private javax.swing.JTextField relacion;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtcodigo;
    // End of variables declaration//GEN-END:variables
}
