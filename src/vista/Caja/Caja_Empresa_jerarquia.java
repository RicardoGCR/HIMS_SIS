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
import javax.swing.ImageIcon;
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
byte tge;
byte tga;
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
        jTabbedPane1.setEnabledAt(0,false);
        jTabbedPane1.setEnabledAt(1, false);
        cbxMoneda.setBackground(Color.WHITE);
        cbxTipoDoc.setBackground(Color.WHITE);
        jTabbedPane1.setSelectedIndex(1); 
        cargareliminar.setVisible(false);
        txtcodigo.setVisible(false);
        btnLista.setVisible(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/iconos/icons8-Tarea del sistema-24.png")).getImage());

    }
    
     public void LISTAR(){
    try {
        String titulos[]={"Codigo","Forma de Pago","Distrito","Representante","Nº Documento","Tipo Documento","Direccion","Telefono","","","","Correo","Moneda","Código Fiscal"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];

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
                fila[10]=r.getString(11);
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14);

      
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
             String titulos[]={"Codigo","Forma de Pago","Distrito","Representante","Nº Documento","Tipo Documento","Direccion","Telefono","","","","Correo","Moneda","Código Fiscal"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[14];

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
                fila[10]=r.getString(11);
                fila[11]=r.getString(12);
                fila[12]=r.getString(13);
                fila[13]=r.getString(14);

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
    tb_Grupo1.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(180);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(280);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(6).setPreferredWidth(300);
    tb_Grupo1.getColumnModel().getColumn(7).setPreferredWidth(150);
    tb_Grupo1.getColumnModel().getColumn(8).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(8).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(9).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(9).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(10).setMaxWidth(0);
    tb_Grupo1.getColumnModel().getColumn(11).setPreferredWidth(100);
    tb_Grupo1.getColumnModel().getColumn(12).setPreferredWidth(50);
    tb_Grupo1.getColumnModel().getColumn(13).setPreferredWidth(50);
    tb_Grupo1.setRowHeight(40);


    }
      
     public void formato1(){
    tb_Grupo.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(300);
    tb_Grupo.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(3).setMaxWidth(0);
    tb_Grupo.setRowHeight(40);
    }
    
     public void formato2(){
    tb_Grupo2.getColumnModel().getColumn(0).setPreferredWidth(300);
    tb_Grupo2.getColumnModel().getColumn(1).setPreferredWidth(500);
    tb_Grupo2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupo2.getColumnModel().getColumn(2).setMaxWidth(0);
    tb_Grupo2.setRowHeight(40);
    }
     
     public void Guardar(){

        if((txtrepre.getText().equals(""))){
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Asegurese de completar todos los campos");
                           eli.setVisible(false);
                           noeli.setVisible(false);
        } else {
        
                
                Caja_EmpresaJerarquia cno1 = new Caja_EmpresaJerarquia();
//                cno1.setCod_empre_jerar(txtcodigo.getText());//
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
                cno1.setCodF(txtCodFiscal.getText());//
                cno1.setTipo_documento(cbxTipoDoc.getSelectedItem().toString());//
                    if(cno1.Nuevo()==true){
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(0,153,102)); 
                           Mensaje.setText("Datos Guardados de forma correcta");
                           eli.setText("OK");
                           eli.setVisible(true);
                           noeli.setVisible(false);
                           tge=1;
                           LISTAR();
                           btnguardar.setEnabled(false);
                           btneditar.setEnabled(true);
                           txtrepre.setEditable(false);
                           txtruc.setEditable(false);
                           txtdirec.setEditable(false);
                           txttelef.setEditable(false);
                           cbxMoneda.setEnabled(false);
                           cbxTipoDoc.setEnabled(false);
                           txtCorreo.setEditable(false);
                           txtCodFiscal.setEditable(false);
                           
                       } else {
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(255,91,70)); 
                            Mensaje.setText("Ocurrió un error, Verifique");
                            eli.setVisible(false);
                            noeli.setVisible(false);
                       }}}
     public void Modificar(){

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
                        cno.setCorreo(txtCorreo.getText());//
                        cno.setMoneda(cbxMoneda.getSelectedItem().toString());//
                        cno.setCodF(txtCodFiscal.getText());//
                        cno.setTipo_documento(cbxTipoDoc.getSelectedItem().toString());//
                        if(cno.modificar()==true){
                             LISTAR();
                             tge=9;
                             cargareliminar.setBackground(new Color(0,153,102)); 
                             Mensaje.setText("Datos Actualizados de forma correcta");
                             eli.setText("OK");
                             eli.setVisible(true);
                             noeli.setVisible(false);
                             btnguardar.setEnabled(false);
                             btneditar.setEnabled(true);
                             txtrepre.setEditable(false);
                             txtruc.setEditable(false);
                             txtdirec.setEditable(false);
                             txttelef.setEditable(false);
                             cbxMoneda.setEnabled(false);
                             cbxTipoDoc.setEnabled(false);
                             txtCorreo.setEditable(false);
                             txtCodFiscal.setEditable(false);
                             jTabbedPane1.setSelectedIndex(1); 
                             
                        } else {
                           
                            cargareliminar.setVisible(true);
                            cargareliminar.setBackground(new Color(255,91,70)); 
                            Mensaje.setText("Ocurrió un error, Verifique");
                            eli.setVisible(false);
                            noeli.setVisible(false);
                        }
                       
    }
     public void Eliminar(){
          
        try{
     
                Caja_EmpresaJerarquia hCEl = new Caja_EmpresaJerarquia();
                hCEl.setCod_empre_jerar(txtcodigo.getText());
                if(hCEl.eliminar()){
                                cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro eliminado de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                 LISTAR();
                                jTabbedPane1.setSelectedIndex(1);
                                 btnguardar.setEnabled(false);
                                btneditar.setEnabled(false);
                                btneliminar.setEnabled(false);
                                tge=7;
             
                
           
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
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txtcodigo = new javax.swing.JTextField();
                txtrepre = new javax.swing.JTextField();
                fp = new javax.swing.JLabel();
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
                panelCPT = new javax.swing.JPanel();
                txtjerar = new javax.swing.JTextField();
                b = new javax.swing.JButton();
                panelCPT1 = new javax.swing.JPanel();
                txtdistri = new javax.swing.JTextField();
                b1 = new javax.swing.JButton();
                cbxTipoDoc = new javax.swing.JComboBox();
                jLabel15 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                tb_Grupo1 = new javax.swing.JTable(){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false; //Disallow the editing of any cell
                    }};
                    jPanel1 = new javax.swing.JPanel();
                    jLabel1 = new javax.swing.JLabel();
                    btnNuevo = new javax.swing.JButton();
                    btneditar = new javax.swing.JButton();
                    btnguardar = new javax.swing.JButton();
                    btneliminar = new javax.swing.JButton();
                    lblusu = new javax.swing.JLabel();
                    jPanel23 = new javax.swing.JPanel();
                    buscartodo = new javax.swing.JTextField();
                    btnBuscarPaciente = new javax.swing.JButton();
                    lbldetalle = new javax.swing.JLabel();
                    btnLista = new javax.swing.JButton();
                    jPanel5 = new javax.swing.JPanel();
                    jLabel33 = new javax.swing.JLabel();
                    cargareliminar = new javax.swing.JPanel();
                    Mensaje = new javax.swing.JLabel();
                    eli = new javax.swing.JButton();
                    noeli = new javax.swing.JButton();

                    distritos.setAlwaysOnTop(true);
                    distritos.setMinimumSize(new java.awt.Dimension(612, 355));
                    distritos.setResizable(false);

                    jPanel7.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel7.setMinimumSize(new java.awt.Dimension(310, 441));

                    jLabel19.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel19.setText("Ubicación");

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
                            .addContainerGap(332, Short.MAX_VALUE))
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

                    jScrollPane2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                    tb_Grupo.setSelectionBackground(new java.awt.Color(102, 102, 102));
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
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                            .addGap(0, 0, 0))
                    );
                    distritosLayout.setVerticalGroup(
                        distritosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(distritosLayout.createSequentialGroup()
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    jerarquias.setAlwaysOnTop(true);
                    jerarquias.setMinimumSize(new java.awt.Dimension(465, 391));
                    jerarquias.setResizable(false);

                    jPanel8.setBackground(new java.awt.Color(41, 127, 184));
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
                            .addContainerGap(185, Short.MAX_VALUE))
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

                    jScrollPane4.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo2.setBorder(javax.swing.BorderFactory.createCompoundBorder());
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
                    tb_Grupo2.setSelectionBackground(new java.awt.Color(102, 102, 102));
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
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                            .addGap(0, 0, 0))
                    );
                    jerarquiasLayout.setVerticalGroup(
                        jerarquiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jerarquiasLayout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                    jTabbedPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
                    jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N

                    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                    jPanel3.setMinimumSize(new java.awt.Dimension(543, 156));

                    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel3.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel3.setText("Jerarquia");

                    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel4.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel4.setText("Distrito");

                    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel5.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel5.setText("Representante");

                    txtcodigo.setEnabled(false);

                    txtrepre.setEditable(false);
                    txtrepre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txtrepre.setForeground(new java.awt.Color(51, 51, 51));

                    fp.setBackground(new java.awt.Color(255, 255, 255));
                    fp.setForeground(new java.awt.Color(255, 255, 255));
                    fp.setText("jLabel8");

                    nom.setBackground(new java.awt.Color(255, 255, 255));
                    nom.setForeground(new java.awt.Color(255, 255, 255));
                    nom.setText("jLabel8");

                    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel9.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel9.setText("Tipo Documento");

                    txtruc.setEditable(false);
                    txtruc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txtruc.setForeground(new java.awt.Color(51, 51, 51));
                    txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtrucKeyTyped(evt);
                        }
                    });

                    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel10.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel10.setText("Direccion");

                    txtdirec.setEditable(false);
                    txtdirec.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txtdirec.setForeground(new java.awt.Color(51, 51, 51));

                    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel11.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel11.setText("Telefono");

                    txttelef.setEditable(false);
                    txttelef.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txttelef.setForeground(new java.awt.Color(51, 51, 51));

                    lbldist.setForeground(new java.awt.Color(255, 255, 255));
                    lbldist.setText("jLabel12");

                    lbljerar.setForeground(new java.awt.Color(255, 255, 255));
                    lbljerar.setText("jLabel12");

                    txtCorreo.setEditable(false);
                    txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txtCorreo.setForeground(new java.awt.Color(51, 51, 51));

                    cbxMoneda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    cbxMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PEN" }));
                    cbxMoneda.setEnabled(false);

                    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel12.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel12.setText("Correo");

                    jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel13.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel13.setText("Modena");

                    jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel14.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel14.setText("Codigo de domicilio fiscal");

                    txtCodFiscal.setEditable(false);
                    txtCodFiscal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    txtCodFiscal.setForeground(new java.awt.Color(51, 51, 51));
                    txtCodFiscal.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtCodFiscalKeyTyped(evt);
                        }
                    });

                    panelCPT.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtjerar.setEditable(false);
                    txtjerar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    txtjerar.setForeground(new java.awt.Color(51, 51, 51));
                    txtjerar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    txtjerar.setBorder(null);
                    txtjerar.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtjerarCaretUpdate(evt);
                        }
                    });

                    b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    b.setContentAreaFilled(false);
                    b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            bActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPTLayout = new javax.swing.GroupLayout(panelCPT);
                    panelCPT.setLayout(panelCPTLayout);
                    panelCPTLayout.setHorizontalGroup(
                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCPTLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtjerar, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    panelCPTLayout.setVerticalGroup(
                        panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCPTLayout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(panelCPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtjerar, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    panelCPT1.setBackground(new java.awt.Color(255, 255, 255));
                    panelCPT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

                    txtdistri.setEditable(false);
                    txtdistri.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
                    txtdistri.setForeground(new java.awt.Color(51, 51, 51));
                    txtdistri.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    txtdistri.setBorder(null);
                    txtdistri.addCaretListener(new javax.swing.event.CaretListener() {
                        public void caretUpdate(javax.swing.event.CaretEvent evt) {
                            txtdistriCaretUpdate(evt);
                        }
                    });

                    b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                    b1.setContentAreaFilled(false);
                    b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    b1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            b1ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelCPT1Layout = new javax.swing.GroupLayout(panelCPT1);
                    panelCPT1.setLayout(panelCPT1Layout);
                    panelCPT1Layout.setHorizontalGroup(
                        panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCPT1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtdistri, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))
                    );
                    panelCPT1Layout.setVerticalGroup(
                        panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCPT1Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addGroup(panelCPT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtdistri, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    cbxTipoDoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    cbxTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Doc. Trib.NO.DOM.SIN.RUC", "1 DNI", "4 CARNET DE EXTRANJERIA", "6 RUC", "7 PASAPORTE", "A CED.DIPLOMATICA DE IDENTIDAD" }));
                    cbxTipoDoc.setEnabled(false);

                    jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                    jLabel15.setForeground(new java.awt.Color(51, 51, 51));
                    jLabel15.setText("Numero");

                    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                    jPanel3.setLayout(jPanel3Layout);
                    jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(42, 42, 42)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txttelef, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelCPT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtrepre)
                                        .addComponent(txtdirec)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCodFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(fp)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldist)
                                    .addGap(18, 18, 18)
                                    .addComponent(nom)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbljerar)))
                            .addContainerGap(188, Short.MAX_VALUE))
                    );
                    jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(panelCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panelCPT1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addGap(2, 2, 2))
                                .addComponent(txtrepre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(cbxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(txtdirec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txttelef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel13))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(txtCodFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fp)
                                .addComponent(lbldist)
                                .addComponent(lbljerar)
                                .addComponent(nom))
                            .addContainerGap(98, Short.MAX_VALUE))
                    );

                    jTabbedPane1.addTab("", jPanel3);

                    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                    jScrollPane3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

                    tb_Grupo1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
                    tb_Grupo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                    tb_Grupo1.setSelectionBackground(new java.awt.Color(102, 102, 102));
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

                    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                    jPanel2.setLayout(jPanel2Layout);
                    jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                    );
                    jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
                    );

                    jTabbedPane1.addTab("", jPanel2);

                    jPanel1.setBackground(new java.awt.Color(41, 127, 184));
                    jPanel1.setPreferredSize(new java.awt.Dimension(284, 678));

                    jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("<html>Empresa<span style=\"font-size:'14px'\"><br>Forma de pago</br></span></html>");

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
                            .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    lbldetalle.setText("Empresa o Representante, RUC ");

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

                    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                    jPanel1.setLayout(jPanel1Layout);
                    jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLista, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbldetalle)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btneditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addContainerGap(19, Short.MAX_VALUE))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lbldetalle))
                                .addComponent(btnBuscarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addGap(171, 171, 171)
                            .addComponent(lblusu)
                            .addContainerGap())
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

                    cargareliminar.setBackground(new java.awt.Color(255, 153, 51));

                    Mensaje.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
                    Mensaje.setForeground(new java.awt.Color(255, 255, 255));
                    Mensaje.setText("Desea Actualizar el Registro ?");

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
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    cargareliminarLayout.setVerticalGroup(
                        cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cargareliminarLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(cargareliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Mensaje)
                                .addComponent(eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(noeli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                    getContentPane().setLayout(layout);
                    layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTabbedPane1)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                                .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    );
                    layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTabbedPane1))
                    );

                    pack();
                }// </editor-fold>//GEN-END:initComponents

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
   
        int fila=tb_Grupo1.getSelectedRow();
        if(evt.getClickCount()==2){
          jTabbedPane1.setSelectedIndex(0);
          jLabel33.setText("Edición");
          txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtjerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtdistri.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            txtrepre.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtruc.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            cbxTipoDoc.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            txtdirec.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            txttelef.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
            txtCorreo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 11)));
            cbxMoneda.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 12)));
            txtCodFiscal.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
            
            lbldist.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 9)));
            lbljerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 8)));
        txtrepre.setEditable(false);
        txtruc.setEditable(false);
        txtdirec.setEditable(false);
        txttelef.setEditable(false);
        txtCorreo.setEditable(false);
        txtCodFiscal.setEditable(false);       
      
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
 
        b.setEnabled(false);
        b1.setEnabled(false);
            
        }
        
        if(evt.getClickCount()==1){
          txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtjerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtdistri.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            txtrepre.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtruc.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            cbxTipoDoc.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            txtdirec.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            txttelef.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
            txtCorreo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 11)));
            cbxMoneda.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 12)));
            txtCodFiscal.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
            
            lbldist.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 9)));
            lbljerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 8)));

        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
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
              jTabbedPane1.setSelectedIndex(0);
            jLabel33.setText("Edición");
            txtcodigo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 0)));
            txtjerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));
            txtdistri.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2)));
            txtrepre.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
            txtruc.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 4)));
            cbxTipoDoc.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 5)));
            txtdirec.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 6)));
            txttelef.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 7)));
            
            txtCorreo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 11)));
            cbxMoneda.setSelectedItem(String.valueOf(tb_Grupo1.getValueAt(fila, 12)));
            txtCodFiscal.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 13)));
            
            lbldist.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 9)));
            lbljerar.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 8)));
            
        txtrepre.setEditable(false);
        txtruc.setEditable(false);
        txtdirec.setEditable(false);
        txttelef.setEditable(false);
        txtCorreo.setEditable(false);
        txtCodFiscal.setEditable(false);
      
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(false);
        b.setEnabled(false);
        b1.setEnabled(false);
            
        }
    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
