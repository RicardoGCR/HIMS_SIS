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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelos.Caja.Caja_Grupo;
import servicios.Conexion;

/**
 *
 * @author Ricardo
 */
public class Caja_Grupo_Nomenclaturas extends javax.swing.JFrame {
DefaultTableModel m;
byte tg;
byte tgm;
    /**
     * Creates new form Caja_Grupo_Nomenclaturas
     */
Caja_Grupo cnn = new Caja_Grupo();
    public Caja_Grupo_Nomenclaturas() {
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);//en el centro
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        buscar.setLocationRelativeTo(null);//en el centro
        buscar.getContentPane().setBackground(Color.WHITE); 
        Ok.setLocationRelativeTo(null);//en el centro
        Ok.getContentPane().setBackground(Color.WHITE); 

        tb_Grupo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb_Grupo.doLayout();
                setLocationRelativeTo(null);//en el centro

        cargarTipoUsuario();
        formatoTipoUsuario();
        
        Caja_Grupo id=new Caja_Grupo();
        //txtcod.setText(id.idGrupo());
       
    
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtcod.setEnabled(false);
        txtav.setEnabled(false);
        txtgp.setEnabled(false);
        cargareliminar.setVisible(false);
        
    }

    
    
    public void Buscar(){
                   // TODO add your handling code here:
        String consulta="";
        try {
            tb_Grupo.setModel(new DefaultTableModel());
             String titulos[]={"Codigo","Abreviatura","Grupo","Usuario"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];

            Caja_Grupo obj=new Caja_Grupo();
                    consulta="exec Caja_Grupo_Nomenclatura_BUSCAR ?";
                    
            PreparedStatement cmd = obj.getCn().prepareStatement(consulta);
            cmd.setString(1, txtBuscar.getText());
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
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            formatoTipoUsuario();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
      }
    public void cargarTipoUsuario(){
    try {
             String titulos[]={"Codigo","Abreviatura","Grupo","Usuario"};
            m=new DefaultTableModel(null,titulos);
            JTable p=new JTable(m);
            String fila[]=new String[5];

            Conexion obj = new Conexion();  
        String consulta="exec Caja_Grupo_Nomenclaturas_LISTAR";
        ResultSet r;
        r=obj.Listar(consulta);
        int c=1;
        while(r.next()){
          
            fila[0]=r.getString(1);
            fila[1]=r.getString(2);
            fila[2]=r.getString(3);
            fila[3]=r.getString(4);
         

                m.addRow(fila);
                c++;
            }
            tb_Grupo.setModel(m);
            TableRowSorter<TableModel> elQueOrdena=new TableRowSorter<TableModel>(m);
            tb_Grupo.setRowSorter(elQueOrdena);
            this.tb_Grupo.setModel(m);
            formatoTipoUsuario();
    } catch (Exception e) {
    }
}
    public void formatoTipoUsuario(){
    tb_Grupo.getColumnModel().getColumn(1).setPreferredWidth(120);
    tb_Grupo.getColumnModel().getColumn(2).setPreferredWidth(330);

    tb_Grupo.getColumnModel().getColumn(0).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(0).setMaxWidth(0);
    tb_Grupo.getColumnModel().getColumn(3).setMinWidth(0);
    tb_Grupo.getColumnModel().getColumn(3).setMaxWidth(0);
}
     public void Guardar(){
       if((txtgp.getText().equals("")) ||  txtav.getText().equals("")){
              cargareliminar.setVisible(true);        
                                btnguardar.setEnabled(false);
                                cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Debe completar todos los campos");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tgm=0;
                                
        } else {
        
                
                Caja_Grupo cno1 = new Caja_Grupo();
                cno1.setCod_grupo_nomen_aten(txtcod.getText());//
                cno1.setCodigo_grupo(txtav.getText());//
                cno1.setNombre_grupo_nomen(txtgp.getText());//
                cno1.setNom_usu(lblusu.getText());//
       
                    if(cno1.nuevoGrupoNomenclatura()==true){
                          cargareliminar.setVisible(true);
                                cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Guardados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tgm=1;
                                 btnguardar.setEnabled(false);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        txtcod.setEnabled(true);
        txtav.setEnabled(true);
        txtgp.setEnabled(true);
        txtcod.setEditable(false);
        txtav.setEditable(false);
        txtgp.setEditable(false);  
                           cargarTipoUsuario();
                       } else {
                           cargareliminar.setVisible(true);
                           cargareliminar.setBackground(new Color(255,91,70)); 
                           Mensaje.setText("Ocurrio un error, Verifique");
                           eli.setVisible(false);
                           noeli.setVisible(false);

                       }
                    }
                          
     }
     
     public void Modificar(){
      
                        Caja_Grupo cno1 = new Caja_Grupo();
                        cno1.setCod_grupo_nomen_aten(txtcod.getText());//
                        cno1.setCodigo_grupo(txtav.getText());//
                        cno1.setNombre_grupo_nomen(txtgp.getText());//
                        cno1.setNom_usu(lblusu.getText());//
                        if(cno1.modificarGrupoNomenclatura()==true){
                            cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Datos Actualizados de forma correcta");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tgm=2;
                           cargarTipoUsuario();
       
        txtcod.setEnabled(true);
        txtav.setEnabled(true);
        txtgp.setEnabled(true);
        txtcod.setEditable(false);
        txtav.setEditable(false);
        txtgp.setEditable(false);
                        } else {
                           
                               cargareliminar.setBackground(new Color(255,91,70)); 
                                Mensaje.setText("Ocurrio un error, Verifique");
                                eli.setVisible(false);
                                noeli.setVisible(false);
                        }
                        
     }
      public void Eliminar(){ 
        try{
           
                Caja_Grupo hCEl = new Caja_Grupo();
                hCEl.setCod_grupo_nomen_aten(txtcod.getText());
                if(hCEl.eliminarCajaNomenclatura()){
                   cargareliminar.setBackground(new Color(0,153,102)); 
                                Mensaje.setText("Registro Eliminado");
                                eli.setText("OK");
                                eli.setVisible(true);
                                noeli.setVisible(false);
                                tgm=9;
                    cargarTipoUsuario();
             
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

        buscar = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnbuscar1 = new javax.swing.JButton();
        Ok = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        btnbuscar4 = new javax.swing.JButton();
        lblusu1 = new javax.swing.JLabel();
        btnNuevo1 = new javax.swing.JButton();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtav = new javax.swing.JTextField();
        txtgp = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_Grupo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //Disallow the editing of any cell
            }};
            txtBuscar = new javax.swing.JTextField();
            jLabel52 = new javax.swing.JLabel();
            cargareliminar = new javax.swing.JPanel();
            Mensaje = new javax.swing.JLabel();
            eli = new javax.swing.JButton();
            noeli = new javax.swing.JButton();

            buscar.setAlwaysOnTop(true);
            buscar.setAutoRequestFocus(false);
            buscar.setMinimumSize(new java.awt.Dimension(692, 374));

            jPanel6.setBackground(new java.awt.Color(0, 153, 153));

            jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            jLabel8.setText("Busqueda Grupos");

            btnbuscar1.setForeground(new java.awt.Color(240, 240, 240));
            btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Buscar-32.png"))); // NOI18N
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

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(242, 242, 242)
                            .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(412, Short.MAX_VALUE))
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(11, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addGap(3, 3, 3)
                    .addComponent(btnbuscar1)
                    .addGap(408, 408, 408))
            );

            javax.swing.GroupLayout buscarLayout = new javax.swing.GroupLayout(buscar.getContentPane());
            buscar.getContentPane().setLayout(buscarLayout);
            buscarLayout.setHorizontalGroup(
                buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            buscarLayout.setVerticalGroup(
                buscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(buscarLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(270, Short.MAX_VALUE))
            );

            Ok.setAlwaysOnTop(true);
            Ok.setMinimumSize(new java.awt.Dimension(396, 135));

            jPanel7.setBackground(new java.awt.Color(255, 255, 255));
            jPanel7.setMinimumSize(new java.awt.Dimension(396, 160));

            btnbuscar4.setForeground(new java.awt.Color(240, 240, 240));
            btnbuscar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Casilla de verificación marcada 2-96.png"))); // NOI18N
            btnbuscar4.setMnemonic('N');
            btnbuscar4.setToolTipText("");
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

            lblusu1.setBackground(new java.awt.Color(51, 51, 51));
            lblusu1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            lblusu1.setForeground(new java.awt.Color(102, 102, 102));
            lblusu1.setText("El registro se guardo de forma exitosa");

            btnNuevo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            btnNuevo1.setForeground(new java.awt.Color(102, 102, 102));
            btnNuevo1.setMnemonic('N');
            btnNuevo1.setText("Entendido");
            btnNuevo1.setToolTipText("");
            btnNuevo1.setContentAreaFilled(false);
            btnNuevo1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btnNuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            btnNuevo1.setIconTextGap(30);
            btnNuevo1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnNuevo1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
            jPanel7.setLayout(jPanel7Layout);
            jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(btnbuscar4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblusu1)
                        .addComponent(btnNuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(18, Short.MAX_VALUE))
            );
            jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(btnbuscar4)
                            .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(lblusu1)
                            .addGap(18, 18, 18)
                            .addComponent(btnNuevo1)
                            .addGap(42, 42, 42))))
            );

            javax.swing.GroupLayout OkLayout = new javax.swing.GroupLayout(Ok.getContentPane());
            Ok.getContentPane().setLayout(OkLayout);
            OkLayout.setHorizontalGroup(
                OkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            OkLayout.setVerticalGroup(
                OkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jPanel1.setBackground(new java.awt.Color(0, 153, 153));

            jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 30)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Grupos");

            btnNuevo.setForeground(new java.awt.Color(240, 240, 240));
            btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Icon/Documento-32.png"))); // NOI18N
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
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 979, Short.MAX_VALUE)
                            .addComponent(btneliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblusu)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addGap(34, 34, 34))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(lblusu)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btneliminar1)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btneliminar)
                                .addComponent(btnbuscar))
                            .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addComponent(jLabel7))
                    .addGap(393, 393, 393))
            );

            jPanel2.setBackground(new java.awt.Color(255, 255, 255));

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setText("Codigo");

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setText("Abreviatura");

            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setText("Grupo");

            txtcod.setEditable(false);
            txtcod.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtcod.setForeground(new java.awt.Color(51, 51, 51));
            txtcod.setBorder(null);
            txtcod.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    txtcodActionPerformed(evt);
                }
            });

            txtav.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtav.setForeground(new java.awt.Color(51, 51, 51));

            txtgp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            txtgp.setForeground(new java.awt.Color(51, 51, 51));

            jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            jLabel51.setForeground(new java.awt.Color(102, 102, 102));
            jLabel51.setText("Edicion");

            jPanel3.setBackground(new java.awt.Color(153, 153, 153));
            jPanel3.setMinimumSize(new java.awt.Dimension(0, 432));
            jPanel3.setPreferredSize(new java.awt.Dimension(0, 432));

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE)
            );

            jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

            jLabel52.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
            jLabel52.setForeground(new java.awt.Color(102, 102, 102));
            jLabel52.setText("Registro");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(55, 55, 55)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtcod)
                                    .addComponent(txtav, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtgp, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel51))
                    .addContainerGap())
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel52)
                                .addComponent(jLabel51))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(28, 28, 28)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtgp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cargareliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(cargareliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        tg = 1;
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true);
        txtcod.setText(cnn.idGrupo());
       
        txtcod.setEnabled(true);
        txtav.requestFocus();
        txtav.setEnabled(true);
        txtgp.setEnabled(true);
         txtcod.setEditable(true);
        txtav.setEditable(true);
        txtgp.setEditable(true);
        txtav.setText("");
        txtgp.setText("");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        tg=2;
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        txtcod.setEnabled(true);
        txtav.setEnabled(true);
        txtav.requestFocus();
        txtgp.setEnabled(true);
        txtcod.setEditable(true);
        txtav.setEditable(true);
        txtgp.setEditable(true);

    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
 if(tg==1){
             Guardar();  
        }
        if(tg==2){
           cargareliminar.setVisible(true);
           cargareliminar.setBackground(new Color(255,153,51)); 
           Mensaje.setText("Desea Actualizar el Registro ?");
           eli.setText("Si");
           eli.setVisible(true);
           noeli.setVisible(true); 
           tgm=3;
        }       
       
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
  cargareliminar.setVisible(true);
        cargareliminar.setBackground(new Color(255,91,70)); 
        Mensaje.setText("Desea Eliminar este registro?");
          eli.setText("Si");
          eli.setVisible(true);
          noeli.setVisible(true);
          tgm=8;
       
       

    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
  
        txtBuscar.requestFocus();
               
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodActionPerformed

    private void tb_GrupoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_GrupoMouseClicked
 int fila=tb_Grupo.getSelectedRow();
   if(evt.getClickCount()==1){
       buscar.dispose();
       txtcod.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));  
       txtav.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1))); 
       txtgp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2)));
   } 
   btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        txtcod.setEnabled(true);
        txtav.setEnabled(true);
        txtgp.setEnabled(true);
        txtcod.setEditable(false);
        txtav.setEditable(false);
        txtgp.setEditable(false);
    }//GEN-LAST:event_tb_GrupoMouseClicked

    private void tb_GrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_GrupoKeyPressed
 char teclaPresionada = evt.getKeyChar();
       if(teclaPresionada==KeyEvent.VK_ENTER){
            int fila = tb_Grupo.getSelectedRow();
            
            buscar.dispose();
            txtcod.setText(String.valueOf(tb_Grupo.getValueAt(fila, 0)));  
            txtav.setText(String.valueOf(tb_Grupo.getValueAt(fila, 1))); 
            txtgp.setText(String.valueOf(tb_Grupo.getValueAt(fila, 2))); 
        
    
       }
        btnNuevo.setEnabled(true);
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbuscar.setEnabled(true);
        txtcod.setEnabled(true);
        txtav.setEnabled(true);
        txtgp.setEnabled(true);
        txtcod.setEditable(false);
        txtav.setEditable(false);
        txtgp.setEditable(false);
    }//GEN-LAST:event_tb_GrupoKeyPressed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
