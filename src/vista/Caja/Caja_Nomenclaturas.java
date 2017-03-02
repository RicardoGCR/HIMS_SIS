/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Caja;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import modelos.Caja.Caja_Nomenclatura;
import servicios.Conexion;
/**
 *
 * @author Ricardo
 */
public class Caja_Nomenclaturas extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
    /**
     * Creates new form Caja_Nomenclaturas
     */
 Caja_Nomenclatura cnn = new Caja_Nomenclatura();
    public Caja_Nomenclaturas() {

        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.WHITE);
         Grupos.setLocationRelativeTo(null);//en el centro
         Grupos.getContentPane().setBackground(Color.WHITE); 
         Unidad.setLocationRelativeTo(null);//en el centro
         Unidad.getContentPane().setBackground(Color.WHITE); 
         Cta6.setLocationRelativeTo(null);//en el centro
         Cta6.getContentPane().setBackground(Color.WHITE); 

     
                
        //setResizable(false);//para que no funcione el boton maximizar
        LISTAR();
        //formato();
        LISTAR_Grupo();
        LISTAR_Unidad();
        LISTAR_Cta6();
        //formatoventanas();
      
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
       
    
    }
    public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo1.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","CPT","Descripcion CPT","Area","Grupo","Cuenta 6","","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec Nomenclatura_BUSCAR ?";
                    
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
    public void BuscarGrupo(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupos.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Nombre",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec buscarGrupos ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            tb_Grupos.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos.setRowSorter(elQueOrdena);
            this.tb_Grupos.setModel(m);

            formatoventanas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      } 
    public void BuscarUnidad(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupos1.setModel(new DefaultTableModel());
             String titulos[]={"Área",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec buscarunidad ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar1.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);

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
    public void BuscarCta6(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupos2.setModel(new DefaultTableModel());
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Caja_Nomenclatura obj=new Caja_Nomenclatura();
                    consulta="exec buscarCta6 ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar2.getText());
            ResultSet r= cmd.executeQuery();
            int c=1;
            while(r.next()){
           
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
        

                m.addRow(fila);
                c++;
            }
            tb_Grupos2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos2.setRowSorter(elQueOrdena);
            this.tb_Grupos2.setModel(m);

            formatoventanas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void LISTAR(){
    try {
             String titulos[]={"Codigo","CPT","Descripcion CPT","Area","Grupo","Cuenta 6","","","","","",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[12];

            Conexion obj = new Conexion();  
        String consulta="exec Nomenclatura_Listar";
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
    public void LISTAR_Grupo(){
    try {
             String titulos[]={"Codigo","Nombre",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec listargrupos";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2); 
                fila[2]=r.getString(3); 
                    m.addRow(fila);
                    c++;
            }
            tb_Grupos.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos.setRowSorter(elQueOrdena);
            this.tb_Grupos.setModel(m);
            formatoventanas();
            
    } catch (Exception e) {
    }
}
    public void LISTAR_Unidad(){
    try {
             String titulos[]={"Área",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[2];

            Conexion obj = new Conexion();  
        String consulta="exec listaruinidad";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2); 

                    m.addRow(fila);
                    c++;
            }
        
            tb_Grupos1.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos1.setRowSorter(elQueOrdena);
            this.tb_Grupos1.setModel(m);
           formatoventanas1();
            
    } catch (Exception e) {
    }
}
    public void LISTAR_Cta6(){
    try {
             String titulos[]={"Cuenta","Descripcion",""};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[3];

            Conexion obj = new Conexion();  
        String consulta="exec listarCta6";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
          while(r.next()){
                fila[0]=r.getString(1);
                fila[1]=r.getString(2); 
                fila[2]=r.getString(3); 
                    m.addRow(fila);
                    c++;
            }
          
            tb_Grupos2.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupos2.setRowSorter(elQueOrdena);
            this.tb_Grupos2.setModel(m);
          
              formatoventanas();
    } catch (Exception e) {
    }
}
    public void formato(){
    tb_Grupo1.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(0).setMaxWidth(0);    
    tb_Grupo1.getColumnModel().getColumn(1).setPreferredWidth(80);
    tb_Grupo1.getColumnModel().getColumn(2).setPreferredWidth(600);
    tb_Grupo1.getColumnModel().getColumn(3).setPreferredWidth(80);
    tb_Grupo1.getColumnModel().getColumn(4).setPreferredWidth(80);
    tb_Grupo1.getColumnModel().getColumn(5).setPreferredWidth(300);
    
    tb_Grupo1.getColumnModel().getColumn(6).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(6).setMaxWidth(0); 
    tb_Grupo1.getColumnModel().getColumn(7).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(7).setMaxWidth(0); 
    tb_Grupo1.getColumnModel().getColumn(8).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(8).setMaxWidth(0); 
    tb_Grupo1.getColumnModel().getColumn(9).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(9).setMaxWidth(0); 
    tb_Grupo1.getColumnModel().getColumn(10).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(10).setMaxWidth(0); 
    tb_Grupo1.getColumnModel().getColumn(11).setMinWidth(0);
    tb_Grupo1.getColumnModel().getColumn(11).setMaxWidth(0); 
    
}
    public void formatoventanas(){
    tb_Grupos.getColumnModel().getColumn(0).setPreferredWidth(70);
    tb_Grupos.getColumnModel().getColumn(1).setPreferredWidth(200);
    tb_Grupos.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupos.getColumnModel().getColumn(2).setMaxWidth(0);
    
    tb_Grupos2.getColumnModel().getColumn(0).setPreferredWidth(50);
    tb_Grupos2.getColumnModel().getColumn(1).setPreferredWidth(250);
    tb_Grupos2.getColumnModel().getColumn(2).setMinWidth(0);
    tb_Grupos2.getColumnModel().getColumn(2).setMaxWidth(0);
    
}
    public void formatoventanas1(){
    tb_Grupos1.getColumnModel().getColumn(0).setPreferredWidth(200);
    tb_Grupos1.getColumnModel().getColumn(1).setMinWidth(0);
    tb_Grupos1.getColumnModel().getColumn(1).setMaxWidth(0);
}
    
    public void Modificar(){
        int modificar = JOptionPane.showConfirmDialog(this, "¿Desea ACTUALIZAR los datos?",
                      "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(modificar == 0 ){
                Caja_Nomenclatura cno1 = new Caja_Nomenclatura();
                cno1.setCod_nomen_caja(txtcod.getText());
                cno1.setCod_grupo_nomen_aten(grupo.getText());
                cno1.setAr_id(Integer.parseInt(unior.getText()));
                cno1.setId_cuenta(ct6.getText());
                cno1.setNomen_caja(txtnomenclatura.getText()+txtnom1.getText());
                cno1.setDescripcion_nomen_tipo(txtdes.getText());
                cno1.setNom_usu(lblusu.getText());//
                
                if (visible.getSelectedIndex()==0){
                    cno1.setVis_admi("Pendiente");
                     } else if(tg==11){
                    cno1.setVis_admi("NO");  
                }

       
                //cno1.setTipo_nomen(visible1.getSelectedItem().toString());
        
                cno1.setVis_aten("");
                
                       if(cno1.modificarNomenclatura()==true){
                        
                           JOptionPane.showMessageDialog(this, "Datos Actualizados");
                           LISTAR();
                           
                          
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al actualizar los datos");
                       }
                    } else {
                    JOptionPane.showMessageDialog(this, "No ha hecho ninguna modificación");
                    }
    }
    
    public void Guardar(){
        Caja_Nomenclaturas cno2 = new Caja_Nomenclaturas();
        if((txtcod.getText().equals("")) ||  txtnomenclatura.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos");
        } else {
        int guardar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea GUARDAR los datos?",
                            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(guardar == 0 ){
                
                Caja_Nomenclatura cno1 = new Caja_Nomenclatura();
                cno1.setCod_nomen_caja(txtcod.getText());
                cno1.setCod_grupo_nomen_aten(grupo.getText());
                cno1.setAr_id(Integer.parseInt(unior.getText()));
                cno1.setId_cuenta(ct6.getText());
                
                cno1.setNomen_caja(txtnomenclatura.getText()+txtnom1.getText());
                cno1.setDescripcion_nomen_tipo(txtdes.getText());
                cno1.setNom_usu(lblusu.getText());
                
                if (visible.getSelectedIndex()==0){
                    cno1.setVis_admi("Pendiente");
                     } else if(tg==11){
                    cno1.setVis_admi("NO");  
                }
         
                //cno1.setTipo_nomen(visible1.getSelectedItem().toString());

                cno1.setVis_aten("");
       
                    if(cno1.nuevaNomenclatura()==true){
                           JOptionPane.showMessageDialog(this, "Datos Guardados");
                           LISTAR();
                       } else {
                           JOptionPane.showMessageDialog(this, "Error al guardar");

                       }
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

        Grupos = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            Unidad = new javax.swing.JDialog();
            jPanel7 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            btnbuscar2 = new javax.swing.JButton();
            txtBuscar1 = new javax.swing.JTextField();
            jScrollPane4 = new javax.swing.JScrollPane();
            tb_Grupos1 = new javax.swing.JTable(){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false; //Disallow the editing of any cell
                }};
                Cta6 = new javax.swing.JDialog();
                jPanel8 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                btnbuscar3 = new javax.swing.JButton();
                txtBuscar2 = new javax.swing.JTextField();
                jScrollPane5 = new javax.swing.JScrollPane();
                tb_Grupos2 = new javax.swing.JTable(){
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
                    jTabbedPane1 = new javax.swing.JTabbedPane();
                    jPanel4 = new javax.swing.JPanel();
                    txtuo = new javax.swing.JTextField();
                    txtgrupo = new javax.swing.JTextField();
                    txtct6 = new javax.swing.JTextField();
                    txtnomenclatura = new javax.swing.JTextField();
                    txtdes = new javax.swing.JTextField();
                    jLabel6 = new javax.swing.JLabel();
                    jLabel7 = new javax.swing.JLabel();
                    jLabel9 = new javax.swing.JLabel();
                    jLabel10 = new javax.swing.JLabel();
                    jLabel11 = new javax.swing.JLabel();
                    jLabel12 = new javax.swing.JLabel();
                    txtcod = new javax.swing.JTextField();
                    lblvisa = new javax.swing.JLabel();
                    visible = new javax.swing.JComboBox();
                    grupo = new javax.swing.JLabel();
                    ct6 = new javax.swing.JLabel();
                    unior = new javax.swing.JLabel();
                    jLabel51 = new javax.swing.JLabel();
                    txtnom1 = new javax.swing.JTextField();
                    nm = new javax.swing.JLabel();
                    b = new javax.swing.JButton();
                    b1 = new javax.swing.JButton();
                    b2 = new javax.swing.JButton();
                    jPanel2 = new javax.swing.JPanel();
                    jScrollPane3 = new javax.swing.JScrollPane();
                    tb_Grupo1 = new javax.swing.JTable(){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false; //Disallow the editing of any cell
                        }};
                        jPanel27 = new javax.swing.JPanel();
                        buscartodo = new javax.swing.JTextField();
                        T3 = new javax.swing.JLabel();

                        Grupos.setAlwaysOnTop(true);
                        Grupos.setMinimumSize(new java.awt.Dimension(310, 441));
                        Grupos.setModal(true);

                        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

                        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel2.setText("Grupo");

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
                        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscarKeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                        jPanel6.setLayout(jPanel6Layout);
                        jPanel6Layout.setHorizontalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(30, Short.MAX_VALUE))
                        );
                        jPanel6Layout.setVerticalGroup(
                            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(btnbuscar1)))
                                .addGap(408, 408, 408))
                        );

                        tb_Grupos.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Grupos.setGridColor(new java.awt.Color(255, 255, 255));
                        tb_Grupos.setRowHeight(25);
                        tb_Grupos.setSelectionBackground(new java.awt.Color(0, 153, 153));
                        tb_Grupos.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_GruposMouseClicked(evt);
                            }
                        });
                        tb_Grupos.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_GruposKeyPressed(evt);
                            }
                        });
                        jScrollPane2.setViewportView(tb_Grupos);

                        javax.swing.GroupLayout GruposLayout = new javax.swing.GroupLayout(Grupos.getContentPane());
                        Grupos.getContentPane().setLayout(GruposLayout);
                        GruposLayout.setHorizontalGroup(
                            GruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GruposLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        GruposLayout.setVerticalGroup(
                            GruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GruposLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                        );

                        Unidad.setAlwaysOnTop(true);
                        Unidad.setMinimumSize(new java.awt.Dimension(310, 441));

                        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

                        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel3.setText("Área");

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
                        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscar1KeyPressed(evt);
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
                                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(30, Short.MAX_VALUE))
                        );
                        jPanel7Layout.setVerticalGroup(
                            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(btnbuscar2)))
                                .addGap(408, 408, 408))
                        );

                        tb_Grupos1.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Grupos1.setGridColor(new java.awt.Color(255, 255, 255));
                        tb_Grupos1.setRowHeight(25);
                        tb_Grupos1.setSelectionBackground(new java.awt.Color(0, 153, 153));
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

                        javax.swing.GroupLayout UnidadLayout = new javax.swing.GroupLayout(Unidad.getContentPane());
                        Unidad.getContentPane().setLayout(UnidadLayout);
                        UnidadLayout.setHorizontalGroup(
                            UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UnidadLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        UnidadLayout.setVerticalGroup(
                            UnidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UnidadLayout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                        );

                        Cta6.setAlwaysOnTop(true);
                        Cta6.setMinimumSize(new java.awt.Dimension(500, 441));
                        Cta6.setPreferredSize(new java.awt.Dimension(500, 441));

                        jPanel8.setBackground(new java.awt.Color(0, 153, 153));

                        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel4.setText("Cuenta 6");

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
                        txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtBuscar2KeyPressed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                        jPanel8.setLayout(jPanel8Layout);
                        jPanel8Layout.setHorizontalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnbuscar3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(30, Short.MAX_VALUE))
                        );
                        jPanel8Layout.setVerticalGroup(
                            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(btnbuscar3)))
                                .addGap(408, 408, 408))
                        );

                        tb_Grupos2.setModel(new javax.swing.table.DefaultTableModel(
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
                        tb_Grupos2.setGridColor(new java.awt.Color(255, 255, 255));
                        tb_Grupos2.setRowHeight(25);
                        tb_Grupos2.setSelectionBackground(new java.awt.Color(0, 153, 153));
                        tb_Grupos2.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tb_Grupos2MouseClicked(evt);
                            }
                        });
                        tb_Grupos2.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                tb_Grupos2KeyPressed(evt);
                            }
                        });
                        jScrollPane5.setViewportView(tb_Grupos2);

                        javax.swing.GroupLayout Cta6Layout = new javax.swing.GroupLayout(Cta6.getContentPane());
                        Cta6.getContentPane().setLayout(Cta6Layout);
                        Cta6Layout.setHorizontalGroup(
                            Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Cta6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                                .addContainerGap())
                        );
                        Cta6Layout.setVerticalGroup(
                            Cta6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Cta6Layout.createSequentialGroup()
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                        );

                        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                        setMinimumSize(new java.awt.Dimension(1054, 550));

                        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

                        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
                        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                        jLabel1.setText("CPT");

                        btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
                        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
                        btnNuevo.setToolTipText("");
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
                        btneditar.setToolTipText("");
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
                        btnguardar.setToolTipText("");
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
                        btneliminar.setToolTipText("");
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
                        btnbuscar.setToolTipText("");
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
                                        .addComponent(btneliminar1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblusu)
                                .addGap(19, 19, 19))
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(btneliminar1)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(lblusu)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btneditar)
                                        .addComponent(btnguardar)
                                        .addComponent(btneliminar)
                                        .addComponent(btnbuscar))
                                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(393, 393, 393))
                        );

                        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
                        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

                        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                        txtuo.setEditable(false);
                        txtuo.setBorder(null);
                        txtuo.setEnabled(false);

                        txtgrupo.setEditable(false);
                        txtgrupo.setBorder(null);
                        txtgrupo.setEnabled(false);
                        txtgrupo.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtgrupoActionPerformed(evt);
                            }
                        });

                        txtct6.setEditable(false);
                        txtct6.setBorder(null);
                        txtct6.setEnabled(false);

                        txtnomenclatura.setEditable(false);
                        txtnomenclatura.setBorder(null);
                        txtnomenclatura.setEnabled(false);

                        txtdes.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtdesActionPerformed(evt);
                            }
                        });

                        jLabel6.setText("Codigo");

                        jLabel7.setText("Grupo");

                        jLabel9.setText("Cuenta contable");

                        jLabel10.setText("Area");

                        jLabel11.setText("CPT");

                        jLabel12.setText("Descripcion");

                        txtcod.setEditable(false);
                        txtcod.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                        txtcod.setEnabled(false);

                        lblvisa.setText("Visible en Admision");

                        visible.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Si", "No" }));

                        grupo.setForeground(new java.awt.Color(255, 255, 255));
                        grupo.setText("jLabel3");

                        ct6.setForeground(new java.awt.Color(255, 255, 255));
                        ct6.setText("jLabel4");

                        unior.setForeground(new java.awt.Color(255, 255, 255));
                        unior.setText("jLabel5");

                        jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
                        jLabel51.setForeground(new java.awt.Color(102, 102, 102));
                        jLabel51.setText("Edición ");

                        txtnom1.addKeyListener(new java.awt.event.KeyAdapter() {
                            public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtnom1KeyPressed(evt);
                            }
                        });

                        nm.setForeground(new java.awt.Color(255, 255, 255));
                        nm.setText("jLabel5");

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

                        b2.setForeground(new java.awt.Color(240, 240, 240));
                        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Search-16.png"))); // NOI18N
                        b2.setMnemonic('N');
                        b2.setContentAreaFilled(false);
                        b2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        b2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                        b2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                        b2.setIconTextGap(30);
                        b2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                        b2.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                b2ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                        jPanel4.setLayout(jPanel4Layout);
                        jPanel4Layout.setHorizontalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel10))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(lblvisa)
                                            .addComponent(jLabel12))
                                        .addGap(61, 61, 61)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(txtnom1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(txtgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(txtct6, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(txtuo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap(751, Short.MAX_VALUE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(nm)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(ct6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(unior)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(grupo)
                                                        .addGap(314, 314, 314))))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(visible, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                        );
                        jPanel4Layout.setVerticalGroup(
                            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel51)
                                .addGap(16, 16, 16)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel7))
                                            .addComponent(b))
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(b1)
                                                .addGap(24, 24, 24))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel9)
                                                    .addComponent(txtct6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel10)
                                            .addComponent(txtuo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(b2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtnomenclatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(visible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblvisa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ct6)
                                    .addComponent(nm)
                                    .addComponent(unior)
                                    .addComponent(grupo)))
                        );

                        jTabbedPane1.addTab("Edicion", jPanel4);

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

                        jPanel27.setBackground(new java.awt.Color(204, 204, 204));

                        buscartodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                        buscartodo.setBorder(null);
                        buscartodo.addCaretListener(new javax.swing.event.CaretListener() {
                            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                                buscartodoCaretUpdate(evt);
                            }
                        });
                        buscartodo.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                buscartodoMouseClicked(evt);
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
                        });

                        T3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Búsqueda-25.png"))); // NOI18N
                        T3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                        T3.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                T3MouseClicked(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
                        jPanel27.setLayout(jPanel27Layout);
                        jPanel27Layout.setHorizontalGroup(
                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                        );
                        jPanel27Layout.setVerticalGroup(
                            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(T3)
                                    .addComponent(buscartodo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                        );

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                        );

                        jTabbedPane1.addTab("Listado", jPanel2);

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane1))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        txtcod.setText(cnn.idNomen());
        txtcod.setEnabled(true);
        tg=1;
         txtnomenclatura.setEnabled(false);
         txtgrupo.setEnabled(false);
         txtct6.setEnabled(false);
         txtuo.setEnabled(false);
         txtdes.setEnabled(true);
    
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);
         txtnomenclatura.setText("");
         txtdes.setText("");
    
         txtuo.setText("");
         txtct6.setText("");
         txtgrupo.setText("");
        
        jTabbedPane1.setSelectedIndex(0);
        Grupos.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
         txtnomenclatura.setEnabled(true);
         txtdes.setEnabled(true);
 
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);
         tg=2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if(tg==1){
            Guardar();
        }else if(tg==2){
            Modificar();
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
  
        int eliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea ELIMINAR?",
            "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        try{
            if(eliminar == 0 ){
                Caja_Nomenclatura hCEl = new Caja_Nomenclatura();
                hCEl.setCod_nomen_caja(txtcod.getText());
                if(hCEl.eliminarCajaNomenclatura()){
                    JOptionPane.showMessageDialog(this, "Historia Clínica \n\t\t\teliminada");
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

    private void txtgrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgrupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgrupoActionPerformed

    private void txtdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdesActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        Buscar();  
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
        BuscarGrupo();
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void tb_Grupo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MouseClicked
    int fila=tb_Grupo1.getSelectedRow();
     if(evt.getClickCount()==2){
      jTabbedPane1.setSelectedIndex(0);
       txtcod.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));  
       txtgrupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2))); 
       txtct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3)));
   }
        tg=2;
         txtnomenclatura.setEnabled(true);
         txtdes.setEnabled(true);
     
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);

    }//GEN-LAST:event_tb_Grupo1MouseClicked

    private void tb_Grupo1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupo1MousePressed

    }//GEN-LAST:event_tb_Grupo1MousePressed

    private void tb_Grupo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupo1KeyPressed

          char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo1.getSelectedRow();
            jTabbedPane1.setSelectedIndex(0);
           
            txtcod.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 1)));  
            txtgrupo.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 2))); 
            txtct6.setText(String.valueOf(tb_Grupo1.getValueAt(fila, 3))); 
        
    
       }
        tg=2;
        txtnomenclatura.setEnabled(true);
         txtdes.setEnabled(true);
       
         visible.setEnabled(true);
         btnguardar.setEnabled(true);
         btneditar.setEnabled(false);
         b.setEnabled(true);
         b1.setEnabled(true);
         b2.setEnabled(true);

    }//GEN-LAST:event_tb_Grupo1KeyPressed

    private void tb_GruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GruposMouseClicked
   
        int fila=tb_Grupos.getSelectedRow();
        if(evt.getClickCount()==2){
            Grupos.dispose();
            nm.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0)));
            txtgrupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0))+"  :  "+(tb_Grupos.getValueAt(fila, 1)));
            grupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 2)));
            
        }
         try {
            String cuenta5=nm.getText();
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4));
            txtnomenclatura.setText(palabra5);
            txtnomenclatura.setEnabled(true);
        } catch (Exception e) {
        } 
        
          txtgrupo.setEnabled(true);
            txtgrupo.setEditable(false);
    }//GEN-LAST:event_tb_GruposMouseClicked

    private void tb_GruposKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GruposKeyPressed
 
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos.getSelectedRow();
            Grupos.dispose();
    
            txtgrupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0))+"  :  "+(tb_Grupos.getValueAt(fila, 1)));
            grupo.setText(String.valueOf(tb_Grupos.getValueAt(fila, 2)));
            nm.setText(String.valueOf(tb_Grupos.getValueAt(fila, 0)));
            txtgrupo.setEnabled(true);
            txtgrupo.setEditable(false);
           
        }
         try {
            String cuenta5=nm.getText();
            String palabra5=String.valueOf(cuenta5.charAt(0)+""+cuenta5.charAt(1)+""+cuenta5.charAt(2)+""+cuenta5.charAt(3)+""+cuenta5.charAt(4));
            txtnomenclatura.setText(palabra5);
            txtnomenclatura.setEnabled(true);
        } catch (Exception e) {
        } 
   Cta6.setVisible(true);
               
    }//GEN-LAST:event_tb_GruposKeyPressed

    private void btnbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar2ActionPerformed

    private void txtBuscar1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar1CaretUpdate
        BuscarUnidad();
    }//GEN-LAST:event_txtBuscar1CaretUpdate

    private void txtBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar1ActionPerformed

    private void tb_Grupos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos1MouseClicked
       
        int fila=tb_Grupos1.getSelectedRow();
        if(evt.getClickCount()==2){
            Unidad.dispose();
            txtuo.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            unior.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            txtuo.setEnabled(true);
            txtuo.setEditable(false);
            } 
    }//GEN-LAST:event_tb_Grupos1MouseClicked

    private void tb_Grupos1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos1KeyPressed
      
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos1.getSelectedRow();
            Unidad.dispose();
            txtuo.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 0)));
            unior.setText(String.valueOf(tb_Grupos1.getValueAt(fila, 1)));
            txtuo.setEnabled(true);
            txtuo.setEditable(false);
            txtnom1.requestFocus();
           
        }
    }//GEN-LAST:event_tb_Grupos1KeyPressed

    private void btnbuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar3ActionPerformed

    private void txtBuscar2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscar2CaretUpdate
        BuscarCta6();
    }//GEN-LAST:event_txtBuscar2CaretUpdate

    private void txtBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscar2ActionPerformed

    private void tb_Grupos2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_Grupos2MouseClicked
         int fila=tb_Grupos2.getSelectedRow();
        if(evt.getClickCount()==2){
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"  :  "+(tb_Grupos2.getValueAt(fila, 1)));
            ct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtct6.setEnabled(true);
            txtct6.setEditable(false);
            txtnomenclatura.setRequestFocusEnabled(true);
           
        }
    }//GEN-LAST:event_tb_Grupos2MouseClicked

    private void tb_Grupos2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_Grupos2KeyPressed
            char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupos2.getSelectedRow();
            Cta6.dispose();
            txtct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 0))+"  :  "+(tb_Grupos2.getValueAt(fila, 1)));
            ct6.setText(String.valueOf(tb_Grupos2.getValueAt(fila, 2)));
            txtct6.setEnabled(true);
            txtct6.setEditable(false);
            txtnomenclatura.setRequestFocusEnabled(true);
            Unidad.setVisible(true);
        }
    }//GEN-LAST:event_tb_Grupos2KeyPressed

    private void T3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T3MouseClicked

    }//GEN-LAST:event_T3MouseClicked

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

    private void buscartodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscartodoMouseClicked

    }//GEN-LAST:event_buscartodoMouseClicked

    private void buscartodoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_buscartodoCaretUpdate
 Buscar();      
    }//GEN-LAST:event_buscartodoCaretUpdate

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        Grupos.setVisible(true);
    }//GEN-LAST:event_bActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Cta6.setVisible(true);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        Unidad.setVisible(true);
    }//GEN-LAST:event_b2ActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupos.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupos.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupos1.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupos1.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscar1KeyPressed

    private void txtBuscar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyPressed
        char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            //int fila = tb_Grupo3.getSelectedRow();
          tb_Grupos2.getSelectionModel().setSelectionInterval (0,0) ;
          tb_Grupos2.requestFocus();
          } 
    }//GEN-LAST:event_txtBuscar2KeyPressed

    private void txtnom1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnom1KeyPressed
          char teclaPresionada = evt.getKeyChar();
        if(teclaPresionada==KeyEvent.VK_ENTER){
            txtdes.requestFocus(); 
        }
    }//GEN-LAST:event_txtnom1KeyPressed

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
            java.util.logging.Logger.getLogger(Caja_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Nomenclaturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Cta6;
    private javax.swing.JDialog Grupos;
    private javax.swing.JLabel T3;
    private javax.swing.JDialog Unidad;
    private javax.swing.JButton b;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar2;
    private javax.swing.JButton btnbuscar3;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField buscartodo;
    private javax.swing.JLabel ct6;
    private javax.swing.JLabel grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel lblvisa;
    private javax.swing.JLabel nm;
    private javax.swing.JTable tb_Grupo1;
    private javax.swing.JTable tb_Grupos;
    private javax.swing.JTable tb_Grupos1;
    private javax.swing.JTable tb_Grupos2;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtct6;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtgrupo;
    private javax.swing.JTextField txtnom1;
    private javax.swing.JTextField txtnomenclatura;
    private javax.swing.JTextField txtuo;
    private javax.swing.JLabel unior;
    private javax.swing.JComboBox visible;
    // End of variables declaration//GEN-END:variables
}