//        txtcodigo.setText(cnn.id());
        jLabel33.setText("Edición");
        tg=1;
        
        txtrepre.setEditable(true);
        txtruc.setEditable(true);
        txtdirec.setEditable(true);
        txttelef.setEditable(true);
        txtCorreo.setEditable(true);
        txtCodFiscal.setEditable(true);
      
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        b.setEnabled(true);
        b1.setEnabled(true);
        txtrepre.setText("");
        txtdistri.setText("");
        txtjerar.setText("");
        txttelef.setText("");
        txtruc.setText("");
        txtdirec.setText("");
        txtCorreo.setText("");
        txtCodFiscal.setText("");
        jTabbedPane1.setSelectedIndex(0);  
        cbxMoneda.setEnabled(true);
        cbxTipoDoc.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        b.setEnabled(true);
        b1.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        txtrepre.setEditable(true);
        txtruc.setEditable(true);
        txtdirec.setEditable(true);
        txttelef.setEditable(true);
        txtCorreo.setEditable(false);
        txtCodFiscal.setEditable(false);
        cbxMoneda.setEnabled(true);
        cbxTipoDoc.setEnabled(true);
        txtCorreo.setEditable(true);
        txtCodFiscal.setEditable(true);
        tg=2;     
        jLabel33.setText("Edición");
        jTabbedPane1.setSelectedIndex(0);  
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
       if(tg==1){
            Guardar();
            b.setEnabled(false);
            b1.setEnabled(false);
        }else if(tg==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
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

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
        Buscar();
        jLabel33.setText("Listado");
        jTabbedPane1.setSelectedIndex(1); 
     
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void btnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPacienteActionPerformed

    }//GEN-LAST:event_btnBuscarPacienteActionPerformed

    private void txtjerarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtjerarCaretUpdate

    }//GEN-LAST:event_txtjerarCaretUpdate

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
       jerarquias.setVisible(true);    
    }//GEN-LAST:event_bActionPerformed

    private void txtdistriCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtdistriCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdistriCaretUpdate

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        distritos.setVisible(true);      
    }//GEN-LAST:event_b1ActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
        if (tge==3 || tge==1 ||tge==9 ||tge==7||tge==9){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            Modificar();
            btnguardar.setEnabled(false);
            btneditar.setEnabled(true);
            btneliminar.setEnabled(true);
            b.setEnabled(false);
            b1.setEnabled(false);

        }
        if (tge==6){
            Eliminar();

        }
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed

        if (tge==3 || tge==1 || tge==6){
            cargareliminar.setVisible(false);

        }
        if (tge==2){
            cargareliminar.setVisible(true);
            cargareliminar.setBackground(new Color(255,153,51));
            Mensaje.setText("No se han realizado modificaciones");
            eli.setText("OK");
            eli.setVisible(true);
            noeli.setVisible(false);
            tge=9;

        }

    }//GEN-LAST:event_noeliActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        jLabel33.setText("Listado");
        LISTAR();
        jTabbedPane1.setSelectedIndex(1);
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);

    }//GEN-LAST:event_btnListaActionPerformed

    private void txtrucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyTyped
          if (txtruc.getText().length()==11){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char tecla;
        tecla = evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
            getToolkit().beep();            
        }   
    }//GEN-LAST:event_txtrucKeyTyped

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
    private javax.swing.JLabel Mensaje;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton btnBuscarPaciente;
    public static javax.swing.JButton btnLista;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    public static javax.swing.JTextField buscartodo;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JComboBox cbxMoneda;
    private javax.swing.JComboBox cbxTipoDoc;
    private javax.swing.JDialog distritos;
    private javax.swing.JButton eli;
    private javax.swing.JLabel fp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JDialog jerarquias;
    private javax.swing.JLabel lbldetalle;
    private javax.swing.JLabel lbldist;
    private javax.swing.JLabel lbljerar;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JButton noeli;
    private javax.swing.JLabel nom;
    private javax.swing.JPanel panelCPT;
    private javax.swing.JPanel panelCPT1;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupo2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCodFiscal;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdirec;
    public static javax.swing.JTextField txtdistri;
    public static javax.swing.JTextField txtjerar;
    private javax.swing.JTextField txtrepre;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttelef;
    // End of variables declaration//GEN-END:variables
}