Buscar();     
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void txtBuscarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarCaretUpdate
Buscar(); 
    }//GEN-LAST:event_txtBuscarCaretUpdate

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnbuscar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbuscar4ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliActionPerformed
       if (tgm==3){
     Modificar();
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
   } else   
     if (tgm==8){
     Eliminar();
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbuscar.setEnabled(true); 
   }   
        else if (tgm==2 ||tgm==1||tgm==9){
             cargareliminar.setVisible(false);
 
   }  
             if (tgm==0){
             cargareliminar.setVisible(false);
             txtav.requestFocus();
             btnguardar.setEnabled(true);
 
   }  
    }//GEN-LAST:event_eliActionPerformed

    private void noeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noeliActionPerformed
        cargareliminar.setVisible(false);
    }//GEN-LAST:event_noeliActionPerformed

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
            java.util.logging.Logger.getLogger(Caja_Grupo_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Caja_Grupo_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Caja_Grupo_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Caja_Grupo_Nomenclaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Caja_Grupo_Nomenclaturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mensaje;
    private javax.swing.JDialog Ok;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnbuscar4;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btneliminar1;
    private javax.swing.JButton btnguardar;
    private javax.swing.JDialog buscar;
    private javax.swing.JPanel cargareliminar;
    private javax.swing.JButton eli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblusu;
    private javax.swing.JLabel lblusu1;
    private javax.swing.JButton noeli;
    private javax.swing.JTable tb_Grupo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtav;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtgp;
    // End of variables declaration//GEN-END:variables
}
