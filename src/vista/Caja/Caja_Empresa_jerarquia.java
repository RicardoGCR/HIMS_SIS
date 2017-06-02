/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_EmpresaJerarquia;
import servicios.Conexion;

/**
 *
 * @author MYS1
 */
public class Caja_Empresa_jerarquia extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
Caja_EmpresaJerarquia cnn = new Caja_EmpresaJerarquia();
    /**
     * Creates new form Caja_Empresa_jerarquia
     */
    public Caja_Empresa_jerarquia() {
        initComponents();
         this.setExtendedState(MAXIMIZED_BOTH);
         this.getContentPane().setBackground(Color.WHITE);
         LISTARdist();
         LISTARjerar();
         LISTAR();
         formato();
         formato1();
         formato2();
         distritos.setLocationRelativeTo(null);//en el centro
         distritos.getContentPane().setBackground(Color.WHITE);
         jerarquias.setLocationRelativeTo(null);//en el centro
         jerarquias.getContentPane().setBackground(Color.WHITE);
         
        
      
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);

    }
    
     public void LISTAR(){
    try {
        String titulos[]={"Codigo","Forma de Pago","Distrito","Representante","RUC","Direccion","Telefono","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_EmpresaJerarquiaFP_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
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
                fila[9]=r.getString(10);

      
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
    
     public void LISTARdist(){
    try {
             String titulos[]={"Distrito","Provincia","Departamento",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Conexion obj = new Conexion();  
        String consulta="exec distrito";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
      
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
     
     public void LISTARjerar(){
    try {
             String titulos[]={"Nombre","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec jerarquiaFormaPago_EMPRESA_LISTAR";
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
            formato2();
            
    } catch (Exception e) {
    }
}
     
     public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Forma de Pago","Distrito","Representante","RUC","Direccion","Telefono","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[10];

            Caja_EmpresaJerarquia obj=new Caja_EmpresaJerarquia();
                    consulta="exec Caja_EmpresaJerarquia_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, buscartodo.getText());
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
                fila[9]=r.getString(10);

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
     
     public void Buscardist(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Distrito","Provincia","Departamento",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[4];

            Caja_EmpresaJerarquia obj=new Caja_EmpresaJerarquia();
                    consulta="exec distritoBuscar ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
                fila[0]=r.getString(1); // id de hc
                fila[1]=r.getString(2); // codigo de hc
                fila[2]=r.getString(3);
                fila[3]=r.getString(4);
             

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
          
     public void Buscarjerar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo2.setModel(new DefaultTableModel());
             String titulos[]={"Nombre","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_EmpresaJerarquia obj=new Caja_EmpresaJerarquia();
                    consulta="exec Jerarquia_FormaPago_BUSCAR ?";
                    
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

            formato2();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
     
     public void formato(){
    tb_Grupo1.getColumnModel().getColumn(0).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(6).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(7).setPreferredWidth(0);
    tb_Grupo1.getColumnModel().getColumn(8).setPreferredWidth(0);
    tb_Grupo1.getColumnModel().getColumn(9).setPreferredWidth(0);


    }
      
     public void formato1(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(3).setPreferredWidth(0);

    }
    
     public void formato2(){
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_Grupo2.getColumnModel().getColumn(2).setPreferredWidth(0);

    }
     
     public void Guardar(){

        if((txtrepre.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_EmpresaJerarquia cno1 = new Caja_EmpresaJerarquia();
                cno1.setCod_empre_jerar(txtcodigo.getText());//
                cno1.setCod_jerar_forma_pago(lbljerar.getText());//
                cno1.setCod_dis(lbldist.getText());//
                cno1.setRepre_empre_jerar(txtrepre.getText());//
                cno1.setRuc(txtruc.getText());//
                cno1.setDireccion_empre_jerar(txtdirec.getText());//
                cno1.setTelefono(txttelef.getText());//
                cno1.setCod_empre_jerar_farma("");//
                cno1.setNom_usu(lblusu.getText());//
                cno1.setCorreo(txtCorreo.getText());//
                cno1.setMoneda(cbxMoneda.getSelectedItem().toString());//
                    if(cno1.Nuevo()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           tg=2;
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           txtrepre.setEnabled(false);
                           txtruc.setEnabled(false);
                           txtdirec.setEnabled(false);
                           txttelef.setEnabled(false);
                           
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");
                       }}}}
     public void Modificar(){
            int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(modificar == 0 ){
                        Caja_EmpresaJerarquia cno = new Caja_EmpresaJerarquia();
                        cno.setCod_empre_jerar(txtcodigo.getText());//
                        cno.setCod_jerar_forma_pago(lbljerar.getText());//
                        cno.setCod_dis(lbldist.getText());//
                        cno.setRepre_empre_jerar(txtrepre.getText());//
                        cno.setRuc(txtruc.getText());//
                        cno.setDireccion_empre_jerar(txtdirec.getText());//
                        cno.setTelefono(txttelef.getText());//
                        cno.setCod_empre_jerar_farma("");//
                        cno.setNom_usu(lblusu.getText());//
                        if(cno.modificar()==true){
                             JOptionPane.showMessageDialog(this, "Ejecucion Correcta");
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtrepre.setEnabled(false);
                             txtruc.setEnabled(false);
                             txtdirec.setEnabled(false);
                             txttelef.setEnabled(false);
                             
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

        distritos = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            jerarquias = new javax.swing.JDialog();
            jPanel8 = new javax.swing.JPanel();
            jLabel20 = new javax.swing.JLabel();
            btnbuscar2 = new javax.swing.JButton();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane4 = new javax.swing.JScrollPane();
            tb_Grupo2 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel3 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txtcodigo = new javax.swing.JTextField();
                txtjerar = new javax.swing.JTextField();
                txtdistri = new javax.swing.JTextField();
                txtrepre = new javax.swing.JTextField();
                b = new javax.swing.JButton();
                fp = new javax.swing.JLabel();
                jLabel51 = new javax.swing.JLabel();
                b1 = new javax.swing.JButton();
                nom = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                txtruc = new javax.swing.JTextField();
                jLabel10 = new javax.swing.JLabel();
                txtdirec = new javax.swing.JTextField();
                jLabel11 = new javax.swing.JLabel();
                txttelef = new javax.swing.JTextField();
                lbldist = new javax.swing.JLabel();
                lbljerar = new javax.swing.JLabel();
                txtCorreo = new javax.swing.JTextField();
                cbxMoneda = new javax.swing.JComboBox();
                jLabel12 = new javax.swing.JLabel();
                jLabel13 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();
                txtCodFiscal = new javax.swing.JTextField();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Grupo1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    buscartodo = new javax.swing.JTextField();
                    jLabel8 = new javax.swing.JLabel();
                    jPanel1 = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
                    btneditar = new javax.swing.JButton();
                    btnguardar = new javax.swing.JButton();
                    btneliminar = new javax.swing.JButton();
                    btnbuscar = new javax.swing.JButton();
                    lblusu = new javax.swing.JLabel();
                    jPanel5 = new javax.swing.JPanel();
                    jLabel33 = new javax.swing.JLabel();

                    distritos.setAlwaysOnTop(true);
                    distritos.setMinimumSize(new java.awt.Dimension(310, 441));

                    jPanel7.setBackground(new java.awt.Color(0, 153, 153));
                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel19.setText("Distrito");

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

                    javax.swing.GroupLayout distritosLayout = new javax.swing.GroupLayout(distritos.getContentPane());
                    distritos.getContentPane().setLayout(distritosLayout);
                    distritosLayout.setHorizontalGroup(
                        distritosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(distritosLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addContainerGap())
                    );
                    distritosLayout.setVerticalGroup(
                        distritosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(distritosLayout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    jerarquias.setAlwaysOnTop(true);
                    jerarquias.setMinimumSize(new java.awt.Dimension(310, 441));

                    jPanel8.setBackground(new java.awt.Color(0, 153, 153));
                    jPanel8.setMinimumSize(new java.awt.Dimension(310, 441));

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

                    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                    jPanel8.setLayout(jPanel8Layout);
                    jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(30, Short.MAX_VALUE))
                    );
                    jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(btnbuscar2)))
                            .addGap(408, 408, 408))
                    );

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

                    javax.swing.GroupLayout jerarquiasLayout = new javax.swing.GroupLayout(jerarquias.getContentPane());
                    jerarquias.getContentPane().setLayout(jerarquiasLayout);
                    jerarquiasLayout.setHorizontalGroup(
                        jerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jerarquiasLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addContainerGap())
                    );
                    jerarquiasLayout.setVerticalGroup(
                        jerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jerarquiasLayout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                    jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                    jLabel2.setText("Codigo");

                    jLabel3.setText("Jerarquia");

                    jLabel4.setText("Distrito");

                    jLabel5.setText("Representante");

                    txtcodigo.setEnabled(false);

                    txtjerar.setEnabled(false);

                    txtdistri.setEnabled(false);

                    txtrepre.setEnabled(false);

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

                    jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                    jLabel51.setText("Edición");

                    b1.setBackground(new java.awt.Color(255, 255, 255));
                    b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                    b1.setEnabled(false);
                    b1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            b1ActionPerformed(evt);
                        }
                    });

                    nom.setBackground(new java.awt.Color(255, 255, 255));
                    nom.setForeground(new java.awt.Color(255, 255, 255));
                    nom.setText("jLabel8");

                    jLabel9.setText("RUC");

                    txtruc.setEnabled(false);

                    jLabel10.setText("Direccion");

                    txtdirec.setEnabled(false);

                    jLabel11.setText("Telefono");

                    txttelef.setEnabled(false);

                    lbldist.setForeground(new java.awt.Color(255, 255, 255));
                    lbldist.setText("jLabel12");

                    lbljerar.setForeground(new java.awt.Color(255, 255, 255));
                    lbljerar.setText("jLabel12");

                    cbxMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN", " " }));
                    cbxMoneda.setEnabled(false);

                    jLabel12.setText("Correo");

                    jLabel13.setText("Modena");

                    jLabel14.setText("Codigo de domicilio fiscal");

                    txtCodFiscal.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtCodFiscalKeyTyped(evt);
                        }
                    });

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
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(41, 41, 41)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtdistri, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                        .addComponent(txtjerar, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addGap(283, 283, 283)
                                                    .addComponent(lbldist)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lbljerar))
                                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                    .addComponent(txtrepre, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(33, 33, 33)
                                                    .addComponent(fp)
                                                    .addGap(34, 34, 34)
                                                    .addComponent(nom))))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(63, 63, 63)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txttelef, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtdirec, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCodFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(23, Short.MAX_VALUE))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel51)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel3)
                                                    .addComponent(txtjerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(b))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(txtdistri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(b1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtrepre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fp)
                                    .addComponent(nom)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtdirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txttelef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)
                                .addComponent(lbldist)
                                .addComponent(lbljerar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGap(21, 21, 21)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(txtCodFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jTabbedPane1.addTab("Edicion", jPanel3);

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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
                    );

                    jTabbedPane1.addTab("Listado", jPanel2);

                    jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("<html>Empresa<span style=\"font-size:'14px'\"><br>Forma de pago</br></span></html>");

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

                    lblusu.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                    lblusu.setForeground(new java.awt.Color(255, 255, 255));
                    lblusu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Usuario-40.png"))); // NOI18N
                    lblusu.setText("Silvana");
                    lblusu.setFocusable(false);
                    lblusu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 17, Short.MAX_VALUE))
                                .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btneliminar)
                                    .addComponent(btnbuscar))
                                .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGap(463, 463, 463)
                            .addComponent(lblusu)
                            .addGap(49, 49, 49))
                    );

                    jPanel5.setBackground(new java.awt.Color(43, 43, 43));
                    jPanel5.setPreferredSize(new java.awt.Dimension(929, 115));

                    jLabel33.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
                    jLabel33.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel33.setText("Listado");

                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                    jPanel5.setLayout(jPanel5Layout);
                    jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(67, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTabbedPane1)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
   
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
              jTabbedPane1.setSelectedIndex(1);
          txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtjerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtdistri.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            txtrepre.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtruc.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            txtdirec.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            txttelef.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            
            lbldist.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 8)));
            lbljerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
              txtrepre.setEnabled(false);
        txtruc.setEnabled(false);
        txtdirec.setEnabled(false);
        txttelef.setEnabled(false);
      
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
            
        }
    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed
       char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
              jTabbedPane1.setSelectedIndex(1);
          
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtjerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtdistri.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            txtrepre.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtruc.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            txtdirec.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            txttelef.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            
            lbldist.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 8)));
            lbljerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
        txtrepre.setEnabled(false);
        txtruc.setEnabled(false);
        txtdirec.setEnabled(false);
        txttelef.setEnabled(false);
      
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        b.setEnabled(false);
        b1.setEnabled(false);
            
        }
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
       
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        jerarquias.setVisible(true);     
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        distritos.setVisible(true);         
    }//GEN-LAST:event_b1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtcodigo.setText(cnn.id());
        tg=1;
        
        txtrepre.setEnabled(true);
        txtruc.setEnabled(true);
        txtdirec.setEnabled(true);
        txttelef.setEnabled(true);
      
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        b.setEnabled(true);
        b1.setEnabled(true);
        txtrepre.setText("");
        txtdistri.setText("");
        txtjerar.setText("");
        txttelef.setText("");
        txtruc.setText("");
        txtdirec.setText("");
        jTabbedPane1.setSelectedIndex(1);  
        cbxMoneda.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        b.setEnabled(true);
        b1.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        txtrepre.setEnabled(true);
        txtruc.setEnabled(true);
        txtdirec.setEnabled(true);
        txttelef.setEnabled(true);
        tg=2;       
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            Guardar();
            LISTAR();
        }else if(tg==2){
            Modificar();
            LISTAR();
        }      
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
   int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        try{
            if(eliminar == 0 ){
                Caja_EmpresaJerarquia hCEl = new Caja_EmpresaJerarquia();
                hCEl.setCod_empre_jerar(txtcodigo.getText());
                if(hCEl.eliminar()){
                    JOptionPane.showMessageDialog(this, "Empresa \n\t\t\teliminada");
             
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
        LISTAR();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed

    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        Buscardist();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
     
        int fila=tb_Grupo.getSelectedRow();
        if(evt.getClickCount()==2){
            distritos.dispose();
            txtdistri.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            lbldist.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));
        }
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
    
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            distritos.dispose();
            txtdistri.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));
            lbldist.setText(String.valueOf(tb_Grupo.getValueAt(fila, 3)));
        }
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed

    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        Buscarjerar();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo2MouseClicked
        //CUENTA2
        int fila=tb_Grupo2.getSelectedRow();
        if(evt.getClickCount()==2){
            jerarquias.dispose();
            txtjerar.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 1)));
            lbljerar.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
    }//GEN-LAST:event_tb_Grupo2MouseClicked

    private void tb_Grupo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo2KeyPressed
        // CUENTA 2
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo2.getSelectedRow();
            jerarquias.dispose();
            txtjerar.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 1)));
            lbljerar.setText(String.valueOf(tb_Grupo2.getValueAt(fila, 2)));
        }
    }//GEN-LAST:event_tb_Grupo2KeyPressed

    private void txtCodFiscalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFiscalKeyTyped
        
        if (txtCodFiscal.getText().length()==3){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();            
        }    

    }//GEN-LAST:event_txtCodFiscalKeyTyped

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
            java.util.logging.Logger.getLogger(Caja_Empresa_jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Empresa_jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Empresa_jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Empresa_jerarquia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Empresa_jerarquia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JComboBox cbxMoneda;
    private javax.swing.JDialog distritos;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDialog jerarquias;
    private javax.swing.JLabel lbldist;
    private javax.swing.JLabel lbljerar;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JLabel nom;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCodFiscal;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdirec;
    private javax.swing.JTextField txtdistri;
    private javax.swing.JTextField txtjerar;
    private javax.swing.JTextField txtrepre;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttelef;
    // End of variables declaration//GEN-END:variables
}
